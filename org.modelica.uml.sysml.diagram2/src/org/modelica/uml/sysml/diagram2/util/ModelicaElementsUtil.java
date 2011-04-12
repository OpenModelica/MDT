package org.modelica.uml.sysml.diagram2.util;

import java.util.LinkedList;
import java.util.Iterator;

import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.internal.impl.UMLFactoryImpl;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.common.util.EList; 
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelEditPart;

import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.compiler.ElementInfo;

import org.modelica.uml.sysml.diagram2.util.SetPropertiesUtil;
import org.modelica.uml.sysml.ModelicaType;
import org.modelica.uml.sysml.impl.ModelicaTypeImpl;

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
			
			final String typeName = element.getTypeName();
			UMLFactoryImpl umlFactory = new UMLFactoryImpl();
			PrimitiveType type = umlFactory.createPrimitiveType();
			type.setName(typeName);	
			EditPart modelEdp = classEDP.getParent();

			if (modelEdp instanceof ModelEditPart) {
				final Model model = (Model)((ModelEditPart)modelEdp).getDiagramView().getElement();
//				NewTypeCommand cmd = new NewTypeCommand(editingDomain, model, typeName);
//				editingDomain.getCommandStack().execute(cmd);
//				editingDomain.getCommandStack().flush();
//
//				type = cmd.getType();

			}

			if(componentEDP != null){
				SetPropertiesUtil.setModelProperties(editingDomain,
						componentEDP, 
						element.getElementVisibility(), 
						arrayDimension, 
						"", 
						direction, 
						false, 
						element.getNames(),  
						type,
						element.getKind());
			}
		}
		
	}

	protected static EditPart createComponent(ModelicaClassEditPart classEDP ,ElementInfo element){
		
		EditPart componentEDP = null;
		
		String kind = element.getVariability();
		
		if (kind != null){
			if(kind.equals("parameter")){
				componentEDP = SetPropertiesUtil.addParameter(classEDP);
			}	
			else if (kind.equals("unspecified")){
				componentEDP = SetPropertiesUtil.addVariable(classEDP);
			}
		}

		return componentEDP;
		
	}
	
	protected static void setComponentProperties(TransactionalEditingDomain editingDomain,
			EditPart componentEDP,
			ElementInfo element){
		
	}
	
	public class NewTypeCommand extends RecordingCommand {
		private Type newType = null;
		private Model model;
		private String typeName;
		NewTypeCommand(TransactionalEditingDomain domain, Model model, 
				String typeName) {
			super(domain);
			this.model = model;
			this.typeName = typeName;
			
		}
	
		protected void doExecute() {

			if (model != null) {
//				if (model.getOwnedType(typeName) == null) {
				newType = model.createOwnedPrimitiveType(typeName);
//				}
			}
		}
		
		public Type getType(){
			return newType;
		}
	}
}
