package org.modelica.mdt.debug.core.sourcelookup;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;
import org.modelica.mdt.debug.core.model.MDTStackFrame;

/**
 * The MDT source lookup participant knows how to translate a MDT stack frame
 * into a source file name
 */
public class MDTSourceLookupParticipant extends AbstractSourceLookupParticipant {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.internal.core.sourcelookup.ISourceLookupParticipant#getSourceName(java.lang.Object)
	 */
	public String getSourceName(Object object) throws CoreException {
		if (object instanceof MDTStackFrame) {
			return ((MDTStackFrame) object).getSourceName();
		}
		return null;
	}

}
