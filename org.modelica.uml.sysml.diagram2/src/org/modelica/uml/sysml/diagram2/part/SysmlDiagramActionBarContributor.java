package org.modelica.uml.sysml.diagram2.part;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.editor.FileDiagramEditor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.SubMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;


import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.ICompilerResult;

/**
 * @generated
 */
public class SysmlDiagramActionBarContributor extends
		DiagramActionBarContributor {

	/**
	 * @generated NOT
	 */
	private EditorAction MLmenu;
	private SysmlInheritanceDiagramAction inheritanceDiagramHandler;
	private EditorAction submenu2Handler;
	private RetargetAction inheritanceDiagram;
	private RetargetAction submenu2;
	//private Action selectAllAction;
	
	private String id1 = "Inheritance Diagram";
	private String id2 = "sub2";
	//private SysmlDiagramAction diagramAction;
	
	
	
	class EditorAction extends Action{
		
		private Shell shell;
		
		private IEditorPart activeEditor;
		private String label;
		
		
		public EditorAction(String label){
			super(label);
			this.label = label;
			shell = new Shell(SWT.RESIZE);
			
			
		}
		
		public void run(){
			
			/*java.util.List list =  diagramAction.returnOperationSet();
			int diagramSelectioSize =list.size();
			String diagramSelectionElements ="";
			for(int i=0;i<diagramSelectioSize;i++){
				diagramSelectionElements = diagramSelectionElements + "Type " + i +" is "
				+ list.get(i).
				getClass().toString()+"\n";
			}
			
			selectAllAction.run();*/
			
			
			
			FileDiagramEditor editor = (FileDiagramEditor) activeEditor;
			DiagramEditPart editPart = editor.getDiagramEditPart();
			/*DiagramGraphicalViewer graphicalViewer = (DiagramGraphicalViewer)editor.getDiagramGraphicalViewer();
			EditPart graphicalEditPart = graphicalViewer.getContents();
			String graphicalEditPartName = graphicalEditPart.getClass().toString();
			
			SimpleRootEditPart rootEditPart = (SimpleRootEditPart)graphicalViewer.getRootEditPart();
			String rootName = rootEditPart.getClass().toString();
			int rootEditPartChildrenSize = rootEditPart.getChildren().size();
			String rootChildren = "";
			for(int i=0;i<rootEditPartChildrenSize;i++){
				rootChildren = rootChildren + "Type " + i +" is "
				+ rootEditPart.getChildren().get(i).
				getClass().toString()+"\n";
			}*/
			
			int diagramPartSize = editPart.getChildren().size();
			int contentSize = editor.getDiagram().getElement().eContents().size();
			String reportContent = "";
			for(int i=0;i<contentSize;i++){
				reportContent = reportContent + "Type " + i +" is "
				+ editor.getDiagram().getElement()
				.eContents().get(i).getClass().toString()+"\n";
			}
			
			List childrenList = editPart.getChildren();
			
			String report ="";
			
			for(int i=0;i<diagramPartSize;i++){
				
				String reportTypes = "";
				
				reportTypes = "\n Type " + i +" is " 
				+(childrenList.get(i)).getClass().toString()+"\n";
				
				String sourceConnections ="";
				String targetConnections ="";
				List sourceConnectionsList = ((ShapeNodeEditPart)childrenList.get(i)).getSourceConnections();
				List targetConnectionsList = ((ShapeNodeEditPart)childrenList.get(i)).getTargetConnections();
				Iterator iter = sourceConnectionsList.iterator();
				while(iter.hasNext()){
					sourceConnections =sourceConnections+
					"\n Type " + iter.hashCode()+ " is "
					+ iter.next().getClass().toString();
				}
				 iter = targetConnectionsList.iterator();
				 while(iter.hasNext()){
					 targetConnections =targetConnections+
						"\n Type " + iter.hashCode()+ " is "
						+ iter.next().getClass().toString();
					}
				 
				 report = report + "\n" +reportTypes +"\n sourceConnections \n"+sourceConnections+
				 			"\n targetConnections \n"+targetConnections;
			}
			
			
			
			/*String diagramEditPart = editPart.getClass().toString();
			String editorInputClass = editor.getEditorInput().getClass().toString();
			
			
			EditPart editPartFirstChild =((EditPart)editPart.getChildren().get(0)); 
			int sizefirstChild = editPartFirstChild.getChildren().size();
			
			String reportTypesChild = "";
			for(int i=0;i<sizefirstChild;i++){
				reportTypesChild = reportTypesChild + "Type " + i +" is " 
				+((EditPart)editPartFirstChild.getChildren().get(i)).getClass().toString()+"\n";
			}
			
			StructuredSelection graphicalSelection = ((StructuredSelection)editor.
									getDiagramGraphicalViewer().getSelection());
			int elementsNumber = editor.getDiagramGraphicalViewer().getSelectedEditParts().size();
			
			String slectedElements = "";
			
			for(int i =0; i<elementsNumber;i++){
				slectedElements = slectedElements + "\n"
				+ ((SysmlDiagramEditor)editor).
				returnElements(graphicalSelection).
				get(i).getClass().toString();
			}*/
			
			String result[] = {""};
			ICompilerResult  compilerResult;
			
			boolean showInConsole = true;
			try{
				
				String command = "loadModel(\"Modelica\")";
				compilerResult = CompilerProxy.sendExpression(command,showInConsole);
				result = compilerResult.getResult();
			}
			catch(Exception e){
				
			}
			MessageDialog
            .openInformation(
                    shell,"ModelicaML menu",
                    "testing " + label
                  //  +" \n "+ diagramEditPart
                 //   +"\n" + "editorInputClass: " + editorInputClass
                    +"\n Diagram size "+ diagramPartSize
                   // +"\n rootEditPartChildrenSize: "+ rootEditPartChildrenSize
                    //+"\n" + rootChildren
                   //+report
                   // +"\n Content \n" + reportContent+"\n"
                  //  + "\n diagramSelectioSize: "+diagramSelectioSize
                   // +diagramSelectionElements
                    // + "\n slectedElements "+elementsNumber+"\n"
                    // + slectedElements
                     //+"\n graphicalEditPartName "+ graphicalEditPartName
                   // +"\n First child"
                    //+"\n"+ reportTypesChild 
                    +"\n cd(\"..\")"
                     );			
		}
		
		public void setActiveEditor(IEditorPart part) {
            activeEditor = part;
        }
	}
	
	/**
	 * @generated NOT
	 */
	public SysmlDiagramActionBarContributor(){
		
		
		MLmenu = new EditorAction("ModelicaML");
		inheritanceDiagram = new RetargetAction(id1, "Inheritance Diagram");
		submenu2 = new RetargetAction(id2, "submenu2");
		inheritanceDiagramHandler = new SysmlInheritanceDiagramAction("Inheritance Diagram" , SysmlInheritanceDiagramAction.FROM_FILE);
		submenu2Handler = new EditorAction("submenu2");
	}
	
	/**
	 * @generated NOT
	 */
	public void contributeToMenu(IMenuManager menuManager) {
        // Run super.
        super.contributeToMenu(menuManager);

        // Editor-specitic menu
        MenuManager modelicaMLMenu = new MenuManager("ModelicaML"); //$NON-NLS-1$
        MenuManager inheritanceSubMenu = new MenuManager("Inheritance diagram");
        // It is important to append the menu to the
        // group "additions". This group is created
        // between "Project" and "Tools" menus
        // for this purpose.
        menuManager.insertAfter("additions", modelicaMLMenu); //$NON-NLS-1$
        inheritanceSubMenu.add(inheritanceDiagram);
        inheritanceSubMenu.setVisible(true);
        modelicaMLMenu.add(inheritanceSubMenu);
        modelicaMLMenu.add(submenu2);
    }


	/**
	 * @generated NOT
	 */
	public void init(IActionBars bars, IWorkbenchPage page) {
		super.init(bars, page);
		String selectAllId =  ActionFactory.SELECT_ALL.getId();
//		selectAllAction = (Action)bars.getGlobalActionHandler(selectAllId);
//		diagramAction = new SysmlDiagramAction(page);
		
		bars.setGlobalActionHandler(id1, inheritanceDiagramHandler);
		bars.setGlobalActionHandler(id2, submenu2Handler);
		
//		 Hook retarget actions as page listeners
		page.addPartListener(inheritanceDiagram);
		page.addPartListener(submenu2);
		IWorkbenchPart activePart = page.getActivePart();
		if (activePart != null) {
			inheritanceDiagram.partActivated(activePart);
			submenu2.partActivated(activePart);
			
		}

	}

	/**
	 * @generated NOT
	 */
	public void dispose() {
		// Remove retarget actions as page listeners
		getPage().removePartListener(inheritanceDiagram);
		getPage().removePartListener(submenu2);
		
		
	}
	
	/**
	 * @generated NOT
	 */
	public void setActiveEditor(IEditorPart editor) {
		
		super.setActiveEditor(editor);
		
		MLmenu.setActiveEditor(editor);
		inheritanceDiagramHandler.setActiveEditor(editor);
		submenu2Handler.setActiveEditor(editor);
		
	}
		
	/**
	 * @generated
	 */
	protected Class getEditorClass() {
		return SysmlDiagramEditor.class;
	}

	/**
	 * @generated
	 */
	protected String getEditorId() {
		return SysmlDiagramEditor.ID;
	}
}
