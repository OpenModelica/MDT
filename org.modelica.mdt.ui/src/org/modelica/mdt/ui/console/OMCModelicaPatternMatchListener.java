
package org.modelica.mdt.ui.console;

import org.eclipse.ui.console.IPatternMatchListener;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;
import org.eclipse.jface.text.BadLocationException;
import java.util.regex.*;
import org.eclipse.core.runtime.Platform;


public class OMCModelicaPatternMatchListener implements IPatternMatchListener
{
	public static String ID = "org.modelica.mdt.ui.console.OMCModelicaPatternMatchListener";	

	private String regexpGeneral = "^(\"omc>\".*)";    	

    private Pattern patternGeneral = Pattern.compile(regexpGeneral);
    private Matcher matcherGeneral;	
    
    private boolean DEBUG = false;
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
		event.getOffset();
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
