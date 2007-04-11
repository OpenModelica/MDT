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

import org.eclipse.debug.core.DebugException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.window.Window;
import org.modelica.mdt.debug.core.model.MDTDebugTarget;
import org.modelica.mdt.debug.ui.MDTDebugUIPlugin;

/**
 * Pushes a value onto the data stack.
 */
public class PushAction extends Action {
    
    private MDTDebugTarget fTarget;
    private DataStackView fView;

    /**
     * Constructs an action to pop values off the stack 
     */
    protected PushAction(DataStackView view) {
        super("Push");
        ImageRegistry imageRegistry = MDTDebugUIPlugin.getDefault().getImageRegistry();
        setImageDescriptor(imageRegistry.getDescriptor(MDTDebugUIPlugin.IMG_ELCL_PUSH));
        setDisabledImageDescriptor(imageRegistry.getDescriptor(MDTDebugUIPlugin.IMG_DLCL_PUSH));
        setToolTipText("Push");
        setEnabled(false);     
        fView = view;
    }
    
    public void run() {
        InputDialog dialog = new InputDialog(fView.getSite().getShell(), "Specify Value", "Enter value to push", null, null);
        if (dialog.open() == Window.OK) {
            try {
                fTarget.push(dialog.getValue());
            } catch (DebugException e) {
            }
        }
        fView.getViewer().refresh();
    }

    protected void setDebugTarget(MDTDebugTarget target) {
        fTarget = target;
        setEnabled(fTarget != null && fTarget.canPush());
    }
    
    
}
