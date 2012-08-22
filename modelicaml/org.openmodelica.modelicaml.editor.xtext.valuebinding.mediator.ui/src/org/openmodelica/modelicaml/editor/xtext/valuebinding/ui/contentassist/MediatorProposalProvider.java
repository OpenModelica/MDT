/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.contentassist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#contentAssist on how to customize content assistant
 */
public class MediatorProposalProvider extends AbstractMediatorProposalProvider {
	@Override
	public void complete_SumFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		
		super.complete_name(model, ruleCall, context, acceptor);
	
		List<String> cList = new ArrayList<String>();
		cList.add("sum(:)");
		
		for (String string : cList) {
			String completionString = string;
			String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);
			ICompletionProposal completionProposal = createCompletionProposal(completionString, displayString, SWTResourceManager.getImage(SWTResourceManager.class, "/icons/valueMediator.png"), context);
			acceptor.accept(completionProposal);
		}
	}
	
	@Override
	public void complete_ProductFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		
		super.complete_name(model, ruleCall, context, acceptor);
	
		List<String> cList = new ArrayList<String>();
		cList.add("product(:)");
		
		for (String string : cList) {
			String completionString = string;
			String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);
			ICompletionProposal completionProposal = createCompletionProposal(completionString, displayString, SWTResourceManager.getImage(SWTResourceManager.class, "/icons/valueMediator.png"), context);
			acceptor.accept(completionProposal);
		}
	}
	
	@Override
	public void complete_MinFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		
		super.complete_name(model, ruleCall, context, acceptor);
	
		List<String> cList = new ArrayList<String>();
		cList.add("min(:)");
		
		for (String string : cList) {
			String completionString = string;
			String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);
			ICompletionProposal completionProposal = createCompletionProposal(completionString, displayString, SWTResourceManager.getImage(SWTResourceManager.class, "/icons/valueMediator.png"), context);
			acceptor.accept(completionProposal);
		}
	}
	
	@Override
	public void complete_MaxFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		
		super.complete_name(model, ruleCall, context, acceptor);
	
		List<String> cList = new ArrayList<String>();
		cList.add("max(:)");
		
		for (String string : cList) {
			String completionString = string;
			String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);
			ICompletionProposal completionProposal = createCompletionProposal(completionString, displayString, SWTResourceManager.getImage(SWTResourceManager.class, "/icons/valueMediator.png"), context);
			acceptor.accept(completionProposal);
		}
	}
	
	@Override
	public void complete_AverageFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		
		super.complete_name(model, ruleCall, context, acceptor);
	
		List<String> cList = new ArrayList<String>();
		cList.add("avg(:)");
		
		for (String string : cList) {
			String completionString = string;
			String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);
			ICompletionProposal completionProposal = createCompletionProposal(completionString, displayString, SWTResourceManager.getImage(SWTResourceManager.class, "/icons/valueMediator.png"), context);
			acceptor.accept(completionProposal);
		}
	}
	
	@Override
	public void complete_SizeFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		
		super.complete_name(model, ruleCall, context, acceptor);
	
		List<String> cList = new ArrayList<String>();
		cList.add("size(:)");
		
		for (String string : cList) {
			String completionString = string;
			String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);
			ICompletionProposal completionProposal = createCompletionProposal(completionString, displayString, SWTResourceManager.getImage(SWTResourceManager.class, "/icons/valueMediator.png"), context);
			acceptor.accept(completionProposal);
		}
	}
	
	
	@Override
	public void complete_ToArrayFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		
		super.complete_name(model, ruleCall, context, acceptor);
	
		List<String> cList = new ArrayList<String>();
		cList.add("toArray(:)");
		
		for (String string : cList) {
			String completionString = string;
			String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);
			ICompletionProposal completionProposal = createCompletionProposal(completionString, displayString, SWTResourceManager.getImage(SWTResourceManager.class, "/icons/valueMediator.png"), context);
			acceptor.accept(completionProposal);
		}
	}
	
	@Override
	public void complete_ANDFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		
		super.complete_name(model, ruleCall, context, acceptor);
	
		List<String> cList = new ArrayList<String>();
		cList.add("AND(:)");
		
		for (String string : cList) {
			String completionString = string;
			String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);
			ICompletionProposal completionProposal = createCompletionProposal(completionString, displayString, SWTResourceManager.getImage(SWTResourceManager.class, "/icons/valueMediator.png"), context);
			acceptor.accept(completionProposal);
		}
	}
	@Override
	public void complete_ORFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		
		super.complete_name(model, ruleCall, context, acceptor);
	
		List<String> cList = new ArrayList<String>();
		cList.add("OR(:)");
		
		for (String string : cList) {
			String completionString = string;
			String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);
			ICompletionProposal completionProposal = createCompletionProposal(completionString, displayString, SWTResourceManager.getImage(SWTResourceManager.class, "/icons/valueMediator.png"), context);
			acceptor.accept(completionProposal);
		}
	}
	@Override
	public void complete_XORFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		
		super.complete_name(model, ruleCall, context, acceptor);
	
		List<String> cList = new ArrayList<String>();
		cList.add("XOR(:)");
		
		for (String string : cList) {
			String completionString = string;
			String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);
			ICompletionProposal completionProposal = createCompletionProposal(completionString, displayString, SWTResourceManager.getImage(SWTResourceManager.class, "/icons/valueMediator.png"), context);
			acceptor.accept(completionProposal);
		}
	}
	
	@Override
	public void complete_GetSingleProviderFunction(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		
		super.complete_name(model, ruleCall, context, acceptor);
	
		List<String> cList = new ArrayList<String>();
		cList.add("getSingleProvider()");
		
		for (String string : cList) {
			String completionString = string;
			String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);
			ICompletionProposal completionProposal = createCompletionProposal(completionString, displayString, SWTResourceManager.getImage(SWTResourceManager.class, "/icons/valueMediator.png"), context);
			acceptor.accept(completionProposal);
		}
	}
}
