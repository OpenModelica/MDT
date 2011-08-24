/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Linkoping University,
 * Department of Computer and Information Science,
 * SE-58183 Linkoping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 
 * AND THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S  
 * ACCEPTANCE OF THE OSMC PUBLIC LICENSE.
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from Linkoping University, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS
 * OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 */
package org.modelica.mdt.debug.gdb.core.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugEvent;
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
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.core.breakpoints.MDTLineBreakpoint;
import org.modelica.mdt.debug.core.launcher.IMDTConstants;
import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.mi.MISession;
import org.modelica.mdt.debug.gdb.core.mi.command.CommandFactory;
import org.modelica.mdt.debug.gdb.core.mi.command.MICommand;
import org.modelica.mdt.debug.gdb.core.mi.event.MIBreakpointHitEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MIEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MIFunctionFinishedEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MIGDBExitEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MIInferiorExitEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MISteppingRangeEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MIStoppedEvent;
import org.modelica.mdt.debug.gdb.core.mi.output.MIFrame;
import org.modelica.mdt.debug.gdb.core.mi.output.MIInfo;
import org.modelica.mdt.debug.gdb.core.model.thread.GDBThread;
import org.modelica.mdt.debug.gdb.core.model.thread.GDBThread.ExecuteCommand;
import org.modelica.mdt.debug.gdb.helper.GDBHelper;

/**
 * @author Adeel Asghar
 *
 */
public class GDBDebugTarget extends GDBDebugElement implements IDebugTarget, IBreakpointManagerListener, Observer {

	// associated system process (VM)
	private IProcess fProcess;
	// containing launch object
	private ILaunch fLaunch;
	private MISession fMISession;
	// program name
	private String fName;

	// terminated state
	private boolean fTerminated = false;
	private ArrayList<GDBThread> fThreads;	
	/**
	 * Constructs a new debug target in the given launch for the associated GDB
	 * VM process.
	 * 
	 * @param launch
	 *            containing launch
	 * @param process
	 *            GDB VM
	 * @param miSession 
	 * @exception CoreException
	 *                if unable to connect to host
	 */
	public GDBDebugTarget(ILaunch launch, IProcess process, MISession miSession) throws CoreException 
	{
		super(null);
		fThreads = new ArrayList<GDBThread>();
		fLaunch = launch;
		fProcess = process;
		fMISession = miSession;
		fMISession.addObserver(this);
		
		GDBThread x = new GDBThread(this);
		fThreads.add(x);
		started();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Iterator getThreadIterator() {
		List<GDBThread> threadList;
		synchronized (fThreads) {
			threadList= (List<GDBThread>) fThreads.clone();
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
	@SuppressWarnings("unchecked")
	protected void removeAllThreads() {
		Iterator<GDBThread> itr= getThreadIterator();
		while (itr.hasNext()) {
			GDBThread child= (GDBThread) itr.next();
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
		// it can be possible that we have already terminated when the inferior exit event is raised.
		if (!fTerminated) {
			fTerminated = true;
			removeAllThreads();
			IBreakpointManager breakpointManager = getBreakpointManager();
	        breakpointManager.removeBreakpointListener(this);
			breakpointManager.removeBreakpointManagerListener(this);
			fireTerminateEvent();
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
		//((GDBThread)getThread()).suspend();
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
					mdtBreakpoint.insertBreakpoint(this);
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
				mdtBreakpoint.removeBreakpoint(this);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	 * Notification we have connected to the GDB Debugger and it has started.
	 * Run the program.
	 */
	private void started() {
		fireCreationEvent();
		installDeferredBreakpoints();
		try {
			((GDBThread)getThread()).start();
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
        return false;
	}
	
	/**
	 * Pops and returns the top of the data stack
	 * 
	 * @return the top value on the stack 
	 * @throws DebugException if the stack is empty or the request fails
	 */
	public IValue pop() throws DebugException {
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
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	/**
	 * Listens to the notifications from MI Session and interprets the commands received from MI Session
	 * and fires the corresponding debug events.
	 * 
	 * @param Observable
	 * @throws Object
	 */
	@Override
	public void update(Observable arg, Object event) {
		// TODO Auto-generated method stub
		MIEvent miEvent = (MIEvent)event;
		try {
			if (miEvent instanceof MIInferiorExitEvent || miEvent instanceof MIGDBExitEvent) {
				if (miEvent instanceof MIInferiorExitEvent) {
					if (MDTDebugCorePlugin.DEBUG) System.out.println("MIInferiorExitEvent caught in gdb debug target");
				} else if (miEvent instanceof MIGDBExitEvent)
					if (MDTDebugCorePlugin.DEBUG) System.out.println("MIGDBExitEvent caught in gdb debug target");
				terminate();
			}
			else if (miEvent instanceof MIBreakpointHitEvent) {
				if (MDTDebugCorePlugin.DEBUG) System.out.println("MIBreakpointHitEvent caught in gdb debug target");
				((GDBThread)getThread()).setRefreshStackFrames(true);
				((GDBThread)getThread()).suspended(DebugEvent.BREAKPOINT);
			}
			// MISteppingRangeEvent can be caused by -exec-next and -exec-step
			else if (miEvent instanceof MISteppingRangeEvent) {
				if (MDTDebugCorePlugin.DEBUG) System.out.println("MISteppingRangeEvent caught in gdb debug target");
				if (skipSteppedInFrames(miEvent)) {
					((GDBThread)getThread()).setRefreshStackFrames(true);
					((GDBThread)getThread()).suspended(DebugEvent.STEP_END);
				}
			}
			// MIFunctionFinishedEvent is caused by -exec-finish
			else if (miEvent instanceof MIFunctionFinishedEvent) {
				if (MDTDebugCorePlugin.DEBUG) System.out.println("MIFunctionFinishedEvent caught in gdb debug target");
				if (skipSteppedInFrames(miEvent)) {
					((GDBThread)getThread()).setRefreshStackFrames(true);
					((GDBThread)getThread()).suspended(DebugEvent.STEP_RETURN);
				}
			}
		} catch (DebugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param miEvent
	 * @return 
	 * @throws CoreException 
	 */
	private boolean skipSteppedInFrames(MIEvent miEvent) throws CoreException {
		// TODO Auto-generated method stub
		MIStoppedEvent stoppedEvent = (MIStoppedEvent)miEvent;
		MIFrame miFrame = stoppedEvent.getFrame();
		// if we reach to a c file with step in we should keep on executing -exec-step or -exec-next until we are back to .mo file.
		if (GDBHelper.filterCFiles(getGDBDebugTarget(), miFrame)) {
			CommandFactory factory = fMISession.getCommandFactory();
			MICommand execCmd = null;
			if (((GDBThread)getThread()).getExecuteCommand() == ExecuteCommand.EXECNEXT) {
				execCmd = factory.createMIExecNext();
			} else if (((GDBThread)getThread()).getExecuteCommand() == ExecuteCommand.EXECSTEP) {
				execCmd = factory.createMIExecStep();
			}
			try {
				fMISession.postCommand(execCmd);
				MIInfo info = execCmd.getMIInfo();
				if (info == null) {
					throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
							MDTDebugCorePlugin.getResourceString("GDBDebugTarget.skipSteppedInFrames.ExecStep.NoAnswer"), null));
				}
			} catch (MIException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @return
	 */
	public MISession getMISession() {
		// TODO Auto-generated method stub
		return fMISession;
	}	
}
