/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.debug.ui.propertypages;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.modelica.mdt.debug.core.IMDTBreakpoint;
import org.modelica.mdt.debug.ui.SWTFactory;
import org.modelica.mdt.internal.core.ErrorManager;

/**
 * Property page for configuring IJavaBreakpoints.
 */
public class MDTBreakpointPage extends PropertyPage {
	
	protected Button fEnabledButton;
	protected Button fHitCountButton;
	protected Text fHitCountText;
	protected Combo fSuspendPolicy;
	protected List<String> fErrorMessages= new ArrayList<String>();
		
	/**
	 * Constant for the empty string
	 */
	protected static final String EMPTY_STRING = ""; //$NON-NLS-1$
	
	
	/**
	 * Store the breakpoint properties.
	 * @see org.eclipse.jface.preference.IPreferencePage#performOk()
	 */
	public boolean performOk() {
		IWorkspaceRunnable wr = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				/*IMDTBreakpoint breakpoint = */getBreakpoint();
				doStore();
			}
		};
		try {
			ResourcesPlugin.getWorkspace().run(wr, null, 0, null);
		} 
		catch (CoreException e) { 
			ErrorManager.logError(e);
		}
		return super.performOk();
	}
	
	/**
	 * Adds the given error message to the errors currently displayed on this page.
	 * The page displays the most recently added error message.
	 * Clients should retain messages that are passed into this method as the
	 * message should later be passed into removeErrorMessage(String) to clear the error.
	 * This method should be used instead of setErrorMessage(String).
	 * @param message the error message to display on this page.
	 */
	protected void addErrorMessage(String message) {
		fErrorMessages.remove(message);
		fErrorMessages.add(message);
		setErrorMessage(message);
		setValid(message == null);
	}
	
	/**
	 * Removes the given error message from the errors currently displayed on this page.
	 * When an error message is removed, the page displays the error that was added
	 * before the given message. This is akin to popping the message from a stack.
	 * Clients should call this method instead of setErrorMessage(null).
	 * @param message the error message to clear
	 */
	protected void removeErrorMessage(String message) {
		fErrorMessages.remove(message);
		if (fErrorMessages.isEmpty()) {
			addErrorMessage(null);
		} else {
			addErrorMessage((String) fErrorMessages.get(fErrorMessages.size() - 1));
		}
	}
	
	/**
	 * Stores the values configured in this page. This method
	 * should be called from within a workspace runnable to
	 * reduce the number of resource deltas.
	 */
	protected void doStore() throws CoreException {
		IMDTBreakpoint breakpoint = getBreakpoint();
		storeEnabled(breakpoint);
	}

	/**
	 * Stores the value of the enabled state in the breakpoint.
	 * @param breakpoint the breakpoint to update
	 * @throws CoreException if an exception occurs while setting
	 *  the enabled state
	 */
	private void storeEnabled(IMDTBreakpoint breakpoint) throws CoreException {
		breakpoint.setEnabled(fEnabledButton.getSelection());
	}

	/**
	 * Creates the labels and editors displayed for the breakpoint.
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		setTitle(PropertyPageMessages.JavaBreakpointPage_11);
		noDefaultAndApplyButton();
		Composite mainComposite = createComposite(parent, 1);
		createLabels(mainComposite);
		try {
			createEnabledButton(mainComposite);
			createTypeSpecificEditors(mainComposite);
		} 
		catch (CoreException e) {ErrorManager.logError(e);}
		setValid(true);
		// if this breakpoint is being created, change the shell title to indicate 'creation'
		try {
            if (getBreakpoint().getMarker() != null) {
            	getShell().addShellListener(new ShellListener() {
                    public void shellActivated(ShellEvent e) {
                        Shell shell = (Shell)e.getSource();
                        shell.setText(MessageFormat.format(PropertyPageMessages.JavaBreakpointPage_10, new Object[]{getName(getBreakpoint())})); 
                        shell.removeShellListener(this);
                    }
                    public void shellClosed(ShellEvent e) {
                    }
                    public void shellDeactivated(ShellEvent e) {
                    }
                    public void shellDeiconified(ShellEvent e) {
                    }
                    public void shellIconified(ShellEvent e) {
                    }
                });
            }
        } catch (Exception e) {
        }
		return mainComposite;
	}
	
    /**
     * Returns the name of the given element.
     * 
     * @param element the element
     * @return the name of the element
     */
    private String getName(IAdaptable element) {
        IWorkbenchAdapter adapter = (IWorkbenchAdapter) element.getAdapter(IWorkbenchAdapter.class);
        if (adapter != null) {
            return adapter.getLabel(element);
        } 
        return EMPTY_STRING;
    }	
	
	/**
	 * Creates the labels displayed for the breakpoint.
	 * @param parent
	 */
	protected void createLabels(Composite parent) {
		Composite labelComposite = createComposite(parent, 2);
		try {
			String typeName = ((IMDTBreakpoint) getElement()).getFailureFilter();
			if (typeName != null) {
				createLabel(labelComposite, PropertyPageMessages.JavaBreakpointPage_3); 
				Text text = SWTFactory.createText(labelComposite, SWT.READ_ONLY, 1, typeName);
				text.setBackground(parent.getBackground());
			}
			createTypeSpecificLabels(labelComposite);
		} catch (CoreException ce) {
			ErrorManager.logError(ce);
		}
	}

	
	/**
	 * Creates the button to toggle enablement of the breakpoint
	 * @param parent
	 * @throws CoreException
	 */
	protected void createEnabledButton(Composite parent) throws CoreException {
		fEnabledButton = createCheckButton(parent, PropertyPageMessages.JavaBreakpointPage_5); 
		fEnabledButton.setSelection(getBreakpoint().isEnabled());
	}
	
	/**
	 * Returns the breakpoint that this preference page configures
	 * @return the breakpoint this page configures
	 */
	protected IMDTBreakpoint getBreakpoint() {
		return (IMDTBreakpoint) getElement();
	}
	
	/**
	 * Allows subclasses to add type specific labels to the common Java
	 * breakpoint page.
	 * @param parent
	 */
	protected void createTypeSpecificLabels(Composite parent) {}
	
	/**
	* Allows subclasses to add type specific editors to the common Java
	* breakpoint page.
	* @param parent
	*/
   protected void createTypeSpecificEditors(Composite parent) throws CoreException {}
	
	/**
	 * Creates a fully configured text editor with the given initial value
	 * @param parent
	 * @param initialValue
	 * @return the configured text editor
	 */
	protected Text createText(Composite parent, String initialValue) {
		return SWTFactory.createText(parent, SWT.SINGLE | SWT.BORDER, 1, initialValue);
	}
	
	/**
	 * Creates a fully configured composite with the given number of columns
	 * @param parent
	 * @param numColumns
	 * @return the configured composite
	 */
	protected Composite createComposite(Composite parent, int numColumns) {
		return SWTFactory.createComposite(parent, parent.getFont(), numColumns, 1, GridData.FILL_HORIZONTAL, 0, 0);
	}

	/**
	 * Creates a fully configured check button with the given text.
	 * @param parent the parent composite
	 * @param text the label of the returned check button
	 * @return a fully configured check button
	 */
	protected Button createCheckButton(Composite parent, String text) {
		return SWTFactory.createCheckButton(parent, text, null, false, 1);
	}

	/**
	 * Creates a fully configured label with the given text.
	 * @param parent the parent composite
	 * @param text the test of the returned label
	 * @return a fully configured label
	 */
	protected Label createLabel(Composite parent, String text) {
		return SWTFactory.createLabel(parent, text, 1);
	}

	/**
	 * Creates a fully configured radio button with the given text.
	 * @param parent the parent composite
	 * @param text the label of the returned radio button
	 * @return a fully configured radio button
	 */
	protected Button createRadioButton(Composite parent, String text) {
		return SWTFactory.createRadioButton(parent, text, 1);
	}
	
	/**
	 * Check to see if the breakpoint should be deleted.
	 */
	public boolean performCancel() {
		return super.performCancel();
	}
}
