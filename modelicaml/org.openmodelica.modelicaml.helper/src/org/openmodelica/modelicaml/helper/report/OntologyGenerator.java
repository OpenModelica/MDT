package org.openmodelica.modelicaml.helper.report;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.coode.owlapi.rdf.model.RDFLiteralNode;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.internal.registry.OffsetTable;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.datacollection.ElementsCollector;
import org.openmodelica.modelicaml.common.helpers.VerificationServices;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.helper.structures.GeneratedModelsData;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.RDFLiteral;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationValueVisitor;
import org.semanticweb.owlapi.model.OWLAnnotationValueVisitorEx;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataVisitor;
import org.semanticweb.owlapi.model.OWLDataVisitorEx;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectVisitor;
import org.semanticweb.owlapi.model.OWLObjectVisitorEx;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.model.SetOntologyID;
import org.semanticweb.owlapi.util.DefaultPrefixManager;


public class OntologyGenerator {
	
	public static final String VVDR_ONTOLOGY_URI = "http://www.openmodelica.org/modelicaml/ontology/vvdr";
	public static final String VVDR_ONTOLOGY_FILENAME = "vvdr.owl";
	
	private IProgressMonitor monitor;
	private ProgressMonitorDialog progressDialog;
	
	// onty for testing
	private String mainOntologyPath = "D:/__PROJECTS/2008_PhD/phd_courses/2012_SemanticTechnologiesInOperation/PARTIII/PROJECT/ontology/vvdr.owl";
	
	private String fileName = "report";
	private String fileExtension = ".owl";
	
//	private String newFilePath = "D:/__PROJECTS/2008_PhD/phd_courses/2012_SemanticTechnologiesInOperation/PARTIII/PROJECT/ontology/"+ fileName + fileExtension;
	
	private OWLOntologyManager manager;
	private OWLOntology ontology;
	private OWLDataFactory factory;
	private PrefixManager pm;
	
	private GeneratedModelsData gmd;
	private EObject rootElement;
	
	public OntologyGenerator(GeneratedModelsData gmd, EObject rootElement) {
		
		this.gmd = gmd;
		this.rootElement = rootElement;
	}
	
	
	public String createReport(String projectName, String folderPath, boolean askWhereToStore) throws URISyntaxException, IOException, CoreException, OWLOntologyCreationException, OWLOntologyStorageException{
		
		String filePath = null;
		
		progressDialog = new ProgressMonitorDialog(new Shell());
		monitor = progressDialog.getProgressMonitor();
		
		// open dialog
		progressDialog.open();
		
		// if selected -> ask user where to store the report files
		if (askWhereToStore) {
			// TODO: open file dialog so that the user can select where to store the report
		}
		
		// store files in specified project 
		else {
			// close progress monitor
			progressDialog.close();
			
			return createOntology(folderPath);
		}
		
		// close progress monitor
		progressDialog.close();
		
		return filePath;
	}
	
	public String createOntology(String folderPath) throws OWLOntologyCreationException, OWLOntologyStorageException, URISyntaxException, IOException{
		
		String path = null; // null indicates that no file was created
		
		// load main ontology
		manager = OWLManager.createOWLOntologyManager();
		File fileToLoad = new File(mainOntologyPath); // only for testing
//		File fileToLoad = new File(FileLocator.toFileURL(Platform.getBundle("org.openmodelica.modelicaml.helper").getEntry("sources/ontology/" + VVDR_ONTOLOGY_FILENAME)).toURI());
        // Now load the local copy
        OWLOntology mainOnt = manager.loadOntologyFromOntologyDocument(fileToLoad);
        System.out.println("Loaded ontology: " + mainOnt);
		
        
        // new ontology file path
		String newFilePath = folderPath + "/" + fileName + fileExtension;

        IRI ontologyIRI = IRI.create(VVDR_ONTOLOGY_URI + "/" + VerificationServices.getTimeStamp());
        ontology = manager.createOntology(ontologyIRI);
       	
        factory = manager.getOWLDataFactory();
        
        // import vvDR ontology
        OWLImportsDeclaration importDeclaration = factory.getOWLImportsDeclaration(IRI.create(VVDR_ONTOLOGY_URI));
        AddImport ai = new AddImport(ontology, importDeclaration);
        manager.applyChange(ai);
        
        pm = new DefaultPrefixManager(VVDR_ONTOLOGY_URI);
        
        // add requirement individuals
        addRequirementIndividuals();
        
        // getDesignVerification
        addDesignVerificationIndividuals();
        
        // add design alternative individual
        addDesignModelIndividuals();
        
        // add scenario individuals
        addScenarioIndividuals();
        
        // TODO: add violated requirements
        
        // TODO: add not evaluated requirements
        
        // TODO: add not violated requirements
        
        
        
//		String generatedModelPackageName = StringUtls.replaceSpecChar( ((NamedElement)gmd.getGeneratedModelsPackage()).getName()) + "_" + VerificationServices.getTimeStamp();;
		monitor.setTaskName("Copying Report Data ...");
		
		// copy folder
//		File folderToCopy = new File(FileLocator.toFileURL(Platform.getBundle("org.openmodelica.modelicaml.helper").getEntry("sources/reportData")).toURI());

//		IFileSystem fileSystem = EFS.getLocalFileSystem();
//		IFileStore reportData = fileSystem.getStore(folderToCopy.toURI());
//		IFileStore reportDataCopy = fileSystem.getStore(java.net.URI.create(folderPath + "/" + generatedModelPackageName + "/" + "reportData"));
//		reportData.copy(reportDataCopy, EFS.OVERWRITE, monitor);
		
//		newFilePath = folderPath + "/" + generatedModelPackageName + "/" + fileName + fileExtension;
        // save the new ontology
		
        OutputStream o = new FileOutputStream(newFilePath);
        manager.saveOntology(ontology, o);
        
        // return the new filepath if the only if the file was created.
        File ontFile = new File(newFilePath);
		if (ontFile.exists()) {
			path = newFilePath;
		}  
		
		return path;
	}
	
	
	private void addDesignModelIndividuals(){
		
		// get class
        OWLClass designAlternativeClass = factory.getOWLClass("#DesignAlternative", pm);
        
		for (Element derificationModel : gmd.getSystemModels()) {
			String name = ((NamedElement)derificationModel).getName();
			String qName = ((NamedElement)derificationModel).getQualifiedName();

	        // create individual
	        OWLNamedIndividual ind = factory.getOWLNamedIndividual("#"+StringUtls.replaceSpecChar(qName), pm);
	        
	        // add to ontology
	        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(designAlternativeClass, ind);
	        // Add the class assertion
	        manager.addAxiom(ontology, classAssertion);
	        
	        // add requirement name
	        OWLDataProperty nameProperty = factory.getOWLDataProperty("#name", pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqName= factory.getOWLDataPropertyAssertionAxiom(nameProperty, ind, name);
	        manager.addAxiom(ontology, dataPropertyAxiomReqName);
	        
	    	// add requirement qualified name
	        OWLDataProperty qNameProperty = factory.getOWLDataProperty("#qualifiedName", pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqQName= factory.getOWLDataPropertyAssertionAxiom(qNameProperty, ind, qName);
	        manager.addAxiom(ontology, dataPropertyAxiomReqQName);	        
		}
	}
	
	private void addDesignVerificationIndividuals(){
		
		// get class
        OWLClass designVerificationClass = factory.getOWLClass("#DesignVerification", pm);

		for (Element derificationModel : gmd.getGeneratedModels()) {
			String name = ((NamedElement)derificationModel).getName();
			String qName = ((NamedElement)derificationModel).getQualifiedName();

	        // create individual
	        OWLNamedIndividual ind = factory.getOWLNamedIndividual("#"+StringUtls.replaceSpecChar(qName), pm);
	        
	        // add to ontology
	        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(designVerificationClass, ind);
	        // Add the class assertion
	        manager.addAxiom(ontology, classAssertion);
	        
	        // add requirement name
	        OWLDataProperty nameProperty = factory.getOWLDataProperty("#name", pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqName= factory.getOWLDataPropertyAssertionAxiom(nameProperty, ind, name);
	        manager.addAxiom(ontology, dataPropertyAxiomReqName);
	        
	    	// add requirement qualified name
	        OWLDataProperty qNameProperty = factory.getOWLDataProperty("#qualifiedName", pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqQName= factory.getOWLDataPropertyAssertionAxiom(qNameProperty, ind, qName);
	        manager.addAxiom(ontology, dataPropertyAxiomReqQName);	   
	        
		}
	}
	
	private void addScenarioIndividuals(){
		
		// get class
        OWLClass scenarioClass = factory.getOWLClass("#Scenario", pm);

		for (Element designVerificationModel : gmd.getGeneratedModels()) {
			
			for (TreeObject scenarioTreeObject : gmd.getScenarios(designVerificationModel)) {
				Element scenario = scenarioTreeObject.getUmlElement();
				
				String name = ((NamedElement)scenario).getName();
				String qName = ((NamedElement)scenario).getQualifiedName();

		        // create individual
		        OWLNamedIndividual ind = factory.getOWLNamedIndividual("#"+StringUtls.replaceSpecChar(qName), pm);
		        
		        // add to ontology
		        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(scenarioClass, ind);
		        // Add the class assertion
		        manager.addAxiom(ontology, classAssertion);
		        
		        // add requirement name
		        OWLDataProperty nameProperty = factory.getOWLDataProperty("#name", pm);
		        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqName= factory.getOWLDataPropertyAssertionAxiom(nameProperty, ind, name);
		        manager.addAxiom(ontology, dataPropertyAxiomReqName);
		        
		    	// add requirement qualified name
		        OWLDataProperty qNameProperty = factory.getOWLDataProperty("#qualifiedName", pm);
		        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqQName= factory.getOWLDataPropertyAssertionAxiom(qNameProperty, ind, qName);
		        manager.addAxiom(ontology, dataPropertyAxiomReqQName);	  
			}
		}
	}
	
	
	private void addRequirementIndividuals(){
		
		// get class
        OWLClass reqClass = factory.getOWLClass("#Requirement", pm);
        
		for (Element requirement : gmd.getAllFoundRequirements()) {
			
			String name = ((NamedElement)requirement).getName();
			String qName = ((NamedElement)requirement).getQualifiedName();
			String id = ModelicaMLServices.getRequirementID(requirement);
			String text = ModelicaMLServices.getRequirementText(requirement);

	        // create individual
	        OWLNamedIndividual reqInd = factory.getOWLNamedIndividual("#"+StringUtls.replaceSpecChar(qName), pm);
	        
	        // add to ontology
	        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(reqClass, reqInd);
	        // Add the class assertion
	        manager.addAxiom(ontology, classAssertion);
	        
	        // add requirement name
	        OWLDataProperty reqNameProperty = factory.getOWLDataProperty("#name", pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqName= factory.getOWLDataPropertyAssertionAxiom(reqNameProperty, reqInd, name);
	        manager.addAxiom(ontology, dataPropertyAxiomReqName);
	        
	    	// add requirement qualified name
	        OWLDataProperty reqQNameProperty = factory.getOWLDataProperty("#qualifiedName", pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqQName= factory.getOWLDataPropertyAssertionAxiom(reqQNameProperty, reqInd, qName);
	        manager.addAxiom(ontology, dataPropertyAxiomReqQName);
	        
	        // add id string
	        OWLDataProperty reqIdProperty = factory.getOWLDataProperty("#ID", pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqId = factory.getOWLDataPropertyAssertionAxiom(reqIdProperty, reqInd, id);
	        manager.addAxiom(ontology, dataPropertyAxiomReqId);
	        
	    	// add requirement text
	        OWLDataProperty reqTextProperty = factory.getOWLDataProperty("#text", pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqText = factory.getOWLDataPropertyAssertionAxiom(reqTextProperty, reqInd, text);
	        manager.addAxiom(ontology, dataPropertyAxiomReqText);
	        
		}
	}

}
