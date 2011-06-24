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
package org.openmodelica.simulation.environment.wizard.newsimulationproject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.openmodelica.simulation.environment.Activator;
import org.openmodelica.simulation.environment.preferences.page.WorkbenchPreferencePageDefaultConfiguration;


// TODO: Auto-generated Javadoc
/**
 * This "New" wizard page allows creating a simulation project using existing Modelica code.
 * Option 1: The user selects Modelica classes and a Modelica main class to be load from OMC.
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */
public class NewSimulationProjectModellingEnvironmentWizardPage extends WizardPage {

	{
		 //Register as a resource user - SWTResourceManager will
		 //handle the obtaining and disposing of resources
		 //SWTResourceManager.registerResourceUser(this);
	}
	
	/** The project parent folder. */
	private File projectParentFolder;
	
	/** The text path to project parent folder. */
	private Text textPathToProjectParentFolder;
	
	/** The text project name. */
	private Text textProjectName;
	
	/** The text version. */
	private Text textVersion;
	
	/** The text author. */
	private Text textAuthor;
	
	/** The text company. */
	private Text textCompany;
	
	/** The styled text comment. */
	StyledText styledTextComment;

	/** The composite path. */
	private Composite compositePath;
	
	/** The composite attributes. */
	private Composite compositeAttributes;
	
	/** The button create non interactive. */
	private Button buttonCreateNonInteractive;
	
	/** The button interactive. */
	private Button buttonInteractive;
	
	/** The label configuration. */
	private Label labelConfiguration;
	
	/** The composite create configuraion. */
	private Composite compositeCreateConfiguraion;
	
	/** The fullqualified main class name. */
	String fullqualifiedMainClassName;

//	private ISelection selection;

	/**
 * Constructor for a wizard page to create a project from existing code.
 *
 * @param selection the selection
 */
	public NewSimulationProjectModellingEnvironmentWizardPage(ISelection selection) {
		super("New Simulation Project Wizard");
		projectParentFolder = null;
		setTitle("Create a new Simulation Project");
		setDescription("This Wizard supports the user to create a new simulation project using an existing Modelica model");
//		this.selection = selection;
	}
	
	/**
	 * Constructor for a wizard page to create a project from existing code.
	 *
	 * @param selection the selection
	 * @param mainClassName the main class name
	 */
	public NewSimulationProjectModellingEnvironmentWizardPage(ISelection selection, String mainClassName) {
		super("New Simulation Project Wizard");
		projectParentFolder = null;
		setTitle("Create a new Simulation Project");
		setDescription("This Wizard supports the user to create a new simulation project using an existing Modelica model");
//		this.selection = selection;
		this.fullqualifiedMainClassName = mainClassName;
	}
	
	/**
	 * Creates the control.
	 *
	 * @param parent the parent
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.makeColumnsEqualWidth = true;
		container.setLayout(layout);
		createCompositePath(container);
		createCompositeName_Comment(container);
		initialize(container);
		dialogChanged();
		setControl(container);
	}

	/**
	 * Creates the composite path.
	 *
	 * @param container the container
	 */
	private void createCompositePath(Composite container) {

		compositePath = new Composite(container, SWT.NONE);
		GridLayout compositePathLayout = new GridLayout();
		compositePathLayout.makeColumnsEqualWidth = true;
		compositePathLayout.numColumns = 3;
		GridData compositePathLData = new GridData();
		compositePathLData.grabExcessHorizontalSpace = true;
		compositePathLData.horizontalAlignment = GridData.FILL;
		compositePathLData.verticalAlignment = GridData.FILL;
		compositePath.setLayoutData(compositePathLData);
		compositePath.setLayout(compositePathLayout);
		//CLASSES TO BE LOAD FROM THE OPENMODELICA COMPILER
		//MODELICA MAINCLASS
		//PROJECT FOLDER
		{
			Label label = new Label(compositePath, SWT.NULL);
			GridData labelLData = new GridData();
			labelLData.grabExcessHorizontalSpace = true;
			labelLData.horizontalSpan = 3;
			labelLData.horizontalAlignment = GridData.FILL;
			label.setLayoutData(labelLData);
			label.setText("Path to Project Folder:");
		}
		//GET WORKSPACE PATH AND POINT ON org.openmodelica.simulation.projects
		File workspaceFolder = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
		projectParentFolder = new File( workspaceFolder.getPath() + "/org.openmodelica.simulation.projects");
		
		{
			textPathToProjectParentFolder = new Text(compositePath, SWT.BORDER | SWT.SINGLE);
			GridData gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			textPathToProjectParentFolder.setLayoutData(gd);
			textPathToProjectParentFolder.setText(projectParentFolder.getPath());
			
			textPathToProjectParentFolder.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					dialogChanged();
				}
			});
		}
		{
			Button button = new Button(compositePath, SWT.PUSH);
			GridData buttonLData = new GridData();
			buttonLData.horizontalAlignment = GridData.FILL;
			buttonLData.grabExcessHorizontalSpace = true;
			button.setLayoutData(buttonLData);
			button.setText("Browse...");
			button.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					projectParentFolder = handleBrowseForFolder( new File(projectParentFolder.getPath()), "Please select a folder to store the Simulation Project in");
					textPathToProjectParentFolder.setText(projectParentFolder.getPath());
				}
			});
			
		}
	}

	/**
	 * Creates the composite name_ comment.
	 *
	 * @param container the container
	 */
	private void createCompositeName_Comment(Composite container) {

		compositeAttributes = new Composite(container, SWT.NONE);
		GridLayout compositeName_CommentLayout = new GridLayout();
		compositeName_CommentLayout.makeColumnsEqualWidth = true;
		compositeName_CommentLayout.numColumns = 3;
		GridData compositeName_CommentLData = new GridData();
		compositeName_CommentLData.horizontalAlignment = GridData.FILL;
		compositeName_CommentLData.grabExcessHorizontalSpace = true;
		compositeName_CommentLData.verticalAlignment = GridData.FILL;
		compositeName_CommentLData.grabExcessVerticalSpace = true;
		compositeAttributes.setLayoutData(compositeName_CommentLData);
		compositeAttributes.setLayout(compositeName_CommentLayout);
		{
			compositeCreateConfiguraion = new Composite(container, SWT.NONE);
			GridLayout compositeCreateConfiguraionLayout = new GridLayout();
			compositeCreateConfiguraionLayout.makeColumnsEqualWidth = true;
			GridData compositeCreateConfiguraionLData = new GridData();
			compositeCreateConfiguraionLData.verticalAlignment = GridData.FILL;
			compositeCreateConfiguraionLData.horizontalAlignment = GridData.FILL;
			compositeCreateConfiguraionLData.grabExcessHorizontalSpace = true;
			compositeCreateConfiguraionLData.grabExcessVerticalSpace = true;
			compositeCreateConfiguraion.setLayoutData(compositeCreateConfiguraionLData);
			compositeCreateConfiguraion.setLayout(compositeCreateConfiguraionLayout);
			{
				labelConfiguration = new Label(compositeCreateConfiguraion, SWT.NONE);
				GridData labelConfigurationLData = new GridData();
				labelConfiguration.setLayoutData(labelConfigurationLData);
				labelConfiguration.setText("Please select the simulation type.");
			}
//			{
//				buttonInteractive = new Button(compositeCreateConfiguraion, SWT.RADIO | SWT.LEFT);
//				GridData buttonInteractiveLData = new GridData();
//				buttonInteractive.setLayoutData(buttonInteractiveLData);
//				buttonInteractive.setText("Interactive Simulation");
//			}
			{
				buttonCreateNonInteractive = new Button(compositeCreateConfiguraion, SWT.RADIO | SWT.LEFT);
				GridData buttonCreateNonInteractiveLData = new GridData();
				buttonCreateNonInteractive.setLayoutData(buttonCreateNonInteractiveLData);
				buttonCreateNonInteractive.setText("Non-Interactive Simulation");
			}
			buttonCreateNonInteractive.setSelection(true);
		}

		GridData fileTextLData = new GridData();
		fileTextLData.horizontalAlignment = GridData.FILL;
		fileTextLData.grabExcessHorizontalSpace = true;
		fileTextLData.horizontalSpan = 2;
//		fileTextLData.verticalAlignment = GridData.FILL;
//		fileTextLData.grabExcessVerticalSpace = true;
		fileTextLData.verticalSpan = 1;
		
		{
			Label labelProjectName = new Label(compositeAttributes,
					SWT.NULL);
			labelProjectName.setText("*Project name:");
		}
		{
			textProjectName = new Text(compositeAttributes, SWT.BORDER | SWT.SINGLE);

			textProjectName.setLayoutData(fileTextLData);
			
			Scanner sc = new Scanner(fullqualifiedMainClassName);
			sc.useDelimiter("\\.");
			
			/*
			 * The main class name is the leaf class
			 * using a scanner it will be the last available string using next()
			 */
			
			String mainclassname = "myClass";
			while(sc.hasNext())
				mainclassname = sc.next();
				
			Date dt = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			
			textProjectName.setText(mainclassname + "_" + df.format(dt));
			textProjectName.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					dialogChanged();
				}
			});
		}
		{
			Label labelProjectVersion = new Label(compositeAttributes,
					SWT.NULL);
			labelProjectVersion.setText("Version:");
		}
		{
			textVersion = new Text(compositeAttributes, SWT.BORDER | SWT.SINGLE);
			textVersion.setText("1.0");

			textVersion.setLayoutData(fileTextLData);
			textVersion.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					if(textVersion.getText().isEmpty())
						textVersion.setText("1.0");
				}
			});
		}
		{
			Label labelProjectAuthor = new Label(compositeAttributes,
					SWT.NULL);
			labelProjectAuthor.setText("Author:");
		}
		{
			textAuthor = new Text(compositeAttributes, SWT.BORDER | SWT.SINGLE);

			textAuthor.setLayoutData(fileTextLData);
			
			IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
			
			String author = preferenceStore.getString(WorkbenchPreferencePageDefaultConfiguration.AUTHOR);
			textAuthor.setText(author);
			
			textAuthor.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					if(textAuthor.getText().isEmpty()){
						IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
						
						String author = preferenceStore.getString(WorkbenchPreferencePageDefaultConfiguration.AUTHOR);
						textAuthor.setText(author);
					}
				}
			});
		}
		{
			Label labelCompany = new Label(compositeAttributes,
					SWT.NULL);
			labelCompany.setText("Company:");
		}
		{
			textCompany = new Text(compositeAttributes, SWT.BORDER | SWT.SINGLE);

			textCompany.setLayoutData(fileTextLData);
			
			IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
			
			String company = preferenceStore.getString(WorkbenchPreferencePageDefaultConfiguration.COMPANY);
			textCompany.setText(company);
			
			textCompany.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					if(textCompany.getText().isEmpty()){
						IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
						
						String company = preferenceStore.getString(WorkbenchPreferencePageDefaultConfiguration.COMPANY);
						textCompany.setText(company);
					}
				}
			});
		}
		{
			Label labelComment;
			labelComment = new Label(compositeAttributes, SWT.NONE);
			GridData labelCommentLData = new GridData();
			labelComment.setLayoutData(labelCommentLData);
			labelComment.setText("Comment:");
			GridData styledTextCommentLData = new GridData();
			styledTextCommentLData.horizontalAlignment = GridData.FILL;
			styledTextCommentLData.grabExcessHorizontalSpace = true;
			styledTextCommentLData.horizontalSpan = 2;
			styledTextCommentLData.verticalAlignment = GridData.FILL;
			styledTextCommentLData.grabExcessVerticalSpace = true;
			styledTextCommentLData.verticalSpan = 5;
			styledTextComment = new StyledText(compositeAttributes, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			styledTextComment.setLayoutData(styledTextCommentLData);
			styledTextComment.setText("no comment yet");
			styledTextComment.setSize(220, 71);
		}
		
	}
	
	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 *
	 * @param container the container
	 */

	private void initialize(Composite container) {
//		if (selection != null && selection.isEmpty() == false
//				&& selection instanceof IStructuredSelection) {
//			IStructuredSelection ssel = (IStructuredSelection) selection;
//			if (ssel.size() > 1)
//				return;
//			Object obj = ssel.getFirstElement();
//			if (obj instanceof IResource) {
//				IContainer container1;
//				if (obj instanceof IContainer)
//					container1 = (IContainer) obj;
//				else
//					container1 = ((IResource) obj).getParent();
//				textPathToModelicaMainclassFile.setText(container1.getFullPath().toString());
//			}
//		}
	}

	/**
	 * Select a folder to work with in different ways.
	 *
	 * @param dir is the Current Directory where the file chooser should point on. Passing in null sets the file chooser to point to the user's default directory.
	 * @param message the message
	 * @return A Folder to work with as a File, or null if nothing has been selected
	 */
	private File handleBrowseForFolder(File dir, String message) {

		DirectoryDialog directoryDialog = new DirectoryDialog(getShell());
		
		if(dir != null){
			directoryDialog.setFilterPath(dir.getPath());
		}
		else{
			directoryDialog.setFilterPath("/");
		}
		
		directoryDialog.setMessage(message);
		directoryDialog.setText("Directory Dialog");
		String selectedDirectory = directoryDialog.open();
		if (selectedDirectory != null) {
			 File file = new File (selectedDirectory);
			 return file;
		}
		return null;
	}

	/**
	 * Ensures that the class list and both text fields are set.
	 */

	private void dialogChanged() {
		String fileName = getProjectName();
		//VALIDATE MODELICA CLASSES TO BE LOAD FROM OMC
		
		//VALIDATE MODELICA MODEL
		
		//VALIDATE PROJECT FOLDER
		if (getPathToProjectFolder().length() == 0) {
			updateStatus("Please select or enter a project folder");
			return;
		}
		if (!(new File(getPathToProjectFolder())).canRead()) {
			updateStatus("Folder must be readable");
			return;
		}
		if (!(new File(getPathToProjectFolder())).canWrite()) {
			updateStatus("Folder must be writable");
			return;
		}
		//VALIDATE PROJECT NAME
		if (fileName.length() == 0) {
			updateStatus("Project name must be specified");
			return;
		}
		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) { //TODO WLADIMIR FRAGEN WAS ERLAUBT IST
			updateStatus("Project name must be valid");
			return;
		}
		if (fileName.endsWith(".")) {
			updateStatus("Project name ends with a dot");
			return;
		}
		updateStatus(null);
	}

	/**
	 * Update status.
	 *
	 * @param message the message
	 */
	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	/**
	 * Gets the path to project folder.
	 *
	 * @return the path to project folder
	 */
	public String getPathToProjectFolder() {
		return textPathToProjectParentFolder.getText();
	}
	
	/**
	 * Gets the project parent folder.
	 *
	 * @return the project parent folder
	 */
	public File getProjectParentFolder(){
		return projectParentFolder;
	}
	
	/**
	 * Gets the project name.
	 *
	 * @return the project name
	 */
	public String getProjectName() {
		return textProjectName.getText();
	}

	/**
	 * Gets the project version.
	 *
	 * @return the project version
	 */
	public String getProjectVersion() {
		return textVersion.getText();
	}

	/**
	 * Gets the project author.
	 *
	 * @return the project author
	 */
	public String getProjectAuthor() {
		return textAuthor.getText();
	}

	/**
	 * Gets the project company.
	 *
	 * @return the project company
	 */
	public String getProjectCompany() {
		return textCompany.getText();
	}
	
	/**
	 * Gets the project comment.
	 *
	 * @return the project comment
	 */
	public String getProjectComment(){
		return styledTextComment.getText();
	}
	
	/**
	 * A user can create a configuration file afterwards if one of the radio buttons was selected.
	 *
	 * @return an <b>empty</b> String if nothing has been selected, <b>noninteractive</b> if create a non interactive simulation has been selected, <b>interactive</b> if create an interactive simulation has been selected
	 */
	public String getCreateConfig(){
		
		if(buttonCreateNonInteractive.getSelection())
			return "noninteractive";
		else if(buttonInteractive.getSelection())
			return "interactive";
		else
			return "";
	}
}