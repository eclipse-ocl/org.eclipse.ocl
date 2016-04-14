%options slr
%options fp=Ecore2XtextKWLexer,prefix=Char_
%options noserialize
%options package=org.eclipse.ocl.examples.ecore2xtext
%options template=../lpg/KeywordTemplateF.gi
%options export_terminals=("Ecore2XtextParsersym.java", "TK_")
%options include_directory="../lpg"

%Import
	KWLexerMapF.gi
%End

%Define

	--
	-- Definition of macros used in the template
	--
	$action_class /.$file_prefix./
	$eof_char /.Char_EOF./
	$copyright_contributions /.*./

%End

%Notice
	/./**
 * Ecore2Xtext Keyword Lexer
 * <copyright>
 *******************************************************************************/
	./
%End

%Globals
	/../
%End

%Export
	abstract
	changeable
	containment
	defaultValueLiteral
	derived
	eClassifier
	eExceptions
	eOpposite
	eSuperTypes
	eType
	encoding
	instanceClassName
	key
	lowerBound
	name
	nsPrefix
	nsURI
	ordered
	resolveProxies
	serializable
	source
	transient
	type
	unsettable
	upperBound
	value
	version
	volatile
	xmi
	xmlns
	xsi
%End

%Start
	KeyWord
%End

%Rules

-- The Goal for the parser is a single Keyword

	KeyWord ::=
		a b s t r a c t
		/.$BeginAction
			$setResult($_abstract);
		  $EndAction
		./
		
		| c h a n g e a b l e
		/.$BeginAction
			$setResult($_changeable);
		  $EndAction
		./
		
		| c o n t a i n m e n t
		/.$BeginAction
			$setResult($_containment);
		  $EndAction
		./
		
		| d e f a u l t V a l u e L i t e r a l
		/.$BeginAction
			$setResult($_defaultValueLiteral);
		  $EndAction
		./
		
		| d e r i v e d
		/.$BeginAction
			$setResult($_derived);
		  $EndAction
		./
		
		| e C l a s s i f i e r
		/.$BeginAction
			$setResult($_eClassifier);
		  $EndAction
		./
		
		| e E x c e p t i o n s
		/.$BeginAction
			$setResult($_eExceptions);
		  $EndAction
		./
		
		| e O p p o s i t e
		/.$BeginAction
			$setResult($_eOpposite);
		  $EndAction
		./
		
		| e S u p e r T y p e s
		/.$BeginAction
			$setResult($_eSuperTypes);
		  $EndAction
		./
		
		| e T y p e
		/.$BeginAction
			$setResult($_eType);
		  $EndAction
		./
		
		| e n c o d i n g
		/.$BeginAction
			$setResult($_encoding);
		  $EndAction
		./
		
		| i n s t a n c e C l a s s N a m e
		/.$BeginAction
			$setResult($_instanceClassName);
		  $EndAction
		./
		
		| k e y
		/.$BeginAction
			$setResult($_key);
		  $EndAction
		./
		
		| l o w e r B o u n d
		/.$BeginAction
			$setResult($_lowerBound);
		  $EndAction
		./
		
		| n a m e
		/.$BeginAction
			$setResult($_name);
		  $EndAction
		./
		
		| n s P r e f i x
		/.$BeginAction
			$setResult($_nsPrefix);
		  $EndAction
		./
		
		| n s U R I
		/.$BeginAction
			$setResult($_nsURI);
		  $EndAction
		./
		
		| o r d e r e d
		/.$BeginAction
			$setResult($_ordered);
		  $EndAction
		./
		
		| r e s o l v e P r o x i e s
		/.$BeginAction
			$setResult($_resolveProxies);
		  $EndAction
		./
		
		| s e r i a l i z a b l e
		/.$BeginAction
			$setResult($_serializable);
		  $EndAction
		./
		
		| s o u r c e
		/.$BeginAction
			$setResult($_source);
		  $EndAction
		./
		
		| t r a n s i e n t
		/.$BeginAction
			$setResult($_transient);
		  $EndAction
		./
		
		| t y p e
		/.$BeginAction
			$setResult($_type);
		  $EndAction
		./
		
		| u n s e t t a b l e
		/.$BeginAction
			$setResult($_unsettable);
		  $EndAction
		./
		
		| u p p e r B o u n d
		/.$BeginAction
			$setResult($_upperBound);
		  $EndAction
		./
		
		| v a l u e
		/.$BeginAction
			$setResult($_value);
		  $EndAction
		./
		
		| v e r s i o n
		/.$BeginAction
			$setResult($_version);
		  $EndAction
		./
		
		| v o l a t i l e
		/.$BeginAction
			$setResult($_volatile);
		  $EndAction
		./
		
		| x m i
		/.$BeginAction
			$setResult($_xmi);
		  $EndAction
		./
		
		| x m l n s
		/.$BeginAction
			$setResult($_xmlns);
		  $EndAction
		./
		
		| x s i
		/.$BeginAction
			$setResult($_xsi);
		  $EndAction
		./
%End
