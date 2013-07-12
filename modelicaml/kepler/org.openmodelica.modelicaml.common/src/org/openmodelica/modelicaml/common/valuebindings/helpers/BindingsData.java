package org.openmodelica.modelicaml.common.valuebindings.helpers;

import java.util.HashSet;

import org.eclipse.uml2.uml.Element;
import org.openmodelica.modelicaml.common.services.EditorServices;

public class BindingsData {
	
	private static HashSet<Element> clients = new HashSet<Element>();
	private static HashSet<Element> clientsMandatory = new HashSet<Element>();
	private static HashSet<Element> providers = new HashSet<Element>();
	private static HashSet<Element> mediators = new HashSet<Element>();
	
	
	public static void clearAll(){
		clients.clear();
		clientsMandatory.clear();
		providers.clear();
		mediators.clear();
	}
			
	
	public static void setBindingsData(HashSet<Element> clients,
			HashSet<Element> clientsMandatory,
			HashSet<Element> providers,
			HashSet<Element> mediators){
		
		setClients(clients);
		setClientsMandatory(clientsMandatory);
		setProviders(providers);
		setMediators(mediators);
		
		// Refresh the Model Explorer view in order to show clients and providers
		EditorServices.refreshModelExplorerView();
		
	}
	
	public static HashSet<Element> getClients() {
		return clients;
	}

	public static void setClients(HashSet<Element> clients) {
		BindingsData.clients = clients;
	}

	public static HashSet<Element> getClientsMandatory() {
		return clientsMandatory;
	}

	public static void setClientsMandatory(HashSet<Element> clientsMandatory) {
		BindingsData.clientsMandatory = clientsMandatory;
	}

	public static HashSet<Element> getProviders() {
		return providers;
	}

	public static void setProviders(HashSet<Element> providers) {
		BindingsData.providers = providers;
	}

	public static HashSet<Element> getMediators() {
		return mediators;
	}

	public static void setMediators(HashSet<Element> mediators) {
		BindingsData.mediators = mediators;
	}

}
