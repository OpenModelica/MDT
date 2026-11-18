/**
 * This file is part of Modelica Development Tooling (MDT).
 * The Modelica Development Tooling (MDT) software is 
 * distributed under the conditions specified below.
 *
 * Copyright (c) 2005-2007,
 * The MDT Team: 
 * @author Adrian Pop [adrpo@ida.liu.se], 
 * @author Elmir Jagudin, 
 * @author Andreas Remar, 
 * Programming Environments Laboratory (PELAB),
 * Department of Computer and Information Science (IDA),
 * Linköping University (LiU).
 * 
 * All rights reserved.
 *
 * (The new BSD license, see also 
 *  http://www.opensource.org/licenses/bsd-license.php)
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
 * * Neither the name of Authors nor the name of Linköpings University nor 
 *   the names of its contributors may be used to endorse or promote products 
 *   derived from this software without specific prior written permission.
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

package org.modelica.mdt.omc;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Finds the installation directory of the OpenModelica Compiler when
 * several versions of OpenModelica are installed side by side.
 *
 * The Windows installer sets OPENMODELICAHOME for the default installation
 * and OPENMODELICAHOME_&lt;version&gt; for each installed version, where
 * every character of the version that is not a letter or a digit is
 * replaced by an underscore: version 1.27.0 is found via
 * OPENMODELICAHOME_1_27_0.
 *
 * @author Adrian Pop
 */
public class OMCHome
{
	public static final String ENV_HOME = "OPENMODELICAHOME";
	public static final String ENV_VERSION_PREFIX = ENV_HOME + "_";

	/**
	 * An installation registered as OPENMODELICAHOME_&lt;version&gt;.
	 */
	public static class Installation
	{
		/** the name of the environment variable */
		public final String name;
		/** the installation directory */
		public final File home;

		Installation(String name, File home)
		{
			this.name = name;
			this.home = home;
		}
	}

	/**
	 * @return the name of the environment variable that holds the
	 * installation directory of the given OpenModelica version, a leading
	 * 'v' (as in the git tag v1.27.0) is ignored
	 */
	public static String versionEnvName(String version)
	{
		version = version.trim();
		if (version.length() > 1
				&& (version.charAt(0) == 'v' || version.charAt(0) == 'V')
				&& Character.isDigit(version.charAt(1)))
		{
			version = version.substring(1);
		}
		return ENV_VERSION_PREFIX + version.replaceAll("[^0-9A-Za-z]", "_");
	}

	/**
	 * @return true if the given installation directory has an omc binary
	 */
	public static boolean hasOMC(File home)
	{
		File bin = new File(home, "bin");
		return new File(bin, "omc").isFile() || new File(bin, "omc.exe").isFile();
	}

	/**
	 * Sort key for the names OPENMODELICAHOME_&lt;version&gt;, the newest
	 * version has the highest key.
	 *
	 * The version is &lt;major&gt;_&lt;minor&gt;_&lt;patch&gt; followed by
	 * nothing for a release (1_27_0), by a pre-release part for a build
	 * before that release (1_28_0_dev_1962_g6e348e217c) or by the number of
	 * commits for a build after that release (1_27_0_5_g0123456789). For
	 * the same &lt;major&gt;_&lt;minor&gt;_&lt;patch&gt; a pre-release is
	 * older than the release, which is older than a build after it; the
	 * numbers that follow (e.g. the number of commits) decide between two
	 * builds of the same kind.
	 *
	 * @return major, minor, patch, kind (0 pre-release, 1 release,
	 * 2 commits after the release) and then the numbers that follow
	 */
	public static long[] versionSortKey(String name)
	{
		String[] parts = name.substring(ENV_VERSION_PREFIX.length()).split("_");
		List<Long> key = new ArrayList<Long>();

		int i = 0;
		while (i < parts.length && i < 3 && isNumber(parts[i]))
		{
			key.add(Long.valueOf(parts[i]));
			i++;
		}
		while (key.size() < 3)
		{
			key.add(Long.valueOf(0));
		}

		if (i >= parts.length || parts[i].length() == 0)
		{
			key.add(Long.valueOf(1)); /* release */
		}
		else if (isNumber(parts[i]))
		{
			key.add(Long.valueOf(2)); /* commits after the release */
		}
		else
		{
			key.add(Long.valueOf(0)); /* dev, alpha, beta, rc, ... */
		}

		for (; i < parts.length; i++)
		{
			if (isNumber(parts[i]))
			{
				key.add(Long.valueOf(parts[i]));
			}
		}

		long[] result = new long[key.size()];
		for (int j = 0; j < result.length; j++)
		{
			result[j] = key.get(j).longValue();
		}
		return result;
	}

	/**
	 * @return a negative number, zero or a positive number if the version
	 * in the first name is older than, the same as or newer than the
	 * version in the second name
	 */
	public static int compareVersions(String name1, String name2)
	{
		long[] key1 = versionSortKey(name1);
		long[] key2 = versionSortKey(name2);
		for (int i = 0; i < key1.length && i < key2.length; i++)
		{
			if (key1[i] != key2[i])
			{
				return key1[i] < key2[i] ? -1 : 1;
			}
		}
		return key1.length - key2.length;
	}

	/**
	 * @param env the environment to look at
	 * @return the OpenModelica installations registered as
	 * OPENMODELICAHOME_&lt;version&gt;, newest version first. Variables
	 * which point to a directory without an omc binary are left out.
	 */
	public static List<Installation> installedVersions(Map<String, String> env)
	{
		List<Installation> versions = new ArrayList<Installation>();
		for (Map.Entry<String, String> entry : env.entrySet())
		{
			String name = entry.getKey();
			String value = entry.getValue();
			/* the names of environment variables are case-insensitive on Windows */
			if (!name.toUpperCase().startsWith(ENV_VERSION_PREFIX)
					|| value == null || value.length() == 0)
			{
				continue;
			}
			File home = new File(value);
			if (hasOMC(home))
			{
				versions.add(new Installation(name, home));
			}
		}

		Collections.sort(versions, new Comparator<Installation>()
		{
			public int compare(Installation v1, Installation v2)
			{
				int result = compareVersions(v2.name, v1.name);
				return result != 0 ? result : v1.name.compareTo(v2.name);
			}
		});
		return versions;
	}

	/**
	 * Finds the OpenModelica installation to use:
	 * OPENMODELICAHOME, the default installation, if it is set, and
	 * otherwise the newest version registered as
	 * OPENMODELICAHOME_&lt;version&gt;. When such versions are registered
	 * an OPENMODELICAHOME which has no omc binary (e.g. one left behind by
	 * an uninstalled version) is skipped.
	 *
	 * @param env the environment to look at
	 * @return the installation directory or null if there is none, in
	 * which case omc can still be searched for in the PATH
	 */
	public static File find(Map<String, String> env)
	{
		List<Installation> versions = installedVersions(env);

		String value = null;
		for (Map.Entry<String, String> entry : env.entrySet())
		{
			if (entry.getKey().equalsIgnoreCase(ENV_HOME))
			{
				value = entry.getValue();
				break;
			}
		}

		if (value != null && value.length() > 0)
		{
			File home = new File(value);
			if (versions.isEmpty() || hasOMC(home))
			{
				return home;
			}
		}

		if (!versions.isEmpty())
		{
			return versions.get(0).home;
		}
		return null;
	}

	private static boolean isNumber(String s)
	{
		if (s.length() == 0)
		{
			return false;
		}
		for (int i = 0; i < s.length(); i++)
		{
			if (s.charAt(i) < '0' || s.charAt(i) > '9')
			{
				return false;
			}
		}
		return true;
	}
}
