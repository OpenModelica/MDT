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
package org.openmodelica.simulation.environment.wizard.sessionconfiguration.noninteractive;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.openmodelica.simulation.core.models.modelica.ModelicaModel;
import org.openmodelica.simulation.core.models.modelica.ModelicaParameter;
import org.openmodelica.simulation.core.models.modelica.ModelicaProperty;
import org.openmodelica.simulation.core.models.modelica.ModelicaVariable;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * WizardPage to modify the session configuration setup
 * This WizardPage can be used to create a new page from initial or using an sample.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */
public class SessionConfiguration_ChangeModelNonInteractiveWizardPage extends WizardPage {

	/** The composite1. */
	private Composite composite1;
	
	/** The composite model tree. */
	private Composite compositeModelTree;
	
	/** The composite search. */
	private Composite compositeSearch;
	
	/** The composite selected value data. */
	private Composite compositeSelectedValueData;
	
	/** The composite check actions. */
	private Composite compositeCheckActions;
	
	/** The composite property data container. */
	private Composite compositePropertyDataContainer;
	
	/** The composite property data. */
	private Composite compositePropertyData;
	
	/** The composite buttons. */
	private Composite compositeButtons;
	
	/** The sash form model data. */
	private SashForm sashFormModelData;
	
	/** The button restore all. */
	private Button buttonRestoreAll;
	
	/** The button restore. */
	private Button buttonRestore;
	
	/** The button search. */
	private Button buttonSearch;
	
	/** The text search. */
	private Text textSearch;
	
	/** The label full qualified name. */
	private Label labelFullQualifiedName;
	
	/** The label search. */
	private Label labelSearch;
	
	/** The tree model. */
	private Tree treeModel;

	/** Last selected tree item to restore last selection if an error occurs. */
	private TreeItem lastItem;

	/**
	 * This model will be used as default  Model. This can be the initialModel or a configured Model
	 */
	private ModelicaModel sampleModelicaModel; // @jve:decl-index=0:
	
	/** The new modelica model. */
	private ModelicaModel newModelicaModel;
	
	/** This model will be used to display an initial value of a property only to support the user!. */
//	private ModelicaModel initialModelicaModel; //TODO [201009] implementieren aber niedrige prio
	
	/**
	 * All Properties will be stored temporally in this map. The properties can be changed and restored using the original sample properties
	 */
	public Map<String, PropertySetting> changedPropertiesMap;  
	
	/** Last position in the foundTreeItem list. */
	private int foundPosition;
	/**
	 * Lists all search results to be selected with the search button (next...)
	 */
	private List<TreeItem> foundTreeItem;
	
	/**
	 * Auto generated Constructor can only be used while developing.
	 *
	 * @param pageName the page name
	 */
	protected SessionConfiguration_ChangeModelNonInteractiveWizardPage(String pageName) {
		super(pageName);
	}
	
	/**
	 * Constructor to be used from a Wizard.
	 *
	 * @param pageName the page name
	 * @param sampleModelicaModel This model will be used as default  Model. This can be the initialModel or a configured Model
	 */
	protected SessionConfiguration_ChangeModelNonInteractiveWizardPage(String pageName, ModelicaModel sampleModelicaModel) {
		super(pageName);
		setTitle("Prepare a non-interactive simulation session: Model Configuration");
        setMessage("Change the value of initial properties in the tree below\nA changed property will marked as extra bold type");
		initAll(sampleModelicaModel);
	}

	/**
	 * Initializes all used properties.
	 *
	 * @param sampleModelicaModel a ModelicaModel to be used as sample
	 */
	private void initAll(ModelicaModel sampleModelicaModel){
		this.sampleModelicaModel = sampleModelicaModel;
		changedPropertiesMap = new HashMap<String, PropertySetting>();
		foundPosition = 0;
		foundTreeItem = new ArrayList<TreeItem>();
		lastItem = null;
		for (String fullQualifiedName : this.sampleModelicaModel.getMainclass().getAllFullQualifiedVariableNames()) {
			ModelicaProperty existingProperty = this.sampleModelicaModel.getMainclass().getVariableByName(fullQualifiedName);
				changedPropertiesMap.put(fullQualifiedName, new PropertySetting(existingProperty));
//			System.out.println(changedPropertiesMap.get(fullQualifiedName));
		}

		for (String fullQualifiedName : this.sampleModelicaModel.getMainclass().getAllFullQualifiedParameterNames()) {
			ModelicaProperty existingProperty = this.sampleModelicaModel.getMainclass().getParameterByName(fullQualifiedName);
				changedPropertiesMap.put(fullQualifiedName, new PropertySetting(existingProperty));
//			System.out.println(changedPropertiesMap.get(fullQualifiedName));
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		{
			composite1 = new Composite(parent, SWT.NONE);
			GridLayout composite1Layout = new GridLayout();
			composite1Layout.makeColumnsEqualWidth = true;
			composite1.setLayout(composite1Layout);
			{
				sashFormModelData = new SashForm(composite1, SWT.BORDER);
				GridData sashFormModelDataLData = new GridData();
				sashFormModelDataLData.horizontalAlignment = GridData.FILL;
				sashFormModelDataLData.grabExcessHorizontalSpace = true;
				sashFormModelDataLData.verticalAlignment = GridData.FILL;
				sashFormModelDataLData.grabExcessVerticalSpace = true;
				sashFormModelData.setLayoutData(sashFormModelDataLData);
				sashFormModelData.setSize(60, 30);
				sashFormModelData.setOrientation(SWT.VERTICAL);
				{
					compositeModelTree = new Composite(sashFormModelData, SWT.NONE);
					GridLayout compositeModelTreeLayout = new GridLayout();
					compositeModelTreeLayout.makeColumnsEqualWidth = true;
					compositeModelTree.setLayout(compositeModelTreeLayout);
					{
						GridData treeModelLData = new GridData();
						treeModelLData.horizontalAlignment = GridData.FILL;
						treeModelLData.grabExcessHorizontalSpace = true;
						treeModelLData.verticalAlignment = GridData.FILL;
						treeModelLData.grabExcessVerticalSpace = true;
						treeModel = new Tree(compositeModelTree, SWT.NONE);
						treeModel.setLayoutData(treeModelLData);
						treeModel.addSelectionListener(new SelectionAdapter() {
							public void widgetSelected(SelectionEvent arg0) {
								
								doAdoptionOfProperty((TreeItem) arg0.item);
//								compositeSelectedValueData.setEnabled(true); //This is only important at start time...
//								List<String> exceptionList = new ArrayList<String>(); //Signal if all values are correct and could adopt in the corresponding PropertySetting object
//								adoptChangedValues(exceptionList);
//								if(exceptionList.isEmpty()){
//									TreeItem item = (TreeItem) arg0.item;
////									setViewCompositePropertySetting(item);
//									lastItem = item;
//									setViewCompositePropertySetting(item);
//								} else{
//									String exceptionMessages = "Changed Property values can't adopt\nPlease check following Values:\n\n";
//									
//									for( String exception : exceptionList){
//										exceptionMessages += "\n" + exception;
//									}
//									
//									MessageDialog.openError(getShell(), "Corrupt Values", exceptionMessages);
//
//									treeModel.setSelection(lastItem);
//								}
							}
						});
						buildTreeModel(sampleModelicaModel, treeModel);
					}
				}
				{
					compositeSelectedValueData = new Composite(sashFormModelData, SWT.NONE);
					GridLayout compositeSelectedValueDataLayout = new GridLayout();
					compositeSelectedValueDataLayout.makeColumnsEqualWidth = true;
					compositeSelectedValueData.setLayout(compositeSelectedValueDataLayout);
					{
						compositeCheckActions = new Composite(compositeSelectedValueData, SWT.NONE);
						GridLayout compositeCheckActionsLayout = new GridLayout();
						compositeCheckActionsLayout.makeColumnsEqualWidth = true;
						GridData compositeCheckActionsLData = new GridData();
						compositeCheckActionsLData.horizontalAlignment = GridData.FILL;
						compositeCheckActionsLData.grabExcessHorizontalSpace = true;
						compositeCheckActionsLData.verticalAlignment = GridData.FILL;
						compositeCheckActions.setLayoutData(compositeCheckActionsLData);
						compositeCheckActions.setLayout(compositeCheckActionsLayout);
						{ 
							labelFullQualifiedName = new Label(compositeCheckActions, SWT.NONE);
							GridData labelFullQualifiedNameLData = new GridData();
							labelFullQualifiedNameLData.horizontalAlignment = GridData.FILL;
							labelFullQualifiedNameLData.grabExcessHorizontalSpace = true;
							labelFullQualifiedName.setLayoutData(labelFullQualifiedNameLData);
							labelFullQualifiedName.setText("Full Qualified Name");
						}
//						{
//							buttonCheckPlot = new Button(compositeCheckActions, SWT.CHECK | SWT.LEFT);
//							GridData buttonCheckPlotLData = new GridData();
//							buttonCheckPlot.setLayoutData(buttonCheckPlotLData);
//							buttonCheckPlot.setText("Plot on View");
//							buttonCheckPlot.setToolTipText("Option if this property should be displayed on a plot/view");
//						}
//						{
//							buttonCheckInteractive = new Button(compositeCheckActions, SWT.CHECK | SWT.LEFT);
//							GridData buttonCheckInteractiveLData = new GridData();
//							buttonCheckInteractive.setLayoutData(buttonCheckInteractiveLData);
//							buttonCheckInteractive.setText("Use Interactively");
//							buttonCheckInteractive.setToolTipText("Option if this property is able to be changed interactively");
//						}
					}
					{
						compositePropertyDataContainer = new Composite(compositeSelectedValueData, SWT.NONE);
//						compositePropertyDataContainer = new SessionConfiguration_ChangeModel_DoubleValueComposite(compositeSelectedValueData, SWT.NONE);
						GridLayout compositePropertyDataContainerLayout = new GridLayout();
						compositePropertyDataContainerLayout.makeColumnsEqualWidth = true;
						GridData compositePropertyDataContainerLData = new GridData();
						compositePropertyDataContainerLData.horizontalAlignment = GridData.FILL;
						compositePropertyDataContainerLData.grabExcessHorizontalSpace = true;
						compositePropertyDataContainer.setLayoutData(compositePropertyDataContainerLData);
						compositePropertyDataContainer.setLayout(compositePropertyDataContainerLayout);
						{
							compositePropertyData = new SessionConfiguration_ChangeModel_DoubleValueComposite(compositePropertyDataContainer, SWT.NONE);// = new SessionConfiguration_ChangeModel_DoubleValueComposite(compositePropertyDataContainer, SWT.NONE);
							GridLayout compositePropertyDataLayout = new GridLayout();
							compositePropertyDataLayout.makeColumnsEqualWidth = true;
							GridData compositePropertyDataLData = new GridData();
							compositePropertyDataLData.horizontalAlignment = GridData.FILL;
							compositePropertyDataLData.grabExcessHorizontalSpace = true;
							compositePropertyData.setLayoutData(compositePropertyDataLData);
							compositePropertyData.setLayout(compositePropertyDataLayout);
						}
					}
					{
						buttonRestore = new Button(compositeSelectedValueData,
								SWT.PUSH | SWT.CENTER);
						GridData buttonRestoreLData = new GridData();
						buttonRestore.setLayoutData(buttonRestoreLData);
						buttonRestore.setText("Restore Default");
						LocalResourceManager resources 
					    = new LocalResourceManager(JFaceResources.getResources(), buttonRestore);
						ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/clear.gif");
						Image i = imageDescriptor.createImage();
						resources.dispose();
						buttonRestore.setImage(i);
						buttonRestore
								.setToolTipText("Restores the default value for this property");
						buttonRestore
								.addSelectionListener(new SelectionAdapter() {
									public void widgetSelected(SelectionEvent e) {
										TreeItem selectedItem = treeModel
												.getSelection()[0];
										restorePropertySetting((String) selectedItem
												.getData());
										setViewCompositePropertySetting(selectedItem);
										
										highlightIfChanged(selectedItem);
									}
								});
					}
				}
			}
			{
				compositeSearch = new Composite(composite1, SWT.BORDER);
				GridLayout compositeSearchLayout = new GridLayout();
				compositeSearchLayout.makeColumnsEqualWidth = true;
				compositeSearchLayout.numColumns = 4;
				GridData compositeSearchLData = new GridData();
				compositeSearchLData.horizontalAlignment = GridData.FILL;
				compositeSearchLData.grabExcessHorizontalSpace = true;
				compositeSearch.setLayoutData(compositeSearchLData);
				compositeSearch.setLayout(compositeSearchLayout);
				{
					labelSearch = new Label(compositeSearch, SWT.NONE);
					GridData labelSearchLData = new GridData();
					labelSearch.setLayoutData(labelSearchLData);
					labelSearch.setText("Search for:");
				}
				{
					GridData textSearchLData = new GridData();
					textSearchLData.horizontalSpan = 2;
					textSearchLData.horizontalAlignment = GridData.FILL;
					textSearchLData.grabExcessHorizontalSpace = true;
					textSearch = new Text(compositeSearch, SWT.BORDER);
					textSearch.setLayoutData(textSearchLData);
					textSearch.addModifyListener(new ModifyListener() {

						public void modifyText(ModifyEvent evt) {
							foundTreeItem.clear();
							foundPosition = 0;
							buttonSearch.setText("Search");
						}
					});
				}
				{
					buttonSearch = new Button(compositeSearch, SWT.PUSH | SWT.CENTER);
					GridData buttonSearchLData = new GridData();
					buttonSearchLData.horizontalAlignment = GridData.FILL;
					buttonSearch.setLayoutData(buttonSearchLData);
					buttonSearch.setText("Search");
					buttonSearch.addSelectionListener(new SelectionAdapter() {
						
						@Override
						public void widgetSelected(SelectionEvent e) {
							super.widgetSelected(e);
							if(foundTreeItem.isEmpty() && !textSearch.getText().isEmpty()){ //Is a new search
								search(textSearch.getText());
								buttonSearch.setText("Next");
								if(doAdoptionOfProperty(foundTreeItem.get(foundPosition))){
									showNextSearchResult();
								}
							} else{ //Go to next found element
								if(doAdoptionOfProperty(foundTreeItem.get(foundPosition))){
									showNextSearchResult();
								}
							}
						}
					});
				}
			}
			{
				compositeButtons = new Composite(composite1, SWT.NONE);
				GridLayout compositeButtonsLayout = new GridLayout();
				compositeButtonsLayout.makeColumnsEqualWidth = true;
				GridData compositeButtonsLData = new GridData();
				compositeButtonsLData.horizontalAlignment = GridData.FILL;
				compositeButtonsLData.grabExcessHorizontalSpace = true;
				compositeButtons.setLayoutData(compositeButtonsLData);
				compositeButtons.setLayout(compositeButtonsLayout);
				{
					buttonRestoreAll = new Button(compositeButtons, SWT.PUSH | SWT.CENTER);
					GridData buttonRestoreAllLData = new GridData();
					buttonRestoreAll.setLayoutData(buttonRestoreAllLData);
					buttonRestoreAll.setText("Restore All");
					
					LocalResourceManager resources 
				    = new LocalResourceManager(JFaceResources.getResources(), buttonRestoreAll);
					ImageDescriptor imageDescriptor = Activator.getImageDescriptor("/src/org/openmodelica/simulation/environment/icons/clear.gif");
					Image i = imageDescriptor.createImage();
					resources.dispose();
					buttonRestoreAll.setImage(i);
					buttonRestoreAll.addSelectionListener(new SelectionAdapter() {
						
						@Override
						public void widgetSelected(SelectionEvent e) {
							super.widgetSelected(e);
							MessageDialog messageDialog = new MessageDialog(
									getShell(),
									"Restore Default",
									null,
									"Are you sure you want to reset all propery settings to default?",
									MessageDialog.QUESTION, new String[] {
											"Restore All", "Cancel" }, 1);
							if (messageDialog.open() == 0) {
								initAll(sampleModelicaModel);
								treeModel.removeAll();
								buildTreeModel(sampleModelicaModel, treeModel);
								setViewCompositePropertySetting(null);
								foundTreeItem.clear();
								foundPosition = 0;
								buttonSearch.setText("Search");
							}
						}
					});
				}
			}
		}
		setControl(composite1);
		compositeSelectedValueData.setEnabled(false); //Can't set invisible because size will be corrupt...
	}
	
	/**
	 * Show the next search result, if the last found result has already been displayed start from beginning.
	 */
	private void showNextSearchResult(){
		treeModel.select(foundTreeItem.get(foundPosition));
		treeModel.showSelection();
		if(foundTreeItem.size()-1<=foundPosition){
			foundPosition = 0; //Search from begining
		}else{
			foundPosition++;
		}
	}
	
	/**
	 * Searches recursively for an element using the rekSearch method.
	 *
	 * @param name the name
	 */
	private void search(String name) {
		rekSearch(treeModel.getItems()[0], name); //treeModel.getItems()[0] is the root element!
	}
	
	/**
	 * Rek search.
	 *
	 * @param item the item
	 * @param name the name
	 */
	private void rekSearch(TreeItem item, String name){
		if(item.getItemCount() != 0){ //is node
			for(TreeItem child : item.getItems()){
				rekSearch(child, name);
			}
		} else{ //is leaf
			if(((String)item.getData()).toLowerCase().contains(name.toLowerCase())){
				foundTreeItem.add(item);
			}
		}
	}

	/**
	 * Try to adopt a property if its values has been modified and causes a view change on the new selected property.
	 *
	 * @param selectedItem New selected TreeItem from the item tree
	 * @return true if the last item has been adopted successfully otherwise false
	 */
	private boolean doAdoptionOfProperty(TreeItem selectedItem){

		compositeSelectedValueData.setEnabled(true); //This is only important at start time...
		List<String> exceptionList = new ArrayList<String>(); //Signal if all values are correct and could adopt in the corresponding PropertySetting object
		adoptChangedValues(exceptionList);
		if(exceptionList.isEmpty()){
//			setViewCompositePropertySetting(item);
			highlightIfChanged(lastItem);
			lastItem = selectedItem;
			setViewCompositePropertySetting(selectedItem);
			return true;
		} else{
			String exceptionMessages = "Changed Property values can't adopt\nPlease check following Values:\n\n";
			
			for( String exception : exceptionList){
				exceptionMessages += "\n" + exception;
			}
			
			MessageDialog.openError(getShell(), "Corrupt Values", exceptionMessages);

			treeModel.setSelection(lastItem);
			return false;
		}
	}
	
	/**
	 * Highlight a changed/modified property.
	 *
	 * @param item the item
	 */
	private void highlightIfChanged(TreeItem item){
			
		if(item!=null && item.getData() != null){
			String fullQualifiedName = (String) item.getData();
			PropertySetting lastSelectedPropertySetting = changedPropertiesMap
			.get(fullQualifiedName);
			
			int defaultHeight = item.getFont().getFontData()[0].getHeight();
			String defaultName = item.getFont().getFontData()[0].getName();
			
			if(lastSelectedPropertySetting.isChanged()){
				System.err.println(item.getText() + "CHANGED");
				Font font = new Font(Display.getCurrent(), defaultName, defaultHeight, SWT.BOLD); 
				item.setFont(font);
			} else{
				System.err.println(item.getText() + "NOT-CHANGED");
				Font font = new Font(Display.getCurrent(), defaultName, defaultHeight, SWT.NONE); 
				item.setFont(font);
			}
		}
	}
	
	/**
	 * If the user selects a tree item, the modified values of the last selected tree item must save into the corresponding PropertySetting object.
	 * If one of the values are corrupt, the value won't adopt into the corresponding PropertySetting object and the user will be informed about this problem
	 *
	 * @param exceptionList the exception list
	 */
	private void adoptChangedValues(List<String> exceptionList) {
		if (lastItem != null && lastItem.getData() != null) {
			String fullQualifiedName = (String) lastItem.getData();
			PropertySetting lastSelectedPropertySetting = changedPropertiesMap
					.get(fullQualifiedName);

			if (lastSelectedPropertySetting.getTempProperty()
					.getPrimitiveValue() instanceof Double) {
				try {
					ModelicaProperty modelicaProperty_Of_lastSelectedPropertySetting = lastSelectedPropertySetting
							.getTempProperty();
					// getChangedValue and try to set it into the last selected
					// PropertySetting
					try {
						Double changedValue = ((SessionConfiguration_ChangeModel_DoubleValueComposite) compositePropertyData)
								.getValue();
						Double oldValue = ((Double) modelicaProperty_Of_lastSelectedPropertySetting
								.getPrimitiveValue());

						if (oldValue == null) {
							if (changedValue != null) {
								modelicaProperty_Of_lastSelectedPropertySetting
										.setPrimitiveValue(changedValue);
								lastSelectedPropertySetting.setChanged(true);
								//Change name in Tree
								String lastItemString = lastItem.getText();
								Scanner sc = new Scanner(lastItemString);
								sc.useDelimiter("\\[");							
								String propertyname = sc.next().trim();
								if (lastSelectedPropertySetting.getTempProperty() instanceof ModelicaVariable) {
									
									lastItem.setText(propertyname
											+ "  [Value: "
											+ changedValue
											+ " | Typ: Variable]");
								} else if (lastSelectedPropertySetting.getTempProperty() instanceof ModelicaParameter) {
									lastItem.setText(propertyname
											+ "  [Value: "
											+ changedValue
											+ " | Typ: Parameter]");
								}
								
							}
						} else if (!oldValue.equals(changedValue)) {
							modelicaProperty_Of_lastSelectedPropertySetting
									.setPrimitiveValue(changedValue);
							lastSelectedPropertySetting.setChanged(true);
							
							
							//Change name in Tree
							String lastItemString = lastItem.getText();
							Scanner sc = new Scanner(lastItemString);
							sc.useDelimiter("\\[");							
							String propertyname = sc.next().trim();
							if (lastSelectedPropertySetting.getTempProperty() instanceof ModelicaVariable) {
								lastItem.setText(propertyname
										+ "  [Value: "
										+ changedValue
										+ " | Typ: Variable]");
							} else if (lastSelectedPropertySetting.getTempProperty() instanceof ModelicaParameter) {
								lastItem.setText(propertyname
										+ "  [Value: "
										+ changedValue
										+ " | Typ: Parameter]");
							}
						}

					} catch (NumberFormatException e) {
						// TODO Zurücksetzen???, Messagesbox!!!, last item am
						// ende selectieren wenn andere werte übernommen
						// wurden!!
						exceptionList.add("Property Value is corrupt:\n"
								+ e.getMessage());
						// e.printStackTrace();
					}
					// getChangedMinValue and try to set it into the last
					// selected PropertySetting
					try {
						Double changedMinValue = ((SessionConfiguration_ChangeModel_DoubleValueComposite) compositePropertyData)
								.getMinValue();
						Double oldMinValue = ((Double) modelicaProperty_Of_lastSelectedPropertySetting
								.getMinValue());

						if (oldMinValue == null) {
							if (changedMinValue != null) {
								modelicaProperty_Of_lastSelectedPropertySetting
										.setMinValue(changedMinValue);
								// lastSelectedPropertySetting
								// .setChanged(true);
							}
						} else if (!oldMinValue.equals(changedMinValue)) {
							modelicaProperty_Of_lastSelectedPropertySetting
									.setMinValue(changedMinValue);
							// lastSelectedPropertySetting
							// .setChanged(true);
						}

					} catch (NumberFormatException e) {
						// TODO Zurücksetzen???, Messagesbox!!!, last item am
						// ende selectieren wenn andere werte übernommen
						// wurden!!
						exceptionList.add("Property MinValue is corrupt:\n"
								+ e.getMessage());
						// e.printStackTrace();
					}
					// getChangedMaxValue and try to set it into the last
					// selected PropertySetting
					try {
						Double changedMaxValue = ((SessionConfiguration_ChangeModel_DoubleValueComposite) compositePropertyData)
								.getMaxValue();
						Double oldMaxValue = ((Double) modelicaProperty_Of_lastSelectedPropertySetting
								.getMaxValue());

						if (oldMaxValue == null) {
							if (changedMaxValue != null) {
								modelicaProperty_Of_lastSelectedPropertySetting
										.setMaxValue(changedMaxValue);
								// lastSelectedPropertySetting
								// .setChanged(true);
							}
						} else if (!oldMaxValue.equals(changedMaxValue)) {
							modelicaProperty_Of_lastSelectedPropertySetting
									.setMaxValue(changedMaxValue);
							// lastSelectedPropertySetting
							// .setChanged(true);
						}

					} catch (NumberFormatException e) {
						// TODO Zurücksetzen???, Messagesbox!!!, last item am
						// ende selectieren wenn andere werte übernommen
						// wurden!!
						exceptionList.add("Property MaxValue is corrupt:\n"
								+ e.getMessage());
						// e.printStackTrace();
					}

				} catch (Exception e) {
					// This exception is for the set... methods in
					// ModelicaProperty
					// e.printStackTrace();
				}

			} else if (lastSelectedPropertySetting.getTempProperty()
					.getPrimitiveValue() instanceof Integer) {
				try {
					ModelicaProperty modelicaProperty_Of_lastSelectedPropertySetting = lastSelectedPropertySetting
							.getTempProperty();
					// getChangedValue and try to set it into the last selected
					// PropertySetting
					try {
						Integer changedValue = ((SessionConfiguration_ChangeModel_IntegerValueComposite) compositePropertyData)
								.getValue();

						Integer oldValue = ((Integer) modelicaProperty_Of_lastSelectedPropertySetting
								.getPrimitiveValue());

						if (oldValue == null) {
							if (changedValue != null) {
								modelicaProperty_Of_lastSelectedPropertySetting
										.setPrimitiveValue(changedValue);
								lastSelectedPropertySetting.setChanged(true);
							}
						} else if (!oldValue.equals(changedValue)) {
							modelicaProperty_Of_lastSelectedPropertySetting
									.setPrimitiveValue(changedValue);
							lastSelectedPropertySetting.setChanged(true);
						}

					} catch (NumberFormatException e) {
						// TODO Zurücksetzen???, Messagesbox!!!, last item am
						// ende selectieren wenn andere werte übernommen
						// wurden!!
						exceptionList.add("Property Value is corrupt:\n"
								+ e.getMessage());
						// e.printStackTrace();
					}
					// getChangedMinValie and try to set it into the last
					// selected PropertySetting
					Integer changedMinValue = ((SessionConfiguration_ChangeModel_IntegerValueComposite) compositePropertyData)
							.getMinValue();
					Integer changedMaxValue = ((SessionConfiguration_ChangeModel_IntegerValueComposite) compositePropertyData)
							.getMaxValue();
					try {
						Integer oldMinValue = ((Integer) modelicaProperty_Of_lastSelectedPropertySetting
								.getMinValue());

						if (oldMinValue == null) {
							if (changedMinValue != null) {
								modelicaProperty_Of_lastSelectedPropertySetting
										.setMinValue(changedMinValue);
								// lastSelectedPropertySetting
								// .setChanged(true);
							}
						} else if (!oldMinValue.equals(changedMinValue)) {
							modelicaProperty_Of_lastSelectedPropertySetting
									.setMinValue(changedMinValue);
							// lastSelectedPropertySetting
							// .setChanged(true);
						}

					} catch (NumberFormatException e) {
						// TODO Zurücksetzen???, Messagesbox!!!, last item am
						// ende selectieren wenn andere werte übernommen
						// wurden!!
						exceptionList.add("Property MinValue is corrupt:\n"
								+ e.getMessage());
					}
					// getChangedMaxValie and try to set it into the last
					// selected PropertySetting
					try {
						Integer oldMaxValue = ((Integer) modelicaProperty_Of_lastSelectedPropertySetting
								.getMaxValue());

						if (oldMaxValue == null) {
							if (changedMaxValue != null) {
								modelicaProperty_Of_lastSelectedPropertySetting
										.setMaxValue(changedMaxValue);
								// lastSelectedPropertySetting
								// .setChanged(true);
							}
						} else if (!oldMaxValue.equals(changedMaxValue)) {
							modelicaProperty_Of_lastSelectedPropertySetting
									.setMaxValue(changedMaxValue);
							// lastSelectedPropertySetting
							// .setChanged(true);
						}
					} catch (NumberFormatException e) {
						// TODO Zurücksetzen???, Messagesbox!!!, last item am
						// ende selectieren wenn andere werte übernommen
						// wurden!!
						exceptionList.add("Property MaxValue is corrupt:\n"
								+ e.getMessage());
						// e.printStackTrace();
					}

				} catch (Exception e) {
					// This exception is for the set... methods in
					// ModelicaProperty
					e.printStackTrace();
				}
			} else if (lastSelectedPropertySetting.getTempProperty()
					.getPrimitiveValue() instanceof String) {
				ModelicaProperty modelicaProperty_Of_lastSelectedPropertySetting = lastSelectedPropertySetting
						.getTempProperty();
				String changedValue = ((SessionConfiguration_ChangeModel_StringValueComposite) compositePropertyData)
						.getValue();
				if (!((String) modelicaProperty_Of_lastSelectedPropertySetting
						.getPrimitiveValue()).equals(changedValue)) {
					try {
						modelicaProperty_Of_lastSelectedPropertySetting
								.setPrimitiveValue(changedValue);
						lastSelectedPropertySetting.setChanged(true);
					} catch (Exception e) {
						// TODO Zurücksetzen???, Messagesbox!!!, last item am
						// ende selectieren wenn andere werte übernommen
						// wurden!!
						exceptionList.add("Property Value is corrupt:\n"
								+ e.getMessage());
						// e.printStackTrace();
					}
				}
			} else if (lastSelectedPropertySetting.getTempProperty()
					.getPrimitiveValue() instanceof Boolean) {
				ModelicaProperty modelicaProperty_Of_lastSelectedPropertySetting = lastSelectedPropertySetting
						.getTempProperty();
				Boolean changedValue = ((SessionConfiguration_ChangeModel_BooleanValueComposite) compositePropertyData)
						.getValue();
				if (!((Boolean) modelicaProperty_Of_lastSelectedPropertySetting
						.getPrimitiveValue()).equals(changedValue)) {
					try {
						modelicaProperty_Of_lastSelectedPropertySetting
								.setPrimitiveValue(changedValue);
						lastSelectedPropertySetting.setChanged(true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						exceptionList.add("Property Value is corrupt:\n"
								+ e.getMessage());
						// e.printStackTrace();
					}
				}
			}

//			if (lastSelectedPropertySetting.getTempProperty() instanceof ModelicaParameter) {
//				if (((ModelicaParameter) lastSelectedPropertySetting
//						.getTempProperty()).getInteractive() != buttonCheckInteractive
//						.getSelection()) {
//					((ModelicaParameter) lastSelectedPropertySetting
//							.getTempProperty())
//							.setInteractive(buttonCheckInteractive
//									.getSelection());
//					lastSelectedPropertySetting.setChanged(true);
//				}
//			}
//			if (lastSelectedPropertySetting.getTempProperty().getDisplay() != buttonCheckPlot
//					.getSelection()) {
//				lastSelectedPropertySetting.getTempProperty().setDisplay(
//						buttonCheckPlot.getSelection());
//				lastSelectedPropertySetting.setChanged(true);
//			}
		}
	}
	
	/**
	 * Create a copy of the existing ModelicaModel sampleModelicaModel into the newModelicaModel.
	 * Adopts all changed or unchanged ModelicaProperties from the PropertySettings which are 
	 * changedPropertiesMap to the ModelicaMainclass from the ModelicaModel newModelicaModel
	 */
	public void adoptAllPropertySettings(){
		
		if(treeModel.getSelection().length != 0){
			TreeItem item = treeModel.getSelection()[0];
			doAdoptionOfProperty((TreeItem) item);
		}
			
		newModelicaModel = ModelicaModel.createCopyOf(sampleModelicaModel);
		for(String fullQualifiedName: changedPropertiesMap.keySet()){
			ModelicaProperty mp = changedPropertiesMap.get(fullQualifiedName).getTempProperty();
			if(mp instanceof ModelicaVariable)
				newModelicaModel.getMainclass().addVariable(mp);
			else if(mp instanceof ModelicaParameter)
				newModelicaModel.getMainclass().addParameter(mp);
		}
	}
	
	/**
	 * Create a copy of the newModelicaModel and returns it.
	 *
	 * @return a copy of the new configured Modelica model
	 */
	public ModelicaModel getConfiguredModelicaModel(){
		return ModelicaModel.createCopyOf(newModelicaModel);
	}
	
	/**
	 * When an item has been selected a specified composite must be called which is specified for its data type.
	 *
	 * @param item the new view composite property setting
	 */
	private void setViewCompositePropertySetting(TreeItem item) {
		if (item != null && item.getData() != null) { // If true, than it is a leaf
			String fullQualifiedName = (String) item.getData();
			PropertySetting selectedPropertyItem = changedPropertiesMap
					.get(fullQualifiedName);
			if (selectedPropertyItem != null) {
				compositeSelectedValueData.setVisible(true);
				labelFullQualifiedName.setText(fullQualifiedName);
//				buttonCheckPlot.setSelection(selectedPropertyItem
//						.getTempProperty().getDisplay());
//				if (selectedPropertyItem.getTempProperty() instanceof ModelicaParameter) {
//					buttonCheckInteractive
//							.setSelection(((ModelicaParameter) selectedPropertyItem
//									.getTempProperty()).getInteractive());
//					buttonCheckInteractive.setEnabled(true);
//					buttonCheckInteractive.setVisible(true);
//				} else {
//					buttonCheckInteractive.setEnabled(false);
//					buttonCheckInteractive.setVisible(false);
//				}
				if (selectedPropertyItem.getTempProperty().getPrimitiveValue() instanceof Number)
					createCompositeValueNumber((Number) selectedPropertyItem
							.getTempProperty().getPrimitiveValue(),
							selectedPropertyItem.getTempProperty()
									.getMinValue(), selectedPropertyItem
									.getTempProperty().getMaxValue());
				else
					createCompositeValueNonNumber(selectedPropertyItem
							.getTempProperty().getPrimitiveValue());

			}
		} else {
			compositeSelectedValueData.setVisible(false);
		}
	}
	
	/**
	 * Creates the composite value number.
	 *
	 * @param primitiveValue the primitive value
	 * @param minValue the min value
	 * @param maxValue the max value
	 */
	private void createCompositeValueNumber(Number primitiveValue,
			Number minValue, Number maxValue) {
		if (compositePropertyData != null)
			compositePropertyData.dispose();
		if (primitiveValue instanceof Double) {
			GridData compositePropertyDataLData = new GridData();
			compositePropertyDataLData.horizontalAlignment = GridData.FILL;
			compositePropertyDataLData.grabExcessHorizontalSpace = true;
			compositePropertyData = new SessionConfiguration_ChangeModel_DoubleValueComposite(
					compositePropertyDataContainer, SWT.NONE,
					(Double) primitiveValue, (Double) minValue,
					(Double) maxValue);
			compositePropertyData.setLayoutData(compositePropertyDataLData);
		} else if (primitiveValue instanceof Integer) {
			compositePropertyData = new SessionConfiguration_ChangeModel_IntegerValueComposite(
					compositePropertyDataContainer, SWT.NONE,
					(Integer) primitiveValue, (Integer) minValue,
					(Integer) maxValue);
		}
		// compositeValue.setLayoutData(compositeValueLData);
		// compositeValue.setLayout(compositeValueLayout);
	}
	
	/**
	 * Creates the composite value non number.
	 *
	 * @param primitiveValue the primitive value
	 */
	private void createCompositeValueNonNumber(Object primitiveValue) {
		if (compositePropertyData != null)
			compositePropertyData.dispose();
		if (primitiveValue instanceof String) {
			GridData compositePropertyDataLData1 = new GridData();
			compositePropertyDataLData1.horizontalAlignment = GridData.FILL;
			compositePropertyDataLData1.grabExcessHorizontalSpace = true;
			compositePropertyData = new SessionConfiguration_ChangeModel_StringValueComposite(
					compositePropertyDataContainer, SWT.NONE,
					(String) primitiveValue);
			compositePropertyData.setLayoutData(compositePropertyDataLData1);
		} else if (primitiveValue instanceof Boolean) {
			compositePropertyData = new SessionConfiguration_ChangeModel_BooleanValueComposite(
					compositePropertyDataContainer, SWT.NONE,
					(Boolean) primitiveValue);
		}
		// compositeValue.setLayoutData(compositeValueLData);
		// compositeValue.setLayout(compositeValueLayout);
	}
	
	/**
	 * Builds the tree model.
	 *
	 * @param modelicaModel the modelica model
	 * @param treeModel the tree model
	 */
	private void buildTreeModel(ModelicaModel modelicaModel, Tree treeModel) {
		if (modelicaModel != null) {
			TreeItem rootItem = new TreeItem(treeModel, SWT.NULL);
			rootItem.setText(modelicaModel.getName());

			Set<String> fullQualifiedVariableNames = modelicaModel
					.getMainclass().getAllFullQualifiedVariableNames();

			Set<String> fullQualifiedParameterNames = modelicaModel
					.getMainclass().getAllFullQualifiedParameterNames();

			for (String fullQualifiedName : fullQualifiedParameterNames) {
				createModelElement(modelicaModel, rootItem, fullQualifiedName,
						fullQualifiedName, 'p');
			}

			for (String fullQualifiedName : fullQualifiedVariableNames) {
				createModelElement(modelicaModel, rootItem, fullQualifiedName,
						fullQualifiedName, 'v');
			}
		} else {
			treeModel.removeAll();
		}
	}
	
	/**
	 * Recursive method to build the treeModel with all nodes and child elements.
	 *
	 * @param modelicaModel the modelica model
	 * @param itemParent parantItem
	 * @param restOfFullQualifiedName the rest of full qualified name
	 * @param fullQualifiedName the full qualified name
	 * @param propertyType Type of the Property, only for documentation or symbols!!
	 */
	private void createModelElement(ModelicaModel modelicaModel, TreeItem itemParent,
			String restOfFullQualifiedName, String fullQualifiedName,
			char propertyType) {

		Scanner sc = new Scanner(restOfFullQualifiedName);
		sc.useDelimiter("\\.");
		if (sc.hasNext()) {
			String name = sc.next();

			if (itemParent.getItems().length != 0) {
				boolean hasChild = false;
				for (TreeItem childItem : itemParent.getItems()) {
					if (childItem.getText().equals(name)) {
						if (sc.hasNextLine())
							createModelElement(modelicaModel, childItem,
									sc.nextLine(), fullQualifiedName,
									propertyType);
						hasChild = true;
						break;
					}
				}
				if (!hasChild) {
					TreeItem newChildItem = new TreeItem(itemParent, SWT.NULL);
					newChildItem.setText(name);
					if (sc.hasNextLine()) {
						createModelElement(modelicaModel, newChildItem,
								sc.nextLine(), fullQualifiedName, propertyType);
					} else {
						newChildItem.setData(fullQualifiedName);
						if (propertyType == 'v') {
							newChildItem.setText(newChildItem.getText()
									+ "  [Value: "
									+ modelicaModel
											.getMainclass()
											.getVariableByName(
													fullQualifiedName)
											.getPrimitiveValue()
									+ " | Typ: Variable]");
						} else if (propertyType == 'p') {
							newChildItem.setText(newChildItem.getText()
									+ "  [Value: "
									+ modelicaModel
											.getMainclass()
											.getParameterByName(
													fullQualifiedName)
											.getPrimitiveValue()
									+ " | Typ: Parameter]");
						}
					}
				}
			} else {
				TreeItem newChildItem = new TreeItem(itemParent, SWT.NULL);
				newChildItem.setText(name);
				if (sc.hasNextLine()) {
					createModelElement(modelicaModel, newChildItem,
							sc.nextLine(), fullQualifiedName, propertyType);
				} else {
					newChildItem.setData(fullQualifiedName);
					if (propertyType == 'v') {
						newChildItem.setText(newChildItem.getText()
								+ "  [Value: "
								+ modelicaModel.getMainclass()
										.getVariableByName(fullQualifiedName)
										.getPrimitiveValue()
								+ " | Typ: Variable]");
					} else if (propertyType == 'p') {
						newChildItem.setText(newChildItem.getText()
								+ "  [Value: "
								+ modelicaModel.getMainclass()
										.getParameterByName(fullQualifiedName)
										.getPrimitiveValue()
								+ " | Typ: Parameter]");
					}
				}
			}
		}
	}
	
	/**
	 * Call this method to undo changes on a property
	 * All default values from the XML file will be restored.
	 *
	 * @param fullQualifiedName the full qualified name
	 */
	private void restorePropertySetting(String fullQualifiedName) {
		ModelicaProperty restoreProperty;

		if (sampleModelicaModel.getMainclass()
				.getAllFullQualifiedVariableNames().contains(fullQualifiedName)) {
			restoreProperty = sampleModelicaModel.getMainclass()
					.getVariableByName(fullQualifiedName);
			changedPropertiesMap.get(fullQualifiedName)
					.restoreModelicaPropertyData(restoreProperty);

		} else if (sampleModelicaModel.getMainclass()
				.getAllFullQualifiedParameterNames()
				.contains(fullQualifiedName)) {
			restoreProperty = sampleModelicaModel.getMainclass()
					.getParameterByName(fullQualifiedName);
			changedPropertiesMap.get(fullQualifiedName)
					.restoreModelicaPropertyData(restoreProperty);
		}
	}
	
	/**
	 * Signals if a given property has been changed.
	 *
	 * @param property to be checked on modifications
	 * @return true if the given property has been changed otherwise false
	 */
	public boolean isChanged(PropertySetting property){
		return property.isChanged();
	}
	
	/**
	 * This class is to store a changed property value
	 * to restore the old value later and to check which values had been
	 * changed, while creating the simulation operation for the OMC.
	 * All information except the change signal are in the ModelicaProperty class
	 * @author Parham Vasaiely 
	 */
	private class PropertySetting {
		
		/** The changed. */
		private Boolean changed;
		
		/** The temp property. */
		private ModelicaProperty tempProperty;

		/**
		 * Creates a new PropertySetting and copies all values from an existing
		 * ModelicaProperty to its own temporary Property.
		 *
		 * @param originProperty the origin property
		 */
		public PropertySetting(ModelicaProperty originProperty) {
			tempProperty = createNewModelicaPropertyFromExisting(originProperty);
			changed = false;
		}

		/**
		 * Creates a new ModelicaProperty and copies all values from an existing
		 * property it This is important because only a reference cause many
		 * problems.
		 *
		 * @param existingProperty the existing property
		 * @return the modelica property
		 */
		private ModelicaProperty createNewModelicaPropertyFromExisting(
				ModelicaProperty existingProperty) {
			ModelicaProperty mpTemp = null;

			if (existingProperty instanceof ModelicaVariable) {
				try {
					mpTemp = new ModelicaVariable(existingProperty.getName(),
							existingProperty.getType(),
							existingProperty.getComment());
					mpTemp.setPrimitiveValue(existingProperty
							.getPrimitiveValue());
					mpTemp.setDisplay(existingProperty.getDisplay());
					mpTemp.setMinValue(existingProperty.getMinValue());
					mpTemp.setMaxValue(existingProperty.getMaxValue());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (existingProperty instanceof ModelicaParameter) {
				try {
					mpTemp = new ModelicaParameter(existingProperty.getName(),
							existingProperty.getType(),
							existingProperty.getComment());
					mpTemp.setPrimitiveValue(existingProperty
							.getPrimitiveValue());
					mpTemp.setDisplay(existingProperty.getDisplay());
					mpTemp.setMinValue(existingProperty.getMinValue());
					mpTemp.setMaxValue(existingProperty.getMaxValue());
					((ModelicaParameter) mpTemp)
							.setInteractive(((ModelicaParameter) existingProperty)
									.getInteractive());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return mpTemp;
		}

		/**
		 * Use this method to restore the property with a original
		 * ModelicaProperty.
		 *
		 * @param restoreProperty original ModelicaProperty from the XML file
		 */
		public void restoreModelicaPropertyData(ModelicaProperty restoreProperty) {
			if (tempProperty.getName().equals(restoreProperty.getName())) {
				try {
					tempProperty.setPrimitiveValue(restoreProperty
							.getPrimitiveValue());
					tempProperty.setDisplay(restoreProperty.getDisplay());
					tempProperty.setMinValue(restoreProperty.getMinValue());
					tempProperty.setMaxValue(restoreProperty.getMaxValue());
					changed = false;
					if (restoreProperty instanceof ModelicaParameter)
						((ModelicaParameter) tempProperty)
								.setInteractive(((ModelicaParameter) restoreProperty)
										.getInteractive());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		/**
		 * Signals if a property has been changed.
		 *
		 * @return true if property has been changed otherwise false
		 */
		public Boolean isChanged() {
			return changed;
		}

		/**
		 * Marks a modified property as changed.
		 *
		 * @param changed the new changed
		 */
		public void setChanged(Boolean changed) {
			this.changed = changed;
		}

		/**
		 * Gets the temp property.
		 *
		 * @return the temp property
		 */
		public ModelicaProperty getTempProperty() {
			return tempProperty;
		}

		/**
		 * Sets the temp property.
		 *
		 * @param tempProperty the new temp property
		 */
		@SuppressWarnings("unused")
		public void setTempProperty(ModelicaProperty tempProperty) {
			this.tempProperty = tempProperty;
		}
	} //end class PropertyNewValue

}
