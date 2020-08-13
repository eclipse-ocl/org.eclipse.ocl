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

import java.util.ArrayList
import org.eclipse.emf.ecore.EClass
import org.eclipse.ocl.xtext.base.cs2text.user.RTGrammarAnalysis
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis
import org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules
import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule2
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep
import org.eclipse.ocl.xtext.base.cs2text.xtext.RTSerializationRules
import com.google.common.collect.Lists
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep
import org.eclipse.xtext.util.Strings
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment
import java.util.List
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsFactory
import org.eclipse.ocl.xtext.base.cs2text.idioms.StringSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils
import org.eclipse.ocl.xtext.base.cs2text.idioms.NoSpaceSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.NewLineSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.PushSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.PopSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.SoftNewLineSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.SoftSpaceSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.ValueSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.HalfNewLineSegment
import org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment

/**
 * DeclarativeSerializerFragmentXtend augments DeclarativeSerializerFragment with M2T functionality
 * exploiting Xtend's string template capabilities.
 */
class DeclarativeSerializerFragmentXtend extends DeclarativeSerializerFragment
{
	protected override doGetAnalysisProviderContent(GrammarAnalysis grammarAnalysis) {
		'''
		public class «getAnalysisProviderClass(grammar).simpleName» extends «getAnalysisProviderSuperClass(grammar)»
		{
			private static «new TypeReference(RTGrammarAnalysis)» analysis = null;
		
			@Override
			public «new TypeReference(RTGrammarAnalysis)» getAnalysis() {
				if (analysis == null) {
					analysis = new «new TypeReference(RTGrammarAnalysis)»();
				}
				«FOR eClass : grammarAnalysis.getSortedProducedEClasses()»
				analysis.addSerializationRules(_Rules.create_«eClass.getName()»_Rules());
				«ENDFOR»
				return analysis;
			}
			
			private static class _Steps
			{
				«FOR step : getSortedSerializationSteps(grammarAnalysis)»
				private static final /* @@NonNull*/ «new TypeReference(RTSerializationStep)» «getSerializationStepId(step)» // «step.toString()»
					= «generateSerializationStep(step)»;
				«ENDFOR»
			}
			
			private static class _Segments
			{
				«FOR segments : getSortedSegments(grammarAnalysis)»
				private static final /* @@NonNull*/ «new TypeReference(Segment)» [] «getSegmentsId(segments)» // «segments»
					= «generateSegments(segments)»;
				«ENDFOR»
			}
						
			private static class _Rules
			{
				«generateSerializationRules(grammarAnalysis)»
			}
		}
		'''
	}
	
	protected def generateSerializationRules(GrammarAnalysis grammarAnalysis, EClass eClass) {
		'''
		/**
		 * «eClass.getName()»
		 */
		private static «new TypeReference(RTSerializationRules)» create_«eClass.getName()»_Rules() {
			return new «new TypeReference(RTSerializationRules)»(«emitLiteral(eClass)», «new TypeReference(Lists)».newArrayList(
				«FOR serializationRule : grammarAnalysis.getSerializationRules(eClass).getSerializationRules() SEPARATOR ','»
				«generateSerializationRule(serializationRule.getBasicSerializationRule().getRuntime())»
				«ENDFOR»
			), 0);
		}
		'''
	}
	
	protected def generateSerializationRules(GrammarAnalysis grammarAnalysis) {
		'''
			«FOR eClass : grammarAnalysis.getSortedProducedEClasses() SEPARATOR '\n'»
			«generateSerializationRules(grammarAnalysis, eClass)»
			«ENDFOR»
		'''
	}
	
	protected def generateSerializationRule(RTSerializationRule serializationRule) {
		'''
		// «serializationRule.toRuleString()»
		new «new TypeReference(RTSerializationRule)»(
			new /*@NonNull*/ «new TypeReference(RTSerializationStep)» /*@NonNull*/ []{
				«FOR serializationStep : serializationRule.getSerializationSteps() SEPARATOR ','»
				_Steps.«getSerializationStepId(serializationStep)» /* «serializationStep.toString()» */
				«ENDFOR»
			}, 
			«IF serializationRule.getStaticSegments() != null»
			new /*@NonNull*/ «new TypeReference(Segment)» /*@NonNull*/ [] []{
				«IF serializationRule.getStaticSegments() != null»
				«FOR segments : serializationRule.getStaticSegments() SEPARATOR ','»
				«IF segments != null»
				_Segments.«getSegmentsId(segments)» /* «FOR segment : segments SEPARATOR ' + '»«segment.toString()»«ENDFOR» */
				«ELSE»
				null
				«ENDIF»
				«ENDFOR»
				«ENDIF»
			}
			«ELSE»
			null
			«ENDIF»
		)
		'''
	}
	
	protected def generateSegments(List<Segment> segments) {
		'''
		new «new TypeReference(Segment)» /*@NonNull*/ [] {
			«FOR segment : segments SEPARATOR ',\n'»«generateSegment(segment)» /* «segment.toString()» */«ENDFOR»};
		'''
	}
	
	protected def generateSegment(Segment segment) {
		switch segment {
		CustomSegment: return generateCustomSegment(segment)
		HalfNewLineSegment: return generateHalfNewLineSegment(segment)
		NewLineSegment: return generateNewLineSegment(segment)
		NoSpaceSegment: return generateNoSpaceSegment(segment)
		PopSegment: return generatePopSegment(segment)
		PushSegment: return generatePushSegment(segment)
		SoftNewLineSegment: return generateSoftNewLineSegment(segment)
		SoftSpaceSegment: return generateSoftSpaceSegment(segment)
		StringSegment: return generateStringSegment(segment)
		ValueSegment: return generateValueSegment(segment)
		default: segment.getClass().getName() //throw new UnsupportedOperationException()
		}
	}
	
	protected def generateCustomSegment(CustomSegment segment) {
		'''«new TypeReference(IdiomsUtils)».createCustomSegment(null, «new TypeReference(segment.getSupportClassName())».class)'''
	}
	
	protected def generateHalfNewLineSegment(HalfNewLineSegment segment) {
		'''«new TypeReference(IdiomsUtils)».HALF_NEW_LINE'''
	}
	
	protected def generateNewLineSegment(NewLineSegment segment) {
		'''«new TypeReference(IdiomsUtils)».NEW_LINE'''
	}
	
	protected def generateNoSpaceSegment(NoSpaceSegment segment) {
		'''«new TypeReference(IdiomsUtils)».NO_SPACE'''
	}
	
	protected def generatePopSegment(PopSegment segment) {
		'''«new TypeReference(IdiomsUtils)».POP'''
	}
	
	protected def generatePushSegment(PushSegment segment) {
		'''«new TypeReference(IdiomsUtils)».PUSH'''
	}
	
	protected def generateSoftNewLineSegment(SoftNewLineSegment segment) {
		'''«new TypeReference(IdiomsUtils)».SOFT_NEW_LINE'''
	}
	
	protected def generateSoftSpaceSegment(SoftSpaceSegment segment) {
		'''«new TypeReference(IdiomsUtils)».SOFT_SPACE'''
	}
	
	protected def generateStringSegment(StringSegment segment) {
		'''«new TypeReference(IdiomsUtils)».createStringSegment(«segment.getString()»)'''
	}
	
	protected def generateValueSegment(ValueSegment segment) {
		'''«new TypeReference(IdiomsUtils)».VALUE'''
	}
	
	protected def generateSerializationStep(RTSerializationStep serializationStep) {
		switch serializationStep {
		RTSerializationAssignStep: return generateSerializationAssignStep(serializationStep)
		RTSerializationAssignedRuleCallStep: return generateSerializationAssignedRuleCallStep(serializationStep)
		RTSerializationAssignedRuleCallsStep: return generateSerializationAssignedRuleCallsStep(serializationStep)
		RTSerializationCrossReferenceStep: return generateSerializationCrossReferenceStep(serializationStep)
		RTSerializationLiteralStep: return generateSerializationLiteralStep(serializationStep)
		RTSerializationSequenceStep: return generateSerializationSequenceStep(serializationStep)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateSerializationAssignStep(RTSerializationAssignStep serializationStep) {
		'''new «new TypeReference(RTSerializationAssignStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»)'''
	}
	
	protected def generateSerializationAssignedRuleCallStep(RTSerializationAssignedRuleCallStep serializationStep) {
		'''new «new TypeReference(RTSerializationAssignedRuleCallStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())», "«serializationStep.getCalledRuleKey()»")'''
	}
	
	protected def generateSerializationAssignedRuleCallsStep(RTSerializationAssignedRuleCallsStep serializationStep) {
		'''new «new TypeReference(RTSerializationAssignedRuleCallsStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())» «FOR calledRuleKey : serializationStep.getCalledRuleKeys()», "«calledRuleKey»"«ENDFOR»)'''
	}
	
	protected def generateSerializationCrossReferenceStep(RTSerializationCrossReferenceStep serializationStep) {
		'''new «new TypeReference(RTSerializationCrossReferenceStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»)'''
	}
	
	protected def generateSerializationLiteralStep(RTSerializationLiteralStep serializationStep) {
		'''new «new TypeReference(RTSerializationLiteralStep)»(«serializationStep.getVariableIndex()», "«Strings.convertToJavaString(serializationStep.getString())»")'''
	}
	
	protected def generateSerializationSequenceStep(RTSerializationSequenceStep serializationStep) {
		'''new «new TypeReference(RTSerializationSequenceStep)»(«serializationStep.getVariableIndex()», «serializationStep.getStartIndex()», «serializationStep.getEndIndex()»)'''
	}
}
