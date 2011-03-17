/*
 * 
 */
package org.openmodelica.simulation.environment.view.simulation.noninteractive;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

// TODO: Auto-generated Javadoc
/**
 * The Class NonInteractiveSimulationVisualisationView.
 */
public class NonInteractiveSimulationVisualisationView extends ViewPart {
	
	/** The composite1. */
	private Composite composite1;
	
	/** The composite visualisation. */
	private Composite compositeVisualisation;
	
	/**
	 * Instantiates a new non interactive simulation visualisation view.
	 */
	public NonInteractiveSimulationVisualisationView() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		 composite1 = new Composite(parent, SWT.NULL);
	        GridLayout composite1Layout = new GridLayout();
	        composite1.setLayout(composite1Layout);
		{
			compositeVisualisation = new NonInteractiveSimulationVisualisation_JFreeChartPlotComposite(composite1, SWT.NONE);
//        	compositeVisualisation = new CopyOfInteractiveSimulationVisualisation_JFreeChartPlotComposite(composite1, SWT.NONE);
        	FillLayout compositeVisualisationLayout = new FillLayout(org.eclipse.swt.SWT.HORIZONTAL);
        	GridData compositeVisualisationLData = new GridData();
        	compositeVisualisationLData.horizontalAlignment = GridData.FILL;
        	compositeVisualisationLData.verticalAlignment = GridData.FILL;
        	compositeVisualisationLData.grabExcessHorizontalSpace = true;
        	compositeVisualisationLData.grabExcessVerticalSpace = true;
        	compositeVisualisation.setLayoutData(compositeVisualisationLData);
        	compositeVisualisation.setLayout(compositeVisualisationLayout);
        }

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
