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
import java.util.Vector;

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
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.List;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.ElementsInfo;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.ModelicaParser;
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
 * the parameters of classes that have parameters.
 * 
 * @author Tyler Durden
 */
public class ModelicaCompletionProcessor implements IContentAssistProcessor
{
//	/*
//	 * There is a separate narrowedProposals so that we can store the original
//	 * proposals. This allows us to backtrace the proposals.
//	 */
//	private List narrowedProposals = new List();
//	private List proposals = new List();
//	private int typeAhead = 0;

	private Vector<String> inputParameters = new Vector<String>();
	private Vector<String> outputParameters = new Vector<String>();
	private IEditorPart editor;
	
	private static String functionProposal;
	
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
			return false;
		}
	}
	
	protected IContextInformationValidator validator = new Validator();
	
//	/**
//	 * This method calls OMC and tries to get the classes defined in the
//	 * class name found in the document we're editing.
//	 * 
//	 * @param viewer where the document is stored
//	 * @param offset offset to where in the document we've typed a .
//	 */
//	private void newProposals(String className)
//	{
//		if(proposals != null)
//		{
//			proposals.clear();
//		}
//		else
//		{
//			proposals = new List();
//		}
//		if(narrowedProposals != null)
//		{
//			narrowedProposals.clear();
//		}
//		else
//		{
//			narrowedProposals = new List();
//		}
//		
//		typeAhead = 0;
//
//		try
//		{
//			proposals = CompilerProxy.getClassNames(className);
//		}
//		catch (CompilerException e)
//		{
//			/* 
//			 * if there were errors fetching classnames, report
//			 * error and leave proposals empty
//			 */
//			ErrorManager.showCompilerError(e);
//		}
//		finally
//		{
//			if(proposals != null)
//			{
//				narrowedProposals.addAll(proposals);
//			}
//		}
//	}
	
//	/**
//	 * Update the completion proposals by only adding the class names that
//	 * match the currently typed prefix of a class.
//	 * 
//	 * @param className the prefix of a proper class name
//	 */
//	private void updateProposals(String className)
//	{
//		String classPrefix = className.substring(className.lastIndexOf('.') + 1);
//		
//		typeAhead = classPrefix.length();
//		
//		narrowedProposals.clear();
//		
//		for (ListElement proposedClass : proposals)
//		{
//			if (((Element)proposedClass).toString().startsWith(classPrefix))
//			{
//				narrowedProposals.append(proposedClass);
//			}
//		}
//	}
//	
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
//			for (char ch )
//			do
//			{
//				ch = doc.getChar(offset);
//				System.out.println(offset + " '" + ch + "'");
//			}
//			while (offset >= 0 &&
//					/* the characters we are looking for */
//					ch != '\n' && ch != '\t' && ch != ' ' && ch != '(');
//
			offset++; /* exclude the charachter we looked at lastly */
			return doc.get(offset, (end - offset));
		}
		catch (BadLocationException e)
		{
			ErrorManager.logBug(UIPlugin.getSymbolicName(),
					"illegal position encountered while calculating prefix");
		}
		return "omg"; /* this will happend only if our code is broken somehow */
	}
	
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
	
	private void fetchParameters(String functionName)
	{
		inputParameters.clear();
		outputParameters.clear();
		
		Collection<ElementsInfo> elementsInfo;
		
		try 
		{
			elementsInfo = CompilerProxy.getElementsInfo(functionName);
		}
		catch (ConnectException e)
		{
			ErrorManager.showCompilerError(e);
			return;
		}
		catch (CompilerException e)
		{
			ErrorManager.logError(e);
			return;
		}

		functionProposal = functionName;
		
		for(ElementsInfo info : elementsInfo)
		{
			boolean elementIsPublic = false;
			boolean elementIsComponent = false;
			boolean elementIsInput = false;
			boolean elementIsOutput = false;
			boolean elementNamesFound = false;
			String name = null;
			String typename = "";
			String direction = null;			
			
			elementIsPublic = info.getElementVisibility().equals("public");
			elementIsComponent = info.getElementType().equals("component");

			if (elementIsComponent)
			{
				List comp = ModelicaParser.parseList(info.getNames());
				
				name = comp.elementAt(0).toString();
				elementNamesFound = true;
			}
			typename = info.getTypeName();
			
			direction = info.getDirection();
	
			if (direction != null)
			{
				elementIsInput = direction.equals("input");
				elementIsOutput = direction.equals("output");
			}
			
			if(elementIsPublic && elementIsComponent
					&& elementIsInput && elementNamesFound)
			{
				inputParameters.add(typename + " " + name);
			}
			else if(elementIsPublic && elementIsComponent
					&& elementIsOutput && elementNamesFound)
			{
				outputParameters.add(typename + " " + name);
			}
		}
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
		LinkedList<ICompletionProposal> proposals = 
			new LinkedList<ICompletionProposal>();
		
		String prefix = getPrefix(viewer.getDocument(), offset);

		//TODO when eclipse restores the files that where left
		// in the editor area when it was closed last time the
		// editor input will not be of ModelicaElementEditorInput
		// thus code completion will not work, this should be fixed somehow
		// check out org.eclipse.ui.IEditorInput.getPersistable() method 

		if (input instanceof ModelicaElementEditorInput)
		{
			IModelicaSourceFile file = 
				((ModelicaElementEditorInput)input).getSourceFile();
			System.out.println("we are looking at " + file);
			try
			{
				IModelicaClass clazz = file.getClassAt(offset);
				if (clazz != null)
				{
					System.out.println("class def is " + clazz.getElementName());
					computeCompPropsFromImports(clazz, prefix, offset, proposals);
				}
				else
				{
					System.out.println("no class here");
				}
			}
			catch (Exception e)
			{
				System.out.println(e);
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("not an instance");
		}
		
//		String className = findClassName(viewer, offset);
//		
//		if (className == null || className.equals(""))
//		{
//			/* bail out without any proposals */
//			return new ICompletionProposal[0];
//		}
//		
//		if(className.charAt(className.length() - 1) == '.')
//		{
//			/*
//			 * If the last character is a dot (.) we should fetch new proposals.
//			 * We do a substring to get rid of the dot at the end.
//			 */
//			newProposals(className.substring(0, className.length()-1));
//		}
//		else
//		{
//			/* 
//			 * Else we should narrow the proposals by only proposing the class
//			 * names that has a prefix matching that of the typed class name.
//			 */
//			updateProposals(className);
//		}
//		
//		/* Create 'real' completion proposals out of our proposals. */
//		ICompletionProposal[] completionProposals = 
//			new ICompletionProposal[narrowedProposals.size()];
//		for(int i = 0;i < narrowedProposals.size();i++)
//		{
//			String proposal = narrowedProposals.elementAt(i).toString();
//			completionProposals[i] =
//				new CompletionProposal(proposal, offset - typeAhead, typeAhead,
//						proposal.length(), null, proposal, null, null);
//		}
//
//		return completionProposals;
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	/**
	 * compute completion proposals based in imports statments in the provided class
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
			IModelicaClass imported = imp.getImportedPackage();

			switch (imp.getType())
			{
			case QUALIFIED:
				computeCompPropsFromPackage(imp.getImportedPackage(),
						prefix, offset, proposals);
				break;
			case SINGLE_DEFINITION:
				break;
			case UNQUALIFIED:
				break;
			case RENAMING:
				break;
			}
		}
	}

	private void computeCompPropsFromPackage(IModelicaClass importedPackage,
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
		String packageName = importedPackage.getElementName();

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
		
		StringTokenizer st = new StringTokenizer(prefix, ".");
		String token;
		
		while (st.hasMoreTokens())
		{
			token = st.nextToken();
			System.out.println("tonek: " + token);
		}
		
//		for (IModelicaElement element : importedPackage.getChildren())
//		{
//			String name = element.getFullName();
//			proposals.add(new CompletionProposal(name, 0, name.length(), 0));
//		}
	}

	/**
	 * computes the information that is displayed in the yellow pop-up
	 * as you type.
	 */
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset)
	{
		String className = getPrefix(viewer.getDocument(), offset);

		fetchParameters(className.substring(0, className.length()-1));
		
		String proposal = "";
		
		if(inputParameters.size() > 0)
		{
			proposal += "Input: ";
			for(String s : inputParameters)
			{
				proposal += s + ", ";
			}
		}
		if(outputParameters.size() > 0)
		{
			proposal += "Output: ";
			for(int i = 0;i < outputParameters.size();i++)
			{
				if(i + 1 < outputParameters.size())
					proposal += outputParameters.get(i) + ", ";
				else
					proposal += outputParameters.get(i);
			}
		}
		
		if(proposal == "")
			return null;
		
		IContextInformation[] result = new IContextInformation[1];
		
		result[0] = new ContextInformation("Function proposal", proposal);
		
		return result;
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
