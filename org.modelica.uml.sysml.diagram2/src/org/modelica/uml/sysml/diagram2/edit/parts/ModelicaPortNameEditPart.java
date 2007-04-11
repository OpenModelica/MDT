package org.modelica.uml.sysml.diagram2.edit.parts;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.notation.View;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.transaction.RunnableWithResult;

import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;

import org.eclipse.gef.tools.DirectEditManager;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.LabelLocator;

import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;

import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;

import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;

import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser;

import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;

import org.eclipse.jface.text.contentassist.IContentAssistProcessor;

import org.eclipse.jface.viewers.ICellEditorValidator;

import org.eclipse.swt.SWT;

import org.eclipse.swt.accessibility.AccessibleEvent;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.diagram2.edit.policies.SysmlTextSelectionEditPolicy;
import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 */
public class ModelicaPortNameEditPart extends LabelEditPart
		implements ITextAwareEditPart {

	/**
	 */
	public static final int VISUAL_ID = 2071;

	/**
	 */
	private DirectEditManager manager;

	/**
	 */
	private IParser parser;

	/**
	 */
	private List parserElements;

	/**
	 */
	private String defaultText;

	/**
	 */
	static {
		registerSnapBackPosition(SysmlVisualIDRegistry
				.getType(ModelicaPortNameEditPart.VISUAL_ID), new Point(0, 0));
	}

	/**
	 */
	public ModelicaPortNameEditPart(View view) {
		super(view);
	}

	/**
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
	}

	public void refreshBounds() {
		IFigure refFigure = ((GraphicalEditPart) getParent()).getFigure();
		int dx = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE
				.getLocation_X())).intValue();
		int dy = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE
				.getLocation_Y())).intValue();
		Point offset = new Point(dx, dy);
		
		getFigure().getParent().setConstraint(getFigure(),
				new LabelLocator(refFigure, offset, getKeyPoint()) {

					public void relocate(IFigure target) {					
						
						Point location = getReferencePoint().getTranslated(
								getOffset());
						target.translateToParent(location);
						location.translate(-target.getBounds().width / 2, 0);
						target.setLocation(location);
						target.setSize(new Dimension(
								target.getPreferredSize().width, target
										.getPreferredSize().height));
					}

					protected Point getReferencePoint() {
						return getLabelLocation(parent);
					}
				});
	}
	
	/**
	 */
	public Point getReferencePoint() {
		return getLabelLocation(((GraphicalEditPart) getParent()).getFigure());
	}

	/**
	 */
	protected Point getLabelLocation(IFigure parent) {
		return parent.getBounds().getBottom();
	}
	
	/**
	 */
	protected String getLabelTextHelper(IFigure figure) {
		if (figure instanceof WrapLabel) {
			return ((WrapLabel) figure).getText();
		} else {
			return ((Label) figure).getText();
		}
	}

	/**
	 */
	protected void setLabelTextHelper(IFigure figure, String text) {
		if (figure instanceof WrapLabel) {
			((WrapLabel) figure).setText(text);
		} else {
			((Label) figure).setText(text);
		}
	}

	/**
	 */
	protected Image getLabelIconHelper(IFigure figure) {
		if (figure instanceof WrapLabel) {
			return ((WrapLabel) figure).getIcon();
		} else {
			return ((Label) figure).getIcon();
		}
	}

	/**
	 */
	protected void setLabelIconHelper(IFigure figure, Image icon) {
		if (figure instanceof WrapLabel) {
			((WrapLabel) figure).setIcon(icon);
		} else {
			((Label) figure).setIcon(icon);
		}
	}

	/**
	 */
	public void setLabel(IFigure figure) {
		unregisterVisuals();
		setFigure(figure);
		defaultText = getLabelTextHelper(figure);
		registerVisuals();
		refreshVisuals();
	}

	/**
	 */
	protected List getModelChildren() {
		return Collections.EMPTY_LIST;
	}

	/**
	 */
	public IGraphicalEditPart getChildBySemanticHint(String semanticHint) {
		return null;
	}

	/**
	 */
	protected EObject getParserElement() {
		EObject element = resolveSemanticElement();
		return element != null ? element : (View) getModel();
	}

	/**
	 */
	protected Image getLabelIcon() {
		return null;
	}

	/**
	 */
	protected String getLabelText() {
		ModelicaProperty connectorProperty = (ModelicaProperty) ((View) this
				.getModel()).getElement();
		return connectorProperty.getName();
	}

	public String getEditText() {
		return null;
	}

	public void setLabelText(String arg0) {
	}

	/**
	 */
	protected boolean isEditable() {
		return false;
	}

	/**
	 */
	public ICellEditorValidator getEditTextValidator() {
		return null;
	}

	/**
	 */
	public IContentAssistProcessor getCompletionProcessor() {
		return null;
	}

	/**
	 */
	public ParserOptions getParserOptions() {
		return ParserOptions.NONE;
	}

	/**
	 */
	public IParser getParser() {
		return parser;
	}

	/**
	 */
	protected DirectEditManager getManager() {
		return manager;
	}

	/**
	 */
	protected void setManager(DirectEditManager manager) {
		this.manager = manager;
	}

	/**
	 */
	protected void performDirectEdit() {
	}

	/**
	 */
	protected void performDirectEdit(Point eventLocation) {
	}

	/**
	 */
	protected void refreshVisuals() {
		super.refreshVisuals();
		refreshLabel();
		refreshFont();
		refreshFontColor();
		refreshUnderline();
		refreshStrikeThrough();
	}

	/**
	 */
	protected void refreshLabel() {
		setLabelTextHelper(getFigure(), getLabelText());
		setLabelIconHelper(getFigure(), getLabelIcon());
		Object pdEditPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
		if (pdEditPolicy instanceof SysmlTextSelectionEditPolicy) {
			((SysmlTextSelectionEditPolicy) pdEditPolicy).refreshFeedback();
		}
	}

	/**
	 */
	protected void refreshUnderline() {
		FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(
				NotationPackage.eINSTANCE.getFontStyle());
		if (style != null && getFigure() instanceof WrapLabel) {
			((WrapLabel) getFigure()).setTextUnderline(style.isUnderline());
		}
	}

	/**
	 */
	protected void refreshStrikeThrough() {
		FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(
				NotationPackage.eINSTANCE.getFontStyle());
		if (style != null && getFigure() instanceof WrapLabel) {
			((WrapLabel) getFigure()).setTextStrikeThrough(style
					.isStrikeThrough());
		}
	}

	/**
	 */
	protected void refreshFont() {
		FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(
				NotationPackage.eINSTANCE.getFontStyle());
		if (style != null) {
			FontData fontData = new FontData(style.getFontName(), style
					.getFontHeight(), (style.isBold() ? SWT.BOLD : SWT.NORMAL)
					| (style.isItalic() ? SWT.ITALIC : SWT.NORMAL));
			setFont(fontData);
		}
	}

	/**
	 */
	protected void setFontColor(Color color) {
		getFigure().setForegroundColor(color);
	}

	/**
	 */
	protected void addSemanticListeners() {
		if (getParser() instanceof ISemanticParser) {
			EObject element = resolveSemanticElement();
			parserElements = ((ISemanticParser) getParser())
					.getSemanticElementsBeingParsed(element);
			for (int i = 0; i < parserElements.size(); i++) {
				addListenerFilter(
						"SemanticModel" + i, this, (EObject) parserElements.get(i)); //$NON-NLS-1$
			}
		} else {
			super.addSemanticListeners();
		}
	}

	/**
	 */
	protected void removeSemanticListeners() {
		if (parserElements != null) {
			for (int i = 0; i < parserElements.size(); i++) {
				removeListenerFilter("SemanticModel" + i); //$NON-NLS-1$
			}
		} else {
			super.removeSemanticListeners();
		}
	}

	/**
	 */
	protected AccessibleEditPart getAccessibleEditPart() {
		if (accessibleEP == null) {
			accessibleEP = new AccessibleGraphicalEditPart() {

				public void getName(AccessibleEvent e) {
					e.result = getLabelTextHelper(getFigure());
				}
			};
		}
		return accessibleEP;
	}

	/**
	 */
	private View getFontStyleOwnerView() {
		return getPrimaryView();
	}

	/**
	 */
	protected void handleNotificationEvent(Notification event) {
		Object feature = event.getFeature();
		if (NotationPackage.eINSTANCE.getFontStyle_FontColor().equals(feature)) {
			Integer c = (Integer) event.getNewValue();
			setFontColor(DiagramColorRegistry.getInstance().getColor(c));
		} else if (NotationPackage.eINSTANCE.getFontStyle_Underline().equals(
				feature)) {
			refreshUnderline();
		} else if (NotationPackage.eINSTANCE.getFontStyle_StrikeThrough()
				.equals(feature)) {
			refreshStrikeThrough();
		} else if (NotationPackage.eINSTANCE.getFontStyle_FontHeight().equals(
				feature)
				|| NotationPackage.eINSTANCE.getFontStyle_FontName().equals(
						feature)
				|| NotationPackage.eINSTANCE.getFontStyle_Bold()
						.equals(feature)
				|| NotationPackage.eINSTANCE.getFontStyle_Italic().equals(
						feature)) {
			refreshFont();
		} else {
			if (getParser() != null
					&& getParser().isAffectingEvent(event,
							getParserOptions().intValue())) {
				refreshLabel();
			}
			if (getParser() instanceof ISemanticParser) {
				ISemanticParser modelParser = (ISemanticParser) getParser();
				if (modelParser.areSemanticElementsAffected(null, event)) {
					removeSemanticListeners();
					if (resolveSemanticElement() != null) {
						addSemanticListeners();
					}
					refreshLabel();
				}
			}
		}
		super.handleNotificationEvent(event);
	}

	/**
	 */
	protected IFigure createFigure() {
		IFigure label = createFigurePrim();
		defaultText = getLabelTextHelper(label);
		return label;
	}

	/**
	 */
	protected IFigure createFigurePrim() {
		return new ModelicaPortNameFigure();
	}

	/**
	 */
	public class ModelicaPortNameFigure extends
			org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel {

		/**
		 */
		public ModelicaPortNameFigure() {

			this.setText("<...>");
			createContents();
		}

		/**
		 */
		private void createContents() {
		}

		/**
		 */
		private boolean myUseLocalCoordinates = false;

		/**
		 */
		protected boolean useLocalCoordinates() {
			return myUseLocalCoordinates;
		}

		/**
		 */
		protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
			myUseLocalCoordinates = useLocalCoordinates;
		}

	}

}
