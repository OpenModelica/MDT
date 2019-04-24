package org.modelica.mdt.debug.core.model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Stack;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

/**
 * A variable in a MDT stack frame
 */
public class MDTVariable extends MDTDebugElement implements IVariable {

    private static boolean DEBUG = false;
	String line;
	DefaultMutableTreeNode top = null;
	DefaultMutableTreeNode modelicaVariables = null; 
	DefaultMutableTreeNode child = null;
	DefaultMutableTreeNode parent = null; 

	DefaultTreeModel treeModel = null;
	
    
	
    public static final int KIND_UNKNOWN  = -1;
    public static final int KIND_VARIABLE = 0;
    public static final int KIND_LIST     = 1;
    public static final int KIND_OPTION   = 2;
    public static final int KIND_LVAR     = 3;
    public static final int KIND_VECTOR   = 4;
    public static final int KIND_ARRAY    = 5;
    public static final int KIND_DATATYPE = 6;
    public static final int KIND_STRING   = 7;    
    public static final int KIND_REAL     = 8;
    public static final int KIND_INT      = 9;
    public static final int KIND_BOOLEAN  = 10;
    public static final int KIND_FUNCTION = 11;    
    public static final int KIND_TUPLE    = 12;       
    public static final int KIND_NORMAL   = 13;    
	
	// name & stack frmae
	private String fName;
	private int fNumber; /* variable Number in the frame */
	private IValue fValue = null;
	private String fReferenceTypeName;
	private MDTStackFrame fFrame;
	
	private Socket fCommandSocket;	
	private PrintWriter fCommandWriter;
	private BufferedReader fReplyReader;
	
	
	/**
	 * Constructs a variable contained in the given stack frame
	 * with the given name.
	 * 
	 * @param frame owning stack frame
	 * @param name variable name
	 */
	public MDTVariable(MDTStackFrame frame, String name, int Number) {
		super(frame.getMDTDebugTarget());
		fFrame = frame;
		fName = name;
		fNumber = Number;
		fReferenceTypeName = "reaplaceable type Any";
		fCommandSocket = frame.getMDTDebugTarget().getCommandSocket();
		fCommandWriter = frame.getMDTDebugTarget().getCommandWriter();
		fReplyReader = frame.getMDTDebugTarget().getReplyReader();
		fValue = null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#getValue()
	 */
	public synchronized IValue getValue() throws DebugException {
		if (fValue == null)
		{
			sendRequest(
				IMDTDebugCommands.CMD_FRAME_VARIABLE_VALUE + " " + 
				getStackFrame().getIdentifier() + ":" + 
				getNumber());
			
			// now we have the variable into treeModel
			// get the root:
			DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
			// get the first variable
			ModelicaVariableInfo vi = (ModelicaVariableInfo)root.getUserObject();
			// set the type
			fReferenceTypeName = vi.rmlType;
			fValue = buildMDTValue(root);
		}
		return fValue;
	}

	class ComponentVariable extends MDTDebugElement implements IVariable
	{
		protected String fName;
		protected IValue fValue;
		protected String fType;
		
		public ComponentVariable(MDTDebugTarget t, String name, IValue value, String type)
		{
			super(t);
			fName = name; fValue = value; fType = type;
		}
		public String getName() throws DebugException 						{ return fName;  }
		public String getReferenceTypeName() throws DebugException 			{ return fType;  }
		public IValue getValue() throws DebugException 						{ return fValue; }
		public boolean hasValueChanged() throws DebugException 				{ return false;  }
		public boolean supportsValueModification()							{ return false;  }
		public void setValue(IValue value) throws DebugException			{ /* nothing */  }
		public void setValue(String expression) throws DebugException 		{ /* nothing */  }
		public boolean verifyValue(IValue value) throws DebugException  	{ return false;  }
		public boolean verifyValue(String expression) throws DebugException	{ return false;  }
	}
	
	
	public IValue buildMDTValue(DefaultMutableTreeNode root)
	{
		Enumeration kids = root.children();
		ModelicaVariableInfo vi = (ModelicaVariableInfo)root.getUserObject();
		String value = vi.toString();		
		if (!kids.hasMoreElements())
		{
			return new MDTValue(this.getMDTDebugTarget(), value, new IVariable[0]);
		}
		Vector<IVariable> v = new Vector<IVariable>(); 
		while (kids.hasMoreElements())
		{
			DefaultMutableTreeNode k = (DefaultMutableTreeNode)kids.nextElement();
			v.add(buildComponentVariable(k));
		}
		IVariable[] vars = new IVariable[v.size()];
		v.toArray(vars);
		return new MDTValue(this.getMDTDebugTarget(), value, vars);
	}
	
	public IVariable buildComponentVariable(DefaultMutableTreeNode root)
	{
		Enumeration kids = root.children();
		ModelicaVariableInfo vi = (ModelicaVariableInfo)root.getUserObject();
		String type = vi.rmlType; 
		// String value = vi.toString();
		if (!kids.hasMoreElements())
		{
			return new ComponentVariable(
					this.getMDTDebugTarget(), 
					vi.rmlName, 
					new MDTValue(this.getMDTDebugTarget(), vi.rmlValue, new IVariable[0]), type);
		}
		Vector<IVariable> v = new Vector<IVariable>(); 
		while (kids.hasMoreElements())
		{
			DefaultMutableTreeNode k = (DefaultMutableTreeNode)kids.nextElement();
			v.add(buildComponentVariable(k));
		}
		IVariable[] vars = new IVariable[v.size()];
		v.toArray(vars);
		return new ComponentVariable(this.getMDTDebugTarget(), vi.rmlName, new MDTValue(this.getMDTDebugTarget(), vi.rmlValue, vars), type);
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#getName()
	 */
	public String getName() throws DebugException {
		return fName;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#getName()
	 */
	public int getNumber() throws DebugException {
		return fNumber;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#getReferenceTypeName()
	 */
	public String getReferenceTypeName() throws DebugException {
		return fReferenceTypeName;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#hasValueChanged()
	 */
	public boolean hasValueChanged() throws DebugException {
		return false;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValueModification#setValue(java.lang.String)
	 */
	public void setValue(String expression) throws DebugException {
		sendRequest("setvar " + getStackFrame().getIdentifier() + " " + getName() + " " + expression);
		fireChangeEvent(DebugEvent.CONTENT);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValueModification#setValue(org.eclipse.debug.core.model.IValue)
	 */
	public void setValue(IValue value) throws DebugException {
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValueModification#supportsValueModification()
	 */
	public boolean supportsValueModification() {
		return true;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValueModification#verifyValue(java.lang.String)
	 */
	public boolean verifyValue(String expression) throws DebugException {
		return true;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValueModification#verifyValue(org.eclipse.debug.core.model.IValue)
	 */
	public boolean verifyValue(IValue value) throws DebugException {
		return false;
	}
	
	/**
	 * Sends a request to the MDT Debugger and will get one of IMDTDebugCommands.EVENT_*
	 * 
	 * @param cmd
	 *            debug command
	 * @throws DebugException
	 *             if the request fails
	 */
	public String sendRequest(String cmd) throws DebugException 
	{
		synchronized (fCommandSocket) {
			System.out.println("Sending command:" + cmd);
			fCommandWriter.println(cmd);
			fCommandWriter.flush();
			
			/*
			try {
				// wait for reply
				String reply = fReplyReader.readLine();
				System.out.println("Got reply:" + reply);
				System.out.flush();
				return reply;
			} catch (IOException e) {
				requestFailed("Request failed: " + cmd, e);
			}
			*/
			top = new DefaultMutableTreeNode("TOP");
			treeModel = new DefaultTreeModel(top);
			receiveAndBuildValue();
			//System.out.println("Variable tree:" + treeModel.toString());
			treeModel.setRoot(((DefaultMutableTreeNode)treeModel.getRoot()).getFirstChild());
			return treeModel.toString();
		}
	}

	public boolean receiveAndBuildValue()
	{
		// Create a socket object from the ServerSocket to listen and accept 
		// connections.
		// Open input and output streams

		try 
		{
			Stack<DefaultMutableTreeNode> stack = new Stack<DefaultMutableTreeNode>();
			DefaultMutableTreeNode node = null;
			int currentDepth = -1;
			ModelicaVariableInfo var = null; 
			/*
			 * first reading the variable type\n then the contents:
			 * depth|vName|vValue|vType
			 * false|bool
			 * true|bool
			 * number:enumer:DCONSTRUCTOR|TYPE 
			 * int|int
			 * pointer:function (same module)|TYPE
			 * %f|real
			 * -|string -> <.$STRING_START$>STRING<.$STRING_END$.>
			 * pointer:function (external module)|TYPE
			 * TUPLE[slots]|TYPE 
			 * VECTOR[SLOTS]|TYPE
			 * ARRAY[SLOTS]|TYPE 
			 * LIST_START|TYPE| -> LIST_END|TYPE");
			 * NONE[0]|TYPE 
			 * SOME[1]|TYPE
			 * DATATYPE[slots]|TYPE|FILE|%d.%d.%d.%d" 
			 * ??UNNAMED??[slots]|TYPE
			 * LVAR(UNKNOWN)
			 */
			int which = 0;
			String vName = null, vType = null, vValue = null;
			int nKind = -1;
			int nDepth = 0;
			int maxDepth = 0;

			// As long as we receive data, send Ok to the client.
			while (true) 
			{
				line = fReplyReader.readLine(); 
				if (DEBUG) System.err.println("Received:" + line);
				if (line == null) break;
				if (line.compareTo("<.$STOP$.>") == 0) 
				{ 
					break;
				}				
				if (line.compareTo("value not found") == 0)
				{
					node = new DefaultMutableTreeNode(
							new ModelicaVariableInfo(KIND_VARIABLE, fName, "value not found", "value not found"));
					treeModel.insertNodeInto(node, top, top.getChildCount());
					break;
				}
				if (line.compareTo("variable not found") == 0)
				{
					node = new DefaultMutableTreeNode(
							new ModelicaVariableInfo(KIND_VARIABLE, fName, "variable not found", "variable not found"));
					treeModel.insertNodeInto(node, top, top.getChildCount());
					break;
				}				
				if (line.compareTo("variable type not found") == 0)
				{
					node = new DefaultMutableTreeNode(new ModelicaVariableInfo(KIND_VARIABLE, fName, "variable type not found", "variable type not found"));
					treeModel.insertNodeInto(node, top, top.getChildCount());
					break;
				}				
				if (line.compareTo("<.$STRING_START$.>") == 0) 
				{ 
					// string reading
					if (DEBUG) System.err.println("Reading strings");
					String text = "";
					while (true)
					{
						line = fReplyReader.readLine(); if (DEBUG) System.err.println("Received:" + line);
						if (line == null) continue;
						else if (line.compareTo("<.$STRING_END$.>") != 0)
							 	text += line + "\n"; /* append to the little boy */
						else 
							{
							    /* remove the last \n shit! */
								text = text.substring(0,text.length()-1);
								break;
							}
					}
					
					((ModelicaVariableInfo)((DefaultMutableTreeNode)stack.lastElement()).getUserObject()).rmlValue = "\"" + text + "\"";;
					((ModelicaVariableInfo)((DefaultMutableTreeNode)stack.lastElement()).getUserObject()).nKind = KIND_STRING;
					if (DEBUG) System.err.println("Read string:" + text);
				}
				else
				if (which < 1) 
				{
					switch(which)
					{
						case 0: { vType = line; break; }     // var type						
					}
					which = which + 1;
					if (which == 1)
					{
						// everything's read, build the var, put it in the list
						// "file:///c:/dev/modelica/modeq/"
						/* variableFromRML.addLast( */
						node = new DefaultMutableTreeNode(new ModelicaVariableInfo(KIND_VARIABLE, fName, vValue, vType));
						treeModel.insertNodeInto(node, top, top.getChildCount());
						stack.push(node);
					}
				}
				else
				{ 				
					// parse line here!
					String tmp = null;					
					tmp = line.substring(0, line.indexOf("|"));
					nDepth = Integer.valueOf(tmp).intValue();
					line = line.substring(line.indexOf("|")+1,line.length());					
					String sKind = line.substring(0, line.indexOf("|")+1);
					line = line.substring(line.indexOf("|")+1,line.length());
					vName = line.substring(0, line.indexOf("|"));
					line = line.substring(line.indexOf("|")+1,line.length());					
					vValue = line.substring(0, line.indexOf("|"));
					line = line.substring(line.indexOf("|")+1,line.length());
					vType = line;
					if (sKind == null) nKind = KIND_NORMAL;
					else
					{
						/*java expects this!
						depth|ty|vName|vValue|vType|vFile|sl.sc.el.ec\n
						where ty:
						  st<ring>,
						  da<tatype constructor>,
						  tu<ple>,
						  li<st>,
						  en<enumer>,
						  op<option>,
						  lv<ar>,
						  re<al>,
						  in<t>,
						  bo<olean>
						  fu<nction>
						  ve<ctor>*/
					  if (sKind.compareTo("st|") == 0) nKind = KIND_STRING;
					  if (sKind.compareTo("da|") == 0) nKind = KIND_DATATYPE;
					  if (sKind.compareTo("tu|") == 0) nKind = KIND_TUPLE;
					  if (sKind.compareTo("li|") == 0) nKind = KIND_LIST;
					  if (sKind.compareTo("en|") == 0) nKind = KIND_DATATYPE;
					  if (sKind.compareTo("op|") == 0) nKind = KIND_OPTION;
					  if (sKind.compareTo("lv|") == 0) nKind = KIND_LVAR;
					  if (sKind.compareTo("re|") == 0) nKind = KIND_REAL;
					  if (sKind.compareTo("in|") == 0) nKind = KIND_INT;
					  if (sKind.compareTo("bo|") == 0) nKind = KIND_BOOLEAN;
					  if (sKind.compareTo("fu|") == 0) nKind = KIND_FUNCTION;
					  if (sKind.compareTo("ve|") == 0) nKind = KIND_VECTOR;
					  if (sKind.compareTo("ar|") == 0) nKind = KIND_ARRAY;
					  if (nKind == KIND_NORMAL) nKind = KIND_UNKNOWN; 
					}
					if (nDepth > maxDepth) maxDepth = nDepth;
					// deal with updating the tree model here
					var = new ModelicaVariableInfo(nKind,vName, vValue, vType,nDepth);	
					node = new DefaultMutableTreeNode(var);			
					if (var.nDepth > currentDepth)
					{
						parent = (DefaultMutableTreeNode)stack.lastElement();
						treeModel.insertNodeInto(node, parent, parent.getChildCount());				
						stack.push(node);
						currentDepth = var.nDepth;
					}
					else if (var.nDepth <= currentDepth)
					{
						while (((ModelicaVariableInfo)((DefaultMutableTreeNode)stack.lastElement()).getUserObject()).nDepth  
						    >= var.nDepth) stack.pop();
						parent = (DefaultMutableTreeNode)stack.lastElement();
						treeModel.insertNodeInto(node, parent, parent.getChildCount());				
						stack.push(node);
						currentDepth = var.nDepth;
					}					
				}
				/* expandAll(tree); */
			}	
		}   
		catch (IOException e) 
		{
			System.err.println(e);
			return false;
		}
		return true;
	}
	
	
	/**
	 * Returns the stack frame owning this variable.
	 * 
	 * @return the stack frame owning this variable
	 */
	protected MDTStackFrame getStackFrame() {
		return fFrame;
	}

    private class ModelicaVariableInfo 
	{
        int nKind;
    	String rmlName = null;
    	String rmlValue = null;
        String rmlType = null; 
		int nDepth = 0;

        public ModelicaVariableInfo(int _nKind, String sName, String sValue, String sType) 
		{
            nKind = _nKind;
        	rmlName = sName;
        	rmlValue = sValue;
        	rmlType = sType; 
			nDepth = -1;
        }

        public ModelicaVariableInfo(int _nKind, String sName, String sValue, String sType, int depth) 
		{
            nKind = _nKind;
        	rmlName = sName; 
        	rmlValue = sValue;        	
        	rmlType = sType;
			nDepth = depth;
        }
        

        public String toString() 
		{
        	if (nKind == KIND_VARIABLE)
        	{
	            return rmlType;
        	}
        	if (nKind == KIND_STRING)
        	{
	            return "\"" + rmlValue + "\"";
        	}        	
			return rmlName;
        }
    }	    
    
}
