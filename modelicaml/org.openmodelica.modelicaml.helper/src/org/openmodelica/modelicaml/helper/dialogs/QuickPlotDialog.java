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
package org.openmodelica.modelicaml.helper.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.helper.Activator;

public class QuickPlotDialog extends Dialog {

	private QuickPlotComposite plot;

	public QuickPlotDialog(Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(false);
		setShellStyle( SWT.SHELL_TRIM );
	}
	
	
	protected void configureShell(Shell shell) {
        super.configureShell(shell);
   		shell.setText("Quick Plot");
   		shell.setSize(600, 400);
   		shell.setImage(SWTResourceManager.getImage(Activator.class, "/icons/plot.png"));
    }

	@Override
	protected Control createButtonBar(Composite parent) {
		// do not create OK and CANCEL buttons.
		return null;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
        final Composite composite = (Composite) super.createDialogArea(parent);

        composite.setLayout(new FillLayout(SWT.HORIZONTAL));
        {
        	plot = new QuickPlotComposite(composite, SWT.NONE);
        	plot.setLayout(new FillLayout(SWT.HORIZONTAL));
        }
        return parent;
	}
	
	public QuickPlotComposite getPlot() {
		return plot;
	}
}
