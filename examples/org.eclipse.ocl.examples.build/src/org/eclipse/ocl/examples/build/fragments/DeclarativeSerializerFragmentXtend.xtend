/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.fragments;

import com.google.inject.Inject
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.jdt.annotation.NonNull
import org.eclipse.jdt.annotation.Nullable
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRuleAnalysis
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue
import org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue
import org.eclipse.ocl.xtext.base.cs2text.enumerations.OthersEnumerationValue
import org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue
import org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.HalfNewLineSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils
import org.eclipse.ocl.xtext.base.cs2text.idioms.NewLineSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.NoSpaceSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.PopSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.PushSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment
import org.eclipse.ocl.xtext.base.cs2text.idioms.SoftNewLineSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.SoftSpaceSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.StringSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.ValueSegment
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValue_MultiplicativeCardinality
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValues
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndex_MultiplicativeCardinality
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndexes
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EnumerationValue_MultiplicativeCardinality
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.RuleIndex_MultiplicativeCardinality
import org.eclipse.ocl.xtext.base.cs2text.solutions.AddCardinalitySolution
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution
import org.eclipse.ocl.xtext.base.cs2text.solutions.DivideCardinalitySolution
import org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution
import org.eclipse.ocl.xtext.base.cs2text.solutions.EReferenceSizeCardinalitySolution
import org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution
import org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution
import org.eclipse.ocl.xtext.base.cs2text.solutions.IntegerCardinalitySolution
import org.eclipse.ocl.xtext.base.cs2text.solutions.MultiplyCardinalitySolution
import org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution
import org.eclipse.ocl.xtext.base.cs2text.solutions.VariableCardinalitySolution
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep
import org.eclipse.ocl.xtext.base.cs2text.user.RTGrammarAnalysis
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue
import org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis
import org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue
import org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue
import org.eclipse.xtext.util.Strings
import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassValue
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassValue.SerializationRule_SegmentsList
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignsStep

/**
 * DeclarativeSerializerFragmentXtend augments DeclarativeSerializerFragment with M2T functionality
 * exploiting Xtend's string template capabilities.
 */
class DeclarativeSerializerFragmentXtend extends DeclarativeSerializerFragment
{
	protected override doGetAnalysisProviderContent(GrammarAnalysis grammarAnalysis) {
		newTypeReference(NonNull);
		newTypeReference(Nullable);
		initAnalysisProviderContent(grammarAnalysis);
		'''		
		public class «getAnalysisProviderClass(grammar).simpleName» extends «getAnalysisProviderSuperClass(grammar)»
		{
			/**
			 * The metadata resulting from static analysis of the grammar.
			 */
			private static «newTypeReference(RTGrammarAnalysis)» analysis = null;
		
			@Override
			public «newTypeReference(RTGrammarAnalysis)» getAnalysis() {
				if (analysis == null) {
					analysis = new «newTypeReference(RTGrammarAnalysis)»(
						/**
						 *	The indexable per-produceable EClass meta data.
						 */
						new «newTypeReference(EClassValue)» [] {
							«FOR eClass : getEClassIterable(grammarAnalysis) SEPARATOR ','»
							«getEClassId(eClass, true)»  /* «eClass.getEPackage().getName()»::«eClass.getName()» */
							«ENDFOR»
						},
						/**
						 *	The indexable per-grammar rule meta data.
						 */
						new «newTypeReference(AbstractRuleValue)» [] {
							«FOR grammarRuleValue : getGrammarRuleValueIterator(grammarAnalysis) SEPARATOR ','»
							«getGrammarRuleValueId(grammarRuleValue, true)»  /* «grammarRuleValue.getIndex()» : «grammarRuleValue.toString()» */
							«ENDFOR»
						}
					);
				}
				return analysis;
			}
			
			«generateIndexVectors(grammarAnalysis)»
			
			«generateEnumValues(grammarAnalysis)»
			
			«generateMatchTerms(grammarAnalysis)»
			
			«generateMatchSteps(grammarAnalysis)»
			
			«generateSerializationTerms(grammarAnalysis)»
			
			«generateSerializationSegments(grammarAnalysis)»
			
			«generateSerializationSegmentsLists(grammarAnalysis)»
						
			«generateGrammarRuleValues(grammarAnalysis)»
												
			«generateEClassValues(grammarAnalysis)»

			«generateSerializationRules(grammarAnalysis)»
			
			private _EClassValues ec;
			private _EnumValues ev;
			private _IndexVectors iv;
			private _MatchSteps ms;
			private _MatchTerms mt;
			private _SerializationSegmentsLists sl;
			private _SerializationSegments ss;
			«FOR page : getSerializationRulePageIterable(grammarAnalysis)»
			private _SerializationRules«page» sr«page»;
			«ENDFOR»
			private _SerializationTerms st;
			private _GrammarRuleValues gr;

			/**
			 * Post constructor/injection initialization to avoid recursions.
			 */
			@«newTypeReference(Inject)»
			public void init() {
				iv = new _IndexVectors();
				ev = new _EnumValues();
				mt = new _MatchTerms();
				ms = new _MatchSteps();
				st = new _SerializationTerms();
				ss = new _SerializationSegments();
				sl = new _SerializationSegmentsLists();
				«FOR page : getSerializationRulePageIterable(grammarAnalysis)»
				sr«page» = new _SerializationRules«page»();
				«ENDFOR»
				gr = new _GrammarRuleValues();
				ec = new _EClassValues();		
			}
			
		}
		//	Commented imports ensure Xtend provides a true import allowing unqualified annotated usage
		«FOR importedClassName : getImportedClassNameIterable()»
		«var index = importedClassName.lastIndexOf('.')»
		«IF index < 0»
		//	import «new TypeReference(importedClassName)»;
		«ELSE»
		//	import «new TypeReference(importedClassName.substring(0, index), importedClassName.substring(index+1).replace('$', '.'))»;
		«ENDIF»
		«ENDFOR»
		'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateEClassValues(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * Configuration for each EClass that may be serialized.
		 */
		private class _EClassValues
		{
			«FOR eClass : getEClassIterable(grammarAnalysis)»
			private final @NonNull «newTypeReference(EClassValue)» «getEClassId(eClass, false)» // «eClass.getName()»
				= «generateEClassValue_EClass(grammarAnalysis, eClass)»;
			«ENDFOR»
		}
		'''
	}
	
	protected def generateEClassValue_EClass(GrammarAnalysis grammarAnalysis, EClass eClass) {
		'''
		new «newTypeReference(EClassValue)»(«emitLiteral(eClass)»,
			new @NonNull «newTypeReference(SerializationRule_SegmentsList)» [] {
				«FOR serializationRuleSegmentsList : grammarAnalysis.getEClassValue(eClass).getSerializationRuleSegmentsLists() SEPARATOR ','»
				«var serializationRule = serializationRuleSegmentsList.getSerializationRule()»
				new «newTypeReference(SerializationRule_SegmentsList)»(«getSerializationRuleId(serializationRule, true)», «getSegmentsListId(getSegmentsListString(serializationRule.getStaticSegments()), true)») /* «serializationRule.toRuleString()» */
				«ENDFOR»
			}, «IF grammarAnalysis.basicGetEReferenceRuleIndexes(eClass) === null »null«ELSE»
			new @NonNull «newTypeReference(EReference_RuleIndexes)» [] {
				«FOR eReferenceRuleIndex : getEReferenceRuleIndexesIterable(grammarAnalysis, eClass) SEPARATOR ','»
				new «newTypeReference(EReference_RuleIndexes)»(«emitLiteral(eReferenceRuleIndex.getEReference())»,
					«getIndexVectorId(eReferenceRuleIndex.getAssignedTargetRuleValueIndexes(), true)») /* «FOR ruleValueIndex : eReferenceRuleIndex.getAssignedTargetRuleValueIndexes() SEPARATOR '|'»«grammarAnalysis.getRuleValue(ruleValueIndex).toString()»«ENDFOR» */
				«ENDFOR»
			}«ENDIF»
		)'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateEnumValues(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * String combinations used by assigned String EAttributes
		 */
		private class _EnumValues
		{
			«FOR enumValue : getEnumValueIterable(grammarAnalysis)»
			private final @NonNull «newTypeReference(EnumerationValue)» «getEnumValueId(enumValue, false)» // «enumValue.toString()»
				= «generateEnumValue(enumValue)»;
			«ENDFOR»
		}
		'''
	}
	
	protected def generateEnumValue(EnumerationValue enumValue) {
		switch enumValue {
		MultipleEnumerationValue: return generateEnumValue_MultipleEnumerationValue(enumValue)
		OthersEnumerationValue: return generateEnumValue_OthersEnumerationValue(enumValue)
		SingleEnumerationValue: return generateEnumValue_SingleEnumerationValue(enumValue)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateEnumValue_MultipleEnumerationValue(MultipleEnumerationValue enumValue) {
		'''new «newTypeReference(MultipleEnumerationValue)»(new @NonNull String[]{«FOR value : enumValue.getValues() SEPARATOR ', '»"«value»"«ENDFOR»})'''
	}
	
	protected def generateEnumValue_OthersEnumerationValue(OthersEnumerationValue enumValue) {
		'''new «newTypeReference(OthersEnumerationValue)»()'''
	}
	
	protected def generateEnumValue_SingleEnumerationValue(SingleEnumerationValue enumValue) {
		'''new «newTypeReference(SingleEnumerationValue)»("«enumValue.getName()»")'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateGrammarRuleValues(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * The various serialization rules for each grammar rule.
		 */
		private class _GrammarRuleValues
		{
			«FOR grammarRuleValue : getGrammarRuleValueIterator(grammarAnalysis)»
			«generateGrammarRuleValue(grammarAnalysis, grammarRuleValue)»
			«ENDFOR»
		}
		'''
	}
	
	protected def generateGrammarRuleValue(GrammarAnalysis grammarAnalysis, AbstractRuleValue grammarRuleValue) {
		switch grammarRuleValue {
		DataTypeRuleValue: return generateGrammarRuleValue_DataTypeRule(grammarAnalysis, grammarRuleValue)
		ParserRuleValue: return generateGrammarRuleValue_ParserRuleValue(grammarAnalysis, grammarRuleValue)
		TerminalRuleValue: return generateGrammarRuleValue_TerminalRuleValue(grammarAnalysis, grammarRuleValue)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateGrammarRuleValue_DataTypeRule(GrammarAnalysis grammarAnalysis, DataTypeRuleValue dataTypeRuleValue) {
		'''private final @NonNull «newTypeReference(DataTypeRuleValue)» «getGrammarRuleValueId(dataTypeRuleValue, false)» // «dataTypeRuleValue.getName()»
	= new «newTypeReference(DataTypeRuleValue)»(«dataTypeRuleValue.getIndex()», "«dataTypeRuleValue.getName()»");'''
	}
	
	protected def generateGrammarRuleValue_ParserRuleValue(GrammarAnalysis grammarAnalysis, ParserRuleValue parserRuleValue) {
			// «FOR subParserRuleValue : subParserRuleValueClosure SEPARATOR ','» «getParserRuleValueId(subParserRuleValue, true)» /* «subParserRuleValue.getName()» */«ENDFOR» */'''
		var subParserRuleValueIndexes = parserRuleValue.getSubParserRuleValueIndexes();
		if (subParserRuleValueIndexes !== null) {
			'''
			private final @NonNull «newTypeReference(ParserRuleValue)» «getGrammarRuleValueId(parserRuleValue, false)» // «parserRuleValue.getName()»
				= new «newTypeReference(ParserRuleValue)»(«parserRuleValue.getIndex()», "«parserRuleValue.getName()»",
					new @NonNull «newTypeReference(SerializationRule)» [] {
						«FOR serializationRule : getSerializationRulesIterable(grammarAnalysis, parserRuleValue) SEPARATOR ','»
						«getSerializationRuleId(serializationRule, true)» /* «serializationRule.toRuleString()» */
						«ENDFOR»
					}, 
					«getIndexVectorId(subParserRuleValueIndexes, true)»); /* «FOR index : subParserRuleValueIndexes SEPARATOR '|'»«getGrammarRuleName(index)»«ENDFOR» */
			'''
		}
		else {
			'''
			private final @NonNull «newTypeReference(ParserRuleValue)» «getGrammarRuleValueId(parserRuleValue, false)» // «parserRuleValue.getName()»
				= new «newTypeReference(ParserRuleValue)»(«parserRuleValue.getIndex()», "«parserRuleValue.getName()»", 
					new @NonNull «newTypeReference(SerializationRule)» [] {
						«FOR serializationRule : getSerializationRulesIterable(grammarAnalysis, parserRuleValue) SEPARATOR ','»
						«getSerializationRuleId(serializationRule, true)» /* «serializationRule.toRuleString()» */
						«ENDFOR»
					}, 
					(«newTypeReference(IndexVector)»)null);
			'''
		}
	}
	
	protected def generateGrammarRuleValue_TerminalRuleValue(GrammarAnalysis grammarAnalysis, TerminalRuleValue terminalRuleValue) {
		'''private final @NonNull «newTypeReference(TerminalRuleValue)» «getGrammarRuleValueId(terminalRuleValue, false)» // «terminalRuleValue.getName()»
	= new «newTypeReference(TerminalRuleValue)»(«terminalRuleValue.getIndex()», "«terminalRuleValue.getName()»");'''
	}

	/* ************************************************************************************************************************** */
	
	protected def generateIndexVectors(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * Bit vectors of useful grammar rule combinations
		 */
		private class _IndexVectors
		{
			«FOR indexVector : getIndexVectorIterable(grammarAnalysis)»
			private final @NonNull «newTypeReference(IndexVector)» «getIndexVectorId(indexVector, false)» // «FOR index : indexVector SEPARATOR '|' »«grammarAnalysis.getRuleName(index)»«ENDFOR»
				= new «newTypeReference(IndexVector)»(«indexVector.toWordsString()»);
			«ENDFOR»
		}
		'''
	}

	
	/* ************************************************************************************************************************** */
	
	protected def generateMatchSteps(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * Steps for the matching process.
		 */
		private class _MatchSteps
		{
			«FOR matchStep : getMatchStepIterable(grammarAnalysis)»
			private final @NonNull «newTypeReference(CardinalitySolutionStep)» «getMatchStepId(matchStep, false)» // «matchStep.toString()»
				= «generateMatchStep(matchStep)»;
			«ENDFOR»
		}
		'''
	}
	
	protected def generateMatchStep(CardinalitySolutionStep matchStep) {
		switch matchStep {
		CardinalitySolutionStep.CardinalitySolutionStep_Assert: return generateMatchStep_Assert(matchStep)
		CardinalitySolutionStep.CardinalitySolutionStep_Assign: return generateMatchStep_Assign(matchStep)
		CardinalitySolutionStep.CardinalitySolutionStep_RuleCheck: return generateMatchStep_RuleCheck(matchStep)
		CardinalitySolutionStep.CardinalitySolutionStep_ValueCheck: return generateMatchStep_ValueCheck(matchStep)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateMatchStep_Assert(CardinalitySolutionStep.CardinalitySolutionStep_Assert matchStep) {
		'''new «newTypeReference(CardinalitySolutionStep.CardinalitySolutionStep_Assert)»(«getMatchTermId(matchStep.getCardinalitySolution(), true)»)'''
	}
	
	protected def generateMatchStep_Assign(CardinalitySolutionStep.CardinalitySolutionStep_Assign matchStep) {
		'''new «newTypeReference(CardinalitySolutionStep.CardinalitySolutionStep_Assign)»(«matchStep.getVariableIndex()», «getMatchTermId(matchStep.getCardinalitySolution(), true)»)'''
	}
	
	protected def generateMatchStep_RuleCheck(CardinalitySolutionStep.CardinalitySolutionStep_RuleCheck matchStep) {
		'''new «newTypeReference(CardinalitySolutionStep.CardinalitySolutionStep_RuleCheck)»(«emitLiteral(matchStep.getEReference())», «getIndexVectorId(matchStep.getRuleValueIndexes(), true)»/*«FOR index : matchStep.getRuleValueIndexes() SEPARATOR '|' »«grammarAnalysis.getRuleName(index)»«ENDFOR»*/)'''
	}
	
	protected def generateMatchStep_ValueCheck(CardinalitySolutionStep.CardinalitySolutionStep_ValueCheck matchStep) {
		'''new «newTypeReference(CardinalitySolutionStep.CardinalitySolutionStep_ValueCheck)»(«matchStep.getVariableIndex()», «getMatchTermId(matchStep.getCardinalitySolution(), true)»)'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateMatchTerms(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * Expression terms used during the matching process.
		 */
		private class _MatchTerms
		{
			«FOR matchTerm : getMatchTermIterable(grammarAnalysis)»
			private final @NonNull «newTypeReference(CardinalitySolution)» «getMatchTermId(matchTerm, false)» // «matchTerm.toString()»
				= «generateMatchTerm(matchTerm)»;
			«ENDFOR»
		}
		'''
	}

	protected def generateMatchTerm(CardinalitySolution matchTerm) {
		switch matchTerm {
		AddCardinalitySolution: return generateMatchTerm_AddCardinalitySolution(matchTerm)
		DivideCardinalitySolution: return generateMatchTerm_DivideCardinalitySolution(matchTerm)
		EAttributeSizeCardinalitySolution: return generateMatchTerm_EAttributeSizeCardinalitySolution(matchTerm)
		EReferenceSizeCardinalitySolution: return generateMatchTerm_EReferenceSizeCardinalitySolution(matchTerm)
		EStructuralFeatureSizeCardinalitySolution: return generateMatchTerm_EStructuralFeatureSizeCardinalitySolution(matchTerm)
		GreaterThanCardinalitySolution: return generateMatchTerm_GreaterThanCardinalitySolution(matchTerm)
		IntegerCardinalitySolution: return generateMatchTerm_IntegerSolution(matchTerm)
		MultiplyCardinalitySolution: return generateMatchTerm_MultiplyCardinalitySolution(matchTerm)
		SubtractCardinalitySolution: return generateMatchTerm_SubtractCardinalitySolution(matchTerm)
		VariableCardinalitySolution: return generateMatchTerm_VariableCardinalitySolution(matchTerm)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateMatchTerm_AddCardinalitySolution(AddCardinalitySolution matchTerm) {
		'''new «newTypeReference(AddCardinalitySolution)»(«getMatchTermId(matchTerm.getLeft(), false)», «getMatchTermId(matchTerm.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_DivideCardinalitySolution(DivideCardinalitySolution matchTerm) {
		'''new «newTypeReference(DivideCardinalitySolution)»(«getMatchTermId(matchTerm.getLeft(), false)», «getMatchTermId(matchTerm.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_EAttributeSizeCardinalitySolution(EAttributeSizeCardinalitySolution matchTerm) {
		'''new «newTypeReference(EAttributeSizeCardinalitySolution)»(«emitLiteral(matchTerm.getEAttribute())», «getEnumValueId(matchTerm.getEnumerationValue(), true)»)'''
	}
	
	protected def generateMatchTerm_EReferenceSizeCardinalitySolution(EReferenceSizeCardinalitySolution matchTerm) {
		'''new «newTypeReference(EReferenceSizeCardinalitySolution)»(«emitLiteral(matchTerm.getEReference())», "«matchTerm.getParserRuleValue().getName()»")'''
	}
	
	protected def generateMatchTerm_EStructuralFeatureSizeCardinalitySolution(EStructuralFeatureSizeCardinalitySolution matchTerm) {
		'''new «newTypeReference(EStructuralFeatureSizeCardinalitySolution)»(«emitLiteral(matchTerm.getEStructuralFeature())»)'''
	}
	
	protected def generateMatchTerm_GreaterThanCardinalitySolution(GreaterThanCardinalitySolution matchTerm) {
		'''new «newTypeReference(GreaterThanCardinalitySolution)»(«getMatchTermId(matchTerm.getLeft(), false)», «getMatchTermId(matchTerm.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_IntegerSolution(IntegerCardinalitySolution matchTerm) {
		'''new «newTypeReference(IntegerCardinalitySolution)»(«matchTerm.getValue()»)'''
	}
	
	protected def generateMatchTerm_MultiplyCardinalitySolution(MultiplyCardinalitySolution matchTerm) {
		'''new «newTypeReference(MultiplyCardinalitySolution)»(«getMatchTermId(matchTerm.getLeft(), false)», «getMatchTermId(matchTerm.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_SubtractCardinalitySolution(SubtractCardinalitySolution matchTerm) {
		'''new «newTypeReference(SubtractCardinalitySolution)»(«getMatchTermId(matchTerm.getLeft(), false)», «getMatchTermId(matchTerm.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_VariableCardinalitySolution(VariableCardinalitySolution matchTerm) {
		'''new «newTypeReference(VariableCardinalitySolution)»(«matchTerm.getVariableIndex()»)'''
	}
	
	/* ************************************************************************************************************************** */	

	protected def generateSerializationRules(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * The various serialization rules that serialize an EClass.
		 */
		«FOR page : getSerializationRulePageIterable(grammarAnalysis)»
		private class _SerializationRules«page»
		{
			«FOR serializationRuleAnalysis : getSerializationRuleAnalysisIterable(grammarAnalysis, page)»
			«generateSerializationRule(serializationRuleAnalysis)»
			«ENDFOR»
		}
		«ENDFOR»
		'''
	}
	
	protected def generateSerializationRule(SerializationRuleAnalysis serializationRuleAnalysis) {
		var SerializationRule serializationRule = serializationRuleAnalysis.getRuntime();
		'''
		// «serializationRuleAnalysis.getName()» : «serializationRule.toRuleString()»
		private @NonNull «newTypeReference(SerializationRule)» «getSerializationRuleId(serializationRuleAnalysis.getRuntime(), false)» = new «newTypeReference(SerializationRule)»(«serializationRuleAnalysis.getRuleValueIndex()»,
			new @NonNull «newTypeReference(CardinalitySolutionStep)» @NonNull [] {
				«FOR solutionStep : serializationRuleAnalysis.getStaticRuleMatch().getSteps() SEPARATOR ','»
				«getMatchStepId(solutionStep, true)» /* «solutionStep.toString()» */
				«ENDFOR»
			}, 
			«var serializationSteps = serializationRule.getSerializationSteps()»
			«var segmentsList = serializationRule.getStaticSegments()»
			new @NonNull «newTypeReference(RTSerializationStep)» @NonNull [] {
				«FOR i : integersIterable(serializationSteps.length) SEPARATOR ','»
				«var serializationStep = serializationSteps.get(i)»
				«var segments = segmentsList.get(i)»
				«getSerializationStepId(serializationStep, true)» /* «serializationStep.toString()» || «IF segments !== null»«FOR segment : segments SEPARATOR ' '»«segment.toString()»«ENDFOR»«ELSE»«"«null»"»«ENDIF» */
				«ENDFOR»
			}, 
			«var segmentsListString = getSegmentsListString(segmentsList)»
			«IF segmentsList !== null»«getSegmentsListId(segmentsListString, true)»«ELSE»null«ENDIF»,
			«var eAttribute2EnumerationValues = serializationRuleAnalysis.basicGetEAttribute2EnumerationValues()»
			«IF eAttribute2EnumerationValues !== null»
			new @NonNull «newTypeReference(EAttribute_EnumerationValues)» [] {
				«FOR eAttributeData : eAttribute2EnumerationValues SEPARATOR ','»
				new «newTypeReference(EAttribute_EnumerationValues)»(«emitLiteral(eAttributeData.getEAttribute())»,
					«FOR enumerationValue : eAttributeData.getEnumerationValues() SEPARATOR ','»«getEnumValueId(enumerationValue, true)»«ENDFOR»)
				«ENDFOR»
			},
			«ELSE»
			null,
			«ENDIF»
			«var eReference2AssignedRuleValueIndexes = serializationRuleAnalysis.basicGetEReference2AssignedRuleValueIndexes()»
			«IF eReference2AssignedRuleValueIndexes !== null»
			new @NonNull «newTypeReference(EReference_RuleIndexes)» [] {
				«FOR eReferenceData : eReference2AssignedRuleValueIndexes SEPARATOR ','»
				new «newTypeReference(EReference_RuleIndexes)»(«emitLiteral(eReferenceData.getEReference())»,
					«getIndexVectorId(eReferenceData.getAssignedTargetRuleValueIndexes(), true)») /* «FOR ruleValueIndex : eReferenceData.getAssignedTargetRuleValueIndexes() SEPARATOR '|'»«grammarAnalysis.getRuleValue(ruleValueIndex).toString()»«ENDFOR» */
				«ENDFOR»
			},
			«ELSE»
			null,
			«ENDIF»
			«var needsDefaultEAttributes = serializationRuleAnalysis.basicGetNeedsDefaultEAttributes()»
			«IF needsDefaultEAttributes !== null»
			new /*@NonNull*/ «newTypeReference(EAttribute)» [] {
				«FOR eAttribute : needsDefaultEAttributes SEPARATOR ','»
				«emitLiteral(eAttribute)»
				«ENDFOR»
			},
			«ELSE»
			null,
			«ENDIF»
			«var eAttribute2enumerationValue2multiplicativeCardinalityArray = serializationRuleAnalysis.basicGetEAttribute2enumerationValue2multiplicativeCardinality()»
			«IF eAttribute2enumerationValue2multiplicativeCardinalityArray !== null»
			new @NonNull «newTypeReference(EAttribute_EnumerationValue_MultiplicativeCardinality)» [] {
				«FOR eAttribute2enumerationValue2multiplicativeCardinality : eAttribute2enumerationValue2multiplicativeCardinalityArray SEPARATOR ','»
				new «newTypeReference(EAttribute_EnumerationValue_MultiplicativeCardinality)»(«emitLiteral(eAttribute2enumerationValue2multiplicativeCardinality.getEAttribute())»,
					new @NonNull «newTypeReference(EnumerationValue_MultiplicativeCardinality)» [] {
					«FOR enumerationValue2multiplicativeCardinality : eAttribute2enumerationValue2multiplicativeCardinality.getEnumerationValue_MultiplicativeCardinality() SEPARATOR ','»
					«var enumerationValue = enumerationValue2multiplicativeCardinality.getEnumerationValue()»
						new «newTypeReference(EnumerationValue_MultiplicativeCardinality)»(«enumerationValue !== null ? getEnumValueId(enumerationValue, true) : "null"», «emitMultiplicativeCardinality(enumerationValue2multiplicativeCardinality.getMultiplicativeCardinality())»)
					«ENDFOR»
					}
				)
				«ENDFOR»
			},
			«ELSE»
			null,
			«ENDIF»
			«var eReference2ruleValueIndex2multiplicativeCardinalityArray = serializationRuleAnalysis.basicGetEReference2ruleValueIndex2multiplicativeCardinality()»
			«IF eReference2ruleValueIndex2multiplicativeCardinalityArray !== null»
			new @NonNull «newTypeReference(EReference_RuleIndex_MultiplicativeCardinality)» [] {
				«FOR eReference2ruleValueIndex2multiplicativeCardinality : eReference2ruleValueIndex2multiplicativeCardinalityArray SEPARATOR ','»
				new «newTypeReference(EReference_RuleIndex_MultiplicativeCardinality)»(«emitLiteral(eReference2ruleValueIndex2multiplicativeCardinality.getEReference())»,
					new @NonNull «newTypeReference(RuleIndex_MultiplicativeCardinality)» [] {
					«FOR ruleValueIndex2multiplicativeCardinality : eReference2ruleValueIndex2multiplicativeCardinality.getRuleIndex_MultiplicativeCardinality() SEPARATOR ','»
						new «newTypeReference(RuleIndex_MultiplicativeCardinality)»(«ruleValueIndex2multiplicativeCardinality.getRuleIndex()», «emitMultiplicativeCardinality(ruleValueIndex2multiplicativeCardinality.getMultiplicativeCardinality())»)
					«ENDFOR»
					}
				)
				«ENDFOR»
			});
			«ELSE»
			null);
			«ENDIF»
		'''
	}
	
	/* ************************************************************************************************************************** */	
	
	protected def generateSerializationSegments(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * The various string segment sequences that may be used to serialize a serialization term.
		 */
		private class _SerializationSegments
		{
			«FOR segments : getSegmentsIterable(grammarAnalysis)»
			private final @NonNull «newTypeReference(Segment)» [] «getSegmentsId(segments, false)» = new @NonNull «newTypeReference(Segment)» @NonNull [] {
				«FOR segment : segments SEPARATOR ','»
				«generateSerializationSegment(segment)» /* «segment.toString()» */
				«ENDFOR»
			};
			«ENDFOR»
		}
		'''
	}

	protected def generateSerializationSegment(Segment segment) {
		switch segment {
		CustomSegment: return generateSerializationSegment_Custom(segment)
		HalfNewLineSegment: return generateSerializationSegment_HalfNewLine(segment)
		NewLineSegment: return generateSerializationSegment_NewLine(segment)
		NoSpaceSegment: return generateSerializationSegment_NoSpace(segment)
		PopSegment: return generateSerializationSegment_Pop(segment)
		PushSegment: return generateSerializationSegment_Push(segment)
		SoftNewLineSegment: return generateSerializationSegment_SoftNewLine(segment)
		SoftSpaceSegment: return generateSerializationSegment_SoftSpace(segment)
		StringSegment: return generateSerializationSegment_String(segment)
		ValueSegment: return generateSerializationSegment_Value(segment)
		default: segment.getClass().getName() //throw new UnsupportedOperationException()
		}
	}
	
	protected def generateSerializationSegment_Custom(CustomSegment segment) {
		'''«newTypeReference(IdiomsUtils)».createCustomSegment(null, «newTypeReference(segment.getSupportClassName())».class)'''
	}
	
	protected def generateSerializationSegment_HalfNewLine(HalfNewLineSegment segment) {
		'''«newTypeReference(IdiomsUtils)».HALF_NEW_LINE'''
	}
	
	protected def generateSerializationSegment_NewLine(NewLineSegment segment) {
		'''«newTypeReference(IdiomsUtils)».NEW_LINE'''
	}
	
	protected def generateSerializationSegment_NoSpace(NoSpaceSegment segment) {
		'''«newTypeReference(IdiomsUtils)».NO_SPACE'''
	}
	
	protected def generateSerializationSegment_Pop(PopSegment segment) {
		'''«newTypeReference(IdiomsUtils)».POP'''
	}
	
	protected def generateSerializationSegment_Push(PushSegment segment) {
		'''«newTypeReference(IdiomsUtils)».PUSH'''
	}
	
	protected def generateSerializationSegment_SoftNewLine(SoftNewLineSegment segment) {
		'''«newTypeReference(IdiomsUtils)».SOFT_NEW_LINE'''
	}
	
	protected def generateSerializationSegment_SoftSpace(SoftSpaceSegment segment) {
		'''«newTypeReference(IdiomsUtils)».SOFT_SPACE'''
	}
	
	protected def generateSerializationSegment_String(StringSegment segment) {
		'''«newTypeReference(IdiomsUtils)».createStringSegment(«segment.getString()»)'''
	}
	
	protected def generateSerializationSegment_Value(ValueSegment segment) {
		'''«newTypeReference(IdiomsUtils)».VALUE'''
	}
	
	/* ************************************************************************************************************************** */	
	
	protected def generateSerializationSegmentsLists(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * The various lists of string segment sequences that may be used to serialize a serialization rule.
		 */
		private class _SerializationSegmentsLists
		{
			«FOR segmentsList : getSegmentsListIterable(grammarAnalysis)»
			private final @NonNull «newTypeReference(Segment)» @NonNull [] @Nullable [] «getSegmentsListId(getSegmentsListString(segmentsList), false)» = new @NonNull «newTypeReference(Segment)» @NonNull [] @Nullable [] {
				«FOR segments : segmentsList SEPARATOR ','»
				«IF segments !== null»«getSegmentsId(segments, true)» /* «FOR segment : segments SEPARATOR ' '»«segment.toString()»«ENDFOR» */«ELSE»null«ENDIF»
				«ENDFOR»
			};
			«ENDFOR»
		}
		'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateSerializationTerms(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * The various serialization term used to serialize a serialization rule.
		 */
		private class _SerializationTerms
		{
			«FOR step : getSerializationStepIterable(grammarAnalysis)»
			«generateSerializationTerm(step)»;
			«ENDFOR»
		}
		'''
	}
	
	protected def generateSerializationTerm(RTSerializationStep serializationStep) {
		switch serializationStep {
		RTSerializationAssignStep: return generateSerializationTerm_Assign(serializationStep)
		RTSerializationAssignedRuleCallStep: return generateSerializationTerm_AssignedRuleCall(serializationStep)
		RTSerializationAssignedRuleCallsStep: return generateSerializationTerm_AssignedRuleCalls(serializationStep)
		RTSerializationAssignsStep: return generateSerializationTerm_AssignsStep(serializationStep)
		RTSerializationCrossReferenceStep: return generateSerializationTerm_CrossReference(serializationStep)
		RTSerializationLiteralStep: return generateSerializationTerm_Literal(serializationStep)
		RTSerializationSequenceStep: return generateSerializationTerm_Sequence(serializationStep)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateSerializationTerm_Assign(RTSerializationAssignStep serializationStep) {
		'''private final @NonNull «newTypeReference(RTSerializationAssignStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(RTSerializationAssignStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»)'''
	}
	
	protected def generateSerializationTerm_AssignedRuleCall(RTSerializationAssignedRuleCallStep serializationStep) {
		'''private final @NonNull «newTypeReference(RTSerializationAssignedRuleCallStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(RTSerializationAssignedRuleCallStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())», «serializationStep.getCalledRuleIndex()» /* «grammarAnalysis.getRuleValue(serializationStep.getCalledRuleIndex()).getName()» */)'''
	}
	
	protected def generateSerializationTerm_AssignedRuleCalls(RTSerializationAssignedRuleCallsStep serializationStep) {
		'''private final @NonNull «newTypeReference(RTSerializationAssignedRuleCallsStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(RTSerializationAssignedRuleCallsStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())», «getIndexVectorId(serializationStep.getCalledRuleIndexes(), true)» /* «FOR calledRuleIndex : serializationStep.getCalledRuleIndexes() SEPARATOR ', '»«grammarAnalysis.getRuleValue(calledRuleIndex).getName()»«ENDFOR» */)'''
	}
	
	protected def generateSerializationTerm_AssignsStep(RTSerializationAssignsStep serializationStep) {
		var enumerationValue = serializationStep.getEnumerationValue();
		var calledRuleIndexes = serializationStep.getCalledRuleIndexes();
		'''private final @NonNull «newTypeReference(RTSerializationAssignsStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(RTSerializationAssignsStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())», «IF enumerationValue !== null»«getEnumValueId(enumerationValue, true)»«ELSE»null«ENDIF», «IF calledRuleIndexes !== null»new @NonNull Integer [] { «FOR calledRuleIndex : calledRuleIndexes SEPARATOR ','»«calledRuleIndex»/*«grammarAnalysis.getRuleValue(calledRuleIndex).getName()»*/«ENDFOR»}«ELSE»null«ENDIF»)'''
	}

	protected def generateSerializationTerm_CrossReference(RTSerializationCrossReferenceStep serializationStep) {
		'''private final @NonNull «newTypeReference(RTSerializationCrossReferenceStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(RTSerializationCrossReferenceStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())», getCrossReference(«emitLiteral(serializationStep.getEStructuralFeature())», "«emitCalledRule(serializationStep.getCrossReference())»"))'''
	}
	
	protected def generateSerializationTerm_Literal(RTSerializationLiteralStep serializationStep) {
		'''private final @NonNull «newTypeReference(RTSerializationLiteralStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(RTSerializationLiteralStep)»(«serializationStep.getVariableIndex()», "«Strings.convertToJavaString(serializationStep.getString())»")'''
	}
	
	protected def generateSerializationTerm_Sequence(RTSerializationSequenceStep serializationStep) {
		'''private final @NonNull «newTypeReference(RTSerializationSequenceStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(RTSerializationSequenceStep)»(«serializationStep.getVariableIndex()», «serializationStep.getStartIndex()», «serializationStep.getEndIndex()»)'''
	}
}
