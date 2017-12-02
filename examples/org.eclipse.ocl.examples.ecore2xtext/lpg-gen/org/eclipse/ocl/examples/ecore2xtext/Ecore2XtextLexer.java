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

    public void ruleAction(int ruleNumber)
    {
        switch(ruleNumber)
        {

            //
            // Rule 1:  Token ::= / >
            //
            case 1: { 
				makeToken(Ecore2XtextParsersym.TK_SLASH_RANGLE);
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
				makeToken(Ecore2XtextParsersym.TK_LANGLE);
	              break;
            }
	
            //
            // Rule 4:  Token ::= < /
            //
            case 4: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_SLASH);
	              break;
            }
	
            //
            // Rule 5:  Token ::= < / d e t a i l s >
            //
            case 5: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_SLASH_100_101_116_97_105_108_115_RANGLE);
	              break;
            }
	
            //
            // Rule 6:  Token ::= < / e A n n o t a t i o n s >
            //
            case 6: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_SLASH_101_65_110_110_111_116_97_116_105_111_110_115_RANGLE);
	              break;
            }
	
            //
            // Rule 7:  Token ::= < / e C l a s s i f i e r s >
            //
            case 7: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_SLASH_101_67_108_97_115_115_105_102_105_101_114_115_RANGLE);
	              break;
            }
	
            //
            // Rule 8:  Token ::= < / e G e n e r i c T y p e >
            //
            case 8: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_SLASH_101_71_101_110_101_114_105_99_84_121_112_101_RANGLE);
	              break;
            }
	
            //
            // Rule 9:  Token ::= < / e O p e r a t i o n s >
            //
            case 9: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_SLASH_101_79_112_101_114_97_116_105_111_110_115_RANGLE);
	              break;
            }
	
            //
            // Rule 10:  Token ::= < / e P a c k a g e s >
            //
            case 10: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_SLASH_101_80_97_99_107_97_103_101_115_RANGLE);
	              break;
            }
	
            //
            // Rule 11:  Token ::= < / e P a r a m e t e r s >
            //
            case 11: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_SLASH_101_80_97_114_97_109_101_116_101_114_115_RANGLE);
	              break;
            }
	
            //
            // Rule 12:  Token ::= < / e S t r u c t u r a l F e a t u r e s >
            //
            case 12: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_SLASH_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_RANGLE);
	              break;
            }
	
            //
            // Rule 13:  Token ::= < / e T y p e A r g u m e n t s >
            //
            case 13: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_SLASH_101_84_121_112_101_65_114_103_117_109_101_110_116_115_RANGLE);
	              break;
            }
	
            //
            // Rule 14:  Token ::= < / e c o r e : E P a c k a g e >
            //
            case 14: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_SLASH_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101_RANGLE);
	              break;
            }
	
            //
            // Rule 15:  Token ::= < / x m i : X M I >
            //
            case 15: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE);
	              break;
            }
	
            //
            // Rule 16:  Token ::= < ? x m l
            //
            case 16: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_QUERY_120_109_108);
	              break;
            }
	
            //
            // Rule 17:  Token ::= < d e t a i l s
            //
            case 17: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_100_101_116_97_105_108_115);
	              break;
            }
	
            //
            // Rule 18:  Token ::= < e A n n o t a t i o n s
            //
            case 18: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_101_65_110_110_111_116_97_116_105_111_110_115);
	              break;
            }
	
            //
            // Rule 19:  Token ::= < e C l a s s i f i e r s
            //
            case 19: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_101_67_108_97_115_115_105_102_105_101_114_115);
	              break;
            }
	
            //
            // Rule 20:  Token ::= < e G e n e r i c T y p e
            //
            case 20: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_101_71_101_110_101_114_105_99_84_121_112_101);
	              break;
            }
	
            //
            // Rule 21:  Token ::= < e O p e r a t i o n s
            //
            case 21: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_101_79_112_101_114_97_116_105_111_110_115);
	              break;
            }
	
            //
            // Rule 22:  Token ::= < e P a c k a g e s
            //
            case 22: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_101_80_97_99_107_97_103_101_115);
	              break;
            }
	
            //
            // Rule 23:  Token ::= < e P a r a m e t e r s
            //
            case 23: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_101_80_97_114_97_109_101_116_101_114_115);
	              break;
            }
	
            //
            // Rule 24:  Token ::= < e S t r u c t u r a l F e a t u r e s
            //
            case 24: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115);
	              break;
            }
	
            //
            // Rule 25:  Token ::= < e T y p e A r g u m e n t s
            //
            case 25: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_101_84_121_112_101_65_114_103_117_109_101_110_116_115);
	              break;
            }
	
            //
            // Rule 26:  Token ::= < e c o r e : E P a c k a g e
            //
            case 26: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101);
	              break;
            }
	
            //
            // Rule 27:  Token ::= < x m i : X M I
            //
            case 27: { 
				makeToken(Ecore2XtextParsersym.TK_LANGLE_120_109_105_COLON_88_77_73);
	              break;
            }
	
            //
            // Rule 28:  Token ::= =
            //
            case 28: { 
				makeToken(Ecore2XtextParsersym.TK_EQUALS);
	              break;
            }
	
            //
            // Rule 29:  Token ::= >
            //
            case 29: { 
				makeToken(Ecore2XtextParsersym.TK_RANGLE);
	              break;
            }
	
            //
            // Rule 30:  Token ::= ? >
            //
            case 30: { 
				makeToken(Ecore2XtextParsersym.TK_QUERY_RANGLE);
	              break;
            }
	
            //
            // Rule 94:  IDENTIFIER ::= IDENTIFIER_4
            //
            case 94: { 
					makeToken(Ecore2XtextParsersym.TK_IDENTIFIER);
	              break;
            }
	
            //
            // Rule 95:  IDENTIFIER_4 ::= DIGITS
            //
            case 95: { 
					makeToken($_IDENTIFIER_4);
	              break;
            }
	
            //
            // Rule 96:  IDENTIFIER_4 ::= LOWERS
            //
            case 96: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 97:  IDENTIFIER_4 ::= RANGE_1
            //
            case 97: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 98:  IDENTIFIER_4 ::= UPPERS
            //
            case 98: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 99:  IDENTIFIER_4 ::= DIGITS IDENTIFIER_4
            //
            case 99: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 100:  IDENTIFIER_4 ::= LOWERS IDENTIFIER_4
            //
            case 100: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 101:  IDENTIFIER_4 ::= RANGE_1 IDENTIFIER_4
            //
            case 101: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 102:  IDENTIFIER_4 ::= UPPERS IDENTIFIER_4
            //
            case 102: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 103:  STRING ::= DQUOTE DQUOTE
            //
            case 103: { 
					makeToken(Ecore2XtextParsersym.TK_STRING);
	              break;
            }
	
            //
            // Rule 104:  STRING ::= DQUOTE STRING_9 DQUOTE
            //
            case 104: { 
					makeToken(Ecore2XtextParsersym.TK_STRING);
	              break;
            }
	
            //
            // Rule 105:  STRING_6 ::= LOWERS STRING_6
            //
            case 105: { 
					makeToken($_STRING_6);
	              break;
            }
	
            //
            // Rule 106:  STRING_6 ::= LOWERS SEMICOLON
            //
            case 106: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 107:  STRING_6 ::= UPPERS STRING_6
            //
            case 107: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 108:  STRING_6 ::= UPPERS SEMICOLON
            //
            case 108: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 109:  STRING_9 ::= HTAB
            //
            case 109: { 
					makeToken($_STRING_9);
	              break;
            }
	
            //
            // Rule 110:  STRING_9 ::= LF
            //
            case 110: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 111:  STRING_9 ::= CR
            //
            case 111: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 112:  STRING_9 ::= SPACE
            //
            case 112: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 113:  STRING_9 ::= PLING
            //
            case 113: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 114:  STRING_9 ::= HASH
            //
            case 114: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 115:  STRING_9 ::= T_36
            //
            case 115: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 116:  STRING_9 ::= T_37
            //
            case 116: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 117:  STRING_9 ::= SQUOTE
            //
            case 117: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 118:  STRING_9 ::= LPAREN
            //
            case 118: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 119:  STRING_9 ::= RPAREN
            //
            case 119: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 120:  STRING_9 ::= STAR
            //
            case 120: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 121:  STRING_9 ::= PLUS
            //
            case 121: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 122:  STRING_9 ::= COMMA
            //
            case 122: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 123:  STRING_9 ::= MINUS
            //
            case 123: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 124:  STRING_9 ::= DOT
            //
            case 124: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 125:  STRING_9 ::= SLASH
            //
            case 125: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 126:  STRING_9 ::= COLON
            //
            case 126: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 127:  STRING_9 ::= SEMICOLON
            //
            case 127: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 128:  STRING_9 ::= RANGLE
            //
            case 128: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 129:  STRING_9 ::= AT
            //
            case 129: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 130:  STRING_9 ::= LSQUARE
            //
            case 130: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 131:  STRING_9 ::= BSLASH
            //
            case 131: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 132:  STRING_9 ::= RSQUARE
            //
            case 132: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 133:  STRING_9 ::= CARET
            //
            case 133: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 134:  STRING_9 ::= T_96
            //
            case 134: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 135:  STRING_9 ::= LBRACE
            //
            case 135: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 136:  STRING_9 ::= BAR
            //
            case 136: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 137:  STRING_9 ::= RBRACE
            //
            case 137: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 138:  STRING_9 ::= T_126
            //
            case 138: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 139:  STRING_9 ::= DIGITS
            //
            case 139: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 140:  STRING_9 ::= LOWERS
            //
            case 140: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 141:  STRING_9 ::= RANGE_1
            //
            case 141: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 142:  STRING_9 ::= UPPERS
            //
            case 142: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 143:  STRING_9 ::= STRING_9 HTAB
            //
            case 143: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 144:  STRING_9 ::= STRING_9 LF
            //
            case 144: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 145:  STRING_9 ::= STRING_9 CR
            //
            case 145: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 146:  STRING_9 ::= STRING_9 SPACE
            //
            case 146: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 147:  STRING_9 ::= STRING_9 PLING
            //
            case 147: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 148:  STRING_9 ::= STRING_9 HASH
            //
            case 148: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 149:  STRING_9 ::= STRING_9 T_36
            //
            case 149: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 150:  STRING_9 ::= STRING_9 T_37
            //
            case 150: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 151:  STRING_9 ::= STRING_9 SQUOTE
            //
            case 151: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 152:  STRING_9 ::= STRING_9 LPAREN
            //
            case 152: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 153:  STRING_9 ::= STRING_9 RPAREN
            //
            case 153: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 154:  STRING_9 ::= STRING_9 STAR
            //
            case 154: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 155:  STRING_9 ::= STRING_9 PLUS
            //
            case 155: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 156:  STRING_9 ::= STRING_9 COMMA
            //
            case 156: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 157:  STRING_9 ::= STRING_9 MINUS
            //
            case 157: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 158:  STRING_9 ::= STRING_9 DOT
            //
            case 158: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 159:  STRING_9 ::= STRING_9 SLASH
            //
            case 159: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 160:  STRING_9 ::= STRING_9 COLON
            //
            case 160: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 161:  STRING_9 ::= STRING_9 SEMICOLON
            //
            case 161: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 162:  STRING_9 ::= STRING_9 RANGLE
            //
            case 162: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 163:  STRING_9 ::= STRING_9 AT
            //
            case 163: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 164:  STRING_9 ::= STRING_9 LSQUARE
            //
            case 164: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 165:  STRING_9 ::= STRING_9 BSLASH
            //
            case 165: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 166:  STRING_9 ::= STRING_9 RSQUARE
            //
            case 166: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 167:  STRING_9 ::= STRING_9 CARET
            //
            case 167: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 168:  STRING_9 ::= STRING_9 T_96
            //
            case 168: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 169:  STRING_9 ::= STRING_9 LBRACE
            //
            case 169: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 170:  STRING_9 ::= STRING_9 BAR
            //
            case 170: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 171:  STRING_9 ::= STRING_9 RBRACE
            //
            case 171: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 172:  STRING_9 ::= STRING_9 T_126
            //
            case 172: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 173:  STRING_9 ::= STRING_9 DIGITS
            //
            case 173: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 174:  STRING_9 ::= STRING_9 LOWERS
            //
            case 174: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 175:  STRING_9 ::= STRING_9 RANGE_1
            //
            case 175: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 176:  STRING_9 ::= STRING_9 UPPERS
            //
            case 176: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 177:  STRING_9 ::= AND STRING_6
            //
            case 177: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 178:  STRING_9 ::= STRING_9 AND STRING_6
            //
            case 178: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 179:  WS ::= WS_3
            //
            case 179: { 
					makeToken(Ecore2XtextParsersym.TK_WS);
	              break;
            }
	
            //
            // Rule 180:  WS_3 ::= HTAB
            //
            case 180: { 
					makeToken($_WS_3);
	              break;
            }
	
            //
            // Rule 181:  WS_3 ::= LF
            //
            case 181: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 182:  WS_3 ::= CR
            //
            case 182: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 183:  WS_3 ::= SPACE
            //
            case 183: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 184:  WS_3 ::= HTAB WS_3
            //
            case 184: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 185:  WS_3 ::= LF WS_3
            //
            case 185: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 186:  WS_3 ::= CR WS_3
            //
            case 186: { 
					makeToken();
	              break;
            }
	
            //
            // Rule 187:  WS_3 ::= SPACE WS_3
            //
            case 187: { 
					makeToken();
	              break;
            }
	
    
            default:
                break;
        }
        return;
    }
}

