/*
 * 
 */
package org.openmodelica.simulation.environment.filehandling;

	//file filter from sun! 

	import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.filechooser.FileFilter;

	// TODO: Auto-generated Javadoc
/**
	 * A convenience implementation of FileFilter that filters out
	 * all files except for those type extensions that it knows about.
	 * 
	 * In this case the type extension is a Modelica file (*.mo)
	 *
	 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
	 */
	public class ModelicaModelFileFilter extends FileFilter{

//	    private static String TYPE_UNKNOWN = "Type Unknown";
//	    private static String HIDDEN_FILE = "Hidden File";

	    /** The filters. */
private Hashtable<String, ModelicaModelFileFilter> filters = null;
	    
    	/** The description. */
    	private String description = null;
	    
    	/** The full description. */
    	private String fullDescription = null;
	    
    	/** The use extensions in description. */
    	private boolean useExtensionsInDescription = true;

	    /**
	     * Creates a file filter. If no filters are added, then all
	     * files are accepted.
	     *
	     * @see #addExtension
	     */
	    public ModelicaModelFileFilter() {
		this.filters = new Hashtable<String, ModelicaModelFileFilter>();
	    }

	    /**
    	 * Creates a file filter that accepts files with the given extension.
    	 * Example: new ModelicaModelFileFilter("mo");
    	 *
    	 * @param extension the extension
    	 * @see #addExtension
    	 */
	    public ModelicaModelFileFilter(String extension) {
		this(extension,null);
	    }

	    /**
    	 * Creates a file filter that accepts the given file type.
    	 * Example: new ModelicaModelFileFilter("mo", "Modelica File");
    	 * 
    	 * Note that the "." before the extension is not needed. If
    	 * provided, it will be ignored.
    	 *
    	 * @param extension the extension
    	 * @param description the description
    	 * @see #addExtension
    	 */
	    public ModelicaModelFileFilter(String extension, String description) {
		this();
		if(extension!=null) addExtension(extension);
	 	if(description!=null) setDescription(description);
	    }

	    /**
    	 * Creates a file filter from the given string array.
    	 * Example: new ModelicaModelFileFilter(String {"mo", "xx"});
    	 * 
    	 * Note that the "." before the extension is not needed and
    	 * will be ignored.
    	 *
    	 * @param filters the filters
    	 * @see #addExtension
    	 */
	    public ModelicaModelFileFilter(String[] filters) {
		this(filters, null);
	    }

	    /**
    	 * Creates a file filter from the given string array and description.
    	 * Example: new ModelicaModelFileFilter(String {"mo", "xx"}, "mo and xx Modelica Files");
    	 * 
    	 * Note that the "." before the extension is not needed and will be ignored.
    	 *
    	 * @param filters the filters
    	 * @param description the description
    	 * @see #addExtension
    	 */
	    public ModelicaModelFileFilter(String[] filters, String description) {
		this();
		for (int i = 0; i < filters.length; i++) {
		    // add filters one by one
		    addExtension(filters[i]);
		}
	 	if(description!=null) setDescription(description);
	    }

	    /**
    	 * Return true if this file should be shown in the directory pane,
    	 * false if it shouldn't.
    	 * 
    	 * Files that begin with "." are ignored.
    	 *
    	 * @param f the f
    	 * @return true, if successful
    	 * @see #getExtension
    	 * @see FileFilter#accepts
    	 */
	    public boolean accept(File f) {
		if(f != null) {
		    if(f.isDirectory()) {
			return true;
		    }
		    String extension = getExtension(f);
		    if(extension != null && filters.get(getExtension(f)) != null) {
			return true;
		    };
		}
		return false;
	    }

	    /**
    	 * Return the extension portion of the file's name .
    	 *
    	 * @param f the f
    	 * @return the extension
    	 * @see #getExtension
    	 * @see FileFilter#accept
    	 */
	     public String getExtension(File f) {
		if(f != null) {
		    String filename = f.getName();
		    int i = filename.lastIndexOf('.');
		    if(i>0 && i<filename.length()-1) {
			return filename.substring(i+1).toLowerCase();
		    };
		}
		return null;
	    }

	    /**
    	 * Adds a filetype "dot" extension to filter against.
    	 * 
    	 * For example: the following code will create a filter that filters
    	 * out all files except those that end in ".jpg" and ".tif":
    	 * 
    	 * ModelicaModelFileFilter filter = new ModelicaModelFileFilter();
    	 * filter.addExtension("jpg");
    	 * filter.addExtension("tif");
    	 * 
    	 * Note that the "." before the extension is not needed and will be ignored.
    	 *
    	 * @param extension the extension
    	 */
	    public void addExtension(String extension) {
		if(filters == null) {
		    filters = new Hashtable<String, ModelicaModelFileFilter>(5); //TODO GENERIC ENTFERNEN FALLS FEHLER
		}
		filters.put(extension.toLowerCase(), this);
		fullDescription = null;
	    }


	    /**
    	 * Returns the human readable description of this filter. For
    	 * example: "JPEG and GIF Image Files (*.jpg, *.gif)"
    	 *
    	 * @return the description
    	 * @see setDescription
    	 * @see setExtensionListInDescription
    	 * @see isExtensionListInDescription
    	 * @see FileFilter#getDescription
    	 */
	    public String getDescription() {
		if(fullDescription == null) {
		    if(description == null || isExtensionListInDescription()) {
	 		fullDescription = description==null ? "(" : description + " (";
			// build the description from the extension list
			Enumeration<String> extensions = filters.keys(); //TODO STRING ENTFERNEN FALLS FEHLER
			if(extensions != null) {
			    fullDescription += "." + (String) extensions.nextElement();
			    while (extensions.hasMoreElements()) {
				fullDescription += ", ." + (String) extensions.nextElement();
			    }
			}
			fullDescription += ")";
		    } else {
			fullDescription = description;
		    }
		}
		return fullDescription;
	    }

	    /**
    	 * Sets the human readable description of this filter. For
    	 * example: filter.setDescription("Gif and JPG Images");
    	 *
    	 * @param description the new description
    	 * @see setDescription
    	 * @see setExtensionListInDescription
    	 * @see isExtensionListInDescription
    	 */
	    public void setDescription(String description) {
		this.description = description;
		fullDescription = null;
	    }

	    /**
    	 * Determines whether the extension list (.mo, .xx, etc) should
    	 * show up in the human readable description.
    	 * 
    	 * Only relevant if a description was provided in the constructor
    	 * or using setDescription();
    	 *
    	 * @param b the new extension list in description
    	 * @see getDescription
    	 * @see setDescription
    	 * @see isExtensionListInDescription
    	 */
	    public void setExtensionListInDescription(boolean b) {
		useExtensionsInDescription = b;
		fullDescription = null;
	    }

	    /**
    	 * Returns whether the extension list (.mo, .xx, etc) should
    	 * show up in the human readable description.
    	 * 
    	 * Only relevant if a description was provided in the constructor
    	 * or using setDescription();
    	 *
    	 * @return true, if is extension list in description
    	 * @see getDescription
    	 * @see setDescription
    	 * @see setExtensionListInDescription
    	 */
	    public boolean isExtensionListInDescription() {
		return useExtensionsInDescription;
	    }
}
