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
	
	private String code = null; // derived code
	private String errorString = null; // result of validation
	private Boolean errorsDetected = false; // indicates if an error was detected.

	private TreeParent root; // root of the instantiation tree
	private Element umlElement = null; // value client
 	private Stereotype valueClientStereotype = null; // value client stereotype
	private Element valueClient = null; // value client
	
	private EList<Element> valueMediators = new BasicEList<Element>(); // list of mediators that the client has

	
	public DeriveCodeHelper(TreeObject item, TreeParent root) {
        this.root = root;
        this.umlElement = item.getUmlElement();
        deriveCode();
	}
	
	public DeriveCodeHelper(Element valueClient, TreeParent root) {
        this.root = root;
        this.umlElement = valueClient;
        deriveCode();
	}
	
	
	private void deriveCode(){
		// set Value Client data
		setValueClientData();
		
		// collect Value Mediators data
        setValueMediatorsData();

        // validate all and set error text
        validate();
        
        if ( !errorsDetected() ) { // No code should be derived if there are errors.
        	this.code = deriveCodeForValueClient(valueClient);
		}
	}

	private void setValueClientData(){
		if (this.umlElement != null) {
        	this.valueClientStereotype = this.umlElement.getAppliedStereotype(Constants.stereotypeQName_ValueClient);
	        valueClient = this.umlElement;
		}
	}
	
	private void setValueMediatorsData(){
		 if (umlElement != null && valueClientStereotype != null ) {
				
			// set value mediators data
			Object objValueMediatorsValue = umlElement.getValue(valueClientStereotype, Constants.stereotypeQName_ValueClient_obtainsValueFrom);
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
	
	
	
	
	// then main method.
	private String deriveCodeForValueClient(Element valueClient){
		
		String valueClientOperation = getOperationSpecification(valueClient, Constants.stereotypeQName_ValueClient, Constants.propertyName_operation);

		if (valueMediators.size() > 1) { // if there are multiple linked mediators then there must be an operation that specifies what to do with multiple items ...
			// get code items from different mediators
			List<String> codeItems = new ArrayList<String>();
			for (Element valueMediator : valueMediators) {
				codeItems.add(getCodeFromValueMediator(valueMediator) + "\n");
			}
			// expand all macros (e.g. build sum or product out of individual code items)
			return expandMacros(valueClientOperation, codeItems) ;	
		}
		else if (valueMediators.size() == 1) { // if there is only one mediator 
			if (valueClientOperation != null) { // if there is an operation defined then expand the macros based on first mediator.
				List<String> codeItems = new ArrayList<String>();
				codeItems.add(getCodeFromValueMediator(valueMediators.get(0)));
				
				// expand all macros for the one mediator.
				return expandMacros(valueClientOperation, codeItems);
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
		EList<TreeObject> valueProvidersList = TreeUtls.getValueProviders(valueClient, valueMediatorList, root);
		
		if (valueProvidersList.size() > 1 ) {
			// get items
			List<String> codeItems = new ArrayList<String>();
			for (TreeObject valueProvider : valueProvidersList) {
				codeItems.add(getCodeFromValueProvider(valueProvider));
			}

			return expandMacros(valueMediatorOperation, codeItems) ;
		}
		else if (valueProvidersList.size() == 1) {
			if (valueMediatorOperation != null) {  // if there is an operation defined then expand the macros based on first mediator.
				
				List<String> codeItems = new ArrayList<String>();
				codeItems.add(getCodeFromValueProvider(valueProvidersList.get(0)));
				return expandMacros(valueMediatorOperation, codeItems) ;
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
//			macroCode = "(" + macroCode.replaceAll("#PATH#", item.getDotPath()) + ")";
//			macroCode = macroCode.replaceAll("getPath\\((\\s+)?\\)", item.getDotPath());
//			macroCode = macroCode.replaceAll("getOwnPath\\((\\s+)?\\)", item.getDotPath());
			macroCode = macroCode.replaceAll(Constants.MACRO_ownPath, item.getDotPath());
			return macroCode ;
		}
		return item.getDotPath();
	}

	
	private String expandMacros(String macroCode, List<String> codeItems){
		
		if (macroCode != null) {
			// replace ( : ) with ( i for i in {comp1.name, comp2, etc.})
//			System.err.println("ORIGINAL: " + macroCode);
			macroCode = macroCode.replaceAll("\\((\\s+)?\\:(\\s+)?\\)", "( i for i in {" + getItemsSeparated(", ", codeItems) + "})");
//			System.err.println("EXPANDED: " + macroCode);

			// this is only valid if codeItems has only one item. The validation will catch it and set error so that no code will be generated if there are multiple items
			macroCode = macroCode.replaceAll("getResult\\((\\s+)?\\)", getItemsSeparated(", ", codeItems) ); // used by value client/mediator to manipulate the result received from one mediator/provider
		}
		return macroCode;
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
	
	
//	private boolean containsGetFirstValue(String code) {
////		//@Deprecated
////		if (code != null && code.contains("#REF#")) {
////			return true;
////		}
////		if (code != null && code.contains("getRef(")) {
////			return true;
////		}
////		if (code != null && code.contains("getFirst(")) {
////			return true;
////		}
////		if (code != null && code.contains("getFirstValue(")) {
//		if (code != null && code.matches("(.+)?getFirstValue\\((\\s+)?\\)(.+)?") ) { // @Deprecated
////			System.err.println("Matched: " + code);
//			return true;
//		}
//		if (code != null && code.matches("(.+)?getFirst\\((\\s+)?\\)(.+)?") ) {
////			System.err.println("Matched: " + code);
//			return true;
//		}
//		return false;
//	}
	
	private boolean hasValidValueClientBindingOperationForMultipleItems(String macroCode){
		if (macroCode != null) {
			if ( macroCode.matches("(.+)\\((\\s+)?\\:(\\s+)?\\)(.+)?") ) { 
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

	private void validate(){
		String name = ((NamedElement)valueClient).getName(); 
		String title;
		String message; 
		String valueClientOperation = getOperationSpecification(valueClient, Constants.stereotypeQName_ValueClient, Constants.propertyName_operation);

		// -----------------------------------------------------------------------------		
		// ##### if a value client has multiple value mediators then it must have the binding operation defined
		if (valueMediators.size() > 1 && !hasValidValueClientBindingOperationForMultipleItems( valueClientOperation )) {
			name = ((NamedElement)valueClient).getName();
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
		
		for (Element valueMediator : valueMediators) {
			String valueMediatorOperation = getOperationSpecification(valueMediator, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);

			// create a list with only one value mediator
			EList<Element> valueMediatorList = new BasicEList<Element>();
			valueMediatorList.add(valueMediator);
			// get value providers
			EList<TreeObject> valueProvidersList = TreeUtls.getValueProviders(valueClient, valueMediatorList, root);

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
	
	public String getErrorString() {
		return errorString;
	}

	public String getCode() {
		return code;
	}
	
	public Boolean errorsDetected (){
		return this.errorsDetected;
	}

}
