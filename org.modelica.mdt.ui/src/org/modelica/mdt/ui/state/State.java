
package org.modelica.mdt.ui.state;

public class State {
	
	public static State getInstance() {
		return instance;
	}
	
	public boolean markOccurrencesOn() {
		return markOccurrences;
	}
	
	public void toggleMarkOccurrences() {
		markOccurrences = !markOccurrences;
	}
	
	private State() {
		// Empty.
	}

	private static State instance = new State();
	
	private static boolean markOccurrences = false;
}
