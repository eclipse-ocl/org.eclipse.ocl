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
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep;
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
import org.eclipse.ocl.xtext.base.cs2text.solutions.VariableCardinalitySolution;
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
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;

public class CompleteOCLAnalysisProvider extends AbstractAnalysisProvider
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
				new AbstractRuleValue [] {
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
	private class _IndexVectors
	{
		private final @NonNull IndexVector _0 // CoIteratorVariableCS
			= new IndexVector(0x10L);
		private final @NonNull IndexVector _1 // CollectionLiteralPartCS
			= new IndexVector(0x40L);
		private final @NonNull IndexVector _2 // CollectionTypeCS
			= new IndexVector(0x100L);
		private final @NonNull IndexVector _3 // ConstraintCS
			= new IndexVector(0x1000L);
		private final @NonNull IndexVector _4 // ContextDeclCS
			= new IndexVector(0x2000L);
		private final @NonNull IndexVector _5 // CurlyBracketedClauseCS
			= new IndexVector(0x4000L);
		private final @NonNull IndexVector _6 // DefCS
			= new IndexVector(0x10000L);
		private final @NonNull IndexVector _7 // DefParameterCS
			= new IndexVector(0x40000L);
		private final @NonNull IndexVector _8 // DefCS|DefOperationCS|DefPropertyCS
			= new IndexVector(0xb0000L);
		private final @NonNull IndexVector _9 // ElseIfThenExpCS
			= new IndexVector(0x400000L);
		private final @NonNull IndexVector _10 // ExpCS
			= new IndexVector(0x20000000L);
		private final @NonNull IndexVector _11 // FirstPathElementCS
			= new IndexVector(0x40000000L);
		private final @NonNull IndexVector _12 // ImportCS
			= new IndexVector(0x800000000L);
		private final @NonNull IndexVector _13 // LetVariableCS
			= new IndexVector(0x40000000000L);
		private final @NonNull IndexVector _14 // MapLiteralPartCS
			= new IndexVector(0x400000000000L);
		private final @NonNull IndexVector _15 // MapTypeCS
			= new IndexVector(0x800000000000L);
		private final @NonNull IndexVector _16 // MultiplicityCS
			= new IndexVector(0x4000000000000L);
		private final @NonNull IndexVector _17 // NavigatingArgExpCS
			= new IndexVector(0x80000000000000L);
		private final @NonNull IndexVector _18 // NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
			= new IndexVector(0x740000000000000L);
		private final @NonNull IndexVector _19 // FirstPathElementCS|NextPathElementCS
			= new IndexVector(0x2000000040000000L);
		private final @NonNull IndexVector _20 // PackageDeclarationCS
			= new IndexVector(0x0L,0x2L);
		private final @NonNull IndexVector _21 // ParameterCS
			= new IndexVector(0x0L,0x4L);
		private final @NonNull IndexVector _22 // PathNameCS
			= new IndexVector(0x0L,0x8L);
		private final @NonNull IndexVector _23 // PatternExpCS
			= new IndexVector(0x0L,0x10L);
		private final @NonNull IndexVector _24 // ExpCS|PatternExpCS
			= new IndexVector(0x20000000L,0x10L);
		private final @NonNull IndexVector _25 // PrefixedLetExpCS
			= new IndexVector(0x0L,0x20L);
		private final @NonNull IndexVector _26 // LetExpCS|PrefixedLetExpCS
			= new IndexVector(0x20000000000L,0x20L);
		private final @NonNull IndexVector _27 // PrefixedPrimaryExpCS
			= new IndexVector(0x0L,0x40L);
		private final @NonNull IndexVector _28 // ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS
			= new IndexVector(0x2008L,0x801L);
		private final @NonNull IndexVector _29 // RoundBracketedClauseCS
			= new IndexVector(0x0L,0x1000L);
		private final @NonNull IndexVector _30 // ShadowPartCS
			= new IndexVector(0x0L,0x20000L);
		private final @NonNull IndexVector _31 // SpecificationCS
			= new IndexVector(0x0L,0x80000L);
		private final @NonNull IndexVector _32 // SquareBracketedClauseCS
			= new IndexVector(0x0L,0x100000L);
		private final @NonNull IndexVector _33 // StringLiteralExpCS
			= new IndexVector(0x0L,0x400000L);
		private final @NonNull IndexVector _34 // TemplateBindingCS
			= new IndexVector(0x0L,0x800000L);
		private final @NonNull IndexVector _35 // TemplateParameterSubstitutionCS
			= new IndexVector(0x0L,0x1000000L);
		private final @NonNull IndexVector _36 // TemplateSignatureCS
			= new IndexVector(0x0L,0x2000000L);
		private final @NonNull IndexVector _37 // TupleLiteralPartCS
			= new IndexVector(0x0L,0x8000000L);
		private final @NonNull IndexVector _38 // TuplePartCS
			= new IndexVector(0x0L,0x10000000L);
		private final @NonNull IndexVector _39 // TypeExpCS
			= new IndexVector(0x0L,0x40000000L);
		private final @NonNull IndexVector _40 // TypeExpWithoutMultiplicityCS
			= new IndexVector(0x0L,0x80000000L);
		private final @NonNull IndexVector _41 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
			= new IndexVector(0x800000000100L,0x120000200L);
		private final @NonNull IndexVector _42 // TypeLiteralWithMultiplicityCS
			= new IndexVector(0x0L,0x400000000L);
		private final @NonNull IndexVector _43 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS
			= new IndexVector(0x800000000100L,0x520000200L);
		private final @NonNull IndexVector _44 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new IndexVector(0x800000000180L,0x9a0000200L);
		private final @NonNull IndexVector _45 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new IndexVector(0x800000000180L,0x9e0000200L);
		private final @NonNull IndexVector _46 // TypeParameterCS
			= new IndexVector(0x0L,0x1000000000L);
		private final @NonNull IndexVector _47 // TypeRefCS
			= new IndexVector(0x0L,0x2000000000L);
		private final @NonNull IndexVector _48 // TypedRefCS
			= new IndexVector(0x0L,0x4000000000L);
		private final @NonNull IndexVector _49 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS
			= new IndexVector(0x800000000100L,0xc120000200L);
		private final @NonNull IndexVector _50 // NextPathElementCS|URIFirstPathElementCS
			= new IndexVector(0x2000000000000000L,0x80000000000L);
		private final @NonNull IndexVector _51 // FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
			= new IndexVector(0x2000000040000000L,0x80000000000L);
		private final @NonNull IndexVector _52 // URIPathNameCS
			= new IndexVector(0x0L,0x100000000000L);
		private final @NonNull IndexVector _53 // BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0xc000002000000004L,0x400000400100L);
		private final @NonNull IndexVector _54 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0xd020212400000024L,0x400204410180L);
		private final @NonNull IndexVector _55 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0xd020212400000024L,0x4002044101c0L);
		private final @NonNull IndexVector _56 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0xd020232400000024L,0x4002044101e0L);
		private final @NonNull IndexVector _57 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0xd020232420000024L,0x4002044101e0L);
		private final @NonNull IndexVector _58 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0xd0a0232420000024L,0x4002044101e0L);
		private final @NonNull IndexVector _59 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0xd020232420000024L,0x4002044101f0L);
		private final @NonNull IndexVector _60 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
			= new IndexVector(0x800000000100L,0x400e120000200L);
	}

	/**
	 * String combinations used by assigned String EAttributes
	 */
	private class _EnumValues
	{
		private final @NonNull EnumerationValue _00 // '*|+|?'
			= new MultipleEnumerationValue(new @NonNull String[]{"*", "+", "?"});
		private final @NonNull EnumerationValue _01 // ','
			= new SingleEnumerationValue(",");
		private final @NonNull EnumerationValue _02 // '::*'
			= new SingleEnumerationValue("::*");
		private final @NonNull EnumerationValue _03 // ';'
			= new SingleEnumerationValue(";");
		private final @NonNull EnumerationValue _04 // '@'
			= new SingleEnumerationValue("@");
		private final @NonNull EnumerationValue _05 // 'Map'
			= new SingleEnumerationValue("Map");
		private final @NonNull EnumerationValue _06 // 'Tuple'
			= new SingleEnumerationValue("Tuple");
		private final @NonNull EnumerationValue _07 // 'false|true'
			= new MultipleEnumerationValue(new @NonNull String[]{"false", "true"});
		private final @NonNull EnumerationValue _08 // 'static'
			= new SingleEnumerationValue("static");
		private final @NonNull EnumerationValue _09 // '|'
			= new SingleEnumerationValue("|");
		private final @NonNull EnumerationValue _10 // '|1'
			= new SingleEnumerationValue("|1");
	}

	/**
	 * Expression terms used during the matching process.
	 */
	private class _MatchTerms
	{
		private final @NonNull CardinalitySolution _000 // 0
			= new IntegerCardinalitySolution(0);
		private final @NonNull CardinalitySolution _001 // 1
			= new IntegerCardinalitySolution(1);
		private final @NonNull CardinalitySolution _002 // V0
			= new VariableCardinalitySolution(0);
		private final @NonNull CardinalitySolution _003 // |AbstractNameExpCS::isPre.'@'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, ev._04);
		private final @NonNull CardinalitySolution _004 // |AbstractNameExpCS::ownedCurlyBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _005 // |AbstractNameExpCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _006 // |AbstractNameExpCS::ownedRoundBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _007 // |AbstractNameExpCS::ownedSquareBracketedClauses|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		private final @NonNull CardinalitySolution _008 // |BooleanLiteralExpCS::symbol.'false|true'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._07);
		private final @NonNull CardinalitySolution _009 // |ClassifierContextDeclCS::ownedDefinitions|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS);
		private final @NonNull CardinalitySolution _010 // |ClassifierContextDeclCS::ownedInvariants|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS);
		private final @NonNull CardinalitySolution _011 // |ClassifierContextDeclCS::selfName|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME);
		private final @NonNull CardinalitySolution _012 // |CollectionLiteralExpCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _013 // |CollectionLiteralExpCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _014 // |CollectionLiteralPartCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _015 // |CollectionLiteralPartCS::ownedLastExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		private final @NonNull CardinalitySolution _016 // |CollectionPatternCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _017 // |CollectionPatternCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _018 // |CollectionPatternCS::restVariableName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		private final @NonNull CardinalitySolution _019 // |CollectionTypeCS::name|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		private final @NonNull CardinalitySolution _020 // |CollectionTypeCS::ownedCollectionMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		private final @NonNull CardinalitySolution _021 // |CollectionTypeCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _022 // |CompleteOCLDocumentCS::ownedContexts|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS);
		private final @NonNull CardinalitySolution _023 // |CompleteOCLDocumentCS::ownedPackages|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES);
		private final @NonNull CardinalitySolution _024 // |ConstraintCS::ownedMessageSpecification|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		private final @NonNull CardinalitySolution _025 // |ConstraintCS::ownedSpecification|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		private final @NonNull CardinalitySolution _026 // |ContextCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _027 // |CurlyBracketedClauseCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _028 // |DefCS::isStatic.'static'|
			= new EAttributeSizeCardinalitySolution(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC, ev._08);
		private final @NonNull CardinalitySolution _029 // |DefCS::ownedSpecification|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION);
		private final @NonNull CardinalitySolution _030 // |DefOperationCS::ownedParameters|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS);
		private final @NonNull CardinalitySolution _031 // |ExpSpecificationCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _032 // |FeatureContextDeclCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _033 // |IfExpCS::ownedCondition|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		private final @NonNull CardinalitySolution _034 // |IfExpCS::ownedElseExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		private final @NonNull CardinalitySolution _035 // |IfExpCS::ownedIfThenExpressions|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		private final @NonNull CardinalitySolution _036 // |IfExpCS::ownedThenExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull CardinalitySolution _037 // |IfThenExpCS::ownedCondition|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		private final @NonNull CardinalitySolution _038 // |IfThenExpCS::ownedThenExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull CardinalitySolution _039 // |ImportCS::isAll.'::*'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, ev._02);
		private final @NonNull CardinalitySolution _040 // |ImportCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _041 // |InfixExpCS::ownedLeft|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		private final @NonNull CardinalitySolution _042 // |LambdaLiteralExpCS::ownedExpressionCS|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		private final @NonNull CardinalitySolution _043 // |LetExpCS::ownedInExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		private final @NonNull CardinalitySolution _044 // |LetExpCS::ownedVariables|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		private final @NonNull CardinalitySolution _045 // |LetVariableCS::ownedRoundBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _046 // |MapLiteralExpCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _047 // |MapLiteralExpCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _048 // |MapLiteralPartCS::ownedKey|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		private final @NonNull CardinalitySolution _049 // |MapLiteralPartCS::ownedValue|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		private final @NonNull CardinalitySolution _050 // |MapTypeCS::name.'Map'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, ev._05);
		private final @NonNull CardinalitySolution _051 // |MapTypeCS::ownedKeyType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		private final @NonNull CardinalitySolution _052 // |MapTypeCS::ownedValueType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		private final @NonNull CardinalitySolution _053 // |MultiplicityBoundsCS::lowerBound|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull CardinalitySolution _054 // |MultiplicityBoundsCS::upperBound|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull CardinalitySolution _055 // |MultiplicityCS::isNullFree.'|1'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, ev._10);
		private final @NonNull CardinalitySolution _056 // |MultiplicityStringCS::stringBounds.'*|+|?'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._00);
		private final @NonNull CardinalitySolution _057 // |NamedElementCS::name|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull CardinalitySolution _058 // |NavigatingArgCS::ownedCoIterator|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		private final @NonNull CardinalitySolution _059 // |NavigatingArgCS::ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _060 // |NavigatingArgCS::ownedNameExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		private final @NonNull CardinalitySolution _061 // |NavigatingArgCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _062 // |NavigatingArgCS::prefix.','|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._01);
		private final @NonNull CardinalitySolution _063 // |NavigatingArgCS::prefix.';'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._03);
		private final @NonNull CardinalitySolution _064 // |NavigatingArgCS::prefix.'|'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._09);
		private final @NonNull CardinalitySolution _065 // |NestedExpCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _066 // |NumberLiteralExpCS::symbol|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull CardinalitySolution _067 // |OperationContextDeclCS::ownedBodies|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES);
		private final @NonNull CardinalitySolution _068 // |OperationContextDeclCS::ownedParameters|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS);
		private final @NonNull CardinalitySolution _069 // |OperationContextDeclCS::ownedPostconditions|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS);
		private final @NonNull CardinalitySolution _070 // |OperationContextDeclCS::ownedPreconditions|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS);
		private final @NonNull CardinalitySolution _071 // |OperatorExpCS::ownedRight|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull CardinalitySolution _072 // |PackageDeclarationCS::ownedContexts|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS);
		private final @NonNull CardinalitySolution _073 // |PackageDeclarationCS::ownedInvariants|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS);
		private final @NonNull CardinalitySolution _074 // |PathElementCS::referredElement|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private final @NonNull CardinalitySolution _075 // |PathNameCS::ownedPathElements|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull CardinalitySolution _076 // |PathNameDeclCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _077 // |PatternExpCS::ownedPatternType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		private final @NonNull CardinalitySolution _078 // |PatternExpCS::patternVariableName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		private final @NonNull CardinalitySolution _079 // |PrimitiveTypeRefCS::name|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		private final @NonNull CardinalitySolution _080 // |PropertyContextDeclCS::ownedDefaultExpressions|
			= new EStructuralFeatureSizeCardinalitySolution(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull CardinalitySolution _081 // |RootCS::ownedImports|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		private final @NonNull CardinalitySolution _082 // |RoundBracketedClauseCS::ownedArguments|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		private final @NonNull CardinalitySolution _083 // |ShadowPartCS::ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _084 // |ShadowPartCS::referredProperty|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		private final @NonNull CardinalitySolution _085 // |SpecificationCS::exprString|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		private final @NonNull CardinalitySolution _086 // |SquareBracketedClauseCS::ownedTerms|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		private final @NonNull CardinalitySolution _087 // |StringLiteralExpCS::segments|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		private final @NonNull CardinalitySolution _088 // |TemplateBindingCS::ownedMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull CardinalitySolution _089 // |TemplateBindingCS::ownedSubstitutions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull CardinalitySolution _090 // |TemplateParameterSubstitutionCS::ownedActualParameter|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull CardinalitySolution _091 // |TemplateSignatureCS::ownedParameters|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull CardinalitySolution _092 // |TemplateableElementCS::ownedSignature|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		private final @NonNull CardinalitySolution _093 // |TupleLiteralExpCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _094 // |TupleTypeCS::name.'Tuple'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, ev._06);
		private final @NonNull CardinalitySolution _095 // |TupleTypeCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _096 // |TypeLiteralExpCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _097 // |TypeNameExpCS::ownedCurlyBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _098 // |TypeNameExpCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _099 // |TypeNameExpCS::ownedPatternGuard|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		private final @NonNull CardinalitySolution _100 // |TypeParameterCS::ownedExtends|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull CardinalitySolution _101 // |TypedElementCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _102 // |TypedRefCS::ownedMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull CardinalitySolution _103 // |TypedTypeRefCS::ownedBinding|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull CardinalitySolution _104 // |TypedTypeRefCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _105 // |VariableCS::ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _106 // |VariableCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _107 // |WildcardTypeRefCS::ownedExtends|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull CardinalitySolution _108 // (|AbstractNameExpCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_005, _001);
		private final @NonNull CardinalitySolution _109 // (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
			= new SubtractCardinalitySolution(_008, _001);
		private final @NonNull CardinalitySolution _110 // (|CollectionLiteralExpCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_012, _001);
		private final @NonNull CardinalitySolution _111 // (|CollectionLiteralExpCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_012, _000);
		private final @NonNull CardinalitySolution _112 // (|CollectionLiteralExpCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_013, _001);
		private final @NonNull CardinalitySolution _113 // (|CollectionLiteralPartCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_014, _001);
		private final @NonNull CardinalitySolution _114 // (|CollectionPatternCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_016, _001);
		private final @NonNull CardinalitySolution _115 // (|CollectionPatternCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_017, _001);
		private final @NonNull CardinalitySolution _116 // (|CollectionTypeCS::name| - 1)
			= new SubtractCardinalitySolution(_019, _001);
		private final @NonNull CardinalitySolution _117 // (|ConstraintCS::ownedSpecification| - 1)
			= new SubtractCardinalitySolution(_025, _001);
		private final @NonNull CardinalitySolution _118 // (|ContextCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_026, _001);
		private final @NonNull CardinalitySolution _119 // (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_027, _001);
		private final @NonNull CardinalitySolution _120 // (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_027, _000);
		private final @NonNull CardinalitySolution _121 // (|DefCS::ownedSpecification| - 1)
			= new SubtractCardinalitySolution(_029, _001);
		private final @NonNull CardinalitySolution _122 // (|DefOperationCS::ownedParameters| - 1)
			= new SubtractCardinalitySolution(_030, _001);
		private final @NonNull CardinalitySolution _123 // (|DefOperationCS::ownedParameters| > 0)
			= new GreaterThanCardinalitySolution(_030, _000);
		private final @NonNull CardinalitySolution _124 // (|ExpSpecificationCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_031, _001);
		private final @NonNull CardinalitySolution _125 // (|FeatureContextDeclCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_032, _001);
		private final @NonNull CardinalitySolution _126 // (|IfExpCS::ownedCondition| - 1)
			= new SubtractCardinalitySolution(_033, _001);
		private final @NonNull CardinalitySolution _127 // (|IfExpCS::ownedElseExpression| - 1)
			= new SubtractCardinalitySolution(_034, _001);
		private final @NonNull CardinalitySolution _128 // (|IfExpCS::ownedThenExpression| - 1)
			= new SubtractCardinalitySolution(_036, _001);
		private final @NonNull CardinalitySolution _129 // (|IfThenExpCS::ownedCondition| - 1)
			= new SubtractCardinalitySolution(_037, _001);
		private final @NonNull CardinalitySolution _130 // (|IfThenExpCS::ownedThenExpression| - 1)
			= new SubtractCardinalitySolution(_038, _001);
		private final @NonNull CardinalitySolution _131 // (|ImportCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_040, _001);
		private final @NonNull CardinalitySolution _132 // (|InfixExpCS::ownedLeft| - 1)
			= new SubtractCardinalitySolution(_041, _001);
		private final @NonNull CardinalitySolution _133 // (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
			= new SubtractCardinalitySolution(_042, _001);
		private final @NonNull CardinalitySolution _134 // (|LetExpCS::ownedInExpression| - 1)
			= new SubtractCardinalitySolution(_043, _001);
		private final @NonNull CardinalitySolution _135 // (|LetExpCS::ownedVariables| - 1)
			= new SubtractCardinalitySolution(_044, _001);
		private final @NonNull CardinalitySolution _136 // (|MapLiteralExpCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_046, _001);
		private final @NonNull CardinalitySolution _137 // (|MapLiteralExpCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_046, _000);
		private final @NonNull CardinalitySolution _138 // (|MapLiteralExpCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_047, _001);
		private final @NonNull CardinalitySolution _139 // (|MapLiteralPartCS::ownedKey| - 1)
			= new SubtractCardinalitySolution(_048, _001);
		private final @NonNull CardinalitySolution _140 // (|MapLiteralPartCS::ownedValue| - 1)
			= new SubtractCardinalitySolution(_049, _001);
		private final @NonNull CardinalitySolution _141 // (|MapTypeCS::name.'Map'| - 1)
			= new SubtractCardinalitySolution(_050, _001);
		private final @NonNull CardinalitySolution _142 // (|MapTypeCS::ownedKeyType| - V0)
			= new SubtractCardinalitySolution(_051, _002);
		private final @NonNull CardinalitySolution _143 // (|MultiplicityBoundsCS::lowerBound| - 1)
			= new SubtractCardinalitySolution(_053, _001);
		private final @NonNull CardinalitySolution _144 // (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
			= new SubtractCardinalitySolution(_056, _001);
		private final @NonNull CardinalitySolution _145 // (|NamedElementCS::name| - 1)
			= new SubtractCardinalitySolution(_057, _001);
		private final @NonNull CardinalitySolution _146 // (|NavigatingArgCS::ownedCoIterator| - 1)
			= new SubtractCardinalitySolution(_058, _001);
		private final @NonNull CardinalitySolution _147 // (|NavigatingArgCS::ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_059, _001);
		private final @NonNull CardinalitySolution _148 // (|NavigatingArgCS::ownedNameExpression| - 1)
			= new SubtractCardinalitySolution(_060, _001);
		private final @NonNull CardinalitySolution _149 // (|NavigatingArgCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_061, _001);
		private final @NonNull CardinalitySolution _150 // (|NavigatingArgCS::prefix.','| - 1)
			= new SubtractCardinalitySolution(_062, _001);
		private final @NonNull CardinalitySolution _151 // (|NavigatingArgCS::prefix.';'| - 1)
			= new SubtractCardinalitySolution(_063, _001);
		private final @NonNull CardinalitySolution _152 // (|NavigatingArgCS::prefix.'|'| - 1)
			= new SubtractCardinalitySolution(_064, _001);
		private final @NonNull CardinalitySolution _153 // (|NestedExpCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_065, _001);
		private final @NonNull CardinalitySolution _154 // (|NumberLiteralExpCS::symbol| - 1)
			= new SubtractCardinalitySolution(_066, _001);
		private final @NonNull CardinalitySolution _155 // (|OperationContextDeclCS::ownedParameters| - 1)
			= new SubtractCardinalitySolution(_068, _001);
		private final @NonNull CardinalitySolution _156 // (|OperationContextDeclCS::ownedParameters| > 0)
			= new GreaterThanCardinalitySolution(_068, _000);
		private final @NonNull CardinalitySolution _157 // (|OperatorExpCS::ownedRight| - 1)
			= new SubtractCardinalitySolution(_071, _001);
		private final @NonNull CardinalitySolution _158 // (|PathElementCS::referredElement| - 1)
			= new SubtractCardinalitySolution(_074, _001);
		private final @NonNull CardinalitySolution _159 // (|PathNameCS::ownedPathElements| - 1)
			= new SubtractCardinalitySolution(_075, _001);
		private final @NonNull CardinalitySolution _160 // (|PathNameDeclCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_076, _001);
		private final @NonNull CardinalitySolution _161 // (|PatternExpCS::ownedPatternType| - 1)
			= new SubtractCardinalitySolution(_077, _001);
		private final @NonNull CardinalitySolution _162 // (|PrimitiveTypeRefCS::name| - 1)
			= new SubtractCardinalitySolution(_079, _001);
		private final @NonNull CardinalitySolution _163 // (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new SubtractCardinalitySolution(_082, _001);
		private final @NonNull CardinalitySolution _164 // (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new GreaterThanCardinalitySolution(_082, _000);
		private final @NonNull CardinalitySolution _165 // (|ShadowPartCS::ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_083, _001);
		private final @NonNull CardinalitySolution _166 // (|ShadowPartCS::referredProperty| - 1)
			= new SubtractCardinalitySolution(_084, _001);
		private final @NonNull CardinalitySolution _167 // (|SpecificationCS::exprString| - 1)
			= new SubtractCardinalitySolution(_085, _001);
		private final @NonNull CardinalitySolution _168 // (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new SubtractCardinalitySolution(_086, _001);
		private final @NonNull CardinalitySolution _169 // (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new SubtractCardinalitySolution(_089, _001);
		private final @NonNull CardinalitySolution _170 // (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
			= new SubtractCardinalitySolution(_090, _001);
		private final @NonNull CardinalitySolution _171 // (|TemplateSignatureCS::ownedParameters| - 1)
			= new SubtractCardinalitySolution(_091, _001);
		private final @NonNull CardinalitySolution _172 // (|TupleLiteralExpCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_093, _001);
		private final @NonNull CardinalitySolution _173 // (|TupleTypeCS::name.'Tuple'| - 1)
			= new SubtractCardinalitySolution(_094, _001);
		private final @NonNull CardinalitySolution _174 // (|TupleTypeCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_095, _001);
		private final @NonNull CardinalitySolution _175 // (|TupleTypeCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_095, _000);
		private final @NonNull CardinalitySolution _176 // (|TypeLiteralExpCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_096, _001);
		private final @NonNull CardinalitySolution _177 // (|TypeNameExpCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_098, _001);
		private final @NonNull CardinalitySolution _178 // (|TypeParameterCS::ownedExtends| - 1)
			= new SubtractCardinalitySolution(_100, _001);
		private final @NonNull CardinalitySolution _179 // (|TypeParameterCS::ownedExtends| > 0)
			= new GreaterThanCardinalitySolution(_100, _000);
		private final @NonNull CardinalitySolution _180 // (|TypedElementCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_101, _001);
		private final @NonNull CardinalitySolution _181 // (|TypedTypeRefCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_104, _001);
		private final @NonNull CardinalitySolution _182 // (|VariableCS::ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_105, _001);
	}

	/**
	 * Steps for the matching process.
	 */
	private class _MatchSteps
	{
		private final @NonNull CardinalitySolutionStep _000 // assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._108);
		private final @NonNull CardinalitySolutionStep _001 // assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._109);
		private final @NonNull CardinalitySolutionStep _002 // assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._112);
		private final @NonNull CardinalitySolutionStep _003 // assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._113);
		private final @NonNull CardinalitySolutionStep _004 // assert (|CollectionPatternCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._115);
		private final @NonNull CardinalitySolutionStep _005 // assert (|CollectionTypeCS::name| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._116);
		private final @NonNull CardinalitySolutionStep _006 // assert (|ConstraintCS::ownedSpecification| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._117);
		private final @NonNull CardinalitySolutionStep _007 // assert (|ContextCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._118);
		private final @NonNull CardinalitySolutionStep _008 // assert (|DefCS::ownedSpecification| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._121);
		private final @NonNull CardinalitySolutionStep _009 // assert (|ExpSpecificationCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._124);
		private final @NonNull CardinalitySolutionStep _010 // assert (|FeatureContextDeclCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._125);
		private final @NonNull CardinalitySolutionStep _011 // assert (|IfExpCS::ownedCondition| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._126);
		private final @NonNull CardinalitySolutionStep _012 // assert (|IfExpCS::ownedElseExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._127);
		private final @NonNull CardinalitySolutionStep _013 // assert (|IfExpCS::ownedThenExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._128);
		private final @NonNull CardinalitySolutionStep _014 // assert (|IfThenExpCS::ownedCondition| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._129);
		private final @NonNull CardinalitySolutionStep _015 // assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._130);
		private final @NonNull CardinalitySolutionStep _016 // assert (|ImportCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._131);
		private final @NonNull CardinalitySolutionStep _017 // assert (|InfixExpCS::ownedLeft| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._132);
		private final @NonNull CardinalitySolutionStep _018 // assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._133);
		private final @NonNull CardinalitySolutionStep _019 // assert (|LetExpCS::ownedInExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._134);
		private final @NonNull CardinalitySolutionStep _020 // assert (|MapLiteralExpCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._138);
		private final @NonNull CardinalitySolutionStep _021 // assert (|MapLiteralPartCS::ownedKey| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._139);
		private final @NonNull CardinalitySolutionStep _022 // assert (|MapLiteralPartCS::ownedValue| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._140);
		private final @NonNull CardinalitySolutionStep _023 // assert (|MapTypeCS::name.'Map'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._141);
		private final @NonNull CardinalitySolutionStep _024 // assert (|MapTypeCS::ownedKeyType| - V0) == 0
			= new CardinalitySolutionStep_Assert(mt._142);
		private final @NonNull CardinalitySolutionStep _025 // assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._143);
		private final @NonNull CardinalitySolutionStep _026 // assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._144);
		private final @NonNull CardinalitySolutionStep _027 // assert (|NamedElementCS::name| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._145);
		private final @NonNull CardinalitySolutionStep _028 // assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._146);
		private final @NonNull CardinalitySolutionStep _029 // assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._147);
		private final @NonNull CardinalitySolutionStep _030 // assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._148);
		private final @NonNull CardinalitySolutionStep _031 // assert (|NavigatingArgCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._149);
		private final @NonNull CardinalitySolutionStep _032 // assert (|NavigatingArgCS::prefix.','| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._150);
		private final @NonNull CardinalitySolutionStep _033 // assert (|NavigatingArgCS::prefix.';'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._151);
		private final @NonNull CardinalitySolutionStep _034 // assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._152);
		private final @NonNull CardinalitySolutionStep _035 // assert (|NestedExpCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._153);
		private final @NonNull CardinalitySolutionStep _036 // assert (|NumberLiteralExpCS::symbol| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._154);
		private final @NonNull CardinalitySolutionStep _037 // assert (|OperatorExpCS::ownedRight| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._157);
		private final @NonNull CardinalitySolutionStep _038 // assert (|PathElementCS::referredElement| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._158);
		private final @NonNull CardinalitySolutionStep _039 // assert (|PathNameCS::ownedPathElements| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._159);
		private final @NonNull CardinalitySolutionStep _040 // assert (|PathNameDeclCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._160);
		private final @NonNull CardinalitySolutionStep _041 // assert (|PatternExpCS::ownedPatternType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._161);
		private final @NonNull CardinalitySolutionStep _042 // assert (|PrimitiveTypeRefCS::name| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._162);
		private final @NonNull CardinalitySolutionStep _043 // assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._165);
		private final @NonNull CardinalitySolutionStep _044 // assert (|ShadowPartCS::referredProperty| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._166);
		private final @NonNull CardinalitySolutionStep _045 // assert (|SpecificationCS::exprString| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._167);
		private final @NonNull CardinalitySolutionStep _046 // assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._170);
		private final @NonNull CardinalitySolutionStep _047 // assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._173);
		private final @NonNull CardinalitySolutionStep _048 // assert (|TypeLiteralExpCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._176);
		private final @NonNull CardinalitySolutionStep _049 // assert (|TypeNameExpCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._177);
		private final @NonNull CardinalitySolutionStep _050 // assert (|TypedElementCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._180);
		private final @NonNull CardinalitySolutionStep _051 // assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._181);
		private final @NonNull CardinalitySolutionStep _052 // assert (|VariableCS::ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._182);
		private final @NonNull CardinalitySolutionStep _053 // assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._111);
		private final @NonNull CardinalitySolutionStep _054 // assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._120);
		private final @NonNull CardinalitySolutionStep _055 // assign V0 = (|LetExpCS::ownedVariables| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._135);
		private final @NonNull CardinalitySolutionStep _056 // assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._137);
		private final @NonNull CardinalitySolutionStep _057 // assign V0 = (|PathNameCS::ownedPathElements| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._159);
		private final @NonNull CardinalitySolutionStep _058 // assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._164);
		private final @NonNull CardinalitySolutionStep _059 // assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._168);
		private final @NonNull CardinalitySolutionStep _060 // assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._169);
		private final @NonNull CardinalitySolutionStep _061 // assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._171);
		private final @NonNull CardinalitySolutionStep _062 // assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._172);
		private final @NonNull CardinalitySolutionStep _063 // assign V0 = (|TupleTypeCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._175);
		private final @NonNull CardinalitySolutionStep _064 // assign V0 = (|TypeParameterCS::ownedExtends| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._179);
		private final @NonNull CardinalitySolutionStep _065 // assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
			= new CardinalitySolutionStep_Assign(0, mt._007);
		private final @NonNull CardinalitySolutionStep _066 // assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
			= new CardinalitySolutionStep_Assign(0, mt._015);
		private final @NonNull CardinalitySolutionStep _067 // assign V0 = |CollectionPatternCS::restVariableName|
			= new CardinalitySolutionStep_Assign(0, mt._018);
		private final @NonNull CardinalitySolutionStep _068 // assign V0 = |CollectionTypeCS::ownedType|
			= new CardinalitySolutionStep_Assign(0, mt._021);
		private final @NonNull CardinalitySolutionStep _069 // assign V0 = |DefCS::isStatic.'static'|
			= new CardinalitySolutionStep_Assign(0, mt._028);
		private final @NonNull CardinalitySolutionStep _070 // assign V0 = |IfExpCS::ownedIfThenExpressions|
			= new CardinalitySolutionStep_Assign(0, mt._035);
		private final @NonNull CardinalitySolutionStep _071 // assign V0 = |LetVariableCS::ownedRoundBracketedClause|
			= new CardinalitySolutionStep_Assign(0, mt._045);
		private final @NonNull CardinalitySolutionStep _072 // assign V0 = |MapTypeCS::ownedValueType|
			= new CardinalitySolutionStep_Assign(0, mt._052);
		private final @NonNull CardinalitySolutionStep _073 // assign V0 = |MultiplicityBoundsCS::upperBound|
			= new CardinalitySolutionStep_Assign(0, mt._054);
		private final @NonNull CardinalitySolutionStep _074 // assign V0 = |MultiplicityCS::isNullFree.'|1'|
			= new CardinalitySolutionStep_Assign(0, mt._055);
		private final @NonNull CardinalitySolutionStep _075 // assign V0 = |NamedElementCS::name|
			= new CardinalitySolutionStep_Assign(0, mt._057);
		private final @NonNull CardinalitySolutionStep _076 // assign V0 = |NavigatingArgCS::ownedCoIterator|
			= new CardinalitySolutionStep_Assign(0, mt._058);
		private final @NonNull CardinalitySolutionStep _077 // assign V0 = |NavigatingArgCS::ownedInitExpression|
			= new CardinalitySolutionStep_Assign(0, mt._059);
		private final @NonNull CardinalitySolutionStep _078 // assign V0 = |NavigatingArgCS::ownedType|
			= new CardinalitySolutionStep_Assign(0, mt._061);
		private final @NonNull CardinalitySolutionStep _079 // assign V0 = |PackageDeclarationCS::ownedInvariants|
			= new CardinalitySolutionStep_Assign(0, mt._073);
		private final @NonNull CardinalitySolutionStep _080 // assign V0 = |PatternExpCS::patternVariableName|
			= new CardinalitySolutionStep_Assign(0, mt._078);
		private final @NonNull CardinalitySolutionStep _081 // assign V0 = |PropertyContextDeclCS::ownedDefaultExpressions|
			= new CardinalitySolutionStep_Assign(0, mt._080);
		private final @NonNull CardinalitySolutionStep _082 // assign V0 = |RootCS::ownedImports|
			= new CardinalitySolutionStep_Assign(0, mt._081);
		private final @NonNull CardinalitySolutionStep _083 // assign V0 = |StringLiteralExpCS::segments|
			= new CardinalitySolutionStep_Assign(0, mt._087);
		private final @NonNull CardinalitySolutionStep _084 // assign V0 = |TemplateableElementCS::ownedSignature|
			= new CardinalitySolutionStep_Assign(0, mt._092);
		private final @NonNull CardinalitySolutionStep _085 // assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
			= new CardinalitySolutionStep_Assign(0, mt._097);
		private final @NonNull CardinalitySolutionStep _086 // assign V0 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(0, mt._102);
		private final @NonNull CardinalitySolutionStep _087 // assign V0 = |TypedTypeRefCS::ownedBinding|
			= new CardinalitySolutionStep_Assign(0, mt._103);
		private final @NonNull CardinalitySolutionStep _088 // assign V0 = |VariableCS::ownedType|
			= new CardinalitySolutionStep_Assign(0, mt._106);
		private final @NonNull CardinalitySolutionStep _089 // assign V0 = |WildcardTypeRefCS::ownedExtends|
			= new CardinalitySolutionStep_Assign(0, mt._107);
		private final @NonNull CardinalitySolutionStep _090 // assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._110);
		private final @NonNull CardinalitySolutionStep _091 // assign V1 = (|CollectionPatternCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._114);
		private final @NonNull CardinalitySolutionStep _092 // assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._119);
		private final @NonNull CardinalitySolutionStep _093 // assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._136);
		private final @NonNull CardinalitySolutionStep _094 // assign V1 = (|OperationContextDeclCS::ownedParameters| > 0)
			= new CardinalitySolutionStep_Assign(1, mt._156);
		private final @NonNull CardinalitySolutionStep _095 // assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._163);
		private final @NonNull CardinalitySolutionStep _096 // assign V1 = (|TupleTypeCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(1, mt._175);
		private final @NonNull CardinalitySolutionStep _097 // assign V1 = (|TypeParameterCS::ownedExtends| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._178);
		private final @NonNull CardinalitySolutionStep _098 // assign V1 = 0
			= new CardinalitySolutionStep_Assign(1, mt._000);
		private final @NonNull CardinalitySolutionStep _099 // assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
			= new CardinalitySolutionStep_Assign(1, mt._006);
		private final @NonNull CardinalitySolutionStep _100 // assign V1 = |ClassifierContextDeclCS::selfName|
			= new CardinalitySolutionStep_Assign(1, mt._011);
		private final @NonNull CardinalitySolutionStep _101 // assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
			= new CardinalitySolutionStep_Assign(1, mt._020);
		private final @NonNull CardinalitySolutionStep _102 // assign V1 = |CompleteOCLDocumentCS::ownedPackages|
			= new CardinalitySolutionStep_Assign(1, mt._023);
		private final @NonNull CardinalitySolutionStep _103 // assign V1 = |ConstraintCS::ownedMessageSpecification|
			= new CardinalitySolutionStep_Assign(1, mt._024);
		private final @NonNull CardinalitySolutionStep _104 // assign V1 = |ImportCS::isAll.'::*'|
			= new CardinalitySolutionStep_Assign(1, mt._039);
		private final @NonNull CardinalitySolutionStep _105 // assign V1 = |MultiplicityCS::isNullFree.'|1'|
			= new CardinalitySolutionStep_Assign(1, mt._055);
		private final @NonNull CardinalitySolutionStep _106 // assign V1 = |NavigatingArgCS::ownedCoIterator|
			= new CardinalitySolutionStep_Assign(1, mt._058);
		private final @NonNull CardinalitySolutionStep _107 // assign V1 = |NavigatingArgCS::ownedInitExpression|
			= new CardinalitySolutionStep_Assign(1, mt._059);
		private final @NonNull CardinalitySolutionStep _108 // assign V1 = |PackageDeclarationCS::ownedContexts|
			= new CardinalitySolutionStep_Assign(1, mt._072);
		private final @NonNull CardinalitySolutionStep _109 // assign V1 = |TemplateBindingCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(1, mt._088);
		private final @NonNull CardinalitySolutionStep _110 // assign V1 = |TemplateableElementCS::ownedSignature|
			= new CardinalitySolutionStep_Assign(1, mt._092);
		private final @NonNull CardinalitySolutionStep _111 // assign V1 = |TypeNameExpCS::ownedPatternGuard|
			= new CardinalitySolutionStep_Assign(1, mt._099);
		private final @NonNull CardinalitySolutionStep _112 // assign V1 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(1, mt._102);
		private final @NonNull CardinalitySolutionStep _113 // assign V1 = |VariableCS::ownedType|
			= new CardinalitySolutionStep_Assign(1, mt._106);
		private final @NonNull CardinalitySolutionStep _114 // assign V2 = (|DefOperationCS::ownedParameters| > 0)
			= new CardinalitySolutionStep_Assign(2, mt._123);
		private final @NonNull CardinalitySolutionStep _115 // assign V2 = (|OperationContextDeclCS::ownedParameters| - 1)
			= new CardinalitySolutionStep_Assign(2, mt._155);
		private final @NonNull CardinalitySolutionStep _116 // assign V2 = (|TupleTypeCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(2, mt._174);
		private final @NonNull CardinalitySolutionStep _117 // assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
			= new CardinalitySolutionStep_Assign(2, mt._004);
		private final @NonNull CardinalitySolutionStep _118 // assign V2 = |ClassifierContextDeclCS::ownedInvariants|
			= new CardinalitySolutionStep_Assign(2, mt._010);
		private final @NonNull CardinalitySolutionStep _119 // assign V2 = |CompleteOCLDocumentCS::ownedContexts|
			= new CardinalitySolutionStep_Assign(2, mt._022);
		private final @NonNull CardinalitySolutionStep _120 // assign V2 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(2, mt._102);
		private final @NonNull CardinalitySolutionStep _121 // assign V3 = (|DefOperationCS::ownedParameters| - 1)
			= new CardinalitySolutionStep_Assign(3, mt._122);
		private final @NonNull CardinalitySolutionStep _122 // assign V3 = |AbstractNameExpCS::isPre.'@'|
			= new CardinalitySolutionStep_Assign(3, mt._003);
		private final @NonNull CardinalitySolutionStep _123 // assign V3 = |ClassifierContextDeclCS::ownedDefinitions|
			= new CardinalitySolutionStep_Assign(3, mt._009);
		private final @NonNull CardinalitySolutionStep _124 // assign V3 = |FeatureContextDeclCS::ownedType|
			= new CardinalitySolutionStep_Assign(3, mt._032);
		private final @NonNull CardinalitySolutionStep _125 // assign V3 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(3, mt._102);
		private final @NonNull CardinalitySolutionStep _126 // assign V4 = |OperationContextDeclCS::ownedPreconditions|
			= new CardinalitySolutionStep_Assign(4, mt._070);
		private final @NonNull CardinalitySolutionStep _127 // assign V4 = |TypedElementCS::ownedType|
			= new CardinalitySolutionStep_Assign(4, mt._101);
		private final @NonNull CardinalitySolutionStep _128 // assign V5 = |OperationContextDeclCS::ownedPostconditions|
			= new CardinalitySolutionStep_Assign(5, mt._069);
		private final @NonNull CardinalitySolutionStep _129 // assign V6 = |OperationContextDeclCS::ownedBodies|
			= new CardinalitySolutionStep_Assign(6, mt._067);
		private final @NonNull CardinalitySolutionStep _130 // check-rule basecs::ConstraintCS.ownedMessageSpecification : 83
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, iv._31/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _131 // check-rule basecs::ConstraintCS.ownedSpecification : 83
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, iv._31/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _132 // check-rule basecs::ImportCS.ownedPathName : 108
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, iv._52/*URIPathNameCS*/);
		private final @NonNull CardinalitySolutionStep _133 // check-rule basecs::PathNameCS.ownedPathElements : 30
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._11/*FirstPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _134 // check-rule basecs::PathNameCS.ownedPathElements : 30|61
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._19/*FirstPathElementCS|NextPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _135 // check-rule basecs::PathNameCS.ownedPathElements : 61|107
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._50/*NextPathElementCS|URIFirstPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _136 // check-rule basecs::RootCS.ownedImports : 35
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, iv._12/*ImportCS*/);
		private final @NonNull CardinalitySolutionStep _137 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : 50
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, iv._16/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _138 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : 88
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, iv._35/*TemplateParameterSubstitutionCS*/);
		private final @NonNull CardinalitySolutionStep _139 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 101
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, iv._47/*TypeRefCS*/);
		private final @NonNull CardinalitySolutionStep _140 // check-rule basecs::TemplateSignatureCS.ownedParameters : 100
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, iv._46/*TypeParameterCS*/);
		private final @NonNull CardinalitySolutionStep _141 // check-rule basecs::TemplateableElementCS.ownedSignature : 89
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, iv._36/*TemplateSignatureCS*/);
		private final @NonNull CardinalitySolutionStep _142 // check-rule basecs::TupleTypeCS.ownedParts : 92
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, iv._38/*TuplePartCS*/);
		private final @NonNull CardinalitySolutionStep _143 // check-rule basecs::TypeParameterCS.ownedExtends : 102
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, iv._48/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _144 // check-rule basecs::TypedElementCS.ownedType : 94
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, iv._39/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _145 // check-rule basecs::TypedRefCS.ownedMultiplicity : 50
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, iv._16/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _146 // check-rule basecs::TypedTypeRefCS.ownedBinding : 87
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, iv._34/*TemplateBindingCS*/);
		private final @NonNull CardinalitySolutionStep _147 // check-rule basecs::TypedTypeRefCS.ownedPathName : 67
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, iv._22/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _148 // check-rule basecs::WildcardTypeRefCS.ownedExtends : 102
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, iv._48/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _149 // check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : 16
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, iv._6/*DefCS*/);
		private final @NonNull CardinalitySolutionStep _150 // check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : 12
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS, iv._3/*ConstraintCS*/);
		private final @NonNull CardinalitySolutionStep _151 // check-rule completeoclcs::CompleteOCLDocumentCS.ownedContexts : 13
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS, iv._4/*ContextDeclCS*/);
		private final @NonNull CardinalitySolutionStep _152 // check-rule completeoclcs::CompleteOCLDocumentCS.ownedPackages : 65
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES, iv._20/*PackageDeclarationCS*/);
		private final @NonNull CardinalitySolutionStep _153 // check-rule completeoclcs::DefCS.ownedSpecification : 83
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION, iv._31/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _154 // check-rule completeoclcs::DefOperationCS.ownedParameters : 18
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS, iv._7/*DefParameterCS*/);
		private final @NonNull CardinalitySolutionStep _155 // check-rule completeoclcs::FeatureContextDeclCS.ownedType : 94
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE, iv._39/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _156 // check-rule completeoclcs::OperationContextDeclCS.ownedBodies : 83
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES, iv._31/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _157 // check-rule completeoclcs::OperationContextDeclCS.ownedParameters : 66
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS, iv._21/*ParameterCS*/);
		private final @NonNull CardinalitySolutionStep _158 // check-rule completeoclcs::OperationContextDeclCS.ownedPostconditions : 12
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS, iv._3/*ConstraintCS*/);
		private final @NonNull CardinalitySolutionStep _159 // check-rule completeoclcs::OperationContextDeclCS.ownedPreconditions : 12
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS, iv._3/*ConstraintCS*/);
		private final @NonNull CardinalitySolutionStep _160 // check-rule completeoclcs::PackageDeclarationCS.ownedContexts : 13
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS, iv._4/*ContextDeclCS*/);
		private final @NonNull CardinalitySolutionStep _161 // check-rule completeoclcs::PackageDeclarationCS.ownedInvariants : 12
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS, iv._3/*ConstraintCS*/);
		private final @NonNull CardinalitySolutionStep _162 // check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME, iv._22/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _163 // check-rule completeoclcs::PropertyContextDeclCS.ownedDefaultExpressions : 83
			= new CardinalitySolutionStep_RuleCheck(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS, iv._31/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _164 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._5/*CurlyBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _165 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 67
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, iv._22/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _166 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 76
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._29/*RoundBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _167 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 84
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, iv._32/*SquareBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _168 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 6
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, iv._1/*CollectionLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _169 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 8
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, iv._2/*CollectionTypeCS*/);
		private final @NonNull CardinalitySolutionStep _170 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _171 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 68
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._23/*PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _172 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _173 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : 68
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, iv._23/*PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _174 // check-rule essentialoclcs::CollectionPatternCS.ownedType : 8
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, iv._2/*CollectionTypeCS*/);
		private final @NonNull CardinalitySolutionStep _175 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 50
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, iv._16/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _176 // check-rule essentialoclcs::CollectionTypeCS.ownedType : 95
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, iv._40/*TypeExpWithoutMultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _177 // check-rule essentialoclcs::ContextCS.ownedExpression : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _178 // check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 81
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, iv._30/*ShadowPartCS*/);
		private final @NonNull CardinalitySolutionStep _179 // check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _180 // check-rule essentialoclcs::IfExpCS.ownedCondition : 29|68
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, iv._24/*ExpCS|PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _181 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _182 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 22
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, iv._9/*ElseIfThenExpCS*/);
		private final @NonNull CardinalitySolutionStep _183 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _184 // check-rule essentialoclcs::IfThenExpCS.ownedCondition : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _185 // check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _186 // check-rule essentialoclcs::InfixExpCS.ownedLeft : 70
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, iv._27/*PrefixedPrimaryExpCS*/);
		private final @NonNull CardinalitySolutionStep _187 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _188 // check-rule essentialoclcs::LetExpCS.ownedInExpression : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _189 // check-rule essentialoclcs::LetExpCS.ownedVariables : 42
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, iv._13/*LetVariableCS*/);
		private final @NonNull CardinalitySolutionStep _190 // check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 76
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._29/*RoundBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _191 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 46
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, iv._14/*MapLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _192 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : 47
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, iv._15/*MapTypeCS*/);
		private final @NonNull CardinalitySolutionStep _193 // check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _194 // check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _195 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : 94
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, iv._39/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _196 // check-rule essentialoclcs::MapTypeCS.ownedValueType : 94
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, iv._39/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _197 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, iv._0/*CoIteratorVariableCS*/);
		private final @NonNull CardinalitySolutionStep _198 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _199 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, iv._17/*NavigatingArgExpCS*/);
		private final @NonNull CardinalitySolutionStep _200 // check-rule essentialoclcs::NavigatingArgCS.ownedType : 94
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, iv._39/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _201 // check-rule essentialoclcs::NestedExpCS.ownedExpression : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _202 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _203 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 69
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._25/*PrefixedLetExpCS*/);
		private final @NonNull CardinalitySolutionStep _204 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 70
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._27/*PrefixedPrimaryExpCS*/);
		private final @NonNull CardinalitySolutionStep _205 // check-rule essentialoclcs::PatternExpCS.ownedPatternType : 94
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, iv._39/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _206 // check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 54|56|57|58
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, iv._18/*NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS*/);
		private final @NonNull CardinalitySolutionStep _207 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 29|68
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._24/*ExpCS|PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _208 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 86
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._33/*StringLiteralExpCS*/);
		private final @NonNull CardinalitySolutionStep _209 // check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _210 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 91
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, iv._37/*TupleLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _211 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 98
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, iv._42/*TypeLiteralWithMultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _212 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._5/*CurlyBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _213 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 67
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, iv._22/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _214 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _215 // check-rule essentialoclcs::VariableCS.ownedInitExpression : 29
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _216 // check-rule essentialoclcs::VariableCS.ownedType : 94
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, iv._39/*TypeExpCS*/);
	}

	/**
	 * The various serialization term used to serialize a serialization rule.
	 */
	private class _SerializationTerms
	{
		private final @NonNull RTSerializationLiteralStep _000 // 1*'&&'
									= new RTSerializationLiteralStep(-1, "&&");
		private final @NonNull RTSerializationLiteralStep _001 // 1*'('
									= new RTSerializationLiteralStep(-1, "(");
		private final @NonNull RTSerializationLiteralStep _002 // 1*')'
									= new RTSerializationLiteralStep(-1, ")");
		private final @NonNull RTSerializationLiteralStep _003 // 1*'*'
									= new RTSerializationLiteralStep(-1, "*");
		private final @NonNull RTSerializationLiteralStep _004 // 1*'++'
									= new RTSerializationLiteralStep(-1, "++");
		private final @NonNull RTSerializationLiteralStep _005 // 1*','
									= new RTSerializationLiteralStep(-1, ",");
		private final @NonNull RTSerializationLiteralStep _006 // 1*'..'
									= new RTSerializationLiteralStep(-1, "..");
		private final @NonNull RTSerializationLiteralStep _007 // 1*':'
									= new RTSerializationLiteralStep(-1, ":");
		private final @NonNull RTSerializationLiteralStep _008 // 1*'::'
									= new RTSerializationLiteralStep(-1, "::");
		private final @NonNull RTSerializationLiteralStep _009 // 1*';'
									= new RTSerializationLiteralStep(-1, ";");
		private final @NonNull RTSerializationLiteralStep _010 // 1*'<'
									= new RTSerializationLiteralStep(-1, "<");
		private final @NonNull RTSerializationLiteralStep _011 // 1*'<-'
									= new RTSerializationLiteralStep(-1, "<-");
		private final @NonNull RTSerializationLiteralStep _012 // 1*'='
									= new RTSerializationLiteralStep(-1, "=");
		private final @NonNull RTSerializationLiteralStep _013 // 1*'>'
									= new RTSerializationLiteralStep(-1, ">");
		private final @NonNull RTSerializationLiteralStep _014 // 1*'?'
									= new RTSerializationLiteralStep(-1, "?");
		private final @NonNull RTSerializationLiteralStep _015 // 1*'@'
									= new RTSerializationLiteralStep(-1, "@");
		private final @NonNull RTSerializationLiteralStep _016 // 1*'Lambda'
									= new RTSerializationLiteralStep(-1, "Lambda");
		private final @NonNull RTSerializationLiteralStep _017 // 1*'Map'
									= new RTSerializationLiteralStep(-1, "Map");
		private final @NonNull RTSerializationLiteralStep _018 // 1*'Tuple'
									= new RTSerializationLiteralStep(-1, "Tuple");
		private final @NonNull RTSerializationLiteralStep _019 // 1*'['
									= new RTSerializationLiteralStep(-1, "[");
		private final @NonNull RTSerializationLiteralStep _020 // 1*']'
									= new RTSerializationLiteralStep(-1, "]");
		private final @NonNull RTSerializationLiteralStep _021 // 1*'body'
									= new RTSerializationLiteralStep(-1, "body");
		private final @NonNull RTSerializationLiteralStep _022 // 1*'context'
									= new RTSerializationLiteralStep(-1, "context");
		private final @NonNull RTSerializationLiteralStep _023 // 1*'def'
									= new RTSerializationLiteralStep(-1, "def");
		private final @NonNull RTSerializationLiteralStep _024 // 1*'derive'
									= new RTSerializationLiteralStep(-1, "derive");
		private final @NonNull RTSerializationLiteralStep _025 // 1*'else'
									= new RTSerializationLiteralStep(-1, "else");
		private final @NonNull RTSerializationLiteralStep _026 // 1*'elseif'
									= new RTSerializationLiteralStep(-1, "elseif");
		private final @NonNull RTSerializationLiteralStep _027 // 1*'endif'
									= new RTSerializationLiteralStep(-1, "endif");
		private final @NonNull RTSerializationLiteralStep _028 // 1*'endpackage'
									= new RTSerializationLiteralStep(-1, "endpackage");
		private final @NonNull RTSerializationLiteralStep _029 // 1*'extends'
									= new RTSerializationLiteralStep(-1, "extends");
		private final @NonNull RTSerializationLiteralStep _030 // 1*'if'
									= new RTSerializationLiteralStep(-1, "if");
		private final @NonNull RTSerializationLiteralStep _031 // 1*'import'
									= new RTSerializationLiteralStep(-1, "import");
		private final @NonNull RTSerializationLiteralStep _032 // 1*'in'
									= new RTSerializationLiteralStep(-1, "in");
		private final @NonNull RTSerializationLiteralStep _033 // 1*'init'
									= new RTSerializationLiteralStep(-1, "init");
		private final @NonNull RTSerializationLiteralStep _034 // 1*'inv'
									= new RTSerializationLiteralStep(-1, "inv");
		private final @NonNull RTSerializationLiteralStep _035 // 1*'invalid'
									= new RTSerializationLiteralStep(-1, "invalid");
		private final @NonNull RTSerializationLiteralStep _036 // 1*'let'
									= new RTSerializationLiteralStep(-1, "let");
		private final @NonNull RTSerializationLiteralStep _037 // 1*'null'
									= new RTSerializationLiteralStep(-1, "null");
		private final @NonNull RTSerializationLiteralStep _038 // 1*'package'
									= new RTSerializationLiteralStep(-1, "package");
		private final @NonNull RTSerializationLiteralStep _039 // 1*'post'
									= new RTSerializationLiteralStep(-1, "post");
		private final @NonNull RTSerializationLiteralStep _040 // 1*'pre'
									= new RTSerializationLiteralStep(-1, "pre");
		private final @NonNull RTSerializationLiteralStep _041 // 1*'self'
									= new RTSerializationLiteralStep(-1, "self");
		private final @NonNull RTSerializationLiteralStep _042 // 1*'then'
									= new RTSerializationLiteralStep(-1, "then");
		private final @NonNull RTSerializationLiteralStep _043 // 1*'{'
									= new RTSerializationLiteralStep(-1, "{");
		private final @NonNull RTSerializationLiteralStep _044 // 1*'|'
									= new RTSerializationLiteralStep(-1, "|");
		private final @NonNull RTSerializationLiteralStep _045 // 1*'|?'
									= new RTSerializationLiteralStep(-1, "|?");
		private final @NonNull RTSerializationLiteralStep _046 // 1*'}'
									= new RTSerializationLiteralStep(-1, "}");
		private final @NonNull RTSerializationAssignedRuleCallStep _047 // 1*AbstractNameExpCS::ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignStep _048 // 1*BooleanLiteralExpCS::symbol
									= new RTSerializationAssignStep(-1, EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull RTSerializationAssignedRuleCallStep _049 // 1*ClassifierContextDeclCS::ownedInvariants+=ConstraintCS
									= new RTSerializationAssignedRuleCallStep(-1, CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _050 // 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _051 // 1*CollectionLiteralExpCS::ownedType=CollectionTypeCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _052 // 1*CollectionLiteralPartCS::ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _053 // 1*CollectionLiteralPartCS::ownedExpression=PatternExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _054 // 1*CollectionLiteralPartCS::ownedLastExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _055 // 1*CollectionPatternCS::ownedParts+=PatternExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _056 // 1*CollectionPatternCS::ownedType=CollectionTypeCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _057 // 1*CollectionPatternCS::restVariableName=Identifier
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _058 // 1*CollectionTypeCS::name=CollectionTypeIdentifier
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _059 // 1*CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _060 // 1*ConstraintCS::ownedMessageSpecification=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		private final @NonNull RTSerializationAssignedRuleCallStep _061 // 1*ConstraintCS::ownedSpecification=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		private final @NonNull RTSerializationAssignedRuleCallStep _062 // 1*ContextCS::ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _063 // 1*CurlyBracketedClauseCS::ownedParts+=ShadowPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _064 // 1*DefCS::ownedSpecification=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(-1, CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION);
		private final @NonNull RTSerializationAssignedRuleCallStep _065 // 1*DefOperationCS::ownedParameters+=DefParameterCS
									= new RTSerializationAssignedRuleCallStep(-1, CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS);
		private final @NonNull RTSerializationAssignedRuleCallStep _066 // 1*ExpSpecificationCS::ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _067 // 1*FeatureContextDeclCS::ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallsStep _068 // 1*IfExpCS::ownedCondition=ExpCS|PatternExpCS
									= new RTSerializationAssignedRuleCallsStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationAssignedRuleCallStep _069 // 1*IfExpCS::ownedElseExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _070 // 1*IfExpCS::ownedThenExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _071 // 1*IfThenExpCS::ownedCondition=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		private final @NonNull RTSerializationAssignedRuleCallStep _072 // 1*IfThenExpCS::ownedThenExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _073 // 1*ImportCS::ownedPathName=URIPathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _074 // 1*InfixExpCS::ownedLeft=PrefixedPrimaryExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		private final @NonNull RTSerializationAssignedRuleCallStep _075 // 1*LambdaLiteralExpCS::ownedExpressionCS=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		private final @NonNull RTSerializationAssignedRuleCallStep _076 // 1*LetExpCS::ownedInExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _077 // 1*LetExpCS::ownedVariables+=LetVariableCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		private final @NonNull RTSerializationAssignedRuleCallStep _078 // 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _079 // 1*MapLiteralExpCS::ownedType=MapTypeCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _080 // 1*MapLiteralPartCS::ownedKey=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		private final @NonNull RTSerializationAssignedRuleCallStep _081 // 1*MapLiteralPartCS::ownedValue=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		private final @NonNull RTSerializationAssignedRuleCallStep _082 // 1*MapTypeCS::ownedKeyType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _083 // 1*MapTypeCS::ownedValueType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _084 // 1*MultiplicityBoundsCS::lowerBound=LOWER
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull RTSerializationAssignedRuleCallStep _085 // 1*MultiplicityBoundsCS::upperBound=UPPER
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull RTSerializationAssignStep _086 // 1*MultiplicityStringCS::stringBounds
									= new RTSerializationAssignStep(-1, BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS);
		private final @NonNull RTSerializationAssignedRuleCallStep _087 // 1*NamedElementCS::name=BinaryOperatorName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _088 // 1*NamedElementCS::name=Identifier
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _089 // 1*NamedElementCS::name=UnaryOperatorName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _090 // 1*NamedElementCS::name=UnrestrictedName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _091 // 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		private final @NonNull RTSerializationAssignedRuleCallStep _092 // 1*NavigatingArgCS::ownedInitExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _093 // 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _094 // 1*NavigatingArgCS::ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _095 // 1*NestedExpCS::ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _096 // 1*NumberLiteralExpCS::symbol=NUMBER_LITERAL
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull RTSerializationAssignedRuleCallStep _097 // 1*OperationContextDeclCS::ownedBodies+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(-1, CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES);
		private final @NonNull RTSerializationAssignedRuleCallStep _098 // 1*OperationContextDeclCS::ownedParameters+=ParameterCS
									= new RTSerializationAssignedRuleCallStep(-1, CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS);
		private final @NonNull RTSerializationAssignedRuleCallStep _099 // 1*OperationContextDeclCS::ownedPostconditions+=ConstraintCS
									= new RTSerializationAssignedRuleCallStep(-1, CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _100 // 1*OperationContextDeclCS::ownedPreconditions+=ConstraintCS
									= new RTSerializationAssignedRuleCallStep(-1, CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _101 // 1*OperatorExpCS::ownedRight=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull RTSerializationAssignedRuleCallStep _102 // 1*OperatorExpCS::ownedRight=PrefixedLetExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull RTSerializationAssignedRuleCallStep _103 // 1*OperatorExpCS::ownedRight=PrefixedPrimaryExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull RTSerializationAssignedRuleCallStep _104 // 1*PackageDeclarationCS::ownedInvariants+=ConstraintCS
									= new RTSerializationAssignedRuleCallStep(-1, CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS);
		private final @NonNull RTSerializationCrossReferenceStep _105 // 1*PathElementCS::referredElement=URI
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"));
		private final @NonNull RTSerializationCrossReferenceStep _106 // 1*PathElementCS::referredElement=UnreservedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"));
		private final @NonNull RTSerializationCrossReferenceStep _107 // 1*PathElementCS::referredElement=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull RTSerializationCrossReferenceStep _108 // 1*PathElementCS::referredElement=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _109 // 1*PathNameCS::ownedPathElements+=FirstPathElementCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _110 // 1*PathNameCS::ownedPathElements+=NextPathElementCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _111 // 1*PathNameCS::ownedPathElements+=URIFirstPathElementCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _112 // 1*PathNameDeclCS::ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _113 // 1*PatternExpCS::ownedPatternType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _114 // 1*PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _115 // 1*PropertyContextDeclCS::ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(-1, CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _116 // 1*RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		private final @NonNull RTSerializationAssignedRuleCallsStep _117 // 1*ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS
									= new RTSerializationAssignedRuleCallsStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationAssignedRuleCallStep _118 // 1*ShadowPartCS::ownedInitExpression=StringLiteralExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull RTSerializationCrossReferenceStep _119 // 1*ShadowPartCS::referredProperty=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _120 // 1*SpecificationCS::exprString=UNQUOTED_STRING
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		private final @NonNull RTSerializationAssignedRuleCallStep _121 // 1*SquareBracketedClauseCS::ownedTerms+=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		private final @NonNull RTSerializationAssignedRuleCallStep _122 // 1*TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _123 // 1*TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull RTSerializationAssignedRuleCallStep _124 // 1*TemplateSignatureCS::ownedParameters+=TypeParameterCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull RTSerializationAssignedRuleCallStep _125 // 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _126 // 1*TupleTypeCS::ownedParts+=TuplePartCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _127 // 1*TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _128 // 1*TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _129 // 1*TypeNameExpCS::ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _130 // 1*TypeNameExpCS::ownedPatternGuard=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		private final @NonNull RTSerializationAssignedRuleCallStep _131 // 1*TypeParameterCS::ownedExtends+=TypedRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull RTSerializationAssignedRuleCallStep _132 // 1*TypedElementCS::ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _133 // 1*TypedTypeRefCS::ownedBinding=TemplateBindingCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull RTSerializationAssignedRuleCallStep _134 // 1*TypedTypeRefCS::ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _135 // 1*VariableCS::ownedInitExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _136 // 1*VariableCS::ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _137 // 1*WildcardTypeRefCS::ownedExtends=TypedRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull RTSerializationSequenceStep _138 // 1*steps-1..10
									= new RTSerializationSequenceStep(-1, 1, 10);
		private final @NonNull RTSerializationSequenceStep _139 // 1*steps-1..11
									= new RTSerializationSequenceStep(-1, 1, 11);
		private final @NonNull RTSerializationSequenceStep _140 // 1*steps-1..12
									= new RTSerializationSequenceStep(-1, 1, 12);
		private final @NonNull RTSerializationSequenceStep _141 // 1*steps-1..13
									= new RTSerializationSequenceStep(-1, 1, 13);
		private final @NonNull RTSerializationSequenceStep _142 // 1*steps-1..17
									= new RTSerializationSequenceStep(-1, 1, 17);
		private final @NonNull RTSerializationSequenceStep _143 // 1*steps-1..23
									= new RTSerializationSequenceStep(-1, 1, 23);
		private final @NonNull RTSerializationSequenceStep _144 // 1*steps-1..3
									= new RTSerializationSequenceStep(-1, 1, 3);
		private final @NonNull RTSerializationSequenceStep _145 // 1*steps-1..4
									= new RTSerializationSequenceStep(-1, 1, 4);
		private final @NonNull RTSerializationSequenceStep _146 // 1*steps-1..5
									= new RTSerializationSequenceStep(-1, 1, 5);
		private final @NonNull RTSerializationSequenceStep _147 // 1*steps-1..6
									= new RTSerializationSequenceStep(-1, 1, 6);
		private final @NonNull RTSerializationSequenceStep _148 // 1*steps-1..7
									= new RTSerializationSequenceStep(-1, 1, 7);
		private final @NonNull RTSerializationSequenceStep _149 // 1*steps-1..8
									= new RTSerializationSequenceStep(-1, 1, 8);
		private final @NonNull RTSerializationSequenceStep _150 // 1*steps-1..9
									= new RTSerializationSequenceStep(-1, 1, 9);
		private final @NonNull RTSerializationLiteralStep _151 // V00*'static'
									= new RTSerializationLiteralStep(0, "static");
		private final @NonNull RTSerializationLiteralStep _152 // V00*'|1'
									= new RTSerializationLiteralStep(0, "|1");
		private final @NonNull RTSerializationAssignedRuleCallStep _153 // V00*AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		private final @NonNull RTSerializationAssignedRuleCallStep _154 // V00*IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _155 // V00*LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _156 // V00*PatternExpCS::patternVariableName=UnrestrictedName
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _157 // V00*RootCS::ownedImports+=ImportCS
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _158 // V00*StringLiteralExpCS::segments+=StringLiteral
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _159 // V00*TemplateableElementCS::ownedSignature=TemplateSignatureCS
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		private final @NonNull RTSerializationAssignedRuleCallStep _160 // V00*TypedRefCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationSequenceStep _161 // V00*steps-2..4
									= new RTSerializationSequenceStep(0, 2, 4);
		private final @NonNull RTSerializationSequenceStep _162 // V00*steps-2..7
									= new RTSerializationSequenceStep(0, 2, 7);
		private final @NonNull RTSerializationSequenceStep _163 // V00*steps-3..10
									= new RTSerializationSequenceStep(0, 3, 10);
		private final @NonNull RTSerializationSequenceStep _164 // V00*steps-3..5
									= new RTSerializationSequenceStep(0, 3, 5);
		private final @NonNull RTSerializationSequenceStep _165 // V00*steps-3..6
									= new RTSerializationSequenceStep(0, 3, 6);
		private final @NonNull RTSerializationSequenceStep _166 // V00*steps-3..7
									= new RTSerializationSequenceStep(0, 3, 7);
		private final @NonNull RTSerializationSequenceStep _167 // V00*steps-3..8
									= new RTSerializationSequenceStep(0, 3, 8);
		private final @NonNull RTSerializationSequenceStep _168 // V00*steps-4..10
									= new RTSerializationSequenceStep(0, 4, 10);
		private final @NonNull RTSerializationSequenceStep _169 // V00*steps-4..6
									= new RTSerializationSequenceStep(0, 4, 6);
		private final @NonNull RTSerializationSequenceStep _170 // V00*steps-4..8
									= new RTSerializationSequenceStep(0, 4, 8);
		private final @NonNull RTSerializationSequenceStep _171 // V00*steps-4..9
									= new RTSerializationSequenceStep(0, 4, 9);
		private final @NonNull RTSerializationSequenceStep _172 // V00*steps-5..7
									= new RTSerializationSequenceStep(0, 5, 7);
		private final @NonNull RTSerializationSequenceStep _173 // V00*steps-6..8
									= new RTSerializationSequenceStep(0, 6, 8);
		private final @NonNull RTSerializationSequenceStep _174 // V00*steps-6..9
									= new RTSerializationSequenceStep(0, 6, 9);
		private final @NonNull RTSerializationLiteralStep _175 // V01*'::*'
									= new RTSerializationLiteralStep(1, "::*");
		private final @NonNull RTSerializationLiteralStep _176 // V01*'|1'
									= new RTSerializationLiteralStep(1, "|1");
		private final @NonNull RTSerializationAssignedRuleCallStep _177 // V01*AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _178 // V01*ClassifierContextDeclCS::selfName=UnrestrictedName
									= new RTSerializationAssignedRuleCallStep(1, CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _179 // V01*CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		private final @NonNull RTSerializationAssignedRuleCallStep _180 // V01*CompleteOCLDocumentCS::ownedPackages+=PackageDeclarationCS
									= new RTSerializationAssignedRuleCallStep(1, CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES);
		private final @NonNull RTSerializationAssignedRuleCallStep _181 // V01*PackageDeclarationCS::ownedContexts+=ContextDeclCS
									= new RTSerializationAssignedRuleCallStep(1, CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS);
		private final @NonNull RTSerializationAssignedRuleCallsStep _182 // V01*RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS
									= new RTSerializationAssignedRuleCallsStep(1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
				 new @NonNull AbstractRuleValue [3]);
		private final @NonNull RTSerializationAssignedRuleCallStep _183 // V01*TemplateBindingCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationAssignedRuleCallStep _184 // V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		private final @NonNull RTSerializationAssignedRuleCallStep _185 // V01*TypedRefCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationSequenceStep _186 // V01*steps-10..13
									= new RTSerializationSequenceStep(1, 10, 13);
		private final @NonNull RTSerializationSequenceStep _187 // V01*steps-4..6
									= new RTSerializationSequenceStep(1, 4, 6);
		private final @NonNull RTSerializationSequenceStep _188 // V01*steps-4..7
									= new RTSerializationSequenceStep(1, 4, 7);
		private final @NonNull RTSerializationSequenceStep _189 // V01*steps-5..7
									= new RTSerializationSequenceStep(1, 5, 7);
		private final @NonNull RTSerializationSequenceStep _190 // V01*steps-5..8
									= new RTSerializationSequenceStep(1, 5, 8);
		private final @NonNull RTSerializationSequenceStep _191 // V01*steps-5..9
									= new RTSerializationSequenceStep(1, 5, 9);
		private final @NonNull RTSerializationSequenceStep _192 // V01*steps-6..10
									= new RTSerializationSequenceStep(1, 6, 10);
		private final @NonNull RTSerializationSequenceStep _193 // V01*steps-6..8
									= new RTSerializationSequenceStep(1, 6, 8);
		private final @NonNull RTSerializationSequenceStep _194 // V01*steps-7..9
									= new RTSerializationSequenceStep(1, 7, 9);
		private final @NonNull RTSerializationSequenceStep _195 // V01*steps-8..10
									= new RTSerializationSequenceStep(1, 8, 10);
		private final @NonNull RTSerializationSequenceStep _196 // V01*steps-9..11
									= new RTSerializationSequenceStep(1, 9, 11);
		private final @NonNull RTSerializationAssignedRuleCallStep _197 // V02*AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(2, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _198 // V02*CompleteOCLDocumentCS::ownedContexts+=ContextDeclCS
									= new RTSerializationAssignedRuleCallStep(2, CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _199 // V02*TypedRefCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationSequenceStep _200 // V02*steps-6..8
									= new RTSerializationSequenceStep(2, 6, 8);
		private final @NonNull RTSerializationSequenceStep _201 // V02*steps-7..9
									= new RTSerializationSequenceStep(2, 7, 9);
		private final @NonNull RTSerializationSequenceStep _202 // V02*steps-8..10
									= new RTSerializationSequenceStep(2, 8, 10);
		private final @NonNull RTSerializationSequenceStep _203 // V02*steps-8..12
									= new RTSerializationSequenceStep(2, 8, 12);
		private final @NonNull RTSerializationAssignedRuleCallStep _204 // V03*ClassifierContextDeclCS::ownedDefinitions+=DefCS
									= new RTSerializationAssignedRuleCallStep(3, CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _205 // V03*FeatureContextDeclCS::ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(3, CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _206 // V03*TypedRefCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationSequenceStep _207 // V03*steps-10..12
									= new RTSerializationSequenceStep(3, 10, 12);
		private final @NonNull RTSerializationSequenceStep _208 // V03*steps-6..8
									= new RTSerializationSequenceStep(3, 6, 8);
		private final @NonNull RTSerializationAssignedRuleCallStep _209 // V04*TypedElementCS::ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull RTSerializationSequenceStep _210 // V04*steps-14..16
									= new RTSerializationSequenceStep(4, 14, 16);
		private final @NonNull RTSerializationSequenceStep _211 // V05*steps-17..19
									= new RTSerializationSequenceStep(5, 17, 19);
		private final @NonNull RTSerializationSequenceStep _212 // V06*steps-20..23
									= new RTSerializationSequenceStep(6, 20, 23);

		/**
		 * Post constructor initialization that avoids recursions.
		 */
		private final void init() {
			_047.init(gr._067);
			_049.init(gr._012);
			_050.init(gr._006);
			_051.init(gr._008);
			_052.init(gr._029);
			_053.init(gr._068);
			_054.init(gr._029);
			_055.init(gr._068);
			_056.init(gr._008);
			_057.init(gr._033);
			_058.init(gr._009);
			_059.init(gr._095);
			_060.init(gr._083);
			_061.init(gr._083);
			_062.init(gr._029);
			_063.init(gr._081);
			_064.init(gr._083);
			_065.init(gr._018);
			_066.init(gr._029);
			_067.init(gr._094);
			_068.init(new @NonNull AbstractRuleValue [] {gr._029/*ExpCS*/, gr._068/*PatternExpCS*/});
			_069.init(gr._029);
			_070.init(gr._029);
			_071.init(gr._029);
			_072.init(gr._029);
			_073.init(gr._108);
			_074.init(gr._070);
			_075.init(gr._029);
			_076.init(gr._029);
			_077.init(gr._042);
			_078.init(gr._046);
			_079.init(gr._047);
			_080.init(gr._029);
			_081.init(gr._029);
			_082.init(gr._094);
			_083.init(gr._094);
			_084.init(gr._039);
			_085.init(gr._105);
			_087.init(gr._001);
			_088.init(gr._033);
			_089.init(gr._109);
			_090.init(gr._112);
			_091.init(gr._004);
			_092.init(gr._029);
			_093.init(gr._055);
			_094.init(gr._094);
			_095.init(gr._029);
			_096.init(gr._052);
			_097.init(gr._083);
			_098.init(gr._066);
			_099.init(gr._012);
			_100.init(gr._012);
			_101.init(gr._029);
			_102.init(gr._069);
			_103.init(gr._070);
			_104.init(gr._012);
			_109.init(gr._030);
			_110.init(gr._061);
			_111.init(gr._107);
			_112.init(gr._067);
			_113.init(gr._094);
			_114.init(gr._074);
			_115.init(gr._083);
			_116.init(gr._054);
			_117.init(new @NonNull AbstractRuleValue [] {gr._029/*ExpCS*/, gr._068/*PatternExpCS*/});
			_118.init(gr._086);
			_120.init(gr._104);
			_121.init(gr._029);
			_122.init(gr._088);
			_123.init(gr._101);
			_124.init(gr._100);
			_125.init(gr._091);
			_126.init(gr._092);
			_127.init(gr._098);
			_128.init(gr._014);
			_129.init(gr._067);
			_130.init(gr._029);
			_131.init(gr._102);
			_132.init(gr._094);
			_133.init(gr._087);
			_134.init(gr._067);
			_135.init(gr._029);
			_136.init(gr._094);
			_137.init(gr._102);
			_153.init(gr._084);
			_154.init(gr._022);
			_155.init(gr._076);
			_156.init(gr._112);
			_157.init(gr._035);
			_158.init(gr._085);
			_159.init(gr._089);
			_160.init(gr._050);
			_177.init(gr._076);
			_178.init(gr._112);
			_179.init(gr._050);
			_180.init(gr._065);
			_181.init(gr._013);
			_182.init(new @NonNull AbstractRuleValue [] {gr._057/*NavigatingCommaArgCS*/, gr._058/*NavigatingSemiArgCS*/, gr._056/*NavigatingBarArgCS*/});
			_183.init(gr._050);
			_184.init(gr._089);
			_185.init(gr._050);
			_197.init(gr._014);
			_198.init(gr._013);
			_199.init(gr._050);
			_204.init(gr._016);
			_205.init(gr._094);
			_206.init(gr._050);
			_209.init(gr._094);
		}
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
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _11 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
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
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _23 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */
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
			ss._7 /* «? » «value» «? » */,
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
				(IndexVector)null);
		private final @NonNull ParserRuleValue _003 // ClassifierContextDeclCS
			= new ParserRuleValue(3, "ClassifierContextDeclCS",
				new @NonNull SerializationRule [] {
					sr0._017 /* { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedDefinitions+=DefCS[+] } */,
					sr0._016 /* { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[+] ownedDefinitions+=DefCS[*] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _004 // CoIteratorVariableCS
			= new ParserRuleValue(4, "CoIteratorVariableCS",
				new @NonNull SerializationRule [] {
					sr0._034 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _005 // CollectionLiteralExpCS
			= new ParserRuleValue(5, "CollectionLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._035 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _006 // CollectionLiteralPartCS
			= new ParserRuleValue(6, "CollectionLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._036 /* ownedExpression=PatternExpCS */,
					sr0._037 /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _007 // CollectionPatternCS
			= new ParserRuleValue(7, "CollectionPatternCS",
				new @NonNull SerializationRule [] {
					sr0._038 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _008 // CollectionTypeCS
			= new ParserRuleValue(8, "CollectionTypeCS",
				new @NonNull SerializationRule [] {
					sr0._039 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _009 // CollectionTypeIdentifier
			= new DataTypeRuleValue(9, "CollectionTypeIdentifier");
		private final @NonNull ParserRuleValue _010 // CompleteOCLDocumentCS
			= new ParserRuleValue(10, "CompleteOCLDocumentCS",
				new @NonNull SerializationRule [] {
					sr0._018 /* { ownedImports+=ImportCS[*] ownedPackages+=PackageDeclarationCS[*] ownedContexts+=ContextDeclCS[*] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _011 // CompleteOCLNavigationOperatorName
			= new DataTypeRuleValue(11, "CompleteOCLNavigationOperatorName");
		private final @NonNull ParserRuleValue _012 // ConstraintCS
			= new ParserRuleValue(12, "ConstraintCS",
				new @NonNull SerializationRule [] {
					sr0._019 /* { { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _013 // ContextDeclCS
			= new ParserRuleValue(13, "ContextDeclCS",
				new @NonNull SerializationRule [] {
					sr0._017 /* { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedDefinitions+=DefCS[+] } */,
					sr0._016 /* { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[+] ownedDefinitions+=DefCS[*] } */,
					sr0._025 /* { 'context' ownedSignature=TemplateSignatureCS[?] ownedPathName=PathNameCS '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypeExpCS[?] { 'pre' ownedPreconditions+=ConstraintCS }[*] { 'post' ownedPostconditions+=ConstraintCS }[*] { 'body' ':' ownedBodies+=SpecificationCS }[*] } */,
					sr0._028 /* { 'context' ownedPathName=PathNameCS ':' ownedType=TypeExpCS { 'derive' ':' ownedDefaultExpressions+=SpecificationCS }[*] { 'init' ':' ownedDefaultExpressions+=SpecificationCS }[*] } */
				},
				iv._28); /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */
		private final @NonNull ParserRuleValue _014 // CurlyBracketedClauseCS
			= new ParserRuleValue(14, "CurlyBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr0._040 /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				(IndexVector)null);
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
				(IndexVector)null);
		private final @NonNull ParserRuleValue _018 // DefParameterCS
			= new ParserRuleValue(18, "DefParameterCS",
				new @NonNull SerializationRule [] {
					sr0._021 /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _019 // DefPropertyCS
			= new ParserRuleValue(19, "DefPropertyCS",
				new @NonNull SerializationRule [] {
					sr0._022 /* { isStatic='static'[?] 'def' ':' name=UnrestrictedName ':' ownedType=TypeExpCS '=' ownedSpecification=SpecificationCS } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _020 // ESCAPED_CHARACTER
			= new TerminalRuleValue(20, "ESCAPED_CHARACTER");
		private final @NonNull TerminalRuleValue _021 // ESCAPED_ID
			= new TerminalRuleValue(21, "ESCAPED_ID");
		private final @NonNull ParserRuleValue _022 // ElseIfThenExpCS
			= new ParserRuleValue(22, "ElseIfThenExpCS",
				new @NonNull SerializationRule [] {
					sr0._041 /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				(IndexVector)null);
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
					sr0._048 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._050 /* '*' */,
					sr0._058 /* 'invalid' */,
					sr0._051 /* 'null' */,
					sr0._045 /* 'self' */,
					sr0._055 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._054 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._052 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._047 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._046 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._043 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._056 /* symbol=NUMBER_LITERAL */,
					sr0._044 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._049 /* segments+=StringLiteral[+] */,
					sr0._053 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._057 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._062 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._084 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._57); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _030 // FirstPathElementCS
			= new ParserRuleValue(30, "FirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._000 /* referredElement=UnrestrictedName */
				},
				(IndexVector)null);
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
				(IndexVector)null);
		private final @NonNull ParserRuleValue _035 // ImportCS
			= new ParserRuleValue(35, "ImportCS",
				new @NonNull SerializationRule [] {
					sr0._023 /* { {'import'|'include'|'library'} { name=Identifier ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _036 // InfixOperatorName
			= new DataTypeRuleValue(36, "InfixOperatorName");
		private final @NonNull ParserRuleValue _037 // InvalidLiteralExpCS
			= new ParserRuleValue(37, "InvalidLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._060 /* 'invalid' */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _038 // LETTER_CHARACTER
			= new TerminalRuleValue(38, "LETTER_CHARACTER");
		private final @NonNull DataTypeRuleValue _039 // LOWER
			= new DataTypeRuleValue(39, "LOWER");
		private final @NonNull ParserRuleValue _040 // LambdaLiteralExpCS
			= new ParserRuleValue(40, "LambdaLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._061 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _041 // LetExpCS
			= new ParserRuleValue(41, "LetExpCS",
				new @NonNull SerializationRule [] {
					sr0._062 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _042 // LetVariableCS
			= new ParserRuleValue(42, "LetVariableCS",
				new @NonNull SerializationRule [] {
					sr0._063 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _043 // ML_COMMENT
			= new TerminalRuleValue(43, "ML_COMMENT");
		private final @NonNull TerminalRuleValue _044 // ML_SINGLE_QUOTED_STRING
			= new TerminalRuleValue(44, "ML_SINGLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _045 // MapLiteralExpCS
			= new ParserRuleValue(45, "MapLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._064 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _046 // MapLiteralPartCS
			= new ParserRuleValue(46, "MapLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr1._065 /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _047 // MapTypeCS
			= new ParserRuleValue(47, "MapTypeCS",
				new @NonNull SerializationRule [] {
					sr1._066 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _048 // Model
			= new ParserRuleValue(48, "Model",
				new @NonNull SerializationRule [] {
					sr1._067 /* ownedExpression=ExpCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _049 // MultiplicityBoundsCS
			= new ParserRuleValue(49, "MultiplicityBoundsCS",
				new @NonNull SerializationRule [] {
					sr0._001 /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _050 // MultiplicityCS
			= new ParserRuleValue(50, "MultiplicityCS",
				new @NonNull SerializationRule [] {
					sr0._002 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					sr0._006 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					sr0._003 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */,
					sr0._007 /* { '[' stringBounds={'*|+|?'} ']' } */,
					sr0._004 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					sr0._005 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _051 // MultiplicityStringCS
			= new ParserRuleValue(51, "MultiplicityStringCS",
				new @NonNull SerializationRule [] {
					sr0._008 /* stringBounds={'*|+|?'} */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _052 // NUMBER_LITERAL
			= new DataTypeRuleValue(52, "NUMBER_LITERAL");
		private final @NonNull ParserRuleValue _053 // NameExpCS
			= new ParserRuleValue(53, "NameExpCS",
				new @NonNull SerializationRule [] {
					sr1._068 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _054 // NavigatingArgCS
			= new ParserRuleValue(54, "NavigatingArgCS",
				new @NonNull SerializationRule [] {
					sr1._071 /* ownedNameExpression=NavigatingArgExpCS */,
					sr1._072 /* { ':' ownedType=TypeExpCS } */,
					sr1._070 /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr1._073 /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr1._069 /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _055 // NavigatingArgExpCS
			= new ParserRuleValue(55, "NavigatingArgExpCS",
				new @NonNull SerializationRule [] {
					sr0._024 /* '?' */,
					sr0._042 /* symbol={'false|true'} */,
					sr0._048 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._050 /* '*' */,
					sr0._058 /* 'invalid' */,
					sr0._051 /* 'null' */,
					sr0._045 /* 'self' */,
					sr0._055 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._054 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._052 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._047 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._046 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._043 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._056 /* symbol=NUMBER_LITERAL */,
					sr0._044 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._049 /* segments+=StringLiteral[+] */,
					sr0._053 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._057 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._062 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._084 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._58); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _056 // NavigatingBarArgCS
			= new ParserRuleValue(56, "NavigatingBarArgCS",
				new @NonNull SerializationRule [] {
					sr1._074 /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _057 // NavigatingCommaArgCS
			= new ParserRuleValue(57, "NavigatingCommaArgCS",
				new @NonNull SerializationRule [] {
					sr1._076 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					sr1._077 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr1._075 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr1._078 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _058 // NavigatingSemiArgCS
			= new ParserRuleValue(58, "NavigatingSemiArgCS",
				new @NonNull SerializationRule [] {
					sr1._079 /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _059 // NavigationOperatorName
			= new DataTypeRuleValue(59, "NavigationOperatorName");
		private final @NonNull ParserRuleValue _060 // NestedExpCS
			= new ParserRuleValue(60, "NestedExpCS",
				new @NonNull SerializationRule [] {
					sr1._080 /* { '(' ownedExpression=ExpCS ')' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _061 // NextPathElementCS
			= new ParserRuleValue(61, "NextPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._009 /* referredElement=UnreservedName */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _062 // NullLiteralExpCS
			= new ParserRuleValue(62, "NullLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._081 /* 'null' */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _063 // NumberLiteralExpCS
			= new ParserRuleValue(63, "NumberLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._082 /* symbol=NUMBER_LITERAL */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _064 // OperationContextDeclCS
			= new ParserRuleValue(64, "OperationContextDeclCS",
				new @NonNull SerializationRule [] {
					sr0._025 /* { 'context' ownedSignature=TemplateSignatureCS[?] ownedPathName=PathNameCS '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypeExpCS[?] { 'pre' ownedPreconditions+=ConstraintCS }[*] { 'post' ownedPostconditions+=ConstraintCS }[*] { 'body' ':' ownedBodies+=SpecificationCS }[*] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _065 // PackageDeclarationCS
			= new ParserRuleValue(65, "PackageDeclarationCS",
				new @NonNull SerializationRule [] {
					sr0._026 /* { 'package' ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedContexts+=ContextDeclCS[*] 'endpackage' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _066 // ParameterCS
			= new ParserRuleValue(66, "ParameterCS",
				new @NonNull SerializationRule [] {
					sr0._027 /* { { name=UnrestrictedName ':' }[?] ownedType=TypeExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _067 // PathNameCS
			= new ParserRuleValue(67, "PathNameCS",
				new @NonNull SerializationRule [] {
					sr0._010 /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _068 // PatternExpCS
			= new ParserRuleValue(68, "PatternExpCS",
				new @NonNull SerializationRule [] {
					sr1._083 /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _069 // PrefixedLetExpCS
			= new ParserRuleValue(69, "PrefixedLetExpCS",
				new @NonNull SerializationRule [] {
					sr0._062 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._084 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._26); /* LetExpCS|PrefixedLetExpCS */
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
				iv._55); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
				iv._54); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
				iv._53); /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _073 // PrimitiveTypeCS
			= new ParserRuleValue(73, "PrimitiveTypeCS",
				new @NonNull SerializationRule [] {
					sr1._086 /* name=PrimitiveTypeIdentifier */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _074 // PrimitiveTypeIdentifier
			= new DataTypeRuleValue(74, "PrimitiveTypeIdentifier");
		private final @NonNull ParserRuleValue _075 // PropertyContextDeclCS
			= new ParserRuleValue(75, "PropertyContextDeclCS",
				new @NonNull SerializationRule [] {
					sr0._028 /* { 'context' ownedPathName=PathNameCS ':' ownedType=TypeExpCS { 'derive' ':' ownedDefaultExpressions+=SpecificationCS }[*] { 'init' ':' ownedDefaultExpressions+=SpecificationCS }[*] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _076 // RoundBracketedClauseCS
			= new ParserRuleValue(76, "RoundBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._087 /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				(IndexVector)null);
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
				(IndexVector)null);
		private final @NonNull ParserRuleValue _081 // ShadowPartCS
			= new ParserRuleValue(81, "ShadowPartCS",
				new @NonNull SerializationRule [] {
					sr1._090 /* ownedInitExpression=StringLiteralExpCS */,
					sr1._089 /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _082 // SimplePathNameCS
			= new ParserRuleValue(82, "SimplePathNameCS",
				new @NonNull SerializationRule [] {
					sr1._091 /* ownedPathElements+=FirstPathElementCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _083 // SpecificationCS
			= new ParserRuleValue(83, "SpecificationCS",
				new @NonNull SerializationRule [] {
					sr0._029 /* exprString=UNQUOTED_STRING */,
					sr0._030 /* ownedExpression=ExpCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _084 // SquareBracketedClauseCS
			= new ParserRuleValue(84, "SquareBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._092 /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _085 // StringLiteral
			= new DataTypeRuleValue(85, "StringLiteral");
		private final @NonNull ParserRuleValue _086 // StringLiteralExpCS
			= new ParserRuleValue(86, "StringLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._093 /* segments+=StringLiteral[+] */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _087 // TemplateBindingCS
			= new ParserRuleValue(87, "TemplateBindingCS",
				new @NonNull SerializationRule [] {
					sr0._011 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _088 // TemplateParameterSubstitutionCS
			= new ParserRuleValue(88, "TemplateParameterSubstitutionCS",
				new @NonNull SerializationRule [] {
					sr0._012 /* ownedActualParameter=TypeRefCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _089 // TemplateSignatureCS
			= new ParserRuleValue(89, "TemplateSignatureCS",
				new @NonNull SerializationRule [] {
					sr0._032 /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */,
					sr0._031 /* { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _090 // TupleLiteralExpCS
			= new ParserRuleValue(90, "TupleLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._094 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _091 // TupleLiteralPartCS
			= new ParserRuleValue(91, "TupleLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr1._095 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _092 // TuplePartCS
			= new ParserRuleValue(92, "TuplePartCS",
				new @NonNull SerializationRule [] {
					sr1._096 /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _093 // TupleTypeCS
			= new ParserRuleValue(93, "TupleTypeCS",
				new @NonNull SerializationRule [] {
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _094 // TypeExpCS
			= new ParserRuleValue(94, "TypeExpCS",
				new @NonNull SerializationRule [] {
					sr1._098 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._102 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._100 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._103 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._099 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._101 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._45); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
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
				iv._44); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull ParserRuleValue _096 // TypeLiteralCS
			= new ParserRuleValue(96, "TypeLiteralCS",
				new @NonNull SerializationRule [] {
					sr0._039 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr1._066 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._086 /* name=PrimitiveTypeIdentifier */,
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._41); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */
		private final @NonNull ParserRuleValue _097 // TypeLiteralExpCS
			= new ParserRuleValue(97, "TypeLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._104 /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _098 // TypeLiteralWithMultiplicityCS
			= new ParserRuleValue(98, "TypeLiteralWithMultiplicityCS",
				new @NonNull SerializationRule [] {
					sr1._105 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._106 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._107 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._108 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._43); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
		private final @NonNull ParserRuleValue _099 // TypeNameExpCS
			= new ParserRuleValue(99, "TypeNameExpCS",
				new @NonNull SerializationRule [] {
					sr1._109 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _100 // TypeParameterCS
			= new ParserRuleValue(100, "TypeParameterCS",
				new @NonNull SerializationRule [] {
					sr0._013 /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				(IndexVector)null);
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
				iv._60); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
		private final @NonNull ParserRuleValue _102 // TypedRefCS
			= new ParserRuleValue(102, "TypedRefCS",
				new @NonNull SerializationRule [] {
					sr0._014 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */,
					sr0._039 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr1._066 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._086 /* name=PrimitiveTypeIdentifier */,
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._49); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
		private final @NonNull ParserRuleValue _103 // TypedTypeRefCS
			= new ParserRuleValue(103, "TypedTypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._014 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				(IndexVector)null);
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
				(IndexVector)null);
		private final @NonNull ParserRuleValue _108 // URIPathNameCS
			= new ParserRuleValue(108, "URIPathNameCS",
				new @NonNull SerializationRule [] {
					sr1._112 /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _109 // UnaryOperatorName
			= new DataTypeRuleValue(109, "UnaryOperatorName");
		private final @NonNull ParserRuleValue _110 // UnlimitedNaturalLiteralExpCS
			= new ParserRuleValue(110, "UnlimitedNaturalLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._113 /* '*' */
				},
				(IndexVector)null);
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
				(IndexVector)null);
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
					new SerializationRule_SegmentsList(sr0._017, sl._52) /* { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedDefinitions+=DefCS[+] } */,
					new SerializationRule_SegmentsList(sr0._016, sl._52) /* { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[+] ownedDefinitions+=DefCS[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
						iv._8) /* DefCS|DefOperationCS|DefPropertyCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
						iv._3) /* ConstraintCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						iv._22) /* PathNameCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._36) /* TemplateSignatureCS */
				}
			);
		private final @NonNull EClassValue _02 // CollectionLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._048, sl._30) /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
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
						iv._59) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
						iv._23) /* PatternExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						iv._2) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassValue _05 // CollectionTypeCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._039, sl._18) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._103, sl._19) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._107, sl._19) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						iv._16) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._16) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						iv._44) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _06 // CompleteOCLDocumentCS
			= new EClassValue(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._018, sl._25) /* { ownedImports+=ImportCS[*] ownedPackages+=PackageDeclarationCS[*] ownedContexts+=ContextDeclCS[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS,
						iv._28) /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
						iv._12) /* ImportCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES,
						iv._20) /* PackageDeclarationCS */
				}
			);
		private final @NonNull EClassValue _07 // ConstraintCS
			= new EClassValue(BaseCSPackage.Literals.CONSTRAINT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._019, sl._34) /* { { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						iv._31) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						iv._31) /* SpecificationCS */
				}
			);
		private final @NonNull EClassValue _08 // ContextCS
			= new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._067, sl._24) /* ownedExpression=ExpCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _09 // CurlyBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._040, sl._14) /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						iv._30) /* ShadowPartCS */
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
						iv._36) /* TemplateSignatureCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
						iv._31) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _11 // DefPropertyCS
			= new EClassValue(CompleteOCLCSPackage.Literals.DEF_PROPERTY_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._022, sl._60) /* { isStatic='static'[?] 'def' ':' name=UnrestrictedName ':' ownedType=TypeExpCS '=' ownedSpecification=SpecificationCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
						iv._31) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _12 // ExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._024, sl._24) /* '?' */,
					new SerializationRule_SegmentsList(sr0._050, sl._24) /* '*' */,
					new SerializationRule_SegmentsList(sr0._058, sl._24) /* 'invalid' */,
					new SerializationRule_SegmentsList(sr0._051, sl._24) /* 'null' */,
					new SerializationRule_SegmentsList(sr0._045, sl._24) /* 'self' */
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
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _14 // IfExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._055, sl._53) /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					new SerializationRule_SegmentsList(sr0._059, sl._53) /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						iv._59) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						iv._9) /* ElseIfThenExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _15 // IfThenExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._041, sl._50) /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _16 // ImportCS
			= new EClassValue(BaseCSPackage.Literals.IMPORT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._023, sl._29) /* { {'import'|'include'|'library'} { name=Identifier ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
						iv._52) /* URIPathNameCS */
				}
			);
		private final @NonNull EClassValue _17 // InfixExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._054, sl._31) /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
						iv._55) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
					new SerializationRule_SegmentsList(sr0._052, sl._57) /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					new SerializationRule_SegmentsList(sr0._061, sl._57) /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _20 // LetExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._062, sl._46) /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
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
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._29) /* RoundBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _22 // MapLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._047, sl._30) /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
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
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _24 // MapTypeCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._066, sl._20) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._099, sl._21) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._108, sl._21) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._16) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _25 // MultiplicityBoundsCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._001, sl._23) /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */,
					new SerializationRule_SegmentsList(sr0._002, sl._10) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._006, sl._11) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._003, sl._11) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, null
			);
		private final @NonNull EClassValue _26 // MultiplicityStringCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._007, sl._12) /* { '[' stringBounds={'*|+|?'} ']' } */,
					new SerializationRule_SegmentsList(sr0._004, sl._13) /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._005, sl._13) /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._008, sl._61) /* stringBounds={'*|+|?'} */
				}, null
			);
		private final @NonNull EClassValue _27 // NameExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._046, sl._26) /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					new SerializationRule_SegmentsList(sr1._068, sl._26) /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._5) /* CurlyBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._22) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._29) /* RoundBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						iv._32) /* SquareBracketedClauseCS */
				}
			);
		private final @NonNull EClassValue _28 // NavigatingArgCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._071, sl._24) /* ownedNameExpression=NavigatingArgExpCS */,
					new SerializationRule_SegmentsList(sr1._072, sl._43) /* { ':' ownedType=TypeExpCS } */,
					new SerializationRule_SegmentsList(sr1._073, sl._32) /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr1._070, sl._33) /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr1._069, sl._28) /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					new SerializationRule_SegmentsList(sr1._074, sl._48) /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */,
					new SerializationRule_SegmentsList(sr1._076, sl._38) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					new SerializationRule_SegmentsList(sr1._075, sl._40) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr1._077, sl._41) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr1._078, sl._39) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					new SerializationRule_SegmentsList(sr1._079, sl._42) /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						iv._0) /* CoIteratorVariableCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						iv._58) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _29 // NestedExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._043, sl._37) /* { '(' ownedExpression=ExpCS ')' } */,
					new SerializationRule_SegmentsList(sr1._080, sl._37) /* { '(' ownedExpression=ExpCS ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
					new SerializationRule_SegmentsList(sr0._056, sl._24) /* symbol=NUMBER_LITERAL */,
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
						iv._31) /* SpecificationCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS,
						iv._21) /* ParameterCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						iv._22) /* PathNameCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS,
						iv._3) /* ConstraintCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS,
						iv._3) /* ConstraintCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._36) /* TemplateSignatureCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _33 // PackageDeclarationCS
			= new EClassValue(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._026, sl._47) /* { 'package' ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedContexts+=ContextDeclCS[*] 'endpackage' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS,
						iv._28) /* ClassifierContextDeclCS|ContextDeclCS|OperationContextDeclCS|PropertyContextDeclCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS,
						iv._3) /* ConstraintCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						iv._22) /* PathNameCS */
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
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
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
						iv._51) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
				}
			);
		private final @NonNull EClassValue _38 // PatternExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._083, sl._58) /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _39 // PrefixExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._044, sl._43) /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					new SerializationRule_SegmentsList(sr1._084, sl._43) /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					new SerializationRule_SegmentsList(sr1._085, sl._43) /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._56) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
						iv._31) /* SpecificationCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
						iv._22) /* PathNameCS */,
					new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _42 // RoundBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._087, sl._08) /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						iv._18) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
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
					new SerializationRule_SegmentsList(sr1._090, sl._24) /* ownedInitExpression=StringLiteralExpCS */,
					new SerializationRule_SegmentsList(sr1._089, sl._58) /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						iv._59) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _45 // SquareBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._092, sl._09) /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _46 // StringLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._049, sl._24) /* segments+=StringLiteral[+] */,
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
						iv._35) /* TemplateParameterSubstitutionCS */
				}
			);
		private final @NonNull EClassValue _48 // TemplateParameterSubstitutionCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._012, sl._24) /* ownedActualParameter=TypeRefCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						iv._60) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
				}
			);
		private final @NonNull EClassValue _49 // TemplateSignatureCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._032, sl._36) /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */,
					new SerializationRule_SegmentsList(sr0._031, sl._45) /* { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						iv._46) /* TypeParameterCS */
				}
			);
		private final @NonNull EClassValue _50 // TupleLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._053, sl._56) /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._094, sl._56) /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						iv._37) /* TupleLiteralPartCS */
				}
			);
		private final @NonNull EClassValue _51 // TupleLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._095, sl._55) /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _52 // TuplePartCS
			= new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._096, sl._58) /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _53 // TupleTypeCS
			= new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._097, sl._16) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._102, sl._17) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._106, sl._17) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._16) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						iv._38) /* TuplePartCS */
				}
			);
		private final @NonNull EClassValue _54 // TypeLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._057, sl._24) /* ownedType=TypeLiteralWithMultiplicityCS */,
					new SerializationRule_SegmentsList(sr1._104, sl._24) /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						iv._43) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
				}
			);
		private final @NonNull EClassValue _55 // TypeNameExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._101, sl._02) /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._109, sl._01) /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._5) /* CurlyBracketedClauseCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._16) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._22) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						iv._57) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _56 // TypeParameterCS
			= new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._013, sl._51) /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						iv._49) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _57 // TypedTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._014, sl._04) /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						iv._34) /* TemplateBindingCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						iv._22) /* PathNameCS */
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
						iv._45) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _60 // WildcardTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._015, sl._22) /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						iv._49) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
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
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._038 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._108 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._61,
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
		private @NonNull SerializationRule _001 = new SerializationRule(49,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._073 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._025 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || «null» */,
				st._084 /* 1*MultiplicityBoundsCS::lowerBound=LOWER || «? » «value» «? » */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._006 /* 1*'..' || «? » «value» «? » */,
				st._085 /* 1*MultiplicityBoundsCS::upperBound=UPPER || «? » «value» «? » */
			},
			sl._23,
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
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' }
		private @NonNull SerializationRule _002 = new SerializationRule(50,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._073 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._025 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._084 /* 1*MultiplicityBoundsCS::lowerBound=LOWER || «? » «value» «? » */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «? » «value» «? » */,
				st._085 /* 1*MultiplicityBoundsCS::upperBound=UPPER || «? » «value» «? » */,
				st._020 /* 1*']' || «! » «value» */
			},
			sl._10,
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
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _003 = new SerializationRule(50,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._105 /* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._073 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._025 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._084 /* 1*MultiplicityBoundsCS::lowerBound=LOWER || «? » «value» «? » */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «? » «value» «? » */,
				st._085 /* 1*MultiplicityBoundsCS::upperBound=UPPER || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._10, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
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
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} '|?' ']' }
		private @NonNull SerializationRule _004 = new SerializationRule(50,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._026 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._086 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._00, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _005 = new SerializationRule(50,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._074 /* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._026 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._086 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._152 /* V00*'|1' || «? » «value» «? » */,
				st._020 /* 1*']' || «! » «value» */
			},
			sl._13,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					ev._10),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._00)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._10, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._00, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' }
		private @NonNull SerializationRule _006 = new SerializationRule(50,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._073 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._025 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._084 /* 1*MultiplicityBoundsCS::lowerBound=LOWER || «? » «value» «? » */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «? » «value» «? » */,
				st._085 /* 1*MultiplicityBoundsCS::upperBound=UPPER || «? » «value» «? » */,
				st._045 /* 1*'|?' || «? » «value» «? » */,
				st._020 /* 1*']' || «! » «value» */
			},
			sl._11,
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
		private @NonNull SerializationRule _007 = new SerializationRule(50,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._026 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._086 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._020 /* 1*']' || «! » «value» */
			},
			sl._12,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._00)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._00, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityStringCS : stringBounds={'*|+|?'}
		private @NonNull SerializationRule _008 = new SerializationRule(51,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._026 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._086 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */
			},
			sl._61,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._00)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._00, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Base::NextPathElementCS : referredElement=UnreservedName
		private @NonNull SerializationRule _009 = new SerializationRule(61,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._038 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._106 /* 1*PathElementCS::referredElement=UnreservedName || «? » «value» «? » */
			},
			sl._61,
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
		private @NonNull SerializationRule _010 = new SerializationRule(67,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._134 /* check-rule basecs::PathNameCS.ownedPathElements : 30|61 */,
				ms._057 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || «null» */,
				st._109 /* 1*PathNameCS::ownedPathElements+=FirstPathElementCS || «null» */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'::' || «! » «value» «! » */,
				st._110 /* 1*PathNameCS::ownedPathElements+=NextPathElementCS || «null» */
			},
			sl._03,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._19) /* FirstPathElementCS|NextPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(61, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// Base::TemplateBindingCS : { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _011 = new SerializationRule(87,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._138 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 88 */,
				ms._137 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 50 */,
				ms._109 /* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				ms._060 /* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._147 /* 1*steps-1..6 || «null» */,
				st._122 /* 1*TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || «null» */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._122 /* 1*TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || «null» */,
				st._183 /* V01*TemplateBindingCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._05,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					iv._35) /* TemplateParameterSubstitutionCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(88, MultiplicativeCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// Base::TemplateParameterSubstitutionCS : ownedActualParameter=TypeRefCS
		private @NonNull SerializationRule _012 = new SerializationRule(88,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._139 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 101 */,
				ms._046 /* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._123 /* 1*TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					iv._47) /* TypeRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ONE)
					}
				)
			});
		// Base::TypeParameterCS : { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] }
		private @NonNull SerializationRule _013 = new SerializationRule(100,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._143 /* check-rule basecs::TypeParameterCS.ownedExtends : 102 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._064 /* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				ms._097 /* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._090 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._167 /* V00*steps-3..8 || «null» */,
				st._029 /* 1*'extends' || «? » «value» «? » */,
				st._131 /* 1*TypeParameterCS::ownedExtends+=TypedRefCS || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._000 /* 1*'&&' || «? » «value» «? » */,
				st._131 /* 1*TypeParameterCS::ownedExtends+=TypedRefCS || «null» */
			},
			sl._51,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					iv._48) /* TypedRefCS */
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
					new RuleIndex_MultiplicativeCardinality(102, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::TypedTypeRefCS : { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] }
		private @NonNull SerializationRule _014 = new SerializationRule(103,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._146 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 87 */,
				ms._147 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 67 */,
				ms._087 /* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				ms._051 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._147 /* 1*steps-1..6 || «null» */,
				st._134 /* 1*TypedTypeRefCS::ownedPathName=PathNameCS || «null» */,
				st._165 /* V00*steps-3..6 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._133 /* 1*TypedTypeRefCS::ownedBinding=TemplateBindingCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._04,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._34) /* TemplateBindingCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._22) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(87, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ONE)
					}
				)
			});
		// Base::WildcardTypeRefCS : { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
		private @NonNull SerializationRule _015 = new SerializationRule(114,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._148 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : 102 */,
				ms._089 /* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || «null» */,
				st._014 /* 1*'?' || «? » «value» «? » */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._029 /* 1*'extends' || «? » «value» «? » */,
				st._137 /* 1*WildcardTypeRefCS::ownedExtends=TypedRefCS || «null» */
			},
			sl._22,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					iv._48) /* TypedRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(102, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// CompleteOCL::ClassifierContextDeclCS : { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[+] ownedDefinitions+=DefCS[*] }
		private @NonNull SerializationRule _016 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._149 /* check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : 16 */,
				ms._141 /* check-rule basecs::TemplateableElementCS.ownedSignature : 89 */,
				ms._162 /* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
				ms._150 /* check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : 12 */,
				ms._123 /* assign V3 = |ClassifierContextDeclCS::ownedDefinitions| */,
				ms._118 /* assign V2 = |ClassifierContextDeclCS::ownedInvariants| */,
				ms._040 /* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
				ms._100 /* assign V1 = |ClassifierContextDeclCS::selfName| */,
				ms._084 /* assign V0 = |TemplateableElementCS::ownedSignature| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._022 /* 1*'context' || «? » «value» «? » */,
				st._159 /* V00*TemplateableElementCS::ownedSignature=TemplateSignatureCS || «null» */,
				st._178 /* V01*ClassifierContextDeclCS::selfName=UnrestrictedName || «? » «value» «? » */,
				st._112 /* 1*PathNameDeclCS::ownedPathName=PathNameCS || «null» */,
				st._200 /* V02*steps-6..8 || «null» */,
				st._034 /* 1*'inv' || «? » «value» «? » */,
				st._049 /* 1*ClassifierContextDeclCS::ownedInvariants+=ConstraintCS || «null» */,
				st._204 /* V03*ClassifierContextDeclCS::ownedDefinitions+=DefCS || «null» */
			},
			sl._52,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
					iv._6) /* DefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._36) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					iv._22) /* PathNameCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
					iv._3) /* ConstraintCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(16, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(89, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(12, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// CompleteOCL::ClassifierContextDeclCS : { 'context' ownedSignature=TemplateSignatureCS[?] selfName=UnrestrictedName[?] ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedDefinitions+=DefCS[+] }
		private @NonNull SerializationRule _017 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._149 /* check-rule completeoclcs::ClassifierContextDeclCS.ownedDefinitions : 16 */,
				ms._141 /* check-rule basecs::TemplateableElementCS.ownedSignature : 89 */,
				ms._162 /* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
				ms._150 /* check-rule completeoclcs::ClassifierContextDeclCS.ownedInvariants : 12 */,
				ms._123 /* assign V3 = |ClassifierContextDeclCS::ownedDefinitions| */,
				ms._118 /* assign V2 = |ClassifierContextDeclCS::ownedInvariants| */,
				ms._040 /* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
				ms._100 /* assign V1 = |ClassifierContextDeclCS::selfName| */,
				ms._084 /* assign V0 = |TemplateableElementCS::ownedSignature| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._022 /* 1*'context' || «? » «value» «? » */,
				st._159 /* V00*TemplateableElementCS::ownedSignature=TemplateSignatureCS || «null» */,
				st._178 /* V01*ClassifierContextDeclCS::selfName=UnrestrictedName || «? » «value» «? » */,
				st._112 /* 1*PathNameDeclCS::ownedPathName=PathNameCS || «null» */,
				st._200 /* V02*steps-6..8 || «null» */,
				st._034 /* 1*'inv' || «? » «value» «? » */,
				st._049 /* 1*ClassifierContextDeclCS::ownedInvariants+=ConstraintCS || «null» */,
				st._204 /* V03*ClassifierContextDeclCS::ownedDefinitions+=DefCS || «null» */
			},
			sl._52,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
					iv._6) /* DefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._36) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					iv._22) /* PathNameCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
					iv._3) /* ConstraintCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(16, MultiplicativeCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(89, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(12, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// CompleteOCL::CompleteOCLDocumentCS : { ownedImports+=ImportCS[*] ownedPackages+=PackageDeclarationCS[*] ownedContexts+=ContextDeclCS[*] }
		private @NonNull SerializationRule _018 = new SerializationRule(10,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._151 /* check-rule completeoclcs::CompleteOCLDocumentCS.ownedContexts : 13 */,
				ms._136 /* check-rule basecs::RootCS.ownedImports : 35 */,
				ms._152 /* check-rule completeoclcs::CompleteOCLDocumentCS.ownedPackages : 65 */,
				ms._119 /* assign V2 = |CompleteOCLDocumentCS::ownedContexts| */,
				ms._102 /* assign V1 = |CompleteOCLDocumentCS::ownedPackages| */,
				ms._082 /* assign V0 = |RootCS::ownedImports| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._157 /* V00*RootCS::ownedImports+=ImportCS || «null» */,
				st._180 /* V01*CompleteOCLDocumentCS::ownedPackages+=PackageDeclarationCS || «null» */,
				st._198 /* V02*CompleteOCLDocumentCS::ownedContexts+=ContextDeclCS || «null» */
			},
			sl._25,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS,
					iv._4) /* ContextDeclCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					iv._12) /* ImportCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES,
					iv._20) /* PackageDeclarationCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_CONTEXTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(35, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.COMPLETE_OCL_DOCUMENT_CS__OWNED_PACKAGES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(65, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// CompleteOCL::ConstraintCS : { { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS }
		private @NonNull SerializationRule _019 = new SerializationRule(12,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._131 /* check-rule basecs::ConstraintCS.ownedSpecification : 83 */,
				ms._130 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 83 */,
				ms._006 /* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				ms._075 /* assign V0 = |NamedElementCS::name| */,
				ms._103 /* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._162 /* V00*steps-2..7 || «null» */,
				st._090 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._188 /* V01*steps-4..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._060 /* 1*ConstraintCS::ownedMessageSpecification=SpecificationCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._061 /* 1*ConstraintCS::ownedSpecification=SpecificationCS || «null» */
			},
			sl._34,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._31) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._31) /* SpecificationCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(83, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(83, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// CompleteOCL::DefOperationCS : { isStatic='static'[?] 'def' ':' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=DefParameterCS { ',' ownedParameters+=DefParameterCS }[*] }[?] ')' ':' ownedType=TypeExpCS[?] '=' ownedSpecification=SpecificationCS }
		private @NonNull SerializationRule _020 = new SerializationRule(17,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._154 /* check-rule completeoclcs::DefOperationCS.ownedParameters : 18 */,
				ms._141 /* check-rule basecs::TemplateableElementCS.ownedSignature : 89 */,
				ms._144 /* check-rule basecs::TypedElementCS.ownedType : 94 */,
				ms._153 /* check-rule completeoclcs::DefCS.ownedSpecification : 83 */,
				ms._008 /* assert (|DefCS::ownedSpecification| - 1) == 0 */,
				ms._127 /* assign V4 = |TypedElementCS::ownedType| */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._110 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._069 /* assign V0 = |DefCS::isStatic.'static'| */,
				ms._114 /* assign V2 = (|DefOperationCS::ownedParameters| > 0) */,
				ms._121 /* assign V3 = (|DefOperationCS::ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._142 /* 1*steps-1..17 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._151 /* V00*'static' || «? » «value» «? » */,
				st._023 /* 1*'def' || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._184 /* V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS || «null» */,
				st._090 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._203 /* V02*steps-8..12 || «null» */,
				st._065 /* 1*DefOperationCS::ownedParameters+=DefParameterCS || «null» */,
				st._207 /* V03*steps-10..12 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._065 /* 1*DefOperationCS::ownedParameters+=DefParameterCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._209 /* V04*TypedElementCS::ownedType=TypeExpCS || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._064 /* 1*DefCS::ownedSpecification=SpecificationCS || «null» */
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
					iv._36) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					iv._31) /* SpecificationCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._08, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.DEF_OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(18, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(89, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(83, MultiplicativeCardinality.ONE)
					}
				)
			});
		// CompleteOCL::DefParameterCS : { name=UnrestrictedName ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _021 = new SerializationRule(18,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._144 /* check-rule basecs::TypedElementCS.ownedType : 94 */,
				ms._050 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._090 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._132 /* 1*TypedElementCS::ownedType=TypeExpCS || «null» */
			},
			sl._58,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ONE)
					}
				)
			});
		// CompleteOCL::DefPropertyCS : { isStatic='static'[?] 'def' ':' name=UnrestrictedName ':' ownedType=TypeExpCS '=' ownedSpecification=SpecificationCS }
		private @NonNull SerializationRule _022 = new SerializationRule(19,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._144 /* check-rule basecs::TypedElementCS.ownedType : 94 */,
				ms._153 /* check-rule completeoclcs::DefCS.ownedSpecification : 83 */,
				ms._008 /* assert (|DefCS::ownedSpecification| - 1) == 0 */,
				ms._050 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._069 /* assign V0 = |DefCS::isStatic.'static'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._151 /* V00*'static' || «? » «value» «? » */,
				st._023 /* 1*'def' || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._090 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._132 /* 1*TypedElementCS::ownedType=TypeExpCS || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._064 /* 1*DefCS::ownedSpecification=SpecificationCS || «null» */
			},
			sl._60,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC,
					ev._08)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					iv._31) /* SpecificationCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.DEF_CS__IS_STATIC,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._08, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.DEF_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(83, MultiplicativeCardinality.ONE)
					}
				)
			});
		// CompleteOCL::ImportCS : { {'import'|'include'|'library'} { name=Identifier ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] }
		private @NonNull SerializationRule _023 = new SerializationRule(35,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._132 /* check-rule basecs::ImportCS.ownedPathName : 108 */,
				ms._104 /* assign V1 = |ImportCS::isAll.'::*'| */,
				ms._016 /* assert (|ImportCS::ownedPathName| - 1) == 0 */,
				ms._075 /* assign V0 = |NamedElementCS::name| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._031 /* 1*'import' || «null» */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._088 /* 1*NamedElementCS::name=Identifier || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*ImportCS::ownedPathName=URIPathNameCS || «null» */,
				st._175 /* V01*'::*' || «? » «value» «? » */
			},
			sl._29,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
					ev._02)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					iv._52) /* URIPathNameCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._02, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(108, MultiplicativeCardinality.ONE)
					}
				)
			});
		// CompleteOCL::NavigatingArgExpCS : '?'
		private @NonNull SerializationRule _024 = new SerializationRule(55,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
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
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._159 /* check-rule completeoclcs::OperationContextDeclCS.ownedPreconditions : 12 */,
				ms._157 /* check-rule completeoclcs::OperationContextDeclCS.ownedParameters : 66 */,
				ms._158 /* check-rule completeoclcs::OperationContextDeclCS.ownedPostconditions : 12 */,
				ms._155 /* check-rule completeoclcs::FeatureContextDeclCS.ownedType : 94 */,
				ms._141 /* check-rule basecs::TemplateableElementCS.ownedSignature : 89 */,
				ms._162 /* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
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
			new @NonNull RTSerializationStep @NonNull [] {
				st._143 /* 1*steps-1..23 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._022 /* 1*'context' || «? » «value» «? » */,
				st._159 /* V00*TemplateableElementCS::ownedSignature=TemplateSignatureCS || «null» */,
				st._112 /* 1*PathNameDeclCS::ownedPathName=PathNameCS || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._192 /* V01*steps-6..10 || «null» */,
				st._098 /* 1*OperationContextDeclCS::ownedParameters+=ParameterCS || «null» */,
				st._202 /* V02*steps-8..10 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._098 /* 1*OperationContextDeclCS::ownedParameters+=ParameterCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._205 /* V03*FeatureContextDeclCS::ownedType=TypeExpCS || «null» */,
				st._210 /* V04*steps-14..16 || «null» */,
				st._040 /* 1*'pre' || «? » «value» «? » */,
				st._100 /* 1*OperationContextDeclCS::ownedPreconditions+=ConstraintCS || «null» */,
				st._211 /* V05*steps-17..19 || «null» */,
				st._039 /* 1*'post' || «? » «value» «? » */,
				st._099 /* 1*OperationContextDeclCS::ownedPostconditions+=ConstraintCS || «null» */,
				st._212 /* V06*steps-20..23 || «null» */,
				st._021 /* 1*'body' || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._097 /* 1*OperationContextDeclCS::ownedBodies+=SpecificationCS || «null» */
			},
			sl._44,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS,
					iv._3) /* ConstraintCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS,
					iv._21) /* ParameterCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS,
					iv._3) /* ConstraintCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._36) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					iv._22) /* PathNameCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES,
					iv._31) /* SpecificationCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(12, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(66, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(12, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(89, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(83, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// CompleteOCL::PackageDeclarationCS : { 'package' ownedPathName=PathNameCS { 'inv' ownedInvariants+=ConstraintCS }[*] ownedContexts+=ContextDeclCS[*] 'endpackage' }
		private @NonNull SerializationRule _026 = new SerializationRule(65,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._161 /* check-rule completeoclcs::PackageDeclarationCS.ownedInvariants : 12 */,
				ms._160 /* check-rule completeoclcs::PackageDeclarationCS.ownedContexts : 13 */,
				ms._162 /* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
				ms._108 /* assign V1 = |PackageDeclarationCS::ownedContexts| */,
				ms._079 /* assign V0 = |PackageDeclarationCS::ownedInvariants| */,
				ms._040 /* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._038 /* 1*'package' || «? » «value» «? » */,
				st._112 /* 1*PathNameDeclCS::ownedPathName=PathNameCS || «null» */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._034 /* 1*'inv' || «? » «value» «? » */,
				st._104 /* 1*PackageDeclarationCS::ownedInvariants+=ConstraintCS || «null» */,
				st._181 /* V01*PackageDeclarationCS::ownedContexts+=ContextDeclCS || «null» */,
				st._028 /* 1*'endpackage' || «? » «value» «? » */
			},
			sl._47,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS,
					iv._3) /* ConstraintCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS,
					iv._4) /* ContextDeclCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					iv._22) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_INVARIANTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(12, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.PACKAGE_DECLARATION_CS__OWNED_CONTEXTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ONE)
					}
				)
			});
		// CompleteOCL::ParameterCS : { { name=UnrestrictedName ':' }[?] ownedType=TypeExpCS }
		private @NonNull SerializationRule _027 = new SerializationRule(66,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._144 /* check-rule basecs::TypedElementCS.ownedType : 94 */,
				ms._050 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._075 /* assign V0 = |NamedElementCS::name| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._161 /* V00*steps-2..4 || «null» */,
				st._090 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._132 /* 1*TypedElementCS::ownedType=TypeExpCS || «null» */
			},
			sl._35,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ONE)
					}
				)
			});
		// CompleteOCL::PropertyContextDeclCS : { 'context' ownedPathName=PathNameCS ':' ownedType=TypeExpCS { 'derive' ':' ownedDefaultExpressions+=SpecificationCS }[*] { 'init' ':' ownedDefaultExpressions+=SpecificationCS }[*] }
		private @NonNull SerializationRule _028 = new SerializationRule(75,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._155 /* check-rule completeoclcs::FeatureContextDeclCS.ownedType : 94 */,
				ms._162 /* check-rule completeoclcs::PathNameDeclCS.ownedPathName : 67 */,
				ms._163 /* check-rule completeoclcs::PropertyContextDeclCS.ownedDefaultExpressions : 83 */,
				ms._010 /* assert (|FeatureContextDeclCS::ownedType| - 1) == 0 */,
				ms._040 /* assert (|PathNameDeclCS::ownedPathName| - 1) == 0 */,
				ms._081 /* assign V0 = |PropertyContextDeclCS::ownedDefaultExpressions| */,
				ms._098 /* assign V1 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._141 /* 1*steps-1..13 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._022 /* 1*'context' || «? » «value» «? » */,
				st._112 /* 1*PathNameDeclCS::ownedPathName=PathNameCS || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*FeatureContextDeclCS::ownedType=TypeExpCS || «null» */,
				st._174 /* V00*steps-6..9 || «null» */,
				st._024 /* 1*'derive' || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._115 /* 1*PropertyContextDeclCS::ownedDefaultExpressions+=SpecificationCS || «null» */,
				st._186 /* V01*steps-10..13 || «null» */,
				st._033 /* 1*'init' || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._115 /* 1*PropertyContextDeclCS::ownedDefaultExpressions+=SpecificationCS || «null» */
			},
			sl._54,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					iv._22) /* PathNameCS */,
				new EReference_RuleIndexes(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._31) /* SpecificationCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.FEATURE_CONTEXT_DECL_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.PATH_NAME_DECL_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(83, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// CompleteOCL::SpecificationCS : exprString=UNQUOTED_STRING
		private @NonNull SerializationRule _029 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._045 /* assert (|SpecificationCS::exprString| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._120 /* 1*SpecificationCS::exprString=UNQUOTED_STRING || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// CompleteOCL::SpecificationCS : ownedExpression=ExpCS
		private @NonNull SerializationRule _030 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._179 /* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 29 */,
				ms._009 /* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._066 /* 1*ExpSpecificationCS::ownedExpression=ExpCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				)
			});
		// CompleteOCL::TemplateSignatureCS : { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' }
		private @NonNull SerializationRule _031 = new SerializationRule(89,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._140 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 100 */,
				ms._061 /* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._010 /* 1*'<' || «? » «value» «? » */,
				st._124 /* 1*TemplateSignatureCS::ownedParameters+=TypeParameterCS || «null» */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._124 /* 1*TemplateSignatureCS::ownedParameters+=TypeParameterCS || «null» */,
				st._013 /* 1*'>' || «? » «value» «? » */
			},
			sl._45,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._46) /* TypeParameterCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(100, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// CompleteOCL::TemplateSignatureCS : { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' }
		private @NonNull SerializationRule _032 = new SerializationRule(89,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._140 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 100 */,
				ms._061 /* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._124 /* 1*TemplateSignatureCS::ownedParameters+=TypeParameterCS || «null» */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._124 /* 1*TemplateSignatureCS::ownedParameters+=TypeParameterCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._36,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._46) /* TypeParameterCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(100, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::BooleanLiteralExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _033 = new SerializationRule(2,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._001 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._048 /* 1*BooleanLiteralExpCS::symbol || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._07)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._07, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::CoIteratorVariableCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] }
		private @NonNull SerializationRule _034 = new SerializationRule(4,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._216 /* check-rule essentialoclcs::VariableCS.ownedType : 94 */,
				ms._088 /* assign V0 = |VariableCS::ownedType| */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._090 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._136 /* 1*VariableCS::ownedType=TypeExpCS || «null» */
			},
			sl._50,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _035 = new SerializationRule(5,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._168 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 6 */,
				ms._169 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 8 */,
				ms._002 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._053 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._090 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._051 /* 1*CollectionLiteralExpCS::ownedType=CollectionTypeCS || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._170 /* V00*steps-4..8 || «null» */,
				st._050 /* 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._050 /* 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._30,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					iv._1) /* CollectionLiteralPartCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(6, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(8, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : ownedExpression=PatternExpCS
		private @NonNull SerializationRule _036 = new SerializationRule(6,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._171 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 68 */,
				ms._003 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._053 /* 1*CollectionLiteralPartCS::ownedExpression=PatternExpCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					iv._23) /* PatternExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] }
		private @NonNull SerializationRule _037 = new SerializationRule(6,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._170 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 29 */,
				ms._172 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 29 */,
				ms._066 /* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
				ms._003 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._052 /* 1*CollectionLiteralPartCS::ownedExpression=ExpCS || «null» */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._006 /* 1*'..' || «? » «value» «? » */,
				st._054 /* 1*CollectionLiteralPartCS::ownedLastExpression=ExpCS || «null» */
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
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CollectionPatternCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' }
		private @NonNull SerializationRule _038 = new SerializationRule(7,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._174 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 8 */,
				ms._173 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 68 */,
				ms._067 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._091 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._004 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._139 /* 1*steps-1..11 || «null» */,
				st._056 /* 1*CollectionPatternCS::ownedType=CollectionTypeCS || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._168 /* V00*steps-4..10 || «null» */,
				st._055 /* 1*CollectionPatternCS::ownedParts+=PatternExpCS || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._055 /* 1*CollectionPatternCS::ownedParts+=PatternExpCS || «null» */,
				st._004 /* 1*'++' || «? » «value» «? » */,
				st._057 /* 1*CollectionPatternCS::restVariableName=Identifier || «? » «value» «? » */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._06,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._23) /* PatternExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(8, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::CollectionTypeCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
		private @NonNull SerializationRule _039 = new SerializationRule(8,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._175 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 50 */,
				ms._176 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 95 */,
				ms._068 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._101 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || «null» */,
				st._058 /* 1*CollectionTypeCS::name=CollectionTypeIdentifier || «? » «value» «? » */,
				st._166 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._059 /* 1*CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || «null» */,
				st._179 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._18,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._16) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._40) /* TypeExpWithoutMultiplicityCS */
			},
			new /*@NonNull*/ EAttribute [] {
				EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(95, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CurlyBracketedClauseCS : { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _040 = new SerializationRule(14,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._178 /* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 81 */,
				ms._054 /* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
				ms._092 /* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._166 /* V00*steps-3..7 || «null» */,
				st._063 /* 1*CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || «null» */,
				st._189 /* V01*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._063 /* 1*CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._14,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					iv._30) /* ShadowPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(81, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ElseIfThenExpCS : { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS }
		private @NonNull SerializationRule _041 = new SerializationRule(22,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._185 /* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 29 */,
				ms._184 /* check-rule essentialoclcs::IfThenExpCS.ownedCondition : 29 */,
				ms._015 /* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
				ms._014 /* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._026 /* 1*'elseif' || «? » «value» «? » */,
				st._071 /* 1*IfThenExpCS::ownedCondition=ExpCS || «null» */,
				st._042 /* 1*'then' || «? » «value» «? » */,
				st._072 /* 1*IfThenExpCS::ownedThenExpression=ExpCS || «null» */
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
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _042 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._001 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._048 /* 1*BooleanLiteralExpCS::symbol || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._07)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._07, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::ExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _043 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._201 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 29 */,
				ms._035 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._095 /* 1*NestedExpCS::ownedExpression=ExpCS || «null» */,
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
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _044 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._204 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 70 */,
				ms._037 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._089 /* 1*NamedElementCS::name=UnaryOperatorName || «? » «value» «? » */,
				st._103 /* 1*OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || «null» */
			},
			sl._43,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._27) /* PrefixedPrimaryExpCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(70, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : 'self'
		private @NonNull SerializationRule _045 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._041 /* 1*'self' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _046 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._164 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14 */,
				ms._166 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 76 */,
				ms._167 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 84 */,
				ms._165 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 67 */,
				ms._122 /* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				ms._117 /* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				ms._099 /* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				ms._065 /* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				ms._000 /* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._047 /* 1*AbstractNameExpCS::ownedPathName=PathNameCS || «null» */,
				st._153 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || «null» */,
				st._177 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || «null» */,
				st._197 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || «null» */,
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
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._29) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._32) /* SquareBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._22) /* PathNameCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._04, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(14, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(76, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(84, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _047 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._192 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 47 */,
				ms._191 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 46 */,
				ms._020 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._056 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._093 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._079 /* 1*MapLiteralExpCS::ownedType=MapTypeCS || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._170 /* V00*steps-4..8 || «null» */,
				st._078 /* 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._078 /* 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS || «null» */,
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
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(47, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(46, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _048 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._168 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 6 */,
				ms._169 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 8 */,
				ms._002 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._053 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._090 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._051 /* 1*CollectionLiteralExpCS::ownedType=CollectionTypeCS || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._170 /* V00*steps-4..8 || «null» */,
				st._050 /* 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._050 /* 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._30,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					iv._1) /* CollectionLiteralPartCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(6, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(8, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _049 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._083 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._158 /* V00*StringLiteralExpCS::segments+=StringLiteral || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			},
			null);
		// EssentialOCL::ExpCS : '*'
		private @NonNull SerializationRule _050 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._003 /* 1*'*' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : 'null'
		private @NonNull SerializationRule _051 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._037 /* 1*'null' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _052 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._187 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 29 */,
				ms._018 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._016 /* 1*'Lambda' || «? » «value» «? » */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._075 /* 1*LambdaLiteralExpCS::ownedExpressionCS=ExpCS || «null» */,
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
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
		private @NonNull SerializationRule _053 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._210 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 91 */,
				ms._062 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._018 /* 1*'Tuple' || «? » «value» «? » */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._125 /* 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || «null» */,
				st._172 /* V00*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._125 /* 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._56,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._37) /* TupleLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(91, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS }
		private @NonNull SerializationRule _054 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._202 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 29 */,
				ms._186 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : 70 */,
				ms._037 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._017 /* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._074 /* 1*InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || «null» */,
				st._087 /* 1*NamedElementCS::name=BinaryOperatorName || «? » «value» «? » */,
				st._101 /* 1*OperatorExpCS::ownedRight=ExpCS || «null» */
			},
			sl._31,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					iv._27) /* PrefixedPrimaryExpCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(70, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _055 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._181 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 29 */,
				ms._180 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 29|68 */,
				ms._183 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 29 */,
				ms._182 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 22 */,
				ms._012 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._070 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._013 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._011 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._030 /* 1*'if' || «? » «value» «? » */,
				st._068 /* 1*IfExpCS::ownedCondition=ExpCS|PatternExpCS || «null» */,
				st._042 /* 1*'then' || «? » «value» «? » */,
				st._070 /* 1*IfExpCS::ownedThenExpression=ExpCS || «null» */,
				st._154 /* V00*IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || «null» */,
				st._025 /* 1*'else' || «? » «value» «? » */,
				st._069 /* 1*IfExpCS::ownedElseExpression=ExpCS || «null» */,
				st._027 /* 1*'endif' || «? » «value» «? » */
			},
			sl._53,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._24) /* ExpCS|PatternExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._9) /* ElseIfThenExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(22, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _056 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._036 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._096 /* 1*NumberLiteralExpCS::symbol=NUMBER_LITERAL || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::ExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _057 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._211 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 98 */,
				ms._048 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._127 /* 1*TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._42) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(98, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : 'invalid'
		private @NonNull SerializationRule _058 = new SerializationRule(29,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._035 /* 1*'invalid' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::IfExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _059 = new SerializationRule(34,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._181 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 29 */,
				ms._180 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 29|68 */,
				ms._183 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 29 */,
				ms._182 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 22 */,
				ms._012 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._070 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._013 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._011 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._030 /* 1*'if' || «? » «value» «? » */,
				st._068 /* 1*IfExpCS::ownedCondition=ExpCS|PatternExpCS || «null» */,
				st._042 /* 1*'then' || «? » «value» «? » */,
				st._070 /* 1*IfExpCS::ownedThenExpression=ExpCS || «null» */,
				st._154 /* V00*IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || «null» */,
				st._025 /* 1*'else' || «? » «value» «? » */,
				st._069 /* 1*IfExpCS::ownedElseExpression=ExpCS || «null» */,
				st._027 /* 1*'endif' || «? » «value» «? » */
			},
			sl._53,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._24) /* ExpCS|PatternExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._9) /* ElseIfThenExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(22, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::InvalidLiteralExpCS : 'invalid'
		private @NonNull SerializationRule _060 = new SerializationRule(37,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
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
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._187 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 29 */,
				ms._018 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._016 /* 1*'Lambda' || «? » «value» «? » */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._075 /* 1*LambdaLiteralExpCS::ownedExpressionCS=ExpCS || «null» */,
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
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::LetExpCS : { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
		private @NonNull SerializationRule _062 = new SerializationRule(41,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._188 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : 29 */,
				ms._189 /* check-rule essentialoclcs::LetExpCS.ownedVariables : 42 */,
				ms._019 /* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
				ms._055 /* assign V0 = (|LetExpCS::ownedVariables| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._036 /* 1*'let' || «? » «value» «? » */,
				st._077 /* 1*LetExpCS::ownedVariables+=LetVariableCS || «null» */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._077 /* 1*LetExpCS::ownedVariables+=LetVariableCS || «null» */,
				st._032 /* 1*'in' || «? » «value» «? » */,
				st._076 /* 1*LetExpCS::ownedInExpression=ExpCS || «null» */
			},
			sl._46,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					iv._13) /* LetVariableCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(42, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::LetVariableCS : { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _063 = new SerializationRule(42,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._215 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 29 */,
				ms._216 /* check-rule essentialoclcs::VariableCS.ownedType : 94 */,
				ms._190 /* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 76 */,
				ms._052 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._113 /* assign V1 = |VariableCS::ownedType| */,
				ms._071 /* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._090 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._155 /* V00*LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || «null» */,
				st._187 /* V01*steps-4..6 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._136 /* 1*VariableCS::ownedType=TypeExpCS || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._135 /* 1*VariableCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._49,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._29) /* RoundBracketedClauseCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(76, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
	}
	private class _SerializationRules1
	{
		// EssentialOCL::MapLiteralExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _064 = new SerializationRule(45,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._192 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 47 */,
				ms._191 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 46 */,
				ms._020 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._056 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._093 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._079 /* 1*MapLiteralExpCS::ownedType=MapTypeCS || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._170 /* V00*steps-4..8 || «null» */,
				st._078 /* 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._078 /* 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS || «null» */,
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
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(47, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(46, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::MapLiteralPartCS : { ownedKey=ExpCS '<-' ownedValue=ExpCS }
		private @NonNull SerializationRule _065 = new SerializationRule(46,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._193 /* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 29 */,
				ms._194 /* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 29 */,
				ms._022 /* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
				ms._021 /* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._080 /* 1*MapLiteralPartCS::ownedKey=ExpCS || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._081 /* 1*MapLiteralPartCS::ownedValue=ExpCS || «null» */
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
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::MapTypeCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
		private @NonNull SerializationRule _066 = new SerializationRule(47,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._196 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 94 */,
				ms._195 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 94 */,
				ms._072 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._024 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._023 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._017 /* 1*'Map' || «? » «value» «? » */,
				st._167 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._082 /* 1*MapTypeCS::ownedKeyType=TypeExpCS || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._083 /* 1*MapTypeCS::ownedValueType=TypeExpCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._20,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._39) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._05, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::Model : ownedExpression=ExpCS
		private @NonNull SerializationRule _067 = new SerializationRule(48,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._177 /* check-rule essentialoclcs::ContextCS.ownedExpression : 29 */,
				ms._007 /* assert (|ContextCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._062 /* 1*ContextCS::ownedExpression=ExpCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NameExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _068 = new SerializationRule(53,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._164 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14 */,
				ms._166 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 76 */,
				ms._167 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 84 */,
				ms._165 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 67 */,
				ms._122 /* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				ms._117 /* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				ms._099 /* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				ms._065 /* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				ms._000 /* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._047 /* 1*AbstractNameExpCS::ownedPathName=PathNameCS || «null» */,
				st._153 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || «null» */,
				st._177 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || «null» */,
				st._197 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || «null» */,
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
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._29) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._32) /* SquareBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._22) /* PathNameCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._04, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(14, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(76, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(84, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _069 = new SerializationRule(54,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._197 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._029 /* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				ms._106 /* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				ms._078 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._138 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._091 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || «null» */,
				st._032 /* 1*'in' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._28,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(4, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _070 = new SerializationRule(54,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._197 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._107 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				ms._076 /* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				ms._031 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._138 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */,
				st._172 /* V00*steps-5..7 || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._091 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || «null» */,
				st._195 /* V01*steps-8..10 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._33,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(4, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : ownedNameExpression=NavigatingArgExpCS
		private @NonNull SerializationRule _071 = new SerializationRule(54,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _072 = new SerializationRule(54,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._031 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */
			},
			sl._43,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _073 = new SerializationRule(54,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._197 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._077 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._028 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._091 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || «null» */,
				st._172 /* V00*steps-5..7 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._32,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(4, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingBarArgCS : { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _074 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._078 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._034 /* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
				ms._107 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._044 /* 1*'|' || «? » «value» «? » */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._171 /* V00*steps-4..9 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */,
				st._194 /* V01*steps-7..9 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._48,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._09)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._09, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _075 = new SerializationRule(57,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._197 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._077 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._028 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._091 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || «null» */,
				st._173 /* V00*steps-6..8 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._40,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(4, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS }
		private @NonNull SerializationRule _076 = new SerializationRule(57,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */
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
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _077 = new SerializationRule(57,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._197 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._107 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				ms._076 /* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				ms._031 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._139 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */,
				st._173 /* V00*steps-6..8 || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._091 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || «null» */,
				st._196 /* V01*steps-9..11 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._41,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(4, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _078 = new SerializationRule(57,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._197 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 4 */,
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._029 /* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				ms._106 /* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				ms._078 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._139 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */,
				st._194 /* V01*steps-7..9 || «null» */,
				st._011 /* 1*'<-' || «? » «value» «? » */,
				st._091 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || «null» */,
				st._032 /* 1*'in' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._39,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(4, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingSemiArgCS : { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _079 = new SerializationRule(58,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._198 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 29 */,
				ms._200 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 94 */,
				ms._199 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 55 */,
				ms._078 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._030 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._033 /* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
				ms._107 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._009 /* 1*';' || «! » «value» «?\n» */,
				st._093 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._171 /* V00*steps-4..9 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._094 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */,
				st._194 /* V01*steps-7..9 || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._092 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._42,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._03)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._17) /* NavigatingArgExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._03, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NestedExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _080 = new SerializationRule(60,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._201 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 29 */,
				ms._035 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._095 /* 1*NestedExpCS::ownedExpression=ExpCS || «null» */,
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
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NullLiteralExpCS : 'null'
		private @NonNull SerializationRule _081 = new SerializationRule(62,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
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
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._036 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._096 /* 1*NumberLiteralExpCS::symbol=NUMBER_LITERAL || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::PatternExpCS : { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS }
		private @NonNull SerializationRule _083 = new SerializationRule(68,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._205 /* check-rule essentialoclcs::PatternExpCS.ownedPatternType : 94 */,
				ms._041 /* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
				ms._080 /* assign V0 = |PatternExpCS::patternVariableName| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._156 /* V00*PatternExpCS::patternVariableName=UnrestrictedName || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._113 /* 1*PatternExpCS::ownedPatternType=TypeExpCS || «null» */
			},
			sl._58,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					iv._39) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedLetExpCS : { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		private @NonNull SerializationRule _084 = new SerializationRule(69,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._203 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 69 */,
				ms._037 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._089 /* 1*NamedElementCS::name=UnaryOperatorName || «? » «value» «? » */,
				st._102 /* 1*OperatorExpCS::ownedRight=PrefixedLetExpCS || «null» */
			},
			sl._43,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._25) /* PrefixedLetExpCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(69, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedPrimaryExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _085 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._204 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 70 */,
				ms._037 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._089 /* 1*NamedElementCS::name=UnaryOperatorName || «? » «value» «? » */,
				st._103 /* 1*OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || «null» */
			},
			sl._43,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._27) /* PrefixedPrimaryExpCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(70, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrimitiveTypeCS : name=PrimitiveTypeIdentifier
		private @NonNull SerializationRule _086 = new SerializationRule(73,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._042 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || «? » «value» «? » */
			},
			sl._61,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::RoundBracketedClauseCS : { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' }
		private @NonNull SerializationRule _087 = new SerializationRule(76,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._206 /* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 54|56|57|58 */,
				ms._058 /* assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0) */,
				ms._095 /* assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._147 /* 1*steps-1..6 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._116 /* 1*RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || «null» */,
				st._182 /* V01*RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._08,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					iv._18) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(58, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(57, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(54, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::SelfExpCS : 'self'
		private @NonNull SerializationRule _088 = new SerializationRule(80,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._041 /* 1*'self' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ShadowPartCS : { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) }
		private @NonNull SerializationRule _089 = new SerializationRule(81,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._207 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 29|68 */,
				ms._043 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
				ms._044 /* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._119 /* 1*ShadowPartCS::referredProperty=UnrestrictedName || «? » «value» «? » */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._117 /* 1*ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || «null» */
			},
			sl._58,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._24) /* ExpCS|PatternExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ShadowPartCS : ownedInitExpression=StringLiteralExpCS
		private @NonNull SerializationRule _090 = new SerializationRule(81,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._208 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 86 */,
				ms._043 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._118 /* 1*ShadowPartCS::ownedInitExpression=StringLiteralExpCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._33) /* StringLiteralExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(86, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::SimplePathNameCS : ownedPathElements+=FirstPathElementCS
		private @NonNull SerializationRule _091 = new SerializationRule(82,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._133 /* check-rule basecs::PathNameCS.ownedPathElements : 30 */,
				ms._039 /* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._109 /* 1*PathNameCS::ownedPathElements+=FirstPathElementCS || «null» */
			},
			sl._00,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._11) /* FirstPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::SquareBracketedClauseCS : { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' }
		private @NonNull SerializationRule _092 = new SerializationRule(84,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._209 /* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 29 */,
				ms._059 /* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || «null» */,
				st._019 /* 1*'[' || «! » «value» «! » */,
				st._121 /* 1*SquareBracketedClauseCS::ownedTerms+=ExpCS || «null» */,
				st._169 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._121 /* 1*SquareBracketedClauseCS::ownedTerms+=ExpCS || «null» */,
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
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::StringLiteralExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _093 = new SerializationRule(86,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._083 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._158 /* V00*StringLiteralExpCS::segments+=StringLiteral || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			},
			null);
		// EssentialOCL::TupleLiteralExpCS : { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
		private @NonNull SerializationRule _094 = new SerializationRule(90,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._210 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 91 */,
				ms._062 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._018 /* 1*'Tuple' || «? » «value» «? » */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._125 /* 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || «null» */,
				st._172 /* V00*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._125 /* 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._56,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._37) /* TupleLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(91, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::TupleLiteralPartCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _095 = new SerializationRule(91,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._215 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 29 */,
				ms._216 /* check-rule essentialoclcs::VariableCS.ownedType : 94 */,
				ms._052 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._088 /* assign V0 = |VariableCS::ownedType| */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._148 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._090 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._136 /* 1*VariableCS::ownedType=TypeExpCS || «null» */,
				st._012 /* 1*'=' || «? » «value» «? » */,
				st._135 /* 1*VariableCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._55,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TuplePartCS : { name=UnrestrictedName ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _096 = new SerializationRule(92,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._144 /* check-rule basecs::TypedElementCS.ownedType : 94 */,
				ms._050 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._027 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._145 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._090 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._132 /* 1*TypedElementCS::ownedType=TypeExpCS || «null» */
			},
			sl._58,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._39) /* TypeExpCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TupleTypeCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
		private @NonNull SerializationRule _097 = new SerializationRule(93,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._142 /* check-rule basecs::TupleTypeCS.ownedParts : 92 */,
				ms._047 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._063 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._096 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._116 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._138 /* 1*steps-1..10 || «null» */,
				st._018 /* 1*'Tuple' || «? » «value» «? » */,
				st._163 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._191 /* V01*steps-5..9 || «null» */,
				st._126 /* 1*TupleTypeCS::ownedParts+=TuplePartCS || «null» */,
				st._201 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._126 /* 1*TupleTypeCS::ownedParts+=TuplePartCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._16,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._06)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._38) /* TuplePartCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._06, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _098 = new SerializationRule(94,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._086 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._042 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || «null» */,
				st._114 /* 1*PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || «? » «value» «? » */,
				st._160 /* V00*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
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
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _099 = new SerializationRule(94,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._196 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 94 */,
				ms._195 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 94 */,
				ms._112 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._072 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._024 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._023 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || «null» */,
				st._017 /* 1*'Map' || «? » «value» «? » */,
				st._167 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._082 /* 1*MapTypeCS::ownedKeyType=TypeExpCS || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._083 /* 1*MapTypeCS::ownedValueType=TypeExpCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._185 /* V01*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
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
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._39) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._05, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _100 = new SerializationRule(94,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._174 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 8 */,
				ms._173 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 68 */,
				ms._120 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._067 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._091 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._004 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._140 /* 1*steps-1..12 || «null» */,
				st._056 /* 1*CollectionPatternCS::ownedType=CollectionTypeCS || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._168 /* V00*steps-4..10 || «null» */,
				st._055 /* 1*CollectionPatternCS::ownedParts+=PatternExpCS || «null» */,
				st._193 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._055 /* 1*CollectionPatternCS::ownedParts+=PatternExpCS || «null» */,
				st._004 /* 1*'++' || «? » «value» «? » */,
				st._057 /* 1*CollectionPatternCS::restVariableName=Identifier || «? » «value» «? » */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._199 /* V02*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._07,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._23) /* PatternExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(8, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _101 = new SerializationRule(94,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._214 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 29 */,
				ms._213 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 67 */,
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._212 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14 */,
				ms._120 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._085 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._049 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._111 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || «null» */,
				st._129 /* 1*TypeNameExpCS::ownedPathName=PathNameCS || «null» */,
				st._167 /* V00*steps-3..8 || «null» */,
				st._128 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || «null» */,
				st._190 /* V01*steps-5..8 || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._130 /* 1*TypeNameExpCS::ownedPatternGuard=ExpCS || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._199 /* V02*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._02,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._22) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(14, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _102 = new SerializationRule(94,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._142 /* check-rule basecs::TupleTypeCS.ownedParts : 92 */,
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._125 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._047 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._063 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._096 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._116 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._139 /* 1*steps-1..11 || «null» */,
				st._018 /* 1*'Tuple' || «? » «value» «? » */,
				st._163 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._191 /* V01*steps-5..9 || «null» */,
				st._126 /* 1*TupleTypeCS::ownedParts+=TuplePartCS || «null» */,
				st._201 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._126 /* 1*TupleTypeCS::ownedParts+=TuplePartCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._206 /* V03*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._17,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._06)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._38) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._06, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _103 = new SerializationRule(94,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._175 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 50 */,
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._176 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 95 */,
				ms._120 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._068 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._101 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._058 /* 1*CollectionTypeCS::name=CollectionTypeIdentifier || «? » «value» «? » */,
				st._166 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._059 /* 1*CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || «null» */,
				st._179 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._199 /* V02*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._19,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._16) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._40) /* TypeExpWithoutMultiplicityCS */
			},
			new /*@NonNull*/ EAttribute [] {
				EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(95, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _104 = new SerializationRule(97,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._211 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 98 */,
				ms._048 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._127 /* 1*TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._42) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(98, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _105 = new SerializationRule(98,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._086 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._042 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._144 /* 1*steps-1..3 || «null» */,
				st._114 /* 1*PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || «? » «value» «? » */,
				st._160 /* V00*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
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
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _106 = new SerializationRule(98,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._142 /* check-rule basecs::TupleTypeCS.ownedParts : 92 */,
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._125 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._047 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._063 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._096 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._116 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._139 /* 1*steps-1..11 || «null» */,
				st._018 /* 1*'Tuple' || «? » «value» «? » */,
				st._163 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._191 /* V01*steps-5..9 || «null» */,
				st._126 /* 1*TupleTypeCS::ownedParts+=TuplePartCS || «null» */,
				st._201 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._126 /* 1*TupleTypeCS::ownedParts+=TuplePartCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._206 /* V03*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._17,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._06)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._38) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._06, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _107 = new SerializationRule(98,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._175 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 50 */,
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._176 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 95 */,
				ms._120 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._068 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._101 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._058 /* 1*CollectionTypeCS::name=CollectionTypeIdentifier || «? » «value» «? » */,
				st._166 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._059 /* 1*CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || «null» */,
				st._179 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._199 /* V02*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._19,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._16) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._16) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._40) /* TypeExpWithoutMultiplicityCS */
			},
			new /*@NonNull*/ EAttribute [] {
				EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(95, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _108 = new SerializationRule(98,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._145 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 50 */,
				ms._196 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 94 */,
				ms._195 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 94 */,
				ms._112 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._072 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._024 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._023 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*steps-1..9 || «null» */,
				st._017 /* 1*'Map' || «? » «value» «? » */,
				st._167 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._082 /* 1*MapTypeCS::ownedKeyType=TypeExpCS || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._083 /* 1*MapTypeCS::ownedValueType=TypeExpCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._185 /* V01*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
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
					iv._39) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._39) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._05, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(94, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeNameExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
		private @NonNull SerializationRule _109 = new SerializationRule(99,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._214 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 29 */,
				ms._213 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 67 */,
				ms._212 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14 */,
				ms._085 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._049 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._111 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*steps-1..8 || «null» */,
				st._129 /* 1*TypeNameExpCS::ownedPathName=PathNameCS || «null» */,
				st._167 /* V00*steps-3..8 || «null» */,
				st._128 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || «null» */,
				st._190 /* V01*steps-5..8 || «null» */,
				st._043 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._130 /* 1*TypeNameExpCS::ownedPatternGuard=ExpCS || «null» */,
				st._046 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._01,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._22) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(29, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(14, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::URIFirstPathElementCS : referredElement=UnrestrictedName
		private @NonNull SerializationRule _110 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._038 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._107 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._61,
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
		// EssentialOCL::URIFirstPathElementCS : referredElement=URI
		private @NonNull SerializationRule _111 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._038 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._105 /* 1*PathElementCS::referredElement=URI || «? » «value» «? » */
			},
			sl._61,
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
		// EssentialOCL::URIPathNameCS : { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] }
		private @NonNull SerializationRule _112 = new SerializationRule(108,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._135 /* check-rule basecs::PathNameCS.ownedPathElements : 61|107 */,
				ms._057 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._146 /* 1*steps-1..5 || «null» */,
				st._111 /* 1*PathNameCS::ownedPathElements+=URIFirstPathElementCS || «null» */,
				st._164 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'::' || «! » «value» «! » */,
				st._110 /* 1*PathNameCS::ownedPathElements+=NextPathElementCS || «null» */
			},
			sl._03,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._50) /* NextPathElementCS|URIFirstPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(61, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS : '*'
		private @NonNull SerializationRule _113 = new SerializationRule(110,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
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
	private _IndexVectors iv;
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
		iv = new _IndexVectors();
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
		st.init();
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
//	import RTSerializationAssignedRuleCallsStep;
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
//	import VariableCardinalitySolution;
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
//	import CompleteOCLCSPackage;
//	import EssentialOCLCSPackage;
