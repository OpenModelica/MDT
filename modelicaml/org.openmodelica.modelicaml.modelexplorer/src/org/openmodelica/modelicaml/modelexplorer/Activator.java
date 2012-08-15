package org.openmodelica.modelicaml.modelexplorer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.facet.infra.browser.custom.MetamodelView;
import org.eclipse.emf.facet.infra.browser.custom.TypeView;
import org.eclipse.emf.facet.infra.browser.custom.core.CustomizationsCatalog;
import org.eclipse.emf.facet.infra.browser.uicore.CustomizationManager;
import org.eclipse.emf.facet.infra.facet.Facet;
import org.eclipse.emf.facet.infra.facet.FacetSet;
import org.eclipse.emf.facet.infra.facet.core.FacetSetCatalog;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
@SuppressWarnings({ "deprecation", "restriction" })
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.openmodelica.modelicaml.modelexplorer"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * The code below was copied from org.eclipse.papyrus.views.modelexplorer.Activator
	 */
	

	/**
	 * 
	 * @return the customization manager in charge to adapt element in modisco
	 */
	public CustomizationManager getCustomizationManager() {
		return org.eclipse.papyrus.infra.emf.Activator.getDefault().getCustomizationManager();
	}

//	private void init(final CustomizationManager customizationManager) {
//		// the appearance can be customized here:
//
//		customizationManager.setShowDerivedLinks(true);
//
//		try {
//
//			// load customizations defined as default through the customization
//			// extension
//			List<MetamodelView> registryDefaultCustomizations = CustomizationsCatalog.getInstance().getRegistryDefaultCustomizations();
//			
//			// first register all ModelicaML customizations
//			for (MetamodelView metamodelView : registryDefaultCustomizations) {
//				System.err.println("Activator: " + metamodelView.getName());
//				if (metamodelView.getName().startsWith("ModelicaML")) {
//					customizationManager.registerCustomization(metamodelView);
//				}
//			}
//			
//			// now register all other
//			for (MetamodelView metamodelView : registryDefaultCustomizations) {
//				if (!metamodelView.getName().startsWith("ModelicaML")) {
//					customizationManager.registerCustomization(metamodelView);
//				}
//			}
//			
//			
////			for(MetamodelView metamodelView : registryDefaultCustomizations) {
////				
////				System.err.println("Activator: " + metamodelView.getName());
////				
////				customizationManager.registerCustomization(metamodelView);
////			}
//			customizationManager.loadCustomizations();
//			loadFacetsForCustomizations(registryDefaultCustomizations,customizationManager);
//
//		} catch (Throwable e) {
//			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Error initializing customizations", e)); //$NON-NLS-1$
//		}
//	}
//
//	/**
//	 * load the facets
//	 * 
//	 * @param customizations
//	 *            list of customization
//	 * @param customizationManager
//	 *            the Customization Manager
//	 */
//	protected void loadFacetsForCustomizations(
//		final List<MetamodelView> customizations,
//		final CustomizationManager customizationManager) {
//		final Set<Facet> referencedFacets = new HashSet<Facet>();
//		final Collection<FacetSet> facetSets = FacetSetCatalog.getSingleton()
//			.getAllFacetSets();
//
//		for (MetamodelView customization : customizations) {
//			String metamodelURI = customization.getMetamodelURI();
//			// find customized FacetSet
//			FacetSet customizedFacetSet = null;
//			if (metamodelURI != null) {
//				for (FacetSet facetSet : facetSets) {
//					if (metamodelURI.equals(facetSet.getNsURI())) {
//						customizedFacetSet = facetSet;
//						break;
//					}
//				}
//			}
//			if (customizedFacetSet == null) {
//				continue;
//			}
//
//			// find customized Facets
//			EList<TypeView> types = customization.getTypes();
//			for (TypeView typeView : types) {
//				String metaclassName = typeView.getMetaclassName();
//				Facet facet = findFacetWithFullyQualifiedName(metaclassName,
//					customizedFacetSet);
//				if (facet != null) {
//					referencedFacets.add(facet);
//				} else {
////					Activator.log.warn(NLS
////						.bind(
////							Messages.BrowserActionBarContributor_missingRequiredFacet,
////							new Object[] {
////								metaclassName,
////								customizedFacetSet
////								.getName(),
////								customization.getName() }));
//				}
//			}
//
//			for (Facet referencedFacet : referencedFacets) {
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
//	/**
//	 * fin a facet from
//	 * 
//	 * @param metaclassName
//	 * @param customizedFacetSet
//	 * @return
//	 */
//	private Facet findFacetWithFullyQualifiedName(final String metaclassName,
//		final FacetSet customizedFacetSet) {
//		EList<Facet> facets = customizedFacetSet.getFacets();
//		for (Facet facet : facets) {
//			String facetName = getMetaclassQualifiedName(facet);
//			if (metaclassName.equals(facetName)) {
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
//		while (ePackage != null) {
//			qualifiedNameParts.add(ePackage.getName());
//			ePackage = ePackage.getESuperPackage();
//		}
//
//		for (int i = qualifiedNameParts.size() - 1; i >= 0; i--) {
//			builder.append(qualifiedNameParts.get(i) + "."); //$NON-NLS-1$
//		}
//
//		builder.append(eClass.getName());
//
//		return builder.toString();
//	}
//
//	/**
//	 * 
//	 * @see org.eclipse.ui.IStartup#earlyStartup()
//	 * 
//	 */
//	public void earlyStartup() {
//
//	}
	

}
