package org.modelica.mdt;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

public class ProjectsView extends ViewPart
{
	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private IResourceChangeListener resourceListener;


	public class ProjectsLabelProvider extends WorkbenchLabelProvider
	{

	}

	public class ProjectsTreeContentProvider implements ITreeContentProvider 
	{

		public Object[] getElements(Object inputElement)
		{
			return getChildren(inputElement);
		}
		
		public void dispose()
		{
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
		{
		}

		public Object[] getChildren(Object parent)
		{
			if (parent instanceof IContainer)
			{
				try
				{
					return ((IContainer)parent).members();
				}
				catch (CoreException e)
				{
					//TODO should we do something here ?
					e.printStackTrace();
				}
			}
			else if (parent instanceof IResource)
			{
				
			}
			return null;
		}

		public Object getParent(Object element)
		{
			return null;
		}

		public boolean hasChildren(Object element)
		{
			if (element instanceof IProject)
			{
				return ((IProject)element).isOpen();
			}
			else if (element instanceof IFolder)
			{
				return true;
			}
			return false;
		}


	}

	@Override
	public void createPartControl(Composite parent)
	{
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		viewer =
			new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		viewer.setContentProvider(new ProjectsTreeContentProvider());
		viewer.setLabelProvider(new ProjectsLabelProvider());
		viewer.setInput(workspace.getRoot());

		drillDownAdapter = new DrillDownAdapter(viewer);
		
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
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceListener);
	}

}
