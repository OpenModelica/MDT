/**
 * 
 * 
 * @author Adrian Pop, adrpo@ida.liu.se 
 **/
package org.modelica.mdt.debug.ui.launcher;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.modelica.mdt.debug.core.launcher.IMDTConstants;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;

/**
 * Tab to specify the MDT program to run/debug.
 */
public class MDTMainTab extends AbstractLaunchConfigurationTab {

	private Text fProgramText;
	private Text fProgramArguments;
	
	private Button fProgramButton;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Font font = parent.getFont();
		Composite comp = new Composite(parent, SWT.NONE);
		setControl(comp);
		GridLayout compLayout = new GridLayout();
		comp.setLayout(compLayout);
		comp.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		comp.setFont(font);
		createVerticalSpacer(comp, 1);
		
		Composite top = new Composite(comp, SWT.NONE);
		GridLayout topLayout = new GridLayout();
		topLayout.verticalSpacing   = 0;
		topLayout.horizontalSpacing = 2;		
		topLayout.numColumns = 3;
		top.setLayout(topLayout);
		top.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
		top.setFont(font);
		createVerticalSpacer(top, 3);
		
		Label programLabel = new Label(top, SWT.NONE);
		programLabel.setText("&Program:");
		GridData gd = new GridData(GridData.BEGINNING);
		programLabel.setLayoutData(gd);
		programLabel.setFont(font);

		fProgramText = new Text(top, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fProgramText.setLayoutData(gd);
		fProgramText.setFont(font);
		fProgramText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
		
		fProgramButton = createPushButton(top, "&Browse...", null); //$NON-NLS-1$
		fProgramButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				browseEXEFiles();
			}
		});	

		createSeparator(comp, 1);
		
		Composite bottom = new Composite(comp, SWT.NONE);
		GridLayout bottomLayout = new GridLayout(1, false);
		bottom.setLayout(bottomLayout);
		bottom.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		bottom.setFont(font);
		createVerticalSpacer(bottom, 3);
		
		Label programArgumentsLabel = new Label(bottom, SWT.NONE);
		gd = new GridData(GridData.BEGINNING);				
		programArgumentsLabel.setText("&Arguments:");		
		programArgumentsLabel.setLayoutData(gd);
		programArgumentsLabel.setFont(font);

		gd = new GridData(GridData.FILL, GridData.FILL, true, true);	
		gd.grabExcessVerticalSpace = true;
		fProgramArguments = new Text(bottom, SWT.MULTI | SWT.BORDER);
		fProgramArguments.setLayoutData(gd);
		fProgramArguments.setFont(font);
		fProgramArguments.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
	}

	/**
	 * Open a resource chooser to select a MDT program
	 */
	protected void browseEXEFiles() 
	{
		ResourceListSelectionDialog dialog = new ResourceListSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(),
				IResource.FILE);
		dialog.setTitle("Executable program");
		dialog.setMessage("Select Executable Program");
		// TODO: single select
		if (dialog.open() == Window.OK) 
		{
			Object[] files = dialog.getResult();
			IFile file = (IFile) files[0];
			fProgramText.setText(file.getLocation().toOSString());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) 
	{
		// fProgramArguments.setText("+d = interactiveCorba");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
	 */
	public void initializeFrom(ILaunchConfiguration configuration) 
	{
		try 
		{
			String program = configuration.getAttribute(IMDTConstants.ATTR_MDT_PROGRAM, (String) null);
			if (program != null) {
				fProgramText.setText(program);
			}
			String programArguments = configuration.getAttribute(IMDTConstants.ATTR_MDT_ARGUMENTS, (String) null);
			if (programArguments != null) {
				fProgramArguments.setText(programArguments);
			}			
		} catch (CoreException e) {
			setErrorMessage(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void performApply(ILaunchConfigurationWorkingCopy configuration) 
	{
		String program = fProgramText.getText().trim();
		if (program.length() == 0) 
		{
			program = null;
		}
		configuration.setAttribute(IMDTConstants.ATTR_MDT_PROGRAM, program);
		String programArguments = fProgramArguments.getText().trim();
		if (programArguments.length() == 0) 
		{
			programArguments = null;
		}
		configuration.setAttribute(IMDTConstants.ATTR_MDT_ARGUMENTS, programArguments);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
	 */
	public String getName() {
		return "Main";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
	 */
	public boolean isValid(ILaunchConfiguration launchConfig) {
		String text = fProgramText.getText();
		if (text.length() > 0) 
		{
			IPath path = new Path(text);
			if (ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path) == null) {
				setErrorMessage("Specified program does not exist");
				return false;
			}
		} else {
			setMessage("Specify a program");
		}
		return super.isValid(launchConfig);
	}
}
