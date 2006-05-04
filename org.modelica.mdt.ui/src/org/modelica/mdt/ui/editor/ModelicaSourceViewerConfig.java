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

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.modelica.mdt.ui.text.IModelicaPartitions;
import org.modelica.mdt.ui.text.ModelicaCodeScanner;
import org.modelica.mdt.ui.text.ModelicaMultilineCommentScanner;
import org.modelica.mdt.ui.text.ModelicaSinglelineCommentScanner;
import org.modelica.mdt.ui.text.ModelicaStringScanner;

/**
 * @author Peter Bunus
 * 
 * @author Adrian Pop
 *   - big changes, splited scanners for different partitions, etc
 */
public class ModelicaSourceViewerConfig extends TextSourceViewerConfiguration 
{

	private ModelicaCodeScanner 	         fCodeScanner;              /* for code parts and strings */
	private ModelicaMultilineCommentScanner  fMultilineCommentScanner;  /* for C comments */
	private ModelicaSinglelineCommentScanner fSinglelineCommentScanner; /* for single line comments */
	private ModelicaStringScanner            fStringScanner;            /* for strings and comment strings */
	
	private ModelicaEditor textEditor;
		
	private String fDocumentPartitioning;

	public ModelicaSourceViewerConfig(ModelicaEditor textEditor, String partitioning) 
	{
		this.textEditor = textEditor;
		this.fDocumentPartitioning = partitioning;
	}

	protected ModelicaCodeScanner getCodeScanner() 
	{
		if (fCodeScanner == null) 
		{
			fCodeScanner = new ModelicaCodeScanner();
			fCodeScanner.setDefaultReturnToken
				(new Token(ModelicaEditorColors.CODE_ATTRIBUTE));
		}
		return fCodeScanner;
	}
	
	protected ModelicaMultilineCommentScanner getMultilineCommentScanner() 
	{
		if (fMultilineCommentScanner == null) 
		{
			fMultilineCommentScanner = new ModelicaMultilineCommentScanner();
			fMultilineCommentScanner.setDefaultReturnToken
				(new Token(ModelicaEditorColors.MULTILINE_COMMENT_ATTRIBUTE));
		}
		return fMultilineCommentScanner;
	}

	protected ModelicaSinglelineCommentScanner getSinglelineCommentScanner() 
	{
		if (fSinglelineCommentScanner == null) 
		{
			fSinglelineCommentScanner = new ModelicaSinglelineCommentScanner();
			fSinglelineCommentScanner.setDefaultReturnToken
				(new Token(ModelicaEditorColors.SINGLELINE_COMMENT_ATTRIBUTE));
		}
		return fSinglelineCommentScanner;
	}

	protected ModelicaStringScanner getStringScanner() 
	{
		if (fStringScanner == null) 
		{
			fStringScanner = new ModelicaStringScanner();
			fStringScanner.setDefaultReturnToken
				(new Token(ModelicaEditorColors.STRING_ATTRIBUTE));
		}
		return fStringScanner;
	}
		
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) 
	{
		PresentationReconciler reconciler = new PresentationReconciler();
		
		reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
		
		DefaultDamagerRepairer drCode = new DefaultDamagerRepairer(getCodeScanner());
		reconciler.setDamager(drCode, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(drCode, IDocument.DEFAULT_CONTENT_TYPE);
		
		DefaultDamagerRepairer drMultilineComments = new DefaultDamagerRepairer(getMultilineCommentScanner());		
		reconciler.setDamager(drMultilineComments, IModelicaPartitions.MODELICA_MULTI_LINE_COMMENT);
		reconciler.setRepairer(drMultilineComments, IModelicaPartitions.MODELICA_MULTI_LINE_COMMENT);		
		
		DefaultDamagerRepairer drSingleComments = new DefaultDamagerRepairer(getSinglelineCommentScanner());		
		reconciler.setDamager(drSingleComments, IModelicaPartitions.MODELICA_SINGLE_LINE_COMMENT);
		reconciler.setRepairer(drSingleComments, IModelicaPartitions.MODELICA_SINGLE_LINE_COMMENT);
		
		DefaultDamagerRepairer drStrings = new DefaultDamagerRepairer(getStringScanner());
		reconciler.setDamager(drStrings, IModelicaPartitions.MODELICA_STRING);
		reconciler.setRepairer(drStrings, IModelicaPartitions.MODELICA_STRING);		
				
		return reconciler;
	}
	
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer)
	{
		ContentAssistant assistant = new ContentAssistant();
		
		assistant.setContentAssistProcessor(new ModelicaCompletionProcessor(textEditor),
				IDocument.DEFAULT_CONTENT_TYPE);
		
		assistant.enableAutoActivation(true);
		assistant.setAutoActivationDelay(100);
		assistant.setProposalPopupOrientation(IContentAssistant
				.PROPOSAL_OVERLAY);
		assistant.setContextInformationPopupOrientation(IContentAssistant
				.CONTEXT_INFO_ABOVE);
		assistant.setProposalSelectorBackground(new Color(Display.getCurrent(),
				new RGB(255, 255, 255)));
		assistant.setContextInformationPopupBackground(new Color(Display.getCurrent(),
				new RGB(255, 255, 255)));

		return assistant;
	}
	
	//TODO Adrian Pop, please add the tab length to the Modelica Preferences page!
	public int getTabWidth(ISourceViewer sourceViewer) 
	{
		/* return the peter fritzson constant */
		return 2;
	}

	/*
	 * @see SourceViewerConfiguration#getConfiguredContentTypes(ISourceViewer)
	 */
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			IModelicaPartitions.MODELICA_MULTI_LINE_COMMENT,
			IModelicaPartitions.MODELICA_SINGLE_LINE_COMMENT,
			IModelicaPartitions.MODELICA_STRING
		};
	}

	/*
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getConfiguredDocumentPartitioning(org.eclipse.jface.text.source.ISourceViewer)
	 * @since 3.0
	 */
	public String getConfiguredDocumentPartitioning(ISourceViewer sourceViewer) {
		if (fDocumentPartitioning != null)
			return fDocumentPartitioning;
		return super.getConfiguredDocumentPartitioning(sourceViewer);
	}	
	
}
