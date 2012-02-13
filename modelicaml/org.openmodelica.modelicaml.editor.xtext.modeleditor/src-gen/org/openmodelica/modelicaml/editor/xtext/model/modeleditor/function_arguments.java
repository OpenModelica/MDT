/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>function arguments</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getArgExp <em>Arg Exp</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getFun_Arg_Expr <em>Fun Arg Expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getFun_Arg_For <em>Fun Arg For</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getName_arg <em>Name arg</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getfunction_arguments()
 * @model
 * @generated
 */
public interface function_arguments extends EObject
{
  /**
   * Returns the value of the '<em><b>Arg Exp</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arg Exp</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arg Exp</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getfunction_arguments_ArgExp()
   * @model containment="true"
   * @generated
   */
  EList<expression> getArgExp();

  /**
   * Returns the value of the '<em><b>Fun Arg Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fun Arg Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fun Arg Expr</em>' containment reference.
   * @see #setFun_Arg_Expr(Fun_Arguments_exp)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getfunction_arguments_Fun_Arg_Expr()
   * @model containment="true"
   * @generated
   */
  Fun_Arguments_exp getFun_Arg_Expr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getFun_Arg_Expr <em>Fun Arg Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fun Arg Expr</em>' containment reference.
   * @see #getFun_Arg_Expr()
   * @generated
   */
  void setFun_Arg_Expr(Fun_Arguments_exp value);

  /**
   * Returns the value of the '<em><b>Fun Arg For</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fun Arg For</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fun Arg For</em>' containment reference.
   * @see #setFun_Arg_For(Fun_Arguments_for)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getfunction_arguments_Fun_Arg_For()
   * @model containment="true"
   * @generated
   */
  Fun_Arguments_for getFun_Arg_For();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getFun_Arg_For <em>Fun Arg For</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fun Arg For</em>' containment reference.
   * @see #getFun_Arg_For()
   * @generated
   */
  void setFun_Arg_For(Fun_Arguments_for value);

  /**
   * Returns the value of the '<em><b>Name arg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name arg</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name arg</em>' containment reference.
   * @see #setName_arg(named_arguments)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getfunction_arguments_Name_arg()
   * @model containment="true"
   * @generated
   */
  named_arguments getName_arg();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getName_arg <em>Name arg</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name arg</em>' containment reference.
   * @see #getName_arg()
   * @generated
   */
  void setName_arg(named_arguments value);

} // function_arguments
