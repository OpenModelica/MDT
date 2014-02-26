<%--encoding=utf-8--%>
<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
%>

<%-- Comments --%>
<%script type="uml.Element" name="comment"%>
<%string_comment%><%annotation%>

<%script type="uml.Element" name="string_comment" post="trim()"%>
<%if (getProperty("c_includeModelComments") && ownedComment[!hasStereotype(getProperty("s_annotation"))]!=null){%>"<%for (ownedComment[!hasStereotype(getProperty("s_annotation"))]){%><%body.replaceAll("\\n","").replaceAll("\\r","").replaceAll("\"", "'")%> <%}%>"<%}%>

<%script type="uml.Element" name="annotation" post="trim()"%>
<%if (ownedComment.annotation_fullString != null){%> annotation(<%ownedComment.annotation_fullString%>)<%}%>

<%script type="uml.Comment" name="annotation_fullString" post="trim()"%>
<%getStereotypeValue(getProperty("s_annotation"), getProperty("s_p_fullAnnotationString"))%>

<%-- ################## Placeholder for additional includes --%>
<%-- add_inc_structure: e.g. in ModelicaML for including instances of state machines --%>
<%-- add_inc_behavior: e.g. in ModelicaML for including state machines or activities code --%>
<%-- add_inc_top_file: e.g. for including (comments) information about the code generation --%>
<%-- add_inc_validation: e.g. for including a model-validation step before code generation --%>

<%script type="uml.NamedElement" name="add_inc_structure"%>
<%script type="uml.NamedElement" name="add_inc_behavior"%>
<%script type="uml.Classifier" name="add_inc_top_file"%>
<%script type="uml.Package" name="add_inc_top_file"%>
<%script type="uml.Classifier" name="add_inc_validation"%>
<%script type="uml.Package" name="add_inc_validation"%>
<%script type="uml.Package" name="add_inc_top_annotation"%>
<%-- ################## Placeholder for additional includes END --%>
