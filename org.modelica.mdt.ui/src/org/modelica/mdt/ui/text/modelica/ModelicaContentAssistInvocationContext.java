/*******************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.ui.text.modelica;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

import org.eclipse.ui.IEditorPart;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.internal.core.ModelicaFile;
import org.modelica.mdt.ui.editor.EditorUtility;

/**
 * Describes the context of a content assist invocation in a Java editor.
 * <p>
 * Clients may use but not subclass this class.
 * </p>
 *
 * @since 3.2
 *
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ModelicaContentAssistInvocationContext extends ContentAssistInvocationContext {
	private final IEditorPart fEditor;

	private IModelicaSourceFile fCU= null;
	private boolean fCUComputed= false;

	private CompletionProposalLabelProvider fLabelProvider;
	private CompletionProposalCollector fCollector;

	private ICompletionProposal[] fKeywordProposals= null;
	private CompletionContext fCoreContext= null;

	/**
	 * Creates a new context.
	 *
	 * @param viewer the viewer used by the editor
	 * @param offset the invocation offset
	 * @param editor the editor that content assist is invoked in
	 */
	public ModelicaContentAssistInvocationContext(ITextViewer viewer, int offset, IEditorPart editor) {
		super(viewer, offset);
		Assert.isNotNull(editor);
		fEditor= editor;
	}

	/**
	 * Creates a new context.
	 *
	 * @param unit the compilation unit in <code>document</code>
	 */
	public ModelicaContentAssistInvocationContext(IModelicaSourceFile unit) {
		super();
		fCU= unit;
		fCUComputed= true;
		fEditor= null;
	}

	/**
	 * Returns the compilation unit that content assist is invoked in, <code>null</code> if there
	 * is none.
	 *
	 * @return the compilation unit that content assist is invoked in, possibly <code>null</code>
	 */
	public IModelicaSourceFile getCompilationUnit() {
		if (!fCUComputed) {
			fCUComputed= true;
			if (fCollector != null)
				fCU= fCollector.getCompilationUnit();
			else {
				IModelicaElement je= EditorUtility.getEditorInputModelicaElement(fEditor);
				if (je instanceof IModelicaSourceFile)
					fCU= (IModelicaSourceFile)je;
			}
		}
		return fCU;
	}

	/**
	 * Returns the project of the compilation unit that content assist is invoked in,
	 * <code>null</code> if none.
	 *
	 * @return the current java project, possibly <code>null</code>
	 */
	public IModelicaProject getProject() {
		IModelicaSourceFile unit= getCompilationUnit();
		return unit == null ? null : unit.getProject();
	}

	/**
	 * Returns the keyword proposals that are available in this context, possibly none.
	 * <p>
	 * <strong>Note:</strong> This method may run
	 * {@linkplain ICodeAssist#codeComplete(int, org.eclipse.jdt.core.CompletionRequestor) codeComplete}
	 * on the compilation unit.
	 * </p>
	 *
	 * @return the available keyword proposals
	 */
	public ICompletionProposal[] getKeywordProposals() {
		if (fKeywordProposals == null) {
			if (fCollector != null && !fCollector.isIgnored(CompletionProposal.KEYWORD) && fCollector.getContext() != null) {
				// use the existing collector if it exists, collects keywords, and has already been invoked
				fKeywordProposals= fCollector.getKeywordCompletionProposals();
			} else {
				// otherwise, retrieve keywords ourselves
				computeKeywordsAndContext();
			}
		}

		return fKeywordProposals;
	}

	/**
	 * Returns the {@link CompletionContext core completion context} if available, <code>null</code>
	 * otherwise.
	 * <p>
	 * <strong>Note:</strong> This method may run
	 * {@linkplain ICodeAssist#codeComplete(int, org.eclipse.jdt.core.CompletionRequestor) codeComplete}
	 * on the compilation unit.
	 * </p>
	 *
	 * @return the core completion context if available, <code>null</code> otherwise
	 */
	public CompletionContext getCoreContext() {
		if (fCoreContext == null) {
			// use the context from the existing collector if it exists, retrieve one ourselves otherwise
			if (fCollector != null)
				fCoreContext= fCollector.getContext();
			if (fCoreContext == null)
				computeKeywordsAndContext();
		}
		return fCoreContext;
	}
	
	/**
	 * Returns a label provider that can be used to compute proposal labels.
	 *
	 * @return a label provider that can be used to compute proposal labels
	 */
	public CompletionProposalLabelProvider getLabelProvider() {
		if (fLabelProvider == null) {
			if (fCollector != null)
				fLabelProvider= fCollector.getLabelProvider();
			else
				fLabelProvider= new CompletionProposalLabelProvider();
		}

		return fLabelProvider;
	}

	/**
	 * Sets the collector, which is used to access the compilation unit, the core context and the
	 * label provider. This is a performance optimization: {@link IModelicaCompletionProposalComputer}s
	 * may instantiate a {@link CompletionProposalCollector} and set this invocation context via
	 * {@link CompletionProposalCollector#setInvocationContext(ModelicaContentAssistInvocationContext)},
	 * which in turn calls this method. This allows the invocation context to retrieve the core
	 * context and keyword proposals from the existing collector, instead of computing theses values
	 * itself via {@link #computeKeywordsAndContext()}.
	 *
	 * @param collector the collector
	 */
	void setCollector(CompletionProposalCollector collector) {
		fCollector= collector;
	}

	/**
	 * Fallback to retrieve a core context and keyword proposals when no collector is available.
	 * Runs code completion on the cu and collects keyword proposals. {@link #fKeywordProposals} is
	 * non-<code>null</code> after this call.
	 *
	 * @since 3.3
	 */
	private void computeKeywordsAndContext() {
		IModelicaSourceFile cu= getCompilationUnit();
		if (cu == null) {
			if (fKeywordProposals == null)
				fKeywordProposals= new ICompletionProposal[0];
			return;
		}

		CompletionProposalCollector collector= new CompletionProposalCollector(cu, true);
		collector.setIgnored(CompletionProposal.KEYWORD, false);

		try {
			//((ModelicaFile) cu).codeComplete(getInvocationOffset(), collector);
			if (fCoreContext == null)
				fCoreContext= collector.getContext();
			if (fKeywordProposals == null)
				fKeywordProposals= collector.getKeywordCompletionProposals();
			if (fLabelProvider == null)
				fLabelProvider= collector.getLabelProvider();
		} catch (Exception x) {
			ErrorManager.logError(x);
			if (fKeywordProposals == null)
				fKeywordProposals= new IModelicaCompletionProposal[0];
		}
	}

	/*
	 * Implementation note: There is no need to override hashCode and equals, as we only add cached
	 * values shared across one assist invocation.
	 */
}
