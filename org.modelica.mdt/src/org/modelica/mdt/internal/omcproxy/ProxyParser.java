package org.modelica.mdt.internal.omcproxy;

import java.util.StringTokenizer;

import org.modelica.mdt.MdtPlugin;

/**
 * 
 * @author Andreas Remar
 *
 */
public class ProxyParser
{
	/**
	 * This very simple parser just splits a flat modelica list into
	 * substrings. Doesn't handle nested lists. 
	 * 
	 * an example of a modelica list is: '{hej,hop,hepp}' 
	 */
	public static String[] parseList(String str)
	{
		/* remove whitespace before and after */
		str = str.trim();
		
		/* check that a list is parsed, otherwise this is probably an error */
		if(str.charAt(0) != '{' || str.charAt(str.length() - 1) != '}')
			return null;

		/* remove {} and split into tokens */
		str = str.substring(1, str.length() - 1);
		String[] retvals = str.split(",");
		
		return retvals;
	}

	public static CompileError[] parseErrorString(String errorString)
	{
		StringTokenizer strTok = new StringTokenizer(errorString, "\r\n");
		CompileError[] compileErrs = new CompileError[strTok.countTokens()];
		
		for (int i = 0; strTok.hasMoreTokens(); i++)
		{
			/* default line number is 1 in case OMC returns unexpected string */
			int lineno = 1;
			String errorLine = strTok.nextToken();
			
		
			/*
			 * An error string looks something like:
			 *    [/path/to/file.mo:20:1]: error: some error
			 * So to parse line number, we first split
			 * around ']'
			 */
		
			/* 
			 * errorParts[0] is now error location
			 * and errorParts[1] is error message
			 */
			String[] errorParts = errorLine.split("]");
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
			
			String errorDesc = 
				errorMessage.substring(secondColon+1).trim();
			
			compileErrs[i] = new CompileError(lineno, errorDesc);
		}

		return compileErrs;
	}
	
	
	
}
