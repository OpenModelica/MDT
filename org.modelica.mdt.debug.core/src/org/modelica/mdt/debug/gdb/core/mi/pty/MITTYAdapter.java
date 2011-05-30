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
package org.modelica.mdt.debug.gdb.core.mi.pty;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Adeel Asghar
 *
 */
public class MITTYAdapter implements IMITTY {

	PTY fPty;
	
	public MITTYAdapter(PTY pty) {
		fPty = pty;
	}
	
	/* (non-Javadoc)
	 * @see org.modelica.mdt.gdb.debug.core.mi.pty.IMITTY#getSlaveName()
	 */
	@Override
	public String getSlaveName() {
		// TODO Auto-generated method stub
		return fPty.getSlaveName();
	}

	/* (non-Javadoc)
	 * @see org.modelica.mdt.gdb.debug.core.mi.pty.IMITTY#getOutputStream()
	 */
	@Override
	public OutputStream getOutputStream() {
		// TODO Auto-generated method stub
		return fPty.getOutputStream();
	}

	/* (non-Javadoc)
	 * @see org.modelica.mdt.gdb.debug.core.mi.pty.IMITTY#getInputStream()
	 */
	@Override
	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		return fPty.getInputStream();
	}
}
