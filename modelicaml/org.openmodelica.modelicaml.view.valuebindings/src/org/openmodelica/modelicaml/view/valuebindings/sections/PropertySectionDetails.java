package org.openmodelica.modelicaml.view.valuebindings.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.papyrus.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.modelexplorer.ModelExplorerView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;

public class PropertySectionDetails extends AbstractPropertySection {

    private TreeObject item;

	private Text textName;
	private Link textType;
	private Link textOwner;

	private Label lblName;
	private Label lblType;

	private Label lblOwner;

    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        
        parent.setLayout(new GridLayout(1, false));
        Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        
		lblName = new Label(composite, SWT.NONE);
		lblName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblName.setBounds(10, 13, 49, 13);
		lblName.setText("Name:");
		
		textName = new Text(composite, SWT.BORDER);
		textName.setEditable(false);
		textName.setBounds(65, 10, 579, 19);
		
		lblType = new Label(composite, SWT.NONE);
		lblType.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblType.setBounds(10, 62, 49, 13);
		lblType.setText("Type:");
		
		textType = new Link(composite, SWT.NONE);
		textType.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textType.setBounds(65, 62, 579, 13);
		textType.setText("<a>New Link</a>");
		
		lblOwner = new Label(composite, SWT.NONE);
		lblOwner.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblOwner.setBounds(10, 43, 49, 13);
		lblOwner.setText("Owner:");
		
		textOwner = new Link(composite, SWT.NONE);
		textOwner.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textOwner.setBounds(65, 43, 579, 13);
		textOwner.setText("<a>New Link</a>");
 
    }

    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        Assert.isTrue(selection instanceof IStructuredSelection);
        Object input = ((IStructuredSelection) selection).getFirstElement();
        Assert.isTrue(input instanceof TreeObject);
        this.item = (TreeObject) input;
    }

    public void refresh() {

    	boolean typeTextVisibility = item.isValueClient() || item.isValueMediator() || item.isValueProvider();
    	boolean ownerTextVisibility = !item.isValueClientsNode() && !item.isValueProvidersNode() && item.getUmlElement() != null &&  item.getUmlElement().getOwner() instanceof NamedElement;
		
    	lblType.setVisible(typeTextVisibility);
		textType.setVisible(typeTextVisibility);

		lblOwner.setVisible(ownerTextVisibility);    		
		textOwner.setVisible(ownerTextVisibility);

    	textName.setText(item.getName());
    	
    	// type
    	String type = "";
    	final Element element = item.getUmlElement();
    	if (element instanceof Property) {
			if ( ((Property)element).getType() != null) {
				if (((Property)element).getType() instanceof PrimitiveType) {
					type = ((Property)element).getType().getName();
				}
				else {
					type = "<a>" + ((Property)element).getType().getName() + "</a>";
					textType.setToolTipText( ((Property)element).getType().getQualifiedName());
					textType.addListener(SWT.Selection, new Listener() {
					      public void handleEvent(Event event) {
					    	  EObject object = ((Property)element).getType();
					    	  locate(object); // locate in Papyrus
					      }
					    });
				}
				type = ((Property)element).getType().getName();
			}
		}
    	textType.setText(type);
    	
    	// owner 
    	String owner = "";
    	if (item.getUmlElement() != null &&  item.getUmlElement().getOwner() instanceof NamedElement) {
//    		owner = ((NamedElement)item.getUmlElement().getOwner()).getQualifiedName();
    		owner = "<a>" + ((NamedElement)item.getUmlElement().getOwner()).getName() + "</a>";
    		textOwner.setToolTipText(((NamedElement)item.getUmlElement().getOwner()).getQualifiedName());
    		textOwner.addListener(SWT.Selection, new Listener() {
			      public void handleEvent(Event event) {
			    	  EObject object = (NamedElement)item.getUmlElement().getOwner();
			    	  locate(object); // locate in Papyrus
			      }
			    });
		}
    	textOwner.setText(owner);
    }

    
    
    private void locate(Object object){
		if (object instanceof EObject) {
			
			IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.eclipse.papyrus.modelexplorer.modelexplorer");

			ModelExplorerPageBookView modelExplorerPageBookView = null;
			if (view instanceof ModelExplorerPageBookView) {
				modelExplorerPageBookView = (ModelExplorerPageBookView)view;
			}
			CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
			List<Object> items = new ArrayList<Object>();
			items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
			modelExplorerView.setSelection(new StructuredSelection(items), true);
      	}
	}

}
