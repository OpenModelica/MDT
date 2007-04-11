package org.modelica.mdt.debug.ui.editor;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;
import org.modelica.mdt.debug.core.model.MDTDebugTarget;
import org.modelica.mdt.debug.core.model.MDTStackFrame;
import org.modelica.mdt.debug.core.model.MDTThread;
import org.modelica.mdt.ui.text.ModelicaWordFinder;

/**
 * Produces debug hover for the MDT debugger.
 */
public class DebugTextHover implements ITextHover {

    /* (non-Javadoc)
     * @see org.eclipse.jface.text.ITextHover#getHoverInfo(org.eclipse.jface.text.ITextViewer, org.eclipse.jface.text.IRegion)
     */
    public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
        String varName = null;
        try {
            varName = textViewer.getDocument().get(hoverRegion.getOffset(), hoverRegion.getLength());
        } catch (BadLocationException e) {
           return null;
        }
        if (varName.startsWith("$") && varName.length() > 1) {
            varName = varName.substring(1);
        }
   
        MDTStackFrame frame = null;
        IAdaptable debugContext = DebugUITools.getDebugContext();
        if (debugContext instanceof MDTStackFrame) {
           frame = (MDTStackFrame) debugContext;
        } else if (debugContext instanceof MDTThread) {
            MDTThread thread = (MDTThread) debugContext;
            try {
                frame = (MDTStackFrame) thread.getTopStackFrame();
            } catch (DebugException e) {
                return null;
            }
        } else if (debugContext instanceof MDTDebugTarget) {
            MDTDebugTarget target = (MDTDebugTarget) debugContext;
            try {
                IThread[] threads = target.getThreads();
                if (threads.length > 0) {
                    frame = (MDTStackFrame) threads[0].getTopStackFrame();
                }
            } catch (DebugException e) {
                return null;
            }
        }
        if (frame != null) {
            try {
                IVariable[] variables = frame.getVariables();
                for (int i = 0; i < variables.length; i++) {
                    IVariable variable = variables[i];
                    if (variable.getName().equals(varName)) {
                        return varName + " = " + variable.getValue().getValueString(); 
                    }
                }
            } catch (DebugException e) {
            }
        }
        return null;
    }
    	
    /* (non-Javadoc)
     * @see org.eclipse.jface.text.ITextHover#getHoverRegion(org.eclipse.jface.text.ITextViewer, int)
     */
    public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
        return ModelicaWordFinder.findWord(textViewer.getDocument(), offset);
    }

}
