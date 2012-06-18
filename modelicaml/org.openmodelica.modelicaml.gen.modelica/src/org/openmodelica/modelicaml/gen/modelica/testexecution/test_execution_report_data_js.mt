<%--encoding=utf-8--%>
<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.common.helpers.VerificationExecutionServices
import org.openmodelica.modelicaml.common.services.StringUtls
import  org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
%>

<%script type="uml.Model" name="file_name_js"%>
<%if (owner == null){%>
<%getTestSessionFolderProjectPath()%>/report-gen/includes/report_data.js
<%}%>

<%script type="uml.Model" name="report_js" file="<%file_name_js%>"%>
/*
MACRO: 
	- varDothPath[question] -> to be replaced by the evaluated value for the referenced variable and its question.

Questions: 
	- [<%getMACROatLeastOneTimeTrue()%>], 
	- [<%getMACROatLeastOneTimeFalse()%>], 
	- [<%getMACROalwaysTrue()%>], 
	- [<%getMACROalwaysFalse()%>], 
	- [<%getMACROchangedItsValue()%>]
	
Questions return strings are "true", "false", "fileNotFound" and "resultsNotfound".
- "fileNotFound" indicates that the model was not executed.
- "resultsNotfound" indicates that the model was executed but the results for a particular variables were not recorded.

For each results file there is block starting with 'START' and ending with 'END'.
Parameter 'RESULTS_FILE_..._PATH = ###../.._res.xml/###' is a key value pair, where the value is the absolute or relative path to the results file. 
*/

<%-- var testSessionFolderAbsolutePath = "<%getTestSessionFolderAbsolutePath()%>"; --%>
var testSessionFolderRelativePath = "../../";

// statistics
var testsExecutedNumber = 0;
var testsPassedNumber = 0;
var testsFailedNumber = 0;

/* A data entry consists of: test model qualified name, variable (doth-path reference), question */
var data = new Array();

<%report_js_testModels%>




<%script type="uml.Model" name="report_js_testModels"%>
<%for (getTestModels()){%>
/* ~~~~~ Test Model <%qualifiedName%>  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

/*{$START}*/
<%-- /*RESULTS_FILE_RELATIVE_PATH = ###<%getTestModelQName()%>_res.xml###*/ --%>
/*RESULTS_FILE_RELATIVE_PATH = ###<%getTestModelQName()%>_res.mat###*/

data['<%qualifiedName%>'] = new Array();

/* ~~~~~ Verification Model '<%qualifiedName%>': verdicts ~~~~~ */
//data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getTestPassedPropertyName()%>'] = new Array();
//data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getTestPassedPropertyName()%>']['<%getMACROatLeastOneTimeTrue()%>'] = '###<%getReqTestVerdictPropertyName()%>.<%getTestPassedPropertyName()%>:<%getMACROatLeastOneTimeTrue()%>###';

// test model verdicts
data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getAllRequirementsEvaluatedPropertyName()%>'] = new Array();
data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getAllRequirementsEvaluatedPropertyName()%>']['<%getMACROatLeastOneTimeTrue()%>'] = '###<%getReqTestVerdictPropertyName()%>.<%getAllRequirementsEvaluatedPropertyName()%>:<%getMACROatLeastOneTimeTrue()%>###';

data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getSomeRequirementsViolatedPropertyName()%>'] = new Array();
data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getSomeRequirementsViolatedPropertyName()%>']['<%getMACROatLeastOneTimeTrue()%>'] = '###<%getReqTestVerdictPropertyName()%>.<%getSomeRequirementsViolatedPropertyName()%>:<%getMACROatLeastOneTimeTrue()%>:###';

// update statistics
if (data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getSomeRequirementsViolatedPropertyName()%>']['<%getMACROatLeastOneTimeTrue()%>'] == "false"
	&& data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getAllRequirementsEvaluatedPropertyName()%>']['<%getMACROatLeastOneTimeTrue()%>'] == "true"){
	
	// test passed
	testsPassedNumber = testsPassedNumber + 1;
	
	// test was executed
	testsExecutedNumber = testsExecutedNumber + 1;
}
else if (data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getSomeRequirementsViolatedPropertyName()%>']['<%getMACROatLeastOneTimeTrue()%>'] == "true"
	|| data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getAllRequirementsEvaluatedPropertyName()%>']['<%getMACROatLeastOneTimeTrue()%>'] == "false"){

	// test failed
	testsFailedNumber = testsFailedNumber + 1;

	// test was executed
	testsExecutedNumber = testsExecutedNumber + 1;
}

		//models
<%getComponentsJS()%>

		// requirements
<%getRequirementsJS()%>
/*{$END}*/

<%}%>







