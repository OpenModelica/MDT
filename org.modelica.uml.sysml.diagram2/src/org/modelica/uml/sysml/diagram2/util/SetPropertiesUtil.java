package org.modelica.uml.sysml.diagram2.util;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.wizard.ProgressMonitorPart;

import org.eclipse.swt.layout.FillLayout;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.common.ui.services.properties.ICompositePropertySource;
import org.eclipse.gmf.runtime.common.ui.services.properties.extended.PropertySource;
import org.eclipse.gmf.runtime.common.ui.services.properties.PropertiesServiceAdapterFactory;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.diagram.ui.requests.ChangePropertyValueRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.core.commands.SetPropertyCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.emf.ui.properties.commands.SetModelPropertyValueCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.UMLPackage;
import org.modelica.uml.sysml.AccessKind;
import org.modelica.uml.sysml.PropertyKind;
import org.modelica.uml.sysml.FlowDirection;
import org.modelica.uml.sysml.VariabilityKind;
import org.modelica.uml.sysml.SysmlPackage;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassName2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParametersEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassVariablesEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEquationsEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPropertyEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty3EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaEquationPropertyEditPart;
import org.modelica.uml.sysml.diagram2.part.SysmlDiagramEditorPlugin;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;
import org.modelica.uml.sysml.diagram2.providers.SysmlPropertyProvider;

import org.eclipse.ui.views.properties.IPropertySource; 

import org.eclipse.uml2.uml.UMLPackage;

public class SetPropertiesUtil {

	private SetPropertiesUtil(){

	}
	
	public static void setModelProperties(TransactionalEditingDomain editingDomain,
			EditPart parameterEDP,
			String access,
			int arrayDimension,
			String defaultValue,
			String direction,
			boolean isFlow,
			String name,
			Object type,
			String variability){
		
		setAccessFeatureValue(editingDomain, parameterEDP, access);
		setArrayDimProperty(editingDomain, parameterEDP,arrayDimension);
		setDefaultProperty(editingDomain, parameterEDP, defaultValue);
		setDirectionProperty(editingDomain,parameterEDP, direction);
		setIsFlowProperty(editingDomain, parameterEDP, isFlow);
		setNameProperty(editingDomain, parameterEDP, name);
		setVariabilityKindProperty(editingDomain, parameterEDP, variability);
		
	}

	public static void setAccessFeatureValue(TransactionalEditingDomain editingDomain, EditPart container, String value){

		AccessKind newValue = null;

		if(container.getModel() instanceof NodeImpl){
			
			newValue = AccessKind.get(value);

			EObject elementToEdit = ((NodeImpl)container.getModel()).getElement();
			EStructuralFeature propertyFeature = SysmlPackage.Literals.MODELICA_PROPERTY__ACCESS;

			
			
			if(newValue != null){
			SetRequest setPropertyFeatureRequest = new SetRequest(elementToEdit, propertyFeature , newValue);
			SetValueCommand setPropertyFeatureCommand = new SetValueCommand(setPropertyFeatureRequest);


			//This code is kept as an example
			
			IPropertySource propertySource = 
				new PropertiesServiceAdapterFactory().getPropertySource(container);
			
			SetModelPropertyValueCommand setAccessProp = new SetModelPropertyValueCommand( editingDomain, 
					"Set Property command", container,  propertySource, 
					propertyFeature, newValue); 
			
			String propertyID = ""+propertyFeature.getFeatureID();
			String propertyName = propertyFeature.getName();
			ChangePropertyValueRequest changePropertyRequest =
									new ChangePropertyValueRequest(propertyName,
														propertyID,
			 											newValue);
			
			SetPropertyCommand changePropertCmd = new SetPropertyCommand(editingDomain, new EObjectAdapter(elementToEdit),
					propertyID, propertyName, newValue);
			
			
			Command command = container.getCommand(changePropertyRequest);
		
			try{
				setAccessProp.execute(new NullProgressMonitor(), null);
			}
			catch(Exception e){

			}

			}
		}
	}


	public static void setClassName(ModelicaClassEditPart partToEdit, String newName){

		//Here we change the name of the displayed figure

		ModelicaClassName2EditPart nameEditPart;

		nameEditPart = (ModelicaClassName2EditPart)partToEdit.getPrimaryChildEditPart();
		if(nameEditPart != null){

			EObject elementToEdit = ((NodeImpl)partToEdit.getModel()).getElement();

			EStructuralFeature nameFeature = UMLPackage.eINSTANCE.getNamedElement().getEStructuralFeature(
			"name");



			SetRequest setNameRequest = new SetRequest(elementToEdit, nameFeature , newName);
			SetValueCommand setNameCommand = new SetValueCommand(setNameRequest);

			try{
				setNameCommand.execute(new NullProgressMonitor(), null);
			}
			catch(Exception e){

			}

		}		
	}

	public static ModelicaPropertyEditPart addParameter(ModelicaClassEditPart parent){

		if(parent.getModel() instanceof NodeImpl){



			ModelicaClassParametersEditPart parameterEDP = 
				(ModelicaClassParametersEditPart)parent.getChildBySemanticHint("5001");	

			IElementType elementType = SysmlElementTypes.ModelicaProperty_2001;
			EObject elementToEdit = ((NodeImpl)parent.getModel()).getElement();


			CreateElementRequest addParameterRequest = 
				new CreateElementRequest(elementToEdit, elementType);

			CreateElementCommand addParameterCommand = new CreateElementCommand(addParameterRequest);

			//trying another method to create an element
			
			/*CreateViewAndElementRequest addParamRequest = new CreateViewAndElementRequest
											(elementType,elementToEdit, 
											SysmlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);*/
			CreateViewRequest addParamRequest = CreateViewRequestFactory
			.getCreateShapeRequest(elementType, 
					SysmlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			
			Command command = parent.getCommand(addParamRequest);
			
			DiagramCommandStack commandStack = parent.getDiagramEditDomain()
			.getDiagramCommandStack();
			
			//Get the children list before creation
			List oldChildrenList = new LinkedList();
			oldChildrenList.addAll(parameterEDP.getChildren());

			if(command.canExecute())
				commandStack.execute(command);
			
			
			/*
			try{
				addParameterCommand.execute(new NullProgressMonitor(), null);
			}
			catch(Exception e){

			}*/

			//Get the children list after creation 
			//and remove the old ones
			List newChildrenList = new LinkedList();
			newChildrenList.addAll(parameterEDP.getChildren());
			newChildrenList.removeAll(oldChildrenList);

//			assumes the remaining children in the list are 1
			//It should be so if no threads intervene
			//in the future
			return (ModelicaPropertyEditPart)newChildrenList.get(0);		
		}

		return null;

	}


	public static ModelicaProperty3EditPart addVariable(ModelicaClassEditPart parent){

		if(parent.getModel() instanceof NodeImpl){

			ModelicaClassVariablesEditPart variablesEDP = 
				(ModelicaClassVariablesEditPart)parent.getChildBySemanticHint("5003");	

			IElementType elementType = SysmlElementTypes.ModelicaProperty_2003;


			CreateViewRequest createRequest = CreateViewRequestFactory
			.getCreateShapeRequest(elementType, 
					SysmlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

			Command command = parent.getCommand(createRequest);

			//Get the children list before creation
			List oldChildrenList = new LinkedList();
			oldChildrenList.addAll(variablesEDP.getChildren());

//			Create the new element
			DiagramCommandStack commandStack = parent.getDiagramEditDomain()
			.getDiagramCommandStack();
			commandStack.execute(command);

			//Get the children list after creation 
			//and remove the old ones
			List newChildrenList = new LinkedList();
			newChildrenList.addAll(variablesEDP.getChildren());
			newChildrenList.removeAll(oldChildrenList);

//			assumes the remaining children in the list are 1
			//It should be so if no threads intervene
			//in the future
			return (ModelicaProperty3EditPart)newChildrenList.get(0);		
		}

		return null;	
	}
	
	public static ModelicaEquationPropertyEditPart addEquation(ModelicaClassEditPart parent){
		
		if(parent.getModel() instanceof NodeImpl){

			ModelicaClassEquationsEditPart variablesEDP = 
				(ModelicaClassEquationsEditPart)parent.getChildBySemanticHint("5006");	

			IElementType elementType = SysmlElementTypes.ModelicaEquationProperty_2006;


			CreateViewRequest createRequest = CreateViewRequestFactory
			.getCreateShapeRequest(elementType, 
					SysmlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

			Command command = parent.getCommand(createRequest);

			//Get the children list before creation
			List oldChildrenList = new LinkedList();
			oldChildrenList.addAll(variablesEDP.getChildren());

//			Create the new element
			DiagramCommandStack commandStack = parent.getDiagramEditDomain()
			.getDiagramCommandStack();
			commandStack.execute(command);

			//Get the children list after creation 
			//and remove the old ones
			List newChildrenList = new LinkedList();
			newChildrenList.addAll(variablesEDP.getChildren());
			newChildrenList.removeAll(oldChildrenList);

//			assumes the remaining children in the list are 1
			//It should be so if no threads intervene
			//in the future
			return (ModelicaEquationPropertyEditPart)newChildrenList.get(0);		
		}

		return null;	

		
	}
	
	public static void setArrayDimProperty(TransactionalEditingDomain editingDomain, EditPart parameterEDP, int newValue){
		
		if(parameterEDP.getModel() instanceof NodeImpl){

			EObject elementToEdit = ((NodeImpl)parameterEDP.getModel()).getElement();
			EStructuralFeature propertyFeature = SysmlPackage.Literals.MODELICA_PROPERTY__ARRAY_DIMENSION;

			String dimension = ""+newValue;
			SetRequest setPropertyFeatureRequest = new SetRequest(elementToEdit, propertyFeature , dimension);
			SetValueCommand setPropertyFeatureCommand = new SetValueCommand(setPropertyFeatureRequest);
			
			
			IPropertySource propertySource = 
				new PropertiesServiceAdapterFactory().getPropertySource(parameterEDP);
			
			
			SetModelPropertyValueCommand setAccessProp = new SetModelPropertyValueCommand( editingDomain, 
					"Set Property command", parameterEDP,  propertySource, 
					propertyFeature, dimension);

			try{
				setAccessProp.execute(new NullProgressMonitor(), null);
			}
			catch(Exception e){

			}


		}

		
	}
	
	public static void setDefaultProperty(TransactionalEditingDomain editingDomain, EditPart parameterEDP, String newValue){
		
		if(parameterEDP.getModel() instanceof NodeImpl){

			EObject elementToEdit = ((NodeImpl)parameterEDP.getModel()).getElement();
			EStructuralFeature propertyFeature = UMLPackage.Literals.PROPERTY__DEFAULT;
			
			SetRequest setPropertyFeatureRequest = new SetRequest(elementToEdit, propertyFeature , newValue);
			SetValueCommand setPropertyFeatureCommand = new SetValueCommand(setPropertyFeatureRequest);
			
			IPropertySource propertySource = 
				new PropertiesServiceAdapterFactory().getPropertySource(parameterEDP);
			
			
			SetModelPropertyValueCommand setAccessProp = new SetModelPropertyValueCommand( editingDomain, 
					"Set Property command", parameterEDP,  propertySource, 
					propertyFeature, newValue);


			try{
				setAccessProp.execute(new NullProgressMonitor(), null);
			}
			catch(Exception e){

			}


		}		
	}

	public static void setDirectionProperty(TransactionalEditingDomain editingDomain, EditPart parameterEDP, String newValue){
		
		if(parameterEDP.getModel() instanceof NodeImpl){

			EObject elementToEdit = ((NodeImpl)parameterEDP.getModel()).getElement();
			EStructuralFeature propertyFeature = SysmlPackage.Literals.MODELICA_PROPERTY__DIRECTION;
	
			FlowDirection newDirection = FlowDirection.get(newValue);
			
			IPropertySource propertySource = 
				new PropertiesServiceAdapterFactory().getPropertySource(parameterEDP);
			
			
			SetModelPropertyValueCommand setAccessProp = new SetModelPropertyValueCommand( editingDomain, 
					"Set Property command", parameterEDP,  propertySource, 
					propertyFeature, newDirection);

			
			SetRequest setPropertyFeatureRequest = new SetRequest(elementToEdit, propertyFeature , newDirection);
			SetValueCommand setPropertyFeatureCommand = new SetValueCommand(setPropertyFeatureRequest);
			
			
			try{
				setAccessProp.execute(new NullProgressMonitor(), null);
			}
			catch(Exception e){

			}


		}		

		
	}
	
	public static void setIsFlowProperty(TransactionalEditingDomain editingDomain, EditPart parameterEDP, boolean newValue){

		if(parameterEDP.getModel() instanceof NodeImpl){

			EObject elementToEdit = ((NodeImpl)parameterEDP.getModel()).getElement();
			EStructuralFeature propertyFeature = SysmlPackage.Literals.MODELICA_PROPERTY__IS_FLOW;

			SetRequest setPropertyFeatureRequest = new SetRequest(elementToEdit, propertyFeature , newValue);
			SetValueCommand setPropertyFeatureCommand = new SetValueCommand(setPropertyFeatureRequest);

			IPropertySource propertySource = 
				new PropertiesServiceAdapterFactory().getPropertySource(parameterEDP);
			
			
			SetModelPropertyValueCommand setAccessProp = new SetModelPropertyValueCommand( editingDomain, 
					"Set Property command", parameterEDP,  propertySource, 
					propertyFeature, newValue);

			

			try{
				setAccessProp.execute(new NullProgressMonitor(), null);
			}
			catch(Exception e){

			}
		}


	}

	public static void setNameProperty(TransactionalEditingDomain editingDomain, EditPart parameterEDP, String newValue){

		if(parameterEDP.getModel() instanceof NodeImpl){

			EObject elementToEdit = ((NodeImpl)parameterEDP.getModel()).getElement();
			EStructuralFeature propertyFeature = UMLPackage.eINSTANCE.getNamedElement().getEStructuralFeature(
			"name");

			SetRequest setPropertyFeatureRequest = new SetRequest(elementToEdit, propertyFeature , newValue);
			SetValueCommand setPropertyFeatureCommand = new SetValueCommand(setPropertyFeatureRequest);

			IPropertySource propertySource = 
				new PropertiesServiceAdapterFactory().getPropertySource(parameterEDP);
			
			
			SetModelPropertyValueCommand setAccessProp = new SetModelPropertyValueCommand( editingDomain, 
					"Set Property command", parameterEDP,  propertySource, 
					propertyFeature, newValue);

			

			try{
				setAccessProp.execute(new NullProgressMonitor(), null);
			}
			catch(Exception e){

			}
		}


	}

	//not necessary. The property is set when the parameter, variable or equqtion
	//is created
	/*public static void setPropertyKindProperty(TransactionalEditingDomain editingDomain, EditPart parameterEDP, String newValue){

		if(parameterEDP.getModel() instanceof NodeImpl){

			EObject elementToEdit = ((NodeImpl)parameterEDP.getModel()).getElement();
			EStructuralFeature propertyFeature = SysmlPackage.Literals.MODELICA_PROPERTY__PROPERTY_KIND;

			PropertyKind newKind = PropertyKind.get(newValue); 
			
			SetRequest setPropertyFeatureRequest = new SetRequest(elementToEdit, propertyFeature , newKind);
			SetValueCommand setPropertyFeatureCommand = new SetValueCommand(setPropertyFeatureRequest);
			
			IPropertySource propertySource = 
				new PropertiesServiceAdapterFactory().getPropertySource(parameterEDP);
			
			
			SetModelPropertyValueCommand setAccessProp = new SetModelPropertyValueCommand( editingDomain, 
					"Set Property command", parameterEDP,  propertySource, 
					propertyFeature, newKind);


			try{
				setAccessProp.execute(new NullProgressMonitor(), null);
			}
			catch(Exception e){

			}
		}


	}*/

	public static void setVariabilityKindProperty(TransactionalEditingDomain editingDomain, EditPart parameterEDP, String newValue){

		if(parameterEDP.getModel() instanceof NodeImpl){

			EObject elementToEdit = ((NodeImpl)parameterEDP.getModel()).getElement();
			EStructuralFeature propertyFeature = SysmlPackage.Literals.MODELICA_PROPERTY__VARIABILITY;

			VariabilityKind newKind = VariabilityKind.get(newValue); 
			
			SetRequest setPropertyFeatureRequest = new SetRequest(elementToEdit, propertyFeature , newKind);
			SetValueCommand setPropertyFeatureCommand = new SetValueCommand(setPropertyFeatureRequest);
			
			IPropertySource propertySource = 
				new PropertiesServiceAdapterFactory().getPropertySource(parameterEDP);
			
			
			SetModelPropertyValueCommand setAccessProp = new SetModelPropertyValueCommand( editingDomain, 
					"Set Property command", parameterEDP,  propertySource, 
					propertyFeature, newKind);

	
			try{
				setAccessProp.execute(new NullProgressMonitor(), null);
			}
			catch(Exception e){

			}
		}


	}
	
	public static void setTypeProperty(TransactionalEditingDomain editingDomain, EditPart parameterEDP, String newValue){

		if(parameterEDP.getModel() instanceof NodeImpl){

			EObject elementToEdit = ((NodeImpl)parameterEDP.getModel()).getElement();
			EStructuralFeature propertyFeature = UMLPackage.Literals.TYPED_ELEMENT__TYPE;
			
			 
			
			SetRequest setPropertyFeatureRequest = new SetRequest(elementToEdit, propertyFeature , newValue);
			SetValueCommand setPropertyFeatureCommand = new SetValueCommand(setPropertyFeatureRequest);
			
			IPropertySource propertySource = 
				new PropertiesServiceAdapterFactory().getPropertySource(parameterEDP);
			
			
			SetModelPropertyValueCommand setAccessProp = new SetModelPropertyValueCommand( editingDomain, 
					"Set Property command", parameterEDP,  propertySource, 
					propertyFeature, newValue);

	
			try{
				setAccessProp.execute(new NullProgressMonitor(), null);
			}
			catch(Exception e){

			}
		}


	}

	
	public static void setEquationProperty(TransactionalEditingDomain editingDomain, EditPart parameterEDP, String newValue){
		
		if(parameterEDP.getModel() instanceof NodeImpl){
			
			

			EObject elementToEdit = ((NodeImpl)parameterEDP.getModel()).getElement();
			EStructuralFeature propertyFeature = SysmlPackage.Literals.MODELICA_EQUATION_PROPERTY__EQUATION; 
			
			IPropertySource propertySource = 
				new PropertiesServiceAdapterFactory().getPropertySource(parameterEDP);
			
			
			SetModelPropertyValueCommand setAccessProp = new SetModelPropertyValueCommand( editingDomain, 
					"Set Property command", parameterEDP,  propertySource, 
					propertyFeature, newValue);

	
			try{
				setAccessProp.execute(new NullProgressMonitor(), null);
			}
			catch(Exception e){

			}
		}


		
	}
	
}