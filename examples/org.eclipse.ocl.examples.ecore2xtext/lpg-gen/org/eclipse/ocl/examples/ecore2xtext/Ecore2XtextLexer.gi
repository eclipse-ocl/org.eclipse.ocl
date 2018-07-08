%options escape=$
%options la=2
%options fp=Ecore2XtextLexer,prefix=Char_
%options single-productions
%options noserialize
%options package=org.eclipse.ocl.examples.ecore2xtext
%options template=../lpg/LexerTemplateF.gi
%options filter=Ecore2XtextKWLexer.gi
%options export_terminals=("Ecore2XtextParsersym.java", "TK_")
%options include_directory="../lpg"

%Headers
	--
	-- Additional methods for the action class not provided in the template
	--
	/.
		//
		// The Lexer contains an array of characters as the input stream to be parsed.
		// There are methods to retrieve and classify characters.
		// The lexparser "token" is implemented simply as the index of the next character in the array.
		// The Lexer extends the abstract class LpgLexStream with an implementation of the abstract
		// method getKind.  The template defines the Lexer class and the lexer() method.
		// A driver creates the action class, "Lexer", passing an Option object to the constructor.
		//
		$kw_lexer_class kwLexer;
		boolean printTokens;
		private final static int ECLIPSE_TAB_VALUE = 4;

		public int [] getKeywordKinds() { return kwLexer.getKeywordKinds(); }

		public $action_type(String filename) throws java.io.IOException
		{
			this(filename, ECLIPSE_TAB_VALUE);
			this.kwLexer = new $kw_lexer_class(lexStream.getInputChars(), $_IDENTIFIER);
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
			if (kwKind == $_IDENTIFIER)
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
		static class $super_stream_class extends LpgLexStream
		{
			public final static int tokenKind[] =
			{
				$sym_type.$prefix$Default$suffix$,	// 0 0x0
				$sym_type.$prefix$Default$suffix$,	// 1 0x1
				$sym_type.$prefix$Default$suffix$,	// 2 0x2
				$sym_type.$prefix$Default$suffix$,	// 3 0x3
				$sym_type.$prefix$Default$suffix$,	// 4 0x4
				$sym_type.$prefix$Default$suffix$,	// 5 0x5
				$sym_type.$prefix$Default$suffix$,	// 6 0x6
				$sym_type.$prefix$Default$suffix$,	// 7 0x7
				$sym_type.$prefix$Default$suffix$,	// 8 0x8
				$sym_type.$prefix$HTAB$suffix$,	// 9 0x9
				$sym_type.$prefix$LF$suffix$,	// 10 0xa
				$sym_type.$prefix$Default$suffix$,	// 11 0xb
				$sym_type.$prefix$Default$suffix$,	// 12 0xc
				$sym_type.$prefix$CR$suffix$,	// 13 0xd
				$sym_type.$prefix$Default$suffix$,	// 14 0xe
				$sym_type.$prefix$Default$suffix$,	// 15 0xf
				$sym_type.$prefix$Default$suffix$,	// 16 0x10
				$sym_type.$prefix$Default$suffix$,	// 17 0x11
				$sym_type.$prefix$Default$suffix$,	// 18 0x12
				$sym_type.$prefix$Default$suffix$,	// 19 0x13
				$sym_type.$prefix$Default$suffix$,	// 20 0x14
				$sym_type.$prefix$Default$suffix$,	// 21 0x15
				$sym_type.$prefix$Default$suffix$,	// 22 0x16
				$sym_type.$prefix$Default$suffix$,	// 23 0x17
				$sym_type.$prefix$Default$suffix$,	// 24 0x18
				$sym_type.$prefix$Default$suffix$,	// 25 0x19
				$sym_type.$prefix$Default$suffix$,	// 26 0x1a
				$sym_type.$prefix$Default$suffix$,	// 27 0x1b
				$sym_type.$prefix$Default$suffix$,	// 28 0x1c
				$sym_type.$prefix$Default$suffix$,	// 29 0x1d
				$sym_type.$prefix$Default$suffix$,	// 30 0x1e
				$sym_type.$prefix$Default$suffix$,	// 31 0x1f
				$sym_type.$prefix$SPACE$suffix$,	// 32 0x20
				$sym_type.$prefix$PLING$suffix$,	// 33 0x21
				$sym_type.$prefix$DQUOTE$suffix$,	// 34 0x22
				$sym_type.$prefix$HASH$suffix$,	// 35 0x23
				$sym_type.$prefix$DOLLAR$suffix$,	// 36 0x24
				$sym_type.$prefix$PERCENT$suffix$,	// 37 0x25
				$sym_type.$prefix$AND$suffix$,	// 38 0x26
				$sym_type.$prefix$SQUOTE$suffix$,	// 39 0x27
				$sym_type.$prefix$LPAREN$suffix$,	// 40 0x28
				$sym_type.$prefix$RPAREN$suffix$,	// 41 0x29
				$sym_type.$prefix$STAR$suffix$,	// 42 0x2a
				$sym_type.$prefix$PLUS$suffix$,	// 43 0x2b
				$sym_type.$prefix$COMMA$suffix$,	// 44 0x2c
				$sym_type.$prefix$MINUS$suffix$,	// 45 0x2d
				$sym_type.$prefix$DOT$suffix$,	// 46 0x2e
				$sym_type.$prefix$SLASH$suffix$,	// 47 0x2f
				$sym_type.$prefix$0$suffix$,	// 48 0x30
				$sym_type.$prefix$1$suffix$,	// 49 0x31
				$sym_type.$prefix$2$suffix$,	// 50 0x32
				$sym_type.$prefix$3$suffix$,	// 51 0x33
				$sym_type.$prefix$4$suffix$,	// 52 0x34
				$sym_type.$prefix$5$suffix$,	// 53 0x35
				$sym_type.$prefix$6$suffix$,	// 54 0x36
				$sym_type.$prefix$7$suffix$,	// 55 0x37
				$sym_type.$prefix$8$suffix$,	// 56 0x38
				$sym_type.$prefix$9$suffix$,	// 57 0x39
				$sym_type.$prefix$COLON$suffix$,	// 58 0x3a
				$sym_type.$prefix$SEMICOLON$suffix$,	// 59 0x3b
				$sym_type.$prefix$LT$suffix$,	// 60 0x3c
				$sym_type.$prefix$EQ$suffix$,	// 61 0x3d
				$sym_type.$prefix$GT$suffix$,	// 62 0x3e
				$sym_type.$prefix$QUERY$suffix$,	// 63 0x3f
				$sym_type.$prefix$AT$suffix$,	// 64 0x40
				$sym_type.$prefix$A$suffix$,	// 65 0x41
				$sym_type.$prefix$B$suffix$,	// 66 0x42
				$sym_type.$prefix$C$suffix$,	// 67 0x43
				$sym_type.$prefix$D$suffix$,	// 68 0x44
				$sym_type.$prefix$E$suffix$,	// 69 0x45
				$sym_type.$prefix$F$suffix$,	// 70 0x46
				$sym_type.$prefix$G$suffix$,	// 71 0x47
				$sym_type.$prefix$H$suffix$,	// 72 0x48
				$sym_type.$prefix$I$suffix$,	// 73 0x49
				$sym_type.$prefix$J$suffix$,	// 74 0x4a
				$sym_type.$prefix$K$suffix$,	// 75 0x4b
				$sym_type.$prefix$L$suffix$,	// 76 0x4c
				$sym_type.$prefix$M$suffix$,	// 77 0x4d
				$sym_type.$prefix$N$suffix$,	// 78 0x4e
				$sym_type.$prefix$O$suffix$,	// 79 0x4f
				$sym_type.$prefix$P$suffix$,	// 80 0x50
				$sym_type.$prefix$Q$suffix$,	// 81 0x51
				$sym_type.$prefix$R$suffix$,	// 82 0x52
				$sym_type.$prefix$S$suffix$,	// 83 0x53
				$sym_type.$prefix$T$suffix$,	// 84 0x54
				$sym_type.$prefix$U$suffix$,	// 85 0x55
				$sym_type.$prefix$V$suffix$,	// 86 0x56
				$sym_type.$prefix$W$suffix$,	// 87 0x57
				$sym_type.$prefix$X$suffix$,	// 88 0x58
				$sym_type.$prefix$Y$suffix$,	// 89 0x59
				$sym_type.$prefix$Z$suffix$,	// 90 0x5a
				$sym_type.$prefix$LSQUARE$suffix$,	// 91 0x5b
				$sym_type.$prefix$BSLASH$suffix$,	// 92 0x5c
				$sym_type.$prefix$RSQUARE$suffix$,	// 93 0x5d
				$sym_type.$prefix$CARET$suffix$,	// 94 0x5e
				$sym_type.$prefix$USCORE$suffix$,	// 95 0x5f
				$sym_type.$prefix$GRAVE$suffix$,	// 96 0x60
				$sym_type.$prefix$a$suffix$,	// 97 0x61
				$sym_type.$prefix$b$suffix$,	// 98 0x62
				$sym_type.$prefix$c$suffix$,	// 99 0x63
				$sym_type.$prefix$d$suffix$,	// 100 0x64
				$sym_type.$prefix$e$suffix$,	// 101 0x65
				$sym_type.$prefix$f$suffix$,	// 102 0x66
				$sym_type.$prefix$g$suffix$,	// 103 0x67
				$sym_type.$prefix$h$suffix$,	// 104 0x68
				$sym_type.$prefix$i$suffix$,	// 105 0x69
				$sym_type.$prefix$j$suffix$,	// 106 0x6a
				$sym_type.$prefix$k$suffix$,	// 107 0x6b
				$sym_type.$prefix$l$suffix$,	// 108 0x6c
				$sym_type.$prefix$m$suffix$,	// 109 0x6d
				$sym_type.$prefix$n$suffix$,	// 110 0x6e
				$sym_type.$prefix$o$suffix$,	// 111 0x6f
				$sym_type.$prefix$p$suffix$,	// 112 0x70
				$sym_type.$prefix$q$suffix$,	// 113 0x71
				$sym_type.$prefix$r$suffix$,	// 114 0x72
				$sym_type.$prefix$s$suffix$,	// 115 0x73
				$sym_type.$prefix$t$suffix$,	// 116 0x74
				$sym_type.$prefix$u$suffix$,	// 117 0x75
				$sym_type.$prefix$v$suffix$,	// 118 0x76
				$sym_type.$prefix$w$suffix$,	// 119 0x77
				$sym_type.$prefix$x$suffix$,	// 120 0x78
				$sym_type.$prefix$y$suffix$,	// 121 0x79
				$sym_type.$prefix$z$suffix$,	// 122 0x7a
				$sym_type.$prefix$LBRACE$suffix$,	// 123 0x7b
				$sym_type.$prefix$BAR$suffix$,	// 124 0x7c
				$sym_type.$prefix$RBRACE$suffix$,	// 125 0x7d
				$sym_type.$prefix$TILDE$suffix$,	// 126 0x7e

				$sym_type.$prefix$AfterASCII$suffix$,	  // for all chars in range 128..65534
				$sym_type.$prefix$EOF$suffix$			  // for '\uffff' or 65535
			};

		public final int getKind(int i)  // Classify character at ith location
		{
			int c = (i >= getStreamLength() ? '\uffff' : getCharValue(i));
			return (c < 128 // ASCII Character
					  ? tokenKind[c]
					  : c == '\uffff'
						   ? $sym_type.$prefix$EOF$suffix$
						   : $sym_type.$prefix$AfterASCII$suffix$);
		}

		public String[] orderedExportedSymbols() { return $exp_type.orderedTerminalSymbols; }

		public $super_stream_class(String filename, int tab) throws java.io.IOException
		{
			super(filename, tab);
		}

		public $super_stream_class(char[] input_chars, String filename, int tab)
		{
			super(input_chars, filename, tab);
		}

		public $super_stream_class(char[] input_chars, String filename)
		{
			super(input_chars, filename, 1);
		}
		}
	./
%End

%Define

	--
	-- Definition of macro used in the included file LexerBasicMap.g
	-- We redefine that one defined by EssentialOCLLexer
	--
	$kw_lexer_class /.Ecore2XtextKWLexer./

%End

%Export
	IDENTIFIER
	STRING
	WS

	SLASH_GT
	COLON
	LT
	LT_SLASH
	LT_SLASH_d_e_t_a_i_l_s_GT
	LT_SLASH_e_A_n_n_o_t_a_t_i_o_n_s_GT
	LT_SLASH_e_C_l_a_s_s_i_f_i_e_r_s_GT
	LT_SLASH_e_G_e_n_e_r_i_c_T_y_p_e_GT
	LT_SLASH_e_O_p_e_r_a_t_i_o_n_s_GT
	LT_SLASH_e_P_a_c_k_a_g_e_s_GT
	LT_SLASH_e_P_a_r_a_m_e_t_e_r_s_GT
	LT_SLASH_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s_GT
	LT_SLASH_e_T_y_p_e_A_r_g_u_m_e_n_t_s_GT
	LT_SLASH_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e_GT
	LT_SLASH_x_m_i_COLON_X_M_I_GT
	LT_QUERY_x_m_l
	LT_d_e_t_a_i_l_s
	LT_e_A_n_n_o_t_a_t_i_o_n_s
	LT_e_C_l_a_s_s_i_f_i_e_r_s
	LT_e_G_e_n_e_r_i_c_T_y_p_e
	LT_e_O_p_e_r_a_t_i_o_n_s
	LT_e_P_a_r_a_m_e_t_e_r_s
	LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s
	LT_e_S_u_b_p_a_c_k_a_g_e_s
	LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s
	LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e
	LT_x_m_i_COLON_X_M_I
	EQ
	GT
	QUERY_GT
%End

%Terminals
	Default

	0
	1
	2
	3
	4
	5
	6
	7
	8
	9
	A
	B
	C
	D
	E
	F
	G
	H
	I
	J
	K
	L
	M
	N
	O
	P
	Q
	R
	S
	T
	U
	V
	W
	X
	Y
	Z
	a
	b
	c
	d
	e
	f
	g
	h
	i
	j
	k
	l
	m
	n
	o
	p
	q
	r
	s
	t
	u
	v
	w
	x
	y
	z
	HTAB ::= HorizontalTab
	LF ::= NewLine
	CR ::= Return
	SPACE ::= ' '
	PLING ::= '!'
	DQUOTE ::= '"'
	HASH ::= '#'
	DOLLAR ::= '$'
	PERCENT ::= '%'
	AND ::= '&'
	SQUOTE ::= "'"
	LPAREN ::= '('
	RPAREN ::= ')'
	STAR ::= '*'
	PLUS ::= '+'
	COMMA ::= ','
	MINUS ::= '-'
	DOT ::= '.'
	SLASH ::= '/'
	COLON ::= ':'
	SEMICOLON ::= ';'
	LT ::= '<'
	EQ ::= '='
	GT ::= '>'
	QUERY ::= '?'
	AT ::= '@'
	LSQUARE ::= '['
	BSLASH ::= '\'
	RSQUARE ::= ']'
	CARET ::= '^'
	USCORE ::= '_'
	GRAVE ::= '`'
	LBRACE ::= '{'
	BAR ::= '|'
	RBRACE ::= '}'
	TILDE ::= '~'

	AfterASCII
%End

%Start
	Token
%End

%Rules
-- The Goal for the parser is a single Token

	Token ::= / >
		/.$BeginAction
			makeToken($_SLASH_GT);
		  $EndAction
	./

	Token ::= :
		/.$BeginAction
			makeToken($_COLON);
		  $EndAction
	./

	Token ::= <
		/.$BeginAction
			makeToken($_LT);
		  $EndAction
	./

	Token ::= < /
		/.$BeginAction
			makeToken($_LT_SLASH);
		  $EndAction
	./

	Token ::= < / d e t a i l s >
		/.$BeginAction
			makeToken($_LT_SLASH_d_e_t_a_i_l_s_GT);
		  $EndAction
	./

	Token ::= < / e A n n o t a t i o n s >
		/.$BeginAction
			makeToken($_LT_SLASH_e_A_n_n_o_t_a_t_i_o_n_s_GT);
		  $EndAction
	./

	Token ::= < / e C l a s s i f i e r s >
		/.$BeginAction
			makeToken($_LT_SLASH_e_C_l_a_s_s_i_f_i_e_r_s_GT);
		  $EndAction
	./

	Token ::= < / e G e n e r i c T y p e >
		/.$BeginAction
			makeToken($_LT_SLASH_e_G_e_n_e_r_i_c_T_y_p_e_GT);
		  $EndAction
	./

	Token ::= < / e O p e r a t i o n s >
		/.$BeginAction
			makeToken($_LT_SLASH_e_O_p_e_r_a_t_i_o_n_s_GT);
		  $EndAction
	./

	Token ::= < / e P a c k a g e s >
		/.$BeginAction
			makeToken($_LT_SLASH_e_P_a_c_k_a_g_e_s_GT);
		  $EndAction
	./

	Token ::= < / e P a r a m e t e r s >
		/.$BeginAction
			makeToken($_LT_SLASH_e_P_a_r_a_m_e_t_e_r_s_GT);
		  $EndAction
	./

	Token ::= < / e S t r u c t u r a l F e a t u r e s >
		/.$BeginAction
			makeToken($_LT_SLASH_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s_GT);
		  $EndAction
	./

	Token ::= < / e T y p e A r g u m e n t s >
		/.$BeginAction
			makeToken($_LT_SLASH_e_T_y_p_e_A_r_g_u_m_e_n_t_s_GT);
		  $EndAction
	./

	Token ::= < / e c o r e : E P a c k a g e >
		/.$BeginAction
			makeToken($_LT_SLASH_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e_GT);
		  $EndAction
	./

	Token ::= < / x m i : X M I >
		/.$BeginAction
			makeToken($_LT_SLASH_x_m_i_COLON_X_M_I_GT);
		  $EndAction
	./

	Token ::= < ? x m l
		/.$BeginAction
			makeToken($_LT_QUERY_x_m_l);
		  $EndAction
	./

	Token ::= < d e t a i l s
		/.$BeginAction
			makeToken($_LT_d_e_t_a_i_l_s);
		  $EndAction
	./

	Token ::= < e A n n o t a t i o n s
		/.$BeginAction
			makeToken($_LT_e_A_n_n_o_t_a_t_i_o_n_s);
		  $EndAction
	./

	Token ::= < e C l a s s i f i e r s
		/.$BeginAction
			makeToken($_LT_e_C_l_a_s_s_i_f_i_e_r_s);
		  $EndAction
	./

	Token ::= < e G e n e r i c T y p e
		/.$BeginAction
			makeToken($_LT_e_G_e_n_e_r_i_c_T_y_p_e);
		  $EndAction
	./

	Token ::= < e O p e r a t i o n s
		/.$BeginAction
			makeToken($_LT_e_O_p_e_r_a_t_i_o_n_s);
		  $EndAction
	./

	Token ::= < e P a r a m e t e r s
		/.$BeginAction
			makeToken($_LT_e_P_a_r_a_m_e_t_e_r_s);
		  $EndAction
	./

	Token ::= < e S t r u c t u r a l F e a t u r e s
		/.$BeginAction
			makeToken($_LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s);
		  $EndAction
	./

	Token ::= < e S u b p a c k a g e s
		/.$BeginAction
			makeToken($_LT_e_S_u_b_p_a_c_k_a_g_e_s);
		  $EndAction
	./

	Token ::= < e T y p e A r g u m e n t s
		/.$BeginAction
			makeToken($_LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s);
		  $EndAction
	./

	Token ::= < e c o r e : E P a c k a g e
		/.$BeginAction
			makeToken($_LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e);
		  $EndAction
	./

	Token ::= < x m i : X M I
		/.$BeginAction
			makeToken($_LT_x_m_i_COLON_X_M_I);
		  $EndAction
	./

	Token ::= =
		/.$BeginAction
			makeToken($_EQ);
		  $EndAction
	./

	Token ::= >
		/.$BeginAction
			makeToken($_GT);
		  $EndAction
	./

	Token ::= ? >
		/.$BeginAction
			makeToken($_QUERY_GT);
		  $EndAction
	./

	RANGE_1 -> ' ' | '!'

	RANGE_2 -> '#' | '$' | '%' | '&' | "'" | '(' | ')' | '*' | '+' | ',' | '-' | '.' | '/' | '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | ':' | ';' | '<' | '=' | '>' | '?' | '@' | 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | 'G' | 'H' | 'I' | 'J' | 'K' | 'L' | 'M' | 'N' | 'O' | 'P' | 'Q' | 'R' | 'S' | 'T' | 'U' | 'V' | 'W' | 'X' | 'Y' | 'Z' | '[' | '\' | ']' | '^' | '_' | '`' | 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g' | 'h' | 'i' | 'j' | 'k' | 'l' | 'm' | 'n' | 'o' | 'p' | 'q' | 'r' | 's' | 't' | 'u' | 'v' | 'w' | 'x' | 'y' | 'z' | '{' | '|' | '}' | '~'

	DIGITS -> '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9'

	UPPERS -> 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | 'G' | 'H' | 'I' | 'J' | 'K' | 'L' | 'M' | 'N' | 'O' | 'P' | 'Q' | 'R' | 'S' | 'T' | 'U' | 'V' | 'W' | 'X' | 'Y' | 'Z'

	RANGE_5 -> '_'

	LOWERS -> 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g' | 'h' | 'i' | 'j' | 'k' | 'l' | 'm' | 'n' | 'o' | 'p' | 'q' | 'r' | 's' | 't' | 'u' | 'v' | 'w' | 'x' | 'y' | 'z'

		Token ::= IDENTIFIER
			/.$BeginAction
						makeIdentifier(Ecore2XtextParsersym.TK_IDENTIFIER);
			  $EndAction
			./

	IDENTIFIER -> LOWERS
	IDENTIFIER -> RANGE_5
	IDENTIFIER -> UPPERS
	IDENTIFIER -> LOWERS IDENTIFIER_3
	IDENTIFIER -> RANGE_5 IDENTIFIER_3
	IDENTIFIER -> UPPERS IDENTIFIER_3
	IDENTIFIER_3 -> DIGITS
	IDENTIFIER_3 -> LOWERS
	IDENTIFIER_3 -> RANGE_5
	IDENTIFIER_3 -> UPPERS
	IDENTIFIER_3 -> IDENTIFIER_3 DIGITS
	IDENTIFIER_3 -> IDENTIFIER_3 LOWERS
	IDENTIFIER_3 -> IDENTIFIER_3 RANGE_5
	IDENTIFIER_3 -> IDENTIFIER_3 UPPERS

		Token ::= STRING
			/.$BeginAction
						makeToken(Ecore2XtextParsersym.TK_STRING);
			  $EndAction
			./

	STRING -> DQUOTE DQUOTE
	STRING -> DQUOTE STRING_3 DQUOTE
	STRING_3 -> RANGE_1
	STRING_3 -> RANGE_2
	STRING_3 -> STRING_3 RANGE_1
	STRING_3 -> STRING_3 RANGE_2

		Token ::= WS
			/.$BeginAction
						skipToken(Ecore2XtextParsersym.TK_WS);
			  $EndAction
			./

	WS -> WS_3
	WS_3 -> HTAB
	WS_3 -> LF
	WS_3 -> CR
	WS_3 -> SPACE
	WS_3 -> HTAB WS_3
	WS_3 -> LF WS_3
	WS_3 -> CR WS_3
	WS_3 -> SPACE WS_3
%End
