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

import java.util.StringTokenizer;

/**
 * @author Adeel Asghar
 *
 */
/**
 * GDB/CLI info proc parsing.
(gdb) info proc
process 19127 flags:
PR_STOPPED Process (LWP) is stopped
PR_ISTOP Stopped on an event of interest
PR_RLC Run-on-last-close is in effect
PR_FAULTED : Incurred a traced hardware fault FLTBPT: Breakpoint trap
 */
public class CLIInfoProcInfo extends MIInfo {

	int pid;

	public CLIInfoProcInfo(MIOutput out) {
		super(out);
		parse();
	}

	public int getPID() {
		return pid;
	}

	void parse() {
		if (isDone()) {
			MIOutput out = getMIOutput();
			MIOOBRecord[] oobs = out.getMIOOBRecords();
			for (int i = 0; i < oobs.length; i++) {
				if (oobs[i] instanceof MIConsoleStreamOutput) {
					MIStreamRecord cons = (MIStreamRecord) oobs[i];
					String str = cons.getString();
					// We are interested in the process info and PID
					parseLine(str);
				}
			}
		}
	}

	void parseLine(String str) {
		if (str != null && str.length() > 0) {
			str = str.trim();
			if (!str.startsWith("process")) {
				return;
			}
			StringTokenizer st = new StringTokenizer(str);
			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				if (Character.isDigit(s.charAt(0))) {
					try {
						pid = Integer.decode(s).intValue();
						break;
					} catch (NumberFormatException e) {
					}
				}
			}
		}
	}
}
