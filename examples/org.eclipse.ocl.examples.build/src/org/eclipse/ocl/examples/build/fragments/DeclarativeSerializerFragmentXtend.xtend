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
					analysis.addSerializationRules(create_«eClass.getName()»_Rules());
				«ENDFOR»
				return analysis;
			}
			
			«generateSerializationRules(grammarAnalysis)»
		}
		'''
	}
	
	protected def generateSerializationRule(GrammarAnalysis grammarAnalysis, EClass eClass) {
		'''
		/**
		 * «eClass.getName()»
		 */
		private «new TypeReference(SerializationRules)» create_«eClass.getName()»_Rules() {
		«FOR serializationRule : grammarAnalysis.getSerializationRules(eClass).getSerializationRules()»
			// «serializationRule.toString()»
		«ENDFOR»
			return new «new TypeReference(SerializationRules)»(«new TypeReference(emitQualifiedLiteral(eClass.getEPackage()))».Literals.«emitLiteral(eClass)», new «new TypeReference(ArrayList)»<>());
		}
		'''
	}
	
	protected def generateSerializationRules(GrammarAnalysis grammarAnalysis) {
		'''
			«FOR eClass : grammarAnalysis.getSortedProducedEClasses()»
			«generateSerializationRule(grammarAnalysis, eClass)»
			«ENDFOR»
		'''
	}
}
