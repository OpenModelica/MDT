package org.modelica.mdt.ui.editor;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.hyperlink.AbstractHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlink;

class SusanHyperLinkDetector extends AbstractHyperlinkDetector {

//	private ArrayList<String>
//  hyperlink detector not in use atm
	@Override
	public IHyperlink[] detectHyperlinks(ITextViewer textViewer,
			IRegion region, boolean canShowMultipleHyperlinks)
	{
		
	//	IDocument document = textViewer.getDocument();
	//	int offset = region.getOffset();
		// TODO Auto-generated method stub
		return null;
	}
	
}