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

package org.eclipse.ocl.parser.backtracking;

/**
 * The backtracking variant of the OCL parser symbol table.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 * 
 * @since 1.3
 */
@SuppressWarnings("nls")
public interface OCLBacktrackingParsersym {
    public final static int
      TK_QUOTED_IDENTIFIER = 3,
      TK_INTEGER_LITERAL = 29,
      TK_REAL_LITERAL = 30,
      TK_STRING_LITERAL = 25,
      TK_PLUS = 38,
      TK_MINUS = 8,
      TK_MULTIPLY = 9,
      TK_DIVIDE = 40,
      TK_GREATER = 45,
      TK_LESS = 46,
      TK_EQUAL = 26,
      TK_GREATER_EQUAL = 47,
      TK_LESS_EQUAL = 48,
      TK_NOT_EQUAL = 49,
      TK_LPAREN = 4,
      TK_RPAREN = 5,
      TK_LBRACE = 65,
      TK_RBRACE = 53,
      TK_LBRACKET = 62,
      TK_RBRACKET = 72,
      TK_ARROW = 41,
      TK_BAR = 51,
      TK_COMMA = 27,
      TK_COLON = 2,
      TK_COLONCOLON = 39,
      TK_SEMICOLON = 73,
      TK_DOT = 42,
      TK_DOTDOT = 75,
      TK_AT = 50,
      TK_CARET = 43,
      TK_CARETCARET = 44,
      TK_QUESTIONMARK = 66,
      TK_self = 31,
      TK_if = 32,
      TK_then = 76,
      TK_else = 77,
      TK_endif = 74,
      TK_and = 52,
      TK_or = 54,
      TK_xor = 57,
      TK_not = 28,
      TK_implies = 63,
      TK_let = 33,
      TK_in = 78,
      TK_true = 34,
      TK_false = 35,
      TK_null = 36,
      TK_invalid = 37,
      TK_Set = 10,
      TK_Bag = 11,
      TK_Sequence = 12,
      TK_Collection = 13,
      TK_OrderedSet = 14,
      TK_String = 15,
      TK_Integer = 16,
      TK_UnlimitedNatural = 17,
      TK_Real = 18,
      TK_Boolean = 19,
      TK_Tuple = 20,
      TK_OclAny = 21,
      TK_OclVoid = 22,
      TK_OclInvalid = 23,
      TK_inv = 58,
      TK_pre = 55,
      TK_post = 59,
      TK_context = 64,
      TK_package = 67,
      TK_endpackage = 68,
      TK_def = 56,
      TK_body = 60,
      TK_derive = 69,
      TK_init = 70,
      TK_static = 61,
      TK_import = 6,
      TK_OclMessage = 24,
      TK_EOF_TOKEN = 71,
      TK_IDENTIFIER = 7,
      TK_SINGLE_LINE_COMMENT = 79,
      TK_MULTI_LINE_COMMENT = 80,
      TK_ERROR_TOKEN = 1;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "ERROR_TOKEN",
                 "COLON",
                 "QUOTED_IDENTIFIER",
                 "LPAREN",
                 "RPAREN",
                 "import",
                 "IDENTIFIER",
                 "MINUS",
                 "MULTIPLY",
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
                 "EQUAL",
                 "COMMA",
                 "not",
                 "INTEGER_LITERAL",
                 "REAL_LITERAL",
                 "self",
                 "if",
                 "let",
                 "true",
                 "false",
                 "null",
                 "invalid",
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
                 "NOT_EQUAL",
                 "AT",
                 "BAR",
                 "and",
                 "RBRACE",
                 "or",
                 "pre",
                 "def",
                 "xor",
                 "inv",
                 "post",
                 "body",
                 "static",
                 "LBRACKET",
                 "implies",
                 "context",
                 "LBRACE",
                 "QUESTIONMARK",
                 "package",
                 "endpackage",
                 "derive",
                 "init",
                 "EOF_TOKEN",
                 "RBRACKET",
                 "SEMICOLON",
                 "endif",
                 "DOTDOT",
                 "then",
                 "else",
                 "in",
                 "SINGLE_LINE_COMMENT",
                 "MULTI_LINE_COMMENT"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
