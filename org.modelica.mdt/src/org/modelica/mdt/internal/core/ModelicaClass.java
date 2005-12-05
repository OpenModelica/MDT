package org.modelica.mdt.internal.core;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.IRegion;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.builder.SyntaxChecker;
import org.modelica.mdt.core.IClassComponent;
import org.modelica.mdt.core.IClassExtend;
import org.modelica.mdt.core.IClassImport;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.internal.omcproxy.ConnectionException;
import org.modelica.mdt.internal.omcproxy.ElementLocation;
import org.modelica.mdt.internal.omcproxy.InvocationError;
import org.modelica.mdt.internal.omcproxy.OMCProxy;
import org.modelica.mdt.internal.omcproxy.UnexpectedReplyException;

/**
 * @author Andreas Remar
 */
public class ModelicaClass extends ModelicaElement implements IModelicaClass
{
	/* subpackages and subclasses hashed by the thier's shortname */
	private Hashtable<String, Object> children = null;

	private String prefix;
	private String name;
	private String fullName;
	private Type type;
	
	private ElementLocation location = null;
	
	/*
	 * the file where this class is defined, 
	 * can be null if it is unknown
	 * when the container is unknow the class is assumed to 
	 * be external e.g. defined in system library
	 */
	private IFile container;
	
	private boolean typeKnown = false;

	/**
	 * Create a modelica class that is defined inside a modelica file.
	 * @param container the file where this file is defined
	 * @param prefix
	 * @param name
	 */
	protected ModelicaClass(IFile container, String prefix, String name)	
	{

		this.container = container;
		this.name = name;
		this.prefix = prefix;
		
		if (prefix.equals(""))
		{
			fullName = name;
		}
		else
		{
			fullName = prefix + "." + name;
		}

		type = Type.CLASS;
	}
	
	/**
	 * Create a modelica class that is defined in unknow location, for example
	 * a system library class. This method assumes that a class named
	 * 'prefix'.'name' is loaded into OMC.
	 * 
	 * @param prefix
	 * @param name
	 */
	protected ModelicaClass(String prefix, String name)
	{
		this(null, prefix, name);
	}
	
	public String getElementName()
	{
		return name;
	}

	public String getPackage()
	{
		return prefix;
	}
	
	public Type getType()
		throws ConnectionException
	{
		if(typeKnown == false)
		{
			
			Type t = OMCProxy.getType(fullName);
			if(t != null)
			{
				type = t;
			}
			
			typeKnown = true;
		}

		return type;
	}
	
	public IResource getResource()
	{
		return container;
	}

	
	
	/**
	 * @throws InvocationError 
	 * @throws CoreException 
	 * @see org.modelica.mdt.core.IModelicaElement#getLocation()
	 */
	public IRegion getLocation() 
		throws ConnectionException, UnexpectedReplyException, 
			InvocationError, CoreException
	{
		if (location == null)
		{
			loadElementLocation();
		}
		
		if (container != null)
		{
			SyntaxChecker.getLineRegion(container, location.getLine());
		}

		IRegion reg = null;
		
		try
		{
			reg = 
				SyntaxChecker.getLineRegion(location.getPath(), 
						location.getLine());
		}
		catch (FileNotFoundException e)
		{
			throw new CoreException(
					new Status(IStatus.ERROR,
								MdtPlugin.getSymbolicName(),
								IStatus.OK, 
								"could not find modelica source file " + 
									location.getPath(),
								e));
		}
		return reg;
	}

	@Override
	public String getFilePath() 
		throws ConnectionException, UnexpectedReplyException, InvocationError
	{
		if (location == null)
		{
			loadElementLocation();
		}
		return location.getPath();
	}

	private void loadElementLocation()
		throws ConnectionException, UnexpectedReplyException, InvocationError
	{
		location = OMCProxy.getElementLocation(fullName);
	}

	public Collection<Object> getChildren() 
		throws ConnectionException,	UnexpectedReplyException,
			InvocationError, CoreException
	{
		if (children == null)
		{
			children = loadElements();
		}
		
		return children.values();
	}

	private Hashtable<String, Object> loadElements() 
		throws ConnectionException, UnexpectedReplyException, InvocationError 
	{
		Hashtable<String, Object> elements = new Hashtable<String, Object>();
		
		for (String name : OMCProxy.getPackages(fullName))
		{
			elements.put(name, new InnerPackage(container, fullName, name));
		}
		
		for (String name : OMCProxy.getClassNames(fullName))
		{
			elements.put(name, new ModelicaClass(container, fullName, name));
		}
		
		return elements;
	}

	public boolean hasChildren()
		throws ConnectionException, InvocationError, 
			UnexpectedReplyException, CoreException
	{
		return !getChildren().isEmpty();
	}
	
	@Override
	public Collection<IModelicaElementChange> reload()
		throws ConnectionException, UnexpectedReplyException, InvocationError
	{
		LinkedList<IModelicaElementChange> changes = 
			new LinkedList<IModelicaElementChange>();

		if (children == null)
		{
			/* if children are not loaded, then we can't reload */
			return changes;
		}
		
		/* invalidate location */
		location = null;
		
		Hashtable<String, Object> newChildren = loadElements();

		@SuppressWarnings("unchecked")
		Hashtable<String, Object> oldChildren = 
			(Hashtable<String, Object>) children.clone();
		
		
		for (Object element : newChildren.values())
		{
			ModelicaElement moElement = (ModelicaElement) element;

			ModelicaElement oldElement = (ModelicaElement)
				oldChildren.remove(moElement.getElementName());
		
			if (oldElement == null)
			{
				/* new element added */
				children.put(moElement.getElementName(), element);
				changes.add(new ModelicaElementChange(this, element));
			}
			else
			{
				/* element present before, refresh ! */
				changes.addAll(oldElement.reload());
			}
		}
		
		/* now there is only removed elements in the oldChildren table */
		for (Object element : oldChildren.values())
		{
			ModelicaElement moElement = (ModelicaElement) element;

			children.remove(moElement.getElementName());
			changes.add(new ModelicaElementChange(element, ChangeType.REMOVED));
		}
		
		return changes;
	}

	public IClassImport[] getImports()
	{
		// TODO implement me
		return new IClassImport[0];
	}

	public IClassExtend[] getExtends()
	{
		// TODO impelement me
		return new IClassExtend[0];
	}

	public IClassComponent[] getComponents()
	{
		// TODO implement me
		return new IClassComponent[0];
	}
}
