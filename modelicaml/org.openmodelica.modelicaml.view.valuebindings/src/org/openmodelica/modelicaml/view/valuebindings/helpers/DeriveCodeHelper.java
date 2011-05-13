package org.openmodelica.modelicaml.view.valuebindings.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.instantiation.TreeUtls;

public class DeriveCodeHelper {
	
	private TreeObject selectedTreeItem = null; // selected tree item
	private TreeParent root; 					// the root of the instantiation tree
	
	private Element valueClientUmlElement = null; // value client UML element. It may be the selected tree item or one of his parents.
	private TreeObject valueClientTreeItem = null; // value client that was found. It may be the selected tree item or one of his parents.

	private EList<Element> valueMediators = new BasicEList<Element>(); // list of mediators that the final client has

	private String valueClientRightHandMacroCode = null; // the extracted macro code for the final value client

	// used for macro expansion
	private static final int itemType_CLIENT 	= 0; 
	private static final int itemType_MEDIATOR 	= 1;
	private static final int itemType_PROVIDER 	= 2;

	private String code = null; // derived code
	private String errorString = null; // result of validation
	private Boolean errorsDetected = false; // indicates if an error was detected.

	
	// This constructor should always be preferred. 
	public DeriveCodeHelper(TreeObject item, TreeParent root) {
		this.selectedTreeItem = item;
		this.root = root;
		
		this.valueClientUmlElement= selectedTreeItem.getUmlElement();
        deriveCode();
	}
	
	// This constructor should only be used if no tree item is available ...
	public DeriveCodeHelper(Element valueClientElement, TreeParent root) {
        this.root = root;
        this.valueClientUmlElement = valueClientElement;
        deriveCode();
	}
	
	
	private void deriveCode(){
		// set Value Client data
		setValueClientAndItsRightHandMacroCode();
		
		// collect Value Mediators data
        setValueMediatorsData();

        
        if (valueClientUmlElement != null) { // if a value client was found at all.
            // validate all and set error text
            validate();
            
            // TODO: turn on after testing!
            if ( !errorsDetected() ) { // No code should be derived if there are errors.
            	this.code = deriveCodeForValueClient(valueClientUmlElement);
    		}
		}        
	}
	
	private void setValueMediatorsData(){
   		// Higher definition shall overwrite lower definitions. 
		// Look up for value client operations defined at the upper level.

		if (valueClientUmlElement != null ) {
			Stereotype valueClientStereotype = valueClientUmlElement.getAppliedStereotype(Constants.stereotypeQName_ValueClient);
			if (valueClientStereotype != null) {
				// set value mediators data
				Object objValueMediatorsValue = valueClientUmlElement.getValue(valueClientStereotype, Constants.stereotypeQName_ValueClient_obtainsValueFrom);
				if (objValueMediatorsValue instanceof EList) {

					EList<EObject> valueMediatorsStereotypeApplications = new BasicEList<EObject>();
					for (Object o : (EList<?>)objValueMediatorsValue) {
						if (o instanceof EObject) {
							valueMediatorsStereotypeApplications.add((EObject)o);
						}
					}
					
					for (EObject eObject : valueMediatorsStereotypeApplications) {
						Element valueMediator = UMLUtil.getBaseElement(eObject);
						valueMediators.add(valueMediator);
					}
				}
			}
		}
	}
	
	
	
	
	
	
	// The main method
	private String deriveCodeForValueClient(Element valueClient){
		
		String valueClientOperation = getOperationSpecification(valueClient, Constants.stereotypeQName_ValueClient, Constants.propertyName_operation);

		if (valueMediators.size() > 1) { // if there are multiple linked mediators then there must be an operation that specifies what to do with multiple items ...
			// get code items from different mediators
			List<String> codeItems = new ArrayList<String>();
			for (Element valueMediator : valueMediators) {
				codeItems.add(getCodeFromValueMediator(valueMediator) + "\n");
			}
			// expand all macros (e.g. build sum or product, etc., out of individual code items)
			return expandMacros(valueClientOperation, codeItems, itemType_CLIENT) ;	
		}
		else if (valueMediators.size() == 1) { // if there is only one mediator 
			if (valueClientOperation != null) { // if there is an operation defined then expand the macros based on first mediator.
				List<String> codeItems = new ArrayList<String>();
				codeItems.add(getCodeFromValueMediator(valueMediators.get(0)));
				
				// expand all macros (e.g. build sum or product, etc., out of individual code items)
				return expandMacros(valueClientOperation, codeItems, itemType_CLIENT);
			}
			else { // if no operation is defined then just return the code obtained from the first mediator.
				return getCodeFromValueMediator(valueMediators.get(0));
			}
		}
		 return null; // null if it was not possible to derive code.
	}
	
	
	private String getCodeFromValueMediator(Element valueMediator){
		String valueMediatorOperation = getOperationSpecification(valueMediator, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
		
		// create a list with only one value mediator
		EList<Element> valueMediatorList = new BasicEList<Element>();
		valueMediatorList.add(valueMediator);
		// get value providers
		EList<TreeObject> valueProvidersList = TreeUtls.getValueProviders(valueClientUmlElement, valueMediatorList, root);
		
		if (valueProvidersList.size() > 1 ) {
			// get items
			List<String> codeItems = new ArrayList<String>();
			for (TreeObject valueProvider : valueProvidersList) {
				codeItems.add(getCodeFromValueProvider(valueProvider));
			}

			return expandMacros(valueMediatorOperation, codeItems, itemType_MEDIATOR) ;
		}
		else if (valueProvidersList.size() == 1) {
			if (valueMediatorOperation != null) {  // if there is an operation defined then expand the macros based on first mediator.
				
				List<String> codeItems = new ArrayList<String>();
				codeItems.add(getCodeFromValueProvider(valueProvidersList.get(0)));
				return expandMacros(valueMediatorOperation, codeItems, itemType_MEDIATOR) ;
			}
			else { // if no operation is defined then just return the code obtained from the first provider.
				return getCodeFromValueProvider(valueProvidersList.get(0));
			}
		}
		
		return null; // null if it was not possible to derive code.
	}
	
	
	private String getCodeFromValueProvider(TreeObject item){
		Element element = item.getUmlElement();
		String macroCode = getOperationSpecification(element, Constants.stereotypeQName_ValueProvider, Constants.propertyName_operation);
		if (macroCode != null ) {
			macroCode = macroCode.replaceAll(Constants.MACRO_ownPath, item.getDotPath());
			return macroCode ;
		}
		return item.getDotPath();
	}

	
	
	private void setValueClientAndItsRightHandMacroCode(){
		String macroCode = null;
		
		if (selectedTreeItem != null) { // if item is set 
			Element element = selectedTreeItem.getUmlElement();
			if (element != null && isValueClient(element)) { // it is a client
				macroCode = getOperationSpecification(element, Constants.stereotypeQName_ValueClient, Constants.propertyName_operation);
			}
		}
		if (macroCode != null) {
			String[] macroCodeSplitted = macroCode.split(";");
			if ( macroCodeSplitted.length > 0) { // if there is at least one line that ends with ";"
				for (int i = 0; i < macroCodeSplitted.length; i++) { // the next will always overwrite the previous 
					if ( !macroCodeSplitted[i].trim().equals("") ) { // if it is not an empty line 
						String[] bindingEqationParts = macroCodeSplitted[i].split("=");
						if (bindingEqationParts.length == 2 ) {// it is a binding equation with left and right part 
							
							// Get the left and right parts and trim them.
							String leftHand = bindingEqationParts[0].trim();
							String rightHand = bindingEqationParts[1].trim();
							
							// If the left part of the binding equation refers to this value client 
							//(i.e. its "ownPath") then set the right valueClientRightHandMacroCode.
							if (leftHand.equals(Constants.MACRO_ownPath)) {
								
								// Set the value client data based on the selected element. 
								// Note that this can be overwritten by selected tree item parents.
								valueClientRightHandMacroCode = rightHand;
								valueClientUmlElement = selectedTreeItem.getUmlElement();
								setValueClientTreeItem(selectedTreeItem);
							}
//							System.err.println("leftHand: " + leftHand);
//							System.err.println("rightHand: " + rightHand);
						}
						else {
							// TODO: generate marker
							System.err.println("VB001 - ERROR: ModelicaML, Value Binding Code Derivation failed for '" + macroCodeSplitted[i] + "'" );
						}
					}
				}
			}
			
			
		}
		
		if (selectedTreeItem != null) {
			// Higher hierarchy level definitions shall overwrite the lower level definitions.
			// Look for macro code for this value client in all its parents and overwrite respectively.
			TreeObject nextParent = selectedTreeItem.getParent();
			if (nextParent != null) {
				setValueClientAndItsRightHandMacroCodeFromParent(selectedTreeItem, selectedTreeItem);	
			}
		}
	}
	
	
	private void setValueClientAndItsRightHandMacroCodeFromParent(TreeObject item, TreeObject selectedItem){
		TreeObject parent = item.getParent();
		
		if (parent != null) { // if there is parent
			
			Element element = parent.getUmlElement();
			if (element != null && isValueClient(element)) { // it is a client
			
				String macroCode = getOperationSpecification(element, Constants.stereotypeQName_ValueClient, Constants.propertyName_operation);
				if (macroCode != null) { // if it has an operation defined
				
					String[] macroCodeSplitted = macroCode.split(";");
					if ( macroCodeSplitted.length > 0) { // if there is at least one line that ends with ";"
						for (int i = 0; i < macroCodeSplitted.length; i++) { // the next will always overwrite the previous 
							if ( !macroCodeSplitted[i].trim().equals("") ) { // if it is not an empty line 
								String[] bindingEqationParts = macroCodeSplitted[i].split("=");
								if (bindingEqationParts.length == 2 ) {// it is a binding equation with left and right part 
									
									// get the left and right parts and trim them
									String leftHand = bindingEqationParts[0].trim();
									String rightHand = bindingEqationParts[1].trim();
									
									// it the left part of the binding equation refers to this value client 
									//(i.e. its "ownPath") then return the right part.
									
									String expandedLeftHand = leftHand.replaceFirst(Constants.MACRO_ownPath, parent.getDotPath()).trim();
//									System.err.println("expandedLeftHand_: " + expandedLeftHand);

									if (expandedLeftHand.equals(selectedItem.getDotPath())) { 
										
										// Set the value client data based on the selected element. 
										// Overwrite the selected tree item data.
										valueClientRightHandMacroCode = rightHand;
										valueClientUmlElement = parent.getUmlElement();
										setValueClientTreeItem(parent);
										
//										System.err.println("value client is: " + parent.getDotPath());
//										System.err.println("value client macro is: " + valueClientRightHandMacroCode);
									}
//									System.err.println("leftHand: " + leftHand);
//									System.err.println("rightHand: " + rightHand);
								}
								else {
									// TODO: generate marker
									System.err.println("VB002 - ERROR: ModelicaML, Value Binding Code Derivation failed for '" + macroCodeSplitted[i] + "'" );
								}
							}
						}
					}
				}
			}
		}

		TreeObject nexParent = parent.getParent();
		if (nexParent != null) {
			// go to the next level, i.e. to the next parent and look for value client macro
			setValueClientAndItsRightHandMacroCodeFromParent(parent, selectedItem);
		}
	}
	
	
		
	
	private String expandMacros(String macroCode, List<String> codeItems, int itemType){
		// only for value clients
		if (itemType == itemType_CLIENT) { // for a value client 
			// get the valueClientRightHandMacroCode that was set previously
			macroCode = valueClientRightHandMacroCode;
		}
		if (macroCode != null) {
			
			// replace size(:) numberOfProviders
//			EList<TreeObject> providersList = TreeUtls.getValueProviders(valueClientUmlElement, valueMediators, root); 

			// TODO: test providersList.size()! 
			macroCode = macroCode.replaceAll("size\\((\\s+)?\\:(\\s+)?\\)", String.valueOf(getValueProviders().size()) );
			
			// replace avg(:) with  ( sum(:) / numberOfProviders). Note, sum(:) will be expanded below.
			macroCode = macroCode.replaceAll("avr\\((\\s+)?\\:(\\s+)?\\)", "(sum(:) / " + String.valueOf(getValueProviders().size()) + ")");
			
			// replace ( : ) with ( i for i in {comp1.name, comp2, etc.})
			macroCode = macroCode.replaceAll("\\((\\s+)?\\:(\\s+)?\\)", "( i for i in {" + getItemsSeparated(", ", codeItems) + "})");

			// this is only valid if codeItems has only one item. The validation will catch it and set error so that no code will be generated if there are multiple items
			macroCode = macroCode.replaceAll("getResult\\((\\s+)?\\)", getItemsSeparated(", ", codeItems) ); // used by value client/mediator to manipulate the result received from one mediator/provider
		}
		return macroCode;
	}
	
	
	
	
	// ############## VALIDATION
	private void validate(){
		String name = ((NamedElement)valueClientUmlElement).getName(); 
		String title;
		String message; 
		String valueClientOperation = valueClientRightHandMacroCode;
		
		// CLIENT VALIDATION #############################################
		// -----------------------------------------------------------------------------		
		// ##### if a value client has multiple value mediators then it must have the binding operation defined
		if (valueMediators.size() > 1 && !hasValidValueClientBindingOperationForMultipleItems( valueClientOperation )) {
			name = ((NamedElement)valueClientUmlElement).getName();
			title = "Value Client Inconsistancy \n";
			message = title + "The value client '" + name + "' is linked to " + valueMediators.size() 
							+ " value mediators: " + getItemsAsStringList(valueMediators) + "" 
							+ "\nHowever no binding operation is defined that specifies what shall be done in case of multiple Value Mediators. \n\n";
//			MessageDialog.openError(new Shell(), title, message );
			
			// set the error mode
			setError(message);
		}

		// ------------------------------------------------------------------------------
		// ##### if a value client has multiple value mediators then it cannot contain getResult() macro that can only be used in combination with a single value mediator.
		if ( valueMediators.size() > 1 && containsGetResult( valueClientOperation ) ) {
			title = "Value Client Inconsistancy \n";
			message = title + "The value client '" + name + "' is linked to " + valueMediators.size() 
							+ " value mediators: " + getItemsAsStringList(valueMediators) + "" 
							+ "\nHowever, it contains the getResult() macro that can only be used for one Value Mediator.\n\n";
//			MessageDialog.openError(new Shell(), title, message );
			
			// set error 
			setError(message);
		}
		
		// MEDIATORS VALIDATION #############################################
		for (Element valueMediator : valueMediators) {
			String valueMediatorOperation = getOperationSpecification(valueMediator, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);

			// create a list with only one value mediator
			EList<Element> valueMediatorList = new BasicEList<Element>();
			valueMediatorList.add(valueMediator);
			// get value providers
			EList<TreeObject> valueProvidersList = TreeUtls.getValueProviders(valueClientUmlElement, valueMediatorList, root);

			// -----------------------------------------------------------------------------------------------------		
			// if value mediator has multiple value providers then it must have the binding operation defined
			if (valueProvidersList.size() > 1 && !hasValidValueClientBindingOperationForMultipleItems(valueMediatorOperation)) {
				name = ((NamedElement)valueMediator).getName();
				title = "Value Mediator Inconsistancy \n";
				message = title + "The value mediator '" + name + "' is linked to " + valueProvidersList.size() 
								+ " value providers: " + getItemsAsStringList(valueProvidersList) +"" 
								+ "\nHowever, no binding operation is defined that specifies what shall be done in case of multiple Value Providers. \n\n";
//				MessageDialog.openError(new Shell(), title, message );
				
				// set error
				setError(message);
			}
			
			// -----------------------------------------------------------------------------------------------------
			// ##### if a value mediator has multiple value providers then it cannot contain getResult() macro that can only be used in combination with a single provider.
			if (valueProvidersList.size() > 1 && containsGetResult(valueMediatorOperation) ) {
				title = "Value Mediator Inconsistancy \n";
				message = title + "The value mediator '" + name + "' is linked to " + valueProvidersList.size() 
								+ " value mediators: " + getItemsAsStringList(valueMediators) + "" 
								+ "\nHowever, it contains the getResult() that can only be used for one Value Provider. \n\n";
//				MessageDialog.openError(new Shell(), title, message );
				
				// set error
				setError(message);
			}
		}
	}
	
	
	
	
	
	
	
//	private String buildOperatorString(String operator, List<String> items){
//		String code = "";
//		for (int i = 0; i < items.size(); i++) {
//			if (i == 0) { // first item
//				code = code + items.get(i) + "\n";
//			}
//			else if (i == items.size() - 1 ) { // last item
//				code = code + operator + items.get(i);
//			}
//			else {
//				code = code + operator + items.get(i)  + "\n";
//			}
//		}
//		return code;
//	}
	
	
	private String getItemsSeparated(String separator, List<String> items){
		String code = "";
		for (int i = 0; i < items.size(); i++) {
			if (i == 0) { // first item
				code = code + items.get(i);
			}
			else if (i == items.size() - 1 ) { // last item
				code = code + separator + items.get(i);
			}
			else {
				code = code + separator+ items.get(i);
			}
		}
		return code;
	}
	
	
	private boolean isValueClient(Element element){
		if (element != null && element.getAppliedStereotype(Constants.stereotypeQName_ValueClient) != null ) {
			return true;
		}
		return false;
	}

	
	private String getOperationSpecification(Element element, String stereotypeQName, String propertyName){
		if (element != null) {
			Stereotype stereotype = element.getAppliedStereotype(stereotypeQName);
			if (stereotype != null) {
				Object o = element.getValue(stereotype, propertyName);
				if (o instanceof String) {
					if ( !((String)o).trim().equals("") ) {
						return (String)o;
					}
				}
			}
		}			
		return null;
	}
	
	
	private boolean containsGetResult(String code) {
		if (code != null && code.matches("(.+)?getResult\\((\\s+)?\\)(.+)?") ) {
//			System.err.println("Matched: " + code);
			return true;
		}
		return false;
	}
	
	private boolean hasValidValueClientBindingOperationForMultipleItems(String macroCode){
//		System.err.println("macroCode: " + macroCode);
		if (macroCode != null) {
			if ( macroCode.matches("(.+)\\((\\s+)?\\:(\\s+)?\\)(.+)?") ) {
//				System.err.println("macroCode matches: " + macroCode);
				return true;
			}
		}
		return false;
	}
	
//	private boolean isValidValueClientBindingOperation(String macroCode){
//		if (macroCode != null) {
//
//			if (macroCode.matches("(.+)\\((\\s+)?\\:(\\s+)?\\)(.+)?") && !containsGetResult(macroCode)) {
//				return true;
//			}
////			if (containsGetFirstValue(macroCode)) {
////				return true;
////			}
//			if ( containsGetResult(macroCode) && !macroCode.matches("(.+)\\((\\s+)?\\:(\\s+)?\\)(.+)?")) {
//				return true;
//			}
//			else {
//				setError(" Not a valid macro... " +  macroCode + " \n\n");
//				System.err.println("Not a valid macro... " +  macroCode);
//			}
//		}
//		
//		return false;
//	}
	
	private void setError(String message){
		
		errorsDetected = true;
		
		if (errorString == null) {
			errorString = message;	
		}
		else {
			errorString = errorString + "\n\n" + message;
		}
		
		errorString = errorString + "----------------------------------------------" +
									"----------------------------------------------" +
									"--------------------------------------- \n";
	}

	private String getItemsAsStringList(EList<?> list){
		String string = "";
		for (Object object : list) {
			if (object instanceof TreeObject ) {
				string = string + "\n         - " + ((TreeObject)object).getDotPath();	
			}
			else if (object instanceof NamedElement) {
				string = string + "\n         - " + ((NamedElement)object).getQualifiedName();	
			}
		}
		return string;
	}

	

	
	
	
	
	
	
	//###################### GETTER and SETTER
	
	public String getErrorString() {
		return errorString;
	}

	public String getCode() {
		return code;
	}
	
	public Boolean errorsDetected (){
		return this.errorsDetected;
	}

	public void setValueClientTreeItem(TreeObject valueClientTreeItem) {
		this.valueClientTreeItem = valueClientTreeItem;
	}

	public TreeObject getValueClientTreeItem() {
		return valueClientTreeItem;
	}
	
	public EList<Element> getValueMediators() {
		return valueMediators;
	}

	public void setValueMediators(EList<Element> valueMediators) {
		this.valueMediators = valueMediators;
	}

	public EList<TreeObject> getValueProviders() {
		EList<TreeObject> listOfProviders = TreeUtls.getValueProviders(valueClientUmlElement, valueMediators, root);
    	return listOfProviders;
	}
	
	public String getValueClientRightHandMacroCode() {
		return valueClientRightHandMacroCode;
	}

	public void setValueClientRightHandMacroCode(
			String valueClientRightHandMacroCode) {
		this.valueClientRightHandMacroCode = valueClientRightHandMacroCode;
	}
}
