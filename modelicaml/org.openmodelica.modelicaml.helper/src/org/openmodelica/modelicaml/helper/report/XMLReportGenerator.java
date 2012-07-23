package org.openmodelica.modelicaml.helper.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.helpers.VerificationExecutionServices;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.helper.structures.GeneratedModelsData;

public class XMLReportGenerator {

	private GeneratedModelsData gmd;
	private HashMap<Element, ClassInstantiation> preparedModelInstantiations;
	
	// default line break is for HTML
	private String lineDelimiter = "<br />";
	
	private final static String XMLTagName_report = "report";
	private final static String XMLTagName_date = "date";
	private final static String XMLTagName_verificationModel = "verificationModel";
	private final static String XMLTagName_systemModel = "systemModel";
	private final static String XMLTagName_comments = "comments";
	private final static String XMLTagName_scenario = "scenario";
	private final static String XMLTagName_notUsedScenarios = "notUsedScenarios";
	private final static String XMLTagName_notImplementedRequirements= "notImplementedRequirements";
	private final static String XMLTagName_requirement = "requirement";
	private final static String XMLTagName_requirementText = "text";

	private final static String XMLTagName_client = "client";
	
	private final static String XMLTagName_isMandatory = "isMandatory";
	private final static String XMLTagName_binding = "binding";
	
	private final static String XMLTagName_isEvaluated = "isEvaluated";
	private final static String XMLTagName_isViolated = "isViolated";
	
	private final static String XMLTagName_locateLink = "locateLink";
	private final static String XMLTagName_plotLink =  "plotLink";
	
	private final static String XMLTagName_name = "name";
	private final static String XMLTagName_qualifiedName =   "qualifiedName";
	private final static String XMLTagName_instanceName = "instanceName";

	private final static String XMLTagName_id =   "id";

	private final static String XMLTagName_newPositiveRelations = "newPositiveRelations";
	private final static String XMLTagName_newNegativeRelations = "newNegativeRelations";
	
	
	private String fileContent;
	
	public static int XMLContent = 0;
	
	public XMLReportGenerator(GeneratedModelsData gmd, int contentType){
		
		this.gmd = gmd;
		preparedModelInstantiations = gmd.getGenerator().getPreparedModelInstantiations();
		
		if (contentType == XMLContent) {
			fileContent = getXMLReportContent();
		}
	}
	
	
	
	public String getXMLReportContent(){
		
		String string = "";
		string += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
		// TODO: add here the XSL tag 
		
		string += "<"+XMLTagName_report+">";
		
		string += "<"+XMLTagName_date+">";
		Calendar c1 = Calendar.getInstance(); // today
		Date date = c1.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		string += sdf.format(date);
		string += "</"+XMLTagName_date+">";
		
		// simulated models
		string += getVeMItems();

		// TODO: Not simulated models
		
		// TODO: Indicators for how many models were simulated, how many requirements were violated and evaluated, etc.
		

		// requirements for which no binding could be derived
		string += getNotImplementedRequirementItems();
		
		// Scenarios that were not used because they do not stimulate the model properly
		string += getNotUsedScenariosItems();
		
		// new positive and negative relations between scenarios and requirements
		string += getNewRelationItems(true);
		string += getNewRelationItems(false);
		
		string += "</"+XMLTagName_report+">";
		return string;
	}
	
	
	private String getVeMItems(){
		String string = "";
		for (Element model : gmd.getGeneratedModels()) {
			string += "<"+XMLTagName_verificationModel+" "+XMLTagName_name+"=\""+ModelicaMLServices.getName(model)+"\" "+XMLTagName_qualifiedName+"=\""+ModelicaMLServices.getQualifiedName(model)+"\">";

			ClassInstantiation ci = ModelicaMLServices.getModelInstantiation(model, preparedModelInstantiations);
			
			string += getSystemModelItems(ci);
			
			string += getScenarioItems(ci);
			
			string += getRequirementItems(ci);
			
			string += "</"+XMLTagName_verificationModel+">";
		}
		return string;
	}
	
	private String getNewRelationItems(boolean isPositiveRelations){
		
		String string = "";
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
					XMLTagName_locateLink+"=\""+getLocateLink(scenarioType)+"\" " +
					XMLTagName_instanceName+"=\""+scenario.getDotPath()+"\" "+
					XMLTagName_name+"=\""+ModelicaMLServices.getName(scenarioType)+"\"  "+
					XMLTagName_qualifiedName+"=\""+ModelicaMLServices.getQualifiedName(scenarioType)+"\">";
			
			HashSet<TreeObject> requirements = relationsMap.get(scenario);
			
			if (requirements != null) {
				for (TreeObject requirement : requirements) {
					
					Element requirementType = requirement.getComponentType();
					
					string += "<"+XMLTagName_requirement+" "+
							XMLTagName_locateLink+"=\""+getLocateLink(requirementType)+"\" " +
							XMLTagName_instanceName+"=\""+requirement.getDotPath()+"\" "+
							XMLTagName_name+"=\""+ModelicaMLServices.getName(requirementType)+"\"  "+
							XMLTagName_qualifiedName+"=\""+ModelicaMLServices.getQualifiedName(requirementType)+"\">";
					string += "</"+XMLTagName_requirement+">";
				}
			}
			
			string += "</"+XMLTagName_scenario+">";
		}
		
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
			
			if (gmd.getGenerator().getSystemModels().contains(type)) {
				
				string += "<"+XMLTagName_systemModel+" "+
								XMLTagName_locateLink+"=\""+getLocateLink(VeM, treeObject)+"\" " +
								XMLTagName_plotLink+"=\""+getPlotStatusLink(VeM, treeObject)+"\" " +
								XMLTagName_instanceName+"=\""+treeObject.getDotPath()+"\" "+
								XMLTagName_name+"=\""+ModelicaMLServices.getName(type)+"\"  "+
								XMLTagName_qualifiedName+"=\""+ModelicaMLServices.getQualifiedName(type)+"\">";
				
				// add comments
				string += 	"<"+XMLTagName_comments+">" + getAllComments(type, "<br />") + "</"+XMLTagName_comments+">";

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
						XMLTagName_locateLink+"=\""+getLocateLink(VeM, scenario)+"\" " +
						XMLTagName_plotLink+"=\""+getPlotStatusLink(VeM, scenario)+"\" " +
						XMLTagName_instanceName+"=\""+scenario.getDotPath()+"\" "+
						XMLTagName_name+"=\""+ModelicaMLServices.getName(type)+"\" "+
						XMLTagName_qualifiedName+"=\""+ModelicaMLServices.getQualifiedName(type)+"\">";
				string += "<"+XMLTagName_comments+">" + getAllComments(type, lineDelimiter) + "</"+XMLTagName_comments+">";

				// Add clients (isMandatory, binding, plot link)
				string += getClientItems(VeM, scenario);
 

				string += "</"+XMLTagName_scenario+">";
			}
		}
		
		return string;
	}
	
	private String getRequirementItems(ClassInstantiation ci){
		String string = "";
		
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
			string += "<"+XMLTagName_requirementText+">" + ModelicaMLServices.getRequirementText(type) + "</"+XMLTagName_requirementText+">"; 

			// Add clients (isMandatory, binding, plot link)
			string += getClientItems(VeM, requirement);

			string += "</"+XMLTagName_requirement+">";
		}
		
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
						XMLTagName_locateLink+"=\""+getLocateLink(VeM, client)+"\" " +
						XMLTagName_plotLink +"=\"" + getPlotLink(VeM, client) + "\"" +
								">";
			
			string += "<"+XMLTagName_binding+">" + client.getFinalModificationRightHand() + "</"+XMLTagName_binding+">";;
			string += "</"+XMLTagName_client+">";
		}
		return string;
	}
	
	
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
	
	private String getNotImplementedRequirementItems(){
		String string = "";
		HashSet<Element> allFoundRequirements = gmd.getGenerator().getVsc().getAllRequirements();
		HashSet<Element> allUsedRequirements= gmd.getAllRequirements();
		
		allFoundRequirements.removeAll(allUsedRequirements);
		
		HashSet<Element> allNotImplementedRequirements = allFoundRequirements;
		if (allNotImplementedRequirements != null && allNotImplementedRequirements.size() > 0) {
			string += "<"+XMLTagName_notImplementedRequirements+">";
			
			for (Element requirement : allNotImplementedRequirements) {
				string += "<"+XMLTagName_requirement+" "+XMLTagName_name+"=\""+ModelicaMLServices.getName(requirement)+"\" "+XMLTagName_qualifiedName+"=\""+ModelicaMLServices.getQualifiedName(requirement)+"\">";
				string += "<"+XMLTagName_comments+">" + getAllComments(requirement, lineDelimiter) + "</"+XMLTagName_comments+">";
				string += "</"+XMLTagName_requirement+">";
			}
			
			string += "</"+XMLTagName_notImplementedRequirements+">";
		}
		return string;
	}
	

	private String getNotUsedScenariosItems(){
		String string = "";
		
		HashSet<Element> allFoundScenarios = gmd.getGenerator().getVsc().getAllScenarios();
		HashSet<Element> allUsedScenarios = gmd.getAllScenarios();
		
		allFoundScenarios.removeAll(allUsedScenarios);
		
		HashSet<Element> allNotUsedScenarios = allFoundScenarios;
		if (allNotUsedScenarios != null && allNotUsedScenarios.size() > 0) {
			string += "<"+XMLTagName_notUsedScenarios+">";
			
			for (Element scenario : allNotUsedScenarios) {
				string += "<"+XMLTagName_scenario+" "+XMLTagName_name+"=\""+ModelicaMLServices.getName(scenario)+"\" "+XMLTagName_qualifiedName+"=\""+ModelicaMLServices.getQualifiedName(scenario)+"\">";
				string += "<"+XMLTagName_comments+">" + getAllComments(scenario, lineDelimiter) + "</"+XMLTagName_comments+">";
				string += "</"+XMLTagName_scenario+">";
			}
			
			string += "</"+XMLTagName_notUsedScenarios+">";
		}
		return string;
	}

	
	
	/*
	 * Additional info 
	 */
	private String getAllComments(Element element, String lineBreakString) {
		String commentString = "";
		if (element instanceof Element) {
			for (Comment comment : element.getOwnedComments()) {
				commentString += comment.getBody() + lineBreakString;
			}
		}
		return commentString;
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
