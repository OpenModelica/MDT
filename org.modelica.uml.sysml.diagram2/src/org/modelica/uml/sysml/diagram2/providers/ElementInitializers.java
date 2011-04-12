package org.modelica.uml.sysml.diagram2.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.uml2.uml.UMLPackage;

import org.modelica.uml.sysml.SysmlPackage;

import org.modelica.uml.sysml.diagram2.expressions.SysmlAbstractExpression;
import org.modelica.uml.sysml.diagram2.expressions.SysmlOCLFactory;

import org.modelica.uml.sysml.diagram2.part.SysmlDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	/**
	 * @generated
	 */
	public static class Initializers {
		/**
		 * @generated
		 */
		public static final IObjectInitializer SatisfiedBy_1005 = new ObjectInitializer(
				SysmlPackage.eINSTANCE.getSatisfiedBy()) {
			protected void init() {
				add(createExpressionFeatureInitializer(UMLPackage.eINSTANCE
						.getNamedElement_Name(), SysmlOCLFactory.getExpression(
						"\'SatisfiedBy\'", //$NON-NLS-1$
						SysmlPackage.eINSTANCE.getSatisfiedBy())));
			}
		}; // SatisfiedBy_1005 ObjectInitializer		
		/**
		 * @generated
		 */
		public static final IObjectInitializer Satisfies_1006 = new ObjectInitializer(
				SysmlPackage.eINSTANCE.getSatisfies()) {
			protected void init() {
				add(createExpressionFeatureInitializer(UMLPackage.eINSTANCE
						.getNamedElement_Name(), SysmlOCLFactory.getExpression(
						"\'Satisfies\'", //$NON-NLS-1$
						SysmlPackage.eINSTANCE.getSatisfies())));
			}
		}; // Satisfies_1006 ObjectInitializer		
		/**
		 * @generated
		 */
		public static final IObjectInitializer ModelicaProperty_2001 = new ObjectInitializer(
				SysmlPackage.eINSTANCE.getModelicaProperty()) {
			protected void init() {
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_Access(), SysmlOCLFactory
						.getExpression("AccessKind::public", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty())));
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_PropertyKind(), SysmlOCLFactory
						.getExpression("PropertyKind::parameter", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty())));
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_Variability(), SysmlOCLFactory
						.getExpression("VariabilityKind::none", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty())));
			}
		}; // ModelicaProperty_2001 ObjectInitializer		
		/**
		 * @generated
		 */
		public static final IObjectInitializer ModelicaProperty_2002 = new ObjectInitializer(
				SysmlPackage.eINSTANCE.getModelicaProperty()) {
			protected void init() {
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_Access(), SysmlOCLFactory
						.getExpression("AccessKind::public", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty())));
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_PropertyKind(), SysmlOCLFactory
						.getExpression("PropertyKind::part", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty())));
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_Variability(), SysmlOCLFactory
						.getExpression("VariabilityKind::none", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty())));
			}
		}; // ModelicaProperty_2002 ObjectInitializer		
		/**
		 * @generated
		 */
		public static final IObjectInitializer ModelicaProperty_2003 = new ObjectInitializer(
				SysmlPackage.eINSTANCE.getModelicaProperty()) {
			protected void init() {
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_Variability(), SysmlOCLFactory
						.getExpression("VariabilityKind::none", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty())));
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_PropertyKind(), SysmlOCLFactory
						.getExpression("PropertyKind::variable", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty())));
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_Access(), SysmlOCLFactory
						.getExpression("AccessKind::public", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty())));
			}
		}; // ModelicaProperty_2003 ObjectInitializer		
		/**
		 * @generated
		 */
		public static final IObjectInitializer ModelicaProperty_2004 = new ObjectInitializer(
				SysmlPackage.eINSTANCE.getModelicaProperty()) {
			protected void init() {
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_Variability(), SysmlOCLFactory
						.getExpression("VariabilityKind::none", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty())));
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_PropertyKind(), SysmlOCLFactory
						.getExpression("PropertyKind::part", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty())));
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_Access(), SysmlOCLFactory
						.getExpression("AccessKind::public", //$NON-NLS-1$
								SysmlPackage.eINSTANCE.getModelicaProperty())));
			}
		}; // ModelicaProperty_2004 ObjectInitializer		
		/**
		 * @generated
		 */
		public static final IObjectInitializer ModelicaEquationProperty_2006 = new ObjectInitializer(
				SysmlPackage.eINSTANCE.getModelicaEquationProperty()) {
			protected void init() {
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_Variability(), SysmlOCLFactory
						.getExpression("VariabilityKind::none", //$NON-NLS-1$
								SysmlPackage.eINSTANCE
										.getModelicaEquationProperty())));
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_PropertyKind(), SysmlOCLFactory
						.getExpression("PropertyKind::equation", //$NON-NLS-1$
								SysmlPackage.eINSTANCE
										.getModelicaEquationProperty())));
				add(createExpressionFeatureInitializer(SysmlPackage.eINSTANCE
						.getModelicaProperty_Access(), SysmlOCLFactory
						.getExpression("AccessKind::public", //$NON-NLS-1$
								SysmlPackage.eINSTANCE
										.getModelicaEquationProperty())));
			}
		}; // ModelicaEquationProperty_2006 ObjectInitializer		
		/**
		 * @generated
		 */
		public static final IObjectInitializer SatisfyRelationship_3002 = new ObjectInitializer(
				SysmlPackage.eINSTANCE.getSatisfyRelationship()) {
			protected void init() {
				add(createExpressionFeatureInitializer(UMLPackage.eINSTANCE
						.getNamedElement_Name(), SysmlOCLFactory.getExpression(
						"\'<<satisfy>>\'", //$NON-NLS-1$
						SysmlPackage.eINSTANCE.getSatisfyRelationship())));
			}
		}; // SatisfyRelationship_3002 ObjectInitializer		
		/**
		 * @generated
		 */
		public static final IObjectInitializer DeriveReqRelationship_3003 = new ObjectInitializer(
				SysmlPackage.eINSTANCE.getDeriveReqRelationship()) {
			protected void init() {
				add(createExpressionFeatureInitializer(UMLPackage.eINSTANCE
						.getNamedElement_Name(), SysmlOCLFactory.getExpression(
						"\'<<deriveReqt>>\'", //$NON-NLS-1$
						SysmlPackage.eINSTANCE.getDeriveReqRelationship())));
			}
		}; // DeriveReqRelationship_3003 ObjectInitializer

		/** 
		 * @generated
		 */
		private Initializers() {
		}

		/** 
		 * @generated
		 */
		public static interface IObjectInitializer {
			/** 
			 * @generated
			 */
			public void init(EObject instance);
		}

		/** 
		 * @generated
		 */
		public static abstract class ObjectInitializer implements
				IObjectInitializer {
			/** 
			 * @generated
			 */
			final EClass element;
			/** 
			 * @generated
			 */
			private List featureInitializers = new ArrayList();

			/** 
			 * @generated
			 */
			ObjectInitializer(EClass element) {
				this.element = element;
				init();
			}

			/**
			 * @generated
			 */
			protected abstract void init();

			/** 
			 * @generated
			 */
			protected final IFeatureInitializer add(
					IFeatureInitializer initializer) {
				featureInitializers.add(initializer);
				return initializer;
			}

			/** 
			 * @generated
			 */
			public void init(EObject instance) {
				for (Iterator it = featureInitializers.iterator(); it.hasNext();) {
					IFeatureInitializer nextExpr = (IFeatureInitializer) it
							.next();
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
		interface IFeatureInitializer {
			/**
			 * @generated
			 */
			void init(EObject contextInstance);
		}

		/**
		 * @generated
		 */
		static IFeatureInitializer createNewElementFeatureInitializer(
				EStructuralFeature initFeature,
				ObjectInitializer[] newObjectInitializers) {
			final EStructuralFeature feature = initFeature;
			final ObjectInitializer[] initializers = newObjectInitializers;
			return new IFeatureInitializer() {
				public void init(EObject contextInstance) {
					for (int i = 0; i < initializers.length; i++) {
						EObject newInstance = initializers[i].element
								.getEPackage().getEFactoryInstance().create(
										initializers[i].element);
						if (feature.isMany()) {
							((Collection) contextInstance.eGet(feature))
									.add(newInstance);
						} else {
							contextInstance.eSet(feature, newInstance);
						}
						initializers[i].init(newInstance);
					}
				}
			};
		}

		/**
		 * @generated
		 */
		static IFeatureInitializer createExpressionFeatureInitializer(
				EStructuralFeature initFeature,
				SysmlAbstractExpression valueExpression) {
			final EStructuralFeature feature = initFeature;
			final SysmlAbstractExpression expression = valueExpression;
			return new IFeatureInitializer() {
				public void init(EObject contextInstance) {
					expression.assignTo(feature, contextInstance);
				}
			};
		}
	} // end of Initializers
}
