package org.openmodelica.modelicaml.editor.xtext.model.validation;

import java.util.List;

import org.eclipse.xtext.validation.Check;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name;
 

public class ModeleditorJavaValidator extends AbstractModeleditorJavaValidator {

	@Check
	public void checkComponent_reference(component_reference cr) 
	{
		List<String> cList = ModelicaMLContentAssist.getFullComponentReferenceSortedList();
		// this is added in order to avoid concurrent modifications. TODO: test it!
		cList.addAll(ModelicaMLContentAssist.getFullModifiedComponentReferenceSortedList()); 


		String dotPath = cr.getRef();
		String ref1DotPath = ""; 
		
		if (cr.getRef1().size() > 0) {
			for (String string : cr.getRef1()) {
				ref1DotPath = ref1DotPath + "." + string;
			}
			dotPath = dotPath + ref1DotPath;
		}
		// TODO: do not raise errors for functions from the MSL because there is no validation yet.
		// i.e. there is no list of MSL functions available for code completion validation.
		if ( !dotPath.startsWith("Modelica.") && !dotPath.startsWith(".Modelica.") 
				&& !cList.contains(dotPath) ) {
			error("component_reference '" + dotPath + "' cannot be resolved to a class component", null,  ModeleditorPackage.COMPONENT_REFERENCE);
		}
	}
	
	
	@Check
	public void checkname(name cr) 
	{
		List<String> cList = ModelicaMLContentAssist.getFullComponentReferenceSortedList();
		// this is added in order to avoid concurrent modifications. TODO: test it!
		cList.addAll(ModelicaMLContentAssist.getFullModifiedComponentReferenceSortedList()); 

		
		cList.addAll(ModelicaMLContentAssist.getTypeSpecifierSortedList()); // use for redeclare in modifications ...
		
		// TODO: do not raise errors for functions from the MSL because there is no validation yet.
		// i.e. there is no list of MSL functions available for code completion validation.
		if ( !cr.getName_ID().startsWith("Modelica.") && !cr.getName_ID().startsWith(".Modelica.") 
				&& !cList.contains(cr.getName_ID()) ) {
			error("name '" + cr.getName_ID() + "' cannot be resolved",  null, ModeleditorPackage.NAME__NAME_ID);
		}
	}

}
