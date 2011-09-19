/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Bjorn Freeman-Benson - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.debug.ui.views;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.ui.AbstractDebugView;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.core.model.MDTDebugTarget;
import org.modelica.mdt.debug.gdb.core.model.GDBDebugTarget;
import org.modelica.mdt.debug.gdb.core.model.stack.GDBStackFrame;
import org.modelica.mdt.debug.gdb.core.model.thread.GDBThread;

/**
 * View of the MDT VM data stack 
 */
public class DataStackView extends AbstractDebugView implements ISelectionListener {
    
    private MDTDebugTarget fTarget;
    private GDBDebugTarget fGDBTarget;
    private PopAction fPopAction;
    private PushAction fPushAction;
	
	class StackViewContentProvider implements ITreeContentProvider {

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
		 */
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof MDTDebugTarget) {
				try {
					return ((MDTDebugTarget)parentElement).getDataStack();
				} catch (DebugException e) {
				}
			}
			return new Object[0];
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
		 */
		public Object getParent(Object element) {
			if (element instanceof IDebugTarget) {
				return null;
			} else {
				return ((IDebugElement)element).getDebugTarget();
			}
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
		 */
		public boolean hasChildren(Object element) {
			if (element instanceof IDebugElement) {
				return getChildren(element).length > 0;
			}
			return false;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.AbstractDebugView#createViewer(org.eclipse.swt.widgets.Composite)
	 */
	protected Viewer createViewer(Composite parent) {
		TreeViewer viewer = new TreeViewer(parent);
		viewer.setLabelProvider(DebugUITools.newDebugModelPresentation());
		viewer.setContentProvider(new StackViewContentProvider());
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(IDebugUIConstants.ID_DEBUG_VIEW, this);
		getSite().setSelectionProvider(viewer);
		return viewer;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.AbstractDebugView#createActions()
	 */
	protected void createActions() {
	    fPopAction = new PopAction(this);
	    fPushAction = new PushAction(this);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.AbstractDebugView#getHelpContextId()
	 */
	protected String getHelpContextId() {
		return null;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.AbstractDebugView#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	protected void fillContextMenu(IMenuManager menu) {
		menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		menu.add(fPopAction);
		menu.add(fPushAction);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.AbstractDebugView#configureToolBar(org.eclipse.jface.action.IToolBarManager)
	 */
	protected void configureToolBar(IToolBarManager tbm) {
	    tbm.add(fPopAction);
	    tbm.add(fPushAction);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPart#dispose()
	 */
	public void dispose() {
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(IDebugUIConstants.ID_DEBUG_VIEW, this);
		super.dispose();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		update();
	}
    
    /**
     * Updates the view for the selected target (if suspended)
     */
    private synchronized void update() {
		IAdaptable adaptable = DebugUITools.getDebugContext();
		fTarget = null;
		fGDBTarget = null;
		if (adaptable != null) {
			IDebugElement element = (IDebugElement) adaptable.getAdapter(IDebugElement.class);
			if (element != null) {
				if (element.getModelIdentifier().equals(MDTDebugCorePlugin.ID_MDT_DEBUG_MODEL)) {
					if (element.getDebugTarget() instanceof MDTDebugTarget)
						fTarget = (MDTDebugTarget) element.getDebugTarget();
					else if (element.getDebugTarget() instanceof GDBDebugTarget)
						fGDBTarget = (GDBDebugTarget) element.getDebugTarget();
				}
			}
			if (adaptable instanceof GDBStackFrame) {
				try {
					GDBStackFrame gdbStackFrame = (GDBStackFrame)adaptable;
					GDBThread gdbThread = (GDBThread)gdbStackFrame.getThread();
					gdbThread.setCurrentGDBStackFrame(gdbStackFrame);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					MDTDebugCorePlugin.log(null, e);
				}
			}
		}
		if (fTarget != null) {
			Object input = null;
			if (fTarget.isSuspended()) {
			    input = fTarget;
			}
			getViewer().setInput(input);
			fPushAction.setDebugTarget(fTarget);
			getViewer().refresh();
		} else if (fGDBTarget != null) {
			Object input = null;
			if (fGDBTarget.isSuspended()) {
			    input = fGDBTarget;
			}
			getViewer().setInput(input);
			fPushAction.setGDBDebugTarget(fGDBTarget);
			getViewer().refresh();
		}
    }
}
