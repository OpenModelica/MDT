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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.development.profile.update.helper.dialogs;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.services.EditorServices;


public class ResultsUnhandledElementsDialogHTML extends Dialog {

	private String title = "";
	private Browser browser;
	protected Image image = null;
	
	private List<EObject> consideredElements;
	private List<Element> unhandeledElements;
	private HashMap<String, Element> unhandeledElementsMap = new HashMap<String, Element>();

	
	public ResultsUnhandledElementsDialogHTML(
			Shell parentShell, 
			String title,
			Image image,
			List<EObject> consideredElements,
			List<Element> unhandeledElements) {
		
		super(parentShell);
		setBlockOnOpen(false);
//		setShellStyle( SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP | SWT.SHELL_TRIM );
		setShellStyle( SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.SHELL_TRIM );
		
        this.title = title;
		this.image = image;
		this.consideredElements = consideredElements;
        this.unhandeledElements = unhandeledElements;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		
	}
	
	protected void configureShell(Shell shell) {
        super.configureShell(shell);
   		shell.setText(this.title);
   		shell.setImage(this.image); 
   		shell.setSize(700, 400);
    }

	LocationListener locationListener = new LocationAdapter() {
	    public void changing(LocationEvent event) {
			String location = event.location;
			String decodedLocation = null;
			
			try {
				decodedLocation = URLDecoder.decode(location, "UTF-8").trim().replaceAll("about:", "");
				
			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
				MessageDialog.openError(
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
						"Invalid Browser Link", "It is not possible to decode the link " + location);
			}
			
			if (decodedLocation != null) {
				event.doit = false;	// don't change the page

				// locate in Papyrus
				Element element = unhandeledElementsMap.get(decodedLocation);
				EditorServices.locateInModelExplorer(element, false); // locate in Papyrus
				
				// set focus back to browser
				getShell().setFocus();
			}
	    }
	};
	
	
	@Override
	protected Control createDialogArea(Composite parent) {
        final Composite composite = (Composite) super.createDialogArea(parent);

        try {
	    
	        composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		    browser = new Browser(composite, SWT.NONE);

		    // HTML source
		    String html = "<html><head>"
		    		+ "<title>Unhandled elements</title>"
		    		+ "<style type=\"text/css\">body {color: #000000; font-size:11px; color:#000000; font-family:Verdana;}</style>"
		    		+ "</head>"
		    		+ "<body>";
		    int numberOfHandledElements = (consideredElements.size() - unhandeledElements.size());
		    
		    String plural = "";
		    if (numberOfHandledElements > 0) {
		    	
		    	if (numberOfHandledElements > 1) {
					plural = "s";
				}
			    html += "For <strong>" +  numberOfHandledElements
			    		+ "</strong> element"+plural+" appropriate stereotype"+plural+" could be identified. <br /><br />";
			}
		    
		    if (this.unhandeledElements.size() > 0) {
		    	
		    	plural = "";
		    	if (this.unhandeledElements.size() > 1) {
					plural = "s";
				}
		    	
		    	html += "For the following <strong>"+this.unhandeledElements.size()+"</strong> element"+plural+" "
		    			+ "<strong>no</strong> appropriate streotype could be found:";
		    	
			    html += "<ul>";
			    
				for (int i = 0; i < this.unhandeledElements.size(); i++) {
				
					final Element element = unhandeledElements.get(i);
					String XMIid = element.eResource().getURIFragment(element);
					unhandeledElementsMap.put(XMIid, element);
					
					String qName = ""; 
					String linkString =  "<span style=\"color:grey;\">" + getMetaClassName(element) + "</span> ";
					
					html += "<li>";
					if (element instanceof NamedElement) {
						qName = ((NamedElement)element).getQualifiedName();
						linkString += "<a href=\""+XMIid+"\">" + ((NamedElement)element).getName() + "</a>";
					}
					else {
						// get the owner name
						Element owner = element.getOwner();
						if (owner instanceof NamedElement) {
							qName = ((NamedElement)owner).getQualifiedName();
						}
						
						linkString += "<a href=\""+XMIid+"\">" + getMetaClassName(element) + "</a>";
					}
					
					html += linkString + "<ul><li><span style=\"color:grey;\">("+qName.trim()+")</span></li></ul>";
					html += "</li>";
				}	
				
				html += "</ul>";
			}
		    
			html += "</body></html>";
		    
			
		    browser.setText(html);
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
	
	private String getMetaClassName(EObject selectedElement){
		return selectedElement.eClass().getName() + ": ";
	}

}
