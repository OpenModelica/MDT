package org.openmodelica.modelicaml.view.valuebindings.exchange;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.TypedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeUtls;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeBuilder;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;
import org.w3c.dom.Document;


public class XMLMediatorsExporter {
	
	private final String FILE_EXTENSION = "xml";
	public final static String DEFAULT_FILE_NAME						= "bindingSpecification";
	
	private String projectName;
	private String folderName;
	
	private String filePath;
		
	public final static String XMLTagName_bindings 						= "bindingSpecification";
	public final static String XMLAttrName_exportDate 					= "exportDate";
	
	public final static String XMLTagName_mediator 						= "mediator";
	public final static String XMLTagName_client 						= "client";
	public final static String XMLTagName_provider	 					= "provider";
	public final static String XMLTagName_template 						= "template";
	
	public final static String XMLTagName_preferred 					= "preferred";
	public final static String XMLAttrName_clientInstancePath 			= "clientInstancePath";
	public final static String XMLAttrName_providerInstancePath			= "providerInstancePath";
	
	
	public final static String XMLAttrName_language 					= "language";
	public final static String XMLAttrName_qualifiedName 				= "qualifiedName";
	public final static String XMLAttrName_ownerQualifiedName 			= "ownerQualifiedName";
	
	public final static String XMLAttrName_name 						= "name";
	public final static String XMLAttrName_requiredType 				= "requiredType";
	public final static String XMLAttrName_mandatory 					= "mandatory";
	
	public final static String XMLTagName_comment 						= "comment";
	public final static String XMLTagName_date 							= "date";
	
	private IProgressMonitor monitor;
	private ProgressMonitorDialog progressDialog;
	
	private TreeBuilder treeBuilder;
	
	private String bindingTemplateLanguageName = "ModelicaML";
	
	// XML document
	public Document document = null;
	
	public XMLMediatorsExporter(){}
		
	public void exportToXML(TreeBuilder treeBuilder, String projectName, String folderName, boolean askWhereToStore) {
		
		this.projectName = projectName;
		this.folderName = folderName;
		this.treeBuilder = treeBuilder;
		
		if (treeBuilder.getValueMediators() != null && treeBuilder.getValueMediators().size() > 0) {
			progressDialog = new ProgressMonitorDialog(new Shell());
			monitor = progressDialog.getProgressMonitor();
			
			progressDialog.open();
			
			fillDocument();
			try {
				createFiles(askWhereToStore);
				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			MessageDialog.openInformation(new Shell(), "Mediators export", "No mediators were found.");
		}
	}
	
	
	public void fillDocument(){
		
		monitor.setTaskName("Creating XML file ...");
		
		try {
    		// document
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder builder = factory.newDocumentBuilder();
    		document = builder.newDocument();
    		
    		// root element
    		org.w3c.dom.Element rootElement = createRootElement(document);
    		document.appendChild(rootElement);

    		// add mediators
    		monitor.setTaskName("Exporting mediators ...");
    		rootElement = appendMediatorItems(rootElement);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}
	
	public org.w3c.dom.Element createRootElement(Document document) {
		
		org.w3c.dom.Element rootElement = document.createElement(XMLTagName_bindings);

		// add date 
//		org.w3c.dom.Element dateXMLElement = document.createElement(XMLTagName_date);
//		dateXMLElement.setTextContent(getDate("yyyy.MM.dd HH:mm:ss"));
//		rootElement.appendChild(dateXMLElement);
		
		rootElement.setAttribute(XMLAttrName_exportDate, getDate("yyyy.MM.dd HH:mm:ss"));
		
		return rootElement;
	}
	
	
	private org.w3c.dom.Element appendMediatorItems(org.w3c.dom.Element documentNode){
		for (Element mediator: ModelicaMLServices.getSortedByName(treeBuilder.getValueMediators())) {
			org.w3c.dom.Element mediatorXMLElement = document.createElement(XMLTagName_mediator);
			
			mediatorXMLElement.setAttribute(XMLAttrName_name, StringEscapeUtils.escapeXml(ModelicaMLServices.getName(mediator)));
			mediatorXMLElement.setAttribute(XMLAttrName_requiredType, ModelicaMLServices.getName(((TypedElement)mediator).getType()).replace("Modelica", ""));
//			mediatorXMLElement.setAttribute(XMLAttrName_ownerQualifiedName, StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(mediator.getOwner())));
			mediatorXMLElement.setAttribute(XMLAttrName_ownerQualifiedName, StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedNameNormalized(mediator.getOwner(), "::", ".")));
			
			appendComments(mediatorXMLElement, mediator);
			appendMediatorTemplate(mediatorXMLElement, mediator);
			
			monitor.setTaskName("Collecting clients for '"+ModelicaMLServices.getName(mediator)+"' ...");
			appendClients(mediatorXMLElement, mediator);
			
			monitor.setTaskName("Collecting providers for '"+ModelicaMLServices.getName(mediator)+"' ...");
			appendProviders(mediatorXMLElement, mediator);

			monitor.setTaskName("Collecting preffered bindings for '"+ModelicaMLServices.getName(mediator)+"' ...");
			appendPreferred(mediatorXMLElement, mediator);
			
			documentNode.appendChild(mediatorXMLElement);
		}

		return documentNode;
	}
	
	
	private org.w3c.dom.Element appendMediatorTemplate(org.w3c.dom.Element documentNode, Element mediator){
		
		String template = DeriveValueBindingCodeUtls.getOperationSpecification(mediator, Constants.stereotypeQName_ValueMediator, Constants.propertyName_operation);
		if (template != null) {
			template = template.trim();
			
			if (!template.isEmpty()) {
				org.w3c.dom.Element mediatorTemplateXMLElement = document.createElement(XMLTagName_template);
				mediatorTemplateXMLElement.setTextContent(template);
				mediatorTemplateXMLElement.setAttribute(XMLAttrName_language, getBindingTemplateLanguageName());
				
				documentNode.appendChild(mediatorTemplateXMLElement);
			}
		}
		
		return documentNode;
	}
	
	private String getDate(String format){
		String dateFormat = format;
		if (dateFormat == null) {
			dateFormat = "yyyy.MM.dd HH:mm:ss";
		}
		Calendar c1 = Calendar.getInstance(); // today
		Date date = c1.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		
		return sdf.format(date);
	}
	
	
	private org.w3c.dom.Element appendClients(org.w3c.dom.Element documentNode, Element mediator){
		HashSet<Element> clients = treeBuilder.valueMediatorToValueClients.get(mediator); 
		
		if (clients != null) {
			
			for (Element client: clients) {
				
				Boolean isMandatory = false;
				if (treeBuilder.getRequiredValueClients(mediator).contains(client)) {
					isMandatory = true;
				}
				
				org.w3c.dom.Element clientXMLElement = document.createElement(XMLTagName_client);
				if (isMandatory) {
					clientXMLElement.setAttribute(XMLAttrName_mandatory, isMandatory.toString());	
				}
//				clientXMLElement.setAttribute(XMLAttrName_name, StringEscapeUtils.escapeXml(ModelicaMLServices.getName(client)));
//				clientXMLElement.setAttribute(XMLAttrName_qualifiedName, StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(client)));
				clientXMLElement.setAttribute(XMLAttrName_qualifiedName, StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedNameNormalized(client, "::", ".")));
				

				// template
				String template = getClientTemplate(mediator, client);
				if (template != null) {
					org.w3c.dom.Element templateElement = document.createElement(XMLTagName_template);
					templateElement.setTextContent(template);
					templateElement.setAttribute(XMLAttrName_language, getBindingTemplateLanguageName());
					
					clientXMLElement.appendChild(templateElement);

				}

				documentNode.appendChild(clientXMLElement);
			}
		}
		return documentNode;
	}
	
	
	private org.w3c.dom.Element appendProviders(org.w3c.dom.Element documentNode, Element mediator){
		HashSet<Element> providers = treeBuilder.valueMediatorToValueProviders.get(mediator); 
		
		if (providers != null) {
			
			for (Element provider: providers) {
				
				org.w3c.dom.Element providerXMLElement = document.createElement(XMLTagName_provider);
//				clientXMLElement.setAttribute(XMLAttrName_name, StringEscapeUtils.escapeXml(ModelicaMLServices.getName(provider)));
//				providerXMLElement.setAttribute(XMLAttrName_qualifiedName, StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedName(provider)));
				providerXMLElement.setAttribute(XMLAttrName_qualifiedName, StringEscapeUtils.escapeXml(ModelicaMLServices.getQualifiedNameNormalized(provider, "::", ".")));
				
				// template
				String template = getProviderTemplate(mediator, provider);
				if (template != null) {
					org.w3c.dom.Element templateElement = document.createElement(XMLTagName_template);
					templateElement.setTextContent(template);
					templateElement.setAttribute(XMLAttrName_language, getBindingTemplateLanguageName());
					
					providerXMLElement.appendChild(templateElement);
				}
				
				documentNode.appendChild(providerXMLElement);
			}
		}
		return documentNode;
	}
	
	
	private org.w3c.dom.Element appendPreferred(org.w3c.dom.Element documentNode, Element mediator){
		List<String> preferred = TreeUtls.getStringListPropertyFromElement(mediator, Constants.stereotypeQName_ValueMediator, Constants.propertyName_preferredProviders);
		
		if (preferred != null && preferred.size() > 0) {
			
			for (String preferredProviderAssignment : preferred) {
				String[] splitted = preferredProviderAssignment.split(Constants.preferredProvidersAssignmentSeparator);
				if (splitted.length == 2 ) { // if there is a pair with the separator
					String clientPath = splitted[0].trim();
					String providerPath = splitted[1].trim();

					org.w3c.dom.Element preferredXMLElement = document.createElement(XMLTagName_preferred);
					preferredXMLElement.setAttribute(XMLAttrName_clientInstancePath, StringEscapeUtils.escapeXml(clientPath));
					preferredXMLElement.setAttribute(XMLAttrName_providerInstancePath, StringEscapeUtils.escapeXml(providerPath));
					
					documentNode.appendChild(preferredXMLElement);
				}
			}
		}
		return documentNode;
	}
	
	
	private org.w3c.dom.Element appendComments(org.w3c.dom.Element documentNode, Element element) {
		String string = "";
		if (element.getOwnedComments() != null && element.getOwnedComments().size() > 0) {
			
			if (element instanceof Element) {
				for (Comment comment : element.getOwnedComments()) {
					if (comment.getBody() != null) {
						string += StringEscapeUtils.escapeXml(comment.getBody());
					}
				}
			}
			
			if (!string.isEmpty()) {
				org.w3c.dom.Element commentXMLElement = document.createElement(XMLTagName_comment);
				commentXMLElement.setTextContent(string);
				
				documentNode.appendChild(commentXMLElement);
			}
			
		}
		return documentNode;
	}
	
	
	private String getClientTemplate(Element mediator, Element client){
		String template = null;
		if (mediator != null ) {
			EList<Dependency> dependencies = TreeUtls.getMediatorDependency((NamedElement)mediator, (NamedElement)client, Constants.stereotypeQName_ProvidesValueFor);
			if (dependencies.size() > 1 ) {
				// stop here, this error leads to the cancellation of code derivation.
//				String message = "NOT VALID: The client '"+((NamedElement)client).getQualifiedName()+"' is referenced multiple times by the mediator '"+((NamedElement)mediator).getQualifiedName()+"'. Only one reference is allowed.";
			}
			else {
				template = DeriveValueBindingCodeUtls.getOperationSpecification(dependencies.get(0), Constants.stereotypeQName_ProvidesValueFor, Constants.propertyName_operation);
			}
		}
		
		if (template != null) {
			template = template.trim();
		}
		return template;
	}
	
	private String getProviderTemplate(Element mediator, Element provider){
		String template = null;
		if (mediator != null ) {
			EList<Dependency> dependencies = TreeUtls.getMediatorDependency((NamedElement)mediator, (NamedElement)provider, Constants.stereotypeQName_ObtainsValueFrom);
			if (dependencies.size() > 1 ) {
				// stop here, this error leads to the cancellation of code derivation.
//				String message = "NOT VALID: There are multiple references between the mediator '" + ((NamedElement)mediator).getQualifiedName() + "' and the provider '"+((NamedElement)provider).getQualifiedName()+"'";
			}
			else {
				template = DeriveValueBindingCodeUtls.getOperationSpecification(dependencies.get(0), Constants.stereotypeQName_ObtainsValueFrom, Constants.propertyName_operation);
			}
		}
		
		if (template != null) {
			template = template.trim();
		}
		return template;
	}
	
	// File
	private String createFiles(boolean askWhereToStore) throws URISyntaxException, IOException, CoreException{
			
			String folderPath = getFolderPath();
			
			// if selected -> ask user where to store the report files
			if (askWhereToStore) {
				// Open file dialog so that the user can select where to store the report
				folderPath = getFilePath();
				filePath = storeFile(folderPath);
			}
			
			// store files in specified project 
			else {
				File folder = new File(folderPath);
				
				boolean folderCreated = false;
				if (!folder.exists()) {
					 folderCreated = new File(folderPath).mkdir();
				}
				
				if (folder.exists() || folderCreated) {
					
					// create report files: XML, HTML 
					filePath = storeFile(folderPath + "/" + DEFAULT_FILE_NAME + "." + FILE_EXTENSION);
					
					// TODO: export mediators as OWL
	//				Boolean generateOWLReportFiles = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "generateOWLReportFiles", false, null);
	//
	//				if (generateOWLReportFiles) {
	//					// create OWL report
	//					monitor.setTaskName("Creating OWL report ...");
	//					OntologyGenerator og = new OntologyGenerator(gmd, rootElement, progressDialog);
	//					try {
	//						// take the folder where the report.html is: 
	//						Path path = new Path(filePath);
	//						IPath ontologyFolderPath = path.removeLastSegments(1);
	//						
	//						// create owl file
	//						String ontologyFilePath = og.createOntology(ontologyFolderPath.toOSString());
	//						
	//					} catch (OWLOntologyCreationException e) {
	//						// TODO Auto-generated catch block
	//						e.printStackTrace();
	//					} catch (OWLOntologyStorageException e) {
	//						// TODO Auto-generated catch block
	//						e.printStackTrace();
	//					}
	//				}
					
					// close progress monitor
					progressDialog.close();
				}
			}
			
			// close progress monitor
			progressDialog.close();
	
			// refresh the projects browser
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
			
			return filePath;
		}

	
	private String storeFile(String absoluteFilePath) throws URISyntaxException, IOException, CoreException{

		monitor.setTaskName("Storing files ...");
		
		if (absoluteFilePath != null) {
			try {
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(document);
//				StreamResult result = new StreamResult(new File(absoluteFilePath, "." + FILE_EXTENSION));
				
				File file = new File(absoluteFilePath);
				boolean go = true;
				
				if (file.exists()) {
					go = MessageDialog.openQuestion(new Shell(), "Overwrite?", absoluteFilePath + " \nalready exists. \n\nOverwrite?");
				}
				if (go) {
					StreamResult result = new StreamResult(new File(absoluteFilePath));
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
					transformer.transform(source, result);
				}
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			}

				/*
				 * http://docs.oracle.com/javase/1.4.2/docs/api/java/io/BufferedWriter.html
				 * "In general, a Writer sends its output immediately to the underlying character or byte stream. 
				 *  Unless prompt output is required, it is advisable 
				 *  to wrap a BufferedWriter around any Writer whose write() operations may be costly, 
				 *  such as FileWriters and OutputStreamWriters."
				 *  
				 *  Use: new BufferedWriter(new FileWriter("foo.out"));
				 */
				
				// write xml 
//				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(absoluteFilePath),"UTF-8"));
//				out.write(fileContent);
//				out.close();
		}
		
			
		progressDialog.close();
		
		return absoluteFilePath;
	}
		
	// Utls
	private String getFilePath(){
		// Let the user select the file for each model 
		FileDialog fileDialog = new FileDialog(new Shell(), SWT.SAVE);
		fileDialog.getParent().setFocus();
		fileDialog.setFilterPath(getFolderPath());
		String[] extensionFilter = {"*." + FILE_EXTENSION, "*.*"};
		fileDialog.setFilterExtensions(extensionFilter);
		
		fileDialog.setFileName(DEFAULT_FILE_NAME);
		fileDialog.setText("Select the location for storing the exported mediators.");
		
		String filePath = fileDialog.open();
		return filePath;
	}
	
	
	private String getFolderPath(){
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject iProject = root.getProject(projectName);
		
		String projectPath = iProject.getLocationURI().toString().replaceFirst("file:\\/", "");
		String folderPath = projectPath;
		if (folderName != null) { 	folderPath = projectPath + "/" + folderName; }
		else {						folderPath = projectPath;}
		
		return folderPath;		
	}

	public String getBindingTemplateLanguageName() {
		return bindingTemplateLanguageName;
	}

	public void setBindingTemplateLanguageName(
			String bindingTemplateLanguageName) {
		this.bindingTemplateLanguageName = bindingTemplateLanguageName;
	}
	
}
