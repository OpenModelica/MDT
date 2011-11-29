
package org.modelica.mdt.ui.hover;

import org.eclipse.swt.widgets.Shell;

import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IInformationControlExtension4;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextHoverExtension2;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.information.IInformationProviderExtension2;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;

/**
 * Provides Modelicadoc as hover info for Modelica elements.
 *
 * @since 2.1
 */
public class ModelicaDocumentationHover extends AbstractModelicaEditorTextHover 
	implements IInformationProviderExtension2, ITextHoverExtension, ITextHoverExtension2 {

	
	/**
	 * The hover control creator.
	 * 
	 * @since 3.2
	 */
	private IInformationControlCreator fHoverControlCreator;
	/**
	 * The presentation control creator.
	 * 
	 * @since 3.2
	 */
	private IInformationControlCreator fPresenterControlCreator;


	/*
	 * @see IInformationProviderExtension2#getInformationPresenterControlCreator()
	 * @since 3.1
	 */
	public IInformationControlCreator getInformationPresenterControlCreator() {
		if (fPresenterControlCreator == null) {
			fPresenterControlCreator= new AbstractReusableInformationControlCreator() {

				/*
				 * @see org.modelica.mdt.ui.text.java.hover.AbstractReusableInformationControlCreator#doCreateInformationControl(org.eclipse.swt.widgets.Shell)
				 */
				public IInformationControl doCreateInformationControl(Shell parent) {
//					int shellStyle= SWT.RESIZE | SWT.TOOL;
//					int style= SWT.V_SCROLL | SWT.H_SCROLL;
					return new DefaultInformationControl(parent, /* shellStyle, style, */ new HTMLTextPresenter(false));
				}
			};
		}
		return fPresenterControlCreator;
	}

	/*
	 * @see ITextHoverExtension#getHoverControlCreator()
	 * @since 3.2
	 */
	public IInformationControlCreator getHoverControlCreator() {
		if (fHoverControlCreator == null) {
			fHoverControlCreator= new AbstractReusableInformationControlCreator() {
				
				/*
				 * @see org.modelica.mdt.ui.text.java.hover.AbstractReusableInformationControlCreator#doCreateInformationControl(org.eclipse.swt.widgets.Shell)
				 */
				public IInformationControl doCreateInformationControl(Shell parent) {
					return new DefaultInformationControl(parent, /*SWT.NONE,*/ new HTMLTextPresenter(true)/*, getTooltipAffordanceString()*/);
				}
				
				/*
				 * @see org.modelica.mdt.ui.text.java.hover.AbstractReusableInformationControlCreator#canReuse(org.eclipse.jface.text.IInformationControl)
				 */
				public boolean canReuse(IInformationControl control) {
					boolean canReuse= super.canReuse(control);
					if (canReuse && control instanceof IInformationControlExtension4)
						((IInformationControlExtension4)control).setStatusText(getTooltipAffordanceString());
					return canReuse;
						
				}
			};
		}
		return fHoverControlCreator;
	}

	/*
	 * @see ModelicaElementHover
	 */
	protected String getHoverInfo(IModelicaElement[] result) {

		StringBuffer buffer= new StringBuffer();
		int nResults= result.length;
		if (nResults == 0)
			return null;

		boolean hasContents= false;
		if (nResults > 1) {

			for (int i= 0; i < result.length; i++) {
				HTMLPrinter.startBulletList(buffer);
				IModelicaElement curr= result[i];
				if (curr instanceof IModelicaClass) 
				{
					HTMLPrinter.addBullet(buffer, getInfoText(curr));
					hasContents= true;
				}
				HTMLPrinter.endBulletList(buffer);
			}

		} else {

			IModelicaElement curr= result[0];
			if (curr instanceof IModelicaElement) {
				IModelicaElement member= curr;
				HTMLPrinter.addSmallHeader(buffer, getInfoText(member));
				HTMLPrinter.addParagraph(buffer, curr.getElementName());
				hasContents= true;
			} 
		}
		
		if (!hasContents)
			return null;

		if (buffer.length() > 0) {
			HTMLPrinter.insertPageProlog(buffer, 0, getStyleSheet());
			HTMLPrinter.addPageEpilog(buffer);
			return buffer.toString();
		}

		return null;
	}

	private String getInfoText(IModelicaElement member) 
	{
		String label= member.getElementName();
		StringBuffer buf= new StringBuffer();
		for (int i= 0; i < label.length(); i++) {
			char ch= label.charAt(i);
			if (ch == '<') {
				buf.append("&lt;"); //$NON-NLS-1$
			} else if (ch == '>') {
				buf.append("&gt;"); //$NON-NLS-1$
			} else {
				buf.append(ch);
			}
		}
		return buf.toString();
	}

	public Object getHoverInfo2(ITextViewer textViewer, IRegion hoverRegion)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
