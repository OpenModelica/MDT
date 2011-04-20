package org.openmodelica.modelicaml.view.valuebindings.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeParent;

public class BACKUP_ElementSelectionDialog extends Dialog {
	private Shell sShell = null; 
	private Button bttOK = null;
	private Button bttCancel = null;

	private int value = 0;
	
	private Label message;
	private StyledText selectedElementDescription;
	private ControlDecoration selectedElementDecoration;

	private StyledText qName;
	private StyledText comments;
	
	private EObject selectedElement = null;
	
	private TreeParent selectedTreeParent = null; 
	private TreeViewer viewer = null;
	
	
	
	public EObject getSelectedElement() {
		return selectedElement;
	}

	public void setSelectedElement(EObject selectedElement) {
		this.selectedElement = selectedElement;
	}

	private List<String> listOfAllowedMetaClassNames = new ArrayList<String>();
//	private List<String> listOfNotAllowedMetaClassNames = new ArrayList<String>();
	
	private String title = "";
	private Image image = null;
	
	private String mode = null;
	
	public BACKUP_ElementSelectionDialog(	Shell parent, 
									Image image,
									String title, 
									String message, 
									List<String> listOfAllowedMetaClassNames, 
									TreeParent selectedTreeParent, 
									TreeViewer viewer, 
									String mode 
									) {
		super(parent);

		this.title = title;
		this.image = image;
		
		// create shell
		createSShell();
		
		this.message.setText(message);

		this.listOfAllowedMetaClassNames = listOfAllowedMetaClassNames;
		this.selectedTreeParent = selectedTreeParent;
		this.viewer = viewer;
		this.mode = mode;
		
		// add the selection listener
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);

	}

	 private ISelectionListener selectionListener = new ISelectionListener() {
	        public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
	        if (sourcepart != BACKUP_ElementSelectionDialog.this &&
	            selection instanceof IStructuredSelection) {
	
	        	if (getCurrentSelections() != null && getCurrentSelections().size() > 0 ) {
						selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
				}
				if (	selectedElement != null 
						&& listOfAllowedMetaClassNames.contains(selectedElement.eClass().getName())
						&& selectedElement != selectedTreeParent.getUmlElement()
						) {
					
					// init
					String string = "";
					int textRangePointer = 0;
					selectedElementDescription.setText(""); // reset the text
					StyleRange range = new StyleRange();
//					range.foreground = new Color(null, 105, 105, 105); // dim grey
					range.foreground = new Color(null, 139, 137, 137); 

					// set the selected Property text
					string = ((Property)selectedElement).getName();
					selectedElementDescription.append(string);
					selectedElementDescription.setStyleRange(range);
					textRangePointer = textRangePointer + string.length(); // point to the last chart. 
					
					String typeString = "not defined";
					if (((Property)selectedElement).getType() != null) {
						typeString = ((Property)selectedElement).getType().getName();
					}
					
					string = " (" + selectedElement.eClass().getName() + " of type " + typeString + ")";
					
					selectedElementDescription.append(string);
					range.start = textRangePointer;
					range.length = string.length();
					selectedElementDescription.setStyleRange(range);
					textRangePointer = textRangePointer + string.length(); // point to the last chart. 
					
					// set the qName text
					string = ((Property)selectedElement).getQualifiedName();
					qName.setText(string);
					
					// set the Comments text 
					EList<Comment> commentsList = ((Property)selectedElement).getOwnedComments();
					if (commentsList.size() > 0) {
						for (Comment comment : commentsList) {
							string = string + "\n" + comment.getBody();
						}
						comments.setText(string);
					}

					// hide the error image
					selectedElementDecoration.hide();

					// enable the OK button
					bttOK.setEnabled(true);
					
				} else {
					// reset all details 
					qName.setText("");
					comments.setText("");
					
					// show the error image
					selectedElementDecoration.show();					
					if (selectedElement == selectedTreeParent.getUmlElement()) { // link to its self
						String text = "It is not allowed to link associate an element with its self.";
						selectedElementDescription.setText(text);
					}
					else if (selectedElement != null) {
						String text = selectedElement.eClass().getName() + " is not allowed!";
						selectedElementDescription.setText(text);
					}
					else {
						String text = "Not a valid selection";
						selectedElementDescription.setText(text);
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
		sShell.setSize(545, 336);
		
		sShell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);
			}
		});
		
		Shell shell = sShell;
		
		message = new Label(shell, SWT.NONE);
		message.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		message.setBounds(20, 10, 507, 17);
		message.setText("Click on the element to be associated.");

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		composite.setBounds(0, 0, 537, 34);
		
		selectedElementDescription = new StyledText(shell, SWT.BORDER);
		selectedElementDescription.setDoubleClickEnabled(false);
		selectedElementDescription.setEditable(false);
		selectedElementDescription.setBounds(20, 65, 507, 17);
		
		selectedElementDecoration = new ControlDecoration(selectedElementDescription, SWT.LEFT | SWT.TOP);
		selectedElementDecoration.setMarginWidth(3);
		selectedElementDecoration.setImage(FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage());
		selectedElementDecoration.show();
		
		Label lblSelectedElement = new Label(shell, SWT.NONE);
		lblSelectedElement.setBounds(20, 46, 507, 13);
		lblSelectedElement.setText("Selected Element:");
			
		Group grpSelectedElementDetails = new Group(shell, SWT.NONE);
		grpSelectedElementDetails.setText("Selected Element Details:");
		grpSelectedElementDetails.setBounds(20, 88, 507, 158);
		
		Label lblQName = new Label(grpSelectedElementDetails, SWT.NONE);
		lblQName.setBounds(10, 20, 487, 13);
		lblQName.setText("Qualified Name:");
		
		qName = new StyledText(grpSelectedElementDetails, SWT.BORDER | SWT.READ_ONLY );
		qName.setDoubleClickEnabled(false);
		qName.setEditable(false);
		qName.setText("");
		qName.setIndent(2);
		qName.setWrapIndent(2);
		qName.setBounds(10, 39, 487, 17);
		
		Label lblComments = new Label(grpSelectedElementDetails, SWT.NONE);
		lblComments.setBounds(10, 62, 487, 13);
		lblComments.setText("Comments:");
		
		comments = new StyledText(grpSelectedElementDetails, SWT.BORDER | SWT.FULL_SELECTION | SWT.READ_ONLY | SWT.WRAP);
		comments.setText("");
		comments.setDoubleClickEnabled(false);
		comments.setEditable(false);
		comments.setBounds(10, 81, 487, 67);

		bttOK = new Button(sShell, SWT.NONE);
		bttOK.setBounds(385, 262, 68, 23);
		bttOK.setText("OK");
		bttOK.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				setValue(SWT.OK);
				
//				TreeParent item = new TreeParent("HAHAH");
//				selectedTreeParent.addChild(item);
//				viewer.add(selectedTreeParent, item);
				
				sShell.dispose();
			}
		});
		
		bttCancel = new Button(sShell, SWT.NONE);
		bttCancel.setBounds(459, 262, 68, 23);
		bttCancel.setText("Cancel");
		bttCancel.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				setValue(SWT.CANCEL);
				sShell.dispose();
			}
		});
		
	}
	
	public int open() {
		this.sShell.open();
		return getValue();
	}

	
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
}
