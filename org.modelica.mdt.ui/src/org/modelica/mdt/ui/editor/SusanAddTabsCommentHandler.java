package org.modelica.mdt.ui.editor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.handlers.HandlerUtil;


public class SusanAddTabsCommentHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		if (! (HandlerUtil.getActiveEditor(event) instanceof SusanEditor) )
			return null;
		
		SusanEditor editor = (SusanEditor) HandlerUtil.getActiveEditor(event);
		try
		{
			editor.tabSection();
		} catch (BadLocationException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
}