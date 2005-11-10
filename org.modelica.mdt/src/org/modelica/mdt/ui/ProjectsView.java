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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.DeleteResourceAction;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.core.ModelicaCore;

public class ProjectsView extends ViewPart
{
	/* key widgets' tags for abbot */
	public static final String TREE_TAG = "treeParentTag";

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private IResourceChangeListener resourceListener;
	private ProjectsViewDoubleClickAction doubleClickAction;
    private DeleteResourceAction deleteAction;

	@Override
	public void createPartControl(Composite parent)
	{
		/* create tree viewer */
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		viewer =
			new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		viewer.setContentProvider(new ModelicaElementContentProvider());
		viewer.setLabelProvider(new WorkbenchLabelProvider());
		viewer.setInput(ModelicaCore.getModelicaRoot());
		MdtPlugin.tag(viewer.getTree(), TREE_TAG);

		drillDownAdapter = new DrillDownAdapter(viewer);
		
		MenuManager menuMgr= new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener()
		{
			public void menuAboutToShow(IMenuManager manager)
			{
				ProjectsView.this.fillContextMenu(manager);
			}
		});

		Menu contextMenu = menuMgr.createContextMenu(viewer.getTree());
		viewer.getTree().setMenu(contextMenu);
		
		// Register viewer with site. This must be done before making the actions.
		IWorkbenchPartSite site= getSite();
		site.registerContextMenu(menuMgr, viewer);
		site.setSelectionProvider(viewer);
		
		makeActions(); // call before registering for selection changes
		
		hookDoubleClickAction();
		
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
		
		viewer.getTree().addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				ProjectsView.this.handleKeyPressed(e);
			}
			
		});
		workspace.addResourceChangeListener(resourceListener);
	}

	protected void handleKeyPressed(KeyEvent e) 
	{
		switch(e.character)
		{
		case SWT.DEL:
			deleteAction.selectionChanged((IStructuredSelection) viewer.getSelection());
			if (deleteAction.isEnabled())
			{
				deleteAction.run();
			}
			break;
		}
	}

	@Override
	public void setFocus()
	{
		viewer.getTree().setFocus();
	}
	
	
	private void makeActions() 
	{
		doubleClickAction = new ProjectsViewDoubleClickAction(viewer);
		
		Shell shell = getSite().getShell();
		ISharedImages images = PlatformUI.getWorkbench().getSharedImages();
//		clipboard = new Clipboard(shell.getDisplay());
//
//		pasteAction = new PasteAction(shell, clipboard);
//		pasteAction.setDisabledImageDescriptor(images
//				.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
//		pasteAction.setImageDescriptor(images
//	                .getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));

//		copyAction = new CopyAction(shell, clipboard, pasteAction);
//		copyAction.setDisabledImageDescriptor(images
//	                .getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
//		copyAction.setImageDescriptor(images
//	                .getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
//
//		moveAction = new ResourceNavigatorMoveAction(shell, treeViewer);
//		renameAction = new ResourceNavigatorRenameAction(shell, treeViewer);

		deleteAction = new DeleteResourceAction(shell);
		deleteAction.setDisabledImageDescriptor(images
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
		deleteAction.setImageDescriptor(images
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));

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
		manager.add(deleteAction);
		deleteAction.selectionChanged((IStructuredSelection) viewer.getSelection());
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
