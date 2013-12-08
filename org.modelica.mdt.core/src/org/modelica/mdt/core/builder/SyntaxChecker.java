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

package org.modelica.mdt.core.builder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceRuleFactory;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.ICompileError;
import org.modelica.mdt.core.compiler.IParseResults;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.core.preferences.PreferenceManager;
import org.modelica.mdt.internal.core.CorePlugin;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.internal.core.FolderPackage;


/**
 * This builder loads all changed files into OMC in order to check for
 * parse errors. If a loaded file have any parse error, then a problem
 * marker is set to communicate the problem to the user.
 *
 * @author Andreas Remar
 */
public class SyntaxChecker extends IncrementalProjectBuilder
{

	public static boolean DEBUG = false;

	public static final String BUILDER_ID = "org.modelica.mdt.core.syntaxChecker";

	@Override
	protected IProject[] build(int kind, @SuppressWarnings("rawtypes") Map args, IProgressMonitor monitor) throws CoreException
	{
		switch(kind)
		{
		case IncrementalProjectBuilder.FULL_BUILD:
			fullBuild(monitor);
			break;

		default:
			IResourceDelta delta = getDelta(getProject());
			if(delta == null)
			{
				fullBuild(monitor);
			}
			else
			{
				incrementalBuild(delta, monitor);
			}
			break;
		}

		return null;
	}

	/*
	 * Performs a full build on the project.
	 */
	protected void fullBuild(final IProgressMonitor monitor)
	{
		try
		{
			getProject().accept(new FullBuildVisitor());
		}
		catch(CoreException e)
		{
			ErrorManager.showCoreError(e);
		}
	}

	/*
	 * Performs an incremental build on a project given a resource delta of
	 * changes.
	 */
	protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor)
	{
		try
		{
			delta.accept(new PartialBuildVisitor());
		}
		catch(CoreException e)
		{
			ErrorManager.showCoreError(e);
		}
	}

	/**
	 * Adds this builder to the project
	 * @param project the project to add this builder to
	 */
	public static void addBuilderToProject(IProject project)
	{
		if(!project.isOpen())
		{
			return;
		}

		IProjectDescription description;
		try
		{
			description = project.getDescription();
		}
		catch(CoreException e)
		{
			ErrorManager.logError(e);
			return;
		}

		/*
		 * Check if builder is already associated with this
		 * project.
		 */
		ICommand[] cmds = description.getBuildSpec();
		for(ICommand cmd : cmds)
		{
			if(cmd.getBuilderName().equals(BUILDER_ID))
			{
				return;
			}
		}

		/*
		 * Associate builder with project.
		 */
		ICommand newCmd = description.newCommand();
		newCmd.setBuilderName(BUILDER_ID);
		List<ICommand> newCmds = new ArrayList<ICommand>();
		newCmds.addAll(Arrays.asList(cmds));
		newCmds.add(newCmd);
		description.setBuildSpec(
				(ICommand[])newCmds.toArray(
						new ICommand[newCmds.size()]));
		try
		{
			project.setDescription(description, null);
		}
		catch(CoreException e)
		{
			ErrorManager.logError(e);
		}
	}

	/**
	 *
	 * @param project the project to remove this builder from
	 */
	public static void removeBuilderFromProject(IProject project)
	{
		if(!project.isOpen())
		{
			return;
		}

		IProjectDescription description;
		try
		{
			description = project.getDescription();
		}
		catch(CoreException e)
		{
			ErrorManager.logError(e);
			return;
		}

		int index = -1;
		ICommand[] cmds = description.getBuildSpec();
		for(int j = 0;j < cmds.length;j++)
		{
			if(cmds[j].getBuilderName().equals(BUILDER_ID))
			{
				index = j;
				break;
			}
		}

		if(index == -1)
		{
			return;
		}

		List<ICommand> newCmds = new ArrayList<ICommand>();
		newCmds.addAll(Arrays.asList(cmds));
		newCmds.remove(index);
		description.setBuildSpec(
				(ICommand[])newCmds.toArray(new ICommand[newCmds.size()]));
		try
		{
			project.setDescription(description, null);
		}
		catch(CoreException e)
		{
			ErrorManager.logError(e);
		}
	}

	/**
	 *
	 * @param file the file where the marker should be added
	 * @param lineno where in the file this marker should be set
	 * @param msg a message indicating the problem
	 */
	protected static void reportProblem(IFile file, ICompileError error)
	{
		createMarkerAtLocation(file,
							   error.getStartLine(), error.getStartColumn(),
							   error.getEndLine(), error.getEndColumn(),
							   error.getErrorDescription(), IMarker.PROBLEM);
	}

	protected static void reportNamespaceProblem(IFile file, String className)
	{
		createMarkerAtLocation(file, 1, 1, 1, 1,
				className + " defined in an unexpected namespace",
				CorePlugin.UNEXPECTED_NAMESPACE_MARKER_ID);
	}

	/**
	 * Calculate where the particular line begins and how long it stretches.
	 *  @deprecated
	 * @param filePath the full path to the file where to look for lines
	 * @param lineno line number to find
	 * @return region which line occupies or null if the does not
	 * have such a line number
	 * @throws CoreException if there were errors reading file contents
	 * @throws FileNotFoundException if the filePath does not exists
	 */

	public static IRegion getLineRegion(String filePath, int lineno)
		throws CoreException, FileNotFoundException
	{
		// TODO this probably should be moved into ModelicaFile class,
		// however that would require that IFile -> IModelicaFile mapping
		// is implemented. such a mupping is probably needed anyway to
		// know which other files that should be loaded into OMC and checked
		// for errors. that is all dependant files, e.g. if file A defines foo
		// and file B imports foo both A and B should be reshecked for errors
		// when A is modified (saved), right now only A is checked. end of rant.

		/*
		 * To find out where the line is in the file, we have to read it
		 * in to a Document and then use getLineOffset to convert from a
		 * line number to a character positions.
		 */
		return getLineRegion(new FileInputStream(new File(filePath)), lineno);
	}

	/**
	 * Wrapper function to get the region given an IFile and a line number
	 * @deprecated
	 * @param file the file that contains the line we're interested in
	 * @param lineno the line number to find
	 * @return region that this line occupies, or <code>null</code> if no such
	 * line was found in the file
	 * @throws CoreException
	 */
	public static IRegion getLineRegion(IFile file, int lineno)
		throws CoreException
	{
		return getLineRegion(file.getContents(), lineno);
	}


	/**
	 * Tries to calculate a region for a given file and line number.
	 * @deprecated
	 * @param fileContents contents of the file that we want to search for line
	 * @param lineno the line number to find
	 * @return region that this line occupies, or <code>null</code> if no such
	 * line was found in the file
	 */
	private static IRegion getLineRegion(InputStream fileContents, int lineno)
	{
		BufferedInputStream bis = new BufferedInputStream(fileContents);
		String contents = "";

		/*
		 * Read in contents of the file.
		 */
		while(true)
		{
			try
			{
				int avail = bis.available();
				if(avail == 0)
				{
					break;
				}
				byte[] buf = new byte[avail];
				bis.read(buf, 0, avail);

				contents += new String(buf);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}

		/*
		 * Convert contents of the file to a document.
		 */
		Document d = new Document(contents);

		/*
		 * Try to find the region where the line is.
		 */
		try
		{
			return new Region(d.getLineOffset(lineno - 1), d.getLineLength(lineno - 1));
		}
		catch(BadLocationException e)
		{
			ErrorManager.logError(e);
		}

		return null;
	}

	/**
	 *
	 * @param file the file where this marker should be set
	 * @param startLineNumber the line number where this marker should start
	 * @param startColumnNumber the column number where this marker should start
	 * @param endLineNumber the line number where this marker should end
	 * @param endColumnNumber the column number where this marker should end
	 * @param message a message describing this marker
	 * @param type the type of the marker (problem, info, ...)
	 * @return the created marker, or <code>null</code> if marker couldn't
	 * 		   be created
	 */
	public static void createMarkerAtLocation(final IResource resource,
			final int startLineNumber, final int startColumnNumber,
			final int endLineNumber, final int endColumnNumber,
			final String message, final String type)
	{
			IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					IMarker marker = null;
					marker = resource.createMarker(type);

					/*
					IRegion startLineReg = null;
					IRegion endLineReg = null;
					try
					{
					 startLineReg = getLineRegion(resource.getLocation().toOSString(), startLineNumber);
					 endLineReg = getLineRegion(resource.getLocation().toOSString(), endLineNumber);
					}
					catch(FileNotFoundException e)
					{
						throw
							new CoreException(
									new Status(IStatus.ERROR, "org.modelica.mdt.core", IStatus.OK, "File Not Found", (Throwable)e));
					}

					int start = startLineReg.getOffset() + startColumnNumber - 1;
					int end = endLineReg.getOffset() + endColumnNumber - 1;
					if(startLineNumber == endLineNumber
							&& startColumnNumber == endColumnNumber)
					{
						end++;
					}



					marker.setAttribute(IMarker.CHAR_START, start);
					marker.setAttribute(IMarker.CHAR_END, end);
					*/
					marker.setAttribute(IMarker.LINE_NUMBER, startLineNumber);
					marker.setAttribute(IMarker.MESSAGE, message);
					marker.setAttribute(IMarker.LINE_NUMBER, startLineNumber);
					marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
					marker.setAttribute(IMarker.LOCATION, Integer.toString(startLineNumber));
				}
			};

		try
		{
			run(getMarkerRule(resource), runnable);
		}
		catch(CoreException e)
		{
			ErrorManager.logError(e);
		}
	}

    protected static ISchedulingRule getMarkerRule(IResource resource) {
        ISchedulingRule rule = null;
        if (resource != null) {
            IResourceRuleFactory ruleFactory = ResourcesPlugin.getWorkspace().getRuleFactory();
            rule = ruleFactory.markerRule(resource);
        }
        return rule;
    }

    /**
	 * Execute the given workspace runnable with the scheduling rule to use when running the operation.
	 *
	 * @param rule the rule to use when running the operation
     * @param wr the runnable operation
     * @throws DebugException If a core exception occurs performing the operation
	 * @since 3.1
	 */
    protected static void run(final ISchedulingRule rule, final IWorkspaceRunnable wr) throws CoreException {
    	Thread t = new Thread()
    	{
    		public void run()
    		{
    			try{
    			ResourcesPlugin.getWorkspace().run(wr, rule, IWorkspace.AVOID_UPDATE, null);
    			}
    			catch (CoreException e) {
    				ErrorManager.logError(e);
				}
    		}
    	};
    	t.start();
    }

	protected void startupOnInitialization()
	{
		/* NOP */
	}

	protected void clean(IProgressMonitor monitor)
	{
		/* NOP */
	}

	/**
	 *
	 * @param file the file we should load into OMC to check for errors
	 * @throws ConnectException
	 * @throws UnexpectedReplyException
	 * @throws CompilerInstantiationException
	 */
	public static IParseResults loadFileAndReportErrors(final IFile file, boolean checkForNamespaceProblems)
		throws ConnectException, UnexpectedReplyException,
				CompilerInstantiationException
	{
		/* schedule a marker delete */
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				file.deleteMarkers(IMarker.PROBLEM, false, IResource.DEPTH_INFINITE);
				file.deleteMarkers(CorePlugin.UNEXPECTED_NAMESPACE_MARKER_ID, false, IResource.DEPTH_INFINITE);
			}
		};

		try
		{
			run(getMarkerRule(file), runnable);
		}
		catch(CoreException e)
		{
			ErrorManager.logError(e);
		}

		IParseResults res = null;

		// if is not on the ban list, load it!
		if (!PreferenceManager.isAnIgnoredDirectoryOrFile(file.getFullPath().toString()))
		{
			/*
			 * Try loading the file into OMC and get the results.
			 */
			res = CompilerProxy.loadSourceFile(file);

			if (res == null) return res;
			/*
			 * If there were any compile errors, report them as problems.
			 */
			for (ICompileError error : res.getCompileErrors())
			{
				reportProblem(file, error);
			}
		}

		/*
		 * Make sure that everything was defined in the correct namespace.
		 */
		if (checkForNamespaceProblems)
			checkForNamespaceProblems(file, res);

		return res;
	}

	/**
	 * Check a file for missing or malformed 'within' statements by comparing
	 * where in the file hierarchy a file is defined and the actual contents
	 * returned from OMC.
	 *
	 * @param file the file that has been loaded into OMC
	 * @param res the compilation result from having loaded the file
	 * @throws ConnectException
	 * @throws UnexpectedReplyException
	 * @throws CompilerInstantiationException
	 */
	private static void checkForNamespaceProblems(IFile file, IParseResults res)
		throws ConnectException, UnexpectedReplyException,
			CompilerInstantiationException
	{
		IModelicaProject project = ModelicaCore.getModelicaRoot().getProject(file.getProject().getName());

		IModelicaElement element = null;
		try
		{
			element = project.findElement(file.getProjectRelativePath());
		}
		catch (InvocationError e)
		{
			ErrorManager.logError(e);
		}
		catch (CoreException e)
		{
			ErrorManager.logError(e);
		}

		IModelicaElement parent = element.getParent();
		String ppName = parent.getFullName();

		/*
		 * If the parent is a package, the elements inside should be defined
		 * as being children of the parent.
		 */
		if(parent instanceof FolderPackage)
		{
			String filePath = file.getLocation().toString();
			if(filePath.endsWith(File.separator + "package.mo"))
			{
				for(String name : res.getClasses())
				{
					if (DEBUG) System.out.println("name:"+name+" ppname:"+ppName);
					if(name.equals(ppName) == false)
					{
						reportNamespaceProblem(file, name);
					}
				}
			}
			else
			{
				for(String name : res.getClasses())
				{
					/* get the prefix of the class, eg.
					 * Modelica.Math.sin has prefix Modelica.Math */
					String prefix = null;
					int lastIndex = name.lastIndexOf('.');
					if(lastIndex != -1)
					{
						prefix = name.substring(0, lastIndex);
					}
					else
					{
						prefix = null;
					}

					/*
					 * If the prefix doesn't match the parent packages name,
					 * then the 'within' statement is bad.
					 */
					if (DEBUG) System.out.println("prefix:"+prefix+" ppname:"+ppName);
					if(prefix != null && prefix.equals(ppName) == false)
					{
						reportNamespaceProblem(file, name);
					}
				}
			}
		}
	}
}
