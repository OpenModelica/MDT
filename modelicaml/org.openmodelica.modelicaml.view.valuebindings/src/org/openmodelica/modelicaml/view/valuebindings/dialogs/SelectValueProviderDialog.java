package org.openmodelica.modelicaml.view.valuebindings.dialogs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeUtls;
import org.openmodelica.modelicaml.view.valuebindings.helpers.ValueBindingsDataCollector;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;
import org.eclipse.swt.widgets.Label;

public class SelectValueProviderDialog extends Dialog {

	private String title;
	private List<TreeObject> elementsForSelection;
	
	private Element mediatorElement;
	private String toolTipTextValueMediator;
	
	private String groupTitle;
	private String messageText;
	
	private ValueBindingsDataCollector dataCollection;
	
	private TreeObject selectedElement = null;
	
	public SelectValueProviderDialog(Shell parentShell, 
			String title, 
			Element mediatorElement,
			String toolTipTextValueMediator,
			String groupTitle,
			String messageText,
			List<TreeObject> elementsForSelection,
			ValueBindingsDataCollector dataCollection
			) {
	
		super(parentShell);
		
		this.title = title;
		this.mediatorElement = mediatorElement;
		this.toolTipTextValueMediator = toolTipTextValueMediator;
		this.groupTitle = groupTitle;
		this.messageText = messageText;
		this.elementsForSelection = elementsForSelection;
		this.dataCollection = dataCollection;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText(this.title);
		super.configureShell(newShell);
	}

	
	@Override
	protected Control createDialogArea(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
//		Link sourceElementInfo = new Link(composite, 0);
//		Label valueMediatorInfo = new Label(composite, 0);
		Button valueMediatorInfo = new Button(composite, SWT.RADIO);
		valueMediatorInfo.setSelection(true);
		valueMediatorInfo.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "/icons/valueMediator.png"));

		
		// name
		String toolTipTextMediator = "Q.Name: " + ((NamedElement)mediatorElement).getQualifiedName();
		
		// operation
		String mediatorOperation = DeriveValueBindingCodeUtls.getOperationSpecification(mediatorElement, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
		if (mediatorOperation == null) {
			toolTipTextMediator = toolTipTextMediator + "\r\nOperation: NONE.";
		}
		else if (DeriveValueBindingCodeUtls.isValidMediatorMultipleItemsScript(mediatorOperation)) {
			toolTipTextMediator = toolTipTextMediator + "\r\nOperation: MULTIPLE providers operation.";
		}
		else if (DeriveValueBindingCodeUtls.isValidMediatorSingleItemsScript(mediatorOperation)) {
			toolTipTextMediator = toolTipTextMediator + "\r\nOperation: SINGLE provider operation.";
		}

		// type
		Type type = ((TypedElement)mediatorElement).getType();
		if (type != null ) {
			String typeString = "\r\nType: " + type.getName().replaceFirst("Modelica", "");
			toolTipTextMediator = toolTipTextMediator + typeString;
		}
		
		// variability 
		Stereotype stereotype = mediatorElement.getAppliedStereotype(Constants.stereotypeQName_ValueMediator);
		if (stereotype != null) {
			Object o = mediatorElement.getValue(stereotype, Constants.propertyName_variability);
			if (o instanceof EnumerationLiteral) {
				toolTipTextMediator = toolTipTextMediator + "\r\nVariability: " + ((EnumerationLiteral)o).getName();
			}
		}
		HashMap<Element, HashSet<Element>> map = dataCollection.getMediatorToProviders();
		if (map != null) {// of there are mediators with providers at all
			HashSet<Element> providers = map.get(mediatorElement); // get the providers for this mediator
			if (providers != null) { 
				if (providers.size() > 0 ) {
					toolTipTextMediator = toolTipTextMediator + "\r\n\r\nProviders available in the instantiated class: ";
				}
				for (Element provider : providers) {
					HashSet<TreeObject> treeObjects = dataCollection.getInstantiationTreeObjects(provider);
					for (TreeObject treeObject : treeObjects) {
						toolTipTextMediator = toolTipTextMediator + "\r\n          - " + treeObject.getDotPath();
					}
				}
			}
		}
		valueMediatorInfo.setToolTipText(toolTipTextMediator);
		
		valueMediatorInfo.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
		valueMediatorInfo.setText( "Value Mediator: " + ((NamedElement)mediatorElement).getName());
		
		Link message = new Link(composite, SWT.NONE);
		message.setText(messageText);
		
		Group group = new Group(composite, SWT.NONE);
		group.setLayout(new RowLayout(SWT.VERTICAL));
		GridData gd_group = new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1);
		gd_group.widthHint = 474;
		group.setLayoutData(gd_group);
		group.setText(groupTitle);
		
		final Button btnAddtoPreferredProviders = new Button(composite, SWT.CHECK);
		btnAddtoPreferredProviders.setToolTipText("This action will remember the selection decision by storing the qualified names pair of the client and the selected provider.");
		btnAddtoPreferredProviders.setText("remember this selction (i.e., add to preferred providers)");
		
		btnAddtoPreferredProviders.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnAddtoPreferredProviders.getSelection()) {
					setAddToPrefferedProviders(true);
				}
				else {
					setAddToPrefferedProviders(false);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		for (TreeObject provider : elementsForSelection) {
			NamedElement providerElement = (NamedElement) provider.getUmlElement();
			
			if (providerElement instanceof TypedElement) { // TODO: what if it is a State?
				
				final Button rb = new Button(group, SWT.RADIO);
				rb.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "/icons/addValueProviders.png"));
				
				// name
				String toolTipTextSelectionItem = "Provider Path: " + provider.getDotPath()
						+ "\nElement: " + providerElement.getQualifiedName();
				
				// operation
				EList<Dependency> depList = TreeUtls.getMediatorDependency((NamedElement)mediatorElement, providerElement, Constants.stereotypeQName_ObtainsValueFrom);
				String providerOperation = DeriveValueBindingCodeUtls.getOperationSpecification(depList.get(0), Constants.stereotypeQName_ObtainsValueFrom, Constants.propertyName_operation);
				if (providerOperation == null) {
					toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\nOperation: NONE.";
				}
				else {
					toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\nOperation: " + providerOperation;
				}

				// type
				if (providerElement instanceof TypedElement) {
					Type providerType = ((TypedElement)providerElement).getType();
					if (providerType != null ) {
						String providerTypeString = "\r\nType: " + providerType.getName().replaceFirst("Modelica", "");
						toolTipTextSelectionItem = toolTipTextSelectionItem + providerTypeString;
					}
				}
				
				// variability 
				Stereotype varStereotype = providerElement.getAppliedStereotype(Constants.stereotypeQName_Variable);
				if (varStereotype != null) {
					Object o = providerElement.getValue(varStereotype, Constants.propertyName_variability);
					if (o instanceof EnumerationLiteral) {
						toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\nVariability: " + ((EnumerationLiteral)o).getName();
					}	
				}
				
				toolTipTextSelectionItem = toolTipTextSelectionItem + "\r\n";
				
				rb.setToolTipText(toolTipTextSelectionItem);
				rb.setText(provider.getDotPath());
				rb.setData(provider);
				rb.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						setSelectedElement((TreeObject) rb.getData());
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						setSelectedElement((TreeObject) rb.getData());
					}
				});
			}
		}
		
		return parent;
	}

	
	private boolean addToPrefferedProviders = false;
	
	public void setSelectedElement(TreeObject selectedElement) {
		this.selectedElement = selectedElement;
	}

	public TreeObject getSelectedElement() {
		return selectedElement;
	}

	public void setAddToPrefferedProviders(boolean addToPrefferedProviders) {
		this.addToPrefferedProviders = addToPrefferedProviders;
	}

	public boolean isAddToPrefferedProviders() {
		return addToPrefferedProviders;
	}

}
