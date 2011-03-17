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
//import org.eclipse.core.runtime.Assert;
//import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.jface.viewers.IStructuredSelection;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.ui.IWorkbenchPart;
//import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
//
//import com.cea.papyrus.core.editpart.IUMLElementEditPart;
//import com.cea.papyrus.core.tabbedproperties.PropertyViewSection;
//
///**
// * The name section on the element tab.
// */
//public class ImageIconPathSection extends PropertyViewSection {
//
//	/**
//	 * 
//	 */
//	private ImageIconPathComposite imageIconPathComposite;
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse
//	 * .swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
//	 */
//	/**
//	 * 
//	 * 
//	 * @param tabbedPropertySheetPage
//	 * @param parent
//	 */
//	@Override
//	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
//		super.createControls(parent, tabbedPropertySheetPage);
//		imageIconPathComposite = new ImageIconPathComposite("Icon Path:");
//		imageIconPathComposite.createContent(parent, tabbedPropertySheetPage.getWidgetFactory());
//	}
//
//	/*
//	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
//	 */
//	/**
//	 * 
//	 */
//	@Override
//	public void refresh() {
//		imageIconPathComposite.refresh();
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @seeorg.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.
//	 * IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
//	 */
//	/**
//	 * 
//	 * 
//	 * @param part
//	 * @param selection
//	 */
//	@Override
//	public void setInput(IWorkbenchPart part, ISelection selection) {
//		super.setInput(part, selection);
//		Assert.isTrue(selection instanceof IStructuredSelection);
//		Object input = ((IStructuredSelection) selection).getFirstElement();
//		if (input instanceof IUMLElementEditPart<?>) {
//			imageIconPathComposite.setElement(((IUMLElementEditPart<?>) input).getUmlElement());
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.cea.papyrus.core.ui.properties.tabbed.PropertyViewSection#dispose()
//	 */
//	/**
//	 * 
//	 */
//	public void dispose() {
//		super.dispose();
//		if (imageIconPathComposite != null)
//			imageIconPathComposite.dispose();
//	}
//}