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
package org.openmodelica.simulation.environment.view.project;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.openmodelica.simulation.core.models.simulation.SimulationProject;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * This Class can be used to instantiate composite objects to be used in the
 * SimulationProjectDataView TabFolder as a TabItems control composite.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */
public class SimulationProjectData_ProjectDescriptionComposite extends Composite implements Observer{
	
	/** The label name. */
	private Label labelName;
	
	/** The text name. */
	private Text textName;
	
	/** The text version. */
	private Text textVersion;
	
	/** The label company. */
	private Label labelCompany;
	
	/** The styled text comment. */
	private StyledText styledTextComment;
	
	/** The label comment. */
	private Label labelComment;
	
	/** The text company. */
	private Text textCompany;
	
	/** The text author. */
	private Text textAuthor;
	
	/** The label author. */
	private Label labelAuthor;
	
	/** The label version. */
	private Label labelVersion;
	
	/** The parent composite. {@link SimulationProjectDataView} */
	SimulationProjectDataView parentComposite;

//	/**
//	* Auto-generated main method to display this 
//	* org.eclipse.swt.widgets.Composite inside a new Shell.
//	*/
//	public static void main(String[] args) {
//		showGUI();
//	}
	
	/**
	* Overriding checkSubclass allows this class to extend org.eclipse.swt.widgets.Composite
	*/	
	protected void checkSubclass() {
	}
	
	/**
	* Auto-generated method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		SimulationProjectData_ProjectDescriptionComposite inst = new SimulationProjectData_ProjectDescriptionComposite(shell, SWT.NULL);
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if(size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * Instantiates a new simulation project data_ project description composite.
	 *
	 * @param parent the parent
	 * @param style the style
	 */
	public SimulationProjectData_ProjectDescriptionComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	/**
	 * Instantiates a new simulation project data_ project description composite.
	 *
	 * @param parent the parent
	 * @param simulationProjectDataView the simulation project data view
	 * @param style the style
	 */
	public SimulationProjectData_ProjectDescriptionComposite(org.eclipse.swt.widgets.Composite parent,
			SimulationProjectDataView simulationProjectDataView, int style) {
		super(parent, style);
		parentComposite = simulationProjectDataView;
		initGUI();
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout();
			thisLayout.makeColumnsEqualWidth = true;
			thisLayout.numColumns = 3;
			this.setLayout(thisLayout);
			this.setSize(245, 154);
			{
				labelName = new Label(this, SWT.NONE);
				GridData labelNameLData = new GridData();
				labelName.setLayoutData(labelNameLData);
				labelName.setText("Project Name:");
			}
			{
				GridData textNameLData = new GridData();
				textNameLData.horizontalIndent = 1;
				textNameLData.grabExcessHorizontalSpace = true;
				textNameLData.horizontalAlignment = GridData.FILL;
				textNameLData.horizontalSpan = 2;
				textName = new Text(this, SWT.BORDER);
				textName.setLayoutData(textNameLData);
				textName.setEditable(false);
			}
			{
				labelVersion = new Label(this, SWT.NONE);
				GridData labelVersionLData = new GridData();
				labelVersion.setLayoutData(labelVersionLData);
				labelVersion.setText("Version:");
			}
			{
				GridData textVersionLData = new GridData();
				textVersionLData.horizontalAlignment = GridData.FILL;
				textVersionLData.grabExcessHorizontalSpace = true;
				textVersionLData.horizontalSpan = 2;
				textVersion = new Text(this, SWT.BORDER);
				textVersion.setLayoutData(textVersionLData);
				textVersion.setEditable(false);
			}
			{
				labelAuthor = new Label(this, SWT.NONE);
				GridData labelAuthorLData = new GridData();
				labelAuthor.setLayoutData(labelAuthorLData);
				labelAuthor.setText("Author:");
			}
			{
				GridData textAuthorLData = new GridData();
				textAuthorLData.horizontalAlignment = GridData.FILL;
				textAuthorLData.grabExcessHorizontalSpace = true;
				textAuthorLData.horizontalSpan = 2;
				textAuthor = new Text(this, SWT.BORDER);
				textAuthor.setLayoutData(textAuthorLData);
				textAuthor.setEditable(false);
			}
			{
				labelCompany = new Label(this, SWT.NONE);
				GridData labelCompanyLData = new GridData();
				labelCompany.setLayoutData(labelCompanyLData);
				labelCompany.setText("Company:");
			}
			{
				GridData textCompanyLData = new GridData();
				textCompanyLData.horizontalAlignment = GridData.FILL;
				textCompanyLData.grabExcessHorizontalSpace = true;
				textCompanyLData.horizontalSpan = 2;
				textCompany = new Text(this, SWT.BORDER);
				textCompany.setLayoutData(textCompanyLData);
				textCompany.setEditable(false);
			}
			{
				labelComment = new Label(this, SWT.NONE);
				GridData labelCommentLData = new GridData();
				labelCommentLData.horizontalSpan = 3;
				labelComment.setLayoutData(labelCommentLData);
				labelComment.setText("Comment:");
			}
			{
				GridData styledTextCommentLData = new GridData();
				styledTextCommentLData.horizontalSpan = 3;
				styledTextCommentLData.horizontalAlignment = GridData.FILL;
				styledTextCommentLData.grabExcessHorizontalSpace = true;
				styledTextCommentLData.verticalAlignment = GridData.FILL;
				styledTextCommentLData.grabExcessVerticalSpace = true;
				styledTextComment = new StyledText(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
				styledTextComment.setLayoutData(styledTextCommentLData);
				styledTextComment.setEditable(false);
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fill text fields.
	 *
	 * @param selectedSimulationProject the selected simulation project
	 */
	private void fillTextFields(SimulationProject selectedSimulationProject){
		
		if(selectedSimulationProject != null){
			textName.setText( selectedSimulationProject.getProjectName() );
			textVersion.setText(selectedSimulationProject.getProjectVersion());
			textAuthor.setText(selectedSimulationProject.getProjectAuthor());
			textCompany.setText(selectedSimulationProject.getProjectCompany());
			styledTextComment.setText(selectedSimulationProject.getProjectComment());
		}else{
			textName.setText("");
			textVersion.setText("");
			textAuthor.setText("");
			textCompany.setText("");
			styledTextComment.setText("");
		}
		
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				Display.getDefault().readAndDispatch();
				SimulationProject selectedSimulationProject = Activator
						.getSimulationProjectCenter()
						.getSelectedSimulationProject();
				fillTextFields(selectedSimulationProject);
			}
		});
		
		parentComposite.setSelectionToDataView();
	}
}
