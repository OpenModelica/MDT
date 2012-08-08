package org.openmodelica.modelicaml.view.valuebindings.dialogs;

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
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeParent;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;

public class SearchDialog extends Dialog {
	private Tree tree;
	private Button btnSearch;
	private Text txtKeyWords;
	
	private TreeViewer viewer;
	private TreeParent root;
	
	private boolean isShowClientPerspective = false;
	private boolean isShowProviderPerspective = false;
	
	private HashSet<String> keywords = new HashSet<String>();
	private Button btnOr;
	private Button btnAnd;

	public SearchDialog(Shell parentShell, TreeViewer viewer, TreeParent root, boolean isShowClientPerspective, boolean isShowProviderPerspective) {
		
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM);
		
		this.root = root;
		this.viewer = viewer;
		this.isShowClientPerspective = isShowClientPerspective;
		this.isShowProviderPerspective = isShowProviderPerspective;
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
		newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings","icons/find.png"));
		newShell.setText("Search");
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
		gd_grpKeywords.heightHint = 64;
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
							
							if ( (treeObject.isValueClient() || treeObject.isValueProvider()) && treeObject.getParent() != null && treeObject.getParent().getParent() != null) {

								TreeParent mediator = treeObject.getParent().getParent();
								
								if (mediator.isValueMediator()) {
									TreeItem mediatorItem = new TreeItem(tree, SWT.NONE);
									mediatorItem.setData(mediator);
									mediatorItem.setText(mediator.getName());
									mediatorItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "icons/valueMediator.png"));
									
									// create client or provider
									TreeItem treeItem = new TreeItem(mediatorItem, SWT.NONE);
									treeItem.setData(treeObject);
									treeItem.setText(treeObject.getName());
									if (treeObject.isValueClient()) {
										treeItem.setText("Client " + treeItem.getText());
									}
									else if (treeObject.isValueProvider()) {
										treeItem.setText("Provider " + treeItem.getText());
									}
									treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "icons/Property.gif"));	
								}
							}
							else {

								TreeItem treeItem = new TreeItem(tree, SWT.NONE);
								treeItem.setData(treeObject);
								treeItem.setText(treeObject.getName());
								if (treeObject.isValueClient() || treeObject.isValueProvider() ) {
									treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "icons/Property.gif"));							
								}
								else if (treeObject.isValueMediator()) {
									treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "icons/valueMediator.png"));
								}
								else if (treeObject.isValueMediatorContainer()) {
									treeItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "icons/valueMediatorsContainer.png"));
								}
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
					HashSet<Object> objects = TreeUtls.findTreeItems((TreeObject) selectedItem.getData(), root, new HashSet<Object>());
					List<Object> newSelectionItems = new ArrayList<Object>();
					newSelectionItems.addAll(objects);
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
			if (treeObject.isValueClient() || treeObject.isValueProvider() || treeObject.isValueMediator() || treeObject.isValueMediatorContainer()) {
				
				if ((isShowClientPerspective) && !treeObject.isReadOnly() ) {
					// skip
				}
				else if ((isShowProviderPerspective) && !treeObject.isReadOnly() ) {
					// skip
				}
				else if (!(isShowClientPerspective | isShowProviderPerspective) && treeObject.isReadOnly()){
					// skip
				}
				else {
					String string = "";
					// search in tree object name, uml element qualified name, type qualified name, all comments 
					
					string += treeObject.getName();
					if (treeObject.getUmlElement() != null ){
						string += ModelicaMLServices.getQualifiedName(treeObject.getUmlElement());
						string += treeObject.getUmlElement().getOwnedComments().toString();
						if (treeObject.getUmlElement() instanceof Property) {
							Type type = ((Property)treeObject.getUmlElement()).getType();
							string +=  ModelicaMLServices.getQualifiedName(type);
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
						boolean matched = false;
						for (String keyword : keywords) {
							// collect matches
							if (string.toLowerCase().contains(keyword.toLowerCase())) {
								matched = true;
							}
							else {
								matched = false;
								matchedAlways = false;
							}
						}
						if (matched && matchedAlways) {
							foundItems.add(treeObject);
						}
					} 	
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
		return new Point(494, 373);
	}
}

