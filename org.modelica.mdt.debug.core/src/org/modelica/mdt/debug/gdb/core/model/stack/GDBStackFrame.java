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
package org.modelica.mdt.debug.gdb.core.model.stack;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.core.launcher.IMDTConstants;
import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.mi.MISession;
import org.modelica.mdt.debug.gdb.core.mi.command.CommandFactory;
import org.modelica.mdt.debug.gdb.core.mi.command.MIStackListVariables;
import org.modelica.mdt.debug.gdb.core.mi.command.MIStackSelectFrame;
import org.modelica.mdt.debug.gdb.core.mi.output.MIArg;
import org.modelica.mdt.debug.gdb.core.mi.output.MIFrame;
import org.modelica.mdt.debug.gdb.core.mi.output.MIStackListVariablesInfo;
import org.modelica.mdt.debug.gdb.core.model.GDBDebugElement;
import org.modelica.mdt.debug.gdb.core.model.thread.GDBThread;
import org.modelica.mdt.debug.gdb.core.model.variable.GDBVariable;
import org.modelica.mdt.debug.gdb.helper.GDBHelper;
import org.modelica.mdt.debug.gdb.helper.TypeHelper;
import org.modelica.mdt.debug.gdb.helper.VariableHelper;

/**
 * @author Adeel Asghar
 *
 */
/**
 * MDT stack frame.
 */
public class GDBStackFrame extends GDBDebugElement implements IStackFrame {
	
	private GDBThread fGDBThread;
	private MIFrame fFrame;
	private String fName = "Unknown";
	private int fLineNumber = -1;
	private int fStartChar = -1;
	private int fEndChar = -1;	
	private String fFileName;
	private int fId;
	private List<GDBVariable> fGDBVariables = null;
	private Boolean fRefreshVariables = true;
	/**
	 * Need this flag to prevent evaluations on disposed frames. 
	 */
	private boolean fIsDisposed = false;
	
	/**
	 * Constructs a stack frame in the given thread with the given
	 * frame data.
	 * 
	 * @param thread
	 * @param frame stack frame id (0 is the bottom of the stack)
	 */
	public GDBStackFrame(GDBThread thread, MIFrame frame) {
		super(thread.getGDBDebugTarget());
		fGDBThread = thread;
		fFrame = frame;
		initialize();
	}
	
	/**
	 * Initializes this frame based on its data
	 *
	 * @param data
	 */
	protected synchronized void initialize() {
		fId = fFrame.getLevel();
		fFileName = (new Path(fFrame.getFile())).lastSegment();
		fLineNumber = fFrame.getLine();
		fStartChar = -1;
		fEndChar = -1;
		/* for .mo files we have function names concatenated with file name like _Main_main but other files are normal
		 * so in order to get the function name for .mo file we need to do some manipulations
		 */
		
		if (GDBHelper.isCFile(fFrame.getFile())) {
			fName = fFrame.getFunction();
		} else {
			String[] fileName = fFileName.split("\\.");
			int beginIndex = fileName[0].length() + 2;
			fName = fFrame.getFunction().substring(beginIndex);
		}
	}

	/**
	 * @param variablesList
	 */
	private void compareVariables(List<MIArg> variablesList) {
		// TODO Auto-generated method stub
		for (MIArg miArg : variablesList) {
			GDBVariable gdbVariable = getGDBVariable(miArg.getName());
			if (gdbVariable == null) {
				createVariable(miArg);
			} else {
				gdbVariable.setRefreshValue(true);
				if (gdbVariable.getGDBValue() != null) {
					gdbVariable.getGDBValue().setRefreshChildren(true);
				}
			}
		}
	}

	/**
	 * @param miArg
	 */
	private void createVariable(MIArg miArg) {
		// TODO Auto-generated method stub
		String referenceType = TypeHelper.getModelicaType(miArg.getName(), miArg.getType(), getGDBDebugTarget());
		String displayName = miArg.getName();
		/* since all the variables we are interested in starts with underscore 
		 * so remove the underscore
		 */
		displayName = miArg.getName().substring(1, miArg.getName().length());
		// based on the modelica type create the specific variable.
		VariableHelper.createVariable(this, miArg.getName(), displayName, miArg.getType(),
				referenceType, null, null, fGDBVariables);
	}

	/**
	 * @param name
	 * @return
	 */
	private GDBVariable getGDBVariable(String name) {
		// TODO Auto-generated method stub
		for (GDBVariable variable : fGDBVariables) {
			if (variable.getOriginalName().equals(name)) {
				return variable;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStackFrame#getThread()
	 */
	public IThread getThread() {
		return fGDBThread;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStackFrame#getVariables()
	 */
	public IVariable[] getVariables() throws DebugException {
		if (isDisposed()) {
			return new IVariable[0];
		}
		computeVariables();
		return (IVariable[])fGDBVariables.toArray(new IVariable[fGDBVariables.size()]);
	}
	/**
	 * 
	 */
	private void computeVariables() {
		// TODO Auto-generated method stub
		if (fGDBVariables == null) {
			fGDBVariables = new ArrayList<GDBVariable>();
		}
		if (isRefreshVariables()) {
			/* get variables from GDB for this stack
			 * since MDT is single threaded we always use thread=1
			 */
			MISession miSession = getGDBDebugTarget().getMISession();
			CommandFactory factory = miSession.getCommandFactory();
			MIArg[] args = null;
			MIStackListVariablesInfo stackListVariablesInfo = null;
			MIStackListVariables stackListVariablesCmd = factory.createMIStackListVariables(new String[]{"--thread", "1", "--frame", Integer.toString(getIdentifier()), "--simple-values"});
			try {
				miSession.postCommand(stackListVariablesCmd);
				stackListVariablesInfo = stackListVariablesCmd.getMIStackListVariablesInfo();
				if (stackListVariablesInfo == null) {
					throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
							MDTDebugCorePlugin.getResourceString("GDBStackFrame.computeVariables.StackListVariables.NoAnswer"), null));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				MDTDebugCorePlugin.log(null, e);
			}
			args = stackListVariablesInfo.getLocals();
			List<MIArg> variablesList = new ArrayList<MIArg>();
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					if (args[i].getName().startsWith("_")) {
						variablesList.add(args[i]);
					}
				}
			}
			// first remove the variables that are removed from this frame
			VariableHelper.removeVariables(variablesList, fGDBVariables);
			// compare and create IVariable
			compareVariables(variablesList);
			setRefreshVariables(false);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStackFrame#hasVariables()
	 */
	public boolean hasVariables() throws DebugException {
		return (isDisposed()) ? false : (getVariables().length > 0);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStackFrame#getLineNumber()
	 */
	public int getLineNumber() throws DebugException {
		return fLineNumber;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStackFrame#getCharStart()
	 */
	public int getCharStart() throws DebugException {
		return fStartChar;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStackFrame#getCharEnd()
	 */
	public int getCharEnd() throws DebugException {
		return fEndChar;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStackFrame#getName()
	 */
	public String getName() throws DebugException {
		return fName;
	}	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStackFrame#getRegisterGroups()
	 */
	public IRegisterGroup[] getRegisterGroups() throws DebugException {
		return null;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStackFrame#hasRegisterGroups()
	 */
	public boolean hasRegisterGroups() throws DebugException {
		return false;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#canStepInto()
	 */
	public boolean canStepInto() {
		return getThread().canStepInto();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#canStepOver()
	 */
	public boolean canStepOver() {
		return getThread().canStepOver();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#canStepReturn()
	 */
	public boolean canStepReturn() {
		return getThread().canStepReturn();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#isStepping()
	 */
	public boolean isStepping() {
		return getThread().isStepping();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#stepInto()
	 */
	public void stepInto() throws DebugException {
		getThread().stepInto();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#stepOver()
	 */
	public void stepOver() throws DebugException {
		getThread().stepOver();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IStep#stepReturn()
	 */
	public void stepReturn() throws DebugException {
		getThread().stepReturn();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ISuspendResume#canResume()
	 */
	public boolean canResume() {
		return getThread().canResume();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ISuspendResume#canSuspend()
	 */
	public boolean canSuspend() {
		return getThread().canSuspend();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ISuspendResume#isSuspended()
	 */
	public boolean isSuspended() {
		return getThread().isSuspended();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ISuspendResume#resume()
	 */
	public void resume() throws DebugException {
		getThread().resume();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ISuspendResume#suspend()
	 */
	public void suspend() throws DebugException {
		getThread().suspend();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ITerminate#canTerminate()
	 */
	public boolean canTerminate() {
		return getThread().canTerminate();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ITerminate#isTerminated()
	 */
	public boolean isTerminated() {
		return getThread().isTerminated();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.ITerminate#terminate()
	 */
	public void terminate() throws DebugException {
		getThread().terminate();
	}
	
	/**
	 * Returns the name of the source file this stack frame is associated
	 * with.
	 * 
	 * @return the name of the source file this stack frame is associated
	 * with
	 */
	public String getSourceName() {
		return fFileName;
	}
	/**
	 * Returns the name of the source file this stack frame is associated
	 * with.
	 * 
	 * @return the name of the source file this stack frame is associated
	 * with
	 */
	public String getFullSourceName() {
		IPath workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
		IPath filePath = new Path(getMIFrame().getFile());
		return filePath.removeFirstSegments(workspacePath.segmentCount() + 2).toOSString();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof GDBStackFrame) {
			GDBStackFrame sf = (GDBStackFrame)obj;
			return sf.getThread().equals(getThread()) && 
				sf.getSourceName().equals(getSourceName()) &&
				sf.fStartChar == fStartChar &&
				sf.fEndChar == fEndChar &&
				sf.fLineNumber == fLineNumber &&
				sf.fId == fId;/* &&
				sf.fCallType.equals(fCallType);*/
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return getSourceName().hashCode() + fStartChar + fEndChar + fLineNumber + fId;
	}
	
	/**
	 * Returns this stack frame's unique identifier within its thread
	 * 
	 * @return this stack frame's unique identifier within its thread
	 */
	public int getIdentifier() {
		return fId;
	}

	/**
	 * @return
	 */
	public MIFrame getMIFrame() {
		// TODO Auto-generated method stub
		return fFrame;
	}

	/**
	 * @param miFrame
	 * @return
	 */
	public boolean compareMIFrame(MIFrame miFrame) {
		// TODO Auto-generated method stub
		if (getMIFrame().getFile().equals(miFrame.getFile())) {
			if (getMIFrame().getFunction().equals(miFrame.getFunction())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param miFrame
	 */
	public void updateMe(MIFrame miFrame) {
		// TODO Auto-generated method stub
		fFrame = miFrame;
		initialize();
		setRefreshVariables(true);
	}

	/**
	 * Clears all the variables inside the stack frame
	 * 
	 */
	public void dispose() {
		// TODO Auto-generated method stub
		setDisposed(true);
		if (fGDBVariables == null) {
			return;
		}
		Iterator<GDBVariable> it = fGDBVariables.iterator();
		while (it.hasNext()) {
			((GDBVariable)it.next()).dispose();
		}
		fGDBVariables.clear();
		fGDBVariables = null;
	}
	
	protected boolean isDisposed() {
		return fIsDisposed;
	}

	private synchronized void setDisposed(boolean isDisposed) {
		fIsDisposed = isDisposed;
	}

	/**
	 * 
	 * Makes this frame the current frame for GDB.
	 * @throws MIException 
	 * @throws CoreException 
	 * 
	 */
	public void setCurrentFrame() throws MIException, CoreException {
		// TODO Auto-generated method stub
		MISession miSession = getGDBDebugTarget().getMISession();
		CommandFactory factory = miSession.getCommandFactory();
		MIStackSelectFrame miStackSelectFrameCmd = factory.createMIStackSelectFrame(getIdentifier());
		miSession.postCommand(miStackSelectFrameCmd);
		if (miStackSelectFrameCmd.getMIInfo() == null) {
			throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
					MDTDebugCorePlugin.getResourceString("GDBThread.setCurrentStackFrame.StackSelectFrame.NoAnswer"), null));
		}
	}

	/**
	 * @param refreshVariables the fRefreshVariables to set
	 */
	public void setRefreshVariables(Boolean refreshVariables) {
		this.fRefreshVariables = refreshVariables;
	}

	/**
	 * @return the fRefreshVariables
	 */
	public Boolean isRefreshVariables() {
		return fRefreshVariables;
	}
}
