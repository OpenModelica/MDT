/*******************************************************************************
 * @author Adrian Pop
 * Copyright (c) 2006 Adrian Pop, adrpo@ida.liu.se
 * All rights reserved. 
 * Permission to use, copy, modify, and distribute this software and
 * its documentation for NON-COMMERCIAL purposes and without fee is hereby 
 * granted, provided that this copyright notice appear in all copies and 
 * that both the copyright notice and this permission notice and warranty
 * disclaimer appear in supporting documentation, and that the name of
 * The Author not be used in advertising or publicity pertaining to
 * distribution of the software without specific, written prior permission.
 * 
 * COMMERCIAL use, copy, modification and distribution 
 * is NOT permitted without prior agreement with Adrian Pop.
 * 
 * THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE,
 * INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS
 * OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE
 * OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
*******************************************************************************/
package org.modelica.mdt.ui.editor;

import org.eclipse.jface.text.hyperlink.*;
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
import org.eclipse.jface.text.ITextHover;
import org.modelica.mdt.ui.text.ModelicaTextHover;

/**
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

	public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {

		IHyperlinkDetector[] inheritedDetectors= super.getHyperlinkDetectors(sourceViewer);

		if (textEditor == null)
			return inheritedDetectors;

		int inheritedDetectorsLength= inheritedDetectors != null ? inheritedDetectors.length : 0;
		IHyperlinkDetector[] detectors= new IHyperlinkDetector[inheritedDetectorsLength + 1];
		detectors[0]= new ModelicaElementHyperlinkDetector(textEditor);
		for (int i= 0; i < inheritedDetectorsLength; i++)
			detectors[i+1]= inheritedDetectors[i];

		return detectors;
	}

	public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType) 
	{
		if (contentType.equals(IDocument.DEFAULT_CONTENT_TYPE))
		{
			ModelicaTextHover textHover = new ModelicaTextHover();
			textHover.setEditor(textEditor);
			return textHover;
		}
		else return null;
	}
	
	
}
