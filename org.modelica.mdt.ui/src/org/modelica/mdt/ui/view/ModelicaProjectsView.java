/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Link�pings universitet, Department of
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
 * * Neither the name of Link�pings universitet nor the names of its
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

package org.modelica.mdt.ui.view;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.resources.IncrementalProjectBuilder;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CloseResourceAction;
import org.eclipse.ui.actions.DeleteResourceAction;
import org.eclipse.ui.actions.NewWizardMenu;
import org.eclipse.ui.actions.OpenResourceAction;
import org.eclipse.ui.actions.BuildAction;
import org.eclipse.ui.actions.OpenWithMenu;
import org.eclipse.ui.actions.RenameResourceAction;

import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.ide.ResourceUtil;
import org.eclipse.ui.part.DrillDownAdapter;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.ui.ModelicaElementContentProvider;
import org.modelica.mdt.ui.ModelicaElementComparator;
import org.modelica.mdt.ui.UIPlugin;
import org.modelica.mdt.ui.editor.EditorUtility;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.views.navigator.ResourceNavigator;

public class ModelicaProjectsView extends ResourceNavigator
{
	public static final String ID = "org.modelica.mdt.ui.view.ModelicaProjectsView";
	
	/* key widgets' tags for abbot */
	public static final String TREE_TAG = "treeParentTag";

	//private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private ModelicaProjectsViewDoubleClickAction doubleClickAction;
	
	private OpenResourceAction openProjectAction;
	private CloseResourceAction closeProjectAction;
	private DeleteResourceAction deleteAction;
	private BuildAction incrementalBuildAction;
	private BuildAction fullBuildAction;
	private RenameResourceAction renameResourceAction;
	private OpenWithMenu openWithMenuAction;
	
	
    /* handes the context menu in this view */ 
    private MenuManager contextMenu;
    private MenuManager newSubMenu;

	@Override
	public void createPartControl(Composite parent)
	{
		super.createPartControl(parent);
		
		getViewer().setContentProvider(new ModelicaElementContentProvider());
		getViewer().setInput(ModelicaCore.getModelicaRoot());
		getViewer().setComparator(new ModelicaElementComparator(ModelicaElementComparator.MODELICA));
		UIPlugin.tag(getViewer().getTree(), TREE_TAG);

		drillDownAdapter = new DrillDownAdapter(getViewer());
		
		makeContextMenu();
		
		/*
		 * Register getViewer() with site. This must be done 
		 * before making the actions.
		 */
		IWorkbenchPartSite site = getSite();
		site.registerContextMenu(contextMenu, getViewer());
		site.setSelectionProvider(getViewer());
		
		
		getViewer().addOpenListener(new IOpenListener() {
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
		actionBars.setGlobalActionHandler(IDEActionFactory.OPEN_PROJECT.getId(),  openProjectAction);		
		actionBars.setGlobalActionHandler(IDEActionFactory.CLOSE_PROJECT.getId(), closeProjectAction);
		actionBars.setGlobalActionHandler(IDEActionFactory.BUILD_PROJECT.getId(), fullBuildAction);
		
		hookDoubleClickAction();
	}
		
	protected void handleOpen(OpenEvent event)
	{
		/*
		 * hande open action on a element in the project view tree,
		 * this is usualy a double click on the element
		 */
		IStructuredSelection selection = (IStructuredSelection) event.getSelection();

		try
		{
			EditorUtility.openInEditor ((IModelicaElement)selection.getFirstElement());
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
	
	
	public void makeActions() 
	{
		super.makeActions();
		
		doubleClickAction = new ModelicaProjectsViewDoubleClickAction(getViewer());
		
		Shell shell = getViewSite().getShell();
		ISharedImages images = PlatformUI.getWorkbench().getSharedImages();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
				
		renameResourceAction = new RenameResourceAction(shell);
		getViewer().addSelectionChangedListener(renameResourceAction);

		openProjectAction = new OpenResourceAction(shell);
		getViewer().addSelectionChangedListener(openProjectAction);
		workspace.addResourceChangeListener(openProjectAction);
		
		closeProjectAction = new CloseResourceAction(shell);
		getViewer().addSelectionChangedListener(closeProjectAction);
		workspace.addResourceChangeListener(closeProjectAction);
					
		deleteAction = new DeleteResourceAction(shell);
		
		deleteAction.setDisabledImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
		deleteAction.setImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));

		fullBuildAction = new BuildAction(shell, IncrementalProjectBuilder.FULL_BUILD);
		getViewer().addSelectionChangedListener(fullBuildAction);

		incrementalBuildAction = new BuildAction(shell, IncrementalProjectBuilder.INCREMENTAL_BUILD);
		getViewer().addSelectionChangedListener(incrementalBuildAction);
		
	}

	private void hookDoubleClickAction() 
	{
		getViewer().addDoubleClickListener(new IDoubleClickListener() 
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
				ModelicaProjectsView.this.fillContextMenu(manager);
			}
		});

		Menu menu = contextMenu.createContextMenu(getViewer().getTree());
		getViewer().getTree().setMenu(menu);
	}
	
	public void fillContextMenu(IMenuManager manager) 
	{
		super.fillContextMenu(manager);
		manager.add(new Separator());
		try
		{
			IStructuredSelection selection = (IStructuredSelection)getViewer().getSelection();
			if (selection != null && !selection.isEmpty())
			{
				IModelicaElement file = (IModelicaElement)selection.getFirstElement();
				IResource x = file.getResource();
				openWithMenuAction = new OpenWithMenu(getSite().getPage(), x);
				manager.add(openWithMenuAction);
			}
		}
		catch(NullPointerException e)
		{
			// do nothing
			e.printStackTrace();
		}
		catch(ClassCastException e)
		{
			// do nothing
			e.printStackTrace();
		}	
		manager.add(renameResourceAction);		
		renameResourceAction.selectionChanged((IStructuredSelection) getViewer().getSelection());
		manager.add(new Separator());
		manager.add(incrementalBuildAction);
		incrementalBuildAction.selectionChanged((IStructuredSelection) getViewer().getSelection());
		manager.add(fullBuildAction);
		fullBuildAction.selectionChanged((IStructuredSelection) getViewer().getSelection());
		manager.add(openProjectAction);
		openProjectAction.selectionChanged((IStructuredSelection) getViewer().getSelection());
		manager.add(closeProjectAction);
		closeProjectAction.selectionChanged((IStructuredSelection) getViewer().getSelection());
		manager.add(new Separator());
		manager.add(deleteAction);
		deleteAction.selectionChanged((IStructuredSelection) getViewer().getSelection());
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);		
		/* Other plug-ins can contribute their actions here */
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

    /**
     * An editor has been activated.  Sets the selection in this navigator
     * to be the editor's input, if linking is enabled.
     * 
     * @param editor the active editor
     */
    protected void editorActivated(IEditorPart editor) {
        if (!isLinkingEnabled()) {
            return;
        }
        IFile file = ResourceUtil.getFile(editor.getEditorInput());
        if (file != null) {
            ISelection newSelection = new StructuredSelection(file);
            if (getTreeViewer().getSelection().equals(newSelection)) {
                getTreeViewer().getTree().showSelection();
            } else {
                getTreeViewer().setSelection(newSelection, true);
            }
        }
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
