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
package org.modelica.mdt.debug.gdb.helper;

import java.io.UnsupportedEncodingException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.modelica.mdt.debug.core.launcher.IMDTConstants;
import org.modelica.mdt.debug.gdb.core.mi.output.MIFrame;
import org.modelica.mdt.debug.gdb.core.model.GDBDebugTarget;

/**
 * @author Adeel Asghar
 *
 */
public class GDBHelper {
	/* Meta Modelica Types */
	public static String MODELICA_METATYPE = "modelica_metatype";
	public static String MODELICA_BOOLEAN = "modelica_boolean";
	public static String MODELICA_INETGER = "modelica_integer";
	public static String MODELICA_REAL = "modelica_real";
	public static String REPLACEABLE_TYPE_ANY = "replaceable type Any";
	public static String LIST = "list";
	public static String RECORD = "record";
	public static String TUPLE = "tuple";
	public static String OPTION = "Option";
	public static String ARRAY = "Array";
	public static String NONE = "NONE()";
	public static String SOME = "SOME()";
	/* Modelica Types */
	public static String STRING = "String";
	public static String BOOLEAN = "Boolean";
	public static String INTEGER = "Integer";
	public static String REAL = "Real";
	/* Standard Modelica Arrays */
	public static String MODELICA_INTEGER_ARRAY = "integer_array";
	public static String MODELICA_REAL_ARRAY = "real_array";
	public static String MODELICA_BOOLEAN_ARRAY = "boolean_array";
	public static String MODELICA_STRING_ARRAY = "string_array";
	/* Signals */
	public static String SIGINT = "SIGINT";
	public static String SIGTRAP = "SIGTRAP";
	public static String SIGSEGV = "SIGSEGV";
	
	public static Boolean filterCFiles(GDBDebugTarget gdbDebugTarget, MIFrame miFrame) throws CoreException {
		if (miFrame.getFile().isEmpty()) {
			return true;
		}
		String debugCSource = gdbDebugTarget.getLaunch().getLaunchConfiguration().getAttribute(IMDTConstants.ATTR_MDT_DEBUG_C_SOURCE, "false");
		if (debugCSource.equals("true")) {
			// if debugcsource is true we return false but we have to make sure that
			// user don't get any bootstrapped c file
			Path filePath = new Path(miFrame.getFile());
			if (filePath.segment(filePath.segmentCount() - 2).equals("bootstrapping")) {
				return true;
			}
			return false;
		} else {
			return !isMoFile(miFrame.getFile());
		}
	}
	
	/**
	 * @param file
	 * @return
	 */
	public static Boolean isMoFile(String file) {
		// TODO Auto-generated method stub
		if (new Path(file).getFileExtension().equals("mo")) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean isCFile(String file) {
		if (new Path(file).getFileExtension().equals("c")) {
			return true;
		} else if (new Path(file).getFileExtension().equals("cpp")) {
			return true;
		} else if (new Path(file).getFileExtension().equals("cc")) {
			return true;
		} else if (new Path(file).getFileExtension().equals("h")) {
			return true;
		} else {
			return false;
		}
	}

	public static String getListType(String listType) {
		int beginIndex = listType.indexOf("<");
		int endIndex = listType.lastIndexOf(">");
		return listType.substring(beginIndex + 1, endIndex);
	}
	
	/**
	 * Converts OMC hex string to normal java string with utf-8 encoding
	 * @param hexString
	 * @return javaString
	 */
	public static String omcHexToString(String hexString) {
		String hex = hexString.substring(9);					// since the hex value starts with _omcQuot_
		
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
		   bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}

		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hexString;
	}
}
