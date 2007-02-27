
package org.modelica.mdt.ui;

import org.eclipse.core.runtime.jobs.*;

public class MetaModelicaJobListener implements IJobChangeListener
{
	private boolean DEBUG = false;
	
    protected void debug(String message)
    {
    	if (DEBUG) System.out.println("MetaModelicaJobListener: " + message);
    }
	
	public void aboutToRun(IJobChangeEvent event) 
	{
		debug("aboutToRun: " + event.getJob().getName());
		debug("aboutToRunRule: " + event.getJob().getRule().toString());
		// delete the markers before we run the job			
		//if (event.getJob().getName().equals("Building workspace"))
		//	UIPlugin.getDefault().deleteMarkers();
	}
  
	public void awake(IJobChangeEvent event) 
	{
		debug("awake: " + event.getJob().getName());	}
	
	public void done(IJobChangeEvent event) 
	{
		debug("done: " + event.getJob().getName());		
	}
	
	public void running(IJobChangeEvent event) 
	{
		debug("running: " + event.getJob().getName());		
	}
	
	public void scheduled(IJobChangeEvent event) 
	{
		debug("scheduled: " + event.getJob().getName());		
	}
	
	public void sleeping(IJobChangeEvent event) 
	{
		debug("sleeping: " + event.getJob().getName());
	}

}
