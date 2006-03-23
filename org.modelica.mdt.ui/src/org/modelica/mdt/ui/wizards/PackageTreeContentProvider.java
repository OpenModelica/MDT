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

package org.modelica.mdt.ui.wizards;

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IParent;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.ui.UIPlugin;

/**
 * Provides content for the tree of all packages in a modelica project. 
 * All packages in the top name space are placed in the root disregarding
 * where they are located in the file system.
 * 
 * Currently packages defined inside source code files (.mo) are filtered
 * out becouse we don't support generating packages inside files right now.
 * This filter hack should be removed in the future.
 * 
 * The only supported inputElement is IModelicaProject. Avoid other types !
 */
public class PackageTreeContentProvider implements ITreeContentProvider
{

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement)
	{
		Collection<Object> filtredChildrenPackages = new Vector<Object>();
		
		try
		{
			Collection<? extends IModelicaElement> children;
			
			if (parentElement instanceof IModelicaProject)
			{
				children = ((IModelicaProject)parentElement).getRootClasses();
			}
			else if (parentElement instanceof IParent) 
			{
				children = ((IParent)parentElement).getChildren();
			}
			else
			{
				/* if we are not a IParent then we don't have any children */
				return Collections.EMPTY_LIST.toArray();
			}

			/* 
			 * filter out everything but folder packages
			 */
			for (Object elm : children)
			{

				if (!(elm instanceof IModelicaClass))
				{
					/* ignore non-class elements */
					continue;
				}
				IModelicaClass classElement = (IModelicaClass) elm;

				if (classElement.getRestriction() != 
					IModelicaClass.Restriction.PACKAGE)
				{
					/* ignore non-package classes */
					continue;
				}

				if (classElement.getResource().getType() ==	IResource.FILE)
				{
					/* ignore packages defined inside a file */
					continue;
				}
				
				filtredChildrenPackages.add(elm);
			}		
		}
		catch (CompilerException e)
		{
			ErrorManager.showCompilerError(e);
		}
		catch (CoreException e)
		{
			ErrorManager.showCoreError(e);
		}
		
		return filtredChildrenPackages.toArray();
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element)
	{
		return null;
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element)
	{
		if (element instanceof IModelicaClass)
		{
			try
			{
				return ((IModelicaClass)element).hasChildren();
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
		
		return false;
	}

	/**
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement)
	{
		if (inputElement instanceof IModelicaProject)
		{
			return getChildren((IModelicaProject)inputElement);
		}

		/* we only support browsing package tree from a project object */
		ErrorManager.logBug(UIPlugin.getSymbolicName(), 
				"input element of unsupported type set");
		return null;
	}

	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose()
	{
		/* NOP */
	}

	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
	{
		/* NOP */
	}
}
