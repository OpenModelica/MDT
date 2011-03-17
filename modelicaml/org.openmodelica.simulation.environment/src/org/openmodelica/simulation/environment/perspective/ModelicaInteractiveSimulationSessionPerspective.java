/*
 * 
 */
package org.openmodelica.simulation.environment.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelicaInteractiveSimulationSessionPerspective.
 */
public class ModelicaInteractiveSimulationSessionPerspective implements IPerspectiveFactory {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
	}

}
