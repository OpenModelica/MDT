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

package org.modelica.mdt.ui.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.part.FileEditorInput;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.ui.ModelicaElementFactory;

/**
 * An instance of this class can be used to open a modelica element
 * in an editor. This class extends FileEditorInput with a mapping
 * from editor input to modelica source file reference.
 * 
 * Use getSourceFile() to retrive the modelica source file that this
 * input represents.
 */
public class ModelicaElementEditorInput extends FileEditorInput
{
	private IModelicaElement element;
	
	public ModelicaElementEditorInput(IModelicaElement element)
	{
		super((IFile)element.getResource());
		this.element = element;		
	}
	
	/**
	 * @return the modelica source file reference that this input represents
	 */
	public IModelicaSourceFile getSourceFile()
	{
		return element.getSourceFile();
	}
	
	public IPersistableElement getPersistable()
	{
		return this;
	}
	

	@Override
	public String getFactoryId()
	{
		return ModelicaElementFactory.FACTORY_ID;
	}

	@Override
	public void saveState(IMemento memento)
	{
		IModelicaProject proj = element.getProject();
		
		memento.putInteger(ModelicaElementFactory.STORED_CLASS_KEY, ModelicaElementFactory.MODELICA_ELEMENT_EDITOR_INPUT_CLASS);
		memento.putString(ModelicaElementFactory.ELEMENT_PROJECT_NAME_KEY, proj.getElementName());			
		memento.putString(ModelicaElementFactory.ELEMENT_SOURCE_FILE_KEY, getSourceFile().getResource().getProjectRelativePath().toOSString());
	}
	
}
