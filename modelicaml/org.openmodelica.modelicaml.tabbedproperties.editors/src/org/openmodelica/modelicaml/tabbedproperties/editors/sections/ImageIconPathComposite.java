package org.openmodelica.modelicaml.tabbedproperties.editors.sections;
///*******************************************************************************
// * Copyright (c) 2006 CEA List.
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * which accompanies this distribution, and is available at
// * http://www.eclipse.org/legal/epl-v10.html
// *
// * Contributors:
// *     CEA List - initial API and implementation
// *******************************************************************************/
//package com.eadsiw.modelicaml.editors.xtext.tabbedproperties.sections;
//
//import org.eclipse.core.resources.IWorkspace;
//import org.eclipse.core.resources.IWorkspaceRoot;
//import org.eclipse.core.resources.ResourcesPlugin;
//import org.eclipse.core.runtime.IPath;
//import org.eclipse.emf.common.notify.impl.NotificationImpl;
//import org.eclipse.emf.common.util.EList;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.custom.CLabel;
//import org.eclipse.swt.events.FocusEvent;
//import org.eclipse.swt.events.KeyAdapter;
//import org.eclipse.swt.events.KeyEvent;
//import org.eclipse.swt.events.MouseEvent;
//import org.eclipse.swt.events.MouseListener;
//import org.eclipse.swt.layout.FormAttachment;
//import org.eclipse.swt.layout.FormData;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.FileDialog;
//import org.eclipse.swt.widgets.MessageBox;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Text;
//import org.eclipse.ui.PlatformUI;
//import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
//import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
//import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
//import org.eclipse.uml2.uml.Element;
//import org.eclipse.uml2.uml.ElementImport;
//import org.eclipse.uml2.uml.NamedElement;
//import org.eclipse.uml2.uml.Stereotype;
//
////import com.cea.papyrus.core.listener.PapyrusNotification;
////import com.cea.papyrus.profile.ui.composites.ISectionComposite;
////import com.eadsiw.modelicaml.tabbedproperties.properties.ModelicaMLProperties;
////import com.eadsiw.modelicaml.tabbedproperties.utils.Utils;
//
//import org.eclipse.papyrus.profile.ui.compositesformodel.ISectionComposite;;
//
///**
// * 
// */
//public class ImageIconPathComposite implements ISectionComposite {
//
//	/**
//	 * 
//	 */
//	private Text iconImagePathText;
//	
//	private Button button = null;
//	/**
//	 * Listener for the add button.
//	 */
//	protected MouseListener editButtonlistener = new EditButtonlistener();
//
//	/**
//	 * 
//	 */
//	private Composite parent;
//
//	/**
//	 * 
//	 */
//	private Element element;
//
//	/**
//	 * 
//	 */
//	private String sectionLabel;
//
//	/**
//	 * 
//	 */
//	protected DirectEditFocusListener directEditFocusListener = new DirectEditFocusListener();
//
//	/**
//	 * 
//	 */
//	protected KeyAdapter keyAdapter = new EditKeyAdapter();
//
//	/**
//	 * Creates a new NameComposite.
//	 * 
//	 * @param sectionName
//	 *            the name of the section
//	 */
//	public ImageIconPathComposite(String sectionName) {
//		super();
//		sectionLabel = sectionName;
//	}
//
//	/**
//	 * 
//	 * 
//	 * @return the element
//	 */
//	public Element getElement() {
//		return element;
//	}
//
//	/**
//	 * 
//	 * 
//	 * @param element
//	 *            the element to set
//	 */
//	public void setElement(Element element) {
//		this.element = element;
//	}
//
//	/**
//	 * 
//	 * 
//	 * @return
//	 */
//	public String getText() {
//		return iconImagePathText.getText();
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.cea.papyrus.core.ui.composites.ISectionComposite#createContent(org.eclipse.swt.widgets
//	 * .Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory)
//	 */
//	/**
//	 * 
//	 * 
//	 * @param factory
//	 * @param parent
//	 * 
//	 * @return
//	 */
//	public Composite createContent(Composite parent, TabbedPropertySheetWidgetFactory factory) {
//		Composite composite = factory.createFlatFormComposite(parent);
//		this.parent = parent;
//		FormData data;
//
//		iconImagePathText = factory.createText(composite, ""); //$NON-NLS-1$
//		iconImagePathText.setEditable(true);
//
//		// nameText.addFocusListener(directEditFocusListener);
//		data = new FormData();
//		data.width = 300;
//		data.left = new FormAttachment(0, AbstractPropertySection.STANDARD_LABEL_WIDTH);
//		data.top = new FormAttachment(0, 0);
//		iconImagePathText.setLayoutData(data);
//
//		CLabel nameLabel = factory.createCLabel(composite, sectionLabel); //$NON-NLS-1$
//		data = new FormData();
//		data.left = new FormAttachment(0, 0);
//		data.right = new FormAttachment(iconImagePathText, 120);
//		data.top = new FormAttachment(iconImagePathText, 0, SWT.CENTER);
//		nameLabel.setLayoutData(data);
//		
//		button = new Button(composite, SWT.PUSH);
//		button.setText("Select");
//		data = new FormData();
//		data.left = new FormAttachment(iconImagePathText, 0);
//		data.top = new FormAttachment(0, 0);
//		button.setLayoutData(data);
//		button.addMouseListener(editButtonlistener);
//		
//
//
//		
//		return composite;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.cea.papyrus.core.ui.composites.ISectionComposite#refresh()
//	 */
//	/**
//	 * 
//	 */
//	public void refresh() {
//		if (!iconImagePathText.isDisposed()) {
//			if (element instanceof NamedElement) {
//				iconImagePathText.removeFocusListener(directEditFocusListener);
//				iconImagePathText.removeKeyListener(keyAdapter);
//				EList<Stereotype> stereotypelist = ((NamedElement) element).getAppliedStereotypes();
//				String iconPath = null;
//				for (Stereotype stereotype : stereotypelist) {
//					if(Utils.isModelicaMLRestrictedClass(stereotype) || Utils.isModelicaMLComponent(stereotype))
//					{
//						iconPath = (String)((NamedElement) element).getValue(stereotype, ModelicaMLProperties.s_p_iconImagePath);
//					}
//				}
//				
//				if (iconPath == null) {
//					iconImagePathText.setText("");
//				} else {
//					iconImagePathText.setText(iconPath);
//				}
//
//				iconImagePathText.setEditable(true);
//			}
//
//			
//			iconImagePathText.addFocusListener(directEditFocusListener);
//			iconImagePathText.addKeyListener(keyAdapter);
//		}
//	}
//	
//	
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.cea.papyrus.core.ui.composites.ISectionComposite#dispose()
//	 */
//	/**
//	 * 
//	 */
//	public void dispose() {
//		if (iconImagePathText != null && !iconImagePathText.isDisposed()) {
//			iconImagePathText.removeFocusListener(directEditFocusListener);
//			iconImagePathText.removeKeyListener(keyAdapter);
//		}
//	}
//	
//	public void editButtonPressed(){
//
//
//		Shell shell = new Shell();
//
//		IWorkspace workspace = ResourcesPlugin.getWorkspace();
//		IWorkspaceRoot root = workspace.getRoot();
//		IPath location = root.getLocation();
//		
//		FileDialog dialog =
//		    new FileDialog(shell);
//		dialog.setFilterPath(location.toOSString());
//		String selected = dialog.open();
//		if(selected.startsWith(location.toOSString()))
//		{
//			selected = selected.substring(location.toOSString().length());
//		
//		EList<Stereotype> stereotypelist = ((NamedElement) element).getAppliedStereotypes();
//		for (Stereotype stereotype : stereotypelist) {
//			if(Utils.isModelicaMLRestrictedClass(stereotype) || Utils.isModelicaMLComponent(stereotype))
//			{
//				((NamedElement) element).setValue(stereotype, ModelicaMLProperties.s_p_iconImagePath, selected);
//				((NamedElement) element).eNotify(new NotificationImpl(PapyrusNotification.SET, null, null));
//			}
//		}
//		}
//		else {
//			 MessageBox mb = new MessageBox(shell, SWT.ERROR);
//		        mb.setText("Wrong Location.");
//		        mb.setMessage("Image have to be located in your current workspace!");
//		        mb.open();
//		}
//		
//	}
//
//	/**
//	 * sets the behavioral feature.
//	 * 
//	 * @param element
//	 *            the behavioral feature that owns the displayed parameters
//	 */
//	public void setInput(Element element) {
//		setElement(element);
//	}
//
//	/**
//	 * 
//	 */
//	private class EditKeyAdapter extends KeyAdapter {
//
//		/*
//		 * (non-Javadoc)
//		 * 
//		 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
//		 */
//		/**
//		 * 
//		 * 
//		 * @param e
//		 */
//		@Override
//		public void keyPressed(KeyEvent e) {
//			// close the text editor and copy the data over
//			// when the user hits "ENTER"
//			if (e.character == SWT.CR) {
//
//				// get text in nameText and save it into the model
//				if (element instanceof ElementImport) {// in the case of element import
//					// do nothing
//				} else if (element instanceof NamedElement) {
////					Command command = GenericCommandFactory.eINSTANCE.createCommand(element, "iconImagePath",
////							new Object[] { iconImagePathText.getText().trim() });
////					MultiDiagramEditor editor = (MultiDiagramEditor) PlatformUI.getWorkbench()
////							.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
////					CommandStack stack = (CommandStack) editor.getAdapter(CommandStack.class);
////					stack.execute(command);
////					editor.setFocus();
//					// ((NamedElement)element).setName(nameText.getText().trim());
//				}
//
//			}
//			// close the text editor when the user hits "ESC"
//			if (e.character == SWT.ESC) {
//				if (element instanceof ElementImport) {// in the case of element import
//					// do nothing
//				} else if (element instanceof NamedElement) {
//					// export text
//					EList<Stereotype> stereotypelist = ((NamedElement) element).getAppliedStereotypes();
//					for (Stereotype stereotype : stereotypelist) {
//						if(Utils.isModelicaMLRestrictedClass(stereotype) || Utils.isModelicaMLComponent(stereotype))
//						{
//							((NamedElement) element).setValue(stereotype, ModelicaMLProperties.s_p_iconImagePath,iconImagePathText.getText().trim() );
//							((NamedElement) element).eNotify(new NotificationImpl(PapyrusNotification.SET, null, null));
//						}
//					}			
//					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().setFocus();
//				}
//			}
//		}
//	}
//
//	/**
//	 * Listener for a direct edit manager.
//	 * 
//	 * @author Remi SCHNEKENBURGER
//	 */
//	protected class DirectEditFocusListener implements org.eclipse.swt.events.FocusListener {
//
//		// check if the name has been changed or not
//		/**
//		 * 
//		 */
//		String label = "";
//
//		/**
//		 * 
//		 * 
//		 * @param e
//		 */
//		public void focusGained(FocusEvent e) {
//			label = getText().trim();
//		}
//
//		/**
//		 * 
//		 * 
//		 * @param e
//		 */
//		public void focusLost(FocusEvent e) {
//			if (!label.equals(getText().trim())) {
//				if (element instanceof NamedElement) {
//
//					EList<Stereotype> stereotypelist = ((NamedElement) element).getAppliedStereotypes();
//					for (Stereotype stereotype : stereotypelist) {
//						if(Utils.isModelicaMLRestrictedClass(stereotype) || Utils.isModelicaMLComponent(stereotype))
//						{
//							((NamedElement) element).setValue(stereotype, ModelicaMLProperties.s_p_iconImagePath,iconImagePathText.getText().trim() );
//							((NamedElement) element).eNotify(new NotificationImpl(PapyrusNotification.SET, null, null));
//						}
//					}			
//				}
//			}
//		}
//	};
//	
//protected class EditButtonlistener implements MouseListener {
//
//		
//		/**
//		 * 
//		 * 
//		 * @param e
//		 */
//		public void mouseDoubleClick(MouseEvent e) {
//			// do nothing
//		}
//
//		/**
//		 * 
//		 * 
//		 * @param e
//		 */
//		public void mouseDown(MouseEvent e) {
//			// do nothing
//		}
//
//		/**
//		 * 
//		 * 
//		 * @param e
//		 */
//		public void mouseUp(MouseEvent e) {
//			editButtonPressed();
//			refresh();
//		}
//	}
//}
