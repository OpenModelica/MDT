package org.openmodelica.modelicaml.simulation.testexecution.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.openmodelica.modelicaml.simulation.plot.JFreeChartPlotComposite;
import org.openmodelica.modelicaml.simulation.plot.VariableTreeComposite;

public class Dialog4Plot extends org.eclipse.swt.widgets.Dialog {

	private Shell dialogShell;
	private SashForm sashForm1;
	private Composite Tree;
	private Composite Plot;

	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Dialog inside a new Shell.
	*/
	
	public Dialog4Plot(Shell parent, int style) {
		super(parent, style);
	}

	public void open() {
		try {
			Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

			GridLayout dialogShellLayout = new GridLayout();
			dialogShellLayout.makeColumnsEqualWidth = true;
			dialogShell.setLayout(dialogShellLayout);
			{
				GridData sashForm1LData = new GridData();
				sashForm1LData.horizontalAlignment = GridData.FILL;
				sashForm1LData.verticalAlignment = GridData.FILL;
				sashForm1LData.grabExcessVerticalSpace = true;
				sashForm1LData.grabExcessHorizontalSpace = true;
				sashForm1 = new SashForm(dialogShell, SWT.BORDER);
				sashForm1.setLayoutData(sashForm1LData);
				{
					Plot = new JFreeChartPlotComposite(sashForm1, SWT.NONE);
					GridLayout PlotLayout = new GridLayout();
					PlotLayout.makeColumnsEqualWidth = true;
					Plot.setLayout(PlotLayout);
				}
				{
					Tree = new VariableTreeComposite(sashForm1, SWT.NONE);
					GridLayout TreeLayout = new GridLayout();
					TreeLayout.makeColumnsEqualWidth = true;
					Tree.setLayout(TreeLayout);
				}
			}
			dialogShell.layout();
			dialogShell.pack();			
			dialogShell.setLocation(getParent().toDisplay(100, 100));
			dialogShell.open();
			Display display = dialogShell.getDisplay();
			while (!dialogShell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
