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
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.TextOperationAction;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChangeListener;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IParent;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.ui.text.IModelicaPartitions;
import org.modelica.mdt.ui.text.ModelicaPairMatcher;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.modelica.mdt.ui.text.ModelicaDocumentProvider;
import org.eclipse.ui.texteditor.IDocumentProvider;

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
 * 
 * @author Peter Bunus
 * @author MDT team
 * @author Adrian Pop
 */
public class ModelicaEditor extends TextEditor
	implements IModelicaElementChangeListener
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
	
	protected void initializeEditor() 
	{
		super.initializeEditor();
		if (getPreferenceStore() != null) /* TODO!! fix this later */
			getPreferenceStore().setValue(EDITOR_MATCHING_BRACKETS, true);
		// setDocumentProvider(getDocumentProvider());
		setSourceViewerConfiguration(new ModelicaSourceViewerConfig(this, IModelicaPartitions.MODELICA_PARTITIONING));
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
		
		/*
		 * create the action that activates the content assist on CTRL+SPACE
		 */
		IAction a = 
			new TextOperationAction(ResourceBundle.getBundle(RESOURCE_BUNDLE),
					"ContentAssistProposal.", this, 
					ISourceViewer.CONTENTASSIST_PROPOSALS);
		a.setActionDefinitionId
			(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		setAction("ContentAssistProposal", a);
		
	}

	@Override
	public void createPartControl(Composite parent)
	{
        super.createPartControl(parent);
        
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
		if (file != null)
		{
			/* don't do folding unless we are invoked on a modelica element */
			updateAnnotations(file);
			ModelicaCore.getModelicaRoot().
				addModelicaElementChangeListener(this);
		}
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
	}

	public void elementsChanged(Collection<IModelicaElementChange> changes)
	{
		IModelicaSourceFile file = getSourceFile();
		
		/*
		 * check among change list if the file we are open
		 * on have been changed, and in that case
		 * update folding annotations
		 */
		for (IModelicaElementChange change : changes)
		{
			IModelicaElement elm = change.getElement();
			
			if (elm == file)
			{
				updateAnnotations(file);
				break;
			}
		}
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
			reg = elm.getLocation();
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
	
}
