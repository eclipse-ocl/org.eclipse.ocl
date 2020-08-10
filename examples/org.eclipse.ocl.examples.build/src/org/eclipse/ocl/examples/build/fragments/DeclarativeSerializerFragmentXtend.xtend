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

import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.ocl.xtext.base.cs2text.user.RTGrammarAnalysis
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis

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
				return analysis;
			}
			
			«FOR eClass : grammarAnalysis.getSortedProducedEClasses()»

			/**
			 * «eClass.getName()»
			 */
			«FOR serializationRule : grammarAnalysis.getSerializationRules(eClass).getSerializationRules()»
			// «serializationRule.toString()»
			«ENDFOR»
			«ENDFOR»
		}
		'''
	}
	
	protected def generateSerializationRule(BasicSerializationRule serializationRule) {
		'''
		public class «getAnalysisProviderClass(grammar).simpleName» extends «getAnalysisProviderSuperClass(grammar)»
		{
			private static «new TypeReference(RTGrammarAnalysis)» analysis = null;
		
			@Override
			public «new TypeReference(RTGrammarAnalysis)» getAnalysis() {
				if (analysis == null) {
					analysis = new «new TypeReference(RTGrammarAnalysis)»();
				}
				return analysis;
			}
		}
		'''
	}
}
