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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.modelica.mdt.debug.core.MDTDebugCorePlugin;


/**
 * Receiving, and printing to the console, stderr output
 * 
 * @author Adeel Asghar
 *
 */
public class ErrorThread extends Thread {
	MISession session;
	
	public ErrorThread(MISession s) {
		super("MI Error Thread");
		session = s;
	}
	
	/**
	 * Sit on the error stream output, and append to the GDB console
	 */
	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(session.getChannelErrorStream()));
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				OutputStream console = session.getLogPipe();
				if (MDTDebugCorePlugin.DEBUG) session.writeLog("MI Error Thread :: " + line);
				if (console != null) {
					console.write((line + "\n").getBytes());
					console.flush();
				}
			}
		} catch (IOException e) {
			try {
				reader.close();
			} catch (IOException e1) {/* closing anyway */}
		}
	}
}
