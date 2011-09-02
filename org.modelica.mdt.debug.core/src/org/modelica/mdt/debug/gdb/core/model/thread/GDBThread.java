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
package org.modelica.mdt.debug.gdb.core.model.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.core.launcher.IMDTConstants;
import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.mi.MISession;
import org.modelica.mdt.debug.gdb.core.mi.command.CommandFactory;
import org.modelica.mdt.debug.gdb.core.mi.command.MIDataEvaluateExpression;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecContinue;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecFinish;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecNext;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecRun;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecStep;
import org.modelica.mdt.debug.gdb.core.mi.command.MIStackInfoDepth;
import org.modelica.mdt.debug.gdb.core.mi.command.MIStackListFrames;
import org.modelica.mdt.debug.gdb.core.mi.output.MIFrame;
import org.modelica.mdt.debug.gdb.core.mi.output.MIInfo;
import org.modelica.mdt.debug.gdb.core.mi.output.MIStackInfoDepthInfo;
import org.modelica.mdt.debug.gdb.core.mi.output.MIStackListFramesInfo;
import org.modelica.mdt.debug.gdb.core.model.GDBDebugElement;
import org.modelica.mdt.debug.gdb.core.model.GDBDebugTarget;
import org.modelica.mdt.debug.gdb.core.model.stack.GDBStackFrame;
import org.modelica.mdt.debug.gdb.helper.GDBHelper;

/**
 * @author Adeel Asghar
 *
 */
/**
 * A MDT GDB Debugger thread. A MDT GDB Debugger is single threaded.
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
	 * Whether this thread is suspended
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
	private List<GDBStackFrame> fGDBStackFrames = null;
	private int fStackDepth = 0;
	private final static int MAX_STACK_DEPTH = 100;
	private Boolean fRefreshStackFrames = true;
	public enum ExecuteCommand {
		EXECNEXT,
		EXECSTEP;
	}
	private ExecuteCommand fExecuteCommand = ExecuteCommand.EXECSTEP;
	/**
	 * Constructs a new thread for the given target
	 * 
	 * @param target VM
	 */
	public GDBThread(GDBDebugTarget target) {
		super(target);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IThread#getStackFrames()
	 */
	public IStackFrame[] getStackFrames() throws DebugException {
		computeStackFrames();
		return (IStackFrame[])fGDBStackFrames.toArray(new IStackFrame[fGDBStackFrames.size()]);
	}

	/**
	 * @param lowFrame
	 * @param highFrame
	 * @return
	 */
	private void getStackFrames(int lowFrame, int highFrame) {
		// TODO Auto-generated method stub
		// if fGDBStackFrames is null then initialize it
		if (fGDBStackFrames == null) {
			fGDBStackFrames = new ArrayList<GDBStackFrame>();
		}
		try {
			// get the list of frames from GDB
			MISession miSession = getGDBDebugTarget().getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			MIStackListFrames stackListFramesCmd = factory.createMIStackListFrames(lowFrame, highFrame);
			miSession.postCommand(stackListFramesCmd);
			MIStackListFramesInfo stackListFramesInfo = stackListFramesCmd.getMIStackListFramesInfo();
			if (stackListFramesInfo == null) {
				throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
						MDTDebugCorePlugin.getResourceString("GDBThread.getStackFrames.StackListFrames.NoAnswer"), null));
			}
			// first remove the frames that are removed from the stacks list
			removeStackFrames(stackListFramesInfo.getMIFrames());
			compareStackFrames(stackListFramesInfo.getMIFrames());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MDTDebugCorePlugin.log(null, e);
		}
	}

	/**
	 * @param miFrames
	 */
	private void removeStackFrames(MIFrame[] miFrames) {
		// TODO Auto-generated method stub
		Boolean isFound;
		for(Iterator<GDBStackFrame> i = fGDBStackFrames.iterator(); i.hasNext();) {
			isFound = false;
			GDBStackFrame stackFrame = i.next();
			for (MIFrame miFrame : miFrames) {
				if (stackFrame.compareMIFrame(miFrame)) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				i.remove();
				stackFrame.dispose();
				stackFrame = null;
			}
		}
	}

	/**
	 * 
	 */
	protected synchronized void computeStackFrames() {
		// TODO Auto-generated method stub
		if (isSuspended()) {
			if (isRefreshStackFrames()) {
				int depth = getStackInfoDepth();
				if (depth >= getMaxStackDepth())
					depth = getMaxStackDepth() - 1;
				getStackFrames(0, depth - 1);
				setRefreshStackFrames(false);
			}
		}
	}	

	/**
	 * Compares the stack frames received from GDB with the existing stack frames
	 * If the stack is already loaded we just update it.
	 * Also filters out the .c file depending on the debug_c_files flag.
	 * 
	 * @param miFrames
	 * @throws CoreException 
	 */
	private void compareStackFrames(MIFrame[] miFrames) throws CoreException {
		// TODO Auto-generated method stub
		List<MIFrame> miFramesList = new ArrayList<MIFrame>(Arrays.asList(miFrames));
		int i = -1;
		while (i + 1 < miFramesList.size()) {
			i++;
			for (GDBStackFrame gdbStackFrame : fGDBStackFrames) {
				if (gdbStackFrame.compareMIFrame(miFramesList.get(i))) {
					gdbStackFrame.updateMe(miFramesList.get(i));
					miFramesList.remove(i);
					i = -1;		// restart the loop since the list is updated.
					break;
				}
			}
		}
		
		// create stack frames
		for (i = 0 ; i < miFramesList.size() ; i++) {
			if (!GDBHelper.filterCFiles(getGDBDebugTarget(), (miFramesList.get(i)))) {
				fGDBStackFrames.add(new GDBStackFrame(this, miFramesList.get(i)));
			}
		}
		// sort the stack frames
		Collections.sort(fGDBStackFrames, new Comparator<GDBStackFrame>() {
			@Override
			public int compare(GDBStackFrame stackFrame1, GDBStackFrame stackFrame2) {
				// TODO Auto-generated method stub
				Integer id1 = new Integer(stackFrame1.getIdentifier());
				Integer id2 = new Integer(stackFrame2.getIdentifier());
				return id1.compareTo(id2);
			}
		});
	}

	/**
	 * @return
	 */
	private int getMaxStackDepth() {
		// TODO Auto-generated method stub
		return MAX_STACK_DEPTH;
	}

	/**
	 * @return
	 */
	private int getStackInfoDepth() {
		// TODO Auto-generated method stub
		if (fStackDepth == 0) { 
			try {
				MISession miSession = getGDBDebugTarget().getMISession();
				CommandFactory factory = miSession.getCommandFactory();
				MIStackInfoDepth stackInfoDepthCmd = factory.createMIStackInfoDepth();
				MIStackInfoDepthInfo stackInfoDepthInfo;
				miSession.postCommand(stackInfoDepthCmd);
				try {
					stackInfoDepthInfo = stackInfoDepthCmd.getMIStackInfoDepthInfo();
					if (stackInfoDepthInfo == null) {
						throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
								MDTDebugCorePlugin.getResourceString("GDBThread.getStackInfoDepth.StackInfoDepth.NoAnswer"), null));
					}
					fStackDepth = stackInfoDepthInfo.getDepth();
				} catch (MIException e) {
					// First try fails, retry. gdb patches up the corrupt frame
					// so retry should give us a frame count that is safe.
					stackInfoDepthCmd = factory.createMIStackInfoDepth();
					miSession.postCommand(stackInfoDepthCmd);
					stackInfoDepthInfo = stackInfoDepthCmd.getMIStackInfoDepthInfo();
					if (stackInfoDepthInfo == null) {
						try {
							throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
									MDTDebugCorePlugin.getResourceString("GDBThread.getStackInfoDepth.StackInfoDepth.NoAnswer"), null));
						} catch (CoreException e1) {
							// TODO Auto-generated catch block
							MDTDebugCorePlugin.log(null, e);
						}
					}
					fStackDepth = stackInfoDepthInfo.getDepth();
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					MDTDebugCorePlugin.log(null, e);
				}
			} catch (MIException e) {
				// 1 is safe to return
				return 1;
			}
		}
		return fStackDepth;
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
		computeStackFrames();
		if (fGDBStackFrames.size() > 0) {
			return fGDBStackFrames.get(0);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IThread#getName()
	 */
	public String getName() {
		return "Main Thread";
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
		// send gdb the -exec-continue commmand
		try {
			MIExecContinue execContinueCmd = getGDBDebugTarget().getMISession().getCommandFactory().createMIExecContinue();
			execContinueCmd.setQuiet(true);
			getGDBDebugTarget().getMISession().postCommand(execContinueCmd);
			if (execContinueCmd.getMIInfo() == null) {
				throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
						MDTDebugCorePlugin.getResourceString("GDBThread.resume.ExecContinue.NoAnswer"), null));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MDTDebugCorePlugin.log(null, e);
		}
	}
	/**
	 * @throws CoreException  
	 * 
	 */
	public void start() throws CoreException {
		setRunning(true);
		setSuspended(false);
		setStepping(false);
		try {
			MISession miSession = getGDBDebugTarget().getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			// send -exec-run
			MIExecRun execRunCmd = factory.createMIExecRun(new String[0]);
			miSession.postCommand(execRunCmd);
			if (execRunCmd.getMIInfo() == null) {
				throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
						MDTDebugCorePlugin.getResourceString("GDBThread.start.ExecRun.NoAnswer"), null));
			}
			// if run is ok then change the stdout buffer policy, only for windows
			if (Platform.getOS().equals(Platform.OS_WIN32)) {
				MIDataEvaluateExpression changeStdoutBufferCmd = factory.createMIChangeStdoutBuffer();
				miSession.postCommand(changeStdoutBufferCmd, -1);
				// we don't care about the time and output of this command
			}
		} catch (MIException e) {
			// TODO Auto-generated catch block
			MDTDebugCorePlugin.log(e.getMessage() + e.getLogMessage(), e);
		}
		getGDBDebugTarget().getMISession().getGDBInferior().update();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ISuspendResume#suspend()
	 */
	public void suspend() throws DebugException {
		// on windows -exec-interrupt is not supported
		System.out.println(Platform.getOS());
		if (Platform.getOS().equals(Platform.OS_WIN32)) {
			try {
				int result = getGDBDebugTarget().getMISession().interruptInferior(getGDBDebugTarget());
				if (result != 0) {
					MDTDebugCorePlugin.log(null, new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0, 
							"Unable to interrupt the running program.", null)));
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				MDTDebugCorePlugin.log(null, e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				MDTDebugCorePlugin.log(null, e);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				MDTDebugCorePlugin.log(null, e);
			}
			suspended(DebugEvent.CLIENT_REQUEST);
		}
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
		if (isSuspended()) {
			return fGDBStackFrames.size() > 1;
		} else {
			return false;
		}
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
		setRunning(true);
		setSuspended(false);
		setStepping(false);
		resumed(DebugEvent.STEP_INTO);
		// send the -exec-step command
		MISession miSession = getGDBDebugTarget().getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIExecStep execStepCommand = factory.createMIExecStep();
		try {
			miSession.postCommand(execStepCommand);
			setExecuteCommand(ExecuteCommand.EXECSTEP);
			MIInfo info = execStepCommand.getMIInfo();
			if (info == null) {
				throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
						MDTDebugCorePlugin.getResourceString("GDBThread.stepInto.ExecStep.NoAnswer"), null));
			}
		} catch (Exception e) {
			MDTDebugCorePlugin.log(null, e);
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#stepOver()
	 */
	public void stepOver() throws DebugException {
		setRunning(true);
		setSuspended(false);
		setStepping(false);
		resumed(DebugEvent.STEP_OVER);
		// send the -exec-next command
		CommandFactory factory = getGDBDebugTarget().getMISession().getCommandFactory();
		MIExecNext execNextCmd = factory.createMIExecNext();
		try {
			getGDBDebugTarget().getMISession().postCommand(execNextCmd);
			setExecuteCommand(ExecuteCommand.EXECNEXT);
			MIInfo info = execNextCmd.getMIInfo();
			if (info == null) {
				throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
						MDTDebugCorePlugin.getResourceString("GDBThread.stepOver.ExecNext.NoAnswer"), null));
			}
		} catch (Exception e) {
			MDTDebugCorePlugin.log(null, e);
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#stepReturn()
	 */
	public void stepReturn() throws DebugException {
		setRunning(true);
		setSuspended(false);
		setStepping(false);
		resumed(DebugEvent.STEP_RETURN);
		// send the -exec-finish command
		CommandFactory factory = getGDBDebugTarget().getMISession().getCommandFactory();
		MIExecFinish execFinishCmd = factory.createMIExecFinish();
		try {
			getGDBDebugTarget().getMISession().postCommand(execFinishCmd);
			setExecuteCommand(ExecuteCommand.EXECNEXT);
			MIInfo info = execFinishCmd.getMIInfo();
			if (info == null) {
				throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
						MDTDebugCorePlugin.getResourceString("GDBThread.stepReturn.ExecFinish.NoAnswer"), null));
			}
		} catch (Exception e) {
			MDTDebugCorePlugin.log(null, e);
		}
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
		// terminate the MI Session. It will exit the GDB as well.
		getGDBDebugTarget().getMISession().getSessionProcess().destroy();
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
	public void setSuspended(boolean suspended) {
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
		fireResumeEvent(detail);
	}
	
	/**
	 * Notification the target has suspended for the given reason
	 * 
	 * @param detail reason for the suspend
	 */
	public void suspended(int detail) {
		setStepping(true);
		setSuspended(true);
		setRunning(false);
		fireSuspendEvent(detail);
	}
	
	/**
	 * Notification this thread has terminated - update state
	 * and fire a terminate event.
	 */
	public void terminated() {
		setTerminated(true);
		setStepping(false);
		setRunning(false);
		
		try { terminate(); } catch (DebugException e) {}
		fireTerminateEvent();
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
	 *  
	 * @return true if thread is running otherwise false
	 */
	protected boolean isRunning() {
		return fRunning;
	}
	/**
	 * Sets whether this thread is terminated
	 * 
	 * @param terminated whether this thread is terminated
	 */
	protected void setTerminated(boolean terminated) {
		fTerminated= terminated;
	}

	/**
	 * @param refreshStackFrames the fRefreshChildren to set
	 */
	public void setRefreshStackFrames(Boolean refreshStackFrames) {
		this.fRefreshStackFrames = refreshStackFrames;
	}

	/**
	 * @return the fRefreshStackFrames
	 */
	public Boolean isRefreshStackFrames() {
		return fRefreshStackFrames;
	}

	/**
	 * @param executeCommand the fExecuteCommand to set
	 */
	public void setExecuteCommand(ExecuteCommand executeCommand) {
		this.fExecuteCommand = executeCommand;
	}

	/**
	 * @return the fExecuteCommand
	 */
	public ExecuteCommand getExecuteCommand() {
		return fExecuteCommand;
	}

	/**
	 * Compares the fGDBStackFrames with passed MIFrame.
	 * @param miFrame
	 * @return
	 */
	public GDBStackFrame getFrame(MIFrame miFrame) {
		// TODO Auto-generated method stub
		for (GDBStackFrame gdbStackFrame : fGDBStackFrames) {
			if (gdbStackFrame.compareMIFrame(miFrame)) {
				return gdbStackFrame;
			}
		}
		return null;
	}
}
