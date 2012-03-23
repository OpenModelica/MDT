/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Link�pings universitet, Department of
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
 * * Neither the name of Link�pings universitet nor the names of its
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

package org.modelica.mdt.core;

import java.net.URI;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;
import org.modelica.mdt.internal.core.CorePlugin;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.internal.core.ModelicaRoot;

public class ModelicaCore {
	public static String PLUGIN_ID = "ModelicaCorePlugin";
	private static ModelicaRoot modelicaRoot = null;

	/* 
	 * regexp pattern of a valid modelica class name,
	 * see modelica specification page 9 (and perhaps some other pages as well)
	 * for the formal definition
	 * http://www.modelica.org/documents/ModelicaSpec22.pdf
	 */
	private static String getPattern() {
		String IDENT = "([_a-zA-Z]\\w*)";

		/* \p{Graph} are all printable characters in the POSIX standard
		 * Q-CHAR = [\p{Graph}&&[^'\]] */
		String Q_CHAR = "[\\p{Graph}&&[^'\\\\]]";

		/* S-ESCAPE = \'|\"|\?|\\|\a|\b|\f|\n|\r|\t|\v */
		String S_ESCAPE = "((\\\\')|(\\\\\")|(\\\\\\?)|(\\\\\\\\)|(\\\\a)"
				+"|(\\\\b)|(\\\\f)|(\\\\n)|(\\\\r)|(\\\\t)|(\\\\v))";

		/* Q-IDENT = "'" (Q-CHAR | S-ESCAPE) {Q-CHAR | S-ESCAPE} "'" */
		String Q_IDENT = "('(" + Q_CHAR + "|" + S_ESCAPE + ")+')";

		String pattern = IDENT + "|" + Q_IDENT;

		return pattern;
	}

	private static Pattern classNamePattern = Pattern.compile(getPattern());

	public static IModelicaRoot getModelicaRoot() {
		return modelicaRoot;
	}

	public static void start() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IPathVariableManager pathMan = workspace.getPathVariableManager();
		String name = "OPENMODELICALIBRARY";
		String path = System.getenv(name);
		boolean error = false;

		if (path != null) {
			java.io.File openModelicaLib = new java.io.File(path);

			if (!openModelicaLib.exists() || !openModelicaLib.isDirectory()) {
				error = true;
			}
			else {
				try {
					URI value = openModelicaLib.toURI();
					IStatus validNameStatus = pathMan.validateName(name);
					IStatus validValueStatus = pathMan.validateValue(value);

					if (validNameStatus.isOK()) {
						// There is a bug in Eclipse versions < 3.8 where IPathVariableManager.validateValue(URI)
						// returns null regardless of its input. We simply cannot validate the value URI
						// in those versions.
						if (validValueStatus == null || validValueStatus.isOK()) {
							pathMan.setURIValue(name, value);
						}
						else {
							// if validValueStatus != null && !validValueStatus.isOK()
							error = true;
						}
					}
					else {
						// if !validNameStatus.isOK()
						error = true;
					}
				}
				catch (CoreException e) {
					ErrorManager.logError(e);
					error = true;
				}
			}
		}
		else {
			// if path == null
			error = true;
		}

		if (error) {
			Display display = CorePlugin.getDisplay();
			display.asyncExec(new Runnable() {
				@Override
				public void run() {
					ErrorDialog.openError(CorePlugin.getShell(),"Error", null,
							new Status(IStatus.ERROR, "org.modelica.mdt.core", IStatus.OK, 
									"OPENMODELICALIBRARY environment variable is not set or has an invalid value.!\n" +
											"Please exit Eclipse and set the variable properly if you want to be able " +
											"to browse the Modelica Library", null));
				}
			});
		}

		modelicaRoot = new ModelicaRoot();
		modelicaRoot.start();
	}

	public static void stop() {
		modelicaRoot.stop();
		modelicaRoot = null;
	}

	/**
	 * @return true if name is a valid modelica identifier name
	 */
	public static boolean isLegalIdentifierName(String name) {
		return ModelicaCore.classNamePattern.matcher(name).matches();
	}
}
