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
package org.modelica.mdt.debug.gdb.core.model.variable;

/**
 * A helper class to temporary store variables retrieved from GDB
 * @author Adeel Asghar
 *
 */
public class Variable {
	
	private String fName;
	private String fDisplayName;
	private String fVoidPointer;
	
	/**
	 * 
	 */
	public Variable(String name, String displayName, String voidPointer) {
		// TODO Auto-generated constructor stub
		setName(name);
		setDisplayName(displayName);
		setVoidPointer(voidPointer);
	}
	
	/**
	 * @param name the fName to set
	 */
	public void setName(String name) {
		this.fName = name;
	}
	
	/**
	 * @return the fName
	 */
	public String getName() {
		return fName;
	}
	
	/**
	 * @param displayName the fDisplayName to set
	 */
	public void setDisplayName(String displayName) {
		this.fDisplayName = displayName;
	}
	
	/**
	 * @return the fDisplayName
	 */
	public String getDisplayName() {
		return fDisplayName;
	}
	
	/**
	 * @param voidPointer the fVoidPointer to set
	 */
	public void setVoidPointer(String voidPointer) {
		this.fVoidPointer = voidPointer;
	}
	
	/**
	 * @return the fVoidPointer
	 */
	public String getVoidPointer() {
		return fVoidPointer;
	}
	
}
