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

import java.io.File;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.simulation.Activator;
import org.openmodelica.modelicaml.simulation.plot.JFreeChartPlotComposite;
import org.openmodelica.modelicaml.simulation.plot.VariableTreeComposite;

public class DialogPlot extends Dialog {

	private String title = "";
	private SashForm sashForm1;
	private Composite Tree;
	private Composite Plot;

	private String resultsFilePath;
	
	/** Contains a number of simulation results Key: fully qualified name <Key: time, Value: value>. */
	private Map<String, Map<String, String>> simulationResultsAsString;

	
	public DialogPlot(Shell parentShell, String resultsFilePath) {
		super(parentShell);
		setBlockOnOpen(false);
        //setShellStyle( SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP | SWT.SHELL_TRIM );
		setShellStyle( SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.SHELL_TRIM );
        this.resultsFilePath = resultsFilePath;
        this.title = getTitle(resultsFilePath);
	}
	 
	
	public synchronized void setResults(Map<String, Map<String, String>> results){
		simulationResultsAsString = results;
	}
	
	
	protected void configureShell(Shell shell) {
        super.configureShell(shell);
   		shell.setText(this.title);
   		shell.setSize(900, 700);
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
			sashForm1 = new SashForm(composite, SWT.BORDER | SWT.SMOOTH);
			{
				Plot = new JFreeChartPlotComposite(sashForm1, SWT.NONE, simulationResultsAsString);
				Plot.setLayout(new FillLayout(SWT.HORIZONTAL));
			}
			{
				Tree = new VariableTreeComposite(sashForm1, SWT.NONE, simulationResultsAsString.keySet(), getSimulationModelName(this.resultsFilePath));
				
				((VariableTreeComposite)Tree).setChart((JFreeChartPlotComposite)Plot);
				
				GridLayout TreeLayout = new GridLayout();
				TreeLayout.makeColumnsEqualWidth = true;
				Tree.setLayout(TreeLayout);
			}
			sashForm1.setWeights(new int[] {600, 273});
		}
        
        return parent;
	}

	private String getSimulationModelName(String filePath){
		String name = filePath;
		if (filePath != null && filePath.length() != 0) {
			String[] splitted = filePath.split("/");
			name = splitted[splitted.length - 1];
			name = name.replaceFirst("_res.xml", "");

			String[] dotSplitted = name.split("\\.");
			name = dotSplitted[dotSplitted.length - 1];
		}
		return name;
	}
	
	private String getTitle(String filePath){
		String title = "";
		File file = new File(filePath);
		String parentFolderPath = file.getParent();
		File sessionFolder = new File(parentFolderPath);
		
		if (sessionFolder.isDirectory()) {
			title = title + sessionFolder.getName() + "/" + file.getName();
			return title;
		}
		return filePath;
	}
}
