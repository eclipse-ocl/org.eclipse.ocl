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

import java.util.List
import org.eclipse.emf.ecore.EClass
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
import org.eclipse.ocl.xtext.base.cs2text.user.RTGrammarAnalysis
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue
import org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis
import org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue
import org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue
import org.eclipse.xtext.util.Strings
import org.eclipse.xtext.xtext.generator.model.TypeReference
import com.google.inject.Inject
import org.eclipse.jdt.annotation.NonNull
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EStructuralFeature_CardinalityExpression
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndexes
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValues
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValue_MultiplicativeCardinality
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndex_MultiplicativeCardinality
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.RuleIndex_MultiplicativeCardinality
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EnumerationValue_MultiplicativeCardinality
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityExpression
import org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeCardinalityExpression
import org.eclipse.ocl.xtext.base.cs2text.solutions.EReferenceCardinalityExpression
import org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureCardinalityExpression

/**
 * DeclarativeSerializerFragmentXtend augments DeclarativeSerializerFragment with M2T functionality
 * exploiting Xtend's string template capabilities.
 */
class DeclarativeSerializerFragmentXtend extends DeclarativeSerializerFragment
{
	protected override doGetAnalysisProviderContent(GrammarAnalysis grammarAnalysis) {
		newTypeReference(NonNull);
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
						new «newTypeReference(EClassData)» [] {
							«FOR eClass : getSortedEClasses(grammarAnalysis) SEPARATOR ','»
							«getEClassId(eClass, true)»  /* «eClass.getEPackage().getName()»::«eClass.getName()» */
							«ENDFOR»
						},
						/**
						 *	The indexable per-grammar rule meta data.
						 */
						new «newTypeReference(AbstractRuleValue)» [] {
							«FOR ruleValue : getSortedRuleValues(grammarAnalysis) SEPARATOR ','»
							«getRuleValueId(ruleValue, true)»  /* «ruleValue.getIndex()» : «ruleValue.toString()» */
							«ENDFOR»
						}
					);
				}
				return analysis;
			}
			
			«generateIndexVectors(grammarAnalysis)»
			
			«generateEnumValues(grammarAnalysis)»
			
			«generateMatchChecks(grammarAnalysis)»
			
			«generateMatchTerms(grammarAnalysis)»
			
			«generateMatchSteps(grammarAnalysis)»
			
			«generateSerializationTerms(grammarAnalysis)»
			
			«generateSerializationSegments(grammarAnalysis)»
						
			«generateRuleValues(grammarAnalysis)»
												
			«generateEClassDatas(grammarAnalysis)»

			«generateSerializationRules(grammarAnalysis)»
			
			private _EnumValues ev;
			private _IndexVectors iv;
			private _MatchChecks mc;
			private _MatchTerms mt;
			private _MatchSteps ms;
			private _SerializationTerms st;
			private _SerializationSegments ss;
			«FOR page : getSortedSerializationRulePages(grammarAnalysis)»
			private _SerializationRules«page» sr«page»;
			«ENDFOR»
			private _RuleValues rv;
			private _EClassData ec;

			/**
			 * Post constructor/injection initialization to avoid recursions.
			 */
			@«newTypeReference(Inject)»
			public void init() {
				iv = new _IndexVectors();
				ev = new _EnumValues();
				mc = new _MatchChecks();
				mt = new _MatchTerms();
				ms = new _MatchSteps();
				st = new _SerializationTerms();
				ss = new _SerializationSegments();
				«FOR page : getSortedSerializationRulePages(grammarAnalysis)»
				sr«page» = new _SerializationRules«page»();
				«ENDFOR»
				rv = new _RuleValues();
				ec = new _EClassData();		
				st.init();
			}
			
			//	Commented imports to ensure Xtend provides a true import allowing unqualified annotated usage
				«FOR importedClassName : getSortedImportClassNames()»
				«var index = importedClassName.lastIndexOf('.')»
				«IF index < 0»
				//	import «new TypeReference(importedClassName)»;
				«ELSE»
				//	import «new TypeReference(importedClassName.substring(0, index), importedClassName.substring(index+1).replace('$', '.'))»;
				«ENDIF»
				«ENDFOR»
		}
		'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateEClassDatas(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * Configuration for each EClass that may be serialized.
		 */
		private class _EClassData
		{
			«FOR eClass : getSortedEClasses(grammarAnalysis)»
			private final @NonNull «newTypeReference(EClassData)» «getEClassId(eClass, false)» // «eClass.getName()»
				= «generateEClassData_EClass(grammarAnalysis, eClass)»;
			«ENDFOR»
		}
		'''
	}
	
	protected def generateEClassData_EClass(GrammarAnalysis grammarAnalysis, EClass eClass) {
		'''
		new «newTypeReference(EClassData)»(«emitLiteral(eClass)»,
			new @NonNull «newTypeReference(SerializationRule)» [] {
				«FOR serializationRule : grammarAnalysis.getEClassData(eClass).getSerializationRules() SEPARATOR ','»
				«getSerializationRuleId(serializationRule, true)» /* «serializationRule.toRuleString()» */
				«ENDFOR»
			}, «IF grammarAnalysis.basicGetEReferenceDatas(eClass) === null »null«ELSE»
			new @NonNull «newTypeReference(EReference_RuleIndexes)» [] {
				«FOR eReferenceData : grammarAnalysis.getEReferenceDatas(eClass) SEPARATOR ','»
				new «newTypeReference(EReference_RuleIndexes)»(«emitLiteral(eReferenceData.getEReference())»,
					«getIndexVectorId(eReferenceData.getAssignedTargetRuleValueIndexes(), true)») /* «FOR ruleValueIndex : eReferenceData.getAssignedTargetRuleValueIndexes() SEPARATOR '|'»«grammarAnalysis.getRuleValue(ruleValueIndex).toString()»«ENDFOR» */
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
			«FOR enumValue : getSortedEnumValues(grammarAnalysis)»
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
	
	protected def generateIndexVectors(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * Bit vectors of useful grammar rule combinations
		 */
		private class _IndexVectors
		{
			«FOR indexVector : getSortedIndexVectors(grammarAnalysis)»
			private final @NonNull «newTypeReference(IndexVector)» «getIndexVectorId(indexVector, false)» // «FOR index : indexVector SEPARATOR '|' »«grammarAnalysis.getRuleName(index)»«ENDFOR»
				= new «newTypeReference(IndexVector)»(«indexVector.toWordsString()»);
			«ENDFOR»
		}
		'''
	}

	/* ************************************************************************************************************************** */
	
	protected def generateMatchChecks(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * Checks for the matching process.
		 */
		private class _MatchChecks
		{
			«FOR matchCheck : getSortedMatchChecks(grammarAnalysis)»
			private final @NonNull «newTypeReference(CardinalityExpression)» «getMatchCheckId(matchCheck, false)» /* «matchCheck.toString()» */
				= «generateMatchCheck(matchCheck)»;
			«ENDFOR»
		}
		'''
	}
	
	protected def generateMatchCheck(CardinalityExpression matchCheck) {
		switch matchCheck {
		EAttributeCardinalityExpression: return generateMatchCheck_EAttributeCardinalityExpression(matchCheck)
		EReferenceCardinalityExpression: return generateMatchCheck_EReferenceCardinalityExpression(matchCheck)
		EStructuralFeatureCardinalityExpression: return generateMatchCheck_EStructuralFeatureCardinalityExpression(matchCheck)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateMatchCheck_EAttributeCardinalityExpression(EAttributeCardinalityExpression matchCheck) {
		'''new «newTypeReference(EAttributeCardinalityExpression)»("«matchCheck.getName()»", «emitLiteral(matchCheck.getEAttribute())», «getEnumValueId(matchCheck.getEnumerationValue(), true)»)'''
	}
	
	protected def generateMatchCheck_EReferenceCardinalityExpression(EReferenceCardinalityExpression matchCheck) {
		'''new «newTypeReference(EReferenceCardinalityExpression)»("«matchCheck.getName()»", «emitLiteral(matchCheck.getEReference())», «getRuleValueId(matchCheck.getParserRuleValue(), true)»)'''
	}
	
	protected def generateMatchCheck_EStructuralFeatureCardinalityExpression(EStructuralFeatureCardinalityExpression matchCheck) {
		'''new «newTypeReference(EStructuralFeatureCardinalityExpression)»("«matchCheck.getName()»", «emitLiteral(matchCheck.getEStructuralFeature())»)'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateMatchSteps(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * Steps for the matching process.
		 */
		private class _MatchSteps
		{
			«FOR step : getSortedSolutionSteps(grammarAnalysis)»
			private final @NonNull «newTypeReference(CardinalitySolutionStep)» «getSolutionStepId(step, false)» // «step.toString()»
				= «generateSolutionStep(step)»;
			«ENDFOR»
		}
		'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateMatchTerms(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * Expression terms used during the matching process.
		 */
		private class _MatchTerms
		{
			«FOR solution : getSortedSolutions(grammarAnalysis)»
			private final @NonNull «newTypeReference(CardinalitySolution)» «getSolutionId(solution, false)» // «solution.toString()»
				= «generateMatchTerm(solution)»;
			«ENDFOR»
		}
		'''
	}

	protected def generateMatchTerm(CardinalitySolution solution) {
		switch solution {
		AddCardinalitySolution: return generateMatchTerm_AddCardinalitySolution(solution)
		DivideCardinalitySolution: return generateMatchTerm_DivideCardinalitySolution(solution)
		EAttributeSizeCardinalitySolution: return generateMatchTerm_EAttributeSizeCardinalitySolution(solution)
		EReferenceSizeCardinalitySolution: return generateMatchTerm_EReferenceSizeCardinalitySolution(solution)
		EStructuralFeatureSizeCardinalitySolution: return generateMatchTerm_EStructuralFeatureSizeCardinalitySolution(solution)
		GreaterThanCardinalitySolution: return generateMatchTerm_GreaterThanCardinalitySolution(solution)
		IntegerCardinalitySolution: return generateMatchTerm_IntegerSolution(solution)
		MultiplyCardinalitySolution: return generateMatchTerm_MultiplyCardinalitySolution(solution)
		SubtractCardinalitySolution: return generateMatchTerm_SubtractCardinalitySolution(solution)
		VariableCardinalitySolution: return generateMatchTerm_VariableCardinalitySolution(solution)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateMatchTerm_AddCardinalitySolution(AddCardinalitySolution solution) {
		'''new «newTypeReference(AddCardinalitySolution)»(«getSolutionId(solution.getLeft(), false)», «getSolutionId(solution.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_DivideCardinalitySolution(DivideCardinalitySolution solution) {
		'''new «newTypeReference(DivideCardinalitySolution)»(«getSolutionId(solution.getLeft(), false)», «getSolutionId(solution.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_EAttributeSizeCardinalitySolution(EAttributeSizeCardinalitySolution solution) {
		'''new «newTypeReference(EAttributeSizeCardinalitySolution)»(«emitLiteral(solution.getEAttribute())», «getEnumValueId(solution.getEnumerationValue(), true)»)'''
	}
	
	protected def generateMatchTerm_EReferenceSizeCardinalitySolution(EReferenceSizeCardinalitySolution solution) {
		'''new «newTypeReference(EReferenceSizeCardinalitySolution)»(«emitLiteral(solution.getEReference())», "«solution.getParserRuleValue().getName()»")'''
	}
	
	protected def generateMatchTerm_EStructuralFeatureSizeCardinalitySolution(EStructuralFeatureSizeCardinalitySolution solution) {
		'''new «newTypeReference(EStructuralFeatureSizeCardinalitySolution)»(«emitLiteral(solution.getEStructuralFeature())»)'''
	}
	
	protected def generateMatchTerm_GreaterThanCardinalitySolution(GreaterThanCardinalitySolution solution) {
		'''new «newTypeReference(GreaterThanCardinalitySolution)»(«getSolutionId(solution.getLeft(), false)», «getSolutionId(solution.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_IntegerSolution(IntegerCardinalitySolution solution) {
		'''new «newTypeReference(IntegerCardinalitySolution)»(«solution.getValue()»)'''
	}
	
	protected def generateMatchTerm_MultiplyCardinalitySolution(MultiplyCardinalitySolution solution) {
		'''new «newTypeReference(MultiplyCardinalitySolution)»(«getSolutionId(solution.getLeft(), false)», «getSolutionId(solution.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_SubtractCardinalitySolution(SubtractCardinalitySolution solution) {
		'''new «newTypeReference(SubtractCardinalitySolution)»(«getSolutionId(solution.getLeft(), false)», «getSolutionId(solution.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_VariableCardinalitySolution(VariableCardinalitySolution solution) {
		'''new «newTypeReference(VariableCardinalitySolution)»(«solution.getVariableIndex()»)'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateRuleValues(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * The various serialization rules for each grammar rule.
		 */
		private class _RuleValues
		{
			«FOR ruleValue : getSortedRuleValues(grammarAnalysis)»
			«generateRuleValue(grammarAnalysis, ruleValue)»
			«ENDFOR»
		}
		'''
	}
	
	protected def generateRuleValue(GrammarAnalysis grammarAnalysis, AbstractRuleValue ruleValue) {
		switch ruleValue {
		DataTypeRuleValue: return generateRuleValue_DataTypeRule(grammarAnalysis, ruleValue)
		ParserRuleValue: return generateRuleValue_ParserRuleValue(grammarAnalysis, ruleValue)
		TerminalRuleValue: return generateRuleValue_TerminalRuleValue(grammarAnalysis, ruleValue)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateRuleValue_DataTypeRule(GrammarAnalysis grammarAnalysis, DataTypeRuleValue ruleValue) {
		'''private final @NonNull «newTypeReference(DataTypeRuleValue)» «getRuleValueId(ruleValue, false)» // «ruleValue.getName()»
	= new «newTypeReference(DataTypeRuleValue)»(«ruleValue.getIndex()», "«ruleValue.getName()»");'''
	}
	
	protected def generateRuleValue_ParserRuleValue(GrammarAnalysis grammarAnalysis, ParserRuleValue ruleValue) {
			// «FOR subParserRuleValue : subParserRuleValueClosure SEPARATOR ','» «getParserRuleValueId(subParserRuleValue, true)» /* «subParserRuleValue.getName()» */«ENDFOR» */'''
		var subParserRuleValueIndexes = ruleValue.getSubParserRuleValueIndexes();
		if (subParserRuleValueIndexes !== null) {
			'''
			private final @NonNull «newTypeReference(ParserRuleValue)» «getRuleValueId(ruleValue, false)» // «ruleValue.getName()»
				= new «newTypeReference(ParserRuleValue)»(«ruleValue.getIndex()», "«ruleValue.getName()»",
					new @NonNull «newTypeReference(SerializationRule)» [] {
						«FOR serializationRule : grammarAnalysis.getSerializationRules(ruleValue) SEPARATOR ','»
						«getSerializationRuleId(serializationRule, true)» /* «serializationRule.toRuleString()» */
						«ENDFOR»
					}, 
					«getIndexVectorId(subParserRuleValueIndexes, true)»); /* «FOR index : subParserRuleValueIndexes SEPARATOR '|'»«getRuleName(index)»«ENDFOR» */
			'''
		}
		else {
			'''
			private final @NonNull «newTypeReference(ParserRuleValue)» «getRuleValueId(ruleValue, false)» // «ruleValue.getName()»
				= new «newTypeReference(ParserRuleValue)»(«ruleValue.getIndex()», "«ruleValue.getName()»", 
					new @NonNull «newTypeReference(SerializationRule)» [] {
						«FOR serializationRule : grammarAnalysis.getSerializationRules(ruleValue) SEPARATOR ','»
						«getSerializationRuleId(serializationRule, true)» /* «serializationRule.toRuleString()» */
						«ENDFOR»
					}, 
					(«newTypeReference(IndexVector)»)null);
			'''
		}
	}
	
	protected def generateRuleValue_TerminalRuleValue(GrammarAnalysis grammarAnalysis, TerminalRuleValue ruleValue) {
		'''private final @NonNull «newTypeReference(TerminalRuleValue)» «getRuleValueId(ruleValue, false)» // «ruleValue.getName()»
	= new «newTypeReference(TerminalRuleValue)»(«ruleValue.getIndex()», "«ruleValue.getName()»");'''
	}
	
	/* ************************************************************************************************************************** */	

	protected def generateSerializationRules(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * The various serialization rules that serialize an EClass.
		 */
		«FOR page : getSortedSerializationRulePages(grammarAnalysis)»
		private class _SerializationRules«page»
		{
			«FOR serializationRule : getSortedSerializationRules(grammarAnalysis, page)»
			«generateSerializationRule(serializationRule)»
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
				«getSolutionStepId(solutionStep, true)» /* «solutionStep.toString()» */
				«ENDFOR»
			}, 
			new @NonNull «newTypeReference(RTSerializationStep)» @NonNull [] {
				«FOR serializationStep : serializationRule.getSerializationSteps() SEPARATOR ','»
				«getSerializationStepId(serializationStep, true)» /* «serializationStep.toString()» */
				«ENDFOR»
			}, 
			«IF serializationRule.getStaticSegments() !== null»
			new @NonNull «newTypeReference(Segment)» @NonNull [] [] {
				«IF serializationRule.getStaticSegments() !== null»
				«FOR segments : serializationRule.getStaticSegments() SEPARATOR ','»
				«IF segments !== null»
				«getSegmentsId(segments, true)» /* «FOR segment : segments SEPARATOR ' + '»«segment.toString()»«ENDFOR» */
				«ELSE»
				null
				«ENDIF»
				«ENDFOR»
				«ENDIF»
			},
			«ELSE»
			null,
			«ENDIF»
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
			«var eStructuralFeature2cardinalityExpression = serializationRuleAnalysis.basicGetEStructuralFeature2cardinalityExpression()»
			«IF eStructuralFeature2cardinalityExpression !== null»
			new @NonNull «newTypeReference(EStructuralFeature_CardinalityExpression)» [] {
				«FOR eStructuralFeatureData : eStructuralFeature2cardinalityExpression SEPARATOR ','»
				new «newTypeReference(EStructuralFeature_CardinalityExpression)»(«emitLiteral(eStructuralFeatureData.getEStructuralFeature())»,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* «eStructuralFeatureData.getCardinalityExpression()» */
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
			«FOR segments : getSortedSegments(grammarAnalysis)»
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
	
	protected def generateSerializationTerms(GrammarAnalysis grammarAnalysis) {
		'''
		/**
		 * The various serialization term used to serialize a serialization rule.
		 */
		private class _SerializationTerms
		{
			«FOR step : getSortedSerializationSteps(grammarAnalysis)»
			«generateSerializationTerm1(step)»;
			«ENDFOR»
			
			/**
			 * Post constructor initialization that avoids recursions.
			 */
			private final void init() {
				«FOR step : getSortedSerializationSteps(grammarAnalysis)»«generateSerializationTerm2(step)»«ENDFOR»
			}
		}
		'''
	}
	
	protected def generateSerializationTerm1(RTSerializationStep serializationStep) {
		switch serializationStep {
		RTSerializationAssignStep: return generateSerializationTerm1_Assign(serializationStep)
		RTSerializationAssignedRuleCallStep: return generateSerializationTerm1_AssignedRuleCall(serializationStep)
		RTSerializationAssignedRuleCallsStep: return generateSerializationTerm1_AssignedRuleCalls(serializationStep)
		RTSerializationCrossReferenceStep: return generateSerializationTerm1_CrossReference(serializationStep)
		RTSerializationLiteralStep: return generateSerializationTerm1_Literal(serializationStep)
		RTSerializationSequenceStep: return generateSerializationTerm1_Sequence(serializationStep)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateSerializationTerm1_Assign(RTSerializationAssignStep serializationStep) {
		'''private final @NonNull «newTypeReference(RTSerializationAssignStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(RTSerializationAssignStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»)'''
	}
	
	protected def generateSerializationTerm1_AssignedRuleCall(RTSerializationAssignedRuleCallStep serializationStep) {
		'''private final @NonNull «newTypeReference(RTSerializationAssignedRuleCallStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(RTSerializationAssignedRuleCallStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»)'''
	}
	
	protected def generateSerializationTerm1_AssignedRuleCalls(RTSerializationAssignedRuleCallsStep serializationStep) {
		'''private final @NonNull «newTypeReference(RTSerializationAssignedRuleCallsStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(RTSerializationAssignedRuleCallsStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»,
		 new @NonNull «newTypeReference(AbstractRuleValue)» [«serializationStep.getCalledRuleValues().size()»])'''
	}

	protected def generateSerializationTerm1_CrossReference(RTSerializationCrossReferenceStep serializationStep) {
		'''private final @NonNull «newTypeReference(RTSerializationCrossReferenceStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(RTSerializationCrossReferenceStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())», "«emitCalledRule(serializationStep.getCrossReference())»")'''
	}
	
	protected def generateSerializationTerm1_Literal(RTSerializationLiteralStep serializationStep) {
		'''private final @NonNull «newTypeReference(RTSerializationLiteralStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(RTSerializationLiteralStep)»(«serializationStep.getVariableIndex()», "«Strings.convertToJavaString(serializationStep.getString())»")'''
	}
	
	protected def generateSerializationTerm1_Sequence(RTSerializationSequenceStep serializationStep) {
		'''private final @NonNull «newTypeReference(RTSerializationSequenceStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(RTSerializationSequenceStep)»(«serializationStep.getVariableIndex()», «serializationStep.getStartIndex()», «serializationStep.getEndIndex()»)'''
	}
	
	protected def generateSerializationTerm2(RTSerializationStep serializationStep) {
		switch serializationStep {
		RTSerializationAssignedRuleCallStep: return generateSerializationTerm2_AssignedRuleCall(serializationStep)
		RTSerializationAssignedRuleCallsStep: return generateSerializationTerm2_AssignedRuleCalls(serializationStep)
		default: return ""
		}
	}
	
	protected def generateSerializationTerm2_AssignedRuleCall(RTSerializationAssignedRuleCallStep serializationStep) {
		'''«getSerializationStepId(serializationStep, false)».init(«getRuleValueId(serializationStep.getCalledRuleValue(), true)/*«serializationStep.getCalledRuleValue().getName()»*/»);
		'''
	}
	
	protected def generateSerializationTerm2_AssignedRuleCalls(RTSerializationAssignedRuleCallsStep serializationStep) {
		'''«getSerializationStepId(serializationStep, false)».init(new @NonNull «newTypeReference(AbstractRuleValue)» [] {«FOR calledRuleValue : serializationStep.getCalledRuleValues() SEPARATOR ', '»«getRuleValueId(calledRuleValue, true)»/*«calledRuleValue.getName()»*/«ENDFOR»});
		'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateSolutionStep(CardinalitySolutionStep solutionStep) {
		switch solutionStep {
		CardinalitySolutionStep.CardinalitySolutionStep_Assert: return generateSolutionStep_Assert(solutionStep)
		CardinalitySolutionStep.CardinalitySolutionStep_Assign: return generateSolutionStep_Assign(solutionStep)
		CardinalitySolutionStep.CardinalitySolutionStep_RuleCheck: return generateSolutionStep_RuleCheck(solutionStep)
		CardinalitySolutionStep.CardinalitySolutionStep_ValueCheck: return generateSolutionStep_ValueCheck(solutionStep)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateSolutionStep_Assert(CardinalitySolutionStep.CardinalitySolutionStep_Assert solutionStep) {
		'''new «newTypeReference(org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep$CardinalitySolutionStep_Assert)»(«getSolutionId(solutionStep.getCardinalitySolution(), true)»)'''
	}
	
	protected def generateSolutionStep_Assign(CardinalitySolutionStep.CardinalitySolutionStep_Assign solutionStep) {
		'''new «newTypeReference(org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep$CardinalitySolutionStep_Assign)»(«solutionStep.getVariableIndex()», «getSolutionId(solutionStep.getCardinalitySolution(), true)»)'''
	}
	
	protected def generateSolutionStep_RuleCheck(CardinalitySolutionStep.CardinalitySolutionStep_RuleCheck solutionStep) {
		'''new «newTypeReference(org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep$CardinalitySolutionStep_RuleCheck)»(«emitLiteral(solutionStep.getEReference())», «getIndexVectorId(solutionStep.getRuleValueIndexes(), true)»/*«FOR index : solutionStep.getRuleValueIndexes() SEPARATOR '|' »«grammarAnalysis.getRuleName(index)»«ENDFOR»*/)'''
	}
	
	protected def generateSolutionStep_ValueCheck(CardinalitySolutionStep.CardinalitySolutionStep_ValueCheck solutionStep) {
		'''new «newTypeReference(org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep$CardinalitySolutionStep_ValueCheck)»(«solutionStep.getVariableIndex()», «getSolutionId(solutionStep.getCardinalitySolution(), true)»)'''
	}
}
