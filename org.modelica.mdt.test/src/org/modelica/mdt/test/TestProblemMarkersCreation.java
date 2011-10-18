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

package org.modelica.mdt.test;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.test.util.Utility;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * This test that problem markers are created when 
 * files with syntax errors are saved to the workspace.
 * 
 * @author Elmir Jagudin
 */
public class TestProblemMarkersCreation extends TestCase 
{

	/**
	 * This resource visitor traverses the complete resouce delta.
	 * It if find any of files that we are interested, it runs the
	 * appropriate tests and sets the flag that the test were run. 
	 */
	public class MarkerChecker implements IResourceDeltaVisitor {

		public boolean visit(IResourceDelta delta) throws CoreException
		{
			IResource res = delta.getResource();
			
			if (res.equals(problematicModel))
			{
				checkProblematicModel();
				problematicModelChecked = true;
			}
			else if (res.equals(unProblematicModel))
			{
				checkUnProblematicModel();
				unProblematicModelChecked = true;
			}

			return true;
		}

		
		/**
		 * check that there is no problems markers set on the unProblematicModel
		 * @throws CoreException
		 */
		private void checkUnProblematicModel() throws CoreException
		{
			IMarker[] markers = 
				unProblematicModel.findMarkers(IMarker.PROBLEM, true, 
					IResource.DEPTH_ONE);
			
			assertEquals("unexpected problem markers found on " +
					unProblematicModel.getName(),
					markers.length, 0);
			
		}
		/** 
		 * check that there is a problem marker starting
		 * on char 24 end ending on char 54 (line 2)
		 */
		private void checkProblematicModel() throws CoreException
		{
			boolean foundMarker = false;
			int start, end;
			
			for (IMarker marker :
				problematicModel.findMarkers(IMarker.PROBLEM, true, 
							IResource.DEPTH_ONE))
			{
			
				start = (Integer) marker.getAttribute(IMarker.CHAR_START);
				end = (Integer) marker.getAttribute(IMarker.CHAR_END);
				
				if (start == 24 && end == 74)
				{
					/* this is the marker we are looking for */
					foundMarker = true;

					/*
					 * make same sanity checks on the marker
					 */
					assertEquals(marker.getAttribute(IMarker.SEVERITY),
								IMarker.SEVERITY_ERROR);
					assertEquals(marker.getAttribute(IMarker.LINE_NUMBER),
							2);
					break;
				}
			}
			if (!foundMarker)
			{
				fail("problem marker not found on " + 
						problematicModel.getName());
			}
			
		}
	}

	private static final String PROJECT_NAME_1 = 
		TestProblemMarkersCreation.class.getName() + "1";
	
	private static IFile problematicModel;
	private static IFile unProblematicModel;
	
	/* 
	 * flags that tell that a resource change event have been
	 * received for the respective file and checks have been made on them 
	 */
	public boolean problematicModelChecked = false;
	public boolean unProblematicModelChecked = false;


	private IProject project = null;
	
	@Override
	protected void setUp() throws CoreException
	{
//		/*
//		 * setup project
//		 */
//		IModelicaProject mproj = 
//			ModelicaCore.getModelicaRoot().createProject(PROJECT_NAME_1);
//		Assert.assertNotNull("failed to create project", mproj);
//		
//		project = mproj.getWrappedProject();
//		project.open(null);
	}
	
	public void testProblemMarkers() throws CoreException
	{
		IResourceChangeListener resListener = new IResourceChangeListener()
		{
			public void resourceChanged(IResourceChangeEvent event) 
			{
				try 
				{
					event.getDelta().accept(new MarkerChecker());
				} 
				catch (CoreException e) 
				{
					fail("exception thrown while processing resource change"+e);
				}
			}
		};
		project.getWorkspace().
			addResourceChangeListener(resListener, 
					IResourceChangeEvent.POST_BUILD);
		
		/* create a model without any errors */
		unProblematicModel = project.getFile("unproblematic_model.mo");
		String contents = 
			"model unproblematic_model\n" +
			"\n" + 
			"end unproblematic_model;\n";
		unProblematicModel.create(Utility.getByteStream(contents), true, null);

		
		/* create a model without any errors */
		problematicModel = project.getFile("problematic_model.mo");
		contents = 
			"model problematic_model\n" +
			"aj_em_en_un_expected_tooken\n" +
			"\n" + 
			"end problematic_model;\n";
		problematicModel.create(Utility.getByteStream(contents), true, null);
		
		/*
		 * here we assume that resouce change events are processed in some
		 * other thread than this
		 * this assumption is out of thin air (only empirically checked)
		 * so there is possability that the below loop hangs, if it is
		 * ment to process resouce events on this thread
		 */
		
		while (!problematicModelChecked && !unProblematicModelChecked)
		{
			/*
			 * wait for builder to pick up our two new files
			 * and wait for the sanity check to be run in these two files
			 */
			Utility.sleep(this, 104);
		}
		
		project.getWorkspace().removeResourceChangeListener(resListener);

	}
}
