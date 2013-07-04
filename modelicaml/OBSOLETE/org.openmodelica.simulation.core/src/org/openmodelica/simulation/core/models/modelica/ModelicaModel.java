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

// TODO: Auto-generated Javadoc
/**
 * Represents the whole Modelica model with all data which are necessary to simulate a model
 * It contains general model information and a Modelica main class with all properties and their values.
 *
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@eads.com
 */
public class ModelicaModel {
	
	/*
	 * General model information
	 */
	/**
	 * Model name.
	 * NOTE: The Model name is just an identifier for the root model name. It is not the full qualified model name!
	 * The full qualified model name is the Type of the mainclass.
	 * The ModelicaModel name should only be used in the tree view of the model properties
	 * @see #ModelicaMainclass
	 */
	private String name;
	/**
	 * Model identification number. 
	 * this id is a composition of the hash values from the project name
	 * version and the current system time modulo 1000000 to keep the value small
	 * NOTE: THIS ID IS NOT IMPLEMENTED YET
	 */
	private String id;
	
	/** Model version. */
	private String version;
	
	/** Model author. */
	private String author;
	
	/** A company which has right on this model. */
	private String company;
	
	/** Developer or User comments. */
	private String comment;
	
	/** Number of State variables. */
	private Integer n_states;
	
	/** Number of Algebraic variables. */
	private Integer n_algebraics;
	
	/** Number of Parameters. */
	private Integer n_parameters;
	
	/** Number of InputVariables (Not used yet). */
	private Integer n_inputVars;
	
	/** Number of OutPutVariables (Not used yet). */
	private Integer n_outputVars;
	
	/** Number of IntegerParameter (Not used yet). */
	private Integer n_int_parameters;
	
	/** Number of IntegerVariables (Not used yet). */
	private Integer n_int_variables;
	
	/** Number of BoolParameters (Not used yet). */
	private Integer n_bool_parameters;
	
	/** Number of BoolVariables (Not used yet). */
	private Integer n_bool_variables;	
	
	/** Number of string parameters  (Not used yet). */
	private Integer n_string_parameters;
	
	/** Number of string variables (Not used yet). */
	private Integer n_string_variables;
	
	/** Modelica main class which contains all properties and their values. */
	private ModelicaMainclass mainclass;
	
	/**
	 * Instantiates a new modelica model.
	 */
	public ModelicaModel(){
		super();
	}	

	/**
	 * This Constructor can be used if the general model information are available yet e.g. while reading a "SimInit.xml" or "SimConfig.xml"
	 * @param name Model name
	 * @param id Model identification number
	 * @param version Model version
	 * @param author Model author
	 * @param company A company which has right on this model 
	 * @param comment Developer or User comments
	 * @param n_states Number of State variables
	 * @param n_algebraics Number of Algebraic variables
	 * @param n_parameters Number of Parameters
	 * @param n_inputVars Number of InputVariables (Not used yet)
	 * @param n_outputVars Number of OutPutVariables (Not used yet)
	 * @param n_int_parameters Number of IntegerParameter
	 * @param n_int_variables Number of IntegerVariables
	 * @param n_bool_parameters Number of BooleanParameters
	 * @param n_bool_variables Number if BooleanVariables
	 * @param n_string_parameters Number of string parameters  (Not used yet)
	 * @param n_string_variables Number of string variables (Not used yet)
	 */
	public ModelicaModel(String name, String id, String version, String author, String company, String comment, Integer n_states, Integer n_algebraics,
			Integer n_parameters, Integer n_inputVars, Integer n_outputVars, Integer n_int_parameters, Integer n_int_variables, Integer n_bool_parameters, Integer n_bool_variables, Integer n_string_parameters, Integer n_string_variables) {
		super();
		this.name = name;
		this.id = id;
		this.version = version;
		this.author = author;
		this.company = company;
		this.comment = comment;
		this.n_states = n_states;
		this.n_algebraics = n_algebraics;
		this.n_parameters = n_parameters;
		this.n_inputVars = n_inputVars;
		this.n_outputVars = n_outputVars;
		this.n_int_parameters = n_int_parameters;
		this.n_int_variables = n_int_variables;
		this.n_bool_parameters = n_bool_parameters;
		this.n_bool_variables = n_bool_variables;
		this.n_string_parameters = n_string_parameters;
		this.n_string_variables = n_string_variables;
	}
	
//	/**
//	 * The "modelsimulation.exe" works only correct with its corresponding "modelconfig.xml".
//	 * To proof their togetherness there is a need for a unique id.
//	 * This id is a composition of the hash values from the project name, version and the current system time
//	 * modulo 1000000 to keep the value small 
//	 */
//	private void createID() {	
//		
//		Long time = new Long(System.currentTimeMillis());
//		Integer addedHashCodes = name.hashCode() + version.hashCode() + time.hashCode();
//		Integer abs = java.lang.Math.abs(addedHashCodes);
//		Integer mod = abs % 1000000;
//		id = mod + "";
//		
//	}

//	/**
//	 * Constructor for reading a "ModelConfig.xml" where a id is already generated
//	 * @param name
//	 * @param id
//	 * @param comment
//	 * @param version
//	 * @param n_states
//	 * @param n_algebraics
//	 * @param n_parameters
//	 * @param n_string_parameters
//	 * @param n_string_variables
//	 */
//	public ModelicaModel(String name, String id, String comment, String version, Integer n_states, Integer n_algebraics,
//			Integer n_parameters, Integer n_string_parameters, Integer n_string_variables) {
//		super();
//		this.name = name;
//		this.id = id;
//		this.comment = comment;
//		this.version = version;
//		this.n_algebraics = n_algebraics;
//		this.n_parameters = n_parameters;
//		this.n_states = n_states;
//		this.n_string_parameters = n_string_parameters;
//		this.n_string_variables = n_string_variables;
//	}

	/**
 * Only the model name e.g. TanksConnectedPI
 *
 * @return the name
 * @see #name
 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId(){
		return id;
	}
	
	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public String getCompany() {
		return company;
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
	 * Gets the n_states.
	 *
	 * @return the n_states
	 */
	public Integer getN_states() {
		return n_states;
	}

	/**
	 * Gets the n_algebraics.
	 *
	 * @return the n_algebraics
	 */
	public Integer getN_algebraics() {
		return n_algebraics;
	}

	/**
	 * Gets the n_parameters.
	 *
	 * @return the n_parameters
	 */
	public Integer getN_parameters() {
		return n_parameters;
	}
	
	/**
	 * Gets the n_input vars.
	 *
	 * @return the n_input vars
	 */
	public Integer getN_inputVars() {
		return n_inputVars;
	}

	/**
	 * Gets the n_output vars.
	 *
	 * @return the n_output vars
	 */
	public Integer getN_outputVars() {
		return n_outputVars;
	}
	
	/**
	 * Gets the n_int_parameters.
	 *
	 * @return the n_int_parameters
	 */
	public Integer getN_int_parameters() {
		return n_int_parameters;
	}

	/**
	 * Gets the n_int_variables.
	 *
	 * @return the n_int_variables
	 */
	public Integer getN_int_variables() {
		return n_int_variables;
	}

	/**
	 * Gets the n_bool_parameters.
	 *
	 * @return the n_bool_parameters
	 */
	public Integer getN_bool_parameters() {
		return n_bool_parameters;
	}

	/**
	 * Gets the n_bool_variables.
	 *
	 * @return the n_bool_variables
	 */
	public Integer getN_bool_variables() {
		return n_bool_variables;
	}

	/**
	 * Gets the n_string_parameters.
	 *
	 * @return the n_string_parameters
	 */
	public Integer getN_string_parameters() {
		return n_string_parameters;
	}

	/**
	 * Gets the n_string_variables.
	 *
	 * @return the n_string_variables
	 */
	public Integer getN_string_variables() {
		return n_string_variables;
	}


	/**
	 * Gets the mainclass.
	 *
	 * @return the mainclass
	 */
	public ModelicaMainclass getMainclass() {
		return mainclass;
	}
	
	/**
	 * Sets the mainclass.
	 *
	 * @param mainclass the new mainclass
	 */
	public void setMainclass(ModelicaMainclass mainclass){
		this.mainclass = mainclass;
	}
	
	/**
	 * Creates the copy of.
	 *
	 * @param s the s
	 * @return the modelica model
	 */
	public static ModelicaModel createCopyOf(ModelicaModel s){
	
		ModelicaModel destModelicaModel;
		
		destModelicaModel = new ModelicaModel(s.getName(), s.getId(), s.getVersion(), s.getAuthor(), s.getCompany(), s.getComment(), s.getN_states(), s.getN_algebraics(), s.getN_parameters(), s.getN_inputVars(), s.getN_outputVars(), s.getN_int_parameters(), s.getN_int_variables(), s.getN_bool_parameters(), s.getN_bool_variables(), s.getN_string_parameters(), s.getN_string_variables());
		destModelicaModel.setMainclass(ModelicaMainclass.createCopyOf(s.getMainclass()));
		
		return destModelicaModel;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " Name: " + name + " Id: " + id + " Version: " + version + mainclass.toString();
	}
	
	/**
	 * Creates an example Modelica model containing the following data
	 *  name "Project"
	 *  id "4021"
	 *  version "1.2.2"
	 *  author "Parham"
	 *  company "EADS"
	 *  comment "No comment yet"
	 *  
	 *  Also containing a Modelica main class: "TwoTanksMain", "TanksConnectedPI", "This is the Mainclass"
	 *  With a variable "tank1.h", "Real", "No comments yet"
	 *  
	 * @return a full initialized ModelicaModel with example data, an example modelica main class and a variable
	 */
	public static ModelicaModel createExampleProject(){
		ModelicaModel mp = new ModelicaModel("Project", "4021", "1.2.2", "Parham", "EADS", "No comment yet", 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
		ModelicaMainclass mainclass = new ModelicaMainclass("TwoTanksMain", "TanksConnectedPI", "This is the Mainclass");
		mp.setMainclass(mainclass);
		ModelicaVariable mv = new ModelicaVariable("tank1.h", "Real", "No comments yet");
		try {
			mv.setPrimitiveValue(new Double("1.1"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainclass.addVariable(mv);
		
		System.out.println("Name: " + mp.getMainclass().getVariableByName("tank1.h").getName());
		System.out.println("Value: " + mp.getMainclass().getVariableByName("tank1.h").getPrimitiveValue());
		System.out.println("ID: " + mp.id);
		
		return mp;
	}
	
}
