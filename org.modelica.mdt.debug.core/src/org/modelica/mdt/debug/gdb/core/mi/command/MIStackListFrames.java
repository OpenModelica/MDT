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
import org.modelica.mdt.debug.gdb.core.mi.output.MIInfo;
import org.modelica.mdt.debug.gdb.core.mi.output.MIOutput;
import org.modelica.mdt.debug.gdb.core.mi.output.MIStackListFramesInfo;

/**
 * @author Adeel Asghar
 *
 *     -stack-list-frames [ LOW-FRAME HIGH-FRAME ]
 *
 *  List the frames currently on the stack.  For each frame it displays
 * the following info:
 *
 * `LEVEL'
 *    The frame number, 0 being the topmost frame, i.e. the innermost
 *    function.
 *
 * `ADDR'
 *    The `$pc' value for that frame.
 *
 * `FUNC'
 *    Function name.
 *
 * `FILE'
 *    File name of the source file where the function lives.
 *
 * `FULLNAME'
 *    Absolute file name of the source file where the function lives.
 *	@since gdb 6.4
 *
 * `LINE'
 *   Line number corresponding to the `$pc'.
 *
 *  If invoked without arguments, this command prints a backtrace for the
 * whole stack.  If given two integer arguments, it shows the frames whose
 * levels are between the two arguments (inclusive).  If the two arguments
 * are equal, it shows the single frame at the corresponding level.
 * 
 */
public class MIStackListFrames extends MICommand 
{
	public MIStackListFrames() {
		super("-stack-list-frames");
	}

	public MIStackListFrames(int low, int high) {
		super("-stack-list-frames", new String[]{Integer.toString(low), Integer.toString(high)});
	}

	public MIStackListFramesInfo getMIStackListFramesInfo() throws MIException {
		return (MIStackListFramesInfo)getMIInfo();
	}

	public MIInfo getMIInfo() throws MIException {
		MIInfo info = null;
		MIOutput out = getMIOutput();
		if (out != null) {
			info = new MIStackListFramesInfo(out);
			if (info.isError()) {
				throwMIException(info, out);
			}
		}
		return info;
	}
}