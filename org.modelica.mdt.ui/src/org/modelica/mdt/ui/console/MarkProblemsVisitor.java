/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Link�pings universitet, Department of
 * Computer and Information Science, PELAB
 *
 * All rights reserved.
 *
 * (The new BSD license, see also
 * http://www.opensource.org/licenses/bsd-license.php)
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * * Neither the name of Link�pings universitet nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.modelica.mdt.ui.console;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.ui.console.FileLink;
import org.eclipse.ui.console.TextConsole;
import org.eclipse.ui.texteditor.MarkerUtilities;
import org.modelica.mdt.ui.UIPlugin;

/**
 * Visitor used to load set markers on files with problems
 * 
 * @author Adrian Pop
 */
public class MarkProblemsVisitor implements IResourceVisitor
{
	protected String strFileName;
	protected String strLine;
	protected String strMsg;
	protected int offset;
	protected TextConsole console;
    private boolean DEBUG = false;	
	
	public MarkProblemsVisitor(
			String problemFileName, 
			String problemLine, 
			String problemMessage, 
			int problemOffset, 
			TextConsole problemConsole)
	{
		strFileName = problemFileName;
		strLine = problemLine;
		strMsg = problemMessage;
		offset = problemOffset;
		console = problemConsole;
		String value = Platform.getDebugOption  /*load trace/ConsolePatternMatching flag */
		("org.modelica.mdt.ui/trace/MarkProblemsVisitor");
		if (value != null && value.equalsIgnoreCase("true"))
		{
			DEBUG = true;
		}
		debug("constructor");    	
    }
    
    protected void debug(String message)
    {
    	if (DEBUG) System.out.println("MarkProbemsVisitor: " + message);
    }
	
	/**
	 * @param resource the resource in the project that we are 'visiting'
	 */
	public boolean visit(IResource resource) throws CoreException
	{
		debug("VisitResName:"+ resource.getName() + " typ:" + resource.getType());
		IResource iResource = null;		
		switch (resource.getType())
		{
		case IResource.FOLDER: 
			iResource = ((IFolder)resource).findMember(strFileName);
			break;
		case IResource.PROJECT:
			iResource = ((IProject)resource).findMember(strFileName);
			break;
		case IResource.ROOT:
			iResource = ((IWorkspace)resource).getRoot().findMember(strFileName);
			break;
		default:
			return true;
		}
		
		if (iResource != null)
		{			
			IPath path = iResource.getProjectRelativePath();
			IFile iFile = iResource.getProject().getFile(path);
			//iFile = (IFile) iResource;
			// do not delete any markers, they are deleted someplace else
			//iFile.deleteMarkers(UIPlugin.METAMODELICA_BULD_MARKER_ID, false,
			//		IResource.DEPTH_INFINITE);
	    	try
	    	{
	        	int lineNumber = Integer.parseInt(strLine);
	        	FileLink fileLink = null;
	        	if (iFile != null)
	        	{
	            	debug("FileInProject:"+iResource.getName());        		
//		        	String editorID = null;
//			        	if (iFile.getName().endsWith(".mo")) 
//			        		editorID = "org.modelica.mdt.ui.editor.ModelicaEditor";
//			        	if (iFile.getName().endsWith(".c")   || 
//			        		iFile.getName().endsWith(".cpp") ||
//			        		iFile.getName().endsWith(".h") ||
//			        		iFile.getName().endsWith(".cc")) 
//			        		editorID = "org.";	        	
		        	fileLink = new FileLink(iFile, null /* default Editor! */,-1,-1,lineNumber);    		
		    		console.addHyperlink(
		    					fileLink, 
		    					offset, 
		    					strFileName.length() + 1 + strLine.length());
			        setMarker(iFile, strMsg, lineNumber);
			        /*
			        if (UIPlugin.getDefault().containsMarkedFile(iFile))
			        {
			        	//delete the marked file
				        UIPlugin.getDefault().deleteMarkedFile(iFile);			        	
			        }
			        UIPlugin.getDefault().addMarkedFile(iFile);
			        */
	        	}
	        	else
	              debug("FileInProject: not found!");
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	return false; /* if we found it, don't visit further */
		}
		
		/*
		 * Return true to indicate that the children of this resource should
		 * be visited.
		 */
		return true;
	}
	
    protected static final String ERROR = "Error"; 
    protected static final String WARNING = "Warning"; 
    protected static final String C_ERROR = "error"; 
    protected static final String C_WARNING = "warning";     
	
    /**
     * @param in_file
     * @param message
     * @param lineNumber
     * @throws CoreException
     */
    protected void setMarker(IFile in_file, String message, int lineNumber) throws CoreException 
    {
    	debug("MetaModelicaPaternMatchListener.setMarker:");
    	debug("File:" + in_file.getName());
    	debug("Line:" + lineNumber);
    	debug("Message:" + message);    	
        Map<String, Integer> attributes = new HashMap<String, Integer>();
        message = message.trim();        
        if (message.startsWith(":") || Character.isDigit(message.charAt(0)))
        {        	
            message = message.substring(1);
            message = message.trim();            
        	while (Character.isDigit(message.charAt(0))) message = message.substring(1);
            message = message.trim();
            if (message.startsWith(":")) message = message.substring(1);
        }
        message = message.trim();
        if (message.startsWith(ERROR) || message.startsWith(C_ERROR))
            attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
        else if (message.startsWith(WARNING) || message.startsWith(C_WARNING))
            attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_WARNING));
        else
            attributes.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_INFO));
    
        /* do not set the marker if already set */
		IMarker[] markers = in_file.findMarkers(UIPlugin.METAMODELICA_BULD_MARKER_ID, false, IResource.DEPTH_INFINITE);
		for (IMarker marker : markers)
		{
			if (message.equals(MarkerUtilities.getMessage(marker)) &&
				MarkerUtilities.getLineNumber(marker) == lineNumber)
			{
				debug("Marker NOT SET as is already there!");
				return;
			}
		}

        debug("Creating marker: "+in_file.getName()+":"+lineNumber+" "+message);
        MarkerUtilities.setLineNumber(attributes, lineNumber);
        MarkerUtilities.setMessage(attributes, message);
        MarkerUtilities.createMarker(in_file, attributes, UIPlugin.METAMODELICA_BULD_MARKER_ID);
    }    
	
}
