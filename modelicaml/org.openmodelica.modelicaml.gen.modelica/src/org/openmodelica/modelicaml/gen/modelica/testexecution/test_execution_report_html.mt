<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.common.helpers.VerificationExecutionServices
import org.openmodelica.modelicaml.common.services.StringUtls
import  org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
%>

<%script type="uml.Model" name="file_name_html"%>
<%if (owner == null){%>
<%getTestSessionFolderProjectPath()%>/report-gen/report.html
<%}%>



<%script type="uml.Model" name="report_html" file="<%file_name_html%>"%>
<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>	
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>
<title>Test Execution Report (<%getTime()%>)</title>

	<script type='text/javascript' src='includes/report_data.js'></script>
	<script type='text/javascript' src='includes/report_functions.js'></script>

	<script type='text/javascript'>
		var testsNumber = <%getTestModelsNumber()%>; // the number of tests contained in this report.
	</script>

	<link rel="stylesheet" type="text/css" href="includes/report_style.css">

</head>

<body>
	<a name="top"></a>
	<!-- Title, Overview -->
	<div id='title' style='margin:0;padding:0;position:relative; left:20px; top:0px; text-align:left;z-index:1;'>
		<span style='font-size:18px; line-height:25px;'>Verification Session Report</span>
		<br>
		<span>Creation date: <%getTime()%></span>
		<br><br>
		
		<span>Verification models number 
			(<strong><script type='text/javascript'>document.write(testsNumber)</script></strong>), 
		</span> 
		
		<span style='color:#ff9900'>Executed 
			(<strong><script type='text/javascript'>document.write(testsExecutedNumber)</script></strong>), 
		</span>
		
		<span style='color:#32CD32'>Passed 
			(<strong><script type='text/javascript'>document.write(testsPassedNumber)</script></strong>), 
		</span>
		
		<span style='color:#FF0000'>Failed  
			(<strong><script type='text/javascript'>document.write(testsFailedNumber)</script></strong>)
		</span> 
		
		<br>
	</div>
	
	<%report_html_testModels_overview%>
	
	<%report_html_testModels%>
	
</body>
</html>

<%script type="uml.Model" name="report_html_testModels_overview"%>
<!-- Overview -->
<div style='margin-left:20px; margin-top:20px;'>
<%for (getTestModels()){%>
	<!-- Test passed/failed  -->
	<span style='color:#000000;'>
		<script type='text/javascript'>
			writeTestPassedString(
				data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getAllRequirementsEvaluatedPropertyName()%>']['<%getMACROatLeastOneTimeTrue()%>'],
				data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getSomeRequirementsViolatedPropertyName()%>']['<%getMACROalwaysFalse%>']
				);
		</script>
	</span>
	
	<!-- Test Model Name-->
	<span style='color:#000000; margin-left:10px;'>
		<script type='text/javascript'>writeLink('#<%qualifiedName%>', '<strong><%name%></strong>')</script>	
	</span>
	<script type='text/javascript'>
		writePlotLink(testSessionFolderRelativePath + '<%getTestModelQName()%>_res.xml',  'Plot');
	</script>
	<br>
	<!--<span style='color:#000000; font-size:10px;'>(<%qualifiedName%>)</span><br>-->
<%}%>
</div>



<%script type="uml.Model" name="report_html_testModels"%>
<%for (getTestModels()){%>
<br>
<!-- Test Model '<%qualifiedName%>' START ***************************************************************** -->
<a name="<%qualifiedName%>"></a> 
<div style='position:relative;background-color:#F0F0F0; border:1px #D2B48C solid;left:20px;top:20px;width:95%;padding:5px;'>
	<div style='margin-left:10px;>
		<!-- test passed/failed  -->
		<span style='color:#000000;'>
			<script type='text/javascript'>
			writeTestPassedString(
				data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getAllRequirementsEvaluatedPropertyName()%>']['<%getMACROatLeastOneTimeTrue()%>'],
				data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getSomeRequirementsViolatedPropertyName()%>']['<%getMACROalwaysFalse%>']
				);
		</script>
		</span>

		<!-- Test Model Name-->
		<span style='color:#000000; margin-left:10px;'>
			<script type='text/javascript'>writeLink('locate:<%qualifiedName%>', '<strong><%name%></strong>')</script>	
		</span>
		<script type='text/javascript'>
			writePlotLink(testSessionFolderRelativePath + '<%getTestModelQName()%>_res.xml',  'Plot');
		</script>
		<br>
		<span style='color:#000000; font-size:10px;'>(<%qualifiedName%>)</span>
	</div>
	
	<!-- test model veridcts -->
	<div style='padding:10px;'>
		<table cellpadding='0' cellspacing='0' border='0'>
			<tr>
				<td><i>verdict</i> 
					<script type='text/javascript'>
						writeLink('locate:<%qualifiedName%>#<%getReqTestVerdictPropertyName()%>.<%getAllRequirementsEvaluatedPropertyName()%>', '<strong><%getAllRequirementsEvaluatedPropertyName()%></strong>');
					</script>
				</td>
				<td>&nbsp;:&nbsp;</td>
				<td>
					<script type='text/javascript'>
						writeAtLeastOneTimeTrueString(data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getAllRequirementsEvaluatedPropertyName()%>']['<%getMACROatLeastOneTimeTrue()%>']);
					</script>
				</td>
			</tr>
			<tr>
				<td><i>verdict</i> 
					<script type='text/javascript'>
						writeLink('locate:<%qualifiedName%>#<%getReqTestVerdictPropertyName()%>.<%getSomeRequirementsViolatedPropertyName()%>', '<strong><%getSomeRequirementsViolatedPropertyName()%></strong>');
					</script>	
				<td>&nbsp;:&nbsp;</td>
				<td>
					<script type='text/javascript'>
						writeAlwaysFalseString(data['<%qualifiedName%>']['<%getReqTestVerdictPropertyName()%>.<%getSomeRequirementsViolatedPropertyName()%>']['<%getMACROalwaysFalse%>']);
					</script>
				</td>
			</tr>
		</table>								
	</div>
	
	<%getComponentsHTML()%>
	<!-- ***************************************************************************************************  -->
	<br>
	<%getRequirementsHTML()%>
	
	<div style='margin-left:10px; margin-top:20px;'>
		<a href="#top">... back to overview</a>
	</div>
	
</div>
<!-- Test Model '<%qualifiedName%>' END ***************************************************************** -->

<%}%>



