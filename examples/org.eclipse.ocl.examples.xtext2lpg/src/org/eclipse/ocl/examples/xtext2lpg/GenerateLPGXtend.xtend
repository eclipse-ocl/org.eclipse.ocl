/*******************************************************************************
 * Copyright (c) 2014 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     E.D.Willink - initial API and implementation
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
		var keywordValues = getSortedKWValues(parserGrammar);
		'''
		%options slr
		%options fp=«syntaxName»KWLexer,prefix=Char_
		%options noserialize
		%options package=«emitSyntaxPackage(lexerGrammar.syntax)»
		%options template=../lpg/KeywordTemplateF.gi
		%options export_terminals=("«syntaxName»Parsersym.java", "TK_")
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
		var punctValues = getSortedPunctValues(parserGrammar);
		var terminalRules = getSortedTerminalRules(syntax);
		var punctChars = getSortedPunctChars(lexerGrammar);
		var characterRanges = getSortedCharacterRanges(lexerGrammar);
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
		
		%Define
		
			--
			-- Definition of macro used in the included file LexerBasicMap.g
			-- We redefine that one defined by EssentialOCLLexer
			--
			$kw_lexer_class /.«syntaxName»KWLexer./
		
		%End
		
		%Export
			«FOR terminalRule : terminalRules»
				«terminalRule.name»
			«ENDFOR»
		
			«FOR keywordValue : punctValues»
				«emitLabel(keywordValue)»
			«ENDFOR»
		%End
		
		%Terminals
			«FOR characterRange : characterRanges»
				«FOR c : getCharacters(characterRange) SEPARATOR ' '»«c»«ENDFOR»

			«ENDFOR»
			«FOR c : punctChars»
				«emitLabel(c)» ::= «emitQuotedCharacter(c)»
			«ENDFOR»
		%End
		
		%Start
			Token
		%End
		
		%Rules
			«FOR keywordValue : punctValues»
			Token ::= «FOR c : keywordValue.toCharArray() SEPARATOR ' '»'«c»'«ENDFOR»
				/.$BeginAction
							makeToken($_«emitLabel(keywordValue)»);
				  $EndAction
				./

			«ENDFOR»
			«FOR characterRange : characterRanges»
			«characterRange.getDebug()» -> «FOR c : getCharacters(characterRange) SEPARATOR ' | '»'«c»'«ENDFOR»

			«ENDFOR»
			«FOR terminalRule : terminalRules»
			«generateTerminalRule(terminalRule)»

			«ENDFOR»
		%End
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
		%options ast_type=CSTNode
		%options template=dtParserTemplateF.gi
		%options include_directory=".;../lpg"
		
		%Start
		«FOR rule : parserGrammar.goals»
			«rule.name»
		«ENDFOR»
		%End
		
		%Notice
			/./**
		 *******************************************************************************/
			./
		%End
		
		%Globals
			/.
			/* imports */
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
			
			«FOR keywordValue : getSortedPunctValues(parserGrammar)»
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
		«generateParserDisjunction(rule)»
		«FOR subrule : getSortedSubRules(rule.subrules)»
			«generateParserDisjunction(subrule)»
		«ENDFOR»
		'''
	}

	protected def String generateTerminalRule(TypedRule rule) {
		'''
		«generateLexerDisjunction(rule)»
		«FOR subrule : getSortedSubRules(rule.subrules)»
			«generateLexerDisjunction(subrule)»
		«ENDFOR»
		'''
	}

	protected def String generateLexerDisjunction(AbstractRule rule) {
		'''
		«FOR conjunction : getSortedConjunctions(rule.element as Disjunction)»
		«rule.name» ::=«IF conjunction.elements.isEmpty()» %empty«ELSE»«FOR element : conjunction.elements» «generateTerm(element)»«ENDFOR»«ENDIF»
			/.$BeginAction
							makeToken($_«rule.name»);
			  $EndAction
			./
		«ENDFOR»
		'''
	}

	protected def String generateParserDisjunction(AbstractRule rule) {
		'''
		«FOR conjunction : getSortedConjunctions(rule.element as Disjunction)»
		«rule.name» ::=«IF conjunction.elements.isEmpty()» %empty«ELSE»«FOR element : conjunction.elements» «generateTerm(element)»«ENDFOR»«ENDIF» --«nextState()»
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
