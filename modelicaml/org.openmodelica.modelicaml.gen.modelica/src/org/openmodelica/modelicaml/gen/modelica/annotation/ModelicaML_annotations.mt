<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
%>


<%script type="uml.Class" name="g_annotation"%>
<%if (hasStereotype(getProperty("s_package"))){%><%graphical_annotation_package%>
<%}else{%><%graphical_annotation_class%><%}%>

<%script type="uml.Class" name="graphical_annotation_class"%>
Diagram(coordinateSystem(extent={{-148.5,-105.0},{148.5,105.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10})), Icon(coordinateSystem(extent={{-100.0,-100.0},{100.0,100.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10}), graphics={Rectangle(visible=true, origin={0.0,65.0}, fillColor={255,255,255}, fillPattern=FillPattern.Solid, lineThickness=1, extent={{-100.0,-35.0},{100.0,35.0}}),Rectangle(visible=true, origin={0.0,-35.0}, fillColor={255,255,255}, fillPattern=FillPattern.Solid, lineThickness=1, extent={{-100.0,-65.0},{100.0,65.0}}),Text(visible=true, origin={0.0,52.0578}, fillPattern=FillPattern.Solid, extent={{-96.311,-15.0969},{96.311,15.0969}}, textString="<%name%>", fontName="Arial"),Text(visible=true, origin={0.0,83.1521}, fillPattern=FillPattern.Solid, extent={{-90.0,-11.3192},{90.0,11.3192}}, textString="«<%restriction%>»", fontName="Arial"),Text(visible=true, origin={0.3349,-37.1756}, fillPattern=FillPattern.Solid, extent={{-97.7954,-59.2801},{97.7954,59.2801}}, textString="<%type_name_abr%>", fontName="Arial")})
<%script type="uml.Class" name="graphical_annotation_package"%>
Diagram(coordinateSystem(extent={{-148.5,-105.0},{148.5,105.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10})), Icon(coordinateSystem(extent={{-100.0,-100.0},{100.0,100.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10}), graphics={Rectangle(visible=true, origin={0.0,-11.4622}, fillColor={190,224,255}, fillPattern=FillPattern.Solid, extent={{-100.0,-58.5379},{100.0,58.5379}}),Rectangle(visible=true, origin={-55.0,63.5379}, fillColor={255,255,255}, fillPattern=FillPattern.Solid, extent={{-45.0,-16.4621},{45.0,16.4621}}),Text(visible=true, origin={0.0,-10.0}, fillPattern=FillPattern.Solid, extent={{-93.2419,-12.7008},{93.2419,12.7008}}, textString="<%name%>", fontName="Arial", textStyle={TextStyle.Bold})})

<%script type="uml.Class" name="restriction" post="trim()"%>
<%if (hasStereotype(getProperty("s_package")) || hasStereotype(getProperty("s_operator"))){%>package<%}%>
<%if (hasStereotype(getProperty("s_model")) || hasStereotype(getProperty("s_class"))){%>model<%}%>
<%if (hasStereotype(getProperty("s_block"))){%>block<%}%>
<%if (hasStereotype(getProperty("s_connector"))){%>connector<%}%>
<%if (hasStereotype(getProperty("s_record"))){%>record<%}%>
<%if (hasStereotype(getProperty("s_calculationModel"))){%>calculationModel<%}%>
<%if (hasStereotype(getProperty("s_requirement"))){%>requirement<%}%>

<%script type="uml.Class" name="type_name_abr" post="trim()"%>
<%if (restriction == "model"){%>M<%}%>
<%if (restriction == "block"){%>B<%}%>
<%if (restriction == "record"){%>R<%}%>
<%if (restriction == "connector"){%>C<%}%>
<%if (restriction == "package"){%>P<%}%>
<%if (restriction == "calculationModel"){%>cM<%}%>
<%if (restriction == "requirement"){%>Rq<%}%>


<%script type="uml.FunctionBehavior" name="g_annotation" post="trim()"%>
<%graphical_annotation%>
<%script type="uml.FunctionBehavior" name="graphical_annotation" post="trim()"%>
Diagram(coordinateSystem(extent={{-148.5,-105.0},{148.5,105.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10})), Icon(coordinateSystem(extent={{-100.0,-100.0},{100.0,100.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10}), graphics={Line(visible=true, origin={-37.381,-45.476}, points={{-57.619,-9.524},{-57.619,20.476},{-37.619,20.476},{-37.619,40.476},{-17.619,40.476},{-17.619,50.476},{12.381,50.476},{12.381,40.476},{32.381,40.476},{32.381,20.476},{52.381,20.476},{52.381,-9.524},{32.381,-9.524},{32.381,-29.524},{12.381,-29.524},{12.381,-39.524},{-17.619,-39.524},{-17.619,-29.524},{-37.619,-29.524},{-37.619,-9.524},{-57.619,-9.524}}, thickness=10),Line(visible=true, origin={-38.654,-42.2231}, points={{-13.846,4.615},{-3.846,4.615},{-3.846,14.615},{6.154,14.615},{6.154,4.615},{16.154,4.615},{16.154,-5.385},{6.154,-5.385},{6.154,-15.385},{-3.846,-15.385},{-3.846,-5.385},{-13.846,-5.385},{-13.846,4.615}}, color={128,128,128}, thickness=5),Line(visible=true, origin={45.4776,34.524}, points={{-57.619,-9.524},{-57.619,20.476},{-37.619,20.476},{-37.619,40.476},{-17.619,40.476},{-17.619,50.476},{12.381,50.476},{12.381,40.476},{32.381,40.476},{32.381,20.476},{52.381,20.476},{52.381,-9.524},{32.381,-9.524},{32.381,-29.524},{12.381,-29.524},{12.381,-39.524},{-17.619,-39.524},{-17.619,-29.524},{-37.619,-29.524},{-37.619,-9.524},{-57.619,-9.524}}, thickness=10),Line(visible=true, origin={41.0348,40.385}, points={{-13.846,4.615},{-3.846,4.615},{-3.846,14.615},{6.154,14.615},{6.154,4.615},{16.154,4.615},{16.154,-5.385},{6.154,-5.385},{6.154,-15.385},{-3.846,-15.385},{-3.846,-5.385},{-13.846,-5.385},{-13.846,4.615}}, color={128,128,128}, thickness=5)})


<%script type="uml.StateMachine" name="g_annotation" post="trim()"%>
<%graphical_annotation%>
<%script type="uml.StateMachine" name="graphical_annotation" post="trim()"%>
Diagram(coordinateSystem(extent={{-148.5,-105.0},{148.5,105.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10})), Icon(coordinateSystem(extent={{-100.0,-100.0},{100.0,100.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10}), graphics={Rectangle(visible=true, origin={-6.722,3.6841}, lineColor={0,85,127}, fillColor={85,170,255}, fillPattern=FillPattern.Solid, lineThickness=10, extent={{-75.0,-50.0},{75.0,50.0}}, radius=40),Rectangle(visible=true, origin={70.0,2.6556}, lineColor={85,170,255}, fillColor={0,85,127}, fillPattern=FillPattern.Solid, lineThickness=4, extent={{-26.8908,-25.0},{26.8908,25.0}})})

<%script type="uml.StateMachine" name="g_annotation_finalstate" post="trim()"%>
Diagram(coordinateSystem(extent={{-148.5,-105.0},{148.5,105.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10})), Icon(coordinateSystem(extent={{-100.0,-100.0},{100.0,100.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10}), graphics={Rectangle(visible=true, origin={0.0,0.0}, lineColor={0,85,127}, fillColor={255,255,255}, fillPattern=FillPattern.HorizontalCylinder, lineThickness=10, extent={{-34.8134,-34.6638},{34.8134,34.6638}}, radius=40),Rectangle(visible=true, origin={0.0,0.0}, fillPattern=FillPattern.HorizontalCylinder, lineThickness=10, extent={{-17.2767,-17.2024},{17.2767,17.2024}}, radius=40)})

<%script type="uml.StateMachine" name="g_annotation_state" post="trim()"%>
<%graphical_annotation_state%>
<%script type="uml.StateMachine" name="graphical_annotation_state" post="trim()"%>
Diagram(coordinateSystem(extent={{-148.5,-105.0},{148.5,105.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10})), Icon(coordinateSystem(extent={{-100.0,-100.0},{100.0,100.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10}), graphics={Rectangle(visible=true, origin={-0.0,-0.1675}, lineColor={0,85,127}, fillColor={104,182,221}, fillPattern=FillPattern.HorizontalCylinder, lineThickness=10, extent={{-64.6031,-34.6638},{64.6031,34.6638}}, radius=40)})

<%script type="uml.StateMachine" name="g_annotation_initialstate" post="trim()"%>
Diagram(coordinateSystem(extent={{-148.5,-105.0},{148.5,105.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10})), Icon(coordinateSystem(extent={{-100.0,-100.0},{100.0,100.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10}), graphics={Rectangle(visible=true, origin={-0.0,0.0}, lineColor={0,85,127}, fillColor={104,182,221}, fillPattern=FillPattern.HorizontalCylinder, lineThickness=10, extent={{-34.8134,-34.6638},{34.8134,34.6638}}, radius=40)})

<%script type="uml.Region" name="g_annotation_historystate" post="trim()"%>
Diagram(coordinateSystem(extent={{-148.5,-105.0},{148.5,105.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10})), Icon(coordinateSystem(extent={{-100.0,-100.0},{100.0,100.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10}), graphics={Rectangle(visible=true, origin={-0.0,0.0}, lineColor={0,85,127}, fillColor={104,182,221}, fillPattern=FillPattern.HorizontalCylinder, lineThickness=10, extent={{-36.162,-36.0066},{36.162,36.0066}}, radius=40),Text(visible=true, origin={-0.0,-1.9063}, fillPattern=FillPattern.Solid, extent={{-56.7804,-50.2136},{56.7804,50.2136}}, textString="H", fontName="Courier New", textStyle={TextStyle.Bold})})

<%script type="uml.Region" name="g_annotation_finalstate" post="trim()"%>
Diagram(coordinateSystem(extent={{-148.5,-105.0},{148.5,105.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10})), Icon(coordinateSystem(extent={{-100.0,-100.0},{100.0,100.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10}), graphics={Rectangle(visible=true, origin={0.0,0.0}, lineColor={0,85,127}, fillColor={255,255,255}, fillPattern=FillPattern.HorizontalCylinder, lineThickness=10, extent={{-34.8134,-34.6638},{34.8134,34.6638}}, radius=40),Rectangle(visible=true, origin={0.0,0.0}, fillPattern=FillPattern.HorizontalCylinder, lineThickness=10, extent={{-17.2767,-17.2024},{17.2767,17.2024}}, radius=40)})

<%script type="uml.State" name="g_annotation_state" post="trim()"%>
<%graphical_annotation_state%>
<%script type="uml.State" name="graphical_annotation_state" post="trim()"%>
Diagram(coordinateSystem(extent={{-148.5,-105.0},{148.5,105.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10})), Icon(coordinateSystem(extent={{-100.0,-100.0},{100.0,100.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10}), graphics={Rectangle(visible=true, origin={-0.0,-0.1675}, lineColor={0,85,127}, fillColor={104,182,221}, fillPattern=FillPattern.HorizontalCylinder, lineThickness=10, extent={{-64.6031,-34.6638},{64.6031,34.6638}}, radius=40)})


<%script type="uml.Region" name="g_annotation" post="trim()"%>
<%graphical_annotation%>
<%script type="uml.Region" name="graphical_annotation" post="trim()"%>
Diagram(coordinateSystem(extent={{-148.5,-105.0},{148.5,105.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10})), Icon(coordinateSystem(extent={{-100.0,-100.0},{100.0,100.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10}), graphics={Rectangle(visible=true, origin={0.3349,3.6841}, lineColor={0,85,127}, fillColor={104,182,221}, fillPattern=FillPattern.Solid, lineThickness=10, extent={{-84.7336,-50.0},{84.7336,50.0}}, radius=40),Rectangle(visible=true, origin={2.0095,40.0}, fillPattern=FillPattern.Solid, extent={{-2.0095,-8.4441},{2.0095,8.4441}}),Rectangle(visible=true, origin={2.0095,4.4251}, fillPattern=FillPattern.Solid, extent={{-2.0095,-8.4441},{2.0095,8.4441}}),Rectangle(visible=true, origin={2.0095,-31.5559}, fillPattern=FillPattern.Solid, extent={{-2.0095,-8.4441},{2.0095,8.4441}}),Rectangle(visible=true, origin={-30.0,70.0}, lineColor={0,85,127}, fillColor={255,255,255}, fillPattern=FillPattern.Solid, lineThickness=2, extent={{-25.0,-10.0},{25.0,10.0}})})


<%script type="uml.Package" name="g_annotation" post="trim()"%>
<%graphical_annotation%>
<%script type="uml.Package" name="graphical_annotation" post="trim()"%>
Diagram(coordinateSystem(extent={{-148.5,-105.0},{148.5,105.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10})), Icon(coordinateSystem(extent={{-100.0,-100.0},{100.0,100.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10}), graphics={Rectangle(visible=true, origin={0.0,-11.4622}, fillColor={190,224,255}, fillPattern=FillPattern.Solid, extent={{-100.0,-58.5379},{100.0,58.5379}}),Rectangle(visible=true, origin={-55.0,63.5379}, fillColor={255,255,255}, fillPattern=FillPattern.Solid, extent={{-45.0,-16.4621},{45.0,16.4621}}),Text(visible=true, origin={0.0,-10.0}, fillPattern=FillPattern.Solid, extent={{-93.2419,-12.7008},{93.2419,12.7008}}, textString="<%name%>", fontName="Arial", textStyle={TextStyle.Bold})})


<%script type="uml.Model" name="g_annotation" post="trim()"%>
<%graphical_annotation%>
<%script type="uml.Model" name="graphical_annotation" post="trim()"%>
Diagram(coordinateSystem(extent={{-148.5,-105.0},{148.5,105.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10})), Icon(coordinateSystem(extent={{-100.0,-100.0},{100.0,100.0}}, preserveAspectRatio=true, initialScale=0.1, grid={10,10}), graphics={Rectangle(visible=true, origin={0.0,-11.4622}, fillColor={190,224,255}, fillPattern=FillPattern.Solid, extent={{-100.0,-58.5379},{100.0,58.5379}}),Rectangle(visible=true, origin={-55.0,63.5379}, fillColor={255,255,255}, fillPattern=FillPattern.Solid, extent={{-45.0,-16.4621},{45.0,16.4621}}),Text(visible=true, origin={0.0,-42.7008}, fillPattern=FillPattern.Solid, extent={{-93.2419,-12.7008},{93.2419,12.7008}}, textString="<%name%>", fontName="Arial", textStyle={TextStyle.Bold}),Polygon(visible=true, origin={31.1881,0.7502}, fillColor={255,255,255}, fillPattern=FillPattern.Solid, points={{-1.1881,29.2498},{50.7679,-14.6249},{-49.5798,-14.6249}})})