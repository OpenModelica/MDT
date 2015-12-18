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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.AbstractInformationControlManager;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.ITextViewerExtension4;
import org.eclipse.jface.text.ITextViewerExtension5;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.IInformationProviderExtension;
import org.eclipse.jface.text.information.IInformationProviderExtension2;
import org.eclipse.jface.text.information.InformationPresenter;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.DefaultEncodingSupport;
import org.eclipse.ui.editors.text.IEncodingSupport;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.ResourceAction;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.ui.texteditor.TextEditorAction;
import org.eclipse.ui.texteditor.TextOperationAction;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.modelica.mdt.breakpoint.MDTBreakpointAdapter;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.ISourceRegion;
import org.modelica.mdt.internal.core.CorePlugin;
import org.modelica.mdt.internal.core.DefinitionSourceRegion;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.ui.PreferenceConstants;
import org.modelica.mdt.ui.UIPlugin;
import org.modelica.mdt.ui.actions.FoldingActionGroup;
import org.modelica.mdt.ui.actions.IModelicaEditorActionDefinitionIds;
import org.modelica.mdt.ui.actions.OpenAction;
import org.modelica.mdt.ui.hover.ModelicaSourceHover;
import org.modelica.mdt.ui.hover.SourceViewerInformationControl;
import org.modelica.mdt.ui.state.State;
import org.modelica.mdt.ui.text.IModelicaPartitions;
import org.modelica.mdt.ui.text.ModelicaCodeResolver;
import org.modelica.mdt.ui.text.ModelicaDocumentProvider;
import org.modelica.mdt.ui.text.ModelicaFoldingStructureProvider;
import org.modelica.mdt.ui.text.ModelicaKeywords;
import org.modelica.mdt.ui.text.ModelicaPairMatcher;
import org.modelica.mdt.ui.text.ModelicaWordFinder;
import org.modelica.mdt.ui.util.AnalyzeRegion;
import org.modelica.mdt.ui.view.ModelicaContentOutlinePage;

/**
 * Creates an editor for modelica source code.
 *
 * The editor is configured with following features:
 *
 *  - modelica syntax highlighting
 *  - content assistant
 *  - info-pops
 *  - action that invokes content assist on ctrl+space
 *  - folding(collapsing) of blocks of source code
 *  - outline synchronization
 *  - Modelica Project Viewer synchronization
 *
 * @author Adrian Pop
 * @author MDT team
 */
public class ModelicaEditor extends TextEditor implements IPropertyListener {

	private ProjectionSupport fProjectionSupport;
	protected final static String EDITOR_MATCHING_BRACKETS="matchingBrackets";
	protected final static String EDITOR_MATCHING_BRACKETS_COLOR=  "matchingBracketsColor";
	protected final static char[] BRACKETS= { '{', '}', '(', ')', '[', ']', '<', '>' };
	protected ModelicaPairMatcher fBracketMatcher= new ModelicaPairMatcher(BRACKETS);
	private ModelicaDocumentProvider fDocumentProvider = null;

	private FoldingActionGroup fFoldingGroup;

	/** The outline page */
	protected ModelicaContentOutlinePage fOutlinePage;
	/** Outliner context menu Id */
	protected String fOutlinerContextMenuId;
	/** The information presenter. */
	private InformationPresenter fInformationPresenter;

	public ModelicaEditor() {
		addPropertyListener(this);
	}

	/* deals with opening the second file in the editor and notifying the outline page*/
	@Override
	public void propertyChanged(Object obj, int prop) {
		if (prop == IEditorPart.PROP_INPUT) {
			if (obj == this && fOutlinePage != null) {
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

	/**
	 * Returns the folding action group, or <code>null</code> if there is none.
	 *
	 * @return the folding action group, or <code>null</code> if there is none
	 * @since 3.0
	 */
	protected FoldingActionGroup getFoldingActionGroup() {
		return fFoldingGroup;
	}

	/*
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#rulerContextMenuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void rulerContextMenuAboutToShow(IMenuManager menu) {
		super.rulerContextMenuAboutToShow(menu);
		IMenuManager foldingMenu= new MenuManager("Folding", "projection"); //$NON-NLS-1$
		menu.appendToGroup(ITextEditorActionConstants.GROUP_RULERS, foldingMenu);

		IAction action= getAction("FoldingToggle"); //$NON-NLS-1$
		foldingMenu.add(action);
		action= getAction("FoldingExpandAll"); //$NON-NLS-1$
		foldingMenu.add(action);
		action= getAction("FoldingCollapseAll"); //$NON-NLS-1$
		foldingMenu.add(action);
		action= getAction("FoldingRestore"); //$NON-NLS-1$
		foldingMenu.add(action);
		action= getAction("FoldingCollapseMembers"); //$NON-NLS-1$
		foldingMenu.add(action);
		action= getAction("FoldingCollapseComments"); //$NON-NLS-1$
		foldingMenu.add(action);
	}

	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		setOutlinerContextMenuId("#ModelicaOutlinerContext"); //$NON-NLS-1$

		if (getPreferenceStore() != null) { /* TODO!! fix this later */
			getPreferenceStore().setValue(EDITOR_MATCHING_BRACKETS, true);
		}

		setSourceViewerConfiguration(new ModelicaSourceViewerConfig(this, IModelicaPartitions.MODELICA_PARTITIONING));
	}

	@Override
	public IDocumentProvider getDocumentProvider() {
		if (fDocumentProvider == null) {
			fDocumentProvider = new ModelicaDocumentProvider();
		}

		return fDocumentProvider;
	}

	@Override
	protected void createActions() {
		super.createActions();

		this.setAction("OpenAction", new OpenAction(this));

		ResourceAction resAction= new TextOperationAction(ModelicaEditorMessages.getBundleForConstructedKeys(), "ShowModelicaDoc.", this, ISourceViewer.INFORMATION, true); //$NON-NLS-1$
		resAction= new InformationDispatchAction(ModelicaEditorMessages.getBundleForConstructedKeys(), "ShowModelicaDoc.", (TextOperationAction) resAction); //$NON-NLS-1$
		resAction.setActionDefinitionId(IModelicaEditorActionDefinitionIds.SHOW_MODELICADOC);
		setAction("ShowModelicaDoc", resAction); //$NON-NLS-1$

		fFoldingGroup= new FoldingActionGroup(this, getViewer());
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		/* reuse the hover control from ModelicaSourceViewer */
		ITextHover textHover = getSourceViewerConfiguration().getTextHover(getSourceViewer(), IDocument.DEFAULT_CONTENT_TYPE);
		IInformationControlCreator informationControlCreator = ((ModelicaSourceHover)textHover).getHoverControlCreator();

		fInformationPresenter= new InformationPresenter(informationControlCreator);
		fInformationPresenter.setSizeConstraints(80, 10, true, false);
		fInformationPresenter.install(getSourceViewer());
		fInformationPresenter.setDocumentPartitioning(IModelicaPartitions.MODELICA_PARTITIONING);

		/* adrpo: add an auto edit strategy */
		ITextViewerExtension2 extension = (ITextViewerExtension2)getViewer();
		if (extension != null) {
			extension.prependAutoEditStrategy(new ModelicaAutoIndentStrategy(), IDocument.DEFAULT_CONTENT_TYPE);
		}

		/* toggle the first projection */
		if (((ProjectionViewer)getSourceViewer()).canDoOperation(ProjectionViewer.TOGGLE)) {
			((ProjectionViewer)getSourceViewer()).doOperation(ProjectionViewer.TOGGLE);
		}

		fEditorSelectionChangedListener= new EditorSelectionChangedListener();
		ISelectionProvider selProv = getSelectionProvider();
		selProv.addSelectionChangedListener(fEditorSelectionChangedListener);
		fEditorSelectionChangedListener.install(selProv);
	}

	@Override
	protected final ISourceViewer createSourceViewer(Composite parent, IVerticalRuler ruler, int styles) {
		/*
		 * setup the fViewer that is capable of text folding
		 */
		IPreferenceStore store= getPreferenceStore();
		ISourceViewer viewer = new ModelicaSourceViewer(parent, ruler, getOverviewRuler(), isOverviewRulerVisible(), styles);

		if (viewer != null && isFoldingEnabled() && (store == null)) {
			((ModelicaSourceViewer)viewer).prepareDelayedProjection();
		}

		ProjectionViewer projectionViewer = (ProjectionViewer)viewer;

		fProjectionSupport= new ProjectionSupport(projectionViewer, getAnnotationAccess(), getSharedColors());
		fProjectionSupport.addSummarizableAnnotationType("org.eclipse.ui.workbench.texteditor.error"); //$NON-NLS-1$
		fProjectionSupport.addSummarizableAnnotationType("org.eclipse.ui.workbench.texteditor.warning"); //$NON-NLS-1$
		fProjectionSupport.setHoverControlCreator(new IInformationControlCreator() {
			@Override
			public IInformationControl createInformationControl(Shell shell) {
				return new SourceViewerInformationControl(shell, SWT.TOOL | SWT.NO_TRIM | getOrientation(), SWT.NONE);
			}
		});

		fProjectionSupport.install();

		fProjectionModelUpdater= new ModelicaFoldingStructureProvider();
		if (fProjectionModelUpdater != null) {
			fProjectionModelUpdater.install(this, projectionViewer);
		}

		// ensure source fViewer decoration support has been created and configured
		SourceViewerDecorationSupport svds = getSourceViewerDecorationSupport(viewer);
		svds.setCharacterPairMatcher(fBracketMatcher);

		return viewer;
	}

	public ISourceViewer getViewer() {
		return getSourceViewer();
	}

	@Override
	public void dispose() {
		super.dispose();

		if (fEncodingSupport != null) {
			fEncodingSupport.dispose();
			fEncodingSupport= null;
		}

		if (fEditorSelectionChangedListener != null)  {
			fEditorSelectionChangedListener.uninstall(getSelectionProvider());
			fEditorSelectionChangedListener= null;
		}
		if (fInformationPresenter != null) {
			fInformationPresenter.uninstall();
			fInformationPresenter = null;
		}
		if (fProjectionModelUpdater != null) {
			fProjectionModelUpdater.uninstall();
			fProjectionModelUpdater= null;
		}

		if (fProjectionSupport != null) {
			fProjectionSupport.dispose();
			fProjectionSupport= null;
		}
	}

	@Override
	protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {
		support.setCharacterPairMatcher(fBracketMatcher);
		support.setMatchingCharacterPainterPreferenceKeys(
				EDITOR_MATCHING_BRACKETS,
				EDITOR_MATCHING_BRACKETS_COLOR);
		support.setSymbolicFontName(getFontPropertyPreferenceKey());

		super.configureSourceViewerDecorationSupport(support);
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		try {
			if (IContentOutlinePage.class.equals(adapter)) {
				if (fOutlinePage == null) {
					fOutlinePage= createOutlinePage();
				}

				return fOutlinePage;
			}
		}
		catch (NoClassDefFoundError e) {
			ErrorManager.logError(e);
		}

		if (IEncodingSupport.class.equals(adapter)) {
			return fEncodingSupport;
		}

		if (adapter == ModelicaFoldingStructureProvider.class) {
			return fProjectionModelUpdater;
		}

		if (fProjectionSupport != null) {
			Object newAdapter = fProjectionSupport.getAdapter(getSourceViewer(), adapter);
			if (newAdapter != null) {
				return newAdapter;
			}
		}

		if (IToggleBreakpointsTarget.class.equals(adapter)) {
			return new MDTBreakpointAdapter();
		}

		return super.getAdapter(adapter);
	}

	/**
	 * Creates the outline page used with this editor.
	 *
	 * @return the created Modelica outline page
	 */
	protected ModelicaContentOutlinePage createOutlinePage() {
		ModelicaContentOutlinePage page= new ModelicaContentOutlinePage(fOutlinerContextMenuId, this);
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
	public void synchronizeOutlinePage(IModelicaElement element, boolean checkIfOutlinePageActive) {
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

		/**
		 * @param modelicaEditor
		 */
		EditorSelectionChangedListener() {
			this.documentProvider = ModelicaEditor.this.getDocumentProvider();
		}

		/*
		 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
		 */
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			if (State.getInstance().markOccurrencesOn()) {
				ISelection selection = event.getSelection();
				Object source = event.getSource();

				handleMarkOccurrences(selection, source);
			}
			else {
				// Clear any old marks.
				// FIXME: This should happen as soon as the option is turned off, now
				// it happens when the selection changes.
				IAnnotationModel model = getAnnotationModel();
				if (model != null) {
					removeAnnotations(model);
				}
			}

			ModelicaEditor.this.selectionChanged();
		}

		private void handleMarkOccurrences(ISelection selection, Object source) {
			if (selection instanceof TextSelection && source instanceof ModelicaSourceViewer) {
				TextSelection textSelection = (TextSelection)selection;
				ModelicaSourceViewer modelicaSourceViewer = (ModelicaSourceViewer)source;
				IDocument document = modelicaSourceViewer.getDocument();

				if (document != null) {
					int offset = textSelection.getOffset();
					IRegion region = ModelicaWordFinder.findWord(document, offset);
					AnalyzeRegion wordAnalyzer = new AnalyzeRegion();
					String hoverStr = wordAnalyzer.performAnalysis(region, document, ModelicaEditor.this);

					if (hoverStr != null && wordAnalyzer.identifierFound()) {
						String word = wordAnalyzer.getWord();

						if (maybeChangeMark(word, offset)) {
							changeMark(word, document);
						}
					}
				}
			}
		}

		private boolean maybeChangeMark(String word, int offset) {
			if (word == null || word.length() == 0) {
				return false;
			}

			if (word.equals(currentMark)) {
				return false;
			}

			if (ModelicaKeywords.isKeyword(word)) {
				return false;
			}

			final char firstChar = word.charAt(0);

			if (!Character.isJavaIdentifierStart(firstChar)) {
				return false;
			}

			return true;
		}

		private void changeMark(String word, IDocument document) {
			IAnnotationModel model = getAnnotationModel();

			if (model != null) {
				currentMark = word;

				removeAnnotations(model);

				model.connect(document);
				String text = document.get();
				int index = 0;
				while (index != -1) {
					index = text.indexOf(word, index);
					boolean isGoodIndex = isGoodIndex(index, word, text);
					if (isGoodIndex) {
						Position position = new Position(index, word.length());
						// We need a new Annotation object, can't re-use.
						Annotation annotation = new Annotation("org.eclipse.jdt.ui.occurrences", false, "Description");
						model.addAnnotation(annotation, position);
						markedPositions.add(annotation);
					}

					if (index != -1) {
						index += word.length();
					}
				}
			}
		}

		private boolean isGoodIndex(final int index, final String word, final String text) {
			boolean isGoodIndex = false;

			if (index != -1) {
				// We may have a good index, but say the word we want to mark is "foo" and
				// we found the word "foobar", the index is not good for marking after all.
				final int indexNextToWord = index + word.length() + 1;

				if (indexNextToWord >= text.length()) {
					// Are we at the end of the document? Then we can mark.
					isGoodIndex = true;
				}
				else {
					// Check char immediately to our right
					final char rightChar = text.charAt(indexNextToWord);
					if (!Character.isJavaIdentifierPart(rightChar) && !Character.isDigit(rightChar)) {
						isGoodIndex = true;
					}
				}
			}

			return isGoodIndex;
		}

		private IAnnotationModel getAnnotationModel() {
			IEditorInput editorInput = ModelicaEditor.this.getEditorInput();
			IAnnotationModel model = documentProvider.getAnnotationModel(editorInput);

			return model;
		}

		private void removeAnnotations(IAnnotationModel model) {
			for (Annotation annotation : markedPositions) {
				model.removeAnnotation(annotation);
			}
		}

		private final IDocumentProvider documentProvider;

		private String currentMark;
		private List<Annotation> markedPositions = new ArrayList<Annotation>();
	}

	protected void selectionChanged() {
		if (getSelectionProvider() == null) {
			return;
		}

		IModelicaElement element= computeHighlightRangeSourceReference();
		if (CorePlugin.getDefault().getPreferenceStore().getBoolean("ModelicaEditor.SyncOutlineOnCursorMove")) {
			synchronizeOutlinePage(element);
		}

		setSelection(element, false);
	}

	/**
	 * Updates the selection in the editor's widget with the selection of the outline page.
	 */
	class OutlineSelectionChangedListener  extends AbstractSelectionChangedListener {
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			doSelectionChanged(event);
		}
	}

	private IWorkbenchPage getActivePage() {
		IWorkbenchWindow window= UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();
		if (window == null) {
			return null;
		}

		return window.getActivePage();
	}

	protected void doSelectionChanged(SelectionChangedEvent event) {
		IModelicaElement reference= null;

		ISelection selection= event.getSelection();
		Iterator<?> iter= ((IStructuredSelection) selection).iterator();
		while (iter.hasNext()) {
			Object o= iter.next();
			if (o instanceof IModelicaElement) {
				reference= (IModelicaElement) o;
				break;
			}
		}
		if (!isActivePart() && getActivePage() != null) {
			getActivePage().bringToTop(this);
		}

		setSelection(reference, !isActivePart());

		ISelectionProvider selectionProvider= getSelectionProvider();
		if (selectionProvider == null) {
			return;
		}

		ISelection textSelection= selectionProvider.getSelection();
		if (!(textSelection instanceof ITextSelection)) {
			return;
		}
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
		if (page == null) {
			return;
		}

		IModelicaElement me = getEditorInputModelicaElement();

		if (me != null) {
			page.setInput(me);
		}
		else {
			page.setInput(null);
		}
	}

	/**
	 * Returns the Modelica element wrapped by this editors input.
	 *
	 * @return the Modelica element wrapped by this editors input.
	 * @since 0.6.8
	 */
	public IModelicaElement getEditorInputModelicaElement() {
		IEditorInput i = getEditorInput();

		if (i != null && i instanceof ModelicaElementEditorInput) {
			IModelicaElement me = ((ModelicaElementEditorInput)i).getSourceFile();
			if (me != null) {
				return me;
			}
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
	public IModelicaElement computeHighlightRangeSourceReference() {
		int caret = getCarretOffset();

		if (caret == -1) {
			return null;
		}

		IModelicaElement element= getElementAt(caret, false);

		if (element == null) {
			return null;
		}

		return element;
	}

	public int getCarretOffset() {
		int caret =-1;
		ISourceViewer sourceViewer= getSourceViewer();
		if (sourceViewer == null) {
			return caret;
		}

		StyledText styledText= sourceViewer.getTextWidget();

		if (styledText == null) {
			return caret;
		}

		if (sourceViewer instanceof ITextViewerExtension5) {
			ITextViewerExtension5 extension= (ITextViewerExtension5)sourceViewer;
			caret= extension.widgetOffset2ModelOffset(styledText.getCaretOffset());
		}
		else {
			int offset= sourceViewer.getVisibleRegion().getOffset();
			caret= offset + styledText.getCaretOffset();
		}

		return caret;
	}

	/**
	 * Returns the most narrow modelica element including the given offset.
	 *
	 * @param offset the offset inside of the requested element
	 * @param reconcile <code>true</code> if editor input should be reconciled in advance
	 * @return the most narrow Modelica element
	 * @since 0.6.8
	 */
	public IModelicaElement getElementAt(int offset, boolean reconcile) {
		ISourceRegion sourceRegion = null;

		try {
			int line = getViewer().getDocument().getLineOfOffset(offset);
			int firstOffset = getViewer().getDocument().getLineOffset(line);
			int realColumn = offset - firstOffset;
			sourceRegion = new DefinitionSourceRegion(line+1, realColumn, line+1, realColumn+1);
		}
		catch (BadLocationException e) {
			//ErrorManager.logError(e);
		}

		return ModelicaCodeResolver.getClassAt(this, sourceRegion);
	}

	/**
	 * Returns the most narrow modelica element including the given offset.
	 *
	 * @param offset the offset inside of the requested element
	 * @return the most narrow modelica element
	 */
	public IModelicaElement getElementAt(int offset) {
		return getElementAt(offset, true);
	}

	protected void setSelection(IModelicaElement reference, boolean moveCursor) {
		if (getSelectionProvider() == null) {
			return;
		}

		ISelection selection= getSelectionProvider().getSelection();

		if (selection instanceof TextSelection) {
			TextSelection textSelection= (TextSelection) selection;
			// PR 39995: [navigation] Forward history cleared after going back in navigation history:
			// mark only in navigation history if the cursor is being moved (which it isn't if
			// this is called from a PostSelectionEvent that should only update the magnet)
			if (moveCursor && (textSelection.getOffset() != 0 || textSelection.getLength() != 0)) {
				markInNavigationHistory();
			}
		}

		if (reference != null) {
			StyledText  textWidget= null;
			ISourceViewer sourceViewer= getSourceViewer();

			if (sourceViewer != null) {
				textWidget= sourceViewer.getTextWidget();
			}

			if (textWidget == null) {
				return;
			}

			try {
				IRegion range= null;
				range = getRegionFromSourceRegion(reference.getLocation().getSourceRegion(), getViewer().getDocument());

				if (range == null) {
					return;
				}

				int offset= range.getOffset();
				int length= range.getLength();

				if (offset < 0 || length < 0) {
					return;
				}

				setHighlightRange(offset, length, moveCursor);

				if (!moveCursor) {
					return;
				}

				offset= -1;
				length= -1;

				if (offset > -1 && length > 0) {
					try {
						textWidget.setRedraw(false);
						sourceViewer.revealRange(offset, length);
						sourceViewer.setSelectedRange(offset, length);
					}
					finally {
						textWidget.setRedraw(true);
					}

					markInNavigationHistory();
				}
			}
			catch (Exception e) {
				// Empty.
			}
		}
		else if (moveCursor) {
			resetHighlightRange();
			markInNavigationHistory();
		}
	}

	public void setSelection(IModelicaElement element) {
		if (element == null || element instanceof IModelicaFile) {
			 // If the element is an IModelicaFile this unit is either the input
			 // of this editor or not being displayed.
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
	@Override
	protected void adjustHighlightRange(int offset, int length) {
		try {
			IModelicaElement element= getElementAt(offset);
			while (element instanceof IModelicaElement) {
				IRegion range= getRegionFromSourceRegion(element.getLocation().getSourceRegion(), getViewer().getDocument());
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
		}
		catch (Exception x) {
			// do nothing
		}

		ISourceViewer viewer= getSourceViewer();
		if (viewer instanceof ITextViewerExtension5) {
			ITextViewerExtension5 extension= (ITextViewerExtension5) viewer;
			extension.exposeModelRange(new Region(offset, length));
		}
		else {
			resetHighlightRange();
		}
	}

	public void setViewer(Viewer viewer) {
		// do nothing
	}

	public IRegion getRegionFromSourceRegion(ISourceRegion sourceRegion, IDocument document) {
		if (document == null) {
			return new Region(1, 0);
		}

		int startChar = 1;
		int endChar = 1;

		try {
			startChar = document.getLineOffset(sourceRegion.getStartLine()-1) + sourceRegion.getStartColumn()-1;
			endChar = document.getLineOffset(sourceRegion.getEndLine()-1) + sourceRegion.getEndColumn();
		}
		catch (BadLocationException e) {
			//ErrorManager.logError(e);
		}

		return new Region(startChar, endChar - startChar);
	}

	public void setHighlightRange(ISourceRegion sourceRegion, boolean moveCursor) {
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
		@Override
		public IRegion getSubject(ITextViewer textViewer, int invocationOffset) {
			return fHoverRegion;
		}

		/*
		 * @see org.eclipse.jface.text.information.IInformationProvider#getInformation(org.eclipse.jface.text.ITextViewer, org.eclipse.jface.text.IRegion)
		 */
		@Override
		public String getInformation(ITextViewer textViewer, IRegion subject) {
			return fHoverInfo.toString();
		}

		/*
		 * @see org.eclipse.jface.text.information.IInformationProviderExtension#getInformation2(org.eclipse.jface.text.ITextViewer, org.eclipse.jface.text.IRegion)
		 * @since 3.2
		 */
		@Override
		public Object getInformation2(ITextViewer textViewer, IRegion subject) {
			return fHoverInfo;
		}

		/*
		 * @see org.eclipse.jface.text.information.IInformationProviderExtension2#getInformationPresenterControlCreator()
		 */
		@Override
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
			if (textOperationAction == null) {
				throw new IllegalArgumentException();
			}

			fTextOperationAction= textOperationAction;
		}

		/*
		 * @see org.eclipse.jface.action.IAction#run()
		 */
		@Override
		public void run() {
			ISourceViewer sourceViewer= getSourceViewer();

			if (sourceViewer == null) {
				fTextOperationAction.run();
				return;
			}

			if (sourceViewer instanceof ITextViewerExtension4)  {
				ITextViewerExtension4 extension4= (ITextViewerExtension4) sourceViewer;
				if (extension4.moveFocusToWidgetToken()) {
					return;
				}
			}

			if (sourceViewer instanceof ITextViewerExtension2) {
				// does a text hover exist?
				ITextHover textHover= ((ITextViewerExtension2) sourceViewer).getCurrentTextHover();
				if (textHover != null && makeTextHoverFocusable(sourceViewer, textHover)) {
					return;
				}
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
			if (offset == -1) {
				return false;
			}

			try {
				IRegion hoverRegion= textHover.getHoverRegion(sourceViewer, offset);
				if (hoverRegion == null) {
					return false;
				}

				String hoverInfo= textHover.getHoverInfo(sourceViewer, hoverRegion);

				IInformationControlCreator controlCreator= null;
				if (textHover instanceof IInformationProviderExtension2) {
					controlCreator= ((IInformationProviderExtension2)textHover).getInformationPresenterControlCreator();
				}

				/* TODO! TODO! FIXME! - when opened on a normal file, the control creator is NULL! */
				IInformationProvider informationProvider= new InformationProvider(hoverRegion, hoverInfo, controlCreator);

				fInformationPresenter.setOffset(offset);
				fInformationPresenter.setAnchor(AbstractInformationControlManager.ANCHOR_BOTTOM);
				fInformationPresenter.setMargins(6, 6); // default values from AbstractInformationControlManager
				String contentType= TextUtilities.getContentType(sourceViewer.getDocument(), IModelicaPartitions.MODELICA_PARTITIONING, offset, true);
				fInformationPresenter.setInformationProvider(informationProvider, contentType);
				fInformationPresenter.showInformation();

				return true;
			}
			catch (BadLocationException e) {
				return false;
			}
		}

		// modified version from TextViewer
		private int computeOffsetAtLocation(ITextViewer textViewer, int x, int y) {
			StyledText styledText= textViewer.getTextWidget();
			IDocument document= textViewer.getDocument();

			if (document == null) {
				return -1;
			}

			try {
				int widgetOffset= styledText.getOffsetAtLocation(new Point(x, y));
				Point p= styledText.getLocationAtOffset(widgetOffset);
				if (p.x > x) {
					widgetOffset--;
				}

				if (textViewer instanceof ITextViewerExtension5) {
					ITextViewerExtension5 extension= (ITextViewerExtension5) textViewer;
					return extension.widgetOffset2ModelOffset(widgetOffset);
				}
				else {
					IRegion visibleRegion= textViewer.getVisibleRegion();
					return widgetOffset + visibleRegion.getOffset();
				}
			}
			catch (IllegalArgumentException e) {
				return -1;
			}
		}
	}

	/*
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#initializeKeyBindingScopes()
	 */
	@Override
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(new String[] { "org.modelica.mdt.ui.modelicaEditorScope" });  //$NON-NLS-1$
	}

	private Point fCachedSelectedRange;

	public Point getCachedSelectedRange() {
		return fCachedSelectedRange;
	}

	/*
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#handleCursorPositionChanged()
	 * @since 3.3
	 */
	@Override
	protected void handleCursorPositionChanged() {
		super.handleCursorPositionChanged();
		fCachedSelectedRange= getViewer().getSelectedRange();
	}

	private ModelicaFoldingStructureProvider fProjectionModelUpdater;

	/**
	 * Resets the foldings structure according to the folding
	 * preferences.
	 *
	 * @since 3.2
	 */
	public void resetProjection() {
		if (fProjectionModelUpdater != null) {
			fProjectionModelUpdater.initialize();
		}
	}

	/**
	 * The folding runner.
	 * @since 3.1
	 */
	private ToggleFoldingRunner fFoldingRunner;
	private final class ToggleFoldingRunner implements IPartListener2 {
		/**
		 * The workbench page we registered the part listener with, or
		 * <code>null</code>.
		 */
		private IWorkbenchPage fPage;

		/**
		 * Does the actual toggling of projection.
		 */
		private void toggleFolding() {
			ISourceViewer sourceViewer= getSourceViewer();
			if (sourceViewer instanceof ProjectionViewer) {
				ProjectionViewer pv= (ProjectionViewer) sourceViewer;
				if (pv.canDoOperation(ProjectionViewer.TOGGLE)) {
					pv.doOperation(ProjectionViewer.TOGGLE);
				}
			}
		}

		/**
		 * Makes sure that the editor's folding state is correct the next time
		 * it becomes visible. If it already is visible, it toggles the folding
		 * state. If not, it either registers a part listener to toggle folding
		 * when the editor becomes visible, or cancels an already registered
		 * runner.
		 */
		public void runWhenNextVisible() {
			// if there is one already: toggling twice is the identity
			if (fFoldingRunner != null) {
				fFoldingRunner.cancel();
				return;
			}
			IWorkbenchPartSite site= getSite();
			if (site != null) {
				IWorkbenchPage page= site.getPage();
				if (!page.isPartVisible(ModelicaEditor.this)) {
					// if we're not visible - defer until visible
					fPage= page;
					fFoldingRunner= this;
					page.addPartListener(this);
					return;
				}
			}
			// we're visible - run now
			toggleFolding();
		}

		/**
		 * Remove the listener and clear the field.
		 */
		private void cancel() {
			if (fPage != null) {
				fPage.removePartListener(this);
				fPage= null;
			}
			if (fFoldingRunner == this) {
				fFoldingRunner= null;
			}
		}

		/*
		 * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.IWorkbenchPartReference)
		 */
		@Override
		public void partVisible(IWorkbenchPartReference partRef) {
			if (ModelicaEditor.this.equals(partRef.getPart(false))) {
				cancel();
				toggleFolding();
			}
		}

		/*
		 * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.IWorkbenchPartReference)
		 */
		@Override
		public void partClosed(IWorkbenchPartReference partRef) {
			if (ModelicaEditor.this.equals(partRef.getPart(false))) {
				cancel();
			}
		}

		@Override
		public void partActivated(IWorkbenchPartReference partRef) {}
		@Override
		public void partBroughtToTop(IWorkbenchPartReference partRef) {}
		@Override
		public void partDeactivated(IWorkbenchPartReference partRef) {}
		@Override
		public void partOpened(IWorkbenchPartReference partRef) {}
		@Override
		public void partHidden(IWorkbenchPartReference partRef) {}
		@Override
		public void partInputChanged(IWorkbenchPartReference partRef) {}
	}

	/**
	 * Collapses all foldable members if supported by the folding
	 * structure provider.
	 *
	 * @since 3.2
	 */
	public void collapseMembers() {
		fProjectionModelUpdater.collapseMembers();
	}

	/**
	 * Collapses all foldable comments if supported by the folding
	 * structure provider.
	 *
	 * @since 3.2
	 */
	public void collapseComments() {
		fProjectionModelUpdater.collapseComments();
	}

	@Override
	protected void handlePreferenceStoreChanged(PropertyChangeEvent event) {
		String property= event.getProperty();

		try {
			ISourceViewer sourceViewer= getSourceViewer();

			if (sourceViewer == null) {
				return;
			}

			boolean newBooleanValue= false;
			Object newValue= event.getNewValue();

			if (newValue != null) {
				newBooleanValue= Boolean.valueOf(newValue.toString()).booleanValue();
			}

			if (PreferenceConstants.EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE.equals(property)) {
				if (newBooleanValue) {
					selectionChanged();
				}

				return;
			}

			if (PreferenceConstants.EDITOR_FOLDING_PROVIDER.equals(property)) {
				if (sourceViewer instanceof ProjectionViewer) {
					ProjectionViewer projectionViewer= (ProjectionViewer) sourceViewer;
					if (fProjectionModelUpdater != null) {
						fProjectionModelUpdater.uninstall();
					}
					// either freshly enabled or provider changed
					fProjectionModelUpdater= new ModelicaFoldingStructureProvider();
					if (fProjectionModelUpdater != null) {
						fProjectionModelUpdater.install(this, projectionViewer);
					}
				}

				return;
			}
			if (PreferenceConstants.EDITOR_FOLDING_ENABLED.equals(property)) {
				if (sourceViewer instanceof ProjectionViewer) {
					new ToggleFoldingRunner().runWhenNextVisible();
				}

				return;
			}
		}
		finally {
			super.handlePreferenceStoreChanged(event);
		}

		if (AbstractDecoratedTextEditorPreferenceConstants.SHOW_RANGE_INDICATOR.equals(property)) {
			// superclass already installed the range indicator
			Object newValue= event.getNewValue();
			ISourceViewer viewer= getSourceViewer();
			if (newValue != null && viewer != null) {
				if (Boolean.valueOf(newValue.toString()).booleanValue()) {
					// adjust the highlightrange in order to get the magnet right after changing the selection
					Point selection= viewer.getSelectedRange();
					adjustHighlightRange(selection.x, selection.y);
				}
			}
		}
	}

	boolean isFoldingEnabled() {
		return true;
		//return UIPlugin.getDefault().getPreferenceStore().getBoolean(SpellingPreferenceConstants.EDITOR_FOLDING_ENABLED);
	}

	/*
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#performRevert()
	 */
	@Override
	protected void performRevert() {
		ProjectionViewer projectionViewer= (ProjectionViewer) getSourceViewer();
		projectionViewer.setRedraw(false);

		try {
			boolean projectionMode= projectionViewer.isProjectionMode();
			if (projectionMode) {
				projectionViewer.disableProjection();
				if (fProjectionModelUpdater != null) {
					fProjectionModelUpdater.uninstall();
				}
			}

			super.performRevert();

			if (projectionMode) {
				if (fProjectionModelUpdater != null) {
					fProjectionModelUpdater.install(this, projectionViewer);
				}

				projectionViewer.enableProjection();
			}
		}
		finally {
			projectionViewer.setRedraw(true);
		}
	}

	@Override
	protected void installEncodingSupport() {
		fEncodingSupport= new DefaultEncodingSupport();
		fEncodingSupport.initialize(this);
	}

	/*
	 * @see StatusTextEditor#getStatusHeader(IStatus)
	 */
	@Override
	protected String getStatusHeader(IStatus status) {
		String statusHeader = null;

		if (fEncodingSupport != null) {
			statusHeader = fEncodingSupport.getStatusHeader(status);
		}

		if (statusHeader == null) {
			statusHeader = super.getStatusHeader(status);
		}

		return statusHeader;
	}

	/*
	 * @see StatusTextEditor#getStatusBanner(IStatus)
	 */
	@Override
	protected String getStatusBanner(IStatus status) {
		String statusBanner = null;

		if (fEncodingSupport != null) {
			statusBanner = fEncodingSupport.getStatusBanner(status);
		}

		if (statusBanner == null) {
			statusBanner = super.getStatusBanner(status);
		}

		return statusBanner;
	}

	/*
	 * @see StatusTextEditor#getStatusMessage(IStatus)
	 */
	@Override
	protected String getStatusMessage(IStatus status) {
		String statusMessage = null;

		if (fEncodingSupport != null) {
			statusMessage = fEncodingSupport.getStatusMessage(status);
		}

		if (statusMessage == null) {
			statusMessage = super.getStatusMessage(status);
		}

		return statusMessage;
	}
	
	/**
	 * Get a list with the unused variables in the file in the editor.
	 * One set of strings contains: <br>
	 *  Name <br>
	 * 	LineNumber <br>
	 *  Type of variable <br>
	 * @return
	 */
	public ArrayList<String[]> getUnusedVariables()
	{
		ArrayList<String[]> list = new ArrayList<String[]>();
		ArrayList<String[]> functionList = getFunctions(); // list with all functions and where they start and end
		//IDocument document = getDocumentProvider().getDocument(getEditorInput());
		
		for (int i= 0; i < functionList.size(); i++) // look for unused variables in functions
		{
			List<String[]> variables = new ArrayList<String[]>();
			//protected part
			
			int startLine = Integer.parseInt(functionList.get(i)[1]);
			int endLine = Integer.parseInt(functionList.get(i)[2]);
			variables = getUnusedVariablesForFunction(startLine, endLine, functionList.get(i)[0]);
			if (variables == null)
				continue;
			if ( variables.isEmpty() )
				continue;
			for (int j = 0; j < variables.size(); j++) {	
				//if unused add
				String[] tmp = {variables.get(j)[0],variables.get(j)[2],variables.get(j)[1]  ,functionList.get(i)[0] };
				list.add(tmp);
			}
		}
		//return list
		return list;
	}
	
	/**
	 * Returns all Functions that have a wrong or no documentation.<b>
	 * The return-Values are [function_Name, StartLine, Endline]. The lines are respective to the document
	 * 
	 * @return ArrayList<String[]>
	 */

	public List<String[]> getWrongFunctionDoc(String regEx)
	{
		ArrayList<String[]> list = new ArrayList<String[]>();
		ArrayList<String[]> functionList = getFunctions();
		for (int i= 0; i < functionList.size(); i++) // search for documentatins and check
		{
			//checkFunctionDoc("gets the stateVars from the list of vars. author:Waurich TUD 2013-06");
			int startLine = Integer.parseInt(functionList.get(i)[1]);
			int endLine = Integer.parseInt(functionList.get(i)[2]);
			
			IWorkbench wb = PlatformUI.getWorkbench();
			IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
			IWorkbenchPage page = win.getActivePage();
			IDocument document = (IDocument) getDocumentProvider().getDocument(page.getActiveEditor().getEditorInput());
			String doc = ""; //the string with the documentation
			//get the documentation of the function
			try {	
				String funcText = document.get(document.getLineOffset(startLine), document.getLineOffset(endLine)-document.getLineOffset(startLine));
				
				//ArrayList<String> matches = new ArrayList<String>();
				
				Matcher m = Pattern.compile("\\\"[^\\\"]*\\\"").matcher(funcText);
				if (m.find())
				doc = m.group();

			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			
			//Check for pattern in documentation
			if (!checkRegEx(doc, regEx))
				list.add(functionList.get(i));

		}
		
		return list;
	}
	

	public List<String[]> getUnusedFunctions()
	{
		ArrayList<String[]> list = new ArrayList<String[]>();
		ArrayList<String[]> functionList = getFunctions(); // list with all functions and where they start and end
		//IDocument document = getDocumentProvider().getDocument(getEditorInput());
		
		for (int i= 0; i < functionList.size(); i++) // look for unused variables in functions
		{
			int startLine = Integer.parseInt(functionList.get(i)[1]);
			int endLine = Integer.parseInt(functionList.get(i)[2]);
			
			IWorkbench wb = PlatformUI.getWorkbench();
			IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
			IWorkbenchPage page = win.getActivePage();
			IDocument document = (IDocument) getDocumentProvider().getDocument(page.getActiveEditor().getEditorInput());
			try {
				endLine = document.getLineOffset(endLine)+document.getLineLength(endLine);
				
				if (document.get(document.getLineOffset(startLine),document.getLineLength(startLine)).trim().startsWith("public") )
					continue;
				startLine = document.getLineOffset(startLine);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}

			String doc = document.get();
			int lastIndex = 0;
			boolean found = false;
			while(lastIndex != -1)
			{
				lastIndex = doc.indexOf(functionList.get(i)[0],lastIndex);
			    
			    if (lastIndex == -1)
			    	break;	    
			    //found function and not in itself
			    if (!( (lastIndex >= startLine) && (lastIndex <= endLine) ) ) {
			    		found = true;
			    		break;
			    	}   
			    if(lastIndex != -1) {
			        lastIndex += functionList.get(i)[0].length();
			    }
			}
			
			if (!found) {
				list.add(functionList.get(i));
			}	
		}
		return list;
	}

	private List<String[]> getUnusedVariablesForFunction(int startLine, int endLine, String funcName)
	{
		String lineString = "";
		int line = startLine+1;
		
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = win.getActivePage();
		 
		//get editorInput from active page
		IDocument document = (IDocument) getDocumentProvider().getDocument(page.getActiveEditor().getEditorInput());
		
		ArrayList<String[]> variables = new ArrayList<String[]>(); //list with All variables in the function
		ArrayList<String[]> returnVariables = new ArrayList<String[]>(); //list with unused Variables only
		//structure of the list: Name, Type, Line
		ITypedRegion[] linePartitioning = null;
		int offs = 0;
		boolean protec = false;
		int algoStartLine = -1;
		String partFunctName = "";
		while (line <= endLine)	
		{
			if (lineString.trim().equals("algorithm")) {
				algoStartLine = line;
				line = endLine;
				break;
			}
			
			String[] var = {"","",""};
			try {
				lineString = document.get( document.getLineOffset(line) , document.getLineLength(line));				
				linePartitioning= TextUtilities.computePartitioning(document, IModelicaPartitions.MODELICA_PARTITIONING, document.getLineOffset(line), document.getLineLength(line), false);
				if (line < endLine-1)
					offs = document.getLineOffset(line+1)-1;
				else offs = document.getLineOffset(endLine)-1;
				
			} catch (BadLocationException e) {
				e.printStackTrace();
				return null;
			}

			//if Not String or comment check for type of variable and then name
			boolean found = false;
			for (int i = 0; i< linePartitioning.length; i++)
			{
				if ( (linePartitioning[i].getType().equals("__dftl_partition_content_type") ) && (linePartitioning[i].getLength() >= 1) && (linePartitioning[i].getOffset()<offs) )
				{
					try {
						if (document.get(linePartitioning[i].getOffset(), linePartitioning[i].getLength()).trim().length() <= 0) 
							break;
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
					
					found = true;
					break;
				}
			}
			// finished checking for comments
			
			if (!found) // line is comments and strings only
			{
				line++;
				continue;
			}
			//get variable from line
			if (lineString.contains("replaceable"))
			{
				line++;
				continue;
			}
			// check for partial functions and skip them
			if (lineString.contains("partial")) {
				partFunctName = lineString.trim().substring(lineString.trim().lastIndexOf("function")+8, lineString.trim().length()-1);
				line++;
				continue;
			}

			if (partFunctName != "") {
				lineString = lineString.trim();
				if ( lineString.startsWith("end") && (lineString.contains(partFunctName.trim())) )
				{
					partFunctName = "";
				}
				
				line++;
				continue;
			}
			//start with the algorithm
			int add = 0;
			int prefixIndex = 0;
			String type = "";
			String name = "";
			if (!protec) //not in protected range
			{
				if (lineString.contains("protected")) {
					protec = true;
					line++;
					continue;
				}
				//break because of end of the variables
				if (lineString.contains("algorithm")) {
					algoStartLine = line;
					line = endLine;
					break;
				}
				prefixIndex = lineString.indexOf("input")-1;
				
				if (prefixIndex <= -1) {
					prefixIndex = lineString.indexOf("output");
					add += 7+prefixIndex;
				}
				else
					add += 6+prefixIndex;
			}
			else //protec
			{
				//break because of end of the variables
				if (lineString.contains("algorithm")) 
				{
					algoStartLine = line;
					line = endLine;
					break;
				}
			}
			
			//check for whitespaces
			while ( (lineString.charAt(add) == ' ') || (lineString.charAt(add) == '\t') ) {				
				add++;
				if (add > lineString.length())
					break;				
			}
			//extract varname and type
			ArrayList<String> nameList = new ArrayList<String>();
		
			type = extractType(lineString, add);
			add += type.length();
			if (add >= lineString.length()) {
				//System.out.println(Integer.toString(line));
				return null;
			}
			while ( (lineString.charAt(add) == ' ') || (lineString.charAt(add) == '\t') ) {				
				add++;
				if (add > lineString.length())
					break;				
			}
			
			for (int i= add; i < lineString.length(); i++)
			{
				if ( (lineString.charAt(i) == ' ') || (lineString.charAt(i) == '\t') || (lineString.charAt(i) == ';') )
					{ 
						if (nameList.isEmpty() || (lineString.charAt(i) == ';') )
							{
								if ( (!nameList.isEmpty() ) && ( !lineString.trim().startsWith("output") ) )
									nameList.add(name);
								break;
							}
						else
							continue;
					}
				else
					if (lineString.charAt(i) != ',')
						name += lineString.charAt(i);
					else //more then one variable with this type in the same line
					{
						nameList.add(name);
						name = "";	
					}
			}
			if ( (nameList.isEmpty() ) 
					&&  (!lineString.trim().startsWith("output")) 
					) {
					var[0] = name;
					var[1] = type;
					var[2] = Integer.toString(line - 1);
					variables.add(var);
			}
			else
				if  ( (!lineString.trim().startsWith("output")) )
				for (int i = 0; i < nameList.size(); i++) {
					String[] tmpvar = {nameList.get(i),type,Integer.toString(line -1 )};				
					variables.add(tmpvar);
				}
			
			//add found variables to list
			//break because of end of the variables
			if (lineString.contains("algorithm")) {
				algoStartLine = line;
				line = endLine;				
				break;
			}

			line++;
		}
		
		//complete list of variables available
		//search for variables
		String algorithm = "";
		String outputVariables = "";
		if ( (algoStartLine < 0) && (lineString.contains("end"))  && (lineString.contains(funcName)) )
			//return variables;
			algoStartLine = startLine;
		try {
			algorithm = document.get( document.getLineOffset(algoStartLine) , document.getLineOffset(endLine)-document.getLineOffset(algoStartLine));
			outputVariables = document.get(document.getLineOffset(startLine), document.getLineOffset(algoStartLine) - document.getLineOffset(startLine));
		} catch (BadLocationException e) {
			e.printStackTrace();
			return variables;
		}
		
		boolean found = false;
		for (int var = 0; var < variables.size(); var++) {
			found = false;
			//check each variable	
			if ( algorithm.indexOf(variables.get(var)[0]) != -1)		
				found = true;
			//check output variables

			//=.*v((;)|([^A-Za-z0-9]))			
			Pattern p = Pattern.compile("=.*[^A-Za-z0-9]+"+variables.get(var)[0]+"((;)|([^A-Za-z0-9]))");
			Matcher m = p.matcher(outputVariables);


			if ( m.find() )
				found = true;
			
			if (!found) {
				returnVariables.add(variables.get(var));
			}
		}
		return returnVariables;
	}
	/**
	 * checks the functiondoc to match the given regex
	 * @param doc
	 * @param regEx
	 * @return
	 */
	private boolean checkRegEx(String doc, String regEx)
	{
		Pattern p = Pattern.compile(regEx, Pattern.DOTALL);
		Matcher m = p.matcher(doc);
		return m.matches();
	}
	
	/**
	 * Extract the type from the string
	 * @param lineString
	 * @param startPos
	 * @return
	 */
	private String extractType(String lineString, int startPos)
	{
		boolean found = false;
		String type = "";
		int i = startPos;
		int count = 0; //count for "<" symbols 
		while (i < lineString.length())
		{
			if ( ( (lineString.charAt(i) == ' ') || (lineString.charAt(i) == '	') ) && (count == 0))
				{
					// check for whitespaces after found type
					return type;
				}
			
				switch (lineString.charAt(i)) {
					case '>': {
						count--;
						break;
					}
					case '<': {
						found = true;
						count++;
						break;
					}
				}	
			
			type += lineString.charAt(i);
			
			if ( found && (count == 0) )
				return type;
			
			i++;
		}
		return type;
	}

	public ArrayList<String[]> getFunctions(IDocument document)
	{
		ArrayList<String[]> functionList = new ArrayList<String[]>(); //list of all functions and where they start + end
		//start analyzing
		int line = 0;
		int startLine = 0; //line where the function that is searched now starts
		String fName = ""; //name of function 
		boolean foundfunction = false;
		Pattern wordMatchingPattern = Pattern.compile("[A-Za-z0-9\\_]");
		ITypedRegion[] linePartitioning = null;
		
		//start search for functions
		while (line < document.getNumberOfLines())
		{
			//get content of line

			String lineString = "";
			int offs = 0;
			try {
				lineString = document.get( document.getLineOffset(line) , document.getLineLength(line));
				linePartitioning= TextUtilities.computePartitioning(document, IModelicaPartitions.MODELICA_PARTITIONING, document.getLineOffset(line), document.getLineLength(line), false);
				if (line < document.getNumberOfLines()-1)
					offs = document.getLineOffset(line+1)-1;
				else offs = document.getLineOffset(document.getNumberOfLines()-1)-1;
			} catch (BadLocationException e) {
				e.printStackTrace();
				return null;
			}		
			
			Pattern p = Pattern.compile("\\sfunction\\s[A-Za-z0-9<>, ()]+\\s*=\\s*[A-Za-z0-9<>, ()]+\\s*;");
			Matcher m = p.matcher(lineString);
			if (m.find())
				{
					line++;
					continue;
				}
			boolean notJustComments = false;
			String newLineString  = "";
			for (int i = 0; i< linePartitioning.length; i++)
			{
				if ( (linePartitioning[i].getType().equals("__dftl_partition_content_type") ) && (linePartitioning[i].getLength() >= 1) && (linePartitioning[i].getOffset()<offs) )
				{
					
					try {
						newLineString += document.get(linePartitioning[i].getOffset(), linePartitioning[i].getLength());
						if (document.get(linePartitioning[i].getOffset(), linePartitioning[i].getLength()).trim().length() <= 0) 
							break;
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
					
					notJustComments = true;
					break;
				}
			}
			//look if it is the start of a function
			if (!foundfunction)
			{
				//check if "function" is just in comment

				if ( (newLineString.contains("function")) && (notJustComments) && (!(newLineString.contains("partial"))) && (!(newLineString.contains("uniontype"))))
				{
					foundfunction = true;
					startLine = line;
					//search for name
					int pos = lineString.indexOf("function")+8;
					boolean into = false;
					while ( (pos < lineString.length()) && !wordMatchingPattern.matcher("" + lineString.charAt(pos)).matches() )
					{
						into = true;
						pos++;
					}
					if (!into)
						continue;
					int pos2 = pos;
					while ( (pos2 < lineString.length()) && wordMatchingPattern.matcher("" + lineString.charAt(pos2)).matches() )
					{
						fName += lineString.charAt(pos2);
						pos2++;
					}

				}
			}
			else // look for end of function
			{
				if ( checkRegEx(lineString, "\\s*end\\s+"+fName+".*$") && (notJustComments) )
				{
					String[] tmp = { fName, Integer.toString(startLine),Integer.toString(line) };
					functionList.add( tmp );
					//reset
					foundfunction = false;
					startLine = 0;
					fName = "";
				}
			}
			
			line++; 
		}
		
		return functionList;
	
	}
	
	public ArrayList<String[]> getFunctions()
	{
		return getFunctions( getDocumentProvider().getDocument(getEditorInput()) );
	}
	
}
