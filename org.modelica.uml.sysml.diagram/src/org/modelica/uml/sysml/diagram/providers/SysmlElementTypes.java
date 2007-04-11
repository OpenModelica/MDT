package org.modelica.uml.sysml.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.AccessKind;
import org.eclipse.uml2.uml.VisibilityKind;

import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.SysmlPackage;

import org.modelica.uml.sysml.diagram.expressions.SysmlAbstractExpression;
import org.modelica.uml.sysml.diagram.expressions.SysmlOCLFactory;

import org.modelica.uml.sysml.diagram.part.SysmlDiagramEditorPlugin;

/**
 * @generated
 */
public class SysmlElementTypes {

	/**
	 * @generated
	 */
	private SysmlElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			element = ((EStructuralFeature) element).getEContainingClass();
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return SysmlDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated NOT
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap();
			elements.put(Model_79, UMLPackage.eINSTANCE.getModel());
			elements.put(ModelicaProperty_2001, SysmlPackage.eINSTANCE
					.getModelicaProperty());
			elements.put(ModelicaProperty_2002, SysmlPackage.eINSTANCE
					.getModelicaProperty());
			elements.put(ModelicaProperty_2003, SysmlPackage.eINSTANCE
					.getModelicaProperty());
			elements.put(EnumerationLiteral_2004, UMLPackage.eINSTANCE
					.getEnumerationLiteral());
			elements.put(ModelicaClass_1001, SysmlPackage.eINSTANCE
					.getModelicaClass());
			elements.put(Enumeration_1002, UMLPackage.eINSTANCE
					.getEnumeration());
			elements.put(Generalization_3001, UMLPackage.eINSTANCE
					.getGeneralization());
			elements.put(ModelicaComposition_3002, SysmlPackage.eINSTANCE
					.getModelicaComposition());

			elements.put(ModelicaModel_1001, SysmlPackage.eINSTANCE
					.getModelicaModel());
			elements.put(ModelicaBlock_1001, SysmlPackage.eINSTANCE
					.getModelicaBlock());
			elements.put(ModelicaRecord_1001, SysmlPackage.eINSTANCE
					.getModelicaRecord());
			elements.put(ModelicaConnector_1001, SysmlPackage.eINSTANCE
					.getModelicaConnector());
			elements.put(ModelicaFunction_1001, SysmlPackage.eINSTANCE
					.getModelicaFunction());

		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	public static final IElementType Model_79 = getElementType("org.modelica.uml.sysml.diagram.Model_79"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ModelicaProperty_2001 = getElementType("org.modelica.uml.sysml.diagram.ModelicaProperty_2001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ModelicaProperty_2002 = getElementType("org.modelica.uml.sysml.diagram.ModelicaProperty_2002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ModelicaProperty_2003 = getElementType("org.modelica.uml.sysml.diagram.ModelicaProperty_2003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType EnumerationLiteral_2004 = getElementType("org.modelica.uml.sysml.diagram.EnumerationLiteral_2004"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ModelicaClass_1001 = getElementType("org.modelica.uml.sysml.diagram.ModelicaClass_1001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Enumeration_1002 = getElementType("org.modelica.uml.sysml.diagram.Enumeration_1002"); //$NON-NLS-1$

	/**
	 */
	public static final IElementType ModelicaModel_1001 = getElementType("org.modelica.uml.sysml.diagram.ModelicaModel_1001"); //$NON-NLS-1$

	/**
	 */
	public static final IElementType ModelicaBlock_1001 = getElementType("org.modelica.uml.sysml.diagram.ModelicaBlock_1001"); //$NON-NLS-1$

	/**
	 */
	public static final IElementType ModelicaRecord_1001 = getElementType("org.modelica.uml.sysml.diagram.ModelicaRecord_1001"); //$NON-NLS-1$

	/**
	 */
	public static final IElementType ModelicaConnector_1001 = getElementType("org.modelica.uml.sysml.diagram.ModelicaConnector_1001"); //$NON-NLS-1$

	/**
	 */
	public static final IElementType ModelicaFunction_1001 = getElementType("org.modelica.uml.sysml.diagram.ModelicaFunction_1001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Generalization_3001 = getElementType("org.modelica.uml.sysml.diagram.Generalization_3001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ModelicaComposition_3002 = getElementType("org.modelica.uml.sysml.diagram.ModelicaComposition_3002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	private static Set KNOWN_ELEMENT_TYPES;

	/**
	 * @generated NOT
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet();
			KNOWN_ELEMENT_TYPES.add(Model_79);
			KNOWN_ELEMENT_TYPES.add(ModelicaProperty_2001);
			KNOWN_ELEMENT_TYPES.add(ModelicaProperty_2002);
			KNOWN_ELEMENT_TYPES.add(ModelicaProperty_2003);
			KNOWN_ELEMENT_TYPES.add(EnumerationLiteral_2004);
			KNOWN_ELEMENT_TYPES.add(ModelicaClass_1001);
			KNOWN_ELEMENT_TYPES.add(Enumeration_1002);
			KNOWN_ELEMENT_TYPES.add(Generalization_3001);
			KNOWN_ELEMENT_TYPES.add(ModelicaComposition_3002);

			KNOWN_ELEMENT_TYPES.add(ModelicaModel_1001);
			KNOWN_ELEMENT_TYPES.add(ModelicaBlock_1001);
			KNOWN_ELEMENT_TYPES.add(ModelicaRecord_1001);
			KNOWN_ELEMENT_TYPES.add(ModelicaConnector_1001);
			KNOWN_ELEMENT_TYPES.add(ModelicaFunction_1001);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static class Initializers {

		/**
		 * @generated
		 */
		public static final ObjectInitializer ModelicaProperty_2001 = new ObjectInitializer(
				new FeatureInitializer[] {
						new FeatureInitializer(SysmlOCLFactory.getExpression(
								"VariabilityKind::none", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty()),
								SysmlPackage.eINSTANCE
										.getModelicaProperty_Variability()),
						new FeatureInitializer(SysmlOCLFactory.getExpression(
								"PropertyKind::parameter", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty()),
								SysmlPackage.eINSTANCE
										.getModelicaProperty_PropertyKind()),
						new FeatureInitializer(SysmlOCLFactory.getExpression(
								"AccessKind::public", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty()),
								SysmlPackage.eINSTANCE
										.getModelicaProperty_Access()) });

		/**
		 * @generated
		 */
		public static final ObjectInitializer ModelicaProperty_2002 = new ObjectInitializer(
				new FeatureInitializer[] {
						new FeatureInitializer(SysmlOCLFactory.getExpression(
								"VariabilityKind::none", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty()),
								SysmlPackage.eINSTANCE
										.getModelicaProperty_Variability()),
						new FeatureInitializer(SysmlOCLFactory.getExpression(
								"PropertyKind::part", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty()),
								SysmlPackage.eINSTANCE
										.getModelicaProperty_PropertyKind()),
						new FeatureInitializer(SysmlOCLFactory.getExpression(
								"AccessKind::public", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty()),
								SysmlPackage.eINSTANCE
										.getModelicaProperty_Access()) });

		/**
		 * @generated
		 */
		public static final ObjectInitializer ModelicaProperty_2003 = new ObjectInitializer(
				new FeatureInitializer[] {
						new FeatureInitializer(SysmlOCLFactory.getExpression(
								"VariabilityKind::none", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty()),
								SysmlPackage.eINSTANCE
										.getModelicaProperty_Variability()),
						new FeatureInitializer(SysmlOCLFactory.getExpression(
								"PropertyKind::variable", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty()),
								SysmlPackage.eINSTANCE
										.getModelicaProperty_PropertyKind()),
						new FeatureInitializer(SysmlOCLFactory.getExpression(
								"AccessKind::public", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty()),
								SysmlPackage.eINSTANCE
										.getModelicaProperty_Access()) });

		/** 
		 * @generated
		 */
		private Initializers() {
		}

		/** 
		 * @generated
		 */
		public static class ObjectInitializer {
			/** 
			 * @generated
			 */
			private FeatureInitializer[] initExpressions;

			/** 
			 * @generated
			 */
			ObjectInitializer(FeatureInitializer[] initExpressions) {
				this.initExpressions = initExpressions;
			}

			/** 
			 * @generated
			 */
			public void init(EObject instance) {
				for (int i = 0; i < initExpressions.length; i++) {
					FeatureInitializer nextExpr = initExpressions[i];
					try {
						nextExpr.init(instance);
					} catch (RuntimeException e) {
						SysmlDiagramEditorPlugin.getInstance().logError(
								"Feature initialization failed", e); //$NON-NLS-1$						
					}
				}
			}
		} // end of ObjectInitializer

		/** 
		 * @generated
		 */
		static class FeatureInitializer {

			/** 
			 * @generated
			 */
			private EStructuralFeature sFeature;

			/** 
			 * @generated
			 */
			private SysmlAbstractExpression expression;

			/**
			 * @generated
			 */
			FeatureInitializer(SysmlAbstractExpression expression,
					EStructuralFeature sFeature) {
				this.sFeature = sFeature;
				this.expression = expression;
			}

			/** 
			 * @generated
			 */
			void init(EObject contextInstance) {
				expression.assignTo(sFeature, contextInstance);
			}
		} // end of FeatureInitializer

	} // end of Initializers
}
