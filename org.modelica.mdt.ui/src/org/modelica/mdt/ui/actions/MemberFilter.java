/******************************************************************************
 * @author Adrian Pop [adrpo@ida.liu.se, http://www.ida.liu.se/~adrpo]
 * Copyright (c) 2002-2006, Adrian Pop [adrpo@ida.liu.se],
 * Programming Environments Laboratory (PELAB),
 * Department of Computer and Information Science (IDA), 
 * Linköpings University (LiU). 
 * All rights reserved.
 *
 * http://www.ida.liu.se/~adrpo/license/
 *
 * NON-COMMERCIAL terms and conditions [NON-COMMERCIAL setting]:
 * Permission to use, copy, modify, and distribute this software and
 * its documentation in source or binary form (including products 
 * developed or generated using this software) for NON-COMMERCIAL 
 * purposes and without fee is hereby granted, provided that this 
 * copyright notice appear in all copies and that both the copyright 
 * notice and this permission notice and warranty disclaimer appear 
 * in supporting documentation, and that the name of The Author is not 
 * to be used in advertising or publicity pertaining to distribution 
 * of the software without specific, prior written permission.
 * 
 * COMMERCIAL terms and conditions [COMMERCIAL setting]:
 * COMMERCIAL use, copy, modification and distribution in source 
 * or binary form (including products developed or generated using
 * this software) is NOT permitted without prior written agreement 
 * from Adrian Pop [adrpo@ida.liu.se].
 * 
 * THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE,
 * INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS
 * OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE
 * OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
 *****************************************************************************/
package org.modelica.mdt.ui.actions;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;

/**
 * Filter for the outline viewer.
 * Changing a filter property does not trigger a refiltering of the viewer
 */
public class MemberFilter extends ViewerFilter 
{
	public static final int FILTER_PROTECTED= 1;
	public static final int FILTER_PARAMETERS= 2;
	public static final int FILTER_COMPONENTS= 4;
	public static final int FILTER_TYPES= 8;
	
	private int fFilterProperties;


	/**
	 * Modifies filter and add a property to filter for
	 */
	public final void addFilter(int filter) {
		fFilterProperties |= filter;
	}
	/**
	 * Modifies filter and remove a property to filter for
	 */	
	public final void removeFilter(int filter) {
		fFilterProperties &= (-1 ^ filter);
	}
	/**
	 * Tests if a property is filtered
	 */		
	public final boolean hasFilter(int filter) {
		return (fFilterProperties & filter) != 0;
	}
	
	/*
	 * @see ViewerFilter#isFilterProperty(java.lang.Object, java.lang.String)
	 */
	public boolean isFilterProperty(Object element, Object property) {
		return false;
	}
	/*
	 * @see ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */		
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		try 
		{
			if (element instanceof IModelicaComponent) 
			{
				if (hasFilter(FILTER_COMPONENTS)) return false;
			}
			if (element instanceof IModelicaClass) 
			{
				if (hasFilter(FILTER_TYPES) && ((IModelicaClass)element).getRestriction() == IModelicaClass.Restriction.TYPE) 
				{
					return false;
				}
			}	
			/*
			if (element instanceof IModelicaComponent) 
			{
				if (hasFilter(FILTER_PARAMETERS) && (Flags.isStatic(flags) || isFieldInInterfaceOrAnnotation(member)) && memberType != IModelicaElement.TYPE) {
					return false;
				}
			}				
			if (element instanceof IModelicaComponent) 
			{
				if (hasFilter(FILTER_PROTECTED) && !Flags.isPublic(flags) && !isMemberInInterfaceOrAnnotation(member) && !isTopLevelType(member) && !isEnumConstant(member)) {
					return false;
				}
			}
			*/				
		} catch (Exception e) {
			// ignore
			e.printStackTrace();
		}
		return true;
	}		
}
