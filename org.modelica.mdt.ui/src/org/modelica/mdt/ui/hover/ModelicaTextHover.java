/******************************************************************************
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
 *****************************************************************************/

package org.modelica.mdt.ui.hover;

import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.internal.core.ModelicaComponent;
import org.modelica.mdt.internal.core.ModelicaElement;
import org.modelica.mdt.ui.editor.ModelicaEditor;
import org.modelica.mdt.ui.editor.ModelicaElementEditorInput;
import org.modelica.mdt.ui.editor.SystemFileEditorInput;
import org.modelica.mdt.ui.text.ModelicaKeywords;
import org.modelica.mdt.ui.text.ModelicaWordFinder;
import org.modelica.mdt.core.compiler.IClassInfo;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.core.IModelicaElement;

public class ModelicaTextHover implements ITextHover
{
	private IEditorPart fEditor;
	private boolean DEBUG = false;
	
	public IRegion getHoverRegion(ITextViewer textViewer, int offset) 
	{
		return ModelicaWordFinder.findWord(textViewer.getDocument(), offset);
	}

	public void setEditor(IEditorPart editor)
	{
		fEditor = editor;
	}

	public IEditorPart getEditor()
	{
		return fEditor;
	}

	
	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) 
	{
		if (hoverRegion != null) 
		{
			String word = null;
			try 
			{
				if (hoverRegion.getLength() > -1)
				{ 
					word = textViewer.getDocument().get(hoverRegion.getOffset(), hoverRegion.getLength());
					int offs = +1;
					while (Character.isJavaIdentifierPart(textViewer.getDocument().getChar(hoverRegion.getOffset()-offs)) ||
							textViewer.getDocument().getChar(hoverRegion.getOffset()-offs) == '.')
					{
						word = String.valueOf(textViewer.getDocument().getChar(hoverRegion.getOffset()-offs)) + word;
						offs = offs + 1;
					}
				}
			} 
			catch (BadLocationException x) 
			{
				x.printStackTrace();
			}
			if (word != null)
			{
				// if is a keyword don't bother!
				if (ModelicaKeywords.isKeyword(word)) return "keyword: " + word;
				String info = null;
				
				// else try to go for the full class name
				if (fEditor == null) return null;  
				IModelicaSourceFile file = null;				
				if (fEditor instanceof ModelicaEditor)
				{
					IModelicaElement me = ((ModelicaEditor)fEditor).getEditorInputModelicaElement();
					if (me != null)
						file = me.getSourceFile();
				}
				if (file == null)
				{
					IEditorInput input = fEditor.getEditorInput();
					if (input instanceof SystemFileEditorInput ||
					 	input instanceof FileEditorInput)
					{
						file = null;
						//IPath path = ((SystemFileEditorInput)input).getStorage().getFullPath();
						//IFile f = path.toFile();
						//file = new ModelicaSourceFile((FolderPackage)null, (IFile)path);
					}
					if (input instanceof ModelicaElementEditorInput)
					{
						file = ((ModelicaElementEditorInput)input).getSourceFile();
					}
				}
				if (file != null)
				{
					try
					{
						IModelicaElement c = ((ModelicaEditor)fEditor).getElementAt(hoverRegion.getOffset());
						while (c != null)
						{
							if (c != null) 
							{
								Collection<? extends IModelicaElement> kids = null;								
								if (c instanceof IModelicaClass)
								{
									kids = ((IModelicaClass)c).getChildren();
								}
								if (c instanceof IModelicaSourceFile)
								{
									kids = ((IModelicaSourceFile)c).getChildren();
								}
								
								// try to see the components of the class:
								if (kids != null)
								for (IModelicaElement comp : kids)
								{
									if (comp == null) continue;
									
									if (DEBUG) System.err.println("Searching childen:" + comp.getElementName());								
									if (comp.getElementName().equals(word))
									{
										if (comp instanceof IModelicaComponent)
										{
											if (DEBUG) System.err.println("Found component child:" + comp.getFullName() + "." + word);
											info = getInformation(c.getFullName() + "." + word);						
											if (info != null) return info;
											ModelicaComponent cmp = (ModelicaComponent)comp;
											return cmp.getVisibility().name().toLowerCase() + " " + cmp.getTypeName();
										}
										if (comp instanceof IModelicaClass)
										{
											if (DEBUG) System.err.println("Found child:" + comp.getFullName() + "." + word);
											info = getInformation(c.getFullName() + "." + word);						
											if (info != null) return info;
										}
									}
									// not equal, try directly:
									if (comp instanceof IModelicaClass)
									{
										if (DEBUG) System.err.println("Trying inner class:" + comp.getFullName() + "." + word);
										info = getInformation(comp.getFullName() + "." + word);						
										if (info != null) return info;
									}									
								}
								// see if the word is the same as the class:
								if (word.equals(c.getElementName()))
								{
									if (DEBUG) System.err.println("Lookup:" + c.getFullName());						
									info = getInformation(c.getFullName());						
									if (info != null) return info;							
								}
								
								// try to put the word inside the class
								if (DEBUG) System.err.println("Lookup:" + c.getElementName() + "." + word);						
								info = getInformation(c.getElementName() + "." + word);
								if (info != null) return info;
								// try to go from the class to the TOP level and search for children in
								// any of the classes until then.
								c = (ModelicaElement)c.getParent();							
							}						
							if (info != null) return info;
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
					catch (Exception e)
					{
						ErrorManager.logError(e);
					}
				}
				// lastly try to see if we can solve the name directly
				if (DEBUG) System.err.println("Lookup:" + word);				
				info = getInformation(word);
				if (info != null) return info;
				
				return word;
			}
		}
		return null; 
	}

	public synchronized String getInformation(String className)
	{
		// if the name starts with something fancy, just ignore it!
		if (!Character.isJavaIdentifierStart(className.charAt(0))) 
			return null;
		
		try
		{
			 ICompilerResult res = CompilerProxy.getClassString(className);
			 String info = res.getFirstResult().trim();
			if (info.startsWith("\"")) info = info.substring(1);
			if (info.endsWith("\"")) info = info.substring(0, info.length()-1);
			info = info.trim();
			if (!info.equals("")) return info + "\n";

			IClassInfo classAttributes = CompilerProxy.getClassInfo(className);
			return classAttributes.getRestriction().name();			
		}
		catch(UnexpectedReplyException e)
		{
			//e.printStackTrace();
		}
		catch(Exception e)
		{
			//e.printStackTrace();
		}
		return null;
	}

}
