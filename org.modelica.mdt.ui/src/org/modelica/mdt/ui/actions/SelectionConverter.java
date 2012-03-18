/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.ui.actions;

import java.util.Iterator;

//import org.modelica.mdt.core.IClassFile;
//import org.modelica.mdt.core.ICodeAssist;
//import org.modelica.mdt.core.IModelicaSourceFile;
//import org.modelica.mdt.core.IModelicaElement;
//import org.modelica.mdt.core.ISourceRange;
//import org.modelica.mdt.core.ISourceReference;
//import org.modelica.mdt.core.IType;
//import org.modelica.mdt.core.JavaModelException;

import org.eclipse.swt.widgets.Shell;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;

//import org.modelica.mdt.internal.corext.util.JavaModelUtil;
//import org.modelica.mdt.ui.JavaPlugin;
//import org.modelica.mdt.ui.ModelicaEditor.IClassFileEditorInput;
//import org.modelica.mdt.ui.ModelicaEditor.ModelicaEditor;
//import org.modelica.mdt.ui.util.ExceptionHandler;
//
//import org.modelica.mdt.ui.IWorkingCopyManager;
import org.modelica.mdt.core.*;
import org.modelica.mdt.ui.editor.ModelicaEditor;
import org.modelica.mdt.ui.editor.ModelicaElementEditorInput;
import org.eclipse.core.runtime.CoreException;

public class SelectionConverter {

	private static final IModelicaElement[] EMPTY_RESULT= new IModelicaElement[0];
	
	private SelectionConverter() {
		// no instance
	}

	/**
	 * Converts the selection provided by the given part into a structured selection.
	 * The following conversion rules are used:
	 * <ul>
	 *	<li><code>part instanceof ModelicaEditor</code>: returns a structured selection
	 * 	using code resolve to convert the editor's text selection.</li>
	 * <li><code>part instanceof IWorkbenchPart</code>: returns the part's selection
	 * 	if it is a structured selection.</li>
	 * <li><code>default</code>: returns an empty structured selection.</li>
	 * </ul>
	 */
	public static IStructuredSelection getStructuredSelection(IWorkbenchPart part) throws CoreException {
//		if (part instanceof ModelicaEditor)
//			return new StructuredSelection(codeResolve((ModelicaEditor)part));
		ISelectionProvider provider= part.getSite().getSelectionProvider();
		if (provider != null) {
			ISelection selection= provider.getSelection();
			if (selection instanceof IStructuredSelection)
				return (IStructuredSelection)selection;
		}
		return StructuredSelection.EMPTY;
	}

	
	/**
	 * Converts the given structured selection into an array of Java elements.
	 * An empty array is returned if one of the elements stored in the structured
	 * selection is not of type <code>IModelicaElement</code>
	 */
	public static IModelicaElement[] getElements(IStructuredSelection selection) {
		if (!selection.isEmpty()) {
			IModelicaElement[] result= new IModelicaElement[selection.size()];
			int i= 0;
			@SuppressWarnings("rawtypes")
			Iterator iter = selection.iterator();
			while (iter.hasNext()) {
				Object element= iter.next();
				if (!(element instanceof IModelicaElement)) {
					return EMPTY_RESULT;
				}
				result[i++]= (IModelicaElement)element;
			}
			return result;
		}
		return EMPTY_RESULT;
	}

	public static boolean canOperateOn(ModelicaEditor editor) {
		if (editor == null)
			return false;
		return getInput(editor) != null;
		
	}
	
	/**
	 * Converts the text selection provided by the given editor into an array of
	 * Java elements. If the selection doesn't cover a Java element and the selection's
	 * length is greater than 0 the methods returns the editor's input element.
	 */
//	public static IModelicaElement[] codeResolveOrInput(ModelicaEditor editor) throws CoreException {
//		IModelicaElement input= getInput(editor);
//		ITextSelection selection= (ITextSelection)editor.getSelectionProvider().getSelection();
//		IModelicaElement[] result= codeResolve(input, selection);
//		if (result.length == 0) {
//			result= new IModelicaElement[] {input};
//		}
//		return result;
//	}
	
//	public static IModelicaElement[] codeResolveOrInputHandled(ModelicaEditor editor, Shell shell, String title) {
//		try {
//			return codeResolveOrInput(editor);
//		} catch(CoreException e) {
//			e.printStackTrace();
//			System.err.println("Shell:" + shell.toString() + "Title:" + title); 
//		}
//		return null;
//	}
	
	/**
	 * Converts the text selection provided by the given editor a Java element by
	 * asking the user if code reolve returned more than one result. If the selection 
	 * doesn't cover a Java element and the selection's length is greater than 0 the 
	 * methods returns the editor's input element.
	 */
//	public static IModelicaElement codeResolveOrInput(ModelicaEditor editor, Shell shell, String title, String message) throws CoreException {
//		IModelicaElement[] elements= codeResolveOrInput(editor);
//		if (elements == null || elements.length == 0)
//			return null;
//		IModelicaElement candidate= elements[0];
//		return candidate;
//	}
	
//	public static IModelicaElement codeResolveOrInputHandled(ModelicaEditor editor, Shell shell, String title, String message) {
//		try {
//			return codeResolveOrInput(editor, shell, title, message);
//		} catch(CoreException e) {
//			e.printStackTrace();
//			System.err.println("Shell:" + shell.toString() + "Title:" + title); 
//		}
//		return null;
//	}
//		
	public static IModelicaElement[] codeResolve(ModelicaEditor editor) throws CoreException {
		return codeResolve(getInput(editor), (ITextSelection)editor.getSelectionProvider().getSelection());
	}

	/**
	 * Converts the text selection provided by the given editor a Java element by
	 * asking the user if code reolve returned more than one result. If the selection 
	 * doesn't cover a Java element <code>null</code> is returned.
	 */
	public static IModelicaElement codeResolve(ModelicaEditor editor, Shell shell, String title, String message) throws CoreException {
		IModelicaElement[] elements= codeResolve(editor);
		if (elements == null || elements.length == 0)
			return null;
		IModelicaElement candidate= elements[0];
		return candidate;
	}
	
	public static IModelicaElement[] codeResolveHandled(ModelicaEditor editor, Shell shell, String title) {
		try {
			return codeResolve(editor);
		} catch(CoreException e) {
			e.printStackTrace();
			System.err.println("Shell:" + shell.toString() + "Title:" + title); 
		}
		return null;
	}
	
	public static IModelicaElement getElementAtOffset(ModelicaEditor editor) throws CoreException {
		return getElementAtOffset(getInput(editor), (ITextSelection)editor.getSelectionProvider().getSelection());
	}
		
	public static IModelicaElement getInput(ModelicaEditor editor) {
		if (editor == null)
			return null;
		IEditorInput input= editor.getEditorInput();
		if (!(input instanceof ModelicaElementEditorInput))
		{
			return null;
		}
		
		return ((ModelicaElementEditorInput) input).getSourceFile();
	}
	
	public static IModelicaElement[] codeResolve(IModelicaElement input, ITextSelection selection) throws CoreException {
//			if (input instanceof ICodeAssist) {
//				if (input instanceof IModelicaSourceFile) {
//					JavaModelUtil.reconcile((IModelicaSourceFile) input);
//				}
//				IModelicaElement[] elements= ((ICodeAssist)input).codeSelect(selection.getOffset(), selection.getLength());
//				if (elements != null && elements.length > 0)
//					return elements;
//			}
			return EMPTY_RESULT;
	}
//	
	public static IModelicaElement getElementAtOffset(IModelicaElement input, ITextSelection selection) throws CoreException {
//		if (input instanceof IModelicaSourceFile) {
//			IModelicaSourceFile cunit= (IModelicaSourceFile) input;
//			JavaModelUtil.reconcile(cunit);
//			IModelicaElement ref= cunit.getElementAt(selection.getOffset());
//			if (ref == null)
//				return input;
//			else
//				return ref;
//		} else if (input instanceof IClassFile) {
//			IModelicaElement ref= ((IClassFile)input).getElementAt(selection.getOffset());
//			if (ref == null)
//				return input;
//			else
//				return ref;
//		}
		return null;
	}
	
//	public static IModelicaElement[] resolveSelectedElements(IModelicaElement input, ITextSelection selection) throws CoreException {
//		IModelicaElement enclosing= resolveEnclosingElement(input, selection);
//		if (enclosing == null)
//			return EMPTY_RESULT;
//		if (!(enclosing instanceof ISourceReference))
//			return EMPTY_RESULT;
//		ISourceRange sr= ((ISourceReference)enclosing).getSourceRange();
//		if (selection.getOffset() == sr.getOffset() && selection.getLength() == sr.getLength())
//			return new IModelicaElement[] {enclosing};
//	}
	
//	public static IModelicaElement resolveEnclosingElement(ModelicaEditor editor, ITextSelection selection) throws CoreException {
//		return resolveEnclosingElement(getInput(editor), selection);
//	}
//	
//	public static IModelicaElement resolveEnclosingElement(IModelicaElement input, ITextSelection selection) throws CoreException {
//		IModelicaElement atOffset= null;
//		if (input instanceof IModelicaSourceFile) {
//			IModelicaSourceFile cunit= (IModelicaSourceFile)input;
//			JavaModelUtil.reconcile(cunit);
//			atOffset= cunit.getElementAt(selection.getOffset());
//		} else if (input instanceof IClassFile) {
//			IClassFile cfile= (IClassFile)input;
//			atOffset= cfile.getElementAt(selection.getOffset());
//		} else {
//			return null;
//		}
//		if (atOffset == null) {
//			return input;
//		} else {
//			int selectionEnd= selection.getOffset() + selection.getLength();
//			IModelicaElement result= atOffset;
//			if (atOffset instanceof ISourceReference) {
//				ISourceRange range= ((ISourceReference)atOffset).getSourceRange();
//				while (range.getOffset() + range.getLength() < selectionEnd) {
//					result= result.getParent();
//					if (! (result instanceof ISourceReference)) {
//						result= input;
//						break;
//					}
//					range= ((ISourceReference)result).getSourceRange();
//				}
//			}
//			return result;
//		}
//	}
}
