
package org.modelica.mdt.ui.text;

public class ModelicaKeywords
{
	/* keywords list has to be alphabetized for this to work properly */
	static public String[] keywords = {
		"algorithm","and","annotation","assert",
		"block","break",
		"class","connect","connector","constant",
		"der","discrete",
		"each","else","elseif","elsewhen","encapsulated","end",
		"enumeration","equation",
		"extends","external","false","final",
		"flow","for","function",
		"if","import","in","initial","inner","input",
		"loop","model","not","or","outer",
		"output","overload","package","parameter",
		"partial","protected","public","record",
		"redeclare","replaceable","return", "terminate",
		"then","time","true","type","when","while","within",
		
		/* The following are Meta-Modelica Keywords */
		"as", "case", "equality", "failure", "list", "local", "match", 
		"matchcontinue" , "tuple", "uniontype", "lvar", "fail", "subtypeof"
	};
	
	public static boolean isKeyword(String key)
	{
		for (int i=0; i<keywords.length;i++) 
		{
			if (keywords[i].equals(key)) return true;
		}
		return false;
	}
	
}
