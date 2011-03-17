/*
 * 
 */
package org.openmodelica.simulation.environment.wizard.sessionconfiguration.interactive;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

// TODO: Auto-generated Javadoc
/**
 * The Class SessionConfiguration_ChangeModel_BooleanValueComposite.
 */
public class SessionConfiguration_ChangeModel_BooleanValueComposite extends org.eclipse.swt.widgets.Composite {
	
	/** The composite1. */
	private Composite composite1;
	
	/** The button check value. */
	private Button buttonCheckValue;

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
	public SessionConfiguration_ChangeModel_BooleanValueComposite(Composite parent, int style) {
		super(parent, style);
		initGUI();
		initValues(true);
	}
	
	/**
	 * Constructor to be used with given values for a property.
	 *
	 * @param parent Composite as parent
	 * @param style the style
	 * @param value Boolean value to be used as sample
	 */
	public SessionConfiguration_ChangeModel_BooleanValueComposite(Composite parent, int style, Boolean value) {
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
			{
				composite1 = new Composite(this, SWT.NONE);
				GridLayout composite1Layout = new GridLayout();
				composite1Layout.numColumns = 1;
				GridData composite1LData = new GridData();
				composite1LData.horizontalAlignment = GridData.FILL;
				composite1LData.grabExcessHorizontalSpace = true;
				composite1LData.verticalAlignment = GridData.FILL;
				composite1.setLayoutData(composite1LData);
				composite1.setLayout(composite1Layout);
				{
					buttonCheckValue = new Button(composite1, SWT.CHECK | SWT.LEFT);
					GridData buttonCheckValueLData = new GridData();
					buttonCheckValue.setLayoutData(buttonCheckValueLData);
					buttonCheckValue.setText("Boolean Value");
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
	 * @param value Boolean value
	 */
	private void initValues(Boolean value){
		buttonCheckValue.setSelection(value);
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Boolean getValue(){
		return buttonCheckValue.getSelection();
	}
}
