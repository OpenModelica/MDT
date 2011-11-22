/******************************************************************************
 * @author Adrian Pop [adrpo@ida.liu.se, http://www.ida.liu.se/~adrpo]
 * Copyright (c) 2002-2006, Adrian Pop [adrpo@ida.liu.se],
 * Programming Environments Laboratory (PELAB),
 * Department of Computer and Information Science (IDA), 
 * Linköpings University (LiU). 
 * All rights reserved.
 *
 * http://www.ida.liu.se/~adrpo/license/
 *
 * NON-COMMERCIAL terms and conditions [NON-COMMERCIAL setting]:
 * Permission to use, copy, modify, and distribute this software and
 * its documentation in source or binary form (including products 
 * developed or generated using this software) for NON-COMMERCIAL 
 * purposes and without fee is hereby granted, provided that this 
 * copyright notice appear in all copies and that both the copyright 
 * notice and this permission notice and warranty disclaimer appear 
 * in supporting documentation, and that the name of The Author is not 
 * to be used in advertising or publicity pertaining to distribution 
 * of the software without specific, prior written permission.
 * 
 * COMMERCIAL terms and conditions [COMMERCIAL setting]:
 * COMMERCIAL use, copy, modification and distribution in source 
 * or binary form (including products developed or generated using
 * this software) is NOT permitted without prior written agreement 
 * from Adrian Pop [adrpo@ida.liu.se].
 * 
 * THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE,
 * INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS
 * OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE
 * OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
 *****************************************************************************/
package org.modelica.mdt.ui.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;

import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionGroup;

import org.modelica.mdt.core.IModelicaRoot;
import org.modelica.mdt.ui.ModelicaImages;
import org.modelica.mdt.ui.UIPlugin;
import org.modelica.mdt.ui.filters.CustomFiltersDialog;
import org.modelica.mdt.ui.filters.FilterDescriptor;
import org.modelica.mdt.ui.filters.FilterMessages;
import org.modelica.mdt.ui.filters.NamePatternFilter;

/**
 * Action group to add the filter action to a view part's tool bar
 * menu.
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
 * @since 0.6.8
 */
public class CustomFiltersActionGroup extends ActionGroup {

	class ShowFilterDialogAction extends Action {
		ShowFilterDialogAction() {
			setText(FilterMessages.OpenCustomFiltersDialogAction_text); 
			setImageDescriptor(ModelicaImages.getImageDescriptor(ModelicaImages.IMG_OUTLINE_FILTER));
		}
		
		public void run() {
			openDialog();
		}
	}

	/**
	 * Menu contribution item which shows and lets check and uncheck filters.
	 * 
	 * @since 0.6.8
	 */
	class FilterActionMenuContributionItem extends ContributionItem {

		private int fItemNumber;
		private boolean fState;
		private String fFilterId;
		private String fFilterName;
		private CustomFiltersActionGroup fActionGroup;

		/**
		 * Constructor for FilterActionMenuContributionItem.
		 * 
		 * @param actionGroup 	the action group
		 * @param filterId		the id of the filter
		 * @param filterName	the name of the filter
		 * @param state			the initial state of the filter
		 * @param itemNumber	the menu item index
		 */
		public FilterActionMenuContributionItem(CustomFiltersActionGroup actionGroup, String filterId, String filterName, boolean state, int itemNumber) {
			super(filterId);
			Assert.isNotNull(actionGroup);
			Assert.isNotNull(filterId);
			Assert.isNotNull(filterName);
			fActionGroup= actionGroup;
			fFilterId= filterId;
			fFilterName= filterName;
			fState= state;
			fItemNumber= itemNumber;
		}

		/*
		 * Overrides method from ContributionItem.
		 */
		public void fill(Menu menu, int index) {
			MenuItem mi= new MenuItem(menu, SWT.CHECK, index);
			mi.setText("&" + fItemNumber + " " + fFilterName);  //$NON-NLS-1$  //$NON-NLS-2$
			mi.setSelection(fState);
			mi.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					fState= !fState;
					fActionGroup.setFilter(fFilterId, fState);
				}
			});
		}
	
		/*
		 * @see org.eclipse.jface.action.IContributionItem#isDynamic()
		 */
		public boolean isDynamic() {
			return true;
		}
	}

	private static final String TAG_CUSTOM_FILTERS = "customFilters"; //$NON-NLS-1$
	private static final String TAG_USER_DEFINED_PATTERNS_ENABLED= "userDefinedPatternsEnabled"; //$NON-NLS-1$
	private static final String TAG_USER_DEFINED_PATTERNS= "userDefinedPatterns"; //$NON-NLS-1$
	private static final String TAG_XML_DEFINED_FILTERS= "xmlDefinedFilters"; //$NON-NLS-1$
	private static final String TAG_LRU_FILTERS = "lastRecentlyUsedFilters"; //$NON-NLS-1$

	private static final String TAG_CHILD= "child"; //$NON-NLS-1$
	private static final String TAG_PATTERN= "pattern"; //$NON-NLS-1$
	private static final String TAG_FILTER_ID= "filterId"; //$NON-NLS-1$
	private static final String TAG_IS_ENABLED= "isEnabled"; //$NON-NLS-1$

	private static final String SEPARATOR= ",";  //$NON-NLS-1$

	private static final int MAX_FILTER_MENU_ENTRIES= 3;
	private static final String RECENT_FILTERS_GROUP_NAME= "recentFiltersGroup"; //$NON-NLS-1$

	private StructuredViewer fViewer;

	private NamePatternFilter fPatternFilter;
	private Map<String, ViewerFilter> fInstalledBuiltInFilters;
	
	private Map<String, Boolean> fEnabledFilterIds;
	private boolean fUserDefinedPatternsEnabled;
	private String[] fUserDefinedPatterns;
	private FilterDescriptor[] fCachedFilterDescriptors;

	/**
	 * Recently changed filter Ids stack with oldest on top (i.e. at the end).
	 *
	 * @since 0.6.8
	 */
	private Stack<String> fLRUFilterIdsStack; 
	/**
	 * Handle to menu manager to dynamically update
	 * the last recently used filters.
	 * 
	 * @since 0.6.8
	 */
	private IMenuManager fMenuManager;
	/**
	 * The menu listener which dynamically updates
	 * the last recently used filters.
	 * 
	 * @since 0.6.8
	 */
	private IMenuListener fMenuListener;
	/**
	 * Filter Ids used in the last view menu invocation.
	 * 
	 * @since 0.6.8
	 */
	private String[] fFilterIdsUsedInLastViewMenu;
	private Map<String, FilterDescriptor> fFilterDescriptorMap;
	private String fTargetId;
	
	/**
	 * Creates a new <code>CustomFiltersActionGroup</code>.
	 * 
	 * @param part		the view part that owns this action group
	 * @param viewer	the viewer to be filtered
	 */
	public CustomFiltersActionGroup(IViewPart part, StructuredViewer viewer) {
		this(part.getViewSite().getId(), viewer);
	}

	/**
	 * Creates a new <code>CustomFiltersActionGroup</code>.
	 * 
	 * @param ownerId	the id of this action group's owner
	 * @param viewer	the viewer to be filtered
	 */
	public CustomFiltersActionGroup(String ownerId, StructuredViewer viewer) {
		Assert.isNotNull(ownerId);
		Assert.isNotNull(viewer);
		fTargetId= ownerId;
		fViewer= viewer;

		fLRUFilterIdsStack= new Stack<String>();

		initializeWithPluginContributions();
		initializeWithViewDefaults();
		
		installFilters();
	}
	
	/*
	 * Method declared on ActionGroup.
	 */
	public void fillActionBars(IActionBars actionBars) {
		fillToolBar(actionBars.getToolBarManager());
		fillViewMenu(actionBars.getMenuManager());
	}
	
	/**
	 * Returns a list of currently enabled filters. The filter
	 * is identified by its id.
	 * <p>
	 * This method is for internal use only and should not
	 * be called by clients outside of JDT/UI.
	 * </p>
	 * 
	 * @return a list of currently enabled filters
	 * 
	 * @since 0.6.8
	 */
	public String[] internalGetEnabledFilterIds() {
		Set<String> enabledFilterIds= new HashSet<String>();
		for (Map.Entry<String, Boolean> entry : fEnabledFilterIds.entrySet()) {
			String id= entry.getKey();
			boolean isEnabled = entry.getValue();
			if (isEnabled) {
				enabledFilterIds.add(id);
			}
		}
		String[] enabledFilterIdsArray = enabledFilterIds.toArray(new String[0]);
		return enabledFilterIdsArray;
	}

	/**
	 * Removes filters for the given parent and element
	 * 
	 * @param parent the parent of the element
	 * @param element the element
	 * @param contentProvider the content provider of the viewer from which 
	 *  the filters will be removed
	 *  
	 * @return the array of new filter ids
	 */
	public String[] removeFiltersFor(Object parent, Object element, IContentProvider contentProvider) {
	    String[] enabledFilters= internalGetEnabledFilterIds();
	    Set<String> newFilters= new HashSet<String>();
	    for (String filterName : enabledFilters) {
            ViewerFilter filter= fInstalledBuiltInFilters.get(filterName);
            if (filter == null) {
                newFilters.add(filterName);
            }
            else if (isSelected(parent, element, contentProvider, filter)) { 
                newFilters.add(filterName);
            }
        }
	    
	    String[] newFiltersArray = new String[0];
	    
	    if (newFilters.size() != enabledFilters.length) {
	    	Assert.isTrue(newFilters.size() < enabledFilters.length);
	        newFiltersArray = newFilters.toArray(new String[0]);
	    }
	    
	    return newFiltersArray;
	}
	
	/**
	 * Sets the filters to the given array of new filters
	 * 
	 * @param newFilters the new filters
	 */
	public void setFilters(String[] newFilters) {
	    setEnabledFilterIds(newFilters);
	    updateViewerFilters(true);
	}
	
	private boolean isSelected(Object parent, Object element, IContentProvider contentProvider, ViewerFilter filter) {
	    if (contentProvider instanceof ITreeContentProvider) {
	        // the element and all its parents have to be selected
	        ITreeContentProvider provider = (ITreeContentProvider) contentProvider;
	        while (element != null && !(element instanceof IModelicaRoot)) {
	            if (!filter.select(fViewer, parent, element)) 
	                return false;
	            element= provider.getParent( element);
	        }
	        return true;
	    } 
	    return filter.select(fViewer, parent, element);
	}

    /**
	 * Sets the enable state of the given filter.
	 * 
	 * @param filterId the id of the filter
	 * @param state the filter state
	 */
	private void setFilter(String filterId, boolean state) {
		// Renew filter id in LRU stack
		fLRUFilterIdsStack.remove(filterId);
		fLRUFilterIdsStack.add(0, filterId);
		
		fEnabledFilterIds.put(filterId, Boolean.valueOf(state));
		//storeViewDefaults();
		
		updateViewerFilters(true);
	}
		
	private void setEnabledFilterIds(String[] enabledIds) {
		for (String id : fEnabledFilterIds.keySet()) {
			fEnabledFilterIds.put(id, false);
		}
		
		for (String id : enabledIds) {
			fEnabledFilterIds.put(id, true);
		}
	}

	private void setUserDefinedPatterns(String[] patterns) {
		fUserDefinedPatterns= patterns;
		cleanUpPatternDuplicates();
	}

	/**
	 * Sets the recently changed filters.
	 * 
	 * @param changeHistory the change history
	 * @since 3.0
	 */
	private void setRecentlyChangedFilters(Stack<FilterDescriptor> changeHistory) {
		Stack<String> oldestFirstStack= new Stack<String>();
		
		int length= Math.min(changeHistory.size(), MAX_FILTER_MENU_ENTRIES);
		for (int i= 0; i < length; i++) {
			FilterDescriptor filterDesc = changeHistory.pop();
			String id = filterDesc.getId();
			oldestFirstStack.push(id);
		}
		
		length= Math.min(fLRUFilterIdsStack.size(), MAX_FILTER_MENU_ENTRIES - oldestFirstStack.size());
		int NEWEST= 0;
		for (int i= 0; i < length; i++) {
			String filter= fLRUFilterIdsStack.remove(NEWEST);
			if (!oldestFirstStack.contains(filter)) {
				oldestFirstStack.push(filter);
			}
		}
		fLRUFilterIdsStack = oldestFirstStack;
	}
	
	private boolean areUserDefinedPatternsEnabled() {
		return fUserDefinedPatternsEnabled;
	}

	private void setUserDefinedPatternsEnabled(boolean state) {
		fUserDefinedPatternsEnabled= state;
	}

	private void fillToolBar(IToolBarManager tooBar) {
	}

	/**
	 * Fills the given view menu with the entries managed by the
	 * group.
	 * 
	 * @param viewMenu the menu to fill
	 */
	public void fillViewMenu(IMenuManager viewMenu) {
		/*
		 * Don't change the separator group name.
		 * Using this name ensures that other filters
		 * get contributed to the same group.
		 */
		viewMenu.add(new Separator("filters")); //$NON-NLS-1$
		viewMenu.add(new GroupMarker(RECENT_FILTERS_GROUP_NAME));
		viewMenu.add(new ShowFilterDialogAction());

		fMenuManager= viewMenu;
		fMenuListener= new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				removePreviousLRUFilterActions(manager);
				addLRUFilterActions(manager);
			}
		};
		fMenuManager.addMenuListener(fMenuListener);
	}

	private void removePreviousLRUFilterActions(IMenuManager mm) {
		if (fFilterIdsUsedInLastViewMenu == null)
			return;
		
		for (int i= 0; i < fFilterIdsUsedInLastViewMenu.length; i++)
			mm.remove(fFilterIdsUsedInLastViewMenu[i]);
	}

	private void addLRUFilterActions(IMenuManager mm) {
		if (fLRUFilterIdsStack.isEmpty()) {
			fFilterIdsUsedInLastViewMenu= null;
			return;
		}
		
		SortedSet<String> sortedFilters= new TreeSet<String>(fLRUFilterIdsStack);
		String[] recentlyChangedFilterIds= (String[])sortedFilters.toArray(new String[sortedFilters.size()]);
		
		fFilterIdsUsedInLastViewMenu= new String[recentlyChangedFilterIds.length];
		for (int i= 0; i < recentlyChangedFilterIds.length; i++) {
			String id= recentlyChangedFilterIds[i];
			fFilterIdsUsedInLastViewMenu[i]= id;
			boolean state= fEnabledFilterIds.containsKey(id) && ((Boolean)fEnabledFilterIds.get(id)).booleanValue();
			FilterDescriptor filterDesc= (FilterDescriptor)fFilterDescriptorMap.get(id);
			if (filterDesc != null) {
				IContributionItem item= new FilterActionMenuContributionItem(this, id, filterDesc.getName(), state, i+1);
				mm.insertBefore(RECENT_FILTERS_GROUP_NAME, item);
			}
		}
	}

	/*
	 * Method declared on ActionGroup.
	 */
	public void dispose() {
		if (fMenuManager != null)
			fMenuManager.removeMenuListener(fMenuListener);
		fCachedFilterDescriptors= null;
		super.dispose();
	}
	
	private void initializeWithPluginContributions() {
		fUserDefinedPatterns = new String[0];
		fUserDefinedPatternsEnabled = false;

		FilterDescriptor[] filterDescs = getCachedFilterDescriptors();
		fFilterDescriptorMap = new HashMap<String, FilterDescriptor>();
		fEnabledFilterIds = new HashMap<String, Boolean>();
		for (FilterDescriptor filterDesc : filterDescs) {
			String id= filterDesc.getId();
			boolean isEnabled= filterDesc.isEnabled();
			if (fEnabledFilterIds.containsKey(id)) {
				System.err.println("duplicateFilterId: " + id);
			}
			fEnabledFilterIds.put(id, isEnabled);
			fFilterDescriptorMap.put(id, filterDesc);
		}
	}

	// ---------- viewer filter handling ----------
	
	private void installFilters() {
		fInstalledBuiltInFilters= new HashMap<String, ViewerFilter>(fEnabledFilterIds.size());
		fPatternFilter= new NamePatternFilter();
		fPatternFilter.setPatterns(getUserAndBuiltInPatterns());
		fViewer.addFilter(fPatternFilter);
		updateBuiltInFilters();
	}

	private void updateViewerFilters(boolean refresh) {
		String[] patterns= getUserAndBuiltInPatterns();
		fPatternFilter.setPatterns(patterns);
		fViewer.getControl().setRedraw(false);
		updateBuiltInFilters();
		if (refresh)
			fViewer.refresh();
		fViewer.getControl().setRedraw(true);
	}
	
	private void updateBuiltInFilters() {
		Set<String> installedFilters= fInstalledBuiltInFilters.keySet();
		Set<String> filtersToAdd= new HashSet<String>();
		Set<String> filtersToRemove= new HashSet<String>();
		for (Map.Entry<String, Boolean> entry : fEnabledFilterIds.entrySet()) {
			String id = entry.getKey();
			boolean isEnabled = entry.getValue();
			if (isEnabled && !installedFilters.contains(id)) {
				filtersToAdd.add(id);
			}
			else if (!isEnabled && installedFilters.contains(id)) {
				filtersToRemove.add(id);
			}
		}
		
		// Install the filters
		FilterDescriptor[] filterDescs= getCachedFilterDescriptors();
		for (FilterDescriptor filterDesc : filterDescs) {
			String id = filterDesc.getId();
			// just to double check - id should denote a custom filter anyway
			boolean isCustomFilter = filterDesc.isCustomFilter();
			if (isCustomFilter) {
				if (filtersToAdd.contains(id)) {
					ViewerFilter filter = filterDesc.createViewerFilter();
					if (filter != null) {
						fViewer.addFilter(filter);
						fInstalledBuiltInFilters.put(id, filter);
					}
				}
				if (filtersToRemove.contains(id)) {
					fViewer.removeFilter(fInstalledBuiltInFilters.get(id));
					fInstalledBuiltInFilters.remove(id);
				}
			}
		}
	}

	private String[] getUserAndBuiltInPatterns() {
		List<String> patterns= new ArrayList<String>();
		if (areUserDefinedPatternsEnabled()) {
			patterns.addAll(Arrays.asList(fUserDefinedPatterns));
		}
		FilterDescriptor[] filterDescs= getCachedFilterDescriptors();
		for (int i= 0; i < filterDescs.length; i++) {
			String id= filterDescs[i].getId();
			boolean isPatternFilter= filterDescs[i].isPatternFilter();
			Object isEnabled= fEnabledFilterIds.get(id);
			if (isEnabled != null && isPatternFilter && ((Boolean)isEnabled).booleanValue())
				patterns.add(filterDescs[i].getPattern());
		}
		return (String[])patterns.toArray(new String[patterns.size()]);
	}

	// ---------- view kind/defaults persistency ----------
		
	private void initializeWithViewDefaults() {
		// get default values for view
		setUserDefinedPatterns(CustomFiltersDialog.convertFromString("", SEPARATOR));

		for (String id : fEnabledFilterIds.keySet()) {
			fEnabledFilterIds.put(id, true);
		}
		
		fLRUFilterIdsStack.clear();
		//String lruFilterIds= store.getString(TAG_LRU_FILTERS);
		StringTokenizer tokenizer= new StringTokenizer("*.o,*.srz,*.sig", SEPARATOR);
		while (tokenizer.hasMoreTokens()) {
			String id= tokenizer.nextToken();
			if (fFilterDescriptorMap.containsKey(id) && !fLRUFilterIdsStack.contains(id))
				fLRUFilterIdsStack.push(id);
		}
	}

	private void storeViewDefaults() {
		// get default values for view
		IPreferenceStore store= UIPlugin.getDefault().getPreferenceStore();
		
		store.setValue(getPreferenceKey(TAG_USER_DEFINED_PATTERNS_ENABLED), fUserDefinedPatternsEnabled);
		store.setValue(getPreferenceKey(TAG_USER_DEFINED_PATTERNS), CustomFiltersDialog.convertToString(fUserDefinedPatterns ,SEPARATOR));

		for (Map.Entry<String, Boolean> entry : fEnabledFilterIds.entrySet()) {
			String id = entry.getKey();
			boolean isEnabled = entry.getValue();
			store.setValue(id, isEnabled);
		}

		StringBuffer buf= new StringBuffer(fLRUFilterIdsStack.size() * 20);
		
		Iterator<String> iter= fLRUFilterIdsStack.iterator();
		while (iter.hasNext()) {
			buf.append(iter.next());
			buf.append(SEPARATOR);
		}
		store.setValue(TAG_LRU_FILTERS, buf.toString());
	}
	
	private String getPreferenceKey(String tag) {
		return "CustomFiltersActionGroup." + fTargetId + '.' + tag; //$NON-NLS-1$
	}

	// ---------- view instance persistency ----------

	/**
	 * Saves the state of the custom filters in a memento.
	 * 
	 * @param memento the memento into which the state is saved
	 */
	public void saveState(IMemento memento) {
		IMemento customFilters= memento.createChild(TAG_CUSTOM_FILTERS);
		customFilters.putString(TAG_USER_DEFINED_PATTERNS_ENABLED, Boolean.toString(fUserDefinedPatternsEnabled));
		saveUserDefinedPatterns(customFilters);
		saveXmlDefinedFilters(customFilters);
		saveLRUFilters(customFilters);
	}

	private void saveXmlDefinedFilters(IMemento memento) {
		if(fEnabledFilterIds != null && !fEnabledFilterIds.isEmpty()) {
			IMemento xmlDefinedFilters= memento.createChild(TAG_XML_DEFINED_FILTERS);
			for (Map.Entry<String, Boolean> entry : fEnabledFilterIds.entrySet()) {
				String id = entry.getKey();
				Boolean isEnabled = entry.getValue();
				IMemento child= xmlDefinedFilters.createChild(TAG_CHILD);
				child.putString(TAG_FILTER_ID, id);
				child.putString(TAG_IS_ENABLED, isEnabled.toString());
			}
		}
	}
	/**
	 * Stores the last recently used filter Ids into
	 * the given memento
	 * 
	 * @param memento the memento into which to store the LRU filter Ids
	 * @since 3.0
	 */
	private void saveLRUFilters(IMemento memento) {
		if(fLRUFilterIdsStack != null && !fLRUFilterIdsStack.isEmpty()) {
			IMemento lruFilters= memento.createChild(TAG_LRU_FILTERS);
			for (String id : fLRUFilterIdsStack) {
				IMemento child= lruFilters.createChild(TAG_CHILD);
				child.putString(TAG_FILTER_ID, id);
			}
		}
	}

	private void saveUserDefinedPatterns(IMemento memento) {
		if(fUserDefinedPatterns != null && fUserDefinedPatterns.length > 0) {
			IMemento userDefinedPatterns= memento.createChild(TAG_USER_DEFINED_PATTERNS);
			for (int i= 0; i < fUserDefinedPatterns.length; i++) {
				IMemento child= userDefinedPatterns.createChild(TAG_CHILD);
				child.putString(TAG_PATTERN, fUserDefinedPatterns[i]);
			}
		}
	}

	/**
	 * Restores the state of the filter actions from a memento.
	 * <p>
	 * Note: This method does not refresh the viewer.
	 * </p>
	 * 
	 * @param memento the memento from which the state is restored
	 */	
	public void restoreState(IMemento memento) {
		if (memento == null)
			return;
		IMemento customFilters= memento.getChild(TAG_CUSTOM_FILTERS);
		if (customFilters == null)
			return;
		String userDefinedPatternsEnabled= customFilters.getString(TAG_USER_DEFINED_PATTERNS_ENABLED);
		if (userDefinedPatternsEnabled == null)
			return;

		fUserDefinedPatternsEnabled= Boolean.valueOf(userDefinedPatternsEnabled).booleanValue();
		restoreUserDefinedPatterns(customFilters);
		restoreXmlDefinedFilters(customFilters);
		restoreLRUFilters(customFilters);
		
		updateViewerFilters(false);
	}

	private void restoreUserDefinedPatterns(IMemento memento) {
		IMemento userDefinedPatterns= memento.getChild(TAG_USER_DEFINED_PATTERNS);
		if(userDefinedPatterns != null) {	
			IMemento children[]= userDefinedPatterns.getChildren(TAG_CHILD);
			String[] patterns= new String[children.length];
			for (int i = 0; i < children.length; i++)
				patterns[i]= children[i].getString(TAG_PATTERN);

			setUserDefinedPatterns(patterns);
		} else
			setUserDefinedPatterns(new String[0]);
	}

	private void restoreXmlDefinedFilters(IMemento memento) {
		IMemento xmlDefinedFilters= memento.getChild(TAG_XML_DEFINED_FILTERS);
		if(xmlDefinedFilters != null) {
			IMemento[] children= xmlDefinedFilters.getChildren(TAG_CHILD);
			for (int i= 0; i < children.length; i++) {
				String id= children[i].getString(TAG_FILTER_ID);
				Boolean isEnabled= Boolean.valueOf(children[i].getString(TAG_IS_ENABLED));
				fEnabledFilterIds.put(id, isEnabled);
			}
		}
	}

	private void restoreLRUFilters(IMemento memento) {
		IMemento lruFilters= memento.getChild(TAG_LRU_FILTERS);
		fLRUFilterIdsStack.clear();
		if(lruFilters != null) {
			IMemento[] children= lruFilters.getChildren(TAG_CHILD);
			for (int i= 0; i < children.length; i++) {
				String id= children[i].getString(TAG_FILTER_ID);
				if (fFilterDescriptorMap.containsKey(id) && !fLRUFilterIdsStack.contains(id))
					fLRUFilterIdsStack.push(id);
			}
		}
	}

	private void cleanUpPatternDuplicates() {
		if (!areUserDefinedPatternsEnabled())
			return;
		List<String> userDefinedPatterns = Arrays.asList(fUserDefinedPatterns);
		FilterDescriptor[] filters= getCachedFilterDescriptors();

		for (int i= 0; i < filters.length; i++) {
			if (filters[i].isPatternFilter()) {
				String pattern= filters[i].getPattern();
				if (userDefinedPatterns.contains(pattern)) {
					fEnabledFilterIds.put(filters[i].getId(), Boolean.TRUE);
					boolean hasMore= true;
					while (hasMore)
						hasMore= userDefinedPatterns.remove(pattern);
				}
			}
		}
		fUserDefinedPatterns= (String[])userDefinedPatterns.toArray(new String[userDefinedPatterns.size()]);
		setUserDefinedPatternsEnabled(fUserDefinedPatternsEnabled && fUserDefinedPatterns.length > 0);
	}

	private FilterDescriptor[] getCachedFilterDescriptors() {
		if (fCachedFilterDescriptors == null)
			fCachedFilterDescriptors= FilterDescriptor.getFilterDescriptors(fTargetId);
		return fCachedFilterDescriptors;
	}
	
	// ---------- dialog related code ----------

	private void openDialog() {
		CustomFiltersDialog dialog= new CustomFiltersDialog(
			fViewer.getControl().getShell(),
			fTargetId,
			areUserDefinedPatternsEnabled(),
			fUserDefinedPatterns,
			internalGetEnabledFilterIds());
		
		if (dialog.open() == Window.OK) {
			setEnabledFilterIds(dialog.getEnabledFilterIds());
			setUserDefinedPatternsEnabled(dialog.areUserDefinedPatternsEnabled());
			setUserDefinedPatterns(dialog.getUserDefinedPatterns());
			setRecentlyChangedFilters(dialog.getFilterDescriptorChangeHistory());

			storeViewDefaults();

			updateViewerFilters(true);
		}
	}
}
