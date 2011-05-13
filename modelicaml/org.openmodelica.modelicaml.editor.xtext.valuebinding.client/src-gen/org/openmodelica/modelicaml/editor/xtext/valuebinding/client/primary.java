/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.client;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.initial_ref;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name_Function;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>primary</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getName_Function <em>Name Function</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getInitial_ref <em>Initial ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getSum <em>Sum</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getProd <em>Prod</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getMin <em>Min</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getMax <em>Max</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getAvr <em>Avr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getSize <em>Size</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary()
 * @model
 * @generated
 */
public interface primary extends org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary
{
  /**
   * Returns the value of the '<em><b>Name Function</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name Function</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name Function</em>' containment reference.
   * @see #setName_Function(name_Function)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Name_Function()
   * @model containment="true"
   * @generated
   */
  name_Function getName_Function();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getName_Function <em>Name Function</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name Function</em>' containment reference.
   * @see #getName_Function()
   * @generated
   */
  void setName_Function(name_Function value);

  /**
   * Returns the value of the '<em><b>Initial ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Initial ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Initial ref</em>' containment reference.
   * @see #setInitial_ref(initial_ref)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Initial_ref()
   * @model containment="true"
   * @generated
   */
  initial_ref getInitial_ref();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getInitial_ref <em>Initial ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Initial ref</em>' containment reference.
   * @see #getInitial_ref()
   * @generated
   */
  void setInitial_ref(initial_ref value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(ExprDer)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Expr()
   * @model containment="true"
   * @generated
   */
  ExprDer getExpr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(ExprDer value);

  /**
   * Returns the value of the '<em><b>Sum</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sum</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sum</em>' attribute.
   * @see #setSum(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Sum()
   * @model
   * @generated
   */
  String getSum();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getSum <em>Sum</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sum</em>' attribute.
   * @see #getSum()
   * @generated
   */
  void setSum(String value);

  /**
   * Returns the value of the '<em><b>Prod</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Prod</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Prod</em>' attribute.
   * @see #setProd(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Prod()
   * @model
   * @generated
   */
  String getProd();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getProd <em>Prod</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Prod</em>' attribute.
   * @see #getProd()
   * @generated
   */
  void setProd(String value);

  /**
   * Returns the value of the '<em><b>Min</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Min</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min</em>' attribute.
   * @see #setMin(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Min()
   * @model
   * @generated
   */
  String getMin();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getMin <em>Min</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min</em>' attribute.
   * @see #getMin()
   * @generated
   */
  void setMin(String value);

  /**
   * Returns the value of the '<em><b>Max</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Max</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max</em>' attribute.
   * @see #setMax(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Max()
   * @model
   * @generated
   */
  String getMax();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getMax <em>Max</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max</em>' attribute.
   * @see #getMax()
   * @generated
   */
  void setMax(String value);

  /**
   * Returns the value of the '<em><b>Avr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Avr</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Avr</em>' attribute.
   * @see #setAvr(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Avr()
   * @model
   * @generated
   */
  String getAvr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getAvr <em>Avr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Avr</em>' attribute.
   * @see #getAvr()
   * @generated
   */
  void setAvr(String value);

  /**
   * Returns the value of the '<em><b>Size</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Size</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Size</em>' attribute.
   * @see #setSize(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Size()
   * @model
   * @generated
   */
  String getSize();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getSize <em>Size</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Size</em>' attribute.
   * @see #getSize()
   * @generated
   */
  void setSize(String value);

  /**
   * Returns the value of the '<em><b>Result</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Result</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Result</em>' attribute.
   * @see #setResult(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Result()
   * @model
   * @generated
   */
  String getResult();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getResult <em>Result</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Result</em>' attribute.
   * @see #getResult()
   * @generated
   */
  void setResult(String value);

} // primary
