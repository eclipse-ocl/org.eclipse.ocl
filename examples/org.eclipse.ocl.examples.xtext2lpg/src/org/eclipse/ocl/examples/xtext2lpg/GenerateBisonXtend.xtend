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
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EReference

class GenerateBisonXtend extends GenerateBisonUtils
{
	/*@NonNull*/ protected override String generateBisonModel(/*@NonNull*/ Syntax syntax) {
		return generateModel(getLexerGrammar(syntax), getParserGrammar(syntax))
	}
	/*@NonNull*/ protected override String generateBisonLexer(/*@NonNull*/ Syntax syntax) {
		return generateLexer(getLexerGrammar(syntax), getParserGrammar(syntax));
	}
	
	/*@NonNull*/ protected override String generateBisonParser(/*@NonNull*/ Syntax syntax) {
		return generateParser(getParserGrammar(syntax));
	}

 	protected def String generateLexer(/*@NonNull*/ LexerGrammar lexerGrammar, /*@NonNull*/ ParserGrammar parserGrammar) {
		var syntax = lexerGrammar.getSyntax();
		var syntaxName = emitSyntaxName(syntax);
		'''
		%{
		#include <«syntaxName».h>
		#include <«syntaxName».tab.h>
		#include <stdlib.h>
		#include <string.h>
		%}
		
		white [ \t]+
		
		digit							[0-9]
		hex								[0-9A-Fa-f]
		letter							[A-Z_a-z]
		identifier						({letter}({letter}|{digit})*)
		string							(\"([^\"\\\n]|\\.)*\")
		
		%%
		
		{white} { }

		«FOR keywordValue : getSortedIdentifierKWValues(parserGrammar)»
		"«keywordValue»" return KW_«emitLabel(keywordValue)»;
		«ENDFOR»
		
		«FOR keywordValue : getSortedNonIdentifierKWValues(parserGrammar)»
		"«keywordValue»" return KW_«emitLabel(keywordValue)»;
		«ENDFOR»

		{identifier} { yylval.identifier=yytext; 
		 return IDENTIFIER;
		}

		{string} { yylval.string=yytext/*substr(yytext+1, yyleng-2)*/; 
		 return STRING;
		}
		'''
	}
	
 	protected def String generateModel(/*@NonNull*/ LexerGrammar lexerGrammar, /*@NonNull*/ ParserGrammar parserGrammar) {
		var keywordValues = getSortedIdentifierKWValues(parserGrammar);
		var keywordCharacters = getSortedCharacters(keywordValues);
		var keywordAlphaNumericCharacters = getAlphaNumericCharacters(keywordCharacters);
		var keywordPunctuationCharacters = getPunctuationCharacters(keywordCharacters);
		'''
		#define YYSTYPE_IS_DECLARED
		#define null (void *)0

		typedef enum boolean { false, true } boolean;
		
		void **appendToList(void **, void *);
		void **appendToSet(void **, void *);
		void **appendToMap(void **, void *);
		boolean booleanValueOf(const char *);
		void **createMap();
		void **createMap1(void *);
		void **createSet();
		void **createSet1(void *);
		
		void *createXMIAttribute();
		void *createXMLNSAttribute();
		void *createXSIAttribute();
		void *createEObject();
		
		«FOR modelType : getSortedModelTypes(parserGrammar)»
		«generateModelTypeDeclarations(modelType)»
		«ENDFOR»
		
		«FOR modelType : getSortedModelTypes(parserGrammar) SEPARATOR '\n'»
		«generateModelTypeDefinitions(modelType)»
		«ENDFOR»
		
		typedef struct EResource {
			EPackage *s_body;
			const char *s_encoding;
			const char *s_version;
		} EResource;
		
		typedef union {
			void *pointer;
			EResource *resource;
			const char *identifier;
			void **list;
			const char *string;
			«FOR eClass : getSortedModelTypes(parserGrammar)»
			«eClass.name» *u_«eClass.name»;
			«ENDFOR»
		} YYSTYPE;
		'''
	}

	protected def String generateModelFeature(EStructuralFeature eFeature) {
		'''
		«IF eFeature instanceof EReference»
			«IF eFeature.isMany()»
				struct «eFeature.getEType().name» **s_«eFeature.name»;
			«ELSE»
				struct «eFeature.getEType().name» *s_«eFeature.name»;
			«ENDIF»
		«ELSE»
			«IF eFeature.isMany()»
				«emitDataTypeName(eFeature.getEType())»*s_«eFeature.name»;
			«ELSE»
				«emitDataTypeName(eFeature.getEType())»s_«eFeature.name»;
			«ENDIF»
		«ENDIF»
		'''
	}

	protected def String generateModelType(EClass eClass) {
		'''
		typedef struct «eClass.name» {
			«FOR eFeature : getSortedModelFeatures(eClass)»
			«generateModelFeature(eFeature)»
			«ENDFOR»
		} «eClass.name»;
		'''
	}

	protected def String generateModelTypeDeclarations(EClass eClass) {
		'''
		struct «eClass.name»;
		'''
	}

	protected def String generateModelTypeDefinitions(EClass eClass) {
		'''
		typedef struct «eClass.name» {
			«FOR eFeature : getSortedModelFeatures(eClass)»
			«generateModelFeature(eFeature)»
			«ENDFOR»
		} «eClass.name»;
		'''
	}

	protected def String generateParser(/*@NonNull*/ ParserGrammar parserGrammar) {
		var syntax = parserGrammar.getSyntax();
		var syntaxName = emitSyntaxName(syntax);
		'''
		%{
		#include <math.h>
		#include <stdio.h>
		#include <stdlib.h>
		#include <«syntaxName».h>

		extern FILE *yyin;
		%}
		
		«FOR keywordValue : getSortedIdentifierKWValues(parserGrammar)»
		%token KW_«emitLabel(keywordValue)»
		«ENDFOR»
		
		«FOR keywordValue : getSortedNonIdentifierKWValues(parserGrammar)»
		%token KW_«emitLabel(keywordValue)»
		«ENDFOR»

		%token IDENTIFIER
		%token STRING

		«FOR rule : parserGrammar.goals»
		%start «rule.name»
		«ENDFOR»
		%%

		«FOR parserRule : getSortedParserRules(parserGrammar) SEPARATOR '\n'»
			«FOR rule : selectRules(parserGrammar.rules, parserRule.name)»
				«generateParserRule(rule)»
			«ENDFOR»
		«ENDFOR»
	
		%%
		
		int yyerror(char *s) {
		  printf("%s\n", s);
		  return 0;
		}

		int main(int argc, char *argv[]) {
			if (argc > 0) {
				yyin = fopen(*++argv, "r");
		}
		  int status = yyparse();
		     fprintf(stderr, "yyparse() => %d\n", status);		  
		  if (status == 0)
		     fprintf(stderr, "Successful parsing.\n");
		  else
		     fprintf(stderr, "error found.\n");
		}
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
		«rule.name» : «IF conjunction.elements.isEmpty()» %empty«ELSE»«FOR element : conjunction.elements» «generateTerm(element)»«ENDFOR»«ENDIF» //«nextState()»
		{
			«emitCreateEObject(conjunction)»
		}
		«ENDFOR»
		'''
	}

	protected def String generateParserSubruleDisjunction(AbstractRule rule) {
		'''
		«FOR conjunction : getSortedConjunctions(rule.element as Disjunction)»
		«rule.name» : «IF conjunction.elements.isEmpty()» %empty«ELSE»«FOR element : conjunction.elements» «generateTerm(element)»«ENDFOR»«ENDIF» //«nextState()»
		{
			«emitContinueEObject(conjunction)»
		}
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
			Keyword: return "KW_" + emitLabel(element.value)
			UntilToken: return "UNTIL " + generateTerm(element.terminal)
			Wildcard: return '.'
			default: return "<<<" + element.eClass.name + ">>>"
		}		
	}
}
