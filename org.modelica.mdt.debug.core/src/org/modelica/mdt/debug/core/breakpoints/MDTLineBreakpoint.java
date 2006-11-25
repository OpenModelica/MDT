package org.modelica.mdt.debug.core.breakpoints;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.LineBreakpoint;
import org.modelica.mdt.debug.core.launcher.IMDTConstants;

/**
 * RML line breakpoint
 */
public class MDTLineBreakpoint extends LineBreakpoint {

	/**
	 * Default constructor is required for the breakpoint manager to re-create
	 * persisted breakpoints. After instantiating a breakpoint, the
	 * <code>setMarker(...)</code> method is called to restore this
	 * breakpoint's attributes.
	 */
	public MDTLineBreakpoint() {
	}

	/**
	 * Constructs a line breakpoint on the given resource at the given line
	 * number. The line number is 1-based (i.e. the first line of a file is line
	 * number 1). The RML VM uses 0-based line numbers, so this line number
	 * translation is done at breakpoint install time.
	 * 
	 * @param resource
	 *            file on which to set the breakpoint
	 * @param lineNumber
	 *            1-based line number of the breakpoint
	 * @throws CoreException
	 *             if unable to create the breakpoint
	 */
	public MDTLineBreakpoint(IResource resource, int lineNumber)
			throws CoreException {
		IMarker marker = resource
				.createMarker("org.pelab.rml.debug.core.lineBreakpoint.marker");
		setMarker(marker);
		setEnabled(true);
		ensureMarker().setAttribute(IMarker.LINE_NUMBER, lineNumber);
		ensureMarker().setAttribute(IBreakpoint.ID,
				IMDTConstants.ID_MDT_DEBUG_MODEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IBreakpoint#getModelIdentifier()
	 */
	public String getModelIdentifier() {
		return IMDTConstants.ID_MDT_DEBUG_MODEL;
	}

}
