package org.openmodelica.modelicaml.modelica.importer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openmodelica.modelicaml.modelica.importer.helper.StringHandler;
import org.openmodelica.modelicaml.modelica.importer.omc.corba.OMCProxy;


public class OpenModelicaCompilerCommunication {

	private OMCProxy omc;
	private ArrayList<String> history;
	
	public OpenModelicaCompilerCommunication(){
		super();
		this.omc = new OMCProxy();
		history = new ArrayList<String>();
	}
	
	/*
	 * EXAMPLES:
	 * 
	 * history.add(0, "getVersion()"); history.add(0, "loadModel(Modelica)");
	 * history.add(0, "getClassNames()"); history.add(0,
	 * "getClassNames(...class name...)"); history.add(0,
	 * "loadFile(\"...file...\")"); history.add(0, "list()"); history.add(0,
	 * "list(...class name...)"); history.add(0, "simulate(...class name...)");
	 * history.add(0, "plot(...variable name...)"); history.add(0,
	 * "plot({...var1, var2,...})"); history.add(0,
	 * "instantiateModel(...class name...");
	 * 
	 * executeCommand("\n" + "model BouncingBall\n"+
	 * "  parameter Real e=0.7 \"coefficient of restitution\";\n" +
	 * "  parameter Real g=9.81 \"gravity acceleration\";\n" +
	 * "  Real h(start=1) \"height of ball\";\n" +
	 * "  Real v \"velocity of ball\";\n" +
	 * "  Boolean flying(start=true)  \"true, if ball is flying\";\n" +
	 * "  Boolean impact;\n" + "  Real v_new;\n" + "  Integer foo;\n" +
	 * "equation\n" + "  impact = h <= 0.0;\n" +
	 * "  foo = if impact then 1 else 2;\n" +
	 * "  der(v) = if flying then -g else 0;\n" + "  der(h) = v;\n" +
	 * "  when {h <= 0.0 and v <= 0.0,impact} then\n" +
	 * "    v_new = if edge(impact) then -e*pre(v) else 0;\n" +
	 * "    flying = v_new > 0;\n" + "    reinit(v, v_new);\n" + "  end when;\n"
	 * + "end BouncingBall;\n"); executeCommand("getErrorString()");
	 * executeCommand("instantiateModel(BouncingBall)");
	 * executeCommand("getErrorString()");
	 * executeCommand("instantiateModel(BouncingBall)");
	 * executeCommand("getErrorString()");
	 * executeCommand("simulate(BouncingBall,stopTime=3.5)");
	 * executeCommand("getErrorString()");
	 * executeCommand("plot({h, impact, v})");
	 * executeCommand("getErrorString()");
	 * executeCommand("readFile(\"sim_dcmotor.mos\")");
	 * executeCommand("getErrorString()");
	 * executeCommand("runScript(\"sim_dcmotor.mos\")");
	 * executeCommand("getErrorString()");
	 */
	
	/**
	 * Execute command.
	 *
	 * @param command A command to OMC e.g. loadModel(Modelica), simulate(...), ...
	 * @return the string
	 */
	private String executeCommand(String command) {
//		System.err.println("Expression:" + command);

		/**
		 * Reply from OMC
		 */
		String reply = "no reply";

		if (command != null && command.length() > 0) {
			history.add(command);
			try {
				reply = omc.sendExpression(command);
			} catch (Exception ex) {
				reply = "\nError while sending expression: " + command + "\n"
						+ ex.getMessage();
			}
		} else {
			reply = ("\nNo expression sent because is empty");
		}

		return reply;
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
	
	
	/**
	 * Leave OpenModelica and kill the process.
	 * 
	 * @return Reply from OMC
	 */
	public String quit() {
		return executeCommand("quit()");
	}
	
	/**
	 * Get a possible error String from omc after executing a command.
	 *
	 * @return error string as reply from omc
	 */
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
	
	public String getNamedAnnotation(String className, String annotationName ){
		return executeCommand("getNamedAnnotation(" + className + "," + annotationName+")");
	}
	
	public String getClassNames(String parentClassQName){
		return executeCommand("getClassNames("+parentClassQName+")");
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

	public String isConnector(String className){
		return executeCommand("isConnector(" + className + ")");
	}
	
//	public String getInheritanceCount(String className){
//		return executeCommand("getInheritanceCount(" + className + ")");
//	}
	
	public String getNthInheritedClass(String className, String n){
		return executeCommand("getNthInheritedClass(" + className+ "," + n + ")");
	}
	
	public String getComponents(String className){
		return executeCommand("getComponents(" + className + ")");
	}
	
	public String getComponentNames(String className){
		return executeCommand("getComponentNames(" + className + ")");
	}
	
	public String getDocumentationAnnotation(String className){
		return executeCommand("getDocumentationAnnotation(" + className + ")");
	}
	
	
	
//	getTempDirectoryPath()
//	getModelicaPath()
//	getVersion()
//	getNamedAnnotation(Modelica,version)
//	getClassNames()
//	getClassNamesRecursive(Modelica)
//	getClassInformation(Modelica)
//	getClassRestriction(Modelica)
//	isConnector(Modelica)
//	getInheritanceCount(Modelica)
//	getNthInheritedClass(Modelica, 1)
//	getComponents(Modelica.Icons.Library)
//	getDocumentationAnnotation(Modelica.Blocks.Continuous.CriticalDamping)
	 
	
	public String getComponentModifierNames(String className, String componentName){
		return executeCommand("getComponentModifierNames(" + className + ", " + componentName+ ")");
	}
	
	public String getComponentModifierValue(String className, String componentName){
		return executeCommand("getComponentModifierValue(" + className + ", " + componentName+ ")");
	}
	
	public String getExtendsModifierNames(String className, String extendedClassName){
		return executeCommand("getExtendsModifierNames(" + className + ", "  + extendedClassName + ")");
	}
	
	public String getExtendsModifierValue(String className, String extendedClassName, String componentName){
		return executeCommand("getExtendsModifierValue(" + className + ", " + extendedClassName+ ", " + componentName+ ")");
	}
	
	public Integer getInheritanceCount(String className){
		String reply = executeCommand("getInheritanceCount(" + className + ")");
		if (reply != null) {
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
				if (!reply.equals("") && !reply.equals("Error") && !reply.equals("false")) {
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
		return executeCommand("getNthComponentCondition(" + componentName + ", " + number+ ")");
	}
	
	public String isEnumeration(String className){
		return executeCommand("isEnumeration(" + className + ")");
	}
	
	public String getEnumerationLiterals(String className){
		return executeCommand("getEnumerationLiterals(" + className + ")");
	}
	
	public String isReplaceable(String className, String nestedClassName){
		return executeCommand("isReplaceable(" + className +", " + nestedClassName + ")");
	}
	
//	public String getAnnotationCount(String className){
//		return executeCommand("getAnnotationCount(" + className + ")");
//	}
	
	public Integer getAnnotationCount(String className){
		String reply = executeCommand("getAnnotationCount(" + className + ")");
		if (reply != null ) {
			Integer count = Integer.valueOf(reply.trim());
			if ( count != null ) {
				return count;
			}
		}
		else {
			System.err.println("Could not complete the operation getAnnotationCount("+className+")");
		}
		return 0;
	}
	
	public List<String> getAnnotations(String className){
		int count = getAnnotationCount(className);
		List<String> annotations = new ArrayList<String>();
		if (count > 0 ) {
			for (int i = 1; i <= count; i++) {
				String reply = getNthAnnotationString(className, String.valueOf(i)).trim();
				if (!reply.equals("") && !reply.equals("Error") && !reply.equals("false")) {
					String stringInBrackets = StringHandler.removeFirstLastDoubleQuotes(reply.trim().replaceFirst("annotation", "").trim());
					String annotationString = StringHandler.removeFirstLastBrackets(stringInBrackets.substring(0, stringInBrackets.length() - 1));
					annotations.add(annotationString);
				}
			}
		}
		return annotations;
	}
	
	public String getNthAnnotationString(String elementName, String number){
		return executeCommand("getNthAnnotationString(" + elementName + ", " + number+ ")");
	}
	
	
	
	
	public int getImportCount(String className){
		String reply = executeCommand("getImportCount(" + className + ")");
		if (reply != null && !reply.trim().equals("")) {
			Integer count = Integer.valueOf(reply.trim());
			if ( count != null ) {
				return count;
			}
		}
		else {
			System.err.println("Could not complete the operation getImportCount("+className+")");
		}
		return 0;
	}
	
	public String getNthImport(String className, int number){
		return executeCommand("getNthImport(" + className + ", " + number+ ")");
	}
	
	
	
	
//	public String getInitialAlgorithmCount(String className){
//		return executeCommand("getInitialAlgorithmCount(" + className + ")");
//	}
	public Integer getInitialAlgorithmCount(String className){
		String reply = executeCommand("getInitialAlgorithmCount(" + className + ")");
		if (reply != null && !reply.trim().equals("")) {
			Integer count = Integer.valueOf(reply.trim());
			if ( count != null ) {
				return count;
			}
		}
		else {
			System.err.println("Could not complete the operation getInitialAlgorithmCount("+className+")");
		}
		return 0;
	}

	public String getNthInitialAlgorithm(String className, String number){
		return executeCommand("getNthInitialAlgorithm(" + className + ", " + number+ ")");
	}
	
	public List<String> getInitialAlgorithms(String className){
		int count = getInitialAlgorithmCount(className);
		List<String> initialAlgorithms = new ArrayList<String>();
		if (count > 0 ) {
			for (int i = 1; i <= count; i++) {
				String reply = getNthInitialAlgorithm(className, String.valueOf(i)).trim();
				if (!reply.equals("") && !reply.equals("Error") && !reply.equals("false")) {
//					initialAlgorithms.add(StringHandler.removeFirstLastDoubleQuotes(reply.trim()));
					String string = StringHandler.removeFirstLastDoubleQuotes(reply.trim());
					initialAlgorithms.add(replaceSpecChars(string));
				}
			}
		}
		return initialAlgorithms;
	}
	
		
//	public String getAlgorithmCount(String className){
//		return executeCommand("getAlgorithmCount(" + className + ")");
//	}
	
	public Integer getAlgorithmCount(String className){
		String reply = executeCommand("getAlgorithmCount(" + className + ")");
		if (reply != null && !reply.trim().equals("")) {
			Integer count = Integer.valueOf(reply.trim());
			if ( count != null ) {
				return count;
			}
		}
		else {
			System.err.println("Could not complete the operation getAlgorithmCount("+className+")");
		}
		return 0;
	}

	public String getNthAlgorithm(String className, String number){
		return executeCommand("getNthAlgorithm(" + className + ", " + number+ ")");
	}
	
	public List<String> getAlgorithms(String className){
		int count = getAlgorithmCount(className);
		List<String> algorithms = new ArrayList<String>();
		if (count > 0 ) {
			for (int i = 1; i <= count; i++) {
				String reply = getNthAlgorithm(className, String.valueOf(i)).trim();
				if (!reply.equals("") && !reply.equals("Error") && !reply.equals("false")) {
//					algorithms.add(StringHandler.removeFirstLastDoubleQuotes(reply.trim()));
					String string = StringHandler.removeFirstLastDoubleQuotes(reply.trim());
					algorithms.add(replaceSpecChars(string));
				}
			}
		}
		return algorithms;
	}
	
	

	
//	public String getInitialEquationCount(String className){
//		return executeCommand("getInitialEquationCount(" + className + ")");
//	}
	
	
	public Integer getInitialEquationCount(String className){
		String reply = executeCommand("getInitialEquationCount(" + className + ")");
		if (reply != null && !reply.trim().equals("")) {
			Integer count = Integer.valueOf(reply.trim());
			if ( count != null ) {
				return count;
			}
		}
		else {
			System.err.println("Could not complete the operation getInitialEquationCount("+className+")");
		}
		return 0;
	}

	public String getNthInitialEquation(String className, String number){
		return executeCommand("getNthInitialEquation(" + className + ", " + number+ ")");
	}
	
	public List<String> getInitialEquations(String className){
		int count = getInitialEquationCount(className);
		List<String> initialEquations = new ArrayList<String>();
		if (count > 0 ) {
			for (int i = 1; i <= count; i++) {
				String reply = getNthInitialEquation(className, String.valueOf(i)).trim();
				if (!reply.equals("") && !reply.equals("Error") && !reply.equals("false")) {
//					initialEquations.add(StringHandler.removeFirstLastDoubleQuotes(reply.trim()));
					String string = StringHandler.removeFirstLastDoubleQuotes(reply.trim());
					initialEquations.add(replaceSpecChars(string));

				}
			}
		}
		return initialEquations;
	}
	

		
//	public String getEquationCount(String className){
//		return executeCommand("getEquationCount(" + className + ")");
//	}
	
	public Integer getEquationCount(String className){
		String reply = executeCommand("getEquationCount(" + className + ")");
		if (reply != null && !reply.trim().equals("")) {
			Integer count = Integer.valueOf(reply.trim());
			if ( count != null ) {
				return count;
			}
		}
		else {
			System.err.println("Could not complete the operation getEquationCount("+className+")");
		}
		return 0;
	}

	public String getNthEquation(String className, String number){
		return executeCommand("getNthEquation(" + className + ", " + number+ ")");
	}
	
	public List<String> getEquations(String className){
		int count = getEquationCount(className);
		List<String> equations = new ArrayList<String>();
		if (count > 0 ) {
			for (int i = 1; i <= count; i++) {
				String reply = getNthEquation(className, String.valueOf(i)).trim();
				if (!reply.equals("") && !reply.equals("Error") && !reply.equals("false")) {
					String string = StringHandler.removeFirstLastDoubleQuotes(reply.trim());
					equations.add(replaceSpecChars(string));
				}
			}
		}
		return equations;
	}
	
	
	private String replaceSpecChars(String string){
		String newString = string.replaceAll("\\\\" + "\"", "\"");
		return newString;
	}
	
	
	
	/**
	 * Gets the command history.
	 *
	 * @return the command history
	 */
	public ArrayList<String> getCommandHistory() {
//		ArrayList<String> tempHistory = new ArrayList<String>();
//		Collections.copy(tempHistory, history);
//
//		return tempHistory;
		return this.history;
	}
}
