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

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.model.WorkbenchAdapter;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.ui.text.ModelicaLabelBuilder;

/**
 * This class mapps modelica objects (Modelica packages, classes, etc) to icons 
 * and labels via IWorkbenchAdapter interface. This icon and lables are used in 
 * for example Modelica Projects view.
 * 
 * @author Elmir Jagudin
 */

public class ModelicaElementAdapter extends WorkbenchAdapter 
{

	@Override
	public String getLabel(Object object)
	{
		if (object instanceof IModelicaClass)
		{
			IModelicaClass cls = ((IModelicaClass)object);
			try
			{
				return ModelicaLabelBuilder.constructSignature(cls);
			}
			catch(Exception e){
				/* ignore */
			}
		}		
		return ((IModelicaElement)object).getElementName();
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object object)
	{
		if (object instanceof IModelicaProject)
		{
			/*
			 * Aren't patterns beautiful ?
			 */
			IModelicaProject mproj = (IModelicaProject) object;
			IWorkbenchAdapter wadap = (IWorkbenchAdapter) mproj.getWrappedProject().getAdapter(IWorkbenchAdapter.class);
			return wadap.getImageDescriptor(mproj.getWrappedProject());
			
		}
		/* 
		 * this check uggly must be done couse 
		 * IModelicaFile is superclass of IModelicaSourceFile
		 */
		else if ((object instanceof IModelicaFile) && !(object instanceof IModelicaSourceFile))
		{
			/*
			 * pattern beauty continued...
			 */
			IModelicaFile mfile = (IModelicaFile) object;
			IWorkbenchAdapter wadap = (IWorkbenchAdapter) mfile.getResource().getAdapter(IWorkbenchAdapter.class);
			return wadap.getImageDescriptor(mfile.getResource());
			
		}
		else if (object instanceof IModelicaFolder)
		{
			return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER);
		}
		else if (object instanceof IModelicaElement)
		{
			String key = ModelicaImages.getModelicaElementKey((IModelicaElement)object);
			if (key != null)
			{
				return ModelicaImages.getImageDescriptor(key);
			}
		}
		
		return super.getImageDescriptor(object);
	}

}