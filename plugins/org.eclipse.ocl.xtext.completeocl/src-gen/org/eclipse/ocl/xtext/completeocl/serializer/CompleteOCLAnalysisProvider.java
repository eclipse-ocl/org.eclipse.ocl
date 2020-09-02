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
package org.eclipse.ocl.xtext.completeocl.serializer;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.AbstractAnalysisProvider;
import org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.runtime.DataTypeRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.EClassValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.EClassValue.SerializationRule_SegmentsList;
import org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue.EnumerationValueMultiple;
import org.eclipse.ocl.xtext.base.cs2text.runtime.EnumerationValue.EnumerationValueSingle;
import org.eclipse.ocl.xtext.base.cs2text.runtime.GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.GrammarRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.GrammarRuleVector;
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
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchTerm.SerializationMatchTermVariable;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValue_GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValues;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndex_GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndexes;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EnumerationValue_GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.RuleIndex_GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepAssignKeyword;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepAssignedRuleCall;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepAssigns;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepCrossReference;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepLiteral;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepSequence;
import org.eclipse.ocl.xtext.base.cs2text.runtime.TerminalRuleValue;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;

public class CompleteOCLAnalysisProvider extends AbstractAnalysisProvider
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
					ec._00  /* essentialoclcs::BooleanLiteralExpCS */,
					ec._01  /* completeoclcs::ClassifierContextDeclCS */,
					ec._02  /* essentialoclcs::CollectionLiteralExpCS */,
					ec._03  /* essentialoclcs::CollectionLiteralPartCS */,
					ec._04  /* essentialoclcs::CollectionPatternCS */,
					ec._05  /* essentialoclcs::CollectionTypeCS */,
					ec._06  /* completeoclcs::CompleteOCLDocumentCS */,
					ec._07  /* basecs::ConstraintCS */,
					ec._08  /* essentialoclcs::ContextCS */,
					ec._09  /* essentialoclcs::CurlyBracketedClauseCS */,
					ec._10  /* completeoclcs::DefOperationCS */,
					ec._11  /* completeoclcs::DefPropertyCS */,
					ec._12  /* essentialoclcs::ExpCS */,
					ec._13  /* essentialoclcs::ExpSpecificationCS */,
					ec._14  /* essentialoclcs::IfExpCS */,
					ec._15  /* essentialoclcs::IfThenExpCS */,
					ec._16  /* basecs::ImportCS */,
					ec._17  /* essentialoclcs::InfixExpCS */,
					ec._18  /* essentialoclcs::InvalidLiteralExpCS */,
					ec._19  /* essentialoclcs::LambdaLiteralExpCS */,
					ec._20  /* essentialoclcs::LetExpCS */,
					ec._21  /* essentialoclcs::LetVariableCS */,
					ec._22  /* essentialoclcs::MapLiteralExpCS */,
					ec._23  /* essentialoclcs::MapLiteralPartCS */,
					ec._24  /* essentialoclcs::MapTypeCS */,
					ec._25  /* basecs::MultiplicityBoundsCS */,
					ec._26  /* basecs::MultiplicityStringCS */,
					ec._27  /* essentialoclcs::NameExpCS */,
					ec._28  /* essentialoclcs::NavigatingArgCS */,
					ec._29  /* essentialoclcs::NestedExpCS */,
					ec._30  /* essentialoclcs::NullLiteralExpCS */,
					ec._31  /* essentialoclcs::NumberLiteralExpCS */,
					ec._32  /* completeoclcs::OperationContextDeclCS */,
					ec._33  /* completeoclcs::PackageDeclarationCS */,
					ec._34  /* basecs::ParameterCS */,
					ec._35  /* basecs::PathElementCS */,
					ec._36  /* basecs::PathElementWithURICS */,
					ec._37  /* basecs::PathNameCS */,
					ec._38  /* essentialoclcs::PatternExpCS */,
					ec._39  /* essentialoclcs::PrefixExpCS */,
					ec._40  /* basecs::PrimitiveTypeRefCS */,
					ec._41  /* completeoclcs::PropertyContextDeclCS */,
					ec._42  /* essentialoclcs::RoundBracketedClauseCS */,
					ec._43  /* essentialoclcs::SelfExpCS */,
					ec._44  /* essentialoclcs::ShadowPartCS */,
					ec._45  /* essentialoclcs::SquareBracketedClauseCS */,
					ec._46  /* essentialoclcs::StringLiteralExpCS */,
					ec._47  /* basecs::TemplateBindingCS */,
					ec._48  /* basecs::TemplateParameterSubstitutionCS */,
					ec._49  /* basecs::TemplateSignatureCS */,
					ec._50  /* essentialoclcs::TupleLiteralExpCS */,
					ec._51  /* essentialoclcs::TupleLiteralPartCS */,
					ec._52  /* basecs::TuplePartCS */,
					ec._53  /* basecs::TupleTypeCS */,
					ec._54  /* essentialoclcs::TypeLiteralExpCS */,
					ec._55  /* essentialoclcs::TypeNameExpCS */,
					ec._56  /* basecs::TypeParameterCS */,
					ec._57  /* basecs::TypedTypeRefCS */,
					ec._58  /* essentialoclcs::UnlimitedNaturalLiteralExpCS */,
					ec._59  /* essentialoclcs::VariableCS */,
					ec._60  /* basecs::WildcardTypeRefCS */
				},
				/**
				 *	The indexable per-grammar rule meta data.
				 */
				new GrammarRuleValue [] {
					gr._000  /* 0 : ANY_OTHER */,
					gr._001  /* 1 : BinaryOperatorName */,
					gr._002  /* 2 : BooleanLiteralExpCS */,
					gr._003  /* 3 : ClassifierContextDeclCS */,
					gr._004  /* 4 : CoIteratorVariableCS */,
					gr._005  /* 5 : CollectionLiteralExpCS */,
					gr._006  /* 6 : CollectionLiteralPartCS */,
					gr._007  /* 7 : CollectionPatternCS */,
					gr._008  /* 8 : CollectionTypeCS */,
					gr._009  /* 9 : CollectionTypeIdentifier */,
					gr._010  /* 10 : CompleteOCLDocumentCS */,
					gr._011  /* 11 : CompleteOCLNavigationOperatorName */,
					gr._012  /* 12 : ConstraintCS */,
					gr._013  /* 13 : ContextDeclCS */,
					gr._014  /* 14 : CurlyBracketedClauseCS */,
					gr._015  /* 15 : DOUBLE_QUOTED_STRING */,
					gr._016  /* 16 : DefCS */,
					gr._017  /* 17 : DefOperationCS */,
					gr._018  /* 18 : DefParameterCS */,
					gr._019  /* 19 : DefPropertyCS */,
					gr._020  /* 20 : ESCAPED_CHARACTER */,
					gr._021  /* 21 : ESCAPED_ID */,
					gr._022  /* 22 : ElseIfThenExpCS */,
					gr._023  /* 23 : EssentialOCLInfixOperatorName */,
					gr._024  /* 24 : EssentialOCLNavigationOperatorName */,
					gr._025  /* 25 : EssentialOCLReservedKeyword */,
					gr._026  /* 26 : EssentialOCLUnaryOperatorName */,
					gr._027  /* 27 : EssentialOCLUnreservedName */,
					gr._028  /* 28 : EssentialOCLUnrestrictedName */,
					gr._029  /* 29 : ExpCS */,
					gr._030  /* 30 : FirstPathElementCS */,
					gr._031  /* 31 : ID */,
					gr._032  /* 32 : INT */,
					gr._033  /* 33 : Identifier */,
					gr._034  /* 34 : IfExpCS */,
					gr._035  /* 35 : ImportCS */,
					gr._036  /* 36 : InfixOperatorName */,
					gr._037  /* 37 : InvalidLiteralExpCS */,
					gr._038  /* 38 : LETTER_CHARACTER */,
					gr._039  /* 39 : LOWER */,
					gr._040  /* 40 : LambdaLiteralExpCS */,
					gr._041  /* 41 : LetExpCS */,
					gr._042  /* 42 : LetVariableCS */,
					gr._043  /* 43 : ML_COMMENT */,
					gr._044  /* 44 : ML_SINGLE_QUOTED_STRING */,
					gr._045  /* 45 : MapLiteralExpCS */,
					gr._046  /* 46 : MapLiteralPartCS */,
					gr._047  /* 47 : MapTypeCS */,
					gr._048  /* 48 : Model */,
					gr._049  /* 49 : MultiplicityBoundsCS */,
					gr._050  /* 50 : MultiplicityCS */,
					gr._051  /* 51 : MultiplicityStringCS */,
					gr._052  /* 52 : NUMBER_LITERAL */,
					gr._053  /* 53 : NameExpCS */,
					gr._054  /* 54 : NavigatingArgCS */,
					gr._055  /* 55 : NavigatingArgExpCS */,
					gr._056  /* 56 : NavigatingBarArgCS */,
					gr._057  /* 57 : NavigatingCommaArgCS */,
					gr._058  /* 58 : NavigatingSemiArgCS */,
					gr._059  /* 59 : NavigationOperatorName */,
					gr._060  /* 60 : NestedExpCS */,
					gr._061  /* 61 : NextPathElementCS */,
					gr._062  /* 62 : NullLiteralExpCS */,
					gr._063  /* 63 : NumberLiteralExpCS */,
					gr._064  /* 64 : OperationContextDeclCS */,
					gr._065  /* 65 : PackageDeclarationCS */,
					gr._066  /* 66 : ParameterCS */,
					gr._067  /* 67 : PathNameCS */,
					gr._068  /* 68 : PatternExpCS */,
					gr._069  /* 69 : PrefixedLetExpCS */,
					gr._070  /* 70 : PrefixedPrimaryExpCS */,
					gr._071  /* 71 : PrimaryExpCS */,
					gr._072  /* 72 : PrimitiveLiteralExpCS */,
					gr._073  /* 73 : PrimitiveTypeCS */,
					gr._074  /* 74 : PrimitiveTypeIdentifier */,
					gr._075  /* 75 : PropertyContextDeclCS */,
					gr._076  /* 76 : RoundBracketedClauseCS */,
					gr._077  /* 77 : SIMPLE_ID */,
					gr._078  /* 78 : SINGLE_QUOTED_STRING */,
					gr._079  /* 79 : SL_COMMENT */,
					gr._080  /* 80 : SelfExpCS */,
					gr._081  /* 81 : ShadowPartCS */,
					gr._082  /* 82 : SimplePathNameCS */,
					gr._083  /* 83 : SpecificationCS */,
					gr._084  /* 84 : SquareBracketedClauseCS */,
					gr._085  /* 85 : StringLiteral */,
					gr._086  /* 86 : StringLiteralExpCS */,
					gr._087  /* 87 : TemplateBindingCS */,
					gr._088  /* 88 : TemplateParameterSubstitutionCS */,
					gr._089  /* 89 : TemplateSignatureCS */,
					gr._090  /* 90 : TupleLiteralExpCS */,
					gr._091  /* 91 : TupleLiteralPartCS */,
					gr._092  /* 92 : TuplePartCS */,
					gr._093  /* 93 : TupleTypeCS */,
					gr._094  /* 94 : TypeExpCS */,
					gr._095  /* 95 : TypeExpWithoutMultiplicityCS */,
					gr._096  /* 96 : TypeLiteralCS */,
					gr._097  /* 97 : TypeLiteralExpCS */,
					gr._098  /* 98 : TypeLiteralWithMultiplicityCS */,
					gr._099  /* 99 : TypeNameExpCS */,
					gr._100  /* 100 : TypeParameterCS */,
					gr._101  /* 101 : TypeRefCS */,
					gr._102  /* 102 : TypedRefCS */,
					gr._103  /* 103 : TypedTypeRefCS */,
					gr._104  /* 104 : UNQUOTED_STRING */,
					gr._105  /* 105 : UPPER */,
					gr._106  /* 106 : URI */,
					gr._107  /* 107 : URIFirstPathElementCS */,
					gr._108  /* 108 : URIPathNameCS */,
					gr._109  /* 109 : UnaryOperatorName */,
					gr._110  /* 110 : UnlimitedNaturalLiteralExpCS */,
					gr._111  /* 111 : UnreservedName */,
					gr._112  /* 112 : UnrestrictedName */,
					gr._113  /* 113 : WS */,
					gr._114  /* 114 : WildcardTypeRefCS */
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
		private final @NonNull GrammarRuleVector _0 // CoIteratorVariableCS
			= new GrammarRuleVector(0x10L);
		private final @NonNull GrammarRuleVector _1 // CollectionLiteralPartCS
			= new GrammarRuleVector(0x40L);
		private final @NonNull GrammarRuleVector _2 // CollectionTypeCS
			= new GrammarRuleVector(0x100L);
		private final @NonNull GrammarRuleVector _3 // ConstraintCS
			= new GrammarRuleVector(0x1000L);
		private final @NonNull GrammarRuleVector _4 // ContextDeclCS
			= new GrammarRuleVector(0x2000L);
		private final @NonNull GrammarRuleVector _5 // CurlyBracketedClauseCS
			= new GrammarRuleVector(0x4000L);
		private final @NonNull GrammarRuleVector _6 // DefCS
			= new GrammarRuleVector(0x10000L);
		private final @NonNull GrammarRuleVector _7 // DefParameterCS
			= new GrammarRuleVector(0x40000L);
		private final @NonNull GrammarRuleVector _8 // DefCS|DefOperationCS|DefPropertyCS
			= new GrammarRuleVector(0xb0000L);
		private final @NonNull GrammarRuleVector _9 // ElseIfThenExpCS
			= new GrammarRuleVector(0x400000L);
		private final @NonNull GrammarRuleVector _10 // ExpCS
			= new GrammarRuleVector(0x20000000L);
		private final @NonNull GrammarRuleVector _11 // FirstPathElementCS
			= new GrammarRuleVector(0x40000000L);
		private final @NonNull GrammarRuleVector _12 // ImportCS
			= new GrammarRuleVector(0x800000000L);
		private final @NonNull GrammarRuleVector _13 // LetVariableCS
			= new GrammarRuleVector(0x40000000000L);
		private final @NonNull GrammarRuleVector _14 // MapLiteralPartCS
			= new GrammarRuleVector(0x400000000000L);
		private final @NonNull GrammarRuleVector _15 // MapTypeCS
			= new GrammarRuleVector(0x800000000000L);
		private final @NonNull GrammarRuleVector _16 // MultiplicityCS
			= new GrammarRuleVector(0x4000000000000L);
		private final @NonNull GrammarRuleVector _17 // NavigatingArgExpCS
			= new GrammarRuleVector(0x80000000000000L);
		private final @NonNull GrammarRuleVector _18 // NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
			= new GrammarRuleVector(0x700000000000000L);
		private final @NonNull GrammarRuleVector _19 // NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
			= new GrammarRuleVector(0x740000000000000L);
		private final @NonNull GrammarRuleVector _20 // FirstPathElementCS|NextPathElementCS
			= new GrammarRuleVector(0x2000000040000000L);
		private final @NonNull GrammarRuleVector _21 // PackageDeclarationCS
			= new GrammarRuleVector(0x0L,0x2L);
		private final @NonNull GrammarRuleVector _22 // ParameterCS
			= new GrammarRuleVector(0x0L,0x4L);
		private final @NonNull GrammarRuleVector _23 // PathNameCS
			= new GrammarRuleVector(0x0L,0x8L);
		private final @NonNull GrammarRuleVector _24 // PatternExpCS
			= new GrammarRuleVector(0x0L,0x10L);
		private final @NonNull GrammarRuleVector _25 // ExpCS|PatternExpCS
			= new GrammarRuleVector(0x20000000L,0x10L);
		private final @NonNull GrammarRuleVector _26 // PrefixedLetExpCS
			= new GrammarRuleVector(0x0L,0x20L);
		private final @NonNull GrammarRuleVector _27 // LetExpCS|PrefixedLetExpCS
			= new GrammarRuleVector(0x20000000000L,0x20L);
		private final @NonNull GrammarRuleVector _28 // PrefixedPrimaryExpCS
			= new GrammarRuleVector(0x0L,0x40L);
		private final @NonNull GrammarRuleVector _29 // ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS
			= new GrammarRuleVector(0x2008L,0x801L);
		private final @NonNull GrammarRuleVector _30 // RoundBracketedClauseCS
			= new GrammarRuleVector(0x0L,0x1000L);
		private final @NonNull GrammarRuleVector _31 // ShadowPartCS
			= new GrammarRuleVector(0x0L,0x20000L);
		private final @NonNull GrammarRuleVector _32 // SpecificationCS
			= new GrammarRuleVector(0x0L,0x80000L);
		private final @NonNull GrammarRuleVector _33 // SquareBracketedClauseCS
			= new GrammarRuleVector(0x0L,0x100000L);
		private final @NonNull GrammarRuleVector _34 // StringLiteralExpCS
			= new GrammarRuleVector(0x0L,0x400000L);
		private final @NonNull GrammarRuleVector _35 // TemplateBindingCS
			= new GrammarRuleVector(0x0L,0x800000L);
		private final @NonNull GrammarRuleVector _36 // TemplateParameterSubstitutionCS
			= new GrammarRuleVector(0x0L,0x1000000L);
		private final @NonNull GrammarRuleVector _37 // TemplateSignatureCS
			= new GrammarRuleVector(0x0L,0x2000000L);
		private final @NonNull GrammarRuleVector _38 // TupleLiteralPartCS
			= new GrammarRuleVector(0x0L,0x8000000L);
		private final @NonNull GrammarRuleVector _39 // TuplePartCS
			= new GrammarRuleVector(0x0L,0x10000000L);
		private final @NonNull GrammarRuleVector _40 // TypeExpCS
			= new GrammarRuleVector(0x0L,0x40000000L);
		private final @NonNull GrammarRuleVector _41 // TypeExpWithoutMultiplicityCS
			= new GrammarRuleVector(0x0L,0x80000000L);
		private final @NonNull GrammarRuleVector _42 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
			= new GrammarRuleVector(0x800000000100L,0x120000200L);
		private final @NonNull GrammarRuleVector _43 // TypeLiteralWithMultiplicityCS
			= new GrammarRuleVector(0x0L,0x400000000L);
		private final @NonNull GrammarRuleVector _44 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS
			= new GrammarRuleVector(0x800000000100L,0x520000200L);
		private final @NonNull GrammarRuleVector _45 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new GrammarRuleVector(0x800000000180L,0x9a0000200L);
		private final @NonNull GrammarRuleVector _46 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new GrammarRuleVector(0x800000000180L,0x9e0000200L);
		private final @NonNull GrammarRuleVector _47 // TypeParameterCS
			= new GrammarRuleVector(0x0L,0x1000000000L);
		private final @NonNull GrammarRuleVector _48 // TypeRefCS
			= new GrammarRuleVector(0x0L,0x2000000000L);
		private final @NonNull GrammarRuleVector _49 // TypedRefCS
			= new GrammarRuleVector(0x0L,0x4000000000L);
		private final @NonNull GrammarRuleVector _50 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS
			= new GrammarRuleVector(0x800000000100L,0xc120000200L);
		private final @NonNull GrammarRuleVector _51 // NextPathElementCS|URIFirstPathElementCS
			= new GrammarRuleVector(0x2000000000000000L,0x80000000000L);
		private final @NonNull GrammarRuleVector _52 // FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
			= new GrammarRuleVector(0x2000000040000000L,0x80000000000L);
		private final @NonNull GrammarRuleVector _53 // URIPathNameCS
			= new GrammarRuleVector(0x0L,0x100000000000L);
		private final @NonNull GrammarRuleVector _54 // BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0xc000002000000004L,0x400000400100L);
		private final @NonNull GrammarRuleVector _55 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0xd020212400000024L,0x400204410180L);
		private final @NonNull GrammarRuleVector _56 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0xd020212400000024L,0x4002044101c0L);
		private final @NonNull GrammarRuleVector _57 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0xd020232400000024L,0x4002044101e0L);
		private final @NonNull GrammarRuleVector _58 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0xd020232420000024L,0x4002044101e0L);
		private final @NonNull GrammarRuleVector _59 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0xd0a0232420000024L,0x4002044101e0L);
		private final @NonNull GrammarRuleVector _60 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0xd020232420000024L,0x4002044101f0L);
		private final @NonNull GrammarRuleVector _61 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
			= new GrammarRuleVector(0x800000000100L,0x400e120000200L);
	}

	/**
	 * String combinations used by assigned String EAttributes
	 */
	private class _EnumValues
	{
		private final @NonNull EnumerationValue _00 // '*|+|?'
			= new EnumerationValueMultiple(new @NonNull String[]{"*", "+", "?"});
		private final @NonNull EnumerationValue _01 // ','
			= new EnumerationValueSingle(",");
		private final @NonNull EnumerationValue _02 // '::*'
			= new EnumerationValueSingle("::*");
		private final @NonNull EnumerationValue _03 // ';'
			= new EnumerationValueSingle(";");
		private final @NonNull EnumerationValue _04 // '@'
			= new EnumerationValueSingle("@");
		private final @NonNull EnumerationValue _05 // 'Map'
			= new EnumerationValueSingle("Map");
		private final @NonNull EnumerationValue _06 // 'Tuple'
			= new EnumerationValueSingle("Tuple");
		private final @NonNull EnumerationValue _07 // 'false|true'
			= new EnumerationValueMultiple(new @NonNull String[]{"false", "true"});
		private final @NonNull EnumerationValue _08 // 'static'
			= new EnumerationValueSingle("static");
		private final @NonNull EnumerationValue _09 // '|'
			= new EnumerationValueSingle("|");
		private final @NonNull EnumerationValue _10 // '|1'
			= new EnumerationValueSingle("|1");
	}

	/**
	 * Expression terms used during the matching process.
	 */
	private class _MatchTerms
	{
		private final @NonNull SerializationMatchTerm _000 // 0
			= new SerializationMatchTermInteger(0);
		private final @NonNull SerializationMatchTerm _001 // 1
			= new SerializationMatchTermInteger(1);
		private final @NonNull SerializationMatchTerm _002 // V0
			= new SerializationMatchTermVariable(0);
		private final @NonNull SerializationMatchTerm _003 // |AbstractNameExpCS::isPre.'@'|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, ev._04);
		private final @NonNull SerializationMatchTerm _004 // |AbstractNameExpCS::ownedCurlyBracketedClause|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull SerializationMatchTerm _005 // |AbstractNameExpCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _006 // |AbstractNameExpCS::ownedRoundBracketedClause|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull SerializationMatchTerm _007 // |AbstractNameExpCS::ownedSquareBracketedClauses|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		private final @NonNull SerializationMatchTerm _008 // |BooleanLiteralExpCS::symbol.'false|true'|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._07);
		private final @NonNull SerializationMatchTerm _009 // |ClassifierContextDeclCS::ownedDefinitions|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS);
		private final @NonNull SerializationMatchTerm _010 // |ClassifierContextDeclCS::ownedInvariants|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS);
		private final @NonNull SerializationMatchTerm _011 // |ClassifierContextDeclCS::selfName|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME);
		private final @NonNull SerializationMatchTerm _012 // |CollectionLiteralExpCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _013 // |CollectionLiteralExpCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _014 // |CollectionLiteralPartCS::ownedExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull SerializationMatchTerm _015 // |CollectionLiteralPartCS::ownedLastExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		private final @NonNull SerializationMatchTerm _016 // |CollectionPatternCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _017 // |CollectionPatternCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _018 // |CollectionPatternCS::restVariableName|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		private final @NonNull SerializationMatchTerm _019 // |CollectionTypeCS::name|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		private final @NonNull SerializationMatchTerm _020 // |CollectionTypeCS::ownedCollectionMultiplicity|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		private final @NonNull SerializationMatchTerm _021 // |CollectionTypeCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _022 // |CompleteOCLDocumentCS::ownedContexts|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS);
		private final @NonNull SerializationMatchTerm _023 // |CompleteOCLDocumentCS::ownedPackages|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES);
		private final @NonNull SerializationMatchTerm _024 // |ConstraintCS::ownedMessageSpecification|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		private final @NonNull SerializationMatchTerm _025 // |ConstraintCS::ownedSpecification|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		private final @NonNull SerializationMatchTerm _026 // |ContextCS::ownedExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		private final @NonNull SerializationMatchTerm _027 // |CurlyBracketedClauseCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _028 // |DefCS::isStatic.'static'|
			= new SerializationMatchTermEAttributeSize(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC, ev._08);
		private final @NonNull SerializationMatchTerm _029 // |DefCS::ownedSpecification|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION);
		private final @NonNull SerializationMatchTerm _030 // |DefOperationCS::ownedParameters|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS);
		private final @NonNull SerializationMatchTerm _031 // |ExpSpecificationCS::ownedExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		private final @NonNull SerializationMatchTerm _032 // |FeatureContextDeclCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _033 // |IfExpCS::ownedCondition|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		private final @NonNull SerializationMatchTerm _034 // |IfExpCS::ownedElseExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		private final @NonNull SerializationMatchTerm _035 // |IfExpCS::ownedIfThenExpressions|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		private final @NonNull SerializationMatchTerm _036 // |IfExpCS::ownedThenExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull SerializationMatchTerm _037 // |IfThenExpCS::ownedCondition|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		private final @NonNull SerializationMatchTerm _038 // |IfThenExpCS::ownedThenExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull SerializationMatchTerm _039 // |ImportCS::isAll.'::*'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, ev._02);
		private final @NonNull SerializationMatchTerm _040 // |ImportCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _041 // |InfixExpCS::ownedLeft|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		private final @NonNull SerializationMatchTerm _042 // |LambdaLiteralExpCS::ownedExpressionCS|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		private final @NonNull SerializationMatchTerm _043 // |LetExpCS::ownedInExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		private final @NonNull SerializationMatchTerm _044 // |LetExpCS::ownedVariables|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		private final @NonNull SerializationMatchTerm _045 // |LetVariableCS::ownedRoundBracketedClause|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull SerializationMatchTerm _046 // |MapLiteralExpCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _047 // |MapLiteralExpCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _048 // |MapLiteralPartCS::ownedKey|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		private final @NonNull SerializationMatchTerm _049 // |MapLiteralPartCS::ownedValue|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		private final @NonNull SerializationMatchTerm _050 // |MapTypeCS::name.'Map'|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, ev._05);
		private final @NonNull SerializationMatchTerm _051 // |MapTypeCS::ownedKeyType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		private final @NonNull SerializationMatchTerm _052 // |MapTypeCS::ownedValueType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		private final @NonNull SerializationMatchTerm _053 // |MultiplicityBoundsCS::lowerBound|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull SerializationMatchTerm _054 // |MultiplicityBoundsCS::upperBound|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull SerializationMatchTerm _055 // |MultiplicityCS::isNullFree.'|1'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, ev._10);
		private final @NonNull SerializationMatchTerm _056 // |MultiplicityStringCS::stringBounds.'*|+|?'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._00);
		private final @NonNull SerializationMatchTerm _057 // |NamedElementCS::name|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull SerializationMatchTerm _058 // |NavigatingArgCS::ownedCoIterator|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		private final @NonNull SerializationMatchTerm _059 // |NavigatingArgCS::ownedInitExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull SerializationMatchTerm _060 // |NavigatingArgCS::ownedNameExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		private final @NonNull SerializationMatchTerm _061 // |NavigatingArgCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _062 // |NavigatingArgCS::prefix.','|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._01);
		private final @NonNull SerializationMatchTerm _063 // |NavigatingArgCS::prefix.';'|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._03);
		private final @NonNull SerializationMatchTerm _064 // |NavigatingArgCS::prefix.'|'|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._09);
		private final @NonNull SerializationMatchTerm _065 // |NestedExpCS::ownedExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		private final @NonNull SerializationMatchTerm _066 // |NumberLiteralExpCS::symbol|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull SerializationMatchTerm _067 // |OperationContextDeclCS::ownedBodies|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES);
		private final @NonNull SerializationMatchTerm _068 // |OperationContextDeclCS::ownedParameters|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS);
		private final @NonNull SerializationMatchTerm _069 // |OperationContextDeclCS::ownedPostconditions|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS);
		private final @NonNull SerializationMatchTerm _070 // |OperationContextDeclCS::ownedPreconditions|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS);
		private final @NonNull SerializationMatchTerm _071 // |OperatorExpCS::ownedRight|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull SerializationMatchTerm _072 // |PackageDeclarationCS::ownedContexts|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS);
		private final @NonNull SerializationMatchTerm _073 // |PackageDeclarationCS::ownedInvariants|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS);
		private final @NonNull SerializationMatchTerm _074 // |PathElementCS::referredElement|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private final @NonNull SerializationMatchTerm _075 // |PathNameCS::ownedPathElements|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull SerializationMatchTerm _076 // |PathNameDeclCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _077 // |PatternExpCS::ownedPatternType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		private final @NonNull SerializationMatchTerm _078 // |PatternExpCS::patternVariableName|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		private final @NonNull SerializationMatchTerm _079 // |PrimitiveTypeRefCS::name|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		private final @NonNull SerializationMatchTerm _080 // |PropertyContextDeclCS::ownedDefaultExpressions|
			= new SerializationMatchTermEStructuralFeatureSize(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull SerializationMatchTerm _081 // |RootCS::ownedImports|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		private final @NonNull SerializationMatchTerm _082 // |RoundBracketedClauseCS::ownedArguments|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		private final @NonNull SerializationMatchTerm _083 // |ShadowPartCS::ownedInitExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull SerializationMatchTerm _084 // |ShadowPartCS::referredProperty|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		private final @NonNull SerializationMatchTerm _085 // |SpecificationCS::exprString|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		private final @NonNull SerializationMatchTerm _086 // |SquareBracketedClauseCS::ownedTerms|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		private final @NonNull SerializationMatchTerm _087 // |StringLiteralExpCS::segments|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		private final @NonNull SerializationMatchTerm _088 // |TemplateBindingCS::ownedMultiplicity|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull SerializationMatchTerm _089 // |TemplateBindingCS::ownedSubstitutions|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull SerializationMatchTerm _090 // |TemplateParameterSubstitutionCS::ownedActualParameter|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull SerializationMatchTerm _091 // |TemplateSignatureCS::ownedParameters|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull SerializationMatchTerm _092 // |TemplateableElementCS::ownedSignature|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		private final @NonNull SerializationMatchTerm _093 // |TupleLiteralExpCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _094 // |TupleTypeCS::name.'Tuple'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, ev._06);
		private final @NonNull SerializationMatchTerm _095 // |TupleTypeCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _096 // |TypeLiteralExpCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _097 // |TypeNameExpCS::ownedCurlyBracketedClause|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull SerializationMatchTerm _098 // |TypeNameExpCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _099 // |TypeNameExpCS::ownedPatternGuard|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		private final @NonNull SerializationMatchTerm _100 // |TypeParameterCS::ownedExtends|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull SerializationMatchTerm _101 // |TypedElementCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _102 // |TypedRefCS::ownedMultiplicity|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull SerializationMatchTerm _103 // |TypedTypeRefCS::ownedBinding|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull SerializationMatchTerm _104 // |TypedTypeRefCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _105 // |VariableCS::ownedInitExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull SerializationMatchTerm _106 // |VariableCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _107 // |WildcardTypeRefCS::ownedExtends|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull SerializationMatchTerm _108 // (|AbstractNameExpCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_005, _001);
		private final @NonNull SerializationMatchTerm _109 // (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
			= new SerializationMatchTermSubtract(_008, _001);
		private final @NonNull SerializationMatchTerm _110 // (|CollectionLiteralExpCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_012, _001);
		private final @NonNull SerializationMatchTerm _111 // (|CollectionLiteralExpCS::ownedParts| > 0)
			= new SerializationMatchTermGreaterThan(_012, _000);
		private final @NonNull SerializationMatchTerm _112 // (|CollectionLiteralExpCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_013, _001);
		private final @NonNull SerializationMatchTerm _113 // (|CollectionLiteralPartCS::ownedExpression| - 1)
			= new SerializationMatchTermSubtract(_014, _001);
		private final @NonNull SerializationMatchTerm _114 // (|CollectionPatternCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_016, _001);
		private final @NonNull SerializationMatchTerm _115 // (|CollectionPatternCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_017, _001);
		private final @NonNull SerializationMatchTerm _116 // (|CollectionTypeCS::name| - 1)
			= new SerializationMatchTermSubtract(_019, _001);
		private final @NonNull SerializationMatchTerm _117 // (|ConstraintCS::ownedSpecification| - 1)
			= new SerializationMatchTermSubtract(_025, _001);
		private final @NonNull SerializationMatchTerm _118 // (|ContextCS::ownedExpression| - 1)
			= new SerializationMatchTermSubtract(_026, _001);
		private final @NonNull SerializationMatchTerm _119 // (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_027, _001);
		private final @NonNull SerializationMatchTerm _120 // (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new SerializationMatchTermGreaterThan(_027, _000);
		private final @NonNull SerializationMatchTerm _121 // (|DefCS::ownedSpecification| - 1)
			= new SerializationMatchTermSubtract(_029, _001);
		private final @NonNull SerializationMatchTerm _122 // (|DefOperationCS::ownedParameters| - 1)
			= new SerializationMatchTermSubtract(_030, _001);
		private final @NonNull SerializationMatchTerm _123 // (|DefOperationCS::ownedParameters| > 0)
			= new SerializationMatchTermGreaterThan(_030, _000);
		private final @NonNull SerializationMatchTerm _124 // (|ExpSpecificationCS::ownedExpression| - 1)
			= new SerializationMatchTermSubtract(_031, _001);
		private final @NonNull SerializationMatchTerm _125 // (|FeatureContextDeclCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_032, _001);
		private final @NonNull SerializationMatchTerm _126 // (|IfExpCS::ownedCondition| - 1)
			= new SerializationMatchTermSubtract(_033, _001);
		private final @NonNull SerializationMatchTerm _127 // (|IfExpCS::ownedElseExpression| - 1)
			= new SerializationMatchTermSubtract(_034, _001);
		private final @NonNull SerializationMatchTerm _128 // (|IfExpCS::ownedThenExpression| - 1)
			= new SerializationMatchTermSubtract(_036, _001);
		private final @NonNull SerializationMatchTerm _129 // (|IfThenExpCS::ownedCondition| - 1)
			= new SerializationMatchTermSubtract(_037, _001);
		private final @NonNull SerializationMatchTerm _130 // (|IfThenExpCS::ownedThenExpression| - 1)
			= new SerializationMatchTermSubtract(_038, _001);
		private final @NonNull SerializationMatchTerm _131 // (|ImportCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_040, _001);
		private final @NonNull SerializationMatchTerm _132 // (|InfixExpCS::ownedLeft| - 1)
			= new SerializationMatchTermSubtract(_041, _001);
		private final @NonNull SerializationMatchTerm _133 // (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
			= new SerializationMatchTermSubtract(_042, _001);
		private final @NonNull SerializationMatchTerm _134 // (|LetExpCS::ownedInExpression| - 1)
			= new SerializationMatchTermSubtract(_043, _001);
		private final @NonNull SerializationMatchTerm _135 // (|LetExpCS::ownedVariables| - 1)
			= new SerializationMatchTermSubtract(_044, _001);
		private final @NonNull SerializationMatchTerm _136 // (|MapLiteralExpCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_046, _001);
		private final @NonNull SerializationMatchTerm _137 // (|MapLiteralExpCS::ownedParts| > 0)
			= new SerializationMatchTermGreaterThan(_046, _000);
		private final @NonNull SerializationMatchTerm _138 // (|MapLiteralExpCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_047, _001);
		private final @NonNull SerializationMatchTerm _139 // (|MapLiteralPartCS::ownedKey| - 1)
			= new SerializationMatchTermSubtract(_048, _001);
		private final @NonNull SerializationMatchTerm _140 // (|MapLiteralPartCS::ownedValue| - 1)
			= new SerializationMatchTermSubtract(_049, _001);
		private final @NonNull SerializationMatchTerm _141 // (|MapTypeCS::name.'Map'| - 1)
			= new SerializationMatchTermSubtract(_050, _001);
		private final @NonNull SerializationMatchTerm _142 // (|MapTypeCS::ownedKeyType| - V0)
			= new SerializationMatchTermSubtract(_051, _002);
		private final @NonNull SerializationMatchTerm _143 // (|MultiplicityBoundsCS::lowerBound| - 1)
			= new SerializationMatchTermSubtract(_053, _001);
		private final @NonNull SerializationMatchTerm _144 // (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
			= new SerializationMatchTermSubtract(_056, _001);
		private final @NonNull SerializationMatchTerm _145 // (|NamedElementCS::name| - 1)
			= new SerializationMatchTermSubtract(_057, _001);
		private final @NonNull SerializationMatchTerm _146 // (|NavigatingArgCS::ownedCoIterator| - 1)
			= new SerializationMatchTermSubtract(_058, _001);
		private final @NonNull SerializationMatchTerm _147 // (|NavigatingArgCS::ownedInitExpression| - 1)
			= new SerializationMatchTermSubtract(_059, _001);
		private final @NonNull SerializationMatchTerm _148 // (|NavigatingArgCS::ownedNameExpression| - 1)
			= new SerializationMatchTermSubtract(_060, _001);
		private final @NonNull SerializationMatchTerm _149 // (|NavigatingArgCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_061, _001);
		private final @NonNull SerializationMatchTerm _150 // (|NavigatingArgCS::prefix.','| - 1)
			= new SerializationMatchTermSubtract(_062, _001);
		private final @NonNull SerializationMatchTerm _151 // (|NavigatingArgCS::prefix.';'| - 1)
			= new SerializationMatchTermSubtract(_063, _001);
		private final @NonNull SerializationMatchTerm _152 // (|NavigatingArgCS::prefix.'|'| - 1)
			= new SerializationMatchTermSubtract(_064, _001);
		private final @NonNull SerializationMatchTerm _153 // (|NestedExpCS::ownedExpression| - 1)
			= new SerializationMatchTermSubtract(_065, _001);
		private final @NonNull SerializationMatchTerm _154 // (|NumberLiteralExpCS::symbol| - 1)
			= new SerializationMatchTermSubtract(_066, _001);
		private final @NonNull SerializationMatchTerm _155 // (|OperationContextDeclCS::ownedParameters| - 1)
			= new SerializationMatchTermSubtract(_068, _001);
		private final @NonNull SerializationMatchTerm _156 // (|OperationContextDeclCS::ownedParameters| > 0)
			= new SerializationMatchTermGreaterThan(_068, _000);
		private final @NonNull SerializationMatchTerm _157 // (|OperatorExpCS::ownedRight| - 1)
			= new SerializationMatchTermSubtract(_071, _001);
		private final @NonNull SerializationMatchTerm _158 // (|PathElementCS::referredElement| - 1)
			= new SerializationMatchTermSubtract(_074, _001);
		private final @NonNull SerializationMatchTerm _159 // (|PathNameCS::ownedPathElements| - 1)
			= new SerializationMatchTermSubtract(_075, _001);
		private final @NonNull SerializationMatchTerm _160 // (|PathNameDeclCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_076, _001);
		private final @NonNull SerializationMatchTerm _161 // (|PatternExpCS::ownedPatternType| - 1)
			= new SerializationMatchTermSubtract(_077, _001);
		private final @NonNull SerializationMatchTerm _162 // (|PrimitiveTypeRefCS::name| - 1)
			= new SerializationMatchTermSubtract(_079, _001);
		private final @NonNull SerializationMatchTerm _163 // (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new SerializationMatchTermSubtract(_082, _001);
		private final @NonNull SerializationMatchTerm _164 // (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new SerializationMatchTermGreaterThan(_082, _000);
		private final @NonNull SerializationMatchTerm _165 // (|ShadowPartCS::ownedInitExpression| - 1)
			= new SerializationMatchTermSubtract(_083, _001);
		private final @NonNull SerializationMatchTerm _166 // (|ShadowPartCS::referredProperty| - 1)
			= new SerializationMatchTermSubtract(_084, _001);
		private final @NonNull SerializationMatchTerm _167 // (|SpecificationCS::exprString| - 1)
			= new SerializationMatchTermSubtract(_085, _001);
		private final @NonNull SerializationMatchTerm _168 // (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new SerializationMatchTermSubtract(_086, _001);
		private final @NonNull SerializationMatchTerm _169 // (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new SerializationMatchTermSubtract(_089, _001);
		private final @NonNull SerializationMatchTerm _170 // (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
			= new SerializationMatchTermSubtract(_090, _001);
		private final @NonNull SerializationMatchTerm _171 // (|TemplateSignatureCS::ownedParameters| - 1)
			= new SerializationMatchTermSubtract(_091, _001);
		private final @NonNull SerializationMatchTerm _172 // (|TupleLiteralExpCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_093, _001);
		private final @NonNull SerializationMatchTerm _173 // (|TupleTypeCS::name.'Tuple'| - 1)
			= new SerializationMatchTermSubtract(_094, _001);
		private final @NonNull SerializationMatchTerm _174 // (|TupleTypeCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_095, _001);
		private final @NonNull SerializationMatchTerm _175 // (|TupleTypeCS::ownedParts| > 0)
			= new SerializationMatchTermGreaterThan(_095, _000);
		private final @NonNull SerializationMatchTerm _176 // (|TypeLiteralExpCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_096, _001);
		private final @NonNull SerializationMatchTerm _177 // (|TypeNameExpCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_098, _001);
		private final @NonNull SerializationMatchTerm _178 // (|TypeParameterCS::ownedExtends| - 1)
			= new SerializationMatchTermSubtract(_100, _001);
		private final @NonNull SerializationMatchTerm _179 // (|TypeParameterCS::ownedExtends| > 0)
			= new SerializationMatchTermGreaterThan(_100, _000);
		private final @NonNull SerializationMatchTerm _180 // (|TypedElementCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_101, _001);
		private final @NonNull SerializationMatchTerm _181 // (|TypedTypeRefCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_104, _001);
		private final @NonNull SerializationMatchTerm _182 // (|VariableCS::ownedInitExpression| - 1)
			= new SerializationMatchTermSubtract(_105, _001);
	}

	/**
	 * Steps for the matching process.
	 */
	private class _MatchSteps
	{
		private final @NonNull SerializationMatchStep _000 // assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._108);
		private final @NonNull SerializationMatchStep _001 // assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
			= new MatchStep_Assert(mt._109);
		private final @NonNull SerializationMatchStep _002 // assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._112);
		private final @NonNull SerializationMatchStep _003 // assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
			= new MatchStep_Assert(mt._113);
		private final @NonNull SerializationMatchStep _004 // assert (|CollectionPatternCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._115);
		private final @NonNull SerializationMatchStep _005 // assert (|CollectionTypeCS::name| - 1) == 0
			= new MatchStep_Assert(mt._116);
		private final @NonNull SerializationMatchStep _006 // assert (|ConstraintCS::ownedSpecification| - 1) == 0
			= new MatchStep_Assert(mt._117);
		private final @NonNull SerializationMatchStep _007 // assert (|ContextCS::ownedExpression| - 1) == 0
			= new MatchStep_Assert(mt._118);
		private final @NonNull SerializationMatchStep _008 // assert (|DefCS::ownedSpecification| - 1) == 0
			= new MatchStep_Assert(mt._121);
		private final @NonNull SerializationMatchStep _009 // assert (|ExpSpecificationCS::ownedExpression| - 1) == 0
			= new MatchStep_Assert(mt._124);
		private final @NonNull SerializationMatchStep _010 // assert (|FeatureContextDeclCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._125);
		private final @NonNull SerializationMatchStep _011 // assert (|IfExpCS::ownedCondition| - 1) == 0
			= new MatchStep_Assert(mt._126);
		private final @NonNull SerializationMatchStep _012 // assert (|IfExpCS::ownedElseExpression| - 1) == 0
			= new MatchStep_Assert(mt._127);
		private final @NonNull SerializationMatchStep _013 // assert (|IfExpCS::ownedThenExpression| - 1) == 0
			= new MatchStep_Assert(mt._128);
		private final @NonNull SerializationMatchStep _014 // assert (|IfThenExpCS::ownedCondition| - 1) == 0
			= new MatchStep_Assert(mt._129);
		private final @NonNull SerializationMatchStep _015 // assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
			= new MatchStep_Assert(mt._130);
		private final @NonNull SerializationMatchStep _016 // assert (|ImportCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._131);
		private final @NonNull SerializationMatchStep _017 // assert (|InfixExpCS::ownedLeft| - 1) == 0
			= new MatchStep_Assert(mt._132);
		private final @NonNull SerializationMatchStep _018 // assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
			= new MatchStep_Assert(mt._133);
		private final @NonNull SerializationMatchStep _019 // assert (|LetExpCS::ownedInExpression| - 1) == 0
			= new MatchStep_Assert(mt._134);
		private final @NonNull SerializationMatchStep _020 // assert (|MapLiteralExpCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._138);
		private final @NonNull SerializationMatchStep _021 // assert (|MapLiteralPartCS::ownedKey| - 1) == 0
			= new MatchStep_Assert(mt._139);
		private final @NonNull SerializationMatchStep _022 // assert (|MapLiteralPartCS::ownedValue| - 1) == 0
			= new MatchStep_Assert(mt._140);
		private final @NonNull SerializationMatchStep _023 // assert (|MapTypeCS::name.'Map'| - 1) == 0
			= new MatchStep_Assert(mt._141);
		private final @NonNull SerializationMatchStep _024 // assert (|MapTypeCS::ownedKeyType| - V0) == 0
			= new MatchStep_Assert(mt._142);
		private final @NonNull SerializationMatchStep _025 // assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
			= new MatchStep_Assert(mt._143);
		private final @NonNull SerializationMatchStep _026 // assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
			= new MatchStep_Assert(mt._144);
		private final @NonNull SerializationMatchStep _027 // assert (|NamedElementCS::name| - 1) == 0
			= new MatchStep_Assert(mt._145);
		private final @NonNull SerializationMatchStep _028 // assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
			= new MatchStep_Assert(mt._146);
		private final @NonNull SerializationMatchStep _029 // assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
			= new MatchStep_Assert(mt._147);
		private final @NonNull SerializationMatchStep _030 // assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
			= new MatchStep_Assert(mt._148);
		private final @NonNull SerializationMatchStep _031 // assert (|NavigatingArgCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._149);
		private final @NonNull SerializationMatchStep _032 // assert (|NavigatingArgCS::prefix.','| - 1) == 0
			= new MatchStep_Assert(mt._150);
		private final @NonNull SerializationMatchStep _033 // assert (|NavigatingArgCS::prefix.';'| - 1) == 0
			= new MatchStep_Assert(mt._151);
		private final @NonNull SerializationMatchStep _034 // assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
			= new MatchStep_Assert(mt._152);
		private final @NonNull SerializationMatchStep _035 // assert (|NestedExpCS::ownedExpression| - 1) == 0
			= new MatchStep_Assert(mt._153);
		private final @NonNull SerializationMatchStep _036 // assert (|NumberLiteralExpCS::symbol| - 1) == 0
			= new MatchStep_Assert(mt._154);
		private final @NonNull SerializationMatchStep _037 // assert (|OperatorExpCS::ownedRight| - 1) == 0
			= new MatchStep_Assert(mt._157);
		private final @NonNull SerializationMatchStep _038 // assert (|PathElementCS::referredElement| - 1) == 0
			= new MatchStep_Assert(mt._158);
		private final @NonNull SerializationMatchStep _039 // assert (|PathNameCS::ownedPathElements| - 1) == 0
			= new MatchStep_Assert(mt._159);
		private final @NonNull SerializationMatchStep _040 // assert (|PathNameDeclCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._160);
		private final @NonNull SerializationMatchStep _041 // assert (|PatternExpCS::ownedPatternType| - 1) == 0
			= new MatchStep_Assert(mt._161);
		private final @NonNull SerializationMatchStep _042 // assert (|PrimitiveTypeRefCS::name| - 1) == 0
			= new MatchStep_Assert(mt._162);
		private final @NonNull SerializationMatchStep _043 // assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
			= new MatchStep_Assert(mt._165);
		private final @NonNull SerializationMatchStep _044 // assert (|ShadowPartCS::referredProperty| - 1) == 0
			= new MatchStep_Assert(mt._166);
		private final @NonNull SerializationMatchStep _045 // assert (|SpecificationCS::exprString| - 1) == 0
			= new MatchStep_Assert(mt._167);
		private final @NonNull SerializationMatchStep _046 // assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
			= new MatchStep_Assert(mt._170);
		private final @NonNull SerializationMatchStep _047 // assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
			= new MatchStep_Assert(mt._173);
		private final @NonNull SerializationMatchStep _048 // assert (|TypeLiteralExpCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._176);
		private final @NonNull SerializationMatchStep _049 // assert (|TypeNameExpCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._177);
		private final @NonNull SerializationMatchStep _050 // assert (|TypedElementCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._180);
		private final @NonNull SerializationMatchStep _051 // assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._181);
		private final @NonNull SerializationMatchStep _052 // assert (|VariableCS::ownedInitExpression| - 1) == 0
			= new MatchStep_Assert(mt._182);
		private final @NonNull SerializationMatchStep _053 // assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
			= new MatchStep_Assign(0, mt._111);
		private final @NonNull SerializationMatchStep _054 // assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new MatchStep_Assign(0, mt._120);
		private final @NonNull SerializationMatchStep _055 // assign V0 = (|LetExpCS::ownedVariables| - 1)
			= new MatchStep_Assign(0, mt._135);
		private final @NonNull SerializationMatchStep _056 // assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
			= new MatchStep_Assign(0, mt._137);
		private final @NonNull SerializationMatchStep _057 // assign V0 = (|PathNameCS::ownedPathElements| - 1)
			= new MatchStep_Assign(0, mt._159);
		private final @NonNull SerializationMatchStep _058 // assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new MatchStep_Assign(0, mt._164);
		private final @NonNull SerializationMatchStep _059 // assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new MatchStep_Assign(0, mt._168);
		private final @NonNull SerializationMatchStep _060 // assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new MatchStep_Assign(0, mt._169);
		private final @NonNull SerializationMatchStep _061 // assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
			= new MatchStep_Assign(0, mt._171);
		private final @NonNull SerializationMatchStep _062 // assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
			= new MatchStep_Assign(0, mt._172);
		private final @NonNull SerializationMatchStep _063 // assign V0 = (|TupleTypeCS::ownedParts| > 0)
			= new MatchStep_Assign(0, mt._175);
		private final @NonNull SerializationMatchStep _064 // assign V0 = (|TypeParameterCS::ownedExtends| > 0)
			= new MatchStep_Assign(0, mt._179);
		private final @NonNull SerializationMatchStep _065 // assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
			= new MatchStep_Assign(0, mt._007);
		private final @NonNull SerializationMatchStep _066 // assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
			= new MatchStep_Assign(0, mt._015);
		private final @NonNull SerializationMatchStep _067 // assign V0 = |CollectionPatternCS::restVariableName|
			= new MatchStep_Assign(0, mt._018);
		private final @NonNull SerializationMatchStep _068 // assign V0 = |CollectionTypeCS::ownedType|
			= new MatchStep_Assign(0, mt._021);
		private final @NonNull SerializationMatchStep _069 // assign V0 = |DefCS::isStatic.'static'|
			= new MatchStep_Assign(0, mt._028);
		private final @NonNull SerializationMatchStep _070 // assign V0 = |IfExpCS::ownedIfThenExpressions|
			= new MatchStep_Assign(0, mt._035);
		private final @NonNull SerializationMatchStep _071 // assign V0 = |LetVariableCS::ownedRoundBracketedClause|
			= new MatchStep_Assign(0, mt._045);
		private final @NonNull SerializationMatchStep _072 // assign V0 = |MapTypeCS::ownedValueType|
			= new MatchStep_Assign(0, mt._052);
		private final @NonNull SerializationMatchStep _073 // assign V0 = |MultiplicityBoundsCS::upperBound|
			= new MatchStep_Assign(0, mt._054);
		private final @NonNull SerializationMatchStep _074 // assign V0 = |MultiplicityCS::isNullFree.'|1'|
			= new MatchStep_Assign(0, mt._055);
		private final @NonNull SerializationMatchStep _075 // assign V0 = |NamedElementCS::name|
			= new MatchStep_Assign(0, mt._057);
		private final @NonNull SerializationMatchStep _076 // assign V0 = |NavigatingArgCS::ownedCoIterator|
			= new MatchStep_Assign(0, mt._058);
		private final @NonNull SerializationMatchStep _077 // assign V0 = |NavigatingArgCS::ownedInitExpression|
			= new MatchStep_Assign(0, mt._059);
		private final @NonNull SerializationMatchStep _078 // assign V0 = |NavigatingArgCS::ownedType|
			= new MatchStep_Assign(0, mt._061);
		private final @NonNull SerializationMatchStep _079 // assign V0 = |PackageDeclarationCS::ownedInvariants|
			= new MatchStep_Assign(0, mt._073);
		private final @NonNull SerializationMatchStep _080 // assign V0 = |PatternExpCS::patternVariableName|
			= new MatchStep_Assign(0, mt._078);
		private final @NonNull SerializationMatchStep _081 // assign V0 = |PropertyContextDeclCS::ownedDefaultExpressions|
			= new MatchStep_Assign(0, mt._080);
		private final @NonNull SerializationMatchStep _082 // assign V0 = |RootCS::ownedImports|
			= new MatchStep_Assign(0, mt._081);
		private final @NonNull SerializationMatchStep _083 // assign V0 = |StringLiteralExpCS::segments|
			= new MatchStep_Assign(0, mt._087);
		private final @NonNull SerializationMatchStep _084 // assign V0 = |TemplateableElementCS::ownedSignature|
			= new MatchStep_Assign(0, mt._092);
		private final @NonNull SerializationMatchStep _085 // assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
			= new MatchStep_Assign(0, mt._097);
		private final @NonNull SerializationMatchStep _086 // assign V0 = |TypedRefCS::ownedMultiplicity|
			= new MatchStep_Assign(0, mt._102);
		private final @NonNull SerializationMatchStep _087 // assign V0 = |TypedTypeRefCS::ownedBinding|
			= new MatchStep_Assign(0, mt._103);
		private final @NonNull SerializationMatchStep _088 // assign V0 = |VariableCS::ownedType|
			= new MatchStep_Assign(0, mt._106);
		private final @NonNull SerializationMatchStep _089 // assign V0 = |WildcardTypeRefCS::ownedExtends|
			= new MatchStep_Assign(0, mt._107);
		private final @NonNull SerializationMatchStep _090 // assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
			= new MatchStep_Assign(1, mt._110);
		private final @NonNull SerializationMatchStep _091 // assign V1 = (|CollectionPatternCS::ownedParts| - 1)
			= new MatchStep_Assign(1, mt._114);
		private final @NonNull SerializationMatchStep _092 // assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new MatchStep_Assign(1, mt._119);
		private final @NonNull SerializationMatchStep _093 // assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
			= new MatchStep_Assign(1, mt._136);
		private final @NonNull SerializationMatchStep _094 // assign V1 = (|OperationContextDeclCS::ownedParameters| > 0)
			= new MatchStep_Assign(1, mt._156);
		private final @NonNull SerializationMatchStep _095 // assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new MatchStep_Assign(1, mt._163);
		private final @NonNull SerializationMatchStep _096 // assign V1 = (|TupleTypeCS::ownedParts| > 0)
			= new MatchStep_Assign(1, mt._175);
		private final @NonNull SerializationMatchStep _097 // assign V1 = (|TypeParameterCS::ownedExtends| - 1)
			= new MatchStep_Assign(1, mt._178);
		private final @NonNull SerializationMatchStep _098 // assign V1 = 0
			= new MatchStep_Assign(1, mt._000);
		private final @NonNull SerializationMatchStep _099 // assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
			= new MatchStep_Assign(1, mt._006);
		private final @NonNull SerializationMatchStep _100 // assign V1 = |ClassifierContextDeclCS::selfName|
			= new MatchStep_Assign(1, mt._011);
		private final @NonNull SerializationMatchStep _101 // assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
			= new MatchStep_Assign(1, mt._020);
		private final @NonNull SerializationMatchStep _102 // assign V1 = |CompleteOCLDocumentCS::ownedPackages|
			= new MatchStep_Assign(1, mt._023);
		private final @NonNull SerializationMatchStep _103 // assign V1 = |ConstraintCS::ownedMessageSpecification|
			= new MatchStep_Assign(1, mt._024);
		private final @NonNull SerializationMatchStep _104 // assign V1 = |ImportCS::isAll.'::*'|
			= new MatchStep_Assign(1, mt._039);
		private final @NonNull SerializationMatchStep _105 // assign V1 = |MultiplicityCS::isNullFree.'|1'|
			= new MatchStep_Assign(1, mt._055);
		private final @NonNull SerializationMatchStep _106 // assign V1 = |NavigatingArgCS::ownedCoIterator|
			= new MatchStep_Assign(1, mt._058);
		private final @NonNull SerializationMatchStep _107 // assign V1 = |NavigatingArgCS::ownedInitExpression|
			= new MatchStep_Assign(1, mt._059);
		private final @NonNull SerializationMatchStep _108 // assign V1 = |PackageDeclarationCS::ownedContexts|
			= new MatchStep_Assign(1, mt._072);
		private final @NonNull SerializationMatchStep _109 // assign V1 = |TemplateBindingCS::ownedMultiplicity|
			= new MatchStep_Assign(1, mt._088);
		private final @NonNull SerializationMatchStep _110 // assign V1 = |TemplateableElementCS::ownedSignature|
			= new MatchStep_Assign(1, mt._092);
		private final @NonNull SerializationMatchStep _111 // assign V1 = |TypeNameExpCS::ownedPatternGuard|
			= new MatchStep_Assign(1, mt._099);
		private final @NonNull SerializationMatchStep _112 // assign V1 = |TypedRefCS::ownedMultiplicity|
			= new MatchStep_Assign(1, mt._102);
		private final @NonNull SerializationMatchStep _113 // assign V1 = |VariableCS::ownedType|
			= new MatchStep_Assign(1, mt._106);
		private final @NonNull SerializationMatchStep _114 // assign V2 = (|DefOperationCS::ownedParameters| > 0)
			= new MatchStep_Assign(2, mt._123);
		private final @NonNull SerializationMatchStep _115 // assign V2 = (|OperationContextDeclCS::ownedParameters| - 1)
			= new MatchStep_Assign(2, mt._155);
		private final @NonNull SerializationMatchStep _116 // assign V2 = (|TupleTypeCS::ownedParts| - 1)
			= new MatchStep_Assign(2, mt._174);
		private final @NonNull SerializationMatchStep _117 // assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
			= new MatchStep_Assign(2, mt._004);
		private final @NonNull SerializationMatchStep _118 // assign V2 = |ClassifierContextDeclCS::ownedInvariants|
			= new MatchStep_Assign(2, mt._010);
		private final @NonNull SerializationMatchStep _119 // assign V2 = |CompleteOCLDocumentCS::ownedContexts|
			= new MatchStep_Assign(2, mt._022);
		private final @NonNull SerializationMatchStep _120 // assign V2 = |TypedRefCS::ownedMultiplicity|
			= new MatchStep_Assign(2, mt._102);
		private final @NonNull SerializationMatchStep _121 // assign V3 = (|DefOperationCS::ownedParameters| - 1)
			= new MatchStep_Assign(3, mt._122);
		private final @NonNull SerializationMatchStep _122 // assign V3 = |AbstractNameExpCS::isPre.'@'|
			= new MatchStep_Assign(3, mt._003);
		private final @NonNull SerializationMatchStep _123 // assign V3 = |ClassifierContextDeclCS::ownedDefinitions|
			= new MatchStep_Assign(3, mt._009);
		private final @NonNull SerializationMatchStep _124 // assign V3 = |FeatureContextDeclCS::ownedType|
			= new MatchStep_Assign(3, mt._032);
		private final @NonNull SerializationMatchStep _125 // assign V3 = |TypedRefCS::ownedMultiplicity|
			= new MatchStep_Assign(3, mt._102);
		private final @NonNull SerializationMatchStep _126 // assign V4 = |OperationContextDeclCS::ownedPreconditions|
			= new MatchStep_Assign(4, mt._070);
		private final @NonNull SerializationMatchStep _127 // assign V4 = |TypedElementCS::ownedType|
			= new MatchStep_Assign(4, mt._101);
		private final @NonNull SerializationMatchStep _128 // assign V5 = |OperationContextDeclCS::ownedPostconditions|
			= new MatchStep_Assign(5, mt._069);
		private final @NonNull SerializationMatchStep _129 // assign V6 = |OperationContextDeclCS::ownedBodies|
			= new MatchStep_Assign(6, mt._067);
		private final @NonNull SerializationMatchStep _130 // check-rule basecs::ConstraintCS.ownedMessageSpecification : 83
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, iv._32/*SpecificationCS*/);
		private final @NonNull SerializationMatchStep _131 // check-rule basecs::ConstraintCS.ownedSpecification : 83
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, iv._32/*SpecificationCS*/);
		private final @NonNull SerializationMatchStep _132 // check-rule basecs::ImportCS.ownedPathName : 108
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, iv._53/*URIPathNameCS*/);
		private final @NonNull SerializationMatchStep _133 // check-rule basecs::PathNameCS.ownedPathElements : 30
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._11/*FirstPathElementCS*/);
		private final @NonNull SerializationMatchStep _134 // check-rule basecs::PathNameCS.ownedPathElements : 30|61
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._20/*FirstPathElementCS|NextPathElementCS*/);
		private final @NonNull SerializationMatchStep _135 // check-rule basecs::PathNameCS.ownedPathElements : 61|107
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._51/*NextPathElementCS|URIFirstPathElementCS*/);
		private final @NonNull SerializationMatchStep _136 // check-rule basecs::RootCS.ownedImports : 35
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, iv._12/*ImportCS*/);
		private final @NonNull SerializationMatchStep _137 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : 50
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, iv._16/*MultiplicityCS*/);
		private final @NonNull SerializationMatchStep _138 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : 88
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, iv._36/*TemplateParameterSubstitutionCS*/);
		private final @NonNull SerializationMatchStep _139 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 101
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, iv._48/*TypeRefCS*/);
		private final @NonNull SerializationMatchStep _140 // check-rule basecs::TemplateSignatureCS.ownedParameters : 100
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, iv._47/*TypeParameterCS*/);
		private final @NonNull SerializationMatchStep _141 // check-rule basecs::TemplateableElementCS.ownedSignature : 89
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, iv._37/*TemplateSignatureCS*/);
		private final @NonNull SerializationMatchStep _142 // check-rule basecs::TupleTypeCS.ownedParts : 92
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, iv._39/*TuplePartCS*/);
		private final @NonNull SerializationMatchStep _143 // check-rule basecs::TypeParameterCS.ownedExtends : 102
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, iv._49/*TypedRefCS*/);
		private final @NonNull SerializationMatchStep _144 // check-rule basecs::TypedElementCS.ownedType : 94
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, iv._40/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _145 // check-rule basecs::TypedRefCS.ownedMultiplicity : 50
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, iv._16/*MultiplicityCS*/);
		private final @NonNull SerializationMatchStep _146 // check-rule basecs::TypedTypeRefCS.ownedBinding : 87
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, iv._35/*TemplateBindingCS*/);
		private final @NonNull SerializationMatchStep _147 // check-rule basecs::TypedTypeRefCS.ownedPathName : 67
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, iv._23/*PathNameCS*/);
		private final @NonNull SerializationMatchStep _148 // check-rule basecs::WildcardTypeRefCS.ownedExtends : 102
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, iv._49/*TypedRefCS*/);
		private final @NonNull SerializationMatchStep _149 // check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : 16
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, iv._6/*DefCS*/);
		private final @NonNull SerializationMatchStep _150 // check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : 12
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS, iv._3/*ConstraintCS*/);
		private final @NonNull SerializationMatchStep _151 // check-rule completeoclcs::CompleteOCLDocumentCS.ownedContexts : 13
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS, iv._4/*ContextDeclCS*/);
		private final @NonNull SerializationMatchStep _152 // check-rule completeoclcs::CompleteOCLDocumentCS.ownedPackages : 65
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES, iv._21/*PackageDeclarationCS*/);
		private final @NonNull SerializationMatchStep _153 // check-rule completeoclcs::DefCS.ownedSpecification : 83
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION, iv._32/*SpecificationCS*/);
		private final @NonNull SerializationMatchStep _154 // check-rule completeoclcs::DefOperationCS.ownedParameters : 18
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS, iv._7/*DefParameterCS*/);
		private final @NonNull SerializationMatchStep _155 // check-rule completeoclcs::FeatureContextDeclCS.ownedType : 94
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, iv._40/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _156 // check-rule completeoclcs::OperationContextDeclCS.ownedBodies : 83
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES, iv._32/*SpecificationCS*/);
		private final @NonNull SerializationMatchStep _157 // check-rule completeoclcs::OperationContextDeclCS.ownedParameters : 66
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS, iv._22/*ParameterCS*/);
		private final @NonNull SerializationMatchStep _158 // check-rule completeoclcs::OperationContextDeclCS.ownedPostconditions : 12
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS, iv._3/*ConstraintCS*/);
		private final @NonNull SerializationMatchStep _159 // check-rule completeoclcs::OperationContextDeclCS.ownedPreconditions : 12
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS, iv._3/*ConstraintCS*/);
		private final @NonNull SerializationMatchStep _160 // check-rule completeoclcs::PackageDeclarationCS.ownedContexts : 13
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS, iv._4/*ContextDeclCS*/);
		private final @NonNull SerializationMatchStep _161 // check-rule completeoclcs::PackageDeclarationCS.ownedInvariants : 12
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS, iv._3/*ConstraintCS*/);
		private final @NonNull SerializationMatchStep _162 // check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, iv._23/*PathNameCS*/);
		private final @NonNull SerializationMatchStep _163 // check-rule completeoclcs::PropertyContextDeclCS.ownedDefaultExpressions : 83
			= new MatchStep_RuleCheck(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS, iv._32/*SpecificationCS*/);
		private final @NonNull SerializationMatchStep _164 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._5/*CurlyBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _165 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 67
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, iv._23/*PathNameCS*/);
		private final @NonNull SerializationMatchStep _166 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 76
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._30/*RoundBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _167 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 84
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, iv._33/*SquareBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _168 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 6
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, iv._1/*CollectionLiteralPartCS*/);
		private final @NonNull SerializationMatchStep _169 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 8
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, iv._2/*CollectionTypeCS*/);
		private final @NonNull SerializationMatchStep _170 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _171 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 68
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._24/*PatternExpCS*/);
		private final @NonNull SerializationMatchStep _172 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _173 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : 68
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, iv._24/*PatternExpCS*/);
		private final @NonNull SerializationMatchStep _174 // check-rule essentialoclcs::CollectionPatternCS.ownedType : 8
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, iv._2/*CollectionTypeCS*/);
		private final @NonNull SerializationMatchStep _175 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 50
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, iv._16/*MultiplicityCS*/);
		private final @NonNull SerializationMatchStep _176 // check-rule essentialoclcs::CollectionTypeCS.ownedType : 95
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, iv._41/*TypeExpWithoutMultiplicityCS*/);
		private final @NonNull SerializationMatchStep _177 // check-rule essentialoclcs::ContextCS.ownedExpression : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _178 // check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 81
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, iv._31/*ShadowPartCS*/);
		private final @NonNull SerializationMatchStep _179 // check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _180 // check-rule essentialoclcs::IfExpCS.ownedCondition : 29|68
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, iv._25/*ExpCS|PatternExpCS*/);
		private final @NonNull SerializationMatchStep _181 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _182 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 22
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, iv._9/*ElseIfThenExpCS*/);
		private final @NonNull SerializationMatchStep _183 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _184 // check-rule essentialoclcs::IfThenExpCS.ownedCondition : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _185 // check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _186 // check-rule essentialoclcs::InfixExpCS.ownedLeft : 70
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, iv._28/*PrefixedPrimaryExpCS*/);
		private final @NonNull SerializationMatchStep _187 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _188 // check-rule essentialoclcs::LetExpCS.ownedInExpression : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _189 // check-rule essentialoclcs::LetExpCS.ownedVariables : 42
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, iv._13/*LetVariableCS*/);
		private final @NonNull SerializationMatchStep _190 // check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 76
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._30/*RoundBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _191 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 46
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, iv._14/*MapLiteralPartCS*/);
		private final @NonNull SerializationMatchStep _192 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : 47
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, iv._15/*MapTypeCS*/);
		private final @NonNull SerializationMatchStep _193 // check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _194 // check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _195 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : 94
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, iv._40/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _196 // check-rule essentialoclcs::MapTypeCS.ownedValueType : 94
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, iv._40/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _197 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, iv._0/*CoIteratorVariableCS*/);
		private final @NonNull SerializationMatchStep _198 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _199 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, iv._17/*NavigatingArgExpCS*/);
		private final @NonNull SerializationMatchStep _200 // check-rule essentialoclcs::NavigatingArgCS.ownedType : 94
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, iv._40/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _201 // check-rule essentialoclcs::NestedExpCS.ownedExpression : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _202 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _203 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 69
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._26/*PrefixedLetExpCS*/);
		private final @NonNull SerializationMatchStep _204 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 70
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._28/*PrefixedPrimaryExpCS*/);
		private final @NonNull SerializationMatchStep _205 // check-rule essentialoclcs::PatternExpCS.ownedPatternType : 94
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, iv._40/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _206 // check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 54|56|57|58
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, iv._19/*NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS*/);
		private final @NonNull SerializationMatchStep _207 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 29|68
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._25/*ExpCS|PatternExpCS*/);
		private final @NonNull SerializationMatchStep _208 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 86
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._34/*StringLiteralExpCS*/);
		private final @NonNull SerializationMatchStep _209 // check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _210 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 91
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, iv._38/*TupleLiteralPartCS*/);
		private final @NonNull SerializationMatchStep _211 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 98
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, iv._43/*TypeLiteralWithMultiplicityCS*/);
		private final @NonNull SerializationMatchStep _212 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._5/*CurlyBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _213 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 67
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, iv._23/*PathNameCS*/);
		private final @NonNull SerializationMatchStep _214 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _215 // check-rule essentialoclcs::VariableCS.ownedInitExpression : 29
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull SerializationMatchStep _216 // check-rule essentialoclcs::VariableCS.ownedType : 94
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, iv._40/*TypeExpCS*/);
	}

	/**
	 * The various serialization term used to serialize a serialization rule.
	 */
	private class _SerializationTerms
	{
		private final @NonNull SerializationStepLiteral _000 // 1*'&&'
									= new SerializationStepLiteral(-1, "&&");
		private final @NonNull SerializationStepLiteral _001 // 1*'('
									= new SerializationStepLiteral(-1, "(");
		private final @NonNull SerializationStepLiteral _002 // 1*')'
									= new SerializationStepLiteral(-1, ")");
		private final @NonNull SerializationStepLiteral _003 // 1*'*'
									= new SerializationStepLiteral(-1, "*");
		private final @NonNull SerializationStepLiteral _004 // 1*'++'
									= new SerializationStepLiteral(-1, "++");
		private final @NonNull SerializationStepLiteral _005 // 1*','
									= new SerializationStepLiteral(-1, ",");
		private final @NonNull SerializationStepLiteral _006 // 1*'..'
									= new SerializationStepLiteral(-1, "..");
		private final @NonNull SerializationStepLiteral _007 // 1*':'
									= new SerializationStepLiteral(-1, ":");
		private final @NonNull SerializationStepLiteral _008 // 1*'::'
									= new SerializationStepLiteral(-1, "::");
		private final @NonNull SerializationStepLiteral _009 // 1*';'
									= new SerializationStepLiteral(-1, ";");
		private final @NonNull SerializationStepLiteral _010 // 1*'<'
									= new SerializationStepLiteral(-1, "<");
		private final @NonNull SerializationStepLiteral _011 // 1*'<-'
									= new SerializationStepLiteral(-1, "<-");
		private final @NonNull SerializationStepLiteral _012 // 1*'='
									= new SerializationStepLiteral(-1, "=");
		private final @NonNull SerializationStepLiteral _013 // 1*'>'
									= new SerializationStepLiteral(-1, ">");
		private final @NonNull SerializationStepLiteral _014 // 1*'?'
									= new SerializationStepLiteral(-1, "?");
		private final @NonNull SerializationStepLiteral _015 // 1*'@'
									= new SerializationStepLiteral(-1, "@");
		private final @NonNull SerializationStepLiteral _016 // 1*'Lambda'
									= new SerializationStepLiteral(-1, "Lambda");
		private final @NonNull SerializationStepLiteral _017 // 1*'Map'
									= new SerializationStepLiteral(-1, "Map");
		private final @NonNull SerializationStepLiteral _018 // 1*'Tuple'
									= new SerializationStepLiteral(-1, "Tuple");
		private final @NonNull SerializationStepLiteral _019 // 1*'['
									= new SerializationStepLiteral(-1, "[");
		private final @NonNull SerializationStepLiteral _020 // 1*']'
									= new SerializationStepLiteral(-1, "]");
		private final @NonNull SerializationStepLiteral _021 // 1*'body'
									= new SerializationStepLiteral(-1, "body");
		private final @NonNull SerializationStepLiteral _022 // 1*'context'
									= new SerializationStepLiteral(-1, "context");
		private final @NonNull SerializationStepLiteral _023 // 1*'def'
									= new SerializationStepLiteral(-1, "def");
		private final @NonNull SerializationStepLiteral _024 // 1*'derive'
									= new SerializationStepLiteral(-1, "derive");
		private final @NonNull SerializationStepLiteral _025 // 1*'else'
									= new SerializationStepLiteral(-1, "else");
		private final @NonNull SerializationStepLiteral _026 // 1*'elseif'
									= new SerializationStepLiteral(-1, "elseif");
		private final @NonNull SerializationStepLiteral _027 // 1*'endif'
									= new SerializationStepLiteral(-1, "endif");
		private final @NonNull SerializationStepLiteral _028 // 1*'endpackage'
									= new SerializationStepLiteral(-1, "endpackage");
		private final @NonNull SerializationStepLiteral _029 // 1*'extends'
									= new SerializationStepLiteral(-1, "extends");
		private final @NonNull SerializationStepLiteral _030 // 1*'if'
									= new SerializationStepLiteral(-1, "if");
		private final @NonNull SerializationStepLiteral _031 // 1*'import'
									= new SerializationStepLiteral(-1, "import");
		private final @NonNull SerializationStepLiteral _032 // 1*'in'
									= new SerializationStepLiteral(-1, "in");
		private final @NonNull SerializationStepLiteral _033 // 1*'init'
									= new SerializationStepLiteral(-1, "init");
		private final @NonNull SerializationStepLiteral _034 // 1*'inv'
									= new SerializationStepLiteral(-1, "inv");
		private final @NonNull SerializationStepLiteral _035 // 1*'invalid'
									= new SerializationStepLiteral(-1, "invalid");
		private final @NonNull SerializationStepLiteral _036 // 1*'let'
									= new SerializationStepLiteral(-1, "let");
		private final @NonNull SerializationStepLiteral _037 // 1*'null'
									= new SerializationStepLiteral(-1, "null");
		private final @NonNull SerializationStepLiteral _038 // 1*'package'
									= new SerializationStepLiteral(-1, "package");
		private final @NonNull SerializationStepLiteral _039 // 1*'post'
									= new SerializationStepLiteral(-1, "post");
		private final @NonNull SerializationStepLiteral _040 // 1*'pre'
									= new SerializationStepLiteral(-1, "pre");
		private final @NonNull SerializationStepLiteral _041 // 1*'self'
									= new SerializationStepLiteral(-1, "self");
		private final @NonNull SerializationStepLiteral _042 // 1*'then'
									= new SerializationStepLiteral(-1, "then");
		private final @NonNull SerializationStepLiteral _043 // 1*'{'
									= new SerializationStepLiteral(-1, "{");
		private final @NonNull SerializationStepLiteral _044 // 1*'|'
									= new SerializationStepLiteral(-1, "|");
		private final @NonNull SerializationStepLiteral _045 // 1*'|?'
									= new SerializationStepLiteral(-1, "|?");
		private final @NonNull SerializationStepLiteral _046 // 1*'}'
									= new SerializationStepLiteral(-1, "}");
		private final @NonNull SerializationStepAssignedRuleCall _047 // 1*AbstractNameExpCS::ownedPathName=67
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 67 /* PathNameCS */);
		private final @NonNull SerializationStepAssignKeyword _048 // 1*BooleanLiteralExpCS::symbol='false|true'
									= new SerializationStepAssignKeyword(-1, EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._07);
		private final @NonNull SerializationStepAssignedRuleCall _049 // 1*ClassifierContextDeclCS::ownedInvariants+=12
									= new SerializationStepAssignedRuleCall(-1, CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS, 12 /* ConstraintCS */);
		private final @NonNull SerializationStepAssignedRuleCall _050 // 1*CollectionLiteralExpCS::ownedParts+=6
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 6 /* CollectionLiteralPartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _051 // 1*CollectionLiteralExpCS::ownedType=8
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 8 /* CollectionTypeCS */);
		private final @NonNull SerializationStepAssignedRuleCall _052 // 1*CollectionLiteralPartCS::ownedExpression=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _053 // 1*CollectionLiteralPartCS::ownedExpression=68
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 68 /* PatternExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _054 // 1*CollectionLiteralPartCS::ownedLastExpression=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _055 // 1*CollectionPatternCS::ownedParts+=68
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 68 /* PatternExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _056 // 1*CollectionPatternCS::ownedType=8
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 8 /* CollectionTypeCS */);
		private final @NonNull SerializationStepAssignedRuleCall _057 // 1*CollectionPatternCS::restVariableName=33
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 33 /* Identifier */);
		private final @NonNull SerializationStepAssignedRuleCall _058 // 1*CollectionTypeCS::name=9
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 9 /* CollectionTypeIdentifier */);
		private final @NonNull SerializationStepAssignedRuleCall _059 // 1*CollectionTypeCS::ownedType=95
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 95 /* TypeExpWithoutMultiplicityCS */);
		private final @NonNull SerializationStepAssignedRuleCall _060 // 1*ConstraintCS::ownedMessageSpecification=83
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 83 /* SpecificationCS */);
		private final @NonNull SerializationStepAssignedRuleCall _061 // 1*ConstraintCS::ownedSpecification=83
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 83 /* SpecificationCS */);
		private final @NonNull SerializationStepAssignedRuleCall _062 // 1*ContextCS::ownedExpression=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _063 // 1*CurlyBracketedClauseCS::ownedParts+=81
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 81 /* ShadowPartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _064 // 1*DefCS::ownedSpecification=83
									= new SerializationStepAssignedRuleCall(-1, CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION, 83 /* SpecificationCS */);
		private final @NonNull SerializationStepAssignedRuleCall _065 // 1*DefOperationCS::ownedParameters+=18
									= new SerializationStepAssignedRuleCall(-1, CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS, 18 /* DefParameterCS */);
		private final @NonNull SerializationStepAssignedRuleCall _066 // 1*ExpSpecificationCS::ownedExpression=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _067 // 1*FeatureContextDeclCS::ownedType=94
									= new SerializationStepAssignedRuleCall(-1, CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, 94 /* TypeExpCS */);
		private final @NonNull SerializationStepAssigns _068 // 1*IfExpCS::ownedCondition=29|68
									= new SerializationStepAssigns(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, null, new @NonNull Integer [] { 29/*ExpCS*/,68/*PatternExpCS*/});
		private final @NonNull SerializationStepAssignedRuleCall _069 // 1*IfExpCS::ownedElseExpression=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _070 // 1*IfExpCS::ownedThenExpression=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _071 // 1*IfThenExpCS::ownedCondition=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _072 // 1*IfThenExpCS::ownedThenExpression=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _073 // 1*ImportCS::ownedPathName=108
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 108 /* URIPathNameCS */);
		private final @NonNull SerializationStepAssignedRuleCall _074 // 1*InfixExpCS::ownedLeft=70
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 70 /* PrefixedPrimaryExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _075 // 1*LambdaLiteralExpCS::ownedExpressionCS=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _076 // 1*LetExpCS::ownedInExpression=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _077 // 1*LetExpCS::ownedVariables+=42
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 42 /* LetVariableCS */);
		private final @NonNull SerializationStepAssignedRuleCall _078 // 1*MapLiteralExpCS::ownedParts+=46
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 46 /* MapLiteralPartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _079 // 1*MapLiteralExpCS::ownedType=47
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 47 /* MapTypeCS */);
		private final @NonNull SerializationStepAssignedRuleCall _080 // 1*MapLiteralPartCS::ownedKey=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _081 // 1*MapLiteralPartCS::ownedValue=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _082 // 1*MapTypeCS::ownedKeyType=94
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 94 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _083 // 1*MapTypeCS::ownedValueType=94
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 94 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _084 // 1*MultiplicityBoundsCS::lowerBound=39
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 39 /* LOWER */);
		private final @NonNull SerializationStepAssignedRuleCall _085 // 1*MultiplicityBoundsCS::upperBound=105
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 105 /* UPPER */);
		private final @NonNull SerializationStepAssignKeyword _086 // 1*MultiplicityStringCS::stringBounds='*|+|?'
									= new SerializationStepAssignKeyword(-1, BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._00);
		private final @NonNull SerializationStepAssignedRuleCall _087 // 1*NamedElementCS::name=1
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 1 /* BinaryOperatorName */);
		private final @NonNull SerializationStepAssignedRuleCall _088 // 1*NamedElementCS::name=109
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 109 /* UnaryOperatorName */);
		private final @NonNull SerializationStepAssignedRuleCall _089 // 1*NamedElementCS::name=112
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 112 /* UnrestrictedName */);
		private final @NonNull SerializationStepAssignedRuleCall _090 // 1*NamedElementCS::name=33
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 33 /* Identifier */);
		private final @NonNull SerializationStepAssignedRuleCall _091 // 1*NavigatingArgCS::ownedCoIterator=4
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 4 /* CoIteratorVariableCS */);
		private final @NonNull SerializationStepAssignedRuleCall _092 // 1*NavigatingArgCS::ownedInitExpression=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _093 // 1*NavigatingArgCS::ownedNameExpression=55
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 55 /* NavigatingArgExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _094 // 1*NavigatingArgCS::ownedType=94
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 94 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _095 // 1*NestedExpCS::ownedExpression=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _096 // 1*NumberLiteralExpCS::symbol=52
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 52 /* NUMBER_LITERAL */);
		private final @NonNull SerializationStepAssignedRuleCall _097 // 1*OperationContextDeclCS::ownedBodies+=83
									= new SerializationStepAssignedRuleCall(-1, CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES, 83 /* SpecificationCS */);
		private final @NonNull SerializationStepAssignedRuleCall _098 // 1*OperationContextDeclCS::ownedParameters+=66
									= new SerializationStepAssignedRuleCall(-1, CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS, 66 /* ParameterCS */);
		private final @NonNull SerializationStepAssignedRuleCall _099 // 1*OperationContextDeclCS::ownedPostconditions+=12
									= new SerializationStepAssignedRuleCall(-1, CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS, 12 /* ConstraintCS */);
		private final @NonNull SerializationStepAssignedRuleCall _100 // 1*OperationContextDeclCS::ownedPreconditions+=12
									= new SerializationStepAssignedRuleCall(-1, CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS, 12 /* ConstraintCS */);
		private final @NonNull SerializationStepAssignedRuleCall _101 // 1*OperatorExpCS::ownedRight=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _102 // 1*OperatorExpCS::ownedRight=69
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 69 /* PrefixedLetExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _103 // 1*OperatorExpCS::ownedRight=70
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 70 /* PrefixedPrimaryExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _104 // 1*PackageDeclarationCS::ownedInvariants+=12
									= new SerializationStepAssignedRuleCall(-1, CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS, 12 /* ConstraintCS */);
		private final @NonNull SerializationStepCrossReference _105 // 1*PathElementCS::referredElement=URI
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"));
		private final @NonNull SerializationStepCrossReference _106 // 1*PathElementCS::referredElement=UnreservedName
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"));
		private final @NonNull SerializationStepCrossReference _107 // 1*PathElementCS::referredElement=UnrestrictedName
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull SerializationStepCrossReference _108 // 1*PathElementCS::referredElement=UnrestrictedName
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull SerializationStepAssignedRuleCall _109 // 1*PathNameCS::ownedPathElements+=107
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 107 /* URIFirstPathElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _110 // 1*PathNameCS::ownedPathElements+=30
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 30 /* FirstPathElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _111 // 1*PathNameCS::ownedPathElements+=61
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 61 /* NextPathElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _112 // 1*PathNameDeclCS::ownedPathName=67
									= new SerializationStepAssignedRuleCall(-1, CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, 67 /* PathNameCS */);
		private final @NonNull SerializationStepAssignedRuleCall _113 // 1*PatternExpCS::ownedPatternType=94
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 94 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _114 // 1*PrimitiveTypeRefCS::name=74
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 74 /* PrimitiveTypeIdentifier */);
		private final @NonNull SerializationStepAssignedRuleCall _115 // 1*PropertyContextDeclCS::ownedDefaultExpressions+=83
									= new SerializationStepAssignedRuleCall(-1, CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS, 83 /* SpecificationCS */);
		private final @NonNull SerializationStepAssignedRuleCall _116 // 1*RoundBracketedClauseCS::ownedArguments+=54
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 54 /* NavigatingArgCS */);
		private final @NonNull SerializationStepAssigns _117 // 1*ShadowPartCS::ownedInitExpression=29|68
									= new SerializationStepAssigns(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, null, new @NonNull Integer [] { 29/*ExpCS*/,68/*PatternExpCS*/});
		private final @NonNull SerializationStepAssignedRuleCall _118 // 1*ShadowPartCS::ownedInitExpression=86
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 86 /* StringLiteralExpCS */);
		private final @NonNull SerializationStepCrossReference _119 // 1*ShadowPartCS::referredProperty=UnrestrictedName
									= new SerializationStepCrossReference(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"));
		private final @NonNull SerializationStepAssignedRuleCall _120 // 1*SpecificationCS::exprString=104
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, 104 /* UNQUOTED_STRING */);
		private final @NonNull SerializationStepAssignedRuleCall _121 // 1*SquareBracketedClauseCS::ownedTerms+=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _122 // 1*TemplateBindingCS::ownedSubstitutions+=88
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 88 /* TemplateParameterSubstitutionCS */);
		private final @NonNull SerializationStepAssignedRuleCall _123 // 1*TemplateParameterSubstitutionCS::ownedActualParameter=101
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 101 /* TypeRefCS */);
		private final @NonNull SerializationStepAssignedRuleCall _124 // 1*TemplateSignatureCS::ownedParameters+=100
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 100 /* TypeParameterCS */);
		private final @NonNull SerializationStepAssignedRuleCall _125 // 1*TupleLiteralExpCS::ownedParts+=91
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 91 /* TupleLiteralPartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _126 // 1*TupleTypeCS::ownedParts+=92
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 92 /* TuplePartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _127 // 1*TypeLiteralExpCS::ownedType=98
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 98 /* TypeLiteralWithMultiplicityCS */);
		private final @NonNull SerializationStepAssignedRuleCall _128 // 1*TypeNameExpCS::ownedCurlyBracketedClause=14
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /* CurlyBracketedClauseCS */);
		private final @NonNull SerializationStepAssignedRuleCall _129 // 1*TypeNameExpCS::ownedPathName=67
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 67 /* PathNameCS */);
		private final @NonNull SerializationStepAssignedRuleCall _130 // 1*TypeNameExpCS::ownedPatternGuard=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _131 // 1*TypeParameterCS::ownedExtends+=102
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 102 /* TypedRefCS */);
		private final @NonNull SerializationStepAssignedRuleCall _132 // 1*TypedElementCS::ownedType=94
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 94 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _133 // 1*TypedTypeRefCS::ownedBinding=87
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 87 /* TemplateBindingCS */);
		private final @NonNull SerializationStepAssignedRuleCall _134 // 1*TypedTypeRefCS::ownedPathName=67
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 67 /* PathNameCS */);
		private final @NonNull SerializationStepAssignedRuleCall _135 // 1*VariableCS::ownedInitExpression=29
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 29 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _136 // 1*VariableCS::ownedType=94
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 94 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _137 // 1*WildcardTypeRefCS::ownedExtends=102
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 102 /* TypedRefCS */);
		private final @NonNull SerializationStepSequence _138 // 1*steps-1..10
									= new SerializationStepSequence(-1, 1, 10);
		private final @NonNull SerializationStepSequence _139 // 1*steps-1..11
									= new SerializationStepSequence(-1, 1, 11);
		private final @NonNull SerializationStepSequence _140 // 1*steps-1..12
									= new SerializationStepSequence(-1, 1, 12);
		private final @NonNull SerializationStepSequence _141 // 1*steps-1..13
									= new SerializationStepSequence(-1, 1, 13);
		private final @NonNull SerializationStepSequence _142 // 1*steps-1..17
									= new SerializationStepSequence(-1, 1, 17);
		private final @NonNull SerializationStepSequence _143 // 1*steps-1..23
									= new SerializationStepSequence(-1, 1, 23);
		private final @NonNull SerializationStepSequence _144 // 1*steps-1..3
									= new SerializationStepSequence(-1, 1, 3);
		private final @NonNull SerializationStepSequence _145 // 1*steps-1..4
									= new SerializationStepSequence(-1, 1, 4);
		private final @NonNull SerializationStepSequence _146 // 1*steps-1..5
									= new SerializationStepSequence(-1, 1, 5);
		private final @NonNull SerializationStepSequence _147 // 1*steps-1..6
									= new SerializationStepSequence(-1, 1, 6);
		private final @NonNull SerializationStepSequence _148 // 1*steps-1..7
									= new SerializationStepSequence(-1, 1, 7);
		private final @NonNull SerializationStepSequence _149 // 1*steps-1..8
									= new SerializationStepSequence(-1, 1, 8);
		private final @NonNull SerializationStepSequence _150 // 1*steps-1..9
									= new SerializationStepSequence(-1, 1, 9);
		private final @NonNull SerializationStepLiteral _151 // V00*'static'
									= new SerializationStepLiteral(0, "static");
		private final @NonNull SerializationStepLiteral _152 // V00*'|1'
									= new SerializationStepLiteral(0, "|1");
		private final @NonNull SerializationStepAssignedRuleCall _153 // V00*AbstractNameExpCS::ownedSquareBracketedClauses+=84
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 84 /* SquareBracketedClauseCS */);
		private final @NonNull SerializationStepAssignedRuleCall _154 // V00*IfExpCS::ownedIfThenExpressions+=22
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 22 /* ElseIfThenExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _155 // V00*LetVariableCS::ownedRoundBracketedClause=76
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 76 /* RoundBracketedClauseCS */);
		private final @NonNull SerializationStepAssignedRuleCall _156 // V00*PatternExpCS::patternVariableName=112
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 112 /* UnrestrictedName */);
		private final @NonNull SerializationStepAssignedRuleCall _157 // V00*RootCS::ownedImports+=35
									= new SerializationStepAssignedRuleCall(0, BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 35 /* ImportCS */);
		private final @NonNull SerializationStepAssignedRuleCall _158 // V00*StringLiteralExpCS::segments+=85
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 85 /* StringLiteral */);
		private final @NonNull SerializationStepAssignedRuleCall _159 // V00*TemplateableElementCS::ownedSignature=89
									= new SerializationStepAssignedRuleCall(0, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 89 /* TemplateSignatureCS */);
		private final @NonNull SerializationStepAssignedRuleCall _160 // V00*TypedRefCS::ownedMultiplicity=50
									= new SerializationStepAssignedRuleCall(0, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 50 /* MultiplicityCS */);
		private final @NonNull SerializationStepSequence _161 // V00*steps-2..4
									= new SerializationStepSequence(0, 2, 4);
		private final @NonNull SerializationStepSequence _162 // V00*steps-2..7
									= new SerializationStepSequence(0, 2, 7);
		private final @NonNull SerializationStepSequence _163 // V00*steps-3..10
									= new SerializationStepSequence(0, 3, 10);
		private final @NonNull SerializationStepSequence _164 // V00*steps-3..5
									= new SerializationStepSequence(0, 3, 5);
		private final @NonNull SerializationStepSequence _165 // V00*steps-3..6
									= new SerializationStepSequence(0, 3, 6);
		private final @NonNull SerializationStepSequence _166 // V00*steps-3..7
									= new SerializationStepSequence(0, 3, 7);
		private final @NonNull SerializationStepSequence _167 // V00*steps-3..8
									= new SerializationStepSequence(0, 3, 8);
		private final @NonNull SerializationStepSequence _168 // V00*steps-4..10
									= new SerializationStepSequence(0, 4, 10);
		private final @NonNull SerializationStepSequence _169 // V00*steps-4..6
									= new SerializationStepSequence(0, 4, 6);
		private final @NonNull SerializationStepSequence _170 // V00*steps-4..8
									= new SerializationStepSequence(0, 4, 8);
		private final @NonNull SerializationStepSequence _171 // V00*steps-4..9
									= new SerializationStepSequence(0, 4, 9);
		private final @NonNull SerializationStepSequence _172 // V00*steps-5..7
									= new SerializationStepSequence(0, 5, 7);
		private final @NonNull SerializationStepSequence _173 // V00*steps-6..8
									= new SerializationStepSequence(0, 6, 8);
		private final @NonNull SerializationStepSequence _174 // V00*steps-6..9
									= new SerializationStepSequence(0, 6, 9);
		private final @NonNull SerializationStepLiteral _175 // V01*'::*'
									= new SerializationStepLiteral(1, "::*");
		private final @NonNull SerializationStepLiteral _176 // V01*'|1'
									= new SerializationStepLiteral(1, "|1");
		private final @NonNull SerializationStepAssignedRuleCall _177 // V01*AbstractNameExpCS::ownedRoundBracketedClause=76
									= new SerializationStepAssignedRuleCall(1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 76 /* RoundBracketedClauseCS */);
		private final @NonNull SerializationStepAssignedRuleCall _178 // V01*ClassifierContextDeclCS::selfName=112
									= new SerializationStepAssignedRuleCall(1, CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME, 112 /* UnrestrictedName */);
		private final @NonNull SerializationStepAssignedRuleCall _179 // V01*CollectionTypeCS::ownedCollectionMultiplicity=50
									= new SerializationStepAssignedRuleCall(1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 50 /* MultiplicityCS */);
		private final @NonNull SerializationStepAssignedRuleCall _180 // V01*CompleteOCLDocumentCS::ownedPackages+=65
									= new SerializationStepAssignedRuleCall(1, CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES, 65 /* PackageDeclarationCS */);
		private final @NonNull SerializationStepAssignedRuleCall _181 // V01*PackageDeclarationCS::ownedContexts+=13
									= new SerializationStepAssignedRuleCall(1, CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS, 13 /* ContextDeclCS */);
		private final @NonNull SerializationStepAssigns _182 // V01*RoundBracketedClauseCS::ownedArguments+=57|58|56
									= new SerializationStepAssigns(1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, null, new @NonNull Integer [] { 57/*NavigatingCommaArgCS*/,58/*NavigatingSemiArgCS*/,56/*NavigatingBarArgCS*/});
		private final @NonNull SerializationStepAssignedRuleCall _183 // V01*TemplateBindingCS::ownedMultiplicity=50
									= new SerializationStepAssignedRuleCall(1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 50 /* MultiplicityCS */);
		private final @NonNull SerializationStepAssignedRuleCall _184 // V01*TemplateableElementCS::ownedSignature=89
									= new SerializationStepAssignedRuleCall(1, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 89 /* TemplateSignatureCS */);
		private final @NonNull SerializationStepAssignedRuleCall _185 // V01*TypedRefCS::ownedMultiplicity=50
									= new SerializationStepAssignedRuleCall(1, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 50 /* MultiplicityCS */);
		private final @NonNull SerializationStepSequence _186 // V01*steps-10..13
									= new SerializationStepSequence(1, 10, 13);
		private final @NonNull SerializationStepSequence _187 // V01*steps-4..6
									= new SerializationStepSequence(1, 4, 6);
		private final @NonNull SerializationStepSequence _188 // V01*steps-4..7
									= new SerializationStepSequence(1, 4, 7);
		private final @NonNull SerializationStepSequence _189 // V01*steps-5..7
									= new SerializationStepSequence(1, 5, 7);
		private final @NonNull SerializationStepSequence _190 // V01*steps-5..8
									= new SerializationStepSequence(1, 5, 8);
		private final @NonNull SerializationStepSequence _191 // V01*steps-5..9
									= new SerializationStepSequence(1, 5, 9);
		private final @NonNull SerializationStepSequence _192 // V01*steps-6..10
									= new SerializationStepSequence(1, 6, 10);
		private final @NonNull SerializationStepSequence _193 // V01*steps-6..8
									= new SerializationStepSequence(1, 6, 8);
		private final @NonNull SerializationStepSequence _194 // V01*steps-7..9
									= new SerializationStepSequence(1, 7, 9);
		private final @NonNull SerializationStepSequence _195 // V01*steps-8..10
									= new SerializationStepSequence(1, 8, 10);
		private final @NonNull SerializationStepSequence _196 // V01*steps-9..11
									= new SerializationStepSequence(1, 9, 11);
		private final @NonNull SerializationStepAssignedRuleCall _197 // V02*AbstractNameExpCS::ownedCurlyBracketedClause=14
									= new SerializationStepAssignedRuleCall(2, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /* CurlyBracketedClauseCS */);
		private final @NonNull SerializationStepAssignedRuleCall _198 // V02*CompleteOCLDocumentCS::ownedContexts+=13
									= new SerializationStepAssignedRuleCall(2, CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS, 13 /* ContextDeclCS */);
		private final @NonNull SerializationStepAssignedRuleCall _199 // V02*TypedRefCS::ownedMultiplicity=50
									= new SerializationStepAssignedRuleCall(2, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 50 /* MultiplicityCS */);
		private final @NonNull SerializationStepSequence _200 // V02*steps-6..8
									= new SerializationStepSequence(2, 6, 8);
		private final @NonNull SerializationStepSequence _201 // V02*steps-7..9
									= new SerializationStepSequence(2, 7, 9);
		private final @NonNull SerializationStepSequence _202 // V02*steps-8..10
									= new SerializationStepSequence(2, 8, 10);
		private final @NonNull SerializationStepSequence _203 // V02*steps-8..12
									= new SerializationStepSequence(2, 8, 12);
		private final @NonNull SerializationStepAssignedRuleCall _204 // V03*ClassifierContextDeclCS::ownedDefinitions+=16
									= new SerializationStepAssignedRuleCall(3, CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, 16 /* DefCS */);
		private final @NonNull SerializationStepAssignedRuleCall _205 // V03*FeatureContextDeclCS::ownedType=94
									= new SerializationStepAssignedRuleCall(3, CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, 94 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _206 // V03*TypedRefCS::ownedMultiplicity=50
									= new SerializationStepAssignedRuleCall(3, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 50 /* MultiplicityCS */);
		private final @NonNull SerializationStepSequence _207 // V03*steps-10..12
									= new SerializationStepSequence(3, 10, 12);
		private final @NonNull SerializationStepSequence _208 // V03*steps-6..8
									= new SerializationStepSequence(3, 6, 8);
		private final @NonNull SerializationStepAssignedRuleCall _209 // V04*TypedElementCS::ownedType=94
									= new SerializationStepAssignedRuleCall(4, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 94 /* TypeExpCS */);
		private final @NonNull SerializationStepSequence _210 // V04*steps-14..16
									= new SerializationStepSequence(4, 14, 16);
		private final @NonNull SerializationStepSequence _211 // V05*steps-17..19
									= new SerializationStepSequence(5, 17, 19);
		private final @NonNull SerializationStepSequence _212 // V06*steps-20..23
									= new SerializationStepSequence(6, 20, 23);
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
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _01 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			null,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _02 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			null,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _03 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _04 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _05 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _06 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _07 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _08 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _09 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _10 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _11 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _12 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _13 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _14 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _15 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _16 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _17 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _18 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _19 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._4 /* «! » «value» */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _20 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _21 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _22 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _23 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _24 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _25 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			null,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _26 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			null,
			null,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _27 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _28 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _29 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _30 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _31 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _32 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _33 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _34 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _35 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _36 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _37 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _38 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._2 /* «! » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _39 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._2 /* «! » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _40 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _41 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _42 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._3 /* «! » «value» «?\n» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _43 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _44 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _45 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _46 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _47 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _48 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _49 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _50 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _51 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _52 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _53 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _54 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _55 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _56 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _57 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _58 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _59 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _60 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _61 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._7 /* «? » «value» «? » */
		};
	}

	/**
	 * The various serialization rules for each grammar rule.
	 */
	private class _GrammarRuleValues
	{
		private final @NonNull TerminalRuleValue _000 // ANY_OTHER
			= new TerminalRuleValue(0, "ANY_OTHER");
		private final @NonNull DataTypeRuleValue _001 // BinaryOperatorName
			= new DataTypeRuleValue(1, "BinaryOperatorName");
		private final @NonNull ParserRuleValue _002 // BooleanLiteralExpCS
			= new ParserRuleValue(2, "BooleanLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._033 /* symbol={'false|true'} */
				},
				null);
		private final @NonNull ParserRuleValue _003 // ClassifierContextDeclCS
			= new ParserRuleValue(3, "ClassifierContextDeclCS",
				new @NonNull SerializationRule [] {
					sr0._016 /* { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedDefinitions+=DefCS[+] } */,
					sr0._017 /* { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[+] ownedDefinitions+=DefCS[*] } */
				},
				null);
		private final @NonNull ParserRuleValue _004 // CoIteratorVariableCS
			= new ParserRuleValue(4, "CoIteratorVariableCS",
				new @NonNull SerializationRule [] {
					sr0._034 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _005 // CollectionLiteralExpCS
			= new ParserRuleValue(5, "CollectionLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._035 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _006 // CollectionLiteralPartCS
			= new ParserRuleValue(6, "CollectionLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._036 /* ownedExpression=PatternExpCS */,
					sr0._037 /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _007 // CollectionPatternCS
			= new ParserRuleValue(7, "CollectionPatternCS",
				new @NonNull SerializationRule [] {
					sr0._038 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _008 // CollectionTypeCS
			= new ParserRuleValue(8, "CollectionTypeCS",
				new @NonNull SerializationRule [] {
					sr0._039 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _009 // CollectionTypeIdentifier
			= new DataTypeRuleValue(9, "CollectionTypeIdentifier");
		private final @NonNull ParserRuleValue _010 // CompleteOCLDocumentCS
			= new ParserRuleValue(10, "CompleteOCLDocumentCS",
				new @NonNull SerializationRule [] {
					sr0._018 /* { ownedImports+=ImportCS[*] ownedPackages+=PackageDeclarationCS[*] ownedContexts+=ContextDeclCS[*] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _011 // CompleteOCLNavigationOperatorName
			= new DataTypeRuleValue(11, "CompleteOCLNavigationOperatorName");
		private final @NonNull ParserRuleValue _012 // ConstraintCS
			= new ParserRuleValue(12, "ConstraintCS",
				new @NonNull SerializationRule [] {
					sr0._019 /* { { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS } */
				},
				null);
		private final @NonNull ParserRuleValue _013 // ContextDeclCS
			= new ParserRuleValue(13, "ContextDeclCS",
				new @NonNull SerializationRule [] {
					sr0._016 /* { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedDefinitions+=DefCS[+] } */,
					sr0._017 /* { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[+] ownedDefinitions+=DefCS[*] } */,
					sr0._025 /* { 'context' ownedSignature=TemplateSignatureCS[?] ownedPathName=PathNameCS '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypeExpCS[?] { 'pre' ownedPreconditions+=ConstraintCS }[*] { 'post' ownedPostconditions+=ConstraintCS }[*] { 'body' ':' ownedBodies+=SpecificationCS }[*] } */,
					sr0._028 /* { 'context' ownedPathName=PathNameCS ':' ownedType=TypeExpCS { 'derive' ':' ownedDefaultExpressions+=SpecificationCS }[*] { 'init' ':' ownedDefaultExpressions+=SpecificationCS }[*] } */
				},
				iv._29); /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */
		private final @NonNull ParserRuleValue _014 // CurlyBracketedClauseCS
			= new ParserRuleValue(14, "CurlyBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr0._040 /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				null);
		private final @NonNull TerminalRuleValue _015 // DOUBLE_QUOTED_STRING
			= new TerminalRuleValue(15, "DOUBLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _016 // DefCS
			= new ParserRuleValue(16, "DefCS",
				new @NonNull SerializationRule [] {
					sr0._020 /* { isStatic='static'[?] 'def' ':' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=DefParameterCS { ',' ownedParameters+=DefParameterCS }[*] }[?] ')' ':' ownedType=TypeExpCS[?] '=' ownedSpecification=SpecificationCS } */,
					sr0._022 /* { isStatic='static'[?] 'def' ':' name=UnrestrictedName ':' ownedType=TypeExpCS '=' ownedSpecification=SpecificationCS } */
				},
				iv._8); /* DefCS|DefOperationCS|DefPropertyCS */
		private final @NonNull ParserRuleValue _017 // DefOperationCS
			= new ParserRuleValue(17, "DefOperationCS",
				new @NonNull SerializationRule [] {
					sr0._020 /* { isStatic='static'[?] 'def' ':' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=DefParameterCS { ',' ownedParameters+=DefParameterCS }[*] }[?] ')' ':' ownedType=TypeExpCS[?] '=' ownedSpecification=SpecificationCS } */
				},
				null);
		private final @NonNull ParserRuleValue _018 // DefParameterCS
			= new ParserRuleValue(18, "DefParameterCS",
				new @NonNull SerializationRule [] {
					sr0._021 /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _019 // DefPropertyCS
			= new ParserRuleValue(19, "DefPropertyCS",
				new @NonNull SerializationRule [] {
					sr0._022 /* { isStatic='static'[?] 'def' ':' name=UnrestrictedName ':' ownedType=TypeExpCS '=' ownedSpecification=SpecificationCS } */
				},
				null);
		private final @NonNull TerminalRuleValue _020 // ESCAPED_CHARACTER
			= new TerminalRuleValue(20, "ESCAPED_CHARACTER");
		private final @NonNull TerminalRuleValue _021 // ESCAPED_ID
			= new TerminalRuleValue(21, "ESCAPED_ID");
		private final @NonNull ParserRuleValue _022 // ElseIfThenExpCS
			= new ParserRuleValue(22, "ElseIfThenExpCS",
				new @NonNull SerializationRule [] {
					sr0._041 /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				null);
		private final @NonNull DataTypeRuleValue _023 // EssentialOCLInfixOperatorName
			= new DataTypeRuleValue(23, "EssentialOCLInfixOperatorName");
		private final @NonNull DataTypeRuleValue _024 // EssentialOCLNavigationOperatorName
			= new DataTypeRuleValue(24, "EssentialOCLNavigationOperatorName");
		private final @NonNull DataTypeRuleValue _025 // EssentialOCLReservedKeyword
			= new DataTypeRuleValue(25, "EssentialOCLReservedKeyword");
		private final @NonNull DataTypeRuleValue _026 // EssentialOCLUnaryOperatorName
			= new DataTypeRuleValue(26, "EssentialOCLUnaryOperatorName");
		private final @NonNull DataTypeRuleValue _027 // EssentialOCLUnreservedName
			= new DataTypeRuleValue(27, "EssentialOCLUnreservedName");
		private final @NonNull DataTypeRuleValue _028 // EssentialOCLUnrestrictedName
			= new DataTypeRuleValue(28, "EssentialOCLUnrestrictedName");
		private final @NonNull ParserRuleValue _029 // ExpCS
			= new ParserRuleValue(29, "ExpCS",
				new @NonNull SerializationRule [] {
					sr0._042 /* symbol={'false|true'} */,
					sr0._043 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._044 /* '*' */,
					sr0._045 /* 'invalid' */,
					sr0._046 /* 'null' */,
					sr0._047 /* 'self' */,
					sr0._048 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._049 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._050 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._051 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._052 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._053 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._054 /* symbol=NUMBER_LITERAL */,
					sr0._055 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._056 /* segments+=StringLiteral[+] */,
					sr0._057 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._058 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._062 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._084 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._58); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _030 // FirstPathElementCS
			= new ParserRuleValue(30, "FirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._000 /* referredElement=UnrestrictedName */
				},
				null);
		private final @NonNull DataTypeRuleValue _031 // ID
			= new DataTypeRuleValue(31, "ID");
		private final @NonNull TerminalRuleValue _032 // INT
			= new TerminalRuleValue(32, "INT");
		private final @NonNull DataTypeRuleValue _033 // Identifier
			= new DataTypeRuleValue(33, "Identifier");
		private final @NonNull ParserRuleValue _034 // IfExpCS
			= new ParserRuleValue(34, "IfExpCS",
				new @NonNull SerializationRule [] {
					sr0._059 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				},
				null);
		private final @NonNull ParserRuleValue _035 // ImportCS
			= new ParserRuleValue(35, "ImportCS",
				new @NonNull SerializationRule [] {
					sr0._023 /* { {'import'|'include'|'library'} { name=Identifier ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _036 // InfixOperatorName
			= new DataTypeRuleValue(36, "InfixOperatorName");
		private final @NonNull ParserRuleValue _037 // InvalidLiteralExpCS
			= new ParserRuleValue(37, "InvalidLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._060 /* 'invalid' */
				},
				null);
		private final @NonNull TerminalRuleValue _038 // LETTER_CHARACTER
			= new TerminalRuleValue(38, "LETTER_CHARACTER");
		private final @NonNull DataTypeRuleValue _039 // LOWER
			= new DataTypeRuleValue(39, "LOWER");
		private final @NonNull ParserRuleValue _040 // LambdaLiteralExpCS
			= new ParserRuleValue(40, "LambdaLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._061 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _041 // LetExpCS
			= new ParserRuleValue(41, "LetExpCS",
				new @NonNull SerializationRule [] {
					sr0._062 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _042 // LetVariableCS
			= new ParserRuleValue(42, "LetVariableCS",
				new @NonNull SerializationRule [] {
					sr0._063 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull TerminalRuleValue _043 // ML_COMMENT
			= new TerminalRuleValue(43, "ML_COMMENT");
		private final @NonNull TerminalRuleValue _044 // ML_SINGLE_QUOTED_STRING
			= new TerminalRuleValue(44, "ML_SINGLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _045 // MapLiteralExpCS
			= new ParserRuleValue(45, "MapLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._064 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _046 // MapLiteralPartCS
			= new ParserRuleValue(46, "MapLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr1._065 /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _047 // MapTypeCS
			= new ParserRuleValue(47, "MapTypeCS",
				new @NonNull SerializationRule [] {
					sr1._066 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _048 // Model
			= new ParserRuleValue(48, "Model",
				new @NonNull SerializationRule [] {
					sr1._067 /* ownedExpression=ExpCS */
				},
				null);
		private final @NonNull ParserRuleValue _049 // MultiplicityBoundsCS
			= new ParserRuleValue(49, "MultiplicityBoundsCS",
				new @NonNull SerializationRule [] {
					sr0._001 /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _050 // MultiplicityCS
			= new ParserRuleValue(50, "MultiplicityCS",
				new @NonNull SerializationRule [] {
					sr0._002 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					sr0._003 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					sr0._004 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */,
					sr0._005 /* { '[' stringBounds={'*|+|?'} ']' } */,
					sr0._006 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					sr0._007 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */
				},
				null);
		private final @NonNull ParserRuleValue _051 // MultiplicityStringCS
			= new ParserRuleValue(51, "MultiplicityStringCS",
				new @NonNull SerializationRule [] {
					sr0._008 /* stringBounds={'*|+|?'} */
				},
				null);
		private final @NonNull DataTypeRuleValue _052 // NUMBER_LITERAL
			= new DataTypeRuleValue(52, "NUMBER_LITERAL");
		private final @NonNull ParserRuleValue _053 // NameExpCS
			= new ParserRuleValue(53, "NameExpCS",
				new @NonNull SerializationRule [] {
					sr1._068 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _054 // NavigatingArgCS
			= new ParserRuleValue(54, "NavigatingArgCS",
				new @NonNull SerializationRule [] {
					sr1._069 /* ownedNameExpression=NavigatingArgExpCS */,
					sr1._070 /* { ':' ownedType=TypeExpCS } */,
					sr1._071 /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr1._072 /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr1._073 /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _055 // NavigatingArgExpCS
			= new ParserRuleValue(55, "NavigatingArgExpCS",
				new @NonNull SerializationRule [] {
					sr0._024 /* '?' */,
					sr0._042 /* symbol={'false|true'} */,
					sr0._043 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._044 /* '*' */,
					sr0._045 /* 'invalid' */,
					sr0._046 /* 'null' */,
					sr0._047 /* 'self' */,
					sr0._048 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._049 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._050 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._051 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._052 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._053 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._054 /* symbol=NUMBER_LITERAL */,
					sr0._055 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._056 /* segments+=StringLiteral[+] */,
					sr0._057 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._058 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._062 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._084 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._59); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _056 // NavigatingBarArgCS
			= new ParserRuleValue(56, "NavigatingBarArgCS",
				new @NonNull SerializationRule [] {
					sr1._074 /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _057 // NavigatingCommaArgCS
			= new ParserRuleValue(57, "NavigatingCommaArgCS",
				new @NonNull SerializationRule [] {
					sr1._078 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					sr1._075 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr1._076 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr1._077 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _058 // NavigatingSemiArgCS
			= new ParserRuleValue(58, "NavigatingSemiArgCS",
				new @NonNull SerializationRule [] {
					sr1._079 /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _059 // NavigationOperatorName
			= new DataTypeRuleValue(59, "NavigationOperatorName");
		private final @NonNull ParserRuleValue _060 // NestedExpCS
			= new ParserRuleValue(60, "NestedExpCS",
				new @NonNull SerializationRule [] {
					sr1._080 /* { '(' ownedExpression=ExpCS ')' } */
				},
				null);
		private final @NonNull ParserRuleValue _061 // NextPathElementCS
			= new ParserRuleValue(61, "NextPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._009 /* referredElement=UnreservedName */
				},
				null);
		private final @NonNull ParserRuleValue _062 // NullLiteralExpCS
			= new ParserRuleValue(62, "NullLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._081 /* 'null' */
				},
				null);
		private final @NonNull ParserRuleValue _063 // NumberLiteralExpCS
			= new ParserRuleValue(63, "NumberLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._082 /* symbol=NUMBER_LITERAL */
				},
				null);
		private final @NonNull ParserRuleValue _064 // OperationContextDeclCS
			= new ParserRuleValue(64, "OperationContextDeclCS",
				new @NonNull SerializationRule [] {
					sr0._025 /* { 'context' ownedSignature=TemplateSignatureCS[?] ownedPathName=PathNameCS '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypeExpCS[?] { 'pre' ownedPreconditions+=ConstraintCS }[*] { 'post' ownedPostconditions+=ConstraintCS }[*] { 'body' ':' ownedBodies+=SpecificationCS }[*] } */
				},
				null);
		private final @NonNull ParserRuleValue _065 // PackageDeclarationCS
			= new ParserRuleValue(65, "PackageDeclarationCS",
				new @NonNull SerializationRule [] {
					sr0._026 /* { 'package' ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedContexts+=ContextDeclCS[*] 'endpackage' } */
				},
				null);
		private final @NonNull ParserRuleValue _066 // ParameterCS
			= new ParserRuleValue(66, "ParameterCS",
				new @NonNull SerializationRule [] {
					sr0._027 /* { { name=UnrestrictedName ':' }[?] ownedType=TypeExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _067 // PathNameCS
			= new ParserRuleValue(67, "PathNameCS",
				new @NonNull SerializationRule [] {
					sr0._010 /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				null);
		private final @NonNull ParserRuleValue _068 // PatternExpCS
			= new ParserRuleValue(68, "PatternExpCS",
				new @NonNull SerializationRule [] {
					sr1._083 /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _069 // PrefixedLetExpCS
			= new ParserRuleValue(69, "PrefixedLetExpCS",
				new @NonNull SerializationRule [] {
					sr0._062 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._084 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._27); /* LetExpCS|PrefixedLetExpCS */
		private final @NonNull ParserRuleValue _070 // PrefixedPrimaryExpCS
			= new ParserRuleValue(70, "PrefixedPrimaryExpCS",
				new @NonNull SerializationRule [] {
					sr0._033 /* symbol={'false|true'} */,
					sr0._035 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._059 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._060 /* 'invalid' */,
					sr0._061 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr1._064 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr1._068 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr1._080 /* { '(' ownedExpression=ExpCS ')' } */,
					sr1._081 /* 'null' */,
					sr1._082 /* symbol=NUMBER_LITERAL */,
					sr1._085 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr1._088 /* 'self' */,
					sr1._093 /* segments+=StringLiteral[+] */,
					sr1._094 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._104 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._113 /* '*' */
				},
				iv._56); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _071 // PrimaryExpCS
			= new ParserRuleValue(71, "PrimaryExpCS",
				new @NonNull SerializationRule [] {
					sr0._033 /* symbol={'false|true'} */,
					sr0._035 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._059 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._060 /* 'invalid' */,
					sr0._061 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr1._064 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr1._068 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr1._080 /* { '(' ownedExpression=ExpCS ')' } */,
					sr1._081 /* 'null' */,
					sr1._082 /* symbol=NUMBER_LITERAL */,
					sr1._088 /* 'self' */,
					sr1._093 /* segments+=StringLiteral[+] */,
					sr1._094 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._104 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._113 /* '*' */
				},
				iv._55); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _072 // PrimitiveLiteralExpCS
			= new ParserRuleValue(72, "PrimitiveLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._033 /* symbol={'false|true'} */,
					sr0._060 /* 'invalid' */,
					sr1._081 /* 'null' */,
					sr1._082 /* symbol=NUMBER_LITERAL */,
					sr1._093 /* segments+=StringLiteral[+] */,
					sr1._113 /* '*' */
				},
				iv._54); /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _073 // PrimitiveTypeCS
			= new ParserRuleValue(73, "PrimitiveTypeCS",
				new @NonNull SerializationRule [] {
					sr1._086 /* name=PrimitiveTypeIdentifier */
				},
				null);
		private final @NonNull DataTypeRuleValue _074 // PrimitiveTypeIdentifier
			= new DataTypeRuleValue(74, "PrimitiveTypeIdentifier");
		private final @NonNull ParserRuleValue _075 // PropertyContextDeclCS
			= new ParserRuleValue(75, "PropertyContextDeclCS",
				new @NonNull SerializationRule [] {
					sr0._028 /* { 'context' ownedPathName=PathNameCS ':' ownedType=TypeExpCS { 'derive' ':' ownedDefaultExpressions+=SpecificationCS }[*] { 'init' ':' ownedDefaultExpressions+=SpecificationCS }[*] } */
				},
				null);
		private final @NonNull ParserRuleValue _076 // RoundBracketedClauseCS
			= new ParserRuleValue(76, "RoundBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._087 /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				null);
		private final @NonNull TerminalRuleValue _077 // SIMPLE_ID
			= new TerminalRuleValue(77, "SIMPLE_ID");
		private final @NonNull TerminalRuleValue _078 // SINGLE_QUOTED_STRING
			= new TerminalRuleValue(78, "SINGLE_QUOTED_STRING");
		private final @NonNull TerminalRuleValue _079 // SL_COMMENT
			= new TerminalRuleValue(79, "SL_COMMENT");
		private final @NonNull ParserRuleValue _080 // SelfExpCS
			= new ParserRuleValue(80, "SelfExpCS",
				new @NonNull SerializationRule [] {
					sr1._088 /* 'self' */
				},
				null);
		private final @NonNull ParserRuleValue _081 // ShadowPartCS
			= new ParserRuleValue(81, "ShadowPartCS",
				new @NonNull SerializationRule [] {
					sr1._089 /* ownedInitExpression=StringLiteralExpCS */,
					sr1._090 /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				null);
		private final @NonNull ParserRuleValue _082 // SimplePathNameCS
			= new ParserRuleValue(82, "SimplePathNameCS",
				new @NonNull SerializationRule [] {
					sr1._091 /* ownedPathElements+=FirstPathElementCS */
				},
				null);
		private final @NonNull ParserRuleValue _083 // SpecificationCS
			= new ParserRuleValue(83, "SpecificationCS",
				new @NonNull SerializationRule [] {
					sr0._029 /* exprString=UNQUOTED_STRING */,
					sr0._030 /* ownedExpression=ExpCS */
				},
				null);
		private final @NonNull ParserRuleValue _084 // SquareBracketedClauseCS
			= new ParserRuleValue(84, "SquareBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._092 /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				null);
		private final @NonNull DataTypeRuleValue _085 // StringLiteral
			= new DataTypeRuleValue(85, "StringLiteral");
		private final @NonNull ParserRuleValue _086 // StringLiteralExpCS
			= new ParserRuleValue(86, "StringLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._093 /* segments+=StringLiteral[+] */
				},
				null);
		private final @NonNull ParserRuleValue _087 // TemplateBindingCS
			= new ParserRuleValue(87, "TemplateBindingCS",
				new @NonNull SerializationRule [] {
					sr0._011 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _088 // TemplateParameterSubstitutionCS
			= new ParserRuleValue(88, "TemplateParameterSubstitutionCS",
				new @NonNull SerializationRule [] {
					sr0._012 /* ownedActualParameter=TypeRefCS */
				},
				null);
		private final @NonNull ParserRuleValue _089 // TemplateSignatureCS
			= new ParserRuleValue(89, "TemplateSignatureCS",
				new @NonNull SerializationRule [] {
					sr0._031 /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */,
					sr0._032 /* { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				},
				null);
		private final @NonNull ParserRuleValue _090 // TupleLiteralExpCS
			= new ParserRuleValue(90, "TupleLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._094 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _091 // TupleLiteralPartCS
			= new ParserRuleValue(91, "TupleLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr1._095 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _092 // TuplePartCS
			= new ParserRuleValue(92, "TuplePartCS",
				new @NonNull SerializationRule [] {
					sr1._096 /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _093 // TupleTypeCS
			= new ParserRuleValue(93, "TupleTypeCS",
				new @NonNull SerializationRule [] {
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _094 // TypeExpCS
			= new ParserRuleValue(94, "TypeExpCS",
				new @NonNull SerializationRule [] {
					sr1._098 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._099 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._100 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._101 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._102 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._103 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._46); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull ParserRuleValue _095 // TypeExpWithoutMultiplicityCS
			= new ParserRuleValue(95, "TypeExpWithoutMultiplicityCS",
				new @NonNull SerializationRule [] {
					sr0._038 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
					sr0._039 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr1._066 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._086 /* name=PrimitiveTypeIdentifier */,
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._109 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				iv._45); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull ParserRuleValue _096 // TypeLiteralCS
			= new ParserRuleValue(96, "TypeLiteralCS",
				new @NonNull SerializationRule [] {
					sr0._039 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr1._066 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._086 /* name=PrimitiveTypeIdentifier */,
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._42); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */
		private final @NonNull ParserRuleValue _097 // TypeLiteralExpCS
			= new ParserRuleValue(97, "TypeLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._104 /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				null);
		private final @NonNull ParserRuleValue _098 // TypeLiteralWithMultiplicityCS
			= new ParserRuleValue(98, "TypeLiteralWithMultiplicityCS",
				new @NonNull SerializationRule [] {
					sr1._105 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._106 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._107 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._108 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._44); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
		private final @NonNull ParserRuleValue _099 // TypeNameExpCS
			= new ParserRuleValue(99, "TypeNameExpCS",
				new @NonNull SerializationRule [] {
					sr1._109 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _100 // TypeParameterCS
			= new ParserRuleValue(100, "TypeParameterCS",
				new @NonNull SerializationRule [] {
					sr0._013 /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _101 // TypeRefCS
			= new ParserRuleValue(101, "TypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._014 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */,
					sr0._015 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */,
					sr0._039 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr1._066 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._086 /* name=PrimitiveTypeIdentifier */,
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._61); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
		private final @NonNull ParserRuleValue _102 // TypedRefCS
			= new ParserRuleValue(102, "TypedRefCS",
				new @NonNull SerializationRule [] {
					sr0._014 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */,
					sr0._039 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr1._066 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._086 /* name=PrimitiveTypeIdentifier */,
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._50); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
		private final @NonNull ParserRuleValue _103 // TypedTypeRefCS
			= new ParserRuleValue(103, "TypedTypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._014 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				null);
		private final @NonNull TerminalRuleValue _104 // UNQUOTED_STRING
			= new TerminalRuleValue(104, "UNQUOTED_STRING");
		private final @NonNull DataTypeRuleValue _105 // UPPER
			= new DataTypeRuleValue(105, "UPPER");
		private final @NonNull DataTypeRuleValue _106 // URI
			= new DataTypeRuleValue(106, "URI");
		private final @NonNull ParserRuleValue _107 // URIFirstPathElementCS
			= new ParserRuleValue(107, "URIFirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr1._110 /* referredElement=UnrestrictedName */,
					sr1._111 /* referredElement=URI */
				},
				null);
		private final @NonNull ParserRuleValue _108 // URIPathNameCS
			= new ParserRuleValue(108, "URIPathNameCS",
				new @NonNull SerializationRule [] {
					sr1._112 /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _109 // UnaryOperatorName
			= new DataTypeRuleValue(109, "UnaryOperatorName");
		private final @NonNull ParserRuleValue _110 // UnlimitedNaturalLiteralExpCS
			= new ParserRuleValue(110, "UnlimitedNaturalLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._113 /* '*' */
				},
				null);
		private final @NonNull DataTypeRuleValue _111 // UnreservedName
			= new DataTypeRuleValue(111, "UnreservedName");
		private final @NonNull DataTypeRuleValue _112 // UnrestrictedName
			= new DataTypeRuleValue(112, "UnrestrictedName");
		private final @NonNull TerminalRuleValue _113 // WS
			= new TerminalRuleValue(113, "WS");
		private final @NonNull ParserRuleValue _114 // WildcardTypeRefCS
			= new ParserRuleValue(114, "WildcardTypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._015 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				null);
	}

	/**
	 * Configuration for each EClass that may be serialized.
	 */
	private class _EClassValues
	{
		private final @NonNull EClassValue _00 // BooleanLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._042, sl._24) /* symbol={'false|true'} */,
					new SerializationRule_SegmentsList(sr0._033, sl._24) /* symbol={'false|true'} */
				}, null
			);
		private final @NonNull EClassValue _01 // ClassifierContextDeclCS
			= new EClassValue(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._016, sl._52) /* { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedDefinitions+=DefCS[+] } */,
					new SerializationRule_SegmentsList(sr0._017, sl._52) /* { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[+] ownedDefinitions+=DefCS[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
						iv._8) /* DefCS|DefOperationCS|DefPropertyCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
						iv._3) /* ConstraintCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						iv._23) /* PathNameCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._37) /* TemplateSignatureCS */
				}
			);
		private final @NonNull EClassValue _02 // CollectionLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._043, sl._30) /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					new SerializationRule_SegmentsList(sr0._035, sl._30) /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						iv._1) /* CollectionLiteralPartCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						iv._2) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassValue _03 // CollectionLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._036, sl._24) /* ownedExpression=PatternExpCS */,
					new SerializationRule_SegmentsList(sr0._037, sl._27) /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						iv._60) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _04 // CollectionPatternCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._038, sl._06) /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
					new SerializationRule_SegmentsList(sr1._100, sl._07) /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._16) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						iv._24) /* PatternExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						iv._2) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassValue _05 // CollectionTypeCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._039, sl._18) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._101, sl._19) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._107, sl._19) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						iv._16) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._16) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _06 // CompleteOCLDocumentCS
			= new EClassValue(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._018, sl._25) /* { ownedImports+=ImportCS[*] ownedPackages+=PackageDeclarationCS[*] ownedContexts+=ContextDeclCS[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS,
						iv._29) /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
						iv._12) /* ImportCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES,
						iv._21) /* PackageDeclarationCS */
				}
			);
		private final @NonNull EClassValue _07 // ConstraintCS
			= new EClassValue(BaseCSPackage.Literals.CONSTRAINT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._019, sl._34) /* { { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						iv._32) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						iv._32) /* SpecificationCS */
				}
			);
		private final @NonNull EClassValue _08 // ContextCS
			= new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._067, sl._24) /* ownedExpression=ExpCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _09 // CurlyBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._040, sl._14) /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						iv._31) /* ShadowPartCS */
				}
			);
		private final @NonNull EClassValue _10 // DefOperationCS
			= new EClassValue(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._020, sl._59) /* { isStatic='static'[?] 'def' ':' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=DefParameterCS { ',' ownedParameters+=DefParameterCS }[*] }[?] ')' ':' ownedType=TypeExpCS[?] '=' ownedSpecification=SpecificationCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS,
						iv._7) /* DefParameterCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._37) /* TemplateSignatureCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
						iv._32) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _11 // DefPropertyCS
			= new EClassValue(CompleteOCLCSPackage.Literals.DEF_PROPERTY_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._022, sl._60) /* { isStatic='static'[?] 'def' ':' name=UnrestrictedName ':' ownedType=TypeExpCS '=' ownedSpecification=SpecificationCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
						iv._32) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _12 // ExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._024, sl._24) /* '?' */,
					new SerializationRule_SegmentsList(sr0._044, sl._24) /* '*' */,
					new SerializationRule_SegmentsList(sr0._045, sl._24) /* 'invalid' */,
					new SerializationRule_SegmentsList(sr0._046, sl._24) /* 'null' */,
					new SerializationRule_SegmentsList(sr0._047, sl._24) /* 'self' */
				}, null
			);
		private final @NonNull EClassValue _13 // ExpSpecificationCS
			= new EClassValue(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._029, sl._24) /* exprString=UNQUOTED_STRING */,
					new SerializationRule_SegmentsList(sr0._030, sl._24) /* ownedExpression=ExpCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _14 // IfExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._048, sl._53) /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					new SerializationRule_SegmentsList(sr0._059, sl._53) /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						iv._60) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						iv._9) /* ElseIfThenExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _15 // IfThenExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._041, sl._50) /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _16 // ImportCS
			= new EClassValue(BaseCSPackage.Literals.IMPORT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._023, sl._29) /* { {'import'|'include'|'library'} { name=Identifier ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
						iv._53) /* URIPathNameCS */
				}
			);
		private final @NonNull EClassValue _17 // InfixExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._049, sl._31) /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
						iv._56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _18 // InvalidLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._060, sl._24) /* 'invalid' */
				}, null
			);
		private final @NonNull EClassValue _19 // LambdaLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._050, sl._57) /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					new SerializationRule_SegmentsList(sr0._061, sl._57) /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _20 // LetExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._062, sl._46) /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						iv._13) /* LetVariableCS */
				}
			);
		private final @NonNull EClassValue _21 // LetVariableCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._063, sl._49) /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._30) /* RoundBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _22 // MapLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._051, sl._30) /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					new SerializationRule_SegmentsList(sr1._064, sl._30) /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						iv._14) /* MapLiteralPartCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						iv._15) /* MapTypeCS */
				}
			);
		private final @NonNull EClassValue _23 // MapLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._065, sl._31) /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _24 // MapTypeCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._066, sl._20) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._102, sl._21) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._108, sl._21) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						iv._46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._16) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						iv._46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _25 // MultiplicityBoundsCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._001, sl._22) /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */,
					new SerializationRule_SegmentsList(sr0._002, sl._10) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._003, sl._11) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._004, sl._11) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, null
			);
		private final @NonNull EClassValue _26 // MultiplicityStringCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._005, sl._12) /* { '[' stringBounds={'*|+|?'} ']' } */,
					new SerializationRule_SegmentsList(sr0._006, sl._13) /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._007, sl._13) /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._008, sl._61) /* stringBounds={'*|+|?'} */
				}, null
			);
		private final @NonNull EClassValue _27 // NameExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._052, sl._26) /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					new SerializationRule_SegmentsList(sr1._068, sl._26) /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._5) /* CurlyBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._23) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._30) /* RoundBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						iv._33) /* SquareBracketedClauseCS */
				}
			);
		private final @NonNull EClassValue _28 // NavigatingArgCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._069, sl._24) /* ownedNameExpression=NavigatingArgExpCS */,
					new SerializationRule_SegmentsList(sr1._070, sl._43) /* { ':' ownedType=TypeExpCS } */,
					new SerializationRule_SegmentsList(sr1._072, sl._32) /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr1._071, sl._33) /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr1._073, sl._28) /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					new SerializationRule_SegmentsList(sr1._074, sl._48) /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */,
					new SerializationRule_SegmentsList(sr1._078, sl._38) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					new SerializationRule_SegmentsList(sr1._076, sl._40) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr1._075, sl._41) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr1._077, sl._39) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					new SerializationRule_SegmentsList(sr1._079, sl._42) /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						iv._0) /* CoIteratorVariableCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						iv._59) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						iv._46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _29 // NestedExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._053, sl._37) /* { '(' ownedExpression=ExpCS ')' } */,
					new SerializationRule_SegmentsList(sr1._080, sl._37) /* { '(' ownedExpression=ExpCS ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _30 // NullLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._081, sl._24) /* 'null' */
				}, null
			);
		private final @NonNull EClassValue _31 // NumberLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._054, sl._24) /* symbol=NUMBER_LITERAL */,
					new SerializationRule_SegmentsList(sr1._082, sl._24) /* symbol=NUMBER_LITERAL */
				}, null
			);
		private final @NonNull EClassValue _32 // OperationContextDeclCS
			= new EClassValue(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._025, sl._44) /* { 'context' ownedSignature=TemplateSignatureCS[?] ownedPathName=PathNameCS '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypeExpCS[?] { 'pre' ownedPreconditions+=ConstraintCS }[*] { 'post' ownedPostconditions+=ConstraintCS }[*] { 'body' ':' ownedBodies+=SpecificationCS }[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES,
						iv._32) /* SpecificationCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS,
						iv._22) /* ParameterCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						iv._23) /* PathNameCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS,
						iv._3) /* ConstraintCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS,
						iv._3) /* ConstraintCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._37) /* TemplateSignatureCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
						iv._46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _33 // PackageDeclarationCS
			= new EClassValue(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._026, sl._47) /* { 'package' ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedContexts+=ContextDeclCS[*] 'endpackage' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS,
						iv._29) /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS,
						iv._3) /* ConstraintCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						iv._23) /* PathNameCS */
				}
			);
		private final @NonNull EClassValue _34 // ParameterCS
			= new EClassValue(BaseCSPackage.Literals.PARAMETER_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._021, sl._58) /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */,
					new SerializationRule_SegmentsList(sr0._027, sl._35) /* { { name=UnrestrictedName ':' }[?] ownedType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _35 // PathElementCS
			= new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._000, sl._61) /* referredElement=UnrestrictedName */,
					new SerializationRule_SegmentsList(sr0._009, sl._61) /* referredElement=UnreservedName */,
					new SerializationRule_SegmentsList(sr1._110, sl._61) /* referredElement=UnrestrictedName */
				}, null
			);
		private final @NonNull EClassValue _36 // PathElementWithURICS
			= new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._111, sl._61) /* referredElement=URI */
				}, null
			);
		private final @NonNull EClassValue _37 // PathNameCS
			= new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._010, sl._03) /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */,
					new SerializationRule_SegmentsList(sr1._091, sl._00) /* ownedPathElements+=FirstPathElementCS */,
					new SerializationRule_SegmentsList(sr1._112, sl._03) /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						iv._52) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
				}
			);
		private final @NonNull EClassValue _38 // PatternExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._083, sl._58) /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
						iv._46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _39 // PrefixExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._055, sl._43) /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					new SerializationRule_SegmentsList(sr1._084, sl._43) /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					new SerializationRule_SegmentsList(sr1._085, sl._43) /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _40 // PrimitiveTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._086, sl._61) /* name=PrimitiveTypeIdentifier */,
					new SerializationRule_SegmentsList(sr1._098, sl._15) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._105, sl._15) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._16) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassValue _41 // PropertyContextDeclCS
			= new EClassValue(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._028, sl._54) /* { 'context' ownedPathName=PathNameCS ':' ownedType=TypeExpCS { 'derive' ':' ownedDefaultExpressions+=SpecificationCS }[*] { 'init' ':' ownedDefaultExpressions+=SpecificationCS }[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS,
						iv._32) /* SpecificationCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						iv._23) /* PathNameCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
						iv._46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _42 // RoundBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._087, sl._08) /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						iv._19) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
				}
			);
		private final @NonNull EClassValue _43 // SelfExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._088, sl._24) /* 'self' */
				}, null
			);
		private final @NonNull EClassValue _44 // ShadowPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._089, sl._24) /* ownedInitExpression=StringLiteralExpCS */,
					new SerializationRule_SegmentsList(sr1._090, sl._58) /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						iv._60) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _45 // SquareBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._092, sl._09) /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _46 // StringLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._056, sl._24) /* segments+=StringLiteral[+] */,
					new SerializationRule_SegmentsList(sr1._093, sl._24) /* segments+=StringLiteral[+] */
				}, null
			);
		private final @NonNull EClassValue _47 // TemplateBindingCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._011, sl._05) /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						iv._16) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						iv._36) /* TemplateParameterSubstitutionCS */
				}
			);
		private final @NonNull EClassValue _48 // TemplateParameterSubstitutionCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._012, sl._24) /* ownedActualParameter=TypeRefCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						iv._61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
				}
			);
		private final @NonNull EClassValue _49 // TemplateSignatureCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._031, sl._36) /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */,
					new SerializationRule_SegmentsList(sr0._032, sl._45) /* { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						iv._47) /* TypeParameterCS */
				}
			);
		private final @NonNull EClassValue _50 // TupleLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._057, sl._56) /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._094, sl._56) /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						iv._38) /* TupleLiteralPartCS */
				}
			);
		private final @NonNull EClassValue _51 // TupleLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._095, sl._55) /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _52 // TuplePartCS
			= new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._096, sl._58) /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _53 // TupleTypeCS
			= new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._097, sl._16) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._099, sl._17) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._106, sl._17) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._16) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						iv._39) /* TuplePartCS */
				}
			);
		private final @NonNull EClassValue _54 // TypeLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._058, sl._24) /* ownedType=TypeLiteralWithMultiplicityCS */,
					new SerializationRule_SegmentsList(sr1._104, sl._24) /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						iv._44) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
				}
			);
		private final @NonNull EClassValue _55 // TypeNameExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._103, sl._02) /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._109, sl._01) /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._5) /* CurlyBracketedClauseCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._16) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._23) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _56 // TypeParameterCS
			= new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._013, sl._51) /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						iv._50) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _57 // TypedTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._014, sl._04) /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						iv._35) /* TemplateBindingCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						iv._23) /* PathNameCS */
				}
			);
		private final @NonNull EClassValue _58 // UnlimitedNaturalLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._113, sl._24) /* '*' */
				}, null
			);
		private final @NonNull EClassValue _59 // VariableCS
			= new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._034, sl._50) /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._46) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _60 // WildcardTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._015, sl._23) /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						iv._50) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
				}
			);
	}

	/**
	 * The various serialization rules that serialize an EClass.
	 */
	private class _SerializationRules0
	{
		// Base::FirstPathElementCS : referredElement=UnrestrictedName
		private @NonNull SerializationRule _000 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._038 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._107 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._61,
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
		private @NonNull SerializationRule _001 = new SerializationRule(49,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._073 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._025 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || «null» */,
				st._084 /* 1*MultiplicityBoundsCS::lowerBound=39 || «? » «value» «? » */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._085 /* 1*MultiplicityBoundsCS::upperBound=105 || «? » «value» «? » */
			},
			sl._22,
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
		private @NonNull SerializationRule _002 = new SerializationRule(50,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._073 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._025 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._084 /* 1*MultiplicityBoundsCS::lowerBound=39 || «? » «value» «? » */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._085 /* 1*MultiplicityBoundsCS::upperBound=105 || «? » «value» «? » */,
				st._020 /* 1*']' || «! » «value» */
			},
			sl._10,
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
		private @NonNull SerializationRule _003 = new SerializationRule(50,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._073 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._025 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._084 /* 1*MultiplicityBoundsCS::lowerBound=39 || «? » «value» «? » */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._085 /* 1*MultiplicityBoundsCS::upperBound=105 || «? » «value» «? » */,
				st._045 /* 1*'|?' || «? » «value» «? » */,
				st._020 /* 1*']' || «! » «value» */
			},
			sl._11,
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
		private @NonNull SerializationRule _004 = new SerializationRule(50,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._105 /* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._073 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._025 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._084 /* 1*MultiplicityBoundsCS::lowerBound=39 || «? » «value» «? » */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._085 /* 1*MultiplicityBoundsCS::upperBound=105 || «? » «value» «? » */,
				st._176 /* V01*'|1' || «? » «value» «? » */,
				st._020 /* 1*']' || «! » «value» */
			},
			sl._11,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					ev._10)
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
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._10, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} ']' }
		private @NonNull SerializationRule _005 = new SerializationRule(50,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._026 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._086 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */,
				st._020 /* 1*']' || «! » «value» */
			},
			sl._12,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._00)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._00, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} '|?' ']' }
		private @NonNull SerializationRule _006 = new SerializationRule(50,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._026 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._086 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */,
				st._045 /* 1*'|?' || «? » «value» «? » */,
				st._020 /* 1*']' || «! » «value» */
			},
			sl._13,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._00)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._00, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _007 = new SerializationRule(50,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._074 /* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._026 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._086 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */,
				st._152 /* V00*'|1' || «? » «value» «? » */,
				st._020 /* 1*']' || «! » «value» */
			},
			sl._13,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._00),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					ev._10)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._00, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._10, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityStringCS : stringBounds={'*|+|?'}
		private @NonNull SerializationRule _008 = new SerializationRule(51,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._026 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._086 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */
			},
			sl._61,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._00)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._00, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Base::NextPathElementCS : referredElement=UnreservedName
		private @NonNull SerializationRule _009 = new SerializationRule(61,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._038 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._106 /* 1*PathElementCS::referredElement=UnreservedName || «? » «value» «? » */
			},
			sl._61,
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
		private @NonNull SerializationRule _010 = new SerializationRule(67,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._134 /* check-rule basecs::PathNameCS.ownedPathElements : 30|61 */,
				ms._057 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || «null» */,
				st._110 /* 1*PathNameCS::ownedPathElements+=30 || «null» */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'::' || «! » «value» «! » */,
				st._111 /* 1*PathNameCS::ownedPathElements+=61 || «null» */
			},
			sl._03,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._20) /* FirstPathElementCS|NextPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE),
					new RuleIndex_GrammarCardinality(61, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::TemplateBindingCS : { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _011 = new SerializationRule(87,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._138 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 88 */,
				ms._137 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 50 */,
				ms._109 /* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				ms._060 /* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._147 /* 1*steps-1..6 || «null» */,
				st._122 /* 1*TemplateBindingCS::ownedSubstitutions+=88 || «null» */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._122 /* 1*TemplateBindingCS::ownedSubstitutions+=88 || «null» */,
				st._183 /* V01*TemplateBindingCS::ownedMultiplicity=50 || «null» */
			},
			sl._05,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					iv._36) /* TemplateParameterSubstitutionCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(88, GrammarCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// Base::TemplateParameterSubstitutionCS : ownedActualParameter=TypeRefCS
		private @NonNull SerializationRule _012 = new SerializationRule(88,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._139 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 101 */,
				ms._046 /* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._123 /* 1*TemplateParameterSubstitutionCS::ownedActualParameter=101 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					iv._48) /* TypeRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ONE)
					}
				)
			});
		// Base::TypeParameterCS : { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] }
		private @NonNull SerializationRule _013 = new SerializationRule(100,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._143 /* check-rule basecs::TypeParameterCS.ownedExtends : 102 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._064 /* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				ms._097 /* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._089 /* 1*NamedElementCS::name=112 || «? » «value» «? » */,
				st._167 /* V00*steps-3..8 || «null» */,
				st._029 /* 1*'extends' || «? » «value» «? » */,
				st._131 /* 1*TypeParameterCS::ownedExtends+=102 || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._000 /* 1*'&&' || «? » «value» «? » */,
				st._131 /* 1*TypeParameterCS::ownedExtends+=102 || «null» */
			},
			sl._51,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					iv._49) /* TypedRefCS */
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
					new RuleIndex_GrammarCardinality(102, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::TypedTypeRefCS : { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] }
		private @NonNull SerializationRule _014 = new SerializationRule(103,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._146 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 87 */,
				ms._147 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 67 */,
				ms._087 /* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				ms._051 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._147 /* 1*steps-1..6 || «null» */,
				st._134 /* 1*TypedTypeRefCS::ownedPathName=67 || «null» */,
				st._165 /* V00*steps-3..6 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._133 /* 1*TypedTypeRefCS::ownedBinding=87 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._04,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._35) /* TemplateBindingCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._23) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(87, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
					}
				)
			});
		// Base::WildcardTypeRefCS : { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
		private @NonNull SerializationRule _015 = new SerializationRule(114,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._148 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : 102 */,
				ms._089 /* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || «null» */,
				st._014 /* 1*'?' || «? » «value» «? » */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._029 /* 1*'extends' || «? » «value» «? » */,
				st._137 /* 1*WildcardTypeRefCS::ownedExtends=102 || «null» */
			},
			sl._23,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					iv._49) /* TypedRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(102, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// CompleteOCL::ClassifierContextDeclCS : { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedDefinitions+=DefCS[+] }
		private @NonNull SerializationRule _016 = new SerializationRule(3,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._162 /* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
				ms._141 /* check-rule basecs::TemplateableElementCS.ownedSignature : 89 */,
				ms._149 /* check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : 16 */,
				ms._150 /* check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : 12 */,
				ms._123 /* assign V3 = |ClassifierContextDeclCS::ownedDefinitions| */,
				ms._118 /* assign V2 = |ClassifierContextDeclCS::ownedInvariants| */,
				ms._040 /* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
				ms._100 /* assign V1 = |ClassifierContextDeclCS::selfName| */,
				ms._084 /* assign V0 = |TemplateableElementCS::ownedSignature| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._022 /* 1*'context' || «? » «value» «? » */,
				st._159 /* V00*TemplateableElementCS::ownedSignature=89 || «null» */,
				st._178 /* V01*ClassifierContextDeclCS::selfName=112 || «? » «value» «? » */,
				st._112 /* 1*PathNameDeclCS::ownedPathName=67 || «null» */,
				st._200 /* V02*steps-6..8 || «null» */,
				st._034 /* 1*'inv' || «? » «value» «? » */,
				st._049 /* 1*ClassifierContextDeclCS::ownedInvariants+=12 || «null» */,
				st._204 /* V03*ClassifierContextDeclCS::ownedDefinitions+=16 || «null» */
			},
			sl._52,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					iv._23) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._37) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
					iv._6) /* DefCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
					iv._3) /* ConstraintCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(89, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(16, GrammarCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(12, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// CompleteOCL::ClassifierContextDeclCS : { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[+] ownedDefinitions+=DefCS[*] }
		private @NonNull SerializationRule _017 = new SerializationRule(3,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._162 /* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
				ms._141 /* check-rule basecs::TemplateableElementCS.ownedSignature : 89 */,
				ms._149 /* check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : 16 */,
				ms._150 /* check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : 12 */,
				ms._123 /* assign V3 = |ClassifierContextDeclCS::ownedDefinitions| */,
				ms._118 /* assign V2 = |ClassifierContextDeclCS::ownedInvariants| */,
				ms._040 /* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
				ms._100 /* assign V1 = |ClassifierContextDeclCS::selfName| */,
				ms._084 /* assign V0 = |TemplateableElementCS::ownedSignature| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._022 /* 1*'context' || «? » «value» «? » */,
				st._159 /* V00*TemplateableElementCS::ownedSignature=89 || «null» */,
				st._178 /* V01*ClassifierContextDeclCS::selfName=112 || «? » «value» «? » */,
				st._112 /* 1*PathNameDeclCS::ownedPathName=67 || «null» */,
				st._200 /* V02*steps-6..8 || «null» */,
				st._034 /* 1*'inv' || «? » «value» «? » */,
				st._049 /* 1*ClassifierContextDeclCS::ownedInvariants+=12 || «null» */,
				st._204 /* V03*ClassifierContextDeclCS::ownedDefinitions+=16 || «null» */
			},
			sl._52,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					iv._23) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._37) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
					iv._6) /* DefCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
					iv._3) /* ConstraintCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(89, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(12, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// CompleteOCL::CompleteOCLDocumentCS : { ownedImports+=ImportCS[*] ownedPackages+=PackageDeclarationCS[*] ownedContexts+=ContextDeclCS[*] }
		private @NonNull SerializationRule _018 = new SerializationRule(10,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._136 /* check-rule basecs::RootCS.ownedImports : 35 */,
				ms._151 /* check-rule completeoclcs::CompleteOCLDocumentCS.ownedContexts : 13 */,
				ms._152 /* check-rule completeoclcs::CompleteOCLDocumentCS.ownedPackages : 65 */,
				ms._119 /* assign V2 = |CompleteOCLDocumentCS::ownedContexts| */,
				ms._102 /* assign V1 = |CompleteOCLDocumentCS::ownedPackages| */,
				ms._082 /* assign V0 = |RootCS::ownedImports| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._157 /* V00*RootCS::ownedImports+=35 || «null» */,
				st._180 /* V01*CompleteOCLDocumentCS::ownedPackages+=65 || «null» */,
				st._198 /* V02*CompleteOCLDocumentCS::ownedContexts+=13 || «null» */
			},
			sl._25,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					iv._12) /* ImportCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS,
					iv._4) /* ContextDeclCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES,
					iv._21) /* PackageDeclarationCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(35, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(65, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// CompleteOCL::ConstraintCS : { { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS }
		private @NonNull SerializationRule _019 = new SerializationRule(12,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._131 /* check-rule basecs::ConstraintCS.ownedSpecification : 83 */,
				ms._130 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 83 */,
				ms._006 /* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				ms._075 /* assign V0 = |NamedElementCS::name| */,
				ms._103 /* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._162 /* V00*steps-2..7 || «null» */,
				st._089 /* 1*NamedElementCS::name=112 || «? » «value» «? » */,
				st._188 /* V01*steps-4..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._060 /* 1*ConstraintCS::ownedMessageSpecification=83 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._061 /* 1*ConstraintCS::ownedSpecification=83 || «null» */
			},
			sl._34,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._32) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._32) /* SpecificationCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(83, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(83, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// CompleteOCL::DefOperationCS : { isStatic='static'[?] 'def' ':' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=DefParameterCS { ',' ownedParameters+=DefParameterCS }[*] }[?] ')' ':' ownedType=TypeExpCS[?] '=' ownedSpecification=SpecificationCS }
		private @NonNull SerializationRule _020 = new SerializationRule(17,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._154 /* check-rule completeoclcs::DefOperationCS.ownedParameters : 18 */,
				ms._141 /* check-rule basecs::TemplateableElementCS.ownedSignature : 89 */,
				ms._153 /* check-rule completeoclcs::DefCS.ownedSpecification : 83 */,
				ms._144 /* check-rule basecs::TypedElementCS.ownedType : 94 */,
				ms._008 /* assert (|DefCS::ownedSpecification| - 1) == 0 */,
				ms._127 /* assign V4 = |TypedElementCS::ownedType| */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._110 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._069 /* assign V0 = |DefCS::isStatic.'static'| */,
				ms._114 /* assign V2 = (|DefOperationCS::ownedParameters| > 0) */,
				ms._121 /* assign V3 = (|DefOperationCS::ownedParameters| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._142 /* 1*steps-1..17 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._151 /* V00*'static' || «? » «value» «? » */,
				st._023 /* 1*'def' || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._184 /* V01*TemplateableElementCS::ownedSignature=89 || «null» */,
				st._089 /* 1*NamedElementCS::name=112 || «? » «value» «? » */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._203 /* V02*steps-8..12 || «null» */,
				st._065 /* 1*DefOperationCS::ownedParameters+=18 || «null» */,
				st._207 /* V03*steps-10..12 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._065 /* 1*DefOperationCS::ownedParameters+=18 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._209 /* V04*TypedElementCS::ownedType=94 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._064 /* 1*DefCS::ownedSpecification=83 || «null» */
			},
			sl._59,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC,
					ev._08)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS,
					iv._7) /* DefParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._37) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					iv._32) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._08, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(18, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(89, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(83, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// CompleteOCL::DefParameterCS : { name=UnrestrictedName ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _021 = new SerializationRule(18,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._144 /* check-rule basecs::TypedElementCS.ownedType : 94 */,
				ms._050 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._089 /* 1*NamedElementCS::name=112 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._132 /* 1*TypedElementCS::ownedType=94 || «null» */
			},
			sl._58,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */
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
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
					}
				)
			});
		// CompleteOCL::DefPropertyCS : { isStatic='static'[?] 'def' ':' name=UnrestrictedName ':' ownedType=TypeExpCS '=' ownedSpecification=SpecificationCS }
		private @NonNull SerializationRule _022 = new SerializationRule(19,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._153 /* check-rule completeoclcs::DefCS.ownedSpecification : 83 */,
				ms._144 /* check-rule basecs::TypedElementCS.ownedType : 94 */,
				ms._008 /* assert (|DefCS::ownedSpecification| - 1) == 0 */,
				ms._050 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._069 /* assign V0 = |DefCS::isStatic.'static'| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._151 /* V00*'static' || «? » «value» «? » */,
				st._023 /* 1*'def' || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._089 /* 1*NamedElementCS::name=112 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._132 /* 1*TypedElementCS::ownedType=94 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._064 /* 1*DefCS::ownedSpecification=83 || «null» */
			},
			sl._60,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC,
					ev._08)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					iv._32) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._08, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(83, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
					}
				)
			});
		// CompleteOCL::ImportCS : { {'import'|'include'|'library'} { name=Identifier ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] }
		private @NonNull SerializationRule _023 = new SerializationRule(35,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._132 /* check-rule basecs::ImportCS.ownedPathName : 108 */,
				ms._104 /* assign V1 = |ImportCS::isAll.'::*'| */,
				ms._016 /* assert (|ImportCS::ownedPathName| - 1) == 0 */,
				ms._075 /* assign V0 = |NamedElementCS::name| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._031 /* 1*'import' || «null» */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._090 /* 1*NamedElementCS::name=33 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*ImportCS::ownedPathName=108 || «null» */,
				st._175 /* V01*'::*' || «? » «value» «? » */
			},
			sl._29,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
					ev._02)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					iv._53) /* URIPathNameCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._02, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(108, GrammarCardinality.ONE)
					}
				)
			});
		// CompleteOCL::NavigatingArgExpCS : '?'
		private @NonNull SerializationRule _024 = new SerializationRule(55,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._014 /* 1*'?' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// CompleteOCL::OperationContextDeclCS : { 'context' ownedSignature=TemplateSignatureCS[?] ownedPathName=PathNameCS '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypeExpCS[?] { 'pre' ownedPreconditions+=ConstraintCS }[*] { 'post' ownedPostconditions+=ConstraintCS }[*] { 'body' ':' ownedBodies+=SpecificationCS }[*] }
		private @NonNull SerializationRule _025 = new SerializationRule(64,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._162 /* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
				ms._159 /* check-rule completeoclcs::OperationContextDeclCS.ownedPreconditions : 12 */,
				ms._158 /* check-rule completeoclcs::OperationContextDeclCS.ownedPostconditions : 12 */,
				ms._141 /* check-rule basecs::TemplateableElementCS.ownedSignature : 89 */,
				ms._155 /* check-rule completeoclcs::FeatureContextDeclCS.ownedType : 94 */,
				ms._157 /* check-rule completeoclcs::OperationContextDeclCS.ownedParameters : 66 */,
				ms._156 /* check-rule completeoclcs::OperationContextDeclCS.ownedBodies : 83 */,
				ms._129 /* assign V6 = |OperationContextDeclCS::ownedBodies| */,
				ms._128 /* assign V5 = |OperationContextDeclCS::ownedPostconditions| */,
				ms._126 /* assign V4 = |OperationContextDeclCS::ownedPreconditions| */,
				ms._124 /* assign V3 = |FeatureContextDeclCS::ownedType| */,
				ms._040 /* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
				ms._084 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._094 /* assign V1 = (|OperationContextDeclCS::ownedParameters| > 0) */,
				ms._115 /* assign V2 = (|OperationContextDeclCS::ownedParameters| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._143 /* 1*steps-1..23 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._022 /* 1*'context' || «? » «value» «? » */,
				st._159 /* V00*TemplateableElementCS::ownedSignature=89 || «null» */,
				st._112 /* 1*PathNameDeclCS::ownedPathName=67 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._192 /* V01*steps-6..10 || «null» */,
				st._098 /* 1*OperationContextDeclCS::ownedParameters+=66 || «null» */,
				st._202 /* V02*steps-8..10 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._098 /* 1*OperationContextDeclCS::ownedParameters+=66 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._205 /* V03*FeatureContextDeclCS::ownedType=94 || «null» */,
				st._210 /* V04*steps-14..16 || «null» */,
				st._040 /* 1*'pre' || «? » «value» «? » */,
				st._100 /* 1*OperationContextDeclCS::ownedPreconditions+=12 || «null» */,
				st._211 /* V05*steps-17..19 || «null» */,
				st._039 /* 1*'post' || «? » «value» «? » */,
				st._099 /* 1*OperationContextDeclCS::ownedPostconditions+=12 || «null» */,
				st._212 /* V06*steps-20..23 || «null» */,
				st._021 /* 1*'body' || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._097 /* 1*OperationContextDeclCS::ownedBodies+=83 || «null» */
			},
			sl._44,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					iv._23) /* PathNameCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS,
					iv._3) /* ConstraintCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS,
					iv._3) /* ConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._37) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS,
					iv._22) /* ParameterCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES,
					iv._32) /* SpecificationCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(12, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(12, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(89, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(66, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(83, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// CompleteOCL::PackageDeclarationCS : { 'package' ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedContexts+=ContextDeclCS[*] 'endpackage' }
		private @NonNull SerializationRule _026 = new SerializationRule(65,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._162 /* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
				ms._161 /* check-rule completeoclcs::PackageDeclarationCS.ownedInvariants : 12 */,
				ms._160 /* check-rule completeoclcs::PackageDeclarationCS.ownedContexts : 13 */,
				ms._108 /* assign V1 = |PackageDeclarationCS::ownedContexts| */,
				ms._079 /* assign V0 = |PackageDeclarationCS::ownedInvariants| */,
				ms._040 /* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._038 /* 1*'package' || «? » «value» «? » */,
				st._112 /* 1*PathNameDeclCS::ownedPathName=67 || «null» */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._034 /* 1*'inv' || «? » «value» «? » */,
				st._104 /* 1*PackageDeclarationCS::ownedInvariants+=12 || «null» */,
				st._181 /* V01*PackageDeclarationCS::ownedContexts+=13 || «null» */,
				st._028 /* 1*'endpackage' || «? » «value» «? » */
			},
			sl._47,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					iv._23) /* PathNameCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS,
					iv._3) /* ConstraintCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS,
					iv._4) /* ContextDeclCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(12, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// CompleteOCL::ParameterCS : { { name=UnrestrictedName ':' }[?] ownedType=TypeExpCS }
		private @NonNull SerializationRule _027 = new SerializationRule(66,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._144 /* check-rule basecs::TypedElementCS.ownedType : 94 */,
				ms._050 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._075 /* assign V0 = |NamedElementCS::name| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._161 /* V00*steps-2..4 || «null» */,
				st._089 /* 1*NamedElementCS::name=112 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._132 /* 1*TypedElementCS::ownedType=94 || «null» */
			},
			sl._35,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
					}
				)
			});
		// CompleteOCL::PropertyContextDeclCS : { 'context' ownedPathName=PathNameCS ':' ownedType=TypeExpCS { 'derive' ':' ownedDefaultExpressions+=SpecificationCS }[*] { 'init' ':' ownedDefaultExpressions+=SpecificationCS }[*] }
		private @NonNull SerializationRule _028 = new SerializationRule(75,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._162 /* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
				ms._155 /* check-rule completeoclcs::FeatureContextDeclCS.ownedType : 94 */,
				ms._163 /* check-rule completeoclcs::PropertyContextDeclCS.ownedDefaultExpressions : 83 */,
				ms._010 /* assert (|FeatureContextDeclCS::ownedType| - 1) == 0 */,
				ms._040 /* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
				ms._081 /* assign V0 = |PropertyContextDeclCS::ownedDefaultExpressions| */,
				ms._098 /* assign V1 = 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._141 /* 1*steps-1..13 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._022 /* 1*'context' || «? » «value» «? » */,
				st._112 /* 1*PathNameDeclCS::ownedPathName=67 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*FeatureContextDeclCS::ownedType=94 || «null» */,
				st._174 /* V00*steps-6..9 || «null» */,
				st._024 /* 1*'derive' || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._115 /* 1*PropertyContextDeclCS::ownedDefaultExpressions+=83 || «null» */,
				st._186 /* V01*steps-10..13 || «null» */,
				st._033 /* 1*'init' || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._115 /* 1*PropertyContextDeclCS::ownedDefaultExpressions+=83 || «null» */
			},
			sl._54,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					iv._23) /* PathNameCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._32) /* SpecificationCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(83, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// CompleteOCL::SpecificationCS : exprString=UNQUOTED_STRING
		private @NonNull SerializationRule _029 = new SerializationRule(83,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._045 /* assert (|SpecificationCS::exprString| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._120 /* 1*SpecificationCS::exprString=104 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// CompleteOCL::SpecificationCS : ownedExpression=ExpCS
		private @NonNull SerializationRule _030 = new SerializationRule(83,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._179 /* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 29 */,
				ms._009 /* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._066 /* 1*ExpSpecificationCS::ownedExpression=29 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				)
			});
		// CompleteOCL::TemplateSignatureCS : { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' }
		private @NonNull SerializationRule _031 = new SerializationRule(89,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._140 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 100 */,
				ms._061 /* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._124 /* 1*TemplateSignatureCS::ownedParameters+=100 || «null» */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._124 /* 1*TemplateSignatureCS::ownedParameters+=100 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._36,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._47) /* TypeParameterCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(100, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// CompleteOCL::TemplateSignatureCS : { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' }
		private @NonNull SerializationRule _032 = new SerializationRule(89,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._140 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 100 */,
				ms._061 /* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._010 /* 1*'<' || «? » «value» «? » */,
				st._124 /* 1*TemplateSignatureCS::ownedParameters+=100 || «null» */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._124 /* 1*TemplateSignatureCS::ownedParameters+=100 || «null» */,
				st._013 /* 1*'>' || «? » «value» «? » */
			},
			sl._45,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._47) /* TypeParameterCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(100, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::BooleanLiteralExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _033 = new SerializationRule(2,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._001 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._048 /* 1*BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._07)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._07, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::CoIteratorVariableCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] }
		private @NonNull SerializationRule _034 = new SerializationRule(4,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._216 /* check-rule essentialoclcs::VariableCS.ownedType : 94 */,
				ms._088 /* assign V0 = |VariableCS::ownedType| */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._089 /* 1*NamedElementCS::name=112 || «? » «value» «? » */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._136 /* 1*VariableCS::ownedType=94 || «null» */
			},
			sl._50,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */
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
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _035 = new SerializationRule(5,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._169 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 8 */,
				ms._168 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 6 */,
				ms._002 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._053 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._090 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._051 /* 1*CollectionLiteralExpCS::ownedType=8 || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._170 /* V00*steps-4..8 || «null» */,
				st._050 /* 1*CollectionLiteralExpCS::ownedParts+=6 || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._050 /* 1*CollectionLiteralExpCS::ownedParts+=6 || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._30,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					iv._1) /* CollectionLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(8, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(6, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : ownedExpression=PatternExpCS
		private @NonNull SerializationRule _036 = new SerializationRule(6,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._171 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 68 */,
				ms._003 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._053 /* 1*CollectionLiteralPartCS::ownedExpression=68 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					iv._24) /* PatternExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] }
		private @NonNull SerializationRule _037 = new SerializationRule(6,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._170 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 29 */,
				ms._172 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 29 */,
				ms._066 /* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
				ms._003 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._052 /* 1*CollectionLiteralPartCS::ownedExpression=29 || «null» */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._054 /* 1*CollectionLiteralPartCS::ownedLastExpression=29 || «null» */
			},
			sl._27,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CollectionPatternCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' }
		private @NonNull SerializationRule _038 = new SerializationRule(7,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._173 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 68 */,
				ms._174 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 8 */,
				ms._067 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._091 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._004 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._139 /* 1*steps-1..11 || «null» */,
				st._056 /* 1*CollectionPatternCS::ownedType=8 || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._168 /* V00*steps-4..10 || «null» */,
				st._055 /* 1*CollectionPatternCS::ownedParts+=68 || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._055 /* 1*CollectionPatternCS::ownedParts+=68 || «null» */,
				st._004 /* 1*'++' || «? » «value» «? » */,
				st._057 /* 1*CollectionPatternCS::restVariableName=33 || «? » «value» «? » */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._06,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._24) /* PatternExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(68, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(8, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionTypeCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
		private @NonNull SerializationRule _039 = new SerializationRule(8,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._176 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 95 */,
				ms._175 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 50 */,
				ms._068 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._101 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || «null» */,
				st._058 /* 1*CollectionTypeCS::name=9 || «? » «value» «? » */,
				st._166 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._059 /* 1*CollectionTypeCS::ownedType=95 || «null» */,
				st._179 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=50 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._18,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._41) /* TypeExpWithoutMultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._16) /* MultiplicityCS */
			},
			new /*@NonNull*/ EAttribute [] {
				EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(95, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CurlyBracketedClauseCS : { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _040 = new SerializationRule(14,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._178 /* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 81 */,
				ms._054 /* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
				ms._092 /* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._166 /* V00*steps-3..7 || «null» */,
				st._063 /* 1*CurlyBracketedClauseCS::ownedParts+=81 || «null» */,
				st._189 /* V01*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._063 /* 1*CurlyBracketedClauseCS::ownedParts+=81 || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._14,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					iv._31) /* ShadowPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(81, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ElseIfThenExpCS : { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS }
		private @NonNull SerializationRule _041 = new SerializationRule(22,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._185 /* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 29 */,
				ms._184 /* check-rule essentialoclcs::IfThenExpCS.ownedCondition : 29 */,
				ms._015 /* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
				ms._014 /* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._026 /* 1*'elseif' || «? » «value» «? » */,
				st._071 /* 1*IfThenExpCS::ownedCondition=29 || «null» */,
				st._042 /* 1*'then' || «? » «value» «? » */,
				st._072 /* 1*IfThenExpCS::ownedThenExpression=29 || «null» */
			},
			sl._50,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _042 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._001 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._048 /* 1*BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._07)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._07, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::ExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _043 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._169 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 8 */,
				ms._168 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 6 */,
				ms._002 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._053 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._090 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._051 /* 1*CollectionLiteralExpCS::ownedType=8 || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._170 /* V00*steps-4..8 || «null» */,
				st._050 /* 1*CollectionLiteralExpCS::ownedParts+=6 || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._050 /* 1*CollectionLiteralExpCS::ownedParts+=6 || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._30,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					iv._1) /* CollectionLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(8, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(6, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : '*'
		private @NonNull SerializationRule _044 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._003 /* 1*'*' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : 'invalid'
		private @NonNull SerializationRule _045 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._035 /* 1*'invalid' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : 'null'
		private @NonNull SerializationRule _046 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._037 /* 1*'null' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : 'self'
		private @NonNull SerializationRule _047 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._041 /* 1*'self' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _048 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._183 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 29 */,
				ms._182 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 22 */,
				ms._181 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 29 */,
				ms._180 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 29|68 */,
				ms._012 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._070 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._013 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._011 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._030 /* 1*'if' || «? » «value» «? » */,
				st._068 /* 1*IfExpCS::ownedCondition=29|68 || «null» */,
				st._042 /* 1*'then' || «? » «value» «? » */,
				st._070 /* 1*IfExpCS::ownedThenExpression=29 || «null» */,
				st._154 /* V00*IfExpCS::ownedIfThenExpressions+=22 || «null» */,
				st._025 /* 1*'else' || «? » «value» «? » */,
				st._069 /* 1*IfExpCS::ownedElseExpression=29 || «null» */,
				st._027 /* 1*'endif' || «? » «value» «? » */
			},
			sl._53,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._9) /* ElseIfThenExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._25) /* ExpCS|PatternExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(22, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE),
					new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS }
		private @NonNull SerializationRule _049 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._202 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 29 */,
				ms._186 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : 70 */,
				ms._037 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._017 /* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._074 /* 1*InfixExpCS::ownedLeft=70 || «null» */,
				st._087 /* 1*NamedElementCS::name=1 || «? » «value» «? » */,
				st._101 /* 1*OperatorExpCS::ownedRight=29 || «null» */
			},
			sl._31,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					iv._28) /* PrefixedPrimaryExpCS */
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
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(70, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _050 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._187 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 29 */,
				ms._018 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._016 /* 1*'Lambda' || «? » «value» «? » */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._075 /* 1*LambdaLiteralExpCS::ownedExpressionCS=29 || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._57,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _051 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._192 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 47 */,
				ms._191 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 46 */,
				ms._020 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._056 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._093 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._079 /* 1*MapLiteralExpCS::ownedType=47 || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._170 /* V00*steps-4..8 || «null» */,
				st._078 /* 1*MapLiteralExpCS::ownedParts+=46 || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._078 /* 1*MapLiteralExpCS::ownedParts+=46 || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._30,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					iv._15) /* MapTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					iv._14) /* MapLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(47, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(46, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _052 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._165 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 67 */,
				ms._167 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 84 */,
				ms._164 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14 */,
				ms._166 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 76 */,
				ms._122 /* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				ms._117 /* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				ms._099 /* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				ms._065 /* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				ms._000 /* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._047 /* 1*AbstractNameExpCS::ownedPathName=67 || «null» */,
				st._153 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=84 || «null» */,
				st._177 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=76 || «null» */,
				st._197 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=14 || «null» */,
				st._208 /* V03*steps-6..8 || «null» */,
				st._015 /* 1*'@' || «? » «value» «? » */,
				st._040 /* 1*'pre' || «? » «value» «? » */
			},
			sl._26,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._04)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._23) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._33) /* SquareBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._30) /* RoundBracketedClauseCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._04, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(84, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(14, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(76, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _053 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._201 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 29 */,
				ms._035 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._095 /* 1*NestedExpCS::ownedExpression=29 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._37,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _054 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._036 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._096 /* 1*NumberLiteralExpCS::symbol=52 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::ExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _055 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._204 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 70 */,
				ms._037 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._088 /* 1*NamedElementCS::name=109 || «? » «value» «? » */,
				st._103 /* 1*OperatorExpCS::ownedRight=70 || «null» */
			},
			sl._43,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._28) /* PrefixedPrimaryExpCS */
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
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(70, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _056 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._083 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._158 /* V00*StringLiteralExpCS::segments+=85 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE_OR_MORE)
					}
				)
			},
			null);
		// EssentialOCL::ExpCS : { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
		private @NonNull SerializationRule _057 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._210 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 91 */,
				ms._062 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._018 /* 1*'Tuple' || «? » «value» «? » */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._125 /* 1*TupleLiteralExpCS::ownedParts+=91 || «null» */,
				st._172 /* V00*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._125 /* 1*TupleLiteralExpCS::ownedParts+=91 || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._56,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._38) /* TupleLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(91, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _058 = new SerializationRule(29,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._211 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 98 */,
				ms._048 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._127 /* 1*TypeLiteralExpCS::ownedType=98 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._43) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(98, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::IfExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _059 = new SerializationRule(34,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._183 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 29 */,
				ms._182 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 22 */,
				ms._181 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 29 */,
				ms._180 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 29|68 */,
				ms._012 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._070 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._013 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._011 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._030 /* 1*'if' || «? » «value» «? » */,
				st._068 /* 1*IfExpCS::ownedCondition=29|68 || «null» */,
				st._042 /* 1*'then' || «? » «value» «? » */,
				st._070 /* 1*IfExpCS::ownedThenExpression=29 || «null» */,
				st._154 /* V00*IfExpCS::ownedIfThenExpressions+=22 || «null» */,
				st._025 /* 1*'else' || «? » «value» «? » */,
				st._069 /* 1*IfExpCS::ownedElseExpression=29 || «null» */,
				st._027 /* 1*'endif' || «? » «value» «? » */
			},
			sl._53,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._9) /* ElseIfThenExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._25) /* ExpCS|PatternExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(22, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE),
					new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::InvalidLiteralExpCS : 'invalid'
		private @NonNull SerializationRule _060 = new SerializationRule(37,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._035 /* 1*'invalid' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::LambdaLiteralExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _061 = new SerializationRule(40,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._187 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 29 */,
				ms._018 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._016 /* 1*'Lambda' || «? » «value» «? » */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._075 /* 1*LambdaLiteralExpCS::ownedExpressionCS=29 || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._57,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::LetExpCS : { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
		private @NonNull SerializationRule _062 = new SerializationRule(41,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._189 /* check-rule essentialoclcs::LetExpCS.ownedVariables : 42 */,
				ms._188 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : 29 */,
				ms._019 /* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
				ms._055 /* assign V0 = (|LetExpCS::ownedVariables| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._036 /* 1*'let' || «? » «value» «? » */,
				st._077 /* 1*LetExpCS::ownedVariables+=42 || «null» */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._077 /* 1*LetExpCS::ownedVariables+=42 || «null» */,
				st._032 /* 1*'in' || «? » «value» «? » */,
				st._076 /* 1*LetExpCS::ownedInExpression=29 || «null» */
			},
			sl._46,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					iv._13) /* LetVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(42, GrammarCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::LetVariableCS : { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _063 = new SerializationRule(42,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._216 /* check-rule essentialoclcs::VariableCS.ownedType : 94 */,
				ms._190 /* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 76 */,
				ms._215 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 29 */,
				ms._052 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._113 /* assign V1 = |VariableCS::ownedType| */,
				ms._071 /* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._089 /* 1*NamedElementCS::name=112 || «? » «value» «? » */,
				st._155 /* V00*LetVariableCS::ownedRoundBracketedClause=76 || «null» */,
				st._187 /* V01*steps-4..6 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._136 /* 1*VariableCS::ownedType=94 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._135 /* 1*VariableCS::ownedInitExpression=29 || «null» */
			},
			sl._49,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._30) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */
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
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(76, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				)
			});
	}
	private class _SerializationRules1
	{
		// EssentialOCL::MapLiteralExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _064 = new SerializationRule(45,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._192 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 47 */,
				ms._191 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 46 */,
				ms._020 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._056 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._093 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._079 /* 1*MapLiteralExpCS::ownedType=47 || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._170 /* V00*steps-4..8 || «null» */,
				st._078 /* 1*MapLiteralExpCS::ownedParts+=46 || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._078 /* 1*MapLiteralExpCS::ownedParts+=46 || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._30,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					iv._15) /* MapTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					iv._14) /* MapLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(47, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(46, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::MapLiteralPartCS : { ownedKey=ExpCS '<-' ownedValue=ExpCS }
		private @NonNull SerializationRule _065 = new SerializationRule(46,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._193 /* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 29 */,
				ms._194 /* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 29 */,
				ms._022 /* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
				ms._021 /* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._080 /* 1*MapLiteralPartCS::ownedKey=29 || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._081 /* 1*MapLiteralPartCS::ownedValue=29 || «null» */
			},
			sl._31,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::MapTypeCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
		private @NonNull SerializationRule _066 = new SerializationRule(47,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._196 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 94 */,
				ms._195 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 94 */,
				ms._072 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._024 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._023 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._017 /* 1*'Map' || «? » «value» «? » */,
				st._167 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._082 /* 1*MapTypeCS::ownedKeyType=94 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._083 /* 1*MapTypeCS::ownedValueType=94 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._20,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._40) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._40) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._05, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::Model : ownedExpression=ExpCS
		private @NonNull SerializationRule _067 = new SerializationRule(48,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._177 /* check-rule essentialoclcs::ContextCS.ownedExpression : 29 */,
				ms._007 /* assert (|ContextCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._062 /* 1*ContextCS::ownedExpression=29 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NameExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _068 = new SerializationRule(53,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._165 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 67 */,
				ms._167 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 84 */,
				ms._164 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14 */,
				ms._166 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 76 */,
				ms._122 /* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				ms._117 /* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				ms._099 /* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				ms._065 /* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				ms._000 /* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._047 /* 1*AbstractNameExpCS::ownedPathName=67 || «null» */,
				st._153 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=84 || «null» */,
				st._177 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=76 || «null» */,
				st._197 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=14 || «null» */,
				st._208 /* V03*steps-6..8 || «null» */,
				st._015 /* 1*'@' || «? » «value» «? » */,
				st._040 /* 1*'pre' || «? » «value» «? » */
			},
			sl._26,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._04)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._23) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._33) /* SquareBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._30) /* RoundBracketedClauseCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._04, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(84, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(14, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(76, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : ownedNameExpression=NavigatingArgExpCS
		private @NonNull SerializationRule _069 = new SerializationRule(54,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=55 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _070 = new SerializationRule(54,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._031 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=94 || «null» */
			},
			sl._43,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _071 = new SerializationRule(54,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._197 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
				ms._107 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				ms._076 /* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				ms._031 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._138 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=55 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=94 || «null» */,
				st._172 /* V00*steps-5..7 || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._091 /* 1*NavigatingArgCS::ownedCoIterator=4 || «null» */,
				st._195 /* V01*steps-8..10 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=29 || «null» */
			},
			sl._33,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(4, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _072 = new SerializationRule(54,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._197 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
				ms._077 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._028 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=55 || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._091 /* 1*NavigatingArgCS::ownedCoIterator=4 || «null» */,
				st._172 /* V00*steps-5..7 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=29 || «null» */
			},
			sl._32,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(4, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _073 = new SerializationRule(54,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._197 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
				ms._029 /* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				ms._106 /* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				ms._078 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._138 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=55 || «null» */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=94 || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._091 /* 1*NavigatingArgCS::ownedCoIterator=4 || «null» */,
				st._032 /* 1*'in' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=29 || «null» */
			},
			sl._28,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(4, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingBarArgCS : { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _074 = new SerializationRule(56,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._078 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._034 /* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
				ms._107 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._044 /* 1*'|' || «? » «value» «? » */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=55 || «null» */,
				st._171 /* V00*steps-4..9 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=94 || «null» */,
				st._194 /* V01*steps-7..9 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=29 || «null» */
			},
			sl._48,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._09)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._09, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _075 = new SerializationRule(57,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._197 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
				ms._107 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				ms._076 /* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				ms._031 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._139 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=55 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=94 || «null» */,
				st._173 /* V00*steps-6..8 || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._091 /* 1*NavigatingArgCS::ownedCoIterator=4 || «null» */,
				st._196 /* V01*steps-9..11 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=29 || «null» */
			},
			sl._41,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._01, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(4, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _076 = new SerializationRule(57,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._197 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
				ms._077 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._028 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=55 || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._091 /* 1*NavigatingArgCS::ownedCoIterator=4 || «null» */,
				st._173 /* V00*steps-6..8 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=29 || «null» */
			},
			sl._40,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._01, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(4, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _077 = new SerializationRule(57,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._197 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
				ms._029 /* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				ms._106 /* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				ms._078 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._139 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=55 || «null» */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=94 || «null» */,
				st._194 /* V01*steps-7..9 || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._091 /* 1*NavigatingArgCS::ownedCoIterator=4 || «null» */,
				st._032 /* 1*'in' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=29 || «null» */
			},
			sl._39,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._01, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(4, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS }
		private @NonNull SerializationRule _078 = new SerializationRule(57,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=55 || «null» */
			},
			sl._38,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._01, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingSemiArgCS : { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _079 = new SerializationRule(58,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._078 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._033 /* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
				ms._107 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._009 /* 1*';' || «! » «value» «?\n» */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=55 || «null» */,
				st._171 /* V00*steps-4..9 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=94 || «null» */,
				st._194 /* V01*steps-7..9 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=29 || «null» */
			},
			sl._42,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._03)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._03, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(55, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NestedExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _080 = new SerializationRule(60,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._201 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 29 */,
				ms._035 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._095 /* 1*NestedExpCS::ownedExpression=29 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._37,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NullLiteralExpCS : 'null'
		private @NonNull SerializationRule _081 = new SerializationRule(62,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._037 /* 1*'null' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::NumberLiteralExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _082 = new SerializationRule(63,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._036 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._096 /* 1*NumberLiteralExpCS::symbol=52 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::PatternExpCS : { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS }
		private @NonNull SerializationRule _083 = new SerializationRule(68,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._205 /* check-rule essentialoclcs::PatternExpCS.ownedPatternType : 94 */,
				ms._041 /* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
				ms._080 /* assign V0 = |PatternExpCS::patternVariableName| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._156 /* V00*PatternExpCS::patternVariableName=112 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._113 /* 1*PatternExpCS::ownedPatternType=94 || «null» */
			},
			sl._58,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					iv._40) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedLetExpCS : { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		private @NonNull SerializationRule _084 = new SerializationRule(69,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._203 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 69 */,
				ms._037 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._088 /* 1*NamedElementCS::name=109 || «? » «value» «? » */,
				st._102 /* 1*OperatorExpCS::ownedRight=69 || «null» */
			},
			sl._43,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._26) /* PrefixedLetExpCS */
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
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(69, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedPrimaryExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _085 = new SerializationRule(70,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._204 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 70 */,
				ms._037 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._088 /* 1*NamedElementCS::name=109 || «? » «value» «? » */,
				st._103 /* 1*OperatorExpCS::ownedRight=70 || «null» */
			},
			sl._43,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._28) /* PrefixedPrimaryExpCS */
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
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(70, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrimitiveTypeCS : name=PrimitiveTypeIdentifier
		private @NonNull SerializationRule _086 = new SerializationRule(73,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._042 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*PrimitiveTypeRefCS::name=74 || «? » «value» «? » */
			},
			sl._61,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::RoundBracketedClauseCS : { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' }
		private @NonNull SerializationRule _087 = new SerializationRule(76,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._206 /* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 54|56|57|58 */,
				ms._058 /* assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0) */,
				ms._095 /* assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._147 /* 1*steps-1..6 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._116 /* 1*RoundBracketedClauseCS::ownedArguments+=54 || «null» */,
				st._182 /* V01*RoundBracketedClauseCS::ownedArguments+=57|58|56 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._08,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					iv._19) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(54, GrammarCardinality.ZERO_OR_ONE),
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_MORE),
					new RuleIndex_GrammarCardinality(57, GrammarCardinality.ZERO_OR_MORE),
					new RuleIndex_GrammarCardinality(58, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::SelfExpCS : 'self'
		private @NonNull SerializationRule _088 = new SerializationRule(80,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._041 /* 1*'self' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ShadowPartCS : ownedInitExpression=StringLiteralExpCS
		private @NonNull SerializationRule _089 = new SerializationRule(81,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._208 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 86 */,
				ms._043 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._118 /* 1*ShadowPartCS::ownedInitExpression=86 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._34) /* StringLiteralExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(86, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ShadowPartCS : { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) }
		private @NonNull SerializationRule _090 = new SerializationRule(81,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._207 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 29|68 */,
				ms._043 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
				ms._044 /* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._119 /* 1*ShadowPartCS::referredProperty=UnrestrictedName || «? » «value» «? » */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._117 /* 1*ShadowPartCS::ownedInitExpression=29|68 || «null» */
			},
			sl._58,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._25) /* ExpCS|PatternExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE),
					new RuleIndex_GrammarCardinality(68, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				)
			});
		// EssentialOCL::SimplePathNameCS : ownedPathElements+=FirstPathElementCS
		private @NonNull SerializationRule _091 = new SerializationRule(82,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._133 /* check-rule basecs::PathNameCS.ownedPathElements : 30 */,
				ms._039 /* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._110 /* 1*PathNameCS::ownedPathElements+=30 || «null» */
			},
			sl._00,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._11) /* FirstPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::SquareBracketedClauseCS : { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' }
		private @NonNull SerializationRule _092 = new SerializationRule(84,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._209 /* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 29 */,
				ms._059 /* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._121 /* 1*SquareBracketedClauseCS::ownedTerms+=29 || «null» */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._121 /* 1*SquareBracketedClauseCS::ownedTerms+=29 || «null» */,
				st._020 /* 1*']' || «! » «value» */
			},
			sl._09,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::StringLiteralExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _093 = new SerializationRule(86,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._083 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._158 /* V00*StringLiteralExpCS::segments+=85 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE_OR_MORE)
					}
				)
			},
			null);
		// EssentialOCL::TupleLiteralExpCS : { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
		private @NonNull SerializationRule _094 = new SerializationRule(90,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._210 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 91 */,
				ms._062 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._018 /* 1*'Tuple' || «? » «value» «? » */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._125 /* 1*TupleLiteralExpCS::ownedParts+=91 || «null» */,
				st._172 /* V00*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._125 /* 1*TupleLiteralExpCS::ownedParts+=91 || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._56,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._38) /* TupleLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(91, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::TupleLiteralPartCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _095 = new SerializationRule(91,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._216 /* check-rule essentialoclcs::VariableCS.ownedType : 94 */,
				ms._215 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 29 */,
				ms._052 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._088 /* assign V0 = |VariableCS::ownedType| */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._089 /* 1*NamedElementCS::name=112 || «? » «value» «? » */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._136 /* 1*VariableCS::ownedType=94 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._135 /* 1*VariableCS::ownedInitExpression=29 || «null» */
			},
			sl._55,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */
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
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TuplePartCS : { name=UnrestrictedName ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _096 = new SerializationRule(92,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._144 /* check-rule basecs::TypedElementCS.ownedType : 94 */,
				ms._050 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._089 /* 1*NamedElementCS::name=112 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._132 /* 1*TypedElementCS::ownedType=94 || «null» */
			},
			sl._58,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._40) /* TypeExpCS */
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
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TupleTypeCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
		private @NonNull SerializationRule _097 = new SerializationRule(93,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._142 /* check-rule basecs::TupleTypeCS.ownedParts : 92 */,
				ms._047 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._063 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._096 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._116 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._138 /* 1*steps-1..10 || «null» */,
				st._018 /* 1*'Tuple' || «? » «value» «? » */,
				st._163 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._191 /* V01*steps-5..9 || «null» */,
				st._126 /* 1*TupleTypeCS::ownedParts+=92 || «null» */,
				st._201 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._126 /* 1*TupleTypeCS::ownedParts+=92 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._16,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._06)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._39) /* TuplePartCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._06, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _098 = new SerializationRule(94,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._086 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._042 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || «null» */,
				st._114 /* 1*PrimitiveTypeRefCS::name=74 || «? » «value» «? » */,
				st._160 /* V00*TypedRefCS::ownedMultiplicity=50 || «null» */
			},
			sl._15,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _099 = new SerializationRule(94,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._142 /* check-rule basecs::TupleTypeCS.ownedParts : 92 */,
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._125 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._047 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._063 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._096 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._116 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._139 /* 1*steps-1..11 || «null» */,
				st._018 /* 1*'Tuple' || «? » «value» «? » */,
				st._163 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._191 /* V01*steps-5..9 || «null» */,
				st._126 /* 1*TupleTypeCS::ownedParts+=92 || «null» */,
				st._201 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._126 /* 1*TupleTypeCS::ownedParts+=92 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._206 /* V03*TypedRefCS::ownedMultiplicity=50 || «null» */
			},
			sl._17,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._06)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._39) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._06, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _100 = new SerializationRule(94,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._173 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 68 */,
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._174 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 8 */,
				ms._120 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._067 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._091 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._004 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._140 /* 1*steps-1..12 || «null» */,
				st._056 /* 1*CollectionPatternCS::ownedType=8 || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._168 /* V00*steps-4..10 || «null» */,
				st._055 /* 1*CollectionPatternCS::ownedParts+=68 || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._055 /* 1*CollectionPatternCS::ownedParts+=68 || «null» */,
				st._004 /* 1*'++' || «? » «value» «? » */,
				st._057 /* 1*CollectionPatternCS::restVariableName=33 || «? » «value» «? » */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._199 /* V02*TypedRefCS::ownedMultiplicity=50 || «null» */
			},
			sl._07,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._24) /* PatternExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(68, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(8, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _101 = new SerializationRule(94,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._176 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 95 */,
				ms._175 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 50 */,
				ms._120 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._068 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._101 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._058 /* 1*CollectionTypeCS::name=9 || «? » «value» «? » */,
				st._166 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._059 /* 1*CollectionTypeCS::ownedType=95 || «null» */,
				st._179 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=50 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._199 /* V02*TypedRefCS::ownedMultiplicity=50 || «null» */
			},
			sl._19,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._41) /* TypeExpWithoutMultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._16) /* MultiplicityCS */
			},
			new /*@NonNull*/ EAttribute [] {
				EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(95, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _102 = new SerializationRule(94,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._196 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 94 */,
				ms._195 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 94 */,
				ms._112 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._072 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._024 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._023 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || «null» */,
				st._017 /* 1*'Map' || «? » «value» «? » */,
				st._167 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._082 /* 1*MapTypeCS::ownedKeyType=94 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._083 /* 1*MapTypeCS::ownedValueType=94 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._185 /* V01*TypedRefCS::ownedMultiplicity=50 || «null» */
			},
			sl._21,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._40) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._40) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._05, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _103 = new SerializationRule(94,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._213 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 67 */,
				ms._212 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14 */,
				ms._214 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 29 */,
				ms._120 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._085 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._049 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._111 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || «null» */,
				st._129 /* 1*TypeNameExpCS::ownedPathName=67 || «null» */,
				st._167 /* V00*steps-3..8 || «null» */,
				st._128 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=14 || «null» */,
				st._190 /* V01*steps-5..8 || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._130 /* 1*TypeNameExpCS::ownedPatternGuard=29 || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._199 /* V02*TypedRefCS::ownedMultiplicity=50 || «null» */
			},
			sl._02,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._23) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(14, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _104 = new SerializationRule(97,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._211 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 98 */,
				ms._048 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._127 /* 1*TypeLiteralExpCS::ownedType=98 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._43) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(98, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _105 = new SerializationRule(98,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._086 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._042 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || «null» */,
				st._114 /* 1*PrimitiveTypeRefCS::name=74 || «? » «value» «? » */,
				st._160 /* V00*TypedRefCS::ownedMultiplicity=50 || «null» */
			},
			sl._15,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _106 = new SerializationRule(98,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._142 /* check-rule basecs::TupleTypeCS.ownedParts : 92 */,
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._125 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._047 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._063 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._096 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._116 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._139 /* 1*steps-1..11 || «null» */,
				st._018 /* 1*'Tuple' || «? » «value» «? » */,
				st._163 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._191 /* V01*steps-5..9 || «null» */,
				st._126 /* 1*TupleTypeCS::ownedParts+=92 || «null» */,
				st._201 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._126 /* 1*TupleTypeCS::ownedParts+=92 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._206 /* V03*TypedRefCS::ownedMultiplicity=50 || «null» */
			},
			sl._17,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._06)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._39) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._06, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _107 = new SerializationRule(98,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._176 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 95 */,
				ms._175 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 50 */,
				ms._120 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._068 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._101 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._058 /* 1*CollectionTypeCS::name=9 || «? » «value» «? » */,
				st._166 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._059 /* 1*CollectionTypeCS::ownedType=95 || «null» */,
				st._179 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=50 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._199 /* V02*TypedRefCS::ownedMultiplicity=50 || «null» */
			},
			sl._19,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._41) /* TypeExpWithoutMultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._16) /* MultiplicityCS */
			},
			new /*@NonNull*/ EAttribute [] {
				EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(95, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _108 = new SerializationRule(98,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._196 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 94 */,
				ms._195 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 94 */,
				ms._112 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._072 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._024 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._023 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || «null» */,
				st._017 /* 1*'Map' || «? » «value» «? » */,
				st._167 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._082 /* 1*MapTypeCS::ownedKeyType=94 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._083 /* 1*MapTypeCS::ownedValueType=94 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._185 /* V01*TypedRefCS::ownedMultiplicity=50 || «null» */
			},
			sl._21,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._40) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._40) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._05, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(94, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeNameExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
		private @NonNull SerializationRule _109 = new SerializationRule(99,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._213 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 67 */,
				ms._212 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14 */,
				ms._214 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 29 */,
				ms._085 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._049 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._111 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._129 /* 1*TypeNameExpCS::ownedPathName=67 || «null» */,
				st._167 /* V00*steps-3..8 || «null» */,
				st._128 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=14 || «null» */,
				st._190 /* V01*steps-5..8 || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._130 /* 1*TypeNameExpCS::ownedPatternGuard=29 || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._01,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._23) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(67, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(14, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(29, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::URIFirstPathElementCS : referredElement=UnrestrictedName
		private @NonNull SerializationRule _110 = new SerializationRule(107,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._038 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._108 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._61,
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
		// EssentialOCL::URIFirstPathElementCS : referredElement=URI
		private @NonNull SerializationRule _111 = new SerializationRule(107,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._038 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._105 /* 1*PathElementCS::referredElement=URI || «? » «value» «? » */
			},
			sl._61,
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
		// EssentialOCL::URIPathNameCS : { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] }
		private @NonNull SerializationRule _112 = new SerializationRule(108,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._135 /* check-rule basecs::PathNameCS.ownedPathElements : 61|107 */,
				ms._057 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || «null» */,
				st._109 /* 1*PathNameCS::ownedPathElements+=107 || «null» */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'::' || «! » «value» «! » */,
				st._111 /* 1*PathNameCS::ownedPathElements+=61 || «null» */
			},
			sl._03,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._51) /* NextPathElementCS|URIFirstPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(61, GrammarCardinality.ZERO_OR_MORE),
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS : '*'
		private @NonNull SerializationRule _113 = new SerializationRule(110,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._003 /* 1*'*' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
	}

	private _EClassValues ec;
	private _EnumValues ev;
	private _GrammarRuleVectors iv;
	private _MatchSteps ms;
	private _MatchTerms mt;
	private _SerializationSegmentsLists sl;
	private _SerializationSegments ss;
	private _SerializationRules0 sr0;
	private _SerializationRules1 sr1;
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
		sr1 = new _SerializationRules1();
		gr = new _GrammarRuleValues();
		ec = new _EClassValues();
	}

}
//	Commented imports ensure Xtend provides a true import allowing unqualified annotated usage
//	import Inject;
//	import EAttribute;
//	import NonNull;
//	import Nullable;
//	import BaseCommentSegmentSupport;
//	import IdiomsUtils;
//	import Segment;
//	import DataTypeRuleValue;
//	import EClassValue;
//	import SerializationRule_SegmentsList;
//	import EnumerationValue;
//	import EnumerationValueMultiple;
//	import EnumerationValueSingle;
//	import GrammarCardinality;
//	import GrammarRuleValue;
//	import GrammarRuleVector;
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
//	import SerializationMatchTermVariable;
//	import SerializationRule;
//	import EAttribute_EnumerationValue_GrammarCardinality;
//	import EAttribute_EnumerationValues;
//	import EReference_RuleIndex_GrammarCardinality;
//	import EReference_RuleIndexes;
//	import EnumerationValue_GrammarCardinality;
//	import RuleIndex_GrammarCardinality;
//	import SerializationStep;
//	import SerializationStepAssignKeyword;
//	import SerializationStepAssignedRuleCall;
//	import SerializationStepAssigns;
//	import SerializationStepCrossReference;
//	import SerializationStepLiteral;
//	import SerializationStepSequence;
//	import TerminalRuleValue;
//	import BaseCSPackage;
//	import CompleteOCLCSPackage;
//	import EssentialOCLCSPackage;
