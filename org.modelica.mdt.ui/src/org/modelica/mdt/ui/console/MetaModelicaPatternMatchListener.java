
package org.modelica.mdt.ui.console;

import org.eclipse.ui.console.IPatternMatchListener;
import org.eclipse.ui.console.IPatternMatchListenerDelegate;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;
import org.eclipse.jface.text.BadLocationException;
import java.util.regex.*;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.ui.console.FileLink;
import org.modelica.mdt.core.*;
import org.modelica.mdt.ui.UIPlugin;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.MarkerUtilities;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class MetaModelicaPatternMatchListener implements IPatternMatchListener
{
	public static String ID = "org.modelica.mdt.ui.console.MetaModelicaPatternMatchListener";	

	private String regexpMetaModelica = "\n(.*)\\:([0-9]*)\\.[0-9]*\\-[0-9]*\\.[0-9]*(.*)";
	private String regexpGeneral = "\n([^:]+)\\:([0-9]+):(.*)";    	

    private Pattern patternMetaModelica = Pattern.compile(regexpMetaModelica);
    private Matcher matcherMetaModelica; 

    private Pattern patternGeneral = Pattern.compile(regexpGeneral);
    private Matcher matcherGeneral;	
    
    private boolean DEBUG = true;
    private TextConsole omdevConsole;
    
    public MetaModelicaPatternMatchListener()
    {
    	super();
		String value = Platform.getDebugOption  /*load trace/ConsolePatternMatching flag */
		("org.modelica.mdt.omc/trace/ConsolePatternMatching");
		if (value != null && value.equalsIgnoreCase("true"))
		{
			DEBUG = true;
		}
		debug("constructor");
    }
    
    protected void debug(String message)
    {
    	if (DEBUG) System.out.println("MetaModelicaPatternMatchListener: " + message);
    }

	public void matchFound(PatternMatchEvent event)
	{
    	String strFileName = null;
    	String strLine = null;
    	String strMsg = null;
		debug("matchFound");
		/* get the existing consoles */
		TextConsole console = (TextConsole)event.getSource();		
		debug("Console:"+console.getName());
		String line = "";
		int offset = event.getOffset();
		try
		{
			line = console.getDocument().get(
					event.getOffset(), event.getLength());
			debug("MatchedLine:" + line);
		}
		catch (BadLocationException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			matcherMetaModelica = patternMetaModelica.matcher(line);        
			if (matcherMetaModelica.matches()) 
			{
				debug("Matches:" + matcherMetaModelica.groupCount());				
				if (matcherMetaModelica.groupCount() != 3) return;
				strFileName = matcherMetaModelica.group(1);
				strLine = matcherMetaModelica.group(2);
				strMsg = matcherMetaModelica.group(3);
				debug("Matches:[" + strFileName + "|" + strLine + "|" + strMsg + "]" );
			}
			else /* if the MetaModelica pattern does not match try the general pattern! */
			{
				matcherGeneral = patternGeneral.matcher(line);
				debug("General Matches:" + matcherGeneral.groupCount());				
				if (matcherGeneral.matches()) 
				{        	
					if (matcherGeneral.groupCount() != 3) return;
					strFileName = matcherGeneral.group(1);
					strLine = matcherGeneral.group(2);
					strMsg = matcherGeneral.group(3);
					debug("Matches:[" + strFileName + "|" + strLine + "|" + strMsg + "]" );					
				}
				else
				{
					debug("No pattern matched");
					return;
				}
			}
			MarkProblemsVisitor problemsVisitor = 
				new	MarkProblemsVisitor(
						strFileName, 
						strLine, 
						strMsg, 
						offset+1, // +1 for \n 
						console);
			IModelicaProject[] projects = ModelicaCore.getModelicaRoot().getProjects();
			for (IModelicaProject project: projects)
			{
				IProject pro = project.getWrappedProject();
				if (pro != null) pro.accept(problemsVisitor);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }
		
	
	public void connect(TextConsole console)
	{
		debug("should connect?:"+console.getName());		
		/*
		debug("should connect?:"+console.getName());
		if (omdevConsole != null && console.equals(omdevConsole)) // already connected
		{
			debug("already conected:"+console.getName());
			return;
		}
		*/
		if (console.getName().contains("OMDev"))
		{
			debug("connect:"+console.getName());		
			console.addPatternMatchListener(this);
			omdevConsole = console;
		}
	}

	public void disconnect()	
	{		
		if (omdevConsole == null) return;
		debug("disconnect");
		omdevConsole.removePatternMatchListener(this);
	}
	
	public int getCompilerFlags()
	{
		debug("getCompilerFlags");		
		return 0;
	}
	
	public String getLineQualifier()
	{
		debug("getLineQualifer");		
		return null;
	}
	
	public String getPattern()
	{
		debug("getPattern");		
		return "\n([^:]+)\\:([0-9]+)(.*)";
	}	
}
