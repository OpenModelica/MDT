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
package org.openmodelica.modelicaml.simulation.testexecution.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.simulation.Activator;
import org.openmodelica.modelicaml.simulation.plot.JFreeChartPlotComposite;
import org.openmodelica.modelicaml.simulation.plot.VariableTreeComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;

public class DialogPlot extends Dialog {

	private String title = "";
	private SashForm sashForm1;
	private Composite Tree;
	private Composite Plot;
	
	public DialogPlot(Shell parentShell,String title) {
		super(parentShell);
		setBlockOnOpen(false);
        //setShellStyle( SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP | SWT.SHELL_TRIM );
		setShellStyle( SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.SHELL_TRIM );
        this.title = title;
	}
	
	protected void configureShell(Shell shell) {
        super.configureShell(shell);
   		shell.setText(this.title);
   		shell.setSize(900, 700);
   		shell.setImage(SWTResourceManager.getImage(Activator.class, "/icons/plot.png"));
    }

	
	@Override
	protected Control createDialogArea(Composite parent) {
        final Composite composite = (Composite) super.createDialogArea(parent);
                composite.setLayout(new FillLayout(SWT.HORIZONTAL));
        
                {
        			sashForm1 = new SashForm(composite, SWT.BORDER);
        			{
        				Plot = new JFreeChartPlotComposite(sashForm1, SWT.NONE);
        				Plot.setLayout(new FillLayout(SWT.HORIZONTAL));
        			}
        			{
        				Tree = new VariableTreeComposite(sashForm1, SWT.NONE);
        				GridLayout TreeLayout = new GridLayout();
        				TreeLayout.makeColumnsEqualWidth = true;
        				Tree.setLayout(TreeLayout);
        			}
        			sashForm1.setWeights(new int[] {600, 273});
        		}
        
        return parent;
	}
	
}
