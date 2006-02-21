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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.internal.core.CorePlugin;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;
import org.modelica.mdt.ui.editor.ModelicaCompletionProcessor;
import org.modelica.mdt.ui.editor.ModelicaElementEditorInput;

import junit.framework.TestCase;

/**
 * Test various methods in org.modelica.ui.editor.ModelicaCompletionProcessor
 */
public class TestModelicaCompletionProcessor extends TestCase 
{
	
	private static final String FILE_NAME = "import_rich_model.mo";
	
	/* test subject */
	private ModelicaCompletionProcessor compProc;
	
	@Override
	//TODO replace me with the line below
	public void setUp() throws Exception
	//protected void setUp() throws Exception 
	{
		Area51Projects.createProjects();
		
		/* 
		 * open file import_rich_model.mo in an editor 
		 */
		IModelicaProject proj = 
			Utility.getProject(Area51Projects.MODELICA_PROJECT_NAME);
		
		IModelicaElement file = 
			proj.findElement(new Path(FILE_NAME));
		assertNotNull("could not find " + FILE_NAME, file);
		assertTrue("expected a source file ", 
				(file instanceof IModelicaSourceFile));
		
		IEditorPart editor = 
			openInEditor(new ModelicaElementEditorInput(file));
		
		assertNotNull(editor);
		
		compProc = new ModelicaCompletionProcessor(editor);
		
		/*
		 * setup the document to test getPrefix() on
		 */
	}
	
	private static IEditorPart openInEditor(IEditorInput input)
		throws PartInitException 
	{
		if (input != null) 
		{
			IWorkbenchPage p = CorePlugin.getActivePage();
			if (p != null) 
			{
				IEditorPart editorPart = 
						p.openEditor(input, "org.modelica.mdt.editor", true);
	
				return editorPart;
			}
		}
		return null;
	}

	
	public void testPrefix()
	{
		/* this is used to test getPrefix() method */
		String docContents = 
			"hej hopp jaa\n" + // 13 characters
			"asdf im(prt ;da"; // 15 characters

		Document prefixDoc = new Document(docContents);

		String prefix;
		
		/* try to fetch prefix from the very begining of the document */
		prefix = callGetPrefix(compProc, prefixDoc, 0);
		assertEquals("unexpected prefix", "", prefix);

		prefix = callGetPrefix(compProc, prefixDoc, 8);
		assertEquals("unexpected prefix", "hopp", prefix);
		
		prefix = callGetPrefix(compProc, prefixDoc, 1);
		assertEquals("unexpected prefix", "h", prefix);

		prefix = callGetPrefix(compProc, prefixDoc, 5);
		assertEquals("unexpected prefix", "h", prefix);
		
		prefix = callGetPrefix(compProc, prefixDoc, 3);
		assertEquals("unexpected prefix", "hej", prefix);
		
		prefix = callGetPrefix(compProc, prefixDoc, 7);
		assertEquals("unexpected prefix", "hop", prefix);
		
		prefix = callGetPrefix(compProc, prefixDoc, 11);
		assertEquals("unexpected prefix", "ja", prefix);
		
		prefix = callGetPrefix(compProc, prefixDoc, 12);
		assertEquals("unexpected prefix", "jaa", prefix);
		
		prefix = callGetPrefix(compProc, prefixDoc, 17);
		assertEquals("unexpected prefix", "asdf", prefix);
		
		prefix = callGetPrefix(compProc, prefixDoc, 24);
		assertEquals("unexpected prefix", "prt", prefix);

		prefix = callGetPrefix(compProc, prefixDoc, 22);
		assertEquals("unexpected prefix", "p", prefix);
				
		prefix = callGetPrefix(compProc, prefixDoc, 28);
		assertEquals("unexpected prefix", "da", prefix);
		
		prefix = callGetPrefix(compProc, prefixDoc, 25);
		assertEquals("unexpected prefix", "", prefix);

		prefix = callGetPrefix(compProc, prefixDoc, 27);
		assertEquals("unexpected prefix", "d", prefix);

	}

	private String callGetPrefix(ModelicaCompletionProcessor compProc, 
			Document doc, int offset) 
	{
		try 
		{
			Method method = 
				compProc.getClass().getDeclaredMethod("getPrefix", 
							IDocument.class, int.class);
			
			method.setAccessible(true);
			return (String) method.invoke(compProc, doc, offset);
		}
		catch (SecurityException e) 
		{
			fail("was not allowed to call getPrefix()");
		}
		catch (NoSuchMethodException e) 
		{
			fail("could not find the getPrefix() method");
		} 
		catch (IllegalArgumentException e) 
		{
			fail("illegal arguments supplied to getPrefix()");
		}
		catch (IllegalAccessException e) 
		{
			fail("ehh, aj think they accuse us of tresspassing");
		}
		catch (InvocationTargetException e) 
		{
			fail("expection thrown while calling getPrefix()");
		}
		return null; /* we should have failed by now */
	}
}