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
package org.openmodelica.modelicaml.modelexplorer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.facet.infra.browser.custom.MetamodelView;
import org.eclipse.emf.facet.infra.browser.custom.TypeView;
import org.eclipse.emf.facet.infra.browser.custom.core.CustomizationsCatalog;
import org.eclipse.emf.facet.infra.browser.uicore.CustomizationManager;
import org.eclipse.emf.facet.infra.facet.Facet;
import org.eclipse.emf.facet.infra.facet.FacetSet;
import org.eclipse.emf.facet.infra.facet.core.FacetSetCatalog;
import org.eclipse.papyrus.infra.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.navigator.CommonViewer;

/**
 * Papyrus Model Explorer associated to one {@link IMultiDiagramEditor}.
 * This ModelExplorer is linked to one single {@link IMultiDiagramEditor}. It doesn't change its
 * source when the current Editor change. To allow to explore different Model, use a {@link ModelExplorerPageBookView}.
 * 
 */
@SuppressWarnings({ "deprecation", "restriction" })
public class ModelExplorerView extends org.eclipse.papyrus.views.modelexplorer.ModelExplorerView {

	public ModelExplorerView(IMultiDiagramEditor part) {
		super(part);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EditingDomain getEditingDomain() {
		// TODO Auto-generated method stub
		return super.getEditingDomain();
	}
	
	@Override
	public CommonViewer getCommonViewer() {
		// TODO Auto-generated method stub
		return super.getCommonViewer();
	}
	
	
	public static void reveal(Iterable<?> selection, CommonViewer viewer){
		org.eclipse.papyrus.views.modelexplorer.ModelExplorerView.reveal(selection, viewer);
	}
	
	@Override
	public void createPartControl(Composite aParent) {
		
		super.createPartControl(aParent);
		
		/*
		 * ModelicaML SPECIFIC: this code was added in order to make sure that the ModelicaML
		 * customization is always added at the top of the Model Explorer Customizations.
		 * The code was collected for {@link LoadBrowserCustomization} and the {@link Activator} class.
		 */
		
		try {
			// load customizations defined as default through the customization extension
			List<MetamodelView> registryDefaultCustomizations = CustomizationsCatalog.getInstance().getRegistryDefaultCustomizations();
			
			//before loading, clean all facet to prevent to let not interesting facets.
			Activator.getDefault().getCustomizationManager().clearFacets();
			Activator.getDefault().getCustomizationManager().clearCustomizations();
			
			// first register all ModelicaML customizations
			for (MetamodelView metamodelView : registryDefaultCustomizations) {
				if (metamodelView.getName().startsWith("ModelicaML")) {
					Activator.getDefault().getCustomizationManager().registerCustomization(metamodelView);
//					System.err.println("ModelExplorerView: " + metamodelView.getName());
				}
			}
			
			// now register all other
			for (MetamodelView metamodelView : registryDefaultCustomizations) {
				if (!metamodelView.getName().startsWith("ModelicaML")) {
					Activator.getDefault().getCustomizationManager().registerCustomization(metamodelView);
//					System.err.println("ModelExplorerView: " + metamodelView.getName());
				}
			}
			
//			System.err.println("\nLoading customizations");
			
			Activator.getDefault().getCustomizationManager().loadCustomizations();
			loadFacetsForCustomizations(registryDefaultCustomizations,Activator.getDefault().getCustomizationManager());

//			System.err.println("ModelicaML customization is loaded.");

		} catch (Throwable e) {
			System.err.println("Could not load the ModelicaML Ccustomization");
//			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Error initializing customizations", e)); //$NON-NLS-1$
		}
	}
	
	
	/*
	 * The code was collected for {@link LoadBrowserCustomization} and the {@link Activator} class.
	 */
	
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
//					Activator.log.warn(NLS
//						.bind(
//							Messages.BrowserActionBarContributor_missingRequiredFacet,
//							new Object[] {
//								metaclassName,
//								customizedFacetSet
//								.getName(),
//								customization.getName() }));
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
	
}
