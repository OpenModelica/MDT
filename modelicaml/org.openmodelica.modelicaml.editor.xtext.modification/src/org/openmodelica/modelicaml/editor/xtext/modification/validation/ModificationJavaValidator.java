package org.openmodelica.modelicaml.editor.xtext.modification.validation;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.Check;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier;
 

public class ModificationJavaValidator extends AbstractModificationJavaValidator {


	@Check
	public void checkLeft_hand_component_reference(left_hand_component_reference cr) 
	{
		List<String> cList = ModelicaMLContentAssist.getFullModifiedComponentReferenceSortedList();

		String dotPath = cr.getRef();
		String ref1DotPath = ""; 
		
		if (cr.getRef1().size() > 0) {
			for (String string : cr.getRef1()) {
				ref1DotPath = ref1DotPath + "." + string;
			}
			dotPath = dotPath + ref1DotPath;
		}
		if ( !cList.contains(dotPath) ) {
			error("left_hand_component_reference '" + dotPath + "' cannot be resolved to a class component", (EStructuralFeature) cr,  ModeleditorPackage.COMPONENT_REFERENCE);
		}
	}

	
	@Check
	public void checktype_specifier(type_specifier cr) 
	{
		//error("type_specifier '" + cr.getName() + "' cannot be resolved to a type_specifier", ModeleditorPackage.NAME__NAME_ID);

		List<String> cList = ModelicaMLContentAssist.getTypeSpecifierSortedList();
		if ( !cList.contains(cr.getName().getName_ID()) ) {
			error("type_specifier '" + cr.getName().getName_ID() + "' cannot be resolved", (EStructuralFeature) cr, ModeleditorPackage.NAME__NAME_ID);
		}
	}

}
