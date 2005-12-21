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

package org.modelica.mdt.core;

import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.compiler.CompilerInstantiationException;
import org.modelica.mdt.compiler.ConnectException;
import org.modelica.mdt.compiler.IElementLocation;
import org.modelica.mdt.compiler.IModelicaCompiler;
import org.modelica.mdt.compiler.IParseResults;
import org.modelica.mdt.compiler.InvocationError;
import org.modelica.mdt.compiler.UnexpectedReplyException;
import org.modelica.mdt.compiler.CompilerInstantiationException.ProblemType;


/**
 * This class provides one to one mapping to IModelicaComiler interface,
 * however it hides the details of lazy loading and instansiating of the
 * compiler object via the extension points.
 * 
 * All access to the modelica compiler should be made through this class.
 * 
 */
public class CompilerProxy
{
	private static IModelicaCompiler compiler = null;
	
	private static IModelicaCompiler getCompiler() 
		throws CompilerInstantiationException
	{
		if (compiler == null)
		{
			compiler = loadCompiler();
		}
		
		return compiler;
	}
	
	/**
	 * Load the first best modelica compiler proxy contributed by 
	 * some other plugin via the org.modelica.mdt.compiler extension point.
	 * 
	 * @return
	 * @throws CompilerInstantiationException 
	 */
	private static IModelicaCompiler loadCompiler() 
		throws CompilerInstantiationException
	{		
		IExtensionPoint extensionPoint = 
			Platform.getExtensionRegistry().getExtensionPoint(MdtPlugin.COMPILER_EXTENSION_ID);		
		
		IExtension[] extensions = extensionPoint.getExtensions();
		
		if (extensions.length == 0)
		{
			throw new CompilerInstantiationException(ProblemType.NO_COMPILERS_FOUND);
		}
		else if (extensions.length > 1)
		{
			/*
			 * compile a list of all plugins that define modelica compiler
			 * extension point
			 */
			Vector<String> compilerPlugins = new Vector<String>();
			
			for (IExtension ext : extensions)
			{
				compilerPlugins.add(ext.getNamespace());
			}

			throw new 
				CompilerInstantiationException(compilerPlugins);
		}

		/* here we know that extensions array is one element long */
		
		for (IConfigurationElement elm : extensions[0].getConfigurationElements())
		{
			try
			{
				Object obj = elm.createExecutableExtension("class");
				if (obj instanceof IModelicaCompiler)
				{
					return (IModelicaCompiler)obj;
				}
			}
			catch (CoreException e)
			{
				throw new CompilerInstantiationException(e,
						extensions[0].getNamespace());
			}
		}
	
		//TODO no class element found in the extension declaration 
		return null;
	}
	
	public static String getCompilerName() 
		throws CompilerInstantiationException
	{
		return getCompiler().getCompileName();
	}

	// TODO rename to loadFile or maybe loadSourceFile ?
	public static IParseResults loadFileInteractive(IFile file) 
		throws ConnectException, UnexpectedReplyException, 
			CompilerInstantiationException
	{
		return getCompiler().loadFileInteractive(file);
	}
	
	public static Vector<Object> getClassNames(String className)
		throws ConnectException, UnexpectedReplyException,
			CompilerInstantiationException
	{
		return getCompiler().getClassNames(className);
	}
	
	// TODO this should have a better name
	public static Vector<Object> getElementsInfo(String className)
		throws ConnectException, InvocationError, UnexpectedReplyException, 
			CompilerInstantiationException
	{
		return getCompiler().getElementsInfo(className);
	}

	public static IElementLocation getElementLocation(String className)
		throws ConnectException, UnexpectedReplyException, InvocationError,
			CompilerInstantiationException 
	{
		return getCompiler().getElementLocation(className);
	}
	
	public static IModelicaClass.Type getRestrictionType(String className)
		throws ConnectException, CompilerInstantiationException
	{
		return getCompiler().getRestrictionType(className);
	}
	
	public static void loadSystemLibrary()
		throws ConnectException, CompilerInstantiationException
	{
		getCompiler().loadSystemLibrary();		
	}
}