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
