package org.openmodelica.modelicaml.helper.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.diagram.common.editparts.IUMLEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.wb.swt.SWTResourceManager;
import org.openmodelica.modelicaml.helper.dialogs.InstantiateRequirementsDialog;


public class InstantiatedRequirementsView extends ViewPart implements ISelectionListener {
	
	public InstantiatedRequirementsView() {
		super();
	}
	private final String reqStereotypeQname = "ModelicaML::ModelicaRequirementConstructs::Requirement";
	private ExpandBar expandBar;
	private Composite shell;
	private Class selectedClass;
	private List<Property> reqList = new ArrayList<Property>();
	private List<ExpandItem> reqGraphicalItemsList = new ArrayList<ExpandItem>();
	private List<Group> reqGraphicalGroupItemsList = new ArrayList<Group>();
	
	
	@Override
	public void createPartControl(Composite parent) {
		shell = parent;
		
		expandBar = new ExpandBar(shell, SWT.V_SCROLL);
		expandBar.setSpacing(2);
		GridData gd_expandBar = new GridData(SWT.LEFT, SWT.TOP, true, true);
//		gd_expandBar.heightHint = 626;
//		gd_expandBar.widthHint = 1400;
		expandBar.setLayoutData(gd_expandBar);
		getViewSite().getPage().addSelectionListener(this);
	}

	@Override
	public void setFocus() {
		shell.setFocus();
	}


	/**
	 * @see ISelectionListener#selectionChanged(IWorkbenchPart, ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		
		if (!shell.isDisposed() && selection != null && selection instanceof IStructuredSelection) {
			Object first = ((IStructuredSelection) selection).getFirstElement();
			
			EObject selectedElement = null;

			if (first instanceof ModelElementItem) {
				selectedElement = ((ModelElementItem)first).getEObject();
			}
			else if (first instanceof IUMLEditPart) {
				selectedElement = ((IUMLEditPart)first).getUMLElement();
			}
			
			if (selectedElement instanceof Class ) {
				selectedClass = (Class) selectedElement;
				reqList = getInstatiatedRequirementsList(selectedClass);
				drawContents();
			}
		}
	}
	
	private void clearItemsList(){
		for (ExpandItem item : reqGraphicalItemsList) {
			item.dispose();
		}
		for (Group group : reqGraphicalGroupItemsList) {
			group.dispose();
		}
	}
	
	private void drawContents(){

		clearItemsList();// clear the graphical view.

//		Label lblSelectRequirementsTo = new Label(messageComposite, SWT.NONE);
//		lblSelectRequirementsTo.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
//		lblSelectRequirementsTo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
//		lblSelectRequirementsTo.setBounds(0, 0, 238, 18);
//
//		if (reqList != null && reqList.size() > 0) {
//			// NEW Label for the owning class name
//			lblSelectRequirementsTo.setText("The following requirements are instantiated in:");
//			CLabel label_1 = new CLabel(messageComposite, SWT.NONE);
//			label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//			label_1.setTopMargin(0);
//			label_1.setBottomMargin(0);
//			
//			// Set the name of containing class 
//			label_1.setText(selectedClass.getName());	
//			label_1.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
//			label_1.setBounds(244, 0, 450, 18);
//		}
//		else {
//			lblSelectRequirementsTo.setText("No requirements are instantiated in " + selectedClass.getName() +  ".");
//		}
		
		
		for (Property property : reqList) {
				
				Class req = (Class) property.getType();
				
				Stereotype stereotype = req.getAppliedStereotype(reqStereotypeQname);
				if (stereotype != null) {
//					String title = "" + property.getName() + " (" + req.getName() + ")";
					String title = "" + property.getName();
					Object id = "" + req.getValue(stereotype, "id");
					Object text = "" + req.getValue(stereotype, "text");;
					String qName = "" + req.getQualifiedName();

					ExpandItem reqExpandItem = new ExpandItem(expandBar, SWT.NONE);
					reqGraphicalItemsList.add(reqExpandItem);
					reqExpandItem.setExpanded(false);
					
					// mark requirement that is already instantiated 
					reqExpandItem.setImage(SWTResourceManager.getImage(InstantiateRequirementsDialog.class, "/icons/exists_already.png"));

					// Set Req. Title
//					reqExpandItem.setText(id + ": " + title);
					reqExpandItem.setText(title);
					
					Group grpId = new Group(expandBar, SWT.NONE);
					reqGraphicalGroupItemsList.add(grpId);
					grpId.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
					grpId.setToolTipText(qName);
					
					// Set Req. ID
					//grpId.setText("id: " + id.toString() + "   " + qName);
					grpId.setText(id.toString() + " : " + qName);
					reqExpandItem.setControl(grpId);
					
					//StyledText reqText = new StyledText(grpId, SWT.FULL_SELECTION | SWT.WRAP | SWT.V_SCROLL);
					StyledText reqText = new StyledText(grpId, SWT.FULL_SELECTION | SWT.WRAP);
					reqText.setIndent(1);
					reqText.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
					reqText.setEditable(false);
					
					// Set Req. Text
					reqText.setText(text.toString());
					//reqText.setBounds(29, 36, 656, 98);
//					reqText.setBounds(5, 20, expandBar.getShell().getBounds().width - 70, SWT.VERTICAL - 229);
					reqText.setBounds(5, 20, 600, 80);
					reqText.setToolTipText(qName);
					
					grpId.setTabList(new Control[]{reqText});
					reqExpandItem.setHeight(reqExpandItem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);	
				}
			
		}
		
		
		
		
	}
	
	
	private List<Property> getInstatiatedRequirementsList(Class owningClass){
		if (owningClass != null) {
			List<Property> requirements = new ArrayList<Property>();
			for (Property property : owningClass.getAllAttributes()) { // TODO: here use the ModelicaMLAST instead to find requirements
				Type pType = property.getType();
				
				if ( pType != null && (pType.getAppliedStereotype(reqStereotypeQname) != null)) {
					requirements.add(property);
				}
			}
			return requirements;
		}
		return null;
	}
	


}

