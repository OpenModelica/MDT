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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.simulation.xml;

import java.util.ArrayList;

/**
 * 
 * @author parham.vasaiely
 *
 * this class represents the test session containing a set of single test (verification) models. 
 */
public class TestSession {

	public String packageFileRelativePath = "";
	public ArrayList<TestModel> testModels;
	
	public TestSession(String packageFileRelativePath){
		this.packageFileRelativePath =  packageFileRelativePath;
		testModels = new ArrayList<TestModel>();
	}
	
	/**
	 * Internal class test model. A test model contains all parameters to configure a simulation
	 * @author parham.vasaiely
	 *
	 */
	public static class TestModel{
		public String qualifiedName;
		//simulationData
		public String start;
		public String stop;
		public String numberOfIntervals;
		public String tolerance;
		public String solver;
		public String outputFormat;
		
		public TestModel(String qualifiedName, String start, String stop, String numberOfIntervals,
				String tolerance, String solver, String outputFormat) {

			this.qualifiedName = qualifiedName;
			this.start = start;
			this.stop = stop;
			this.numberOfIntervals = numberOfIntervals;
			this.tolerance = tolerance;
//			this.start = new Double(start);
//			this.stop = new Double(stop);
//			this.numberOfIntervals = new Double(numberOfIntervals);
//			this.tolerance = new Double(tolerance);
			this.solver = solver;
			this.outputFormat = outputFormat;
		}
	}
}
