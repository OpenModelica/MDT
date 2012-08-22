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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.modelexplorer.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.facet.infra.browser.Messages;
import org.eclipse.emf.facet.infra.browser.custom.MetamodelView;
import org.eclipse.emf.facet.infra.browser.custom.TypeView;
import org.eclipse.emf.facet.infra.browser.custom.ui.dialogs.LoadCustomizationsDialog;
import org.eclipse.emf.facet.infra.browser.uicore.CustomizationManager;
import org.eclipse.emf.facet.infra.facet.Facet;
import org.eclipse.emf.facet.infra.facet.FacetSet;
import org.eclipse.emf.facet.infra.facet.core.FacetSetCatalog;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageMngr;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.DiResourceSet;
import org.eclipse.papyrus.infra.core.utils.EditorUtils;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.views.modelexplorer.Activator;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.openmodelica.modelicaml.common.constants.Constants;

/**
 * This is a class that launches the button load Customization. this code comes
 * from the {@link LoadCustomizationsDialog}
 */
@SuppressWarnings({ "restriction", "deprecation" })
public class LoadBrowserCustomization extends AbstractHandler {

	/**
	 * 
	 * 
	 * @return the common navigator
	 */
	protected CommonNavigator getCommonNavigator() {
//		IViewPart part = org.eclipse.papyrus.views.modelexplorer.NavigatorUtils.findViewPart(ModelicaMLModelExplorerPageBookView.VIEW_ID);
//		IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ModelicaMLModelExplorerPageBookView.VIEW_ID);
		IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELEXPLORER);
		// the part is only a book, retrieving correct page
		if (part instanceof ModelExplorerPageBookView) {
			IViewPart page = ((ModelExplorerPageBookView)part).getActiveView();
			if(page instanceof CommonNavigator) {
				return ((CommonNavigator)page);
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		if (Activator.getDefault().getCustomizationManager() != null) {
			CustomizationManager customizationManager = Activator.getDefault().getCustomizationManager();
			final List<MetamodelView> registeredCustomizations = customizationManager.getRegisteredCustomizations();

			final LoadCustomizationsDialog loadCustomizationsDialog = new LoadCustomizationsDialog(new Shell(), registeredCustomizations, getMetamodels());
			if (Window.OK == loadCustomizationsDialog.open() ) {
				try {

					customizationManager.clearCustomizations();
					List<MetamodelView> selectedCustomizations = loadCustomizationsDialog.getSelectedCustomizations();
					
					//before loading, clean all facet to prevent to let not interesting facets.
					customizationManager.clearFacets();
					if (loadCustomizationsDialog.isLoadRequiredFacetsSelected()) {
						// load facets corresponding to customizations

						loadFacetsForCustomizations(selectedCustomizations, customizationManager);
					}

					// first register all ModelicaML customizations
					for (MetamodelView metamodelView : selectedCustomizations) {
//						System.err.println("LoadBrowserCustomization: " + metamodelView.getName());
						if (metamodelView.getName().startsWith("ModelicaML")) {
							customizationManager.registerCustomization(metamodelView);
						}
					}
					
					// now register all other
					for (MetamodelView metamodelView : selectedCustomizations) {
						if (!metamodelView.getName().startsWith("ModelicaML")) {
							customizationManager.registerCustomization(metamodelView);
						}
					}
					
					customizationManager.loadCustomizations();
					
					
					if (getCommonNavigator() != null) {
						Tree tree = getCommonNavigator().getCommonViewer().getTree();
						customizationManager.installCustomPainter(tree);
						tree.redraw();
					}
					// Activator.getDefault().getCustomizationManager().loadCustomizations(selectedCustomizations);

				} catch (final Exception e) {
					Activator.log.error(e);
				}
			}
			// load customizations defined as default through the customization extension
			if (getCommonNavigator() != null) {
				getCommonNavigator().getCommonViewer().refresh();
			}
		}
		return null;
	}


	/**
	 * 
	 * @return the RessourceSet
	 */
	private DiResourceSet getDiResourceSet() {
		return EditorUtils.getDiResourceSet();
	}

	/**
	 * load the facets
	 * 
	 * @param customizations
	 *            list of customization
	 * @param customizationManager
	 *            the Customization Manager
	 */
	protected void loadFacetsForCustomizations(
		final List<MetamodelView> customizations,
		final CustomizationManager customizationManager) {
		final Set<Facet> referencedFacets = new HashSet<Facet>();
		final Collection<FacetSet> facetSets = FacetSetCatalog.getSingleton()
			.getAllFacetSets();

		for (MetamodelView customization : customizations) {
			String metamodelURI = customization.getMetamodelURI();
			// find customized FacetSet
			FacetSet customizedFacetSet = null;
			if (metamodelURI != null) {
				for (FacetSet facetSet : facetSets) {
					if (metamodelURI.equals(facetSet.getNsURI())) {
						customizedFacetSet = facetSet;
						break;
					}
				}
			}
			if (customizedFacetSet == null) {
				continue;
			}

			// find customized Facets
			EList<TypeView> types = customization.getTypes();
			for (TypeView typeView : types) {
				String metaclassName = typeView.getMetaclassName();
				Facet facet = findFacetWithFullyQualifiedName(metaclassName,
					customizedFacetSet);
				if (facet != null) {
					referencedFacets.add(facet);
				} else {
					Activator.log.warn(NLS
						.bind(
							Messages.BrowserActionBarContributor_missingRequiredFacet,
							new Object[] {
								metaclassName,
								customizedFacetSet
								.getName(),
								customization.getName() }));
				}
			}

			for (Facet referencedFacet : referencedFacets) {
				customizationManager.loadFacet(referencedFacet);
			}
		}

		//
		// for modified facets
		// customizationManager.getInstancesForMetaclasses().buildDerivationTree();
		// customizationManager.getAppearanceConfiguration().touch();
		// customizationManager.refreshDelayed(true);
	}

	/**
	 * fin a facet from
	 * 
	 * @param metaclassName
	 * @param customizedFacetSet
	 * @return
	 */
	private Facet findFacetWithFullyQualifiedName(final String metaclassName,
		final FacetSet customizedFacetSet) {
		EList<Facet> facets = customizedFacetSet.getFacets();
		for (Facet facet : facets) {
			String facetName = getMetaclassQualifiedName(facet);
			if (metaclassName.equals(facetName)) {
				return facet;
			}
		}
		return null;
	}

	/** @return the qualified name of the given metaclass */
	public static String getMetaclassQualifiedName(final EClassifier eClass) {
		final ArrayList<String> qualifiedNameParts = new ArrayList<String>();
		final StringBuilder builder = new StringBuilder();

		EPackage ePackage = eClass.getEPackage();
		while (ePackage != null) {
			qualifiedNameParts.add(ePackage.getName());
			ePackage = ePackage.getESuperPackage();
		}

		for (int i = qualifiedNameParts.size() - 1; i >= 0; i--) {
			builder.append(qualifiedNameParts.get(i) + "."); //$NON-NLS-1$
		}

		builder.append(eClass.getName());

		return builder.toString();
	}

	/**
	 * Get the metmodel URI
	 * **/
	public String getMetamodelURI() {

		try {
			EList<EObject> contents = getDiResourceSet().getModelResource()
				.getContents();
			if (contents.size() > 0) {
				EObject eObject = contents.get(0);
				EClass eClass = eObject.eClass();
				if (eClass != null) {
					return eClass.getEPackage().getNsURI();
				}
			}
		} catch (Exception e) {
			Activator.log.error(e);
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * Get the metmodel URI
	 * **/
	public List<EPackage> getMetamodels() {
		List<EPackage> ePackages = new ArrayList<EPackage>();
		ServicesRegistry serviceRegistry = null;
		try {
			serviceRegistry = ServiceUtilsForActionHandlers.getInstance()
				.getServiceRegistry();
		} catch (ServiceException e) {
			Activator.log.error(e);
		}

		/*we look for the current editors, because their are represented in the model explorer
		using specific facet and uiCustom. (see bug 359692) */
		IPageMngr pageMngr = null;
		try {
			pageMngr = ServiceUtils.getInstance().getIPageMngr(serviceRegistry);
		} catch (ServiceException e) {
			Activator.log.error(e);
		}
		List<Object> pages = pageMngr.allPages();
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i) instanceof EObject) {
				EPackage ePackage = ((EObject) pages.get(i)).eClass()
					.getEPackage();
				if (!ePackages.contains(ePackage)) {
					ePackages.add(ePackage);
				}
			}
		}

		try {
			EList<EObject> contents = getDiResourceSet().getModelResource()
				.getContents();
			if (contents.size() > 0) {
				EObject eObject = contents.get(0);
				EClass eClass = eObject.eClass();
				if (eClass != null) {
					ePackages.add(eClass.getEPackage());
				}
			}
		} catch (Exception e) {
			Activator.log.error(e);
		}
		return ePackages;
	}

}
