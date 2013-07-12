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
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_indices;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>for statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement#getFor_indices <em>For indices</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement#getFor_statement <em>For statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getfor_statement()
 * @model
 * @generated
 */
public interface for_statement extends EObject
{
  /**
   * Returns the value of the '<em><b>For indices</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>For indices</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>For indices</em>' containment reference.
   * @see #setFor_indices(for_indices)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getfor_statement_For_indices()
   * @model containment="true"
   * @generated
   */
  for_indices getFor_indices();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement#getFor_indices <em>For indices</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>For indices</em>' containment reference.
   * @see #getFor_indices()
   * @generated
   */
  void setFor_indices(for_indices value);

  /**
   * Returns the value of the '<em><b>For statement</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>For statement</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>For statement</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getfor_statement_For_statement()
   * @model containment="true"
   * @generated
   */
  EList<for_statement> getFor_statement();

} // for_statement
