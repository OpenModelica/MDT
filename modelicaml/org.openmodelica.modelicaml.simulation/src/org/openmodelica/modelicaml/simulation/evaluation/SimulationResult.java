package org.openmodelica.modelicaml.simulation.evaluation;

import java.util.Map;
import java.util.TreeMap;

public class SimulationResult {

	public String property;
	public Map<Double, Double> values;
	
	public SimulationResult(){
		property = "";
		this.values = new TreeMap<Double, Double>();
	}
	
//	public SimulationResult(String property, Map<Double, Double> values){
//		this.property = property;
//		this.values = values;
//	}
	
	public static void main(String[] args){
	}
}
