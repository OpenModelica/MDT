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
import org.modelica.mdt.debug.gdb.core.mi.output.MIStackListVariablesInfo;

/**
 * @author Adeel Asghar
 *
 *  -stack-list-variables print-values
 *  Display the names of local variables and function arguments for the selected frame.
 *  If print-values is 0 or --no-values, print only the names of the variables; 
 *  if it is 1 or --all-values, print also their values.
 * 
 */
public class MIStackListVariables extends MICommand {
	
	public MIStackListVariables(String[] params) {
		super("-stack-list-variables", params);
	}

	public MIStackListVariablesInfo getMIStackListVariablesInfo() throws MIException {
		return (MIStackListVariablesInfo)getMIInfo();
	}

	public MIInfo getMIInfo() throws MIException {
		MIInfo info = null;
		MIOutput out = getMIOutput();
		if (out != null) {
			info = new MIStackListVariablesInfo(out);
			if (info.isError()) {
				throwMIException(info, out);
			}
		}
		return info;
	}
}
