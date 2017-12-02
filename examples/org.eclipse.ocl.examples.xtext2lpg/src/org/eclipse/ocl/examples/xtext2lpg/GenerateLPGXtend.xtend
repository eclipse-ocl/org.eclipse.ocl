/*******************************************************************************
 * Copyright (c) 2014 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *	 E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext2lpg

import org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractElement
import org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule
import org.eclipse.ocl.examples.xtext2lpg.XBNF.ActionAssignment
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Disjunction
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Epsilon
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Keyword
import org.eclipse.ocl.examples.xtext2lpg.XBNF.KeywordAssignment
import org.eclipse.ocl.examples.xtext2lpg.XBNF.LexerGrammar
import org.eclipse.ocl.examples.xtext2lpg.XBNF.ParserGrammar
import org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCall
import org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCallAssignment
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax
import org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule
import org.eclipse.ocl.examples.xtext2lpg.XBNF.CharacterRange
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Wildcard
import org.eclipse.ocl.examples.xtext2lpg.XBNF.UntilToken

 class GenerateLPGXtend extends GenerateLPGUtils
{
	/*@NonNull*/ protected override String generateLPGKWLexer(/*@NonNull*/ Syntax syntax) {
		return generateKWLexer(getLexerGrammar(syntax), getParserGrammar(syntax))
	}
	/*@NonNull*/ protected override String generateLPGLexer(/*@NonNull*/ Syntax syntax) {
		return generateLexer(getLexerGrammar(syntax), getParserGrammar(syntax));
	}
	
	/*@NonNull*/ protected override String generateLPGParser(/*@NonNull*/ Syntax syntax) {
		return generateParser(getParserGrammar(syntax));
	}
	
 	protected def String generateKWLexer(/*@NonNull*/ LexerGrammar lexerGrammar, /*@NonNull*/ ParserGrammar parserGrammar) {
		var keywordValues = getSortedIdentifierKWValues(parserGrammar);
		var keywordCharacters = getSortedCharacters(keywordValues);
		var keywordAlphaNumericCharacters = getAlphaNumericCharacters(keywordCharacters);
		var keywordPunctuationCharacters = getPunctuationCharacters(keywordCharacters);
		'''
		%options slr
		%options fp=«syntaxName»KWLexer,prefix=Char_
		%options noserialize
		%options package=«emitSyntaxPackage(lexerGrammar.syntax)»
		%options template=../lpg/KeywordTemplateF.gi
		%options export_terminals=("«syntaxName»Parsersym.java", "TK_")
		%options include_directory="../lpg"
		
		%Terminals
			«FOR keywordCharacter : keywordAlphaNumericCharacters»
				«keywordCharacter» 
			«ENDFOR»
			«FOR keywordCharacter : keywordPunctuationCharacters»
				«emitLabel(keywordCharacter)» ::= «emitCharacter(keywordCharacter)»
			«ENDFOR»
		%End
		
		%Headers
			/.
				final static int tokenKind[] = new int[128];
				static
				{
					«FOR keywordCharacter : keywordCharacters»
						tokenKind[«emitCharacter(keywordCharacter)»] = $sym_type.$prefix$«emitLabel(keywordCharacter)»$suffix$;
					«ENDFOR»
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
		 * «syntaxName» Keyword Lexer
		 * <copyright>
		 *******************************************************************************/
			./
		%End

		%Globals
			/../
		%End

		%Export
			«FOR keywordValue : keywordValues»
				«emitLabel(keywordValue)»
			«ENDFOR»
		%End

		%Start
			KeyWord
		%End

		%Rules

		-- The Goal for the parser is a single Keyword

			KeyWord ::=
				«FOR keywordValue : keywordValues SEPARATOR '\n\n| '»«FOR c : keywordValue.toCharArray() SEPARATOR ' '»«c»«ENDFOR»
				/.$BeginAction
					$setResult($_«emitLabel(keywordValue)»);
				  $EndAction
				./«ENDFOR»
		%End
		'''
	}

 	protected def String generateLexer(/*@NonNull*/ LexerGrammar lexerGrammar, /*@NonNull*/ ParserGrammar parserGrammar) {
		var syntax = lexerGrammar.getSyntax();
		var syntaxName = emitSyntaxName(syntax);
		'''
		%options escape=$
		%options la=2
		%options fp=«syntaxName»Lexer,prefix=Char_
		%options single-productions
		%options noserialize
		%options package=«emitSyntaxPackage(syntax)»
		%options template=../lpg/LexerTemplateF.gi
		%options filter=«syntaxName»KWLexer.gi
		%options export_terminals=("«syntaxName»Parsersym.java", "TK_")
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
					«generateLexerTokenKinds(lexerGrammar, parserGrammar)»
						
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
			$kw_lexer_class /.«syntaxName»KWLexer./
		
		%End

		%Export
			«FOR terminalRule : getSortedTerminalRules(syntax)»
				«terminalRule.name»
			«ENDFOR»

			«FOR keywordValue : getSortedNonIdentifierKWValues(parserGrammar)»
				«emitLabel(keywordValue)»
			«ENDFOR»
		%End
		
		«generateLexerTerminals(lexerGrammar, parserGrammar)»

		%Start
			Token
		%End
		
		%Rules
		-- The Goal for the parser is a single Token
			«FOR keywordValue : getSortedNonIdentifierKWValues(parserGrammar)»

			«generateLexerKeywordRule(keywordValue)»
			«ENDFOR»
			«FOR characterRange : getSortedCharacterRanges(lexerGrammar)»

			«generateLexerCharacterRangeRule(characterRange)»
			«ENDFOR»
			«FOR terminalRule : getSortedTerminalRules(syntax)»

			«generateLexerTerminalRule(terminalRule)»
			«ENDFOR»
		%End
		'''
	}

	protected def String generateLexerCharacterRangeRule(CharacterRange characterRange) {
		'''
			«characterRange.getDebug()» -> «FOR c : getCharacters(characterRange) SEPARATOR ' | '»«emitQuotedCharacter(c.charAt(0))»«ENDFOR»
		'''
	}

	protected def String generateLexerDisjunction(AbstractRule rule) {
		'''
		«FOR conjunction : getSortedConjunctions(rule.element as Disjunction)»
		«rule.name» ->«IF conjunction.elements.isEmpty()» %empty«ELSE»«FOR element : conjunction.elements» «generateTerm(element)»«ENDFOR»«ENDIF»
		«ENDFOR»
		'''
	}

	protected def String generateLexerKeywordRule(String keyword) {
		'''
			Token ::=«FOR c : keyword.toCharArray()» «c»«ENDFOR»
				/.$BeginAction
					makeToken($_«emitLabel(keyword)»);
				  $EndAction
			./
		'''
	}

	protected def String generateLexerTerminalRule(TypedRule rule) {
		'''
			Token ::= «emitLabel(rule.name)»
				/.$BeginAction
							«if ("WS".equals(rule.name)) "skipToken" else if ("IDENTIFIER".equals(rule.name)) "makeIdentifier" else "makeToken"»(«syntaxName»Parsersym.TK_«rule.name»);
				  $EndAction
				./

		«generateLexerDisjunction(rule)»
		«FOR subrule : getSortedSubRules(rule.subrules)»
			«generateLexerDisjunction(subrule)»
		«ENDFOR»
		'''
	}

	protected def String generateLexerTerminals(/*@NonNull*/ LexerGrammar lexerGrammar, /*@NonNull*/ ParserGrammar parserGrammar) {
		var punctChars = getSortedPunctChars(lexerGrammar, parserGrammar);
		var keywordCharacters = punctChars;
		var alphaNumericCharacters = getAlphaNumericCharacters(keywordCharacters);
		var punctuationCharacters = getPunctuationCharacters(keywordCharacters);
		'''
		%Terminals			
			Default
			
			«FOR keywordCharacter : alphaNumericCharacters»
				«keywordCharacter» 
			«ENDFOR»			
			«FOR c : punctuationCharacters»
				«emitLabel(c)» ::= «emitQuotedCharacter(c)»
			«ENDFOR»
			
			AfterASCII			
		%End
		'''
	}

	protected def String generateLexerTokenKinds(/*@NonNull*/ LexerGrammar lexerGrammar, /*@NonNull*/ ParserGrammar parserGrammar) {
		var characterRanges = getSortedCharacterRanges(lexerGrammar);
		var punctChars = getSortedPunctChars(lexerGrammar, parserGrammar);
		var characterIndexes = getSortedCharacterIndexes();
		var index2character = getSortedCharacterIndexes(punctChars, characterRanges);
		'''
				public final static int tokenKind[] =
				{
					«FOR i : characterIndexes»
						$sym_type.$prefix$«emitLabel(index2character.get(Integer.valueOf(i)), "Default")»$suffix$,	// «i as int» 0x«Integer.toHexString(i)»
					«ENDFOR»

					$sym_type.$prefix$AfterASCII$suffix$,	  // for all chars in range 128..65534
					$sym_type.$prefix$EOF$suffix$			  // for '\uffff' or 65535 
				};
		'''
	}

	protected def String generateParser(/*@NonNull*/ ParserGrammar parserGrammar) {
		var syntax = parserGrammar.getSyntax();
		var syntaxName = emitSyntaxName(syntax);
		'''
		%options escape=$
		%options la=1
		%options fp=«syntaxName»Parser,prefix=TK_
		%options noserialize
		%options package=«emitSyntaxPackage(syntax)»
		%options import_terminals=«syntaxName»Lexer.gi
		%options ast_type=XMLResource
		%options template=dtParserTemplateF.gi
		%options include_directory=".;../lpg"
		
		%Start
		«FOR rule : parserGrammar.goals»
			«rule.name»
		«ENDFOR»
		%End
				
		%Define
		    -- Redefinition of macros used in the parser template
		    --
		    $default_repair_count /.getDefaultRepairCount()./
			$super_parser_class /.AbstractEcore2XtextParser./
		%End
		
		%Notice
			/./**
		 *******************************************************************************/
			./
		%End
		
		%Globals
			/.
			/* imports */
			import org.eclipse.emf.ecore.*;
			import org.eclipse.emf.ecore.xmi.XMLResource;
			./
		%End
		
		--%KeyWords
		-- Reserved keywords
		--	body context def derive endpackage init inv package post pre static
		
		-- Restricted keywords
		--	OclMessage
		--%End
		
		%Terminals
			«FOR terminalRule : getSortedTerminalRules(syntax)»
				«terminalRule.name»
			«ENDFOR»
			
			«FOR keywordValue : getSortedNonIdentifierKWValues(parserGrammar)»
				«emitLabel(keywordValue)» ::= '«keywordValue»'
			«ENDFOR»
		%End
		
		%Rules
		«FOR parserRule : getSortedParserRules(parserGrammar) SEPARATOR '\n'»
			«FOR rule : selectRules(parserGrammar.rules, parserRule.name)»
				«generateParserRule(rule)»
			«ENDFOR»
		«ENDFOR»
		%End
		'''
	}

	protected def String generateParserRule(TypedRule rule) {
		'''
		«generateParserRuleDisjunction(rule)»
		«FOR subrule : getSortedSubRules(rule.subrules)»
			«generateParserSubruleDisjunction(subrule)»
		«ENDFOR»
		'''
	}

	protected def String generateParserRuleDisjunction(AbstractRule rule) {
		'''
		«FOR conjunction : getSortedConjunctions(rule.element as Disjunction)»
		«rule.name» ::=«IF conjunction.elements.isEmpty()» %empty«ELSE»«FOR element : conjunction.elements» «generateTerm(element)»«ENDFOR»«ENDIF» --«nextState()»
				/.$BeginAction
							setResult(«emitCreateEObject(conjunction)»);
				  $EndAction
				./
		«ENDFOR»
		'''
	}

	protected def String generateParserSubruleDisjunction(AbstractRule rule) {
		'''
		«FOR conjunction : getSortedConjunctions(rule.element as Disjunction)»
		«rule.name» ::=«IF conjunction.elements.isEmpty()» %empty«ELSE»«FOR element : conjunction.elements» «generateTerm(element)»«ENDFOR»«ENDIF» --«nextState()»
				/.$BeginAction
							setResult(«emitContinueEObject(conjunction)»);
				  $EndAction
				./
		«ENDFOR»
		'''
	}

	protected def String generateTerm(AbstractElement element) {
		switch element {
			ActionAssignment: return ""
			CharacterRange: return element.getDebug()
			Epsilon: return "%empty"
			RuleCallAssignment: return element.referredRule.name
			RuleCall: return element.referredRule.name
			KeywordAssignment: return emitLabel(element.value)
			Keyword: return emitLabel(element.value)
			UntilToken: return "UNTIL " + generateTerm(element.terminal)
			Wildcard: return '.'
			default: return "<<<" + element.eClass.name + ">>>"
		}		
	}
}
