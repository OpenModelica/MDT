package org.modelica.mdt.ui;

import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CloseResourceAction;
import org.eclipse.ui.actions.DeleteResourceAction;
import org.eclipse.ui.actions.NewWizardMenu;
import org.eclipse.ui.actions.OpenResourceAction;
import org.eclipse.ui.ide.IDEActionFactory;
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
	
	private OpenResourceAction openProjectAction;
	private CloseResourceAction closeProjectAction;
	private DeleteResourceAction deleteAction;
    
    /* handes the context menu in this view */ 
    private MenuManager contextMenu;
    private MenuManager newSubMenu;

	@Override
	public void createPartControl(Composite parent)
	{
		/* create tree viewer */
		viewer =
			new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		
		viewer.setContentProvider(new ModelicaElementContentProvider());
		viewer.setLabelProvider(new WorkbenchLabelProvider());
		viewer.setInput(ModelicaCore.getModelicaRoot());
		MdtPlugin.tag(viewer.getTree(), TREE_TAG);

        viewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                handleSelectionChanged(event);
            }
        });
		
		drillDownAdapter = new DrillDownAdapter(viewer);
		
		makeContextMenu();
		
		// Register viewer with site. This must be done before making the actions.
		IWorkbenchPartSite site = getSite();
		site.registerContextMenu(contextMenu, viewer);
		site.setSelectionProvider(viewer);
		
		makeActions(); // call before registering for selection changes

		/*
		 * make Project->Open Project and Project->Close Project main menus
		 * work in this view also, hehe
		 */
		IActionBars actionBars = getViewSite().getActionBars();
		actionBars.setGlobalActionHandler(IDEActionFactory.OPEN_PROJECT.getId(),
				openProjectAction);		
		actionBars.setGlobalActionHandler(IDEActionFactory.CLOSE_PROJECT.getId(),
				closeProjectAction);

				
		
		hookDoubleClickAction();
		viewer.getTree().addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				ProjectsView.this.handleKeyPressed(e);
			}
			
		});
	}

	protected void handleSelectionChanged(SelectionChangedEvent event)
	{
		
		getViewSite().getActionBars().updateActionBars();
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

		openProjectAction = new OpenResourceAction(shell);
		viewer.addSelectionChangedListener(openProjectAction);
		closeProjectAction = new CloseResourceAction(shell);
		viewer.addSelectionChangedListener(closeProjectAction);
					
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
	
	private void makeContextMenu() 
	{
		newSubMenu = new MenuManager("New");
		newSubMenu.add(new NewWizardMenu(getSite().getWorkbenchWindow()));

		contextMenu = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(new IMenuListener()
		{
			public void menuAboutToShow(IMenuManager manager)
			{
				ProjectsView.this.fillContextMenu(manager);
			}
		});

		Menu menu = contextMenu.createContextMenu(viewer.getTree());
		viewer.getTree().setMenu(menu);
	
	}
	
	private void fillContextMenu(IMenuManager manager) 
	{
		manager.add(newSubMenu);
		manager.add(new Separator());
		manager.add(deleteAction);
		manager.add(new Separator());		
		manager.add(openProjectAction);
		manager.add(closeProjectAction);

		
//		openProjectAction.selectionChanged((IStructuredSelection) viewer.getSelection());
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
