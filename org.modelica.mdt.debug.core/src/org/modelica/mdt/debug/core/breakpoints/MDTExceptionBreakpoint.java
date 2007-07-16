/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.debug.core.breakpoints;

 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.modelica.mdt.debug.core.IMDTFailureBreakpoint;

public class MDTExceptionBreakpoint extends MDTBreakpoint implements IMDTFailureBreakpoint {

	private static final String MDT_EXCEPTION_BREAKPOINT= "org.eclipse.jdt.debug.javaExceptionBreakpointMarker"; //$NON-NLS-1$
	
	/**
	 * Exception breakpoint attribute storing the suspend on caught value
	 * (value <code>"org.eclipse.jdt.debug.core.caught"</code>). This attribute is stored as a <code>boolean</code>.
	 * When this attribute is <code>true</code>, a caught exception of the associated
	 * type will cause excecution to suspend .
	 */
	protected static final String CAUGHT = "org.eclipse.jdt.debug.core.caught"; //$NON-NLS-1$
	/**
	 * Exception breakpoint attribute storing the suspend on uncaught value
	 * (value <code>"org.eclipse.jdt.debug.core.uncaught"</code>). This attribute is stored as a
	 * <code>boolean</code>. When this attribute is <code>true</code>, an uncaught
	 * exception of the associated type will cause excecution to suspend.
	 */
	protected static final String UNCAUGHT = "org.eclipse.jdt.debug.core.uncaught"; //$NON-NLS-1$	
	/**
	 * Exception breakpoint attribute storing the checked value (value <code>"org.eclipse.jdt.debug.core.checked"</code>).
	 * This attribute is stored as a <code>boolean</code>, indicating whether an
	 * exception is a checked exception.
	 */
	protected static final String CHECKED = "org.eclipse.jdt.debug.core.checked"; //$NON-NLS-1$	
	
	/**
	 * Exception breakpoint attribute storing the String value (value <code>"org.eclipse.jdt.debug.core.filters"</code>).
	 * This attribute is stored as a <code>String</code>, a comma delimited list
	 * of class filters.  The filters are applied as inclusion or exclusion depending on 
	 * INCLUSIVE_FILTERS.
	 */
	protected static final String INCLUSION_FILTERS = "org.eclipse.jdt.debug.core.inclusion_filters"; //$NON-NLS-1$	
	
	/**
	 * Exception breakpoint attribute storing the String value (value <code>"org.eclipse.jdt.debug.core.filters"</code>).
	 * This attribute is stored as a <code>String</code>, a comma delimited list
	 * of class filters.  The filters are applied as inclusion or exclusion depending on 
	 * INCLUSIVE_FILTERS.
	 */
	protected static final String EXCLUSION_FILTERS = "org.eclipse.jdt.debug.core.exclusion_filters"; //$NON-NLS-1$	
	/**
	 * Allows the user to specify whether we should suspend if subclasses of the specified exception are thrown/caught
	 * @since 3.2
	 */
	protected static final String SUSPEND_ON_SUBCLASSES = "org.eclipse.jdt.debug.core.suspend_on_subclasses"; //$NON-NLS-1$
	
	/**
	 * Name of the exception that was actually hit (could be a
	 * subtype of the type that is being caught).
	 */
	protected String fExceptionName = null;
	
	/**
	 * The current set of inclusion class filters.
	 */
	protected String[] fInclusionClassFilters= null;
	
	/**
	 * The current set of inclusion class filters.
	 */
	protected String[] fExclusionClassFilters= null;
		
	public MDTExceptionBreakpoint() {
	}
	
	/**
	 * Creates and returns an exception breakpoint for the
	 * given (throwable) type. Caught and uncaught specify where the exception
	 * should cause thread suspensions - that is, in caught and/or uncaught locations.
	 * Checked indicates if the given exception is a checked exception.
	 * @param resource the resource on which to create the associated
	 *  breakpoint marker 
	 * @param exceptionName the fully qualified name of the exception for
	 *  which to create the breakpoint
	 * @param caught whether to suspend in caught locations
	 * @param uncaught whether to suspend in uncaught locations
 	 * @param checked whether the exception is a checked exception
 	 * @param add whether to add this breakpoint to the breakpoint manager
	 * @return a Java exception breakpoint
	 * @exception DebugException if unable to create the associated marker due
	 *  to a lower level exception.
	 */	
	public MDTExceptionBreakpoint(final IResource resource, final String exceptionName, final boolean caught, final boolean uncaught, final boolean checked, final boolean add, final Map attributes) throws DebugException {
		IWorkspaceRunnable wr= new IWorkspaceRunnable() {

			public void run(IProgressMonitor monitor) throws CoreException {				
				// create the marker
				setMarker(resource.createMarker(MDT_EXCEPTION_BREAKPOINT));
				
				// add attributes
				attributes.put(IBreakpoint.ID, getModelIdentifier());
				attributes.put(TYPE_NAME, exceptionName);
				attributes.put(ENABLED, Boolean.TRUE);
				attributes.put(CAUGHT, Boolean.valueOf(caught));
				attributes.put(UNCAUGHT, Boolean.valueOf(uncaught));
				attributes.put(CHECKED, Boolean.valueOf(checked));				
				ensureMarker().setAttributes(attributes);
				
				register(add);
			}

		};
		run(getMarkerRule(resource), wr);
	}
		

	/**
	 * Enable this exception breakpoint.
	 * 
	 * If the exception breakpoint is not catching caught or uncaught,
	 * turn both modes on. If this isn't done, the resulting
	 * state (enabled with caught and uncaught both disabled)
	 * is ambiguous.
	 */
	public void setEnabled(boolean enabled) throws CoreException {	
		if (enabled) {
			if (!(isCaught() || isUncaught())) {
				setAttributes(new String[] {CAUGHT, UNCAUGHT}, new Object[] {Boolean.TRUE, Boolean.TRUE});
			}
		}
		super.setEnabled(enabled);
	}
	
	/**
	 * Sets the values for whether this breakpoint will
	 * suspend execution when the associated exception is thrown
	 * and caught or not caught.
	 */
	protected void setCaughtAndUncaught(boolean caught, boolean uncaught) throws CoreException {
		Object[] values= new Object[]{Boolean.valueOf(caught), Boolean.valueOf(uncaught)};
		String[] attributes= new String[]{CAUGHT, UNCAUGHT};
		setAttributes(attributes, values);
	}
		
	/**
	 * @see IMDTFailureBreakpoint#isCaught()
	 */
	public boolean isCaught() throws CoreException {
		return ensureMarker().getAttribute(CAUGHT, false);
	}
	
	/**
	 * @see IMDTFailureBreakpoint#setCaught(boolean)
	 */
	public void setCaught(boolean caught) throws CoreException {
		if (caught == isCaught()) {
			return;
		}
		setAttribute(CAUGHT, caught);
		if (caught && !isEnabled()) {
			setEnabled(true);
		} else if (!(caught || isUncaught())) {
			setEnabled(false);
		}
	}
	
		
	/**
	 * @see IMDTFailureBreakpoint#isUncaught()
	 */
	public boolean isUncaught() throws CoreException {
		return ensureMarker().getAttribute(UNCAUGHT, false);
	}	
	
	/**
	 * @see IMDTFailureBreakpoint#setUncaught(boolean)
	 */
	public void setUncaught(boolean uncaught) throws CoreException {
		if (uncaught == isUncaught()) {
			return;
		}
		setAttribute(UNCAUGHT, uncaught);
		if (uncaught && !isEnabled()) {
			setEnabled(true);
		} else if (!(uncaught || isCaught())) {
			setEnabled(false);
		}
	}
	
	/**
	 * @see IMDTFailureBreakpoint#isChecked()
	 */
	public boolean isChecked() throws CoreException {
		return ensureMarker().getAttribute(CHECKED, false);
	}
			
	/**
	 * Determines of the filters for this exception include the default package or not
	 * @param filters the list of filters to inspect
	 * @return true if any one of the spcified filters include the default package
	 */
	protected boolean filtersIncludeDefaultPackage(String[] filters) {
		for (int i = 0; i < filters.length; i++) {
			if (filters[i].length() == 0 || (filters[i].indexOf('.') == -1)) {
				return true;
			}
		}
		return false;
	}	
	
	/**
	 * Returns whether the given type is in the given filter set.
	 * 
	 * @param filters the filter set
	 * @param typeName fully qualified type name
	 * @param defaultPackage whether the type name is in the default package
	 * @return boolean
	 */
	protected boolean matchesFilters(String[] filters, String typeName, boolean defaultPackage) {
	    for (int i = 0; i < filters.length; i++) {
	        String filter = filters[i];
	        if (defaultPackage && filter.length() == 0) {
	            return true;
	        }
	        
	        filter = filter.replaceAll("\\.", "\\\\.");  //$NON-NLS-1$//$NON-NLS-2$
	        filter = filter.replaceAll("\\*", "\\.\\*");  //$NON-NLS-1$//$NON-NLS-2$
	        Pattern pattern = Pattern.compile(filter);
	        if (pattern.matcher(typeName).find()) {
	            return true;
	        }
		}
		return false;
	}
	
	/**
	 * Sets the name of the exception that was last hit
	 * 
	 * @param name fully qualified exception name
	 */
	protected void setExceptionName(String name) {
		fExceptionName = name;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.debug.core.IJavaExceptionBreakpoint#getExceptionTypeName()
	 */
	public String getExceptionTypeName() {
		return fExceptionName;
	}
	
	/**
	 * @see IMDTFailureBreakpoint#getFilters()
	 * @deprecated
	 */
	public String[] getFilters() {
		String[] iFilters= getInclusionFilters();
		String[] eFilters= getExclusionFilters();
		String[] filters= new String[iFilters.length + eFilters.length];
		System.arraycopy(iFilters, 0, filters, 0, iFilters.length);
		System.arraycopy(eFilters, 0, filters, iFilters.length, eFilters.length);
		return filters;
	}
	
	/**
	 * @see IMDTFailureBreakpoint#setFilters(String[], boolean)
	 * @deprecated
	 */
	public void setFilters(String[] filters, boolean inclusive) throws CoreException {
		if (inclusive) {
			setInclusionFilters(filters);
		} else {
			setExclusionFilters(filters);
		}
	}
	
	
	/**
	 * Serializes the array of Strings into one comma
	 * separated String.
	 * Removes duplicates.
	 */
	protected String serializeList(String[] list) {
		if (list == null) {
			return ""; //$NON-NLS-1$
		}
		Set set= new HashSet(list.length);

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.length; i++) {
			if (i > 0) {
				buffer.append(',');
			}
			String pattern= list[i];
			if (!set.contains(pattern)) {
				if (pattern.length() == 0) {
					//serialize the default package
					pattern= "."; //$NON-NLS-1$
				}
				buffer.append(pattern);
			}
		}
		return buffer.toString();
	}	
	
	/**
	 * Parses the comma separated String into an array of Strings
	 */
	protected String[] parseList(String listString) {
		List list = new ArrayList(10);
		StringTokenizer tokenizer = new StringTokenizer(listString, ","); //$NON-NLS-1$
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if (token.equals(".")) { //$NON-NLS-1$
				//serialized form for the default package
				//@see serializeList(String[])
				token= ""; //$NON-NLS-1$
			}
			list.add(token);
		}
		return (String[])list.toArray(new String[list.size()]);
	}
	
	/**
	 * @see IMDTFailureBreakpoint#isInclusiveFiltered()
	 * @deprecated
	 */
	public boolean isInclusiveFiltered() throws CoreException {
		return ensureMarker().getAttribute(INCLUSION_FILTERS, "").length() > 0; //$NON-NLS-1$
	}
	
	protected String[] getInclusionClassFilters() {
		if (fInclusionClassFilters == null) {
			try {
				fInclusionClassFilters= parseList(ensureMarker().getAttribute(INCLUSION_FILTERS, "")); //$NON-NLS-1$
			} catch (CoreException ce) {
				fInclusionClassFilters= new String[]{};
			}
		}
		return fInclusionClassFilters;
	}

	protected void setInclusionClassFilters(String[] filters) {
		fInclusionClassFilters = filters;
	}
	
	protected String[] getExclusionClassFilters() {
		if (fExclusionClassFilters == null) {
			try {
				fExclusionClassFilters= parseList(ensureMarker().getAttribute(EXCLUSION_FILTERS, "")); //$NON-NLS-1$
			} catch (CoreException ce) {
				fExclusionClassFilters= new String[]{};
			}
		}
		return fExclusionClassFilters;
	}

	protected void setExclusionClassFilters(String[] filters) {
		fExclusionClassFilters = filters;
	}
	
	/**
	 * @see org.eclipse.jdt.debug.core.IMDTFailureBreakpoint#getExclusionFilters()
	 */
	public String[] getExclusionFilters() {
		return getExclusionClassFilters();
	}

	/**
	 * @see org.eclipse.jdt.debug.core.IMDTFailureBreakpoint#getInclusionFilters()
	 */
	public String[] getInclusionFilters() {
		return getInclusionClassFilters();
	}

	/**
	 * @see org.eclipse.jdt.debug.core.IMDTFailureBreakpoint#setExclusionFilters(String[])
	 */
	public void setExclusionFilters(String[] filters) throws CoreException {
		String serializedFilters= serializeList(filters);
		
		if (serializedFilters.equals(ensureMarker().getAttribute(EXCLUSION_FILTERS, ""))) { //$NON-NLS-1$
			//no change
			return;
		}

		setExclusionClassFilters(filters);
		
		setAttribute(EXCLUSION_FILTERS, serializedFilters);	
	}

	/**
	 * @see org.eclipse.jdt.debug.core.IMDTFailureBreakpoint#setInclusionFilters(String[])
	 */
	public void setInclusionFilters(String[] filters) throws CoreException {
		String serializedFilters= serializeList(filters);
		
		if (serializedFilters.equals(ensureMarker().getAttribute(INCLUSION_FILTERS, ""))) { //$NON-NLS-1$
			//no change
			return;
		}

		setInclusionClassFilters(filters);
		
		setAttribute(INCLUSION_FILTERS, serializedFilters);
	}
	
	public String getFailureFilter()
	{
		return "*";
	}
	
}

