/*
 * Copyright 2006, Adrian Pop, adrpo@ida.liu.se
 */
package org.modelica.mdt.ui.editor;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * @author Adrian Pop, adpro@ida.liu.se
 *  2006-04-28 
 *   - Added as a place for all colors used in the editor
 *   - In the future colors should be picked from preference page
 */
public class ModelicaEditorColors
{

	public static Color MULTILINE_COMMENT_COLOR 
	= new Color(Display.getCurrent(), new RGB(0, 150, 0));
	public static Color SINGLELINE_COMMENT_COLOR 
	= new Color(Display.getCurrent(), new RGB(0, 130, 0));
	public static Color STRING_COLOR 
	= new Color(Display.getCurrent(), new RGB(0, 139, 0));
	public static Color KEYWORD_COLOR 
	= new Color(Display.getCurrent(), new RGB(139, 0, 0));
	public static Color CODE_COLOR
	= new Color(Display.getCurrent(), new RGB(0, 0, 0));
	public static Color TYPE_COLOR 
	= new Color(Display.getCurrent(), new RGB(255, 10, 10));
	public static Color NUMBER_COLOR 
	= new Color(Display.getCurrent(), new RGB(139, 0, 139));
	private static Color CONSTRUCTOR_COLOR 
	= new Color(Display.getCurrent(), new RGB(0, 0, 255));
	private static Color BUILTIN_COLOR 
	= new Color(Display.getCurrent(), new RGB(0, 139, 139));
	private static Color FUNCTION_COLOR 
	= new Color(Display.getCurrent(), new RGB(139, 139, 139));
	private static Color OPERATORS_COLOR 
	= new Color(Display.getCurrent(), new RGB(255, 0, 139));	
	
	public static Color ERROR_COLOR 
	= new Color(Display.getCurrent(), new RGB(255, 0, 0));
	
	public static TextAttribute MULTILINE_COMMENT_ATTRIBUTE = 
		new TextAttribute(MULTILINE_COMMENT_COLOR,  null,SWT.NORMAL);

	public static TextAttribute SINGLELINE_COMMENT_ATTRIBUTE =
		new TextAttribute(SINGLELINE_COMMENT_COLOR, null,SWT.NORMAL);

	public static TextAttribute STRING_ATTRIBUTE =
		new TextAttribute(STRING_COLOR, null,SWT.NORMAL);

	public static TextAttribute KEYWORD_ATTRIBUTE = 
		new TextAttribute(KEYWORD_COLOR, null, SWT.BOLD);

	public static TextAttribute CODE_ATTRIBUTE =
		new TextAttribute(CODE_COLOR, null,SWT.NORMAL);

	public static TextAttribute TYPE_ATTRIBUTE =
		new TextAttribute(TYPE_COLOR, null, SWT.BOLD);

	public static TextAttribute NUMBER_ATTRIBUTE = 
		new TextAttribute(NUMBER_COLOR, null, SWT.NORMAL);
	
	public static TextAttribute FUNCTION_ATTRIBUTE = 
		new TextAttribute(FUNCTION_COLOR, null, SWT.NORMAL);
	
	public static TextAttribute BUILTIN_ATTRIBUTE = 
		new TextAttribute(BUILTIN_COLOR, null, SWT.NORMAL);

	public static TextAttribute CONSTRUCTOR_ATTRIBUTE = 
		new TextAttribute(CONSTRUCTOR_COLOR, null, SWT.NORMAL);
	
	public static TextAttribute OPERATORS_ATTRIBUTE = 
		new TextAttribute(OPERATORS_COLOR, null, SWT.NORMAL);
	
	public static TextAttribute ERROR_ATTRIBUTE =
		new TextAttribute(ERROR_COLOR, null, SWT.BOLD);	

}
