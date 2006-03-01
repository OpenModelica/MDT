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

package org.modelica.mdt.ui.editor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IParameter;
import org.modelica.mdt.core.IParent;
import org.modelica.mdt.core.ISignature;
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
 * This class is responsible for proposing completions and giving
 * context information about functions. These functions kick in when .
 * and ( are typed or when the user presses CTRL+SPACE.
 * 
 * computeCompletionProposals() takes care of proposing classnames when
 * typing after a .
 * 
 * computeContextInformation() takes care of showing the definitions of
 * the parameters of classes that have parameters.
 * 
 * @author Tyler Durden
 */
public class ModelicaCompletionProcessor implements IContentAssistProcessor
{

	private IEditorPart editor;
	
	private static String functionProposal = "";
	private static int functionNameStart = 0;
	private static int functionNameEnd = 0;
	
	public ModelicaCompletionProcessor(IEditorPart editor) 
	{
		this.editor = editor;
	}
	
	protected static class Validator implements IContextInformationValidator,
		IContextInformationPresenter
	{
		protected ITextViewer fViewer;
		protected int fInstallOffset;
		
		public boolean isContextInformationValid(int offset)
		{
			/* If we've backed over the (, we're done with this information. */
			if(offset - fInstallOffset < 0)
			{
				return false;
			}
			
			String stringTyped =
				ModelicaCompletionProcessor.getLine(fViewer, offset);
			
			/* Get start of parameter list. */
			int pos = stringTyped.indexOf('(');
			
			if(pos == -1)
			{
				return false;
			}
			
			/* Match parens to see if we're done typing. */
			int pardepth = 0;
			for(;pos < stringTyped.length();pos++)
			{
				if(stringTyped.charAt(pos) == '(')
					pardepth++;
				else if(stringTyped.charAt(pos) == ')')
					pardepth--;
			}
			
			/* The TYPING will go on as long as it has to! */
			/* (or somebody yells stop, goes limb, passes out) */
			if(pardepth == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		
		public void install(IContextInformation info, ITextViewer viewer,
				int offset)
		{
			fViewer = viewer;
			fInstallOffset = offset;
		}
		
		public boolean updatePresentation(int documentPosition,
				TextPresentation presentation)
		{
			/* Only apply the style range as we start displaying this
			 * information*/
			if(fInstallOffset != documentPosition)
			{
				return false;
			}
			
			StyleRange sr;
			sr = new StyleRange(functionNameStart, 
					functionNameEnd - functionNameStart, null, null, SWT.BOLD);

			presentation.addStyleRange(sr);
			
			return true;
		}
	}
	
	protected IContextInformationValidator validator = new Validator();
	
	/**
	 * calculates the prefix of possible class/package/component names
	 * at specified offset
	 * 
	 * @param viewer the container of the document
	 * @param offset the offset into the document where we're typing
	 * @return the prefix calculated
	 */
	private String getPrefix(IDocument doc, int offset)
	{
		/*
		 * look for characters that can not be part of a
		 * modelica element name such as ' ', '\t' and so on
		 * this is just a best guess attempt to figure out the
		 * prefix, actually
		 */
		try
		{
			int end = offset;
			char c;
			
			do
			{
				/* goto next character */
				offset--;
				if (offset < 0)
				{
					/* past the start of the document */
					break;
				}
				c = doc.getChar(offset);
			}
			while
			(	/* the characters we are looking for */
				c != '\n' && c != '\t' && c != ' ' && c != '(' && c != ';'
				&& c != ')');

			offset++; /* exclude the charachter we looked at lastly */
			return doc.get(offset, (end - offset));
		}
		catch (BadLocationException e)
		{
			ErrorManager.logBug(UIPlugin.getSymbolicName(),
					"illegal position encountered while calculating prefix");
		}
		return ""; /* this will happend only if our code is broken somehow */
	}

	//TODO this getLine() should be removed and some of the other 25 implementa-
	//tions of getLie() should be called instead. Some day we may get to a sin-
	//gle digit number of getLine() implementations

	private static String getLine(ITextViewer viewer, int offset)
	{
		IDocument document = viewer.getDocument();
		
		int tempCounter = offset;
		char ch = '\n';
		String foundString = "";
		
		do
		{
			try
			{
				ch = document.getChar(--tempCounter);
			}
			catch(BadLocationException e)
			{
				return "";
			}
			if(ch == '\n' || ch == '\r')
				break;
			else if(foundString.startsWith(functionProposal))
				break;
			else
				foundString = ch + foundString;
		}
		while(true);
		
		return foundString;
	}
	
	/**
	 * Compute completion proposals by finding a class name and send that to
	 * OMC to find out if it has something to propose.
	 * 
	 * @param viewer the holder of the document we're editing
	 * @param offset the offset into the document where we're editing
	 * @return the proposed completions given the contents of the document
	 */
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset)
	{
		
		IEditorInput input = editor.getEditorInput();
		
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
		
		LinkedList<ICompletionProposal> proposals = 
			new LinkedList<ICompletionProposal>();
		
		String prefix = getPrefix(viewer.getDocument(), offset);

		IModelicaSourceFile file = 
			((ModelicaElementEditorInput)input).getSourceFile();
		try
		{
			IModelicaClass clazz = file.getClassAt(offset);
			if (clazz != null)
			{
				computeCompPropsFromImports(clazz, prefix, offset, proposals);
				computeCompPropsFromStdLib(clazz, prefix, offset, proposals);
			}
			else
			{
				System.out.println("no class here");
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
		
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
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
	private void computeCompPropsFromStdLib(IModelicaClass clazz, String prefix,
			int offset, LinkedList<ICompletionProposal> proposals) 
		throws ConnectException, CompilerInstantiationException, 
			UnexpectedReplyException, InvocationError, CoreException
	{
		IStandardLibrary stdLib =
			ModelicaCore.getModelicaRoot().getStandardLibrary();
		
		for (IModelicaClass pkg : stdLib.getPackages())
		{
			computeCompPropsFromPackage(pkg, null, prefix, offset, proposals);
		}
	}

	/**
	 * compute completion proposals based in imports statments in the 
	 * provided class
	 * 
	 * @param clazz the class who's import statmets to use for computation
	 * @param proposals the container where the proposals will be added
	 */
	private void computeCompPropsFromImports(IModelicaClass clazz, 
			String prefix, int offset,
			Collection<ICompletionProposal> proposals) 
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		/*
		 * compute complete list of imports available in this class
		 * That is the list of all imports made in this class and 
		 * up to the top-level namespace 
		 */
		Collection<IModelicaImport> imports = new LinkedList<IModelicaImport>();
		IModelicaClass currClass = clazz;
		
		while (currClass != null)
		{
			imports.addAll(currClass.getImports());
			currClass = currClass.getParentNamespace();
		}
		
		for (IModelicaImport imp : imports)
		{
			IModelicaClass importedPackage = imp.getImportedPackage();
			switch (imp.getType())
			{
			case QUALIFIED:
				computeCompPropsFromPackage(importedPackage, null,
						prefix, offset, proposals);
				break;
			case UNQUALIFIED:
				for (IModelicaElement child : importedPackage.getChildren())
				{
					computeCompPropsFromPackage(((IModelicaClass)child), null,
							prefix, offset, proposals);
				}
				break;
			case RENAMING:
				computeCompPropsFromPackage(importedPackage, imp.getAlias(),
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
	private void computeCompPropsFromPackage(IModelicaClass importedPackage,
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
	 * computes the information that is displayed in the yellow pop-up
	 * as you type.
	 */
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset)
	{
		/* If the offset is 0, there is no prefix and there can be no
		 * context information */
		if(offset == 0)
		{
			return new ContextInformation[0];
		}
		
		IEditorInput input = editor.getEditorInput();
		
		if((input instanceof ModelicaElementEditorInput) == false)
		{
			ErrorManager.logBug(UIPlugin.getSymbolicName(),
					"Context information processor invoked on a non-modelica" +
					" content.");
			
			return new ContextInformation[0];
			
			// TODO see todo in computeCompletionProposals
		}
		
		String prefix = getPrefix(viewer.getDocument(), offset - 1);
		
		/* If the prefix ends with a dot, there is no function name that we
		 * can fetch parameters from */
		if(prefix.endsWith("."))
		{
			return new ContextInformation[0];
		}
		
		LinkedList<IContextInformation> information =
			new LinkedList<IContextInformation>();
		
		IModelicaSourceFile file = 
			((ModelicaElementEditorInput)input).getSourceFile();
		
		try
		{
			IModelicaClass modelicaClass = file.getClassAt(offset);
			if(modelicaClass != null)
			{
				computeContextInformationFromImports(modelicaClass, prefix,
						offset, information);
				computeContextInformationFromStdLib(modelicaClass, prefix,
						offset, information);
			}
			else
			{
				return new IContextInformation[0];
			}
		}
		catch(CoreException e)
		{
			ErrorManager.showCoreError(e);
		}
		catch(CompilerException e)
		{
			ErrorManager.showCompilerError(e);
		}
		
		return information.toArray(new IContextInformation[information.size()]);		
	}
	
	private void computeContextInformationFromStdLib(
			IModelicaClass modelicaClass, String prefix, int offset,
			LinkedList<IContextInformation> info)
		throws ConnectException, CompilerInstantiationException,
			UnexpectedReplyException, InvocationError, CoreException
	{
		IStandardLibrary stdLib = 
			ModelicaCore.getModelicaRoot().getStandardLibrary();
		
		for(IModelicaClass pkg : stdLib.getPackages())
		{
			computeContextInformationFromPackage(pkg, null, prefix, offset,
					info);
		}
	}

	private void computeContextInformationFromImports(
			IModelicaClass modelicaClass, String prefix, int offset,
			LinkedList<IContextInformation> info) 
		throws ConnectException,
			UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		Collection<IModelicaImport> imports = new LinkedList<IModelicaImport>();
		
		/* I wanted a for-loop and I got a for-loop. Hah! */
		for(IModelicaClass currClass = modelicaClass;
			currClass != null;
			currClass = currClass.getParentNamespace())
		{
			imports.addAll(currClass.getImports());
		}
		
		for(IModelicaImport imp : imports)
		{
			IModelicaClass importedPackage = imp.getImportedPackage();

			switch(imp.getType())
			{
			case QUALIFIED:
				computeContextInformationFromPackage(importedPackage, null,
						prefix, offset, info);
				break;
			case UNQUALIFIED:
				for(IModelicaElement child : importedPackage.getChildren())
				{
					computeContextInformationFromPackage((IModelicaClass)child,
							null, prefix, offset, info);
				}
				break;
			}
		}
	}
	
	private void computeContextInformationFromPackage(
			IModelicaClass importedPackage, String packageAlias, String prefix,
			int offset, Collection<IContextInformation> info) 
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		String packageName;
		
		if(packageAlias != null)
		{
			packageName = packageAlias;
		}
		else
		{
			packageName = importedPackage.getElementName();
		}

		int firstDot = prefix.indexOf('.');
		if(firstDot == -1)
		{
			
			if(!packageName.equals(prefix))
			{
				return;
			}

			String proposal = constructProposalString(importedPackage);
			
			if(info.size() == 0)
			{
				info.add(new ContextInformation(proposal, proposal));
				functionProposal = prefix;
			}
			return;
		}
		else if(packageName.equals(prefix.substring(0, firstDot)) == false)
		{
			return;
		}
		
		int lastDot = prefix.lastIndexOf('.');
		String levelName = prefix.substring(0, lastDot);
		
		String childPrefix = prefix.substring(lastDot + 1);
		
		StringTokenizer st = new StringTokenizer(levelName, ".");
		String token;
		
		IParent currLevel = (IParent)importedPackage;
		
		if(st.hasMoreTokens())
		{
			st.nextToken();
		}
		
		while(st.hasMoreTokens())
		{
			token = st.nextToken();
			boolean foundNextLevel = false;
			
			for(IModelicaElement child : currLevel.getChildren())
			{
				if(child.getElementName().equals(token))
				{
					foundNextLevel = true;
					if(child instanceof IParent)
					{
						currLevel = (IParent)child;
					}
					else
					{
						return;
					}
				}
			}
			
			if(foundNextLevel == false)
			{
				return;
			}
		}
		
		for(IModelicaElement element : currLevel.getChildren())
		{
			String elementName = element.getElementName();
			
			if(elementName.equals(childPrefix) == false)
			{
				continue;
			}

			if(element instanceof IModelicaClass)
			{
				String proposal = 
					constructProposalString((IModelicaClass)element);

				if(info.size() == 0)
				{
					info.add(new ContextInformation(proposal, proposal));
					functionProposal = prefix;
				}
			}
		}
	}
	
	private String constructProposalString(IModelicaClass modelicaClass)
		throws ConnectException, InvocationError, UnexpectedReplyException,
			CompilerInstantiationException, CoreException
	{
		ISignature signature = modelicaClass.getSignature();
		if(signature == null)
		{
			return "";
		}
		
		String proposal;
		
		IParameter[] outputs = signature.getOutputs();

		if(outputs.length >= 2)
		{
			proposal = "(";
			
			int i = 0;
			for(;i < outputs.length - 1;i++)
			{
				proposal += outputs[i].getType() + ", ";
			}
			
			proposal += outputs[i].getType() + ") ";
		}
		else if(outputs.length == 1)
		{
			proposal = outputs[0].getType() + " ";
		}
		else
		{
			proposal = "";
		}
		
		functionNameStart = proposal.length();
		
		proposal += modelicaClass.getElementName() + "(";
		
		functionNameEnd = proposal.length() - 1;
		
		IParameter[] inputs = signature.getInputs();
		if(inputs.length >= 1)
		{
			int i = 0;
			for(;i < inputs.length - 1;i++)
			{
				proposal += inputs[i].getType() + " "
					+ inputs[i].getName() + ", ";
			}
			proposal += inputs[i].getType() + " " + inputs[i].getName();
		}
		proposal += ")";

		return proposal;
	}
	
	public char[] getCompletionProposalAutoActivationCharacters()
	{
		return new char[] {'.'};
	}

	public char[] getContextInformationAutoActivationCharacters()
	{
		return new char[] {'('};
	}

	public String getErrorMessage()
	{
		return null;
	}

	public IContextInformationValidator getContextInformationValidator()
	{
		return validator;
	}
}
