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

package org.modelica.mdt.internal.core;

import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChangeListener;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IStandardLibrary;
import org.modelica.mdt.internal.core.ModelicaProject;
import org.modelica.mdt.core.IModelicaRoot;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

/**
 * The internal implementation of the IModelicaRoot interface.
 * 
 * @author Elmir Jagudin
 */
public class ModelicaRoot implements IModelicaRoot, IResourceChangeListener 
{
	private Hashtable<IProject, IModelicaProject> projectsTable;
	private LinkedList<IModelicaElementChangeListener> listeners;
	
	private StandardLibrary standardLibrary = null;

	/**
	 * @see org.modelica.mdt.core.IModelicaRoot#getProjects()
	 */
	public IModelicaProject[] getProjects() 
	{
		IModelicaProject[] res = 
			new IModelicaProject[projectsTable.values().size()];
		projectsTable.values().toArray(res);

		return res;	
	}
	
	public IModelicaProject getProject(String name)
	{
		for (IModelicaProject proj : projectsTable.values())
		{
			if (proj.getElementName().equals(name))
			{
				return proj;
			}
		}
		return null;
	}


	private void loadProjects()
	{
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		projectsTable = new Hashtable<IProject, IModelicaProject>();
		
		IProject[] projects = workspaceRoot.getProjects();

		/*
		 * wrap all projects with modelica nature into a ModelicProject object
		 */
		for (int i = 0; i < projects.length; i++)
		{
			projectsTable.put(projects[i], 
					new ModelicaProject((IProject)projects[i]));
		}
		
		
	}

	
	public void start()
	{
		loadProjects();

		listeners = new LinkedList<IModelicaElementChangeListener>();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, 
					IResourceChangeEvent.POST_CHANGE);
	}

	public void stop()
	{
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		projectsTable = null;
		listeners = null;
	}	

	/*
	 * org.eclipse.core.resources.IResourceChangeListener interface 
	 */
	public void resourceChanged(IResourceChangeEvent event)
	{
		List<IModelicaElementChange> changes = 
				new LinkedList<IModelicaElementChange>();
		
		for (IResourceDelta delta : event.getDelta().getAffectedChildren())
		{			
			IProject project = (IProject) delta.getResource();

			switch (delta.getKind())
			{
			case IResourceDelta.ADDED:
				changes.addAll(handleResourceAdded(project));
				break;
			case IResourceDelta.REMOVED:
				changes.addAll(handleResourceRemoved(project));
				break;				
			case IResourceDelta.CHANGED:
				changes.addAll(handleResourceChanged(project, delta));
				break;
			}
		}
		
		if (!changes.isEmpty())
		{
			postChangeEvent(changes);
		}
	}

	private List<IModelicaElementChange> handleResourceAdded(IProject project)
	{
		List<IModelicaElementChange> changes = 
				new LinkedList<IModelicaElementChange>();
		ModelicaProject modelicaProject = new ModelicaProject(project);

		projectsTable.put(project, modelicaProject);
		changes.add(new ModelicaElementChange(this, modelicaProject));

		return changes;
	}

	private List<IModelicaElementChange> handleResourceRemoved(IProject project)
	{
		List<IModelicaElementChange> changes = 
			new LinkedList<IModelicaElementChange>();

		IModelicaProject wrappedProject = projectsTable.remove(project);
		changes.add(new ModelicaElementChange(wrappedProject, 
						IModelicaElementChange.ChangeType.REMOVED));

		return changes;
	}

	private List<IModelicaElementChange> handleResourceChanged(IProject project,
			IResourceDelta delta)
	{
		List<IModelicaElementChange> changes = 
			new LinkedList<IModelicaElementChange>();
				
		if (projectsTable == null)
		{
			/* if projects are not loaded, no changes can happen to 'em */
			return changes;
		}

		try
		{
			ModelicaProject modelicaProject = 
				(ModelicaProject) projectsTable.get(project);
			changes.addAll(modelicaProject.update(delta));
		} 
		catch (CompilerException e)
		{
			/*
			 * display error and return whatever changes was added before
			 * the error struck
			 */
			ErrorManager.showCompilerError(e);
		}
		catch (CoreException e)
		{
			/*
			 * display error and return whatever changes was added before
			 * the error struck
			 */
			ErrorManager.showCoreError(e);
		}

		
		return changes;
	}

	/**
	 * post IModelicaElementChange event to all listeners
	 * @param changes the list of changes to post
	 */
	private void postChangeEvent(List<IModelicaElementChange> changes)
	{
		for (IModelicaElementChangeListener listener : listeners)
		{
			listener.elementsChanged(changes);
		}
	}
	
	public void addModelicaElementChangeListener
			(IModelicaElementChangeListener listener)
	{
		listeners.add(listener);
	}

	public void removeModelicaElementChangeListener
			(IModelicaElementChangeListener listener)
	{
		listeners.remove(listener);
	}

	public IModelicaProject createProject(String name)	throws CoreException
	{
		IProject newProject = 
			ResourcesPlugin.getWorkspace().getRoot().getProject(name);
		
		/* create the project */
		newProject.create(null);
		
		/* open project */
		newProject.open(null);
		
		/* add modelica nature to the project */
		CorePlugin.addModelicaNature(newProject);

		/* return a wraped project to the caller */
		return new ModelicaProject(newProject);
	}

	public IStandardLibrary getStandardLibrary() 
	{
		if (standardLibrary  == null)
		{
			standardLibrary = new StandardLibrary();
		}
		return standardLibrary;
	}
	
	
	/**
	 * A utilit methods to look-up a package by it's full name.
	 * 
	 * This method looks in all subtrees of the top elements provided.
	 * 
	 * @param topElements the roots of the elements subtrees to look among
	 * @param packageName the package's full name that we want to locate
	 * @return the package found or null if there were no such package
	 */
	protected static IModelicaClass getPackage
		(Collection<? extends IModelicaElement> topElements, String packageName)
	
		throws ConnectException, CompilerInstantiationException, 
			UnexpectedReplyException, CoreException, InvocationError
	{
		/*
		 * split up the full package names in separate package names
		 * e.g. foo.bar.gazonk into foo, bar and gazonk
		 * 
		 * look up then foo package among the root packages, bar
		 * among the foo's children and gazonk among bar's offspring 
		 */
		LinkedList<? super IModelicaElement> currentChildren =
			new LinkedList<IModelicaElement>();
		currentChildren.addAll(topElements);

		/* iterate over separate package names */
		StringTokenizer pkgNames = new StringTokenizer(packageName, ".");
		String subname;
		IModelicaClass currentParent = null;
		
		while(pkgNames.hasMoreTokens())
		{
			subname = pkgNames.nextToken();
			
			/* look among packages to find the subname */
			currentParent = null;
			while (!currentChildren.isEmpty())
			{
				Object o = currentChildren.removeFirst();
				if (o instanceof IModelicaSourceFile)
				{
					/* we should take a look among files children */
					currentChildren.addAll(((IModelicaSourceFile)o).getChildren());
					continue;
				}
				else if (o instanceof IModelicaFolder)
				{
					/* we should take a look among folders children */
					currentChildren.addAll(((IModelicaFolder)o).getChildren());
					continue;
					
				}
				else if (!(o instanceof IModelicaClass))
				{
					/* skip other elements (files, components, etc) */
					continue;
				}
				
				/* here we know that o is of type IModelicaClass */
				IModelicaClass p = (IModelicaClass) o;

				if (p.getElementName().equals(subname))
				{
					/*
					 * we found our next subpackage,
					 * continiue to look among it's children
					 */
					currentChildren.clear();
					currentChildren.addAll(p.getChildren());
					currentParent = p;
					break;
				}
			}
			
			if (currentParent == null)
			{
				/*
				 * we failed to find our subpackage, the requested packages
				 * does not exsits, bail out
				 */
				break;
			}
		}
		
		return currentParent;

	}

}
