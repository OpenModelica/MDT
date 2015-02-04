/*******************************************************************************
 * Copyright (c) 2006 MDT Team, PELAB
 *******************************************************************************/
package org.modelica.mdt.ui.text;

import java.util.Stack;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

/**
 * Uses the {@link org.modelica.mdt.ui.text.ModelicaHeuristicScanner}to
 * get the indentation level for a certain position in a document.
 *
 * <p>
 * An instance holds some internal position in the document and is therefore
 * not threadsafe.
 * </p>
 *
 * @since 3.0
 */
public class ModelicaIndenter {

	/** The document being scanned. */
	private IDocument fDocument;
	/** The indentation accumulated by <code>findReferencePosition</code>. */
	private int fIndent;
	/**
	 * The absolute (character-counted) indentation offset for special cases
	 * (method defs, array initializers)
	 */
	private int fAlign;
	/** The stateful scanposition for the indentation methods. */
	private int fPosition;
	/** The previous position. */
	private int fPreviousPos;
	/** The most recent token. */
	private int fToken;
	/** The line of <code>fPosition</code>. */
	private int fLine;
	/**
	 * The scanner we will use to scan the document. It has to be installed
	 * on the same document as the one we get.
	 */
	private ModelicaHeuristicScanner fScanner;

	/**
	 * Creates a new instance.
	 *
	 * @param document the document to scan
	 * @param scanner the {@link ModelicaHeuristicScanner} to be used for scanning
	 * the document. It must be installed on the same <code>IDocument</code>.
	 */
	public ModelicaIndenter(IDocument document, ModelicaHeuristicScanner scanner)
	{
		Assert.isNotNull(document);
		Assert.isNotNull(scanner);
		fDocument= document;
		fScanner= scanner;
	}

	/**
	 * Computes the indentation at the reference point of <code>position</code>.
	 *
	 * @param offset the offset in the document
	 * @return a String which reflects the indentation at the line in which the
	 *         reference position to <code>offset</code> resides, or <code>null</code>
	 *         if it cannot be determined
	 */
	public StringBuffer getReferenceIndentation(int offset) {
		int unit;
		unit= findReferencePosition(offset, peekChar(offset));

		// if we were unable to find anything, return null
		if (unit == ModelicaHeuristicScanner.NOT_FOUND)
			return null;
		return getLeadingWhitespace(unit);

	}

	/**
	 * Computes the indentation at <code>offset</code>.
	 *
	 * @param offset the offset in the document
	 * @return a String which reflects the correct indentation for the line in
	 *         which offset resides, or <code>null</code> if it cannot be
	 *         determined
	 */
	public StringBuffer computeIndentation(int offset)
	{
		StringBuffer reference= getReferenceIndentation(offset);

		// handle special alignment
		if (fAlign != ModelicaHeuristicScanner.NOT_FOUND) {
			try {
				// a special case has been detected.
				IRegion line= fDocument.getLineInformationOfOffset(fAlign);
				int lineOffset= line.getOffset();
				return createIndent(lineOffset, fAlign, false);
			} catch (BadLocationException e) {
				return null;
			}
		}

		if (reference == null)
			return null;

		// add additional indent
		return createReusingIndent(reference, fIndent);
	}

	/**
	 * Computes the length of a <code>CharacterSequence</code>, counting
	 * a tab character as the size until the next tab stop and every other
	 * character as one.
	 *
	 * @param indent the string to measure
	 * @return the visual length in characters
	 */
	private int computeVisualLength(CharSequence indent) {
		final int tabSize= prefTabSize();
		int length= 0;
		for (int i= 0; i < indent.length(); i++) {
			char ch= indent.charAt(i);
			switch (ch) {
				case '\t':
					if (tabSize > 0) {
						int reminder= length % tabSize;
						length += tabSize - reminder;
					}
					break;
				case ' ':
					length++;
					break;
			}
		}
		return length;
	}

	/**
	 * Strips any characters off the end of <code>reference</code> that exceed
	 * <code>indentLength</code>.
	 *
	 * @param reference the string to measure
	 * @param indentLength the maximum visual indentation length
	 * @return the stripped <code>reference</code>
	 */
	private StringBuffer stripExceedingChars(StringBuffer reference, int indentLength) {
		final int tabSize= prefTabSize();
		int measured= 0;
		int chars= reference.length();
		int i= 0;
		for (; measured < indentLength && i < chars; i++) {
			char ch= reference.charAt(i);
			switch (ch) {
				case '\t':
					if (tabSize > 0) {
						int reminder= measured % tabSize;
						measured += tabSize - reminder;
					}
					break;
				case ' ':
					measured++;
					break;
			}
		}
		int deleteFrom= measured > indentLength ? i - 1 : i;

		return reference.delete(deleteFrom, chars);
	}

	/**
	 * Returns the indentation of the line at <code>offset</code> as a
	 * <code>StringBuffer</code>. If the offset is not valid, the empty string
	 * is returned.
	 *
	 * @param offset the offset in the document
	 * @return the indentation (leading whitespace) of the line in which
	 * 		   <code>offset</code> is located
	 */
	private StringBuffer getLeadingWhitespace(int offset) {
		StringBuffer indent= new StringBuffer();
		try {
			IRegion line= fDocument.getLineInformationOfOffset(offset);
			int lineOffset= line.getOffset();
			int nonWS= fScanner.findNonWhitespaceForwardInAnyPartition(lineOffset, lineOffset + line.getLength());
			indent.append(fDocument.get(lineOffset, nonWS - lineOffset));
			return indent;
		} catch (BadLocationException e) {
			return indent;
		}
	}

	/**
	 * Creates an indentation string of the length indent - start, consisting of
	 * the content in <code>fDocument</code> in the range [start, indent),
	 * with every character replaced by a space except for tabs, which are kept
	 * as such.
	 * <p>
	 * If <code>convertSpaceRunsToTabs</code> is <code>true</code>, every
	 * run of the number of spaces that make up a tab are replaced by a tab
	 * character. If it is not set, no conversion takes place, but tabs in the
	 * original range are still copied verbatim.
	 * </p>
	 *
	 * @param start the start of the document region to copy the indent from
	 * @param indent the exclusive end of the document region to copy the indent
	 *        from
	 * @param convertSpaceRunsToTabs whether to convert consecutive runs of
	 *        spaces to tabs
	 * @return the indentation corresponding to the document content specified
	 *         by <code>start</code> and <code>indent</code>
	 */
	private StringBuffer createIndent(int start, final int indent, final boolean convertSpaceRunsToTabs) {
		final boolean convertTabs= prefUseTabs() && convertSpaceRunsToTabs;
		final int tabLen= prefTabSize();
		final StringBuffer ret= new StringBuffer();
		try {
			int spaces= 0;
			while (start < indent) {

				char ch= fDocument.getChar(start);
				if (ch == '\t') {
					ret.append('\t');
					spaces= 0;
				} else if (convertTabs) {
					spaces++;
					if (spaces == tabLen) {
						ret.append('\t');
						spaces= 0;
					}
				} else {
					ret.append(' ');
				}

				start++;
			}
			// remainder
			while (spaces-- > 0)
				ret.append(' ');

		} catch (BadLocationException e) {
		}

		return ret;
	}

	/**
	 * Creates a string with a visual length of the given
	 * <code>indentationSize</code>.
	 *
	 * @param buffer the original indent to reuse if possible
	 * @param additional the additional indentation units to add or subtract to
	 *        reference
	 * @return the modified <code>buffer</code> reflecting the indentation
	 *         adapted to <code>additional</code>
	 */
	private StringBuffer createReusingIndent(StringBuffer buffer, int additional) {
		int refLength= computeVisualLength(buffer);
		int addLength= prefIndentationSize() * additional; // may be < 0
		int totalLength= Math.max(0, refLength + addLength);


		// copy the reference indentation for the indent up to the last tab
		// stop within the maxCopy area
		int minLength= Math.min(totalLength, refLength);
		int tabSize= prefTabSize();
		int maxCopyLength= tabSize > 0 ? minLength - minLength % tabSize : minLength; // maximum indent to copy
		stripExceedingChars(buffer, maxCopyLength);

		// add additional indent
		int missing= totalLength - maxCopyLength;
		final int tabs, spaces;
			tabs= 0;
			spaces= missing;
		for(int i= 0; i < tabs; i++)
			buffer.append('\t');
		for(int i= 0; i < spaces; i++)
			buffer.append(' ');
		return buffer;
	}

	/**
	 * Returns the reference position regarding to indentation for <code>offset</code>,
	 * or <code>NOT_FOUND</code>. This method calls
	 * {@link #findReferencePosition(int, int) findReferencePosition(offset, nextChar)} where
	 * <code>nextChar</code> is the next character after <code>offset</code>.
	 *
	 * @param offset the offset for which the reference is computed
	 * @return the reference statement relative to which <code>offset</code>
	 *         should be indented, or {@link ModelicaHeuristicScanner#NOT_FOUND}
	 */
	public int findReferencePosition(int offset) {
		return findReferencePosition(offset, peekChar(offset));
	}

	/**
	 * Peeks the next char in the document that comes after <code>offset</code>
	 * on the same line as <code>offset</code>.
	 *
	 * @param offset the offset into document
	 * @return the token symbol of the next element, or TokenEOF if there is none
	 */
	private int peekChar(int offset) {
		if (offset < fDocument.getLength()) {
			try {
				IRegion line= fDocument.getLineInformationOfOffset(offset);
				int lineOffset= line.getOffset();
				int next= fScanner.nextToken(offset, lineOffset + line.getLength());
				return next;
			} catch (BadLocationException e) {
			}
		}
		return Symbols.TokenEOF;
	}

	/**
	 * Returns the reference position regarding to indentation for <code>position</code>,
	 * or <code>NOT_FOUND</code>.
	 *
	 * <p>If <code>peekNextChar</code> is <code>true</code>, the next token after
	 * <code>offset</code> is read and taken into account when computing the
	 * indentation. Currently, if the next token is the first token on the line
	 * (i.e. only preceded by whitespace), the following tokens are specially
	 * handled:
	 * <ul>
	 * 	<li><code>switch</code> labels are indented relative to the switch block</li>
	 * 	<li>opening curly braces are aligned correctly with the introducing code</li>
	 * 	<li>closing curly braces are aligned properly with the introducing code of
	 * 		the matching opening brace</li>
	 * 	<li>closing parenthesis' are aligned with their opening peer</li>
	 * 	<li>the <code>else</code> keyword is aligned with its <code>if</code>, anything
	 * 		else is aligned normally (i.e. with the base of any introducing statements).</li>
	 *  <li>if there is no token on the same line after <code>offset</code>, the indentation
	 * 		is the same as for an <code>else</code> keyword</li>
	 * </ul>
	 *
	 * @param offset the offset for which the reference is computed
	 * @param nextToken the next token to assume in the document
	 * @return the reference statement relative to which <code>offset</code>
	 *         should be indented, or {@link ModelicaHeuristicScanner#NOT_FOUND}
	 */
	public int findReferencePosition(int offset, int nextToken)
	{
		boolean danglingElse= false;
		boolean unindent= false;
		boolean indent= false;
		boolean matchEnd= false;
		boolean matchParen= false;
		boolean matchCase= false;
		boolean matchCaseOrScope= false;

		// account for unindenation characters already typed in, but after position
		// if they are on a line by themselves, the indentation gets adjusted
		// accordingly
		//
		// also account for a dangling else
		if (offset < fDocument.getLength())
		{
			try
			{
				IRegion line= fDocument.getLineInformationOfOffset(offset);
				int lineOffset= line.getOffset();
				int prevPos= Math.max(offset - 1, 0);
				boolean isFirstTokenOnLine= fDocument.get(lineOffset, prevPos + 1 - lineOffset).trim().length() == 0;
				//int prevToken= fScanner.previousToken(prevPos, ModelicaHeuristicScanner.UNBOUND);
				//boolean bracelessBlockStart= true;

				switch (nextToken)
				{
					case Symbols.TokenPACKAGE:
						break;
					case Symbols.TokenMODEL:
						indent= true;
						break;

					case Symbols.TokenELSE:
						danglingElse= true;
						break;
					case Symbols.TokenCASE:
						if (isFirstTokenOnLine)
							matchCase= true;
						break;

					case Symbols.TokenEND: // closing braces get unindented
						if (isFirstTokenOnLine)
							matchEnd= true;
						break;
					case Symbols.TokenTHEN: // then gets unindented
						if (isFirstTokenOnLine)
							unindent= true;
						break;
					case Symbols.TokenEQUATION:
						if (isFirstTokenOnLine)
							matchCaseOrScope= true;
						break;
					case Symbols.TokenALGORITHM:
						if (isFirstTokenOnLine)
							unindent= true;
						break;
					case Symbols.TokenRPAREN:
						if (isFirstTokenOnLine)
							matchParen= true;
						break;
				}
			} catch (BadLocationException e) {
			}
		}
		else
		{
			// don't assume an else could come if we are at the end of file
			danglingElse= false;
		}

		int ref= findReferencePosition(offset, danglingElse, matchEnd, matchParen, matchCase, matchCaseOrScope);
		if (unindent)
			fIndent--;
		if (indent)
			fIndent++;
		return ref;
	}

	/**
	 * Returns the reference position regarding to indentation for <code>position</code>,
	 * or <code>NOT_FOUND</code>.<code>fIndent</code> will contain the
	 * relative indentation (in indentation units, not characters) after the
	 * call. If there is a special alignment (e.g. for a method declaration
	 * where parameters should be aligned), <code>fAlign</code> will contain
	 * the absolute position of the alignment reference in <code>fDocument</code>,
	 * otherwise <code>fAlign</code> is set to <code>ModelicaHeuristicScanner.NOT_FOUND</code>.
	 *
	 * @param offset the offset for which the reference is computed
	 * @param danglingElse whether a dangling else should be assumed at <code>position</code>
	 * @param matchBrace whether the position of the matching brace should be
	 *            returned instead of doing code analysis
	 * @param matchParen whether the position of the matching parenthesis
	 *            should be returned instead of doing code analysis
	 * @param matchCase whether the position of a switch statement reference
	 *            should be returned (either an earlier case statement or the
	 *            switch block brace)
	 * @return the reference statement relative to which <code>position</code>
	 *         should be indented, or {@link ModelicaHeuristicScanner#NOT_FOUND}
	 */
	public int findReferencePosition(
			int offset, boolean danglingElse,
			boolean matchEnd, boolean matchParen,
			boolean matchCase, boolean matchCaseOrScope)
	{
		fIndent= 0; // the indentation modification
		fAlign= ModelicaHeuristicScanner.NOT_FOUND;
		fPosition= offset;

		// forward cases
		// an unindentation happens sometimes if the next token is special, namely on braces, parens and case labels
		// align braces, but handle the case where we align with the method declaration start instead of
		// the opening brace.
		if (matchEnd)
		{
			if (skipModelicaScope())
			{
				try
				{
					// align with the opening scope that is on a line by its own
					int lineOffset= fDocument.getLineOffset(fLine);
					// return the position of the first token on the line.
					return findReferencePosition(lineOffset);
					//if (lineOffset <= fPosition && fDocument.get(lineOffset, fPosition - lineOffset).trim().length() == 0)
					//	return fPosition;
				}
				catch (BadLocationException e)
				{
					// concurrent modification - walk default path
				}
				// if the opening brace is not on the start of the line, skip to the start
				int pos= skipToStatementStart(true, true);
				fIndent= 0; // indent is aligned with reference position
				return pos;
			}
			else
			{
				// if we can't find the matching brace, the heuristic is to unindent
				// by one against the normal position
				int pos= findReferencePosition(offset, danglingElse, false, matchParen, matchCase, matchCaseOrScope);
				fIndent--;
				return pos;
			}
		}

		// align parenthesis'
		if (matchParen)
		{
			if (skipScope(Symbols.TokenLPAREN, Symbols.TokenRPAREN))
			{
				return fPosition;
			}
			else {
				// if we can't find the matching paren, the heuristic is to unindent
				// by one against the normal position
				int pos= findReferencePosition(offset, danglingElse, matchEnd, false, matchCase, matchCaseOrScope);
				fIndent--;
				return pos;
			}
		}

		// the only reliable way to get case labels aligned (due to many different styles of using braces in a block)
		// is to go for another case statement, or the scope opening brace
		if (matchCase)
		{
			return matchCaseAlignment();
		}

		// forward cases
		// an unindentation happens if we are at the start of an equation section and not within
		// an algorithm section.
		// search upwards for case or statement start
		if (matchCaseOrScope)
		{
			return matchCaseOrScopeAlignment();
		}

		nextToken();
		switch (fToken)
		{
			case Symbols.TokenGREATERTHAN:
			case Symbols.TokenEND:
				// skip the block and fall through
				// if we can't complete the scope, reset the scan position
				int pos= fPosition;
				if (!skipScope())
					fPosition= pos;
			case Symbols.TokenSEMICOLON:
				// this is the 90% case: after a statement block
				// the end of the previous statement / block previous.end
				// search to the end of the statement / block before the previous; the token just after that is previous.start
				return skipToStatementStart(danglingElse, true);

			// scope introduction: special treat who special is
			case Symbols.TokenEQUATION:
			case Symbols.TokenALGORITHM:
			case Symbols.TokenLOCAL:
			case Symbols.TokenMATCH:
			case Symbols.TokenMATCHCONTINUE:
				return handleScopeIntroduction(offset + 1);
			case Symbols.TokenTHEN:
				try
				{
					IRegion thenLine= fDocument.getLineInformationOfOffset(offset);
					int lineOffset= thenLine.getOffset();
					int prevPos= Math.max(offset - 1, 0);
					boolean isFirstTokenOnLine= fDocument.get(lineOffset, prevPos + 1 - lineOffset).trim().length() == 0;
					if (isFirstTokenOnLine)
						return handleScopeIntroduction(offset + 1);
					else
						return skipToPreviousListItemOrListStart();
				}
				catch (BadLocationException e) {
					return skipToPreviousListItemOrListStart();
				}

			// scope introduction: special treat who special is
			case Symbols.TokenLPAREN:
			case Symbols.TokenLBRACE:
			case Symbols.TokenLBRACKET:
				return handleScopeIntroduction(offset + 1);

			case Symbols.TokenEOF:
				// trap when hitting start of document
				return ModelicaHeuristicScanner.NOT_FOUND;

			case Symbols.TokenEQUAL:
				// indent assignments
				fIndent= prefAssignmentIndent();
				return fPosition;

			// indentation for blockless introducers:
			case Symbols.TokenWHILE:
			case Symbols.TokenELSE:
				fIndent= prefSimpleIndent();
				return fPosition;

			case Symbols.TokenRPAREN:
				int line= fLine;
				if (skipScope(Symbols.TokenLPAREN, Symbols.TokenRPAREN)) {
					int scope= fPosition;
					nextToken();
					if (fToken == Symbols.TokenCASE || fToken == Symbols.TokenMATCH ||
						fToken == Symbols.TokenMATCHCONTINUE ||
						fToken == Symbols.TokenIF || fToken == Symbols.TokenWHILE ||
						fToken == Symbols.TokenFOR)
					{
						fIndent= prefSimpleIndent();
						return fPosition;
					}
					fPosition= scope;
					if (looksLikeMethodDecl())
					{
						return skipToStatementStart(danglingElse, false);
					}
				}
				// restore
				fPosition= offset;
				fLine= line;
				// else: fall through to default

			case Symbols.TokenCOMMA:
				// inside a list of some type
				// easy if there is already a list item before with its own indentation - we just align
				// if not: take the start of the list ( LPAREN, LBRACE, LBRACKET ) and either align or
				// indent by list-indent
			default:
				// inside whatever we don't know about: similar to the list case:
				// if we are inside a continued expression, then either align with a previous line that has indentation
				// or indent from the expression start line (either a scope introducer or the start of the expr).
				return skipToPreviousListItemOrListStart();

		}
	}

	/**
	 * Skips to the start of a statement that ends at the current position.
	 *
	 * @param danglingElse whether to indent aligned with the last <code>if</code>
	 * @param isInBlock whether the current position is inside a block, which limits the search scope to the next scope introducer
	 * @return the reference offset of the start of the statement
	 */
	private int skipToStatementStart(boolean danglingElse, boolean isInBlock)
	{
		final int NOTHING= 0;
		final int READ_PARENS= 1;
		final int READ_IDENT= 2;
		int mayBeMethodBody= NOTHING;
		boolean isTypeBody= false;
		while (true)
		{
			nextToken();

			if (isInBlock)
			{
				switch (fToken)
				{

					case Symbols.TokenPACKAGE:
						return fPosition;

					// exit on all block introducers
					case Symbols.TokenIF:
					case Symbols.TokenELSE:
					// not a scope introducer in modelica
					//case Symbols.TokenCOLON:
					case Symbols.TokenWHILE:
					case Symbols.TokenFOR:
					case Symbols.TokenCLASS:
					case Symbols.TokenMODEL:
					case Symbols.TokenFUNCTION:
					case Symbols.TokenUNIONTYPE:
					case Symbols.TokenRECORD:
					case Symbols.TokenALGORITHM:
					case Symbols.TokenEQUATION:
					case Symbols.TokenLOCAL:
						fIndent++;
						return fPosition;

					case Symbols.TokenENUMERATION:
						isTypeBody= true;
						break;

					case Symbols.TokenMATCH:
					case Symbols.TokenMATCHCONTINUE:
						fIndent= prefCaseIndent();
						return fPosition;
				}
			}

			switch (fToken)
			{
				// scope introduction through: LPAREN, LBRACE, LBRACKET
				// search stop on SEMICOLON, RBRACE, COLON, EOF
				// -> the next token is the start of the statement (i.e. previousPos when backward scanning)
				case Symbols.TokenLPAREN:
				case Symbols.TokenLBRACE:
				case Symbols.TokenLBRACKET:
				// doen't work in modelica as we may have a comment before the ';'
				//case Symbols.TokenSEMICOLON:
				case Symbols.TokenEOF:
					if (isInBlock)
						fIndent= getBlockIndent(mayBeMethodBody == READ_IDENT, isTypeBody);
					// else: fIndent set by previous calls
					return fPreviousPos;

				case Symbols.TokenEND:
					if (skipModelicaScope())
						continue;

				case Symbols.TokenRBRACE:
					// RBRACE is a little tricky: it can be the end of an array definition, but
					// usually it is the end of a previous block
					int pos= fPreviousPos; // store state
					if(skipScope())
					{
						continue; // it's an array
					}
					else
					{
						if (isInBlock)
							fIndent= getBlockIndent(mayBeMethodBody == READ_IDENT, isTypeBody);
						return pos; // it's not - do as with all the above
					}

				// scopes: skip them
				case Symbols.TokenRPAREN:
					if (isInBlock)
						mayBeMethodBody= READ_PARENS;
				case Symbols.TokenRBRACKET:
				case Symbols.TokenGREATERTHAN:
					pos= fPreviousPos;
					if (skipScope())
						break;
					else
						return pos;

				// IF / ELSE: align the position after the conditional block with the if
				// so we are ready for an else, except if danglingElse is false
				// in order for this to work, we must skip an else to its if
				case Symbols.TokenIF:
					if (danglingElse)
						return fPosition;
					else
						break;
				case Symbols.TokenELSE:
					// skip behind the next if, as we have that one covered
					pos= fPosition;
					if (skipNextIF())
						break;
					else
						return pos;

				case Symbols.TokenWHILE:
					// this one is tricky: while can be the start of a while loop
					// or the end of a do - while
					pos= fPosition;
					if (hasMatchingDo()) {
						// continue searching from the DO on
						break;
					} else {
						// continue searching from the WHILE on
						fPosition= pos;
						break;
					}
				case Symbols.TokenIDENT:
					if (mayBeMethodBody == READ_PARENS)
						mayBeMethodBody= READ_IDENT;
					break;

				default:
					// keep searching

			}

		}
	}

	private int getBlockIndent(boolean isMethodBody, boolean isTypeBody) {
		if (isTypeBody)
			return prefTypeIndent() + (prefIndentBracesForTypes() ? 1 : 0);
		else if (isMethodBody)
			return prefMethodBodyIndent() + (prefIndentBracesForMethods() ? 1 : 0);
		else
			return fIndent;
	}

	/**
	 * Returns as a reference any previous <code>switch</code> labels (<code>case</code>
	 * or <code>default</code>) or the offset of the brace that scopes the switch
	 * statement. Sets <code>fIndent</code> to <code>prefCaseIndent</code> upon
	 * a match.
	 *
	 * @return the reference offset for a <code>switch</code> label
	 */
	private int matchCaseAlignment() {
		while (true) {
			nextToken();
			switch (fToken) {
				// invalid cases: another case label or an LBRACE must come before a case
				// -> bail out with the current position
				case Symbols.TokenLPAREN:
				case Symbols.TokenLBRACKET:
				case Symbols.TokenEOF:
					return fPosition;
				case Symbols.TokenMATCHCONTINUE:
				case Symbols.TokenMATCH:
					fIndent= prefCaseIndent() + 1;
					return fPosition;
				case Symbols.TokenCASE:
					// align with previous label
					fIndent= 0;
					return fPosition;

				// scopes: skip them
				case Symbols.TokenRPAREN:
				case Symbols.TokenRBRACKET:
				case Symbols.TokenRBRACE:
				case Symbols.TokenGREATERTHAN:
				case Symbols.TokenEND:
					skipScope();
					break;

				default:
					// keep searching
					continue;

			}
		}
	}

	/**
	 * Returns as a reference any previous <code>switch</code> labels (<code>case</code>
	 * or <code>default</code>) or the offset of the brace that scopes the switch
	 * statement. Sets <code>fIndent</code> to <code>prefCaseIndent</code> upon
	 * a match.
	 *
	 * @return the reference offset for a <code>switch</code> label
	 */
	private int matchCaseOrScopeAlignment()
	{
		while (true)
		{
			nextToken();
			switch (fToken)
			{
				// -> bail out with the current position
				case Symbols.TokenEOF:
					return fPosition;
				case Symbols.TokenMATCHCONTINUE:
				case Symbols.TokenMATCH:
					fIndent= prefCaseIndent() + 1;
					return fPosition;
				case Symbols.TokenCASE:
					// align with previous label
					fIndent= 1;
					return fPosition;

				// scopes: skip them
				case Symbols.TokenRPAREN:
				case Symbols.TokenRBRACKET:
				case Symbols.TokenRBRACE:
				case Symbols.TokenGREATERTHAN:
				case Symbols.TokenEND:
					skipScope();
					break;

				// scope start, align with it.
				case Symbols.TokenFUNCTION:
				case Symbols.TokenMODEL:
				case Symbols.TokenPACKAGE:
				case Symbols.TokenCLASS:
					fIndent= 0;
					return fPosition;

				default:
					// keep searching
					continue;

			}
		}
	}

	/**
	 * Returns the reference position for a list element. The algorithm
	 * tries to match any previous indentation on the same list. If there is none,
	 * the reference position returned is determined depending on the type of list:
	 * The indentation will either match the list scope introducer (e.g. for
	 * method declarations), so called deep indents, or simply increase the
	 * indentation by a number of standard indents. See also {@link #handleScopeIntroduction(int)}.
	 *
	 * @return the reference position for a list item: either a previous list item
	 * that has its own indentation, or the list introduction start.
	 */
	private int skipToPreviousListItemOrListStart()
	{
		int startLine= fLine;
		int startPosition= fPosition;
		while (true)
		{
			nextToken();

			// if any line item comes with its own indentation, adapt to it
			if (fLine < startLine)
			{
				try
				{
					int lineOffset= fDocument.getLineOffset(startLine);
					int bound= Math.min(fDocument.getLength(), startPosition + 1);
					fAlign= fScanner.findNonWhitespaceForwardInAnyPartition(lineOffset, bound);
				}
				catch (BadLocationException e)
				{
					// ignore and return just the position
				}
				return startPosition;
			}

			switch (fToken) {
				// scopes: skip them
				case Symbols.TokenRPAREN:
				case Symbols.TokenRBRACKET:
				case Symbols.TokenRBRACE:
				case Symbols.TokenGREATERTHAN:
					skipScope();
					break;

				case Symbols.TokenEND:
					skipModelicaScope();
					break;

				// scope introduction: special treat who special is
				case Symbols.TokenLPAREN:
				case Symbols.TokenLBRACE:
				case Symbols.TokenLBRACKET:
					return handleScopeIntroduction(startPosition + 1);

				case Symbols.TokenSEMICOLON:
					return fPosition;
				case Symbols.TokenEOF:
					return 0;

				case Symbols.TokenPACKAGE:
					return handleScopeIntroduction(startPosition + 1);

				// scope introduction: special treat who special is
				case Symbols.TokenMODEL:
				case Symbols.TokenRECORD:
				case Symbols.TokenUNIONTYPE:
				case Symbols.TokenFUNCTION:
				case Symbols.TokenALGORITHM:
				case Symbols.TokenEQUATION:
				case Symbols.TokenCLASS:
				case Symbols.TokenMATCH:
				case Symbols.TokenMATCHCONTINUE:
				case Symbols.TokenLOCAL:
				case Symbols.TokenBLOCK:
				case Symbols.TokenCONNECTOR:
					return handleScopeIntroduction(startPosition + 1);

			}
		}
	}

	/**
	 * Skips a scope and positions the cursor (<code>fPosition</code>) on the
	 * token that opens the scope. Returns <code>true</code> if a matching peer
	 * could be found, <code>false</code> otherwise. The current token when calling
	 * must be one out of <code>Symbols.TokenRPAREN</code>, <code>Symbols.TokenRBRACE</code>,
	 * and <code>Symbols.TokenRBRACKET</code>.
	 *
	 * @return <code>true</code> if a matching peer was found, <code>false</code> otherwise
	 */
	private boolean skipScope() {
		switch (fToken) {
			case Symbols.TokenRPAREN:
				return skipScope(Symbols.TokenLPAREN, Symbols.TokenRPAREN);
			case Symbols.TokenRBRACKET:
				return skipScope(Symbols.TokenLBRACKET, Symbols.TokenRBRACKET);
			case Symbols.TokenRBRACE:
				return skipScope(Symbols.TokenLBRACE, Symbols.TokenRBRACE);
			case Symbols.TokenGREATERTHAN:
				int storedPosition= fPosition;
				int storedToken= fToken;
				nextToken();
				switch (fToken) {
					case Symbols.TokenIDENT:
						if (!isGenericStarter(getTokenContent()))
							break;
					case Symbols.TokenGREATERTHAN:
						if (skipScope(Symbols.TokenLESSTHAN, Symbols.TokenGREATERTHAN))
							return true;
				}
				// <> are harder to detect - restore the position if we fail
				fPosition= storedPosition;
				fToken= storedToken;
				return false;

			case Symbols.TokenEND:
				return skipModelicaScope();

			default:
				Assert.isTrue(false);
				return false;
		}
	}

	/**
	 * Returns the contents of the current token.
	 *
	 * @return the contents of the current token
	 * @since 3.1
	 */
	private CharSequence getTokenContent() {
		return new DocumentCharacterIterator(fDocument, fPosition, fPreviousPos);
	}

	/**
	 * Returns <code>true</code> if <code>identifier</code> is probably a
	 * type variable or type name, <code>false</code> if it is rather not.
	 * This is a heuristic.
	 *
	 * @param identifier the identifier to check
	 * @return <code>true</code> if <code>identifier</code> is probably a
	 *         type variable or type name, <code>false</code> if not
	 * @since 3.1
	 */
	private boolean isGenericStarter(CharSequence identifier) {
		/* This heuristic allows any identifiers if they start with an upper
		 * case. This will fail when a comparison is made with constants:
		 *
		 * if (MAX > foo)
		 *
		 * will try to find the matching '<' which will never come
		 *
		 * Also, it will fail on lower case types and type variables
		 */
		int length= identifier.length();
		if (length > 0 && Character.isUpperCase(identifier.charAt(0))) {
			for (int i= 0; i < length; i++) {
				if (identifier.charAt(i) == '_')
					return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * Handles the introduction of a new scope. The current token must be one out
	 * of <code>Symbols.TokenLPAREN</code>, <code>Symbols.TokenLBRACE</code>,
	 * and <code>Symbols.TokenLBRACKET</code>. Returns as the reference position
	 * either the token introducing the scope or - if available - the first
	 * java token after that.
	 *
	 * <p>Depending on the type of scope introduction, the indentation will align
	 * (deep indenting) with the reference position (<code>fAlign</code> will be
	 * set to the reference position) or <code>fIndent</code> will be set to
	 * the number of indentation units.
	 * </p>
	 *
	 * @param bound the bound for the search for the first token after the scope
	 * introduction.
	 * @return the indent
	 */
	private int handleScopeIntroduction(int bound)
	{
		switch (fToken)
		{
			// scope introduction: special treat who special is
			case Symbols.TokenLPAREN:
				int pos= fPosition; // store
				// special: method declaration deep indentation
				if (looksLikeMethodDecl())
				{
					if (prefMethodDeclDeepIndent())
						return setFirstElementAlignment(pos, bound);
					else {
						fIndent= prefMethodDeclIndent();
						return pos;
					}
				}
				else
				{
					fPosition= pos;
					if (looksLikeMethodCall())
					{
						if (prefMethodCallDeepIndent())
							return setFirstElementAlignment(pos, bound);
						else
						{
							fIndent= prefMethodCallIndent();
							return pos;
						}
					} else if (prefParenthesisDeepIndent())
						return setFirstElementAlignment(pos, bound);
				}

				// normal: return the parenthesis as reference
				fIndent= prefParenthesisIndent();
				return pos;

			case Symbols.TokenPACKAGE:
				pos= fPosition; // store
				fIndent= 0; // do not indent on package
				return pos;

			case Symbols.TokenFUNCTION:
			case Symbols.TokenMODEL:
			case Symbols.TokenCLASS:
			case Symbols.TokenRECORD:
			case Symbols.TokenUNIONTYPE:
			case Symbols.TokenMATCH:
			case Symbols.TokenMATCHCONTINUE:
			case Symbols.TokenEQUATION:
			case Symbols.TokenALGORITHM:
			case Symbols.TokenLOCAL:
			case Symbols.TokenTHEN:
				pos= fPosition; // store
				fIndent= prefBlockIndent();
				return pos;

			case Symbols.TokenLBRACE:
				pos= fPosition; // store
				fIndent= prefBlockIndent();
				return pos;

			case Symbols.TokenLBRACKET:
				pos= fPosition; // store

				// special: method declaration deep indentation
				if (prefArrayDimensionsDeepIndent()) {
					return setFirstElementAlignment(pos, bound);
				}

				// normal: return the bracket as reference
				fIndent= prefBracketIndent();
				return pos; // restore

			default:
				Assert.isTrue(false);
				return -1; // dummy
		}
	}

	/**
	 * Sets the deep indent offset (<code>fAlign</code>) to either the offset
	 * right after <code>scopeIntroducerOffset</code> or - if available - the
	 * first Modelica token after <code>scopeIntroducerOffset</code>, but before
	 * <code>bound</code>.
	 *
	 * @param scopeIntroducerOffset the offset of the scope introducer
	 * @param bound the bound for the search for another element
	 * @return the reference position
	 */
	private int setFirstElementAlignment(int scopeIntroducerOffset, int bound) {
		int firstPossible= scopeIntroducerOffset + 1; // align with the first position after the scope intro
		fAlign= fScanner.findNonWhitespaceForwardInAnyPartition(firstPossible, bound);
		if (fAlign == ModelicaHeuristicScanner.NOT_FOUND)
			fAlign= firstPossible;
		return fAlign;
	}


	/**
	 * Skips over the next <code>if</code> keyword. The current token when calling
	 * this method must be an <code>else</code> keyword. Returns <code>true</code>
	 * if a matching <code>if</code> could be found, <code>false</code> otherwise.
	 * The cursor (<code>fPosition</code>) is set to the offset of the <code>if</code>
	 * token.
	 *
	 * @return <code>true</code> if a matching <code>if</code> token was found, <code>false</code> otherwise
	 */
	private boolean skipNextIF() {
		Assert.isTrue(fToken == Symbols.TokenELSE);

		while (true) {
			nextToken();
			switch (fToken) {
				// scopes: skip them
				case Symbols.TokenRPAREN:
				case Symbols.TokenRBRACKET:
				case Symbols.TokenRBRACE:
				case Symbols.TokenGREATERTHAN:
					skipScope();
					break;

				case Symbols.TokenIF:
					// found it, return
					return true;
				case Symbols.TokenELSE:
					// recursively skip else-if blocks
					skipNextIF();
					break;

				// shortcut scope starts
				case Symbols.TokenLPAREN:
				case Symbols.TokenLBRACE:
				case Symbols.TokenLBRACKET:
				case Symbols.TokenEOF:
					return false;
				}
		}
	}


	/**
	 * while(condition); is ambiguous when parsed backwardly, as it is a valid
	 * statement by its own, so we have to check whether there is a matching
	 * do. A <code>do</code> can either be separated from the while by a
	 * block, or by a single statement, which limits our search distance.
	 *
	 * @return <code>true</code> if the <code>while</code> currently in
	 *         <code>fToken</code> has a matching <code>do</code>.
	 */
	private boolean hasMatchingDo()
	{
		Assert.isTrue(fToken == Symbols.TokenWHILE);
		nextToken();
		switch (fToken)
		{
			case Symbols.TokenRBRACE:
				skipScope(); // and fall thru
			case Symbols.TokenSEMICOLON:
				skipToStatementStart(false, false);
				return fToken == Symbols.TokenEND;
		}
		return false;
	}

	/**
	 * Skips brackets if the current token is a RBRACKET. There can be nothing
	 * but whitespace in between, this is only to be used for <code>[]</code> elements.
	 *
	 * @return <code>true</code> if a <code>[]</code> could be scanned, the
	 *         current token is left at the LBRACKET.
	 */
	private boolean skipBrackets() {
		if (fToken == Symbols.TokenRBRACKET) {
			nextToken();
			if (fToken == Symbols.TokenLBRACKET) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Reads the next token in backward direction from the heuristic scanner
	 * and sets the fields <code>fToken, fPreviousPosition</code> and <code>fPosition</code>
	 * accordingly.
	 */
	private void nextToken() {
		nextToken(fPosition);
	}

	/**
	 * Reads the next token in backward direction of <code>start</code> from
	 * the heuristic scanner and sets the fields <code>fToken, fPreviousPosition</code>
	 * and <code>fPosition</code> accordingly.
	 *
	 * @param start the start offset from which to scan backwards
	 */
	private void nextToken(int start) {
		fToken= fScanner.previousToken(start - 1, ModelicaHeuristicScanner.UNBOUND);
		fPreviousPos= start;
		fPosition= fScanner.getPosition() + 1;
		try
		{
			fLine= fDocument.getLineOfOffset(fPosition);
		}
		catch (BadLocationException e)
		{
			fLine= -1;
		}
	}

	/**
	 * Reads the next token in forward direction of <code>start</code> from
	 * the heuristic scanner and sets the fields <code>fToken, fPreviousPosition</code>
	 * and <code>fPosition</code> accordingly.
	 *
	 * @param start the start offset from which to scan forward
	 */
	private void previousToken(int start)
	{
		fToken= fScanner.nextToken(start, ModelicaHeuristicScanner.UNBOUND);
		fPreviousPos= start;
		fPosition= fScanner.getPosition() + 1;
		try
		{
			fLine= fDocument.getLineOfOffset(fPosition);
		}
		catch (BadLocationException e)
		{
			fLine= -1;
		}
	}

	/**
	 * Returns <code>true</code> if the current tokens look like a method
	 * declaration header (i.e. only the return type and method name). The
	 * heuristic calls <code>nextToken</code> and expects an identifier
	 * (method name) and a type declaration (an identifier with optional
	 * brackets) which also covers the visibility modifier of constructors; it
	 * does not recognize package visible constructors.
	 *
	 * @return <code>true</code> if the current position looks like a method
	 *         declaration header.
	 */
	private boolean looksLikeMethodDecl() {
		/*
		 * TODO This heuristic does not recognize package private constructors
		 * since those do have neither type nor visibility keywords.
		 * One option would be to go over the parameter list, but that might
		 * be empty as well, or not typed in yet - hard to do without an AST...
		 */

		nextToken();
		if (fToken == Symbols.TokenIDENT) { // method name
			do nextToken();
			while (skipBrackets()); // optional brackets for array valued return types

			return fToken == Symbols.TokenIDENT; // return type name

		}
		return false;
	}

	/**
	 * Returns <code>true</code> if the current tokens look like a method
	 * call header (i.e. an identifier as opposed to a keyword taking parenthesized
	 * parameters such as <code>if</code>).
	 * <p>The heuristic calls <code>nextToken</code> and expects an identifier
	 * (method name).
	 *
	 * @return <code>true</code> if the current position looks like a method call
	 *         header.
	 */
	private boolean looksLikeMethodCall() {
		// TODO [5.0] add awareness for constructor calls with generic types: new ArrayList<String>()
		nextToken();
		return fToken == Symbols.TokenIDENT; // method name
	}

	/**
	 * Scans tokens for the matching opening peer. The internal cursor
	 * (<code>fPosition</code>) is set to the offset of the opening peer if found.
	 *
	 * @param openToken the opening peer token
	 * @param closeToken the closing peer token
	 * @return <code>true</code> if a matching token was found, <code>false</code>
	 *         otherwise
	 */
	private boolean skipScope(int openToken, int closeToken) {

		int depth= 1;

		while (true)
		{
			nextToken();

			if (fToken == closeToken) {
				depth++;
			} else if (fToken == openToken) {
				depth--;
				if (depth == 0)
					return true;
			} else if (fToken == Symbols.TokenEOF) {
					return false;
			}
		}
	}

	class Scope
	{
		String afterEnd      = null;
		int    afterEndToken = -1;

		public Scope(String ae, int aft)
		{
			this.afterEnd = ae;
			this.afterEndToken = aft;
			// System.out.println("push->[" + afterEnd + ", " + afterEndToken + "]");
		}
	}

	/**
	 * Scans tokens for the matching opening peer. The internal cursor
	 * (<code>fPosition</code>) is set to the offset of the opening peer if found.
	 *
	 * @param openToken the opening peer token
	 * @param closeToken the closing peer token
	 * @return <code>true</code> if a matching token was found, <code>false</code>
	 *         otherwise
	 */
	private boolean skipModelicaScope()
	{
		Stack<Scope> s = new Stack<Scope>();
		/* save the state if we need it */
//		int fSavedToken = fToken;
//		int fSavedPos   = fPosition;
		/* see what is after 'end' */
		previousToken(fPosition); /* skip 'end' */
		previousToken(fPosition); /* move past end */
//		int fEndPrevToken = fToken;
//		int fEndPos = fPosition;
		String afterEnd = null;
		if (fToken == Symbols.TokenIDENT)
			afterEnd = fScanner.fLastIdent;
		s.push(new Scope(afterEnd, fToken));

		/* go back to the start of 'end' */
		nextToken(fPosition);
		nextToken(fPosition);

		boolean skip = false;

		int depth = 1;

		while (true)
		{
			if (!skip)
				nextToken();
			switch (fToken)
			{
			case Symbols.TokenIDENT:
				/* save the token */
				String token = fScanner.fLastIdent;
				if (s.peek().afterEnd != null && s.peek().afterEnd.equals(fScanner.fLastIdent))
				{
					/* if the next token is a scope introducer, pop the stack */
					nextToken();
					if (fToken == Symbols.TokenEOF)
						return false;
					switch (fToken)
					{
						case Symbols.TokenFUNCTION:
						case Symbols.TokenMODEL:
						case Symbols.TokenPACKAGE:
						case Symbols.TokenCLASS:
						case Symbols.TokenBLOCK:
						case Symbols.TokenCONNECTOR:
						case Symbols.TokenRECORD:
						case Symbols.TokenUNIONTYPE:
							depth--;
							s.pop();
							if (depth == 0)
								return true;
					}
					//	skip the reading of next token if we haven't consumed this one
					skip = true;
				}
				else
				{
					/* if the next token is 'end' push another scope */
					nextToken();
					if (fToken == Symbols.TokenEOF)
						return false;
					if (fToken == Symbols.TokenEND)
					{
						depth++;
						s.push(new Scope(token, Symbols.TokenIDENT));
					}
					skip = true;
				}
				break;

			case Symbols.TokenMATCH:
			case Symbols.TokenMATCHCONTINUE:
			case Symbols.TokenFOR:
			case Symbols.TokenWHILE:
			case Symbols.TokenIF:
			case Symbols.TokenWHEN:
				int tokenID = fToken;
				nextToken();
				if (fToken == Symbols.TokenEOF)
					return false;
				if (fToken == Symbols.TokenEND)
				{
					depth++;
					s.push(new Scope(null, tokenID));
				}
				else
				{
					if (s.peek().afterEndToken == tokenID)
						s.pop();
					depth--;
					/* go back to the token after */
					previousToken(fPosition);
					if (depth == 0)
						return true;
				}
				skip = true;
				break;

			case Symbols.TokenEOF:
					return false;

			default:
				skip = false;
			}
		}
	}

	private boolean prefUseTabs() {
		/* return useTabs; */
		return true;
	}

	private int prefTabSize() {
		return 2; /*TODO fix so that this is added in the preference page */
 	}

	private int prefIndentationSize() {
		return 2; /*TODO fix so that this is added in the preference page */
	}

	private boolean prefArrayDimensionsDeepIndent() {
		return true; // sensible default, no formatter setting
	}


	private int prefCaseIndent() {
		return 0; // sun standard
	}

	private int prefAssignmentIndent() {
		return 0; // prefBlockIndent();
	}

	private int prefSimpleIndent()
	{
		if (prefIndentBracesForBlocks() && prefBlockIndent() == 0)
			return 1;
		else return prefBlockIndent();
	}

	private int prefBracketIndent() {
		return prefBlockIndent();
	}

	private boolean prefMethodDeclDeepIndent() {
		return true;
	}

	private int prefMethodDeclIndent() {
		return 1;
	}

	private boolean prefMethodCallDeepIndent() {
		return false; // sensible default
	}

	private int prefMethodCallIndent() {
		return 1; // sensible default
	}

	private boolean prefParenthesisDeepIndent() {
		return false; // sensible default
	}

	private int prefParenthesisIndent() {
		return prefContinuationIndent();
	}

	private int prefBlockIndent() {
		return 1; // sensible default
	}

	private int prefMethodBodyIndent() {
		return 1; // sensible default
	}

	private int prefTypeIndent() {
		return 1; // sensible default
	}

	private boolean prefIndentBracesForBlocks() {
		return false; // sensible default
	}

	private boolean prefIndentBracesForMethods() {
		return false; // sensible default
	}

	private boolean prefIndentBracesForTypes() {
		return false; // sensible default
	}

	private int prefContinuationIndent() {
		return 2; // sensible default
	}

}
