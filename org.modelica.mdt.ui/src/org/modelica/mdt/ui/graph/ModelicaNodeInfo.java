
package org.modelica.mdt.ui.graph;

/**
 * This class structures the info of a general
 * Modelica object.
 *
 * @author: Magnus Sjöstrand
 */
public class ModelicaNodeInfo
{
	// Type: model, package ... 
	protected String classType = "";
	
	// Name: className 
	protected String className = "";
	
	// Description: <Description> 
	protected String classDescription = "";
	
	// Position: <Path>		
	protected String classPosition = "";
	
	// SearchPath: Class1.hello.hello2
	protected String classPath = "";
}
