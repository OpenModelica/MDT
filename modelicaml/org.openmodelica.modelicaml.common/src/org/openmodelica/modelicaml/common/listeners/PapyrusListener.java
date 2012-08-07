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
package org.openmodelica.modelicaml.common.listeners;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.infra.core.listenerservice.IPapyrusListener;
import org.eclipse.papyrus.infra.core.resource.uml.ExtendedUmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
//import org.eclipse.papyrus.core.listenerservice.IPapyrusListener;
//import org.eclipse.papyrus.resource.uml.ExtendedUmlModel;
//import org.eclipse.papyrus.resource.uml.UmlUtils;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving papyrus events. The class that is
 * interested in processing a papyrus event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addPapyrusListener<code> method. When
 * the papyrus event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see PapyrusEvent
 */
public class PapyrusListener implements IPapyrusListener {
	
	// the uml model that is open in Papyrus 
	private ExtendedUmlModel umlModel = null;
	
	// the uml model that was modified
	private Resource umlModelResource = null;
	
	public PapyrusListener() {
	
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.papyrus.core.listenerservice.IPapyrusListener#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification notification) {
	
//		System.err.println(notification);
//		System.err.println("eventType: " + notification.getEventType());
//		System.err.println("Notifier: " + notification.getNotifier());

// ############# Examples of notifications:	start

//		CHANGE Stereotype value event
//		eventType: 1		
//		org.eclipse.emf.ecore.impl.ENotificationImpl@1ea320f (eventType: SET, position: -1, notifier: org.eclipse.emf.ecore.impl.DynamicEObjectImpl@c8084d (eClass: org.eclipse.emf.ecore.impl.EClassImpl@11dca9 (name: Component) (instanceClassName: null) (abstract: false, interface: false)), feature: org.eclipse.emf.ecore.impl.EAttributeImpl@41d471 (name: final) (ordered: false, unique: false, lowerBound: 1, upperBound: 1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: false, unsettable: false, derived: false) (iD: false), oldValue: true, newValue: false, isTouch: false, wasSet: true)

//		CHANGE Element value event
//		eventType: 22
//		org.eclipse.papyrus.diagram.common.editparts.PapyrusStereotypeListener$StereotypeCustomNotification@7ca2da (eventType: 22, position: -1, notifier: org.eclipse.uml2.uml.internal.impl.PropertyImpl@939bdb (name: source, visibility: <unset>) (isLeaf: false) (isStatic: false) (isOrdered: false, isUnique: true, isReadOnly: false) (isDerived: false, isDerivedUnion: false, aggregation: composite), feature: org.eclipse.emf.ecore.impl.EReferenceImpl@e0420b (name: eAnnotations) (ordered: true, unique: true, lowerBound: 0, upperBound: -1) (changeable: true, volatile: false, transient: false, defaultValueLiteral: null, unsettable: false, derived: false) (containment: true, resolveProxies: false), oldValue: null, newValue: org.eclipse.emf.ecore.impl.DynamicEObjectImpl@c8084d (eClass: org.eclipse.emf.ecore.impl.EClassImpl@11dca9 (name: Component) (instanceClassName: null) (abstract: false, interface: false)), isTouch: false, wasSet: false)
		
// 		SAVE the UML model event
//		org.eclipse.emf.common.notify.impl.NotifyingListImpl$1@3c56d2 (eventType: REMOVE_MANY, position: -1, notifier: org.eclipse.uml2.uml.internal.resource.UMLResourceImpl@e5f1d uri='platform:/resource/modelicaml.example.twotankssystem/model.uml', feature: null, oldValue: [], newValue: null, isTouch: false, wasSet: false)
//		eventType: 1
//		org.eclipse.emf.ecore.resource.impl.ResourceImpl$11@1bf73a9 (eventType: SET, touch: true, position: -1, notifier: org.eclipse.uml2.uml.internal.resource.UMLResourceImpl@e5f1d uri='platform:/resource/modelicaml.example.twotankssystem/model.uml', feature: null, oldValue: false, newValue: false, isTouch: true, wasSet: false)
//		org.eclipse.emf.ecore.resource.impl.ResourceImpl$3@fd379f (eventType: SET, position: -1, notifier: org.eclipse.uml2.uml.internal.resource.UMLResourceImpl@e5f1d uri='platform:/resource/modelicaml.example.twotankssystem/model.uml', feature: null, oldValue: 1297337319666, newValue: 1297345056911, isTouch: false, wasSet: false)

// ############# Examples of notifications: end

		if (notification.getNotifier() instanceof Element && ((Element) notification.getNotifier()).getModel() != null && ((Element) notification.getNotifier()).getModel().eResource() != null) {
			
			umlModelResource = ((Element) notification.getNotifier()).getModel().eResource();
			
			try {
				umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}

			if (umlModel != null) {
				//String projectName = umlModel.getResourceURI().segment(1);
//				String projectName = umlModel.getResource().getURI().segment(1);
//				IWorkspace workspace = ResourcesPlugin.getWorkspace();
//				IWorkspaceRoot root = workspace.getRoot();
//				iProject = root.getProject(projectName);
				
//				System.err.println("n.isModified(): " + umlModelResource.isModified());
//				System.err.println("Current model: " + UmlUtils.getUmlModel().getResourceURI());
//				System.err.println("Modified model U " + r.getURI());
				
				// check if the model that was modified is the ModelicaML model that is open in Papyrus
				if (umlModel.getResourceURI().equals(umlModelResource.getURI() ) ) {

//					System.err.println("n.isModified(): " + umlModelResource.isModified());
//					System.err.println("Current model: " + UmlUtils.getUmlModel().getResourceURI());
//					System.err.println("Modified model U " + umlModel.getResourceURI());
					
					// remember the model resource and time of modification
					Long timeStamp = System.currentTimeMillis();
					ModelicaMLServices.modelModificationStamp.put(umlModel.getResource(), timeStamp);
//					System.err.println("model modificaiton stamp: " + timeStamp);
				}
			}
			
		}
//		if (notification.getNotifier() instanceof UMLResource) {
//			umlModelResource = (UMLResource) notification.getNotifier();
//			umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();
//		}
	}
	
}
