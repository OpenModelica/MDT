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

import org.modelica.mdt.core.compiler.ICompileError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.omc.internal.CompileError;
import org.modelica.mdt.omc.internal.OMCParser;

import junit.framework.TestCase;

/**
 * test org.modelica.mdt.omc.internal.OMCParser class' code
 */
public class TestOMCParser extends TestCase
{
	
	/**
	 * Test if OMCParser.parseErrorString() is able to parse 
	 * legal formatted error strings
	 */	
	public void testParseLegalErrorString()
	{
		try
		{
			/* single error windows style */
			String errorString = 
				"[c:/folde/hej.mo:2:1-3:2:writable]: error: unexpected " +
				"tooken: muu\n";
			ICompileError[] errors = OMCParser.parseErrorString(errorString);
	
			assertEquals(1, errors.length);		
			ICompileError error = errors[0];
			assertEquals(2, error.getStartLine());
			assertEquals("unexpected tooken: muu", error.getErrorDescription());
	
			/* two errors windows style */
			errorString = 
				"[c:/folder he/hej.mo:4:1-4:5:writable]: error: unexpected " +
				"end of file\n" + 
				"[c:/folder he/hej.mo:15:1-16:2:writable]: error: hej remar\n";
			errors = OMCParser.parseErrorString(errorString);
			assertEquals(2, errors.length);
			
			error = errors[0];
			assertEquals(4, error.getStartLine());
			assertEquals("unexpected end of file", error.getErrorDescription());
	
			error = errors[1];
			assertEquals(15, error.getStartLine());
			assertEquals("hej remar", error.getErrorDescription());
			
			/* single error unix style */
			errorString = 
				"[/usr/folde/hej.mo:12:1-13:4:writable]: error: unexpected" +
				" tooken: muu\n";
			errors = OMCParser.parseErrorString(errorString);
	
			assertEquals(1, errors.length);		
			error = errors[0];
			assertEquals(12, error.getStartLine());
			assertEquals("unexpected tooken: muu", error.getErrorDescription());
	
			/* two errors unix style */
			errorString = 
				"[/hej/hop/hej.mo:453:1-570:3:writable]: error: unexpected " +
				"end of file\n" + 
				"[/usr/local/modelica/hej.mo:715:1-864:45:writable]: error: " +
				"hej remar\n";
			errors = OMCParser.parseErrorString(errorString);
			assertEquals(2, errors.length);
			
			error = errors[0];
			assertEquals(453, error.getStartLine());
			assertEquals("unexpected end of file", error.getErrorDescription());
	
			error = errors[1];
			assertEquals(715, error.getStartLine());
			assertEquals("hej remar", error.getErrorDescription());
		}
		catch(Exception e)
		{
			/* On any exception, fail. */
			fail(e.getMessage());
		}
	}


	/**
	 * Test if OMCParser.parseErrorString() is able to handle
	 * illegal formatted error strings
	 */		
	public void testParseIllegalErrorString()
	{

		/* random string generated with getRandomString(-2147477960, 1124) */
		assertUnexpectedReplyThrown(
			"\u009f\u00a8\u001c\u0019'\u00fe\u0082pL\u0080\u008a\u00c2\u000eT::" +
			"\u00e2!j\u001fPCx\u0009\u00b31aO{D:\u001dyv*j\u0097\u00cb\u00d3\u00f3" +
			"\u0019c\u000f\u009a\u00ff\u00be\u0014\u0098\u00eb6\u00cf\u0092\u00d0" +
			"\u0082\u00f0\u00d6\u008e\u000c:/mD\u0010\u00c8\u0097B\u00ca\u008e" +
			"\u00a9\u00e3\u00c2\u00e0\u0090\u000e\u00f3\u0015\u0097s\u00db\u0005," +
			"\u0012E\u00c5\u000e\u00e3\u00b7\u00ec\u009cCO\\]}\u00ad!^\u00b5\u00dd" +
			"<\u00ce\u00ad\u000c\u00c3\u0007\u0097S\u00ef\u00e6T+9\u00d6wI\u000e~");

//		 accepted		

		assertUnexpectedReplyThrown(""); 
 
		assertUnexpectedReplyThrown(
			"!F\u00a13Q\u0081a\u00d8L~\u00ad\u00db\u00bcj\u0014!Z;\u00dci\u00b9%" +
			"\u0001\u00d0\"\u0001E7D04\u00ce\u00d1\u00b6r\u0083W\u00c8\u0019sJ" +
			"\u00d2J\u0005\u0081MaK\u00b2\u0012\u00ccg\u008d9\u00bb\u0085}9.\u00ce" +
			"w\u008f\u00d8\u001c\u00a8\u0083\u001b\u00f0J%:\u0092\u001a\u00e1[ " +
			"\u0097TD\u00997\u001e\u00f4\u00ca\u009f6C|(\u00f4\u00a9\u0005u\u00ecl" +
			"/iNM\u00f0\u0092`nm\u009a\u0098Y\u00f3\u00bd\u00beb\u0099\u00ac\u00a5" +
			"d\u00ca2`\u008e\u00a2\u00d5I?\u00e6gm\u00af\u0004\u00fa\"T\u0017" +
			"\u00eb\u00cb\u00ad6p\u00d7\u00b7%)f:\u0006:Y\u0002\u00cb\u001bD\u00fd" +
			"4:\u000c(\u0006-\u0097`\u008a\u00e6\u0017\u0084C\u00e4\u0098\u001bS" +
			"\u0080A!\u00c3\u0004;\u0086M\u00edy]9C\u00f9Y\"\u008fb%z");

		assertUnexpectedReplyThrown(
			"\r");

		assertUnexpectedReplyThrown(
			"\n");

		assertUnexpectedReplyThrown(
			"\u0096\u00ffEZU\u00fe\u0097\u00b9\u00f0\u00ca\u00c7\u00c0\u001d\u0096" +
			"\u00d9(v\u00ad:\u00ecy\u00e6U\u0010:-I\u00cf\u001a\u00c1\u00b6O:!" +
			"\u00dbehy:\u001e\u00c6U\u00ab|O>\u0017V;\u00ed&A\u0002\u00b97\u009aQ" +
			"](Q\u009f\u0091\u00a5\u00c32\u00073\u0085\u009b\u009c\u00a7`&\u00e3" +
			"\u001f\u00e012(\u00c0Y\u0017\u00a6\u00b2\u001el\u00de53\u00e0\u00b8'" +
			"\u00b2T");
	}

	private void assertUnexpectedReplyThrown(String errorString)
	{
		try
		{
			OMCParser.parseErrorString(errorString);
		}
		catch (UnexpectedReplyException e)
		{
			/* looking good */
			return;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			/* no other types of exception should be thrown */
			fail("unexpected exception thrown while calling " +
					"OMCParser.parseErrorString()");
		}
		/* parseErrorString() did not find any parse errors */
		fail("OMCParser.parseErrorString() accepted illegal error string '"
				+ errorString + "'");
	}
}
