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
 * * Neither the name of Linkï¿½pings universitet nor the names of its
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
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.ISourceRegion;
import org.modelica.mdt.core.IStandardLibrary;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.CorePlugin;

/**
 * A number of routines for working with modelica elements in editors.
 * Currently that number is one. Use openInEditor() to open a modelica element
 * in the editor.
 */
public class EditorUtility
{

	/**
	 * handles the tricky buisness of opening the element in editor depending
	 * on it's type
	 * 
	 * This method figures the source file of the element. If the element
	 * does not have a specific source file (e.g. folder package, modelica
	 * project) then no action is taken.
	 * 
	 * @param element the element to open in the editor
	 */
	public static void openInEditor(IModelicaElement element)
		throws ConnectException, UnexpectedReplyException, 
			InvocationError, CoreException, CompilerInstantiationException
	{
		if (element instanceof IStandardLibrary)
		{
			/* don't open standard library node */
			return;
		}
		if (element instanceof IModelicaFile && !(element instanceof IModelicaSourceFile)) /* yeah, this is uggly */
		{
			openInEditor((IFile)element.getResource());
		}
		else if (element instanceof IModelicaElement)
		{
			IModelicaElement modelicaElement = (IModelicaElement)element;
			IResource res = modelicaElement.getResource();
			ITextEditor editor = null;

			if (res == null)
			{
				/* 
				 * if the resource is null then we have an external 
				 * defined element
				 * Fetch the path to the source file and wrap it into
				 * a system file editor input converter 
				 */
				
				//editor = (ITextEditor) openInEditor(new ModelicaElementEditorInput(modelicaElement));
				String fp = modelicaElement.getFilePath();
				while (modelicaElement.getParent() != null)
				{
					if (modelicaElement.getParent() instanceof IStandardLibrary) break;
					modelicaElement = modelicaElement.getParent();
				}
				editor = (ITextEditor)openInEditor(new SystemFileEditorInput(fp, modelicaElement));
			} 
			else if (res instanceof IFile)
			{
				/* a element defined inside a file in the workspace */
				editor = (ITextEditor) openInEditor(new ModelicaElementEditorInput(modelicaElement));
			}
			else if ((res instanceof IFolder) || (res instanceof IProject))
			{
				/* don't open folder packages and project */
				return;
			}
			
			if (!(element instanceof IModelicaSourceFile))
			{
				/*
				 * for all elements except modelica files goto the line
				 * where the definition of the element begins
				 */
				ISourceRegion reg = ((IModelicaElement)element).getLocation().getSourceRegion();
				
				if (reg != null)
				{
					((ModelicaEditor)editor).setHighlightRange(reg, true);
				}
			}
		}		
	}
	
	/**
	 * Opens a file located in the workspace in it's default editor
	 * 
	 * @param file the file to open
	 */
	public static IEditorPart openInEditor(IFile file) 
		throws PartInitException 
	{
		if (file != null) 
		{
			IWorkbenchPage p = CorePlugin.getActivePage();
			if (p != null) 
			{
				IEditorPart editorPart= IDE.openEditor(p, file, true);
				return editorPart;
			}
		}
		return null;
	}
	
	private static IEditorPart openInEditor(IEditorInput input) throws PartInitException 
	{
		if (input != null) 
		{
			IWorkbenchPage p = CorePlugin.getActivePage();
			if (p != null) 
			{
				IEditorPart editorPart = p.openEditor(input, "org.modelica.mdt.ui.editor", true);

				return editorPart;
			}
		}
		return null;
	}

	/**
	 * Returns the given editor's input as Modelica element.
	 *
	 * @param editor the editor
	 * @return the given editor's input as Modelica element or <code>null</code> if none
	 */
	public static IModelicaElement getEditorInputModelicaElement(IEditorPart editor) {
		Assert.isNotNull(editor);
		IEditorInput editorInput= editor.getEditorInput();
		if (editorInput == null)
			return null;

		if (editorInput instanceof ModelicaElementEditorInput) 
		{
			IModelicaElement me = ((ModelicaElementEditorInput)editorInput).getSourceFile();
			if (me != null) 
				return (IModelicaElement)me;
		}

		if (editorInput instanceof FileEditorInput)
		{
			// search for the ModelicaElement that has this file!
			for(IModelicaProject mproj : ModelicaCore.getModelicaRoot().getProjects())
			{
				IProject proj = mproj.getWrappedProject();
				if (proj.isOpen())
				{
					IPath p = ((FileEditorInput)editorInput).getPath();
					IPath projPath = proj.getLocation();
					// we matched
					if (projPath.isPrefixOf(p))
					{
						p = p.removeFirstSegments(projPath.segmentCount());
						try	{
							IModelicaElement me = mproj.findElement(p);
							if (me != null) return me;
						} 
						catch(Exception x) {
							x.printStackTrace();
						}
					}
				}
			}
		}
		if (editorInput instanceof SystemFileEditorInput)
		{
			return ((SystemFileEditorInput)editorInput).getElement();
		}
		
		return null;
	}

//	public static IEditorInput getEditorInput(IModelicaSourceFile cu)
//	{
//		// TODO Auto-generated method stub
//		return editor.getEditorInput();
//	}
	
	
}
