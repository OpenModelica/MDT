// Copyright (c) 2005 by Leif Frenzel. All rights reserved.
// See http://leiffrenzel.de
package org.modelica.mdt.ui.refactoring.ui.wizards;

import org.eclipse.ltk.ui.refactoring.RefactoringWizard;

import org.modelica.mdt.ui.refactoring.core.RenameFunctionInfo;
import org.modelica.mdt.ui.refactoring.core.RenameFunctionRefactoring;


/** <p>The wizard that is shown to the user for entering the necessary 
  * information for property renaming.</p>
  * 
  * <p>The wizard class is primarily needed for deciding which pages are 
  * shown to the user. The actual user interface creation goes on the 
  * pages.</p>
  * 
  * @author Leif Frenzel
  */
public class RenameFunctionWizard extends RefactoringWizard {

  private final RenameFunctionInfo info;


  public RenameFunctionWizard( final RenameFunctionRefactoring refactoring,
                               final RenameFunctionInfo info ) {
    super( refactoring, DIALOG_BASED_USER_INTERFACE );
    this.info = info;
  }


  // interface methods of RefactoringWizard
  /////////////////////////////////////////

  protected void addUserInputPages() {
    setDefaultPageTitle( getRefactoring().getName() );
    addPage( new RenameFunctionInputPage( info ) );
  }
}
