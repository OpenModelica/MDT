package org.modelica.mdt.editor;

import java.text.MessageFormat;
import java.util.List;

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

public class ModelicaCompletionProcessor implements IContentAssistProcessor
{

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
	
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset)
	{
		String className = "";

		char ch;
		
		int offsetCounter = offset;
		
		try
		{
			/* to skip the . at the end */
			offsetCounter--;
			do
			{
				ch = viewer.getDocument().getChar(--offsetCounter);
				if(ch != '\n' && ch != '\t' && ch != ' ')
					className = ch + className;
				else
					break;
			}
			while(true);
		}
		catch(BadLocationException e)
		{
			e.printStackTrace();
			MdtPlugin.log(e);
		}
		
			String[] classes = null;
			String[] packages = null;
			
			try
			{
				classes = OMCProxy.getClassNames(className);
				packages = OMCProxy.getPackages(className);
			}
			catch (ConnectionException e)
			{
				e.printStackTrace();
				MdtPlugin.log(e);
			}
			catch (UnexpectedReplyException e)
			{
				e.printStackTrace();
				MdtPlugin.log(e);
			}
			catch (InvocationError e)
			{
				e.printStackTrace();
				MdtPlugin.log(e);
			}
			
			ICompletionProposal[] proposals = 
				new ICompletionProposal[classes.length + packages.length];
			
			for(int i = 0;i < classes.length;i++)
			{
				proposals[i] = new CompletionProposal(classes[i], offset, 0,
						classes[i].length(), null, classes[i], null, null);
			}
			for(int i = 0;i < packages.length;i++)
			{
				proposals[i + classes.length] = 
					new CompletionProposal(packages[i], offset, 0,
							packages[i].length(), null, packages[i],
							null, null);
			}
		return proposals;
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
		return new char[] {'.', '('};
	}

	public char[] getContextInformationAutoActivationCharacters()
	{
		return new char[] {'#'};
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
