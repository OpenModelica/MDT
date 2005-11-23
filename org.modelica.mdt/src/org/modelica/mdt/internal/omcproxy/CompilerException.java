package org.modelica.mdt.internal.omcproxy;

/**
 * Supertype of all exceptions that can be encountered while communicating
 * with the modelica compiler.
 *  
 * @author Andreas Remar
 */
abstract public class CompilerException extends Exception
{
	private static final long serialVersionUID = -7881546855664735599L;

	public CompilerException(String error)
	{
		super(error);
	}
}