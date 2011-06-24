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
package org.openmodelica.simulation.environment.wizard.sessionconfiguration.interactive;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

// TODO: Auto-generated Javadoc
/**
 * The Class SessionConfiguration_ChangeModel_StringValueComposite.
 */
public class SessionConfiguration_ChangeModel_StringValueComposite extends org.eclipse.swt.widgets.Composite {
	
	/** The composite1. */
	private Composite composite1;
	
	/** The text value. */
	private Text textValue;
	
	/** The label value. */
	private Label labelValue;

	/**
	* Overriding checkSubclass allows this class to extend org.eclipse.swt.widgets.Composite
	*/	
	protected void checkSubclass() {
	}
	
	/**
	 * Auto-generated method to display this
	 * org.eclipse.swt.widgets.Composite inside a new Shell.
	 *
	 * @param parent the parent
	 * @param style the style
	 */

	/**
	 * This Constructor will be used as demonstration. Commonly all Properties at least has a value
	 * @param parent Composite as parent
	 * @param style
	 */
	public SessionConfiguration_ChangeModel_StringValueComposite(Composite parent, int style) {
		super(parent, style);
		initGUI();
		initValues("empty");
	}
	
	/**
	 * Constructor to be used with given values for a property.
	 *
	 * @param parent Composite as parent
	 * @param style the style
	 * @param value String value to be used as sample
	 */
	public SessionConfiguration_ChangeModel_StringValueComposite(Composite parent, int style, String value) {
		super(parent, style);
		initGUI();
		initValues(value);
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout();
			thisLayout.makeColumnsEqualWidth = true;
			this.setLayout(thisLayout);
			this.setSize(255, 100);
			{
				composite1 = new Composite(this, SWT.NONE);
				GridLayout composite1Layout = new GridLayout();
				composite1Layout.numColumns = 3;
				GridData composite1LData = new GridData();
				composite1LData.horizontalAlignment = GridData.FILL;
				composite1LData.grabExcessHorizontalSpace = true;
				composite1LData.verticalAlignment = GridData.FILL;
				composite1.setLayoutData(composite1LData);
				composite1.setLayout(composite1Layout);
				{
					labelValue = new Label(composite1, SWT.NONE);
					GridData labelValueLData = new GridData();
					labelValue.setLayoutData(labelValueLData);
					labelValue.setText("String Value:");
				}
				{
					GridData textRealLData = new GridData();
					textRealLData.horizontalSpan = 2;
					textRealLData.horizontalAlignment = GridData.FILL;
					textRealLData.grabExcessHorizontalSpace = true;
					textValue = new Text(composite1, SWT.BORDER);
					textValue.setLayoutData(textRealLData);
				}
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes all text fields with values.
	 *
	 * @param value the value
	 */
	private void initValues(String value) {
		textValue.setText(value + "");
	}
	
	/**
	 * Entered value for this property.
	 *
	 * @return the property value as Double, or null if nothing has entered
	 */
	public String getValue() {
		return textValue.getText();
	}
}
