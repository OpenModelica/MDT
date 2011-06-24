/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.simulation.core.models.modelica;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * Represents the Modelica main class which contains all properties and their values.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 */
public class ModelicaMainclass {
	
	/** the name is just main. */
	private String name;
	
	/** Full qualified name of the model which will be used by OpenModelica to identify the model. */
	private String type;
	
	/** Comments on main class. */
	private String comment;
	
	/** Key= fullQualifiedName, Value= Variable object. */
	private Map<String, ModelicaProperty> variables;
	
	/** Key= fullQualifiedName, Value= Parameter object. */
	private Map<String, ModelicaProperty> parameters;
	
	/** Key= fullQualifiedName, Value= inputVariable object. */
	private Map<String, ModelicaProperty> inputVars;
	
	/** Key= fullQualifiedName, Value= outputVariable object. */
	private Map<String, ModelicaProperty> outputVars;
	/*
	 *To be extended with other containers e.g. help variables... 
	 */
	
	/**
	 * Defalt constructor.
	 *
	 * @param name Is the name of the Modelica main class
	 * @param type Is the class name of the Modelica main class
	 * @param comment All comments from the developer of this class
	 */
	public ModelicaMainclass(String name, String type, String comment) {
		super();
		this.comment = comment;
		this.name = name;
		this.type = type;
		
		variables =  new HashMap<String, ModelicaProperty>(); // Key= fullQualifiedName, Value= Variable object
		parameters =  new HashMap<String, ModelicaProperty>(); // Key= fullQualifiedName, Value= Parameter object
		inputVars  =  new HashMap<String, ModelicaProperty>(); // Key= fullQualifiedName, Value= inputVariable object
		outputVars  =  new HashMap<String, ModelicaProperty>(); // Key= fullQualifiedName, Value= outputVariable object
	}

	/**
	 * Just returns 'main'.
	 *
	 * @return name of the main class
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	
	/**
	 * OpenModelica uses the Type as unique identifier of the simulation system. This full qualified name represents the main class
	 * and will be used as executable name, init.txt file name,...
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Returns all in the variable collection containing variable names.
	 *
	 * @return set of strings with all full qualified variable names
	 */
	public Set<String> getAllFullQualifiedVariableNames(){
		return variables.keySet();
	}
	
	/**
	 * Returns all in the parameter collection containing parameter names.
	 *
	 * @return set of strings with all full qualified parameter names
	 */
	public Set<String> getAllFullQualifiedParameterNames(){
		return parameters.keySet();
	}
	
	/**
	 * Returns all in the inputVariables collection containing inputVariable names.
	 *
	 * @return set of strings with all full qualified inputVariable names
	 */
	public Set<String> getAllFullQualifiedInputVariableNames(){
		return inputVars.keySet();
	}
	
	/**
	 * Returns all in the OutputVariable collection containing OutputVariable names.
	 *
	 * @return set of strings with all full qualified OutputVariable names
	 */
	public Set<String> getAllFullQualifiedOutputVariableNames(){
		return outputVars.keySet();
	}
	
	/**
	 * Searches in the variable collection to return a Modelica Property from the math type state or algebraic
	 * with a given full qualified name.
	 *
	 * @param fullQualifiedName the full qualified name
	 * @return If the variable collection contains a Modelica variable with the name fullQualifiedName, the variable will be return.
	 * Otherwise a null will be returned
	 */
	public ModelicaProperty getVariableByName(String fullQualifiedName){
		if(variables.containsKey(fullQualifiedName)){
			return variables.get(fullQualifiedName);
		}			
		return null;
	}
	
	/**
	 * Searches in the parameter collection to return a Modelica Property from the math type parameter
	 * with a given full qualified name.
	 *
	 * @param fullQualifiedName the full qualified name
	 * @return If the parameter collection contains a Modelica parameter with the name fullQualifiedName, the parameter will be return.
	 * Otherwise a null will be returned
	 */
	public ModelicaProperty getParameterByName(String fullQualifiedName){
		if(parameters.containsKey(fullQualifiedName)){
			return parameters.get(fullQualifiedName);
		}			
		return null;
	}
	
	/**
	 * Searches in the InputVariable collection to return a Modelica Property from the math type InputVariable
	 * with a given full qualified name.
	 *
	 * @param fullQualifiedName the full qualified name
	 * @return If the InputVariable collection contains a Modelica InputVariable with the name fullQualifiedName, the parameter will be return.
	 * Otherwise a null will be returned
	 */
	public ModelicaProperty getInputVariableByName(String fullQualifiedName){
		if(inputVars.containsKey(fullQualifiedName)){
			return inputVars.get(fullQualifiedName);
		}			
		return null;
	}
	
	/**
	 * Searches in the OutputVariable collection to return a Modelica Property from the math type OutputVariable
	 * with a given full qualified name.
	 *
	 * @param fullQualifiedName the full qualified name
	 * @return If the OutputVariable collection contains a Modelica OutputVariable with the name fullQualifiedName, the parameter will be return.
	 * Otherwise a null will be returned
	 */
	public ModelicaProperty getOutputVariableByName(String fullQualifiedName){
		if(outputVars.containsKey(fullQualifiedName)){
			return outputVars.get(fullQualifiedName);
		}			
		return null;
	}
	
	/**
	 * Puts a new variable into the variables map
	 * If the variable name exists the value will be overwritten.
	 *
	 * @param variable from type ModelicaProperty
	 */
	public void addVariable(ModelicaProperty variable){
		variables.put(variable.getName(), variable);
	}
	
	/**
	 * Puts a new parameter into the parameters map
	 * If the parameter name exists the value will be overwritten.
	 *
	 * @param parameter from type ModelicaProperty
	 */
	public void addParameter(ModelicaProperty parameter){
		parameters.put(parameter.getName(), parameter);
	}
	
	/**
	 * Puts a new InputVariable into the InputVariable map
	 * If the InputVariable name exists the value will be overwritten.
	 *
	 * @param inputVariable the input variable
	 */
	public void addInputVariable(ModelicaProperty inputVariable){
		inputVars.put(inputVariable.getName(), inputVariable);
	}
	
	/**
	 * Puts a new OutputVariable into the OutputVariable map
	 * If the OutputVariable name exists the value will be overwritten.
	 *
	 * @param outputVariable the output variable
	 */
	public void addOutputVariable(ModelicaProperty outputVariable){
		outputVars.put(outputVariable.getName(), outputVariable);
	}
	
	/**
	 * Creates a copy of an existing Modelica Mainclass to avoid reference problems.
	 *
	 * @param srcModelicaMainclass a Modelica Mainclass object to be copied using its primitive values only
	 * @return a copy of the source Modelica Mainclass
	 */
	public static ModelicaMainclass createCopyOf(ModelicaMainclass srcModelicaMainclass){
		ModelicaMainclass destModelicaMainclass;
		
		destModelicaMainclass = new ModelicaMainclass(srcModelicaMainclass.getName(), srcModelicaMainclass.getType(), srcModelicaMainclass.getComment());
		
		for(String variableName : srcModelicaMainclass.getAllFullQualifiedVariableNames()){
			destModelicaMainclass.addVariable(ModelicaVariable.createCopyOf(srcModelicaMainclass.getVariableByName(variableName)));
		}
		
		for(String parameterName : srcModelicaMainclass.getAllFullQualifiedParameterNames()){
			destModelicaMainclass.addParameter(ModelicaParameter.createCopyOf(srcModelicaMainclass.getParameterByName(parameterName)));
		}
		
		//TODO [201009] ADD IN AND OUT VARIABLES
		
		return destModelicaMainclass;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String tempString = " Name: " + name + " Type: " + type + " Comment: " + comment;
		
		for (ModelicaProperty iterable_element : variables.values()) {
			tempString += "\n" + iterable_element.toString();
		}
		
		for (ModelicaProperty iterable_element : parameters.values()) {
			tempString += "\n" + iterable_element.toString();
		}
		
		for (ModelicaProperty iterable_element : inputVars.values()) {
			tempString += "\n" + iterable_element.toString();
		}
		
		for (ModelicaProperty iterable_element : outputVars.values()) {
			tempString += "\n" + iterable_element.toString();
		}
		
		return tempString;
	}
}
