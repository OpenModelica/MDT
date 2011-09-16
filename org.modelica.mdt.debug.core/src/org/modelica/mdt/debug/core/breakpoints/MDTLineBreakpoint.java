package org.modelica.mdt.debug.core.breakpoints;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.LineBreakpoint;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.core.launcher.IMDTConstants;
import org.modelica.mdt.debug.core.model.IMDTDebugCommands;
import org.modelica.mdt.debug.core.model.IMDTEventListener;
import org.modelica.mdt.debug.core.model.MDTDebugTarget;
import org.modelica.mdt.debug.core.model.MDTThread;
import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.mi.MISession;
import org.modelica.mdt.debug.gdb.core.mi.command.CommandFactory;
import org.modelica.mdt.debug.gdb.core.mi.command.MIBreakDelete;
import org.modelica.mdt.debug.gdb.core.mi.command.MIBreakInsert;
import org.modelica.mdt.debug.gdb.core.mi.event.MIBreakpointHitEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MIEvent;
import org.modelica.mdt.debug.gdb.core.mi.output.MIBreakInsertInfo;
import org.modelica.mdt.debug.gdb.core.mi.output.MIBreakpoint;
import org.modelica.mdt.debug.gdb.core.model.GDBDebugTarget;
import org.modelica.mdt.debug.gdb.core.model.thread.GDBThread;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IThread;

/**
 * MDT line breakpoint
 */

public class MDTLineBreakpoint extends LineBreakpoint implements IMDTEventListener, Observer {
	
	// target currently installed in
	private MDTDebugTarget fTarget;
	// GDB Debug Target
	private GDBDebugTarget fGDBTarget;
	private int fBreakPointNumber;
	
	/**
	 * Default constructor is required for the breakpoint manager
	 * to re-create persisted breakpoints. After instantiating a breakpoint,
	 * the <code>setMarker(...)</code> method is called to restore
	 * this breakpoint's attributes.
	 */
	public MDTLineBreakpoint() {
	}
	
	/**
	 * Constructs a line breakpoint on the given resource at the given
	 * line number. The line number is 1-based (i.e. the first line of a
	 * file is line number 1). The MDT VM uses 0-based line numbers,
	 * so this line number translation is done at breakpoint install time.
	 * 
	 * @param resource file on which to set the breakpoint
	 * @param lineNumber 1-based line number of the breakpoint
	 * @throws CoreException if unable to create the breakpoint
	 */
	public MDTLineBreakpoint(final IResource resource, final int lineNumber) throws CoreException {
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				IMarker marker = resource.createMarker("org.modelica.mdt.debug.core.markerType.lineBreakpoint");
				marker.setAttribute(IBreakpoint.ENABLED, Boolean.TRUE);
				marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
				marker.setAttribute(IBreakpoint.ID, getModelIdentifier());
				marker.setAttribute(IMarker.MESSAGE, "Line Breakpoint: " + resource.getName() + " [line: " + lineNumber + "]");
				setMarker(marker);
				setAttribute(IBreakpoint.ENABLED, Boolean.TRUE);
				setRegistered(true);
			}
		};
		run(getMarkerRule(resource), runnable);
	}
		
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IBreakpoint#getModelIdentifier()
	 */
	public String getModelIdentifier() {
		return MDTDebugCorePlugin.ID_MDT_DEBUG_MODEL;
	}
	
	/**
	 * Returns whether this breakpoint is a run-to-line breakpoint
	 * 
	 * @return whether this breakpoint is a run-to-line breakpoint
	 */
	public boolean isRunToLineBreakpoint() {
		return false;
	}
    
    /**
     * Installs this breakpoint in the given interprettor.
     * Registeres this breakpoint as an event listener in the
     * given target and creates the breakpoint specific request.
     * 
     * @param target MDT interprettor
     * @throws CoreException if installation fails
     */
    public void install(MDTDebugTarget target) throws CoreException {
    	fTarget = target;
    	target.addEventListener(this);
    	createRequest(target);
    }
    
    /**
     * Inserts the breakpoint in gdb.
     * Registers this breakpoint as an observer of MISession. 
     * Sends the -break-insert command to gdb.
     * 
     * @param target GDB Debug Target
     * @throws CoreException if installation fails
     * @throws MIException 
     */
    public void insertBreakpoint(GDBDebugTarget target) throws CoreException, MIException {
    	fGDBTarget = target;
    	getGDBDebugTarget().getMISession().addObserver(this);
    	// insert the break point in gdb now.
    	String lineBreakpoint = getMarker().getResource().getName() + ":" + getLineNumber();
	    MIBreakInsert breakInsertCmd = getGDBDebugTarget().getMISession().getCommandFactory().createMIBreakInsert(lineBreakpoint);
	    getGDBDebugTarget().getMISession().postCommand(breakInsertCmd);
	    MIBreakInsertInfo breakInsertinfo = breakInsertCmd.getMIBreakInsertInfo();
		if (breakInsertinfo == null) {
			throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
				MDTDebugCorePlugin.getResourceString("MDTLineBreakpoint.removeBreakPoint.BreakInsert.NoAnswer"), null));
		}
		// get the breakpoint number
		if (breakInsertinfo.getMIBreakpoints().length > 0) {
			// we only read the zero index result since -break-insert filename:linenumber only add one breakpoint
			MIBreakpoint breakPoint = breakInsertinfo.getMIBreakpoints()[0]; 
			setBreakPointNumber(breakPoint.getNumber());
		}
		breakInsertinfo.getMIBreakpoints();
    }
    
    /**
     * Create the breakpoint specific request in the target. Subclasses
     * should override.
     * 
     * @param target MDT interprettor
     * @throws CoreException if request creation fails
     */
    protected void createRequest(MDTDebugTarget target) throws CoreException {
    	target.sendRequest(
    			IMDTDebugCommands.CMD_BREAKPOINT + " " + 
    			getMarker().getResource().getName() + ":" + 
    			(getLineNumber()));
    }
    
    /**
     * Removes this breakpoint's event request from the target. Subclasses
     * should override.
     * 
     * @param target MDT interprettor
     * @throws CoreException if clearing the request fails
     */
    protected void clearRequest(MDTDebugTarget target) throws CoreException {
    	target.sendRequest(
    			IMDTDebugCommands.CMD_DELETE_BREAKPOINT + " " + 
    			getMarker().getResource().getName() + ":" + 
    			(getLineNumber()));
    }
    
    /**
     * Removes this breakpoint from the given interpreter.
     * Removes this breakpoint as an event listener and clears
     * the request for the interpreter.
     * 
     * @param target MDT interpreter
     * @throws CoreException if removal fails
     */
    public void remove(MDTDebugTarget target) throws CoreException {
    	target.removeEventListener(this);
    	clearRequest(target);
    	fTarget = null;
    }
    
    /**
     * 
     * Removes the breakpoint in gdb.
     * Unregisters this breakpoint as an observer of MISession. 
     * Sends the -break-delete command to gdb.
     * 
	 * @param gdbDebugTarget
     * @throws MIException 
     * @throws CoreException 
	 */
	public void removeBreakpoint(GDBDebugTarget gdbDebugTarget) throws MIException, CoreException {
		// TODO Auto-generated method stub
		MISession miSession = getGDBDebugTarget().getMISession();
		miSession.deleteObserver(this);
		CommandFactory factory = miSession.getCommandFactory();
		MIBreakDelete breakDeleteCmd = factory.createMIBreakDelete(new int[]{getBreakPointNumber()});
		miSession.postCommand(breakDeleteCmd, -1);
		/*
		 * Adeel 2011-09-16 20:58
		 * Not interested in this output. Don't wait for it.
		 */
//		if (breakDeleteCmd.getMIInfo() == null) {
//			throw new CoreException(new Status(IStatus.ERROR, IMDTConstants.ID_MDT_DEBUG_MODEL, 0,
//				MDTDebugCorePlugin.getResourceString("MDTLineBreakpoint.removeBreakPoint.BreakDelete.NoAnswer"), null));
//		}
		fGDBTarget = null;
	}
    
    /**
     * Returns the target this breakpoint is installed in or <code>null</code>.
     * 
     * @return the target this breakpoint is installed in or <code>null</code>
     */
    protected MDTDebugTarget getDebugTarget() {
    	return fTarget;
    }
    
    /**
     * Returns the GDB target this breakpoint is inserted in or <code>null</code>.
     * 
     * @return the GDB target this breakpoint is inserted in or <code>null</code>
     */
    protected GDBDebugTarget getGDBDebugTarget() {
    	return fGDBTarget;
    }
    
    /**
     * Notify's the MDT interprettor that this breakpoint has been hit.
     */
    protected void notifyThread() {
    	if (fTarget != null) {
			try {
				IThread[] threads = fTarget.getThreads();
				if (threads.length == 1) {
	    			MDTThread thread = (MDTThread)threads[0];
	    			thread.suspendedBy(this);
	    		}
			} catch (DebugException e) {
			}    		
    	}
    }
    
    /**
     * Notify's the MDT interpretor that this breakpoint has been hit.
     */
    protected void notifyGDBThread() {
    	if (fGDBTarget != null) {
			try {
				IThread[] threads = fGDBTarget.getThreads();
				if (threads.length == 1) {
	    			GDBThread thread = (GDBThread)threads[0];
	    			thread.suspendedBy(this);
	    		}
			} catch (DebugException e) {
			}
    	}
    }

	/* (non-Javadoc)
	 * 
	 * Subclasses should override to handle their breakpoint specific event.
	 * 
	 * @see example.debug.core.model.IMDTEventListener#handleEvent(java.lang.String)
	 */
	public void handleEvent(String event) 
	{
		if (event.startsWith(IMDTDebugCommands.EVENT_BREAKPOINT)) 
		{
			System.out.println("MDTLineBreakpoint RECEIVED EVENT: " + event); System.out.flush();
			handleHit(event);
		}
	}
    
	/**
     * Determines if this breakpoint was hit and notifies the thread.
     * 
     * @param event breakpoint event
     */
    private void handleHit(String event) 
    {
		int column = event.indexOf(':');    	
		String file = event.substring(IMDTDebugCommands.EVENT_BREAKPOINT.length(),column);
		String line = event.substring(column+1);
		int lineNumber = Integer.parseInt(line);
		System.out.println("Breakpoint on " + file + " line: "+ line);		
		// breakpoints event line numbers are 0 based, model objects are 1 based
		try {
			if (getLineNumber() == lineNumber && 
				getMarker().getResource().getName()==file) 
			{				
				notifyThread();
			}
		} catch (CoreException e) {
		}
    }

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable ar, Object event) {
		// TODO Auto-generated method stub
		MIEvent miEvent = (MIEvent)event;

		if (miEvent instanceof MIBreakpointHitEvent) {
			//getGDBDebugTarget().suspend();
			handleBreakpointHit((MIBreakpointHitEvent)event);
		}
	}

	/**
	 * @param event
	 */
	private void handleBreakpointHit(MIBreakpointHitEvent event) {
		// TODO Auto-generated method stub
		String file = new File(event.getMIFrame().getFile()).getName();
		int lineNumber = event.getMIFrame().getLine();
		try {
			if (getLineNumber() == lineNumber && getMarker().getResource().getName().compareTo(file) == 0)
			{
				notifyGDBThread();
			}
		} catch (CoreException e) {
		}
	}

	/**
	 * @param fBreakPointNumber the fBreakPointNumber to set
	 */
	public void setBreakPointNumber(int fBreakPointNumber) {
		this.fBreakPointNumber = fBreakPointNumber;
	}

	/**
	 * @return the fBreakPointNumber
	 */
	public int getBreakPointNumber() {
		return fBreakPointNumber;
	}	
}
