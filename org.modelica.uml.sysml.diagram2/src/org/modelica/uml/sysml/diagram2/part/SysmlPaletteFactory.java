package org.modelica.uml.sysml.diagram2.part;

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

import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class SysmlPaletteFactory {

	/**
	 * @generated NOT
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createNodes1Group());
		paletteRoot.add(createParts2Group());
		paletteRoot.add(createLinks3Group());
		paletteRoot.add(createNested4Group());
	}

	/**
	 * @generated NOT
	 */
	private PaletteContainer createNodes1Group() {
		PaletteContainer paletteContainer = new PaletteDrawer("Nodes");
		paletteContainer.setDescription("Top Nodes");
		paletteContainer.add(createClass1CreationTool());
		paletteContainer.add(createModel2CreationTool());
		paletteContainer.add(createBlock3CreationTool());
		paletteContainer.add(createFunction6CreationTool());
		paletteContainer.add(createConnector4CreationTool());
		paletteContainer.add(createRecord5CreationTool());
		paletteContainer.add(createType5CreationTool());

		return paletteContainer;
	}

	/**
	 * @generated NOT
	 */
	private PaletteContainer createParts2Group() {
		PaletteContainer paletteContainer = new PaletteDrawer("Parts");
		paletteContainer.setDescription("Internal Parts");
		paletteContainer.add(createClasspart1CreationTool());
		paletteContainer.add(createConnectorpart2CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated NOT
	 */
	private PaletteContainer createLinks3Group() {
		PaletteContainer paletteContainer = new PaletteDrawer("Links");
		paletteContainer.setDescription("Diagram Links");
		paletteContainer.add(createConnection1CreationTool());
		paletteContainer.add(createGeneralization2CreationTool());
		paletteContainer.add(createComposition3CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated NOT
	 */
	private PaletteContainer createNested4Group() {
		PaletteContainer paletteContainer = new PaletteDrawer("Nested");
		paletteContainer.setDescription("Nested Nodes");
		paletteContainer.add(createNestedClass1CreationTool());
		paletteContainer.add(createNestedModel2CreationTool());
		paletteContainer.add(createNestedBlock3CreationTool());
		paletteContainer.add(createNestedConnector4CreationTool());
		paletteContainer.add(createNestedRecord5CreationTool());
		paletteContainer.add(createNestedFunction6CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createClass1CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaClass_1001);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaClass_1001);
		elementTypes.add(SysmlElementTypes.ModelicaClass_2005);
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
	 * @generated
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
		ToolEntry result = new NodeToolEntry("Connector", "Create Modelica Block",
				smallImage, largeImage, elementTypes);

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
		ToolEntry result = new NodeToolEntry("Record", "Create Modelica Block",
				smallImage, largeImage, elementTypes);


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
	 * @generated NOT
	 */
	private ToolEntry createFunction4CreationTool() {
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
	private ToolEntry createType5CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaType_1002);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaType_1002);
		ToolEntry result = new NodeToolEntry("Type", "Create Modelica Type",
				smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createClasspart1CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaProperty_2004);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaProperty_2004);
		ToolEntry result = new NodeToolEntry("Class part", "Create Class Part",
				smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createConnectorpart2CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = null;

		largeImage = smallImage;

		ToolEntry result = new ToolEntry("Connector part",
				"Create Connector part", smallImage, largeImage) {
		};

		return result;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createConnection1CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaConnection_3009);

		largeImage = smallImage;

		final List relationshipTypes = new ArrayList();
		relationshipTypes.add(SysmlElementTypes.ModelicaConnection_3009);
		ToolEntry result = new LinkToolEntry("Connection",
				"Create Connection Link", smallImage, largeImage,
				relationshipTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createGeneralization2CreationTool() {
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
	private ToolEntry createComposition3CreationTool() {
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
	 * @generated NOT
	 */
	private ToolEntry createNestedClass1CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaClass_2005);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaClass_2005);
		ToolEntry result = new NodeToolEntry("Class",
				"Create nested Modelica Class", smallImage, largeImage,
				elementTypes);

		return result;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNestedModel2CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaModel_2005);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaModel_2005);
		ToolEntry result = new NodeToolEntry("Model",
				"Create nested Modelica Model", smallImage, largeImage,
				elementTypes);

		return result;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNestedBlock3CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaBlock_2005);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaBlock_2005);
		ToolEntry result = new NodeToolEntry("Block",
				"Create nested Modelica Block", smallImage, largeImage,
				elementTypes);

		return result;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNestedConnector4CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaConnector_2005);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaConnector_2005);
		ToolEntry result = new NodeToolEntry("Connector",
				"Create nested Modelica Connector", smallImage, largeImage,
				elementTypes);

		return result;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNestedRecord5CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaRecord_2005);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaRecord_2005);
		ToolEntry result = new NodeToolEntry("Record",
				"Create nested Modelica Record", smallImage, largeImage,
				elementTypes);

		return result;
	}

	/**
	 * @generated NOT
	 */
	private ToolEntry createNestedFunction6CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = SysmlElementTypes
				.getImageDescriptor(SysmlElementTypes.ModelicaFunction_2005);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(SysmlElementTypes.ModelicaFunction_2005);
		ToolEntry result = new NodeToolEntry("Function",
				"Create nested Modelica Function", smallImage, largeImage,
				elementTypes);

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
