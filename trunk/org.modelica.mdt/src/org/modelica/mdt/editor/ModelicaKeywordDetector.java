/*
 * Created on Jan 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.modelica.mdt.editor;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * @author Peter Bunus
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * Determines whether a given character is valid as part of an Modelica keyword in
 * the current context.
 */

public class ModelicaKeywordDetector implements IWordDetector, ModelicaSyntax {

	/**
	 * 
	 */
	public ModelicaKeywordDetector() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	   * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
	   */
	  public boolean isWordStart(char c) {
	    for (int i = 0; i < reservedwords.length; i++)
	      if (((String) reservedwords[i]).charAt(0) == c)
	        return true;

	    for (int i = 0; i < types.length; i++)
	      if (((String) types[i]).charAt(0) == c)
	        return true;

	    for (int i = 0; i < constants.length; i++)
	      if (((String) constants[i]).charAt(0) == c)
	        return true;
	    return false;
	  }

	  /**
	   * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
	   */
	  public boolean isWordPart(char c) {
	    for (int i = 0; i < reservedwords.length; i++)
	      if (((String) reservedwords[i]).indexOf(c) != -1)
	        return true;

	    for (int i = 0; i < types.length; i++)
	      if (((String) types[i]).indexOf(c) != -1)
	        return true;

	    for (int i = 0; i < constants.length; i++)
	      if (((String) constants[i]).indexOf(c) != -1)
	        return true;

	    return false;
	  }

}
