<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"
 	 	doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
     doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
	<!--****************************************************************
	    ** "/" template
	    *************************************************************-->
	<xsl:template match="/">
		<html xmlns="http://www.w3.org/1999/xhtml">
		
		<link rel="stylesheet" href="reportData/report.css" type="text/css" />

		<link rel="stylesheet" href="reportData/dhtmlwindow.css" type="text/css" />
		
		<style type="text/css" title="text/css" media="print">
			div#top{display:block;}
			div#nav{display:none;}
			div.view{display:block;}
			div.view{margin-bottom:50 px;}
<!-- 			div.expandable{display:block} -->
		</style>
		
		<style type="text/css" title="text/css" media="screen">
			@import url(reportData-gen/dhtmlwindow.css);
			div#nav{float:left;}
			div.view{float:left;display:none;}
<!-- 			div.expandable{display:none} -->
		</style>

		
		<script type="text/javascript" src="reportData/report.js" />
<!-- 		<script type="text/javascript" src="reportData-gen/jquery.js" /> -->
		<script type="text/javascript" src="reportData/dhtmlwindow.js">
		
		/***********************************************
		* DHTML Window Widget- © Dynamic Drive (www.dynamicdrive.com)
		* This notice must stay intact for legal use.
		* Visit http://www.dynamicdrive.com/ for full source code
		***********************************************/

		</script>
		
			<head>
				<title>Verification Report (<xsl:value-of select="report/date"/>)</title>
			</head>
			<body>
				<div>
					<h1 class="reportHeader" style="margin-bottom:0px;">Verification Report</h1>
					<span>Creation date: <xsl:value-of select="report/date"/></span>
				</div>
				<div id="nav" class="navigation">
					
<!-- 					<script type="text/javascript">  -->
<!-- 						var frame_pos_x=800; -->
<!-- 						var frame_pos_y=400; -->
<!-- 						var frame_offset_left=250; -->
<!-- 						var frame_offset_top=100; -->
<!-- 						var shift=20; -->

<!-- 						function getFrameAction(count, qname, name){ -->
<!-- 							return String("divwin"+ count +"=dhtmlwindow.open('divbox"+count -->
<!-- 									+"', 'div', '"+qname+"', '"+name"', 'width="+frame_pos_x -->
<!-- 									+"px,height="+frame_pos_y+"px,left="+(frame_offset_left+count*shift) -->
<!-- 									+"px,top="+(frame_offset_top+count*shift)+"px,resize=1,scrolling=1'); return false") -->
<!-- 						} -->
<!-- 						document.write("abcdfsd"); -->
<!-- 					</script> -->
					
				
					<h3 style="margin-bottom:0px;">Table of Contents:</h3>
					<div style="margin-left:10px;">
						<div><a href ="#" onClick="divwin=dhtmlwindow.open('divbox', 'div', 'violatedRequirements', 'Violated Requirements', 'width=800px,height=700px,left=280px,top=40px,resize=1,scrolling=1'); return false">
							<xsl:value-of select="report/violatedRequirements/@name"/> (<xsl:value-of select="report/violatedRequirements/count"/>)</a></div>
						<div><a href ="#" onClick="divwin=dhtmlwindow.open('divbox2', 'div', 'notViolatedRequirements', 'Not Violated Requirements', 'width=800px,height=700px,left=300px,top=60px,resize=1,scrolling=1'); return false">
							<xsl:value-of select="report/notViolatedRequirements/@name"/> (<xsl:value-of select="report/notViolatedRequirements/count"/>)</a></div>
						<div><a href ="#" onClick="divwin=dhtmlwindow.open('divbox3', 'div', 'notEvaluatedRequirements', 'Not Evaluated Requirements', 'width=800px,height=700px,left=320px,top=80px,resize=1,scrolling=1'); return false">
							<xsl:value-of select="report/notEvaluatedRequirements/@name"/> (<xsl:value-of select="report/notEvaluatedRequirements/count"/>)</a></div>
						<div><a href ="#" onClick="divwin=dhtmlwindow.open('divbox31', 'div', 'notImplementedRequirements', 'Not Implemented Requirements', 'width=800px,height=700px,left=320px,top=80px,resize=1,scrolling=1'); return false">
							<xsl:value-of select="report/notImplementedRequirements/@name"/> (<xsl:value-of select="report/notImplementedRequirements/count"/>)</a></div>
	
	
						<div><a href ="#" onClick="divwin=dhtmlwindow.open('divbox4', 'div', 'usedScenarios', 'Used Scenarios', 'width=800px,height=700px,left=340px,top=100px,resize=1,scrolling=1'); return false">
							<xsl:value-of select="report/usedScenarios/@name"/> (<xsl:value-of select="report/usedScenarios/count"/>)</a></div>			
						<div><a href ="#" onClick="divwin=dhtmlwindow.open('divbox41', 'div', 'notUsedScenarios', 'Not Used Scenarios', 'width=800px,height=700px,left=340px,top=100px,resize=1,scrolling=1'); return false">
							<xsl:value-of select="report/notUsedScenarios/@name"/> (<xsl:value-of select="report/notUsedScenarios/count"/>)</a></div>			
	
	
						<div><a href ="#" onClick="divwin=dhtmlwindow.open('divbox5', 'div', 'newPositiveRelations', 'New Positive Relations', 'width=800px,height=700px,left=360px,top=120px,resize=1,scrolling=1'); return false">
							<xsl:value-of select="report/newPositiveRelations/@name"/> (<xsl:value-of select="report/newPositiveRelations/count"/>)</a></div>
						<div><a href ="#" onClick="divwin=dhtmlwindow.open('divbox6', 'div', 'newNegativeRelations', 'New Negative Relations', 'width=800px,height=700px,left=380px,top=140px,resize=1,scrolling=1'); return false">
							<xsl:value-of select="report/newNegativeRelations/@name"/> (<xsl:value-of select="report/newNegativeRelations/count"/>)</a></div>
						<div><a href ="#" onClick="divwin=dhtmlwindow.open('divbox7', 'div', 'verificationModels', 'Verification Models', 'width=800px,height=700px,left=400px,top=160px,resize=1,scrolling=1'); return false">
							<xsl:value-of select="report/verificationModels/@name"/> (<xsl:value-of select="report/verificationModels/count"/>)</a></div>
						<div><a href ="#" onClick="divwin=dhtmlwindow.open('divbox8', 'div', 'simulatedModels', 'Simulated Models', 'width=800px,height=700px,left=420px,top=180px,resize=1,scrolling=1'); return false">
							<xsl:value-of select="report/simulatedModels/@name"/> (<xsl:value-of select="report/simulatedModels/count"/>)</a></div>
						<div><a href ="#" onClick="divwin=dhtmlwindow.open('divbox9', 'div', 'notSimulatedModels', 'Not Simulated Models', 'width=800px,height=700px,left=440px,top=200px,resize=1,scrolling=1'); return false">
							<xsl:value-of select="report/notSimulatedModels/@name"/> (<xsl:value-of select="report/notSimulatedModels/count"/>)</a></div>
					</div>
				</div>
				<div id="violatedRequirements" class="view" >	
<!-- 					<h2>Violated Requirements ( <xsl:value-of select="report/violatedRequirements/count"/> )</h2> -->
					<div class="description red"><xsl:value-of select="report/violatedRequirements/description"/></div>
					<div class="metric red">Metric: <xsl:value-of select="report/violatedRequirements/count"/></div>
					<hr/>
					<div class="box"><xsl:apply-templates select="report/violatedRequirements/requirement"/></div>
				</div>
				<div id="notViolatedRequirements" class="view" >	
<!-- 					<h2>Not Violated Requirements ( <xsl:value-of select="report/notViolatedRequirements/count"/> )</h2> -->
					<div class="description green"><xsl:value-of select="report/notViolatedRequirements/description"/></div>
					<div class="metric green">Metric: <xsl:value-of select="report/notViolatedRequirements/count"/></div>
					<hr/>
					<div class="box"><xsl:apply-templates select="report/notViolatedRequirements/requirement"/></div>
				</div>
				<div id="notEvaluatedRequirements" class="view" >	
<!-- 					<h2>Not Evaluated Requirements ( <xsl:value-of select="report/notEvaluatedRequirements/count"/> )</h2> -->
					<div class="description"><xsl:value-of select="report/notEvaluatedRequirements/description"/></div>
					<div class="metric darkOrange">Metric: <xsl:value-of select="report/notEvaluatedRequirements/count"/></div>
					<hr/>
					<div class="box"><xsl:apply-templates select="report/notEvaluatedRequirements/requirement"/></div>
				</div>
				<div id="notImplementedRequirements" class="view" >	
					<div class="description"><xsl:value-of select="report/notImplementedRequirements/description"/></div>
					<div class="metric red">Metric: <xsl:value-of select="report/notImplementedRequirements/count"/></div>
					<hr/>
					<div class="box"><xsl:apply-templates select="report/notImplementedRequirements/requirement"/></div>
				</div>
				<div id="usedScenarios" class="view" >	
<!-- 					<h2>Used Scenarios ( <xsl:value-of select="report/usedScenarios/count"/> )</h2> -->
					<div class="description"><xsl:value-of select="report/usedScenarios/description"/></div>
					<div class="metric green">Metric: <xsl:value-of select="report/usedScenarios/count"/> </div>
					<hr/>					
					<div class="box"><xsl:apply-templates select="report/usedScenarios/scenario"/></div>
				</div>
				<div id="notUsedScenarios" class="view" >	
					<div class="description"><xsl:value-of select="report/notUsedScenarios/description"/></div>
					<div class="metric green">Metric: <xsl:value-of select="report/notUsedScenarios/count"/> </div>
					<hr/>					
					<div class="box"><xsl:apply-templates select="report/notUsedcenarios/scenario"/></div>
				</div>
				
				<div id="newPositiveRelations" class="view" >	
<!-- 					<h2>New Positive Relations ( <xsl:value-of select="report/newPositiveRelations/count"/> )</h2> -->
					<div class="description"><xsl:value-of select="report/newPositiveRelations/description"/></div>
					<div class="metric green">Metric: <xsl:value-of select="report/newPositiveRelations/count"/></div>
					<hr/>					
					<div class="box"><xsl:apply-templates select="report/newPositiveRelations/scenario"/></div>
				</div>
				<div id="newNegativeRelations" class="view" >	
<!-- 					<h2>Violated Requirements ( <xsl:value-of select="report/newNegativeRelations/count"/> )</h2> -->
					<div class="description"><xsl:value-of select="report/newNegativeRelations/description"/></div>
					<div class="metric red">Metric: <xsl:value-of select="report/newNegativeRelations/count"/></div>
					<hr/>					
					<div class="box"><xsl:apply-templates select="report/newNegativeRelations/scenario"/></div>
				</div>
				<div id="verificationModels" class="view" >	
<!-- 					<h2>Verification Models ( <xsl:value-of select="report/verificationModels/count"/> )</h2> -->
					<div class="description"><xsl:value-of select="report/verificationModels/description"/></div>
					<div class="metric">Metric: <xsl:value-of select="report/verificationModels/count"/></div>
					<hr/>
					<div class="box"><xsl:apply-templates select="report/verificationModels"/></div>
				</div>
				<div id="notSimulatedModels" class="view" >	
<!-- 					<h2>Not Simulated Models ( <xsl:value-of select="report/notSimulatedModels/count"/> )</h2> -->
					<div class="description"><xsl:value-of select="report/notSimulatedModels/description"/></div>
					<div class="metric red">Metric: <xsl:value-of select="report/notSimulatedModels/count"/></div>
					<hr/>
					<div class="box"><xsl:apply-templates select="report/notSimulatedModels"/></div>
				</div>
				<div id="simulatedModels" class="view" >	
<!-- 					<h2>Simulated Models ( <xsl:value-of select="report/simulatedModels/count"/> )</h2> -->
					<div class="description"><xsl:value-of select="report/simulatedModels/description"/></div>
					<div class="metric green">Metric:  <xsl:value-of select="report/simulatedModels/count"/></div>
					<hr/>
					<div class="box"><xsl:apply-templates select="report/simulatedModels"/></div>
				</div>

			</body>
		</html>
	</xsl:template>
	
	
	<!--***************************************************************
      ** "report/violatedRequirements/requirement" template
      ************************************************************-->
	<xsl:template match="report/violatedRequirements/requirement | report/notViolatedRequirements/requirement | report/notEvaluatedRequirements/requirement | report/notImplementedRequirements/requirement">

			<a href ="#" >
		       <xsl:attribute name="onclick">
		       <xsl:text>clip('</xsl:text>
		       <xsl:value-of select="@uid"/>
		       <xsl:text>')</xsl:text>
		       </xsl:attribute>
<!-- 		       <span class="simpleLink">... click to expand or collapse</span> -->
				<h3 style="padding:0px;"><xsl:value-of select="@id"/>: <xsl:value-of select="@name"/></h3>
			</a>
		
		
		<div class="expandable" style="display: none">
			 <xsl:attribute name="id">
			 <xsl:text>div_</xsl:text>
			 <xsl:value-of select="@uid"/>
			 </xsl:attribute>


<!-- 			<h3 style="padding:0px;"><xsl:value-of select="@id"/>: <xsl:value-of select="@name"/> -->
<!-- 					<br /> -->
					<span class="qualifiedName">
						<a href ="#">
							<xsl:attribute name="href">
								<xsl:value-of select="@locateLink"/>
							</xsl:attribute>
							<xsl:value-of select="@qualifiedName"/> 
						</a>
					</span>
<!-- 			</h3> -->
			
			<xsl:apply-templates select="text"/>
			
			<xsl:if test="comments!=''">
				<div style="margin-top:10px;"><span class="bold">Comments: </span><xsl:apply-templates select="comments"/></div>
			</xsl:if>
			
			<div class="verificationModels">
				<span class="bold">Simulated in the following verification models: </span>
				<xsl:apply-templates select="verificationModel"/>
			</div>
			
			<hr />
		</div>
		
	</xsl:template>
	
	
	<!--***************************************************************
      ** "comments" template
      ************************************************************-->
	<xsl:template match="comments">
<!-- 		<h4>Comments ( <xsl:value-of select="count"/> )</h4> -->
<!-- 		<xsl:value-of select="@qualifiedName"/> -->
<!-- 		<ul> -->
			<span><xsl:apply-templates select="comment"/></span>
			
<!-- 		</ul> -->
	</xsl:template>
	<!--***************************************************************
      ** "comment" template
      ************************************************************-->
	<xsl:template match="comments/comment">
<!-- 		<li><xsl:apply-templates/></li> -->
		<i><xsl:apply-templates/></i><br />
	</xsl:template>
	
	<!--***************************************************************
      ** "text" template
      ************************************************************-->
	<xsl:template match="text">
		<div><b>Text: </b><xsl:value-of select="."/></div>
	</xsl:template>
	
	
	<!--***************************************************************
      ** "verificationModel" template
      ************************************************************-->
	<xsl:template match="verificationModel">
		
<!-- 			<h4>Verification Model -> (<xsl:value-of select="@name"/>)</h4> -->
			
<!-- 			<script type="text/javascript"> -->
<!-- 				  $(document).ready(function() { -->
<!-- 				  // Hide the "view" div. -->
<!-- 				  $('div.viewPart').hide(); -->
<!-- 				  // Watch for clicks on the "slide" link. -->
<!-- 				  $('div.slide').click(function() { -->
<!-- 				  // When clicked, toggle the "view" div. -->
<!-- 				  $('div.viewPart').slideToggle(400); -->
<!-- 				  return false; -->
<!-- 				}); -->
<!-- 				}); -->
<!-- 				</script> -->
<!-- 			<h2 class="expand_heading"><a href="#">Verification Model: (<xsl:value-of select="@name"/>)</a></h2> -->
<!-- 			<div class="slide" style="cursor: pointer;"><a>Verification Model: (<xsl:value-of select="@name"/>)</a></div> -->

		<div class="expandableLink">
		
		VeM: <xsl:value-of select="@name"/>    
			<a href ="#" >
		       <xsl:attribute name="onclick">
		       <xsl:text>clip('</xsl:text>
		       <xsl:value-of select="@uid"/>
		       <xsl:text>')</xsl:text>
		       </xsl:attribute> ... click to expand or collapse
			</a>
		</div>
		
		<div class="expandable verificationModel" style="display: none">
			 <xsl:attribute name="id">
			 <xsl:text>div_</xsl:text>
			 <xsl:value-of select="@uid"/>
			 </xsl:attribute>
			 
			<div class="box">
				
				<span class="qualifiedName">
					<a href ="#">
						<xsl:attribute name="href">
							<xsl:value-of select="@locateLink"/>
						</xsl:attribute>
						<xsl:value-of select="@qualifiedName"/> 
					</a>
				</span>
		
				<div style="padding-top:3px;">
					<xsl:if test="@startTime!=''">startTime:<xsl:value-of select="@startTime"/>, </xsl:if>
					<xsl:if test="@stopTime!=''">stopTime:<xsl:value-of select="@stopTime"/>, </xsl:if>
					<xsl:if test="@tolerance!=''">tolerance:<xsl:value-of select="@tolerance"/>, </xsl:if>
					<xsl:if test="@interval!=''">intervals:<xsl:value-of select="@interval"/>,</xsl:if>
					<xsl:if test="@outputFormat!=''">outputFormat:<xsl:value-of select="@outputFormat"/></xsl:if>
				</div>
				
				<xsl:apply-templates select="systemModel"/>
				<xsl:apply-templates select="scenario"/>
				<xsl:apply-templates select="requirements"/>
			</div>
		</div>
	</xsl:template>
	
	<!--***************************************************************
      ** "verificationModel/systemModel" template
      ************************************************************-->
	<xsl:template match="verificationModel/systemModel">
		<div style="padding-top:10px;">
			<span>
				<span style="font-weight:bold;">System Model: </span> 
				<a href ="#">
					<xsl:attribute name="href">
						<xsl:value-of select="@locateLink"/>
					</xsl:attribute>
					<xsl:value-of select="@instanceName"/> 
					(<xsl:value-of select="@qualifiedName"/>) 
				</a>
			</span> 
		</div>
<!-- 		<h5>System Model <xsl:value-of select="@name"/></h5> -->
<!-- 		<table> -->
<!-- 			<tr><td>locateLink: </td><td><xsl:value-of select="@locateLink"/></td></tr> -->
<!-- 			<tr><td>plotLink: </td><td><xsl:value-of select="@plotLink"/></td></tr> -->
<!-- 			<tr><td>instanceName: </td><td><xsl:value-of select="@instanceName"/></td></tr> -->
<!-- 			<tr><td>qualifiedName: </td><td><xsl:value-of select="@qualifiedName"/></td></tr> -->
<!-- 		</table> -->
		<xsl:apply-templates select="comments"/>
		
		<div style="margin-left:20px; margin-top:5px;">
			<xsl:apply-templates select="client"/>
		</div>
		
	</xsl:template>
	<!--***************************************************************
      ** "verificationModel/scenario" template
      ************************************************************-->
	<xsl:template match="verificationModel/scenario">
		<div style="padding-top:10px;">
			<span>
				<span style="font-weight:bold;">Scenario: </span> 
				<a href ="#">
					<xsl:attribute name="href">
						<xsl:value-of select="@locateLink"/>
					</xsl:attribute>
					<xsl:value-of select="@instanceName"/> 
					(<xsl:value-of select="@qualifiedName"/>) 
				</a>
			</span> 
		</div>
	
<!-- 		<h3>Scenario ( <xsl:value-of select="@name"/> )</h3> -->
		
<!-- 		<table> -->
<!-- 			<tr><td>locateLink: </td><td><xsl:value-of select="@locateLink"/></td></tr> -->
<!-- 			<tr><td>plotLink: </td><td><xsl:value-of select="@plotLink"/></td></tr> -->
<!-- 			<tr><td>instanceName: </td><td><xsl:value-of select="@instanceName"/></td></tr> -->
<!-- 			<tr><td>qualifiedName: </td><td><xsl:value-of select="@qualifiedName"/></td></tr> -->
<!-- 		</table> -->
		<xsl:apply-templates select="comments"/>
		
		<div style="margin-left:20px; margin-top:5px;">
			<xsl:apply-templates select="client"/>
		</div>
		
	</xsl:template>
	
	<!--***************************************************************
      ** "verificationModel/requirements" template
      ************************************************************-->
	<xsl:template match="verificationModel/requirements">
<!-- 		<h3>Requirements ( <xsl:value-of select="count"/> )</h3> -->
		<xsl:apply-templates select="requirement"/>
	</xsl:template>
	
	<!--***************************************************************
      ** "verificationModel/requirements/requirement" template
      ************************************************************-->
	<xsl:template match="verificationModel/requirements/requirement">
		<div style="padding-top:10px;">
			<span>
				<span style="font-weight:bold;"><xsl:if test="@isViolated='true'"><span class="red">VIOLATED </span></xsl:if>Requirement (<xsl:value-of select="@id"/>): </span> 
				<a href ="#">
					<xsl:attribute name="href">
						<xsl:value-of select="@locateLink"/>
					</xsl:attribute>
					<xsl:value-of select="@instanceName"/> 
<!-- 					(<xsl:value-of select="@qualifiedName"/>)  -->
				</a>
			</span> 
		</div>
<!-- 		<xsl:apply-templates select="text"/> -->
<!-- 		<div style="margin-left:20px; margin-top:10px; background-color:#F9F9F9; border:1px #aaa9a9 solid; "> -->
		<div style="margin-left:20px; margin-top:5px;">
			<xsl:apply-templates select="client"/>
		</div>
		
		
<!-- 		<h3>Requirement <xsl:value-of select="@id"/>: <xsl:value-of select="@name"/></h3> -->
<!-- 		<xsl:value-of select="@qualifiedName"/><br/><br/> -->
<!-- 		<table> -->
<!-- 			<tr><td>isViolated: </td><td><xsl:value-of select="@isViolated"/></td></tr> -->
<!-- 			<tr><td>isEvaluated: </td><td><xsl:value-of select="@isEvaluated"/></td></tr> -->
<!-- 			<tr><td>instanceName: </td><td><xsl:value-of select="@instanceName"/></td></tr> -->
<!-- 			<tr><td>locateLink: </td><td><xsl:value-of select="@locateLink"/></td></tr> -->
<!-- 			<tr><td>plotLink: </td><td><xsl:value-of select="@plotLink"/></td></tr> -->
<!-- 		</table><br/> -->
<!-- 		<xsl:apply-templates select="text"/> -->
<!-- 		<xsl:apply-templates select="client"/> -->
	</xsl:template>
	
	<!--***************************************************************
      ** "verificationModel/requirements/requirement/client" template
      ************************************************************-->
	<xsl:template match="verificationModel/requirements/requirement/client | verificationModel/systemModel/client | verificationModel/scenario/client">
		
		<div style="margin_bottom:10px;">
			Client <xsl:if test="@isMandatory= 'true'"><span>(mandatory)</span></xsl:if>: 
			<a href ="#">
				<xsl:attribute name="href">
					<xsl:value-of select="@locateLink"/>
				</xsl:attribute>
				<xsl:value-of select="@clientPath"/>
			</a>
			= <xsl:apply-templates select="binding"/>
		</div> 
		
<!-- 		<h4>Client</h4> -->
<!-- 		<table> -->
<!-- 			<tr><td>instanceName: </td><td><xsl:value-of select="@instanceName"/></td></tr> -->
<!-- 			<tr><td>isMandatory: </td><td><xsl:value-of select="@isMandatory"/></td></tr> -->
<!-- 			<tr><td>locateLink: </td><td><xsl:value-of select="@locateLink"/></td></tr> -->
<!-- 			<tr><td>plotLink: </td><td><xsl:value-of select="@plotLink"/></td></tr> -->
<!-- 		</table><br/> -->
<!-- 		<b>Binding: </b><xsl:apply-templates select="binding"/><br/> -->
	</xsl:template>







	<!--***************************************************************
      ** "report/violatedRequirements/requirement" template
      ************************************************************-->
<!-- 	<xsl:template match="report/notViolatedRequirements/requirement"> -->
<!-- 		<h3>Requirement <xsl:value-of select="@id"/>: <xsl:value-of select="@name"/></h3> -->
<!-- 		<xsl:value-of select="@qualifiedName"/> -->
<!-- 		<xsl:apply-templates select="comments"/> -->
<!-- 		<xsl:apply-templates select="text"/> -->
<!-- 		<xsl:apply-templates select="verificationModel"/> -->
<!-- 	</xsl:template> -->






	<!--***************************************************************
      ** "report/UsedScenarios/scenario" template
      ************************************************************-->
	<xsl:template match="report/usedScenarios/scenario | report/notUsedScenarios/scenario">
		<h3>Scenario: <xsl:value-of select="@name"/> 
			<br /><span class="qualifiedName">
					<a href ="#">
						<xsl:attribute name="href">
							<xsl:value-of select="@locateLink"/>
						</xsl:attribute>
						<xsl:value-of select="@qualifiedName"/> 
					</a>
				</span>
		</h3>
<!-- 		<xsl:value-of select="@qualifiedName"/> -->
		<xsl:apply-templates select="comments"/>
		<xsl:apply-templates select="verificationModel"/>
	</xsl:template>






	<!--***************************************************************
      ** "report/newPositiveRelations/scenario" template
      ************************************************************-->
	<xsl:template match="report/newPositiveRelations/scenario | report/newNegativeRelations/scenario">
		<div style="margin-bottom:30px;">
			<h3>Scenario: <xsl:value-of select="@name"/>
				<br /><span class="qualifiedName">
						<a href ="#">
							<xsl:attribute name="href">
								<xsl:value-of select="@locateLink"/>
							</xsl:attribute>
							<xsl:value-of select="@qualifiedName"/> 
						</a>
					</span>
			</h3>
	<!-- 		<xsl:value-of select="@qualifiedName"/> -->
			<xsl:apply-templates select="comments"/>
			<div style="margin-left:20px;">
				<xsl:apply-templates select="requirement"/>
				<xsl:apply-templates select="verificationModel"/>
			</div>
		</div>
	</xsl:template>




	<!--***************************************************************
      ** "scenario/requirement" template
      ************************************************************-->
	<xsl:template match="scenario/requirement">
		<h4>Requirement <xsl:value-of select="@id"/>: <xsl:value-of select="@name"/>
			<br /><span class="qualifiedName">
				<a href ="#">
					<xsl:attribute name="href">
						<xsl:value-of select="@locateLink"/>
					</xsl:attribute>
					<xsl:value-of select="@qualifiedName"/> 
				</a>
			</span>
		</h4>
<!-- 		<table> -->
<!-- 			<tr><td>locateLink: </td><td><xsl:value-of select="@locateLink"/></td></tr> -->
<!-- 			<tr><td>instanceName: </td><td><xsl:value-of select="@instanceName"/></td></tr> -->
<!-- 			<tr><td>qualifiedName: </td><td><xsl:value-of select="@qualifiedName"/></td></tr> -->
<!-- 		</table><br/> -->
	</xsl:template>






	<!--***************************************************************
      ** "report/verificationModels" template
      ************************************************************-->
	<xsl:template match="report/verificationModels | report/notSimulatedModels | report/simulatedModels">
		<xsl:apply-templates select="verificationModel"/>
	</xsl:template>

</xsl:stylesheet>
