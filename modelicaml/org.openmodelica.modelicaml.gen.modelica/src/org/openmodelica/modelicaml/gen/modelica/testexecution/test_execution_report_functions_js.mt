<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.common.helpers.VerificationExecutionServices
import org.openmodelica.modelicaml.common.services.StringUtls
import  org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
%>

<%script type="uml.Model" name="file_name_js_functions"%>
<%if (owner == null){%>
<%getTestSessionFolderProjectPath()%>/report-gen/includes/report_functions.js
<%}%>

<%script type="uml.Model" name="report_js_functions" file="<%file_name_js_functions%>"%>

function writeTestPassedString(allRequirementsEvaluated_atLeastOneTimeTrue, someRequirementsViolated_atLeastOneTimeTrue){
	if (allRequirementsEvaluated_atLeastOneTimeTrue == "true" && someRequirementsViolated_atLeastOneTimeTrue == "false"){
		document.write('<font color="#32CD32"><strong>Passed</strong></font>');
	}
	else if (allRequirementsEvaluated_atLeastOneTimeTrue == "false" || someRequirementsViolated_atLeastOneTimeTrue == "true"){
		document.write('<font color="#FF0000"><strong>Failed</strong></font>');
	}
	else if (allRequirementsEvaluated_atLeastOneTimeTrue == "true" && someRequirementsViolated_atLeastOneTimeTrue == "true"){
		document.write('<font color="#FF0000"><strong>Failed</strong></font>');
	}
	else {
		document.write('<font color="#FF0000"><strong>Pass/Fail: Not executed</strong></font>');
	}
}


function writeRequirementPassedString(evaluted, violated){
	if (evaluted == "true" && violated == "true"){
		//document.write('<font color="#FF0000"><strong>Failed</strong></font>');
		document.write('<font color="#FF0000"><strong>Violated</strong></font>');
	}
	else if (evaluted == "true" && violated == "false"){
		//document.write('<font color="#32CD32"><strong>Passed</strong></font>');
		document.write('<font color="#32CD32"><strong>Not violated</strong></font>');
	}
	else if (evaluted == "false"){
		//document.write('<font color="#FF0000"><strong>Failed</strong></font>');
		document.write('<font color="#FF0000"><strong>Not evaluated</strong></font>');
	}
	else {
		document.write('<font color="#FF0000"><strong>Status: Not executed</strong></font>');
	}
}

function writeAlwaysFalseString(value){
	if (value == "true") {
		document.write('<font color="#32CD32"><strong>was always false</strong></font>');
	}
	else if (value == "false"){
		document.write('<font color="#FF0000"><strong>was not always false</strong></font>');
	}
	else if (value == "fileNotFound"){
		document.write('<font color="#FF0000"><strong>Results file not found.</strong></font>');
	}
	else if (value == "resultsNotFound"){
		document.write('<font color="#FF0000"><strong>Results do not exist.</strong></font>');
	}
	else {
		document.write('<font color="#FF0000"><strong>Results are unknown</strong></font>');
	}
}

function writeBooleanGreenYesString(value){
	if (value == "true") {
		document.write('<font color="#32CD32"><strong>yes</strong></font>');
	}
	else if (value == "false"){
		document.write('<font color="#FF0000"><strong>no</strong></font>');
	}
	else if (value == "fileNotFound"){
		document.write('<font color="#FF0000"><strong>Results file not found.</strong></font>');
	}
	else if (value == "resultsNotFound"){
		document.write('<font color="#FF0000"><strong>Results do not exist.</strong></font>');
	}
	else {
		document.write('<font color="#FF0000"><strong>Results are unknown</strong></font>');
	}
}


function writeBooleanRedYesString(value){
	if (value == "true") {
		document.write('<font color="#FF0000"><strong>yes</strong></font>');
	}
	else if (value == "false"){
		document.write('<font color="#32CD32"><strong>no</strong></font>');
	}
	else if (value == "fileNotFound"){
		document.write('<font color="#FF0000"><strong>Results file not found.</strong></font>');
	}
	else if (value == "resultsNotFound"){
		document.write('<font color="#FF0000"><strong>Results do not exist.</strong></font>');
	}
	else {
		document.write('<font color="#FF0000"><strong>Results are unknown</strong></font>');
	}
}

function writeAtLeastOneTimeTrueString(value){
	if (value == "true") {
		document.write('<font color="#32CD32"><strong>was at least one time true</strong></font>');
	}
	else if (value == "false"){
		document.write('<font color="#FF0000"><strong>was never true</strong></font>');
	}
	else if (value == "fileNotFound"){
		document.write('<font color="#FF0000"><strong>Results file not found.</strong></font>');
	}
	else if (value == "resultsNotFound"){
		document.write('<font color="#FF0000"><strong>Results do not exist.</strong></font>');
	}
	else {
		document.write('<font color="#FF0000"><strong>Results are unknown</strong></font>');
	}
}

function writeChangedItsValueString(value){
	if (value == "true") {
		document.write('<font color="#32CD32"><strong>changed its value</strong></font>');
	}
	else if (value == "false"){
		document.write('<font color="#FF0000"><strong>did not change its value</strong></font>');
	}
	else if (value == "fileNotFound"){
		document.write('<font color="#FF0000"><strong>Results file not found.</strong></font>');
	}
	else if (value == "resultsNotFound"){
		document.write('<font color="#FF0000"><strong>Results do not exist.</strong></font>');
	}
	else {
		document.write('<font color="#FF0000"><strong>Results are unknown</strong></font>');
	}
}


function writePlotLink(href, text){
	if (getEnableToolValue() == "true"){
		//document.write("(<a href='plot:" + href + "'><strong>"+text+"</strong></a>)");
		document.write("(<a href='" + href + "'><strong>"+text+"</strong></a>)");
	}	
}

function writeLink(href, text){
	if (getEnableToolValue() == "true"){
		document.write("<a href='"+href+"'>"+text+"</a>");
	}	
	else {
		document.write(text);
	}
}

// GET Parameter handling
function getEnableToolValue() {
    var url   = window.location.search;
    if (url != "")  {
      liste = url.replace("?","").split("&");
      /* auch hier splitten am "&" */
      for (i=0;i<=liste.length-1;i++) {
        temp = liste[i].split("=");
        /* auch hier splitten am "=" */
        temp[1]=temp[1].replace(/\+/g," ");
        temp[1]=unescape(temp[1]);
        /* wie gehabt, das Putzen aber jetzt:*/
		if (temp[0] == "enableTool") {
			return temp[1];
		}
       } //next
     } // end if
   }




// input 
function decode() {
    var url   = window.location.search;
    /* wie gehabt, es wird alles ab dem "?" gelesen */
    if (url != "")  {
      liste = url.split("&");
      /* auch hier splitten am "&" */
      for (i=0;i<=liste.length-1;i++) {
        temp = liste[i].split("=");
        /* auch hier splitten am "=" */
        temp[1]=temp[1].replace(/\+/g," ");
        temp[1]=unescape(temp[1]);
        /* wie gehabt, das Putzen aber jetzt:*/
        eval_temp=temp[0] + "=" + temp[1];
        eval (eval_temp);
        /* schreibt den Inhalt in evaltemp, dass dann etwa so aussieht: daten1=Hallo und "berechnet" diesen String. Die Variablen stehen danach zur Verfügung --- ACHTUNG: sollen die Variablen außerhalb der Funktion verfügbar sein, dann müssen sie außerhalb "definiert" werden! */
       } //next
     } // end if
   } 