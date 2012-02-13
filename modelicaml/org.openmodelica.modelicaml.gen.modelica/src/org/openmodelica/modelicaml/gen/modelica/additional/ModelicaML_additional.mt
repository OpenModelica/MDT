<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.common.services.StringUtls
%>

<%script type="uml.Element" name="copyright"%>
/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Linköping University,
 * Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 
 * AND THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S  
 * ACCEPTANCE OF THE OSMC PUBLIC LICENSE.
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from Linköping University, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS
 * OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Contributors 2009-2011: 
 *   Wladimir Schamai, EADS Innovation Works
 * 
 */


<%-- usercode  ############################################--%>
<%script type="uml.NamedElement" name="user_code_area_top" post="trim()" %>
//<%startUserCode%>	: <%name.replaceSpecChar()%>-user-code-area-top  ###########################
//You can enter Modelica code between these start and end lines. It will NOT be deleted or overwritten by code-generator.
//<%endUserCode%>	: <%name.replaceSpecChar()%>-user-code-area-top ###########################

<%-- usercode  ############################################--%>
<%script type="uml.NamedElement" name="user_code_area_bottom" post="trim()" %>
//<%startUserCode%>	: <%name.replaceSpecChar()%>-user-code-area-bottom ###########################
//You can enter Modelica code between these start and end lines. It will NOT be deleted or overwritten by code-generator.
//<%endUserCode%>	: <%name.replaceSpecChar()%>-user-code-area-bottom ###########################
