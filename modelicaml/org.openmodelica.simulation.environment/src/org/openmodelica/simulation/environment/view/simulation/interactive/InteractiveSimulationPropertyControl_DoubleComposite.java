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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.openmodelica.simulation.core.network.SimulationControlClient;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * The Class InteractiveSimulationPropertyControl_DoubleComposite.
 */
public class InteractiveSimulationPropertyControl_DoubleComposite extends org.eclipse.swt.widgets.Composite {
	
	/** The label full qualified name. */
	private Label labelFullQualifiedName;
	
	/** The button manual change. */
	private Button buttonManualChange;
	
	/** The text value. */
	private Text textValue;
	
	/** The composite manual change. */
	private Composite compositeManualChange;

	/**
	 * Auto-generated main method to display this
	 * org.eclipse.swt.widgets.Composite inside a new Shell.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		showGUI();
	}
	
	/**
	* Overriding checkSubclass allows this class to extend org.eclipse.swt.widgets.Composite
	*/	
	protected void checkSubclass() {
	}
	
	/**
	* Auto-generated method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		InteractiveSimulationPropertyControl_DoubleComposite inst = new InteractiveSimulationPropertyControl_DoubleComposite(shell, SWT.NULL);
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if(size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * Instantiates a new interactive simulation property control_ double composite.
	 *
	 * @param parent the parent
	 * @param style the style
	 */
	public InteractiveSimulationPropertyControl_DoubleComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		String fullQualifiedName = "source.flowLevel";
		Double value = 5.0;
		Double minValue = 0.010;
		Double maxValue = 2.000;
		initGUI(fullQualifiedName, value, minValue, maxValue);
	}
	
	/**
	 * Instantiates a new interactive simulation property control_ double composite.
	 *
	 * @param parent the parent
	 * @param style the style
	 * @param color the color
	 * @param fullQualifiedName the full qualified name
	 * @param value the value
	 * @param minValue the min value
	 * @param maxValue the max value
	 */
	public InteractiveSimulationPropertyControl_DoubleComposite(
			Composite parent,
			int style, int color, String fullQualifiedName, Double value,
			Double minValue, Double maxValue) {
		super(parent, style);
//		this.omi_parent = omi_parent;
		initGUI(fullQualifiedName, value, minValue, maxValue);
	}


	/**
	 * Inits the gui.
	 *
	 * @param fullQualifiedName the full qualified name
	 * @param value the value
	 * @param minValue the min value
	 * @param maxValue the max value
	 */
	private void initGUI(String fullQualifiedName, Double value,
			Double minValue, Double maxValue) {
		try {
			GridLayout thisLayout = new GridLayout();
			GridData thisLData = new GridData();
			thisLData.horizontalAlignment = GridData.FILL;
			thisLData.grabExcessHorizontalSpace = true;
			this.setLayoutData(thisLData);
			this.setLayout(thisLayout);
			this.setSize(256, 64);
			{
				labelFullQualifiedName = new Label(this, SWT.NONE);
				GridData labelFullQualifiedNameLData = new GridData();
				labelFullQualifiedNameLData.grabExcessHorizontalSpace = true;
				labelFullQualifiedNameLData.horizontalAlignment = GridData.FILL;
				labelFullQualifiedName.setLayoutData(labelFullQualifiedNameLData);
				labelFullQualifiedName.setText(fullQualifiedName);
			}
			{
				compositeManualChange = new Composite(this, SWT.NONE);
				GridLayout compositeManualChangeLayout = new GridLayout();
				compositeManualChangeLayout.makeColumnsEqualWidth = true;
				compositeManualChangeLayout.numColumns = 3;
				GridData compositeManualChangeLData = new GridData();
				compositeManualChangeLData.horizontalAlignment = GridData.FILL;
				compositeManualChangeLData.grabExcessHorizontalSpace = true;
				compositeManualChangeLData.verticalAlignment = GridData.BEGINNING;
				compositeManualChange.setLayoutData(compositeManualChangeLData);
				compositeManualChange.setLayout(compositeManualChangeLayout);
				{
					textValue = new Text(compositeManualChange, SWT.NONE);
					GridData text1LData = new GridData();
					text1LData.horizontalAlignment = GridData.FILL;
					text1LData.grabExcessHorizontalSpace = true;
					text1LData.horizontalSpan = 2;
					textValue.setLayoutData(text1LData);
					textValue.setText(value.toString());
				}
				{
					buttonManualChange = new Button(compositeManualChange, SWT.PUSH | SWT.CENTER);
					GridData buttonManualChangeLData = new GridData();
					buttonManualChangeLData.horizontalAlignment = GridData.END;
					buttonManualChange.setLayoutData(buttonManualChangeLData);
					buttonManualChange.setText("Change");
					buttonManualChange.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
							try {
								Double parameterValue = new Double(textValue
										.getText());
								SimulationControlClient //TODO Auslagern in SimulationCenter_Interactive
										.send_changevalue_Message(Activator
												.getSimulationCenter_Interactive().getSequenceNumber()+"",
												Activator
														.getSimulationCenter_Interactive()
														.getSimulationResultManager()
														.getActSimulationTime()
														.toString(),
												labelFullQualifiedName
														.getText(),
												parameterValue.toString());
							} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
				}
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
