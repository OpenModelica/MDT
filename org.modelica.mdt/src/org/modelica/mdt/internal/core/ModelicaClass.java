package org.modelica.mdt.internal.core;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.modelica.mdt.core.IClassComponent;
import org.modelica.mdt.core.IClassExtend;
import org.modelica.mdt.core.IClassImport;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaPackage;
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
	private String parentPackage;
	private String className;
	private String fullName;
	private Type type;
	
	private boolean typeKnown = false;
	
	/**
	 * Create a top class that is contained in a specific modelica file.
	 * 
	 * @param containerFile
	 * @param name
	 */
	protected ModelicaClass(IModelicaFile containerFile, String name)
	{
		this.className = name;
		this.fullName = name;
	}
		
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

	public List<?> getChildren() 
		throws ConnectionException, InvocationError, UnexpectedReplyException
	{
		List<Object> children = new LinkedList<Object>();
		
		children.addAll(getPackages());
		children.addAll(getClasses());

		return children;
	}

	public boolean hasChildren()
		throws ConnectionException, InvocationError, UnexpectedReplyException
	{
		return !getChildren().isEmpty();
	}

	public List<IModelicaClass> getClasses() 
		throws ConnectionException, UnexpectedReplyException
	{
		LinkedList<IModelicaClass> classes = new LinkedList<IModelicaClass>();
		
		for (String name : OMCProxy.getClassNames(fullName))
		{
			classes.add(new ModelicaClass(name, fullName));
		}
		
		return classes;
	}

	public List<IModelicaPackage> getPackages()
		throws ConnectionException, InvocationError, UnexpectedReplyException
	{
		LinkedList<IModelicaPackage> pkgs = new LinkedList<IModelicaPackage>();
		
		for (String name : OMCProxy.getPackages(fullName))
		{
			pkgs.add(new ModelicaPackage(name, fullName));
		}
		return pkgs;
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
