package org.modelica.mdt.internal.core;

import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.internal.omcproxy.InitializationException;
import org.modelica.mdt.internal.omcproxy.OMCProxy;

/**
 * 
 * @author Andreas Remar
 *
 */
public class ModelicaClass extends ModelicaElement implements IModelicaClass
{
	private String parentPackage;
	private String className;
	private Type type;
	
	private boolean typeKnown = false;
	
	protected ModelicaClass(String name, String pkg)
	{
		className = name;
		parentPackage = pkg;
		type = Type.CLASS;
	}
	
	public String getElementName()
	{
		return className;
	}

	public String getPackage()
	{
		return parentPackage;
	}
	
	public Type getType() throws InitializationException
	{
		if(typeKnown == false)
		{
			String fullName = null;
			if(parentPackage == null)
				fullName = className;
			else
				fullName = parentPackage + "." + className;
			
			type = OMCProxy.getType(fullName);
			
			typeKnown = true;
		}

		return type;
	}
}
