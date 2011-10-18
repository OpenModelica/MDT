/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Linköpings universitet, Department of
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
 * * Neither the name of Linköpings universitet nor the names of its
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

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaRoot;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.ui.editor.ModelicaElementEditorInput;
import org.modelica.mdt.ui.editor.SystemFileEditorInput;

/**
 * Implement the factory that can recreate modelica elements from
 * mementos. Used to provide persistence across platform sessions.
 */
public class ModelicaElementFactory implements IElementFactory
{
	/* the ID of this factory as specified by the extension point */
	public static final String FACTORY_ID = 
		"org.modelica.mdt.ui.ModelicaElementFactory";
	
	/* key used to store the class of the stored object */
	public static final String STORED_CLASS_KEY = "storedClass";
	
	/* stored class key's valid values */
	public static final int MODELICA_ELEMENT_EDITOR_INPUT_CLASS = 0;
	public static final int SYSTEM_FILE_EDITOR_INPUT = 1;

	/* keys used to save ModelicaElementEditorInput state */
	public static final String ELEMENT_PROJECT_NAME_KEY = 
		"elementProjectName";
	public static final String ELEMENT_SOURCE_FILE_KEY = 
		"elementSourceFile";
	
	/* key used to save SystemFileEditorInput */
	public static final String FILE_PATH_KEY = "filePath";
	


	public IAdaptable createElement(IMemento memento)
	{
		Assert.isNotNull(memento);
		if (memento == null) 
		{
			ErrorManager.logBug(UIPlugin.getSymbolicName(),
			"the memento is null");			
			return null;
		}
		Integer storedClass = memento.getInteger(STORED_CLASS_KEY);
		
		if (storedClass == null)
		{
			/* we don't know what class is stored in this memento, fail */
			ErrorManager.logBug(UIPlugin.getSymbolicName(),
			"class stored in the memento is null");
			return null;
		}
		
		switch (storedClass)
		{
		case MODELICA_ELEMENT_EDITOR_INPUT_CLASS:
			return restoreModelicaElementEditorInput(memento);
		case SYSTEM_FILE_EDITOR_INPUT:
			return restoreSystemFileEditorInput(memento);
		default:
			ErrorManager.logBug(UIPlugin.getSymbolicName(),
			"unknow class stored in the memento");			
		}
		
		ErrorManager.logBug(UIPlugin.getSymbolicName(),
				"unknow class stored in the memento");
		return null; /* this is not happening */
	}


	private IAdaptable restoreSystemFileEditorInput(IMemento memento)
	{
		String path = memento.getString(FILE_PATH_KEY);
		
		if (path == null)
		{
			/* can't restore system file editor input without a path */
		}
		return new SystemFileEditorInput(path, null);
	}


	private IAdaptable restoreModelicaElementEditorInput(IMemento memento)
	{
		IModelicaRoot root = ModelicaCore.getModelicaRoot();
		
		IModelicaProject proj = 
				root.getProject(memento.getString(ELEMENT_PROJECT_NAME_KEY));
		
		if (!proj.getResource().isAccessible()) return null;
			
		IModelicaElement elem = null;
		
		try
		{
			elem = 
				proj.findElement
					(new Path(memento.getString(ELEMENT_SOURCE_FILE_KEY)));
		
		} 
		catch (CompilerException e)
		{
			ErrorManager.showCompilerError(e);
			return null;
		}
		catch (CoreException e)
		{
			ErrorManager.showCoreError(e);
			return null;
		}
		if (elem != null) return new ModelicaElementEditorInput(elem);
		else return null;
	}

}

