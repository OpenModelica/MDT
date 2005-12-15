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

package org.modelica.mdt.ui;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.modelica.mdt.ErrorManager;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChangeListener;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaRoot;
import org.modelica.mdt.core.IParent;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.internal.core.SystemLibrary;
import org.modelica.mdt.internal.omcproxy.CompilerException;

/**
 * Content provider for a tree viewer. This content provider works only
 * whith TreeViewer:s to keep things simple. If you want to use it for
 * other viewers some addditional hacking is required on this class.
 *  
 * @author Elmir Jagudin
 */
public class ModelicaElementContentProvider 
	implements ITreeContentProvider, IModelicaElementChangeListener 
{
	private TreeViewer viewer;
	
	public ModelicaElementContentProvider()
	{
		/* we are interested in changes to modelica elements tree */
		ModelicaCore.getModelicaRoot().addModelicaElementChangeListener(this);
	}
	
	public Object[] getElements(Object inputElement)
	{
		if (inputElement instanceof IModelicaRoot)
		{
			return ((IModelicaRoot)inputElement).getProjects();
		}
		
		/* this is not happening */
		return new Object[] {};
	}
	
	public void dispose()
	{
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
	{
		this.viewer = (TreeViewer)viewer;
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
				ErrorManager.logError(e);
			}
		}
		else if (parent instanceof IModelicaProject)
		{
			IModelicaProject modelicaProj = 
				(IModelicaProject)parent;
			if ( !modelicaProj.getProject().isOpen())
			{
				/* we have no children if we are closed */
				return new Object[0];
			}

			Collection<Object> list = null;
			boolean hasModelicaNature = false;
			try
			{
				list = 
					modelicaProj.getRootFolder().getChildren();
				
				hasModelicaNature = modelicaProj.getProject().
					getDescription().hasNature(MdtPlugin.MODELICA_NATURE);
			}
			catch (CompilerException e)
			{
				/* on OMC error, show message and return empty children array */
				ErrorManager.showCompilerError(e);
				return new Object[0];
			}
			catch (CoreException e)
			{
				/* on core error, show message and return empty children array */
				ErrorManager.showCoreError(e);
				return new Object[0];
			}

			if (!hasModelicaNature)
			{
				/* don't add system library to non modelica projects */
				return list.toArray();
			}
			
			Object[] children = new Object[list.size()+1];
			/*
			 * add as last element system library
			 */
			list.toArray(children);
			children[children.length-1] = new SystemLibrary();
			return children; 			
		}
		else if (parent instanceof IParent)
		{
			try
			{
				return ((IParent)parent).getChildren().toArray();
			}
			catch (CompilerException e)
			{
				/* on OMC error, show message and return empty children array */
				ErrorManager.showCompilerError(e);
				return new Object[0];
			}
			catch (CoreException e)
			{
				/* on core error, show message and return empty children array */
				ErrorManager.showCoreError(e);
				return new Object[0];
			}
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
		else if (element instanceof IModelicaProject)
		{
			return ((IModelicaProject)element).getProject().isOpen();
		}
		else if (element instanceof IParent)
		{
			try 
			{
				return ((IParent)element).hasChildren();
			} 
			catch (CoreException e) 
			{
				/* 
				 * on Core error, show message and return that 
				 * there are no children 
				 */
				ErrorManager.showCoreError(e);
				return false;
			}
			catch (CompilerException e)
			{
				/* 
				 * on OMC error, show message and return that 
				 * there are no children 
				 */
				ErrorManager.showCompilerError(e);
				return false;
			}
		}
		return false;
	}

	/*
	 * IModelicaElementChangeListener interface method
	 */
	public void elementsChanged(final List<IModelicaElementChange> changes)
	{
		Control ctrl = viewer.getControl();
		
		if (ctrl == null || ctrl.isDisposed())
		{
			return;
		}
		if (ctrl.getDisplay().getThread() == Thread.currentThread()) 
		{
			handleChanges(changes);
		}
		else
		{			
			ctrl.getDisplay().asyncExec(new Runnable()
			{
				public void run() 
				{
					/* Abort if this happens after disposes */
					Control ctrl = viewer.getControl();
					if (ctrl == null || ctrl.isDisposed())
						return;
					handleChanges(changes);
				}
			});
		}
	}

	protected void handleChanges(List<IModelicaElementChange> changes)
	{
		  for (IModelicaElementChange change : changes)
		  {
			  Object element = change.getElement();

			  switch (change.getChangeType())
			  {
			  case ADDED:
				  viewer.add(change.getParent(), element);
				  break;
			  case CHANGED:
				  viewer.update(element, null);
				  break;
			  case REMOVED:
				  viewer.remove(element);
				  break;
			  case OPENED:
			  case CLOSED:
				  viewer.refresh(element);
				  break;
			  }
		  }
	}
}
