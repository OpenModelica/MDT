package org.modelica.mdt.ui.editor;

import org.eclipse.jface.text.rules.IWordDetector;

public class SusanKeywords implements IWordDetector
{

	public SusanKeywords()
	{
	}

	public boolean isWordStart(char c)
	{
		return Character.isJavaIdentifierStart(c);
	}

	public boolean isWordPart(char c)
	{
		return Character.isJavaIdentifierPart(c);
	}
}
