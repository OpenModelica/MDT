package org.openmodelica.modelicaml.generate.modelica.services;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

//import fr.obeo.acceleo.gen.AcceleoEcoreGenPlugin;


// TODO: Auto-generated Javadoc
/**
 * The Class MoldelicaMLValidator.
 */
public class MoldelicaMLValidator {
	
	/**
	 * Display.
	 * 
	 * @param elt
	 *            the elt
	 * @param msg
	 *            the msg
	 * @param criticality
	 *            the criticality
	 */
	public static void display(Element elt, String msg, String criticality){
		if (!criticality.equals("")) {
//			AcceleoEcoreGenPlugin.getDefault().log(msg, true);			
		}
		else {
//			AcceleoEcoreGenPlugin.getDefault().log(msg, false);

		}
	}
	
	/**
	 * Validation alert.
	 * 
	 * @param elt
	 *            the elt
	 * @param criticality
	 *            the criticality
	 * @param msg
	 *            the msg
	 */
	public static void validationAlert (Element elt, String criticality, String msg){
		if (!criticality.equals("")) {
//			AcceleoEcoreGenPlugin.getDefault().log(msg, true);			
		}
		else {
//			AcceleoEcoreGenPlugin.getDefault().log(msg, false);
		}
		modelicaMLValidationAlert(elt, criticality, msg);
	}
	
	/**
	 * Message dialog.
	 * 
	 * @param elt
	 *            the elt
	 * @param title
	 *            the title
	 * @param msg
	 *            the msg
	 */
	public static void messageDialog(Element elt, String title, String msg){
		
//		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//		if (window != null) {
//			MessageDialog.openError(window.getShell(), title, msg); //$NON-NLS-1$
//		} else {
//			//AcceleoUIActivator.getDefault().getLog().log(e.getStatus());
//		}
		
		//Shell shell = new Shell();
		//AcceleoEcoreGenPlugin.getDefault().getBundle().
		//MessageDialog.openInformation(null, title, msg);
	}
	
	/**
	 * Modelica ml validation alert.
	 * 
	 * @param elt
	 *            the elt
	 * @param criticality
	 *            the criticality
	 * @param msg
	 *            the msg
	 */
	public static void modelicaMLValidationAlert (Element elt, String criticality, String msg){
		IResource r = null;
		URI eUri = elt.eResource().getURI();
		
		if (eUri.isPlatformResource()) {
			String platformString = eUri.toPlatformString(true);
			r = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
		}
			
		try {
		//	IMarker marker = r.createMarker(EValidator.MARKER);
			IMarker marker = r.createMarker("org.openmodelica.modelicaml.modelicamlMarker.validation");
			
			marker.setAttribute(IMarker.MESSAGE, msg);
			
			Integer crit = null;
			if ( criticality.equals("error") ) 	{ crit = IMarker.SEVERITY_ERROR;	}
			else 							{ crit = IMarker.SEVERITY_INFO; }
			
			marker.setAttribute(IMarker.SEVERITY, crit);
			marker.setAttribute(IMarker.LOCATION, ((NamedElement)elt).getQualifiedName());
		//	marker.setAttribute(EValidator.URI_ATTRIBUTE, elt.eResource().getURI().toString());
			marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(elt).toString());//elt.eResource().getURI().toString());
			
			//IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			//ModelicaMLValidationView.openMarkerInEditor(marker, page);
		
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Creates the modelica ml code validation marker.
	 * 
	 * @param elt
	 *            the elt
	 * @param criticality
	 *            the criticality
	 * @param msg
	 *            the msg
	 * @return the i marker
	 */
	public static IMarker createModelicaMLCodeValidationMarker(Element elt, String criticality, String msg){
		IResource r = null;
		URI eUri = elt.eResource().getURI();
		
		if (eUri.isPlatformResource()) {
			String platformString = eUri.toPlatformString(true);
			r = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
		}
			
		try {
		//	IMarker marker = r.createMarker(EValidator.MARKER);
			IMarker marker = r.createMarker("org.openmodelica.modelicaml.modelicamlMarker.validation");
			
			marker.setAttribute(IMarker.MESSAGE, msg);
			
			Integer crit = null;
			if ( criticality.equals("error") ) 	{ crit = IMarker.SEVERITY_ERROR;	}
			else 							{ crit = IMarker.SEVERITY_INFO; }
			
			marker.setAttribute(IMarker.SEVERITY, crit);
			marker.setAttribute(IMarker.LOCATION, ((NamedElement)elt).getQualifiedName());
		//	marker.setAttribute(EValidator.URI_ATTRIBUTE, elt.eResource().getURI().toString());
			marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(elt).toString());//elt.eResource().getURI().toString());
			
			//IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			//ModelicaMLValidationView.openMarkerInEditor(marker, page);
			return marker;
		
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
