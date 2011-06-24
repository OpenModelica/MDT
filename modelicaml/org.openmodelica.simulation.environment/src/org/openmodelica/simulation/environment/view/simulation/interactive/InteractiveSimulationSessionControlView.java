/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.simulation.environment.view.simulation.interactive;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * The Class InteractiveSimulationSessionControlView.
 */
public class InteractiveSimulationSessionControlView extends ViewPart {

    /** The Constant ID_VIEW. */
    public static final String ID_VIEW =
        "org.openmodelica.simulation.environment.view.simulation.interactive.InteractiveSimulationSessionControlView"; //$NON-NLS-1$
        
        /** The button screenshot. */
        private Button buttonScreenshot;
        
        /** The composite views created views. */
        private Composite compositeViewsCreatedViews;
        
        /** The button views add view. */
        private Button buttonViewsAddView;
        
        /** The composite views manangement. */
        private Composite compositeViewsManangement;
        
        /** The styled text status messages. */
        private StyledText styledTextStatusMessages;
        
        /** The styled text simulation time. */
        private StyledText styledTextSimulationTime;
        
        /** The button fast forward. */
        private Button buttonFastForward;
        
        /** The button forward. */
        private Button buttonForward;
        
        /** The button reverse. */
        private Button buttonReverse;
        
        /** The label control rev forw empty. */
        private Label labelControlRevForwEmpty;
        
        /** The button fast rev. */
        private Button buttonFastRev;
        
        /** The button stop. */
        private Button buttonStop;
        
        /** The button toggle run. */
        private Button buttonToggleRun;
        
        /** The composite control rev forw. */
        private Composite compositeControlRevForw;
        
        /** The composite control play stop start. */
        private Composite compositeControlPlayStopStart;
        
        /** The group status. */
        private Group groupStatus;
        
        /** The group control. */
        private Group groupControl;
        
        /** The group view control. */
        private Group groupViewControl;

    /** The composite groups. */
    Composite compositeGroups;
    
    /**
     * Instantiates a new interactive simulation session control view.
     */
    public InteractiveSimulationSessionControlView() {
        super();

        SimulationTimePlot simulationTimePlot = new SimulationTimePlot();
        Activator.getSimulationCenter_Interactive().addSimulationTimePlot((Observer)simulationTimePlot);
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
    	{
	    	parent.setSize(198, 382);
    	}
        compositeGroups = new Composite(parent, SWT.NULL);
        GridLayout composite1Layout = new GridLayout();
        compositeGroups.setLayout(composite1Layout);
        {
        	groupViewControl = new Group(compositeGroups, SWT.NONE);
        	GridLayout groupViewControlLayout = new GridLayout();
        	groupViewControlLayout.makeColumnsEqualWidth = true;
        	groupViewControl.setLayout(groupViewControlLayout);
        	GridData groupViewControlLData = new GridData();
        	groupViewControlLData.horizontalAlignment = GridData.FILL;
        	groupViewControlLData.grabExcessHorizontalSpace = true;
        	groupViewControlLData.verticalAlignment = GridData.FILL;
        	groupViewControlLData.grabExcessVerticalSpace = true;
        	groupViewControl.setLayoutData(groupViewControlLData);
        	groupViewControl.setText("Views");
        	{
        		compositeViewsManangement = new Composite(groupViewControl, SWT.NONE);
        		GridLayout compositeViewsManangementLayout = new GridLayout();
        		compositeViewsManangementLayout.makeColumnsEqualWidth = true;
        		compositeViewsManangementLayout.numColumns = 2;
        		GridData compositeViewsManangementLData = new GridData();
        		compositeViewsManangementLData.horizontalAlignment = GridData.FILL;
        		compositeViewsManangementLData.grabExcessHorizontalSpace = true;
        		compositeViewsManangement.setLayoutData(compositeViewsManangementLData);
        		compositeViewsManangement.setLayout(compositeViewsManangementLayout);
        		{
        			buttonViewsAddView = new Button(compositeViewsManangement, SWT.PUSH | SWT.CENTER);
        			GridData buttonViewsAddViewLData = new GridData();
        			buttonViewsAddViewLData.horizontalAlignment = GridData.FILL;
        			buttonViewsAddViewLData.verticalAlignment = GridData.FILL;
        			buttonViewsAddView.setLayoutData(buttonViewsAddViewLData);
        			
        			
        			LocalResourceManager resources 
				    = new LocalResourceManager(JFaceResources.getResources(), buttonViewsAddView);
					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/add.gif");
					Image i = imageDescriptor.createImage();
					resources.dispose();
            		buttonViewsAddView.setImage(i);
        			buttonViewsAddView.setToolTipText("Add View");
        		}
        		{
        			GridData buttonScreenshotLData = new GridData();
        			buttonScreenshot = new Button(compositeViewsManangement, SWT.PUSH | SWT.CENTER);
        			buttonScreenshot.setLayoutData(buttonScreenshotLData);
        			LocalResourceManager resources 
				    = new LocalResourceManager(JFaceResources.getResources(), buttonScreenshot);
					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/icon_camera.gif");
					Image i = imageDescriptor.createImage();
					resources.dispose();
        			buttonScreenshot.setImage(i);
        			buttonScreenshot.setToolTipText("Screenshot of all Views");
        		}
        	}
        	{
        		compositeViewsCreatedViews = new Composite(groupViewControl, SWT.NONE);
        		GridLayout compositeViewsCreatedViewsLayout = new GridLayout();
        		compositeViewsCreatedViewsLayout.makeColumnsEqualWidth = true;
        		GridData compositeViewsCreatedViewsLData = new GridData();
        		compositeViewsCreatedViewsLData.horizontalAlignment = GridData.FILL;
        		compositeViewsCreatedViewsLData.verticalAlignment = GridData.FILL;
        		compositeViewsCreatedViewsLData.grabExcessVerticalSpace = true;
        		compositeViewsCreatedViews.setLayoutData(compositeViewsCreatedViewsLData);
        		compositeViewsCreatedViews.setLayout(compositeViewsCreatedViewsLayout);
        	}
        	{
        		Group vg = new InteractiveSimulationSessionControl_ViewsGroup(compositeViewsCreatedViews, SWT.NONE);
        		GridData vgLData = new GridData();
        		vgLData.horizontalAlignment = GridData.FILL;
        		vgLData.grabExcessHorizontalSpace = true;
        		vg.setLayoutData(vgLData);
        		vg.setText("View 1");
        	}
        }
        {
        	groupControl = new Group(compositeGroups, SWT.NONE);
        	GridLayout groupControlLayout = new GridLayout();
        	groupControlLayout.makeColumnsEqualWidth = true;
        	groupControl.setLayout(groupControlLayout);
        	GridData groupControlLData = new GridData();
        	groupControlLData.verticalAlignment = GridData.FILL;
        	groupControlLData.horizontalAlignment = GridData.FILL;
        	groupControlLData.grabExcessHorizontalSpace = true;
        	groupControl.setLayoutData(groupControlLData);
        	groupControl.setText("Control");
        	{
        		compositeControlPlayStopStart = new Composite(groupControl, SWT.NONE);
        		GridLayout compositeControlPlayStopStartLayout = new GridLayout();
        		compositeControlPlayStopStartLayout.makeColumnsEqualWidth = true;
        		compositeControlPlayStopStartLayout.numColumns = 2;
        		GridData compositeControlPlayStopStartLData = new GridData();
        		compositeControlPlayStopStartLData.horizontalAlignment = GridData.FILL;
        		compositeControlPlayStopStartLData.grabExcessHorizontalSpace = true;
        		compositeControlPlayStopStartLData.verticalAlignment = GridData.FILL;
        		compositeControlPlayStopStart.setLayoutData(compositeControlPlayStopStartLData);
        		compositeControlPlayStopStart.setLayout(compositeControlPlayStopStartLayout);
        		{
        			buttonToggleRun = new Button(compositeControlPlayStopStart, SWT.TOGGLE | SWT.CENTER);
        			GridData buttonTogglePlayLData = new GridData();
        			buttonTogglePlayLData.horizontalAlignment = GridData.FILL;
        			buttonTogglePlayLData.grabExcessHorizontalSpace = true;
        			buttonToggleRun.setLayoutData(buttonTogglePlayLData);
        			LocalResourceManager resources 
				    = new LocalResourceManager(JFaceResources.getResources(), buttonToggleRun);
					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/run.gif");
					Image i = imageDescriptor.createImage();
					resources.dispose();
        			buttonToggleRun.setImage(i);
        			buttonToggleRun.setToolTipText("Run Simulation");
        			buttonToggleRun.addSelectionListener(new SelectionAdapter() {
        				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                			//  		    	  compositeInteractiveParameters.setEnabled(true); //TODO Hier erst können die Interactiven Elemente geschaltet werden
                			if(buttonToggleRun.getSelection()){ //true = run button, false = pause button
                				buttonStop.setEnabled(true);
                				LocalResourceManager resources 
            				    = new LocalResourceManager(JFaceResources.getResources(), buttonToggleRun);
            					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/progress_pause.gif");
            					Image i = imageDescriptor.createImage();
            					resources.dispose();
                				buttonToggleRun.setImage(i);
                				buttonToggleRun.setToolTipText("Pause Simulation");
                				Activator.getSimulationCenter_Interactive().setSimulationStatus("start");
                				styledTextStatusMessages.setText("Simulation is running...");
                			} else{
                				buttonStop.setEnabled(true);
                				LocalResourceManager resources 
            				    = new LocalResourceManager(JFaceResources.getResources(), buttonToggleRun);
            					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/run.gif");
            					Image i = imageDescriptor.createImage();
            					resources.dispose();
                				buttonToggleRun.setImage(i);
                				buttonToggleRun.setToolTipText("Run Simulation");
                				Activator.getSimulationCenter_Interactive().setSimulationStatus("pause");
                				styledTextStatusMessages.setText("Simulation is paused...");
                			}
                		}
        			});
        		}
        		{
        			buttonStop = new Button(compositeControlPlayStopStart, SWT.PUSH | SWT.CENTER);
        			GridData buttonStopLData = new GridData();
        			buttonStopLData.horizontalAlignment = GridData.FILL;
        			buttonStopLData.grabExcessHorizontalSpace = true;
        			buttonStop.setLayoutData(buttonStopLData);
        			LocalResourceManager resources 
				    = new LocalResourceManager(JFaceResources.getResources(), buttonStop);
					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/progress_stop.gif");
					Image i = imageDescriptor.createImage();
					resources.dispose();
        			buttonStop.setImage(i);
        			buttonStop.setToolTipText("Stop and Reset Simulation");
        			buttonStop.setEnabled(false);
        			buttonStop.addSelectionListener(new SelectionAdapter() {
        				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                			//compositeInteractiveParameters.setEnabled(true); //TODO Hier erst können die Interactiven Elemente geschaltet werden
                			buttonToggleRun.setSelection(false);
                			LocalResourceManager resources 
        				    = new LocalResourceManager(JFaceResources.getResources(), buttonToggleRun);
        					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/run.gif");
        					Image i = imageDescriptor.createImage();
        					resources.dispose();
                			buttonToggleRun.setImage(i);
                			buttonToggleRun.setToolTipText("Run Simulation");
                			buttonStop.setEnabled(false);
                			Activator.getSimulationCenter_Interactive().setSimulationStatus("stop");
                			styledTextStatusMessages.setText("Simulation is stoped...");
                		}
        			});
        		}
        	}
        	{
        		compositeControlRevForw = new Composite(groupControl, SWT.NONE);
        		GridLayout compositeControlRewForwLayout = new GridLayout();
        		compositeControlRewForwLayout.makeColumnsEqualWidth = true;
        		compositeControlRewForwLayout.numColumns = 5;
        		GridData compositeControlRewForwLData = new GridData();
        		compositeControlRewForwLData.verticalAlignment = GridData.FILL;
        		compositeControlRewForwLData.horizontalAlignment = GridData.FILL;
        		compositeControlRewForwLData.grabExcessHorizontalSpace = true;
        		compositeControlRevForw.setLayoutData(compositeControlRewForwLData);
        		compositeControlRevForw.setLayout(compositeControlRewForwLayout);
        		{
        			buttonFastRev = new Button(compositeControlRevForw, SWT.TOGGLE | SWT.CENTER);
        			GridData buttonFastRevLData = new GridData();
        			buttonFastRevLData.horizontalAlignment = GridData.FILL;
        			buttonFastRevLData.grabExcessHorizontalSpace = true;
        			buttonFastRev.setLayoutData(buttonFastRevLData);
        			
        			LocalResourceManager resources 
				    = new LocalResourceManager(JFaceResources.getResources(), buttonFastRev);
					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/fast_back.gif");
					Image i = imageDescriptor.createImage();
					resources.dispose();
        			buttonFastRev.setImage(i);
        			buttonFastRev.setToolTipText("Fast Reverse");
        		}
        		{
        			buttonReverse = new Button(compositeControlRevForw, SWT.PUSH | SWT.CENTER);
        			GridData buttonReverseLData = new GridData();
        			buttonReverseLData.horizontalAlignment = GridData.FILL;
        			buttonReverseLData.grabExcessHorizontalSpace = true;
        			buttonReverse.setLayoutData(buttonReverseLData);
        			
        			
        			LocalResourceManager resources 
				    = new LocalResourceManager(JFaceResources.getResources(), buttonReverse);
					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/back.gif");
					Image i = imageDescriptor.createImage();
					resources.dispose();
        			buttonReverse.setImage(i);
        			buttonReverse.setToolTipText("Step Back");
        		}
        		{
        			GridData labelControlRevForwEmptyLData = new GridData();
        			labelControlRevForwEmptyLData.horizontalAlignment = GridData.FILL;
        			labelControlRevForwEmptyLData.grabExcessHorizontalSpace = true;
        			labelControlRevForwEmpty = new Label(compositeControlRevForw, SWT.NONE);
        			labelControlRevForwEmpty.setLayoutData(labelControlRevForwEmptyLData);
        		}
        		{
        			buttonForward = new Button(compositeControlRevForw, SWT.PUSH | SWT.CENTER);
        			GridData buttonForwardLData = new GridData();
        			buttonForwardLData.horizontalAlignment = GridData.FILL;
        			buttonForwardLData.grabExcessHorizontalSpace = true;
        			buttonForward.setLayoutData(buttonForwardLData);
        			
        			LocalResourceManager resources 
				    = new LocalResourceManager(JFaceResources.getResources(), buttonForward);
					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/forward.gif");
					Image i = imageDescriptor.createImage();
					resources.dispose();
        			buttonForward.setImage(i);
        			buttonForward.setToolTipText("Step Forward");
        		}
        		{
        			buttonFastForward = new Button(compositeControlRevForw, SWT.TOGGLE | SWT.CENTER);
        			GridData buttonFastForwardLData = new GridData();
        			buttonFastForwardLData.horizontalAlignment = GridData.FILL;
        			buttonFastForwardLData.grabExcessHorizontalSpace = true;
        			buttonFastForward.setLayoutData(buttonFastForwardLData);
        			
        			LocalResourceManager resources 
				    = new LocalResourceManager(JFaceResources.getResources(), buttonFastForward);
					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/fast_forward.gif");
					Image i = imageDescriptor.createImage();
					resources.dispose();
        			buttonFastForward.setImage(i);
        			buttonFastForward.setToolTipText("Fast Forward");
        		}
        	}
        	
        	if(Activator.getSimulationCenter_Interactive().getSimulationStatus().equals(""))groupControl.setEnabled(false);
        }
        {
        	groupStatus = new Group(compositeGroups, SWT.NONE);
        	GridLayout groupStatusLayout = new GridLayout();
        	groupStatusLayout.makeColumnsEqualWidth = true;
        	groupStatus.setLayout(groupStatusLayout);
        	GridData groupStatusLData = new GridData();
        	groupStatusLData.verticalAlignment = GridData.FILL;
        	groupStatusLData.horizontalAlignment = GridData.FILL;
        	groupStatusLData.grabExcessHorizontalSpace = true;
        	groupStatus.setLayoutData(groupStatusLData);
        	groupStatus.setText("Status");
        	{
        		styledTextSimulationTime = new StyledText(groupStatus, SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER);
        		GridData styledTextSimulationTimeLData = new GridData();
        		styledTextSimulationTimeLData.horizontalAlignment = GridData.FILL;
        		styledTextSimulationTimeLData.grabExcessHorizontalSpace = true;
        		styledTextSimulationTime.setLayoutData(styledTextSimulationTimeLData);
//        		SimulationTimePlot simulationTimePlot = new SimulationTimePlot();
//        		Activator.getSimulationCenter_Interactive().addSimulationTimePlot((Observer)simulationTimePlot);
        		
        		styledTextSimulationTime.setText("00y 00m 00d 00:00:00.000");
        		styledTextSimulationTime.setEditable(false);
        		Font font = new Font(Display.getCurrent(), "Segoe UI Semibold", 12, SWT.NONE); 
        		styledTextSimulationTime.setFont(font);
        		styledTextSimulationTime.setOrientation(SWT.HORIZONTAL);
        		styledTextSimulationTime.setToolTipText("Actual simulation time [h:min:sec:ms]");
        	}
        	{
        		styledTextStatusMessages = new StyledText(groupStatus, SWT.NONE);
        		GridData styledTextStatusMessagesLData = new GridData();
        		styledTextStatusMessagesLData.horizontalAlignment = GridData.FILL;
        		styledTextStatusMessagesLData.grabExcessHorizontalSpace = true;
        		styledTextStatusMessagesLData.verticalAlignment = GridData.FILL;
        		styledTextStatusMessages.setLayoutData(styledTextStatusMessagesLData);
        		styledTextStatusMessages.setText("Simulation status...");
        		styledTextStatusMessages.setEditable(false);
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
    
    /**
     * A Class to format the simulation time on plot and set the text of the styledTextSimulationTime.
     *
     * @author Parham Vasaiely
     */
	private class SimulationTimePlot implements
			Observer {

		/**
		 * Instantiates a new simulation time plot.
		 */
		public SimulationTimePlot() {
			super();
		}

		/* (non-Javadoc)
		 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
		 */
		@Override
		public void update(Observable o, Object arg) {
			if(arg instanceof String && ((String) arg).equals("newsimulation")){
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						buttonStop.setEnabled(false);
						buttonToggleRun.setSelection(false);
						
						LocalResourceManager resources 
					    = new LocalResourceManager(JFaceResources.getResources(), buttonToggleRun);
						ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/run.gif");
						Image i = imageDescriptor.createImage();
						resources.dispose();
            			buttonToggleRun.setImage(i);
            			buttonToggleRun.setToolTipText("Run Simulation");
						styledTextSimulationTime.setText("00y 00m 00d 00:00:00.000");
						groupControl.setEnabled(true);
					}
				});
			}			
			else if(arg instanceof Double){
				final Double actTimeText = (Double) arg;
				final String formattedTime = formatSimulationTime(actTimeText);
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						styledTextSimulationTime.setText(formattedTime);
					}
				});
			}
		}
		
		/**
		 * Format simulation time.
		 *
		 * @param timeInSec the time in sec
		 * @return the string
		 */
		private String formatSimulationTime(Double timeInSec){
			
				double y = 31556926;
				double m = 2629743.83;
				double d = 86400;
				double h = 3600;
				double min = 60;
				
				Double year = 0.0;
				Double month = 0.0;
				Double day = 0.0;
				Double hour = 0.0;
				Double minute = 0.0;
				Double seconds = 0.0;
				Integer milliseconds = 0;
				
				milliseconds = (int)(remainder(timeInSec, 1.0) * 1000);
				
				double timeInSec2 = timeInSec.intValue(); //Time without millis
				
				year = ((timeInSec2 / y));
				timeInSec2 = remainder(timeInSec2, y);
				
				month = ((timeInSec2 / m));
				timeInSec2 = remainder(timeInSec2, m);
				
				day = ((timeInSec2 / d));
				timeInSec2 = remainder(timeInSec2, d);
				
				hour = ((timeInSec2 / h));
				timeInSec2 = remainder(timeInSec2, h);
				
				minute = ((timeInSec2 / min));
				timeInSec2 = remainder(timeInSec2, min);
				
				seconds = (timeInSec2);
				
				String formattedTime = "";
				
				if(year.intValue() > 0)
					formattedTime = formattedTime + year.intValue() + " y ";
				if(month.intValue() > 0)
					formattedTime = formattedTime + month.intValue() + " m ";
				if(day.intValue() > 0)
					formattedTime = formattedTime + day.intValue() + " d ";
				if(hour.intValue() > 0)
					formattedTime = formattedTime + hour.intValue() + ":";
				else
					formattedTime = formattedTime + "00" + ":";
				if(minute.intValue() > 0)
					formattedTime = formattedTime + minute.intValue() + ":";
				else
					formattedTime = formattedTime + "00" + ":";
				if(seconds.intValue() > 0)
					formattedTime = formattedTime + seconds.intValue() + ".";
				else
					formattedTime = formattedTime + "00" + ".";
				if(milliseconds > 99)
					formattedTime = formattedTime + milliseconds;
				else if(milliseconds > 9)
					formattedTime = formattedTime + "0" + milliseconds;
				else if(milliseconds > 0)
					formattedTime = formattedTime + "00" + milliseconds;
				else
					formattedTime = formattedTime + "000";
					
				return formattedTime;
			}
			
			/**
			 * Remainder.
			 *
			 * @param a the a
			 * @param b the b
			 * @return the double
			 */
			private Double remainder( Double a, Double b ) 
			{ 
				return Math.signum(a) * 
				(Math.abs(a) - Math.abs(b) * Math.floor(Math.abs(a)/Math.abs(b))); 
			}

	}
    
}
