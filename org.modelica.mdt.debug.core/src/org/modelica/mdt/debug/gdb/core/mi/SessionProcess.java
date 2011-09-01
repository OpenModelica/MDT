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
package org.modelica.mdt.debug.gdb.core.mi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.modelica.mdt.debug.gdb.core.mi.command.CLICommand;
import org.modelica.mdt.debug.gdb.core.mi.command.Command;
import org.modelica.mdt.debug.gdb.core.mi.command.CommandFactory;
import org.modelica.mdt.debug.gdb.core.mi.command.RawCommand;

/**
 * @author Adeel Asghar
 *
 */
public class SessionProcess extends Process {

	MISession session;
	OutputStream out;

	public SessionProcess(MISession s) {
		session = s;
	}

	/**
	 * @see java.lang.Process#destroy()
	 */
	public void destroy() {
		session.terminate();
	}

	/**
	 * @see java.lang.Process#exitValue()
	 */
	public int exitValue() {
		return session.getGDBProcess().exitValue();
	}

	/**
	 * @see java.lang.Process#getErrorStream()
	 */
	public InputStream getErrorStream() {
		return session.getMILogStream();
	}

	/**
	 * @see java.lang.Process#getInputStream()
	 */
	public InputStream getInputStream() {
		return session.getMIConsoleStream();
	}

	/**
	 * @see java.lang.Process#getOutputStream()
	 */
	public OutputStream getOutputStream() {
		if (out == null) {
			out = new OutputStream() {
				StringBuffer buf = new StringBuffer();
				@Override
				public void write(int b) throws IOException {
					// TODO Auto-generated method stub
					buf.append((char)b);
					if (b == '\n') {
						post();
					}
				}
				// Encapsulate the string sent to gdb in a fake
				// command and post it to the TxThread.
				public void post() throws IOException {
					// Throw away the newline.
					String str = buf.toString().trim();
					buf.setLength(0);
					Command cmd = null;
					// 1-
					// if We have the secondary prompt it means
					// that GDB is waiting for more feedback, use a RawCommand
					// 2-
					// Do not use the interpreterexec for stepping operation
					// the UI will fall out of step.
					// 3-
					// Normal Command Line Interface.
					boolean secondary = session.inSecondaryPrompt();
					if (secondary) {
						cmd = new RawCommand(str);
					} else if (session.useExecConsole() && str.length() > 0) { 
							//&& !CLIProcessor.isSteppingOperation(str)) {
						CommandFactory factory = session.getCommandFactory();
						cmd = factory.createMIInterpreterExecConsole(str);
					} else {
						cmd = new CLICommand(str);
					}
					try {
						// Do not wait around for the answer.
						session.postCommand(cmd, -1);
					} catch (MIException e) {
						throw new IOException(e.getMessage());
					}
				}
			};
		}
		return out;
	}

	/**
	 * @see java.lang.Process#waitFor()
	 */
	public int waitFor() throws InterruptedException {
		return session.getGDBProcess().waitFor();
	}
}
