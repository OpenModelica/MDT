
package org.modelica.mdt.ui.console;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.console.TextConsole;
import org.eclipse.ui.console.TextConsoleViewer;
import org.eclipse.ui.progress.WorkbenchJob;

public class ModelicaTextConsoleViewer extends TextConsoleViewer
{
    WorkbenchJob revealJob = new WorkbenchJob("Modelica Console Reveal End of Document") {//$NON-NLS-1$
        public IStatus runInUIThread(IProgressMonitor monitor) {
            StyledText textWidget = getTextWidget();
            if (textWidget != null) {
                int lineCount = textWidget.getLineCount();
                textWidget.setTopIndex(lineCount - 1);
            }
            return Status.OK_STATUS;
        }
    };
	
	public ModelicaTextConsoleViewer(Composite parent, TextConsole console)
	{
		super(parent, console);
		revealJob.setSystem(false);
		console.clearConsole();
	}
	
	
	@Override
	protected void handleVisibleDocumentChanged(DocumentEvent event)
	{
		// TODO Auto-generated method stub
		super.handleVisibleDocumentChanged(event);
		revealEndOfDocument();
		setRangeIndication(event.fOffset+event.fText.length(), 0, true);
	}
			
	/**
	 * Hook method called when the visible document has been changed. 	
	 * @param event
	 */
	protected void revealEndOfDocument() 
	{
		super.revealEndOfDocument();
		revealJob.schedule();
	}		
	
}
