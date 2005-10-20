package org.modelica.mdt.internal.core;

import org.modelica.mdt.core.IModelicaClass;

/**
 * 
 * @author Andreas Remar
 *
 */
public class ModelicaClass implements IModelicaClass
{
	String hostPackage;
	String className;

	public ModelicaClass(String name, String pkg)
	{
		className = name;
		hostPackage = pkg;
	}
	
	public String getName()
	{
		return className;
	}
	
	public String getPackage()
	{
		return hostPackage;
	}
}
