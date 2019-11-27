package org.modelica.mdt.debug.core.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.IBreakpointManagerListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IValue;
import org.modelica.mdt.debug.core.breakpoints.MDTLineBreakpoint;
import org.modelica.mdt.debug.core.launcher.IMDTConstants;
import org.modelica.mdt.internal.core.ErrorManager;


public class MDTDebugTarget extends MDTDebugElement implements IDebugTarget, IBreakpointManagerListener, IMDTEventListener
{

	// associated system process (VM)
	private IProcess fProcess;

	// containing launch object
	private ILaunch fLaunch;

	// program name
	private String fName;

	// sockets to communicate with MDT Debuger
	private Socket fCommandSocket;
	private PrintWriter fCommandWriter;
	private Socket fReplySocket;
	private BufferedReader fReplyReader;	
	private Socket fEventSocket;
	private BufferedReader fEventReader;
	private Socket fSignalSocket;
	private PrintWriter fSignalWriter;
	
	// terminated state
	private boolean fTerminated = false;

	private ArrayList fThreads;

	// event dispatch job
	private EventDispatchJob fEventDispatch;
	// event listeners
	private Vector<IMDTEventListener> fEventListeners = new Vector<IMDTEventListener>();

	/**
	 * Listens to events from the MDT Debuger and fires corresponding debug
	 * events.
	 */
	class EventDispatchJob extends Job {

		public EventDispatchJob() {
			super("MDT Event Dispatch");
			setSystem(true);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
		 */
		protected IStatus run(IProgressMonitor monitor) {
			String event = "";
			while (!isTerminated() && event != null) {
				try {
					event = fEventReader.readLine();
					if (event != null) {
						Object[] listeners = fEventListeners.toArray();
						for (int i = 0; i < listeners.length; i++) 
						{
							((IMDTEventListener)listeners[i]).handleEvent(event);	
						}
					}
				} catch (IOException e) {
					try
					{
						terminate();
					}
					catch(DebugException ex)
					{
						ErrorManager.logError(ex);
					}
				}
			}
			return Status.OK_STATUS;
		}

	}

	/**
	 * Constructs a new debug target in the given launch for the associated MDT
	 * VM process.
	 * 
	 * @param launch
	 *            containing launch
	 * @param process
	 *            MDT VM
	 * @param commandPort
	 *            port to send requests to the VM
	 * @param eventPort
	 *            port to read events from
	 * @param signalPort
	 *            port to send async suspend to the VM
	 * @exception CoreException
	 *                if unable to connect to host
	 */
	public MDTDebugTarget(
			ILaunch launch, 
			IProcess process, 
			int commandPort, 
			int replyPort,
			int eventPort, 
			int signalPort) throws CoreException 
	{
		super(null);
		fThreads = new ArrayList();		
		fLaunch = launch;
		fProcess = process;
		addEventListener(this);
		boolean tryAgain = true;
		int noOfTries = -1;
		int maxNoOfTries = 100;
		while (tryAgain)
		{
			noOfTries++;
			tryAgain = false;
			try 
			{
				// give interpreter a chance to start			
				try { Thread.sleep(100); } catch (InterruptedException e) {}
				
				fCommandSocket = new Socket("localhost", commandPort);
				fCommandWriter = new PrintWriter(fCommandSocket.getOutputStream());
				
				// give interpreter a chance to open next socket
				try { Thread.sleep(100); } catch (InterruptedException e) {}
				
				fReplySocket = new Socket("localhost", replyPort);
				fReplyReader = new BufferedReader(new InputStreamReader(fReplySocket.getInputStream()));
				
				// give interpreter a chance to open next socket
				try { Thread.sleep(100); } catch (InterruptedException e) {}
	
				fEventSocket = new Socket("localhost", eventPort);
				fEventReader = new BufferedReader(new InputStreamReader(fEventSocket.getInputStream()));
	
				// give interpreter a chance to open next socket
				try { Thread.sleep(100); } catch (InterruptedException e) {}
				
				fSignalSocket = new Socket("localhost", signalPort);
				fSignalWriter = new PrintWriter(fSignalSocket.getOutputStream());
				
			} catch (UnknownHostException e) {
				// if we could not connect it may be because the debugger was not yet ready
				tryAgain = true;
				if (noOfTries > maxNoOfTries)
					requestFailed("Unable to connect to MDT Debugger", e);
			} catch (IOException e) {
				// if we could not connect it may be because the debugger was not yet ready
				tryAgain = true;
				if (noOfTries > maxNoOfTries)				
					requestFailed("Unable to connect to MDT Debugger", e);
			}
			
			try { Thread.sleep(500); } catch (InterruptedException e) {}			
		}
		
		MDTThread x = new MDTThread(this);
		//x.fireCreationEvent();
		fThreads.add(x);
		fEventDispatch = new EventDispatchJob();
		fEventDispatch.schedule();
		IBreakpointManager breakpointManager = getBreakpointManager();
        breakpointManager.addBreakpointListener(this);
		breakpointManager.addBreakpointManagerListener(this);		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IDebugTarget#getProcess()
	 */
	public IProcess getProcess() {
		return fProcess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IDebugTarget#getThreads()
	 */
	public IThread[] getThreads() throws DebugException {
		synchronized (fThreads) {
			return (IThread[])fThreads.toArray(new IThread[0]);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IDebugTarget#getThreads()
	 */
	public IThread getThread() {
		synchronized (fThreads) {
			if (fThreads.size() > 0)
				return (IThread)fThreads.get(0);
			else
				return null;
		}
	}
	
	
	/**
	 * Returns an iterator over the collection of threads. The
	 * returned iterator is made on a copy of the thread list
	 * so that it is thread safe. This method should always be
	 * used instead of getThreadList().iterator()
	 * @return an iterator over the collection of threads
	 */
	private Iterator getThreadIterator() {
		List threadList;
		synchronized (fThreads) {
			threadList= (List) fThreads.clone();
		}
		return threadList.iterator();
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IDebugTarget#hasThreads()
	 */
	public boolean hasThreads() throws DebugException {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IDebugTarget#getName()
	 */
	public String getName() throws DebugException {
		if (fName == null) {
			fName = "MetaModelica Program";
			try {
				fName = getLaunch().getLaunchConfiguration().getAttribute(IMDTConstants.ATTR_MDT_PROGRAM, "MetaModelica Debugger");
			} catch (CoreException e) {
			}
		}
		return fName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IDebugTarget#supportsBreakpoint(org.eclipse.debug.core.model.IBreakpoint)
	 */
	public boolean supportsBreakpoint(IBreakpoint breakpoint) 
	{
		if (!isTerminated() && breakpoint.getModelIdentifier().equals(getModelIdentifier())) 
		{
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IDebugElement#getDebugTarget()
	 */
	public IDebugTarget getDebugTarget() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IDebugElement#getLaunch()
	 */
	public ILaunch getLaunch() {
		return fLaunch;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ITerminate#canTerminate()
	 */
	public boolean canTerminate() {
		return getProcess().canTerminate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ITerminate#isTerminated()
	 */
	public boolean isTerminated() {
		return fTerminated || getProcess().isTerminated();
	}

	/**
	 * Removes all threads from this target's collection
	 * of threads, firing a terminate event for each.
	 */
	protected void removeAllThreads() {
		Iterator itr= getThreadIterator();
		while (itr.hasNext()) {
			MDTThread child= (MDTThread) itr.next();
			child.terminated();
		}
		synchronized (fThreads) {
		    fThreads.clear();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ITerminate#terminate()
	 */
	public void terminate() throws DebugException 
	{
		fTerminated = true;
		removeAllThreads();
		IBreakpointManager breakpointManager = getBreakpointManager();
        breakpointManager.removeBreakpointListener(this);
		breakpointManager.removeBreakpointManagerListener(this);
		fireTerminateEvent();		
		removeEventListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#canResume()
	 */
	public boolean canResume() {
		return !isTerminated() && isSuspended();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#canSuspend()
	 */
	public boolean canSuspend() {
		return !isTerminated() && !isSuspended();
	}	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#isSuspended()
	 */
	public boolean isSuspended() {
		return !isTerminated() && getThread().isSuspended();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#resume()
	 */
	public void resume() throws DebugException {
		getThread().resume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#suspend()
	 */
	public void suspend() throws DebugException {
		getThread().suspend();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.IBreakpointListener#breakpointAdded(org.eclipse.debug.core.model.IBreakpoint)
	 */
	public void breakpointAdded(IBreakpoint breakpoint) {
		if (supportsBreakpoint(breakpoint)) {
			try {
					if ((breakpoint.isEnabled() && getBreakpointManager().isEnabled()) || !breakpoint.isRegistered()) {
						MDTLineBreakpoint mdtBreakpoint = (MDTLineBreakpoint)breakpoint;
					    mdtBreakpoint.install(this);
				}
			} catch (CoreException e) {
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.IBreakpointListener#breakpointRemoved(org.eclipse.debug.core.model.IBreakpoint,
	 *      org.eclipse.core.resources.IMarkerDelta)
	 */
	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
		if (supportsBreakpoint(breakpoint)) {
			try 
			{
			    MDTLineBreakpoint mdtBreakpoint = (MDTLineBreakpoint)breakpoint;
				mdtBreakpoint.remove(this);
			} 
			catch (CoreException e) 
			{
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.IBreakpointListener#breakpointChanged(org.eclipse.debug.core.model.IBreakpoint,
	 *      org.eclipse.core.resources.IMarkerDelta)
	 */
	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
		if (supportsBreakpoint(breakpoint)) {
			try {
				if (breakpoint.isEnabled() && getBreakpointManager().isEnabled()) {
					breakpointAdded(breakpoint);
				} else {
					breakpointRemoved(breakpoint, null);
				}
			} catch (CoreException e) {
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IDisconnect#canDisconnect()
	 */
	public boolean canDisconnect() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IDisconnect#disconnect()
	 */
	public void disconnect() throws DebugException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IDisconnect#isDisconnected()
	 */
	public boolean isDisconnected() {
		return isTerminated();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IMemoryBlockRetrieval#supportsStorageRetrieval()
	 */
	public boolean supportsStorageRetrieval() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IMemoryBlockRetrieval#getMemoryBlock(long,
	 *      long)
	 */
	public IMemoryBlock getMemoryBlock(long startAddress, long length)
			throws DebugException {
		return null;
	}

	/**
	 * Notification we have connected to the MDT Debugger and it has started.
	 * Run the program.
	 */
	private void started() {
		fireCreationEvent();
		installDeferredBreakpoints();
		try {
			resume();
		} catch (DebugException e) {
		}
	}

	/**
	 * Install breakpoints that are already registered with the breakpoint
	 * manager.
	 */
	private void installDeferredBreakpoints() {
		IBreakpoint[] breakpoints = getBreakpointManager().getBreakpoints(getModelIdentifier());
		for (int i = 0; i < breakpoints.length; i++) {
			breakpointAdded(breakpoints[i]);
		}
	}

	/**
	 * Returns the values on the data stack (top down)
	 * 
	 * @return the values on the data stack (top down)
	 */
	public IValue[] getDataStack() throws DebugException {
		/*
		String dataStack = "1|2|3";//sendRequest("data");
		if (dataStack != null && dataStack.length() > 0) {
			String[] values = dataStack.split("\\|");
			IValue[] theValues = new IValue[values.length];
			for (int i = 0; i < values.length; i++) {
				String value = values[values.length - i - 1];
				theValues[i] = new MDTStackValue(this, value, i);
			}
			return theValues;
		}
		*/
		return new IValue[0];		
	}
	
	/**
	 * Sends a request to the MDT Debugger and will get one of IMDTDebugCommands.EVENT_*
	 * 
	 * @param cmd
	 *            debug command
	 * @throws DebugException
	 *             if the request fails
	 */
	public String sendRequest(String cmd) throws DebugException 
	{
		/*
		if (!isSuspended()) 
		{
			sendSignal(IMDTDebugCommands.EVENT_SUSPEND);
			fireSuspendEvent(DebugEvent.CLIENT_REQUEST);
		}
		*/
		synchronized (fCommandSocket) {
			System.out.println("Sending command:" + cmd);
			fCommandWriter.println(cmd);
			fCommandWriter.flush();
			try {
				// wait for reply
				String reply = fReplyReader.readLine();
				System.out.println("Got reply:" + reply);
				System.out.flush();
				return reply;
			} catch (IOException e) {
				requestFailed("Request failed: " + cmd, e);
			}
		}
		return null;
	}

	/**
	 * Sends a signal to the MDT Debugger.
	 * 
	 * @param signal
	 *            debug signal
	 * @throws DebugException
	 *             if the request fails
	 */
	public void sendSignal(String signal) throws DebugException 
	{
			System.out.println("Sending signal:" + signal);
			fSignalWriter.println(signal);
			fSignalWriter.flush();
	}
	
	/**
	 * When the breakpoint manager disables, remove all registered breakpoints
	 * requests from the VM. When it enables, reinstall them.
	 */
	public void breakpointManagerEnablementChanged(boolean enabled) {
		IBreakpoint[] breakpoints = getBreakpointManager().getBreakpoints(getModelIdentifier());
		for (int i = 0; i < breakpoints.length; i++) {
			if (enabled) {
				breakpointAdded(breakpoints[i]);
			} else {
				breakpointRemoved(breakpoints[i], null);
			}
        }
	}	

	/**
	 * Returns whether popping the data stack is currently permitted
	 *  
	 * @return whether popping the data stack is currently permitted
	 */
	public boolean canPop() {
	    try {
            return !isTerminated() && isSuspended() && getDataStack().length > 0;
        } catch (DebugException e) {
        }
        return false;
	}
	
	/**
	 * Pops and returns the top of the data stack
	 * 
	 * @return the top value on the stack 
	 * @throws DebugException if the stack is empty or the request fails
	 */
	public IValue pop() throws DebugException {
	    IValue[] dataStack = getDataStack();
	    if (dataStack.length > 0) {
	        sendRequest("popdata");
	        return dataStack[0];
	    }
	    requestFailed("Empty stack", null);
	    return null;
	}
	
	/**
	 * Returns whether pushing a value is currently supported.
	 * 
	 * @return whether pushing a value is currently supported
	 */
	public boolean canPush() {
	    return !isTerminated() && isSuspended();
	}
	
	/**
	 * Pushes a value onto the stack.
	 * 
	 * @param value value to push
	 * @throws DebugException on failure
	 */
	public void push(String value) throws DebugException {
	    sendRequest("pushdata " + value);
	}

	/* (non-Javadoc)
	 * @see example.debug.core.model.IMDTEventListener#handleEvent(java.lang.String)
	 */
	public void handleEvent(String event) 
	{		
		System.out.println("MDTDebugTarget RECEIVED EVENT: " + event); System.out.flush();		
		if (event.equals(IMDTDebugCommands.EVENT_START)) 
		{
			started();
		} 
		else if (event.equals(IMDTDebugCommands.EVENT_QUIT)) 
		{
			try
			{
				terminate();
			}
			catch(DebugException e)
			{
				ErrorManager.logError(e);
			}
		}
	}
		
	/**
	 * Registers the given event listener. The listener will be notified of
	 * events in the program being interpretted. Has no effect if the listener
	 * is already registered.
	 *  
	 * @param listener event listener
	 */
	public void addEventListener(IMDTEventListener listener) {
		if (!fEventListeners.contains(listener)) {
			fEventListeners.add(listener);
		}
	}
	
	/**
	 * Deregisters the given event listener. Has no effect if the listener is
	 * not currently registered.
	 *  
	 * @param listener event listener
	 */
	public void removeEventListener(IMDTEventListener listener) {
		fEventListeners.remove(listener);
	}

	public PrintWriter getCommandWriter()
	{
		return fCommandWriter;
	}
	
	public Socket getCommandSocket()
	{
		return fCommandSocket;
	}	

	public BufferedReader getReplyReader()
	{
		return fReplyReader;
	}	
	
	
	
	
}

