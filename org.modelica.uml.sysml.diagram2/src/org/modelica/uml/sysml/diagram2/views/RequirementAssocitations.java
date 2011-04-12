package org.modelica.uml.sysml.diagram2.views;


import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.*;
import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.uml2.uml.Model;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.modelica.uml.sysml.GeneralRelationship;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.Requirement;
import org.modelica.uml.sysml.SatisfiedBy;
import org.modelica.uml.sysml.SatisfiedByProperty;
import org.modelica.uml.sysml.SatisfiedByRelationship;
import org.modelica.uml.sysml.Satisfies;
import org.modelica.uml.sysml.SatisfiesProperty;
import org.modelica.uml.sysml.SatisfyRelationship;
import org.modelica.uml.sysml.diagram2.util.ModelElementsTreeContentProvider;


public class RequirementAssocitations extends ViewPart implements ISelectionListener{
	Table table;

	ModelElementsTreeContentProvider contentProvider;

	/**
	 * The constructor.
	 */
	public RequirementAssocitations() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		table = new Table(parent, SWT.MULTI | SWT.BORDER);

		String[] titles = {"Name","Project", "File","Type"};
		for (int c=0; c<titles.length; c++) {
			TableColumn column = new TableColumn (table, SWT.NONE);
			column.setText (titles [c]);
		}	
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		for (int j=0; j<titles.length; j++) {
			table.getColumn (j).pack ();
		}

		getViewSite().getPage().addSelectionListener(this);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		contentProvider = new ModelElementsTreeContentProvider();
		if (selection instanceof StructuredSelection) {
			StructuredSelection new_name = (StructuredSelection) selection;
			Object first = new_name.getFirstElement();
			if (first instanceof EditPart) {
				EditPart editPart = (EditPart) first;
				if (editPart.getModel() instanceof View) {
					table.removeAll();
					View view = (View) editPart.getModel();
					Object elem = view.getElement();
					ArrayList result = querryWorkspace(elem);
					Iterator iter = result.iterator();

					while (iter.hasNext()) {
						elem = iter.next();
						if (elem instanceof TableEntryUnassociations) {
							TableEntryUnassociations entry = (TableEntryUnassociations) elem;
							putObjectToTable(entry);
						}	

					}
					int headers = table.getColumnCount();
					for (int j=0; j<headers; j++) {
						table.getColumn (j).pack ();
					}		
				}

			}
		}
		contentProvider.dispose();
	}

	public void putObjectToTable(TableEntryUnassociations obj) {
		String[] titles = {"Name", "Project", "File", "Type"};
		TableItem item = new TableItem (table, SWT.NONE);
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

	public ArrayList querryFile(Object elem, IFile file) {
		ArrayList result = new ArrayList();
		if (!isValidModelFile(file)) return result;
		if (elem instanceof ModelicaClass)
			result.addAll(checkClass((ModelicaClass)elem, file));
		else if (elem instanceof Requirement)
			result.addAll(checkRequirement((Requirement)elem, file));

		return result;
	}


	public ArrayList querryProject (Object elem, IProject project) {
		Object[] children = contentProvider.getChildren(project);
		ArrayList result = new ArrayList();
		for(int i = 0;i<children.length;i++)
			if(children[i] instanceof IFile)
				result.addAll(querryFile(elem, (IFile)children[i]));

		return result;

	}

	public ArrayList querryWorkspace(Object elem) {
		Object obj = ResourcesPlugin.getWorkspace().getRoot();
		Object[] elements = contentProvider.getChildren(obj);
		ArrayList result = new ArrayList();
		for(int i = 0;i<elements.length;i++)
			if(elements[i] instanceof IProject)
				result.addAll(querryProject(elem,(IProject)elements[i]));
		return result;
	}


	public ArrayList checkClass(ModelicaClass modelicaClass, IFile file) {

		Object[] modelObjects = contentProvider.getChildren(file);

		ArrayList result = new ArrayList();

		for(int i = 0;i<modelObjects.length;i++) {
			if(modelObjects[i] instanceof Model) {
				Object[] modelChildren = contentProvider.getChildren(modelObjects[i]);
				for(int j = 0; j<modelChildren.length;j++)
					if(modelChildren[j] instanceof GeneralRelationship) {
						GeneralRelationship relationship = (GeneralRelationship)modelChildren[j];
						if(relationship.getSource()instanceof ModelicaClass) {
							if(((ModelicaClass)relationship.getSource()).getName().equals(modelicaClass.getName()))
								if(relationship.getTarget() instanceof Satisfies) {
									Object[] children = ((Satisfies)relationship.getTarget()).getSatisfies().toArray();
									for(int k = 0;k<children.length;k++) {
										if(children[k] instanceof SatisfiesProperty)
											if(((SatisfiesProperty)children[k]).getRequirement() != null) {
												result.add(new TableEntryUnassociations(((SatisfiesProperty)children[k]).getRequirement(), file));
											}
									}
								}
						}		
					} else if(modelChildren[j] instanceof SatisfyRelationship) {
						SatisfyRelationship relationship = (SatisfyRelationship)modelChildren[j];
						if(relationship.getTarget()instanceof ModelicaClass) {
							if(((ModelicaClass)relationship.getTarget()).getName().equals(modelicaClass.getName()))
								if(relationship.getSource() != null) {
									result.add(new TableEntryUnassociations(relationship.getSource(), file));
								}
						}
					}
			}		
		}

		return result;
	}


	public ArrayList checkRequirement(Requirement req, IFile file) {

		Object[] modelObjects = contentProvider.getChildren(file);

		ArrayList result = new ArrayList();
		if(isEObjectFile(req, file)) {
			Iterator iterator = req.getSatisfiedBy().iterator();
			while(iterator.hasNext()) {
				Object object =iterator.next();  
				if(object instanceof ModelicaClass)
					result.add(new TableEntryUnassociations(object, file));
			}
		}
		for(int i = 0;i<modelObjects.length;i++) {
			if(modelObjects[i] instanceof Model) {
				Object[] modelChildren = contentProvider.getChildren(modelObjects[i]);
				for(int j = 0; j<modelChildren.length;j++) {
					if(isEObjectFile(req, file))
						if(modelChildren[j] instanceof SatisfiedByRelationship) {
							SatisfiedByRelationship satisfiedByRelationship = (SatisfiedByRelationship)modelChildren[j];
							if(satisfiedByRelationship.getSource() instanceof Requirement){
								if(((Requirement)satisfiedByRelationship.getSource()).getName().equals(req.getName()))
									if(satisfiedByRelationship.getTarget() instanceof SatisfiedBy){
										SatisfiedBy satisfiedBy = (SatisfiedBy)satisfiedByRelationship.getTarget();
										Object[] children = satisfiedBy.getSatisfiedBy().toArray();
										for(int k = 0;k<children.length;k++) {
											if(children[k] instanceof SatisfiedByProperty)
												if(((SatisfiedByProperty)children[k]).getElement() != null)
													result.add(new TableEntryUnassociations(((SatisfiedByProperty)children[k]).getElement(), file));

										}
									}
							}
						}

					if(modelChildren[j] instanceof GeneralRelationship) {
						GeneralRelationship  satisfiesRelationship = (GeneralRelationship)modelChildren[j];
						if(satisfiesRelationship.getTarget() instanceof Satisfies) {
							Satisfies satisfies = (Satisfies)satisfiesRelationship.getTarget();
							Object[] satisfiesChildren = satisfies.getSatisfies().toArray();
							for (int l = 0; l < satisfiesChildren.length; l++) {
								if(((SatisfiesProperty)satisfiesChildren[l]).getRequirement() != null)
									if((((SatisfiesProperty)satisfiesChildren[l]).getRequirement()).getName().equals(req.getName()))
										if(satisfiesRelationship.getSource() instanceof ModelicaClass)
											result.add(new TableEntryUnassociations(satisfiesRelationship.getSource(), file));
							}

						}
					}

					if(modelChildren[j] instanceof SatisfyRelationship) {
						SatisfyRelationship relationship = (SatisfyRelationship)modelChildren[j];
						if(relationship.getSource()instanceof Requirement) {
							if(((Requirement)relationship.getSource()).getName().equals(req.getName()))
								if(relationship.getTarget() != null) {
									result.add(new TableEntryUnassociations(relationship.getTarget(), file));
								}
						}
					}
				}
			}
		}

		return result;
	}


	private boolean isValidModelFile(IFile file) {
		String fileExtension = file.getFullPath().getFileExtension();
		return "sysml".equals(fileExtension); //$NON-NLS-1$
	}

	private boolean isEObjectFile(EObject obj, IFile file) {
		String str1 = obj.eResource().getURI().path();
		return str1.contains(file.getName());
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		if(table != null)
			table.setFocus();
	}
}