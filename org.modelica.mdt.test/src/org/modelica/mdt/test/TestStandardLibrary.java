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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IModelicaImport.Type;
import org.modelica.mdt.core.IModelicaRoot;
import org.modelica.mdt.core.IStandardLibrary;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.InnerClass;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

/**
 * Do some sanity checks regarding the standard library
 */
public class TestStandardLibrary {
	private IStandardLibrary standardLibrary;

	@org.junit.Before
	public void setUp() throws BundleException {
		// the adapter factory that handles standard library object
		// is installed by the org.modelica.mdt.ui plugin,
		// make sure it is loaded before running tests 
		Bundle bundle = Platform.getBundle("org.modelica.mdt.ui");		
		bundle.start();

		IModelicaRoot modelicaRoot = ModelicaCore.getModelicaRoot();
		standardLibrary = modelicaRoot.getStandardLibrary(null);
	}

	/**
	 * check that we can convert an IStandardLibrary object, return
	 * by modelica root, to workbench object, etc via the IAdapter
	 * mechanism.
	 * 
	 * This is used by the UI layer when displaying modelica standard library
	 * in for example modelica projects view.
	 */
	@org.junit.Test
	public void testStandardLibraryAdapter() {
		// check if standard library object is adaptable
		boolean instanceOfIAdaptable = standardLibrary instanceof IAdaptable;
		assertTrue("standard library object must be adaptable", instanceOfIAdaptable);

		// check if we can convert standard library to workbench adapter
		IWorkbenchAdapter wbAdapter = 
				(IWorkbenchAdapter)standardLibrary.getAdapter(IWorkbenchAdapter.class);

		assertNotNull("could not fetch workbench adapter", wbAdapter);

		String label = wbAdapter.getLabel(standardLibrary);
		assertTrue("wrong label", label.startsWith("Libraries: "));
		assertTrue("wrong label", label.endsWith("omlibrary"));
		ImageDescriptor imageDescriptor = wbAdapter.getImageDescriptor(standardLibrary);
		assertNotNull("no image provided", imageDescriptor);
	}

	/**
	 * Do some integrity tests on classes/packages from the standard library.
	 */
	@org.junit.Test
	public void testStandardLibraryElements()
			throws ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException, CoreException {
		// Fetch the Modelica package from the standard library.
		Collection<IModelicaClass> packages = standardLibrary.getPackages();
		InnerClass modelica = null;

		String desiredPackageName = "Modelica";

		for (IModelicaClass modelicaClass : packages) {
			String elementName = modelicaClass.getElementName();

			if (elementName.equals(desiredPackageName)) {
				modelica = (InnerClass)modelicaClass;
				break;
			}
		}
		assertNotNull("Could not find standard library package \"" + desiredPackageName + "\".", modelica);

		// do checks on Modelica package
		IResource modelicaResource = modelica.getResource();
		assertNull("Standard library should be defined outside of workspace.", modelicaResource);
		String modelicaFilePath = modelica.getFilePath();
		boolean modelicaFilePathEmpty = modelicaFilePath.equals("");
		assertFalse("Empty path to the source file.", modelicaFilePathEmpty);
		//IRegion reg = modelica.getLocation();
		//assertTrue("negative element region can't be", reg.getOffset() >= 0);
		//assertTrue("elements length must be positive", reg.getLength() > 0);

		// do checks on Modelica.Blocks and Modelica.Constants packages
		InnerClass blocks = null;
		InnerClass constants = null;
		Collection<IModelicaElement> modelicaChildren = modelica.getChildren();
		for (IModelicaElement el : modelicaChildren) {
			String name = el.getElementName();

			if (name.equals("Blocks")) {
				blocks = (InnerClass)el;
			}
			else if (name.equals("Constants")) {
				constants = (InnerClass)el;
			}
		} 

		// check Modelica.Blocks
		assertNotNull("Could not find package Modelica.Blocks in standard library.", blocks);
		IResource blocksResource = blocks.getResource();
		assertNull("Standard library should be defined outside of workspace.", blocksResource);
		String blocksFilePath = blocks.getFilePath();
		boolean blocksFilePathEmpty = blocksFilePath.equals("");
		assertFalse("Empty path to the source file.", blocksFilePathEmpty);
		//reg = blocks.getLocation();
		//assertTrue("negative element region can't be", reg.getOffset() >= 0);
		//assertTrue("elements length must be positive", reg.getLength() > 0);

		// check Modelica.Blocks imports
		boolean foundSIimport = false;
		Collection<IModelicaImport> blocksImports = blocks.getImports();
		for (IModelicaImport imp : blocksImports) {
			Type type = imp.getType();
			switch (type) {
			case RENAMING:
				String alias = imp.getAlias();
				if (alias.equals("SI")) {
					foundSIimport = true;
				}
				break;
				// ignore all other types of imports
			}
		}
		assertTrue("Could not find the representation of 'import SI = Modelica.SIunits;' statement", foundSIimport); 

		// check Modelica.Constants
		assertNotNull("Could not find package Modelica.Constants in standard library.", constants);
		IResource constantsResource = constants.getResource();
		assertNull("Standard library should be defined outside of workspace.", constantsResource);
		String constantsFilePath = constants.getFilePath();
		boolean constantsFilePathEmpty = constantsFilePath.equals("");
		assertFalse("Empty path to the source file", constantsFilePathEmpty);
		//reg = constants.getLocation();
		//assertTrue("negative element region can't be", reg.getOffset() >= 0);
		//assertTrue("elements length must be positive", reg.getLength() > 0);

		// do checks on Modelica.Constants components
		Collection<IModelicaElement> constantsChildren = constants.getChildren();
		IModelicaComponent pi = null;
		IModelicaComponent D2R = null;
		for (IModelicaElement el : constantsChildren) {
			String name = el.getElementName();

			if (name.equals("pi")) {
				pi = (IModelicaComponent)el;
			}
			else if (name.equals("D2R")) {
				D2R = (IModelicaComponent)el;
			}
		}

		// check Modelica.Constants.pi
		assertNotNull("Could not find package Modelica.Constants.pi in standard library.", pi);
		IModelicaComponent.Visibility piVisibility = pi.getVisibility();
		assertEquals("pi must have public visibility", IModelicaComponent.Visibility.PUBLIC, piVisibility);
		IResource piResource = pi.getResource();
		assertNull("Standard library should be defined outside of workspace.", piResource);
		String piFilePath = pi.getFilePath();
		boolean piFilePathEmpty = piFilePath.equals("");
		assertFalse("Empty path to the source file.", piFilePathEmpty);
		//reg = pi.getLocation();
		//assertTrue("negative element region can't be", reg.getOffset() >= 0);
		//assertTrue("elements length must be positive", reg.getLength() > 0);

		// check Modelica.Constants.D2R
		assertNotNull("Could not find package Modelica.Constants.pi in standard library.", D2R);
		IModelicaComponent.Visibility d2rVisibility = D2R.getVisibility();
		assertEquals("D2R must have public visibility", IModelicaComponent.Visibility.PUBLIC, d2rVisibility);
		IResource d2rResource = D2R.getResource();
		assertNull("Standard library should be defined outside of workspace.", d2rResource);
		String d2rFilePath = D2R.getFilePath();
		boolean d2rFilePathEmpty = d2rFilePath.equals("");
		assertFalse("Empty path to the source file.", d2rFilePathEmpty);
		//reg = pi.getLocation();
		//assertTrue("negative element region can't be", reg.getOffset() >= 0);
		//assertTrue("elements length must be positive", reg.getLength() > 0);

		// check Modelica.Constants imports
		foundSIimport = false;
		boolean foundNonSIimport = false;

		Collection<IModelicaImport> constantsImports = constants.getImports();
		for (IModelicaImport imp : constantsImports) {
			Type type = imp.getType();
			switch (type) {
			case RENAMING:
				String alias = imp.getAlias();
				if (alias.equals("SI")) {
					foundSIimport = true;
				}
				else if (alias.equals("NonSI")) {
					foundNonSIimport = true;
				}
				break;
				// ignore all other types of imports
			}
		}

		assertTrue("Could not find the representation of 'import SI = Modelica.SIunits;' statement", foundSIimport); 
		assertTrue("Could not find the representation of 'import NonSI = Modelica.SIunits.Conversions.NonSIunits;' statement", foundNonSIimport); 
	}
}
