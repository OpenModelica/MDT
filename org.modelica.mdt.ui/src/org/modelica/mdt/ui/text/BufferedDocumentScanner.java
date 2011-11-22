/*******************************************************************************
 * Copyright (c) 2006 MDT Team, PELAB
 *******************************************************************************/

package org.modelica.mdt.ui.text;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.ICharacterScanner;

/**
 * A buffered document scanner. The buffer always contains a section
 * of a fixed size of the document to be scanned.
 */

public final class BufferedDocumentScanner implements ICharacterScanner {

	/** The document being scanned. */
	private IDocument fDocument;
	/** The offset of the document range to scan. */
	private int fRangeOffset;
	/** The length of the document range to scan. */
	private int fRangeLength;
	/** The delimiters of the document. */
	private char[][] fDelimiters;

	/** The buffer. */
	private final char[] fBuffer;
	/** The offset of the buffer within the document. */
	private int fBufferOffset;
	/** The valid length of the buffer for access. */
	private int fBufferLength;
	/** The offset of the scanner within the buffer. */
	private int fOffset;


	/**
	 * Creates a new buffered document scanner.
	 * The buffer size is set to the given number of characters.
	 *
	 * @param size the buffer size
	 */
	public BufferedDocumentScanner(int size) {
		Assert.isTrue(size >= 1);
		fBuffer= new char[size];
	}

	/**
	 * Fills the buffer with the contens of the document starting at the given offset.
	 *
	 * @param offset the document offset at which the buffer starts
	 */
	private final void updateBuffer(int offset) {

		fBufferOffset= offset;

		if (fBufferOffset + fBuffer.length > fRangeOffset + fRangeLength)
			fBufferLength= fRangeLength - (fBufferOffset - fRangeOffset);
		else
			fBufferLength= fBuffer.length;

		try {
			final String content= fDocument.get(fBufferOffset, fBufferLength);
			content.getChars(0, fBufferLength, fBuffer, 0);
		} catch (BadLocationException e) {
		}
	}

	/**
	 * Configures the scanner by providing access to the document range over which to scan.
	 *
	 * @param document the document to scan
	 * @param offset the offset of the document range to scan
	 * @param length the length of the document range to scan
	 */
	public final void setRange(IDocument document, int offset, int length) {

		fDocument= document;
		fRangeOffset= offset;
		fRangeLength= length;

		String[] delimiters= document.getLegalLineDelimiters();
		fDelimiters= new char[delimiters.length][];
		for (int i= 0; i < delimiters.length; i++)
			fDelimiters[i]= delimiters[i].toCharArray();

		updateBuffer(offset);
		fOffset= 0;
	}

	/*
	 * @see ICharacterScanner#read()
	 */
	public final int read() {

		if (fOffset == fBufferLength) {
			if (fBufferOffset + fBufferLength == fDocument.getLength())
				return EOF;
			else {
				updateBuffer(fBufferOffset + fBufferLength);
				fOffset= 0;
			}
		}

		return fBuffer[fOffset++];
	}

	/*
	 * @see ICharacterScanner#unread
	 */
	public final void unread() {

		if (fOffset == 0) {
			if (fBufferOffset == fRangeOffset) {
				// error: BOF
			} else {
				updateBuffer(fBufferOffset - fBuffer.length);
				fOffset= fBuffer.length - 1;
			}
		} else {
			--fOffset;
		}
	}

	/*
	 * @see ICharacterScanner#getColumn()
	 */
	public final int getColumn() {

		try {
			final int offset= fBufferOffset + fOffset;
			final int line= fDocument.getLineOfOffset(offset);
			final int start= fDocument.getLineOffset(line);
			return offset - start;
		} catch (BadLocationException e) {
		}

		return -1;
	}

	/*
	 * @see ICharacterScanner#getLegalLineDelimiters()
	 */
	public final char[][] getLegalLineDelimiters() {
		return fDelimiters;
	}
}
