package org.modelica.uml.sysml.diagram2.edit.parts;

import java.util.Collections;
import org.eclipse.gef.DragTracker;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ColorConstants;

import org.eclipse.draw2d.geometry.Point;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.impl.ENotificationImpl; 

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;

import org.eclipse.emf.transaction.RunnableWithResult;

import org.eclipse.gef.AccessibleEditPart;

import org.eclipse.gef.requests.DirectEditRequest;

import org.eclipse.gef.tools.DirectEditManager;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;

import org.eclipse.gmf.runtime.common.ui.services.properties.IPropertiesProvider;
import org.eclipse.gmf.runtime.common.ui.services.properties.PropertiesService;
import org.eclipse.gmf.runtime.common.ui.services.properties.PropertiesServiceAdapterFactory;
import org.eclipse.gmf.runtime.common.ui.services.properties.extended.PropertySource;

import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;

import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;

import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;

import org.eclipse.draw2d.StackLayout;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;

import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gef.commands.Command;

import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

import org.eclipse.gef.requests.CreateRequest;

import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ListItemComponentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;

import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;

import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;

import org.eclipse.gmf.runtime.emf.ui.properties.descriptors.EMFCompositeSourcePropertyDescriptor;

import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;

import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.jface.text.contentassist.IContentAssistProcessor;

import org.eclipse.jface.viewers.ICellEditorValidator;

import org.eclipse.swt.SWT;

import org.eclipse.swt.accessibility.AccessibleEvent;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Font;

import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;

import java.util.List;

import org.modelica.uml.sysml.ModelicaType;
import org.modelica.uml.sysml.impl.ModelicaTypeImpl;
import org.modelica.uml.sysml.diagram2.edit.policies.ModelicaTypeCanonicalEditPolicy;
import org.modelica.uml.sysml.diagram2.edit.policies.ModelicaTypeGraphicalNodeEditPolicy;
import org.modelica.uml.sysml.diagram2.edit.policies.ModelicaTypeItemSemanticEditPolicy;

import org.modelica.uml.sysml.diagram2.edit.policies.SysmlTextNonResizableEditPolicy;
import org.modelica.uml.sysml.diagram2.edit.policies.SysmlTextSelectionEditPolicy;
import org.modelica.uml.sysml.diagram2.edit.util.ModelicaPropertyUtil;

import org.modelica.uml.sysml.diagram2.part.SysmlDiagramEditorPlugin;

import org.modelica.uml.sysml.diagram2.part.SysmlVisualIDRegistry;

import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

import org.modelica.uml.sysml.SysmlPackage;
import org.modelica.uml.sysml.ModelicaReal;
import org.modelica.uml.sysml.ModelicaInteger;
import org.modelica.uml.sysml.ModelicaBoolean;
import org.modelica.uml.sysml.ModelicaString;

/**
 * @generated
 */
public class ModelicaTypeEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1002;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public ModelicaTypeEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ModelicaTypeItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new ModelicaTypeGraphicalNodeEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new ModelicaTypeCanonicalEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		LayoutEditPolicy lep = new LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		TypeFigure figure = new TypeFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public TypeFigure getPrimaryShape() {
		return (TypeFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ModelicaTypeNameEditPart) {
			((ModelicaTypeNameEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureTypeName());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		return false;
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		return new DefaultSizeNodeFigure(getMapMode().DPtoLP(40), getMapMode()
				.DPtoLP(40));
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated NOT
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		
		//display the name of the type if it is set
		Object element = ((NodeImpl)this.getModel()).getElement();
		Object value = ((ModelicaTypeImpl)element).getTypeData();
		EStructuralFeature feature = SysmlPackage.Literals.MODELICA_TYPE__TYPE_DATA;
		
		if(value != null){
		
			ENotificationImpl notification = new ENotificationImpl((InternalEObject)element,
					Notification.SET, feature, value, value);
		//call the metod directly , notifyChanged is not working if editpart is not active
			this.handleNotificationEvent(notification);
		}
		
		
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(getMapMode().DPtoLP(5));
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(SysmlVisualIDRegistry
				.getType(ModelicaTypeNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated NOT
	 */
	public void refresh() {

		super.refresh();
	}

	//this works but I do not understand it very well. Prepair explanation for
	// it.
	protected void handleNotificationEvent(Notification notification) {

		super.handleNotificationEvent(notification);

		if (SysmlPackage.Literals.MODELICA_TYPE__TYPE_DATA.equals(notification
				.getFeature())) {

			String typeName = null;

			IPropertiesProvider service = PropertiesService.getInstance();
			PropertiesServiceAdapterFactory adapterFactory = new PropertiesServiceAdapterFactory();
			assert null != service;
			IPropertySource propSource = service.getPropertySource(this);

			IPropertyDescriptor[] descriptors = propSource
					.getPropertyDescriptors();
			for (int i = 0; i < descriptors.length; i++) {
				Object type = notification.getNewValue();

				if (((EMFCompositeSourcePropertyDescriptor) descriptors[i])
						.getId().equals(notification.getFeature())) {
					typeName = descriptors[i].getLabelProvider().getText(type);

					if (typeName != null) {
						WrapLabel typeNameLabel = (WrapLabel) this
								.getPrimaryShape().getFigureTypeDisplay()
								.getChildren().get(0);
						typeNameLabel.setText(typeName);
					}

				}

			}

		}
	}

	/**
	 * @generated
	 */
	public class TypeFigure extends org.eclipse.draw2d.RectangleFigure {

		/**
		 * @generated
		 */
		public TypeFigure() {

			this.setPreferredSize(200, 160);
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {
			org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig_0 = new org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel();
			fig_0.setText("<<ModelicaType>>");

			setFigureIdentifier(fig_0);

			Object layData0 = null;

			this.add(fig_0, layData0);
			org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig_1 = new org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel();
			fig_1.setText("");

			setFigureTypeName(fig_1);

			Object layData1 = null;

			this.add(fig_1, layData1);
			org.eclipse.draw2d.RectangleFigure fig_2 = new org.eclipse.draw2d.RectangleFigure();

			setFigureTypeDisplay(fig_2);

			Object layData2 = null;

			this.add(fig_2, layData2);
			org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig_3 = new org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel();
			fig_3.setText("TypeIdentifier");

			Object layData3 = null;

			fig_2.add(fig_3, layData3);
			fig_3.setSize(200, 20);
			
		}

		/**
		 * @generated
		 */
		private org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fIdentifier;

		/**
		 * @generated
		 */
		public org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel getFigureIdentifier() {
			return fIdentifier;
		}

		/**
		 * @generated
		 */
		private void setFigureIdentifier(
				org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig) {
			fIdentifier = fig;
		}

		/**
		 * @generated
		 */
		private org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fTypeName;

		/**
		 * @generated
		 */
		public org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel getFigureTypeName() {
			return fTypeName;
		}

		/**
		 * @generated
		 */
		private void setFigureTypeName(
				org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig) {
			fTypeName = fig;
		}

		/**
		 * @generated
		 */
		private org.eclipse.draw2d.RectangleFigure fTypeDisplay;

		/**
		 * @generated
		 */
		public org.eclipse.draw2d.RectangleFigure getFigureTypeDisplay() {
			return fTypeDisplay;
		}

		/**
		 * @generated
		 */
		private void setFigureTypeDisplay(org.eclipse.draw2d.RectangleFigure fig) {
			fTypeDisplay = fig;
		}

		/**
		 * @generated
		 */
		private boolean myUseLocalCoordinates = false;

		/**
		 * @generated
		 */
		protected boolean useLocalCoordinates() {
			return myUseLocalCoordinates;
		}

		/**
		 * @generated
		 */
		protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
			myUseLocalCoordinates = useLocalCoordinates;
		}

	}

}
