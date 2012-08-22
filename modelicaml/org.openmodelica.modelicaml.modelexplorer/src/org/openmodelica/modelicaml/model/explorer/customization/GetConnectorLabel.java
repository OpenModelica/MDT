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
package org.openmodelica.modelicaml.model.explorer.customization;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Property;

// TODO: Auto-generated Javadoc
/**
 * creates the label for Connector.
 */
public class GetConnectorLabel implements IJavaModelQuery<Connector, String> {
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmt.modisco.infra.query.core.java.IJavaModelQuery#evaluate(org.eclipse.emf.ecore.EObject, org.eclipse.gmt.modisco.infra.query.core.java.ParameterValueList)
	 */
	public String evaluate(final Connector context, final ParameterValueList parameterValues)
			throws ModelQueryExecutionException {
		
		EList<ConnectorEnd> ends = context.getEnds();
		String string1 = "??";
		String string2 = "??";
		
		ConnectorEnd connectorEnd1 = ends.get(0);
		ConnectorEnd connectorEnd2 = ends.get(1);
		
		Property p1 = connectorEnd1.getPartWithPort();
		if (p1 != null) {
			string1 = p1.getName();
		}
		else {
			string1 = "";
		}
		
		ConnectableElement port1 = connectorEnd1.getRole();
		if (port1 != null) {
			string1 = string1 + "." + port1.getName();
		}
		
		Property p2 = connectorEnd2.getPartWithPort();
		if (p2 != null) {
			string2 = p2.getName();
		}
		else {
			string2 = "";
		}
		
		ConnectableElement port2 = connectorEnd2.getRole();
		if (port2 != null) {
			string2 = string2 + "." + port2.getName();
		}

		return "connect(" + string1 + ", " + string2 + ")";
	}
}
