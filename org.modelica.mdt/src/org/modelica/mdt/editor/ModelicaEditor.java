/*
 * Created on Jan 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.modelica.mdt.editor;

import org.eclipse.ui.editors.text.TextEditor;

/**
 * @author Peter Bunus
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ModelicaEditor extends TextEditor {

	public ModelicaEditor()
	{
		super();
		setSourceViewerConfiguration(new ModelicaSourceViewerConfig());
	}
}
