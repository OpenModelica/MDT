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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ViewerSorter;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IStandardLibrary;
import org.modelica.mdt.core.IModelicaClass.RestrictionType;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.internal.core.ErrorManager;

/**
 * Implements ordering of modelica elements suitable for displaying 'em in the
 * tree view.
 * 
 * @author Homer Simpson
 */
public class ModelicaElementSorter extends ViewerSorter
{
	private static int PROJECT_ORDER 				= 1;
	private static int FOLDER_ORDER 				= 2;
	private static int PACKAGE_ORDER 				= 3;
	private static int CLASS_ORDER 					= 4;
	private static int PUBLIC_COMPONENT_ORDER   	= 5;
	private static int PROTECTED_COMPONENT_ORDER   	= 6;
	private static int MODELICA_FILE_ORDER			= 7;
	private static int PLAIN_FILE_ORDER 			= 8;
	private static int STANDARD_LIBRARY_ORDER 		= 9;
	private static int UNKOWN_TYPE_ORDER 			= Integer.MAX_VALUE;

	@Override
	public int category(Object element)
	{
		if (element instanceof IModelicaFolder)
		{
			return FOLDER_ORDER;
		}
		else if (element instanceof IModelicaClass)
		{
			/*
			 * a class can be a package, sort package in their own category
			 */
			try
			{
				if (((IModelicaClass)element).getRestrictionType() 
						== RestrictionType.PACKAGE)
				{
					return PACKAGE_ORDER;
				}
			}			
			catch (CompilerException e)
			{
				ErrorManager.showCompilerError(e);
				/* we don't realy know what catagory this element is in */
				return UNKOWN_TYPE_ORDER;
			}
			catch (CoreException e)
			{
				ErrorManager.showCoreError(e);
				/* we don't realy know what catagory this element is in */
				return UNKOWN_TYPE_ORDER;
			}
			return CLASS_ORDER;
		}
		else if (element instanceof IModelicaComponent)
		{
			switch (((IModelicaComponent)element).getVisbility())
			{
			case PUBLIC:
				return PUBLIC_COMPONENT_ORDER;
			case PROTECTED:
				return PROTECTED_COMPONENT_ORDER;
			}
		}
		else if (element instanceof IModelicaSourceFile)
		{
			return MODELICA_FILE_ORDER;
		}
		else if (element instanceof IModelicaFile)
		{
			return PLAIN_FILE_ORDER;
		}
		else if (element instanceof IStandardLibrary)
		{
			return STANDARD_LIBRARY_ORDER;
		}
		else if (element instanceof IModelicaProject)
		{
			return PROJECT_ORDER;
		}

		ErrorManager.logBug(UIPlugin.getSymbolicName(),
				"element of unknow type encountered");
		return UNKOWN_TYPE_ORDER;
	}
}
