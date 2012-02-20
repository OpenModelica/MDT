package org.openmodelica.modelicaml.editor.xtext.valuebinding.validation;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.Check;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference;

public class ClientJavaValidator extends AbstractClientJavaValidator {


	@Check
	public void checkLeft_hand_component_reference(left_hand_component_reference cr) 
	{
		List<String> cList = ModelicaMLContentAssist.getFullModifiedComponentReferenceSortedList();
		List<String> cListCopy = new ArrayList<String>();
		cListCopy.addAll(cList);
		for (String string : cListCopy) {
			if (string.startsWith(ModelicaMLContentAssist.getPropertyName())) {
				cList.remove(string);
				cList.add(string.replaceFirst(ModelicaMLContentAssist.getPropertyName(), Constants.MACRO_clientPath));
			}
		}
		
		String dotPath = cr.getRef();
		String ref1DotPath = ""; 
		
		if (cr.getRef1().size() > 0) {
			for (String string : cr.getRef1()) {
				ref1DotPath = ref1DotPath + "." + string;
			}
			dotPath = dotPath + ref1DotPath;
		}
		if ( !cList.contains(dotPath) ) {
			error("left_hand_component_reference '" + dotPath + "' cannot be resolved to a class component", (EStructuralFeature) cr, ModeleditorPackage.COMPONENT_REFERENCE);
		}
	}
	
	@Check
	public void checkComponent_reference(component_reference cr) 
	{
		List<String> cList = ModelicaMLContentAssist.getFullComponentReferenceSortedList();
		List<String> cListCopy = new ArrayList<String>();
		cListCopy.addAll(cList);
		for (String string : cListCopy) {
			if (string.startsWith(ModelicaMLContentAssist.getPropertyName())) {
				cList.remove(string);
				cList.add(string.replaceFirst(ModelicaMLContentAssist.getPropertyName(), Constants.MACRO_clientPath));
			}
		}
		
		String dotPath = cr.getRef();
		String ref1DotPath = ""; 
		
		if (cr.getRef1().size() > 0) {
			for (String string : cr.getRef1()) {
				ref1DotPath = ref1DotPath + "." + string;
			}
			dotPath = dotPath + ref1DotPath;
		}
		if ( !cList.contains(dotPath) ) {
			error("component_reference '" + dotPath + "' cannot be resolved to a class component", (EStructuralFeature) cr, ModeleditorPackage.COMPONENT_REFERENCE);
		}
	}
	
	
	@Check
	public void checkname(name cr) 
	{
		List<String> cList = ModelicaMLContentAssist.getFullComponentReferenceSortedList();
		List<String> cListCopy = new ArrayList<String>();
		cListCopy.addAll(cList);
		for (String string : cListCopy) {
			if (string.startsWith(ModelicaMLContentAssist.getPropertyName())) {
				cList.remove(string);
				cList.add(string.replaceFirst(ModelicaMLContentAssist.getPropertyName(), Constants.MACRO_clientPath));
			}
		}
		
		cList.addAll(ModelicaMLContentAssist.getTypeSpecifierSortedList()); // use for redeclare in modifications ...
		
		if ( !cList.contains(cr.getName_ID()) ) {
			error("name '" + cr.getName_ID() + "' cannot be resolved", (EStructuralFeature) cr, ModeleditorPackage.NAME__NAME_ID);
		}
	}

}
