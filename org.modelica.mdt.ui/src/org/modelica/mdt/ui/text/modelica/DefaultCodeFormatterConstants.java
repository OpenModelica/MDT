/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.ui.text.modelica;

import java.util.Map;

import org.modelica.mdt.ui.UIPlugin;

/**
 * Constants used to set up the options of the code formatter.
 *
 * @since 3.0
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DefaultCodeFormatterConstants {

	/**
	 * <pre>
	 * FORMATTER / Value to set a brace location at the end of a line.
	 * </pre>
	 * @see #FORMATTER_BRACE_POSITION_FOR_ANONYMOUS_TYPE_DECLARATION
	 * @see #FORMATTER_BRACE_POSITION_FOR_ARRAY_INITIALIZER
	 * @see #FORMATTER_BRACE_POSITION_FOR_BLOCK
	 * @see #FORMATTER_BRACE_POSITION_FOR_CONSTRUCTOR_DECLARATION
 	 * @see #FORMATTER_BRACE_POSITION_FOR_METHOD_DECLARATION
 	 * @see #FORMATTER_BRACE_POSITION_FOR_SWITCH
	 * @see #FORMATTER_BRACE_POSITION_FOR_TYPE_DECLARATION
	 * @since 3.0
	 */
	public static final String END_OF_LINE = "end_of_line";	//$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Value to set an option to false.
	 * </pre>
	 * @since 3.0
	 */
	public static final String FALSE = "false"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to align type members of a type declaration on column
	 *     - option id:         "org.eclipse.jdt.core.formatter.formatter.align_type_members_on_columns"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGN_TYPE_MEMBERS_ON_COLUMNS = UIPlugin.PLUGIN_ID + ".formatter.align_type_members_on_columns";	 //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option for alignment of arguments in allocation expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_arguments_in_allocation_expression"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_ARGUMENTS_IN_ALLOCATION_EXPRESSION = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_arguments_in_allocation_expression";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of arguments in enum constant
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_arguments_in_enum_constant"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.1
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_ARGUMENTS_IN_ENUM_CONSTANT = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_arguments_in_enum_constant";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of arguments in explicit constructor call
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_arguments_in_explicit_constructor_call"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_ARGUMENTS_IN_EXPLICIT_CONSTRUCTOR_CALL = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_arguments_in_explicit_constructor_call";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of arguments in method invocation
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_arguments_in_method_invocation"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_ARGUMENTS_IN_METHOD_INVOCATION = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_arguments_in_method_invocation";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of arguments in qualified allocation expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_arguments_in_qualified_allocation_expression"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_ARGUMENTS_IN_QUALIFIED_ALLOCATION_EXPRESSION = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_arguments_in_qualified_allocation_expression";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of assignment
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_assignment"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, M_NO_ALIGNMENT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.2
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_ASSIGNMENT  = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_assignment";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of binary expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_binary_expression"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_BINARY_EXPRESSION = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_binary_expression";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of compact if
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_compact_if"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_ONE_PER_LINE, INDENT_BY_ONE)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_COMPACT_IF = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_compact_if";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of conditional expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_conditional_expression"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_ONE_PER_LINE, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_CONDITIONAL_EXPRESSION = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_conditional_expression";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of enum constants
	 *     - option id:        "org.eclipse.jdt.core.formatter.alignment_for_enum_constants"
	 *     - possible values:  values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:          createAlignmentValue(false, WRAP_NO_SPLIT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.1
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_ENUM_CONSTANTS = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_enum_constants";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of expressions in array initializer
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_expressions_in_array_initializer"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_EXPRESSIONS_IN_ARRAY_INITIALIZER = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_expressions_in_array_initializer";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of multiple fields
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_multiple_fields"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_MULTIPLE_FIELDS = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_multiple_fields";//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of parameters in constructor declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_parameters_in_constructor_declaration"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_PARAMETERS_IN_CONSTRUCTOR_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_parameters_in_constructor_declaration";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of parameters in method declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_parameters_in_method_declaration"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_PARAMETERS_IN_METHOD_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_parameters_in_method_declaration";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of selector in method invocation
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_selector_in_method_invocation"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_SELECTOR_IN_METHOD_INVOCATION = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_selector_in_method_invocation";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of superclass in type declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_superclass_in_type_declaration"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_NEXT_SHIFTED, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_SUPERCLASS_IN_TYPE_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_superclass_in_type_declaration";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of superinterfaces in enum declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_superinterfaces_in_enum_declaration"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.1
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_SUPERINTERFACES_IN_ENUM_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_superinterfaces_in_enum_declaration";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of superinterfaces in type declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_superinterfaces_in_type_declaration"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_SUPERINTERFACES_IN_TYPE_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_superinterfaces_in_type_declaration";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of throws clause in constructor declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_throws_clause_in_constructor_declaration"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_THROWS_CLAUSE_IN_CONSTRUCTOR_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_throws_clause_in_constructor_declaration";	 //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option for alignment of throws clause in method declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.alignment_for_throws_clause_in_method_declaration"
	 *     - possible values:   values returned by <code>createAlignmentValue(boolean, int, int)</code> call
	 *     - default:           createAlignmentValue(false, WRAP_COMPACT, INDENT_DEFAULT)
	 * </pre>
	 * @see #createAlignmentValue(boolean, int, int)
	 * @since 3.0
	 */
	public static final String FORMATTER_ALIGNMENT_FOR_THROWS_CLAUSE_IN_METHOD_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.alignment_for_throws_clause_in_method_declaration";	 //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to add blank lines after the imports declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.blank_lines_after_imports"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "0"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_BLANK_LINES_AFTER_IMPORTS = UIPlugin.PLUGIN_ID + ".formatter.blank_lines_after_imports";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to add blank lines after the package declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.blank_lines_after_package"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "0"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_BLANK_LINES_AFTER_PACKAGE = UIPlugin.PLUGIN_ID + ".formatter.blank_lines_after_package";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to add blank lines at the beginning of the method body
	 *     - option id:         "org.eclipse.jdt.core.formatter.number_of_blank_lines_at_beginning_of_method_body"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "0"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_BLANK_LINES_AT_BEGINNING_OF_METHOD_BODY = UIPlugin.PLUGIN_ID + ".formatter.number_of_blank_lines_at_beginning_of_method_body"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to add blank lines before a field declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.blank_lines_before_field"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "0"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_BLANK_LINES_BEFORE_FIELD = UIPlugin.PLUGIN_ID + ".formatter.blank_lines_before_field";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to add blank lines before the first class body declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.blank_lines_before_first_class_body_declaration"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "0"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_BLANK_LINES_BEFORE_FIRST_CLASS_BODY_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.blank_lines_before_first_class_body_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to add blank lines before the imports declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.blank_lines_before_imports"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "0"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_BLANK_LINES_BEFORE_IMPORTS = UIPlugin.PLUGIN_ID + ".formatter.blank_lines_before_imports";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to add blank lines before a member type declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.blank_lines_before_member_type"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "0"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_BLANK_LINES_BEFORE_MEMBER_TYPE = UIPlugin.PLUGIN_ID + ".formatter.blank_lines_before_member_type";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to add blank lines before a method declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.blank_lines_before_method"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "0"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_BLANK_LINES_BEFORE_METHOD = UIPlugin.PLUGIN_ID + ".formatter.blank_lines_before_method";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to add blank lines before a new chunk
	 *     - option id:         "org.eclipse.jdt.core.formatter.blank_lines_before_new_chunk"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "0"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_BLANK_LINES_BEFORE_NEW_CHUNK = UIPlugin.PLUGIN_ID + ".formatter.blank_lines_before_new_chunk";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to add blank lines before the package declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.blank_lines_before_package"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "0"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_BLANK_LINES_BEFORE_PACKAGE = UIPlugin.PLUGIN_ID + ".formatter.blank_lines_before_package";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to add blank lines between import groups
	 *     - option id:         "org.eclipse.jdt.core.formatter.blank_lines_between_import_groups"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "1"
	 * </pre>
	 * Note: Import groups are defined once "Organize Import" operation has been executed. The code formatter itself
	 * doesn't define the import groups.
	 *
	 * @since 3.3
	 */
	public static final String FORMATTER_BLANK_LINES_BETWEEN_IMPORT_GROUPS = UIPlugin.PLUGIN_ID + ".formatter.blank_lines_between_import_groups";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to add blank lines between type declarations
	 *     - option id:         "org.eclipse.jdt.core.formatter.blank_lines_between_type_declarations"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "0"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_BLANK_LINES_BETWEEN_TYPE_DECLARATIONS = UIPlugin.PLUGIN_ID + ".formatter.blank_lines_between_type_declarations";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to position the braces of an annotation type declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.brace_position_for_annotation_type_declaration"
	 *     - possible values:   { END_OF_LINE, NEXT_LINE, NEXT_LINE_SHIFTED, NEXT_LINE_ON_WRAP }
	 *     - default:           END_OF_LINE
	 * </pre>
	 * @see #END_OF_LINE
	 * @see #NEXT_LINE
	 * @see #NEXT_LINE_SHIFTED
	 * @see #NEXT_LINE_ON_WRAP
	 * @since 3.1
	 */
	public static final String FORMATTER_BRACE_POSITION_FOR_ANNOTATION_TYPE_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.brace_position_for_annotation_type_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to position the braces of an anonymous type declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.brace_position_for_anonymous_type_declaration"
	 *     - possible values:   { END_OF_LINE, NEXT_LINE, NEXT_LINE_SHIFTED, NEXT_LINE_ON_WRAP }
	 *     - default:           END_OF_LINE
	 * </pre>
	 * @see #END_OF_LINE
	 * @see #NEXT_LINE
	 * @see #NEXT_LINE_SHIFTED
	 * @see #NEXT_LINE_ON_WRAP
	 * @since 3.0
	 */
	public static final String FORMATTER_BRACE_POSITION_FOR_ANONYMOUS_TYPE_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.brace_position_for_anonymous_type_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to position the braces of an array initializer
	 *     - option id:         "org.eclipse.jdt.core.formatter.brace_position_for_array_initializer"
	 *     - possible values:   { END_OF_LINE, NEXT_LINE, NEXT_LINE_SHIFTED, NEXT_LINE_ON_WRAP }
	 *     - default:           END_OF_LINE
	 * </pre>
	 * @see #END_OF_LINE
	 * @see #NEXT_LINE
	 * @see #NEXT_LINE_SHIFTED
	 * @see #NEXT_LINE_ON_WRAP
	 * @since 3.0
	 */
	public static final String FORMATTER_BRACE_POSITION_FOR_ARRAY_INITIALIZER = UIPlugin.PLUGIN_ID + ".formatter.brace_position_for_array_initializer";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to position the braces of a block
	 *     - option id:         "org.eclipse.jdt.core.formatter.brace_position_for_block"
	 *     - possible values:   { END_OF_LINE, NEXT_LINE, NEXT_LINE_SHIFTED, NEXT_LINE_ON_WRAP }
	 *     - default:           END_OF_LINE
	 * </pre>
	 * @see #END_OF_LINE
	 * @see #NEXT_LINE
	 * @see #NEXT_LINE_SHIFTED
	 * @see #NEXT_LINE_ON_WRAP
	 * @since 3.0
	 */
	public static final String FORMATTER_BRACE_POSITION_FOR_BLOCK = UIPlugin.PLUGIN_ID + ".formatter.brace_position_for_block";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to position the braces of a block in a case statement when the block is the first statement following
	 *             the case
	 *     - option id:         "org.eclipse.jdt.core.formatter.brace_position_for_block_in_case"
	 *     - possible values:   { END_OF_LINE, NEXT_LINE, NEXT_LINE_SHIFTED, NEXT_LINE_ON_WRAP }
	 *     - default:           END_OF_LINE
	 * </pre>
	 * @see #END_OF_LINE
	 * @see #NEXT_LINE
	 * @see #NEXT_LINE_SHIFTED
	 * @see #NEXT_LINE_ON_WRAP
	 * @since 3.0
	 */
	public static final String FORMATTER_BRACE_POSITION_FOR_BLOCK_IN_CASE = UIPlugin.PLUGIN_ID + ".formatter.brace_position_for_block_in_case";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to position the braces of a constructor declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.brace_position_for_constructor_declaration"
	 *     - possible values:   { END_OF_LINE, NEXT_LINE, NEXT_LINE_SHIFTED, NEXT_LINE_ON_WRAP }
	 *     - default:           END_OF_LINE
	 * </pre>
	 * @see #END_OF_LINE
	 * @see #NEXT_LINE
	 * @see #NEXT_LINE_SHIFTED
	 * @see #NEXT_LINE_ON_WRAP
	 * @since 3.0
	 */
	public static final String FORMATTER_BRACE_POSITION_FOR_CONSTRUCTOR_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.brace_position_for_constructor_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to position the braces of an enum constant
	 *     - option id:         "org.eclipse.jdt.core.formatter.brace_position_for_enum_constant"
	 *     - possible values:   { END_OF_LINE, NEXT_LINE, NEXT_LINE_SHIFTED, NEXT_LINE_ON_WRAP }
	 *     - default:           END_OF_LINE
	 * </pre>
	 * @see #END_OF_LINE
	 * @see #NEXT_LINE
	 * @see #NEXT_LINE_SHIFTED
	 * @see #NEXT_LINE_ON_WRAP
	 * @since 3.1
	 */
	public static final String FORMATTER_BRACE_POSITION_FOR_ENUM_CONSTANT = UIPlugin.PLUGIN_ID + ".formatter.brace_position_for_enum_constant";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to position the braces of an enum declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.brace_position_for_enum_declaration"
	 *     - possible values:   { END_OF_LINE, NEXT_LINE, NEXT_LINE_SHIFTED, NEXT_LINE_ON_WRAP }
	 *     - default:           END_OF_LINE
	 * </pre>
	 * @see #END_OF_LINE
	 * @see #NEXT_LINE
	 * @see #NEXT_LINE_SHIFTED
	 * @see #NEXT_LINE_ON_WRAP
	 * @since 3.1
	 */
	public static final String FORMATTER_BRACE_POSITION_FOR_ENUM_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.brace_position_for_enum_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to position the braces of a method declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.brace_position_for_method_declaration"
	 *     - possible values:   { END_OF_LINE, NEXT_LINE, NEXT_LINE_SHIFTED, NEXT_LINE_ON_WRAP }
	 *     - default:           END_OF_LINE
	 * </pre>
	 * @see #END_OF_LINE
	 * @see #NEXT_LINE
	 * @see #NEXT_LINE_SHIFTED
	 * @see #NEXT_LINE_ON_WRAP
	 * @since 3.0
	 */
	public static final String FORMATTER_BRACE_POSITION_FOR_METHOD_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.brace_position_for_method_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to position the braces of a switch statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.brace_position_for_switch"
	 *     - possible values:   { END_OF_LINE, NEXT_LINE, NEXT_LINE_SHIFTED, NEXT_LINE_ON_WRAP }
	 *     - default:           END_OF_LINE
	 * </pre>
	 * @see #END_OF_LINE
	 * @see #NEXT_LINE
	 * @see #NEXT_LINE_SHIFTED
	 * @see #NEXT_LINE_ON_WRAP
	 * @since 3.0
	 */
	public static final String FORMATTER_BRACE_POSITION_FOR_SWITCH = UIPlugin.PLUGIN_ID + ".formatter.brace_position_for_switch";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to position the braces of a type declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.brace_position_for_type_declaration"
	 *     - possible values:   { END_OF_LINE, NEXT_LINE, NEXT_LINE_SHIFTED, NEXT_LINE_ON_WRAP }
	 *     - default:           END_OF_LINE
	 * </pre>
	 * @see #END_OF_LINE
	 * @see #NEXT_LINE
	 * @see #NEXT_LINE_SHIFTED
	 * @see #NEXT_LINE_ON_WRAP
	 * @since 3.0
	 */
	public static final String FORMATTER_BRACE_POSITION_FOR_TYPE_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.brace_position_for_type_declaration";	//$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to control whether blank lines are cleared inside comments
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.clear_blank_lines"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.1
	 * @deprecated Use {@link #FORMATTER_COMMENT_CLEAR_BLANK_LINES_IN_BLOCK_COMMENT} and {@link #FORMATTER_COMMENT_CLEAR_BLANK_LINES_IN_JAVADOC_COMMENT}
	 */
	public final static String FORMATTER_COMMENT_CLEAR_BLANK_LINES = "org.eclipse.jdt.core.formatter.comment.clear_blank_lines"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to control whether blank lines are cleared inside javadoc comments
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.clear_blank_lines_in_javadoc_comment"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.3
	 */
	public final static String FORMATTER_COMMENT_CLEAR_BLANK_LINES_IN_JAVADOC_COMMENT = "org.eclipse.jdt.core.formatter.comment.clear_blank_lines_in_javadoc_comment"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to control whether blank lines are cleared inside block comments
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.clear_blank_lines_in_block_comment"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.3
	 */
	public final static String FORMATTER_COMMENT_CLEAR_BLANK_LINES_IN_BLOCK_COMMENT = "org.eclipse.jdt.core.formatter.comment.clear_blank_lines_in_block_comment"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to control whether comments are formatted
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.format_comments"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.1
	 * @deprecated Use multiple settings for each kind of comments. See {@link #FORMATTER_COMMENT_FORMAT_BLOCK_COMMENT},
	 * {@link #FORMATTER_COMMENT_FORMAT_JAVADOC_COMMENT} and {@link #FORMATTER_COMMENT_FORMAT_LINE_COMMENT}.
	 */
	public final static String FORMATTER_COMMENT_FORMAT = "org.eclipse.jdt.core.formatter.comment.format_comments"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to control whether single line comments are formatted
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.format_line_comments"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.3
	 */
	public final static String FORMATTER_COMMENT_FORMAT_LINE_COMMENT = "org.eclipse.jdt.core.formatter.comment.format_line_comments"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to control whether multiple lines comments are formatted
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.format_block_comments"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.3
	 */
	public final static String FORMATTER_COMMENT_FORMAT_BLOCK_COMMENT = "org.eclipse.jdt.core.formatter.comment.format_block_comments"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to control whether javadoc comments are formatted
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.format_javadoc_comments"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.3
	 */
	public final static String FORMATTER_COMMENT_FORMAT_JAVADOC_COMMENT = "org.eclipse.jdt.core.formatter.comment.format_javadoc_comments"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to control whether the header comment of a Java source file is formatted
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.format_header"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.1
	 */
	public final static String FORMATTER_COMMENT_FORMAT_HEADER = "org.eclipse.jdt.core.formatter.comment.format_header"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to control whether HTML tags are formatted.
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.format_html"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.1
	 */
	public final static String FORMATTER_COMMENT_FORMAT_HTML = "org.eclipse.jdt.core.formatter.comment.format_html"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to control whether code snippets are formatted in comments
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.format_source_code"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.1
	 */
	public final static String FORMATTER_COMMENT_FORMAT_SOURCE = "org.eclipse.jdt.core.formatter.comment.format_source_code"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to control whether description of Javadoc parameters are indented
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.indent_parameter_description"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.1
	 */
	public final static String FORMATTER_COMMENT_INDENT_PARAMETER_DESCRIPTION = "org.eclipse.jdt.core.formatter.comment.indent_parameter_description"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to control whether Javadoc root tags are indented.
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.indent_root_tags"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.1
	 */
	public final static String FORMATTER_COMMENT_INDENT_ROOT_TAGS = "org.eclipse.jdt.core.formatter.comment.indent_root_tags"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to insert an empty line before the Javadoc root tag block
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.insert_new_line_before_root_tags"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public final static String FORMATTER_COMMENT_INSERT_EMPTY_LINE_BEFORE_ROOT_TAGS = "org.eclipse.jdt.core.formatter.comment.insert_new_line_before_root_tags"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line after Javadoc root tag parameters
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.insert_new_line_for_parameter"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public final static String FORMATTER_COMMENT_INSERT_NEW_LINE_FOR_PARAMETER = "org.eclipse.jdt.core.formatter.comment.insert_new_line_for_parameter"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to specify the line length for comments.
	 *     - option id:         "org.eclipse.jdt.core.formatter.comment.line_length"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "80"
	 * </pre>
	 * @since 3.1
	 */
	public final static String FORMATTER_COMMENT_LINE_LENGTH = "org.eclipse.jdt.core.formatter.comment.line_length"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to compact else/if
	 *     - option id:         "org.eclipse.jdt.core.formatter.compact_else_if"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_COMPACT_ELSE_IF = UIPlugin.PLUGIN_ID + ".formatter.compact_else_if";	//$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to set the continuation indentation
	 *     - option id:         "org.eclipse.jdt.core.formatter.continuation_indentation"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "2"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_CONTINUATION_INDENTATION = UIPlugin.PLUGIN_ID + ".formatter.continuation_indentation";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to set the continuation indentation inside array initializer
	 *     - option id:         "org.eclipse.jdt.core.formatter.continuation_indentation_for_array_initializer"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "2"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_CONTINUATION_INDENTATION_FOR_ARRAY_INITIALIZER = UIPlugin.PLUGIN_ID + ".formatter.continuation_indentation_for_array_initializer";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to indent body declarations compare to its enclosing annotation declaration header
	 *     - option id:         "org.eclipse.jdt.core.formatter.indent_body_declarations_compare_to_annotation_declaration_header"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.2
	 */
	public static final String FORMATTER_INDENT_BODY_DECLARATIONS_COMPARE_TO_ANNOTATION_DECLARATION_HEADER = UIPlugin.PLUGIN_ID + ".formatter.indent_body_declarations_compare_to_annotation_declaration_header";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to indent body declarations compare to its enclosing enum constant header
	 *     - option id:         "org.eclipse.jdt.core.formatter.indent_body_declarations_compare_to_enum_constant_header"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.1
	 */
	public static final String FORMATTER_INDENT_BODY_DECLARATIONS_COMPARE_TO_ENUM_CONSTANT_HEADER = UIPlugin.PLUGIN_ID + ".formatter.indent_body_declarations_compare_to_enum_constant_header";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to indent body declarations compare to its enclosing enum declaration header
	 *     - option id:         "org.eclipse.jdt.core.formatter.indent_body_declarations_compare_to_enum_declaration_header"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.1
	 */
	public static final String FORMATTER_INDENT_BODY_DECLARATIONS_COMPARE_TO_ENUM_DECLARATION_HEADER = UIPlugin.PLUGIN_ID + ".formatter.indent_body_declarations_compare_to_enum_declaration_header";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to indent body declarations compare to its enclosing type header
	 *     - option id:         "org.eclipse.jdt.core.formatter.indent_body_declarations_compare_to_type_header"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_INDENT_BODY_DECLARATIONS_COMPARE_TO_TYPE_HEADER = UIPlugin.PLUGIN_ID + ".formatter.indent_body_declarations_compare_to_type_header";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to indent breaks compare to cases
	 *     - option id:         "org.eclipse.jdt.core.formatter.indent_breaks_compare_to_cases"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_INDENT_BREAKS_COMPARE_TO_CASES = UIPlugin.PLUGIN_ID + ".formatter.indent_breaks_compare_to_cases";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to indent empty lines
	 *     - option id:         "org.eclipse.jdt.core.formatter.indent_empty_lines"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.2
	 */
	public static final String FORMATTER_INDENT_EMPTY_LINES = UIPlugin.PLUGIN_ID + ".formatter.indent_empty_lines"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to indent statements inside a block
	 *     - option id:         "org.eclipse.jdt.core.formatter.indent_statements_compare_to_block"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_INDENT_STATEMENTS_COMPARE_TO_BLOCK = UIPlugin.PLUGIN_ID + ".formatter.indent_statements_compare_to_block"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to indent statements inside the body of a method or a constructor
	 *     - option id:         "org.eclipse.jdt.core.formatter.indent_statements_compare_to_body"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_INDENT_STATEMENTS_COMPARE_TO_BODY = UIPlugin.PLUGIN_ID + ".formatter.indent_statements_compare_to_body"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to indent switch statements compare to cases
	 *     - option id:         "org.eclipse.jdt.core.formatter.indent_switchstatements_compare_to_cases"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_INDENT_SWITCHSTATEMENTS_COMPARE_TO_CASES = UIPlugin.PLUGIN_ID + ".formatter.indent_switchstatements_compare_to_cases";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to indent switch statements compare to switch
	 *     - option id:         "org.eclipse.jdt.core.formatter.indent_switchstatements_compare_to_switch"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_INDENT_SWITCHSTATEMENTS_COMPARE_TO_SWITCH = UIPlugin.PLUGIN_ID + ".formatter.indent_switchstatements_compare_to_switch";	//$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to specify the equivalent number of spaces that represents one indentation
	 *     - option id:         "org.eclipse.jdt.core.formatter.indentation.size"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "4"
	 * </pre>
	 * <p>This option is used only if the tab char is set to MIXED.
	 * </p>
	 * @see #FORMATTER_TAB_CHAR
	 * @since 3.1
	 */
	public static final String FORMATTER_INDENTATION_SIZE = UIPlugin.PLUGIN_ID + ".formatter.indentation.size"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line after an annotation
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_after_annotation"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 * @deprecated
	 * All new options must be enabled to activate old strategy
	 * {@link #FORMATTER_INSERT_NEW_LINE_AFTER_ANNOTATION_ON_MEMBER}
	 * {@link #FORMATTER_INSERT_NEW_LINE_AFTER_ANNOTATION_ON_LOCAL_VARIABLE}
	 * {@link #FORMATTER_INSERT_NEW_LINE_AFTER_ANNOTATION_ON_PARAMETER}
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_AFTER_ANNOTATION = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_after_annotation";//$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line after an annotation on a member (package, class, method, field declaration)
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_after_annotation_on_member"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.4
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_AFTER_ANNOTATION_ON_MEMBER = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_after_annotation_on_member";//$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line after an annotation on a parameter
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_after_annotation_on_parameter"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.4
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_AFTER_ANNOTATION_ON_PARAMETER = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_after_annotation_on_parameter";//$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line after an annotation on a local variable
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_after_annotation_on_local_variable"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.4
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_AFTER_ANNOTATION_ON_LOCAL_VARIABLE = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_after_annotation_on_local_variable";//$NON-NLS-1$


	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line after the opening brace in an array initializer
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_after_opening_brace_in_array_initializer"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_AFTER_OPENING_BRACE_IN_ARRAY_INITIALIZER = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_after_opening_brace_in_array_initializer";//$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line at the end of the current file if missing
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_at_end_of_file_if_missing"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_AT_END_OF_FILE_IF_MISSING = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_at_end_of_file_if_missing";//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line before the catch keyword in try statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_before_catch_in_try_statement"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_BEFORE_CATCH_IN_TRY_STATEMENT = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_before_catch_in_try_statement";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line before the closing brace in an array initializer
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_before_closing_brace_in_array_initializer"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_BEFORE_CLOSING_BRACE_IN_ARRAY_INITIALIZER = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_before_closing_brace_in_array_initializer";//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line before the else keyword in if statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_before_else_in_if_statement"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_BEFORE_ELSE_IN_IF_STATEMENT = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_before_else_in_if_statement";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line before the finally keyword in try statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_before_finally_in_try_statement"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_BEFORE_FINALLY_IN_TRY_STATEMENT = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_before_finally_in_try_statement";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line before while in do statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_before_while_in_do_statement"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_BEFORE_WHILE_IN_DO_STATEMENT = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_before_while_in_do_statement";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line in an empty annotation declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_in_empty_annotation_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.2
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_IN_EMPTY_ANNOTATION_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_in_empty_annotation_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line in an empty anonymous type declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_in_empty_anonymous_type_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_IN_EMPTY_ANONYMOUS_TYPE_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_in_empty_anonymous_type_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line in an empty block
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_in_empty_block"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_IN_EMPTY_BLOCK = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_in_empty_block";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line in an empty enum constant
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_in_empty_enum_constant"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_IN_EMPTY_ENUM_CONSTANT = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_in_empty_enum_constant";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line in an empty enum declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_in_empty_enum_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_IN_EMPTY_ENUM_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_in_empty_enum_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line in an empty method body
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_in_empty_method_body"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_IN_EMPTY_METHOD_BODY = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_in_empty_method_body";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a new line in an empty type declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_new_line_in_empty_type_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_NEW_LINE_IN_EMPTY_TYPE_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_new_line_in_empty_type_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after and in wilcard
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_and_in_type_parameter"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_AND_IN_TYPE_PARAMETER = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_and_in_type_parameter"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after an assignment operator
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_assignment_operator"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_ASSIGNMENT_OPERATOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_assignment_operator"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after at in annotation
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_at_in_annotation"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_AT_IN_ANNOTATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_at_in_annotation"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after at in annotation type declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_at_in_annotation_type_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_AT_IN_ANNOTATION_TYPE_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_at_in_annotation_type_declaration"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after a binary operator
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_binary_operator"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_BINARY_OPERATOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_binary_operator"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the closing angle bracket in type arguments
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_closing_angle_bracket_in_type_arguments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_CLOSING_ANGLE_BRACKET_IN_TYPE_ARGUMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_closing_angle_bracket_in_type_arguments"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the closing angle bracket in type parameters
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_closing_angle_bracket_in_type_parameters"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_CLOSING_ANGLE_BRACKET_IN_TYPE_PARAMETERS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_closing_angle_bracket_in_type_parameters"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the closing brace of a block
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_closing_brace_in_block"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_CLOSING_BRACE_IN_BLOCK = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_closing_brace_in_block"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the closing parenthesis of a cast expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_closing_paren_in_cast"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_CLOSING_PAREN_IN_CAST = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_closing_paren_in_cast"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the colon in an assert statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_colon_in_assert"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COLON_IN_ASSERT = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_colon_in_assert"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after colon in a case statement when a opening brace follows the colon
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_colon_in_case"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COLON_IN_CASE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_colon_in_case";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the colon in a conditional expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_colon_in_conditional"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COLON_IN_CONDITIONAL = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_colon_in_conditional"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after colon in a for statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_colon_in_for"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COLON_IN_FOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_colon_in_for";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the colon in a labeled statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_colon_in_labeled_statement"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COLON_IN_LABELED_STATEMENT = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_colon_in_labeled_statement"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in an allocation expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_allocation_expression"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_ALLOCATION_EXPRESSION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_allocation_expression"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in annotation
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_annotation"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_ANNOTATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_annotation"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in an array initializer
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_array_initializer"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_ARRAY_INITIALIZER = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_array_initializer"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in the parameters of a constructor declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_constructor_declaration_parameters"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_CONSTRUCTOR_DECLARATION_PARAMETERS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_constructor_declaration_parameters"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in the exception names in a throws clause of a constructor declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_constructor_declaration_throws"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_CONSTRUCTOR_DECLARATION_THROWS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_constructor_declaration_throws"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in the arguments of an enum constant
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_enum_constant_arguments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_ENUM_CONSTANT_ARGUMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_enum_constant_arguments"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in enum declarations
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_enum_declarations"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_ENUM_DECLARATIONS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_enum_declarations"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in the arguments of an explicit constructor call
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_explicitconstructorcall_arguments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_EXPLICIT_CONSTRUCTOR_CALL_ARGUMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_explicitconstructorcall_arguments"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in the increments of a for statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_for_increments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_FOR_INCREMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_for_increments"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in the initializations of a for statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_for_inits"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_FOR_INITS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_for_inits"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in the parameters of a method declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_method_declaration_parameters"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_METHOD_DECLARATION_PARAMETERS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_method_declaration_parameters"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in the exception names in a throws clause of a method declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_method_declaration_throws"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_METHOD_DECLARATION_THROWS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_method_declaration_throws"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in the arguments of a method invocation
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_method_invocation_arguments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_METHOD_INVOCATION_ARGUMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_method_invocation_arguments"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in multiple field declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_multiple_field_declarations"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_MULTIPLE_FIELD_DECLARATIONS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_multiple_field_declarations"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in multiple local declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_multiple_local_declarations"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_MULTIPLE_LOCAL_DECLARATIONS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_multiple_local_declarations"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in parameterized type reference
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_parameterized_type_reference"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_PARAMETERIZED_TYPE_REFERENCE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_parameterized_type_reference"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in superinterfaces names of a type header
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_superinterfaces"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_SUPERINTERFACES = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_superinterfaces"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in type arguments
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_type_arguments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_TYPE_ARGUMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_type_arguments"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the comma in type parameters
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_type_parameters"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_TYPE_PARAMETERS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_comma_in_type_parameters"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after ellipsis
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_ellipsis"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_ELLIPSIS  = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_ellipsis";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening angle bracket in parameterized type reference
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_angle_bracket_in_parameterized_type_reference"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_ANGLE_BRACKET_IN_PARAMETERIZED_TYPE_REFERENCE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_angle_bracket_in_parameterized_type_reference";//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening angle bracket in type arguments
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_angle_bracket_in_type_arguments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_ANGLE_BRACKET_IN_TYPE_ARGUMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_angle_bracket_in_type_arguments";//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening angle bracket in type parameters
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_angle_bracket_in_type_parameters"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_ANGLE_BRACKET_IN_TYPE_PARAMETERS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_angle_bracket_in_type_parameters";//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening brace in an array initializer
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_brace_in_array_initializer"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_BRACE_IN_ARRAY_INITIALIZER = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_brace_in_array_initializer";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening bracket inside an array allocation expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_bracket_in_array_allocation_expression"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_BRACKET_IN_ARRAY_ALLOCATION_EXPRESSION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_bracket_in_array_allocation_expression";//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening bracket inside an array reference
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_bracket_in_array_reference"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_BRACKET_IN_ARRAY_REFERENCE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_bracket_in_array_reference";//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening parenthesis in annotation
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_annotation"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_ANNOTATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_paren_in_annotation"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening parenthesis in a cast expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_cast"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_CAST = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_paren_in_cast"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening parenthesis in a catch
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_catch"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_CATCH = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_paren_in_catch"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening parenthesis in a constructor declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_constructor_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_CONSTRUCTOR_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_paren_in_constructor_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening parenthesis in enum constant
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_enum_constant"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_ENUM_CONSTANT = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_paren_in_enum_constant"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening parenthesis in a for statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_for"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_FOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_paren_in_for"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening parenthesis in an if statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_if"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_IF = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_paren_in_if"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening parenthesis in a method declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_method_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_METHOD_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_paren_in_method_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening parenthesis in a method invocation
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_method_invocation"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_METHOD_INVOCATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_paren_in_method_invocation"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening parenthesis in a parenthesized expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_parenthesized_expression"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_PARENTHESIZED_EXPRESSION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_paren_in_parenthesized_expression"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening parenthesis in a switch statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_switch"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_SWITCH = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_paren_in_switch"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening parenthesis in a synchronized statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_synchronized"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_SYNCHRONIZED = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_paren_in_synchronized"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after the opening parenthesis in a while statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_while"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_WHILE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_opening_paren_in_while"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after a postfix operator
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_postfix_operator"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_POSTFIX_OPERATOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_postfix_operator"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after a prefix operator
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_prefix_operator"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_PREFIX_OPERATOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_prefix_operator"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after question mark in a conditional expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_question_in_conditional"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_QUESTION_IN_CONDITIONAL = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_question_in_conditional"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after question mark in a wildcard
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_question_in_wildcard"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_QUESTION_IN_WILDCARD = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_question_in_wildcard"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after semicolon in a for statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_semicolon_in_for"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_SEMICOLON_IN_FOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_semicolon_in_for"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space after an unary operator
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_after_unary_operator"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_AFTER_UNARY_OPERATOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_after_unary_operator"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before and in wildcard
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_and_in_type_parameter"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_AND_IN_TYPE_PARAMETER = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_and_in_type_parameter";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before an assignment operator
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_assignment_operator"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_ASSIGNMENT_OPERATOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_assignment_operator";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before at in annotation type declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_at_in_annotation_type_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_AT_IN_ANNOTATION_TYPE_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_at_in_annotation_type_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before an binary operator
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_binary_operator"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_BINARY_OPERATOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_binary_operator";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing angle bracket in parameterized type reference
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_angle_bracket_in_parameterized_type_reference"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_ANGLE_BRACKET_IN_PARAMETERIZED_TYPE_REFERENCE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_angle_bracket_in_parameterized_type_reference";		//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing angle bracket in type arguments
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_angle_bracket_in_type_arguments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_ANGLE_BRACKET_IN_TYPE_ARGUMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_angle_bracket_in_type_arguments";		//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing angle bracket in type parameters
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_angle_bracket_in_type_parameters"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_ANGLE_BRACKET_IN_TYPE_PARAMETERS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_angle_bracket_in_type_parameters";		//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing brace in an array initializer
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_brace_in_array_initializer"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_BRACE_IN_ARRAY_INITIALIZER = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_brace_in_array_initializer";		//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing bracket in an array allocation expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_bracket_in_array_allocation_expression"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_BRACKET_IN_ARRAY_ALLOCATION_EXPRESSION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_bracket_in_array_allocation_expression";//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing bracket in an array reference
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_bracket_in_array_reference"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_BRACKET_IN_ARRAY_REFERENCE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_bracket_in_array_reference";//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing parenthesis in annotation
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_annotation"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_ANNOTATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_paren_in_annotation";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing parenthesis in a cast expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_cast"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_CAST = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_paren_in_cast";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing parenthesis in a catch
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_catch"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_CATCH = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_paren_in_catch";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing parenthesis in a constructor declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_constructor_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_CONSTRUCTOR_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_paren_in_constructor_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing parenthesis in enum constant
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_enum_constant"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_ENUM_CONSTANT = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_paren_in_enum_constant";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing parenthesis in a for statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_for"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_FOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_paren_in_for";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing parenthesis in an if statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_if"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_IF = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_paren_in_if";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing parenthesis in a method declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_method_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_METHOD_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_paren_in_method_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing parenthesis in a method invocation
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_method_invocation"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_METHOD_INVOCATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_paren_in_method_invocation"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing parenthesis in a parenthesized expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_parenthesized_expression"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_PARENTHESIZED_EXPRESSION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_paren_in_parenthesized_expression"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing parenthesis in a switch statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_switch"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_SWITCH = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_paren_in_switch";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing parenthesis in a synchronized statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_synchronized"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_SYNCHRONIZED = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_paren_in_synchronized";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the closing parenthesis in a while statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_while"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_WHILE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_closing_paren_in_while";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before colon in an assert statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_colon_in_assert"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COLON_IN_ASSERT = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_colon_in_assert";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before colon in a case statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_colon_in_case"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COLON_IN_CASE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_colon_in_case";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before colon in a conditional expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_colon_in_conditional"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COLON_IN_CONDITIONAL = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_colon_in_conditional";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before colon in a default statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_colon_in_default"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COLON_IN_DEFAULT = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_colon_in_default";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before colon in a for statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_colon_in_for"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COLON_IN_FOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_colon_in_for";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before colon in a labeled statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_colon_in_labeled_statement"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COLON_IN_LABELED_STATEMENT = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_colon_in_labeled_statement";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in an allocation expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_allocation_expression"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_ALLOCATION_EXPRESSION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_allocation_expression";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in annotation
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_annotation"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_ANNOTATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_annotation";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in an array initializer
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_array_initializer"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_ARRAY_INITIALIZER = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_array_initializer";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in the parameters of a constructor declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_constructor_declaration_parameters"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_CONSTRUCTOR_DECLARATION_PARAMETERS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_constructor_declaration_parameters";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in the exception names of the throws clause of a constructor declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_constructor_declaration_throws"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_CONSTRUCTOR_DECLARATION_THROWS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_constructor_declaration_throws";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in the arguments of enum constant
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_enum_constant_arguments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_ENUM_CONSTANT_ARGUMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_enum_constant_arguments";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in enum declarations
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_enum_declarations"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_ENUM_DECLARATIONS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_enum_declarations";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in the arguments of an explicit constructor call
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_explicitconstructorcall_arguments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_EXPLICIT_CONSTRUCTOR_CALL_ARGUMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_explicitconstructorcall_arguments";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in the increments of a for statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_for_increments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_FOR_INCREMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_for_increments";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in the initializations of a for statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_for_inits"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_FOR_INITS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_for_inits";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in the parameters of a method declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_method_declaration_parameters"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_METHOD_DECLARATION_PARAMETERS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_method_declaration_parameters";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in the exception names of the throws clause of a method declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_method_declaration_throws"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_METHOD_DECLARATION_THROWS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_method_declaration_throws";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in the arguments of a method invocation
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_method_invocation_arguments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_METHOD_INVOCATION_ARGUMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_method_invocation_arguments";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in a multiple field declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_multiple_field_declarations"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_MULTIPLE_FIELD_DECLARATIONS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_multiple_field_declarations";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in a multiple local declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_multiple_local_declarations"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_MULTIPLE_LOCAL_DECLARATIONS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_multiple_local_declarations";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in parameterized type reference
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_parameterized_type_reference"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_PARAMETERIZED_TYPE_REFERENCE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_parameterized_type_reference";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in the superinterfaces names in a type header
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_superinterfaces"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_SUPERINTERFACES = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_superinterfaces";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in type arguments
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_type_arguments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_TYPE_ARGUMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_type_arguments";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before comma in type parameters
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_type_parameters"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_TYPE_PARAMETERS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_comma_in_type_parameters";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before ellipsis
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_ellipsis"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_ELLIPSIS  = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_ellipsis";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening angle bracket in parameterized type reference
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_angle_bracket_in_parameterized_type_reference"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_ANGLE_BRACKET_IN_PARAMETERIZED_TYPE_REFERENCE  = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_angle_bracket_in_parameterized_type_reference";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening angle bracket in type arguments
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_angle_bracket_in_type_arguments"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_ANGLE_BRACKET_IN_TYPE_ARGUMENTS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_angle_bracket_in_type_arguments";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening angle bracket in type parameters
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_angle_bracket_in_type_parameters"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_ANGLE_BRACKET_IN_TYPE_PARAMETERS = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_angle_bracket_in_type_parameters";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening brace in an annotation type declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_annotation_type_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_ANNOTATION_TYPE_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_brace_in_annotation_type_declaration"; 	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening brace in an anonymous type declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_anonymous_type_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_ANONYMOUS_TYPE_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_brace_in_anonymous_type_declaration"; 	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening brace in an array initializer
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_array_initializer"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_ARRAY_INITIALIZER = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_brace_in_array_initializer"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening brace in a block
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_block"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_BLOCK = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_brace_in_block";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening brace in a constructor declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_constructor_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_CONSTRUCTOR_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_brace_in_constructor_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening brace in an enum constant
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_enum_constant"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_ENUM_CONSTANT = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_brace_in_enum_constant";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening brace in an enum declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_enum_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_ENUM_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_brace_in_enum_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening brace in a method declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_method_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_METHOD_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_brace_in_method_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening brace in a switch statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_switch"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_SWITCH = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_brace_in_switch";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening brace in a type declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_type_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_TYPE_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_brace_in_type_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening bracket in an array allocation expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_bracket_in_array_allocation_expression"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACKET_IN_ARRAY_ALLOCATION_EXPRESSION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_bracket_in_array_allocation_expression";//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening bracket in an array reference
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_bracket_in_array_reference"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACKET_IN_ARRAY_REFERENCE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_bracket_in_array_reference";//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening bracket in an array type reference
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_bracket_in_array_type_reference"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACKET_IN_ARRAY_TYPE_REFERENCE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_bracket_in_array_type_reference";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening parenthesis in annotation
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_annotation"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_ANNOTATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_paren_in_annotation";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening parenthesis in annotation type member declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_annotation_type_member_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_ANNOTATION_TYPE_MEMBER_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_paren_in_annotation_type_member_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening parenthesis in a catch
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_catch"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_CATCH = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_paren_in_catch";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening parenthesis in a constructor declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_constructor_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_CONSTRUCTOR_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_paren_in_constructor_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening parenthesis in enum constant
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_enum_constant"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_ENUM_CONSTANT = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_paren_in_enum_constant";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening parenthesis in a for statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_for"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_FOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_paren_in_for";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening parenthesis in an if statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_if"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_IF = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_paren_in_if";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening parenthesis in a method declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_method_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_METHOD_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_paren_in_method_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening parenthesis in a method invocation
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_method_invocation"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_METHOD_INVOCATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_paren_in_method_invocation";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening parenthesis in a parenthesized expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_parenthesized_expression"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_PARENTHESIZED_EXPRESSION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_paren_in_parenthesized_expression"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening parenthesis in a switch statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_switch"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_SWITCH = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_paren_in_switch";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening parenthesis in a synchronized statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_synchronized"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_SYNCHRONIZED = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_paren_in_synchronized";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before the opening parenthesis in a while statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_while"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_WHILE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_opening_paren_in_while";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before parenthesized expression in return statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_parenthesized_expression_in_return"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 *
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.2
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_PARENTHESIZED_EXPRESSION_IN_RETURN  = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_parenthesized_expression_in_return";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before parenthesized expression in throw statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_parenthesized_expression_in_throw"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 *
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.3
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_PARENTHESIZED_EXPRESSION_IN_THROW  = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_parenthesized_expression_in_throw";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before a postfix operator
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_postfix_operator"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_POSTFIX_OPERATOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_postfix_operator";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before a prefix operator
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_prefix_operator"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_PREFIX_OPERATOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_prefix_operator";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before question mark in a conditional expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_question_in_conditional"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_QUESTION_IN_CONDITIONAL = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_question_in_conditional";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before question mark in a wildcard
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_question_in_wildcard"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_QUESTION_IN_WILDCARD = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_question_in_wildcard"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before semicolon
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_semicolon"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_SEMICOLON = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_semicolon";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before semicolon in for statement
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_semicolon_in_for"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_SEMICOLON_IN_FOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_semicolon_in_for";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space before unary operator
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_before_unary_operator"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BEFORE_UNARY_OPERATOR = UIPlugin.PLUGIN_ID + ".formatter.insert_space_before_unary_operator";	//$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to insert a space between brackets in an array type reference
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_between_brackets_in_array_type_reference"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BETWEEN_BRACKETS_IN_ARRAY_TYPE_REFERENCE = UIPlugin.PLUGIN_ID + ".formatter.insert_space_between_brackets_in_array_type_reference";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space between empty braces in an array initializer
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_between_empty_braces_in_array_initializer"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_BRACES_IN_ARRAY_INITIALIZER = UIPlugin.PLUGIN_ID + ".formatter.insert_space_between_empty_braces_in_array_initializer";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space between empty brackets in an array allocation expression
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_between_empty_brackets_in_array_allocation_expression"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_BRACKETS_IN_ARRAY_ALLOCATION_EXPRESSION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_between_empty_brackets_in_array_allocation_expression";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space between empty parenthesis in an annotation type member declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_between_empty_parens_in_annotation_type_member_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_PARENS_IN_ANNOTATION_TYPE_MEMBER_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_between_empty_parens_in_annotation_type_member_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space between empty parenthesis in a constructor declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_between_empty_parens_in_constructor_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_PARENS_IN_CONSTRUCTOR_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_between_empty_parens_in_constructor_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space between empty parenthesis in enum constant
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_between_empty_parens_in_enum_constant"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.1
	 */
	public static final String FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_PARENS_IN_ENUM_CONSTANT = UIPlugin.PLUGIN_ID + ".formatter.insert_space_between_empty_parens_in_enum_constant";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space between empty parenthesis in a method declaration
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_between_empty_parens_in_method_declaration"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_PARENS_IN_METHOD_DECLARATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_between_empty_parens_in_method_declaration";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to insert a space between empty parenthesis in a method invocation
	 *     - option id:         "org.eclipse.jdt.core.formatter.insert_space_between_empty_parens_in_method_invocation"
	 *     - possible values:   { INSERT, DO_NOT_INSERT }
	 *     - default:           DO_NOT_INSERT
	 * </pre>
	 * @see JavaCore#INSERT
	 * @see JavaCore#DO_NOT_INSERT
	 * @since 3.0
	 */
	public static final String FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_PARENS_IN_METHOD_INVOCATION = UIPlugin.PLUGIN_ID + ".formatter.insert_space_between_empty_parens_in_method_invocation";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to keep else statement on the same line
	 *     - option id:         "org.eclipse.jdt.core.formatter.keep_else_statement_on_same_line"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_KEEP_ELSE_STATEMENT_ON_SAME_LINE = UIPlugin.PLUGIN_ID + ".formatter.keep_else_statement_on_same_line"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to keep empty array initializer one one line
	 *     - option id:         "org.eclipse.jdt.core.formatter.keep_empty_array_initializer_on_one_line"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_KEEP_EMPTY_ARRAY_INITIALIZER_ON_ONE_LINE = UIPlugin.PLUGIN_ID + ".formatter.keep_empty_array_initializer_on_one_line"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to keep guardian clause on one line
	 *     - option id:         "org.eclipse.jdt.core.formatter.format_guardian_clause_on_one_line"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_KEEP_GUARDIAN_CLAUSE_ON_ONE_LINE = UIPlugin.PLUGIN_ID + ".formatter.format_guardian_clause_on_one_line";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to keep simple if statement on the one line
	 *     - option id:         "org.eclipse.jdt.core.formatter.keep_imple_if_on_one_line"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_KEEP_SIMPLE_IF_ON_ONE_LINE = UIPlugin.PLUGIN_ID + ".formatter.keep_imple_if_on_one_line"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to keep then statement on the same line
	 *     - option id:         "org.eclipse.jdt.core.formatter.keep_then_statement_on_same_line"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_KEEP_THEN_STATEMENT_ON_SAME_LINE = UIPlugin.PLUGIN_ID + ".formatter.keep_then_statement_on_same_line";//$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to specify the length of the page. Beyond this length, the formatter will try to split the code
	 *     - option id:         "org.eclipse.jdt.core.formatter.lineSplit"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "80"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_LINE_SPLIT = UIPlugin.PLUGIN_ID + ".formatter.lineSplit"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to indent block comments that start on the first column
	 *     - option id:         "org.eclipse.jdt.core.formatter.formatter.never_indent_block_comments_on_first_column"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * Note that this option is ignored if the formatter is created with the mode {@link ToolFactory#M_FORMAT_NEW}.
	 * @see #TRUE
	 * @see #FALSE
	 * @see ToolFactory#createCodeFormatter(Map, int)
	 * @since 3.3
	 */
	public static final String FORMATTER_NEVER_INDENT_BLOCK_COMMENTS_ON_FIRST_COLUMN = UIPlugin.PLUGIN_ID + ".formatter.never_indent_block_comments_on_first_column"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to indent line comments that start on the first column
	 *     - option id:         "org.eclipse.jdt.core.formatter.formatter.never_indent_line_comments_on_first_column"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * Note that this option is ignored if the formatter is created with the mode {@link ToolFactory#M_FORMAT_NEW}.
	 * @see #TRUE
	 * @see #FALSE
	 * @see ToolFactory#createCodeFormatter(Map, int)
	 * @since 3.3
	 */
	public static final String FORMATTER_NEVER_INDENT_LINE_COMMENTS_ON_FIRST_COLUMN = UIPlugin.PLUGIN_ID + ".formatter.never_indent_line_comments_on_first_column"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to specify the number of empty lines to preserve
	 *     - option id:         "org.eclipse.jdt.core.formatter.number_of_empty_lines_to_preserve"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "0"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_NUMBER_OF_EMPTY_LINES_TO_PRESERVE = UIPlugin.PLUGIN_ID + ".formatter.number_of_empty_lines_to_preserve";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to specify whether or not empty statement should be on a new line
	 *     - option id:         "org.eclipse.jdt.core.formatter.put_empty_statement_on_new_line"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.0
	 */
	public static final String FORMATTER_PUT_EMPTY_STATEMENT_ON_NEW_LINE = UIPlugin.PLUGIN_ID + ".formatter.put_empty_statement_on_new_line";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to specify the tabulation size
	 *     - option id:         "org.eclipse.jdt.core.formatter.tabulation.char"
	 *     - possible values:   { TAB, SPACE, MIXED }
	 *     - default:           TAB
	 * </pre>
	 * More values may be added in the future.
	 *
	 * @see JavaCore#TAB
	 * @see JavaCore#SPACE
	 * @see #MIXED
	 * @since 3.0
	 */
	public static final String FORMATTER_TAB_CHAR = UIPlugin.PLUGIN_ID + ".formatter.tabulation.char"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to specify the equivalent number of spaces that represents one tabulation
	 *     - option id:         "org.eclipse.jdt.core.formatter.tabulation.size"
	 *     - possible values:   "&lt;n&gt;", where n is zero or a positive integer
	 *     - default:           "4"
	 * </pre>
	 * @since 3.0
	 */
	public static final String FORMATTER_TAB_SIZE = UIPlugin.PLUGIN_ID + ".formatter.tabulation.size"; //$NON-NLS-1$

	/**
	 * <pre>
	 * FORMATTER / Option to use tabulations for indentation and spaces for line wrapping
	 *     - option id:         "org.eclipse.jdt.core.formatter.use_tabs_only_for_leading_indentations"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           FALSE
	 * </pre>
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.1
	 */
	public static final String FORMATTER_USE_TABS_ONLY_FOR_LEADING_INDENTATIONS = UIPlugin.PLUGIN_ID + ".formatter.use_tabs_only_for_leading_indentations"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Option to wrap before the binary operator
	 *     - option id:         "org.eclipse.jdt.core.formatter.wrap_before_binary_operator"
	 *     - possible values:   { TRUE, FALSE }
	 *     - default:           TRUE
	 * </pre>
	 * This option is used only if the option {@link #FORMATTER_ALIGNMENT_FOR_BINARY_EXPRESSION} is set.
	 * @see #TRUE
	 * @see #FALSE
	 * @since 3.3
	 */
	public static final String FORMATTER_WRAP_BEFORE_BINARY_OPERATOR = UIPlugin.PLUGIN_ID + ".formatter.wrap_before_binary_operator"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / The wrapping is done by indenting by one compare to the current indentation.
	 * </pre>
	 * @since 3.0
	 */
	public static final int INDENT_BY_ONE= 2;

	/**
	 * <pre>
	 * FORMATTER / The wrapping is done by using the current indentation.
	 * </pre>
	 * @since 3.0
	 */
	public static final int INDENT_DEFAULT= 0;
	/**
	 * <pre>
	 * FORMATTER / The wrapping is done by indenting on column under the splitting location.
	 * </pre>
	 * @since 3.0
	 */
	public static final int INDENT_ON_COLUMN = 1;

	/**
	 * <pre>
	 * FORMATTER / Possible value for the option FORMATTER_TAB_CHAR
	 * </pre>
	 * @since 3.1
	 * @see JavaCore#TAB
	 * @see JavaCore#SPACE
	 * @see #FORMATTER_TAB_CHAR
	 */
	public static final String MIXED = "mixed"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Value to set a brace location at the start of the next line with
	 *             the right indentation.
	 * </pre>
	 * @see #FORMATTER_BRACE_POSITION_FOR_ANONYMOUS_TYPE_DECLARATION
	 * @see #FORMATTER_BRACE_POSITION_FOR_ARRAY_INITIALIZER
	 * @see #FORMATTER_BRACE_POSITION_FOR_BLOCK
	 * @see #FORMATTER_BRACE_POSITION_FOR_CONSTRUCTOR_DECLARATION
 	 * @see #FORMATTER_BRACE_POSITION_FOR_METHOD_DECLARATION
 	 * @see #FORMATTER_BRACE_POSITION_FOR_SWITCH
	 * @see #FORMATTER_BRACE_POSITION_FOR_TYPE_DECLARATION
	 * @since 3.0
	 */
	public static final String NEXT_LINE = "next_line"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Value to set a brace location at the start of the next line if a wrapping
	 *             occured.
	 * </pre>
	 * @see #FORMATTER_BRACE_POSITION_FOR_ANONYMOUS_TYPE_DECLARATION
	 * @see #FORMATTER_BRACE_POSITION_FOR_ARRAY_INITIALIZER
	 * @see #FORMATTER_BRACE_POSITION_FOR_BLOCK
	 * @see #FORMATTER_BRACE_POSITION_FOR_CONSTRUCTOR_DECLARATION
 	 * @see #FORMATTER_BRACE_POSITION_FOR_METHOD_DECLARATION
 	 * @see #FORMATTER_BRACE_POSITION_FOR_SWITCH
	 * @see #FORMATTER_BRACE_POSITION_FOR_TYPE_DECLARATION
	 * @since 3.0
	 */
    public static final String NEXT_LINE_ON_WRAP = "next_line_on_wrap"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Value to set a brace location at the start of the next line with
	 *             an extra indentation.
	 * </pre>
	 * @see #FORMATTER_BRACE_POSITION_FOR_ANONYMOUS_TYPE_DECLARATION
	 * @see #FORMATTER_BRACE_POSITION_FOR_ARRAY_INITIALIZER
	 * @see #FORMATTER_BRACE_POSITION_FOR_BLOCK
	 * @see #FORMATTER_BRACE_POSITION_FOR_CONSTRUCTOR_DECLARATION
 	 * @see #FORMATTER_BRACE_POSITION_FOR_METHOD_DECLARATION
 	 * @see #FORMATTER_BRACE_POSITION_FOR_SWITCH
	 * @see #FORMATTER_BRACE_POSITION_FOR_TYPE_DECLARATION
	 * @since 3.0
	 */
	public static final String NEXT_LINE_SHIFTED = "next_line_shifted";	//$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / Value to set an option to true.
	 * </pre>
	 * @since 3.0
	 */
	public static final String TRUE = "true"; //$NON-NLS-1$
	/**
	 * <pre>
	 * FORMATTER / The wrapping is done using as few lines as possible.
	 * </pre>
	 * @since 3.0
	 */
	public static final int WRAP_COMPACT= 1;
	/**
	 * <pre>
	 * FORMATTER / The wrapping is done putting the first element on a new
	 *             line and then wrapping next elements using as few lines as possible.
	 * </pre>
	 * @since 3.0
	 */
	public static final int WRAP_COMPACT_FIRST_BREAK= 2;
	/**
	 * <pre>
	 * FORMATTER / The wrapping is done by putting each element on its own line
	 *             except the first element.
	 * </pre>
	 * @since 3.0
	 */
	public static final int WRAP_NEXT_PER_LINE= 5;
	/**
	 * <pre>
	 * FORMATTER / The wrapping is done by putting each element on its own line.
	 *             All elements are indented by one except the first element.
	 * </pre>
	 * @since 3.0
	 */
	public static final int WRAP_NEXT_SHIFTED= 4;

	/**
	 * <pre>
	 * FORMATTER / Value to disable alignment.
	 * </pre>
	 * @since 3.0
	 */
	public static final int WRAP_NO_SPLIT= 0;
	/**
	 * <pre>
	 * FORMATTER / The wrapping is done by putting each element on its own line.
	 * </pre>
	 * @since 3.0
	 */
	public static final int WRAP_ONE_PER_LINE= 3;

	/*
	 * Private constants. Not in javadoc
	 */
	private static final IllegalArgumentException WRONG_ARGUMENT = new IllegalArgumentException();

	/**
	 * Returns the formatter settings that most closely approximate
	 * the default formatter settings of Eclipse version 2.1.
	 *
	 * @return the Eclipse 2.1 settings
	 * @since 3.0
	 */
	public static Map getEclipse21Settings() {
		return DefaultCodeFormatterOptions.getDefaultSettings().getMap();
	}

	/**
	 * Returns the default Eclipse formatter settings
	 *
	 * @return the Eclipse default settings
	 * @since 3.1
	 */
	public static Map getEclipseDefaultSettings() {
		return DefaultCodeFormatterOptions.getEclipseDefaultSettings().getMap();
	}

	/**
	 * <p>Return the indentation style of the given alignment value.
	 * The given alignment value should be created using the <code>createAlignmentValue(boolean, int, int)</code>
	 * API.
	 * </p>
	 *
	 * @param value the given alignment value
	 * @return the indentation style of the given alignment value
	 * @see #createAlignmentValue(boolean, int, int)
	 * @exception IllegalArgumentException if the given alignment value is null, or if it
	 * doesn't have a valid format.
	 */
	public static int getIndentStyle(String value) {
		if (value == null) {
			throw WRONG_ARGUMENT;
		}
		return INDENT_DEFAULT;
	}

	/**
	 * Returns the settings according to the Java conventions.
	 *
	 * @return the settings according to the Java conventions
	 * @since 3.0
	 */
	public static Map getJavaConventionsSettings() {
		return DefaultCodeFormatterOptions.getJavaConventionsSettings().getMap();
	}
}
