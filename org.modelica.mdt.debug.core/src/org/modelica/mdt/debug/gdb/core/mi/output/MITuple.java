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
 * GDB/MI tuple value.
 */
public class MITuple extends MIValue {

	final static MIResult[] nullResults = new MIResult[0];
	final static MIValue[] nullValues = new MIValue[0];
	MIResult[] results = nullResults;
	MIValue[] values = nullValues;

	public MIResult[] getMIResults() {
		return results;
	}

	public void setMIResults(MIResult[] res) {
		results = res;
	}

	public MIValue[] getMIValues() {
		return values;
	}

	public void setMIValues(MIValue[] vals) {
		values = vals;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append('{');
		for (int i = 0; i < results.length; i++) {
			if (i != 0) {
				buffer.append(',');
			}
			buffer.append(results[i].toString());
		}
		for (int i = 0; i < values.length; i++) {
			if (i != 0) {
				buffer.append(',');
			}
			buffer.append(values[i].toString());
		}
		buffer.append('}');
		return buffer.toString();
	}
}
