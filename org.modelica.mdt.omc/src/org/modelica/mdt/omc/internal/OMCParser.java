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

package org.modelica.mdt.omc.internal;

import java.util.StringTokenizer;

import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.ErrorManager;

/**
 * Handles parsing of more complex replys from OMC.
 * 
 * @author Andreas Remar
 */
public class OMCParser
{

	/**
	 * Parses the error string that is set by loadFileInteractive() on compile 
	 * errors. E.g. error string will look like \n delimeted list of
	 *      [/path/to/file.mo:20:1-20:5:writable]: error: some error
	 *
	 * The actual error string is retrived by calling getErrorString() after
	 * calling loadFileInteractive()
	 * 
	 * @param errorString the error string in the above format
	 * @return
	 */
	public static CompileError[] parseErrorString(String errorString)
		throws UnexpectedReplyException
	{
		StringTokenizer strTok = new StringTokenizer(errorString, "\r\n");
		CompileError[] compileErrs = new CompileError[strTok.countTokens()];
		
		for (int i = 0; strTok.hasMoreTokens(); i++)
		{
			/* default line number is 1 in case OMC returns unexpected string */
			int startLineNumber = 1;
			int startColumnNumber = 1;
			int endLineNumber = 1;
			int endColumnNumber = 1;
			String errorLine = strTok.nextToken();
			
		
			/*
			 * An error looks something like:
			 *   [/path/to/file.mo:20:12-34:20:writable]: error: some error
			 * We split on ']'
			 */
		
			/* 
			 * errorParts[0] is now error location
			 * and errorParts[1] is error message
			 */
			String[] errorParts = errorLine.split("]");
			
			if(errorParts.length < 2)
			{
				throw new UnexpectedReplyException("Weird error message from "+
						"the compiler: [" + errorLine + "]");
			}
			
			String errorLocation = errorParts[0];
			String errorMessage = errorParts[1];
		
			/* 
			 * parse error location from
			 *    "[/path/to/file.mo:20:12-34:20:writable"
			 * or
			 *    "[c:/path/to/file.mo:20:12-34:20:writable"
			 */
			
			String errorLocationParts[] = errorLocation.split(":");
			
			/*
			 * This is the ugliest hack in a long while. Aaaah, nice.
			 * 
			 * How it actually works:
			 *   Because : is both a separator in Windows (C:/path/to/file) and
			 *   a separator in error messages, where the line and column
			 *   numbers are found varies. If on Windows, the info we want
			 *   starts at array index 2, and on more normal systems, it starts
			 *   at array index 1. Simply check if the element at index 1 is
			 *   a digit or something else. Use this to set where in the array
			 *   that the line and column numbers can be found.
			 */
			char startCharacter = errorLocationParts[1].charAt(0);
			int infoOffset;
			if(startCharacter >= '0' && startCharacter <= '9')
			{
				infoOffset = 0;
			}
			else
			{
				infoOffset = 1;
			}
			
			try
			{
				startLineNumber = 
					Integer.parseInt(errorLocationParts[1+infoOffset]);

				// Split the 12-34 (start column & end line)
				String startColumnAndEndLine[] = 
					errorLocationParts[2+infoOffset].split("-");
				
				startColumnNumber =
					Integer.parseInt(startColumnAndEndLine[0]);

				endLineNumber =
					Integer.parseInt(startColumnAndEndLine[1]);

				endColumnNumber = 
					Integer.parseInt(errorLocationParts[3+infoOffset]);
			}
			catch (NumberFormatException e)
			{
				ErrorManager.logError(e);
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
			
			compileErrs[i] = new CompileError(startLineNumber,
											  startColumnNumber,
											  endLineNumber,
											  endColumnNumber,
											  errorDesc);
		}

		return compileErrs;
	}
	
	
	
}
