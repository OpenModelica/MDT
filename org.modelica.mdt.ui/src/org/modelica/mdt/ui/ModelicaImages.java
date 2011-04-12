/*******************************************************************************
 * Copyright (c) 2006 MDT Team, PELAB
 *******************************************************************************/

package org.modelica.mdt.ui;

/*
 * @author Adrian Pop [adrpo@ida.liu.se, http://www.ida.liu.se/~adrpo]
 * @author MDT Team
 */

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaExtends;
import org.modelica.mdt.core.IModelicaImport;
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

	public static final String NAME_PREFIX = "org.modelica.mdt.ui";
	
	/*
	 * Available cached Images in the Mdt plug-in image registry.
	 */	
	public static final String IMG_OBJS_PACKAGE = "IMG_OBJS_PACKAGE";
	public static final String IMG_OBJS_CLASS = "IMG_OBJS_CLASS";
	public static final String IMG_OBJS_MODEL = "IMG_OBJS_MODEL";
	public static final String IMG_OBJS_FUNCTION = "IMG_OBJS_FUNCTION";
	public static final String IMG_OBJS_RECORD = "IMG_OBJS_RECORD";
	public static final String IMG_OBJS_CONNECTOR = "IMG_OBJS_CONNECTOR";
	public static final String IMG_OBJS_EXPANDABLE_CONNECTOR = "IMG_OBJS_EXPANDABLE_CONNECTOR";	
	public static final String IMG_OBJS_BLOCK = "IMG_OBJS_BLOCK";	
	public static final String IMG_OBJS_TYPE = "IMG_OBJS_TYPE";
	public static final String IMG_OBJS_UNIONTYPE = "IMG_OBJS_UNIONTYPE";	
	public static final String IMG_OBJS_LIBRARY = "IMG_OBJS_LIBRARY";
	public static final String IMG_OBJS_MO_FILE = "IMG_OBJS_MO_FILE";
	public static final String IMG_OBJS_PROTECTED_COMPONENT = "IMG_OBJS_PROTECTED_COMPONENT";
	public static final String IMG_OBJS_PUBLIC_COMPONENT = "IMG_OBJS_PUBLIC_COMPONENT";
	
	public static final String IMG_OUTLINE_SYNCED = "IMG_OUTLINE_SYNCED";
	public static final String IMG_OUTLINE_FILTER = "IMG_OUTLINE_FILTER";
	public static final String IMG_OUTLINE_SORT = "IMG_OUTLINE_SORT";		
	public static final String IMG_OUTLINE_HIDE_COMPONENTS = "IMG_OUTLINE_HIDE_COMPONENTS";
	public static final String IMG_OUTLINE_HIDE_PARAMETERS = "IMG_OUTLINE_HIDE_PARAMETERS";
	public static final String IMG_OUTLINE_HIDE_PROTECTED = "IMG_OUTLINE_HIDE_PROTECTED";	
	public static final String IMG_OUTLINE_HIDE_TYPES = "IMG_OUTLINE_HIDE_TYPES";

	
	public static final String IMG_WIZBAN_PACKAGE = "IMG_WIZBAN_PACKAGE";
	public static final String IMG_WIZBAN_PROJECT = "IMG_WIZBAN_PROJECT";
	public static final String IMG_WIZBAN_CLASS = "IMG_WIZBAN_CLASS";
	
	public static final String IMG_MDT_STATUS_ONLINE = "IMG_MDT_STATUS_ONLINE";
	public static final String IMG_MDT_STATUS_OFFLINE = "IMG_MDT_STATUS_OFFLINE";
	
	public static final String IMG_CORRECTION_CHANGE = NAME_PREFIX + "correction_change.gif"; //$NON-NLS-1$
	public static final String IMG_CORRECTION_MOVE = NAME_PREFIX + "correction_move.gif"; //$NON-NLS-1$
	public static final String IMG_CORRECTION_RENAME = NAME_PREFIX + "correction_rename.gif"; //$NON-NLS-1$
	public static final String IMG_CORRECTION_LINKED_RENAME = NAME_PREFIX + "correction_linked_rename.gif"; //$NON-NLS-1$
	public static final String IMG_CORRECTION_DELETE_IMPORT = NAME_PREFIX + "correction_delete_import.gif"; //$NON-NLS-1$
	public static final String IMG_CORRECTION_LOCAL = NAME_PREFIX + "localvariable_obj.gif"; //$NON-NLS-1$
	public static final String IMG_CORRECTION_REMOVE = NAME_PREFIX + "remove_correction.gif"; //$NON-NLS-1$
	public static final String IMG_CORRECTION_ADD = NAME_PREFIX + "add_correction.gif"; //$NON-NLS-1$
	public static final String IMG_CORRECTION_CAST = NAME_PREFIX + "correction_cast.gif"; //$NON-NLS-1$
	public static final String IMG_OBJS_NLS_NEVER_TRANSLATE= NAME_PREFIX + "never_translate.gif"; //$NON-NLS-1$
	
	
	/*
	 * Set of predefined Image Descriptors.
	 */
	private static final String T_OBJ= "obj16/"; 		//$NON-NLS-1$
	private static final String T_WIZBAN= "wizban/"; 	//$NON-NLS-1$
    private static final String T_ELCL= "elcl16/"; 	//$NON-NLS-1$
    private static final String T_ETOOL= "etool16/"; 	//$NON-NLS-1$    


	
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
	 * Helper method to access the image registry from the MDT UIPlugin class.
	 */
	/* package */ public static ImageRegistry getImageRegistry() 
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
		declareRegistryImage(IMG_OBJS_PACKAGE, T_OBJ + "package_obj.gif"); //$NON-NLS-1$
		
		declareRegistryImage(IMG_OBJS_CLASS, T_OBJ + "class_obj.gif");
		declareRegistryImage(IMG_OBJS_MODEL, T_OBJ + "model_obj.gif");
		declareRegistryImage(IMG_OBJS_FUNCTION, T_OBJ + "function_obj.gif");
		declareRegistryImage(IMG_OBJS_RECORD, T_OBJ + "record_obj.gif");
		declareRegistryImage(IMG_OBJS_CONNECTOR, T_OBJ + "connector_obj.gif");
		declareRegistryImage(IMG_OBJS_EXPANDABLE_CONNECTOR, T_OBJ + "expandable_connector_obj.gif");		
		declareRegistryImage(IMG_OBJS_BLOCK, T_OBJ + "block_obj.gif");
		declareRegistryImage(IMG_OBJS_MO_FILE, T_OBJ + "mo_file.gif");
		declareRegistryImage(IMG_OBJS_LIBRARY, T_OBJ + "library_obj.gif");
		declareRegistryImage(IMG_OBJS_PROTECTED_COMPONENT, T_OBJ + "protected_component_obj.gif");
		declareRegistryImage(IMG_OBJS_PUBLIC_COMPONENT,  T_OBJ + "public_component_obj.gif");
		declareRegistryImage(IMG_OBJS_TYPE, T_OBJ + "type_obj.gif");
		declareRegistryImage(IMG_OBJS_UNIONTYPE, T_OBJ + "uniontype_obj.gif");
		
		declareRegistryImage(IMG_CORRECTION_CHANGE, T_OBJ + "correction_change.gif");
		declareRegistryImage(IMG_CORRECTION_MOVE, T_OBJ + "correction_move.gif");
		declareRegistryImage(IMG_CORRECTION_RENAME, T_OBJ + "correction_rename.gif");
		declareRegistryImage(IMG_CORRECTION_LINKED_RENAME, T_OBJ + "correction_linked_rename.gif");
		declareRegistryImage(IMG_CORRECTION_DELETE_IMPORT, T_OBJ + "correction_delete_import.gif");
		declareRegistryImage(IMG_CORRECTION_LOCAL, T_OBJ + "localvariable_obj.gif");
		declareRegistryImage(IMG_CORRECTION_REMOVE, T_OBJ + "remove_correction.gif");
		declareRegistryImage(IMG_CORRECTION_ADD, T_OBJ + "add_correction.gif");
		declareRegistryImage(IMG_CORRECTION_CAST, T_OBJ + "correction_cast.gif");
		declareRegistryImage(IMG_OBJS_NLS_NEVER_TRANSLATE, T_OBJ + "never_translate.gif");
		

		declareRegistryImage(IMG_OUTLINE_SYNCED, T_ELCL + "synced.gif");
		declareRegistryImage(IMG_OUTLINE_FILTER, T_ELCL + "filter_ps.gif");
		declareRegistryImage(IMG_OUTLINE_SORT,   T_ELCL + "sort.gif");
		declareRegistryImage(IMG_OUTLINE_HIDE_COMPONENTS, T_ELCL + "hide_components.gif");
		declareRegistryImage(IMG_OUTLINE_HIDE_PARAMETERS,  T_ELCL + "hide_parameters.gif");
		declareRegistryImage(IMG_OUTLINE_HIDE_PROTECTED, T_ELCL + "hide_protected.gif");
		declareRegistryImage(IMG_OUTLINE_HIDE_TYPES, T_ELCL + "hide_types.gif");		
		
		declareRegistryImage(IMG_WIZBAN_PACKAGE, T_WIZBAN + "package_wiz.gif");
		declareRegistryImage(IMG_WIZBAN_PROJECT, T_WIZBAN + "project_wiz.gif");
		declareRegistryImage(IMG_WIZBAN_CLASS, T_WIZBAN + "class_wiz.gif");
		declareRegistryImage(IMG_MDT_STATUS_ONLINE, T_ETOOL + "mdt_online.gif");
		declareRegistryImage(IMG_MDT_STATUS_OFFLINE, T_ETOOL + "mdt_offline.gif");		
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
				case EXPANDABLE_CONNECTOR:
					return ModelicaImages.IMG_OBJS_EXPANDABLE_CONNECTOR;
				case BLOCK:
					return ModelicaImages.IMG_OBJS_BLOCK;
				case TYPE:
					return ModelicaImages.IMG_OBJS_TYPE;
				case UNIONTYPE:
					return ModelicaImages.IMG_OBJS_UNIONTYPE;
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
			switch (((IModelicaComponent)element).getVisibility())
			{
			case PUBLIC:
				return ModelicaImages.IMG_OBJS_PUBLIC_COMPONENT;
			case PROTECTED:
				return ModelicaImages.IMG_OBJS_PROTECTED_COMPONENT;
			}
		}
		else if (element instanceof IModelicaImport)
		{
			switch (((IModelicaImport)element).getVisibility())
			{
			case PUBLIC:
				return ModelicaImages.IMG_OBJS_PUBLIC_COMPONENT;
			case PROTECTED:
				return ModelicaImages.IMG_OBJS_PROTECTED_COMPONENT;
			}
		}
		else if (element instanceof IModelicaExtends)
		{
			switch (((IModelicaExtends)element).getVisibility())
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
