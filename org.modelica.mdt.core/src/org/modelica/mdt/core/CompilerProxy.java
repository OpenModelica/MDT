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

import java.io.OutputStream;
import java.util.Collection;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.ElementInfo;
import org.modelica.mdt.core.compiler.IClassInfo;
import org.modelica.mdt.core.compiler.IModelicaCompiler;
import org.modelica.mdt.core.compiler.IParseResults;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.core.compiler.CompilerInstantiationException.ProblemType;
import org.modelica.mdt.core.preferences.PreferenceManager;
import org.modelica.mdt.internal.core.CorePlugin;

/**
 * This class provides one to one mapping to IModelicaCompiler interface,
 * however it hides the details of lazy loading and instantiating of the
 * compiler object via the extension points.
 *
 * All access to the modelica compiler should be made through this class.
 *
 */
//TODO move this to org.modelica.mdt.internal.core package !
public class CompilerProxy {
	private static IModelicaCompiler compiler = null;
	//final static ILock lock = Platform.getJobManager().newLock();	

	public static IModelicaCompiler getCompiler() throws CompilerInstantiationException {
		if (compiler == null) {
			compiler = loadCompiler();

			//			if (!getCompiler().isRunning())
			//			{
			//				// make an interactive job for running the compiler				
			//				Job compilerJob = new Job("Starting the OpenModelica Compiler")
			//				{
			//					public IStatus run(IProgressMonitor monitor)
			//					{
			//						try
			//						{
			//							lock.acquire(100);
			//
			//							monitor.beginTask("Trying sending 'getVersion()'", 10);
			//							while (!monitor.isCanceled())
			//							{
			//								Thread.sleep(100);
			//								// Access or modify data structure
			//								try
			//								{
			//									compiler.sendExpression("getVersion()", true);
			//									monitor.subTask("Sending 'getVersion()'"); monitor.worked(1);
			//								}
			//								catch(Exception e)
			//								{
			//									ErrorManager.logError(e);
			//								}
			//
			//								if (compiler.isRunning()) break;							
			//							}
			//						}
			//						catch(InterruptedException e)
			//						{
			//							ErrorManager.logError(e);
			//						} finally {
			//							lock.release();
			//						}													
			//						if (monitor.isCanceled()) 
			//							return Status.CANCEL_STATUS;
			//						monitor.done();
			//						return Status.OK_STATUS;
			//					}
			//				};
			//				compilerJob.setUser(true);
			//				compilerJob.setPriority(Job.INTERACTIVE);
			//				compilerJob.schedule();
			//			}	
		}

		return compiler;
	}

	/**
	 * Load the first best modelica compiler proxy contributed by 
	 * some other plugin via the org.modelica.mdt.core.compiler extension point.
	 * 
	 * @return
	 * @throws CompilerInstantiationException 
	 */
	private static IModelicaCompiler loadCompiler() throws CompilerInstantiationException {		
		if (!PreferenceManager.getStartOMC()) {
			return new NoCompiler();
		}

		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(CorePlugin.COMPILER_EXTENSION_ID);		

		IExtension[] extensions = extensionPoint.getExtensions();

		if (extensions.length == 0) {
			throw new CompilerInstantiationException(ProblemType.NO_COMPILERS_FOUND);
		}
		else if (extensions.length > 1) {
			/*
			 * compile a list of all plugins that define modelica compiler
			 * extension point
			 */
			Vector<String> compilerPlugins = new Vector<String>();

			for (IExtension ext : extensions) {
				compilerPlugins.add(ext.getNamespaceIdentifier());
			}

			throw new CompilerInstantiationException(compilerPlugins);
		}

		/* here we know that extensions array is one element long */
		IConfigurationElement[] configurationElements = extensions[0].getConfigurationElements();
		for (IConfigurationElement elm : configurationElements) {
			try {
				Object obj = elm.createExecutableExtension("class");
				if (obj instanceof IModelicaCompiler) {
					return (IModelicaCompiler)obj;
				}
			}
			catch (CoreException e) {
				String namespaceIdentifier = extensions[0].getNamespaceIdentifier();
				throw new CompilerInstantiationException(e, namespaceIdentifier);
			}
		}

		//TODO no class element found in the extension declaration 
		return null;
	}

	public synchronized static String getCompilerName() throws CompilerInstantiationException {
		IModelicaCompiler compiler = getCompiler();
		String compilerName = compiler.getCompilerName();
		return compilerName;
	}

	public synchronized static IParseResults loadSourceFile(IFile file) 
			throws ConnectException, UnexpectedReplyException, CompilerInstantiationException {
		IModelicaCompiler compiler = getCompiler();
		IParseResults parseResults = compiler.loadSourceFile(file);
		return parseResults;
	}

	public synchronized static List getClassNames(String className)
			throws ConnectException, UnexpectedReplyException, CompilerInstantiationException {
		IModelicaCompiler compiler = getCompiler();
		List classNames = compiler.getClassNames(className);
		return classNames;
	}

	public synchronized static Collection<ElementInfo> getElements(String className)
			throws ConnectException, InvocationError, UnexpectedReplyException, CompilerInstantiationException {
		IModelicaCompiler compiler = getCompiler();
		Collection<ElementInfo> elements = compiler.getElements(className);
		return elements;
	}

	public synchronized static IDefinitionLocation getClassLocation(String className)
			throws ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException {
		IModelicaCompiler compiler = getCompiler();
		IDefinitionLocation definitionLocation = compiler.getClassLocation(className);
		return definitionLocation;
	}

	public synchronized static IModelicaClass.Restriction getRestriction(String className)
			throws ConnectException, CompilerInstantiationException, UnexpectedReplyException {
		return getCompiler().getRestriction(className);
	}

	/**
	 * @return the top classes in the standard library
	 */
	public synchronized static String[] getStandardLibrary() throws ConnectException, CompilerInstantiationException {
		IModelicaCompiler compiler = getCompiler();
		String[] stdlib = compiler.getStandardLibrary();
		return stdlib;
	}

	public synchronized static IClassInfo getClassInfo(String className)
			throws CompilerInstantiationException, ConnectException, UnexpectedReplyException {
		return getCompiler().getClassInfo(className);
	}

	public synchronized static ICompilerResult getClassString(String className) 
			throws CompilerInstantiationException, ConnectException, UnexpectedReplyException {
		IModelicaCompiler compiler = getCompiler();
		ICompilerResult classString = compiler.getClassString(className);
		return classString;
	}	

	/**
	 * @author Adrian Pop
	 * @param command
	 * @return the result of the command execution
	 * @throws CompilerInstantiationException
	 * @throws ConnectException
	 * @throws UnexpectedReplyException
	 */
	public synchronized static ICompilerResult sendExpression(String command, boolean showInConsole)
			throws CompilerInstantiationException, ConnectException, UnexpectedReplyException {
		IModelicaCompiler compiler = getCompiler();
		ICompilerResult res = compiler.sendExpression(command, showInConsole);
		return res;
	}

	/**
	 * @author Adrian Pop
	 * @param command
	 * @return the result of the command execution
	 * @throws CompilerInstantiationException
	 * @throws ConnectException
	 * @throws UnexpectedReplyException
	 */
	public synchronized static boolean isRunning() 
			throws CompilerInstantiationException, ConnectException, UnexpectedReplyException {
		IModelicaCompiler compiler = getCompiler();
		boolean isRunning = compiler.isRunning();
		return isRunning;
	}

	/**
	 * @author Adrian Pop
	 * @param command
	 * @return the result of the command execution
	 * @throws CompilerInstantiationException
	 * @throws ConnectException
	 * @throws UnexpectedReplyException
	 */	
	public static void setConsoleOutputStream(OutputStream outputStream) throws CompilerInstantiationException {
		IModelicaCompiler compiler = getCompiler();
		compiler.setConsoleOutputStream(outputStream);
	}
}
