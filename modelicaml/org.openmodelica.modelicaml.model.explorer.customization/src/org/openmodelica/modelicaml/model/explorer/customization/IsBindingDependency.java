package org.openmodelica.modelicaml.model.explorer.customization;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.gmt.modisco.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.gmt.modisco.infra.query.core.java.IJavaModelQuery;
import org.eclipse.gmt.modisco.infra.query.core.java.ParameterValueList;
import org.openmodelica.modelicaml.common.constants.Constants;

/** Checks if this dependency has a Value Binding stereotype */
public class IsBindingDependency implements IJavaModelQuery<Dependency, Boolean> {
	public Boolean evaluate(final Dependency context, final ParameterValueList parameterValues)
			throws ModelQueryExecutionException {
		if (context.getAppliedStereotype(Constants.stereotypeQName_ProvidesValueFor) != null) {
			return true;
		}
		if (context.getAppliedStereotype(Constants.stereotypeQName_ObtainsValueFrom) != null) {
			return true;
		}
		return false;
	}
}
