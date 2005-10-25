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

package org.modelica.mdt;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaRoot;
import org.modelica.mdt.core.IParent;

/**
 * @author Elmir Jagudin
 *
 */
public class ModelicaElementContentProvider implements ITreeContentProvider 
{

	public Object[] getElements(Object inputElement)
	{
		try
		{
			if (inputElement instanceof IModelicaRoot)
			{
				return ((IModelicaRoot)inputElement).getProjects();
			}
			
		}
		catch (CoreException e)
		{
			MdtPlugin.log(e);
		}
		return new Object[] {};
	}
	
	public void dispose()
	{
		System.out.println("disposed " + ModelicaElementContentProvider.class);
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
	{
		System.out.println("input changed " + ModelicaElementContentProvider.class);
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
				MdtPlugin.log(e);
			}
		}
		else if (parent instanceof IModelicaProject)
		{
			IModelicaProject mp = (IModelicaProject)parent; 
			return MdtPlugin.concatenate(mp.getPackages(), mp.getClasses());
		}
		else if (parent instanceof IParent)
		{
			System.out.println("get children of " + parent);
			Object[] ch = ((IParent)parent).getChildren();
			for (Object ob : ch)
			{
				System.out.println("got " + ob.getClass());
			}
			
			return ch;
			//return ((IParent)parent).getChildren();
		}
		return null;
	}

	public Object getParent(Object element)
	{
		System.out.println("get parent " + ModelicaElementContentProvider.class);
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
			return true;
		}
		return false;
	}
}
