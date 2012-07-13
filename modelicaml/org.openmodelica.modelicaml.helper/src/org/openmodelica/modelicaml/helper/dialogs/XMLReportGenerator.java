package org.openmodelica.modelicaml.helper.dialogs;

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
import java.util.HashSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.helper.handlers.ScenariosToRequirementsRelationsDiscoveryToolbarHandler.GeneratedModelsData;

public class XMLReportGenerator {

	public static String createFile(String projectName, String folderName, String fileName, String fileContent, boolean askWhereToStore){
		
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
			
			if (folder.exists() || folderCreated) {
				filePath = folderPath + "/" + fileName;
				boolean ok = storeFile(filePath, fileContent);
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


	private static boolean storeFile(String filePath, String fileContent){
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
	
	
	public static String getXMLReportContent(GeneratedModelsData gmd){
		
		String string = "";
		string += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
		// TODO: add here the XSL tag 
		
		string += "<report>";
		
		string += "<date>";
		Calendar c1 = Calendar.getInstance(); // today
		Date date = c1.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		string += sdf.format(date);
		string += "</date>";
		
		for (Element model : gmd.getGeneratedModels()) {
			string += "<simulationModel name=\""+getQName(model)+"\">";

			HashSet<TreeObject> scenarios = gmd.getScenarios(model);
			if (scenarios != null) {
				for (TreeObject scenarioTreeObject : scenarios) {
					
					Element scenario = scenarioTreeObject.getComponentType();
					
					string += "<scenario name=\""+getQName(scenario)+"\">";
					
					// add the system model
					for (Element systemModel : gmd.getSystemModels()) {
						string += "<systemModel name=\""+getQName(systemModel)+"\">";
						string += "</systemModel>";
					}
					
					HashSet<Element> violatedRequirements = gmd.getScenarioToEvaluatedRequirements().get(scenario);
					HashSet<Element> notViolatedRequirements = gmd.getScenarioWithNotViolatedRequirements(scenario);
					HashSet<Element> notEvaluatedRequirements = gmd.getScenarioToNotEvaluatedRequirements().get(scenario);
					
					string += getRequirementsString(violatedRequirements, "violatedRequirements");
					string += getRequirementsString(notViolatedRequirements, "notViolatedRequirements");
					string += getRequirementsString(notEvaluatedRequirements, "notEvaluatedRequirements");
					
					string += "</scenario>";
				}
			}
			string += "</simulationModel>";
		}
		
		string += "</report>";
		return string;
	}
	
	
	private static String getRequirementsString(HashSet<Element> list, String tagName){
		String string = "";
		if (list != null) {
			string += "<"+tagName+">";
			for (Element requirement : list) {
				
				String isViolated = "false";
				if (tagName.startsWith("violatedRequirements")) {
					isViolated = "true";
				}
				
				string += "<requirement isViolated=\""+isViolated+"\" name=\""+getQName(requirement)+"\">";
				string += "</requirement>";
				
			}
			string += "</"+tagName+">";
		}
		return string;
	}
	
	private static String getQName(Element element) {
		if (element instanceof NamedElement) {
			return ((NamedElement)element).getQualifiedName();
		}
		return "NotDefined";
	}

}
