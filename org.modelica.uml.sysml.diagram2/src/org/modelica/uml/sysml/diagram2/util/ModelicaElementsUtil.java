package org.modelica.uml.sysml.diagram2.util;

import java.util.LinkedList;
import java.util.Iterator;

import org.eclipse.gef.EditPart;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEditPart;

import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.compiler.ElementInfo;

import org.modelica.uml.sysml.diagram2.util.SetPropertiesUtil;

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
		
		SetPropertiesUtil.setClassName(classEDP,className);
		
		handleClassInfo(classEDP,classInfo);
		
	}
	
	public static void handleClassInfo(ModelicaClassEditPart classEDP,
			LinkedList<ElementInfo> classInfo){
		
		Iterator iter = classInfo.iterator();
		while(iter.hasNext()){
			handleElement(classEDP, (ElementInfo)iter.next());
		}
		
		
	}
	
	protected static void handleElement(ModelicaClassEditPart classEDP ,ElementInfo element){
		
		if(element.getElementType().equals("component")){
			
			TransactionalEditingDomain editingDomain;
			editingDomain = classEDP.getEditingDomain();

			EditPart componentEDP;
			componentEDP = createComponent(classEDP , element); 

			int arrayDimension = 0;
			String direction = "none"; 
			if(element.getDirection().equals("input"))
				direction = "in";
			else if (element.getDirection().equals("output"))
				direction = "out";

			if(componentEDP != null){
				SetPropertiesUtil.setModelProperties(editingDomain,
						componentEDP, 
						element.getElementVisibility(), 
						arrayDimension, 
						"", 
						direction, 
						false, 
						element.getNames(),  
						element.getTypeName(), 
						element.getKind());
			}
		}
		
	}

	protected static EditPart createComponent(ModelicaClassEditPart classEDP ,ElementInfo element){
		
		EditPart componentEDP = null;
		
		//String kind = element.getKind();
		
		String kind = "parameter";
		
		if(kind.equals("parameter")){
			componentEDP = SetPropertiesUtil.addParameter(classEDP);
		}
		else if (kind.equals("unspecified")){
			componentEDP = SetPropertiesUtil.addVariable(classEDP);
		}
		
		return componentEDP;
		
	}
	
	protected static void setComponentProperties(TransactionalEditingDomain editingDomain,
			EditPart componentEDP,
			ElementInfo element){
		
	}
	
}
