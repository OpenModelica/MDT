package org.modelica.mdt.debug.core.launcher;

public interface IMDTConstants {

	/**
	 * Unique identifier for the MDT debug model (value
	 * <code>org.pelab.rml.debug</code>).
	 */
	public static final String ID_MDT_DEBUG_MODEL = "org.pelab.rml.debug.core";

	/**
	 * Name of the string substitution variable that resolves to the location of
	 * a local Perl executable (value <code>perlExecutable</code>).
	 */
	public static final String ID_PERL_EXECUTABLE = "perlExecutable";

	/**
	 * Launch configuration key. Value is a path to a perl program. The path is
	 * a string representing a full path to a perl program in the workspace.
	 */
	public static final String ATTR_MDT_PROGRAM = ID_MDT_DEBUG_MODEL
			+ ".ATTR_MDT_PROGRAM";

}
