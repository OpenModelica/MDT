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
 * GDB/MI response.
 */
public class MIOutput {
	public static final MIOOBRecord[] nullOOBRecord = new MIOOBRecord[0];
	MIResultRecord rr = null;
	MIOOBRecord[] oobs = nullOOBRecord;

	public MIResultRecord getMIResultRecord() {
		return rr;
	}

	public void setMIResultRecord(MIResultRecord res) {
		rr = res ;
	}

	public MIOOBRecord[] getMIOOBRecords() {
		return oobs;
	}

	public void setMIOOBRecords(MIOOBRecord [] bands) {
		oobs = bands;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < oobs.length; i++) {
			buffer.append(oobs[i].toString());
		}
		if (rr != null) {
			buffer.append(rr.toString());
		}
		return buffer.toString();
	}
}
