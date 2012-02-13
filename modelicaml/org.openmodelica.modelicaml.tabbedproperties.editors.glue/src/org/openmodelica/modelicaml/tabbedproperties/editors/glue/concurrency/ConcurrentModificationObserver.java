/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.concurrency;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

// TODO: Auto-generated Javadoc
/**
 * Detects concurrent modifications of GMF and Xtext editors based on Xtext's dirty state management.
 * 
 * @author koehnlein
 */
public class ConcurrentModificationObserver implements IPartListener {

	/** The factory. */
	private EditingDomainAdapter.Factory factory;

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 *
	 * @param activePage the active page
	 */
	public ConcurrentModificationObserver(IWorkbenchPage activePage) {
		factory = new EditingDomainAdapter.Factory();
		for (IEditorReference editorRef : activePage.getEditorReferences()) {
			IEditorPart editor = editorRef.getEditor(false);
			if (editor != null) {
				partOpened(editor);
			}
		}
	}

	/**
	 * This method is called when information about an ConcurrentModification
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param part the part
	 */
	public void partOpened(IWorkbenchPart part) {
		if (part instanceof DiagramEditor) {
			TransactionalEditingDomain editingDomain = ((DiagramEditor) part).getEditingDomain();
			factory.adapt(editingDomain, EditingDomainAdapter.class);
		}
	}

	/**
	 * This method is called when information about an ConcurrentModification
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param part the part
	 */
	public void partClosed(IWorkbenchPart part) {
		if (part instanceof DiagramEditor) {
			// as long as bug 299920 is not fixed, we have to dispose the adapter manually
			TransactionalEditingDomain editingDomain = ((DiagramEditor) part).getEditingDomain();
			if (editingDomain != null) {
				EditingDomainAdapter adapter = (EditingDomainAdapter) factory.adapt(editingDomain,
						EditingDomainAdapter.class);
				if (adapter != null) {
					adapter.dispose();
				}
			}
		}
	}

	/**
	 * This method is called when information about an ConcurrentModification
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param part the part
	 */
	public void partActivated(IWorkbenchPart part) {
		// do nothing
	}

	/**
	 * This method is called when information about an ConcurrentModification
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param part the part
	 */
	public void partDeactivated(IWorkbenchPart part) {
		// do nothing
	}

	/**
	 * This method is called when information about an ConcurrentModification
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param part the part
	 */
	public void partBroughtToTop(IWorkbenchPart part) {
		// do nothing
	}

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 */
	public static class Initializer implements IStartup {
		
		/**
		 * Early startup.
		 */
		public void earlyStartup() {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					activePage.addPartListener(new ConcurrentModificationObserver(activePage));
				}
			});
		}
	}

}
