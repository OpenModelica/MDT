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
 * GDB/MI ResultRecord.
 */
public class MIResultRecord {
	
	public final static String DONE ="done";
	public final static String RUNNING ="running";
	public final static String CONNECTED ="connected";
	public final static String ERROR ="error";
	public final static String EXIT ="exit";

	static final MIResult[] nullResults = new MIResult[0];
	MIResult[] results = nullResults;
	String resultClass = "";
	int token = -1;

	public int getToken() {
		return token;
	}

	public void setToken(int t) {
		token = t;
	}

	/**
	 */
	public String getResultClass() {
		return resultClass;
	}

	public void setResultClass(String type) {
		resultClass = type;
	}

	public MIResult[] getMIResults() {
		return results;
	}

	public void setMIResults(MIResult[] res) {
		results = res;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(token).append('^').append(resultClass);
		for (int i = 0; i < results.length; i++) {
			buffer.append(',').append(results[i].toString());
		}
		return buffer.toString();
	}
}
