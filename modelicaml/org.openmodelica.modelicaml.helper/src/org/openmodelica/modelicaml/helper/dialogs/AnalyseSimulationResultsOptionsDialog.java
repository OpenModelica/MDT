package org.openmodelica.modelicaml.helper.dialogs;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.papyrus.infra.core.utils.BusinessModelResolver;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.helper.datacollection.VerificationDataCollector;
import org.openmodelica.modelicaml.helper.generators.GeneratorVeMScenariosBased;
import org.openmodelica.modelicaml.helper.structures.GeneratedModelsData;

public class AnalyseSimulationResultsOptionsDialog extends Dialog {

	private Element generatedModelsPackage;
	private String generatedModelsPackagePackgeNamePrefix = "Package containing VeMs: ";
	
	private String targetPackgeSelectionMessage = "Click on an package, that contains VeMs, " +
			"\r\nin the model explorer.";
	
	private String message = "This helper will simulate selected models (or skip the simulation if results files are provided) \r\nand analyze the result files.";
	
	private String dialogMessage = "";
	
	private HashSet<Button> buttons = new HashSet<Button>();
	private HashMap<Button,Label> maptButtonsToLabels = new HashMap<Button,Label>();
	
	private CLabel lblSelectionHint;
	private Button btnGeneratedModelsPackage;
	
	private GeneratedModelsData generatedModelsData;
	
	// default options selection
	private boolean simulate = true;
	private boolean recordOnlyRequirements = true;
	private String resultFilesFolderPath;
	
	// scenario based models generator
	private GeneratorVeMScenariosBased smg;

	private static final String defaultNegativeString = "Not specified ... ";

	private static final String selectionHintMessage = "Click on an element in the model explorer ... ";
	private static final String notValidString = "Not valid. ";
	
	private HashSet<String> filesFound = new HashSet<String>();

	private UmlModel umlModel;
	private VerificationDataCollector verificationDataCollector;
	
	
//	// only for debugging
//	public static void main(String[] args) {
//		AnalyseSimulationResulstOptionsDialog dialog = new AnalyseSimulationResulstOptionsDialog(new Shell(), null);
//		dialog.open();
//	}
	
	
	public AnalyseSimulationResultsOptionsDialog(Shell parentShell, Element generatedModelsPackage) {

		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP );
		
		this.generatedModelsPackage = generatedModelsPackage;

		dialogMessage = message;
		
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);
	}

	@Override
	protected Control createContents(Composite parent) {
		
		Control control = super.createContents(parent);
		
		// pre-settings: If no package was pre-selected then activate the button
		if (getGeneratedModelsPackage() == null) {
			btnGeneratedModelsPackage.setSelection(true);
			updateButton(btnGeneratedModelsPackage);
		}
		
		// validate the selection and enable/disable the ok button.
    	setOKButtonEnablement();
    	
		return control;
	}
	
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gl_container = new GridLayout(1, false);
		gl_container.horizontalSpacing = 1;
		container.setLayout(gl_container);
		
		CLabel lblInfo = new CLabel(container, SWT.NONE);
		lblInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		GridData gd_lblInfo = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_lblInfo.widthHint = 527;
		lblInfo.setLayoutData(gd_lblInfo);
		lblInfo.setTopMargin(5);
		lblInfo.setBottomMargin(5);
		lblInfo.setRightMargin(5);
		lblInfo.setLeftMargin(5);
		lblInfo.setText(dialogMessage);
		
		Group grpSources = new Group(container, SWT.SHADOW_ETCHED_IN);
		GridData gd_grpSources = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd_grpSources.heightHint = 41;
		gd_grpSources.widthHint = 525;
		grpSources.setLayoutData(gd_grpSources);
		grpSources.setText("Sources");
		
		// selection hint
		lblSelectionHint = new CLabel(grpSources, SWT.BORDER | SWT.SHADOW_OUT);
		lblSelectionHint.setTouchEnabled(true);
		lblSelectionHint.setToolTipText(selectionHintMessage);
		lblSelectionHint.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		lblSelectionHint.setBounds(53, 22, 410, 41);
		lblSelectionHint.setText(selectionHintMessage);
		lblSelectionHint.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "/icons/editor/ModelExplorer.png"));
		lblSelectionHint.setVisible(false);
		
		/*
		 * Target Package Selection
		 */
		btnGeneratedModelsPackage = new Button(grpSources, SWT.TOGGLE | SWT.CENTER);
		btnGeneratedModelsPackage.setToolTipText("Click on this button to enable selection of another element.");
		btnGeneratedModelsPackage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateButton(btnGeneratedModelsPackage);
			}
		});
		btnGeneratedModelsPackage.setBounds(10, 22, 37, 24);
		
		Label lblTargetPackage = new Label(grpSources, SWT.NONE);
		lblTargetPackage.setBounds(57, 27, 657, 15);

		// add to lists
		buttons.add(btnGeneratedModelsPackage);
		maptButtonsToLabels.put(btnGeneratedModelsPackage, lblTargetPackage);
		// update the image and the label text
		updateButton(btnGeneratedModelsPackage);
		
		
		// Options Group
		Group grpOptions = new Group(container, SWT.NONE);
		grpOptions.setLayout(new FormLayout());
		GridData gd_grpOptions = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd_grpOptions.heightHint = 79;
		grpOptions.setLayoutData(gd_grpOptions);
		grpOptions.setText("Options");
		
		
		final Button btnSimulate = new Button(grpOptions, SWT.RADIO);
		FormData fd_btnSimulate = new FormData();
		fd_btnSimulate.right = new FormAttachment(0, 514);
		fd_btnSimulate.top = new FormAttachment(0, 11);
		fd_btnSimulate.left = new FormAttachment(0, 7);
		btnSimulate.setLayoutData(fd_btnSimulate);
		btnSimulate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (btnSimulate.getSelection()) {
					setSimulate(true);
				}
				else {
					setSimulate(false);
				}
				
				setOKButtonEnablement();
			}
		});
		btnSimulate.setSelection(isSimulate());
		btnSimulate.setText("Simulate Models with Open Modelica Compiler");
		
		
		final Button btnProvideResultFiles = new Button(grpOptions, SWT.RADIO);
		FormData fd_btnProvideResultFiles = new FormData();
		fd_btnProvideResultFiles.right = new FormAttachment(0, 514);
		fd_btnProvideResultFiles.top = new FormAttachment(0, 53);
		fd_btnProvideResultFiles.left = new FormAttachment(0, 7);
		btnProvideResultFiles.setLayoutData(fd_btnProvideResultFiles);
		btnProvideResultFiles.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (btnProvideResultFiles.getSelection()) {
					grpFilesSelection.setEnabled(true);
					grpFilesSelection.setVisible(true);
					if (getShell().getSize().y < 500) {
						getShell().setSize(getShell().getSize().x, 600);
					}
					
					setSimulate(false);
				}
				else {
					grpFilesSelection.setEnabled(false);
					grpFilesSelection.setVisible(false);
					if (getShell().getSize().y > 500) {
						getShell().setSize(getShell().getSize().x, 300);
					}
					
					setSimulate(true);
				}
				
				setOKButtonEnablement();
			}
		});
		btnProvideResultFiles.setText("Skip simulation and only analyze results");
		
		
		final Button btnRecordOnlyRequirementStatus = new Button(grpOptions, SWT.CHECK);
		FormData fd_btnRecordOnlyRequirementStatus = new FormData();
		fd_btnRecordOnlyRequirementStatus.right = new FormAttachment(0, 515);
		fd_btnRecordOnlyRequirementStatus.top = new FormAttachment(0, 31);
		fd_btnRecordOnlyRequirementStatus.left = new FormAttachment(0, 17);
		btnRecordOnlyRequirementStatus.setLayoutData(fd_btnRecordOnlyRequirementStatus);
		btnRecordOnlyRequirementStatus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnRecordOnlyRequirementStatus.getSelection()) {
					setRecordOnlyRequirements(true);
				}
				else {
					setRecordOnlyRequirements(false);
				}
			}
		});
		btnRecordOnlyRequirementStatus.setSelection(isRecordOnlyRequirements());
		btnRecordOnlyRequirementStatus.setText("Record only clients and requirements status values");
		
		grpFilesSelection = new Group(container, SWT.NONE);
		grpFilesSelection.setLayout(new GridLayout(2, false));
		GridData gd_grpFilesSelection = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grpFilesSelection.widthHint = 502;
		gd_grpFilesSelection.heightHint = 224;
		grpFilesSelection.setLayoutData(gd_grpFilesSelection);
		grpFilesSelection.setText("Files Selection");
		
		Label lblSelectResultFilesFolder = new Label(grpFilesSelection, SWT.NONE);
		GridData gd_lblSelectResultFilesFolder = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_lblSelectResultFilesFolder.widthHint = 533;
		lblSelectResultFilesFolder.setLayoutData(gd_lblSelectResultFilesFolder);
		lblSelectResultFilesFolder.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/elcl16/up_nav.gif"));
		lblSelectResultFilesFolder.setText("Select the folder that contains the simulation result files listed below:");
		
		Button btnSelectFolder = new Button(grpFilesSelection, SWT.NONE);
		btnSelectFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.OPEN);
				// set filter
				String defaultPath = getProjectPath();
				// by default use project path
				if (defaultPath != null) {
					dialog.setFilterPath(defaultPath);
				}
				// if use selected the folder once -> use this as default
				if (getResultFilesFolderPath() != null) {
					dialog.setFilterPath(getResultFilesFolderPath());
				}

				String result = dialog.open();
				
				setResultFilesFolderPath(result);
				if (result != null) {
					
					String path = URIUtil.toURI(result,true).getRawPath();
					if (path.startsWith("/")) {
						path = path.trim().substring(1, path.length());
					}
					path = path.replaceAll("%20", " ");
					
					folderPath.setText(path);
					folderPath.setForeground(new Color(null, 0, 0, 0));
					
					updateSimulationResultFileTreeItems();
					
					setOKButtonEnablement();
				}
			}
		});
		btnSelectFolder.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/elcl16/up_nav.gif"));
		btnSelectFolder.setText("Select Folder");
		
		folderPath = new StyledText(grpFilesSelection, SWT.BORDER);
		folderPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		if (getResultFilesFolderPath() == null) {
			folderPath.setText(" ... not selected yet.");
			folderPath.setForeground(new Color(null, 255, 0, 0));
		}
		else {
			folderPath.setText(getResultFilesFolderPath());	
			folderPath.setForeground(new Color(null, 0, 0, 0));
		}
		folderPath.setEditable(false);
		
		Label lblSimulationResultFiles = new Label(grpFilesSelection, SWT.NONE);
		lblSimulationResultFiles.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		lblSimulationResultFiles.setText("Required simulation result files:");
		
		// disable depending on the simulation option 
		grpFilesSelection.setEnabled(!isSimulate());
		
		resultsFilesTree = new Tree(grpFilesSelection, SWT.BORDER);
		resultsFilesTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				TreeItem[] selectedItems = resultsFilesTree.getSelection();
				for (TreeItem treeItem : selectedItems) {
					DialogMessage dialog = new DialogMessage(getShell(), "File Path Information", "File path:", treeItem.getText(), false);
					dialog.open();
				}
			}
		});
		resultsFilesTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		new Label(grpFilesSelection, SWT.NONE);
		
		grpFilesSelection.setVisible(true);
		new Label(grpFilesSelection, SWT.NONE);
		
		// add tree items if possible
		updateSimulationResultFileTreeItems();
		
		// set images for all buttons
		updateButtonImages();
		return container;
	}
	
	
	
	private String getProjectPath(){
		// get UML model data
		UmlModel umlModel = UmlUtils.getUmlModel();
//		String umlModelFileURI = umlModel.getResourceURI().toString();

		if (umlModel != null) {
			// get project data
			String projectName = umlModel.getResource().getURI().segment(1);
			
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();
			IProject iProject = root.getProject(projectName);
			
			// TODO find a better way to get the absolute path
			String projectPath = iProject.getLocationURI().toString().replaceFirst("file:\\/", "");
			
			if (projectPath != null) {
				return projectPath;
			}
		}
		return null;
	}
	
	private void updateSimulationResultFileTreeItems(){
		
		resultsFilesTree.removeAll();
		
		HashSet<String> filesNotFound = new HashSet<String>();
		
		HashSet<String> filesFound = new HashSet<String>();
		
		if (getGeneratedModelsData() != null) {
			HashSet<Element> generatedModels = getGeneratedModelsData().getGeneratedModels();
			for (Element VeM : generatedModels) {
				String fileName = ModelicaMLServices.getSimulationResultsFileName((NamedElement) VeM);
				
				TreeItem item = new TreeItem(resultsFilesTree, SWT.NONE);
				item.setText(fileName);
				item.setForeground(new Color(null, 255, 0, 0));
				
				if (getResultFilesFolderPath() != null) {
					File file = new File(getResultFilesFolderPath() + "/" + fileName);
					// if the files does not exists -> mark it as red
					if (file.exists()) {
						// green 107-142-35
						item.setForeground(new Color(null, 107, 142, 35));
						filesFound.add(fileName);
					}
					else {
						filesNotFound.add(fileName);
					}
				}
			}
		}
		
		setFilesFound(filesFound);
		setOKButtonEnablement();
		
		if (filesNotFound.size() > 0) {
			
			String dialogMessage = "The following files could not be found:\n";
			for (String string : filesNotFound) {
				dialogMessage += "    - " + string + "\n";
			}
			DialogMessage dialog = new DialogMessage(getShell(), "Files Selection Error", "", dialogMessage, true);
			dialog.open();
		}
	}
	
	
	@Override
	protected void configureShell(Shell newShell) {
		
		newShell.setMinimumSize(new Point(580, 300));
		newShell.setSize(new Point(650, 300));
		
		super.configureShell(newShell);
//		super.configureShell(new Shell(getParentShell(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP | SWT.SHELL_TRIM));
		
		newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile","resources/icons/icons16/runanalysis.gif"));
		newShell.setText("Simulation Results Analysis");
	}
	
	
	@Override
	protected void okPressed() {

		if (isValidSelection()) {
			// dispose the shell
			getParentShell().dispose();
		}
	}
	
	private void collect(){
		
		if (getGeneratedModelsPackage() != null) {
			
			// collect verification data to be passed
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(getShell());
			final String title = "Collecting Verification Data";
			dialog.getProgressMonitor().setTaskName(title + "...");
			dialog.open();
			
			// get the UML model
			umlModel = UmlUtils.getUmlModel();
			EObject rootElement = null;
			
			try {
				rootElement = umlModel.lookupRoot();
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
			
			// collect verification data
			if (rootElement instanceof Package) {
				verificationDataCollector = new VerificationDataCollector(rootElement);
			}
			else {
				MessageDialog.openError(getParentShell(), title + " Error", "Could not access the ModelicaML model. Please open it in editor.");
			}
			
//				dialog.run(false, true, new IRunnableWithProgress() {
//					@Override
//					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
//						try {
//							verificationDataCollector = new VerificationDataCollector(umlModel.lookupRoot());
//						} catch (NotFoundException e) {
//							e.printStackTrace();
//							MessageDialog.openError(getParentShell(), title + " Error", "Could not access the ModelicaML model. Please open it in editor.");
//						}
//					}
//				});
			dialog.close();
		}
	}
	
	
	protected ISelectionListener selectionListener = new ISelectionListener() {
        public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
        if (sourcepart != AnalyseSimulationResultsOptionsDialog.this && selection instanceof IStructuredSelection) {

        	if (getCurrentSelections() != null && getCurrentSelections().size() > 0 ) {
        		EObject selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));

      			Button button = getActiveButton();
      			if (button != null && !button.isDisposed()) {
      				if (button.equals(btnGeneratedModelsPackage)) {
      					
    					// Update data and button 
            			if (selectedElement instanceof Package) {

            				/*
            				 * If a new package was selected -> collect all verification models and verification related data (e.g. all requirements, all scenarios, etc. )
            				 */
            				setGeneratedModelsPackage((Element) selectedElement);
            				collect();
            				
            				if (verificationDataCollector != null) {
                				GeneratedModelsData modelsData = new GeneratedModelsData(getGeneratedModelsPackage(), verificationDataCollector);
            					
            					// check if this package contains VeMs
            					if (modelsData.getGeneratedModels() != null && modelsData.getGeneratedModels().size() > 0 ) {
            						
                    				button.setSelection(false);
                    				updateButton(button);
            						
        							setGeneratedModelsData(modelsData);
                    				updateSimulationResultFileTreeItems();
    							}
            					else {
            						setSelectionHintMode(true, "No verification models were found in '"+ModelicaMLServices.getName((Element) selectedElement)+"'");
            					}
							}
            				else {
            					// do nothing
            				}
    					}
            			else {
            				setSelectionHintMode(true, notValidString + "Only packages are allowed.");
            			}
    				}
				}
			}
        	
        	// validate the selection and enable/disable the ok button.
        	setOKButtonEnablement();
        }
    }
	};
	private Group grpFilesSelection;
	private StyledText folderPath;
	private Tree resultsFilesTree;
	
	
	
	/* ************************************************************************************************ */
	
	// Utilities

	@SuppressWarnings("unchecked")
	private List<Object> getCurrentSelections() {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			return structuredSelection.toList();
		}
		return null;
	}
	
	
	protected EObject adaptSelectedElement( Object selection ) {
		EObject eObject = null;
		if(selection != null) {
			
			if (selection instanceof org.openmodelica.modelicaml.common.instantiation.TreeParent) { // this is an object from components tree view plugin
				return ((org.openmodelica.modelicaml.common.instantiation.TreeParent)selection).getProperty();
			}
			if(selection instanceof IAdaptable) {
				selection = ((IAdaptable)selection).getAdapter(EObject.class);
			}
			Object businessObject = BusinessModelResolver.getInstance().getBusinessModel(selection);
			if(businessObject instanceof EObject) {
				eObject = (EObject)businessObject;
			}
		}
		return eObject;
	}
	
	private void setSelectionHintMode(boolean isError, String message){
		if (!isError) { //normal
			lblSelectionHint.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			lblSelectionHint.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "/icons/editor/ModelExplorer.png"));
			lblSelectionHint.setText(message);
		}
		if (isError) { // error
			lblSelectionHint.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			lblSelectionHint.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "/icons/icons16/error_log.gif"));
//			lblSelectionHint.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEC_FIELD_ERROR));
			lblSelectionHint.setText(message);
		}
	}
	
	private void setOKButtonEnablement(){
		if (isValidSelection() && getButton(IDialogConstants.OK_ID) != null && !getButton(IDialogConstants.OK_ID).isDisposed()) {
			getButton(IDialogConstants.OK_ID).setEnabled(true);
		}
		else if (getButton(IDialogConstants.OK_ID) != null  && !getButton(IDialogConstants.OK_ID).isDisposed()) {
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		}
	}


	private boolean isValidSelection(){
		if (generatedModelsPackage != null 
				&& (isSimulate() || (!isSimulate() && getResultFilesFolderPath() != null && filesFound != null && filesFound.size() > 0))
				
				) {
			return true;
		}
		return false;
	}
	
	private String getName(Element el){
		String name = defaultNegativeString;
		if (el == null) {
			return name;
		}
		if (el instanceof NamedElement) {
			String elName = ((NamedElement) el).getName();
			if (elName != null) {
				name = elName;
			}
		}
		return name;
	}
	
	private String getQName(Element el){
		String name = defaultNegativeString;
		if (el == null) {
			return name;
		}
		if (el instanceof NamedElement) {
			String elName = ((NamedElement) el).getQualifiedName();
			if (elName != null) {
				name = elName;
			}
		}
		return name;
	}
	
	private Button getActiveButton(){
		Button activeButton = null;
		for (Button button : buttons) {
			if (button!= null && !button.isDisposed() && button.getSelection()) {
				return button;
			}
		}
		
		return activeButton;
	}
	
	private void updateButton(Button button){
		
		if (button.getSelection()) {

			// Deactivate other buttons
			deactivateOtherButtons(button);

			// show hint
			lblSelectionHint.setBounds(getActiveButton().getBounds().x + 40, getActiveButton().getBounds().y - 3, lblSelectionHint.getBounds().width, lblSelectionHint.getBounds().height);
			lblSelectionHint.setVisible(true);
		}
		else {
			lblSelectionHint.setVisible(false);
			setSelectionHintMode(false, getButtonSelectionHint(button));
		}
		
		// get a specific selection hint message for button
		setSelectionHintMode(false, getButtonSelectionHint(button));
		
		updateButtonLabelText(button);
	}
	

	private String getButtonSelectionHint(Button button){
		
		String msg = selectionHintMessage;
		
		if (button.getSelection()) {
			
			if (button.equals(btnGeneratedModelsPackage)) {
				return targetPackgeSelectionMessage;
			}
			
		}
		
		return msg;
	}

	private void highlightLabels(String string, Button button){
		// if the name is empty 
		if (string.equals(defaultNegativeString)) {
			// red font color
			lblSelectionHint.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			maptButtonsToLabels.get(button).setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}
		else {
			lblSelectionHint.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			maptButtonsToLabels.get(button).setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		}
	}
	
	private void updateButtonLabelText(Button button){
		
		String elementName = defaultNegativeString;
		String elementQName = defaultNegativeString;
		String elementNameMsg;
		
		if (button.equals(btnGeneratedModelsPackage)) {
			elementName = getName(generatedModelsPackage);
			elementNameMsg = generatedModelsPackagePackgeNamePrefix + getName(generatedModelsPackage);
			maptButtonsToLabels.get(btnGeneratedModelsPackage).setText(elementNameMsg);
			elementQName = getQName(generatedModelsPackage);
			maptButtonsToLabels.get(btnGeneratedModelsPackage).setToolTipText(elementQName);
			
			// check if the name is empty. if yes, highlight the label
			highlightLabels(elementName, button);
		}
	}

	private void updateButtonImages(){
		for (Button button : buttons) {
			if (button.equals(btnGeneratedModelsPackage)) {
				button.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/UMLPackage.gif"));
			}
		}
	}
	
	
	private void deactivateOtherButtons(Button activeButton){
		for (Button button : buttons) {
			if (!button.equals(activeButton)) {
				button.setSelection(false);
			}
		}
	}

	
	/* ************************************************************************************************ */
	// getter and setter
	
	public Element getGeneratedModelsPackage() {
		return generatedModelsPackage;
	}

	public void setGeneratedModelsPackage(Element generatedModelsPackage) {
		this.generatedModelsPackage = generatedModelsPackage;
	}


	public GeneratorVeMScenariosBased getSmg() {
		return smg;
	}

	public boolean isSimulate() {
		return simulate;
	}

	public void setSimulate(boolean simulate) {
		this.simulate = simulate;
	}

	public boolean isRecordOnlyRequirements() {
		return recordOnlyRequirements;
	}

	public void setRecordOnlyRequirements(boolean recordOnlyRequirements) {
		this.recordOnlyRequirements = recordOnlyRequirements;
	}

	public String getResultFilesFolderPath() {
		return resultFilesFolderPath;
	}

	public void setResultFilesFolderPath(String resultFilesFolderPath) {
		this.resultFilesFolderPath = resultFilesFolderPath;
	}


	public GeneratedModelsData getGeneratedModelsData() {
		return generatedModelsData;
	}

	public void setGeneratedModelsData(GeneratedModelsData generatedModelsData) {
		this.generatedModelsData = generatedModelsData;
	}

	public HashSet<String> getFilesFound() {
		return filesFound;
	}

	public void setFilesFound(HashSet<String> filesFound) {
		this.filesFound = filesFound;
	}

	public UmlModel getUmlModel() {
		return umlModel;
	}

	public void setUmlModel(UmlModel umlModel) {
		this.umlModel = umlModel;
	}

	public VerificationDataCollector getVerificationDataCollector() {
		return verificationDataCollector;
	}

	public void setVerificationDataCollector(VerificationDataCollector verificationDataCollector) {
		this.verificationDataCollector = verificationDataCollector;
	}
}
