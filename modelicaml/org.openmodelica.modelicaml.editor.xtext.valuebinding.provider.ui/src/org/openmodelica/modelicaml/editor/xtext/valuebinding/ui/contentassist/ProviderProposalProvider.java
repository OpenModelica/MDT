/*
* generated by Xtext
*/
package org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.contentassist;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.contentassist.AbstractProviderProposalProvider;
/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#contentAssist on how to customize content assistant
 */
public class ProviderProposalProvider extends AbstractProviderProposalProvider {


	@Override
	public void complete_component_reference(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.complete_component_reference(model, ruleCall, context, acceptor);
		
		List<String> cList = ModelicaMLContentAssist.getComponentReferenceSortedList();
		cList.add(Constants.MACRO_providerPath);
		
		for (String string : cList) {
			// replace the Value Provider component name with a specific macro
			if (string.startsWith(ModelicaMLContentAssist.getPropertyName())) {
				string = string.replaceFirst(ModelicaMLContentAssist.getPropertyName(), Constants.MACRO_providerPath);
			}
			
			String completionString = string;
			String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);

			Image image = ModelicaMLContentAssist.getIcon(string);
			if (image == null) {
				image = SWTResourceManager.getImage(SWTResourceManager.class, "/icons/valueMediator.png");
			}
			
			ICompletionProposal completionProposal = createCompletionProposal(completionString, displayString, image, context);

			acceptor.accept(completionProposal);
		}
	}
	
//	OLD: //String displayString = string.replaceFirst(context.getPrefix(), "");
//	NEW: String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);

		
	@Override
	public void complete_name(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		super.complete_name(model, ruleCall, context, acceptor);
		
		List<String> cList = ModelicaMLContentAssist.getComponentReferenceSortedList();
		cList.add(Constants.MACRO_providerPath);
		
		for (String string : cList) {
			// replace the component name with a specific macro
			if (string.startsWith(ModelicaMLContentAssist.getPropertyName())) {
				string = string.replaceFirst(ModelicaMLContentAssist.getPropertyName(), Constants.MACRO_providerPath);
			}
			
			String completionString = string;
			String displayString = ModelicaMLContentAssist.getDisplayCompletionString(context.getPrefix(), completionString);

			Image image = ModelicaMLContentAssist.getIcon(string);
			if (image == null) {
				image = SWTResourceManager.getImage(SWTResourceManager.class, "/icons/valueMediator.png");
			}
			
			ICompletionProposal completionProposal = createCompletionProposal(completionString, displayString, image, context);
			
			acceptor.accept(completionProposal);
		}
	}
	
}
