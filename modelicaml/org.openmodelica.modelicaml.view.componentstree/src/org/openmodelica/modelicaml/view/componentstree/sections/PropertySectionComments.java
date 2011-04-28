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
import org.openmodelica.modelicaml.view.componentstree.utls.SWTResourceManager;

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
//			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.eclipse.papyrus.modelexplorer.modelexplorer");
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
