package org.modelica.mdt;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Plugin;

public class MdtPlugin extends Plugin
{

	public static final String MODELICA_NATURE = 
		"org.modelica.mdt.ModelicaNature";
	
	public static void addModelicaNature(IProject project) throws CoreException
	{
		if (project.hasNature(MODELICA_NATURE)) 
			return;

		IProjectDescription description = project.getDescription();
		String[] ids= description.getNatureIds();
		String[] newIds= new String[ids.length + 1];
		System.arraycopy(ids, 0, newIds, 0, ids.length);
		newIds[ids.length]= MODELICA_NATURE;
		description.setNatureIds(newIds);
		project.setDescription(description, null);
	}
	
}
