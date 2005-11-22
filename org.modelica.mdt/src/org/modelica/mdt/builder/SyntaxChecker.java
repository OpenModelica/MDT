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

package org.modelica.mdt.builder;

import java.io.BufferedInputStream;
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
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.internal.omcproxy.CompileError;
import org.modelica.mdt.internal.omcproxy.CompilerException;
import org.modelica.mdt.internal.omcproxy.OMCProxy;
import org.modelica.mdt.internal.omcproxy.ParseResults;

/**
 * This builder load all changed files into OMC in order to check for
 * parse errors. If a loaded file have any parse error, then a problem 
 * markers are set to communicate the problem to the user.
 * 
 * @author Andreas Remar
 */
public class SyntaxChecker extends IncrementalProjectBuilder
{

	public static final String BUILDER_ID = "org.modelica.mdt.syntaxChecker";

	@Override
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException
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
	
	protected void fullBuild(final IProgressMonitor monitor)
	{
		try
		{
			getProject().accept(new FullBuildVisitor());
		}
		catch(CoreException e)
		{
			// TODO Some other error handling?
			e.printStackTrace();
		}
	}
	
	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor)
	{
		try
		{
			delta.accept(new PartialBuildVisitor());
		}
		catch(CoreException e)
		{
			// TODO Proper error handling, someone?
			e.printStackTrace();
		}
	}
	
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
			MdtPlugin.log(e);
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
			MdtPlugin.log(e);
		}
	}
	
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
			MdtPlugin.log(e);
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
			MdtPlugin.log(e);
		}
	}
	
	protected static void reportProblem(IFile file, int lineno, String msg)
	{
		try
		{
			IMarker marker = file.createMarker(IMarker.PROBLEM);
			marker.setAttribute(IMarker.MESSAGE, msg);
			marker.setAttribute(IMarker.LINE_NUMBER, lineno);
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			marker.setAttribute(IMarker.LOCATION, Integer.toString(lineno));
			
			
			/*
			 * To find out where the error is in the file, we have to read it
			 * in to a Document and then use getLineOffset to convert from a
			 * line number to a character position.
			 */
			int start = 0; int end = 0;
			InputStream is = file.getContents();
			BufferedInputStream bis = new BufferedInputStream(is);
			String contents = "";
			while(true)
			{
				try
				{
					int avail = bis.available();
					if(avail == 0)
						break;
					byte[] buf = new byte[avail];
					bis.read(buf, 0, avail);

					contents += new String(buf);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}

			Document d = new Document(contents);
			try
			{
				start = d.getLineOffset(lineno - 1);
				end = start + d.getLineLength(lineno - 1);
			}
			catch(BadLocationException e)
			{
				MdtPlugin.log(e);
			}

			marker.setAttribute(IMarker.CHAR_START, start);
			marker.setAttribute(IMarker.CHAR_END, end);
		}
		catch(CoreException e)
		{
			MdtPlugin.log(e);
		}
		
	}
	
	protected void startupOnInitialization()
	{
		// TODO Add builder init here
	}
	
	protected void clean(IProgressMonitor monitor)
	{
		// TODO Add builder clean logic here
	}

	protected static void loadFileAndReportErrors(IFile file)
		throws CompilerException
	{
		ParseResults res = OMCProxy.loadFileInteractive(file);

		for (CompileError error : res.getCompileErrors())
		{
			reportProblem(file, error.getLine(), error.getErrorDescription());
		}
	}
}
