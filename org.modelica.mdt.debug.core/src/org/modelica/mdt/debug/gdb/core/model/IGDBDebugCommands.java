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
package org.modelica.mdt.debug.gdb.core.model;

/**
 * @author Adeel Asghar
 *
 */
public interface IGDBDebugCommands 
{
	/* commands to be send to the debugger */
	static final String CMD_BREAKPOINT 					= "$b";
	static final String CMD_DELETE_BREAKPOINT 			= "$d"; 			
	static final String CMD_FUNCTION_BREAKPOINT 		= "$bm";   		
	static final String CMD_DELETE_FUNCTION_BREAKPOINT 	= "$bmd";	
	static final String CMD_STRING_BREAKPOINT			= "$bs";   			
	static final String CMD_DELETE_STRING_BREAKPOINT	= "$bsd";		
	static final String CMD_CLEAR_BREAKPOINTS			= "$c";
	static final String CMD_STEP						= "$s";
	static final String CMD_STEP_OVER					= "$o";
	static final String CMD_STEP_RETURN					= "$n";	
	static final String CMD_RUN							= "$r";
	static final String CMD_SUSPEND 					= "$u";
	static final String CMD_STACK 						= "$k";
	static final String CMD_DATA_STACK					= "$a";	
	static final String CMD_VARIABLE_VALUE 				= "$v";
	static final String CMD_LAZY_VARIABLE_VALUE 		= "$vl";
	static final String CMD_FRAME_VARIABLE_VALUE 		= "$fv";
	static final String CMD_FRAME_LAZY_VARIABLE_VALUE	= "$fvl";		
	static final String CMD_QUIT						= "$q";
	
	static final String REPLY_OK						= "$ok";
	static final String REPLY_ERROR						= "$err";
	
	/* events that we get/send from/to the debugger */
	static final String EVENT_START						= "$t";	
	static final String EVENT_READY						= "$rdy";
	static final String EVENT_STEP						= "$s";
	static final String EVENT_STEP_OVER					= "$o";
	static final String EVENT_STEP_RETURN				= "$n";
	static final String EVENT_RUN						= "$r";
	static final String EVENT_QUIT						= "$q";
	static final String EVENT_ERROR						= "$err";
	static final String EVENT_SUSPEND					= "$u";
	static final String EVENT_BREAKPOINT				= "$b";	
}
