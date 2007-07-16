package org.modelica.mdt.debug.ui.presentation;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.core.breakpoints.MDTLineBreakpoint;
import org.modelica.mdt.debug.core.breakpoints.MDTWatchpoint;
import org.modelica.mdt.debug.core.model.MDTDebugTarget;
import org.modelica.mdt.debug.core.model.MDTStackFrame;
import org.modelica.mdt.debug.core.model.MDTThread;

/**
 * Renders MDT debug elements
 */
public class MDTModelPresentation extends LabelProvider implements 
	IDebugModelPresentation
	/* 
	IInstructionPointerPresentation,  
	IDebugEditorPresentation
	*/
{

	public void setAttribute(String attribute, Object value) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		if (element instanceof MDTDebugTarget) {
			return getTargetText((MDTDebugTarget)element);
		} else if (element instanceof MDTThread) {
	        return getThreadText((MDTThread)element);
	    } else if (element instanceof MDTStackFrame) {
	        return getStackFrameText((MDTStackFrame)element);
	    } else if (element instanceof MDTWatchpoint) {
	        return getWatchpointText((MDTWatchpoint)element);
	    }
		return null;
	}
	
	/**
	 * Returns a label for the given watchpoint.
	 * 
     * @param watchpoint
     * @return a label for the given watchpoint
     */
    private String getWatchpointText(MDTWatchpoint watchpoint) {
        try {
	        String label = watchpoint.getVariableName() + " (" + watchpoint.getFunctionName() + ")";
	        if (watchpoint.isAccess()) {
	            label += " [read]";
	        }
	        if (watchpoint.isModification()) {
	            label += " [write]";
	        }
	        return label;
        } catch (CoreException e) {
            return null;
        } 
    }
    /**
	 * Returns a label for the given debug target
	 * 
	 * @param target debug target
	 * @return a label for the given debug target
	 */
	private String getTargetText(MDTDebugTarget target) {
		try {
			String pgmPath = target.getLaunch().getLaunchConfiguration().getAttribute(MDTDebugCorePlugin.ATTR_MDT_PROGRAM, (String)null);
			if (pgmPath != null) {
			    IPath path = new Path(pgmPath);
			    String label = "";
			    if (target.isTerminated()) {
			    	label = "<terminated>";
			    }
			    return label + "MDT [" + path.lastSegment() + "]";
			}
		} catch (CoreException e) {
		}
		return "MDT";
		
	}
	
	/**
	 * Returns a label for the given stack frame
	 * 
	 * @param frame a stack frame
	 * @return a label for the given stack frame 
	 */
	private String getStackFrameText(MDTStackFrame frame) {
	    try {
	       return 
	       		frame.getName() + 
	       		" (line: " + frame.getLineNumber() + 
	       		", SP: " + frame.getMMCStackPointer() + ", call: " + frame.getCallType() + ")"; 
	    } catch (DebugException e) {
	    }
	    return null;

	}
	
	/**
	 * Returns a label for the given thread
	 * 
	 * @param thread a thread
	 * @return a label for the given thread
	 */
	private String getThreadText(MDTThread thread) {
	    String label = thread.getName();
	    if (thread.isStepping()) {
	        label += " (stepping)";
	    } else if (thread.isSuspended()) {
	        IBreakpoint[] breakpoints = thread.getBreakpoints();
	        if (breakpoints.length == 0) {
	        	if (thread.getError() == null) {
	        		label += " (suspended)";
	        	} else {
	        		label += " (" + thread.getError() + ")";
	        	}
	        } else {
	            IBreakpoint breakpoint = breakpoints[0]; // there can only be one in MDT
	            if (breakpoint instanceof MDTLineBreakpoint) {
	            	MDTLineBreakpoint pdaBreakpoint = (MDTLineBreakpoint) breakpoint;
	            	if (pdaBreakpoint instanceof MDTWatchpoint) {
	            	    try {
		            	    MDTWatchpoint watchpoint = (MDTWatchpoint)pdaBreakpoint;
		            	    label += " (watchpoint: " + watchpoint.getSuspendType() + " " + watchpoint.getVariableName() + ")";
	            	    } catch (CoreException e) {
	            	    }
	            	} else if (pdaBreakpoint.isRunToLineBreakpoint()) {
	            		label += " (run to line)";
	            	} else {
	            		label += " (suspended at line breakpoint)";
	            	}
	            }
	        }
	    } else if (thread.isTerminated() || thread.getDebugTarget().isTerminated()) {
	        label = "<terminated> " + label;
	    } else {
	        label += " (running)";
	    }
	    
	    return label;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.IDebugModelPresentation#computeDetail(org.eclipse.debug.core.model.IValue,
	 *      org.eclipse.debug.ui.IValueDetailListener)
	 */
	public void computeDetail(IValue value, IValueDetailListener listener) {
		String detail = "";
		try {
			detail = value.getValueString();
		} catch (DebugException e) {
		}
		listener.detailComputed(value, detail);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ISourcePresentation#getEditorInput(java.lang.Object)
	 */
	public IEditorInput getEditorInput(Object element) {
		if (element instanceof IFile) {
			// TODO! search in the project for this file
			// return new ModelicaElementEditorInput(modelicaElement);
			return new FileEditorInput((IFile) element);
		}
		if (element instanceof ILineBreakpoint) {
			return new FileEditorInput((IFile) ((ILineBreakpoint) element).getMarker().getResource());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ISourcePresentation#getEditorId(org.eclipse.ui.IEditorInput,
	 *      java.lang.Object)
	 */
	public String getEditorId(IEditorInput input, Object element) {
		if (element instanceof IFile || element instanceof ILineBreakpoint) {
			return "org.modelica.mdt.ui.editor";
		}
		return null;
	}

	/*
	public Annotation getInstructionPointerAnnotation(IEditorPart editorPart, IStackFrame frame) 
	{
		if (frame == null) return null;
		IThread thread = frame.getThread();
		if (thread == null) return null;
		IStackFrame topFrame = null;
		try
		{
			topFrame = thread.getTopStackFrame();
		}
		catch(DebugException e)
		{
			return null;
		}
		if (topFrame == null) return null;
		if (frame.equals(topFrame))
		{
			return new Annotation(IDebugUIConstants.ANNOTATION_TYPE_INSTRUCTION_POINTER_CURRENT, false, "MDT Execution Point");
		}
		return new Annotation(IDebugUIConstants.ANNOTATION_TYPE_INSTRUCTION_POINTER_SECONDARY, false, "MDT Execution Point Secondary");
	}

	public String getInstructionPointerAnnotationType(IEditorPart editorPart, IStackFrame frame) {
		if (frame == null) return null;
		IThread thread = frame.getThread();
		if (thread == null) return null;
		IStackFrame topFrame = null;
		try
		{
			topFrame = thread.getTopStackFrame();
		}
		catch(DebugException e)
		{
			return null;
		}
		if (topFrame == null) return null;
		if (frame.equals(topFrame))
		{
			return IDebugUIConstants.ANNOTATION_TYPE_INSTRUCTION_POINTER_CURRENT;
		}
		return IDebugUIConstants.ANNOTATION_TYPE_INSTRUCTION_POINTER_SECONDARY;
	}

	public Image getInstructionPointerImage(IEditorPart editorPart, IStackFrame frame) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInstructionPointerText(IEditorPart editorPart, IStackFrame frame) {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	/*
	public boolean addAnnotations(IEditorPart editorPart, IStackFrame frame) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeAnnotations(IEditorPart editorPart, IThread thread) 
	{
		if (thread == null || (thread != null &&thread.isTerminated()))
		{
			IEditorInput ei = editorPart.getEditorInput();
			try
			{				
				if (ei instanceof FileEditorInput)
				{
					((FileEditorInput)ei).getFile().deleteMarkers(
							IDebugUIConstants.ANNOTATION_TYPE_INSTRUCTION_POINTER_CURRENT, 
							true, IResource.DEPTH_INFINITE);
				}
			}
			catch(CoreException e)
			{
				ErrorManager.logError(e);
			}
		}
	}
	*/
	
}
