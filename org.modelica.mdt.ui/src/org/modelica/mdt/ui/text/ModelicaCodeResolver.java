/*******************************************************************************
 * @author Adrian Pop
 * Copyright (c) 2006 Adrian Pop, adrpo@ida.liu.se
 * All rights reserved. 
 * Permission to use, copy, modify, and distribute this software and
 * its documentation for NON-COMMERCIAL purposes and without fee is hereby 
 * granted, provided that this copyright notice appear in all copies and 
 * that both the copyright notice and this permission notice and warranty
 * disclaimer appear in supporting documentation, and that the name of
 * The Author not be used in advertising or publicity pertaining to
 * distribution of the software without specific, written prior permission.
 * 
 * COMMERCIAL use, copy, modification and distribution 
 * is NOT permitted without prior agreement with Adrian Pop.
 * 
 * THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE,
 * INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS
 * OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE
 * OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
*******************************************************************************/
package org.modelica.mdt.ui.text;

import java.util.Collection;
import org.modelica.mdt.ui.ModelicaLookupException;
import org.modelica.mdt.ui.editor.ModelicaEditor;
import org.modelica.mdt.ui.editor.ModelicaElementEditorInput;
import org.modelica.mdt.ui.editor.SystemFileEditorInput;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.ISourceRegion;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.internal.core.ModelicaComponent;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

@SuppressWarnings("unchecked")
public class ModelicaCodeResolver
{	
	private static boolean DEBUG = false;
	public static synchronized IModelicaElement[] lookup(ModelicaEditor editor, ITextSelection selection)
	throws ModelicaLookupException
	{	 
		IRegion region = ModelicaWordFinder.findWord(
				editor.getViewer().getDocument(), 
				selection.getOffset());

		String word = null;		

		// if we have no region return null!
		if (region == null) return null;
		try 
		{
			if (region.getLength() > -1)
			{ 
				word = editor.getViewer().getDocument().get(region.getOffset(), region.getLength());

				int offs = +1;
				while (Character.isJavaIdentifierPart(editor.getViewer().getDocument().getChar(region.getOffset()-offs)) ||
						editor.getViewer().getDocument().getChar(region.getOffset()-offs) == '.')
				{
					word = String.valueOf(editor.getViewer().getDocument().getChar(region.getOffset()-offs)) + word;
					offs = offs + 1;
				}
			}
		} 
		catch (BadLocationException x) 
		{
			x.printStackTrace();
		}		

		// if we don't have any word at that offset, return null!
		if (word == null) return null;
		// if is a keyword don't bother!
		if (ModelicaKeywords.isKeyword(word)) return null;
		
		// if the name starts with something fancy, just ignore it!		
		if (!Character.isJavaIdentifierStart(word.charAt(0))) 
			return null;
		
		if (DEBUG) System.out.println("ModelicaCodeResolve.lookup: " + word);

		// we have the identifier! search for the best match 
		IModelicaElement[] result = null;
		// first look into the class!
		IEditorInput input = editor.getEditorInput();
		IModelicaSourceFile file = null;	
		if (editor instanceof ModelicaEditor)
		{
			IModelicaElement me = ((ModelicaEditor)editor).getEditorInputModelicaElement();
			if (me != null)
				file = me.getSourceFile();
		}
		if (file == null)
		{
			if (input instanceof ModelicaElementEditorInput)
			{
				file = ((ModelicaElementEditorInput)input).getSourceFile();
			}
			if (input instanceof SystemFileEditorInput || input instanceof FileEditorInput)
			{
				file = null;
				//IPath path = ((SystemFileEditorInput)input).getStorage().getFullPath();
				//IFile f = path.toFile();
				//file = new ModelicaSourceFile((FolderPackage)null, (IFile)path);
			}			
		}
		if (file != null)
		{
			try
			{
				IModelicaClass c = (IModelicaClass)editor.getElementAt(selection.getOffset()); //file.getClassAt(selection.getOffset());
				result = bottomUpLookup(word, c);
				// if we haven't find anything try to perform topDownLookup
				if (result == null)
				{
					for (IModelicaClass cls: file.getProject().getRootClasses())
					{
						result = topDownLookup(word, cls);
						if (result != null) return result;
					}
				}
				return result;
			}
			catch (CoreException e)
			{
				ErrorManager.showCoreError(e);
			} 
			catch (CompilerException e)
			{
				ErrorManager.showCompilerError(e);
			}					
			return null;
		}
		return null;
	}

	public static IModelicaElement[] bottomUpLookup(String id, IModelicaElement c)
	{
		if (c == null) return null;
		IModelicaElement theClass = null;
		Collection<? extends IModelicaElement> kids = null;
		if (c instanceof IModelicaClass)
		{
			theClass = (IModelicaClass)c;
			try
			{							
				kids = ((IModelicaClass)theClass).getChildren();
			}
			catch (CompilerException e)
			{
				ErrorManager.showCompilerError(e);
			}								
			catch (CoreException e)
			{
				ErrorManager.showCoreError(e);
			} 			
		}
		if (c instanceof IModelicaSourceFile)
		{
			theClass = (IModelicaElement)c;
			try
			{							
				for (IModelicaElement comp : ((IModelicaSourceFile)theClass).getChildren())
				{
					if (comp instanceof IModelicaClass)
					{
						Collection k = ((IModelicaClass)comp).getChildren();
						if (kids == null) kids = k;
						else kids.addAll(k);
					}
				}
			}
			catch (CompilerException e)
			{
				ErrorManager.showCompilerError(e);
			}											
			catch (CoreException e)
			{
				ErrorManager.showCoreError(e);
			} 
		}
		
		// we couldn't cast to something useful, bail out
		if (theClass == null) return null;
		
		IModelicaElement[] result = null;
		if (DEBUG) System.err.println("BottomUp: Looking into class:" + theClass.getFullName());

		// see if the word is the same as the class:
		if (id.equals(theClass.getElementName()) || id.equals(theClass.getFullName()))
		{
			if (DEBUG) System.err.println("BottomUp: Lookup found:" + theClass.getFullName());
			result = new IModelicaElement[1];
			result[0] = theClass;
			return result;
		}
		// try to see the components of the class:
		for (IModelicaElement comp : kids)
		{
			if (DEBUG) System.err.println("BottomUp: Searching childen:" + comp.getElementName());								
			if (comp.getElementName().equals(id) || id.equals(theClass.getFullName() + "." + comp.getElementName()))
			{
				if (comp instanceof IModelicaComponent)
				{
					if (DEBUG) System.err.println("BottomUp: Found component child:" + comp.getElementName());
					ModelicaComponent cmp = (ModelicaComponent)comp;
					result = new IModelicaElement[1];
					result[0] = cmp; 
					return result;
				}
				if (comp instanceof IModelicaClass)
				{
					if (DEBUG) System.err.println("BottomUp: Found class child:" + comp.getElementName());
					result = new IModelicaElement[1];
					result[0] = comp; 
					return result;
				}				
			}
		}

		return bottomUpLookup(id, theClass.getParent());
		
	}
	
	public static IModelicaElement[] topDownLookup(String id, IModelicaElement c)
	{
		if (c == null) return null;
		if (!(c instanceof IModelicaClass)) return null;
		IModelicaClass theClass = (IModelicaClass)c;
		IModelicaElement[] result = null;			
		if (DEBUG) System.err.println("TopDown: Looking into class:" + theClass.getFullName());		
		// see if the word is the same as the class:
		if (id.equals(theClass.getElementName()) || id.equals(theClass.getFullName()))
		{
			if (DEBUG) System.err.println("TopDown: Lookup:" + theClass.getFullName());
			result = new IModelicaElement[1];
			result[0] = c;
			return result;
		}
		// try to see the components of the class ONLY if the id starts with theClass.getFullName()
		if (!id.startsWith(theClass.getFullName())) return null;
		
		try
		{
			for (IModelicaElement comp : theClass.getChildren())
			{
				if (DEBUG) System.err.println("TopDown: Searching childen:" + comp.getElementName());								
				if (comp.getElementName().equals(id) || 
						id.equals(theClass.getFullName() + "." + comp.getElementName()))
				{
					if (comp instanceof IModelicaComponent)
					{
						if (DEBUG) System.err.println("TopDown: Found component child:" + comp.getElementName());
						ModelicaComponent cmp = (ModelicaComponent)comp;
						result = new IModelicaElement[1];
						result[0] = cmp; 
						return result;
					}
					if (comp instanceof IModelicaClass)
					{
						if (DEBUG) System.err.println("TopDown: Found class child:" + comp.getElementName());
						result = new IModelicaElement[1];
						result[0] = comp; 
						return result;
					}				
				}
				if (comp instanceof IModelicaClass)
				{
					result = topDownLookup(id, comp);
					if (result != null) return result;
				}								
			}
		}
		catch (CoreException e)
		{
			ErrorManager.showCoreError(e);
		} 
		catch (CompilerException e)
		{
			ErrorManager.showCompilerError(e);
		}								
		return null;		
	}	
	
	/**
	 * @author Adrian Pop
	 * @param editor
	 * @param offset
	 * @return IModelicaClass
	 * @deprecated
	 * @see ModelicaCodeResolver#getClassAt(ModelicaEditor, ISourceRegion)
	 */
	public static IModelicaClass getClassAt(ModelicaEditor editor, int offset)
	{
		IEditorInput input = editor.getEditorInput();
		if (input instanceof ModelicaElementEditorInput)
		{
			IModelicaSourceFile file = 
				((ModelicaElementEditorInput)input).getSourceFile();		
			try
			{
				IModelicaClass c = file.getClassAt(offset);
				return c;
			}
			catch (CoreException e)
			{
				ErrorManager.showCoreError(e);
			} 
			catch (CompilerException e)
			{
				ErrorManager.showCompilerError(e);
			}
		}
		return null;
	}
	
	/**
	 * @author Adrian Pop
	 * @param ModelicaEditor editor
	 * @param ISourceRange sourceRange
	 * @return IModelicaClass
	 */
	public static IModelicaClass getClassAt(ModelicaEditor editor, ISourceRegion sourceRegion)
	{
		IEditorInput input = editor.getEditorInput();
		IModelicaSourceFile file = null;
		if (!(input instanceof ModelicaElementEditorInput))
		{
			/* try some other way */
			IModelicaElement me = editor.getEditorInputModelicaElement();
			file = me.getSourceFile();
		}
		else
		{
			 file = ((ModelicaElementEditorInput)input).getSourceFile();	
		}

		if (file != null)
		{
	
			try
			{
				IModelicaClass c = file.getClassAt(sourceRegion);
				return c;
			}
			catch (CoreException e)
			{
				ErrorManager.showCoreError(e);
			} 
			catch (CompilerException e)
			{
				ErrorManager.showCompilerError(e);
			}
		}
		
		return null;
	}	
	
}
