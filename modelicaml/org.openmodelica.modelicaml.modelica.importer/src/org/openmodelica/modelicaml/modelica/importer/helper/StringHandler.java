package org.openmodelica.modelicaml.modelica.importer.helper;

import java.util.ArrayList;

public class StringHandler {


	public static void main(String[] args) {
//		getComponentsExample();
		getClassInformationExample();
		
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
	        while (value.charAt(i) != '"' && value.charAt(i) != '\0') {
	            i++;
//	            fprintf(stderr, "error? malformed string-list. skipping: %c\n", value[i].toAscii());
	        }
	    }
	    return lst; // ERROR?
	}
	
}
