/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.ui.text.modelica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.graphics.Image;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StyledString;

import org.eclipse.jface.text.IDocument;

import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.compiler.IProblem;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.internal.core.Signature;
import org.modelica.mdt.ui.UIPlugin;
import org.modelica.mdt.ui.assist.ModelicaCompletionProposal;

/**
 * Modelica UI implementation of <code>CompletionRequestor</code>. Produces
 * {@link IModelicaCompletionProposal}s from the proposal descriptors received via
 * the <code>CompletionRequestor</code> interface.
 * <p>
 * The lifecycle of a <code>CompletionProposalCollector</code> instance is very
 * simple:
 * <pre>
 * IDocument unit= ...
 * int offset= ...
 *
 * CompletionProposalCollector collector= new CompletionProposalCollector(unit);
 * unit.codeComplete(offset, collector);
 * IModelicaCompletionProposal[] proposals= collector.getModelicaCompletionProposals();
 * String errorMessage= collector.getErrorMessage();
 *
 * &#x2f;&#x2f; display &#x2f; process proposals
 * </pre>
 * Note that after a code completion operation, the collector will store any
 * received proposals, which may require a considerable amount of memory, so the
 * collector should not be kept as a reference after a completion operation.
 * </p>
 * <p>
 * Clients may instantiate or subclass.
 * </p>
 * @since 3.1
 */
public class CompletionProposalCollector extends CompletionRequestor {

	/** Tells whether this class is in debug mode. */
	private static final boolean DEBUG= "true".equalsIgnoreCase(Platform.getDebugOption("org.eclipse.jdt.ui/debug/ResultCollector"));  //$NON-NLS-1$//$NON-NLS-2$

	/** Triggers for method proposals without parameters. Do not modify. */
	protected final static char[] METHOD_TRIGGERS= new char[] { ';', ',', '.', '\t', '[', ' ' };
	/** Triggers for method proposals. Do not modify. */
	protected final static char[] METHOD_WITH_ARGUMENTS_TRIGGERS= new char[] { '(', '-', ' ' };
	/** Triggers for types. Do not modify. */
	protected final static char[] TYPE_TRIGGERS= new char[] { '.', '\t', '[', '(', ' ' };
	/** Triggers for variables. Do not modify. */
	protected final static char[] VAR_TRIGGER= new char[] { '\t', ' ', '=', ';', '.' };

	private final CompletionProposalLabelProvider fLabelProvider= new CompletionProposalLabelProvider();

	private final List<IModelicaCompletionProposal> fModelicaProposals= new ArrayList<IModelicaCompletionProposal>();
	private final List<IModelicaCompletionProposal> fKeywords= new ArrayList<IModelicaCompletionProposal>();
	private final Set<String> fSuggestedMethodNames= new HashSet<String>();

	private final IModelicaSourceFile fCompilationUnit;
	@SuppressWarnings("unused")
	private final IModelicaProject fModelicaProject;
	private int fUserReplacementLength;

	private CompletionContext fContext;
	@SuppressWarnings("unused")
	private IProblem fLastProblem;

	/* performance instrumentation */
	private long fStartTime;
	private long fUITime;

	/**
	 * The UI invocation context or <code>null</code>.
	 *
	 * @since 3.2
	 */
	private ModelicaContentAssistInvocationContext fInvocationContext;

	/**
	 * Creates a new instance ready to collect proposals. If the passed
	 * <code>IDocument</code> is not contained in an
	 * {@link IModelicaProject}, no javadoc will be available as
	 * {@link org.eclipse.jface.text.contentassist.ICompletionProposal#getAdditionalProposalInfo() additional info}
	 * on the created proposals.
	 *
	 * @param cu the compilation unit that the result collector will operate on
	 */
	public CompletionProposalCollector(IModelicaSourceFile cu) {
		this(cu.getProject(), cu, false);
	}

	/**
	 * Creates a new instance ready to collect proposals. Note that proposals
	 * for anonymous types and method declarations are not created when using
	 * this constructor, as those need to know the compilation unit that they
	 * are created on. Use
	 * {@link CompletionProposalCollector#CompletionProposalCollector(IDocument)}
	 * instead to get all proposals.
	 * <p>
	 * If the passed Modelica project is <code>null</code>, no javadoc will be
	 * available as
	 * {@link org.eclipse.jface.text.contentassist.ICompletionProposal#getAdditionalProposalInfo() additional info}
	 * on the created (e.g. method and type) proposals.
	 * </p>
	 * @param project the project that the result collector will operate on, or
	 *        <code>null</code>
	 */
	public CompletionProposalCollector(IModelicaProject project) {
		this(project, null, false);
	}

	private CompletionProposalCollector(IModelicaProject project, IModelicaSourceFile cu, boolean ignoreAll) {
		super(ignoreAll);
		fModelicaProject= project;
		fCompilationUnit= cu;

		fUserReplacementLength= -1;
		if (!ignoreAll) {
			setRequireExtendedContext(true);
		}
	}

	/**
	 * Creates a new instance ready to collect proposals. If the passed
	 * <code>IDocument</code> is not contained in an
	 * {@link IModelicaProject}, no javadoc will be available as
	 * {@link org.eclipse.jface.text.contentassist.ICompletionProposal#getAdditionalProposalInfo() additional info}
	 * on the created proposals.
	 *
	 * @param cu the compilation unit that the result collector will operate on
	 * @param ignoreAll <code>true</code> to ignore all kinds of completion proposals
	 * @since 3.4
	 */
	public CompletionProposalCollector(IModelicaSourceFile cu, boolean ignoreAll) {
		this(cu.getProject(), cu, ignoreAll);
	}

	/* (non-Modelicadoc)
	 * @see org.eclipse.jdt.core.CompletionRequestor#setIgnored(int, boolean)
	 */
	public void setIgnored(int completionProposalKind, boolean ignore) {
		super.setIgnored(completionProposalKind, ignore);
	}

	/**
	 * Sets the invocation context.
	 * <p>
	 * Subclasses may extend.
	 * </p>
	 *
	 * @param context the invocation context
	 * @see #getInvocationContext()
	 * @since 3.2
	 */
	public void setInvocationContext(ModelicaContentAssistInvocationContext context) {
		Assert.isNotNull(context);
		fInvocationContext= context;
		context.setCollector(this);
	}

	/**
	 * Returns the invocation context. If none has been set via
	 * {@link #setInvocationContext(ModelicaContentAssistInvocationContext)}, a new one is created.
	 *
	 * @return invocationContext the invocation context
	 * @since 3.2
	 */
	protected final ModelicaContentAssistInvocationContext getInvocationContext() {
		if (fInvocationContext == null)
			setInvocationContext(new ModelicaContentAssistInvocationContext(getCompilationUnit()));
		return fInvocationContext;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Subclasses may replace, but usually should not need to. Consider
	 * replacing
	 * {@linkplain #createModelicaCompletionProposal(CompletionProposal) createModelicaCompletionProposal}
	 * instead.
	 * </p>
	 */
	public void accept(CompletionProposal proposal) {
		long start= DEBUG ? System.currentTimeMillis() : 0;
		try {
			if (isFiltered(proposal))
				return;

			IModelicaCompletionProposal javaProposal= createModelicaCompletionProposal(proposal);
			if (javaProposal != null) {
				fModelicaProposals.add(javaProposal);
				if (proposal.getKind() == CompletionProposal.KEYWORD)
					fKeywords.add(javaProposal);
			}
		} catch (IllegalArgumentException e) {
			// all signature processing method may throw IAEs
			// https://bugs.eclipse.org/bugs/show_bug.cgi?id=84657
			// don't abort, but log and show all the valid proposals
			ErrorManager.logError(new Status(IStatus.ERROR, UIPlugin.PLUGIN_ID, IStatus.OK, "Exception when processing proposal for: " + String.valueOf(proposal.getCompletion()), e)); //$NON-NLS-1$
		}

		if (DEBUG) fUITime += System.currentTimeMillis() - start;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Subclasses may extend, but usually should not need to.
	 * </p>
	 * @see #getContext()
	 */
	public void acceptContext(CompletionContext context) {
		fContext= context;
		fLabelProvider.setContext(context);
	}

	/**
	 * {@inheritDoc}
	 *
	 * Subclasses may extend, but must call the super implementation.
	 */
	public void beginReporting() {
		if (DEBUG) {
			fStartTime= System.currentTimeMillis();
			fUITime= 0;
		}

		fLastProblem= null;
		fModelicaProposals.clear();
		fKeywords.clear();
		fSuggestedMethodNames.clear();
	}

	/**
	 * {@inheritDoc}
	 *
	 * Subclasses may extend, but must call the super implementation.
	 */
	public void completionFailure(IProblem problem) {
		fLastProblem= problem;
	}

	/**
	 * {@inheritDoc}
	 *
	 * Subclasses may extend, but must call the super implementation.
	 */
	public void endReporting() {
		if (DEBUG) {
			long total= System.currentTimeMillis() - fStartTime;
			System.err.println("Core Collector (core):\t" + (total - fUITime)); //$NON-NLS-1$
			System.err.println("Core Collector (ui):\t" + fUITime); //$NON-NLS-1$
		}
	}

	/**
	 * Returns an error message about any error that may have occurred during
	 * code completion, or the empty string if none.
	 * <p>
	 * Subclasses may replace or extend.
	 * </p>
	 * @return an error message or the empty string
	 */
	public String getErrorMessage() {
		return ""; //$NON-NLS-1$
	}

	/**
	 * Returns the unsorted list of received proposals.
	 *
	 * @return the unsorted list of received proposals
	 */
	public final IModelicaCompletionProposal[] getModelicaCompletionProposals() {
		return (IModelicaCompletionProposal[]) fModelicaProposals.toArray(new IModelicaCompletionProposal[fModelicaProposals.size()]);
	}

	/**
	 * Returns the unsorted list of received keyword proposals.
	 *
	 * @return the unsorted list of received keyword proposals
	 */
	public final IModelicaCompletionProposal[] getKeywordCompletionProposals() {
		return (IModelicaCompletionProposal[]) fKeywords.toArray(new ModelicaCompletionProposal[fKeywords.size()]);
	}

	/**
	 * If the replacement length is set, it overrides the length returned from
	 * the content assist infrastructure. Use this setting if code assist is
	 * called with a none empty selection.
	 *
	 * @param length the new replacement length, relative to the code assist
	 *        offset. Must be equal to or greater than zero.
	 */
	public final void setReplacementLength(int length) {
		Assert.isLegal(length >= 0);
		fUserReplacementLength= length;
	}

	/**
	 * Computes the relevance for a given <code>CompletionProposal</code>.
	 * <p>
	 * Subclasses may replace, but usually should not need to.
	 * </p>
	 * @param proposal the proposal to compute the relevance for
	 * @return the relevance for <code>proposal</code>
	 */
	protected int computeRelevance(CompletionProposal proposal) {
		final int baseRelevance= proposal.getRelevance() * 16;
		switch (proposal.getKind()) {
			case CompletionProposal.PACKAGE_REF:
				return baseRelevance + 0;
			case CompletionProposal.LABEL_REF:
				return baseRelevance + 1;
			case CompletionProposal.KEYWORD:
				return baseRelevance + 2;
			case CompletionProposal.TYPE_REF:
			case CompletionProposal.ANONYMOUS_CLASS_DECLARATION:
				return baseRelevance + 3;
			case CompletionProposal.METHOD_REF:
			case CompletionProposal.METHOD_NAME_REFERENCE:
			case CompletionProposal.METHOD_DECLARATION:
			case CompletionProposal.ANNOTATION_ATTRIBUTE_REF:
				return baseRelevance + 4;
			case CompletionProposal.POTENTIAL_METHOD_DECLARATION:
				return baseRelevance + 4 /* + 99 */;
			case CompletionProposal.FIELD_REF:
				return baseRelevance + 5;
			case CompletionProposal.LOCAL_VARIABLE_REF:
			case CompletionProposal.VARIABLE_DECLARATION:
				return baseRelevance + 6;
			default:
				return baseRelevance;
		}
	}

	/**
	 * Creates a new java completion proposal from a core proposal. This may
	 * involve computing the display label and setting up some context.
	 * <p>
	 * This method is called for every proposal that will be displayed to the
	 * user, which may be hundreds. Implementations should therefore defer as
	 * much work as possible: Labels should be computed lazily to leverage
	 * virtual table usage, and any information only needed when
	 * <em>applying</em> a proposal should not be computed yet.
	 * </p>
	 * <p>
	 * Implementations may return <code>null</code> if a proposal should not
	 * be included in the list presented to the user.
	 * </p>
	 * <p>
	 * Subclasses may extend or replace this method.
	 * </p>
	 *
	 * @param proposal the core completion proposal to create a UI proposal for
	 * @return the created java completion proposal, or <code>null</code> if
	 *         no proposal should be displayed
	 */
	protected IModelicaCompletionProposal createModelicaCompletionProposal(CompletionProposal proposal) {
		switch (proposal.getKind()) {
			case CompletionProposal.KEYWORD:
				return createKeywordProposal(proposal);
			case CompletionProposal.PACKAGE_REF:
				return createPackageProposal(proposal);
			case CompletionProposal.TYPE_REF:
				return createTypeProposal(proposal);
			case CompletionProposal.JAVADOC_TYPE_REF:
				return createModelicadocLinkTypeProposal(proposal);
			case CompletionProposal.FIELD_REF:
			case CompletionProposal.JAVADOC_FIELD_REF:
			case CompletionProposal.JAVADOC_VALUE_REF:
				return createFieldProposal(proposal);
			case CompletionProposal.METHOD_REF:
			case CompletionProposal.METHOD_REF_WITH_CASTED_RECEIVER:
			case CompletionProposal.METHOD_NAME_REFERENCE:
			case CompletionProposal.JAVADOC_METHOD_REF:
				return createMethodReferenceProposal(proposal);
			case CompletionProposal.LABEL_REF:
				return createLabelProposal(proposal);
			case CompletionProposal.LOCAL_VARIABLE_REF:
			case CompletionProposal.VARIABLE_DECLARATION:
				return createLocalVariableProposal(proposal);
			case CompletionProposal.ANNOTATION_ATTRIBUTE_REF:
				return createAnnotationAttributeReferenceProposal(proposal);
			case CompletionProposal.JAVADOC_BLOCK_TAG:
			case CompletionProposal.JAVADOC_PARAM_REF:
				return createModelicadocSimpleProposal(proposal);
			case CompletionProposal.JAVADOC_INLINE_TAG:
				return createModelicadocInlineTagProposal(proposal);
			case CompletionProposal.POTENTIAL_METHOD_DECLARATION:
			case CompletionProposal.ANONYMOUS_CLASS_DECLARATION:				
			default:
				return null;
		}
	}

	/**
	 * Returns the compilation unit that the receiver operates on, or
	 * <code>null</code> if the <code>IModelicaProject</code> constructor was
	 * used to create the receiver.
	 *
	 * @return the compilation unit that the receiver operates on, or
	 *         <code>null</code>
	 */
	protected final IModelicaSourceFile getCompilationUnit() {
		return fCompilationUnit;
	}

	/**
	 * Returns the <code>CompletionContext</code> for this completion operation.

	 * @return the <code>CompletionContext</code> for this completion operation
	 * @see CompletionRequestor#acceptContext(CompletionContext)
	 */
	protected final CompletionContext getContext() {
		return fContext;
	}

	/**
	 * Returns a cached image for the given descriptor.
	 *
	 * @param descriptor the image descriptor to get an image for, may be
	 *        <code>null</code>
	 * @return the image corresponding to <code>descriptor</code>
	 */
	protected final Image getImage(ImageDescriptor descriptor) {
		return (descriptor == null) ? null : descriptor.createImage();
	}

	/**
	 * Returns the proposal label provider used by the receiver.
	 *
	 * @return the proposal label provider used by the receiver
	 */
	protected final CompletionProposalLabelProvider getLabelProvider() {
		return fLabelProvider;
	}

	/**
	 * Returns the replacement length of a given completion proposal. The
	 * replacement length is usually the difference between the return values of
	 * <code>proposal.getReplaceEnd</code> and
	 * <code>proposal.getReplaceStart</code>, but this behavior may be
	 * overridden by calling {@link #setReplacementLength(int)}.
	 *
	 * @param proposal the completion proposal to get the replacement length for
	 * @return the replacement length for <code>proposal</code>
	 */
	protected final int getLength(CompletionProposal proposal) {
		int start= proposal.getReplaceStart();
		int end= proposal.getReplaceEnd();
		int length;
		if (fUserReplacementLength == -1) {
			length= end - start;
		} else {
			length= fUserReplacementLength;
			// extend length to begin at start
			int behindCompletion= proposal.getCompletionLocation() + 1;
			if (start < behindCompletion) {
				length+= behindCompletion - start;
			}
		}
		return length;
	}

	/**
	 * Returns <code>true</code> if <code>proposal</code> is filtered, e.g.
	 * should not be proposed to the user, <code>false</code> if it is valid.
	 * <p>
	 * Subclasses may extends this method. The default implementation filters
	 * proposals set to be ignored via
	 * {@linkplain CompletionRequestor#setIgnored(int, boolean) setIgnored} and
	 * types set to be ignored in the preferences.
	 * </p>
	 *
	 * @param proposal the proposal to filter
	 * @return <code>true</code> to filter <code>proposal</code>,
	 *         <code>false</code> to let it pass
	 */
	protected boolean isFiltered(CompletionProposal proposal) {
		if (isIgnored(proposal.getKind()))
			return true;
		char[] declaringType= getDeclaringType(proposal);
		return declaringType!= null;
	}

	/**
	 * Returns the type signature of the declaring type of a
	 * <code>CompletionProposal</code>, or <code>null</code> for proposals
	 * that do not have a declaring type. The return value is <em>not</em>
	 * <code>null</code> for proposals of the following kinds:
	 * <ul>
	 * <li>METHOD_DECLARATION</li>
	 * <li>METHOD_NAME_REFERENCE</li>
	 * <li>METHOD_REF</li>
	 * <li>ANNOTATION_ATTRIBUTE_REF</li>
	 * <li>POTENTIAL_METHOD_DECLARATION</li>
	 * <li>ANONYMOUS_CLASS_DECLARATION</li>
	 * <li>FIELD_REF</li>
	 * <li>PACKAGE_REF (returns the package, but no type)</li>
	 * <li>TYPE_REF</li>
	 * </ul>
	 *
	 * @param proposal the completion proposal to get the declaring type for
	 * @return the type signature of the declaring type, or <code>null</code> if there is none
	 * @see Signature#toCharArray(char[])
	 */
	protected final char[] getDeclaringType(CompletionProposal proposal) {
		switch (proposal.getKind()) {
			case CompletionProposal.METHOD_DECLARATION:
			case CompletionProposal.METHOD_NAME_REFERENCE:
			case CompletionProposal.JAVADOC_METHOD_REF:
			case CompletionProposal.METHOD_REF:
			case CompletionProposal.METHOD_REF_WITH_CASTED_RECEIVER:
			case CompletionProposal.ANNOTATION_ATTRIBUTE_REF:
			case CompletionProposal.POTENTIAL_METHOD_DECLARATION:
			case CompletionProposal.ANONYMOUS_CLASS_DECLARATION:
			case CompletionProposal.FIELD_REF:
			case CompletionProposal.FIELD_REF_WITH_CASTED_RECEIVER:
			case CompletionProposal.JAVADOC_FIELD_REF:
			case CompletionProposal.JAVADOC_VALUE_REF:
			case CompletionProposal.PACKAGE_REF:
				return proposal.getDeclarationSignature();
			case CompletionProposal.JAVADOC_TYPE_REF:
			case CompletionProposal.TYPE_REF:
			case CompletionProposal.LOCAL_VARIABLE_REF:
			case CompletionProposal.VARIABLE_DECLARATION:
			case CompletionProposal.KEYWORD:
			case CompletionProposal.LABEL_REF:
			case CompletionProposal.JAVADOC_BLOCK_TAG:
			case CompletionProposal.JAVADOC_INLINE_TAG:
			case CompletionProposal.JAVADOC_PARAM_REF:
				return null;
			default:
				Assert.isTrue(false);
				return null;
		}
	}

	private IModelicaCompletionProposal createAnnotationAttributeReferenceProposal(CompletionProposal proposal) {
		return null;
	}

	private IModelicaCompletionProposal createFieldProposal(CompletionProposal proposal) {
		return null;
	}

	/**
	 * Creates the Modelica completion proposal for the JDT Core
	 * {@link CompletionProposal#FIELD_REF_WITH_CASTED_RECEIVER} proposal.
	 *
	 * @param proposal the JDT Core proposal
	 * @return the Modelica completion proposal
	 * @since 3.4
	 */

	private IModelicaCompletionProposal createModelicadocSimpleProposal(CompletionProposal javadocProposal) {
		// TODO do better with javadoc proposals
//		String completion= String.valueOf(proposal.getCompletion());
//		int start= proposal.getReplaceStart();
//		int length= getLength(proposal);
//		String label= fLabelProvider.createSimpleLabel(proposal);
//		Image image= getImage(fLabelProvider.createImageDescriptor(proposal));
//		int relevance= computeRelevance(proposal);
//
//		ModelicaCompletionProposal javaProposal= new ModelicaCompletionProposal(completion, start, length, image, label, relevance);
//		if (fModelicaProject != null)
//			javaProposal.setProposalInfo(new FieldProposalInfo(fModelicaProject, proposal));
//
//		javaProposal.setTriggerCharacters(VAR_TRIGGER);
//
//		return javaProposal;
		ModelicaCompletionProposal proposal = new ModelicaCompletionProposal(javadocProposal, getInvocationContext());
//		adaptLength(proposal, javadocProposal);
		return proposal;
	}

	private IModelicaCompletionProposal createModelicadocInlineTagProposal(CompletionProposal javadocProposal) {
		ModelicaCompletionProposal proposal= new ModelicaCompletionProposal(javadocProposal, getInvocationContext());
		adaptLength(proposal, javadocProposal);
		return proposal;
	}

	private IModelicaCompletionProposal createKeywordProposal(CompletionProposal proposal) {
		String completion= String.valueOf(proposal.getCompletion());
		int start= proposal.getReplaceStart();
		int length= getLength(proposal);
		StyledString label= null; //fLabelProvider.createSimpleLabel(proposal);
		int relevance= computeRelevance(proposal);
		return new ModelicaCompletionProposal(completion, start, length, null, label, relevance);
	}

	private IModelicaCompletionProposal createLabelProposal(CompletionProposal proposal) {
		String completion= String.valueOf(proposal.getCompletion());
		int start= proposal.getReplaceStart();
		int length= getLength(proposal);
		StyledString label= null; // fLabelProvider.createSimpleLabel(proposal);
		int relevance= computeRelevance(proposal);

		return new ModelicaCompletionProposal(completion, start, length, null, label, relevance);
	}

	private IModelicaCompletionProposal createLocalVariableProposal(CompletionProposal proposal) {
		String completion= String.valueOf(proposal.getCompletion());
		int start= proposal.getReplaceStart();
		int length= getLength(proposal);
		Image image= null; // getImage(fLabelProvider.createLocalImageDescriptor(proposal));
		StyledString label= null; // fLabelProvider.createSimpleLabelWithType(proposal);
		int relevance= computeRelevance(proposal);

		final ModelicaCompletionProposal javaProposal= new ModelicaCompletionProposal(completion, start, length, image, label, relevance);
		javaProposal.setTriggerCharacters(VAR_TRIGGER);
		return javaProposal;
	}


	private IModelicaCompletionProposal createMethodReferenceProposal(CompletionProposal methodProposal) {
		ModelicaCompletionProposal proposal= new ModelicaCompletionProposal(methodProposal, getInvocationContext());
		adaptLength(proposal, methodProposal);
		return proposal;
	}

	private void adaptLength(ModelicaCompletionProposal proposal, CompletionProposal coreProposal) {
		if (fUserReplacementLength != -1) {
			proposal.setReplacementLength(getLength(coreProposal));
		}
	}

	private IModelicaCompletionProposal createPackageProposal(CompletionProposal proposal) {
		String completion= String.valueOf(proposal.getCompletion());
		int start= proposal.getReplaceStart();
		int length= getLength(proposal);
		StyledString label= null; // fLabelProvider.createSimpleLabel(proposal);
		Image image= null; // getImage(fLabelProvider.createPackageImageDescriptor(proposal));
		int relevance= computeRelevance(proposal);

		return new ModelicaCompletionProposal(completion, start, length, image, label, relevance);
	}

	private IModelicaCompletionProposal createTypeProposal(CompletionProposal typeProposal) {
		ModelicaCompletionProposal proposal= new ModelicaCompletionProposal(typeProposal, getInvocationContext());
		adaptLength(proposal, typeProposal);
		return proposal;
	}

	private IModelicaCompletionProposal createModelicadocLinkTypeProposal(CompletionProposal typeProposal) {
		ModelicaCompletionProposal proposal= new ModelicaCompletionProposal(typeProposal, getInvocationContext());
		adaptLength(proposal, typeProposal);
		return proposal;
	}

}
