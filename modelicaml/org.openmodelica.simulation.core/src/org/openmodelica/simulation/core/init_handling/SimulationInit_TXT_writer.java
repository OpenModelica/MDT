package org.openmodelica.simulation.core.init_handling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

// TODO: Auto-generated Javadoc
/**
 * Generates an text file including all data from the model_init.txt file in the same structure
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 *
 */
public class SimulationInit_TXT_writer {

	/**
	 * Reads a init.txt file from a specified path and creates an InitData object
	 * which contains all initial values and simulation settings
	 *
	 * @param pathToInit Absoulut path to the "_init.txt" file
	 * @param modifiedInitData the modified init data
	 * @return An InitData object containing all initial values and simulation
	 * settings
	 */
	public static boolean writeInit(String pathToInit, InitData modifiedInitData) {
			
			File initFile = new File(pathToInit);
			
			Writer fw = null; 
			 
			try 
			{ 
			  fw = new FileWriter(initFile); 
			  
			  fw.write(modifiedInitData.start + " // start value");
			  fw.append( System.getProperty("line.separator") );
			  fw.append( modifiedInitData.stop + "// stop value");
			  fw.append( System.getProperty("line.separator") );
			  fw.append( modifiedInitData.step + " // step value");
			  fw.append( System.getProperty("line.separator") );
			  fw.append( modifiedInitData.tolerance + " // tolerance");
			  fw.append( System.getProperty("line.separator") );
			  fw.append( "\"" + modifiedInitData.method + "\" // method");
			  fw.append( System.getProperty("line.separator") );
			  fw.append(  "\"plt\" // outputFormat");
			  fw.append( System.getProperty("line.separator") );
			  fw.append(  "\".*\" // variableFilter");
			  fw.append( System.getProperty("line.separator") );
			 
			  fw.append( modifiedInitData.states + " // n states");
			  fw.append( System.getProperty("line.separator") );
			  fw.append( modifiedInitData.alg_vars + " // n alg vars");
			  fw.append( System.getProperty("line.separator") );
			  fw.append( modifiedInitData.parameters + " // n parameters");
			  fw.append( System.getProperty("line.separator") );
			  
			  fw.append( modifiedInitData.n_int_parameters + " // n int parameters");
			  fw.append( System.getProperty("line.separator") );
			  fw.append( modifiedInitData.n_int_variables + " // n int variables");
			  fw.append( System.getProperty("line.separator") );
			  fw.append( modifiedInitData.n_bool_parameters + " // n bool parameters");
			  fw.append( System.getProperty("line.separator") );
			  fw.append( modifiedInitData.n_bool_variables + " // n bool variables");
			  fw.append( System.getProperty("line.separator") );
			  
			  fw.append( modifiedInitData.string_parameters + " // n string-parameters");
			  fw.append( System.getProperty("line.separator") );
			  fw.append( modifiedInitData.string_variables + " // n string variables");
			  fw.append( System.getProperty("line.separator") );
			  //--- States
			  for(String stateName : modifiedInitData.orderdStateProperties){
				  fw.append( modifiedInitData.stateProperties.get(stateName) + " // " + stateName + "");
				  fw.append( System.getProperty("line.separator") );				  
			  }
			  //--- StateDer
			  for(String derstateName : modifiedInitData.orderdDerStateProperties){
				  fw.append( modifiedInitData.derStateProperties.get(derstateName) + " // " + derstateName + "");
				  fw.append( System.getProperty("line.separator") );				  
			  }
			  //--- Alg
			  for(String algName : modifiedInitData.orderdAlgProperties){
				  fw.append( modifiedInitData.algProperties.get(algName) + " // " + algName + "");
				  fw.append( System.getProperty("line.separator") );				  
			  }
			  //--- Param
			  for(String parName : modifiedInitData.orderdParameterProperties){
				  fw.append( modifiedInitData.parameterProperties.get(parName) + " // " + parName + "");
				  fw.append( System.getProperty("line.separator") );				  
			  }
			  
			//--- int Param
			  for(String parName : modifiedInitData.orderdN_int_parametersProperties){
				  fw.append( modifiedInitData.n_int_parametersProperties.get(parName) + " // " + parName + "");
				  fw.append( System.getProperty("line.separator") );				  
			  }
			  
				//--- int Var
			  for(String varName : modifiedInitData.orderdN_int_variablesProperties){
				  fw.append( modifiedInitData.n_int_variablesProperties.get(varName) + " // " + varName + "");
				  fw.append( System.getProperty("line.separator") );				  
			  }
			  
				//--- bool Param
			  for(String parName : modifiedInitData.orderdN_bool_parametersProperties){
				
				  //TODO [20110308] New Version of OMI init file has true/false for boolean instead of 1.0/0.0
				  if(modifiedInitData.n_bool_parametersProperties.get(parName) == 1.0)
					  fw.append( "true" + " // " + parName + "");
				  else if(modifiedInitData.n_bool_parametersProperties.get(parName) == 0.0)
					  fw.append( "false" + " // " + parName + "");
				  
				  fw.append( System.getProperty("line.separator") );				  
			  }
			  
				//--- bool Var
			  for(String varName : modifiedInitData.orderdN_bool_variablesProperties){
				  
				//TODO [20110308] New Version of OMI init file has true/false for boolean instead of 1.0/0.0
				  if(modifiedInitData.n_bool_variablesProperties.get(varName) == 1.0)
					  fw.append( "true" + " // " + varName + "");
				  else if(modifiedInitData.n_bool_variablesProperties.get(varName) == 0.0)
					  fw.append( "false" + " // " + varName + "");
				  
				  fw.append( System.getProperty("line.separator") );				  
			  }
			  
			  //TODO Strings
			  
			  return true;
			} 
			catch ( IOException e ) { 
			  System.err.println( "Konnte Datei nicht erstellen" ); 
			} 
			finally { 
			  if ( fw != null ) 
			    try { fw.close(); } catch ( IOException e ) { } 
			}
			
		return false;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		InitData modifiedInitData;
		try {
			String projectPath = "C:\\AircraftWaterTankSystem.TestEnvironment_init.xml";
			modifiedInitData = SimulationInit_XML_reader.readFromXML(projectPath);
			modifiedInitData.step = 1.8;
			System.out.println(modifiedInitData.n_int_variablesProperties);
			writeInit("C:\\AircraftWaterTankSystem.TestEnvironment_init.xml", modifiedInitData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
