package org.modelica.mdt.internal.core;

import java.io.File;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.modelica.mdt.core.IClassComponent;
import org.modelica.mdt.core.IClassExtend;
import org.modelica.mdt.core.IClassImport;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChange.ChangeType;
import org.modelica.mdt.internal.omcproxy.ConnectionException;
import org.modelica.mdt.internal.omcproxy.InvocationError;
import org.modelica.mdt.internal.omcproxy.OMCProxy;
import org.modelica.mdt.internal.omcproxy.UnexpectedReplyException;

/**
 * 
 * @author Andreas Remar
 *
 */
public class ModelicaClass extends ModelicaElement implements IModelicaClass
{
	/* subpackages and subclasses hashed by the thier's shortname */
	private Hashtable<String, Object> children = null;

	private String prefix;
	private String name;
	private String fullName;
	private Type type;
	
	private boolean typeKnown = false;

	/**
	 * @param prefix
	 * @param name
	 */
	protected ModelicaClass(String prefix, String name)
	{
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
		throws ConnectionException, UnexpectedReplyException
	{
		// TODO The path to the file should actually be stored somewhere in
		//      ModelicaClass (at least that's what I've heard)
		String[] tokens = OMCProxy.getCrefInfo(fullName);

		IPath filePath = new Path(tokens[0]);

		/*
		 * filePath is an absolute path starting at the root of the file system,
		 * what we want is a path starting from the workspace root.
		 * To accomplish this, we cut off the first segments. 
		 */
		IPath workspacePath = ResourcesPlugin.getWorkspace()
			.getRoot().getRawLocation();
		
		/* Make sure that this file really is inside the workspace root */
		if(workspacePath.isPrefixOf(filePath) == false)
			return null;
		
		filePath = filePath.removeFirstSegments(workspacePath.segmentCount());
		
		IFile f = 
			ResourcesPlugin.getWorkspace().getRoot().getFile(filePath);
		
		return f;
	}
	
	public File getFile() throws ConnectionException, UnexpectedReplyException
	{
		String[] tokens = OMCProxy.getCrefInfo(fullName);
		
		File file = new File(tokens[0]);
		
		return file;
	}
	
	public int getLine() throws ConnectionException, UnexpectedReplyException
	{
		String[] tokens = OMCProxy.getCrefInfo(fullName);
		return Integer.parseInt(tokens[1]);
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
			elements.put(name, new InnerPackage(fullName, name));
		}
		
		for (String name : OMCProxy.getClassNames(fullName))
		{
			elements.put(name, new ModelicaClass(fullName, name));
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
