/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.modelica.importer.helper;

import java.util.ArrayList;

public class StringHandler {


	public static void main(String[] args) {
//		getComponentsExample();
//		getClassInformationExample();
//		connectExample();
		getNextSimcolonExample();
	}

	private static void getNextSimcolonExample(){
		String string = "connect(Pre1.y,And1.u3) ; connect(Pre1.y,And1.u2) annotation(Line(points = {{-19,-50},{-10,-50},{-10,-18},{-2,-18}}, color = {255,0,255}));";
		System.err.println(getNextSemicolonPosition(string, 0, "connect(Pre1.y,And1.u3"));
	}

	
	private static void connectExample(){
		String string = "connect(Pre1.y,And1.u2) annotation(Line(points = {{-19,-50},{-10,-50},{-10,-18},{-2,-18}}, color = {255,0,255}));";
		System.err.println(getConnectEndsString(string));
	}
	
	private static void getComponentsExample(){
		// reply from getComponents 
//		String value = "{{\"Real\",\"r\",\"This is a comment. Text text \", \"text\", text text ...\", \"public\", \"false\", \"false\", \"false\", \"true\", \"unspecified\", \"none\", \"unspecified\",\"{}\"}}";
//		String value = "{{\"Real\",\"r\",\"This is a comment. \", \"public\", \"false\", \"false\", \"false\", \"true\", \"unspecified\", \"none\", \"unspecified\",\"{}\"}}";
		String value = "{" +
				"{\"Real\",\"r\",\"This is a comment. \", \"public\", \"false\", \"false\", \"false\", \"true\", \"unspecified\", \"none\", \"unspecified\",\"{}\"}," +
				"{\"Real\",\"r2\",\"This is a comment. \", \"public\", \"false\", \"false\", \"false\", \"true\", \"unspecified\", \"none\", \"unspecified\",\"{}\"}," +
				"{\"Real\",\"r3\",\"This is a comment. \", \"public\", \"false\", \"false\", \"false\", \"true\", \"unspecified\", \"none\", \"unspecified\",\"{}\"}" +
				"}";
		
		System.err.println(value);
		System.err.println("unparseArrays: " + unparseArrays(value));
		for (String string : unparseArrays(value)) {
			System.err.println("unparseStrings:" + unparseStrings(string));
		}
		
	}
	
	private static void getClassInformationExample(){
		// reply from getClassInformation
		String value = "{\"type\",\"This is the comment\",\"<interactive>\",{false,false,false},{\"writable\",2,17,2,43},{3}}";
		System.err.println(value);
		System.err.println("unparseArrays: " + unparseArrays(value));
		System.err.println("unparseStrings:" + unparseStrings(value));
		
		System.err.println("class restriction: " + unparseStrings(value).get(0));
		System.err.println("class comment: " + unparseStrings(value).get(1));
		System.err.println("class arraySize: " + removeFirstLastCurlBrackets(unparseArrays(value).get(2)));
		
		
	}
	
	
	public static String removeFirstLastCurlBrackets(String value){
		 value = value.trim();
		    if (value.length() > 1 && value.charAt(0) == '{' && value.charAt(value.length() - 1) == '}')
		    {
		        value = value.substring(1, (value.length() - 1));
		    }
		    return value;
	}
	
	public static String removeFirstLastBrackets(String value){
		 value = value.trim();
		    if (value.length() > 1 && value.charAt(0) == '(' && value.charAt(value.length() - 1) == ')')
		    {
		        value = value.substring(1, (value.length() - 1));
		    }
		    return value;
	}
	
	public static String removeFirstLastDoubleQuotes(String value){
		 value = value.trim();
		    if (value.length() > 1 && value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"')
		    {
		        value = value.substring(1, (value.length() - 1));
		    }
		    return value;
	}
	
	public static ArrayList<String> unparseArrays(String value) {
		
		 ArrayList<String> lst = new ArrayList<String>();
		  int qopen = 0;
		  int qopenindex = 0;
		  int braceopen = 0;
		  int mainbraceopen = 0;
		  int i = 0;
		  int count = 0;
		  value = StringHandler.removeFirstLastCurlBrackets(value);
//		  System.err.println(value);
		  int length = value.length();
		  int subbraceopen = 0;
		  
		  for (i=0; i < length ; i++)
		  {
		    if (value.charAt(i) == ' ' || value.charAt(i) == ',') continue; // ignore any kind of space
		    if (value.charAt(i) == '{' && qopen == 0 && braceopen == 0)
		    {
		    	
		      braceopen = 1;
		      mainbraceopen = i;
		      continue;
		    }
		    if (value.charAt(i) == '{')
		    {
		      subbraceopen = 1;
		    }
		    
		    char temp = value.charAt(i);
		    
		    if (value.charAt(i) == '}' && braceopen == 1 && qopen == 0 && subbraceopen == 0)
		    {
		      //closing of a group
//		      char * tmp = new char [i -mainbraceopen +5];
		      int copylength = i - mainbraceopen + 1;
//		      strncpy (tmp, value.toStdString().data() + mainbraceopen , copylength);
//		      tmp [copylength] = 0;
		      braceopen = 0;
		      //printf("arr[%d]=%s;\n", count, tmp);
//		      lst.append(QString(tmp));
//		      lst.add(value.substring(value.length() + mainbraceopen, copylength));
		      lst.add(value.substring(mainbraceopen, mainbraceopen + copylength));
//		      delete tmp;
		      count ++;
		      continue;
		    }
		    if (value.charAt(i) == '}')
		      subbraceopen = 0;

		    if (value.charAt(i) == '\"')
		    {
		      if (qopen == 0)
		      {
		        qopen = 1;
		        qopenindex = i;
		      }
		      else
		      {
		        // its a closing quote
		        qopen = 0;
		      }
		    }
		  }
		  return lst;
	}
	

	private static String CONSUME_CHAR(String value,String res,int i) {
		if (value.charAt(i) == '\\') { 
		    i++; 
		    switch (value.charAt(i)) { 
		    case '\'': res = res + '\''; break; 
		    case '"':  res = res + '\"'; break; 
//		    case '?':  res.append('\?'); break; 
//		    case '\\': res.append('\\'); break; 
//		    case 'a':  res.append('\a'); break; 
//		    case 'b':  res.append('\b'); break; 
//		    case 'f':  res.append('\f'); break; 
//		    case 'n':  res.append('\n'); break; 
//		    case 'r':  res.append('\r'); break; 
//		    case 't':  res.append('\t'); break; 
//		    case 'v':  res.append('\v'); break; 
		    } 
		    } else { 
		    	res = res + value.charAt(i); 
		    }
		return res;
		
	}
	
	
	
	public static ArrayList<String> unparseStrings(String value){
		ArrayList<String> lst = new ArrayList<String>();
	    value = value.trim();
	    
	    if (value.charAt(0) != '{') return lst; // ERROR?
	    int i=1;
	    String res = "";
	    while (value.charAt(i) == '"') {
	        i++;
	        while (value.charAt(i) != '"') {
	        	res = CONSUME_CHAR(value,res,i);
	            i++;
	            /* if we have unexpected double quotes then, however omc should return \" */
	            /* remove this block once fixed in omc */
	            if (value.charAt(i) == '"' && value.charAt(i+1) != ',') {
	                if (value.charAt(i+1) != '}') {
	                	res = CONSUME_CHAR(value,res,i);
	                    i++;
	                }
	            }
	            /* remove this block once fixed in omc */
	        }
	        i++;
	        if (value.charAt(i) == '}') {
	            lst.add(res);
	            return lst;
	        }
	        if (value.charAt(i) == ',') {
	            lst.add(res);
	            i++;
	            res = "";
	            while (value.charAt(i) == ' ')     // if we have space before next value e.g {"x", "y", "z"}
	              i++;
	            continue;
	        }
//	        while (value.charAt(i) != '"' && value.charAt(i) != '\0') {
//	            i++;
////	            fprintf(stderr, "error? malformed string-list. skipping: %c\n", value[i].toAscii());
//	        }
	    }
	    return lst; // ERROR?
	}
	
	
	public static String getStringInCurlyBraces(String value){

		value = value.trim();
	    String res = "";
	    
	    if (value.charAt(0) != '{') return res; // ERROR?
	    int i=1;
	    while (value.charAt(i) != '}') {
	        res = CONSUME_CHAR(value,res,i);
	        i++;
	    }
	    return res; // ERROR?
	}
	
	
	public static String getConnectEndsString(String value){
	    value = value.trim();
	    String connectEndsString = "";
	    int i=0;
	    
	    // find the first bracket (i.e. after "connect")
	    while (value.charAt(i) != '('){
	    	i++;
	    	value = CONSUME_CHAR(value,value,i);
	    }
	    
	    while (value.charAt(i) == '(') { // start token, i.e. connect(
	        i++;
	        while (value.charAt(i) != ')') { // stop token
	        	connectEndsString = CONSUME_CHAR(value,connectEndsString,i);
	            i++;
	        }
	        return connectEndsString;
	    }
	    return null; // ERROR?
	}
	
	public static int getNextSemicolonPosition(String entireString, int subStringStart, String subString){
		int i = subStringStart + subString.length() - 1;
		
		if ( i >= 0 && i < entireString.length()) {
			while (entireString.charAt(i) != ';'){
		    	i++;
		    	
		    	if (entireString.charAt(i) == ';') {
		    		return i;
				}
		    	if (i == entireString.length()) {
					return -1;
				}
		    }
		}

		return -1;
	}
	
	public static ArrayList<String> getConnectEnds(String string){
		ArrayList<String> ends = new ArrayList<String>();
		String connectdEndsString = getConnectEndsString(string);
		if (connectdEndsString != null) {
			String[] splitted = connectdEndsString.split(",");
			if (splitted.length == 2) {
				ends.add(splitted[0].trim());
				ends.add(splitted[1].trim());
				
				return ends;
			}
		}
	    return null;
	}
	
}
