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
package org.openmodelica.modelicaml.helper.report;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.internal.filesystem.local.LocalFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.helpers.VerificationServices;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.helper.structures.GeneratedModelsData;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

@SuppressWarnings("restriction")
public class XMLReportGenerator {

	private GeneratedModelsData gmd;
	private HashMap<Element, ClassInstantiation> preparedModelInstantiations;
	
	// default line break is for HTML
	public String lineDelimiter = "<br />";
	
	public final static String XMLTagName_report 						= "report";
	public final static String XMLTagName_date 							= "date";
	
	// view tag name
	public final static String XMLTagName_verificationModels 			= "verificationModels";
	
	public final static String XMLTagName_notImplementedRequirements	= "notImplementedRequirements";
	public final static String XMLTagName_violatedRequirements 			= "violatedRequirements";
	public final static String XMLTagName_notViolatedRequirements 		= "notViolatedRequirements";
	public final static String XMLTagName_notEvaluatedRequirements 		= "notEvaluatedRequirements";
	
	public final static String XMLTagName_notUsedScenarios 				= "notUsedScenarios";
	public final static String XMLTagName_usedScenarios 				= "usedScenarios";

	public final static String XMLTagName_newPositiveRelations 			= "newPositiveRelations";
	public final static String XMLTagName_newNegativeRelations 			= "newNegativeRelations";
	
	public final static String XMLTagName_simulated 					= "simulatedModels";
	public final static String XMLTagName_notSimulated 					= "notSimulatedModels";

	// view name 
	public final static String ViewName_verificationModels 				= "All Verification Models";
	
	public final static String ViewName_notImplementedRequirements		= "Not Implemented Requirements";
	public final static String ViewName_violatedRequirements 			= "Violated Requirements";
	public final static String ViewName_notViolatedRequirements 		= "Not Violated Requirements";
	public final static String ViewName_notEvaluatedRequirements 		= "Not Evaluated Requirements";
	
	public final static String ViewName_notUsedScenarios 				= "Not Used Scenarios";
	public final static String ViewName_usedScenarios 					= "Used Scenarios";

	public final static String ViewName_newPositiveRelations 			= "New Positive Relations";
	public final static String ViewName_newNegativeRelations 			= "New Negative Relations";
	
	public final static String ViewName_simulated 						= "Simulated Models";
	public final static String ViewName_notSimulated 					= "Not Simulated Models";

	
	// view name 
	public final static String ViewDescription_verificationModels 				= "All verification models.";
	
	public final static String ViewDescription_notImplementedRequirements		= "Not Implemented Requirements.";
	public final static String ViewDescription_violatedRequirements 			= "Requirements that were evaluated and violated.";
	public final static String ViewDescription_notViolatedRequirements 			= "Requirements that were evaluated and not violated.";
	public final static String ViewDescription_notEvaluatedRequirements 		= "Requirements that were not evaluated in any of the verification models.";
	
	public final static String ViewDescription_notUsedScenarios 				= "Scenarios that were not used in this verification session because they do not have appropriate providers to stimulate the system model.";
	public final static String ViewDescription_usedScenarios 					= "Scenarios that were used in this verification session because they have appropriate providers to stimulate the system model.";

	public final static String ViewDescription_newPositiveRelations 			= "New discovered positive relations between scenarios and requirements ("+Constants.stereotypeQName_UseToVerify+" relation).";
	public final static String ViewDescription_newNegativeRelations 			= "New discovered negative relations between scenarios and requirements ("+Constants.stereotypeQName_DoNotUseToVerify+" relation).";
	
	public final static String ViewDescription_simulated 						= "All models that were simulated and for which simulation result were found.";
	public final static String ViewDescription_notSimulated 					= "All models for which no simulation result file could be found.";
	
	
	// items
	public final static String XMLTagName_verificationModel 			= "verificationModel";
	public final static String XMLTagName_systemModel 					= "systemModel";
	public final static String XMLTagName_comments 						= "comments";
	public final static String XMLTagName_comment 						= "comment";
	public final static String XMLTagName_scenario 						= "scenario";
	
	public final static String XMLTagName_requirements 					= "requirements";
	public final static String XMLTagName_requirement 					= "requirement";
	public final static String XMLTagName_id 							= "id";
	public final static String XMLTagName_requirementText 				= "text";
	public final static String XMLTagName_isEvaluated 					= "isEvaluated";
	public final static String XMLTagName_isViolated 					= "isViolated";
	
	public final static String XMLTagName_startTime 					= "startTime";
	public final static String XMLTagName_stopTime 						= "stopTime";
	public final static String XMLTagName_tolerance  					= "tolerance";
	public final static String XMLTagName_interval						= "intervals";
	public final static String XMLTagName_outputFormat					= "outputFormat";
	
	public final static String XMLTagName_type							= "type";
	public final static String XMLTagName_variablity					= "variablity";
	
	
	public final static String XMLTagName_client 						= "client";
	
	public final static String XMLTagName_isMandatory 					= "isMandatory";
	public final static String XMLTagName_binding 						= "binding";
	
	public final static String XMLTagName_locateLink 					= "locateLink";
	public final static String XMLTagName_plotLink 						= "plotLink";
	
	public final static String XMLTagName_name 							= "name";
	public final static String XMLTagName_qualifiedName 				=  "qualifiedName";
	public final static String XMLTagName_instanceName 					= "instanceName";
	public final static String XMLTagName_clientPath 					= "clientPath";

	public final static String XMLTagName_count 						= "count";

	public final static String XMLTagName_description 					= "description";
	
	public final static String XMLTagName_uid 							= "uid";

	// local unique id for any user interface object
	private int uid = 0;
	
	// generated file content
	private String fileContent;
	
	// type of content
	public static int XMLContent = 0;
	
	private int contentType;
	
	private IProgressMonitor monitor;
	private ProgressMonitorDialog progressDialog;
	
	private boolean copySimulationFiles = false;
	
	private EObject rootElement;
	
	public XMLReportGenerator(GeneratedModelsData gmd, EObject rootElement, int contentType) {
		
		this.gmd = gmd;
		this.rootElement = rootElement;
		
		if (gmd.getGenerator() != null) {
			preparedModelInstantiations = gmd.getGenerator().getPreparedModelInstantiations();
		}
		this.contentType = contentType;
		
	}

	
	public String getXMLReportContent(){
		
		monitor.setTaskName("Creating XML Report...");
		
		String string = "";
		string += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
		
		// XSL tag for translation into HTML 
//		string += "<?xml-stylesheet type=\"text/xsl\" href=\"ReportTranslator.xslt\"?>";
		
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
		
		// all simulated models 
		monitor.setTaskName("Collecting Simulated Models ...");
		string += getSimulatedModelItems();
		
		// not simulated models
		monitor.setTaskName("Collecting Not Simulated Models ...");
		string += getNotSimulatedModelItems();
		
		string += "</"+XMLTagName_report+">";

		return string;
	}
	
	
	
	private String getNotSimulatedModelItems(){
		String string = "";
		
		string += "<"+XMLTagName_notSimulated+" name=\""+ ViewName_notSimulated+"\">";
		
		string += "<"+XMLTagName_description+">" +  StringEscapeUtils.escapeXml(ViewDescription_notSimulated) +  "</"+XMLTagName_description+">";
		
		int i = 0;
		for (Element model :  ModelicaMLServices.getSortedByName(gmd.getNotSimulatedModels())) {
			
			// counter
			i++;
			
			string += "<"+XMLTagName_verificationModel+" "+
						XMLTagName_uid +"=\""+getUid()+"\" "+
						XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(model))+"\" "+
						XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(model))+"\">";
			
			string += 	getAllComments(model);
			
			string += "</"+XMLTagName_verificationModel+">";
		}
		
		string += "<"+XMLTagName_count+">" + i + " of " + gmd.getGeneratedModels().size() + "</"+XMLTagName_count+">";
		
		string += "</"+XMLTagName_notSimulated+">";
		
		return string;
	}
	
	private String getSimulatedModelItems(){

		HashSet<Element> notSimulatedModels = new HashSet<Element>();
		notSimulatedModels.addAll(gmd.getNotSimulatedModels());
		HashSet<Element> allModels = new HashSet<Element>();
		allModels.addAll(gmd.getGeneratedModels());
		
		allModels.removeAll(notSimulatedModels);
		
		HashSet<Element> simulatedModels = allModels;
		
		
		String string = "";
		
		string += "<"+XMLTagName_simulated+" name=\""+ ViewName_simulated+"\">";
		
		string += "<"+XMLTagName_description+">" +  StringEscapeUtils.escapeXml(ViewDescription_simulated) +  "</"+XMLTagName_description+">";
		
		int i = 0;
		for (Element model : simulatedModels) {
			
			// counter
			i++;
			
			string += "<"+XMLTagName_verificationModel+" "+
						XMLTagName_uid +"=\""+getUid()+"\" "+
						XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(model))+"\" "+
						XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(model))+"\">";
			
			string += 	getAllComments(model);
			
			string += "</"+XMLTagName_verificationModel+">";
		}
		
		string += "<"+XMLTagName_count+">" + i + " of " + gmd.getGeneratedModels().size() + "</"+XMLTagName_count+">";
		
		string += "</"+XMLTagName_simulated+">";
		
		return string;
	}
	
	
	private String getVeMItems(){
		String string = "";
		
		string += "<"+XMLTagName_verificationModels +
				" " + XMLTagName_name + "=\""+ViewName_verificationModels+"\">";
		
		string += "<"+XMLTagName_description+">" +  StringEscapeUtils.escapeXml(ViewDescription_verificationModels) +  "</"+XMLTagName_description+">";
		
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
					XMLTagName_uid +"=\""+getUid()+"\" "+
					
					XMLTagName_startTime +"=\""+VerificationServices.getStartTime(model)+"\" "+
					XMLTagName_stopTime +"=\""+VerificationServices.getStopTime(model)+"\" "+
					XMLTagName_tolerance +"=\""+VerificationServices.getTolerance(model)+"\" "+
					XMLTagName_interval +"=\""+VerificationServices.getInterval(model)+"\" "+
					XMLTagName_outputFormat +"=\""+VerificationServices.getOutputFormat(model)+"\" "+
				
					XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(model))+"\" " +
					XMLTagName_plotLink+"=\""+StringEscapeUtils.escapeXml(getPlotLink(model))+"\" " +
					
					XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(model))+"\" "+
					XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(model))+"\">";

		ClassInstantiation ci = ModelicaMLServices.getModelInstantiation(model, preparedModelInstantiations, gmd.getAllFoundMediators(), false);
		
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
			string += "<"+XMLTagName_newPositiveRelations + 
					" "+ XMLTagName_name + "=\""+ ViewName_newPositiveRelations +"\" >";
			
			string += "<"+XMLTagName_description+">" +  StringEscapeUtils.escapeXml(ViewDescription_newPositiveRelations) +  "</"+XMLTagName_description+">";
		}
		else {
			string += "<"+XMLTagName_newNegativeRelations+" name=\""+ ViewName_newNegativeRelations + "\">";
			
			string += "<"+XMLTagName_description+">" +  StringEscapeUtils.escapeXml(ViewDescription_newNegativeRelations) +  "</"+XMLTagName_description+">";
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
					XMLTagName_uid +"=\""+getUid()+"\" "+	
					XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(scenarioType))+"\" " +
					XMLTagName_instanceName+"=\""+scenario.getDotPath()+"\" "+
					XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(scenarioType))+"\"  "+
					XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(scenarioType))+"\">";
			
			HashSet<TreeObject> requirements = relationsMap.get(scenario);
			
			if (requirements != null) {
				for (TreeObject requirement : ModelicaMLServices.getSortedByDotPath(requirements)) {
					
					// counter
					i++;
					
					Element requirementType = requirement.getComponentType();
					
					string += "<"+XMLTagName_requirement+" "+
							XMLTagName_uid +"=\""+getUid()+"\" "+	
							XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(requirementType))+"\" " +
							XMLTagName_instanceName+"=\""+requirement.getDotPath()+"\" "+
							XMLTagName_id + "=\""+ModelicaMLServices.getRequirementID(requirementType) +"\" " +
							
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
								XMLTagName_uid +"=\""+getUid()+"\" "+			
//								XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(treeObject.getUmlElement()))+"\" " +
								XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(VeM, treeObject))+"\" " +
								XMLTagName_plotLink+"=\""+StringEscapeUtils.escapeXml(getPlotLink(VeM, treeObject))+"\" " +
								XMLTagName_instanceName+"=\""+treeObject.getDotPath()+"\" "+
								XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(type))+"\"  "+
								XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(type))+"\">";
				
				// add comments
				string += 	getAllComments(type);

				// Add clients (isMandatory, binding, plot link)
				string += getClientItems(ci, VeM, treeObject);

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
			for (TreeObject scenario :  ModelicaMLServices.getSortedByDotPath(scenarios)) {
				
				Element type = scenario.getComponentType();

				string += "<"+XMLTagName_scenario+" "+
						XMLTagName_uid +"=\""+getUid()+"\" "+	
//						XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(scenario.getUmlElement()))+"\" " +
						XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(VeM, scenario))+"\" " +
						XMLTagName_plotLink+"=\""+StringEscapeUtils.escapeXml(getPlotLink(VeM, scenario))+"\" " +
						XMLTagName_instanceName+"=\""+scenario.getDotPath()+"\" "+
						XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(type))+"\" "+
						XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(type))+"\">";
				string += getAllComments(type);

				// Add clients (isMandatory, binding, plot link)
				string += getClientItems(ci, VeM, scenario);
 
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
						XMLTagName_uid +"=\""+getUid()+"\" "+	
						XMLTagName_isViolated + "=\""+isViolated+"\" " +
						XMLTagName_isEvaluated+"=\""+isEvaluated+"\" " +
						XMLTagName_instanceName+"=\""+requirement.getDotPath()+"\" " +
//						XMLTagName_locateLink+"=\""+getLocateLink(requirement.getUmlElement())+"\" " +
						XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(VeM, requirement))+"\" " +
						XMLTagName_plotLink+"=\""+getPlotRequirementStatusLink(VeM, requirement)+"\" " +
						XMLTagName_name+"=\""+ModelicaMLServices.getName(type)+"\" " +
						XMLTagName_qualifiedName + "=\""+ModelicaMLServices.getQualifiedName(type) +"\" " +
						XMLTagName_id + "=\""+ModelicaMLServices.getRequirementID(type) +"\" " +
								">";
				
				// add requirement text
//				string += "<"+XMLTagName_requirementText+">" + StringEscapeUtils.escapeHtml(ModelicaMLServices.getRequirementText(type)) + "</"+XMLTagName_requirementText+">"; 
				string += "<"+XMLTagName_requirementText+">" + StringEscapeUtils.escapeXml(ModelicaMLServices.getRequirementText(type)) + "</"+XMLTagName_requirementText+">";

				// Add clients (isMandatory, binding, plot link)
				string += getClientItems(ci, VeM, requirement);

				string += "</"+XMLTagName_requirement+">";
			}
		}
		
		string += "<"+XMLTagName_count+">" + i + " of " + gmd.getAllFoundRequirements().size() + "</"+XMLTagName_count+">";
		
		string += "</"+XMLTagName_requirements+">";
		
		return string;
	}
	
	private String getClientItems(ClassInstantiation ci, Element VeM, TreeObject treeObject){
		String string = "";
		
		for (TreeObject client : VerificationServices.getClientsTreeItems(ci, treeObject, new HashSet<TreeObject>(), false)) {
			
			String isMandatory = "false";
			
			if (client.isValueClient_required()) {
				isMandatory = "true";
			}
			
			string += "<"+XMLTagName_client+" " +  
						XMLTagName_uid +"=\""+getUid()+"\" "+		
						
						XMLTagName_type +"=\""+StringEscapeUtils.escapeXml(VerificationServices.getTreeItemTypeName(client))+"\" "+
						XMLTagName_variablity +"=\""+VerificationServices.getVariability(client)+"\" "+
						
						XMLTagName_instanceName+"=\"" + client.getDotPath() + "\" " + 
						XMLTagName_clientPath+"=\"" + client.getDotPathWithoutFirstLevelComponent() + "\" " + 
						XMLTagName_isMandatory+"=\"" + isMandatory + "\" " +
//						XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(client.getUmlElement()))+"\" " +
						XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(VeM, client))+"\" " +
						XMLTagName_plotLink +"=\"" + StringEscapeUtils.escapeXml(getPlotLink(VeM, client)) + "\" " +
								">";
			String binding = client.getFinalModificationRightHand();
			
			if (binding != null) {
//				string += "<"+XMLTagName_binding+">" + StringEscapeUtils.escapeHtml(binding) + "</"+XMLTagName_binding+">";
				string += "<"+XMLTagName_binding+">" + StringEscapeUtils.escapeXml(binding) + "</"+XMLTagName_binding+">";
			}
			
			string += "</"+XMLTagName_client+">";
		}
		return string;
	}
	
	

	
	
	private String getVeMforScenario(Element usedScenario){
		String string = "";
		
		// loop over all VeM and see if this requirement is in the 
		for (Element VeM :  ModelicaMLServices.getSortedByName(gmd.getGeneratedModels())) {
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
		
		string += "<"+XMLTagName_usedScenarios+" name=\""+ViewName_usedScenarios+"\">";
		
		string += "<"+XMLTagName_description+">" +  StringEscapeUtils.escapeXml(ViewDescription_usedScenarios) +  "</"+XMLTagName_description+">";
		
		for (Element usedScenario :  ModelicaMLServices.getSortedByName(usedScenarios)) {
			
			// counter
			i++ ;
			
			string += "<"+XMLTagName_scenario+" "+
						XMLTagName_uid +"=\""+getUid()+"\" "+	
						XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(usedScenario))+"\" " +
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
		for (Element VeM :  ModelicaMLServices.getSortedByName(gmd.getGeneratedModels())) {
			HashSet<TreeObject> requirements = gmd.getRequirements(VeM);
			boolean modelContains  = false;
			if (requirements != null) {
				for (TreeObject requirementTreeObject : requirements) {
					Element type = requirementTreeObject.getComponentType();
					if (evaluatedRequirement.equals(type)) {
						
						String statusDotPath = requirementTreeObject.getDotPath().trim() + "." + gmd.requirementStatusPropertyName;
						String key = gmd.getModelToTreeItemKeyString(VeM, statusDotPath);

						boolean isViolated = gmd.getViolatedRequirements().contains(key);
						boolean isEvaluated = gmd.getEvaluatedRequirements().contains(key);
						boolean isNotEvaluated = gmd.getNotEvaluatedRequirements().contains(key);
						
						// if only violated requirements should be shown
						if (onlyViolatedRequirements && isViolated) {
							modelContains = true;
						}
						// if only not violated should be shown
						else if (onlyNotViolatedRequirements && isEvaluated && !isViolated) {
							modelContains = true; 
						}
						// of only not evaluated should be shown
						else if (onlyNotEvaluatedRequirements && isNotEvaluated) {
							modelContains = true; 
						} 
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
			string += "<"+XMLTagName_violatedRequirements+" " +  
						XMLTagName_name + "=\""+ViewName_violatedRequirements+"\">";
			
			string += "<"+XMLTagName_description+">" + StringEscapeUtils.escapeXml(ViewDescription_violatedRequirements)+  "</"+XMLTagName_description+">";

		}
		else if (onlyNotViolatedRequirements) {
			evaluatedRequirements = gmd.getRequirementsNotViolatedInScenarios();
			string += "<"+XMLTagName_notViolatedRequirements+" "+ 
							XMLTagName_name + "=\""+ViewName_notViolatedRequirements+"\">";
			
			string += "<"+XMLTagName_description+">" +  StringEscapeUtils.escapeXml(ViewDescription_notViolatedRequirements) +  "</"+XMLTagName_description+">";
		}

		for (Element evaluatedRequirement : ModelicaMLServices.getSortedByRequirementId(evaluatedRequirements)) {
			i++ ;
			string += "<"+XMLTagName_requirement+" "+
					XMLTagName_uid +"=\""+getUid()+"\" "+	
					XMLTagName_id + "=\""+ModelicaMLServices.getRequirementID(evaluatedRequirement) +"\" " +
					XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(evaluatedRequirement))+"\" " +
					XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(evaluatedRequirement))+"\" "+
					XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(evaluatedRequirement))+"\">";
					string += getAllRequirementComments(evaluatedRequirement);
		
			// add requirement text
//			string += "<"+XMLTagName_requirementText+">" + StringEscapeUtils.escapeHtml(ModelicaMLServices.getRequirementText(evaluatedRequirement)) + "</"+XMLTagName_requirementText+">"; 
			string += "<"+XMLTagName_requirementText+">" + StringEscapeUtils.escapeXml(ModelicaMLServices.getRequirementText(evaluatedRequirement)) + "</"+XMLTagName_requirementText+">";

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
		boolean showOnlyViolated = false;
		boolean showOnlyNotViolated = false;
		boolean showOnlyNotEvaluated = true;
		
		HashSet<Element> notEvaluatedRequirements = gmd.getNotEvaluatedRequirementElements();
		string += "<"+XMLTagName_notEvaluatedRequirements+" name=\""+ViewName_notEvaluatedRequirements+"\">";
		
		string += "<"+XMLTagName_description+">" +  StringEscapeUtils.escapeXml(ViewDescription_notEvaluatedRequirements)+  "</"+XMLTagName_description+">";


		for (Element notEvaluatedRequirement :  ModelicaMLServices.getSortedByRequirementId(notEvaluatedRequirements)) {
			i++ ;
			string += "<"+XMLTagName_requirement+" "+
					XMLTagName_uid +"=\""+getUid()+"\" "+	
					XMLTagName_id + "=\""+ModelicaMLServices.getRequirementID(notEvaluatedRequirement) +"\" " +
					XMLTagName_locateLink+"=\""+StringEscapeUtils.escapeXml(getLocateLink(notEvaluatedRequirement))+"\" " +
					XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(notEvaluatedRequirement))+"\" "+
					XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(notEvaluatedRequirement))+"\">";
					string += getAllRequirementComments(notEvaluatedRequirement);
		
			// add requirement text
//			string += "<"+XMLTagName_requirementText+">" + StringEscapeUtils.escapeHtml(ModelicaMLServices.getRequirementText(notEvaluatedRequirement)) + "</"+XMLTagName_requirementText+">"; 
			string += "<"+XMLTagName_requirementText+">" + StringEscapeUtils.escapeXml(ModelicaMLServices.getRequirementText(notEvaluatedRequirement)) + "</"+XMLTagName_requirementText+">";

			// loop over all VeM and see if this requirement is in the 
			string += getVeMForRequirement(notEvaluatedRequirement, showOnlyViolated, showOnlyNotViolated, showOnlyNotEvaluated);
			
			string += "</"+XMLTagName_requirement+">";
		}
		
		string += "<"+XMLTagName_count+">" + i + " of " + gmd.getAllFoundRequirements().size() + "</"+XMLTagName_count+">";
		
		string += "</"+XMLTagName_notEvaluatedRequirements+">";
		return string;
	}
	
	
	private String getNotImplementedRequirementItems(){
		String string = "";
		int i = 0;
		
		HashSet<Element> allFoundRequirements = new HashSet<Element>();
		allFoundRequirements.addAll(gmd.getAllFoundRequirements());
		HashSet<Element> allUsedRequirements = new HashSet<Element>();
		allUsedRequirements.addAll(gmd.getAllUsedRequirements());
		
		allFoundRequirements.removeAll(allUsedRequirements);
		
		HashSet<Element> allNotImplementedRequirements = allFoundRequirements;
//		if (allNotImplementedRequirements != null && allNotImplementedRequirements.size() > 0) {
			string += "<"+XMLTagName_notImplementedRequirements+" name=\""+ViewName_notImplementedRequirements+"\">";
			
			string += "<"+XMLTagName_description+">" +  StringEscapeUtils.escapeXml(ViewDescription_notImplementedRequirements) +  "</"+XMLTagName_description+">";
			
			for (Element requirement :  ModelicaMLServices.getSortedByRequirementId(allNotImplementedRequirements)) {
				
				// counter 
				i++ ;
				
				string += "<"+XMLTagName_requirement+" "+
							XMLTagName_uid +"=\""+getUid()+"\" "+		
							XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(requirement))+"\" "+
							XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(requirement))+"\">";
				string += getAllRequirementComments(requirement);
				string += "</"+XMLTagName_requirement+">";
			}
			
			string += "<"+XMLTagName_count+">" + i + " of " + gmd.getAllFoundRequirements().size() + "</"+XMLTagName_count+">";
			
			string += "</"+XMLTagName_notImplementedRequirements+">";
//		}
		return string;
	}
	

	private String getNotUsedScenarioItems(){
		String string = "";
		int i = 0;
		
		HashSet<Element> allFoundScenarios = new HashSet<Element>();
		allFoundScenarios.addAll(gmd.getAllFoundScenarios());
		HashSet<Element> allUsedScenarios = new HashSet<Element>();
		allUsedScenarios.addAll(gmd.getAllScenarios());
		
		allFoundScenarios.removeAll(allUsedScenarios);
		
		HashSet<Element> allNotUsedScenarios = allFoundScenarios;
//		if (allNotUsedScenarios != null && allNotUsedScenarios.size() > 0) {
			
			string += "<"+XMLTagName_notUsedScenarios+" name=\""+ViewName_notUsedScenarios+"\">";
			
			string += "<"+XMLTagName_description+">" +  StringEscapeUtils.escapeXml(ViewDescription_notUsedScenarios) +  "</"+XMLTagName_description+">";
			
			for (Element scenario :  ModelicaMLServices.getSortedByName(allNotUsedScenarios)) {
				// counter
				i++;
				
				string += "<"+XMLTagName_scenario+" "+
							XMLTagName_uid +"=\""+getUid()+"\" "+	
							XMLTagName_name+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getName(scenario))+"\" "+
							XMLTagName_qualifiedName+"=\""+StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(scenario))+"\">";
				string += getAllComments(scenario);
				string += "</"+XMLTagName_scenario+">";
			}
			
			string += "<"+XMLTagName_count+">" + i + " of " + gmd.getAllFoundScenarios().size() + "</"+XMLTagName_count+">";
			string += "</"+XMLTagName_notUsedScenarios+">";
//		}
			
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
	
	private String getPlotLink(Element model){
		String linkAdress = "";
		linkAdress = "plot:" + ModelicaMLServices.getQualifiedName(model);
		return linkAdress;
	}
	
	private String getPlotLink(Element model, TreeObject treeObject){
		String linkAdress = "";
		linkAdress = "plot:" + ModelicaMLServices.getQualifiedName(model) + Constants.linkDelimiter + treeObject.getDotPath();
		return linkAdress;
	}
	
	private String getPlotRequirementStatusLink(Element model, TreeObject treeObject){
		String linkAdress = "";
		linkAdress = "plot:" + ModelicaMLServices.getQualifiedName(model) + Constants.linkDelimiter + treeObject.getDotPath();
		return linkAdress + "." + Constants.propertyName_mStatus;
	}
	
	
	
	/*
	 * Additional info 
	 */
	private String getAllComments(Element element) {
		String string = "";
//		int i = 0;
		
		string += "<"+XMLTagName_comments+">";
		if (element instanceof Element) {
			for (Comment comment : element.getOwnedComments()) {
				//counter
//				i++;
//				string += "<"+XMLTagName_comment+">" + StringEscapeUtils.escapeHtml(comment.getBody()) + "</"+XMLTagName_comment+">";
				string += "<"+XMLTagName_comment+">" + StringEscapeUtils.escapeXml(comment.getBody()) + "</"+XMLTagName_comment+">";
			}
		}
//		string += "<"+XMLTagName_count+">" + i + "</"+XMLTagName_count+">";
		string += "</"+XMLTagName_comments+">";
		return string;
	}
	
	/*
	 * Additional info 
	 */
	private String getAllRequirementComments(Element element) {
		String string = "";
//		int i = 0;
		
		string += "<"+XMLTagName_comments+">";
		if (element instanceof Element) {
			for (Comment comment : element.getOwnedComments()) {
				
				// requierement text is also a comment. Do not add requirement text as comment
				if (!comment.getBody().trim().equals(ModelicaMLServices.getRequirementText(element).trim())) {
					
					//counter
//					i++;
//					string += "<"+XMLTagName_comment+">" + StringEscapeUtils.escapeHtml(comment.getBody()) + "</"+XMLTagName_comment+">";
					string += "<"+XMLTagName_comment+">" + StringEscapeUtils.escapeXml(comment.getBody()) + "</"+XMLTagName_comment+">";
				}
			}
		}
//		string += "<"+XMLTagName_count+">" + i + "</"+XMLTagName_count+">";
		string += "</"+XMLTagName_comments+">";
		return string;
	}
	
	
	/*
	 * File handling
	 */
	public String createReport(String projectName, String folderName, boolean askWhereToStore) throws URISyntaxException, IOException, CoreException{
		String filePath = null;
		String xmlFileName = "report.xml";
		
		if (contentType == XMLContent) {
			
			Boolean alwaysCopySimulationResultFilesIntoVerificationSessionReport = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "alwaysCopySimulationResultFilesIntoVerificationSessionReport", false, null);

			// ask for copying sim files 
			if (alwaysCopySimulationResultFilesIntoVerificationSessionReport) {
				copySimulationFiles = alwaysCopySimulationResultFilesIntoVerificationSessionReport;
			}
			else {
				copySimulationFiles = MessageDialog.openQuestion(new Shell(), "Copy Simulation Files?", "Should all simulation files be copies to the report folder?");
			}
			
			progressDialog = new ProgressMonitorDialog(new Shell());
			monitor = progressDialog.getProgressMonitor();
			
			// open dialog
			progressDialog.open();
			
			// create report content
			fileContent = getXMLReportContent();
			
			// if selected -> ask user where to store the report files
			if (askWhereToStore) {
				// TODO: open file dialog so that the user can select where to store the report
			}
			
			// store files in specified project 
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
				
				if (folder.exists() || folderCreated) {
					
//					filePath = folderPath + "/" + xmlFileName;
//					// correct path if needed
//					filePath = filePath.replaceAll("%20", " ");
					
//					Path folderAbsolutePath = new Path(folderPath);
//					Path xmlReportFileAbsolutePath = new Path(folderPath + "/" + xmlFileName);
//					filePath = xmlReportFileAbsolutePath.toOSString();
					
					// create report files: XML, HTML 
					filePath = storeFile(folderPath, xmlFileName);
					
					Boolean generateOWLReportFiles = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "generateOWLReportFiles", false, null);

					if (generateOWLReportFiles) {
						// create OWL report
						monitor.setTaskName("Creating OWL report ...");
						OntologyGenerator og = new OntologyGenerator(gmd, rootElement, progressDialog);
						try {
							// take the folder where the report.html is: 
							Path path = new Path(filePath);
							IPath ontologyFolderPath = path.removeLastSegments(1);
							
							// create owl file
							String ontologyFilePath = og.createOntology(ontologyFolderPath.toOSString());
							
						} catch (OWLOntologyCreationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (OWLOntologyStorageException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					// close progress monitor
					progressDialog.close();
				}
			}
			
			// close progress monitor
			progressDialog.close();

		}
		
		// refresh the projects browser
		ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
		
		return filePath;
	}

	private String storeFile(String folderPath, String xmlFileName) throws URISyntaxException, IOException, CoreException{
		if (fileContent != null) {

			// pretty printing
//          Source xmlInput = new StreamSource(new StringReader(fileContent));
//	        StreamResult xmlOutput = new StreamResult(new StringWriter());
	
//	        // Configure transformer
//	        Transformer transformer = TransformerFactory.newInstance().newTransformer(); // An identity transformer
////	    transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "testing.dtd");
//	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//	        transformer.transform(xmlInput, xmlOutput);
			
//			String generatedModelPackageName = StringUtls.replaceSpecChar( ((NamedElement)gmd.getGeneratedModelsPackage()).getName()) + "_" + System.currentTimeMillis();
			String generatedModelPackageName = StringUtls.replaceSpecChar( ((NamedElement)gmd.getGeneratedModelsPackage()).getName()) + "_" + VerificationServices.getTimeStamp();
			
			monitor.setTaskName("Copying Report Data ...");
			
			// copy folder
			File folderToCopy = new File(FileLocator.toFileURL(Platform.getBundle("org.openmodelica.modelicaml.helper").getEntry("sources/reportData")).toURI());

			IFileSystem fileSystem = EFS.getLocalFileSystem();
			IFileStore reportData = fileSystem.getStore(folderToCopy.toURI());
			IFileStore reportDataCopy = fileSystem.getStore(java.net.URI.create(folderPath + "/" + generatedModelPackageName + "/" + "reportData"));
			reportData.copy(reportDataCopy, EFS.OVERWRITE, monitor);
			
			String xmlPath = folderPath + "/" + generatedModelPackageName + "/" + xmlFileName;
			String htmlPath = folderPath + "/" + generatedModelPackageName + "/" + xmlFileName + ".html";
			
			
			if (isCopySimulationFiles()) {
				
				for (Element  VeM : gmd.getSimulationResultsFile().keySet()) {
					
					String simFilePath = gmd.getSimulationResultsFile().get(VeM);
					monitor.setTaskName("Copying file: " + simFilePath);
					
					if (simFilePath != null && new File(simFilePath).exists()) {
						
						simFilePath = simFilePath.replaceAll("\\\\", "/");
						String simFileName = new File(simFilePath).getName();
						
						//copy file
						new LocalFile(new File(simFilePath)).copy(new LocalFile(new File(folderPath + "/" + generatedModelPackageName + "/" + simFileName)), EFS.NONE, monitor);
						
						// the code below did not work because if source and destination are on different drives (e.g. C: and D:)
//						IFileStore simFile = fileSystem.getStore(java.net.URI.create(simFilePath));
//						IFileStore simFileCopy = fileSystem.getStore(java.net.URI.create(folderPath + "/" + generatedModelPackageName + "/" + simFileName));
//						simFile.copy(simFileCopy, EFS.OVERWRITE, monitor);
					}
				}
			}
			
			/*
			 * http://docs.oracle.com/javase/1.4.2/docs/api/java/io/BufferedWriter.html
			 * "In general, a Writer sends its output immediately to the underlying character or byte stream. 
			 *  Unless prompt output is required, it is advisable 
			 *  to wrap a BufferedWriter around any Writer whose write() operations may be costly, 
			 *  such as FileWriters and OutputStreamWriters."
			 *  
			 *  Use: new BufferedWriter(new FileWriter("foo.out"));
			 */
			
			// write xml 
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(xmlPath),"UTF-8"));
			out.write(fileContent);
//			out.write(xmlOutput.toString());
			out.close();
			
			// export to HTML
			exportToHtml(folderPath, xmlPath, htmlPath);
			
			progressDialog.close();
			
			return htmlPath;
//				FileOutputStream fos = new FileOutputStream(filePath); 
//				OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");
//				out.write(Constants.fileEncoding);
//				out.close();
		}
		return null;
	}

	
	private void exportToHtml(String folderPath, String xmlPath, String htmlPath) throws URISyntaxException, IOException, CoreException{
		
		// translate
		monitor.setTaskName("Translating To HTML ...");
		File xsltPath = new File(FileLocator.toFileURL(Platform.getBundle("org.openmodelica.modelicaml.helper").getEntry("/sources/xslt/reportTranslator.xslt")).toURI());

        Source xmlSource = new StreamSource(xmlPath);
        Source xsltSource = new StreamSource(xsltPath);

        TransformerFactory transFact = TransformerFactory.newInstance();
        Transformer trans = null;
        
		try {
			trans = transFact.newTransformer(xsltSource);
			trans.transform(xmlSource, new StreamResult(new FileOutputStream(htmlPath)));

		} catch (TransformerConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public int getUid() {
		//increase id by 1 
		this.uid++;
		
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public boolean isCopySimulationFiles() {
		return copySimulationFiles;
	}

	public void setCopySimulationFiles(boolean copySimulationFiles) {
		this.copySimulationFiles = copySimulationFiles;
	}
}
