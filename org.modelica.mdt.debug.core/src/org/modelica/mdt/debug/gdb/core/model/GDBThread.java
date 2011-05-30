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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecRun;
import org.modelica.mdt.debug.gdb.core.mi.command.MIGDBExit;

/**
 * @author Adeel Asghar
 *
 */
/**
 * A MDT Debugger thread. A MDT Debugger is single threaded.
 */
public class GDBThread extends GDBDebugElement implements IThread {
	
	/**
	 * Breakpoint this thread is suspended at or <code>null</code>
	 * if none.
	 */
	private IBreakpoint fBreakpoint;
	
	/**
	 * Whether this thread is stepping
	 */
	private boolean fStepping = false;
	
	/**
	 * Wether this thread is suspended
	 */
	private boolean fSuspended = false;

	private boolean fTerminated = false;

	private boolean fRunning = false;	
	
	/**
	 * Most recent error event or <code>null</code>
	 */
	private String fErrorEvent;
	
	/**
	 * Table mapping stack frames to current variables
	 */
	private Map<IStackFrame, IVariable[]> fVariables = new HashMap<IStackFrame, IVariable[]>();
	
	private IStackFrame[] fStackFrames = null; 
	/**
	 * Constructs a new thread for the given target
	 * 
	 * @param target VM
	 */
	public GDBThread(GDBDebugTarget target) {
		super(target);
		//getGDBDebugTarget().addEventListener(this);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IThread#getStackFrames()
	 */
	public IStackFrame[] getStackFrames() throws DebugException {
		fStackFrames = new IStackFrame[0];		
//		if (isSuspended()) {
//			String framesData = sendRequest(IGDBDebugCommands.CMD_STACK);
//			if (framesData != null) {
//				String[] frames = framesData.split("#");
//				fStackFrames = new IStackFrame[frames.length];
//				for (int i = 0; i < frames.length; i++) {
//					String data = frames[i];
//					fStackFrames[frames.length - i - 1] = new GDBStackFrame(this, data, i);
//				}
//				return fStackFrames;
//			}
//		}
		return fStackFrames;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IThread#hasStackFrames()
	 */
	public boolean hasStackFrames() throws DebugException {
		return isSuspended();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IThread#getPriority()
	 */
	public int getPriority() throws DebugException {
		return 0;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IThread#getTopStackFrame()
	 */
	public IStackFrame getTopStackFrame() throws DebugException {
		getStackFrames();
		if (fStackFrames.length > 0) {
			return fStackFrames[0];
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IThread#getName()
	 */
	public String getName() {
		return "Main thread";
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IThread#getBreakpoints()
	 */
	public IBreakpoint[] getBreakpoints() {
		if (fBreakpoint == null) {
			return new IBreakpoint[0];
		}
		return new IBreakpoint[]{fBreakpoint};
	}
	
	/**
	 * Notifies this thread it has been suspended by the given breakpoint.
	 * 
	 * @param breakpoint breakpoint
	 */
	public void suspendedBy(IBreakpoint breakpoint) {
		fBreakpoint = breakpoint;
		suspended(DebugEvent.BREAKPOINT);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ISuspendResume#canResume()
	 */
	public boolean canResume() {
		return isSuspended();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ISuspendResume#canSuspend()
	 */
	public boolean canSuspend() {
		return !isSuspended();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ISuspendResume#isSuspended()
	 */
	public boolean isSuspended() {
		return fSuspended && !isTerminated();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ISuspendResume#resume()
	 */
	public void resume() throws DebugException {
		setRunning(true);
		setSuspended(false);
		setStepping(false);
		resumed(DebugEvent.CLIENT_REQUEST);
		//sendRequest(IGDBDebugCommands.CMD_RUN);
	}
	/** 
	 * 
	 */
	public void start() throws DebugException {
		setRunning(true);
		setSuspended(false);
		setStepping(false);
		resumed(DebugEvent.CREATE);
//		sendRequest(IGDBDebugCommands.CMD_RUN);
		try {
			MIExecRun execRunCmd = getGDBDebugTarget().getMISession().getCommandFactory().createMIExecRun(new String[0]);
			getGDBDebugTarget().getMISession().postCommand(execRunCmd);
			if (execRunCmd.getMIInfo() == null)
				System.out.println("Error in gdb run command in GDBThread.start");
		} catch (MIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ISuspendResume#suspend()
	 */
	public void suspend() throws DebugException {
		if (isSuspended()) {
			fireSuspendEvent(DebugEvent.CLIENT_REQUEST);
			return;
		}
		setStepping(true);
		setRunning(false);		
	    //sendSignal(IGDBDebugCommands.CMD_SUSPEND);		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#canStepInto()
	 */
	public boolean canStepInto() {
		return isSuspended();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#canStepOver()
	 */
	public boolean canStepOver() {
		return isSuspended();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#canStepReturn()
	 */
	public boolean canStepReturn() {
		return isSuspended();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#isStepping()
	 */
	public boolean isStepping() {
		return fStepping;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#stepInto()
	 */
	public void stepInto() throws DebugException {
		sendRequest(IGDBDebugCommands.CMD_STEP);
		resumed(DebugEvent.STEP_INTO);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#stepOver()
	 */
	public void stepOver() throws DebugException {
		sendRequest(IGDBDebugCommands.CMD_STEP_OVER);
		resumed(DebugEvent.STEP_OVER);		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#stepReturn()
	 */
	public void stepReturn() throws DebugException {
		sendRequest(IGDBDebugCommands.CMD_STEP_RETURN);
		resumed(DebugEvent.STEP_RETURN);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ITerminate#canTerminate()
	 */
	public boolean canTerminate() {
		return !isTerminated();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ITerminate#isTerminated()
	 */
	public boolean isTerminated() {
		return getDebugTarget().isTerminated() || fTerminated;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ITerminate#terminate()
	 */
	public void terminate() throws DebugException {
		setTerminated(true);
		setStepping(false);
		setRunning(false);
//		sendSignal(IGDBDebugCommands.EVENT_SUSPEND); // first put the debugger in step mode
//		sendSignal(IGDBDebugCommands.EVENT_QUIT);    // end the event handler
		// terminate the MI Session. It will exit the GDB as well.
		getGDBDebugTarget().getMISession().terminate();
	}
	
	/**
	 * Sets whether this thread is stepping
	 * 
	 * @param stepping whether stepping
	 */
	private void setStepping(boolean stepping) {
		fStepping = stepping;
	}
	
	/**
	 * Sets whether this thread is suspended
	 * 
	 * @param suspended whether suspended
	 */
	private void setSuspended(boolean suspended) {
		fSuspended = suspended;
	}

	/**
	 * Sets the most recent error event encountered, or <code>null</code>
	 * to clear the most recent error
	 * 
	 * @param event or <code>null</code>
	 */
	private void setError(String event) {
		fErrorEvent = event;
	}

	/**
	 * Returns the most revent error event encountered since the last
	 * suspend, or <code>null</code> if none.
	 * 
	 * @return the most revent error event encountered since the last
	 * suspend, or <code>null</code> if none
	 */
	public Object getError() {
		return fErrorEvent;
	}
	
	/**
	 * Notification the target has resumed for the given reason.
	 * Clears any error condition that was last encountered and
	 * fires a resume event, and clears all cached variables
	 * for stack frames.
	 * 
	 * @param detail reason for the resume
	 */
	private void resumed(int detail) {
		setError(null);
		synchronized (fVariables) {
			fVariables.clear();
		}
		fireResumeEvent(detail);
	}
	
	/**
	 * Notification the target has suspended for the given reason
	 * 
	 * @param detail reason for the suspend
	 */
	private void suspended(int detail) {
		setStepping(true);
		setSuspended(true);
		setRunning(false);
		fireSuspendEvent(detail);
	}

	/**
     * Notification an error was encountered. Fires a breakpoint
     * suspend event.
     */
    private void exceptionHit() {
    	suspended(DebugEvent.BREAKPOINT);
    }  
	
	/**
	 * Sets the current variables for the given stack frame. Called
	 * by GDB stack frame when it is created.
	 * 
	 * @param frame
	 * @param variables
	 */
	protected void setVariables(IStackFrame frame, IVariable[] variables) {
		synchronized (fVariables) {
			fVariables.put(frame, variables);
		}
	}
	
	/**
	 * Returns the current variables for the given stack frame, or
	 * <code>null</code> if none.
	 * 
	 * @param frame stack frame
	 * @return variables or <code>null</code>
	 */
	protected IVariable[] getVariables(IStackFrame frame) {
		synchronized (fVariables) {
			IVariable[] variables = (IVariable[]) fVariables.get(frame);
			if (variables == null) {
				return new IVariable[0];
			}
			return variables;
		}
	}
	
	/**
	 * Pops the top frame off the callstack.
	 *
	 * @throws DebugException
	 */
	public void pop() throws DebugException {
		sendRequest("drop");
	}
	
	/**
	 * Returns whether this thread can pop the top stack frame.
	 *
	 * @return whether this thread can pop the top stack frame
	 */
	public boolean canPop() {
		try {
			return getStackFrames().length > 1;
		} catch (DebugException e) {
		}
		return false;
	}
	
	/**
	 * Notification this thread has terminated - update state
	 * and fire a terminate event.
	 */
	protected void terminated() {
		setTerminated(true);
		setStepping(false);
		setRunning(false);
		fireTerminateEvent();		
		try { terminate(); } catch (DebugException e) {}
	}	
	
	
	/**
	 * Sets whether this thread is currently executing.
	 * When set to <code>true</code>, this thread's current
	 * breakpoints are cleared.
	 * 
	 * @param running whether this thread is executing
	 */
	protected void setRunning(boolean running) {
		fRunning = running;
	}
	
	/**
	 * Sets whether this thread is terminated
	 * 
	 * @param terminated whether this thread is terminated
	 */
	protected void setTerminated(boolean terminated) {
		fTerminated= terminated;
	}	
}
