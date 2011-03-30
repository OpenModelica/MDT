package org.openmodelica.modelicaml.model.explorer.customization;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.gmt.modisco.infra.query.core.java.IJavaModelQuery;
import org.eclipse.gmt.modisco.infra.query.core.java.ParameterValueList;

/** Returns the label for import dependencies */
public class GetImportsLabel implements IJavaModelQuery<Classifier, String> {
	public String evaluate(final Classifier context, final ParameterValueList parameterValues)
			throws ModelQueryExecutionException {

		String label = "";
		Boolean hasImports = false;
		
		EList<Dependency> clientsList = context.getClientDependencies();
		
		for (Dependency dependency : clientsList) {
			Element supplier = dependency.getSuppliers().get(0);
			if (supplier instanceof Class || supplier instanceof Enumeration) {
				hasImports = true;
				if (label.equals("")) {
					label = ((NamedElement)supplier).getName();
				}
				else {
					label = label + ", " + ((NamedElement)supplier).getName();
				}
			}
		}
		if (hasImports) {
			return "import " + label;
		}
		return null;
	}
}
