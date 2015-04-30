/**
 * This Class is for the Susan-Editor-Extension
 * it is containing the Reconciler Class wich contains the call of the Demage Repairers for the Rules
 * 
 */

package org.modelica.mdt.ui.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class SusanSourceViewerConfig extends SourceViewerConfiguration
{
	public SusanSourceViewerConfig()
	{
		super();
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer)
	{
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE, SusanRuleBasedPartitionScanner.SUSAN_MULTILINE_COMMENT_ID };
	}

	@Override
	public String getConfiguredDocumentPartitioning(ISourceViewer sourceViewer)
	{
		
		return SusanRuleBasedPartitionScanner.SUSAN_PARTITIONING;
	}
	
	
	
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer)
	{
		
		PresentationReconciler r = new PresentationReconciler();
		DefaultDamagerRepairer dr_default;
		
		dr_default = new DefaultDamagerRepairer(new SusanRuleBasedScanner());
		r.setDamager(dr_default, IDocument.DEFAULT_CONTENT_TYPE);
		r.setRepairer(dr_default, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr = new NonRuleBasedDamagerRepairer(new TextAttribute(SusanEditorColors.MULTILINE_COMMENT_COLOR));
		r.setDamager(ndr, SusanRuleBasedPartitionScanner.SUSAN_MULTILINE_COMMENT_ID);
		r.setRepairer(ndr, SusanRuleBasedPartitionScanner.SUSAN_MULTILINE_COMMENT_ID);
		
		NonRuleBasedDamagerRepairer ndr3 = new NonRuleBasedDamagerRepairer(new TextAttribute(SusanEditorColors.MULTILINE_COMMENT_COLOR));
		r.setDamager(ndr3, SusanRuleBasedPartitionScanner.SUSAN_MULTILINE_STRING_ID);
		r.setRepairer(ndr3, SusanRuleBasedPartitionScanner.SUSAN_MULTILINE_STRING_ID);

		NonRuleBasedDamagerRepairer ndr2 = new NonRuleBasedDamagerRepairer(new TextAttribute(SusanEditorColors.FUNCTION_COLOR));
		r.setDamager(ndr2, SusanRuleBasedPartitionScanner.SUSAN_TARGET_CODE_ID1);
		r.setRepairer(ndr2, SusanRuleBasedPartitionScanner.SUSAN_TARGET_CODE_ID1);
		
		NonRuleBasedDamagerRepairer ndr4 = new NonRuleBasedDamagerRepairer(new TextAttribute(SusanEditorColors.FUNCTION_COLOR));
		r.setDamager(ndr4, SusanRuleBasedPartitionScanner.SUSAN_TARGET_CODE_ID2);
		r.setRepairer(ndr4, SusanRuleBasedPartitionScanner.SUSAN_TARGET_CODE_ID2);
		
		NonRuleBasedDamagerRepairer ndr5 = new NonRuleBasedDamagerRepairer(new TextAttribute(SusanEditorColors.FUNCTION_COLOR));
		r.setDamager(ndr5, SusanRuleBasedPartitionScanner.SUSAN_TARGET_CODE_ID3);
		r.setRepairer(ndr5, SusanRuleBasedPartitionScanner.SUSAN_TARGET_CODE_ID3);
		


		return r;
	}

}
