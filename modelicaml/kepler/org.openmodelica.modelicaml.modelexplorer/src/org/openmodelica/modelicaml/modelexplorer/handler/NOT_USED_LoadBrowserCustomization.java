package org.openmodelica.modelicaml.modelexplorer.handler;
///*
// * This file is part of OpenModelica.
// *
// * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
// * c/o Link�pings universitet, Department of Computer and Information Science,
// * SE-58183 Link�ping, Sweden.
// *
// * All rights reserved.
// *
// * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
// * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
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
// * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-2013
// *
// * Contributors: 
// *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
// */
//package org.openmodelica.modelicaml.modelexplorer.handler;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.eclipse.core.commands.AbstractHandler;
//import org.eclipse.core.commands.ExecutionEvent;
//import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.emf.common.util.EList;
//import org.eclipse.emf.ecore.EClass;
//import org.eclipse.emf.ecore.EClassifier;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.emf.ecore.EPackage;
//import org.eclipse.emf.facet.infra.browser.Messages;
//import org.eclipse.emf.facet.infra.browser.custom.MetamodelView;
//import org.eclipse.emf.facet.infra.browser.custom.TypeView;
//import org.eclipse.emf.facet.infra.browser.uicore.CustomizationManager;
//import org.eclipse.emf.facet.infra.facet.Facet;
//import org.eclipse.emf.facet.infra.facet.FacetSet;
//import org.eclipse.emf.facet.infra.facet.core.FacetSetCatalog;
//import org.eclipse.jface.window.Window;
//import org.eclipse.osgi.util.NLS;
//import org.eclipse.papyrus.infra.core.resource.ModelSet;
//import org.eclipse.papyrus.infra.core.resource.ModelUtils;
//import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageManager;
//import org.eclipse.papyrus.infra.core.services.ServiceException;
//import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
//import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
//import org.eclipse.papyrus.infra.emf.utils.ServiceUtilsForHandlers;
//import org.eclipse.papyrus.infra.services.semantic.service.SemanticService;
//import org.eclipse.papyrus.views.modelexplorer.Activator;
//import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
//import org.eclipse.papyrus.views.modelexplorer.dialog.PapyrusLoadBrowserCustomizationDialog;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Tree;
//import org.eclipse.ui.IViewPart;
//import org.eclipse.ui.handlers.HandlerUtil;
//import org.eclipse.ui.navigator.CommonNavigator;
//
///**
// * This is a class that launches the button load Customization. this code comes
// * from the {@link LoadCustomizationsDialog}
// */
//public class LoadBrowserCustomization extends AbstractHandler {
//
//	/**
//	 * 
//	 * 
//	 * @return the common navigator
//	 */
//	protected CommonNavigator getCommonNavigator() {
//		IViewPart part = org.eclipse.papyrus.views.modelexplorer.NavigatorUtils.findViewPart(ModelExplorerPageBookView.VIEW_ID);
//		// the part is only a book, retrieving correct page
//		if(part instanceof ModelExplorerPageBookView) {
//			IViewPart page = ((ModelExplorerPageBookView)part).getActiveView();
//			if(page instanceof CommonNavigator) {
//				return ((CommonNavigator)page);
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	public Object execute(ExecutionEvent event) throws ExecutionException {
//
//		if(Activator.getDefault().getCustomizationManager() != null) {
//			CustomizationManager customizationManager = Activator.getDefault().getCustomizationManager();
//			final List<MetamodelView> registeredCustomizations = customizationManager.getRegisteredCustomizations();
//
//			PapyrusLoadBrowserCustomizationDialog loadCustomizationsDialog;
//
//			Shell shell = HandlerUtil.getActiveShell(event);
//
//			try {
//				ServicesRegistry registry = ServiceUtilsForHandlers.getInstance().getServiceRegistry(event);
//				loadCustomizationsDialog = new PapyrusLoadBrowserCustomizationDialog(shell, registeredCustomizations, getMetamodels(registry));
//			} catch (ServiceException ex) {
//				Activator.log.error(ex);
//				loadCustomizationsDialog = new PapyrusLoadBrowserCustomizationDialog(shell, registeredCustomizations, Collections.<EPackage> emptyList());
//			}
//
//			if(Window.OK == loadCustomizationsDialog.open()) {
//				try {
//
//					customizationManager.clearCustomizations();
//					List<MetamodelView> selectedCustomizations = loadCustomizationsDialog.getSelectedCustomizations();
//					//before loading, clean all facet to prevent to let not interesting facets.
//					customizationManager.clearFacets();
//
//
//					// Always load facets corresponding to customizations
//					loadFacetsForCustomizations(selectedCustomizations, customizationManager);
//
//
//					for(MetamodelView metamodelView : selectedCustomizations) {
//						customizationManager.registerCustomization(metamodelView);
//					}
//					customizationManager.loadCustomizations();
//					if(getCommonNavigator() != null) {
//						Tree tree = getCommonNavigator().getCommonViewer().getTree();
//						customizationManager.installCustomPainter(tree);
//					}
//
//					//Save the current state of the customizations
//					org.eclipse.papyrus.infra.emf.Activator.getDefault().saveCustomizationManagerState();
//				} catch (final Exception e) {
//					Activator.log.error(e);
//				}
//			}
//			// load customizations defined as default through the customization
//			// extension
//			if(getCommonNavigator() != null) {
//				getCommonNavigator().getCommonViewer().refresh();
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @return the RessourceSet
//	 */
//	private ModelSet getModelSet() {
//		return ModelUtils.getModelSet();
//	}
//
//	/**
//	 * load the facets
//	 * 
//	 * @param customizations
//	 *        list of customization
//	 * @param customizationManager
//	 *        the Customization Manager
//	 */
//	protected void loadFacetsForCustomizations(final List<MetamodelView> customizations, final CustomizationManager customizationManager) {
//		final Set<Facet> referencedFacets = new HashSet<Facet>();
//		final Collection<FacetSet> facetSets = FacetSetCatalog.getSingleton().getAllFacetSets();
//
//		for(MetamodelView customization : customizations) {
//			String metamodelURI = customization.getMetamodelURI();
//			// find customized FacetSet
//			FacetSet customizedFacetSet = null;
//			if(metamodelURI != null) {
//				for(FacetSet facetSet : facetSets) {
//					if(metamodelURI.equals(facetSet.getNsURI())) {
//						customizedFacetSet = facetSet;
//						break;
//					}
//				}
//			}
//			if(customizedFacetSet == null) {
//				continue;
//			}
//
//			// find customized Facets
//			EList<TypeView> types = customization.getTypes();
//			for(TypeView typeView : types) {
//				String metaclassName = typeView.getMetaclassName();
//				Facet facet = findFacetWithFullyQualifiedName(metaclassName, customizedFacetSet);
//				if(facet != null) {
//					referencedFacets.add(facet);
//				} else {
//					Activator.log.warn(NLS.bind(Messages.BrowserActionBarContributor_missingRequiredFacet, new Object[]{ metaclassName, customizedFacetSet.getName(), customization.getName() }));
//				}
//			}
//
//			for(Facet referencedFacet : referencedFacets) {
//				customizationManager.loadFacet(referencedFacet);
//			}
//		}
//
//		//
//		// for modified facets
//		// customizationManager.getInstancesForMetaclasses().buildDerivationTree();
//		// customizationManager.getAppearanceConfiguration().touch();
//		// customizationManager.refreshDelayed(true);
//	}
//
//	/**
//	 * fin a facet from
//	 * 
//	 * @param metaclassName
//	 * @param customizedFacetSet
//	 * @return
//	 */
//	private Facet findFacetWithFullyQualifiedName(final String metaclassName, final FacetSet customizedFacetSet) {
//		EList<Facet> facets = customizedFacetSet.getFacets();
//		for(Facet facet : facets) {
//			String facetName = getMetaclassQualifiedName(facet);
//			if(metaclassName.equals(facetName)) {
//				return facet;
//			}
//		}
//		return null;
//	}
//
//	/** @return the qualified name of the given metaclass */
//	public static String getMetaclassQualifiedName(final EClassifier eClass) {
//		final ArrayList<String> qualifiedNameParts = new ArrayList<String>();
//		final StringBuilder builder = new StringBuilder();
//
//		EPackage ePackage = eClass.getEPackage();
//		while(ePackage != null) {
//			qualifiedNameParts.add(ePackage.getName());
//			ePackage = ePackage.getESuperPackage();
//		}
//
//		for(int i = qualifiedNameParts.size() - 1; i >= 0; i--) {
//			builder.append(qualifiedNameParts.get(i) + "."); //$NON-NLS-1$
//		}
//
//		builder.append(eClass.getName());
//
//		return builder.toString();
//	}
//
//	//	/**
//	//	 * Get the metmodel URI
//	//	 * **/
//	//@Unused
//	//	private String getMetamodelURI() {
//	//
//	//		try {
//	//			EList<EObject> contents = UmlUtils.getUmlResource(getModelSet()).getContents();
//	//			if(contents.size() > 0) {
//	//				EObject eObject = contents.get(0);
//	//				EClass eClass = eObject.eClass();
//	//				if(eClass != null) {
//	//					return eClass.getEPackage().getNsURI();
//	//				}
//	//			}
//	//		} catch (Exception e) {
//	//			Activator.log.error(e);
//	//		}
//	//		return ""; //$NON-NLS-1$
//	//	}
//
//	/**
//	 * Get the metmodel URI
//	 * **/
//	protected List<EPackage> getMetamodels(ServicesRegistry serviceRegistry) {
//		List<EPackage> ePackages = new ArrayList<EPackage>();
//
//		/*
//		 * we look for the current editors, because their are represented in the model explorer
//		 * using specific facet and uiCustom. (see bug 359692)
//		 */
//		IPageManager pageMngr = null;
//		try {
//			pageMngr = ServiceUtils.getInstance().getIPageManager(serviceRegistry);
//			List<Object> pages = pageMngr.allPages();
//			for(int i = 0; i < pages.size(); i++) {
//				if(pages.get(i) instanceof EObject) {
//					EPackage ePackage = ((EObject)pages.get(i)).eClass().getEPackage();
//					if(!ePackages.contains(ePackage)) {
//						ePackages.add(ePackage);
//					}
//				}
//			}
//		} catch (ServiceException e) {
//			Activator.log.error(e);
//		}
//
//		try {
//			SemanticService semantic = serviceRegistry.getService(SemanticService.class);
//			for(EObject rootElement : semantic.getSemanticRoots()) {
//				EClass eClass = rootElement.eClass();
//				if(eClass != null) {
//					EPackage ePackage = eClass.getEPackage();
//					if(!ePackages.contains(ePackage)) {
//						ePackages.add(eClass.getEPackage());
//					}
//				}
//			}
//		} catch (ServiceException ex) {
//			Activator.log.error(ex);
//		}
//		return ePackages;
//	}
//
//}
