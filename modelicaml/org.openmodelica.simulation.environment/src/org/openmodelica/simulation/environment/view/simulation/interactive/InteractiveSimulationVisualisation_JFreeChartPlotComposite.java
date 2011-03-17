/*
 * 
 */
package org.openmodelica.simulation.environment.view.simulation.interactive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

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
import org.openmodelica.simulation.core.models.modelica.ModelicaProperty;
import org.openmodelica.simulation.core.simulationdata.SimulationResultManager_Interactive;
import org.openmodelica.simulation.environment.Activator;

// TODO: Auto-generated Javadoc
/**
 * The Class InteractiveSimulationVisualisation_JFreeChartPlotComposite.
 */
public class InteractiveSimulationVisualisation_JFreeChartPlotComposite extends org.eclipse.swt.widgets.Composite implements Observer{

	/** The prop map. */
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
	 * Instantiates a new interactive simulation visualisation_ j free chart plot composite.
	 *
	 * @param parent the parent
	 * @param style the style
	 */
	public InteractiveSimulationVisualisation_JFreeChartPlotComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		
		Activator.getSimulationCenter_Interactive().addPlot(this);
		
		initGUI();
	}
	
	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		try {
			FillLayout parentLayout = new FillLayout(org.eclipse.swt.SWT.HORIZONTAL);
			this.setLayout(parentLayout);
			this.layout();
			createPartControl(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the part control.
	 *
	 * @param parent the parent
	 */
	private void createPartControl(Composite parent) {
		// compositeGroups = new Composite(parent, SWT.NULL);
		// compositeGroups.setLayout(new GridLayout(4, false));
		// JFreeChart chart = createChart(createDataset());

		propMap = new HashMap<String, XYSeries>();
		dataset = new XYSeriesCollection();
		dataset.setAutoWidth(true);

		// ************************************** TODO EXAMPLE **************************************
		
		ArrayList<ModelicaProperty> propertyList = (ArrayList<ModelicaProperty>) Activator
				.getSimulationCenter_Interactive()
					.getPlotProperties();

		createSeriesMap(propertyList);
		// **************************************

		chart = ChartFactory.createXYLineChart("Interactive Simulation", "Time (s)", "Level (m)",
				dataset, PlotOrientation.VERTICAL, true, false, false);

		plot = chart.getXYPlot();
		plot.getDomainAxis().setAutoRange(true);
		plot.getDomainAxis().setLowerMargin(0.0);
		plot.getDomainAxis().setUpperMargin(0.0);
		
		@SuppressWarnings("unused")
		final ChartComposite frame = new ChartComposite(parent, SWT.NONE,
				chart, true);
		
		if(!Activator.getSimulationCenter_Interactive().getSimulationStatus().equals(""))newSimulation();
	}
	
	/**
	 * Creates the series map.
	 *
	 * @param propertyList the property list
	 */
	private void createSeriesMap(ArrayList<ModelicaProperty> propertyList){
		
		for(ModelicaProperty mp : propertyList){
			XYSeries series = new XYSeries(mp.getName());
			series.add(0,(Number)mp.getPrimitiveValue());
			propMap.put(mp.getName(), series);
			dataset.addSeries(series);
		}
	}

	/**
	 * New simulation.
	 */
	private void newSimulation(){
		propMap.clear();
		maxTimeInPlot = 0.0;
		dataSetSelectionChanged();
		chart.setTitle("Interactive Simulation");
	}
	
	/**
	 * Add all selected properties to the visualization view
	 * Note: Performance check: Diese methode l�scht erstmal alle series aus dem dataset um auch die ver�nderungen mit zu nehmen
	 * ansonsten h�tte man zwei schleifen die man durch gehen muss (deselected, selected).
	 *
	 */
	private void dataSetSelectionChanged() {
		dataset.removeAllSeries();
		
		ArrayList<ModelicaProperty> propertyList = (ArrayList<ModelicaProperty>) Activator
		.getSimulationCenter_Interactive()
			.getPlotProperties();

		createSeriesMap(propertyList);
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(final Observable o, final Object arg1) {
		if(arg1 instanceof String && ((String) arg1).equals("newsimulation")){
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					newSimulation();
				}
			});
		}
		
		else{
			final Object actTime = arg1;
			if (arg1 instanceof Double && ((Double) actTime) < maxTimeInPlot) {
				for (final String fullQualifiedName : propMap.keySet()) {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							try {
								XYSeries tempxyser = propMap.get(fullQualifiedName)
								.createCopy(0, ((Double) actTime).intValue());
								Display.getDefault().readAndDispatch();
								dataset
								.removeSeries(propMap
										.get(fullQualifiedName));
								propMap.put(fullQualifiedName, tempxyser);
								dataset.addSeries(tempxyser);
							} catch (CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
				maxTimeInPlot = (Double) actTime;
				// System.out.println("MAXTIME: " + maxTime);
			} else { //The simulation is running and values will being added to the plot
				for (final String fullQualifiedName : propMap.keySet()) {
					Display.getDefault().asyncExec(new Runnable() { //TODO [201010] asyncExec -> Jobs
						public void run() {
							Display.getDefault().readAndDispatch();
							maxTimeInPlot = (Double) actTime;
							propMap.get(fullQualifiedName)
							.addOrUpdate(
									(Double) actTime,
									((SimulationResultManager_Interactive) o)
									.getValueForFullQualifiedName(
											(Double) actTime,
											fullQualifiedName));
							
							Double stepTime =  new Double(Activator.getSimulationCenter_Interactive().getSelectedSimulationSessionConfiguration().getSimSetting().getSimStepTime());
							double minRage = 0.0;
							if(maxTimeInPlot-(stepTime*50) <= 0) minRage = 0d;
							else minRage = maxTimeInPlot-(stepTime*50);
							plot.getDomainAxis().setRange(minRage, maxTimeInPlot);
						}
					});
				}
			}
		}
		
	}

}
