/*
 * (c) Copyright MDT Team, PELAB, 2006
 */

package org.modelica.mdt.ui.editor;

import java.util.ResourceBundle;
import org.eclipse.ui.texteditor.TextEditorAction;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.internal.core.CorePlugin;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.ui.text.ModelicaDocumentProvider;

/**
 * Class that defines the action correcting the indentation of a Modelica file
 */

//public class ModelicaCorrectIndentationAction extends TextEditorAction
//{
//
//	private static final String ACTIONS_RESOURCE_BUNDLE = "org.modelica.mdt.ui.editor.ModelicaActions";
//
//	private static 
//	ModelicaCorrectIndentationAction instance = new ModelicaCorrectIndentationAction();
//
//	/**
//	 * Constructs and updates the action.
//	 */
//	private ModelicaCorrectIndentationAction() {
//		/* Editor will be set later that's why we have null here */
//		super(ResourceBundle.getBundle(ACTIONS_RESOURCE_BUNDLE),
//		"ModelicaCorrectIndentationAction.", null);
//		update();
//	}
//
//	public static ModelicaCorrectIndentationAction getInstance() {
//		return instance;
//	}
//
//	/**
//	 * Code called when the action is fired.
//	 */
//	public void run() 
//	{
//		try
//		{
//			/* Correct Indentation Here */
//			System.err.println("Correct Indentation called");
//		}
//		catch (Exception e) 
//		{
//			ErrorManager.logBug("org.modelica.mdt.editor",
//			"Correct Indentation Failed!");			
//		}
//	}
//
//	
//	//public void update()
//	//{
//	//	super.update();
//		/* 
//		 * 	Subclasses that may modify the editor content should use 
//		 *  canModifyEditor() in their update code to check whether 
//		 *  updating the editor is most likely possible (even if it 
//		 *  is read-only - this may change for editor contents that 
//		 *  are under version control) and validateEditorInputState() 
//		 *  before actually modifying the editor contents. 
//		 */
//	//}
//    
//}



import java.util.ResourceBundle;

import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;

import org.eclipse.jface.text.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.IRewriteTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.source.ISourceViewer;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorExtension3;
import org.eclipse.ui.texteditor.TextEditorAction;


import org.modelica.mdt.ui.text.IModelicaPartitions;

import org.modelica.mdt.ui.editor.ModelicaEditor;
import org.modelica.mdt.ui.text.ModelicaHeuristicScanner;
import org.modelica.mdt.ui.text.ModelicaIndenter;
import org.modelica.mdt.ui.text.ModelicaPartitionScanner;


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
	 * differently to accomodate normal TAB operation.
	 */
	private final boolean fIsTabAction;
	
	private static final String ACTIONS_RESOURCE_BUNDLE = "org.modelica.mdt.ui.editor.ModelicaActions";
	private static ModelicaCorrectIndentationAction instance = 
		new ModelicaCorrectIndentationAction();
	
		/**
		 * Constructs and updates the action.
		 */
		private ModelicaCorrectIndentationAction() 
		{
			/* Editor will be set later that's why we have null here */
			super(ResourceBundle.getBundle(ACTIONS_RESOURCE_BUNDLE),
			"ModelicaCorrectIndentationAction.", null);
			fIsTabAction = false;
		}

		public static ModelicaCorrectIndentationAction getInstance() 
		{
			return instance;
		}
	
	/*
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() 
	{
		// update has been called by the framework
		if (!isEnabled() || !validateEditorInputState())
			return;
		
		ITextSelection selection= getSelection();
		final IDocument document= getDocument();
		
		if (document != null) 
		{
			
			final int offset= selection.getOffset();
			final int length= selection.getLength();
			final Position end= new Position(offset + length);
			final int firstLine, nLines;
			fCaretOffset= -1;
			
			try 
			{
				document.addPosition(end);
				firstLine= document.getLineOfOffset(offset);
				// check for marginal (zero-length) lines
				int minusOne= length == 0 ? 0 : 1;
				nLines= document.getLineOfOffset(offset + length - minusOne) - firstLine + 1;
			} 
			catch (BadLocationException e) 
			{
				// will only happen on concurrent modification
				ErrorManager.logWarning("BadLocationException rised - Concurent Modification");
				e.printStackTrace();
				return;
			}
			
			Runnable runnable= new Runnable() 
			{
				public void run() 
				{
					IRewriteTarget target= (IRewriteTarget)getTextEditor().getAdapter(IRewriteTarget.class);
					if (target != null) {
						target.beginCompoundChange();
						target.setRedraw(false);
					}
					
					try 
					{
						ModelicaHeuristicScanner scanner= new ModelicaHeuristicScanner(document);
						ModelicaIndenter indenter= new ModelicaIndenter(document, scanner);
						boolean hasChanged= false;
						for (int i= 0; i < nLines; i++) 
						{
							hasChanged |= indentLine(document, firstLine + i, offset, indenter, scanner);
						}
						
						// update caret position: move to new position when indenting just one line
						// keep selection when indenting multiple
						int newOffset, newLength;
						if (fIsTabAction) 
						{
							newOffset= fCaretOffset;
							newLength= 0;
						} 
						else if (nLines > 1) 
						{
							newOffset= offset;
							newLength= end.getOffset() - offset;
						} 
						else 
						{
							newOffset= fCaretOffset;
							newLength= 0;
						}
						
						// always reset the selection if anything was replaced
						// but not when we had a singleline nontab invocation
						if (newOffset != -1 && (hasChanged || newOffset != offset || newLength != length))
							selectAndReveal(newOffset, newLength);
						
						document.removePosition(end);
					} 
					catch (BadLocationException e) 
					{
						// will only happen on concurrent modification
						ErrorManager.logWarning("BadLocationException rised - Concurent Modification");
						e.printStackTrace();						
					} 
					finally 
					{	
						if (target != null) 
						{
							target.endCompoundChange();
							target.setRedraw(true);
						}
					}
				}
			};
			
			if (nLines > 50) 
			{
				Display display= getTextEditor().getEditorSite().getWorkbenchWindow().getShell().getDisplay();
				BusyIndicator.showWhile(display, runnable);
			} 
			else
				runnable.run();
			
		}
	}
	
	/**
	 * Selects the given range on the editor.
	 * 
	 * @param newOffset the selection offset
	 * @param newLength the selection range
	 */
	private void selectAndReveal(int newOffset, int newLength) 
	{
		Assert.isTrue(newOffset >= 0); 
		Assert.isTrue(newLength >= 0); 
		ITextEditor editor= getTextEditor();
		if (editor instanceof ModelicaEditor) 
		{
			ISourceViewer viewer= ((ModelicaEditor)editor).getViewer();
			if (viewer != null)
				viewer.setSelectedRange(newOffset, newLength);
		} else
			// this is too intrusive, but will never get called anyway
			getTextEditor().selectAndReveal(newOffset, newLength);
			
	}

	/**
	 * Indents a single line using the java heuristic scanner. 
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
		//System.out.println("Line: " + line);
		IRegion currentLine= document.getLineInformation(line);
		int offset= currentLine.getOffset();
		int wsStart= offset;  // where we start searching for non-WS; after the "" in single line comments
		
		String indent= null;
		if (offset < document.getLength()) 
		{
			ITypedRegion partition= TextUtilities.getPartition(document, IModelicaPartitions.MODELICA_PARTITIONING, offset, true);
			ITypedRegion startingPartition= TextUtilities.getPartition(document, IModelicaPartitions.MODELICA_PARTITIONING, offset, false);
			String type= partition.getType();
			//System.out.print("["+startingPartition.getType()+"] ");
			//System.out.println(document.get(offset, currentLine.getLength()));			
			/* do not indent multiline strings! */			
			if (type.equals(IModelicaPartitions.MODELICA_STRING) ||
				startingPartition.getType().equals(IModelicaPartitions.MODELICA_STRING))
			{
				return false;
			}
			if (type.equals(IModelicaPartitions.MODELICA_MULTI_LINE_COMMENT)) 
			{
				indent= computeModelicadocIndent(document, line, scanner);
			} 
		} 
		
		// standard java indentation
		if (indent == null) 
		{
			StringBuffer computed= indenter.computeIndentation(offset);
			if (computed != null)
				indent= computed.toString();
			else
				indent= new String();
		}
		
		// change document:
		// get current white space
		int lineLength= currentLine.getLength();
		int end= scanner.findNonWhitespaceForwardInAnyPartition(wsStart, offset + lineLength);
		if (end == ModelicaHeuristicScanner.NOT_FOUND)
			end= offset + lineLength;
		int length= end - offset;
		String currentIndent= document.get(offset, length);
		
		// if we are right before the text start / line end, and already after the insertion point
		// then just insert a tab.
		if (fIsTabAction && caret == end && whiteSpaceLength(currentIndent) >= whiteSpaceLength(indent)) 
		{
			String tab= getTabEquivalent();
			document.replace(caret, 0, tab);
			fCaretOffset= caret + tab.length();
			return true;
		}
		
		// set the caret offset so it can be used when setting the selection
		if (caret >= offset && caret <= end)
			fCaretOffset= offset + indent.length();
		else
			fCaretOffset= -1;
		
		// only change the document if it is a real change
		if (!indent.equals(currentIndent)) 
		{
			String deletedText= document.get(offset, length);
			document.replace(offset, length, indent);
			
			if (fIsTabAction && indent.length() > currentIndent.length() && true) 
			{
				ITextEditor editor= getTextEditor();
			}
			return true;
		} else
			return false;
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
		if (line == 0)
			return null;
		
		IRegion previousLine= document.getLineInformation(line - 1);
		int start= previousLine.getOffset();
		int end= start + previousLine.getLength();
		
		int firstNonWS= scanner.findNonWhitespaceForwardInAnyPartition(start, end);
		if (firstNonWS == ModelicaHeuristicScanner.NOT_FOUND)
			return document.get(start, end - start);
		StringBuffer buf= new StringBuffer();
		String indentation= document.get(start, firstNonWS - start);
		buf.append(indentation);
		if (document.getChar(firstNonWS) == '/')
			// Modelica comment started on the previous line
			buf.append(' ');
		return buf.toString();
	}
	
	/**
	 * Returns the size in characters of a string. All characters count one, tabs count the editor's
	 * preference for the tab display 
	 * 
	 * @param indent the string to be measured.
	 * @return the size in characters of a string
	 */
	private int whiteSpaceLength(String indent) 
	{
		if (indent == null)
			return 0;
		else 
		{
			int size= 0;
			int l= indent.length();
			int tabSize= getTabSize();
			
			for (int i= 0; i < l; i++)
				size += indent.charAt(i) == '\t' ? tabSize : 1;
			return size;
		}
	}

	/**
	 * Returns a tab equivalent, either as a tab character or as spaces, depending on the editor and
	 * formatter preferences.
	 * 
	 * @return a string representing one tab in the editor, never <code>null</code>
	 */
	private String getTabEquivalent() {
		String tab;
		int size= getTabSize();
		StringBuffer buf= new StringBuffer();
		for (int i= 0; i< size; i++)
			buf.append(' ');
		tab= buf.toString();	
		return tab;
	}
	
	/**
	 * Returns the tab size used by the java editor, which is deduced from the
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
	private ISelectionProvider getSelectionProvider() 
	{
		ITextEditor editor= getTextEditor();
		if (editor != null) 
		{
			return editor.getSelectionProvider();
		}
		return null;
	}
	
	/*
	 * @see org.eclipse.ui.texteditor.IUpdate#update()
	 */
	public void update() 
	{
		super.update();
		
		if (isEnabled())
			if (fIsTabAction)
				setEnabled(canModifyEditor() && isSmartMode() && isValidSelection());
			else
				setEnabled(canModifyEditor() && !getSelection().isEmpty());
	}
	
	/**
	 * Returns if the current selection is valid, i.e. whether it is empty and the caret in the 
	 * whitespace at the start of a line, or covers multiple lines.
	 * 
	 * @return <code>true</code> if the selection is valid for an indent operation
	 */
	private boolean isValidSelection() 
	{
		ITextSelection selection= getSelection();
		if (selection.isEmpty())
			return false;
		
		int offset= selection.getOffset();
		int length= selection.getLength();
		
		IDocument document= getDocument();
		if (document == null)
			return false;
		
		try 
		{
			IRegion firstLine= document.getLineInformationOfOffset(offset);
			int lineOffset= firstLine.getOffset();
			
			// either the selection has to be empty and the caret in the WS at the line start
			// or the selection has to extend over multiple lines
			if (length == 0)
				return document.get(lineOffset, offset - lineOffset).trim().length() == 0;
			else
				return lineOffset + firstLine.getLength() < offset + length;
				// return false;  only enable for empty selections for now
			
		} 
		catch (BadLocationException e) 
		{
		}
		
		return false;
	}
	
	/**
	 * Returns the smart preference state.
	 * 
	 * @return <code>true</code> if smart mode is on, <code>false</code> otherwise
	 */
	private boolean isSmartMode() 
	{
		ITextEditor editor= getTextEditor();
		
		if (editor instanceof ITextEditorExtension3)
			return ((ITextEditorExtension3) editor).getInsertMode() == ITextEditorExtension3.SMART_INSERT;
		
		return false;
	}
	
	/**
	 * Returns the document currently displayed in the editor, or <code>null</code> if none can be 
	 * obtained.
	 * 
	 * @return the current document or <code>null</code>
	 */
	private IDocument getDocument() 
	{
		
		ModelicaEditor editor= (ModelicaEditor)getTextEditor();
		if (editor != null) 
		{	
			ModelicaDocumentProvider provider= (ModelicaDocumentProvider)editor.getDocumentProvider();
			IEditorInput input= editor.getEditorInput();
			if (provider != null && input != null)
			{
				IDocument doc = provider.getDocument(input);
				/*
				IDocumentPartitioner partitioner =
					new FastPartitioner(
						new ModelicaPartitionScanner(),
						IModelicaPartitions.partitionTypes);
				partitioner.connect(doc);
				doc.setDocumentPartitioner(partitioner);
				*/
				return doc;
			}
			
		}
		return null;
	}
	
	/**
	 * Returns the selection on the editor or an invalid selection if none can be obtained. Returns
	 * never <code>null</code>.
	 * 
	 * @return the current selection, never <code>null</code>
	 */
	private ITextSelection getSelection() 
	{
		ISelectionProvider provider= getSelectionProvider();
		if (provider != null) 
		{	
			ISelection selection= provider.getSelection();
			if (selection instanceof ITextSelection)
				return (ITextSelection) selection;
		}
		
		// null object
		return TextSelection.emptySelection();
	}
	
}


