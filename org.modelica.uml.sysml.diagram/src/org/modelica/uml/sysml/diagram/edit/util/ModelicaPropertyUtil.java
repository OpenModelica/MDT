package org.modelica.uml.sysml.diagram.edit.util;

import org.modelica.uml.sysml.AccessKind;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.PropertyKind;
import org.modelica.uml.sysml.FlowDirection;
import org.modelica.uml.sysml.VariabilityKind;

public class ModelicaPropertyUtil {

	public static String getSignatureString(String varName,
			ModelicaComposition composition) {

		StringBuffer signature = new StringBuffer();

		if (composition.getAccess().equals(AccessKind.PUBLIC_LITERAL)) {
			signature.append("+");
		} else if (composition.getAccess().equals(AccessKind.PROTECTED_LITERAL)) {
			signature.append("#");
		}

		signature.append(" ");

		boolean bracesPut = false;
		if (!composition.getDirection().equals(FlowDirection.INOUT_LITERAL)) {
			if (!bracesPut) {
				//signature.append("{");
				bracesPut = true;
			}
			signature.append(composition.getDirection());
		}

		if (composition.isIsFlow()) {
			if (!bracesPut) {
				//signature.append("{");
				bracesPut = true;
			} else {
				signature.append(" ");
			}
			signature.append("flow");
		}

		if (bracesPut) {
			//signature.append("}");
		}
		
		signature.append(" " + varName);

		if (composition.getArrayDimension() != null
				&& !composition.getArrayDimension().trim().equals("")) {
			signature.append("[");
			signature.append(composition.getArrayDimension());
			signature.append("]");
		}
				
		if (composition.getDefault() != null && !composition.getDefault().equals("")) {
			signature.append("(" + composition.getDefault()+ ")");
		}

		return signature.toString();
	}

	public static String getSignatureStringWithDefault(String varName,
			ModelicaProperty property) {
		String signature = getSignatureString(varName, property);
		if (property.getDefault() != null && !property.getDefault().equals("")) {
			signature += "(" + property.getDefault() + ")";
		}
		return signature;
	}

	public static String getSignatureString(String varName,
			ModelicaProperty property) {

		StringBuffer signature = new StringBuffer();

		String propertyKind = property.getPropertyKind().getName();

		if (property.getType() != null) {

			String typeName = property.getType().getName();
			if (typeName != null && typeName.length() > 0) {

				boolean bracesPut = false;
				if (propertyKind.equals(PropertyKind.PART_LITERAL.getName())) {

					if (!property.getDirection().equals(
							FlowDirection.INOUT_LITERAL)) {
						if (!bracesPut) {
							//signature.append("{");
							bracesPut = true;
						}
						signature.append(property.getDirection());
					}

					if (property.isIsFlow()) {
						if (!bracesPut) {
							//signature.append("{");
							bracesPut = true;
						} else {
							signature.append(" ");
						}
						signature.append("flow");
					}

				} else if (propertyKind.equals(PropertyKind.VARIABLE_LITERAL
						.getName())) {

					if (property.getVariability().equals(
							VariabilityKind.CONSTANT_LITERAL)) {
						if (!bracesPut) {
							//signature.append("{");
							bracesPut = true;
						}
						signature.append("constant");
					} else {

						if (!property.getDirection().equals(
								FlowDirection.INOUT_LITERAL)) {
							if (!bracesPut) {
								//signature.append("{");
								bracesPut = true;
							}
							signature.append(property.getDirection());
						}

						if (property.isIsFlow()) {
							if (!bracesPut) {
								// signature.append("{");
								bracesPut = true;
							} else {
								signature.append(" ");
							}
							signature.append("flow");
						}

						if (!property.getVariability().equals(
								VariabilityKind.NONE_LITERAL)) {
							if (!bracesPut) {
								//signature.append("{");
								bracesPut = true;
							} else {
								signature.append(" ");
							}
							signature.append(property.getVariability());
						}

					}
										
				}

				if (bracesPut) {
					//signature.append("}");
				}
				signature.append(" " + typeName);
			}			
			
			signature.append("  " + varName);
			
			if (property.getArrayDimension() != null
					&& !property.getArrayDimension().trim().equals("")) {
				signature.append("[");
				signature.append(property.getArrayDimension());
				signature.append("]");
			}
			
		} else {
			signature.append("" + varName);
		}

		signature.append(" ");
		return signature.toString();
	}

	public static String getSignatureStringForPart(String varName,
			ModelicaProperty property) {

		StringBuffer signature = new StringBuffer();
		

		if (property.getType() != null) {

			String typeName = property.getType().getName();
			signature.append(typeName + " ");
			signature.append(" " + varName);
			
			if (typeName != null && typeName.length() > 0) {

				if (property.getArrayDimension() != null
						&& !property.getArrayDimension().trim().equals("")) {
					signature.append("[");
					signature.append(property.getArrayDimension());
					signature.append("]");
				}

			}
		} else {
			signature.append(" " + varName);	
		}
			
		return signature.toString();
	}

}
