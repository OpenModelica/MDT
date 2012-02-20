package org.openmodelica.modelicaml.editor.xtext.declaration.validation;
 
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.ComposedChecks;
import org.openmodelica.modelicaml.editor.xtext.model.validation.ModeleditorJavaValidator;

@ComposedChecks(validators= {org.eclipse.xtext.validation.ImportUriValidator.class, org.eclipse.xtext.validation.NamesAreUniqueValidator.class})
public class AbstractDeclarationJavaValidator extends ModeleditorJavaValidator {

	@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(org.openmodelica.modelicaml.editor.xtext.declaration.declaration.DeclarationPackage.eINSTANCE);
		return result;
	}

}
