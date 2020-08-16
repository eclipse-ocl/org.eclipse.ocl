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

import com.google.common.collect.Lists
import java.util.List
import org.eclipse.emf.ecore.EClass
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
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep
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
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis
import org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules
import org.eclipse.xtext.util.Strings
import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis
import org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleAnalysis
import org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleAnalysis
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue
import java.util.ArrayList
import org.eclipse.ocl.pivot.utilities.ClassUtil
import org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector
import org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue
import org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue

/**
 * DeclarativeSerializerFragmentXtend augments DeclarativeSerializerFragment with M2T functionality
 * exploiting Xtend's string template capabilities.
 */
class DeclarativeSerializerFragmentXtend extends DeclarativeSerializerFragment
{
	protected override doGetAnalysisProviderContent(GrammarAnalysis grammarAnalysis) {
		initAnalysisProviderContent(grammarAnalysis);
		'''
		public class «getAnalysisProviderClass(grammar).simpleName» extends «getAnalysisProviderSuperClass(grammar)»
		{
			private static «new TypeReference(RTGrammarAnalysis)» analysis = null;
		
			@Override
			public «new TypeReference(RTGrammarAnalysis)» getAnalysis() {
				if (analysis == null) {
					analysis = new «new TypeReference(RTGrammarAnalysis)»();
				}
				«FOR eClass : getSortedEClasses(grammarAnalysis)»
					analysis.addEClassData(«getEClassId(eClass, true)»);
				«ENDFOR»
				return analysis;
			}
			
			private class _EnumValues
			{
				«FOR enumValue : getSortedEnumValues(grammarAnalysis)»
				private final /*@NonNull*/ «new TypeReference(EnumerationValue)» «getEnumValueId(enumValue, false)» // «enumValue.toString()»
					= «generateEnumValue(enumValue)»;
				«ENDFOR»
			}
			
			private class _MatchTerms
			{
				«FOR solution : getSortedSolutions(grammarAnalysis)»
				private final /*@NonNull*/ «new TypeReference(CardinalitySolution)» «getSolutionId(solution, false)» // «solution.toString()»
					= «generateSolution(solution)»;
				«ENDFOR»
			}
			
			private class _MatchSteps
			{
				«FOR step : getSortedSolutionSteps(grammarAnalysis)»
				private final /*@NonNull*/ «new TypeReference(CardinalitySolutionStep)» «getSolutionStepId(step, false)» // «step.toString()»
					= «generateSolutionStep(step)»;
				«ENDFOR»
			}
			
			private class _SerializationTerms
			{
				«FOR step : getSortedSerializationSteps(grammarAnalysis)»
				private final /*@NonNull*/ «new TypeReference(RTSerializationStep)» «getSerializationStepId(step, false)» // «step.toString()»
					= «generateSerializationStep(step)»;
				«ENDFOR»
			}
			
			private class _SerializationSegments
			{
				«FOR segments : getSortedSegments(grammarAnalysis)»
				private final /*@NonNull*/ «new TypeReference(Segment)» [] «getSegmentsId(segments, false)» // «segments»
					= «generateSegments(segments)»;
				«ENDFOR»
			}
						
			private class _RuleValues
			{
				«FOR ruleValue : getSortedRuleValues(grammarAnalysis)»
				«generateRuleValue(grammarAnalysis, ruleValue)»
				«ENDFOR»
			}
						
			private class _EClassData
			{
				«FOR eClass : getSortedEClasses(grammarAnalysis)»
				private final /*@NonNull*/ «new TypeReference(EClassData)» «getEClassId(eClass, false)» // «eClass.getName()»
					= «generateEClassData(grammarAnalysis, eClass)»;
				«ENDFOR»
			}
						
			private class _SerializationRules
			{
				«FOR serializationRule : getSortedSerializationRules(grammarAnalysis)»
				private final /*@NonNull*/ «new TypeReference(RTSerializationRule)» «getSerializationRuleId(serializationRule, false)»
					= «generateSerializationRule(serializationRule)»;
				«ENDFOR»
			}
			
			private final _EClassData ec = new _EClassData();
			private final _EnumValues ev = new _EnumValues();
			private final _MatchSteps ms = new _MatchSteps();
			private final _MatchTerms mt = new _MatchTerms();
			private final _RuleValues rv = new _RuleValues();
			private final _SerializationRules sr = new _SerializationRules();
			private final _SerializationSegments ss = new _SerializationSegments();
			private final _SerializationTerms st = new _SerializationTerms();
		}
		'''
	}
	
	protected def generateSerializationRule(RTSerializationRule serializationRule) {
'''/* «serializationRule.toRuleString()» */
new «new TypeReference(RTSerializationRule)»(
	new /*@NonNull*/ «new TypeReference(CardinalitySolutionStep)» /*@NonNull*/ []{
		«FOR solutionStep : serializationRule.getBasicSerializationRule().getStaticRuleMatch().getSteps() SEPARATOR ','»
		«getSolutionStepId(solutionStep, true)» /* «solutionStep.toString()» */
		«ENDFOR»
	}, 
	new /*@NonNull*/ «new TypeReference(RTSerializationStep)» /*@NonNull*/ []{
		«FOR serializationStep : serializationRule.getSerializationSteps() SEPARATOR ','»
		«getSerializationStepId(serializationStep, true)» /* «serializationStep.toString()» */
		«ENDFOR»
	}, 
	«IF serializationRule.getStaticSegments() != null»
	new /*@NonNull*/ «new TypeReference(Segment)» /*@NonNull*/ [] []{
		«IF serializationRule.getStaticSegments() != null»
		«FOR segments : serializationRule.getStaticSegments() SEPARATOR ','»
		«IF segments != null»
		«getSegmentsId(segments, true)» /* «FOR segment : segments SEPARATOR ' + '»«segment.toString()»«ENDFOR» */
		«ELSE»
		null
		«ENDIF»
		«ENDFOR»
		«ENDIF»
	}
	«ELSE»
	null
	«ENDIF»
)'''
	}
	
/*	protected def generateSerializationRules(GrammarAnalysis grammarAnalysis, EClass eClass) {
		'''
		/**
		 * «eClass.getName()»
		 * /
		private static «new TypeReference(RTSerializationRules)» create_«eClass.getName()»_Rules() {
			return new «new TypeReference(RTSerializationRules)»(«emitLiteral(eClass)», «new TypeReference(Lists)».newArrayList(
				«FOR serializationRule : grammarAnalysis.getSerializationRules(eClass).getSerializationRules() SEPARATOR ','»
				«generateSerializationRule(serializationRule.getBasicSerializationRule().getRuntime())»
				«ENDFOR»
			), 0);
		}
		'''
	} */
	
/*	protected def generateSerializationRules(GrammarAnalysis grammarAnalysis) {
		'''
			«FOR eClass : grammarAnalysis.getSortedProducedEClasses() SEPARATOR '\n'»
			«generateSerializationRules(grammarAnalysis, eClass)»
			«ENDFOR»
		'''
	} */
	
	/* ************************************************************************************************************************** */
	
	protected def generateSegment(Segment segment) {
		switch segment {
		CustomSegment: return generateSegment_Custom(segment)
		HalfNewLineSegment: return generateSegment_HalfNewLine(segment)
		NewLineSegment: return generateSegment_NewLine(segment)
		NoSpaceSegment: return generateSegment_NoSpace(segment)
		PopSegment: return generateSegment_Pop(segment)
		PushSegment: return generateSegment_Push(segment)
		SoftNewLineSegment: return generateSegment_SoftNewLine(segment)
		SoftSpaceSegment: return generateSegment_SoftSpace(segment)
		StringSegment: return generateSegment_String(segment)
		ValueSegment: return generateSegment_Value(segment)
		default: segment.getClass().getName() //throw new UnsupportedOperationException()
		}
	}
	
	protected def generateSegments(List<Segment> segments) {
		'''
		new «new TypeReference(Segment)» /*@NonNull*/ [] {
			«FOR segment : segments SEPARATOR ',\n'»«generateSegment(segment)» /* «segment.toString()» */«ENDFOR»}
		'''
	}
	
	protected def generateSegment_Custom(CustomSegment segment) {
		'''«new TypeReference(IdiomsUtils)».createCustomSegment(null, «new TypeReference(segment.getSupportClassName())».class)'''
	}
	
	protected def generateSegment_HalfNewLine(HalfNewLineSegment segment) {
		'''«new TypeReference(IdiomsUtils)».HALF_NEW_LINE'''
	}
	
	protected def generateSegment_NewLine(NewLineSegment segment) {
		'''«new TypeReference(IdiomsUtils)».NEW_LINE'''
	}
	
	protected def generateSegment_NoSpace(NoSpaceSegment segment) {
		'''«new TypeReference(IdiomsUtils)».NO_SPACE'''
	}
	
	protected def generateSegment_Pop(PopSegment segment) {
		'''«new TypeReference(IdiomsUtils)».POP'''
	}
	
	protected def generateSegment_Push(PushSegment segment) {
		'''«new TypeReference(IdiomsUtils)».PUSH'''
	}
	
	protected def generateSegment_SoftNewLine(SoftNewLineSegment segment) {
		'''«new TypeReference(IdiomsUtils)».SOFT_NEW_LINE'''
	}
	
	protected def generateSegment_SoftSpace(SoftSpaceSegment segment) {
		'''«new TypeReference(IdiomsUtils)».SOFT_SPACE'''
	}
	
	protected def generateSegment_String(StringSegment segment) {
		'''«new TypeReference(IdiomsUtils)».createStringSegment(«segment.getString()»)'''
	}
	
	protected def generateSegment_Value(ValueSegment segment) {
		'''«new TypeReference(IdiomsUtils)».VALUE'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateSerializationStep(RTSerializationStep serializationStep) {
		switch serializationStep {
		RTSerializationAssignStep: return generateSerializationStep_Assign(serializationStep)
		RTSerializationAssignedRuleCallStep: return generateSerializationStep_AssignedRuleCall(serializationStep)
		RTSerializationAssignedRuleCallsStep: return generateSerializationStep_AssignedRuleCalls(serializationStep)
		RTSerializationCrossReferenceStep: return generateSerializationStep_CrossReference(serializationStep)
		RTSerializationLiteralStep: return generateSerializationStep_Literal(serializationStep)
		RTSerializationSequenceStep: return generateSerializationStep_Sequence(serializationStep)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateSerializationStep_Assign(RTSerializationAssignStep serializationStep) {
		'''new «new TypeReference(RTSerializationAssignStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»)'''
	}
	
	protected def generateSerializationStep_AssignedRuleCall(RTSerializationAssignedRuleCallStep serializationStep) {
		'''new «new TypeReference(RTSerializationAssignedRuleCallStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»,
		«getRuleValueId(serializationStep.getCalledRuleValue(), true)/*«serializationStep.getCalledRuleValue().getName()»*/») '''
	}
	
	protected def generateSerializationStep_AssignedRuleCalls(RTSerializationAssignedRuleCallsStep serializationStep) {
		'''new «new TypeReference(RTSerializationAssignedRuleCallsStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»,
		 new «new TypeReference(AbstractRuleValue)» [] {«FOR calledRuleValue : serializationStep.getCalledRuleValues() SEPARATOR ', '»«getRuleValueId(calledRuleValue, true)»/*«calledRuleValue.getName()»*/«ENDFOR»})'''
	}
	
	protected def generateSerializationStep_CrossReference(RTSerializationCrossReferenceStep serializationStep) {
		'''new «new TypeReference(RTSerializationCrossReferenceStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»)'''
	}
	
	protected def generateSerializationStep_Literal(RTSerializationLiteralStep serializationStep) {
		'''new «new TypeReference(RTSerializationLiteralStep)»(«serializationStep.getVariableIndex()», "«Strings.convertToJavaString(serializationStep.getString())»")'''
	}
	
	protected def generateSerializationStep_Sequence(RTSerializationSequenceStep serializationStep) {
		'''new «new TypeReference(RTSerializationSequenceStep)»(«serializationStep.getVariableIndex()», «serializationStep.getStartIndex()», «serializationStep.getEndIndex()»)'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateSolution(CardinalitySolution solution) {
		switch solution {
		AddCardinalitySolution: return generateSolution_AddCardinalitySolution(solution)
		DivideCardinalitySolution: return generateSolution_DivideCardinalitySolution(solution)
		EAttributeSizeCardinalitySolution: return generateSolution_EAttributeSizeCardinalitySolution(solution)
		EReferenceSizeCardinalitySolution: return generateSolution_EReferenceSizeCardinalitySolution(solution)
		EStructuralFeatureSizeCardinalitySolution: return generateSolution_EStructuralFeatureSizeCardinalitySolution(solution)
		GreaterThanCardinalitySolution: return generateSolution_GreaterThanCardinalitySolution(solution)
		IntegerCardinalitySolution: return generateSolution_IntegerSolution(solution)
		MultiplyCardinalitySolution: return generateSolution_MultiplyCardinalitySolution(solution)
		SubtractCardinalitySolution: return generateSolution_SubtractCardinalitySolution(solution)
		VariableCardinalitySolution: return generateSolution_VariableCardinalitySolution(solution)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateSolution_AddCardinalitySolution(AddCardinalitySolution solution) {
		'''new «new TypeReference(AddCardinalitySolution)»(«getSolutionId(solution.getLeft(), true)», «getSolutionId(solution.getRight(), true)»)'''
	}
	
	protected def generateSolution_DivideCardinalitySolution(DivideCardinalitySolution solution) {
		'''new «new TypeReference(DivideCardinalitySolution)»(«getSolutionId(solution.getLeft(), true)», «getSolutionId(solution.getRight(), true)»)'''
	}
	
	protected def generateSolution_EAttributeSizeCardinalitySolution(EAttributeSizeCardinalitySolution solution) {
		'''new «new TypeReference(EAttributeSizeCardinalitySolution)»(«emitLiteral(solution.getEAttribute())», «getEnumValueId(solution.getEnumerationValue(), true)»)'''
	}
	
	protected def generateSolution_EReferenceSizeCardinalitySolution(EReferenceSizeCardinalitySolution solution) {
		'''new «new TypeReference(EReferenceSizeCardinalitySolution)»(«emitLiteral(solution.getEReference())», "«solution.getParserRuleValue().getName()»")'''
	}
	
	protected def generateSolution_EStructuralFeatureSizeCardinalitySolution(EStructuralFeatureSizeCardinalitySolution solution) {
		'''new «new TypeReference(EStructuralFeatureSizeCardinalitySolution)»(«emitLiteral(solution.getEStructuralFeature())»)'''
	}
	
	protected def generateSolution_GreaterThanCardinalitySolution(GreaterThanCardinalitySolution solution) {
		'''new «new TypeReference(GreaterThanCardinalitySolution)»(«getSolutionId(solution.getLeft(), true)», «getSolutionId(solution.getRight(), true)»)'''
	}
	
	protected def generateSolution_IntegerSolution(IntegerCardinalitySolution solution) {
		'''new «new TypeReference(IntegerCardinalitySolution)»(«solution.getValue()»)'''
	}
	
	protected def generateSolution_MultiplyCardinalitySolution(MultiplyCardinalitySolution solution) {
		'''new «new TypeReference(MultiplyCardinalitySolution)»(«getSolutionId(solution.getLeft(), true)», «getSolutionId(solution.getRight(), true)»)'''
	}
	
	protected def generateSolution_SubtractCardinalitySolution(SubtractCardinalitySolution solution) {
		'''new «new TypeReference(SubtractCardinalitySolution)»(«getSolutionId(solution.getLeft(), true)», «getSolutionId(solution.getRight(), true)»)'''
	}
	
	protected def generateSolution_VariableCardinalitySolution(VariableCardinalitySolution solution) {
		'''new «new TypeReference(VariableCardinalitySolution)»(«solution.getVariableIndex()»)'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateSolutionStep(CardinalitySolutionStep solutionStep) {
		switch solutionStep {
		CardinalitySolutionStep.Assert: return generateSolutionStep_Assert(solutionStep)
		CardinalitySolutionStep.Assign: return generateSolutionStep_Assign(solutionStep)
		CardinalitySolutionStep.RuleCheck: return generateSolutionStep_RuleCheck(solutionStep)
		CardinalitySolutionStep.ValueCheck: return generateSolutionStep_ValueCheck(solutionStep)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateSolutionStep_Assert(CardinalitySolutionStep.Assert solutionStep) {
		'''new «new TypeReference(CardinalitySolutionStep.Assert)»(«getSolutionId(solutionStep.getCardinalitySolution(), true)»)'''
	}
	
	protected def generateSolutionStep_Assign(CardinalitySolutionStep.Assign solutionStep) {
		'''new «new TypeReference(CardinalitySolutionStep.Assign)»(«solutionStep.getVariableIndex()», «getSolutionId(solutionStep.getCardinalitySolution(), true)»)'''
	}
	
	protected def generateSolutionStep_RuleCheck(CardinalitySolutionStep.RuleCheck solutionStep) {
		'''new «new TypeReference(CardinalitySolutionStep.RuleCheck)»(«emitLiteral(solutionStep.getEReference())», new String[]{«FOR ruleAnalysis : solutionStep.getRuleAnalyses() SEPARATOR ', '»"«ruleAnalysis»"«ENDFOR»})'''
	}
	
	protected def generateSolutionStep_ValueCheck(CardinalitySolutionStep.ValueCheck solutionStep) {
		'''new «new TypeReference(CardinalitySolutionStep.ValueCheck)»(«solutionStep.getVariableIndex()», «getSolutionId(solutionStep.getCardinalitySolution(), true)»)'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateEnumValue(EnumerationValue enumValue) {
		switch enumValue {
		MultipleEnumerationValue: return generateEnumValue_MultipleEnumerationValue(enumValue)
		OthersEnumerationValue: return generateEnumValue_OthersEnumerationValue(enumValue)
		SingleEnumerationValue: return generateEnumValue_SingleEnumerationValue(enumValue)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateEnumValue_MultipleEnumerationValue(MultipleEnumerationValue enumValue) {
		'''new «new TypeReference(MultipleEnumerationValue)»(new String[]{«FOR value : enumValue.getValues() SEPARATOR ', '»"«value»"«ENDFOR»})'''
	}
	
	protected def generateEnumValue_OthersEnumerationValue(OthersEnumerationValue enumValue) {
		'''new «new TypeReference(OthersEnumerationValue)»()'''
	}
	
	protected def generateEnumValue_SingleEnumerationValue(SingleEnumerationValue enumValue) {
		'''new «new TypeReference(SingleEnumerationValue)»("«enumValue.getName()»")'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateRuleValue(GrammarAnalysis grammarAnalysis, AbstractRuleValue ruleValue) {
		switch ruleValue {
		DataTypeRuleValue: return generateRuleValue_DataTypeRule(grammarAnalysis, ruleValue)
		ParserRuleValue: return generateRuleValue_ParserRuleValue(grammarAnalysis, ruleValue)
		TerminalRuleValue: return generateRuleValue_TerminalRuleValue(grammarAnalysis, ruleValue)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateRuleValue_DataTypeRule(GrammarAnalysis grammarAnalysis, DataTypeRuleValue ruleValue) {
		'''private final /*@NonNull*/ «new TypeReference(DataTypeRuleValue)» «getRuleValueId(ruleValue, false)» // «ruleValue.getName()»
	= new «new TypeReference(DataTypeRuleValue)»(«ruleValue.getIndex()», "«ruleValue.getName()»");'''
	}
	
	protected def generateRuleValue_ParserRuleValue(GrammarAnalysis grammarAnalysis, ParserRuleValue ruleValue) {
			// «FOR subParserRuleValue : subParserRuleValueClosure SEPARATOR ','» «getParserRuleValueId(subParserRuleValue, true)» /* «subParserRuleValue.getName()» */«ENDFOR» */'''
		var subParserRuleValueIndexes = ruleValue.getSubParserRuleValueIndexes();
		if (subParserRuleValueIndexes !== null) {
		'''private final /*@NonNull*/ «new TypeReference(ParserRuleValue)» «getRuleValueId(ruleValue, false)» // «ruleValue.getName()»
	= new «new TypeReference(ParserRuleValue)»(«ruleValue.getIndex()», "«ruleValue.getName()»",
	new «new TypeReference(RTSerializationRule)» [] {«FOR serializationRule : grammarAnalysis.getSerializationRules(ruleValue) SEPARATOR ','»
			«getSerializationRuleId(serializationRule, true)» /* «serializationRule.toRuleString()» */
			«ENDFOR»},
			new «new TypeReference(IndexVector)»(new long[]{
	«subParserRuleValueIndexes.toWordsString()»})); // «FOR index : subParserRuleValueIndexes SEPARATOR ','»«getRuleName(index)»«ENDFOR»'''
		}
		else {
		'''private final /*@NonNull*/ «new TypeReference(ParserRuleValue)» «getRuleValueId(ruleValue, false)» // «ruleValue.getName()»
	= new «new TypeReference(ParserRuleValue)»(«ruleValue.getIndex()», "«ruleValue.getName()»", 
	new «new TypeReference(RTSerializationRule)» [] {«FOR serializationRule : grammarAnalysis.getSerializationRules(ruleValue) SEPARATOR ','»
			«getSerializationRuleId(serializationRule, true)» /* «serializationRule.toRuleString()» */
			«ENDFOR»}, («new TypeReference(IndexVector)»)null);'''
		}
	}
	
	protected def generateRuleValue_TerminalRuleValue(GrammarAnalysis grammarAnalysis, TerminalRuleValue ruleValue) {
		'''private final /*@NonNull*/ «new TypeReference(TerminalRuleValue)» «getRuleValueId(ruleValue, false)» // «ruleValue.getName()»
	= new «new TypeReference(TerminalRuleValue)»(«ruleValue.getIndex()», "«ruleValue.getName()»");'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateEClassData(GrammarAnalysis grammarAnalysis, EClass eClass) {
		switch eClass {
		EClass: return generateEClassData_EClass(grammarAnalysis, eClass)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateEClassData_EClass(GrammarAnalysis grammarAnalysis, EClass eClass) {
		'''
		new «new TypeReference(EClassData)»("«eClass.getName()»", «emitLiteral(eClass)»,
		new «new TypeReference(RTSerializationRule)» [] {«FOR serializationRule : grammarAnalysis.getSerializationRules(eClass).getSerializationRules() SEPARATOR ','»
		«getSerializationRuleId(serializationRule.getBasicSerializationRule().getRuntime(), true)» /* «serializationRule.toString()» */
		«ENDFOR»})'''
	}
}
