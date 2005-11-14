
package org.modelica.mdt.test;

import org.modelica.mdt.internal.core.ModelicaElement;

import junit.framework.TestCase;

public class TestModelicaElement extends TestCase
{
	public void testIsLegalIdentifierName()
	{
		String[] legalIdentifiers = {"_", "abc", "_abc", "_000", "p00_2",
									 "'abc'", "'\\\\'", "'!323%%\"'",
									 "'!\"#$%&\\'()*+,-./:;<=>?@[\\\\]^_`{|}~'"};
		String[] illegalIdentifiers = {"012a", "åäö", "'\\'", "'''", "'\n'"};
		for(String s: legalIdentifiers)
		{
			assertTrue("Legal identifier rejected ("+s+")",
					ModelicaElement.isLegalIdentifierName(s));
		}
		for(String s: illegalIdentifiers)
		{
			assertFalse("Illegal identifier accepted ("+s+")",
					ModelicaElement.isLegalIdentifierName(s));
		}
	}
}
