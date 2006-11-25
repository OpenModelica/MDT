/*
    Copyright PELAB, Linkoping University

    This file is part of Relational Meta-Language (RML) and the Modelica+ compiler
	http://www.ida.liu.se/~pelab/rml
	http://www.ida.liu.se/~pelab/modelica

    RML is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    RML is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 * Adrian Pop, adrpo@ida.liu.se, http://www.ida.liu.se/~adrpo,
 * MODDataTree.java 
 *  - created 2004-11-22
 *  - copyright adrpo, IDA/PELAB
 *  - last modified: 2004-12-23
 */

package org.modelica.mdt.debug.server;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import javax.swing.tree.*;
import java.io.*;
import java.net.*;

public class MDTDebugServer extends Thread
{
    private static boolean DEBUG = true;
 
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
    
	boolean bServerStop = false;
    
	public static SimpleThread listenThread = null;

	public static final String rmlServerHostname = "localhost";
	public static final int rmlServerPort = 9999;
	ServerSocket rmlServer = null;
	String line;
	BufferedReader is;
	PrintStream os;
	Socket clientSocket = null;

	DefaultMutableTreeNode top = null;
	DefaultMutableTreeNode modelicaVariables = null; 
	DefaultMutableTreeNode child = null;
	DefaultMutableTreeNode parent = null; 

	DefaultTreeModel treeModel = null;

	LinkedList variableFromMOD = null;
	
	static String modelicaFilesPrefix = null;

	FileInputStream fis; 

    public MDTDebugServer() 
	{
		listenThread = new SimpleThread("listener");
		listenThread.start();
    }

    private class ModelicaVariableInfo 
	{
        int nKind;
    	String rmlName = null;
        String rmlType = null;
        String modelicaFile = null; 
        String rmlRelation = null;
		int sl=0,sc=0,el=0,ec=0;
		int r_sl=0,r_sc=0,r_el=0,r_ec=0;
		int c_sl=0,c_sc=0,c_el=0,c_ec=0;		
		int nDepth = 0;

        public ModelicaVariableInfo(
        		int _nKind,
        		String sName, 
				String sType,		
        		String sFile,
				String sRelation,
				int _sl, 
				int _sc, 
				int _el,
				int _ec,
				int _r_sl, 
				int _r_sc, 
				int _r_el,
				int _r_ec,
				int _c_sl, 
				int _c_sc, 
				int _c_el,
				int _c_ec) 
		{
            nKind = _nKind;
        	rmlName = sName; rmlType = sType; rmlRelation = sRelation;
			sl = _sl; sc = _sc;  el = _el; ec = _ec;
			r_sl = _r_sl; r_sc = _r_sc; r_el = _r_el; r_ec = _r_ec;
			c_sl = _c_sl; c_sc = _c_sc; c_el = _c_el; c_ec = _c_ec;			
			modelicaFile = sFile;
			nDepth = -1;
        }
        
        public ModelicaVariableInfo(
        		int _nKind,
        		String sName, 
				String sType,		
        		String sFile,
				int _sl, 
				int _sc, 
				int _el,
				int _ec,
				int _nDepth) 
		{
            nKind = _nKind;
        	rmlName = sName; rmlType = sType;
			sl = _sl; sc = _sc;  el = _el; ec = _ec;
			nDepth = _nDepth;
			try
			{
				modelicaFile = sFile;
			}
			catch (Exception e)
			{
				System.err.println("URL maformed:" + sFile);
			}
            if (modelicaFile == null) 
			{
                System.err.println("Couldn't find file: " + sFile);
            }
        }
       

        public String toString() 
		{
        	if (nKind == KIND_VARIABLE)
        	{
	            return (rmlName+" / type: "+ rmlType +" / file: "+
						modelicaFile+" / position: "+
						sl+"."+sc+"."+el+"."+ec+" / live range: " +
						r_sl+"."+r_sc+"."+r_el+"."+r_ec);
        	}
			if (modelicaFile.compareTo("RML") == 0) 
			{
				return (rmlName+" / type: "+ rmlType +" / file: Modelica+ Builtin" +" / depth: " + nDepth);
			}
			return (rmlName+" / type: "+ rmlType +" / file: "+
				modelicaFile+" / position: "+
				sl+"."+sc+"."+el+"."+ec+" / depth: " + nDepth);
        }
    }

	public boolean listen()
	{
		// Try to open a server socket on port rmlServerPort on hostname rmlServerHostname
		// Note that we can't choose a port less than 1023 if we are not
		// privileged users (root) 
		try 
		{
			rmlServer = new ServerSocket();
			rmlServer.bind(new InetSocketAddress(rmlServerHostname, rmlServerPort));
			rmlServer.setSoTimeout(0);			
			if (DEBUG) System.err.println("Listening on :" + rmlServer.getInetAddress().getHostName() + ":" + rmlServerPort); 
		}
		catch (IOException e) 
		{
			System.err.println(e);
			bServerStop = true;
			return false;
		}   

		// Create a socket object from the ServerSocket to listen and accept 
		// connections.
		// Open input and output streams

		try 
		{
			clientSocket = rmlServer.accept();
			if (DEBUG) System.err.println("Client connected - now receiving data");
			Stack stack = new Stack();
			DefaultMutableTreeNode node = null;
			int currentDepth = -1;
			ModelicaVariableInfo var = null; 
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()), 100000);
			os = new PrintStream(clientSocket.getOutputStream());
			/*
			 * first reading the variable (name,type,file,range,clause_range)
			 * then contenst:
			 * depth|vName|vType|vFile|sl.sc.el.ec
			 * false|bool|RML|0.0.0.0
			 * true|bool|RML|0.0.0.0"
			 * number:enumer:DCONSTRUCTOR|TYPE|FILE|%d.%d.%d.%d 
			 * int|int|RML|0.0.0.0
			 * pointer:function (same module)|TYPE|RML|0.0.0.0
			 * %f|real|RML|0.0.0.0
			 * -|string|RML|0.0.0.0 -> <.$STRING_START$>STRING<.$STRING_END$.>
			 * pointer:function (external module)|TYPE|RML|0.0.0.0
			 * TUPLE[slots]|TYPE|RML|0.0.0.0 
			 * VECTOR[SLOTS]|TYPE|RML|0.0.0.0
			 * ARRAY[SLOTS]|TYPE|RML|0.0.0.0 
			 * LIST_START|TYPE||RML|0.0.0.0 -> LIST_END|TYPE|RML|0.0.0.0");
			 * NONE[0]|TYPE|RML|0.0.0.0 
			 * SOME[1]|TYPE|RML|0.0.0.0
			 * DATATYPE[slots]|TYPE|FILE|%d.%d.%d.%d" 
			 * ??UNNAMED??[slots]|TYPE|RML|0.0.0.0
			 * LVAR(UNKNOWN)|RML|0.0.0.0
			 */
			int which = 0;
			bServerStop = false;
			String vName = null, vFile = null, vType = null, vRelation = null;
			int sl = 0, sc = 0, el = 0, ec = 0, r_sl = 0, r_sc = 0, r_el = 0, r_ec = 0;
			int c_sl = 0, c_sc = 0, c_el = 0, c_ec = 0;
			int nKind = -1;
			int nDepth = 0;
			int maxDepth = 0;

			// As long as we receive data, send Ok to the client.
			while (!bServerStop) 
			{
				line = is.readLine(); 
				if (DEBUG) System.err.println("Received:" + line);
				if (line == null) break;
				os.println("K");
				if (line.compareTo("<.$STOP$.>") == 0) 
				{ 
					os.println("STOPPING SERVER!");
					break;
				}				
				if (line.compareTo("<.$STRING_START$.>") == 0) 
				{ 
					// string reading
					if (DEBUG) System.err.println("Reading strings");
					String text = "";
					while (true)
					{
						line = is.readLine(); if (DEBUG) System.err.println("Received:" + line);
						if (line == null) continue;
						else if (line.compareTo("<.$STRING_END$.>") != 0)
							 	text += line + "\n"; /* append to the little boy */
						else 
							{
							    /* remove the last \n shit! */
								text = text.substring(0,text.length()-1);
								break;
							}
						/* os.println("string-reading"); */						
					}
					
					((ModelicaVariableInfo)((DefaultMutableTreeNode)stack.lastElement()).getUserObject()).modelicaFile = text;
					((ModelicaVariableInfo)((DefaultMutableTreeNode)stack.lastElement()).getUserObject()).nKind = KIND_STRING;
					if (DEBUG) System.err.println("Read string:" + text);
					os.println("string-reading-ended");
				}
				else
				if (which < 7) 
				{
					switch(which)
					{
						case 0: { vName = line; break; }     // var name
						case 1: { vType = line; break; }     // var type						
						case 2: { vFile = line; break; }     // file 
						case 3: { vRelation = line; break; } // relation
						case 4: 
						{ 
							String tmp = null;
							tmp = line.substring(0, line.indexOf("."));
							sl = Integer.valueOf(tmp).intValue(); 
							line = line.substring(line.indexOf(".")+1,line.length());
							tmp = line.substring(0, line.indexOf("."));
							sc = Integer.valueOf(tmp).intValue(); 
							line = line.substring(line.indexOf(".")+1,line.length());
							tmp = line.substring(0, line.indexOf("."));
							el = Integer.valueOf(tmp).intValue(); 
							line = line.substring(line.indexOf(".")+1,line.length());
							tmp = line.substring(0, line.length());
							ec = Integer.valueOf(tmp).intValue(); 
							break; 
						} // range						
						case 5: 
						{ 
							String tmp = null;
							tmp = line.substring(0, line.indexOf("."));
							r_sl = Integer.valueOf(tmp).intValue(); 
							line = line.substring(line.indexOf(".")+1,line.length());
							tmp = line.substring(0, line.indexOf("."));
							r_sc = Integer.valueOf(tmp).intValue(); 
							line = line.substring(line.indexOf(".")+1,line.length());
							tmp = line.substring(0, line.indexOf("."));
							r_el = Integer.valueOf(tmp).intValue(); 
							line = line.substring(line.indexOf(".")+1,line.length());
							tmp = line.substring(0, line.length());
							r_ec = Integer.valueOf(tmp).intValue(); 
							break;  
						} // clause_range
						case 6: 
						{ 
							String tmp = null;
							tmp = line.substring(0, line.indexOf("."));
							c_sl = Integer.valueOf(tmp).intValue(); 
							line = line.substring(line.indexOf(".")+1,line.length());
							tmp = line.substring(0, line.indexOf("."));
							c_sc = Integer.valueOf(tmp).intValue(); 
							line = line.substring(line.indexOf(".")+1,line.length());
							tmp = line.substring(0, line.indexOf("."));
							c_el = Integer.valueOf(tmp).intValue(); 
							line = line.substring(line.indexOf(".")+1,line.length());
							tmp = line.substring(0, line.length());
							c_ec = Integer.valueOf(tmp).intValue(); 
							break;  
						} // clause_range
					}
					which = which + 1;
					if (which == 7)
					{
						// everything's read, build the var, put it in the list
						// "file:///c:/dev/modelica/modeq/"
						/* variableFromRML.addLast( */
						node = new DefaultMutableTreeNode(new ModelicaVariableInfo(
						  		KIND_VARIABLE,
						  		vName, 
								vType, 
								vFile, 
								vRelation,
								sl,	sc,	el,	ec,
								r_sl, r_sc, r_el, r_ec,
								c_sl, c_sc, c_el, c_ec));
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
					vType = line.substring(0, line.indexOf("|"));
					line = line.substring(line.indexOf("|")+1,line.length());
					vFile = line.substring(0, line.indexOf("|"));
					line = line.substring(line.indexOf("|")+1,line.length());
					//if (DEBUG) System.err.println(sKind+vName+"|"+vType+"|"+vFile+"|"+line);
					
					tmp = line.substring(0, line.indexOf("."));
					sl = Integer.valueOf(tmp).intValue(); 
					line = line.substring(line.indexOf(".")+1,line.length());
					tmp = line.substring(0, line.indexOf("."));
					sc = Integer.valueOf(tmp).intValue(); 
					line = line.substring(line.indexOf(".")+1,line.length());
					tmp = line.substring(0, line.indexOf("."));
					el = Integer.valueOf(tmp).intValue(); 
					line = line.substring(line.indexOf(".")+1,line.length());
					tmp = line.substring(0, line.length());
					ec = Integer.valueOf(tmp).intValue();
					if (sKind == null) nKind = KIND_NORMAL;
					else
					{
						/*java expects this!
						depth|ty|vName|vType|vFile|sl.sc.el.ec\n
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
					var = new ModelicaVariableInfo(
							  		nKind,
							  		vName, 
									vType, 
									vFile, 
									sl,	sc,	el,	ec,
									nDepth);	
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
			rmlServer.close();
			bServerStop = false;
			/* buildTree(variableFromRML, maxDepth); */		
		}   
		catch (IOException e) 
		{
			System.err.println(e);
			bServerStop = true;
			return false;
		}
		return true;
	}

	private class SimpleThread extends Thread 
	{
		public SimpleThread(String str) 
		{
			super(str);
			if (DEBUG) System.err.println("Creating thread"+str);
		}
		public void run() 
		{
			while (!bServerStop) 
			{
				if (DEBUG) System.err.println("Calling listen!");
				listen();
			}
			System.out.println("\n!!!!ListenerStopped!!!! - client communication failed - restart!");
		}
	}
	
	public void buildTree(LinkedList list, int depth)
	{
		ListIterator idx = list.listIterator(0);
		DefaultMutableTreeNode parentVariable = new DefaultMutableTreeNode(idx.next());
		treeModel.insertNodeInto(parentVariable, top, top.getChildCount());
		list.remove(0); /* we removed the first which is a variable */
		int currentDepth = -1;
		// deal with updating the tree model here
		idx = list.listIterator(0);
		Stack stack = new Stack();
		stack.push(parentVariable);
		while (idx.hasNext())
		{
			ModelicaVariableInfo var = (ModelicaVariableInfo)idx.next();
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(var);			
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
	}
}
