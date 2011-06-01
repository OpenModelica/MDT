package org.openmodelica.modelicaml.model.explorer.customization;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.gmt.modisco.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.gmt.modisco.infra.query.core.java.IJavaModelQuery;
import org.eclipse.gmt.modisco.infra.query.core.java.ParameterValueList;
import org.openmodelica.modelicaml.common.constants.Constants;

/** Checks if the dependency has a Value Binding stereotype */
public class HasValueBindingStereotype implements IJavaModelQuery<Element, Boolean> {
	public Boolean evaluate(final Element context, final ParameterValueList parameterValues)
			throws ModelQueryExecutionException {
		if (context instanceof Dependency) {
			if (context.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor) != null) {
				System.err.println("stereotypeQName_ProvidesValueFor" + context);
				return true;
			}
			if (context.getAppliedStereotype(Constants.stereotypeQName_ObtainsValueFrom) != null) {
				System.err.println("stereotypeQName_ObtainsValueFrom" + context);
				return true;
			}
		}
		return false;
	}
}
