
package org.modelica.mdt.ui.console;

import org.eclipse.ui.console.IPatternMatchListener;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;
import org.eclipse.jface.text.BadLocationException;
import java.util.regex.*;
import org.eclipse.core.resources.IProject;
import org.modelica.mdt.core.*;
import org.eclipse.core.runtime.Platform;


public class MetaModelicaPatternMatchListener implements IPatternMatchListener
{
	public static String ID = "org.modelica.mdt.ui.console.MetaModelicaPatternMatchListener";	

	private String regexpMetaModelica = "^([^:]+)\\:([0-9]*)\\.[0-9]*\\-[0-9]*\\.[0-9]*(.*)";
	private String regexpGeneral = "^([^:]+)\\:([0-9]+):(.*)";    	

    private Pattern patternMetaModelica = Pattern.compile(regexpMetaModelica);
    private Matcher matcherMetaModelica; 

    private Pattern patternGeneral = Pattern.compile(regexpGeneral);
    private Matcher matcherGeneral;	
    
    private boolean DEBUG = false;
    private TextConsole omdevConsole;
    
    public MetaModelicaPatternMatchListener()
    {
    	super();
		String value = Platform.getDebugOption  /*load trace/ConsolePatternMatching flag */
		("org.modelica.mdt.ui/trace/MetaModelicaPatternMatchListener");
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
		
		while(line.charAt(0) == '\n' || line.charAt(0) == '\r')
		{
			line = line.substring(1);
			offset++;
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
						offset,  
						console);
			IModelicaProject[] projects = ModelicaCore.getModelicaRoot().getProjects();
			for (IModelicaProject project: projects)
			{
				IProject pro = project.getWrappedProject();
				if (pro != null && pro.isOpen()) 
				{
					debug("ProjectName:"+pro.getName()+" path:" + pro.getFullPath());
					pro.accept(problemsVisitor);
				}
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
		return "([^:\n\r]+)\\:([0-9]+)(.*)";
	}	
}
