/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.client.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.factor;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.term;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.component_reference;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage
 * @generated
 */
public class ClientAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ClientPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClientAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = ClientPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ClientSwitch<Adapter> modelSwitch =
    new ClientSwitch<Adapter>()
    {
      @Override
      public Adapter caseClientOperation(ClientOperation object)
      {
        return createClientOperationAdapter();
      }
      @Override
      public Adapter caseprimary(primary object)
      {
        return createprimaryAdapter();
      }
      @Override
      public Adapter casecomponent_reference(component_reference object)
      {
        return createcomponent_referenceAdapter();
      }
      @Override
      public Adapter caseterm(term object)
      {
        return createtermAdapter();
      }
      @Override
      public Adapter casefactor(factor object)
      {
        return createfactorAdapter();
      }
      @Override
      public Adapter caseModeleditor_primary(org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary object)
      {
        return createModeleditor_primaryAdapter();
      }
      @Override
      public Adapter caseModeleditor_component_reference(org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference object)
      {
        return createModeleditor_component_referenceAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation
   * @generated
   */
  public Adapter createClientOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary <em>primary</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary
   * @generated
   */
  public Adapter createprimaryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.component_reference <em>component reference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.component_reference
   * @generated
   */
  public Adapter createcomponent_referenceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link modeleditor.term <em>term</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see modeleditor.term
   * @generated
   */
  public Adapter createtermAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link modeleditor.factor <em>factor</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see modeleditor.factor
   * @generated
   */
  public Adapter createfactorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link modeleditor.primary <em>primary</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see modeleditor.primary
   * @generated
   */
  public Adapter createModeleditor_primaryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link modeleditor.component_reference <em>component reference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see modeleditor.component_reference
   * @generated
   */
  public Adapter createModeleditor_component_referenceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //ClientAdapterFactory
