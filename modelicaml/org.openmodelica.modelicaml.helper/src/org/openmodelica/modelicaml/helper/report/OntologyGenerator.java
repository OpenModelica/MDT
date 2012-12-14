package org.openmodelica.modelicaml.helper.report;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.helpers.VerificationServices;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.helper.structures.GeneratedModelsData;
import org.semanticweb.HermiT.Configuration;
import org.semanticweb.HermiT.Reasoner.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;
import org.semanticweb.owlapi.util.InferredClassAssertionAxiomGenerator;
import org.semanticweb.owlapi.util.InferredDisjointClassesAxiomGenerator;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.semanticweb.owlapi.util.InferredSubClassAxiomGenerator;


public class OntologyGenerator {
	
	public static final String VVDR_ONTOLOGY_URI = "http://www.openmodelica.org/modelicaml/ontology/vvdr";
	public static final String VVDR_ONTOLOGY_FILENAME = "vvdr.owl";
	
	// OWL classes
	public static final String DELIMITER = "#";
	
	public static final String REQUIREMENT = "Requirement";
	public static final String DESIGN_ALTERNATIVE = "DesignAlternative";
	public static final String DESIGN_VERIFICATION = "DesignVerification";
	public static final String SCENARIO = "Scenario";
	public static final String NOT_EVALUATED_REQUIREMENT = "NotEvaluatedRequirement";
	public static final String NOT_VIOLATED_REQUIREMENT = "NotViolatedRequirement";
	public static final String VIOLATED_REQUIREMENT = "ViolatedRequirement";
	
	// OWL data properties
	public static final String ID = "requirementId";
	public static final String REQUIREMENT_TEXT = "requirementText";

	public static final String NAME = "name";
	public static final String QNAME = "qualifiedName";
	
	// OWL object properties
	public static final String CONSIDERED_DESIGN = "consideredDesign";
	public static final String CONSIDERED_REQUIREMENT = "consideredRequirement";
	public static final String CONSIDERED_SCENARIO  = "consideredScenario";
	
	public static final String EVALUATED = "evaluated";
	public static final String USED_MODEL= "usedModel";
	public static final String USED_TO_PRODUCE= "usedToProduce";
	
	// mode
	public static final int NOT_EVALUATED = 0;
	public static final int NOT_VIOLATED = 1;
	public static final int VIOLATED = 2;
	
	private IProgressMonitor monitor;
	private ProgressMonitorDialog progressDialog;
	
	private String fileName = "report";
	private String fileExtension = ".owl";
	
	private OWLOntologyManager manager;
	private OWLOntology ontology;
	private OWLDataFactory factory;
	private PrefixManager pm;
	
	private GeneratedModelsData gmd;
	private EObject rootElement;
	
	// options
	private boolean loadMainOnology = true;
	private boolean copyMainOnologyIntoReportFolder = true;
	
	private boolean createInferredAxioms = false; // TODO: does not work yet.
	
	
	
	
	
	public OntologyGenerator(GeneratedModelsData gmd, EObject rootElement, ProgressMonitorDialog progressDialog) {
		
		this.gmd = gmd;
		this.rootElement = rootElement;
		
		// use the passed progress monitor if provided, create a new one if not.
		if (progressDialog != null) {
			this.progressDialog = progressDialog;
		}
		else {
			this.progressDialog = new ProgressMonitorDialog(new Shell());
		}
		this.monitor = this.progressDialog.getProgressMonitor();
	}
	
	
	
	
	
	public String createOntology(String folderPath) throws OWLOntologyCreationException, OWLOntologyStorageException, URISyntaxException, IOException, CoreException{
		
		String path = null; // null indicates that no file was created
		
		manager = OWLManager.createOWLOntologyManager();
		
		// load main ontologies
		loadMainOntology();
		
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
        
        // add violated requirements
        addEvaluations(VIOLATED);
        
        // add not evaluated requirements
        addEvaluations(NOT_EVALUATED);
        
        // add not violated requirements
        addEvaluations(NOT_VIOLATED);

		
		// copy the main ontology into the report folder
		if (copyMainOnologyIntoReportFolder) {
			monitor.setTaskName("Copying main ontology into report folder ...");
			
//			String fileToCopy = new Path(new File( FileLocator.toFileURL(Platform.getBundle("org.openmodelica.modelicaml.helper").getEntry("sources/ontology/vvdr.owl")).toURI()).getAbsolutePath() ).toOSString();

			IFileSystem fileSystem = EFS.getLocalFileSystem();
			IFileStore mainOntology = fileSystem.getStore(new Path(new File( FileLocator.toFileURL(Platform.getBundle("org.openmodelica.modelicaml.helper").getEntry("sources/ontology/vvdr.owl")).toURI()).getAbsolutePath() ));
			IFileStore mainOntologyCopy = fileSystem.getStore(new Path(folderPath + "/" + VVDR_ONTOLOGY_FILENAME));
			mainOntology.copy(mainOntologyCopy, EFS.OVERWRITE, monitor);
		}
		
		// save the new ontology
		monitor.setTaskName("Storing generated ontology ...");
		
		// new ontology file path
		Path newFilePath = new Path(folderPath + "/" + fileName + fileExtension);
		
        OutputStream o = new FileOutputStream(newFilePath.toOSString());
        manager.saveOntology(ontology, o);
        
        // return the new filepath if the only if the file was created.
        File ontFile = new File(newFilePath.toOSString());
		if (ontFile.exists()) {
			path = newFilePath.toOSString();
		}  
		
		// reason in order to complete the inverseOf relations etc.
		if (createInferredAxioms) {
			monitor.setTaskName("Creating infered axioms ...");
			createInferredAxioms(path);
		}
		
		return path;
	}
	
	
	
	
	public void loadMainOntology() throws URISyntaxException, IOException, OWLOntologyCreationException{
		/*
		 * TODO: see if loading of the main ontology is needed at all. 
		 * Clearly, it will be required for reasoning but probably not for the ontology creation.
		 * When not loaded all used classes and properties of the main ontology are included in the new ontology 
		 * and are replaced when the main ontology is loaded to reasoning in an ontology tool.  
		 */
		
		if (loadMainOnology) { 	// load main ontology

			File fileToLoad = new File(new Path(
					new File(FileLocator.toFileURL(Platform.getBundle("org.openmodelica.modelicaml.helper").getEntry("sources/ontology/" + VVDR_ONTOLOGY_FILENAME)).toURI()).getAbsolutePath()).toOSString()
				); 
			
			// Load the local copy of the main ontology
	        OWLOntology mainOnt = manager.loadOntologyFromOntologyDocument(fileToLoad);
	        System.out.println("Loaded ontology: " + mainOnt);
		}
	}
	

	
	
	
	private void addDesignModelIndividuals(){
		
		// get class
        OWLClass designAlternativeClass = factory.getOWLClass(DELIMITER + DESIGN_ALTERNATIVE, pm);
        
		for (Element derificationModel : gmd.getSystemModels()) {
			
			// name and qualified name of the element
			String name = StringEscapeUtils.escapeXml(((NamedElement)derificationModel).getName());
			String qName = StringEscapeUtils.escapeXml(((NamedElement)derificationModel).getQualifiedName());

	        // create individual
//	        OWLNamedIndividual ind = factory.getOWLNamedIndividual(DELIMITER+StringUtls.replaceSpecChar(qName), pm);
			OWLNamedIndividual ind = factory.getOWLNamedIndividual(DELIMITER+StringUtls.replaceSpecChar(name), pm);
	        
	        // add to ontology
	        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(designAlternativeClass, ind);
	        // Add the class assertion
	        manager.addAxiom(ontology, classAssertion);
	        
	        // add requirement name
	        OWLDataProperty nameProperty = factory.getOWLDataProperty(DELIMITER + NAME, pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqName= factory.getOWLDataPropertyAssertionAxiom(nameProperty, ind, name);
	        manager.addAxiom(ontology, dataPropertyAxiomReqName);
	        
	    	// add requirement qualified name
	        OWLDataProperty qNameProperty = factory.getOWLDataProperty(DELIMITER + QNAME, pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqQName= factory.getOWLDataPropertyAssertionAxiom(qNameProperty, ind, qName);
	        manager.addAxiom(ontology, dataPropertyAxiomReqQName);	        
		}
	}
	
	private void addDesignVerificationIndividuals(){
		
		// get class
        OWLClass designVerificationClass = factory.getOWLClass(DELIMITER + DESIGN_VERIFICATION, pm);

		for (Element derificationModel : gmd.getGeneratedModels()) {
			
			// name and qualified name of the element
			String name = StringEscapeUtils.escapeXml(((NamedElement)derificationModel).getName());
			String qName = StringEscapeUtils.escapeXml(((NamedElement)derificationModel).getQualifiedName());

	        // create individual
//	        OWLNamedIndividual ind = factory.getOWLNamedIndividual(DELIMITER+StringUtls.replaceSpecChar(qName), pm);
			OWLNamedIndividual ind = factory.getOWLNamedIndividual(DELIMITER+StringUtls.replaceSpecChar(name), pm);
	        
	        // add to ontology
	        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(designVerificationClass, ind);
	        // Add the class assertion
	        manager.addAxiom(ontology, classAssertion);
	        
	        // add requirement name
	        OWLDataProperty nameProperty = factory.getOWLDataProperty(DELIMITER + NAME, pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqName= factory.getOWLDataPropertyAssertionAxiom(nameProperty, ind, name);
	        manager.addAxiom(ontology, dataPropertyAxiomReqName);
	        
	    	// add requirement qualified name
	        OWLDataProperty qNameProperty = factory.getOWLDataProperty(DELIMITER + QNAME, pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqQName= factory.getOWLDataPropertyAssertionAxiom(qNameProperty, ind, qName);
	        manager.addAxiom(ontology, dataPropertyAxiomReqQName);	   
	        
		}
	}
	
	private void addScenarioIndividuals(){
		
		// get class
        OWLClass scenarioClass = factory.getOWLClass(DELIMITER + SCENARIO, pm);

		for (Element scenario : gmd.getAllFoundScenarios()) {
			
			// name and qualified name of the element
			String name = StringEscapeUtils.escapeXml(((NamedElement)scenario).getName());
			String qName = StringEscapeUtils.escapeXml(((NamedElement)scenario).getQualifiedName());

	        // create individual
//	        OWLNamedIndividual ind = factory.getOWLNamedIndividual(DELIMITER+StringUtls.replaceSpecChar(qName), pm);
			OWLNamedIndividual ind = factory.getOWLNamedIndividual(DELIMITER+StringUtls.replaceSpecChar(name), pm);
	        
	        // add to ontology
	        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(scenarioClass, ind);
	        // Add the class assertion
	        manager.addAxiom(ontology, classAssertion);
	        
	        // add requirement name
	        OWLDataProperty nameProperty = factory.getOWLDataProperty(DELIMITER + NAME, pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqName= factory.getOWLDataPropertyAssertionAxiom(nameProperty, ind, name);
	        manager.addAxiom(ontology, dataPropertyAxiomReqName);
	        
	    	// add requirement qualified name
	        OWLDataProperty qNameProperty = factory.getOWLDataProperty(DELIMITER + QNAME, pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqQName= factory.getOWLDataPropertyAssertionAxiom(qNameProperty, ind, qName);
	        manager.addAxiom(ontology, dataPropertyAxiomReqQName);	  
		}
			
	}
	
	
	private void addRequirementIndividuals(){
		
		// get class
        OWLClass reqClass = factory.getOWLClass(DELIMITER + REQUIREMENT, pm);
        
		for (Element requirement : gmd.getAllFoundRequirements()) {
			
			// name and qualified name of the element
			String name = StringEscapeUtils.escapeXml(((NamedElement)requirement).getName());
			String qName = StringEscapeUtils.escapeXml(((NamedElement)requirement).getQualifiedName());
			// requirement id and text
			String id = StringEscapeUtils.escapeXml(ModelicaMLServices.getRequirementID(requirement));
			String text = StringEscapeUtils.escapeXml(ModelicaMLServices.getRequirementText(requirement));

	        // create individual
//	        OWLNamedIndividual ind = factory.getOWLNamedIndividual(DELIMITER+StringUtls.replaceSpecChar(qName), pm);
			OWLNamedIndividual ind = factory.getOWLNamedIndividual(DELIMITER+StringUtls.replaceSpecChar(name), pm);
	        
	        // add to ontology
	        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(reqClass, ind);
	        // Add the class assertion
	        manager.addAxiom(ontology, classAssertion);
	        
	        // add requirement name
	        OWLDataProperty reqNameProperty = factory.getOWLDataProperty(DELIMITER + NAME, pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqName= factory.getOWLDataPropertyAssertionAxiom(reqNameProperty, ind, name);
	        manager.addAxiom(ontology, dataPropertyAxiomReqName);
	        
	    	// add requirement qualified name
	        OWLDataProperty reqQNameProperty = factory.getOWLDataProperty(DELIMITER + QNAME, pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqQName= factory.getOWLDataPropertyAssertionAxiom(reqQNameProperty, ind, qName);
	        manager.addAxiom(ontology, dataPropertyAxiomReqQName);
	        
	        // add id string
	        OWLDataProperty reqIdProperty = factory.getOWLDataProperty(DELIMITER + ID, pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqId = factory.getOWLDataPropertyAssertionAxiom(reqIdProperty, ind, id);
	        manager.addAxiom(ontology, dataPropertyAxiomReqId);
	        
	    	// add requirement text
	        OWLDataProperty reqTextProperty = factory.getOWLDataProperty(DELIMITER + REQUIREMENT_TEXT, pm);
	        OWLDataPropertyAssertionAxiom dataPropertyAxiomReqText = factory.getOWLDataPropertyAssertionAxiom(reqTextProperty, ind, text);
	        manager.addAxiom(ontology, dataPropertyAxiomReqText);
	        
		}
	}
	
	

	private void addEvaluations(int mode){
		
		OWLClass owlClass = null;
		String owlClassName = null; 
		HashSet<String> requirementQNames = null;
		
		if (mode==NOT_EVALUATED) {
			owlClass = factory.getOWLClass(DELIMITER + NOT_EVALUATED_REQUIREMENT, pm);
			owlClassName = NOT_EVALUATED_REQUIREMENT;
			requirementQNames = gmd.getNotEvaluatedRequirements();
		}
		else if (mode==NOT_VIOLATED) {
			owlClass = factory.getOWLClass(DELIMITER + NOT_VIOLATED_REQUIREMENT, pm);
			owlClassName = NOT_VIOLATED_REQUIREMENT;
			requirementQNames = gmd.getNotViolatedRequirements();
		}
		else if (mode==VIOLATED) {
			owlClass = factory.getOWLClass(DELIMITER + VIOLATED_REQUIREMENT, pm);
			owlClassName = VIOLATED_REQUIREMENT;
			requirementQNames = gmd.getViolatedRequirements();
		}

		HashSet<Element> designAlternatives = gmd.getSystemModels();
		
		if (requirementQNames != null && designAlternatives != null && owlClass!= null && owlClassName!=null) {
			for (Element vem : gmd.getGeneratedModels()) {
				HashSet<TreeObject> requirements = gmd.getRequirements(vem);
				HashSet<TreeObject> scenarios = gmd.getScenarios(vem);
				
				for (TreeObject requirementTreeObject : requirements) {
					Element requirementProperty = requirementTreeObject.getUmlElement();
					if (requirementProperty instanceof Property) {
						Element requirement = ((Property)requirementProperty).getType();
						if (requirement instanceof NamedElement) {
							
							if ( requirementQNames.contains(ModelicaMLServices.getQualifiedName(vem) + Constants.linkDelimiter + requirementTreeObject.getDotPath() + "." + Constants.propertyName_mStatus) ) {
								// create evaluation individual
								
								OWLNamedIndividual ind = factory.getOWLNamedIndividual(DELIMITER+StringUtls.replaceSpecChar(((Property) requirementProperty).getName()) 
										+ "__IN__" 
										+ StringUtls.replaceSpecChar( ((NamedElement)vem).getName()), pm);
//										+ StringUtls.replaceSpecChar( ((NamedElement)vem).getQualifiedName()), pm);
						        
						        // add to ontology
						        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(owlClass, ind);
						        // Add the class assertion
						        manager.addAxiom(ontology, classAssertion);
						        
						        // set the considered requirement object property
						        OWLObjectProperty consideredRequirementProperty = factory.getOWLObjectProperty(DELIMITER + CONSIDERED_REQUIREMENT, pm);
						        OWLNamedIndividual reqInd = factory.getOWLNamedIndividual(DELIMITER+StringUtls.replaceSpecChar(((NamedElement) requirement).getName()), pm);
						        OWLObjectPropertyAssertionAxiom consideredRequirementPropertyAxiom = factory.getOWLObjectPropertyAssertionAxiom(consideredRequirementProperty, ind, reqInd);
						        manager.addAxiom(ontology, consideredRequirementPropertyAxiom);				        

						        // set the considered scenario object property
						        for (TreeObject scenarioTreeObject : scenarios) {
						        	Element scenarioProperty = scenarioTreeObject.getUmlElement();
						        	if (scenarioProperty instanceof Property) {
										Element scenario = ((Property)scenarioProperty).getType();
										if (scenario != null) {
									        OWLObjectProperty consideredScenarioProperty = factory.getOWLObjectProperty(DELIMITER + CONSIDERED_SCENARIO, pm);
									        OWLNamedIndividual scenarioInd = factory.getOWLNamedIndividual(DELIMITER+StringUtls.replaceSpecChar(((NamedElement) scenario).getName()), pm);
									        OWLObjectPropertyAssertionAxiom consideredScenarioPropertyAxiom = factory.getOWLObjectPropertyAssertionAxiom(consideredScenarioProperty, ind, scenarioInd);
									        manager.addAxiom(ontology, consideredScenarioPropertyAxiom);				        
										}
									}
								}
						        
						        // set the considered design alternative object property
						        for (Element designAlternative: designAlternatives) {
							        OWLObjectProperty consideredDesignAlternativeProperty = factory.getOWLObjectProperty(DELIMITER + CONSIDERED_DESIGN, pm);
							        OWLNamedIndividual designInd = factory.getOWLNamedIndividual(DELIMITER+StringUtls.replaceSpecChar(((NamedElement) designAlternative).getName()), pm);
							        OWLObjectPropertyAssertionAxiom consideredScenarioPropertyAxiom = factory.getOWLObjectPropertyAssertionAxiom(consideredDesignAlternativeProperty, ind, designInd);
							        manager.addAxiom(ontology, consideredScenarioPropertyAxiom);				        
								}
						        
						    	// set the considered requirement object property
						        OWLObjectProperty usedModelProperty = factory.getOWLObjectProperty(DELIMITER + USED_MODEL, pm);
						        OWLNamedIndividual vemInd = factory.getOWLNamedIndividual(DELIMITER+StringUtls.replaceSpecChar(((NamedElement) vem).getName()), pm);
						        OWLObjectPropertyAssertionAxiom usedModelPropertyAxiom = factory.getOWLObjectPropertyAssertionAxiom(usedModelProperty, ind, vemInd);
						        manager.addAxiom(ontology, usedModelPropertyAxiom);		
							}
						}
					}
				}
			}
		}
	}
	
	
	
	
	
	/*
	 * REASONING
	 */

	// TODO: does not work. No object property axoims (e.g. inverseOf) are computed ...
	public void createInferredAxioms(String originalOntologyPath) throws OWLOntologyCreationException, OWLOntologyStorageException, IOException {
		
		// First, we create an OWLOntologyManager object. The manager will load and 
    	// save ontologies. 
        OWLOntologyManager manager=OWLManager.createOWLOntologyManager();
        // Now, we create the file from which the ontology will be loaded. 
    	// Here the ontology is stored in a file locally in the ontologies subfolder
    	// of the examples folder.
        File inputOntologyFile = new File(originalOntologyPath);
        // We use the OWL API to load the ontology. 
        OWLOntology ontology=manager.loadOntologyFromOntologyDocument(inputOntologyFile);
        // Now we can start and create the reasoner. Since materialisation of axioms is controlled 
        // by OWL API classes and is not natively supported by HermiT, we need to instantiate HermiT 
        // as an OWLReasoner. This is done via a ReasonerFactory object. 
        ReasonerFactory factory = new ReasonerFactory();
        // The factory can now be used to obtain an instance of HermiT as an OWLReasoner. 
        Configuration c=new Configuration();
        c.reasonerProgressMonitor=new ConsoleProgressMonitor();
        OWLReasoner reasoner=factory.createReasoner(ontology, c);
        // The following call causes HermiT to compute the class, object, 
        // and data property hierarchies as well as the class instances. 
        // Hermit does not yet support precomputation of property instances. 
        //reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY, InferenceType.CLASS_ASSERTIONS, InferenceType.OBJECT_PROPERTY_HIERARCHY, InferenceType.DATA_PROPERTY_HIERARCHY, InferenceType.OBJECT_PROPERTY_ASSERTIONS);
        // We now have to decide which kinds of inferences we want to compute. For different types 
        // there are different InferredAxiomGenerator implementations available in the OWL API and 
        // we use the InferredSubClassAxiomGenerator and the InferredClassAssertionAxiomGenerator 
        // here. The different generators are added to a list that is then passed to an 
        // InferredOntologyGenerator. 
        List<InferredAxiomGenerator<? extends OWLAxiom>> generators=new ArrayList<InferredAxiomGenerator<? extends OWLAxiom>>();
        generators.add(new InferredSubClassAxiomGenerator());
        generators.add(new InferredClassAssertionAxiomGenerator());
        // We dynamically overwrite the default disjoint classes generator since it tries to 
        // encode the reasoning problem itself instead of using the appropriate methods in the 
        // reasoner. That bypasses all our optimisations and means there is not progress report :-( 
        // We don't want that!
        generators.add(new InferredDisjointClassesAxiomGenerator() {
            boolean precomputed=false;
            protected void addAxioms(OWLClass entity, OWLReasoner reasoner, OWLDataFactory dataFactory, Set<OWLDisjointClassesAxiom> result) {
                if (!precomputed) {
//                    reasoner.precomputeInferences(InferenceType.DISJOINT_CLASSES);
                	reasoner.precomputeInferences(InferenceType.OBJECT_PROPERTY_ASSERTIONS, InferenceType.DATA_PROPERTY_ASSERTIONS);
                    precomputed=true;
                }
                for (OWLClass cls : reasoner.getDisjointClasses(entity).getFlattened()) {
                    result.add(dataFactory.getOWLDisjointClassesAxiom(entity, cls));
                }
            }
        });
        // We can now create an instance of InferredOntologyGenerator. 
        InferredOntologyGenerator iog=new InferredOntologyGenerator(reasoner,generators);
        // Before we actually generate the axioms into an ontology, we first have to create that ontology. 
        // The manager creates the for now empty ontology for the inferred axioms for us. 
        OWLOntology inferredAxiomsOntology=manager.createOntology();
        // Now we use the inferred ontology generator to fill the ontology. That might take some 
        // time since it involves possibly a lot of calls to the reasoner.    
        iog.fillOntology(manager, inferredAxiomsOntology);
        // Now the axioms are computed and added to the ontology, but we still have to save 
        // the ontology into a file. Since we cannot write to relative files, we have to resolve the 
        // relative path to an absolute one in an OS independent form. We do this by (virtually) creating a 
        // file with a relative path from which we get the absolute file.  
        
        Path originalOntology = new Path(originalOntologyPath);
		IPath newOntologyPath = new Path(originalOntology.removeLastSegments(1) + "/" + fileName + "_inferred" + fileExtension);
		
//        File inferredOntologyFile=new File("examples/ontologies/pizza-inferred.owl");
		File inferredOntologyFile=new File(newOntologyPath.toOSString());
		
        if (!inferredOntologyFile.exists())
            inferredOntologyFile.createNewFile();
        inferredOntologyFile=inferredOntologyFile.getAbsoluteFile();
        // Now we create a stream since the ontology manager can then write to that stream. 
        OutputStream outputStream=new FileOutputStream(inferredOntologyFile);
        // We use the same format as for the input ontology.
        manager.saveOntology(inferredAxiomsOntology, manager.getOntologyFormat(ontology), outputStream);
        // Now that ontology that contains the inferred axioms should be in the ontologies subfolder 
        // (you Java IDE, e.g., Eclipse, might have to refresh its view of files in the file system) 
        // before the file is visible.  
        System.out.println("The ontology in '"+newOntologyPath.toOSString()+"' \nshould now contain all inferred axioms (you might need to refresh the IDE file view). ");
    }
}
