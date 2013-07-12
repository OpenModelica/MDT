/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.partialEditing;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.OverviewRuler;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.DefaultMarkerAnnotationAccess;
import org.eclipse.ui.texteditor.MarkerAnnotationPreferences;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.xtext.ui.editor.XtextSourceViewer;
import org.eclipse.xtext.ui.editor.XtextSourceViewerConfiguration;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.ui.editor.preferences.IPreferenceStoreAccess;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionProvider;
import org.eclipse.xtext.ui.editor.validation.AnnotationIssueProcessor;
import org.eclipse.xtext.ui.editor.validation.IValidationIssueProcessor;
import org.eclipse.xtext.ui.editor.validation.ValidationJob;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.common.collect.Iterators;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating SourceViewerHandle objects.
 *
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class SourceViewerHandleFactory {
	
	/** The source viewer factory. */
	@Inject 
	private XtextSourceViewer.Factory sourceViewerFactory;
	
	/** The source viewer configuration provider. */
	@Inject 
	private Provider<XtextSourceViewerConfiguration> sourceViewerConfigurationProvider;
	
	/** The document provider. */
	@Inject 
	private Provider<XtextDocument> documentProvider;
	
	/** The resource validator. */
	@Inject 
	private IResourceValidator resourceValidator;
	
	/** The preference store access. */
	@Inject
	private IPreferenceStoreAccess preferenceStoreAccess;
	
	/** The xtext injector. */
	private Injector xtextInjector ;
	
	/** The Constant VERTICAL_RULER_WIDTH. */
	protected static final int VERTICAL_RULER_WIDTH = 12;
	
	/**
	 * Creates the.
	 *
	 * @param parent the parent
	 * @param resourceProvider the resource provider
	 * @return SourceViewerHandle
	 */
	public SourceViewerHandle create(Composite parent, ISyntheticResourceProvider resourceProvider) {
		//final XtextSourceViewer viewer= sourceViewerFactory.createSourceViewer(parent, null, null, false, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);

		final IVerticalRuler ruler = new VerticalRuler(VERTICAL_RULER_WIDTH, new DefaultMarkerAnnotationAccess());
		final IOverviewRuler oRuler = new OverviewRuler(new DefaultMarkerAnnotationAccess(), VERTICAL_RULER_WIDTH, getSharedColors());
		//final XtextSourceViewer viewer = sourceViewerFactory.createSourceViewer(parent, ruler, null, true, SWT.None);
		final XtextSourceViewer viewer = sourceViewerFactory.createSourceViewer(parent, ruler, oRuler, true, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);

		XtextSourceViewerConfiguration viewerConfiguration = sourceViewerConfigurationProvider.get();
//		SourceViewerConfiguration configuration= new SourceViewerConfiguration() {
//			public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
//
//				ContentAssistant assistant= new ContentAssistant();
//				assistant.enableAutoActivation(true);
//				assistant.enableAutoInsert(true);
//				assistant.setContentAssistProcessor(fTemplateProcessor, IDocument.DEFAULT_CONTENT_TYPE);
//				return assistant;
//			}
//		};
		viewer.configure(viewerConfiguration);
		
		final SourceViewerDecorationSupport viewerDecorationSupport = new SourceViewerDecorationSupport(viewer, oRuler, new DefaultMarkerAnnotationAccess(), getSharedColors());
		MarkerAnnotationPreferences annotationPreferences = new MarkerAnnotationPreferences();
		Iterator<AnnotationPreference> e = Iterators.filter(annotationPreferences.getAnnotationPreferences().iterator(), AnnotationPreference.class);
		while (e.hasNext())
			viewerDecorationSupport.setAnnotationPreference(e.next());
		viewerDecorationSupport.install(preferenceStoreAccess.getPreferenceStore());
		parent.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				viewerDecorationSupport.dispose();
			}
		});
		final XtextDocument document = documentProvider.get();
		final SourceViewerHandle result = new SourceViewerHandle(document, viewer, viewerConfiguration, resourceProvider, xtextInjector);
		ValidationJob job = new ValidationJob(resourceValidator, document, 
				new IValidationIssueProcessor() {
					private AnnotationIssueProcessor annotationIssueProcessor;
					
					public void processIssues(List<Issue> issues, IProgressMonitor monitor) {
						IValidationIssueProcessor issueProcessor = result.getIssueProcessor();
						if (issueProcessor != null)
							issueProcessor.processIssues(issues, monitor);
						if (annotationIssueProcessor == null) {
							annotationIssueProcessor = new AnnotationIssueProcessor(document, 
									viewer.getAnnotationModel(), 
									new IssueResolutionProvider.NullImpl());
						}
						if (annotationIssueProcessor != null)
							annotationIssueProcessor.processIssues(issues, monitor);						
					}
				}, CheckMode.FAST_ONLY);
		document.setValidationJob(job);
		return result;
	}
	
	/**
	 * Gets the shared colors.
	 *
	 * @return the shared colors
	 */
	protected ISharedTextColors getSharedColors() {
		return EditorsUI.getSharedTextColors();
	}
}
