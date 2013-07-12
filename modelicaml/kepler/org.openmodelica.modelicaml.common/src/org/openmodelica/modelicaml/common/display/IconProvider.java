/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Link�pings universitet, Department of Computer and Information Science,
 * SE-58183 Link�ping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.common.display;

import java.io.IOException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.openmodelica.modelicaml.common.Activator;
import org.openmodelica.modelicaml.common.utls.ResourceManager;




// TODO: Auto-generated Javadoc
/**
 * The Class IconProvider.
 */
public class IconProvider {
	
	/** The Constant iconsFolderPath. */
	final private static String iconsFolderPath = "/icons/";
	private final static ImageDescriptor privateVisibilityImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/VisibilityKind_private.gif");
	
//	/**
//	 * Gets the icon.
//	 *
//	 * @param imageFileName the image file name
//	 * @return the icon
//	 */
//	public static Image getIcon(String imageFileName) {
//		String path = iconsFolderPath + imageFileName;
//		String key = Activator.PLUGIN_ID + path;
//		Image result = JFaceResources.getImageRegistry().get(key);
//		if(result == null) {
//			URL url = Activator.getDefault().getBundle().getEntry(path);
//			try {
//				result = new Image(Display.getDefault(), url.openStream());
//				JFaceResources.getImageRegistry().put(key, result);
//			} catch (IOException e) {
//			}
//		}
//		return result;
//	}
	
	
	public static Image getIcon(String imageFileName, boolean isPrivate) {
		
		String path = iconsFolderPath + imageFileName;
		String key = Activator.PLUGIN_ID + path;
		
		Image result = JFaceResources.getImageRegistry().get(key);
		
		if(result == null) {
			URL url = Activator.getDefault().getBundle().getEntry(path);
			try {
				
				result = new Image(Display.getDefault(), url.openStream());
				// register for reuse
				JFaceResources.getImageRegistry().put(key, result);
				
			} catch (IOException e) {
			}
		}
				
		// create overlay
		if (isPrivate) {
			DecorationOverlayIcon overlayIcon = new DecorationOverlayIcon(result, privateVisibilityImageDescriptor, IDecoration.BOTTOM_RIGHT);
			result = overlayIcon.createImage();
		}
		
		return result;
	}
}
