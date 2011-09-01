/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Linkoping University,
 * Department of Computer and Information Science,
 * SE-58183 Linkoping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 
 * AND THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S  
 * ACCEPTANCE OF THE OSMC PUBLIC LICENSE.
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from Linkoping University, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS
 * OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 */
package org.modelica.mdt.debug.gdb.core.mi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Observable;

import org.eclipse.core.resources.ResourcesPlugin;
import org.modelica.mdt.debug.core.MDTDebugCorePlugin;
import org.modelica.mdt.debug.gdb.core.mi.command.Command;
import org.modelica.mdt.debug.gdb.core.mi.command.CommandFactory;
import org.modelica.mdt.debug.gdb.core.mi.command.MIGDBExit;
import org.modelica.mdt.debug.gdb.core.mi.command.MIGDBSet;
import org.modelica.mdt.debug.gdb.core.mi.command.MIGDBShowPrompt;
import org.modelica.mdt.debug.gdb.core.mi.command.MIInterpreterExecConsole;
import org.modelica.mdt.debug.gdb.core.mi.event.MIEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MIGDBExitEvent;
import org.modelica.mdt.debug.gdb.core.mi.output.MIGDBShowInfo;
import org.modelica.mdt.debug.gdb.core.mi.output.MIParser;
import org.modelica.mdt.debug.gdb.core.mi.pty.IMITTY;

/**
 * @author Adeel Asghar
 *
 */
public class MISession extends Observable {
	// GDB Process
	Process fGDBProcess;
	// GDB process streams
	InputStream fInChannel;
	InputStream fInErrorChannel;
	OutputStream fOutChannel;
	// Threads
	TxThread fTxThread;
	RxThread fRxThread;
	ErrorThread fErrorThread;
	EventThread fEventThread;
	// Command queues
	CommandQueue fTxQueue;
	CommandQueue fRxQueue;
	Queue fEventQueue;
	// Piped Streams from GDB inferior to eclipse
	PipedInputStream fMIInConsolePipe;
	PipedOutputStream fMIOutConsolePipe;
	PipedInputStream fMIInLogPipe;
	PipedOutputStream fMIOutLogPipe;
	// GDB commands factory
	CommandFactory fMICommandFactory;
	// parser to parse GDB outputs
	MIParser fMIParser;
	
	long fCommandTimeout = 5000;
	// GDB inferior process i.e actual program we are debugging
	GDBInferior fGDBInferior;
	Process fSessionProcess;
	private boolean fTerminated;
	boolean fUseInterpreterExecConsole;
	// logging
	private File fLogFile;
	private BufferedWriter fLogFileWriter;
	
	/**
	 * @param fPty 
	 * @param fGDBProcess
	 * @throws MIException 
	 * @throws IOException 
	 */
	public MISession(Process gdbProcess, IMITTY pty) throws MIException, IOException {
		// TODO Auto-generated constructor stub
		fGDBProcess = gdbProcess;
		// get the gdb streams
		fInChannel = fGDBProcess.getInputStream();
		fInErrorChannel = fGDBProcess.getErrorStream();
		fOutChannel = fGDBProcess.getOutputStream();
		// create the transmission, receiving, error and event threads
		fTxThread = new TxThread(this);
		fRxThread = new RxThread(this);
		fErrorThread = new ErrorThread(this);
		fEventThread = new EventThread(this);
		// create the transmission, receiving and event threads
		fTxQueue = new CommandQueue();
		fRxQueue = new CommandQueue();
		fEventQueue = new Queue();
		// create the MI response parser
		fMIParser = new MIParser();
		// create the MI command factory
		fMICommandFactory = new CommandFactory();
		// create the gdb inferior process which actually represents our debugged program
		fGDBInferior = new GDBInferior(this, pty);
		// logging
		fLogFile = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString() + "/debuggerlog.txt");
		fLogFileWriter = new BufferedWriter(new FileWriter(fLogFile));
		
		setup();
		
		// start all threads
		fTxThread.start();
		fRxThread.start();
		fErrorThread.start();
		fEventThread.start();
		
		try {
			initialize();
		} catch (MIException exc) {
			// Kill the Transmition thread.
			if (fTxThread.isAlive()) {
				fTxThread.interrupt();
			}		
			// Kill the Receiving Thread.
			if (fRxThread.isAlive()) {
				fRxThread.interrupt();
			}
			// Kill the Error reading Thread.
			if (fErrorThread.isAlive()) {
				fErrorThread.interrupt();
			}
			// Kill the event Thread.
			if (fEventThread.isAlive()) {
				fEventThread.interrupt();
			}
			// close the logging
			fLogFileWriter.close();
			// rethrow up the exception.
			throw exc;
		}
	}

	/**
	 * @throws MIException 
	 * 
	 */
	private void initialize() throws MIException {
		// TODO Auto-generated method stub
		// Disable a certain number of irritations from gdb.
		// Like confirmation and screen size.
		MIGDBSet confirm = getCommandFactory().createMIGDBSet(new String[]{"confirm", "off"});
		postCommand(confirm);
		confirm.getMIInfo();

		MIGDBSet width = getCommandFactory().createMIGDBSet(new String[]{"width", "0"});
		postCommand(width);
		width.getMIInfo();

		MIGDBSet height = getCommandFactory().createMIGDBSet(new String[]{"height", "0"});
		postCommand(height);
		height.getMIInfo();
		
		fUseInterpreterExecConsole = canUseInterpreterExecConsole();

		String prompt = getCLIPrompt();
		if (prompt != null) {
			getMIParser().cliPrompt = prompt;
		}
	}	

	/**
	 * @return
	 */
	private boolean canUseInterpreterExecConsole() {
		// TODO Auto-generated method stub
		// Try to discover if "-interpreter-exec" is supported.
		try {
			MIInterpreterExecConsole echo = getCommandFactory().createMIInterpreterExecConsole("echo");
			postCommand(echo);
			echo.getMIInfo();
			return true;
		} catch (MIException e) {
			//
		}
		return false;
	}
	
	/**
	 * @return
	 * @throws MIException 
	 */
	private String getCLIPrompt() throws MIException {
		// TODO Auto-generated method stub
		// Get GDB's prompt
		MIGDBShowPrompt prompt = getCommandFactory().createMIGDBShowPrompt();
		postCommand(prompt);
		MIGDBShowInfo infoPrompt = prompt.getMIGDBShowInfo();
		String value = infoPrompt.getValue();
		if (value != null && value.length() > 0) {
			return value.trim();
		}
		return null;
	}

	/**
	 * @throws MIException 
	 * 
	 */
	private void setup() throws MIException {
		// TODO Auto-generated method stub
		// The Process may have terminated earlier because
		// of bad arguments etc .. check this here and bail out.
		try {
			fGDBProcess.exitValue();		// Raise the IllegalThreadStateException if gdb has started fine otherwise get the error. 
			InputStream err = fGDBProcess.getErrorStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(err));
			String line = null;
			try {
				line = reader.readLine();
				reader.close();
			} catch (Exception e) {
				// the reader may throw a NPE.
			}
			if (line == null) {
				line = MDTDebugCorePlugin.getResourceString("MISession.setup.Process_Terminated");
			}
			throw new MIException(line);
		} catch (IllegalThreadStateException e) {
			// Ok, it means the process is alive.
		}
	}
	
	/**
	 * @return InputStream GDB Input Stream
	 */
	public InputStream getChannelInputStream() {
		// TODO Auto-generated method stub
		return fInChannel;
	}
	
	/**
	 * @return InputStream GDB Input Error Stream
	 */
	public InputStream getChannelErrorStream() {
		// TODO Auto-generated method stub
		return fInErrorChannel;
	}
	
	/**
	 * @return OutputStream GDB Output Stream
	 */
	public OutputStream getChannelOutputStream() {
		// TODO Auto-generated method stub
		return fOutChannel;
	}
	
	/**
	 * @return RxThread the receiving thread
	 */
	public RxThread getRxThread() {
		// TODO Auto-generated method stub
		return fRxThread;
	}
	
	/**
	 * @return CommandQueue GDB receiving command queue
	 */
	public CommandQueue getRxQueue() {
		// TODO Auto-generated method stub
		return fRxQueue;
	}

	/**
	 * @return CommandQueue GDB transmission command queue
	 */
	public CommandQueue getTxQueue() {
		// TODO Auto-generated method stub
		return fTxQueue;
	}
	
	/**
	 * @return Queue events queue
	 */
	public Queue getEventQueue() {
		// TODO Auto-generated method stub
		return fEventQueue;
	}

	/**
	 * @return MIParser GDB output parser
	 */
	public MIParser getMIParser() {
		// TODO Auto-generated method stub
		return fMIParser;
	}

	/**
	 * @return
	 */
	public OutputStream getLogPipe() {
		// TODO Auto-generated method stub
		if (fMIOutLogPipe == null) {
			getMILogStream();
		}
		return fMIOutLogPipe;
	}

	/**
	 * @return 
	 *
	 * get MI Console Stream.
	 * The parser will make available the MI console stream output.
	 * 
	 */
	public InputStream getMILogStream() {
		// TODO Auto-generated method stub
		if (fMIInLogPipe == null) {
			try {
				fMIOutLogPipe = new PipedOutputStream();
				fMIInLogPipe = new PipedInputStream(fMIOutLogPipe);
			} catch (IOException e) {
				MDTDebugCorePlugin.log(null, e);
			}
		}
		return fMIInLogPipe;
	}
	
	/**
	 * @return
	 */
	OutputStream getConsolePipe() {
		if (fMIOutConsolePipe == null) {
			getMIConsoleStream();
		}
		return fMIOutConsolePipe;
	}

	/**
	 * get MI Console Stream.
	 * The parser will make available the MI console stream output.
	 * @return 
	 */
	public InputStream getMIConsoleStream() {
		// TODO Auto-generated method stub
		if (fMIInConsolePipe == null) {
			try {
				fMIOutConsolePipe = new PipedOutputStream();
				fMIInConsolePipe = new PipedInputStream(fMIOutConsolePipe);
			} catch (IOException e) {
			}
		}
		return fMIInConsolePipe;
	}

	/**
	 * @return Process the actual debug program
	 */
	public GDBInferior getGDBInferior() {
		return fGDBInferior;
	}

	/**
	 * @return
	 */
	public Process getGDBProcess() {
		// TODO Auto-generated method stub
		return fGDBProcess;
	}
	
	/**
	 * equivalent to:
	 * postCommand(cmd, cmdTimeout) 
	 */
	public void postCommand(Command cmd) throws MIException {
		postCommand(cmd, fCommandTimeout);
	}

	/**
	 * Sends a command to gdb, and wait(timeout) for a response.
	 * if timeout < 0 the wait will be skipped.
	 * 
	 */
	public void postCommand(Command cmd, long timeout) throws MIException {

		// Test if we are in a sane state.
		if (!fTxThread.isAlive() || !fRxThread.isAlive()) {
			throw new MIException(MDTDebugCorePlugin.getResourceString("MISession.postCommand.Thread_Terminated"));
		}

//		// Test if we are in the right state?
//		if (inferior.isRunning()) {
//			// REMINDER: if we support -exec-interrupt
//			// Let it throught:
//			if (!(cmd instanceof MIExecInterrupt)) {
//				throw new MIException(MIPlugin.getResourceString("src.MISession.Target_not_suspended"));
//			}
//		}

//		if (inferior.isTerminated()) {
//			// the only thing that can call postCommand when the inferior is in a TERMINATED
//			// state is MIGDBShowExitCode, for when MIInferior is computing error code.
//			if (!(cmd instanceof MIGDBShowExitCode)) {
//				throw new MIException(MIPlugin.getResourceString("src.MISession.Inferior_Terminated"));
//			}
//		}

		if (isTerminated()) {
			throw new MIException(MDTDebugCorePlugin.getResourceString("MISession.postCommand.Session_terminated"));
		}
		fTxQueue.addCommand(cmd);

		// do not wait around the answer.
		if (timeout < 0) {
			return;
		}
		// Wait for the response or timedout
		synchronized (cmd) {
			// RxThread will set the MIOutput on the cmd
			// when the response arrive.
			while (cmd.getMIOutput() == null) {
				try {
					cmd.wait(timeout);
					if (cmd.getMIOutput() == null) {
						throw new MIException(MDTDebugCorePlugin.getResourceString("MISession.postCommand.Target_not_responding"));
					}
				} catch (InterruptedException e) {
					//MDTDebugCorePlugin.log(null, e);
				}
			}
		}
	}
	
	/**
	 * Terminate the MI Session
	 */
	public void terminate() {
		// TODO Auto-generated method stub
		// Sanity check.
		if (isTerminated()) {
			return;
		}
		// Destroy any MI Inferior(Process) and streams.
		fGDBInferior.destroy();
		// {in,out}Channel is use as predicate/condition
		// in the {RX,TX,Event}Thread to detect termination
		// and bail out.  So they are set to null.
		InputStream inGDB = fInChannel;
		fInChannel = null;
		OutputStream outGDB = fOutChannel;
		fOutChannel = null;
		// Although we will close the pipe().  It is cleaner
		// to give a chance to gdb to cleanup.
		// send the exit(-gdb-exit).  But we only wait a maximum of 2 sec.
		MIGDBExit gdbExitCmd = fMICommandFactory.createMIGDBExit();
		try {
			postCommand(gdbExitCmd, 2000);
		} catch (MIException e) {
			//ignore any exception at this point.
		}
		fTerminated = true;
		// Make sure gdb is killed.
		// FIX: the destroy() must be call before closing gdb streams
		// on windows if the order is not follow the close() will hang.
		if (fGDBProcess != null) {
			fGDBProcess.destroy();
		}
		// Close the input GDB prompt
		try {
			if (inGDB != null)
				inGDB.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		// Close the output GDB prompt
		try {
			if (outGDB != null)
				outGDB.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		// Destroy the MI console stream.
		try {
			fMIInConsolePipe = null;
			if (fMIOutConsolePipe != null) {
				fMIOutConsolePipe.close();
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}
		// Destroy the MI log stream.
		try {
			fMIInLogPipe = null;
			if (fMIOutLogPipe != null) {
				fMIOutLogPipe.close();
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}
		// Kill the Transmission thread.
		try {
			if (fTxThread.isAlive()) {
				fTxThread.interrupt();
				fTxThread.join(fCommandTimeout);
			}
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
		// Kill the Receiving Thread.
		try {
			if (fRxThread.isAlive()) {
				fRxThread.interrupt();
				fRxThread.join(fCommandTimeout);
			}
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
		// Kill the Error Thread.
		try {
			if (fErrorThread.isAlive()) {
				fErrorThread.interrupt();
				fErrorThread.join(fCommandTimeout);
			}
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
		// Kill the event Thread ... if it is not us.
		if (!fEventThread.equals(Thread.currentThread())) {			
			// Kill the event Thread.
			try {
				if (fEventThread.isAlive()) {
					fEventThread.interrupt();
					fEventThread.join(fCommandTimeout);
				}
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
		// close the logging
		try {
			fLogFileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		// Tell the observers that the session is terminated
		notifyObservers(new MIGDBExitEvent(this, 0));
		// Should not be necessary but just to be safe.
		deleteObservers();
	}
	
	/**
	 * Notify the observers of new MI OOB events.
	 */
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}

	/**
	 * @return CommandFactory the MI commands factory
	 */
	public CommandFactory getCommandFactory() {
		// TODO Auto-generated method stub
		return fMICommandFactory;
	}

	/**
	 * Check if the gdb session is terminated.
	 */
	public boolean isTerminated() {
		return fTerminated;
	}
	
	/**
	 * Return a "fake" Process that will
	 * encapsulate the call input/output of gdb.
	 */
	public Process getSessionProcess() {
		if (fSessionProcess == null) {
			fSessionProcess = new SessionProcess(this);
		}
		return fSessionProcess;
	}
	
	public boolean inPrimaryPrompt() {
		return fRxThread.inPrimaryPrompt();
	}

	public boolean inSecondaryPrompt() {
		return fRxThread.inSecondaryPrompt();
	}

	/**
	 * @param events
	 */
	public void fireEvents(MIEvent[] events) {
		// TODO Auto-generated method stub
		if (events != null && events.length > 0) {
			for (int i = 0; i < events.length; i++) {
				fireEvent(events[i]);
			}
		}
	}

	/**
	 * @param event
	 */
	public void fireEvent(MIEvent event) {
		// TODO Auto-generated method stub
		if (event != null) {
			getEventQueue().addItem(event);
		}
	}

	/**
	 * Return the default Command Timeout, default 10 secs.
	 */
	public long getCommandTimeout() {
		// TODO Auto-generated method stub
		return fCommandTimeout;
	}

	/**
	 * @return
	 */
	public boolean useExecConsole() {
		// TODO Auto-generated method stub
		return fUseInterpreterExecConsole;
	}	

	/**
	 * @return the fLogFileWriter
	 */
	public BufferedWriter getLogFileWriter() {
		return fLogFileWriter;
	}

}
