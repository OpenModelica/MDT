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
package org.openmodelica.modelicaml.relations.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.EditorServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.relations.model.TreeObject;

public class SelectInvalidRelationsToDeleteDialog extends Dialog {
	private Tree tree;
	private HashSet<Element> invalidRelations;
	private Button btnDeleteAll;
	private HashSet<TreeItem> allTreeItems = new HashSet<TreeItem>();
	private final ImageDescriptor errorImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/error_ovr.gif");


	public SelectInvalidRelationsToDeleteDialog(Shell parentShell, HashSet<Element> invalidRelations) {
		
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM);
		this.invalidRelations = invalidRelations;

	}

	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations","icons/find.png"));
		newShell.setText("Invalid Relations Search (" + invalidRelations.size() + " found)");			
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Group grpFoundItems = new Group(container, SWT.NONE);
		grpFoundItems.setText("Found items");
		grpFoundItems.setLayout(new GridLayout(1, false));
		grpFoundItems.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		tree = new Tree(grpFoundItems, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL);
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnDeleteAll.setEnabled(true);
			}
		});
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				TreeItem[] selectedItems = tree.getSelection();
				for (TreeItem selectedItem : selectedItems) {
					EditorServices.locateInModelExplorer(selectedItem.getData(), false);
				}
			}
		});
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		createTree(tree);
		
		
		Composite composite = new Composite(grpFoundItems, SWT.NONE);
		composite.setLayout(new GridLayout(5, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelectAll = new Button(composite, SWT.NONE);
		btnSelectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (TreeItem treeItem : allTreeItems) {
					treeItem.setChecked(true);
				}
			}
		});
		btnSelectAll.setText("Select all");
		
		final Button btnDeselectAll = new Button(composite, SWT.NONE);
		btnDeselectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (TreeItem treeItem : allTreeItems) {
					treeItem.setChecked(false);
				}
			}
		});
		btnDeselectAll.setText("Deselect all");
		
		Button btnLocate = new Button(composite, SWT.NONE);
		btnLocate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] selectedItems = tree.getSelection();
				for (TreeItem selectedItem : selectedItems) {
					EditorServices.locateInModelExplorer(selectedItem.getData(), false);
				}
			}
		});
		btnLocate.setText("Locate");
		btnLocate.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations","icons/editor/ModelExplorer.png"));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite_1.heightHint = 9;
		composite_1.setLayoutData(gd_composite_1);
		
		btnDeleteAll = new Button(composite, SWT.NONE);
		btnDeleteAll.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnDeleteAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				TreeItem[] selectedItems = tree.getSelection();

				HashSet<Element> deletedRelations = new HashSet<Element>();
				for (TreeItem treeItem : allTreeItems) {
					if (treeItem.getChecked()) {
						Element relation = (Element) treeItem.getData();
						
						// do not delete in installed Libraries
						if (relation.getModel() != null && relation.getModel().getAppliedStereotype(Constants.stereotypeQName_InstalledLibrary) != null) {
							// skip
						}
						else {
							// delete element
							deletedRelations.add(deleteRelation(relation));

							invalidRelations.remove(relation);
						}
					}
				}

				// update the tree
				tree.removeAll();
				createTree(tree);
				
				// disable delete button until the next selection
				btnDeleteAll.setEnabled(false);
				
				String message = "";
				for (Element deletedRelation : deletedRelations) {
					if (deletedRelation instanceof Generalization) {
						message += "  - Extends relation: " + deletedRelation.toString();
					}
					else if (deletedRelation instanceof Dependency) {
//						message += "Dependency: " + ModelicaMLServices.getQualifiedName(deletedRelation) + " in (" + ModelicaMLServices.getQualifiedName(deletedRelation.getOwner()) + ")";
						message += "  - Dependency: '" + ModelicaMLServices.getQualifiedName(deletedRelation) + "'";

					}
					else {
//						message += "Element: " + ModelicaMLServices.getQualifiedName(deletedRelation)+ " in (" + ModelicaMLServices.getQualifiedName(deletedRelation.getOwner()) + ")";
						message += "  - Element: '" + ModelicaMLServices.getQualifiedName(deletedRelation) + "'";
					}
				}
				
				DialogMessage resultsDialog = new DialogMessage(getShell(), "Delete Relations Results", "The follwoing relations were deleted:", message, false);
				resultsDialog.open();
				
			}
		});
		btnDeleteAll.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/etool16/delete.gif"));
		btnDeleteAll.setText("Delete selected");

		return area;
	}

	
	public Image decorateError(Image image) {
		return new DecorationOverlayIcon(image, errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
	
	public void createTree(Tree tree){
		
		allTreeItems.clear();
		
		List<Element> relations = ModelicaMLServices.getSortedByName(invalidRelations); 
//		List<Element> relations = ModelicaMLServices.getSortedByEClassName(invalidRelations); // is far too slow! 
		if (relations != null) {
			for (Element relation : relations) {
				
				TreeItem treeItem = new TreeItem(tree, SWT.CHECK);
				treeItem.setData(relation);
				allTreeItems.add(treeItem);
				
				if (relation instanceof Generalization) {
					treeItem.setText("in: " + ModelicaMLServices.getQualifiedName(relation.getOwner()));
				}
				else {
					treeItem.setText(ModelicaMLServices.getName(relation) + "          (in "+ModelicaMLServices.getQualifiedName(relation.getOwner())+")");	
				}
				if ( relation instanceof Dependency ) {
					treeItem.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/Dependency.gif")));
				}
				else if ( relation instanceof Generalization) {
					treeItem.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.relations", "icons/Generalization.gif")));
				}
				
				if (relation.getModel() != null && relation.getModel().getAppliedStereotype(Constants.stereotypeQName_InstalledLibrary) != null) {
					treeItem.setGrayed(true);
					treeItem.setText("In Library: " + treeItem.getText());
				}
			}
		}
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
	
	
	private Element deleteRelation(final Element relation){

		TransactionalEditingDomain editingDomain = EditorServices.getPapyrusEditingDomain(); 

		CompoundCommand cc = new CompoundCommand("Create a ModelicaML Proxy Class");
		Command command = new RecordingCommand(editingDomain) {
			
			Element deletedElement = null;
			
			@Override
			public Collection<?> getResult() {
				List<Element> collection = new ArrayList<Element>();
				if (deletedElement != null) {
					collection.add(deletedElement);
				}
				return collection;
			};
			
			@Override
			protected void doExecute() {
				deletedElement = relation; 
				relation.destroy();
			}
		};
		
		cc.append(command);
		// Execute command
		editingDomain.getCommandStack().execute(cc);

		Collection<?> result = command.getResult();
		for (Object object : result) {
			if (object instanceof Element) {
				return (Element)object;
			}
		}
		return null;
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
}

