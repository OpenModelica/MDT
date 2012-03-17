package org.openmodelica.modelicaml.simulation.plot;

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
import org.openmodelica.modelicaml.simulation.Activator;

public class JFreeChartPlotComposite extends org.eclipse.swt.widgets.Composite implements Observer{

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
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
		
	/**
	* Overriding checkSubclass allows this class to extend org.eclipse.swt.widgets.Composite
	*/	
	protected void checkSubclass() {
	}
	
	/**
	* Auto-generated method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	
	public JFreeChartPlotComposite(org.eclipse.swt.widgets.Composite parent, int style) {
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
