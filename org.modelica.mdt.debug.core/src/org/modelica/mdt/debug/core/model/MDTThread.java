package org.modelica.mdt.debug.core.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;

/**
 * A MDT Debugger thread. A MDT Debugger is single threaded.
 */
public class MDTThread extends MDTDebugElement implements IThread, IMDTEventListener {
	
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
	public MDTThread(MDTDebugTarget target) {
		super(target);
		getMDTDebugTarget().addEventListener(this);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IThread#getStackFrames()
	 */
	public IStackFrame[] getStackFrames() throws DebugException {
		fStackFrames = new IStackFrame[0];		
		if (isSuspended()) {
			String framesData = sendRequest(IMDTDebugCommands.CMD_STACK);
			if (framesData != null) {
				String[] frames = framesData.split("#");
				fStackFrames = new IStackFrame[frames.length];
				for (int i = 0; i < frames.length; i++) {
					String data = frames[i];
					fStackFrames[frames.length - i - 1] = new MDTStackFrame(this, data, i);
				}
				return fStackFrames;
			}
		}
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
		setStepping(false);
		resumed(DebugEvent.CLIENT_REQUEST);
		sendRequest(IMDTDebugCommands.CMD_RUN);
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
	    sendSignal(IMDTDebugCommands.CMD_SUSPEND);		
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
		sendRequest(IMDTDebugCommands.CMD_STEP);
		resumed(DebugEvent.STEP_INTO);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#stepOver()
	 */
	public void stepOver() throws DebugException {
		sendRequest(IMDTDebugCommands.CMD_STEP_OVER);
		resumed(DebugEvent.STEP_OVER);		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#stepReturn()
	 */
	public void stepReturn() throws DebugException {
		sendRequest(IMDTDebugCommands.CMD_STEP_RETURN);
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
		sendSignal(IMDTDebugCommands.EVENT_SUSPEND); // first put the debugger in step mode
		sendSignal(IMDTDebugCommands.EVENT_QUIT);    // end the event handler
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

	/* (non-Javadoc)
	 * @see example.debug.core.model.IMDTEventListener#handleEvent(java.lang.String)
	 */
	public void handleEvent(String event) 
	{
		// clear previous state
		fBreakpoint = null;
		setStepping(false);
		
		// handle events
		System.out.println("MDThread RECEIVED EVENT: " + event); System.out.flush();
		
		if (event.startsWith(IMDTDebugCommands.EVENT_STEP)) {
			suspended(DebugEvent.STEP_END);
		}
		else if (event.startsWith(IMDTDebugCommands.EVENT_STEP_OVER)) {
			suspended(DebugEvent.STEP_END);							
		}
		else if (event.startsWith(IMDTDebugCommands.EVENT_BREAKPOINT)) {
				setSuspended(true);
				if (getError() != null) exceptionHit();
				suspended(DebugEvent.BREAKPOINT);
		}
		else if (event.startsWith(IMDTDebugCommands.EVENT_READY)) {
				suspended(DebugEvent.CLIENT_REQUEST);
		}
		else if (event.startsWith(IMDTDebugCommands.EVENT_RUN)) 
		{
			resumed(DebugEvent.CLIENT_REQUEST);							
		}
		else if (event.startsWith(IMDTDebugCommands.EVENT_QUIT)) 
		{
			terminated();							
		}
		if (event.equals(IMDTDebugCommands.EVENT_START)) 
		{
			// do nothing!
			//setSuspended(true);
		} 		
		else 
		{
			System.out.println("Unknown Event:" + event);
			setError(event);
		}		
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
	 * by MDT stack frame when it is created.
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
