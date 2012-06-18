package org.openmodelica.modelicaml.simulation.simresults;

import java.io.IOException;
import java.util.ArrayList;

public interface IResultsReader {

	public ArrayList<String> getNames();
	public double[] getValues(String name) throws Exception;
	public String getFilePath();
	public void releaseFile() throws IOException;
}
