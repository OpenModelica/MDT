package org.openmodelica.modelicaml.editor.xtext.valuebinding.validation;

import java.util.List;

import org.eclipse.xtext.validation.Check;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name;
 

public class MediatorJavaValidator extends AbstractMediatorJavaValidator {

	@Check
	public void checkComponent_reference(component_reference cr) 
	{
		List<String> cList = ModelicaMLContentAssist.getFullComponentReferenceSortedList();

		String dotPath = cr.getRef();
		String ref1DotPath = ""; 
		
		if (cr.getRef1().size() > 0) {
			for (String string : cr.getRef1()) {
				ref1DotPath = ref1DotPath + "." + string;
			}
			dotPath = dotPath + ref1DotPath;
		}
		if ( !cList.contains(dotPath) ) {
			error("component_reference '" + dotPath + "' cannot be resolved to a class component", ModeleditorPackage.COMPONENT_REFERENCE);
		}
	}
	
	
	@Check
	public void checkname(name cr) 
	{
		List<String> cList = ModelicaMLContentAssist.getFullComponentReferenceSortedList();
		
		cList.addAll(ModelicaMLContentAssist.getTypeSpecifierSortedList()); // use for redeclare in modifications ...
		
		if ( !cList.contains(cr.getName_ID()) ) {
			error("name '" + cr.getName_ID() + "' cannot be resolved", ModeleditorPackage.NAME__NAME_ID);
		}
	}

}
