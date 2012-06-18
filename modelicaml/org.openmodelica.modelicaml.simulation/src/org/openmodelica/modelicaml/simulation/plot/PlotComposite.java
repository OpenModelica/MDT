package org.openmodelica.modelicaml.simulation.plot;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.openmodelica.modelicaml.simulation.simresults.ReadMatlab4;

public class PlotComposite extends org.eclipse.swt.widgets.Composite {

	private XYSeriesCollection dataset;
	private JFreeChart chart;
	private XYPlot plot;
	
	private ReadMatlab4 reader;
	
	public static final String timeName = "time";
	
	public PlotComposite(org.eclipse.swt.widgets.Composite parent, int style, ReadMatlab4 reader) {
		super(parent, style);
		this.reader = reader;
		initGUI();
	}

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
	
	private void createPartControl(Composite parent) {

		dataset = new XYSeriesCollection();
		dataset.setAutoWidth(true);
		chart = ChartFactory.createXYLineChart("", timeName, "", dataset, PlotOrientation.VERTICAL, true, false, false);

		plot = chart.getXYPlot();
		plot.getDomainAxis().setAutoRange(true);
		plot.getDomainAxis().setLowerMargin(0.0);
		plot.getDomainAxis().setUpperMargin(0.0);
		plot.setWeight(1);
		
		@SuppressWarnings("unused")
		final ChartComposite frame = new ChartComposite(parent, SWT.NONE, chart, true);
	}
	
	public void addValues(final String varPath) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				
				XYSeries series = getSerie(varPath);
				if (series != null) {
					dataset.addSeries(series);
				}
			}
		});
	}
	
	public void removeValues(final String varPath) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {

				XYSeries series = getSerie(varPath);
				if (series != null) {
					dataset.removeSeries(series);
				}
			}
		});
	}
	
	private XYSeries getSerie(String varPath){
		XYSeries series = null;
		if (reader != null) {
			double[] time;
			double[] values;
			try {
				time = reader.read_vals(timeName);
				values = reader.read_vals(varPath);

				series = new XYSeries(varPath);
				
				if (time != null && values != null) {
					for (int i = 0; i < time.length; i++) {
						double t = time[i];
						series.add(t, values[i]);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				MessageDialog.openError(new Shell(), "Plotting Error", "Cannot read the values for '"+varPath+"'");
			}
		}
		else {
			MessageDialog.openError(new Shell(), "Plotting Error", "Cannot read the simulation file for '"+varPath+"'");
		}
		return series;
	}

}
