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
import org.modelica.mdt.debug.gdb.core.mi.output.MILogStreamOutput;
import org.modelica.mdt.debug.gdb.core.mi.output.MIOOBRecord;
import org.modelica.mdt.debug.gdb.core.mi.output.MIOutput;
import org.modelica.mdt.debug.gdb.core.mi.output.MIStreamRecord;

/**
 * @author Adeel Asghar
 *
 */
/**
 * A base class for all mi commands.
 */
public abstract class Command {

	private static int globalCounter;

	int token = 0;
	MIOutput output;
	boolean quiet = false;

	/**
	 * A global counter for all command, the token
	 * will be use to identify uniquely a command.
	 * Unless the value wraps around which is unlikely.
	 */
	private static synchronized int getUniqToken() {
		int count = ++globalCounter;
		// If we ever wrap around.
		if (count <= 0) {
			count = globalCounter = 1;
		}
		return count;
	}

	/**
	 * Returns the identifier of this request.
	 * 
	 * @return the identifier of this request
	 */
	public int getToken() {
		if (token == 0) {
			token = getUniqToken();
		}
		return token;
	}
	
//	public void setToken(int token) {
//		this.token = token;
//	}

	public MIOutput getMIOutput() {
		return output;
	}

	public void setMIOutput(MIOutput mi) {
		output = mi;
	}

	/**
	 * Parse the MIOutput generated after posting the command.
	 * @return MIInfo - the information about the posted MI command.
	 */
	public MIInfo getMIInfo () throws MIException {
		MIInfo info = null;
		MIOutput out = getMIOutput();
		if (out != null) {
			info = new MIInfo(out);
			if (info.isError()) {
				throwMIException(info, out);
			}
		}
		return info;
	}

	/**
	 * throw an MIException.
	 */
	protected void throwMIException (MIInfo info, MIOutput out) throws MIException {
		String mesg = info.getErrorMsg().trim();
		StringBuffer sb = new StringBuffer();
		MIOOBRecord[] oobs = out.getMIOOBRecords();
		for (int i = 0; i < oobs.length; i++) {
			if (oobs[i] instanceof MILogStreamOutput) {
				MIStreamRecord o = (MIStreamRecord) oobs[i];
				String s = o.getString();
				if (!s.trim().equalsIgnoreCase(mesg)) {
					sb.append(s);
				}
			}
		}
		String details = sb.toString();
		if (details.trim().length() == 0) {
			details = mesg;
		}
		throw new MIException(mesg, details);
	}

	public boolean isQuiet() {
		return this.quiet;
	}

	public void setQuiet( boolean quiet ) {
		this.quiet = quiet;
	}
}
