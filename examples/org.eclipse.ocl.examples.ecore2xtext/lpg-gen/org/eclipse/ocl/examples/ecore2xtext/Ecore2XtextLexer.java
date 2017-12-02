package org.eclipse.ocl.examples.ecore2xtext;

import lpg.runtime.*;

public class Ecore2XtextLexer implements RuleAction
{
    private Ecore2XtextLexerLpgLexStream lexStream;
    
    private static ParseTable prs = new Ecore2XtextLexerprs();
    public ParseTable getParseTable() { return prs; }

    private LexParser lexParser = new LexParser();
    public LexParser getParser() { return lexParser; }

    public int getToken(int i) { return lexParser.getToken(i); }
    public int getRhsFirstTokenIndex(int i) { return lexParser.getFirstToken(i); }
    public int getRhsLastTokenIndex(int i) { return lexParser.getLastToken(i); }

    public int getLeftSpan() { return lexParser.getToken(1); }
    public int getRightSpan() { return lexParser.getLastToken(); }

    public void resetKeywordLexer()
    {
        if (kwLexer == null)
              this.kwLexer = new Ecore2XtextKWLexer(lexStream.getInputChars(), Ecore2XtextParsersym.TK_IDENTIFIER);
        else this.kwLexer.setInputChars(lexStream.getInputChars());
    }

    public void reset(String filename, int tab) throws java.io.IOException
    {
        lexStream = new Ecore2XtextLexerLpgLexStream(filename, tab);
        lexParser.reset((ILexStream) lexStream, prs, (RuleAction) this);
        resetKeywordLexer();
    }

    public void reset(char[] input_chars, String filename)
    {
        reset(input_chars, filename, 1);
    }
    
    public void reset(char[] input_chars, String filename, int tab)
    {
        lexStream = new Ecore2XtextLexerLpgLexStream(input_chars, filename, tab);
        lexParser.reset((ILexStream) lexStream, prs, (RuleAction) this);
        resetKeywordLexer();
    }
    
    public Ecore2XtextLexer(String filename, int tab) throws java.io.IOException 
    {
        reset(filename, tab);
    }

    public Ecore2XtextLexer(char[] input_chars, String filename, int tab)
    {
        reset(input_chars, filename, tab);
    }

    public Ecore2XtextLexer(char[] input_chars, String filename)
    {
        reset(input_chars, filename, 1);
    }

    public Ecore2XtextLexer() {}

    public ILexStream getILexStream() { return lexStream; }

    /**
     * @deprecated replaced by {@link #getILexStream()}
     */
    public ILexStream getLexStream() { return lexStream; }

    private void initializeLexer(IPrsStream prsStream, int start_offset, int end_offset)
    {
        if (lexStream.getInputChars() == null)
            throw new NullPointerException("LexStream was not initialized");
        lexStream.setPrsStream(prsStream);
        prsStream.makeToken(start_offset, end_offset, 0); // Token list must start with a bad token
    }

    private void addEOF(IPrsStream prsStream, int end_offset)
    {
        prsStream.makeToken(end_offset, end_offset, Ecore2XtextParsersym.TK_EOF_TOKEN); // and end with the end of file token
        prsStream.setStreamLength(prsStream.getSize());
    }

    public void lexer(IPrsStream prsStream)
    {
        lexer(null, prsStream);
    }
    
    public void lexer(Monitor monitor, IPrsStream prsStream)
    {
        initializeLexer(prsStream, 0, -1);
        lexParser.parseCharacters(monitor);  // Lex the input characters
        addEOF(prsStream, lexStream.getStreamIndex());
    }

    public void lexer(IPrsStream prsStream, int start_offset, int end_offset)
    {
        lexer(null, prsStream, start_offset, end_offset);
    }
    
    public void lexer(Monitor monitor, IPrsStream prsStream, int start_offset, int end_offset)
    {
        if (start_offset <= 1)
             initializeLexer(prsStream, 0, -1);
        else initializeLexer(prsStream, start_offset - 1, start_offset - 1);

        lexParser.parseCharacters(monitor, start_offset, end_offset);

        addEOF(prsStream, (end_offset >= lexStream.getStreamIndex() ? lexStream.getStreamIndex() : end_offset + 1));
    }

    /**
     * If a parse stream was not passed to this Lexical analyser then we
     * simply report a lexical error. Otherwise, we produce a bad token.
     */
    public void reportLexicalError(int startLoc, int endLoc) {
        IPrsStream prs_stream = lexStream.getPrsStream();
        if (prs_stream == null)
            lexStream.reportLexicalError(startLoc, endLoc);
        else {
            //
            // Remove any token that may have been processed that fall in the
            // range of the lexical error... then add one error token that spans
            // the error range.
            //
            for (int i = prs_stream.getSize() - 1; i > 0; i--) {
                if (prs_stream.getStartOffset(i) >= startLoc)
                     prs_stream.removeLastToken();
                else break;
            }
            prs_stream.makeToken(startLoc, endLoc, 0); // add an error token to the prsStream
        }        
    }

    //
    // The Lexer contains an array of characters as the input stream to be parsed.
    // There are methods to retrieve and classify characters.
    // The lexparser "token" is implemented simply as the index of the next character in the array.
    // The Lexer extends the abstract class LpgLexStream with an implementation of the abstract
    // method getKind.  The template defines the Lexer class and the lexer() method.
    // A driver creates the action class, "Lexer", passing an Option object to the constructor.
    //
    Ecore2XtextKWLexer kwLexer;
    boolean printTokens;
    private final static int ECLIPSE_TAB_VALUE = 4;

    public int [] getKeywordKinds() { return kwLexer.getKeywordKinds(); }

    public Ecore2XtextLexer(String filename) throws java.io.IOException
    {
        this(filename, ECLIPSE_TAB_VALUE);
        this.kwLexer = new Ecore2XtextKWLexer(lexStream.getInputChars(), Ecore2XtextParsersym.TK_IDENTIFIER);
    }

    /**
     * @deprecated function replaced by {@link #reset(char [] content, String filename)}
     */
    public void initialize(char [] content, String filename)
    {
        reset(content, filename);
    }
    
    final void makeToken(int left_token, int right_token, int kind)
    {
        lexStream.makeToken(left_token, right_token, kind);
    }
    
    final void makeToken(int kind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan();
        lexStream.makeToken(startOffset, endOffset, kind);
        if (printTokens) printValue(startOffset, endOffset);
    }

    final void makeComment(int kind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan();
        lexStream.getIPrsStream().makeAdjunct(startOffset, endOffset, kind);
    }

    final void skipToken()
    {
        if (printTokens) printValue(getLeftSpan(), getRightSpan());
    }
    
    final void checkForKeyWord()
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan(),
            kwKind = kwLexer.lexer(startOffset, endOffset);
        lexStream.makeToken(startOffset, endOffset, kwKind);
        if (printTokens) printValue(startOffset, endOffset);
    }
    
    //
    // This flavor of checkForKeyWord is necessary when the default kind
    // (which is returned when the keyword filter doesn't match) is something
    // other than _IDENTIFIER.
    //
    final void checkForKeyWord(int defaultKind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan(),
            kwKind = kwLexer.lexer(startOffset, endOffset);
        if (kwKind == Ecore2XtextParsersym.TK_IDENTIFIER)
            kwKind = defaultKind;
        lexStream.makeToken(startOffset, endOffset, kwKind);
        if (printTokens) printValue(startOffset, endOffset);
    }
    
    final void printValue(int startOffset, int endOffset)
    {
        String s = new String(lexStream.getInputChars(), startOffset, endOffset - startOffset + 1);
        System.out.print(s);
    }

    //
    //
    //
    static class Ecore2XtextLexerLpgLexStream extends LpgLexStream
    {
    public final static int tokenKind[] =
    {
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 000    0x00
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 001    0x01
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 002    0x02
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 003    0x03
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 004    0x04
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 005    0x05
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 006    0x06
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 007    0x07
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 008    0x08
        Ecore2XtextLexersym.Char_HT,              // 009    0x09
        Ecore2XtextLexersym.Char_LF,              // 010    0x0A
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 011    0x0B
        Ecore2XtextLexersym.Char_FF,              // 012    0x0C
        Ecore2XtextLexersym.Char_CR,              // 013    0x0D
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 014    0x0E
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 015    0x0F
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 016    0x10
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 017    0x11
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 018    0x12
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 019    0x13
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 020    0x14
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 021    0x15
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 022    0x16
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 023    0x17
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 024    0x18
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 025    0x19
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 026    0x1A
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 027    0x1B
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 028    0x1C
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 029    0x1D
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 030    0x1E
        Ecore2XtextLexersym.Char_CtlCharNotWS,    // 031    0x1F
        Ecore2XtextLexersym.Char_Space,           // 032    0x20
        Ecore2XtextLexersym.Char_Exclamation,     // 033    0x21
        Ecore2XtextLexersym.Char_DoubleQuote,     // 034    0x22
        Ecore2XtextLexersym.Char_Sharp,           // 035    0x23
        Ecore2XtextLexersym.Char_DollarSign,      // 036    0x24
        Ecore2XtextLexersym.Char_Percent,         // 037    0x25
        Ecore2XtextLexersym.Char_Ampersand,       // 038    0x26
        Ecore2XtextLexersym.Char_SingleQuote,     // 039    0x27
        Ecore2XtextLexersym.Char_LeftParen,       // 040    0x28
        Ecore2XtextLexersym.Char_RightParen,      // 041    0x29
        Ecore2XtextLexersym.Char_Star,            // 042    0x2A
        Ecore2XtextLexersym.Char_Plus,            // 043    0x2B
        Ecore2XtextLexersym.Char_Comma,           // 044    0x2C
        Ecore2XtextLexersym.Char_Minus,           // 045    0x2D
        Ecore2XtextLexersym.Char_Dot,             // 046    0x2E
        Ecore2XtextLexersym.Char_Slash,           // 047    0x2F
        Ecore2XtextLexersym.Char_0,               // 048    0x30
        Ecore2XtextLexersym.Char_1,               // 049    0x31
        Ecore2XtextLexersym.Char_2,               // 050    0x32
        Ecore2XtextLexersym.Char_3,               // 051    0x33
        Ecore2XtextLexersym.Char_4,               // 052    0x34
        Ecore2XtextLexersym.Char_5,               // 053    0x35
        Ecore2XtextLexersym.Char_6,               // 054    0x36
        Ecore2XtextLexersym.Char_7,               // 055    0x37
        Ecore2XtextLexersym.Char_8,               // 056    0x38
        Ecore2XtextLexersym.Char_9,               // 057    0x39
        Ecore2XtextLexersym.Char_Colon,           // 058    0x3A
        Ecore2XtextLexersym.Char_SemiColon,       // 059    0x3B
        Ecore2XtextLexersym.Char_LessThan,        // 060    0x3C
        Ecore2XtextLexersym.Char_Equal,           // 061    0x3D
        Ecore2XtextLexersym.Char_GreaterThan,     // 062    0x3E
        Ecore2XtextLexersym.Char_QuestionMark,    // 063    0x3F
        Ecore2XtextLexersym.Char_AtSign,          // 064    0x40
        Ecore2XtextLexersym.Char_A,               // 065    0x41
        Ecore2XtextLexersym.Char_B,               // 066    0x42
        Ecore2XtextLexersym.Char_C,               // 067    0x43
        Ecore2XtextLexersym.Char_D,               // 068    0x44
        Ecore2XtextLexersym.Char_E,               // 069    0x45
        Ecore2XtextLexersym.Char_F,               // 070    0x46
        Ecore2XtextLexersym.Char_G,               // 071    0x47
        Ecore2XtextLexersym.Char_H,               // 072    0x48
        Ecore2XtextLexersym.Char_I,               // 073    0x49
        Ecore2XtextLexersym.Char_J,               // 074    0x4A
        Ecore2XtextLexersym.Char_K,               // 075    0x4B
        Ecore2XtextLexersym.Char_L,               // 076    0x4C
        Ecore2XtextLexersym.Char_M,               // 077    0x4D
        Ecore2XtextLexersym.Char_N,               // 078    0x4E
        Ecore2XtextLexersym.Char_O,               // 079    0x4F
        Ecore2XtextLexersym.Char_P,               // 080    0x50
        Ecore2XtextLexersym.Char_Q,               // 081    0x51
        Ecore2XtextLexersym.Char_R,               // 082    0x52
        Ecore2XtextLexersym.Char_S,               // 083    0x53
        Ecore2XtextLexersym.Char_T,               // 084    0x54
        Ecore2XtextLexersym.Char_U,               // 085    0x55
        Ecore2XtextLexersym.Char_V,               // 086    0x56
        Ecore2XtextLexersym.Char_W,               // 087    0x57
        Ecore2XtextLexersym.Char_X,               // 088    0x58
        Ecore2XtextLexersym.Char_Y,               // 089    0x59
        Ecore2XtextLexersym.Char_Z,               // 090    0x5A
        Ecore2XtextLexersym.Char_LeftBracket,     // 091    0x5B
        Ecore2XtextLexersym.Char_BackSlash,       // 092    0x5C
        Ecore2XtextLexersym.Char_RightBracket,    // 093    0x5D
        Ecore2XtextLexersym.Char_Caret,           // 094    0x5E
        Ecore2XtextLexersym.Char__,               // 095    0x5F
        Ecore2XtextLexersym.Char_BackQuote,       // 096    0x60
        Ecore2XtextLexersym.Char_a,               // 097    0x61
        Ecore2XtextLexersym.Char_b,               // 098    0x62
        Ecore2XtextLexersym.Char_c,               // 099    0x63
        Ecore2XtextLexersym.Char_d,               // 100    0x64
        Ecore2XtextLexersym.Char_e,               // 101    0x65
        Ecore2XtextLexersym.Char_f,               // 102    0x66
        Ecore2XtextLexersym.Char_g,               // 103    0x67
        Ecore2XtextLexersym.Char_h,               // 104    0x68
        Ecore2XtextLexersym.Char_i,               // 105    0x69
        Ecore2XtextLexersym.Char_j,               // 106    0x6A
        Ecore2XtextLexersym.Char_k,               // 107    0x6B
        Ecore2XtextLexersym.Char_l,               // 108    0x6C
        Ecore2XtextLexersym.Char_m,               // 109    0x6D
        Ecore2XtextLexersym.Char_n,               // 110    0x6E
        Ecore2XtextLexersym.Char_o,               // 111    0x6F
        Ecore2XtextLexersym.Char_p,               // 112    0x70
        Ecore2XtextLexersym.Char_q,               // 113    0x71
        Ecore2XtextLexersym.Char_r,               // 114    0x72
        Ecore2XtextLexersym.Char_s,               // 115    0x73
        Ecore2XtextLexersym.Char_t,               // 116    0x74
        Ecore2XtextLexersym.Char_u,               // 117    0x75
        Ecore2XtextLexersym.Char_v,               // 118    0x76
        Ecore2XtextLexersym.Char_w,               // 119    0x77
        Ecore2XtextLexersym.Char_x,               // 120    0x78
        Ecore2XtextLexersym.Char_y,               // 121    0x79
        Ecore2XtextLexersym.Char_z,               // 122    0x7A
        Ecore2XtextLexersym.Char_LeftBrace,       // 123    0x7B
        Ecore2XtextLexersym.Char_VerticalBar,     // 124    0x7C
        Ecore2XtextLexersym.Char_RightBrace,      // 125    0x7D
        Ecore2XtextLexersym.Char_Tilde,           // 126    0x7E

        Ecore2XtextLexersym.Char_AfterASCII,      // for all chars in range 128..65534
        Ecore2XtextLexersym.Char_EOF              // for '\uffff' or 65535 
    };
            
    public final int getKind(int i)  // Classify character at ith location
    {
        int c = (i >= getStreamLength() ? '\uffff' : getCharValue(i));
        return (c < 128 // ASCII Character
                  ? tokenKind[c]
                  : c == '\uffff'
                       ? Ecore2XtextLexersym.Char_EOF
                       : Ecore2XtextLexersym.Char_AfterASCII);
    }

    public String[] orderedExportedSymbols() { return Ecore2XtextParsersym.orderedTerminalSymbols; }

    public Ecore2XtextLexerLpgLexStream(String filename, int tab) throws java.io.IOException
    {
        super(filename, tab);
    }

    public Ecore2XtextLexerLpgLexStream(char[] input_chars, String filename, int tab)
    {
        super(input_chars, filename, tab);
    }

    public Ecore2XtextLexerLpgLexStream(char[] input_chars, String filename)
    {
        super(input_chars, filename, 1);
    }
    }

    public void ruleAction(int ruleNumber)
    {
        switch(ruleNumber)
        {

            //
            // Rule 1:  Token ::= / >
            //
            case 1: { 
			makeToken(Ecore2XtextParsersym.TK_Slash_GreaterThan);
	              break;
            }
	
            //
            // Rule 2:  Token ::= :
            //
            case 2: { 
			makeToken(Ecore2XtextParsersym.TK_Colon);
	              break;
            }
	
            //
            // Rule 3:  Token ::= <
            //
            case 3: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan);
	              break;
            }
	
            //
            // Rule 4:  Token ::= < /
            //
            case 4: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_Slash);
	              break;
            }
	
            //
            // Rule 5:  Token ::= < / d e t a i l s >
            //
            case 5: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_Slash_100_101_116_97_105_108_115_GreaterThan);
	              break;
            }
	
            //
            // Rule 6:  Token ::= < / e A n n o t a t i o n s >
            //
            case 6: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_Slash_101_65_110_110_111_116_97_116_105_111_110_115_GreaterThan);
	              break;
            }
	
            //
            // Rule 7:  Token ::= < / e C l a s s i f i e r s >
            //
            case 7: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_Slash_101_67_108_97_115_115_105_102_105_101_114_115_GreaterThan);
	              break;
            }
	
            //
            // Rule 8:  Token ::= < / e G e n e r i c T y p e >
            //
            case 8: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_Slash_101_71_101_110_101_114_105_99_84_121_112_101_GreaterThan);
	              break;
            }
	
            //
            // Rule 9:  Token ::= < / e O p e r a t i o n s >
            //
            case 9: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_Slash_101_79_112_101_114_97_116_105_111_110_115_GreaterThan);
	              break;
            }
	
            //
            // Rule 10:  Token ::= < / e P a c k a g e s >
            //
            case 10: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_Slash_101_80_97_99_107_97_103_101_115_GreaterThan);
	              break;
            }
	
            //
            // Rule 11:  Token ::= < / e P a r a m e t e r s >
            //
            case 11: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_Slash_101_80_97_114_97_109_101_116_101_114_115_GreaterThan);
	              break;
            }
	
            //
            // Rule 12:  Token ::= < / e S t r u c t u r a l F e a t u r e s >
            //
            case 12: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_Slash_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_GreaterThan);
	              break;
            }
	
            //
            // Rule 13:  Token ::= < / e T y p e A r g u m e n t s >
            //
            case 13: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_Slash_101_84_121_112_101_65_114_103_117_109_101_110_116_115_GreaterThan);
	              break;
            }
	
            //
            // Rule 14:  Token ::= < / e c o r e : E P a c k a g e >
            //
            case 14: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_Slash_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101_GreaterThan);
	              break;
            }
	
            //
            // Rule 15:  Token ::= < / x m i : X M I >
            //
            case 15: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan);
	              break;
            }
	
            //
            // Rule 16:  Token ::= < ? x m l
            //
            case 16: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_QuestionMark_120_109_108);
	              break;
            }
	
            //
            // Rule 17:  Token ::= < d e t a i l s
            //
            case 17: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_100_101_116_97_105_108_115);
	              break;
            }
	
            //
            // Rule 18:  Token ::= < e A n n o t a t i o n s
            //
            case 18: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_101_65_110_110_111_116_97_116_105_111_110_115);
	              break;
            }
	
            //
            // Rule 19:  Token ::= < e C l a s s i f i e r s
            //
            case 19: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_101_67_108_97_115_115_105_102_105_101_114_115);
	              break;
            }
	
            //
            // Rule 20:  Token ::= < e G e n e r i c T y p e
            //
            case 20: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_101_71_101_110_101_114_105_99_84_121_112_101);
	              break;
            }
	
            //
            // Rule 21:  Token ::= < e O p e r a t i o n s
            //
            case 21: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_101_79_112_101_114_97_116_105_111_110_115);
	              break;
            }
	
            //
            // Rule 22:  Token ::= < e P a c k a g e s
            //
            case 22: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_101_80_97_99_107_97_103_101_115);
	              break;
            }
	
            //
            // Rule 23:  Token ::= < e P a r a m e t e r s
            //
            case 23: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_101_80_97_114_97_109_101_116_101_114_115);
	              break;
            }
	
            //
            // Rule 24:  Token ::= < e S t r u c t u r a l F e a t u r e s
            //
            case 24: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115);
	              break;
            }
	
            //
            // Rule 25:  Token ::= < e T y p e A r g u m e n t s
            //
            case 25: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_101_84_121_112_101_65_114_103_117_109_101_110_116_115);
	              break;
            }
	
            //
            // Rule 26:  Token ::= < e c o r e : E P a c k a g e
            //
            case 26: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101);
	              break;
            }
	
            //
            // Rule 27:  Token ::= < x m i : X M I
            //
            case 27: { 
			makeToken(Ecore2XtextParsersym.TK_LessThan_120_109_105_Colon_88_77_73);
	              break;
            }
	
            //
            // Rule 28:  Token ::= =
            //
            case 28: { 
			makeToken(Ecore2XtextParsersym.TK_Equal);
	              break;
            }
	
            //
            // Rule 29:  Token ::= >
            //
            case 29: { 
			makeToken(Ecore2XtextParsersym.TK_GreaterThan);
	              break;
            }
	
            //
            // Rule 30:  Token ::= ? >
            //
            case 30: { 
			makeToken(Ecore2XtextParsersym.TK_QuestionMark_GreaterThan);
	              break;
            }
	
            //
            // Rule 94:  Token ::= IDENTIFIER
            //
            case 94: { 
			make Token(Ecore2XtextParsersym.TK_IDENTIFIER);check keyword
	              break;
            }
	
            //
            // Rule 104:  Token ::= STRING
            //
            case 104: { 
			makeToken(Ecore2XtextParsersym.TK_STRING);
	              break;
            }
	
            //
            // Rule 181:  Token ::= WS
            //
            case 181: { 
			skipToken();
	              break;
            }
	
    
            default:
                break;
        }
        return;
    }
}

