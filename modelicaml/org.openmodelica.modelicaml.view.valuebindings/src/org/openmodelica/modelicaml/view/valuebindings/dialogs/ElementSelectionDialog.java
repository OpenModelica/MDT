package org.openmodelica.modelicaml.view.valuebindings.dialogs;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.core.utils.EditorUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeParent;

public class ElementSelectionDialog extends Dialog {
	private Shell sShell = null; 
	private Button bttOK = null;
	private Button bttCancel = null;

	private int value = 0;
	
	private Label message;
	private Group valueMediatorGroup;
	private Label vmIcon;
	private Label vmName;
	private Group selectedElementGroup;
	private Label seIcon;
	private Label seName;

	// Constants 
	private static final Image errorIcon = SWTResourceManager.getImage(ElementSelectionDialog.class, "/org/eclipse/jface/dialogs/images/message_error.gif");
	private static final Image propertyIcon = SWTResourceManager.getImage(ElementSelectionDialog.class, "/icons/Property.gif");
	private static final Image valueMediatorIcon = SWTResourceManager.getImage(ElementSelectionDialog.class, "/icons/valueMediator.png");

	private EObject selectedElement = null;
	
	private TreeParent valueMediatorTreeItem = null; 
	private TreeViewer viewer = null;

//	private List<String> listOfAllowedMetaClassNames = new ArrayList<String>();
	private String title = "";
	private Image image = null;
	private String mode = null;
	
	public ElementSelectionDialog(	Shell parent, 
									Image image,
									String title, 
									String message, 
									List<String> listOfAllowedMetaClassNames, 
									TreeParent valueMediatorTreeItem, 
									TreeViewer viewer, 
									String mode 
									) {
		super(parent);

//		this.listOfAllowedMetaClassNames = listOfAllowedMetaClassNames;
		this.valueMediatorTreeItem = valueMediatorTreeItem;
		this.viewer = viewer;
		this.mode = mode;
		
		this.title = title;
		this.image = image;
		
		// create shell
		createSShell();
		
		// set the message text
		this.message.setText(message);
		
		// set the value mediator icon and text. 
		String typeString = "Not Defined!";
		String ownerString = "";
		if (valueMediatorTreeItem.getUmlElement().getOwner() instanceof NamedElement) {
			ownerString = "owner(" + ((NamedElement)valueMediatorTreeItem.getUmlElement().getOwner()).getName() + ")";
		}
		if (valueMediatorTreeItem.getUmlElement() != null 
				&& valueMediatorTreeItem.getUmlElement() instanceof Property  
				&& ((Property)valueMediatorTreeItem.getUmlElement()).getType() != null ) {
			typeString = ((Property)valueMediatorTreeItem.getUmlElement()).getType().getName().replaceFirst("Modelica", "");
		}
		vmName.setText(valueMediatorTreeItem.getName() + "   -   type(" + typeString + ")" + ", " + ownerString);
		vmIcon.setImage(valueMediatorIcon);
		
		// set default values for element selection
		seName.setText("Click on a model element ...");
		seIcon.setImage(errorIcon); // nothing is selected yet.

		if (mode.equals("addValueProvider")) {
			selectedElementGroup.setText("Value Provider"); 
		}
		else if (mode.equals("addValueClient")) {
			selectedElementGroup.setText("Value Client");
		}
		else {
			selectedElementGroup.setText("Selected Element");
		}
		
		// add the selection listener
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);

	}

	 private ISelectionListener selectionListener = new ISelectionListener() {
	        public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
	        if (sourcepart != ElementSelectionDialog.this &&
	            selection instanceof IStructuredSelection) {
	
	        	if (getCurrentSelections() != null && getCurrentSelections().size() > 0 ) {
						selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
				}
	        	
				if (	selectedElement instanceof Property
						&& ((Property)selectedElement).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) == null
						&& selectedElement != valueMediatorTreeItem.getUmlElement()
						) {

					String typeString = "Not Defined!";
					String ownerString = "";
					if (((Element) selectedElement).getOwner() instanceof NamedElement) {
						NamedElement owner = (NamedElement) ( (NamedElement) selectedElement).getOwner();
						ownerString = "owner(" + owner.getName() + ")";
					}
					if (selectedElement instanceof Property  
							&& ((Property)selectedElement).getType() != null ) {
						typeString = ((Property)selectedElement).getType().getName().replaceFirst("Modelica", "");
					}
					seName.setText( ((Property)selectedElement).getName() + "   -   type(" + typeString + ")" + ", " + ownerString);
					seIcon.setImage(propertyIcon);

					// enable the OK button
					bttOK.setEnabled(true);
					
				} else {
					// reset  
					seName.setText("");
					seIcon.setImage(errorIcon);
					
					if (selectedElement == valueMediatorTreeItem.getUmlElement()) { // link to its self
						String text = "It is not allowed to link an element with itself.";
						seName.setText(text);
					}
					else if ( selectedElement instanceof Property
							&& ((Property)selectedElement).getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null){
						String text = "It is not allowed to link a Value Mediator to another Value Mediator.";
						seName.setText(text);
					}
					else if (selectedElement != null) {
						String text = selectedElement.eClass().getName() + " is not allowed!";
						seName.setText(text);
					}
					else {
						String text = "Not a valid selection";
						seName.setText(text);
					}

					// disable the OK button
					bttOK.setEnabled(false);
				}
	        }
	    }
	};
	    
	
	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		// SWT.APPLICATION_MODAL, SWT.SYSTEM_MODAL do not work, 
		// SWT.PRIMARY_MODAL, SWT.MODELESS work
		sShell = new Shell(getParentShell(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP | SWT.SHELL_TRIM);
		
		sShell.setText(this.title);
		sShell.setImage(this.image); 
		
//		sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		sShell.setSize(545, 241);
		
		sShell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);
			}
		});
		
		Shell shell = sShell;
		
		message = new Label(shell, SWT.NONE);
		message.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		message.setBounds(20, 10, 507, 17);
		message.setText("Click on the element to be associated.");

		Composite messageBg = new Composite(shell, SWT.NONE);
		messageBg.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		messageBg.setBounds(0, 0, 537, 34);
		
		valueMediatorGroup = new Group(shell, SWT.NONE);
		valueMediatorGroup.setText("Value Mediator");
		valueMediatorGroup.setBounds(10, 40, 517, 55);
		
		vmName = new Label(valueMediatorGroup, SWT.NONE);
		vmName.setBounds(32, 23, 475, 13);
		
		vmIcon = new Label(valueMediatorGroup, SWT.NONE);
		vmIcon.setBounds(10, 23, 16, 16);
		
		selectedElementGroup = new Group(shell, SWT.NONE);
		selectedElementGroup.setText("Value Client");
		selectedElementGroup.setBounds(10, 101, 517, 55);
		
		seName = new Label(selectedElementGroup, SWT.NONE);
		seName.setBounds(32, 23, 475, 13);
		
		seIcon = new Label(selectedElementGroup, SWT.NONE);
		seIcon.setBounds(10, 23, 16, 16);
		
		bttOK = new Button(sShell, SWT.NONE);
		bttOK.setBounds(385, 172, 68, 23);
		bttOK.setText("OK");
		bttOK.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				setValue(SWT.OK);
				
				if (selectedElement instanceof NamedElement) {
					
					addValueToStereotypeProperty_List();
					
					TreeObject item = new TreeParent( ((NamedElement)selectedElement).getName());
					item.setUmlElement((NamedElement)selectedElement);
					
					TreeObject[] children = valueMediatorTreeItem.getChildren();
					for (int i = 0; i < children.length; i++) {
						if (mode.equals("addValueClient") && children[i].isValueClientsNode() ) {
							
//							TreeParent titleNode = new TreeParent( Constants.valueClientsTitleName );
//							selectedTreeParent.addChild(titleNode);
//							titleNode.addChild(item);
							
							((TreeParent) children[i]).addChild(item);
							
//							viewer.add(selectedTreeParent, titleNode);
//							viewer.add(titleNode, item);							
							viewer.add(children[i], item);
						}
						else if (mode.equals("addValueProvider") && children[i].isValueProvidersNode()) {
//							TreeParent titleNode = new TreeParent( Constants.valueProvidersTitleName );
//							selectedTreeParent.addChild(titleNode);
//							titleNode.addChild(item);
							
							((TreeParent) children[i]).addChild(item);
							
//							viewer.add(selectedTreeParent, titleNode);
//							viewer.add(titleNode, item);
							viewer.add(children[i], item);
						}
					}
					viewer.refresh();
				}
				sShell.dispose();
			}
		});
		
		bttCancel = new Button(sShell, SWT.NONE);
		bttCancel.setBounds(459, 172, 68, 23);
		bttCancel.setText("Cancel");
		bttCancel.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				setValue(SWT.CANCEL);
				sShell.dispose();
			}
		});
		
	}
	
	private Stereotype getStereotype(){
		String stereotypeQName = "";
		Stereotype stereotype = null;
		
		if (this.mode.equals("addValueClient")) {
			stereotypeQName = Constants.stereotypeQName_ValueClient;
		}
		else if (this.mode.equals("addValueProvider")) {
			stereotypeQName = Constants.stereotypeQName_ValueProvider;
		}
		
		if (this.selectedElement instanceof NamedElement) {
			// get stereotype
			stereotype = ((NamedElement)this.selectedElement).getAppliedStereotype(stereotypeQName);
			
			// apply stereotype if not yet applied
			if (stereotype == null) {
				final Stereotype s = ((NamedElement)this.selectedElement).getApplicableStereotype(stereotypeQName);
				if (s != null) {
					
					//########## storing start
					TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
					CompoundCommand cc = new CompoundCommand();
					Command command = new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							((NamedElement)selectedElement).applyStereotype(s);
						}
					};
					cc.append(command);
					editingDomain.getCommandStack().execute(cc);
					
					
					// get stereotype
					stereotype = ((NamedElement)this.selectedElement).getAppliedStereotype(stereotypeQName); 
				}
			}
		}
		return stereotype;
	}

	private String getStereotypePropertyName(){
		String stereotypeProperty_name = null;
		if (this.mode.equals("addValueClient")) {
			stereotypeProperty_name = Constants.stereotypeQName_ValueClient_obtainsValueFrom;
		}
		else if (this.mode.equals("addValueProvider")) {
			stereotypeProperty_name = Constants.stereotypeQName_ValueProvider_providesValueFor;
		}
		return stereotypeProperty_name;
	}
	
	@SuppressWarnings("rawtypes")
	private boolean containsObject(EList list, EObject eObject){
		if (list instanceof EList) {
			for (Object object : (EList)list) {
				if (object instanceof EObject) {
//					if (((EObject)object).eCrossReferences().get(0) == eObject) {
					if (UMLUtil.getBaseElement((EObject)object) == eObject) {
						return true;
					}
				}	
			}
		}
		return false;
	}
	
	private void addValueToStereotypeProperty_List(){
		
		if (this.selectedElement instanceof NamedElement) {
			// get stereotype
			final Stereotype stereotype = getStereotype();
			final String stereotypeProperty_name = getStereotypePropertyName();
			if ( stereotype != null && stereotypeProperty_name != null) {
				final Object list = ((NamedElement)this.selectedElement).getValue(stereotype, stereotypeProperty_name);
				
				if (list instanceof EList) {
//					########## storing start
					TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
					CompoundCommand cc = new CompoundCommand("Add value mediator reference");
					Command command = new RecordingCommand(editingDomain) {
						@SuppressWarnings({ "unchecked", "rawtypes" })
						@Override
						protected void doExecute() {
							if (!containsObject((EList) list, valueMediatorTreeItem.getUmlElement())) {
								// get the value mediator stereotype
								final Stereotype valueMediatorStereotype = valueMediatorTreeItem.getUmlElement().getAppliedStereotype(Constants.stereotypeQName_ValueMediator);
								
								// Important: use the getStereotypeApplication to get an EObject! 
								DynamicEObjectImpl eObject =(DynamicEObjectImpl)valueMediatorTreeItem.getUmlElement().getStereotypeApplication(valueMediatorStereotype);
								
								// add value to the list
								((EList)list).add(eObject);
							}
						}
					};
					cc.append(command);
					editingDomain.getCommandStack().execute(cc);
				}
			}
		}
	}
	
	
	
	public int open() {
		this.sShell.open();
		return getValue();
	}

	
	@SuppressWarnings("unchecked")
	private List<Object> getCurrentSelections() {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			return structuredSelection.toList();
		}
		return null;
	}
	
	
	protected EObject adaptSelectedElement( Object selection) {
		EObject eObject = null;
		if(selection != null) {
			
			if (selection instanceof org.openmodelica.modelicaml.common.instantiation.TreeParent) { // this is an object from components tree view plugin
				return ((org.openmodelica.modelicaml.common.instantiation.TreeParent)selection).getProperty();
			}
			
			if(selection instanceof IAdaptable) {
				selection = ((IAdaptable)selection).getAdapter(EObject.class);
			}
			Object businessObject = BusinessModelResolver.getInstance().getBusinessModel(selection);
			if(businessObject instanceof EObject) {
				eObject = (EObject)businessObject;
			}
		}
		return eObject;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public EObject getSelectedElement() {
		return selectedElement;
	}

	public void setSelectedElement(EObject selectedElement) {
		this.selectedElement = selectedElement;
	}
}
