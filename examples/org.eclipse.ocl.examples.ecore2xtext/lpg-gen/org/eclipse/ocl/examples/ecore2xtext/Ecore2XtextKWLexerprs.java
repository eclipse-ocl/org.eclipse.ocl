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

    public final static int NUM_STATES = 212;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 56;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 278;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 0;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 31;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 58;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 32;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 38;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 57;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 246;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 247;
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
            8,10,11,19,7,11,11,9,11,5,
            8,17,3,10,4,8,5,7,14,12,
            6,9,4,10,10,5,7,8,3,5,
            3
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
            1,1,1,34,15,24,21,26,32,41,
            55,60,43,59,61,64,11,70,48,63,
            72,51,67,76,78,79,80,81,86,83,
            87,92,90,29,95,97,98,99,100,101,
            105,104,18,112,19,106,107,117,120,126,
            123,122,129,131,134,136,137,139,138,144,
            140,146,148,150,153,154,155,156,157,158,
            160,169,168,173,159,175,176,177,179,186,
            178,188,189,190,192,191,195,197,199,202,
            204,205,209,211,207,213,215,218,220,221,
            222,224,225,226,229,232,233,238,234,243,
            244,246,247,248,249,251,254,255,256,266,
            263,269,273,259,271,275,276,277,281,283,
            287,284,289,290,291,293,294,297,295,302,
            301,306,307,308,309,315,317,311,319,320,
            321,323,326,329,327,332,333,331,335,336,
            340,338,342,346,354,356,343,357,360,358,
            359,366,362,372,368,376,374,378,382,379,
            385,384,388,386,392,398,400,401,402,404,
            389,408,409,412,414,415,417,418,421,423,
            428,430,432,391,433,434,435,439,440,442,
            443,445,447,449,452,247,247
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,8,9,
            0,11,12,13,0,5,16,0,0,19,
            0,1,2,0,6,0,26,4,0,9,
            5,0,22,16,24,10,18,6,15,29,
            0,1,0,33,2,35,4,0,17,9,
            0,23,38,3,0,1,9,7,0,0,
            0,1,0,0,36,6,0,9,5,0,
            1,0,25,7,3,0,14,0,0,0,
            0,6,0,4,7,0,0,2,10,0,
            10,0,6,11,0,4,0,0,0,0,
            0,12,4,0,0,0,0,0,2,15,
            5,0,13,10,17,11,0,17,7,0,
            4,0,0,27,5,0,4,2,0,1,
            0,1,11,0,1,0,0,0,0,0,
            5,3,6,0,1,0,9,0,1,0,
            1,6,0,0,0,0,0,0,0,0,
            8,2,9,5,10,10,10,0,0,2,
            13,3,0,34,0,0,0,0,0,4,
            8,3,8,6,8,0,1,0,0,0,
            0,0,4,2,0,8,0,7,0,1,
            6,0,13,0,0,2,0,6,0,1,
            0,1,0,1,0,9,12,0,4,0,
            0,0,2,0,0,0,30,3,0,6,
            11,0,0,0,9,3,19,0,1,8,
            7,20,0,0,1,0,0,0,0,21,
            0,3,5,0,0,0,6,4,0,4,
            2,19,0,18,10,0,1,21,0,7,
            0,3,0,1,0,0,0,7,2,5,
            0,1,0,0,9,3,0,1,0,0,
            0,3,0,0,0,12,0,5,9,3,
            0,0,8,13,3,0,0,0,0,2,
            0,5,12,8,0,1,0,24,0,0,
            0,13,0,5,14,0,0,11,0,1,
            0,0,0,3,0,0,11,0,16,0,
            8,0,0,23,2,0,20,28,17,8,
            5,14,18,0,15,0,0,0,0,0,
            7,0,6,8,5,0,1,0,10,2,
            9,0,37,0,3,0,1,0,0,22,
            7,0,1,0,0,0,1,0,0,12,
            0,0,14,9,7,12,5,0,1,0,
            0,0,1,0,16,5,7,0,0,2,
            7,0,4,0,0,4,0,0,1,6,
            0,31,0,3,8,11,4,0,1,0,
            1,0,0,0,0,4,4,3,0,0,
            2,0,0,1,0,1,0,8,0,0,
            2,0,6,0,0,0,15,0,7,0,
            0,0,0,0,0,32,0,0,0,0,
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
            247,46,49,45,39,42,40,43,38,41,
            247,37,47,48,247,68,35,247,247,36,
            247,53,54,247,101,247,44,50,247,52,
            56,247,73,99,69,55,102,58,51,72,
            247,60,247,71,64,70,63,247,57,59,
            247,91,246,276,247,61,75,78,247,247,
            247,66,247,247,90,62,247,65,67,247,
            74,247,76,79,278,247,77,247,247,247,
            247,80,247,83,81,247,247,85,82,247,
            84,247,87,86,247,88,247,247,247,247,
            247,89,94,247,247,247,247,247,104,92,
            103,247,95,98,260,97,247,96,100,247,
            105,247,247,93,106,247,108,107,247,110,
            247,111,109,247,270,247,247,247,247,247,
            112,114,113,247,116,247,115,247,262,247,
            119,118,247,247,247,247,247,247,247,247,
            120,126,121,130,122,123,124,247,247,128,
            125,127,247,117,247,247,247,247,247,277,
            129,133,131,134,132,247,273,247,247,247,
            247,247,136,138,247,135,247,139,247,141,
            140,247,137,247,247,143,247,142,247,257,
            247,145,247,147,247,146,144,247,148,247,
            247,247,151,247,247,247,264,154,247,153,
            150,247,247,247,155,158,149,247,268,157,
            159,152,247,247,161,247,247,247,247,156,
            247,165,164,247,247,247,166,167,247,169,
            174,160,247,162,168,247,170,163,247,171,
            247,172,247,173,247,247,247,175,177,274,
            247,178,247,247,176,179,247,180,247,247,
            247,181,247,247,247,265,247,184,182,186,
            247,247,187,183,188,247,247,247,247,191,
            247,190,252,189,247,275,247,185,247,247,
            247,192,247,195,194,247,247,193,247,199,
            247,247,247,202,247,247,198,247,263,247,
            201,247,247,197,209,247,258,196,200,248,
            207,206,203,247,205,247,247,247,247,247,
            208,247,210,269,211,247,255,247,213,216,
            214,247,204,247,215,247,217,247,247,212,
            218,247,271,247,247,247,222,247,247,272,
            247,247,219,220,221,261,223,247,224,247,
            247,247,249,247,228,226,225,247,247,229,
            227,247,256,247,247,254,247,247,267,253,
            247,236,247,231,250,230,232,247,233,247,
            234,247,247,247,247,235,266,238,247,247,
            239,247,247,242,247,259,247,240,247,247,
            244,247,243,247,247,247,241,247,251,247,
            247,247,247,247,247,237
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
