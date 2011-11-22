/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005-2006, Linköpings universitet, Department of
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

import java.io.File;

import org.eclipse.core.resources.IStorage;
import org.eclipse.debug.core.sourcelookup.containers.LocalFileStorage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.PlatformUI;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.ui.ModelicaElementFactory;

/**
 * @author Adrian Pop
 */
public class SystemFileEditorInput implements IStorageEditorInput, IPersistableElement {

	private LocalFileStorage fileStorage;
	private IModelicaElement element;

	public SystemFileEditorInput(String path, IModelicaElement modelicaElement) {
		fileStorage = new LocalFileStorage(new File(path));
		element = modelicaElement;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SystemFileEditorInput)) {
			return false;
		}

		SystemFileEditorInput other = (SystemFileEditorInput) obj;
		return fileStorage.equals(other.fileStorage);
	}

	public IModelicaElement getElement() {
		return element;
	}

	/**
	 * @see IEditorInput#getName()
	 */
	@Override
	public String getName() {
		return fileStorage.getName();
	}

	/**
	 * @see IEditorInput#getFullPath()
	 */
	public String getFullPath() {		
		return fileStorage.getFullPath().toString();
	}

	/**
	 * @see IEditorInput#getContentType()
	 */
	public String getContentType() {
		return fileStorage.getFullPath().getFileExtension();
	}

	/**
	 * @see IEditorInput#getToolTipText()
	 */
	@Override
	public String getToolTipText() {
		return fileStorage.getFullPath().toString();
	}

	/**
	 * @see IEditorInput#getImageDescriptor()
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		IEditorRegistry registry = PlatformUI.getWorkbench().getEditorRegistry();
		return registry.getImageDescriptor(fileStorage.getFullPath().getFileExtension());
	}

	/**
	 * @see IEditorInput#exists()
	 */
	@Override
	public boolean exists() {
		return fileStorage.getFile().exists();
	}

	/**
	 * @see IAdaptable#getAdapter(Class)
	 */
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		return null;
	}

	/**
	 * see IStorageEditorInput#getStorage()
	 */
	@Override
	public IStorage getStorage() {
	 	return fileStorage;
	}

	/**
	 * @see IEditorInput#getPersistable()
	 */
	@Override
	public IPersistableElement getPersistable() {
		return this;
	}

	@Override
	public String getFactoryId() {
		return ModelicaElementFactory.FACTORY_ID;
	}

	@Override
	public void saveState(IMemento memento) {
		memento.putInteger(ModelicaElementFactory.STORED_CLASS_KEY, ModelicaElementFactory.SYSTEM_FILE_EDITOR_INPUT);
		memento.putString(ModelicaElementFactory.FILE_PATH_KEY, getFullPath());
	}
}
