/*
* generated by Xtext
*/
package org.openmodelica.modelicaml.editor.xtext.equation.parser.antlr;

import java.io.InputStream;

import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class EquationsectionAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.tokens");
	}
}
