/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator;

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
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getName_Function <em>Name Function</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getInitial_ref <em>Initial ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getSum <em>Sum</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getProd <em>Prod</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getMin <em>Min</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getMax <em>Max</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getAvg <em>Avg</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getSize <em>Size</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getToArray <em>To Array</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getAnd <em>And</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getOr <em>Or</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getXor <em>Xor</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getSingleProvider <em>Single Provider</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary()
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
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_Name_Function()
   * @model containment="true"
   * @generated
   */
  name_Function getName_Function();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getName_Function <em>Name Function</em>}' containment reference.
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
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_Initial_ref()
   * @model containment="true"
   * @generated
   */
  initial_ref getInitial_ref();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getInitial_ref <em>Initial ref</em>}' containment reference.
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
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_Expr()
   * @model containment="true"
   * @generated
   */
  ExprDer getExpr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getExpr <em>Expr</em>}' containment reference.
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
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_Sum()
   * @model
   * @generated
   */
  String getSum();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getSum <em>Sum</em>}' attribute.
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
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_Prod()
   * @model
   * @generated
   */
  String getProd();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getProd <em>Prod</em>}' attribute.
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
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_Min()
   * @model
   * @generated
   */
  String getMin();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getMin <em>Min</em>}' attribute.
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
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_Max()
   * @model
   * @generated
   */
  String getMax();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getMax <em>Max</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max</em>' attribute.
   * @see #getMax()
   * @generated
   */
  void setMax(String value);

  /**
   * Returns the value of the '<em><b>Avg</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Avg</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Avg</em>' attribute.
   * @see #setAvg(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_Avg()
   * @model
   * @generated
   */
  String getAvg();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getAvg <em>Avg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Avg</em>' attribute.
   * @see #getAvg()
   * @generated
   */
  void setAvg(String value);

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
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_Size()
   * @model
   * @generated
   */
  String getSize();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getSize <em>Size</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Size</em>' attribute.
   * @see #getSize()
   * @generated
   */
  void setSize(String value);

  /**
   * Returns the value of the '<em><b>To Array</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>To Array</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>To Array</em>' attribute.
   * @see #setToArray(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_ToArray()
   * @model
   * @generated
   */
  String getToArray();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getToArray <em>To Array</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>To Array</em>' attribute.
   * @see #getToArray()
   * @generated
   */
  void setToArray(String value);

  /**
   * Returns the value of the '<em><b>And</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>And</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>And</em>' attribute.
   * @see #setAnd(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_And()
   * @model
   * @generated
   */
  String getAnd();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getAnd <em>And</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>And</em>' attribute.
   * @see #getAnd()
   * @generated
   */
  void setAnd(String value);

  /**
   * Returns the value of the '<em><b>Or</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Or</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Or</em>' attribute.
   * @see #setOr(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_Or()
   * @model
   * @generated
   */
  String getOr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getOr <em>Or</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Or</em>' attribute.
   * @see #getOr()
   * @generated
   */
  void setOr(String value);

  /**
   * Returns the value of the '<em><b>Xor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Xor</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Xor</em>' attribute.
   * @see #setXor(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_Xor()
   * @model
   * @generated
   */
  String getXor();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getXor <em>Xor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Xor</em>' attribute.
   * @see #getXor()
   * @generated
   */
  void setXor(String value);

  /**
   * Returns the value of the '<em><b>Single Provider</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Single Provider</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Single Provider</em>' attribute.
   * @see #setSingleProvider(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage#getprimary_SingleProvider()
   * @model
   * @generated
   */
  String getSingleProvider();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getSingleProvider <em>Single Provider</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Single Provider</em>' attribute.
   * @see #getSingleProvider()
   * @generated
   */
  void setSingleProvider(String value);

} // primary
