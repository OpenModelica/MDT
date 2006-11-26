/**
 * @author Adrian Pop
 */
package org.modelica.mdt.ui.console;

import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleListener;
import org.eclipse.ui.console.IConsoleManager;
import org.modelica.mdt.ui.UIPlugin;

/**
 * Creates a new console into which users can 
 * send commands to the OpenModelica compiler.
 * 
 * @author Adrian Pop
 *
 */
public class ModelicaConsoleFactory implements IConsoleFactory 
{
    private IConsoleManager     fConsoleManager = null;
    private ModelicaTextConsole fConsole = null;

    public ModelicaConsoleFactory() 
    {   
        fConsoleManager = ConsolePlugin.getDefault().getConsoleManager();
        fConsoleManager.addConsoleListener(new IConsoleListener() 
        {
            public void consolesAdded(IConsole[] consoles) 
            {
            	/*
                for (int i = 0; i < consoles.length; i++) 
                {
                    if(consoles[i] == fConsole) 
                    {
                    	fConsole.initialize();
                    }
                } 
                */           	
            }

            public void consolesRemoved(IConsole[] consoles) 
            {
                for (int i = 0; i < consoles.length; i++) 
                {
                    if(consoles[i] == fConsole) 
                    {
                    	fConsole.destroy();
                        fConsole = null;
                    }
                }
            }
        
        });
    }
    /* (non-Javadoc)
     * @see org.eclipse.ui.console.IConsoleFactory#openConsole()
     */
    public void openConsole() 
    {
        if (fConsole == null) 
        {
            fConsole = UIPlugin.getDefault().getModelicaTextConsole(); 
	        fConsoleManager.addConsoles(new IConsole[]{fConsole});
        }
        fConsoleManager.showConsoleView(fConsole);
    }
    
}
