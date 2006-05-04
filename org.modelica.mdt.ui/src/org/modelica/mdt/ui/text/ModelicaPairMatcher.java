/*******************************************************************************
 * Copyright (c) 2006 MDT Team, PELAB
 *******************************************************************************/

package org.modelica.mdt.ui.text;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.modelica.mdt.ui.text.IModelicaPartitions;

/**
 * Helper class for match pairs of characters.
 * @author Adrian Pop
 */

public class ModelicaPairMatcher implements ICharacterPairMatcher
{
	protected char[] fPairs;
	protected IDocument fDocument;
	protected int fOffset;

	protected int fStartPos;
	protected int fEndPos;
	protected int fAnchor;
	private boolean fHighlightAngularBrackets= true;


	public ModelicaPairMatcher(char[] pairs) 
	{
		fPairs= pairs;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#match(org.eclipse.jface.text.IDocument, int)
	 */
	public IRegion match(IDocument document, int offset) {

		fOffset= offset;
		//System.out.println("Match called!");
		if (fOffset < 0)
			return null;

		fDocument= document;

		if (fDocument != null && matchPairsAt() && fStartPos != fEndPos)
			return new Region(fStartPos, fEndPos - fStartPos + 1);

		//System.out.println("Match returned null!");
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#getAnchor()
	 */
	public int getAnchor() {
		return fAnchor;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#dispose()
	 */
	public void dispose() {
		clear();
		fDocument= null;
	}

	/*
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#clear()
	 */
	public void clear() {
	}

	protected boolean matchPairsAt() {

		int i;
		int pairIndex1= fPairs.length;
		int pairIndex2= fPairs.length;

		fStartPos= -1;
		fEndPos= -1;

		// get the char preceding the start position
		try {

			char prevChar= fDocument.getChar(Math.max(fOffset - 1, 0));
			// search for opening peer character next to the activation point
			for (i= 0; i < fPairs.length; i= i + 2) {
				if (prevChar == fPairs[i]) {
					fStartPos= fOffset - 1;
					pairIndex1= i;
				}
			}

			// search for closing peer character next to the activation point
			for (i= 1; i < fPairs.length; i= i + 2) {
				if (prevChar == fPairs[i]) {
					fEndPos= fOffset - 1;
					pairIndex2= i;
				}
			}

			if (fEndPos > -1) {
				fAnchor= RIGHT;
				fStartPos= searchForOpeningPeer(fEndPos, fPairs[pairIndex2 - 1], fPairs[pairIndex2], fDocument);
				if (fStartPos > -1)
					return true;
				else
					fEndPos= -1;
			}	else if (fStartPos > -1) {
				fAnchor= LEFT;
				fEndPos= searchForClosingPeer(fStartPos, fPairs[pairIndex1], fPairs[pairIndex1 + 1], fDocument);
				if (fEndPos > -1)
					return true;
				else
					fStartPos= -1;
			}

		} catch (BadLocationException x) {
		}

		return false;
	}

	protected int searchForClosingPeer(int offset, char openingPeer, char closingPeer, IDocument document) throws BadLocationException {
		boolean useGenericsHeuristic= openingPeer == '<';
		if (useGenericsHeuristic && !fHighlightAngularBrackets)
			return -1;
		ModelicaHeuristicScanner scanner= new ModelicaHeuristicScanner(document, IModelicaPartitions.MODELICA_PARTITIONING, TextUtilities.getContentType(document, IModelicaPartitions.MODELICA_PARTITIONING, offset, false));
		if (useGenericsHeuristic && !isTypeParameterBracket(offset, document, scanner))
			return -1;

		return scanner.findClosingPeer(offset + 1, String.valueOf(openingPeer), String.valueOf(closingPeer));
	}


	protected int searchForOpeningPeer(int offset, char openingPeer, char closingPeer, IDocument document) throws BadLocationException {
		boolean useGenericsHeuristic= openingPeer == '<';
		if (useGenericsHeuristic && !fHighlightAngularBrackets)
			return -1;

		ModelicaHeuristicScanner scanner= new ModelicaHeuristicScanner(document, IModelicaPartitions.MODELICA_PARTITIONING, TextUtilities.getContentType(document, IModelicaPartitions.MODELICA_PARTITIONING, offset, false));
		int peer= scanner.findOpeningPeer(offset - 1, String.valueOf(openingPeer), String.valueOf(closingPeer));
		if (peer == ModelicaHeuristicScanner.NOT_FOUND)
			return -1;
		if (useGenericsHeuristic && !isTypeParameterBracket(peer, document, scanner))
			return -1;
		return peer;
	}

	/**
	 * Checks if the angular bracket at <code>offset</code> is a type
	 * parameter bracket.
	 *
	 * @param offset the offset of the opening bracket
	 * @param document the document
	 * @param scanner a java heuristic scanner on <code>document</code>
	 * @return <code>true</code> if the bracket is part of a type parameter,
	 *         <code>false</code> otherwise
	 * @since 3.1
	 */
	private boolean isTypeParameterBracket(int offset, IDocument document, ModelicaHeuristicScanner scanner) {
		/*
		 * type parameter come after braces (closing or opening), semicolons, or after
		 * a Type name (heuristic: starts with capital character, or after a modifier
		 * keyword in a method declaration (visibility, static, synchronized, final)
		 */

		try {
			IRegion line= document.getLineInformationOfOffset(offset);

			int prevToken= scanner.previousToken(offset - 1, line.getOffset());
			int prevTokenOffset= scanner.getPosition() + 1;
			String previous= prevToken == Symbols.TokenEOF ? null : document.get(prevTokenOffset, offset - prevTokenOffset).trim();

			if ((prevToken == Symbols.TokenIDENT && isTypeParameterIntroducer(previous))
				 || prevToken == Symbols.TokenEOF)
				return true;
		} catch (BadLocationException e) {
			return false;
		}

		return false;
	}

	/**
	 * Returns <code>true</code> if <code>identifier</code> is an identifier
	 * that could come right before a type parameter list. It uses a heuristic:
	 * if the identifier starts with an upper case, it is assumed a type name.
	 * Also, if <code>identifier</code> is a method modifier, it is assumed
	 * that the angular bracket is part of the generic type parameter of a
	 * method.
	 *
	 * @param identifier the identifier to check
	 * @return <code>true</code> if the identifier could introduce a type
	 *         parameter list
	 * @since 3.1
	 */
	private boolean isTypeParameterIntroducer(String identifier) {
		return identifier.length() > 0
				&& (Character.isUpperCase(identifier.charAt(0))
						|| identifier.startsWith("list") //$NON-NLS-1$
						|| identifier.startsWith("tuple") //$NON-NLS-1$
						|| identifier.startsWith("Option") //$NON-NLS-1$
						|| identifier.startsWith("lvar"));
	}
}
