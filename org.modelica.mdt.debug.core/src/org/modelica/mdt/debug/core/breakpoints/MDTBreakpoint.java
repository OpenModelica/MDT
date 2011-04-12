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

 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.model.Breakpoint;
import org.modelica.mdt.debug.core.IMDTBreakpoint;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;


public abstract class MDTBreakpoint extends Breakpoint implements IMDTBreakpoint {

	/**
	 * Breakpoint attribute storing the expired value (value <code>"org.modelica.mdt.debug.core.expired"</code>).
	 * This attribute is stored as a <code>boolean</code>. Once a hit count has
	 * been reached, a breakpoint is considered to be "expired".
	 */
	protected static final String EXPIRED = "org.modelica.mdt.debug.core.expired"; //$NON-NLS-1$
	/**
	 * Breakpoint attribute storing a breakpoint's hit count value
	 * (value <code>"org.modelica.mdt.debug.core.hitCount"</code>). This attribute is stored as an
	 * <code>int</code>.
	 */
	protected static final String HIT_COUNT = "org.modelica.mdt.debug.core.hitCount"; //$NON-NLS-1$
	/**
	 * Breakpoint attribute storing the number of debug targets a
	 * breakpoint is installed in (value <code>"org.modelica.mdt.debug.core.installCount"</code>).
	 * This attribute is a <code>int</code>.
	 */
	protected static final String INSTALL_COUNT = "org.modelica.mdt.debug.core.installCount"; //$NON-NLS-1$	
	
	/**
	 * Breakpoint attribute storing the fully qualified name of the type
	 * this breakpoint is located in.
	 * (value <code>"org.modelica.mdt.debug.core.typeName"</code>). This attribute is a <code>String</code>.
	 */
	protected static final String TYPE_NAME = "org.modelica.mdt.debug.core.typeName"; //$NON-NLS-1$		
	
	/**
	 * Breakpoint attribute storing suspend policy code for 
	 * this breakpoint.
	 * (value <code>"org.modelica.mdt.debug.core.suspendPolicy</code>).
	 * This attribute is an <code>int</code> corresponding
	 * to <code>IMDTBreakpoint.SUSPEND_VM</code> or
	 * <code>IMDTBreakpoint.SUSPEND_THREAD</code>.
	 */
	protected static final String SUSPEND_POLICY = "org.modelica.mdt.debug.core.suspendPolicy"; //$NON-NLS-1$			
	
	/**
	 * Stores the collection of requests that this breakpoint has installed in
	 * debug targets.
	 * key: a debug target
	 * value: the requests this breakpoint has installed in that target
	 */
	protected HashMap fRequestsByTarget;
	
	/**
	 * The list of threads (ThreadReference objects) in which this breakpoint will suspend,
	 * associated with the target in which each thread exists (JDIDebugTarget).
	 * key: targets the debug targets (IJavaDebugTarget)
	 * value: thread the filtered thread (IJavaThread) in the given target
	 */
	protected Map fFilteredThreadsByTarget;
	
	/**
	 * Stores the type name that this breakpoint was last installed
	 * in. When a breakpoint is created, the TYPE_NAME attribute assigned to it
	 * is that of its top level enclosing type. When installed, the type
	 * may actually be an inner type. We need to keep track of the type 
	 * type the breakpoint was installed in, in case we need to re-install
	 * the breakpoint for HCR (i.e. in case an inner type is HCR'd).
	 */
	protected String fInstalledTypeName = null;
	
	/**
	 * List of targets in which this breakpoint is installed.
	 * Used to prevent firing of more than one install notification
	 * when a breakpoint's requests are re-created.
	 */
	protected Set fInstalledTargets = null;
	
	/**
	 * List of active instance filters for this breakpoint
	 * (list of <code>IJavaObject</code>).
	 */
	protected List fInstanceFilters = null;
		
	/**
	 * Property identifier for a breakpoint object on an event request
	 */
	public static final String JAVA_BREAKPOINT_PROPERTY = "org.modelica.mdt.debug.breakpoint"; //$NON-NLS-1$
	
	/**
	 * MDTBreakpoint attributes
	 */	
	protected static final String[] fgExpiredEnabledAttributes= new String[]{EXPIRED, ENABLED};
	
	public MDTBreakpoint() {
		fRequestsByTarget = new HashMap(1);
		fFilteredThreadsByTarget= new HashMap(1);
	}	

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IBreakpoint#getModelIdentifier()
	 */
	public String getModelIdentifier() {
		return MDTDebugCorePlugin.ID_MDT_DEBUG_MODEL;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.Breakpoint#setMarker(org.eclipse.core.resources.IMarker)
	 */
	public void setMarker(IMarker marker) throws CoreException {
		super.setMarker(marker);
	}

	/**
	 * Add this breakpoint to the breakpoint manager,
	 * or sets it as unregistered.
	 */
	protected void register(boolean register) throws CoreException {
        DebugPlugin plugin = DebugPlugin.getDefault();
		if (plugin != null && register) {
            plugin.getBreakpointManager().addBreakpoint(this);
		} else {
			setRegistered(false);
		}
	}	
	
}
