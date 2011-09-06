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

/**
 * @author Adeel Asghar
 *
 */
/**
 * GDB/MI Frame tuple parsing.
 */
public class MIFrame {

	int level;
	String addr;
	String func = "";
	String file = "";
	// since gdb 6.4
	String fullname = "";
	int line;
	MIArg[] args = new MIArg[0];

	public MIFrame(MITuple tuple) {
		parse(tuple);
	}

	public MIArg[] getArgs() {
		return args;
	}

	public String getFile() {
		String fname = getFullname();
		return ( fname.length() != 0 ) ? fname : file;
	}

	public String getFullname() {
		return fullname;
	}

	public String getFunction() {
		return func;
	}

	public int getLine() {
		return line;
	}

	public String getAddress() {
		return addr;
	}

	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("level=\"" + level + "\"");
		buffer.append(",addr=\"" + addr + "\"");
		buffer.append(",func=\"" + func + "\"");
		buffer.append(",file=\"" + file + "\"");
		buffer.append(",line=\"").append(line).append('"');
		buffer.append(",args=[");
		for (int i = 0; i < args.length; i++) {
			if (i != 0) {
				buffer.append(',');
			}
			buffer.append("{name=\"" + args[i].getName() + "\"");
			buffer.append(",value=\"" + args[i].getValue() + "\"}");
		}
		buffer.append(']');
		return buffer.toString();
	}

	void parse(MITuple tuple) {
		MIResult[] results = tuple.getMIResults();
		for (int i = 0; i < results.length; i++) {
			String var = results[i].getVariable();
			MIValue value = results[i].getMIValue();
			String str = "";
			if (value != null && value instanceof MIConst) {
				str = ((MIConst)value).getCString();
			}

			if (var.equals("level")) {
				try {
					level = Integer.parseInt(str.trim());
				} catch (NumberFormatException e) {
				}
			} else if (var.equals("addr")) {
				try {
					addr = str.trim();
				} catch (NumberFormatException e) {
				}
			} else if (var.equals("func")) {
				func = null;
				if ( str != null ) {
					str = str.trim();
					if ( str.equals( "??" ) )
						func = "";
					else {
						func = str;
						// In some situations gdb returns the function names that include parameter types.
						// To make the presentation consistent truncate the parameters. PR 46592
						// However PR180059: only cut it if it is last brackets represent parameters,
						// because gdb can return: func="(anonymous namespace)::func2((anonymous namespace)::Test*)"
						int closing = str.lastIndexOf(')');
						if (closing == str.length() - 1) {
							int end = getMatchingBracketIndex(str, closing - 1);
							if (end >= 0) {
								func = str.substring(0, end);
							}
						}
					}
				}
			} else if (var.equals("file")) {
				file = str;
			} else if (var.equals("fullname")) {
				fullname = str;
			} else if (var.equals("line")) {
				try {
					line = Integer.parseInt(str.trim());
				} catch (NumberFormatException e) {
				}
			} else if (var.equals("args")) {
				if (value instanceof MIList) {
					args = MIArg.getMIArgs((MIList)value);
				} else if (value instanceof MITuple) {
					args = MIArg.getMIArgs((MITuple)value);
				}
			}
		}
	}

	private int getMatchingBracketIndex(String str, int end) {
	    int depth = 1;
	    for (;end>=0;end--) {
	    	int c = str.charAt(end);
	    	if (c=='(') {
	    		depth--;
	    		if (depth==0) break;
	    	} else if (c==')') 
	    		depth++;
	    }
	    return end;
    }
}
