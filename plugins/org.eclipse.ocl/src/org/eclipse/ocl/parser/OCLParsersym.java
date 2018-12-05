/**
* Essential OCL Grammar
* <copyright>
*
* Copyright (c) 2005, 2018 IBM Corporation and others.
* All rights reserved.   This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v2.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v20.html
*
* Contributors:
*   IBM - Initial API and implementation
*   E.D.Willink - Elimination of some shift-reduce conflicts
*   E.D.Willink - Remove unnecessary warning suppression
*   E.D.Willink - Bugs 184048, 225493, 243976, 259818, 282882, 287993, 288040, 292112, 295166
*   Borland - Bug 242880
*   Adolfo Sanchez-Barbudo Herrera (Open Canarias):
*        - 242153: LPG v 2.0.17 adoption.
*        - 299396: Introducing new LPG templates
*        - 300534: Removing the use of deprecated macros.
*******************************************************************************/

package org.eclipse.ocl.parser;

/**
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
@SuppressWarnings("nls")
public interface OCLParsersym {
	/**
	 * Token kind codes.
	 * 
	 * @noreference These constants are not intended to be referenced by clients.
	 * @since 3.2
	 */
    public final static int
      TK_QUOTED_IDENTIFIER = 6,
      TK_INTEGER_LITERAL = 43,
      TK_REAL_LITERAL = 44,
      TK_STRING_LITERAL = 37,
      TK_PLUS = 11,
      TK_MINUS = 4,
      TK_MULTIPLY = 5,
      TK_DIVIDE = 13,
      TK_GREATER = 18,
      TK_LESS = 19,
      TK_EQUAL = 9,
      TK_GREATER_EQUAL = 20,
      TK_LESS_EQUAL = 21,
      TK_NOT_EQUAL = 38,
      TK_LPAREN = 1,
      TK_RPAREN = 3,
      TK_LBRACE = 61,
      TK_RBRACE = 62,
      TK_LBRACKET = 57,
      TK_RBRACKET = 68,
      TK_ARROW = 14,
      TK_BAR = 40,
      TK_COMMA = 10,
      TK_COLON = 2,
      TK_COLONCOLON = 12,
      TK_SEMICOLON = 69,
      TK_DOT = 15,
      TK_DOTDOT = 72,
      TK_AT = 39,
      TK_CARET = 16,
      TK_CARETCARET = 17,
      TK_QUESTIONMARK = 70,
      TK_self = 45,
      TK_if = 46,
      TK_then = 73,
      TK_else = 74,
      TK_endif = 75,
      TK_and = 41,
      TK_or = 47,
      TK_xor = 54,
      TK_not = 42,
      TK_implies = 58,
      TK_let = 48,
      TK_in = 76,
      TK_true = 49,
      TK_false = 50,
      TK_null = 51,
      TK_invalid = 52,
      TK_Set = 22,
      TK_Bag = 23,
      TK_Sequence = 24,
      TK_Collection = 25,
      TK_OrderedSet = 26,
      TK_String = 27,
      TK_Integer = 28,
      TK_UnlimitedNatural = 29,
      TK_Real = 30,
      TK_Boolean = 31,
      TK_Tuple = 32,
      TK_OclAny = 33,
      TK_OclVoid = 34,
      TK_OclInvalid = 35,
      TK_inv = 55,
      TK_pre = 59,
      TK_post = 63,
      TK_context = 60,
      TK_package = 64,
      TK_endpackage = 77,
      TK_def = 53,
      TK_body = 65,
      TK_derive = 66,
      TK_init = 67,
      TK_static = 56,
      TK_import = 7,
      TK_OclMessage = 36,
      TK_EOF_TOKEN = 71,
      TK_IDENTIFIER = 8,
      TK_SINGLE_LINE_COMMENT = 78,
      TK_MULTI_LINE_COMMENT = 79,
      TK_ERROR_TOKEN = 80;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "LPAREN",
                 "COLON",
                 "RPAREN",
                 "MINUS",
                 "MULTIPLY",
                 "QUOTED_IDENTIFIER",
                 "import",
                 "IDENTIFIER",
                 "EQUAL",
                 "COMMA",
                 "PLUS",
                 "COLONCOLON",
                 "DIVIDE",
                 "ARROW",
                 "DOT",
                 "CARET",
                 "CARETCARET",
                 "GREATER",
                 "LESS",
                 "GREATER_EQUAL",
                 "LESS_EQUAL",
                 "Set",
                 "Bag",
                 "Sequence",
                 "Collection",
                 "OrderedSet",
                 "String",
                 "Integer",
                 "UnlimitedNatural",
                 "Real",
                 "Boolean",
                 "Tuple",
                 "OclAny",
                 "OclVoid",
                 "OclInvalid",
                 "OclMessage",
                 "STRING_LITERAL",
                 "NOT_EQUAL",
                 "AT",
                 "BAR",
                 "and",
                 "not",
                 "INTEGER_LITERAL",
                 "REAL_LITERAL",
                 "self",
                 "if",
                 "or",
                 "let",
                 "true",
                 "false",
                 "null",
                 "invalid",
                 "def",
                 "xor",
                 "inv",
                 "static",
                 "LBRACKET",
                 "implies",
                 "pre",
                 "context",
                 "LBRACE",
                 "RBRACE",
                 "post",
                 "package",
                 "body",
                 "derive",
                 "init",
                 "RBRACKET",
                 "SEMICOLON",
                 "QUESTIONMARK",
                 "EOF_TOKEN",
                 "DOTDOT",
                 "then",
                 "else",
                 "endif",
                 "in",
                 "endpackage",
                 "SINGLE_LINE_COMMENT",
                 "MULTI_LINE_COMMENT",
                 "ERROR_TOKEN"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
