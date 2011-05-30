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
 * @author Adeel Asghar
 *
 */
/**
 *    -break-insert [ -t ] [ -h ] [ -r ]
 *       [ -c CONDITION ] [ -i IGNORE-COUNT ]
 *       [ -p THREAD ] [ LINE | ADDR ]
 * 
 * If specified, LINE, can be one of:
 * 
 *  * function
 *
 *  * filename:linenum
 *
 *  * filename:function
 *
 *  * *address
 * 
 *  The possible optional parameters of this command are:
 *
 * `-t'
 *     Insert a tempoary breakpoint.
 *
 * `-h'
 *     Insert a hardware breakpoint.
 *
 * `-c CONDITION'
 *     Make the breakpoint conditional on CONDITION.
 *
 * `-i IGNORE-COUNT'
 *     Initialize the IGNORE-COUNT.
 *
 * `-r'
 *
 *     Insert a regular breakpoint in all the functions whose names match
 *     the given regular expression.  Other flags are not applicable to
 *     regular expresson.
 *
 *  The result is in the form:
 *
 *     ^done,bkptno="NUMBER",func="FUNCNAME",
 *      file="FILENAME",line="LINENO"
 * 
 */
public class MIBreakInsert extends MICommand {
	public MIBreakInsert(String func) {
		this(false, false, null, 0, func, 0);
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