/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.internal.core;


import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.modelica.mdt.MdtPlugin;

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
		ICON_BASE_URL= MdtPlugin.getDefault().getBundle().getEntry(pathSuffix);
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
	public static final String IMG_OBJS_MO_FILE = "IMG_OBJS_MO_FILE";		
	
	
	/*
	 * Set of predefined Image Descriptors.
	 */
	private static final String T_OBJ= "obj16/"; 		//$NON-NLS-1$
//	private static final String T_OVR= "ovr16/"; 		//$NON-NLS-1$
//	private static final String T_WIZBAN= "wizban/"; 	//$NON-NLS-1$
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
		fgImageRegistry= new ImageRegistry(MdtPlugin.getStandardDisplay());
		declareImages();
	}
	
	private static void declareImages() 
	{
		declareRegistryImage(IMG_OBJS_PACKAGE, T_OBJ + "package_obj.gif"); //$NON-NLS-1$
		
		declareRegistryImage(IMG_OBJS_CLASS, T_OBJ + "class_obj.png"); //$NON-NLS-1$
		declareRegistryImage(IMG_OBJS_MODEL, T_OBJ + "model_obj.png"); //$NON-NLS-1$
		declareRegistryImage(IMG_OBJS_FUNCTION, T_OBJ + "function_obj.png"); //$NON-NLS-1$
		declareRegistryImage(IMG_OBJS_RECORD, T_OBJ + "record_obj.png"); //$NON-NLS-1$
		declareRegistryImage(IMG_OBJS_CONNECTOR, T_OBJ + "connector_obj.png"); //$NON-NLS-1$
		declareRegistryImage(IMG_OBJS_BLOCK, T_OBJ + "block_obj.png"); //$NON-NLS-1$
		declareRegistryImage(IMG_OBJS_TYPE, T_OBJ + "type_obj.png"); //$NON-NLS-1$
		declareRegistryImage(IMG_OBJS_MO_FILE, T_OBJ + "mo_file.png"); //$NON-NLS-1$
				
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
			MdtPlugin.log(me);
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

}
