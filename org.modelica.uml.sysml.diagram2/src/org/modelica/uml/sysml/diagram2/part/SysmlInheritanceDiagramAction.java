package org.modelica.uml.sysml.diagram2.part;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
import java.io.File;
import java.io.FileFilter;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.uml2.uml.UMLPackage;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.ListElement;
import org.modelica.mdt.core.ModelicaParserException;
import org.modelica.mdt.core.compiler.ModelicaParser;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.core.compiler.ElementInfo;

import org.modelica.mdt.internal.core.InnerClass;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.*;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;

//import org.eclipse.gmf.runtime.diagram.ui.actions.;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.diagram.ui.requests.ChangePropertyValueRequest;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.editor.FileDiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.ui.properties.commands.SetModelPropertyValueCommand;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.properties.extended.PropertySource;

import org.eclipse.gef.commands.Command;

import org.eclipse.draw2d.geometry.Point;

import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;
import org.modelica.uml.sysml.diagram2.providers.SysmlStructuralFeatureParser;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEditPart.ClassFigure;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassName2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaEquationPropertyEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPropertyEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParametersEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty3EditPart;

import org.modelica.uml.sysml.diagram2.util.ClassInheritanceDialog;
import org.modelica.uml.sysml.diagram2.util.ModelicaClassLocation;
import org.modelica.uml.sysml.diagram2.util.ModelicaElementsUtil;


import org.modelica.uml.sysml.impl.ModelicaClassImpl;

import org.modelica.uml.sysml.AccessKind;
import org.modelica.uml.sysml.SysmlPackage;

/**
 * @author vasba
 *
 */
public class SysmlInheritanceDiagramAction extends Action{

	private IEditorPart activeEditor;
//	object name stores the name of the class or the path of the file
//	depending on the action type
	private String label, path;
	private DiagramEditPart editPart = null;
	private List<CustomNode> createdNodes;
	private int actionType;
	private Point startPoint = new Point(0,0);


	public static final int FROM_FILE = 0;
	public static final int FROM_CLASS = 1;


	public SysmlInheritanceDiagramAction(String label,int actionType){
		super(label);
		this.label = label;
		this.actionType = actionType;
		createdNodes = new ArrayList<CustomNode>();
	}

	public void runWithEvent(Event event) {
		Display display = event.display; 
		String[] result = {""};
		ICompilerResult  compilerResult;
		createdNodes = new ArrayList<CustomNode>();
		startPoint = new Point(0,0);
		boolean showInConsole = true;

		if(actionType == FROM_CLASS){
			try{
				ModelicaClassLocation[] classes = openClassesDialog(display);
				generateInhDiagramFromClassLocations(classes);
//				generateInheritanceDiagramFromFile(classes);
//				String command = "loadModel(" + classes + ")";
//				compilerResult = CompilerProxy.sendExpression(command,showInConsole);
//				result = compilerResult.getResult();
//				if(Boolean.parseBoolean(result[0].trim()) == true){

//				result =new String[]{"Modelica"}; 
//				}
			}
			catch(Exception e){

			}
		}

		if (actionType == FROM_FILE){
			String[] fileNames = openFileDialog(display);
			generateInheritanceDiagramFromFile(fileNames);
		}

		super.runWithEvent(event);
	}


	public String[] openFileDialog(Display display){

		Shell shell = display.getActiveShell();
		shell.setActive();

		FileDialog dialog = new FileDialog(shell);
		dialog.setFilterExtensions(new String[]{"*.mo"});

		dialog.open();
		String path = dialog.getFilterPath();
		String fileName = dialog.getFileName();
		return new String[]{path,fileName};
	}

	public void setActiveEditor(IEditorPart part) {
		activeEditor = part;

		if(activeEditor instanceof SysmlDiagramEditor){
			editPart = ((SysmlDiagramEditor) activeEditor).getDiagramEditPart();

		}
	}


	public ModelicaClassLocation[] openClassesDialog(Display display) {
		Shell shell = display.getActiveShell();
		shell.setActive();

		ClassInheritanceDialog dialog = new ClassInheritanceDialog(shell,SWT.PRIMARY_MODAL);
		ModelicaClassLocation[] classes = dialog.open();

		return classes;
	}


	public static final String convertPath(String path){
		String newPath = "";
		for(int i = 0; i < path.length(); i++){
			if(path.charAt(i) == '\\')
				newPath += '/';
			else newPath += path.charAt(i);
		}
		return newPath;
	}


	protected void generateInheritanceDiagramFromFile(String[] fileNames){
//		String[] fileClasses = {""};
		String path = fileNames[0];
		String fileName = fileNames[1];

		path = convertPath(path);
		this.path = path;
		String command = "loadFile(\"" + path + "/" + fileName + "\")";
		fileName = fileName.replaceAll(".mo", "");
		loadFolderModels(path);


		try{
			ICompilerResult compilerResult;
			compilerResult = CompilerProxy.sendExpression(command, true);
			String[] result = compilerResult.getResult();

			if(Boolean.parseBoolean(result[0].trim()) != true){
				/*	command = "getClassNames(" + fileName + ")";
				compilerResult = CompilerProxy.sendExpression(command, true);
				fileClasses = compilerResult.getResult();*/
//				return if file load failed
				return;
			}
		}
		catch(Exception e){

		}

//		fileClasses = {fileName};
		String[] fileClasses = {fileName};
		createInheritanceNodesList(fileClasses);
		createInheritanceConnections(createdNodes);

	}

	protected void generateInheritanceDiagramFromClasses(String[] classNames){

		createInheritanceNodesList(classNames);
		createInheritanceConnections(createdNodes);

	}

	protected void generateInhDiagramFromClassLocations(ModelicaClassLocation[] classes) {
		for (int i = 0; i<classes.length;i++) {
			String path = classes[i].getFilePath();
			String className = classes[i].getClassName();

			path = convertPath(path);
			this.path = path;
//			String command = "loadFile(\"" + path + "\")";


			int lastPathDelim = path.lastIndexOf("/");
			String fileName = path.substring(lastPathDelim + 1, path.length());
			fileName = fileName.replaceAll(".mo", "");

			String filePath = path.substring(0, lastPathDelim);
			String command = "cd(\"" + filePath + "\")";
			loadFolderModels(filePath);


			try{
				ICompilerResult compilerResult;
				compilerResult = CompilerProxy.sendExpression(command, true);
				String[] result = compilerResult.getResult();

				if(Boolean.parseBoolean(result[0].trim()) != true){
					/*	command = "getClassNames(" + fileName + ")";
				compilerResult = CompilerProxy.sendExpression(command, true);
				fileClasses = compilerResult.getResult();*/
//					return if file load failed
//					return;
				}
			}
			catch(Exception e){

			}

//			fileClasses = {fileName};
			String[] fileClasses = {fileName+ "." +className};
			createInheritanceNodesList(fileClasses);
		}
		createInheritanceConnections(createdNodes);
	}

	protected List<CustomNode> createInheritanceNodesList(String[] classes){
		if (classes == null || classes.length == 0) return new ArrayList<CustomNode>();
		List<CustomNode> sessionCreatedNodes = new ArrayList<CustomNode>();
		CustomNode createdNode = null;
		ModelicaClassEditPart createdEditPart = null;

		String classType = "";
		String[] result = {""};
		String command = "";


		for(int i=0; i<classes.length;i++){
//			if (createdNodes.)
			loadImports(classes[i]);
			command = "getClassRestriction("+ classes[i] +")";
			try{
				ICompilerResult compilerResult;
				compilerResult = CompilerProxy.sendExpression(command, true);
				result = compilerResult.getResult();
			}
			catch(Exception e){

			}

			if(result != null)
				classType = result[0].trim();
			if(!classType.isEmpty())
				classType = classType.substring(1, classType.length()-1);

			
			createdEditPart = (ModelicaClassEditPart)createEmptyNodeEditPart(classType);

			if(createdEditPart != null){
				ModelicaElementsUtil.handleclass(createdEditPart, classes[i]);
				createdNode = new CustomNode(classType.trim(),classes[i],createdEditPart);
				createdNodes.add(createdNode);
				sessionCreatedNodes.add(createdNode);
			}

			command = "getClassNames("+ classes[i]+ ")";

			String retval="";

			try{
				retval = CompilerProxy.sendExpression(command, true).getFirstResult();
			}
			catch(Exception e){

			}

			org.modelica.mdt.core.List list = null;
			try{
				try
				{
					list = ModelicaParser.parseList(retval);
				}
				catch(ModelicaParserException e)
				{
					throw new UnexpectedReplyException("Unable to parse list: " 
							+ e.getMessage());
				}
			}
			catch(Exception e){

			}

			if(list != null){
				result = new String[list.size()];

				for(int j =0;j<list.size();j++){

					result[j] = classes[i] + "." + list.elementAt(j).toString();
					String tmpResult = result[j];
				}
				sessionCreatedNodes.addAll(createInheritanceNodesList(result));
			}

		}
		return sessionCreatedNodes;
	}	


	protected void createInheritanceConnections(List<CustomNode> nodes){
		List<CustomNode> sessionCreatedNodes = new ArrayList<CustomNode>();
		ArrayList list = new ArrayList(nodes);
		Iterator listIterator = list.iterator();
		CustomNode sourceNode = null;
		int inheritanceCount;
		String omcCommand = "";
		String omcResult = "";

		while(listIterator.hasNext()){
			sourceNode = (CustomNode)listIterator.next();
			omcCommand ="getInheritanceCount("+sourceNode.getName()+")";
			if (sourceNode.getName().equals("Rotational.Inertia")) {
				int i = 5;
			}

			try{
				omcResult = CompilerProxy.sendExpression(omcCommand, true).getFirstResult();			
			}
			catch(Exception e){

			}

			inheritanceCount = Integer.parseInt(omcResult.trim());

			for(int i=0;i<inheritanceCount;i++){				
				omcCommand = "getNthInheritedClass("+ sourceNode.getName()+","+(i+1)+")";

				try{
					omcResult = CompilerProxy.sendExpression(omcCommand, true).getFirstResult();					
				}
				catch(Exception e){

				}

				omcResult = omcResult.trim();
				//omcResult = omcResult.substring(1, omcResult.length()-1);

				EditPart targetEditPart = getEditPart(omcResult);
				if(targetEditPart == null){
					boolean validInheritance = checkAndLoad(omcResult);
					
					String modelica = "Modelica";
					if(!omcResult.substring(0, modelica.length()).equals(modelica) && !validInheritance){
						omcResult = modelica + "." + omcResult;
						//Try again with the new path.					
						validInheritance = checkAndLoad(omcResult);
					} 					
					
					if (validInheritance){

						omcCommand = "getClassRestriction("+ omcResult +")";
						String[] result = {""};
						try{
							ICompilerResult compilerResult;
							compilerResult = CompilerProxy.sendExpression(omcCommand, true);
							result = compilerResult.getResult();
						}
						catch(Exception e){

						}
						String classType = "";
						if(result != null)
							classType = result[0].trim();
						if(!classType.isEmpty())
							classType = classType.substring(1, classType.length()-1);



						targetEditPart = (ModelicaClassEditPart)createEmptyNodeEditPart(classType);

						if(targetEditPart != null){

							ModelicaElementsUtil.handleclass((ModelicaClassEditPart)targetEditPart, omcResult);

							CustomNode createdNode = new CustomNode(classType.trim(),omcResult,targetEditPart);
							sessionCreatedNodes.add(createdNode);
							createdNodes.add(createdNode);
						}
					}

				}
				
				if (sourceNode.getNodeEditPart() != null & targetEditPart != null){
					createInheritanceConnectionEditPart(sourceNode.getNodeEditPart(),targetEditPart);
					
				}
			}
		}
		
		if (actionType == FROM_CLASS && sessionCreatedNodes.size() > 0) {						
			createInheritanceConnections(sessionCreatedNodes);
		}
	}


	protected void createInheritanceConnectionEditPart(EditPart source, EditPart target){
		//Create a generalization link if dot created yet.
		if (source instanceof AbstractGraphicalEditPart) {
			AbstractGraphicalEditPart nSource = (AbstractGraphicalEditPart)source;
			for (Object element : nSource.getTargetConnections()){
				if (element instanceof AbstractConnectionEditPart) {
					AbstractConnectionEditPart connection = (AbstractConnectionEditPart)element;
					if (connection.getTarget() == target)
						return;
				}
			}
			
			IElementType resourceElementType = SysmlElementTypes.Generalization_3001;

			CreateConnectionViewAndElementRequest connectionRequest =
				new CreateConnectionViewAndElementRequest(
						resourceElementType,
						((IHintedType)resourceElementType).getSemanticHint(),
						SysmlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

			Command command = connectionRequest.getCreateCommand(connectionRequest,source, target);

			if (command == null || !(command.canExecute())) {
				// Action enablement criteria expected to prevent this
				throw new IllegalArgumentException("Command for '" 
						+ " new generalization link" + "' is not executable."); 

			}
			DiagramCommandStack commandStack = editPart.getDiagramEditDomain()
			.getDiagramCommandStack();
			commandStack.execute(command);
		}
	}

	protected EditPart createEmptyNodeEditPart(String classType){
		if(editPart != null){
			IElementType nodeType = null;
			if(classType.equals("class"))
				nodeType = SysmlElementTypes.ModelicaClass_1001;			
			else if(classType.equals("model"))
				nodeType = SysmlElementTypes.ModelicaModel_1001;
			else if(classType.equals("block"))
				nodeType = SysmlElementTypes.ModelicaBlock_1001;
			else if(classType.equals("function"))
				nodeType = SysmlElementTypes.ModelicaFunction_1001;
			else if(classType.equals("record"))
				nodeType = SysmlElementTypes.ModelicaRecord_1001;
			else if(classType.equals("connector"))
				nodeType = SysmlElementTypes.ModelicaConnector_1001;
			/*else if (classType.equals("package"))
			nodeType = SysmlElementTypes.ModelicaClass_1001;*/
			if(nodeType == null)
				return null;
			else
				return createEmptyNodeEditPart(nodeType);
		}
		else return null;
	}

	protected EditPart createEmptyNodeEditPart(IElementType resourceElementType){

		if(editPart != null && resourceElementType != null){

//			Get the command to create the new element and its view
			CreateViewRequest createRequest = CreateViewRequestFactory
			.getCreateShapeRequest(resourceElementType, 
					SysmlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

			//Set the location of the node in the diagram
			if(startPoint.x<200)
				startPoint.x += 100;
			else{
				startPoint.y += 100;
				startPoint.x = 0;

			}

			createRequest.setLocation(startPoint);


			Command command = editPart.getCommand(createRequest);

			if (command == null || !(command.canExecute())) {
				// Action enablement criteria expected to prevent this
				throw new IllegalArgumentException("Command for '" 
						+ " new class diagram" + "' is not executable."); 

			}

			//Get the children list before creation
			List oldChildrenList = new LinkedList();
			oldChildrenList.addAll(editPart.getChildren());

			// Create the new element
			DiagramCommandStack commandStack = editPart.getDiagramEditDomain()
			.getDiagramCommandStack();
			commandStack.execute(command);
			//Get the children list after creation 
			//and remove the old ones
			List newChildrenList = new LinkedList();
			newChildrenList.addAll(editPart.getChildren());
			newChildrenList.removeAll(oldChildrenList);

			//assumes the remaining children in the list are 1
			//It should be so if no threads intervene
			//in the future
			return (EditPart)newChildrenList.get(0);

		}
		else return editPart;
	}

	protected EditPart getEditPart(String name){
		Iterator listIterator = createdNodes.iterator();

		while(listIterator.hasNext()){
			CustomNode tempNode = (CustomNode)listIterator.next();
			if(tempNode.getName().equals(name)){
				return tempNode.getNodeEditPart();
			}
		}
		return null;
	}

	/**
	 * Checks if a class is loaded, if not tries to load it.
	 * Returns true on success.
	 * @param className
	 * @return
	 */
	private boolean checkAndLoad(String className){
		String omcCommand = "";
		String[] result = {""};
		omcCommand = "existClass("+ className +")";
		try{
			ICompilerResult compilerResult = CompilerProxy.sendExpression(omcCommand, true);
			result = compilerResult.getResult();
			if(Boolean.parseBoolean(result[0].trim()) == true){
				return true; 
			}
		}
		catch(Exception e){

		}

		

		omcCommand = "loadModel(" + className + ")";
		try{
			ICompilerResult compilerResult = CompilerProxy.sendExpression(omcCommand, true);
			result = compilerResult.getResult();
			if(Boolean.parseBoolean(result[0].trim()) == true){
				return true; 
			}
		}
		catch(Exception e){

		}
		return false;
	}


	/**
	 * Changes the working directory to the given path and loads all the
	 * modelica files it finds in the given folder.
	 * @param path
	 */
	/**
	 * @param path
	 */
	private void loadFolderModels(String path){
		String omcCommand = "";
		String[] result = {""};
		ICompilerResult compilerResult;
		omcCommand = "cd(\""+ path + "\")";
		try{
			compilerResult = CompilerProxy.sendExpression(omcCommand, true);
			result = compilerResult.getResult();
			String trimed = result[0].trim();
			trimed = trimed.substring(1, trimed.length()-1);
			trimed = convertPath(trimed);
//			String convertedpath = convertPath(path); 
			if(!trimed.equals(path)){
				return; 
			}
		}
		catch(Exception e){
		}

		File folder = new File(path);
		File[] files;
		if (folder.isDirectory())
			files = folder.listFiles(
					new FileFilter() {
						public boolean accept(File pathname){
							String extension = ".mo";
							String name = pathname.getName();
							int begIndex = name.length() - extension.length();
							int endIndex = name.length();
							String realExtension = name.substring(begIndex, endIndex);
							if (realExtension.equals(extension))
								return true;
							else return false;
						}
					});
		else return;

		for (int i = 0; i<files.length;i++){
			String filePath = files[i].getPath();
			filePath = convertPath(filePath);

			omcCommand = "loadFile(\"" + filePath + "\")";
			try{
				compilerResult = CompilerProxy.sendExpression(omcCommand, true);
				result = compilerResult.getResult();
				String trimed = result[0].trim();
				/*	if(Boolean.parseBoolean(result[0].trim()) == true){
					return true; 
				}*/
			}
			catch(Exception e){
			}
		}

	
	}

	
	/**
	 * Loads the models specified by the import element info.
	 * @param objectName
	 */
	public void loadImports(String objectName) {
		LinkedList<ElementInfo> classInfo = null;
		
		try{
			classInfo= (LinkedList<ElementInfo>)CompilerProxy.getElements(objectName);
		}
		catch(Exception e){
			
		}		

		if (classInfo != null)
			for (ElementInfo element : classInfo)
			{
				String type = element.getElementType();
				String path = element.getPath();
				if (type.equals("import")){
					boolean loaded = checkAndLoad(path);
					int i = 5;
				}

			}
	}

	protected class CustomNode{
		private String name;
		private String type;
		private EditPart nodeEditPart;

		CustomNode(String type, String name,EditPart node){
			this.name=name;
			this.type = type;
			nodeEditPart = node;

		}

		public void setType(String type){
			this.type = type;
		}

		public String getType(){
			return type;
		}

		public void setName(String name){
			this.name = name;
		}

		public String getName(){
			return name;
		}

		public void setEditPart(EditPart editPart){
			this.nodeEditPart = editPart;
		}

		public EditPart getNodeEditPart(){
			return nodeEditPart;
		}
	}
}
