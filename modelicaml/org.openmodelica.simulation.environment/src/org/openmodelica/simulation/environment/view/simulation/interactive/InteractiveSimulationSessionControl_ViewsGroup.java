/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Link�pings universitet, Department of Computer and Information Science,
 * SE-58183 Link�ping, Sweden.
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.simulation.environment.view.simulation.interactive;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * The Class InteractiveSimulationSessionControl_ViewsGroup.
 */
public class InteractiveSimulationSessionControl_ViewsGroup extends org.eclipse.swt.widgets.Group {

	/** The composite buttons. */
	private Composite compositeButtons;
	
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
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Group inside a new Shell.
	*/
	
	/**
	* Overriding checkSubclass allows this class to extend org.eclipse.swt.widgets.Group
	*/	
	protected void checkSubclass() {
	}
	
	/**
	 * Auto-generated method to display this
	 * org.eclipse.swt.widgets.Group inside a new Shell.
	 *
	 * @param parent the parent
	 * @param style the style
	 */

	public InteractiveSimulationSessionControl_ViewsGroup(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			FormLayout thisLayout = new FormLayout();
			this.setLayout(thisLayout);
			{
				compositeButtons = new Composite(this, SWT.NONE);
				GridLayout compositeButtonsLayout = new GridLayout();
				compositeButtonsLayout.makeColumnsEqualWidth = true;
				compositeButtonsLayout.numColumns = 5;
				FormData compositeButtonsLData = new FormData();
				compositeButtonsLData.top =  new FormAttachment(0, 1000, 0);
				compositeButtonsLData.left =  new FormAttachment(0, 1000, 0);
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
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
