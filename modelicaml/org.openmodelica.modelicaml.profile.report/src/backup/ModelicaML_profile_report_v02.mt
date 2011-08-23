<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML

%>

<%script type="uml.Profile" name="ProfileName"%>
<%if (name=="ModelicaML"){%><%name%>_Profile_version_<%eAnnotations.contents.nFirst().eAllContents().nGet(1).split(":").nLast().trim().split("\)").nFirst().replaceAll("\.", "_")%>.html<%}%>


<%script type="uml.Profile" name="ModelicaML_profile_report_v01" file="<%ProfileName%>"%>
<html>
	<head>
		<title>ModelicaML (UML Profile) - Version <%eAnnotations.contents.nFirst().eAllContents().nGet(1).split(":").nLast().trim().split("\)").nFirst()%></title>
		<style TYPE="text/css">
		body, td {
			font-family: Verdana; 
			font-size:11px;
		}
		h2, h3, h4 {padding-bottom:0px; margin-bottom:0px; }
		table {width:500px}
		</style>
	<head>
<body>

	<h1>ModelicaML (UML Profile)</h1>
	Profile Version: <strong><%eAnnotations.contents.nFirst().eAllContents().nGet(1).split(":").nLast().trim().split("\)").nFirst()%></strong>
	<br><hr /><br>
	
	Contents:<br />
<%for (nestedPackage){%>
	<%nestedProfilesLinks%>
<%}%>
<br /><hr /><br />
<%for (nestedPackage){%>
	<%nestedProfiles%>
<%}%>

</body>
</html>

<%script type="uml.Package" name="nestedProfilesLinks"%>
<%if (ownedElement.filter("PrimitiveType") != null || ownedElement.filter("Stereotype") != null){%>
 - <a href="#<%namespace.name%>.<%name%>"><%name%></a><br />
<%}%>

<%script type="uml.Package" name="nestedProfiles"%>
<%if (ownedElement.filter("PrimitiveType") != null || ownedElement.filter("Stereotype") != null){%>
	<a name="<%namespace.name%>.<%name%>"></a>
	<h2 style="color:#01DF01">Package: <%name%></h2>
	
	<%for (ownedElement.filter("PrimitiveType")){%>
			<h3 style="color:#BDBDBD;">Primitive Type: <span style="color:#000000;"><strong><%name%></strong></span></h3>
				
				<%for (ownedElement.filter("Property")){%>
					<span style="color:#BDBDBD">Property:</span> <%name%> <span style="color:#BDBDBD">(<%lower%>..<%upper%>)</span>, <span style="color:#BDBDBD">of type:</span> <%type.name%> <%if (type.ownedElement.filter("EnumerationLiteral").name != null){%>(<%type.ownedElement.filter("EnumerationLiteral").name.sepStr(", ")%>)<%}%><br />
				<%}%>
	<%}%>
	
	<%if (ownedElement.filter("Stereotype") != null){%>
		<%for (ownedElement.filter("Stereotype")){%>
			<a name="<%namespace.name%>.<%name%>"></a>
			<h3 style="color:#BDBDBD;">Stereotype: <span style="color:#000000;">&#171;<%name%>&#187;</span><%if (isAbstract){%> <span style="color:#FF0000">(is abstract)</span><%}%></h3>
				<%ownedComment.body.replaceAll("\n", "<br />")%>
				<br /><br />
				<%if (superClass.name != null){%>Inherits from the Stereotype(s): 
				<a href="#<%superClass.namespace.name%>.<%superClass.name%>"><span style="color:#0080FF; font-weight:bold;">&#171;<%superClass.name.sepStr(", ")%>&#187;</span></a> 
				the properties: <span style="color:#0080FF; font-weight:bold;"><%getInheritedMembers().name.sepStr(", ")%></span>
				<br /><%}%>
				<%if (getExtendedMetaclasses().name != null){%>Extends the UML meta-class(es): <span style="color:#0080FF; font-weight:bold;">	<%getExtendedMetaclasses().name.sepStr(", ")%></span><br /><br /><%}%>
				
				<%for (ownedElement.filter("Property")){%>	
					<%if (!name.startsWith("base_")){%>
						<span style="color:#BDBDBD">Property:</span> <%name%> <span style="color:#BDBDBD">(<%lower%>..<%upper%>)</span>, <span style="color:#BDBDBD">of type:</span> <%type.name%> <%if (type.ownedElement.filter("EnumerationLiteral").name != null){%>(<%type.ownedElement.filter("EnumerationLiteral").name.sepStr(", ")%>)<%}%><br />
					<%}%>
				<%}%>
				-------------------------------------------------------------------------------------------------------------
		<%}%>
	<%}%>
	<br /><br />
<%}%>

	
	
	
	
