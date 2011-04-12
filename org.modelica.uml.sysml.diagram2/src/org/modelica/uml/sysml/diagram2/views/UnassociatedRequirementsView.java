package org.modelica.uml.sysml.diagram2.views;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.SelectAllAction;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResourceFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.uml2.uml.Model;
import org.modelica.uml.sysml.GeneralRelationship;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.Requirement;
import org.modelica.uml.sysml.SatisfiedBy;
import org.modelica.uml.sysml.SatisfiedByProperty;
import org.modelica.uml.sysml.Satisfies;
import org.modelica.uml.sysml.SatisfiesProperty;
import org.modelica.uml.sysml.SatisfyRelationship;
import org.modelica.uml.sysml.diagram2.part.SysmlDiagramEditor;
import org.modelica.uml.sysml.diagram2.part.SysmlDiagramEditorUtil;
import org.modelica.uml.sysml.diagram2.part.SysmlElementChooserDialog;
import org.modelica.uml.sysml.diagram2.views.RequirementQuerryView.SearchListener;

public class UnassociatedRequirementsView extends ViewPart{
	ScrolledComposite sc;
	SysmlElementChooserDialog dialog;

	private Button fileCheck;
	private IFile file;
	private Text fileLocation;
	private Button projectCheck;
	private IProject project;
	private Text projectName;
	private Button workspace;
	private Button negated;

	private Button classCheck;
	private Button requirementCheck;

	static private Table resultTable;

	@Override
	public void createPartControl(Composite parent) {
		View view = null;
		dialog = new SysmlElementChooserDialog(parent.getShell(), view);
		dialog.setMyFilter(new FilesFilter());
		dialog.setAsModelObject(false);
		
		sc = new ScrolledComposite(parent, SWT.H_SCROLL |   
				SWT.V_SCROLL | SWT.BORDER);

		Composite composite = new Composite(sc, SWT.NONE);
//		composite.setLayout(new FillLayout());
		composite.setSize(400,400);
		sc.setContent(composite);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setMinSize(composite.computeSize(1000, 1000));

		createLocationGroup(composite, 0, 0, 200, 120);
		createTypeGroup(composite, 0, 125, 200, 35);

		negated = new Button(composite, SWT.CHECK);
		negated.setBounds(0, 161, 20, 20);

		Label negatedLabel = new Label(composite, SWT.NONE);
		negatedLabel.setBounds(20, 161, 50, 20);
		negatedLabel.setText("Negated");

		Button search = new Button(composite,SWT.PUSH);
		search.setBounds(80, 161, 50, 20);
		search.setText("Search");

		search.addListener(SWT.Selection, new SearchListener());

		int rtW = 400, rtH = 200;
		resultTable = new Table(composite, SWT.MULTI | SWT.BORDER);

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		resultTable.setLayoutData(data);
		String[] titles = {"Name", "Project", "File", "Type"};
		for (int c=0; c<titles.length; c++) {
			TableColumn column = new TableColumn (resultTable, SWT.NONE);
			column.setText (titles [c]);
		}		

		resultTable.setBounds(0 + 200 + 10, 0, rtW, rtH);
		resultTable.setLinesVisible(true);
		resultTable.setHeaderVisible(true);
		for (int j=0; j<titles.length; j++) {
			resultTable.getColumn (j).pack ();
		}
		
		resultTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDoubleClick(e);
				TableItem[] selections = resultTable.getSelection();
				String projectName = selections[0].getText(1);
				String fileName = selections[0].getText(2);
				fileName = fileName.replaceAll("sysml", "mci");
				String objectName =  selections[0].getText(0);

				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				IFile file = project.getFile(fileName);
				IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
				if (file instanceof IFile) {
					try {
						page.openEditor(new FileEditorInput(
								(IFile) file), SysmlDiagramEditor.ID);	
					} catch (Exception ex) {
						// TODO: handle exception
						int i = 5;
					}
				}
			
				IEditorReference[] editors = page.getEditorReferences();
				for(int i = 0;i<editors.length;i++) {
					try {
						if (editors[i].getEditorInput() instanceof FileEditorInput) {
							FileEditorInput new_name = (FileEditorInput) editors[i].getEditorInput();
							String location1 =new_name.getFile().getLocationURI().getPath();
							String location2 = file.getLocationURI().getPath();
							if(location1.equals(location2)) {
								GraphicalViewer viewer = (GraphicalViewer)editors[i].getEditor(false).getAdapter(GraphicalViewer.class);
								if (viewer != null) {
									List children = viewer.getContents().getChildren();
									Iterator iter = children.iterator();
									List selection = new ArrayList();
									while (iter.hasNext()) {
										EditPart elem = (EditPart) iter.next();
										if (elem != null) {
											if ((View)elem.getModel() != null) {
												if (((View)elem.getModel()).getElement() instanceof ModelicaClass) {
													ModelicaClass classElem = (ModelicaClass) ((View)elem.getModel()).getElement();
													if(classElem.getName().equals(objectName))
														selection.add(elem);
												}
												if (((View)elem.getModel()).getElement() instanceof Requirement) {
													Requirement reqElem = (Requirement) ((View)elem.getModel()).getElement();
													if(reqElem.getName().equals(objectName))
														selection.add(elem);
												}
											}
										}
									}
									viewer.setSelection(new StructuredSelection(selection));
								}
							}		
						}
					} catch (Exception x) {
						// TODO: handle exception
					}
				}
			}
		});
	}

	@Override
	public void setFocus() {
		sc.setFocus();
	}

	public void createTypeGroup(final Composite parent, int x, int y, int w, int h) {
		Group typeGroup = new Group(parent, SWT.SHADOW_ETCHED_IN);
		typeGroup.setText("Type");

		typeGroup.setBounds(x, y, w, h);
		int buttonSize = 20;

		classCheck = new Button(typeGroup, SWT.RADIO);
		classCheck.setSelection(true);
		classCheck.setBounds(0, 15, buttonSize, buttonSize);
		Label classLabel = new Label(typeGroup, SWT.NONE);
		int labelW = 40;
		classLabel.setBounds(buttonSize, 15, buttonSize + labelW - 1, buttonSize - 1);
		classLabel.setText("Class");
		requirementCheck = new Button(typeGroup, SWT.RADIO);
		requirementCheck.setBounds(2*buttonSize + labelW, 15, buttonSize, buttonSize);
		Label reqLabel = new Label(typeGroup, SWT.NONE);
		reqLabel.setBounds(3*buttonSize + labelW, 15, w - 3*buttonSize - labelW - 5, buttonSize -1);
		reqLabel.setText("Requirement");
	}

	public void createLocationGroup(final Composite composite,int x,int y,int w,int h) {
		Group locationGroup = new Group(composite, SWT.SHADOW_ETCHED_IN);

		locationGroup.setBounds(x,y,w,h);
		locationGroup.setText("Location");

		int buttonSize = 20;

		fileCheck = new Button(locationGroup,SWT.RADIO);
		int fX = 0, fY = 15, fW = 20, fH = 20;
		fileCheck.setBounds(fX, fY, fW, fH);
		fileCheck.setSelection(true);

		Label fileLabel = new Label(locationGroup, SWT.NONE);
		fileLabel.setBounds(fX + 20, fY , 50, 17);
		fileLabel.setText("File");

		fileLocation = new Text(locationGroup, SWT.BORDER|SWT.READ_ONLY);
		int flX = 0, flY = fY + fH, flW = w - buttonSize, flH = 20;
		fileLocation.setBounds(flX, flY, flW, flH);

		final Button fileButton = new Button(locationGroup, SWT.PUSH);
		fileButton.setBounds(flW, flY, buttonSize, buttonSize);
		fileButton.setText("...");

		fileButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				dialog.setMyFilter(new FilesFilter());
				dialog.setAsModelObject(false);
				dialog.open();
				Object obj = dialog.getMySelectedElement();
				if(obj instanceof IFile) {
					file = (IFile)obj;
					String str =file.getParent().getName()+ "\\" + file.getName();
					fileLocation.setText(str);
				}

			}
		});

		projectCheck = new Button(locationGroup, SWT.RADIO);
		int pX = 0, pY = flY + flH, pW = 20, pH = 20;
		projectCheck.setBounds(pX, pY, pW, pH);

		Label projectLabel = new Label(locationGroup, SWT.NONE);
		projectLabel.setBounds(pX + 20, pY , 50, 17);
		projectLabel.setText("Project");

		projectName = new Text(locationGroup, SWT.BORDER|SWT.READ_ONLY);
		int pnX = 0, pnY = pY + pH, pnW = w - buttonSize, pnH = 20;
		projectName.setBounds(pnX, pnY, pnW, pnH);

		final Button projectButton = new Button(locationGroup, SWT.PUSH);
		projectButton.setBounds(pnW, pnY, buttonSize, buttonSize);
		projectButton.setText("...");
		projectButton.setEnabled(false);

		projectButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				dialog.setMyFilter(new ProjectFilter());
				dialog.setAsModelObject(false);
				dialog.open();
				Object obj = dialog.getMySelectedElement();
				if(obj instanceof Project) {
					project = (Project)obj;
					projectName.setText(project.getName());
				}
			}
		});

		workspace = new Button(locationGroup, SWT.RADIO);
		int wX = 0, wY = pnY + pnH, wW = 20, wH = 20;
		workspace.setBounds(wX, wY, wW, wH);

		Label wsLabel = new Label(locationGroup, SWT.NONE);
		int wslX = wW, wslY = wY, wslH = 20, wslW = 60;
		wsLabel.setBounds(wslX, wslY, wslW, wslH);
		wsLabel.setText("Workspace");

		fileCheck.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				boolean tmp = fileCheck.getSelection();				
				fileButton.setEnabled(tmp);
				fileLocation.setEnabled(tmp);
			}
		});

		projectCheck.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				boolean tmp = projectCheck.getSelection();
				projectButton.setEnabled(tmp);
				projectName.setEnabled(tmp);
			}
		});

	}

	class SearchListener implements Listener {
		private ITreeContentProvider contentProvider;

		public void handleEvent (Event event) {
			resultTable.removeAll();
			View view = null;
			dialog = new SysmlElementChooserDialog(dialog.getShell(), view);
			dialog.setMyFilter(new FilesFilter());
			dialog.setAsModelObject(false);
//			reread the file and the project
			IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();

			if(file != null)
				file = wsRoot.getFile(file.getFullPath());
			if(project != null)
				project = wsRoot.getProject(project.getName());

			initContentProvider();
			if (contentProvider == null) return;
			ArrayList collectedObjects = new ArrayList();
			ArrayList result = new ArrayList();

			if(fileCheck.getSelection())
				collectedObjects = collectFromFile(file);
			else if(projectCheck.getSelection())
				collectedObjects = collectFromProject(project);
			else if(workspace.getSelection())
				collectedObjects = collectFromWorkspace();

			result = querryWorkspace(collectedObjects);
			Iterator iter = collectedObjects.iterator();
			boolean resetTable = true;
			boolean negate = negated.getSelection(); 
			while(iter.hasNext()) {
				Object obj = iter.next();
				if(obj instanceof TableEntryUnassociations) {
					if(!result.contains(((TableEntryUnassociations)obj).getModelObject())) {
						if(!negate) {
							if(resetTable) {								
								resetTable = false;
							}
							putObjectToTable((TableEntryUnassociations)obj);
						}
					}else if (negate) {
						if(resetTable) {							
							resetTable = false;
						}
						putObjectToTable((TableEntryUnassociations)obj);
					}
				}
			}

		int headers = resultTable.getColumnCount();
		for (int j=0; j<headers; j++) {
			resultTable.getColumn (j).pack ();
		}					
	}

	private void initContentProvider() {
//		if(contentProvider == null) {
		contentProvider = dialog.getMyContentProvider();
//		}
	}

	public void putObjectToTable(TableEntryUnassociations obj) {
		String[] titles = {"Name", "Project", "File", "Type"};
		TableItem item = new TableItem (resultTable, SWT.NONE);
		Object element = obj.getModelObject();
		String elementName = "";
		String objectType = "";
		if(element instanceof ModelicaClass) {
			elementName = ((ModelicaClass)element).getName();
//			objectType = ((ModelicaClass)element).
		}
		if(element instanceof Requirement) {
			elementName = ((Requirement)element).getName();
		}

		item.setText (0, elementName);
		Object project = contentProvider.getParent(obj.getFile());
		if(project instanceof Project)
			item.setText (1, ((Project)project).getName());
		if(obj.getFile() != null)
			item.setText (2, obj.getFile().getName());
		item.setText (3, obj.getModelObject().getClass().getCanonicalName());
	}

	public ArrayList querryFile (IFile file, ArrayList objects) {

		Object[] children = contentProvider.getChildren(file);

		ArrayList result = new ArrayList();
		if(!isValidModelFile(file)) return result;

		Iterator iter = objects.iterator();
		while(iter.hasNext()) {
			Object obj = iter.next();
			if(obj instanceof TableEntryUnassociations) {
				Object entry = ((TableEntryUnassociations)obj).getModelObject();
				boolean checkResult;
				if(entry instanceof ModelicaClass) {
					checkResult = checkClass((ModelicaClass)entry, children);
					if(checkResult)
						result.add(entry);
				}
				if(entry instanceof Requirement) {
					checkResult = checkRequirement((Requirement)entry, children);
					if (checkResult)
						result.add(entry);
				}
			}
		}
		return result;
	}

	public ArrayList querryProject (IProject project,ArrayList objects) {
		Object[] children = contentProvider.getChildren(project);
		ArrayList result = new ArrayList();
		for(int i = 0;i<children.length;i++)
			if(children[i] instanceof IFile)
				result.addAll(querryFile((IFile)children[i], objects));

		return result;

	}

	public ArrayList querryWorkspace(ArrayList objects) {
		Object obj = ResourcesPlugin.getWorkspace().getRoot();
		Object[] elements = contentProvider.getChildren(obj);
		ArrayList result = new ArrayList();
		for(int i = 0;i<elements.length;i++)
			if(elements[i] instanceof IProject)
				result.addAll(querryProject((IProject)elements[i], objects));
		return result;
	}

	public ArrayList<TableEntryUnassociations> collectFromFile(IFile file) {
		boolean checkClasses = classCheck.getSelection();
		ArrayList<TableEntryUnassociations> objects = new ArrayList<TableEntryUnassociations>();
		if(!isValidModelFile(file)) return objects;
		if (contentProvider == null)
			return objects;

		Object[] children = contentProvider.getChildren(file);
		for(int i = 0;i<children.length;i++) {
			if(children[i] instanceof Model) {
				Object[] modelChildren = contentProvider.getChildren(children[i]);
				for(int j = 0;j<modelChildren.length;j++) {	
					if(checkClasses) {
						if(modelChildren[j] instanceof ModelicaClass)
							objects.add(new TableEntryUnassociations(modelChildren[j], file));
					} else if(modelChildren[j] instanceof Requirement)
						objects.add(new TableEntryUnassociations(modelChildren[j], file));
				}
			}
		}
		return objects;
	}

	public ArrayList<TableEntryUnassociations> collectFromProject(IProject project) {
		ArrayList<TableEntryUnassociations> objects = new ArrayList<TableEntryUnassociations>();
		Object[] children = contentProvider.getChildren(project);
		for(int i = 0;i<children.length;i++) {
			if(children[i] instanceof IFile)
				objects.addAll(collectFromFile((IFile)children[i]));
		}
		return objects;
	}

	public ArrayList<TableEntryUnassociations> collectFromWorkspace() {
		ArrayList<TableEntryUnassociations> objects = new ArrayList<TableEntryUnassociations>();
		Object obj = ResourcesPlugin.getWorkspace().getRoot();
		Object[] children = contentProvider.getChildren(obj);
		for(int i = 0;i<children.length;i++) {
			if(children[i] instanceof IProject)
				objects.addAll(collectFromProject((IProject)children[i]));
		}
		return objects;
	}

	private boolean checkRequirement(Requirement req, Object[] modelObjects) {
		Iterator iterator = req.getSatisfiedBy().iterator();
		while(iterator.hasNext())
			if(iterator.next() instanceof ModelicaClass)
				return true;

		for(int i = 0;i<modelObjects.length;i++) {
			if(modelObjects[i] instanceof Model) {
				Object[] modelChildren = contentProvider.getChildren(modelObjects[i]);
				for(int j = 0; j<modelChildren.length;j++) {
					if(modelChildren[i] instanceof SatisfiedBy) {
						Object[] children = contentProvider.getChildren(modelChildren[i]);
						for(int k = 0;k<children.length;k++) {
							if(children[k] instanceof SatisfiedByProperty)
								if(((SatisfiedByProperty)children[k]).getElement() != null)
									return true;
						}
					}
					
					if(modelChildren[j] instanceof GeneralRelationship) {
						GeneralRelationship  satisfiesRelationship = (GeneralRelationship)modelChildren[j];
						if(satisfiesRelationship.getTarget() instanceof Satisfies) {
							Satisfies satisfies = (Satisfies)satisfiesRelationship.getTarget();
							Object[] satisfiesChildren = satisfies.getSatisfies().toArray();
							for (int l = 0; l < satisfiesChildren.length; l++) {
								if((((SatisfiesProperty)satisfiesChildren[l]).getRequirement()).getName().equals(req.getName()))
									if(satisfiesRelationship.getSource() instanceof ModelicaClass)
									return true;
							}

						}
					}else if(modelChildren[j] instanceof SatisfyRelationship) {
						SatisfyRelationship relationship = (SatisfyRelationship)modelChildren[j];
						if(relationship.getSource()instanceof Requirement) {
							if(((Requirement)relationship.getSource()).getName().equals(req.getName()))
								if(relationship.getTarget() != null) {
									return true;
								}
						}
					}
					
				}

			}
		}
		return false;
	}

	private boolean checkClass(ModelicaClass modelicaClass, Object[] modelObjects) {
		for(int i = 0;i<modelObjects.length;i++) {
			if(modelObjects[i] instanceof Model) {
				Object[] modelChildren = contentProvider.getChildren(modelObjects[i]);
				for(int j = 0; j<modelChildren.length;j++) {
					if(modelChildren[j] instanceof GeneralRelationship) {
						GeneralRelationship relationship = (GeneralRelationship)modelChildren[j];
						if(relationship.getSource() == modelicaClass) {
							if(relationship.getTarget() instanceof Satisfies) {
								Object[] children = contentProvider.getChildren(relationship.getTarget());
								for(int k = 0;k<children.length;k++) {
									if(children[k] instanceof SatisfiesProperty)
										if(((SatisfiesProperty)children[k]).getRequirement() != null)
											return true;
								}
							}
						}		
					} else if(modelChildren[j] instanceof SatisfyRelationship) {
						SatisfyRelationship relationship = (SatisfyRelationship)modelChildren[j];
						if(relationship.getTarget()instanceof ModelicaClass) {
							if(((ModelicaClass)relationship.getTarget()).getName().equals(modelicaClass.getName()))
								if(relationship.getSource() != null) {
									return true;
								}
						}
					}
					
					
				}
			}
		}

		return false;
	}

	private boolean isValidModelFile(IFile file) {
		String fileExtension = file.getFullPath().getFileExtension();
		return "sysml".equals(fileExtension); //$NON-NLS-1$
	}
}



}

