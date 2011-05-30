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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Adeel Asghar
 *
 */
/**
 * Simple thread-safe Queue implementation.
 */
public class Queue {
	
	protected List<Object> list;
	
	public Queue() {
		list = Collections.synchronizedList(new LinkedList<Object>());
	}
	
	public Object removeItem() throws InterruptedException {
		synchronized (list) {
			while (list.isEmpty()) {
				list.wait();
			}

			// extract the new first cmd
			Object item = list.remove(0);
			//if (GDBDebugCorePlugin.DEBUG) System.out.println("in Queue.removeItem() - removed: '" + item + "'");
			return item;
		}
	}

	public void addItem(Object item) {
		synchronized (list) {
			list.add(item);
			//if (GDBDebugCorePlugin.DEBUG) System.out.println("in Queue.addItem - added: '" + item + "'");
			// After adding, notify any and all waiting
			// threads that the list has changed.
			list.notifyAll();
		}
	}

	public Object[] clearItems() {
		Object[] array;
		synchronized (list) {
			array = list.toArray();
			list.clear();
		}
		return array;
	}

	public boolean isEmpty() {
		boolean empty;
		synchronized (list) {
			empty = list.isEmpty();
		}
		return empty;
	}
	
}
