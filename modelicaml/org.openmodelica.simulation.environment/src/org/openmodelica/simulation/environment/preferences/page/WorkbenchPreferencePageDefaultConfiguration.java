package org.openmodelica.simulation.environment.preferences.page;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.openmodelica.simulation.environment.Activator;

/**
 * OpenModelica Interactive preference page
 * @author Parham
 *
 */
public class WorkbenchPreferencePageDefaultConfiguration extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	public final static String AUTHOR = "AUTHOR";
	public final static String COMPANY = "COMPANY";
	public final static String SimulationControlServerPort = "SCSPort";
	public final static String RuntimeControlServerPort = "RCSPort";
	public final static String SimulationTransferServerPort = "STSPort";
	public final static String START = "START";
	public final static String STOP = "STOP";
	public final static String OUTPUT = "OUTPUT";
	public final static String TOLERANCE = "TOLERANCE";
	public final static String SOLVER = "SOLVER";
	public final static String DASSL = "dassl";
	public final static String EULER = "euler";
	public final static String DASSL2 = "dassl2";
	public final static String RUNGEKUTTA = "rungekutta";
	
	IntegerFieldEditor simulationControlServerPortIntegerFieldEditor;
	IntegerFieldEditor runtimeControlServerPortIntegerFieldEditor;
	IntegerFieldEditor simulationTransferServerPortIntegerFieldEditor;
	
	public WorkbenchPreferencePageDefaultConfiguration() {
		super(GRID);
	}

//	public WorkbenchPreferencePageDefaultConfiguration(String title) {
//		super(title);
//		// TODO Auto-generated constructor stub
//	}
//
//	public WorkbenchPreferencePageDefaultConfiguration(String title,
//			ImageDescriptor image) {
//		super(title, image);
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public void createFieldEditors() {
//		addField(new DirectoryFieldEditor("WORKSPACEPATH", "&Directory preference:",
//				getFieldEditorParent()));

		addField(new StringFieldEditor(AUTHOR, "Author:",
				getFieldEditorParent()));
		addField(new StringFieldEditor(COMPANY, "Company:",
				getFieldEditorParent()));

		simulationControlServerPortIntegerFieldEditor = new IntegerFieldEditor(SimulationControlServerPort, "Simulation Control Server Port:",
				getFieldEditorParent(), 5);
		addField(simulationControlServerPortIntegerFieldEditor);
		
		runtimeControlServerPortIntegerFieldEditor = new IntegerFieldEditor(RuntimeControlServerPort, "Runtime Control Server Port:",
				getFieldEditorParent(), 5);
		addField(runtimeControlServerPortIntegerFieldEditor);
		
		simulationTransferServerPortIntegerFieldEditor = new IntegerFieldEditor(SimulationTransferServerPort, "Simulation Transfer Server Port:",
				getFieldEditorParent(), 5);
		addField(simulationTransferServerPortIntegerFieldEditor);
		
		addField(new StringFieldEditor(START, "Start:",
				getFieldEditorParent()));
		
		addField(new StringFieldEditor(STOP, "Stop:",
				getFieldEditorParent()));
		
		addField(new StringFieldEditor(OUTPUT, "Output Interval(s):",
				getFieldEditorParent()));
		
		addField(new StringFieldEditor(TOLERANCE, "Tolerance:",
				getFieldEditorParent()));
		
		addField(new ComboFieldEditor(SOLVER, "Solver:", new String[][] { { DASSL, "dassl" }, { RUNGEKUTTA, "rungekutta" },
				{ EULER, "euler" } }, getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Set default simulation setup for interactive and non-interactive simulation.");
		
	}

//	@Override
//	protected Control createContents(Composite parent) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
