package org.modelica.uml.sysml.diagram.part;

import java.util.List;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;
import java.util.ArrayList;

import org.eclipse.gef.palette.PaletteDrawer;

import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.diagram.providers.SysmlElementTypes;

/**
 * @generated
 */
public class SysmlPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createNodes1Group());
		paletteRoot.add(createLinks2Group());
	}

	/**
	 * @generated
	 */
	private PaletteContainer createNodes1Group() {
		PaletteContainer paletteContainer = new PaletteDrawer("Nodes");
		paletteContainer.setDescription("Diagram Nodes");
		paletteContainer.add(createClass1CreationTool());
		paletteContainer.add(createModel2CreationTool());
		paletteContainer.add(createBlock3CreationTool());
		paletteContainer.add(createConnector4CreationTool());
		paletteContainer.add(createRecord5CreationTool());
		paletteContainer.add(createFunction6CreationTool());
		paletteContainer.add(createEnumeration7CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private PaletteContainer createLinks2Group() {
		PaletteContainer paletteContainer = new PaletteDrawer("Links");
		paletteContainer.setDescription("Diagram Links");
		paletteContainer.add(createGeneralization1CreationTool());
		paletteContainer.add(createComposition2CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createClass1CreationTool() {

		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaClass_1001);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaClass_1001);
		ToolEntry result = new NodeToolEntry("Class", "Create Modelica Class",
				smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createModel2CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaModel_1001);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaModel_1001);
		ToolEntry result = new NodeToolEntry("Model", "Create Modelica Model",
				smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createBlock3CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaBlock_1001);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaBlock_1001);
		ToolEntry result = new NodeToolEntry("Block", "Create Modelica Block",
				smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createConnector4CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaConnector_1001);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaConnector_1001);
		ToolEntry result = new NodeToolEntry("Connector",
				"Create Modelica Connector", smallImage, largeImage,
				elementTypes);

		return result;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createRecord5CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaRecord_1001);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaRecord_1001);
		ToolEntry result = new NodeToolEntry("Record",
				"Create Modelica Record", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createFunction6CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaFunction_1001);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaFunction_1001);
		ToolEntry result = new NodeToolEntry("Function",
				"Create Modelica Function", smallImage, largeImage,
				elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEnumeration7CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.Enumeration_1002);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.Enumeration_1002);
		ToolEntry result = new NodeToolEntry("Enumeration",
				"Create Enumeration", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createGeneralization1CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.Generalization_3001);

		largeImage = smallImage;

		final List relationshipTypes = new ArrayList();
		relationshipTypes.add(SysmlElementTypes.Generalization_3001);
		ToolEntry result = new LinkToolEntry("Generalization",
				"Create Generalization Link", smallImage, largeImage,
				relationshipTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createComposition2CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaComposition_3002);

		largeImage = smallImage;

		final List relationshipTypes = new ArrayList();
		relationshipTypes.add(SysmlElementTypes.ModelicaComposition_3002);
		ToolEntry result = new LinkToolEntry("Composition",
				"Create Composition Link", smallImage, largeImage,
				relationshipTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				ImageDescriptor smallIcon, ImageDescriptor largeIcon,
				List elementTypes) {
			super(title, description, smallIcon, largeIcon);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				ImageDescriptor smallIcon, ImageDescriptor largeIcon,
				List relationshipTypes) {
			super(title, description, smallIcon, largeIcon);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
