
package org.modelica.xtext.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.CheckType;

public class ModelicaBaseCheckValidator extends org.eclipse.xtext.check.AbstractCheckValidator {

	public ModelicaBaseCheckValidator() {
		configure();
	}
	
	protected void configure() {
		addCheckFile("org::modelica::xtext::validation::ModelicaBaseFastChecks", CheckType.FAST);
		addCheckFile("org::modelica::xtext::validation::ModelicaBaseChecks", CheckType.NORMAL);
		addCheckFile("org::modelica::xtext::validation::ModelicaBaseExpensiveChecks", CheckType.EXPENSIVE);
	}
	
	@Override
	protected List<? extends EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(org.modelica.xtext.modelicaBase.ModelicaBasePackage.eINSTANCE);
		return result;
	}
	
}
