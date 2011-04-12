
package org.modelica.mdt.ui.util;

public class Launchers
{
	
	public static boolean launcherNameMatches(String name)
	{
		String str = name.toLowerCase();
		boolean b1 = str.contains("omdev") || str.contains("rml") || str.contains("omc") || str.contains("openmodelica");
	    boolean b2 = str.endsWith(".launch");	
		return (b1 && b2);
	}
	
	public static boolean consoleNameMatches(String name)
	{
		String str = name.toLowerCase();
		boolean b = str.contains("omdev") || str.contains("rml") || str.contains("omc") || str.contains("openmodelica"); 	
		return (b);
	}
	
}
