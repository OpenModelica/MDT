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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
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
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.simulation.plot;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class VariableTreeComposite extends org.eclipse.swt.widgets.Composite implements Observer{
	private Composite mainComposite;

	
	private String simulationModelName;
	
	public String getSimulationModelName() {
		return simulationModelName;
	}

	public void setSimulationModelName(String simulationModelName) {
		this.simulationModelName = simulationModelName;
	}

	private PlotComposite chart;
	
	/** The composite model tree. */
	private Composite compositeModelTree;
	
	/** The tree model. */
	private Tree treeModel;
	


	/** The composite search. */
	private Composite compositeSearch;

	/** The button search. */
	private Button buttonSearch;
	
	/** The text find. */
	private Text textFind;
	
	/** The labelsearch. */
	private Label labelsearch;
	
	/** Containing all available properties. */
	List<String> properties;
	
	/** Last position in the foundTreeItem list. */
	private int foundPosition;
	/**
	 * Lists all search results to be selected with the search button (next...)
	 */
	private List<TreeItem> foundTreeItem;
	
	/** The count. */
	private int count;
	
	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
		
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
		VariableTreeComposite inst = new VariableTreeComposite(shell, SWT.NULL);
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

	public VariableTreeComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		properties = new ArrayList<String>();
		foundPosition = 0;
		foundTreeItem = new ArrayList<TreeItem>();
		count = 0;
		
//		Set<String> properties = Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().getResults().keySet();
		this.properties.addAll(properties);
		
//		Activator.getSimulationCenter_NonInteractive().addPlot(this);
		initGUI();
	}
	
	
	public VariableTreeComposite(org.eclipse.swt.widgets.Composite parent, int style,  List<String> properties, String simulationModelName) {
		super(parent, style);
//		properties = new HashSet<String>();
		foundPosition = 0;
		foundTreeItem = new ArrayList<TreeItem>();
		count = 0;
		this.setSimulationModelName(simulationModelName);
		
//		Set<String> properties = Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().getResults().keySet();
//		this.properties.addAll(properties);
		this.properties = properties;
		
//		Activator.getSimulationCenter_NonInteractive().addPlot(this);
		initGUI();
	}

	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout();
			thisLayout.makeColumnsEqualWidth = true;
			this.setLayout(thisLayout);
			{
				mainComposite = new Composite(this, SWT.NONE);
				GridLayout mainCompositeLayout = new GridLayout();
				mainCompositeLayout.makeColumnsEqualWidth = true;
				GridData mainCompositeLData = new GridData();
				mainCompositeLData.horizontalAlignment = GridData.FILL;
				mainCompositeLData.verticalAlignment = GridData.FILL;
				mainCompositeLData.grabExcessHorizontalSpace = true;
				mainCompositeLData.grabExcessVerticalSpace = true;
				mainComposite.setLayoutData(mainCompositeLData);
				mainComposite.setLayout(mainCompositeLayout);
				{
					compositeModelTree = new Composite(mainComposite, SWT.NONE);
//					GridLayout modelTreeCompositeLayout = new GridLayout();
//					modelTreeCompositeLayout.makeColumnsEqualWidth = true;
//					GridData modelTreeCompositeLData = new GridData();
//					compositeModelTree.setLayoutData(modelTreeCompositeLData);
//					compositeModelTree.setLayout(modelTreeCompositeLayout);
					GridLayout compositeModelTreeLayout = new GridLayout();
					compositeModelTreeLayout.makeColumnsEqualWidth = true;
					GridData compositeModelTreeLData = new GridData();
					compositeModelTreeLData.horizontalAlignment = GridData.FILL;
					compositeModelTreeLData.grabExcessVerticalSpace = true;
					compositeModelTreeLData.verticalAlignment = GridData.FILL;
					compositeModelTreeLData.grabExcessHorizontalSpace = true;
					compositeModelTree.setLayoutData(compositeModelTreeLData);
					compositeModelTree.setLayout(compositeModelTreeLayout);
					{

						GridData treeModelLData = new GridData();
						treeModelLData.horizontalAlignment = GridData.FILL;
						treeModelLData.grabExcessHorizontalSpace = true;
						treeModelLData.verticalAlignment = GridData.FILL;
						treeModelLData.grabExcessVerticalSpace = true;
						treeModel = new Tree(compositeModelTree, SWT.BORDER | SWT.CHECK);
						treeModel.setLayoutData(treeModelLData);
						buildTreeModel(properties);
						treeModel.addListener(SWT.Selection, new Listener() {

							public void handleEvent(Event event) {

								if (event.detail == SWT.CHECK) {

									TreeItem item = (TreeItem) event.item;
									
									/*
									 * Only plot leaf variables 
									 * in order to avoid that the entire tree is plotted. 
									 */
									if (item.getItems() == null || item.getItems().length == 0) {

										boolean checked = item.getChecked();
										checkItems(item, checked);
										checkPath(item.getParentItem(), checked, false);
									}
									else {
										/*
										 * Feedback to user: Instead of opening a dialog 
										 * mark it grey in order to indicate that it was not plotted.
										 */
										item.setGrayed(true);
									}
								}
							}
					    });
					}
				}
				{
					compositeSearch = new Composite(mainComposite, SWT.NONE);
					GridLayout compositeSearchLayout = new GridLayout();
					compositeSearchLayout.makeColumnsEqualWidth = true;
					compositeSearchLayout.numColumns = 5;
					GridData compositeSearchLData = new GridData();
					compositeSearchLData.horizontalAlignment = GridData.FILL;
					compositeSearchLData.verticalAlignment = GridData.END;
					compositeSearch.setLayoutData(compositeSearchLData);
					compositeSearch.setLayout(compositeSearchLayout);
					{
						labelsearch = new Label(compositeSearch, SWT.NONE);
						labelsearch.setText("Find:");
						labelsearch.setToolTipText("Enter a string to be searched for in all full qualified names. This search is not case sensitive.");
					}
					{
						GridData textFindLData = new GridData();
						textFindLData.horizontalSpan = 3;
						textFindLData.horizontalAlignment = GridData.FILL;
						textFind = new Text(compositeSearch, SWT.BORDER);
						textFind.setLayoutData(textFindLData);
						textFind.addModifyListener(new ModifyListener() {

							public void modifyText(ModifyEvent evt) {
								foundTreeItem.clear();
								foundPosition = 0;
								buttonSearch.setText("Search");
							}
						});
					}
					{
						buttonSearch = new Button(compositeSearch, SWT.PUSH | SWT.CENTER);
						buttonSearch.setText("Search");
						buttonSearch.setFocus();
						buttonSearch.addSelectionListener(new SelectionAdapter() {
							
							@Override
							public void widgetSelected(SelectionEvent e) {
								super.widgetSelected(e);
								if(foundTreeItem.isEmpty() && !textFind.getText().isEmpty()){ //Is a new search
									search(textFind.getText());
									buttonSearch.setText("Next");
//									System.out.println(foundTreeItem);
									showNextSearchResult();
								} else{//Go to next found element
									showNextSearchResult();
								}
							}
						});
					}
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
		if (foundTreeItem.size() > 0) {
			treeModel.select(foundTreeItem.get(foundPosition));
			treeModel.showSelection();
			if(foundTreeItem.size()-1<=foundPosition){
				foundPosition = 0; //Search from beginning
			}else{
				foundPosition++;
			}
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
	 * @param properties the properties
	 */
	private void buildTreeModel(List<String> properties) {
		if(properties != null && !properties.isEmpty()){
			TreeItem rootItem = new TreeItem(treeModel, SWT.NULL);
			rootItem.setText(getSimulationModelName());
			
			for (String fullQualifiedName : properties) {
				
				if(fullQualifiedName.startsWith("der(")) {
					String derName = fullQualifiedName.replace("der(", "");
					derName = derName.replace(")", "");
					createModelElementForDerivative(rootItem, derName, fullQualifiedName);
				}
				else if (fullQualifiedName.startsWith("time")) {
					// don't create a node for time. this is pointless.
				}
				else {
					createModelElement(rootItem, fullQualifiedName, fullQualifiedName);
				}
			}
		}
	}
	
	/**
	 * Recursive method to build the treeModel with all nodes and child elements.
	 *
	 * @param itemParent parant tree item
	 * @param restOfFullQualifiedName the rest of full qualified name
	 * @param fullQualifiedName the full qualified name
	 */
	private void createModelElement(TreeItem itemParent,
			String restOfFullQualifiedName, String fullQualifiedName) {

		Scanner sc = new Scanner(restOfFullQualifiedName);
		sc.useDelimiter("\\.");
		if (sc.hasNext()) {
			String name = sc.next();

			if (itemParent.getItems().length != 0) {
				boolean hasChild = false;
				for (TreeItem childItem : itemParent.getItems()) {
					if (childItem.getText().equals(name)) {
						if (sc.hasNextLine())
							createModelElement(childItem, sc.nextLine(),
									fullQualifiedName);
						hasChild = true;
						break;
					}
				}
				if (!hasChild) {
					TreeItem newChildItem = new TreeItem(itemParent, SWT.NULL);
					newChildItem.setText(name);
					newChildItem.setChecked(false);
					if (sc.hasNextLine()) {
						createModelElement(newChildItem, sc.nextLine(),
								fullQualifiedName);
					} else {
						newChildItem.setData(fullQualifiedName);
						
						newChildItem.setText(newChildItem.getText());
					}
				}
			} else {
				TreeItem newChildItem = new TreeItem(itemParent, SWT.NULL);
				newChildItem.setText(name);
				newChildItem.setChecked(false);
				if (sc.hasNextLine()) {
					createModelElement(newChildItem, sc.nextLine(),
							fullQualifiedName);
				} else {
					newChildItem.setData(fullQualifiedName);

						newChildItem.setText(newChildItem.getText());
					
				}
			}
		}
	}

	/**
	 * Recursive method to build the treeModel with all nodes and child elements for a derivative property. The prefix der(...) will be added to the name
	 *
	 * @param itemParent parant tree item
	 * @param restOfFullQualifiedName the rest of full qualified name
	 * @param fullQualifiedName the full qualified name
	 */
	private void createModelElementForDerivative(TreeItem itemParent,
			String restOfFullQualifiedName, String fullQualifiedName) {

		Scanner sc = new Scanner(restOfFullQualifiedName);
		sc.useDelimiter("\\.");
		if (sc.hasNext()) {
			String name = sc.next();

			if (itemParent.getItems().length != 0) {
				boolean hasChild = false;
				for (TreeItem childItem : itemParent.getItems()) {
					if (childItem.getText().equals(name)) {
						if (sc.hasNextLine())
							createModelElementForDerivative(childItem, sc.nextLine(),
									fullQualifiedName);
						hasChild = true;
						break;
					}
				}
				if (!hasChild) {
					TreeItem newChildItem = new TreeItem(itemParent, SWT.NULL);
					newChildItem.setChecked(false);
					if (sc.hasNextLine()) {
						newChildItem.setText(name);
						createModelElementForDerivative(newChildItem, sc.nextLine(),
								fullQualifiedName);
					} else {
						newChildItem.setText("der(" + name + ")");
						newChildItem.setData(fullQualifiedName);
					}
				}
			} else {
				TreeItem newChildItem = new TreeItem(itemParent, SWT.NULL);
				newChildItem.setChecked(false);
				if (sc.hasNextLine()) {
					newChildItem.setText(name);
					createModelElementForDerivative(newChildItem, sc.nextLine(),
							fullQualifiedName);
				} else {
					newChildItem.setText("der(" + name + ")");
					newChildItem.setData(fullQualifiedName);
				}
			}
		}
	}

	
	/**
	 * Count child items.
	 *
	 * @param ti the ti
	 * @return the int
	 */
	@SuppressWarnings("unused")
	private int countChildItems(TreeItem ti){
		count = 0;
		
		getCountRek(ti);
		
		return count;
	}
	
	/**
	 * Gets the count rek.
	 *
	 * @param item the item
	 * @return the count rek
	 */
	private void getCountRek(TreeItem item){
		if(item.getItemCount() != 0){ //is node
			for(TreeItem child : item.getItems()){
				getCountRek(child);
			}
		} else{ //is leaf
			count++;
		}
	}
	
	/**
	 * Check path.
	 *
	 * @param item the item
	 * @param checked the checked
	 * @param grayed the grayed
	 */
	private void checkPath(TreeItem item, boolean checked, boolean grayed) {

		if (item == null)
			return;

		if (grayed) {

			checked = true;

		} else {

			int index = 0;

			TreeItem[] items = item.getItems();

			while (index < items.length) {

				TreeItem child = items[index];

				if (child.getGrayed()

				|| checked != child.getChecked()) {

					checked = grayed = true;

					break;

				}

				index++;

			}

		}

		item.setChecked(checked);
		if(item.getData()!=null){
			if(checked){
				if (getChart() != null) {
					chart.addValues((String)item.getData());
				}
//				Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().propertySelected((String)item.getData());
			}else{
				// TODO: test it! what happens if we deselect?
				if (getChart() != null) {
					chart.removeValues((String)item.getData());
				}
//				Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().propertyUnselection((String)item.getData());
			}
		}

		item.setGrayed(grayed);
		checkPath(item.getParentItem(), checked, grayed);
	}



	/**
	 * Check items.
	 *
	 * @param item the item
	 * @param checked the checked
	 */
	private void checkItems(TreeItem item, boolean checked) {

		item.setGrayed(false);

		item.setChecked(checked);
		
		if(item.getData()!=null){
			if(checked){
				if (getChart() != null) {
					chart.addValues((String)item.getData());
				}
//				Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().propertySelected((String)item.getData());
			}else{
				// TODO: test it!
				if (getChart() != null) {
					chart.removeValues((String)item.getData());
				}
//				Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().propertyUnselection((String)item.getData());
			}
		}

		TreeItem[] items = item.getItems();

		for (int i = 0; i < items.length; i++) {

			checkItems(items[i], checked);

		}

	}

	/**
	 * New simulation.
	 */
	public void newSimulation(){
		foundPosition = 0;
		foundTreeItem = new ArrayList<TreeItem>();
		count = 0;
		textFind.setText("");
//		Set<String> properties = Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().getResults().keySet();
		this.properties.addAll(properties);
		treeModel.removeAll();
		buildTreeModel(properties);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if(((String)arg1).equals("newsimulation")){
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					newSimulation();
				}
			});
		}
	}

	public PlotComposite getChart() {
		return chart;
	}

//	public void setChart(JFreeChartPlotComposite chart) {
//		this.chart = chart;
//	}
	
	public void setChart(PlotComposite chart) {
		this.chart = chart;
	}
	
	public Tree getTreeModel() {
		return treeModel;
	}

	public void setTreeModel(Tree treeModel) {
		this.treeModel = treeModel;
	}

}
