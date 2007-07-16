package org.modelica.mdt.core;

import java.io.OutputStream;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.modelica.mdt.core.IModelicaClass.Restriction;
import org.modelica.mdt.core.compiler.ElementInfo;
import org.modelica.mdt.core.compiler.IClassInfo;
import org.modelica.mdt.core.compiler.IModelicaCompiler;
import org.modelica.mdt.core.compiler.IParseResults;

class NoCompiler implements IModelicaCompiler
{
	public String getCompilerName() 
	{ 
		return "Empty Compiler"; 
	}

	public IParseResults loadSourceFile(IFile file)
	{
		return null;
	}
	
	public List getClassNames(String className)
	{
		return null;
	}
	
	public Collection<ElementInfo> getElements(String className)
	{
		return null;
	}
	
	public IDefinitionLocation getClassLocation(String className)
	{
		return null;
	}

	public Restriction getRestriction(String className)
	{
		return null;
	}
	
	public String[] getStandardLibrary()
	{
		return null;
	}
	
	public IClassInfo getClassInfo(String className)
	{
		return null;
	}
	
	public ICompilerResult getClassString(String className)
	{
		return null;
	}
	
	public ICompilerResult sendExpression(String command, boolean showInConsole)
	{
		return null;
	}

	public boolean isRunning()
	{
		return false;
	}

	public void setConsoleOutputStream(OutputStream outputStream)
	{
		/* do nothing */
	}
	
}
