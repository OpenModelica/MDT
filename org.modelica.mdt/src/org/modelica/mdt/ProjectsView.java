package org.modelica.mdt;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.navigator.ResourceNavigator;

public class ProjectsView extends ResourceNavigator
{
	public class ViewFilter extends ViewerFilter {

		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element)
		{
			if ((element instanceof IFile) ||
				(element instanceof IFolder))
			{
				return !((IResource)element).getName().startsWith("."); 
			}
			return true;
		}


	}

	public void createPartControl(Composite parent)
	{
		super.createPartControl(parent);
		this.getViewer().addFilter(new ViewFilter());
	}
}
