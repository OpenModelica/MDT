package org.openmodelica.modelicaml.helper.report;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.helpers.VerificationExecutionServices;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.helper.structures.GeneratedModelsData;

public class XMLReportGenerator implements IRunnableWithProgress {

	private GeneratedModelsData gmd;
	private HashMap<Element, ClassInstantiation> preparedModelInstantiations;
	
	// default line break is for HTML
	public String lineDelimiter = "<br />";
	
	public final static String XMLTagName_report 						= "report";
	public final static String XMLTagName_date 							= "date";
	
	public final static String XMLTagName_verificationModels 			= "verificationModels";
	
	public final static String XMLTagName_verificationModel 			= "verificationModel";
	public final static String XMLTagName_systemModel 					= "systemModel";
	public final static String XMLTagName_comments 						= "comments";
	public final static String XMLTagName_comment 						= "comment";
	public final static String XMLTagName_scenario 						= "scenario";
	
	public final static String XMLTagName_notUsedScenarios 				= "notUsedScenarios";
	public final static String XMLTagName_usedScenarios 				= "usedScenarios";
	
	public final static String XMLTagName_notImplementedRequirements	= "notImplementedRequirements";
	public final static String XMLTagName_violatedRequirements 			= "violatedRequirements";
	public final static String XMLTagName_notViolatedRequirements 		= "notViolatedRequirements";
	public final static String XMLTagName_notEvaluatedRequirements 			= "notEvaluatedRequirements";
	
	public final static String XMLTagName_requirements 					= "requirements";
	public final static String XMLTagName_requirement 					= "requirement";
	public final static String XMLTagName_id 							= "id";
	public final static String XMLTagName_requirementText 				= "text";
	public final static String XMLTagName_isEvaluated 					= "isEvaluated";
	public final static String XMLTagName_isViolated 					= "isViolated";
	
	public final static String XMLTagName_client 						= "client";
	
	public final static String XMLTagName_isMandatory 					= "isMandatory";
	public final static String XMLTagName_binding 						= "binding";
	
	public final static String XMLTagName_locateLink 					= "locateLink";
	public final static String XMLTagName_plotLink 						= "plotLink";
	
	public final static String XMLTagName_name 							= "name";
	public final static String XMLTagName_qualifiedName 				=  "qualifiedName";
	public final static String XMLTagName_instanceName 					= "instanceName";

	public final static String XMLTagName_newPositiveRelations 			= "newPositiveRelations";
	public final static String XMLTagName_newNegativeRelations 			= "newNegativeRelations";
	
	public final static String XMLTagName_simulated 					= "simulatedModels";
	public final static String XMLTagName_notSimulated 					= "notSimulatedModels";
	
	public final static String XMLTagName_count 						= "count";
	
	private String fileContent;
	
	public static int XMLContent = 0;
	
	
	
	public XMLReportGenerator(GeneratedModelsData gmd, int contentType) {
		
		this.gmd = gmd;
		
		if (gmd.getGenerator() != null) {
			preparedModelInstantiations = gmd.getGenerator().getPreparedModelInstantiations();
		}
		
		if (contentType == XMLContent) {
			IWorkbench wb = PlatformUI.getWorkbench();
			IProgressService ps = wb.getProgressService();
			try {
				ps.run(false, true, this);
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		fileContent = getXMLReportContent(monitor);
	}

	
	
	public String getXMLReportContent(IProgressMonitor monitor){
		
		monitor.setTaskName("Creating XML Report...");
		
		String string = "";
		string += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
		
		// TODO: XSL tag for translation into HTML 
		
		string += "<"+XMLTagName_report+">";
		
		string += "<"+XMLTagName_date+">";
		Calendar c1 = Calendar.getInstance(); // today
		Date date = c1.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		string += sdf.format(date);
		string += "</"+XMLTagName_date+">";

		// violated requirements
		monitor.setTaskName("Collecting Violated Requirements ...");
		string += getEvaluatedRequirementItems(true, false, false);
		
		// not violated requirements
		monitor.setTaskName("Collecting Not Violated Requirements ...");
		string += getEvaluatedRequirementItems(false, true, false);
		
		// not violated requirements
		monitor.setTaskName("Collecting Not Evaluated Requirements ...");
		string += getNotEvaluatedRequirementItems();

		// requirements for which no binding could be derived
		monitor.setTaskName("Collecting Not Implemented Requirements ...");
		string += getNotImplementedRequirementItems();

		// scenarios that were not used because they do not stimulate the model properly
		monitor.setTaskName("Collecting Used Scenarios ...");
		string += getUsedScenarioItems();
		
		// scenarios that were not used because they do not stimulate the model properly
		monitor.setTaskName("Collecting Not Used Scenarios ...");
		string += getNotUsedScenarioItems();

		// new positive and negative relations between scenarios and requirements
		monitor.setTaskName("Collecting New Relations ...");
		string += getNewRelationItems(true);
		string += getNewRelationItems(false);
		
		// all verification models
		monitor.setTaskName("Collecting Verification Models ...");
		string += getVeMItems();

		// not simulated models
		monitor.setTaskName("Collecting Not Simulated ...");
		string += getNotSimulatedModelItems();
		
		string += "</"+XMLTagName_report+">";
		
		return string;
	}
	
	
	
	private String getNotSimulatedModelItems(){
		String string = "";
		string += "<"+XMLTagName_notSimulated+">";
		
		int i = 0;
		for (Element model : gmd.getNotSimulatedModels()) {
			
			// counter
			i++;
			
			string += "<"+XMLTagName_verificationModel+" "+
						XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(model))+"\" "+
						XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(model))+"\">";
			
			string += 	getAllComments(model);
			
			string += "</"+XMLTagName_verificationModel+">";
		}
		
		string += "<"+XMLTagName_count+">" + i + " of " + gmd.getGeneratedModels().size() + "</"+XMLTagName_count+">";
		
		string += "</"+XMLTagName_notSimulated+">";
		
		return string;
	}
	
	
	private String getVeMItems(){
		String string = "";
		
		string += "<"+XMLTagName_verificationModels+">";
		
		int i = 0;
		
		for (Element model : gmd.getGeneratedModels()) {
			// counter
			i++; 
			
			// no filter for requirements
			string += getVeMItem(model, null, false, false, false);
			
		}
		
		string += "<"+XMLTagName_count+">" + i + "</"+XMLTagName_count+">";

		string += "</"+XMLTagName_verificationModels+">";
		
		return string;
	}
	
	private String getVeMItem(Element model, 
			Element filterForRequirementElement, 
			boolean onlyViolatedRequirements, 
			boolean onlyNotViolatedRequirements, 
			boolean onlyNotEvaluatedRequirements){
		
		String string = "";
		
		string += "<"+XMLTagName_verificationModel+" "+
					XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(model))+"\" "+
					XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(model))+"\">";

		ClassInstantiation ci = ModelicaMLServices.getModelInstantiation(model, preparedModelInstantiations);
		
		string += getSystemModelItems(ci);
		
		string += getScenarioItems(ci);
		
		string += getRequirementItems(ci, filterForRequirementElement, onlyViolatedRequirements, onlyNotViolatedRequirements, onlyNotEvaluatedRequirements);
		
		string += "</"+XMLTagName_verificationModel+">";
		
		return string;
	}
	
	
	private String getNewRelationItems(boolean isPositiveRelations){
		
		String string = "";
		int i = 0;
		
		if (isPositiveRelations) {
			string += "<"+XMLTagName_newPositiveRelations+">";
		}
		else {
			string += "<"+XMLTagName_newNegativeRelations+">";
		}

		HashMap<TreeObject, HashSet<TreeObject>> relationsMap;
		if (isPositiveRelations) {
			relationsMap = gmd.getNewPositiveRelations();
		}
		else {
			relationsMap = gmd.getNewNegativeRelations();
		}
		
		for (TreeObject scenario : relationsMap.keySet()) {
			
			Element scenarioType = scenario.getComponentType();
			
			string += "<"+XMLTagName_scenario+" "+
					XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(scenarioType))+"\" " +
					XMLTagName_instanceName+"=\""+scenario.getDotPath()+"\" "+
					XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(scenarioType))+"\"  "+
					XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(scenarioType))+"\">";
			
			HashSet<TreeObject> requirements = relationsMap.get(scenario);
			
			if (requirements != null) {
				for (TreeObject requirement : requirements) {
					
					// counter
					i++;
					
					Element requirementType = requirement.getComponentType();
					
					string += "<"+XMLTagName_requirement+" "+
							XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(requirementType))+"\" " +
							XMLTagName_instanceName+"=\""+requirement.getDotPath()+"\" "+
							XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(requirementType))+"\"  "+
							XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(requirementType))+"\">";
					string += "</"+XMLTagName_requirement+">";
				}
			}
			
			// add VeM for traceability
			string += getVeMforScenario(scenarioType);
			
			string += "</"+XMLTagName_scenario+">";
		}
		
		
		string += "<"+XMLTagName_count+">" + i + "</"+XMLTagName_count+">";
		
		if (isPositiveRelations) {
			string += "</"+XMLTagName_newPositiveRelations+">";
		}
		else {
			string += "</"+XMLTagName_newNegativeRelations+">";
		}		return string;
	}
	
	
	private String getSystemModelItems(ClassInstantiation ci){
		
		String string = "";
		
		Element VeM = ci.getSelectedClass();
		for (TreeObject treeObject : ci.getAllTreeObjects()) {
			
			Element type = treeObject.getComponentType();
			
			if (gmd.getSystemModels().contains(type)) {
				
				string += "<"+XMLTagName_systemModel+" "+
								XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(VeM, treeObject))+"\" " +
								XMLTagName_plotLink+"=\""+StringEscapeUtils.escapeXml(getPlotStatusLink(VeM, treeObject))+"\" " +
								XMLTagName_instanceName+"=\""+treeObject.getDotPath()+"\" "+
								XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(type))+"\"  "+
								XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(type))+"\">";
				
				// add comments
				string += 	getAllComments(type);

				// Add clients (isMandatory, binding, plot link)
				string += getClientItems(VeM, treeObject);

				string += "</"+XMLTagName_systemModel+">";
			}
		}
		
		return string;
	}
	
	private String getScenarioItems(ClassInstantiation ci){
		String string = "";
		
		Element VeM = ci.getSelectedClass();
		
		if (VeM != null) {
			HashSet<TreeObject> scenarios = gmd.getScenarios(VeM);
			for (TreeObject scenario : scenarios) {
				
				Element type = scenario.getComponentType();

				string += "<"+XMLTagName_scenario+" "+
						XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(VeM, scenario))+"\" " +
						XMLTagName_plotLink+"=\""+StringEscapeUtils.escapeXml(getPlotStatusLink(VeM, scenario))+"\" " +
						XMLTagName_instanceName+"=\""+scenario.getDotPath()+"\" "+
						XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(type))+"\" "+
						XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(type))+"\">";
				string += getAllComments(type);

				// Add clients (isMandatory, binding, plot link)
				string += getClientItems(VeM, scenario);
 
				string += "</"+XMLTagName_scenario+">";
			}
		}
		
		return string;
	}
	
	private String getRequirementItems(ClassInstantiation ci, 
				Element filterForRequirementElement, 
				boolean onlyViolatedRequirements, 
				boolean onlyNotViolatedRequirements,
				boolean onlyNotEvaluatedRequirements){
		
		String string = "";
		int i = 0;
		
		string += "<"+XMLTagName_requirements+">";
		
		Element VeM = ci.getSelectedClass();
		HashSet<TreeObject> requirements = gmd.getRequirements(VeM);
		
		for (TreeObject requirement : requirements) {
			
			Element type = requirement.getComponentType();
			String statusDotPath = requirement.getDotPath().trim() + "." + gmd.requirementStatusPropertyName;
			String key = gmd.getModelToTreeItemKeyString(VeM, statusDotPath);

			String isViolated = "false";
			if (gmd.getViolatedRequirements().contains(key)) {
				isViolated = "true";
			}
			
			String isEvaluated = "false";
			if (gmd.getEvaluatedRequirements().contains(key)) {
				isEvaluated = "true";
			}
			
			boolean skip = false;
			// if the requirements list should only include requirements with type "filterForRequirementElement"
			if (filterForRequirementElement != null && !type.equals(filterForRequirementElement)) {
				skip = true;
			}
			// if only violated requirements should be shown
			else if (onlyViolatedRequirements && !isViolated.equals("true")) {
				skip = true;
			}
			// if only not violated should be shown
			else if (onlyNotViolatedRequirements && !gmd.getNotViolatedRequirements().contains(key)) {
				skip = true;
			}
			// of only not evaluated should be shown
			else if (onlyNotEvaluatedRequirements && !gmd.getNotEvaluatedRequirements().contains(key)) {
				 skip = true;
			}
			
			if (!skip) {
				// counter 
				i ++;
				string += "<"+XMLTagName_requirement+" " +
						XMLTagName_isViolated + "=\""+isViolated+"\" " +
						XMLTagName_isEvaluated+"=\""+isEvaluated+"\" " +
						XMLTagName_instanceName+"=\""+requirement.getDotPath()+"\" " +
						XMLTagName_locateLink+"=\""+getLocateLink(VeM, requirement)+"\" " +
						XMLTagName_plotLink+"=\""+getPlotStatusLink(VeM, requirement)+"\" " +
						XMLTagName_name+"=\""+ModelicaMLServices.getName(type)+"\" " +
						XMLTagName_qualifiedName + "=\""+ModelicaMLServices.getQualifiedName(type) +"\" " +
						XMLTagName_id + "=\""+ModelicaMLServices.getRequirementID(type) +"\"" +
								">";
				
				// add requirement text
				string += "<"+XMLTagName_requirementText+">" + StringEscapeUtils.escapeHtml(ModelicaMLServices.getRequirementText(type)) + "</"+XMLTagName_requirementText+">"; 

				// Add clients (isMandatory, binding, plot link)
				string += getClientItems(VeM, requirement);

				string += "</"+XMLTagName_requirement+">";
			}
		}
		
		string += "<"+XMLTagName_count+">" + i + " of " + gmd.getAllFoundRequirements().size() + "</"+XMLTagName_count+">";
		
		string += "</"+XMLTagName_requirements+">";
		
		return string;
	}
	
	private String getClientItems(Element VeM, TreeObject treeObject){
		String string = "";
		for (TreeObject client : VerificationExecutionServices.getClientsTreeItems(treeObject, new HashSet<TreeObject>())) {
			String isMandatory = "false";
			if (client.isValueClient_required()) {
				isMandatory = "true";
			}
			string += "<"+XMLTagName_client+" " +  
						XMLTagName_instanceName+"=\"" + client.getDotPath() + "\" " + 
						XMLTagName_isMandatory+"=\"" + isMandatory + "\"" +
						XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(VeM, client))+"\" " +
						XMLTagName_plotLink +"=\"" + StringEscapeUtils.escapeXml(getPlotLink(VeM, client)) + "\"" +
								">";
			
			string += "<"+XMLTagName_binding+">" + StringEscapeUtils.escapeHtml(client.getFinalModificationRightHand()) + "</"+XMLTagName_binding+">";;
			string += "</"+XMLTagName_client+">";
		}
		return string;
	}
	
	

	
	
	private String getVeMforScenario(Element usedScenario){
		String string = "";
		// loop over all VeM and see if this requirement is in the 
		for (Element VeM : gmd.getGeneratedModels()) {
			HashSet<TreeObject> scenarios = gmd.getScenarios(VeM);
			boolean modelContains  = false;
			if (scenarios != null) {
				for (TreeObject scenarioTreeObject : scenarios) {
					Element type = scenarioTreeObject.getComponentType();
					if (usedScenario.equals(type)) {
						modelContains = true; 
					}
				}
			}
			
			if (modelContains) {
				string += getVeMItem(VeM, null, false, false, false);
			}
		}
		return string;
	}
	
	
	private String getUsedScenarioItems(){
		String string = "";
		int i = 0;
		
		HashSet<Element> usedScenarios = gmd.getAllScenarios();
		
		string += "<"+XMLTagName_usedScenarios+">";
		
		for (Element usedScenario : usedScenarios) {
			
			// counter
			i++ ;
			
			string += "<"+XMLTagName_scenario+" "+
					XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(usedScenario))+"\" "+
					XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(usedScenario))+"\">";
			string += getAllComments(usedScenario);

			// loop over all VeM and see if this requirement is in the 
			string += getVeMforScenario(usedScenario);

			string += "</"+XMLTagName_scenario+">";
		}
		
		string += "<"+XMLTagName_count+">" + i + " of " + gmd.getAllFoundScenarios().size() + "</"+XMLTagName_count+">";
		
		string += "</"+XMLTagName_usedScenarios+">";
		
		return string;
	}
	
	
	private String getVeMForRequirement(Element evaluatedRequirement, boolean onlyViolatedRequirements, boolean onlyNotViolatedRequirements, boolean onlyNotEvaluatedRequirements){
		String string = "";
		for (Element VeM : gmd.getGeneratedModels()) {
			HashSet<TreeObject> requirements = gmd.getRequirements(VeM);
			boolean modelContains  = false;
			if (requirements != null) {
				for (TreeObject requirementTreeObject : requirements) {
					Element type = requirementTreeObject.getComponentType();
					if (evaluatedRequirement.equals(type)) {
						modelContains = true; 
					}
				}
			}
			
			if (modelContains) {
				string += getVeMItem(VeM, evaluatedRequirement, onlyViolatedRequirements, onlyNotViolatedRequirements, onlyNotEvaluatedRequirements);
			}
		}
		
		return string;
	}
	
	
	private String getEvaluatedRequirementItems( boolean onlyViolatedRequirements, boolean onlyNotViolatedRequirements, boolean onlyNotEvaluatedRequirements){
		String string = "";
		int i = 0;
		
		HashSet<Element> evaluatedRequirements = new HashSet<Element>();
		if (onlyViolatedRequirements) {
			evaluatedRequirements = gmd.getRequirementsViolatedInScenarios();
			string += "<"+XMLTagName_violatedRequirements+">";

		}
		else if (onlyNotViolatedRequirements) {
			evaluatedRequirements = gmd.getRequirementsNotViolatedInScenarios();
			string += "<"+XMLTagName_notViolatedRequirements+">";
		}

		for (Element evaluatedRequirement : evaluatedRequirements) {
			i++ ;
			string += "<"+XMLTagName_requirement+" "+
					XMLTagName_id + "=\""+ModelicaMLServices.getRequirementID(evaluatedRequirement) +"\"" +
					XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(evaluatedRequirement))+"\" "+
					XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(evaluatedRequirement))+"\">";
					string += getAllComments(evaluatedRequirement);
		
			// add requirement text
			string += "<"+XMLTagName_requirementText+">" + StringEscapeUtils.escapeHtml(ModelicaMLServices.getRequirementText(evaluatedRequirement)) + "</"+XMLTagName_requirementText+">"; 

			// loop over all VeM and see if this requirement is in the 
			string += getVeMForRequirement(evaluatedRequirement, onlyViolatedRequirements, onlyNotViolatedRequirements, onlyNotEvaluatedRequirements);
			
			string += "</"+XMLTagName_requirement+">";
		}
		
		string += "<"+XMLTagName_count+">" + i + " of " + gmd.getAllFoundRequirements().size() + "</"+XMLTagName_count+">";
		
		if (onlyViolatedRequirements) {
			evaluatedRequirements = gmd.getRequirementsViolatedInScenarios();
			string += "</"+XMLTagName_violatedRequirements+">";
		}
		else if (onlyNotViolatedRequirements) {
			evaluatedRequirements = gmd.getRequirementsNotViolatedInScenarios();
			string += "</"+XMLTagName_notViolatedRequirements+">";
		}
		
		return string;
	}
	
	
	
	private String getNotEvaluatedRequirementItems(){
		String string = "";
		int i = 0;
		boolean showOnlyNotEvaluated = true;
		boolean showOnlyViolated = false;
		boolean showOnlyNotViolated = false;
		
		HashSet<Element> evaluatedRequirements = gmd.getNotEvaluatedRequirements();
		string += "<"+XMLTagName_notEvaluatedRequirements+">";


		for (Element evaluatedRequirement : evaluatedRequirements) {
			i++ ;
			string += "<"+XMLTagName_requirement+" "+
					XMLTagName_id + "=\""+ModelicaMLServices.getRequirementID(evaluatedRequirement) +"\"" +
					XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(evaluatedRequirement))+"\" "+
					XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(evaluatedRequirement))+"\">";
					string += getAllComments(evaluatedRequirement);
		
			// add requirement text
			string += "<"+XMLTagName_requirementText+">" + StringEscapeUtils.escapeHtml(ModelicaMLServices.getRequirementText(evaluatedRequirement)) + "</"+XMLTagName_requirementText+">"; 

			// loop over all VeM and see if this requirement is in the 
			string += getVeMForRequirement(evaluatedRequirement, showOnlyViolated, showOnlyNotViolated, showOnlyNotEvaluated);
			
			string += "</"+XMLTagName_requirement+">";
		}
		
		string += "<"+XMLTagName_count+">" + i + " of " + gmd.getAllFoundRequirements().size() + "</"+XMLTagName_count+">";
		
		string += "</"+XMLTagName_notEvaluatedRequirements+">";
		return string;
	}
	
	
	private String getNotImplementedRequirementItems(){
		String string = "";
		int i = 0;
		
		HashSet<Element> allFoundRequirements = gmd.getAllFoundRequirements();
		HashSet<Element> allUsedRequirements= gmd.getAllUsedRequirements();
		
		allFoundRequirements.removeAll(allUsedRequirements);
		
		HashSet<Element> allNotImplementedRequirements = allFoundRequirements;
		if (allNotImplementedRequirements != null && allNotImplementedRequirements.size() > 0) {
			string += "<"+XMLTagName_notImplementedRequirements+">";
			
			for (Element requirement : allNotImplementedRequirements) {
				
				// counter 
				i++ ;
				
				string += "<"+XMLTagName_requirement+" "+
							XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(requirement))+"\" "+
							XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(requirement))+"\">";
				string += getAllComments(requirement);
				string += "</"+XMLTagName_requirement+">";
			}
			
			string += "<"+XMLTagName_count+">" + i + " of " + gmd.getAllFoundRequirements().size() + "</"+XMLTagName_count+">";
			
			string += "</"+XMLTagName_notImplementedRequirements+">";
		}
		return string;
	}
	

	private String getNotUsedScenarioItems(){
		String string = "";
		int i = 0;
		
		HashSet<Element> allFoundScenarios = gmd.getAllFoundScenarios();
		HashSet<Element> allUsedScenarios = gmd.getAllScenarios();
		
		allFoundScenarios.removeAll(allUsedScenarios);
		
		HashSet<Element> allNotUsedScenarios = allFoundScenarios;
		if (allNotUsedScenarios != null && allNotUsedScenarios.size() > 0) {
			string += "<"+XMLTagName_notUsedScenarios+">";
			
			for (Element scenario : allNotUsedScenarios) {
				// counter
				i++;
				
				string += "<"+XMLTagName_scenario+" "+
							XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(scenario))+"\" "+
							XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(scenario))+"\">";
				string += getAllComments(scenario);
				string += "</"+XMLTagName_scenario+">";
			}
			
			string += "<"+XMLTagName_count+">" + i + " of " + gmd.getAllFoundScenarios().size() + "</"+XMLTagName_count+">";
			string += "</"+XMLTagName_notUsedScenarios+">";
		}
		return string;
	}

	
	
	// Links
	private String getLocateLink(Element model){
		String linkAdress = "";
		linkAdress = "locate:" + ModelicaMLServices.getQualifiedName(model);
		return linkAdress;
	}
	
	private String getLocateLink(Element model, TreeObject treeObject){
		String linkAdress = "";
		linkAdress = "locate:" + ModelicaMLServices.getQualifiedName(model) + Constants.linkDelimiter + treeObject.getDotPath();
		return linkAdress;
	}
	
	private String getPlotLink(Element model, TreeObject treeObject){
		String linkAdress = "";
		linkAdress = "plot:" + ModelicaMLServices.getQualifiedName(model) + Constants.linkDelimiter + treeObject.getDotPath();
		return linkAdress;
	}
	
	private String getPlotStatusLink(Element model, TreeObject treeObject){
		String linkAdress = "";
		linkAdress = "plot:" + ModelicaMLServices.getQualifiedName(model) + Constants.linkDelimiter + treeObject.getDotPath();
		return linkAdress + "." + Constants.propertyName_mStatus;
	}
	
	
	
	/*
	 * Additional info 
	 */
	private String getAllComments(Element element) {
		String string = "";
		int i = 0;
		
		string += "<"+XMLTagName_comments+">";
		if (element instanceof Element) {
			for (Comment comment : element.getOwnedComments()) {
				//counter
				i++;
				string += "<"+XMLTagName_comment+">" + StringEscapeUtils.escapeHtml(comment.getBody()) + "</"+XMLTagName_comment+">";
			}
		}
		string += "<"+XMLTagName_count+">" + i + "</"+XMLTagName_count+">";
		string += "</"+XMLTagName_comments+">";
		return string;
	}
	
	
	
	
	/*
	 * File handling
	 */
	public String createFile(String projectName, String folderName, String fileName, boolean askWhereToStore){
		
		String filePath = null;
		
		if (askWhereToStore) {
			// TODO: open file dialog so that the user can select where to store the report
		}
		else {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();
			IProject iProject = root.getProject(projectName);
			
			String projectPath = iProject.getLocationURI().toString().replaceFirst("file:\\/", "");
			String folderPath = projectPath + "/" + folderName;
			
			File folder = new File(folderPath);
			
			boolean folderCreated = false;
			if (!folder.exists()) {
				 folderCreated = new File(folderPath).mkdir();
			}
			
			// TODO: replace empty spaces in the folderPath
			
			if (folder.exists() || folderCreated) {
				filePath = folderPath + "/" + fileName;
				boolean ok = storeFile(filePath);
				if (ok) {
					return filePath;
				}
				else {
					return null;
				}
			}
		}
		
		
		return filePath;
	}

	
	private boolean storeFile(String filePath){
		if (fileContent != null) {
			try {

				/*
				 * http://docs.oracle.com/javase/1.4.2/docs/api/java/io/BufferedWriter.html
				 * "In general, a Writer sends its output immediately to the underlying character or byte stream. 
				 *  Unless prompt output is required, it is advisable 
				 *  to wrap a BufferedWriter around any Writer whose write() operations may be costly, 
				 *  such as FileWriters and OutputStreamWriters."
				 *  
				 *  Use: new BufferedWriter(new FileWriter("foo.out"));
				 */
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8"));
				out.write(fileContent);
				out.close();
				
				// refresh the projects browser
				ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
				
				return true;
//					FileOutputStream fos = new FileOutputStream(filePath); 
//					OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");
//					out.write(Constants.fileEncoding);
//					out.close();
				
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}



	public String getLineBreake() {
		return lineDelimiter;
	}



	public void setLineBreake(String lineBreake) {
		this.lineDelimiter = lineBreake;
	}
	
	
	public String getFileContent() {
		return fileContent;
	}



	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}





}
