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
import java.util.List;

import org.eclipse.swt.custom.BusyIndicator;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.StructuredViewer;

import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.actions.ActionGroup;

import org.modelica.mdt.ui.ModelicaImages;
import org.modelica.mdt.ui.UIPlugin;
import org.modelica.mdt.ui.actions.MemberFilter;
import org.modelica.mdt.ui.actions.MemberFilterAction;

/**
 * Action Group that contributes filter buttons for a view parts showing 
 * methods and fields. Contributed filters are: hide fields, hide static
 * members hide non-public members and hide local types.
 * <p>
 * The action group installs a filter on a structured viewer. The filter is connected 
 * to the actions installed in the view part's toolbar menu and is updated when the 
 * state of the buttons changes.
 *  
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
 * @since 0.6.8
 */
public class MemberFilterActionGroup extends ActionGroup {

	public static final int FILTER_PROTECTED= MemberFilter.FILTER_PROTECTED;
	public static final int FILTER_PARAMETERS= MemberFilter.FILTER_PARAMETERS;
	public static final int FILTER_COMPONENTS= MemberFilter.FILTER_COMPONENTS;

	/** @since 0.6.8 */
	public static final int FILTER_TYPES= MemberFilter.FILTER_TYPES;
	/** @since 0.6.8 */
	public static final int ALL_FILTERS= FILTER_PROTECTED | FILTER_COMPONENTS | FILTER_PARAMETERS | FILTER_TYPES;

	private static final String TAG_HIDECOMPONENTS= "hidecomponents"; //$NON-NLS-1$
	private static final String TAG_HIDEPARAMETERS= "hideparameters"; //$NON-NLS-1$
	private static final String TAG_HIDEPRIVATE= "hideprotected"; //$NON-NLS-1$
	private static final String TAG_HIDETYPES= "hidetypes"; //$NON-NLS-1$

	private MemberFilterAction[] fFilterActions;
	private MemberFilter fFilter;

	private StructuredViewer fViewer;
	private String fViewerId;
	private boolean fInViewMenu;


	/**
	 * Creates a new <code>MemberFilterActionGroup</code>.
	 * 
	 * @param viewer the viewer to be filtered
	 * @param viewerId a unique id of the viewer. Used as a key to to store 
	 * the last used filter settings in the preference store
	 */
	public MemberFilterActionGroup(StructuredViewer viewer, String viewerId) {
		this(viewer, viewerId, false);
	}

	/**
	 * Creates a new <code>MemberFilterActionGroup</code>.
	 * 
	 * @param viewer the viewer to be filtered
	 * @param viewerId a unique id of the viewer. Used as a key to to store 
	 * the last used filter settings in the preference store
	 * @param inViewMenu if <code>true</code> the actions are added to the view
	 * menu. If <code>false</code> they are added to the toolbar.
	 * 
	 * @since 0.6.8
	 */
	public MemberFilterActionGroup(StructuredViewer viewer, String viewerId, boolean inViewMenu) {	
		this(viewer, viewerId, inViewMenu, ALL_FILTERS);
	}

	/**
	 * Creates a new <code>MemberFilterActionGroup</code>.
	 * 
	 * @param viewer the viewer to be filtered
	 * @param viewerId a unique id of the viewer. Used as a key to to store 
	 * the last used filter settings in the preference store
	 * @param inViewMenu if <code>true</code> the actions are added to the view
	 * menu. If <code>false</code> they are added to the toolbar.
	 * @param availableFilters Specifies which filter action should be contained. <code>FILTER_PROTECTED</code>,
	 * <code>FILTER_PARAMETERS</code>, <code>FILTER_COMPONENTS</code> and <code>FILTER_TYPES</code>
	 * or a combination of these constants are possible values. Use <code>ALL_FILTERS</code> to select all available filters.
	 * 
	 * @since 0.6.8
	 */
	public MemberFilterActionGroup(StructuredViewer viewer, String viewerId, boolean inViewMenu, int availableFilters) {	
		fViewer= viewer;
		fViewerId= viewerId;
		fInViewMenu= inViewMenu;

		fFilter= new MemberFilter();

		List<MemberFilterAction> actions= new ArrayList<MemberFilterAction>();
		IPreferenceStore store = UIPlugin.getDefault().getPreferenceStore();

		// fields
		int filterProperty= FILTER_COMPONENTS;
		if (isSet(filterProperty, availableFilters)) {
			boolean filterEnabled = store.getBoolean(getPreferenceKey(filterProperty));
			if (filterEnabled) {
				fFilter.addFilter(filterProperty);
			}
			String title = "Hide components"; 
			MemberFilterAction hideComponents= new MemberFilterAction(this, title, filterProperty, filterEnabled);
			hideComponents.setDescription("Hides components"); 
			hideComponents.setToolTipText("Hide components"); 
			hideComponents.setImageDescriptor(ModelicaImages.getImageDescriptor(ModelicaImages.IMG_OUTLINE_HIDE_COMPONENTS));
			actions.add(hideComponents);
		}

		// static
		filterProperty= FILTER_PARAMETERS;
		if (isSet(filterProperty, availableFilters)) {
			boolean filterEnabled = store.getBoolean(getPreferenceKey(filterProperty));
			if (filterEnabled) {
				fFilter.addFilter(filterProperty);
			}
			String title = "Hide parameters"; 
			MemberFilterAction hideStatic= new MemberFilterAction(this, title, FILTER_PARAMETERS, filterEnabled);
			hideStatic.setDescription("Hides the parameters"); 
			hideStatic.setToolTipText("Hide parameters"); 
			hideStatic.setImageDescriptor(ModelicaImages.getImageDescriptor(ModelicaImages.IMG_OUTLINE_HIDE_PARAMETERS));
			actions.add(hideStatic);
		}

		// non-public
		filterProperty= FILTER_PROTECTED;
		if (isSet(filterProperty, availableFilters)) {
			boolean filterEnabled = store.getBoolean(getPreferenceKey(filterProperty)); 
			if (filterEnabled) {
				fFilter.addFilter(filterProperty);
			}
			String title = "Hide protected"; 
			MemberFilterAction hideNonPublic= new MemberFilterAction(this, title, filterProperty, filterEnabled);
			hideNonPublic.setDescription("Hide protected elements"); 
			hideNonPublic.setToolTipText("Hide protected"); 
			hideNonPublic.setImageDescriptor(ModelicaImages.getImageDescriptor(ModelicaImages.IMG_OUTLINE_HIDE_PROTECTED));
			actions.add(hideNonPublic);
		}

		// local types
		filterProperty= FILTER_TYPES;
		if (isSet(filterProperty, availableFilters)) {
			boolean filterEnabled = store.getBoolean(getPreferenceKey(filterProperty));
			if (filterEnabled) {
				fFilter.addFilter(filterProperty);
			}
			String title = "Hide types"; 
			MemberFilterAction hideLocalTypes= new MemberFilterAction(this, title, filterProperty, filterEnabled);
			hideLocalTypes.setDescription("Hide type definitions"); 
			hideLocalTypes.setToolTipText("Hide types"); 
			hideLocalTypes.setImageDescriptor(ModelicaImages.getImageDescriptor(ModelicaImages.IMG_OUTLINE_HIDE_TYPES));
			actions.add(hideLocalTypes);
		}

		// order corresponds to order in toolbar
		fFilterActions= actions.toArray(new MemberFilterAction[actions.size()]);

		fViewer.addFilter(fFilter);
	}

	private String getPreferenceKey(int filterProperty) {
		// TODO: Use modern-style enums for the properties.
		String preferenceKey = "MemberFilterActionGroup." + fViewerId + '.' + String.valueOf(filterProperty);

		return preferenceKey;
	}

	/**
	 * Sets the member filters.
	 * 
	 * @param filterProperty the filter to be manipulated. Valid values are <code>FILTER_COMPONENTS</code>, 
	 * <code>FILTER_PUBLIC</code> <code>FILTER_PRIVATE</code> and <code>FILTER_TYPES_ACTION</code>
	 * as defined by this action group
	 * @param set if <code>true</code> the given filter is installed. If <code>false</code> the
	 * given filter is removed
	 * .
	 */	
	public void setMemberFilter(int filterProperty, boolean set) {
		setMemberFilters(new int[] {filterProperty}, new boolean[] {set}, true);
	}

	private void setMemberFilters(int[] propertyKeys, boolean[] propertyValues, boolean refresh) {
		if (propertyKeys.length == 0)
			return;
		Assert.isTrue(propertyKeys.length == propertyValues.length);

		for (int i= 0; i < propertyKeys.length; i++) {
			int filterProperty= propertyKeys[i];
			boolean set= propertyValues[i];

			IPreferenceStore store= UIPlugin.getDefault().getPreferenceStore();
			boolean found= false;
			for (int j= 0; j < fFilterActions.length; j++) {
				int currProperty= fFilterActions[j].getFilterProperty();
				if (currProperty == filterProperty) {
					fFilterActions[j].setChecked(set);
					found= true;
					store.setValue(getPreferenceKey(filterProperty), set);
				}
			}
			if (found) {
				if (set) {
					fFilter.addFilter(filterProperty);
				} else {
					fFilter.removeFilter(filterProperty);
				}
			}
		}
		if (refresh) {
			fViewer.getControl().setRedraw(false);
			BusyIndicator.showWhile(fViewer.getControl().getDisplay(), new Runnable() {
				@Override
				public void run() {
					fViewer.refresh();
				}
			});
			fViewer.getControl().setRedraw(true);
		}
	}

	private boolean isSet(int flag, int set) {
		return (flag & set) != 0;
	}

	/**
	 * Returns <code>true</code> if the given filter is installed.
	 * 
	 * @param filterProperty the filter to be tested. Valid values are <code>FILTER_COMPONENTS</code>, 
	 * <code>FILTER_PUBLIC</code>, <code>FILTER_PRIVATE</code> and <code>FILTER_TYPES</code> as defined by this action 
	 * group
	 */	
	public boolean hasMemberFilter(int filterProperty) {
		return fFilter.hasFilter(filterProperty);
	}

	/**
	 * Saves the state of the filter actions in a memento.
	 * 
	 * @param memento the memento to which the state is saved
	 */
	public void saveState(IMemento memento) {
		memento.putString(TAG_HIDECOMPONENTS, String.valueOf(hasMemberFilter(FILTER_COMPONENTS)));
		memento.putString(TAG_HIDEPARAMETERS, String.valueOf(hasMemberFilter(FILTER_PARAMETERS)));
		memento.putString(TAG_HIDEPRIVATE, String.valueOf(hasMemberFilter(FILTER_PROTECTED)));
		memento.putString(TAG_HIDETYPES, String.valueOf(hasMemberFilter(FILTER_TYPES)));
	}

	/**
	 * Restores the state of the filter actions from a memento.
	 * <p>
	 * Note: This method does not refresh the viewer.
	 * </p>
	 * @param memento the memento from which the state is restored
	 */	
	public void restoreState(IMemento memento) {
		setMemberFilters(
				new int[] {FILTER_COMPONENTS, FILTER_PARAMETERS, FILTER_PROTECTED, FILTER_TYPES},
				new boolean[] {
						Boolean.valueOf(memento.getString(TAG_HIDECOMPONENTS)).booleanValue(),
						Boolean.valueOf(memento.getString(TAG_HIDEPARAMETERS)).booleanValue(),
						Boolean.valueOf(memento.getString(TAG_HIDEPRIVATE)).booleanValue(),
						Boolean.valueOf(memento.getString(TAG_HIDETYPES)).booleanValue()
				}, false);
	}

	/* (non-Javadoc)
	 * @see ActionGroup#fillActionBars(IActionBars)
	 */
	@Override
	public void fillActionBars(IActionBars actionBars) {
		contributeToToolBar(actionBars.getToolBarManager());
	}

	/**
	 * Adds the filter actions to the given tool bar
	 * 
	 * @param tbm the tool bar to which the actions are added
	 */
	public void contributeToToolBar(IToolBarManager tbm) {
		if (fInViewMenu)
			return;
		for (int i= 0; i < fFilterActions.length; i++) {
			tbm.add(fFilterActions[i]);
		}
	}

	/**
	 * Adds the filter actions to the given menu manager.
	 * 
	 * @param menu the menu manager to which the actions are added
	 * @since 0.6.8
	 */
	public void contributeToViewMenu(IMenuManager menu) {
		if (!fInViewMenu)
			return;
		final String filters= "filters"; //$NON-NLS-1$
		if (menu.find(filters) != null) {
			for (int i= 0; i < fFilterActions.length; i++) {
				menu.prependToGroup(filters, fFilterActions[i]);
			}
		} else {
			for (int i= 0; i < fFilterActions.length; i++) {
				menu.add(fFilterActions[i]);
			}
		}
	}

	/* (non-Javadoc)
	 * @see ActionGroup#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
	}

}
