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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.common.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.papyrus.infra.emf.utils.BusinessModelResolver;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.Type;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.builders.NotificationBuilder;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.utls.ResourceManager;

public class ModelicaMLServices {

	/*
	 * Papyrus notification temporary popup.
	 * @messageType is -1=no icon, 0=info icon, 1=warning icon, 2=error icon
	 */
	public static void notify(
			final String title, 
			final String message, 
			final int messageType, 
			final long displayTimeInSeconds
			){
		
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				
				NotificationBuilder notification = new NotificationBuilder();

				notification.setAsynchronous(true);
				notification.setTemporary(true);
				notification.setDelay(displayTimeInSeconds * 1000);
//				notification.setHTML(true); // does not work :(
				
				if (messageType == 0) {
					notification.setType(Type.INFO);
				}
				else if (messageType == 1) {
					notification.setType(Type.WARNING);
				}
				else if (messageType == 2) {
					notification.setType(Type.ERROR);
				}
				
				notification.setTitle(title);
				notification.setMessage(message);
				
				// set ModelicaML image
				notification.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/ModelicaML_logo.gif"));
				
				// run
				notification.run();
			}
		});
	}
	
	/*
	 * Regeneration of  code
	 */
	public static HashMap<Resource,Long> modelModificationStamp = new HashMap<Resource,Long>();
	public static HashMap<Resource,Long> codeGenerationStamp = new HashMap<Resource,Long>();
	
	public static boolean regenerateCode(Resource resource){
		
		Long modifiedModelTimeStamp = modelModificationStamp.get(resource);
		Long generatedCodeTimeStamp = codeGenerationStamp.get(resource);
		
//		System.err.println("Checking if it is neccesary to regenerate code for: "+  resource);
//		System.err.println("modifiedModelTimeStamp: " + modifiedModelTimeStamp);
//		System.err.println("generatedCodeTimeStamp: " + generatedCodeTimeStamp);
//		if (generatedCodeTimeStamp != null && modifiedModelTimeStamp != null) {			
//			System.err.println("modifiedModelTimeStamp < generatedCodeTimeStamp: " + (modifiedModelTimeStamp < generatedCodeTimeStamp));
//		}
		
		if (generatedCodeTimeStamp != null && modifiedModelTimeStamp != null) {
			// if code was generated after the model modification -> no need for regenerating code
			if (modifiedModelTimeStamp < generatedCodeTimeStamp) {
				
				// Check if the code-gen folder exists in the project
				String projectName = resource.getURI().segment(1);
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IWorkspaceRoot root = workspace.getRoot();
				IProject iProject = root.getProject(projectName);
				
				String projectPath = iProject.getLocationURI().toString().replaceFirst("file:\\/", "");
				String codeSynch = projectPath + "/" + Constants.folderName_code_gen;
				File folder = new File(codeSynch);
				// if the folder exists -> then skip the cg
				boolean codeGenDirExists = folder.isDirectory();
				if (codeGenDirExists) {
					
//					System.err.println("Skipping Modelica code generation ... ");

					String resourceName = resource.getURI().toPlatformString(true);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
					String lastCodeGeneration = sdf.format(generatedCodeTimeStamp);
					String lastModification = sdf.format(modifiedModelTimeStamp);
					
					notify("ModelicaML: Code Generation", 
							"Code generation was skipped." +
							"\nThe model '"+resourceName+"' " +
							"\nhas not changed since the last code generation." +
							
							"\n\nLast model modification: "+lastModification + 
							"\nLast code generation : "+lastCodeGeneration, 
							-1,
							2);

					return false;
				}
			}
		}
		return true;
	}
	
	
	public static boolean saveModel(UmlModel umlModel){
		if (umlModel!=null && umlModel.getResource().isModified()) {
			try {
//				// get the Papyrus model in order to make sure that the model is not being saved, hence is not dirty anymore.
//				if (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
//					IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
//					PapyrusMultiDiagramEditor papyrusEditor = ((PapyrusMultiDiagramEditor)editorPart);
//					
//					boolean isDirty = papyrusEditor.isDirty();
//					
//				}
				umlModel.saveModel();
				String resourceName = umlModel.getResource().getURI().toPlatformString(true);
				notify("ModelicaML: Save Model", 
						"The model '"+resourceName+"' " +
						"\nwas saved. It will need to be reloaded in editor.",
						1,
						2);
				
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				notify("ModelicaML: Save Model", 
						"Failed to save the model.",
						2,
						2);
			}
		}
		return false;
	}

	
	/*
	 * Element adoption
	 */
	public static EObject adaptSelectedElement( Object selection) {
		
		EObject eObject = null;
		if(selection != null) {
			// from any adaptable
			if(selection instanceof IAdaptable) {
				selection = ((IAdaptable)selection).getAdapter(EObject.class);
			}
	
			Object businessObject = BusinessModelResolver.getInstance().getBusinessModel(selection);
			if(businessObject instanceof EObject) {
				eObject = (EObject)businessObject;
			}
		}
		return eObject;
	}
	
	
	/*
	 * Requirements data
	 */
	public static String getRequirementID(Element req){
		if (req instanceof NamedElement) {
			Stereotype s = ((NamedElement)req).getAppliedStereotype(Constants.stereotypeQName_Requirement);
			if (s != null) {
				Object o =((NamedElement)req).getValue(s, Constants.propertyName_id);
				if (o != null) {
					return o.toString();
				}
			}
		}
		return "";
	}
	
	public static String getRequirementText(Element req){
		if (req instanceof NamedElement) {
			Stereotype s = ((NamedElement)req).getAppliedStereotype(Constants.stereotypeQName_Requirement);
			if (s != null) {
				Object o =((NamedElement)req).getValue(s, Constants.propertyName_text);
				if (o != null) {
					return o.toString();
				}
			}
		}
		return "";
	}

	
	public static boolean hasModelicaMLStereotype(Element element){
		EList<Stereotype> sList = element.getAppliedStereotypes();
		for (Stereotype stereotype : sList) {
			if (stereotype.getQualifiedName().startsWith(Constants.modelingLanguageName)) {
				return true;
			}
		}
		return false;
	}
	
	
	public static Stereotype getFirstModelicaMLComponentStereotype(Property property) {
		Stereotype stereotype = null;
		
		stereotype = property.getAppliedStereotype(Constants.stereotypeQName_Component);
		if (stereotype != null) { return stereotype; }
		stereotype = property.getAppliedStereotype(Constants.stereotypeQName_Variable);
		if (stereotype != null) { return stereotype; }
		stereotype = property.getAppliedStereotype(Constants.stereotypeQName_RequirementInstance);
		if (stereotype != null) { return stereotype; }
		stereotype = property.getAppliedStereotype(Constants.stereotypeQName_CalculatedProperty);
		if (stereotype != null) { return stereotype; }
		return stereotype ;
	}
	
	
	/*
	 * Utilities
	 */
	
	
	public static String getQualifiedNameNormalized(String modelName, String segmentSeparatorOriginal,  String segmentSeparatorNew){
		// e.g. from UML to Modelica: segmentSeparatorOriginal = "::", segmentSeparatorNew="."
		return StringUtls.replaceSpecCharExceptThis( modelName, segmentSeparatorOriginal).replaceAll(segmentSeparatorOriginal, segmentSeparatorNew);
	}
	
	public static String getQualifiedNameNormalized(Element element, String segmentSeparatorOriginal,  String segmentSeparatorNew){
		// e.g. from UML to Modelica: segmentSeparatorOriginal = "::", segmentSeparatorNew="."
		return StringUtls.replaceSpecCharExceptThis( getQualifiedName(element), segmentSeparatorOriginal).replaceAll(segmentSeparatorOriginal, segmentSeparatorNew);
	}
	
	
	
	public static int getBodyIndex(OpaqueAction behavior, String language) {
		int index = 0;
		boolean isFound = false;

		// test if the language exists
		Iterator<String> it = behavior.getLanguages().iterator();
		while (it.hasNext() && !isFound) {
			String lang = it.next();
			if (lang.equalsIgnoreCase(language)) {
				isFound = true;
			} else {
				index++;
			}
		}
		// returns -1 if not found
		if (!isFound) {
			index = -1;
		}
		return index;
	}
	
	
	
	
	public static List<TreeObject> getSortedByDotPath(HashSet<TreeObject> set){
		if (set == null) { return null; }

		List<TreeObject> listSorted = new ArrayList<TreeObject>(set);
		Comparator<TreeObject> c = new Comparator<TreeObject>() {
			@Override
			public int compare(TreeObject arg0, TreeObject arg1) {
				return arg0.getDotPath().compareToIgnoreCase(arg1.getDotPath());
			}
		};
		Collections.sort(listSorted, c);
		
		return listSorted;
	}
	
	public static List<Element> getSortedByName(HashSet<Element> set){
		if (set == null) { return null; }

		List<Element> listSorted = new ArrayList<Element>(set);
		Comparator<Element> c = new Comparator<Element>() {
			@Override
			public int compare(Element arg0, Element arg1) {
				if (arg0 instanceof NamedElement && arg1 instanceof NamedElement) {
					if (((NamedElement)arg0).getName() != null && ((NamedElement)arg1).getName() != null) {
						return ((NamedElement)arg0).getName().compareToIgnoreCase( ((NamedElement)arg1).getName());
					}
					else {
						return 0;
					}
				}
				if (arg0 != null && arg1 != null) {
					return arg0.toString().compareToIgnoreCase(arg1.toString());
				}
				else{
					return 0;
				}
			}
		};
		Collections.sort(listSorted, c);
		
		return listSorted;
	}
	
	// do not use it, it is very slow!
//	public static  List<Element> getSortedByEClassName(HashSet<Element> set){
//		if (set == null) { return null; }
//
//		List<Element> listSorted = new ArrayList<Element>(set);
//		Comparator<Element> c = new Comparator<Element>() {
//			@Override
//			public int compare(Element arg0, Element arg1) {
//				if (arg0 instanceof Element) {
//					
//					String name1 = ((Element)arg0).eClass().getName();
//					String name2 = ((Element)arg1).eClass().getName();
//					
//					return name1.compareToIgnoreCase( name2 );
//				}
//				return arg0.toString().compareToIgnoreCase(arg1.toString());
//			}
//		};
//		
//		Collections.sort(listSorted, c);
//		
//		return listSorted;
//	}
	
	public static  List<Element> getSortedByQName(HashSet<Element> set){
		if (set == null) { return null; }

		List<Element> listSorted = new ArrayList<Element>(set);
		Comparator<Element> c = new Comparator<Element>() {
			@Override
			public int compare(Element arg0, Element arg1) {
				if (arg0 instanceof NamedElement) {
					return ((NamedElement)arg0).getQualifiedName().compareToIgnoreCase( ((NamedElement)arg1).getQualifiedName());
				}
				return arg0.toString().compareToIgnoreCase(arg1.toString());
			}
		};
		Collections.sort(listSorted, c);
		
		return listSorted;
	}
	
	public static List<Element> getSortedByRequirementId(HashSet<Element> set){
		if (set == null) { return null; }
		List<Element> listSorted = new ArrayList<Element>(set);
		Comparator<Element> c = new Comparator<Element>() {
			@Override
			public int compare(Element arg0, Element arg1) {
				return ModelicaMLServices.getRequirementID(arg0).trim().compareToIgnoreCase( ModelicaMLServices.getRequirementID(arg1).trim());
			}
		};
		Collections.sort(listSorted, c);
		
		return listSorted;
	}
	
	
	public static Image getImage(Element element){
		
		if (element instanceof Package) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/papyrus/Package.gif");
		}
		else if (element.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif");
		}
		else if (element.getAppliedStereotype(Constants.stereotypeQName_Model) != null) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/model.gif");
		}
		else if (element.getAppliedStereotype(Constants.stereotypeQName_Function) != null) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/papyrus/FunctionBehavior.gif");
		}
		else if (element.getAppliedStereotype(Constants.stereotypeQName_Connector) != null) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/connector.gif");
		}
		else if (element.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) != null) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif");
		}
		else if (element.getAppliedStereotype(Constants.stereotypeQName_VerificationScenario) != null) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif");
		}
		else if (element.getAppliedStereotype(Constants.stereotypeQName_ValueMediatorsContainer) != null) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/valueMediatorsContainer.png");
		}
		else if (element.getAppliedStereotype(Constants.stereotypeQName_ValueMediator) != null) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/valueMediator.png");
		}
		else if (element.getAppliedStereotype(Constants.stereotypeQName_RequirementInstance) != null) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirementInstance.png");
		}
		else if (element.getAppliedStereotype(Constants.stereotypeQName_FunctionArgument) != null) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/Parameter.gif");
		}
		else if (element.getAppliedStereotype(Constants.stereotypeQName_Variable) != null) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/variable.png");
		}
		else if (element.getAppliedStereotype(Constants.stereotypeQName_Component) != null) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/component.png");
		}
		else if (element.getAppliedStereotype(Constants.stereotypeQName_CalculatedProperty) != null) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculatedProperty.png");
		}
		else if (element instanceof OpaqueBehavior) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/papyrus/OpaqueBehavior.gif");
		}
		else if (element instanceof PrimitiveType) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/type.gif");
		}
		else if (element instanceof Enumeration) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/papyrus/Enumeration.gif");
		}
		else if (element instanceof Class && ! (element instanceof Behavior)) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/Class.gif");
		}
		else if (element instanceof Port) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/Port.gif");
		}
		else if (element instanceof Property) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/Property.gif");
		}
		else if (element instanceof Port) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/Port.gif");
		}
		else if (element instanceof Comment) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/Comment.gif");
		}
		else if (element instanceof Generalization) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/Generalization.gif");
		}
		else if (element instanceof Dependency) {
			return ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/Dependency.gif");
		}
		
		// TODO: complete the list
		
		return null;
	}
	
	
	
	public static String getTimeStamp(){
		Calendar c1 = Calendar.getInstance(); // today
		Date date = c1.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	
	
	public static String getNamePostFix(NamedElement owner, String name){
		String postfix = "_";
		int highestPostFixNumber = 0;
	
		EList<Element> ownedElements = owner.getOwnedElements();
		for (Element element : ownedElements) {
			if (element instanceof NamedElement && ((NamedElement)element).getName().startsWith(name)) {
				
				String namePart = ((NamedElement)element).getName();
				String postFix = namePart.replaceFirst(name, "").replaceAll("_", "").trim();
				
				if (!postFix.equals("")) {
					try {
						int postFixNumber = Integer.valueOf(postFix);
						if (postFixNumber > highestPostFixNumber) {
							highestPostFixNumber = postFixNumber;
						}
					} catch (Exception e) {
						// ignore, do nothing.
					}
				}
			}
		}
		return postfix + (highestPostFixNumber + 1);	
	}
	
	
	
	public static boolean isModelicaMLProfileApplied(EObject eObject){
		
		Model rootModel = ((Element)eObject).getModel();
		if (rootModel != null) {
			List<Profile> profiles = rootModel.getAllAppliedProfiles();
			for (Profile profile : profiles) {
				if (profile.getQualifiedName().startsWith("ModelicaML::")) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	
	public static boolean isModelicaMLStereotypeApplied(Element element){
		List<Stereotype> sl = element.getAppliedStereotypes();
		for (Stereotype s: sl) {
			if (s.getQualifiedName().startsWith("ModelicaML::")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isModelicaMLStereotypeApplied(Element element, String stereotypeQName){
		List<Stereotype> sl = element.getAppliedStereotypes();
		for (Stereotype s: sl) {
			if (s.getQualifiedName().equals(stereotypeQName)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * OMC handling
	 */
	public static boolean containsOMCErrorMessage(String msg){
		if (	msg.toLowerCase().contains("Error:")
				|| msg.toLowerCase().contains("Warning:")
				|| msg.toLowerCase().contains("Error occured")
				|| msg.toLowerCase().contains("No reply from OMC")
			) {
			
			// TODO: create a full list of possible OMC error messages
			return true;
		}
		if (	msg.trim().toLowerCase().equals("false") 
				|| msg.trim().equals("Error")) {
			
			return true;
		}
		
		return false;
	}
	
	public static List<String> getFilesToLoad(String folderPath){
		List<String> list = new ArrayList<String>();

		if (folderPath.trim().endsWith("/")) {
			folderPath = folderPath.substring(0, folderPath.length() - 1);
		}
		
		File folder = new File(folderPath);
		
		if (folder.exists() && folder.isDirectory()) {
			
			// 1.Level
			
			// load all .mo files that are not pacakge.mo
			File[] files1Level = folder.listFiles();
			for (File file : files1Level) {
				boolean isMoFile = file.getName().endsWith(".mo");
				if (file.isFile() && isMoFile && !file.getName().equals("package.mo")) {
					list.add(formatPath(folderPath + "/" + file.getName()));
				}
			}
			
			// load a package.mo at 1.level 
			File packageMo = new File(folderPath + "/" + "pacakge.mo");
			if ( packageMo.exists()) {
				list.add(formatPath(folderPath + "/" + "pacakge.mo"));
			}
			
			// if there is no package.mo at 1.Level -> look into sub-folders and find package.mo files there
			else {
				// 2.Level
				File[] files2Level = folder.listFiles();
				for (File file : files2Level) {
					if (file.isDirectory()) {
						String subFolderPackageMoPath = folderPath + "/" + file.getName() + "/package.mo";
						File subFolderPackageMo = new File(subFolderPackageMoPath);
						if (subFolderPackageMo.exists()) {
							list.add(formatPath(subFolderPackageMoPath));
						}
					}
				}
			}
		}
		return list;
	}
	
	
	public static String formatPath(String path){
		while(path.contains("\\")){
			path = path.replace('\\', '/');
		}
		path = path.replaceAll("%20", " ");
		return path;
	}
	
	
	/*
	 * Files handling
	 */
	public static IStatus deleteFiles(List<String> filesToBeDeleted, IProgressMonitor monitor, String message){
		
		if (monitor.isCanceled()){
			return Status.CANCEL_STATUS;
		}

//		IFileSystem fileSystem = EFS.getLocalFileSystem();
		for (String filePath : filesToBeDeleted) {
			
			monitor.subTask(message + filePath );
			
			if (!ModelicaMLServices.containsOMCErrorMessage(filePath)) {

				File file = new File(filePath);
				
				if (file.exists()) {
					file.delete();
//					IFileStore fileToBeDeleted = fileSystem.getStore(java.net.URI.create("file:/"  + filePath ));
//					try {
//						fileToBeDeleted.delete(EFS.NONE, monitor);
//					} catch (CoreException e) {
////						e.printStackTrace();
//					}
				}
				
				if (monitor.isCanceled()){
					return Status.CANCEL_STATUS;
				}
			}
			
		}
		return Status.OK_STATUS;
	}
	
	
	/*
	 * TODO: This is a workaround for not encoding the generated code files in UTF-8
	 * Remove this when new Acceleo framework and file encoding is used.
	 * 
	 */
	
	public static void generatePackageEncodingFile(String projectName){
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject iProject = root.getProject(projectName);
		
		String projectPath = iProject.getLocationURI().toString().replaceFirst("file:\\/", "");
		String codeGenFiolderPath = projectPath + "/" + Constants.folderName_code_gen;
		
		File folder = new File(codeGenFiolderPath);
		
		if (folder.exists()) {
		
			// find the top-level pacakge.mo and get its parent (folder) 
			String folderPath = getFolderOfFirstPackageMOfile(codeGenFiolderPath);
			
			if (folderPath != null) {
				String filePath = folderPath + "/" + Constants.encodingPackageFileNameAndExtension;
//				File file = new File(filePath);
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
					out.write(Constants.fileEncoding);
					out.close();
					
//					FileOutputStream fos = new FileOutputStream(filePath); 
//					OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");
//					out.write(Constants.fileEncoding);
					out.close();
					
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static String getFolderOfFirstPackageMOfile(String folderAbsolutePath){
		
		File folder = new File(folderAbsolutePath);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.getName().equals("package.mo")) {
				return folderAbsolutePath;
			}
		}
		for (File file : files) {
			if (file.isDirectory()) {
				return getFolderOfFirstPackageMOfile(file.getAbsolutePath());
			}
		}
		return null;
	}
	


	
	public static String getQualifiedName(Element element) {
		if (element instanceof NamedElement) {
			String qName = ((NamedElement)element).getQualifiedName();
			if (qName != null) {
				return qName; 
			}
		}
		if (element != null) {
			return Constants.UKNOWN_NAME + "  -> " + element.toString();
		}
		
		return Constants.UKNOWN_NAME;
	}
	
	public static String getName(Element element) {
		if (element instanceof NamedElement) {
			String name = ((NamedElement)element).getName();
			if (name != null) {
				return name; 
			}
		}
		if (element != null) {
			return Constants.UKNOWN_NAME + "  -> " + element.toString();	
		}
		
		return Constants.UKNOWN_NAME;
		
	}
	
	
	
	
	/*
	 * Model instantiations
	 */
	
	
	public static HashMap<Element,ClassInstantiation> getModelInstantiations(
			HashSet<Element> models, 
			HashMap<Element,ClassInstantiation> preparedInstantiations, 
			HashSet<Element> preCollectedMediators,
			boolean reCollectMediatorsIfEmpty){
		
		HashMap<Element,ClassInstantiation> modelToInstantiations = new HashMap<Element, ClassInstantiation>();
		
		for (Element model : models) {
			
			ClassInstantiation newInstantiation = getModelInstantiation(model, preparedInstantiations, preCollectedMediators, reCollectMediatorsIfEmpty);
			
			if (newInstantiation != null) {
				modelToInstantiations.put(model, newInstantiation);
			}
		}
		
		return modelToInstantiations;
	}
	
	
	public static ClassInstantiation getModelInstantiation(Element model, 
			HashMap<Element,
			ClassInstantiation> preparedInstantiations, 
			HashSet<Element> preCollectedMediators, 
			boolean reCollectMediatorsIfEmpty){
		
		ClassInstantiation newInstantiation = null;
		
		if (preparedInstantiations != null) {
			newInstantiation = preparedInstantiations.get(model);
		}

		// create only if it does not exists already
		if ( newInstantiation == null) {
			
			ClassInstantiation ci_model = new ClassInstantiation((Class) model, true, false, preCollectedMediators, reCollectMediatorsIfEmpty);
			// instantiate -> create a tree
			ci_model.createTree();
			// collect bindings data that is specific to this tree
			ci_model.collectBindingsDataFromUmlModel();
			
			newInstantiation = ci_model;
		}

		return newInstantiation;
	}
	
	
	
	public static Model getCommonRootModel(HashSet<Element> selectedModels){
		/*
		 * TODO: Issue: If models are in different root Models which are at the same hierarchy?
		 */
		Model rootModel = null;
		for (Element selectedModel : selectedModels) {
			Model newRootCandidate = selectedModel.getModel();
			if (newRootCandidate  != null) {
				
				// new root is contained in the known root
				if (rootModel != null && rootModel.getQualifiedName().contains(newRootCandidate.getQualifiedName()) ) {
					// let the known model be ...
				}
				else if (rootModel != null && newRootCandidate.getQualifiedName().contains(rootModel.getQualifiedName()) ) {
					rootModel = newRootCandidate;
				}
				// very first time
				else if (rootModel == null) {
					rootModel = newRootCandidate;
				}
				else {
					//TODO: here the models are in different root Models which are at the same hierarchy?
					// which to take? 
					System.err.println("PROBLEM detecting common root model. The root models are at the same hierarchy levels.");
				}
			}
		}
		return rootModel;
	}
	
	
	
	
	
	public static HashSet<Element> getElementsFromAllInstantiations(HashMap<Element, ClassInstantiation> modelToItsInstantiation){
		HashSet<Element> allElements = new HashSet<Element>();
		HashSet<ClassInstantiation> classInstantiations = new HashSet<ClassInstantiation>();
		for (Element model: modelToItsInstantiation.keySet()) {
			classInstantiations.add(modelToItsInstantiation.get(model));
		}
		
		for (ClassInstantiation classInstantiation : classInstantiations) {
			allElements.addAll(classInstantiation.getElementToInstantiationTreeObjects().keySet());
		}
		return allElements;
	}
	
	public static HashMap<Element,HashSet<TreeObject>> getElementToInstantiationTreeObjects(HashMap<Element, ClassInstantiation> modelToItsInstantiation){
		 HashMap<Element,HashSet<TreeObject>> allElements = new  HashMap<Element,HashSet<TreeObject>>();
		HashSet<ClassInstantiation> classInstantiations = new HashSet<ClassInstantiation>();
		for (Element model: modelToItsInstantiation.keySet()) {
			classInstantiations.add(modelToItsInstantiation.get(model));
		}
		
		for (ClassInstantiation classInstantiation : classInstantiations) {
			allElements.putAll(classInstantiation.getElementToInstantiationTreeObjects());
		}
		return allElements;
	}
	
	public static HashSet<TreeObject> getAllTreeObjectsFromInstantiation(ClassInstantiation classInstantiation){
		HashSet<TreeObject> allTreeObjects = new HashSet<TreeObject>();
		
		for (Element element : classInstantiation.getElementToInstantiationTreeObjects().keySet()) {
			HashSet<TreeObject> treeObjects = classInstantiation.getElementToInstantiationTreeObjects().get(element);
			if (treeObjects != null && treeObjects.size() > 0 ) {
				allTreeObjects.addAll(treeObjects);
			}
		}
		return allTreeObjects;
	}
	
	
	
	/*
	 * Simulation files handling
	 */
	
	public static String getOMCSimulationResultsFileName(NamedElement model){
		return getModelQName((NamedElement) model) + "_res.mat";
	}
	
	public static String getOMCSimulationResultsFileName(String modelName){
		return StringUtls.replaceSpecCharExceptThis( modelName, "::").replaceAll("::", ".") + "_res.mat";
	}
	
	public static String getModelQName(NamedElement model) {
		return StringUtls.replaceSpecCharExceptThis( model.getQualifiedName(), "::").replaceAll("::", ".");
	}
	
	public static HashMap<Element,File> findSimulationFiles(HashSet<Element> generatedModels, String fileExtension, String folderAbsolutePath){
		
		HashMap<Element,File> foundFiles = new HashMap<Element,File>();
		HashSet<Element> ambiguousCases = new HashSet<Element>();
		if (folderAbsolutePath == null || fileExtension == null) { return null; }
		
		File folder = new File(folderAbsolutePath);
		
		if (folder.exists() && folder.isDirectory()) {
			for (Element element : generatedModels) {
				if (element instanceof NamedElement) {
					NamedElement model = (NamedElement) element;
					
					for (File file : folder.listFiles()) {
						String fileName = file.getName();
						String modelName = StringUtls.replaceSpecChar(model.getName());
						if (fileName.contains(fileExtension) && fileName.contains(modelName)) {
							
							/*
							 * We collect conflict case when for one model multiple files matches.  
							 */
							if (foundFiles.get(element) != null) {
								ambiguousCases.add(element);
							}
							foundFiles.put(element, file);
						}
					}
				}
			}
		}
		
		/*
		 * Remove all ambiguous cases. User should select the files manually.
		 */
		for (Element element : ambiguousCases) {
			foundFiles.remove(element);
		}
		
		// if no files were found return null
		if (foundFiles.size() == 0) { return null;}
		
		// return the files found
		return foundFiles;
	}
	
	public static HashMap<String,File> findSimulationFile(String qName, String fileExtension, String folderAbsolutePath){
		
		HashMap<String, File> foundFiles = new HashMap<String,File>();
		HashSet<String> ambiguousCases = new HashSet<String>();
		
		if (folderAbsolutePath == null || fileExtension == null) { return null; }
		
		File folder = new File(folderAbsolutePath);
		
		if (folder.exists() && folder.isDirectory()) {
			for (File file : folder.listFiles()) {
				String fileName = file.getName();
				String modelName = StringUtls.replaceSpecChar(qName);
				if (fileName.contains(fileExtension) && fileName.contains(modelName)) {
					
					/*
					 * We collect conflict case when for one model multiple files matches.  
					 */
					if (foundFiles.get(qName) != null) {
						ambiguousCases.add(qName);
					}
					foundFiles.put(qName, file);
				}
			}
		}
		
		/*
		 * Remove all ambiguous cases. User should select the files manually.
		 */
		for (String name: ambiguousCases) {
			foundFiles.remove(name);
		}
		
		// if no files were found return null
		if (foundFiles.size() == 0) { return null;}
		
		// return the files found
		return foundFiles;
	}
	
	
	
	
	public static Shell getShell(){
		Shell shell = null;
		IWorkbench wb = PlatformUI.getWorkbench();
		if (wb != null) {
			IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
			if (win != null) {
				shell = win.getShell();
			}
		}
		if (shell == null) {
			shell = new Shell();
		}
		return shell;
	}
	
	
	/* 
	 * Modelica synchronization 
	 */
	public static Long ModelicaModelSyncStartTime; 	// time when the sync. started 
	public static Long ModelicaModelSyncStopTime;	// time when the sync. was finished
}
