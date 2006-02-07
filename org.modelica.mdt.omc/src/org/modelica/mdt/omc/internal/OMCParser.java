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
	 *      [/path/to/file.mo:20:1]: error: some error
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
			
			if(errorParts.length < 2)
			{
				throw new UnexpectedReplyException("Weird error message from "+
						"the compiler: [" + errorLine + "]");
			}
			
			String errorLocation = errorParts[0];
			String errorMessage = errorParts[1];
		
			/* 
			 * parse error location from
			 *    "[/path/to/file.mo:20:1"
			 * we are only interested in line number at this point
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
			
			compileErrs[i] = new CompileError(lineno, errorDesc);
		}

		return compileErrs;
	}
	
	
	
}
