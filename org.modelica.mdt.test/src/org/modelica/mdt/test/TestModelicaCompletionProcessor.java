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

import java.lang.reflect.Method;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension6;
import org.eclipse.jface.text.IUndoManager;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.internal.core.CorePlugin;
import org.modelica.mdt.test.util.Area51Projects;
import org.modelica.mdt.test.util.Utility;
import org.modelica.mdt.ui.assist.ModelicaCompletionProcessor;
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
	
	/* the viewer we are testing completion processor */
	private ITextViewer textViewer;
	
	/* the editor that contains the text we are computing completions on */
	private AbstractTextEditor editor;
	
	/* we can undo changes, this easy ! */
	private IUndoManager undoManager;

	
	@Override
	protected void setUp() throws Exception 
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
		
		editor = 
			(AbstractTextEditor)
				openInEditor(new ModelicaElementEditorInput(file));
		
		assertNotNull(editor);
		
		compProc = new ModelicaCompletionProcessor(editor);
		
		textViewer = callGetSourceViewer(editor);
		assertNotNull("could not fetch ITextViewer", textViewer);
		undoManager = ((ITextViewerExtension6)textViewer).getUndoManager();
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
						p.openEditor(input, "org.modelica.mdt.ui.editor", true);
	
				return editorPart;
			}
		}
		return null;
	}

	/**
	 * check ModelicaCompletionProcessor.computeCompletionProposals() method
	 */
	public void testComputeCompletionProposals() throws Exception
	{
		IDocument doc = textViewer.getDocument();
		String result;
				
		/*
		 * test completions inside the import_rich_model class but
		 * outside import_rich_model.bar
		 */

		/* type 'Mode' inside import_rich_model */
		doc.replace(383, 0, "Mode"); 

		/* get proposals at the end of 'Mode' */
		ICompletionProposal[] props =   
			compProc.computeCompletionProposals(textViewer, 387);
		checkProposalsOrder(props);

		/* we are expecting a proposal for the Modelica package */
		boolean foundModelicaProp = false;
		for (ICompletionProposal proposal : props)
		{
			if (proposal.getDisplayString().startsWith("Modelica"))
			{
				proposal.apply(doc);
				result = doc.get(383, 8);
				assertEquals("unexpected result of applying proposal",
						"Modelica", result);
				foundModelicaProp = true;
			}
		}
		assertTrue("did not found proposal for the 'Modelica'", 
				foundModelicaProp);
		
		/* type 'Modelica' inside import_rich_model */
		editor.doRevertToSaved();
		doc.replace(383, 0, "Modelica.M"); 

		/* get proposals at the end of 'Modelica.M' */
		props = compProc.computeCompletionProposals(textViewer, 393);
		checkProposalsOrder(props);

		/* 
		 * we are expecting a proposal for the Modelica.Math and 
		 * Modelica.Mechanics package 
		 */
		boolean foundModelicaMathProp = false;
		boolean foundModelicaMechanicsProp = false;
		
		for (ICompletionProposal proposal : props)
		{
			
			if (proposal.getDisplayString().startsWith("Math"))
			{
				/* must be Modelica.Math proposal */
				proposal.apply(doc);
				result = doc.get(383, 13);
				assertEquals("unexpected result of applying proposal",
						"Modelica.Math", result);
				foundModelicaMathProp = true;
				undoManager.undo();
			}
			else if (proposal.getDisplayString().startsWith("Mechanics"))
			{
				/* must be Modelica.Math proposal */
				proposal.apply(doc);
				result = doc.get(383, "Modelica.Mechanics".length());
				assertEquals("unexpected result of applying proposal",
						"Modelica.Mechanics", result);
				foundModelicaMechanicsProp = true;
				undoManager.undo();
			}
			
		}
		assertTrue("did not found proposal for the 'Modelica.Math'", 
				foundModelicaMathProp);
		assertTrue("did not found proposal for the 'Modelica.Mechanics'", 
				foundModelicaMechanicsProp);

		/* type 'Mechanics.Rotational.Sp' inside import_rich_model */
		editor.doRevertToSaved();
		doc.replace(383, 0, "Mechanics.Rotational.Sp"); 

		/* get proposals at the end of 'Modelica.M' */
		props = compProc.computeCompletionProposals(textViewer, 
				383 + "Mechanics.Rotational.Sp".length());
		checkProposalsOrder(props);
		
		/* 
		 * we are expecting a proposal for the Mechanics.Rotational.Speed and 
		 * Mechanics.Rotational.Spring packages 
		 */
		boolean foundSpeedProp = false;
		boolean foundSpringDamperProp = false;
		
		for (ICompletionProposal proposal : props) {
			String proposalDisplayString = proposal.getDisplayString(); System.out.println("proposalDisplayString: " + proposalDisplayString);
			
			if (proposalDisplayString.startsWith("Speed")) {
				proposal.apply(doc);
				result = doc.get(383, "Mechanics.Rotational.Speed".length());
				assertEquals("unexpected result of applying proposal",
						"Mechanics.Rotational.Speed", result);
				foundSpeedProp = true;
				undoManager.undo();
			}
			else if (proposalDisplayString.startsWith("SpringDamper")) {
				proposal.apply(doc);
				result = doc.get(383, 
						"Mechanics.Rotational.SpringDamper".length());
				assertEquals("unexpected result of applying proposal",
						"Mechanics.Rotational.SpringDamper", result);
				foundSpringDamperProp = true;
				undoManager.undo();
			}
		}

		assertTrue("did not found proposal for the Mechanics.Rotational.Speed", foundSpeedProp);
		assertTrue("did not found proposal for the Mechanics.Rotational.SpringDamper", foundSpringDamperProp);

		/* type 'Modelica.Math.' */
		editor.doRevertToSaved();
		doc.replace(383, 0, "Modelica.Math."); 

		/* get proposals at the end of 'Modelica.Math.' */
		props = compProc.computeCompletionProposals(textViewer, 397);
		checkProposalsOrder(props);

		/* 
		 * we are expecting a proposal for among others Modelica.Math.asin,
		 * Modelica.Math.cos, Modelica.Math.exp and Modelica.Math.log
		 */
		boolean foundModelicaMathAsinProp = false;
		boolean foundModelicaMathCosProp = false;
		boolean foundModelicaMathExpProp = false;
		boolean foundModelicaMathLogProp = false;
		
		for (ICompletionProposal proposal : props) {
			if (proposal.getDisplayString().startsWith("asin")) {
				/* must be Modelica.Math.asin proposal */
				proposal.apply(doc);
				result = doc.get(383, "Modelica.Math.asin".length());
				assertEquals("unexpected result of applying proposal",
						"Modelica.Math.asin", result);
				foundModelicaMathAsinProp = true;
				undoManager.undo();
			}
			else if (proposal.getDisplayString().startsWith("cos")) {
				/* must be Modelica.Math.cos proposal */
				proposal.apply(doc);
				result = doc.get(383, "Modelica.Math.cos".length());
				assertEquals("unexpected result of applying proposal",
						"Modelica.Math.cos", result);
				foundModelicaMathCosProp = true;
				undoManager.undo();
			}
			else if (proposal.getDisplayString().startsWith("exp")) {
				/* must be Modelica.Math.exp proposal */
				proposal.apply(doc);
				result = doc.get(383, "Modelica.Math.exp".length());
				assertEquals("unexpected result of applying proposal",
						"Modelica.Math.exp", result);
				foundModelicaMathExpProp = true;
				undoManager.undo();
			}			
			else if (proposal.getDisplayString().startsWith("log")) {
				/* must be Modelica.Math.log proposal */
				proposal.apply(doc);
				result = doc.get(383, "Modelica.Math.log".length());
				assertEquals("unexpected result of applying proposal",
						"Modelica.Math.log", result);
				foundModelicaMathLogProp = true;
				undoManager.undo();
			}			
		}
		
		assertTrue("did not found proposal for the 'Modelica.Math.asin'", foundModelicaMathAsinProp);
		assertTrue("did not found proposal for the 'Modelica.Math.cos'", foundModelicaMathCosProp);
		assertTrue("did not found proposal for the 'Modelica.Math.exp'", foundModelicaMathExpProp);
		assertTrue("did not found proposal for the 'Modelica.Math.log'", foundModelicaMathLogProp);
		
		/* 
		 * test completion proposals in 'the middle of nowhere'
		 * in a place where the prefix will be empty (the '' string)
		 */
		editor.doRevertToSaved();
		props = compProc.computeCompletionProposals(textViewer, 383);
		checkProposalsOrder(props);

		/* 
		 * we are expecting a proposal for among others Blocks,
		 * x, Icons and hehehe
		 */
		boolean foundBlocksProp = false;
		boolean foundIconsProp = false;
		boolean foundHeheheProp = false;
		boolean foundXProp = false;
		
		for (ICompletionProposal proposal : props)
		{
			if (proposal.getDisplayString().startsWith("Blocks"))
			{
				proposal.apply(doc);
				result = doc.get(383, "Blocks".length());
				assertEquals("unexpected result of applying proposal",
						"Blocks", result);
				foundBlocksProp = true;
				undoManager.undo();
			}
			else if (proposal.getDisplayString().startsWith("Icons"))
			{
				/* must be Modelica.Math.cos proposal */
				proposal.apply(doc);
				result = doc.get(383, "Icons".length());
				assertEquals("unexpected result of applying proposal",
						"Icons", result);
				foundIconsProp = true;
				undoManager.undo();
			}
			else if (proposal.getDisplayString().startsWith("hehehe"))
			{
				/* must be Modelica.Math.exp proposal */
				proposal.apply(doc);
				result = doc.get(383, "hehehe".length());
				assertEquals("unexpected result of applying proposal",
						"hehehe", result);
				foundHeheheProp = true;
				undoManager.undo();
			}
			else if (proposal.getDisplayString().startsWith("x"))
			{
				/* must be Modelica.Math.cos proposal */
				proposal.apply(doc);
				result = doc.get(383, "x".length());
				assertEquals("unexpected result of applying proposal",
						"x", result);
				foundXProp = true;
				undoManager.undo();
			}
		}
		
		assertTrue("did not found proposal for the 'Blocks'", 
				foundBlocksProp);
		assertTrue("did not found proposal for the 'Icons'", 
				foundIconsProp);
		assertTrue("did not found proposal for the 'hehehe'", 
				foundHeheheProp);
		assertTrue("did not found proposal for the 'x'", 
				foundXProp);
		
		/* type 'm' inside import_rich_model */
		editor.doRevertToSaved();
		doc.replace(383, 0, "m"); 

		/* get proposals at just past 'm' */
		props =	compProc.computeCompletionProposals(textViewer, 384);
		checkProposalsOrder(props);
				
		/* 
		 * we are expecting a proposal for the 'mm', 
		 * the renamed Modelica.Math package 
		 */
		boolean foundMMProp = false;
		for (ICompletionProposal proposal : props)
		{
			if (proposal.getDisplayString().startsWith("mm"))
			{
				proposal.apply(doc);
				result = doc.get(383, "mm".length());
				assertEquals("unexpected result of applying proposal",
						"mm", result);
				foundMMProp = true;
			}
		}
		assertTrue("did not found proposal for the 'mm'", 
				foundMMProp);
		
		/* type 'mm.s' inside import_rich_model */
		editor.doRevertToSaved();
		doc.replace(383, 0, "mm.s"); 

		/* get proposals at the end of 'mm.s' */
		props =	compProc.computeCompletionProposals(textViewer, 386);
		checkProposalsOrder(props);

		/* 
		 * we are expecting a proposal for the 'mm.sin' and 'mm.sinh' 
		 * the renamed Modelica.Math package 
		 */
		boolean foundMMSinProp = false;
		boolean foundMMSinhProp = false;
		for (ICompletionProposal proposal : props)
		{
			/*
			 * the order of these if statments is important becouse 
			 * 'sinh' starts with 'sin'
			 */
			if (proposal.getDisplayString().startsWith("sinh"))
			{
				proposal.apply(doc);
				result = doc.get(383, "mm.sinh".length());
				assertEquals("unexpected result of applying proposal",
						"mm.sinh", result);
				foundMMSinhProp = true;
				undoManager.undo();
			}
			else if (proposal.getDisplayString().startsWith("sin"))
			{
				proposal.apply(doc);
				result = doc.get(383, "mm.sin".length());
				assertEquals("unexpected result of applying proposal",
						"mm.sin", result);
				foundMMSinProp = true;
				undoManager.undo();
			}
		}
		assertTrue("did not found proposal for the 'mm.sinh'", 
				foundMMSinhProp);
		assertTrue("did not found proposal for the 'mm.sin'", 
				foundMMSinProp);
		
		/*
		 * test completions inside the inside import_rich_model.bar
		 */
		
		/* type '    ho' inside import_rich_model.bar */
		editor.doRevertToSaved();
		doc.replace(586, 0, "    ho");
		
		/* get proposals at the end of 'ho' */
		props = compProc.computeCompletionProposals(textViewer, 
				586 + "    ho".length());
		checkProposalsOrder(props);

		/* we are expecting a proposal for the hopp package */
		boolean foundHoppProp = false;
		for (ICompletionProposal proposal : props)
		{
			if (proposal.getDisplayString().startsWith("hopp"))
			{
				proposal.apply(doc);
				result = doc.get(590, "hopp".length());
				assertEquals("unexpected result of applying proposal",
						"hopp", result);
				foundHoppProp = true;
			}
		}
		assertTrue("did not found proposal for the 'hopp'", 
				foundHoppProp);		


		/* type 'SIunits.Absorb' inside import_rich_model.bar */
		editor.doRevertToSaved();
		doc.replace(586, 0, "SIunits.Absorb"); 

		/* get proposals at the end of 'SIunits.Absorb' */
		props = compProc.computeCompletionProposals(textViewer, 
				586 + "SIunits.Absorb".length());
		checkProposalsOrder(props);

		/* 
		 * we are expecting a proposal for the SIunits.AbsorbedDose and 
		 * SIunits.AbsorbedDoseRate 
		 */
		boolean foundAbsorbedDoseProp = false;
		boolean foundAbsorbedDoseRateProp = false;
		
		for (ICompletionProposal proposal : props)
		{
			/*
			 * the order of these if statments is important becouse 
			 * 'AbsorbedDoseRate' starts with 'AbsorbedDose'
			 */
			if (proposal.getDisplayString().startsWith("AbsorbedDoseRate"))
			{
				/* must be Modelica.Math proposal */
				proposal.apply(doc);
				result = doc.get(586, "SIunits.AbsorbedDoseRate".length());
				assertEquals("unexpected result of applying proposal",
						"SIunits.AbsorbedDoseRate", result);
				foundAbsorbedDoseRateProp = true;
				undoManager.undo();
			}
			else if (proposal.getDisplayString().equals("AbsorbedDose"))
			{
				/* must be Modelica.Math proposal */
				proposal.apply(doc);
				result = doc.get(586, "SIunits.AbsorbedDose".length());
				assertEquals("unexpected result of applying proposal",
						"SIunits.AbsorbedDose", result);
				foundAbsorbedDoseProp = true;
				undoManager.undo();
			}			
		}
		assertTrue("did not found proposal for the 'SIunits.AbsorbedDose'", 
				foundAbsorbedDoseProp);
		assertTrue("did not found proposal for the 'SIunits.AbsorbedDoseRate'", 
				foundAbsorbedDoseRateProp);

		
		/* type 'Blocks.Math.' inside import_rich_model.bar */
		editor.doRevertToSaved();
		doc.replace(586, 0, "Blocks.Math.");
		
		/* get proposals at the end of 'Blocks.Math.' */
		props = compProc.computeCompletionProposals(textViewer, 
				586 + "Blocks.Math.".length());
		checkProposalsOrder(props);

		/* 
		 * we are expecting among others proposals for the Math.Edge, Math.Min
		 * and Math.Feedback
		 */
		boolean foundMathEdgeProp = false;
		boolean foundMathMinProp = false;
		boolean foundMathFeedbackProp = false;
		
		for (ICompletionProposal proposal : props)
		{
			if (proposal.getDisplayString().startsWith("Edge"))
			{
				/* must be Blocks.Math.Edge proposal */
				proposal.apply(doc);
				result = doc.get(586, "Blocks.Math.Edge".length());
				assertEquals("unexpected result of applying proposal",
						"Blocks.Math.Edge", result);
				foundMathEdgeProp = true;
				undoManager.undo();
			}
			else if (proposal.getDisplayString().startsWith("Min"))
			{
				/* must be Blocks.Math.Min proposal */
				proposal.apply(doc);
				result = doc.get(586, "Blocks.Math.Min".length());
				assertEquals("unexpected result of applying proposal",
						"Blocks.Math.Min", result);
				foundMathMinProp = true;
				undoManager.undo();
			}
			else if (proposal.getDisplayString().startsWith("Feedback"))
			{
				/* must be Blocks.Math.Min proposal */
				proposal.apply(doc);
				result = doc.get(586, "Blocks.Math.Feedback".length());
				assertEquals("unexpected result of applying proposal",
						"Blocks.Math.Feedback", result);
				foundMathFeedbackProp = true;
				undoManager.undo();
			}

		}
		
		assertTrue("did not found proposal for the 'Blocks.Math.Edge'", 
				foundMathEdgeProp);
		assertTrue("did not found proposal for the 'Blocks.Math.Min'", 
				foundMathMinProp);
		assertTrue("did not found proposal for the 'Blocks.Math.Feedback'", 
				foundMathFeedbackProp);

		/*
		 * type nothing inside import_rich_model.bar to test proposals
		 * for empty prefix 
		 */
		editor.doRevertToSaved();
		
		props = compProc.computeCompletionProposals(textViewer,	586);
		checkProposalsOrder(props);

		/* 
		 * we are expecting among others proposals for the x, y and cp
		 */
		foundXProp = false;
		boolean foundYProp = false;
		boolean foundCpProp = false;
		
		for (ICompletionProposal proposal : props)
		{
			if (proposal.getDisplayString().startsWith("x"))
			{
				proposal.apply(doc);
				result = doc.get(586, "x".length());
				assertEquals("unexpected result of applying proposal",
						"x", result);
				foundXProp = true;
				undoManager.undo();
			}
			else if (proposal.getDisplayString().startsWith("y"))
			{
				proposal.apply(doc);
				result = doc.get(586, "y".length());
				assertEquals("unexpected result of applying proposal",
						"y", result);
				foundYProp = true;
				undoManager.undo();
			}
			else if (proposal.getDisplayString().startsWith("cp"))
			{
				proposal.apply(doc);
				result = doc.get(586, "cp".length());
				assertEquals("unexpected result of applying proposal",
						"cp", result);
				foundCpProp = true;
				undoManager.undo();
			}

		}
		assertTrue("did not found proposal for the 'Blocks.Math.Edge'", 
				foundXProp);
		assertTrue("did not found proposal for the 'Blocks.Math.Min'", 
				foundYProp);
		assertTrue("did not found proposal for the 'Blocks.Math.Feedback'", 
				foundCpProp);

		
		/*
		 * this is a bug trigger code
		 * when cp. is typed inside the import_rich_model.bar
		 * the faulty version of the completion processor proposes 'package.mo'
		 */
		
		/* type 'cp.' inside import_rich_model.bar */
		editor.doRevertToSaved();
		doc.replace(586, 0, "cp.");
		
		/* get proposals at the end of 'Blocks.Math.' */
		props = compProc.computeCompletionProposals(textViewer, 557);
		checkProposalsOrder(props);

		/* 
		 * we are NOT expecting a proposal for package.mo
		 */
		for (ICompletionProposal proposal : props)
		{
			/* check that no unexpected proposals show up */
			System.out.println(proposal.getDisplayString());
			if (proposal.getDisplayString().startsWith("package.mo"))
			{
				/* not good !*/
				fail("proposal for package.mo found");
			}		
		}


		/*
		 *  Test generating proposals inside the encapsulated
		 *  class import_rich_model.foo with empty prefix.
		 *  
		 *  We must check that no proposals based on the outside
		 *  class are returned to uss.
		 */
		
		editor.doRevertToSaved();
		/* get proposals at inside import_rich_model.foo with empty prefix */
		props = compProc.computeCompletionProposals(textViewer, 771);
		checkProposalsOrder(props);

		/* 
		 * we are NOT expecting a proposal for among others Modelica.Math.sin,
		 * mm, Modelica.Blocks, Modelica.Icons 
		 * 
		 * we are expecting a proposal for foo_local_var
		 */
		boolean foundFooLocalVarProp = false;
		for (ICompletionProposal proposal : props)
		{
			/* check that no unexpected proposals show up */
			if (proposal.getDisplayString().startsWith("sin"))
			{
				fail("proposal for sin");
			}
			else if (proposal.getDisplayString().startsWith("mm"))
			{
				fail("proposal for mm (Modelica.Math)");
			}
			else if (proposal.getDisplayString().startsWith("Icons"))
			{
				fail("proposal for Icons");
			}
			else if (proposal.getDisplayString().startsWith("Blocks"))
			{
				fail("proposal for Blocks");
			}
			
			/* check for expected proposals */
			if (proposal.getDisplayString().startsWith("foo_local_var"))
			{
				proposal.apply(doc);
				result = doc.get(771, "foo_local_var".length());
				assertEquals("unexpected result of applying proposal",
						"foo_local_var", result);
				foundFooLocalVarProp = true;
				undoManager.undo();
			}

		}
		assertTrue("did not found proposal for the 'foo_local_var'", 
				foundFooLocalVarProp);
		
		/* 
		 * revert to saved otherwise a dialog will pop-up asking us to save
		 * changes. that will make the test require manual labor to finish
		 * running, not good
		 */
		editor.doRevertToSaved();

	}

	/**
	 * THis method check that proposals are sorted in alpabetic order
	 * on the display string. It proposals are not sorted, this
	 * method fails the test.
	 */
	private void checkProposalsOrder(ICompletionProposal[] props)
	{
		ICompletionProposal prevProp = null;

		for (ICompletionProposal currProp : props)
		{
			if (prevProp != null)
			{
				int compRes = 
					prevProp.getDisplayString().compareToIgnoreCase
						(currProp.getDisplayString());
				
				/* 
				 * current proposals display string must be
				 * greater or equal to previus
				 */
				assertTrue("completion proposals not sorted", compRes <= 0);
			}
			prevProp = currProp;
		}
	}
	
	/**
	 * check ModelicaCompletionProcessor.getPrefix() method
	 */
	public void testPrefix()
		throws Exception
	{
		/* this is used to test getPrefix() method */
		String docContents = 
			"hej hopp jaa\n" + /* 13 characters */
			"asdf im(prt ;da"; /* 15 characters */

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

	/**
	 * This method allows to call the private method  
	 * ModelicaCompletionProcessor.getPrefix() by the magic of reflection.
	 *
	 * @param compProc the ModelicaCompletionProcessor to invoke the method on
	 * @param doc the 'doc' argument to pass to getPrefix()
	 * @param offset the 'offset' argument to pass to getPrefix()
	 */
	private String callGetPrefix(ModelicaCompletionProcessor compProc, 
			Document doc, int offset) 
		throws Exception
	{
		Method method = 
			compProc.getClass().getDeclaredMethod("getPrefix", 
						IDocument.class, int.class);
		
		method.setAccessible(true);
		return (String) method.invoke(compProc, doc, offset);
	}

	/**
	 * This method allows to call the private method  
	 * AbstractTextEditor.getSourceViewer() by the magic of reflection.
	 *
	 * @param editor the AbstractTextEditor to invoke the method on
	 */	
	private ITextViewer callGetSourceViewer(AbstractTextEditor editor)
		throws Exception
	{
		Method method = 
			AbstractTextEditor.class.getDeclaredMethod("getSourceViewer"); 
		
		method.setAccessible(true);
		return (ITextViewer) method.invoke(editor);
	}


}