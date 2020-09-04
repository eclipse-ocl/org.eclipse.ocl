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
package org.eclipse.ocl.examples.xtext.build.fragments;

import com.google.inject.Inject
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.jdt.annotation.NonNull
import org.eclipse.jdt.annotation.Nullable
import org.eclipse.ocl.examples.xtext.serializer.DataTypeRuleValue
import org.eclipse.ocl.examples.xtext.serializer.EClassValue
import org.eclipse.ocl.examples.xtext.serializer.EClassValue.SerializationRule_SegmentsList
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueMultiple
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueOthers
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueSingle
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector
import org.eclipse.ocl.examples.xtext.serializer.ParserRuleValue
import org.eclipse.ocl.examples.xtext.serializer.SerializationBuilder
import org.eclipse.ocl.examples.xtext.serializer.SerializationGrammarAnalysis
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchStep
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermAdd
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermDivide
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEAttributeSize
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEReferenceSize
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEStructuralFeatureSize
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermGreaterThan
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermInteger
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermMultiply
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermSubtract
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermVariable
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EAttribute_EnumerationValue_GrammarCardinality
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EAttribute_EnumerationValues
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EReference_RuleIndex_GrammarCardinality
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EReference_RuleIndexes
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EnumerationValue_GrammarCardinality
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.RuleIndex_GrammarCardinality
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment.CustomSerializationSegment
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment.StringSerializationSegment
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment.ValueSerializationSegment
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepAssignKeyword
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepAssignedRuleCall
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepAssigns
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepCrossReference
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepLiteral
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepSequence
import org.eclipse.ocl.examples.xtext.serializer.TerminalRuleValue
import org.eclipse.xtext.util.Strings
import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.ocl.examples.xtext.build.xtext.GrammarAnalysis
import org.eclipse.ocl.examples.xtext.build.xtext.SerializationRuleAnalysis

/**
 * DeclarativeSerializerFragmentXtend augments DeclarativeSerializerFragment with M2T functionality
 * exploiting Xtend's string template capabilities.
 */
class DeclarativeSerializerFragmentXtend extends DeclarativeSerializerFragment
{
	protected override doGetSerializationMetaDataContent(GrammarAnalysis grammarAnalysis) {
		newTypeReference(NonNull);
		newTypeReference(Nullable);
		initSerializationMetaDataContent(grammarAnalysis);
		'''		
			public class «getSerializationMetaDataClass(grammar).simpleName» extends «getSerializationMetaDataSuperClass(grammar)»
			{
				/**
				 * The metadata resulting from static analysis of the grammar.
				 */
				private static «newTypeReference(SerializationGrammarAnalysis)» analysis = null;
			
				@Override
				public «newTypeReference(SerializationGrammarAnalysis)» getAnalysis() {
					if (analysis == null) {
						analysis = new «newTypeReference(SerializationGrammarAnalysis)»(
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
							new «newTypeReference(GrammarRuleValue)» [] {
								«FOR grammarRuleValue : getGrammarRuleValueIterator(grammarAnalysis) SEPARATOR ','»
									«getGrammarRuleValueId(grammarRuleValue, true)»  /* «grammarRuleValue.getIndex()» : «grammarRuleValue.toString()» */
								«ENDFOR»
							}
						);
					}
					return analysis;
				}
				
				«generateGrammarRuleVectors(grammarAnalysis)»
				
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
				private _GrammarRuleVectors iv;
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
					iv = new _GrammarRuleVectors();
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
					new «newTypeReference(SerializationRule_SegmentsList)»(«getSerializationRuleId(serializationRule, true)», «getSerializationSegmentsListId(getSerializationSegmentsListString(serializationRule.getStaticSegments()), true)») /* «serializationRule.toRuleString()» */
				«ENDFOR»
			}, «IF grammarAnalysis.basicGetEReferenceRuleIndexes(eClass) === null »null«ELSE»
			new @NonNull «newTypeReference(EReference_RuleIndexes)» [] {
				«FOR eReferenceRuleIndex : getEReferenceRuleIndexesIterable(grammarAnalysis, eClass) SEPARATOR ','»
					new «newTypeReference(EReference_RuleIndexes)»(«emitLiteral(eReferenceRuleIndex.getEReference())»,
						«getGrammarRuleVectorId(eReferenceRuleIndex.getAssignedTargetRuleValueIndexes(), true)») /* «FOR ruleValueIndex : eReferenceRuleIndex.getAssignedTargetRuleValueIndexes() SEPARATOR '|'»«grammarAnalysis.getRuleValue(ruleValueIndex).toString()»«ENDFOR» */
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
		EnumerationValueMultiple: return generateEnumValue_EnumerationValueMultiple(enumValue)
		EnumerationValueOthers: return generateEnumValue_EnumerationValueOthers(enumValue)
		EnumerationValueSingle: return generateEnumValue_EnumerationValueSingle(enumValue)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateEnumValue_EnumerationValueMultiple(EnumerationValueMultiple enumValue) {
		'''new «newTypeReference(EnumerationValueMultiple)»(new @NonNull String[]{«FOR value : enumValue.getValues() SEPARATOR ', '»"«value»"«ENDFOR»})'''
	}
	
	protected def generateEnumValue_EnumerationValueOthers(EnumerationValueOthers enumValue) {
		'''new «newTypeReference(EnumerationValueOthers)»()'''
	}
	
	protected def generateEnumValue_EnumerationValueSingle(EnumerationValueSingle enumValue) {
		'''new «newTypeReference(EnumerationValueSingle)»("«enumValue.getName()»")'''
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
	
	protected def generateGrammarRuleValue(GrammarAnalysis grammarAnalysis, GrammarRuleValue grammarRuleValue) {
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
		var subParserRuleValueIndexes = parserRuleValue.getSubParserRuleValueIndexes();
		'''
			private final @NonNull «newTypeReference(ParserRuleValue)» «getGrammarRuleValueId(parserRuleValue, false)» // «parserRuleValue.getName()»
				= new «newTypeReference(ParserRuleValue)»(«parserRuleValue.getIndex()», "«parserRuleValue.getName()»",
					new @NonNull «newTypeReference(SerializationRule)» [] {
						«FOR serializationRule : getSerializationRulesIterable(grammarAnalysis, parserRuleValue) SEPARATOR ','»
							«getSerializationRuleId(serializationRule, true)» /* «serializationRule.toRuleString()» */
						«ENDFOR»
					}, 
					«IF subParserRuleValueIndexes !== null»«getGrammarRuleVectorId(subParserRuleValueIndexes, true)»); /* «FOR index : subParserRuleValueIndexes SEPARATOR '|'»«getGrammarRuleName(index)»«ENDFOR» */«ELSE»null);«ENDIF»
		'''
	}
	
	protected def generateGrammarRuleValue_TerminalRuleValue(GrammarAnalysis grammarAnalysis, TerminalRuleValue terminalRuleValue) {
		'''private final @NonNull «newTypeReference(TerminalRuleValue)» «getGrammarRuleValueId(terminalRuleValue, false)» // «terminalRuleValue.getName()»
	= new «newTypeReference(TerminalRuleValue)»(«terminalRuleValue.getIndex()», "«terminalRuleValue.getName()»");'''
	}

	/* ************************************************************************************************************************** */
	
	protected def generateGrammarRuleVectors(GrammarAnalysis grammarAnalysis) {
		'''
			/**
			 * Bit vectors of useful grammar rule combinations
			 */
			private class _GrammarRuleVectors
			{
				«FOR grammarRuleVector : getGrammarRuleVectorIterable(grammarAnalysis)»
					private final @NonNull «newTypeReference(GrammarRuleVector)» «getGrammarRuleVectorId(grammarRuleVector, false)» // «FOR index : grammarRuleVector SEPARATOR '|' »«grammarAnalysis.getRuleName(index)»«ENDFOR»
						= new «newTypeReference(GrammarRuleVector)»(«grammarRuleVector.toWordsString()»);
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
					private final @NonNull «newTypeReference(SerializationMatchStep)» «getMatchStepId(matchStep, false)» // «matchStep.toString()»
						= «generateMatchStep(matchStep)»;
				«ENDFOR»
			}
		'''
	}
	
	protected def generateMatchStep(SerializationMatchStep matchStep) {
		switch matchStep {
		SerializationMatchStep.MatchStep_Assert: return generateMatchStep_Assert(matchStep)
		SerializationMatchStep.MatchStep_Assign: return generateMatchStep_Assign(matchStep)
		SerializationMatchStep.MatchStep_RuleCheck: return generateMatchStep_RuleCheck(matchStep)
		SerializationMatchStep.MatchStep_ValueCheck: return generateMatchStep_ValueCheck(matchStep)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateMatchStep_Assert(SerializationMatchStep.MatchStep_Assert matchStep) {
		'''new «newTypeReference(SerializationMatchStep.MatchStep_Assert)»(«getMatchTermId(matchStep.getCardinalitySolution(), true)»)'''
	}
	
	protected def generateMatchStep_Assign(SerializationMatchStep.MatchStep_Assign matchStep) {
		'''new «newTypeReference(SerializationMatchStep.MatchStep_Assign)»(«matchStep.getVariableIndex()», «getMatchTermId(matchStep.getCardinalitySolution(), true)»)'''
	}
	
	protected def generateMatchStep_RuleCheck(SerializationMatchStep.MatchStep_RuleCheck matchStep) {
		'''new «newTypeReference(SerializationMatchStep.MatchStep_RuleCheck)»(«emitLiteral(matchStep.getEReference())», «getGrammarRuleVectorId(matchStep.getRuleValueIndexes(), true)»/*«FOR index : matchStep.getRuleValueIndexes() SEPARATOR '|' »«grammarAnalysis.getRuleName(index)»«ENDFOR»*/)'''
	}
	
	protected def generateMatchStep_ValueCheck(SerializationMatchStep.MatchStep_ValueCheck matchStep) {
		'''new «newTypeReference(SerializationMatchStep.MatchStep_ValueCheck)»(«matchStep.getVariableIndex()», «getMatchTermId(matchStep.getCardinalitySolution(), true)»)'''
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
					private final @NonNull «newTypeReference(SerializationMatchTerm)» «getMatchTermId(matchTerm, false)» // «matchTerm.toString()»
						= «generateMatchTerm(matchTerm)»;
				«ENDFOR»
			}
		'''
	}

	protected def generateMatchTerm(SerializationMatchTerm matchTerm) {
		switch matchTerm {
		SerializationMatchTermAdd: return generateMatchTerm_Add(matchTerm)
		SerializationMatchTermDivide: return generateMatchTerm_Divide(matchTerm)
		SerializationMatchTermEAttributeSize: return generateMatchTerm_EAttributeSize(matchTerm)
		SerializationMatchTermEReferenceSize: return generateMatchTerm_EReferenceSize(matchTerm)
		SerializationMatchTermEStructuralFeatureSize: return generateMatchTerm_EStructuralFeatureSize(matchTerm)
		SerializationMatchTermGreaterThan: return generateMatchTerm_GreaterThan(matchTerm)
		SerializationMatchTermInteger: return generateMatchTerm_IntegerSolution(matchTerm)
		SerializationMatchTermMultiply: return generateMatchTerm_Multiply(matchTerm)
	//	SerializationMatchTermRuntime: return generateMatchTerm_Runtime(matchTerm)
		SerializationMatchTermSubtract: return generateMatchTerm_Subtract(matchTerm)
		SerializationMatchTermVariable: return generateMatchTerm_Variable(matchTerm)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateMatchTerm_Add(SerializationMatchTermAdd matchTerm) {
		'''new «newTypeReference(SerializationMatchTermAdd)»(«getMatchTermId(matchTerm.getLeft(), false)», «getMatchTermId(matchTerm.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_Divide(SerializationMatchTermDivide matchTerm) {
		'''new «newTypeReference(SerializationMatchTermDivide)»(«getMatchTermId(matchTerm.getLeft(), false)», «getMatchTermId(matchTerm.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_EAttributeSize(SerializationMatchTermEAttributeSize matchTerm) {
		'''new «newTypeReference(SerializationMatchTermEAttributeSize)»(«emitLiteral(matchTerm.getEAttribute())», «getEnumValueId(matchTerm.getEnumerationValue(), true)»)'''
	}
	
	protected def generateMatchTerm_EReferenceSize(SerializationMatchTermEReferenceSize matchTerm) {
		'''new «newTypeReference(SerializationMatchTermEReferenceSize)»(«emitLiteral(matchTerm.getEReference())», "«matchTerm.getParserRuleValue().getName()»")'''
	}
	
	protected def generateMatchTerm_EStructuralFeatureSize(SerializationMatchTermEStructuralFeatureSize matchTerm) {
		'''new «newTypeReference(SerializationMatchTermEStructuralFeatureSize)»(«emitLiteral(matchTerm.getEStructuralFeature())»)'''
	}
	
	protected def generateMatchTerm_GreaterThan(SerializationMatchTermGreaterThan matchTerm) {
		'''new «newTypeReference(SerializationMatchTermGreaterThan)»(«getMatchTermId(matchTerm.getLeft(), false)», «getMatchTermId(matchTerm.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_IntegerSolution(SerializationMatchTermInteger matchTerm) {
		'''new «newTypeReference(SerializationMatchTermInteger)»(«matchTerm.getValue()»)'''
	}
	
	protected def generateMatchTerm_Multiply(SerializationMatchTermMultiply matchTerm) {
		'''new «newTypeReference(SerializationMatchTermMultiply)»(«getMatchTermId(matchTerm.getLeft(), false)», «getMatchTermId(matchTerm.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_Subtract(SerializationMatchTermSubtract matchTerm) {
		'''new «newTypeReference(SerializationMatchTermSubtract)»(«getMatchTermId(matchTerm.getLeft(), false)», «getMatchTermId(matchTerm.getRight(), false)»)'''
	}
	
	protected def generateMatchTerm_Variable(SerializationMatchTermVariable matchTerm) {
		'''new «newTypeReference(SerializationMatchTermVariable)»(«matchTerm.getVariableIndex()»)'''
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
				new @NonNull «newTypeReference(SerializationMatchStep)» @NonNull [] {
					«FOR solutionStep : serializationRuleAnalysis.getStaticRuleMatch().getSteps() SEPARATOR ','»
						«getMatchStepId(solutionStep, true)» /* «solutionStep.toString()» */
					«ENDFOR»
				}, 
				«var serializationSteps = serializationRule.getSerializationSteps()»
				«var segmentsList = serializationRule.getStaticSegments()»
				new @NonNull «newTypeReference(SerializationStep)» @NonNull [] {
					«FOR i : integersIterable(serializationSteps.length) SEPARATOR ','»
						«var serializationStep = serializationSteps.get(i)»
						«var segments = segmentsList.get(i)»
					«getSerializationStepId(serializationStep, true)» /* «serializationStep.toString()» || «IF segments !== null»«FOR segment : segments SEPARATOR ' '»«segment.toString()»«ENDFOR»«ELSE»«"«null»"»«ENDIF» */
				«ENDFOR»
				}, 
				«var segmentsListString = getSerializationSegmentsListString(segmentsList)»
				«IF segmentsList !== null»«getSerializationSegmentsListId(segmentsListString, true)»«ELSE»null«ENDIF»,
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
								«getGrammarRuleVectorId(eReferenceData.getAssignedTargetRuleValueIndexes(), true)») /* «FOR ruleValueIndex : eReferenceData.getAssignedTargetRuleValueIndexes() SEPARATOR '|'»«grammarAnalysis.getRuleValue(ruleValueIndex).toString()»«ENDFOR» */
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
				«var eAttribute2enumerationValue2grammarCardinalityArray = serializationRuleAnalysis.basicGetEAttribute2enumerationValue2grammarCardinality()»
				«IF eAttribute2enumerationValue2grammarCardinalityArray !== null»
					new @NonNull «newTypeReference(EAttribute_EnumerationValue_GrammarCardinality)» [] {
						«FOR eAttribute2enumerationValue2grammarCardinality : eAttribute2enumerationValue2grammarCardinalityArray SEPARATOR ','»
							new «newTypeReference(EAttribute_EnumerationValue_GrammarCardinality)»(«emitLiteral(eAttribute2enumerationValue2grammarCardinality.getEAttribute())»,
								new @NonNull «newTypeReference(EnumerationValue_GrammarCardinality)» [] {
								«FOR enumerationValue2grammarCardinality : eAttribute2enumerationValue2grammarCardinality.getEnumerationValue_GrammarCardinality() SEPARATOR ','»
									«var enumerationValue = enumerationValue2grammarCardinality.getEnumerationValue()»
										new «newTypeReference(EnumerationValue_GrammarCardinality)»(«enumerationValue !== null ? getEnumValueId(enumerationValue, true) : "null"», «emitGrammarCardinality(enumerationValue2grammarCardinality.getGrammarCardinality())»)
								«ENDFOR»
								}
							)
						«ENDFOR»
					},
				«ELSE»
					null,
				«ENDIF»
				«var eReference2ruleValueIndex2grammarCardinalityArray = serializationRuleAnalysis.basicGetEReference2ruleValueIndex2grammarCardinality()»
				«IF eReference2ruleValueIndex2grammarCardinalityArray !== null»
					new @NonNull «newTypeReference(EReference_RuleIndex_GrammarCardinality)» [] {
						«FOR eReference2ruleValueIndex2grammarCardinality : eReference2ruleValueIndex2grammarCardinalityArray SEPARATOR ','»
							new «newTypeReference(EReference_RuleIndex_GrammarCardinality)»(«emitLiteral(eReference2ruleValueIndex2grammarCardinality.getEReference())»,
								new @NonNull «newTypeReference(RuleIndex_GrammarCardinality)» [] {
								«FOR ruleValueIndex2grammarCardinality : eReference2ruleValueIndex2grammarCardinality.getRuleIndex_GrammarCardinality() SEPARATOR ','»
									new «newTypeReference(RuleIndex_GrammarCardinality)»(«ruleValueIndex2grammarCardinality.getRuleIndex()», «emitGrammarCardinality(ruleValueIndex2grammarCardinality.getGrammarCardinality())»)
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
				«FOR segments : getSerializationSegmentsIterable(grammarAnalysis)»
					private final @NonNull «newTypeReference(SerializationSegment)» [] «getSerializationSegmentsId(segments, false)» = new @NonNull «newTypeReference(SerializationSegment)» @NonNull [] {
						«FOR segment : segments SEPARATOR ','»
							«generateSerializationSegment(segment)» /* «segment.toString()» */
						«ENDFOR»
					};
				«ENDFOR»
			}
		'''
	}

	protected def generateSerializationSegment(SerializationSegment segment) {
		switch segment {
		CustomSerializationSegment: return generateSerializationSegment_Custom(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.HALF_NEW_LINE) : return generateSerializationSegment_HalfNewLine(segment)
	//	StringSerializationSegment case segment.getString().equals(SerializationBuilder.NEW_LINE) : return generateSerializationSegment_NewLine(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.NO_SPACE) : return generateSerializationSegment_NoSpace(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.POP) : return generateSerializationSegment_Pop(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.PUSH) : return generateSerializationSegment_Push(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.SOFT_NEW_LINE) : return generateSerializationSegment_SoftNewLine(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.SOFT_SPACE) : return generateSerializationSegment_SoftSpace(segment)
		ValueSerializationSegment: return generateSerializationSegment_Value(segment)
		default: segment.getClass().getName() //throw new UnsupportedOperationException()
		}
	}
	
	protected def generateSerializationSegment_Custom(CustomSerializationSegment segment) {
		'''new «newTypeReference(CustomSerializationSegment)»(«newTypeReference(segment.getSupportClassName())».class)'''
	}
	
	protected def generateSerializationSegment_HalfNewLine(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».HALF_NEW_LINE'''
	}
	
	protected def generateSerializationSegment_NewLine(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».NEW_LINE'''
	}
	
	protected def generateSerializationSegment_NoSpace(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».NO_SPACE'''
	}
	
	protected def generateSerializationSegment_Pop(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».POP'''
	}
	
	protected def generateSerializationSegment_Push(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».PUSH'''
	}
	
	protected def generateSerializationSegment_SoftNewLine(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».SOFT_NEW_LINE'''
	}
	
	protected def generateSerializationSegment_SoftSpace(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».SOFT_SPACE'''
	}
	
	protected def generateSerializationSegment_String(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».createStringSegment(«segment.getString()»)'''
	}
	
	protected def generateSerializationSegment_Value(ValueSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».VALUE'''
	}
	
	/* ************************************************************************************************************************** */	
	
	protected def generateSerializationSegmentsLists(GrammarAnalysis grammarAnalysis) {
		'''
			/**
			 * The various lists of string segment sequences that may be used to serialize a serialization rule.
			 */
			private class _SerializationSegmentsLists
			{
				«FOR segmentsList : getSerializationSegmentsListIterable(grammarAnalysis)»
					private final @NonNull «newTypeReference(SerializationSegment)» @NonNull [] @Nullable [] «getSerializationSegmentsListId(getSerializationSegmentsListString(segmentsList), false)» = new @NonNull «newTypeReference(SerializationSegment)» @NonNull [] @Nullable [] {
						«FOR segments : segmentsList SEPARATOR ','»
						«IF segments !== null»«getSerializationSegmentsId(segments, true)» /* «FOR segment : segments SEPARATOR ' '»«segment.toString()»«ENDFOR» */«ELSE»null«ENDIF»
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
	
	protected def generateSerializationTerm(SerializationStep serializationStep) {
		switch serializationStep {
		SerializationStepAssignKeyword: return generateSerializationTerm_Assign(serializationStep)
		SerializationStepAssignedRuleCall: return generateSerializationTerm_AssignedRuleCall(serializationStep)
		SerializationStepAssigns: return generateSerializationTerm_AssignsStep(serializationStep)
		SerializationStepCrossReference: return generateSerializationTerm_CrossReference(serializationStep)
		SerializationStepLiteral: return generateSerializationTerm_Literal(serializationStep)
		SerializationStepSequence: return generateSerializationTerm_Sequence(serializationStep)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateSerializationTerm_Assign(SerializationStepAssignKeyword serializationStep) {
		'''private final @NonNull «newTypeReference(SerializationStepAssignKeyword)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(SerializationStepAssignKeyword)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())», «getEnumValueId(serializationStep.getEnumerationValue(), true)»)'''
	}
	
	protected def generateSerializationTerm_AssignedRuleCall(SerializationStepAssignedRuleCall serializationStep) {
		'''private final @NonNull «newTypeReference(SerializationStepAssignedRuleCall)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(SerializationStepAssignedRuleCall)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())», «serializationStep.getCalledRuleIndex()» /* «grammarAnalysis.getRuleValue(serializationStep.getCalledRuleIndex()).getName()» */)'''
	}
	
	protected def generateSerializationTerm_AssignsStep(SerializationStepAssigns serializationStep) {
		var enumerationValue = serializationStep.getEnumerationValue();
		var calledRuleIndexes = serializationStep.getCalledRuleIndexes();
		'''private final @NonNull «newTypeReference(SerializationStepAssigns)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(SerializationStepAssigns)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())», «IF enumerationValue !== null»«getEnumValueId(enumerationValue, true)»«ELSE»null«ENDIF», «IF calledRuleIndexes !== null»new @NonNull Integer [] { «FOR calledRuleIndex : calledRuleIndexes SEPARATOR ','»«calledRuleIndex»/*«grammarAnalysis.getRuleValue(calledRuleIndex).getName()»*/«ENDFOR»}«ELSE»null«ENDIF»)'''
	}

	protected def generateSerializationTerm_CrossReference(SerializationStepCrossReference serializationStep) {
		'''private final @NonNull «newTypeReference(SerializationStepCrossReference)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(SerializationStepCrossReference)»(«serializationStep.getVariableIndex()», «emitLiteral(serializationStep.getEStructuralFeature())», getCrossReference(«emitLiteral(serializationStep.getEStructuralFeature())», "«emitCalledRule(serializationStep.getCrossReference())»"))'''
	}
	
	protected def generateSerializationTerm_Literal(SerializationStepLiteral serializationStep) {
		'''private final @NonNull «newTypeReference(SerializationStepLiteral)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(SerializationStepLiteral)»(«serializationStep.getVariableIndex()», "«Strings.convertToJavaString(serializationStep.getString())»")'''
	}
	
	protected def generateSerializationTerm_Sequence(SerializationStepSequence serializationStep) {
		'''private final @NonNull «newTypeReference(SerializationStepSequence)» «getSerializationStepId(serializationStep, false)» // «serializationStep.toString()»
							= new «newTypeReference(SerializationStepSequence)»(«serializationStep.getVariableIndex()», «serializationStep.getStartIndex()», «serializationStep.getEndIndex()»)'''
	}
}
