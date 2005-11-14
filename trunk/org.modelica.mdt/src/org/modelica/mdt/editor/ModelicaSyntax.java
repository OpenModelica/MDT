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

package org.modelica.mdt.editor;

/**
 * @author Peter Bunus
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ModelicaSyntax {
	public static final String[] reservedwords = {"algorithm", "and",
			"annotation", "block", "boundary", "Code", "class", "connect",
			"connector", "constant", "discrete", "each", "else", "elseif",
			"elsewhen", "end", "enumeration", "equation", "encapsulated",
			"extends", "external", "false", "final", "flow", "for", "function",
			"if", "import", "in", "initial", "inner", "input", "loop", "model",
			"not", "outer", "overload", "or", "output", "package", "parameter",
			"partial", "protected", "public", "record", "redeclare", "replaceable",
			"results", "start", "then", "true", "type", "unsigned_real", "when", "while", "within"};

		  public static final String[] predicates = {"UNION", "IS",
		      "IS NULL", "NOT NULL", "IS OF", "IN", "BETWEEN",
		      "OVERLAPS", "LIKE", "< >", "=", "<", ">", "<=", ">=",
		      "NOT", "AND", "OR", "+", "-", "%", "|", ":",
		      ".", "[ ]", "::", "SOME", "ANY", "ALL", "EXISTS", "union",
		      "is", "is null", "not null", "is of", "in", "between",
		      "overlaps", "like", "not", "and", "or", "some", "any",
		      "all", "exists"};
		  public static final String[] types = {"Real", "Integer", "Boolean"};
		  public static final String[] constants = {"FALSE", "NULL",
		      "TRUE", "false", "true", "null"};
		  public static final String[] functions = {"ABS", "ABSVAL",
		      "ACOS", "ASCII", "ASIN", "ATAN", "ATAN2", "BIGINT",
		      "BLOB", "CEILING", "CHAR", "CHR", "CLOB", "COALESCE",
		      "CONCAT", "CORRELATION", "COS", "COT", "COUNT",
		      "COUNT_BIG", "COVARIANCE", "DATE", "DAY", "DAYNAME",
		      "DAYOFWEEK", "DAYOFWEEK_ISO", "DAYOFYEAR", "DAYS",
		      "DBCLOB", "DECIMAL", "DEGREES", "DEREF", "DIFFERENCE",
		      "DIGITS", "DLCOMMENT", "DLLINKTYPE", "DLURLCOMPLETE",
		      "DLURLPATH", "DLURLPATHONLY", "DLURLSCHEME",
		      "DLURLSERVER", "DLVALUE", "DOUBLE", "EVENT_MON_STATE",
		      "EXP", "FLOAT", "FLOOR", "GENERATE_UNIQUE", "GRAPHIC",
		      "GROUPING", "HEX", "HOUR", "INSERT", "INTEGER",
		      "JULIAN_DAY", "LCASE", "LCASE", "LEFT", "LENGTH", "LN",
		      "LOCATE", "LOG", "LOG10", "LONG_VARCHAR",
		      "LONG_VARGRAPHIC", "LTRIM", "LTRIM", "MAX", "MICROSECOND",
		      "MIDNIGHT_SECONDS", "MIN", "MINUTE", "MOD", "MONTH",
		      "MONTHNAME", "NODENUMBER", "NULLIF", "PARTITION",
		      "POSSTR", "POWER", "QUARTER", "RADIANS", "RAISE_ERROR",
		      "RAND", "REAL", "REPEAT", "REPLACE", "RIGHT", "ROUND",
		      "RTRIM", "RTRIM", "SECOND", "SIGN", "SIN", "SMALLINT",
		      "SOUNDEX", "SPACE", "SQLCACHE_SNAPSHOT", "SQRT", "STDDEV",
		      "SUBSTR", "SUM", "TABLE_NAME", "TABLE_SCHEMA", "TAN",
		      "TIME", "TIMESTAMP", "TIMESTAMP_ISO", "TIMESTAMPDIFF",
		      "TRANSLATE", "TRUNCATE", "TRUNC", "TYPE_ID", "TYPE_NAME",
		      "TYPE_SCHEMA", "UCASE", "UPPER", "VALUE", "VARCHAR",
		      "VARGRAPHIC", "VARIANCE", "WEEK", "WEEK_ISO", "YEAR",
		      "abs", "absval", "acos", "ascii", "asin", "atan", "atan2",
		      "bigint", "blob", "ceiling", "char", "chr", "clob",
		      "coalesce", "concat", "correlation", "cos", "cot",
		      "count", "count_big", "covariance", "date", "day",
		      "dayname", "dayofweek", "dayofweek_iso", "dayofyear",
		      "days", "dbclob", "decimal", "degrees", "deref",
		      "difference", "digits", "dlcomment", "dllinktype",
		      "dlurlcomplete", "dlurlpath", "dlurlpathonly",
		      "dlurlscheme", "dlurlserver", "dlvalue", "double",
		      "event_mon_state", "exp", "float", "floor",
		      "generate_unique", "graphic", "grouping", "hex", "hour",
		      "insert", "integer", "julian_day", "lcase", "lcase",
		      "left", "length", "ln", "locate", "log", "log10",
		      "long_varchar", "long_vargraphic", "ltrim", "ltrim",
		      "max", "microsecond", "midnight_seconds", "min", "minute",
		      "mod", "month", "monthname", "nodenumber", "nullif",
		      "partition", "posstr", "power", "quarter", "radians",
		      "raise_error", "rand", "real", "repeat", "replace",
		      "right", "round", "rtrim", "rtrim", "second", "sign",
		      "sin", "smallint", "soundex", "space",
		      "sqlcache_snapshot", "sqrt", "stddev", "substr", "sum",
		      "table_name", "table_schema", "tan", "time", "timestamp",
		      "timestamp_iso", "timestampdiff", "translate", "truncate",
		      "trunc", "type_id", "type_name", "type_schema", "ucase",
		      "upper", "value", "varchar", "vargraphic", "variance",
		      "week", "week_iso", "year"};
		  Object[] allWords = {reservedwords, predicates, types,
		      constants, functions};
}
