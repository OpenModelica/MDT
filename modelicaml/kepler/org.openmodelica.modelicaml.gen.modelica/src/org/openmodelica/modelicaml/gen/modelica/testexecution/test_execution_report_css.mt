<%--encoding=utf-8--%>
<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.common.helpers.VerificationServices
import org.openmodelica.modelicaml.common.services.StringUtls
import  org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
%>


<%script type="uml.Model" name="file_name_css"%>
<%if (owner == null){%>
<%getTestSessionFolderProjectPath()%>/report-gen/includes/report_style.css
<%}%>

<%script type="uml.Model" name="report_css" file="<%file_name_css%>"%>
/* Style Sheet */

body{
   background-color: #FFFFFF;
   color: #000000;
   font-size:11px;
   color:#000000;
   font-family:Verdana;
}
	
span {
	line-height:18px;
}
	
.greyBG{
	background-color:#F0F0F0;
}

.greyBG2{
	background-color:#F9F9F9;
}