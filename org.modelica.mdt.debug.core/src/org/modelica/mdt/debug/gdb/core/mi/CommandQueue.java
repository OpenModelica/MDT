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

import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.gdb.core.mi.command.Command;

/**
 * @author Adeel Asghar
 *
 */
/**
 * Simple thread-safe Queue implemetation.
 */
public class CommandQueue extends Queue {
	
	public CommandQueue() {
		super();
	}

	public Command removeCommand(int id) {
		//print("in removeCommand(" + id + ") - entering");
		synchronized (list) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				Command cmd = (Command)list.get(i);
				int token = cmd.getToken();
				if (token == id) {
					list.remove(cmd);
					return cmd;
				} 
			}
		}
		return null;
	}

	public Command removeCommand() throws InterruptedException {
		return (Command)removeItem();
	}

	public void addCommand(Command cmd) {
		if (MDTDebugCorePlugin.DEBUG) System.out.println("in CommandQueue.addCommand() - entering");
		addItem(cmd);
	}

	public Command[] clearCommands() {
		Object[] objs = clearItems();
		Command[] cmds = new Command[objs.length];
		System.arraycopy(objs, 0, cmds, 0, objs.length);
		return cmds;
	}
}
