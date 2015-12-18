/**
 * This Class is for the Susan-Editor-Extension;
 * standart implemention of the TextEditor Class
 * contains SusanEditor(), getAdapter(), dispose()
 * 
 */
package org.modelica.mdt.ui.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.modelica.mdt.ui.editor.SusanContentOutlinePage.Segment;
import org.modelica.mdt.ui.state.State;


public class SusanEditor extends TextEditor
{
	private SusanContentOutlinePage fOutlinePage;
	protected AnnotationModel annotationModel;
	private SusanEditorSelectionChangedListener selectionChangedListener;
	private MarkOccurencesHelper markOccurencesHelper;
	
	public final String SUSANMARKERTYPE = "org.mdt.susanMarker";
	
	/** 
	 * Constructor
	 * Creates a new SusanEditor and is setting the DocumentProvider
	 */
	public SusanEditor()
	{
		super();
		markOccurencesHelper = new MarkOccurencesHelper();
		setSourceViewerConfiguration(new SusanSourceViewerConfig());
		setDocumentProvider(new SusanDocumentProvider());
	}
	
	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
	}
	
	public SusanEditor getEditor()
	{
		return this;
	}
	
	//-----start SelectionChangedListener
	/**
	 * a listener that gets called when the selection in the editor is changed.
	 * is used to create bookmarks for occurences of marked words 
	 * and also to provide the cursor position/marked word for hotkeys to jump to declarations or
	 * tab/untab and comment/uncomment
	 */
	private class SusanEditorSelectionChangedListener extends AbstractSelectionChangedListener {
	
		private String lastindex = "";
		private String word = "";
		private Pattern wordMatchingPattern = Pattern.compile("[A-Za-z0-9\\_]");
		
		@Override
		public void selectionChanged(SelectionChangedEvent event)
		{
			ISelection selection = event.getSelection();
			Object source = event.getSource();
			
			if (State.getInstance().markOccurrencesOn()) 
			{
								
				if (selection instanceof TextSelection && source instanceof SourceViewer )
				{
					TextSelection textSelection = (TextSelection)selection;
					SourceViewer sV = (SourceViewer)source;
					IDocument document = sV.getDocument();
					removeAnnotations(getAnnotationModel());
					
					//IDocument document = getDocument()
					if (document != null && textSelection.getText().length() > 2) {
						String text = textSelection.getText();
						if ( ( text.length() <= 1 ) || (lastindex.equals(text) ))
						{
							//find word where the cursorPosition is in
							int pos = textSelection.getOffset();
							int line = textSelection.getStartLine();
							try
							{
								String lineString = document.get(document.getLineOffset(line), document.getLineLength(line));
								
								pos = pos - document.getLineOffset(line);//somewhere in between the word
								
								int startPos = pos - 1;
								int endPos = pos;
								while((startPos > 0) && (wordMatchingPattern.matcher("" + lineString.charAt(startPos)).matches()))
									startPos--;
	
								while((endPos < lineString.length()-1) && (wordMatchingPattern.matcher("" + lineString.charAt(endPos)).matches()))
									endPos++;
								
								word = lineString.substring(startPos + 1, endPos);
							} 
							catch (BadLocationException e)
							{
								
								e.printStackTrace();
								word = "";
							}
							return;
						}
						
						// no marking of keywords, there are too much and there is no use in jumping to them
						for (int i = 0; i < SusanRuleBasedScanner.SUSAN_KEYWORDS.length; i++)
						{
							if ( text.equals(SusanRuleBasedScanner.SUSAN_KEYWORDS[i]) )
								return;
						}
	
						for (int i = 0; i < SusanRuleBasedScanner.TEMPLATE_KEYWORDS.length; i++)
						{
							if ( text.equals(SusanRuleBasedScanner.TEMPLATE_KEYWORDS[i]) )
								return;
						}
						
						for (int i = 0; i < SusanRuleBasedScanner.CASE_KEYWORDS.length; i++)
						{
							if ( text.equals(SusanRuleBasedScanner.CASE_KEYWORDS[i]) )
								return;
						}
						
						for (int i = 0; i < SusanRuleBasedScanner.VAR_KEYWORDS.length; i++)
						{
							if ( text.equals(SusanRuleBasedScanner.VAR_KEYWORDS[i]) )
								return;
						}
						
						if ( text.equals("<%") || text.equals("%>") || text.equals("<<") || text.equals(">>") )
							return;
						
						boolean b = false;
						int i = 0;
						while ((!b) && (i < (text.length()-1) ) )
						{
							if ( !( (text.charAt(i) == ' ' ) || ((text.charAt(i) == '	' )) ) )
							{
								b = true;
							}
							i++;
							
						}
						if (!b)
							return;
						
						try
						{
							markwords(text);
							word = text;
						} catch (CoreException e)
						{
							e.printStackTrace();
						} catch (BadLocationException e)
						{
							e.printStackTrace();
						}
						
					}
				}
			}
			else
			{
				markOccurencesHelper.clearMarkers();
				if (selection instanceof TextSelection && source instanceof SourceViewer )
				{
					word = ((TextSelection) selection).getText();
				}
			}
		}
		
		public String getMarkedWord()
		{
			return word;
		}
		
		public void setOutlinePage(SusanContentOutlinePage page)
		{
			fOutlinePage = page;
		}
		
	}
	//-----end SelectionChangedListener
	
	/**
	 * a thread for adding bookmarks to the text for a sequence of characters <br>
	 * extends Thread to reduce the slowing down of the editor if there are a lot of occurrences
	 */
	private class MarkOccurencesHelper
	{
		private int wordLength;
		private Timer timer;
		private ArrayList<Integer> positions;
		
		public MarkOccurencesHelper()
		{
			super();
			timer = new Timer();
			positions = new ArrayList<Integer>();
			wordLength = 0;
		}
		
		public void setMarkers(ArrayList<Integer> positions, int wordLength)
		{
			this.positions = positions;
			this.wordLength = wordLength;
			timer.cancel();
			timer = new Timer();
			timer.schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					refreshMarkers();
				}
			}, 500);
		}
		
		public void clearMarkers()
		{
			clearMarkers(extractResource(getEditor()));
		}
		
		protected void clearMarkers(final IResource resource)
		{
			if(resource == null)
				return;
			
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable()
				{
					@Override
					public void run()
					{
						try {
							resource.deleteMarkers(SUSANMARKERTYPE, false, 1);
						} catch (CoreException e1) {
							e1.printStackTrace();
						}
					}
				});
		}
		
		private void refreshMarkers()
		{			
			final IResource resource = extractResource(getEditor());
			final IDocument document = getDocumentProvider().getDocument(getEditorInput());
			
			if(resource == null)
				return;
			
			clearMarkers(resource);
			
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable()
			{
				@Override
				public void run()
				{
					for(Integer posIdx : positions)
					{
						if(posIdx < 0)
							continue;
				
						try
						{
							IMarker marker =  resource.createMarker(SUSANMARKERTYPE);
			
							marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
							marker.setAttribute(IMarker.LOCATION, "1");
							marker.setAttribute(IMarker.LINE_NUMBER, document.getLineOfOffset(posIdx)+1) ;
							marker.setAttribute(IMarker.DONE, true);
						
							if (posIdx != 0) {
								marker.setAttribute(IMarker.CHAR_START,posIdx);
								marker.setAttribute(IMarker.CHAR_END,posIdx + wordLength);
							}
						
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			});
		}
		
		private IResource extractResource(IEditorPart editor) {
			IEditorInput input = editor.getEditorInput();
			if ((input instanceof IFileEditorInput))      
				return ((IFileEditorInput)input).getFile();
			
	     
			return null;
		}
	}
	
	/**
	 * creates the selectionChangedListener for the editor
	 */
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		
		selectionChangedListener= new SusanEditorSelectionChangedListener();
		selectionChangedListener.setOutlinePage(fOutlinePage);
		ISelectionProvider selProv = getSelectionProvider();
		selProv.addSelectionChangedListener(selectionChangedListener);
	}
	

	/**
	 * get the annotationmodel from the editor input
	 * @return IAnnotationModel
	 */
	private IAnnotationModel getAnnotationModel() {
		IEditorInput editorInput = this.getEditorInput();
		IAnnotationModel model = getDocumentProvider().getAnnotationModel(editorInput);

		return model;
	}
	
	/**
	 * remove all annotations of the given Model from the editor <br>
	 * @param model
	 */
	private void removeAnnotations(IAnnotationModel model) {
		if (model == null)
			return;
		
		for (@SuppressWarnings("unchecked")
		Iterator<Annotation> it = model.getAnnotationIterator(); it.hasNext();) {
			{
				Annotation annotation = it.next();
				if(annotation.getType().equals(SUSANMARKERTYPE))
					model.removeAnnotation(annotation);
			}
			
		}
	}
	
	/**
	 * creates a thread that markes the occurrencies of the given parameter <br>
	 * 
	 * @param word String
	 * @throws CoreException
	 * @throws BadLocationException
	 */
	public void markwords(String word) throws CoreException, BadLocationException
	{
		ArrayList<Integer> positions = getWordPositionList(word);
		markOccurencesHelper.setMarkers(positions, word.length());
	}
	
	@Override
	protected void editorSaved() {
		super.editorSaved();
		
		//update outline if the editor is saved
		if (fOutlinePage != null)
			fOutlinePage.update();

	}
	
	//TODO
	//change so that the save makr is correct
	public boolean isDirty() {
		
		return super.isDirty();
	}
	
	
	/**
	 * find and jump to marked template
	 */
	public void findTemplateDeclaration() 
	{
		String selectedWord = selectionChangedListener.getMarkedWord();
		HashMap<String, ArrayList<Segment>> map = fOutlinePage.getMap();
		
		if(!map.containsKey(selectedWord))
			return;
		
		ArrayList<Segment> segList = map.get(selectedWord);
		if (segList.size() == 0)
			return;
		
		Segment s = segList.get(0);
		
		int offset = 0;
		try
		{
			offset = getDocumentProvider().getDocument(getEditorInput()).get(s.position.offset, getDocumentProvider().getDocument(getEditorInput()).getLineLength(getDocumentProvider().getDocument(getEditorInput()).getLineOfOffset(s.position.offset))).lastIndexOf(selectedWord);
		} catch (BadLocationException e)
		{
			e.printStackTrace();
			return;
		}
		
		TextSelection sel2 = new TextSelection(s.position.offset+offset, selectedWord.length());
		getSelectionProvider().setSelection(sel2);
	}
	
	/**
	 *  gets called if the Hotkey to untab a section in the text that is marked atm is used
	 * @throws BadLocationException
	 */
	public void unTabSection() throws BadLocationException
	{
		TextSelection sel = (TextSelection) getSelectionProvider().getSelection();
		

		
		org.eclipse.jface.text.IDocument doc = this.getDocumentProvider().getDocument(getEditorInput());
		int start =  sel.getStartLine();
		int stop = sel.getEndLine();
		if ( (doc == null) || (start > stop) || (start > doc.get().split("\n").length-1) )
			return;
		
		int startPos = doc.getLineOffset(sel.getStartLine());
		int endPos = doc.getLineOffset(sel.getEndLine()+1)-doc.getLineOffset(sel.getStartLine());
		String[] lines = doc.get(startPos , endPos  ).split("\n");
		String s = "";
		
		for (int i = 0; i <= stop-start; i++)
		{
			if (lines[i].startsWith("  "))
			{	
				lines[i] = lines[i].replaceFirst("  ", "");
			}
			s += lines[i];
			s+= "\n";
		}
		
		doc.replace(startPos, endPos, s);
		TextSelection sel2 = new TextSelection(startPos, s.length());
		getSelectionProvider().setSelection(sel2);
	}
	/**
	 *  gets called if the Hotkey to tab a section in the text that is marked atm is used
	 * @throws BadLocationException
	 */
	public void tabSection() throws BadLocationException
	{
		TextSelection sel = (TextSelection) getSelectionProvider().getSelection();
		org.eclipse.jface.text.IDocument doc = this.getDocumentProvider().getDocument(getEditorInput());
		int startPos = doc.getLineOffset(sel.getStartLine());
		int endPos = doc.getLineOffset(sel.getEndLine()+1)-doc.getLineOffset(sel.getStartLine());
		int start =  sel.getStartLine();
		int stop = sel.getEndLine();
		
		if ( (doc == null) || (start > stop) || (start > doc.get().split("\n").length-1) )
			return;		
		
		String[] lines = doc.get(startPos , endPos  ).split("\n");
		
		String s = "";
		for (int i = 0; i <= stop-start; i++)
		{
			lines[i] = "  "+lines[i];
			s += lines[i];
				s+= "\n";		
		}

		doc.replace(startPos, endPos, s);	
		TextSelection sel2 = new TextSelection(startPos, s.length());
		getSelectionProvider().setSelection(sel2);
	}
	
	/**
	 * comments marked lines or line where the coursor is atm
	 * @throws BadLocationException
	 */
	public void commentSection() throws BadLocationException
	{
		TextSelection sel = (TextSelection) getSelectionProvider().getSelection();
		org.eclipse.jface.text.IDocument doc = this.getDocumentProvider().getDocument(getEditorInput());
		int start =  sel.getStartLine();
		int stop = sel.getEndLine();
		if ( (doc == null) || (start > stop) || (start > doc.get().split("\n").length-1) )
			return;
		
		int startPos = doc.getLineOffset(sel.getStartLine());
		int endPos = doc.getLineOffset(sel.getEndLine()+1)-doc.getLineOffset(sel.getStartLine());		
		String[] lines = doc.get(startPos , endPos  ).split("\n");
		String s = "";
		
		for (int i = 0; i <= stop-start; i++)
		{
			lines[i] = "//"+lines[i];
			s += lines[i];
				s+= "\n";		
		}

		doc.replace(startPos, endPos, s);
		TextSelection sel2 = new TextSelection(startPos, s.length());
		getSelectionProvider().setSelection(sel2);
	}
	
	/**
	 * uncomments marked section or line where the cursor is atm
	 * @throws BadLocationException
	 */
	public void unCommentSection() throws BadLocationException
	{
		TextSelection sel = (TextSelection) getSelectionProvider().getSelection();
		org.eclipse.jface.text.IDocument doc = this.getDocumentProvider().getDocument(getEditorInput());
		int start =  sel.getStartLine();
		int stop = sel.getEndLine();
		if ( (doc == null) || (start > stop) || (start > doc.get().split("\n").length-1) )
			return;
		
		int startPos = doc.getLineOffset(sel.getStartLine());
		int endPos = doc.getLineOffset(sel.getEndLine()+1)-doc.getLineOffset(sel.getStartLine());		
		String[] lines = doc.get(startPos , endPos  ).split("\n");		
		String s = "";
		
		for (int i = 0; i <= stop-start; i++)
		{
			int j = 0;
			while ( ( lines[i].charAt(j) == ' ') || (lines[i].charAt(j) == '	') )
				if (lines[i].length() > j) j++;
				else
					break;
			if (lines[i].length() >= j+1)
			{
				if ( (lines[i].charAt(j) == '/' ) && (lines[i].charAt(j+1) == '/' ) )
				{	
					lines[i] = lines[i].replaceFirst("//", "");
				}
			}
			s += lines[i];
				s+= "\n";
		}
		
		doc.replace(startPos, endPos, s);
		TextSelection sel2 = new TextSelection(startPos, s.length());
		getSelectionProvider().setSelection(sel2);
	}
	
	@Override
	protected void updatePropertyDependentActions() {
		super.updatePropertyDependentActions();
	}
	
	/**
	 * returns all the positions of s in the document
	 * @param s (String)
	 * @return List of positions (int)
	 */
	public ArrayList<Integer> getWordPositionList(String s)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		org.eclipse.jface.text.IDocument doc = this.getDocumentProvider().getDocument(getEditorInput());
		String text = doc.get();
		int index = text.indexOf(s);
		list.add(index);
		while (index >= 0) {
		    //System.out.println(index);
		    index = text.indexOf(s, index + 1);
		    list.add(index);
		}
		
		return list;	
	}
	
	/**
	 * checks all files, if the functions are used or not
	 * @return
	 */
	public String getDependenciesOfFiles()
	{
		String activeProjectName = "";
		IProject activeProject = null;
		
		//get Name of active Project
		IEditorPart  editorPart = getSite().getWorkbenchWindow().getActivePage().getActiveEditor();
				if(editorPart  != null)
				{
				    IFileEditorInput input = (IFileEditorInput)editorPart.getEditorInput() ;
				    IFile file = input.getFile();
				    activeProject = file.getProject();
				    activeProjectName = activeProject.getName();  
				    IResource res[] = {};
					try
					{
						res = activeProject.members();
					} catch (CoreException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//got all Files from the project, iterate through them
				    for (int i = 0; i < res.length; i++)
				    	activeProject.getFolder(activeProject.getFullPath()).getFile(res[i].getName());
				    return activeProjectName;
				}
				else return null;
	}
	

	@Override
	public void dispose()
	{
		super.dispose();
	}

	/** 
	 * Creates the Outline for the SusanEditor
	 * <br><br>
	 * input:  <code>IContentOutlinePage</code><br>
	 * return: <code>SusanContentOutlinePage</code>
	 */
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class required)
	{

		if (IContentOutlinePage.class.equals(required)) {
			if (fOutlinePage == null) {
				fOutlinePage = new SusanContentOutlinePage(getDocumentProvider(), this);
				if (getEditorInput() != null)
					fOutlinePage.setInput(getEditorInput());
				
			}
			selectionChangedListener.setOutlinePage(fOutlinePage);
			return fOutlinePage;
		}

		return super.getAdapter(required);
	}

	
	public class ErrorAnnotation extends Annotation {
	    private final IMarker   marker;
	    private String          text;
	    private int             line;
	    private Position        position;
	    private static final String ERROR_TYPE = "error";

	    public ErrorAnnotation(final IMarker marker) {
	        this.marker = marker;
	    }

	    public ErrorAnnotation(final int line, final String text) {
	        super(ERROR_TYPE, true, null);
	        this.marker = null;
	        this.line = line;
	        this.text = text;
	    }

	    public IMarker getMarker() {
	        return this.marker;
	    }

	    public int getLine() {
	        return this.line;
	    }

	    @Override
	    public String getText() {
	        return this.text;
	    }

//	    public Image getImage() {
//	        return ERROR_IMAGE;
//	    }

	    public int getLayer() {
	        return 3;
	    }

	    @Override
	    public String getType() {
	        return ERROR_TYPE;
	    }

	    public Position getPosition() {
	        return this.position;
	    }

	    public void setPosition(final Position position) {
	        this.position = position;
	    }
	}
}
