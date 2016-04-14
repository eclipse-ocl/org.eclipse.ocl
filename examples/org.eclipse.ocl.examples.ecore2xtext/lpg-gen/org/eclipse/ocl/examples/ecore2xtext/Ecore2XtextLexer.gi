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

%Import
	LexerBasicMapF.gi
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

	Slash_GreaterThan
	Colon
	LessThan
	LessThan_Slash
	LessThan_Slash_100_101_116_97_105_108_115_GreaterThan
	LessThan_Slash_101_65_110_110_111_116_97_116_105_111_110_115_GreaterThan
	LessThan_Slash_101_67_108_97_115_115_105_102_105_101_114_115_GreaterThan
	LessThan_Slash_101_71_101_110_101_114_105_99_84_121_112_101_GreaterThan
	LessThan_Slash_101_79_112_101_114_97_116_105_111_110_115_GreaterThan
	LessThan_Slash_101_80_97_99_107_97_103_101_115_GreaterThan
	LessThan_Slash_101_80_97_114_97_109_101_116_101_114_115_GreaterThan
	LessThan_Slash_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_GreaterThan
	LessThan_Slash_101_84_121_112_101_65_114_103_117_109_101_110_116_115_GreaterThan
	LessThan_Slash_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101_GreaterThan
	LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan
	LessThan_QuestionMark_120_109_108
	LessThan_100_101_116_97_105_108_115
	LessThan_101_65_110_110_111_116_97_116_105_111_110_115
	LessThan_101_67_108_97_115_115_105_102_105_101_114_115
	LessThan_101_71_101_110_101_114_105_99_84_121_112_101
	LessThan_101_79_112_101_114_97_116_105_111_110_115
	LessThan_101_80_97_99_107_97_103_101_115
	LessThan_101_80_97_114_97_109_101_116_101_114_115
	LessThan_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115
	LessThan_101_84_121_112_101_65_114_103_117_109_101_110_116_115
	LessThan_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101
	LessThan_120_109_105_Colon_88_77_73
	Equal
	GreaterThan
	QuestionMark_GreaterThan
%End

%Terminals
	0 1 2 3 4 5 6 7 8 9

	A B C D E F G H I J K L M N O P Q R S T U V W X Y Z

	_

	a b c d e f g h i j k l m n o p q r s t u v w x y z

	HT ::= HorizontalTab
	LF ::= NewLine
	CR ::= Return
	Space ::= ' '
	Exclamation ::= '!'
	DoubleQuote ::= '"'
	Sharp ::= '#'
	DollarSign ::= '$'
	Percent ::= '%'
	Ampersand ::= '&'
	SingleQuote ::= "'"
	LeftParen ::= '('
	RightParen ::= ')'
	Star ::= '*'
	Plus ::= '+'
	Comma ::= ','
	Minus ::= '-'
	Dot ::= '.'
	Slash ::= '/'
	Colon ::= ':'
	SemiColon ::= ';'
	LessThan ::= '<'
	Equal ::= '='
	GreaterThan ::= '>'
	QuestionMark ::= '?'
	AtSign ::= '@'
	LeftBracket ::= '['
	BackSlash ::= '\'
	RightBracket ::= ']'
	Caret ::= '^'
	BackQuote ::= '`'
	LeftBrace ::= '{'
	VerticalBar ::= '|'
	RightBrace ::= '}'
	Tilde ::= '~'
	
	FF
	AfterASCII
	CtlCharNotWS
%End

%Start
	Token
%End

%Rules
	Token ::= '/' '>'
		/.$BeginAction
				makeToken($_Slash_GreaterThan);
		  $EndAction
		./

	Token ::= ':'
		/.$BeginAction
				makeToken($_Colon);
		  $EndAction
		./

	Token ::= '<'
		/.$BeginAction
				makeToken($_LessThan);
		  $EndAction
		./

	Token ::= '<' '/'
		/.$BeginAction
				makeToken($_LessThan_Slash);
		  $EndAction
		./

	Token ::= '<' '/' 'd' 'e' 't' 'a' 'i' 'l' 's' '>'
		/.$BeginAction
				makeToken($_LessThan_Slash_100_101_116_97_105_108_115_GreaterThan);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'A' 'n' 'n' 'o' 't' 'a' 't' 'i' 'o' 'n' 's' '>'
		/.$BeginAction
				makeToken($_LessThan_Slash_101_65_110_110_111_116_97_116_105_111_110_115_GreaterThan);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'C' 'l' 'a' 's' 's' 'i' 'f' 'i' 'e' 'r' 's' '>'
		/.$BeginAction
				makeToken($_LessThan_Slash_101_67_108_97_115_115_105_102_105_101_114_115_GreaterThan);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'G' 'e' 'n' 'e' 'r' 'i' 'c' 'T' 'y' 'p' 'e' '>'
		/.$BeginAction
				makeToken($_LessThan_Slash_101_71_101_110_101_114_105_99_84_121_112_101_GreaterThan);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'O' 'p' 'e' 'r' 'a' 't' 'i' 'o' 'n' 's' '>'
		/.$BeginAction
				makeToken($_LessThan_Slash_101_79_112_101_114_97_116_105_111_110_115_GreaterThan);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'P' 'a' 'c' 'k' 'a' 'g' 'e' 's' '>'
		/.$BeginAction
				makeToken($_LessThan_Slash_101_80_97_99_107_97_103_101_115_GreaterThan);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'P' 'a' 'r' 'a' 'm' 'e' 't' 'e' 'r' 's' '>'
		/.$BeginAction
				makeToken($_LessThan_Slash_101_80_97_114_97_109_101_116_101_114_115_GreaterThan);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'S' 't' 'r' 'u' 'c' 't' 'u' 'r' 'a' 'l' 'F' 'e' 'a' 't' 'u' 'r' 'e' 's' '>'
		/.$BeginAction
				makeToken($_LessThan_Slash_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_GreaterThan);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'T' 'y' 'p' 'e' 'A' 'r' 'g' 'u' 'm' 'e' 'n' 't' 's' '>'
		/.$BeginAction
				makeToken($_LessThan_Slash_101_84_121_112_101_65_114_103_117_109_101_110_116_115_GreaterThan);
		  $EndAction
		./

	Token ::= '<' '/' 'e' 'c' 'o' 'r' 'e' ':' 'E' 'P' 'a' 'c' 'k' 'a' 'g' 'e' '>'
		/.$BeginAction
				makeToken($_LessThan_Slash_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101_GreaterThan);
		  $EndAction
		./

	Token ::= '<' '/' 'x' 'm' 'i' ':' 'X' 'M' 'I' '>'
		/.$BeginAction
				makeToken($_LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan);
		  $EndAction
		./

	Token ::= '<' '?' 'x' 'm' 'l'
		/.$BeginAction
				makeToken($_LessThan_QuestionMark_120_109_108);
		  $EndAction
		./

	Token ::= '<' 'd' 'e' 't' 'a' 'i' 'l' 's'
		/.$BeginAction
				makeToken($_LessThan_100_101_116_97_105_108_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'A' 'n' 'n' 'o' 't' 'a' 't' 'i' 'o' 'n' 's'
		/.$BeginAction
				makeToken($_LessThan_101_65_110_110_111_116_97_116_105_111_110_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'C' 'l' 'a' 's' 's' 'i' 'f' 'i' 'e' 'r' 's'
		/.$BeginAction
				makeToken($_LessThan_101_67_108_97_115_115_105_102_105_101_114_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'G' 'e' 'n' 'e' 'r' 'i' 'c' 'T' 'y' 'p' 'e'
		/.$BeginAction
				makeToken($_LessThan_101_71_101_110_101_114_105_99_84_121_112_101);
		  $EndAction
		./

	Token ::= '<' 'e' 'O' 'p' 'e' 'r' 'a' 't' 'i' 'o' 'n' 's'
		/.$BeginAction
				makeToken($_LessThan_101_79_112_101_114_97_116_105_111_110_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'P' 'a' 'c' 'k' 'a' 'g' 'e' 's'
		/.$BeginAction
				makeToken($_LessThan_101_80_97_99_107_97_103_101_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'P' 'a' 'r' 'a' 'm' 'e' 't' 'e' 'r' 's'
		/.$BeginAction
				makeToken($_LessThan_101_80_97_114_97_109_101_116_101_114_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'S' 't' 'r' 'u' 'c' 't' 'u' 'r' 'a' 'l' 'F' 'e' 'a' 't' 'u' 'r' 'e' 's'
		/.$BeginAction
				makeToken($_LessThan_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'T' 'y' 'p' 'e' 'A' 'r' 'g' 'u' 'm' 'e' 'n' 't' 's'
		/.$BeginAction
				makeToken($_LessThan_101_84_121_112_101_65_114_103_117_109_101_110_116_115);
		  $EndAction
		./

	Token ::= '<' 'e' 'c' 'o' 'r' 'e' ':' 'E' 'P' 'a' 'c' 'k' 'a' 'g' 'e'
		/.$BeginAction
				makeToken($_LessThan_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101);
		  $EndAction
		./

	Token ::= '<' 'x' 'm' 'i' ':' 'X' 'M' 'I'
		/.$BeginAction
				makeToken($_LessThan_120_109_105_Colon_88_77_73);
		  $EndAction
		./

	Token ::= '='
		/.$BeginAction
				makeToken($_Equal);
		  $EndAction
		./

	Token ::= '>'
		/.$BeginAction
				makeToken($_GreaterThan);
		  $EndAction
		./

	Token ::= '?' '>'
		/.$BeginAction
				makeToken($_QuestionMark_GreaterThan);
		  $EndAction
		./

	DIGITS -> '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9'

	UPPERS -> 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | 'G' | 'H' | 'I' | 'J' | 'K' | 'L' | 'M' | 'N' | 'O' | 'P' | 'Q' | 'R' | 'S' | 'T' | 'U' | 'V' | 'W' | 'X' | 'Y' | 'Z'

	RANGE_2 -> '_'

	LOWERS -> 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g' | 'h' | 'i' | 'j' | 'k' | 'l' | 'm' | 'n' | 'o' | 'p' | 'q' | 'r' | 's' | 't' | 'u' | 'v' | 'w' | 'x' | 'y' | 'z'

	Token ::= IDENTIFIER
		/.$BeginAction
				makeToken($_IDENTIFIER);
		  $EndAction
		./
	IDENTIFIER -> IDENTIFIER_4
	IDENTIFIER_4 -> DIGITS
	IDENTIFIER_4 -> LOWERS
	IDENTIFIER_4 -> RANGE_2
	IDENTIFIER_4 -> UPPERS
	IDENTIFIER_4 -> DIGITS IDENTIFIER_4
	IDENTIFIER_4 -> LOWERS IDENTIFIER_4
	IDENTIFIER_4 -> RANGE_2 IDENTIFIER_4
	IDENTIFIER_4 -> UPPERS IDENTIFIER_4

	Token ::= STRING
		/.$BeginAction
				makeToken($_STRING);
		  $EndAction
		./
	STRING -> DoubleQuote DoubleQuote
	STRING -> DoubleQuote STRING_9 DoubleQuote
	STRING_6 -> LOWERS STRING_6
	STRING_6 -> LOWERS SemiColon
	STRING_6 -> UPPERS STRING_6
	STRING_6 -> UPPERS SemiColon
	STRING_9 -> HT
	STRING_9 -> LF
	STRING_9 -> CR
	STRING_9 -> Space
	STRING_9 -> Exclamation
	STRING_9 -> Sharp
	STRING_9 -> DollarSign
	STRING_9 -> Percent
	STRING_9 -> SingleQuote
	STRING_9 -> LeftParen
	STRING_9 -> RightParen
	STRING_9 -> Star
	STRING_9 -> Plus
	STRING_9 -> Comma
	STRING_9 -> Minus
	STRING_9 -> Dot
	STRING_9 -> Slash
	STRING_9 -> Colon
	STRING_9 -> SemiColon
	STRING_9 -> GreaterThan
	STRING_9 -> AtSign
	STRING_9 -> LeftBracket
	STRING_9 -> BackSlash
	STRING_9 -> RightBracket
	STRING_9 -> Caret
	STRING_9 -> BackQuote
	STRING_9 -> LeftBrace
	STRING_9 -> VerticalBar
	STRING_9 -> RightBrace
	STRING_9 -> Tilde
	STRING_9 -> DIGITS
	STRING_9 -> LOWERS
	STRING_9 -> RANGE_2
	STRING_9 -> UPPERS
	STRING_9 -> STRING_9 HT
	STRING_9 -> STRING_9 LF
	STRING_9 -> STRING_9 CR
	STRING_9 -> STRING_9 Space
	STRING_9 -> STRING_9 Exclamation
	STRING_9 -> STRING_9 Sharp
	STRING_9 -> STRING_9 DollarSign
	STRING_9 -> STRING_9 Percent
	STRING_9 -> STRING_9 SingleQuote
	STRING_9 -> STRING_9 LeftParen
	STRING_9 -> STRING_9 RightParen
	STRING_9 -> STRING_9 Star
	STRING_9 -> STRING_9 Plus
	STRING_9 -> STRING_9 Comma
	STRING_9 -> STRING_9 Minus
	STRING_9 -> STRING_9 Dot
	STRING_9 -> STRING_9 Slash
	STRING_9 -> STRING_9 Colon
	STRING_9 -> STRING_9 SemiColon
	STRING_9 -> STRING_9 GreaterThan
	STRING_9 -> STRING_9 AtSign
	STRING_9 -> STRING_9 LeftBracket
	STRING_9 -> STRING_9 BackSlash
	STRING_9 -> STRING_9 RightBracket
	STRING_9 -> STRING_9 Caret
	STRING_9 -> STRING_9 BackQuote
	STRING_9 -> STRING_9 LeftBrace
	STRING_9 -> STRING_9 VerticalBar
	STRING_9 -> STRING_9 RightBrace
	STRING_9 -> STRING_9 Tilde
	STRING_9 -> STRING_9 DIGITS
	STRING_9 -> STRING_9 LOWERS
	STRING_9 -> STRING_9 RANGE_2
	STRING_9 -> STRING_9 UPPERS
	STRING_9 -> Ampersand STRING_6
	STRING_9 -> STRING_9 Ampersand STRING_6

	Token ::= WS
		/.$BeginAction
				skipToken();
		  $EndAction
		./
	WS -> WS_3
	WS_3 -> HT
	WS_3 -> LF
	WS_3 -> CR
	WS_3 -> Space
	WS_3 -> HT WS_3
	WS_3 -> LF WS_3
	WS_3 -> CR WS_3
	WS_3 -> Space WS_3

%End
