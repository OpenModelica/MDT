package org.openmodelica.modelicaml.simulation.view.noninteractive;
///*
// * This file is part of OpenModelica.
// *
// * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
// * c/o Linköpings universitet, Department of Computer and Information Science,
// * SE-58183 Linköping, Sweden.
// *
// * All rights reserved.
// *
// * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
// * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
// * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
// * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
// *
// * The OpenModelica software and the Open Source Modelica
// * Consortium (OSMC) Public License (OSMC-PL) are obtained
// * from OSMC, either from the above address,
// * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
// * http://www.openmodelica.org, and in the OpenModelica distribution. 
// * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
// *
// * This program is distributed WITHOUT ANY WARRANTY; without
// * even the implied warranty of  MERCHANTABILITY or FITNESS
// * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
// * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
// *
// * See the full OSMC Public License conditions for more details.
// *
// * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
// *
// * Contributors: 
// *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
// *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
// */
//package org.openmodelica.modelicaml.simulation.view.noninteractive;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Observable;
//import java.util.Observer;
//import java.util.Scanner;
//import java.util.Set;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.ModifyEvent;
//import org.eclipse.swt.events.ModifyListener;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Listener;
//import org.eclipse.swt.widgets.Text;
//import org.eclipse.swt.widgets.Tree;
//import org.eclipse.swt.widgets.TreeItem;
//import org.eclipse.ui.part.ViewPart;
//import org.openmodelica.modelicaml.simulation.Activator;
//
//// TODO: Auto-generated Javadoc
///**
// * View containing the properties tree which can be seleted or deselected to plot on a view.
// *
// * @author Parham
// */
//public class NonInteractiveSimulationControlView extends ViewPart implements Observer{
//	
//	/** The composite model tree. */
//	private Composite compositeModelTree;
//	
//	/** The tree model. */
//	private Tree treeModel;
//	
//	/** The composite search. */
//	private Composite compositeSearch;
//	
//	/** The button search. */
//	private Button buttonSearch;
//	
//	/** The text find. */
//	private Text textFind;
//	
//	/** The labelsearch. */
//	private Label labelsearch;
//	
//	/** The composite1. */
//	private Composite composite1;
//	
//	/** Containing all available properties. */
//	Set<String> properties;
//	
//	/** Last position in the foundTreeItem list. */
//	private int foundPosition;
//	/**
//	 * Lists all search results to be selected with the search button (next...)
//	 */
//	private List<TreeItem> foundTreeItem;
//	
//	/** The count. */
//	private int count;
//
//	/**
//	 * Instantiates a new non interactive simulation control view.
//	 */
//	public NonInteractiveSimulationControlView() {
//		properties = new HashSet<String>();
//		foundPosition = 0;
//		foundTreeItem = new ArrayList<TreeItem>();
//		count = 0;
//		
//		Set<String> properties = Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().getResults().keySet();
//		this.properties.addAll(properties);
//		
//		Activator.getSimulationCenter_NonInteractive().addPlot(this);
//	}
//	
//	/* (non-Javadoc)
//	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
//	 */
//	@Override
//	public void createPartControl(Composite parent) {
//		{
//			composite1 = new Composite(parent, SWT.NONE);
//			GridLayout composite1Layout = new GridLayout();
//			composite1Layout.makeColumnsEqualWidth = true;
//			composite1.setLayout(composite1Layout);
//			{
//				compositeModelTree = new Composite(composite1, SWT.NONE);
//				GridLayout compositeModelTreeLayout = new GridLayout();
//				compositeModelTreeLayout.makeColumnsEqualWidth = true;
//				GridData compositeModelTreeLData = new GridData();
//				compositeModelTreeLData.horizontalAlignment = GridData.FILL;
//				compositeModelTreeLData.grabExcessVerticalSpace = true;
//				compositeModelTreeLData.verticalAlignment = GridData.FILL;
//				compositeModelTreeLData.grabExcessHorizontalSpace = true;
//				compositeModelTree.setLayoutData(compositeModelTreeLData);
//				compositeModelTree.setLayout(compositeModelTreeLayout);
//				{
//					GridData treeModelLData = new GridData();
//					treeModelLData.horizontalAlignment = GridData.FILL;
//					treeModelLData.grabExcessHorizontalSpace = true;
//					treeModelLData.verticalAlignment = GridData.FILL;
//					treeModelLData.grabExcessVerticalSpace = true;
//					treeModel = new Tree(compositeModelTree, SWT.CHECK);
//					treeModel.setLayoutData(treeModelLData);
//					buildTreeModel(properties);
//					treeModel.addListener(SWT.Selection, new Listener() {
//
//						public void handleEvent(Event event) {
//
//							if (event.detail == SWT.CHECK) {
//
//								TreeItem item = (TreeItem) event.item;
//
//								boolean checked = item.getChecked();
//
//								checkItems(item, checked);
//
//								checkPath(item.getParentItem(), checked, false);
//
//							}
//
//						}
//				    });
//
//
////					treeModel.addSelectionListener(new SelectionListener() {
////						
////						@Override
////						public void widgetSelected(SelectionEvent e) {
////							TreeItem ti = (TreeItem)e.item;
////							if(ti.getData()!=null){ //leaf element selected
////								if(ti.getChecked()){
////									Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().propertySelected((String)ti.getData());
////								}else{
////									Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().propertyUnselection((String)ti.getData());
////								}					
////							}else { // node element selected
////								int countItems =countChildItems(ti);
////								if(ti.getChecked()){
////									System.out.println( ti.getText() + " select: " + countItems);
////									selectItemsRek(ti);
////								}else{
////									System.out.println( ti.getText() + " unselect: " + countItems);
////									unselectItemsRek(ti);
////								}					
////							}
////						}
////						
////						@Override
////						public void widgetDefaultSelected(SelectionEvent e) {
////							// TODO Auto-generated method stub
////							
////						}
////					});
//				}
//			}
//			{
//				compositeSearch = new Composite(composite1, SWT.NONE);
//				GridLayout compositeSearchLayout = new GridLayout();
//				compositeSearchLayout.makeColumnsEqualWidth = true;
//				compositeSearchLayout.numColumns = 5;
//				GridData compositeSearchLData = new GridData();
//				compositeSearchLData.horizontalAlignment = GridData.FILL;
//				compositeSearchLData.verticalAlignment = GridData.END;
//				compositeSearch.setLayoutData(compositeSearchLData);
//				compositeSearch.setLayout(compositeSearchLayout);
//				{
//					labelsearch = new Label(compositeSearch, SWT.NONE);
//					GridData labelsearchLData = new GridData();
//					labelsearch.setLayoutData(labelsearchLData);
//					labelsearch.setText("Find:");
//					labelsearch.setToolTipText("Enter a string to be searched for in all full qualified names. This search is not case sensitive.");
//				}
//				{
//					GridData textFindLData = new GridData();
//					textFindLData.horizontalSpan = 3;
//					textFindLData.horizontalAlignment = GridData.FILL;
//					textFind = new Text(compositeSearch, SWT.BORDER);
//					textFind.setLayoutData(textFindLData);
//					textFind.addModifyListener(new ModifyListener() {
//
//						public void modifyText(ModifyEvent evt) {
//							foundTreeItem.clear();
//							foundPosition = 0;
//							buttonSearch.setText("Search");
//						}
//					});
//				}
//				{
//					buttonSearch = new Button(compositeSearch, SWT.PUSH | SWT.CENTER);
//					GridData buttonSearchLData = new GridData();
//					buttonSearch.setLayoutData(buttonSearchLData);
//					buttonSearch.setText("Search");
//					buttonSearch.addSelectionListener(new SelectionAdapter() {
//						
//						@Override
//						public void widgetSelected(SelectionEvent e) {
//							super.widgetSelected(e);
//							if(foundTreeItem.isEmpty() && !textFind.getText().isEmpty()){ //Is a new search
//								search(textFind.getText());
//								buttonSearch.setText("Next");
//								System.out.println(foundTreeItem);
//								showNextSearchResult();
//							} else{//Go to next found element
//								showNextSearchResult();
//							}
//						}
//					});
//				}
//			}
//		}
//	}
//	
//	/**
//	 * Show the next search result, if the last found result has already been displayed start from beginning.
//	 */
//	private void showNextSearchResult(){
//		treeModel.select(foundTreeItem.get(foundPosition));
//		treeModel.showSelection();
//		if(foundTreeItem.size()-1<=foundPosition){
//			foundPosition = 0; //Search from begining
//		}else{
//			foundPosition++;
//		}
//	}
//	
//	/**
//	 * Searches recursively for an element using the rekSearch method.
//	 *
//	 * @param name the name
//	 */
//	private void search(String name) {
//		rekSearch(treeModel.getItems()[0], name); //treeModel.getItems()[0] is the root element!
//	}
//	
//	/**
//	 * Rek search.
//	 *
//	 * @param item the item
//	 * @param name the name
//	 */
//	private void rekSearch(TreeItem item, String name){
//		if(item.getItemCount() != 0){ //is node
//			for(TreeItem child : item.getItems()){
//				rekSearch(child, name);
//			}
//		} else{ //is leaf
//			if(((String)item.getData()).toLowerCase().contains(name.toLowerCase())){
//				foundTreeItem.add(item);
//			}
//		}
//	}
//	
//	/**
//	 * Builds the tree model.
//	 *
//	 * @param properties the properties
//	 */
//	private void buildTreeModel(Set<String> properties) {
//		if(properties != null && !properties.isEmpty()){
//			TreeItem rootItem = new TreeItem(treeModel, SWT.NULL);
//			rootItem.setText("Model-Name");//TODO 20120129 change to dynamic name
//					
//			for (String fullQualifiedName : properties) {
//				if(!fullQualifiedName.startsWith("der("))
//					createModelElement(rootItem, fullQualifiedName, fullQualifiedName);
//				else{
//					String derName = fullQualifiedName.replace("der(", "");
//					derName = derName.replace(")", "");
//					createModelElementForDerivative(rootItem, derName, fullQualifiedName);
//				}
//			}
//		}
//	}
//	
//	/**
//	 * Recursive method to build the treeModel with all nodes and child elements.
//	 *
//	 * @param itemParent parant tree item
//	 * @param restOfFullQualifiedName the rest of full qualified name
//	 * @param fullQualifiedName the full qualified name
//	 */
//	private void createModelElement(TreeItem itemParent,
//			String restOfFullQualifiedName, String fullQualifiedName) {
//
//		Scanner sc = new Scanner(restOfFullQualifiedName);
//		sc.useDelimiter("\\.");
//		if (sc.hasNext()) {
//			String name = sc.next();
//
//			if (itemParent.getItems().length != 0) {
//				boolean hasChild = false;
//				for (TreeItem childItem : itemParent.getItems()) {
//					if (childItem.getText().equals(name)) {
//						if (sc.hasNextLine())
//							createModelElement(childItem, sc.nextLine(),
//									fullQualifiedName);
//						hasChild = true;
//						break;
//					}
//				}
//				if (!hasChild) {
//					TreeItem newChildItem = new TreeItem(itemParent, SWT.NULL);
//					newChildItem.setText(name);
//					newChildItem.setChecked(false);
//					if (sc.hasNextLine()) {
//						createModelElement(newChildItem, sc.nextLine(),
//								fullQualifiedName);
//					} else {
//						newChildItem.setData(fullQualifiedName);
//						
//						newChildItem.setText(newChildItem.getText());
//					}
//				}
//			} else {
//				TreeItem newChildItem = new TreeItem(itemParent, SWT.NULL);
//				newChildItem.setText(name);
//				newChildItem.setChecked(false);
//				if (sc.hasNextLine()) {
//					createModelElement(newChildItem, sc.nextLine(),
//							fullQualifiedName);
//				} else {
//					newChildItem.setData(fullQualifiedName);
//
//						newChildItem.setText(newChildItem.getText());
//					
//				}
//			}
//		}
//	}
//
//	/**
//	 * Recursive method to build the treeModel with all nodes and child elements for a derivative property. The prefix der(...) will be added to the name
//	 *
//	 * @param itemParent parant tree item
//	 * @param restOfFullQualifiedName the rest of full qualified name
//	 * @param fullQualifiedName the full qualified name
//	 */
//	private void createModelElementForDerivative(TreeItem itemParent,
//			String restOfFullQualifiedName, String fullQualifiedName) {
//
//		Scanner sc = new Scanner(restOfFullQualifiedName);
//		sc.useDelimiter("\\.");
//		if (sc.hasNext()) {
//			String name = sc.next();
//
//			if (itemParent.getItems().length != 0) {
//				boolean hasChild = false;
//				for (TreeItem childItem : itemParent.getItems()) {
//					if (childItem.getText().equals(name)) {
//						if (sc.hasNextLine())
//							createModelElementForDerivative(childItem, sc.nextLine(),
//									fullQualifiedName);
//						hasChild = true;
//						break;
//					}
//				}
//				if (!hasChild) {
//					TreeItem newChildItem = new TreeItem(itemParent, SWT.NULL);
//					newChildItem.setChecked(false);
//					if (sc.hasNextLine()) {
//						newChildItem.setText(name);
//						createModelElementForDerivative(newChildItem, sc.nextLine(),
//								fullQualifiedName);
//					} else {
//						newChildItem.setText("der(" + name + ")");
//						newChildItem.setData(fullQualifiedName);
//						
//						//newChildItem.setText(newChildItem.getText());
//					}
//				}
//			} else {
//				TreeItem newChildItem = new TreeItem(itemParent, SWT.NULL);
//				newChildItem.setChecked(false);
//				if (sc.hasNextLine()) {
//					newChildItem.setText(name);
//					createModelElementForDerivative(newChildItem, sc.nextLine(),
//							fullQualifiedName);
//				} else {
//					newChildItem.setText("der(" + name + ")");
//					newChildItem.setData(fullQualifiedName);
//
////						newChildItem.setText(newChildItem.getText());
//					
//				}
//			}
//		}
//	}
//
//	
//	/**
//	 * Count child items.
//	 *
//	 * @param ti the ti
//	 * @return the int
//	 */
//	@SuppressWarnings("unused")
//	private int countChildItems(TreeItem ti){
//		count = 0;
//		
//		getCountRek(ti);
//		
//		return count;
//	}
//	
//	/**
//	 * Gets the count rek.
//	 *
//	 * @param item the item
//	 * @return the count rek
//	 */
//	private void getCountRek(TreeItem item){
//		if(item.getItemCount() != 0){ //is node
//			for(TreeItem child : item.getItems()){
//				getCountRek(child);
//			}
//		} else{ //is leaf
//			count++;
//		}
//	}
//	
//	/**
//	 * Check path.
//	 *
//	 * @param item the item
//	 * @param checked the checked
//	 * @param grayed the grayed
//	 */
//	private void checkPath(TreeItem item, boolean checked, boolean grayed) {
//
//		if (item == null)
//			return;
//
//		if (grayed) {
//
//			checked = true;
//
//		} else {
//
//			int index = 0;
//
//			TreeItem[] items = item.getItems();
//
//			while (index < items.length) {
//
//				TreeItem child = items[index];
//
//				if (child.getGrayed()
//
//				|| checked != child.getChecked()) {
//
//					checked = grayed = true;
//
//					break;
//
//				}
//
//				index++;
//
//			}
//
//		}
//
//		item.setChecked(checked);
//		if(item.getData()!=null){
//			if(checked){
//				Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().propertySelected((String)item.getData());
//			}else{
//				Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().propertyUnselection((String)item.getData());
//			}
//		}
//
//		item.setGrayed(grayed);
//
//		checkPath(item.getParentItem(), checked, grayed);
//
//	}
//
//
//
//	/**
//	 * Check items.
//	 *
//	 * @param item the item
//	 * @param checked the checked
//	 */
//	private void checkItems(TreeItem item, boolean checked) {
//
//		item.setGrayed(false);
//
//		item.setChecked(checked);
//		
//		if(item.getData()!=null){
//			if(checked){
//				Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().propertySelected((String)item.getData());
//			}else{
//				Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().propertyUnselection((String)item.getData());
//			}
//		}
//
//		TreeItem[] items = item.getItems();
//
//		for (int i = 0; i < items.length; i++) {
//
//			checkItems(items[i], checked);
//
//		}
//
//	}
//
//	/**
//	 * New simulation.
//	 */
//	public void newSimulation(){
//		foundPosition = 0;
//		foundTreeItem = new ArrayList<TreeItem>();
//		count = 0;
//		textFind.setText("");
//		Set<String> properties = Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().getResults().keySet();
//		this.properties.addAll(properties);
//		treeModel.removeAll();
//		buildTreeModel(properties);
//	}
//
//	/* (non-Javadoc)
//	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
//	 */
//	@Override
//	public void update(Observable arg0, Object arg1) {
//		if(((String)arg1).equals("newsimulation")){
//			Display.getDefault().asyncExec(new Runnable() {
//				public void run() {
//					newSimulation();
//				}
//			});
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
//	 */
//	@Override
//	public void setFocus() {
//		// TODO Auto-generated method stub
//	}
//}
