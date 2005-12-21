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

import java.util.Vector;

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
import org.modelica.mdt.ErrorManager;
import org.modelica.mdt.compiler.CompilerException;
import org.modelica.mdt.compiler.ConnectException;
import org.modelica.mdt.compiler.ModelicaParser;
import org.modelica.mdt.core.CompilerProxy;


/**
 * This class is responsible for proposing completions and giving
 * context information about functions. These functions kick in when .
 * and ( are typed.
 * 
 * computeCompletionProposals() takes care of proposing classnames when
 * typing after a .
 * 
 * computeContextInformation() takes care of showing the definitions of
 * the parameters of classes that have parameters.
 * 
 * @author Andreas Remar
 */
public class ModelicaCompletionProcessor implements IContentAssistProcessor
{
	/*
	 * There is a separate narrowedProposals so that we can store the original
	 * proposals. This allows us to backtrace the proposals.
	 */
	Vector<Object> narrowedProposals = new Vector<Object>();
	Vector<Object> proposals = new Vector<Object>();
	int typeAhead = 0;

	Vector<String> inputParameters = new Vector<String>();
	Vector<String> outputParameters = new Vector<String>();
	
	/* This string is used to hold the proposed parameters of a function. */
	static String functionParameters;
	
	static String functionProposal;
	
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
			if(pardepth == 0)
				return false;
			else
				return true;
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
	
	/**
	 * This method calls OMC and tries to get the classes defined in the
	 * class name found in the document we're editing.
	 * 
	 * @param viewer where the document is stored
	 * @param offset offset to where in the document we've typed a .
	 */
	private void newProposals(String className)
	{
		if(proposals != null)
			proposals.clear();
		else
			proposals = new Vector<Object>();
		if(narrowedProposals != null)
			narrowedProposals.clear();
		else
			narrowedProposals = new Vector<Object>();
		
		typeAhead = 0;

		try
		{
			proposals = CompilerProxy.getClassNames(className);
		}
		catch (CompilerException e)
		{
			/* 
			 * if there were errors fetching classnames, report
			 * error and leave proposals empty
			 */
			ErrorManager.showCompilerError(e);
		}
		finally
		{
			if(proposals != null)
				narrowedProposals.addAll(proposals);
		}
	}
	
	/**
	 * Update the completion proposals by only adding the class names that
	 * match the currently typed prefix of a class.
	 * 
	 * @param className the prefix of a proper class name
	 */
	private void updateProposals(String className)
	{
		String classPrefix = className.substring(className.lastIndexOf('.') + 1);
		
		typeAhead = classPrefix.length();
		
		narrowedProposals.clear();
		
		for(Object proposedClass : proposals)
		{
			if(((String)proposedClass).startsWith(classPrefix))
			{
				narrowedProposals.add(proposedClass);
			}
		}
	}
	
	/**
	 * Tries to find a class name in the document before the current
	 * cursor offset (where we're typing).
	 * 
	 * @param viewer the container of the document
	 * @param offset the offset into the document where we're typing
	 * @return the class name found
	 */
	private static String findClassName(ITextViewer viewer, int offset)
	{
		int tempCounter = offset;
		String className = "";
		char ch = '\n';
		do
		{
			try
			{
				ch = viewer.getDocument().getChar(--tempCounter);
			}
			catch(BadLocationException e)
			{
			}
			if(ch == '\n' || ch == '\t' || ch == ' '
				|| (ch == '(' && tempCounter < offset - 1))
				break;
			else
				className = ch + className;
		}
		while(true);
		
		return className;
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
		
		Vector<Object> elementsInfo = new Vector<Object>(0);
		
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
		
		for(Object o : elementsInfo)
		{
			boolean elementIsPublic = false;
			boolean elementIsComponent = false;
			boolean elementIsInput = false;
			boolean elementIsOutput = false;
			boolean elementNamesFound = false;
			boolean typenameFound = false;
			Vector names = new Vector(0);
			String typename = "";
			
			if(o instanceof Vector)
			{
				for(Object o2 : (Vector)o)
				{
					if(o2 instanceof String)
					{
						String s = (String)o2;
						
						if(s.startsWith("elementvisibility="))
						{
							if(s.substring("elementvisibility=".length()).equals("public"))
							{
								elementIsPublic = true;
							}							
						}
						else if(s.startsWith("elementtype="))
						{
							if(s.substring("elementtype=".length()).equals("component"))
							{
								elementIsComponent = true;
							}
						}
						else if(s.startsWith("direction="))
						{
							if(s.substring("direction=".length()).contains("input"))
							{
								elementIsInput = true;
							}
							else if(s.substring("direction=".length()).contains("output"))
							{
								elementIsOutput = true;
							}
						}
						else if(s.startsWith("names="))
						{
							names = ModelicaParser.parseList(
									s.substring("names=".length()));
							elementNamesFound = true;
						}
						else if(s.startsWith("typename="))
						{
							typename = s.substring("typename=".length());
							typenameFound = true;
						}
					}
				}
			}
			if(elementIsPublic && elementIsComponent
					&& elementIsInput && elementNamesFound && typenameFound)
			{
				inputParameters.add(typename + " " + (String)names.get(0));
			}
			else if(elementIsPublic && elementIsComponent
					&& elementIsOutput && elementNamesFound && typenameFound)
			{
				outputParameters.add(typename + " " + (String)names.get(0));
				
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
		String className = findClassName(viewer, offset);

		if(className.charAt(className.length() - 1) == '.')
		{
			/*
			 * If the last character is a dot (.) we should fetch new proposals.
			 * We do a substring to get rid of the dot at the end.
			 */
			newProposals(className.substring(0, className.length()-1));
		}
		else
		{
			/* 
			 * Else we should narrow the proposals by only proposing the class
			 * names that has a prefix matching that of the typed class name.
			 */
			updateProposals(className);
		}
		
		/* Create 'real' completion proposals out of our proposals. */
		ICompletionProposal[] completionProposals = 
			new ICompletionProposal[narrowedProposals.size()];
		for(int i = 0;i < narrowedProposals.size();i++)
		{
			String proposal = (String)narrowedProposals.get(i);
			completionProposals[i] =
				new CompletionProposal(proposal, offset - typeAhead, typeAhead,
						proposal.length(), null, proposal, null, null);
		}

		return completionProposals;
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset)
	{
		String className = findClassName(viewer, offset);

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
