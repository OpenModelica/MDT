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

package org.modelica.mdt.core.compiler;

import java.util.Vector;

/**
 * This class prvides some code to parse simple modelica primitives,
 * for now it can parse lists in modelica syntax.
 * 
 * @author Andreas Remar
 */
public class ModelicaParser
{
	/**
	 * This function parses Modelica lists, any nesting possible.
	 * @param str the Modelica list to parse
	 * @return a Vector containing Vector:s and String:s. The Vector:s contain
	 * further Vector:s and String:s. Nesting and stuff.
	 */
	public static Vector<Object> parseList(String str)
	{
		Vector<Object> elements = new Vector<Object>();
		
		/* Remove whitespace before and after */
		str = str.trim();
		
		/* Make sure this string is not empty */
		if(str == "" || str.length() < 2)
		{
			return null;
		}
		
		/* Make sure this is a list */
		if(str.charAt(0) != '{' || str.charAt(str.length() - 1) != '}')
		{
			return null;
		}
		/* Remove { and } */
		str = str.substring(1, str.length() - 1);
		
		/*
		 * { { hej, på } , dig } => [[hej,på],dig]
		 */
		
		/*
		 * Go through the string character by character, looking for commas (,)
		 * and start ({) and end (}) of lists. 
		 */
		String subString = "";
		int depth = 0;
		boolean listFound = false;
		for(int characterPosition = 0;characterPosition < str.length()
			;characterPosition++)
		{
			if(str.charAt(characterPosition) == '{')
			{
				listFound = true;
				depth++;
			}
			if(str.charAt(characterPosition) == ',' && depth == 0)
			{
				/*
				 * If we're at depth 0, then we've found a list (or element)
				 * at the bottom level.
				 */
				Object element = null;
				
				if(listFound)
				{					
					element = parseList(subString);
					/*
					 * If subString really wasn't a list (it contains {} but
					 * still isn't a list), then just trim it and say it's an
					 * element.
					 */
					if(element == null)
						element = subString.trim();
				}
				else
				{
					element = subString.trim();
				}
				
				listFound = false;
				if(element instanceof String && ((String)element).equals(""))
				{
					/* Don't add empty strings */
					continue;
				}
				elements.add(element);
				subString = "";
 			}
			else
			{
				subString += str.charAt(characterPosition);
			}
			if(str.charAt(characterPosition) == '}')
			{
				depth--;
				if(depth < 0)
				{
					return new Vector<Object>();
				}
			}
		}
		
		/* This happens at the end of the list. */
		if(depth == 0)
		{
			Object element = null;
			if(listFound)
			{
				element = parseList(subString);
				/*
				 * If subString really wasn't a list (it contains {} but
				 * still isn't a list), then just trim it and say it's an
				 * element.
				 */
				if(element == null)
				{
					element = subString.trim();
				}
			}
			else
			{
				element = subString.trim();
			}
			
			if(element instanceof String && ((String)element).equals(""))
				/* Don't add empty strings */;
			else
				elements.add(element);
		}
		
		return elements;
	}
}
