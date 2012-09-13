
package org.modelica.mdt.ui.view;

import java.util.ArrayList;

public class GraphPackage
{
	private String name;
	private ArrayList<String> classList = new ArrayList<String>();

	public GraphPackage(String name) {
		this.name = name;
	}

	public void addClass(String packageClass) {
		classList.add(packageClass);
	}

	public ArrayList<String> getNodes() {
		return classList;
	}

	public String getName() {
		return name;
	}

	public boolean containsClass(String compare) {
		for (int i = 0; i < classList.size(); i++)
			if (classList.get(i).equals(compare))
				return true;
		return false;
	}
}
