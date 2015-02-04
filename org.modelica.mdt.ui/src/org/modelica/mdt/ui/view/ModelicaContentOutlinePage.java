/******************************************************************************
 * @author Adrian Pop [adrpo@ida.liu.se, http://www.ida.liu.se/~adrpo]
 * Copyright (c) 2006 Adrian Pop [adrpo@ida.liu.se]
 * All rights reserved.
 * Permission to use, copy, modify, and distribute this software and
 * its documentation for NON-COMMERCIAL purposes and without fee is hereby
 * granted, provided that this copyright notice appear in all copies and
 * that both the copyright notice and this permission notice and warranty
 * disclaimer appear in supporting documentation, and that the name of
 * The Author not be used in advertising or publicity pertaining to
 * distribution of the software without specific, written prior permission.
 *
 * COMMERCIAL use, copy, modification and distribution
 * is NOT permitted without prior written agreement with
 * Adrian Pop [adrpo@ida.liu.se].
 *
 * THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE,
 * INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS
 * OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE
 * OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
 *****************************************************************************/
package org.modelica.mdt.ui.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.IShowInSource;
import org.eclipse.ui.part.IShowInTarget;
import org.eclipse.ui.part.IShowInTargetList;
import org.eclipse.ui.part.ShowInContext;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.IUpdate;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.modelica.mdt.core.IDefinitionLocation;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaElement.Visibility;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChangeListener;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaImport;
import org.modelica.mdt.core.IParameter;
import org.modelica.mdt.core.IParent;
import org.modelica.mdt.core.ISignature;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.CorePlugin;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.internal.core.ModelicaComponent;
import org.modelica.mdt.ui.ModelicaElementChangeListener;
import org.modelica.mdt.ui.ModelicaElementComparator;
import org.modelica.mdt.ui.ModelicaImages;
import org.modelica.mdt.ui.UIPlugin;
import org.modelica.mdt.ui.actions.AbstractToggleLinkingAction;
import org.modelica.mdt.ui.actions.CompositeActionGroup;
import org.modelica.mdt.ui.actions.CustomFiltersActionGroup;
import org.modelica.mdt.ui.actions.MemberFilterActionGroup;
import org.modelica.mdt.ui.editor.EditorUtility;
import org.modelica.mdt.ui.editor.ModelicaEditor;

public class ModelicaContentOutlinePage extends ContentOutlinePage {
	static Object[] NO_CHILDREN= new Object[0];
	static IPreferenceStore prefStore = UIPlugin.getDefault().getPreferenceStore();

	ModelicaEditor fEditor = null;

	private IModelicaElement fInput;
	private String fContextMenuID;
	private Menu fMenu;
	private ModelicaOutlineViewer fOutlineViewer;

	private MemberFilterActionGroup fMemberFilterActionGroup;

	private ListenerList fSelectionChangedListeners= new ListenerList(ListenerList.IDENTITY);
	private ListenerList fPostSelectionChangedListeners= new ListenerList(ListenerList.IDENTITY);
	private Hashtable<String, IAction> fActions= new Hashtable<String, IAction>();

	private ToggleLinkingAction fToggleLinkingAction;

	private CompositeActionGroup fActionGroups;

	private IPreferenceStore fCorePreferenceStore = CorePlugin.getDefault().getPreferenceStore();

	/**
	 * Custom filter action group.
	 * @since 0.6.8
	 */
	private CustomFiltersActionGroup fCustomFiltersActionGroup;

	public void setInput(IModelicaElement inputElement) {
		fInput= inputElement;
		if (fOutlineViewer != null) {
			fOutlineViewer.setInput(fInput);
			updateSelectionProvider(getSite());
		}
	}


	/**
	 * Content provider for the children of an IModelicaFile
	 * @see ITreeContentProvider
	 */
	class ChildrenProvider implements ITreeContentProvider {

		private IModelicaElementChangeListener fListener;

		protected boolean matches(IModelicaElement element) {
			if (element instanceof IModelicaComponent) {
				String name= element.getElementName();
				return (name != null);
			}
			return false;
		}

		@Override
		public Object[] getChildren(Object parent) {
			// TODO: Use a better name for this property and don't hard-code it here. Use modern-style enum.
			String filterProtectedProperty = "MemberFilterActionGroup.org.modelica.mdt.ui.ModelicaContentOutlinePage.1";
			boolean shouldFilterProtected = false;

			if (prefStore.contains(filterProtectedProperty)) {
				shouldFilterProtected = prefStore.getBoolean(filterProtectedProperty);
			}
			else {
				// We are not storing a preference for this filter, so we disable it, i.e., no filtering.
			}

			Object[] result = NO_CHILDREN;

			if (parent instanceof IParent) {
				List<IModelicaElement> filteredChildren = new ArrayList<IModelicaElement>();

				try {
					if (parent instanceof IModelicaClass && ((IModelicaClass)parent).getRestriction() == IModelicaClass.Restriction.FUNCTION) {
						IModelicaClass modelicaFunction = (IModelicaClass)parent;
						Collection<? extends IModelicaElement> children = modelicaFunction.getChildren();
						ISignature signature = modelicaFunction.getSignature();
						IParameter[] inputs = signature.getInputs();
						IParameter[] outputs = signature.getOutputs();

						for (IModelicaElement child : children) {
							if (child instanceof IModelicaComponent) {
								IModelicaComponent component = (IModelicaComponent)child;
								IModelicaComponent newComponent = createDecoratedComponent(modelicaFunction, component, inputs, outputs);
								filteredChildren.add(newComponent);
							}
						}

						result = filteredChildren.toArray();
					}
					else {
						Collection<? extends IModelicaElement> children = ((IParent)parent).getChildren();


						for (IModelicaElement el : children) {
							if (shouldFilterProtected && el.getVisibility() == IModelicaElement.Visibility.PROTECTED) {
								// Filter out this protected item.
							}
							else {
								filteredChildren.add(el);
							}
						}

						result = filteredChildren.toArray();
					}
				}
				catch (Exception x) {
					x.printStackTrace();
				}
			}

			return result;
		}

		@Override
		public Object[] getElements(Object parent) {
			return getChildren(parent);
		}

		@Override
		public Object getParent(Object child) {
			if (child instanceof IModelicaElement) {
				IModelicaElement e= (IModelicaElement) child;
				return e.getParent();
			}
			return null;
		}

		@Override
		public boolean hasChildren(Object parent) {
			if (parent instanceof IParent) {
				IParent c= (IParent) parent;
				try {
					return c.hasChildren();
				}
				catch (Exception x)
				{
					// do nothing
					x.printStackTrace();
				}
			}
			return false;
		}

		public boolean isDeleted(Object o) {
			return false;
		}

		@Override
		public void dispose() {
			if (fListener != null) {
				fListener= null;
			}
		}

		/*
		 * @see IContentProvider#inputChanged(Viewer, Object, Object)
		 */
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			boolean isMO = (newInput instanceof IModelicaFile);

			if (isMO && fListener == null) {
				fListener= new ModelicaElementChangeListener(true);
				fListener.setViewer(viewer);
				ModelicaCore.getModelicaRoot().addModelicaElementChangeListener(fListener);
			}
			else if (!isMO && fListener != null) {
				ModelicaCore.getModelicaRoot().removeModelicaElementChangeListener(fListener);
				fListener= null;
			}
		}

		private IModelicaComponent createDecoratedComponent(IModelicaClass parent, IModelicaComponent modelicaComponent, IParameter[] inputs, IParameter[] outputs)
				throws ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException, CoreException {
			final String name = modelicaComponent.getElementName();
			final String typeName = modelicaComponent.getTypeName();
			final Visibility visibility = modelicaComponent.getVisibility();
			final IDefinitionLocation definitionLocation = modelicaComponent.getLocation();

			final boolean matchedAsIn = searchParameterArray(inputs, name, typeName);
			final boolean matchedAsOut = searchParameterArray(outputs, name, typeName);

			String decoratedName = name;

			if (matchedAsIn && !matchedAsOut) {
				decoratedName = name + " (" + typeName + " - IN)";
			}
			else if (!matchedAsIn && matchedAsOut) {
				decoratedName = name + " (" + typeName + " - OUT)";
			}
			else if (!matchedAsIn && !matchedAsOut) {
				// ErrorManager.logWarning("That's odd, that parameter did not match as being neither in nor out.");
			}
			else { // if matchedAsIn && matchedAsOut
				ErrorManager.logWarning("That's odd, that parameter matched as being both in and out.");
			}

			IModelicaComponent newComponent = new ModelicaComponent(parent, decoratedName, typeName, visibility, definitionLocation);

			return newComponent;
		}

		private boolean searchParameterArray(IParameter[] params, String name, String typeName) {
			boolean matched = false;

			for (IParameter parameter : params) {
				String parameterName = parameter.getName();
				if (name.equals(parameterName)) {
					String parameterTypeName = parameter.getType();
					if (!typeName.equals(parameterTypeName)) {
						ErrorManager.logWarning("That's odd, I found match for parameter name but the type was different.");
					}

					matched = true;
					break;
				}
			}

			return matched;
		}
	}

	class ModelicaOutlineViewer extends TreeViewer {

		/**
		 * Indicates an item which has been reused. At the point of
		 * its reuse it has been expanded. This field is used to
		 * communicate between <code>internalExpandToLevel</code> and
		 * <code>reuseTreeItem</code>.
		 */
		private Item fReusedExpandedItem;
		private boolean fReorderedMembers;
		private boolean fForceFireSelectionChanged;

		public ModelicaOutlineViewer(Tree tree) {
			super(tree);
			setAutoExpandLevel(2);
			setUseHashlookup(true);
		}

		/**
		 * Investigates the given element change event and if affected
		 * incrementally updates the Modelica outline.
		 *
		 * @param delta the Modelica element delta used to reconcile the Modelica outline
		 */
		public void reconcile(IModelicaElementChange delta) {
			fReorderedMembers= false;
			fForceFireSelectionChanged= false;
			if (getSorter() == null) {
				Widget w= findItem(fInput);
				if (w != null && !w.isDisposed()) {
					update(w, delta);
				}
				if (fForceFireSelectionChanged) {
					fireSelectionChanged(new SelectionChangedEvent(getSite().getSelectionProvider(), this.getSelection()));
				}
				if (fReorderedMembers) {
					refresh(true);
					fReorderedMembers= false;
				}
			}
			else {
				// just for now
				refresh(true);
			}
		}

		/*
		 * @see TreeViewer#internalExpandToLevel
		 */
		@Override
		protected void internalExpandToLevel(Widget node, int level) {
			if (node instanceof Item) {
				Item i= (Item) node;
				if (i.getData() instanceof IModelicaElement) {
					IModelicaElement je= (IModelicaElement) i.getData();
					if (je instanceof IModelicaImport || isInnerType(je)) {
						if (i != fReusedExpandedItem) {
							setExpanded(i, false);
							return;
						}
					}
				}
			}

			super.internalExpandToLevel(node, level);
		}

		protected void reuseTreeItem(Item item, Object element) {
			// remove children
			Item[] c = getChildren(item);

			if (c != null && c.length > 0) {
				if (getExpanded(item)) {
					fReusedExpandedItem= item;
				}

				for (int k= 0; k < c.length; k++) {
					if (c[k].getData() != null) {
						disassociate(c[k]);
					}
					c[k].dispose();
				}
			}

			updateItem(item, element);
			updatePlus(item, element);
			internalExpandToLevel(item, ALL_LEVELS);

			fReusedExpandedItem= null;
			fForceFireSelectionChanged= true;
		}

		protected boolean mustUpdateParent(IModelicaElementChange delta, IModelicaElement element) {
			if (element instanceof IModelicaComponent) {
				if (delta.getChangeType() == IModelicaElementChange.ChangeType.ADDED) {
					return true;
				}
			}
			return false;
		}

		/*
		 * @see org.eclipse.jface.viewers.AbstractTreeViewer#isExpandable(java.lang.Object)
		 */
		@Override
		public boolean isExpandable(Object element) {
			if (hasFilters()) {
				return getFilteredChildren(element).length > 0;
			}
			return super.isExpandable(element);
		}

		protected boolean overlaps(IRegion range, int start, int end) {
			return start <= (range.getOffset() + range.getLength() - 1) && range.getOffset() <= end;
		}

		protected boolean filtered(IModelicaElement parent, IModelicaElement child) {
			Object[] result= new Object[] { child };
			ViewerFilter[] filters= getFilters();
			for (int i= 0; i < filters.length; i++) {
				result= filters[i].filter(this, parent, result);
				if (result.length == 0)
					return true;
			}
			return false;
		}

		protected void update(Widget w, IModelicaElementChange delta) {
			boolean doUpdateParent= false;
			boolean doUpdateParentsPlus= false;

			if (doUpdateParent) {
				updateItem(w, delta.getElement());
			}
			if (!doUpdateParent && doUpdateParentsPlus && w instanceof Item) {
				updatePlus((Item)w, delta.getElement());
			}
		}
	}

	class LexicalSortingAction extends Action {

		private ModelicaElementComparator fComparator= new ModelicaElementComparator(ModelicaElementComparator.NAME);
		private ModelicaElementComparator fModelicaComparator= new ModelicaElementComparator(ModelicaElementComparator.MODELICA);

		public LexicalSortingAction() {
			setText("Sort");
			setImageDescriptor(ModelicaImages.getImageDescriptor(ModelicaImages.IMG_OUTLINE_SORT));
			setToolTipText("Sort");
			setDescription("Sorting lables");
			boolean checked= fCorePreferenceStore.getBoolean("ModelicaEditor.Outline.LexicalSortingAction.isChecked"); //$NON-NLS-1$
			valueChanged(checked, false);
		}

		@Override
		public void run() {
			valueChanged(isChecked(), true);
		}

		private void valueChanged(final boolean on, boolean store) {
			setChecked(on);
			BusyIndicator.showWhile(fOutlineViewer.getControl().getDisplay(), new Runnable() {
				@Override
				public void run() {
					if (on) {
						fOutlineViewer.setComparator(fComparator);
					}
					else {
						fOutlineViewer.setComparator(fModelicaComparator);
					}
				}
			});

			if (store) {
				fCorePreferenceStore.setValue("ModeilcaEditor.Outline.LexicalSortingAction.isChecked", on); //$NON-NLS-1$
			}
		}
	}

	/**
	 * This action toggles whether this Modelica Outline page links
	 * its selection to the active editor.
	 *
	 * @since 3.0
	 */
	public class ToggleLinkingAction extends AbstractToggleLinkingAction {

		ModelicaContentOutlinePage fModelicaOutlinePage;

		/**
		 * Constructs a new action.
		 *
		 * @param outlinePage the Modelica outline page
		 */
		public ToggleLinkingAction(ModelicaContentOutlinePage outlinePage) {
			boolean isLinkingEnabled= fCorePreferenceStore.getBoolean("ModelicaEditor.SyncOutlineOnCursorMove");
			setChecked(isLinkingEnabled);
			fModelicaOutlinePage= outlinePage;
		}

		/**
		 * Runs the action.
		 */
		@Override
		public void run() {
			fCorePreferenceStore.setValue("ModelicaEditor.SyncOutlineOnCursorMove", isChecked());
			if (isChecked() && fEditor != null) {
				fEditor.synchronizeOutlinePage(fEditor.computeHighlightRangeSourceReference(), false);
			}
		}
	}

	private IPropertyChangeListener fPropertyChangeListener;

	public ModelicaContentOutlinePage(String contextMenuID, ModelicaEditor editor) {
		Assert.isNotNull(editor);

		fContextMenuID= contextMenuID;
		fEditor= editor;
		fPropertyChangeListener= new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				doPropertyChange(event);
			}
		};
		fCorePreferenceStore.addPropertyChangeListener(fPropertyChangeListener);
	}

	private void doPropertyChange(PropertyChangeEvent event) {
		if (fOutlineViewer != null) {
			// TODO! FIXME! update only if is a sort action! if (event.getProperty() ) {
			fOutlineViewer.refresh(false);
			//}
		}
	}

	/* (non-Modelicadoc)
	 * Method declared on Page
	 */
	@Override
	public void init(IPageSite pageSite) {
		super.init(pageSite);
	}

	/*
	 * @see ISelectionProvider#addSelectionChangedListener(ISelectionChangedListener)
	 */
	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (fOutlineViewer != null) {
			fOutlineViewer.addSelectionChangedListener(listener);
		}
		else {
			fSelectionChangedListeners.add(listener);
		}
	}

	/*
	 * @see ISelectionProvider#removeSelectionChangedListener(ISelectionChangedListener)
	 */
	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		if (fOutlineViewer != null) {
			fOutlineViewer.removeSelectionChangedListener(listener);
		}
		else {
			fSelectionChangedListeners.remove(listener);
		}
	}

	/*
	 * @see ISelectionProvider#setSelection(ISelection)
	 */
	@Override
	public void setSelection(ISelection selection) {
		if (fOutlineViewer != null) {
			fOutlineViewer.setSelection(selection);
		}
	}

	/*
	 * @see ISelectionProvider#getSelection()
	 */
	@Override
	public ISelection getSelection() {
		if (fOutlineViewer == null) {
			return StructuredSelection.EMPTY;
		}

		return fOutlineViewer.getSelection();
	}

	/*
	 * @see org.eclipse.jface.text.IPostSelectionProvider#addPostSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addPostSelectionChangedListener(ISelectionChangedListener listener) {
		if (fOutlineViewer != null) {
			fOutlineViewer.addPostSelectionChangedListener(listener);
		}
		else {
			fPostSelectionChangedListeners.add(listener);
		}
	}

	/*
	 * @see org.eclipse.jface.text.IPostSelectionProvider#removePostSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removePostSelectionChangedListener(ISelectionChangedListener listener) {
		if (fOutlineViewer != null)
			fOutlineViewer.removePostSelectionChangedListener(listener);
		else
			fPostSelectionChangedListeners.remove(listener);
	}

	private void registerToolbarActions(IActionBars actionBars) {
		IToolBarManager toolBarManager= actionBars.getToolBarManager();
		toolBarManager.add(new LexicalSortingAction());

		fMemberFilterActionGroup= new MemberFilterActionGroup(fOutlineViewer, "org.modelica.mdt.ui.ModelicaContentOutlinePage"); //$NON-NLS-1$
		fMemberFilterActionGroup.contributeToToolBar(toolBarManager);

		fCustomFiltersActionGroup.fillActionBars(actionBars);

		IMenuManager viewMenuManager= actionBars.getMenuManager();
		viewMenuManager.add(new Separator("EndFilterGroup")); //$NON-NLS-1$

		fToggleLinkingAction= new ToggleLinkingAction(this);
		viewMenuManager.add(fToggleLinkingAction);
	}

	/*
	 * @see IPage#createControl
	 */
	@Override
	public void createControl(Composite parent) {
		Tree tree= new Tree(parent, SWT.MULTI);

		fOutlineViewer= new ModelicaOutlineViewer(tree);
		fOutlineViewer.setContentProvider(new ChildrenProvider());
		// use a non decorating label provider for outline!
		fOutlineViewer.setLabelProvider(new WorkbenchLabelProvider());

		Object[] listeners= fSelectionChangedListeners.getListeners();
		for (int i= 0; i < listeners.length; i++) {
			fSelectionChangedListeners.remove(listeners[i]);
			fOutlineViewer.addSelectionChangedListener((ISelectionChangedListener) listeners[i]);
		}

		listeners= fPostSelectionChangedListeners.getListeners();
		for (int i= 0; i < listeners.length; i++) {
			fPostSelectionChangedListeners.remove(listeners[i]);
			fOutlineViewer.addPostSelectionChangedListener((ISelectionChangedListener) listeners[i]);
		}

		MenuManager manager= new MenuManager(fContextMenuID, fContextMenuID);
		manager.setRemoveAllWhenShown(true);
		manager.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager m) {
				contextMenuAboutToShow(m);
			}
		});
		fMenu= manager.createContextMenu(tree);
		tree.setMenu(fMenu);

		IPageSite site= getSite();
		site.registerContextMenu("org.modelica.mdt.ui.ModelicaContentOutlinePage", manager, fOutlineViewer); //$NON-NLS-1$

		updateSelectionProvider(site);

		// we must create the groups after we have set the selection provider to the site
		fActionGroups= new CompositeActionGroup(new ActionGroup[] {});

		// register global actions
		IActionBars actionBars= site.getActionBars();
		actionBars.setGlobalActionHandler(ITextEditorActionConstants.UNDO, fEditor.getAction(ITextEditorActionConstants.UNDO));
		actionBars.setGlobalActionHandler(ITextEditorActionConstants.REDO, fEditor.getAction(ITextEditorActionConstants.REDO));

		IAction action= fEditor.getAction(ITextEditorActionConstants.NEXT);
		actionBars.setGlobalActionHandler(ITextEditorActionDefinitionIds.GOTO_NEXT_ANNOTATION, action);
		actionBars.setGlobalActionHandler(ITextEditorActionConstants.NEXT, action);
		action= fEditor.getAction(ITextEditorActionConstants.PREVIOUS);
		actionBars.setGlobalActionHandler(ITextEditorActionDefinitionIds.GOTO_PREVIOUS_ANNOTATION, action);
		actionBars.setGlobalActionHandler(ITextEditorActionConstants.PREVIOUS, action);

		fActionGroups.fillActionBars(actionBars);

		// Custom filter group
		fCustomFiltersActionGroup= new CustomFiltersActionGroup("org.modelica.mdt.ui.ModelicaContentOutlinePage", fOutlineViewer); //$NON-NLS-1$

		registerToolbarActions(actionBars);

		fOutlineViewer.setInput(fInput);
	}

	/*
	 * @since 0.6.8
	 */
	private void updateSelectionProvider(IPageSite site) {
		ISelectionProvider provider= fOutlineViewer;
		if (fInput != null) {
			// TODO! provider= new EmptySelectionProvider();
		}
		site.setSelectionProvider(provider);
	}

	@Override
	public void dispose() {
		if (fEditor == null) {
			return;
		}

		if (fMemberFilterActionGroup != null) {
			fMemberFilterActionGroup.dispose();
			fMemberFilterActionGroup= null;
		}

		if (fCustomFiltersActionGroup != null) {
			fCustomFiltersActionGroup.dispose();
			fCustomFiltersActionGroup= null;
		}

		fEditor.outlinePageClosed();
		fEditor= null;

		fSelectionChangedListeners.clear();
		fSelectionChangedListeners= null;

		fPostSelectionChangedListeners.clear();
		fPostSelectionChangedListeners= null;

		if (fMenu != null && !fMenu.isDisposed()) {
			fMenu.dispose();
			fMenu= null;
		}

		if (fActionGroups != null)
			fActionGroups.dispose();

		fOutlineViewer= null;

		super.dispose();
	}

	@Override
	public Control getControl() {
		if (fOutlineViewer != null) {
			return fOutlineViewer.getControl();
		}

		return null;
	}

	public void select(IModelicaElement reference) {
		if (fOutlineViewer != null) {
			ISelection s= fOutlineViewer.getSelection();
			if (s instanceof IStructuredSelection) {
				IStructuredSelection ss= (IStructuredSelection) s;
				@SuppressWarnings("rawtypes")
				List elements= ss.toList();
				if (!elements.contains(reference)) {
					s= (reference == null ? StructuredSelection.EMPTY : new StructuredSelection(reference));
					fOutlineViewer.setSelection(s, true);
				}
			}
		}
	}

	public void setAction(String actionID, IAction action) {
		Assert.isNotNull(actionID);
		if (action == null) {
			fActions.remove(actionID);
		}
		else {
			fActions.put(actionID, action);
		}
	}

	public IAction getAction(String actionID) {
		Assert.isNotNull(actionID);
		return fActions.get(actionID);
	}

	/*
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	// FIXME: The three top-level conditions test the same thing and all three returns from the function.
	public Object getAdapter(@SuppressWarnings("rawtypes") Class key) {
		if (key == IModelicaElement.class) {
			return getShowInSource();
		}
		if (key == IModelicaElement.class) {
			return new IShowInTargetList() {
				@Override
				public String[] getShowInTargetIds() {
					return new String[] { UIPlugin.ID_PROJECTSVIEW };
				}
			};
		}
		if (key == IModelicaElement.class) {
			return getShowInTarget();
		}

		return null;
	}

	/**
	 * Convenience method to add the action installed under the given actionID to the
	 * specified group of the menu.
	 *
	 * @param menu		the menu manager
	 * @param group		the group to which to add the action
	 * @param actionID	the ID of the new action
	 */
	protected void addAction(IMenuManager menu, String group, String actionID) {
		IAction action= getAction(actionID);
		if (action != null) {
			if (action instanceof IUpdate) {
				((IUpdate) action).update();
			}

			if (action.isEnabled()) {
				IMenuManager subMenu= menu.findMenuUsingPath(group);
				if (subMenu != null) {
					subMenu.add(action);
				}
				else {
					menu.appendToGroup(group, action);
				}
			}
		}
	}

	protected void contextMenuAboutToShow(IMenuManager menu) {
		UIPlugin.createStandardGroups(menu);

		IStructuredSelection selection= (IStructuredSelection)getSelection();
		fActionGroups.setContext(new ActionContext(selection));
		fActionGroups.fillContextMenu(menu);
	}

	/*
	 * @see Page#setFocus()
	 */
	@Override
	public void setFocus() {
		if (fOutlineViewer != null) {
			fOutlineViewer.getControl().setFocus();
		}
	}

	/**
	 * Checks whether a given Modelica element is an inner type.
	 *
	 * @param element the Modelica element
	 * @return <code>true</code> iff the given element is an inner type
	 */
	private boolean isInnerType(IModelicaElement element) {
		try {
			if (element != null && element instanceof IModelicaClass) {
				IModelicaClass modelicaClass = (IModelicaClass)element;
				IModelicaClass.Restriction restriction = modelicaClass.getRestriction();

				if (restriction == IModelicaClass.Restriction.TYPE || restriction == IModelicaClass.Restriction.UNIONTYPE) {
					return true;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Returns the <code>IShowInSource</code> for this view.
	 *
	 * @return the {@link IShowInSource}
	 */
	protected IShowInSource getShowInSource() {
		return new IShowInSource() {
			@Override
			public ShowInContext getShowInContext() {
				return new ShowInContext(null, getSite().getSelectionProvider().getSelection());
			}
		};
	}

	/**
	 * Returns the <code>IShowInTarget</code> for this view.
	 *
	 * @return the {@link IShowInTarget}
	 */
	protected IShowInTarget getShowInTarget() {
		return new IShowInTarget() {
			@Override
			public boolean show(ShowInContext context) {
				ISelection sel= context.getSelection();
				if (sel instanceof ITextSelection) {
					ITextSelection tsel= (ITextSelection) sel;
					int offset= tsel.getOffset();
					IModelicaElement element= fEditor.getElementAt(offset);
					if (element != null) {
						setSelection(new StructuredSelection(element));
						return true;
					}
				}
				else if (sel instanceof IStructuredSelection) {
					setSelection(sel);
					return true;
				}
				return false;
			}
		};
	}

	protected void handleOpen(OpenEvent event) {
		/*
		 * handle open action on a element in the project view tree,
		 * this is usually a double click on the element
		 */
		IStructuredSelection selection = (IStructuredSelection) event.getSelection();

		try {
			EditorUtility.openInEditor ((IModelicaElement)selection.getFirstElement());
		}
		catch (CompilerException e) {
			ErrorManager.showCompilerError(e);
		}
		catch (CoreException e) {
			ErrorManager.showCoreError(e);
		}
	}
}
