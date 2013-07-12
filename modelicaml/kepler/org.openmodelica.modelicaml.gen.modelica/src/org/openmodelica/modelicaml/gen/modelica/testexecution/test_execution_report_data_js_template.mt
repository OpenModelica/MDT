<%--encoding=utf-8--%>
<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.common.helpers.VerificationServices
import org.openmodelica.modelicaml.common.services.StringUtls
import  org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
import org.openmodelica.modelicaml.gen.modelica.testexecution.test_execution_report_data_js
%>


<%script type="uml.Model" name="file_name_js"%>
<%if (owner == null){%>
<%getTestSessionFolderProjectPath()%>/report-gen/includes/report_data.js_template
<%}%>

