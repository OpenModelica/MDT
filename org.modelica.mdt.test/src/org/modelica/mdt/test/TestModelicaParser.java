/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Linköpings universitet, Department of
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
 * * Neither the name of Linköpings universitet nor the names of its
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

import java.util.Vector;

import org.modelica.mdt.compiler.ModelicaParser;

import junit.framework.TestCase;

/**
 * test org.modelica.mdt.compiler.ModelicaParser class' code 
 */
public class TestModelicaParser extends TestCase 
{
	/**
	 * test parsing some simple unnested lists with ModelicaParser.parseList()
	 */
	public void testParseSimpleList()
	{
		/*
		 * some combinations of empty lists
		 */
		Vector<Object> res;
		
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
		assertEquals("hej", (String)res.elementAt(0));
		
		res = ModelicaParser.parseList("{  muu}");
		assertEquals(1,res.size());
		assertEquals("muu", (String)res.elementAt(0));
		
		res = ModelicaParser.parseList("{  muu   }");
		assertEquals(1, res.size());
		assertEquals("muu", (String)res.elementAt(0));

		res = ModelicaParser.parseList("{muu   }");
		assertEquals(1, res.size());
		assertEquals("muu", (String)res.elementAt(0));


		/*
		 * two element lists
		 */
		res = ModelicaParser.parseList("{hej,peter}");
		assertEquals(2, res.size());
		assertEquals("hej", (String)res.elementAt(0));
		assertEquals("peter", (String)res.elementAt(1));
		
		res = ModelicaParser.parseList("{peter,  labb}");
		assertEquals("peter", (String)res.elementAt(0));
		assertEquals("labb", (String)res.elementAt(1));
		
		res = ModelicaParser.parseList("{peter ,labb}");
		assertEquals("peter", (String)res.elementAt(0));
		assertEquals("labb", (String)res.elementAt(1));
		
		res = ModelicaParser.parseList("{peter   ,labb}");
		assertEquals("peter", (String)res.elementAt(0));
		assertEquals("labb", (String)res.elementAt(1));
		
		/*
		 * tree elements list
		 */
		res = ModelicaParser.parseList("{alan, l, cox}");
		assertEquals(3, res.size());
		assertEquals("alan", (String)res.elementAt(0));
		assertEquals("l", (String)res.elementAt(1));
		assertEquals("cox", (String)res.elementAt(2));
		
		res = ModelicaParser.parseList("{alan,l,cox}");
		assertEquals(3, res.size());
		assertEquals("alan", (String)res.elementAt(0));
		assertEquals("l", (String)res.elementAt(1));
		assertEquals("cox", (String)res.elementAt(2));

	}
	
	/**
	 * test parsing some nested lists with ModelicaParser.parseList()
	 */
	public void testParseList()
	{
		Vector v = ModelicaParser.parseList("{{a    ,b   }  ,   c   }");
		assertTrue(v.get(0) instanceof Vector);
		assertTrue(v.size() == 2);
		assertTrue(((Vector)v.get(0)).get(0).equals("a"));
		assertTrue(((Vector)v.get(0)).get(1).equals("b"));
		assertTrue(v.get(1).equals("c"));
		
		v = ModelicaParser.parseList("{a, b, c}");
		assertTrue(v.size() == 3);
		assertTrue(v.get(0).equals("a"));
		assertTrue(v.get(1).equals("b"));
		assertTrue(v.get(2).equals("c"));
		
		v = ModelicaParser.parseList("{a, b, c={a, b, c}}");
		assertTrue(v.size() == 3);
		assertTrue(v.get(0).equals("a"));
		assertTrue(v.get(1).equals("b"));
		assertTrue(v.get(2).equals("c={a, b, c}"));
		
		v = ModelicaParser.parseList("{,,}");
		assertTrue(v.size() == 0);
		
		v = ModelicaParser.parseList("{foo={bar, gzonk}}");
		assertTrue(v.size() == 1);
		assertTrue(v.get(0).equals("foo={bar, gzonk}"));
	}
}
