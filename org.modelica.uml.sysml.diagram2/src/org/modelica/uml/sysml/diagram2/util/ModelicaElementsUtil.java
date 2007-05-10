package org.modelica.uml.sysml.diagram2.util;

import java.util.LinkedList;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEditPart;

import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.compiler.ElementInfo;

public class ModelicaElementsUtil {
	
	private void ModelicaElementsUtil(){
		
	}
	
	public static void handleclass(ModelicaClassEditPart classEDP, String className) {
		
		LinkedList<ElementInfo> classInfo = null;
		
		try{
		classInfo= (LinkedList<ElementInfo>)CompilerProxy.getElements(className);
		}
		catch(Exception e){
			
		}
		
		handleClassInfo(classEDP,classInfo);
		
	}
	
	public static void handleClassInfo(ModelicaClassEditPart classEDP,
			LinkedList<ElementInfo> classInfo){
		
		TransactionalEditingDomain editingDomain;
		editingDomain = classEDP.getEditingDomain();
		
	}

}
