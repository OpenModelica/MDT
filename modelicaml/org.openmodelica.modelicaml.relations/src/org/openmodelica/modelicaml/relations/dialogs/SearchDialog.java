package org.openmodelica.modelicaml.relations.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.relations.model.TreeObject;
import org.openmodelica.modelicaml.relations.model.TreeParent;

public class SearchDialog extends Dialog {
	private Tree tree;
	private Button btnSearch;
	private Text txtKeyWords;
	
	private TreeViewer viewer;
	private TreeParent root;
	
	private HashSet<String> keywords = new HashSet<String>();
	private Button btnOr;
	private Button btnAnd;
	
	private boolean searchInName = true;
	private boolean searchInComments = false;
	private boolean searchInTypeName = true;
	private boolean searchInTypeComments = false;
	private Button btnSearchInNames;
	private Button btnSearchInComments;

	public SearchDialog(Shell parentShell, TreeViewer viewer, TreeParent root) {
		
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM);
		
		this.root = root;
		this.viewer = viewer;
	}

	
	private HashSet<TreeObject> getAllTreeItems(TreeParent treeParent){
		HashSet<TreeObject> allTreeItems = new HashSet<TreeObject>();
		allTreeItems.add(treeParent);
		
		TreeObject[] children = treeParent.getChildren();
		for (int i = 0; i < children.length; i++) {
			allTreeItems.add(children[i]);
			if (children[i] instanceof TreeParent) {
				allTreeItems.addAll(getAllTreeItems((TreeParent)children[i]));
			}
		}
		
		return allTreeItems;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations","icons/find.png"));
		newShell.setText("Search in " + root.getName());		
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Group grpKeywords = new Group(container, SWT.NONE);
		grpKeywords.setText("Keywords search in names and comments");
		grpKeywords.setLayout(new GridLayout(3, false));
		GridData gd_grpKeywords = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_grpKeywords.heightHint = 101;
		grpKeywords.setLayoutData(gd_grpKeywords);
		
		txtKeyWords = new Text(grpKeywords, SWT.BORDER);
		txtKeyWords.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (txtKeyWords.getText().trim().length() > 1) {
					btnSearch.setEnabled(true);
				}
				else {
					btnSearch.setEnabled(false);
				}
			}
		});
		txtKeyWords.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		txtKeyWords.setFocus();
		
		btnSearch = new Button(grpKeywords, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] keyWordsTextSplitted = txtKeyWords.getText().trim().split(" ");
				
				keywords.clear();
				for (String string : keyWordsTextSplitted) {
					keywords.add(string);
				}

				if (keywords.size() > 0) {
					// update the tree
					tree.removeAll();
					
					List<TreeObject> foundItems = getSortedByName(getMatchedItems()); 
					if (foundItems != null) {
						for (TreeObject treeObject : foundItems) {
							
							TreeItem treeItem = new TreeItem(tree, SWT.NONE);
							treeItem.setData(treeObject);
							treeItem.setText(treeObject.getName());
							
							Element umlElement = treeObject.getUmlElement();
							if (umlElement instanceof Model) {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/Model.gif"));
							}
							else if ( umlElement instanceof Dependency ) {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/Dependency.gif"));
							}
							else if ( umlElement instanceof Generalization) {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/Generalization.gif"));
							}
							else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null ) {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/requirement.gif"));
							}
							else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) != null ) {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/calculationModel.gif"));
							}
							else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Model) != null ) {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/model.gif"));
							}
							else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Block) != null ) {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/block.gif"));
							}
							else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Record) != null ) {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/record.gif"));
							}
							else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Connector) != null ) {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/connector.gif"));
							}
							else if ( umlElement.getAppliedStereotype(Constants.stereotypeQName_Function) != null ) {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/FunctionBehavior.gif"));
							}
							else if ( umlElement instanceof Property) {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/Property.gif"));
							}
							else  if ( umlElement instanceof Class) {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/Class.gif"));
							}
							else  if ( umlElement instanceof PrimitiveType) {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/PrimitiveType.gif"));
							}
							else {
								treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/unknown_obj.gif"));
							}
						}
					}
				}
			}
		});
		btnSearch.setText("Search");
		
		btnOr = new Button(grpKeywords, SWT.RADIO);
		btnOr.setSelection(true);
		btnOr.setText("OR");
		
		btnAnd = new Button(grpKeywords, SWT.RADIO);
		btnAnd.setText("AND");
		new Label(grpKeywords, SWT.NONE);
		
		btnSearchInNames = new Button(grpKeywords, SWT.CHECK);
		btnSearchInNames.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnSearchInNames.getSelection()) {
					searchInName = true;
					searchInTypeName = true;
				}
				else {
					searchInName = false;
					searchInTypeName = false;
				}
			}
		});
		btnSearchInNames.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnSearchInNames.setText("Search in names");
		btnSearchInNames.setSelection(searchInName);
		
		
		new Label(grpKeywords, SWT.NONE);
		
		btnSearchInComments = new Button(grpKeywords, SWT.CHECK);
		btnSearchInComments.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnSearchInComments.setText("Search in comments");
		btnSearchInComments.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnSearchInComments.getSelection()) {
					searchInComments = true;
					searchInTypeComments = true;
				}
				else {
					searchInComments = false;
					searchInTypeComments = false;
				}
			}
		});
		btnSearchInComments.setSelection(searchInComments);
		
		
		new Label(grpKeywords, SWT.NONE);
		new Label(grpKeywords, SWT.NONE);
		new Label(grpKeywords, SWT.NONE);
		new Label(grpKeywords, SWT.NONE);
		
		Group grpFoundItems = new Group(container, SWT.NONE);
		grpFoundItems.setText("Found items");
		grpFoundItems.setLayout(new GridLayout(1, false));
		grpFoundItems.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		tree = new Tree(grpFoundItems, SWT.BORDER | SWT.V_SCROLL);
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				TreeItem[] selectedItems = tree.getSelection();
				for (TreeItem selectedItem : selectedItems) {
//					HashSet<Object> objects = treeBuilder.findTreeItems( ((TreeObject) selectedItem.getData()).getDotPath(), root, new HashSet<Object>());
					List<Object> newSelectionItems = new ArrayList<Object>();
					newSelectionItems.add(selectedItem.getData());
					viewer.setSelection(new StructuredSelection(newSelectionItems), true);
				}
			}
		});
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		return area;
	}

	
	
	public static List<TreeObject> getSortedByName(HashSet<TreeObject> set){
		if (set == null) { return null; }

		List<TreeObject> listSorted = new ArrayList<TreeObject>(set);
		Comparator<TreeObject> c = new Comparator<TreeObject>() {
			@Override
			public int compare(TreeObject arg0, TreeObject arg1) {
				return arg0.getName().compareToIgnoreCase(arg1.getName());
			}
		};
		Collections.sort(listSorted, c);
		
		return listSorted;
	}
	
	
	private HashSet<TreeObject> getMatchedItems(){
		HashSet<TreeObject> foundItems = new HashSet<TreeObject>();
		
		for (TreeObject treeObject: getAllTreeItems(root)) {
			String string = "";
			// search in tree object name, uml element qualified name, type qualified name, all comments 
			
			if (isSearchInName()) {
				string += treeObject.getName();
			}
			
			if (treeObject.getUmlElement() != null ){
				
				if (isSearchInName()) {
					string += ModelicaMLServices.getQualifiedName(treeObject.getUmlElement());
				}
				if (isSearchInComments()) {
					string += treeObject.getUmlElement().getOwnedComments().toString();							
				}

				if (treeObject.getUmlElement() instanceof Property) {
					Type type = ((Property)treeObject.getUmlElement()).getType();
					if (isSearchInTypeName()) {
						string +=  ModelicaMLServices.getQualifiedName(type);
					}
					if (isSearchInTypeComments()) {
						string += type.getOwnedComments().toString();
					}
				}
			}
			
			// OR search 
			if (btnOr.getSelection()) {
				for (String keyword : keywords) {
					// collect matches
					if (string.toLowerCase().contains(keyword.toLowerCase())) {
						foundItems.add(treeObject);
					}
				}
			}
			// AND search
			else if (btnAnd.getSelection()) {
				boolean matchedAlways = true;
//				boolean matched = false;
				for (String keyword : keywords) {
					// collect matches
					if (string.toLowerCase().contains(keyword.toLowerCase())) {
//						matched = true;
					}
					else {
//						matched = false;
						matchedAlways = false;
					}
				}
				if (matchedAlways) {
					foundItems.add(treeObject);
				}
			} 	
		}
		
		return foundItems;
	}
	
	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
//		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
//				true);
//		createButton(parent, IDialogConstants.CANCEL_ID,
//				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(494, 413);
	}


	public boolean isSearchInComments() {
		return searchInComments;
	}


	public void setSearchInComments(boolean searchInComments) {
		this.searchInComments = searchInComments;
	}


	public boolean isSearchInName() {
		return searchInName;
	}


	public void setSearchInName(boolean searchInName) {
		this.searchInName = searchInName;
	}


	public boolean isSearchInTypeName() {
		return searchInTypeName;
	}


	public void setSearchInTypeName(boolean searchInTypeName) {
		this.searchInTypeName = searchInTypeName;
	}


	public boolean isSearchInTypeComments() {
		return searchInTypeComments;
	}


	public void setSearchInTypeComments(boolean searchInTypeComments) {
		this.searchInTypeComments = searchInTypeComments;
	}
}

