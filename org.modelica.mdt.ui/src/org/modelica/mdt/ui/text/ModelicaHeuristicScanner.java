/*******************************************************************************
 *
 *******************************************************************************/
package org.modelica.mdt.ui.text;

import java.util.Arrays;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.TypedRegion;

/**
 * Utility methods for heuristic based Modelica manipulations in an incomplete Modelica source file.
 *
 * <p>An instance holds some internal position in the document and is therefore not threadsafe.</p>
 *
 * @since 3.0
 */
public final class ModelicaHeuristicScanner implements Symbols
{

	String fLastIdent = null;

	static public class key
	{
		String token;
		int tokenID;
		public key(
			String token,
			int tokenID )
		{
			this.token    = token;    /* the name of the keyword */
			this.tokenID  = tokenID;  /* the tokenID */
		}

		String getToken() { return token; }
		int getTokenID() { return tokenID; }
	}
	/* keywords */
	static public key[] keywords =
		{
			new key("algorithm",  	TokenALGORITHM),
			new key("and",       	TokenAND),
			new key("annotation",	TokenANNOTATION),
			new key("assert",		TokenASSERT),
			new key("block",    	TokenBLOCK),
			new key("break", 		TokenBREAK),
			new key("class", 		TokenCLASS),
			new key("connect", 		TokenCONNECT),
			new key("expandable", 	TokenEXPANDABLE),
			new key("connector", 	TokenCONNECTOR),
			new key("operator", 	TokenOPERATOR),
			new key("constrainedby", 	TokenCONSTRAINEDBY),
			new key("constant", 	TokenCONSTANT),
			new key("der", 			TokenDER),
			new key("discrete", 	TokenDISCRETE),
			new key("each", 		TokenEACH),
			new key("else", 		TokenELSE),
			new key("elseif", 		TokenELSEIF),
			new key("elsewhen", 	TokenELSEWHEN),
			new key("encapsulated", TokenENCAPSULATED),
			new key("end", 			TokenEND),
			new key("enumeration", 	TokenENUMERATION),
			new key("equation", 	TokenEQUATION),
			new key("extends", 		TokenEXTENDS),
			new key("external", 	TokenEXTERNAL),
			new key("false", 		TokenFALSE),
			new key("final", 		TokenFINAL),
			new key("flow", 		TokenFLOW),
			new key("for", 			TokenFOR),
			new key("function", 	TokenFUNCTION),
			new key("if", 			TokenIF),
			new key("import", 		TokenIMPORT),
			new key("in", 			TokenIN),
			new key("initial", 		TokenINITIAL),
			new key("inner", 		TokenINNER),
			new key("input", 		TokenINPUT),
			new key("loop", 		TokenLOOP),
			new key("model", 		TokenMODEL),
			new key("not", 			TokenNOT),
			new key("or", 			TokenOR),
			new key("outer", 		TokenOUTER),
			new key("output", 		TokenOUTPUT),
			new key("overload", 	TokenOVERLOAD),
			new key("package", 		TokenPACKAGE),
			new key("parameter", 	TokenPARAMETER),
			new key("partial", 		TokenPARTIAL),
			new key("protected", 	TokenPROTECTED),
			new key("public", 		TokenPUBLIC),
			new key("record", 		TokenRECORD),
			new key("redeclare", 	TokenREDECLARE),
			new key("replacable", 	TokenREPLACEABLE),
			new key("return", 		TokenRETURN),
			new key("terminate", 	TokenTERMINATE),
			new key("then", 		TokenTHEN),
			new key("time", 		TokenTIME),
			new key("true", 		TokenTRUE),
			new key("type", 		TokenTYPE),
			new key("when", 		TokenWHEN),
			new key("while", 		TokenWHILE),
			new key("within", 		TokenWITHIN),

			/* The following are Meta-Modelica Keywords */
			new key("as", 			TokenAS),
			new key("case", 		TokenCASE),
			new key("equality", 	TokenEQUALITY),
			new key("failure", 		TokenFAILURE),
			new key("list", 		TokenLIST),
			new key("local", 		TokenLOCAL),
			new key("match", 		TokenMATCH),
			new key("matchcontinue",TokenMATCHCONTINUE),
			new key("tuple", 		TokenTUPLE),
			new key("subtypeof",    TokenSUBTYPEOF),
			new key("uniontype", 	TokenUNIONTYPE),
			new key("guard", 	    TokenGUARD),
			new key("metarecord", 	TokenRECORD),

			new key("{", 			TokenLBRACE),
			new key("}",			TokenRBRACE),
			new key("(",			TokenLPAREN),
			new key(")",			TokenRPAREN),
			new key(";",			TokenSEMICOLON),
			new key(":",			TokenCOLON),
			new key(",",			TokenCOMMA),
			new key("[",			TokenLBRACKET),
			new key("]",			TokenRBRACKET),
			new key("=",			TokenEQUAL),
			new key("<",			TokenLESSTHAN),
			new key(">",			TokenGREATERTHAN)
		};

	/**
	 * Returned by all methods when the requested position could not be found, or if a
	 * {@link BadLocationException} was thrown while scanning.
	 */
	public static final int NOT_FOUND= -1;

	/**
	 * Special bound parameter that means either -1 (backward scanning) or
	 * <code>fDocument.getLength()</code> (forward scanning).
	 */
	public static final int UNBOUND= -2;

	/**
	 * Specifies the stop condition, upon which the <code>scanXXX</code> methods will decide whether
	 * to keep scanning or not. This interface may implemented by clients.
	 */
	private static abstract class StopCondition
	{
		/**
		 * Instructs the scanner to return the current position.
		 *
		 * @param ch the char at the current position
		 * @param position the current position
		 * @param forward the iteration direction
		 * @return <code>true</code> if the stop condition is met.
		 */
		public abstract boolean stop(String str, int position, boolean forward);

		/**
		 * Asks the condition to return the next position to query. The default
		 * is to return the next/previous position.
		 *
		 * @return the next position to scan
		 */
		public int nextPosition(int position, boolean forward)
		{
			return forward ? position + 1 : position - 1;
		}
	}

	/**
	 * Stops upon a non-whitespace (as defined by {@link Character#isWhitespace(char)}) character.
	 */
	private static class NonWhitespace extends StopCondition
	{
		/*
		 * @see org.modelica.mdt.ui.text.ModelicaHeuristicScanner.StopCondition#stop(char)
		 */
		public boolean stop(String str, int position, boolean forward)
		{
			if (forward)
				return !Character.isWhitespace(str.charAt(0));
			else
				return !Character.isWhitespace(str.charAt(str.length()));
		}
	}

	/**
	 * Stops upon a non-whitespace character in the default partition.
	 *
	 * @see NonWhitespace
	 */
	private final class NonWhitespaceDefaultPartition extends NonWhitespace
	{
		/*
		 * @see org.modelica.mdt.ui.text.ModelicaHeuristicScanner.StopCondition#stop(char)
		 */
		public boolean stop(String str, int position, boolean forward)
		{
			return super.stop(str, position, true) && isDefaultPartition(position);
		}

		/*
		 * @see org.modelica.mdt.ui.text.ModelicaHeuristicScanner.StopCondition#nextPosition(int, boolean)
		 */
		public int nextPosition(int position, boolean forward)
		{
			ITypedRegion partition= getPartition(position);
			if (fPartition.equals(partition.getType()))
				return super.nextPosition(position, forward);

			if (forward)
			{
				int end= partition.getOffset() + partition.getLength();
				if (position < end)
					return end;
			}
			else
			{
				int offset= partition.getOffset();
				if (position > offset)
					return offset - 1;
			}
			return super.nextPosition(position, forward);
		}
	}

	/**
	 * Stops upon a non-modelica identifier (as defined by {@link Character#isModelicaIdentifierPart(char)}) character.
	 */
	private static class NonModelicaIdentifierPart extends StopCondition {
		/*
		 * @see org.modelica.mdt.ui.text.ModelicaHeuristicScanner.StopCondition#stop(char)
		 */
		public boolean stop(String str, int position, boolean forward)
		{
			if (forward)
				return !Character.isJavaIdentifierPart(str.charAt(0));
			else
				return !Character.isJavaIdentifierPart(str.charAt(str.length()));
		}
	}

	/**
	 * Stops upon a non-modelica identifier character in the default partition.
	 *
	 * @see NonModelicaIdentifierPart
	 */
	private final class NonModelicaIdentifierPartDefaultPartition extends NonModelicaIdentifierPart {
		/*
		 * @see org.modelica.mdt.ui.text.ModelicaHeuristicScanner.StopCondition#stop(char)
		 */
		public boolean stop(String str, int position, boolean forward) {
			return super.stop(str, position, true) || !isDefaultPartition(position);
		}

		/*
		 * @see org.modelica.mdt.ui.text.ModelicaHeuristicScanner.StopCondition#nextPosition(int, boolean)
		 */
		public int nextPosition(int position, boolean forward) {
			ITypedRegion partition= getPartition(position);
			if (fPartition.equals(partition.getType()))
				return super.nextPosition(position, forward);

			if (forward) {
				int end= partition.getOffset() + partition.getLength();
				if (position < end)
					return end;
			} else {
				int offset= partition.getOffset();
				if (position > offset)
					return offset - 1;
			}
			return super.nextPosition(position, forward);
		}
	}

	/**
	 * Stops upon a string in the default partition that matches the given string list.
	 */
	private final class StringMatch extends StopCondition {
		private final String[] fStrings;

		/**
		 * Creates a new instance.
		 * @param ch the single character to match
		 */
		public StringMatch(String str) {
			this(new String[] {str});
		}

		/**
		 * Creates a new instance.
		 * @param chars the chars to match.
		 */
		public StringMatch(String[] strings) {
			Assert.isNotNull(strings);
			Assert.isTrue(strings.length > 0);
			fStrings= strings;
			Arrays.sort(strings);
		}

		/*
		 * @see org.modelica.mdt.ui.text.ModelicaHeuristicScanner.StopCondition#stop(char, int)
		 */
		public boolean stop(String str, int position, boolean forward) {
			return Arrays.binarySearch(fStrings, str) >= 0 && isDefaultPartition(position);
		}

		/*
		 * @see org.modelica.mdt.ui.text.ModelicaHeuristicScanner.StopCondition#nextPosition(int, boolean)
		 */
		public int nextPosition(int position, boolean forward)
		{
			ITypedRegion partition= getPartition(position);
			if (fPartition.equals(partition.getType()))
				return super.nextPosition(position, forward);

			if (forward) {
				int end= partition.getOffset() + partition.getLength();
				if (position < end)
					return end;
			} else {
				int offset= partition.getOffset();
				if (position > offset)
					return offset - 1;
			}
			return super.nextPosition(position, forward);
		}
	}

	/** The document being scanned. */
	private IDocument fDocument;
	/** The partitioning being used for scanning. */
	private String fPartitioning;
	/** The partition to scan in. */
	private String fPartition;

	/* internal scan state */

	/** the most recently read character. */
	private char fChar;
	/** the most recently read position. */
	private int fPos;

	/* preset stop conditions */
	private final StopCondition fNonWSDefaultPart= new NonWhitespaceDefaultPartition();
	private final static StopCondition fNonWS= new NonWhitespace();
	private final StopCondition fNonIdent= new NonModelicaIdentifierPartDefaultPartition();

	/**
	 * Creates a new instance.
	 *
	 * @param document the document to scan
	 * @param partitioning the partitioning to use for scanning
	 * @param partition the partition to scan in
	 */
	public ModelicaHeuristicScanner(IDocument document, String partitioning, String partition) {
		Assert.isNotNull(document);
		Assert.isNotNull(partitioning);
		Assert.isNotNull(partition);
		fDocument= document;
		fPartitioning= partitioning;
		fPartition= partition;
	}

	/**
	 * Calls <code>this(document, IModelicaPartitions.MODELICA_PARTITIONING, IDocument.DEFAULT_CONTENT_TYPE)</code>.
	 *
	 * @param document the document to scan.
	 */
	public ModelicaHeuristicScanner(IDocument document) {
		this(document, IModelicaPartitions.MODELICA_PARTITIONING, IDocument.DEFAULT_CONTENT_TYPE);
	}

	/**
	 * Returns the most recent internal scan position.
	 *
	 * @return the most recent internal scan position.
	 */
	public int getPosition() {
		return fPos;
	}

	/**
	 * Returns the next token in forward direction, starting at <code>start</code>, and not extending
	 * further than <code>bound</code>. The return value is one of the constants defined in {@link Symbols}.
	 * After a call, {@link #getPosition()} will return the position just after the scanned token
	 * (i.e. the next position that will be scanned).
	 *
	 * @param start the first character position in the document to consider
	 * @param bound the first position not to consider any more
	 * @return a constant from {@link Symbols} describing the next token
	 */
	public int nextToken(int start, int bound)
	{
		int pos= scanForward(start, bound, fNonWSDefaultPart);
		if (pos == NOT_FOUND)
			return TokenEOF;

		fPos++;

		/* here we check only for 1 char symbols */
		for(int i = 0; i < keywords.length; i++)
		{
			if (keywords[i].token.equals(String.valueOf(fChar)))
				return keywords[i].tokenID;
		}


		// else
		if (Character.isJavaIdentifierPart(fChar))
		{
			// assume an ident or keyword
			int from= pos, to;
			pos= scanForward(pos + 1, bound, fNonIdent);
			if (pos == NOT_FOUND)
				to= bound == UNBOUND ? fDocument.getLength() : bound;
			else
				to= pos;

			String identOrKeyword;
			try
			{
				identOrKeyword= fDocument.get(from, to - from);
			} catch (BadLocationException e) {
				return TokenEOF;
			}

			return getToken(identOrKeyword);


		}
		else
		{
			// operators, number literals etc
			return TokenOTHER;
		}
	}

	/**
	 * Returns the next token in backward direction, starting at <code>start</code>, and not extending
	 * further than <code>bound</code>. The return value is one of the constants defined in {@link Symbols}.
	 * After a call, {@link #getPosition()} will return the position just before the scanned token
	 * starts (i.e. the next position that will be scanned).
	 *
	 * @param start the first character position in the document to consider
	 * @param bound the first position not to consider any more
	 * @return a constant from {@link Symbols} describing the previous token
	 */
	public int previousToken(int start, int bound) {
		int pos= scanBackward(start, bound, fNonWSDefaultPart);
		if (pos == NOT_FOUND)
			return TokenEOF;

		fPos--;

		/* here we check only for 1 char symbols */
		for(int i = 0; i < keywords.length; i++)
		{
			if (keywords[i].token.equals(String.valueOf(fChar)))
				return keywords[i].tokenID;
		}

		// else
		if (Character.isJavaIdentifierPart(fChar))
		{
			// assume an ident or keyword
			int from, to= pos + 1;
			pos= scanBackward(pos - 1, bound, fNonIdent);
			if (pos == NOT_FOUND)
				from= bound == UNBOUND ? 0 : bound + 1;
			else
				from= pos + 1;

			String identOrKeyword;
			try {
				identOrKeyword= fDocument.get(from, to - from);
			} catch (BadLocationException e) {
				return TokenEOF;
			}
			return getToken(identOrKeyword);
		}
		else
		{
			// operators, number literals etc
			return TokenOTHER;
		}

	}

	/**
	 * Returns one of the keyword constants or <code>TokenIDENT</code> for a scanned identifier.
	 *
	 * @param s a scanned identifier
	 * @return one of the constants defined in {@link Symbols}
	 */
	private int getToken(String s)
	{
		Assert.isNotNull(s);
		for(int i = 0; i < keywords.length; i++)
		{
			if (keywords[i].token.equals(s))
				return keywords[i].tokenID;
		}
		fLastIdent = s;
		return TokenIDENT;
	}

	/**
	 * Returns the position of the closing peer string (forward search).
	 * Any scopes introduced by opening peers
	 * are skipped. All peers accounted for must reside in the
	 * default partition.
	 *
	 * <p>Note that <code>start</code> must not point to the opening peer, but to the first
	 * string being searched.</p>
	 *
	 * @param start the start position
	 * @param openingPeer the opening peer string (e.g. "{")
	 * @param closingPeer the closing peer string (e.g. "}")
	 * @return the matching peer string position, or <code>NOT_FOUND</code>
	 */
	public int findClosingPeer(int start, final String openingPeer, final String closingPeer)
	{
		Assert.isNotNull(fDocument);
		Assert.isTrue(start >= 0);

		try
		{
			int depth= 1;
			start -= 1;
			while (true)
			{
				start=
					scanForward(
							start + 1,
							UNBOUND,
							new StringMatch(new String[] {openingPeer, closingPeer}));
				if (start == NOT_FOUND)
					return NOT_FOUND;

				if (fDocument.get(start, openingPeer.length()).equals(openingPeer))
					depth++;
				else
					depth--;

				if (depth == 0)
					return start;
			}

		}
		catch (BadLocationException e)
		{
			return NOT_FOUND;
		}
	}

	/**
	 * Returns the position of the opening peer character (backward search).
	 * Any scopes introduced by closing peers
	 * are skipped. All peers accounted for must reside in
	 * the default partition.
	 *
	 * <p>Note that <code>start</code> must not point to the closing peer, but to the first
	 * character being searched.</p>
	 *
	 * @param start the start position
	 * @param openingPeer the opening peer character (e.g. '{')
	 * @param closingPeer the closing peer character (e.g. '}')
	 * @return the matching peer character position, or <code>NOT_FOUND</code>
	 */
	public int findOpeningPeer(int start, String openingPeer, String closingPeer) {
		Assert.isTrue(start < fDocument.getLength());

		try {
			int depth= 1;
			start += 1;
			while (true)
			{
				start= scanBackward(
							start - 1,
							UNBOUND,
							new StringMatch(new String[] {openingPeer, closingPeer}));
				if (start == NOT_FOUND)
					return NOT_FOUND;

				if (fDocument.get(start,closingPeer.length()).equals(closingPeer))
					depth++;
				else
					depth--;

				if (depth == 0)
					return start;
			}

		}
		catch (BadLocationException e)
		{
			return NOT_FOUND;
		}
	}

	/**
	 * Finds the smallest position in <code>fDocument</code> such that the position is &gt;= <code>position</code>
	 * and &lt; <code>bound</code> and <code>Character.isWhitespace(fDocument.getChar(pos))</code> evaluates to <code>false</code>
	 * and the position is in the default partition.
	 *
	 * @param position the first character position in <code>fDocument</code> to be considered
	 * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code> &gt; <code>position</code>, or <code>UNBOUND</code>
	 * @return the smallest position of a non-whitespace character in [<code>position</code>, <code>bound</code>) that resides in a Modelica partition, or <code>NOT_FOUND</code> if none can be found
	 */
	public int findNonWhitespaceForward(int position, int bound)
	{
		return scanForward(position, bound, fNonWSDefaultPart);
	}

	/**
	 * Finds the smallest position in <code>fDocument</code> such that the position is &gt;= <code>position</code>
	 * and &lt; <code>bound</code> and <code>Character.isWhitespace(fDocument.getChar(pos))</code> evaluates to <code>false</code>.
	 *
	 * @param position the first character position in <code>fDocument</code> to be considered
	 * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code> &gt; <code>position</code>, or <code>UNBOUND</code>
	 * @return the smallest position of a non-whitespace character in [<code>position</code>, <code>bound</code>), or <code>NOT_FOUND</code> if none can be found
	 */
	public int findNonWhitespaceForwardInAnyPartition(int position, int bound)
	{
		return scanForward(position, bound, fNonWS);
	}

	/**
	 * Finds the highest position in <code>fDocument</code> such that the position is &lt;= <code>position</code>
	 * and &gt; <code>bound</code> and <code>Character.isWhitespace(fDocument.getChar(pos))</code> evaluates to <code>false</code>
	 * and the position is in the default partition.
	 *
	 * @param position the first character position in <code>fDocument</code> to be considered
	 * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code> &lt; <code>position</code>, or <code>UNBOUND</code>
	 * @return the highest position of a non-whitespace character in (<code>bound</code>, <code>position</code>] that resides in a Modelica partition, or <code>NOT_FOUND</code> if none can be found
	 */
	public int findNonWhitespaceBackward(int position, int bound)
	{
		return scanBackward(position, bound, fNonWSDefaultPart);
	}

	/**
	 * Finds the lowest position <code>p</code> in <code>fDocument</code> such that <code>start</code> &lt;= p &lt;
	 * <code>bound</code> and <code>condition.stop(fDocument.getChar(p), p)</code> evaluates to <code>true</code>.
	 *
	 * @param start the first character position in <code>fDocument</code> to be considered
	 * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code> &gt; <code>start</code>, or <code>UNBOUND</code>
	 * @param condition the <code>StopCondition</code> to check
	 * @return the lowest position in [<code>start</code>, <code>bound</code>) for which <code>condition</code> holds, or <code>NOT_FOUND</code> if none can be found
	 */
	public int scanForward(int start, int bound, StopCondition condition)
	{
		Assert.isTrue(start >= 0);

		if (bound == UNBOUND)
			bound= fDocument.getLength();

		Assert.isTrue(bound <= fDocument.getLength());

		try
		{
			fPos= start;
			while (fPos < bound)
			{
				fChar= fDocument.getChar(fPos);
				if (condition.stop(String.valueOf(fChar), fPos, true))
					return fPos;

				fPos= condition.nextPosition(fPos, true);
			}
		}
		catch (BadLocationException e)
		{
		}
		return NOT_FOUND;
	}


	/**
	 * Finds the lowest position in <code>fDocument</code> such that the position is &gt;= <code>position</code>
	 * and &lt; <code>bound</code> and <code>fDocument.getChar(position) == ch</code> evaluates to <code>true</code>
	 * and the position is in the default partition.
	 *
	 * @param position the first character position in <code>fDocument</code> to be considered
	 * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code> &gt; <code>position</code>, or <code>UNBOUND</code>
	 * @param ch the <code>char</code> to search for
	 * @return the lowest position of <code>ch</code> in (<code>bound</code>, <code>position</code>] that resides in a Modelica partition, or <code>NOT_FOUND</code> if none can be found
	 */
	public int scanForward(int position, int bound, String str)
	{
		return scanForward(position, bound, new StringMatch(str));
	}

	/**
	 * Finds the lowest position in <code>fDocument</code> such that the position is &gt;= <code>position</code>
	 * and &lt; <code>bound</code> and <code>fDocument.getChar(position) == ch</code> evaluates to <code>true</code> for at least one
	 * ch in <code>chars</code> and the position is in the default partition.
	 *
	 * @param position the first character position in <code>fDocument</code> to be considered
	 * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code> &gt; <code>position</code>, or <code>UNBOUND</code>
	 * @param chars an array of <code>char</code> to search for
	 * @return the lowest position of a non-whitespace character in [<code>position</code>, <code>bound</code>) that resides in a Modelica partition, or <code>NOT_FOUND</code> if none can be found
	 */
	public int scanForward(int position, int bound, String[] strs)
	{
		return scanForward(position, bound, new StringMatch(strs));
	}

	/**
	 * Finds the highest position <code>p</code> in <code>fDocument</code> such that <code>bound</code> &lt; <code>p</code> &lt;= <code>start</code>
	 * and <code>condition.stop(fDocument.getChar(p), p)</code> evaluates to <code>true</code>.
	 *
	 * @param start the first character position in <code>fDocument</code> to be considered
	 * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code> &lt; <code>start</code>, or <code>UNBOUND</code>
	 * @param condition the <code>StopCondition</code> to check
	 * @return the highest position in (<code>bound</code>, <code>start</code> for which <code>condition</code> holds, or <code>NOT_FOUND</code> if none can be found
	 */
	public int scanBackward(int start, int bound, StopCondition condition)
	{
		if (bound == UNBOUND)
			bound= -1;

		Assert.isTrue(bound >= -1);
		Assert.isTrue(start < fDocument.getLength() );

		try
		{
			fPos= start;
			while (fPos > bound)
			{

				fChar= fDocument.getChar(fPos);
				if (condition.stop(String.valueOf(fChar), fPos, false))
					return fPos;

				fPos= condition.nextPosition(fPos, false);
			}
		}
		catch (BadLocationException e)
		{
		}
		return NOT_FOUND;
	}

	/**
	 * Finds the highest position in <code>fDocument</code> such that the position is &lt;= <code>position</code>
	 * and &gt; <code>bound</code> and <code>fDocument.getChar(position) == ch</code> evaluates to <code>true</code> for at least one
	 * ch in <code>chars</code> and the position is in the default partition.
	 *
	 * @param position the first character position in <code>fDocument</code> to be considered
	 * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code> &lt; <code>position</code>, or <code>UNBOUND</code>
	 * @param ch the <code>char</code> to search for
	 * @return the highest position of one element in <code>chars</code> in (<code>bound</code>, <code>position</code>] that resides in a Modelica partition, or <code>NOT_FOUND</code> if none can be found
	 */
	public int scanBackward(int position, int bound, String str)
	{
		return scanBackward(position, bound, new StringMatch(str));
	}

	/**
	 * Finds the highest position in <code>fDocument</code> such that the position is &lt;= <code>position</code>
	 * and &gt; <code>bound</code> and <code>fDocument.getChar(position) == ch</code> evaluates to <code>true</code> for at least one
	 * ch in <code>chars</code> and the position is in the default partition.
	 *
	 * @param position the first character position in <code>fDocument</code> to be considered
	 * @param bound the first position in <code>fDocument</code> to not consider any more, with <code>bound</code> &lt; <code>position</code>, or <code>UNBOUND</code>
	 * @param chars an array of <code>char</code> to search for
	 * @return the highest position of one element in <code>chars</code> in (<code>bound</code>, <code>position</code>] that resides in a Modelica partition, or <code>NOT_FOUND</code> if none can be found
	 */
	public int scanBackward(int position, int bound, String[] strs)
	{
		return scanBackward(position, bound, new StringMatch(strs));
	}

	/**
	 * Checks whether <code>position</code> resides in a default (Modelica) partition of <code>fDocument</code>.
	 *
	 * @param position the position to be checked
	 * @return <code>true</code> if <code>position</code> is in the default partition of <code>fDocument</code>, <code>false</code> otherwise
	 */
	public boolean isDefaultPartition(int position)
	{
		Assert.isTrue(position >= 0);
		Assert.isTrue(position <= fDocument.getLength());

		try
		{
			return fPartition.equals(TextUtilities.getContentType(fDocument, fPartitioning, position, false));
		}
		catch (BadLocationException e)
		{
			return false;
		}
	}

	/**
	 * Returns the partition at <code>position</code>.
	 *
	 * @param position the position to get the partition for
	 * @return the partition at <code>position</code> or a dummy zero-length
	 *         partition if accessing the document fails
	 */
	private ITypedRegion getPartition(int position)
	{
		Assert.isTrue(position >= 0);
		Assert.isTrue(position <= fDocument.getLength());

		try
		{
			return TextUtilities.getPartition(fDocument, fPartitioning, position, false);
		}
		catch (BadLocationException e)
		{
			return new TypedRegion(position, 0, "__no_partition_at_all"); //$NON-NLS-1$
		}

	}

}
