/*
 * 
 */
package org.openmodelica.simulation.environment.view.project;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.part.ViewPart;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * Simple View containing a tree
 * WILL ONLY BE USED AS DEMONSTRATION AND TESTING.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */
public class SimulationProjectListingView extends ViewPart {
	
	/**
	 * The ID of the view as specified by the extension.
	 */
//	public static final String ID = "eads.simulation.modelica.views.project.projectlisting";
	
	private Composite compositeProjectTree;
	
	/** The tree projects. */
	private Tree treeProjects;

	/**
	 * Instantiates a new simulation project listing view.
	 */
	public SimulationProjectListingView() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		{
			compositeProjectTree = new Composite(parent, SWT.NONE);
			GridLayout compositeProjectTreeLayout = new GridLayout();
			compositeProjectTreeLayout.makeColumnsEqualWidth = true;
			compositeProjectTree.setLayout(compositeProjectTreeLayout);
			{
				GridData treeProjectsLData = new GridData();
				treeProjectsLData.horizontalAlignment = GridData.FILL;
				treeProjectsLData.verticalAlignment = GridData.FILL;
				treeProjectsLData.grabExcessHorizontalSpace = true;
				treeProjects = new Tree(compositeProjectTree, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL
				        | SWT.H_SCROLL);
				treeProjects.setLayoutData(treeProjectsLData);
				treeProjects.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						TreeItem ti = treeProjects.getSelection()[0];
						Activator.getSimulationProjectCenter().setSelectedSimulationProject((String)ti.getData());
					}
				});
				{ //Fill the tree with all available projects
					for(String simprojName : Activator.getSimulationProjectCenter().getSimulationProjects()){
						
						Calendar cal = Calendar.getInstance(); 
						cal.setTimeInMillis(Activator.getSimulationProjectCenter().getFileLastModifiedDate(simprojName));
						DateFormat formatter = new SimpleDateFormat();
						
						String date = (formatter.format(cal.getTime()));
						TreeItem projectItem = new TreeItem(treeProjects, 0);
						projectItem.setText(simprojName + " [" + date + "]"); 
						projectItem.setData(simprojName);
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
