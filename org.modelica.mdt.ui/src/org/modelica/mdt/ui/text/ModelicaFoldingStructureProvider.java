/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.ui.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Assert;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.projection.IProjectionListener;
import org.eclipse.jface.text.source.projection.IProjectionPosition;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.viewers.Viewer;

import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.modelica.mdt.core.IDefinitionLocation;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChangeListener;
import org.modelica.mdt.core.IModelicaExtends;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IParent;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.ui.editor.EditorUtility;
import org.modelica.mdt.ui.editor.ModelicaEditor;

/**
 * Updates the projection model of a class file or compilation unit.
 * <p>
 * Clients may instantiate or subclass. Subclasses must make sure to always call the superclass'
 * code when overriding methods that are marked with "subclasses may extend".
 * </p>
 * 
 * @since 3.0 (internal)
 * @since 3.2 (API)
 */
public class ModelicaFoldingStructureProvider {
	/**
	 * A context that contains the information needed to compute the folding structure of an
	 * {@link IModelicaSourceFile} or an {@link IClassFile}. Computed folding regions are collected
	 * via
	 * {@linkplain #addProjectionRange(ModelicaFoldingStructureProvider.ModelicaProjectionAnnotation, Position) addProjectionRange}.
	 */
	protected final class FoldingStructureComputationContext {
		private final ProjectionAnnotationModel fModel;
		private final IDocument fDocument;

		private final boolean fAllowCollapsing;

		private boolean fHasModelicaDocComment;
		private Map<ModelicaProjectionAnnotation, Position> fMap= new LinkedHashMap<ModelicaProjectionAnnotation, Position>();

		private FoldingStructureComputationContext(IDocument document, ProjectionAnnotationModel model, boolean allowCollapsing) {
			Assert.isNotNull(document);
			Assert.isNotNull(model);
			fDocument= document;
			fModel= model;
			fAllowCollapsing= allowCollapsing;
		}
		
		private boolean hasModelicaDocComment() {
			return fHasModelicaDocComment;
		}

		private void setHasModelicaDocComment() {
			fHasModelicaDocComment= true;
		}
		
		public ModelicaMultilineCommentScanner getScanner()
		{
			return new ModelicaMultilineCommentScanner();
		}
		/**
		 * Returns <code>true</code> if newly created folding regions may be collapsed,
		 * <code>false</code> if not. This is usually <code>false</code> when updating the
		 * folding structure while typing; it may be <code>true</code> when computing or restoring
		 * the initial folding structure.
		 * 
		 * @return <code>true</code> if newly created folding regions may be collapsed,
		 *         <code>false</code> if not
		 */
		public boolean allowCollapsing() {
			return fAllowCollapsing;
		}

		/**
		 * Returns the document which contains the code being folded.
		 * 
		 * @return the document which contains the code being folded
		 */
		private IDocument getDocument() {
			return fDocument;
		}

		private ProjectionAnnotationModel getModel() {
			return fModel;
		}
				
		/**
		 * Adds a projection (folding) region to this context. The created annotation / position
		 * pair will be added to the {@link ProjectionAnnotationModel} of the
		 * {@link ProjectionViewer} of the editor.
		 * 
		 * @param annotation the annotation to add
		 * @param position the corresponding position
		 */
		public void addProjectionRange(ModelicaProjectionAnnotation annotation, Position position) {
			fMap.put(annotation, position);
		}

		/**
		 * Returns <code>true</code> if header comments should be collapsed.
		 * 
		 * @return <code>true</code> if header comments should be collapsed
		 */
		public boolean collapseFileHeaderComments() {
			return fAllowCollapsing && fCollapseHeaderComments;
		}

		/**
		 * Returns <code>true</code> if import containers should be collapsed.
		 * 
		 * @return <code>true</code> if import containers should be collapsed
		 */
		public boolean collapseImportContainer() {
			return fAllowCollapsing && fCollapseImportContainer;
		}

		/**
		 * Returns <code>true</code> if inner types should be collapsed.
		 * 
		 * @return <code>true</code> if inner types should be collapsed
		 */
		public boolean collapseClasses() {
			return fAllowCollapsing && fCollapseClasses;
		}

		/**
		 * Returns <code>true</code> if modelicadoc comments should be collapsed.
		 * 
		 * @return <code>true</code> if modelicadoc comments should be collapsed
		 */
		public boolean collapseModelicaDoc() {
			return fAllowCollapsing && fCollapseModelicaDoc;
		}

		/**
		 * Returns <code>true</code> if methods should be collapsed.
		 * 
		 * @return <code>true</code> if methods should be collapsed
		 */
		public boolean collapseMembers() {
			return fAllowCollapsing && fCollapseMembers;
		}
	}
	
	/**
	 * A {@link ProjectionAnnotation} for modelica code.
	 */
	protected static final class ModelicaProjectionAnnotation extends ProjectionAnnotation {

		private IModelicaElement fModelicaElement;
		private boolean fIsComment;

		/**
		 * Creates a new projection annotation.
		 * 
		 * @param isCollapsed <code>true</code> to set the initial state to collapsed,
		 *        <code>false</code> to set it to expanded
		 * @param element the modelica element this annotation refers to
		 * @param isComment <code>true</code> for a foldable comment, <code>false</code> for a
		 *        foldable code element
		 */
		public ModelicaProjectionAnnotation(boolean isCollapsed, IModelicaElement element, boolean isComment) {
			super(isCollapsed);
			fModelicaElement = element;
			fIsComment = isComment;
		}

		IModelicaElement getElement() {
			return fModelicaElement;
		}

		void setElement(IModelicaElement element) {
			fModelicaElement= element;
		}

		boolean isComment() {
			return fIsComment;
		}

		void setIsComment(boolean isComment) {
			fIsComment= isComment;
		}

		/*
		 * @see modelica.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ModelicaProjectionAnnotation:\n" + //$NON-NLS-1$
					"\telement: \t"+ fModelicaElement.toString() + "\n" + //$NON-NLS-1$ //$NON-NLS-2$
					"\tcollapsed: \t" + isCollapsed() + "\n" + //$NON-NLS-1$ //$NON-NLS-2$
					"\tcomment: \t" + isComment() + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	

	private static final class Tuple {
		ModelicaProjectionAnnotation annotation;
		Position position;
		Tuple(ModelicaProjectionAnnotation annotation, Position position) {
			this.annotation= annotation;
			this.position= position;
		}
	}

	/**
	 * Filter for annotations.
	 */
	private static interface Filter {
		boolean match(ModelicaProjectionAnnotation annotation);
	}
	
	/**
	 * Matches comments.
	 */
	private static final class CommentFilter implements Filter {
		@Override
		public boolean match(ModelicaProjectionAnnotation annotation) {
			if (annotation.isComment() && !annotation.isMarkedDeleted()) {
				return true;
			}
			return false;
		}
	}

	/**
	 * Matches members.
	 */
	private static final class MemberFilter implements Filter {
		@Override
		public boolean match(ModelicaProjectionAnnotation annotation) {
			if (!annotation.isComment() && !annotation.isMarkedDeleted()) {
				IModelicaElement element= annotation.getElement();
				if (element instanceof IModelicaElement) {
						return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * Matches modelica elements contained in a certain set.
	 */
	private static final class ModelicaElementSetFilter implements Filter {
		private final Set<IModelicaElement> fSet;
		private final boolean fMatchCollapsed;

		private ModelicaElementSetFilter(Set<IModelicaElement> set, boolean matchCollapsed) {
			fSet= set;
			fMatchCollapsed= matchCollapsed;
		}

		@Override
		public boolean match(ModelicaProjectionAnnotation annotation) {
			boolean stateMatch= fMatchCollapsed == annotation.isCollapsed();
			if (stateMatch && !annotation.isComment() && !annotation.isMarkedDeleted()) {
				IModelicaElement element= annotation.getElement();
				if (fSet.contains(element)) {
					return true;
				}
			}
			return false;
		}
	}

	private class ElementChangedListener implements IModelicaElementChangeListener
	{
		@Override
		public void setViewer(Viewer v)
		{
			
		}
		/*
		 * 
		 */
		@Override
		public void elementsChanged(Collection<IModelicaElementChange> changes){
			if (!(changes instanceof IModelicaSourceFile)) return; 
				fUpdatingCount++;
				try {
					update(createContext(false));
				} finally {
					fUpdatingCount--;
				}
			}
		}

	/**
	 * Projection position that will return two foldable regions: one folding away
	 * the region from after the '/**' to the beginning of the content, the other
	 * from after the first content line until after the comment.
	 */
	private final class CommentPosition extends Position implements IProjectionPosition {
		CommentPosition(int offset, int length) {
			super(offset, length);
		}

		/*
		 * @see org.eclipse.jface.text.source.projection.IProjectionPosition#computeFoldingRegions(org.eclipse.jface.text.IDocument)
		 */
		@Override
		public IRegion[] computeProjectionRegions(IDocument document) throws BadLocationException {
			DocumentCharacterIterator sequence= new DocumentCharacterIterator(document, offset, offset + length);
			int prefixEnd= 0;
			int contentStart= findFirstContent(sequence, prefixEnd);

			int firstLine= document.getLineOfOffset(offset + prefixEnd);
			int captionLine= document.getLineOfOffset(offset + contentStart);
			int lastLine= document.getLineOfOffset(offset + length);

			Assert.isTrue(firstLine <= captionLine, "first folded line is greater than the caption line"); //$NON-NLS-1$
			Assert.isTrue(captionLine <= lastLine, "caption line is greater than the last folded line"); //$NON-NLS-1$

			IRegion preRegion;
			if (firstLine < captionLine) {
//				preRegion= new Region(offset + prefixEnd, contentStart - prefixEnd);
				int preOffset= document.getLineOffset(firstLine);
				IRegion preEndLineInfo= document.getLineInformation(captionLine);
				int preEnd= preEndLineInfo.getOffset();
				preRegion= new Region(preOffset, preEnd - preOffset);
			} else {
				preRegion= null;
			}

			if (captionLine < lastLine) {
				int postOffset= document.getLineOffset(captionLine + 1);
				IRegion postRegion= new Region(postOffset, offset + length - postOffset);

				if (preRegion == null)
					return new IRegion[] { postRegion };

				return new IRegion[] { preRegion, postRegion };
			}

			if (preRegion != null)
				return new IRegion[] { preRegion };

			return null;
		}

		/**
		 * Finds the offset of the first identifier part within <code>content</code>.
		 * Returns 0 if none is found.
		 *
		 * @param content the content to search
		 * @return the first index of a unicode identifier part, or zero if none can
		 *         be found
		 */
		private int findFirstContent(final CharSequence content, int prefixEnd) {
			int lenght= content.length();
			for (int i= prefixEnd; i < lenght; i++) {
				if (Character.isUnicodeIdentifierPart(content.charAt(i)))
					return i;
			}
			return 0;
		}

//		/**
//		 * Finds the offset of the first identifier part within <code>content</code>.
//		 * Returns 0 if none is found.
//		 *
//		 * @param content the content to search
//		 * @return the first index of a unicode identifier part, or zero if none can
//		 *         be found
//		 */
//		private int findPrefixEnd(final CharSequence content) {
//			// return the index after the leading '/*' or '/**'
//			int len= content.length();
//			int i= 0;
//			while (i < len && isWhiteSpace(content.charAt(i)))
//				i++;
//			if (len >= i + 2 && content.charAt(i) == '/' && content.charAt(i + 1) == '*')
//				if (len >= i + 3 && content.charAt(i + 2) == '*')
//					return i + 3;
//				else
//					return i + 2;
//			else
//				return i;
//		}
//
//		private boolean isWhiteSpace(char c) {
//			return c == ' ' || c == '\t';
//		}

		/*
		 * @see org.eclipse.jface.text.source.projection.IProjectionPosition#computeCaptionOffset(org.eclipse.jface.text.IDocument)
		 */
		@Override
		public int computeCaptionOffset(IDocument document) {
//			return 0;
			DocumentCharacterIterator sequence= new DocumentCharacterIterator(document, offset, offset + length);
			return findFirstContent(sequence, 0);
		}
	}

	/**
	 * Projection position that will return two foldable regions: one folding away
	 * the lines before the one containing the simple name of the modelica element, one
	 * folding away any lines after the caption.
	 */
	private static final class ModelicaElementPosition extends Position implements IProjectionPosition {

		private IModelicaElement fElement;

		public ModelicaElementPosition(int offset, int length, IModelicaElement element) {
			super(offset, length);
			Assert.isNotNull(element);
			fElement= element;
		}
		
		public void setMember(IModelicaElement member) {
			Assert.isNotNull(member);
			fElement= member;
		}
		
		/*
		 * @see org.eclipse.jface.text.source.projection.IProjectionPosition#computeFoldingRegions(org.eclipse.jface.text.IDocument)
		 */
		@Override
		public IRegion[] computeProjectionRegions(IDocument document) throws BadLocationException {
			int nameStart= offset;
			@SuppressWarnings("unused")
			int nameEnd = offset+length;
			try {
				IDefinitionLocation nameRange= fElement.getLocation();
				if (nameRange != null)
				{
					nameStart= 
						document.getLineOffset(nameRange.getSourceRegion().getStartLine()-1)+
						nameRange.getSourceRegion().getStartColumn();
					nameEnd= 
						document.getLineOffset(nameRange.getSourceRegion().getEndLine()-1)+
						nameRange.getSourceRegion().getEndColumn();	
				}
			} catch (Exception e) {
				// ignore and use default
			}

			int firstLine= document.getLineOfOffset(offset);
			int captionLine= document.getLineOfOffset(nameStart);
			int lastLine= document.getLineOfOffset(offset + length);

			IRegion preRegion;
			if (firstLine < captionLine) {
				int preOffset= document.getLineOffset(firstLine);
				IRegion preEndLineInfo= document.getLineInformation(captionLine);
				int preEnd= preEndLineInfo.getOffset();
				preRegion= new Region(preOffset, preEnd - preOffset);
			} else {
				preRegion= null;
			}

			if (captionLine < lastLine) {
				int postOffset= document.getLineOffset(captionLine + 1);
				IRegion postRegion= new Region(postOffset, offset + length - postOffset);

				if (preRegion == null)
					return new IRegion[] { postRegion };

				return new IRegion[] { preRegion, postRegion };
			}

			if (preRegion != null)
				return new IRegion[] { preRegion };

			return null;
		}

		/*
		 * @see org.eclipse.jface.text.source.projection.IProjectionPosition#computeCaptionOffset(org.eclipse.jface.text.IDocument)
		 */
		@Override
		public int computeCaptionOffset(IDocument document) throws BadLocationException {
			int nameStart= offset;
			try {
				// need a reconcile here?
				IDefinitionLocation nameRange= fElement.getLocation();
				if (nameRange != null)
					nameStart= 
						document.getLineOffset(nameRange.getSourceRegion().getStartLine()-1)+
						nameRange.getSourceRegion().getStartColumn();
			} catch (Exception e) {
				// ignore and use default
			}
			return nameStart - offset;
		}

	}
	
	/**
	 * Internal projection listener.
	 */
	private final class ProjectionListener implements IProjectionListener {
		private ProjectionViewer fViewer;

		/**
		 * Registers the listener with the viewer.
		 * 
		 * @param viewer the viewer to register a listener with
		 */
		public ProjectionListener(ProjectionViewer viewer) {
			Assert.isLegal(viewer != null);
			fViewer= viewer;
			fViewer.addProjectionListener(this);
		}
		
		/**
		 * Disposes of this listener and removes the projection listener from the viewer.
		 */
		public void dispose() {
			if (fViewer != null) {
				fViewer.removeProjectionListener(this);
				fViewer= null;
			}
		}
		
		/*
		 * @see org.eclipse.jface.text.source.projection.IProjectionListener#projectionEnabled()
		 */
		@Override
		public void projectionEnabled() {
			handleProjectionEnabled();
		}

		/*
		 * @see org.eclipse.jface.text.source.projection.IProjectionListener#projectionDisabled()
		 */
		@Override
		public void projectionDisabled() {
			handleProjectionDisabled();
		}
	}
	
	/* context and listeners */
	private ModelicaEditor fEditor;
	private ProjectionListener fProjectionListener;
	private IModelicaElement fInput;
	private IModelicaElementChangeListener fElementListener;

	/* preferences */
	private boolean fCollapseModelicaDoc= false;
	private boolean fCollapseImportContainer= true;
	private boolean fCollapseClasses= false;
	private boolean fCollapseMembers= false;
	private boolean fCollapseHeaderComments= true;

	/* filters */
	/** Member filter, matches nested members (but not top-level types). */
	private final Filter fMemberFilter = new MemberFilter();
	/** Comment filter, matches comments. */
	private final Filter fCommentFilter = new CommentFilter();

	@SuppressWarnings("unused")
	private volatile int fUpdatingCount= 0;

	/**
	 * Creates a new folding provider. It must be
	 * {@link #install(ITextEditor, ProjectionViewer) installed} on an editor/viewer pair before it
	 * can be used, and {@link #uninstall() uninstalled} when not used any longer.
	 * <p>
	 * The projection state may be reset by calling {@link #initialize()}.
	 * </p>
	 */
	public ModelicaFoldingStructureProvider() {
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Subclasses may extend.
	 * </p>
	 * 
	 * @param editor {@inheritDoc}
	 * @param viewer {@inheritDoc}
	 */
	public void install(ITextEditor editor, ProjectionViewer viewer) {
		Assert.isLegal(editor != null);
		Assert.isLegal(viewer != null);

		internalUninstall();
		
		if (editor instanceof ModelicaEditor) {
			fProjectionListener= new ProjectionListener(viewer);
			fEditor= (ModelicaEditor)editor;
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Subclasses may extend.
	 * </p>
	 */
	public void uninstall() {
		internalUninstall();
	}
	
	/**
	 * Internal implementation of {@link #uninstall()}.
	 */
	private void internalUninstall() {
		if (isInstalled()) {
			handleProjectionDisabled();
			fProjectionListener.dispose();
			fProjectionListener= null;
			fEditor= null;
		}
	}

	/**
	 * Returns <code>true</code> if the provider is installed, <code>false</code> otherwise.
	 * 
	 * @return <code>true</code> if the provider is installed, <code>false</code> otherwise
	 */
	protected final boolean isInstalled() {
		return fEditor != null;
	}

	/**
	 * Called whenever projection is enabled, for example when the viewer issues a
	 * {@link IProjectionListener#projectionEnabled() projectionEnabled} message. When the provider
	 * is already enabled when this method is called, it is first
	 * {@link #handleProjectionDisabled() disabled}.
	 * <p>
	 * Subclasses may extend.
	 * </p>
	 */
	protected void handleProjectionEnabled() {
		// http://home.ott.oti.com/teams/wswb/anon/out/vms/index.html
		// projectionEnabled messages are not always paired with projectionDisabled
		// i.e. multiple enabled messages may be sent out.
		// we have to make sure that we disable first when getting an enable
		// message.
		handleProjectionDisabled();

		if (isInstalled()) {
			initialize();
			fElementListener= new ElementChangedListener();
			ModelicaCore.getModelicaRoot().addModelicaElementChangeListener(fElementListener);
		}
	}

	/**
	 * Called whenever projection is disabled, for example when the provider is
	 * {@link #uninstall() uninstalled}, when the viewer issues a
	 * {@link IProjectionListener#projectionDisabled() projectionDisabled} message and before
	 * {@link #handleProjectionEnabled() enabling} the provider. Implementations must be prepared to
	 * handle multiple calls to this method even if the provider is already disabled.
	 * <p>
	 * Subclasses may extend.
	 * </p>
	 */
	protected void handleProjectionDisabled() {
		if (fElementListener != null) {
			ModelicaCore.getModelicaRoot().removeModelicaElementChangeListener(fElementListener);
			fElementListener= null;
		}
	}

	/*
	 * @see org.modelica.mdt.ui.text.folding.IModelicaFoldingStructureProvider#initialize()
	 */
	public final void initialize() {
		fUpdatingCount++;
		try {
			update(createInitialContext());
		} finally {
			fUpdatingCount--;
		}
	}

	private FoldingStructureComputationContext createInitialContext() {
		initializePreferences();
		fInput= getInputElement();
		if (fInput == null)
			return null;
		
		return createContext(true);
	}

	private FoldingStructureComputationContext createContext(boolean allowCollapse) {
		if (!isInstalled())
			return null;
		ProjectionAnnotationModel model= getModel();
		if (model == null)
			return null;
		IDocument doc= getDocument();
		if (doc == null)
			return null;
				
		return new FoldingStructureComputationContext(doc, model, allowCollapse);
	}
	
	private IModelicaElement getInputElement() {
		if (fEditor == null)
			return null;
		return EditorUtility.getEditorInputModelicaElement(fEditor);
	}

	private void initializePreferences() {
		fCollapseImportContainer= false;
		fCollapseModelicaDoc= true;
		fCollapseHeaderComments= true;
	}

	private void update(FoldingStructureComputationContext ctx) {
		if (ctx == null) {
			return;
		}

		Map<ModelicaProjectionAnnotation, Position> additions= new HashMap<ModelicaProjectionAnnotation, Position>();
		List<ModelicaProjectionAnnotation> deletions= new ArrayList<ModelicaProjectionAnnotation>();
		List<ModelicaProjectionAnnotation> updates= new ArrayList<ModelicaProjectionAnnotation>();

		computeFoldingStructure(ctx);
		Map<ModelicaProjectionAnnotation, Position> newStructure= ctx.fMap;
		Map<IModelicaElement, List<Tuple>> oldStructure= computeCurrentStructure(ctx);

		for (Map.Entry<ModelicaProjectionAnnotation, Position> entry : newStructure.entrySet()) {
			ModelicaProjectionAnnotation newAnnotation = entry.getKey();
			Position newPosition = entry.getValue();

			IModelicaElement element = newAnnotation.getElement();
			List<Tuple> annotations = oldStructure.get(element);
			if (annotations == null) {
				additions.put(newAnnotation, newPosition);
			}
			else {
				Iterator<Tuple> x= annotations.iterator();
				boolean matched= false;
				while (x.hasNext()) {
					Tuple tuple= x.next();
					ModelicaProjectionAnnotation existingAnnotation= tuple.annotation;
					Position existingPosition= tuple.position;
					if (newAnnotation.isComment() == existingAnnotation.isComment()) {
						boolean updateCollapsedState= ctx.allowCollapsing() && existingAnnotation.isCollapsed() != newAnnotation.isCollapsed();
						if (existingPosition != null && (!newPosition.equals(existingPosition) || updateCollapsedState)) {
							existingPosition.setOffset(newPosition.getOffset());
							existingPosition.setLength(newPosition.getLength());
							if (updateCollapsedState)
								if (newAnnotation.isCollapsed()) {
									existingAnnotation.markCollapsed();
								}
								else {
									existingAnnotation.markExpanded();
								}
							updates.add(existingAnnotation);
						}
						matched= true;
						x.remove();
						break;
					}
				}
				if (!matched) {
					additions.put(newAnnotation, newPosition);
				}
				if (annotations.isEmpty()) {
					oldStructure.remove(element);
				}
			}
		}

		for (List<Tuple> list : oldStructure.values()) {
			for (Tuple tuple : list) {
				ModelicaProjectionAnnotation annotation = tuple.annotation;
				deletions.add(annotation);
			}
		}

		match(deletions, additions, updates, ctx);

		Annotation[] deletedArray= deletions.toArray(new Annotation[0]);
		Annotation[] changedArray= updates.toArray(new Annotation[0]);
		ctx.getModel().modifyAnnotations(deletedArray, additions, changedArray);
    }
	
	private void computeFoldingStructure(FoldingStructureComputationContext ctx) {
		try {
			IParent parent= (IParent) fInput;			
			IRegion fileHeaderComment = computerFileHeaderComment(ctx);
			if (fileHeaderComment != null)
			{
				IRegion normalized= alignRegion(fileHeaderComment, ctx);
				if (normalized != null)
				{
					Position position= createCommentPosition(normalized);
					if (position != null)
						ctx.addProjectionRange(new ModelicaProjectionAnnotation(true, (IModelicaElement)parent, true), position);
				}
			}			
			computeFoldingStructure(parent.getChildren(), ctx, true);
		} catch (Exception x) {
		}
	}

	private void computeFoldingStructure(Collection<? extends IModelicaElement> elements, FoldingStructureComputationContext ctx, boolean dive) {
			for (IModelicaElement element : elements) 
			{
				if (!(element instanceof IModelicaComponent) && !(element instanceof IModelicaExtends) &&
					!(element instanceof IModelicaImport)	) // don't add components/extends/imports 
					computeFoldingStructure(element, ctx);
				if (element instanceof IParent) {
					IParent parent= (IParent) element;
					try{ computeFoldingStructure(parent.getChildren(), ctx, dive); } catch(Exception e) { /* do nothing */ }
				}
			}
	}

	/**
	 * Computes the folding structure for a given {@link IModelicaElement modelica element}. Computed
	 * projection annotations are
	 * {@link ModelicaFoldingStructureProvider.FoldingStructureComputationContext#addProjectionRange(ModelicaFoldingStructureProvider.ModelicaProjectionAnnotation, Position) added}
	 * to the computation context.
	 * <p>
	 * Subclasses may extend or replace. The default implementation creates projection annotations
	 * for the following elements:
	 * <ul>
	 * <li>the modelicadoc comments of any element/class</li>
	 * <li>header comments (modelicadoc or multi-line comments appearing after the first type's
	 * modelicadoc or before the package or import declarations).</li>
	 * </ul>
	 * </p>
	 * 
	 * @param element the modelica element to compute the folding structure for
	 * @param ctx the computation context
	 */
	protected void computeFoldingStructure(IModelicaElement element, FoldingStructureComputationContext ctx) {

		boolean collapse= false;
		boolean collapseCode= true;
		if (element instanceof IModelicaClass) 
		{
				collapse= ctx.collapseClasses();
		}

		try
		{
			IRegion[] regions= computeProjectionRanges(element.getLocation(), ctx);
			if (regions.length > 0) {
				// comments
				for (int i= 1; i < regions.length; i++) {
					IRegion normalized= alignRegion(regions[i], ctx);
					if (normalized != null) {
						Position position= createCommentPosition(normalized);
						if (position != null) {
							boolean commentCollapse;
							if (i == 1 && (regions.length > 2 || ctx.hasModelicaDocComment())) {
								commentCollapse= ctx.collapseModelicaDoc();
							} else {
								commentCollapse= ctx.collapseModelicaDoc();
							}
							ctx.addProjectionRange(new ModelicaProjectionAnnotation(commentCollapse, null, true), position);
						}
					}
				}
				// code
				if (collapseCode) {
					IRegion normalized= alignRegion(regions[0], ctx);
					if (normalized != null) {
						Position position= element instanceof IModelicaElement ? 
								createModelicaElementPosition(normalized, element) : 
								createCommentPosition(normalized);
						if (position != null)
							ctx.addProjectionRange(new ModelicaProjectionAnnotation(collapse, element, false), position);
					}
				}			
			}
		}
		catch(Exception e)
		{

		}
	}


	/**
	 * Computes the projection ranges for a given <code>IDefinitionLocation</code>. More than one
	 * range or none at all may be returned. If there are no foldable regions, an empty array is
	 * returned.
	 * <p>
	 * The last region in the returned array (if not empty) describes the region for the modelica
	 * element that implements the source reference. Any preceding regions describe modelicadoc comments
	 * of that modelica element.
	 * </p>
	 * 
	 * @param reference a modelica element that is a source reference
	 * @param ctx the folding context
	 * @return the regions to be folded
	 */
	protected final IRegion[] computeProjectionRanges(IDefinitionLocation reference, FoldingStructureComputationContext ctx) {
		try {
				int offsetStart = ctx.getDocument().getLineOffset(
						reference.getSourceRegion().getStartLine()-1)+
						reference.getSourceRegion().getStartColumn();
				int offsetEnd   = ctx.getDocument().getLineOffset(
						reference.getSourceRegion().getEndLine()-1) +
						reference.getSourceRegion().getEndColumn();													

				IRegion range = new Region(offsetStart, offsetEnd - offsetStart);

				List<IRegion> regions= new ArrayList<IRegion>();
				
				regions.add(range); // add the element				
				
				IRegion headerComment= computeModelicaDocComment(range, ctx);
				if (headerComment != null) {
					regions.add(headerComment);
					ctx.setHasModelicaDocComment();
				}

				IRegion[] result= new IRegion[regions.size()];
				regions.toArray(result);
				return result;
		}
		catch (Exception e) {
		}

		return new IRegion[0];
	}
	
	public IRegion computerFileHeaderComment(FoldingStructureComputationContext ctx)
	{
		int offset = 0; int length = -1; IDocument d = null;
		try{		
			d = getDocument();
			/* search for multiline comments at the start of the file */
			// eat whitespace!
			while (Character.isWhitespace((d.getChar(offset)))) offset++;
			if (d.getChar(offset) == '/' && d.getChar(offset+1) == '*')
			{
				// found the /* start, search fot the end!
				length = 0;
				while (!(d.getChar(offset+length) == '*' && d.getChar(offset+length+1) == '/')) length++;
			}
		}
		catch (BadLocationException e){ return null; }			
		if (length != -1) {
			return new Region(offset, length+1);
		}
		return null;
	}
	
	

	private IRegion computeModelicaDocComment(IRegion range, FoldingStructureComputationContext ctx)  
	{
		/* now add collapsed documentation! */
		/* search for next word which should be an identifier */
		try{
			int offset = range.getOffset();
			int length = -1;
			IDocument d = getDocument();
			while (Character.isJavaIdentifierPart(d.getChar(offset))) offset++;
			/* kill the space */				
			while (Character.isWhitespace((d.getChar(offset)))) offset++;
			/* search for next word which should be an identifier */
			while (Character.isJavaIdentifierPart(d.getChar(offset))) offset++;
			/* kill the space */				
			while (Character.isWhitespace((d.getChar(offset)))) offset++;
			if (d.getChar(offset) == '"') /* found the doc */
			{
				offset++;
				length = 0;
				while (d.getChar(offset+length) != '"' || 
					   (d.getChar(offset+length-1) == '\\' && d.getChar(offset+length) == '"')) length++;
				int elementStartLine = d.getLineOfOffset(range.getOffset());
				int commentStartLine = d.getLineOfOffset(offset-1);
				if (elementStartLine == commentStartLine)
					return null;
				return new Region(offset-1, length+1);
			}
		}
		catch (BadLocationException e){ /* ignore */ }	
		
		return null;
	}
	
	/**
	 * Creates a comment folding position from an
	 * {@link #alignRegion(IRegion, ModelicaFoldingStructureProvider.FoldingStructureComputationContext) aligned}
	 * region.
	 * 
	 * @param aligned an aligned region
	 * @return a folding position corresponding to <code>aligned</code>
	 */
	protected final Position createCommentPosition(IRegion aligned) {
		return new CommentPosition(aligned.getOffset(), aligned.getLength());
	}
	
	/**
	 * Creates a folding position that remembers its member from an
	 * {@link #alignRegion(IRegion, ModelicaFoldingStructureProvider.FoldingStructureComputationContext) aligned}
	 * region.
	 * 
	 * @param aligned an aligned region
	 * @param member the member to remember
	 * @return a folding position corresponding to <code>aligned</code>
	 */
	protected final Position createModelicaElementPosition(IRegion aligned, IModelicaElement element) {
		return new ModelicaElementPosition(aligned.getOffset(), aligned.getLength(), element);
	}

	/**
	 * Aligns <code>region</code> to start and end at a line offset. The region's start is
	 * decreased to the next line offset, and the end offset increased to the next line start or the
	 * end of the document. <code>null</code> is returned if <code>region</code> is
	 * <code>null</code> itself or does not comprise at least one line delimiter, as a single line
	 * cannot be folded.
	 * 
	 * @param region the region to align, may be <code>null</code>
	 * @param ctx the folding context
	 * @return a region equal or greater than <code>region</code> that is aligned with line
	 *         offsets, <code>null</code> if the region is too small to be foldable (e.g. covers
	 *         only one line)
	 */
	protected final IRegion alignRegion(IRegion region, FoldingStructureComputationContext ctx) {
		if (region == null)
			return null;
		
		IDocument document= ctx.getDocument();
		
		try {
			
			int start= document.getLineOfOffset(region.getOffset());
			int end= document.getLineOfOffset(region.getOffset() + region.getLength());
			if (start >= end)
				return null;
			
			int offset= document.getLineOffset(start);
			int endOffset;
			if (document.getNumberOfLines() > end + 1)
				endOffset= document.getLineOffset(end + 1);
			else
				endOffset= document.getLineOffset(end) + document.getLineLength(end);
			
			return new Region(offset, endOffset - offset);
			
		} catch (BadLocationException x) {
			// concurrent modification
			return null;
		}
	}
	
	private ProjectionAnnotationModel getModel() {
		//return ((ProjectionViewer)((ModelicaEditor)(fEditor)).getViewer()).getProjectionAnnotationModel();
		return (ProjectionAnnotationModel) fEditor.getAdapter(ProjectionAnnotationModel.class);
	}

	private IDocument getDocument() {
		ModelicaEditor editor= fEditor;
		if (editor == null)
			return null;
		
		IDocumentProvider provider= editor.getDocumentProvider();
		if (provider == null)
			return null;
		
		return provider.getDocument(editor.getEditorInput());
	}
	
	/**
	 * Matches deleted annotations to changed or added ones. A deleted
	 * annotation/position tuple that has a matching addition / change
	 * is updated and marked as changed. The matching tuple is not added
	 * (for additions) or marked as deletion instead (for changes). The
	 * result is that more annotations are changed and fewer get
	 * deleted/re-added.
	 */
	private void match(List<ModelicaProjectionAnnotation> deletions, Map<ModelicaProjectionAnnotation, Position> additions,
			List<ModelicaProjectionAnnotation> changes, FoldingStructureComputationContext ctx) {
		if (deletions.isEmpty() || (additions.isEmpty() && changes.isEmpty()))
			return;

		List<ModelicaProjectionAnnotation> newDeletions= new ArrayList<ModelicaProjectionAnnotation>();
		List<ModelicaProjectionAnnotation> newChanges= new ArrayList<ModelicaProjectionAnnotation>();

		Iterator<ModelicaProjectionAnnotation> deletionIterator= deletions.iterator();
		while (deletionIterator.hasNext()) {
			ModelicaProjectionAnnotation deleted= deletionIterator.next();
			Position deletedPosition= ctx.getModel().getPosition(deleted);
			if (deletedPosition == null) {
				continue;
			}
			
			Tuple deletedTuple= new Tuple(deleted, deletedPosition);

			Tuple match= findMatch(deletedTuple, changes, null, ctx);
			boolean addToDeletions= true; 
			if (match == null) {
				match= findMatch(deletedTuple, additions.keySet(), additions, ctx);
				addToDeletions= false;
			}
			
			if (match != null) {
				IModelicaElement element= match.annotation.getElement();
				deleted.setElement(element);
				deletedPosition.setLength(match.position.getLength());
				if (deletedPosition instanceof ModelicaElementPosition && element instanceof IModelicaElement) {
					ModelicaElementPosition jep= (ModelicaElementPosition) deletedPosition;
					jep.setMember(element);
				}

				deletionIterator.remove();
				newChanges.add(deleted);

				if (addToDeletions) {
					newDeletions.add(match.annotation);
				}
			}
		}

		deletions.addAll(newDeletions);
		changes.addAll(newChanges);
	}

	/**
	 * Finds a match for <code>tuple</code> in a collection of
	 * annotations. The positions for the
	 * <code>ModelicaProjectionAnnotation</code> instances in
	 * <code>annotations</code> can be found in the passed
	 * <code>positionMap</code> or <code>fCachedModel</code> if
	 * <code>positionMap</code> is <code>null</code>.
	 * <p>
	 * A tuple is said to match another if their annotations have the
	 * same comment flag and their position offsets are equal.
	 * </p>
	 * <p>
	 * If a match is found, the annotation gets removed from
	 * <code>annotations</code>.
	 * </p>
	 * 
	 * @param tuple the tuple for which we want to find a match
	 * @param annotations collection of
	 *        <code>ModelicaProjectionAnnotation</code>
	 * @param positionMap a <code>Map&lt;Annotation, Position&gt;</code>
	 *        or <code>null</code>
	 * @return a matching tuple or <code>null</code> for no match
	 */
	private Tuple findMatch(Tuple tuple, Collection<ModelicaProjectionAnnotation> annotations, Map<ModelicaProjectionAnnotation, Position> positionMap, FoldingStructureComputationContext ctx) {
		Iterator<ModelicaProjectionAnnotation> it= annotations.iterator();
		while (it.hasNext()) {
			ModelicaProjectionAnnotation annotation= it.next();
			if (tuple.annotation.isComment() == annotation.isComment()) {
				Position position= positionMap == null ? ctx.getModel().getPosition(annotation) : positionMap.get(annotation);
				if (position == null)
					continue;

				if (tuple.position.getOffset() == position.getOffset()) {
					it.remove();
					return new Tuple(annotation, position);
				}
			}
		}
		
		return null;
	}

	private Map<IModelicaElement, List<Tuple>> computeCurrentStructure(FoldingStructureComputationContext ctx) {
		Map<IModelicaElement, List<Tuple>> map= new HashMap<IModelicaElement, List<Tuple>>();
		ProjectionAnnotationModel model= ctx.getModel();
		Iterator<?> e= model.getAnnotationIterator();
		while (e.hasNext()) {
			Object annotation= e.next();
			if (annotation instanceof ModelicaProjectionAnnotation) {
				ModelicaProjectionAnnotation modelica= (ModelicaProjectionAnnotation) annotation;
				Position position= model.getPosition(modelica);
				Assert.isNotNull(position);
				List<Tuple> list = map.get(modelica.getElement());
				if (list == null) {
					list = new ArrayList<Tuple>(2);
					map.put(modelica.getElement(), list);
				}
				list.add(new Tuple(modelica, position));
			}
		}

		Comparator<Tuple> comparator= new Comparator<Tuple>() {
			@Override
			public int compare(Tuple o1, Tuple o2) {
				return o1.position.getOffset() - o2.position.getOffset();
			}
		};
		for (List<Tuple> list : map.values()) {
			Collections.sort(list, comparator);
		}
		return map;
	}
	
	/*
	 * @see IModelicaFoldingStructureProviderExtension#collapseMembers()
	 * @since 3.2
	 */
	public final void collapseMembers() {
		modifyFiltered(fMemberFilter, false);
	}
	
	/*
	 * @see IModelicaFoldingStructureProviderExtension#collapseComments()
	 * @since 3.2
	 */
	public final void collapseComments() {
		modifyFiltered(fCommentFilter, false);
	}

	/*
	 * @see org.modelica.mdt.ui.text.folding.IModelicaFoldingStructureProviderExtension#collapseElements(org.modelica.mdt.core.IModelicaElement[])
	 */
	public final void collapseElements(IModelicaElement[] elements) {
		Set<IModelicaElement> set= new HashSet<IModelicaElement>(Arrays.asList(elements));
		modifyFiltered(new ModelicaElementSetFilter(set, false), false);
	}

	/*
	 * @see org.modelica.mdt.ui.text.folding.IModelicaFoldingStructureProviderExtension#expandElements(org.modelica.mdt.core.IModelicaElement[])
	 */
	public final void expandElements(IModelicaElement[] elements) {
		Set<IModelicaElement> set= new HashSet<IModelicaElement>(Arrays.asList(elements));
		modifyFiltered(new ModelicaElementSetFilter(set, true), true);
	}

	/**
	 * Collapses or expands all annotations matched by the passed filter.
	 * 
	 * @param filter the filter to use to select which annotations to collapse
	 * @param expand <code>true</code> to expand the matched annotations, <code>false</code> to
	 *        collapse them
	 */
	private void modifyFiltered(Filter filter, boolean expand) {
		if (!isInstalled())
			return;

		ProjectionAnnotationModel model= getModel();
		if (model == null)
			return;
		
		List<ModelicaProjectionAnnotation> modified= new ArrayList<ModelicaProjectionAnnotation>();
		Iterator<?> iter= model.getAnnotationIterator();
		while (iter.hasNext()) {
			Object annotation= iter.next();
			if (annotation instanceof ModelicaProjectionAnnotation) {
				ModelicaProjectionAnnotation modelica= (ModelicaProjectionAnnotation) annotation;
				
				if (expand == modelica.isCollapsed() && filter.match(modelica)) {
					if (expand) {
						modelica.markExpanded();
					}
					else {
						modelica.markCollapsed();
					}
					modified.add(modelica);
				}

			}
		}
		
		model.modifyAnnotations(null, null, modified.toArray(new Annotation[0]));
	}
}
