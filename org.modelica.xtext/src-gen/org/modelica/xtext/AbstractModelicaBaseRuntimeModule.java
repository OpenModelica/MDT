
/*
 * generated by Xtext
 */
package org.modelica.xtext;

import org.eclipse.xtext.Constants;
import org.eclipse.xtext.service.DefaultRuntimeModule;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Manual modifications go to {org.modelica.xtext.ModelicaBaseRuntimeModule}
 */
public abstract class AbstractModelicaBaseRuntimeModule extends DefaultRuntimeModule {
	
	@Override
	public void configure(Binder binder) {
		super.configure(binder);
		binder.bind(String.class).annotatedWith(Names.named(Constants.LANGUAGE_NAME)).toInstance(
			"org.modelica.xtext.ModelicaBase");
	}
	
	
	// contributed by org.eclipse.xtext.generator.grammarAccess.GrammarAccessFragment
	public Class<? extends org.eclipse.xtext.IGrammarAccess> bindIGrammarAccess() {
		return org.modelica.xtext.services.ModelicaBaseGrammarAccess.class;
	}

	// contributed by org.eclipse.xtext.generator.parseTreeConstructor.ParseTreeConstructorFragment
	public Class<? extends org.eclipse.xtext.parsetree.reconstr.IParseTreeConstructor> bindIParseTreeConstructor() {
		return org.modelica.xtext.parseTreeConstruction.ModelicaBaseParsetreeConstructor.class;
	}

	// contributed by de.itemis.xtext.antlr.XtextAntlrGeneratorFragment
	public Class<? extends org.eclipse.xtext.parser.antlr.IAntlrParser> bindIAntlrParser() {
		return org.modelica.xtext.parser.antlr.ModelicaBaseParser.class;
	}

	// contributed by de.itemis.xtext.antlr.XtextAntlrGeneratorFragment
	public Class<? extends org.eclipse.xtext.parser.ITokenToStringConverter> bindITokenToStringConverter() {
		return org.eclipse.xtext.parser.antlr.AntlrTokenToStringConverter.class;
	}

	// contributed by de.itemis.xtext.antlr.XtextAntlrGeneratorFragment
	public Class<? extends org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider> bindIAntlrTokenFileProvider() {
		return org.modelica.xtext.parser.antlr.ModelicaBaseAntlrTokenFileProvider.class;
	}

	// contributed by de.itemis.xtext.antlr.XtextAntlrGeneratorFragment
	public Class<? extends org.eclipse.xtext.parser.antlr.Lexer> bindLexer() {
		return org.modelica.xtext.parser.antlr.internal.InternalModelicaBaseLexer.class;
	}

	// contributed by de.itemis.xtext.antlr.XtextAntlrGeneratorFragment
	public Class<? extends org.eclipse.xtext.parser.antlr.ITokenDefProvider> bindITokenDefProvider() {
		return org.eclipse.xtext.parser.antlr.AntlrTokenDefProvider.class;
	}

	// contributed by org.eclipse.xtext.generator.validation.JavaValidatorFragment
	@org.eclipse.xtext.service.SingletonBinding(eager=true)	public Class<? extends org.modelica.xtext.validation.ModelicaBaseJavaValidator> bindModelicaBaseJavaValidator() {
		return org.modelica.xtext.validation.ModelicaBaseJavaValidator.class;
	}

	// contributed by org.eclipse.xtext.generator.validation.CheckFragment
	public Class<? extends org.eclipse.xtend.expression.ExecutionContext> bindExecutionContext() {
		return org.eclipse.xtext.xtend.InjectableExecutionContext.class;
	}

	// contributed by org.eclipse.xtext.generator.validation.CheckFragment
	public Class<? extends org.eclipse.xtend.expression.ResourceManager> bindResourceManager() {
		return org.eclipse.xtext.xtend.InjectableResourceManager.class;
	}

	// contributed by org.eclipse.xtext.generator.validation.CheckFragment
	public java.lang.ClassLoader bindClassLoaderToInstance() {
		return AbstractModelicaBaseRuntimeModule.class.getClassLoader();
	}

	// contributed by org.eclipse.xtext.generator.validation.CheckFragment
	@org.eclipse.xtext.service.SingletonBinding(eager=true)	public Class<? extends org.modelica.xtext.validation.ModelicaBaseCheckValidator> bindModelicaBaseCheckValidator() {
		return org.modelica.xtext.validation.ModelicaBaseCheckValidator.class;
	}

	// contributed by org.eclipse.xtext.generator.scoping.JavaScopingFragment
	public Class<? extends org.eclipse.xtext.scoping.IScopeProvider> bindIScopeProvider() {
		return org.modelica.xtext.scoping.ModelicaBaseScopeProvider.class;
	}

	// contributed by org.eclipse.xtext.generator.formatting.FormatterFragment
	public Class<? extends org.eclipse.xtext.formatting.IFormatter> bindIFormatter() {
		return org.modelica.xtext.formatting.ModelicaBaseFormatter.class;
	}

}
