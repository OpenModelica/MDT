package org.modelica.mdt.debug.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IDebugTarget;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.DebugElement;

/**
 * Common function of MDT debug model elements
 */


public class MDTDebugElement extends DebugElement {

	/**
	 * Constructs a new debug element in the given target.
	 * 
	 * @param target debug target
	 */
	public MDTDebugElement(IDebugTarget target) {
		super(target);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IDebugElement#getModelIdentifier()
	 */
	public String getModelIdentifier() {
		return MDTDebugCorePlugin.ID_MDT_DEBUG_MODEL;
	}
	
	/**
	 * @param request command
	 * @return reply
	 * @throws DebugException if the request fails
	 */	
	public String sendRequest(String request) throws DebugException {
		return getMDTDebugTarget().sendRequest(request);
	}

	/**
	 * @param request signal
	 * @return reply
	 * @throws DebugException if the request fails
	 */	
	public void sendSignal(String signal) throws DebugException {
		getMDTDebugTarget().sendSignal(signal);
	}
	
	/**
	 * Returns the debug target as a MDT target.
	 * 
	 * @return MDT debug target
	 */
	protected MDTDebugTarget getMDTDebugTarget() {
	    return (MDTDebugTarget) getDebugTarget();
	}
	
	/**
	 * Returns the breakpoint manager
	 * 
     * @return the breakpoint manager
     */
    protected IBreakpointManager getBreakpointManager() {
        return DebugPlugin.getDefault().getBreakpointManager();
    }	
}
