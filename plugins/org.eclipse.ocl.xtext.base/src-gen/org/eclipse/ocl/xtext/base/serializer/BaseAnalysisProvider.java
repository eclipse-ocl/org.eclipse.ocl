/*******************************************************************************
 * Copyright (c) 2011, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.serializer;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.AbstractAnalysisProvider;
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValue_MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValues;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndex_MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndexes;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EnumerationValue_MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.RuleIndex_MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.IntegerCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.CardinalitySolutionStep_Assert;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.CardinalitySolutionStep_Assign;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.CardinalitySolutionStep_RuleCheck;
import org.eclipse.ocl.xtext.base.cs2text.user.RTGrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassValue.SerializationRule_SegmentsList;
import org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;

public class BaseAnalysisProvider extends AbstractAnalysisProvider
{
	/**
	 * The metadata resulting from static analysis of the grammar.
	 */
	private static RTGrammarAnalysis analysis = null;

	@Override
	public RTGrammarAnalysis getAnalysis() {
		if (analysis == null) {
			analysis = new RTGrammarAnalysis(
				/**
				 *	The indexable per-produceable EClass meta data.
				 */
				new EClassValue [] {
					ec._0  /* basecs::MultiplicityBoundsCS */,
					ec._1  /* basecs::MultiplicityStringCS */,
					ec._2  /* basecs::PathElementCS */,
					ec._3  /* basecs::PathNameCS */,
					ec._4  /* basecs::TemplateBindingCS */,
					ec._5  /* basecs::TemplateParameterSubstitutionCS */,
					ec._6  /* basecs::TemplateSignatureCS */,
					ec._7  /* basecs::TypeParameterCS */,
					ec._8  /* basecs::TypedTypeRefCS */,
					ec._9  /* basecs::WildcardTypeRefCS */
				},
				/**
				 *	The indexable per-grammar rule meta data.
				 */
				new AbstractRuleValue [] {
					gr._00  /* 0 : ANY_OTHER */,
					gr._01  /* 1 : DOUBLE_QUOTED_STRING */,
					gr._02  /* 2 : ESCAPED_CHARACTER */,
					gr._03  /* 3 : ESCAPED_ID */,
					gr._04  /* 4 : FirstPathElementCS */,
					gr._05  /* 5 : ID */,
					gr._06  /* 6 : INT */,
					gr._07  /* 7 : Identifier */,
					gr._08  /* 8 : LETTER_CHARACTER */,
					gr._09  /* 9 : LOWER */,
					gr._10  /* 10 : ML_COMMENT */,
					gr._11  /* 11 : ML_SINGLE_QUOTED_STRING */,
					gr._12  /* 12 : MultiplicityBoundsCS */,
					gr._13  /* 13 : MultiplicityCS */,
					gr._14  /* 14 : MultiplicityStringCS */,
					gr._15  /* 15 : NUMBER_LITERAL */,
					gr._16  /* 16 : NextPathElementCS */,
					gr._17  /* 17 : PathNameCS */,
					gr._18  /* 18 : SIMPLE_ID */,
					gr._19  /* 19 : SINGLE_QUOTED_STRING */,
					gr._20  /* 20 : SL_COMMENT */,
					gr._21  /* 21 : StringLiteral */,
					gr._22  /* 22 : TemplateBindingCS */,
					gr._23  /* 23 : TemplateParameterSubstitutionCS */,
					gr._24  /* 24 : TemplateSignatureCS */,
					gr._25  /* 25 : TypeParameterCS */,
					gr._26  /* 26 : TypeRefCS */,
					gr._27  /* 27 : TypedRefCS */,
					gr._28  /* 28 : TypedTypeRefCS */,
					gr._29  /* 29 : UPPER */,
					gr._30  /* 30 : URI */,
					gr._31  /* 31 : UnreservedName */,
					gr._32  /* 32 : UnrestrictedName */,
					gr._33  /* 33 : WS */,
					gr._34  /* 34 : WildcardTypeRefCS */
				}
			);
		}
		return analysis;
	}

	/**
	 * Bit vectors of useful grammar rule combinations
	 */
	private class _IndexVectors
	{
		private final @NonNull IndexVector _0 // MultiplicityCS
			= new IndexVector(0x2000L);
		private final @NonNull IndexVector _1 // FirstPathElementCS|NextPathElementCS
			= new IndexVector(0x10010L);
		private final @NonNull IndexVector _2 // PathNameCS
			= new IndexVector(0x20000L);
		private final @NonNull IndexVector _3 // TemplateBindingCS
			= new IndexVector(0x400000L);
		private final @NonNull IndexVector _4 // TemplateParameterSubstitutionCS
			= new IndexVector(0x800000L);
		private final @NonNull IndexVector _5 // TypeParameterCS
			= new IndexVector(0x2000000L);
		private final @NonNull IndexVector _6 // TypeRefCS
			= new IndexVector(0x4000000L);
		private final @NonNull IndexVector _7 // TypedRefCS
			= new IndexVector(0x8000000L);
		private final @NonNull IndexVector _8 // TypedRefCS|TypedTypeRefCS
			= new IndexVector(0x18000000L);
		private final @NonNull IndexVector _9 // TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
			= new IndexVector(0x41c000000L);
	}

	/**
	 * String combinations used by assigned String EAttributes
	 */
	private class _EnumValues
	{
		private final @NonNull EnumerationValue _0 // '*|+|?'
			= new MultipleEnumerationValue(new @NonNull String[]{"*", "+", "?"});
		private final @NonNull EnumerationValue _1 // '|1'
			= new SingleEnumerationValue("|1");
	}

	/**
	 * Expression terms used during the matching process.
	 */
	private class _MatchTerms
	{
		private final @NonNull CardinalitySolution _00 // 0
			= new IntegerCardinalitySolution(0);
		private final @NonNull CardinalitySolution _01 // 1
			= new IntegerCardinalitySolution(1);
		private final @NonNull CardinalitySolution _02 // |MultiplicityBoundsCS::lowerBound|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull CardinalitySolution _03 // |MultiplicityBoundsCS::upperBound|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull CardinalitySolution _04 // |MultiplicityCS::isNullFree.'|1'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, ev._1);
		private final @NonNull CardinalitySolution _05 // |MultiplicityStringCS::stringBounds.'*|+|?'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._0);
		private final @NonNull CardinalitySolution _06 // |NamedElementCS::name|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull CardinalitySolution _07 // |PathElementCS::referredElement|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private final @NonNull CardinalitySolution _08 // |PathNameCS::ownedPathElements|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull CardinalitySolution _09 // |TemplateBindingCS::ownedMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull CardinalitySolution _10 // |TemplateBindingCS::ownedSubstitutions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull CardinalitySolution _11 // |TemplateParameterSubstitutionCS::ownedActualParameter|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull CardinalitySolution _12 // |TemplateSignatureCS::ownedParameters|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull CardinalitySolution _13 // |TypeParameterCS::ownedExtends|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull CardinalitySolution _14 // |TypedTypeRefCS::ownedBinding|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull CardinalitySolution _15 // |TypedTypeRefCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _16 // |WildcardTypeRefCS::ownedExtends|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull CardinalitySolution _17 // (|MultiplicityBoundsCS::lowerBound| - 1)
			= new SubtractCardinalitySolution(_02, _01);
		private final @NonNull CardinalitySolution _18 // (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
			= new SubtractCardinalitySolution(_05, _01);
		private final @NonNull CardinalitySolution _19 // (|NamedElementCS::name| - 1)
			= new SubtractCardinalitySolution(_06, _01);
		private final @NonNull CardinalitySolution _20 // (|PathElementCS::referredElement| - 1)
			= new SubtractCardinalitySolution(_07, _01);
		private final @NonNull CardinalitySolution _21 // (|PathNameCS::ownedPathElements| - 1)
			= new SubtractCardinalitySolution(_08, _01);
		private final @NonNull CardinalitySolution _22 // (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new SubtractCardinalitySolution(_10, _01);
		private final @NonNull CardinalitySolution _23 // (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
			= new SubtractCardinalitySolution(_11, _01);
		private final @NonNull CardinalitySolution _24 // (|TemplateSignatureCS::ownedParameters| - 1)
			= new SubtractCardinalitySolution(_12, _01);
		private final @NonNull CardinalitySolution _25 // (|TypeParameterCS::ownedExtends| - 1)
			= new SubtractCardinalitySolution(_13, _01);
		private final @NonNull CardinalitySolution _26 // (|TypeParameterCS::ownedExtends| > 0)
			= new GreaterThanCardinalitySolution(_13, _00);
		private final @NonNull CardinalitySolution _27 // (|TypedTypeRefCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_15, _01);
	}

	/**
	 * Steps for the matching process.
	 */
	private class _MatchSteps
	{
		private final @NonNull CardinalitySolutionStep _00 // assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._17);
		private final @NonNull CardinalitySolutionStep _01 // assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._18);
		private final @NonNull CardinalitySolutionStep _02 // assert (|NamedElementCS::name| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._19);
		private final @NonNull CardinalitySolutionStep _03 // assert (|PathElementCS::referredElement| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._20);
		private final @NonNull CardinalitySolutionStep _04 // assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._23);
		private final @NonNull CardinalitySolutionStep _05 // assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._27);
		private final @NonNull CardinalitySolutionStep _06 // assign V0 = (|PathNameCS::ownedPathElements| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._21);
		private final @NonNull CardinalitySolutionStep _07 // assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._22);
		private final @NonNull CardinalitySolutionStep _08 // assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._24);
		private final @NonNull CardinalitySolutionStep _09 // assign V0 = (|TypeParameterCS::ownedExtends| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._26);
		private final @NonNull CardinalitySolutionStep _10 // assign V0 = |MultiplicityBoundsCS::upperBound|
			= new CardinalitySolutionStep_Assign(0, mt._03);
		private final @NonNull CardinalitySolutionStep _11 // assign V0 = |MultiplicityCS::isNullFree.'|1'|
			= new CardinalitySolutionStep_Assign(0, mt._04);
		private final @NonNull CardinalitySolutionStep _12 // assign V0 = |TypedTypeRefCS::ownedBinding|
			= new CardinalitySolutionStep_Assign(0, mt._14);
		private final @NonNull CardinalitySolutionStep _13 // assign V0 = |WildcardTypeRefCS::ownedExtends|
			= new CardinalitySolutionStep_Assign(0, mt._16);
		private final @NonNull CardinalitySolutionStep _14 // assign V1 = (|TypeParameterCS::ownedExtends| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._25);
		private final @NonNull CardinalitySolutionStep _15 // assign V1 = |MultiplicityCS::isNullFree.'|1'|
			= new CardinalitySolutionStep_Assign(1, mt._04);
		private final @NonNull CardinalitySolutionStep _16 // assign V1 = |TemplateBindingCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(1, mt._09);
		private final @NonNull CardinalitySolutionStep _17 // check-rule basecs::PathNameCS.ownedPathElements : 4|16
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._1/*FirstPathElementCS|NextPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _18 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : 13
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, iv._0/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _19 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : 23
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, iv._4/*TemplateParameterSubstitutionCS*/);
		private final @NonNull CardinalitySolutionStep _20 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 26
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, iv._6/*TypeRefCS*/);
		private final @NonNull CardinalitySolutionStep _21 // check-rule basecs::TemplateSignatureCS.ownedParameters : 25
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, iv._5/*TypeParameterCS*/);
		private final @NonNull CardinalitySolutionStep _22 // check-rule basecs::TypeParameterCS.ownedExtends : 27
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, iv._7/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _23 // check-rule basecs::TypedTypeRefCS.ownedBinding : 22
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, iv._3/*TemplateBindingCS*/);
		private final @NonNull CardinalitySolutionStep _24 // check-rule basecs::TypedTypeRefCS.ownedPathName : 17
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, iv._2/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _25 // check-rule basecs::WildcardTypeRefCS.ownedExtends : 27
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, iv._7/*TypedRefCS*/);
	}

	/**
	 * The various serialization term used to serialize a serialization rule.
	 */
	private class _SerializationTerms
	{
		private final @NonNull RTSerializationLiteralStep _00 // 1*'&&'
									= new RTSerializationLiteralStep(-1, "&&");
		private final @NonNull RTSerializationLiteralStep _01 // 1*'('
									= new RTSerializationLiteralStep(-1, "(");
		private final @NonNull RTSerializationLiteralStep _02 // 1*')'
									= new RTSerializationLiteralStep(-1, ")");
		private final @NonNull RTSerializationLiteralStep _03 // 1*','
									= new RTSerializationLiteralStep(-1, ",");
		private final @NonNull RTSerializationLiteralStep _04 // 1*'..'
									= new RTSerializationLiteralStep(-1, "..");
		private final @NonNull RTSerializationLiteralStep _05 // 1*'::'
									= new RTSerializationLiteralStep(-1, "::");
		private final @NonNull RTSerializationLiteralStep _06 // 1*'?'
									= new RTSerializationLiteralStep(-1, "?");
		private final @NonNull RTSerializationLiteralStep _07 // 1*'['
									= new RTSerializationLiteralStep(-1, "[");
		private final @NonNull RTSerializationLiteralStep _08 // 1*']'
									= new RTSerializationLiteralStep(-1, "]");
		private final @NonNull RTSerializationLiteralStep _09 // 1*'extends'
									= new RTSerializationLiteralStep(-1, "extends");
		private final @NonNull RTSerializationLiteralStep _10 // 1*'|?'
									= new RTSerializationLiteralStep(-1, "|?");
		private final @NonNull RTSerializationAssignedRuleCallStep _11 // 1*MultiplicityBoundsCS::lowerBound=9
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 9 /* LOWER */);
		private final @NonNull RTSerializationAssignedRuleCallStep _12 // 1*MultiplicityBoundsCS::upperBound=29
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 29 /* UPPER */);
		private final @NonNull RTSerializationAssignStep _13 // 1*MultiplicityStringCS::stringBounds
									= new RTSerializationAssignStep(-1, BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS);
		private final @NonNull RTSerializationAssignedRuleCallStep _14 // 1*NamedElementCS::name=32
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 32 /* UnrestrictedName */);
		private final @NonNull RTSerializationCrossReferenceStep _15 // 1*PathElementCS::referredElement=UnreservedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"));
		private final @NonNull RTSerializationCrossReferenceStep _16 // 1*PathElementCS::referredElement=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _17 // 1*PathNameCS::ownedPathElements+=16
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 16 /* NextPathElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _18 // 1*PathNameCS::ownedPathElements+=4
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 4 /* FirstPathElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _19 // 1*TemplateBindingCS::ownedSubstitutions+=23
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 23 /* TemplateParameterSubstitutionCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _20 // 1*TemplateParameterSubstitutionCS::ownedActualParameter=26
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 26 /* TypeRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _21 // 1*TemplateSignatureCS::ownedParameters+=25
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 25 /* TypeParameterCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _22 // 1*TypeParameterCS::ownedExtends+=27
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 27 /* TypedRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _23 // 1*TypedTypeRefCS::ownedBinding=22
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 22 /* TemplateBindingCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _24 // 1*TypedTypeRefCS::ownedPathName=17
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 17 /* PathNameCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _25 // 1*WildcardTypeRefCS::ownedExtends=27
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 27 /* TypedRefCS */);
		private final @NonNull RTSerializationSequenceStep _26 // 1*steps-1..4
									= new RTSerializationSequenceStep(-1, 1, 4);
		private final @NonNull RTSerializationSequenceStep _27 // 1*steps-1..5
									= new RTSerializationSequenceStep(-1, 1, 5);
		private final @NonNull RTSerializationSequenceStep _28 // 1*steps-1..6
									= new RTSerializationSequenceStep(-1, 1, 6);
		private final @NonNull RTSerializationSequenceStep _29 // 1*steps-1..7
									= new RTSerializationSequenceStep(-1, 1, 7);
		private final @NonNull RTSerializationSequenceStep _30 // 1*steps-1..8
									= new RTSerializationSequenceStep(-1, 1, 8);
		private final @NonNull RTSerializationLiteralStep _31 // V00*'|1'
									= new RTSerializationLiteralStep(0, "|1");
		private final @NonNull RTSerializationSequenceStep _32 // V00*steps-3..5
									= new RTSerializationSequenceStep(0, 3, 5);
		private final @NonNull RTSerializationSequenceStep _33 // V00*steps-3..6
									= new RTSerializationSequenceStep(0, 3, 6);
		private final @NonNull RTSerializationSequenceStep _34 // V00*steps-3..8
									= new RTSerializationSequenceStep(0, 3, 8);
		private final @NonNull RTSerializationSequenceStep _35 // V00*steps-4..6
									= new RTSerializationSequenceStep(0, 4, 6);
		private final @NonNull RTSerializationLiteralStep _36 // V01*'|1'
									= new RTSerializationLiteralStep(1, "|1");
		private final @NonNull RTSerializationAssignedRuleCallStep _37 // V01*TemplateBindingCS::ownedMultiplicity=13
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 13 /* MultiplicityCS */);
		private final @NonNull RTSerializationSequenceStep _38 // V01*steps-6..8
									= new RTSerializationSequenceStep(1, 6, 8);
	}

	/**
	 * The various string segment sequences that may be used to serialize a serialization term.
	 */
	private class _SerializationSegments
	{
		private final @NonNull Segment [] _0 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.createCustomSegment(null, BaseCommentSegmentSupport.class) /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport */,
			IdiomsUtils.VALUE /* «value» */
		};
		private final @NonNull Segment [] _1 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.NO_SPACE /* «! » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.NO_SPACE /* «! » */
		};
		private final @NonNull Segment [] _2 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.NO_SPACE /* «! » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.SOFT_SPACE /* «? » */
		};
		private final @NonNull Segment [] _3 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.NO_SPACE /* «! » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.SOFT_NEW_LINE /* «?\n» */
		};
		private final @NonNull Segment [] _4 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.NO_SPACE /* «! » */,
			IdiomsUtils.VALUE /* «value» */
		};
		private final @NonNull Segment [] _5 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.POP /* «-» */,
			IdiomsUtils.SOFT_SPACE /* «? » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.SOFT_NEW_LINE /* «?\n» */
		};
		private final @NonNull Segment [] _6 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.SOFT_SPACE /* «? » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.PUSH /* «+» */,
			IdiomsUtils.SOFT_NEW_LINE /* «?\n» */
		};
		private final @NonNull Segment [] _7 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.SOFT_SPACE /* «? » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.SOFT_SPACE /* «? » */
		};
		private final @NonNull Segment [] _8 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.HALF_NEW_LINE /* «½\n» */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.HALF_NEW_LINE /* «½\n» */
		};
	}

	/**
	 * The various lists of string segment sequences that may be used to serialize a serialization rule.
	 */
	private class _SerializationSegmentsLists
	{
		private final @NonNull Segment @NonNull [] @Nullable [] _00 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _01 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _02 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _03 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _04 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _05 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _06 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _07 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _08 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _09 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _10 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _11 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _12 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._7 /* «? » «value» «? » */
		};
	}

	/**
	 * The various serialization rules for each grammar rule.
	 */
	private class _GrammarRuleValues
	{
		private final @NonNull TerminalRuleValue _00 // ANY_OTHER
			= new TerminalRuleValue(0, "ANY_OTHER");
		private final @NonNull TerminalRuleValue _01 // DOUBLE_QUOTED_STRING
			= new TerminalRuleValue(1, "DOUBLE_QUOTED_STRING");
		private final @NonNull TerminalRuleValue _02 // ESCAPED_CHARACTER
			= new TerminalRuleValue(2, "ESCAPED_CHARACTER");
		private final @NonNull TerminalRuleValue _03 // ESCAPED_ID
			= new TerminalRuleValue(3, "ESCAPED_ID");
		private final @NonNull ParserRuleValue _04 // FirstPathElementCS
			= new ParserRuleValue(4, "FirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._00 /* referredElement=UnrestrictedName */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _05 // ID
			= new DataTypeRuleValue(5, "ID");
		private final @NonNull TerminalRuleValue _06 // INT
			= new TerminalRuleValue(6, "INT");
		private final @NonNull DataTypeRuleValue _07 // Identifier
			= new DataTypeRuleValue(7, "Identifier");
		private final @NonNull TerminalRuleValue _08 // LETTER_CHARACTER
			= new TerminalRuleValue(8, "LETTER_CHARACTER");
		private final @NonNull DataTypeRuleValue _09 // LOWER
			= new DataTypeRuleValue(9, "LOWER");
		private final @NonNull TerminalRuleValue _10 // ML_COMMENT
			= new TerminalRuleValue(10, "ML_COMMENT");
		private final @NonNull TerminalRuleValue _11 // ML_SINGLE_QUOTED_STRING
			= new TerminalRuleValue(11, "ML_SINGLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _12 // MultiplicityBoundsCS
			= new ParserRuleValue(12, "MultiplicityBoundsCS",
				new @NonNull SerializationRule [] {
					sr0._01 /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _13 // MultiplicityCS
			= new ParserRuleValue(13, "MultiplicityCS",
				new @NonNull SerializationRule [] {
					sr0._05 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					sr0._07 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					sr0._03 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */,
					sr0._06 /* { '[' stringBounds={'*|+|?'} ']' } */,
					sr0._04 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					sr0._02 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _14 // MultiplicityStringCS
			= new ParserRuleValue(14, "MultiplicityStringCS",
				new @NonNull SerializationRule [] {
					sr0._08 /* stringBounds={'*|+|?'} */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _15 // NUMBER_LITERAL
			= new DataTypeRuleValue(15, "NUMBER_LITERAL");
		private final @NonNull ParserRuleValue _16 // NextPathElementCS
			= new ParserRuleValue(16, "NextPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._09 /* referredElement=UnreservedName */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _17 // PathNameCS
			= new ParserRuleValue(17, "PathNameCS",
				new @NonNull SerializationRule [] {
					sr0._10 /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _18 // SIMPLE_ID
			= new TerminalRuleValue(18, "SIMPLE_ID");
		private final @NonNull TerminalRuleValue _19 // SINGLE_QUOTED_STRING
			= new TerminalRuleValue(19, "SINGLE_QUOTED_STRING");
		private final @NonNull TerminalRuleValue _20 // SL_COMMENT
			= new TerminalRuleValue(20, "SL_COMMENT");
		private final @NonNull DataTypeRuleValue _21 // StringLiteral
			= new DataTypeRuleValue(21, "StringLiteral");
		private final @NonNull ParserRuleValue _22 // TemplateBindingCS
			= new ParserRuleValue(22, "TemplateBindingCS",
				new @NonNull SerializationRule [] {
					sr0._11 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _23 // TemplateParameterSubstitutionCS
			= new ParserRuleValue(23, "TemplateParameterSubstitutionCS",
				new @NonNull SerializationRule [] {
					sr0._12 /* ownedActualParameter=TypeRefCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _24 // TemplateSignatureCS
			= new ParserRuleValue(24, "TemplateSignatureCS",
				new @NonNull SerializationRule [] {
					sr0._13 /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _25 // TypeParameterCS
			= new ParserRuleValue(25, "TypeParameterCS",
				new @NonNull SerializationRule [] {
					sr0._14 /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _26 // TypeRefCS
			= new ParserRuleValue(26, "TypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._15 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */,
					sr0._16 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				iv._9); /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
		private final @NonNull ParserRuleValue _27 // TypedRefCS
			= new ParserRuleValue(27, "TypedRefCS",
				new @NonNull SerializationRule [] {
					sr0._15 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				iv._8); /* TypedRefCS|TypedTypeRefCS */
		private final @NonNull ParserRuleValue _28 // TypedTypeRefCS
			= new ParserRuleValue(28, "TypedTypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._15 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _29 // UPPER
			= new DataTypeRuleValue(29, "UPPER");
		private final @NonNull DataTypeRuleValue _30 // URI
			= new DataTypeRuleValue(30, "URI");
		private final @NonNull DataTypeRuleValue _31 // UnreservedName
			= new DataTypeRuleValue(31, "UnreservedName");
		private final @NonNull DataTypeRuleValue _32 // UnrestrictedName
			= new DataTypeRuleValue(32, "UnrestrictedName");
		private final @NonNull TerminalRuleValue _33 // WS
			= new TerminalRuleValue(33, "WS");
		private final @NonNull ParserRuleValue _34 // WildcardTypeRefCS
			= new ParserRuleValue(34, "WildcardTypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._16 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				(IndexVector)null);
	}

	/**
	 * Configuration for each EClass that may be serialized.
	 */
	private class _EClassValues
	{
		private final @NonNull EClassValue _0 // MultiplicityBoundsCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._01, sl._08) /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */,
					new SerializationRule_SegmentsList(sr0._05, sl._03) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._07, sl._04) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._03, sl._04) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, null
			);
		private final @NonNull EClassValue _1 // MultiplicityStringCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._06, sl._05) /* { '[' stringBounds={'*|+|?'} ']' } */,
					new SerializationRule_SegmentsList(sr0._04, sl._06) /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._02, sl._06) /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._08, sl._12) /* stringBounds={'*|+|?'} */
				}, null
			);
		private final @NonNull EClassValue _2 // PathElementCS
			= new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._00, sl._12) /* referredElement=UnrestrictedName */,
					new SerializationRule_SegmentsList(sr0._09, sl._12) /* referredElement=UnreservedName */
				}, null
			);
		private final @NonNull EClassValue _3 // PathNameCS
			= new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._10, sl._00) /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						iv._1) /* FirstPathElementCS|NextPathElementCS */
				}
			);
		private final @NonNull EClassValue _4 // TemplateBindingCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._11, sl._02) /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						iv._0) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						iv._4) /* TemplateParameterSubstitutionCS */
				}
			);
		private final @NonNull EClassValue _5 // TemplateParameterSubstitutionCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._12, sl._09) /* ownedActualParameter=TypeRefCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						iv._9) /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
				}
			);
		private final @NonNull EClassValue _6 // TemplateSignatureCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._13, sl._10) /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						iv._5) /* TypeParameterCS */
				}
			);
		private final @NonNull EClassValue _7 // TypeParameterCS
			= new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._14, sl._11) /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						iv._8) /* TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _8 // TypedTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._15, sl._01) /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						iv._3) /* TemplateBindingCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						iv._2) /* PathNameCS */
				}
			);
		private final @NonNull EClassValue _9 // WildcardTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._16, sl._07) /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						iv._8) /* TypedRefCS|TypedTypeRefCS */
				}
			);
	}

	/**
	 * The various serialization rules that serialize an EClass.
	 */
	private class _SerializationRules0
	{
		// Base::FirstPathElementCS : referredElement=UnrestrictedName
		private @NonNull SerializationRule _00 = new SerializationRule(4,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._03 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._16 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._12,
			null,
			null,
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				)
			});
		// Base::MultiplicityBoundsCS : { lowerBound=LOWER { '..' upperBound=UPPER }[?] }
		private @NonNull SerializationRule _01 = new SerializationRule(12,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._10 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._00 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._27 /* 1*steps-1..5 || «null» */,
				st._11 /* 1*MultiplicityBoundsCS::lowerBound=9 || «? » «value» «? » */,
				st._32 /* V00*steps-3..5 || «null» */,
				st._04 /* 1*'..' || «? » «value» «? » */,
				st._12 /* 1*MultiplicityBoundsCS::upperBound=29 || «? » «value» «? » */
			},
			sl._08,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _02 = new SerializationRule(13,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._11 /* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._01 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._27 /* 1*steps-1..5 || «null» */,
				st._07 /* 1*'[' || «! » «value» «! » */,
				st._13 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._31 /* V00*'|1' || «? » «value» «? » */,
				st._08 /* 1*']' || «! » «value» */
			},
			sl._06,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._0),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					ev._1)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._0, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._1, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _03 = new SerializationRule(13,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._15 /* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._10 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._00 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._30 /* 1*steps-1..8 || «null» */,
				st._07 /* 1*'[' || «! » «value» «! » */,
				st._11 /* 1*MultiplicityBoundsCS::lowerBound=9 || «? » «value» «? » */,
				st._35 /* V00*steps-4..6 || «null» */,
				st._04 /* 1*'..' || «? » «value» «? » */,
				st._12 /* 1*MultiplicityBoundsCS::upperBound=29 || «? » «value» «? » */,
				st._36 /* V01*'|1' || «? » «value» «? » */,
				st._08 /* 1*']' || «! » «value» */
			},
			sl._04,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					ev._1)
			},
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._1, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} '|?' ']' }
		private @NonNull SerializationRule _04 = new SerializationRule(13,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._01 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._27 /* 1*steps-1..5 || «null» */,
				st._07 /* 1*'[' || «! » «value» «! » */,
				st._13 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._10 /* 1*'|?' || «? » «value» «? » */,
				st._08 /* 1*']' || «! » «value» */
			},
			sl._06,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._0)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._0, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' }
		private @NonNull SerializationRule _05 = new SerializationRule(13,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._10 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._00 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._29 /* 1*steps-1..7 || «null» */,
				st._07 /* 1*'[' || «! » «value» «! » */,
				st._11 /* 1*MultiplicityBoundsCS::lowerBound=9 || «? » «value» «? » */,
				st._35 /* V00*steps-4..6 || «null» */,
				st._04 /* 1*'..' || «? » «value» «? » */,
				st._12 /* 1*MultiplicityBoundsCS::upperBound=29 || «? » «value» «? » */,
				st._08 /* 1*']' || «! » «value» */
			},
			sl._03,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} ']' }
		private @NonNull SerializationRule _06 = new SerializationRule(13,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._01 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._26 /* 1*steps-1..4 || «null» */,
				st._07 /* 1*'[' || «! » «value» «! » */,
				st._13 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._08 /* 1*']' || «! » «value» */
			},
			sl._05,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._0)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._0, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' }
		private @NonNull SerializationRule _07 = new SerializationRule(13,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._10 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._00 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._30 /* 1*steps-1..8 || «null» */,
				st._07 /* 1*'[' || «! » «value» «! » */,
				st._11 /* 1*MultiplicityBoundsCS::lowerBound=9 || «? » «value» «? » */,
				st._35 /* V00*steps-4..6 || «null» */,
				st._04 /* 1*'..' || «? » «value» «? » */,
				st._12 /* 1*MultiplicityBoundsCS::upperBound=29 || «? » «value» «? » */,
				st._10 /* 1*'|?' || «? » «value» «? » */,
				st._08 /* 1*']' || «! » «value» */
			},
			sl._04,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityStringCS : stringBounds={'*|+|?'}
		private @NonNull SerializationRule _08 = new SerializationRule(14,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._01 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._13 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */
			},
			sl._12,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._0)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._0, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Base::NextPathElementCS : referredElement=UnreservedName
		private @NonNull SerializationRule _09 = new SerializationRule(16,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._03 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._15 /* 1*PathElementCS::referredElement=UnreservedName || «? » «value» «? » */
			},
			sl._12,
			null,
			null,
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				)
			});
		// Base::PathNameCS : { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] }
		private @NonNull SerializationRule _10 = new SerializationRule(17,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._17 /* check-rule basecs::PathNameCS.ownedPathElements : 4|16 */,
				ms._06 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._27 /* 1*steps-1..5 || «null» */,
				st._18 /* 1*PathNameCS::ownedPathElements+=4 || «null» */,
				st._32 /* V00*steps-3..5 || «null» */,
				st._05 /* 1*'::' || «! » «value» «! » */,
				st._17 /* 1*PathNameCS::ownedPathElements+=16 || «null» */
			},
			sl._00,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._1) /* FirstPathElementCS|NextPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(4, MultiplicativeCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(16, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::TemplateBindingCS : { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _11 = new SerializationRule(22,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._18 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 13 */,
				ms._19 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 23 */,
				ms._16 /* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				ms._07 /* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._28 /* 1*steps-1..6 || «null» */,
				st._19 /* 1*TemplateBindingCS::ownedSubstitutions+=23 || «null» */,
				st._32 /* V00*steps-3..5 || «null» */,
				st._03 /* 1*',' || «! » «value» «? » */,
				st._19 /* 1*TemplateBindingCS::ownedSubstitutions+=23 || «null» */,
				st._37 /* V01*TemplateBindingCS::ownedMultiplicity=13 || «null» */
			},
			sl._02,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					iv._0) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					iv._4) /* TemplateParameterSubstitutionCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(23, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// Base::TemplateParameterSubstitutionCS : ownedActualParameter=TypeRefCS
		private @NonNull SerializationRule _12 = new SerializationRule(23,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._20 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 26 */,
				ms._04 /* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._20 /* 1*TemplateParameterSubstitutionCS::ownedActualParameter=26 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._09,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					iv._6) /* TypeRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(26, MultiplicativeCardinality.ONE)
					}
				)
			});
		// Base::TemplateSignatureCS : { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' }
		private @NonNull SerializationRule _13 = new SerializationRule(24,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._21 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 25 */,
				ms._08 /* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._29 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._01 /* 1*'(' || «! » «value» «! » */,
				st._21 /* 1*TemplateSignatureCS::ownedParameters+=25 || «null» */,
				st._35 /* V00*steps-4..6 || «null» */,
				st._03 /* 1*',' || «! » «value» «? » */,
				st._21 /* 1*TemplateSignatureCS::ownedParameters+=25 || «null» */,
				st._02 /* 1*')' || «! » «value» */
			},
			sl._10,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._5) /* TypeParameterCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(25, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// Base::TypeParameterCS : { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] }
		private @NonNull SerializationRule _14 = new SerializationRule(25,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._22 /* check-rule basecs::TypeParameterCS.ownedExtends : 27 */,
				ms._02 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._09 /* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				ms._14 /* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._30 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._14 /* 1*NamedElementCS::name=32 || «? » «value» «? » */,
				st._34 /* V00*steps-3..8 || «null» */,
				st._09 /* 1*'extends' || «? » «value» «? » */,
				st._22 /* 1*TypeParameterCS::ownedExtends+=27 || «null» */,
				st._38 /* V01*steps-6..8 || «null» */,
				st._00 /* 1*'&&' || «? » «value» «? » */,
				st._22 /* 1*TypeParameterCS::ownedExtends+=27 || «null» */
			},
			sl._11,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					iv._7) /* TypedRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::TypedTypeRefCS : { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] }
		private @NonNull SerializationRule _15 = new SerializationRule(28,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._23 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 22 */,
				ms._24 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 17 */,
				ms._12 /* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				ms._05 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._28 /* 1*steps-1..6 || «null» */,
				st._24 /* 1*TypedTypeRefCS::ownedPathName=17 || «null» */,
				st._33 /* V00*steps-3..6 || «null» */,
				st._01 /* 1*'(' || «! » «value» «! » */,
				st._23 /* 1*TypedTypeRefCS::ownedBinding=22 || «null» */,
				st._02 /* 1*')' || «! » «value» */
			},
			sl._01,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._3) /* TemplateBindingCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._2) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(22, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(17, MultiplicativeCardinality.ONE)
					}
				)
			});
		// Base::WildcardTypeRefCS : { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
		private @NonNull SerializationRule _16 = new SerializationRule(34,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._25 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : 27 */,
				ms._13 /* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._27 /* 1*steps-1..5 || «null» */,
				st._06 /* 1*'?' || «? » «value» «? » */,
				st._32 /* V00*steps-3..5 || «null» */,
				st._09 /* 1*'extends' || «? » «value» «? » */,
				st._25 /* 1*WildcardTypeRefCS::ownedExtends=27 || «null» */
			},
			sl._07,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					iv._7) /* TypedRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
	}

	private _EClassValues ec;
	private _EnumValues ev;
	private _IndexVectors iv;
	private _MatchSteps ms;
	private _MatchTerms mt;
	private _SerializationSegmentsLists sl;
	private _SerializationSegments ss;
	private _SerializationRules0 sr0;
	private _SerializationTerms st;
	private _GrammarRuleValues gr;

	/**
	 * Post constructor/injection initialization to avoid recursions.
	 */
	@Inject
	public void init() {
		iv = new _IndexVectors();
		ev = new _EnumValues();
		mt = new _MatchTerms();
		ms = new _MatchSteps();
		st = new _SerializationTerms();
		ss = new _SerializationSegments();
		sl = new _SerializationSegmentsLists();
		sr0 = new _SerializationRules0();
		gr = new _GrammarRuleValues();
		ec = new _EClassValues();
	}

}
//	Commented imports ensure Xtend provides a true import allowing unqualified annotated usage
//	import Inject;
//	import EAttribute;
//	import NonNull;
//	import Nullable;
//	import MultiplicativeCardinality;
//	import EnumerationValue;
//	import MultipleEnumerationValue;
//	import SingleEnumerationValue;
//	import BaseCommentSegmentSupport;
//	import IdiomsUtils;
//	import Segment;
//	import RTSerializationAssignStep;
//	import RTSerializationAssignedRuleCallStep;
//	import RTSerializationCrossReferenceStep;
//	import RTSerializationLiteralStep;
//	import RTSerializationSequenceStep;
//	import RTSerializationStep;
//	import SerializationRule;
//	import EAttribute_EnumerationValue_MultiplicativeCardinality;
//	import EAttribute_EnumerationValues;
//	import EReference_RuleIndex_MultiplicativeCardinality;
//	import EReference_RuleIndexes;
//	import EnumerationValue_MultiplicativeCardinality;
//	import RuleIndex_MultiplicativeCardinality;
//	import CardinalitySolution;
//	import EAttributeSizeCardinalitySolution;
//	import EStructuralFeatureSizeCardinalitySolution;
//	import GreaterThanCardinalitySolution;
//	import IntegerCardinalitySolution;
//	import SubtractCardinalitySolution;
//	import CardinalitySolutionStep;
//	import CardinalitySolutionStep_Assert;
//	import CardinalitySolutionStep_Assign;
//	import CardinalitySolutionStep_RuleCheck;
//	import RTGrammarAnalysis;
//	import AbstractRuleValue;
//	import DataTypeRuleValue;
//	import EClassValue;
//	import SerializationRule_SegmentsList;
//	import IndexVector;
//	import ParserRuleValue;
//	import TerminalRuleValue;
//	import BaseCSPackage;
