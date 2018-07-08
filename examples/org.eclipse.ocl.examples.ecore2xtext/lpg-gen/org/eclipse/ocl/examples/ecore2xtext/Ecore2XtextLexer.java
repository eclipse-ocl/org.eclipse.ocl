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
        IPrsStream prs_stream = lexStream.getIPrsStream();
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

	final void makeIdentifier(int kind)
	{
		checkForKeyWord(kind);
	}

	final void makeComment(int kind)
	{
		int startOffset = getLeftSpan(),
			endOffset = getRightSpan();
		lexStream.getIPrsStream().makeAdjunct(startOffset, endOffset, kind);
	}

	final void skipToken(int kind)
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
			Ecore2XtextLexersym.Char_Default,	// 0 0x0
			Ecore2XtextLexersym.Char_Default,	// 1 0x1
			Ecore2XtextLexersym.Char_Default,	// 2 0x2
			Ecore2XtextLexersym.Char_Default,	// 3 0x3
			Ecore2XtextLexersym.Char_Default,	// 4 0x4
			Ecore2XtextLexersym.Char_Default,	// 5 0x5
			Ecore2XtextLexersym.Char_Default,	// 6 0x6
			Ecore2XtextLexersym.Char_Default,	// 7 0x7
			Ecore2XtextLexersym.Char_Default,	// 8 0x8
			Ecore2XtextLexersym.Char_HTAB,	// 9 0x9
			Ecore2XtextLexersym.Char_LF,	// 10 0xa
			Ecore2XtextLexersym.Char_Default,	// 11 0xb
			Ecore2XtextLexersym.Char_Default,	// 12 0xc
			Ecore2XtextLexersym.Char_CR,	// 13 0xd
			Ecore2XtextLexersym.Char_Default,	// 14 0xe
			Ecore2XtextLexersym.Char_Default,	// 15 0xf
			Ecore2XtextLexersym.Char_Default,	// 16 0x10
			Ecore2XtextLexersym.Char_Default,	// 17 0x11
			Ecore2XtextLexersym.Char_Default,	// 18 0x12
			Ecore2XtextLexersym.Char_Default,	// 19 0x13
			Ecore2XtextLexersym.Char_Default,	// 20 0x14
			Ecore2XtextLexersym.Char_Default,	// 21 0x15
			Ecore2XtextLexersym.Char_Default,	// 22 0x16
			Ecore2XtextLexersym.Char_Default,	// 23 0x17
			Ecore2XtextLexersym.Char_Default,	// 24 0x18
			Ecore2XtextLexersym.Char_Default,	// 25 0x19
			Ecore2XtextLexersym.Char_Default,	// 26 0x1a
			Ecore2XtextLexersym.Char_Default,	// 27 0x1b
			Ecore2XtextLexersym.Char_Default,	// 28 0x1c
			Ecore2XtextLexersym.Char_Default,	// 29 0x1d
			Ecore2XtextLexersym.Char_Default,	// 30 0x1e
			Ecore2XtextLexersym.Char_Default,	// 31 0x1f
			Ecore2XtextLexersym.Char_SPACE,	// 32 0x20
			Ecore2XtextLexersym.Char_PLING,	// 33 0x21
			Ecore2XtextLexersym.Char_DQUOTE,	// 34 0x22
			Ecore2XtextLexersym.Char_HASH,	// 35 0x23
			Ecore2XtextLexersym.Char_DOLLAR,	// 36 0x24
			Ecore2XtextLexersym.Char_PERCENT,	// 37 0x25
			Ecore2XtextLexersym.Char_AND,	// 38 0x26
			Ecore2XtextLexersym.Char_SQUOTE,	// 39 0x27
			Ecore2XtextLexersym.Char_LPAREN,	// 40 0x28
			Ecore2XtextLexersym.Char_RPAREN,	// 41 0x29
			Ecore2XtextLexersym.Char_STAR,	// 42 0x2a
			Ecore2XtextLexersym.Char_PLUS,	// 43 0x2b
			Ecore2XtextLexersym.Char_COMMA,	// 44 0x2c
			Ecore2XtextLexersym.Char_MINUS,	// 45 0x2d
			Ecore2XtextLexersym.Char_DOT,	// 46 0x2e
			Ecore2XtextLexersym.Char_SLASH,	// 47 0x2f
			Ecore2XtextLexersym.Char_0,	// 48 0x30
			Ecore2XtextLexersym.Char_1,	// 49 0x31
			Ecore2XtextLexersym.Char_2,	// 50 0x32
			Ecore2XtextLexersym.Char_3,	// 51 0x33
			Ecore2XtextLexersym.Char_4,	// 52 0x34
			Ecore2XtextLexersym.Char_5,	// 53 0x35
			Ecore2XtextLexersym.Char_6,	// 54 0x36
			Ecore2XtextLexersym.Char_7,	// 55 0x37
			Ecore2XtextLexersym.Char_8,	// 56 0x38
			Ecore2XtextLexersym.Char_9,	// 57 0x39
			Ecore2XtextLexersym.Char_COLON,	// 58 0x3a
			Ecore2XtextLexersym.Char_SEMICOLON,	// 59 0x3b
			Ecore2XtextLexersym.Char_LT,	// 60 0x3c
			Ecore2XtextLexersym.Char_EQ,	// 61 0x3d
			Ecore2XtextLexersym.Char_GT,	// 62 0x3e
			Ecore2XtextLexersym.Char_QUERY,	// 63 0x3f
			Ecore2XtextLexersym.Char_AT,	// 64 0x40
			Ecore2XtextLexersym.Char_A,	// 65 0x41
			Ecore2XtextLexersym.Char_B,	// 66 0x42
			Ecore2XtextLexersym.Char_C,	// 67 0x43
			Ecore2XtextLexersym.Char_D,	// 68 0x44
			Ecore2XtextLexersym.Char_E,	// 69 0x45
			Ecore2XtextLexersym.Char_F,	// 70 0x46
			Ecore2XtextLexersym.Char_G,	// 71 0x47
			Ecore2XtextLexersym.Char_H,	// 72 0x48
			Ecore2XtextLexersym.Char_I,	// 73 0x49
			Ecore2XtextLexersym.Char_J,	// 74 0x4a
			Ecore2XtextLexersym.Char_K,	// 75 0x4b
			Ecore2XtextLexersym.Char_L,	// 76 0x4c
			Ecore2XtextLexersym.Char_M,	// 77 0x4d
			Ecore2XtextLexersym.Char_N,	// 78 0x4e
			Ecore2XtextLexersym.Char_O,	// 79 0x4f
			Ecore2XtextLexersym.Char_P,	// 80 0x50
			Ecore2XtextLexersym.Char_Q,	// 81 0x51
			Ecore2XtextLexersym.Char_R,	// 82 0x52
			Ecore2XtextLexersym.Char_S,	// 83 0x53
			Ecore2XtextLexersym.Char_T,	// 84 0x54
			Ecore2XtextLexersym.Char_U,	// 85 0x55
			Ecore2XtextLexersym.Char_V,	// 86 0x56
			Ecore2XtextLexersym.Char_W,	// 87 0x57
			Ecore2XtextLexersym.Char_X,	// 88 0x58
			Ecore2XtextLexersym.Char_Y,	// 89 0x59
			Ecore2XtextLexersym.Char_Z,	// 90 0x5a
			Ecore2XtextLexersym.Char_LSQUARE,	// 91 0x5b
			Ecore2XtextLexersym.Char_BSLASH,	// 92 0x5c
			Ecore2XtextLexersym.Char_RSQUARE,	// 93 0x5d
			Ecore2XtextLexersym.Char_CARET,	// 94 0x5e
			Ecore2XtextLexersym.Char_USCORE,	// 95 0x5f
			Ecore2XtextLexersym.Char_GRAVE,	// 96 0x60
			Ecore2XtextLexersym.Char_a,	// 97 0x61
			Ecore2XtextLexersym.Char_b,	// 98 0x62
			Ecore2XtextLexersym.Char_c,	// 99 0x63
			Ecore2XtextLexersym.Char_d,	// 100 0x64
			Ecore2XtextLexersym.Char_e,	// 101 0x65
			Ecore2XtextLexersym.Char_f,	// 102 0x66
			Ecore2XtextLexersym.Char_g,	// 103 0x67
			Ecore2XtextLexersym.Char_h,	// 104 0x68
			Ecore2XtextLexersym.Char_i,	// 105 0x69
			Ecore2XtextLexersym.Char_j,	// 106 0x6a
			Ecore2XtextLexersym.Char_k,	// 107 0x6b
			Ecore2XtextLexersym.Char_l,	// 108 0x6c
			Ecore2XtextLexersym.Char_m,	// 109 0x6d
			Ecore2XtextLexersym.Char_n,	// 110 0x6e
			Ecore2XtextLexersym.Char_o,	// 111 0x6f
			Ecore2XtextLexersym.Char_p,	// 112 0x70
			Ecore2XtextLexersym.Char_q,	// 113 0x71
			Ecore2XtextLexersym.Char_r,	// 114 0x72
			Ecore2XtextLexersym.Char_s,	// 115 0x73
			Ecore2XtextLexersym.Char_t,	// 116 0x74
			Ecore2XtextLexersym.Char_u,	// 117 0x75
			Ecore2XtextLexersym.Char_v,	// 118 0x76
			Ecore2XtextLexersym.Char_w,	// 119 0x77
			Ecore2XtextLexersym.Char_x,	// 120 0x78
			Ecore2XtextLexersym.Char_y,	// 121 0x79
			Ecore2XtextLexersym.Char_z,	// 122 0x7a
			Ecore2XtextLexersym.Char_LBRACE,	// 123 0x7b
			Ecore2XtextLexersym.Char_BAR,	// 124 0x7c
			Ecore2XtextLexersym.Char_RBRACE,	// 125 0x7d
			Ecore2XtextLexersym.Char_TILDE,	// 126 0x7e

			Ecore2XtextLexersym.Char_AfterASCII,	  // for all chars in range 128..65534
			Ecore2XtextLexersym.Char_EOF			  // for '\uffff' or 65535
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
		makeToken(Ecore2XtextParsersym.TK_SLASH_GT);
	              break;
            }

            //
            // Rule 2:  Token ::= :
            //
            case 2: {
		makeToken(Ecore2XtextParsersym.TK_COLON);
	              break;
            }

            //
            // Rule 3:  Token ::= <
            //
            case 3: {
		makeToken(Ecore2XtextParsersym.TK_LT);
	              break;
            }

            //
            // Rule 4:  Token ::= < /
            //
            case 4: {
		makeToken(Ecore2XtextParsersym.TK_LT_SLASH);
	              break;
            }

            //
            // Rule 5:  Token ::= < / d e t a i l s >
            //
            case 5: {
		makeToken(Ecore2XtextParsersym.TK_LT_SLASH_d_e_t_a_i_l_s_GT);
	              break;
            }

            //
            // Rule 6:  Token ::= < / e A n n o t a t i o n s >
            //
            case 6: {
		makeToken(Ecore2XtextParsersym.TK_LT_SLASH_e_A_n_n_o_t_a_t_i_o_n_s_GT);
	              break;
            }

            //
            // Rule 7:  Token ::= < / e C l a s s i f i e r s >
            //
            case 7: {
		makeToken(Ecore2XtextParsersym.TK_LT_SLASH_e_C_l_a_s_s_i_f_i_e_r_s_GT);
	              break;
            }

            //
            // Rule 8:  Token ::= < / e G e n e r i c T y p e >
            //
            case 8: {
		makeToken(Ecore2XtextParsersym.TK_LT_SLASH_e_G_e_n_e_r_i_c_T_y_p_e_GT);
	              break;
            }

            //
            // Rule 9:  Token ::= < / e O p e r a t i o n s >
            //
            case 9: {
		makeToken(Ecore2XtextParsersym.TK_LT_SLASH_e_O_p_e_r_a_t_i_o_n_s_GT);
	              break;
            }

            //
            // Rule 10:  Token ::= < / e P a c k a g e s >
            //
            case 10: {
		makeToken(Ecore2XtextParsersym.TK_LT_SLASH_e_P_a_c_k_a_g_e_s_GT);
	              break;
            }

            //
            // Rule 11:  Token ::= < / e P a r a m e t e r s >
            //
            case 11: {
		makeToken(Ecore2XtextParsersym.TK_LT_SLASH_e_P_a_r_a_m_e_t_e_r_s_GT);
	              break;
            }

            //
            // Rule 12:  Token ::= < / e S t r u c t u r a l F e a t u r e s >
            //
            case 12: {
		makeToken(Ecore2XtextParsersym.TK_LT_SLASH_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s_GT);
	              break;
            }

            //
            // Rule 13:  Token ::= < / e T y p e A r g u m e n t s >
            //
            case 13: {
		makeToken(Ecore2XtextParsersym.TK_LT_SLASH_e_T_y_p_e_A_r_g_u_m_e_n_t_s_GT);
	              break;
            }

            //
            // Rule 14:  Token ::= < / e c o r e : E P a c k a g e >
            //
            case 14: {
		makeToken(Ecore2XtextParsersym.TK_LT_SLASH_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e_GT);
	              break;
            }

            //
            // Rule 15:  Token ::= < / x m i : X M I >
            //
            case 15: {
		makeToken(Ecore2XtextParsersym.TK_LT_SLASH_x_m_i_COLON_X_M_I_GT);
	              break;
            }

            //
            // Rule 16:  Token ::= < ? x m l
            //
            case 16: {
		makeToken(Ecore2XtextParsersym.TK_LT_QUERY_x_m_l);
	              break;
            }

            //
            // Rule 17:  Token ::= < d e t a i l s
            //
            case 17: {
		makeToken(Ecore2XtextParsersym.TK_LT_d_e_t_a_i_l_s);
	              break;
            }

            //
            // Rule 18:  Token ::= < e A n n o t a t i o n s
            //
            case 18: {
		makeToken(Ecore2XtextParsersym.TK_LT_e_A_n_n_o_t_a_t_i_o_n_s);
	              break;
            }

            //
            // Rule 19:  Token ::= < e C l a s s i f i e r s
            //
            case 19: {
		makeToken(Ecore2XtextParsersym.TK_LT_e_C_l_a_s_s_i_f_i_e_r_s);
	              break;
            }

            //
            // Rule 20:  Token ::= < e G e n e r i c T y p e
            //
            case 20: {
		makeToken(Ecore2XtextParsersym.TK_LT_e_G_e_n_e_r_i_c_T_y_p_e);
	              break;
            }

            //
            // Rule 21:  Token ::= < e O p e r a t i o n s
            //
            case 21: {
		makeToken(Ecore2XtextParsersym.TK_LT_e_O_p_e_r_a_t_i_o_n_s);
	              break;
            }

            //
            // Rule 22:  Token ::= < e P a r a m e t e r s
            //
            case 22: {
		makeToken(Ecore2XtextParsersym.TK_LT_e_P_a_r_a_m_e_t_e_r_s);
	              break;
            }

            //
            // Rule 23:  Token ::= < e S t r u c t u r a l F e a t u r e s
            //
            case 23: {
		makeToken(Ecore2XtextParsersym.TK_LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s);
	              break;
            }

            //
            // Rule 24:  Token ::= < e S u b p a c k a g e s
            //
            case 24: {
		makeToken(Ecore2XtextParsersym.TK_LT_e_S_u_b_p_a_c_k_a_g_e_s);
	              break;
            }

            //
            // Rule 25:  Token ::= < e T y p e A r g u m e n t s
            //
            case 25: {
		makeToken(Ecore2XtextParsersym.TK_LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s);
	              break;
            }

            //
            // Rule 26:  Token ::= < e c o r e : E P a c k a g e
            //
            case 26: {
		makeToken(Ecore2XtextParsersym.TK_LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e);
	              break;
            }

            //
            // Rule 27:  Token ::= < x m i : X M I
            //
            case 27: {
		makeToken(Ecore2XtextParsersym.TK_LT_x_m_i_COLON_X_M_I);
	              break;
            }

            //
            // Rule 28:  Token ::= =
            //
            case 28: {
		makeToken(Ecore2XtextParsersym.TK_EQ);
	              break;
            }

            //
            // Rule 29:  Token ::= >
            //
            case 29: {
		makeToken(Ecore2XtextParsersym.TK_GT);
	              break;
            }

            //
            // Rule 30:  Token ::= ? >
            //
            case 30: {
		makeToken(Ecore2XtextParsersym.TK_QUERY_GT);
	              break;
            }

            //
            // Rule 188:  Token ::= IDENTIFIER
            //
            case 188: {
					makeIdentifier(Ecore2XtextParsersym.TK_IDENTIFIER);
		              break;
            }

            //
            // Rule 203:  Token ::= STRING
            //
            case 203: {
					makeToken(Ecore2XtextParsersym.TK_STRING);
		              break;
            }

            //
            // Rule 210:  Token ::= WS
            //
            case 210: {
					skipToken(Ecore2XtextParsersym.TK_WS);
		              break;
            }


            default:
                break;
        }
        return;
    }
}

