/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Linkoping University,
 * Department of Computer and Information Science,
 * SE-58183 Linkoping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 
 * AND THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S  
 * ACCEPTANCE OF THE OSMC PUBLIC LICENSE.
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from Linkoping University, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS
 * OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 */
package org.modelica.mdt.debug.gdb.core.mi.output;

/**
 * @author Adeel Asghar
 *
 */
/**
 * GDB/MI const value represents a ios-c string.
 */
public class MIConst extends MIValue {
	
	String cstring = "";

	public String getCString() {
		return cstring;
	}

	public void setCString(String str) {
		cstring = str;
	}

	/**
	 * Translate gdb c-string.
	 */
	public String getString() {
		return getString(cstring);
	}

	public static String getString(String str) {
		StringBuffer buffer = new StringBuffer();
		boolean escape = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '\\') {
				if (escape) {
					buffer.append(c);
					escape = false;
				} else {
					escape = true;
				}
			} else {
				if (escape) {
					if (isIsoCSpecialChar(c)) {
						buffer.append(isoC(c));
					} else {
						buffer.append('\\');
						buffer.append(c);
					}
				} else {
					buffer.append(c);
				}
				escape = false;
			}
		}

		// If escape is still true it means that the
		// last char was an '\'.
		if (escape) {
			buffer.append('\\');
		}

		return buffer.toString();
	}

	public String toString() {
		return getCString();
	}

	/**
	 * Assuming that the precedent character was the
	 * escape sequence '\'
	 */
	private static String isoC(char c) {
		String s = new Character(c).toString();
		if (c == '"') {
			s = "\"";
		} else if (c == '\'') {
			s = "\'";
		} else if (c == '?') {
			s = "?";
		} else if (c == 'a') {
			s = "\007";
		} else if (c == 'b') {
			s = "\b";
		} else if (c == 'f') {
			s = "\f";
		} else if (c == 'n') {
			s = System.getProperty("line.separator", "\n");
		} else if (c == 'r') {
			s = "\r";
		} else if (c == 't') {
			s = "\t";
		} else if (c == 'v') {
			s = "\013";
		}
		return s;
	}

	private static boolean isIsoCSpecialChar(char c) {
		switch (c) {
			case '"':
			case '\'':
			case '?':
			case 'a':
			case 'b':
			case 'f':
			case 'n':
			case 'r':
			case 't':
			case 'v':
				return true;
		}
		return false;
	}
}
