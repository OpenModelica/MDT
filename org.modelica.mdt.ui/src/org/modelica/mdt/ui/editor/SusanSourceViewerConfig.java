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

		DefaultDamagerRepairer drSingleLineComment = new DefaultDamagerRepairer(new SusanRuleBasedScanner());
		r.setDamager(drSingleLineComment, SusanRuleBasedPartitionScanner.SUSAN_SINGLELINE_COMMENT_ID);
		r.setRepairer(drSingleLineComment, SusanRuleBasedPartitionScanner.SUSAN_SINGLELINE_COMMENT_ID);

		DefaultDamagerRepairer drMultiLineComment = new DefaultDamagerRepairer(new SusanRuleBasedScanner());
		r.setDamager(drMultiLineComment, SusanRuleBasedPartitionScanner.SUSAN_MULTILINE_COMMENT_ID);
		r.setRepairer(drMultiLineComment, SusanRuleBasedPartitionScanner.SUSAN_MULTILINE_COMMENT_ID);

		DefaultDamagerRepairer drTargetCode = new DefaultDamagerRepairer(new SusanRuleBasedScanner());
		r.setDamager(drTargetCode, SusanRuleBasedPartitionScanner.SUSAN_TARGET_CODE_ID);
		r.setRepairer(drTargetCode, SusanRuleBasedPartitionScanner.SUSAN_TARGET_CODE_ID);

		DefaultDamagerRepairer drKeyWords = new DefaultDamagerRepairer(new SusanRuleBasedScanner());
		r.setDamager(drKeyWords, SusanRuleBasedPartitionScanner.SUSAN_KEYWORDS_ID);
		r.setRepairer(drKeyWords, SusanRuleBasedPartitionScanner.SUSAN_KEYWORDS_ID);

		NonRuleBasedDamagerRepairer ndr = new NonRuleBasedDamagerRepairer(new TextAttribute(SusanEditorColors.MULTILINE_COMMENT_COLOR));

		r.setDamager(ndr, SusanRuleBasedPartitionScanner.SUSAN_MULTILINE_COMMENT_ID);
		r.setRepairer(ndr, SusanRuleBasedPartitionScanner.SUSAN_MULTILINE_COMMENT_ID);

		NonRuleBasedDamagerRepairer ndr2 = new NonRuleBasedDamagerRepairer(new TextAttribute(SusanEditorColors.FUNCTION_COLOR));

		r.setDamager(ndr2, SusanRuleBasedPartitionScanner.SUSAN_TARGET_CODE_ID);
		r.setRepairer(ndr2, SusanRuleBasedPartitionScanner.SUSAN_TARGET_CODE_ID);

		return r;
	}

}
