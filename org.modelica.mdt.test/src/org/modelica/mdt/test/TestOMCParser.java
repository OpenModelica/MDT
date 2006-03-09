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

import org.modelica.mdt.core.compiler.ICompileError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
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
	
			/* no errors aka empty error string */
			errors = OMCParser.parseErrorString("");
			assertEquals(0, errors.length);
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
//		 accepted		

		assertUnexpectedReplyThrown(""); 
 
		assertUnexpectedReplyThrown(
			"!F\u00a13Q\u0081a\u00d8L~\u00ad\u00db\u00bcj\u0014!Z;\u00dci\u00b9%" +
			"\u0000\u00d0\"\u0001E7D04\u00ce\u00d1\u00b6r\u0083W\u00c8\u0019sJ" +
			"\u00d2J\u0005\u0081MaK\u00b2\u0012\u00ccg\u008d9\u00bb\u0085}9.\u00ce" +
			"w\u008f\u00d8\u001c\u00a8\u0083\u001b\u00f0J%:\u0092\u001a\u00e1[ " +
			"\u0097TD\u00997\u001e\u00f4\u00ca\u009f6C|(\u00f4\u00a9\u0005u\u00ecl" +
			"/iNM\u00f0\u0092`nm\u009a\u0098Y\u00f3\u00bd\u00beb\u0099\u00ac\u00a5" +
			"d\u00ca2`\u008e\u00a2\u00d5I?\u00e6gm\u00af\u0004\u00fa\"T\u0017" +
			"\u00eb\u00cb\u00ad6p\u00d7\u00b7%)f:\u0006:Y\u0002\u00cb\u001bD\u00fd" +
			"4:\u000c(\u0006-\u0097`\u008a\u00e6\u0017\u0084C\u00e4\u0098\u001bS" +
			"\u0080A!\u00c3\u0004;\u0086M\u00edy]9C\u00f9Y\"\u008fb%z");

		assertUnexpectedReplyThrown(
			"\u00a4+\u0005\u008c\u00e5\u0089\u0095\u00f9H\u00fe\u00bdw \u00bf" +
			"\u00e6\u008a\u00f1\u00fe\u00a4!sv\u0017\u00a7Y\u00b7J:\u0089\u0084" +
			"\u0014\u0003\u00a5\u00d3#\u00cc\u00d5\u00ac\u008f\u00e0\u0088P\u00c2" +
			"\u00b5\u00e5y\u0089\u0086\u00a4\u009fx-\u0009~x9\u00e8\u009e\u00fb" +
			"\u009d\u00a1\u00f4\u009eS:\u00d9I\u0096\u00aa\u0011y\u00cf\u00c7" +
			"\u008eO:j\u00d7q\u0004\u0087O<\u00d0\u00ec\u00bb\u00d3\u00d1\u0010b" +
			"\u00d8'n\u00d8b\u00c9\u0081:p\u00b1wU\u0006%\u0084\u00b4\u00cf\u0085X" +
			",\u00ea}\u00c4\u00d0!\u009fD\u00cd9\u009c6e\u00dd\u001e\u00ebv\u008e" +
			"\u00f4\u0084eIR?]\u000eh\u001c\u001f\u00cc\u0096(\u00ce\u0096O\\C" +
			"\u00dd\u00aeap\u00caW\u001ax\u001b\u00b6r\u00070");

		assertUnexpectedReplyThrown(
			"\u00a2\u00c7V\u00b0,\u00a4\u0000\u00f1:\u0081\u00e2:m\u00c4\u00af" +
			"\u00b8\u00b7I#\u00e6L\u00e0\u00a1\u001f\u00be\u00d4\u0091\u009f\u00f1" +
			"3\u0017\u0089\u0011\u008c\u00f5(\u00bd\u0085\u00a3\u00ed\u0093\u00f3" +
			"\u00d6\u00b1r\u00d7y\u00f2\u00c5Jgl\u00f9\u001f\u000f\u00a3N,\u00b36" +
			"\u00da\u00f1\u001e:\u008a\u00c9\u00c1:7\u0095\u00c3A\u00e42`\u00c7/8" +
			" \u0001\u008f\u00e3\u00db&\u0085\u00cer\u000f\u00e2\u00f0rC\u000e" +
			"\u00ca\u00a1\u00ad~3\u0000\u00db\u00cb\u000c\u00d2\u001d=]\u0012Z" +
			"\u00e9^\u00d7\u0009\u00f6|\u00b8\u001e\u00f8\u00f6\u009a\u00ff,\u00b7" +
			"\u0010d");

		assertUnexpectedReplyThrown(
			"e\u00f3\u00be;\u000c\u00e1\u0018\u009cG:\u00da\u0085\u00c7\u009e:" +
			"\u0099\u0003\u009c\u0081\u000b\u008a\u00ea\u00a7\\AN\u00048\u0081" +
			"\u001d\u00e2\u001e\u00f8M\\`\u0019+\u00d6\u00061\u00f4\u0085\u0099" +
			"\u009bS\u00f9&\u001d\u0094\u00b7\u00afN\u0094\u00d4\u0096[\u0015:" +
			"\u00cc\u00d1\u00b1B|\u00b0;\u0083\u00ae\u00ca:\u00d6\u00bcr\u00d5e" +
			"\u0014)d7x{l\u00a1,\u0087\u00f5\u0089\u00fa^\u00e1\u0016dj\u00f3" +
			"\u00a2\u00a94\u009f(Gu\u001e\u00be(?fQA\u00ce\u0009\u00d3Q'\u0016d" +
			"\u000eEH'\u0001\u00b0\u00f4wn\u00f6N\u00b2\u00e9\u0012\u00bc\u00d0" +
			"\u00b0\u0083\u00fd{\u00ceO\u00d47R\u001d\u008f *O\u008d\u00cew\u00ea" +
			"\u00dc\u00a4\u008d}\u008b\u00e8G\u0090\u00a1I98\u00ef\u009c3\u00f3" +
			"\u00a6\u00e1\u00e6b\u009f\u00f5\u00da]\u00c2\u00dft\u00c6\u0099\u00ba" +
			"\u008c\u00af\u0010\u00b9\u009a3\u00d0\u00d5\u00d3JS5\u009e{");

		assertUnexpectedReplyThrown(
			"\u00e4\u00c3N\u00ff\u000c\u0009\u00d2\u00a4\u00b3)c)$O\u0090M\u00e7" +
			"\u0012\u00cf\u00ef\u009dN\u0089\u00fe\u00f9\u00be9\u009aG}:dS\u00b1" +
			"\u00bb(\u00a59\u00d2 +\u0007Q\u00ff\u00cf\u00d1\u0016'\\\u009b%K:&" +
			"\u0089Bf\u00e2!\u0001\u0018Yq\u00cc7f\u0015\u0097*O\u00c8\u009bo(" +
			"\u00a5:7\u00d5A[6\u00c3t:\u0088\u0095\u0088\u00f9\u00e63\u00b9\u00ed" +
			"\u001a\u00bbh!\u0014\u00a0\u00a4\u0009g\u00bc\u0008\u00dc\u00balx" +
			"\u00c5\u00ee]\u00dcm\u0092x\u00e4\u0011\u00e5\u001c\u0086,\u00df" +
			"\u0085I\u0093\u00c0\u00ec\u00f2\u00a1B\u00ad\u00e4zk\u0016\u00fe" +
			"\u00a5?b\u00c9*f&\u009c\u00a0\u00a1\u00f3&H2\u00bf_z\u00ed\u00d49" +
			"\u008e\u009f\u00e7'\u0082k3m\u0089\u0008\u0099\u0009n\u00a0\u00b0" +
			"\u001f0y\u00f7d]+e");

		assertUnexpectedReplyThrown(
			"1\u00d3\u00eeqm\u00dc\u008c\u00ba\u00d12\u00e1i`\u008d\u00ca\u00f2e" +
			"\u00c0%\u00b1\u00c2\u00d7\u00ae:\u00b5ooJ\u00f7>\u001f\u00aa::\u0003" +
			"\u00ac,\u0011z%\u00be\u00dd^\u00cf\u00d3\u0007J/\u00dfB;~\u00a9\"" +
			"\u00f4\u008f\u0081\u00b2\u0095X\u009c\u008b\u00b0+u,y?K<\u0003\u00ef" +
			"\u00d9n\u00c1K\u00f6\u00b1\u008a}\u0005\u00dd\u000e\u001a\u001aK" +
			"\u000bt\u00faEaF\u0007\u0082\\=\u00b3\u00dd\u00bf\u00f08*\u00fc/" +
			"\u00c6a'P7\u00b23j3\u00a9\u00d1\u0084FB\u00af\u00d9_\u00989\u00166" +
			"\u00f0\u00c7\u0003|\u00ed-r\u0006;u\u0099\u00fdfO\u00dfB%\u0009\u00fe" +
			"\u00bc\u00b4\u00fa\u009aA\u00a3\u009a\u00e6\u0000F4\u008a\u00aa:" +
			"\u0097\u0090\u008b\u00a9\u00d79\u00eb\u00b2\u00b4NH\u00aa\u0012\u0091" +
			"\u008d\"S\u0012b\u00ea.\u00b9\u0084\u008b\u00f5G\u00c4G]]\u00e7(" +
			"\u00f1\u00fdN \u00fbA\u00c0\u009a\u00d9\u00f9Iiq\u00a7\u00cd\u00d0pT" +
			"\u00e0\u00ccKLi\u0003v");

		assertUnexpectedReplyThrown(
			"E\u00e7\u00cc:\u00c2,^\u0005\u00be\u0083\u00b4UL\u00f7\u00fdZ\u0087" +
			"\u008dp\u001aJ\u00bf\u008bl\u00c9L\u00b3\u00d5\u00a6Qu\u00c02gzX" +
			"\u0089\u008e\u00b06\u00aa\u0096\u00c11(/c\u009d\u00d4\u0092no\u0092" +
			"\u00f48SK\u00fc\u000e\u0014=\u00c8<\u0099\u00c26*\u00a4d\u00b8\u00b8," +
			"f\u0007\u00f5\u00eey\u001d\\A:\u008f)\u00f37k\u00a3\u00e1He\u00c0" +
			"\u00a8\u0011\u0087\u00c3\u008f\u00cc:\u009e\u00fb\u0018\u00c7\u009c" +
			"\u0089\u00d6#R\u00dc\u008dGF\u0004\u00f5hs\u00b7\u00e2W\u00b0\u00d5:" +
			")R\u00bb\u0015\u009bA\u000c\u00cf\u0013\u00b1N\u0015\u00c7e\u00e5" +
			"\u00a4\u00c7\u00e6\u00d1\u00b8~a\u0087\u009f\u00e6\u00e7n=\u00c6" +
			"\u00ce\u008fT\u0088H\u00da,\u00c6\u00a2>~\u0094\u0007\u00a8\u0095" +
			"\u00c2~]2?\u00bc\u00b2\u001d\u00a4\u0080S\u001cyn.d\u00daR\u008b" +
			"\u00f1w\u00b7\u00f3\u00a3/\u0093\u00ccg_2\u0091\u00bf\u00af\u00142Z" +
			"\u00cby\u00b4N({H\u008c\u00a7GQ\u0084\u0013D-@\u00a9~");

		assertUnexpectedReplyThrown(
			"\u00b0\u00f1\u00dc\u00b7\u00feM:\u00a7\u0014\u00c9\u00a7a\u0098t:" +
			"\u00ce\u001e\u001b(\u0096:uZ\u00fc\u00b5\u00ffn\u00cb\u0012(\u00b0L" +
			"\u00a0l\u0007\u001a\u0081\\\u00fc\u00d7\u00a7\u00a7>;!\u00e7|\u00e2" +
			"\u0095L|WCf\\\u008c.\u00ee\u00a4@\\\u00ebRbN\u0084\u00c3#\u0000\u00a2" +
			"\u00a5v\u00d4\u0016\u00a1\"W!d)\u0004\u00b4R\u00072c\u00f9:\u0092" +
			"\u009f\u0012(\u00f4\u00e3\u00ef\u000c\u0092\u00a4\u00b7j\u00e2\u001e}" +
			"\u00c0\u00f37_&\u00cd\u00db\"Ii-\u0002\u00b8L)\u00bfhT\u00e5Z\u00f4Ka" +
			"\u00c8\u00e1\u008at\u00c9O\u00e5d\u00eba\u0000g]\u00bdz\u00a9B2\u00a7" +
			"\u00e6{i\u001d\u00c2\u0080\u00ba");

		assertUnexpectedReplyThrown(
			"\u00b3;\u00e1\u00ed\u008c\u0003\u00b4Q\u00b2R\u00f3:a0\u00957%\u00c8" +
			"\u00b6\u00a9\u00dd\u00f44/k:\u00f3\u00eax\u00ea&\u0096}:^\u009f\u00ba" +
			"\u00f2:\u0018\u0082\u0080\u0089*m\u0088\u0096'I\u00ede\u00d1\u000c" +
			"\u00ef\u00a1\u00f9Wv\u00d5\u00e6\u00a4\u0003H8_\u00f4\u0094\u0005" +
			"\u00ae\u00a9\u0015\u008e\u0007\u00ba\u00c7W\u0096\u00c2\u00f3\u00dcly" +
			"\u00bc\u00eeL\u00b2?\u0019C\u00ab`\u0094V`\u00cc\u00b1\u00e2\u00da" +
			"\u00d9\u0084/e\u008c97\u00cf[\u00a7\u00cfDz]\u00ab\u0083h\u00166EJ");

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

		assertUnexpectedReplyThrown(
			"\u0012\u00e0o:e\u0080\u001b\u008a\u009aE\u00a5\\\u00a5Y\u00a1}$\u009e" +
			"8\u0088\u00e6\u00c4\u00aa\u00b6\u00ef\u008a}\u0006\u00a7:`\u00c6W" +
			"\u009f\u00d0x\u0016X\u00b0\u0096\u0088?@\u008a\u00d2\u00f903\u00e6:" +
			"\u009fp\u00af\u0013kn\u00c0\u00f8\u00a9\u00d4\u00ef\u009a/:9\u00d2" +
			"\u00e0\u008c\u00c1\u00e7=\u009c\u009b2\u00d6\u00e1\u0011\u00b4\u00dc," +
			"g\u00df\u0018Q\u00e7\u00a0\u00fa\u00c4\u00e5k\u0018\u0097\u00e8\u00c0" +
			"\u00062\u00fd\u00e7\u00b7Y\u00bb]V\u00c1\u009b`\u0008^V\u00d7\u00a4" +
			"\u00d2\u00fdW\u00e7\u00a3\u008bg+\u00b6\u008a\u0006-C\u0091\u00cfR)" +
			"\u0089\u0009yLb3\u000b60HC\u00ad?~\u00b5\u0081z\u009cp\u00ce\u0007 " +
			"\u008f\u00fc\u00f9\u00fe5\u0002\u00bb{E\u0009\u00fc\u0011\u00991" +
			"\u00e2\u00a7cze\u00b9\u0091\u00b1dga\u00ceR\u0018\u0008\u0097\u00b9[" +
			".");

		assertUnexpectedReplyThrown(
			"^\u0003;\u000e\u00c9\u00bfR\u00d3M\u001dc/\u00a7\u00dd\u0095\u00e7" +
			"\u001f\u001a$\u00de|\u009908\u0010\u00ab?.\u00b1iy[#R\u0013S_\u00bd" +
			"\u00e9\u0001\u0003c2\u00c7dE\u0001e\u000bO\u00d5\u00a4\u0086\u00e0" +
			"\u00e5\u0002\u00d1\u000b\u0014\u00bb\u00f2\u00e9\u00a0z\u00ec\u00b5" +
			"\u00d7H\u008f.\u00a1\u0092\u00c8\u00cd\u00ad\u00de\u00fd\u00f4\u00c9:" +
			"\u00bf\u00a2\u0088`;\u001d\u00f9?\u00df@\u00f6oI\u00b82\u00f5\u00fb" +
			"\u008fm#@e:T\u00fa\u00d6\u0002\u00ac\u00b2\u0090\u00c9V\u0080\u0094" +
			"\u00cev\\4\u00d5\u0006k&/:a8\u0012\u0080\u00b7\u001b=+\u00ad\u00ecY" +
			"\u00b2\u00bc\u00d8\u000f\u0009:\u0088\u00c9o\u00f6{\u000bs?\u0005" +
			"\u00b2\u0012\u00abA\u00dfF\u0098\\\u0008(\u000f=v\u00e6\u00df\u00ad" +
			"\u00e7u\u00e2m\u00fd<\u0091\u009d\u0086\u00ac\u00b1\u00cc\u00b6N" +
			"\u00a89\u00afW]\u0081\u00d9\u0082HN\u00a8~\u00cb\u00a1\u00adP\u0090" +
			"\u0018EQ\u009d0\u00db\u00d5\u00f1");

		assertUnexpectedReplyThrown(
			"cuQS\u00a1A\u009d6<\u0007\u00ad\u0014\\\u009dJ>h\u00ae\u001c\u00ee" +
			"\u0015IYJ:E\u00f4R\u0000b\u000fQT\u008d\u00a7e\u001a)\u00d4#x\u00a6b" +
			"\u0092\u00b6\u00d6g\u00e8C9\u0093\u0019s\u00f78\u00f7\u00d4\u00df" +
			"\u0091p\u009b\u0015\u0015\u00e4\u00ed\u0006Q\u00ac:\u00fd'\u00c4" +
			"\u001cL^:p\u0086#\u00f3\u00e3\u00d1\u00fb\u00cc\u00cc\u00d0$\u00d0" +
			"\u00f70\u0009}\u00ae\u000b\u0097c\u0018&\u0097:\u0003\u009bw\u0085" +
			"\u0006\u00ee?4\u00ddl\u0004\u00dd\u00a2\u00fdX\u00ea\u00aa\u00fb" +
			"\u00c0\u00e9FzDY%\u0092\u00a6\u0005\u00c2\u0016\u00ab\u00a9-A\u0083" +
			"\u008f\u00b7\u00d3Ut%eSc!&\u00dd\u0003\u0090\u00e7\u00da\u00caBE=r" +
			"\u0003k\u009d[\\iQ\u00e3\u00a2\u0092\u00cb\u00e6\u00d7\u00a6\u00cf>" +
			"\u00c3k\u00acu\u00c2]\u0081Zq\u00f7%m\u00d5\u00fb\u00c0\u00dc\u00ba(" +
			"\u00ee\\\u009f\u00d0B\u0000i^J\u00af\u0013\u0013\u00ab\u0008\u00f59" +
			"\u001f-4\u0000");

		assertUnexpectedReplyThrown(
			"\u009d\u00aa\u00de\u00deL\u00cf\u00c2\u00bc-\u00c4\u00ac\u001a/\u00a9" +
			"\u00aexd\u00c0\u0011\u00d9?\u0017\u00e6\u00b8\u00d2\u0002\u00b7\u00f6" +
			"\u00af\u00c4\u00acv\u0008\u00c0)4=\u00d5\u00e1L\u00f8N\u00d1\u0008n" +
			"\u00ce\u00e7\u00d1\u001d\u0089\u00f0\u001dB\u0095\u00bb\u00e9 \u0098" +
			"\u00a3\u008f\u008b\u008b\u0087{\u000e\u00e1\u008d\u00d4K\u00f0\u00bb" +
			"\u00dee\u0090\u00cb\u001d\u00c1\u0018\u00ce9vo\u00c8\u00c2\u0018t=9" +
			"\u00f4r\u00dfi\u00c8\u00a1\u0082B\u00ae\u00abi\u00e82\u00c3\u001b[" +
			"\u00bb=2.\u00fb\u00aaE\u0017\u00a6\u008fz/1\u00a6\u0091`R5m:\u00d1" +
			"\u00adR\u00f8#\u0018\u00c44_i\u009bt\u00b2\u009eL\u0011$\u0096\u00e9:" +
			"9\u00fcx\u00ba\u009cW\u009e\u00e8J:X\u0019~\u00f5\u00ce\u0093\u00e2" +
			"\u00c7W\u0095\u0010\u0010:\u00bf\u0002\u00c0\u0080\u009e(l\u0090" +
			"\u0085\u00e2\u00062\u00ee\u0080\u000f\u0004\u0081o\u00aa\u0082\u000f" +
			"\u000eb8\u0093\u0099x ;!\u00b8\u00fe\u00dd\u00cde`\u0086\u00f0\u0012]" +
			">Z\u001d\u00d1\u00e8\u00c2\u00ef\u00a3\u00b3\u00f09");

		assertUnexpectedReplyThrown(
			"\n");

		assertUnexpectedReplyThrown(
			"`/o:\u0014\u00ac|\u0084\u00e0:\u008a\u00f0U\u00907\u00e4%\u00e7\u00c6" +
			")u:\u008d\u009c\u00a1\u00dcN\u0008\u0096\u00ad\u00f4\u008a\u00cf|" +
			"\u00a5\u009c\u00c4\u00a5[\u00abv\u000c\u00d7\u00ec\u00f5q\u00be\u0008" +
			",o\u0013\u00ea\u001a@`\u0013\u00d1St3\u00d0\u0005'v\u00dd\u001d^" +
			"\u0080q\u00c7\u0090\u0094\u00b3\u00bd\u0018:\u0098\u0088 \u0097\\" +
			"\u009a\u000f\u00f7T\u00fc]\u00c0Q\u00b9\u00a5e\u00a0\u00e8bi@\u0005" +
			"\u00a4\u00ff\u00d6og[\u00f4\u0002t\u00bb7\u00e4\u00b6\u0008\u00c0" +
			"\u00fb\u00e0R\u00c4\u00bc\u00c9e \u00b7\u00f6\u0085\u00f8\u0087\u00c7" +
			"(u\u00d9\u00f7\u0013\u001d\u00a0z\u00b0\u00f8\u00f3\u00d9\u00cd_" +
			"\u00be\\\u00acc\u00d2\u00e3\u0085\u00c2\u00a4i\u0015\u00e0\u00ac" +
			"\u001f\u00fc\u00a0\u00c4\u00f6\u00e49)\u0001W");

		assertUnexpectedReplyThrown(
			"ly\u001e,:Jp\u00c3\u0003\u009b/L\u008cx\u00c0\u0006w\u00fa\u001c" +
			"\u0095\u00fc\u00b0\u001b\u00dd\u00fe\u00e7\u0008\u00b3\u00c2PfX\u00a4" +
			"\u00c9\u00ca!rb\u00b8\u00f2\u0086\u00ec\u00b7K\u0003F\u00f1ruv)\u00df" +
			"\u00ef\u00ae#\u00f1\u00fa\u000f9\u00990\u0016\u00fa\u000c\u00d8\u00a4" +
			"\u0082\u00fc9\u00a7i\u00d1si=-\u0018\u00daB{\u00cf=12[*R-\u0004\u001b" +
			"\u0016:v\u00f9\u009b`R\u0086:\u00d6\u00b9|PaB\u009d\u00e3\u00e5\u001d" +
			"\u00ea\u008f\u00acs@'\u00f5e\u00bc\u00ec\u00f9\u0006\u00bfo\u00b4q." +
			"\u00afI\u00c3:\u00eb\u00f9\u00b1\u00c6_$q]A\u0086\u0019G\u00f9\u008b" +
			"\u00f4\u00f1\u00ab\u00e3\u0005c\u008fD/\u0018\u00e5V\u00f2\u008bv" +
			"\u00ae\u00bf(\u001d\u0092\u00f2\u00c6!g\u0000\u0086VrF \u00e2\u00f7" +
			"\u0007\u009a4K\u0086\u00c7<\u0012t\u00ad)n=v\u0082`>)d\u00a5\u00e1K" +
			"\u00f6\u009b\u00f5\u00c5\u00cc\u00f6\u0093\u0015Br\u0008");

		assertUnexpectedReplyThrown(
			"\n");

		assertUnexpectedReplyThrown(
			"\r");

		assertUnexpectedReplyThrown(
			"\u00d1\u00a9\u00be\u00cf|\u00f4\u0097>\u00b6c\u00e3\u00b8g\u00b0" +
			"\u00d7OW:\u00dfAF\u00d7\u00d8X\u0013\u00daF\u00ace\u001f\u0004\u0088C" +
			"\u0017x\u001c\u00b7\u00ddU6\u0010yG}%\u00aa\u00c0A\u00e8x\u00d69_" +
			"\u0092\u0099K\u00fe!\u008aU\u0087Fawuni\u00ba:\u0010R\u00e51\\\u00d1{" +
			"\u00bbkl'WI\u00dd\u0016I\u0094\u00a98\u0007I\u00eeh\u00f3\u008f\u001e" +
			"&\u00dc\u00da\u00f7q^\u0014\u009e()T\u00febZM\u00bb\u0019\u0018hH" +
			"\u00978\u00ef:Z\u0010$\u009d\u0098Cb\u0097\u001e\u000cx\u0088\u0017" +
			"\u00cf\u00095\u0084\u00cb\u008e&*\u00a5\u0000\u00c6\u0096\u0003\u00e0" +
			"\u00b8A\u00ef\u00ed\u009a\u00bd\u00fe\u0096\u009cn\u00c7 \u0007\u00d9" +
			"?\u00b2\u00d8\u0012\u000b\u00beO -\u00b5\u008187\u00cb;\"\u00d5\u00f2" +
			"\u00c9CJ\u0012!\u00a8:\u00f17\u009d\u0000\u008e\u0080\u00e7\u00d56" +
			"\u00d2'\u00e6\u0012\u00b5k,\u0090y;\u001b\u0091]l\u00ad\u0091cD\u00b6" +
			"c\u008f");

		assertUnexpectedReplyThrown(
			"\r");

		
		/* random string generated with getRandomString(-2147477960, 1124) */
		assertUnexpectedReplyThrown(
			"\u009f\u00a8\u001c\u0019'\u00fe\u0082pL\u0080\u008a\u00c2\u000eT::" +
			"\u00e2!j\u001fPCx\u0009\u00b31aO{D:\u001dyv*j\u0097\u00cb\u00d3\u00f3" +
			"\u0019c\u000f\u009a\u00ff\u00be\u0014\u0098\u00eb6\u00cf\u0092\u00d0" +
			"\u0082\u00f0\u00d6\u008e\u000c:/mD\u0010\u00c8\u0097B\u00ca\u008e" +
			"\u00a9\u00e3\u00c2\u00e0\u0090\u000e\u00f3\u0015\u0097s\u00db\u0005," +
			"\u0012E\u00c5\u000e\u00e3\u00b7\u00ec\u009cCO\\]}\u00ad!^\u00b5\u00dd" +
			"<\u00ce\u00ad\u000c\u00c3\u0007\u0097S\u00ef\u00e6T+9\u00d6wI\u000e~");

		/* random string generated with getRandomString(-2147219462, 1124) */
		assertUnexpectedReplyThrown(
			"\"_y\u009b\u0093\u0006\u00a5X\u00e6#\u0092::\u00e9\u00c1\u0093$r" +
			"\u0082\u00a7\u00fa\u00b7Ya\u008f\u00880\u00ce'e^\u00c8j\u00e8\u00e9" +
			"\u00bfG\u009c\u0010\u00da\u00c8L',\u0084X\u00d8Of\u00e8!\u0002,3" +
			"\u0097\u00dfQ\u0015\u00e0\u0095\u00df\u0087w\u0080\u00f3\u0006\u0092" +
			"\u00cf\\(\u00a8\u00c9:\u0016\u00b7\u00fc\u0004\u00f0\u001a\u009e" +
			"\u00f2\u00b8o\u00a4\u0094\u00a7\u008e'\u0095p\u00b1<\u00a2c\u00fe+" +
			"\u0015\u00ca\u00b3\u00ff\u00del\u00dcol7Ov.\u00df\u0089\u0004\u00fa" +
			"\u00ec\u00ef\u00d9\u0014\u0015:\u00a3\u00f5OD;\u0007\u00b7gE{\u00b8d" +
			"\u00d9\u00a3r\u00cd\u009c\u0004\u00a1\u00e2\u00d6\u00da\u0003M\u00e2D" +
			"\u00a6\u00e9\u0086\u0091\u00ea)\u0010\u00c0E\u00b3\u0098G\u00f1\u00b1" +
			"\u00e1\u00a5\u00e6=v\u00c0\u001d{q\u0099\u00b7c\u0005\u00c4\u00d2m" +
			"\u008a\u00a8\u001dr\u00e6sb\u00da\u00e8y#\u0007\u00bb\u00c1\"\u0005" +
			"\u00eaf]\u0088q\u0011\u00d2\u00c3q");


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
