/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Linköpings universitet, Department of
 * Computer and Information Science, PELAB
 *
 * All rights reserved.
 *
 * (The new BSD license, see also
 * http://www.opensource.org/licenses/bsd-license.php)
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * * Neither the name of Linköpings universitet nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.modelica.mdt.ui;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;
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
import org.modelica.mdt.ErrorManager;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.editor.EditorUtility;
import org.modelica.mdt.compiler.CompilerException;

public class ProjectsView extends ViewPart
{
	/* key widgets' tags for abbot */
	public static final String TREE_TAG = "treeParentTag";

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
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
		viewer.setSorter(new ModelicaElementSorter());
		MdtPlugin.tag(viewer.getTree(), TREE_TAG);

        viewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                handleSelectionChanged(event);
            }
        });
		
		drillDownAdapter = new DrillDownAdapter(viewer);
		
		makeContextMenu();
		
		/*
		 * Register viewer with site. This must be done 
		 * before making the actions.
		 */
		IWorkbenchPartSite site = getSite();
		site.registerContextMenu(contextMenu, viewer);
		site.setSelectionProvider(viewer);
		
		viewer.addOpenListener(new IOpenListener() {
			public void open(OpenEvent event) 
			{
				handleOpen(event);
			}
		});
		
		/* call before registering for selection changes */
		makeActions();

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

	protected void handleOpen(OpenEvent event)
	{
		/*
		 * hande open action on a element in the project view tree,
		 * this is usualy a double click on the element
		 */
		IStructuredSelection selecton = 
			(IStructuredSelection) event.getSelection();

		try
		{
			EditorUtility.openInEditor(selecton.getFirstElement());
		} 
		catch (CompilerException e)
		{
			ErrorManager.showCompilerError(e);
		}
		catch (CoreException e)
		{
			ErrorManager.showCoreError(e);
		}
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

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		openProjectAction = new OpenResourceAction(shell);
		viewer.addSelectionChangedListener(openProjectAction);
		workspace.addResourceChangeListener(openProjectAction);
		
		closeProjectAction = new CloseResourceAction(shell);
		viewer.addSelectionChangedListener(closeProjectAction);
		workspace.addResourceChangeListener(closeProjectAction);
					
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

		contextMenu = new MenuManager("#PopupMenu");
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

		deleteAction.selectionChanged((IStructuredSelection) viewer.getSelection());
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		
		/* Other plug-ins can contribute there actions here */
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	@Override
	public void dispose()
	{
		super.dispose();
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		
		workspace.removeResourceChangeListener(openProjectAction);
		workspace.removeResourceChangeListener(closeProjectAction);
	}
}
