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

package org.modelica.mdt.editor;

import java.text.MessageFormat;
import java.util.Vector;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.internal.omcproxy.ConnectionException;
import org.modelica.mdt.internal.omcproxy.InvocationError;
import org.modelica.mdt.internal.omcproxy.OMCProxy;
import org.modelica.mdt.internal.omcproxy.UnexpectedReplyException;

/**
 * 
 * @author Andreas Remar
 *
 */
public class ModelicaCompletionProcessor implements IContentAssistProcessor
{
	Vector<String> proposals = new Vector<String>();
	
	/*
	 * There is a separate narrowedProposals so that we can store the original
	 * proposals. This allows us to backtrace the proposals.
	 */
	Vector<String> narrowedProposals = new Vector<String>();
	int typeAhead = 0;
	
	protected static class Validator implements IContextInformationValidator,
		IContextInformationPresenter
	{
		protected int fInstallOffset;
		
		public boolean isContextInformationValid(int offset)
		{
			return Math.abs(fInstallOffset - offset) < 5;
		}
		
		public void install(IContextInformation info, ITextViewer viewer,
				int offset)
		{
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
		proposals.clear();
		narrowedProposals.clear();
		typeAhead = 0;

		String[] classes = null;
		try
		{
			classes = OMCProxy.getClassNames(className);
		}
		catch (ConnectionException e)
		{
			e.printStackTrace();
			MdtPlugin.log(e);
		}
		catch (UnexpectedReplyException e)
		{
			/* Ignore (we simply can't find the class...) */
		}

		for(String s : classes)
		{
			proposals.add(s);
			narrowedProposals.add(s);
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
		//Vector<String> props = new Vector<String>();
		
		typeAhead = classPrefix.length();
		
		narrowedProposals.clear();
		
		for(String proposedClass : proposals)
		{
			if(proposedClass.startsWith(classPrefix))
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
	private String findClassName(ITextViewer viewer, int offset)
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
			if(ch == '\n' || ch == '\t' || ch == ' ')
				break;
			else
				className = ch + className;
		}
		while(true);
		
		return className;
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
			String proposal = narrowedProposals.get(i);
			completionProposals[i] =
				new CompletionProposal(proposal, offset - typeAhead, typeAhead,
						proposal.length(), null, proposal, null, null);
		}

		return completionProposals;
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset)
	{
		IContextInformation[] result= new IContextInformation[5];
		for (int i= 0; i < result.length; i++)
			result[i]= new ContextInformation(
				MessageFormat.format("CompletionProcessor.ContextInfo.display.pattern", new Object[] { new Integer(i), new Integer(offset) }),  //$NON-NLS-1$
				MessageFormat.format("CompletionProcessor.ContextInfo.value.pattern", new Object[] { new Integer(i), new Integer(offset - 5), new Integer(offset + 5)})); //$NON-NLS-1$
		return result;
	}

	public char[] getCompletionProposalAutoActivationCharacters()
	{
		return new char[] {'.'};
	}

	public char[] getContextInformationAutoActivationCharacters()
	{
		return null;
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
