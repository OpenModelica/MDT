
package org.modelica.mdt.ui.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds the information of a node.
 * It will be used as part of storage for when saving
 * data of an analyze about a certain class. Each node
 * represents one class or nested class.
 *
 * @author: Magnus Sjöstrand
 */
public class ModelicaNode extends ModelicaNodeInfo{
	private int id;
	private final String name;
	boolean expandable;
	private List<ModelicaNode> connections;
	private int color;
	private boolean simulated;

	public ModelicaNode(int id, String name, int color) {
		this.id = id;
		this.name = name;
		this.expandable = true;
		this.connections = new ArrayList<ModelicaNode>();
		this.color = color;
		this.simulated = true;
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

	public List<ModelicaNode> getConnectedTo() {
		return connections;
	}

	public int getColor() {
		return color;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setClassDescription(String classDescription) {
		this.classDescription = classDescription;
	}

	public void setClassPosition(String classPosition) {
		this.classPosition = classPosition;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	
	public ArrayList<String> getToolTipInfo() {
		ArrayList<String> toolTipList = new ArrayList<String>();
		toolTipList.add(this.classType);
		toolTipList.add(this.className);
		toolTipList.add(this.classDescription);
		toolTipList.add(this.classPosition);
		toolTipList.add(this.classPath);
		return toolTipList;
	}
	
	public void setId(int newID){
		this.id = newID;
	}
	
	public boolean isSimulated(){
		return this.simulated;
	}
} 