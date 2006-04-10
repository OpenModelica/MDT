/*
 * (c) Copyright MDT Team, PELAB, 2006
 */

package org.modelica.mdt.ui.editor;

import java.util.ResourceBundle;


import org.eclipse.ui.texteditor.TextEditorAction;
import org.modelica.mdt.internal.core.ErrorManager;

/**
 * Class that defines the action correcting the indentation of a Modelica file
 */

public class ModelicaCorrectIndentationAction extends TextEditorAction
{

	private static final String ACTIONS_RESOURCE_BUNDLE = "org.modelica.mdt.ui.editor.ModelicaActions";

	private static 
	ModelicaCorrectIndentationAction instance = new ModelicaCorrectIndentationAction();

	/**
	 * Constructs and updates the action.
	 */
	private ModelicaCorrectIndentationAction() {
		/* Editor will be set later that's why we have null here */
		super(ResourceBundle.getBundle(ACTIONS_RESOURCE_BUNDLE),
		"ModelicaCorrectIndentationAction.", null);
		update();
	}

	public static ModelicaCorrectIndentationAction getInstance() {
		return instance;
	}

	/**
	 * Code called when the action is fired.
	 */
	public void run() 
	{
		try
		{
			/* Correct Indentation Here */
			System.err.println("Correct Indentation called");
		}
		catch (Exception e) 
		{
			ErrorManager.logBug("org.modelica.mdt.editor",
			"Correct Indentation Failed!");			
		}
	}

	
	//public void update()
	//{
	//	super.update();
		/* 
		 * 	Subclasses that may modify the editor content should use 
		 *  canModifyEditor() in their update code to check whether 
		 *  updating the editor is most likely possible (even if it 
		 *  is read-only - this may change for editor contents that 
		 *  are under version control) and validateEditorInputState() 
		 *  before actually modifying the editor contents. 
		 */
	//}
    
}




