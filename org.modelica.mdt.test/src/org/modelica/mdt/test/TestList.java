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

import org.modelica.mdt.core.List;
import org.modelica.mdt.core.Element;
import org.modelica.mdt.core.ListElement;

import junit.framework.TestCase;

/**
 * test org.modelica.mdt.core.List class' code 
 */

public class TestList extends TestCase 
{
	private static final String[] content = { "foo", "hej", "bar", "wee" }; 
	/**
	 * test creating, populating and using a simple (not nested) list
	 */
	public void testSimpleList()
	{

		List l = new List();
		
		assertEquals("empty lists size is wrong", 0, l.size());
		
		l.append(new Element(content[0]));		
		assertEquals(1, l.size());

		l.append(new Element(content[1]));		
		assertEquals(2, l.size());

		l.append(new Element(content[2]));		
		assertEquals(3, l.size());
		
		l.append(new Element(content[3]));		
		assertEquals(4, l.size());

		/*
		 * test accessing elements with elementAt()
		 */
		for (int i=0; i < content.length; i++)
		{
			ListElement le = l.elementAt(i);
			
			assertTrue("wrong type of element recieved", le instanceof Element);
			assertTrue(((Element)le).toString().equals(content[i]));
		}
		
		/*
		 * test iterating over the list
		 */
		int i = 0;
		for (ListElement le : l)
		{
			assertTrue("wrong type of element recieved", le instanceof Element);
			assertTrue(((Element)le).toString().equals(content[i++]));
			
		}
	}
}
