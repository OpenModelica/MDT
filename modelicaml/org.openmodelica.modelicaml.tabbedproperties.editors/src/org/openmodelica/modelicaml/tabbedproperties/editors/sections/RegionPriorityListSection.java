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
package org.openmodelica.modelicaml.tabbedproperties.editors.sections;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.core.utils.EditorUtils;
import org.eclipse.papyrus.profile.ImageManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;

// TODO: Auto-generated Javadoc
/**
 * The Class RegionPriorityListSection.
 */
public class RegionPriorityListSection extends AbstractPropertySection {

	/**
	 * Instantiates a new region priority list section.
	 */
	public RegionPriorityListSection() {
		super();
	}


	/** The S_ executionorderpriority. */
	private String S_EXECUTIONORDERPRIORITY = "ExecutionOrderPriority";
	
	/** The BEHAVIOUR. */
	private String BEHAVIOUR = "ModelicaML::ModelicaBehaviorConstructs";
	
	/** The PRIORITY. */
	private String PRIORITY = "priority";
	
	
	/** The set. */
	private Set<Entry<Integer,Region>> set = null;
	
	/** The tm. */
	private TreeMap<Integer, Region> tm =  new TreeMap<Integer, Region>(); 
	
	/** The index region map. */
	private HashMap<Integer,Region> indexRegionMap = new HashMap<Integer, Region>(); 
	
	/** The priorized list. */
	protected org.eclipse.swt.widgets.List priorizedList = null;
	
	/** The regions. */
	private EList<Region> regions = null;

	/** The current string array. */
	protected String[] currentStringArray = {} ;

	/**
	 * Listener for the add button.
	 */
	protected UpButtonlistener upButtonlistener = new UpButtonlistener();
	/**
	 * Listener for the add button.
	 */
	protected DownButtonlistener downButtonlistener = new DownButtonlistener();

	/** The stereotyp propertie name. */
	protected String stereotypPropertieName  = null;
	

	/**
	 * button that moves the element up.
	 */
	protected Button upButton;

	/**
	 * button that moves the element down.
	 */
	protected Button downButton;
	
	/** The overview label. */
	protected CLabel overviewLabel;
	
	/** The current region. */
	private Region currentRegion;
	
	/** The updown. */
	private Integer updown = -1;
	
	/** The selected uml element. */
	private Element selectedUmlElement;
	
	/**
	 * Creates the controls.
	 * 
	 * @param parent
	 *            the parent
	 * @param tabbedPropertySheetPage
	 *            the tabbed property sheet page
	 */
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		FormData data;


		overviewLabel = getWidgetFactory().createCLabel(composite, stereotypPropertieName+":"); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.width = 300;
		data.top = new FormAttachment(priorizedList, 0, SWT.TOP);
		overviewLabel.setLayoutData(data);
		overviewLabel.setText("Priority Order");
		this.priorizedList = this.getWidgetFactory().createList(composite, SWT.READ_ONLY |SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.width = 300;
		data.top = new FormAttachment(overviewLabel, 0);
		data.height = 100;
		this.priorizedList.setLayoutData(data);
			

		// ///////////////////////////////////////////////////////////////////////////
		// Create and place button vertically on the left side
		// Button : Add Element
		// Button Delete Element

		
		upButton = new Button(composite, SWT.PUSH);
		upButton.setVisible(true);
		upButton.setImage(ImageManager.IMG_UP);
	//	upButton.setToolTipText("Add a new element");

		data = new FormData();
		data.top = new FormAttachment(overviewLabel, 0);
		data.left = new FormAttachment(priorizedList, 5);
		upButton.setLayoutData(data);
		upButton.addMouseListener(upButtonlistener);
		
		
		downButton = new Button(composite, SWT.PUSH);
		downButton.setVisible(true);
		downButton.setImage(ImageManager.IMG_DOWN);
		//downButton.setToolTipText("Delete selected element(s)");
		data = new FormData();
		data.top = new FormAttachment(overviewLabel, 0);
		data.left = new FormAttachment(upButton, 5);
		downButton.setLayoutData(data);
		downButton.addMouseListener(downButtonlistener);


	}

	/*
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	@Override
	public void refresh() {
		if (!priorizedList.isDisposed()) {
			Element element = this.selectedUmlElement;
			if(element instanceof Region)
			{
				Stereotype stereotype = null;
				priorizedList.removeAll();
				
				currentRegion = (Region) element;
				State sourceState = currentRegion.getState();
				StateMachine sourceStateMachine = currentRegion.getStateMachine();
				if(sourceState != null)
				{
					regions = sourceState.getRegions();
				}
				else if (sourceStateMachine != null) {
					regions = sourceStateMachine.getRegions();
				}
				if(tm != null)
					tm.clear();
				if(indexRegionMap != null)
					indexRegionMap.clear();
				if(set != null)
					set.clear();
				for (final Region region : regions) {
					
					//if transition has not the stereotype state transition the stereotype will be applied
					if(!Utils.hasStereotype(region, BEHAVIOUR+"::"+S_EXECUTIONORDERPRIORITY))
					{
						stereotype = region.getApplicableStereotype(BEHAVIOUR+"::"+S_EXECUTIONORDERPRIORITY);
						
						final Stereotype stereotypeToBeApplied = region.getApplicableStereotype(BEHAVIOUR+"::"+S_EXECUTIONORDERPRIORITY);
						if(stereotype == null)
						{
							break;
						}
						
						//################## Store
						CompoundCommand cc = new CompoundCommand();
						// Get Papyrus editing domain.
						TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
						
						// Record command
						Command command = new RecordingCommand(editingDomain) {
							@Override
							protected void doExecute() {
								region.applyStereotype(stereotypeToBeApplied);	
								region.setValue(stereotypeToBeApplied, PRIORITY, new Integer(1));
							}
						};
						cc.append(command);
						// Execute command
						editingDomain.getCommandStack().execute(cc);
						//################## Store end 
						
					}
					
					stereotype = region.getAppliedStereotype(BEHAVIOUR+"::"+S_EXECUTIONORDERPRIORITY);
					final Stereotype stereotypeToBeEdited = region.getAppliedStereotype(BEHAVIOUR+"::"+S_EXECUTIONORDERPRIORITY);
					Assert.isNotNull(stereotype);
					if(stereotype == null)
					{
						break;
					}
					//get priority from transition
					Integer transitionPriority = (Integer)region.getValue(stereotype, PRIORITY);
					
					//if treemap already contains the same priority try a lower priority
					while(tm.containsKey(transitionPriority))
					{
						final Integer transitionPriorityCopy = transitionPriority;
						
						//################## Store
						CompoundCommand cc = new CompoundCommand();
						// Get Papyrus editing domain.
						TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
						
						// Record command
						Command command = new RecordingCommand(editingDomain) {
							@Override
							protected void doExecute() {
								region.setValue(stereotypeToBeEdited, PRIORITY, new Integer(transitionPriorityCopy+1));
							}
						};
						cc.append(command);
						// Execute command
						editingDomain.getCommandStack().execute(cc);
						//################## Store end 
						
						transitionPriority = (Integer)region.getValue(stereotype, PRIORITY);
					}
					//add priority and transition to treemap
					tm.put(transitionPriority, region);
					
				}
				Assert.isNotNull(stereotype);
				if(stereotype != null)
				{
					//convert treemap to set
					set =  tm.entrySet();
					
					//iterate over set
					for (Entry<Integer, Region> entry : set) {
						int index = priorizedList.getItemCount();
						
										
					    //add item to swt list
						//priority 1 is at position 0 in the priorized list
						priorizedList.add(entry.getValue().getQualifiedName().replaceFirst(entry.getValue().getRedefinitionContexts().get(0).getQualifiedName()+"::", ""));
						if(entry.getKey()!=index+1)
						{
							entry.getValue().setValue(stereotype, PRIORITY, new Integer(index));
						}
						//add index position and transition to indexRegionMap
						indexRegionMap.put(index, entry.getValue());

					}
					// select selected transition in the diagram or last selected element in the priorized list 
						for (int i = 0; i < priorizedList.getItemCount(); i++) {
							if(indexRegionMap.get(i).equals(currentRegion))
							{
								if(updown == i || updown == -1)
								{
									priorizedList.select(i);
								}
								else
								{
									priorizedList.select(updown);
								}
							}
						}
					}
	
					
					
				}
			}	
		
}
	




	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cea.papyrus.core.ui.properties.tabbed.PropertyViewSection#dispose()
	 */
	/**
	 * 
	 */
	public void dispose() {
		super.dispose();
		if (upButton != null && !upButton.isDisposed())
			upButton.removeMouseListener(upButtonlistener);
		if (downButton != null && !downButton.isDisposed())
			downButton.removeMouseListener(downButtonlistener);
}

	
	/**
	 * Sets the input.
	 * 
	 * @param part
	 *            the part
	 * @param selection
	 *            the selection
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {

		Object input = ((IStructuredSelection) selection).getFirstElement();
		// Get the selected element
        EObject selectedElement = ModelicaMLServices.adaptSelectedElement(input);
        if (selectedElement instanceof Element) {
        	this.selectedUmlElement = (Element)selectedElement;
		}
		
		super.setInput(part, selection);
		updown = -1;

	}

	
	/**
	 * action executed when the down button is pressed.
	 */
	public void upButtonPressed(){
		final int selection = priorizedList.getSelectionIndex();
		updown = selection;
		if(selection!=0)
		{
			final Region trans = indexRegionMap.get(new Integer(selection));
			final Region trans2 = indexRegionMap.get(new Integer(selection-1));
			
			final Stereotype stereotype = trans.getAppliedStereotype(BEHAVIOUR+"::"+S_EXECUTIONORDERPRIORITY);
			
			//################## Store
			CompoundCommand cc = new CompoundCommand();
			// Get Papyrus editing domain.
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			
			// Record command
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					trans.setValue(stereotype, PRIORITY, new Integer(selection));
					trans2.setValue(stereotype, PRIORITY, new Integer(selection+1));
				}
			};
			cc.append(command);
			// Execute command
			editingDomain.getCommandStack().execute(cc);
			//################## Store end 
			
			updown = selection-1;
			priorizedList.select(selection-1);
//			trans.eNotify(new NotificationImpl(PapyrusNotification.SET, null, null));
//			trans2.eNotify(new NotificationImpl(PapyrusNotification.SET, null, null));
		}
	}
	
	/**
	 * action executed when the down button is pressed.
	 */
	public void downButtonPressed(){
		final int selection = priorizedList.getSelectionIndex();
		updown = selection;
		if(selection+1<priorizedList.getItemCount())
		{
			final Region trans = indexRegionMap.get(new Integer(selection));
			final Region trans2 = indexRegionMap.get(new Integer(selection+1));
			
			final Stereotype stereotype = trans.getAppliedStereotype(BEHAVIOUR+"::"+S_EXECUTIONORDERPRIORITY);
			
			//################## Store
			CompoundCommand cc = new CompoundCommand();
			// Get Papyrus editing domain.
			TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
			
			// Record command
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					trans.setValue(stereotype, PRIORITY, new Integer(selection+2));
					//	indexRegionMap.put(selection+1, trans);
					trans2.setValue(stereotype, PRIORITY, new Integer(selection+1));
				}
			};
			cc.append(command);
			// Execute command
			editingDomain.getCommandStack().execute(cc);
			//################## Store end 
	
			updown = selection+1;
			priorizedList.select(selection+1);
//			trans.eNotify(new NotificationImpl(PapyrusNotification.SET, null, null));
//			trans2.eNotify(new NotificationImpl(PapyrusNotification.SET, null, null));
		
		}

		
	}
	

	

	
/**
 * The Class UpButtonlistener.
 */
private class UpButtonlistener implements MouseListener {

		
		/**
		 * Mouse double click.
		 * 
		 * @param e
		 *            the e
		 */
		public void mouseDoubleClick(MouseEvent e) {
			// do nothing
		}

		/**
		 * Mouse down.
		 * 
		 * @param e
		 *            the e
		 */
		public void mouseDown(MouseEvent e) {
			// do nothing
		}

		/**
		 * Mouse up.
		 * 
		 * @param e
		 *            the e
		 */
		public void mouseUp(MouseEvent e) {
			upButtonPressed();
			refresh();
		}
	}

/**
 * The Class DownButtonlistener.
 */
private class DownButtonlistener implements MouseListener {

	
	/**
	 * Mouse double click.
	 * 
	 * @param e
	 *            the e
	 */
	public void mouseDoubleClick(MouseEvent e) {
		// do nothing
	}

	/**
	 * Mouse down.
	 * 
	 * @param e
	 *            the e
	 */
	public void mouseDown(MouseEvent e) {
		// do nothing
	}

	/**
	 * Mouse up.
	 * 
	 * @param e
	 *            the e
	 */
	public void mouseUp(MouseEvent e) {
		downButtonPressed();
		refresh();
	}
}

}
