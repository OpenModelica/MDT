<%--encoding=utf-8--%>
<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.common.helpers.VerificationExecutionServices
import org.openmodelica.modelicaml.common.services.StringUtls
import  org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
%>

<%script type="uml.Model" name="file_name_xml"%>
<%if (owner == null){%>
<%getTestSessionFolderProjectPath()%>/<%getTestSessionFileName()%>.xml
<%}%>


<%script type="uml.Model" name="test_session_xml" file="<%file_name_xml%>"%>
<%-- <!-- File name: "<%getTestSessionFolderProjectPath()%>/test_session.xml" --> --%>
<?xml version="1.0" encoding="ISO-8859-1"?>
<TestSessionConfiguration>
    <project>
		<name>Test Session <%getTime()%></name>
<%-- 	<packageFileAbsolutePath><%getTopLevelModelicaFilePath()%></packageFileAbsolutePath> --%>
		<packageFileRelativePath><%getCodeGenFolderName()%>/<%name.replaceSpecChar()%>/package.mo</packageFileRelativePath>
    </project>
    <testModels>
		<%xml_testModels%>
	</testModels>
</TestSessionConfiguration>


<%script type="uml.Model" name="xml_testModels"%>
<%for (getTestModels()){%>
<testModel>
	<qualifiedName><%getTestModelQName()%></qualifiedName>		
	<simulationData>
		<start><%getStartTime()%></start> 
		<stop><%getStopTime()%></stop> 
		<numberOfIntervals><%getNumberOfIntervals()%></numberOfIntervals>
		<tolerance><%getTolerance()%></tolerance> 
		<solver><%getSolver()%></solver>
		<outputFormat><%getOutputFormat()%></outputFormat> 
	</simulationData>
	<executionLog>
		<OMCResponse></OMCResponse>
	</executionLog>
</testModel>
<%}%>

