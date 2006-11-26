
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


public class OMCModelicaPatternMatchListener implements IPatternMatchListener
{
	public static String ID = "org.modelica.mdt.ui.console.OMCModelicaPatternMatchListener";	

	private String regexpGeneral = "^(\"omc>\".*)";    	

    private Pattern patternGeneral = Pattern.compile(regexpGeneral);
    private Matcher matcherGeneral;	
    
    private boolean DEBUG = true;
    private TextConsole omcConsole;
    
    public OMCModelicaPatternMatchListener()
    {
    	super();
		String value = Platform.getDebugOption  /*load trace/ConsolePatternMatching flag */
		("org.modelica.mdt.ui/trace/OMCModelicaPatternMatchListener");
		if (value != null && value.equalsIgnoreCase("true"))
		{
			DEBUG = true;
		}
		debug("constructor");
    }
    
    protected void debug(String message)
    {
    	if (DEBUG) System.out.println("OMCModelicaPatternMatchListener: " + message);
    }

	public void matchFound(PatternMatchEvent event)
	{
    	String strCommand = null;
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
		
		while(line.charAt(0) == '\n' || line.charAt(0) == '\r')
		{
			line = line.substring(1);
			offset++;
		}
		
		try
		{
			matcherGeneral = patternGeneral.matcher(line);
			debug("General Matches:" + matcherGeneral.groupCount());				
			if (matcherGeneral.matches()) 
			{        	
				if (matcherGeneral.groupCount() != 1) return;
				strCommand = matcherGeneral.group(1);
				debug("Matches:[" + strCommand + "]" );					
			}
			else
			{
				debug("No pattern matched");
				return;
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
		if (console.getName().contains("OpenModelica Console"))
		{
			debug("connect:"+console.getName());		
			console.addPatternMatchListener(this);
			omcConsole = console;
		}
	}

	public void disconnect()	
	{		
		if (omcConsole == null) return;
		debug("disconnect");
		omcConsole.removePatternMatchListener(this);
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
		return "(\"omc>\".*)";
	}	
}
