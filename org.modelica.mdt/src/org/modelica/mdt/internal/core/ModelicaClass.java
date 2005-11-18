package org.modelica.mdt.internal.core;

import java.io.File;
import java.io.InputStream;

import org.eclipse.core.internal.resources.Resource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.internal.omcproxy.InitializationException;
import org.modelica.mdt.internal.omcproxy.OMCProxy;
import org.eclipse.core.resources.IStorage;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.internal.editors.text.JavaFileEditorInput;
import org.eclipse.ui.internal.editors.text.OpenExternalFileAction;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;

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
		String[] tokens = OMCProxy.getCrefInfo(fullName);

		IPath filePath = new Path(tokens[0]);
		
		IFile f = IDEWorkbenchPlugin.getPluginWorkspace().getRoot().getFile(
                filePath);
		
		return f;
	}
	
	public File getFile() throws InitializationException
	{
		String[] tokens = OMCProxy.getCrefInfo(fullName);
		
		File file = new File(tokens[0]);
		
		return file;
	}
}
