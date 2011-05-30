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
package org.modelica.mdt.debug.gdb.core.mi.event;

import org.modelica.mdt.debug.gdb.core.mi.MISession;
import org.modelica.mdt.debug.gdb.core.mi.output.MIConst;
import org.modelica.mdt.debug.gdb.core.mi.output.MILogStreamOutput;
import org.modelica.mdt.debug.gdb.core.mi.output.MIOOBRecord;
import org.modelica.mdt.debug.gdb.core.mi.output.MIResult;
import org.modelica.mdt.debug.gdb.core.mi.output.MIResultRecord;
import org.modelica.mdt.debug.gdb.core.mi.output.MIStreamRecord;
import org.modelica.mdt.debug.gdb.core.mi.output.MIValue;

/**
 * @author Adeel Asghar
 *
 */
/**
 * (gdb)
 * &"warning: Cannot insert breakpoint 2:\n"
 * &"Cannot access memory at address 0x8020a3\n"
 * 30^error,msg=3D"Cannot access memory at address 0x8020a3"=20
 */
public class MIErrorEvent extends MIStoppedEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String msg = "";
	String log = "";
	MIOOBRecord[] oobs;

	public MIErrorEvent(MISession source, MIResultRecord rr, MIOOBRecord[] o) {
		super(source, rr);
		oobs = o;
		parse();
	}

	public String getMessage() {
		return msg;
	}

	public String getLogMessage() {
		return log;
	}

	void parse () {
		MIResultRecord rr = getMIResultRecord();
		if (rr != null) {
			MIResult[] results = rr.getMIResults();
			if (results != null) {
				for (int i = 0; i < results.length; i++) {
					String var = results[i].getVariable();
					MIValue value = results[i].getMIValue();
					String str = "";
					if (value instanceof MIConst) {
						str = ((MIConst)value).getString();
					}

					if (var.equals("msg")) {
						msg = str;
					}
				}
			}
			if (oobs != null) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < oobs.length; i++) {
					if (oobs[i] instanceof MILogStreamOutput) {
						MIStreamRecord o = (MIStreamRecord)oobs[i];
						sb.append(o.getString());
					}
				}
				log = sb.toString();
			}
		}
	}
}
