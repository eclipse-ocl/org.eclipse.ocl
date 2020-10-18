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
import org.eclipse.ocl.examples.xtext.build.analysis.GrammarAnalysis
import org.eclipse.ocl.examples.xtext.build.analysis.SerializationRuleAnalysis
import org.eclipse.ocl.examples.xtext.serializer.DataTypeRuleValue
import org.eclipse.ocl.examples.xtext.serializer.EClassValue
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueMultiple
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueOthers
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueSingle
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector
import org.eclipse.ocl.examples.xtext.serializer.ParserRuleValue
import org.eclipse.ocl.examples.xtext.serializer.SerializationBuilder
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
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepKeyword
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepSequence
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepWrapper
import org.eclipse.ocl.examples.xtext.serializer.TerminalRuleValue
import org.eclipse.xtext.util.Strings
import org.eclipse.xtext.xtext.generator.model.TypeReference

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
//		var List<CharacterRange> multipleLineCommentCharacterRanges = grammarAnalysis.getMultipleLineCommentCharacterRanges();
//		var singleLineCommentKeywords = grammarAnalysis.getSingleLineCommentKeywords();
		'''		
			«var multipleLineCommentCharacterRanges = grammarAnalysis.getMultipleLineCommentCharacterRanges()»
			«var singleLineCommentKeywords = grammarAnalysis.getSingleLineCommentKeywords()»
			public class «getSerializationMetaDataClass(grammar).simpleName» extends «getSerializationMetaDataSuperClass(grammar)»
			{
				private boolean initialized = false;
				private final @NonNull «newTypeReference(EClassValue)» @NonNull [] eClassValues = new @NonNull «newTypeReference(EClassValue)»[«getEClassCount()»];
				private final @NonNull «newTypeReference(EnumerationValue)» @NonNull [] enumerationValues = new @NonNull «newTypeReference(EnumerationValue)»[«getEnumerationValueCount()»];
				private final @NonNull «newTypeReference(GrammarRuleValue)» @NonNull [] grammarRuleValues = new @NonNull «newTypeReference(GrammarRuleValue)»[«getGrammarRuleValueCount()»];
				private final @NonNull «newTypeReference(GrammarRuleVector)» @NonNull [] grammarRuleVectors = new @NonNull «newTypeReference(GrammarRuleVector)»[«getGrammarRuleVectorCount()»];
				private final @NonNull «newTypeReference(SerializationMatchStep)» @NonNull [] serializationMatchSteps = new @NonNull «newTypeReference(SerializationMatchStep)»[«getMatchStepCount()»];
				private final @NonNull «newTypeReference(SerializationMatchTerm)» @NonNull [] serializationMatchTerms = new @NonNull «newTypeReference(SerializationMatchTerm)»[«getMatchTermCount()»];
				private final @NonNull «newTypeReference(SerializationRule)» @NonNull [] serializationRules = new @NonNull «newTypeReference(SerializationRule)»[«getSerializationRuleCount()»];
				private final @NonNull «newTypeReference(SerializationSegment)» @NonNull [] @NonNull [] serializationSegments = new @NonNull «newTypeReference(SerializationSegment)» @NonNull [«getSerializationSegmentsCount()»] @NonNull [];
				private final @NonNull «newTypeReference(SerializationStep)» @NonNull [] serializationSteps = new @NonNull «newTypeReference(SerializationStep)»[«getSerializationStepCount()»];

				«IF multipleLineCommentCharacterRanges !== null»
				private final @Nullable String @Nullable [] multipleLineCommentMidfixes = new @Nullable String[] {«FOR entry : getMultipleLineCommentMidfixes(multipleLineCommentCharacterRanges) SEPARATOR ','»"«entry != null ? Strings.convertToJavaString(entry) : "null"»"«ENDFOR»};
				private final @NonNull String @Nullable [] multipleLineCommentPrefixes = new @NonNull String[] {«FOR entry : multipleLineCommentCharacterRanges.entrySet() SEPARATOR ','»"«Strings.convertToJavaString(entry.getKey())»"«ENDFOR»};
				private final @NonNull String @Nullable [] multipleLineCommentSuffixes = new @NonNull String[] {«FOR entry : multipleLineCommentCharacterRanges.entrySet() SEPARATOR ','»"«Strings.convertToJavaString(entry.getValue())»"«ENDFOR»};
				«ENDIF»
				«IF singleLineCommentKeywords !== null»
					private final @NonNull String @Nullable [] singleLineCommentPrefixes = new @NonNull String[] {«FOR keyword : singleLineCommentKeywords SEPARATOR ','»"«Strings.convertToJavaString(keyword)»"«ENDFOR»};
				«ENDIF»

				@Override
				public @NonNull «newTypeReference(EClassValue)» @NonNull [] getEClassValues() {
					return eClassValues;
				}

				@Override
				public @NonNull «newTypeReference(EnumerationValue)» @NonNull [] getEnumerationValues() {
					return enumerationValues;
				}

				@Override
				protected int getFirstGlobalSerializationStepAssignmentIndex() {
					return «getFirstGlobalSerializationStepAssignmentIndex()»;
				}

				@Override
				protected int getFirstGlobalSerializationStepLiteralIndex() {
					return «getFirstGlobalSerializationStepLiteralIndex()»;
				}

				@Override
				public @NonNull «newTypeReference(GrammarRuleValue)» @NonNull [] getGrammarRuleValues() {
					return grammarRuleValues;
				}

				@Override
				public @NonNull «newTypeReference(GrammarRuleVector)» @NonNull [] getGrammarRuleVectors() {
					return grammarRuleVectors;
				}

				@Override
				protected int getLastGlobalSerializationStepAssignmentIndex() {
					return «getLastGlobalSerializationStepAssignmentIndex()»;
				}

				@Override
				protected int getLastGlobalSerializationStepLiteralIndex() {
					return «getLastGlobalSerializationStepLiteralIndex()»;
				}
			
				@Override
				public @Nullable String @Nullable [] getMultipleLineCommentMidfixes() {
					return «IF multipleLineCommentCharacterRanges !== null»multipleLineCommentMidfixes«ELSE»null«ENDIF»;
				}
			
				@Override
				public @NonNull String @Nullable [] getMultipleLineCommentPrefixes() {
					return «IF multipleLineCommentCharacterRanges !== null»multipleLineCommentPrefixes«ELSE»null«ENDIF»;
				}
			
				@Override
				public @NonNull String @Nullable [] getMultipleLineCommentSuffixes() {
					return «IF multipleLineCommentCharacterRanges !== null»multipleLineCommentSuffixes«ELSE»null«ENDIF»;
				}

				@Override
				public @NonNull «newTypeReference(SerializationMatchStep)» @NonNull [] getSerializationMatchSteps() {
					return serializationMatchSteps;
				}

				@Override
				public @NonNull «newTypeReference(SerializationMatchTerm)» @NonNull [] getSerializationMatchTerms() {
					return serializationMatchTerms;
				}

				@Override
				public @NonNull «newTypeReference(SerializationRule)» @NonNull [] getSerializationRules() {
					return serializationRules;
				}

				@Override
				public @NonNull «newTypeReference(SerializationSegment)» @NonNull [] @NonNull [] getSerializationSegments() {
					return serializationSegments;
				}

				@Override
				public @NonNull «newTypeReference(SerializationStep)» @NonNull [] getSerializationSteps() {
					return serializationSteps;
				}

				@Override
				public @NonNull String @Nullable [] getSingleLineCommentPrefixes() {
					return «IF singleLineCommentKeywords !== null»singleLineCommentPrefixes«ELSE»null«ENDIF»;
				}

				/**
				 * Post constructor/injection initialization to avoid recursions.
				 */
				@«newTypeReference(Inject)»
				public void init() {
					if (!initialized) {
						initialized = true;
						«generatePagedInit("GrammarRuleVectors", getGrammarRuleVectorCount(), GRAMMAR_RULE_VECTORS_PER_PAGE)»
						«generatePagedInit("EnumerationValues", getEnumerationValueCount(), ENUM_VALUES_PER_PAGE)»
						«generatePagedInit("MatchTerms", getMatchTermCount(), MATCH_TERMS_PER_PAGE)»
						«generatePagedInit("MatchSteps", getMatchStepCount(), MATCH_STEPS_PER_PAGE)»
						«generatePagedInit("SerializationSegments", getSerializationSegmentsCount(), SERIALIZATION_SEGMENTS_PER_PAGE)»
						«generatePagedInit("SerializationSteps", getSerializationStepCount(), SERIALIZATION_STEPS_PER_PAGE)»
						«generatePagedInit("SerializationRules", getSerializationRuleCount(), SERIALIZATION_RULES_PER_PAGE)»
						«generatePagedInit("GrammarRuleValues", getGrammarRuleValueCount(), GRAMMAR_RULE_VALUES_PER_PAGE)»
						«generatePagedInit("EClassValues", getEClassCount(), ECLASS_VALUES_PER_PAGE)»
					}
				}
													
				«generateEClassValues(grammarAnalysis)»
				
				«generateEnumerationValues(grammarAnalysis)»
							
				«generateGrammarRuleValues(grammarAnalysis)»
				
				«generateGrammarRuleVectors(grammarAnalysis)»
				
				«generateMatchSteps(grammarAnalysis)»

				«generateMatchTerms(grammarAnalysis)»

				«generateSerializationRules(grammarAnalysis)»

				«generateSerializationSegments(grammarAnalysis)»

				«generateSerializationSteps(grammarAnalysis)»
			}

			//	Commented imports ensure Xtend provides a true import allowing unqualified annotated usage
			«FOR importedClassName : getImportedClassNameIterable()»
				«var index = importedClassName.lastIndexOf('.')»
				«IF index < 0»
					//	import «newTypeReference(importedClassName)»;
				«ELSE»
					//	import «new TypeReference(importedClassName.substring(0, index), importedClassName.substring(index+1).replace('$', '.'))»;
				«ENDIF»
			«ENDFOR»
		'''
	}
	
	protected def generatePagedInit(String stem, int elementCount, int pageSize) {
		'''
		«FOR page : getPageNumberList(elementCount, pageSize)»
			init«stem»«page !== null ? page : ""»();
		«ENDFOR»
		'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateEClassValues(GrammarAnalysis grammarAnalysis) {
		var eClassValues = getEClassList(grammarAnalysis);
		var eClassValuesSize = eClassValues.size();
		var enumValuePageNumbers = getPageNumberList(eClassValuesSize, ECLASS_VALUES_PER_PAGE);
		'''
		/**
		 * Initialize configuration for each EClass that may be serialized.
		 */
		«FOR page : enumValuePageNumbers»
			private void initEClassValues«page !== null ? page : ""»() {
				«FOR elementIndex : getPageElementList(page, eClassValuesSize, ECLASS_VALUES_PER_PAGE)»
					«var eClassValue = eClassValues.get(elementIndex)»
					eClassValues[«getEClassIndex(eClassValue)»] = «generateEClassValue_EClass(grammarAnalysis, eClassValue)»;
				«ENDFOR»
			}
		«ENDFOR»
		'''
	}
	
	protected def generateEClassValue_EClass(GrammarAnalysis grammarAnalysis, EClass eClass) {
		'''
		new «newTypeReference(EClassValue)»(«emitLiteral(eClass)»,
			createSerializationRules(
				«FOR serializationRule : grammarAnalysis.getEClassValue(eClass).getSerializationRules() SEPARATOR ','»
					«getSerializationRuleIndex(serializationRule)» /* «serializationRule.toRuleString()» */
				«ENDFOR»
			), «IF grammarAnalysis.basicGetEReferenceRuleIndexes(eClass) === null »null«ELSE»
			new @NonNull «newTypeReference(EReference_RuleIndexes)» [] {
				«FOR eReferenceRuleIndex : getEReferenceRuleIndexesIterable(grammarAnalysis, eClass) SEPARATOR ','»
					createEReference_RuleIndexes(«emitLiteral(eReferenceRuleIndex.getEReference())»,
						«getGrammarRuleVectorIndex(eReferenceRuleIndex.getAssignedTargetRuleValueIndexes())») /* «FOR ruleValueIndex : eReferenceRuleIndex.getAssignedTargetRuleValueIndexes() SEPARATOR '|'»«grammarAnalysis.getRuleValue(ruleValueIndex).toString()»«ENDFOR» */
				«ENDFOR»
			}«ENDIF»
		)'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateEnumerationValues(GrammarAnalysis grammarAnalysis) {
		var enumValues = getEnumerationValueList(grammarAnalysis);
		var enumValuesSize = enumValues.size();
		var enumValuePageNumbers = getPageNumberList(enumValuesSize, ENUM_VALUES_PER_PAGE);
		'''
		/**
		 * Initialize string combinations used by assigned String EAttributes.
		 */
		«FOR page : enumValuePageNumbers»
			private void initEnumerationValues«page !== null ? page : ""»() {
				«FOR elementIndex : getPageElementList(page, enumValuesSize, ENUM_VALUES_PER_PAGE)»
					«var enumerationValue = enumValues.get(elementIndex)»
					// «enumerationValue.toString()»
					enumerationValues[«getEnumerationValueIndex(enumerationValue)»] = «generateEnumerationValue(enumerationValue)»;
				«ENDFOR»
			}
		«ENDFOR»
	'''
	}
	
	protected def generateEnumerationValue(EnumerationValue enumerationValue) {
		switch enumerationValue {
		EnumerationValueMultiple: return generateEnumerationValue_EnumerationValueMultiple(enumerationValue)
		EnumerationValueOthers: return generateEnumerationValue_EnumerationValueOthers(enumerationValue)
		EnumerationValueSingle: return generateEnumerationValue_EnumerationValueSingle(enumerationValue)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateEnumerationValue_EnumerationValueMultiple(EnumerationValueMultiple enumerationValue) {
		'''new «newTypeReference(EnumerationValueMultiple)»(new @NonNull String[]{«FOR value : enumerationValue.getValues() SEPARATOR ', '»"«value»"«ENDFOR»})'''
	}
	
	protected def generateEnumerationValue_EnumerationValueOthers(EnumerationValueOthers enumerationValue) {
		'''new «newTypeReference(EnumerationValueOthers)»()'''
	}
	
	protected def generateEnumerationValue_EnumerationValueSingle(EnumerationValueSingle enumerationValue) {
		'''new «newTypeReference(EnumerationValueSingle)»("«enumerationValue.getName()»")'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateGrammarRuleValues(GrammarAnalysis grammarAnalysis) {
		var grammarRuleValues = getGrammarRuleValueList(grammarAnalysis);
		var grammarRuleValuesSize = grammarRuleValues.size();
		var grammarRuleValuePageNumbers = getPageNumberList(grammarRuleValuesSize, GRAMMAR_RULE_VALUES_PER_PAGE);
		'''
		/**
		 * Initialize the various serialization rules for each grammar rule.
		 */
		«FOR page : grammarRuleValuePageNumbers»
			private void initGrammarRuleValues«page !== null ? page : ""»() {
				«FOR elementIndex : getPageElementList(page, grammarRuleValuesSize, GRAMMAR_RULE_VALUES_PER_PAGE)»
					«var grammarRuleValue = grammarRuleValues.get(elementIndex)»
					grammarRuleValues[«getGrammarRuleValueIndex(grammarRuleValue)»] = «generateGrammarRuleValue(grammarAnalysis, grammarRuleValue)»
				«ENDFOR»
			}
		«ENDFOR»
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
		'''new «newTypeReference(DataTypeRuleValue)»(«dataTypeRuleValue.getIndex()», "«dataTypeRuleValue.getName()»");'''
	}
	
	protected def generateGrammarRuleValue_ParserRuleValue(GrammarAnalysis grammarAnalysis, ParserRuleValue parserRuleValue) {
		var subParserRuleValueIndexes = parserRuleValue.getSubParserRuleValueIndexes();
		'''
			createParserRuleValue(«parserRuleValue.getIndex()», "«parserRuleValue.getName()»", «IF subParserRuleValueIndexes !== null»«getGrammarRuleVectorIndex(subParserRuleValueIndexes)» /* «FOR index : subParserRuleValueIndexes SEPARATOR '|'»«getGrammarRuleName(index)»«ENDFOR» */«ELSE»-1«ENDIF»,
				createSerializationRules(
					«FOR serializationRule : getSerializationRuleList(grammarAnalysis, parserRuleValue) SEPARATOR ','»
					«getSerializationRuleIndex(serializationRule)»	/* «getGrammarRuleName(serializationRule.getRuleValueIndex())»: «serializationRule.toRuleString()» */
					«ENDFOR»
				),
				«var formattingTexts = getFormattingTexts(grammarAnalysis, parserRuleValue)»
				«var innerFormattingSegmentsList = getFormattingSegmentsInnerList(grammarAnalysis, parserRuleValue)»
				«var outerFormattingSegmentsList = getFormattingSegmentsOuterList(grammarAnalysis, parserRuleValue)»
				«FOR i : 0..innerFormattingSegmentsList.size()-1 SEPARATOR ','»
				«var formattingText = formattingTexts.get(i)»
				«var outerFormattingSegments = outerFormattingSegmentsList.get(i)»
				«var innerFormattingSegments = innerFormattingSegmentsList.get(i)»
				(«getSerializationSegmentsIndex(outerFormattingSegments)» << 16) | «getSerializationSegmentsIndex(innerFormattingSegments)»	/* «formattingText» : «outerFormattingSegments.toString()» | «innerFormattingSegments.toString()» */
				«ENDFOR»
			);
		'''
	}
	
	protected def generateGrammarRuleValue_TerminalRuleValue(GrammarAnalysis grammarAnalysis, TerminalRuleValue terminalRuleValue) {
		'''new «newTypeReference(TerminalRuleValue)»(«terminalRuleValue.getIndex()», "«terminalRuleValue.getName()»");'''
	}

	/* ************************************************************************************************************************** */
	
	protected def generateGrammarRuleVectors(GrammarAnalysis grammarAnalysis) {
		var grammarRuleVectors = getGrammarRuleVectorList(grammarAnalysis);
		var grammarRuleVectorsSize = grammarRuleVectors.size();
		var grammarRuleVectorPageNumbers = getPageNumberList(grammarRuleVectorsSize, GRAMMAR_RULE_VECTORS_PER_PAGE);
		'''
		/**
		 * Initialize bit vectors of useful grammar rule combinations.
		 */
		«FOR page : grammarRuleVectorPageNumbers»
			private void initGrammarRuleVectors«page !== null ? page : ""»() {
				«FOR elementIndex : getPageElementList(page, grammarRuleVectorsSize, GRAMMAR_RULE_VECTORS_PER_PAGE)»
					«var grammarRuleVector = grammarRuleVectors.get(elementIndex)»
					// «FOR index : grammarRuleVector SEPARATOR '|' »«grammarAnalysis.getRuleName(index)»«ENDFOR»
					grammarRuleVectors[«getGrammarRuleVectorIndex(grammarRuleVector)»] = new «newTypeReference(GrammarRuleVector)»(«grammarRuleVector.toWordsString()»);
				«ENDFOR»
			}
		«ENDFOR»
		'''
	}

	
	/* ************************************************************************************************************************** */
	
	protected def generateMatchSteps(GrammarAnalysis grammarAnalysis) {
		var matchSteps = getMatchStepList(grammarAnalysis);
		var matchStepsSize = matchSteps.size();
		var matchStepPageNumbers = getPageNumberList(matchStepsSize, MATCH_STEPS_PER_PAGE);
		'''
		/**
		 * Initialize steps for the matching process.
		 */
		«FOR page : matchStepPageNumbers»
			private void initMatchSteps«page !== null ? page : ""»() {
				«FOR elementIndex : getPageElementList(page, matchStepsSize, MATCH_STEPS_PER_PAGE)»
					«var matchStep = matchSteps.get(elementIndex)»
					// «matchStep.toString()»
					serializationMatchSteps[«getMatchStepIndex(matchStep)»] = «generateMatchStep(matchStep)»;
				«ENDFOR»
			}
		«ENDFOR»
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
		'''createMatchStep_Assert(«getMatchTermIndex(matchStep.getCardinalitySolution())»)'''
	}
	
	protected def generateMatchStep_Assign(SerializationMatchStep.MatchStep_Assign matchStep) {
		'''createMatchStep_Assign(«matchStep.getVariableIndex()», «getMatchTermIndex(matchStep.getCardinalitySolution())»)'''
	}
	
	protected def generateMatchStep_RuleCheck(SerializationMatchStep.MatchStep_RuleCheck matchStep) {
		'''createMatchStep_RuleCheck(«emitLiteral(matchStep.getEReference())», «getGrammarRuleVectorIndex(matchStep.getRuleValueIndexes())»/*«FOR index : matchStep.getRuleValueIndexes() SEPARATOR '|' »«grammarAnalysis.getRuleName(index)»«ENDFOR»*/)'''
	}
	
	protected def generateMatchStep_ValueCheck(SerializationMatchStep.MatchStep_ValueCheck matchStep) {
		'''createMatchStep_ValueCheck(«matchStep.getVariableIndex()», «getMatchTermIndex(matchStep.getCardinalitySolution())»)'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateMatchTerms(GrammarAnalysis grammarAnalysis) {
		var matchTerms = getMatchTermList(grammarAnalysis);
		var matchTermsSize = matchTerms.size();
		var matchTermPageNumbers = getPageNumberList(matchTermsSize, MATCH_TERMS_PER_PAGE);
		'''
		/**
		 * Initialize expression terms used during the matching process.
		 */
		«FOR page : matchTermPageNumbers»
			private void initMatchTerms«page !== null ? page : ""»() {
				«FOR elementIndex : getPageElementList(page, matchTermsSize, MATCH_TERMS_PER_PAGE)»
					«var matchTerm = matchTerms.get(elementIndex)»
					// «matchTerm.toString()»
					serializationMatchTerms[«getMatchTermIndex(matchTerm)»] = «generateMatchTerm(matchTerm)»;
				«ENDFOR»
			}
		«ENDFOR»
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
		'''createSerializationMatchTermAdd(«getMatchTermIndex(matchTerm.getLeft())», «getMatchTermIndex(matchTerm.getRight())»)'''
	}
	
	protected def generateMatchTerm_Divide(SerializationMatchTermDivide matchTerm) {
		'''createSerializationMatchTermDivide(«getMatchTermIndex(matchTerm.getLeft())», «getMatchTermIndex(matchTerm.getRight())»)'''
	}
	
	protected def generateMatchTerm_EAttributeSize(SerializationMatchTermEAttributeSize matchTerm) {
		'''createSerializationMatchTermEAttributeSize(«emitLiteral(matchTerm.getEAttribute())», «getEnumerationValueIndex(matchTerm.getEnumerationValue())» /* «matchTerm.getEnumerationValue().toString()» */)'''
	}
	
	protected def generateMatchTerm_EReferenceSize(SerializationMatchTermEReferenceSize matchTerm) {
		'''new «newTypeReference(SerializationMatchTermEReferenceSize)»(«emitLiteral(matchTerm.getEReference())», "«matchTerm.getParserRuleValue().getName()»")'''
	}
	
	protected def generateMatchTerm_EStructuralFeatureSize(SerializationMatchTermEStructuralFeatureSize matchTerm) {
		'''new «newTypeReference(SerializationMatchTermEStructuralFeatureSize)»(«emitLiteral(matchTerm.getEStructuralFeature())»)'''
	}
	
	protected def generateMatchTerm_GreaterThan(SerializationMatchTermGreaterThan matchTerm) {
		'''createSerializationMatchTermGreaterThan(«getMatchTermIndex(matchTerm.getLeft())», «getMatchTermIndex(matchTerm.getRight())»)'''
	}
	
	protected def generateMatchTerm_IntegerSolution(SerializationMatchTermInteger matchTerm) {
		'''new «newTypeReference(SerializationMatchTermInteger)»(«matchTerm.getValue()»)'''
	}
	
	protected def generateMatchTerm_Multiply(SerializationMatchTermMultiply matchTerm) {
		'''createSerializationMatchTermMultiply(«getMatchTermIndex(matchTerm.getLeft())», «getMatchTermIndex(matchTerm.getRight())»)'''
	}
	
	protected def generateMatchTerm_Subtract(SerializationMatchTermSubtract matchTerm) {
		'''createSerializationMatchTermSubtract(«getMatchTermIndex(matchTerm.getLeft())», «getMatchTermIndex(matchTerm.getRight())»)'''
	}
	
	protected def generateMatchTerm_Variable(SerializationMatchTermVariable matchTerm) {
		'''new «newTypeReference(SerializationMatchTermVariable)»(«matchTerm.getVariableIndex()»)'''
	}
	
	/* ************************************************************************************************************************** */	

	protected def generateSerializationRules(GrammarAnalysis grammarAnalysis) {
		var serializationRuleAnalyses = getSerializationRuleAnalysisList(grammarAnalysis);
		var serializationRulesSize = serializationRuleAnalyses.size();
		var serializationRulePageNumbers = getPageNumberList(serializationRulesSize, SERIALIZATION_RULES_PER_PAGE);
		'''
		/**
		 * Initialize the various serialization rules that serialize an EClass.
		 */
		«FOR page : serializationRulePageNumbers»
			private void initSerializationRules«page !== null ? page : ""»() {
				«FOR elementIndex : getPageElementList(page, serializationRulesSize, SERIALIZATION_RULES_PER_PAGE)»
					 «var serializationRuleAnalysis = serializationRuleAnalyses.get(elementIndex)»
					// «serializationRuleAnalysis.toString()»
					serializationRules[«getSerializationRuleIndex(serializationRuleAnalysis.getSerializationRule())»] =
						«generateSerializationRule(serializationRuleAnalysis)»;
				«ENDFOR»
			}
		«ENDFOR»
		'''
	}
	
	protected def generateSerializationRule(SerializationRuleAnalysis serializationRuleAnalysis) {
		var SerializationRule serializationRule = serializationRuleAnalysis.getSerializationRule();
		'''
			new «newTypeReference(SerializationRule)»("«serializationRuleAnalysis.getName()»", «serializationRuleAnalysis.getRuleValueIndex()»,
				createSerializationMatchSteps(
					«FOR solutionStep : serializationRuleAnalysis.getSerializationMatchSteps() SEPARATOR ','»
						«getMatchStepIndex(solutionStep)»		/* «solutionStep.toString()» */
					«ENDFOR»
				), 
				«var serializationSteps = serializationRule.getSerializationSteps()»
				createSerializationSteps(
					«FOR i : integersIterable(serializationSteps.length) SEPARATOR ','»
						«var serializationStep = serializationSteps.get(i)»
					«getSerializationStepIndex(serializationStep)»		/* «serializationStep.toString()» */
				«ENDFOR»
				), 
				«var eAttribute2EnumerationValues = serializationRuleAnalysis.basicGetEAttribute2EnumerationValues()»
				«IF eAttribute2EnumerationValues !== null»
				new @NonNull «newTypeReference(EAttribute_EnumerationValues)» [] {
					«FOR eAttributeData : eAttribute2EnumerationValues SEPARATOR ','»
					createEAttribute_EnumerationValues(«emitLiteral(eAttributeData.getEAttribute())»,
						«FOR enumerationValue : eAttributeData.getEnumerationValues() SEPARATOR ','»
						«getEnumerationValueIndex(enumerationValue)»	/* «enumerationValue.toString()» */
						«ENDFOR»
					)
					«ENDFOR»
				},
				«ELSE»
				null,
				«ENDIF»
				«var eReference2AssignedRuleValueIndexes = serializationRuleAnalysis.basicGetEReference2AssignedRuleValueIndexes()»
				«IF eReference2AssignedRuleValueIndexes !== null»
					new @NonNull «newTypeReference(EReference_RuleIndexes)» [] {
						«FOR eReferenceData : eReference2AssignedRuleValueIndexes SEPARATOR ','»
							createEReference_RuleIndexes(«emitLiteral(eReferenceData.getEReference())»,
								«getGrammarRuleVectorIndex(eReferenceData.getAssignedTargetRuleValueIndexes())») /* «FOR ruleValueIndex : eReferenceData.getAssignedTargetRuleValueIndexes() SEPARATOR '|'»«grammarAnalysis.getRuleValue(ruleValueIndex).toString()»«ENDFOR» */
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
										createEnumerationValue_GrammarCardinality(«enumerationValue !== null ? getEnumerationValueIndex(enumerationValue) : -1»«IF enumerationValue !== null» /* «enumerationValue.toString()» */«ENDIF», «emitGrammarCardinality(enumerationValue2grammarCardinality.getGrammarCardinality())»)
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
		var serializationSegmentsList = getSerializationSegmentsList(grammarAnalysis);
		var serializationSegmentsSize = serializationSegmentsList.size();
		var serializationSegmentPageNumbers = getPageNumberList(serializationSegmentsSize, MATCH_TERMS_PER_PAGE);
		'''
		/**
		 * Initialize the various string segment sequences that may be used to serialize a serialization term.
		 */
		«FOR page : serializationSegmentPageNumbers»
			private void initSerializationSegments«page !== null ? page : ""»() {
				«FOR elementIndex : getPageElementList(page, serializationSegmentsSize, MATCH_TERMS_PER_PAGE)»
				«var serializationSegments = serializationSegmentsList.get(elementIndex)»
				serializationSegments[«getSerializationSegmentsIndex(serializationSegments)»] = new @NonNull «newTypeReference(SerializationSegment)» @NonNull [] {
					«FOR segment : serializationSegments SEPARATOR ','»
					«generateSerializationSegment(segment)» /* «segment.toString()» */
					«ENDFOR»
				};
				«ENDFOR»
			}
		«ENDFOR»
		'''
	}

	protected def generateSerializationSegment(SerializationSegment segment) {
		switch segment {
		CustomSerializationSegment: return generateSerializationSegment_Custom(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.HALF_NEW_LINE) : return generateSerializationSegment_HalfNewLine(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.NEW_LINE) : return generateSerializationSegment_NewLine(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.NO_SPACE) : return generateSerializationSegment_NoSpace(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.POP) : return generateSerializationSegment_Pop(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.PUSH) : return generateSerializationSegment_Push(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.PUSH_NEXT) : return generateSerializationSegment_PushNext(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.SOFT_NEW_LINE) : return generateSerializationSegment_SoftNewLine(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.SOFT_SPACE) : return generateSerializationSegment_SoftSpace(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.WRAP_ANCHOR) : return generateSerializationSegment_WrapAnchor(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.WRAP_BEGIN_ALL) : return generateSerializationSegment_WrapBeginAll(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.WRAP_BEGIN_SOME) : return generateSerializationSegment_WrapBeginSome(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.WRAP_END) : return generateSerializationSegment_WrapEnd(segment)
		StringSerializationSegment case segment.getString().equals(SerializationBuilder.WRAP_HERE) : return generateSerializationSegment_WrapHere(segment)
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
	
	protected def generateSerializationSegment_PushNext(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».PUSH_NEXT'''
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
	
	protected def generateSerializationSegment_WrapAnchor(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».WRAP_ANCHOR'''
	}
	
	protected def generateSerializationSegment_WrapBeginAll(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».WRAP_BEGIN_ALL'''
	}
	
	protected def generateSerializationSegment_WrapBeginSome(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».WRAP_BEGIN_SOME'''
	}
	
	protected def generateSerializationSegment_WrapEnd(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».WRAP_END'''
	}
	
	protected def generateSerializationSegment_WrapHere(StringSerializationSegment segment) {
		'''«newTypeReference(SerializationSegment)».WRAP_HERE'''
	}
	
	/* ************************************************************************************************************************** */
	
	protected def generateSerializationSteps(GrammarAnalysis grammarAnalysis) {
		var serializationSteps = getSerializationStepList(grammarAnalysis);
		var serializationStepsSize = serializationSteps.size();
		var serializationStepPageNumbers = getPageNumberList(serializationStepsSize, SERIALIZATION_STEPS_PER_PAGE);
		'''
		/**
		 * Initialize the various serialization steps used to serialize a serialization rule.
		 */
		«FOR page : serializationStepPageNumbers»
			private void initSerializationSteps«page !== null ? page : ""»() {
				«FOR elementIndex : getPageElementList(page, serializationStepsSize, SERIALIZATION_STEPS_PER_PAGE)»
					«var serializationStep = serializationSteps.get(elementIndex)»
					// «serializationStep.toString()»
					serializationSteps[«getSerializationStepIndex(serializationStep)»] = «generateSerializationStep(serializationStep)»;
				«ENDFOR»
			}
		«ENDFOR»
		'''
	}
	
	protected def generateSerializationStep(SerializationStep serializationStep) {
		switch serializationStep {
		SerializationStepAssignKeyword: return generateSerializationStep_Assign(serializationStep)
		SerializationStepAssignedRuleCall: return generateSerializationStep_AssignedRuleCall(serializationStep)
		SerializationStepAssigns: return generateSerializationStep_AssignsStep(serializationStep)
		SerializationStepCrossReference: return generateSerializationStep_CrossReference(serializationStep)
		SerializationStepKeyword: return generateSerializationStep_Keyword(serializationStep)
		SerializationStepSequence: return generateSerializationStep_Sequence(serializationStep)
		SerializationStepWrapper: return generateSerializationStep_Wrapper(serializationStep)
		default: throw new UnsupportedOperationException()
		}
	}
	
	protected def generateSerializationStep_Assign(SerializationStepAssignKeyword serializationStep) {
		'''createSerializationStepAssignKeyword(«emitLiteral(serializationStep.getEStructuralFeature())», «getEnumerationValueIndex(serializationStep.getEnumerationValue())» /* «serializationStep.getEnumerationValue().toString()» */, «getSerializationSegmentsIndex(serializationStep.getSerializationSegments())»)'''
	}
	
	protected def generateSerializationStep_AssignedRuleCall(SerializationStepAssignedRuleCall serializationStep) {
		'''createSerializationStepAssignedRuleCall(«emitLiteral(serializationStep.getEStructuralFeature())», «serializationStep.getCalledRuleIndex()» /*«grammarAnalysis.getRuleValue(serializationStep.getCalledRuleIndex()).getName()»*/, «getSerializationSegmentsIndex(serializationStep.getSerializationSegments())»)'''
	}
	
	protected def generateSerializationStep_AssignsStep(SerializationStepAssigns serializationStep) {
		var enumerationValue = serializationStep.getEnumerationValue();
		var calledRuleIndexes = serializationStep.getCalledRuleIndexes();
		'''createSerializationStepAssigns(«emitLiteral(serializationStep.getEStructuralFeature())», «IF enumerationValue !== null»«getEnumerationValueIndex(enumerationValue)» /* «enumerationValue.toString()» */«ELSE»-1«ENDIF», «IF calledRuleIndexes !== null»new @NonNull Integer [] { «FOR calledRuleIndex : calledRuleIndexes SEPARATOR ','»«calledRuleIndex»/*«grammarAnalysis.getRuleValue(calledRuleIndex).getName()»*/«ENDFOR»}«ELSE»null«ENDIF», «getSerializationSegmentsIndex(serializationStep.getSerializationSegments())»)'''
	}

	protected def generateSerializationStep_CrossReference(SerializationStepCrossReference serializationStep) {
		'''createSerializationStepCrossReference(«emitLiteral(serializationStep.getEStructuralFeature())», getCrossReference(«emitLiteral(serializationStep.getEStructuralFeature())», "«emitCalledRule(serializationStep.getCrossReference())»"), «getSerializationSegmentsIndex(serializationStep.getSerializationSegments())»)'''
	}
	
	protected def generateSerializationStep_Keyword(SerializationStepKeyword serializationStep) {
		'''createSerializationStepKeyword("«Strings.convertToJavaString(serializationStep.getKeyword())»", «getSerializationSegmentsIndex(serializationStep.getSerializationSegments())»)'''
	}
	
	protected def generateSerializationStep_Sequence(SerializationStepSequence serializationStep) {
		var serializationSegmentsIndex = getSerializationSegmentsIndex(serializationStep.getSerializationSegments());
		'''createSerializationStepSequence(«serializationStep.getVariableIndex()», «serializationStep.getStepsRange()»«IF serializationSegmentsIndex >= 0», «serializationSegmentsIndex»«ENDIF»)'''
	}
	
	protected def generateSerializationStep_Wrapper(SerializationStepWrapper serializationStep) {
		'''createSerializationStepWrapper(«getSerializationSegmentsIndex(serializationStep.getSerializationSegments())»)'''
	}
}
