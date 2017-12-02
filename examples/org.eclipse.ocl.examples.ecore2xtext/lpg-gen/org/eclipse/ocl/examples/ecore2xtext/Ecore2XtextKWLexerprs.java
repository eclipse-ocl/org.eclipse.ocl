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

    public final static int NUM_STATES = 213;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 38;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 281;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 32;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 40;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 33;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 38;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 39;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 248;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 249;
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
            5,8,17,3,10,4,8,5,7,14,
            12,6,9,4,10,10,5,7,8,3,
            5,3
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
            1,1,1,1,35,27,15,22,28,26,
            48,63,29,50,61,67,69,16,75,25,
            42,73,51,79,81,82,44,80,84,88,
            85,91,92,93,40,98,71,99,100,101,
            102,104,108,106,110,60,120,109,121,123,
            127,130,126,131,134,138,136,140,143,142,
            144,148,149,152,156,11,150,154,158,160,
            161,163,162,167,171,172,173,180,176,182,
            187,185,189,190,192,194,195,198,175,199,
            203,206,207,210,212,214,215,217,218,221,
            222,225,227,226,229,228,231,236,234,243,
            241,244,246,242,247,251,253,254,256,258,
            267,263,265,271,273,274,277,276,278,283,
            286,287,279,289,292,294,295,296,298,297,
            299,304,306,308,309,310,317,315,320,322,
            312,321,324,325,328,330,331,333,335,332,
            337,338,342,344,346,355,357,345,358,361,
            359,363,367,360,371,372,376,378,379,381,
            383,384,386,387,388,390,399,396,401,404,
            402,392,410,411,413,415,407,417,419,422,
            424,428,429,432,434,423,435,436,439,440,
            441,444,445,447,451,452,453,249,249
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,8,9,
            0,11,12,13,0,0,16,3,8,19,
            5,0,1,2,0,0,0,0,0,15,
            9,6,5,9,6,35,21,10,23,0,
            25,0,17,0,29,0,31,0,1,0,
            0,2,3,10,4,14,9,7,34,0,
            0,22,0,1,38,6,0,1,0,9,
            0,32,0,5,0,1,4,18,0,0,
            0,0,3,0,0,7,6,0,7,2,
            0,0,0,10,3,11,6,0,0,0,
            0,0,3,0,12,0,36,0,0,0,
            2,0,15,13,11,17,7,10,17,0,
            0,16,0,3,5,0,0,5,2,0,
            0,1,3,0,1,0,11,0,1,0,
            5,0,0,0,1,6,4,0,0,0,
            9,0,1,0,6,0,1,0,9,0,
            0,0,0,10,2,4,0,10,2,10,
            0,0,0,13,0,0,5,30,8,0,
            8,0,3,8,0,4,0,1,0,0,
            6,0,3,0,0,2,8,0,0,1,
            26,7,0,6,13,0,0,2,6,0,
            1,0,1,0,0,1,0,0,12,3,
            0,0,9,2,0,0,0,0,0,4,
            0,11,6,0,1,0,19,9,8,4,
            0,0,0,0,1,0,0,20,24,7,
            0,5,0,0,4,0,3,0,6,19,
            3,20,0,18,0,10,0,1,4,7,
            0,1,0,0,2,0,0,0,0,2,
            7,5,0,1,9,0,0,1,0,4,
            12,0,4,0,0,0,0,0,0,5,
            9,4,4,0,8,0,13,0,0,0,
            2,0,5,8,0,12,0,1,23,0,
            0,0,13,0,0,11,5,0,1,0,
            0,0,0,14,0,11,0,0,4,16,
            8,0,22,0,0,0,2,17,37,18,
            5,8,15,24,0,14,0,0,0,0,
            0,7,0,6,8,5,0,1,9,33,
            0,0,10,2,4,0,1,0,0,21,
            0,1,0,0,7,0,0,0,1,0,
            12,0,9,7,5,0,14,12,0,1,
            0,0,7,0,1,5,0,16,7,0,
            0,2,0,3,0,3,0,11,0,1,
            6,0,0,0,8,4,3,0,0,1,
            3,0,1,0,0,0,3,3,0,0,
            0,2,4,0,0,1,0,1,8,27,
            0,0,0,2,0,0,6,0,15,7,
            0,0,0,28,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            249,47,50,40,46,43,41,44,39,42,
            249,38,48,49,249,249,36,51,121,37,
            69,249,54,55,249,249,249,249,249,52,
            53,59,57,76,63,45,74,56,70,249,
            73,249,58,249,72,249,71,249,61,249,
            249,65,64,83,279,78,60,79,77,249,
            249,92,249,62,248,102,249,67,249,66,
            249,91,249,68,249,75,281,103,249,249,
            249,249,84,249,249,80,81,249,82,86,
            249,249,249,85,89,87,88,249,249,249,
            249,249,95,249,90,249,94,249,249,249,
            105,249,93,96,98,263,101,99,97,249,
            249,100,249,106,104,249,249,107,108,249,
            249,111,109,249,112,249,110,249,273,249,
            113,249,249,249,117,114,115,249,249,249,
            116,249,265,249,119,249,120,249,122,249,
            249,249,249,123,127,128,249,124,129,125,
            249,249,249,126,249,249,131,118,130,249,
            132,249,280,133,249,134,249,276,249,249,
            135,249,137,249,249,139,136,249,249,142,
            267,140,249,141,138,249,249,144,143,249,
            260,249,146,249,249,148,249,249,145,149,
            249,249,147,152,249,249,249,249,249,155,
            249,151,154,249,271,249,150,156,158,159,
            249,249,249,249,162,249,249,157,153,160,
            249,165,249,249,166,249,168,249,167,161,
            170,164,249,163,249,169,249,171,173,172,
            249,174,249,249,175,249,249,249,249,178,
            176,277,249,179,177,249,249,181,249,180,
            268,249,182,249,249,249,249,249,249,185,
            183,187,189,249,188,249,184,249,249,249,
            192,249,191,190,249,254,249,278,186,249,
            249,249,193,249,249,194,196,249,200,249,
            249,249,249,195,249,199,249,249,203,266,
            202,249,198,249,249,249,210,201,197,204,
            208,250,206,261,249,207,249,249,249,249,
            249,209,249,211,272,212,249,258,215,205,
            249,249,214,217,216,249,218,249,249,213,
            249,274,249,249,219,249,249,249,223,249,
            275,249,221,222,224,249,220,264,249,225,
            249,249,226,249,251,227,249,229,228,249,
            249,230,249,259,249,257,249,232,249,270,
            231,249,249,249,252,233,234,6,249,235,
            256,249,236,249,249,249,237,269,249,249,
            249,241,240,249,249,244,249,262,242,238,
            249,249,249,246,249,249,245,249,243,253,
            249,249,249,239
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
