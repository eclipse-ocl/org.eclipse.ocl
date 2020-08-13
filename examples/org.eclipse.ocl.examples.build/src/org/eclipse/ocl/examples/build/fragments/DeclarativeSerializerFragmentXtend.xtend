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
			«FOR eClass : grammarAnalysis.getSortedProducedEClasses()»

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
			«IF serializationRule.getStaticSubIdioms() != null»
			new /*@NonNull*/ «new TypeReference(SubIdiom)» /*@NonNull*/ []{/*
				«FOR subIdiom : serializationRule.getStaticSubIdioms()»
				«generateSubIdiom(subIdiom)»
				«ENDFOR»
			*/}
			«ELSE»
			null
			«ENDIF»
		)
		'''
	}
	
	protected def generateSubIdiom(SubIdiom subIdiom) {
		'''
		// «subIdiom != null ? subIdiom.toString() : "null"»
		'''
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
		'''
		new «new TypeReference(RTSerializationAssignStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»)'''
	}
	
	protected def generateSerializationAssignedRuleCallStep(RTSerializationAssignedRuleCallStep serializationStep) {
		'''
		new «new TypeReference(RTSerializationAssignedRuleCallStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())», "«serializationStep.getCalledRuleKey()»")'''
	}
	
	protected def generateSerializationAssignedRuleCallsStep(RTSerializationAssignedRuleCallsStep serializationStep) {
		'''
		new «new TypeReference(RTSerializationAssignedRuleCallsStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())» «FOR calledRuleKey : serializationStep.getCalledRuleKeys()», "«calledRuleKey»"«ENDFOR»)'''
	}
	
	protected def generateSerializationCrossReferenceStep(RTSerializationCrossReferenceStep serializationStep) {
		'''
		new «new TypeReference(RTSerializationCrossReferenceStep)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())»)'''
	}
	
	protected def generateSerializationLiteralStep(RTSerializationLiteralStep serializationStep) {
		'''
		new «new TypeReference(RTSerializationLiteralStep)»(«serializationStep.getVariableIndex()», "«Strings.convertToJavaString(serializationStep.getString())»")'''
	}
	
	protected def generateSerializationSequenceStep(RTSerializationSequenceStep serializationStep) {
		'''
		new «new TypeReference(RTSerializationSequenceStep)»(«serializationStep.getVariableIndex()», «serializationStep.getStartIndex()», «serializationStep.getEndIndex()»)'''
	}
}
