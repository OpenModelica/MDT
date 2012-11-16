
package org.modelica.mdt.ui.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * This class holds the information of a connection.
 * It will be used as part of storage for when saving
 * data of an analyze about a certain class. Each
 * connection represents one or many dependencies between
 * two classes.
 *
 * @author: Magnus Sjöstrand
 */
public class ModelicaConnection {
	final String id; 
	final String label;
	final ModelicaNode source;
	final ModelicaNode destination;
	final int style;
	final Map<String, ArrayList<Integer>> lineRefs = new HashMap<String, ArrayList<Integer>>();
	boolean bending = false;

	public ModelicaConnection(String id, String label, ModelicaNode source, ModelicaNode destination, int style, String connectionName, ArrayList<Integer> lines) {
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

	public ModelicaNode getSource() {
		return source;
	}
	public ModelicaNode getDestination() {
		return destination;
	}
	public int getStyle() {
		return style;
	}
	
	public void setBending() {
		this.bending = true;
	}

	public boolean isBending() {
		return this.bending;
	}
} 
