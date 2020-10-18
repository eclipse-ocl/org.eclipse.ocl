/*******************************************************************************
 * Copyright (c) 2015, 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend

import com.google.inject.Inject
import com.google.inject.Provider
import com.google.inject.Singleton
import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.jdt.annotation.NonNull
import org.eclipse.ocl.xtext.base.utilities.AbstractGrammarResource
import org.eclipse.xtext.AbstractElement
import org.eclipse.xtext.AbstractMetamodelDeclaration
import org.eclipse.xtext.AbstractRule
import org.eclipse.xtext.Action
import org.eclipse.xtext.Alternatives
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.CharacterRange
import org.eclipse.xtext.CrossReference
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.Group
import org.eclipse.xtext.Keyword
import org.eclipse.xtext.NegatedToken
import org.eclipse.xtext.ParserRule
import org.eclipse.xtext.ReferencedMetamodel
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.TerminalRule
import org.eclipse.xtext.TypeRef
import org.eclipse.xtext.UntilToken
import org.eclipse.xtext.Wildcard
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.EnumRule
import org.eclipse.xtext.EnumLiteralDeclaration
import org.eclipse.emf.ecore.EEnumLiteral
import org.eclipse.xtext.UnorderedGroup
import java.util.GregorianCalendar

 class GenerateGrammarXtend extends GenerateGrammar
{
	/*@NonNull*/ protected override String generate(/*@NonNull*/ Resource grammarResource) {
		var year = new GregorianCalendar().get(GregorianCalendar.YEAR);
		var boolean hasEnumRules = hasRules(grammarResource, EnumRule);
		'''
			/*******************************************************************************
			 * Copyright (c) 2015, «year» Willink Transformations and others.
			 * All rights reserved. This program and the accompanying materials
			 * are made available under the terms of the Eclipse Public License v2.0
			 * which accompanies this distribution, and is available at
			 * http://www.eclipse.org/legal/epl-v20.html
			 *
			 * Contributors:
			 *     E.D.Willink - initial API and implementation
			 *******************************************************************************
			 * This code is 100% auto-generated
			 * from: «sourceFile»
			 * by: org.eclipse.ocl.examples.build.xtend.generateGrammar.xtend
			 *
			 * Do not edit it.
			 *******************************************************************************/
			package	«javaPackageName»;
			
			import «List.getName()»;
			
			import «URI.getName()»;
			import «NonNull.getName()»;
			import «EObject.getName()»;
			import «AbstractGrammarResource.getName()»;
			import «AbstractMetamodelDeclaration.getName()»;
			import «AbstractRule.getName()»;
			«IF hasEnumRules»
			import «EnumRule.getName()»;
			«ENDIF»
			import «Grammar.getName()»;
			import «ParserRule.getName()»;
			import «ReferencedMetamodel.getName()»;
			import «TerminalRule.getName()»;
			import «XtextResourceSet.getName()»;

			import «Inject.getName()»;
			import «Provider.getName()»;
			import «Singleton.getName()»;
			
			/**
			 * «grammarFileStem»GrammarResource provides a programmatically initialized «languageName» Grammar model avoiding
			 * the speed limitations of the pre-Xtext 2.4 *.xmi models and the binary incompatibilities between differing *.xtextbin versions.
			 * <p>
			 * The grammar is immutable and is available as static INSTANCE and GRAMMAR fields.
			 */
			public class «grammarFileStem»GrammarResource extends AbstractGrammarResource
			{
				«FOR grammar : grammarResource.getContents()»
				private static final @NonNull Grammar «emit(grammar as Grammar, grammar as Grammar)» = createGrammar(«emitValue((grammar as Grammar).getName())»);
				«ENDFOR»
			
				/**
				 *	The shared immutable instance of the «languageName» Grammar resource.
				 */
				public static final @NonNull «grammarFileStem»GrammarResource INSTANCE = new «grammarFileStem»GrammarResource();
			
				/**
				 *	The shared immutable instance of the «languageName» Grammar model.
				 */
				public static final @NonNull Grammar GRAMMAR = (Grammar)INSTANCE.getContents().get(0);
			
				/**
				 *	The name of the language supported by this grammar.
				 */
				public static final @NonNull String LANGUAGE_NAME = "«languageName»";

				protected «grammarFileStem»GrammarResource() {
					super(URI.createURI(LANGUAGE_NAME));
					List<EObject> contents = getContents();
					«FOR grammar : grammarResource.getContents()»
					contents.add(«getGrammarPackageName(grammar as Grammar)».initGrammar());
					«ENDFOR»
				}

				/*
				 * This class should be bound to org.eclipse.xtext.service.GrammarProvider.
				 */ 
				@Singleton
				public static class GrammarProvider extends org.eclipse.xtext.service.GrammarProvider
				{
					@Inject
					public GrammarProvider(Provider<XtextResourceSet> resourceSetProvider) {
						super(LANGUAGE_NAME, resourceSetProvider);
					}
			
					@Override
					public Grammar getGrammar(Object requestor) {
						return «grammarFileStem»GrammarResource.GRAMMAR;
					}
				}
				«FOR grammar : grammarResource.getContents()»
				«generateGrammarPackage(grammar as Grammar)»
				«ENDFOR»
			}
		'''
	}
	
	/*@NonNull*/ protected def String generateEnumRules(/*@NonNull*/ Grammar grammar, /*@NonNull*/ Iterable<EnumRule> eObjects) {
		'''
		«FOR eObject : eObjects»
		private static final @NonNull EnumRule ER_«eObject.getName()» = createEnumRule(«emitValue(eObject.getName())», «emit(grammar, eObject.getType())»);
		«ENDFOR»

		private static void initEnumRules() {
			«FOR eObject : eObjects»
			«emit(grammar, eObject)».setAlternatives(«pushIndent»«emitIndent»«emit(grammar, eObject.alternatives)»«popIndent»);
			«ENDFOR»
		}

		'''
	}
	
	/*@NonNull*/ protected def String generateGrammarPackage(/*@NonNull*/ Grammar grammar) {
		var List<TerminalRule> terminalRules = getSortedAbstractRules(grammar, TerminalRule);
		var List<EnumRule> enumRules = getSortedAbstractRules(grammar, EnumRule);
		'''
		
		private static class «getGrammarPackageName(grammar)»
		{
			«generateReferencedMetamodels(grammar, getSortedReferencedMetamodels(grammar))»
			«IF terminalRules.size() > 0»
			«generateTerminalRules(grammar, terminalRules)»
			«ENDIF»
			«IF enumRules.size() > 0»
			«generateEnumRules(grammar, enumRules)»
			«ENDIF»
			«generateParserRules(grammar, getSortedAbstractRules(grammar, ParserRule))»
			«generateInitGrammar(grammar, terminalRules.size() > 0, enumRules.size() > 0)»
		}
		'''
	}
	
	/*@NonNull*/ protected def String generateInitGrammar(/*@NonNull*/ Grammar grammar, boolean hasTerminals, boolean hasEnumRules) {
		'''
		private static @NonNull Grammar initGrammar() {
			«IF hasTerminals»
			initTerminalRules();
			«ENDIF»
			«IF hasEnumRules»
			initEnumRules();
			«ENDIF»
			initParserRules();
			Grammar grammar = «emit(grammar, grammar)»;
			«IF grammar.isDefinesHiddenTokens()»
			grammar.setDefinesHiddenTokens(true);
			«ENDIF»
			«IF grammar.getMetamodelDeclarations().size() > 0»
			{
				List<AbstractMetamodelDeclaration> metamodelDeclarations = grammar.getMetamodelDeclarations();
				«FOR element : grammar.getMetamodelDeclarations()»
					metamodelDeclarations.add(«emit(grammar, element)»);
				«ENDFOR»
			}
			«ENDIF»
			«IF grammar.getRules().size() > 0»
			{
				List<AbstractRule> rules = grammar.getRules();
				«FOR element : grammar.getRules()»
					rules.add(«emit(grammar, element)»);
				«ENDFOR»
			}
			«ENDIF»
			«IF grammar.getUsedGrammars().size() > 0»
			{
				List<Grammar> usedGrammars = grammar.getUsedGrammars();
				«FOR element : grammar.getUsedGrammars()»
					usedGrammars.add(«emit(grammar, element)»);
				«ENDFOR»
			}
			«ENDIF»
			«IF grammar.getHiddenTokens().size() > 0»
			{
				List<AbstractRule> hiddenTokens = grammar.getHiddenTokens();
				«FOR element : grammar.getHiddenTokens()»
					hiddenTokens.add(«emit(grammar, element)»);
				«ENDFOR»
			}
			«ENDIF»
			return grammar;
		}
		'''
	}
	
	/*@NonNull*/ protected def String generateParserRules(/*@NonNull*/ Grammar grammar, /*@NonNull*/ Iterable<ParserRule> eObjects) {
		'''
		«FOR eObject : eObjects»
		private static final @NonNull ParserRule PR_«eObject.getName()» = createParserRule(«emitValue(eObject.getName())», «emit(grammar, eObject.getType())»);
		«ENDFOR»

		private static void initParserRules() {
			«FOR eObject : eObjects»
			«emit(grammar, eObject)».setAlternatives(«pushIndent»«emitIndent»«emit(grammar, eObject.alternatives)»«popIndent»);
			«FOR annotation : eObject.annotations»
			addAnnotation(«emit(grammar, eObject)», "«annotation.name»");
			«ENDFOR»
			«ENDFOR»
		}

		'''
	}
	
 	/*@NonNull*/ protected def String generateReferencedMetamodels(/*@NonNull*/ Grammar grammar, /*@NonNull*/ Iterable<ReferencedMetamodel> eObjects) {
		'''
		«FOR eObject : eObjects»
		private static final @NonNull ReferencedMetamodel «emit(grammar, eObject)» = createReferencedMetamodel(«emit(grammar, eObject.getEPackage())», «emitValue(eObject.getAlias())»); // «eObject.getEPackage().getNsURI()»
		«ENDFOR»

		'''
	}
	
	/*@NonNull*/ protected def String generateTerminalRules(/*@NonNull*/ Grammar grammar, /*@NonNull*/ Iterable<TerminalRule> eObjects) {
		'''
		«FOR eObject : eObjects»
		private static final @NonNull TerminalRule TR_«eObject.getName()» = createTerminalRule(«emitValue(eObject.getName())», «emit(grammar, eObject.getType())»);
		«ENDFOR»

		private static void initTerminalRules() {
			«FOR eObject : eObjects»
			«IF eObject.isFragment()»
			«emit(grammar, eObject)».setFragment(true);
			«ENDIF»
			«emit(grammar, eObject)».setAlternatives(«pushIndent»«emitIndent»«emit(grammar, eObject.alternatives)»«popIndent»);
			«ENDFOR»
		}

		'''
	}
	
	/*
	 * Emit the eObject term inline within a grammar, either as a direct construction or as a name reference to a previous construction.
	 */
	/*@NonNull*/ protected def String emit(/*@NonNull*/ Grammar grammar, /*@NonNull*/ EObject eObject) {
		switch eObject {
			Action: return emitAction(grammar, eObject)
			Alternatives: return emitAlternatives(grammar, eObject)
			Assignment: return emitAssignment(grammar, eObject)
			CharacterRange: return emitCharacterRange(grammar, eObject)
			CrossReference: return emitCrossReference(grammar, eObject)
			EClassifier: return emitEClassifierLiteral(eObject)
			EPackage: return emitEPackageLiteral(eObject)			
			EEnumLiteral : return emitEEnumLiteral(eObject)
			EnumRule: return emitEnumRuleLiteral(grammar, eObject)
			EnumLiteralDeclaration: return emitEnumLiteralDeclaration(grammar, eObject)
			Grammar: return "G" + getGrammarPackageName(eObject)
			Group: return emitGroup(grammar, eObject)
			Keyword: return emitKeyword(eObject)
			NegatedToken: return emitNegatedToken(grammar, eObject)
			ParserRule: return emitParserRuleLiteral(grammar, eObject)			
			ReferencedMetamodel: return emitReferencedMetamodelName(grammar, eObject)
			RuleCall: return emitRuleCall(grammar, eObject)
			TerminalRule: return emitTerminalRuleLiteral(grammar, eObject)
			TypeRef: return emitTypeRef(grammar, eObject)
			UntilToken: return emitUntilToken(grammar, eObject)
			UnorderedGroup: return emitUnorderedGroup(grammar, eObject)
			Wildcard: return emitWildcard(grammar, eObject)
			default: return emitSymbol(eObject.eClass(), eObject)
		}
	}
	
	/*@NonNull*/ protected def String emitAction(/*@NonNull*/ Grammar grammar, /*@NonNull*/ Action eObject) {
		return wrapCardinality(eObject, wrapFirstSetPredicated(eObject, wrapPredicated(eObject,
			'''createAction(«emitValue(eObject.getFeature())», «emitValue(eObject.getOperator())», «emit(grammar, eObject.getType())»)''')));
	}
	
	/*@NonNull*/ protected def String emitAlternatives(/*@NonNull*/ Grammar grammar, /*@NonNull*/ Alternatives eObject) {
		return wrapCardinality(eObject, wrapFirstSetPredicated(eObject, wrapPredicated(eObject,
			'''createAlternatives(«pushIndent()»«FOR element : eObject.getElements() SEPARATOR ", "»«emitIndent()»«emit(grammar, element)»«ENDFOR»«popIndent()»)''')));
	}
	
	/*@NonNull*/ protected def String emitAssignment(/*@NonNull*/ Grammar grammar, /*@NonNull*/ Assignment eObject) {
		return wrapCardinality(eObject, wrapFirstSetPredicated(eObject, wrapPredicated(eObject,
			'''createAssignment(«emitValue(eObject.getFeature())», «emitValue(eObject.getOperator())», «emit(grammar, eObject.getTerminal())»)''')));
	}
	
	/*@NonNull*/ protected def String emitCharacterRange(/*@NonNull*/ Grammar grammar, /*@NonNull*/ CharacterRange eObject) {
		return wrapCardinality(eObject, wrapFirstSetPredicated(eObject, wrapPredicated(eObject,
		'''createCharacterRange(«emit(grammar, eObject.getLeft)», «emit(grammar, eObject.getRight)»)''')));
	}
	
	/*@NonNull*/ protected def String emitCrossReference(/*@NonNull*/ Grammar grammar, /*@NonNull*/ CrossReference eObject) {
		return wrapCardinality(eObject, wrapFirstSetPredicated(eObject, wrapPredicated(eObject,
			'''createCrossReference(«pushIndent()»«emitIndent()»«emit(grammar, eObject.getType())», «emit(grammar, eObject.getTerminal())»«popIndent()»)''')));
	}
	
	/*@NonNull*/ protected def String emitEnumLiteralDeclaration(/*@NonNull*/ Grammar grammar, /*@NonNull*/ EnumLiteralDeclaration eObject) {
		return wrapCardinality(eObject, wrapFirstSetPredicated(eObject, wrapPredicated(eObject,
			'''createEnumLiteral(«emit(grammar, eObject.literal)», «emit(grammar, eObject.enumLiteral)»)''')));
		
		
	}
	/*@NonNull*/ protected def String emitGroup(/*@NonNull*/ Grammar grammar, /*@NonNull*/ Group eObject) {
		return wrapCardinality(eObject, wrapFirstSetPredicated(eObject, wrapPredicated(eObject,
			'''createGroup(«pushIndent()»«FOR element : eObject.getElements() SEPARATOR ", "»«emitIndent()»«emit(grammar, element)»«ENDFOR»«popIndent()»)''')));
	}
	
	/*@NonNull*/ protected def String emitKeyword(/*@NonNull*/ Keyword eObject) {
		return wrapCardinality(eObject, wrapFirstSetPredicated(eObject, wrapPredicated(eObject,
		'''createKeyword(«emitValue(eObject.getValue())»)''')));
	}
	
	/*@NonNull*/ protected def String emitNegatedToken(/*@NonNull*/ Grammar grammar, /*@NonNull*/ NegatedToken eObject) {
		return wrapCardinality(eObject,  wrapFirstSetPredicated(eObject, wrapPredicated(eObject,
		'''createNegatedToken(«emit(grammar, eObject.getTerminal())»)''')));
	}
	
	/*@NonNull*/ protected def String emitRuleCall(/*@NonNull*/ Grammar grammar, /*@NonNull*/ RuleCall eObject) {
		return wrapCardinality(eObject, wrapFirstSetPredicated(eObject, wrapPredicated(eObject,
		'''createRuleCall(«emit(grammar, eObject.getRule())»)''')));
	}
	
	/*@NonNull*/ protected def String emitTypeRef(/*@NonNull*/ Grammar grammar, /*@NonNull*/ TypeRef eObject) {
		return '''createTypeRef(«emit(grammar, eObject.getMetamodel())», «emit(grammar, eObject.getClassifier())»)''';
	}
	
	/*@NonNull*/ protected def String emitUnorderedGroup(/*@NonNull*/ Grammar grammar, /*@NonNull*/ UnorderedGroup eObject) {
		return wrapCardinality(eObject, wrapFirstSetPredicated(eObject, wrapPredicated(eObject,
			'''createUnorderedGroup(«pushIndent()»«FOR element : eObject.getElements() SEPARATOR ", "»«emitIndent()»«emit(grammar, element)»«ENDFOR»«popIndent()»)''')));
	}
	
	/*@NonNull*/ protected def String emitUntilToken(/*@NonNull*/ Grammar grammar, /*@NonNull*/ UntilToken eObject) {
		return wrapCardinality(eObject, wrapFirstSetPredicated(eObject, wrapPredicated(eObject,
		'''createUntilToken(«emit(grammar, eObject.getTerminal())»)''')));
	}
	
	/*@NonNull*/ protected def String emitWildcard(/*@NonNull*/ Grammar grammar, /*@NonNull*/ Wildcard eObject) {
		return wrapCardinality(eObject, '''createWildcard()''');
	}
	
	/*@NonNull*/ protected def String wrapCardinality(/*@NonNull*/ AbstractElement eObject, String generatedElement) {
		var String cardinality = eObject.getCardinality();
		if (cardinality === null) {
			return generatedElement;
		}
		else {
			return '''setCardinality(«emitValue(cardinality)», «generatedElement»)''';
		}
	}
	
	/*@NonNull*/ protected def String wrapFirstSetPredicated(/*@NonNull*/ AbstractElement eObject, String generatedElement) {
		if (!eObject.isFirstSetPredicated()) {
			return generatedElement;
		}
		else {
			return '''setFirstSetPredicated(«generatedElement»)''';
		}
	}
	
	/*@NonNull*/ protected def String wrapPredicated(/*@NonNull*/ AbstractElement eObject, String generatedElement) {
		if (!eObject.isPredicated()) {
			return generatedElement;
		}
		else {
			return '''setPredicated(«generatedElement»)''';
		}
	}
}
