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
package org.modelica.mdt.internal.core;

import java.util.regex.Pattern;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.internal.omcproxy.InitializationException;

/**
 * @author Elmir Jagudin
 *
 */
abstract public class ModelicaElement extends PlatformObject 
	implements IModelicaElement 
{
	
	/* regexp pattern of a valid modelica class name */
	// see modelica specification page 9 (and perhaps some other pages as well)
	// http://www.modelica.org/documents/ModelicaSpec22.pdf
	private static String getPattern()
	{

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


	@Override
	public Object getAdapter(Class adapter)
	{
		if (IWorkbenchAdapter.class.equals(adapter))
		{
			return ModelicaCore.getWorkbenchAdapter();
		}
		else if (IResource.class.equals(adapter))
		{
			IResource resource = null;
			try
			{
				resource = getResource();
			}
			catch (InitializationException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (resource != null)
			{
				return resource;
			}
		}

		return super.getAdapter(adapter);
	}

	public IResource getResource() throws InitializationException
	{
		return null;
	}

	/**
	 * @return true if name is a valid modelica identifier name
	 */
	public static boolean isLegalIdentifierName(String name)
	{
		return classNamePattern.matcher(name).matches();
	}
}
