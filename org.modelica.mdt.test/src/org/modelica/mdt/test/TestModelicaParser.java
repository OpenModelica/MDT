/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Link�pings universitet, Department of
 * Computer and Information Science, PELAB
 *
 * All rights reserved.
 *
 * (The new BSD license, see also
 * http://www.opensource.org/licenses/bsd-license.php)
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * * Neither the name of Link�pings universitet nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.modelica.mdt.test;

import java.util.Iterator;

import org.modelica.mdt.core.List;
import org.modelica.mdt.core.ListElement;
import org.modelica.mdt.core.ModelicaParserException;
import org.modelica.mdt.core.compiler.ModelicaParser;

import junit.framework.TestCase;

/**
 * test org.modelica.mdt.core.compiler.ModelicaParser class' code 
 */
public class TestModelicaParser extends TestCase 
{
	/*
	 * the output of the getElements() function to test parsing
	 */
	private static final String GET_ELEMENTS_OUTPUT =
		
	"{ { elementvisibility=public, " +
     "elementfile=\"/home/x05andre/ex/Modelica Library/Modelica/package.mo\", "+
	 "elementline=2, elementcolumn=1, final=false, replaceable=false, "+
	 "inout=\"none\", elementtype=extends, path=Icons.Library },\n"+
	"{ elementvisibility=public, elementtype=annotation },\n"+
	"{ elementvisibility=public, " +
	 "elementfile=\"/home/x05andre/ex/Modelica Library/Modelica/package.mo\", " +
	 "elementline=127, elementcolumn=1, final=false, replaceable=false, " +
	 "inout=\"none\", elementtype=classdef, classname=UsersGuide, " +
	 "classrestriction=PACKAGE, " +
	 "classfile=\"/home/x05andre/ex/Modelica Library/Modelica/package.mo\", " +
	 "classline=127, classcolumn=1 },\n"+
	"{ elementvisibility=public, elementfile=\"\", elementline=0, " +
	 "elementcolumn=0, final=false, replaceable=false, inout=\"none\", " +
	 "elementtype=classdef, classname=Media, classrestriction=PACKAGE, " +
	 "classfile=\"/home/x05andre/ex/Modelica" +
	            " Library/Modelica/Media/package.mo\", " +
	 "classline=1, classcolumn=1 },\n"+
	"{ elementvisibility=public, elementfile=\"\", elementline=0, " +
	 "elementcolumn=0, final=false, replaceable=false, inout=\"none\", " +
	 "elementtype=classdef, classname=Utilities, classrestriction=PACKAGE, " +
	 "classfile=\"/home/x05andre/ex/Modelica " +
	            "Library/Modelica/Utilities/package.mo\", " +
	            "classline=1, classcolumn=1 },\n"+
	"{ elementvisibility=public, elementfile=\"\", elementline=0, " +
	 "elementcolumn=0, final=false, replaceable=false, inout=\"none\", " +
	 "elementtype=classdef, classname=Mechanics, classrestriction=PACKAGE, " +
	 "classfile=\"/home/x05andre/ex/Modelica Library/" +
	             "Modelica/Mechanics/package.mo\", " +
	 "classline=1, classcolumn=1 },\n"+
	"{ elementvisibility=public, elementfile=\"\", elementline=0, " +
	 "elementcolumn=0, final=false, replaceable=false, inout=\"none\", " +
	 "elementtype=classdef, classname=Electrical, classrestriction=PACKAGE, " +
	 "classfile=\"/home/x05andre/ex/Modelica Library/" +
	            "Modelica/Electrical/package.mo\"," +
	 " classline=1, classcolumn=1 },\n"+
	"{ elementvisibility=public, elementfile=\"\", elementline=0, " +
	 "elementcolumn=0, final=false, replaceable=false, inout=\"none\", " +
	 "elementtype=classdef, classname=Math, classrestriction=PACKAGE, " +
	 "classfile=\"/home/x05andre/ex/Modelica Library/Modelica/Math/package.mo\","+
	 " classline=1, classcolumn=1 },\n"+
	"{ elementvisibility=public, elementfile=\"\", elementline=0, " +
	 "elementcolumn=0, final=false, replaceable=false, inout=\"none\", " +
	 "elementtype=classdef, classname=Blocks, classrestriction=PACKAGE, " +
	 "classfile=\"/home/x05andre/ex/Modelica " +
	            "Library/Modelica/Blocks/package.mo\", " +
	            "classline=1, classcolumn=1 },\n"+
	"{ elementvisibility=public, elementfile=\"\", elementline=0, " +
	 "elementcolumn=0, final=false, replaceable=false, inout=\"none\", " +
	 "elementtype=classdef, classname=Thermal, classrestriction=PACKAGE, " +
	 "classfile=\"/home/x05andre/ex/Modelica " +
	            "Library/Modelica/Thermal/package.mo\", classline=1, " +
	            "classcolumn=1 },\n"+
	"{ elementvisibility=public, elementfile=\"\", elementline=0, " +
	 "elementcolumn=0, final=false, replaceable=false, inout=\"none\", " +
	 "elementtype=classdef, classname=Icons, classrestriction=PACKAGE, " +
	 "classfile=\"/home/x05andre/ex/Modelica Library/Modelica/Icons.mo\", " +
	 "classline=1, classcolumn=1 },\n"+
	"{ elementvisibility=public, elementfile=\"\", elementline=0, " +
	 "elementcolumn=0, final=false, replaceable=false, inout=\"none\", " +
	 "elementtype=classdef, classname=Constants, classrestriction=PACKAGE, " +
	 "classfile=\"/home/x05andre/ex/Modelica Library/Modelica/Constants.mo\", " +
	 "classline=1, classcolumn=1 },\n"+
	"{ elementvisibility=public, elementfile=\"\", elementline=0, " +
	 "elementcolumn=0, final=false, replaceable=false, inout=\"none\", " +
	 "elementtype=classdef, classname=SIunits, classrestriction=PACKAGE, " +
	 "classfile=\"/home/x05andre/ex/Modelica Library/Modelica/SIunits.mo\", " +
	 "classline=1, classcolumn=1 },\n"+
	"{ elementvisibility=public, elementfile=\"\", elementline=0, " +
	 "elementcolumn=0, final=false, replaceable=false, inout=\"none\", " +
	 "elementtype=classdef, classname=StateGraph, classrestriction=PACKAGE, " +
	 "classfile=\"/home/x05andre/ex/Modelica Library/Modelica/StateGraph.mo\", " +
	 "classline=1, classcolumn=1 }\n"+
	"}\n";
	

	/*
	 * The expected results when getElements() output is parsed 
	 */
	private static final String[][] GET_ELEMENTS_RES =
	{
	  {"elementvisibility=public",
	   "elementfile=\"/home/x05andre/ex/Modelica Library/Modelica/package.mo\"",
	   "elementline=2", "elementcolumn=1", "final=false", "replaceable=false",
	   "inout=\"none\"", "elementtype=extends", "path=Icons.Library"},
	   
	  {"elementvisibility=public", "elementtype=annotation"},
	  
	  {"elementvisibility=public",
	   "elementfile=\"/home/x05andre/ex/Modelica Library/Modelica/package.mo\"",
	   "elementline=127","elementcolumn=1", "final=false", "replaceable=false",
	   "inout=\"none\"", "elementtype=classdef", "classname=UsersGuide",
	   "classrestriction=PACKAGE",
	   "classfile=\"/home/x05andre/ex/Modelica Library/Modelica/package.mo\"",
	   "classline=127", "classcolumn=1"},

	  {"elementvisibility=public", "elementfile=\"\"", "elementline=0",
	   "elementcolumn=0", "final=false", "replaceable=false", "inout=\"none\"",
	   "elementtype=classdef", "classname=Media", "classrestriction=PACKAGE",
	   "classfile=\"/home/x05andre/ex/Modelica Library/Modelica/Media/package.mo\"",
	   "classline=1", "classcolumn=1"},
	   
	  {"elementvisibility=public", "elementfile=\"\"", "elementline=0",
	   "elementcolumn=0", "final=false", "replaceable=false", "inout=\"none\"",
	   "elementtype=classdef", "classname=Utilities", 
	   "classrestriction=PACKAGE",
	   "classfile=\"/home/x05andre/ex/Modelica " +
		            "Library/Modelica/Utilities/package.mo\"",
	   "classline=1", "classcolumn=1"},
	   
	  {"elementvisibility=public", "elementfile=\"\"", "elementline=0",
	   "elementcolumn=0", "final=false", "replaceable=false", "inout=\"none\"",
	   "elementtype=classdef", "classname=Mechanics", "classrestriction=PACKAGE",
	   "classfile=\"/home/x05andre/ex/Modelica " +
	             "Library/Modelica/Mechanics/package.mo\"",
	   "classline=1", "classcolumn=1"},
	   
	  {"elementvisibility=public", "elementfile=\"\"", "elementline=0",
	   "elementcolumn=0", "final=false", "replaceable=false", "inout=\"none\"",
	   "elementtype=classdef", "classname=Electrical", 
	   "classrestriction=PACKAGE",
	   "classfile=\"/home/x05andre/ex/Modelica Library/" +
		            "Modelica/Electrical/package.mo\"",
	   "classline=1", "classcolumn=1" },
	   
	  {"elementvisibility=public", "elementfile=\"\"", "elementline=0",
	   "elementcolumn=0", "final=false", "replaceable=false", "inout=\"none\"",
	   "elementtype=classdef", "classname=Math", "classrestriction=PACKAGE",
	   "classfile=\"/home/x05andre/ex/Modelica "+
	               "Library/Modelica/Math/package.mo\"",
	   "classline=1", "classcolumn=1" },
	   
	  {"elementvisibility=public", "elementfile=\"\"", "elementline=0",
       "elementcolumn=0", "final=false", "replaceable=false", "inout=\"none\"",
       "elementtype=classdef", "classname=Blocks", "classrestriction=PACKAGE",
       "classfile=\"/home/x05andre/ex/Modelica " +
		            "Library/Modelica/Blocks/package.mo\"",
	   "classline=1", "classcolumn=1" },

	  {"elementvisibility=public", "elementfile=\"\"", "elementline=0",
	   "elementcolumn=0", "final=false", "replaceable=false", "inout=\"none\"",
	   "elementtype=classdef", "classname=Thermal", "classrestriction=PACKAGE",
	   "classfile=\"/home/x05andre/ex/Modelica " +
		            "Library/Modelica/Thermal/package.mo\"", "classline=1",
	   "classcolumn=1"},
	   
	  {"elementvisibility=public", "elementfile=\"\"", "elementline=0",
	   "elementcolumn=0", "final=false", "replaceable=false", "inout=\"none\"",
	   "elementtype=classdef", "classname=Icons", "classrestriction=PACKAGE",
	   "classfile=\"/home/x05andre/ex/Modelica Library/Modelica/Icons.mo\"",
	   "classline=1", "classcolumn=1" },

	  {"elementvisibility=public", "elementfile=\"\"", "elementline=0",
	   "elementcolumn=0", "final=false", "replaceable=false", "inout=\"none\"",
	   "elementtype=classdef","classname=Constants", "classrestriction=PACKAGE",
	   "classfile=\"/home/x05andre/ex/Modelica Library/Modelica/Constants.mo\"",
	   "classline=1", "classcolumn=1" },

	  {"elementvisibility=public", "elementfile=\"\"", "elementline=0",
	   "elementcolumn=0", "final=false", "replaceable=false", "inout=\"none\"",
	   "elementtype=classdef", "classname=SIunits", "classrestriction=PACKAGE",
	   "classfile=\"/home/x05andre/ex/Modelica Library/Modelica/SIunits.mo\"",
	   "classline=1", "classcolumn=1" },

	  {"elementvisibility=public", "elementfile=\"\"", "elementline=0",
	   "elementcolumn=0", "final=false", "replaceable=false", "inout=\"none\"",
	   "elementtype=classdef", "classname=StateGraph",
	   "classrestriction=PACKAGE",
	   "classfile=\"/home/x05andre/ex/Modelica " +
	               "Library/Modelica/StateGraph.mo\"",
	   "classline=1", "classcolumn=1" }
	};

	/**
	 * test parsing some simple unnested lists with ModelicaParser.parseList()
	 * @throws ModelicaParserException 
	 */
	public void testParseSimpleList() throws ModelicaParserException
	{
		/*
		 * some combinations of empty lists
		 */
		List res;
		
		res = ModelicaParser.parseList("{}");
		assertEquals(0, res.size());
	
		res = ModelicaParser.parseList("{ }");
		assertEquals(0, res.size());
		
		res = ModelicaParser.parseList("{    }");
		assertEquals(0, res.size());

		
		res = ModelicaParser.parseList("{ \n}");
		assertEquals(0, res.size());
		
		res = ModelicaParser.parseList("{   \n    }");
		assertEquals(0, res.size());

		/*
		 * single element lists
		 */
		res = ModelicaParser.parseList("{hej}");
		assertEquals(1, res.size());
		assertEquals("hej", res.elementAt(0).toString());
		
		res = ModelicaParser.parseList("{  muu}");
		assertEquals(1,res.size());
		assertEquals("muu", res.elementAt(0).toString());
		
		res = ModelicaParser.parseList("{  muu   }");
		assertEquals(1, res.size());
		assertEquals("muu", res.elementAt(0).toString());

		res = ModelicaParser.parseList("{muu   }");
		assertEquals(1, res.size());
		assertEquals("muu", res.elementAt(0).toString());


		/*
		 * two element lists
		 */
		res = ModelicaParser.parseList("{hej,peter}");
		assertEquals(2, res.size());
		assertEquals("hej", res.elementAt(0).toString());
		assertEquals("peter", res.elementAt(1).toString());
		
		res = ModelicaParser.parseList("{peter,  labb}");
		assertEquals("peter", res.elementAt(0).toString());
		assertEquals("labb", res.elementAt(1).toString());
		
		res = ModelicaParser.parseList("{peter ,labb}");
		assertEquals("peter", res.elementAt(0).toString());
		assertEquals("labb", res.elementAt(1).toString());
		
		res = ModelicaParser.parseList("{peter   ,labb}");
		assertEquals("peter", res.elementAt(0).toString());
		assertEquals("labb", res.elementAt(1).toString());
		
		/*
		 * tree elements list
		 */
		res = ModelicaParser.parseList("{alan, l, cox}");
		assertEquals(3, res.size());
		assertEquals("alan", res.elementAt(0).toString());
		assertEquals("l", res.elementAt(1).toString());
		assertEquals("cox", res.elementAt(2).toString());
		
		res = ModelicaParser.parseList("{alan,l,cox}");
		assertEquals(3, res.size());
		assertEquals("alan", res.elementAt(0).toString());
		assertEquals("l", res.elementAt(1).toString());
		assertEquals("cox",res.elementAt(2).toString());

	}
	
	/**
	 * test parsing some nested lists with ModelicaParser.parseList()
	 * @throws ModelicaParserException 
	 */
	public void testParseList() throws ModelicaParserException
	{
		List v = ModelicaParser.parseList("{{a    ,b   }  ,   c   }");
		assertTrue(v.elementAt(0) instanceof List);
		assertTrue(v.size() == 2);

		assertEquals("a", ((List)v.elementAt(0)).elementAt(0).toString());
		assertEquals("b", ((List)v.elementAt(0)).elementAt(1).toString());
		assertEquals("c", v.elementAt(1).toString());
		
		v = ModelicaParser.parseList("{a, b, c}");
		assertEquals(3, v.size());
		assertEquals("a", v.elementAt(0).toString());
		assertEquals("b", v.elementAt(1).toString());
		assertEquals("c", v.elementAt(2).toString());
		
		v = ModelicaParser.parseList("{a, b, c={a, b, c}}");
		assertEquals(3, v.size());
		assertEquals("a", v.elementAt(0).toString());
		assertEquals("b", v.elementAt(1).toString());
		assertEquals("c={a, b, c}", v.elementAt(2).toString());
		
		boolean failedParse = false;
		try
		{
			v = ModelicaParser.parseList("{,,}");
		}
		catch(ModelicaParserException e)
		{
			failedParse = true;
		}
		assertTrue("Parsing didn't fail on {,,}", failedParse);
		
		v = ModelicaParser.parseList("{foo={bar, gzonk}}");
		assertEquals(1, v.size());
		assertEquals("foo={bar, gzonk}", v.elementAt(0).toString());
	}
	
	public void testParseOmcOutput() throws ModelicaParserException
	{
		List parsedList = ModelicaParser.parseList(GET_ELEMENTS_OUTPUT);
		
		assertEquals(14, parsedList.size());
		
		/*
		 * each element in the res should be a list
		 */
		Iterator<ListElement> it = parsedList.iterator();
		for (String[] expectedList : GET_ELEMENTS_RES)
		{
			ListElement parsedElements = it.next();
			assertTrue("expected to find a list but found an element",
					 (parsedElements instanceof List));
			
			Iterator<ListElement> elementsIterator = ((List)parsedElements).iterator(); 
			for (String expectedElement : expectedList)
			{
				assertEquals("parsed list contains unexpected element",
						expectedElement, elementsIterator.next().toString()); 
			}
			assertFalse("more elements then expected in the parse results",
					elementsIterator.hasNext());
		}
	}
}
