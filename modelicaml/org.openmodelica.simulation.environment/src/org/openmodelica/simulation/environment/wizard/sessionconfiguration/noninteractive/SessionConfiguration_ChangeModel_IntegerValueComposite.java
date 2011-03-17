/*
 * 
 */
package org.openmodelica.simulation.environment.wizard.sessionconfiguration.noninteractive;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

// TODO: Auto-generated Javadoc
/**
 * The Class SessionConfiguration_ChangeModel_IntegerValueComposite.
 */
public class SessionConfiguration_ChangeModel_IntegerValueComposite extends org.eclipse.swt.widgets.Composite {
	
	/** The composite1. */
	private Composite composite1;
	
	/** The text value. */
	private Text textValue;
	
	/** The label max value. */
	private Label labelMaxValue;
	
	/** The text max value. */
	private Text textMaxValue;
	
	/** The text min value. */
	private Text textMinValue;
	
	/** The label min value. */
	private Label labelMinValue;
	
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
	public SessionConfiguration_ChangeModel_IntegerValueComposite(Composite parent, int style) {
		super(parent, style);
		initGUI();
		initValues(-1, -1, 1);
	}
	
	/**
	 * Constructor to be used with given values for a property.
	 *
	 * @param parent Composite as parent
	 * @param style the style
	 * @param value Double value to be used as sample
	 * @param minValue Double value to be used as sample
	 * @param maxValue Double value to be used as sample
	 */
	public SessionConfiguration_ChangeModel_IntegerValueComposite(Composite parent, int style, Integer value, Integer minValue, Integer maxValue) {
		super(parent, style);
		initGUI();
		initValues(value, minValue, maxValue);
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout();
			thisLayout.makeColumnsEqualWidth = true;
			this.setLayout(thisLayout);
			this.setSize(289, 100);
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
					labelValue.setText("Integer Value:");
				}
				{
					GridData textRealLData = new GridData();
					textRealLData.horizontalSpan = 2;
					textRealLData.horizontalAlignment = GridData.FILL;
					textRealLData.grabExcessHorizontalSpace = true;
					textValue = new Text(composite1, SWT.BORDER);
					textValue.setLayoutData(textRealLData);
				}
				{
					labelMinValue = new Label(composite1, SWT.NONE);
					GridData labelMinValueLData = new GridData();
					labelMinValue.setLayoutData(labelMinValueLData);
					labelMinValue.setText("Minumum Value:");
				}
				{
					GridData textMinValueLData = new GridData();
					textMinValueLData.horizontalSpan = 2;
					textMinValueLData.horizontalAlignment = GridData.FILL;
					textMinValueLData.grabExcessHorizontalSpace = true;
					textMinValue = new Text(composite1, SWT.BORDER);
					textMinValue.setLayoutData(textMinValueLData);
				}
				{
					labelMaxValue = new Label(composite1, SWT.NONE);
					GridData labelMaxValueLData = new GridData();
					labelMaxValue.setLayoutData(labelMaxValueLData);
					labelMaxValue.setText("Maximum Value:");
				}
				{
					GridData textMaximumValueLData = new GridData();
					textMaximumValueLData.horizontalSpan = 2;
					textMaximumValueLData.horizontalAlignment = GridData.FILL;
					textMaximumValueLData.grabExcessHorizontalSpace = true;
					textMaxValue = new Text(composite1, SWT.BORDER);
					textMaxValue.setLayoutData(textMaximumValueLData);
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
	 * @param minValue the min value
	 * @param maxValue the max value
	 */
	private void initValues(Integer value, Integer minValue, Integer maxValue){
		textValue.setText(value + "");
		if(minValue != null && maxValue != null){
			textMinValue.setText(minValue + "");
			textMaxValue.setText(maxValue + "");
		}else {
			textMinValue.setText("");
			textMaxValue.setText("");
		}
	}
	
	/**
	 * Entered value for this property
	 * <b>Note:</b> If a minimum is set, the maximum has to be set too. Caller has to check this before use!
	 * @return the property value as Integer, or null if nothing has entered
	 * @throws NumberFormatException if the entered value is not a Integer confirm string
	 */
	public Integer getValue() throws NumberFormatException {
		if(!textValue.getText().isEmpty()){
			Integer value = new Integer(textValue.getText());		
			return value;
		}
		return null;
	}
	
	/**
	 * Entered Minimum value for this property
	 * <b>Note:</b> If a minimum is set, the maximum has to be set too. Caller has to check this before use!
	 *
	 * @return minimum value as Integer, null if no value has been set
	 * @throws NumberFormatException the number format exception
	 */
	public Integer getMinValue() throws NumberFormatException {
		if (!textMinValue.getText().isEmpty()) {
			Integer minValue = new Integer(textMinValue.getText());
			return minValue;
		}
		return null;
	}

	/**
	 * Entered Maximum value for this property.
	 * <b>Note:</b> If a maximum is set, the minimum has to be set too. Caller has to check this before use!
	 * @return maximum value as Integer, null if no value has been set
	 * @throws NumberFormatException if the entered value is not a Integer confirm string
	 */
	public Integer getMaxValue() throws NumberFormatException{
		if(!textMaxValue.getText().isEmpty()){
			Integer maxValue = new Integer(textMaxValue.getText());
			return maxValue;
		}
		return null;
	}

}
