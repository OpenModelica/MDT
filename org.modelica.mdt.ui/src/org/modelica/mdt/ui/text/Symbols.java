/*******************************************************************************
 * Copyright (c) 2006 MDT Team, PELAB
 *******************************************************************************/
package org.modelica.mdt.ui.text;

/**
 * Symbols for the heuristic java scanner.
 *
 * @since 3.0
 */
public interface Symbols
{
	int TokenEOF= -1;

	int TokenALGORITHM 		= 1;
	int TokenAND 			= 2;
	int TokenANNOTATION 	= 3;
	int TokenASSERT			= 4;
	int TokenBLOCK			= 5;
	int TokenBREAK			= 6;
	int TokenCLASS 			= 7;
	int TokenCONNECT 		= 8;
	int TokenCONNECTOR		= 9;

	int TokenCONSTANT   	= 10;
	int TokenDER 			= 11;
	int TokenDISCRETE		= 12;
	int TokenEACH			= 13;
	int TokenELSE			= 14;
	int TokenELSEIF			= 15;
	int TokenELSEWHEN		= 16;
	int TokenENCAPSULATED	= 17;
	int TokenEND			= 18;
	int TokenENUMERATION	= 19;
	int TokenEQUATION		= 20;

	int TokenEXTENDS		= 21;
	int TokenEXTERNAL		= 22;
	int TokenFALSE			= 23;
	int TokenFINAL			= 24;
	int TokenFLOW			= 25;
	int TokenFOR			= 26;
	int TokenFUNCTION		= 27;
	int TokenIF				= 28;
	int TokenIMPORT			= 29;
	int TokenIN				= 30;

	int TokenINITIAL		= 31;
	int TokenINNER			= 32;
	int TokenINPUT			= 33;
	int TokenLOOP			= 34;
	int TokenMODEL			= 35;
	int TokenNOT			= 36;
	int TokenOR				= 37;
	int TokenOUTER			= 38;
	int TokenOUTPUT			= 39;
	int TokenOVERLOAD		= 40;

	int TokenPACKAGE		= 41;
	int TokenPARAMETER		= 42;
	int TokenPARTIAL		= 43;
	int TokenPROTECTED		= 44;
	int TokenPUBLIC			= 45;
	int TokenRECORD			= 46;
	int TokenREDECLARE		= 47;
	int TokenREPLACEABLE	= 48;
	int TokenRETURN			= 49;
	int TokenTERMINATE		= 50;

	int TokenTHEN			= 51;
	int TokenTIME			= 52;
	int TokenTRUE			= 53;
	int TokenTYPE			= 54;
	int TokenWHEN			= 55;
	int TokenWHILE			= 56;
	int TokenWITHIN			= 57;
	int TokenEXPANDABLE		= 69;
	int TokenOPERATOR       = 70;
	int TokenCONSTRAINEDBY  = 71;


	/* The following are Meta-Modelica Keywords */
	int TokenAS				= 158;
	int TokenCASE			= 159;
	int TokenEQUALITY		= 160;

	int TokenFAILURE		= 161;
	int TokenLIST			= 162;
	int TokenLOCAL			= 163;
	int TokenMATCH			= 164;
	int TokenMATCHCONTINUE	= 165;
	int TokenTUPLE			= 166;
	int TokenUNIONTYPE		= 167;
	int TokenSUBTYPEOF      = 168;
	int TokenGUARD          = 169;

	int TokenLBRACE			= 1001;
	int TokenRBRACE			= 1002;
	int TokenLBRACKET		= 1003;
	int TokenRBRACKET		= 1004;
	int TokenLPAREN			= 1005;
	int TokenRPAREN			= 1006;
	int TokenSEMICOLON		= 1007;
	int TokenOTHER			= 1008;
	int TokenCOLON			= 1009;
	int TokenCOMMA			= 1011;
	int TokenEQUAL			= 1012;
	int TokenLESSTHAN		= 1013;
	int TokenGREATERTHAN	= 1014;

	int TokenSTRING 		= 1100;
	int TokenIDENT			= 2000;

}
