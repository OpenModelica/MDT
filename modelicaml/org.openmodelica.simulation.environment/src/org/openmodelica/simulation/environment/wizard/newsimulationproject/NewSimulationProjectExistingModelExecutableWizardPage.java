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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.openmodelica.simulation.environment.Activator;
import org.openmodelica.simulation.environment.preferences.page.WorkbenchPreferencePageDefaultConfiguration;


// TODO: Auto-generated Javadoc
/**
 * This "New" wizard page allows creating a simulation project using existing Modelica code.
 * The user selects Modelica classes and a Modelica main class to be load from OMC.
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */

public class NewSimulationProjectExistingModelExecutableWizardPage extends WizardPage {
	 
	/** The model exe file path. */
	private String modelExeFilePath;
	
	/** The model init txt file path. */
	private String modelInitTxtFilePath;
	
	/** The project parent folder. */
	private File projectParentFolder;
	
	/** The text path to project parent folder. */
	private Text textPathToProjectParentFolder;
	
	/** The text path to model exe file. */
	private Text textPathToModelExeFile;
	
	/** The text path to model init txt file. */
	private Text textPathToModelInitTxtFile;
	
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

//	private ISelection selection;

	/**
 * Constructor for SampleNewWizardPage.
 *
 * @param selection the selection
 */
	public NewSimulationProjectExistingModelExecutableWizardPage(ISelection selection) {
		super("New Simulation Project Wizard");
		setTitle("Create a new Simulation Project");
		setDescription("Create a new simulation project using an existing Modelica model");
		projectParentFolder = null;
//		this.selection = selection;
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
		//EXECUTABLE MODEL CREATED BY OMC
		{
			Label label = new Label(compositePath, SWT.NULL);
			GridData labelLData = new GridData();
			labelLData.grabExcessHorizontalSpace = true;
			labelLData.horizontalAlignment = GridData.FILL;
			labelLData.horizontalSpan = 3;
			label.setLayoutData(labelLData);
			label.setText("Executable model file (*.exe):");
		}
		{
			textPathToModelExeFile = new Text(compositePath, SWT.BORDER | SWT.SINGLE);
			GridData gd = new GridData();
			gd.horizontalSpan = 2;
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;
			textPathToModelExeFile.setLayoutData(gd);
			textPathToModelExeFile.setText("model.exe");
			textPathToModelExeFile.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					if(textPathToModelExeFile.getText().equals("model.exe")){
						textPathToModelExeFile.setText("");
					}
				}
			});
			textPathToModelExeFile.addModifyListener(new ModifyListener() {
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
					String modelicaModelExeFile = handleBrowseForFile("Please select the executable model file", null, "*.exe", "Executable files (*.exe)");
		            if(modelicaModelExeFile!=null){
		            	textPathToModelExeFile.setText(modelicaModelExeFile);
		            	modelExeFilePath = modelicaModelExeFile;
		            }
				}
			});
		}
		
		//MODEL INIT TXT FILE
		{
			Label label = new Label(compositePath, SWT.NULL);
			GridData labelLData = new GridData();
			labelLData.grabExcessHorizontalSpace = true;
			labelLData.horizontalSpan = 3;
			labelLData.horizontalAlignment = GridData.FILL;
			label.setLayoutData(labelLData);
			label.setText("Model init file (*.txt):");
		}
		{
			textPathToModelInitTxtFile = new Text(compositePath, SWT.BORDER | SWT.SINGLE);
			GridData gd = new GridData();
			gd.horizontalSpan = 2;
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;
			textPathToModelInitTxtFile.setLayoutData(gd);
			textPathToModelInitTxtFile.setText("init.txt");
			textPathToModelInitTxtFile.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					if(textPathToModelInitTxtFile.getText().equals("init.txt")){
						textPathToModelInitTxtFile.setText("");
					}
				}
			});
			textPathToModelInitTxtFile.addModifyListener(new ModifyListener() {
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
					String modelicaModelInitTxtFile = null;
					if(modelExeFilePath!=null) modelicaModelInitTxtFile =handleBrowseForFile("Please select the model init text file", modelExeFilePath, "*.txt", "Text files (*.txt)");
					else modelicaModelInitTxtFile = handleBrowseForFile("Please select the model init text file", null, "*.txt", "Text files (*.txt)");
					
					if(modelicaModelInitTxtFile!=null){
						textPathToModelInitTxtFile.setText(modelicaModelInitTxtFile);
						modelInitTxtFilePath = modelicaModelInitTxtFile;
					}
				}
			});
			
		}
		//PROJECT FOLDER
		{
			Label label = new Label(compositePath, SWT.NULL);
			GridData labelLData = new GridData();
			labelLData.grabExcessHorizontalSpace = true;
			labelLData.horizontalSpan = 3;
			labelLData.horizontalAlignment = GridData.FILL;
			label.setLayoutData(labelLData);
			label.setText("Project Folder:");
		}
		//GET WORKSPACE PATH AND POINT ON org.openmodelica.simulation.projects
		File workspaceFolder = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
//		projectParentFolderPath = new File( workspaceFolder.getPath() + "/org.openmodelica.simulation.projects");
		projectParentFolder = new File( workspaceFolder.getPath() + "/org.openmodelica.simulation.projects");
		
		{
			textPathToProjectParentFolder = new Text(compositePath, SWT.BORDER | SWT.SINGLE);
			GridData gd = new GridData();
			gd.horizontalSpan = 2;
			gd.horizontalAlignment = GridData.FILL;
			textPathToProjectParentFolder.setLayoutData(gd);
			textPathToProjectParentFolder.setText("/...projects");
			
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
					
					File newParentFolder = handleBrowseForFolder( projectParentFolder, "Please select a folder to store the Simulation Project in");
					
					if(newParentFolder != null){
						projectParentFolder = newParentFolder;
						textPathToProjectParentFolder.setText(projectParentFolder.getPath());
					}
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
		
		GridData fileTextLData = new GridData();
		fileTextLData.horizontalAlignment = GridData.FILL;
		fileTextLData.grabExcessHorizontalSpace = true;
		fileTextLData.horizontalSpan = 2;
		fileTextLData.verticalSpan = 1;
		
		{
			Label labelProjectName = new Label(compositeAttributes,
					SWT.NULL);
			labelProjectName.setText("Project name:");
		}
		{
			textProjectName = new Text(compositeAttributes, SWT.BORDER | SWT.SINGLE);

			textProjectName.setLayoutData(fileTextLData);
			
			
			Date dt = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			
			textProjectName.setText("Model_" + df.format(dt));
			
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
//				textPathToModelExeFile.setText(container1.getFullPath().toString());
//			}
//		}
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 * @param message the message to be displayed on the file browser
	 * @param dir the directory path to point on at start @see #org.eclipse.swt.widgets.FileDialog
	 * @param extension filter file by this an extension e.g. *.txt
	 * @param extensionDescription description for the extension filter e.g. Text files (*.txt)
	 * 
	 * @return A file, null if nothing has been selected
	 */

	private String handleBrowseForFile(String message, String dir, String extension, String extensionDescription) {

		FileDialog fileDialog = new FileDialog(getShell());
		if(dir!=null)fileDialog.setFilterPath(dir);
		fileDialog.setFilterExtensions(new String[] { extension });
		fileDialog.setFilterNames(new String[] { extensionDescription });
		fileDialog.setText(message);
		String selectedFile = fileDialog.open();
		if (selectedFile != null) {
			File file = new File(selectedFile);
			return file.getPath();
		}
		return null;
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
		//VALIDATE MODEL EXE
		if (getPathToModelicaMainclassFile().length() == 0) {
			updateStatus("Please select or enter a path to an existing model executable file");
			return;
		}
		if (!(new File(getPathToModelicaMainclassFile())).exists()) {
			updateStatus("Model executable file must exist");
			return;
		}
		if (!(new File(getPathToModelicaMainclassFile())).canRead()) {
			updateStatus("Model executable file must be readable");
			return;
		}
		//VALIDATE MODEL INIT
		if (getPathToModelicaMainclassFile().length() == 0) {
			updateStatus("Please select or enter a path to an existing model init file");
			return;
		}
		if (!(new File(getPathToModelicaMainclassFile())).exists()) {
			updateStatus("Model init file must exist");
			return;
		}
		if (!(new File(getPathToModelicaMainclassFile())).canRead()) {
			updateStatus("Model init file must be readable");
			return;
		}
		//VALIDATE PROJECT FOLDER
		if (projectParentFolder == null) {
			updateStatus("Please select or enter a project folder");
			return;
		}
		if (!(projectParentFolder).canRead()) {
			updateStatus("Folder must be readable");
			return;
		}
		if (!(projectParentFolder).canWrite()) {
			updateStatus("Folder must be writable");
			return;
		}
		//VALIDATE PROJECT NAME
		if (fileName.length() == 0) {
			updateStatus("Project name must be specified");
			return;
		}
		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) { //TODO [201008] WLADIMIR FRAGEN WAS ERLAUBT IST
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
	 * Gets the path to modelica mainclass file.
	 *
	 * @return the path to modelica mainclass file
	 */
	public String getPathToModelicaMainclassFile() {
		return textPathToModelExeFile.getText();
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
	 * Gets the model exe file.
	 *
	 * @return the model exe file
	 */
	public String getModelExeFile(){
		return modelExeFilePath;
	}
	
	/**
	 * Gets the model init txt file.
	 *
	 * @return the model init txt file
	 */
	public String getModelInitTxtFile(){
		return modelInitTxtFilePath;
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
}