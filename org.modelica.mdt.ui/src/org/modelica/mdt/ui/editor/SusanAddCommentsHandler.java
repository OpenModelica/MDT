package org.modelica.mdt.ui.editor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.handlers.HandlerUtil;


public class SusanAddCommentsHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		
		if (! (HandlerUtil.getActiveEditor(event) instanceof SusanEditor) )
			return null;
		SusanEditor editor = (SusanEditor) HandlerUtil.getActiveEditor(event);

		try
		{
			editor.commentSection();
		} catch (BadLocationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
}