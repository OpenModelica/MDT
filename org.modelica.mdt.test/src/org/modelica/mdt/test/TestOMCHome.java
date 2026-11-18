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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelica.mdt.omc.OMCHome;

import junit.framework.TestCase;

/**
 * Tests the lookup of the OpenModelica installation when several
 * versions of OpenModelica are installed side by side.
 */
public class TestOMCHome extends TestCase
{
	private File root;

	@Override
	protected void setUp() throws IOException
	{
		root = File.createTempFile("omchome", "");
		root.delete();
		root.mkdir();
	}

	@Override
	protected void tearDown()
	{
		delete(root);
	}

	private void delete(File file)
	{
		File[] children = file.listFiles();
		if (children != null)
		{
			for (File child : children)
			{
				delete(child);
			}
		}
		file.delete();
	}

	/**
	 * create an installation directory, with or without an omc binary
	 */
	private File home(String name, boolean withOMC) throws IOException
	{
		File home = new File(root, name);
		File bin = new File(home, "bin");
		bin.mkdirs();
		if (withOMC)
		{
			new File(bin, "omc.exe").createNewFile();
		}
		return home;
	}

	public void testVersionEnvName()
	{
		assertEquals("OPENMODELICAHOME_1_27_0", OMCHome.versionEnvName("1.27.0"));
		assertEquals("OPENMODELICAHOME_1_27_0", OMCHome.versionEnvName(" v1.27.0 "));
		assertEquals("OPENMODELICAHOME_1_28_0_dev_1962_g6e348e217c",
				OMCHome.versionEnvName("1.28.0~dev-1962-g6e348e217c"));
		assertEquals("OPENMODELICAHOME_version", OMCHome.versionEnvName("version"));
	}

	public void testCompareVersions()
	{
		/* oldest first */
		String[] names = {
				"OPENMODELICAHOME_1_9_12",
				"OPENMODELICAHOME_1_26_3",
				"OPENMODELICAHOME_1_27_0_dev_300_gabcdef0",
				"OPENMODELICAHOME_1_27_0_dev_1962_g6e348e217c",
				"OPENMODELICAHOME_1_27_0",
				"OPENMODELICAHOME_1_27_0_5_g0123456789",
				"OPENMODELICAHOME_1_27_0_12_g0123456789",
				"OPENMODELICAHOME_1_27_1",
				"OPENMODELICAHOME_1_28_0_dev_1_gabcdef0",
		};
		for (int i = 0; i < names.length; i++)
		{
			for (int j = 0; j < names.length; j++)
			{
				int result = OMCHome.compareVersions(names[i], names[j]);
				String message = names[i] + " <=> " + names[j];
				if (i < j)
				{
					assertTrue(message, result < 0);
				}
				else if (i > j)
				{
					assertTrue(message, result > 0);
				}
				else
				{
					assertEquals(message, 0, result);
				}
			}
		}
		assertEquals(0, OMCHome.compareVersions("OPENMODELICAHOME_1_27", "OPENMODELICAHOME_1_27_0"));
	}

	public void testInstalledVersions() throws IOException
	{
		Map<String, String> env = new HashMap<String, String>();
		env.put("PATH", "somewhere");
		env.put("OPENMODELICAHOME", home("default", true).getPath());
		env.put("OPENMODELICAHOME_1_26_3", home("1.26.3", true).getPath());
		env.put("OpenModelicaHome_1_27_0", home("1.27.0", true).getPath());
		env.put("OPENMODELICAHOME_1_28_0_dev_12_gabcdef0", home("nightly", true).getPath());
		/* no omc binary, not existing and empty: left out */
		env.put("OPENMODELICAHOME_1_29_0", home("1.29.0", false).getPath());
		env.put("OPENMODELICAHOME_1_30_0", new File(root, "missing").getPath());
		env.put("OPENMODELICAHOME_1_31_0", "");

		List<OMCHome.Installation> versions = OMCHome.installedVersions(env);
		assertEquals(3, versions.size());
		assertEquals("OPENMODELICAHOME_1_28_0_dev_12_gabcdef0", versions.get(0).name);
		assertEquals("OpenModelicaHome_1_27_0", versions.get(1).name);
		assertEquals(new File(root, "1.27.0"), versions.get(1).home);
		assertEquals("OPENMODELICAHOME_1_26_3", versions.get(2).name);
	}

	public void testFindDefault() throws IOException
	{
		Map<String, String> env = new HashMap<String, String>();
		env.put("OPENMODELICAHOME", home("default", true).getPath());
		env.put("OPENMODELICAHOME_1_27_0", home("1.27.0", true).getPath());
		env.put("OPENMODELICAHOME_1_28_0", home("1.28.0", true).getPath());
		assertEquals(new File(root, "default"), OMCHome.find(env));
	}

	public void testFindDefaultOnlyIsUsedAsIs() throws IOException
	{
		/* as before: with no OPENMODELICAHOME_<version> the default is not checked */
		Map<String, String> env = new HashMap<String, String>();
		env.put("OPENMODELICAHOME", new File(root, "missing").getPath());
		assertEquals(new File(root, "missing"), OMCHome.find(env));
	}

	public void testFindNewestVersion() throws IOException
	{
		Map<String, String> env = new HashMap<String, String>();
		env.put("OPENMODELICAHOME_1_27_0", home("1.27.0", true).getPath());
		env.put("OPENMODELICAHOME_1_28_0", home("1.28.0", true).getPath());
		env.put("OPENMODELICAHOME_1_28_0_dev_5_gabcdef0", home("nightly", true).getPath());
		assertEquals(new File(root, "1.28.0"), OMCHome.find(env));
	}

	public void testFindSkipsStaleDefault() throws IOException
	{
		/* the default installation was removed and its variable left behind */
		Map<String, String> env = new HashMap<String, String>();
		env.put("OPENMODELICAHOME", home("default", false).getPath());
		env.put("OPENMODELICAHOME_1_27_0", home("1.27.0", true).getPath());
		assertEquals(new File(root, "1.27.0"), OMCHome.find(env));
	}

	public void testFindNothing() throws IOException
	{
		Map<String, String> env = new HashMap<String, String>();
		env.put("PATH", "somewhere");
		assertNull(OMCHome.find(env));

		env.put("OPENMODELICAHOME", "");
		env.put("OPENMODELICAHOME_1_29_0", home("1.29.0", false).getPath());
		assertNull(OMCHome.find(env));
	}
}
