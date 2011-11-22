
package org.modelica.mdt.ui.assist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;



public class ModelicaParameterListValidator implements IContextInformationValidator, IContextInformationPresenter {

	private int fPosition;
	private ITextViewer fViewer;
	private IContextInformation fInformation;

	private int fCurrentParameter;



	public ModelicaParameterListValidator() {
	}

	/**
	 * @see IContextInformationValidator#install(IContextInformation, ITextViewer, int)
	 * @see IContextInformationPresenter#install(IContextInformation, ITextViewer, int)
	 */
	public void install(IContextInformation info, ITextViewer viewer, int documentPosition) {
		fPosition= documentPosition;
		fViewer= viewer;
		fInformation= info;

		fCurrentParameter= -1;
	}

	private int getCommentEnd(IDocument d, int pos, int end) throws BadLocationException {
		while (pos < end) {
			char curr= d.getChar(pos);
			pos++;
			if (curr == '*') {
				if (pos < end && d.getChar(pos) == '/') {
					return pos + 1;
				}
			}
		}
		return end;
	}

	private int getStringEnd(IDocument d, int pos, int end, char ch) throws BadLocationException {
		while (pos < end) {
			char curr= d.getChar(pos);
			pos++;
			if (curr == '\\') {
				// ignore escaped characters
				pos++;
			} else if (curr == ch) {
				return pos;
			}
		}
		return end;
	}

	private int getCharCount(IDocument document, final int start, final int end, String increments, String decrements, boolean considerNesting) throws BadLocationException {

		Assert.isTrue((increments.length() != 0 || decrements.length() != 0) && !increments.equals(decrements));
		
		final int NONE= 0;
		final int BRACKET= 1;
		final int BRACE= 2;
		final int PAREN= 3;
		final int ANGLE= 4;

		int nestingMode= NONE;
		int nestingLevel= 0;

		int charCount= 0;
		int offset= start;
		while (offset < end) {
			char curr= document.getChar(offset++);
			switch (curr) {
				case '/':
					if (offset < end) {
						char next= document.getChar(offset);
						if (next == '*') {
							// a comment starts, advance to the comment end
							offset= getCommentEnd(document, offset + 1, end);
						} else if (next == '/') {
							// '//'-comment: nothing to do anymore on this line
							offset= end;
						}
					}
					break;
				case '*':
					if (offset < end) {
						char next= document.getChar(offset);
						if (next == '/') {
							// we have been in a comment: forget what we read before
							charCount= 0;
							++ offset;
						}
					}
					break;
				case '"':
				case '\'':
					offset= getStringEnd(document, offset, end, curr);
					break;
				case '[':
					if (considerNesting) {
						if (nestingMode == BRACKET || nestingMode == NONE) {
							nestingMode= BRACKET;
							nestingLevel++;
						}
						break;
					}
				case ']':
					if (considerNesting) {
						if (nestingMode == BRACKET)
							if (--nestingLevel == 0)
								nestingMode= NONE;
						break;
					}
				case '(':
					if (considerNesting) {
						if (nestingMode == ANGLE) {
							// generics heuristic failed
							nestingMode=PAREN;
							nestingLevel= 1;
						}
						if (nestingMode == PAREN || nestingMode == NONE) {
							nestingMode= PAREN;
							nestingLevel++;
						}
						break;
					}
				case ')':
					if (considerNesting) {
						if (nestingMode == PAREN)
							if (--nestingLevel == 0)
								nestingMode= NONE;
						break;
					}
				case '{':
					if (considerNesting) {
						if (nestingMode == ANGLE) {
							// generics heuristic failed
							nestingMode=BRACE;
							nestingLevel= 1;
						}
						if (nestingMode == BRACE || nestingMode == NONE) {
							nestingMode= BRACE;
							nestingLevel++;
						}
						break;
					}
				case '}':
					if (considerNesting) {
						if (nestingMode == BRACE)
							if (--nestingLevel == 0)
								nestingMode= NONE;
						break;
					}
				case '<':
					if (considerNesting) {
						if (nestingMode == ANGLE || nestingMode == NONE) {
							nestingMode= ANGLE;
							nestingLevel++;
						}
						break;
					}
				case '>':
					if (considerNesting) {
						if (nestingMode == ANGLE)
							if (--nestingLevel == 0)
								nestingMode= NONE;
						break;
					}

				default:
					if (nestingLevel != 0)
						continue;

					if (increments.indexOf(curr) >= 0) {
						++ charCount;
					}

					if (decrements.indexOf(curr) >= 0) {
						-- charCount;
					}
			}
		}

		return charCount;
	}

	/**
	 * @see IContextInformationValidator#isContextInformationValid(int)
	 */
	public boolean isContextInformationValid(int position) {

		try {
			if (position < fPosition)
				return false;

			IDocument document= fViewer.getDocument();
			IRegion line= document.getLineInformationOfOffset(fPosition);

			if (position < line.getOffset() || position >= document.getLength())
				return false;

			return getCharCount(document, fPosition, position, "(<", ")>", false) >= 0; //$NON-NLS-1$ //$NON-NLS-2$

		} catch (BadLocationException x) {
			return false;
		}
	}

	/**
	 * @see IContextInformationPresenter#updatePresentation(int, TextPresentation)
	 */
	public boolean updatePresentation(int position, TextPresentation presentation) {

		int currentParameter= -1;

		try {
			currentParameter= getCharCount(fViewer.getDocument(), fPosition, position, ",", "", true);  //$NON-NLS-1$//$NON-NLS-2$
		} catch (BadLocationException x) {
			return false;
		}

		if (fCurrentParameter != -1) {
			if (currentParameter == fCurrentParameter)
				return false;
		}

		presentation.clear();
		fCurrentParameter= currentParameter;

		String s= fInformation.getInformationDisplayString();
		int[] commas= computeCommaPositions(s);

		if (commas.length - 2 < fCurrentParameter) {
			presentation.addStyleRange(new StyleRange(0, s.length(), null, null, SWT.NORMAL));
			return true;
		}
		
		int start= commas[fCurrentParameter] + 1;
		int end= commas[fCurrentParameter + 1];
		if (start > 0)
			presentation.addStyleRange(new StyleRange(0, start, null, null, SWT.NORMAL));

		if (end > start)
			presentation.addStyleRange(new StyleRange(start, end - start, null, null, SWT.BOLD));

		if (end < s.length())
			presentation.addStyleRange(new StyleRange(end, s.length() - end, null, null, SWT.NORMAL));

		return true;
	}
	
	private int[] computeCommaPositions(String code) {
		final int length= code.length();
	    int pos= 0;
		if (code.lastIndexOf('(') != -1) 
			pos = code.lastIndexOf('(')-1;
		
		List<Integer> positions= new ArrayList<Integer>();
		if (code.lastIndexOf('(') != -1) 		
			positions.add(new Integer(code.lastIndexOf('(')));
		else
			positions.add(new Integer(-1));
		while (pos < length && pos != -1) {
			char ch= code.charAt(pos);
			switch (ch) {
	            case ',':
		            positions.add(new Integer(pos));
		            break;
	            case '<':
	            	pos= code.indexOf('>', pos);
	            	break;
	            case '[':
	            	pos= code.indexOf(']', pos);
	            	break;
	            default:
	            	break;
            }
			if (pos != -1)
				pos++;
		}
		positions.add(new Integer(length));
		
		int[] fields= new int[positions.size()];
		for (int i= 0; i < fields.length; i++)
	        fields[i]= ((Integer) positions.get(i)).intValue();
	    return fields;
    }

}

///*
// * This Validator makes sure that the context information should still be
// * visible. 
// */
//protected static class Validator implements IContextInformationValidator, IContextInformationPresenter
//{
//	
//	protected ITextViewer fViewer;
//	protected int fInstallOffset;
//	IContextInformation fContextInformation = null;		
//	
//	/**
//	 * Check if the context information should still be displayed. Do this
//	 * by counting opening '(' and closing ')' parens and see if they match
//	 * up.
//	 */
//	public boolean isContextInformationValid(int offset)
//	{
//		/* If we've backed over the (, we're done with this information. */
//		if(offset - fInstallOffset < 0)
//		{
//			return false;
//		}
//		if(offset == fInstallOffset) return true;
//		
//		String stringTyped = ModelicaCompletionProcessor.getTypedString(fViewer, offset);
//		if (stringTyped.equalsIgnoreCase(""))
//		{
//			String s1 = fContextInformation.getContextDisplayString();
//			String s2 = fContextInformation.getInformationDisplayString();
//			if (s1 == null && s2 == null) { /* do nothing */ }
//			else 
//				if (s1 != null) stringTyped = s1;
//				else stringTyped = s2;
//			if (stringTyped == null)
//			{
//				stringTyped = "";
//			}
//			else
//			{
//				int fio = stringTyped.indexOf("(");
//				int lio = stringTyped.lastIndexOf(")");
//				if (lio > -1 && fio > -1)
//					stringTyped = stringTyped.substring(fio, lio+1);
//			}
//		}
//					
//		/* Get start of parameter list. */
//		int pos = stringTyped.indexOf('(');
//		
//		/* If no ( was found, something is wrong and the context information
//		 * should not be displayed. */
//		if(pos == -1)
//		{
//			return false;
//		}
//		
//		/* Match parens to see if we're done typing. */
//		int pardepth = 0;
//		for(;pos < stringTyped.length();pos++)
//		{
//			if(stringTyped.charAt(pos) == '(')
//			{
//				pardepth++;
//			}
//			else if(stringTyped.charAt(pos) == ')')
//			{
//				pardepth--;
//			}
//		}
//		
//		/* The TYPING will go on as long as it has to! */
//		/* (or somebody yells stop, goes limb, passes out) */
//		if(pardepth == 0)
//		{
//			if (offset < fInstallOffset + stringTyped.length()-1 && stringTyped.indexOf('.') == -1)
//				return true;				
//			return false;
//		}
//		else if(pardepth < 0)
//		{
//			/* This is a bug */
//			
//			ErrorManager.logBug(UIPlugin.getSymbolicName(), "The user typed too many ) and we don't even noticed.");
//			
//			return false;
//		}
//		else
//		{
//			return true;
//		}
//	}
//	
//	public void install(IContextInformation info, ITextViewer viewer, int offset)
//	{
//		fViewer = viewer;
//		fInstallOffset = offset;
//		fContextInformation = info;
//	}
//	
//	/**
//	 * Whenever the user types a character, the presentation can be updated.
//	 * We just update the presentation at the start, by formatting the
//	 * function name as BOLD.
//	 */
//	public boolean updatePresentation(int documentPosition, TextPresentation presentation)
//	{
//		/* Only apply the style range as we start displaying this
//		 * information. */
//		if(fInstallOffset != documentPosition)
//		{
//			return false;
//		}
//		/* see that we don't have it already in */
//		Iterator i = presentation.getAllStyleRangeIterator();
//
//		int functionNameEnd = 0; int functionNameStart = 0;
//		String contextInfo = fContextInformation.getContextDisplayString();
//		if (contextInfo == null)
//			contextInfo =  fContextInformation.getInformationDisplayString();
//		if (contextInfo != null)
//		{
//			functionNameEnd = contextInfo.indexOf('(');
//			if (functionNameEnd == -1) 
//				functionNameEnd = 0;					
//		}
//		if (functionNameEnd == 0 && functionNameStart == 0) return false;
//		if (i != null)
//			while (i.hasNext()) 
//			{
//				StyleRange s = (StyleRange)i.next();
//				if (s.start == functionNameStart && s.length == functionNameEnd - functionNameStart) return true;
//			}
//		
//		presentation.addStyleRange(new StyleRange(functionNameStart, functionNameEnd - functionNameStart, null, null, SWT.BOLD));
//		return true;
//	}
//}
