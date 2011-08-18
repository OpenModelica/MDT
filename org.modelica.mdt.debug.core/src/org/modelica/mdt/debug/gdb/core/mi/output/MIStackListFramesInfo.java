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
package org.modelica.mdt.debug.gdb.core.mi.output;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adeel Asghar
 *
 * GDB/MI stack list frames info.
 *
 */
public class MIStackListFramesInfo extends MIInfo {

	MIFrame[] frames;
	MIFrame frame;

	public MIStackListFramesInfo(MIOutput out) {
		super(out);
	}

	public MIFrame[] getMIFrames() {
		if (frames == null) {
			parse();
		}
		return frames;
	}
	
	public MIFrame getMIFrame() {
		if (frame == null) {
			parseFrame();
		}
		return frame;
	}

	void parse() {
		List<MIFrame> aList = new ArrayList<MIFrame>(1);
		if (isDone()) {
			MIOutput out = getMIOutput();
			MIResultRecord rr = out.getMIResultRecord();
			if (rr != null) {
				MIResult[] results =  rr.getMIResults();
				for (int i = 0; i < results.length; i++) {
					String var = results[i].getVariable();
					if (var.equals("stack")) {
						MIValue val = results[i].getMIValue();
						if (val instanceof MIList) {
							parseStack((MIList)val, aList);
						} else if (val instanceof MITuple) {
							parseStack((MITuple)val, aList);
						}
					}
				}
			}
		}
		frames = (MIFrame[])aList.toArray(new MIFrame[aList.size()]);
	}
	
	void parseFrame() {
		if (isDone()) {
			MIOutput out = getMIOutput();
			MIResultRecord rr = out.getMIResultRecord();
			if (rr != null) {
				MIResult[] results =  rr.getMIResults();
				for (int i = 0; i < results.length; i++) {
					String var = results[i].getVariable();
					if (var.equals("frame")) {
						MIValue value = results[i].getMIValue();
						if (value instanceof MITuple) {
							frame = new MIFrame((MITuple)value);
						}
					}
				}
			}
		}
	}

	void parseStack(MIList miList, List<MIFrame> aList) {
		MIResult[] results = miList.getMIResults();
		for (int i = 0; i < results.length; i++) {
			String var = results[i].getVariable();
			if (var.equals("frame")) {
				MIValue value = results[i].getMIValue();
				if (value instanceof MITuple) {
					aList.add (new MIFrame((MITuple)value));
				}
			}
		}
	}

	// Old gdb use tuple instead of a list.
	void parseStack(MITuple tuple, List<MIFrame> aList) {
		MIResult[] results = tuple.getMIResults();
		for (int i = 0; i < results.length; i++) {
			String var = results[i].getVariable();
			if (var.equals("frame")) {
				MIValue value = results[i].getMIValue();
				if (value instanceof MITuple) {
					aList.add (new MIFrame((MITuple)value));
				}
			}
		}
	}
}