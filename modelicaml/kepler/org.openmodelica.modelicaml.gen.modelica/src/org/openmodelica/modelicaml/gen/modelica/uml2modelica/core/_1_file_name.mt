<%--encoding=utf-8--%>
<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.common.services.StringUtls
import  org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core.helper
import  org.openmodelica.modelicaml.gen.modelica.cg.helpers.CGConfigurationManager

%>

<%-- File names ############################################--%>
<%script type="uml.NamedElement" name="prefix" post="trim()"%>
<%getProperty("c_outputFolder")%>

<%script type="uml.Classifier" name="file_name" post="trim()"%>
<%if (getCGNameSpace() != null){%><%if (qualifiedName.startsWith(getCGNameSpace())){%><%file_name_string%><%}%><%}else{%><%file_name_string%><%}%>

<%script type="uml.Package" name="file_name" post="trim()"%>
<%if (getCGNameSpace() != null){%><%if (qualifiedName.startsWith(getCGNameSpace())){%><%file_name_string%><%}%><%}else{%><%file_name_string%><%}%>


<%script type="uml.Package" name="file_name_string" post="trim()"%>
<%prefix%><%qualifiedName.replaceSpecCharExceptThis("::").replaceAll("::","\/" )%>/package.mo

<%script type="uml.Classifier" name="file_name_string" post="trim()"%>
<%if ( 	ownerIsAValidPackageFolder == "true" && ( hasStereotype(getProperty("s_package")) || hasStereotype(getProperty("s_operator")) ) ){%><%-- for a Modelica package --%>
<%prefix%><%qualifiedName.replaceSpecCharExceptThis("::").replaceAll("::","\/" )%>/package.mo
<%}else{%><%-- for a Modelica class --%>
	<%if ( isStoredDefinition == "true"	){%><%prefix%><%qualifiedName.replaceSpecCharExceptThis("::").replaceAll("::","\/" )%>.mo<%}%>
<%}%>



<%script type="uml.Classifier" name="ownerIsAValidPackageFolder" post="trim()"%>
<%if ( owner.eClass().name == "Model" || owner.eClass().name == "Package" ){%>true
<%}else{%>
	<%if ( (owner.hasStereotype(getProperty("s_package")) || owner.hasStereotype(getProperty("s_operator"))) 
			&& !hasStereotype(getProperty("ModelicaModelProxy"))){%>
		<%if (owner.owner.eClass().name == "Model" || owner.owner.eClass().name == "Package"){%>true
		<%}else{%>
			<%if ( owner.owner.hasStereotype(getProperty("s_package")) || owner.owner.hasStereotype(getProperty("s_operator")) ){%>true
			<%}else{%>false<%}%>
		<%}%>
	<%}else{%>false<%}%>
<%}%>

<%script type="uml.Classifier" name="isStoredDefinition" post="trim()"%>
<%if (	ownerIsAValidPackageFolder == "true"
		&& isModelicaRestrictedClass == "true" 
		
		&& isProtected == "false"
		&& isRedeclare == "false"
		&& isReplaceable == "false"
		&& isPartialDerivativeFunction == "false"
		&& !hasStereotype(getProperty("ModelicaModelProxy"))
		){%>true<%}else{%>false<%}%>