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
import org.eclipse.ocl.xtext.base.cs2text.runtime.AbstractAnalysisProvider;
import org.eclipse.ocl.xtext.base.cs2text.runtime.DataTypeRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.EClassValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.EClassValue.SerializationRule_SegmentsList;
import org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue.EnumerationValueMultiple;
import org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue.EnumerationValueSingle;
import org.eclipse.ocl.xtext.base.cs2text.runtime.GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.GrammarRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.GrammarRuleVector;
import org.eclipse.ocl.xtext.base.cs2text.runtime.IdiomsUtils;
import org.eclipse.ocl.xtext.base.cs2text.runtime.ParserRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationGrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchStep.MatchStep_Assert;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchStep.MatchStep_Assign;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchStep.MatchStep_RuleCheck;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchTerm;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchTerm.SerializationMatchTermEAttributeSize;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchTerm.SerializationMatchTermEStructuralFeatureSize;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchTerm.SerializationMatchTermGreaterThan;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchTerm.SerializationMatchTermInteger;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchTerm.SerializationMatchTermSubtract;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValue_GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValues;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndex_GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndexes;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EnumerationValue_GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.RuleIndex_GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationSegment;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepAssignKeyword;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepAssignedRuleCall;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepCrossReference;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepLiteral;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepSequence;
import org.eclipse.ocl.xtext.base.cs2text.runtime.TerminalRuleValue;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;

public class BaseAnalysisProvider extends AbstractAnalysisProvider
{
	/**
	 * The metadata resulting from static analysis of the grammar.
	 */
	private static SerializationGrammarAnalysis analysis = null;

	@Override
	public SerializationGrammarAnalysis getAnalysis() {
		if (analysis == null) {
			analysis = new SerializationGrammarAnalysis(
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
				new GrammarRuleValue [] {
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
	private class _GrammarRuleVectors
	{
		private final @NonNull GrammarRuleVector _0 // MultiplicityCS
			= new GrammarRuleVector(0x2000L);
		private final @NonNull GrammarRuleVector _1 // FirstPathElementCS|NextPathElementCS
			= new GrammarRuleVector(0x10010L);
		private final @NonNull GrammarRuleVector _2 // PathNameCS
			= new GrammarRuleVector(0x20000L);
		private final @NonNull GrammarRuleVector _3 // TemplateBindingCS
			= new GrammarRuleVector(0x400000L);
		private final @NonNull GrammarRuleVector _4 // TemplateParameterSubstitutionCS
			= new GrammarRuleVector(0x800000L);
		private final @NonNull GrammarRuleVector _5 // TypeParameterCS
			= new GrammarRuleVector(0x2000000L);
		private final @NonNull GrammarRuleVector _6 // TypeRefCS
			= new GrammarRuleVector(0x4000000L);
		private final @NonNull GrammarRuleVector _7 // TypedRefCS
			= new GrammarRuleVector(0x8000000L);
		private final @NonNull GrammarRuleVector _8 // TypedRefCS|TypedTypeRefCS
			= new GrammarRuleVector(0x18000000L);
		private final @NonNull GrammarRuleVector _9 // TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
			= new GrammarRuleVector(0x41c000000L);
	}

	/**
	 * String combinations used by assigned String EAttributes
	 */
	private class _EnumValues
	{
		private final @NonNull EnumerationValue _0 // '*|+|?'
			= new EnumerationValueMultiple(new @NonNull String[]{"*", "+", "?"});
		private final @NonNull EnumerationValue _1 // '|1'
			= new EnumerationValueSingle("|1");
	}

	/**
	 * Expression terms used during the matching process.
	 */
	private class _MatchTerms
	{
		private final @NonNull SerializationMatchTerm _00 // 0
			= new SerializationMatchTermInteger(0);
		private final @NonNull SerializationMatchTerm _01 // 1
			= new SerializationMatchTermInteger(1);
		private final @NonNull SerializationMatchTerm _02 // |MultiplicityBoundsCS::lowerBound|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull SerializationMatchTerm _03 // |MultiplicityBoundsCS::upperBound|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull SerializationMatchTerm _04 // |MultiplicityCS::isNullFree.'|1'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, ev._1);
		private final @NonNull SerializationMatchTerm _05 // |MultiplicityStringCS::stringBounds.'*|+|?'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._0);
		private final @NonNull SerializationMatchTerm _06 // |NamedElementCS::name|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull SerializationMatchTerm _07 // |PathElementCS::referredElement|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private final @NonNull SerializationMatchTerm _08 // |PathNameCS::ownedPathElements|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull SerializationMatchTerm _09 // |TemplateBindingCS::ownedMultiplicity|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull SerializationMatchTerm _10 // |TemplateBindingCS::ownedSubstitutions|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull SerializationMatchTerm _11 // |TemplateParameterSubstitutionCS::ownedActualParameter|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull SerializationMatchTerm _12 // |TemplateSignatureCS::ownedParameters|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull SerializationMatchTerm _13 // |TypeParameterCS::ownedExtends|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull SerializationMatchTerm _14 // |TypedTypeRefCS::ownedBinding|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull SerializationMatchTerm _15 // |TypedTypeRefCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _16 // |WildcardTypeRefCS::ownedExtends|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull SerializationMatchTerm _17 // (|MultiplicityBoundsCS::lowerBound| - 1)
			= new SerializationMatchTermSubtract(_02, _01);
		private final @NonNull SerializationMatchTerm _18 // (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
			= new SerializationMatchTermSubtract(_05, _01);
		private final @NonNull SerializationMatchTerm _19 // (|NamedElementCS::name| - 1)
			= new SerializationMatchTermSubtract(_06, _01);
		private final @NonNull SerializationMatchTerm _20 // (|PathElementCS::referredElement| - 1)
			= new SerializationMatchTermSubtract(_07, _01);
		private final @NonNull SerializationMatchTerm _21 // (|PathNameCS::ownedPathElements| - 1)
			= new SerializationMatchTermSubtract(_08, _01);
		private final @NonNull SerializationMatchTerm _22 // (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new SerializationMatchTermSubtract(_10, _01);
		private final @NonNull SerializationMatchTerm _23 // (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
			= new SerializationMatchTermSubtract(_11, _01);
		private final @NonNull SerializationMatchTerm _24 // (|TemplateSignatureCS::ownedParameters| - 1)
			= new SerializationMatchTermSubtract(_12, _01);
		private final @NonNull SerializationMatchTerm _25 // (|TypeParameterCS::ownedExtends| - 1)
			= new SerializationMatchTermSubtract(_13, _01);
		private final @NonNull SerializationMatchTerm _26 // (|TypeParameterCS::ownedExtends| > 0)
			= new SerializationMatchTermGreaterThan(_13, _00);
		private final @NonNull SerializationMatchTerm _27 // (|TypedTypeRefCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_15, _01);
	}

	/**
	 * Steps for the matching process.
	 */
	private class _MatchSteps
	{
		private final @NonNull SerializationMatchStep _00 // assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
			= new MatchStep_Assert(mt._17);
		private final @NonNull SerializationMatchStep _01 // assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
			= new MatchStep_Assert(mt._18);
		private final @NonNull SerializationMatchStep _02 // assert (|NamedElementCS::name| - 1) == 0
			= new MatchStep_Assert(mt._19);
		private final @NonNull SerializationMatchStep _03 // assert (|PathElementCS::referredElement| - 1) == 0
			= new MatchStep_Assert(mt._20);
		private final @NonNull SerializationMatchStep _04 // assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
			= new MatchStep_Assert(mt._23);
		private final @NonNull SerializationMatchStep _05 // assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._27);
		private final @NonNull SerializationMatchStep _06 // assign V0 = (|PathNameCS::ownedPathElements| - 1)
			= new MatchStep_Assign(0, mt._21);
		private final @NonNull SerializationMatchStep _07 // assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new MatchStep_Assign(0, mt._22);
		private final @NonNull SerializationMatchStep _08 // assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
			= new MatchStep_Assign(0, mt._24);
		private final @NonNull SerializationMatchStep _09 // assign V0 = (|TypeParameterCS::ownedExtends| > 0)
			= new MatchStep_Assign(0, mt._26);
		private final @NonNull SerializationMatchStep _10 // assign V0 = |MultiplicityBoundsCS::upperBound|
			= new MatchStep_Assign(0, mt._03);
		private final @NonNull SerializationMatchStep _11 // assign V0 = |MultiplicityCS::isNullFree.'|1'|
			= new MatchStep_Assign(0, mt._04);
		private final @NonNull SerializationMatchStep _12 // assign V0 = |TypedTypeRefCS::ownedBinding|
			= new MatchStep_Assign(0, mt._14);
		private final @NonNull SerializationMatchStep _13 // assign V0 = |WildcardTypeRefCS::ownedExtends|
			= new MatchStep_Assign(0, mt._16);
		private final @NonNull SerializationMatchStep _14 // assign V1 = (|TypeParameterCS::ownedExtends| - 1)
			= new MatchStep_Assign(1, mt._25);
		private final @NonNull SerializationMatchStep _15 // assign V1 = |MultiplicityCS::isNullFree.'|1'|
			= new MatchStep_Assign(1, mt._04);
		private final @NonNull SerializationMatchStep _16 // assign V1 = |TemplateBindingCS::ownedMultiplicity|
			= new MatchStep_Assign(1, mt._09);
		private final @NonNull SerializationMatchStep _17 // check-rule basecs::PathNameCS.ownedPathElements : 4|16
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._1/*FirstPathElementCS|NextPathElementCS*/);
		private final @NonNull SerializationMatchStep _18 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : 13
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, iv._0/*MultiplicityCS*/);
		private final @NonNull SerializationMatchStep _19 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : 23
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, iv._4/*TemplateParameterSubstitutionCS*/);
		private final @NonNull SerializationMatchStep _20 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 26
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, iv._6/*TypeRefCS*/);
		private final @NonNull SerializationMatchStep _21 // check-rule basecs::TemplateSignatureCS.ownedParameters : 25
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, iv._5/*TypeParameterCS*/);
		private final @NonNull SerializationMatchStep _22 // check-rule basecs::TypeParameterCS.ownedExtends : 27
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, iv._7/*TypedRefCS*/);
		private final @NonNull SerializationMatchStep _23 // check-rule basecs::TypedTypeRefCS.ownedBinding : 22
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, iv._3/*TemplateBindingCS*/);
		private final @NonNull SerializationMatchStep _24 // check-rule basecs::TypedTypeRefCS.ownedPathName : 17
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, iv._2/*PathNameCS*/);
		private final @NonNull SerializationMatchStep _25 // check-rule basecs::WildcardTypeRefCS.ownedExtends : 27
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, iv._7/*TypedRefCS*/);
	}

	/**
	 * The various serialization term used to serialize a serialization rule.
	 */
	private class _SerializationTerms
	{
		private final @NonNull SerializationStepLiteral _00 // 1*'&&'
									= new SerializationStepLiteral(-1, "&&");
		private final @NonNull SerializationStepLiteral _01 // 1*'('
									= new SerializationStepLiteral(-1, "(");
		private final @NonNull SerializationStepLiteral _02 // 1*')'
									= new SerializationStepLiteral(-1, ")");
		private final @NonNull SerializationStepLiteral _03 // 1*','
									= new SerializationStepLiteral(-1, ",");
		private final @NonNull SerializationStepLiteral _04 // 1*'..'
									= new SerializationStepLiteral(-1, "..");
		private final @NonNull SerializationStepLiteral _05 // 1*'::'
									= new SerializationStepLiteral(-1, "::");
		private final @NonNull SerializationStepLiteral _06 // 1*'?'
									= new SerializationStepLiteral(-1, "?");
		private final @NonNull SerializationStepLiteral _07 // 1*'['
									= new SerializationStepLiteral(-1, "[");
		private final @NonNull SerializationStepLiteral _08 // 1*']'
									= new SerializationStepLiteral(-1, "]");
		private final @NonNull SerializationStepLiteral _09 // 1*'extends'
									= new SerializationStepLiteral(-1, "extends");
		private final @NonNull SerializationStepLiteral _10 // 1*'|?'
									= new SerializationStepLiteral(-1, "|?");
		private final @NonNull SerializationStepAssignedRuleCall _11 // 1*MultiplicityBoundsCS::lowerBound=9
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 9 /* LOWER */);
		private final @NonNull SerializationStepAssignedRuleCall _12 // 1*MultiplicityBoundsCS::upperBound=29
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 29 /* UPPER */);
		private final @NonNull SerializationStepAssignKeyword _13 // 1*MultiplicityStringCS::stringBounds='*|+|?'
									= new SerializationStepAssignKeyword(-1, BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._0);
		private final @NonNull SerializationStepAssignedRuleCall _14 // 1*NamedElementCS::name=32
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 32 /* UnrestrictedName */);
		private final @NonNull SerializationStepCrossReference _15 // 1*PathElementCS::referredElement=UnreservedName
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"));
		private final @NonNull SerializationStepCrossReference _16 // 1*PathElementCS::referredElement=UnrestrictedName
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull SerializationStepAssignedRuleCall _17 // 1*PathNameCS::ownedPathElements+=16
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 16 /* NextPathElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _18 // 1*PathNameCS::ownedPathElements+=4
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 4 /* FirstPathElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _19 // 1*TemplateBindingCS::ownedSubstitutions+=23
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 23 /* TemplateParameterSubstitutionCS */);
		private final @NonNull SerializationStepAssignedRuleCall _20 // 1*TemplateParameterSubstitutionCS::ownedActualParameter=26
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 26 /* TypeRefCS */);
		private final @NonNull SerializationStepAssignedRuleCall _21 // 1*TemplateSignatureCS::ownedParameters+=25
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 25 /* TypeParameterCS */);
		private final @NonNull SerializationStepAssignedRuleCall _22 // 1*TypeParameterCS::ownedExtends+=27
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 27 /* TypedRefCS */);
		private final @NonNull SerializationStepAssignedRuleCall _23 // 1*TypedTypeRefCS::ownedBinding=22
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 22 /* TemplateBindingCS */);
		private final @NonNull SerializationStepAssignedRuleCall _24 // 1*TypedTypeRefCS::ownedPathName=17
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 17 /* PathNameCS */);
		private final @NonNull SerializationStepAssignedRuleCall _25 // 1*WildcardTypeRefCS::ownedExtends=27
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 27 /* TypedRefCS */);
		private final @NonNull SerializationStepSequence _26 // 1*steps-1..4
									= new SerializationStepSequence(-1, 1, 4);
		private final @NonNull SerializationStepSequence _27 // 1*steps-1..5
									= new SerializationStepSequence(-1, 1, 5);
		private final @NonNull SerializationStepSequence _28 // 1*steps-1..6
									= new SerializationStepSequence(-1, 1, 6);
		private final @NonNull SerializationStepSequence _29 // 1*steps-1..7
									= new SerializationStepSequence(-1, 1, 7);
		private final @NonNull SerializationStepSequence _30 // 1*steps-1..8
									= new SerializationStepSequence(-1, 1, 8);
		private final @NonNull SerializationStepLiteral _31 // V00*'|1'
									= new SerializationStepLiteral(0, "|1");
		private final @NonNull SerializationStepSequence _32 // V00*steps-3..5
									= new SerializationStepSequence(0, 3, 5);
		private final @NonNull SerializationStepSequence _33 // V00*steps-3..6
									= new SerializationStepSequence(0, 3, 6);
		private final @NonNull SerializationStepSequence _34 // V00*steps-3..8
									= new SerializationStepSequence(0, 3, 8);
		private final @NonNull SerializationStepSequence _35 // V00*steps-4..6
									= new SerializationStepSequence(0, 4, 6);
		private final @NonNull SerializationStepLiteral _36 // V01*'|1'
									= new SerializationStepLiteral(1, "|1");
		private final @NonNull SerializationStepAssignedRuleCall _37 // V01*TemplateBindingCS::ownedMultiplicity=13
									= new SerializationStepAssignedRuleCall(1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 13 /* MultiplicityCS */);
		private final @NonNull SerializationStepSequence _38 // V01*steps-6..8
									= new SerializationStepSequence(1, 6, 8);
	}

	/**
	 * The various string segment sequences that may be used to serialize a serialization term.
	 */
	private class _SerializationSegments
	{
		private final @NonNull SerializationSegment [] _0 = new @NonNull SerializationSegment @NonNull [] {
			IdiomsUtils.createCustomSegment(null, BaseCommentSegmentSupport.class) /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport */,
			IdiomsUtils.VALUE /* «value» */
		};
		private final @NonNull SerializationSegment [] _1 = new @NonNull SerializationSegment @NonNull [] {
			IdiomsUtils.NO_SPACE /* «! » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.NO_SPACE /* «! » */
		};
		private final @NonNull SerializationSegment [] _2 = new @NonNull SerializationSegment @NonNull [] {
			IdiomsUtils.NO_SPACE /* «! » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.SOFT_SPACE /* «? » */
		};
		private final @NonNull SerializationSegment [] _3 = new @NonNull SerializationSegment @NonNull [] {
			IdiomsUtils.NO_SPACE /* «! » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.SOFT_NEW_LINE /* «?\n» */
		};
		private final @NonNull SerializationSegment [] _4 = new @NonNull SerializationSegment @NonNull [] {
			IdiomsUtils.NO_SPACE /* «! » */,
			IdiomsUtils.VALUE /* «value» */
		};
		private final @NonNull SerializationSegment [] _5 = new @NonNull SerializationSegment @NonNull [] {
			IdiomsUtils.POP /* «-» */,
			IdiomsUtils.SOFT_SPACE /* «? » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.SOFT_NEW_LINE /* «?\n» */
		};
		private final @NonNull SerializationSegment [] _6 = new @NonNull SerializationSegment @NonNull [] {
			IdiomsUtils.SOFT_SPACE /* «? » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.PUSH /* «+» */,
			IdiomsUtils.SOFT_NEW_LINE /* «?\n» */
		};
		private final @NonNull SerializationSegment [] _7 = new @NonNull SerializationSegment @NonNull [] {
			IdiomsUtils.SOFT_SPACE /* «? » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.SOFT_SPACE /* «? » */
		};
		private final @NonNull SerializationSegment [] _8 = new @NonNull SerializationSegment @NonNull [] {
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _00 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _01 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _02 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _03 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _04 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _05 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _06 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _07 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _08 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _09 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _10 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _11 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _12 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
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
				null);
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
				null);
		private final @NonNull ParserRuleValue _13 // MultiplicityCS
			= new ParserRuleValue(13, "MultiplicityCS",
				new @NonNull SerializationRule [] {
					sr0._02 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					sr0._03 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					sr0._04 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */,
					sr0._05 /* { '[' stringBounds={'*|+|?'} ']' } */,
					sr0._06 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					sr0._07 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */
				},
				null);
		private final @NonNull ParserRuleValue _14 // MultiplicityStringCS
			= new ParserRuleValue(14, "MultiplicityStringCS",
				new @NonNull SerializationRule [] {
					sr0._08 /* stringBounds={'*|+|?'} */
				},
				null);
		private final @NonNull DataTypeRuleValue _15 // NUMBER_LITERAL
			= new DataTypeRuleValue(15, "NUMBER_LITERAL");
		private final @NonNull ParserRuleValue _16 // NextPathElementCS
			= new ParserRuleValue(16, "NextPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._09 /* referredElement=UnreservedName */
				},
				null);
		private final @NonNull ParserRuleValue _17 // PathNameCS
			= new ParserRuleValue(17, "PathNameCS",
				new @NonNull SerializationRule [] {
					sr0._10 /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				null);
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
				null);
		private final @NonNull ParserRuleValue _23 // TemplateParameterSubstitutionCS
			= new ParserRuleValue(23, "TemplateParameterSubstitutionCS",
				new @NonNull SerializationRule [] {
					sr0._12 /* ownedActualParameter=TypeRefCS */
				},
				null);
		private final @NonNull ParserRuleValue _24 // TemplateSignatureCS
			= new ParserRuleValue(24, "TemplateSignatureCS",
				new @NonNull SerializationRule [] {
					sr0._13 /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */
				},
				null);
		private final @NonNull ParserRuleValue _25 // TypeParameterCS
			= new ParserRuleValue(25, "TypeParameterCS",
				new @NonNull SerializationRule [] {
					sr0._14 /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				null);
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
				null);
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
				null);
	}

	/**
	 * Configuration for each EClass that may be serialized.
	 */
	private class _EClassValues
	{
		private final @NonNull EClassValue _0 // MultiplicityBoundsCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._01, sl._07) /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */,
					new SerializationRule_SegmentsList(sr0._02, sl._03) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._03, sl._04) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._04, sl._04) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, null
			);
		private final @NonNull EClassValue _1 // MultiplicityStringCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._05, sl._05) /* { '[' stringBounds={'*|+|?'} ']' } */,
					new SerializationRule_SegmentsList(sr0._06, sl._06) /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._07, sl._06) /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
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
					new SerializationRule_SegmentsList(sr0._16, sl._08) /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
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
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._03 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._16 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._12,
			null,
			null,
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				)
			});
		// Base::MultiplicityBoundsCS : { lowerBound=LOWER { '..' upperBound=UPPER }[?] }
		private @NonNull SerializationRule _01 = new SerializationRule(12,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._10 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._00 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._27 /* 1*steps-1..5 || «null» */,
				st._11 /* 1*MultiplicityBoundsCS::lowerBound=9 || «? » «value» «? » */,
				st._32 /* V00*steps-3..5 || «null» */,
				st._04 /* 1*'..' || «! » «value» «! » */,
				st._12 /* 1*MultiplicityBoundsCS::upperBound=29 || «? » «value» «? » */
			},
			sl._07,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' }
		private @NonNull SerializationRule _02 = new SerializationRule(13,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._10 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._00 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._29 /* 1*steps-1..7 || «null» */,
				st._07 /* 1*'[' || «! » «value» «! » */,
				st._11 /* 1*MultiplicityBoundsCS::lowerBound=9 || «? » «value» «? » */,
				st._35 /* V00*steps-4..6 || «null» */,
				st._04 /* 1*'..' || «! » «value» «! » */,
				st._12 /* 1*MultiplicityBoundsCS::upperBound=29 || «? » «value» «? » */,
				st._08 /* 1*']' || «! » «value» */
			},
			sl._03,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' }
		private @NonNull SerializationRule _03 = new SerializationRule(13,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._10 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._00 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._30 /* 1*steps-1..8 || «null» */,
				st._07 /* 1*'[' || «! » «value» «! » */,
				st._11 /* 1*MultiplicityBoundsCS::lowerBound=9 || «? » «value» «? » */,
				st._35 /* V00*steps-4..6 || «null» */,
				st._04 /* 1*'..' || «! » «value» «! » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _04 = new SerializationRule(13,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._15 /* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._10 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._00 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._30 /* 1*steps-1..8 || «null» */,
				st._07 /* 1*'[' || «! » «value» «! » */,
				st._11 /* 1*MultiplicityBoundsCS::lowerBound=9 || «? » «value» «? » */,
				st._35 /* V00*steps-4..6 || «null» */,
				st._04 /* 1*'..' || «! » «value» «! » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._1, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} ']' }
		private @NonNull SerializationRule _05 = new SerializationRule(13,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._01 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._26 /* 1*steps-1..4 || «null» */,
				st._07 /* 1*'[' || «! » «value» «! » */,
				st._13 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */,
				st._08 /* 1*']' || «! » «value» */
			},
			sl._05,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._0)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._0, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} '|?' ']' }
		private @NonNull SerializationRule _06 = new SerializationRule(13,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._01 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._27 /* 1*steps-1..5 || «null» */,
				st._07 /* 1*'[' || «! » «value» «! » */,
				st._13 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._0, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _07 = new SerializationRule(13,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._11 /* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._01 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._27 /* 1*steps-1..5 || «null» */,
				st._07 /* 1*'[' || «! » «value» «! » */,
				st._13 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._0, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._1, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityStringCS : stringBounds={'*|+|?'}
		private @NonNull SerializationRule _08 = new SerializationRule(14,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._01 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._13 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */
			},
			sl._12,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._0)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._0, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Base::NextPathElementCS : referredElement=UnreservedName
		private @NonNull SerializationRule _09 = new SerializationRule(16,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._03 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._15 /* 1*PathElementCS::referredElement=UnreservedName || «? » «value» «? » */
			},
			sl._12,
			null,
			null,
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				)
			});
		// Base::PathNameCS : { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] }
		private @NonNull SerializationRule _10 = new SerializationRule(17,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._17 /* check-rule basecs::PathNameCS.ownedPathElements : 4|16 */,
				ms._06 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(4, GrammarCardinality.ONE),
					new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::TemplateBindingCS : { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _11 = new SerializationRule(22,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._19 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 23 */,
				ms._18 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 13 */,
				ms._16 /* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				ms._07 /* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
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
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					iv._4) /* TemplateParameterSubstitutionCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					iv._0) /* MultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(23, GrammarCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// Base::TemplateParameterSubstitutionCS : ownedActualParameter=TypeRefCS
		private @NonNull SerializationRule _12 = new SerializationRule(23,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._20 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 26 */,
				ms._04 /* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._20 /* 1*TemplateParameterSubstitutionCS::ownedActualParameter=26 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._09,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					iv._6) /* TypeRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(26, GrammarCardinality.ONE)
					}
				)
			});
		// Base::TemplateSignatureCS : { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' }
		private @NonNull SerializationRule _13 = new SerializationRule(24,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._21 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 25 */,
				ms._08 /* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._29 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(25, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// Base::TypeParameterCS : { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] }
		private @NonNull SerializationRule _14 = new SerializationRule(25,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._22 /* check-rule basecs::TypeParameterCS.ownedExtends : 27 */,
				ms._02 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._09 /* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				ms._14 /* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._30 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(27, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::TypedTypeRefCS : { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] }
		private @NonNull SerializationRule _15 = new SerializationRule(28,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._24 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 17 */,
				ms._23 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 22 */,
				ms._12 /* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				ms._05 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
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
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._2) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._3) /* TemplateBindingCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(17, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(22, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// Base::WildcardTypeRefCS : { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
		private @NonNull SerializationRule _16 = new SerializationRule(34,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._25 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : 27 */,
				ms._13 /* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._27 /* 1*steps-1..5 || «null» */,
				st._06 /* 1*'?' || «? » «value» «? » */,
				st._32 /* V00*steps-3..5 || «null» */,
				st._09 /* 1*'extends' || «? » «value» «? » */,
				st._25 /* 1*WildcardTypeRefCS::ownedExtends=27 || «null» */
			},
			sl._08,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					iv._7) /* TypedRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(27, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
	}

	private _EClassValues ec;
	private _EnumValues ev;
	private _GrammarRuleVectors iv;
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
		iv = new _GrammarRuleVectors();
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
//	import DataTypeRuleValue;
//	import EClassValue;
//	import SerializationRule_SegmentsList;
//	import EnumerationValue;
//	import EnumerationValueMultiple;
//	import EnumerationValueSingle;
//	import GrammarCardinality;
//	import GrammarRuleValue;
//	import GrammarRuleVector;
//	import IdiomsUtils;
//	import ParserRuleValue;
//	import SerializationGrammarAnalysis;
//	import SerializationMatchStep;
//	import MatchStep_Assert;
//	import MatchStep_Assign;
//	import MatchStep_RuleCheck;
//	import SerializationMatchTerm;
//	import SerializationMatchTermEAttributeSize;
//	import SerializationMatchTermEStructuralFeatureSize;
//	import SerializationMatchTermGreaterThan;
//	import SerializationMatchTermInteger;
//	import SerializationMatchTermSubtract;
//	import SerializationRule;
//	import EAttribute_EnumerationValue_GrammarCardinality;
//	import EAttribute_EnumerationValues;
//	import EReference_RuleIndex_GrammarCardinality;
//	import EReference_RuleIndexes;
//	import EnumerationValue_GrammarCardinality;
//	import RuleIndex_GrammarCardinality;
//	import SerializationSegment;
//	import SerializationStep;
//	import SerializationStepAssignKeyword;
//	import SerializationStepAssignedRuleCall;
//	import SerializationStepCrossReference;
//	import SerializationStepLiteral;
//	import SerializationStepSequence;
//	import TerminalRuleValue;
//	import BaseCommentSegmentSupport;
//	import BaseCSPackage;
