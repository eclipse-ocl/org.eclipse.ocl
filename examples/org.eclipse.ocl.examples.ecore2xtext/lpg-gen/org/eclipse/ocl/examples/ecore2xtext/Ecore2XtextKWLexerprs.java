/**
* Ecore2Xtext Keyword Lexer
* <copyright>
*******************************************************************************/

package org.eclipse.ocl.examples.ecore2xtext;

public class Ecore2XtextKWLexerprs implements lpg.runtime.ParseTable, Ecore2XtextKWLexersym {
    public final static int ERROR_SYMBOL = 0;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 0;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 0;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 0;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 218;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 38;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 290;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 34;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 40;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 35;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 38;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 39;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 255;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 256;
    public final int getErrorAction() { return ERROR_ACTION; }

    public final static boolean BACKTRACK = false;
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int getStartSymbol() { return lhs(0); }
    public final boolean isValidForParser() { return Ecore2XtextKWLexersym.isValidForParser; }


    public interface IsNullable {
        public final static byte isNullable[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0
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
            0,0,0,0,0,0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            8,10,11,19,7,11,12,11,9,11,
            5,8,5,17,3,10,4,8,5,7,
            14,12,6,9,4,4,10,10,5,7,
            8,3,5,3
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
            1,1,1,1,1,1,37,45,27,23,
            21,34,28,58,63,47,64,66,72,74,
            17,78,35,19,11,51,75,80,82,84,
            86,85,54,90,91,93,92,39,97,99,
            100,102,103,106,107,110,112,111,108,53,
            115,124,127,129,131,136,132,116,137,140,
            144,142,146,148,149,150,154,155,156,159,
            161,163,164,165,167,168,170,172,176,179,
            182,186,185,189,187,194,196,199,200,201,
            202,203,207,209,188,210,212,218,221,217,
            223,225,227,230,232,228,233,237,234,238,
            242,240,241,243,248,253,250,255,259,257,
            262,263,265,264,267,266,275,279,277,281,
            286,288,289,291,284,292,297,299,300,302,
            304,306,305,307,309,313,311,316,310,321,
            323,324,325,330,333,327,334,335,336,337,
            340,342,345,346,347,348,350,349,357,353,
            360,356,370,363,375,359,374,362,378,380,
            384,385,390,386,391,394,395,398,399,401,
            403,404,402,410,414,416,417,419,415,422,
            423,427,428,429,431,433,437,442,443,447,
            449,451,436,452,438,453,456,459,444,461,
            464,468,469,470,256,256
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,8,9,
            0,11,12,13,4,15,0,17,0,19,
            0,5,0,1,2,5,0,0,1,3,
            10,9,14,0,0,35,9,21,0,23,
            7,25,16,9,0,29,0,31,2,3,
            0,18,0,0,4,2,6,0,1,7,
            22,0,0,0,11,0,1,15,34,7,
            32,0,9,0,0,2,5,0,1,0,
            6,0,38,0,0,0,7,6,3,0,
            0,0,0,10,10,3,0,7,0,0,
            11,0,0,12,3,0,0,0,6,0,
            0,0,16,6,0,0,1,18,13,5,
            11,10,0,0,18,2,0,17,0,3,
            0,0,2,5,36,0,0,1,3,0,
            1,0,11,0,1,0,5,0,0,0,
            1,4,7,0,0,0,1,9,0,1,
            0,7,0,0,0,3,0,0,8,0,
            0,0,9,2,10,0,10,10,0,4,
            2,0,13,30,0,0,0,0,0,8,
            5,3,8,0,8,0,1,4,0,0,
            0,0,0,3,2,7,0,8,0,0,
            1,0,6,26,13,7,0,0,7,2,
            0,1,0,1,0,1,0,0,12,0,
            1,0,0,0,3,9,0,0,2,0,
            0,0,0,11,7,4,19,0,9,0,
            8,4,0,1,0,6,0,24,0,1,
            20,0,0,0,0,0,0,5,4,3,
            7,15,0,19,0,10,0,3,0,1,
            0,20,6,0,4,0,1,0,0,2,
            0,0,9,2,6,5,0,1,0,0,
            1,0,4,0,0,0,0,4,0,0,
            0,5,0,12,9,0,4,13,8,4,
            0,12,0,0,0,2,0,5,8,0,
            1,23,0,0,0,0,0,13,5,0,
            14,0,1,11,0,0,0,0,0,0,
            11,4,0,17,8,0,0,22,0,0,
            5,0,0,18,15,7,14,8,24,0,
            8,37,16,0,0,6,2,0,5,0,
            1,33,21,0,0,0,1,10,4,0,
            0,2,9,0,0,1,6,0,0,0,
            0,0,0,0,1,12,5,9,6,0,
            1,14,12,0,0,0,0,1,0,6,
            5,0,0,2,6,3,0,0,0,3,
            0,17,0,1,7,0,0,0,8,11,
            4,0,0,0,3,3,0,1,0,1,
            0,0,0,3,3,0,4,2,0,16,
            0,1,27,0,1,28,8,0,0,0,
            2,0,0,0,7,6,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            256,50,53,42,48,45,46,43,41,44,
            256,40,51,52,290,49,256,38,256,39,
            256,73,256,57,58,60,256,256,64,54,
            59,56,82,256,256,47,63,78,256,74,
            62,77,55,80,256,76,256,75,68,67,
            256,61,256,256,288,91,83,256,65,108,
            97,256,256,256,90,256,70,109,81,66,
            96,256,69,256,256,72,71,256,79,256,
            84,256,255,256,256,256,85,86,88,256,
            256,256,256,87,89,94,256,93,256,256,
            92,256,256,95,100,256,256,256,101,256,
            256,256,98,107,256,256,117,271,102,110,
            104,105,256,256,103,111,256,106,256,112,
            256,256,114,113,99,256,256,118,115,256,
            282,256,116,256,281,256,119,256,256,256,
            123,121,120,256,256,256,273,122,256,126,
            256,125,256,256,256,128,256,256,127,256,
            256,256,129,134,130,256,131,132,256,135,
            136,256,133,124,256,256,256,256,256,137,
            138,289,139,256,140,256,285,141,256,256,
            256,256,256,144,146,142,256,143,256,256,
            149,256,147,275,145,148,256,256,150,151,
            256,269,256,267,256,153,256,256,152,256,
            155,256,256,256,156,154,256,256,159,256,
            256,256,256,158,161,162,157,256,163,256,
            165,166,256,279,256,167,256,160,256,169,
            164,256,256,256,256,256,256,172,173,175,
            174,170,256,168,256,176,256,177,256,178,
            256,171,179,256,180,256,181,256,256,182,
            256,256,184,185,183,286,256,186,256,256,
            188,256,187,256,256,256,256,189,256,256,
            256,192,256,276,190,256,194,191,195,196,
            256,261,256,256,256,199,256,198,197,256,
            287,193,256,256,256,256,256,200,203,256,
            202,256,207,201,256,256,256,256,256,256,
            206,210,256,274,209,256,256,205,256,256,
            215,256,256,208,211,218,214,257,268,256,
            280,204,213,256,256,216,217,256,219,256,
            265,212,220,256,256,256,225,221,223,256,
            256,224,222,256,256,283,226,256,256,256,
            256,256,256,256,230,284,231,228,229,256,
            232,227,272,256,256,256,256,258,256,233,
            234,256,256,237,235,266,256,256,256,264,
            256,236,256,278,238,256,256,256,259,239,
            240,256,6,256,241,263,256,242,256,243,
            256,256,256,244,277,256,247,248,256,250,
            256,251,245,256,270,246,249,256,256,256,
            253,256,256,256,252,260
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
