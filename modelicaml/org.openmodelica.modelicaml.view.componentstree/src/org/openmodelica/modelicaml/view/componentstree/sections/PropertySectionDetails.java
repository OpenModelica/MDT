package org.openmodelica.modelicaml.view.componentstree.sections;

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
import org.eclipse.swt.custom.StyledText;
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
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.instantiation.ModificationManager;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.services.UmlServices;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;

public class PropertySectionDetails extends AbstractPropertySection {
	private Label lblPath;
	private Text textPath;
	
	private Label lblType;

	private Label lblCausality;

	private Label lblDeclaration;
	private StyledText textDeclaration;
	private StyledText textModification;
	private Label lblCausalityText;
	private Link linkType;
	private Link linkOverriden;
	
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
		
		
		
		lblPath = new Label(composite, SWT.NONE);
		lblPath.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPath.setBounds(10, 13, 67, 13);
		lblPath.setText("Path:");
		
		textPath = new Text(composite, SWT.BORDER);
		textPath.setEditable(false);
		textPath.setBounds(83, 10, 582, 19);
		
		lblType = new Label(composite, SWT.NONE);
		lblType.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblType.setBounds(10, 40, 67, 13);
		lblType.setText("Type:");
		
		linkType = new Link(composite, SWT.NONE);
		linkType.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		linkType.setBounds(83, 40, 496, 13);
		linkType.setText("<a>Type name </a> is redeclared, former type was <a>Type name </a>");
		
		lblCausality = new Label(composite, SWT.NONE);
		lblCausality.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblCausality.setBounds(10, 59, 67, 13);
		lblCausality.setText("Causality:");
		
		lblCausalityText = new Label(composite, SWT.NONE);
		lblCausalityText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblCausalityText.setBounds(83, 59, 582, 13);
		lblCausalityText.setText("input");
		
		lblDeclaration = new Label(composite, SWT.NONE);
		lblDeclaration.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDeclaration.setBounds(10, 80, 67, 13);
		lblDeclaration.setText("Declaration:");
		
		textDeclaration = new StyledText(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		textDeclaration.setText("");
		textDeclaration.setEditable(false);
		textDeclaration.setBounds(83, 80, 582, 56);
		
		textModification = new StyledText(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		textModification.setEditable(false);
		textModification.setBounds(83, 161, 582, 56);
		
		linkOverriden = new Link(composite, SWT.NONE);
		linkOverriden.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		linkOverriden.setBounds(83, 142, 582, 13);
		linkOverriden.setText("Overriden by class extension modification in <a>sadflkj asdf sdf </a>");

	}

	@Override
	public void refresh() {
		super.refresh();
		
		// declare visibility
		boolean pathVisibility = true;
		boolean typeVisibility = this.item.getUmlElement() instanceof Property;
		boolean causalityVisibility = this.item.isInput() || this.item.isOutput();
		boolean declarationVisibility = this.item.getUmlElement() instanceof Property && ( this.item.getDeclaration() != null || this.item.getFinalModificationRightHand() != null);
		boolean modificationVisibility = this.item.getUmlElement() instanceof Property && this.item.getFinalModificationRightHand() != null;
		
		// set visibility
		lblPath.setVisible(pathVisibility);
		textPath.setVisible(pathVisibility);
		
		lblType.setVisible(typeVisibility);
		linkType.setVisible(typeVisibility);
		
		lblCausality.setVisible(causalityVisibility);
		lblCausalityText.setVisible(causalityVisibility);
		
		lblDeclaration.setVisible(declarationVisibility);
		textDeclaration.setVisible(declarationVisibility);
		
		linkOverriden.setVisible(modificationVisibility);
		textModification.setVisible(modificationVisibility);
		
		
		// set text.
		textPath.setText(getPathText());
		
		linkType.setText(getTypeText());
		linkType.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		    	  EObject object = null;
		    	  
		    	  if (event.text.trim().equals("final")) {
		    		  Type cFinalType = (Classifier) item.getComponentType();
		    		  object = cFinalType;
		    	  }
		    	  else if (event.text.trim().equals("original")) {
		    		  Type cOriginalType = (Classifier) item.getProperty().getType();
		    		  object = cOriginalType;
		    	  }
		    	  
		    	  locate(object); // locate in Papyrus
		      }
		    });
		
		lblCausalityText.setText(getCausalityText());
		
		textDeclaration.setText(getDeclarationText());
		
		textModification.setText(getModificationText());
		
		linkOverriden.setText(getModificationTitle() + " <a>" + getModificationSourceText() + "</a>");
		linkOverriden.setData(item.getFinalModificationSource());
		linkOverriden.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		    	  EObject object = item.getFinalModificationSource();
		    	  locate(object); // locate in Papyrus
		      }
		    });
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
	
	private String getModificationSourceText(){
		if (item.getFirstLevelComponent() != null && ModificationManager.isInModModListOfComponent(item.getFirstLevelComponent(), item.getDotPathWithoutFirstLevelComponent())) {
			return item.getFirstLevelComponent().getName();
		}
		
		NamedElement modSourceElement = item.getFinalModificationSource();
		if (modSourceElement != null ) {
//			modSource = modSourceElement.eClass().getName() + " '" + modSourceElement.getQualifiedName() + "' ";
			return "'" + modSourceElement.getQualifiedName() + "' ";
		}

//		NamedElement modSourceElement = item.getFinalModificationSource();
//		if (modSourceElement != null ) {
//	//		modSource = modSourceElement.eClass().getName() + " '" + modSourceElement.getQualifiedName() + "' ";
//			if ( item.isInherited() ) { // the modification is stored in  
//				return "'" + item.getSelectedClass().getQualifiedName() + "' ";	
//			}
//			else { // then it is a component modification
//				return "'" + modSourceElement.getName() + "' ";
//			}
//		}
		return "";
	}
	
	private String getModificationTitle(){
//		String modSource = "";
//	//	if (ModificationManager.isInModModListOfComponent(treeObject.getFirstLevelComponent(), treeObject.getDotPathWithoutFirstLevelComponent())) {
//	//		modSource = " in '" + treeObject.getFirstLevelComponent().getName() + "' ";
//	//	}
//		NamedElement modSourceElement = item.getFinalModificationSource();
//		if (modSourceElement != null ) {
//	//		modSource = modSourceElement.eClass().getName() + " '" + modSourceElement.getQualifiedName() + "' ";
//			modSource = "'" + modSourceElement.getQualifiedName() + "' ";
//		}
		
		String modDescription = "";
		if (item.getFinalModificationDescription() != null) {
			modDescription = item.getFinalModificationDescription();
//			return "Overriden " + modDescription  + " in " + modSource + ": ";
			return "Overriden " + modDescription  + " in";
		}
		
//		label_modification.setToolTipText(modSource);

		return "";
	}
	
	private String getPathText(){
		if (this.item.getDotPath().trim().equals("") ) {
			lblPath.setText("Name: ");
			return this.item.getName();
		}
		lblPath.setText("Path: ");
	    return this.item.getDotPath();
	}
	
	
	private String getModificationText(){
      if (item.getFinalModificationRightHand() != null) {
			return " = " + item.getFinalModificationRightHand();
		}
		return "";
	}
	
	private String getDeclarationText(){
		if (item.isLeaf() && item.getProperty() != null ) {
	        String declarationString = "";
			if (item.getProperty() != null) {
				Stereotype stereotype = item.getProperty().getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::Variable");
				if (stereotype != null) {
					Object declarationEquationOrAssignment = UmlServices.getStereotypeValue((Element)item.getProperty(), stereotype.getName(), "declarationEquationOrAssignment");
					if (declarationEquationOrAssignment instanceof String) {
						declarationString = " " + declarationEquationOrAssignment.toString().trim();
						return declarationString;
					}						
				}
			}
		}
		return "";
	}
	
	private String getCausalityText(){
      if (item.isInput()) {
    	  return "input";				
      }
      else if (item.isOutput()) {
    	  return "output";				
      }
		return "";
	}

	
	private String getTypeText(){
		if (item.getUmlElement() instanceof Property) {
			Type type = ((Property)item.getUmlElement()).getType();
			if (type != null) {
				String cTypeString = "Not defined";
				
				Type cOriginalType = (Classifier) item.getProperty().getType();
				String cOriginalTypeString = "";
				if ( !(cOriginalType instanceof PrimitiveType) ) {
					cOriginalTypeString = "<a href=\"original\">" + cOriginalType.getName() + "</a>";
				}
				else {
					cOriginalTypeString = cOriginalType.getName();
				}
				
				Type cFinalType = (Classifier) item.getComponentType();
				String cFinalTypeString = "";
				if ( !(cFinalType instanceof PrimitiveType) ) {
					cFinalTypeString = "<a href=\"final\">" + cFinalType.getName() + "</a>";
					linkType.setToolTipText(cFinalType.getQualifiedName());
				}
				else {
					cFinalTypeString = cFinalType.getName();
				}
	            
	            if (item.hasRedeclaredType() && cFinalType!= null && cOriginalType!= null) {
					cTypeString = "redeclared to " + cFinalTypeString + " (original type was " + cOriginalTypeString +  ")";  
				}
	            else if (cFinalType != null) {
	            	cTypeString = cFinalTypeString;
	            	linkType.setToolTipText(cFinalType.getQualifiedName());
	            }
	            
	            return cTypeString;
			}
		}
		
		return "";
	}

	
//	private String getCommentsString(){
//	      // Comments
//	      String commentString = "";
//	      EList<Comment> comments = item.getComments();
//	      if (comments.size() > 0 ) {
//	      	//commentString = commentString + "Comments of '" + treeObject.getProperty().getName()+"':\n"; 
//	      	for (Comment comment : comments) {
//	  			commentString = commentString + comment.getBody() + " ";
//	  		}
//	      }
//	      
//			if (!commentString.equals("")) {
//				commentString = commentString + "\n\n";
//			}
//			
//			if (item.getProperty() != null ) {
//				if (item.getProperty().getType() != null) {
//					 EList<Comment> commentsOfComponentType = item.getProperty().getType().getOwnedComments();
//					 if (commentsOfComponentType.size() > 0 ) {
//						 commentString = commentString + "Comments from '" + ((Classifier)item.getProperty().getType()).getName()+"' (component type):\n"; 
//						 for (Comment comment : commentsOfComponentType) {
//							commentString = commentString + comment.getBody() + " ";
//						}
//					}
//				}
//			}
//			if (!commentString.equals("")) {
//		        return commentString;
//			}
//			return "";
//		}
	
}
