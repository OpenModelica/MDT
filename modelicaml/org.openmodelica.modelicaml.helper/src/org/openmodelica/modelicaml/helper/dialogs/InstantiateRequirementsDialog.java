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
 * Contributors: 
 *   Wladimir Schamai, EADS Innovation Works 2009-2011
 *   Uwe Pohlmann, University of Paderborn 2009-2010
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011
 */
package org.openmodelica.modelicaml.helper.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.wb.swt.SWTResourceManager;
import org.openmodelica.modelicaml.common.ast.ModelicaMLAST;
import org.openmodelica.modelicaml.common.ast.TreeParent;
import org.openmodelica.modelicaml.helper.impl.RequirementsInstantiator;


// TODO: Auto-generated Javadoc
/**
 * The Class InstantiateRequirementsDialog.
 */
public class InstantiateRequirementsDialog extends Dialog {

	/** The req list. */
	private List<Class> reqList = new ArrayList<Class>();
	
	/** The selected req list. */
	private HashSet<Class> selectedReqList = new HashSet<Class>();
	
	/** The ok button. */
	private Object okButton;
	
	/** The containing class. */
	private Class containingClass = null;
	
	/** The sort filter. */
	private String sortFilter = "name";
	
	/** The expand bar. */
	private ExpandBar expandBar = null;
	
	/** The req expand items list. */
	private List<ExpandItem> reqExpandItemsList = new ArrayList<ExpandItem>();
	
	/** The req groups list. */
	private List<Group> reqGroupsList = new ArrayList<Group>();
	
	/** The key word list. */
	private List<String> keyWordList = new ArrayList<String>();

	private HashMap<Class, Integer> selectedNumberOfInstantiations = new HashMap<Class, Integer>();

	/**
	 * Instantiates a new instantiate requirements dialog.
	 * 
	 * @param shell
	 *            the shell
	 * @param containingClass
	 *            the containing class
	 */
	public InstantiateRequirementsDialog(Shell shell, Class containingClass){
		super(shell);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		this.containingClass = containingClass; 
		this.reqList = getAllRequirementsFromModel(this.containingClass.getModel());
	}
	
	/**
	 * Gets the selected list.
	 * 
	 * @return the selected list
	 */
	public HashSet<Class> getSelectedList(){
		return this.selectedReqList;
	}
	
	public HashMap<Class, Integer> getSelectedNumberOfInstantiations(){
		return this.selectedNumberOfInstantiations;
	}

	/**
	 * Create contents of the window.
	 * 
	 * @param parent
	 *            the parent
	 * @return the control
	 */
    protected Control createDialogArea(Composite parent) {
    	Composite shell = (Composite) super.createDialogArea(parent);
    	shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		shell.setSize(714, 700);
		
		shell.setLayout(new GridLayout(1, false));
    	
		Composite reqDataComposite = new Composite(shell, SWT.NONE);
		reqDataComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblSelectRequirementsTo = new Label(reqDataComposite, SWT.NONE);
		lblSelectRequirementsTo.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lblSelectRequirementsTo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblSelectRequirementsTo.setBounds(0, 0, 238, 18);

		if (reqList != null && reqList.size() > 0) {
			// NEW Label for the owning class name
			lblSelectRequirementsTo.setText("Select requirements to be instantiated in:");
			CLabel label_1 = new CLabel(reqDataComposite, SWT.NONE);
			label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			label_1.setTopMargin(0);
			label_1.setBottomMargin(0);
			
			// Set the name of containing class 
			label_1.setText(containingClass.getName());	
			label_1.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
			label_1.setBounds(244, 0, 450, 18);
		}
		else {
			lblSelectRequirementsTo.setText("No requirements are found in " + containingClass.getModel().getName() +  ".");
		}
		
		//############################ Filter and Search
		Composite composite_2 = new Composite(shell, SWT.BORDER);
		GridData gd_composite_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_2.heightHint = 28;
		gd_composite_2.widthHint = 695;
		composite_2.setLayoutData(gd_composite_2);
		
		CLabel lblNewLabel = new CLabel(composite_2, SWT.NONE);
		lblNewLabel.setBounds(2, 4, 45, 19);
		lblNewLabel.setText("Sort by:");
		
		final CCombo sortByString = new CCombo(composite_2, SWT.BORDER);
		sortByString.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sortFilter = sortByString.getText();
				update();
			}
		});
		sortByString.setItems(new String[] {"name","id"});
		sortByString.setEditable(false);
		sortByString.setText("name");
		sortByString.setBounds(53, 4, 88, 21);
		
		CLabel lblSearchFor = new CLabel(composite_2, SWT.NONE);
		lblSearchFor.setBounds(161, 4, 75, 19);
		lblSearchFor.setText("or search for: ");
		
		final Text keyWords = new Text(composite_2, SWT.BORDER);
		keyWords.setBounds(237, 4, 311, 19);
		
		final Button btnSearch = new Button(composite_2, SWT.NONE);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				keyWordList.clear();
				String keyWordsString = keyWords.getText();
				String[] keyWords = keyWordsString.split(" ");
				for (int i = 0; i < keyWords.length; i++) {
					keyWordList.add(keyWords[i]);
				}
				update();
			}
		});
		btnSearch.setBounds(554, 2, 54, 23);
		btnSearch.setText("Search");
		
		keyWords.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (keyWords.getText().equals("")) {
					btnSearch.setText("Show all");
				}
				else {
					btnSearch.setText("Search");
				}
			}
		});
		// ################################## Filter and Search END
		
    	expandBar = new ExpandBar(shell, SWT.V_SCROLL);
		expandBar.setSpacing(2);
		GridData gd_expandBar = new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1);
		gd_expandBar.heightHint = 626;
		gd_expandBar.widthHint = 1400;
		expandBar.setLayoutData(gd_expandBar);
		//expandBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Composite composite = new Composite(expandBar, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		drawExpandBar(expandBar);
		
		return shell;
	}
    
    
    /**
	 * Gets the all requirements from model.
	 * 
	 * @param model
	 *            the model
	 * @return the all requirements from model
	 */
    private List<Class> getAllRequirementsFromModel(Model model){
		if (model != null) {
			Iterator<EObject> i = model.eAllContents();
			List<Class> requirements = new ArrayList<Class>();
			
			HashMap<String, Class> map = new HashMap<String, Class>();
			
			while (i.hasNext()) {
				EObject object = i.next() ;
				if (object instanceof Class) {
					if ( ((Class)object).getAppliedStereotype("ModelicaML::ModelicaRequirementConstructs::Requirement") != null) {

						//TODO: bug: once the key words search was performed the sorting based on name or id does not work ...
						
						if (keyWordList != null && keyWordList.size() > 0) { // search
							Stereotype stereotype = ((Class)object).getAppliedStereotype("ModelicaML::ModelicaRequirementConstructs::Requirement");
							String name = ((Class)object).getName();
							String id = "" + (String) ((Class)object).getValue(stereotype, "id");
							String text = "" + (String) ((Class)object).getValue(stereotype, "text");
							for (String keyword : keyWordList) {
								if (text != null && text.toUpperCase().contains(keyword.toUpperCase())) {
									map.put(((Class)object).getName(), (Class) object);
								}
								else if (name != null &&  name.toUpperCase().contains(keyword.toUpperCase())) {
									map.put(((Class)object).getName(), (Class) object);
								} 
								else if (id != null && id.toUpperCase().contains(keyword.toUpperCase())) {
									map.put(((Class)object).getName(), (Class) object);
								}
							}
						}
						else { //no search -> use the sortFilter
							if (sortFilter.equals("name")) {
								map.put(((Class)object).getName(), (Class) object);
							}
							else if (sortFilter.equals("id")) {
								Stereotype stereotype = ((Class)object).getAppliedStereotype("ModelicaML::ModelicaRequirementConstructs::Requirement");
								String id = "" + (String) ((Class)object).getValue(stereotype, "id");
								
								if (stereotype!= null) {
									// put the id
									map.put(id, (Class) object);
								}
								else {
									// put the name instead
									map.put(((Class)object).getName(), (Class) object);
								}
							}
						}
					}
				}
			}
			
			List<String> sortedList = new ArrayList<String>(map.keySet());
			Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
			
			for (String string : sortedList) {
				Class class1 = map.get(string);
				requirements.add(class1);
			}
			
			return requirements;
		}
		return null;
	}
		
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
      	shell.setSize(714, 700);
      	shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
      	shell.setText("ModelicaML Helper: Instantiate Requirements");
       	shell.setImage(SWTResourceManager.getImage(InstantiateRequirementsDialog.class, "/icons/list-accept.png"));
    }
	
    /**
	 * Update.
	 */
    private void update(){
    	// Update the requirements list
    	reqList = getAllRequirementsFromModel(this.containingClass.getModel());

    	// dispose the old list
    	for (Group item : reqGroupsList) {
			item.dispose();
		}
    	for (ExpandItem item : reqExpandItemsList) {
			item.dispose();
		}
    	// redraw the list
    	drawExpandBar(expandBar);
    }
    
    
//    
//    /**
//     * Gets the requirement input properties list.
//     *
//     * @param req the req
//     * @return the requirement input properties list
//     */
//    private EList<Property> getRequirementInputPropertiesList(Class req){
//    	EList<Property> inputsList = new BasicEList<Property>();
//    	EList<Property> list = req.getAllAttributes();
//    	for (Property property : list) {
//			Stereotype stereotype = property.getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::Variable");
//			Object causality = property.getValue(stereotype, "causality");
//			
//			if (causality != null && causality.equals("input")) {
//				list.add(property);
//			}
//		}
//    	return list;
//    }
//    
//    
//    /**
//     * Gets the number of required instantiations.
//     *
//     * @param req the req
//     * @return the number of required instantiations
//     */
//    private Integer getNumberOfRequiredInstantiations(Class req){
//
//    	ModelicaMLAST ast = new ModelicaMLAST(this.containingClass, true);
//		ast.createTree();
//		TreeParent root = ast.getTreeRoot();
//		
//    	EList<Property> inputsList = getRequirementInputPropertiesList(req);
//    	Integer numberOrRequiredInstantiations = 0;
//    	for (Property property : inputsList) {
//    		if (root != null) {
//    			String valueBindingStereotypeQName =  "ModelicaMLTesting::ValueBinding::ValueClient";
//    			String valueBindingPropertyName = "obtainsValueFrom";
//    	    	
//    			HashSet<Property> listOfProxies = root.getValueBindings(property, valueBindingStereotypeQName, valueBindingPropertyName);
//    			HashSet<String> listOfDotPathThroughProxies = root.getAllLinkedPrimitiveVariablesDotPathThroughProxy(property, listOfProxies, root);
//    			if (listOfDotPathThroughProxies.size() > numberOrRequiredInstantiations) {
//    	    		numberOrRequiredInstantiations = listOfDotPathThroughProxies.size(); 
//				}
//    			System.err.println(listOfDotPathThroughProxies);
//    			
////    			System.err.println("listOfDotPathThroughProxies.size(): " + listOfDotPathThroughProxies.size());
////    			System.err.println("numberOrRequiredInstantiations: " + numberOrRequiredInstantiations);
//			}
//    		else {
//    			System.err.println("Cannot access the instantiator tree root");
//    		}
//		}
//    	return numberOrRequiredInstantiations;
//    }
    
    /**
	 * Draw expand bar.
	 * 
	 * @param expandBar
	 *            the expand bar
	 * @return the expand bar
	 */
    private ExpandBar drawExpandBar(ExpandBar expandBar){
    	
    	for (Class req : reqList) {
			Stereotype stereotype = req.getAppliedStereotype("ModelicaML::ModelicaRequirementConstructs::Requirement");
			if (stereotype != null) {
				String title = "" + req.getName();
				Object id = "" + req.getValue(stereotype, "id");
				Object text = "" + req.getValue(stereotype, "text");;
				String qName = "" + req.getQualifiedName();

				Class reqClass = req;
				RequirementsInstantiator ri = new RequirementsInstantiator();
				final int numberOfExisingInstantiations = ri.getNumberOfExisitngClassInstances(this.containingClass, reqClass);
//				System.err.println("numberOfExisingInstantiations: " + numberOfExisingInstantiations);
				final int numberOfRequiredInstantiations = ri.getNumberOfRequiredInstantiations(this.containingClass, reqClass);
//				System.err.println("numberOfRequiredInstantiations: " + numberOfRequiredInstantiations);
				
				int recommendedNumberOfInstantiations = 0;
				if ( (numberOfRequiredInstantiations - numberOfExisingInstantiations) > 0 ) {
					recommendedNumberOfInstantiations = numberOfRequiredInstantiations - numberOfExisingInstantiations;
				}
				
				final ExpandItem reqExpandItem = new ExpandItem(expandBar, SWT.NONE);
				reqExpandItemsList.add(reqExpandItem);
				
				reqExpandItem.setExpanded(false);
				
				// mark requirement that is already instantiated 
				if (numberOfExisingInstantiations > 0 ) {
					reqExpandItem.setImage(SWTResourceManager.getImage(InstantiateRequirementsDialog.class, "/icons/exists_already.png"));
				}

				// Set Req. Title
				reqExpandItem.setText(id + ": " + title);
				
				Group grpId = new Group(expandBar, SWT.NONE);
				reqGroupsList.add(grpId);
				grpId.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				
				// Set Req. ID
				//grpId.setText("id: " + id.toString() + "   " + qName);
				grpId.setText(qName);
				reqExpandItem.setControl(grpId);
				
				
				final CCombo numberOfInstantiations = new CCombo(grpId, SWT.BORDER);
//				numberOfInstantiations.addSelectionListener(new SelectionAdapter() {
//					@Override
//					public void widgetSelected(SelectionEvent e) {
//						
//					}
//				});
				
				final Class temp_req = req; 
				final int number = recommendedNumberOfInstantiations;
				numberOfInstantiations.addModifyListener(new ModifyListener() {
					
					@Override
					public void modifyText(ModifyEvent e) {
						try {
							Integer n = Integer.valueOf(numberOfInstantiations.getText());
							if (n > 0 ) {
								selectedNumberOfInstantiations.remove(temp_req);	
								selectedNumberOfInstantiations.put(temp_req, n);
//								System.err.println(n);
							}
							else {
//								numberOfInstantiations.setText("" + advisedNumberOfInstantiations);
							}
							
						} catch (Exception e2) {
							numberOfInstantiations.setText("" + number);
						}

					}
				});
				
				numberOfInstantiations.setItems(new String[] { "" + recommendedNumberOfInstantiations});
				numberOfInstantiations.setEditable(true);
				numberOfInstantiations.setText("" + recommendedNumberOfInstantiations);
				numberOfInstantiations.setBounds(30, 18, 39, 18);
				numberOfInstantiations.setEnabled(false);
				selectedNumberOfInstantiations.put(temp_req, recommendedNumberOfInstantiations);
				
				final Button selectCheckBox = new Button(grpId, SWT.CHECK);
				// Set Req. Reference
				selectCheckBox.setData(req);
				selectCheckBox.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if (selectCheckBox.getSelection() == true) {
							// Add to list
							selectedReqList.add((Class) selectCheckBox.getData());
							reqExpandItem.setImage(SWTResourceManager.getImage(InstantiateRequirementsDialog.class, "/icons/button_ok.png"));
							//MessageDialog.openInformation(new Shell(), "Added", ((Integer) select.getData()).toString());
							numberOfInstantiations.setEnabled(true);
						}
						else {
							// Remove from list
							selectedReqList.remove((Class) selectCheckBox.getData());
							
							// mark requirement that is already instantiated 
							if (numberOfExisingInstantiations > 0 ) {
								reqExpandItem.setImage(SWTResourceManager.getImage(InstantiateRequirementsDialog.class, "/icons/exists_already.png"));
							}
							else {
								reqExpandItem.setImage(null);
							}
							//MessageDialog.openInformation(new Shell(), "Removed", ((Integer) select.getData()).toString());
							numberOfInstantiations.setEnabled(false);
						}
					}
				});
				
				selectCheckBox.setText("select");
				selectCheckBox.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				selectCheckBox.setBounds(10, 20, 13, 13);
						
//				// Set requirement qualified name
//				CLabel reqQualifiedName = new CLabel(grpId, SWT.NONE);
//				reqQualifiedName.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
//				reqQualifiedName.setFont(SWTResourceManager.getFont("Arial", 8, SWT.NORMAL));
//				reqQualifiedName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//				reqQualifiedName.setBounds(29, 17, 645, 13);
//				reqQualifiedName.setText(qName);
				
//				if (numberOfRequiredInstantiations > 1) {
				// Set additional Information
				CLabel additionalInfo = new CLabel(grpId, SWT.NONE);
				additionalInfo.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
				additionalInfo.setFont(SWTResourceManager.getFont("Arial", 8, SWT.NORMAL));
				additionalInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				additionalInfo.setBounds(75, 20, 500, 13);
				String textString = "time(s), because it should be instantiated at least " + numberOfRequiredInstantiations + " time(s) " + " and is already instantiated " + numberOfExisingInstantiations + " time(s).";
				additionalInfo.setText(textString);
//				}
				
				//StyledText reqText = new StyledText(grpId, SWT.FULL_SELECTION | SWT.WRAP | SWT.V_SCROLL);
				StyledText reqText = new StyledText(grpId, SWT.FULL_SELECTION | SWT.WRAP);
				reqText.setIndent(1);
				reqText.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
				reqText.setEditable(false);
				
				// Set Req. Text
				reqText.setText(text.toString());
				//reqText.setBounds(29, 36, 656, 98);
				reqText.setBounds(29, 45, expandBar.getShell().getBounds().width - 70, SWT.VERTICAL - 129);
				
				grpId.setTabList(new Control[]{selectCheckBox, reqText});
				reqExpandItem.setHeight(reqExpandItem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);	
			}
		}
		return expandBar;
    }
    
    
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent) {
        // create OK and Cancel buttons by default
        //parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
    	okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }
	
	
    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
        	
        } else {
        	
        }
        super.buttonPressed(buttonId);
    }
    
}
