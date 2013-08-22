package org.openmodelica.modelicaml.helper.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.helper.datacollection.VerificationScenariosCollector;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeHelper;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeUtls;

public class TEST_GenerateVerificationModels {
	
	// input
	private Element dm;
	private Element LS_S;
	private Element LR_S;
	private Element LM_S;
	private Element LA_S;
	
	// output
	private List<Element> VM_L = new ArrayList<Element>();
	
	// local
	private VerificationScenariosCollector cd;
	private String log;
	private Element rootModel = null; // the root element of the model that contains all locations and the design model
	
	// data
	private List<Element> S_L = new ArrayList<Element>(); // List of found scenarios
	private List<Element> R_L = new ArrayList<Element>(); // List of found requirements
	private List<Element> M_L = new ArrayList<Element>(); // List of found mediators
//	private List<Element> A_L = new ArrayList<Element>(); // List of found additional models
	private HashMap<Element, ClassInstantiation> I_M = new HashMap<Element, ClassInstantiation>();
	private HashMap<TreeObject,TEST_GenerateVerificationModels.BindingData> B_M;
	
	private DeriveValueBindingCodeHelper deriveCodeHelper = new DeriveValueBindingCodeHelper();
		
	// constructor
	public TEST_GenerateVerificationModels(
			Element dm, 
			Element LS_S,
			Element LR_S,
			Element LM_S,
			Element LA_S,
			Element rootModel
			){
		
		this.dm = dm;
		this.LS_S = LS_S;
		this.LR_S = LR_S;
		this.LM_S = LM_S;
		this.LA_S = LA_S;
		
		this.rootModel = rootModel; 
	}
	
	
	/*
	 * This method generates Verification Models
	 */
	public void generateVeMs(){
		
		assert (this.rootModel != null && this.dm != null && this.LS_S != null && this.LR_S != null && this.LM_S != null && this.LA_S != null) : "Input data is incorrect.";
		
		// Collect all relevant data
		cd = new VerificationScenariosCollector(rootModel, LR_S, LS_S, LM_S);
		cd.collectAll(true);
		
		S_L = ModelicaMLServices.getSortedByName(cd.getAllScenarios());
		R_L = ModelicaMLServices.getSortedByName(cd.getAllRequirements());
		M_L = ModelicaMLServices.getSortedByName(cd.getAllMediators());
		
		// TODO: not needed because in cd we already have the additional models sorted
//		A_L = ModelicaMLServices.getSortedByName(cd.getAllMediators()); 
		
		deriveCodeHelper = new DeriveValueBindingCodeHelper();
		
		for (Element  sk : S_L) {
			
			B_M = new HashMap<TreeObject, TEST_GenerateVerificationModels.BindingData>();
			
			log("Combining '" + ModelicaMLServices.getName(dm) +  "' with '" + ModelicaMLServices.getName(sk) + "'");
			
			ClassInstantiation V = new ClassInstantiation("NewVeM_"+ ModelicaMLServices.getName(sk), cd.getAllMediators()); // Instantiation root node
			V.collectBindingsDataFromUmlModel();
			
			instantiate(newHashSet(dm), V); // Create explicit model structure and add to map
			TreeObject dmi = I_M.get(dm).getTreeRoot(); // Get instantiation from the map
//			addChildren(V, newTOSet(dmi)); // Attach instantiation to the root node V
			
			HashSet<Element> AD_S = cd.getModelToItsAdditionalModels().get(dm); // Find additional models
			instantiate(AD_S, V); // Set of instantiations
//			HashSet<TreeObject> adis = getInstantiation(AD_S); 	// {adi0 … adin} = getInstantiation(AD_S) // Set of instantiations
//			addChildren(V, adis); // Attach each instantiation to root node V
			
			instantiate(newHashSet(sk), V); 
			TreeObject sik = I_M.get(sk).getTreeRoot(); 
//			addChildren(V, newTOSet(ski)); // Attach instantiation to the root node V
			
			HashSet<Element> AS_S = cd.getModelToItsAdditionalModels().get(sk); // Additional models for sk
			instantiate(AS_S, V);
//			HashSet<TreeObject> asis = getInstantiation(AS_S);
//			addChildren(V, asis);
			
			// initialize the helper in order to collect and sort the data from the model and the instantiated class.
//			deriveCodeHelper.initialize( (Package) LM_S, V, V.getTreeRoot(), cd.getAllMediators(), false); 
			
			inferBindings(V, V.getAllClients());
			
//			if (allMadatoryClientsSatisfied(V) && usesAtLeastOneProviderFrom(dmi, sik)) {
			if (allMadatoryClientsSatisfied(V) && useAtLeastOneProviderFrom(I_M.get(dm).getAllMandatoryClients(), sik)) {
//				log("	allMadatoryClients are satisfied in '" +ModelicaMLServices.getName(dmi.getSelectedClass())+ "' and some providers are used from '"  + ModelicaMLServices.getName(sik.getSelectedClass()) + "'");
				
				// Find requirements for scenario sk
				List<Element> RS_L = getRequirementsFor(sk, "all");
//				List<Element> RS_L = getRequirementsFor(sk, "explicitRelations");
				
				// Analyze requirements
				HashSet<Element> Rcomb_S = new HashSet<Element>(); // Selected requirements
				HashSet<Element> AR_S = new HashSet<Element>();
				
				if (RS_L != null) {
					for (Element rk : RS_L) {
						
						// Instantiate all models needed by this requirement in addition 
						if (cd.getModelToItsAdditionalModels().get(rk) != null) {
							AR_S.addAll(cd.getModelToItsAdditionalModels().get(rk)); // Find additional models
							instantiate(AR_S, V);
						}
						 
						// Instantiate requirement in order to determine if binding can be derived for its clients
						instantiate(newHashSet(rk), V);
						
						inferBindings(V, I_M.get(rk).getAllClients());
						
						if (allMadatoryClientsSatisfied(I_M.get(rk))) {
							
							log("	+ '" + ModelicaMLServices.getName(rk) + "' is included in the combination of '" +ModelicaMLServices.getName(dm)+ "' and '"  + ModelicaMLServices.getName(sk) + "'.");
							Rcomb_S.add(rk); // Selected requirements
							
							// Determine if this requirement should be instantiated multiple times
							int addInstNumber = getAdditionalInstantionsNumber(I_M.get(rk).getTreeRoot());
							
							if (addInstNumber > 0) {
								log("		* '" + ModelicaMLServices.getName(rk) + "' has to be instantiated " + (addInstNumber ) + " times in addition.");
								
								// additional instantiations
								for (int i = 0; i < addInstNumber; i++) {
									instantiate(newHashSet(rk), V); 
								}
								
								// update bindings
								inferBindings(V, V.getAllClients());
							}
						}
						else {
							TreeObject rik = I_M.get(rk).getTreeRoot();
							TreeParent parent = rik.getParent();
							parent.removeChild(rik);
							
							log("	- '" + ModelicaMLServices.getName(rk) + "' is discarded because not all mandatory clients can be satisfied."); 
						}
					}
					
					// Analyze all additional models collected in this combination
					HashSet<Element> AMI_S = new HashSet<Element>();
					if (AD_S != null ) { AMI_S.addAll(AD_S);}
					if (AS_S != null ) { AMI_S.addAll(AS_S);}
					if (AR_S != null ) { AMI_S.addAll(AR_S);}
					
					for (Element am : AMI_S) {
						TreeObject aik = I_M.get(am).getTreeRoot();
//						if (!usesAtLeastOneProviderFrom(V.getTreeRoot(), aik)) {
						if (!useAtLeastOneProviderFrom(V.getAllMandatoryClients(), aik)) {
							TreeParent parent = aik.getParent();
							parent.removeChild(aik);
							log("	- '" + ModelicaMLServices.getName(am) + "' is discarded as addition model. None of its providers is used.");    
						}
						else {
							log("	+ '" + ModelicaMLServices.getName(am) + "' is included as addition model.");    
						}
					}
					
					// Create verification model. 
					V.getTreeRoot().setName("VeM " + ModelicaMLServices.getName(sk));
					
					System.err.println("------------------------------------\n");
					System.err.println(V.getTreeRoot());
					for (TreeObject child : V.getTreeRoot().getChildren()) {
						System.err.println(child);
					}
					System.err.println("------------------------------------\n");
					
				}
			}
			else {
				log("### '" + ModelicaMLServices.getName(sk) + "' is discarded because it does not contain providers for '" + ModelicaMLServices.getName(dm) + "'.");
			}
		}
	}
	
	private void instantiate(HashSet<Element> models, ClassInstantiation V) {
		if (models != null) {
			for (Element model : models) {
				ClassInstantiation instantiation = new ClassInstantiation((Class) model, new HashSet<Element>(M_L), false);
				instantiation.createTree();

				// TODO: improve it
				instantiation.collectBindingsDataFromUmlModel();
				instantiation.setValueClientOrProviderIndicator(instantiation.getTreeRoot());
				
				I_M.put(model, instantiation);
				
				// add child
				V.getTreeRoot().addChild(instantiation.getTreeRoot());
				instantiation.getTreeRoot().setIsRoot(false);
				V.getAllTreeObjects().addAll(instantiation.getAllTreeObjects());
			}
		}
	}
	
	private void inferBindings(ClassInstantiation V, HashSet<TreeObject> clients){
		
		// re-initialize the helper because V has changed.
		deriveCodeHelper.initialize( (Package) LM_S, V, V.getTreeRoot(), cd.getAllMediators(), false); 
		
		// recollect bindings TODO: Improve it ...
		V.collectBindingsDataFromUmlModel();

		// mark which components are clients TODO: Improve it ...
		V.setValueClientOrProviderIndicator(V.getTreeRoot());
		
		for (TreeObject c : clients) {
			if (!bindingExistsFor(c)) {
				BindingData bd = inferBinding(c, V);
				if (bd.isBindingPossible) {
					B_M.put(c, bd);
				}
			}
		}
	}
	
	private boolean bindingExistsFor(TreeObject c){
		if (B_M.get(c) != null) {
			return true;
		}
		return false;
	}
	
	private BindingData inferBinding(TreeObject c, ClassInstantiation V) {
		
//		System.err.println("Inferring binding for " + c.getDotPath());
		
		BindingData bd = new BindingData();
		
		deriveCodeHelper.deriveBindingCodeForClient(c, false, true);
		
		bd.bindingExpression = deriveCodeHelper.getCode(); 
		bd.cia = deriveCodeHelper.getClientElement();
		bd.ma = deriveCodeHelper.getMediatorElement(); 
		bd.isBindingPossible = (deriveCodeHelper.getCode() != null) || (deriveCodeHelper.getCode() == null && deriveCodeHelper.isUserSelectionRequired());
		
		bd.providers.addAll(deriveCodeHelper.getProviders());
		bd.providersForSelection.addAll(deriveCodeHelper.getProvidersForSelection());
		
		return bd;
	}
	
	
	private boolean allMadatoryClientsSatisfied(ClassInstantiation V){
		
		// if there are no clients at all -> no satisfaction
		if (V.getAllMandatoryClients().size() == 0) {
			return false;
		}
		
		for (TreeObject ck : V.getAllMandatoryClients()) {
			if (!bindingExistsFor(ck)) {
				return false;
			}
		}
		return true;
	}
	
	
	private boolean useAtLeastOneProviderFrom(HashSet<TreeObject> clients, TreeObject cip){
		// note there is no need to mark clients because it was done before in inferBindings
		for (TreeObject ck : clients ) {
			BindingData binding = B_M.get(ck);
			if (isDefined(binding) && binding.isBindingPossible) {
				HashSet<TreeObject> providers = binding.getAllPossibleProviders();
				if (!clientIsFrom(ck,cip) && atLeastOneProviderIsFrom(cip, providers)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
//	private boolean usesAtLeastOneProviderFrom(TreeObject cik, TreeObject cip){
//		// note there is no need to mark clients because it was done before in inferBindings
//		for (TreeObject ck : I_M.get(cik.getSelectedClass()).getAllClients() ) {
//			BindingData binding = B_M.get(ck);
//			if (isDefined(binding) && binding.isBindingPossible) {
//				HashSet<TreeObject> providers = binding.getAllPossibleProviders();
//				if (!clientIsFrom(ck,cip) && atLeastOneProviderIsFrom(cip, providers)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
	
	private boolean clientIsFrom(TreeObject ck, TreeObject cip){
		HashSet<TreeObject> treeObjects = I_M.get(cip.getSelectedClass()).getAllTreeObjects();
		if (treeObjects.contains(ck)) {
			return true;
		}
		return false;
	}
	
	
	private boolean atLeastOneProviderIsFrom(TreeObject cip, HashSet<TreeObject> providers){
		HashSet<TreeObject> treeObjects = I_M.get(cip.getSelectedClass()).getAllTreeObjects();
		for (TreeObject provider : providers) {
			if (treeObjects.contains(provider)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isDefined(Object o){
		if (o != null) {
			return true;
		}
		return false;
	}
	
	
	private int getAdditionalInstantionsNumber(TreeObject ci){
		HashSet<TreeObject> mandClients = I_M.get(ci.getSelectedClass()).getAllMandatoryClients();
		int additionalRequiredInstantiationsNumber = 0;
		
		for (TreeObject ck : mandClients) {
			BindingData binding = B_M.get(ck);
			Element mediator = binding.getMediator();
			if (!mediatorOpContainsReductionFunctions(mediator)) {
				int numberOfProviders = binding.getProvidersForSelection().size();
				if (numberOfProviders > additionalRequiredInstantiationsNumber ) {
					// note, "- 1" because we are looking for the ADDITIONAL number of instantiations
					additionalRequiredInstantiationsNumber = numberOfProviders - 1;
				}
			}
		}
		return additionalRequiredInstantiationsNumber;
	}
	
	
	
	private boolean mediatorOpContainsReductionFunctions(Element mediator){
		String mediatorOperation = DeriveValueBindingCodeUtls.getOperationSpecification(mediator, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
		return DeriveValueBindingCodeUtls.isValidMediatorMultipleItemsScript(mediatorOperation);
	}
	
	
	private List<Element> getRequirementsFor(Element scenario, String selectionOptions){
		if (selectionOptions.equals("all")) {
			return ModelicaMLServices.getSortedByName(cd.getAllRequirements());
		}
		if (selectionOptions.equals("explicitRelations")) {
			return ModelicaMLServices.getSortedByName(cd.getScenarioToReq().get(scenario));
		}
		return null;
	}
	
	// Bidnings data -------------------------------------------------------------------------------
	
	private class BindingData {
		String bindingExpression = null; // (binding expression, i.e., the right-hand expression of the assignment), 
		boolean isBindingPossible = false; //is possible to infer binding including manual decisions?  
		Element cia = null; // (actual client used, i.e., ci or one of its parents in I) 
		Element ma; // actual mediator used 
		HashSet<TreeObject> providers = new HashSet<TreeObject>(); // {po … pn} set of providers referenced by mc and contained in I 
		HashSet<TreeObject> providersForSelection = new HashSet<TreeObject>(); // {po … pn} set of providers referenced by mc and contained in I for manual decision
		
		public HashSet<TreeObject> getProvidersForSelection(){
			return providersForSelection;
		}
		
		public HashSet<TreeObject> getProviders(){
			return providers;
		}
		
		public Element getMediator(){
			return ma;
		}
		
		public HashSet<TreeObject> getAllPossibleProviders(){
			HashSet<TreeObject> ppSet = new HashSet<TreeObject>();
			ppSet.addAll(providers);
			ppSet.addAll(providersForSelection);
			
			return ppSet;
		}
	}
	
	
	
	// Utls ---------------------------------------
	private void log(String message){
		
		// initial
		if (this.log == null) { this.log = "";}
		
		// add a new message
		this.log += message + "\n";
		System.err.println(message);
	}

	
	private List<Element> toList(HashSet<Element> elements) {
		List<Element> list = new ArrayList<Element>();
		
		for (Element element : elements) {
			list.add(element);
		}
		
		return list;
	}
	
	private HashSet<TreeObject> newTOSet(TreeObject element) {
		HashSet<TreeObject> newList = new HashSet<TreeObject>();
		newList.add(element);
		return newList;
	}
	
	private List<Element> newList(Element element) {
		List<Element> newList = new ArrayList<Element>();
		newList.add(element);
		return newList;
	}
	
	private HashSet<Element> newHashSet(Element element) {
		HashSet<Element> newSet = new HashSet<Element>();
		newSet.add(element);
		return newSet;
	}
	

	// Getters and Setters -------------------------------
	public List<Element> getVM_L() {
		return VM_L;
	}


	public void setVM_L(List<Element> vM_L) {
		VM_L = vM_L;
	}
	
	
	
	
	
//	private void addChildren(ClassInstantiation V, HashSet<TreeObject> children){
//	for (TreeObject treeObject : children) {
//		V.getTreeRoot().addChild(treeObject);
//	}
//}

//private HashSet<TreeObject> getInstantiation(HashSet<Element> models) {
//	HashSet<TreeObject> set = new HashSet<TreeObject>();
//	if (models != null ) {
//		for (Element model : models) {
//			TreeObject to = I_M.get(model);
//			if (to != null) {
//				set.add(to);
//			}
//		}
//	}
//	return set;
//}
}
