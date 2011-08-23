<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import eadsiw.modelicaml.UmlServices
%>

<%script type="uml.Profile" name="ProfileName"%>
<%if (name=="ModelicaML"){%><%name%>_description_profile_version_<%eAnnotations.contents.nFirst().eAllContents().nGet(1).split(":").nLast().trim().split("\)").nFirst().replaceAll("\.", "_")%>.html<%}%>


<%script type="uml.Profile" name="ModelicaML_profile_report_v01" file="<%ProfileName%>"%>
<html>
	<head>
		<title>ModelicaML Profile - Full List Of Stereotypes (v<%eAnnotations.contents.nFirst().eAllContents().nGet(1).split(":").nLast().trim().split("\)").nFirst()%>)</title>
		
	<head>
<body>

	<h1>ModelicaML Profile - Full List Of Stereotypes</h1>
	Profile Version: <strong><%eAnnotations.contents.nFirst().eAllContents().nGet(1).split(":").nLast().trim().split("\)").nFirst()%></strong>
	<br />
	<br />
The following sections describe how to represent the: <br />
<%ownedElement.filter("Package").nestedProfilesLinks%>
using the ModelicaML profile. <em>Note: In each section the stereotypes are sorted alphabetically.</em>
<br /><br />
<%ownedElement.filter("Package").Stereotypes%>
<%ownedElement.filter("Package").PrimitiveTypes%>

</body>
</html>

<%script type="uml.Package" name="nestedProfilesLinks"%>
<%if ((ownedElement.filter("PrimitiveType").nSize() > 0 || ownedElement.filter("Stereotype").nSize() > 0) && !hasStereotype("ExcludeFromReport")){%>
	<a href="#<%namespace.name%>.<%name%>">Modelica <%name%></a><br />
<%}%>

<%script type="uml.Package" name="Stereotypes"%>
<%if (ownedElement.filter("Stereotype").nSize() > 0 && !hasStereotype("ExcludeFromReport")){%>
	<a name="<%namespace.name%>.<%name%>"></a>
	<h2><%name%></h2>
	<%if (ownedElement.filter("Stereotype") != null){%>
		<%for (ownedElement.filter("Stereotype").nSort()){%>
			<a name="<%namespace.name%>.<%name%>"></a><h3>Stereotype: <span>&#171;<%name%>&#187;</span><%if (isAbstract){%> <span>(is abstract)</span><%}%></h3>
				<%ownedComment.body.replaceAll("\n", "<br />")%>
				<br /><br />
				<%if (superClass.name != null){%>Inherits from the Stereotype(s): 
				<a href="#<%superClass.namespace.name%>.<%superClass.name%>"><span>&#171;<strong><%superClass.name.sepStr(", ")%></strong>&#187;</span></a> 
				the properties: <span><%getInheritedMembers().name.sepStr(", ")%></span>
				<br /><%}%>
				<%if (getExtendedMetaclasses().name != null){%>Extends the UML meta-class(es): <span><strong><%getExtendedMetaclasses().name.sepStr(", ")%></strong></span><br /><br /><%}%>
				
				<%for (ownedElement.filter("Property")){%>	
					<%if (!name.startsWith("base_")){%>
						<span>Property:</span> <strong><%name%></strong> <span>(multiplicity of entries: <%lower.replaceAll("-1", "*")%>..<%upper.replaceAll("-1", "*")%>)</span>, <span> type:</span> <%type.name%><%if (type.ownedElement.filter("EnumerationLiteral").name != null){%> (<%type.ownedElement.filter("EnumerationLiteral").name.sepStr(", ")%>)<%}%>. <%if (ownedComment.nSize()>0){%><em>Description: <%ownedComment.body.sepStr(" ")%></em><br /><%}%>
						<br />
					<%}%>
				<%}%>
				<br />
		<%}%>
	<%}%>
	<br /><br />
<%}%>

<%script type="uml.Package" name="PrimitiveTypes"%>
<%if (ownedElement.filter("PrimitiveType").nSize() > 0 && !hasStereotype("ExcludeFromReport")){%>
	<a name="<%namespace.name%>.<%name%>"></a>
	<h2><%name%></h2>
	
	<%for (ownedElement.filter("PrimitiveType").nSort()){%>
			<h3>Primitive Type: <span><strong><%name%></strong></span></h3>
				
				<%for (ownedElement.filter("Property")){%>
					<span>Property:</span> <strong><%name%></strong> <span>(multiplicity of entries: <%lower.replaceAll("-1", "*")%>..<%upper.replaceAll("-1", "*")%>)</span>, <span> type:</span> <%type.name%> <%if (type.ownedElement.filter("EnumerationLiteral").name != null){%>(<%type.ownedElement.filter("EnumerationLiteral").name.sepStr(", ")%>)<%}%>.<br />
				<%}%>
	<%}%>
	<br /><br />
<%}%>

	
	
	
	
