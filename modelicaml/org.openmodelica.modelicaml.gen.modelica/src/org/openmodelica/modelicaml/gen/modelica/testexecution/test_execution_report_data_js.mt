<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.common.helpers.TestExecutionServices
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
	- [atLeastOneTimeTrue], 
	- [atLeastOneTimeFalse], 
	- [alwaysTrue], 
	- [alwaysFalse], 
	- [changedItsValue]
	
Questions return strings are "true", "false", "fileNotFound" and "resultsNotfound".
- "fileNotFound" indicates that the model was not executed.
- "resultsNotfound" indicates that the model was executed but the results for a particular variables were not recorded.

For each results file there is block starting with 'START' and ending with 'END'.
Parameter 'RESULTS_FILE_..._PATH = ../.._res.xml/' is a key value pair, where the value is the absolute or relative path to the results file. 
*/

var testSessionFolderAbsolutePath = "<%getTestSessionFolderAbsolutePath()%>";
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

/*START*/
<%-- /* ### RESULTS_FILE_ABSOLUTE_PATH = <%getTestSessionFolderAbsolutePath()%>/<%getTestModelQName()%>_res.xml ### */ --%>
/*RESULTS_FILE_RELATIVE_PATH = ../../<%getTestModelQName()%>_res.xml*/

data['<%qualifiedName%>'] = new Array();

/* ~~~~~ Test Model qName : verdicts ~~~~~ */
data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.testPassed'] = new Array();
data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.testPassed']['atLeastOneTimeTrue'] = '###_reqTestVerdict.testPassed:atLeastOneTimeTrue###';

// test model verdicts
data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.allRequirementsEvaluated'] = new Array();
data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.allRequirementsEvaluated']['atLeastOneTimeTrue'] = '###_reqTestVerdict.allRequirementsEvaluated:atLeastOneTimeTrue###';

data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.someRequirementsViolated'] = new Array();
data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.someRequirementsViolated']['alwaysFalse'] = '###_reqTestVerdict.someRequirementsViolated:alwaysFalse:###';

// update statistics
if (data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.testPassed']['atLeastOneTimeTrue'] == "true"){
	// test passed
	testsPassedNumber = testsPassedNumber + 1;
	// test was executed
	testsExecutedNumber = testsExecutedNumber + 1;
}
else if (data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.testPassed']['atLeastOneTimeTrue'] == "false") {
	// test failed
	testsFailedNumber = testsFailedNumber + 1;
}
else if (data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.testPassed']['atLeastOneTimeTrue'] == "resultsNotfound") {
	// test was executed
	testsExecutedNumber = testsExecutedNumber + 1;
}

		//models
<%getComponentsJS()%>

		// requirements
<%getRequirementsJS()%>
/*END*/

<%}%>







