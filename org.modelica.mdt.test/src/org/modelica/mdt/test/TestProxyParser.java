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

import org.modelica.mdt.internal.omcproxy.CompileError;
import org.modelica.mdt.internal.omcproxy.ProxyParser;

import junit.framework.TestCase;

/**
 * Test various methods in org.modelica.mdt.internal.omcproxy.ProxyParser
 * @author Elmir Jagudin
 */
public class TestProxyParser extends TestCase 
{

	/**
	 * Test if ProxyParser.parseList() works as prescribed.
	 */
	public void testParseList()
	{
		/*
		 * some combinations of empty lists
		 */
		String[] res = ProxyParser.parseList("{}");
		assertEquals(0, res.length);
		
		res = ProxyParser.parseList("{ }");
		assertEquals(0, res.length);
		
		res = ProxyParser.parseList("{    }");
		assertEquals(0, res.length);

		
		res = ProxyParser.parseList("{ \n}");
		assertEquals(0, res.length);
		
		res = ProxyParser.parseList("{   \n    }");
		assertEquals(0, res.length);

		/*
		 * single element lists
		 */
		res = ProxyParser.parseList("{hej}");
		assertEquals(1, res.length);
		assertEquals("hej", res[0]);
		
		res = ProxyParser.parseList("{  muu}");
		assertEquals(1, res.length);
		assertEquals("muu", res[0]);
		
		res = ProxyParser.parseList("{  muu   }");
		assertEquals(1, res.length);
		assertEquals("muu", res[0]);

		res = ProxyParser.parseList("{muu   }");
		assertEquals(1, res.length);
		assertEquals("muu", res[0]);


		/*
		 * two element lists
		 */
		res = ProxyParser.parseList("{hej,peter}");
		assertEquals(2, res.length);
		assertEquals("hej", res[0]);
		assertEquals("peter", res[1]);
		
		res = ProxyParser.parseList("{peter,  labb}");
		assertEquals("peter", res[0]);
		assertEquals("labb", res[1]);
		
		res = ProxyParser.parseList("{peter ,labb}");
		assertEquals("peter", res[0]);
		assertEquals("labb", res[1]);
		
		res = ProxyParser.parseList("{peter   ,labb}");
		assertEquals("peter", res[0]);
		assertEquals("labb", res[1]);
		
		/*
		 * tree elements list
		 */
		res = ProxyParser.parseList("{alan, l, cox}");
		assertEquals(3, res.length);
		assertEquals("alan", res[0]);
		assertEquals("l", res[1]);
		assertEquals("cox", res[0]);
		
		res = ProxyParser.parseList("{alan,l,cox}");
		assertEquals(3, res.length);
		assertEquals("alan", res[0]);
		assertEquals("l", res[1]);
		assertEquals("cox", res[0]);

	}
				


	/**
	 * Test if ProxyParser.parseErrorString() works as prescribed.
	 */	
	public void testParseErrorString()
	{
		/* single error windows style */
		String errorString = 
			"[c:/folde/hej.mo:2:1]: error: unexpected tooken: muu\n";
		CompileError[] errors = ProxyParser.parseErrorString(errorString);

		assertEquals(1, errors.length);		
		CompileError error = errors[0];
		assertEquals(2, error.getLine());
		assertEquals("unexpected tooken: muu", error.getErrorDescription());

		/* two errors windows style */
		errorString = 
			"[c:/folder he/hej.mo:4:1]: error: unexpected end of file\n" + 
			"[c:/folder he/hej.mo:15:1]: error: hej remar\n";
		errors = ProxyParser.parseErrorString(errorString);
		assertEquals(2, errors.length);
		
		error = errors[0];
		assertEquals(4, error.getLine());
		assertEquals("unexpected end of file", error.getErrorDescription());

		error = errors[1];
		assertEquals(15, error.getLine());
		assertEquals("hej remar", error.getErrorDescription());
		
		/* single error unix style */
		errorString = 
			"[/usr/folde/hej.mo:12:1]: error: unexpected tooken: muu\n";
		errors = ProxyParser.parseErrorString(errorString);

		assertEquals(1, errors.length);		
		error = errors[0];
		assertEquals(12, error.getLine());
		assertEquals("unexpected tooken: muu", error.getErrorDescription());

		/* two errors unix style */
		errorString = 
			"[/hej/hop/hej.mo:453:1]: error: unexpected end of file\n" + 
			"[/usr/local/modelica/hej.mo:715:1]: error: hej remar\n";
		errors = ProxyParser.parseErrorString(errorString);
		assertEquals(2, errors.length);
		
		error = errors[0];
		assertEquals(453, error.getLine());
		assertEquals("unexpected end of file", error.getErrorDescription());

		error = errors[1];
		assertEquals(715, error.getLine());
		assertEquals("hej remar", error.getErrorDescription());

		/* no errors aka empty error string */
		errors = ProxyParser.parseErrorString("");
		assertEquals(0, errors.length);
	}
	
}
