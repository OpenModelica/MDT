/*******************************************************************************
 * Copyright (c) 2006 MDT Team, PELAB
 *******************************************************************************/

package org.modelica.mdt.ui.text;

import org.eclipse.jface.text.IDocument;

/**
 * Definition of Modelica partitioning and its partitions.
 * @since 0.6.2
 */
public interface IModelicaPartitions {	
	/**
	 * The identifier of the Modelica partitioning.
	 */
	String MODELICA_PARTITIONING= "___modelica_partitioning";  

	/**
	 * The identifier of the single-line end comment partition content type.
	 */
	String MODELICA_SINGLE_LINE_COMMENT= "__modelica_singleline_comment"; 
	/**
	 * The identifier multi-line comment partition content type.
	 */
	String MODELICA_MULTI_LINE_COMMENT= "__modelica_multiline_comment"; 
	/**
	 * The identifier of the Modelica string partition content type.
	 * It can be string or comment here.
	 */
	String MODELICA_STRING= "__modelica_string";
	
	public static String[] legalPartitionTypes = new String[] {
			IModelicaPartitions.MODELICA_MULTI_LINE_COMMENT,
			IModelicaPartitions.MODELICA_SINGLE_LINE_COMMENT,
			IModelicaPartitions.MODELICA_STRING,
		};	
	
	public static String[] partitionTypes = new String[] {
		IDocument.DEFAULT_CONTENT_TYPE,
		IModelicaPartitions.MODELICA_MULTI_LINE_COMMENT,
		IModelicaPartitions.MODELICA_SINGLE_LINE_COMMENT,
		IModelicaPartitions.MODELICA_STRING,
	};	

}
