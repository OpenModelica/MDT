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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.modelica.mdt.debug.gdb.core.mi.command.Command;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecContinue;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecFinish;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecNext;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecNextInstruction;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecReturn;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecStep;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecStepInstruction;
import org.modelica.mdt.debug.gdb.core.mi.command.MIExecUntil;
import org.modelica.mdt.debug.gdb.core.mi.event.MIBreakpointHitEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MIErrorEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MIEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MIFunctionFinishedEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MIInferiorExitEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MIRunningEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MISignalEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MISteppingRangeEvent;
import org.modelica.mdt.debug.gdb.core.mi.event.MIStoppedEvent;
import org.modelica.mdt.debug.gdb.core.mi.output.MIAsyncRecord;
import org.modelica.mdt.debug.gdb.core.mi.output.MIConsoleStreamOutput;
import org.modelica.mdt.debug.gdb.core.mi.output.MIConst;
import org.modelica.mdt.debug.gdb.core.mi.output.MIExecAsyncOutput;
import org.modelica.mdt.debug.gdb.core.mi.output.MILogStreamOutput;
import org.modelica.mdt.debug.gdb.core.mi.output.MINotifyAsyncOutput;
import org.modelica.mdt.debug.gdb.core.mi.output.MIOOBRecord;
import org.modelica.mdt.debug.gdb.core.mi.output.MIOutput;
import org.modelica.mdt.debug.gdb.core.mi.output.MIParser;
import org.modelica.mdt.debug.gdb.core.mi.output.MIResult;
import org.modelica.mdt.debug.gdb.core.mi.output.MIResultRecord;
import org.modelica.mdt.debug.gdb.core.mi.output.MIStatusAsyncOutput;
import org.modelica.mdt.debug.gdb.core.mi.output.MIStreamRecord;
import org.modelica.mdt.debug.gdb.core.mi.output.MITargetStreamOutput;
import org.modelica.mdt.debug.gdb.core.mi.output.MIValue;

/**
 * @author Adeel Asghar
 *
 */
/**
 * Receiving thread of gdb response output.
 * Reads from the input stream of GDB and
 */
public class RxThread extends Thread {
	MISession session;
	LinkedList<MIStreamRecord> fStreamRecords = new LinkedList<MIStreamRecord>();
	int prompt = 1; // 1 --> Primary prompt "(gdb)"; 2 --> Secondary Prompt ">"
	boolean fEnableConsole = true;
	
	public RxThread(MISession s) {
		super("MI RX Thread");
		session = s;
	}
	
	/*
	 * Get the response, parse the output, dispatch for OOB
	 * search for the corresponding token in rxQueue for the ResultRecord.
	 */
	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(session.getChannelInputStream()));
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				// TRACING: print the output.
				setPrompt(line);
				List<String> outputs = filterMIOutput(line + "\n");
				for (String output : outputs) {
					processMIOutput(output + "\n");
					// logging
					session.writeLog(output);
				}
			}
		} catch (IOException e) {
			//MDTDebugCorePlugin.log(null, e);
		}
		// This code should be executed when gdb been abruptly
		// or unxepectedly killed.  This is detected by checking
		// if the channelInputStream is not null.  In normal case
		// session.terminate() will set the channelInputStream to null.
		if (session.getChannelInputStream() != null) {
			Runnable cleanup = new Runnable() {
				public void run() {
					session.getGDBInferior().setTerminated();
					session.terminate();
				}
			};
			Thread clean = new Thread(cleanup, "GDB Died");
			clean.setDaemon(true);
			clean.start();
		}
		// Clear the queue and notify any command waiting, we are going down.
		CommandQueue rxQueue = session.getRxQueue();
		if (rxQueue != null) {
			Command[] cmds = rxQueue.clearCommands();
			for (int i = 0; i < cmds.length; i++) {
				synchronized (cmds[i]) {
					cmds[i].notifyAll();
				}
			}
		}
	}

	/**
	 * @param string
	 */
	private List<String> filterMIOutput(String buffer) {
		// TODO Auto-generated method stub
		if (buffer.toString().contains("*"+MIAsyncRecord.RUNNING+",")) {
			return createMIOutputArray(buffer, "*"+MIAsyncRecord.RUNNING+",");
		} else if (buffer.toString().contains("*"+MIAsyncRecord.STOPPED+",")) {
			return createMIOutputArray(buffer, "*"+MIAsyncRecord.STOPPED+",");
		} else {
			List<String> outputs = new ArrayList<String>();
			outputs.add(buffer);
			return outputs;
		}
	}

	/**
	 * @param buffer
	 * @param string
	 * @return
	 */
	private List<String> createMIOutputArray(String buffer, String separator) {
		// TODO Auto-generated method stub
		List<String> outputs = new ArrayList<String>();
		outputs.add(buffer.substring(0, buffer.indexOf(separator)));
		outputs.add(buffer.substring(buffer.indexOf(separator)));
		return outputs;
	}

	void setPrompt(String line) {
		MIParser parser = session.getMIParser();
		prompt = 0;
		
		if (line == null || parser == null)
			return;
		
		line = line.trim();
		if (line.equals(parser.primaryPrompt)) {
			prompt = 1;
		} else if (line.equals(parser.secondaryPrompt)) {
			prompt = 2;
		}
	}

	public boolean inPrimaryPrompt() {
		return prompt == 1;
	}

	public boolean inSecondaryPrompt() {
		return prompt == 2;
	}
	
	public void setEnableConsole(boolean enable) {
		fEnableConsole = enable;
	}

	public boolean isEnableConsole() {
		return fEnableConsole;
	}

	/**
	 * Search for the command in the RxQueue, set the MIOutput
	 * and notify() the other end.
	 * Any OOBs are consider like event and dispatch to the
	 * listeners/observers in different thread.
	 */
	void processMIOutput(String buffer) {
		MIOutput response = session.getMIParser().parse(buffer);
		if (response != null) {
			List<MIEvent> list = new ArrayList<MIEvent>();
			CommandQueue rxQueue = session.getRxQueue();

			MIResultRecord rr = response.getMIResultRecord();
			if (rr != null) {
				int id = rr.getToken();
				Command cmd = rxQueue.removeCommand(id);
				// Get a snapshot of the accumulated stream records. We clear
				// the collection below (with each new Result Command response).
				MIStreamRecord[] streamRecords = fStreamRecords.toArray(new MIStreamRecord[fStreamRecords.size()]);
				// Check if the state changed.
				String state = rr.getResultClass();
				if ("running".equals(state)) {
					int type = 0;
					// Check the type of command
					// if it was a step instruction set state stepping
					if (cmd instanceof MIExecNext) {
						type = MIRunningEvent.NEXT;
					} else if (cmd instanceof MIExecNextInstruction) {
						type = MIRunningEvent.NEXTI;
					} else if (cmd instanceof MIExecStep) {
						type = MIRunningEvent.STEP;
					} else if (cmd instanceof MIExecStepInstruction) {
						type = MIRunningEvent.STEPI;
					} else if (cmd instanceof MIExecUntil) {
						type = MIRunningEvent.UNTIL;
					} else if (cmd instanceof MIExecFinish) {
						type = MIRunningEvent.FINISH;
					} else if (cmd instanceof MIExecReturn) {
						type = MIRunningEvent.RETURN;
					} else if (cmd instanceof MIExecContinue) {
						type = MIRunningEvent.CONTINUE;
					} else {
						type = MIRunningEvent.CONTINUE;
					}
					session.getGDBInferior().setRunning();
					MIEvent event = new MIRunningEvent(session, id, type);
					if (cmd != null && cmd.isQuiet())
						event.setPropagate(false);
					list.add(event);
				} else if ("exit".equals(state)) {
					// No need to do anything, terminate() will.
					session.getGDBInferior().setTerminated();
				} else if ("connected".equals(state)) {
					session.getGDBInferior().setConnected();
				} else if ("error".equals(state)) {
					if (session.getGDBInferior().isRunning()) {
						session.getGDBInferior().setSuspended();
						MIEvent event = new MIErrorEvent(session, rr, streamRecords);
						if (cmd != null && cmd.isQuiet())
							event.setPropagate(false); 
						list.add(event);
					}
				} else if ("done".equals(state)) {
					// "done" usually mean that gdb returns after some CLI command
					// The result record may contains informaton specific to oob.
					// This will happen when CLI-Command is use, for example
					// doing "run" will block and return a breakpointhit
					processMIOOBRecord(rr, list);
				}

				// Set the accumulate console Stream
				response.setMIOOBRecords(streamRecords);

				// Notify the waiting command.
				// Notify any command waiting for a ResultRecord.
				if (cmd != null) {
					// Process the Command line to recognize patterns we may need to fire event.
//					if (cmd instanceof CLICommand) {
//						cli.processSettingChanges((CLICommand)cmd);
//					} else if (cmd instanceof MIInterpreterExecConsole) {
//						cli.processSettingChanges((MIInterpreterExecConsole)cmd);
//					}

					synchronized (cmd) {
						cmd.setMIOutput(response);
						cmd.notifyAll();
					}
				}
				// Clear the accumulated stream records on each new Result Command response.
				fStreamRecords.clear();

			} else {

				// Process OOBs
				MIOOBRecord[] oobs = response.getMIOOBRecords();
				for (int i = 0; i < oobs.length; i++) {
					processMIOOBRecord(oobs[i], list);
				}
				// If not waiting for any command results, ensure the stream list doesn't
				// get too large. See Bug 302927 for more
				if (rxQueue.isEmpty() && fStreamRecords.size() > 20)
					fStreamRecords.removeFirst();
			}

			MIEvent[] events = list.toArray(new MIEvent[list.size()]);
			session.fireEvents(events);
		} // if response != null
	}

	/**
	 * Dispatch a thread to deal with the listeners.
	 */
	void processMIOOBRecord(MIOOBRecord oob, List<MIEvent> list) {
		if (oob instanceof MIAsyncRecord) {
			processMIOOBRecord((MIAsyncRecord) oob, list);
			fStreamRecords.clear();
		} else if (oob instanceof MIStreamRecord) {
			processMIOOBRecord((MIStreamRecord) oob);
		}
	}

	void processMIOOBRecord(MIAsyncRecord async, List<MIEvent> list) {
		if (async instanceof MIExecAsyncOutput) {
			MIExecAsyncOutput exec = (MIExecAsyncOutput) async;
			// Change of state.
			String state = exec.getAsyncClass();
			if ("stopped".equals(state)) {
				MIResult[] results = exec.getMIResults();
				for (int i = 0; i < results.length; i++) {
					String var = results[i].getVariable();
					MIValue val = results[i].getMIValue();
					if (var.equals("reason")) {
						if (val instanceof MIConst) {
							String reason = ((MIConst) val).getString();
							MIEvent e = createEvent(reason, exec);
							if (e != null) {
								list.add(e);
							}
						}
					}
				}

				// GDB does not provide reason when stopping on a shared library
				// event or because of a catchpoint (in gdb < 7.0).
				// Hopefully this will be fixed in a future version. Meanwhile,
				// we will use a hack to cope. On most platform we can detect by
				// looking at the console stream for phrase. Although it is a
				// _real_ bad idea to do this, we do not have any other
				// alternatives.
				if (list.isEmpty()) {
					String[] logs = getStreamRecords();
					for (int i = 0; i < logs.length; i++) {
						if (logs[i].startsWith("Catchpoint ")) {
							session.getGDBInferior().setSuspended();
							// Example: "Catchpoint 1 (exception caught)"
							StringTokenizer tokenizer = new StringTokenizer(logs[i]);
							tokenizer.nextToken(); // "Catchpoint"
							try {
								int bkptNumber = Integer.parseInt(tokenizer.nextToken()); // 1
								list.add(new MIBreakpointHitEvent(session, exec, bkptNumber));
							}
							catch (NumberFormatException exc) {
								assert false : "unexpected catchpoint stream record format: " + logs[i];
							}
						}
					}
				}
				// We were stopped for some unknown reason, for example
				// GDB for temporary breakpoints will not send the
				// "reason" ??? still fire a stopped event.
				if (list.isEmpty()) {
					session.getGDBInferior().setSuspended();
					MIEvent e = new MIStoppedEvent(session, exec);
					list.add(e);
				}
			}
		} else if (async instanceof MIStatusAsyncOutput) {
			// Nothing done .. but what about +download??
		} else if (async instanceof MINotifyAsyncOutput) {
			// Nothing
		}
	}

	void processMIOOBRecord(MIStreamRecord stream) {
		if (stream instanceof MIConsoleStreamOutput) {
			OutputStream console = session.getConsolePipe();
			if (console != null) {
				MIConsoleStreamOutput out = (MIConsoleStreamOutput) stream;
				String str = out.getString();
				// Process the console stream too.
				setPrompt(str);
				if (str != null && isEnableConsole()) {
					try {
						console.write(str.getBytes());
						console.flush();
					} catch (IOException e) {
					}
				}
			}
			// Accumulate the Console Stream Output response for parsing.
			// Some commands will put valuable info  in the Console Stream.
			fStreamRecords.add(stream);
		} else if (stream instanceof MITargetStreamOutput) {
			OutputStream target = session.getGDBInferior().getPipedOutputStream();
			if (target != null) {
				MITargetStreamOutput out = (MITargetStreamOutput) stream;
				String str = out.getString();
				if (str != null) {
					try {
						target.write(str.getBytes());
						target.flush();
					} catch (IOException e) {
					}
				}
			}
			// Accumulate the Target Stream Output response for parsing.
			// Some commands, e.g. 'monitor' will put valuable info in the Console Stream.
			fStreamRecords.add(stream);
		} else if (stream instanceof MILogStreamOutput) {
			// This is meant for the gdb console.
			OutputStream log = session.getLogPipe();
			if (log != null) {
				MILogStreamOutput out = (MILogStreamOutput) stream;
				String str = out.getString();
				if (str != null && isEnableConsole()) {
					try {
						log.write(str.getBytes());
						log.flush();
					} catch (IOException e) {
					}
				}
			}
			// Accumulate the Log Stream Output response for parsing.
			// Some commands will put valuable info  in the Log Stream.
			fStreamRecords.add(stream);
		}
	}

	/**
	 * Check for any info that we can gather form the console.
	 */
	void processMIOOBRecord(MIResultRecord rr, List<MIEvent> list) {
		MIResult[] results = rr.getMIResults();
		for (int i = 0; i < results.length; i++) {
			String var = results[i].getVariable();
			if (var.equals("reason")) {
				MIValue value = results[i].getMIValue();
				if (value instanceof MIConst) {
					String reason = ((MIConst) value).getString();
					MIEvent event = createEvent(reason, rr);
					if (event != null) {
						list.add(event);
					}
				}
			}
		}
		// We were stopped for some unknown reason, for example
		// GDB for temporary breakpoints will not send the
		// "reason" ??? still fire a stopped event.
		
		/* Adeel 2011-09-16 20:32
		 * this is not needed. It will just create extra Stopped events that are not needed.
		 */
		
//		if (list.isEmpty()) {
//			if (session.getGDBInferior().isRunning()) {
//				session.getGDBInferior().setSuspended();
//				MIEvent event = new MIStoppedEvent(session, rr);
//				session.fireEvent(event);
//			}
//		}
	}

	MIEvent createEvent(String reason, MIExecAsyncOutput exec) {
		return createEvent(reason, null, exec);
	}

	MIEvent createEvent(String reason, MIResultRecord rr) {
		return createEvent(reason, rr, null);
	}

	MIEvent createEvent(String reason, MIResultRecord rr, MIExecAsyncOutput exec) {
		MIEvent event = null;
		// if inferior exited
		if ("exited-normally".equals(reason) || "exited".equals(reason)) {
			if (exec != null) {
				event = new MIInferiorExitEvent(session, exec);
			} else if (rr != null) {
				event = new MIInferiorExitEvent(session, rr);
			}
			session.getGDBInferior().setTerminated(0,false);
		} else if ("breakpoint-hit".equals(reason)) {
			if (exec != null) {
				event = new MIBreakpointHitEvent(session, exec);
			} else if (rr != null) {
				event = new MIBreakpointHitEvent(session, rr);
			}
		} else if ("end-stepping-range".equals(reason)) {
			if (exec != null) {
				event = new MISteppingRangeEvent(session, exec);
			} else if (rr != null) {
				event = new MISteppingRangeEvent(session, rr);
			}
		} else if ("function-finished".equals(reason)) {
			if (exec != null) {
				event = new MIFunctionFinishedEvent(session, exec);
			} else if (rr != null) {
				event = new MIFunctionFinishedEvent(session, rr);
			}
		} else if ("signal-received".equals(reason)) {
			if (exec != null) {
				event = new MISignalEvent(session, exec);
			} else if (rr != null) {
				event = new MISignalEvent(session, rr);
			}
		}
		return event;
	}

	String[] getStreamRecords() {
		List<String> streamRecords = new ArrayList<String>(fStreamRecords.size());
		for (MIStreamRecord rec : fStreamRecords) {
			String str = rec.getString().trim();
			if (str.length() > 0) {
				streamRecords.add(str);
			}
		}
		return streamRecords.toArray(new String[streamRecords.size()]);
	}
}
