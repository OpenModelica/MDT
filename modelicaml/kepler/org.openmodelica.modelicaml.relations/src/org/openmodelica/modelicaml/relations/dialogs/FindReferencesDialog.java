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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.relations.dialogs;

import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.services.EditorServices;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;

public class FindReferencesDialog extends Dialog {
	private Tree tree;
	private HashSet<Element> foundElements;
	private NamedElement elementToSearchFor;
	private HashSet<TreeItem> allTreeItems = new HashSet<TreeItem>();


	public FindReferencesDialog(Shell parentShell, HashSet<Element> foundElements) {
		
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM);
		this.foundElements = foundElements;

	}

	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.modelexplorer","icons/search_references.gif"));
		newShell.setText("References for "+ModelicaMLServices.getName(getElementToSearchFor())+" (" + foundElements.size() + " found)");			
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
		
		tree = new Tree(grpFoundItems, SWT.BORDER | SWT.V_SCROLL);
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
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
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

		return area;
	}

	public void createTree(Tree tree){
		
		allTreeItems.clear();
		
//		List<Element> elementsSorted = ModelicaMLServices.getSortedByEClassName(foundElements); // is far too slow
		List<Element> elementsSorted = ModelicaMLServices.getSortedByName(foundElements);
		if (elementsSorted != null) {
			for (Element element : elementsSorted) {
				
				TreeItem treeItem = new TreeItem(tree, SWT.NONE);
				treeItem.setData(element);
				allTreeItems.add(treeItem);
				
				if (element instanceof Generalization) {
					treeItem.setText(ModelicaMLServices.getQualifiedName(element.getOwner()));
				}
				else {
					treeItem.setText(ModelicaMLServices.getName(element) + "          (in "+ModelicaMLServices.getQualifiedName(element.getOwner())+")");	
				}

				// set image
				treeItem.setImage(ModelicaMLServices.getImage(element));
				
			}
		}
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


	public NamedElement getElementToSearchFor() {
		return elementToSearchFor;
	}


	public void setElementToSearchFor(NamedElement elementToSearchFor) {
		this.elementToSearchFor = elementToSearchFor;
	}
}

