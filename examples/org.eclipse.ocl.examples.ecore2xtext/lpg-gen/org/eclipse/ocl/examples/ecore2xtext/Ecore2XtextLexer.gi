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

	SLASH_RANGLE
	COLON
	LANGLE
	LANGLE_SLASH
	LANGLE_SLASH_100_101_116_97_105_108_115_RANGLE
	LANGLE_SLASH_101_65_110_110_111_116_97_116_105_111_110_115_RANGLE
	LANGLE_SLASH_101_67_108_97_115_115_105_102_105_101_114_115_RANGLE
	LANGLE_SLASH_101_71_101_110_101_114_105_99_84_121_112_101_RANGLE
	LANGLE_SLASH_101_79_112_101_114_97_116_105_111_110_115_RANGLE
	LANGLE_SLASH_101_80_97_99_107_97_103_101_115_RANGLE
	LANGLE_SLASH_101_80_97_114_97_109_101_116_101_114_115_RANGLE
	LANGLE_SLASH_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_RANGLE
	LANGLE_SLASH_101_84_121_112_101_65_114_103_117_109_101_110_116_115_RANGLE
	LANGLE_SLASH_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101_RANGLE
	LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE
	LANGLE_QUERY_120_109_108
	LANGLE_100_101_116_97_105_108_115
	LANGLE_101_65_110_110_111_116_97_116_105_111_110_115
	LANGLE_101_67_108_97_115_115_105_102_105_101_114_115
	LANGLE_101_71_101_110_101_114_105_99_84_121_112_101
	LANGLE_101_79_112_101_114_97_116_105_111_110_115
	LANGLE_101_80_97_99_107_97_103_101_115
	LANGLE_101_80_97_114_97_109_101_116_101_114_115
	LANGLE_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115
	LANGLE_101_84_121_112_101_65_114_103_117_109_101_110_116_115
	LANGLE_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101
	LANGLE_120_109_105_COLON_88_77_73
	EQUALS
	RANGLE
	QUERY_RANGLE
%End

%Terminals
	0 1 2 3 4 5 6 7 8 9

	A B C D E F G H I J K L M N O P Q R S T U V W X Y Z

	_

	a b c d e f g h i j k l m n o p q r s t u v w x y z

	HTAB ::= HorizontalTab
	LF ::= NewLine
	CR ::= Return
	SPACE ::= ' '
	PLING ::= '!'
	DQUOTE ::= '"'
	HASH ::= '#'
	T_36 ::= '$'
	T_37 ::= '%'
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
	RANGLE ::= '>'
	AT ::= '@'
	LSQUARE ::= '['
	BSLASH ::= '\'
	RSQUARE ::= ']'
	CARET ::= '^'
	T_96 ::= '`'
	LBRACE ::= '{'
	BAR ::= '|'
	RBRACE ::= '}'
	T_126 ::= '~'
%End

%Start
	Token
%End

%Rules
	Token ::= '/' '>'
		/.$BeginAction
					makeToken($_SLASH_RANGLE);
		  $EndAction
		./

	Token ::= ':'
		/.$BeginAction
					makeToken($_COLON);
		  $EndAction
		./

	Token ::= '<'
		/.$BeginAction
					makeToken($_LANGLE);
		  $EndAction
		./

	Token ::= '<' '/'
		/.$BeginAction
					makeToken($_LANGLE_SLASH);
		  $EndAction
		./

	Token ::= '<' '/' 'd' 'e' 't' 'a' 'i' 'l' 's' '>'
		/.$BeginAction
					makeToken($_LANGLE_SLASH_100_101_116_97_105_108_115_RANGLE);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'A' 'n' 'n' 'o' 't' 'a' 't' 'i' 'o' 'n' 's' '>'
		/.$BeginAction
					makeToken($_LANGLE_SLASH_101_65_110_110_111_116_97_116_105_111_110_115_RANGLE);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'C' 'l' 'a' 's' 's' 'i' 'f' 'i' 'e' 'r' 's' '>'
		/.$BeginAction
					makeToken($_LANGLE_SLASH_101_67_108_97_115_115_105_102_105_101_114_115_RANGLE);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'G' 'e' 'n' 'e' 'r' 'i' 'c' 'T' 'y' 'p' 'e' '>'
		/.$BeginAction
					makeToken($_LANGLE_SLASH_101_71_101_110_101_114_105_99_84_121_112_101_RANGLE);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'O' 'p' 'e' 'r' 'a' 't' 'i' 'o' 'n' 's' '>'
		/.$BeginAction
					makeToken($_LANGLE_SLASH_101_79_112_101_114_97_116_105_111_110_115_RANGLE);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'P' 'a' 'c' 'k' 'a' 'g' 'e' 's' '>'
		/.$BeginAction
					makeToken($_LANGLE_SLASH_101_80_97_99_107_97_103_101_115_RANGLE);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'P' 'a' 'r' 'a' 'm' 'e' 't' 'e' 'r' 's' '>'
		/.$BeginAction
					makeToken($_LANGLE_SLASH_101_80_97_114_97_109_101_116_101_114_115_RANGLE);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'S' 't' 'r' 'u' 'c' 't' 'u' 'r' 'a' 'l' 'F' 'e' 'a' 't' 'u' 'r' 'e' 's' '>'
		/.$BeginAction
					makeToken($_LANGLE_SLASH_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_RANGLE);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'T' 'y' 'p' 'e' 'A' 'r' 'g' 'u' 'm' 'e' 'n' 't' 's' '>'
		/.$BeginAction
					makeToken($_LANGLE_SLASH_101_84_121_112_101_65_114_103_117_109_101_110_116_115_RANGLE);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'c' 'o' 'r' 'e' ':' 'E' 'P' 'a' 'c' 'k' 'a' 'g' 'e' '>'
		/.$BeginAction
					makeToken($_LANGLE_SLASH_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101_RANGLE);
		  $EndAction
		./

	Token ::= '<' '/' 'x' 'm' 'i' ':' 'X' 'M' 'I' '>'
		/.$BeginAction
					makeToken($_LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE);
		  $EndAction
		./

	Token ::= '<' '?' 'x' 'm' 'l'
		/.$BeginAction
					makeToken($_LANGLE_QUERY_120_109_108);
		  $EndAction
		./

	Token ::= '<' 'd' 'e' 't' 'a' 'i' 'l' 's'
		/.$BeginAction
					makeToken($_LANGLE_100_101_116_97_105_108_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'A' 'n' 'n' 'o' 't' 'a' 't' 'i' 'o' 'n' 's'
		/.$BeginAction
					makeToken($_LANGLE_101_65_110_110_111_116_97_116_105_111_110_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'C' 'l' 'a' 's' 's' 'i' 'f' 'i' 'e' 'r' 's'
		/.$BeginAction
					makeToken($_LANGLE_101_67_108_97_115_115_105_102_105_101_114_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'G' 'e' 'n' 'e' 'r' 'i' 'c' 'T' 'y' 'p' 'e'
		/.$BeginAction
					makeToken($_LANGLE_101_71_101_110_101_114_105_99_84_121_112_101);
		  $EndAction
		./

	Token ::= '<' 'e' 'O' 'p' 'e' 'r' 'a' 't' 'i' 'o' 'n' 's'
		/.$BeginAction
					makeToken($_LANGLE_101_79_112_101_114_97_116_105_111_110_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'P' 'a' 'c' 'k' 'a' 'g' 'e' 's'
		/.$BeginAction
					makeToken($_LANGLE_101_80_97_99_107_97_103_101_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'P' 'a' 'r' 'a' 'm' 'e' 't' 'e' 'r' 's'
		/.$BeginAction
					makeToken($_LANGLE_101_80_97_114_97_109_101_116_101_114_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'S' 't' 'r' 'u' 'c' 't' 'u' 'r' 'a' 'l' 'F' 'e' 'a' 't' 'u' 'r' 'e' 's'
		/.$BeginAction
					makeToken($_LANGLE_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'T' 'y' 'p' 'e' 'A' 'r' 'g' 'u' 'm' 'e' 'n' 't' 's'
		/.$BeginAction
					makeToken($_LANGLE_101_84_121_112_101_65_114_103_117_109_101_110_116_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'c' 'o' 'r' 'e' ':' 'E' 'P' 'a' 'c' 'k' 'a' 'g' 'e'
		/.$BeginAction
					makeToken($_LANGLE_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101);
		  $EndAction
		./

	Token ::= '<' 'x' 'm' 'i' ':' 'X' 'M' 'I'
		/.$BeginAction
					makeToken($_LANGLE_120_109_105_COLON_88_77_73);
		  $EndAction
		./

	Token ::= '='
		/.$BeginAction
					makeToken($_EQUALS);
		  $EndAction
		./

	Token ::= '>'
		/.$BeginAction
					makeToken($_RANGLE);
		  $EndAction
		./

	Token ::= '?' '>'
		/.$BeginAction
					makeToken($_QUERY_RANGLE);
		  $EndAction
		./

	DIGITS -> '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9'

	UPPERS -> 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | 'G' | 'H' | 'I' | 'J' | 'K' | 'L' | 'M' | 'N' | 'O' | 'P' | 'Q' | 'R' | 'S' | 'T' | 'U' | 'V' | 'W' | 'X' | 'Y' | 'Z'

	RANGE_1 -> '_'

	LOWERS -> 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g' | 'h' | 'i' | 'j' | 'k' | 'l' | 'm' | 'n' | 'o' | 'p' | 'q' | 'r' | 's' | 't' | 'u' | 'v' | 'w' | 'x' | 'y' | 'z'

	IDENTIFIER ::= IDENTIFIER_4
		/.$BeginAction
						makeToken($_IDENTIFIER);
		  $EndAction
		./
	IDENTIFIER_4 ::= DIGITS
		/.$BeginAction
						makeToken($_IDENTIFIER_4);
		  $EndAction
		./
	IDENTIFIER_4 ::= LOWERS
		/.$BeginAction
						makeToken($_IDENTIFIER_4);
		  $EndAction
		./
	IDENTIFIER_4 ::= RANGE_1
		/.$BeginAction
						makeToken($_IDENTIFIER_4);
		  $EndAction
		./
	IDENTIFIER_4 ::= UPPERS
		/.$BeginAction
						makeToken($_IDENTIFIER_4);
		  $EndAction
		./
	IDENTIFIER_4 ::= DIGITS IDENTIFIER_4
		/.$BeginAction
						makeToken($_IDENTIFIER_4);
		  $EndAction
		./
	IDENTIFIER_4 ::= LOWERS IDENTIFIER_4
		/.$BeginAction
						makeToken($_IDENTIFIER_4);
		  $EndAction
		./
	IDENTIFIER_4 ::= RANGE_1 IDENTIFIER_4
		/.$BeginAction
						makeToken($_IDENTIFIER_4);
		  $EndAction
		./
	IDENTIFIER_4 ::= UPPERS IDENTIFIER_4
		/.$BeginAction
						makeToken($_IDENTIFIER_4);
		  $EndAction
		./

	STRING ::= DQUOTE DQUOTE
		/.$BeginAction
						makeToken($_STRING);
		  $EndAction
		./
	STRING ::= DQUOTE STRING_9 DQUOTE
		/.$BeginAction
						makeToken($_STRING);
		  $EndAction
		./
	STRING_6 ::= LOWERS STRING_6
		/.$BeginAction
						makeToken($_STRING_6);
		  $EndAction
		./
	STRING_6 ::= LOWERS SEMICOLON
		/.$BeginAction
						makeToken($_STRING_6);
		  $EndAction
		./
	STRING_6 ::= UPPERS STRING_6
		/.$BeginAction
						makeToken($_STRING_6);
		  $EndAction
		./
	STRING_6 ::= UPPERS SEMICOLON
		/.$BeginAction
						makeToken($_STRING_6);
		  $EndAction
		./
	STRING_9 ::= HTAB
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= LF
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= CR
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= SPACE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= PLING
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= HASH
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= T_36
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= T_37
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= SQUOTE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= LPAREN
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= RPAREN
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STAR
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= PLUS
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= COMMA
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= MINUS
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= DOT
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= SLASH
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= COLON
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= SEMICOLON
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= RANGLE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= AT
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= LSQUARE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= BSLASH
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= RSQUARE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= CARET
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= T_96
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= LBRACE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= BAR
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= RBRACE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= T_126
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= DIGITS
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= LOWERS
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= RANGE_1
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= UPPERS
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 HTAB
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 LF
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 CR
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 SPACE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 PLING
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 HASH
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 T_36
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 T_37
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 SQUOTE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 LPAREN
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 RPAREN
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 STAR
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 PLUS
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 COMMA
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 MINUS
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 DOT
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 SLASH
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 COLON
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 SEMICOLON
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 RANGLE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 AT
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 LSQUARE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 BSLASH
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 RSQUARE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 CARET
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 T_96
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 LBRACE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 BAR
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 RBRACE
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 T_126
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 DIGITS
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 LOWERS
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 RANGE_1
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 UPPERS
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= AND STRING_6
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./
	STRING_9 ::= STRING_9 AND STRING_6
		/.$BeginAction
						makeToken($_STRING_9);
		  $EndAction
		./

	WS ::= WS_3
		/.$BeginAction
						makeToken($_WS);
		  $EndAction
		./
	WS_3 ::= HTAB
		/.$BeginAction
						makeToken($_WS_3);
		  $EndAction
		./
	WS_3 ::= LF
		/.$BeginAction
						makeToken($_WS_3);
		  $EndAction
		./
	WS_3 ::= CR
		/.$BeginAction
						makeToken($_WS_3);
		  $EndAction
		./
	WS_3 ::= SPACE
		/.$BeginAction
						makeToken($_WS_3);
		  $EndAction
		./
	WS_3 ::= HTAB WS_3
		/.$BeginAction
						makeToken($_WS_3);
		  $EndAction
		./
	WS_3 ::= LF WS_3
		/.$BeginAction
						makeToken($_WS_3);
		  $EndAction
		./
	WS_3 ::= CR WS_3
		/.$BeginAction
						makeToken($_WS_3);
		  $EndAction
		./
	WS_3 ::= SPACE WS_3
		/.$BeginAction
						makeToken($_WS_3);
		  $EndAction
		./

%End
