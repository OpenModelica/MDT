package org.openmodelica.modelicaml.helper.generators.example;

import java.util.HashSet;

import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;

public class BindingData {
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
