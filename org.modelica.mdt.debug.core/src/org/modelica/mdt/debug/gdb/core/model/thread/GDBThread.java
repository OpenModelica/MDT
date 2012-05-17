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
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
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
import org.modelica.mdt.debug.gdb.core.mi.command.MIBreakDelete;
import org.modelica.mdt.debug.gdb.core.mi.command.MIBreakDisable;
import org.modelica.mdt.debug.gdb.core.mi.command.MIBreakEnable;
import org.modelica.mdt.debug.gdb.core.mi.command.MIBreakInsert;
import org.modelica.mdt.debug.gdb.core.mi.command.MIDataEvaluateExpression;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecContinue;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecFinish;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecNext;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecRun;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecStep;
import org.modelica.mdt.debug.gdb.core.mi.command.MIStackInfoDepth;
import org.modelica.mdt.debug.gdb.core.mi.command.MIStackListFrames;
import org.modelica.mdt.debug.gdb.core.mi.command.MIStackSelectFrame;
import org.modelica.mdt.debug.gdb.core.mi.output.MIBreakInsertInfo;
import org.modelica.mdt.debug.gdb.core.mi.output.MIBreakpoint;
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
	public List<GDBStackFrame> fGDBStackFrames = null;
	private GDBStackFrame fCurrentGDBStackFrame = null;
	private final static int MAX_STACK_DEPTH = 100;
	private Boolean fRefreshStackFrames = true;
	public enum ExecuteCommand {
		EXECNEXT,
		EXECSTEP;
	}
	private ExecuteCommand fExecuteCommand = ExecuteCommand.EXECSTEP;
	private int fLastStackDepth = 0;
	/**
	 * Needed to handle deletion of Catch.omc:1 breakpoint
	 */
	private int fCatchOMCBreakpoint = 0;
	/**
	 * Constructs a new thread for the given target
	 * 
	 * @param target VM
	 */
	public GDBThread(GDBDebugTarget target) {
		super(target);
		fGDBStackFrames = new ArrayList<GDBStackFrame>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IThread#getStackFrames()
	 */
	public synchronized IStackFrame[] getStackFrames() throws DebugException {
		computeStackFrames();
		return (IStackFrame[])fGDBStackFrames.toArray(new IStackFrame[fGDBStackFrames.size()]);
	}

	/**
	 * @param lowFrame
	 * @param highFrame
	 * @return 
	 * @return
	 */
	public synchronized MIFrame[] getStackFrames(int lowFrame, int highFrame) {
		// TODO Auto-generated method stub
		try {
			// get the list of frames from GDB
			MISession miSession = getGDBDebugTarget().getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			MIStackListFrames stackListFramesCmd = factory.createMIStackListFrames(lowFrame, highFrame);
			miSession.postCommand(stackListFramesCmd, null);
			MIStackListFramesInfo stackListFramesInfo = stackListFramesCmd.getMIStackListFramesInfo();
			if (stackListFramesInfo == null) {
				throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
						MDTDebugCorePlugin.getResourceString("GDBThread.getStackFrames.StackListFrames.NoAnswer"), null));
			}
			// filter c files
			return removeCStackFrames(stackListFramesInfo.getMIFrames());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MDTDebugCorePlugin.log(null, e);
		}
		return null;
	}

	/**
	 * @param frames
	 * @return
	 * @throws CoreException 
	 */
	public MIFrame[] removeCStackFrames(MIFrame[] frames) throws CoreException {
		// TODO Auto-generated method stub
		int removeCounter = 0;
		List<MIFrame> miFramesList = new ArrayList<MIFrame>(Arrays.asList(frames));
		List<MIFrame> removedList = new ArrayList<MIFrame>();
		
		for (MIFrame miFrame : miFramesList) {
			if (GDBHelper.filterCFiles(getGDBDebugTarget(), miFrame)) {
				removedList.add(miFrame);
				removeCounter++;
			} else {
				miFrame.setLevel(miFrame.getLevel() - removeCounter);
				removeCounter = 0;
			}
		}
		
		miFramesList.removeAll(removedList);
		return (MIFrame[])miFramesList.toArray(new MIFrame[miFramesList.size()]);
	}	

	/**
	 * Computes this thread's current stack frames as a list, computing them if required.
	 * 
	 */
	protected synchronized void computeStackFrames() {
		// TODO Auto-generated method stub
		synchronized (getGDBDebugTarget().getLock()) {
			if (isSuspended()) {
				if (isRefreshStackFrames()) {
					try {
						// get the stack depth
						int depth = getStackInfoDepth();
						if (depth >= getMaxStackDepth())
							depth = getMaxStackDepth() - 1;
						// get the stack frames from GDB
						MIFrame[] frames = getStackFrames(0, depth - 1);
						// Safety precaution in case getting the stack frames failed to get us as many as it said
						depth = frames.length;
						
						if (fGDBStackFrames.isEmpty()) {
							if (frames.length > 0) {
								addStackFrames(frames, 0, frames.length, false, depth);
							}
						} else {
							int diff = depth - getLastStackDepth();
							int offset = (diff > 0) ? frames.length - diff : 0;
							int length = (diff > 0) ? diff : -diff;
							if (offset < 0 || !compareStackFrames(frames, fGDBStackFrames, offset, length)) {
								// replace all frames
								disposeStackFrames(0, fGDBStackFrames.size());
								addStackFrames(frames, 0, frames.length, false, depth);						
							}
							if (diff < 0) {
								// stepping out of the last frame
								disposeStackFrames(0, getLastStackDepth() - depth);
								if (frames.length > 0) {
									updateStackFrames(frames, 0, fGDBStackFrames, fGDBStackFrames.size(), depth);
									if (fGDBStackFrames.size() < frames.length) {
										addStackFrames(frames, fGDBStackFrames.size(),
												frames.length - fGDBStackFrames.size(), true, depth);
									}
								}
							}
							else if (diff > 0) {
								// stepping into a new frame
								disposeStackFrames(frames.length - depth + getLastStackDepth(),
										depth - getLastStackDepth());
								addStackFrames(frames, 0, depth - getLastStackDepth(), false, depth);
								updateStackFrames(frames, depth - getLastStackDepth(),
										fGDBStackFrames, frames.length - depth + getLastStackDepth(), depth);
							}
							else { // diff == 0
								if (depth != 0) {
									// we are in the same frame
									updateStackFrames(frames, 0, fGDBStackFrames, frames.length, depth);
								}
							}
						}
						setLastStackDepth(depth);
						if (fGDBStackFrames.size() > 0) {
							setCurrentGDBStackFrame(fGDBStackFrames.get(0));
						}
						setRefreshStackFrames(false);
					} catch (Exception e) {
						// TODO: handle exception
						MDTDebugCorePlugin.log(null, e);
					}
				}
			}
		}
	}	

	/**
	 * Replaces the MIFrame objects in the preserved frames list with the current MIFrame objects.
	 * 
	 * @param newFrames
	 * @param offset
	 * @param oldFrames
	 * @param length
	 * @param depth 
	 */
	private void updateStackFrames(MIFrame[] newFrames, int offset, List<GDBStackFrame> oldFrames, int length, int depth) {
		// TODO Auto-generated method stub
		for( int i = 0; i < length; i++ ) {
			GDBStackFrame frame = oldFrames.get(offset);
			frame.updateMe(newFrames[offset], depth);
			offset++;
		}
	}
	
	/**
	 * Disposes stack frames, to be completely re-computed on the next suspend event.
	 * This method should be called before this thread is resumed when stack
	 * frames are not to be re-used on the next suspend.
	 */
	protected synchronized void disposeStackFrames() {
		Iterator<GDBStackFrame> it = fGDBStackFrames.iterator();
		while (it.hasNext()) {
			GDBStackFrame obj = it.next();
			obj.dispose();
		}
		fGDBStackFrames.clear();
		setLastStackDepth(0);
		setRefreshStackFrames(true);
	}

	/**
	 * @param index
	 * @param length
	 */
	private void disposeStackFrames(int index, int length) {
		// TODO Auto-generated method stub
		List<GDBStackFrame> removeList = new ArrayList<GDBStackFrame>(length);
		Iterator<GDBStackFrame> it = fGDBStackFrames.iterator();
		int counter = 0;
		while(it.hasNext()) {
			GDBStackFrame gdbStackFrame = it.next();
			if (gdbStackFrame != null && counter >= index && counter < index + length) {
				gdbStackFrame.dispose();
				removeList.add(gdbStackFrame);
			}
			++counter;
		}
		fGDBStackFrames.removeAll(removeList);
	}

	/**
	 * Compares the lists of the old and new frames.
	 * 
	 * @param newFrames
	 * @param oldFrames
	 * @param offset
	 * @param length
	 * @return
	 */
	private boolean compareStackFrames(MIFrame[] newFrames, List<GDBStackFrame> oldFrames, int offset,
			int length) {
		// TODO Auto-generated method stub
		if (offset < 0) {
			return false;
		}
		int index = offset;
		Iterator<GDBStackFrame> it = oldFrames.iterator();
		while(it.hasNext() && index < newFrames.length) {
			GDBStackFrame frame = it.next();
			if (!frame.compareMIFrame(newFrames[index++]))
				return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	private int getLastStackDepth() {
		// TODO Auto-generated method stub
		return fLastStackDepth ;
	}
	
	private void setLastStackDepth( int depth ) {
		fLastStackDepth = depth;
	}

	/**
	 * @param frames
	 * @param i
	 * @param length
	 * @param depth 
	 * @param b
	 * @throws CoreException 
	 */
	private void addStackFrames(MIFrame[] newFrames, int startIndex, int length, boolean append, int depth) throws CoreException {
		// TODO Auto-generated method stub
		if (newFrames.length >= startIndex + length) {
			for(int i = 0; i < length; ++i) {
				if (append)
					fGDBStackFrames.add(new GDBStackFrame(this, newFrames[startIndex + i], depth));
				else
					fGDBStackFrames.add(i, new GDBStackFrame(this, newFrames[startIndex + i], depth));
			}
		}
	}

	/**
	 * @return
	 */
	public int getMaxStackDepth() {
		// TODO Auto-generated method stub
		return MAX_STACK_DEPTH;
	}

	/**
	 * @return
	 */
	public int getStackInfoDepth() {
		// TODO Auto-generated method stub
		int stackDepth = 0;
		try {
			MISession miSession = getGDBDebugTarget().getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			MIStackInfoDepth stackInfoDepthCmd = factory.createMIStackInfoDepth();
			MIStackInfoDepthInfo stackInfoDepthInfo;
			miSession.postCommand(stackInfoDepthCmd, null);
			try {
				stackInfoDepthInfo = stackInfoDepthCmd.getMIStackInfoDepthInfo();
				if (stackInfoDepthInfo == null) {
					throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
							MDTDebugCorePlugin.getResourceString("GDBThread.getStackInfoDepth.StackInfoDepth.NoAnswer"), null));
				}
				stackDepth = stackInfoDepthInfo.getDepth();
			} catch (MIException e) {
				// First try fails, retry. gdb patches up the corrupt frame
				// so retry should give us a frame count that is safe.
				stackInfoDepthCmd = factory.createMIStackInfoDepth();
				miSession.postCommand(stackInfoDepthCmd, null);
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
				stackDepth = stackInfoDepthInfo.getDepth();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				MDTDebugCorePlugin.log(null, e);
			}
		} catch (MIException e) {
			// 1 is safe to return
			return 1;
		}
		return stackDepth;
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
	public synchronized IStackFrame getTopStackFrame() throws DebugException {
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
		synchronized (getGDBDebugTarget().getLock()) {
			setRunning(true);
			setSuspended(false);
			setStepping(false);
			resumed(DebugEvent.CLIENT_REQUEST);
			// clear the stack frames completely
			disposeStackFrames();
			// send gdb the -exec-continue commmand
			try {
				MIExecContinue execContinueCmd = getGDBDebugTarget().getMISession().getCommandFactory().createMIExecContinue();
				execContinueCmd.setQuiet(true);
				getGDBDebugTarget().getMISession().postCommand(execContinueCmd, null);
				if (execContinueCmd.getMIInfo() == null) {
					throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
							MDTDebugCorePlugin.getResourceString("GDBThread.resume.ExecContinue.NoAnswer"), null));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				MDTDebugCorePlugin.log(null, e);
			}
		}
	}
	/**
	 * @throws CoreException  
	 * 
	 */
	public void start() throws CoreException {
		synchronized (getGDBDebugTarget().getLock()) {
			setRunning(true);
			setSuspended(false);
			setStepping(false);
			try {
				MISession miSession = getGDBDebugTarget().getMISession();
				CommandFactory factory = miSession.getCommandFactory();
				// send -exec-run
				MIExecRun execRunCmd = factory.createMIExecRun(new String[0]);
				miSession.postCommand(execRunCmd, null);
				if (execRunCmd.getMIInfo() == null) {
					throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
							MDTDebugCorePlugin.getResourceString("GDBThread.start.ExecRun.NoAnswer"), null));
				}
				// if run is ok then change the stdout buffer policy
				MIDataEvaluateExpression changeStdStreamBufferCmd = factory.createMIChangeStdStreamBuffer();
				// we don't care about the time and output of this command
				miSession.postCommand(changeStdStreamBufferCmd, -1, null);
				// Insert a breakpoint to catch longjmp/MMC_THROW
				insertCatchOMCBreakpoint();
			} catch (MIException e) {
				// TODO Auto-generated catch block
				MDTDebugCorePlugin.log(e.getMessage() + e.getLogMessage(), e);
			}
			getGDBDebugTarget().getMISession().getGDBInferior().update();
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ISuspendResume#suspend()
	 */
	public void suspend() throws DebugException {
		suspended(DebugEvent.CLIENT_REQUEST);
		try {
			int result = getGDBDebugTarget().getMISession().interruptInferior();
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
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#canStepInto()
	 */
	public boolean canStepInto() {
		return isStepping();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#canStepOver()
	 */
	public boolean canStepOver() {
		return isStepping();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#canStepReturn()
	 */
	public boolean canStepReturn() {
		if (isStepping()) {
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
		synchronized (getGDBDebugTarget().getLock()) {
			setRunning(true);
			setSuspended(false);
			setStepping(false);
			resumed(DebugEvent.STEP_INTO);
			/*
			 * Adeel 2011-09-20 23:31 When we do step we put the current stack frame as null.
			 * So other threads don't post commands to the inferior.
			 */
			invalidateCurrentGDBStackFrame();
			// send the -exec-step command
			MISession miSession = getGDBDebugTarget().getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			MIExecStep execStepCommand = factory.createMIExecStep();
			try {
				/*
				 * Adeel 2012-01-27 15:23 Before doing a step we should add a breakpoint 
				 * at Catch.omc:1 to handle MMC_THROW()
				 */
				enableCatchOMCBreakpoint();
				miSession.postCommand(execStepCommand, null);
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
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#stepOver()
	 */
	public void stepOver() throws DebugException {
		synchronized (getGDBDebugTarget().getLock()) {
			setRunning(true);
			setSuspended(false);
			setStepping(false);
			resumed(DebugEvent.STEP_OVER);
			/*
			 * Adeel 2011-09-20 23:31 When we do step we put the current stack frame as null.
			 * So other threads don't post commands to the inferior.
			 */
			invalidateCurrentGDBStackFrame();
			// send the -exec-next command
			CommandFactory factory = getGDBDebugTarget().getMISession().getCommandFactory();
			MIExecNext execNextCmd = factory.createMIExecNext();
			try {
				/*
				 * Adeel 2012-01-27 15:23 Before doing a step we should add a breakpoint 
				 * at Catch.omc:1 to handle MMC_THROW()
				 */
				enableCatchOMCBreakpoint();
				getGDBDebugTarget().getMISession().postCommand(execNextCmd, null);
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
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#stepReturn()
	 */
	public void stepReturn() throws DebugException {
		synchronized (getGDBDebugTarget().getLock()) {
			setRunning(true);
			setSuspended(false);
			setStepping(false);
			resumed(DebugEvent.STEP_RETURN);
			/*
			 * Adeel 2011-09-20 23:31 When we do step we put the current stack frame as null.
			 * So other threads don't post commands to the inferior.
			 */
			invalidateCurrentGDBStackFrame();
			// send the -exec-finish command
			CommandFactory factory = getGDBDebugTarget().getMISession().getCommandFactory();
			MIExecFinish execFinishCmd = factory.createMIExecFinish();
			try {
				getGDBDebugTarget().getMISession().postCommand(execFinishCmd, null);
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
	 * Notification the target has interrupted for the given reason
	 * 
	 * @param detail reason for the interrupt
	 */
	public void interrupted(int detail) {
		setStepping(false);
		setSuspended(true);
		setRunning(true);
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

	/**
	 * @param gdbStackFrame
	 * @throws MIException 
	 * @throws CoreException 
	 */
	public void setCurrentGDBStackFrame(GDBStackFrame gdbStackFrame) throws MIException, CoreException {
		// TODO Auto-generated method stub
		// Need the GDB/MI view of level which is the reverse, i.e. the highest level is 0
		// See comment in GDBStackFrame constructor.
		if (fCurrentGDBStackFrame == gdbStackFrame)
			return;
		int miFrameLevel = getLastStackDepth() - gdbStackFrame.getIdentifier();
		MISession miSession = getGDBDebugTarget().getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIStackSelectFrame miStackSelectFrameCmd = factory.createMIStackSelectFrame(miFrameLevel);
		miSession.postCommand(miStackSelectFrameCmd, null);
		if (miStackSelectFrameCmd.getMIInfo() == null) {
			throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
					MDTDebugCorePlugin.getResourceString("GDBThread.setCurrentStackFrame.StackSelectFrame.NoAnswer"), null));
		}
		fCurrentGDBStackFrame = gdbStackFrame;
	}
	
	/**
	 * @return the fCurrentGDBStackFrame
	 */
	public GDBStackFrame getCurrentGDBStackFrame() {
		return fCurrentGDBStackFrame;
	}
	
	/**
	 * Invalidates the current stack frame.
	 * When we do step we put the current stack frame as null.
	 * So other threads don't post commands to the inferior.
	 */
	private void invalidateCurrentGDBStackFrame() {
		// TODO Auto-generated method stub
		fCurrentGDBStackFrame = null;
	}
	
	/**
	 * Inserts a breakpoint at Catch.omc:1 to handle MMC_THROW()
	 * @throws MIException
	 * @throws CoreException
	 */
	public void insertCatchOMCBreakpoint() throws MIException, CoreException {
		MISession miSession = getGDBDebugTarget().getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIBreakInsert breakInsertCmd = factory.createMIBreakInsert(false, false, "", 0, true, true, "Catch.omc:1", 0);
		breakInsertCmd.setQuiet(true);
		miSession.postCommand(breakInsertCmd, null);
		MIBreakInsertInfo breakInsertinfo = breakInsertCmd.getMIBreakInsertInfo();
		if (breakInsertinfo == null) {
			throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
				MDTDebugCorePlugin.getResourceString("MDTLineBreakpoint.insertBreakPoint.BreakInsert.NoAnswer"), null));
		}
		// get the breakpoint number
		if (breakInsertinfo.getMIBreakpoints().length > 0) {
			// we only read the zero index result since -break-insert filename:linenumber only add one breakpoint
			MIBreakpoint breakPoint = breakInsertinfo.getMIBreakpoints()[0]; 
			fCatchOMCBreakpoint = breakPoint.getNumber();
		}
	}
	
	/**
	 * Enables the breakpoint at Catch.omc:1 to handle MMC_THROW()
	 * @throws MIException
	 * @throws CoreException
	 */
	public void enableCatchOMCBreakpoint() throws MIException {
		// TODO Auto-generated method stub
		if (fCatchOMCBreakpoint == 0)
			return;
		MISession miSession = getGDBDebugTarget().getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIBreakEnable breakEnableCmd = factory.createMIBreakEnable(new int[]{fCatchOMCBreakpoint});
		breakEnableCmd.setQuiet(true);
		miSession.postCommand(breakEnableCmd, null);
	}
	
	/**
	 * Disables the breakpoint at Catch.omc:1 to handle MMC_THROW()
	 * @throws MIException
	 * @throws CoreException
	 */
	public void disableCatchOMCBreakpoint() throws MIException {
		// TODO Auto-generated method stub
		if (fCatchOMCBreakpoint == 0)
			return;
		MISession miSession = getGDBDebugTarget().getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIBreakDisable breakDisableCmd = factory.createMIBreakDisable(new int[]{fCatchOMCBreakpoint});
		breakDisableCmd.setQuiet(true);
		miSession.postCommand(breakDisableCmd, null);
	}
	
	/**
	 * Deletes the breakpoint at Catch.omc:1
	 * @throws MIException 
	 */
	public void deleteCatchOMCBreakpoint() throws MIException {
		if (fCatchOMCBreakpoint == 0)
			return;
		MISession miSession = getGDBDebugTarget().getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIBreakDelete breakDeleteCmd = factory.createMIBreakDelete(new int[]{fCatchOMCBreakpoint});
		breakDeleteCmd.setQuiet(true);
		miSession.postCommand(breakDeleteCmd, null);
	}
}
