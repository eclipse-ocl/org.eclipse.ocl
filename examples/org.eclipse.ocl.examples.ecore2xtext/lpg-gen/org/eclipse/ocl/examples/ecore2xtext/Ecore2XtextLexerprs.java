package org.eclipse.ocl.examples.ecore2xtext;

public class Ecore2XtextLexerprs implements lpg.runtime.ParseTable, Ecore2XtextLexersym {
    public final static int ERROR_SYMBOL = 0;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 0;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 0;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 0;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 234;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 99;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 612;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 187;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 13;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 112;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 188;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 38;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 100;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 424;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 425;
    public final int getErrorAction() { return ERROR_ACTION; }

    public final static boolean BACKTRACK = false;
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int getStartSymbol() { return lhs(0); }
    public final boolean isValidForParser() { return Ecore2XtextLexersym.isValidForParser; }


    public interface IsNullable {
        public final static byte isNullable[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            2,1,3,4,5,6,7,8,9,10,
            11,12,13
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
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            2,1,1,2,10,15,15,15,14,12,
            14,22,17,17,10,5,8,13,13,13,
            12,10,12,20,15,15,8,1,1,2,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,2,2,
            2,2,2,3,2,2,2,2,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,2,2,2,2,2,2,2,2,
            2,2,2,2,2,2,2,2,2,2,
            2,2,2,2,2,2,2,2,2,2,
            2,2,2,2,2,2,2,3,1,1,
            1,1,1,2,2,2,2
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
            1,3,3,3,3,3,3,3,3,3,
            3,4,4,4,4,4,4,4,4,4,
            4,4,4,4,4,4,4,4,4,4,
            4,4,4,4,4,4,4,5,6,6,
            6,6,6,6,6,6,6,6,6,6,
            6,6,6,6,6,6,6,6,6,6,
            6,6,6,6,7,8,8,8,8,8,
            8,8,8,9,9,11,11,11,11,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,12,
            13,13,13,13,13,13,13,13,21,190,
            9,7,40,15,38,1,43,32,4,23,
            49,48,60,50,53,63,61,70,66,67,
            76,16,81,77,83,79,84,3,90,88,
            95,92,99,98,102,103,85,104,105,111,
            114,112,117,116,118,119,122,2,127,128,
            130,131,134,135,136,137,140,139,141,8,
            146,150,155,151,160,152,156,161,164,163,
            165,166,171,175,177,169,170,183,185,187,
            188,189,191,192,195,196,197,200,202,204,
            205,211,208,213,214,215,217,224,216,223,
            225,229,230,231,232,237,241,246,235,236,
            248,249,250,252,257,256,258,259,261,266,
            267,268,271,269,274,275,276,278,285,284,
            287,292,293,296,297,299,302,303,304,301,
            306,305,310,312,317,321,314,322,324,325,
            326,333,329,328,337,340,338,342,343,346,
            350,352,345,353,357,359,360,361,364,366,
            367,368,374,373,369,375,382,386,385,379,
            390,393,391,395,396,398,399,402,403,405,
            408,410,412,413,414,415,419,420,421,430,
            433,424,428,436,437,438,441,442,447,450,
            449,452,455,456,458,461,462,459,470,463,
            472,473,475,476,480,478,477,486,489,490,
            495,493,491,425,425
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,0,0,0,1,3,0,0,0,9,
            3,9,11,7,0,0,9,17,18,19,
            0,7,0,23,9,22,26,7,6,29,
            30,0,17,18,19,32,16,0,23,0,
            1,26,0,1,29,30,38,0,0,0,
            13,2,0,22,34,35,36,37,10,0,
            0,22,0,1,5,0,0,15,21,0,
            5,32,12,34,35,0,0,8,0,13,
            0,1,0,0,0,3,3,0,13,0,
            1,0,16,15,0,8,2,0,0,8,
            2,0,0,0,0,21,2,6,5,12,
            0,0,10,0,1,0,0,0,0,1,
            5,0,1,12,8,15,0,0,2,0,
            0,1,3,0,0,0,0,4,0,0,
            0,6,3,3,10,0,1,20,31,0,
            0,0,16,15,0,0,2,8,8,0,
            0,2,0,0,0,0,2,2,0,0,
            0,16,9,3,0,13,0,17,4,28,
            12,5,0,1,0,1,0,0,0,2,
            0,0,1,3,0,0,0,11,4,0,
            31,0,6,0,0,10,3,0,20,5,
            0,1,0,0,0,0,0,5,2,6,
            6,14,0,0,0,24,27,4,0,0,
            0,0,2,2,0,0,0,9,16,3,
            0,17,13,28,4,0,12,0,0,0,
            5,0,1,18,5,0,0,0,0,11,
            0,14,6,5,9,0,0,0,0,3,
            0,1,5,0,0,0,0,0,5,2,
            6,6,14,0,0,2,0,27,4,24,
            33,0,0,1,3,0,0,11,0,4,
            0,0,0,0,0,0,10,6,6,0,
            7,0,1,0,5,11,0,19,18,14,
            0,0,6,0,0,0,5,0,0,9,
            7,3,0,9,2,8,0,0,13,0,
            1,0,0,2,0,0,33,10,3,0,
            1,0,0,11,10,4,0,21,0,0,
            0,1,6,0,6,0,0,0,0,4,
            4,19,0,0,0,12,3,9,0,20,
            8,0,15,2,0,0,8,13,3,0,
            0,7,0,1,0,0,2,0,0,1,
            10,0,0,8,0,4,4,0,1,0,
            21,0,0,0,0,4,4,3,0,0,
            0,12,25,0,20,5,8,0,15,0,
            1,8,0,14,2,0,0,0,1,0,
            0,0,7,7,4,4,0,1,0,0,
            2,0,25,4,0,0,1,0,0,5,
            0,0,0,5,7,14,4,7,7,0,
            1,0,0,2,0,0,0,0,0,0,
            3,7,7,11,5,0,1,11,0,0,
            0,3,0,4,0,1,4,7,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            425,425,425,4,211,238,425,425,425,200,
            270,239,258,455,425,425,271,207,203,201,
            425,426,425,206,225,210,205,454,213,204,
            202,425,232,228,226,212,427,425,231,3,
            195,230,425,208,229,227,424,425,425,425,
            199,217,425,209,193,191,192,453,214,425,
            425,194,425,219,216,425,425,218,215,425,
            222,196,220,198,197,425,425,221,425,223,
            425,233,425,425,425,235,237,425,224,425,
            240,425,234,236,425,241,242,425,425,243,
            244,425,425,425,425,247,249,245,248,441,
            425,425,246,425,251,425,425,425,425,256,
            254,425,257,252,253,250,425,425,259,425,
            425,262,261,425,425,425,425,263,425,425,
            425,265,267,269,264,425,272,260,255,425,
            425,425,266,268,425,425,274,273,275,425,
            425,276,425,425,425,425,282,283,425,425,
            425,278,280,284,425,281,425,279,285,277,
            287,286,425,289,425,290,425,425,425,292,
            425,425,295,294,425,425,425,291,296,425,
            288,425,298,425,425,297,300,425,293,301,
            425,302,425,425,425,425,425,304,307,305,
            306,303,425,425,425,299,452,442,425,425,
            425,425,313,314,425,425,425,311,309,315,
            425,310,312,308,316,425,318,425,425,425,
            317,425,323,319,322,425,425,425,425,321,
            425,320,324,327,325,425,425,425,425,330,
            425,332,331,425,425,425,425,425,334,337,
            335,336,333,425,425,339,425,328,338,329,
            326,425,425,342,341,425,425,340,425,447,
            425,425,425,425,425,425,343,345,346,425,
            440,425,351,425,350,349,425,344,347,348,
            425,425,352,425,425,425,355,425,425,353,
            430,359,425,356,358,360,425,425,357,425,
            362,425,425,364,425,425,354,363,366,425,
            367,425,425,365,369,368,425,361,425,425,
            425,374,371,425,372,425,425,425,425,448,
            446,370,425,425,425,375,377,379,425,373,
            378,425,376,381,425,425,383,380,382,425,
            425,435,425,385,425,425,387,425,425,445,
            386,425,425,388,425,444,443,425,391,425,
            384,425,425,425,425,393,394,396,425,425,
            425,392,389,425,390,399,397,425,395,425,
            400,402,425,398,401,425,425,425,404,425,
            425,425,436,434,405,406,425,451,425,425,
            407,425,403,450,425,425,410,425,425,409,
            425,425,425,411,433,408,413,432,431,425,
            412,425,425,414,425,425,425,425,425,425,
            417,439,438,415,416,425,419,418,425,425,
            425,420,425,449,425,421,422,437
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
