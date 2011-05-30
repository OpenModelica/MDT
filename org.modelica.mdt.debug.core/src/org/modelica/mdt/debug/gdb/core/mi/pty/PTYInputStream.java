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

import java.io.IOException;
import java.io.InputStream;

import org.modelica.mdt.debug.gdb.core.mi.pty.PTY.MasterFD;

/**
 * @author Adeel Asghar
 *
 */
public class PTYInputStream extends InputStream {

	MasterFD master;

	/**
	 * From a Unix valid file descriptor set a Reader.
	 * @param fd file descriptor.
	 */
	public PTYInputStream(MasterFD fd) {
		master = fd;
	}

	/**
	 * Implementation of read for the InputStream.
	 *
	 * @exception IOException on error.
	 */
	@Override
	public int read() throws IOException {
		byte b[] = new byte[1];
		if (1 != read(b, 0, 1))
			return -1;
		return b[0];
	}

	/**
	 * @see InputStream#read(byte[], int, int)
	 */
	@Override
	public int read(byte[] buf, int off, int len) throws IOException {
		if (buf == null) {
			throw new NullPointerException();
		} else if ((off < 0) || (off > buf.length)
					|| (len < 0) || ((off + len) > buf.length)
					|| ((off + len) < 0)) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return 0;
		}
		byte[] tmpBuf = new byte[len];

		len = read0(master.getFD(), tmpBuf, len);
		if (len <= 0)
			return -1;

		System.arraycopy(tmpBuf, 0, buf, off, len);
		return len;
	}

	/**
	 * Close the Reader
	 * @exception IOException on error.
	 */
	@Override
	public void close() throws IOException {
		if (master.getFD() == -1)
			return;
		close0(master.getFD());
		master.setFD(-1);
	}

	private native int read0(int fd, byte[] buf, int len) throws IOException;
	private native int close0(int fd) throws IOException;

	static {
		System.loadLibrary("pty");
	}
}
