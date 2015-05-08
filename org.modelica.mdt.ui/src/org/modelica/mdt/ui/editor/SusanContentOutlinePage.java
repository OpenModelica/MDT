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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

/**
 * A content outline page which always represents the content of the connected
 * editor in 10 segments.
 */
public class SusanContentOutlinePage extends ContentOutlinePage
{
	protected final static String OUTLINE_MARKER_ID = "__susan_segments";
	
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
	
	/**
	 * A segment element.
	 */
	protected static class Segment
	{
		public String name;
		public Position position;
		public boolean isChild;

		public Segment(String name, Position position, boolean isChild)
		{
			this.name = name;
			this.position = position;
			this.isChild = isChild;
		}

		public String toString()
		{
			return name;
		}
	}
	

	public class SegmentStringUpperComparator implements Comparator<Segment>
	{
		@Override
		public int compare(Segment o1, Segment o2)
		{
			return o1.toString().toUpperCase().compareTo(o2.toString().toUpperCase());
		}
	}
	
	/**
	 * Use this iterator to iterate over all lines of the susan document
	 */
	protected class DocumentLineIterator implements Iterable<String>, Iterator<String>
	{
		private IDocument document;
		private ITypedRegion currentRegion;
		private int currentRegionOffset;
		private int currentLine;
		private String[] currentRegionLines;
		
		public DocumentLineIterator(IDocument document)
		{
			this.document = document;
			currentRegion = null;
			currentRegionOffset = 0;
			currentLine = 0;
			currentRegionLines = new String[0];
		}
		
		public int getCurrentOffset()
		{
			return currentRegionOffset;
		}
		
		@Override
		public boolean hasNext()
		{
			if(currentLine == currentRegionLines.length - 1)
				return hasNextValidRegion();
			
			return true;
		}

		@Override
		public String next()
		{
			if(currentLine >= currentRegionLines.length - 1)
			{
				currentRegion = goToNextValidRegion();
					
				if(currentRegion == null)
					throw new NoSuchElementException();
					
				currentRegionLines = document.get().substring(currentRegion.getOffset(), currentRegion.getOffset() + currentRegion.getLength()).split("\\r?\\n");
				currentLine = 0;
				if(currentRegionLines.length > 0)
					currentRegionOffset += currentRegionLines[0].length() + 1;
				else
				{
					currentRegionOffset += 1;
					return "";
				}
			}
			else
			{
				currentLine++;
				currentRegionOffset += currentRegionLines[currentLine].length() + 1;	
			}
			
			return prepareLineString(currentRegionLines[currentLine]);
		}

		protected ITypedRegion goToNextValidRegion()
		{
			ITypedRegion region = null;

			int startOffset = currentRegionOffset;
			while(true)
			{
				try {
					region = document.getPartition(startOffset);
					if(isRegionValid(region))
					{
						currentRegionOffset = startOffset;
						return region;
					}
					startOffset += region.getLength() + 1;
				} catch (BadLocationException e) {
					return null;
				}
			}		
		}
		
		protected String prepareLineString(String lineString)
		{
			int commentIndex = lineString.indexOf("//");
			if(commentIndex >= 0)
				return lineString.substring(0, commentIndex);
			
			return lineString;
		}
		
		protected boolean hasNextValidRegion()
		{
			ITypedRegion region = null;

			int startOffset = currentRegionOffset;
			while(true)
			{
				try {
					region = document.getPartition(startOffset);
					if(isRegionValid(region))
						return true;
					startOffset += region.getLength() + 1;
				} catch (BadLocationException e) {
					return false;
				}
			}
		}
		
		protected boolean isRegionValid(ITypedRegion region)
		{
			return region.getType() == "__dftl_partition_content_type";
		}
		
		@Override
		public void remove()
		{
		}

		@Override
		public Iterator<String> iterator()
		{
			return this;
		}
	}

	protected class SusanTemplateFinderThread extends Thread
	{
		private IDocument document;
		protected List<Segment> innerContent;
		protected HashMap<String, ArrayList<Segment>> innerChildContent;
		protected ContentProvider contentProvider;
		protected Pattern templateFunctionHeadPattern = Pattern.compile("[ \\t]*template[ \\t]+([^\\(]+)\\(([^\\)]*)\\)(.)*");
		protected Pattern templateStartPattern = Pattern.compile("[ \\t]*template(.)*");
		protected Pattern templateEndPattern = Pattern.compile("(.)*::=(.)*");
		private Matcher blockTemplateMatcher = null;
		
		public SusanTemplateFinderThread(IDocument document, ContentProvider contentProvider)
		{
			this.document = document;
			this.innerContent = new ArrayList<SusanContentOutlinePage.Segment>();
			this.innerChildContent = new HashMap<String, ArrayList<Segment>>();
			this.contentProvider = contentProvider;
		}
		
		@Override
		public void run()
		{
			DocumentLineIterator lineIterator = new DocumentLineIterator(document);
			String currentBlock = "";

			boolean noMoreFunctionsFound = false;
			int lineOffset = 0;
			
			try {
				for(String line : lineIterator)
				{
					if(isInterrupted())
						return;
					currentBlock = line;
					
					//Check if current line contains a template definition
					if(templateStartPattern.matcher(currentBlock).matches())
					{
						//Check if the end-pattern is already part of the text
						while(!templateEndPattern.matcher(currentBlock).matches())
						{
							//Include the next line
							if(lineIterator.hasNext())
								currentBlock += lineIterator.next();
							else
							{
								noMoreFunctionsFound = true;
								break;
							}
						}
						
						if(noMoreFunctionsFound)
							break;
						
						blockTemplateMatcher = templateFunctionHeadPattern.matcher(currentBlock);
						if(!blockTemplateMatcher.matches())
							continue;
						
						Position markerPosition = new Position(lineOffset, 0);
						try {
							document.addPosition(OUTLINE_MARKER_ID, markerPosition);
						} catch (BadLocationException e) {
							e.printStackTrace();
							break;
						} catch (BadPositionCategoryException e) {
							e.printStackTrace();
							break;
						}

						ArrayList<Segment> subTree = new ArrayList<Segment>();

						innerContent.add(new Segment(MessageFormat.format(blockTemplateMatcher.group(1), new Object[] { new Integer(markerPosition.offset) }), markerPosition, false));

						for(String arg : blockTemplateMatcher.group(2).split(","))
						{
							if(arg.trim().length() > 0)
								subTree.add(new Segment(MessageFormat.format(arg.trim(), new Object[] { new Integer(markerPosition.offset) }), markerPosition, true));
						}
						innerChildContent.put(blockTemplateMatcher.group(1), subTree);
					}
					
					lineOffset = lineIterator.getCurrentOffset();
				}
			}
			//Catch any exception if someone has changed the document during parsing
			catch (Exception e) 
			{
			}

			Collections.sort(innerContent, new SegmentStringUpperComparator());
			
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable()
			{
				@Override
				public void run()
				{
					contentProvider.setContent(innerContent, innerChildContent);
				}
			});
		}
	}
	
	/**
	 * Divides the editor's document into ten segments and provides elements for
	 * them.
	 */
	protected class ContentProvider implements ITreeContentProvider
	{
		protected IPositionUpdater fPositionUpdater = new DefaultPositionUpdater(OUTLINE_MARKER_ID);
		protected List<Segment> fContent = new ArrayList<SusanContentOutlinePage.Segment>();
		protected HashMap<String, ArrayList<Segment>> fChildContent = new HashMap<String, ArrayList<Segment>>();
		protected SusanTemplateFinderThread susanTemplateFinderThread = null;
		
		protected void parse(IDocument document)
		{
			if(susanTemplateFinderThread != null && susanTemplateFinderThread.isAlive())
			{
				susanTemplateFinderThread.interrupt();
				try {
					susanTemplateFinderThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			susanTemplateFinderThread = new SusanTemplateFinderThread(document, this);
			susanTemplateFinderThread.start();
		}

		public HashMap<String, ArrayList<Segment>> getSegmentMap()
		{
			return fChildContent;
		}
		
		public void setContent(List<Segment> content, HashMap<String, ArrayList<Segment>> childContent)
		{
			fContent = content;
			fChildContent = childContent;
			
			getTreeViewer().refresh();
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
						document.removePositionCategory(OUTLINE_MARKER_ID);
					} catch (BadPositionCategoryException x) {
					}
					document.removePositionUpdater(fPositionUpdater);
				}
			}

			if (newInput != null) {
				IDocument document = fDocumentProvider.getDocument(newInput);
				if (document != null) {
					document.addPositionCategory(OUTLINE_MARKER_ID);
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
			Segment segment = (Segment)element;
			ArrayList<Segment> children = fChildContent.get(segment.name);
			if (children != null && children.size() > 0)
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


	private class SusanLabelProvider extends StyledCellLabelProvider {
	
		/**
		 * a update method to give the <b>bold</b> font to the names of functions in the outline
		 */
		  @Override
		  public void update(ViewerCell cell) {
			String text = "";
			
			List<StyleRange> ranges = new LinkedList<StyleRange>();
			Control control = getTreeViewer().getTree();
			
		    if (cell.getElement() instanceof Segment) 
		    {
		    	Segment segment = (Segment) cell.getElement();
		    	text = segment.name;
		    	cell.setText(text);
		    	
		    	//if not Top Segment in Tree: color type
				if (segment.isChild)
				{
					int spacePos = text.indexOf(" ");
					StyleRange coloredStyledRange = new StyleRange(0, spacePos >= 0 ? spacePos : 0, null, null);
					coloredStyledRange.foreground = SusanEditorColors.KEYWORD_COLOR;	
					ranges.add(coloredStyledRange);
				}
				else
				{
					//otherwise: make the whole word bold
					StyleRange boldStyledRange = new StyleRange(0, text.length(), null, null);
					Font fontBold = getFont(control, SWT.BOLD);
					boldStyledRange.font = fontBold;
					ranges.add(boldStyledRange);
				}
		    }
		    else // if not Segment: return
		    {
		    	super.update(cell);
		    	return;
		    }		    

		    //add StyleRange to Cell
		    
		    cell.setStyleRanges(ranges.toArray(new StyleRange[ranges.size()]));
		    super.update(cell);
		    } 
		  
		  private Font getFont(Control control, int style) {
			    Display display = Display.getCurrent();
			    FontDescriptor fontDescriptor = FontDescriptor.createFrom(control.getFont());
			    return fontDescriptor.setStyle(style).createFont(display);
			}
	}

	public void createControl(Composite parent)
	{
		super.createControl((org.eclipse.swt.widgets.Composite) parent);
		
		//Add font change
		 StyledCellLabelProvider asd = ( new SusanLabelProvider());
		
		TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider(new ContentProvider());
		//viewer.setLabelProvider(new LabelProvider());
		viewer.setLabelProvider(asd);
		viewer.addSelectionChangedListener(this);

		if (fInput != null)
			viewer.setInput(fInput);
	}

	public void selectionChanged(SelectionChangedEvent event)
	{
		super.selectionChanged(event);

		ISelection selection = event.getSelection();
		if (selection.isEmpty())
			fTextEditor.resetHighlightRange();
		else {
			Segment segment = (Segment) ((IStructuredSelection) selection).getFirstElement();
			int start = segment.position.getOffset();
			int length = segment.name.length();
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
	}
	
	public HashMap<String, ArrayList<Segment>> getMap()
	{		
		return ((ContentProvider) getTreeViewer().getContentProvider()).getSegmentMap();
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
				
				//viewer.setSelection(getSelection());
				//viewer.expandAll();
				//add font, ect.
				//viewer.setl
				
				control.setRedraw(true);
			}
		}
	}
}