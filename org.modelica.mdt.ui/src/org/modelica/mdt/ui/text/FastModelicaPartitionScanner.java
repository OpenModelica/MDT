/*******************************************************************************
 * Copyright (c) 2006 MDT Team, PELAB
 *******************************************************************************/

package org.modelica.mdt.ui.text;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

import org.modelica.mdt.ui.text.IModelicaPartitions;


/**
 * This scanner recognizes the Modelica string comments, Modelica multi line string comments, Modelica single line comments,
 * Modelica strings.
 */
public class FastModelicaPartitionScanner implements IPartitionTokenScanner, IModelicaPartitions {

	// states
	private static final int MODELICA= 0;
	private static final int SINGLE_LINE_COMMENT= 1;
	private static final int MULTI_LINE_COMMENT= 2;
	private static final int STRING= 3;

	// beginning of prefixes and postfixes
	private static final int NONE= 0;
	private static final int BACKSLASH= 1; // postfix for STRING
	private static final int SLASH= 2; // prefix for SINGLE_LINE or MULTI_LINE
	private static final int SLASH_STAR= 3; // prefix for MULTI_LINE_COMMENT
	private static final int STAR= 4; // postfix for MULTI_LINE_COMMENT
	private static final int CARRIAGE_RETURN=5; // postfix for STRING and SINGLE_LINE_COMMENT

	/** The scanner. */
	private final BufferedDocumentScanner fScanner= new BufferedDocumentScanner(1000);	// faster implementation

	/** The offset of the last returned token. */
	private int fTokenOffset;
	/** The length of the last returned token. */
	private int fTokenLength;

	/** The state of the scanner. */
	private int fState;
	/** The last significant characters read. */
	private int fLast;
	/** The amount of characters already read on first call to nextToken(). */
	private int fPrefixLength;

	private final IToken[] fTokens= new IToken[] {
		new Token(null),
		new Token(MODELICA_SINGLE_LINE_COMMENT),
		new Token(MODELICA_MULTI_LINE_COMMENT),
		new Token(MODELICA_STRING)
	};

	public FastModelicaPartitionScanner() 
	{

	}

	/*
	 * @see org.eclipse.jface.text.rules.ITokenScanner#nextToken()
	 */
	public IToken nextToken() 
	{

		fTokenOffset += fTokenLength;
		fTokenLength= fPrefixLength;

		while (true) 
		{
			final int ch= fScanner.read();

			// characters
	 		switch (ch) 
	 		{
	 		case ICharacterScanner.EOF:
		 		if (fTokenLength > 0) {
		 			fLast= NONE; // ignore last
		 			return preFix(fState, MODELICA, NONE, 0);

		 		} 
		 		else 
		 		{
		 			fLast= NONE;
		 			fPrefixLength= 0;
					return Token.EOF;
		 		}

	 		case '\r':
	 			if (fLast != CARRIAGE_RETURN) 
	 			{
						fLast= CARRIAGE_RETURN;
						fTokenLength++;
	 					continue;
	 			} 
	 			else 
	 			{
					switch (fState) 
					{
					case SINGLE_LINE_COMMENT:
						if (fTokenLength > 0) {
							IToken token= fTokens[fState];

							fLast= CARRIAGE_RETURN;
							fPrefixLength= 1;

							fState= MODELICA;
							return token;
						} 
						else 
						{
							consume();
							continue;
						}
						
					case STRING:
						consume();
						continue;

					default:
						consume();
						continue;
					}
	 			}

	 		case '\n':
				switch (fState) {
				case SINGLE_LINE_COMMENT:
					// assert(fTokenLength > 0);
					return postFix(fState);

				case STRING:
					consume();
					continue;
					
				default:
					consume();
					continue;
				}	 			

			default:
				if (fLast == CARRIAGE_RETURN) 
				{
					int last;
					int newState;
					switch (fState) 
					{
					case STRING:
						switch (ch) 
						{
						case '/':
							last= SLASH;
							newState= MODELICA;
							break;

						case '*':
							last= STAR;
							newState= MODELICA;
							break;

						case '"':
							last= NONE;
							newState= STRING;
							break;

						case '\r':
							last= CARRIAGE_RETURN;
							newState= MODELICA;
							break;

						case '\\':
							last= BACKSLASH;
							newState= MODELICA;
							break;

						default:
							last= NONE;
							newState= MODELICA;
							break;
						}
						fLast= NONE; // ignore fLast
						return preFix(fState, newState, last, 1);
					
					case SINGLE_LINE_COMMENT:
						switch (ch) 
						{
						case '/':
							last= SLASH;
							newState= MODELICA;
							break;

						case '*':
							last= STAR;
							newState= MODELICA;
							break;

						case '"':
							last= NONE;
							newState= STRING;
							break;

						case '\r':
							last= CARRIAGE_RETURN;
							newState= MODELICA;
							break;

						case '\\':
							last= BACKSLASH;
							newState= MODELICA;
							break;

						default:
							last= NONE;
							newState= MODELICA;
							break;
						}

						fLast= NONE; // ignore fLast
						return preFix(fState, newState, last, 1);

					default:
						break;
					}
				}
			}

			// states
	 		switch (fState) {
	 		case MODELICA:
				switch (ch) {
				case '/':
					if (fLast == SLASH) {
						if (fTokenLength - getLastLength(fLast) > 0) {
							return preFix(MODELICA, SINGLE_LINE_COMMENT, NONE, 2);
						} else {
							preFix(MODELICA, SINGLE_LINE_COMMENT, NONE, 2);
							fTokenOffset += fTokenLength;
							fTokenLength= fPrefixLength;
							break;
						}

					} else {
						fTokenLength++;
						fLast= SLASH;
						break;
					}

				case '*':
					if (fLast == SLASH) {
						if (fTokenLength - getLastLength(fLast) > 0)
							return preFix(MODELICA, MULTI_LINE_COMMENT, SLASH_STAR, 2);
						else {
							preFix(MODELICA, MULTI_LINE_COMMENT, SLASH_STAR, 2);
							fTokenOffset += fTokenLength;
							fTokenLength= fPrefixLength;
							break;
						}

					} else {
						consume();
						break;
					}

				case '"':
					fLast= NONE; // ignore fLast
					if (fTokenLength > 0)
						return preFix(MODELICA, STRING, NONE, 1);
					else {
						preFix(MODELICA, STRING, NONE, 1);
						fTokenOffset += fTokenLength;
						fTokenLength= fPrefixLength;
						break;
					}

				default:
					consume();
					break;
				}
				break;

	 		case SINGLE_LINE_COMMENT:
				consume();
				break;

	 		case MULTI_LINE_COMMENT:
				switch (ch) {
				case '*':
						fTokenLength++;
						fLast= STAR;
					break;

				case '/':
					if (fLast == STAR) {
						return postFix(MULTI_LINE_COMMENT);
					} else {
						consume();
						break;
					}

				default:
					consume();
					break;
				}
				break;

	 		case STRING:
	 			switch (ch) 
	 			{
	 			case '\\':
					fLast= (fLast == BACKSLASH) ? NONE : BACKSLASH;
					fTokenLength++;
					break;

				case '\"':
	 				if (fLast != BACKSLASH) 
	 				{
	 					return postFix(STRING);
		 			} 
	 				else 
	 				{
						consume();
						break;
	 				}

		 		default:
					consume();
	 				break;
	 			}
	 			break;

	 		}
		}
 	}

	private static final int getLastLength(int last) {
		switch (last) {
		default:
			return -1;

		case NONE:
			return 0;

		case CARRIAGE_RETURN:
		case BACKSLASH:
		case SLASH:
		case STAR:
			return 1;

		case SLASH_STAR:
			return 2;
		}
	}

	private final void consume() 
	{
		fTokenLength++;
		fLast= NONE;
	}

	private final IToken postFix(int state) 
	{
		fTokenLength++;
		fLast= NONE;
		fState= MODELICA;
		fPrefixLength= 0;
		return fTokens[state];
	}

	private final IToken preFix(int state, int newState, int last, int prefixLength) 
	{
			fTokenLength -= getLastLength(fLast);
			fLast= last;
			fPrefixLength= prefixLength;
			IToken token= fTokens[state];
			fState= newState;
			return token;
	}

	private static int getState(String contentType) 
	{

		if (contentType == null)
			return MODELICA;

		else if (contentType.equals(MODELICA_SINGLE_LINE_COMMENT))
			return SINGLE_LINE_COMMENT;

		else if (contentType.equals(MODELICA_MULTI_LINE_COMMENT))
			return MULTI_LINE_COMMENT;

		else if (contentType.equals(MODELICA_STRING))
			return STRING;

		else
			return MODELICA;
	}

	/*
	 * @see IPartitionTokenScanner#setPartialRange(IDocument, int, int, String, int)
	 */
	public void setPartialRange(IDocument document, int offset, int length, String contentType, int partitionOffset) {

		fScanner.setRange(document, offset, length);
		fTokenOffset= partitionOffset;
		fTokenLength= 0;
		fPrefixLength= offset - partitionOffset;
		fLast= NONE;

		if (offset == partitionOffset) {
			// restart at beginning of partition
			fState= MODELICA;
		} else {
			fState= getState(contentType);
		}

	}

	/*
	 * @see ITokenScanner#setRange(IDocument, int, int)
	 */
	public void setRange(IDocument document, int offset, int length) {

		fScanner.setRange(document, offset, length);
		fTokenOffset= offset;
		fTokenLength= 0;
		fPrefixLength= 0;
		fLast= NONE;
		fState= MODELICA;
	}

	/*
	 * @see ITokenScanner#getTokenLength()
	 */
	public int getTokenLength() {
		return fTokenLength;
	}

	/*
	 * @see ITokenScanner#getTokenOffset()
	 */
	public int getTokenOffset() {
		return fTokenOffset;
	}

}
