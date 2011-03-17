/*
 * 
 */
package org.openmodelica.simulation.environment.wizard.newsimulationproject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.openmodelica.simulation.environment.Activator;
import org.openmodelica.simulation.environment.preferences.page.WorkbenchPreferencePageDefaultConfiguration;


// TODO: Auto-generated Javadoc
/**
 * This "New" wizard page allows creating a simulation project using existing Modelica code.
 * Option 1: The user selects Modelica classes and a Modelica main class to be load from OMC.
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */

public class NewSimulationProjectExistingCodeWizardPage extends WizardPage {

	 {
		 //Register as a resource user - SWTResourceManager will
		 //handle the obtaining and disposing of resources
		 //SWTResourceManager.registerResourceUser(this);
	 }
	 
	/** The project parent folder. */
	private File projectParentFolder;
	
	/** The text path to project parent folder. */
	private Text textPathToProjectParentFolder;
	
	/** The text modelica mainclass file. */
	private Text textModelicaMainclassFile;
	
	/** The text project name. */
	private Text textProjectName;
	
	/** The text version. */
	private Text textVersion;
	
	/** The text author. */
	private Text textAuthor;
	
	/** The text company. */
	private Text textCompany;
	
	/** The table classes to be load. */
	private Table tableClassesToBeLoad;
	
	/** The arraylist classes to be load. */
	private ArrayList<File> arraylistClassesToBeLoad; //TODO Fehler da referenzen auf files... sollte eher pfad zu den daten sein...!
	
	/** The styled text comment. */
	StyledText styledTextComment;

	/** The composite path. */
	private Composite compositePath;
	
	/** The composite attributes. */
	private Composite compositeAttributes;

//	private ISelection selection;

	/**
 * Constructor for a wizard page to create a project from existing code.
 *
 * @param selection the selection
 */
	public NewSimulationProjectExistingCodeWizardPage(ISelection selection) {
		super("New Simulation Project Wizard");
		projectParentFolder = null;
		arraylistClassesToBeLoad = new ArrayList<File>();
		setTitle("Create a new Simulation Project");
		setDescription("Create a new simulation project using an existing Modelica model");
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
		//CLASSES TO BE LOAD FROM THE OPENMODELICA COMPILER
		{
			Label label = new Label(compositePath, SWT.NONE);
			GridData labelLData = new GridData();
			labelLData.grabExcessHorizontalSpace = true;
			labelLData.horizontalSpan = 3;
			labelLData.horizontalAlignment = GridData.FILL;
			label.setLayoutData(labelLData);
			label.setText("Classes to be load from OMC");
		}
		{
			GridData tableClassesToBeLoadLData = new GridData();
			tableClassesToBeLoadLData.horizontalAlignment = GridData.FILL;
			tableClassesToBeLoadLData.grabExcessHorizontalSpace = true;
			tableClassesToBeLoadLData.horizontalSpan = 2;
			tableClassesToBeLoadLData.heightHint = 44;
			tableClassesToBeLoad = new Table(compositePath, SWT.H_SCROLL | SWT.V_SCROLL);
			tableClassesToBeLoad.setLayoutData(tableClassesToBeLoadLData);
			tableClassesToBeLoad.setHeaderVisible(true);
			tableClassesToBeLoad.setSize(600, 44);
			//TODO Hier muss noch verhindert werden, dass das menu erscheint wenn gar kein element ausgewählt oder gar keins vorhanden ist...!
			 Menu menu = new Menu(getShell(), SWT.POP_UP);
			    MenuItem item = new MenuItem(menu, SWT.PUSH);
			    item.setText("Delete selected Item?");
			    item.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						tableClassesToBeLoad.remove(tableClassesToBeLoad.getSelectionIndex());
					}
				});
			    tableClassesToBeLoad.setMenu(menu);
		    TableColumn tcFileName = new TableColumn(tableClassesToBeLoad, SWT.LEFT);
		    tcFileName.setText("Name");
		    
		    tcFileName.setWidth(300);
//		    tcFileSize.setWidth(100);
		    tableClassesToBeLoad.setToolTipText("List of all classes to be load from OMC.\nTip: You can delete a file by selecting it with the right mouse button");
		}
		{
			Button buttonClassesToBeLoad;
			buttonClassesToBeLoad = new Button(compositePath, SWT.PUSH | SWT.CENTER);
			GridData buttonClassesToBeLoadLData = new GridData();
			buttonClassesToBeLoadLData.horizontalAlignment = GridData.FILL;
			buttonClassesToBeLoadLData.grabExcessHorizontalSpace = true;
			buttonClassesToBeLoadLData.verticalAlignment = GridData.BEGINNING;
			buttonClassesToBeLoad.setLayoutData(buttonClassesToBeLoadLData);
			buttonClassesToBeLoad.setText("Load Class...");
			buttonClassesToBeLoad.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					MessageDialog messageDialog = new MessageDialog(getShell(), "Classes to be load from OMC", null,
					        "Do you want to select a single modelica class file or a folder containing classes to be load from OMC?", MessageDialog.QUESTION,
					        new String[] { "Folder", "File" }, 1);
					int click = messageDialog.open();
					if (click == 0) {
					    File classFolder = handleBrowseForFolder(null, "Please select a folder containing classes to be loaded from OMC:");
					    if(classFolder!=null){
					    	TableItem item = new TableItem(tableClassesToBeLoad, SWT.NULL);
					    	item.setText(new String[]{classFolder.getName()});
					    	arraylistClassesToBeLoad.add(classFolder);
					    }
					}
					else if(click == 1){
						File classFile = handleBrowseForModelicaFile("Please select a single class to be loaded from OMC:");
						if(classFile!=null){
							TableItem item = new TableItem(tableClassesToBeLoad, SWT.NULL);
							item.setText(new String[]{classFile.getName()});
							arraylistClassesToBeLoad.add(classFile);
							tableClassesToBeLoad.pack();
							dialogChanged();
						}
					}
				}
			});
		}
		//MODELICA MAINCLASS
		{
			Label label = new Label(compositePath, SWT.NULL);
			GridData labelLData = new GridData();
			labelLData.grabExcessHorizontalSpace = true;
			labelLData.horizontalSpan = 3;
			labelLData.horizontalAlignment = GridData.FILL;
			label.setLayoutData(labelLData);
			label.setText("Full qualified name of the Modelica main class file");
		}
		{
			textModelicaMainclassFile = new Text(compositePath, SWT.BORDER | SWT.SINGLE);
			GridData gd = new GridData();
			gd.horizontalSpan = 3;
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;
			textModelicaMainclassFile.setLayoutData(gd);
			textModelicaMainclassFile.setText("Full qualified name of main class");
			textModelicaMainclassFile.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					if(textModelicaMainclassFile.getText().equals("Full qualified name of main class")){
						textModelicaMainclassFile.setText("");
					}
				}
			});
			textModelicaMainclassFile.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					dialogChanged();
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
			label.setText("Path to Project Folder:");
		}
		//GET WORKSPACE PATH AND POINT ON org.openmodelica.simulation.projects
		File workspaceFolder = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
		projectParentFolder = new File( workspaceFolder.getPath() + "/org.openmodelica.simulation.projects");
		
		{
			textPathToProjectParentFolder = new Text(compositePath, SWT.BORDER | SWT.SINGLE);
			GridData gd = new GridData();
			gd.horizontalSpan = 2;
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;
			textPathToProjectParentFolder.setLayoutData(gd);
			textPathToProjectParentFolder.setEditable(false);
			textPathToProjectParentFolder.setText("Default Project Folder");
			
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
					
					
					File newParentFolder = handleBrowseForFolder( new File(projectParentFolder.getPath()), "Please select a folder to store the Simulation Project in");
					
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
			labelProjectName.setText("* Project name:");
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
//				textPathToModelicaMainclassFile.setText(container1.getFullPath().toString());
//			}
//		}
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 *
	 * @param message the message
	 * @return A modelica file which should be loaded, null of nothing has been selected
	 */

	private File handleBrowseForModelicaFile(String message) {

		FileDialog fileDialog = new FileDialog(getShell());
		fileDialog.setFilterPath("/");
		fileDialog.setFilterExtensions(new String[] { "*.mo" });
		fileDialog.setFilterNames(new String[] { "Modelica files (*.mo)" });
		fileDialog.setText(message);
		String selectedFile = fileDialog.open();
		if (selectedFile != null) {
			File file = new File(selectedFile);
			return file;
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
		//VALIDATE MODELICA CLASSES TO BE LOAD FROM OMC
		if (tableClassesToBeLoad.getItemCount() == 0) {
			updateStatus("Please select a folder or class file containing classes to be load");
			return;
		}
		
		for(File f : arraylistClassesToBeLoad){
			if (!f.exists()) {
				updateStatus(f.getName() + " does not exist");
				return;
			}
			if (!f.canRead()) {
				updateStatus(f.getName() + " must be readable");
				return;
			}
		}
		
		//VALIDATE MODELICA MODEL
		if (getModelicaMainclassName().length() == 0) {
			updateStatus("Enter a full qualified name of the Modelica main class");
			return;
		}
//		if (!(new File(getModelicaMainclassName())).exists()) {
//			updateStatus("Modelica model must exist");
//			return;
//		}
//		if (!(new File(getModelicaMainclassName())).canRead()) {
//			updateStatus("Modelica model must be readable");
//			return;
//		}
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
	 * Gets the modelica mainclass name.
	 *
	 * @return Full qualified name of the used modelica main class
	 */
	public String getModelicaMainclassName() {
		return textModelicaMainclassFile.getText();
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
	 * Gets the all classes to be load.
	 *
	 * @return A List of all absolute paths of selected modelica models or files
	 */
	public ArrayList<String> getAllClassesToBeLoad(){
		
		ArrayList<String> tempList = new ArrayList<String>();
		
		for(File f : arraylistClassesToBeLoad)
			tempList.add(f.getAbsolutePath());
		
		return tempList;
	}
}