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
import java.io.OutputStream;

import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.gdb.core.mi.command.Command;
import org.modelica.mdt.debug.gdb.core.mi.command.RawCommand;

/**
 * @author Adeel Asghar
 *
 */
/**
 * This thread reads the commands from txQueue  
 * and writes them to the GDB output stream.
 * 
 */
public class TxThread extends Thread {
	MISession session;
	
	public TxThread(MISession s) {
		super("MI TX Thread");
		session = s;
	}
	public void run () {
		try {
			// signal by the session of time to die.
			OutputStream out;
			while ((out = session.getChannelOutputStream()) != null) {
				Command cmd = null;
				CommandQueue txQueue = session.getTxQueue();
				// removeCommand() will block until a command is available.
				try {
					cmd = txQueue.removeCommand();
				} catch (InterruptedException e) {
					//e.printStackTrace();
				}

				if (cmd != null) {
					String str = cmd.toString();
					// if string is empty consider as a noop
					if (MDTDebugCorePlugin.DEBUG) System.out.println("MI Tx Thread " + str);
					if (str.length() > 0) {
						// Move to the RxQueue only if RxThread is alive.
						Thread rx = session.getRxThread();
						if (rx != null && rx.isAlive() && !(cmd instanceof RawCommand)) {
							CommandQueue rxQueue = session.getRxQueue();
							rxQueue.addCommand(cmd);
						} else {
							// The RxThread is not running
							synchronized (cmd) {
								cmd.notifyAll();
							}
						}
						// shove in the pipe
						if (out != null) {
							out.write(str.getBytes());
							out.flush();
						}
					} else {
						// String is empty consider as a noop
						synchronized (cmd) {
							cmd.notifyAll();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Clear the queue and notify any command waiting, we are going down.
		CommandQueue txQueue = session.getTxQueue();
		if (txQueue != null) {
			Command[] cmds = txQueue.clearCommands();
			for (int i = 0; i < cmds.length; i++) {
				synchronized (cmds[i]) {
					cmds[i].notifyAll();
				}
			}
		}
	}
}
