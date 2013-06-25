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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
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
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.modelica.importer.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.omc.OMCProxy;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.modelica.importer.Activator;
import org.openmodelica.modelicaml.modelica.importer.helper.StringHandler;


public class OpenModelicaCompilerCommunication {

	private OMCProxy omc;
	private ArrayList<String> commandHistory;
	private IProgressMonitor monitor;
	
	// TODO: should we have separate logs for notifications, warnings, and errors? 
	private String errorLog = "";
	
	
	
	public OpenModelicaCompilerCommunication(){

		this.omc = new OMCProxy();
		
		// set OMC reference in order to enable the shut down of omc process when Eclipse is closed
		Activator.setOmcProxy(omc);
		
		commandHistory = new ArrayList<String>();
	}
	
	
	private String executeCommand(String command) {
		
		// reply
		String replyString = "";
		
		// set the monitor sub task name
		setMonitorSubTaskName(command);
		
		/*
		 * Check if the command contains OMC error string as parameter in order to
		 * avoid cyclic commands execution 
		 */
		if (ModelicaMLServices.containsOMCErrorMessage(command)) {
			addToErrorLog(command);
			return "";
		}
		
		if (command != null && !command.isEmpty()) {
			
			commandHistory.add(command);
			
			try {
				
				ICompilerResult reply = omc.sendExpression(command, true);

				// find the next not empty positive result. 
				String[] results = reply.getResult();
				for (String string : results) {
					if (!string.trim().isEmpty()) {
						replyString = string;
					}
				}
				
				// catch the error string
				String errorString = reply.getError(); 
				if (errorString != null && !errorString.trim().isEmpty()) {
					addToErrorLog(command + "\n" + errorString);
				}
				
			} catch (Exception ex) {
				addToErrorLog(command + "\n" + ex.getMessage());
			}
		}

		return replyString;
	}
	
	/**
	 * Change directory to the directory given as a string.
	 * 
	 * @param dir
	 *            a directory given as a string. NOTE: USE slash "/" instead of "\\"
	 * @return Reply from OMC
	 */
	public String cd(String dir) {
		return executeCommand("cd(\"" + dir + "\")");
	}
	
	/**
	 * Request actual OMC working directory.
	 * 
	 * @return Reply from OMC, path to omc working directory
	 */
	public String cd() {
		return executeCommand("cd()");
	}
	
	/**
	 * Clear everything.
	 * @return Reply from OMC
	 */
	public String clear(){
		// reset the error log
		setErrorLog("");
		
		return executeCommand("clear()");
	}
	
	/**
	 * Instantiates a model/class and returns a string containing the flat class definition.
	 * Ex: instantiateModel(dcmotor)
	 *
	 * @param modelname the modelname
	 * @return Reply from OMC
	 */
	public String instantiateModel(String modelname){
		return executeCommand("instantiateModel("+ modelname + ")");
	}
	
	
	/**
	 * Clear the variables.
	 * @return Reply from OMC
	 */
	public String clearVariables(){
		return executeCommand("clearVariables()");
	}
	
	/**
	 * Return a string containing all class definitions.
	 * 
	 * @return Reply from OMC
	 */
	public String list() {
		return executeCommand("list()");
	}

	/**
	 * Return a string containing the class definition of the named class. Ex:
	 * list(dcmotor)
	 *
	 * @param modelname the modelname
	 * @return Reply from OMC
	 */
	public String list(String modelname) {
		return executeCommand("list(" + modelname + ")");
	}

	/**
	 * 
	 Return a vector of the currently defined variable names.
	 * 
	 * @return Reply from OMC
	 */
	public String listVariables() {
		return executeCommand(listVariables());
	}

	/**
	 * Load modelica file given as string argument. Ex:
	 * loadFile("../myLibrary/myModels.mo")
	 *
	 * @param strFile the str file
	 * @return Reply from OMC
	 */
	public String loadFile(String strFile) {
		return executeCommand("loadFile(\"" + strFile + "\")");
		
		// TODO: this is a workaround. remove the encoding after Acceleo will encode the files with UTF-8
//		return executeCommand("loadFile(\"" + strFile + "\", encoding=\""+Constants.fileEncoding+"\")"); 
	}

	/**
	 * Load model, function, or package relative to $OPENMODELICALIBRARY.
	 * Ex: loadModel(Modelica.Electrical) Note: if e.g. loadModel(Modelica) fails,
	 * you may have OPENMODELICALIBRARY pointing at the wrong location.
	 *
	 * @param name the name
	 * @return Reply from OMC
	 */
	public String loadModel(String name) {
		return executeCommand("loadModel(" + name + ")");
	}
	
	/**
	 * Define a model from "model Name" ... to ... "end Name"
	 * @param modelCode Full Modelica model code
	 * @return Reply from OMC
	 */
	public String defineModel(String modelCode) {
		return executeCommand(modelCode);
	}
	
	/**
	 * Translates a model but does not simulate it automatically.
	 *
	 * @param MainClass name of the main class
	 * @param outputFormat for the results. For now just the "plt" format will be supported.
	 * @return Reply from OMC
	 */
	public String buildModel(String MainClass, String outputFormat){
		return executeCommand("buildModel("+MainClass+ ", outputFormat=\""+ outputFormat +"\")");
	}
	
	/**
	 * Translates a model but does not simulate it automatically.
	 *
	 * @param MainClass name of the main class
	 * @param outputFormat for the results. For now just the "plt" format will be supported.
	 * @return Reply from OMC
	 */
	public String buildModel(String MainClass, String startTime, String stopTime, String numberOfIntervals, String tolerance, String method, String outputFormat){
		return executeCommand("buildModel("+MainClass+ ", startTime=" +startTime+ ", stopTime=" +stopTime+ ", numberOfIntervals="+numberOfIntervals+ ", tolerance="+tolerance+ ", method=\""+method + "\", outputFormat=\"" + outputFormat +"\")");
	}
	
	/**
	 * Translates a model and simulates it automatically.
	 *
	 * @param MainClass name of the main class
	 * @param outputFormat for the results. For now just the "plt" format will be supported.
	 * @return Reply from OMC
	 */
	public String simulate(String MainClass, String startTime, String stopTime, String numberOfIntervals, String tolerance, String method, String outputFormat){
		return executeCommand("simulate("+MainClass+ ", startTime=" +startTime+ ", stopTime=" +stopTime+ ", numberOfIntervals="+numberOfIntervals+ ", tolerance="+tolerance+ ", method=\""+method + "\", outputFormat=\"" + outputFormat +"\")");
	}
	
	/**
	 * Check a model.
	 *
	 * @param MainClass name of the main class
	 * @return Reply from OMC
	 */
	public String checkModel(String MainClass){
		return executeCommand("checkModel("+MainClass+")");
	}

	public String quit() {
		return executeCommand("quit()");
	}
	
	public String getErrorString(){
		return executeCommand("getErrorString()");
	}
	
	public String getInstallationDirectoryPath(){
		return executeCommand("getInstallationDirectoryPath()");
	}
	
	public String getTempDirectoryPath(){
		return executeCommand("getTempDirectoryPath()");
	}
	
	public String getModelicaPath(){
		return executeCommand("getModelicaPath()");
	}
	
	public String getVersion(){
		return executeCommand("getVersion()");
	}
	
	// Classes ****************************************************************************************************************
	public String getClassNamesString(String parentClassQName){
		if (parentClassQName.trim().equals("")) {
			return executeCommand("getClassNames("+parentClassQName+")");
		}
		else {
			// fetch also protected classes
			return executeCommand("getClassNames("+parentClassQName+", showProtected = true)");
		}
	}
	
	public List<String> getClassNames(String parentClassQName){
		
		List<String> items = new ArrayList<String>();
		
		String reply = "";
		String command = "";
		if (parentClassQName.trim().equals("")) {
			command = "getClassNames("+parentClassQName+")";
		}
		else {
			 // fetch also protected classes
			command = "getClassNames("+parentClassQName+", showProtected = true)";
		}
		
		reply = executeCommand(command);
		
		// add to error log
		if (!isValidGetItemsInCurlyBracesReply(reply)) {
			addToErrorLog(command);
			addToErrorLog(reply);
			
			items  = null;
		}
		else {
			items = getItems(reply);			
		}

		return items;
	}
	
	public String getClassNamesRecursive(String ownerName){
		return executeCommand("getClassNamesRecursive(" + ownerName + ")");
	}
	
	public String getClassInformation(String className){
		return executeCommand("getClassInformation(" + className + ")");
	}
	
	public String getClassRestriction(String className){
		return executeCommand("getClassRestriction(" + className + ")");
	}
	
	
	public boolean isProtectedClass(String owningClassQName, String protectedClassName){
		String reply = executeCommand("isProtectedClass(" + owningClassQName + ", \""+protectedClassName+"\")");
		return reply.trim().equals("true");
	}

	
	public String isConnector(String className){
		return executeCommand("isConnector(" + className + ")");
	}
	
	public String getNthInheritedClass(String className, String n){
		return executeCommand("getNthInheritedClass(" + className+ "," + n + ")");
	}
	
	
	public String getComponents(String className){
		return executeCommand("getComponents(" + className + ")");
	}
	
	public String getComponents(String className, String useDoubleQuotes){
		return executeCommand("getComponents(" + className + ", " + useDoubleQuotes + ")");
	}
	
	public String getComponentNames(String className){
		return executeCommand("getComponentNames(" + className + ")");
	}

	
	public String getDocumentationAnnotation(String className){
		return executeCommand("getDocumentationAnnotation(" + className + ")");
	}

	public String getNamedAnnotation(String className, String annotationName ){
		return executeCommand("getNamedAnnotation(" + className + "," + annotationName+")");
	}

	
	public String getComponentModifierNamesString(String className, String componentName){
		return executeCommand("getComponentModifierNames(" + className + ", " + componentName+ ")");
	}
	
	public List<String> getComponentModifierNames(String className, String componentName){
		List<String> items = new ArrayList<String>();
		String command = "getComponentModifierNames(" + className + ", " + componentName+ ")";
		String reply = executeCommand(command);
		
		// add to error log
		if (!isValidGetItemsInCurlyBracesReply(reply)) {
			addToErrorLog(command);
			addToErrorLog(reply);
			
			items  = null;
		}
		else {
			items = getItems(reply);			
		}
		
		return items;
	}
	
	public String getComponentModifierValue(String className, String componentName){
		return executeCommand("getComponentModifierValue(" + className + ", " + componentName+ ")");
	}
	
	
	
	
	// Extends ****************************************************************************************************************
	public String getExtendsModifierNamesString(String className, String extendedClassName){
		return executeCommand("getExtendsModifierNames(" + className + ", "  + extendedClassName + ")");
	}
	
	public List<String> getExtendsModifierNames(String className, String extendedClassName){
		List<String> items = new ArrayList<String>();
		String command = "getExtendsModifierNames(" + className + ", "  + extendedClassName + ")";
		String reply = executeCommand(command);
		
		// add to error log
		if (!isValidGetItemsInCurlyBracesReply(reply)) {
			addToErrorLog(command);
			addToErrorLog(reply);
			
			items  = null;
		}
		else {
			items = getItems(reply);			
		}
		
		return items;
	}
	
	public String getExtendsModifierValue(String className, String extendedClassName, String componentName){
		return executeCommand("getExtendsModifierValue(" + className + ", " + extendedClassName+ ", " + componentName+ ")");
	}
	
	public int getInheritanceCount(String className){
		String reply = executeCommand("getInheritanceCount(" + className + ")");
		reply = getCountValue(reply);
		
		if (reply != null && !ModelicaMLServices.containsOMCErrorMessage(reply)) {
			Integer count = Integer.valueOf(reply.trim());
			if ( count != null ) {
				return count;
			}
		}
		else {
			System.err.println("Could not complete the operation getInheritanceCount("+className+")");
		}
		return 0;
	}
	
	public List<String> getInheritedClasses(String className){
		int count = getInheritanceCount(className);
		List<String> inheritedClasses = new ArrayList<String>();
		if (count > 0 ) {
			for (int i = 1; i <= count; i++) {
				String reply = getNthInheritedClass(className, String.valueOf(i)).trim();
				if (!reply.equals("") && !ModelicaMLServices.containsOMCErrorMessage(reply)) {
					inheritedClasses.add(reply);
				}
			}
		}
		return inheritedClasses;
	}
	
	
	/*
	 * 	-	getNthComponentCondition(M1, 1) 	// returns the condition as a string e.g “if a”
		-	isEnumeration(M1)                 // retuns true/false
		-	getEnumerationLiterals(M1)        // returns enumerations literals as a list of strings e.g {"one","two","three"}
		
		-	getClassComment(M1)               // returns the class comment as a string e.g “a class comment”
		-	getComponentComment(M1, C1)       // returns the component comment as a string e.g “a component comment”. M1 is the class name and C1 is the component name.
		
		-	isReplaceable(M1, “C1”)           // returns true/false. M1 is the class name and C1 is the nested class name. This API only check the replaceable attribute of nested classes.
		-	getAnnotationCount(M1)            // returns the annotation count e.g 2
		-	getNthAnnotationString(M1, 1)     // returns the Nth annotation as a string.
		-	getImportCount(M1)                // returns the import count e.g 2
		-	getNthImport(M1, 1)               // returns the Nth import as a list of strings e.g {"Modelica.Electrical.Analog","B","named"} => {"PATH","ALIAS/ID","KIND"}. KIND can have four possible values (named, qualified,                                                                                                                                                           unqualified and multiple.
		-	getInitialAlgorithmCount(M1)      // returns the intial algorithm count e.g 2
		-	getNthInitialAlgorithm(M1, 1)     // returns the Nth intial algorithm as a string.
		-	getAlgorithmCount(M1)             // returns the algorithm count e.g 2
		-	getNthAlgorithm(M1, 1)            // returns the Nth algorithm as a string.
		-	getInitialEquationCount(M1)       // returns the intial equation count e.g 2
		-	getNthInitialEquation(M1, 1)      // returns the Nth initial equation as a string.
		-	getEquationCount(M1)              // returns the equation count e.g 2
		-	getNthEquation(M1, 1)             // returns the Nth equation as a string.
	 */
	
	
	public String getNthComponentCondition(String componentName, int number){
		return executeCommand("getNthComponentCondition(" + componentName + ", " + number+ ")").trim();
	}
	
	
	
	// Enumeration ****************************************************************************************************************
	public String isEnumeration(String className){
		return executeCommand("isEnumeration(" + className + ")").trim();
	}
	
	public String getEnumerationLiterals(String className){
		return executeCommand("getEnumerationLiterals(" + className + ")").trim();
	}
	
	public String isReplaceable(String className, String nestedClassName){
		return executeCommand("isReplaceable(" + className +", \"" + nestedClassName + "\")").trim();
	}
	
	
	
	// Annotation ****************************************************************************************************************
	public int getAnnotationCount(String className){
		String reply = executeCommand("getAnnotationCount(" + className + ")").trim();
		reply = getCountValue(reply);
		
		if (isCountValue(reply)) {
			Integer count = Integer.valueOf(reply.trim());
			if ( count != null ) {
				return count;
			}
		}
		else {
			System.err.println("Could not complete the operation getAnnotationCount("+className+")");
			addToErrorLog("Could not complete the operation getAnnotationCount("+className+")");
		}
		return 0;
	}
	
	public List<String> getAnnotations(String className){
		int count = getAnnotationCount(className);
		List<String> annotations = new ArrayList<String>();
		if (count > 0 ) {
			for (int i = 1; i <= count; i++) {
				String reply = getNthAnnotationString(className, String.valueOf(i)).trim();
				if (!reply.equals("") && !ModelicaMLServices.containsOMCErrorMessage(reply)) {
					String stringInBrackets = StringHandler.removeFirstLastDoubleQuotes(reply.trim().replaceFirst("annotation", "").trim());
					String annotationString = StringHandler.removeFirstLastBrackets(stringInBrackets.substring(0, stringInBrackets.length() - 1));
					annotations.add(annotationString);
				}
			}
		}
		return annotations;
	}
	
	public String getNthAnnotationString(String elementName, String number){
		return executeCommand("getNthAnnotationString(" + elementName + ", " + number+ ")").trim();
	}
	
	
	
	// Import ****************************************************************************************************************
	public int getImportCount(String className){
		String reply = executeCommand("getImportCount(" + className + ")").trim();
		reply = getCountValue(reply);
		
		if (isCountValue(reply) ) {
			Integer count = Integer.valueOf(reply.trim());
			if ( count != null ) {
				return count;
			}
		}
		else {
			System.err.println("Could not complete the operation getImportCount("+className+")");
			addToErrorLog("Could not complete the operation getImportCount("+className+")");
		}
		return 0;
	}
	
	public String getNthImport(String className, int number){
		return executeCommand("getNthImport(" + className + ", " + number+ ")").trim();
	}
	
	
	
	
	// Algorithm ****************************************************************************************************************
	public int getInitialAlgorithmCount(String className){
		String reply = executeCommand("getInitialAlgorithmCount(" + className + ")").trim();
		reply = getCountValue(reply);
		
		if (isCountValue(reply) ) {
			Integer count = Integer.valueOf(reply.trim());
			if ( count != null ) {
				return count;
			}
		}
		else {
			System.err.println("Could not complete the operation getInitialAlgorithmCount("+className+")");
			addToErrorLog("Could not complete the operation getInitialAlgorithmCount("+className+")");
		}
		return 0;
	}

	public String getNthInitialAlgorithm(String className, String number){
		return executeCommand("getNthInitialAlgorithm(" + className + ", " + number+ ")").trim();
	}
	
	public List<String> getInitialAlgorithms(String className){
		int count = getInitialAlgorithmCount(className);
		List<String> initialAlgorithms = new ArrayList<String>();
		if (count > 0 ) {
			for (int i = 1; i <= count; i++) {
				String reply = getNthInitialAlgorithm(className, String.valueOf(i)).trim();
				if (!reply.equals("") && !ModelicaMLServices.containsOMCErrorMessage(reply) ) {
					String string = StringHandler.removeFirstLastDoubleQuotes(reply.trim());
					initialAlgorithms.add(replaceSpecChars(string));
				}
			}
		}
		return initialAlgorithms;
	}

	
	public int getAlgorithmCount(String className){
		String reply = executeCommand("getAlgorithmCount(" + className + ")").trim();
		reply = getCountValue(reply);
		
		if (isCountValue(reply)) {
			Integer count = Integer.valueOf(reply.trim());
			if ( count != null ) {
				return count;
			}
		}
		else {
			System.err.println("Could not complete the operation getAlgorithmCount("+className+")");
			addToErrorLog("Could not complete the operation getAlgorithmCount("+className+")");
		}
		return 0;
	}

	public String getNthAlgorithm(String className, String number){
		return executeCommand("getNthAlgorithm(" + className + ", " + number+ ")").trim();
	}
	
	public List<String> getAlgorithms(String className){
		int count = getAlgorithmCount(className);
		List<String> algorithms = new ArrayList<String>();
		if (count > 0 ) {
			for (int i = 1; i <= count; i++) {
				String reply = getNthAlgorithm(className, String.valueOf(i)).trim();
				if (!reply.equals("") && !ModelicaMLServices.containsOMCErrorMessage(reply) ) {
					String string = StringHandler.removeFirstLastDoubleQuotes(reply.trim());
					algorithms.add(replaceSpecChars(string));
				}
			}
		}
		return algorithms;
	}
	
	

	// Equations ****************************************************************************************************************
	public int getInitialEquationCount(String className){
		String reply = executeCommand("getInitialEquationCount(" + className + ")").trim();
		reply = getCountValue(reply);
		
		if (isCountValue(reply) ) {
			Integer count = Integer.valueOf(reply.trim());
			if ( count != null ) {
				return count;
			}
		}
		else {
			System.err.println("Could not complete the operation getInitialEquationCount("+className+")");
			addToErrorLog("Could not complete the operation getInitialEquationCount("+className+")");
		}
		return 0;
	}

	
	public String getNthInitialEquation(String className, String number){
		return executeCommand("getNthInitialEquation(" + className + ", " + number+ ")").trim();
	}
	
	
	public List<String> getInitialEquations(String className){
		int count = getInitialEquationCount(className);
		List<String> initialEquations = new ArrayList<String>();
		if (count > 0 ) {
			for (int i = 1; i <= count; i++) {
				String reply = getNthInitialEquation(className, String.valueOf(i)).trim();
				if (!reply.equals("") && !ModelicaMLServices.containsOMCErrorMessage(reply) ) {
					String string = StringHandler.removeFirstLastDoubleQuotes(reply.trim());
					initialEquations.add(replaceSpecChars(string));

				}
			}
		}
		return initialEquations;
	}
	


	public int getEquationCount(String className){
		String reply = executeCommand("getEquationCount(" + className + ")").trim();
		reply = getCountValue(reply);
		
		if (isCountValue(reply)) {
			Integer count = Integer.valueOf(reply.trim());
			if ( count != null ) {
				return count;
			}
		}
		else {
			System.err.println("Could not complete the operation getEquationCount("+className+")");
			addToErrorLog("Could not complete the operation getEquationCount("+className+")");
		}
		return 0;
	}

	
	public String getNthEquation(String className, String number){
		return executeCommand("getNthEquation(" + className + ", " + number+ ")").trim();
	}
	
	
	public List<String> getEquations(String className){
		int count = getEquationCount(className);
		List<String> equations = new ArrayList<String>();
		if (count > 0 ) {
			for (int i = 1; i <= count; i++) {
				String reply = getNthEquation(className, String.valueOf(i)).trim().trim();
				if (!reply.equals("") && !ModelicaMLServices.containsOMCErrorMessage(reply)) {
					String string = StringHandler.removeFirstLastDoubleQuotes(reply.trim());
					equations.add(replaceSpecChars(string));
				}
			}
		}
		return equations;
	}
	
	
	
	
	/*
	 * Utls ****************************************************************************************************************
	 */
	
	private String replaceSpecChars(String string){
		String newString = string.replaceAll("\\\\" + "\"", "\"");
		return newString;
	}
	
	
	/*
	 * NOTE, this operation is a workaround in order to remove  
	 * possible string that are warnings or errors reported by compiler.
	 */
	private String getCountValue(String reply){
		String count = "";
		
		//remove the double quotes if there are any
		reply = reply.replaceAll("\"", "").trim();
		
		if (!reply.isEmpty()) {
			int i=1;
			String letter = reply.substring(0,1);
			int length = reply.length();
			while ( (letter.matches("[0-9]") || letter.matches("[0-9]*")) && i <= length ) {
				count = letter.substring(0,i);
				letter = reply.substring(0,i);
				i++;
			}
		}
		return count;
		
	}
	
	private boolean isCountValue(String reply) {

		if (	reply != null 
				&& !reply.isEmpty() 
				&& (reply.matches("[0-9]") || reply.matches("[0-9]*")) 
			) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean isValidGetItemsInCurlyBracesReply(String reply){
		
		if (!reply.trim().startsWith("{")) {
			return false;
		}
		if (reply.trim().startsWith("Error") ){
			return false;
		}
		return true;
	}
	
	
	private List<String> getItems(String string){
		
		/*
		 * Note, this is used in order to cut off all warnings and 
		 * errors reported by the compiler. Those are appended after the 
		 * second "}" to the reply. 
		 */
		if (string.startsWith("{")) {
			string = StringHandler.getStringInCurlyBraces(string).trim();
		}
		else {
			String msg = "NOT A VALID COMPILER API REPLY: " + string;
			System.err.println(msg);
			addToErrorLog(msg);
			
			return null;
		}
		
		List<String> items = new ArrayList<String>();
		
		if (string != null && !string.isEmpty() && !ModelicaMLServices.containsOMCErrorMessage(string)) {
			
//			String[] splitted = string.trim().substring(1, string.length() - 2).split(",");
			String[] splitted = string.split(",");
			if (splitted.length > 0 ) {
				for (int i = 0; i < splitted.length; i++) {
					String item = splitted[i].trim();
					if (!item.equals("")) {
						items.add(item);
					}
				}
			}
		}

		if (items.size() > 0 ) {
			return items;	
		}
		else {
			return null;
		}
	}
	
	
	
	public ArrayList<String> getCommandHistory() {
//		ArrayList<String> tempHistory = new ArrayList<String>();
//		Collections.copy(tempHistory, history);
//		return tempHistory;
		
		return this.commandHistory;
	}

	public IProgressMonitor getMonitor() {
		return monitor;
	}

	public void setMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}
	
//	private void setMonitorTaskName(String name){
//		if (this.monitor != null) {
//			monitor.setTaskName(name);
//		}
//	}
	
	private void setMonitorSubTaskName(String name){
		if (this.monitor != null) {
			monitor.subTask(name);
		}
	}

	public String getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}
	
	private void addToErrorLog(String msg){
		this.errorLog += "\n\n" + msg;
	}
	
}
