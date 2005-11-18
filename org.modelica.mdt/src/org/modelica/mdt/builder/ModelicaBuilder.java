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
import org.modelica.mdt.internal.omcproxy.InitializationException;
import org.modelica.mdt.internal.omcproxy.OMCProxy;

/**
 * 
 * @author Andreas Remar
 *
 */
public class ModelicaBuilder extends IncrementalProjectBuilder
{
	@SuppressWarnings("deprecation")
	public static final String BUILDER_ID =
		MdtPlugin.getSymbolicName() + ".modelicaBuilder";

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
			getProject().accept(new ModelicaBuildVisitor());
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
			delta.accept(new ModelicaBuildDeltaVisitor());
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
		for(int j = 0;j < cmds.length; j++)
		{
			if(cmds[j].getBuilderName().equals(BUILDER_ID))
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
	
	protected static void reportProblem(String msg, IFile file, int lineno)
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
			System.out.println(e);
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
		throws InitializationException
	{
		String[] retval = OMCProxy.loadFileInteractive(file);
				
		
		if(retval != null)
		{
			for(String s: retval)
			{
				if(s.contains("error"))
				{
					String msg;
					int lineno = 1;
					
					/*
					 * An error string looks something like:
					 *    [/path/to/file.mo:20:1]: error: some error
					 * So to parse line and column number, we first split
					 * around ']' and then around ':'.
					 * To get the error message, we split around ':' to get
					 * rid of the 'error: ' stuff.
					 */
					
					/* s2[0] is now error location and s2[1] is error message */
					String[] errorParts = s.split("]");
					String errorLocation = errorParts[0];
					String errorMessage = errorParts[1];
					
					/* 
					 * parse error location from
					 *    "[/path/to/file.mo:20:1"
					 * we are only interested in line number at this point
					 * We allready know the file, and column number is just
					 * broken (OMC allways returns 1) 
					 */
					int lastColon = errorLocation.lastIndexOf(":");
					int beforeLastColon = 
						errorLocation.substring(0, lastColon).lastIndexOf(":");
					try
					{
						lineno = 
							Integer.parseInt
							(errorLocation.substring(beforeLastColon+1,
							    lastColon));
					}
					catch (NumberFormatException e)
					{
						MdtPlugin.log(e);
					}
					
					/*
					 * parse error message from
					 *   ": error: some error"
					 * that is we are interested in rest of the string after
					 * second colon
					 */					
					int firstColon = errorMessage.indexOf(":");
					int secondColon = 
							errorMessage.substring(firstColon+1).indexOf(":");
					/* we need global position on errorMessage string */
					secondColon += firstColon+1;
					
					msg = 
						errorMessage.substring(secondColon+1).trim();
					
					ModelicaBuilder.reportProblem(msg, file, lineno);
				}
			}
		}
	}
}
