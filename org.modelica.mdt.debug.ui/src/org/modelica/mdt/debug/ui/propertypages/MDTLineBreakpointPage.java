/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Linkoping University,
 * Department of Computer and Information Science,
 * SE-58183 Linkoping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 
 * AND THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S  
 * ACCEPTANCE OF THE OSMC PUBLIC LICENSE.
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from Linkoping University, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS
 * OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 */
package org.modelica.mdt.debug.ui.propertypages;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.core.breakpoints.MDTLineBreakpoint;

/**
 * @author Adeel Asghar
 *
 */
/**
 * Property page for configuring MDTLineBreakpoints.
 */
@SuppressWarnings("restriction")
public class MDTLineBreakpointPage extends PropertyPage {
	
	protected Button fEnabledButton;
	protected Button fHitCountButton;
	protected Text fHitCountText;
	protected Button fTimeCheckBoxButton;
	protected Text fTimeValueText;
	protected Button fTimeEqualRadio;
	protected Button fTimeGreaterRadio;
	protected Button fTimeGreaterEqualRadio;
	protected Button fTimeLessRadio;
	protected Button fTimeLessEqualRadio;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		noDefaultAndApplyButton();
		Composite mainComposite = SWTFactory.createComposite(parent, parent.getFont(), 1, 1, GridData.FILL_HORIZONTAL, 0, 0);
		try {
			createControls(mainComposite);
			setValid(true);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			MDTDebugCorePlugin.log(null, e);
		}
		return mainComposite;
	}
	
	/**
	 * Returns the breakpoint that this preference page configures
	 * @return the breakpoint this page configures
	 */
	protected MDTLineBreakpoint getBreakpoint() {
		return (MDTLineBreakpoint) getElement();
	}
	
	/**
	 * Creates the properties page controls like labels, text, checkbox etc. for the breakpoint.
	 * @param parent
	 * @throws CoreException 
	 */
	protected void createControls(Composite parent) throws CoreException {
		Composite pageComposite = SWTFactory.createComposite(parent, parent.getFont(), 2, 1, GridData.FILL_HORIZONTAL, 0, 0);
		// get the breakpoint file location info
		SWTFactory.createLabel(pageComposite, PropertyPageMessages.JavaLineBreakpointPage_21, 1);
		String filePath = getBreakpoint().getMarker().getResource().getLocation().toOSString();
		if (filePath != null)
			SWTFactory.createLabel(pageComposite, filePath, 1);
		// create the breakpoint line number info
		SWTFactory.createLabel(pageComposite, PropertyPageMessages.JavaLineBreakpointPage_2, 1);
		int lineNumber = getBreakpoint().getLineNumber();
		SWTFactory.createLabel(pageComposite, new Integer(lineNumber).toString(), 1);
		// create the enabled check box
		fEnabledButton = SWTFactory.createCheckButton(pageComposite, PropertyPageMessages.JavaBreakpointPage_5, null, false, 1);
		fEnabledButton.setSelection(getBreakpoint().isEnabled());
		// create the hit count controls
		Composite hitCountAndTimeConditionComposite = SWTFactory.createComposite(parent, parent.getFont(), 3, 1, 0, 0, 0);
		fHitCountButton = SWTFactory.createCheckButton(hitCountAndTimeConditionComposite, PropertyPageMessages.JavaBreakpointPage_4, null, false, 1);
		fHitCountButton.setLayoutData(new GridData());
		fHitCountButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				boolean enabled = fHitCountButton.getSelection();
				fHitCountText.setEnabled(enabled);
				if(enabled) {
					fHitCountText.setFocus();
				}
			}
		});
		fHitCountText = SWTFactory.createSingleText(hitCountAndTimeConditionComposite, 1);
		GridData gd = (GridData) fHitCountText.getLayoutData();
		gd.minimumWidth = 50;
		SWTFactory.createLabel(hitCountAndTimeConditionComposite, PropertyPageMessages.JavaBreakpointPage_4_1, 1);
		// get hit count values
		boolean hitCountEnabled = false;
		boolean hasHitCount = false;
		String hitCountText = "";
		if (getBreakpoint() != null) {
			hitCountEnabled = true;
			int hitCount = getBreakpoint().getHitCount();
			if (hitCount > 0) {
				hitCountText = new Integer(hitCount).toString();
				hasHitCount = true;
			}
		}
		fHitCountButton.setEnabled(hitCountEnabled);
		fHitCountButton.setSelection(hitCountEnabled & hasHitCount);
		fHitCountText.setEnabled(hasHitCount);
		fHitCountText.setText(hitCountText);
		// create time condition controls
		fTimeCheckBoxButton = SWTFactory.createCheckButton(hitCountAndTimeConditionComposite, PropertyPageMessages.JavaBreakpointPage_12, null, false, 1);
		fTimeCheckBoxButton.setLayoutData(new GridData());
		fTimeCheckBoxButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				boolean enabled = fTimeCheckBoxButton.getSelection();
				fTimeValueText.setEnabled(enabled);
				fTimeEqualRadio.setEnabled(enabled);
				fTimeGreaterRadio.setEnabled(enabled);
				fTimeGreaterEqualRadio.setEnabled(enabled);
				fTimeLessRadio.setEnabled(enabled);
				fTimeLessEqualRadio.setEnabled(enabled);
				if(enabled) {
					fTimeValueText.setFocus();
				}
			}
		});
		fTimeValueText = SWTFactory.createSingleText(hitCountAndTimeConditionComposite, 1);
		gd = (GridData) fTimeValueText.getLayoutData();
		gd.minimumWidth = 50;
		Composite radios = SWTFactory.createComposite(hitCountAndTimeConditionComposite, hitCountAndTimeConditionComposite.getFont(), 5, 1, GridData.FILL_HORIZONTAL, 0, 0);
		fTimeEqualRadio = SWTFactory.createRadioButton(radios, PropertyPageMessages.JavaBreakpointPage_13);
		fTimeEqualRadio.setLayoutData(new GridData());
		fTimeGreaterRadio = SWTFactory.createRadioButton(radios, PropertyPageMessages.JavaBreakpointPage_14);
		fTimeGreaterRadio.setLayoutData(new GridData());
		fTimeGreaterEqualRadio = SWTFactory.createRadioButton(radios, PropertyPageMessages.JavaBreakpointPage_15);
		fTimeGreaterEqualRadio.setLayoutData(new GridData());
		fTimeLessRadio = SWTFactory.createRadioButton(radios, PropertyPageMessages.JavaBreakpointPage_16);
		fTimeLessRadio.setLayoutData(new GridData());
		fTimeLessEqualRadio = SWTFactory.createRadioButton(radios, PropertyPageMessages.JavaBreakpointPage_17);
		fTimeLessEqualRadio.setLayoutData(new GridData());
		// get time condition values
		boolean timeConditionEnabled = false;
		boolean hasTimeValue = false;
		String timeValueText = "";
		int timeCondition = 0;
		if (getBreakpoint() != null) {
			timeConditionEnabled = true;
			double timeValue = Double.parseDouble(getBreakpoint().getTimeValue());
			if (timeValue > -1) {
				timeValueText = getBreakpoint().getTimeValue();
				hasTimeValue = true;
			}
			timeCondition = getBreakpoint().getTimeCondition();
		}
		fTimeCheckBoxButton.setEnabled(timeConditionEnabled);
		fTimeCheckBoxButton.setSelection(timeConditionEnabled & hasTimeValue);
		fTimeValueText.setEnabled(hasTimeValue);
		fTimeValueText.setText(timeValueText);
		fTimeEqualRadio.setEnabled(fTimeCheckBoxButton.getSelection());
		fTimeGreaterRadio.setEnabled(fTimeCheckBoxButton.getSelection());
		fTimeGreaterEqualRadio.setEnabled(fTimeCheckBoxButton.getSelection());
		fTimeLessRadio.setEnabled(fTimeCheckBoxButton.getSelection());
		fTimeLessEqualRadio.setEnabled(fTimeCheckBoxButton.getSelection());
		switch (timeCondition) {
		case 0:
			fTimeEqualRadio.setSelection(true);
			break;
		case 1:
			fTimeGreaterRadio.setSelection(true);
			break;
		case 2:
			fTimeGreaterEqualRadio.setSelection(true);
			break;
		case 3:
			fTimeLessRadio.setSelection(true);
			break;
		case 4:
			fTimeLessEqualRadio.setSelection(true);
			break;
		default:
			fTimeEqualRadio.setSelection(true);
			break;
		}
	}
	
	/**
	 * Store the breakpoint properties.
	 * @see org.eclipse.jface.preference.IPreferencePage#performOk()
	 */
	public boolean performOk() {
		IWorkspaceRunnable wr = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				doStore();
			}
		};
		try {
			ResourcesPlugin.getWorkspace().run(wr, null, 0, null);
		} 
		catch (CoreException e) {
			MDTDebugCorePlugin.log(null, e);
		}
		return super.performOk();
	}
	
	/**
	 * Stores the values configured in this page. This method
	 * should be called from within a workspace runnable to
	 * reduce the number of resource deltas.
	 */
	protected void doStore() throws CoreException {
		MDTLineBreakpoint breakpoint = getBreakpoint();
		if (breakpoint != null) {
			// set breakpoint enabled
			breakpoint.setEnabled(fEnabledButton.getSelection());
			// set hit count
			int hitCount = 0;
			if (fHitCountButton.getSelection()) {
				try {
					hitCount = Integer.parseInt(fHitCountText.getText());
				} 
				catch (NumberFormatException e) {}
			}
			breakpoint.setHitCount(hitCount);
			// set the time condition
			String timeValue = "-1";
			if (fTimeCheckBoxButton.getSelection()) {
				try {
					Double.parseDouble(fTimeValueText.getText());
					timeValue = fTimeValueText.getText();
				} 
				catch (NumberFormatException e) {}
			}
			breakpoint.setTimeValue(timeValue);
			// set time condition
			int timeCondition = 0;
			if (fTimeCheckBoxButton.getSelection()) {
				if (fTimeEqualRadio.getSelection())
					timeCondition = 0;
				else if (fTimeGreaterRadio.getSelection())
					timeCondition = 1;
				else if (fTimeGreaterEqualRadio.getSelection())
					timeCondition = 2;
				else if (fTimeLessRadio.getSelection())
					timeCondition = 3;
				else if (fTimeLessEqualRadio.getSelection())
					timeCondition = 4;
			}
			breakpoint.setTimeCondition(timeCondition);
		}
	}

}
