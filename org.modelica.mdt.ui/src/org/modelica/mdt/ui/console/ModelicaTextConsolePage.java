/**
 * This file is part of Modelica Development Tooling (MDT).
 * The Modelica Development Tooling (MDT) software is 
 * distributed under the conditions specified below.
 *
 * Copyright (c) 2005-2006,
 * The MDT Team: 
 * @author Adrian Pop [adrpo@ida.liu.se], 
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

package org.modelica.mdt.ui.console;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.TextConsole;
import org.eclipse.ui.console.TextConsolePage;
import org.eclipse.ui.console.TextConsoleViewer;

public class ModelicaTextConsolePage extends TextConsolePage
{
	private TextConsole fConsole = null;
	private TextConsoleViewer fViewer = null;

	/**
	 * @author Adrian Pop [adrpo@ida.liu.se], 
	 * @param console
	 * @param view
	 */
	public ModelicaTextConsolePage(TextConsole console, IConsoleView view)
	{
		super(console, view);
		fConsole = console;
	}

	@Override
	protected TextConsoleViewer createViewer(Composite parent)
	{
		fViewer = new ModelicaTextConsoleViewer(parent, fConsole);
		return fViewer;
	}

	@Override
	public TextConsoleViewer getViewer()
	{
		return fViewer;
	}
	
	@Override
	public void setViewer(TextConsoleViewer viewer)
	{
		this.fViewer = viewer;
	}
	
	

}
