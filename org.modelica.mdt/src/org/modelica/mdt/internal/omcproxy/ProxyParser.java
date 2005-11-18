package org.modelica.mdt.internal.omcproxy;

/**
 * 
 * @author Andreas Remar
 *
 */
public class ProxyParser
{
	/* This very simple parser just splits a flat list into
	 * substrings. Doesn't handle nested lists. */
	public static String[] parseList(String str)
	{
		/* remove whitespace before and after */
		str = str.trim();
		
		/* check that a list is parsed, otherwise this is probably an error */
		if(str.charAt(0) != '{' || str.charAt(str.length() - 1) != '}')
			return null;

		/* remove {} and split into tokens */
		str = str.substring(1, str.length() - 1);
		String[] retvals = str.split(",");
		
		return retvals;
	}
	
}
