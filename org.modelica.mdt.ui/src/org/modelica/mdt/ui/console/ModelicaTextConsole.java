package org.modelica.mdt.ui.console;

import java.io.IOException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleInputStream;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.eclipse.ui.part.IPageBookViewPage;
import org.eclipse.ui.progress.WorkbenchJob;

public class ModelicaTextConsole extends IOConsole 
{	
	public final static String         CONSOLE_TYPE = 
		"org.modelica.mdt.ui.console.ModelicaTextConsole";
	private IOConsoleOutputStream      outputStream = null;
	private IOConsoleInputStream       inputStream = null;	
	private OpenModelicaCommandHandler handler = null;
	PrintWelcomeMessageJob pj = new PrintWelcomeMessageJob();	
	
	public ModelicaTextConsole() 
	{
		super("OpenModelica Console", CONSOLE_TYPE, null, true);
		
		outputStream = newOutputStream();
		inputStream = getInputStream();
		inputStream.setColor(new Color(Display.getCurrent(), new RGB(0, 150, 0)));
		outputStream.setColor(new Color(Display.getCurrent(), new RGB(0x4b, 0x5c, 0xff)));
		
		handler = new OpenModelicaCommandHandler(inputStream, outputStream);
		handler.start();
		//setWaterMarks(1000, 50000000);
	}	
		
	public IOConsoleOutputStream getOutputStream()
	{
		return outputStream;
	}
	
	
    @Override
	public void activate()
	{
		// TODO Auto-generated method stub
		super.activate();
		if (outputStream == null)
		{
			outputStream = newOutputStream();
			outputStream.setColor(new Color(Display.getCurrent(), new RGB(0x4b, 0x5c, 0xff)));
		}
		if (inputStream == null)
		{
			inputStream = getInputStream();
			inputStream.setColor(new Color(Display.getCurrent(), new RGB(0, 150, 0)));			
		}
		if (handler == null)
		{
			handler = new OpenModelicaCommandHandler(inputStream, outputStream);
			handler.start();
		}
	}

    
    
	/*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.console.IOConsole#dispose()
     */
    @Override    
    protected void dispose() 
    {
    	try
    	{
    		// stop the command handler
    		if (handler != null)
    		{
	    		handler.interrupt();
	    		//handler.stop();
	    		handler = null;
    		}
            super.dispose();    		
    		// close the streams
    		if (inputStream != null)
    		{
    			inputStream.close();
    			inputStream = null;
    		}
    		if (outputStream != null)
    		{
    			outputStream.close();
    			outputStream = null;
    		}
    	}
    	catch(IOException e)
    	{
    		//e.printStackTrace();
    	}    	
    	catch(NullPointerException e)
    	{
    		//e.printStackTrace();
    	}    	    	
    }
    
	@Override
	public void clearConsole()
	{
		// TODO Auto-generated method stub
		super.clearConsole();
		pj.schedule(10);
	}	
        
	
    public IPageBookViewPage createPage(IConsoleView view) 
    {
        return new ModelicaTextConsolePage(this, view);
    }    
    
    /**
     * Job to display the welcome, runs in the  UI thread.
     */
    private class PrintWelcomeMessageJob extends WorkbenchJob 
    {            
        /**
         * Creates a new job to display the welcome message.
         */
        PrintWelcomeMessageJob() 
        {
            super("Print Welcome Message Job"); //$NON-NLS-1$
            setSystem(false);
        }
        
        /* (non-Javadoc)
         * @see org.eclipse.ui.progress.UIJob#runInUIThread(org.eclipse.core.runtime.IProgressMonitor)
         */
        public IStatus runInUIThread(IProgressMonitor monitor) {
            IJobManager jobManager = Job.getJobManager();
            try {
                jobManager.join(this, monitor);
            } catch (OperationCanceledException e1) {
                return Status.CANCEL_STATUS;
            } catch (InterruptedException e1) {
                return Status.CANCEL_STATUS;
            }
        	if (getDocument() == null) {
        		return Status.OK_STATUS;
        	}
        	        	
        	if (outputStream != null)
        	{
        		try
        		{
        			outputStream.write(ModelicaTextConsoleMessages.WELCOME);
        			outputStream.flush();
        		}
        		catch(IOException e) {
        			// do nothing.
        		}
        	}
        	
        	return Status.OK_STATUS;
        }
    }
	
}		
