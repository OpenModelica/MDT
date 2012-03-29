package org.openmodelica.modelicaml.simulation.view.noninteractive;
///*
// * This file is part of OpenModelica.
// *
// * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
// * c/o Linköpings universitet, Department of Computer and Information Science,
// * SE-58183 Linköping, Sweden.
// *
// * All rights reserved.
// *
// * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
// * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
// * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
// * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
// *
// * The OpenModelica software and the Open Source Modelica
// * Consortium (OSMC) Public License (OSMC-PL) are obtained
// * from OSMC, either from the above address,
// * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
// * http://www.openmodelica.org, and in the OpenModelica distribution. 
// * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
// *
// * This program is distributed WITHOUT ANY WARRANTY; without
// * even the implied warranty of  MERCHANTABILITY or FITNESS
// * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
// * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
// *
// * See the full OSMC Public License conditions for more details.
// *
// * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
// *
// * Contributors: 
// *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
// *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
// */
//package org.openmodelica.modelicaml.simulation.view.noninteractive;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.ui.part.ViewPart;
//
//// TODO: Auto-generated Javadoc
///**
// * The Class NonInteractiveSimulationVisualisationView.
// */
//public class NonInteractiveSimulationVisualisationView extends ViewPart {
//	
//	/** The composite1. */
//	private Composite composite1;
//	
//	/** The composite visualisation. */
//	private Composite compositeVisualisation;
//	
//	/**
//	 * Instantiates a new non interactive simulation visualisation view.
//	 */
//	public NonInteractiveSimulationVisualisationView() {
//		// TODO Auto-generated constructor stub
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
//	 */
//	@Override
//	public void createPartControl(Composite parent) {
//		 composite1 = new Composite(parent, SWT.NULL);
//	        GridLayout composite1Layout = new GridLayout();
//	        composite1.setLayout(composite1Layout);
//		{
//			compositeVisualisation = new NonInteractiveSimulationVisualisation_JFreeChartPlotComposite(composite1, SWT.NONE);
////        	compositeVisualisation = new CopyOfInteractiveSimulationVisualisation_JFreeChartPlotComposite(composite1, SWT.NONE);
//        	FillLayout compositeVisualisationLayout = new FillLayout(org.eclipse.swt.SWT.HORIZONTAL);
//        	GridData compositeVisualisationLData = new GridData();
//        	compositeVisualisationLData.horizontalAlignment = GridData.FILL;
//        	compositeVisualisationLData.verticalAlignment = GridData.FILL;
//        	compositeVisualisationLData.grabExcessHorizontalSpace = true;
//        	compositeVisualisationLData.grabExcessVerticalSpace = true;
//        	compositeVisualisation.setLayoutData(compositeVisualisationLData);
//        	compositeVisualisation.setLayout(compositeVisualisationLayout);
//        }
//
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
//	 */
//	@Override
//	public void setFocus() {
//		// TODO Auto-generated method stub
//
//	}
//
//}
