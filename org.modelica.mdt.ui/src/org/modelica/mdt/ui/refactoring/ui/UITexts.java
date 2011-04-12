// Copyright (c) 2005 by Leif Frenzel. All rights reserved.
// See http://leiffrenzel.de
package org.modelica.mdt.ui.refactoring.ui;

import org.eclipse.osgi.util.NLS;

/** <p>provides internationalized String messages for the UI.</p> 
  * 
  * @author Leif Frenzel
  */
public class UITexts {

  private static final String BUNDLE_NAME 
    = "org.modelica.mdt.ui.refactoring.ui.uitexts"; //$NON-NLS-1$

  static {
    NLS.initializeMessages( BUNDLE_NAME, UITexts.class );
  }

  // message fields
  public static String renameFunction_refuseDlg_title;
  public static String renameFunction_refuseDlg_message;
  public static String renameFunctionInputPage_lblNewName; 
}
