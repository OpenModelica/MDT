
package org.modelica.mdt.ui.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MyConnection {
	final String id; 
	final String label;
	final MyNode source;
	final MyNode destination;
	final int style;
	final Map<String, ArrayList<Integer>> lineRefs = new HashMap<String, ArrayList<Integer>>();

	public MyConnection(String id, String label, MyNode source, MyNode destination, int style, String connectionName, ArrayList<Integer> lines) {
		this.id = id;
		this.label = label;
		this.source = source;
		this.destination = destination;
		this.style = style;
		this.lineRefs.put(connectionName, lines);
	}

	public void addLines(String connectionName, ArrayList<Integer> lines) {
		if (lineRefs.containsKey(connectionName)){
			// TODO: Can we make a better solution to avoid duplicates by change the map's inner types?
			
			lines.addAll(lineRefs.get(connectionName));
			HashSet<Integer> hs = new HashSet<Integer>();
			hs.addAll(lines);
			lines.clear();
			lines.addAll(hs);	
		}
		lineRefs.remove(connectionName);
		lineRefs.put(connectionName, lines);
		
	}

	public String getLabel() {
		return label;
	}

	public MyNode getSource() {
		return source;
	}
	public MyNode getDestination() {
		return destination;
	}
	public int getStyle() {
		return style;
	}

} 
