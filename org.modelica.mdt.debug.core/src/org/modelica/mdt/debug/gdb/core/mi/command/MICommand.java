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
package org.modelica.mdt.debug.gdb.core.mi.command;

/**
 * @author Adeel Asghar
 *
 */
/**
 * 
 * Represents a MI command.
 */
public class MICommand extends Command {

	final static String[] empty = new String[0];
	String[] fOptions = empty;
	String[] fParameters = empty;
	String fOperation = new String();

	public MICommand(String oper) {
		this(oper, empty);
	}

	public MICommand(String oper, String[] params) {
		this(oper, empty, params);
	}

	public MICommand(String oper, String[] opt, String[] params) {
		fOperation = oper;
		fOptions = opt;
		fParameters = params;
	}

	/**
	 * Returns the operation of this command.
	 * 
	 * @return the operation of this command
	 */
	public String getOperation() {
		return fOperation;
	}

	protected void setOperation(String op) {
		fOperation = op; 
	}

	/**
	 * Returns an array of command's options. An empty collection is 
	 * returned if there are no options.
	 * 
	 * @return an array of command's options
	 */
	public String[] getOptions() {
		return fOptions;
	}

	public void setOptions(String[] opt) {
		fOptions = opt;
	}

	/**
	 * Returns an array of command's parameters. An empty collection is 
	 * returned if there are no parameters.
	 * 
	 * @return an array of command's parameters
	 */
	public String[] getParameters() {
		return fParameters;
	}

	public void setParameters(String[] p) {
		fParameters = p;
	}

	protected String optionsToString() {
		String[] options = getOptions();
		StringBuffer sb = new StringBuffer();
		if (options != null && options.length > 0) {
			for (int i = 0; i < options.length; i++) {
				String option = options[i];
				// If the option argument contains " or \ it must be escaped
				if (option.indexOf('"') != -1 || option.indexOf('\\') != -1) {
					StringBuffer buf = new StringBuffer();
					for (int j = 0; j < option.length(); j++) {
						char c = option.charAt(j);
						if (c == '"' || c == '\\') {
							buf.append('\\');
						}
						buf.append(c);
					}
					option = buf.toString();
				}

				// If the option contains a space according to
				// GDB/MI spec we must surround it with double quotes.
				if (option.indexOf('\t') != -1 || option.indexOf(' ') != -1) {
					sb.append(' ').append('"').append(option).append('"');
				} else {
					sb.append(' ').append(option);
				}
			}
		}
		return sb.toString().trim();
	}

	protected String parametersToString() {
		String[] parameters = getParameters();
		String[] options = getOptions();
		StringBuffer buffer = new StringBuffer();
		if (parameters != null && parameters.length > 0) {
			// According to GDB/MI spec
			// Add a "--" separator if any parameters start with "-"
			if (options != null && options.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					if (parameters[i].startsWith("-")) { //$NON-NLS-1$
						buffer.append('-').append('-');
						break;
					}
				}
			}

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < parameters.length; i++) {
				// We need to escape the double quotes and the backslash.
				sb.setLength(0);
				String param = parameters[i];
				for (int j = 0; j < param.length(); j++) {
					char c = param.charAt(j);
					if (c == '"' || c == '\\') {
						sb.append('\\');
					}
					sb.append(c);
				}

				// If the string contains spaces instead of escaping
				// surround the parameter with double quotes.
				if (containsWhitespace(param)) {
					sb.insert(0, '"');
					sb.append('"');
				}
				buffer.append(' ').append(sb);
			}
		}
		return buffer.toString().trim();
	}

	public String toString() {
		StringBuffer command = new StringBuffer(getToken() + getOperation());
		String opt = optionsToString();
		if (opt.length() > 0) {
			command.append(' ').append(opt);
		}
		String p = parametersToString();
		if (p.length() > 0) {
			command.append(' ').append(p);
		}
		command.append('\n');
		return command.toString();
	}

	protected boolean containsWhitespace(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (Character.isWhitespace(s.charAt(i))) {
				return true;
			}
		}
		return false;
	}
}
