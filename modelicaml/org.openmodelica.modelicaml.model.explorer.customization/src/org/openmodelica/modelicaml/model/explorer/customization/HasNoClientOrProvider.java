package org.openmodelica.modelicaml.model.explorer.customization;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.gmt.modisco.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.gmt.modisco.infra.query.core.java.IJavaModelQuery;
import org.eclipse.gmt.modisco.infra.query.core.java.ParameterValueList;

/** Determines if a dependency has empty clients or provider definition. */
public class HasNoClientOrProvider implements IJavaModelQuery<Dependency, Boolean> {
	public Boolean evaluate(final Dependency context, final ParameterValueList parameterValues)
			throws ModelQueryExecutionException {
		
		if (context.getClients() == null || context.getSuppliers() == null) {
			return true;
		}
		
		return false;
	}
}
