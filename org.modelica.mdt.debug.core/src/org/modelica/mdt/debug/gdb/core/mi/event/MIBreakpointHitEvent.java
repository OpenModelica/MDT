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
 * ^stopped,reason="breakpoint-hit",bkptno="1",thread-id="0",frame={addr="0x08048468",func="main",args=[{name="argc",value="1"},{name="argv",value="0xbffff18c"}],file="hello.c",line="4"}
 *
 */
public class MIBreakpointHitEvent extends MIStoppedEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int bkptno;
	MIFrame frame;

	public MIBreakpointHitEvent(MISession source, MIExecAsyncOutput record) {
		super(source, record);
		parse();
	}

	public MIBreakpointHitEvent(MISession source, MIResultRecord record) {
		super(source, record);
		parse();
	}

	/**
	 * This constructor is used for catchpoint hits with gdb < 7.0. In that
	 * environment, we have to get creative as gdb doesn't send us a reason with
	 * the stopped event. Fortunately, a stream record tells us the target has
	 * stopped because of a catchpoint and the associated breakpoint number.
	 * 
	 */
	public MIBreakpointHitEvent(MISession source, MIExecAsyncOutput record, int bkptNumber) {
		super(source, record);
		parse();
		bkptno = bkptNumber;
		assert bkptNumber > 0;	// we know gdb bkpt numbers are 1-based
	}

	public int getNumber() {
		return bkptno;
	}

	public MIFrame getMIFrame() {
		return frame;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("number=").append(bkptno).append('\n');
		buffer.append("thread-id=").append(getThreadId()).append('\n');
		buffer.append(frame.toString());
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
				String str = "";
				if (value != null && value instanceof MIConst) {
					str = ((MIConst)value).getString();
				}

				if (var.equals("bkptno")) {
					try {
						bkptno = Integer.parseInt(str.trim());
					} catch (NumberFormatException e) {
					}
				} else if (var.equals("thread-id")) {
					try {
						int id = Integer.parseInt(str.trim());
						setThreadId(id);
					} catch (NumberFormatException e) {
					}
				} else if (var.equals("frame")) {
					if (value instanceof MITuple) {
						frame = new MIFrame((MITuple)value);
					}
				}
			}
		}
	}
}
