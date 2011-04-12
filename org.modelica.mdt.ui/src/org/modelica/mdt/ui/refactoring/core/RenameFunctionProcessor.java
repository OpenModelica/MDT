// Copyright (c) 2005 by Leif Frenzel. All rights reserved.
// See http://leiffrenzel.de
package org.modelica.mdt.ui.refactoring.core;

import org.eclipse.core.runtime.*;
import org.eclipse.ltk.core.refactoring.*;
import org.eclipse.ltk.core.refactoring.participants.*;

/** <p>The processor is where the work is delegated to if participants are 
  * involved. The processor loads the participants and manages the lifecycle
  * of the refactoring. In order to do that, the refactoring entry point 
  * methods must be implemented.</p>
  *
  * @author Leif Frenzel
  */
public class RenameFunctionProcessor extends RefactoringProcessor {

  private final RenameFunctionInfo info;
  private final RenameFunctionDelegate delegate;

  public RenameFunctionProcessor( final RenameFunctionInfo info ) {
    this.info = info;
    delegate = new RenameFunctionDelegate( info );
  }

  
  // interface methods of RefactoringProcessor
  ////////////////////////////////////////////

  public Object[] getElements() {
    // usually, this would be some element object in the object model on which
    // we work (e.g. a Java element if we were in the Java Model); in this case 
    // we have only the property name
    return new Object[] { info.getOldName() };
  }

  public String getIdentifier() {
    return getClass().getName();
  }

  public String getProcessorName() {
    return CoreTexts.renameFunctionProcessor_name;
  }

  public boolean isApplicable() throws CoreException {
    return true;
  }

  public RefactoringStatus checkInitialConditions( final IProgressMonitor pm ) {
    return delegate.checkInitialConditions();
  }

  public RefactoringStatus checkFinalConditions( 
            final IProgressMonitor pm,  final CheckConditionsContext context ) {
    return delegate.checkFinalConditions( pm, context );
  }

  public Change createChange( final IProgressMonitor pm ) {
    CompositeChange result = new CompositeChange( getProcessorName() ); 
    delegate.createChange( pm, result );
    return result;
  }

  public RefactoringParticipant[] loadParticipants( 
                               final RefactoringStatus status, 
                               final SharableParticipants sharedParticipants ) {
    // This would be the place to load the participants via the 
    // ParticipantManager and decide which of them are allowed to participate.
    return new RefactoringParticipant[ 0 ];
  }
}
