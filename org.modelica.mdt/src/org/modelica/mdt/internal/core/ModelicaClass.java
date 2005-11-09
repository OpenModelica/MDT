package org.modelica.mdt.internal.core;

import org.eclipse.core.resources.IResource;
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
	private String fullName;
	private Type type;
	
	private boolean typeKnown = false;
		
	protected ModelicaClass(String name, String pkg)
	{
		className = name;
		parentPackage = pkg;
		
		if(parentPackage == null)
			fullName = className;
		else
			fullName = parentPackage + "." + className;

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
			
			Type t = OMCProxy.getType(fullName);
			if(t != null)
				type = t;
			
			typeKnown = true;
		}

		return type;
	}
	
	public IResource getResource() throws InitializationException
	{
		//String[] tokens = OMCProxy.getCrefInfo(fullName);
		
		return null;
	}
}
