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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adeel Asghar
 *
 */
/**
 * -break-insert main
 * ^done,bkpt={number="1",type="breakpoint",disp="keep",enabled="y",addr="0x08048468",func="main",file="hello.c",line="4",times="0"}
 * -break-insert -a p
 * ^done,hw-awpt={number="2",exp="p"}
 * -break-watch -r p
 * ^done,hw-rwpt={number="4",exp="p"}
 * -break-watch p
 * ^done,wpt={number="6",exp="p"}
 */
public class MIBreakInsertInfo extends MIInfo {

	MIBreakpoint[] breakpoints;

	void parse() {
		List<MIBreakpoint> aList = new ArrayList<MIBreakpoint>(1);
		if (isDone()) {
			MIOutput out = getMIOutput();
			MIResultRecord rr = out.getMIResultRecord();
			if (rr != null) {
				MIResult[] results =  rr.getMIResults();
				for (int i = 0; i < results.length; i++) {
					String var = results[i].getVariable();
					MIValue val = results[i].getMIValue();
					MIBreakpoint bpt = null;
					if (var.equals("wpt")) {
						if (val instanceof MITuple) {
							bpt = new MIBreakpoint((MITuple)val);
							bpt.setEnabled(true);
							bpt.setWriteWatchpoint(true);
						}
					} else if (var.equals("bkpt")) {
						if (val instanceof MITuple) {
							bpt = new MIBreakpoint((MITuple)val);
							bpt.setEnabled(true);
						}
					} else if (var.equals("hw-awpt")) {
						if (val instanceof MITuple) {
							bpt = new MIBreakpoint((MITuple)val);
							bpt.setAccessWatchpoint(true);
							bpt.setEnabled(true);
						}
					} else if (var.equals("hw-rwpt")) {
						if (val instanceof MITuple) {
							bpt = new MIBreakpoint((MITuple)val);
							bpt.setReadWatchpoint(true);
							bpt.setEnabled(true);
						}
					}
					if (bpt != null) {
						aList.add(bpt);
					}
				}
			}
		}
		breakpoints = (MIBreakpoint[])aList.toArray(new MIBreakpoint[aList.size()]);
	}

	public MIBreakInsertInfo(MIOutput record) {
		super(record);
	}

	public MIBreakpoint[] getMIBreakpoints() {
		if (breakpoints == null) {
			parse();
		}
		return breakpoints;
	}
}
