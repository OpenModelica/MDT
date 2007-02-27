
package org.modelica.mdt.ui.editor;
import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

/**
 * Helper class to get NLSed messages.
 */
final class ModelicaEditorMessages extends NLS {

	private static final String BUNDLE_FOR_CONSTRUCTED_KEYS= "org.modelica.mdt.ui.editor.ModelicaEditorMessages";//$NON-NLS-1$
	private static ResourceBundle fgBundleForConstructedKeys= ResourceBundle.getBundle(BUNDLE_FOR_CONSTRUCTED_KEYS);

	/**
	 * Returns the message bundle which contains constructed keys.
	 *
	 * @since 3.1
	 * @return the message bundle
	 */
	public static ResourceBundle getBundleForConstructedKeys() {
		return fgBundleForConstructedKeys;
	}

	private static final String BUNDLE_NAME= ModelicaEditorMessages.class.getName();


	private ModelicaEditorMessages() {
		// Do not instantiate
	}

	public static String ShowModelicaDoc_label;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ModelicaEditorMessages.class);
	}
}
