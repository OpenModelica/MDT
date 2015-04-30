package org.modelica.mdt.ui.editor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;


public class SusanJumpToDeclaration extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		//jump to declaration of the marked function, if it exists
		if (! (HandlerUtil.getActiveEditor(event) instanceof SusanEditor) )
			return null;
		SusanEditor editor = (SusanEditor) HandlerUtil.getActiveEditor(event);
		editor.findTemplateDeclaration();

		return null;
	}
	
}