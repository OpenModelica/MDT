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
 * Contain info about the GDB/MI breakpoint info.
 *<ul>
 * <li>
 * -break-insert main
 * ^done,bkpt={number="1",type="breakpoint",disp="keep",enabled="y",addr="0x0804846b",func="main",file="hello.c",line="4",times="0"}
 * (gdb)
 * </li>
 * <li>
 * -break-insert -t main
 * ^done,bkpt={number="2",type="breakpoint",disp="del",enabled="y",addr="0x0804846b",func="main",file="hello.c",line="4",times="0"}
 * </li>
 * <li>
 * -break-insert -c 1 main
^done,bkpt={number="3",type="breakpoint",disp="keep",enabled="y",addr="0x0804846b",func="main",file="hello.c",line="4",cond="1",times="0"}
 * </li>
 * <li>
 * -break-insert -h main
 * ^done,bkpt={number="4",type="hw breakpoint",disp="keep",enabled="y",addr="0x0804846b",func="main",file="hello.c",line="4",times="0"}
 * <li>
 * -break-insert -p 0 main
 * ^done,bkpt={number="5",type="breakpoint",disp="keep",enabled="y",addr="0x0804846b",func="main",file="hello.c",line="4",thread="0",thread="0",times="0"}
 * </li>
 * <li>
 * -break-insert -a p
 * ^done,hw-awpt={number="2",exp="p"}
 * </li>
 * <li>
 * -break-watch -r p
 * ^done,hw-rwpt={number="4",exp="p"}
 * </li>
 * <li>
 * -break-watch p
 * ^done,wpt={number="6",exp="p"}
 * </li>
 *</ul>
 */
public class MIBreakpoint {
	int number;
	String type = "";
	String disp = "";
	boolean enabled;
	String address;
	String func = ""; 
	String file = "";
	int line;
	String cond = "";
	int times;
	String what = "";
	String threadId = "";
	int ignore;

	boolean isWpt;
	boolean isAWpt;
	boolean isRWpt;
	boolean isWWpt;
	boolean isHdw;

	public MIBreakpoint(MITuple tuple) {
		parse(tuple);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int num) {
		number = num;
	}

	public String getType() {
		return type;
	}

	public boolean isTemporary() {
		return getDisposition().equals("del");
	}

	public boolean isWatchpoint() {
		return isWpt;
	}

	public void setWatchpoint(boolean w) {
		isWpt = w;
	}

	public boolean isHardware() {
		return isHdw;
	}

	public void setHardware(boolean hd) {
		isWpt = hd;
		isHdw = hd;
	}

	public boolean isAccessWatchpoint() {
		return isAWpt;
	}

	public void setAccessWatchpoint(boolean a) {
		isWpt = a;
		isAWpt = a;
	}

	public boolean isReadWatchpoint() {
		return isRWpt;
	}

	public void setReadWatchpoint(boolean r) {
		isWpt = r;
		isRWpt = r;
	}

	public boolean isWriteWatchpoint() {
		return isWWpt;
	}

	public void setWriteWatchpoint(boolean w) {
		isWpt = w;
		isWWpt = w;
	}

	public String getDisposition() {
		return disp;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean e) {
		enabled = e;
	}

	public String getAddress() {
		return address;
	}

	public String getFunction() {
		return func;
	}

	public String getFile() {
		return file;
	}

	public int getLine() {
		return line;
	}

	public int getTimes() {
		return times;
	}

	public String getWhat() {
		return what;
	}

	public int getIgnoreCount() {
		return ignore;
	}

	public String getCondition() {
		return cond;
	}

	public String getThreadId() {
		return threadId;
	}

	void parse(MITuple tuple) {
		MIResult[] results = tuple.getMIResults();
		for (int i = 0; i < results.length; i++) {
			String var = results[i].getVariable();
			MIValue value = results[i].getMIValue();
			String str = "";
			if (value != null && value instanceof MIConst) {
				str = ((MIConst)value).getCString();
			}

			if (var.equals("number")) {
				try {
					number = Integer.parseInt(str.trim());
				} catch (NumberFormatException e) {
				}
			} else if (var.equals("type")) {
				type = str;
				//type="hw watchpoint"
				if (type.startsWith("hw")) {
					isHdw = true;
					isWWpt = true;
					isWpt = true;
				}
				//type="acc watchpoint"
				if (type.startsWith("acc")) {
					isWWpt = true;
					isRWpt = true;
					isWpt = true;
				}
				//type="read watchpoint"
				if (type.startsWith("read")) {
					isRWpt = true;
					isWpt = true;
				}
				// ??
				if (type.equals("watchpoint")) {
					isWpt = true;
				}
				// type="breakpoint"
				// default ok.
			} else if (var.equals("disp")) {
				disp = str;
			} else if (var.equals("enabled")) {
				enabled = str.equals("y");
			} else if (var.equals("addr")) {
				try {
					address = str.trim();
				} catch (NumberFormatException e) {
				}
			} else if (var.equals("func")) {
				func = str;
			} else if (var.equals("file")) {
				file = str;
			} else if (var.equals("thread")) {
				threadId = str;
			} else if (var.equals("line")) {
				try {
					line = Integer.parseInt(str.trim());
				} catch (NumberFormatException e) {
				}
			} else if (var.equals("times")) {
				try {
					times = Integer.parseInt(str.trim());
				} catch (NumberFormatException e) {
				}
			} else if (var.equals("what") || var.equals("exp")) {
				what = str;
			} else if (var.equals("ignore")) {
				try {
					ignore = Integer.parseInt(str.trim());
				} catch (NumberFormatException e) {
				}
			} else if (var.equals("cond")) {
				cond = str;
			}
		}
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public void setLine(int line) {
		this.line = line;
	}
}
