
package org.modelica.mdt.ui;

import java.util.Collection;

import org.eclipse.core.resources.IResource;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jface.text.IRegion;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IParent;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;

public class SystemLibrary extends PlatformObject 
	implements IModelicaElement, IParent
{
	public String getElementName()
	{
		return "System Library";
	}

	public IResource getResource()
	{
		return null;
	}

	public IRegion getLocation() 
	{
		return null;
	}

	public String getFilePath()
	{
		return null;
	}

	public Collection<IModelicaClass> getChildren()
		throws ConnectException, CompilerInstantiationException
	{
		return CompilerProxy.getStandardLibrary();
	}

	public boolean hasChildren()
	{
		return true;
	}

}
