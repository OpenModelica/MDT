/*
 * 
 */
package org.openmodelica.simulation.environment.core;

import java.util.Map;
import java.util.TreeMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Log.
 */
public class Log {

	/** A container to log all used operations with their corresponding sequence number Key = sequence number as Integer, Value = operation as String. */
	private Map<Integer, String> seqUsageLog;
	
	/**
	 * Instantiates a new log.
	 */
	public Log(){
		seqUsageLog = new TreeMap<Integer, String>();
	}
	
	/**
	 * Put seq usage.
	 *
	 * @param seq the seq
	 * @param operationToUseFor the operation to use for
	 */
	public void putSeqUsage(Integer seq, String operationToUseFor){
		seqUsageLog.put(seq, operationToUseFor);
	}
	
}
