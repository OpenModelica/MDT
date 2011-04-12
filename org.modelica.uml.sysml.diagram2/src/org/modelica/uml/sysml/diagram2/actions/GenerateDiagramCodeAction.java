package org.modelica.uml.sysml.diagram2.actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.internal.impl.GeneralizationImpl;
import org.eclipse.uml2.uml.internal.impl.DirectedRelationshipImpl;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.uml.sysml.diagram2.part.SysmlDiagramEditor;
import org.modelica.uml.sysml.diagram2.util.Utilities;
import org.modelica.uml.sysml.diagram2.edit.parts.DeriveReqRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.GeneralRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParametersEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassVariablesEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEquationsEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassPartsEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassConnectionsEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaCompositionEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.GeneralizationEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaEquationPropertyEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty4EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPortEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaConnectionEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RequirementEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfiedByEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfiedByRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfiesEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfyRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.figures.InOutModelicaPortFigure;

import org.modelica.uml.sysml.GeneralRelationship;
import org.modelica.uml.sysml.ModelicaBoolean;
import org.modelica.uml.sysml.ModelicaInteger;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.ModelicaConnection;
import org.modelica.uml.sysml.ModelicaEquationProperty;
import org.modelica.uml.sysml.ModelicaReal;
import org.modelica.uml.sysml.ModelicaString;
import org.modelica.uml.sysml.PropertyKind;
import org.modelica.uml.sysml.FlowDirection;
import org.modelica.uml.sysml.Requirement;
import org.modelica.uml.sysml.SatisfiedBy;
import org.modelica.uml.sysml.SatisfiedByProperty;
import org.modelica.uml.sysml.Satisfies;
import org.modelica.uml.sysml.SatisfiesProperty;
import org.modelica.uml.sysml.VariabilityKind;

public class GenerateDiagramCodeAction extends Action{

	private IEditorPart activeEditor;
	private DiagramEditPart editPart = null;

	public GenerateDiagramCodeAction(String text){
		super(text);
	}

	public void runWithEvent(Event event) {
		Display display = event.display;
		openGenDialog(display);
	}

	public void openGenDialog(Display display) {
		Shell shell = display.getActiveShell();
		shell.setActive();

		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		dialog.setFilterExtensions(new String[]{"*.mo","*.*"});
		dialog.setText("Specify name of the file to generate");
		String fileName = "";
		String path = "";
		String projectName = "";
		String diagramExtension = "";

		if(activeEditor.getEditorInput() instanceof FileEditorInput) {
			fileName = ((FileEditorInput)activeEditor.getEditorInput()).getFile().getName();
			diagramExtension = ((FileEditorInput)activeEditor.getEditorInput()).getFile().getFileExtension();
			projectName = ((FileEditorInput)activeEditor.getEditorInput()).getFile().getFullPath().segment(0);

		}
		if (fileName != null)
			fileName = fileName.replaceAll(diagramExtension, "mo");
		IPath location =Platform.getLocation();
		path = Utilities.getPath(location);
		if(projectName != null || !projectName.equals(""))
			path += projectName;
		dialog.setFilterPath(path);
		dialog.setFileName(fileName);
		dialog.open();

		path = dialog.getFilterPath();
		fileName = dialog.getFileName();

		generateCode(shell, path, fileName);		
	}

	public void setActiveEditor(IEditorPart part) {
		activeEditor = part;

		if(activeEditor instanceof SysmlDiagramEditor){
			editPart = ((SysmlDiagramEditor) activeEditor).getDiagramEditPart();

		}
	}

	private void generateCode(Shell shell, String path, String fileName){
		File fstream = null;
		BufferedWriter out = null;
		try{
			// Create file 
			fstream = new File(path + "\\" +fileName);
			if (fstream.exists()) {
				MessageBox warning = new MessageBox(shell, SWT.YES | SWT.NO);
				warning.setMessage("File " + fileName + " exist. Do you want to override it?");
				int res = warning.open();
				if(res == SWT.NO)
					return;
			}
			out = new BufferedWriter(new FileWriter(fstream));

		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

		String code = "";
		List editParts = editPart.getChildren();
		Iterator iter = editParts.iterator();
		while(iter.hasNext()){
			AbstractEditPart edP = (AbstractEditPart)iter.next();
			if(edP instanceof ModelicaClassEditPart)
				code += "\n" + generateForClass((ModelicaClassEditPart)edP);
			else if(edP instanceof RequirementEditPart)
				code += "\n" + generateForRequirement((RequirementEditPart)edP);
			code += "\n";
		}	

		if(out != null) {
			try {
				out.write(code);
				out.close();
			} catch (Exception ex) {}
		}
	}

	private String generateForClass(ModelicaClassEditPart edp){
		String generatedCode = "";

		String stereotype = edp.getPrimaryShape().
		getFigureStereotypeName().getText();
		String type = stereotype.substring(2, stereotype.length() - 2); 
		generatedCode += type + " ";
		String name =edp.getPrimaryShape().
		getFigureClassName().getText();
		generatedCode += name + "\n";
		String satisfiesCode = getSatisfiedClassCode(edp);
		if (!satisfiesCode.isEmpty())
			satisfiesCode = "annotation(" + satisfiesCode + ");\n";
		generatedCode += satisfiesCode;
		String inheritancecode;
		generatedCode += getInheritanceCode(edp);

		String compositionsCode;
		generatedCode += getCompositionsCode(edp);

		List contentEdps = edp.getChildren();

		Iterator iter = contentEdps.iterator();

		List equationsEdps = new ArrayList();

		while(iter.hasNext()){
			AbstractEditPart childEDP = (AbstractEditPart)iter.next();
			if (childEDP instanceof ModelicaClassEquationsEditPart) {
				equationsEdps.add(childEDP);
				continue;
			}
			if (childEDP instanceof ModelicaClassConnectionsEditPart)
				equationsEdps.add(childEDP);

			generatedCode += getContentCode(childEDP);
		}

		iter = equationsEdps.iterator();
		boolean hasEquations = false;
//		hasEquations = iter.hasNext();
		String equations = "";
//		if (hasEquations) {
//		generatedCode += "equations\n";
//		}
		while(iter.hasNext()) {
			AbstractEditPart childEDP = (AbstractEditPart)iter.next();
			equations += getEquations(childEDP);
			if(!equations.equals(""))
				hasEquations = true;
		}
		if(hasEquations)
			generatedCode += "equations\n" + equations;
		generatedCode += "end " + name + ";";

		String[] result = {""};
		ICompilerResult  compilerResult;

		boolean showInConsole = true;
		try{

			String command = generatedCode;
			System.out.println(command);
			compilerResult = CompilerProxy.sendExpression(command,showInConsole);
			result = compilerResult.getResult();
			if(Boolean.parseBoolean(result[0].trim()) == true){

//				result =new String[]{"Modelica"}; 
			}
			compilerResult = CompilerProxy.sendExpression("getClassNames()",showInConsole);
			result = compilerResult.getResult();

		}
		catch(Exception e){

		}

		return generatedCode;

	}

	private String getInheritanceCode(ModelicaClassEditPart edp){
		String code = "";

		List sourceConnections = edp.getSourceConnections();
		Iterator iter = sourceConnections.iterator();
		while(iter.hasNext()){
			Object listItem = iter.next();
			if(listItem instanceof GeneralizationEditPart) {
				if(((GeneralizationEditPart)listItem).getModel() instanceof Edge){
					Edge generalization = (Edge)((GeneralizationEditPart)listItem).getModel();
					if(generalization.getElement() instanceof GeneralizationImpl) {
						GeneralizationImpl genElement = (GeneralizationImpl)generalization.getElement();
						List targets = genElement.getTargets();
						Iterator targetsIter = targets.iterator();
						while(targetsIter.hasNext()){
							Object target = targetsIter.next();
							if(target instanceof ModelicaClass){
								ModelicaClass inheritance = (ModelicaClass)target;
								code += "extends " + inheritance.getName() +";\n";

							}
						}


					}

				}

			}
		}
		return code;

	}

	private String getContentCode(AbstractEditPart edp){
		String code = "";

		if(edp instanceof ModelicaClassParametersEditPart)
			code += getParametersCode(edp);
		else if(edp instanceof ModelicaClassVariablesEditPart)
			code += getParametersCode(edp);
		else if(edp instanceof ModelicaClassPartsEditPart)
			code += getPartsCode(edp);
		else if(edp instanceof ModelicaClassConnectionsEditPart)
			code += getParametersCode(edp);

		return code;

	}


	private String getEquations(AbstractEditPart edp){
		String code = "";

		if(edp instanceof ModelicaClassConnectionsEditPart)
			code += getConnectionsCode((ModelicaClassConnectionsEditPart)edp);
		else if(edp instanceof ModelicaClassEquationsEditPart)
			code += getEquationCode((ModelicaClassEquationsEditPart)edp);

		return code;
	}

	private String getParametersCode(AbstractEditPart edp){
		String code = "";
		List parameters = edp.getChildren();
		Iterator iter = parameters.iterator();
		for(int i = 0; i < parameters.size();i++){
			Object elem = getNodeElem((EditPart)parameters.get(i));

			if( elem instanceof ModelicaProperty){
				code += getPropertyCode((ModelicaProperty)elem);
			}
		}
		return code;
	}


	private String getPropertyCode(ModelicaProperty property){
		String code = "";

		if(property.getPropertyKind() == PropertyKind.PARAMETER_LITERAL)
			code = getParameterCode(property);
		else if(property.getPropertyKind() == PropertyKind.VARIABLE_LITERAL)
			code = getVariableCode(property);
//		else if(property.getPropertyKind() == PropertyKind.EQUATION_LITERAL)
//		code = getEquationCode((ModelicaEquationProperty)property);
		else if(property.getPropertyKind() == PropertyKind.PART_LITERAL)
			code = getPartCode(property);

		return code;
	}

	/**
	 * @param property
	 * @return
	 */
	private String getParameterCode(ModelicaProperty property){
		String code = property.getPropertyKind().getLiteral() + " ";
		String tmpCode = "";	

		if(property.getType() == null)
			return "";

		tmpCode = property.getType().getName();	
		if(!tmpCode.isEmpty())
			code += tmpCode + " ";

		tmpCode = property.getName();
		if(!tmpCode.isEmpty())
			code += tmpCode + " ";

		tmpCode = property.getArrayDimension();
		if(tmpCode != null) {
			code += "[" + tmpCode + "]";
		} else {
//			get the default value code
			tmpCode = getTypeCode(property.getType());
			if(tmpCode != null)
				code += tmpCode;
		}
		code += ";\n";


		return code;
	}

	private String getVariableCode(ModelicaProperty property){
		if(property.getType() == null)
			return "";
		String code = "";
		String tmpCode = "";
		if(property.getVariability()== VariabilityKind.CONSTANT_LITERAL)
			tmpCode = "constant ";
		else if(property.getVariability()== VariabilityKind.DISCRETE_LITERAL)
			tmpCode = "discrete ";
		else if(FlowDirection.IN_LITERAL == property.getDirection())
			tmpCode = "input ";
		else if(FlowDirection.OUT_LITERAL == property.getDirection())
			tmpCode = "output ";
		if(!tmpCode.isEmpty())
			code += tmpCode + " ";

		tmpCode = property.getType().getName();	
		if(!tmpCode.isEmpty())
			code += tmpCode + " ";
		tmpCode = property.getName();
		if(!tmpCode.isEmpty())
			code += tmpCode + " ";
		tmpCode = property.getArrayDimension();
		if(tmpCode != null) {
			code += "[" + tmpCode + "]";
		} else {
//			get the default value code
			tmpCode = getTypeCode(property.getType());
			if(tmpCode != null)
				code += tmpCode;
		}
		code += ";\n";


		return code;
	}

	private String getTypeCode(Type type) {
		String code = null;
		if (type instanceof ModelicaReal)
			code = getCodeForModelicaReal((ModelicaReal)type);
		else if (type instanceof ModelicaString)
			code = getCodeForModelicaString((ModelicaString)type);
		else if (type instanceof ModelicaInteger)
			code = getCodeForModelicaInteger((ModelicaInteger)type);
		else if (type instanceof ModelicaBoolean)
			code = getCodeForModelicaBoolean((ModelicaBoolean)type);
		return code;
	}

	private String getCodeForModelicaReal(ModelicaReal type) {
		String code = "";
		if (type != null) {
			if (type.getStart() != 0)
				code += "start = " + type.getStart() + ",";
			if (type.getValue() != 0)
				code += "value = " + type.getValue() + ",";
			if (type.getQuantity() != null && !type.getQuantity().isEmpty())
				code += "quantity = " + type.getQuantity()+ ",";
			if (type.getUnit() != null && !type.getUnit().isEmpty())	
				code += "unit = " + type.getUnit() + ",";
			if (type.getDisplayUnit() != null && !type.getDisplayUnit().isEmpty())
				code += "displayUnit = " + type.getDisplayUnit()+ ",";
			if (type.getMin() != 0)
				code += "min = " + type.getMin() + ",";
			if (type.getMax() != 0)	
				code += "max = " + type.getMax() + ",";
			if (type.isFixed())
				code += "fixed = " + type.isFixed() + ",";
			if (type.getNominal() != 0)
				code += "nominal = " + type.getNominal()+ ",";
			if (code != null && !code.isEmpty())
				code = "(" + code + ")";	
		}
		return code;
	}

	private String getCodeForModelicaString(ModelicaString type) {
		String code = "";
		if (type != null) {
			if (type.getStart() != null && !type.getStart().isEmpty())
				code += "start = " + type.getStart() + ",";
			if (type.getValue() != null && !type.getValue().isEmpty())
				code += "value = " + type.getValue() + ",";
			if (code != null && !code.isEmpty())
				code = "(" + code + ")";	
		}
		return code;
	}

	private String getCodeForModelicaInteger(ModelicaInteger type) {
		String code = "";
		if (type != null) {
			if (type.getStart() != 0)
				code += "start = " + type.getStart() + ",";
			if (type.getValue() != 0)
				code += "value = " + type.getValue() + ",";
			if (type.getMin() != 0)
				code += "min = " + type.getMin() + ",";
			if (type.getMax() != 0)	
				code += "max = " + type.getMax() + ",";
			if (type.isFixed())
				code += "fixed = " + type.isFixed() + ",";
			if (code != null && !code.isEmpty())
				code = "(" + code + ")";	
		}
		return code;
	}

	private String getCodeForModelicaBoolean(ModelicaBoolean type) {
		String code = "";
		if (type != null) {
			if (type.isStart())
				code += "start = " + type.isStart() + ",";
			if (type.isValue())
				code += "value = " + type.isValue() + ",";
			if (type.isFixed())
				code += "fixed = " + type.isFixed() + ",";
			if (code != null && !code.isEmpty())
				code = "(" + code + ")";	
		}
		return code;
	}

	private String getEquationCode(ModelicaClassEquationsEditPart edp){
		String code = "";
		List children = edp.getChildren();
		Iterator chIter = children.iterator();
		while (chIter.hasNext()) {
			ModelicaEquationPropertyEditPart elem = (ModelicaEquationPropertyEditPart) chIter.next();
			if(elem.getModel() instanceof NodeImpl){
				if(((NodeImpl)elem.getModel()).getElement() instanceof ModelicaEquationProperty){
					ModelicaEquationProperty equation = (ModelicaEquationProperty)(((NodeImpl)elem.getModel()).getElement());
					code += equation.getEquation() == null ? "" : equation.getEquation() + "\n";
				}
			}
		}

		return code;
	}

	private String getPartCode(ModelicaProperty property){
		String code = "";
		String tmpCode = "";		

		if(property.getType() == null)
			return "";

		tmpCode = property.getType().getName();	
		if(!tmpCode.isEmpty())
			code += tmpCode + " ";

		tmpCode = property.getName();
		if(!tmpCode.isEmpty())
			code += tmpCode + " ";

		tmpCode = property.getArrayDimension();
		if(tmpCode != null)
			code += "[" + tmpCode + "]";
		/*TO DO default value*/
		code += ";\n";


		return code;

	}

	private String getPartsCode(AbstractEditPart edp){
		String code = "";
		code += getParametersCode(edp);

		return code;
	}

	private String getCompositionsCode(EditPart edp) {
		String code = "";

		if(edp instanceof ModelicaClassEditPart){
			List compositions = ((ModelicaClassEditPart)edp).getSourceConnections();
			Iterator iter = compositions.iterator();
			while(iter.hasNext()){
				Object part = iter.next();
				if(part instanceof ModelicaCompositionEditPart){
					Object model = ((ModelicaCompositionEditPart)part).getModel();
					if(model instanceof Edge){
						Object element = ((Edge)model).getElement();
						if(element instanceof ModelicaComposition)
							code += generateClassPartCode((ModelicaComposition)element);
					}
				}
			}

		}

		return code;
	}

	private String getConnectionsCode(ModelicaClassConnectionsEditPart edp){
		String code = "";
		List childrenEDP = edp.getChildren();
		Iterator iter = childrenEDP.iterator();
		while (iter.hasNext()) {
			Object o = iter.next();
			if(o instanceof ModelicaProperty4EditPart){
				List chdEDP =((ModelicaProperty4EditPart)o).getChildren();
				Iterator pIter = chdEDP.iterator();
				while (pIter.hasNext()){
					Object port = pIter.next();
					if(port instanceof ModelicaPortEditPart){
						List sources = ((ModelicaPortEditPart)port).getSourceConnections();
						Iterator sIter = sources.iterator();
						while(sIter.hasNext()){
							Object connection = sIter.next();
							if(connection instanceof ModelicaConnectionEditPart)
								code += getConnectionCode(((ModelicaProperty4EditPart)o),
										(ModelicaConnectionEditPart)connection);
						}
					}
				}
			}

		}
		return code;

	}

	private String getConnectionCode(ModelicaProperty4EditPart source, 
			ModelicaConnectionEditPart edp){
		String code = "";
		ModelicaProperty sElem, tElem;
		String targetPortName = "", sourcePortName = "";
		if(getNodeElem(source) instanceof ModelicaProperty)
			sElem = (ModelicaProperty)getNodeElem(source);
		else return code;

		EditPart target = edp.getTarget();
		EditPart sourcePort = edp.getSource();

		if (sourcePort instanceof ModelicaPortEditPart){

			IFigure fig = ((ModelicaPortEditPart)sourcePort).getFigure();
			if(fig instanceof InOutModelicaPortFigure)
				sourcePortName = ((InOutModelicaPortFigure)fig).getPortName();
			if(sourcePortName == "")
				return code;
		}

		if (target instanceof ModelicaPortEditPart){

			EditPart parent = target.getParent();
			IFigure fig = ((ModelicaPortEditPart)target).getFigure();
			if(fig instanceof InOutModelicaPortFigure)
				targetPortName = ((InOutModelicaPortFigure)fig).getPortName();
			if(parent instanceof ModelicaProperty4EditPart){
				if(getNodeElem(parent) instanceof ModelicaProperty)
					tElem = (ModelicaProperty)getNodeElem(parent);
				else return code;

				code += "connect(" +sElem.getName() + "." + sourcePortName +
				"," + tElem.getName() + "." + targetPortName + ");\n";
			}
		}
		return code;
	}

	private Object getNodeElem(EditPart edp){
		Object elem = null;
		if(edp.getModel() instanceof Node){
			elem = ((Node)edp.getModel()).getElement();
		}
		return elem;
	}

	private String generateClassPartCode(ModelicaComposition edp){
		String code = "";
		String tmpCode = "";		

		if(edp.getSource()== null)
			return "";

		if(edp.getTarget() instanceof Type){
			Type type = (Type) edp.getTarget();
			tmpCode = type.getName();	
			if(!tmpCode.isEmpty())
				code += tmpCode + " ";
		}
		else return "";

		tmpCode = edp.getName();
		if(!tmpCode.isEmpty())
			code += tmpCode + " ";
		else return "";

		tmpCode = edp.getArrayDimension();
		if(tmpCode != null)
			code += "[" + tmpCode + "]";
		/*TO DO default value*/
		code += ";\n";

		return code;
	}

	public String getSatisfiedClassCode(ModelicaClassEditPart mc) {
		String code = "Satisfies = {";
		boolean addComma = false;
		boolean anyFound = false;
		List list = mc.getSourceConnections();
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			Object source = iter.next();
			if (source instanceof GeneralRelationshipEditPart) {
				GeneralRelationshipEditPart new_name = (GeneralRelationshipEditPart) source;
				if (new_name.getTarget() instanceof SatisfiesEditPart) {
					SatisfiesEditPart target = (SatisfiesEditPart) new_name.getTarget();
					if (((View)target.getModel()).getElement() instanceof Satisfies) {
						Satisfies satisfies = (Satisfies) ((View)target.getModel()).getElement();
						List satisfiesChildren = satisfies.getSatisfies();
						Iterator scIter = satisfiesChildren.iterator();
						while(scIter.hasNext()) {
							Object obj = scIter.next();
							if (obj instanceof SatisfiesProperty) {
								SatisfiesProperty satisfiesProp = (SatisfiesProperty) obj;
								String id = satisfiesProp.getRequirement().getId();
								if(id != null) {
									if (addComma) code += ",";			
									code += id;
									anyFound = true;													
									addComma= true;
								}
							}
						}
					}
				}
			}
		}
		if (anyFound)
			code += "};";
		else code = "";
		return code;
	}


	public String generateForRequirement(RequirementEditPart rqedp) {
		String code = "";
		String name = "";
		String idCode = "";
		String levelCode = "";
		String priorityCode = "";
		String statusCode = "";
		String descriptionCode = "";
		String annotationCode = "";

		String derivedFromCode = "";

		String masterCode = "";

		if (rqedp.getModel() instanceof NodeImpl) {
			NodeImpl model = (NodeImpl) rqedp.getModel();
			if (model.getElement() instanceof Requirement) {
				Requirement elem = (Requirement) model.getElement();
				name = elem.getName();
				if (name == null || name == "")
					return "";
				if (elem.getId() != null || elem.getId() != "")
					idCode = "id = \"" + elem.getId() + "\";\n";

				levelCode = "level = " + elem.getLevel() + ";\n";
				priorityCode = "priority = " + elem.getPriority() + ";\n";
				statusCode = "status = RequirementStatus." +elem.getStatus()+ ";\n";
				descriptionCode = "description = \"" + elem.getDescription() + "\";\n";
				if(elem.getDerivedFrom() != null)
					if(elem.getDerivedFrom().getName() != null)
						derivedFromCode = "derivedFrom = " + elem.getDerivedFrom().getName() + ";\n";

				if(elem.getMaster() != null)
					if(elem.getMaster().getName() != null)
						masterCode = "master = " + elem.getMaster().getName() + ";\n";

				annotationCode = getRequirementAnnotationCode(elem, rqedp);
				code = "requirement " + name + "\n";
				code += annotationCode;
				code += descriptionCode + levelCode + priorityCode + idCode 
				+ statusCode + derivedFromCode + masterCode;
				code += " end " + name + ";";
			}
		}
		return code;
	}


	public String getRequirementAnnotationCode(Requirement elem, RequirementEditPart rqedp) {
		String satisfiedByCode = "";
		String externalSatisfiedByCode = "";
		String satisfyCode = "";
		String derivedCode = "";
		String deriveReqtCode = "";
		String derivedFrom = "";
		String verifiedByCode = "";
		String tracedToCode = "";
		String refinedByCode = "";
		String code = "";
		derivedCode = getDerivedCode(elem);
		satisfiedByCode = getSatisfiedByCode(elem);
		refinedByCode = getRefinedByCode(elem);
		tracedToCode = getTracedToCode(elem);
		verifiedByCode = getVerifiedByCode(elem);
		externalSatisfiedByCode = getExternalSatisfiedByCode(rqedp);
		satisfyCode = getSatisfyCode(rqedp);
		deriveReqtCode = getDeriveReqtCode(rqedp);

		boolean hasAnnotation = !derivedCode.isEmpty() || !satisfiedByCode.isEmpty()
		|| !refinedByCode.isEmpty() || !tracedToCode.isEmpty() || !verifiedByCode.isEmpty()
		|| !externalSatisfiedByCode.isEmpty() || !satisfyCode.isEmpty();

		if (hasAnnotation)
			code = "annotation(" 
				+ (deriveReqtCode.isEmpty() ? "" : deriveReqtCode + ";\n")
				+ (derivedCode.isEmpty() ? "" : derivedCode + ";\n") 
				+ (derivedFrom.isEmpty() ? "" : derivedFrom + ";\n")
				+ (satisfiedByCode.isEmpty() ? "" : satisfiedByCode + ";\n") 
				+ (refinedByCode.isEmpty() ? "" : refinedByCode + ";\n") 
				+ (tracedToCode.isEmpty() ? "" : tracedToCode + ";\n") 
				+ (verifiedByCode.isEmpty() ? "" : verifiedByCode + ";\n") 
				+ (externalSatisfiedByCode.isEmpty() ? "" : externalSatisfiedByCode + ";\n") 
				+ (satisfyCode.isEmpty() ? "" : satisfyCode )
				+ ");\n";
		return code;
	}


	public String getDerivedCode(Requirement req) {
		String code = "";

		List derivedList = req.getDerived();
		Iterator iter = derivedList.iterator();
		boolean generateHeader = true;
		while(iter.hasNext()) {
			Object derived = iter.next();
			if (derived instanceof Requirement) {
				Requirement tmpDerived = (Requirement) derived;
				if(tmpDerived.getName() != null) {
					if(generateHeader)
						code = "derived = {";
					else code += ",";
					code += tmpDerived.getName();
					generateHeader = false;
				}	
			}
		}
		if(!generateHeader)
			code += "}";

		return code;
	}


	public String getDerivedFromCode(Requirement req) {
		String code = "";
		if (req != null){
			Requirement derivedFrom = req.getDerivedFrom();
			if (derivedFrom != null){
				String name = derivedFrom.getName();
				if(!name.isEmpty())
					code = "derivedFrom = {" + name + "}";
			}
		}
		return code;	
	}


	public String getVerifiedByCode(Requirement req) {
		String code = "";

		List derivedList = req.getVerifiedBy();
		Iterator iter = derivedList.iterator();
		boolean generateHeader = true;
		while(iter.hasNext()) {
			Object object = iter.next();
			if (object instanceof ModelicaClass) {
				ModelicaClass tmpObject = (ModelicaClass) object;
				if(tmpObject.getName() != null) {
					if(generateHeader)
						code = "verifiedBy = {";
					else code += ",";
					code += tmpObject.getName();
					generateHeader = false;
				}	
			}
		}
		if(!generateHeader)
			code += "}";

		return code;
	}


	public String getSatisfiedByCode(Requirement req) {
		String code = "";

		List derivedList = req.getSatisfiedBy();
		Iterator iter = derivedList.iterator();
		boolean generateHeader = true;
		while(iter.hasNext()) {
			Object object = iter.next();
			if (object instanceof ModelicaClass) {
				ModelicaClass tmpObject = (ModelicaClass) object;
				if(tmpObject.getName() != null) {
					if(generateHeader)
						code = "satisfiedBy = {";
					else code += ",";
					code += tmpObject.getName();
					generateHeader = false;
				}	
			}
		}
		if(!generateHeader)
			code += "}";

		return code;
	}

	public String getTracedToCode(Requirement req) {
		String code = "";

		List objectList = req.getTracedTo();
		Iterator iter = objectList.iterator();
		boolean generateHeader = true;
		while(iter.hasNext()) {
			Object object = iter.next();
			if (object instanceof ModelicaClass) {
				ModelicaClass tmpObject = (ModelicaClass) object;
				if(tmpObject.getName() != null) {
					if(generateHeader)
						code = "tracedTo = {";
					else code += ",";
					code += tmpObject.getName();
					generateHeader = false;
				}	
			}
		}
		if(!generateHeader)
			code += "}";

		return code;
	}

	public String getRefinedByCode(Requirement req) {
		String code = "";

		List objectList = req.getRefinedBy();
		Iterator iter = objectList.iterator();
		boolean generateHeader = true;
		while(iter.hasNext()) {
			Object object = iter.next();
			if (object instanceof ModelicaClass) {
				ModelicaClass tmpObject = (ModelicaClass) object;
				if(tmpObject.getName() != null) {
					if(generateHeader)
						code = "refinedBy = {";
					else code += ",";
					code += tmpObject.getName();
					generateHeader = false;
				}	
			}
		}
		if(!generateHeader)
			code += "}";

		return code;
	}

	public String getExternalSatisfiedByCode(RequirementEditPart redp) {
		String code = "linkedSatisfiedBy = {";
		boolean addComma = false;
		boolean anyFound = false;
		List list = redp.getSourceConnections();
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			Object source = iter.next();
			if (source instanceof SatisfiedByRelationshipEditPart) {
				SatisfiedByRelationshipEditPart new_name = (SatisfiedByRelationshipEditPart) source;
				if (new_name.getTarget() instanceof SatisfiedByEditPart) {
					SatisfiedByEditPart target = (SatisfiedByEditPart) new_name.getTarget();
					if (((View)target.getModel()).getElement() instanceof SatisfiedBy) {
						SatisfiedBy satisfiedBy = (SatisfiedBy) ((View)target.getModel()).getElement();
						List satisfiedByChildren = satisfiedBy.getSatisfiedBy();
						Iterator scIter = satisfiedByChildren.iterator();
						while(scIter.hasNext()) {
							Object obj = scIter.next();
							if (obj instanceof SatisfiedByProperty) {
								SatisfiedByProperty satisfiesProp = (SatisfiedByProperty) obj;
								String name = satisfiesProp.getElement().getName();
								if(name != null) {
									code += name;
									anyFound = true;
									if (addComma) code += ",";
									addComma= true;
								}
							}
						}
					}
				}
			}
		}
		if (anyFound)
			code += "}";
		else code = "";

		return code;
	}

	public String getSatisfyCode(RequirementEditPart rqedp) {
		String code = "satisfy = {";
		boolean addComma = false;
		boolean anyFound = false;
		List list = rqedp.getSourceConnections();
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			Object source = iter.next();
			if (source instanceof SatisfyRelationshipEditPart) {
				SatisfyRelationshipEditPart new_name = (SatisfyRelationshipEditPart) source;
				if (new_name.getTarget() instanceof ModelicaClassEditPart) {
					ModelicaClassEditPart target = (ModelicaClassEditPart) new_name.getTarget();
					if (((View)target.getModel()).getElement() instanceof ModelicaClass) {
						ModelicaClass satisfiedBy = (ModelicaClass) ((View)target.getModel()).getElement();
						String name = satisfiedBy.getName();
						if(name != null) {
							code += name;
							anyFound = true;
						}
					}
				}
			}
		}
		if (anyFound)
			code += "}";
		else code = "";

		return code;	

	}

	public String getDeriveReqtCode(RequirementEditPart redp) {
		String code = "";
		boolean addComma = false;
		boolean anyFound = false;
		if (redp != null){
			List list = redp.getTargetConnections();
			Iterator iter = list.iterator();
			while(iter.hasNext()){
				Object target = iter.next();
				if (target instanceof DeriveReqRelationshipEditPart) {
					DeriveReqRelationshipEditPart nTarget= (DeriveReqRelationshipEditPart)target;
					if (nTarget.getSource() instanceof RequirementEditPart) {
						RequirementEditPart source = (RequirementEditPart)nTarget.getSource();
						if (((View)source.getModel()).getElement() instanceof Requirement) {
							Requirement req = (Requirement)((View)source.getModel()).getElement();
							if (req != null){
								String id = req.getId();
								if (!id.isEmpty())
									code = "deriveReqt = {" + id + "}";
							}
						}
					}
				}
			}
		}
		return code;
	}
}
