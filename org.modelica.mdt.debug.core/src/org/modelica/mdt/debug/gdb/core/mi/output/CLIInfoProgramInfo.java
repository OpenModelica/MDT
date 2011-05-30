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
 * GDB/MI info program parsing.
(gdb)
info program
&"info program\n"
~"\tUsing the running image of child process 21301.\n"
~"Program stopped at 0x804853f.\n"
~"It stopped at breakpoint 1.\n"
~"Type \"info stack\" or \"info registers\" for more information.\n"
^done
(gdb)

 */
public class CLIInfoProgramInfo extends MIInfo {

	int pid;

	public CLIInfoProgramInfo(MIOutput out) {
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
					// We are interested in the signal info
					parseLine(str);
				}
			}
		}
	}

	void parseLine(String str) {
		if (str != null && str.length() > 0) {
			str = str.replace('.', ' ');
			str = str.trim();
			if (str.startsWith("Using")) {
				StringTokenizer st = new StringTokenizer(str);
				while (st.hasMoreTokens()) {
					String s = st.nextToken();
					/* Not a process id if LWP is reported */
					if (s.equals("LWP")) break;

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
}
