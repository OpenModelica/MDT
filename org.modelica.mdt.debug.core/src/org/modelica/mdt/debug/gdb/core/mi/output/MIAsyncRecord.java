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
 * base Abstract class for the OOB stream MI responses. 
 */
public abstract class MIAsyncRecord extends MIOOBRecord {
	
	public final static String RUNNING ="running";
	public final static String STOPPED ="stopped";
	public final static String THREAD_GROUP_ADDED ="thread-group-added";
	public final static String THREAD_GROUP_STARTED ="thread-group-started";
	public final static String THREAD_CREATED ="thread-created";
	public final static String LIBRARY_LOADED ="library-loaded";
	public final static String THREAD_EXITED ="thread-exited";
	public final static String THREAD_GROUP_EXITED ="thread-group-exited";
	
	final static MIResult[] nullResults = new MIResult[0];

	MIResult[] results = null;
	String asynClass = ""; //$NON-NLS-1$
	int token = 0;

	public int getToken() {
		return token;
	}

	public void setToken(int t) {
		token = t;
	}

	public String getAsyncClass() {
		return asynClass;
	}

	public void setAsyncClass(String a) {
		asynClass = a;
	}

	public MIResult[] getMIResults() {
		if (results == null) {
			return nullResults;
		}
		return results;
	}

	public void setMIResults(MIResult[] res) {
		results = res;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if (token != 0) {
			buffer.append(token);
		}
		if (this instanceof MIExecAsyncOutput) {
			buffer.append('*');
		} else if (this instanceof MIStatusAsyncOutput) {
			buffer.append('+');
		} else if (this instanceof MINotifyAsyncOutput) {
			buffer.append('=');
		}
		buffer.append(asynClass);
		if (results != null) {
			for (int i = 0; i < results.length; i++) {
				buffer.append(',');
				buffer.append(results[i].toString());
			}
		}
		buffer.append('\n');
		return buffer.toString();
	}
}
