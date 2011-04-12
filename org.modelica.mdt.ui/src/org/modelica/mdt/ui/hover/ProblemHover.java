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
package org.modelica.mdt.ui.hover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;

import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;

import org.eclipse.ui.texteditor.MarkerAnnotation;
import org.eclipse.ui.texteditor.spelling.SpellingAnnotation;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.compiler.IProblem;
import org.modelica.mdt.internal.core.ModelicaFolder;
import org.modelica.mdt.internal.core.ModelicaSourceFile;
import org.modelica.mdt.ui.editor.EditorUtility;
import org.modelica.mdt.ui.text.modelica.IProblemLocation;

/**
 * This annotation hover shows the description of the
 * selected java annotation.
 *
 * XXX: Currently this problem hover only works for
 *		Java and spelling problems.
 *		see: https://bugs.eclipse.org/bugs/show_bug.cgi?id=62081
 *
 * @since 3.0
 */
public class ProblemHover extends AbstractAnnotationHover {

	protected static class ProblemInfo extends AnnotationInfo {

		private static final ICompletionProposal[] NO_PROPOSALS= new ICompletionProposal[0];

		public ProblemInfo(Annotation annotation, Position position, ITextViewer textViewer) {
			super(annotation, position, textViewer);
		}

		/*
		 * @see org.eclipse.jdt.internal.ui.text.java.hover.AbstractAnnotationHover.AnnotationInfo#getCompletionProposals()
		 */
		public ICompletionProposal[] getCompletionProposals() {

			if (annotation instanceof MarkerAnnotation)
				return getMarkerAnnotationFixes((MarkerAnnotation) annotation);

			return NO_PROPOSALS;
		}


		private static boolean hasProblem(IProblem[] problems, IProblemLocation location) {
//			for (int i= 0; i < problems.length; i++) {
//				IProblem problem= problems[i];
//				if (problem.getID() == location.getProblemId() && problem.getSourceStart() == location.getOffset())
//					return true;
//			}
			return false;
		}

		private ICompletionProposal[] getMarkerAnnotationFixes(MarkerAnnotation markerAnnotation) {
			if (markerAnnotation.isQuickFixableStateSet() && !markerAnnotation.isQuickFixable())
				return NO_PROPOSALS;

			IMarker marker= markerAnnotation.getMarker();

			IModelicaSourceFile cu= getCompilationUnit(marker);
			if (cu == null)
				return NO_PROPOSALS;

//			IEditorInput input= EditorUtility.getEditorInput(cu);
//			if (input == null)
//				return NO_PROPOSALS;

			IAnnotationModel model= ((ISourceViewer)viewer).getAnnotationModel();
			if (model == null)
				return NO_PROPOSALS;

			ISourceViewer sourceViewer= null;
			if (viewer instanceof ISourceViewer)
				sourceViewer= (ISourceViewer) viewer;

			ArrayList proposals= new ArrayList();
			return (ICompletionProposal[]) proposals.toArray(new ICompletionProposal[proposals.size()]);
		}

		private static IModelicaSourceFile getCompilationUnit(IMarker marker) {
			IResource res= marker.getResource();
			if (res instanceof IFile && res.isAccessible()) {
				IModelicaElement element= new ModelicaSourceFile((ModelicaFolder)res.getParent(), (IFile)res);
				if (element instanceof IModelicaSourceFile)
					return (IModelicaSourceFile) element;
			}
			return null;
		}

		/*
		 * @see org.eclipse.jdt.internal.ui.text.java.hover.AbstractAnnotationHover.AnnotationInfo#fillToolBar(org.eclipse.jface.action.ToolBarManager)
		 */
		public void fillToolBar(ToolBarManager manager, IInformationControl infoControl) {
			super.fillToolBar(manager, infoControl);
		}

	}

	public ProblemHover() {
		super(false);
	}

	protected AnnotationInfo createAnnotationInfo(Annotation annotation, Position position, ITextViewer textViewer) {
		return new ProblemInfo(annotation, position, textViewer);
	}
}
