// Copyright (c) 2005 by Leif Frenzel. All rights reserved.
// See http://leiffrenzel.de
package org.modelica.mdt.ui.refactoring.core;

import org.eclipse.osgi.util.NLS;

/** <p>provides internationalized messages Strings from the coretexts 
  * resource bundle.</p>
  *
  * @author Leif Frenzel
  */
public class CoreTexts extends NLS {
  
  private static final String BUNDLE_NAME 
    = "org.modelica.mdt.ui.refactoring.core.coretexts"; //$NON-NLS-1$
  
  static {
    NLS.initializeMessages( BUNDLE_NAME, CoreTexts.class );
  }

  // message fields
  public static String renameFunctionProcessor_name;
  public static String renameFunctionDelegate_noSourceFile;
  public static String renameFunctionDelegate_roFile;
  public static String renameFunctionDelegate_noPropertyKey;
  public static String renameFunctionDelegate_collectingChanges;
  public static String renameFunctionDelegate_checking;
  public static String renameFunctionDelegate_propNotFound;
}