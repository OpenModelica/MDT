package org.openmodelica.modelicaml.view.valuebindings.sections;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;

public class PropertySection_Operation extends AbstractPropertySection {
	private Link textOperation;
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

		textOperation = new Link(composite, SWT.NONE);
		textOperation.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textOperation.setBounds(10, 10, 700, 200);
	}

	@Override
	public void refresh() {
		super.refresh();

		// set text.
		textOperation.setText(getOperationString());
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
	
	
	
	private String getOperationString(){
	  Stereotype stereotype = getStereotype();
	  if (stereotype != null) {
		  Element element = item.getUmlElement();
		  Object object = element.getValue(stereotype, Constants.propertyName_operation);
		  if (object instanceof String) {
			return (String)object;
		}
	  }
	  return "";
	}
	
	private Stereotype getStereotype(){
		Stereotype stereotype = null;
		Element element = item.getUmlElement();
		if (element != null) {
			stereotype = element.getAppliedStereotype(Constants.stereotypeQName_ValueClient);
			if (stereotype != null) { return stereotype;}

			stereotype = element.getAppliedStereotype(Constants.stereotypeQName_ValueProvider);
			if (stereotype != null) { return stereotype;}

			stereotype = element.getAppliedStereotype(Constants.stereotypeQName_ValueMediator);
			if (stereotype != null) { return stereotype;}
		}
		return stereotype;
	}
}
