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
package org.openmodelica.modelicaml.view.componentstree.sections;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;

public class PropertySectionComments extends AbstractPropertySection {
	private Link textComments;
	private TreeObject item = null;

	
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        Assert.isTrue(selection instanceof IStructuredSelection);
        Object input = ((IStructuredSelection) selection).getFirstElement();
        Assert.isTrue(input instanceof TreeObject);
        this.item = (TreeObject) input;
	}

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {

		super.createControls(parent, aTabbedPropertySheetPage);
		
		parent.setLayout(new GridLayout(1, false));
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		textComments = new Link(composite, SWT.NONE);
		textComments.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//		textComments.setText("");
		textComments.setBounds(10, 10, 700, 200);
//		textComments.setLayoutData(new GridData(0, 0, true, true, 1, 1));

	}

	@Override
	public void refresh() {
		super.refresh();

		// set text.
		textComments.setText(getCommentsString());
	}
	
	
//	private void locate(Object object){
//		if (object instanceof EObject) {
//			
//			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_PAPYRUS_MODELEXPLORER);
//
//			ModelExplorerPageBookView modelExplorerPageBookView = null;
//			if (view instanceof ModelExplorerPageBookView) {
//				modelExplorerPageBookView = (ModelExplorerPageBookView)view;
//			}
//			CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
//			List<Object> items = new ArrayList<Object>();
//			items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
//			modelExplorerView.setSelection(new StructuredSelection(items), true);
//      	}
//	}
	
	
	
	private String getCommentsString(){
	  // Comments
	  String commentString = "";
	  EList<Comment> comments = item.getComments();
	  if (comments.size() > 0 ) {
	  	//commentString = commentString + "Comments of '" + treeObject.getProperty().getName()+"':\n"; 
	  	for (Comment comment : comments) {
			commentString = commentString + comment.getBody() + " ";
		}
	  }
	      
		if (!commentString.equals("")) {
			commentString = commentString + "\n\n";
		}
		
		if (item.getProperty() != null ) {
			if (item.getProperty().getType() != null) {
				 EList<Comment> commentsOfComponentType = item.getProperty().getType().getOwnedComments();
				 if (commentsOfComponentType.size() > 0 ) {
					 commentString = commentString + "Comments from '" + ((Classifier)item.getProperty().getType()).getName()+"' (component type):\n"; 
					 for (Comment comment : commentsOfComponentType) {
						commentString = commentString + comment.getBody() + " ";
					}
				}
			}
		}
		if (!commentString.equals("")) {
	        return commentString;
		}
		
	return "";
	}
}
