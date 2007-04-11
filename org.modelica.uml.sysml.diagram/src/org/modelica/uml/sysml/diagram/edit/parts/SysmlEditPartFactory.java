package org.modelica.uml.sysml.diagram.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.modelica.uml.sysml.diagram.part.SysmlVisualIDRegistry;

/**
 * @generated
 */
public class SysmlEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public static final String EXTERNAL_NODE_LABELS_LAYER = "External Node Labels"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			int viewVisualID = SysmlVisualIDRegistry.getVisualID(view);
			switch (viewVisualID) {
			case ModelicaClassEditPart.VISUAL_ID:
				return new ModelicaClassEditPart(view);
			case ModelicaClassNameEditPart.VISUAL_ID:
				return new ModelicaClassNameEditPart(view);
			case EnumerationEditPart.VISUAL_ID:
				return new EnumerationEditPart(view);
			case EnumerationNameEditPart.VISUAL_ID:
				return new EnumerationNameEditPart(view);
			case ModelicaPropertyEditPart.VISUAL_ID:
				return new ModelicaPropertyEditPart(view);
			case ModelicaProperty2EditPart.VISUAL_ID:
				return new ModelicaProperty2EditPart(view);
			case ModelicaProperty3EditPart.VISUAL_ID:
				return new ModelicaProperty3EditPart(view);
			case EnumerationLiteralEditPart.VISUAL_ID:
				return new EnumerationLiteralEditPart(view);
			case ModelicaClassParametersEditPart.VISUAL_ID:
				return new ModelicaClassParametersEditPart(view);
			case ModelicaClassPartsEditPart.VISUAL_ID:
				return new ModelicaClassPartsEditPart(view);
			case ModelicaClassValuesEditPart.VISUAL_ID:
				return new ModelicaClassValuesEditPart(view);
			case EnumerationCompartmentEditPart.VISUAL_ID:
				return new EnumerationCompartmentEditPart(view);
			case ModelEditPart.VISUAL_ID:
				return new ModelEditPart(view);
			case GeneralizationEditPart.VISUAL_ID:
				return new GeneralizationEditPart(view);
			case ModelicaCompositionEditPart.VISUAL_ID:
				return new ModelicaCompositionEditPart(view);
			case ModelicaCompositionNameEditPart.VISUAL_ID:
				return new ModelicaCompositionNameEditPart(view);
			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrapLabel)
			return new TextCellEditorLocator((WrapLabel) source.getFigure());
		else {
			IFigure figure = source.getFigure();
			return new LabelCellEditorLocator((Label) figure);
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrapLabel wrapLabel;

		/**
		 * @generated
		 */
		public TextCellEditorLocator(WrapLabel wrapLabel) {
			super();
			this.wrapLabel = wrapLabel;
		}

		/**
		 * @generated
		 */
		public WrapLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);

			if (getWrapLabel().isTextWrapped()
					&& getWrapLabel().getText().length() > 0)
				rect.setSize(new Dimension(text.computeSize(rect.width,
						SWT.DEFAULT)));
			else {
				int avr = FigureUtilities.getFontMetrics(text.getFont())
						.getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
						SWT.DEFAULT)).expand(avr * 2, 0));
			}

			if (!rect.equals(new Rectangle(text.getBounds())))
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
		}

	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);

			int avr = FigureUtilities.getFontMetrics(text.getFont())
					.getAverageCharWidth();
			rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
					SWT.DEFAULT)).expand(avr * 2, 0));

			if (!rect.equals(new Rectangle(text.getBounds())))
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
		}
	}
}
