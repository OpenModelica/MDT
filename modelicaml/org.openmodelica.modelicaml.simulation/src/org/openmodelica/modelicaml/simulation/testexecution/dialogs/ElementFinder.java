package org.openmodelica.modelicaml.simulation.testexecution.dialogs;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.datacollection.ElementsCollector;

public class ElementFinder extends ElementsCollector {

	String qName = "";
	public ElementFinder(EObject umlRootElement, String qName){
		
		this.qName = qName;
		
		if (umlRootElement != null) {
			// collect elements
//			collectElementsFromModel(umlRootElement, null);
			collectElementsFromModel(umlRootElement);
		}
	}
	
	@Override
	protected void collect(Element element, boolean isImported) {
		
		// collect elements
		if (element instanceof NamedElement 
				&& ((NamedElement)element).getQualifiedName() != null // important when the name is not set
				&& ((NamedElement)element).getQualifiedName().equals(this.qName)
				&& !elements.contains(element)) {
			
			elements.add(element) ;
			
			if (isImported) {
				importedPackages.add(element);
			}
		}
	}
}
