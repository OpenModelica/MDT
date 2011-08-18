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
import org.modelica.mdt.debug.gdb.core.mi.output.MIExecAsyncOutput;
import org.modelica.mdt.debug.gdb.core.mi.output.MIFrame;
import org.modelica.mdt.debug.gdb.core.mi.output.MIResult;
import org.modelica.mdt.debug.gdb.core.mi.output.MIResultRecord;
import org.modelica.mdt.debug.gdb.core.mi.output.MITuple;
import org.modelica.mdt.debug.gdb.core.mi.output.MIValue;

/**
 * @author Adeel Asghar
 *
 */
/**
*
*  *stopped,reason="end-stepping-range",thread-id="0",frame={addr="0x08048538",func="main",args=[{name="argc",value="1"},{name="argv",value="0xbffff18c"}],file="hello.c",line="13"}
*/
public class MISteppingRangeEvent extends MIStoppedEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MISteppingRangeEvent(MISession source, MIExecAsyncOutput async) {
		super(source, async);
		parse();
	}

	public MISteppingRangeEvent(MISession source, MIResultRecord record) {
		super(source, record);
		parse();
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("thread-id=").append(getThreadId()).append('\n');
		MIFrame f = getFrame();
		if (f != null) {
			buffer.append(getFrame().toString());
		}
		return buffer.toString();
	}

	void parse () {
		MIResult[] results = null;
		MIExecAsyncOutput exec = getMIExecAsyncOutput();
		MIResultRecord rr = getMIResultRecord();
		if (exec != null) {
			results = exec.getMIResults();
		} else if (rr != null) {
			results = rr.getMIResults();
		}
		if (results != null) {
			for (int i = 0; i < results.length; i++) {
				String var = results[i].getVariable();
				MIValue value = results[i].getMIValue();

				if (var.equals("thread-id")) {
					if (value instanceof MIConst) {
						String str = ((MIConst)value).getString();
						try {
							int id = Integer.parseInt(str.trim());
							setThreadId(id);
						} catch (NumberFormatException e) {
						}
					}
				} else if (var.equals("frame")) {
					if (value instanceof MITuple) {
						MIFrame f = new MIFrame((MITuple)value);
						setFrame(f);
					}
				}
			}
		}
	}
}