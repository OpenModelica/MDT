package org.modelica.mdt.internal.core;

import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.internal.corba.InitializationException;
import org.modelica.mdt.internal.corba.OMCProxy;

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
		System.out.println("Trying to fetch type");
		if(typeKnown == false)
		{
			String fullName = null;
			if(parentPackage == null)
				fullName = className;
			else
				fullName = parentPackage + "." + className;
			
			if(OMCProxy.sendExpression("isType(" + fullName + ")").contains("true"))
				type = Type.TYPE;
			else if(OMCProxy.sendExpression("isConnector(" + fullName + ")").contains("true"))
				type = Type.CONNECTOR;
			else if(OMCProxy.sendExpression("isModel(" + fullName + ")").contains("true"))
				type = Type.MODEL;
			else if(OMCProxy.sendExpression("isRecord(" + fullName + ")").contains("true"))
				type = Type.RECORD;
			else if(OMCProxy.sendExpression("isBlock(" + fullName + ")").contains("true"))
				type = Type.BLOCK;
			else if(OMCProxy.sendExpression("isFunction(" + fullName + ")").contains("true"))
				type = Type.FUNCTION;
			
			typeKnown = true;
		}
		
		System.out.println("Type is" + type);
		return type;
	}
}
