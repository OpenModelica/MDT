package org.modelica.mdt.core;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.ElementInfo;
import org.modelica.mdt.core.compiler.IClassInfo;
import org.modelica.mdt.core.compiler.IModelicaCompiler;
import org.modelica.mdt.core.compiler.IParseResults;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

public class OpenModelicaCompiler 
{
	protected static IModelicaCompiler compiler;
	
	public OpenModelicaCompiler() throws CompilerInstantiationException
	{
		compiler = CompilerProxy.getCompiler();
	}
		
	public  String getCompilerName() 
	throws CompilerInstantiationException
	{
		return compiler.getCompilerName();
	}

	public  IParseResults loadSourceFile(IFile file) 
	throws ConnectException, UnexpectedReplyException, 
	CompilerInstantiationException
	{
		return compiler.loadSourceFile(file);
	}

	public  List getClassNames(String className)
	throws ConnectException, UnexpectedReplyException,
	CompilerInstantiationException
	{
		return compiler.getClassNames(className);
	}

	public  Collection<ElementInfo> getElements(String className)
	throws ConnectException, InvocationError, UnexpectedReplyException, 
	CompilerInstantiationException
	{
		return compiler.getElements(className);
	}

	public  IDefinitionLocation getClassLocation(String className)
	throws ConnectException, UnexpectedReplyException, InvocationError,
	CompilerInstantiationException 
	{
		return compiler.getClassLocation(className);
	}

	public  IModelicaClass.Restriction getRestriction(String className)
	throws ConnectException, CompilerInstantiationException,
	UnexpectedReplyException
	{
		return compiler.getRestriction(className);
	}

	/**
	 * @return the top classes in the standard library
	 */
	public  String[] getStandardLibrary()
	throws ConnectException, CompilerInstantiationException
	{
		return compiler.getStandardLibrary();
	}

	public  IClassInfo getClassInfo(String className) 
	throws CompilerInstantiationException, ConnectException,
	UnexpectedReplyException 
	{
		return compiler.getClassInfo(className);
	}

	public  String getClassString(String className) 
	throws CompilerInstantiationException, ConnectException,
	UnexpectedReplyException 
	{
		return compiler.getClassString(className);
	}	

	/**
	 * @author Adrian Pop
	 * @param command
	 * @return the result of the command execution
	 * @throws CompilerInstantiationException
	 * @throws ConnectException
	 * @throws UnexpectedReplyException
	 */
	public synchronized String sendExpression(String command, boolean showInConsole) 
	throws CompilerInstantiationException, ConnectException,
	UnexpectedReplyException 
	{
		return compiler.sendExpression(command, showInConsole);
	}	
}
