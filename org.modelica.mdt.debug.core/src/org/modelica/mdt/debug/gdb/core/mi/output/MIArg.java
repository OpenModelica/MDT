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
package org.modelica.mdt.debug.gdb.core.mi.output;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adeel Asghar
 *
 */
/**
 * Represents a set name=value.
 */
public class MIArg {

	String name;
	String value;
	String type;

	public MIArg(String name, String value, String type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
	
	public String getType() {
		return type;
	}

	/**
	 * Parsing a MIList of the form:
	 * [{name="xxx",value="yyy"},{name="xxx",value="yyy"},..]
	 * [name="xxx",name="xxx",..]
	 * [{name="xxx"},{name="xxx"}]
	 */
	public static MIArg[] getMIArgs(MIList miList) {
		List<MIArg> aList = new ArrayList<MIArg>();
		MIValue[] values = miList.getMIValues();
		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof MITuple) {
				MIArg arg = getMIArg((MITuple)values[i]);
				if (arg != null) {
					aList.add(arg);
				}
			}
		}
		MIResult[] results = miList.getMIResults();
		for (int i = 0; i < results.length; i++) {
			MIValue value = results[i].getMIValue();
			if (value instanceof MIConst) {
				String str = ((MIConst)value).getCString();
				aList.add(new MIArg(str, "", ""));
			}
		}
		return ((MIArg[])aList.toArray(new MIArg[aList.size()]));
	}

	/**
	 * Parsing a MITuple of the form:
	 * {{name="xxx",value="yyy"},{name="xxx",value="yyy"},..}
	 * {name="xxx",name="xxx",..}
	 * {{name="xxx"},{name="xxx"}}
	 */
	public static MIArg[] getMIArgs(MITuple miTuple) {
		List<MIArg> aList = new ArrayList<MIArg>();
		MIValue[] values = miTuple.getMIValues();
		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof MITuple) {
				MIArg arg = getMIArg((MITuple)values[i]);
				if (arg != null) {
					aList.add(arg);
				}
			}
		}
		MIResult[] results = miTuple.getMIResults();
		for (int i = 0; i < results.length; i++) {
			MIValue value = results[i].getMIValue();
			if (value instanceof MIConst) {
				String str = ((MIConst)value).getCString();
				aList.add(new MIArg(str, "", ""));
			}
		}
		return ((MIArg[])aList.toArray(new MIArg[aList.size()]));
	}
	/**
	 * Parsing a MITuple of the form:
	 * {name="xxx",value="yyy"}
	 * {name="xxx"}
	 */
	public static MIArg getMIArg(MITuple tuple) {
		MIResult[] args = tuple.getMIResults();
		MIArg arg = null;
		if (args.length > 0) {
			// Name
			String aName = "";
			MIValue value = args[0].getMIValue();
			if (value != null && value instanceof MIConst) {
				aName = ((MIConst)value).getCString();
			} else {
				aName = "";
			}

			// Value/Type
			String aValue = "";
			String aType = "";
			if (args.length > 1) {
				value = args[1].getMIValue();
				if (args[1].getVariable().equals("value")) {
					if (value != null && value instanceof MIConst) {
						aValue = ((MIConst)value).getCString();
					} else {
						aValue = "";
					}
				} else if (args[1].getVariable().equals("type")) {
					if (value != null && value instanceof MIConst) {
						aType = ((MIConst)value).getCString();
					} else {
						aType = "";
					}
				}
			}
			
			// Value/Type
			if (args.length > 2) {
				value = args[2].getMIValue();
				if (args[2].getVariable().equals("value")) {
					if (value != null && value instanceof MIConst) {
						aValue = ((MIConst)value).getCString();
					} else {
						aValue = "";
					}
				} else if (args[2].getVariable().equals("type")) {
					if (value != null && value instanceof MIConst) {
						aType = ((MIConst)value).getCString();
					} else {
						aType = "";
					}
				}
			}

			arg = new MIArg(aName, aValue, aType);
		}
		return arg;
	}

	public String toString() {
		return name + "=" + value;
	}
}
