/**
* Essential OCL Keyword Lexer
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
*   E.D.Willink - Lexer and Parser refactoring to support extensibility and flexible error handling
*   E.D.Willink - Bug 285633, 292112
*   Adolfo Sanchez-Barbudo Herrera (Open Canarias) - LPG v 2.0.17 adoption (242153)
*   Adolfo Sanchez-Barbudo Herrera (Open Canarias) - Introducing new LPG templates (299396) 
*******************************************************************************/

package org.eclipse.ocl.parser.backtracking;

public class OCLBacktrackingKWLexerprs implements lpg.runtime.ParseTable, OCLBacktrackingKWLexersym {
    public final static int ERROR_SYMBOL = 0;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 0;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 0;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 0;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 161;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 56;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 251;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 43;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 58;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 44;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 36;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 57;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 207;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 208;
    public final int getErrorAction() { return ERROR_ACTION; }

    public final static boolean BACKTRACK = false;
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int getStartSymbol() { return lhs(0); }
    public final boolean isValidForParser() { return OCLBacktrackingKWLexersym.isValidForParser; }


    public interface IsNullable {
        public final static byte isNullable[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            2,1
        };
    };
    public final static byte prosthesesIndex[] = ProsthesesIndex.prosthesesIndex;
    public final int prosthesesIndex(int index) { return prosthesesIndex[index]; }

    public interface IsKeyword {
        public final static byte isKeyword[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            4,2,4,4,5,3,2,3,3,7,
            3,2,4,5,3,3,8,10,10,6,
            7,16,4,7,5,6,7,10,4,7,
            3,3,4,4,7,7,10,3,6,4,
            10,6,6
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,46,39,79,81,82,
            38,69,90,61,92,9,86,47,52,94,
            95,49,99,100,102,25,40,13,57,59,
            104,101,103,105,111,114,115,118,121,123,
            117,128,127,131,132,62,133,136,142,138,
            137,146,147,148,149,150,68,152,153,158,
            37,161,154,162,165,169,170,175,176,15,
            182,178,184,185,173,188,190,70,193,195,
            197,198,200,71,203,205,207,208,210,211,
            213,215,216,219,221,220,222,232,234,236,
            237,239,242,243,245,244,248,249,250,253,
            257,258,259,262,261,265,263,266,268,270,
            275,276,279,280,226,284,282,288,293,295,
            296,290,299,297,300,302,307,308,310,311,
            313,314,317,323,316,322,329,331,332,333,
            334,336,319,339,342,343,346,347,348,356,
            354,349,359,360,361,362,208,208
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,0,9,
            10,11,0,13,0,15,8,5,10,19,
            0,21,22,23,0,13,2,27,28,5,
            18,31,32,33,34,21,0,0,0,0,
            26,4,6,29,7,8,0,8,0,35,
            4,0,1,7,3,7,0,1,0,3,
            0,0,14,24,3,5,8,0,0,0,
            0,13,2,6,36,6,0,7,0,1,
            0,0,14,16,15,0,25,7,7,0,
            1,0,7,0,0,1,5,4,0,0,
            0,0,0,0,0,7,5,8,5,9,
            0,1,10,0,0,11,0,0,4,2,
            0,0,0,3,2,9,0,0,15,2,
            0,0,0,7,2,0,0,0,3,8,
            3,0,12,2,8,0,0,0,0,0,
            1,0,0,0,9,9,4,0,11,2,
            0,0,14,3,0,0,15,3,0,0,
            2,2,0,20,0,0,1,0,17,2,
            6,0,1,0,0,2,14,0,0,0,
            6,2,0,1,0,1,0,0,11,0,
            3,5,0,4,0,3,0,0,1,0,
            0,1,0,4,0,0,1,13,0,0,
            0,0,16,5,5,0,12,7,3,8,
            18,0,1,0,1,0,0,1,0,1,
            5,0,0,0,0,4,2,0,0,0,
            1,8,0,6,6,13,0,0,0,1,
            0,0,0,6,0,0,1,0,12,0,
            6,19,11,4,0,0,16,10,0,0,
            5,0,20,0,1,6,12,0,10,0,
            3,10,0,1,0,0,0,3,0,0,
            11,0,4,8,3,9,0,0,9,0,
            0,5,0,0,1,0,0,10,0,9,
            4,0,0,11,2,7,17,6,0,1,
            0,0,0,0,4,0,1,22,0,6,
            9,0,0,1,12,0,0,0,0,3,
            12,3,5,0,9,0,1,4,0,0,
            0,0,0,2,4,0,0,8,0,0,
            0,30,14,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            208,65,60,66,64,61,67,63,208,47,
            48,68,208,59,208,50,80,96,79,62,
            208,54,58,49,208,210,93,57,56,92,
            97,55,52,51,53,133,208,208,208,208,
            135,72,126,132,73,74,208,94,208,134,
            83,208,85,82,84,89,208,99,208,98,
            208,208,88,95,223,77,100,12,208,208,
            208,246,146,121,207,143,208,145,208,69,
            208,208,75,122,142,208,114,70,71,208,
            76,208,81,208,208,87,78,86,208,208,
            208,208,208,208,208,90,91,215,101,102,
            208,240,103,208,208,104,208,208,106,107,
            208,208,208,108,109,110,208,208,105,111,
            208,208,208,112,115,208,208,208,219,113,
            217,208,224,116,216,208,208,208,208,208,
            120,208,208,208,214,117,124,208,118,125,
            208,208,119,127,208,208,123,241,208,208,
            129,231,208,242,208,208,131,208,128,137,
            130,208,136,208,208,138,140,208,208,208,
            139,237,208,212,208,221,208,208,141,31,
            248,211,208,144,208,147,208,208,149,208,
            208,233,208,150,208,208,153,209,208,208,
            208,208,148,154,156,208,152,155,251,157,
            151,208,158,208,159,208,208,161,208,222,
            160,208,208,208,208,162,163,208,208,208,
            247,164,208,165,166,213,208,208,208,170,
            208,208,208,169,208,208,174,208,168,208,
            173,167,171,176,208,208,172,175,208,208,
            177,208,234,208,180,179,228,208,178,208,
            243,250,208,244,208,208,208,181,208,208,
            182,208,183,229,185,235,208,208,184,208,
            208,232,208,208,188,208,208,186,208,238,
            189,208,208,218,190,198,187,192,208,225,
            208,208,208,208,193,208,197,191,208,196,
            194,208,208,249,195,208,208,208,208,227,
            199,202,226,208,236,208,245,201,208,208,
            208,208,208,230,205,208,208,204,208,208,
            208,200,203
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }
    public final int asb(int index) { return 0; }
    public final int asr(int index) { return 0; }
    public final int nasb(int index) { return 0; }
    public final int nasr(int index) { return 0; }
    public final int terminalIndex(int index) { return 0; }
    public final int nonterminalIndex(int index) { return 0; }
    public final int scopePrefix(int index) { return 0;}
    public final int scopeSuffix(int index) { return 0;}
    public final int scopeLhs(int index) { return 0;}
    public final int scopeLa(int index) { return 0;}
    public final int scopeStateSet(int index) { return 0;}
    public final int scopeRhs(int index) { return 0;}
    public final int scopeState(int index) { return 0;}
    public final int inSymb(int index) { return 0;}
    public final String name(int index) { return null; }
    public final int originalState(int state) { return 0; }
    public final int asi(int state) { return 0; }
    public final int nasi(int state) { return 0; }
    public final int inSymbol(int state) { return 0; }

    /**
     * assert(! goto_default);
     */
    public final int ntAction(int state, int sym) {
        return baseAction[state + sym];
    }

    /**
     * assert(! shift_default);
     */
    public final int tAction(int state, int sym) {
        int i = baseAction[state],
            k = i + sym;
        return termAction[termCheck[k] == sym ? k : i];
    }
    public final int lookAhead(int la_state, int sym) {
        int k = la_state + sym;
        return termAction[termCheck[k] == sym ? k : la_state];
    }
}
