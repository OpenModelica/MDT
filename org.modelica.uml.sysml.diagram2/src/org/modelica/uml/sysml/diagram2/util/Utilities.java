package org.modelica.uml.sysml.diagram2.util;

import org.eclipse.core.runtime.IPath;

public class Utilities {

	public static String getPath(IPath location) {
		String path = "";
		if (location.getDevice() != null)
			path += location.getDevice() + "\\";
		String[] segments = location.segments();
		for (String s : segments) {
			if(s != null || !s.equals(""))
				path += s + "\\";
		}
		return path;
	}
}
