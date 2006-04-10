/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Linköpings universitet, Department of
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
 * * Neither the name of Linkï¿½pings universitet nor the names of its
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

package org.modelica.mdt.ui.editor;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextUtilities;

/**
 * This class implements the auto indent strategy for modelica source
 * code editor.
 * 
 * @author: Adrian Pop
 */

class ModelicaAutoIndentStrategy implements IAutoEditStrategy
{

	static public class key 
	{
		protected String name;
		protected boolean indentPlus;
		protected boolean indentMinus;		
		public key(String name, boolean indentPlus, boolean indentMinus)
		{
			this.name        = name;
			this.indentPlus  = indentPlus;  /* should we indent++ */
			this.indentMinus = indentMinus; /* should we indent-- */
		}
		
		String getName() { return name; }
		boolean getIndentPlus() { return indentPlus; }
		boolean getIndentMinus() { return indentPlus; }		
	}
	
	/* keywords that influence indenting */
	static public key[] keywords = 
		{
			new key("algorithm", true, false),
			new key("and", false, false),
			new key("annotation", false, false), 
			new key("assert", false, false), 
			new key("block", true, false), 
			new key("break", false, false),
			new key("class", true, false), 
			new key("connect", false, false), 
			new key("connector", true, false), 
			new key("constant", false, false), 
			new key("der", false, false),
			new key("discrete", false, false),
			new key("each", false, false), 
			new key("else", false, false), 
			new key("elseif", false, false), 
			new key("elsewhen", false, false), 
			new key("encapsulated", false, false), 
			new key("end", false, true),
			new key("enumeration", false, false), 
			new key("equation", true, false), 
			new key("extends", false, false), 
			new key("external", false, false), 
			new key("false", false, false), 
			new key("final", false, false),
			new key("flow", false, false), 
			new key("for", false, false), 
			new key("function", true, false), 
			new key("if", false, false), 
			new key("import", false, false), 
			new key("in", false, false), 
			new key("initial", false, false), 
			new key("inner", false, false),
			new key("input", false, false), 
			new key("loop", false, false), 
			new key("model", true, false), 
			new key("not", false, false), 
			new key("or", false, false), 
			new key("outer", false, false),	
			new key("output", false, false), 
			new key("overload", false, false), 
			new key("package", true, false), 
			new key("parameter", false, false), 
			new key("partial", false, false), 
			new key("protected", false, false), 
			new key("public", false, false), 
			new key("record", true, false), 
			new key("redeclare", false, false), 
			new key("replacable", false, false), 
			new key("return", false, false),
			new key("terminate", false, false), 
			new key("then", true, false), 
			new key("time", false, false), 
			new key("true", false, false), 
			new key("type", true, false), 
			new key("when", false, false), 
			new key("while", true, false),
			new key("within", true, false),
			
			/* The following are Meta-Modelica Keywords */
			new key("as", false, false), 
			new key("case", true, false), 
			new key("equality", false, false), 
			new key("failure", false, false), 
			new key("list", false, false), 
			new key("local", true, false), 
			new key("match", true, false), 
			new key("matchcontinue", true, false),
			new key("tuple", false, false), 
			new key("uniontype", true, false)
		};


	/**
	 * Returns the first offset greater than <code>offset</code> and
	 * smaller than <code>end</code> whose character is not a space or tab
	 * character. If no such offset is found, <code>end</code> is
	 * returned.
	 * 
	 * @param document
	 *            the document to search in
	 * @param offset
	 *            the offset at which searching start
	 * @param end
	 *            the offset at which searching stops
	 * @return the offset in the specified range whose character is not a
	 *         space or tab
	 * @exception BadLocationException
	 *                if position is an invalid range in the given document
	 */


	protected int findEndOfWhiteSpace(IDocument document, int offset, int end) 
		throws BadLocationException 
	{

		while (offset < end) 
		{
			char c= document.getChar(offset);


			if (c != ' ' && c != '\t') 
			{
				return offset;
			}


			offset++;
		}

		return end;
	}


	/**
	 * Copies the indentation of the previous line and
	 * adds a \t if we have one of these keywords in the previous line:
	 *   class|model|package|function|algorithm|equation|match|matchcontinue|case|
	 *   then|else|public|protected|record|uniontype|block|local.
	 * Decrease indent with \t if the inserted text contains: 
	 *   class|model|package|function|algorithm|equation|match|matchcontinue|case|
	 *   else|public|protected|record|uniontype|block
	 * 
	 * @param d
	 *            the document to work on
	 * @param c
	 *            the command to deal with
	 */
	private void autoIndentAfterNewLine(IDocument d, DocumentCommand c) 
	{

		if (c.offset == -1 || d.getLength() == 0)
		{
			return;
		}


		try 
		{
			/* find start of line */


			int p = (c.offset == d.getLength() ? c.offset - 1 : c.offset);
			IRegion info = d.getLineInformationOfOffset(p);
			int start = info.getOffset();


			/* find white spaces */

			int end = findEndOfWhiteSpace(d, start, c.offset);


			StringBuffer buf = new StringBuffer(c.text);


			if (end > start) 
			{


				/* append to input */
				buf.append(d.get(start, end - start));
			}
			
			/* adrpo --
			 *  we copy the indentation from line above.
			 *  and now we need to decide if we increase/decrease 
			 *  the indent
			 */
			/* take the text appearing in this line and do some
			 * checking on it. 
			 */
			if ( c.offset > end )
			{
				String lineStr = d.get(end, c.offset - end);
				
				for (int i=0; i < keywords.length; i++ )
				{
					if (keywords[i].indentPlus && lineStr.startsWith(keywords[i].name))
					{
						buf.append("  ");
					}
					else if (keywords[i].indentMinus && lineStr.startsWith(keywords[i].name))
					{
						int l = buf.length();
						/* decrease indent */
						if (l >= 2 && buf.charAt(l-1) == ' ' && buf.charAt(l-2) == ' ')
						buf.delete(l-2, l);
					}
				}
			}
			c.text = buf.toString();
		} 
		catch (BadLocationException excp) 
		{

			/* stop work */
			excp.printStackTrace();
		}
	}


	private boolean isLineDelimiter(IDocument document, String text) 
	{
		String[] delimiters = document.getLegalLineDelimiters();


		if (delimiters != null)
		{
			return TextUtilities.equals(delimiters, text) > -1;
		}


		return false;
	}


	private void smartIndentOnTab(IDocument document, DocumentCommand command) 
	{

		/*
		 * see here what ident has the line before 
		 * and apply the needed indenting.
		 * 
		 * -- nothing for now
		 */
	}


	private void smartIndentOnKeypress(IDocument document, 
			DocumentCommand command) 
	{
		switch (command.text.charAt(0)) 
		{
		case '\t':
			smartIndentOnTab(document, command);
			break;


			/* add more here if needed */
			/* ok */
		}
	}

	private void smartPaste(IDocument document, DocumentCommand command) 
	{


		/* 
		 * here parse the command.text and format it before paste
		 * 
		 * -- nothing for now.
		 */
	}


	/**
	 * @see org.eclipse.jface.text.IAutoEditStrategy#customizeDocumentCommand(org.eclipse.jface.text.IDocument,
	 *      org.eclipse.jface.text.DocumentCommand)
	 */
	public void customizeDocumentCommand(IDocument d, DocumentCommand c) 
	{	
	     if (c.doit == false)
	     {
	    	 return;
	     }

		
		if (c.length == 0 && c.text != null && isLineDelimiter(d, c.text))
		{
			autoIndentAfterNewLine(d, c);
		}

		else if (c.text.length() == 1)
		{

			smartIndentOnKeypress(d, c);
		}

		else if (c.text.length() > 1)
		{

			smartPaste(d, c); 
		}
	}
}




