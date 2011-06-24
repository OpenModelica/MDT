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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
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
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.simulation.environment.view.simulation.noninteractive;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.openmodelica.simulation.core.simulationdata.SimulationResultManager_NonInteractive;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * The Class NonInteractiveSimulationVisualisation_JFreeChartPlotComposite.
 */
public class NonInteractiveSimulationVisualisation_JFreeChartPlotComposite extends org.eclipse.swt.widgets.Composite implements Observer{

	/** Contains all available (plotable) results (redundancy to the result manager data but maybe high-performance). */
	Map<String, XYSeries> propMap; //FullQualifiedName, Series
	
	/** The dataset. */
	XYSeriesCollection dataset;
	
	/** The chart. */
	JFreeChart chart;
	
	/** The frame. */
	ChartFrame frame;
	
	/** The plot. */
	XYPlot plot;
	
	/** The max time in plot. */
	Double maxTimeInPlot = 0.0;
	
	/**
	* Overriding checkSubclass allows this class to extend org.eclipse.swt.widgets.Composite
	*/	
	protected void checkSubclass() {
	}

	/**
	 * Constructor to be used by default to create a non interactive visualization.
	 *
	 * @param parent a composite as parent
	 * @param style SWT.NONE can be used
	 */
	public NonInteractiveSimulationVisualisation_JFreeChartPlotComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		
		Map<String, Map<String, String>> resultsMap;
		
		resultsMap = Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().getResults();

		Activator.getSimulationCenter_NonInteractive().addPlot(this);
		initGUI(resultsMap, new HashSet<String>());
	}
	
	/**
	 * Inits the gui.
	 *
	 * @param resultsMap the results map
	 * @param selectedProperties the selected properties
	 */
	private void initGUI(Map<String, Map<String, String>> resultsMap, Set<String> selectedProperties) {
		try {
			FillLayout parentLayout = new FillLayout(org.eclipse.swt.SWT.HORIZONTAL);
			this.setLayout(parentLayout);
			this.layout();
			createPartControl(this, resultsMap, selectedProperties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the part control.
	 *
	 * @param parent the parent
	 * @param resultsMap the results map
	 * @param selectedProperties the selected properties
	 */
	private void createPartControl(Composite parent, Map<String, Map<String, String>> resultsMap, Set<String> selectedProperties) {

		propMap = new HashMap<String, XYSeries>();
		dataset = new XYSeriesCollection();
		dataset.setAutoWidth(true);
		dataSetSelectionChanged(selectedProperties);
		createSeriesMap(resultsMap);
		chart = ChartFactory.createXYLineChart("Simulation Results", "Time (s)", "Value (?)",
				dataset, PlotOrientation.VERTICAL, true, false, false);

		plot = chart.getXYPlot();
		plot.getDomainAxis().setAutoRange(true);
		plot.getDomainAxis().setLowerMargin(0.0);
		plot.getDomainAxis().setUpperMargin(0.0);
		@SuppressWarnings("unused")
		final ChartComposite frame = new ChartComposite(parent, SWT.NONE,
				chart, true);
	}
	

	/**
	 * All plotable properties will add to a properties map. Note: This is not the visualization step
	 *
	 * @param resultsMap the results map
	 */
	private void createSeriesMap(Map<String, Map<String, String>> resultsMap) {

//		dataset.removeAllSeries();

		for (String result : resultsMap.keySet()) {
			XYSeries series = new XYSeries(result);
			for (String time : resultsMap.get(result).keySet()) {
				series.add(new Double(time), new Double(resultsMap.get(result)
						.get(time)));
			}
//			if(result.equals("tank1.h") || result.equals("der(piContinuous1.x)")){
//				dataset.addSeries(series);
//			}
			propMap.put(result, series);
		}
	}

	/**
	 * Use this method if a new systems as been simulated.
	 *
	 * @param simulationResultsAsString the simulation results as string
	 * @param selectedProperties the selected properties
	 */
	private void newSimulation(Map<String, Map<String, String>> simulationResultsAsString, Set<String> selectedProperties){
		propMap.clear();
		dataSetSelectionChanged(selectedProperties);
		createSeriesMap(simulationResultsAsString);
		chart.setTitle("Simulation Results");
	}
	
	/**
	 * Add all selected properties to the visualization view
	 * Note: Performance check: Diese methode löscht erstmal alle series aus dem dataset um auch die veränderungen mit zu nehmen
	 * ansonsten hätte man zwei schleifen die man durch gehen muss (deselected, selected).
	 *
	 * @param propertiesToPlotOnView the properties to plot on view
	 */
	private void dataSetSelectionChanged(Set<String> propertiesToPlotOnView) {
		dataset.removeAllSeries();
		
		for(String selected : propertiesToPlotOnView)
			dataset.addSeries(propMap.get(selected));
	}

	/**
	 * use the argument "newsimulation" to signal that a new systems as been simulated
	 * use and empty string otherwise.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	@Override
	public void update(final Observable arg0, final Object arg1) {
		if(((String) arg1).equals("newsimulation")){
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					newSimulation(((SimulationResultManager_NonInteractive) arg0)
							.getResults(), ((SimulationResultManager_NonInteractive) arg0)
							.getSelectedProperties());
				}
			});
		}else{
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					dataSetSelectionChanged(((SimulationResultManager_NonInteractive) arg0)
							.getSelectedProperties());
				}
			});
		}
	}

}
