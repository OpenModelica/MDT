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
package org.modelica.mdt.debug.gdb.core.mi.command;

import org.modelica.mdt.debug.gdb.core.mi.MIException;
import org.modelica.mdt.debug.gdb.core.mi.output.MIBreakInsertInfo;
import org.modelica.mdt.debug.gdb.core.mi.output.MIInfo;
import org.modelica.mdt.debug.gdb.core.mi.output.MIOutput;

/**
 * -break-insert [ -t ] [ -h ] [ -f ] [ -d ] [ -a ]
 * [ -c condition ] [ -i ignore-count ]
 * [ -p thread ] [ location ]
 * 
 * If specified, location, can be one of:
 * 
 *  function
 *  filename:linenum
 *  filename:function
 *  *address
 *  
 *  The possible optional parameters of this command are:
 *  
 *  `-t'
 *  Insert a temporary breakpoint.
 *  `-h'
 *  Insert a hardware breakpoint.
 *  `-c condition'
 *  Make the breakpoint conditional on condition.
 *  `-i ignore-count'
 *  Initialize the ignore-count.
 *  `-f'
 *  If location cannot be parsed (for example if it refers to unknown files or functions), create a pending breakpoint. Without this flag, gdb will report an error, and won't create a breakpoint, if location cannot be parsed.
 *  `-d'
 *  Create a disabled breakpoint.
 *  `-a'
 *  Create a tracepoint. See Tracepoints. When this parameter is used together with `-h', a fast tracepoint is created.
 *  
 *  The result is in the form:
 *  ^done,bkpt={number="number",type="type",disp="del"|"keep",
 *  enabled="y"|"n",addr="hex",func="funcname",file="filename",
 *  fullname="full_filename",line="lineno",[thread="threadno,]
 *  times="times"}
 *  
 *  where number is the gdb number for this breakpoint, funcname is the name of the function where the breakpoint was inserted, filename is the name of the source file which contains this function, lineno is the source line number within that file and times the number of times that the breakpoint has been hit (always 0 for -break-insert but may be greater for -break-info or -break-list which use the same output). 
 *    
 * @author Adeel Asghar
 *
 */
public class MIBreakInsert extends MICommand {
	public MIBreakInsert(String func) {
		this(false, false, null, 0, func, 0);
		setOptions(new String[]{"-f"});
	}

	public MIBreakInsert(boolean isTemporary, boolean isHardware,
			 String condition, int ignoreCount, String line, int tid) {
		super("-break-insert");

		int i = 0;
		if (isTemporary) {
			i++;
		}
		if (isHardware) {
			i++;
		}
		if (condition != null && condition.length() > 0) {
			i += 2;
		}
		if (ignoreCount > 0) {
			i += 2;
		}
		if (tid > 0) {
			i += 2;
		}
		String[] opts = new String[i];
		
		i = 0;
		if (isTemporary) {
			opts[i] = "-t";
			i++;
		} 
		if (isHardware) {
			opts[i] = "-h";
			i++;
		}
		if (condition != null && condition.length() > 0) {
			opts[i] = "-c";
			i++;
			opts[i] = condition;
			i++;
		}
		if (ignoreCount > 0) {
			opts[i] = "-i";
			i++;
			opts[i] = Integer.toString(ignoreCount);
			i++;
		}
		if (tid > 0) {
			opts[i] = "-p";
			i++;
			opts[i] = Integer.toString(tid);
		}

		if (opts.length > 0) {
			setOptions(opts);
		}
		setParameters(new String[]{line});
	}

	public MIBreakInsertInfo getMIBreakInsertInfo() throws MIException {
		return (MIBreakInsertInfo)getMIInfo();
	}

	public MIInfo getMIInfo() throws MIException {
		MIInfo info = null;
		MIOutput out = getMIOutput();
		if (out != null) {
			info = new MIBreakInsertInfo(out);
			if (info.isError()) {
				throwMIException(info, out);
			}
		}
		return info;
	}
}