package org.modelica.mdt.debug.core.sourcelookup;

import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupDirector;
import org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant;

/**
 * MDT source lookup director. For MDT source lookup there is one source
 * lookup participant. 
 */
public class MDTSourceLookupDirector  extends AbstractSourceLookupDirector  {

	/* (non-Javadoc)
	 * @see org.eclipse.debug.internal.core.sourcelookup.ISourceLookupDirector#initializeParticipants()
	 */
	public void initializeParticipants() {
		addParticipants(new ISourceLookupParticipant[]{new MDTSourceLookupParticipant()});
	}	
		
}
