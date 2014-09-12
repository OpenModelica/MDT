package org.modelica.mdt.ui.editor;

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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

/**
 * A content outline page which always represents the content of the connected
 * editor in 10 segments.
 */
public class SusanContentOutlinePage extends ContentOutlinePage
{

	/**
	 * A segment element.
	 */
	protected static class Segment
	{
		public String name;
		public Position position;

		public Segment(String name, Position position)
		{
			this.name = name;
			this.position = position;
		}

		public String toString()
		{
			return name;
		}
	}

	public class CustomComparator implements Comparator<Segment>
	{
		@Override
		public int compare(Segment o1, Segment o2)
		{
			return o1.toString().toUpperCase().compareTo(o2.toString().toUpperCase());
		}
	}

	/**
	 * Divides the editor's document into ten segments and provides elements for
	 * them.
	 */
	protected class ContentProvider implements ITreeContentProvider
	{

		protected final static String SEGMENTS = "__susan_segments"; //$NON-NLS-1$
		protected IPositionUpdater fPositionUpdater = new DefaultPositionUpdater(SEGMENTS);
		protected List<Segment> fContent = new ArrayList<Segment>();
		protected HashMap<String, ArrayList<Segment>> fChildContent = new HashMap<String, ArrayList<Segment>>();

		protected void parse(IDocument document)
		{

			int lines = document.getNumberOfLines();
			int actPos = 0;

			for (int line = 0; line < lines; line++/* = increment */) {

				try {
					int length = document.getLineLength(line);
					int offset = document.getLineOffset(line);
					Position p = new Position(offset, 0);

					String lineString = document.get(actPos, length);
					String SearchWord = "template";
					if ((lineString.indexOf(SearchWord) > -1) && ((lineString.indexOf("//") > lineString.indexOf(SearchWord) || lineString.indexOf("//") == -1))) {

						String tempName = "";
						int startPos = lineString.indexOf(SearchWord) + SearchWord.length();
						int endPos = lineString.indexOf(")") + 1;

						if (endPos <= startPos) {
							actPos += length;
							continue;
						}
						tempName = lineString.substring(startPos, Math.min(endPos, length));
						document.addPosition(SEGMENTS, p);

						ArrayList<Segment> subTree = new ArrayList<Segment>();

						fContent.add(new Segment(MessageFormat.format(tempName, new Object[] { new Integer(p.offset) }), p)); //$NON-NLS-1$

						startPos = tempName.indexOf("(") + 1;
						if (startPos >= 0) {
							String s = tempName.substring(startPos, tempName.length() - 1);
							String[] split = s.split(",");
							for (int i = 0; i < split.length; i++) {
								subTree.add(new Segment(MessageFormat.format(split[i], new Object[] { new Integer(p.offset) }), p));
							}
						}
						fChildContent.put(tempName, subTree);

					}

					actPos += length;
				} catch (BadPositionCategoryException x) {
				} catch (BadLocationException x) {
				}
			}

			// sort the top of the tree alphabetic
			Collections.sort(fContent, new CustomComparator());

		}

		/*
		 * @see IContentProvider#inputChanged(Viewer, Object, Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
		{
			if (oldInput != null) {
				IDocument document = fDocumentProvider.getDocument(oldInput);
				if (document != null) {
					try {
						document.removePositionCategory(SEGMENTS);
					} catch (BadPositionCategoryException x) {
					}
					document.removePositionUpdater(fPositionUpdater);
				}
			}

			fContent.clear();

			if (newInput != null) {
				IDocument document = fDocumentProvider.getDocument(newInput);
				if (document != null) {
					document.addPositionCategory(SEGMENTS);
					document.addPositionUpdater(fPositionUpdater);

					parse(document);
				}
			}
		}

		/*
		 * @see IContentProvider#dispose
		 */
		public void dispose()
		{
			if (fContent != null) {
				fContent.clear();
				fContent = null;
			}
		}

		/*
		 * @see IContentProvider#isDeleted(Object)
		 */
		public boolean isDeleted(Object element)
		{
			return false;
		}

		/*
		 * @see IStructuredContentProvider#getElements(Object)
		 */
		public Object[] getElements(Object element)
		{

			return fContent.toArray();
		}

		/*
		 * @see ITreeContentProvider#hasChildren(Object)
		 */
		public boolean hasChildren(Object element)
		{

			if (fChildContent.get(((Segment) element).toString()) != null)
				return true;
			else

				return element == fInput;
		}

		/*
		 * @see ITreeContentProvider#getParent(Object)
		 */
		public Object getParent(Object element)
		{
			if (element instanceof Segment)
				return fInput;
			return null;
		}

		/*
		 * @see ITreeContentProvider#getChildren(Object)
		 */
		public Object[] getChildren(Object element)
		{
			if (element == fInput)
				return fContent.toArray();

			if (fChildContent.get(((Segment) element).toString()) != null)
				return fChildContent.get(((Segment) element).toString()).toArray();

			return new Object[0];
		}
	}

	protected Object fInput;
	protected IDocumentProvider fDocumentProvider;
	protected ITextEditor fTextEditor;

	/**
	 * Creates a content outline page using the given provider and the given
	 * editor.
	 * 
	 * @param provider
	 *            the document provider
	 * @param editor
	 *            the editor
	 */
	public SusanContentOutlinePage(IDocumentProvider provider, ITextEditor editor)
	{
		super();
		fDocumentProvider = provider;
		fTextEditor = editor;
	}

	/*
	 * (non-Javadoc) Method declared on ContentOutlinePage
	 */
	public void createControl(Composite parent)
	{

		super.createControl((org.eclipse.swt.widgets.Composite) parent);

		TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider(new ContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		viewer.addSelectionChangedListener(this);

		if (fInput != null)
			viewer.setInput(fInput);
	}

	/*
	 * (non-Javadoc) Method declared on ContentOutlinePage
	 */
	public void selectionChanged(SelectionChangedEvent event)
	{

		super.selectionChanged(event);

		ISelection selection = event.getSelection();
		if (selection.isEmpty())
			fTextEditor.resetHighlightRange();
		else {
			Segment segment = (Segment) ((IStructuredSelection) selection).getFirstElement();
			int start = segment.position.getOffset();
			int length = segment.position.getLength();
			try {
				fTextEditor.setHighlightRange(start, length, true);
			} catch (IllegalArgumentException x) {
				fTextEditor.resetHighlightRange();
			}
		}
	}

	/**
	 * Sets the input of the outline page
	 * 
	 * @param input
	 *            the input of this outline page
	 */
	public void setInput(Object input)
	{
		fInput = input;
		update();
	}

	/**
	 * Updates the outline page.
	 */
	public void update()
	{
		TreeViewer viewer = getTreeViewer();

		if (viewer != null) {
			Control control = viewer.getControl();
			if (control != null && !control.isDisposed()) {
				control.setRedraw(false);
				viewer.setInput(fInput);
				viewer.expandAll();
				control.setRedraw(true);
			}
		}
	}
}