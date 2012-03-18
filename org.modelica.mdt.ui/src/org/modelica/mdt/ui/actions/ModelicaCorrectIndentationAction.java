/*
 * (c) Copyright MDT Team, PELAB, 2006
 */

package org.modelica.mdt.ui.actions;

import java.util.ResourceBundle;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.IRewriteTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorExtension3;
import org.eclipse.ui.texteditor.ITextEditorExtension3.InsertMode;
import org.eclipse.ui.texteditor.TextEditorAction;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.ui.constants.Constants;
import org.modelica.mdt.ui.editor.ModelicaEditor;
import org.modelica.mdt.ui.text.IModelicaPartitions;
import org.modelica.mdt.ui.text.ModelicaDocumentProvider;
import org.modelica.mdt.ui.text.ModelicaHeuristicScanner;
import org.modelica.mdt.ui.text.ModelicaIndenter;

/**
 * Indents a line or range of lines in a Modelica document to its correct position. No complete
 * AST must be present, the indentation is computed using heuristics. The algorithm used is fast for
 * single lines, but does not store any information and therefore not so efficient for large line
 * ranges.
 *
 * @see org.modelica.mdt.ui.text.ModelicaHeuristicScanner
 * @see org.modelica.mdt.ui.text.ModelicaIndenter
 */
public class ModelicaCorrectIndentationAction extends TextEditorAction {

	/** The caret offset after an indent operation. */
	private int fCaretOffset;

	/**
	 * Whether this is the action invoked by TAB. When <code>true</code>, indentation behaves
	 * differently to accommodate normal TAB operation.
	 */
	private final boolean fIsTabAction;

	private DocumentCommand documentCommand = null;
	private int indentLength = 0;

	private static ModelicaCorrectIndentationAction instance = new ModelicaCorrectIndentationAction();

	/**
	 * Constructs and updates the action.
	 */
	private ModelicaCorrectIndentationAction() {
		/* Editor will be set later that's why we have null here */
		super(ResourceBundle.getBundle(Constants.MDT_UI_ACTIONS_RESOURCE_BUNDLE), "ModelicaCorrectIndentationAction.", null);

		fIsTabAction = false;
	}

	public static ModelicaCorrectIndentationAction getInstance() {
		return instance;
	}

	public static ModelicaCorrectIndentationAction getInstance(DocumentCommand documentCommand) {
		instance.documentCommand = documentCommand;

		return instance;
	}

	public static void resetDocumentCommand() {
		instance.documentCommand = null;
	}

	/*
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		// update has been called by the framework
		if (!isEnabled() || !validateEditorInputState()) {
			return;
		}

		ITextSelection selection = getSelection();
		final IDocument document = getDocument();

		if (document != null) {
			final int offset = selection.getOffset();
			final int length = selection.getLength();
			final Position end = new Position(offset + length);
			final int firstLine, nLines;

			fCaretOffset = -1;
			indentLength = 0;

			try {
				document.addPosition(end);
				firstLine = document.getLineOfOffset(offset);
				// check for marginal (zero-length) lines
				int minusOne = length == 0 ? 0 : 1;
				nLines = document.getLineOfOffset(offset + length - minusOne) - firstLine + 1;
			}
			catch (BadLocationException e) {
				// will only happen on concurrent modification
				ErrorManager.logWarning("BadLocationException raised - Concurrent Modification");
				e.printStackTrace();
				return;
			}

			final ITextEditor textEditor = getTextEditor();

			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					IRewriteTarget target = (IRewriteTarget)textEditor.getAdapter(IRewriteTarget.class);

					if (target != null) {
						target.beginCompoundChange();
						target.setRedraw(false);
					}

					try {
						ModelicaHeuristicScanner scanner = new ModelicaHeuristicScanner(document);
						ModelicaIndenter indenter = new ModelicaIndenter(document, scanner);
						boolean hasChanged = false;

						for (int i = 0; i < nLines; i++) {
							hasChanged |= indentLine(document, firstLine + i, offset, indenter, scanner);
						}

						if (nLines > 1 && hasChanged && documentCommand != null) {
							ErrorManager.logWarning("Odd, documentCommand is not null for a change of more than one line (nLines = " + nLines + ").");
						}

						// update caret position: move to new position when indenting just one line
						// keep selection when indenting multiple
						int newOffset, newLength;

						// FIXME: fIsTabAction is always false.
						if (fIsTabAction) {
							newOffset = fCaretOffset;
							newLength = 0;
						}
						else if (nLines > 1) {
							newOffset = offset;
							newLength = end.getOffset() - offset;
						}
						else {
							newOffset = fCaretOffset;
							newLength = 0;
						}

						// always reset the selection if anything was replaced
						// but not when we had a singleline nontab invocation
						if (newOffset != -1 && (hasChanged || newOffset != offset || newLength != length)) {
							selectAndReveal(newOffset, newLength);
						}
						else if (hasChanged) {
							if (nLines == 1) {
								if (indentLength != 0) {
									if (documentCommand != null) {
										documentCommand.offset += indentLength;
									}
									else {
										ErrorManager.logWarning("Indentation was invoked from the menu and a single line indent was generated, " +
												"but I have no DocumentCommand object to update. Refactoring needed!");
									}
								}
								else {
									ErrorManager.logWarning("Odd, hasChanged is true and nLines is 1, but indentLength is 0.");
								}
							}
							else {
								ErrorManager.logWarning("Odd, hasChanged is true but nLines was not 1, it was " + nLines);
							}
						}

						document.removePosition(end);
					}
					catch (BadLocationException e) {
						// will only happen on concurrent modification
						ErrorManager.logWarning("BadLocationException raised - Concurrent Modification");
						e.printStackTrace();
					}
					finally {
						if (target != null) {
							target.endCompoundChange();
							target.setRedraw(true);
						}
					}
				}
			};

			if (nLines > 50) {
				Display display = textEditor.getEditorSite().getWorkbenchWindow().getShell().getDisplay();
				BusyIndicator.showWhile(display, runnable);
			}
			else {
				runnable.run();
			}
		}
	}

	/**
	 * Selects the given range on the editor.
	 *
	 * @param newOffset the selection offset
	 * @param newLength the selection range
	 */
	private void selectAndReveal(int newOffset, int newLength) {
		Assert.isTrue(newOffset >= 0);
		Assert.isTrue(newLength >= 0);

		ITextEditor editor = getTextEditor();

		if (editor instanceof ModelicaEditor) {
			ModelicaEditor modelicaEditor = (ModelicaEditor)editor;
			ISourceViewer viewer = modelicaEditor.getViewer();

			if (viewer != null) {
				viewer.setSelectedRange(newOffset, newLength);
			}
		}
		else {
			// this is too intrusive, but will never get called anyway
			editor.selectAndReveal(newOffset, newLength);
		}
	}

	/**
	 * Indents a single line using the modelica heuristic scanner.
	 * Modelica comments and multiline comments are indented also
	 *
	 * @param document the document
	 * @param line the line to be indented
	 * @param caret the caret position
	 * @param indenter the Modelica indenter
	 * @param scanner the heuristic scanner
	 * @return <code>true</code> if <code>document</code> was modified, <code>false</code> otherwise
	 * @throws BadLocationException if the document got changed concurrently
	 */
	private boolean indentLine(IDocument document, int line, int caret, ModelicaIndenter indenter, ModelicaHeuristicScanner scanner) throws BadLocationException {
		IRegion currentLine = document.getLineInformation(line);
		int offset = currentLine.getOffset();
		int wsStart = offset; // where we start searching for non-WS; after the "" in single line comments
		String indent = null;
		int documentLength = document.getLength();

		if (offset < documentLength) {
			ITypedRegion partition = TextUtilities.getPartition(document, IModelicaPartitions.MODELICA_PARTITIONING, offset, true);
			ITypedRegion startingPartition = TextUtilities.getPartition(document, IModelicaPartitions.MODELICA_PARTITIONING, offset, false);
			String partitionType = partition.getType();

			// do not indent multiline strings!
			if (partitionType.equals(IModelicaPartitions.MODELICA_STRING) || startingPartition.getType().equals(IModelicaPartitions.MODELICA_STRING)) {
				return false;
			}
			else if (partitionType.equals(IModelicaPartitions.MODELICA_MULTI_LINE_COMMENT)) {
				indent = computeModelicadocIndent(document, line, scanner);
			}
		}

		// standard modelica indentation
		if (indent == null) {
			StringBuffer computed = indenter.computeIndentation(offset);

			if (computed != null) {
				indent = computed.toString();
			}
			else {
				indent = new String();
			}
		}

		// change document:
		// get current white space
		int lineLength = currentLine.getLength();
		int end = scanner.findNonWhitespaceForwardInAnyPartition(wsStart, offset + lineLength);

		if (end == ModelicaHeuristicScanner.NOT_FOUND) {
			end = offset + lineLength;
		}

		int length = end - offset;
		String currentIndent = document.get(offset, length);

		// if we are right before the text start / line end, and already after the insertion point
		// then just insert a tab.
		// FIXME: fIsTabAction is always false.
		if (fIsTabAction && caret == end && whiteSpaceLength(currentIndent) >= whiteSpaceLength(indent)) {
			String tab = getTabEquivalent();
			document.replace(caret, 0, tab);
			fCaretOffset = caret + tab.length();
			return true;
		}

		// set the caret offset so it can be used when setting the selection
		if (caret >= offset && caret <= end) {
			fCaretOffset = offset + indent.length();
		}
		else {
			fCaretOffset = -1;
		}

		// only change the document if it is a real change
		if (!indent.equals(currentIndent)) {
			document.replace(offset, length, indent);
			indentLength = indent.length();

			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Computes and returns the indentation for a Modelica comment line. The line
	 * must be inside a Modelica comment.
	 *
	 * @param document the document
	 * @param line the line in document
	 * @param scanner the scanner
	 * @return the indent, or <code>null</code> if not computable
	 * @throws BadLocationException
	 * @since 3.1
	 */
	private String computeModelicadocIndent(IDocument document, int line, ModelicaHeuristicScanner scanner) throws BadLocationException {
		if (line == 0) {
			return null;
		}

		IRegion previousLine = document.getLineInformation(line - 1);
		int start = previousLine.getOffset();
		int end = start + previousLine.getLength();
		int firstNonWS = scanner.findNonWhitespaceForwardInAnyPartition(start, end);

		if (firstNonWS == ModelicaHeuristicScanner.NOT_FOUND) {
			return document.get(start, end - start);
		}

		StringBuffer buf = new StringBuffer();
		String indentation = document.get(start, firstNonWS - start);

		buf.append(indentation);

		if (document.getChar(firstNonWS) == '/') {
			// Modelica comment started on the previous line
			buf.append(' ');
		}

		String ret = buf.toString();
		return ret;
	}

	/**
	 * Returns the size in characters of a string. All characters count one, tabs count the editor's
	 * preference for the tab display
	 *
	 * @param indent the string to be measured.
	 * @return the size in characters of a string
	 */
	private int whiteSpaceLength(String indent) {
		int whitespaceLength = 0;

		if (indent != null) {
			int l = indent.length();
			int tabSize = getTabSize();

			for (int i = 0; i < l; i++) {
				whitespaceLength += indent.charAt(i) == '\t' ? tabSize : 1;
			}
		}

		return whitespaceLength;
	}

	/**
	 * Returns a tab equivalent, either as a tab character or as spaces, depending on the editor and
	 * formatter preferences.
	 *
	 * @return a string representing one tab in the editor, never <code>null</code>
	 */
	private String getTabEquivalent() {
		int size = getTabSize();
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < size; i++) {
			buf.append(' ');
		}

		String tab = buf.toString();
		return tab;
	}

	/**
	 * Returns the tab size used by the modelica editor, which is deduced from the
	 * formatter preferences.
	 *
	 * @return the tab size as defined in the current formatter preferences
	 */
	private int getTabSize() {
		return 2;
	}

	/**
	 * Returns the editor's selection provider.
	 *
	 * @return the editor's selection provider or <code>null</code>
	 */
	private ISelectionProvider getSelectionProvider() {
		ITextEditor editor = getTextEditor();
		ISelectionProvider selectionProvider = null;

		if (editor != null) {
			selectionProvider = editor.getSelectionProvider();
		}

		return selectionProvider;
	}

	/*
	 * @see org.eclipse.ui.texteditor.IUpdate#update()
	 */
	@Override
	public void update() {
		super.update();

		if (isEnabled()) {
			boolean canModifyEditor = canModifyEditor();

			// FIXME: fIsTabAction is always false.
			if (fIsTabAction) {
				setEnabled(canModifyEditor && isSmartMode() && isValidSelection());
			}
			else {
				setEnabled(canModifyEditor && !getSelection().isEmpty());
			}
		}
	}

	/**
	 * Returns if the current selection is valid, i.e. whether it is empty and the caret in the
	 * whitespace at the start of a line, or covers multiple lines.
	 *
	 * @return <code>true</code> if the selection is valid for an indent operation
	 */
	private boolean isValidSelection() {
		ITextSelection selection = getSelection();

		if (selection.isEmpty()) {
			return false;
		}

		int offset = selection.getOffset();
		int length = selection.getLength();

		IDocument document = getDocument();

		if (document == null) {
			return false;
		}

		try {
			IRegion firstLine = document.getLineInformationOfOffset(offset);
			int lineOffset = firstLine.getOffset();

			// either the selection has to be empty and the caret in the WS at the line start
			// or the selection has to extend over multiple lines
			if (length == 0) {
				return document.get(lineOffset, offset - lineOffset).trim().length() == 0;
			}
			else {
				return lineOffset + firstLine.getLength() < offset + length;
			}
		}
		catch (BadLocationException e) {
		}

		return false;
	}

	/**
	 * Returns the smart preference state.
	 *
	 * @return <code>true</code> if smart mode is on, <code>false</code> otherwise
	 */
	private boolean isSmartMode() {
		ITextEditor editor = getTextEditor();
		boolean isSmartMode = false;

		if (editor instanceof ITextEditorExtension3) {
			InsertMode insertMode = ((ITextEditorExtension3)editor).getInsertMode();
			isSmartMode = insertMode == ITextEditorExtension3.SMART_INSERT;
		}

		return isSmartMode;
	}

	/**
	 * Returns the document currently displayed in the editor, or <code>null</code> if none can be
	 * obtained.
	 *
	 * @return the current document or <code>null</code>
	 */
	private IDocument getDocument() {
		ITextEditor editor = getTextEditor();
		IDocument doc = null;

		if (editor != null) {
			if (editor instanceof ModelicaEditor) {
				ModelicaDocumentProvider provider = (ModelicaDocumentProvider)editor.getDocumentProvider();
				IEditorInput input = editor.getEditorInput();

				if (provider != null && input != null) {
					doc = provider.getDocument(input);
				}
			}
			else {
				ErrorManager.logWarning("We got an editor, but it was not an instance of ModelicaEditor.");
			}
		}

		return doc;
	}

	/**
	 * Returns the selection on the editor or an invalid selection if none can be obtained. Returns
	 * never <code>null</code>.
	 *
	 * @return the current selection, never <code>null</code>
	 */
	private ITextSelection getSelection() {
		ISelectionProvider provider = getSelectionProvider();
		ITextSelection textSelection = TextSelection.emptySelection(); // null object

		if (provider != null) {
			ISelection selection = provider.getSelection();

			if (selection instanceof ITextSelection) {
				textSelection = (ITextSelection)selection;
			}
		}

		return textSelection;
	}
}
