/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Linköpings universitet, Department of
 * Computer and Information Science, PELAB
 *
 * All rights reserved.
 *
 * (The new BSD license, see also
 * http://www.opensource.org/licenses/bsd-license.php)
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * * Neither the name of Linköpings universitet nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.modelica.mdt.core.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import org.modelica.mdt.internal.core.CorePlugin;

/**
 * This class manages the settings for the omc plugin (and at the moment the omc
 * and ui plugin as well, but that's just uggly)
 * 
 * The class provides two services, it sets the default settings and
 * gives an easy access to current settings via the get<Setting_Name> methods.
 * 
 * This class does not provides means for changing settings. To modify the 
 * settings the preference store object should be fetched from the core plugin.
 * 
 * Following settings are available:
 * 
 * DisplayCompErrors:
 *  if set to false then compatibility error dialogs will not be displayed when
 *  omc replys with something unexpected, the error will only be logged
 *  
 * UseStandardOmcPath:
 *  if set to true the OPENMODELICAHOME environment variable will be used to
 *  find the omc binary. if set to false the CustomOmcPath settings value will
 *  be used instead, se below.
 *  
 * CustomOmcPath:
 *  the value of this setting can be used as alternative path to find omc binary.
 *  used if UseStandardOmcPath settings is set to false, se above.
 */
public class PreferenceManager extends AbstractPreferenceInitializer 
{
	/* settings tags in the preference store */
	public static final String DISPLAY_COMPATIBILTY_ERRORS = 
		"DisplayCompatibilityErrors";
	public static final String USE_STANDARD_OMC_PATH = 
		"UseStandardOmcPath";
	public static final String CUSTOM_OMC_PATH = 
		"CustomOmcPath";	
	
	private static IPreferenceStore store = null;
	
	/**
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() 
	{
		/* 
		 * setup default settings 
		 */
		IPreferenceStore store = getStore();

		store.setDefault(DISPLAY_COMPATIBILTY_ERRORS, true);
		store.setDefault(USE_STANDARD_OMC_PATH, true);
		store.setDefault(CUSTOM_OMC_PATH, "");
	}
	
	private static IPreferenceStore getStore() 
	{
		if (store == null)
		{
			store = CorePlugin.getDefault().getPreferenceStore();
		}
		return store;
	}

	/**
	 * @return current setting for 'display compatibility errors'
	 */
	public static boolean getDisplayCompErrors()
	{
		return getStore().getBoolean(DISPLAY_COMPATIBILTY_ERRORS);
	}
	
	/**
	 * @return current settig for 'use standard omc path (that is $OPENMODELICAHOME)'
	 */
	public static boolean getUseStandardOmcPath()
	{
		return getStore().getBoolean(USE_STANDARD_OMC_PATH);
	}
	
	/**
	 * @return current settig for 'custom omc path' 
	 */
	public static String getCustomOmcPath()
	{
		return getStore().getString(CUSTOM_OMC_PATH);
	}
}
