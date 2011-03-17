/*
 * 
 */
package org.openmodelica.simulation.environment.view.simulation.interactive;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * The Class InteractiveSimulationVisualisationView.
 */
public class InteractiveSimulationVisualisationView extends ViewPart {
    
    /** The Constant ID_VIEW. */
    public static final String ID_VIEW =
        "org.openmodelica.simulation.environment.view.simulation.interactive.InteractiveSimulationVisualisationView"; //$NON-NLS-1$
        
        /** The composite visualisation. */
        private Composite compositeVisualisation;
        
        /** The composite buttons. */
        private Composite compositeButtons;

    /** The composite1. */
    Composite composite1;
	
	/** The button reset. */
	private Button buttonReset;
	
	/** The button screenshot. */
	private Button buttonScreenshot;
	
	/** The button waiting. */
	private Button buttonWaiting;
	
	/** The button manange. */
	private Button buttonManange;
	
	/** The button delete. */
	private Button buttonDelete;
    
    /**
     * Instantiates a new interactive simulation visualisation view.
     */
    public InteractiveSimulationVisualisationView() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
    	{
	    	parent.setSize(214, 215);
    	}
        composite1 = new Composite(parent, SWT.NULL);
        GridLayout composite1Layout = new GridLayout();
        composite1.setLayout(composite1Layout);
        {
        	compositeButtons = new Composite(composite1, SWT.NONE);
        	GridLayout compositeButtonsLayout = new GridLayout();
        	compositeButtonsLayout.makeColumnsEqualWidth = true;
        	compositeButtonsLayout.numColumns = 5;
        	GridData compositeButtonsLData = new GridData();
        	compositeButtons.setLayoutData(compositeButtonsLData);
        	compositeButtons.setLayout(compositeButtonsLayout);
        	{
        		buttonWaiting = new Button(compositeButtons, SWT.TOGGLE | SWT.CENTER);
        		GridData buttonWaitingLData = new GridData();
        		buttonWaiting.setLayoutData(buttonWaitingLData);
        		
        		LocalResourceManager resources 
			    = new LocalResourceManager(JFaceResources.getResources(), buttonWaiting);
				ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/waiting.gif");
				Image i = imageDescriptor.createImage();
				resources.dispose();
        		buttonWaiting.setImage(i);
        		
        		buttonWaiting.setToolTipText("Wait and don't update View");
        	}
        	{
        		buttonReset = new Button(compositeButtons, SWT.PUSH | SWT.CENTER);
        		GridData buttonResetLData = new GridData();
        		buttonReset.setLayoutData(buttonResetLData);
        		LocalResourceManager resources 
        		= new LocalResourceManager(JFaceResources.getResources(), buttonReset);
        		ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/clear.gif");
        		Image i = imageDescriptor.createImage();
        		resources.dispose();
        		buttonReset.setImage(i);
        		buttonReset.setToolTipText("Clear View");
        	}
        	{
        		buttonDelete = new Button(compositeButtons, SWT.PUSH | SWT.CENTER);
        		GridData buttonDeleteLData = new GridData();
        		buttonDelete.setLayoutData(buttonDeleteLData);
        		LocalResourceManager resources 
        		= new LocalResourceManager(JFaceResources.getResources(), buttonDelete);
        		ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/delete.gif");
        		Image i = imageDescriptor.createImage();
        		resources.dispose();
        		buttonDelete.setImage(i);
        		buttonDelete.setToolTipText("Delete View");
        	}
        	{
        		buttonManange = new Button(compositeButtons, SWT.PUSH | SWT.CENTER);
        		GridData buttonManangeLData = new GridData();
        		buttonManange.setLayoutData(buttonManangeLData);
        		LocalResourceManager resources 
        		= new LocalResourceManager(JFaceResources.getResources(), buttonManange);
        		ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/settings.gif");
        		Image i = imageDescriptor.createImage();
        		resources.dispose();
        		buttonManange.setImage(i);
        		buttonManange.setToolTipText("Configure View");
        	}
        	{
        		buttonScreenshot = new Button(compositeButtons, SWT.PUSH | SWT.CENTER);
        		GridData buttonScreenshotLData = new GridData();
        		buttonScreenshot.setLayoutData(buttonScreenshotLData);
        		LocalResourceManager resources 
        		= new LocalResourceManager(JFaceResources.getResources(), buttonScreenshot);
        		ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/icon_camera.gif");
        		Image i = imageDescriptor.createImage();
        		resources.dispose();
        		buttonScreenshot.setImage(i);
        	}
        }
        {
        	compositeVisualisation = new InteractiveSimulationVisualisation_JFreeChartPlotComposite(composite1, SWT.NONE);
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
    
}
