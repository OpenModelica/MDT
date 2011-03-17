/*
 * 
 */
package org.openmodelica.simulation.environment.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelicaSimulationProjectPerspective.
 */
public class ModelicaSimulationProjectPerspective implements IPerspectiveFactory {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
	}

}
