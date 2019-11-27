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
 * GDB/MI thread info parsing.
 * -thread-info
 * ^done,threads=[{id="2",target-id="Thread 0xb7e14b90 (LWP 21257)",frame={level="0",addr="0xffffe410",func="__kernel_vsyscall",args=[]},state="running"},
 *               {id="1",target-id="Thread 0xb7e156b0 (LWP 21254)",frame={level="0",addr="0x0804891f",func="foo",args=[{name="i",value="10"}],file="/tmp/a.c",fullname="/tmp/a.c",line="158",arch="i386:x86_64"},state="running"}
 *               ],current-thread-id="1" 
 *
 */
public class MIThreadInfoInfo extends MIInfo {

	int currentThreadId = 1;

	public MIThreadInfoInfo(MIOutput out) {
		super(out);
		parse();
	}

	public int getcurrentThreadId() {
		return currentThreadId;
	}

	void parse() {
		if (isDone()) {
			MIOutput out = getMIOutput();
			MIResultRecord rr = out.getMIResultRecord();
			if (rr != null) {
				MIResult[] results =  rr.getMIResults();
				for (int i = 0; i < results.length; i++) {
					String var = results[i].getVariable();
					if (var.equals("current-thread-id")) {
						MIValue val = results[i].getMIValue();
						if (val instanceof MIConst) {
							String str = ((MIConst)val).getCString();
							try {
								currentThreadId = Integer.parseInt(str.trim());
							} catch (NumberFormatException e) {
							}
						}
					}
				}
			}
		}
	}
}
