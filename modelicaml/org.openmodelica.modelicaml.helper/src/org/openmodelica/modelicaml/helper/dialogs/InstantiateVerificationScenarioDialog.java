package org.openmodelica.modelicaml.helper.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;


public class InstantiateVerificationScenarioDialog extends Dialog {

	// selected requirements
	private HashSet<Element> reqList = new HashSet<Element>();
	
	// test scenarios to be instantiated that are selected by the user
	private HashSet<Element> tsToBeInstantiated = new HashSet<Element>();

	// all test scenarios that are found in the top-level model
	private HashSet<Element> allTS = new HashSet<Element>();

	// test scenarios that can be used to test selected requirements
	private HashSet<Element> matchedTS = new HashSet<Element>();
//	private HashMap<Element,HashSet<Element>> matchedTSToReq = new HashMap<Element,HashSet<Element>>();
	
	// test scenarios that have references to requirements
	private HashSet<Element> linkedTS = new HashSet<Element>();
	
	// test scenarios mapped to requirements
	private HashMap<Element,HashSet<Element>> tsToReq = new HashMap<Element,HashSet<Element>>(); 

	// test scenarios mapped to requirements
	private HashMap<Element,HashSet<Element>> reqToTS = new HashMap<Element,HashSet<Element>>(); 
	
	// not covered requirements
	private HashSet<Element> notCoveredRequirements = new HashSet<Element>();
	
	// class where test scenarios should be instantiated
	private Class containingClass;
	
	private static final String tabTitle_AllTS = "All Scenarios";
	private static final String tabTitle_MatchedTS = "Matched Scenarios";
	private static final String tabTitle_LinkedTS = "Linked Scenarios";
	
	private static final String  tabTitle_CoveredReq = "Referenced Requirements";
	private static final String  tabTitle_NotCoveredReq = "Not Referenced Requirements";
	
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param object 
	 * @param selectedElement 
	 */
	public InstantiateVerificationScenarioDialog(Shell parentShell, 
			Class containingClass, 
			HashSet<Element> allTS, 
			HashSet<Element> matchedTS,
			HashSet<Element> linkedTS,
			HashMap<Element, HashSet<Element>> tsToReq, 
			HashMap<Element,HashSet<Element>> reqToTS,
			HashSet<Element> notCoveredRequirements, 
			HashSet<Element> selectedReqList
			
			) 
	{
		super(parentShell);
		setShellStyle(SWT.DIALOG_TRIM | SWT.RESIZE);
		
		if (allTS != null && allTS.size() > 0) {
			this.allTS.addAll(allTS);
		}
		if (matchedTS != null && matchedTS.size() > 0) {
			this.matchedTS.addAll(matchedTS);
		}
		
		if (linkedTS != null && linkedTS.size() > 0) {
			this.linkedTS.addAll(linkedTS);
		}
		
		if (tsToReq != null ) {
			this.tsToReq.putAll(tsToReq);
		}
		
		if (reqToTS != null ) {
			this.reqToTS.putAll(reqToTS);
		}
		
		
		if (selectedReqList != null) {
			reqList.addAll(selectedReqList);
		}
		
		if (notCoveredRequirements != null && notCoveredRequirements.size() > 0) {
			this.notCoveredRequirements.addAll(notCoveredRequirements);
		}
		
		this.containingClass = containingClass;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tabFolder.heightHint = 325;
		gd_tabFolder.widthHint = 672;
		tabFolder.setLayoutData(gd_tabFolder);
		
		// Create "Matched test scenarios" tab 
		if (matchedTS.size() > 0 ) {
			String title = tabTitle_MatchedTS + " (" + matchedTS.size() + ")";
			String message = "The following scenarios can be used to test all selected requirements:";
			ExpandBar expandBar = createTab(tabFolder, title, message, tabTitle_MatchedTS);
			
			// sort based on qName
			List<Element> list = new ArrayList<Element>();
			list.addAll(matchedTS);
			Collections.sort(list, new Comparator<Element>() {
				
				@Override
				public int compare(Element o1, Element o2) {
					if (o1 instanceof NamedElement && o2 instanceof NamedElement) {
						String qName1 = ((NamedElement)o1).getQualifiedName();
						String qName2 = ((NamedElement)o2).getQualifiedName();
						return qName1.compareTo(qName2);
					}
					return 0;
				}
			});
			
			for (Element testScenario : list) {
				if (testScenario instanceof Class) {
					createExpandItem(expandBar, (Class) testScenario);
				}
			}
		}
		
		// Create "Matched test scenarios" tab 
		if (linkedTS.size() > 0 ) {
			String title = tabTitle_LinkedTS + " (" + linkedTS.size() + ")";
			String message = "The following scenarios have references to requirements:";
			ExpandBar expandBar = createTab(tabFolder, title, message, tabTitle_LinkedTS);
			
			// sort based on qName
			List<Element> list = new ArrayList<Element>();
			list.addAll(linkedTS);
			Collections.sort(list, new Comparator<Element>() {
				
				@Override
				public int compare(Element o1, Element o2) {
					if (o1 instanceof NamedElement && o2 instanceof NamedElement) {
						String qName1 = ((NamedElement)o1).getQualifiedName();
						String qName2 = ((NamedElement)o2).getQualifiedName();
						return qName1.compareTo(qName2);
					}
					return 0;
				}
			});
			for (Element testScenario : list) {
				if (testScenario instanceof Class) {
					createExpandItem(expandBar, (Class) testScenario);
				}
			}
		}
		
		// Create "all test scenarios" tab 
		if (allTS.size() > 0 ) {
			String title = tabTitle_AllTS + " (" + allTS.size() + ")";
			String message = "The following scenarios were found in the model:";
			ExpandBar expandBar = createTab(tabFolder, title, message, tabTitle_AllTS);
			
			// sort based on qName
			List<Element> list = new ArrayList<Element>();
			list.addAll(allTS);
			Collections.sort(list, new Comparator<Element>() {
				
				@Override
				public int compare(Element o1, Element o2) {
					if (o1 instanceof NamedElement && o2 instanceof NamedElement) {
						String qName1 = ((NamedElement)o1).getQualifiedName();
						String qName2 = ((NamedElement)o2).getQualifiedName();
						return qName1.compareTo(qName2);
					}
					return 0;
				}
			});
			
			for (Element testScenario : list) {
				if (testScenario instanceof Class) {
					createExpandItem(expandBar, (Class) testScenario);
				}
			}
		}

		// Create "covered requirements" tab 
		if (reqToTS.size() > 0 ) {
			String title = tabTitle_CoveredReq + " (" + reqToTS.size() + ")";
			String message = "The following requirements are referenced by scenarios:";
			ExpandBar expandBar = createTab(tabFolder, title, message, tabTitle_CoveredReq);
			
			// sort based on qName
			List<Element> list = new ArrayList<Element>();
			list.addAll(reqToTS.keySet());
			Collections.sort(list, new Comparator<Element>() {
				
				@Override
				public int compare(Element o1, Element o2) {
					if (o1 instanceof NamedElement && o2 instanceof NamedElement) {
//						String qName1 = ((NamedElement)o1).getQualifiedName();
//						String qName2 = ((NamedElement)o2).getQualifiedName();
//						return qName1.compareTo(qName2);
						String id1 = getRequirementId(o1);
						String id2 = getRequirementId(o2);
						return id1.compareTo(id2);
					}
					return 0;
				}
			});
			
			for (Element req : list) {
				createReqExpandItem(expandBar, (Class) req);
			}
		}

		// Create "covered requirements" tab 
		if (notCoveredRequirements.size() > 0 ) {
			String title = tabTitle_NotCoveredReq + " (" + notCoveredRequirements.size() + ")";
			String message = "The following requirements are not referenced by scenarios found in this model:";
			ExpandBar expandBar = createTab(tabFolder, title, message, tabTitle_NotCoveredReq);
			
			// sort based on qName
			List<Element> list = new ArrayList<Element>();
			list.addAll(notCoveredRequirements);
			Collections.sort(list, new Comparator<Element>() {
				
				@Override
				public int compare(Element o1, Element o2) {
					if (o1 instanceof NamedElement && o2 instanceof NamedElement) {
//						String qName1 = ((NamedElement)o1).getQualifiedName();
//						String qName2 = ((NamedElement)o2).getQualifiedName();
//						return qName1.compareTo(qName2);
						String id1 = getRequirementId(o1);
						String id2 = getRequirementId(o2);
						return id1.compareTo(id2);
					}
					return 0;
				}
			});
			
			for (Element req :  list ) {
				createReqExpandItem(expandBar, (Class) req);
			}
		}
		
		// ONLY for layouting, comment it out when not used.
//		ExpandBar expandBar = createTab(tabFolder, "Title", "Message");

		return container;
	}
	
	
	private ExpandBar createTab(TabFolder tabFolder, String title, String message, String tabTitleOnly){
		TabItem tab = new TabItem(tabFolder, SWT.NONE);
		tab.setText(title);
		
		if (tabTitleOnly.equals(tabTitle_CoveredReq)) {
			tab.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
		}
		else if (tabTitleOnly.equals(tabTitle_NotCoveredReq)) {
			tab.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/QuestionMark.png"));
		}
		else {
			tab.setImage(SWTResourceManager.getImage(InstantiateRequirementsDialog.class, "/icons/listOfActions.png"));
		}
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tab.setControl(composite);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblMessage = new Label(composite, SWT.NONE);
		lblMessage.setText(message);
		
		ExpandBar expandBar = new ExpandBar(composite, SWT.V_SCROLL);
		expandBar.setSpacing(5);
		expandBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		expandBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		
		// ONLY for layouting, comment it out when not used.
//		ExpandItem expandItemTEST = createExpandItem(expandBar, null);
//		ExpandItem expandItemTEST = createReqExpandItem(expandBar, null);

		return expandBar;
	}
	
	private ExpandItem createExpandItem(ExpandBar expandBar, final Class testScenario ){
			
		// get number of referenced requirements
		String numberOfReferencedRequirements = "";
		HashSet<Element> numberOfRequirements = tsToReq.get(testScenario);
		if (numberOfRequirements != null) {
			numberOfReferencedRequirements = " (for " + numberOfRequirements.size()+ " requirements)"; 
		}
		
		final ExpandItem expandItem = new ExpandItem(expandBar, SWT.NONE);
		expandItem.setExpanded(false);
		expandItem.setImage(SWTResourceManager.getImage(InstantiateRequirementsDialog.class, "/icons/listOfActions.png"));
		expandItem.setText(testScenario.getName() + numberOfReferencedRequirements);
		
		Composite expandItemComposite = new Composite(expandBar, SWT.BORDER);
		expandItemComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		expandItem.setControl(expandItemComposite);
		expandItemComposite.setLayout(new GridLayout(2, false));
		
		final Button btInstantiate = new Button(expandItemComposite, SWT.CHECK);
		btInstantiate.setData(testScenario);
		btInstantiate.setToolTipText("Add to or remove from the selection of test scenarios that shall be instantiated.");
		btInstantiate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btInstantiate.getSelection() == true) {
					expandItem.setImage(SWTResourceManager.getImage(InstantiateRequirementsDialog.class, "/icons/button_ok.png"));
					tsToBeInstantiated.add((Element) btInstantiate.getData());
				}
				else {
					expandItem.setImage(SWTResourceManager.getImage(InstantiateRequirementsDialog.class, "/icons/listOfActions.png"));
					tsToBeInstantiated.remove((Element) btInstantiate.getData());
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// set the default selection if this btInstantiate.getData() is contained in the selectedItems...
				if (tsToBeInstantiated.contains(btInstantiate.getData())) {
					btInstantiate.setSelection(true);
				}
			}
		});
		
		
		Label lblQName = new Label(expandItemComposite, SWT.NONE);
		lblQName.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		lblQName.setText(testScenario.getQualifiedName());
		new Label(expandItemComposite, SWT.NONE);
		
		Label lblDescription = new Label(expandItemComposite, SWT.NONE);
		lblDescription.setText("Description:");
		new Label(expandItemComposite, SWT.NONE);
		
		StyledText textDescription = new StyledText(expandItemComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		textDescription.setText(getAllComments(testScenario));
		GridData gd_textDescription = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_textDescription.heightHint = 99;
		textDescription.setLayoutData(gd_textDescription);
		new Label(expandItemComposite, SWT.NONE);
		
		Label lblLinkedRequirements = new Label(expandItemComposite, SWT.NONE);
		lblLinkedRequirements.setText("Linked requirements:");
		new Label(expandItemComposite, SWT.NONE);
		
		StyledText textLinkedRequirements = new StyledText(expandItemComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		textLinkedRequirements.setText(getRequirementsString(testScenario));
		GridData gd_textLinkedRequirements = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_textLinkedRequirements.heightHint = 87;
		textLinkedRequirements.setLayoutData(gd_textLinkedRequirements);
		
		expandItem.setHeight(300);
		return expandItem;
	}
	
	private ExpandItem createReqExpandItem(ExpandBar expandBar, final Class requirement){
		
		// get number of referencing test scenarios
		String numberOfTestCasesString = "";
		HashSet<Element> numberOfTestCases = reqToTS.get(requirement);
		if (numberOfTestCases != null) {
			numberOfTestCasesString = " (in " + numberOfTestCases.size()+ " test scenarios)"; 
		}
		
		final ExpandItem expandItem = new ExpandItem(expandBar, SWT.NONE);
		expandItem.setExpanded(false);
		expandItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
		expandItem.setText(getRequirementId(requirement) + requirement.getName() + numberOfTestCasesString);

		Composite expandItemComposite = new Composite(expandBar, SWT.BORDER);
		expandItemComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		expandItem.setControl(expandItemComposite);
		expandItemComposite.setLayout(new GridLayout(1, false));
		
		Label lblQName = new Label(expandItemComposite, SWT.V_SCROLL);
		lblQName.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		lblQName.setText(requirement.getQualifiedName());
		
		Label lblText = new Label(expandItemComposite, SWT.NONE);
		lblText.setText("Text:");
		
		StyledText textDescription = new StyledText(expandItemComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		textDescription.setText(getRequirementSpecificData(requirement));
		GridData gd_textDescription = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_textDescription.heightHint = 99;
		textDescription.setLayoutData(gd_textDescription);
		
		
		if (reqToTS.keySet().contains(requirement)) {
			Label lblReferencingTestScenarios = new Label(expandItemComposite, SWT.NONE);
			lblReferencingTestScenarios.setText("Referenced by test scenarios:");
			
			// Link does not scroll :(
//			Link textReferencingTestScenarios = new Link(expandItemComposite, SWT.WRAP | SWT.V_SCROLL);
//			textReferencingTestScenarios.setText(getTestSenariosString(requirement));
//			GridData gd_textLinkedRequirements = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
//			gd_textLinkedRequirements.heightHint = 87;
//			textReferencingTestScenarios.setLayoutData(gd_textLinkedRequirements);
//			
//			textReferencingTestScenarios.addListener(SWT.Selection, new Listener() {
//			      public void handleEvent(Event event) {
//			    	  String qName = event.text.trim();
//			    	  for (Element element : allTS) {
//						if (element instanceof NamedElement && ((NamedElement)element).getQualifiedName().equals(qName)) {
//							PapyrusServices.locate(element); // locate in Papyrus Model Explorer
//						}
//					}
//			      }
//			    });
			
			StyledText textReferencingTestScenarios = new StyledText(expandItemComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
			textReferencingTestScenarios.setText(getTestSenariosString(requirement));
			GridData gd_textLinkedRequirements = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
			gd_textLinkedRequirements.heightHint = 87;
			textReferencingTestScenarios.setLayoutData(gd_textLinkedRequirements);
		}
		
		expandItem.setHeight(300);
		return expandItem;
}
	
	
    // Utilities ############################################################################
	private String getAllComments(Element element){
		String comments = "";
		for (Comment comment : element.getOwnedComments()) {
			comments = comments + "\r\n" + comment.getBody();
		}
		return comments;
	}
	
	private String getRequirementId(Element requirement){
		Stereotype s = requirement.getAppliedStereotype(Constants.stereotypeQName_Requirement);
		if (s != null) {
			String id = (String) requirement.getValue(s, Constants.propertyName_id); 
			return id + " ";
		}
		return "";
	}
	
	private String getRequirementSpecificData(Class requirement){
		String string = "";
		Stereotype s = requirement.getAppliedStereotype(Constants.stereotypeQName_Requirement);
		if (s != null) {

			String id = (String) requirement.getValue(s, Constants.propertyName_id); 
			String text  = (String) requirement.getValue(s, Constants.propertyName_text);
			return id + " - " + text;
		}
		return string;
	}
	
	private String getRequirementsString(Class testScenario){
		String string = "";
		HashSet<Element> requirements = tsToReq.get(testScenario);
		
		if (requirements != null) {
			List<String> list = new ArrayList<String>();
			
			for (Element element : requirements) {
				Stereotype s = element.getAppliedStereotype(Constants.stereotypeQName_Requirement);
				if (element instanceof NamedElement && s != null) {

					String id = (String) element.getValue(s, Constants.propertyName_id); 
					String title = ((NamedElement) element).getName();
					String qName = ((NamedElement) element).getQualifiedName();
					
					// add to list
					list.add(" - " + id + " - " + " "+title+" (" + qName + ")" + "\r\n");
				}
			}
			// sort
			Collections.sort(list);
			// build a string
			for (String string2 : list) {
				string = string + string2;
			}

		}
		return string;
	}
	
	private String getTestSenariosString(Class req){
		String string = "";
		HashSet<Element> testScenarios = reqToTS.get(req);
		
		if (testScenarios != null) {
			List<String> list = new ArrayList<String>();
			
			for (Element element : testScenarios) {
				if (element instanceof NamedElement) {
					String name = ((NamedElement) element).getName();
					String qName = ((NamedElement) element).getQualifiedName();
					
					// add to list
//					list.add(" - <a href=\""+qName+"\">" + name + "</a> (" + qName + ")" + "\r\n");
					list.add(" - " + name + " (" + qName + ")" + "\r\n");
				}
			}
			// sort
			Collections.sort(list);
			// build a string
			for (String string2 : list) {
				string = string + string2;
			}
		}
		return string;
	}
	
    // Shell ############################################################################
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

//	/**
//	 * Return the initial size of the dialog.
//	 */
//	@Override
//	protected Point getInitialSize() {
//		return new Point(637, 513);
//	}
	
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
      	shell.setSize(850, 600);
      	shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
      	shell.setText("ModelicaML Helper: Instantiate Scenarios");
       	shell.setImage(SWTResourceManager.getImage(InstantiateRequirementsDialog.class, "/icons/listOfActions.png"));
    }


    // GETTER #########################################
	public HashSet<Element> getTsToBeInstantiated() {
		return tsToBeInstantiated;
	}
}
