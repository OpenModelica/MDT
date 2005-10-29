package org.modelica.mdt.ui;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeViewer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.modelica.mdt.core.ModelicaCore;

public class ProjectsView extends ViewPart
{
	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private IResourceChangeListener resourceListener;
	private ProjectsViewDoubleClickAction doubleClickAction;


	@Override
	public void createPartControl(Composite parent)
	{
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		viewer =
			new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		viewer.setContentProvider(new ModelicaElementContentProvider());
		viewer.setLabelProvider(new WorkbenchLabelProvider());
		viewer.setInput(ModelicaCore.getModelicaRoot());

		drillDownAdapter = new DrillDownAdapter(viewer);
		
		makeActions();
		hookDoubleClickAction();
		hookContextMenu();
		
		resourceListener = new IResourceChangeListener()
		{
		    public void resourceChanged(IResourceChangeEvent event)
		    {
		        if (event.getType() != IResourceChangeEvent.POST_CHANGE)
		        {
		             return;
		        }

		        //TODO this must be made more selective
		    	// a list of objects (tree-nodes) need to be made
		    	// from resource delta and posted to UI thread
		    	// se http://www.eclipse.org/articles/Article-Resource-deltas/resource-deltas.html
   			    getViewSite().getShell().getDisplay().asyncExec(new Runnable()
		        {
		    		 public void run()
		    		 {
		    			 viewer.refresh(true);
		    		 }
		    	});
		    }
		};
		workspace.addResourceChangeListener(resourceListener);
	}

	@Override
	public void setFocus()
	{
		viewer.getTree().setFocus();
	}
	
	private void hookContextMenu()
	{
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener()
		{
			public void menuAboutToShow(IMenuManager manager)
			{
				ProjectsView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}
	
	private void makeActions() 
	{
		doubleClickAction = new ProjectsViewDoubleClickAction(viewer); 
	}

	private void hookDoubleClickAction() 
	{
		viewer.addDoubleClickListener(new IDoubleClickListener() 
		{
			public void doubleClick(DoubleClickEvent event) 
			{
				doubleClickAction.run();
			}
		});
	}
	
	private void fillContextMenu(IMenuManager manager) 
	{
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	@Override
	public void dispose()
	{
		super.dispose();

		ResourcesPlugin.getWorkspace().
			removeResourceChangeListener(resourceListener);
	}

}
