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

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IParent;
import org.modelica.mdt.core.IStandardLibrary;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.ui.ModelicaImages;
import org.modelica.mdt.ui.UIPlugin;

/**
 * An object of this class handles generation of completion proposals
 * based on contents of text viewer and offset.
 * 
 * To generate proposals create a new object with reference to
 * editor and offset, call getProposals() to get a list of
 * completion proposals.
 */
public class CompletionProposalsGenerator
{
	private IEditorInput input;
	private ITextViewer viewer;
	private int offset;
	private String prefix;
	
	private LinkedList<ICompletionProposal> proposals = 
		new LinkedList<ICompletionProposal>();
	
	
	public CompletionProposalsGenerator(ITextViewer viewer, 
			IEditorPart editor, int offset)
	{
		this.viewer = viewer;
		this.input = editor.getEditorInput();
		this.offset = offset;
	}
	
	public ICompletionProposal[] getProposals()
	{
		if (!(input instanceof ModelicaElementEditorInput))
		{
			/*
			 * we should never be attached to an editor on anything else
			 * but a modelica source file (a modelica element)
			 */
			ErrorManager.logBug(UIPlugin.getSymbolicName(),
					"completion prossesor invoked on a non-modelica content");
			/* no completion if there is a bug ! */
			return new ICompletionProposal[0];
		}
		
		
		prefix = 
			ModelicaCompletionProcessor.getPrefix(viewer.getDocument(), offset);

		IModelicaSourceFile file = 
			((ModelicaElementEditorInput)input).getSourceFile();
		try
		{
			IModelicaClass clazz = file.getClassAt(offset);
			/* only do proposals inside class definitions */
			if (clazz != null)
			{
				startPropsGeneration(clazz);
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
		
		sortProposals();
		
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	private void sortProposals()
	{
		Collections.sort(proposals, new Comparator<ICompletionProposal>()
		{
			public int compare(ICompletionProposal arg0, 
					ICompletionProposal arg1)
			{
				return 
					arg0.getDisplayString().compareToIgnoreCase(arg1.getDisplayString());
			}
		});
		
	}

	private void startPropsGeneration(IModelicaClass clazz) 
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		/*
		 * simulate the modelica name lookup process
		 * and add all possible names with respect to current
		 * prefix
		 */
		boolean lastClassEncapsulated = false;
		boolean onlyConstantVariables = false;
		while (clazz != null)
		{
			 /* compute proposals in current scope */
			computePropsIn(clazz, onlyConstantVariables);
			/* 
			 * include non constant variables only on first iteration,
			 * that is only in the inner most scope
			 */
			onlyConstantVariables = true;
			/* move on to next scope unless we are encapsulated */
			if (!clazz.isEncapsulated())
			{
				clazz = clazz.getParentNamespace();
			}
			else
			{
				/* abort completion generation on encapsulated class */
				lastClassEncapsulated = true;
				break;
			}
		}
		
		if (!lastClassEncapsulated)
		{
			/* 
			 * add classes from the global unnamed scope
			 * if we have not bumped into encapsulated class
			 */
			computeCompPropsFromStdLib();
			//TODO we should also compute proposals from top-level
			// packages in this project
		}
	}

	/**
	 * compute propositions in the provided class' scope
	 * 
	 * @param clazz
	 * @param onlyConstantVariables
	 */
	private void computePropsIn(IModelicaClass clazz, 
			boolean onlyConstantVariables) 
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		computePropsFromElements(clazz, onlyConstantVariables);
		computeCompPropsFromImports(clazz);
	}

	private void computePropsFromElements(IModelicaClass clazz, 
			boolean onlyConstantVariables)
		throws ConnectException, UnexpectedReplyException, InvocationError, 
			CompilerInstantiationException, CoreException
	{
		String elementName;
		for (IModelicaElement element : clazz.getChildren())
		{
			elementName = element.getElementName();
			//TODO we should also filter out element based on onlyCOnstantVariables flag
			if (elementName.startsWith(prefix))
			{
				proposals.add(new CompletionProposal(elementName,
						offset-prefix.length(),
						prefix.length(),
						elementName.length(),
						ModelicaImages.get
							(ModelicaImages.getModelicaElementKey(element)),
							elementName, 
						null, null));
			}
		}
	}

	/**
	 * compute completion proposals based in imports statments in the 
	 * provided class
	 * 
	 * @param clazz the class who's import statmets to use for computation
	 * @param proposals the container where the proposals will be added
	 */
	private void computeCompPropsFromImports(IModelicaClass clazz) 
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		for (IModelicaImport imp : clazz.getImports())
		{
			IModelicaClass importedPackage = imp.getImportedPackage();
			switch (imp.getType())
			{
			case QUALIFIED:
				computeProposalsFromPackage(importedPackage, null,
						prefix, offset, proposals);
				break;
			case UNQUALIFIED:
				for (IModelicaElement child : importedPackage.getChildren())
				{
					computeProposalsFromPackage(((IModelicaClass)child), null,
							prefix, offset, proposals);
				}
				break;
			case RENAMING:
				computeProposalsFromPackage(importedPackage, imp.getAlias(),
						prefix, offset, proposals);				
				break;
			}
		}
	}

	/**
	 * Compute proposals based on an imported package. This method
	 * bases the computation on package, packages alias if available,
	 * the prefix before current cursor position.
	 * 
	 * Proposals are added to provided list 'proposals' list. Given offset
	 * is used to create completion propopal object with correct replacement
	 * data.  
	 * 
	 * @param importedPackage the package that is imported
	 * @param packageAlias package's alias or null if package have no alias
	 * @param prefix the prefix before current cursor position
	 * @param offset the current cursor position in the document
	 * @param proposals the computed proposals will be added to this list
	 */
	private void computeProposalsFromPackage(IModelicaClass importedPackage,
			String packageAlias,
			String prefix, int offset,
			Collection<ICompletionProposal> proposals) 
		throws ConnectException, UnexpectedReplyException, InvocationError, 
			CompilerInstantiationException, CoreException
	{
		/*
		 * check with regards to prefix if it is possible that user
		 * wants to type name from this package.
		 */
		int firstDot = prefix.indexOf('.');
		String packageName;
		
		if (packageAlias != null)
		{
			packageName = packageAlias;
		}
		else
		{
			packageName = importedPackage.getElementName();
		}

		if (firstDot == -1)
		{
			/* 
			 * there is not dots in the currnet prefix
			 *
			 * prefix must match the begining of our name otherwise
			 * the user is typing something else
			 */
			if (!packageName.startsWith(prefix))
			{
				/* 
				 * user is typing something else, 
				 * don't contribute any proposals 
				 */
				return;
			}
			
			/*
			 * if there are no dots in the prefix then we can
			 * only propose our own name
			 */ 
			proposals.add(new CompletionProposal(packageName,
					offset-prefix.length(),
					prefix.length(),
					packageName.length(),
					ModelicaImages.get
						(ModelicaImages.getModelicaElementKey(importedPackage)),
					packageName, 
					null, null));
			return;
		}
		else
		{
			/* 
			 * the is dot in the prefix, name of this package must match
			 * with the prefixe's first part before the dot
			 */
			if (!packageName.equals(prefix.substring(0, firstDot)))
			{
				/* 
				 * user is typing something else, 
				 * don't contribute any proposals 
				 */
				return;
			}
		}

		int lastDot = prefix.lastIndexOf('.');
		String levelName = 
			prefix.substring(0, lastDot);

		/* child prefix is begins past last dot */
		String childPrefix =
			prefix.substring(lastDot + 1);
		
		StringTokenizer st = new StringTokenizer(levelName, ".");
		String token;
				
		IParent currLevel = (IParent)importedPackage;
		
		/* skip first token */
		if (st.hasMoreTokens())
		{
			st.nextToken();
		}
		while (st.hasMoreTokens())
		{
			token = st.nextToken();
			boolean foundNextLevel = false;

			for (IModelicaElement child : currLevel.getChildren())
			{
				if ((child instanceof IModelicaFile) ||
					(child instanceof IModelicaSourceFile) ||
					(child instanceof IModelicaFolder))
				{
					/* don't propose files, source files and folders */
					continue;
				}
				if (child.getElementName().equals(token))
				{
					foundNextLevel = true;
					/* we have found our next elemnt */
					if (child instanceof IParent)
					{
						currLevel = (IParent)child;
					}
					else
					{
						/* 
						 * level name must refere to an instance
						 * of a IParent, otherwise we don't know
						 * what the user is typing and cannot contribute
						 * any completions
						 */
						return; /* bail out */
					}
				}
			}
			
			if (!foundNextLevel)
			{
				/* 
				 * level name does not refere to any of this packages children,
				 * nothing to contribute
				 */
				return;
			}
		}
		
		/* 
		 * compute contribution based on current level and
		 * child prefix
		 */
		for (IModelicaElement element : currLevel.getChildren())
		{
			String elementName = element.getElementName();
			
			
			/* filter out children by type */ 
			if ((element instanceof IModelicaFile) ||
					(element instanceof IModelicaSourceFile) ||
					(element instanceof IModelicaFolder))
			{
					/* don't propose files, source files and folders */
					continue;
			}

			/* filter out children by child prefix */
			if (!elementName.startsWith(childPrefix))
			{
				/* don't propose children with non-matching prefix */
				continue;
			}
			
			proposals.add(new CompletionProposal(elementName,
					offset - childPrefix.length(),
					childPrefix.length(),
					elementName.length(), 
					ModelicaImages.get
						(ModelicaImages.getModelicaElementKey(element)),
					elementName, 
					null, null));
		}
	}
	
	/**
	 * Compute proposals based on the packages of the standard library, that
	 * can be refered globaly.
	 * 
	 * @param clazz the class where the proposals are made
	 * @param prefix
	 * @param offset
	 * @param proposals the container where the proposals will be added
	 */
	private void computeCompPropsFromStdLib() 
		throws ConnectException, CompilerInstantiationException, 
			UnexpectedReplyException, InvocationError, CoreException
	{
		IStandardLibrary stdLib =
			ModelicaCore.getModelicaRoot().getStandardLibrary();
		
		for (IModelicaClass pkg : stdLib.getPackages())
		{
			computeProposalsFromPackage(pkg, null, prefix, offset, proposals);
		}
	}



}
