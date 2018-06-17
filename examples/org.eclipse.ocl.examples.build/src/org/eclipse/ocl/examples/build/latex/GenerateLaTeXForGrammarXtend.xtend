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
package org.eclipse.ocl.examples.build.latex

import org.eclipse.xtext.AbstractElement
import org.eclipse.xtext.AbstractMetamodelDeclaration
import org.eclipse.xtext.Action
import org.eclipse.xtext.Alternatives
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.CharacterRange
import org.eclipse.xtext.CrossReference
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.Group
import org.eclipse.xtext.Keyword
import org.eclipse.xtext.NegatedToken
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.TypeRef
import org.eclipse.xtext.UntilToken
import org.eclipse.xtext.Wildcard

 class GenerateLaTeXForGrammarXtend extends GenerateLaTeXForGrammar
{
	/*@NonNull*/ protected override String generateLaTeX(/*@NonNull*/ Grammar grammar) {
		'''
		«emitExternalModels(grammar)»
		
		«emitTerminalRules(grammar)»
		
		«emitParserRules(grammar)»
		'''
	}
	
	protected def emitExternalModels(/*@NonNull*/ Grammar grammar) {
		var metamodelDeclarations = getSortedMetamodelDeclarations(grammar);
		'''
		«emitHeading3("External Models", labelPrefix + "ExternalModels")»
		
		The following aliases and metamodel URIs are used in the grammar.

		«FOR metamodelDeclaration : metamodelDeclarations»		
		«emitMetamodelDeclaration(metamodelDeclaration)»
		«ENDFOR»
		'''
	}
	
	protected def emitParserRules(/*@NonNull*/ Grammar grammar) {
		var parserRules = getSortedParserRules(grammar);
		'''
		«emitHeading3("Parser Rules", labelPrefix + "ParserRules")»
		
		The parser rules define the parser mapping from parser tokens to generalized concrete syntax elements.
		«FOR parserRule : parserRules»

		«emitHeading4(parserRule.name, labelPrefix + "ParserRule:" + parserRule.name)»
		«emitComment(parserRule)»
		Token type: «emitTypeRef(parserRule.type)»
		«emitAllTT(emitAbstractElement(parserRule.alternatives, true))»
		«ENDFOR»
		'''
	}
	
	protected def emitTerminalRules(/*@NonNull*/ Grammar grammar) {
		var terminalRules = getSortedTerminalRules(grammar);
		'''
		«emitHeading3("Terminal Rules", labelPrefix + "TerminalRules")»
		
		The terminal rules define the lexer mapping from character sequences to parser tokens.
		«FOR terminalRule : terminalRules»

		«emitHeading4(terminalRule.name, labelPrefix + "TerminalRule:" + terminalRule.name)»
		«emitComment(terminalRule)»
		Token type: «emitTypeRef(terminalRule.type)»
		«emitAllTT(emitAbstractElement(terminalRule.alternatives, true))»
		«ENDFOR»
		'''
	}
	
	protected def String emitAbstractElement(/*@NonNull*/ AbstractElement abstractElement, boolean atRoot) {
		switch abstractElement {
			Action: return emitAction(abstractElement)
			Alternatives: return emitAlternatives(abstractElement, atRoot)
			Assignment: return emitAssignment(abstractElement)
			CharacterRange: return emitCharacterRange(abstractElement)
			CrossReference: return emitCrossReference(abstractElement)
			Group: return emitGroup(abstractElement, atRoot)
			Keyword: return emitKeyword(abstractElement)
			NegatedToken: return '!' + emitAbstractElement(abstractElement.terminal, false) + emitCardinality(abstractElement)
			RuleCall: return abstractElement.rule.name + emitCardinality(abstractElement)
			UntilToken: return '-> ' + emitAbstractElement(abstractElement.terminal, false)
			Wildcard: return '.' + emitCardinality(abstractElement)
			default: return "<<<" + abstractElement.eClass.name + ">>>"
		}		
	}
	
	protected def String emitAction(/*@NonNull*/ Action action) {
		if (action.feature !== null) {
			return "\n{" + emitTypeRef(action.type) + action.feature + action.operator + "current}";
		}
		else {
			return "\n{" + emitTypeRef(action.type) + "}";
		}
	}
	
	protected def String emitAlternatives(/*@NonNull*/ Alternatives alternatives, boolean atRoot) {
		if (atRoot && (alternatives.cardinality === null)) {
		'''«FOR element : alternatives.elements SEPARATOR '\n| '»«emitAbstractElement(element, false)»«ENDFOR»'''
		}
		else {
		'''«FOR element : alternatives.elements BEFORE '(' SEPARATOR '\n| ' AFTER ')'»«emitAbstractElement(element, false)»«ENDFOR»«alternatives.cardinality»'''
		}
	}
	
	protected def String emitAssignment(/*@NonNull*/ Assignment assignment) {
		return assignment.feature + assignment.operator + emitAbstractElement(assignment.terminal, false) + emitCardinality(assignment) + "\n";
	}
	
	protected def String emitCardinality(/*@NonNull*/ AbstractElement abstractElement) {
		if (abstractElement.cardinality !== null) {
			return abstractElement.cardinality;
		}
		else {
			return "";
		}
	}
	
	protected def String emitCharacterRange(/*@NonNull*/ CharacterRange characterRange) {
		if (characterRange.cardinality === null) {
			return emitKeyword(characterRange.left) + '..' + emitKeyword(characterRange.right)
		}
		else {
			return "(" + emitKeyword(characterRange.left) + '..' + emitKeyword(characterRange.right) + ")" + characterRange.cardinality
		}
	}
	
	protected def String emitCrossReference(/*@NonNull*/ CrossReference crossReference) {
		return "[" + emitTypeRef(crossReference.type) + '|' + emitAbstractElement(crossReference.terminal, false) + "]" + emitCardinality(crossReference)
	}
	
	protected def String emitGroup(/*@NonNull*/ Group group, boolean atRoot) {
		if (atRoot && (group.cardinality === null)) {
		'''«FOR element : group.elements SEPARATOR ' '»«emitAbstractElement(element, false)»«ENDFOR»'''
		}
		else {
		'''«FOR element : group.elements BEFORE '(' SEPARATOR ' ' AFTER ')'»«emitAbstractElement(element, false)»«ENDFOR»«group.cardinality»'''
		}
	}
	
	protected def String emitKeyword(/*@NonNull*/ Keyword keyword) {
		return "'" + emitCharacters(keyword.value) + "'" + emitCardinality(keyword)
	}
	
	protected def emitMetamodelDeclaration(/*@NonNull*/ AbstractMetamodelDeclaration metamodelDeclaration) {
		if (metamodelDeclaration.alias === null) {
			return metamodelDeclaration.EPackage.nsURI + " (default)";
		}
		else {
			return metamodelDeclaration.alias + " : " + metamodelDeclaration.EPackage.nsURI;
		}
	}
	
	protected def emitTypeRef(/*@NonNull*/ TypeRef typeRef) {
		if ((typeRef.metamodel.alias == "ecore") && (typeRef.classifier.name == "EString")) {
			return "String";
		}
		else if ((typeRef.metamodel.alias == "ecore") && (typeRef.classifier.name == "EInt")) {
			return "Integer";
		}
		else if (typeRef.metamodel.alias === null) {
			return typeRef.classifier.name;
		}
		else {
			return typeRef.metamodel.alias + "::" + typeRef.classifier.name;
		}
	}
}
