package org.modelica.mdt.core;

import java.io.OutputStream;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.modelica.mdt.core.IModelicaClass.Restriction;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.ElementInfo;
import org.modelica.mdt.core.compiler.IClassInfo;
import org.modelica.mdt.core.compiler.IModelicaCompiler;
import org.modelica.mdt.core.compiler.IParseResults;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

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
	
	public ICompilerResult getNthInheritedClass(String className, int n)
	{
		return null;
	}
	
	public int getInheritanceCount(String className)
	{
		return 0;
	}
	
	public ICompilerResult getNthAlgorithmItem(String className, int n)
	{
		return null;
	}
	
	public int getAlgorithmItemsCount(String className)
	{
		return 0;
	}
	
	public ICompilerResult getNthEquationItem(String className, int n)
	{
		return null;
	}
	
	public int getEquationItemsCount(String className)
	{
		return 0;
	}
	
	
	public List getComponents(String className)
	{
		return null;
	}
	
	public boolean existClass(String className)
	{
		return false;
	}
	
	public boolean isPackage(String className)
	{
		return false;
	}
	
	public ICompilerResult getErrorString()
	{
		return null;
	}
	
	public ICompilerResult loadFile(String classPath) throws ConnectException, UnexpectedReplyException 
	{	
		return null;
	}
	
	public ICompilerResult getSourceFile(String className) throws ConnectException, UnexpectedReplyException 
	{	
		return null;
	}
}
