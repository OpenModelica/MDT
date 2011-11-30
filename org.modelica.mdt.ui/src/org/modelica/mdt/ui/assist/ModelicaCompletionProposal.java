/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.modelica.mdt.ui.assist;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.texteditor.link.EditorLinkedModeUI;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension3;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension5;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.link.ILinkedModeListener;
import org.eclipse.jface.text.link.LinkedModeModel;
import org.eclipse.jface.text.link.LinkedModeUI;
import org.eclipse.jface.text.link.LinkedPosition;
import org.eclipse.jface.text.link.LinkedPositionGroup;
import org.eclipse.jface.text.link.LinkedModeUI.ExitFlags;
import org.eclipse.jface.text.link.LinkedModeUI.IExitPolicy;
import org.eclipse.jface.viewers.StyledString;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.ui.UIPlugin;
import org.modelica.mdt.ui.hover.AbstractReusableInformationControlCreator;
import org.modelica.mdt.ui.hover.HTMLTextPresenter;
import org.modelica.mdt.ui.text.modelica.CompletionProposal;
import org.modelica.mdt.ui.text.modelica.IModelicaCompletionProposal;
import org.modelica.mdt.ui.text.modelica.ModelicaContentAssistInvocationContext;

public class ModelicaCompletionProposal extends CompletionProposal implements IModelicaCompletionProposal, ICompletionProposal, ICompletionProposalExtension, ICompletionProposalExtension3, ICompletionProposalExtension5
{

	/** The string to be displayed in the completion proposal popup. */
	private String fDisplayString;
	/** The replacement string. */
	private String fReplacementString;
	/** The replacement offset. */
	private int fReplacementOffset;
	/** The replacement length. */
	private int fReplacementLength;
	/** The cursor position after this proposal has been applied. */
	private int fCursorPosition;
	/** The image to be displayed in the completion proposal popup. */
	private Image fImage;
	/** The context information of this proposal. */
	private IContextInformation fContextInformation;
	/** The additional info of this proposal. */
	private String fAdditionalProposalInfo;

	/**
	 * Creates a new completion proposal based on the provided information. The replacement string is
	 * considered being the display string too. All remaining fields are set to <code>null</code>.
	 *
	 * @param replacementString the actual string to be inserted into the document
	 * @param replacementOffset the offset of the text to be replaced
	 * @param replacementLength the length of the text to be replaced
	 * @param cursorPosition the position of the cursor following the insert relative to replacementOffset
	 */
	public ModelicaCompletionProposal(String replacementString, int replacementOffset, int replacementLength, int cursorPosition) {
		this(replacementString, replacementOffset, replacementLength, cursorPosition, null, null, null, null);
	}

	/**
	 * Creates a new completion proposal. All fields are initialized based on the provided information.
	 *
	 * @param replacementString the actual string to be inserted into the document
	 * @param replacementOffset the offset of the text to be replaced
	 * @param replacementLength the length of the text to be replaced
	 * @param cursorPosition the position of the cursor following the insert relative to replacementOffset
	 * @param image the image to display for this proposal
	 * @param displayString the string to be displayed for the proposal
	 * @param contextInformation the context information associated with this proposal
	 * @param additionalProposalInfo the additional information associated with this proposal
	 */
	public ModelicaCompletionProposal(String replacementString, int replacementOffset, int replacementLength, int cursorPosition, Image image, String displayString, IContextInformation contextInformation, String additionalProposalInfo) {
		Assert.isNotNull(replacementString);
		Assert.isTrue(replacementOffset >= 0);
		Assert.isTrue(replacementLength >= 0);
		Assert.isTrue(cursorPosition >= 0);

		fReplacementString= replacementString;
		fReplacementOffset= replacementOffset;
		fReplacementLength= replacementLength;
		fCursorPosition= cursorPosition;
		fImage= image;
		fDisplayString= displayString;
		fContextInformation= contextInformation;
		fAdditionalProposalInfo= additionalProposalInfo;
	}

	public ModelicaCompletionProposal(String text, int start, int length,
			Image image, String text2, int rank)
	{

		fReplacementString= text;
		fReplacementOffset= start;
		fReplacementLength= length;
		fCursorPosition= 0;
		fImage= image;
		fDisplayString= text2;
		fContextInformation= null;
		fAdditionalProposalInfo= "";
	}

	public ModelicaCompletionProposal(String completion, int start, int length,
			Image image, StyledString label, int relevance)
	{
		// TODO Auto-generated constructor stub
	}

	public ModelicaCompletionProposal(CompletionProposal typeProposal,
			ModelicaContentAssistInvocationContext invocationContext)
	{
		// TODO Auto-generated constructor stub
	}

	public ModelicaCompletionProposal(String completion, int start, int length,
			Image image, StyledString label, int relevance,
			boolean inModelicadoc,
			ModelicaContentAssistInvocationContext invocationContext)
	{
		// TODO Auto-generated constructor stub
	}

	public ModelicaCompletionProposal(int kind, int completionOffset)
	{
		// TODO Auto-generated constructor stub
	}

	/*
	 * @see ICompletionProposal#apply(IDocument)
	 */
	public void apply(IDocument document) {
		// not used any longer		
		apply(document, (char) 0, getReplacementOffset() + getReplacementLength());
	}

	/*
	 * @see ICompletionProposal#getSelection(IDocument)
	 */
	public Point getSelection(IDocument document) {
		return new Point(fReplacementOffset + fCursorPosition, 0);
	}

	/*
	 * @see ICompletionProposal#getContextInformation()
	 */
	public IContextInformation getContextInformation() {
		return fContextInformation;
	}

	/*
	 * @see ICompletionProposal#getImage()
	 */
	public Image getImage() {
		return fImage;
	}

	/*
	 * @see ICompletionProposal#getDisplayString()
	 */
	public String getDisplayString() {
		if (fDisplayString != null)
			return fDisplayString;
		return fReplacementString;
	}

	/*
	 * @see ICompletionProposal#getAdditionalProposalInfo()
	 */
	public String getAdditionalProposalInfo() {
		return fAdditionalProposalInfo;
	}

	public Object getAdditionalProposalInfo(IProgressMonitor monitor)
	{
		if (fAdditionalProposalInfo == null || fAdditionalProposalInfo.equalsIgnoreCase(""))
		{
			return null;
		}
		return "<p>" + fAdditionalProposalInfo.replace("\n", "<br>") + "</p>";
	}

	/**
	 * The control creator.
	 * 
	 * @since 3.3
	 */
	private static final class ControlCreator extends AbstractReusableInformationControlCreator {
		/*
		 * @see org.modelica.mdt.ui.text.java.hover.AbstractReusableInformationControlCreator#doCreateInformationControl(org.eclipse.swt.widgets.Shell)
		 */
		public IInformationControl doCreateInformationControl(Shell parent) {
			IInformationControl x = new DefaultInformationControl(parent, SWT.NO_TRIM | SWT.TOOL, SWT.NONE, new HTMLTextPresenter(false));
			x.setSizeConstraints(70, 20);
			return x;
		}
	}
	
	/**
	 * The control creator.
	 * 
	 * @since 3.2
	 */
	private IInformationControlCreator fCreator;
	
	public IInformationControlCreator getInformationControlCreator() {
		Shell shell= UIPlugin.getActiveWorkbenchShell();
		if (shell == null)
			return null;
		
		if (fCreator == null) {
			fCreator= new ControlCreator();
		}
		return fCreator;
	}
	
	/*
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension3#getCompletionOffset()
	 */
	public int getPrefixCompletionStart(IDocument document, int completionOffset) {
		return getReplacementOffset();
	}
	
	/**
	 * Gets the replacement offset.
	 * @return Returns a int
	 */
	public int getReplacementOffset() {
		return fReplacementOffset;
	}

	/**
	 * Sets the replacement offset.
	 * @param replacementOffset The replacement offset to set
	 */
	public void setReplacementOffset(int replacementOffset) {
		Assert.isTrue(replacementOffset >= 0);
		fReplacementOffset= replacementOffset;
	}

	/*
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension3#getReplacementText()
	 */
	public CharSequence getPrefixCompletionText(IDocument document, int completionOffset) {
		return getReplacementString();
	}
	
	/**
	 * Gets the replacement string.
	 * @return Returns a String
	 */
	public String getReplacementString() {
		return fReplacementString;
	}	
	
	/*
	 * @see ICompletionProposalExtension#getContextInformationPosition()
	 */
	public int getContextInformationPosition() {
		if (getContextInformation() == null)
			return getReplacementOffset() - 1;
		return getReplacementOffset() + getCursorPosition();
	}

	/**
	 * Sets the cursor position relative to the insertion offset. By default this is the length of
	 * the completion string (Cursor positioned after the completion)
	 * 
	 * @param cursorPosition The cursorPosition to set
	 */
	public void setCursorPosition(int cursorPosition) {
		Assert.isTrue(cursorPosition >= 0);
		fCursorPosition= cursorPosition;
	}
	
	protected int getCursorPosition() {
		return fCursorPosition;
	}	
	
	/*
	 * @see ICompletionProposalExtension#isValidFor(IDocument, int)
	 */
	public boolean isValidFor(IDocument document, int offset) {
		return validate(document, offset, null);
	}

	/*
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension2#validate(org.eclipse.jface.text.IDocument, int, org.eclipse.jface.text.DocumentEvent)
	 */
	public boolean validate(IDocument document, int offset, DocumentEvent event) {

		if (offset < getReplacementOffset())
			return false;
		
		boolean validated= isValidPrefix(getPrefix(document, offset));

		if (validated && event != null) {
			// adapt replacement range to document change
			int delta= (event.fText == null ? 0 : event.fText.length()) - event.fLength;
			final int newLength= Math.max(getReplacementLength() + delta, 0);
			setReplacementLength(newLength);
		}

		return validated;
	}

	/**
	 * Gets the replacement length.
	 * @return Returns a int
	 */
	public int getReplacementLength() {
		return fReplacementLength;
	}

	/**
	 * Sets the replacement length.
	 * @param replacementLength The replacementLength to set
	 */
	public void setReplacementLength(int replacementLength) {
		Assert.isTrue(replacementLength >= 0);
		fReplacementLength= replacementLength;
	}
	

	/**
	 * Returns the text in <code>document</code> from {@link #getReplacementOffset()} to
	 * <code>offset</code>. Returns the empty string if <code>offset</code> is before the
	 * replacement offset or if an exception occurs when accessing the document.
	 * 
	 * @since 3.2
	 */
	protected String getPrefix(IDocument document, int offset) {
		try {
			int length= offset - getReplacementOffset();
			if (length > 0)
				return document.get(getReplacementOffset(), length);
		} catch (BadLocationException x) {
		}
		return ""; //$NON-NLS-1$
	}
	
	/**
	 * Checks whether <code>prefix</code> is a valid prefix for this proposal. Usually, while code
	 * completion is in progress, the user types and edits the prefix in the document in order to
	 * filter the proposal list. From {@link #validate(IDocument, int, DocumentEvent) }, the
	 * current prefix in the document is extracted and this method is called to find out whether the
	 * proposal is still valid.
	 * <p>
	 * The default implementation checks if <code>prefix</code> is a prefix of the proposal's
	 * {@link #getDisplayString() display string} using the {@link #isPrefix(String, String) }
	 * method.
	 * </p>
	 * 
	 * @param prefix the current prefix in the document
	 * @return <code>true</code> if <code>prefix</code> is a valid prefix of this proposal
	 */
	protected boolean isValidPrefix(String prefix) {
		/*
		 * See http://dev.eclipse.org/bugs/show_bug.cgi?id=17667
		 * why we do not use the replacement string.
		 * String word= fReplacementString;
		 */
		return isPrefix(prefix, getDisplayString());
	}
	
	/**
	 * Case insensitive comparison of <code>prefix</code> with the start of <code>string</code>.
	 * Returns <code>false</code> if <code>prefix</code> is longer than <code>string</code>
	 * 
	 * @since 3.2
	 */
	protected boolean isPrefix(String prefix, String string) {
		if (prefix == null || string ==null || prefix.length() > string.length())
			return false;
		String start= string.substring(0, prefix.length());
		return start.equalsIgnoreCase(prefix);
	}
		
	private ITextViewer fTextViewer;	
	protected ITextViewer getTextViewer() {
		return fTextViewer;
	}
	
	/**
	 * Sets up a simple linked mode at {@link #getCursorPosition()} and an exit policy that will
	 * exit the mode when <code>closingCharacter</code> is typed and an exit position at
	 * <code>getCursorPosition() + 1</code>.
	 * 
	 * @param document the document
	 * @param closingCharacter the exit character
	 */
	protected void setUpLinkedMode(IDocument document, char closingCharacter) {
		if (getTextViewer() != null) {
			int offset= getReplacementOffset() + getCursorPosition();
			int exit= getReplacementOffset() + getReplacementString().length();
			try {
				LinkedPositionGroup group= new LinkedPositionGroup();
				group.addPosition(new LinkedPosition(document, offset, 0, LinkedPositionGroup.NO_STOP));
				
				LinkedModeModel model= new LinkedModeModel();
				model.addGroup(group);
				model.forceInstall();
				
				LinkedModeUI ui= new EditorLinkedModeUI(model, getTextViewer());
				ui.setSimpleMode(true);
				ui.setExitPolicy(new ExitPolicy(closingCharacter, document));
				ui.setExitPosition(getTextViewer(), exit, 0, Integer.MAX_VALUE);
				ui.setCyclingMode(LinkedModeUI.CYCLE_NEVER);
				ui.enter();
			} catch (BadLocationException x) {
				ErrorManager.logError(x);
			}
		}
	}
	
	/**
	 * Sets the replacement string.
	 * @param replacementString The replacement string to set
	 */
	public void setReplacementString(String replacementString) {
		Assert.isNotNull(replacementString);
		fReplacementString= replacementString;
	}
	
	/*
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension#apply(org.eclipse.jface.text.IDocument, char, int)
	 */
	public void apply(IDocument document, char trigger, int offset) {
		String replacement = getReplacementString();
		try{
			// patch replacement length
			int delta= offset - (getReplacementOffset() + getReplacementLength());
			if (delta > 0)
				setReplacementLength(getReplacementLength() + delta);

			if (trigger == (char) 0) {
				replacement= getReplacementString();
			} else {
				StringBuffer buffer= new StringBuffer(getReplacementString());

				// fix for PR #5533. Assumes that no eating takes place.
				if ((getCursorPosition() > 0 && getCursorPosition() <= buffer.length() && buffer.charAt(getCursorPosition() - 1) != trigger)) {
					buffer.insert(getCursorPosition(), trigger);
					setCursorPosition(getCursorPosition() + 1);
				}

				replacement= buffer.toString();
				setReplacementString(replacement);
			}

			// reference position just at the end of the document change.
			int referenceOffset= getReplacementOffset() + getReplacementLength();
			final ReferenceTracker referenceTracker= new ReferenceTracker();
			referenceTracker.preReplace(document, referenceOffset);

			replace(document, getReplacementOffset(), getReplacementLength(), replacement);

			referenceOffset= referenceTracker.postReplace(document);
			setReplacementOffset(referenceOffset - (replacement == null ? 0 : replacement.length()));
		}
		catch(BadLocationException e)
		{
			// do nothing
		}
	}	

	
	private char[] fTriggerCharacters;	
	/*
	 * @see ICompletionProposalExtension#getTriggerCharacters()
	 */
	public char[] getTriggerCharacters() {
		return fTriggerCharacters;
	}
	
	/**
	 * Sets the trigger characters.
	 * 
	 * @param triggerCharacters The set of characters which can trigger the application of this
	 *        completion proposal
	 */
	public void setTriggerCharacters(char[] triggerCharacters) {
		fTriggerCharacters= triggerCharacters;
	}

	protected final void replace(IDocument document, int offset, int length, String string) throws BadLocationException {
		if (!document.get(offset, length).equals(string))
			document.replace(offset, length, string);
	}
	
	protected static final class ExitPolicy implements IExitPolicy {
		
		final char fExitCharacter;
		private final IDocument fDocument;
	
		public ExitPolicy(char exitCharacter, IDocument document) {
			fExitCharacter= exitCharacter;
			fDocument= document;
		}
	
		/*
		 * @see org.modelica.mdt.ui.text.link.LinkedPositionUI.ExitPolicy#doExit(org.modelica.mdt.ui.text.link.LinkedPositionManager, org.eclipse.swt.events.VerifyEvent, int, int)
		 */
		public ExitFlags doExit(LinkedModeModel environment, VerifyEvent event, int offset, int length) {
	
			if (event.character == fExitCharacter) {
				if (environment.anyPositionContains(offset))
					return new ExitFlags(ILinkedModeListener.UPDATE_CARET, false);
				else
					return new ExitFlags(ILinkedModeListener.UPDATE_CARET, true);
			}
	
			switch (event.character) {
				case ';':
					return new ExitFlags(ILinkedModeListener.NONE, true);
				case SWT.CR:
					// when entering an anonymous class as a parameter, we don't want
					// to jump after the parenthesis when return is pressed
					if (offset > 0) {
						try {
							if (fDocument.getChar(offset - 1) == '{')
								return new ExitFlags(ILinkedModeListener.EXIT_ALL, true);
						} catch (BadLocationException e) {
						}
					}
					// fall through
				default:
					return null;
			}
		}
	
	}
	
	/** The reference position category name. */
	//private static final String CATEGORY= "reference_position"; //$NON-NLS-1$
	/** The position updater of the reference position. */
	//private final IPositionUpdater fPositionUpdater= new DefaultPositionUpdater(CATEGORY);
	/** The reference position. */
	//private final Position fPosition= new Position(0);
	
	
	/**
	 * A class to simplify tracking a reference position in a document.
	 */
	static final class ReferenceTracker {
	
		/** The reference position category name. */
		private static final String CATEGORY= "reference_position"; //$NON-NLS-1$
		/** The position updater of the reference position. */
		private final IPositionUpdater fPositionUpdater= new DefaultPositionUpdater(CATEGORY);
		/** The reference position. */
		private final Position fPosition= new Position(0);
	
		/**
		 * Called before document changes occur. It must be followed by a call to postReplace().
		 *
		 * @param document the document on which to track the reference position.
		 *
		 */
		public void preReplace(IDocument document, int offset) throws BadLocationException {
			fPosition.setOffset(offset);
			try {
				document.addPositionCategory(CATEGORY);
				document.addPositionUpdater(fPositionUpdater);
				document.addPosition(CATEGORY, fPosition);
	
			} catch (BadPositionCategoryException e) {
				// should not happen
				ErrorManager.logError(e);
			}
		}
	
		/**
		 * Called after the document changed occurred. It must be preceded by a call to preReplace().
		 *
		 * @param document the document on which to track the reference position.
		 */
		public int postReplace(IDocument document) {
			try {
				document.removePosition(CATEGORY, fPosition);
				document.removePositionUpdater(fPositionUpdater);
				document.removePositionCategory(CATEGORY);
	
			} catch (BadPositionCategoryException e) {
				// should not happen
				ErrorManager.logError(e);
			}
			return fPosition.getOffset();
		}
	}
	
	
}

