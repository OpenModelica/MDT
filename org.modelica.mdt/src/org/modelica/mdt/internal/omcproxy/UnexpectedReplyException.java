package org.modelica.mdt.internal.omcproxy;

public class UnexpectedReplyException extends CompilerException
{
	private static final long serialVersionUID = 2514962826919739522L;

	public UnexpectedReplyException(String error)
	{
		super(error);
	}
}
