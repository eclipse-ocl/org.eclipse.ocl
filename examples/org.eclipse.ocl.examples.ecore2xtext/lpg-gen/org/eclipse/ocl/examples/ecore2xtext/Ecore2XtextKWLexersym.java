/**
* Ecore2Xtext Keyword Lexer
* <copyright>
*******************************************************************************/

package org.eclipse.ocl.examples.ecore2xtext;

public interface Ecore2XtextKWLexersym {
    public final static int
      Char_B = 20,
      Char_C = 21,
      Char_E = 25,
      Char_I = 26,
      Char_L = 27,
      Char_N = 28,
      Char_O = 29,
      Char_P = 22,
      Char_R = 30,
      Char_S = 31,
      Char_T = 23,
      Char_U = 32,
      Char_V = 33,
      Char_a = 2,
      Char_b = 14,
      Char_c = 13,
      Char_d = 12,
      Char_e = 1,
      Char_f = 18,
      Char_g = 24,
      Char_h = 34,
      Char_i = 4,
      Char_k = 35,
      Char_l = 7,
      Char_m = 15,
      Char_n = 5,
      Char_o = 9,
      Char_p = 10,
      Char_r = 6,
      Char_s = 3,
      Char_t = 8,
      Char_u = 11,
      Char_v = 19,
      Char_w = 36,
      Char_x = 16,
      Char_y = 17,
      Char_z = 37,
      Char_EOF = 38;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "e",
                 "a",
                 "s",
                 "i",
                 "n",
                 "r",
                 "l",
                 "t",
                 "o",
                 "p",
                 "u",
                 "d",
                 "c",
                 "b",
                 "m",
                 "x",
                 "y",
                 "f",
                 "v",
                 "B",
                 "C",
                 "P",
                 "T",
                 "g",
                 "E",
                 "I",
                 "L",
                 "N",
                 "O",
                 "R",
                 "S",
                 "U",
                 "V",
                 "h",
                 "k",
                 "w",
                 "z",
                 "EOF"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
