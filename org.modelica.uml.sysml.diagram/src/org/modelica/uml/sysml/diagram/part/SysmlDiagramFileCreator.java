package org.modelica.uml.sysml.diagram.part;

import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.util.IDEEditorFileCreator;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.util.DiagramFileCreator;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

import org.eclipse.core.runtime.IPath;

/**
 * @generated
 */
public class SysmlDiagramFileCreator extends IDEEditorFileCreator {

	/**
	 * @generated
	 */
	private static SysmlDiagramFileCreator INSTANCE = new SysmlDiagramFileCreator();

	/**
	 * @generated
	 */
	public static DiagramFileCreator getInstance() {
		return INSTANCE;
	}

	/**
	 * @generated
	 */
	public String getExtension() {
		return ".mcd"; //$NON-NLS-1$
	}

}