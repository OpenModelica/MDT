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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.modelica.mdt.debug.core.model.MDTDebugTarget;
import org.modelica.mdt.debug.ui.MDTDebugUIPlugin;

/**
 * Pops a selected value off the data stack. The selection does <b>not</b> have to be
 * the top element on the stack.
 */
public class PopAction extends Action implements ISelectionChangedListener {
    
    private DataStackView fView;

    /**
     * Constructs an action to pop values off the stack 
     */
    protected PopAction(DataStackView view) {
        super("Pop");
        ImageRegistry imageRegistry = MDTDebugUIPlugin.getDefault().getImageRegistry();
        setImageDescriptor(imageRegistry.getDescriptor(MDTDebugUIPlugin.IMG_ELCL_POP));
        setDisabledImageDescriptor(imageRegistry.getDescriptor(MDTDebugUIPlugin.IMG_DLCL_POP));
        setToolTipText("Pop");
        setEnabled(false);
        view.getSite().getSelectionProvider().addSelectionChangedListener(this);
        fView = view;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event) {
        if (event.getSelection().isEmpty()) {
            setEnabled(false);
        } else {
            setEnabled(getDebugTarget().canPop());
        } 
    }

    public void run() {
        TreeViewer viewer = (TreeViewer)fView.getViewer();
        IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
        Object popee = selection.getFirstElement();
        try {
            MDTDebugTarget target = getDebugTarget();
            IValue[] stack = target.getDataStack();
	        List<IValue> restore = new ArrayList<IValue>();
	        for (int i = 0; i < stack.length; i++) {
	            Object value = stack[i];
	            if (popee.equals(value)) {
	                // pop & stop
	                target.pop();
	                break;
	            } else {
	                // remember value to push back on
	                restore.add(target.pop());
	            }
	        }
	        while (!restore.isEmpty()) {
	            IValue value = restore.remove(restore.size() - 1);
	            target.push(value.getValueString());
	        }
        } catch (DebugException e) {
        }
        viewer.refresh();
    }
    
    /**
     * Returns the debug target associated with the data view.
     * 
     * @return the debug target associated with the data view
     */
    protected MDTDebugTarget getDebugTarget() {
        TreeViewer viewer = (TreeViewer)fView.getViewer();
        return (MDTDebugTarget) viewer.getInput();
    }
}
