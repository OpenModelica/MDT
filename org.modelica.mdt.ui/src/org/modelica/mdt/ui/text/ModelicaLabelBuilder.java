package org.modelica.mdt.ui.text;

import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IParameter;
import org.modelica.mdt.core.ISignature;
import org.modelica.mdt.core.IModelicaClass.Restriction;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

public class ModelicaLabelBuilder {

	/**
	 * Constructs a textual representation of a functions signature.
	 *  
	 * @param modelicaClass the class that will have its input and output
	 * parameters displayed
	 * @return the constructed <code>String</code>
	 */
	public static String constructSignature(IModelicaClass modelicaClass)
		throws ConnectException, InvocationError, UnexpectedReplyException, CompilerInstantiationException, CoreException {
		String proposal = modelicaClass.getElementName();

		return proposal;
	}
	
	/**
	 * Constructs a textual representation of a functions signature.
	 *  
	 * @param modelicaClass the class that will have its input and output
	 * parameters displayed
	 * @return the constructed <code>String</code>
	 */
	public static String constructProposalSignature(IModelicaElement modelicaElement)
		throws ConnectException, InvocationError, UnexpectedReplyException, CompilerInstantiationException, CoreException {
		if (!(modelicaElement instanceof IModelicaClass)) {
			return modelicaElement.getElementName();
		}
		
		IModelicaClass modelicaClass = (IModelicaClass)modelicaElement;
		
		IModelicaClass.Restriction r = modelicaClass.getRestriction();		
		
		ISignature signature = modelicaClass.getSignature();
		if(signature == null)
		{
			return modelicaClass.getElementName();
		}
		
		/*
		 * If a function has several return values it will look like this:
		 * 
		 *  foo(Real, Real) => (Real, Integer)
		 *  
		 * if a function only has one return value it will look like this:
		 * 
		 *  Real bar(Integer a)
		 */
		
		String proposal;
						
		proposal = modelicaClass.getElementName();
				
		IParameter[] inputs = signature.getInputs();
		if(inputs.length >= 1)
		{
			int i = 0;
			proposal += "(";
			for(;i < inputs.length - 1;i++)
			{
				proposal += inputs[i].getName() + ", ";
			}
			proposal += inputs[i].getName();
			proposal += ")";
		}
		if ((r == Restriction.RECORD || r == Restriction.FUNCTION) && inputs.length == 0)
		{
			proposal += "()";
		}
		
		return proposal;
	}	
	
	/**
	 * Constructs a textual representation of a functions signature.
	 *  
	 * @param modelicaClass the class that will have its input and output
	 * parameters displayed
	 * @return the constructed <code>String</code>
	 */
	public static String constructContextSignature(IModelicaElement modelicaElement)
		throws ConnectException, InvocationError, UnexpectedReplyException,
			CompilerInstantiationException, CoreException
	{		
		if (!(modelicaElement instanceof IModelicaClass)) 
			return modelicaElement.getElementName();
		
		IModelicaClass modelicaClass = (IModelicaClass)modelicaElement;
		
		IModelicaClass.Restriction r = modelicaClass.getRestriction();		
		
		ISignature signature = modelicaClass.getSignature();
		if(signature == null)
		{
			return "";
		}
		
		/*
		 * If a function has several return values it will look like this:
		 * 
		 *  (Real, Integer) foo(Real a, Real b)
		 *  
		 * if a function only has one return value it will look like this:
		 * 
		 *  Real bar(Integer a)
		 */
		
		String proposal;
		
		IParameter[] outputs = signature.getOutputs();

		if(outputs.length >= 2)
		{
			proposal = "(";
			
			int i = 0;
			for(;i < outputs.length - 1;i++)
			{
				proposal += outputs[i].getType() + ", ";
			}
			
			proposal += outputs[i].getType() + ") ";
		}
		else if(outputs.length == 1)
		{
			proposal = outputs[0].getType() + " ";
		}
		else
		{
			proposal = "";
		}

		proposal += modelicaClass.getElementName();
		
		IParameter[] inputs = signature.getInputs();
		if(inputs.length >= 1)
		{
			int i = 0;
			proposal += "(";
			for(;i < inputs.length - 1;i++)
			{
				proposal += inputs[i].getType() + " " + inputs[i].getName() + ", ";
			}
			proposal += inputs[i].getType() + " " + inputs[i].getName();
			proposal += ")";
		}
		if ((r == Restriction.RECORD || r == Restriction.FUNCTION) && inputs.length == 0)
		{
			proposal += "()";
		}
		
		return proposal;
	}	
	
}
