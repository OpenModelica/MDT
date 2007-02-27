
package org.modelica.mdt.ui.hover;

import org.eclipse.osgi.util.NLS;

/**
 * Helper class to get NLSed messages.
 */
final class ModelicaHoverMessages extends NLS {

	private static final String BUNDLE_NAME= ModelicaHoverMessages.class.getName();

	private ModelicaHoverMessages() {
		// Do not instantiate
	}

	public static String ModelicaHover_noAttachedInformation;
	public static String ModelicaTextHover_createTextHover;
	public static String ModelicaTextHover_makeStickyHint;
	public static String NoBreakpointAnnotation_addBreakpoint;
	public static String NLSStringHover_NLSStringHover_missingKeyWarning;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ModelicaHoverMessages.class);
	}
}
