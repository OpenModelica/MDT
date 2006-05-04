/*******************************************************************************
 * Copyright (c) 2006 MDT Team, PELAB
 *******************************************************************************/

package org.modelica.mdt.ui;


import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IStandardLibrary;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.internal.core.ErrorManager;

/**
 * Bundle of most images used by the Mdt plug-in.
 */
public class ModelicaImages 
{

	/* Declare Common paths */
	private static URL ICON_BASE_URL= null;

	static 
	{
		String pathSuffix = "icons/"; //$NON-NLS-1$
		ICON_BASE_URL= UIPlugin.getDefault().getBundle().getEntry(pathSuffix);
	}
	
	// The plugin registry
	private static ImageRegistry fgImageRegistry = null;

	/*
	 * Available cached Images in the Mdt plug-in image registry.
	 */	
	public static final String IMG_OBJS_PACKAGE = "IMG_OBJS_EXCEPTION";
	public static final String IMG_OBJS_CLASS = "IMG_OBJS_CLASS";
	public static final String IMG_OBJS_MODEL = "IMG_OBJS_MODEL";
	public static final String IMG_OBJS_FUNCTION = "IMG_OBJS_FUNCTION";
	public static final String IMG_OBJS_RECORD = "IMG_OBJS_RECORD";
	public static final String IMG_OBJS_CONNECTOR = "IMG_OBJS_CONNECTOR";
	public static final String IMG_OBJS_BLOCK = "IMG_OBJS_BLOCK";	
	public static final String IMG_OBJS_TYPE = "IMG_OBJS_TYPE";
	public static final String IMG_OBJS_LIBRARY = "IMG_OBJS_LIBRARY";
	public static final String IMG_OBJS_MO_FILE = "IMG_OBJS_MO_FILE";
	public static final String IMG_OBJS_PROTECTED_COMPONENT 
			= "IMG_OBJS_PROTECTED_COMPONENT";
	public static final String  IMG_OBJS_PUBLIC_COMPONENT 
			= "IMG_OBJS_PUBLIC_COMPONENT";
	
	public static final String IMG_WIZBAN_PACKAGE = "IMG_WIZBAN_PACKAGE";
	public static final String IMG_WIZBAN_PROJECT = "IMG_WIZBAN_PROJECT";
	public static final String IMG_WIZBAN_CLASS = "IMG_WIZBAN_CLASS";
	
	
	/*
	 * Set of predefined Image Descriptors.
	 */
	private static final String T_OBJ= "obj16/"; 		//$NON-NLS-1$
//	private static final String T_OVR= "ovr16/"; 		//$NON-NLS-1$
	private static final String T_WIZBAN= "wizban/"; 	//$NON-NLS-1$
//	private static final String T_EVIEW= "eview16/"; 	//$NON-NLS-1$
//	private static final String T_DLCL= "dtool16/"; 	//$NON-NLS-1$
//	private static final String T_ELCL= "etool16/"; 	//$NON-NLS-1$


	
	/**
	 * Returns the image managed under the given key in this registry.
	 * 
	 * @param key the image's key
	 * @return the image managed under the given key
	 */ 
	public static Image get(String key) 
	{
		return getImageRegistry().get(key);
	}
	
	/**
	 * Returns the <code>ImageDescriptor</code> identified by the given key,
	 * or <code>null</code> if it does not exist.
	 */
	public static ImageDescriptor getImageDescriptor(String key) 
	{
		return getImageRegistry().getDescriptor(key);
	}	
	
	/*
	 * Helper method to access the image registry from the JDIDebugUIPlugin class.
	 */
	/* package */ static ImageRegistry getImageRegistry() 
	{
		if (fgImageRegistry == null) 
		{
			initializeImageRegistry();
		}
		return fgImageRegistry;
	}
	
	private static void initializeImageRegistry() 
	{
		fgImageRegistry = new ImageRegistry(UIPlugin.getStandardDisplay());
		declareImages();
	}
	
	private static void declareImages() 
	{
		declareRegistryImage(IMG_OBJS_PACKAGE, T_OBJ + "package_obj.png"); //$NON-NLS-1$
		
		declareRegistryImage(IMG_OBJS_CLASS, T_OBJ + "class_obj.png");
		declareRegistryImage(IMG_OBJS_MODEL, T_OBJ + "model_obj.png");
		declareRegistryImage(IMG_OBJS_FUNCTION, T_OBJ + "function_obj.png");
		declareRegistryImage(IMG_OBJS_RECORD, T_OBJ + "record_obj.png");
		declareRegistryImage(IMG_OBJS_CONNECTOR, T_OBJ + "connector_obj.png");
		declareRegistryImage(IMG_OBJS_BLOCK, T_OBJ + "block_obj.png");
		declareRegistryImage(IMG_OBJS_TYPE, T_OBJ + "type_obj.png");
		declareRegistryImage(IMG_OBJS_MO_FILE, T_OBJ + "mo_file.png");
		declareRegistryImage(IMG_OBJS_LIBRARY, T_OBJ + "library_obj.png");
		declareRegistryImage(IMG_OBJS_PROTECTED_COMPONENT, 
				T_OBJ + "protected_component_obj.png");
		declareRegistryImage(IMG_OBJS_PUBLIC_COMPONENT, 
				T_OBJ + "public_component_obj.png");
		
		declareRegistryImage(IMG_WIZBAN_PACKAGE, T_WIZBAN + "package_wiz.png");
		declareRegistryImage(IMG_WIZBAN_PROJECT, T_WIZBAN + "project_wiz.png");
		declareRegistryImage(IMG_WIZBAN_CLASS, T_WIZBAN + "class_wiz.png");
				
	}
	
	/**
	 * Declare an Image in the registry table.
	 * @param key 	The key to use when registering the image
	 * @param path	The path where the image can be found. This path is relative to where
	 *				this plugin class is found (i.e. typically the packages directory)
	 */
	private final static void declareRegistryImage(String key, String path)
	{
		ImageDescriptor desc = ImageDescriptor.getMissingImageDescriptor();
		try 
		{
			desc = ImageDescriptor.createFromURL(makeIconFileURL(path));
		}
		catch (MalformedURLException me) 
		{
			ErrorManager.logError(me);
		}
		fgImageRegistry.put(key, desc);
	}	
	
	private static URL makeIconFileURL(String iconPath) throws MalformedURLException 
	{
		if (ICON_BASE_URL == null) 
		{
			throw new MalformedURLException();
		}
			
		return new URL(ICON_BASE_URL, iconPath);
	}
	
	/**
	 * Get the key to the image used to display certain modelica elements.
	 * @param element the modelica element who's image key to fetch 
	 * @return the image key or null if it is unknow which image is used
	 * to visualize provided element
	 */
	public static String getModelicaElementKey(IModelicaElement element)
	{
		if (element instanceof IModelicaClass)
		{
			try
			{
				switch (((IModelicaClass)element).getRestriction())
				{
				case PACKAGE:
					return ModelicaImages.IMG_OBJS_PACKAGE;
				case CLASS:
					return ModelicaImages.IMG_OBJS_CLASS;
				case MODEL:
					return ModelicaImages.IMG_OBJS_MODEL;
				case FUNCTION:
					return ModelicaImages.IMG_OBJS_FUNCTION;
				case RECORD:
					return ModelicaImages.IMG_OBJS_RECORD;
				case CONNECTOR:
					return ModelicaImages.IMG_OBJS_CONNECTOR;
				case BLOCK:
					return ModelicaImages.IMG_OBJS_BLOCK;
				case TYPE:
					return ModelicaImages.IMG_OBJS_TYPE;
				default:
					ErrorManager.logBug(UIPlugin.getSymbolicName(),
							"IModelicaClass object of unexpected restriction " + 
							((IModelicaClass)element).getRestriction() +
							" encountered.");
				}
			}
			catch (CompilerException e)
			{
				ErrorManager.logError(e);
			}
			catch (CoreException e)
			{
				ErrorManager.logError(e);
			}

		}
		else if (element instanceof IModelicaComponent)
		{
			switch (((IModelicaComponent)element).getVisbility())
			{
			case PUBLIC:
				return ModelicaImages.IMG_OBJS_PUBLIC_COMPONENT;
			case PROTECTED:
				return ModelicaImages.IMG_OBJS_PROTECTED_COMPONENT;
			}
		}
		else if (element instanceof IModelicaSourceFile)
		{
			return ModelicaImages.IMG_OBJS_MO_FILE;
		}
		else if (element instanceof IStandardLibrary)
		{
			return ModelicaImages.IMG_OBJS_LIBRARY;
		}

		/* we have no idea */
		return null;
	}
}
