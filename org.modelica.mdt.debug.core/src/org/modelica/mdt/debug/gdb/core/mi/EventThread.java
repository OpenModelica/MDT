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
import org.modelica.mdt.debug.gdb.core.mi.event.MIEvent;

/**
 * Event Thread blocks on the event Queue, wakes up
 * when events are available and notify all the observers.
 * 
 * @author Adeel Asghar
 *
 */
public class EventThread extends Thread {
	MISession session;

	public EventThread(MISession s) {
		super("MI Event Thread");
		session = s;
	}

	/**
	 * Sit on the output stream, read the events and dispatch them to the observers.
	 */
	public void run() {
		// Signal by the session of time to die.
		while (session.getChannelOutputStream() != null) {
			MIEvent event = null;
			Queue eventQueue = session.getEventQueue();
			// removeItem() will block until an item is available.
			try {
				event = (MIEvent) eventQueue.removeItem();
				if (MDTDebugCorePlugin.DEBUG) session.writeLog("MI Event Thread :: " + event.toString());
			} catch (InterruptedException e) {
				//MDTDebugCorePlugin.log(null, e);
			}
			try {
				if (event != null) {
					if (event.propagate())
						session.notifyObservers(event);
				}
			} catch (Exception e) {
				MDTDebugCorePlugin.log(null, e);
			}
		}
	}
}
