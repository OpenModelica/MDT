package org.modelica.uml.sysml.diagram2.views;

import org.eclipse.core.resources.IFile;


public class TableEntryUnassociations {
	private Object modelObject;
	private IFile file;

	public TableEntryUnassociations(Object obj, IFile file) {
		setModelObject(obj);
		setFile(file);
	}

	public IFile getFile() {
		return file;
	}

	public void setFile(IFile file) {
		this.file = file;
	}

	static final TableEntry[] union(TableEntry[]a, TableEntry[] b) {
		TableEntry[] union = new TableEntry[a.length + b.length];
		for(int i = 0; i<a.length;i++)
			union[i] = a[i];

		for(int i = 0; i<b.length;i++)
			union[a.length + i] = b[i];

		return union;
	}

	public Object getModelObject() {
		return modelObject;
	}

	public void setModelObject(Object modelObject) {
		this.modelObject = modelObject;
	}
}