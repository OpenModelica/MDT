package org.modelica.mdt.ui.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.modelica.mdt.ui.state.State;

public class CommandToggleMarkOccurrences extends AbstractHandler {

	public CommandToggleMarkOccurrences() {
		
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		State.getInstance().toggleMarkOccurrences();

		return null;
	}

}
