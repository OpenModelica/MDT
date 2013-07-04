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
	 * Note: Performance check: Diese methode löscht erstmal alle series aus dem dataset um auch die veränderungen mit zu nehmen
	 * ansonsten hätte man zwei schleifen die man durch gehen muss (deselected, selected).
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
