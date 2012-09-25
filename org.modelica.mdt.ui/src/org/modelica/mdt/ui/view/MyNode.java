
package org.modelica.mdt.ui.view;

import java.util.ArrayList;
import java.util.List;

public class MyNode {
	private final int id;
	private final String name;
	boolean expandable;
	private List<MyNode> connections;
	private int color;

	public MyNode(int id, String name, int color) {
		this.id = id;
		this.name = name;
		this.expandable = true;
		this.connections = new ArrayList<MyNode>();
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public boolean isExpandable() {
		return expandable;
	}

	public List<MyNode> getConnectedTo() {
		return connections;
	}

	public int getColor() {
		return color;
	}
} 