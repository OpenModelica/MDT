package org.modelica.uml.sysml.diagram2.part;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.core.ModelicaParserException;
import org.modelica.mdt.core.compiler.ModelicaParser;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.eclipse.gef.EditPart;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
//import org.eclipse.gmf.runtime.diagram.ui.actions.;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.editor.FileDiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;

import org.eclipse.gef.commands.Command;

import org.eclipse.draw2d.geometry.Point;

import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

public class SysmlInheritanceDiagramAction extends Action{

	private IEditorPart activeEditor;
	private String label;
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
		createdNodes = new LinkedList<CustomNode>();
	}

	public void run(){

		String[] result = {""};
		ICompilerResult  compilerResult;

		boolean showInConsole = true;
		try{

			String command = "loadModel(Modelica)";
			compilerResult = CompilerProxy.sendExpression(command,showInConsole);
			result = compilerResult.getResult();
			if(Boolean.parseBoolean(result[0].trim()) == true){
				
				result =new String[]{"Modelica"}; 
			}
		}
		catch(Exception e){

		}
		
		if(actionType == FROM_CLASS)
			generateInheritanceDiagramFromClasses(result);
			

	}

	public void setActiveEditor(IEditorPart part) {
		activeEditor = part;

		if(activeEditor instanceof FileDiagramEditor)
			editPart = ((FileDiagramEditor) activeEditor).getDiagramEditPart();
	}

	public void generateInheritanceDiagramFromFile(String fileName){
		String[] fileClasses = {""};
		String command = "loadFileInteractive(\""+ fileName+"\")";



		try{
			ICompilerResult compilerResult;
			compilerResult = CompilerProxy.sendExpression(command, true);
			fileClasses = compilerResult.getResult();
		}
		catch(Exception e){

		}

		createInheritanceNodesList(fileClasses);
		createInheritanceConnections();

	}



	public void generateInheritanceDiagramFromClasses(String[] classNames){
		
		
		createInheritanceNodesList(classNames);
		createInheritanceConnections();

	}

	protected void createInheritanceNodesList(String[] classes){

		CustomNode createdNode = null;
		EditPart createdEditPart = null;

		String classType = "";
		String[] result = {""};
		String command = "";


		for(int i=0; i<classes.length;i++){
			command = "getClassRestriction("+ classes[i] +")";
			try{
				ICompilerResult compilerResult;
				compilerResult = CompilerProxy.sendExpression(command, true);
				result = compilerResult.getResult();
			}
			catch(Exception e){

			}

			classType = result[0].trim();
			classType = classType.substring(1, classType.length()-1);
			
			createdEditPart = createEmptyNodeEditPart(classType);
			createdNode = new CustomNode(classType.trim(),classes[i],createdEditPart);
			createdNodes.add(createdNode);

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
		
			result = new String[list.size()];
			
			for(int j =0;j<list.size();j++){
				
				result[j] = classes[i] + "." + list.elementAt(j).toString();
				String tmpResult = result[j];
			}
			createInheritanceNodesList(result);
		}
		
	}

	protected void createInheritanceConnections(){
		
		Iterator listIterator = createdNodes.iterator();
		CustomNode sourceNode = null;
		int inheritanceCount;
		String omcCommand = "";
		String omcResult = "";
		
		while(listIterator.hasNext()){
			sourceNode = (CustomNode)listIterator.next();
			omcCommand ="getInheritanceCount("+sourceNode.getName()+")";
			
			try{
				omcResult = CompilerProxy.sendExpression(omcCommand, true).getFirstResult();			
			}
			catch(Exception e){

			}
			
			inheritanceCount = Integer.parseInt(omcResult.trim());
			
			for(int i=0;i<inheritanceCount;i++){
				omcCommand = "getNthInheritedClass("+ sourceNode.getName()+","+i+")";
				
				try{
					omcResult = CompilerProxy.sendExpression(omcCommand, true).getFirstResult();					
				}
				catch(Exception e){

				}

				EditPart targetEditPart = getEditPart(omcResult);
				
				createInheritanceConnectionEditPart(sourceNode.getNodeEditPart(),targetEditPart);
				
			}

			
			
		}
		
	}
	
	protected void createInheritanceConnectionEditPart(EditPart source, EditPart target){
		
		//Create a generalization link
		IElementType resourceElementType = SysmlElementTypes.Generalization_3001;
		
		CreateConnectionViewAndElementRequest connectionRequest =
			new CreateConnectionViewAndElementRequest(
					resourceElementType,
					((IHintedType)resourceElementType).getSemanticHint(),
					SysmlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		
		Command command = connectionRequest.getCreateCommand(connectionRequest,
				(EditPart)editPart.getChildren().get(0), 
				(EditPart)editPart.getChildren().get(1));
		
		if (command == null || !(command.canExecute())) {
			// Action enablement criteria expected to prevent this
			throw new IllegalArgumentException("Command for '" 
					+ " new generalization link" + "' is not executable."); 

		}
		DiagramCommandStack commandStack = editPart.getDiagramEditDomain()
		.getDiagramCommandStack();
		commandStack.execute(command);
		
	}
	
	protected EditPart createEmptyNodeEditPart(String classType){
		if(editPart != null){
			IElementType nodeType = null;
			if(classType.equals("class"))
				nodeType = SysmlElementTypes.ModelicaClass_1001;
			else if (classType.equals("package"))
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
			if(startPoint.y<200)
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
			newChildrenList = editPart.getChildren();;
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
			if(tempNode.getName()== name){
				return tempNode.getNodeEditPart();
			}
		}
		return null;
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
