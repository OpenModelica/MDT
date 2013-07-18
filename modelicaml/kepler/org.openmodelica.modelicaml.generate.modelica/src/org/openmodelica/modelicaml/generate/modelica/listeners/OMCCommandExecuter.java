//package org.openmodelica.modelicaml.generate.modelica.listeners;
//
//
//import org.eclipse.uml2.uml.Element;
//import org.modelica.ConnectException;
//import org.modelica.OMCProxy;
//
//// TODO: Auto-generated Javadoc
///**
// * The Class OMCCommandExecuter.
// */
//public class OMCCommandExecuter {
//	
//	/** The status. */
//	private String status;
//	
//	/** The proxy. */
//	private OMCProxy proxy = new OMCProxy();
//	
//	/** The simulation parameters. */
//	private String simulationParameters = "";
//	
//	/** The dir. */
//	private String dir;
//	
//	/** The error string. */
//	private String errorString = "";
//	
//	/** The elt. */
//	private Element elt;
//	
//	
//	/**
//	 * Instantiates a new oMC command executer.
//	 * 
//	 * @param elt
//	 *            the elt
//	 * @param folderPath
//	 *            the folder path
//	 * @param modelFilePath
//	 *            the model file path
//	 * @param modelElementQualifiedName
//	 *            the model element qualified name
//	 * @param plotCommand
//	 *            the plot command
//	 * @param simPar
//	 *            the sim par
//	 */
//	public OMCCommandExecuter(Element elt, String folderPath, String modelFilePath, String modelElementQualifiedName, String plotCommand, String simPar) {
//		this.dir = folderPath;
//		this.elt = elt;
//		executeOMCCommand(dir, modelFilePath,modelElementQualifiedName, plotCommand, simPar);
//	}
//
//	/**
//	 * Execute omc command.
//	 * 
//	 * @param folderPath
//	 *            the folder path
//	 * @param modelFilePath
//	 *            the model file path
//	 * @param modelElementQualifiedName
//	 *            the model element qualified name
//	 * @param plotCommand
//	 *            the plot command
//	 * @param simPar
//	 *            the sim par
//	 * @return the string
//	 */
//	private String executeOMCCommand(String folderPath, String modelFilePath, String modelElementQualifiedName, String plotCommand, String simPar) {
//		
//		if (simPar != null) {
//			simulationParameters = "," + simPar;
//		}
//		
//		try {
//
//			status = proxy.sendExpression("clear()");
//			
//			//proxy.sendExpression("cd(\"" + dir + "\")");
//			
//			if (status == null) {
//				System.err.println("No connection to OMC! ");
//				//MoldelicaMLSimulationMarkterCreator.modelicaMLSimulationAlert(elt, "error", "No connection to OMC! ");
//			}
//
//			status = proxy.sendExpression("loadFile(\"" + modelFilePath + "\")");
//			
//			if (status.contains("error") || status.contains("false")) {
//				System.err.println("Cannot find the package " + modelFilePath + "!");
//				//MoldelicaMLSimulationMarkterCreator.modelicaMLSimulationAlert(elt, "error", "Cannot find the package " + modelFilePath + "!");
//			}
//			
//			errorString = proxy.sendExpression("getErrorString()");
//			if (!errorString.equals("")) {
//				System.err.println(errorString);
//			}
//			
//			status = proxy.sendExpression("checkModel("
//					+ modelElementQualifiedName + ")");
//			
//			errorString = proxy.sendExpression("getErrorString()");
//			if (!errorString.equals("")) {
//				System.err.println(errorString);
//			}
//			
//
//		} catch (ConnectException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return status;
//	}
//
//}
