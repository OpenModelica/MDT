/*******************************************************************************
 * Copyright (c) 2006 MDT Team, PELAB
 *******************************************************************************/
package org.modelica.mdt.ui.text;

public class OptimizedReplaceEdit {
	
	int offset;
	int length;
	String replacement;
	
	public OptimizedReplaceEdit(int offset, int length, String replacement) {
		this.offset = offset;
		this.length = length;
		this.replacement = replacement;
	}
	
	public String toString() {
		return "(" + this.offset + ", length " + this.length + " :>" + this.replacement + "<"; //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$//$NON-NLS-4$
	}
}
