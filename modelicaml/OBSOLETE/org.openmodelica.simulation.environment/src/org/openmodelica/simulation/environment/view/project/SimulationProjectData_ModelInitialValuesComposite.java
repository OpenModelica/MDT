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
package org.openmodelica.simulation.environment.view.project;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.openmodelica.simulation.core.models.modelica.ModelicaModel;
import org.openmodelica.simulation.core.models.simulation.SimulationProject;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * This composite displays all data from the init.xml file. 
 * The data can not be modified, the user can only views the property names and its values.
 * 
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 *
 */
public class SimulationProjectData_ModelInitialValuesComposite extends org.eclipse.swt.widgets.Composite implements Observer{

	/** The composite model tree. */
	private Composite compositeModelTree;
	
	/** The tree model. */
	private Tree treeModel;
	
	/** The composite search. */
	private Composite compositeSearch;
	
	/** The button search. */
	private Button buttonSearch;
	
	/** The text search. */
	private Text textSearch;
	
	/** The label search. */
	private Label labelSearch;
	
	/**
	 * Last position in the foundTreeItem list
	 */
	private int foundPosition;
	/**
	 * Lists all search results to be selected with the search button (next...)
	 */
	private List<TreeItem> foundTreeItem;

//	/**
//	* Auto-generated main method to display this 
//	* org.eclipse.swt.widgets.Composite inside a new Shell.
//	*/
//	public static void main(String[] args) {
//		showGUI();
//	}
	
	/**
	* Overriding checkSubclass allows this class to extend org.eclipse.swt.widgets.Composite
	*/	
	protected void checkSubclass() {
	}
	
	/**
	* Auto-generated method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		SimulationProjectData_ModelInitialValuesComposite inst = new SimulationProjectData_ModelInitialValuesComposite(shell, SWT.NULL);
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if(size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * Instantiates a new simulation project data_ model initial values composite.
	 *
	 * @param parent the parent
	 * @param style the style
	 */
	public SimulationProjectData_ModelInitialValuesComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		foundPosition = 0;
		foundTreeItem = new ArrayList<TreeItem>();
		initGUI();
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout();
			thisLayout.makeColumnsEqualWidth = true;
			this.setLayout(thisLayout);
			{
				compositeModelTree = new Composite(this, SWT.NONE);
				FillLayout compositeModelTreeLayout = new FillLayout(org.eclipse.swt.SWT.HORIZONTAL);
				GridData compositeModelTreeLData = new GridData();
				compositeModelTreeLData.horizontalAlignment = GridData.FILL;
				compositeModelTreeLData.grabExcessHorizontalSpace = true;
				compositeModelTreeLData.verticalAlignment = GridData.FILL;
				compositeModelTreeLData.grabExcessVerticalSpace = true;
				compositeModelTree.setLayoutData(compositeModelTreeLData);
				compositeModelTree.setLayout(compositeModelTreeLayout);
				{
					treeModel = new Tree(compositeModelTree, SWT.NONE);
					buildTreeModel(null);
				}
			}
			{
				compositeSearch = new Composite(this, SWT.BORDER);
				GridLayout compositeSearchLayout = new GridLayout();
				compositeSearchLayout.makeColumnsEqualWidth = true;
				compositeSearchLayout.numColumns = 5;
				GridData compositeSearchLData = new GridData();
				compositeSearchLData.horizontalAlignment = GridData.FILL;
				compositeSearchLData.grabExcessHorizontalSpace = true;
				compositeSearchLData.verticalAlignment = GridData.END;
				compositeSearch.setLayoutData(compositeSearchLData);
				compositeSearch.setLayout(compositeSearchLayout);
				{
					labelSearch = new Label(compositeSearch, SWT.NONE);
					GridData labelSearchLData = new GridData();
					labelSearchLData.horizontalAlignment = GridData.CENTER;
					labelSearch.setLayoutData(labelSearchLData);
					labelSearch.setText("Search for:");
					labelSearch.setToolTipText("Enter a string to be searched for in all full qualified names. This search is not case sensitive.");
				}
				{
					GridData textSearchLData = new GridData();
					textSearchLData.horizontalSpan = 3;
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
								showNextSearchResult();
							} else{//Go to next found element
								showNextSearchResult();
							}
						}
					});
				}
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * Builds the tree model.
	 *
	 * @param modelicaModel the modelica model
	 */
	private void buildTreeModel(ModelicaModel modelicaModel) {
		if(modelicaModel != null){
			TreeItem rootItem = new TreeItem(treeModel, SWT.NULL);
			rootItem.setText(modelicaModel.getName());
			
			Set<String> fullQualifiedVariableNames = modelicaModel.getMainclass()
			.getAllFullQualifiedVariableNames();
			
			Set<String> fullQualifiedParameterNames = modelicaModel.getMainclass()
			.getAllFullQualifiedParameterNames();
			
			for (String fullQualifiedName : fullQualifiedParameterNames) {
				createModelElement(modelicaModel, rootItem, fullQualifiedName, fullQualifiedName,
				'p');
			}
			
			for (String fullQualifiedName : fullQualifiedVariableNames) {
				System.out.println(fullQualifiedName);
				System.out.println(fullQualifiedName.contains("der("));
				if(fullQualifiedName.contains("der(")){
					System.out.println("der...");
					fullQualifiedName = fullQualifiedName.replaceAll("der\\(", "");
					fullQualifiedName = fullQualifiedName.replaceAll("\\)", "");
					createModelElement(modelicaModel, rootItem, fullQualifiedName, fullQualifiedName,
					'd');
				}
				createModelElement(modelicaModel, rootItem, fullQualifiedName, fullQualifiedName,
				'v'); //TODO [20110708] der(h) wird weg gelassen!
			}
		}else{
			treeModel.removeAll();
		}
	}
	
	/**
	 * Recursive method to build the treeModel with all nodes and child elements
	 * @param itemParent parant tree item
	 * @param restOfFullQualifiedName
	 * @param fullQualifiedName
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
							createModelElement(modelicaModel, childItem, sc.nextLine(),
									fullQualifiedName, propertyType);
						hasChild = true;
						break;
					}
				}
				if (!hasChild) {
					TreeItem newChildItem = new TreeItem(itemParent, SWT.NULL);
					newChildItem.setText(name);
					if (sc.hasNextLine()) {
						createModelElement(modelicaModel, newChildItem, sc.nextLine(),
								fullQualifiedName, propertyType);
					} else {
						if (propertyType == 'v') {
							newChildItem.setData(fullQualifiedName);
							newChildItem.setText(newChildItem.getText()
									+ "  [Value: "
									+ modelicaModel.getMainclass()
											.getVariableByName(
													fullQualifiedName)
											.getPrimitiveValue() + " | Typ: Variable]");
						} else if (propertyType == 'p') {
							newChildItem.setData(fullQualifiedName);
							newChildItem.setText(newChildItem.getText()
									+ "  [Value: "
									+ modelicaModel.getMainclass()
											.getParameterByName(
													fullQualifiedName)
											.getPrimitiveValue() + " | Typ: Parameter]");
						}  else if (propertyType == 'd') {
							newChildItem.setData("der(" + fullQualifiedName + ")");
							newChildItem.setText("der(" + newChildItem.getText() + ")"
									+ "  [Value: "
									+ modelicaModel.getMainclass()
											.getVariableByName(fullQualifiedName)
											.getPrimitiveValue() + " | Typ: Variable]");
						}
					}
				}
			} else {
				TreeItem newChildItem = new TreeItem(itemParent, SWT.NULL);
				newChildItem.setText(name);
				if (sc.hasNextLine()) {
					createModelElement(modelicaModel, newChildItem, sc.nextLine(),
							fullQualifiedName, propertyType);
				} else {
					if (propertyType == 'v') {
						newChildItem.setData(fullQualifiedName);
						newChildItem.setText(newChildItem.getText()
								+ "  [Value: "
								+ modelicaModel.getMainclass()
										.getVariableByName(fullQualifiedName)
										.getPrimitiveValue() + " | Typ: Variable]");
					} else if (propertyType == 'p') {
						newChildItem.setData(fullQualifiedName);
						newChildItem.setText(newChildItem.getText()
								+ "  [Value: "
								+ modelicaModel.getMainclass()
										.getParameterByName(fullQualifiedName)
										.getPrimitiveValue() + " | Typ: Parameter]");
					} else if (propertyType == 'd') {
						newChildItem.setData("der(" + fullQualifiedName + ")");
						newChildItem.setText("der(" + newChildItem.getText() + ")"
								+ "  [Value: "
								+ modelicaModel.getMainclass()
										.getVariableByName(fullQualifiedName)
										.getPrimitiveValue() + " | Typ: Variable]");
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		treeModel.removeAll();
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				Display.getDefault().readAndDispatch();
				SimulationProject selectedSimulationProject = Activator
						.getSimulationProjectCenter()
						.getSelectedSimulationProject();
				
				if(selectedSimulationProject != null){
					buildTreeModel(selectedSimulationProject.getModelicaModel());
				} else{
					buildTreeModel(null);
				}
			}
		});
		
	}


}
