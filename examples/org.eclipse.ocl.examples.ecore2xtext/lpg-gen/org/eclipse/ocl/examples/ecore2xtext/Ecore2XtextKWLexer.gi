%options slr
%options fp=Ecore2XtextKWLexer,prefix=Char_
%options noserialize
%options package=org.eclipse.ocl.examples.ecore2xtext
%options template=../lpg/KeywordTemplateF.gi
%options export_terminals=("Ecore2XtextParsersym.java", "TK_")
%options include_directory="../lpg"

%Terminals
	B
	C
	E
	I
	L
	N
	O
	P
	R
	S
	T
	U
	V
	a
	b
	c
	d
	e
	f
	g
	h
	i
	k
	l
	m
	n
	o
	p
	r
	s
	t
	u
	v
	w
	x
	y
	z
%End

%Headers
	/.
		final static int tokenKind[] = new int[128];
		static
		{
			tokenKind['B'] = $sym_type.$prefix$B$suffix$;
			tokenKind['C'] = $sym_type.$prefix$C$suffix$;
			tokenKind['E'] = $sym_type.$prefix$E$suffix$;
			tokenKind['I'] = $sym_type.$prefix$I$suffix$;
			tokenKind['L'] = $sym_type.$prefix$L$suffix$;
			tokenKind['N'] = $sym_type.$prefix$N$suffix$;
			tokenKind['O'] = $sym_type.$prefix$O$suffix$;
			tokenKind['P'] = $sym_type.$prefix$P$suffix$;
			tokenKind['R'] = $sym_type.$prefix$R$suffix$;
			tokenKind['S'] = $sym_type.$prefix$S$suffix$;
			tokenKind['T'] = $sym_type.$prefix$T$suffix$;
			tokenKind['U'] = $sym_type.$prefix$U$suffix$;
			tokenKind['V'] = $sym_type.$prefix$V$suffix$;
			tokenKind['a'] = $sym_type.$prefix$a$suffix$;
			tokenKind['b'] = $sym_type.$prefix$b$suffix$;
			tokenKind['c'] = $sym_type.$prefix$c$suffix$;
			tokenKind['d'] = $sym_type.$prefix$d$suffix$;
			tokenKind['e'] = $sym_type.$prefix$e$suffix$;
			tokenKind['f'] = $sym_type.$prefix$f$suffix$;
			tokenKind['g'] = $sym_type.$prefix$g$suffix$;
			tokenKind['h'] = $sym_type.$prefix$h$suffix$;
			tokenKind['i'] = $sym_type.$prefix$i$suffix$;
			tokenKind['k'] = $sym_type.$prefix$k$suffix$;
			tokenKind['l'] = $sym_type.$prefix$l$suffix$;
			tokenKind['m'] = $sym_type.$prefix$m$suffix$;
			tokenKind['n'] = $sym_type.$prefix$n$suffix$;
			tokenKind['o'] = $sym_type.$prefix$o$suffix$;
			tokenKind['p'] = $sym_type.$prefix$p$suffix$;
			tokenKind['r'] = $sym_type.$prefix$r$suffix$;
			tokenKind['s'] = $sym_type.$prefix$s$suffix$;
			tokenKind['t'] = $sym_type.$prefix$t$suffix$;
			tokenKind['u'] = $sym_type.$prefix$u$suffix$;
			tokenKind['v'] = $sym_type.$prefix$v$suffix$;
			tokenKind['w'] = $sym_type.$prefix$w$suffix$;
			tokenKind['x'] = $sym_type.$prefix$x$suffix$;
			tokenKind['y'] = $sym_type.$prefix$y$suffix$;
			tokenKind['z'] = $sym_type.$prefix$z$suffix$;
		};

		final int getKind(char c)
		{
			return (((c & 0xFFFFFF80) == 0) /* 0 <= c < 128? */ ? tokenKind[c] : 0);
		}
	./
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
	eClassifiers
	eExceptions
	eOpposite
	eSuperTypes
	eType
	encoding
	false
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
	true
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

		| e C l a s s i f i e r s
		/.$BeginAction
			$setResult($_eClassifiers);
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

		| f a l s e
		/.$BeginAction
			$setResult($_false);
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

		| t r u e
		/.$BeginAction
			$setResult($_true);
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
