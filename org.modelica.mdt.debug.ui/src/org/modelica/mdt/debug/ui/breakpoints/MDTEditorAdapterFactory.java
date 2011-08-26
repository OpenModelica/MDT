package org.modelica.mdt.debug.ui.breakpoints;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.ui.actions.IRunToLineTarget;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.ui.texteditor.ITextEditor;
import org.modelica.mdt.breakpoint.MDTBreakpointAdapter;
import org.modelica.mdt.breakpoint.MDTRunToLineAdapter;
import org.modelica.mdt.ui.editor.ModelicaEditor;

/**
 * Creates a toggle breakpoint adapter
 */
public class MDTEditorAdapterFactory implements IAdapterFactory {
	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof ModelicaEditor) {
			ITextEditor editorPart = (ITextEditor) adaptableObject;
			IResource resource = (IResource) editorPart.getEditorInput().getAdapter(IResource.class);
			if (resource != null) {
				String extension = resource.getFileExtension();
				if (extension != null && extension.equals("mo")) {
				    if (adapterType.equals(IToggleBreakpointsTarget.class)) {
				        return new MDTBreakpointAdapter();
				    }
					if (adapterType.equals(IRunToLineTarget.class)) {
				        return new MDTRunToLineAdapter();
				    }
				}
			}			
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	@SuppressWarnings("rawtypes")
	public Class[] getAdapterList() {
		return new Class[]{IToggleBreakpointsTarget.class};
	}
}
