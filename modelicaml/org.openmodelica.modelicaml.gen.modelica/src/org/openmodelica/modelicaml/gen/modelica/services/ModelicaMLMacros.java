/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.gen.modelica.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelicaMLMacros.
 */
public class ModelicaMLMacros {
	
	/**
	 * Expand triggers macros.
	 * 
	 * @param trigger_code
	 *            the trigger_code
	 * @param state_stime_path
	 *            the state_stime_path
	 * @return the string
	 */
	public String expandTriggersMacros(String trigger_code, String state_stime_path) {
		String guard1 = null;
		guard1 = expandLOCAL_TIMER_PATH_PREFIXMacro(trigger_code, state_stime_path);
		return guard1;
	}
	
	/**
	 * Expand loca l_ time r_ pat h_ prefix macro.
	 * 
	 * @param trigger_code
	 *            the trigger_code
	 * @param state_stime_path
	 *            the state_stime_path
	 * @return the string
	 */
	public String expandLOCAL_TIMER_PATH_PREFIXMacro(String trigger_code, String state_stime_path) {
		String new_trigger_code = null;
		new_trigger_code = trigger_code.replaceAll("LOCAL_TIMER_PATH_PREFIX", state_stime_path.replaceAll("\\.", "_"));
		return new_trigger_code;
	}
	
	/**
	 * Expand guard macros.
	 * 
	 * @param guard
	 *            the guard
	 * @param state_stime_path
	 *            the state_stime_path
	 * @return the string
	 */
	public String expandGuardMacros(String guard, String state_stime_path) {
		String guard1 = null, guard2 = null, guard3 = null, guard4 = null, guard5 = null, guard6 = null;
		guard1 = expandAFTERMacro(guard, state_stime_path);
		guard2 = expandSIGNALMacro(guard1);
		guard3 = expandCHANGEMacro(guard2);
		guard4 = expandSTIMEMacro(guard3, state_stime_path);
		guard5 = expandABSOLUTE_VALUE_OFMacro(guard4);
		guard6 = expandEVENTMacro(guard5);
		return guard6;
	}

	/**
	 * Expand after macro.
	 * 
	 * @param guard
	 *            the guard
	 * @param state_stime_path
	 *            the state_stime_path
	 * @return the string
	 */
	public String expandAFTERMacro(String guard, String state_stime_path) {
		// old pattern only for AFTER(numbers)
		// Pattern afterPattern = Pattern.compile("AFTER+\\(+[0-9]+\\)");
		// Pattern numberPattern = Pattern.compile("[0-9]+");

		Pattern macroPattern = Pattern.compile("AFTER+\\(+.+\\)");
		Pattern expPattern = Pattern.compile("\\(+.+\\)");

		Matcher macroString = macroPattern.matcher(guard);
		String newGuard = guard;

		if (macroString.find()) {
			// System.out.println("Macro string: " + macroString.group(0));
			Matcher expString = expPattern.matcher(macroString.group(0));
			expString.find();
			// System.out.println("Number: " + numberString.group(0));

			return newGuard.replace(macroString.group(0), "("
					+ state_stime_path + " > " + expString.group(0) + ")");
		} else {
			// System.out.println("Cannot find an AFTER macro.");
			return guard;
		}
	}
	
//	public String expandSIGNALMacro(String guard) {
//		Pattern macroPattern = Pattern.compile("SIGNAL+\\(+.+\\)");
//		Pattern varPattern = Pattern.compile("\\(+.+\\)");
//
//		Matcher macroString = macroPattern.matcher(guard);
//		String newGuard = guard;
//
//		if (macroString.find()) {
//			// System.out.println("Macro string: " + macroString.group(0));
//			Matcher varString = varPattern.matcher(macroString.group(0));
//			varString.find();
//			// System.out.println("Number: " + numberString.group(0));
//			
//			return newGuard.replace(macroString.group(0), "change(" + varString.group(0) + ".counter)");
//		} else {
//			// System.out.println("Cannot find an AFTER macro.");
//			return guard;
//		}
//	}

	/**
 * Expand signal macro.
 * 
 * @param code
 *            the code
 * @return the string
 */
public String expandSIGNALMacro(String code) {
		Pattern GEN_CHANGEPattern = Pattern.compile("SIGNAL+\\(+.+\\)");
		Pattern expPattern = Pattern.compile("\\(+.+\\)");

		Matcher macroString = GEN_CHANGEPattern.matcher(code);
		String newCode = code;

		while (macroString.find()) {
			Matcher expString = expPattern.matcher(macroString.group(0));
			if (expString.find()) {
				String varString = expString.group(0).replace("(", "").replace(")", "");
				//newCode = newCode.replace(macroString.group(0), "change(" + varString + ".counter)"); //OLD
				newCode = newCode.replace(macroString.group(0), varString + ".counter" + " <> pre(" + varString + ".counter)");
			}
		}
		return newCode;
	}
	
	
//	public String expandCHANGEMacro(String guard) {
//		String new_guard = null;
//		new_guard = guard.replaceAll("CHANGE\\(", "change\\(");
//		return new_guard;
//	}
	
//	/**
//	 * @deprecated
//	 * @param guard
//	 * @return
//	 */
//	public String expandEVENTMacro(String guard) {
//		String new_guard = null;
//		new_guard = guard.replaceAll("EVENT\\(", "change\\(");
//		return new_guard;
//	}

	
	/**
 * Expand change macro.
 * 
 * @param guard
 *            the guard
 * @return the string
 */
public String expandCHANGEMacro(String guard) {
		Pattern GEN_CHANGEPattern = Pattern.compile("CHANGE+\\(+.+\\)");
		Pattern expPattern = Pattern.compile("\\(+.+\\)");

		Matcher macroString = GEN_CHANGEPattern.matcher(guard);
		String newGuard = guard;

		while (macroString.find()) {
			Matcher expString = expPattern.matcher(macroString.group(0));
			if (expString.find()) {
				String varString = expString.group(0).replace("(", "").replace(")", "");
				newGuard = newGuard.replace(macroString.group(0), varString + " <> pre("
						+ varString + ")");
			}
		}
		return newGuard;
	}
	
	/**
	 * Expand event macro.
	 * 
	 * @param guard
	 *            the guard
	 * @return the string
	 * @deprecated
	 */
	public String expandEVENTMacro(String guard) {
		Pattern GEN_CHANGEPattern = Pattern.compile("EVENT+\\(+.+\\)");
		Pattern expPattern = Pattern.compile("\\(+.+\\)");

		Matcher macroString = GEN_CHANGEPattern.matcher(guard);
		String newGuard = guard;

		while (macroString.find()) {
			Matcher expString = expPattern.matcher(macroString.group(0));
			if (expString.find()) {
				String varString = expString.group(0).replace("(", "").replace(")", "");
				newGuard = newGuard.replace(macroString.group(0), varString + " <> pre("
						+ varString + ")");
			}
		}
		return newGuard;
	}

	/**
	 * Expand stime macro.
	 * 
	 * @param guard
	 *            the guard
	 * @param state_stime_path
	 *            the state_stime_path
	 * @return the string
	 */
	public String expandSTIMEMacro(String guard, String state_stime_path) {
		String new_guard = null;
		new_guard = guard.replaceAll("STIME", state_stime_path);
		return new_guard;
	}


	/**
	 * Expand absolut e_ valu e_ of macro.
	 * 
	 * @param guard
	 *            the guard
	 * @return the string
	 */
	public String expandABSOLUTE_VALUE_OFMacro(String guard) {
		Pattern absPattern = Pattern.compile("\\x7C.[^\\x7C]+\\x7C");
		Pattern expPattern = Pattern.compile("[^\\x7C].[^\\x7C]+");

		Matcher macroString = absPattern.matcher(guard);
		String newGuard = guard;

		while (macroString.find()) {
			// System.out.println("found: " + macroString.group(0));
			Matcher expString = expPattern.matcher(macroString.group(0));
			if (expString.find()) {
				// System.out.println("old string: " + macroString.group(0));
				// System.out.println("new string: " + "abs(" +
				// expString.group(0) + ")");
				newGuard = newGuard.replace(macroString.group(0), "abs("
						+ expString.group(0) + ")");
			}
			// System.out.println(newGuard+"\n");
		}
		return newGuard;
	}

	/**
	 * Expand gen code macros.
	 * 
	 * @param code
	 *            the code
	 * @return the string
	 */
	public String expandGENCodeMacros(String code) {
		String code1 = null, code2 = null, code3 = null;
		code1 = expandGEN_CHANGEMacro(code);
		code2 = expandGEN_SIGNALMacro(code1);
		code3 = expandGEN_INCMacro(code2);
		//code3 = expandGEN_EVENTMacro(code2);
		return code3;
	}

	/**
	 * Expand ge n_ event macro.
	 * 
	 * @param code
	 *            the code
	 * @return the string
	 * @deprecated
	 */
	public String expandGEN_EVENTMacro(String code) {
		Pattern GEN_EVENTPattern = Pattern.compile("GEN_EVENT+\\(+.+\\)");
		Pattern pathPattern = Pattern.compile("\\(+.+\\)");
		Matcher macroString = GEN_EVENTPattern.matcher(code);
		String newCode = code;

		if (macroString.find()) {
			// System.out.println("Macro string: " + macroString.group(0));

			Matcher pathString = pathPattern.matcher(macroString.group(0));
			pathString.find();

			String newPathString;
			newPathString = pathString.group(0).replace("(", "").replace(")",
					"");

			// System.out.println("Path: " + newPathString);

			return newCode.replace(macroString.group(0), newPathString
					+ " := not " + newPathString);
		} else {
			// System.out.println("Cannot find an after macro for" + code);
			return code;
		}
	}

//	public String expandGEN_SIGNALMacro(String code) {
//		Pattern GEN_SIGNALTPattern = Pattern.compile("GEN_SIGNAL+\\(+.+\\)");
//		Pattern pathPattern = Pattern.compile("\\(+.+\\)");
//		Matcher macroString = GEN_SIGNALTPattern.matcher(code);
//		String newCode = code;
//
//		if (macroString.find()) {
//			// System.out.println("Macro string: " + macroString.group(0));
//
//			Matcher pathString = pathPattern.matcher(macroString.group(0));
//			pathString.find();
//
//			String newPathString;
//			newPathString = pathString.group(0).replace("(", "").replace(")",
//					"");
//
//			// System.out.println("Path: " + newPathString);
//
//			return newCode.replace(macroString.group(0), newPathString + ".occurrence := true"); 
//			//return newCode.replace(macroString.group(0), newPathString + ".occurrence := true; " + newPathString + ".lastSent := time"); // TEST: A SignalEvent is consumed only after time has advanced.
//		} else {
//			// System.out.println("Cannot find an after macro for" + code);
//			return code;
//		}
//	}

	/**
 * Expand ge n_ signal macro.
 * 
 * @param code
 *            the code
 * @return the string
 */
public String expandGEN_SIGNALMacro(String code) {
		Pattern GEN_CHANGEPattern = Pattern.compile("GEN_SIGNAL+\\(+.+\\)");
		Pattern expPattern = Pattern.compile("\\(+.+\\)");

		Matcher macroString = GEN_CHANGEPattern.matcher(code);
		String newCode = code;

		while (macroString.find()) {
			Matcher expString = expPattern.matcher(macroString.group(0));
			if (expString.find()) {
				String varString = expString.group(0).replace("(", "").replace(")", "");
				return newCode.replace(macroString.group(0), varString + ".occurrence := true"); 
				//newCode = newCode.replace(macroString.group(0), varString + ".counter := pre(" +varString + ".counter" + ") + 1");
			}
		}
		return newCode;
	}
	

	/**
	 * Expand ge n_ inc macro.
	 * 
	 * @param code
	 *            the code
	 * @return the string
	 */
	public String expandGEN_INCMacro(String code) {
		Pattern GEN_CHANGEPattern = Pattern.compile("GEN_INC+\\(+.+\\)");
		Pattern expPattern = Pattern.compile("\\(+.+\\)");

		Matcher macroString = GEN_CHANGEPattern.matcher(code);
		String newCode = code;

		while (macroString.find()) {
			Matcher expString = expPattern.matcher(macroString.group(0));
			if (expString.find()) {
				String varString = expString.group(0).replace("(", "").replace(")", "");
				newCode = newCode.replace(macroString.group(0), varString + " := pre(" + varString + ") + 1");
			}
		}
		return newCode;
	}
	
	/**
	 * Expand ge n_ change macro.
	 * 
	 * @param guard
	 *            the guard
	 * @return the string
	 */
	public String expandGEN_CHANGEMacro(String guard) {
		Pattern GEN_CHANGEPattern = Pattern.compile("GEN_CHANGE+\\(+.+\\)");
		Pattern expPattern = Pattern.compile("\\(+.+\\)");

		Matcher macroString = GEN_CHANGEPattern.matcher(guard);
		String newGuard = guard;

		while (macroString.find()) {
			// System.out.println("found: " + macroString.group(0));
			Matcher expString = expPattern.matcher(macroString.group(0));
			if (expString.find()) {
				// System.out.println("old string: " + macroString.group(0));
				// System.out.println("new string: " + "abs(" +
				// expString.group(0) + ")");
				
				String varString = expString.group(0).replace("(", "").replace(")", "");
				newGuard = newGuard.replace(macroString.group(0), varString + " := not "
						+ varString);
			}
			// System.out.println(newGuard+"\n");
		}
		return newGuard;
	}
	
	
	
//	public String expandGEN_CHANGEMacro(String code) {
//		Pattern GEN_CHANGETPattern = Pattern.compile("GEN_CHANGE+\\(+.+\\)");
//		Pattern pathPattern = Pattern.compile("\\(+.+\\)");
//		Matcher macroString = GEN_CHANGETPattern.matcher(code);
//		String newCode = code;
//
//		
//		
//		if (macroString.find()) {
//			// System.out.println("Macro string: " + macroString.group(0));
//
//			Matcher pathString = pathPattern.matcher(macroString.group(0));
//			pathString.find();
//
//			String newPathString;
//			newPathString = pathString.group(0).replace("(", "").replace(")",
//					"");
//
//			// System.out.println("Path: " + newPathString);
//
//			return newCode.replace(macroString.group(0), newPathString
//					+ " := not " + newPathString);
//		} else {
//			// System.out.println("Cannot find an after macro for" + code);
//			return code;
//		}
//	}

}
