/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2006, Adrian Pop, adrpo@ida.liu.se
 * 
 * created Februrary 19, 2006
 */

package org.modelica.mdt.ui.view;

import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;
import org.modelica.mdt.ui.UIPlugin;
import org.modelica.mdt.ui.console.ModelicaTextConsole;
import org.modelica.mdt.ui.console.ModelicaTextConsoleViewer;

/**
 * @author Adrian Pop
 * The ModelicaConsole is used to display the Modelica compiler's output
 * @see ViewPart
 */
public class ModelicaConsoleView extends ViewPart 
{

	private ModelicaTextConsoleViewer viewer = null;
	private ModelicaTextConsole console = null; 
	public static final String ID = "org.modelica.mdt.ui.view.ModelicaConsoleView";

	/**
	 * The constructor.
	 */
	public ModelicaConsoleView() 
	{		
		console = UIPlugin.getDefault().getModelicaTextConsole();
	}
	
	public IDocument getDocument() 
	{ 
		return console.getDocument(); 
	} 
	public ModelicaTextConsole getConsole() 
	{ 
		return console; 
	} 
	
	public ModelicaTextConsoleViewer getViewer(Composite parent)
	{
		viewer = new ModelicaTextConsoleViewer(parent, console);
		return viewer;
	}

	/**
	 * Insert the method's description here.
	 * @see ViewPart#createPartControl
	 */
	public void createPartControl(Composite parent)  
	{
		viewer = getViewer(parent);
		viewer.setDocument(getDocument());
		viewer.setEditable(true);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() 
	{
		viewer.getControl().setFocus();
	}

}

