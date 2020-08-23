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
import org.eclipse.xtext.service.GrammarProvider
import com.google.inject.Inject
import org.eclipse.xtext.Grammar

/**
 * DeclarativeSerializerFragmentXtend augments DeclarativeSerializerFragment with M2T functionality
 * exploiting Xtend's string template capabilities.
 */
class DeclarativeSerializerFragmentXtend extends DeclarativeSerializerFragment
{
	protected override doGetAnalysisProviderContent(GrammarAnalysis grammarAnalysis) {
		initAnalysisProviderContent(grammarAnalysis);
		'''
	//	import «new TypeReference(Inject)»;
		
		public class «getAnalysisProviderClass(grammar).simpleName» extends «getAnalysisProviderSuperClass(grammar)»
		{
		//	@Inject
		//	private «new TypeReference(GrammarProvider)» grammarProvider;

			private static «new TypeReference(RTGrammarAnalysis)» analysis = null;
		
			@Override
			public «new TypeReference(RTGrammarAnalysis)» getAnalysis() {
				if (analysis == null) {
					analysis = new «new TypeReference(RTGrammarAnalysis)»();
					«FOR eClass : getSortedEClasses(grammarAnalysis)»
						analysis.addEClassData(«getEClassId(eClass, true)»);
					«ENDFOR»
					«FOR serializationRule : getSortedSerializationRules(grammarAnalysis)»
					//	analysis.addSerializationRule(«getSerializationRuleId(serializationRule, true)»);
					«ENDFOR»
				}
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
					= «generateMatchTerm(solution)»;
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
				«generateSerializationTerm1(step)»;
				«ENDFOR»
				
				private final void init() {
					«FOR step : getSortedSerializationSteps(grammarAnalysis)»«generateSerializationTerm2(step)»«ENDFOR»
				}
			}
			
			private class _SerializationSegments
			{
				«FOR segments : getSortedSegments(grammarAnalysis)»
				private final /*@NonNull*/ «new TypeReference(Segment)» [] «getSegmentsId(segments, false)» // «segments»
					= «generateSerializationSegments(segments)»;
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
			
			private _EnumValues ev;
			private _MatchTerms mt;
			private _MatchSteps ms;
			private _SerializationTerms st;
			private _SerializationSegments ss;
			private _SerializationRules sr;
			private _RuleValues rv;
			private _EClassData ec;

			@Inject
			public void init() {
			//	«new TypeReference(Grammar)» grammar = grammarProvider.getGrammar(this);
				ev = new _EnumValues();
				mt = new _MatchTerms();
				ms = new _MatchSteps();
				st = new _SerializationTerms();
				ss = new _SerializationSegments();
				sr = new _SerializationRules();
				rv = new _RuleValues();
				ec = new _EClassData();		
				st.init();
			}
		}
		'''
	}
	
	protected def generateSerializationRule(RTSerializationRule serializationRule) {
'''/* «serializationRule.toRuleString()» */
new «new TypeReference(RTSerializationRule)»(«serializationRule.getRuleValueIndex()», /* «serializationRule.getName()» */
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
	
	protected def generateEClassData(GrammarAnalysis grammarAnalysis, EClass eClass) {
		switch eClass {
		EClass: return generateEClassData_EClass(grammarAnalysis, eClass)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateEClassData_EClass(GrammarAnalysis grammarAnalysis, EClass eClass) {
		'''
		new «new TypeReference(EClassData)»("«eClass.getName()»", «emitLiteral(eClass)»,
		new «new TypeReference(RTSerializationRule)» [] {«FOR serializationRule : grammarAnalysis.getSerializationRules(eClass) SEPARATOR ','»
		«getSerializationRuleId(serializationRule.getBasicSerializationRule().getRuntime(), true)» /* «serializationRule.toString()» */
		«ENDFOR»}, «IF grammarAnalysis.basicGetEContainmentFeature2assignedTargetRuleValues(eClass) == null»null«ELSE»
			new «new TypeReference(Object)»[] {«FOR entry : grammarAnalysis.getEContainmentFeature2assignedTargetRuleValues(eClass).entrySet()»
				«entry.getKey()» /* «entry.getValue().toString()» */
				«ENDFOR»«ENDIF»)'''
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
		'''new «new TypeReference(AddCardinalitySolution)»(«getSolutionId(solution.getLeft(), false)», «getSolutionId(solution.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_DivideCardinalitySolution(DivideCardinalitySolution solution) {
		'''new «new TypeReference(DivideCardinalitySolution)»(«getSolutionId(solution.getLeft(), false)», «getSolutionId(solution.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_EAttributeSizeCardinalitySolution(EAttributeSizeCardinalitySolution solution) {
		'''new «new TypeReference(EAttributeSizeCardinalitySolution)»(«emitLiteral(solution.getEAttribute())», «getEnumValueId(solution.getEnumerationValue(), true)»)'''
	}
	
	protected def generateMatchTerm_EReferenceSizeCardinalitySolution(EReferenceSizeCardinalitySolution solution) {
		'''new «new TypeReference(EReferenceSizeCardinalitySolution)»(«emitLiteral(solution.getEReference())», "«solution.getParserRuleValue().getName()»")'''
	}
	
	protected def generateMatchTerm_EStructuralFeatureSizeCardinalitySolution(EStructuralFeatureSizeCardinalitySolution solution) {
		'''new «new TypeReference(EStructuralFeatureSizeCardinalitySolution)»(«emitLiteral(solution.getEStructuralFeature())»)'''
	}
	
	protected def generateMatchTerm_GreaterThanCardinalitySolution(GreaterThanCardinalitySolution solution) {
		'''new «new TypeReference(GreaterThanCardinalitySolution)»(«getSolutionId(solution.getLeft(), false)», «getSolutionId(solution.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_IntegerSolution(IntegerCardinalitySolution solution) {
		'''new «new TypeReference(IntegerCardinalitySolution)»(«solution.getValue()»)'''
	}
	
	protected def generateMatchTerm_MultiplyCardinalitySolution(MultiplyCardinalitySolution solution) {
		'''new «new TypeReference(MultiplyCardinalitySolution)»(«getSolutionId(solution.getLeft(), false)», «getSolutionId(solution.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_SubtractCardinalitySolution(SubtractCardinalitySolution solution) {
		'''new «new TypeReference(SubtractCardinalitySolution)»(«getSolutionId(solution.getLeft(), false)», «getSolutionId(solution.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_VariableCardinalitySolution(VariableCardinalitySolution solution) {
		'''new «new TypeReference(VariableCardinalitySolution)»(«solution.getVariableIndex()»)'''
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
	
	
	protected def generateSerializationSegments(List<Segment> segments) {
		'''
		new «new TypeReference(Segment)» /*@NonNull*/ [] {
			«FOR segment : segments SEPARATOR ',\n'»«generateSerializationSegment(segment)» /* «segment.toString()» */«ENDFOR»}
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
		'''«new TypeReference(IdiomsUtils)».createCustomSegment(null, «new TypeReference(segment.getSupportClassName())».class)'''
	}
	
	protected def generateSerializationSegment_HalfNewLine(HalfNewLineSegment segment) {
		'''«new TypeReference(IdiomsUtils)».HALF_NEW_LINE'''
	}
	
	protected def generateSerializationSegment_NewLine(NewLineSegment segment) {
		'''«new TypeReference(IdiomsUtils)».NEW_LINE'''
	}
	
	protected def generateSerializationSegment_NoSpace(NoSpaceSegment segment) {
		'''«new TypeReference(IdiomsUtils)».NO_SPACE'''
	}
	
	protected def generateSerializationSegment_Pop(PopSegment segment) {
		'''«new TypeReference(IdiomsUtils)».POP'''
	}
	
	protected def generateSerializationSegment_Push(PushSegment segment) {
		'''«new TypeReference(IdiomsUtils)».PUSH'''
	}
	
	protected def generateSerializationSegment_SoftNewLine(SoftNewLineSegment segment) {
		'''«new TypeReference(IdiomsUtils)».SOFT_NEW_LINE'''
	}
	
	protected def generateSerializationSegment_SoftSpace(SoftSpaceSegment segment) {
		'''«new TypeReference(IdiomsUtils)».SOFT_SPACE'''
	}
	
	protected def generateSerializationSegment_String(StringSegment segment) {
		'''«new TypeReference(IdiomsUtils)».createStringSegment(«segment.getString()»)'''
	}
	
	protected def generateSerializationSegment_Value(ValueSegment segment) {
		'''«new TypeReference(IdiomsUtils)».VALUE'''
	}
	
	/* ************************************************************************************************************************** */
	
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
		'''private final /*@NonNull*/ «new TypeReference(RTSerializationAssignStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «new TypeReference(RTSerializationAssignStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»)'''
	}
	
	protected def generateSerializationTerm1_AssignedRuleCall(RTSerializationAssignedRuleCallStep serializationStep) {
		'''private final /*@NonNull*/ «new TypeReference(RTSerializationAssignedRuleCallStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «new TypeReference(RTSerializationAssignedRuleCallStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»)'''
	}
	
	protected def generateSerializationTerm1_AssignedRuleCalls(RTSerializationAssignedRuleCallsStep serializationStep) {
		'''private final /*@NonNull*/ «new TypeReference(RTSerializationAssignedRuleCallsStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «new TypeReference(RTSerializationAssignedRuleCallsStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»,
		 new «new TypeReference(AbstractRuleValue)» [«serializationStep.getCalledRuleValues().size()»])'''
	}
	
	protected def generateSerializationTerm1_CrossReference(RTSerializationCrossReferenceStep serializationStep) {
		'''private final /*@NonNull*/ «new TypeReference(RTSerializationCrossReferenceStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «new TypeReference(RTSerializationCrossReferenceStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())», "«emitCalledRule(serializationStep.getCrossReference())»")'''
	}
	
	protected def generateSerializationTerm1_Literal(RTSerializationLiteralStep serializationStep) {
		'''private final /*@NonNull*/ «new TypeReference(RTSerializationLiteralStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «new TypeReference(RTSerializationLiteralStep)»(«serializationStep.getVariableIndex()», "«Strings.convertToJavaString(serializationStep.getString())»")'''
	}
	
	protected def generateSerializationTerm1_Sequence(RTSerializationSequenceStep serializationStep) {
		'''private final /*@NonNull*/ «new TypeReference(RTSerializationSequenceStep)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «new TypeReference(RTSerializationSequenceStep)»(«serializationStep.getVariableIndex()», «serializationStep.getStartIndex()», «serializationStep.getEndIndex()»)'''
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
		'''«getSerializationStepId(serializationStep, false)».init(new «new TypeReference(AbstractRuleValue)» [] {«FOR calledRuleValue : serializationStep.getCalledRuleValues() SEPARATOR ', '»«getRuleValueId(calledRuleValue, true)»/*«calledRuleValue.getName()»*/«ENDFOR»});
		'''
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
		'''new «new TypeReference(CardinalitySolutionStep.RuleCheck)»(«emitLiteral(solutionStep.getEReference())», new String[]{«FOR ruleValue : solutionStep.getRuleValues() SEPARATOR ', '»"«getRuleValueId(ruleValue, true)»"«ENDFOR»})'''
	}
	
	protected def generateSolutionStep_ValueCheck(CardinalitySolutionStep.ValueCheck solutionStep) {
		'''new «new TypeReference(CardinalitySolutionStep.ValueCheck)»(«solutionStep.getVariableIndex()», «getSolutionId(solutionStep.getCardinalitySolution(), true)»)'''
	}
}
