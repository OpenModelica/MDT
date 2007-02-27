package org.modelica.mdt.debug.core.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.MessageFormat;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IValue;
import org.modelica.mdt.debug.core.launcher.IMDTConstants;

public class MDTDebugTarget extends MDTDebugElement implements IDebugTarget {

	// associated system process (VM)
	private IProcess fProcess;

	// containing launch object
	private ILaunch fLaunch;

	// program name
	private String fName;

	// sockets to communicate with MDT Debuger
	private Socket fCommandSocket;

	private PrintWriter fCommandWriter;

	private BufferedReader fCommandReader;

	private Socket fEventSocket;

	private BufferedReader fEventReader;

	// suspend state
	private boolean fSuspended = true;

	// terminated state
	private boolean fTerminated = false;

	// threads
	private MDTThread fThread;

	private IThread[] fThreads;

	// event dispatch job
	private EventDispatchJob fEventDispatch;

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
						System.out.println("RECEIVED EVENT: " + event);

						fThread.setBreakpoints(null);
						fThread.setStepping(false);
						
						if (event.startsWith("@")) {
							breakpointHit(event);							
						} else if (event.equals("started")) {
							started();
						} else if (event.equals("terminated")) {
							terminated();
						} else if (event.startsWith("run")) {
							if (event.endsWith("step")) {
								fThread.setStepping(true);
								resumed(DebugEvent.STEP_OVER);
							} else if (event.endsWith("client")) {
								resumed(DebugEvent.CLIENT_REQUEST);
							}
						} else if (event.startsWith("suspend")) {
							if (event.endsWith("client")) {
								suspended(DebugEvent.CLIENT_REQUEST);
							} else if (event.endsWith("step")) {
								suspended(DebugEvent.STEP_END);
							} else if (event.indexOf("breakpoint") >= 0) {
								breakpointHit(event);
							}
						}
					}
				} catch (IOException e) {
					System.out.println("EXCEPTION IN RUN:" + e.getMessage());
					terminated();
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
	 * @exception CoreException
	 *                if unable to connect to host
	 */
	public MDTDebugTarget(ILaunch launch, IProcess process, int commandPort, int eventPort) throws CoreException {
		super(null);
		fLaunch = launch;
		fTarget = this;
		fProcess = process;
		try 
		{
			fCommandSocket = new Socket("localhost", commandPort);
			fCommandWriter = new PrintWriter(fCommandSocket.getOutputStream());
			fCommandReader = new BufferedReader(new InputStreamReader(fCommandSocket.getInputStream()));

			fEventSocket = new Socket("localhost", eventPort);
			fEventReader = new BufferedReader(new InputStreamReader(fEventSocket.getInputStream()));
		} catch (UnknownHostException e) {
			abort("Unable to connect to MDT Debugger", e);
		} catch (IOException e) {
			abort("Unable to connect to MDT Debugger", e);
		}
		
		fThread = new MDTThread(this);
		fThreads = new IThread[] { fThread };
		fEventDispatch = new EventDispatchJob();
		fEventDispatch.schedule();
		DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(this);
		
		//sendCommand("break on main");
		//sendCommand("run");		
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
		return fThreads;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IDebugTarget#hasThreads()
	 */
	public boolean hasThreads() throws DebugException {
		return false;
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
		return true;
		/*
		if (breakpoint.getModelIdentifier().equals(IMDTConstants.ID_MDT_DEBUG_MODEL)) {
			try {
				String program = getLaunch().getLaunchConfiguration().getAttribute(IMDTConstants.ATTR_MDT_PROGRAM,(String) null);
				if (program != null) {
					IMarker marker = breakpoint.getMarker();
					if (marker != null) {
						IPath p = new Path(program);
						return marker.getResource().getFullPath().equals(p);
					}
				}
			} 
			catch (CoreException e) 
			{
			}
		}
		return false;
		*/
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
		return getProcess().isTerminated();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ITerminate#terminate()
	 */
	public void terminate() throws DebugException {
		synchronized (fCommandSocket) {
			fCommandWriter.println("exit");
			fCommandWriter.flush();
		}
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
		return fSuspended;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#resume()
	 */
	public void resume() throws DebugException {
		sendCommand("run");
	}

	/**
	 * Notification the target has resumed for the given reason
	 * 
	 * @param detail
	 *            reason for the resume
	 */
	private void resumed(int detail) {
		fSuspended = false;
		fThread.fireResumeEvent(detail);
	}

	/**
	 * Notification the target has suspended for the given reason
	 * 
	 * @param detail
	 *            reason for the suspend
	 */
	private void suspended(int detail) {
		fSuspended = true;
		fThread.fireSuspendEvent(detail);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#suspend()
	 */
	public void suspend() throws DebugException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.IBreakpointListener#breakpointAdded(org.eclipse.debug.core.model.IBreakpoint)
	 */
	public void breakpointAdded(IBreakpoint breakpoint) {
		if (supportsBreakpoint(breakpoint)) {
			try {
				if (breakpoint.isEnabled()) {
					try 
					{
						sendCommand("break on "+ breakpoint.getMarker().getResource().getName() + ":" + (((ILineBreakpoint) breakpoint).getLineNumber()));
					} 
					catch (CoreException e) { }
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
			try {
				sendCommand("break off "+ breakpoint.getMarker().getResource().getName() + ":" + (((ILineBreakpoint) breakpoint).getLineNumber()));
			} catch (CoreException e) {
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
				if (breakpoint.isEnabled()) {
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
		return false;
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
		IBreakpoint[] breakpoints = DebugPlugin.getDefault()
				.getBreakpointManager().getBreakpoints(
						IMDTConstants.ID_MDT_DEBUG_MODEL);
		for (int i = 0; i < breakpoints.length; i++) {
			breakpointAdded(breakpoints[i]);
		}
	}

	/**
	 * Called when this debug target terminates.
	 */
	private void terminated() {
		fTerminated = true;
		fSuspended = false;
		DebugPlugin.getDefault().getBreakpointManager().removeBreakpointListener(this);
		fireTerminateEvent();
	}

	/**
	 * Returns the current stack frames in the target.
	 * 
	 * @return the current stack frames in the target
	 * @throws DebugException
	 *             if unable to perform the request
	 */
	protected IStackFrame[] getStackFrames() throws DebugException {
		synchronized (fCommandSocket) {
			fCommandWriter.println("livevars");
			fCommandWriter.flush();
			try {
				String framesData = fCommandReader.readLine();
				if (framesData != null) {
					String[] frames = framesData.split("#");
					IStackFrame[] theFrames = new IStackFrame[frames.length];
					for (int i = 0; i < frames.length; i++) {
						String data = frames[i];
						theFrames[frames.length - i - 1] = new MDTStackFrame(
								fThread, data, i);
					}
					return theFrames;
				}
			} catch (IOException e) {
				abort("Unable to retrieve stack frames", e);
			}
		}
		
		return new IStackFrame[0];
	}

	/**
	 * Single step the interpreter.
	 * 
	 * @throws DebugException
	 *             if the request fails
	 */
	protected void step() throws DebugException {
		sendCommand("step");
	}

	/**
	 * Returns the current value of the given variable.
	 * 
	 * @param variable
	 * @return variable value
	 * @throws DebugException
	 *             if the request fails
	 */
	protected IValue getVariableValue(MDTVariable variable)
			throws DebugException {
		synchronized (fCommandSocket) {
			fCommandWriter.println("var " + variable.getName());
			// + variable.getStackFrame().getIdentifier() + " "
			fCommandWriter.flush();
			try {
				String value = fCommandReader.readLine();
				return new MDTValue(this, value);
			} catch (IOException e) {
				abort(MessageFormat.format("Unable to retrieve value for variable {0}", new String[] { variable.getName() }), e);
			}
		}
		return null;
	}

	/**
	 * Returns the values on the data stack (top down)
	 * 
	 * @return the values on the data stack (top down)
	 */
	public IValue[] getDataStack() throws DebugException {
		synchronized (fCommandSocket) {
			fCommandWriter.println("data");
			fCommandWriter.flush();
			try {
				String valueString = fCommandReader.readLine();
				if (valueString != null && valueString.length() > 0) {
					String[] values = valueString.split("\\|");
					IValue[] theValues = new IValue[values.length];
					for (int i = 0; i < values.length; i++) {
						String value = values[values.length - i - 1];
						theValues[i] = new MDTValue(this, value);
					}
					return theValues;
				}
			} catch (IOException e) {
				abort("Unable to retrieve data stack", e);
			}
		}
		return new IValue[0];
	}

	/**
	 * Sends a request to the MDT Debugger and waits for an OK.
	 * 
	 * @param request
	 *            debug command
	 * @throws DebugException
	 *             if the request fails
	 */
	private void sendCommand(String request) throws DebugException {
		synchronized (fCommandSocket) {
			System.out.println("Sending command:" + request);
			fCommandWriter.println(request);
			fCommandWriter.flush();
			//try 
			//{
				// wait for "ok"
				//String response = fCommandReader.readLine();
			//} 
			//catch (IOException e) 
			//{
			//	abort("Command failed: " + request, e);
			//}
		}
	}

	/**
	 * Notification a breakpoint was encountered. Determine which breakpoint was
	 * hit and fire a suspend event.
	 * 
	 * @param event
	 *            debug event
	 */
	private void breakpointHit(String event) 
	{
		// determine which breakpoint was hit, and set the thread's breakpoint
		int column = event.indexOf(':');
		String file = event.substring(1,column);
		event=event.substring(column+1);
		String line = event.substring(0,event.indexOf('.'));
		int lastSpace = event.lastIndexOf(' ');
		//if (lastSpace > 0) {
		//	String line = event.substring(lastSpace + 1);
			int lineNumber = Integer.parseInt(line);
			IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager().getBreakpoints(IMDTConstants.ID_MDT_DEBUG_MODEL);

			for (int i = 0; i < breakpoints.length; i++) {
				IBreakpoint breakpoint = breakpoints[i];
				if (supportsBreakpoint(breakpoint)) {
					if (breakpoint instanceof ILineBreakpoint) {
						ILineBreakpoint lineBreakpoint = (ILineBreakpoint) breakpoint;						
						try {
							System.out.println("Line number "+ lineBreakpoint.getLineNumber());
							if (lineBreakpoint.getLineNumber()-1 == lineNumber) {
								fThread.setBreakpoints(new IBreakpoint[] { breakpoint });
								break;
							}
						} catch (CoreException e) {
						}
					}
				}
			}
		//}
		suspended(DebugEvent.BREAKPOINT);
	}

}
