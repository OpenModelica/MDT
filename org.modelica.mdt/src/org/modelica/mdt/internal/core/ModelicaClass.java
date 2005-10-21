package org.modelica.mdt.internal.core;

import org.modelica.mdt.core.IModelicaClass;

/**
 * 
 * @author Andreas Remar
 *
 */
public class ModelicaClass extends ModelicaElement implements IModelicaClass
{
	private String hostPackage;
	private String className;
	private Type type;
	
	protected ModelicaClass(String name, String pkg)
	{
		className = name;
		hostPackage = pkg;
	}
	
	public String getElementName()
	{
		return className;
	}

	public String getPackage()
	{
		return hostPackage;
	}
	
	public Type getType() 
	{
		return type;
	}

	
	//
	// dummy implementations below
	// TODO impelemnt me 4-real !
	//
	public ModelicaClass(Type type) 
	{
		className = "qweaasghasf";
		this.type = type;
	}


}
