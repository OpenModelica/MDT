package org.openmodelica.modelicaml.model.explorer.customization;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.gmt.modisco.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.gmt.modisco.infra.query.core.java.IJavaModelQuery;
import org.eclipse.gmt.modisco.infra.query.core.java.ParameterValueList;

/** Returns the label for a dependency */
public class GetDependecyLabel implements IJavaModelQuery<Dependency, String> {
	public String evaluate(final Dependency context, final ParameterValueList parameterValues)
			throws ModelQueryExecutionException {

		Element client = context.getClients().get(0);
//		System.err.println("client: " + client.eClass());
//		String clientName = "??";
		
		Element supplier = context.getSuppliers().get(0);
//		System.err.println("supplier: " + supplier.eClass());
//		String supplierName = "??";
		
		if (client instanceof Class) {
//			clientName = ((Class)client).getName();
			if (supplier instanceof Class || supplier instanceof Enumeration) {
				return "import of " + ((NamedElement)supplier).getName();
			}
		}
		
		return context.getName();
	}
}
