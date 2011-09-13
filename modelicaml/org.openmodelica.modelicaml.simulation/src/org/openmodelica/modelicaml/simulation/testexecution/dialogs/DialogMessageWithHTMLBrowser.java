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
package org.openmodelica.modelicaml.simulation.testexecution.dialogs;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.resource.NotFoundException;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.services.PapyrusServices;
import org.openmodelica.modelicaml.simulation.testexecution.actions.PlotTestResultsAction;

public class DialogMessageWithHTMLBrowser extends Dialog {

	private String title = "";
	private String location = "";
	private Browser browser;
	
	public DialogMessageWithHTMLBrowser(Shell parentShell,String title, String location) {
		super(parentShell);
		setBlockOnOpen(false);
        setShellStyle( SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP | SWT.SHELL_TRIM );
        this.title = title;
        this.location = location;
	}
	
	protected void configureShell(Shell shell) {
        super.configureShell(shell);
   		shell.setText(this.title);
   		shell.setSize(900, 700);
    }

	LocationListener locationListener = new LocationAdapter() {
	    public void changing(LocationEvent event) {
			String location = event.location;
			String decodedLocation = null;
			
			try {
				decodedLocation = URLDecoder.decode(location, "UTF-8").trim();
			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
				MessageDialog.openError(new Shell(), "Invalid Browser Link", "It is not possible to decode the link " + location);
			}
			
			if (decodedLocation != null && decodedLocation.trim().startsWith("locate")) {
				event.doit = false;	// don't change the page
				
				String[] splitted = decodedLocation.replaceFirst("locate:", "").split("#");
				
				String elementQName = splitted[0];
				for (Element  element : findElement(elementQName)) {
					PapyrusServices.locate(element);
				}
				
				String componentDotPath = "";
				if (splitted.length == 2) {
					componentDotPath = splitted[1];
					PapyrusServices.locateInComponentsTreeView(componentDotPath);
				}

			}
			else if (decodedLocation != null && decodedLocation.trim().endsWith("_res.xml")) {
				event.doit = false;	// don't change the page
				// Dialog for the plotting of variables
				PlotTestResultsAction plotAction = new PlotTestResultsAction();
				plotAction.setXMLFilePath(decodedLocation);
				plotAction.run(null);
			}
	    }
	};
	
	@Override
	protected Control createDialogArea(Composite parent) {
        final Composite composite = (Composite) super.createDialogArea(parent);

        try {
	    composite.setLayout(new FillLayout(SWT.HORIZONTAL));
	    browser = new Browser(composite, SWT.NONE);
	    browser.setUrl(location);
//	    browser.setLayout(new GridLayout());
//	    browser.setSize(composite.getSize().x, composite.getSize().y);
	    browser.setSize(composite.getShell().getClientArea().width, composite.getShell().getClientArea().height);
	    browser.addLocationListener(locationListener);
		
	    composite.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event e) {
				browser.setSize(composite.getSize().x, composite.getSize().y);
			}
		});

	    } catch (SWTError e) {
          MessageBox messageBox = new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK);
          messageBox.setMessage("Browser cannot be initialized.");
          messageBox.setText("Exit");
          messageBox.open();
          System.exit(-1);
       }
        return parent;
	}
	
	private EList<Element> findElement(String qName){
		EList<Element> elements = null; 
		UmlModel umlModel = UmlUtils.getUmlModel();
		try {
			EObject rootModel = umlModel.lookupRoot();
			if (rootModel instanceof Element) {
				ElementFinder ef = new ElementFinder(rootModel, qName);
				elements = ef.getElements();
			}
			
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return elements;
	}
}
