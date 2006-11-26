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
 * the parameters of classes that have parameters. Shows this information
 * after ( has been typed.
 * 
 * @author Tyler Durden
 */
public class ModelicaCompletionProcessor implements IContentAssistProcessor
{
	private IEditorPart editor;
	
	private static String functionProposal = "";
	
	/* Remember where in the context information the name of the function is.
	 * This is used to highlight the function name in the context information.*/
	private static int functionNameStart = 0;
	private static int functionNameEnd = 0;
	
	public ModelicaCompletionProcessor(IEditorPart editor) 
	{
		this.editor = editor;
	}
	
	/*
	 * This Validator makes sure that the context information should still be
	 * visible. 
	 */
	protected static class Validator implements IContextInformationValidator,
		IContextInformationPresenter
	{
		protected ITextViewer fViewer;
		protected int fInstallOffset;
		
		/**
		 * Check if the context information should still be displayed. Do this
		 * by counting opening '(' and closing ')' parens and see if they match
		 * up.
		 */
		public boolean isContextInformationValid(int offset)
		{
			/* If we've backed over the (, we're done with this information. */
			if(offset - fInstallOffset < 0)
			{
				return false;
			}
			
			String stringTyped =
				ModelicaCompletionProcessor.getTypedString(fViewer, offset);
			
			/* Get start of parameter list. */
			int pos = stringTyped.indexOf('(');
			
			/* If no ( was found, something is wrong and the context information
			 * should not be displayed. */
			if(pos == -1)
			{
				return false;
			}
			
			/* Match parens to see if we're done typing. */
			int pardepth = 0;
			for(;pos < stringTyped.length();pos++)
			{
				if(stringTyped.charAt(pos) == '(')
				{
					pardepth++;
				}
				else if(stringTyped.charAt(pos) == ')')
				{
					pardepth--;
				}
			}
			
			/* The TYPING will go on as long as it has to! */
			/* (or somebody yells stop, goes limb, passes out) */
			if(pardepth == 0)
			{
				return false;
			}
			else if(pardepth < 0)
			{
				/* This is a bug */
				
				ErrorManager.logBug(UIPlugin.getSymbolicName(),
						"The user typed too many ) and we don't even noticed.");
				
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
		
		/**
		 * Whenever the user types a character, the presentation can be updated.
		 * We just update the presentation at the start, by formatting the
		 * function name as BOLD.
		 */
		public boolean updatePresentation(int documentPosition,
				TextPresentation presentation)
		{
			/* Only apply the style range as we start displaying this
			 * information. */
			if(fInstallOffset != documentPosition)
			{
				return false;
			}
			
			//StyleRange x = presentation.getDefaultStyleRange();

			presentation.addStyleRange(new StyleRange(functionNameStart, 
					functionNameEnd - functionNameStart, 
					null, null, //x.foreground, x.background, 
					SWT.BOLD));
			
			return true;
		}
	}
	
	protected IContextInformationValidator validator = new Validator();
	
	/**
	 * Calculates the prefix of possible class/package/component names
	 * at specified offset.
	 * 
	 * @param viewer the container of the document
	 * @param offset the offset into the document where we're typing
	 * @return the prefix calculated
	 */
	protected static String getPrefix(IDocument doc, int offset)
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

	/**
	 * Fetches the string that has been typed since the start of the function
	 * name. For example if the user has typed:
	 * 
	 * x = Modelica.Math.sin(a
	 * 
	 * then this function will return
	 * 
	 * Modelica.Math.sin(a
	 * 
	 */
	private static String getTypedString(ITextViewer viewer, int offset)
	{
		IDocument document = viewer.getDocument();
		
		int tempCounter = offset;
		char ch = '\n';
		String foundString = "";

		while(true)
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
		
		CompletionProposalsGenerator propGen =
			new CompletionProposalsGenerator(viewer, editor, offset);
		
		return propGen.getProposals();
	}

	
	/**
	 * Computes the information that is displayed in the yellow pop-up
	 * as you type.
	 * 
	 * @param viewer the holder of the document we're editing
	 * @param offset the offset into the document where we're editing
	 * @return the context information given the function name that has been
	 *         typed
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
			/*
			 * we should never be attached to an editor on anything else
			 * but a modelica source file (a modelica element)
			 */
			ErrorManager.logBug(UIPlugin.getSymbolicName(),
					"Context information prossesor invoked on a non-Modelica " +
					"content");
			
			/* no context information if there is a bug ! */
			return new ContextInformation[0];
		}
		
		
		/* get prefix from offset-1 to skip the first ( */
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
			for (IModelicaElement pkg : file.getChildren())
			if (pkg instanceof IModelicaClass)
			for (IModelicaElement element : ((IModelicaClass)pkg).getChildren())
			{
				if(element instanceof IModelicaClass && 
				   //(element.getElementName().startsWith(prefix) ||
					element.getElementName().equals(prefix))
				{
					String proposal = 
						constructProposalString((IModelicaClass)element);
	
					if(information.size() == 0)
					{
						information.add(new ContextInformation(proposal, proposal));
						/* save what the user typed to get this context
						 * information */
						functionProposal = prefix;
					}
				}
			}
			/* Fetch information about what class we're typing inside */
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

	/**
	 * Compute context information based on import statements in the provided
	 * class.
	 * 
	 * @param modelicaClass the class who's import statements will be used
	 * for computing context information
	 * @param prefix
	 * @param offset the offset into the document where we're typing
	 * @param info the container where context info will be added
	 */
	private void computeContextInformationFromImports(
			IModelicaClass modelicaClass, String prefix, int offset,
			LinkedList<IContextInformation> info) 
		throws ConnectException,
			UnexpectedReplyException, InvocationError,
			CompilerInstantiationException, CoreException
	{
		Collection<IModelicaImport> imports = new LinkedList<IModelicaImport>();
		
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
				
			case RENAMING:
				computeContextInformationFromPackage(importedPackage,
						imp.getAlias(),	prefix, offset, info);
				break;
			}
		}
	}
	
	/**
	 * Compute context information based on some package. This method bases
	 * the computation on the imported package, the package alias if available
	 * (renaming import), and the prefix before the current cursor position.
	 * 
	 * Context information is added to the provided list 'info'. Currently, only
	 * one piece of context information will be added to the list.
	 * 
	 * @param importedPackage the package that is imported
	 * @param packageAlias the alias of the package (renaming import)
	 * @param prefix the prefix before current cursor position
	 * @param offset the current cursor position in the document
	 * @param info the list of context information where the context information
	 * will be added
	 */
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
		/* Make sure the first part of the prefix matches this package, else 
		 * return */
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
		
		/* skip first token */
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
					/* we have found the next level */
					foundNextLevel = true;
					if(child instanceof IParent)
					{
						currLevel = (IParent)child;
					}
					else
					{
						/* Level name must refer to an instance of an IParent,
						 * otherwise we don't know what the user is typing and
						 * can't contribute any context information, so just
						 * escape from this method. */
						return;
					}
				}
			}
			
			if(foundNextLevel == false)
			{
				/* Level name doesn't refer to any of this packages children,
				 * nothing to contribute */
				return;
			}
		}
		
		/*
		 * Compute context information based on the current level and child 
		 * prefix
		 */
		for(IModelicaElement element : currLevel.getChildren())
		{
			String elementName = element.getElementName();
			
			/* The child should match exactly to be of interest for context
			 * information */
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
					
					/* save what the user typed to get this context
					 * information */
					functionProposal = prefix;
				}
			}
		}
	}
	
	/**
	 * Compute context information based on the packages in the standard library
	 * which can be accessed globally.
	 * 
	 * @param modelicaClass the class where the context information is requested
	 * @param prefix
	 * @param offset the offset into the document where we're typing
	 * @param info the container where context info will be added
	 */
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

	
	/**
	 * Constructs a textual representation of a functions signature.
	 *  
	 * @param modelicaClass the class that will have its input and output
	 * parameters displayed
	 * @return the constructed <code>String</code>
	 */
	private String constructProposalString(IModelicaClass modelicaClass)
		throws ConnectException, InvocationError, UnexpectedReplyException,
			CompilerInstantiationException, CoreException
	{
		ISignature signature = modelicaClass.getSignature();
		if(signature == null)
		{
			return "";
		}
		
		/*
		 * If a function has several return values it will look like this:
		 * 
		 *  (Real, Integer) foo(Real a, Real b)
		 *  
		 * if a function only has one return value it will look like this:
		 * 
		 *  Real bar(Integer a)
		 */
		
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
	
	/**
	 * @return the character that should start completion proposal. For MDT
	 * this character is .
	 */
	public char[] getCompletionProposalAutoActivationCharacters()
	{
		return new char[] {'.'};
	}

	/**
	 * @return the character that should start context information. For MDT
	 * this character is )
	 */
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
