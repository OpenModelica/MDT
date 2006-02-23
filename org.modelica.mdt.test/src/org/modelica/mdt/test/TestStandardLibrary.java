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

package org.modelica.mdt.test;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IStandardLibrary;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.InnerClass;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import junit.framework.TestCase;

/**
 * Do some sanity checks regarding the standard library
 */
public class TestStandardLibrary extends TestCase 
{
	/**
	 * check that we can convert an IStandardLibrary object, return
	 * by modelica root, to workbench object, etc via the IAdapter
	 * mechanism.
	 * 
	 * This is used by the UI layer when displaying modelica standard library
	 * in for example modelica projects view.
	 */
	public void testStandardLibraryAdapter() throws BundleException
	{
		/*
		 * the adapter factory that handles standard library object
		 * is installed by the org.modelica.mdt.ui plugin,
		 * make sure it is loaded before running tests 
		 */
		Bundle bundle = Platform.getBundle("org.modelica.mdt.ui");		
		bundle.start();
		
		IStandardLibrary standardLibrary = 
			ModelicaCore.getModelicaRoot().getStandardLibrary();

		/* check if standard library object is adaptable */
		assertTrue("standard library object must be adaptable",
				(standardLibrary instanceof IAdaptable));
		
		/* check if we can convert standard library to workbench adapter */
		IWorkbenchAdapter wbAdapter = 
			(IWorkbenchAdapter)standardLibrary.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);
		assertEquals("wrong label", "Standard Library",
				wbAdapter.getLabel(standardLibrary));
		assertNotNull("no image provided", 
				wbAdapter.getImageDescriptor(standardLibrary));

	}
	
	
	/**
	 * Do some integrity tests on classes/packages from the standard library.
	 */
	public void testStandardLibraryElements()
		throws ConnectException, UnexpectedReplyException, 
			InvocationError, CompilerInstantiationException, CoreException
	{
						
		/* fetch teh Modelica package from the standard library */		
		IStandardLibrary standardLibrary = 
			ModelicaCore.getModelicaRoot().getStandardLibrary();
		
		InnerClass modelica = null;
		
		for (IModelicaClass clazz : standardLibrary.getPackages())
		{
			if (clazz.getElementName().equals("Modelica"))
			{
				modelica = (InnerClass)clazz;
				break;
			}
		}

		assertNotNull("could not found standard library package 'Modelica'",
				modelica);

		
		/*
		 * do checks on Modelica package
		 */
		assertNull("standard library should be defined outside of workspace",
				modelica.getResource());
		assertFalse("empty path to the source file", 
				modelica.getFilePath().equals(""));
		IRegion reg = modelica.getLocation();
		assertTrue("negative element region can't be", reg.getOffset() >= 0);
		assertTrue("elements length must be positive", reg.getLength() > 0);


		/*
		 * do checks on Modelica.Blocks and Modelica.Constants packages
		 */

		InnerClass blocks = null;
		InnerClass constants = null;
 
		for (IModelicaElement el : modelica.getChildren())
		{
			String name = el.getElementName();
			
			if (name.equals("Blocks"))
			{
				blocks = (InnerClass)el;
			}
			else if (name.equals("Constants"))
			{
				constants = (InnerClass)el;
			}
		} 
		
		/* check Modelica.Blocks */
		assertNotNull("could not find package Modelica.Blocks in standard" +
				"library ", blocks);
		assertNull("standard library should be defined outside of workspace",
				blocks.getResource());
		assertFalse("empty path to the source file", 
				blocks.getFilePath().equals(""));
		reg = blocks.getLocation();
		assertTrue("negative element region can't be", reg.getOffset() >= 0);
		assertTrue("elements length must be positive", reg.getLength() > 0);
		
		/* check Modelica.Blocks imports */
		boolean foundSIimport = false;

		for (IModelicaImport imp : blocks.getImports())
		{
			switch (imp.getType())
			{
			case RENAMING:
				if (imp.getAlias().equals("SI"))
				{
					foundSIimport = true;
				}
				break;
				/* ignore all other types of imports */
			}
		}
		
		assertTrue("could not find the representation of" +
				" 'import SI = Modelica.SIunits;' statment", foundSIimport); 
		
		/* check Modelica.Constants */
		assertNotNull("could not find package Modelica.Constants in standard" +
				"library ", constants);
		assertNull("standard library should be defined outside of workspace",
				constants.getResource());
		assertFalse("empty path to the source file", 
				constants.getFilePath().equals(""));
		reg = constants.getLocation();
		assertTrue("negative element region can't be", reg.getOffset() >= 0);
		assertTrue("elements length must be positive", reg.getLength() > 0);

		IModelicaComponent pi = null;
		IModelicaComponent D2R = null;
		
		/*
		 * do checks on Modelica.Constants components
		 */
		for (IModelicaElement el : constants.getChildren())
		{
			String name = el.getElementName();
			
			if (name.equals("pi"))
			{
				pi = (IModelicaComponent)el;
			}
			else if (name.equals("D2R"))
			{
				D2R = (IModelicaComponent)el;
			}
		}

		/* check Modelica.Constants.pi */
		assertNotNull("could not find package Modelica.Constants.pi in standard" 
				+ "library ", pi);
		assertEquals("pi must have public visibility", 
				IModelicaComponent.Visibility.PUBLIC,
				pi.getVisbility());
		assertNull("standard library should be defined outside of workspace",
				pi.getResource());
		assertFalse("empty path to the source file", 
				pi.getFilePath().equals(""));
		reg = pi.getLocation();
		assertTrue("negative element region can't be", reg.getOffset() >= 0);
		assertTrue("elements length must be positive", reg.getLength() > 0);

		/* check Modelica.Constants.D2R */
		assertNotNull("could not find package Modelica.Constants.pi in standard" 
				+ "library ", D2R);
		assertEquals("pi must have public visibility", 
				IModelicaComponent.Visibility.PUBLIC,
				D2R.getVisbility());
		assertNull("standard library should be defined outside of workspace",
				D2R.getResource());
		assertFalse("empty path to the source file", 
				D2R.getFilePath().equals(""));
		reg = pi.getLocation();
		assertTrue("negative element region can't be", reg.getOffset() >= 0);
		assertTrue("elements length must be positive", reg.getLength() > 0);
		
		/* check Modelica.Constants imports */
		foundSIimport = false;
		boolean foundNonSIimport = false;

		for (IModelicaImport imp : constants.getImports())
		{
			switch (imp.getType())
			{
			case RENAMING:
				if (imp.getAlias().equals("SI"))
				{
					foundSIimport = true;
				}
				else if (imp.getAlias().equals("NonSI"))
				{
					foundNonSIimport = true;
				}
				break;
				/* ignore all other types of imports */
			}
		}
		
		assertTrue("could not find the representation of" +
				" 'import SI = Modelica.SIunits;' statment", foundSIimport); 
		assertTrue("could not find the representation of" +
				" import NonSI = Modelica.SIunits.Conversions.NonSIunits;' " +
				"statment", foundNonSIimport); 
	}
}
