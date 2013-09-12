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

package org.modelica.mdt.internal.core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.Region;
import org.modelica.mdt.core.IDefinitionLocation;
import org.modelica.mdt.core.ISourceRegion;

/**
 * This class implements IElementLocation on behalf of OMC proxy plugin.
 */
public class DefinitionLocation implements IDefinitionLocation {	
	private File path;
	private ISourceRegion sourceRegion;

	private Region region = null;

	public DefinitionLocation(String path, int startLine, int startColumn, int endLine, int endColumn) {
		this.path = new File(path);

		//if (!this.path.exists()) {
			//// TODO: this should not be, throw an exception
		//}

		this.sourceRegion = new DefinitionSourceRegion(startLine, startColumn, endLine, endColumn);
	}

	@Override
	public String getPath() {
		return path.getAbsolutePath();
	}

	@Override
	public ISourceRegion getSourceRegion() {
		return sourceRegion;
	}
	
	@Override
	public String toString() {
		String ret = "";
		
		int startLine = sourceRegion.getStartLine();
		int startColumn = sourceRegion.getStartColumn();
		int endLine = sourceRegion.getEndLine();
		int endColumn = sourceRegion.getEndColumn();
		
		ret = "startLine = " + startLine + ", startColumn = " + startColumn + ", endLine = " + endLine + ", endColumn = " + endColumn;
		
		return ret;
	}

	/**
	 * @author Adrian Pop
	 * @deprecated
	 */	
	@Deprecated
	private void computeRegion() {
		BufferedInputStream bis = null;

		try {
			bis = new BufferedInputStream(new FileInputStream(path));
		}
		catch (FileNotFoundException e) {
			/*
			 * we already checked in the constructor that path exists,
			 * this this is not happening
			 */
			//TODO bug location
		}

		String contents = "";
		boolean shouldBreak = false;
		while (!shouldBreak) { // Read in contents of the file.
			try {
				int avail = bis.available();

				if (avail == 0) {
					shouldBreak = true;
				}
				else {
					byte[] buf = new byte[avail];
					bis.read(buf, 0, avail);

					contents += new String(buf);
				}
			}
			catch(IOException e) {
				e.printStackTrace();
				shouldBreak = true;
			}
		}

		// Convert contents of the file to a document.
		Document doc = new Document(contents);

		/*
		 * the default values that are used if exception is 
		 * thrown in the code below 
		 */
		int startChar = 1;
		int endChar = 1;
		try {
			startChar = doc.getLineOffset(sourceRegion.getStartLine() - 1) + sourceRegion.getStartColumn() - 1;
			endChar = doc.getLineOffset(sourceRegion.getEndLine() - 1) + sourceRegion.getEndColumn();
		}
		catch (BadLocationException e) {
			ErrorManager.logError(e);
		}

		region = new Region(startChar, endChar - startChar);
	}	
}
