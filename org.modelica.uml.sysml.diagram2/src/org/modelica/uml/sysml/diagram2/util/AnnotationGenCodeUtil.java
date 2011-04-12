package org.modelica.uml.sysml.diagram2.util;

import org.modelica.uml.sysml.Requirement;

public class AnnotationGenCodeUtil {
	
	private void AnnotationGenCodeUtil(){
		
	}

	public String RequirementAnnotationCode(Requirement req){
		String code = "annotation { /n";
		code += "Requirement ( /n"; 
		
		if(!req.getName().isEmpty()){
			code += "name = { " + req.getName() +" },/n";
		}			
//		if(req.getLevel() != null){
			code += "level = { " + req.getLevel() +" },/n";
			
//		}
		if(!req.getId().isEmpty()) {
			code += "id = { " + req.getId() +" }, /n"; 
		}
//		if(req.getPriority() != null){
			code += "priority = { " + req.getPriority() +" }, /n"; 
//		}
		
		
		code += " )}";
		
		return code;
	}
}
