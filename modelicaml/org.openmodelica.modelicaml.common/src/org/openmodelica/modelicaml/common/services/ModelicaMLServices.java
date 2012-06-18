package org.openmodelica.modelicaml.common.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;

public class ModelicaMLServices {

	
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
	
	public static String getRequirementID(Element req){
		if (req instanceof NamedElement) {
			Stereotype s = ((NamedElement)req).getAppliedStereotype(Constants.stereotypeQName_Requirement);
			if (s != null) {
				Object o =((NamedElement)req).getValue(s, Constants.propertyName_id);
				return o.toString();
			}
		}
		return "";
	}
	
	public static String getRequirementText(Element req){
		if (req instanceof NamedElement) {
			Stereotype s = ((NamedElement)req).getAppliedStereotype(Constants.stereotypeQName_Requirement);
			if (s != null) {
				Object o =((NamedElement)req).getValue(s, Constants.propertyName_text);
				return o.toString();
			}
		}
		return "";
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
				if (arg0 instanceof NamedElement) {
					return ((NamedElement)arg0).getName().compareToIgnoreCase( ((NamedElement)arg1).getName());
				}
				return arg0.toString().compareToIgnoreCase(arg1.toString());
			}
		};
		Collections.sort(listSorted, c);
		
		return listSorted;
	}
	
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
	
	
//	public static List<String> getFilesToLoad(String folderAbsolutePath){
//		
//		// list of files to be loaded
//		List<String> list = new ArrayList<String>();
//
//		// add "/" at the end if there is none
//		if (!folderAbsolutePath.trim().endsWith("/")) {
//			folderAbsolutePath = folderAbsolutePath + "/";
//		}
//		
//		File folder = new File(folderAbsolutePath);
//		if (folder.isDirectory()) {
//			/*
//			 * - get all .mo files add these to the list
//			 * - get all sub.folders and add sub-folder-name/package.mo to the list
//			 */
//			IFileSystem fileSystem = EFS.getLocalFileSystem();
//			IFileStore folderStore = fileSystem.getStore(URI.create(folderAbsolutePath));
//			
//			String[] children;
//			try {
//				children = folderStore.childNames(EFS.NONE, null);
//				for (int i = 0; i < children.length; i++) {
//					String child = children[i];
//					
//					File checkChild = new File(folderAbsolutePath + child);
//					
//					if (child.endsWith(".mo")) { // if it is a .mo file
//						list.add(folderAbsolutePath + child);
//					}
//					else if (checkChild.isDirectory()) { // if it is a folder
//						list.add(folderAbsolutePath + child + "/package.mo");
//					}
//					else { // any other files 
//						
//					}
//				}
//			} catch (CoreException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return list;
//	}
	
	
	public static boolean containsOMCErrorMessage(String msg){
		if (	msg.contains("Error: ")
				|| msg.contains("rror occured")) {
			// TODO: create a full list of possible OMC error messages
			return true;
		}
		if (msg.trim().equals("false")) {
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
				if (file.isFile() && !file.getName().equals("package.mo")) {
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
	
	
	private static String formatPath(String path){
		while(path.contains("\\")){
			path = path.replace('\\', '/');
		}
		path = path.replaceAll("%20", " ");
		return path;
	}
	
	
	public static IStatus deleteFiles(List<String> filesToBeDeleted, IProgressMonitor monitor, String message){
		
		if (monitor.isCanceled()){
			return Status.CANCEL_STATUS;
		}

		IFileSystem fileSystem = EFS.getLocalFileSystem();
		for (String filePath : filesToBeDeleted) {
			
			monitor.subTask(message + filePath );
			
			IFileStore fileToBeDeleted = fileSystem.getStore(java.net.URI.create("file:/"  + filePath ));
			try {
				fileToBeDeleted.delete(EFS.NONE, monitor);
			} catch (CoreException e) {
//				e.printStackTrace();
			}
			
			if (monitor.isCanceled()){
				return Status.CANCEL_STATUS;
			}
			
		}
		return Status.OK_STATUS;
	}
	
	
//	public static IStatus deleteOldSimulationFiles(String modelQName, String directory, IProgressMonitor monitor){
//		
//		if (monitor.isCanceled()){
//			return Status.CANCEL_STATUS;
//		}
//		
//		IFileSystem fileSystem = EFS.getLocalFileSystem();
//		monitor.subTask("Deleting files from OMC tmp folder for '" + modelQName + "'");
//		IFileStore oldExeFile = fileSystem.getStore(java.net.URI.create("file:/" + directory + "/" + modelQName + ".exe"));
//		IFileStore oldXMLInitFile = fileSystem.getStore(java.net.URI.create("file:/" + directory + "/" + modelQName + "_init.xml"));
//		IFileStore oldPltFile = fileSystem.getStore(java.net.URI.create("file:/" + directory + "/" + modelQName + "_res.plt"));
//		IFileStore oldPltXMLFile = fileSystem.getStore(java.net.URI.create("file:/" + directory + "/" + modelQName + "_res.xml"));
//		
//		try {
//			oldExeFile.delete(EFS.NONE, monitor);
//			oldXMLInitFile.delete(EFS.NONE, monitor);
//			oldPltFile.delete(EFS.NONE, monitor);
//			oldPltXMLFile.delete(EFS.NONE, monitor);
//			
//			return Status.OK_STATUS;
//			
//		} catch (CoreException e) {
////			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	public static IStatus deleteFile(String path, IProgressMonitor monitor){
//		
//		if (monitor.isCanceled()){
//			return Status.CANCEL_STATUS;
//		}
//		
//		IFileSystem fileSystem = EFS.getLocalFileSystem();
//		monitor.subTask("Deleting file '" + path + "'");
//		IFileStore existingFile = fileSystem.getStore(java.net.URI.create("file:/" + path));
//		
//		try {
//			existingFile.delete(EFS.NONE, monitor);
//			return Status.OK_STATUS;
//			
//		} catch (CoreException e) {
////			e.printStackTrace();
//			return null;
//		}
//	}
	
	
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
					 * "In general, a Writer sends its output immediately to the underlying character or byte stream. Unless prompt output is required, it is advisable 
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
	
}
