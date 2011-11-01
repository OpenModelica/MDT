package org.openmodelica.modelicaml.modelica.importer.helper;

import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.services.ElementsCollector;

public class ModelicaModelProxiesCollector extends ElementsCollector {

	
	/*
	 *  This method can be overridden for specific collections
	 */
	@Override
	protected void collectElements(Element element, boolean isImported){
		// collect elements
		// avoid duplicates that can occur due to the multiple imports of the same elements
		
		if (element instanceof Element 
				&& ((Element)element).getAppliedStereotype(this.stereotypeQName) != null
				&& !elements.contains(element)) {
			
			elements.add(element) ;
			
			if (isImported) {
				importedElements.add(element);
			}
			
		}
	}
}
