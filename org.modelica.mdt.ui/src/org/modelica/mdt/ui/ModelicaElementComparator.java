/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Link�pings universitet, Department of
 * Computer and Information Science, PELAB
 *
 * All rights reserved.
 *
 * (The new BSD license, see also
 * http://www.opensource.org/licenses/bsd-license.php)
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * * Neither the name of Link�pings universitet nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.modelica.mdt.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.views.navigator.ResourceComparator;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaClass.Restriction;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaExtends;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IStandardLibrary;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.internal.core.ErrorManager;

/**
 * Implements ordering of modelica elements suitable for displaying them in the
 * tree view.
 * @author Adrian Pop
 * @author Homer Simpson
 */
public class ModelicaElementComparator extends ResourceComparator {
	private static final int PROJECT_ORDER = 1;
	private static final int FOLDER_ORDER = 2;
	private static final int EXTENDS_ORDER = 4;	
	private static final int PACKAGE_ORDER = 5;
	private static final int PUBLIC_COMPONENT_ORDER = 7;
	private static final int PROTECTED_COMPONENT_ORDER = 8;
	private static final int CLASS_ORDER = 6;
	private static final int MODELICA_FILE_ORDER = 9;
	private static final int PLAIN_FILE_ORDER = 10;
	private static final int IMPORT_ORDER = 11;	
	private static final int STANDARD_LIBRARY_ORDER = 12;
	private static final int UNKOWN_TYPE_ORDER = Integer.MAX_VALUE;
	
    /**
     * Constructor argument value that indicates to sort items by name.
     */
    public final static int NAME = 1;

    /**
     * Constructor argument value that indicates to sort items by extension.
     */
    public final static int TYPE = 2;
	
    /**
     * Constructor argument value that indicates to sort by our compare method
     * and not use org.eclipse.ui.views.navigator.ResourceComparator.compare().
     */
    public final static int MODELICA = 3;

    
    private int criteria;

    /**
     * Creates a resource sorter that will use the given sort criteria.
     *
     * @param criteria the sort criterion to use: one of <code>NAME</code> or 
     *   <code>TYPE</code>
     */
    public ModelicaElementComparator(int criteria) {
        super(criteria);
        this.criteria = criteria;
    }
	
	@Override
	public int category(Object element) {
		if (element instanceof IModelicaFolder) {
			return FOLDER_ORDER;
		}
		else if (element instanceof IModelicaClass) {
			// a class can be a package, sort package in their own category
			try {
				if (((IModelicaClass)element).getRestriction() == Restriction.PACKAGE) {
					return PACKAGE_ORDER;
				}
			}			
			catch (CompilerException e) {
				ErrorManager.showCompilerError(e);
				// we don't really know what category this element is in
				return UNKOWN_TYPE_ORDER;
			}
			catch (CoreException e)
			{
				ErrorManager.showCoreError(e);
				// we don't really know what category this element is in
				return UNKOWN_TYPE_ORDER;
			}
			return CLASS_ORDER;
		}
		else if (element instanceof IModelicaComponent) {
			switch (((IModelicaComponent)element).getVisibility()) {
			case PUBLIC:
				return PUBLIC_COMPONENT_ORDER;
			case PROTECTED:
				return PROTECTED_COMPONENT_ORDER;
			default:
				return CLASS_ORDER;
			}
		}
		else if (element instanceof IModelicaImport) {
			return IMPORT_ORDER;
		}
		else if (element instanceof IModelicaExtends) {
			return EXTENDS_ORDER;			
		}
		else if (element instanceof IModelicaSourceFile) {
			return MODELICA_FILE_ORDER;
		}
		else if (element instanceof IModelicaFile) {
			return PLAIN_FILE_ORDER;
		}
		else if (element instanceof IStandardLibrary) {
			return STANDARD_LIBRARY_ORDER;
		}
		else if (element instanceof IModelicaProject) {
			return PROJECT_ORDER;
		}
		else if (element instanceof IResource) {
			return PLAIN_FILE_ORDER;
		}

		ErrorManager.logBug(UIPlugin.getSymbolicName(), "element of unknown type encountered");
		return UNKOWN_TYPE_ORDER;
	}
	
    /**
     * Returns a negative, zero, or positive number depending on whether
     * the first element is less than, equal to, or greater than
     * the second element.
     * <p>
     * The default implementation of this method is based on
     * comparing the elements' categories as computed by the <code>category</code>
     * framework method. Elements within the same category are further 
     * subjected to a case insensitive compare of their label strings, either
     * as computed by the content viewer's label provider, or their 
     * <code>toString</code> values in other cases. Subclasses may override.
     * </p>
     * 
     * @param viewer the viewer
     * @param e1 the first element
     * @param e2 the second element
     * @return a negative number if the first element is less  than the 
     *  second element; the value <code>0</code> if the first element is
     *  equal to the second element; and a positive number if the first
     *  element is greater than the second element
     */
    @SuppressWarnings("unchecked")
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
    	if (criteria != MODELICA) {
    		return super.compare(viewer, e1, e2);
    	}
        int cat1 = category(e1);
        int cat2 = category(e2);

        if (cat1 != cat2) {
			return cat1 - cat2;
		}
    	
        String name1;
        String name2;

        if (viewer == null || !(viewer instanceof ContentViewer)) {
            name1 = e1.toString();
            name2 = e2.toString();
        } 
        else {
            IBaseLabelProvider prov = ((ContentViewer)viewer).getLabelProvider();
            if (prov instanceof ILabelProvider) {
                ILabelProvider lprov = (ILabelProvider)prov;
                name1 = lprov.getText(e1);
                name2 = lprov.getText(e2);
            } 
            else {
                name1 = e1.toString();
                name2 = e2.toString();
            }
        }
        if (name1 == null) {
			name1 = "";//$NON-NLS-1$
		}
        if (name2 == null) {
			name2 = "";//$NON-NLS-1$
		}

        // use the comparator to compare the strings
        return getComparator().compare(name1, name2);
    }
}
