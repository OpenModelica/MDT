package org.modelica.uml.sysml.diagram2.properties;

import java.util.List;
import java.util.ArrayList;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.properties.ICompositePropertySource;
import org.eclipse.gmf.runtime.common.ui.services.properties.IPropertiesModifier;
import org.modelica.uml.sysml.diagram2.providers.SysmlPropertyProvider;

import org.modelica.uml.sysml.PropertyKind;

public class SysmlPropertyModifier extends SysmlPropertyProvider implements
		IPropertiesModifier {

	public void apply(ICompositePropertySource propertySource) {

		if (propertySource instanceof SysmlPropertySource) {
			SysmlPropertySource sysmlPropertySource = (SysmlPropertySource) propertySource;

			List readOnlyUMLPropertyList = getReadOnlyUMLProperties(sysmlPropertySource);
			sysmlPropertySource.readOnlyProperties(readOnlyUMLPropertyList);
						
		}
	}

	public boolean provides(IOperation operation) {
		return true;
	}

	protected List getReadOnlyUMLProperties(
			SysmlPropertySource sysmlPropertySource) {
		List<String> propertyList = new ArrayList<String>();

		if (sysmlPropertySource.getAppliedTo() == SysmlPropertySource.APPLIED_TO_PROPERTY) {

			propertyList.add("UMLpropertyKind : PropertyKind");

			if (sysmlPropertySource.getPropertyKind().equals(
					PropertyKind.PARAMETER_LITERAL.getName())
					|| sysmlPropertySource.getPropertyKind().equals(
							PropertyKind.PART_LITERAL.getName())) {

				propertyList.add("UMLvariability : VariabilityKind");
				propertyList.add("UMLisReadOnly : Boolean");

				if (sysmlPropertySource.getPropertyKind().equals(
						PropertyKind.PARAMETER_LITERAL.getName())) {
					propertyList.add("UMLdirection : FlowDirection");
					propertyList.add("UMLisFlow : Boolean");
				}

				if (sysmlPropertySource.getPropertyKind().equals(
						PropertyKind.PART_LITERAL.getName())) {
					// propertyList.add("UMLtype : Type");
				}
			}
		}
		return propertyList;
	}

}
