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

import org.modelica.mdt.ui.actions.ModelicaCorrectIndentationAction;
import org.modelica.mdt.ui.text.ModelicaHeuristicScanner;
import org.modelica.mdt.ui.text.ModelicaIndenter;
import org.modelica.mdt.ui.text.Symbols;

/**
 * This class implements the auto indent strategy for modelica source
 * code editor.
 *
 * @author: Adrian Pop
 */
class ModelicaAutoIndentStrategy implements IAutoEditStrategy {

	static public class key {
		protected String name;
		protected boolean indentPlus;
		protected boolean indentMinus;
		// these specify what should we do with the current line when
		// pressing \n. Example: end Ident;\n should indent this line with a -
		protected boolean indentCurrentPlus;
		protected boolean indentCurrentMinus;

		public key(String name, boolean indentPlus, boolean indentMinus, boolean indentCurrentPlus, boolean indentCurrentMinus) {
			this.name = name; // the name of the key
			this.indentPlus = indentPlus;  // should we indent++
			this.indentMinus = indentMinus; // should we indent--
			// these specify what should we do with the current line when pressing \n
			this.indentCurrentPlus = indentCurrentPlus;
			this.indentCurrentMinus = indentCurrentMinus;
		}

		String getName() {
			return name;
		}

		boolean getIndentPlus() {
			return indentPlus;
		}

		boolean getIndentMinus() {
			return indentPlus;
		}
	}

	// keywords that influence indenting
	static public key[] keywords =
		{
		new key("algorithm", true, false, false, false),
		new key("and", false, false, false, false),
		new key("annotation", false, false, false, false),
		new key("assert", false, false, false, false),
		new key("block", true, false, false, false),
		new key("break", false, false, false, false),
		new key("class", true, false, false, false),
		new key("operator", true, false, false, false),
		new key("connect", false, false, false, false),
		new key("connector", true, false, false, false),
		new key("constant", false, false, false, false),
		new key("der", false, false, false, false),
		new key("discrete", false, false, false, false),
		new key("each", false, false, false, false),
		new key("else", false, false, false, false),
		new key("elseif", false, false, false, false),
		new key("elsewhen", false, false, false, false),
		new key("encapsulated", false, false, false, false),
		new key("end", false, true, false, true),
		new key("enumeration", false, false, false, false),
		new key("equation", true, false, false, false),
		new key("extends", false, false, false, false),
		new key("constrainedby", false, false, false, false),
		new key("expandable", false, false, false, false),
		new key("external", false, false, false, false),
		new key("false", false, false, false, false),
		new key("final", false, false, false, false),
		new key("flow", false, false, false, false),
		new key("for", false, false, false, false),
		new key("function", true, false, false, false),
		new key("if", false, false, false, false),
		new key("import", false, false, false, false),
		new key("in", false, false, false, false),
		new key("initial", false, false, false, false),
		new key("inner", false, false, false, false),
		new key("input", false, false, false, false),
		new key("loop", false, false, false, false),
		new key("model", true, false, false, false),
		new key("not", false, false, false, false),
		new key("or", false, false, false, false),
		new key("outer", false, false, false, false),
		new key("output", false, false, false, false),
		new key("overload", false, false, false, false),
		new key("package", true, false, false, false),
		new key("parameter", false, false, false, false),
		new key("partial", false, false, false, false),
		new key("protected", false, false, false, false),
		new key("public", false, false, false, false),
		new key("record", true, false, false, false),
		new key("redeclare", false, false, false, false),
		new key("replacable", false, false, false, false),
		new key("return", false, false, false, false),
		new key("terminate", false, false, false, false),
		new key("then", true, false, false, false),
		new key("time", false, false, false, false),
		new key("true", false, false, false, false),
		new key("type", true, false, false, false),
		new key("when", false, false, false, false),
		new key("while", true, false, false, false),
		new key("within", true, false, false, false),

		// The following are Meta-Modelica Keywords
		new key("as", false, false, false, false),
		new key("case", true, false, false, true),
		new key("equality", false, false, false, false),
		new key("failure", false, false, false, false),
		new key("list", false, false, false, false),
		new key("local", true, false, false, false),
		new key("match", true, false, false, false),
		new key("matchcontinue", true, false, false, false),
		new key("tuple", false, false, false, false),
		new key("subtypeof", false, false, false, false),
		new key("uniontype", true, false, false, false),
		new key("metarecord", true, false, false, false),
		new key("guard", true, false, false, false)
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
	protected int findEndOfWhiteSpace(IDocument document, int offset, int end) throws BadLocationException {
		while (offset < end) {
			char c = document.getChar(offset);

			if (c != ' ' && c != '\t') {
				return offset;
			}

			offset++;
		}

		return end;
	}

	/**
	 * First sees if the previous line contains the "end" keyword and if it does
	 * it decreases the indent and then:
	 * Copies the indentation of the previous line and
	 * adds a \t if we have one of these keywords in the previous line:
	 *   class|model|package|function|algorithm|equation|match|matchcontinue|case|
	 *   then|else|public|protected|record|uniontype|block|local.
	 * Decrease indent with \t if the inserted text contains:
	 *   end|case.
	 *
	 * @param d
	 *            the document to work on
	 * @param c
	 *            the command to deal with
	 */
	private void autoIndentAfterNewLine(IDocument d, DocumentCommand c) {
		if (c.offset == -1 || d.getLength() == 0) {
			return;
		}

		try {
			// find start of line
			int p = c.offset == d.getLength() ? c.offset - 1 : c.offset;
			IRegion info = d.getLineInformationOfOffset(p);
			int start = info.getOffset();

			// find white spaces
			int end = findEndOfWhiteSpace(d, start, c.offset);
			StringBuffer buf = new StringBuffer(c.text);

			if (end > start) {
				// append to input
				buf.append(d.get(start, end - start));
			}

			// adrpo --
			// we copy the indentation from line above.
			// and now we need to decide if we increase/decrease
			// the indent
			//
			// take the text appearing in this line and do some
			// checking on it.
			if (c.offset > end) {
				String lineStr = d.get(end, c.offset - end);

				for (key keyword : keywords) {
					if (keyword.indentPlus && lineStr.startsWith(keyword.name)) {
						buf.append("  ");
					}
					else if (keyword.indentMinus && lineStr.startsWith(keyword.name)) {
						int l = buf.length();

						// decrease indent
						if (l >= 2 && buf.charAt(l - 1) == ' ' && buf.charAt(l - 2) == ' ') {
							buf.delete(l - 2, l);
						}
					}
				}
			}

			c.text = buf.toString();
		}
		catch (BadLocationException e) {
			// stop work
			e.printStackTrace();
		}
	}

	private boolean isLineDelimiter(IDocument document, String text) {
		String[] delimiters = document.getLegalLineDelimiters();
		boolean isLineDelimiter = false;

		if (delimiters != null) {
			isLineDelimiter = TextUtilities.equals(delimiters, text) > -1;
		}

		return isLineDelimiter;
	}

	private void smartIndentOnTab(IDocument document, DocumentCommand command) {
		command.text = ""; // Needed, or we will get extra whitespace insertions!
		ModelicaCorrectIndentationAction.getInstance(command).run();
		ModelicaCorrectIndentationAction.resetDocumentCommand();
	}

	private void smartIndentOnKeypress(IDocument document, DocumentCommand command) {
		char ch = command.text.charAt(0);

		switch (ch) {
		case '\t':
			smartIndentOnTab(document, command);
			break;
		case 'e':
			smartIndentUponE(document, command);
			break;
		}
	}

	private String getIndentOfLine(IDocument d, int line) throws BadLocationException {
		String indentOfLine = "";

		if (line > -1) {
			int start = d.getLineOffset(line);
			int end = start + d.getLineLength(line) - 1;
			int whiteEnd = findEndOfWhiteSpace(d, start, end);
			indentOfLine = d.get(start, whiteEnd - start);
		}

		return indentOfLine;
	}

	private void smartIndentUponE(IDocument d, final DocumentCommand c) {
		if (c.offset < 4 || d.getLength() == 0) {
			return;
		}

		try {
			final String content = d.get(c.offset - 3, 3);

			if (!content.equals("els") && !content.equals("cas")) {
				return;
			}

			final int p = c.offset - 3;
			// current line
			final int line = d.getLineOfOffset(p);
			final int lineOffset = d.getLineOffset(line);
			// make sure we don't have any leading comments etc.
			if (d.get(lineOffset, p - lineOffset).trim().length() != 0) {
				return;
			}

			final ModelicaHeuristicScanner scanner = new ModelicaHeuristicScanner(d);

			// line of last javacode
			final int pos = scanner.findNonWhitespaceBackward(p - 1, ModelicaHeuristicScanner.UNBOUND);

			if (pos == -1) {
				return;
			}

			final int lastLine = d.getLineOfOffset(pos);

			// only shift if the last java line is further up and is a braceless block candidate
			if (lastLine >= line) {
				return;
			}

			final ModelicaIndenter indenter = new ModelicaIndenter(d, scanner);

			if (content.equals("els")) {
				int ref = indenter.findReferencePosition(p, true, false, false, false, false);

				if (ref == ModelicaHeuristicScanner.NOT_FOUND) {
					return;
				}

				int refLine = d.getLineOfOffset(ref);
				String indent = getIndentOfLine(d, refLine);

				if (indent != null) {
					c.text = indent.toString() + "else";
					c.length += c.offset - lineOffset;
					c.offset = lineOffset;
				}
			}
			else if (content.equals("cas")) {
				int ref = indenter.findReferencePosition(p, false, false, false, true, false);

				if (ref == ModelicaHeuristicScanner.NOT_FOUND) {
					return;
				}

				int refLine = d.getLineOfOffset(ref);
				int nextToken = scanner.nextToken(ref, ModelicaHeuristicScanner.UNBOUND);
				String indent;

				if (nextToken == Symbols.TokenCASE || nextToken == Symbols.TokenELSE) {
					indent = getIndentOfLine(d, refLine);
				}
				else { // at the brace of the switch
					indent = indenter.computeIndentation(p).toString();
				}

				if (indent != null) {
					c.text = indent.toString() + "case";
					c.length += c.offset - lineOffset;
					c.offset = lineOffset;
				}
			}
		}
		catch (BadLocationException e) {
			//org.modelica.mdt.internal.core.ErrorManager.logWarning(e.getMessage());
		}
	}

	private void smartPaste(IDocument document, DocumentCommand command) {
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
	@Override
	public void customizeDocumentCommand(IDocument d, DocumentCommand c) {
		if (c.doit == false) {
			return;
		}

		if (c.length == 0 && c.text != null && isLineDelimiter(d, c.text)) {
			autoIndentAfterNewLine(d, c);
			// ModelicaCorrectIndentationAction.getInstance().run();
		}
		else if (c.text.length() == 1) {
			smartIndentOnKeypress(d, c);
		}
		else if (c.text.length() > 1) {
			smartPaste(d, c);
		}
	}
}
