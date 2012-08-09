package org.openmodelica.modelicaml.helper.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.experimental.chart.swt.ChartComposite;

public class QuickPlotComposite extends org.eclipse.swt.widgets.Composite {

	private XYSeriesCollection dataset;
	private JFreeChart chart;
	private XYPlot plot;
	public static final String timeName = "time";

	
	public QuickPlotComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
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
	
	public void addValues(final XYSeries series) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				
				if (series != null) {
					dataset.addSeries(series);
				}
			}
		});
	}
	
	public void removeValues(final XYSeries series) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {

				if (series != null) {
					dataset.removeSeries(series);
				}
			}
		});
	}
}
