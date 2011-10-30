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

//package org.modelica.mdt.test.util;

/**
 * This file contains some code snippets that can 
 * are useful to copy'n'paste while testing/debugging but
 * don't quite fit into some proper class.
 */
//public class CodeDepot {}

/**
 * Show calling test methods
 * 
 * Prints to standard out all JUnit test methods that are running
 * this code. Useful to find out exactly which tests are invoking
 * some particular code. Just past it at the beginning of whatever
 * block you want to check-out. 
 */

//	for (StackTraceElement element : Thread.currentThread().getStackTrace())
//	{
//		if (element.getMethodName().startsWith("test"))
//		{
//			System.out.println("called from " + element.getClassName() +
//					"." + element.getMethodName());
//			break;
//		}
//	}

/**
 * prints the calling stack
 */
//for (StackTraceElement element : Thread.currentThread().getStackTrace())
//{
//	System.out.println(element.getClassName() +
//			"." + element.getMethodName() + ":" +
//			element.getLineNumber());
//}

/**
 * This piece of code generates a random string in ISO-8859-1 encoding
 * based on a seed.
 * The length of the string is random up to maxLenght.
 * This function is deterministic, given the same seed and maxLength function 
 * always returns same string.
 */

//private Random rnd = new Random();
//private CharsetDecoder cd = Charset.forName("ISO-8859-1").newDecoder();
//
//private String getRandomString(int seed, int maxLength)
//{
//	rnd.setSeed(seed);
//	int length =  rnd.nextInt(maxLength);
//	ByteBuffer byteBuffer = ByteBuffer.allocate(length);
//	byte[] buff = byteBuffer.array();
//	rnd.nextBytes(buff);
//	
//	CharBuffer charBuffer = CharBuffer.allocate(length);
//	
//	cd.reset();
//	cd.decode(byteBuffer, charBuffer, true);
//	cd.flush(charBuffer);
//	
//	return new String(charBuffer.array());
//}

/**
 * 
 * @param str the string to escape all special characters (", \, ans so in)
 * and convert to source code format suitable for pasting into *.java file
 * 
 * @param width string will be formatted as lines of source code with provided 
 * maximal width
 * @return the provided string with all weird characters escaped, a format
 * suitable to paste into java source code
 */
//public String escape(String str, String linePrefix, int width)
//{
//	String res = "\"";
//	String nextPiece;
//	int currLength = 1;
//	
//	//System.out.println("escaping:" + str);
//	for (char c : str.toCharArray())
//	{
//		
//		switch (c) 
//		{
//			case '"':
//				nextPiece = "\\\"";
//				break;
//			case '\n':
//				nextPiece = "\\n";
//				break;
//			case '\r':
//				nextPiece = "\\r";
//				break;
//			case '\\':
//				nextPiece = "\\\\";
//				break;
//			default:
//				if (c < 32 || c > 127)
//				{
//					nextPiece = escape(c);
//				}
//				else
//				{
//					/* 
//					 * only characters witch ASCII value inside [32,127] intervall
//					 * are left unescaped
//					 */
//					nextPiece = Character.toString(c);
//				}
//		}
//		
//		/* linebreak the escaped string literal */
//		if (currLength + nextPiece.length() > (width))
//		{
//			res += "\" +\n" + linePrefix + "\"";
//			currLength = 1;
//		}
//		res += nextPiece;
//		currLength += nextPiece.length();
//		
//	}
//	
//	return res + "\"";
//}

//private String escape(char c) 
//{
//	String hex = Integer.toHexString(c);
//	
//	/*
//	 * prepend "\\u" and zeros to make the hex number exactly 4 characters long
//	 * and to create a legal unicode escape sequence 
//	 */
//	return "\\u" + "0000".substring(0, 4 - hex.length()) + hex; 
//}



