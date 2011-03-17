/*
 * 
 */
package org.openmodelica.simulation.environment.view.simulation.interactive;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;
import org.openmodelica.simulation.core.models.modelica.ModelicaParameter;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * The Class InteractiveSimulationPropertyControlView.
 */
public class InteractiveSimulationPropertyControlView extends ViewPart implements Observer{
    
    /** The Constant ID_VIEW. */
    public static final String ID_VIEW =
        "org.openmodelica.simulation.environment.view.simulation.interactive.InteractiveSimulationPropertyControlView"; //$NON-NLS-1$
        
        /** The scrolled composite property composites. */
        private Composite scrolledCompositePropertyComposites;
        
        /** The composite1. */
        private Composite composite1;

    /**
     * Instantiates a new interactive simulation property control view.
     */
    public InteractiveSimulationPropertyControlView() {
        super();

//        System.err.println("InteractiveSimulationPropertyControlView");
        Activator.getSimulationCenter_Interactive().addPlot(this);
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
	public void createPartControl(Composite parent) {
		{
			parent.setSize(326, 174);
		}
		{
			composite1 = new Composite(parent, SWT.NONE);
			GridLayout composite1Layout = new GridLayout();
			composite1.setLayout(composite1Layout);
			{
				GridData scrolledCompositePropertyCompositesLData = new GridData();
				scrolledCompositePropertyCompositesLData.horizontalAlignment = GridData.FILL;
				scrolledCompositePropertyCompositesLData.verticalAlignment = GridData.FILL;
				scrolledCompositePropertyCompositesLData.grabExcessVerticalSpace = true;
				scrolledCompositePropertyCompositesLData.grabExcessHorizontalSpace = true;
				scrolledCompositePropertyComposites = new Composite(composite1, SWT.NONE);
				
//				createSubCompositesForProperties();
				GridLayout scrolledCompositePropertyCompositesLayout = new GridLayout();
				scrolledCompositePropertyCompositesLayout.makeColumnsEqualWidth = true;
				scrolledCompositePropertyComposites
						.setLayout(scrolledCompositePropertyCompositesLayout);
				scrolledCompositePropertyComposites
						.setLayoutData(scrolledCompositePropertyCompositesLData);
			}
			createSubCompositesForProperties();
		}
	}
	
	/**
	 * Creates the sub composites for properties.
	 */
	public void createSubCompositesForProperties() {
		if(Activator.getSimulationCenter_Interactive() != null){	
			for (ModelicaParameter parameter : Activator.getSimulationCenter_Interactive().getInteractiveProperties()) {
				if (parameter.getPrimitiveValue() instanceof Double) {
					@SuppressWarnings("unused")
					Composite interactiveComposite = new InteractiveSimulationPropertyControl_DoubleComposite(
							scrolledCompositePropertyComposites, SWT.BORDER, 0,
							parameter.getName(),
							(Double) parameter.getPrimitiveValue(),
							(Double) parameter.getMinValue(),
							(Double) parameter.getMaxValue());
				}
			}
		}
	}

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() {
        // TODO Auto-generated method stub
    }
    
    /**
     * Cleans up all resources created by this ViewPart.
     */
    public void dispose() {
        super.dispose();
    }

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String && ((String) arg).equals("newsimulation")){
//			System.out.println("PropertyControl newsimulation");
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					
					for(Control childcontrol : scrolledCompositePropertyComposites.getChildren()){
//						childcontrol.setEnabled(false);
//						childcontrol.setVisible(false);
						childcontrol.dispose();
					}
					
					createSubCompositesForProperties();
				}
			});
		}		
	}
    
}
