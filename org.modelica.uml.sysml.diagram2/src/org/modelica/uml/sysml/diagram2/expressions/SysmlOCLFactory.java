package org.modelica.uml.sysml.diagram2.expressions;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.ETypedElement;

import org.eclipse.emf.ocl.expressions.ExpressionsFactory;
import org.eclipse.emf.ocl.expressions.OCLExpression;
import org.eclipse.emf.ocl.expressions.OperationCallExp;
import org.eclipse.emf.ocl.expressions.Variable;

import org.eclipse.emf.ocl.expressions.util.AbstractVisitor;

import org.eclipse.emf.ocl.helper.HelperUtil;
import org.eclipse.emf.ocl.helper.IOCLHelper;
import org.eclipse.emf.ocl.helper.OCLParsingException;

import org.eclipse.emf.ocl.parser.EcoreEnvironment;
import org.eclipse.emf.ocl.parser.EcoreEnvironmentFactory;
import org.eclipse.emf.ocl.parser.Environment;
import org.eclipse.emf.ocl.parser.EvaluationEnvironment;

import org.eclipse.emf.ocl.query.Query;
import org.eclipse.emf.ocl.query.QueryFactory;

import org.eclipse.emf.ocl.types.util.Types;

import org.eclipse.emf.ocl.utilities.PredefinedType;

/**
 * @generated 
 */
public class SysmlOCLFactory {
	/**
	 * @generated 
	 */
	private SysmlOCLFactory() {
	}

	/**
	 * @generated 
	 */
	public static SysmlAbstractExpression getExpression(String body,
			EClassifier context, Map environment) {
		return new Expression(body, context, environment);
	}

	/**
	 * @generated 
	 */
	public static SysmlAbstractExpression getExpression(String body,
			EClassifier context) {
		return getExpression(body, context, Collections.EMPTY_MAP);
	}

	/**
	 * @generated 
	 */
	private static class Expression extends SysmlAbstractExpression {
		/**
		 * @generated 
		 */
		private Query query;

		/**
		 * @generated 
		 */
		public Expression(String body, EClassifier context, Map environment) {
			super(body, context, environment);

			IOCLHelper oclHelper = (environment.isEmpty()) ? HelperUtil
					.createOCLHelper() : HelperUtil
					.createOCLHelper(createCustomEnv(environment));
			oclHelper.setContext(context());
			try {
				OCLExpression oclExpression = oclHelper.createQuery(body);
				this.query = QueryFactory.eINSTANCE.createQuery(oclExpression);
			} catch (OCLParsingException e) {
				setStatus(IStatus.ERROR, e.getMessage(), e);
			}
		}

		/**
		 * @generated 
		 */
		protected Object doEvaluate(Object context, Map env) {
			if (query == null) {
				return null;
			}
			EvaluationEnvironment evalEnv = query.getEvaluationEnvironment();
			// init environment
			for (Iterator it = env.entrySet().iterator(); it.hasNext();) {
				Map.Entry nextEntry = (Map.Entry) it.next();
				evalEnv.replace((String) nextEntry.getKey(), nextEntry
						.getValue());
			}

			try {
				initExtentMap(context);
				Object result = query.evaluate(context);
				return (result != Types.OCL_INVALID) ? result : null;
			} finally {
				evalEnv.clear();
				query.setExtentMap(Collections.EMPTY_MAP);
			}
		}

		/**
		 * @generated
		 */
		protected Object performCast(Object value, ETypedElement targetType) {
			if (targetType.getEType() instanceof EEnum) {
				if (value instanceof EEnumLiteral) {
					EEnumLiteral literal = (EEnumLiteral) value;
					return (literal.getInstance() != null) ? literal
							.getInstance() : literal;
				}
			}
			return super.performCast(value, targetType);
		}

		/**
		 * @generated
		 */
		private void initExtentMap(Object context) {
			if (query == null || context == null) {
				return;
			}
			final Query queryToInit = query;
			final Object extentContext = context;

			queryToInit.setExtentMap(Collections.EMPTY_MAP);
			if (queryToInit.queryText() != null
					&& queryToInit.queryText().indexOf("allInstances") >= 0) {
				AbstractVisitor visitior = new AbstractVisitor() {
					private boolean usesAllInstances = false;

					public Object visitOperationCallExp(OperationCallExp oc) {
						if (!usesAllInstances) {
							usesAllInstances = PredefinedType.ALL_INSTANCES == oc
									.getOperationCode();
							if (usesAllInstances) {
								queryToInit
										.setExtentMap(EcoreEnvironmentFactory.ECORE_INSTANCE
												.createExtentMap(extentContext));
							}
						}
						return super.visitOperationCallExp(oc);
					}
				};
				queryToInit.getExpression().accept(visitior);
			}
		}

		/**
		 * @generated 
		 */
		private static EcoreEnvironmentFactory createCustomEnv(Map environment) {
			final Map env = environment;
			return new EcoreEnvironmentFactory() {
				public Environment createClassifierContext(Object context) {
					Environment ecoreEnv = super
							.createClassifierContext(context);
					for (Iterator it = env.keySet().iterator(); it.hasNext();) {
						String varName = (String) it.next();
						EClassifier varType = (EClassifier) env.get(varName);
						ecoreEnv.addElement(varName,
								createVar(varName, varType), false);
					}
					return ecoreEnv;
				}
			};
		}

		/**
		 * @generated 
		 */
		private static Variable createVar(String name, EClassifier type) {
			Variable var = ExpressionsFactory.eINSTANCE.createVariable();
			var.setName(name);
			var.setType(EcoreEnvironment.getOCLType(type));
			return var;
		}
	}
}
