package org.modelica.uml.sysml.diagram2.part;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.DemultiplexingListener;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.DiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.DiagramModificationListener;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.document.FileDiagramDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.document.FileDiagramModificationListener;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IFileEditorInput;

/**
 * @generated
 */
public class SysmlDocumentProvider extends FileDiagramDocumentProvider {

	/**
	 * @generated
	 */
	protected void saveDocumentToFile(IDocument document, IFile file,
			boolean overwrite, IProgressMonitor monitor) throws CoreException {
		Diagram diagram = (Diagram) document.getContent();
		Resource diagramResource = diagram.eResource();
		IDiagramDocument diagramDocument = (IDiagramDocument) document;
		TransactionalEditingDomain domain = diagramDocument.getEditingDomain();
		List resources = domain.getResourceSet().getResources();

		monitor.beginTask("Saving diagram", resources.size() + 1); //$NON-NLS-1$
		super.saveDocumentToFile(document, file, overwrite,
				new SubProgressMonitor(monitor, 1));
		for (Iterator it = resources.iterator(); it.hasNext();) {
			Resource nextResource = (Resource) it.next();
			monitor.setTaskName("Saving " + nextResource.getURI()); //$NON-NLS-1$
			if (nextResource != diagramResource && nextResource.isLoaded()) {
				try {
					nextResource.save(Collections.EMPTY_MAP);
				} catch (IOException e) {
					SysmlDiagramEditorPlugin
							.getInstance()
							.logError(
									"Unable to save resource: " + nextResource.getURI(), e); //$NON-NLS-1$
				}
			}
			monitor.worked(1);
		}
		monitor.done();
	}

	/**
	 * @generated
	 */
	protected ISchedulingRule getSaveRule(Object element) {
		IDiagramDocument diagramDocument = getDiagramDocument(element);
		if (diagramDocument != null) {
			Diagram diagram = diagramDocument.getDiagram();
			if (diagram != null) {
				Collection rules = new ArrayList();
				for (Iterator it = diagramDocument.getEditingDomain()
						.getResourceSet().getResources().iterator(); it
						.hasNext();) {
					IFile nextFile = WorkspaceSynchronizer
							.getFile((Resource) it.next());
					if (nextFile != null) {
						rules.add(computeSaveSchedulingRule(nextFile));
					}
				}
				return new MultiRule((ISchedulingRule[]) rules
						.toArray(new ISchedulingRule[rules.size()]));
			}
		}
		return super.getSaveRule(element);
	}

	/**
	 * @generated
	 */
	protected FileInfo createFileInfo(IDocument document,
			FileSynchronizer synchronizer, IFileEditorInput input) {
		assert document instanceof DiagramDocument;

		DiagramModificationListener diagramListener = new CustomModificationListener(
				this, (DiagramDocument) document, input);
		DiagramFileInfo info = new DiagramFileInfo(document, synchronizer,
				diagramListener);

		diagramListener.startListening();
		return info;
	}

	/**
	 * @generated
	 */
	private ISchedulingRule computeSaveSchedulingRule(IResource toCreateOrModify) {
		if (toCreateOrModify.exists()
				&& toCreateOrModify.isSynchronized(IResource.DEPTH_ZERO))
			return fResourceRuleFactory.modifyRule(toCreateOrModify);

		IResource parent = toCreateOrModify;
		do {
			/*
			 * XXX This is a workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=67601
			 * IResourceRuleFactory.createRule should iterate the hierarchy itself.
			 */
			toCreateOrModify = parent;
			parent = toCreateOrModify.getParent();
		} while (parent != null && !parent.exists()
				&& !parent.isSynchronized(IResource.DEPTH_ZERO));

		return fResourceRuleFactory.createRule(toCreateOrModify);
	}

	/**
	 * @generated
	 */
	private class CustomModificationListener extends
			FileDiagramModificationListener {

		/**
		 * @generated
		 */
		private DemultiplexingListener myListener = null;

		/**
		 * @generated
		 */
		public CustomModificationListener(
				SysmlDocumentProvider documentProviderParameter,
				DiagramDocument documentParameter,
				IFileEditorInput inputParameter) {
			super(documentProviderParameter, documentParameter, inputParameter);
			final DiagramDocument document = documentParameter;
			NotificationFilter diagramResourceModifiedFilter = NotificationFilter
					.createEventTypeFilter(Notification.SET);
			myListener = new DemultiplexingListener(
					diagramResourceModifiedFilter) {
				protected void handleNotification(
						TransactionalEditingDomain domain,
						Notification notification) {
					if (notification.getNotifier() instanceof EObject) {
						Resource modifiedResource = ((EObject) notification
								.getNotifier()).eResource();
						if (modifiedResource != document.getDiagram()
								.eResource()) {
							document.setContent(document.getContent());
						}
					}

				}
			};
		}

		/**
		 * @generated
		 */
		public void startListening() {
			super.startListening();
			getEditingDomain().addResourceSetListener(myListener);
		}

		/**
		 * @generated
		 */
		public void stopListening() {
			getEditingDomain().removeResourceSetListener(myListener);
			super.stopListening();
		}

	}

}
