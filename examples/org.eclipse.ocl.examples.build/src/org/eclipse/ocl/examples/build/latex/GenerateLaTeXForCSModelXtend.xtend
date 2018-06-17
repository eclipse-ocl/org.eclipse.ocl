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

import org.eclipse.ocl.pivot.Class
import org.eclipse.ocl.pivot.Element
import org.eclipse.ocl.pivot.Namespace
import org.eclipse.ocl.pivot.Package
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
import org.eclipse.ocl.pivot.utilities.ClassUtil
import org.eclipse.ocl.pivot.utilities.NameUtil

 class GenerateLaTeXForCSModelXtend extends GenerateLaTeXForCSModel
{
	/*@NonNull*/ protected override String generateLaTeX(/*@NonNull*/ Package asPackage,
		/*@NonNull*/ Grammar grammar, /*@Nullable*/ Package cs2asPackage,
		/*@Nullable*/ Package cs2csPackage) {
		'''
		«emitClasses(asPackage, grammar, cs2asPackage, cs2csPackage)»

		«emitExternalModels(grammar)»
		
		«emitTerminalRules(grammar)»
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
			RuleCall: return emitRuleRef(abstractElement.rule) + emitCardinality(abstractElement)
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
			return ""; // "\n{" + emitTypeRef(action.type) + "}";
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

	protected def emitAssociations(/*@NonNull*/ Class asClass) {
		var asAssociations = getSortedAssociations(asClass);
		if (asAssociations.size() > 0) {
		'''

		«emitHeading0a("Associations")»
		«FOR asAssociation : asAssociations»

			«emitHeading0b(prettyPrint(asAssociation, asClass))»
			«emitComment(asAssociation, asClass)»
		«ENDFOR»
		'''
		}
	}

	protected def emitAttributes(/*@NonNull*/ Class asClass) {
		var asAttributes = getSortedAttributes(asClass);
		if ( asAttributes.size() > 0) {
		'''

		«emitHeading0a("Attributes")»
		«FOR asAttribute : asAttributes»

			«emitHeading0b(prettyPrint(asAttribute, asClass))»
			«emitComment(asAttribute, asClass)»
		«ENDFOR»
		'''
		}
	}

	protected def emitCS2AS(/*@NonNull*/ Class asClass, /*@NonNull*/ Package cs2asPackage) {
		var cs2asClass = NameUtil.getNameable(cs2asPackage.getOwnedClasses(), asClass.getName());
		if (cs2asClass !== null)  {
		'''

		«emitHeading0a("Abstract Syntax Synthesis")»
		«FOR asOperation : ClassUtil.nonNullState(cs2asClass).getOwnedOperations()»

			«emitHeading0b(prettyPrint(asOperation, cs2asClass))»
			«emitComment(asOperation, asClass)»
			«IF asOperation.bodyExpression !== null»
				«emitAllTT((asOperation.bodyExpression.getBody().trim()).replace("\n", "\n  "))»
			«ENDIF»
		«ENDFOR»
		'''
		}
	}

	protected def emitCS2CS(/*@NonNull*/ Class asClass, /*@NonNull*/ Package cs2asPackage) {
		var cs2csClass = NameUtil.getNameable(cs2asPackage.getOwnedClasses(), asClass.getName());
		if (cs2csClass !== null)  {
		'''

		«emitHeading0a("Concrete Syntax Disambiguation")»
		«FOR asOperation : ClassUtil.nonNullState(cs2csClass).getOwnedOperations()»

			«emitHeading0b(prettyPrint(asOperation, cs2csClass))»
			«emitComment(asOperation, asClass)»
			«IF asOperation.bodyExpression !== null»
				«emitAllTT((asOperation.bodyExpression.getBody().trim()).replace("\n", "\n  "))»
			«ENDIF»
		«ENDFOR»
		'''
		}
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

	protected def emitClasses(/*@NonNull*/ Package asPackage,
		/*@NonNull*/ Grammar grammar, /*@Nullable*/ Package cs2asPackage,
		/*@Nullable*/ Package cs2csPackage) {
		var asClasses = getSortedClasses(asPackage);
		'''
		«FOR asClass : asClasses»
			
			«emitHeading3(prettyPrint(asClass, asClass), null) + emitClassDef(asClass)»
			«emitComment(asClass, asClass)»
			«emitSuperclasses(asClass)»
			«emitAttributes(asClass)»
			«emitAssociations(asClass)»
			«emitOperations(asClass)»
			«emitParserRules(asClass, grammar)»
			«IF cs2csPackage !== null»
			«emitCS2CS(asClass, ClassUtil.nonNullState(cs2csPackage))»
			«ENDIF»
			«IF cs2asPackage !== null»
			«emitCS2AS(asClass, ClassUtil.nonNullState(cs2asPackage))»
			«ENDIF»
		«ENDFOR»
		'''
	}

	protected def emitComment(/*@NonNull*/ Element asElement, /*@NonNull*/ Namespace asNamespace) {
		if (asElement.getOwnedComments().size() > 0) {
		'''
			«FOR asComment : asElement.getOwnedComments()»
			
			«prettyPrint(asComment, asNamespace)»
			«ENDFOR»
		'''
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

	protected def emitOperations(/*@NonNull*/ Class asClass) {
		var asOperations = getSortedOperations(asClass);
		if (asOperations.size() > 0) {
		'''

		«emitHeading0a("Operations")»
		«FOR asOperation : asOperations»

			«emitHeading0b(prettyPrint(asOperation, asClass) /*+ (asOperation.isInvalidating ? " invalidating" : "") + (asOperation.isValidating ? " validating" : "")*/)»
			«IF asOperation.precedence !== null»
			
				precedence: «emitHeading0b(asOperation.precedence.name)»
			«ENDIF»
			«emitComment(asOperation, asClass)»
			«FOR asConstraint : getSortedPreconditions(asOperation)»
				«emitBeginDefinition»
				«prettyPrint(asConstraint, asClass)»
				«emitEndDefinition»
			«ENDFOR»
			«IF asOperation.bodyExpression !== null»
				«emitBeginDefinition»
				body: «asOperation.bodyExpression.getBody()»
				«emitEndDefinition»
			«ENDIF»
			«FOR asConstraint : getSortedPostconditions(asOperation)»
				«emitBeginDefinition»
				«prettyPrint(asConstraint, asClass)»
				«emitEndDefinition»
			«ENDFOR»
		«ENDFOR»
		'''
		}
	}
	
/*	protected def String emitParserRule(@NonNull org.eclipse.ocl.pivot.Class asClass, @NonNull Collection<ParserRule> parserRules) {
		'''
		«asClass.name» ::=
		  «FOR parserRule : parserRules BEFORE " " SEPARATOR "\n|"» «parserRule.name»«ENDFOR»
		'''
	} */
	
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
	
	protected def emitParserRules(/*@NonNull*/ Class asClass, /*@NonNull*/ Grammar grammar) {
		var parserRules = getSortedParserRules(asClass, grammar);
		if (parserRules.size() > 0) {
		'''
		«IF parserRules.size() > 1»
		«emitHeading0a("Parser Rules")»
		«ELSE»
		«emitHeading0a("Parser Rule")»
		«ENDIF»
		«FOR parserRule : parserRules»
		«IF parserRules.size() > 1»
		«emitHeading0b(parserRule.name)»
		«ENDIF»
		«emitComment(parserRule)»
		«emitAllTT((emitRuleDef(parserRule) + " ::=\n" + emitAbstractElement(parserRule.alternatives, true).trim()).replace("\n", "\n  "))»
		«ENDFOR»
		'''
		}
	}

	protected def emitSuperclasses(/*@NonNull*/ Class asClass) {
		var asSuperClasses = asClass.getSuperClasses();
		if (asSuperClasses.size() > 0) {
		'''
		«emitHeading0a("Superclasses")»
			
			«FOR asSuperClass : asSuperClasses SEPARATOR ', '»«emitClassRef(asSuperClass)»«ENDFOR»
		'''
		}
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
