package org.modelica.mdt.debug.core.launcher;

public interface IMDTConstants 
{

	/**
	 * Unique identifier for the MDT debug model (value
	 * <code>org.modelica.mdt.debug</code>).
	 */
	public static final String ID_MDT_DEBUG_MODEL = "org.modelica.mdt.debug.core";

	/**
	 * Launch configuration key. Value is a path to a perl program. The path is
	 * a string representing a full path to a perl program in the workspace.
	 */
	public static final String ATTR_MDT_PROGRAM = ID_MDT_DEBUG_MODEL + ".ATTR_MDT_PROGRAM";
	public static final String ATTR_MDT_ARGUMENTS = ID_MDT_DEBUG_MODEL + ".ATTR_MDT_ARGUMENTS";
	
	public static final String ATTR_MDT_WORK_DIRECTORY = ID_MDT_DEBUG_MODEL + ".ATTR_MDT_WORK_DIRECTORY";
	public static final String ATTR_MDT_GDB_PATH = ID_MDT_DEBUG_MODEL + ".ATTR_MDT_GDB_PATH";
}
