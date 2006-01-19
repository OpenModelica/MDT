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

package org.modelica.mdt;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;
import org.modelica.mdt.compiler.CommunicationException;
import org.modelica.mdt.compiler.CompilerException;
import org.modelica.mdt.compiler.CompilerInstantiationException;
import org.modelica.mdt.compiler.ConnectException;
import org.modelica.mdt.compiler.InvocationError;
import org.modelica.mdt.compiler.UnexpectedReplyException;
import org.modelica.mdt.core.CompilerProxy;

/**
 * Contains code for handling errors and other unexpected/undersireable
 * conditions.
 * 
 * Provides methods to log errors, warning and to display error to the
 * user.
 * 
 * @author Homer Simpson
 */
public class ErrorManager
{
	/* status codes used for logging */ 
	private static final int INTERNAL_ERROR = 0;
	private static final int INTERNAL_WARNING = 1;
	/* used to logg suspected bugs */
	private static final int ILLEGAL_INTERNAL_STATE = 2;

	/**
	 * error dialog showing logic variables
	 */
	private static boolean connectErrorShown = false;
	private static boolean communicationErrorShown = false;
	private static boolean instantiationErrorShown = false;
	private static long nextInvocationErrorShown = 0;
	private static long nextUnexpectedReplyErrorShown = 0;

	private static final long BUG_USER_WITH_ERRORS_INTERVAL = 60000;
	
	/**
	 * convinience wrapper method for loggin to plugin logger
	 */
	public static void logError(IStatus stat)
	{
		MdtPlugin.getDefault().getLog().log(stat);
	}

	/**
	 * Logs an internal error with the specified throwable
	 * 
	 * @param e the exception to be logged
	 */	
	public static void logError(Throwable e) 
	{
		logError(new Status(IStatus.ERROR, MdtPlugin.getSymbolicName(), 
				INTERNAL_ERROR,
				e.getMessage(), e));
	}
	
	/**
	 * Logs a runtime warning. A message that can be helpfull while
	 * investigating possible problems.
	 * 
	 * @param message warning message
	 */
	public static void logWarning(String message)
	{
		logError(new Status(IStatus.WARNING, MdtPlugin.getSymbolicName(), 
				INTERNAL_WARNING, message, null));
					
	}

	/**
	 * Display the appropriate error message to the user. The message
	 * is derived based on the subtype of the exception.
	 * 
	 * This method also handles the logic if it is appropriate do pop-up
	 * the error dialog box. For example, you don't want to display
	 * 20 'InvocationError' messages in a row to the user. 
	 * 
	 * However, errors are allways logged.
	 * 
	 * @param exception the exception thrown by the compiler
	 */
	public static void showCompilerError(CompilerException exception)
	{
		boolean showErrorDialog = false;
		
		/* construct the error message */
		String message = "unknow error";
		String compilerName = "unknow modelica compiler";
		
		try
		{
			compilerName = CompilerProxy.getCompilerName();
		}
		catch (CompilerInstantiationException e)
		{
			/* 
			 * oh well, not much to do 'bout this one,
			 * let's just name our compiler 'unknow'
			 */
		}
		
		String upgrade_your_software =  /* the standard remedy */
			"Try upgrading the " + MdtPlugin.PLUGIN_HUMAN_NAME + 
			" and/or the " + compilerName + 
			" to more recent versions.";
		
		String check_compiler_and_restart =
			" Make sure that " + compilerName + 
			" is properly installed and configured on your system and" +
			" restart Eclipse to retry connecting.";
			

		if (exception instanceof CommunicationException)
		{
			message = "Lost connection to the " + compilerName +
				check_compiler_and_restart;
			
			/* we only want to se connect error once */
			showErrorDialog = !communicationErrorShown;
			communicationErrorShown = true;
		}
		else if (exception instanceof ConnectException)
		{
			message = "Could not establish connection to the " + 
				compilerName + "." + check_compiler_and_restart;
			
			/* we only want to se connect error once */
			showErrorDialog = !connectErrorShown;
			connectErrorShown = true;
		}
		else if (exception instanceof CompilerInstantiationException)
		{
			CompilerInstantiationException cie 
				= (CompilerInstantiationException) exception;
			switch (((CompilerInstantiationException)exception).getProblemType())
			{
			case NO_COMPILERS_FOUND:
				message = "No plugin found that provides modelica " + 
					" compiler services. Please install a plugin that " + 
					" provides modelica compiler and try again";
				break;
			case MULTIPLE_COMPILERS_FOUND:
				message = "Multiple modelica compiler plugins available. " +
					"Please make sure only one compiler is enabled and " + 
					"try again. Following compiler plugins are found:\n";
				for (String pluginName : cie.getCompilerPlugins())
				{
					message += "'" + pluginName + "'\n"; 
				}
				break;
			case ERROR_CREATING_COMPILER:
				message = "Could not instantiate the modelica compiler. " +
					"The compiler plugin '" + cie.getCompilerPlugin() + 
					"' seems to be faulty. Try upgrading to unfaulty version.";
				/* 
				 * log the underlying exception also, to make enhance
				 * the fourthcoming debuggin experience 
				 */
				logError(cie.getCause());
				break;
			}
			
			/* we only want to se instantiation error once */
			showErrorDialog = !instantiationErrorShown;
			instantiationErrorShown = true;
		}
		else if (exception instanceof InvocationError)
		{
			message = compilerName + " unexpectedly reported error while " +
				((InvocationError)exception).getAction() + ". " +
				upgrade_your_software;
			
			long now = System.currentTimeMillis();
			if (now >= nextInvocationErrorShown)
			{
				/* it is time to bug user again */
				showErrorDialog = true;
				nextInvocationErrorShown = now + BUG_USER_WITH_ERRORS_INTERVAL;
			}
			else
			{
				/* 
				 * we have show this error message quite recently, 
				 * give the user a break
				 */
				showErrorDialog = false;
			}
		}
		else if (exception instanceof UnexpectedReplyException)
		{
			message = "Compatibility problems while communicating with the " +
				compilerName + ". " + upgrade_your_software +
				" You can also try to consult the plugins project homepage " +
				"for compatibility problems.";

			long now = System.currentTimeMillis();
			if (now >= nextUnexpectedReplyErrorShown)
			{
				/* it is time to bug user again */
				showErrorDialog = true;

				nextUnexpectedReplyErrorShown = 
					now + BUG_USER_WITH_ERRORS_INTERVAL;
			}
			else
			{
				/* 
				 * we have show this error message quite recently, 
				 * give the user a break
				 */
				showErrorDialog = false;
			}
		}
		else
		{
			/* unexpected exception type */
			ErrorManager.logBug(MdtPlugin.getSymbolicName(),
					"exception of unexpected type " + 
					exception.getClass().getName() +
					" encountered");
		}

		final IStatus status = 
			new Status(IStatus.ERROR, MdtPlugin.getSymbolicName(), 
				INTERNAL_ERROR, message, exception);
		
		/* log error */
		logError(status);
		
		/* display error to the user */
		if (showErrorDialog)
		{
			Display display = MdtPlugin.getDisplay();
			display.asyncExec(new Runnable()
			{
				public void run()
				{
					ErrorDialog.openError(MdtPlugin.getShell(),	
							"Error", null, status);
				}
			});
		}
	}

	/**
	 * Display Eclipse Core Error, that is condition when a CoreException
	 * is thrown.
	 * 
	 * @param exception the exception describing the error
	 */
	public static void showCoreError(final CoreException exception)
	{
		/* log error */
		logError(exception);
		
		Display display = MdtPlugin.getDisplay();
		display.asyncExec(new Runnable()
		{
			public void run()
			{
				ErrorDialog.openError(MdtPlugin.getShell(),	
						"Error", null, exception.getStatus());
			}
		});
	}

	/**
	 * Log illegal internal state that is a suspected bug.
	 * 
	 * @param pluginSymbolicName the symbolic name of the plugin where the state 
	 * is occuring
	 * @param message a descriptive message of the nature of illegalness of the 
	 * state, this description should be usable to fix the bug.
	 * 
	 */
	public static void logBug(String pluginSymbolicName, String message)
	{
		/*
		 * get the stack trace of the caller, 
		 * used to log the location of the bug 
		 */
		StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
		
		IStatus status = 
			new Status(IStatus.WARNING, pluginSymbolicName, 
				ILLEGAL_INTERNAL_STATE, 
				"illegal internal status (suspected bug) encountered at " +
				ste.getClassName() + "." + ste.getMethodName() +
				"(" + ste.getFileName() + ":" + ste.getLineNumber() + "). " +
				"'" + message + "'", null);
		
		MdtPlugin.getDefault().getLog().log(status);

	}
	
	/**
	 * Returns caller methods name including classname and source file name.
	 * This method is usefull for logging internal errors in conditions
	 * where an exception is not thrown, e.g.
	 * 
	 * ErrorManager.logWarning("something unexpected happend at " +
	 *                         ErrorManager.getCurrentMethod());
	 *                         
	 * will add following line (among others) to the logg:
	 * 
	 * 'something unexpected happend at foo.bar(foo.java)'
	 * 
	 * if called from foo.bar() method.
	 * 
	 * @return the callers method including classname and sourcefile
	 */
	public static String getCurrentMethod()
    {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
		
		return 
			ste.getClassName() + "." + ste.getMethodName() + 
				"(" + ste.getFileName() + ")";
	}
}
