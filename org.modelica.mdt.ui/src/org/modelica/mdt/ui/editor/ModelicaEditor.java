/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Link�pings universitet, Department of
 * Computer and Information Science, PELAB
 *
 * All rights reserved.
 *
 * (The new BSD license, see also
 * http://www.opensource.org/licenses/bsd-license.php)
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * * Neither the name of Link�pings universitet nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.modelica.mdt.ui.editor;

import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.AbstractInformationControlManager;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension4;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.ITextViewerExtension5;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.IInformationProviderExtension;
import org.eclipse.jface.text.information.IInformationProviderExtension2;
import org.eclipse.jface.text.information.InformationPresenter;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.modelica.mdt.ui.actions.IModelicaEditorActionDefinitionIds;
import org.modelica.mdt.ui.actions.OpenAction;
import org.modelica.mdt.ui.hover.HTMLTextPresenter;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.ResourceAction;
import org.eclipse.ui.texteditor.TextEditorAction;
import org.eclipse.ui.texteditor.TextOperationAction;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.modelica.mdt.ui.text.ModelicaDocumentProvider;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.Viewer;

import org.modelica.mdt.ui.UIPlugin;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChangeListener;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IParent;
import org.modelica.mdt.core.ISourceRegion;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.CorePlugin;
import org.modelica.mdt.internal.core.DefinitionSourceRegion;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.ui.text.IModelicaPartitions;
import org.modelica.mdt.ui.text.ModelicaCodeResolver;
import org.modelica.mdt.ui.text.ModelicaPairMatcher;

/**
 * Creates an editor for modelica source code.
 * 
 * The editor is configured with following features:
 * 
 *  - modelica syntax highlighting
 *  - content assiastant
 *  - info-pops
 *  - action that invokes content assist on ctrl+space
 *  - folding(collapsing) of blocks of source code
 *  - outline syncronization
 *  - Modelica Project Viewer syncronization
 * 
 * @author Adrian Pop
 * @author MDT team
 */
public class ModelicaEditor extends TextEditor implements IModelicaElementChangeListener, IPropertyListener
{
	private static final String RESOURCE_BUNDLE = 
		"org.modelica.mdt.ui.editor.ContentAssist";

    private ProjectionSupport projectionSupport;
    private ISourceViewer viewer;
	protected final static String EDITOR_MATCHING_BRACKETS="matchingBrackets";
	protected final static String EDITOR_MATCHING_BRACKETS_COLOR=  "matchingBracketsColor";
	protected final static char[] BRACKETS= { '{', '}', '(', ')', '[', ']', '<', '>' };
	protected ModelicaPairMatcher fBracketMatcher= new ModelicaPairMatcher(BRACKETS);
	private ModelicaDocumentProvider fDocumentProvider = null;
	
	/** The outline page */
	protected ModelicaContentOutlinePage fOutlinePage;
	/** Outliner context menu Id */
	protected String fOutlinerContextMenuId;
	/** The information presenter. */
	private InformationPresenter fInformationPresenter;
		
	public ModelicaEditor()
	{
		super();
		setOutlinerContextMenuId("#ModelicaOutlinerContext"); //$NON-NLS-1$
		addPropertyListener(this);
	}
	

	/* deals with opening the second file in the editor and notifying the outline page*/
	public void propertyChanged(Object obj, int prop)
	{
		if (prop == IEditorPart.PROP_INPUT)
		{
			if (obj == this && fOutlinePage != null)
			{
				setOutlinePageInput(fOutlinePage, getEditorInput());
			}
		}
	}
	
	
	/**
	 * The editor selection changed listener.
	 *
	 * @since 0.6.8
	 */
	private EditorSelectionChangedListener fEditorSelectionChangedListener;
	/** The selection changed listener */
	protected AbstractSelectionChangedListener fOutlineSelectionChangedListener= new OutlineSelectionChangedListener();
	
	
	protected void initializeEditor() 
	{
		super.initializeEditor();
		if (getPreferenceStore() != null) /* TODO!! fix this later */
			getPreferenceStore().setValue(EDITOR_MATCHING_BRACKETS, true);
		// setDocumentProvider(getDocumentProvider());
		setSourceViewerConfiguration(
				new ModelicaSourceViewerConfig(
						this, 
						IModelicaPartitions.MODELICA_PARTITIONING));
	}	
	
	public IDocumentProvider getDocumentProvider()
	{
		if (fDocumentProvider == null)
			fDocumentProvider = new ModelicaDocumentProvider();
		return fDocumentProvider;
	}
	
	protected void createActions() 
	{
		super.createActions();

		this.setAction("OpenAction", new OpenAction(this));
		
		ResourceAction resAction= new TextOperationAction(ModelicaEditorMessages.getBundleForConstructedKeys(), "ShowModelicaDoc.", this, ISourceViewer.INFORMATION, true); //$NON-NLS-1$
		resAction= new InformationDispatchAction(ModelicaEditorMessages.getBundleForConstructedKeys(), "ShowModelicaDoc.", (TextOperationAction) resAction); //$NON-NLS-1$
		resAction.setActionDefinitionId(IModelicaEditorActionDefinitionIds.SHOW_MODELICADOC);
		setAction("ShowModelicaDoc", resAction); //$NON-NLS-1$
		
		/*
		 * create the action that activates the content assist on CTRL+SPACE
		 */
		IAction a = new TextOperationAction(
				ResourceBundle.getBundle(RESOURCE_BUNDLE), "ContentAssistProposal.", this, ISourceViewer.CONTENTASSIST_PROPOSALS);
		a.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		setAction("ContentAssistProposal", a);
		
	}

	@Override
	public void createPartControl(Composite parent)
	{
        super.createPartControl(parent);
        
		IInformationControlCreator informationControlCreator= new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell shell) {
				boolean cutDown= false;
				int style= cutDown ? SWT.NONE : (SWT.V_SCROLL | SWT.H_SCROLL);
				return new DefaultInformationControl(shell, SWT.RESIZE | SWT.TOOL, style, new HTMLTextPresenter(cutDown), "BLA MOTHER!");
			}
		};

		fInformationPresenter= new InformationPresenter(informationControlCreator);
		fInformationPresenter.setSizeConstraints(60, 10, true, true);
		fInformationPresenter.install(getSourceViewer());
		fInformationPresenter.setDocumentPartitioning(IModelicaPartitions.MODELICA_PARTITIONING);
        
        /*
         * install the gadgets that enable text folding
         */
        ProjectionViewer viewer = (ProjectionViewer)getSourceViewer();        
        projectionSupport = 
        	new ProjectionSupport(viewer, getAnnotationAccess(),
        			getSharedColors());
		projectionSupport.install();
		
		/* adrpo: add an auto edit strategy */
		ITextViewerExtension2 extension = (ITextViewerExtension2)viewer;
		if (extension != null)
		{
	 		extension.prependAutoEditStrategy(
	 				new ModelicaAutoIndentStrategy(), 
	 				IDocument.DEFAULT_CONTENT_TYPE);
		}
				
		/* turn projection mode on */
		viewer.doOperation(ProjectionViewer.TOGGLE);
		
		IModelicaSourceFile file = getSourceFile();				
//		if (file != null)
//		{
//			/* don't do folding unless we are invoked on a modelica element */
//			updateAnnotations(file);
//			ModelicaCore.getModelicaRoot().
//				addModelicaElementChangeListener(this);
//		}
		
		fEditorSelectionChangedListener= new EditorSelectionChangedListener();
		fEditorSelectionChangedListener.install(getSelectionProvider());
		
	}

	/**
	 * Find the modelica source file this editor is opened on.
	 * 
	 * @return the source file this editor is opened on or null
	 * 	if the editor is not opened on modelica source file
	 */
	private IModelicaSourceFile getSourceFile()
	{
		IEditorInput input = getEditorInput();

		if (!(input instanceof ModelicaElementEditorInput))
		{
			return null;
		}
		
		return ((ModelicaElementEditorInput) input).getSourceFile();
	}
	
	@Override
	protected ISourceViewer createSourceViewer(Composite parent, 
			IVerticalRuler ruler, int styles)
	{
		/*
		 * setup the viewer that is capable of text folding
		 */
		viewer = 
        		new ProjectionViewer(parent, ruler, getOverviewRuler(), 
        				isOverviewRulerVisible(), styles);
    	/* ensure decoration support has been created and configured */
		/*
		SourceViewerDecorationSupport s = getSourceViewerDecorationSupport(viewer);
		System.out.println("CharPairMatcher set in create viewer");
    	s.setCharacterPairMatcher(fBracketMatcher);
    	*/
		getSourceViewerDecorationSupport(viewer);
    	return viewer;
	}
	
	public ISourceViewer getViewer()
	{
		return viewer;
	}
	
	public void dispose()
	{
		super.dispose();
		ModelicaCore.getModelicaRoot().removeModelicaElementChangeListener(this);
		if (fEditorSelectionChangedListener != null)  {
			fEditorSelectionChangedListener.uninstall(getSelectionProvider());
			fEditorSelectionChangedListener= null;
		}
		fInformationPresenter.uninstall();
	}

	public void elementsChanged(Collection<IModelicaElementChange> changes)
	{
		IModelicaSourceFile file = getSourceFile();
		
		/*
		 * check among change list if the file we are open
		 * on have been changed, and in that case
		 * update folding annotations
		 */
//		for (IModelicaElementChange change : changes)
//		{
//			IModelicaElement elm = change.getElement();
//			
//			if (elm == file)
//			{
//				updateAnnotations(file);
//				break;
//			}
//		}
	}

	/**
	 * updates the folding annotations in this editr based
	 * on the contents of the provided modelica source file.
	 * old annotations are removed.
	 * 
	 * @param file the source file to create annotations from
	 */
	private void updateAnnotations(IModelicaSourceFile file)
	{
		try
		{
			/*
			 * remove old annotations and call helper function
			 * to create new annotations
			 */
			ProjectionAnnotationModel annotationModel = 
				((ProjectionViewer)getSourceViewer()).getProjectionAnnotationModel();
			annotationModel.removeAllAnnotations();
			
			int docLength = getSourceViewer().getDocument().getLength();
			updateAnnotationsIter(file, docLength, annotationModel);
		} 
		catch (CompilerException e)
		{
			ErrorManager.showCompilerError(e);
		}
		catch (CoreException e)
		{
			ErrorManager.showCoreError(e);
		}
	}

	/**
	 * this is helper function for updateAnnotationsIter() that
	 * recursivly creates folding annotations based on class
	 * definition regions
	 */
	private void updateAnnotationsIter(IParent parentElement,
			int docLength,
			IAnnotationModel annotationModel) 
		throws ConnectException, UnexpectedReplyException, InvocationError, 
			CompilerInstantiationException, CoreException
	{
		/*
		 * go over the children of provided element,
		 * add folding annotation for each subclass found
		 * and invoke annotations creations based on that class
		 */
		IRegion reg;
		for (IModelicaElement elm : parentElement.getChildren())
		{
			reg = getRegionFromSourceRegion(elm.getLocation().getSourceRegion(), getViewer().getDocument());
			/* make only class definitions collapsable */
			if (elm instanceof IModelicaClass) 
			{
				int offset = reg.getOffset();
				int length = reg.getLength();

				if (offset + length < docLength)
				{
					length += 1;
				}
				annotationModel.addAnnotation(new ProjectionAnnotation(false),
						new Position(offset, length));

				updateAnnotationsIter((IParent)elm, docLength, annotationModel);
			}
		}		
	}
	
		
	protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) 
	{	
		//System.out.println("CharPairMatcher set in configureSourceViewerDecorationSupport");
		support.setCharacterPairMatcher(fBracketMatcher);
		support.setMatchingCharacterPainterPreferenceKeys(
				EDITOR_MATCHING_BRACKETS, 
				EDITOR_MATCHING_BRACKETS_COLOR);
		support.setSymbolicFontName(getFontPropertyPreferenceKey());
		
		super.configureSourceViewerDecorationSupport(support);		
	}

	@Override
	public Object getAdapter(Class adapter) 
	{
		try
		{
			if (IContentOutlinePage.class.equals(adapter)) {
				if (fOutlinePage == null)
					fOutlinePage= createOutlinePage();
				return fOutlinePage;
			}
		}
		catch(NoClassDefFoundError e)
		{
			ErrorManager.logError(e);
		}
		
		return super.getAdapter(adapter);
	}
	
	/**
	 * Creates the outline page used with this editor.
	 *
	 * @return the created Modelica outline page
	 */
	protected ModelicaContentOutlinePage createOutlinePage() {
		ModelicaContentOutlinePage page= 
			new ModelicaContentOutlinePage(fOutlinerContextMenuId, this);
		fOutlineSelectionChangedListener.install(page);
		setOutlinePageInput(page, getEditorInput());
		return page;
	}
	
	/**
	 * Sets the outliner's context menu ID.
	 *
	 * @param menuId the menu ID
	 */
	protected void setOutlinerContextMenuId(String menuId) {
		fOutlinerContextMenuId= menuId;
	}	

	/**
	 * Informs the editor that its outliner has been closed.
	 */
	public void outlinePageClosed() {
		if (fOutlinePage != null) {
			fOutlineSelectionChangedListener.uninstall(fOutlinePage);
			fOutlinePage= null;
			resetHighlightRange();
		}
	}

	/**
	 * Synchronizes the outliner selection with the given element
	 * position in the editor.
	 *
	 * @param element the Modelica element to select
	 */
	protected void synchronizeOutlinePage(IModelicaElement element) {
		synchronizeOutlinePage(element, true);
	}

	/**
	 * Synchronizes the outliner selection with the given element
	 * position in the editor.
	 *
	 * @param element the Modelica element to select
	 * @param checkIfOutlinePageActive <code>true</code> if check for active outline page needs to be done
	 */
	protected void synchronizeOutlinePage(IModelicaElement element, boolean checkIfOutlinePageActive) {
		if (fOutlinePage != null && element != null && 
			!(checkIfOutlinePageActive && isModelicaContentOutlinePageActive())) {
			fOutlineSelectionChangedListener.uninstall(fOutlinePage);
			fOutlinePage.select(element);
			fOutlineSelectionChangedListener.install(fOutlinePage);
		}
	}

	/**
	 * Synchronizes the outliner selection with the actual cursor
	 * position in the editor.
	 */
	public void synchronizeOutlinePageSelection() {
		synchronizeOutlinePage(computeHighlightRangeSourceReference());
	}
	
	/**
	 * Updates the Modelica outline page selection and this editor's range indicator.
	 *
	 * @since 0.6.8
	 */
	private class EditorSelectionChangedListener extends AbstractSelectionChangedListener {

		/*
		 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
		 */
		public void selectionChanged(SelectionChangedEvent event) {
			// XXX: see https://bugs.eclipse.org/bugs/show_bug.cgi?id=56161
			ModelicaEditor.this.selectionChanged();
		}
	}

	protected void selectionChanged() {
		if (getSelectionProvider() == null)
			return;
		IModelicaElement element= computeHighlightRangeSourceReference();
		if (CorePlugin.getDefault().getPreferenceStore().getBoolean("ModelicaEditor.SyncOutlineOnCursorMove"))
			synchronizeOutlinePage(element);
		setSelection(element, false);
	}

	
	
	/**
	 * Updates the selection in the editor's widget with the selection of the outline page.
	 */
	class OutlineSelectionChangedListener  extends AbstractSelectionChangedListener {
		public void selectionChanged(SelectionChangedEvent event) {
			doSelectionChanged(event);
		}
	}
	
	private IWorkbenchPage getActivePage() {
		IWorkbenchWindow window= UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();
		if (window == null)
			return null;
		return window.getActivePage();
	}	
	
	protected void doSelectionChanged(SelectionChangedEvent event) {

		IModelicaElement reference= null;

		ISelection selection= event.getSelection();
		Iterator iter= ((IStructuredSelection) selection).iterator();
		while (iter.hasNext()) {
			Object o= iter.next();
			if (o instanceof IModelicaElement) {
				reference= (IModelicaElement) o;
				break;
			}
		}
		if (!isActivePart() && getActivePage() != null)
			getActivePage().bringToTop(this);

		setSelection(reference, !isActivePart());
		
		ISelectionProvider selectionProvider= getSelectionProvider();
		if (selectionProvider == null )
			return;
		
		ISelection textSelection= selectionProvider.getSelection();
		if (!(textSelection instanceof ITextSelection))
			return;
				
	}

	protected boolean isActivePart() {
		IWorkbenchPart part= getActivePart();
		return part != null && part.equals(this);
	}
	private boolean isModelicaContentOutlinePageActive() {
		IWorkbenchPart part= getActivePart();
		return part instanceof ContentOutline && ((ContentOutline)part).getCurrentPage() == fOutlinePage;
	}

	private IWorkbenchPart getActivePart() {
		IWorkbenchWindow window= getSite().getWorkbenchWindow();
		IPartService service= window.getPartService();
		IWorkbenchPart part= service.getActivePart();
		return part;
	}
	
	/**
	 * Sets the input of the editor's outline page.
	 *
	 * @param page the Modelica outline page
	 * @param input the editor input
	 */
	protected void setOutlinePageInput(ModelicaContentOutlinePage page, IEditorInput input) {
		if (page == null)
			return;
		
		IModelicaElement me= getEditorInputModelicaElement();
		if (me != null)
			page.setInput(me);
		else
			page.setInput(null);
		
	}
	
	/**
	 * Returns the Modelica element wrapped by this editors input.
	 *
	 * @return the Modelica element wrapped by this editors input.
	 * @since 0.6.8
	 */	
	public IModelicaElement getEditorInputModelicaElement()
	{
		IEditorInput i = getEditorInput();
		if (i != null && i instanceof ModelicaElementEditorInput) 
		{
			IModelicaElement me = ((ModelicaElementEditorInput)i).getSourceFile();
			if (me != null) 
				return (IModelicaElement)me;
		}
		return EditorUtility.getEditorInputModelicaElement(this);
	}
	
	/**
	 * Computes and returns the source reference that includes the caret and
	 * serves as provider for the outline page selection and the editor range
	 * indication.
	 *
	 * @return the computed source reference
	 * @since 0.6.8
	 */
	protected IModelicaElement computeHighlightRangeSourceReference() {
		ISourceViewer sourceViewer= getSourceViewer();
		if (sourceViewer == null)
			return null;

		StyledText styledText= sourceViewer.getTextWidget();
		if (styledText == null)
			return null;

		int caret= 0;
		if (sourceViewer instanceof ITextViewerExtension5) {
			ITextViewerExtension5 extension= (ITextViewerExtension5)sourceViewer;
			caret= extension.widgetOffset2ModelOffset(styledText.getCaretOffset());
		} else {
			int offset= sourceViewer.getVisibleRegion().getOffset();
			caret= offset + styledText.getCaretOffset();
		}

		IModelicaElement element= getElementAt(caret, false);
		if (element == null) return null;
		return element;
	}

	/**
	 * Returns the most narrow java element including the given offset.
	 *
	 * @param offset the offset inside of the requested element
	 * @param reconcile <code>true</code> if editor input should be reconciled in advance
	 * @return the most narrow Modelica element
	 * @since 0.6.8
	 */
	protected IModelicaElement getElementAt(int offset, boolean reconcile) 
	{
		ISourceRegion sourceRegion = null;
		try
		{
			sourceRegion = 
				new DefinitionSourceRegion(
						getViewer().getDocument().getLineOfOffset(offset),
						0,
						getViewer().getDocument().getLineOfOffset(offset),
						1);
						
		}
		catch(BadLocationException e)
		{
			ErrorManager.logError(e);
		}
		return ModelicaCodeResolver.getClassAt(this, sourceRegion);
	}
	
	/**
	 * Returns the most narrow java element including the given offset.
	 *
	 * @param offset the offset inside of the requested element
	 * @return the most narrow java element
	 */
	public IModelicaElement getElementAt(int offset) 
	{
		return getElementAt(offset, true);
	}

	protected void setSelection(IModelicaElement reference, boolean moveCursor) {
		if (getSelectionProvider() == null)
			return;

		ISelection selection= getSelectionProvider().getSelection();
		if (selection instanceof TextSelection) {
			TextSelection textSelection= (TextSelection) selection;
			// PR 39995: [navigation] Forward history cleared after going back in navigation history:
			// mark only in navigation history if the cursor is being moved (which it isn't if
			// this is called from a PostSelectionEvent that should only update the magnet)
			if (moveCursor && (textSelection.getOffset() != 0 || textSelection.getLength() != 0))
				markInNavigationHistory();
		}

		if (reference != null) {

			StyledText  textWidget= null;

			ISourceViewer sourceViewer= getSourceViewer();
			if (sourceViewer != null)
				textWidget= sourceViewer.getTextWidget();

			if (textWidget == null)
				return;

			try 
			{
				IRegion range= null;
				range = getRegionFromSourceRegion(
							reference.getLocation().getSourceRegion(),
							getViewer().getDocument());

				if (range == null)
					return;

				int offset= range.getOffset();
				int length= range.getLength();

				if (offset < 0 || length < 0)
					return;

				setHighlightRange(offset, length, moveCursor);

				if (!moveCursor)
					return;

				offset= -1;
				length= -1;

				if (offset > -1 && length > 0) 
				{
					try  
					{
						textWidget.setRedraw(false);
						sourceViewer.revealRange(offset, length);
						sourceViewer.setSelectedRange(offset, length);
					} finally {
						textWidget.setRedraw(true);
					}

					markInNavigationHistory();
				}

			} catch (Exception x) {
			}

		} else if (moveCursor) {
			resetHighlightRange();
			markInNavigationHistory();
		}
	}

	public void setSelection(IModelicaElement element) {

		if (element == null || element instanceof IModelicaFile) 
		{
			/*
			 * If the element is an IModelicaFile this unit is either the input
			 * of this editor or not being displayed. 
			 */
			return;
		}

		if (element instanceof IModelicaElement) {
			// set highlight range
			setSelection(element, true);
			// set outliner selection
			if (fOutlinePage != null) {
				fOutlineSelectionChangedListener.uninstall(fOutlinePage);
				fOutlinePage.select(element);
				fOutlineSelectionChangedListener.install(fOutlinePage);
			}
		}
	}

	/*
	 * @see AbstractTextEditor#adjustHighlightRange(int, int)
	 */
	protected void adjustHighlightRange(int offset, int length) {

		try 
		{
			IModelicaElement element= getElementAt(offset);
			while (element instanceof IModelicaElement) {
				IRegion range= getRegionFromSourceRegion(
						element.getLocation().getSourceRegion(), 
						getViewer().getDocument());
				if (offset < range.getOffset() + range.getLength() && range.getOffset() < offset + length) {

					ISourceViewer viewer= getSourceViewer();
					if (viewer instanceof ITextViewerExtension5) {
						ITextViewerExtension5 extension= (ITextViewerExtension5) viewer;
						extension.exposeModelRange(new Region(range.getOffset(), range.getLength()));
					}

					setHighlightRange(range.getOffset(), range.getLength(), true);
					if (fOutlinePage != null) {
						fOutlineSelectionChangedListener.uninstall(fOutlinePage);
						fOutlinePage.select(element);
						fOutlineSelectionChangedListener.install(fOutlinePage);
					}

					return;
				}
				element= element.getParent();
			}

		} catch (Exception x) {
			// do nothing
		}

		ISourceViewer viewer= getSourceViewer();
		if (viewer instanceof ITextViewerExtension5) {
			ITextViewerExtension5 extension= (ITextViewerExtension5) viewer;
			extension.exposeModelRange(new Region(offset, length));
		} else {
			resetHighlightRange();
		}

	}	
	
	public void setViewer(Viewer viewer)
	{
		// do nothing
	}
	
	public IRegion getRegionFromSourceRegion(ISourceRegion sourceRegion, IDocument document)
	{
		if (document == null) return new Region(1, 0);
		int startChar = 1, endChar = 1;
		try
		{
			startChar = document.getLineOffset(sourceRegion.getStartLine()-1) + sourceRegion.getStartColumn()-1;
			endChar = document.getLineOffset(sourceRegion.getEndLine()-1) + sourceRegion.getEndColumn();			
		}
		catch(BadLocationException e)
		{
			ErrorManager.logError(e);
		}
		return new Region(startChar, endChar - startChar);
	}
	
	public void setHighlightRange(ISourceRegion sourceRegion, boolean moveCursor)
	{
		IRegion region = getRegionFromSourceRegion(sourceRegion, getViewer().getDocument());
		setHighlightRange(region.getOffset(), region.getLength(), moveCursor);
	}
	
	private static final class InformationProvider implements IInformationProvider, IInformationProviderExtension, IInformationProviderExtension2 {
		
		private IRegion fHoverRegion;
		private Object fHoverInfo;
		private IInformationControlCreator fControlCreator;
		
		InformationProvider(IRegion hoverRegion, Object hoverInfo, IInformationControlCreator controlCreator) {
			fHoverRegion= hoverRegion;
			fHoverInfo= hoverInfo;
			fControlCreator= controlCreator;
		}
		/*
		 * @see org.eclipse.jface.text.information.IInformationProvider#getSubject(org.eclipse.jface.text.ITextViewer, int)
		 */
		public IRegion getSubject(ITextViewer textViewer, int invocationOffset) {
			return fHoverRegion;
		}
		/*
		 * @see org.eclipse.jface.text.information.IInformationProvider#getInformation(org.eclipse.jface.text.ITextViewer, org.eclipse.jface.text.IRegion)
		 */
		public String getInformation(ITextViewer textViewer, IRegion subject) {
			return fHoverInfo.toString();
		}
		/*
		 * @see org.eclipse.jface.text.information.IInformationProviderExtension#getInformation2(org.eclipse.jface.text.ITextViewer, org.eclipse.jface.text.IRegion)
		 * @since 3.2
		 */
		public Object getInformation2(ITextViewer textViewer, IRegion subject) {
			return fHoverInfo;
		}
		/*
		 * @see org.eclipse.jface.text.information.IInformationProviderExtension2#getInformationPresenterControlCreator()
		 */
		public IInformationControlCreator getInformationPresenterControlCreator() {
			return fControlCreator;
		}
	}
	
	
	/**
	 * This action behaves in two different ways: If there is no current text
	 * hover, the javadoc is displayed using information presenter. If there is
	 * a current text hover, it is converted into a information presenter in
	 * order to make it sticky.
	 */
	class InformationDispatchAction extends TextEditorAction {

		/** The wrapped text operation action. */
		private final TextOperationAction fTextOperationAction;

		/**
		 * Creates a dispatch action.
		 *
		 * @param resourceBundle the resource bundle
		 * @param prefix the prefix
		 * @param textOperationAction the text operation action
		 */
		public InformationDispatchAction(ResourceBundle resourceBundle, String prefix, final TextOperationAction textOperationAction) {
			super(resourceBundle, prefix, ModelicaEditor.this);
			if (textOperationAction == null)
				throw new IllegalArgumentException();
			fTextOperationAction= textOperationAction;
		}

		/*
		 * @see org.eclipse.jface.action.IAction#run()
		 */
		public void run() {

			ISourceViewer sourceViewer= getSourceViewer();
			if (sourceViewer == null) {
				fTextOperationAction.run();
				return;
			}

			if (sourceViewer instanceof ITextViewerExtension4)  {
				ITextViewerExtension4 extension4= (ITextViewerExtension4) sourceViewer;
				if (extension4.moveFocusToWidgetToken())
					return;
			}

			if (sourceViewer instanceof ITextViewerExtension2) {
				// does a text hover exist?
				ITextHover textHover= ((ITextViewerExtension2) sourceViewer).getCurrentTextHover();
				if (textHover != null && makeTextHoverFocusable(sourceViewer, textHover))
					return;
			}
			
			// otherwise, just run the action
			fTextOperationAction.run();
		}

	
		/**
		 * Tries to make a text hover focusable (or "sticky").
		 * 
		 * @param sourceViewer the source viewer to display the hover over
		 * @param textHover the hover to make focusable
		 * @return <code>true</code> if successful, <code>false</code> otherwise
		 * @since 3.2
		 */
		public boolean makeTextHoverFocusable(ISourceViewer sourceViewer, ITextHover textHover) {
			Point hoverEventLocation= ((ITextViewerExtension2) sourceViewer).getHoverEventLocation();
			int offset= computeOffsetAtLocation(sourceViewer, hoverEventLocation.x, hoverEventLocation.y);
			if (offset == -1)
				return false;

			try {
				IRegion hoverRegion= textHover.getHoverRegion(sourceViewer, offset);
				if (hoverRegion == null)
					return false;

				String hoverInfo= textHover.getHoverInfo(sourceViewer, hoverRegion);

				IInformationControlCreator controlCreator= null;
				if (textHover instanceof IInformationProviderExtension2)
					controlCreator= ((IInformationProviderExtension2)textHover).getInformationPresenterControlCreator();

				IInformationProvider informationProvider= new InformationProvider(hoverRegion, hoverInfo, controlCreator);

				fInformationPresenter.setOffset(offset);
				fInformationPresenter.setAnchor(AbstractInformationControlManager.ANCHOR_BOTTOM);
				fInformationPresenter.setMargins(6, 6); // default values from AbstractInformationControlManager
				String contentType= TextUtilities.getContentType(sourceViewer.getDocument(), IModelicaPartitions.MODELICA_PARTITIONING, offset, true);
				fInformationPresenter.setInformationProvider(informationProvider, contentType);
				fInformationPresenter.showInformation();

				return true;

			} catch (BadLocationException e) {
				return false;
			}
		}

		// modified version from TextViewer
		private int computeOffsetAtLocation(ITextViewer textViewer, int x, int y) {

			StyledText styledText= textViewer.getTextWidget();
			IDocument document= textViewer.getDocument();

			if (document == null)
				return -1;

			try {
				int widgetOffset= styledText.getOffsetAtLocation(new Point(x, y));
				Point p= styledText.getLocationAtOffset(widgetOffset);
				if (p.x > x)
					widgetOffset--;

				if (textViewer instanceof ITextViewerExtension5) {
					ITextViewerExtension5 extension= (ITextViewerExtension5) textViewer;
					return extension.widgetOffset2ModelOffset(widgetOffset);
				} else {
					IRegion visibleRegion= textViewer.getVisibleRegion();
					return widgetOffset + visibleRegion.getOffset();
				}
			} catch (IllegalArgumentException e) {
				return -1;
			}

		}
	}
	
	/*
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#initializeKeyBindingScopes()
	 */
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(new String[] { "org.modelica.mdt.ui.modelicaEditorScope" });  //$NON-NLS-1$
	}
	
}
