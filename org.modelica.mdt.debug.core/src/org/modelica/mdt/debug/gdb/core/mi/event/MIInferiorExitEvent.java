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
import org.modelica.mdt.debug.gdb.core.mi.output.MIResult;
import org.modelica.mdt.debug.gdb.core.mi.output.MIResultRecord;
import org.modelica.mdt.debug.gdb.core.mi.output.MIValue;

/**
 * @author Adeel Asghar
 *
 */
/**
 * *stopped,reason="exited-normally"
 * *stopped,reason="exited",exit-code="04"
 * ^done,reason="exited",exit-code="04"
 *
 */
public class MIInferiorExitEvent extends MIDestroyedEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int code = 0;

	MIExecAsyncOutput exec = null;
	MIResultRecord rr = null;

	public MIInferiorExitEvent(MISession source, int token) {
		super(source, token);
	}

	public MIInferiorExitEvent(MISession source, MIExecAsyncOutput async) {
		super(source, async.getToken());
		exec = async;
		parse();
	}

	public MIInferiorExitEvent(MISession source, MIResultRecord record) {
		super(source, record.getToken());
		rr = record;
		parse();
	}

	public int getExitCode() {
		return code;
	}

	void parse () {
		MIResult[] results = null;
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
				if (value instanceof MIConst) {
					str = ((MIConst)value).getString();
				}

				if (var.equals("exit-code")) {
					try {
						code = Integer.decode(str.trim()).intValue();
					} catch (NumberFormatException e) {
					}
				}
			}
		}
	}
}
