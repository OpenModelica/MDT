/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Link�pings universitet, Department of Computer and Information Science,
 * SE-58183 Link�ping, Sweden.
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.simulation.testexecution.dialogs;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashSet;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
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
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.PapyrusServices;
import org.openmodelica.modelicaml.simulation.testexecution.actions.PlotResultsAction;

public class DialogMessageWithHTMLBrowser extends Dialog {

	private String title = "";
	private String location = ""; // Absolute path to the html report file
	private Browser browser;
	
	public DialogMessageWithHTMLBrowser(Shell parentShell,String title, String location) {
		super(parentShell);
		setBlockOnOpen(false);
//		setShellStyle( SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP | SWT.SHELL_TRIM );
		setShellStyle( SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.SHELL_TRIM );

        this.title = title;
        this.location = location;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
	}
	
	protected void configureShell(Shell shell) {
        super.configureShell(shell);
   		shell.setText(this.title);
   		shell.setSize(1000, 700);
    }

	LocationListener locationListener = new LocationAdapter() {
	    public void changing(LocationEvent event) {
			String location = event.location;
			String decodedLocation = null;
			
			try {
				decodedLocation = URLDecoder.decode(location, "UTF-8").trim();
			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
				MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Invalid Browser Link", "It is not possible to decode the link " + location);
			}
			
			if (decodedLocation != null && decodedLocation.trim().startsWith("locate")) {
				event.doit = false;	// don't change the page
				
				//String[] splitted = decodedLocation.replaceFirst("locate:", "").split("#");
				String[] splitted = decodedLocation.replaceFirst("locate:", "").split(Constants.linkDelimiter);
				
				String elementQName = splitted[0];
				if (elementQName != null) {
					for (Element  element : findElement(elementQName)) {
//						PapyrusServices.locateWithReselection(element);
						PapyrusServices.locateInModelExplorer(element, true);
					}
				}
				
				String componentDotPath = "";
				if (splitted.length == 2) {
					componentDotPath = splitted[1];
					PapyrusServices.locateInComponentsTreeView(componentDotPath);
				}

			}
			else if (decodedLocation != null &&  decodedLocation.trim().startsWith("plot")) {
				
//				String[] splitted = decodedLocation.split("\\?");
//				decodedLocation = decodedLocation.replaceFirst("plot:", "").trim();
				String[] splitted = decodedLocation.replaceFirst("plot:", "").split(Constants.linkDelimiter);
				decodedLocation = splitted[0];
				String variablePath = null;
				if (splitted.length > 1) {
					variablePath = splitted[1];
				}
				/*
				 * The report contains the model name as parameter. 
				 * We need to translated them to corresponding result file name 
				 */
				decodedLocation  = ModelicaMLServices.getSimulationResultsFileName(decodedLocation);
				
				event.doit = false;	// don't change the page
				String sessionFolderAbsolutePath = geSimulationFilesFolderPath();
				if (sessionFolderAbsolutePath != null) {
					// Dialog for the plotting of variables
					PlotResultsAction plotAction = new PlotResultsAction();
					plotAction.setFilePath(sessionFolderAbsolutePath + "/" + decodedLocation);
					if (variablePath != null) {
						HashSet<String> preselectedVariables = new HashSet<String>();
						preselectedVariables.add(variablePath);
						plotAction.setPreSelectedVariablesToPlot(preselectedVariables);
					}
					plotAction.run(null);
				}
				else { // TODO: report 
//					MessageDialog.openError(new Shell(), "Error", "Could not find the results file for " + decodedLocation);
				}
			}
	    }
	};
	
	
	private String geSimulationFilesFolderPath(){
		IFileSystem fileSystem = EFS.getLocalFileSystem();
//		IFileStore reportFile = fileSystem.getStore(URI.create("file:/" + omcTempWorkingFolder + "/" + model.qualifiedName + ".exe"));
		String[] splitted = this.location.split("\\?"); // remove the URL get parameters 
		IFileStore reportFile = fileSystem.getStore(URI.create(splitted[0]));
		
		IFileInfo reportFileInfo = reportFile.fetchInfo();
		if (reportFileInfo.exists()) {
			IFileStore reportFolder = reportFile.getParent();
			if (reportFolder != null) {
				String path = reportFolder.toURI().getPath();
				if (path.startsWith("\\") || path.startsWith("/")) {
						return path.substring(1);
				}
				else {
					return path;
				}
//				IFileStore sessionFolder = reportFolder.getParent();
//				if (sessionFolder != null) {
//					String path = sessionFolder.toURI().getPath();
//					if (path.startsWith("\\") || path.startsWith("/")) {
//							return path.substring(1);
//					}
//					else {
//						return path;
//					}
//				}
			}
			
		}
		return null;
	}
	
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
          MessageBox messageBox = new MessageBox(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.ICON_ERROR | SWT.OK);
          messageBox.setMessage("Browser cannot be initialized.");
          messageBox.setText("Exit");
          messageBox.open();
          System.exit(-1);
       }
        return parent;
	}
	
	private HashSet<Element> findElement(String qName){
		HashSet<Element> elements = null; 
		UmlModel umlModel = UmlUtils.getUmlModel();
		try {
			EObject rootModel = umlModel.lookupRoot();
			if (rootModel instanceof Element) {
				ElementFinder ef = new ElementFinder(rootModel, qName);
				elements = ef.getElements();
			}
			if (elements == null) { // no element was found
				MessageDialog.openError(getParentShell(), "Error Locating Element", "The element '" + qName + "' was not found.");
			}
			
		} catch (NotFoundException e) {
//			e.printStackTrace();
			MessageDialog.openError(getParentShell(), "Error Locating Element", "Could not access the UML model. Please make sure that the Papyrus UML model is open and the editor is active.");
		}
		
		return elements;
	}

}
