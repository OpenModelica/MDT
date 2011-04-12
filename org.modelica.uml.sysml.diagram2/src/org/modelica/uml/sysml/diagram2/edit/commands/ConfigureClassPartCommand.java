package org.modelica.uml.sysml.diagram2.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.UMLPackage;
import org.modelica.uml.sysml.ModelicaConnectorProperty;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.ConfigureElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

public abstract class ConfigureClassPartCommand 
			extends ConfigureElementCommand {
	
	 /**
     * The input terminal feature.
     */
    private static final EReference TERMINALS = UMLPackage.eINSTANCE
        .getProperty_SubsettedProperty();

	
	 /**
     * Constructs a new configure command for class part.
     * 
     * @param request
     *            the configure request
     * @param configurableType
     *            the kind of element that can be configured by this command
     *            instance.
     */
    protected ConfigureClassPartCommand(ConfigureRequest request,
            EClass configurableType) {

        super(request);
        setEClass(configurableType);
    }
    
    /**
     * Creates a new element.
     * 
     * @param container
     *            the container for the new element
     * @param type
     *            the kind of new element to create
     * @param containmentFeature
     *            the feature in which to put the new element
     * @param progressMonitor
     *            progressMonitor the monitor to measure progress through
     *            long-running operations
     * @return the new <code>Element</code>, or <code>null</code> if none
     *         was created
     */
    private EObject createElement(EObject container, IElementType type,
            EReference containmentFeature, IProgressMonitor progressMonitor) {

        CreateElementRequest createRequest = new CreateElementRequest(
            getEditingDomain(), container, type, containmentFeature);

        IElementType elementType = ElementTypeRegistry.getInstance()
            .getElementType(createRequest.getEditHelperContext());

        if (elementType != null) {
            ICommand createCommand = elementType.getEditCommand(createRequest);

            if (createCommand != null && createCommand.canExecute()) {

                try {
                    createCommand.execute(progressMonitor, null);
                    CommandResult commandResult = createCommand
                        .getCommandResult();

                    if (isOK(commandResult)) {
                        Object result = commandResult.getReturnValue();

                        if (result instanceof EObject) {
                            return (EObject) result;
                        }
                    }

                } catch (ExecutionException e) {
        
                }
            }
        }
        return null;
    }

    /**
     * Creates a new conector port in the <code>containmentFeature</code> of
     * <code>classPart</code>
     * 
     * 
     * @param classPart
     *            the class part
     * 
     * @param progressMonitor
     *            the monitor to measure progress through long-running
     *            operations
     * @return the new terminal element, or <code>null</code> if it wasn't
     *         created
     */
    protected ModelicaConnectorProperty createConnector(
            EObject classPart, IProgressMonitor progressMonitor) {

    /*	EObject result = createElement(classPart, SysmlElementTypes.ModelicaPort_2070, TERMINALS,
            progressMonitor);

        if (result instanceof ModelicaConnectorProperty) {
            return (ModelicaConnectorProperty) result;
    	
        }
*/
        return null;
    }



}
