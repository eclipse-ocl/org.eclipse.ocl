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
package org.eclipse.ocl.xtext.oclinecore.serializer;

import com.google.inject.Inject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.AbstractAnalysisProvider;
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
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.IntegerCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.VariableCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
import org.eclipse.ocl.xtext.base.cs2text.user.RTGrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.EAttributeData;
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData;
import org.eclipse.ocl.xtext.base.cs2text.xtext.EReferenceData;
import org.eclipse.ocl.xtext.base.cs2text.xtext.EStructuralFeatureData;
import org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage;

public class OCLinEcoreAnalysisProvider extends AbstractAnalysisProvider
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
				new EClassData [] {
					ec._00  /* basecs::AnnotationCS */,
					ec._01  /* basecs::AttributeCS */,
					ec._02  /* essentialoclcs::BooleanLiteralExpCS */,
					ec._03  /* essentialoclcs::CollectionLiteralExpCS */,
					ec._04  /* essentialoclcs::CollectionLiteralPartCS */,
					ec._05  /* essentialoclcs::CollectionPatternCS */,
					ec._06  /* essentialoclcs::CollectionTypeCS */,
					ec._07  /* essentialoclcs::ContextCS */,
					ec._08  /* essentialoclcs::CurlyBracketedClauseCS */,
					ec._09  /* basecs::DataTypeCS */,
					ec._10  /* basecs::DetailCS */,
					ec._11  /* basecs::DocumentationCS */,
					ec._12  /* basecs::EnumerationCS */,
					ec._13  /* basecs::EnumerationLiteralCS */,
					ec._14  /* essentialoclcs::ExpCS */,
					ec._15  /* essentialoclcs::ExpSpecificationCS */,
					ec._16  /* essentialoclcs::IfExpCS */,
					ec._17  /* essentialoclcs::IfThenExpCS */,
					ec._18  /* basecs::ImplicitOppositeCS */,
					ec._19  /* basecs::ImportCS */,
					ec._20  /* essentialoclcs::InfixExpCS */,
					ec._21  /* essentialoclcs::InvalidLiteralExpCS */,
					ec._22  /* essentialoclcs::LambdaLiteralExpCS */,
					ec._23  /* essentialoclcs::LetExpCS */,
					ec._24  /* essentialoclcs::LetVariableCS */,
					ec._25  /* essentialoclcs::MapLiteralExpCS */,
					ec._26  /* essentialoclcs::MapLiteralPartCS */,
					ec._27  /* essentialoclcs::MapTypeCS */,
					ec._28  /* basecs::ModelElementRefCS */,
					ec._29  /* basecs::MultiplicityBoundsCS */,
					ec._30  /* basecs::MultiplicityStringCS */,
					ec._31  /* essentialoclcs::NameExpCS */,
					ec._32  /* essentialoclcs::NavigatingArgCS */,
					ec._33  /* essentialoclcs::NestedExpCS */,
					ec._34  /* essentialoclcs::NullLiteralExpCS */,
					ec._35  /* essentialoclcs::NumberLiteralExpCS */,
					ec._36  /* oclinecorecs::OCLinEcoreConstraintCS */,
					ec._37  /* basecs::OperationCS */,
					ec._38  /* basecs::PackageCS */,
					ec._39  /* basecs::ParameterCS */,
					ec._40  /* basecs::PathElementCS */,
					ec._41  /* basecs::PathElementWithURICS */,
					ec._42  /* basecs::PathNameCS */,
					ec._43  /* essentialoclcs::PatternExpCS */,
					ec._44  /* essentialoclcs::PrefixExpCS */,
					ec._45  /* basecs::PrimitiveTypeRefCS */,
					ec._46  /* basecs::ReferenceCS */,
					ec._47  /* essentialoclcs::RoundBracketedClauseCS */,
					ec._48  /* essentialoclcs::SelfExpCS */,
					ec._49  /* essentialoclcs::ShadowPartCS */,
					ec._50  /* essentialoclcs::SquareBracketedClauseCS */,
					ec._51  /* essentialoclcs::StringLiteralExpCS */,
					ec._52  /* basecs::StructuredClassCS */,
					ec._53  /* oclinecorecs::SysMLCS */,
					ec._54  /* basecs::TemplateBindingCS */,
					ec._55  /* basecs::TemplateParameterSubstitutionCS */,
					ec._56  /* basecs::TemplateSignatureCS */,
					ec._57  /* oclinecorecs::TopLevelCS */,
					ec._58  /* essentialoclcs::TupleLiteralExpCS */,
					ec._59  /* essentialoclcs::TupleLiteralPartCS */,
					ec._60  /* basecs::TuplePartCS */,
					ec._61  /* basecs::TupleTypeCS */,
					ec._62  /* essentialoclcs::TypeLiteralExpCS */,
					ec._63  /* essentialoclcs::TypeNameExpCS */,
					ec._64  /* basecs::TypeParameterCS */,
					ec._65  /* basecs::TypedTypeRefCS */,
					ec._66  /* essentialoclcs::UnlimitedNaturalLiteralExpCS */,
					ec._67  /* essentialoclcs::VariableCS */,
					ec._68  /* basecs::WildcardTypeRefCS */
				},
				/**
				 *	The indexable per-grammar rule meta data.
				 */
				new AbstractRuleValue [] {
					rv._000  /* 0 : ANY_OTHER */,
					rv._001  /* 1 : AnnotationCS */,
					rv._002  /* 2 : AnnotationElementCS */,
					rv._003  /* 3 : AttributeCS */,
					rv._004  /* 4 : BinaryOperatorName */,
					rv._005  /* 5 : BooleanLiteralExpCS */,
					rv._006  /* 6 : ClassCS */,
					rv._007  /* 7 : CoIteratorVariableCS */,
					rv._008  /* 8 : CollectionLiteralExpCS */,
					rv._009  /* 9 : CollectionLiteralPartCS */,
					rv._010  /* 10 : CollectionPatternCS */,
					rv._011  /* 11 : CollectionTypeCS */,
					rv._012  /* 12 : CollectionTypeIdentifier */,
					rv._013  /* 13 : CurlyBracketedClauseCS */,
					rv._014  /* 14 : DOUBLE_QUOTED_STRING */,
					rv._015  /* 15 : DataTypeCS */,
					rv._016  /* 16 : DetailCS */,
					rv._017  /* 17 : DocumentationCS */,
					rv._018  /* 18 : ESCAPED_CHARACTER */,
					rv._019  /* 19 : ESCAPED_ID */,
					rv._020  /* 20 : ElseIfThenExpCS */,
					rv._021  /* 21 : EnumerationCS */,
					rv._022  /* 22 : EnumerationLiteralCS */,
					rv._023  /* 23 : EnumerationLiteralName */,
					rv._024  /* 24 : EssentialOCLInfixOperatorName */,
					rv._025  /* 25 : EssentialOCLNavigationOperatorName */,
					rv._026  /* 26 : EssentialOCLReservedKeyword */,
					rv._027  /* 27 : EssentialOCLUnaryOperatorName */,
					rv._028  /* 28 : EssentialOCLUnreservedName */,
					rv._029  /* 29 : EssentialOCLUnrestrictedName */,
					rv._030  /* 30 : ExpCS */,
					rv._031  /* 31 : FirstPathElementCS */,
					rv._032  /* 32 : ID */,
					rv._033  /* 33 : INT */,
					rv._034  /* 34 : INTEGER */,
					rv._035  /* 35 : Identifier */,
					rv._036  /* 36 : IfExpCS */,
					rv._037  /* 37 : ImplicitOppositeCS */,
					rv._038  /* 38 : ImportCS */,
					rv._039  /* 39 : InfixOperatorName */,
					rv._040  /* 40 : InvalidLiteralExpCS */,
					rv._041  /* 41 : InvariantConstraintCS */,
					rv._042  /* 42 : LETTER_CHARACTER */,
					rv._043  /* 43 : LOWER */,
					rv._044  /* 44 : LambdaLiteralExpCS */,
					rv._045  /* 45 : LetExpCS */,
					rv._046  /* 46 : LetVariableCS */,
					rv._047  /* 47 : ML_COMMENT */,
					rv._048  /* 48 : ML_SINGLE_QUOTED_STRING */,
					rv._049  /* 49 : MapLiteralExpCS */,
					rv._050  /* 50 : MapLiteralPartCS */,
					rv._051  /* 51 : MapTypeCS */,
					rv._052  /* 52 : Model */,
					rv._053  /* 53 : ModelElementCS */,
					rv._054  /* 54 : ModelElementRefCS */,
					rv._055  /* 55 : MultiplicityBoundsCS */,
					rv._056  /* 56 : MultiplicityCS */,
					rv._057  /* 57 : MultiplicityStringCS */,
					rv._058  /* 58 : NUMBER_LITERAL */,
					rv._059  /* 59 : NameExpCS */,
					rv._060  /* 60 : NavigatingArgCS */,
					rv._061  /* 61 : NavigatingArgExpCS */,
					rv._062  /* 62 : NavigatingBarArgCS */,
					rv._063  /* 63 : NavigatingCommaArgCS */,
					rv._064  /* 64 : NavigatingSemiArgCS */,
					rv._065  /* 65 : NavigationOperatorName */,
					rv._066  /* 66 : NestedExpCS */,
					rv._067  /* 67 : NextPathElementCS */,
					rv._068  /* 68 : NullLiteralExpCS */,
					rv._069  /* 69 : NumberLiteralExpCS */,
					rv._070  /* 70 : OperationCS */,
					rv._071  /* 71 : PackageCS */,
					rv._072  /* 72 : ParameterCS */,
					rv._073  /* 73 : PathNameCS */,
					rv._074  /* 74 : PatternExpCS */,
					rv._075  /* 75 : PostconditionConstraintCS */,
					rv._076  /* 76 : PreconditionConstraintCS */,
					rv._077  /* 77 : PrefixedLetExpCS */,
					rv._078  /* 78 : PrefixedPrimaryExpCS */,
					rv._079  /* 79 : PrimaryExpCS */,
					rv._080  /* 80 : PrimitiveLiteralExpCS */,
					rv._081  /* 81 : PrimitiveTypeCS */,
					rv._082  /* 82 : PrimitiveTypeIdentifier */,
					rv._083  /* 83 : ReferenceCS */,
					rv._084  /* 84 : RoundBracketedClauseCS */,
					rv._085  /* 85 : SIGNED */,
					rv._086  /* 86 : SIMPLE_ID */,
					rv._087  /* 87 : SINGLE_QUOTED_STRING */,
					rv._088  /* 88 : SL_COMMENT */,
					rv._089  /* 89 : SelfExpCS */,
					rv._090  /* 90 : ShadowPartCS */,
					rv._091  /* 91 : SimplePathNameCS */,
					rv._092  /* 92 : SpecificationCS */,
					rv._093  /* 93 : SquareBracketedClauseCS */,
					rv._094  /* 94 : StringLiteral */,
					rv._095  /* 95 : StringLiteralExpCS */,
					rv._096  /* 96 : StructuralFeatureCS */,
					rv._097  /* 97 : StructuredClassCS */,
					rv._098  /* 98 : SysMLCS */,
					rv._099  /* 99 : TemplateBindingCS */,
					rv._100  /* 100 : TemplateParameterSubstitutionCS */,
					rv._101  /* 101 : TemplateSignatureCS */,
					rv._102  /* 102 : TopLevelCS */,
					rv._103  /* 103 : TupleLiteralExpCS */,
					rv._104  /* 104 : TupleLiteralPartCS */,
					rv._105  /* 105 : TuplePartCS */,
					rv._106  /* 106 : TupleTypeCS */,
					rv._107  /* 107 : TypeExpCS */,
					rv._108  /* 108 : TypeExpWithoutMultiplicityCS */,
					rv._109  /* 109 : TypeIdentifier */,
					rv._110  /* 110 : TypeLiteralCS */,
					rv._111  /* 111 : TypeLiteralExpCS */,
					rv._112  /* 112 : TypeLiteralWithMultiplicityCS */,
					rv._113  /* 113 : TypeNameExpCS */,
					rv._114  /* 114 : TypeParameterCS */,
					rv._115  /* 115 : TypeRefCS */,
					rv._116  /* 116 : TypedMultiplicityRefCS */,
					rv._117  /* 117 : TypedRefCS */,
					rv._118  /* 118 : TypedTypeRefCS */,
					rv._119  /* 119 : UNQUOTED_STRING */,
					rv._120  /* 120 : UPPER */,
					rv._121  /* 121 : URI */,
					rv._122  /* 122 : URIFirstPathElementCS */,
					rv._123  /* 123 : URIPathNameCS */,
					rv._124  /* 124 : UnaryOperatorName */,
					rv._125  /* 125 : UnlimitedNaturalLiteralExpCS */,
					rv._126  /* 126 : UnreservedName */,
					rv._127  /* 127 : UnrestrictedName */,
					rv._128  /* 128 : WS */,
					rv._129  /* 129 : WildcardTypeRefCS */
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
		private final @NonNull IndexVector _0 // AnnotationElementCS
			= new IndexVector(0x4L);
		private final @NonNull IndexVector _1 // ClassCS
			= new IndexVector(0x40L);
		private final @NonNull IndexVector _2 // CoIteratorVariableCS
			= new IndexVector(0x80L);
		private final @NonNull IndexVector _3 // CollectionLiteralPartCS
			= new IndexVector(0x200L);
		private final @NonNull IndexVector _4 // CollectionTypeCS
			= new IndexVector(0x800L);
		private final @NonNull IndexVector _5 // CurlyBracketedClauseCS
			= new IndexVector(0x2000L);
		private final @NonNull IndexVector _6 // DetailCS
			= new IndexVector(0x10000L);
		private final @NonNull IndexVector _7 // ElseIfThenExpCS
			= new IndexVector(0x100000L);
		private final @NonNull IndexVector _8 // EnumerationLiteralCS
			= new IndexVector(0x400000L);
		private final @NonNull IndexVector _9 // ExpCS
			= new IndexVector(0x40000000L);
		private final @NonNull IndexVector _10 // FirstPathElementCS
			= new IndexVector(0x80000000L);
		private final @NonNull IndexVector _11 // ImplicitOppositeCS
			= new IndexVector(0x2000000000L);
		private final @NonNull IndexVector _12 // ImportCS
			= new IndexVector(0x4000000000L);
		private final @NonNull IndexVector _13 // InvariantConstraintCS
			= new IndexVector(0x20000000000L);
		private final @NonNull IndexVector _14 // LetVariableCS
			= new IndexVector(0x400000000000L);
		private final @NonNull IndexVector _15 // MapLiteralPartCS
			= new IndexVector(0x4000000000000L);
		private final @NonNull IndexVector _16 // MapTypeCS
			= new IndexVector(0x8000000000000L);
		private final @NonNull IndexVector _17 // ModelElementCS
			= new IndexVector(0x20000000000000L);
		private final @NonNull IndexVector _18 // ModelElementRefCS
			= new IndexVector(0x40000000000000L);
		private final @NonNull IndexVector _19 // MultiplicityCS
			= new IndexVector(0x100000000000000L);
		private final @NonNull IndexVector _20 // NavigatingArgExpCS
			= new IndexVector(0x2000000000000000L);
		private final @NonNull IndexVector _21 // NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
			= new IndexVector(0xd000000000000000L,0x1L);
		private final @NonNull IndexVector _22 // FirstPathElementCS|NextPathElementCS
			= new IndexVector(0x80000000L,0x8L);
		private final @NonNull IndexVector _23 // OperationCS
			= new IndexVector(0x0L,0x40L);
		private final @NonNull IndexVector _24 // PackageCS
			= new IndexVector(0x0L,0x80L);
		private final @NonNull IndexVector _25 // ParameterCS
			= new IndexVector(0x0L,0x100L);
		private final @NonNull IndexVector _26 // PathNameCS
			= new IndexVector(0x0L,0x200L);
		private final @NonNull IndexVector _27 // PatternExpCS
			= new IndexVector(0x0L,0x400L);
		private final @NonNull IndexVector _28 // ExpCS|PatternExpCS
			= new IndexVector(0x40000000L,0x400L);
		private final @NonNull IndexVector _29 // PostconditionConstraintCS
			= new IndexVector(0x0L,0x800L);
		private final @NonNull IndexVector _30 // PreconditionConstraintCS
			= new IndexVector(0x0L,0x1000L);
		private final @NonNull IndexVector _31 // PrefixedLetExpCS
			= new IndexVector(0x0L,0x2000L);
		private final @NonNull IndexVector _32 // LetExpCS|PrefixedLetExpCS
			= new IndexVector(0x200000000000L,0x2000L);
		private final @NonNull IndexVector _33 // PrefixedPrimaryExpCS
			= new IndexVector(0x0L,0x4000L);
		private final @NonNull IndexVector _34 // RoundBracketedClauseCS
			= new IndexVector(0x0L,0x100000L);
		private final @NonNull IndexVector _35 // ShadowPartCS
			= new IndexVector(0x0L,0x4000000L);
		private final @NonNull IndexVector _36 // SpecificationCS
			= new IndexVector(0x0L,0x10000000L);
		private final @NonNull IndexVector _37 // SquareBracketedClauseCS
			= new IndexVector(0x0L,0x20000000L);
		private final @NonNull IndexVector _38 // StringLiteralExpCS
			= new IndexVector(0x0L,0x80000000L);
		private final @NonNull IndexVector _39 // StructuralFeatureCS
			= new IndexVector(0x0L,0x100000000L);
		private final @NonNull IndexVector _40 // AttributeCS|ReferenceCS|StructuralFeatureCS
			= new IndexVector(0x8L,0x100080000L);
		private final @NonNull IndexVector _41 // ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS
			= new IndexVector(0x208040L,0x200000000L);
		private final @NonNull IndexVector _42 // AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS
			= new IndexVector(0x20000000608048L,0x3000800c0L);
		private final @NonNull IndexVector _43 // AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS
			= new IndexVector(0x20006L,0x400000000L);
		private final @NonNull IndexVector _44 // TemplateBindingCS
			= new IndexVector(0x0L,0x800000000L);
		private final @NonNull IndexVector _45 // TemplateParameterSubstitutionCS
			= new IndexVector(0x0L,0x1000000000L);
		private final @NonNull IndexVector _46 // TemplateSignatureCS
			= new IndexVector(0x0L,0x2000000000L);
		private final @NonNull IndexVector _47 // TupleLiteralPartCS
			= new IndexVector(0x0L,0x10000000000L);
		private final @NonNull IndexVector _48 // TuplePartCS
			= new IndexVector(0x0L,0x20000000000L);
		private final @NonNull IndexVector _49 // TypeExpCS
			= new IndexVector(0x0L,0x80000000000L);
		private final @NonNull IndexVector _50 // TypeExpWithoutMultiplicityCS
			= new IndexVector(0x0L,0x100000000000L);
		private final @NonNull IndexVector _51 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
			= new IndexVector(0x8000000000800L,0x440000020000L);
		private final @NonNull IndexVector _52 // TypeLiteralWithMultiplicityCS
			= new IndexVector(0x0L,0x1000000000000L);
		private final @NonNull IndexVector _53 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS
			= new IndexVector(0x8000000000800L,0x1440000020000L);
		private final @NonNull IndexVector _54 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new IndexVector(0x8000000000c00L,0x2540000020000L);
		private final @NonNull IndexVector _55 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new IndexVector(0x8000000000c00L,0x25c0000020000L);
		private final @NonNull IndexVector _56 // TypeParameterCS
			= new IndexVector(0x0L,0x4000000000000L);
		private final @NonNull IndexVector _57 // TypeRefCS
			= new IndexVector(0x0L,0x8000000000000L);
		private final @NonNull IndexVector _58 // TypedMultiplicityRefCS
			= new IndexVector(0x0L,0x10000000000000L);
		private final @NonNull IndexVector _59 // TypedRefCS
			= new IndexVector(0x0L,0x20000000000000L);
		private final @NonNull IndexVector _60 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS
			= new IndexVector(0x8000000000800L,0x60440000020000L);
		private final @NonNull IndexVector _61 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS
			= new IndexVector(0x8000000000800L,0x70440000020000L);
		private final @NonNull IndexVector _62 // NextPathElementCS|URIFirstPathElementCS
			= new IndexVector(0x0L,0x400000000000008L);
		private final @NonNull IndexVector _63 // FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
			= new IndexVector(0x80000000L,0x400000000000008L);
		private final @NonNull IndexVector _64 // URIPathNameCS
			= new IndexVector(0x0L,0x800000000000000L);
		private final @NonNull IndexVector _65 // BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x10000000020L,0x2000000080010030L);
		private final @NonNull IndexVector _66 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x802111000000120L,0x2000808082018034L);
		private final @NonNull IndexVector _67 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x802111000000120L,0x200080808201c034L);
		private final @NonNull IndexVector _68 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x802311000000120L,0x200080808201e034L);
		private final @NonNull IndexVector _69 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x802311040000120L,0x200080808201e034L);
		private final @NonNull IndexVector _70 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x2802311040000120L,0x200080808201e034L);
		private final @NonNull IndexVector _71 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x802311040000120L,0x200080808201e434L);
		private final @NonNull IndexVector _72 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
			= new IndexVector(0x8000000000800L,0x68440000020000L,0x2L);
	}

	/**
	 * String combinations used by assigned String EAttributes
	 */
	private class _EnumValues
	{
		private final @NonNull EnumerationValue _00 // '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'
			= new MultipleEnumerationValue(new @NonNull String[]{"!composes", "!derived", "!ordered", "!readonly", "!resolve", "!transient", "!unique", "!unsettable", "!volatile", "composes", "derived", "ordered", "readonly", "resolve", "transient", "unique", "unsettable", "volatile"});
		private final @NonNull EnumerationValue _01 // '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'
			= new MultipleEnumerationValue(new @NonNull String[]{"!derived", "!id", "!ordered", "!readonly", "!transient", "!unique", "!unsettable", "!volatile", "derived", "id", "ordered", "readonly", "transient", "unique", "unsettable", "volatile"});
		private final @NonNull EnumerationValue _02 // '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'
			= new MultipleEnumerationValue(new @NonNull String[]{"!derived", "!ordered", "!transient", "!unique", "derived", "ordered", "transient", "unique"});
		private final @NonNull EnumerationValue _03 // '!ordered|!unique|ordered|unique'
			= new MultipleEnumerationValue(new @NonNull String[]{"!ordered", "!unique", "ordered", "unique"});
		private final @NonNull EnumerationValue _04 // '*|+|?'
			= new MultipleEnumerationValue(new @NonNull String[]{"*", "+", "?"});
		private final @NonNull EnumerationValue _05 // ','
			= new SingleEnumerationValue(",");
		private final @NonNull EnumerationValue _06 // '::*'
			= new SingleEnumerationValue("::*");
		private final @NonNull EnumerationValue _07 // ';'
			= new SingleEnumerationValue(";");
		private final @NonNull EnumerationValue _08 // '@'
			= new SingleEnumerationValue("@");
		private final @NonNull EnumerationValue _09 // 'Map'
			= new SingleEnumerationValue("Map");
		private final @NonNull EnumerationValue _10 // 'Tuple'
			= new SingleEnumerationValue("Tuple");
		private final @NonNull EnumerationValue _11 // 'abstract'
			= new SingleEnumerationValue("abstract");
		private final @NonNull EnumerationValue _12 // 'callable'
			= new SingleEnumerationValue("callable");
		private final @NonNull EnumerationValue _13 // 'definition'
			= new SingleEnumerationValue("definition");
		private final @NonNull EnumerationValue _14 // 'false|true'
			= new MultipleEnumerationValue(new @NonNull String[]{"false", "true"});
		private final @NonNull EnumerationValue _15 // 'interface'
			= new SingleEnumerationValue("interface");
		private final @NonNull EnumerationValue _16 // 'invariant'
			= new SingleEnumerationValue("invariant");
		private final @NonNull EnumerationValue _17 // 'postcondition'
			= new SingleEnumerationValue("postcondition");
		private final @NonNull EnumerationValue _18 // 'precondition'
			= new SingleEnumerationValue("precondition");
		private final @NonNull EnumerationValue _19 // 'primitive'
			= new SingleEnumerationValue("primitive");
		private final @NonNull EnumerationValue _20 // 'serializable'
			= new SingleEnumerationValue("serializable");
		private final @NonNull EnumerationValue _21 // 'static'
			= new SingleEnumerationValue("static");
		private final @NonNull EnumerationValue _22 // '|'
			= new SingleEnumerationValue("|");
		private final @NonNull EnumerationValue _23 // '|1'
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
		private final @NonNull CardinalitySolution _003 // |default|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
		private final @NonNull CardinalitySolution _004 // |exprString|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		private final @NonNull CardinalitySolution _005 // |instanceClassName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME);
		private final @NonNull CardinalitySolution _006 // |isAbstract.'abstract'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, ev._11);
		private final @NonNull CardinalitySolution _007 // |isAll.'::*'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, ev._06);
		private final @NonNull CardinalitySolution _008 // |isCallable.'callable'|
			= new EAttributeSizeCardinalitySolution(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE, ev._12);
		private final @NonNull CardinalitySolution _009 // |isInterface.'interface'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE, ev._15);
		private final @NonNull CardinalitySolution _010 // |isNullFree.'|1'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, ev._23);
		private final @NonNull CardinalitySolution _011 // |isPre.'@'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, ev._08);
		private final @NonNull CardinalitySolution _012 // |isPrimitive.'primitive'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, ev._19);
		private final @NonNull CardinalitySolution _013 // |isSerializable.'serializable'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE, ev._20);
		private final @NonNull CardinalitySolution _014 // |isSerializable.'serializable'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE, ev._20);
		private final @NonNull CardinalitySolution _015 // |literal|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL);
		private final @NonNull CardinalitySolution _016 // |lowerBound|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull CardinalitySolution _017 // |name.'Map'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, ev._09);
		private final @NonNull CardinalitySolution _018 // |name.'Tuple'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, ev._10);
		private final @NonNull CardinalitySolution _019 // |name|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		private final @NonNull CardinalitySolution _020 // |name|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull CardinalitySolution _021 // |name|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		private final @NonNull CardinalitySolution _022 // |nsPrefix|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
		private final @NonNull CardinalitySolution _023 // |nsURI|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
		private final @NonNull CardinalitySolution _024 // |ownedActualParameter|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull CardinalitySolution _025 // |ownedAnnotations|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull CardinalitySolution _026 // |ownedArguments|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		private final @NonNull CardinalitySolution _027 // |ownedBinding|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull CardinalitySolution _028 // |ownedBodyExpressions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		private final @NonNull CardinalitySolution _029 // |ownedClasses|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
		private final @NonNull CardinalitySolution _030 // |ownedCoIterator|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		private final @NonNull CardinalitySolution _031 // |ownedCollectionMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		private final @NonNull CardinalitySolution _032 // |ownedCondition|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		private final @NonNull CardinalitySolution _033 // |ownedCondition|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		private final @NonNull CardinalitySolution _034 // |ownedConstraints|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		private final @NonNull CardinalitySolution _035 // |ownedContents|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS);
		private final @NonNull CardinalitySolution _036 // |ownedCurlyBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _037 // |ownedCurlyBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _038 // |ownedDefaultExpressions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull CardinalitySolution _039 // |ownedDetails|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
		private final @NonNull CardinalitySolution _040 // |ownedElseExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		private final @NonNull CardinalitySolution _041 // |ownedExceptions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
		private final @NonNull CardinalitySolution _042 // |ownedExpressionCS|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		private final @NonNull CardinalitySolution _043 // |ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _044 // |ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _045 // |ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _046 // |ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _047 // |ownedExtends|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull CardinalitySolution _048 // |ownedExtends|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull CardinalitySolution _049 // |ownedIfThenExpressions|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		private final @NonNull CardinalitySolution _050 // |ownedImplicitOpposites|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES);
		private final @NonNull CardinalitySolution _051 // |ownedImports|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		private final @NonNull CardinalitySolution _052 // |ownedInExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		private final @NonNull CardinalitySolution _053 // |ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _054 // |ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _055 // |ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _056 // |ownedKeyType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		private final @NonNull CardinalitySolution _057 // |ownedKey|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		private final @NonNull CardinalitySolution _058 // |ownedLastExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		private final @NonNull CardinalitySolution _059 // |ownedLeft|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		private final @NonNull CardinalitySolution _060 // |ownedLiterals|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
		private final @NonNull CardinalitySolution _061 // |ownedMessageSpecification|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		private final @NonNull CardinalitySolution _062 // |ownedMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull CardinalitySolution _063 // |ownedMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull CardinalitySolution _064 // |ownedNameExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		private final @NonNull CardinalitySolution _065 // |ownedOperations|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
		private final @NonNull CardinalitySolution _066 // |ownedPackages|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		private final @NonNull CardinalitySolution _067 // |ownedParameters|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull CardinalitySolution _068 // |ownedParameters|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
		private final @NonNull CardinalitySolution _069 // |ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _070 // |ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _071 // |ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _072 // |ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _073 // |ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _074 // |ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _075 // |ownedPathElements|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull CardinalitySolution _076 // |ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _077 // |ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _078 // |ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _079 // |ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _080 // |ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _081 // |ownedPatternGuard|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		private final @NonNull CardinalitySolution _082 // |ownedPatternType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		private final @NonNull CardinalitySolution _083 // |ownedPostconditions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
		private final @NonNull CardinalitySolution _084 // |ownedPreconditions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		private final @NonNull CardinalitySolution _085 // |ownedProperties|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
		private final @NonNull CardinalitySolution _086 // |ownedReferences|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES);
		private final @NonNull CardinalitySolution _087 // |ownedRight|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull CardinalitySolution _088 // |ownedRoundBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _089 // |ownedRoundBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _090 // |ownedSignature|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		private final @NonNull CardinalitySolution _091 // |ownedSpecification|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		private final @NonNull CardinalitySolution _092 // |ownedSquareBracketedClauses|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		private final @NonNull CardinalitySolution _093 // |ownedSubstitutions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull CardinalitySolution _094 // |ownedSuperTypes|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
		private final @NonNull CardinalitySolution _095 // |ownedTerms|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		private final @NonNull CardinalitySolution _096 // |ownedThenExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull CardinalitySolution _097 // |ownedThenExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull CardinalitySolution _098 // |ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _099 // |ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _100 // |ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _101 // |ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _102 // |ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _103 // |ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _104 // |ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _105 // |ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _106 // |ownedValueType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		private final @NonNull CardinalitySolution _107 // |ownedValue|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		private final @NonNull CardinalitySolution _108 // |ownedVariables|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		private final @NonNull CardinalitySolution _109 // |patternVariableName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		private final @NonNull CardinalitySolution _110 // |prefix.','|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._05);
		private final @NonNull CardinalitySolution _111 // |prefix.';'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._07);
		private final @NonNull CardinalitySolution _112 // |prefix.'|'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._22);
		private final @NonNull CardinalitySolution _113 // |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._00);
		private final @NonNull CardinalitySolution _114 // |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._01);
		private final @NonNull CardinalitySolution _115 // |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._02);
		private final @NonNull CardinalitySolution _116 // |qualifiers.'!ordered|!unique|ordered|unique'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._03);
		private final @NonNull CardinalitySolution _117 // |qualifiers.'definition'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._13);
		private final @NonNull CardinalitySolution _118 // |qualifiers.'static'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._21);
		private final @NonNull CardinalitySolution _119 // |referredElement|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private final @NonNull CardinalitySolution _120 // |referredKeys|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS);
		private final @NonNull CardinalitySolution _121 // |referredOpposite|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE);
		private final @NonNull CardinalitySolution _122 // |referredProperty|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		private final @NonNull CardinalitySolution _123 // |restVariableName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		private final @NonNull CardinalitySolution _124 // |segments|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		private final @NonNull CardinalitySolution _125 // |stereotype.'invariant'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._16);
		private final @NonNull CardinalitySolution _126 // |stereotype.'postcondition'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._17);
		private final @NonNull CardinalitySolution _127 // |stereotype.'precondition'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._18);
		private final @NonNull CardinalitySolution _128 // |stringBounds.'*|+|?'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._04);
		private final @NonNull CardinalitySolution _129 // |symbol.'false|true'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._14);
		private final @NonNull CardinalitySolution _130 // |symbol|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull CardinalitySolution _131 // |upperBound|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull CardinalitySolution _132 // |values|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.DETAIL_CS__VALUES);
		private final @NonNull CardinalitySolution _133 // |value|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
		private final @NonNull CardinalitySolution _134 // |value|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE);
		private final @NonNull CardinalitySolution _135 // (|exprString| - 1)
			= new SubtractCardinalitySolution(_004, _001);
		private final @NonNull CardinalitySolution _136 // (|isInterface.'interface'| > 0)
			= new GreaterThanCardinalitySolution(_009, _000);
		private final @NonNull CardinalitySolution _137 // (|isSerializable.'serializable'| > 0)
			= new GreaterThanCardinalitySolution(_014, _000);
		private final @NonNull CardinalitySolution _138 // (|isSerializable.'serializable'| > 0)
			= new GreaterThanCardinalitySolution(_013, _000);
		private final @NonNull CardinalitySolution _139 // (|lowerBound| - 1)
			= new SubtractCardinalitySolution(_016, _001);
		private final @NonNull CardinalitySolution _140 // (|name.'Map'| - 1)
			= new SubtractCardinalitySolution(_017, _001);
		private final @NonNull CardinalitySolution _141 // (|name.'Tuple'| - 1)
			= new SubtractCardinalitySolution(_018, _001);
		private final @NonNull CardinalitySolution _142 // (|name| - 1)
			= new SubtractCardinalitySolution(_019, _001);
		private final @NonNull CardinalitySolution _143 // (|name| - 1)
			= new SubtractCardinalitySolution(_021, _001);
		private final @NonNull CardinalitySolution _144 // (|name| - 1)
			= new SubtractCardinalitySolution(_020, _001);
		private final @NonNull CardinalitySolution _145 // (|ownedActualParameter| - 1)
			= new SubtractCardinalitySolution(_024, _001);
		private final @NonNull CardinalitySolution _146 // (|ownedAnnotations| > 0)
			= new GreaterThanCardinalitySolution(_025, _000);
		private final @NonNull CardinalitySolution _147 // (|ownedArguments| - 1)
			= new SubtractCardinalitySolution(_026, _001);
		private final @NonNull CardinalitySolution _148 // (|ownedArguments| > 0)
			= new GreaterThanCardinalitySolution(_026, _000);
		private final @NonNull CardinalitySolution _149 // (|ownedBinding| - 1)
			= new SubtractCardinalitySolution(_027, _001);
		private final @NonNull CardinalitySolution _150 // (|ownedBodyExpressions| > 0)
			= new GreaterThanCardinalitySolution(_028, _000);
		private final @NonNull CardinalitySolution _151 // (|ownedCoIterator| - 1)
			= new SubtractCardinalitySolution(_030, _001);
		private final @NonNull CardinalitySolution _152 // (|ownedCondition| - 1)
			= new SubtractCardinalitySolution(_032, _001);
		private final @NonNull CardinalitySolution _153 // (|ownedCondition| - 1)
			= new SubtractCardinalitySolution(_033, _001);
		private final @NonNull CardinalitySolution _154 // (|ownedDefaultExpressions| > 0)
			= new GreaterThanCardinalitySolution(_038, _000);
		private final @NonNull CardinalitySolution _155 // (|ownedDetails| - 1)
			= new SubtractCardinalitySolution(_039, _001);
		private final @NonNull CardinalitySolution _156 // (|ownedDetails| > 0)
			= new GreaterThanCardinalitySolution(_039, _000);
		private final @NonNull CardinalitySolution _157 // (|ownedElseExpression| - 1)
			= new SubtractCardinalitySolution(_040, _001);
		private final @NonNull CardinalitySolution _158 // (|ownedExceptions| - 1)
			= new SubtractCardinalitySolution(_041, _001);
		private final @NonNull CardinalitySolution _159 // (|ownedExceptions| > 0)
			= new GreaterThanCardinalitySolution(_041, _000);
		private final @NonNull CardinalitySolution _160 // (|ownedExpressionCS| - 1)
			= new SubtractCardinalitySolution(_042, _001);
		private final @NonNull CardinalitySolution _161 // (|ownedExpression| - 1)
			= new SubtractCardinalitySolution(_043, _001);
		private final @NonNull CardinalitySolution _162 // (|ownedExpression| - 1)
			= new SubtractCardinalitySolution(_045, _001);
		private final @NonNull CardinalitySolution _163 // (|ownedExpression| - 1)
			= new SubtractCardinalitySolution(_044, _001);
		private final @NonNull CardinalitySolution _164 // (|ownedExpression| - 1)
			= new SubtractCardinalitySolution(_046, _001);
		private final @NonNull CardinalitySolution _165 // (|ownedExtends| - 1)
			= new SubtractCardinalitySolution(_047, _001);
		private final @NonNull CardinalitySolution _166 // (|ownedExtends| > 0)
			= new GreaterThanCardinalitySolution(_047, _000);
		private final @NonNull CardinalitySolution _167 // (|ownedInExpression| - 1)
			= new SubtractCardinalitySolution(_052, _001);
		private final @NonNull CardinalitySolution _168 // (|ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_054, _001);
		private final @NonNull CardinalitySolution _169 // (|ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_053, _001);
		private final @NonNull CardinalitySolution _170 // (|ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_055, _001);
		private final @NonNull CardinalitySolution _171 // (|ownedKeyType| - V0)
			= new SubtractCardinalitySolution(_056, _002);
		private final @NonNull CardinalitySolution _172 // (|ownedKey| - 1)
			= new SubtractCardinalitySolution(_057, _001);
		private final @NonNull CardinalitySolution _173 // (|ownedLeft| - 1)
			= new SubtractCardinalitySolution(_059, _001);
		private final @NonNull CardinalitySolution _174 // (|ownedNameExpression| - 1)
			= new SubtractCardinalitySolution(_064, _001);
		private final @NonNull CardinalitySolution _175 // (|ownedParameters| - 1)
			= new SubtractCardinalitySolution(_067, _001);
		private final @NonNull CardinalitySolution _176 // (|ownedParameters| - 1)
			= new SubtractCardinalitySolution(_068, _001);
		private final @NonNull CardinalitySolution _177 // (|ownedParameters| > 0)
			= new GreaterThanCardinalitySolution(_068, _000);
		private final @NonNull CardinalitySolution _178 // (|ownedParts| - 1)
			= new SubtractCardinalitySolution(_069, _001);
		private final @NonNull CardinalitySolution _179 // (|ownedParts| - 1)
			= new SubtractCardinalitySolution(_074, _001);
		private final @NonNull CardinalitySolution _180 // (|ownedParts| - 1)
			= new SubtractCardinalitySolution(_070, _001);
		private final @NonNull CardinalitySolution _181 // (|ownedParts| - 1)
			= new SubtractCardinalitySolution(_073, _001);
		private final @NonNull CardinalitySolution _182 // (|ownedParts| - 1)
			= new SubtractCardinalitySolution(_072, _001);
		private final @NonNull CardinalitySolution _183 // (|ownedParts| - 1)
			= new SubtractCardinalitySolution(_071, _001);
		private final @NonNull CardinalitySolution _184 // (|ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_072, _000);
		private final @NonNull CardinalitySolution _185 // (|ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_070, _000);
		private final @NonNull CardinalitySolution _186 // (|ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_073, _000);
		private final @NonNull CardinalitySolution _187 // (|ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_074, _000);
		private final @NonNull CardinalitySolution _188 // (|ownedPathElements| - 1)
			= new SubtractCardinalitySolution(_075, _001);
		private final @NonNull CardinalitySolution _189 // (|ownedPathName| - 1)
			= new SubtractCardinalitySolution(_076, _001);
		private final @NonNull CardinalitySolution _190 // (|ownedPathName| - 1)
			= new SubtractCardinalitySolution(_079, _001);
		private final @NonNull CardinalitySolution _191 // (|ownedPathName| - 1)
			= new SubtractCardinalitySolution(_077, _001);
		private final @NonNull CardinalitySolution _192 // (|ownedPathName| - 1)
			= new SubtractCardinalitySolution(_080, _001);
		private final @NonNull CardinalitySolution _193 // (|ownedPathName| - 1)
			= new SubtractCardinalitySolution(_078, _001);
		private final @NonNull CardinalitySolution _194 // (|ownedPatternType| - 1)
			= new SubtractCardinalitySolution(_082, _001);
		private final @NonNull CardinalitySolution _195 // (|ownedRight| - 1)
			= new SubtractCardinalitySolution(_087, _001);
		private final @NonNull CardinalitySolution _196 // (|ownedSubstitutions| - 1)
			= new SubtractCardinalitySolution(_093, _001);
		private final @NonNull CardinalitySolution _197 // (|ownedSuperTypes| - 1)
			= new SubtractCardinalitySolution(_094, _001);
		private final @NonNull CardinalitySolution _198 // (|ownedSuperTypes| > 0)
			= new GreaterThanCardinalitySolution(_094, _000);
		private final @NonNull CardinalitySolution _199 // (|ownedTerms| - 1)
			= new SubtractCardinalitySolution(_095, _001);
		private final @NonNull CardinalitySolution _200 // (|ownedThenExpression| - 1)
			= new SubtractCardinalitySolution(_097, _001);
		private final @NonNull CardinalitySolution _201 // (|ownedThenExpression| - 1)
			= new SubtractCardinalitySolution(_096, _001);
		private final @NonNull CardinalitySolution _202 // (|ownedType| - 1)
			= new SubtractCardinalitySolution(_101, _001);
		private final @NonNull CardinalitySolution _203 // (|ownedType| - 1)
			= new SubtractCardinalitySolution(_104, _001);
		private final @NonNull CardinalitySolution _204 // (|ownedType| - 1)
			= new SubtractCardinalitySolution(_099, _001);
		private final @NonNull CardinalitySolution _205 // (|ownedType| - 1)
			= new SubtractCardinalitySolution(_103, _001);
		private final @NonNull CardinalitySolution _206 // (|ownedType| - 1)
			= new SubtractCardinalitySolution(_102, _001);
		private final @NonNull CardinalitySolution _207 // (|ownedType| - 1)
			= new SubtractCardinalitySolution(_105, _001);
		private final @NonNull CardinalitySolution _208 // (|ownedValue| - 1)
			= new SubtractCardinalitySolution(_107, _001);
		private final @NonNull CardinalitySolution _209 // (|ownedVariables| - 1)
			= new SubtractCardinalitySolution(_108, _001);
		private final @NonNull CardinalitySolution _210 // (|prefix.','| - 1)
			= new SubtractCardinalitySolution(_110, _001);
		private final @NonNull CardinalitySolution _211 // (|prefix.';'| - 1)
			= new SubtractCardinalitySolution(_111, _001);
		private final @NonNull CardinalitySolution _212 // (|prefix.'|'| - 1)
			= new SubtractCardinalitySolution(_112, _001);
		private final @NonNull CardinalitySolution _213 // (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new GreaterThanCardinalitySolution(_113, _000);
		private final @NonNull CardinalitySolution _214 // (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new GreaterThanCardinalitySolution(_114, _000);
		private final @NonNull CardinalitySolution _215 // (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new GreaterThanCardinalitySolution(_115, _000);
		private final @NonNull CardinalitySolution _216 // (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new GreaterThanCardinalitySolution(_116, _000);
		private final @NonNull CardinalitySolution _217 // (|qualifiers.'definition'| - 1)
			= new SubtractCardinalitySolution(_117, _001);
		private final @NonNull CardinalitySolution _218 // (|qualifiers.'static'| - 1)
			= new SubtractCardinalitySolution(_118, _001);
		private final @NonNull CardinalitySolution _219 // (|referredElement| - 1)
			= new SubtractCardinalitySolution(_119, _001);
		private final @NonNull CardinalitySolution _220 // (|referredKeys| - 1)
			= new SubtractCardinalitySolution(_120, _001);
		private final @NonNull CardinalitySolution _221 // (|referredKeys| > 0)
			= new GreaterThanCardinalitySolution(_120, _000);
		private final @NonNull CardinalitySolution _222 // (|referredProperty| - 1)
			= new SubtractCardinalitySolution(_122, _001);
		private final @NonNull CardinalitySolution _223 // (|stereotype.'invariant'| - 1)
			= new SubtractCardinalitySolution(_125, _001);
		private final @NonNull CardinalitySolution _224 // (|stereotype.'postcondition'| - 1)
			= new SubtractCardinalitySolution(_126, _001);
		private final @NonNull CardinalitySolution _225 // (|stereotype.'precondition'| - 1)
			= new SubtractCardinalitySolution(_127, _001);
		private final @NonNull CardinalitySolution _226 // (|stringBounds.'*|+|?'| - 1)
			= new SubtractCardinalitySolution(_128, _001);
		private final @NonNull CardinalitySolution _227 // (|symbol.'false|true'| - 1)
			= new SubtractCardinalitySolution(_129, _001);
		private final @NonNull CardinalitySolution _228 // (|symbol| - 1)
			= new SubtractCardinalitySolution(_130, _001);
	}

	/**
	 * Steps for the matching process.
	 */
	private class _MatchSteps
	{
		private final @NonNull CardinalitySolutionStep _000 // assert (|exprString| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._135);
		private final @NonNull CardinalitySolutionStep _001 // assert (|lowerBound| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._139);
		private final @NonNull CardinalitySolutionStep _002 // assert (|name.'Map'| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._140);
		private final @NonNull CardinalitySolutionStep _003 // assert (|name.'Tuple'| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._141);
		private final @NonNull CardinalitySolutionStep _004 // assert (|name| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._143);
		private final @NonNull CardinalitySolutionStep _005 // assert (|name| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._144);
		private final @NonNull CardinalitySolutionStep _006 // assert (|name| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._142);
		private final @NonNull CardinalitySolutionStep _007 // assert (|ownedActualParameter| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._145);
		private final @NonNull CardinalitySolutionStep _008 // assert (|ownedBinding| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._149);
		private final @NonNull CardinalitySolutionStep _009 // assert (|ownedCoIterator| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._151);
		private final @NonNull CardinalitySolutionStep _010 // assert (|ownedCondition| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._153);
		private final @NonNull CardinalitySolutionStep _011 // assert (|ownedCondition| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._152);
		private final @NonNull CardinalitySolutionStep _012 // assert (|ownedDetails| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._155);
		private final @NonNull CardinalitySolutionStep _013 // assert (|ownedElseExpression| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._157);
		private final @NonNull CardinalitySolutionStep _014 // assert (|ownedExpressionCS| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._160);
		private final @NonNull CardinalitySolutionStep _015 // assert (|ownedExpression| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._162);
		private final @NonNull CardinalitySolutionStep _016 // assert (|ownedExpression| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._163);
		private final @NonNull CardinalitySolutionStep _017 // assert (|ownedExpression| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._161);
		private final @NonNull CardinalitySolutionStep _018 // assert (|ownedExpression| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._164);
		private final @NonNull CardinalitySolutionStep _019 // assert (|ownedInExpression| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._167);
		private final @NonNull CardinalitySolutionStep _020 // assert (|ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._170);
		private final @NonNull CardinalitySolutionStep _021 // assert (|ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._169);
		private final @NonNull CardinalitySolutionStep _022 // assert (|ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._168);
		private final @NonNull CardinalitySolutionStep _023 // assert (|ownedKeyType| - V0) == 0
			= new CardinalitySolutionStep.Assert(mt._171);
		private final @NonNull CardinalitySolutionStep _024 // assert (|ownedKey| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._172);
		private final @NonNull CardinalitySolutionStep _025 // assert (|ownedLeft| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._173);
		private final @NonNull CardinalitySolutionStep _026 // assert (|ownedNameExpression| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._174);
		private final @NonNull CardinalitySolutionStep _027 // assert (|ownedPathElements| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._188);
		private final @NonNull CardinalitySolutionStep _028 // assert (|ownedPathName| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._189);
		private final @NonNull CardinalitySolutionStep _029 // assert (|ownedPathName| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._193);
		private final @NonNull CardinalitySolutionStep _030 // assert (|ownedPathName| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._191);
		private final @NonNull CardinalitySolutionStep _031 // assert (|ownedPathName| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._192);
		private final @NonNull CardinalitySolutionStep _032 // assert (|ownedPathName| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._190);
		private final @NonNull CardinalitySolutionStep _033 // assert (|ownedPatternType| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._194);
		private final @NonNull CardinalitySolutionStep _034 // assert (|ownedRight| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._195);
		private final @NonNull CardinalitySolutionStep _035 // assert (|ownedThenExpression| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._200);
		private final @NonNull CardinalitySolutionStep _036 // assert (|ownedThenExpression| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._201);
		private final @NonNull CardinalitySolutionStep _037 // assert (|ownedType| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._204);
		private final @NonNull CardinalitySolutionStep _038 // assert (|ownedType| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._203);
		private final @NonNull CardinalitySolutionStep _039 // assert (|ownedType| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._206);
		private final @NonNull CardinalitySolutionStep _040 // assert (|ownedType| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._202);
		private final @NonNull CardinalitySolutionStep _041 // assert (|ownedType| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._205);
		private final @NonNull CardinalitySolutionStep _042 // assert (|ownedType| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._207);
		private final @NonNull CardinalitySolutionStep _043 // assert (|ownedValue| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._208);
		private final @NonNull CardinalitySolutionStep _044 // assert (|prefix.','| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._210);
		private final @NonNull CardinalitySolutionStep _045 // assert (|prefix.';'| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._211);
		private final @NonNull CardinalitySolutionStep _046 // assert (|prefix.'|'| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._212);
		private final @NonNull CardinalitySolutionStep _047 // assert (|qualifiers.'definition'| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._217);
		private final @NonNull CardinalitySolutionStep _048 // assert (|qualifiers.'static'| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._218);
		private final @NonNull CardinalitySolutionStep _049 // assert (|referredElement| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._219);
		private final @NonNull CardinalitySolutionStep _050 // assert (|referredProperty| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._222);
		private final @NonNull CardinalitySolutionStep _051 // assert (|stereotype.'invariant'| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._223);
		private final @NonNull CardinalitySolutionStep _052 // assert (|stereotype.'postcondition'| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._224);
		private final @NonNull CardinalitySolutionStep _053 // assert (|stereotype.'precondition'| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._225);
		private final @NonNull CardinalitySolutionStep _054 // assert (|stringBounds.'*|+|?'| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._226);
		private final @NonNull CardinalitySolutionStep _055 // assert (|symbol.'false|true'| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._227);
		private final @NonNull CardinalitySolutionStep _056 // assert (|symbol| - 1) == 0
			= new CardinalitySolutionStep.Assert(mt._228);
		private final @NonNull CardinalitySolutionStep _057 // assign V0 = (|ownedArguments| > 0)
			= new CardinalitySolutionStep.Assign(0, mt._148);
		private final @NonNull CardinalitySolutionStep _058 // assign V0 = (|ownedExtends| > 0)
			= new CardinalitySolutionStep.Assign(0, mt._166);
		private final @NonNull CardinalitySolutionStep _059 // assign V0 = (|ownedParameters| - 1)
			= new CardinalitySolutionStep.Assign(0, mt._175);
		private final @NonNull CardinalitySolutionStep _060 // assign V0 = (|ownedParts| - 1)
			= new CardinalitySolutionStep.Assign(0, mt._178);
		private final @NonNull CardinalitySolutionStep _061 // assign V0 = (|ownedParts| > 0)
			= new CardinalitySolutionStep.Assign(0, mt._187);
		private final @NonNull CardinalitySolutionStep _062 // assign V0 = (|ownedParts| > 0)
			= new CardinalitySolutionStep.Assign(0, mt._184);
		private final @NonNull CardinalitySolutionStep _063 // assign V0 = (|ownedParts| > 0)
			= new CardinalitySolutionStep.Assign(0, mt._185);
		private final @NonNull CardinalitySolutionStep _064 // assign V0 = (|ownedParts| > 0)
			= new CardinalitySolutionStep.Assign(0, mt._186);
		private final @NonNull CardinalitySolutionStep _065 // assign V0 = (|ownedPathElements| - 1)
			= new CardinalitySolutionStep.Assign(0, mt._188);
		private final @NonNull CardinalitySolutionStep _066 // assign V0 = (|ownedSubstitutions| - 1)
			= new CardinalitySolutionStep.Assign(0, mt._196);
		private final @NonNull CardinalitySolutionStep _067 // assign V0 = (|ownedTerms| - 1)
			= new CardinalitySolutionStep.Assign(0, mt._199);
		private final @NonNull CardinalitySolutionStep _068 // assign V0 = (|ownedVariables| - 1)
			= new CardinalitySolutionStep.Assign(0, mt._209);
		private final @NonNull CardinalitySolutionStep _069 // assign V0 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new CardinalitySolutionStep.Assign(0, mt._216);
		private final @NonNull CardinalitySolutionStep _070 // assign V0 = 0
			= new CardinalitySolutionStep.Assign(0, mt._000);
		private final @NonNull CardinalitySolutionStep _071 // assign V0 = |isAbstract.'abstract'|
			= new CardinalitySolutionStep.Assign(0, mt._006);
		private final @NonNull CardinalitySolutionStep _072 // assign V0 = |isCallable.'callable'|
			= new CardinalitySolutionStep.Assign(0, mt._008);
		private final @NonNull CardinalitySolutionStep _073 // assign V0 = |isNullFree.'|1'|
			= new CardinalitySolutionStep.Assign(0, mt._010);
		private final @NonNull CardinalitySolutionStep _074 // assign V0 = |isPrimitive.'primitive'|
			= new CardinalitySolutionStep.Assign(0, mt._012);
		private final @NonNull CardinalitySolutionStep _075 // assign V0 = |literal|
			= new CardinalitySolutionStep.Assign(0, mt._015);
		private final @NonNull CardinalitySolutionStep _076 // assign V0 = |name|
			= new CardinalitySolutionStep.Assign(0, mt._020);
		private final @NonNull CardinalitySolutionStep _077 // assign V0 = |nsPrefix|
			= new CardinalitySolutionStep.Assign(0, mt._022);
		private final @NonNull CardinalitySolutionStep _078 // assign V0 = |ownedCoIterator|
			= new CardinalitySolutionStep.Assign(0, mt._030);
		private final @NonNull CardinalitySolutionStep _079 // assign V0 = |ownedCurlyBracketedClause|
			= new CardinalitySolutionStep.Assign(0, mt._036);
		private final @NonNull CardinalitySolutionStep _080 // assign V0 = |ownedDetails|
			= new CardinalitySolutionStep.Assign(0, mt._039);
		private final @NonNull CardinalitySolutionStep _081 // assign V0 = |ownedExtends|
			= new CardinalitySolutionStep.Assign(0, mt._048);
		private final @NonNull CardinalitySolutionStep _082 // assign V0 = |ownedIfThenExpressions|
			= new CardinalitySolutionStep.Assign(0, mt._049);
		private final @NonNull CardinalitySolutionStep _083 // assign V0 = |ownedInitExpression|
			= new CardinalitySolutionStep.Assign(0, mt._053);
		private final @NonNull CardinalitySolutionStep _084 // assign V0 = |ownedLastExpression|
			= new CardinalitySolutionStep.Assign(0, mt._058);
		private final @NonNull CardinalitySolutionStep _085 // assign V0 = |ownedMultiplicity|
			= new CardinalitySolutionStep.Assign(0, mt._063);
		private final @NonNull CardinalitySolutionStep _086 // assign V0 = |ownedRoundBracketedClause|
			= new CardinalitySolutionStep.Assign(0, mt._088);
		private final @NonNull CardinalitySolutionStep _087 // assign V0 = |ownedSignature|
			= new CardinalitySolutionStep.Assign(0, mt._090);
		private final @NonNull CardinalitySolutionStep _088 // assign V0 = |ownedSquareBracketedClauses|
			= new CardinalitySolutionStep.Assign(0, mt._092);
		private final @NonNull CardinalitySolutionStep _089 // assign V0 = |ownedType|
			= new CardinalitySolutionStep.Assign(0, mt._103);
		private final @NonNull CardinalitySolutionStep _090 // assign V0 = |ownedType|
			= new CardinalitySolutionStep.Assign(0, mt._098);
		private final @NonNull CardinalitySolutionStep _091 // assign V0 = |ownedType|
			= new CardinalitySolutionStep.Assign(0, mt._100);
		private final @NonNull CardinalitySolutionStep _092 // assign V0 = |ownedType|
			= new CardinalitySolutionStep.Assign(0, mt._101);
		private final @NonNull CardinalitySolutionStep _093 // assign V0 = |ownedValueType|
			= new CardinalitySolutionStep.Assign(0, mt._106);
		private final @NonNull CardinalitySolutionStep _094 // assign V0 = |patternVariableName|
			= new CardinalitySolutionStep.Assign(0, mt._109);
		private final @NonNull CardinalitySolutionStep _095 // assign V0 = |qualifiers.'definition'|
			= new CardinalitySolutionStep.Assign(0, mt._117);
		private final @NonNull CardinalitySolutionStep _096 // assign V0 = |qualifiers.'static'|
			= new CardinalitySolutionStep.Assign(0, mt._118);
		private final @NonNull CardinalitySolutionStep _097 // assign V0 = |referredOpposite|
			= new CardinalitySolutionStep.Assign(0, mt._121);
		private final @NonNull CardinalitySolutionStep _098 // assign V0 = |restVariableName|
			= new CardinalitySolutionStep.Assign(0, mt._123);
		private final @NonNull CardinalitySolutionStep _099 // assign V0 = |segments|
			= new CardinalitySolutionStep.Assign(0, mt._124);
		private final @NonNull CardinalitySolutionStep _100 // assign V0 = |upperBound|
			= new CardinalitySolutionStep.Assign(0, mt._131);
		private final @NonNull CardinalitySolutionStep _101 // assign V0 = |values|
			= new CardinalitySolutionStep.Assign(0, mt._132);
		private final @NonNull CardinalitySolutionStep _102 // assign V0 = |value|
			= new CardinalitySolutionStep.Assign(0, mt._133);
		private final @NonNull CardinalitySolutionStep _103 // assign V1 = (|ownedArguments| - 1)
			= new CardinalitySolutionStep.Assign(1, mt._147);
		private final @NonNull CardinalitySolutionStep _104 // assign V1 = (|ownedDetails| > 0)
			= new CardinalitySolutionStep.Assign(1, mt._156);
		private final @NonNull CardinalitySolutionStep _105 // assign V1 = (|ownedExtends| - 1)
			= new CardinalitySolutionStep.Assign(1, mt._165);
		private final @NonNull CardinalitySolutionStep _106 // assign V1 = (|ownedParameters| > 0)
			= new CardinalitySolutionStep.Assign(1, mt._177);
		private final @NonNull CardinalitySolutionStep _107 // assign V1 = (|ownedParts| - 1)
			= new CardinalitySolutionStep.Assign(1, mt._180);
		private final @NonNull CardinalitySolutionStep _108 // assign V1 = (|ownedParts| - 1)
			= new CardinalitySolutionStep.Assign(1, mt._182);
		private final @NonNull CardinalitySolutionStep _109 // assign V1 = (|ownedParts| - 1)
			= new CardinalitySolutionStep.Assign(1, mt._179);
		private final @NonNull CardinalitySolutionStep _110 // assign V1 = (|ownedParts| - 1)
			= new CardinalitySolutionStep.Assign(1, mt._183);
		private final @NonNull CardinalitySolutionStep _111 // assign V1 = (|ownedParts| > 0)
			= new CardinalitySolutionStep.Assign(1, mt._186);
		private final @NonNull CardinalitySolutionStep _112 // assign V1 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new CardinalitySolutionStep.Assign(1, mt._216);
		private final @NonNull CardinalitySolutionStep _113 // assign V1 = |default|
			= new CardinalitySolutionStep.Assign(1, mt._003);
		private final @NonNull CardinalitySolutionStep _114 // assign V1 = |instanceClassName|
			= new CardinalitySolutionStep.Assign(1, mt._005);
		private final @NonNull CardinalitySolutionStep _115 // assign V1 = |isAll.'::*'|
			= new CardinalitySolutionStep.Assign(1, mt._007);
		private final @NonNull CardinalitySolutionStep _116 // assign V1 = |isNullFree.'|1'|
			= new CardinalitySolutionStep.Assign(1, mt._010);
		private final @NonNull CardinalitySolutionStep _117 // assign V1 = |name|
			= new CardinalitySolutionStep.Assign(1, mt._020);
		private final @NonNull CardinalitySolutionStep _118 // assign V1 = |nsURI|
			= new CardinalitySolutionStep.Assign(1, mt._023);
		private final @NonNull CardinalitySolutionStep _119 // assign V1 = |ownedCoIterator|
			= new CardinalitySolutionStep.Assign(1, mt._030);
		private final @NonNull CardinalitySolutionStep _120 // assign V1 = |ownedCollectionMultiplicity|
			= new CardinalitySolutionStep.Assign(1, mt._031);
		private final @NonNull CardinalitySolutionStep _121 // assign V1 = |ownedImports|
			= new CardinalitySolutionStep.Assign(1, mt._051);
		private final @NonNull CardinalitySolutionStep _122 // assign V1 = |ownedInitExpression|
			= new CardinalitySolutionStep.Assign(1, mt._053);
		private final @NonNull CardinalitySolutionStep _123 // assign V1 = |ownedMessageSpecification|
			= new CardinalitySolutionStep.Assign(1, mt._061);
		private final @NonNull CardinalitySolutionStep _124 // assign V1 = |ownedMultiplicity|
			= new CardinalitySolutionStep.Assign(1, mt._063);
		private final @NonNull CardinalitySolutionStep _125 // assign V1 = |ownedMultiplicity|
			= new CardinalitySolutionStep.Assign(1, mt._062);
		private final @NonNull CardinalitySolutionStep _126 // assign V1 = |ownedPatternGuard|
			= new CardinalitySolutionStep.Assign(1, mt._081);
		private final @NonNull CardinalitySolutionStep _127 // assign V1 = |ownedRoundBracketedClause|
			= new CardinalitySolutionStep.Assign(1, mt._089);
		private final @NonNull CardinalitySolutionStep _128 // assign V1 = |ownedSignature|
			= new CardinalitySolutionStep.Assign(1, mt._090);
		private final @NonNull CardinalitySolutionStep _129 // assign V1 = |ownedType|
			= new CardinalitySolutionStep.Assign(1, mt._103);
		private final @NonNull CardinalitySolutionStep _130 // assign V1 = |ownedType|
			= new CardinalitySolutionStep.Assign(1, mt._098);
		private final @NonNull CardinalitySolutionStep _131 // assign V1 = |qualifiers.'!ordered|!unique|ordered|unique'|
			= new CardinalitySolutionStep.Assign(1, mt._116);
		private final @NonNull CardinalitySolutionStep _132 // assign V1 = |referredOpposite|
			= new CardinalitySolutionStep.Assign(1, mt._121);
		private final @NonNull CardinalitySolutionStep _133 // assign V1 = |value|
			= new CardinalitySolutionStep.Assign(1, mt._134);
		private final @NonNull CardinalitySolutionStep _134 // assign V10 = (|ownedBodyExpressions| > 0)
			= new CardinalitySolutionStep.Assign(10, mt._150);
		private final @NonNull CardinalitySolutionStep _135 // assign V10 = (|ownedDefaultExpressions| > 0)
			= new CardinalitySolutionStep.Assign(10, mt._154);
		private final @NonNull CardinalitySolutionStep _136 // assign V10 = 0
			= new CardinalitySolutionStep.Assign(10, mt._000);
		private final @NonNull CardinalitySolutionStep _137 // assign V10 = |ownedConstraints|
			= new CardinalitySolutionStep.Assign(10, mt._034);
		private final @NonNull CardinalitySolutionStep _138 // assign V10 = |ownedPreconditions|
			= new CardinalitySolutionStep.Assign(10, mt._084);
		private final @NonNull CardinalitySolutionStep _139 // assign V11 = (|ownedBodyExpressions| > 0)
			= new CardinalitySolutionStep.Assign(11, mt._150);
		private final @NonNull CardinalitySolutionStep _140 // assign V11 = 0
			= new CardinalitySolutionStep.Assign(11, mt._000);
		private final @NonNull CardinalitySolutionStep _141 // assign V11 = |ownedBodyExpressions|
			= new CardinalitySolutionStep.Assign(11, mt._028);
		private final @NonNull CardinalitySolutionStep _142 // assign V12 = 0
			= new CardinalitySolutionStep.Assign(12, mt._000);
		private final @NonNull CardinalitySolutionStep _143 // assign V12 = |ownedBodyExpressions|
			= new CardinalitySolutionStep.Assign(12, mt._028);
		private final @NonNull CardinalitySolutionStep _144 // assign V12 = |ownedImplicitOpposites|
			= new CardinalitySolutionStep.Assign(12, mt._050);
		private final @NonNull CardinalitySolutionStep _145 // assign V12 = |ownedPostconditions|
			= new CardinalitySolutionStep.Assign(12, mt._083);
		private final @NonNull CardinalitySolutionStep _146 // assign V13 = |ownedImplicitOpposites|
			= new CardinalitySolutionStep.Assign(13, mt._050);
		private final @NonNull CardinalitySolutionStep _147 // assign V13 = |ownedPostconditions|
			= new CardinalitySolutionStep.Assign(13, mt._083);
		private final @NonNull CardinalitySolutionStep _148 // assign V2 = (|isSerializable.'serializable'| > 0)
			= new CardinalitySolutionStep.Assign(2, mt._138);
		private final @NonNull CardinalitySolutionStep _149 // assign V2 = (|ownedDetails| - 1)
			= new CardinalitySolutionStep.Assign(2, mt._155);
		private final @NonNull CardinalitySolutionStep _150 // assign V2 = (|ownedParameters| - 1)
			= new CardinalitySolutionStep.Assign(2, mt._176);
		private final @NonNull CardinalitySolutionStep _151 // assign V2 = (|ownedParameters| > 0)
			= new CardinalitySolutionStep.Assign(2, mt._177);
		private final @NonNull CardinalitySolutionStep _152 // assign V2 = (|ownedParts| - 1)
			= new CardinalitySolutionStep.Assign(2, mt._181);
		private final @NonNull CardinalitySolutionStep _153 // assign V2 = (|ownedSuperTypes| > 0)
			= new CardinalitySolutionStep.Assign(2, mt._198);
		private final @NonNull CardinalitySolutionStep _154 // assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new CardinalitySolutionStep.Assign(2, mt._214);
		private final @NonNull CardinalitySolutionStep _155 // assign V2 = 0
			= new CardinalitySolutionStep.Assign(2, mt._000);
		private final @NonNull CardinalitySolutionStep _156 // assign V2 = |default|
			= new CardinalitySolutionStep.Assign(2, mt._003);
		private final @NonNull CardinalitySolutionStep _157 // assign V2 = |instanceClassName|
			= new CardinalitySolutionStep.Assign(2, mt._005);
		private final @NonNull CardinalitySolutionStep _158 // assign V2 = |ownedAnnotations|
			= new CardinalitySolutionStep.Assign(2, mt._025);
		private final @NonNull CardinalitySolutionStep _159 // assign V2 = |ownedCurlyBracketedClause|
			= new CardinalitySolutionStep.Assign(2, mt._037);
		private final @NonNull CardinalitySolutionStep _160 // assign V2 = |ownedMessageSpecification|
			= new CardinalitySolutionStep.Assign(2, mt._061);
		private final @NonNull CardinalitySolutionStep _161 // assign V2 = |ownedMultiplicity|
			= new CardinalitySolutionStep.Assign(2, mt._063);
		private final @NonNull CardinalitySolutionStep _162 // assign V2 = |ownedPackages|
			= new CardinalitySolutionStep.Assign(2, mt._066);
		private final @NonNull CardinalitySolutionStep _163 // assign V2 = |ownedSpecification|
			= new CardinalitySolutionStep.Assign(2, mt._091);
		private final @NonNull CardinalitySolutionStep _164 // assign V2 = |ownedType|
			= new CardinalitySolutionStep.Assign(2, mt._103);
		private final @NonNull CardinalitySolutionStep _165 // assign V2 = |qualifiers.'!ordered|!unique|ordered|unique'|
			= new CardinalitySolutionStep.Assign(2, mt._116);
		private final @NonNull CardinalitySolutionStep _166 // assign V3 = (|isSerializable.'serializable'| > 0)
			= new CardinalitySolutionStep.Assign(3, mt._137);
		private final @NonNull CardinalitySolutionStep _167 // assign V3 = (|ownedAnnotations| > 0)
			= new CardinalitySolutionStep.Assign(3, mt._146);
		private final @NonNull CardinalitySolutionStep _168 // assign V3 = (|ownedParameters| - 1)
			= new CardinalitySolutionStep.Assign(3, mt._176);
		private final @NonNull CardinalitySolutionStep _169 // assign V3 = (|ownedSuperTypes| - 1)
			= new CardinalitySolutionStep.Assign(3, mt._197);
		private final @NonNull CardinalitySolutionStep _170 // assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new CardinalitySolutionStep.Assign(3, mt._213);
		private final @NonNull CardinalitySolutionStep _171 // assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new CardinalitySolutionStep.Assign(3, mt._214);
		private final @NonNull CardinalitySolutionStep _172 // assign V3 = 0
			= new CardinalitySolutionStep.Assign(3, mt._000);
		private final @NonNull CardinalitySolutionStep _173 // assign V3 = |default|
			= new CardinalitySolutionStep.Assign(3, mt._003);
		private final @NonNull CardinalitySolutionStep _174 // assign V3 = |isPre.'@'|
			= new CardinalitySolutionStep.Assign(3, mt._011);
		private final @NonNull CardinalitySolutionStep _175 // assign V3 = |isSerializable.'serializable'|
			= new CardinalitySolutionStep.Assign(3, mt._013);
		private final @NonNull CardinalitySolutionStep _176 // assign V3 = |ownedAnnotations|
			= new CardinalitySolutionStep.Assign(3, mt._025);
		private final @NonNull CardinalitySolutionStep _177 // assign V3 = |ownedMultiplicity|
			= new CardinalitySolutionStep.Assign(3, mt._063);
		private final @NonNull CardinalitySolutionStep _178 // assign V3 = |ownedPackages|
			= new CardinalitySolutionStep.Assign(3, mt._066);
		private final @NonNull CardinalitySolutionStep _179 // assign V3 = |ownedSpecification|
			= new CardinalitySolutionStep.Assign(3, mt._091);
		private final @NonNull CardinalitySolutionStep _180 // assign V3 = |ownedType|
			= new CardinalitySolutionStep.Assign(3, mt._103);
		private final @NonNull CardinalitySolutionStep _181 // assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new CardinalitySolutionStep.Assign(3, mt._114);
		private final @NonNull CardinalitySolutionStep _182 // assign V4 = (|ownedExceptions| > 0)
			= new CardinalitySolutionStep.Assign(4, mt._159);
		private final @NonNull CardinalitySolutionStep _183 // assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new CardinalitySolutionStep.Assign(4, mt._213);
		private final @NonNull CardinalitySolutionStep _184 // assign V4 = |instanceClassName|
			= new CardinalitySolutionStep.Assign(4, mt._005);
		private final @NonNull CardinalitySolutionStep _185 // assign V4 = |isSerializable.'serializable'|
			= new CardinalitySolutionStep.Assign(4, mt._014);
		private final @NonNull CardinalitySolutionStep _186 // assign V4 = |ownedAnnotations|
			= new CardinalitySolutionStep.Assign(4, mt._025);
		private final @NonNull CardinalitySolutionStep _187 // assign V4 = |ownedClasses|
			= new CardinalitySolutionStep.Assign(4, mt._029);
		private final @NonNull CardinalitySolutionStep _188 // assign V4 = |ownedContents|
			= new CardinalitySolutionStep.Assign(4, mt._035);
		private final @NonNull CardinalitySolutionStep _189 // assign V4 = |ownedLiterals|
			= new CardinalitySolutionStep.Assign(4, mt._060);
		private final @NonNull CardinalitySolutionStep _190 // assign V4 = |ownedType|
			= new CardinalitySolutionStep.Assign(4, mt._103);
		private final @NonNull CardinalitySolutionStep _191 // assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new CardinalitySolutionStep.Assign(4, mt._113);
		private final @NonNull CardinalitySolutionStep _192 // assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new CardinalitySolutionStep.Assign(4, mt._114);
		private final @NonNull CardinalitySolutionStep _193 // assign V5 = (|isInterface.'interface'| > 0)
			= new CardinalitySolutionStep.Assign(5, mt._136);
		private final @NonNull CardinalitySolutionStep _194 // assign V5 = (|ownedExceptions| - 1)
			= new CardinalitySolutionStep.Assign(5, mt._158);
		private final @NonNull CardinalitySolutionStep _195 // assign V5 = (|ownedExceptions| > 0)
			= new CardinalitySolutionStep.Assign(5, mt._159);
		private final @NonNull CardinalitySolutionStep _196 // assign V5 = |ownedAnnotations|
			= new CardinalitySolutionStep.Assign(5, mt._025);
		private final @NonNull CardinalitySolutionStep _197 // assign V5 = |ownedConstraints|
			= new CardinalitySolutionStep.Assign(5, mt._034);
		private final @NonNull CardinalitySolutionStep _198 // assign V5 = |ownedDefaultExpressions|
			= new CardinalitySolutionStep.Assign(5, mt._038);
		private final @NonNull CardinalitySolutionStep _199 // assign V5 = |ownedLiterals|
			= new CardinalitySolutionStep.Assign(5, mt._060);
		private final @NonNull CardinalitySolutionStep _200 // assign V5 = |ownedReferences|
			= new CardinalitySolutionStep.Assign(5, mt._086);
		private final @NonNull CardinalitySolutionStep _201 // assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new CardinalitySolutionStep.Assign(5, mt._113);
		private final @NonNull CardinalitySolutionStep _202 // assign V6 = (|ownedDefaultExpressions| > 0)
			= new CardinalitySolutionStep.Assign(6, mt._154);
		private final @NonNull CardinalitySolutionStep _203 // assign V6 = (|ownedExceptions| - 1)
			= new CardinalitySolutionStep.Assign(6, mt._158);
		private final @NonNull CardinalitySolutionStep _204 // assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new CardinalitySolutionStep.Assign(6, mt._215);
		private final @NonNull CardinalitySolutionStep _205 // assign V6 = (|referredKeys| > 0)
			= new CardinalitySolutionStep.Assign(6, mt._221);
		private final @NonNull CardinalitySolutionStep _206 // assign V6 = |isInterface.'interface'|
			= new CardinalitySolutionStep.Assign(6, mt._009);
		private final @NonNull CardinalitySolutionStep _207 // assign V6 = |ownedAnnotations|
			= new CardinalitySolutionStep.Assign(6, mt._025);
		private final @NonNull CardinalitySolutionStep _208 // assign V6 = |ownedConstraints|
			= new CardinalitySolutionStep.Assign(6, mt._034);
		private final @NonNull CardinalitySolutionStep _209 // assign V6 = |ownedDefaultExpressions|
			= new CardinalitySolutionStep.Assign(6, mt._038);
		private final @NonNull CardinalitySolutionStep _210 // assign V7 = (|ownedDefaultExpressions| > 0)
			= new CardinalitySolutionStep.Assign(7, mt._154);
		private final @NonNull CardinalitySolutionStep _211 // assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new CardinalitySolutionStep.Assign(7, mt._215);
		private final @NonNull CardinalitySolutionStep _212 // assign V7 = (|referredKeys| - 1)
			= new CardinalitySolutionStep.Assign(7, mt._220);
		private final @NonNull CardinalitySolutionStep _213 // assign V7 = (|referredKeys| > 0)
			= new CardinalitySolutionStep.Assign(7, mt._221);
		private final @NonNull CardinalitySolutionStep _214 // assign V7 = 0
			= new CardinalitySolutionStep.Assign(7, mt._000);
		private final @NonNull CardinalitySolutionStep _215 // assign V7 = |ownedAnnotations|
			= new CardinalitySolutionStep.Assign(7, mt._025);
		private final @NonNull CardinalitySolutionStep _216 // assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new CardinalitySolutionStep.Assign(7, mt._115);
		private final @NonNull CardinalitySolutionStep _217 // assign V8 = (|referredKeys| - 1)
			= new CardinalitySolutionStep.Assign(8, mt._220);
		private final @NonNull CardinalitySolutionStep _218 // assign V8 = 0
			= new CardinalitySolutionStep.Assign(8, mt._000);
		private final @NonNull CardinalitySolutionStep _219 // assign V8 = |ownedAnnotations|
			= new CardinalitySolutionStep.Assign(8, mt._025);
		private final @NonNull CardinalitySolutionStep _220 // assign V8 = |ownedDefaultExpressions|
			= new CardinalitySolutionStep.Assign(8, mt._038);
		private final @NonNull CardinalitySolutionStep _221 // assign V8 = |ownedOperations|
			= new CardinalitySolutionStep.Assign(8, mt._065);
		private final @NonNull CardinalitySolutionStep _222 // assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new CardinalitySolutionStep.Assign(8, mt._115);
		private final @NonNull CardinalitySolutionStep _223 // assign V9 = (|ownedDefaultExpressions| > 0)
			= new CardinalitySolutionStep.Assign(9, mt._154);
		private final @NonNull CardinalitySolutionStep _224 // assign V9 = 0
			= new CardinalitySolutionStep.Assign(9, mt._000);
		private final @NonNull CardinalitySolutionStep _225 // assign V9 = |ownedAnnotations|
			= new CardinalitySolutionStep.Assign(9, mt._025);
		private final @NonNull CardinalitySolutionStep _226 // assign V9 = |ownedDefaultExpressions|
			= new CardinalitySolutionStep.Assign(9, mt._038);
		private final @NonNull CardinalitySolutionStep _227 // assign V9 = |ownedPreconditions|
			= new CardinalitySolutionStep.Assign(9, mt._084);
		private final @NonNull CardinalitySolutionStep _228 // assign V9 = |ownedProperties|
			= new CardinalitySolutionStep.Assign(9, mt._085);
		private final @NonNull CardinalitySolutionStep _229 // check-rule basecs::AnnotationCS.ownedContents : 53
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, iv._17/*ModelElementCS*/);
		private final @NonNull CardinalitySolutionStep _230 // check-rule basecs::AnnotationCS.ownedReferences : 54
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, iv._18/*ModelElementRefCS*/);
		private final @NonNull CardinalitySolutionStep _231 // check-rule basecs::AnnotationElementCS.ownedDetails : 16
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, iv._6/*DetailCS*/);
		private final @NonNull CardinalitySolutionStep _232 // check-rule basecs::ClassCS.ownedConstraints : 41
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, iv._13/*InvariantConstraintCS*/);
		private final @NonNull CardinalitySolutionStep _233 // check-rule basecs::ConstraintCS.ownedMessageSpecification : 92
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, iv._36/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _234 // check-rule basecs::ConstraintCS.ownedSpecification : 92
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, iv._36/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _235 // check-rule basecs::EnumerationCS.ownedLiterals : 22
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, iv._8/*EnumerationLiteralCS*/);
		private final @NonNull CardinalitySolutionStep _236 // check-rule basecs::ImportCS.ownedPathName : 123
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, iv._64/*URIPathNameCS*/);
		private final @NonNull CardinalitySolutionStep _237 // check-rule basecs::ModelElementCS.ownedAnnotations : 2
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, iv._0/*AnnotationElementCS*/);
		private final @NonNull CardinalitySolutionStep _238 // check-rule basecs::ModelElementRefCS.ownedPathName : 73
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, iv._26/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _239 // check-rule basecs::OperationCS.ownedBodyExpressions : 92
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, iv._36/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _240 // check-rule basecs::OperationCS.ownedExceptions : 117
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, iv._59/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _241 // check-rule basecs::OperationCS.ownedParameters : 72
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, iv._25/*ParameterCS*/);
		private final @NonNull CardinalitySolutionStep _242 // check-rule basecs::OperationCS.ownedPostconditions : 75
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, iv._29/*PostconditionConstraintCS*/);
		private final @NonNull CardinalitySolutionStep _243 // check-rule basecs::OperationCS.ownedPreconditions : 76
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, iv._30/*PreconditionConstraintCS*/);
		private final @NonNull CardinalitySolutionStep _244 // check-rule basecs::PackageCS.ownedClasses : 6
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, iv._1/*ClassCS*/);
		private final @NonNull CardinalitySolutionStep _245 // check-rule basecs::PackageOwnerCS.ownedPackages : 71
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, iv._24/*PackageCS*/);
		private final @NonNull CardinalitySolutionStep _246 // check-rule basecs::PathNameCS.ownedPathElements : 31
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._10/*FirstPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _247 // check-rule basecs::PathNameCS.ownedPathElements : 31|67
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._22/*FirstPathElementCS|NextPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _248 // check-rule basecs::PathNameCS.ownedPathElements : 67|122
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._62/*NextPathElementCS|URIFirstPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _249 // check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, iv._11/*ImplicitOppositeCS*/);
		private final @NonNull CardinalitySolutionStep _250 // check-rule basecs::RootCS.ownedImports : 38
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, iv._12/*ImportCS*/);
		private final @NonNull CardinalitySolutionStep _251 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, iv._36/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _252 // check-rule basecs::StructuredClassCS.ownedOperations : 70
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, iv._23/*OperationCS*/);
		private final @NonNull CardinalitySolutionStep _253 // check-rule basecs::StructuredClassCS.ownedProperties : 96
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, iv._39/*StructuralFeatureCS*/);
		private final @NonNull CardinalitySolutionStep _254 // check-rule basecs::StructuredClassCS.ownedSuperTypes : 117
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, iv._59/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _255 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : 56
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, iv._19/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _256 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : 100
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, iv._45/*TemplateParameterSubstitutionCS*/);
		private final @NonNull CardinalitySolutionStep _257 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 115
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, iv._57/*TypeRefCS*/);
		private final @NonNull CardinalitySolutionStep _258 // check-rule basecs::TemplateSignatureCS.ownedParameters : 114
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, iv._56/*TypeParameterCS*/);
		private final @NonNull CardinalitySolutionStep _259 // check-rule basecs::TemplateableElementCS.ownedSignature : 101
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, iv._46/*TemplateSignatureCS*/);
		private final @NonNull CardinalitySolutionStep _260 // check-rule basecs::TupleTypeCS.ownedParts : 105
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, iv._48/*TuplePartCS*/);
		private final @NonNull CardinalitySolutionStep _261 // check-rule basecs::TypeParameterCS.ownedExtends : 117
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, iv._59/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _262 // check-rule basecs::TypedElementCS.ownedType : 107
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, iv._49/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _263 // check-rule basecs::TypedElementCS.ownedType : 116
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, iv._58/*TypedMultiplicityRefCS*/);
		private final @NonNull CardinalitySolutionStep _264 // check-rule basecs::TypedRefCS.ownedMultiplicity : 56
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, iv._19/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _265 // check-rule basecs::TypedTypeRefCS.ownedBinding : 99
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, iv._44/*TemplateBindingCS*/);
		private final @NonNull CardinalitySolutionStep _266 // check-rule basecs::TypedTypeRefCS.ownedPathName : 73
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, iv._26/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _267 // check-rule basecs::WildcardTypeRefCS.ownedExtends : 117
			= new CardinalitySolutionStep.RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, iv._59/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _268 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._5/*CurlyBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _269 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, iv._26/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _270 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._34/*RoundBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _271 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, iv._37/*SquareBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _272 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, iv._3/*CollectionLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _273 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, iv._4/*CollectionTypeCS*/);
		private final @NonNull CardinalitySolutionStep _274 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _275 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 74
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._27/*PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _276 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _277 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, iv._27/*PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _278 // check-rule essentialoclcs::CollectionPatternCS.ownedType : 11
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, iv._4/*CollectionTypeCS*/);
		private final @NonNull CardinalitySolutionStep _279 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, iv._19/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _280 // check-rule essentialoclcs::CollectionTypeCS.ownedType : 108
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, iv._50/*TypeExpWithoutMultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _281 // check-rule essentialoclcs::ContextCS.ownedExpression : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _282 // check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 90
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, iv._35/*ShadowPartCS*/);
		private final @NonNull CardinalitySolutionStep _283 // check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _284 // check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, iv._28/*ExpCS|PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _285 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _286 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, iv._7/*ElseIfThenExpCS*/);
		private final @NonNull CardinalitySolutionStep _287 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _288 // check-rule essentialoclcs::IfThenExpCS.ownedCondition : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _289 // check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _290 // check-rule essentialoclcs::InfixExpCS.ownedLeft : 78
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, iv._33/*PrefixedPrimaryExpCS*/);
		private final @NonNull CardinalitySolutionStep _291 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _292 // check-rule essentialoclcs::LetExpCS.ownedInExpression : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _293 // check-rule essentialoclcs::LetExpCS.ownedVariables : 46
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, iv._14/*LetVariableCS*/);
		private final @NonNull CardinalitySolutionStep _294 // check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 84
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._34/*RoundBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _295 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, iv._15/*MapLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _296 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, iv._16/*MapTypeCS*/);
		private final @NonNull CardinalitySolutionStep _297 // check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _298 // check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _299 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, iv._49/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _300 // check-rule essentialoclcs::MapTypeCS.ownedValueType : 107
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, iv._49/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _301 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, iv._2/*CoIteratorVariableCS*/);
		private final @NonNull CardinalitySolutionStep _302 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _303 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, iv._20/*NavigatingArgExpCS*/);
		private final @NonNull CardinalitySolutionStep _304 // check-rule essentialoclcs::NavigatingArgCS.ownedType : 107
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, iv._49/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _305 // check-rule essentialoclcs::NestedExpCS.ownedExpression : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _306 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _307 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 77
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._31/*PrefixedLetExpCS*/);
		private final @NonNull CardinalitySolutionStep _308 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 78
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._33/*PrefixedPrimaryExpCS*/);
		private final @NonNull CardinalitySolutionStep _309 // check-rule essentialoclcs::PatternExpCS.ownedPatternType : 107
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, iv._49/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _310 // check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 60|62|63|64
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, iv._21/*NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS*/);
		private final @NonNull CardinalitySolutionStep _311 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 30|74
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._28/*ExpCS|PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _312 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 95
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._38/*StringLiteralExpCS*/);
		private final @NonNull CardinalitySolutionStep _313 // check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _314 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, iv._47/*TupleLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _315 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, iv._52/*TypeLiteralWithMultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _316 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._5/*CurlyBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _317 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, iv._26/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _318 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _319 // check-rule essentialoclcs::VariableCS.ownedInitExpression : 30
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _320 // check-rule essentialoclcs::VariableCS.ownedType : 107
			= new CardinalitySolutionStep.RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, iv._49/*TypeExpCS*/);
	}

	/**
	 * The various serialization term used to serialize a serialization rule.
	 */
	private class _SerializationTerms
	{
		private final @NonNull RTSerializationLiteralStep _000 // 1*'!serializable'
									= new RTSerializationLiteralStep(-1, "!serializable");
		private final @NonNull RTSerializationLiteralStep _001 // 1*'#'
									= new RTSerializationLiteralStep(-1, "#");
		private final @NonNull RTSerializationLiteralStep _002 // 1*'&&'
									= new RTSerializationLiteralStep(-1, "&&");
		private final @NonNull RTSerializationLiteralStep _003 // 1*'('
									= new RTSerializationLiteralStep(-1, "(");
		private final @NonNull RTSerializationLiteralStep _004 // 1*')'
									= new RTSerializationLiteralStep(-1, ")");
		private final @NonNull RTSerializationLiteralStep _005 // 1*'*'
									= new RTSerializationLiteralStep(-1, "*");
		private final @NonNull RTSerializationLiteralStep _006 // 1*'++'
									= new RTSerializationLiteralStep(-1, "++");
		private final @NonNull RTSerializationLiteralStep _007 // 1*','
									= new RTSerializationLiteralStep(-1, ",");
		private final @NonNull RTSerializationLiteralStep _008 // 1*'..'
									= new RTSerializationLiteralStep(-1, "..");
		private final @NonNull RTSerializationLiteralStep _009 // 1*':'
									= new RTSerializationLiteralStep(-1, ":");
		private final @NonNull RTSerializationLiteralStep _010 // 1*'::'
									= new RTSerializationLiteralStep(-1, "::");
		private final @NonNull RTSerializationLiteralStep _011 // 1*';'
									= new RTSerializationLiteralStep(-1, ";");
		private final @NonNull RTSerializationLiteralStep _012 // 1*'<'
									= new RTSerializationLiteralStep(-1, "<");
		private final @NonNull RTSerializationLiteralStep _013 // 1*'<-'
									= new RTSerializationLiteralStep(-1, "<-");
		private final @NonNull RTSerializationLiteralStep _014 // 1*'='
									= new RTSerializationLiteralStep(-1, "=");
		private final @NonNull RTSerializationLiteralStep _015 // 1*'>'
									= new RTSerializationLiteralStep(-1, ">");
		private final @NonNull RTSerializationLiteralStep _016 // 1*'?'
									= new RTSerializationLiteralStep(-1, "?");
		private final @NonNull RTSerializationLiteralStep _017 // 1*'@'
									= new RTSerializationLiteralStep(-1, "@");
		private final @NonNull RTSerializationLiteralStep _018 // 1*'Lambda'
									= new RTSerializationLiteralStep(-1, "Lambda");
		private final @NonNull RTSerializationLiteralStep _019 // 1*'Map'
									= new RTSerializationLiteralStep(-1, "Map");
		private final @NonNull RTSerializationLiteralStep _020 // 1*'Tuple'
									= new RTSerializationLiteralStep(-1, "Tuple");
		private final @NonNull RTSerializationLiteralStep _021 // 1*'['
									= new RTSerializationLiteralStep(-1, "[");
		private final @NonNull RTSerializationLiteralStep _022 // 1*']'
									= new RTSerializationLiteralStep(-1, "]");
		private final @NonNull RTSerializationLiteralStep _023 // 1*'annotation'
									= new RTSerializationLiteralStep(-1, "annotation");
		private final @NonNull RTSerializationLiteralStep _024 // 1*'attribute'
									= new RTSerializationLiteralStep(-1, "attribute");
		private final @NonNull RTSerializationLiteralStep _025 // 1*'body'
									= new RTSerializationLiteralStep(-1, "body");
		private final @NonNull RTSerializationLiteralStep _026 // 1*'class'
									= new RTSerializationLiteralStep(-1, "class");
		private final @NonNull RTSerializationLiteralStep _027 // 1*'datatype'
									= new RTSerializationLiteralStep(-1, "datatype");
		private final @NonNull RTSerializationLiteralStep _028 // 1*'definition'
									= new RTSerializationLiteralStep(-1, "definition");
		private final @NonNull RTSerializationLiteralStep _029 // 1*'derivation'
									= new RTSerializationLiteralStep(-1, "derivation");
		private final @NonNull RTSerializationLiteralStep _030 // 1*'documentation'
									= new RTSerializationLiteralStep(-1, "documentation");
		private final @NonNull RTSerializationLiteralStep _031 // 1*'else'
									= new RTSerializationLiteralStep(-1, "else");
		private final @NonNull RTSerializationLiteralStep _032 // 1*'elseif'
									= new RTSerializationLiteralStep(-1, "elseif");
		private final @NonNull RTSerializationLiteralStep _033 // 1*'endif'
									= new RTSerializationLiteralStep(-1, "endif");
		private final @NonNull RTSerializationLiteralStep _034 // 1*'enum'
									= new RTSerializationLiteralStep(-1, "enum");
		private final @NonNull RTSerializationLiteralStep _035 // 1*'extends'
									= new RTSerializationLiteralStep(-1, "extends");
		private final @NonNull RTSerializationLiteralStep _036 // 1*'if'
									= new RTSerializationLiteralStep(-1, "if");
		private final @NonNull RTSerializationLiteralStep _037 // 1*'import'
									= new RTSerializationLiteralStep(-1, "import");
		private final @NonNull RTSerializationLiteralStep _038 // 1*'in'
									= new RTSerializationLiteralStep(-1, "in");
		private final @NonNull RTSerializationLiteralStep _039 // 1*'initial'
									= new RTSerializationLiteralStep(-1, "initial");
		private final @NonNull RTSerializationLiteralStep _040 // 1*'invalid'
									= new RTSerializationLiteralStep(-1, "invalid");
		private final @NonNull RTSerializationLiteralStep _041 // 1*'invariant'
									= new RTSerializationLiteralStep(-1, "invariant");
		private final @NonNull RTSerializationLiteralStep _042 // 1*'key'
									= new RTSerializationLiteralStep(-1, "key");
		private final @NonNull RTSerializationLiteralStep _043 // 1*'let'
									= new RTSerializationLiteralStep(-1, "let");
		private final @NonNull RTSerializationLiteralStep _044 // 1*'literal'
									= new RTSerializationLiteralStep(-1, "literal");
		private final @NonNull RTSerializationLiteralStep _045 // 1*'module'
									= new RTSerializationLiteralStep(-1, "module");
		private final @NonNull RTSerializationLiteralStep _046 // 1*'null'
									= new RTSerializationLiteralStep(-1, "null");
		private final @NonNull RTSerializationLiteralStep _047 // 1*'operation'
									= new RTSerializationLiteralStep(-1, "operation");
		private final @NonNull RTSerializationLiteralStep _048 // 1*'opposite'
									= new RTSerializationLiteralStep(-1, "opposite");
		private final @NonNull RTSerializationLiteralStep _049 // 1*'package'
									= new RTSerializationLiteralStep(-1, "package");
		private final @NonNull RTSerializationLiteralStep _050 // 1*'postcondition'
									= new RTSerializationLiteralStep(-1, "postcondition");
		private final @NonNull RTSerializationLiteralStep _051 // 1*'pre'
									= new RTSerializationLiteralStep(-1, "pre");
		private final @NonNull RTSerializationLiteralStep _052 // 1*'precondition'
									= new RTSerializationLiteralStep(-1, "precondition");
		private final @NonNull RTSerializationLiteralStep _053 // 1*'property'
									= new RTSerializationLiteralStep(-1, "property");
		private final @NonNull RTSerializationLiteralStep _054 // 1*'reference'
									= new RTSerializationLiteralStep(-1, "reference");
		private final @NonNull RTSerializationLiteralStep _055 // 1*'self'
									= new RTSerializationLiteralStep(-1, "self");
		private final @NonNull RTSerializationLiteralStep _056 // 1*'static'
									= new RTSerializationLiteralStep(-1, "static");
		private final @NonNull RTSerializationLiteralStep _057 // 1*'sysml'
									= new RTSerializationLiteralStep(-1, "sysml");
		private final @NonNull RTSerializationLiteralStep _058 // 1*'then'
									= new RTSerializationLiteralStep(-1, "then");
		private final @NonNull RTSerializationLiteralStep _059 // 1*'throws'
									= new RTSerializationLiteralStep(-1, "throws");
		private final @NonNull RTSerializationLiteralStep _060 // 1*'{'
									= new RTSerializationLiteralStep(-1, "{");
		private final @NonNull RTSerializationLiteralStep _061 // 1*'|'
									= new RTSerializationLiteralStep(-1, "|");
		private final @NonNull RTSerializationLiteralStep _062 // 1*'|?'
									= new RTSerializationLiteralStep(-1, "|?");
		private final @NonNull RTSerializationLiteralStep _063 // 1*'}'
									= new RTSerializationLiteralStep(-1, "}");
		private final @NonNull RTSerializationAssignedRuleCallStep _064 // 1*default=SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
		private final @NonNull RTSerializationAssignedRuleCallStep _065 // 1*exprString=UNQUOTED_STRING
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		private final @NonNull RTSerializationAssignedRuleCallStep _066 // 1*instanceClassName=SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _067 // 1*literal=SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL);
		private final @NonNull RTSerializationAssignedRuleCallStep _068 // 1*lowerBound=LOWER
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull RTSerializationAssignedRuleCallStep _069 // 1*name=BinaryOperatorName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _070 // 1*name=CollectionTypeIdentifier
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _071 // 1*name=EnumerationLiteralName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _072 // 1*name=PrimitiveTypeIdentifier
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _073 // 1*name=UnaryOperatorName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _074 // 1*name=UnrestrictedName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallsStep _075 // 1*name=UnrestrictedName|SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallsStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationSequenceStep _076 // 1*next-10-steps
									= new RTSerializationSequenceStep(-1, 1, 11);
		private final @NonNull RTSerializationAssignedRuleCallStep _077 // 1*nsPrefix=UnrestrictedName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
		private final @NonNull RTSerializationAssignedRuleCallStep _078 // 1*nsURI=URI
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
		private final @NonNull RTSerializationAssignedRuleCallStep _079 // 1*ownedActualParameter=TypeRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull RTSerializationAssignedRuleCallStep _080 // 1*ownedArguments+=NavigatingArgCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _081 // 1*ownedBinding=TemplateBindingCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull RTSerializationAssignedRuleCallStep _082 // 1*ownedCoIterator=CoIteratorVariableCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		private final @NonNull RTSerializationAssignedRuleCallStep _083 // 1*ownedCondition=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		private final @NonNull RTSerializationAssignedRuleCallsStep _084 // 1*ownedCondition=ExpCS|PatternExpCS
									= new RTSerializationAssignedRuleCallsStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationAssignedRuleCallStep _085 // 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _086 // 1*ownedDetails+=DetailCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
		private final @NonNull RTSerializationAssignedRuleCallStep _087 // 1*ownedElseExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _088 // 1*ownedExceptions+=TypedRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _089 // 1*ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _090 // 1*ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _091 // 1*ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _092 // 1*ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _093 // 1*ownedExpression=PatternExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _094 // 1*ownedExpressionCS=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		private final @NonNull RTSerializationAssignedRuleCallStep _095 // 1*ownedExtends+=TypedRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull RTSerializationAssignedRuleCallStep _096 // 1*ownedExtends=TypedRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull RTSerializationAssignedRuleCallStep _097 // 1*ownedImplicitOpposites+=ImplicitOppositeCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES);
		private final @NonNull RTSerializationAssignedRuleCallStep _098 // 1*ownedInExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _099 // 1*ownedInitExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _100 // 1*ownedInitExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallsStep _101 // 1*ownedInitExpression=ExpCS|PatternExpCS
									= new RTSerializationAssignedRuleCallsStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationAssignedRuleCallStep _102 // 1*ownedInitExpression=StringLiteralExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _103 // 1*ownedKey=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		private final @NonNull RTSerializationAssignedRuleCallStep _104 // 1*ownedKeyType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _105 // 1*ownedLastExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _106 // 1*ownedLeft=PrefixedPrimaryExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		private final @NonNull RTSerializationAssignedRuleCallStep _107 // 1*ownedMessageSpecification=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		private final @NonNull RTSerializationAssignedRuleCallStep _108 // 1*ownedNameExpression=NavigatingArgExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _109 // 1*ownedParameters+=ParameterCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
		private final @NonNull RTSerializationAssignedRuleCallStep _110 // 1*ownedParameters+=TypeParameterCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull RTSerializationAssignedRuleCallStep _111 // 1*ownedParts+=CollectionLiteralPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _112 // 1*ownedParts+=MapLiteralPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _113 // 1*ownedParts+=PatternExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _114 // 1*ownedParts+=ShadowPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _115 // 1*ownedParts+=TupleLiteralPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _116 // 1*ownedParts+=TuplePartCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _117 // 1*ownedPathElements+=FirstPathElementCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _118 // 1*ownedPathElements+=NextPathElementCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _119 // 1*ownedPathElements+=URIFirstPathElementCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _120 // 1*ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _121 // 1*ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _122 // 1*ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _123 // 1*ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _124 // 1*ownedPathName=URIPathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _125 // 1*ownedPatternGuard=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		private final @NonNull RTSerializationAssignedRuleCallStep _126 // 1*ownedPatternType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _127 // 1*ownedRight=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull RTSerializationAssignedRuleCallStep _128 // 1*ownedRight=PrefixedLetExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull RTSerializationAssignedRuleCallStep _129 // 1*ownedRight=PrefixedPrimaryExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull RTSerializationAssignedRuleCallStep _130 // 1*ownedSubstitutions+=TemplateParameterSubstitutionCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _131 // 1*ownedSuperTypes+=TypedRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
		private final @NonNull RTSerializationAssignedRuleCallStep _132 // 1*ownedTerms+=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		private final @NonNull RTSerializationAssignedRuleCallStep _133 // 1*ownedThenExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _134 // 1*ownedThenExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _135 // 1*ownedType=CollectionTypeCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _136 // 1*ownedType=CollectionTypeCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _137 // 1*ownedType=MapTypeCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _138 // 1*ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _139 // 1*ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _140 // 1*ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _141 // 1*ownedType=TypeExpWithoutMultiplicityCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _142 // 1*ownedType=TypeLiteralWithMultiplicityCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _143 // 1*ownedType=TypedMultiplicityRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _144 // 1*ownedValue=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		private final @NonNull RTSerializationAssignedRuleCallStep _145 // 1*ownedValueType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _146 // 1*ownedVariables+=LetVariableCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		private final @NonNull RTSerializationAssignStep _147 // 1*qualifiers
									= new RTSerializationAssignStep(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS);
		private final @NonNull RTSerializationCrossReferenceStep _148 // 1*referredElement=URI
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "rv._121");
		private final @NonNull RTSerializationCrossReferenceStep _149 // 1*referredElement=UnreservedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "rv._126");
		private final @NonNull RTSerializationCrossReferenceStep _150 // 1*referredElement=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "rv._127");
		private final @NonNull RTSerializationCrossReferenceStep _151 // 1*referredElement=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "rv._127");
		private final @NonNull RTSerializationCrossReferenceStep _152 // 1*referredKeys+=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, "rv._127");
		private final @NonNull RTSerializationCrossReferenceStep _153 // 1*referredKeys+=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, "rv._127");
		private final @NonNull RTSerializationCrossReferenceStep _154 // 1*referredOpposite=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, "rv._127");
		private final @NonNull RTSerializationCrossReferenceStep _155 // 1*referredProperty=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "rv._127");
		private final @NonNull RTSerializationAssignedRuleCallStep _156 // 1*restVariableName=Identifier
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		private final @NonNull RTSerializationAssignStep _157 // 1*stringBounds
									= new RTSerializationAssignStep(-1, BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS);
		private final @NonNull RTSerializationAssignStep _158 // 1*symbol
									= new RTSerializationAssignStep(-1, EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull RTSerializationAssignedRuleCallStep _159 // 1*symbol=NUMBER_LITERAL
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull RTSerializationAssignedRuleCallStep _160 // 1*upperBound=UPPER
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull RTSerializationAssignedRuleCallStep _161 // 1*value=SIGNED
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE);
		private final @NonNull RTSerializationLiteralStep _162 // V00*'abstract'
									= new RTSerializationLiteralStep(0, "abstract");
		private final @NonNull RTSerializationLiteralStep _163 // V00*'callable'
									= new RTSerializationLiteralStep(0, "callable");
		private final @NonNull RTSerializationLiteralStep _164 // V00*'definition'
									= new RTSerializationLiteralStep(0, "definition");
		private final @NonNull RTSerializationLiteralStep _165 // V00*'primitive'
									= new RTSerializationLiteralStep(0, "primitive");
		private final @NonNull RTSerializationLiteralStep _166 // V00*'static'
									= new RTSerializationLiteralStep(0, "static");
		private final @NonNull RTSerializationLiteralStep _167 // V00*'|1'
									= new RTSerializationLiteralStep(0, "|1");
		private final @NonNull RTSerializationAssignedRuleCallsStep _168 // V00*name=UnrestrictedName|SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallsStep(0, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationSequenceStep _169 // V00*next-2-steps
									= new RTSerializationSequenceStep(0, 4, 6);
		private final @NonNull RTSerializationAssignedRuleCallStep _170 // V00*ownedIfThenExpressions+=ElseIfThenExpCS
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _171 // V00*ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationAssignedRuleCallStep _172 // V00*ownedRoundBracketedClause=RoundBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _173 // V00*ownedSignature=TemplateSignatureCS
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		private final @NonNull RTSerializationAssignedRuleCallStep _174 // V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		private final @NonNull RTSerializationAssignedRuleCallStep _175 // V00*patternVariableName=UnrestrictedName
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _176 // V00*segments+=StringLiteral
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _177 // V00*value=SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
		private final @NonNull RTSerializationAssignedRuleCallsStep _178 // V00*values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallsStep(0, BaseCSPackage.Literals.DETAIL_CS__VALUES,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationLiteralStep _179 // V01*'::*'
									= new RTSerializationLiteralStep(1, "::*");
		private final @NonNull RTSerializationLiteralStep _180 // V01*'|1'
									= new RTSerializationLiteralStep(1, "|1");
		private final @NonNull RTSerializationSequenceStep _181 // V01*next-6-steps
									= new RTSerializationSequenceStep(1, 4, 10);
		private final @NonNull RTSerializationAssignedRuleCallsStep _182 // V01*ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS
									= new RTSerializationAssignedRuleCallsStep(1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
				 new @NonNull AbstractRuleValue [3]);
		private final @NonNull RTSerializationAssignedRuleCallStep _183 // V01*ownedCollectionMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		private final @NonNull RTSerializationAssignedRuleCallStep _184 // V01*ownedImports+=ImportCS
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _185 // V01*ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationAssignedRuleCallStep _186 // V01*ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationAssignedRuleCallStep _187 // V01*ownedRoundBracketedClause=RoundBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _188 // V01*ownedSignature=TemplateSignatureCS
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		private final @NonNull RTSerializationSequenceStep _189 // V02*next-2-steps
									= new RTSerializationSequenceStep(2, 7, 9);
		private final @NonNull RTSerializationAssignedRuleCallStep _190 // V02*ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _191 // V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(2, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _192 // V02*ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationAssignedRuleCallStep _193 // V02*ownedPackages+=PackageCS
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		private final @NonNull RTSerializationAssignedRuleCallStep _194 // V02*ownedSpecification=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		private final @NonNull RTSerializationLiteralStep _195 // V03*'serializable'
									= new RTSerializationLiteralStep(3, "serializable");
		private final @NonNull RTSerializationSequenceStep _196 // V03*next-1-steps
									= new RTSerializationSequenceStep(3, 12, 13);
		private final @NonNull RTSerializationAssignedRuleCallStep _197 // V03*ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _198 // V03*ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationAssignedRuleCallStep _199 // V03*ownedPackages+=PackageCS
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		private final @NonNull RTSerializationAssignedRuleCallStep _200 // V03*ownedSpecification=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		private final @NonNull RTSerializationLiteralStep _201 // V04*'serializable'
									= new RTSerializationLiteralStep(4, "serializable");
		private final @NonNull RTSerializationSequenceStep _202 // V04*next-1-steps
									= new RTSerializationSequenceStep(4, 14, 15);
		private final @NonNull RTSerializationAssignedRuleCallStep _203 // V04*ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _204 // V04*ownedClasses+=ClassCS
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
		private final @NonNull RTSerializationAssignedRuleCallStep _205 // V04*ownedContents+=ModelElementCS
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _206 // V04*ownedLiterals+=EnumerationLiteralCS
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
		private final @NonNull RTSerializationSequenceStep _207 // V05*next-4-steps
									= new RTSerializationSequenceStep(5, 17, 21);
		private final @NonNull RTSerializationAssignedRuleCallStep _208 // V05*ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _209 // V05*ownedConstraints+=InvariantConstraintCS
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _210 // V05*ownedLiterals+=EnumerationLiteralCS
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
		private final @NonNull RTSerializationAssignedRuleCallStep _211 // V05*ownedReferences+=ModelElementRefCS
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES);
		private final @NonNull RTSerializationLiteralStep _212 // V06*'interface'
									= new RTSerializationLiteralStep(6, "interface");
		private final @NonNull RTSerializationSequenceStep _213 // V06*next-4-steps
									= new RTSerializationSequenceStep(6, 19, 23);
		private final @NonNull RTSerializationAssignedRuleCallStep _214 // V06*ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(6, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _215 // V06*ownedConstraints+=InvariantConstraintCS
									= new RTSerializationAssignedRuleCallStep(6, BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _216 // V06*ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(6, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationSequenceStep _217 // V07*next-4-steps
									= new RTSerializationSequenceStep(7, 22, 26);
		private final @NonNull RTSerializationAssignedRuleCallStep _218 // V07*ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(7, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _219 // V07*ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(7, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationSequenceStep _220 // V08*next-4-steps
									= new RTSerializationSequenceStep(8, 24, 28);
		private final @NonNull RTSerializationAssignedRuleCallStep _221 // V08*ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(8, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _222 // V08*ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(8, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _223 // V08*ownedOperations+=OperationCS
									= new RTSerializationAssignedRuleCallStep(8, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
		private final @NonNull RTSerializationSequenceStep _224 // V09*next-4-steps
									= new RTSerializationSequenceStep(9, 29, 33);
		private final @NonNull RTSerializationAssignedRuleCallStep _225 // V09*ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(9, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _226 // V09*ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(9, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _227 // V09*ownedPreconditions+=PreconditionConstraintCS
									= new RTSerializationAssignedRuleCallStep(9, BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _228 // V09*ownedProperties+=StructuralFeatureCS
									= new RTSerializationAssignedRuleCallStep(9, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
		private final @NonNull RTSerializationSequenceStep _229 // V10*next-4-steps
									= new RTSerializationSequenceStep(10, 29, 33);
		private final @NonNull RTSerializationAssignedRuleCallStep _230 // V10*ownedConstraints+=InvariantConstraintCS
									= new RTSerializationAssignedRuleCallStep(10, BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _231 // V10*ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(10, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _232 // V10*ownedPreconditions+=PreconditionConstraintCS
									= new RTSerializationAssignedRuleCallStep(10, BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		private final @NonNull RTSerializationSequenceStep _233 // V11*next-4-steps
									= new RTSerializationSequenceStep(11, 31, 35);
		private final @NonNull RTSerializationAssignedRuleCallStep _234 // V11*ownedBodyExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(11, BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _235 // V11*ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(11, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationSequenceStep _236 // V12*next-2-steps
									= new RTSerializationSequenceStep(12, 37, 39);
		private final @NonNull RTSerializationAssignedRuleCallStep _237 // V12*ownedBodyExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(12, BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _238 // V12*ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(12, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _239 // V12*ownedPostconditions+=PostconditionConstraintCS
									= new RTSerializationAssignedRuleCallStep(12, BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
		private final @NonNull RTSerializationSequenceStep _240 // V13*next-2-steps
									= new RTSerializationSequenceStep(13, 39, 41);
		private final @NonNull RTSerializationAssignedRuleCallStep _241 // V13*ownedPostconditions+=PostconditionConstraintCS
									= new RTSerializationAssignedRuleCallStep(13, BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);

		/**
		 * Post constructor initialization that avoids recursions.
		 */
		private final void init() {
			_064.init(rv._087);
			_065.init(rv._119);
			_066.init(rv._087);
			_067.init(rv._087);
			_068.init(rv._043);
			_069.init(rv._004);
			_070.init(rv._012);
			_071.init(rv._023);
			_072.init(rv._082);
			_073.init(rv._124);
			_074.init(rv._127);
			_075.init(new @NonNull AbstractRuleValue [] {rv._127/*UnrestrictedName*/, rv._087/*SINGLE_QUOTED_STRING*/});
			_077.init(rv._127);
			_078.init(rv._121);
			_079.init(rv._115);
			_080.init(rv._060);
			_081.init(rv._099);
			_082.init(rv._007);
			_083.init(rv._030);
			_084.init(new @NonNull AbstractRuleValue [] {rv._030/*ExpCS*/, rv._074/*PatternExpCS*/});
			_085.init(rv._013);
			_086.init(rv._016);
			_087.init(rv._030);
			_088.init(rv._117);
			_089.init(rv._030);
			_090.init(rv._030);
			_091.init(rv._030);
			_092.init(rv._030);
			_093.init(rv._074);
			_094.init(rv._030);
			_095.init(rv._117);
			_096.init(rv._117);
			_097.init(rv._037);
			_098.init(rv._030);
			_099.init(rv._030);
			_100.init(rv._030);
			_101.init(new @NonNull AbstractRuleValue [] {rv._030/*ExpCS*/, rv._074/*PatternExpCS*/});
			_102.init(rv._095);
			_103.init(rv._030);
			_104.init(rv._107);
			_105.init(rv._030);
			_106.init(rv._078);
			_107.init(rv._092);
			_108.init(rv._061);
			_109.init(rv._072);
			_110.init(rv._114);
			_111.init(rv._009);
			_112.init(rv._050);
			_113.init(rv._074);
			_114.init(rv._090);
			_115.init(rv._104);
			_116.init(rv._105);
			_117.init(rv._031);
			_118.init(rv._067);
			_119.init(rv._122);
			_120.init(rv._073);
			_121.init(rv._073);
			_122.init(rv._073);
			_123.init(rv._073);
			_124.init(rv._123);
			_125.init(rv._030);
			_126.init(rv._107);
			_127.init(rv._030);
			_128.init(rv._077);
			_129.init(rv._078);
			_130.init(rv._100);
			_131.init(rv._117);
			_132.init(rv._030);
			_133.init(rv._030);
			_134.init(rv._030);
			_135.init(rv._011);
			_136.init(rv._011);
			_137.init(rv._051);
			_138.init(rv._107);
			_139.init(rv._107);
			_140.init(rv._107);
			_141.init(rv._108);
			_142.init(rv._112);
			_143.init(rv._116);
			_144.init(rv._030);
			_145.init(rv._107);
			_146.init(rv._046);
			_156.init(rv._035);
			_159.init(rv._058);
			_160.init(rv._120);
			_161.init(rv._085);
			_168.init(new @NonNull AbstractRuleValue [] {rv._127/*UnrestrictedName*/, rv._087/*SINGLE_QUOTED_STRING*/});
			_170.init(rv._020);
			_171.init(rv._056);
			_172.init(rv._084);
			_173.init(rv._101);
			_174.init(rv._093);
			_175.init(rv._127);
			_176.init(rv._094);
			_177.init(rv._087);
			_178.init(new @NonNull AbstractRuleValue [] {rv._087/*SINGLE_QUOTED_STRING*/, rv._048/*ML_SINGLE_QUOTED_STRING*/});
			_182.init(new @NonNull AbstractRuleValue [] {rv._063/*NavigatingCommaArgCS*/, rv._064/*NavigatingSemiArgCS*/, rv._062/*NavigatingBarArgCS*/});
			_183.init(rv._056);
			_184.init(rv._038);
			_185.init(rv._056);
			_186.init(rv._056);
			_187.init(rv._084);
			_188.init(rv._101);
			_190.init(rv._002);
			_191.init(rv._013);
			_192.init(rv._056);
			_193.init(rv._071);
			_194.init(rv._092);
			_197.init(rv._002);
			_198.init(rv._056);
			_199.init(rv._071);
			_200.init(rv._092);
			_203.init(rv._002);
			_204.init(rv._006);
			_205.init(rv._053);
			_206.init(rv._022);
			_208.init(rv._002);
			_209.init(rv._041);
			_210.init(rv._022);
			_211.init(rv._054);
			_214.init(rv._002);
			_215.init(rv._041);
			_216.init(rv._092);
			_218.init(rv._002);
			_219.init(rv._092);
			_221.init(rv._002);
			_222.init(rv._092);
			_223.init(rv._070);
			_225.init(rv._002);
			_226.init(rv._092);
			_227.init(rv._076);
			_228.init(rv._096);
			_230.init(rv._041);
			_231.init(rv._092);
			_232.init(rv._076);
			_234.init(rv._092);
			_235.init(rv._092);
			_237.init(rv._092);
			_238.init(rv._092);
			_239.init(rv._075);
			_241.init(rv._075);
		}
	}

	/**
	 * The various string segment sequences that may be used to serialize a serialization term.
	 */
	private class _SerializationSegments
	{
		private final @NonNull Segment [] _0 = new @NonNull Segment @NonNull [] {
		};
		private final @NonNull Segment [] _1 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.createCustomSegment(null, BaseCommentSegmentSupport.class) /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport */,
			IdiomsUtils.VALUE /* «value» */
		};
		private final @NonNull Segment [] _2 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.NO_SPACE /* «! » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.NO_SPACE /* «! » */
		};
		private final @NonNull Segment [] _3 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.NO_SPACE /* «! » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.SOFT_SPACE /* «? » */
		};
		private final @NonNull Segment [] _4 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.NO_SPACE /* «! » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.SOFT_NEW_LINE /* «?\n» */
		};
		private final @NonNull Segment [] _5 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.NO_SPACE /* «! » */,
			IdiomsUtils.VALUE /* «value» */
		};
		private final @NonNull Segment [] _6 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.POP /* «-» */,
			IdiomsUtils.SOFT_SPACE /* «? » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.SOFT_NEW_LINE /* «?\n» */
		};
		private final @NonNull Segment [] _7 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.SOFT_SPACE /* «? » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.PUSH /* «+» */,
			IdiomsUtils.SOFT_NEW_LINE /* «?\n» */
		};
		private final @NonNull Segment [] _8 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.SOFT_SPACE /* «? » */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.SOFT_SPACE /* «? » */
		};
	}

	/**
	 * The various serialization rules for each grammar rule.
	 */
	private class _RuleValues
	{
		private final @NonNull TerminalRuleValue _000 // ANY_OTHER
			= new TerminalRuleValue(0, "ANY_OTHER");
		private final @NonNull ParserRuleValue _001 // AnnotationCS
			= new ParserRuleValue(1, "AnnotationCS",
				new @NonNull SerializationRule [] {
					sr1._118 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._120 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
					sr1._119 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._117 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _002 // AnnotationElementCS
			= new ParserRuleValue(2, "AnnotationElementCS",
				new @NonNull SerializationRule [] {
					sr3._201 /* { 'sysml' ownedDetails+=DetailCS ';' } */,
					sr3._200 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */,
					sr1._118 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr2._140 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._120 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
					sr1._119 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._117 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				},
				iv._43); /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
		private final @NonNull ParserRuleValue _003 // AttributeCS
			= new ParserRuleValue(3, "AttributeCS",
				new @NonNull SerializationRule [] {
					sr1._121 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._122 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._126 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._124 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._123 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._125 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _004 // BinaryOperatorName
			= new DataTypeRuleValue(4, "BinaryOperatorName");
		private final @NonNull ParserRuleValue _005 // BooleanLiteralExpCS
			= new ParserRuleValue(5, "BooleanLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._016 /* symbol={'false|true'} */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _006 // ClassCS
			= new ParserRuleValue(6, "ClassCS",
				new @NonNull SerializationRule [] {
					sr2._152 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr2._137 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr2._149 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr2._144 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr2._130 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr2._129 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr2._136 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._153 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._131 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._132 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._142 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._146 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr3._199 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr3._198 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				iv._41); /* ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS */
		private final @NonNull ParserRuleValue _007 // CoIteratorVariableCS
			= new ParserRuleValue(7, "CoIteratorVariableCS",
				new @NonNull SerializationRule [] {
					sr0._017 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _008 // CollectionLiteralExpCS
			= new ParserRuleValue(8, "CollectionLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _009 // CollectionLiteralPartCS
			= new ParserRuleValue(9, "CollectionLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._019 /* ownedExpression=PatternExpCS */,
					sr0._020 /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _010 // CollectionPatternCS
			= new ParserRuleValue(10, "CollectionPatternCS",
				new @NonNull SerializationRule [] {
					sr0._021 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _011 // CollectionTypeCS
			= new ParserRuleValue(11, "CollectionTypeCS",
				new @NonNull SerializationRule [] {
					sr0._023 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _012 // CollectionTypeIdentifier
			= new DataTypeRuleValue(12, "CollectionTypeIdentifier");
		private final @NonNull ParserRuleValue _013 // CurlyBracketedClauseCS
			= new ParserRuleValue(13, "CurlyBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr0._024 /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _014 // DOUBLE_QUOTED_STRING
			= new TerminalRuleValue(14, "DOUBLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _015 // DataTypeCS
			= new ParserRuleValue(15, "DataTypeCS",
				new @NonNull SerializationRule [] {
					sr1._127 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr2._133 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr2._128 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr2._138 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._134 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._135 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _016 // DetailCS
			= new ParserRuleValue(16, "DetailCS",
				new @NonNull SerializationRule [] {
					sr2._139 /* { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _017 // DocumentationCS
			= new ParserRuleValue(17, "DocumentationCS",
				new @NonNull SerializationRule [] {
					sr2._141 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _018 // ESCAPED_CHARACTER
			= new TerminalRuleValue(18, "ESCAPED_CHARACTER");
		private final @NonNull TerminalRuleValue _019 // ESCAPED_ID
			= new TerminalRuleValue(19, "ESCAPED_ID");
		private final @NonNull ParserRuleValue _020 // ElseIfThenExpCS
			= new ParserRuleValue(20, "ElseIfThenExpCS",
				new @NonNull SerializationRule [] {
					sr0._025 /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _021 // EnumerationCS
			= new ParserRuleValue(21, "EnumerationCS",
				new @NonNull SerializationRule [] {
					sr2._147 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr2._151 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr2._148 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr2._143 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._145 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._150 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _022 // EnumerationLiteralCS
			= new ParserRuleValue(22, "EnumerationLiteralCS",
				new @NonNull SerializationRule [] {
					sr2._156 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr2._155 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr2._157 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr2._154 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _023 // EnumerationLiteralName
			= new DataTypeRuleValue(23, "EnumerationLiteralName");
		private final @NonNull DataTypeRuleValue _024 // EssentialOCLInfixOperatorName
			= new DataTypeRuleValue(24, "EssentialOCLInfixOperatorName");
		private final @NonNull DataTypeRuleValue _025 // EssentialOCLNavigationOperatorName
			= new DataTypeRuleValue(25, "EssentialOCLNavigationOperatorName");
		private final @NonNull DataTypeRuleValue _026 // EssentialOCLReservedKeyword
			= new DataTypeRuleValue(26, "EssentialOCLReservedKeyword");
		private final @NonNull DataTypeRuleValue _027 // EssentialOCLUnaryOperatorName
			= new DataTypeRuleValue(27, "EssentialOCLUnaryOperatorName");
		private final @NonNull DataTypeRuleValue _028 // EssentialOCLUnreservedName
			= new DataTypeRuleValue(28, "EssentialOCLUnreservedName");
		private final @NonNull DataTypeRuleValue _029 // EssentialOCLUnrestrictedName
			= new DataTypeRuleValue(29, "EssentialOCLUnrestrictedName");
		private final @NonNull ParserRuleValue _030 // ExpCS
			= new ParserRuleValue(30, "ExpCS",
				new @NonNull SerializationRule [] {
					sr0._029 /* symbol={'false|true'} */,
					sr0._038 /* '*' */,
					sr0._030 /* 'invalid' */,
					sr0._035 /* 'null' */,
					sr0._027 /* 'self' */,
					sr0._026 /* symbol=NUMBER_LITERAL */,
					sr0._028 /* segments+=StringLiteral[+] */,
					sr0._036 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._041 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr1._078 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					sr0._031 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._034 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._033 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._032 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._042 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._037 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._050 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr0._039 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._040 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				iv._69); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _031 // FirstPathElementCS
			= new ParserRuleValue(31, "FirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._000 /* referredElement=UnrestrictedName */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _032 // ID
			= new DataTypeRuleValue(32, "ID");
		private final @NonNull TerminalRuleValue _033 // INT
			= new TerminalRuleValue(33, "INT");
		private final @NonNull DataTypeRuleValue _034 // INTEGER
			= new DataTypeRuleValue(34, "INTEGER");
		private final @NonNull DataTypeRuleValue _035 // Identifier
			= new DataTypeRuleValue(35, "Identifier");
		private final @NonNull ParserRuleValue _036 // IfExpCS
			= new ParserRuleValue(36, "IfExpCS",
				new @NonNull SerializationRule [] {
					sr0._044 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _037 // ImplicitOppositeCS
			= new ParserRuleValue(37, "ImplicitOppositeCS",
				new @NonNull SerializationRule [] {
					sr2._158 /* { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _038 // ImportCS
			= new ParserRuleValue(38, "ImportCS",
				new @NonNull SerializationRule [] {
					sr2._159 /* { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _039 // InfixOperatorName
			= new DataTypeRuleValue(39, "InfixOperatorName");
		private final @NonNull ParserRuleValue _040 // InvalidLiteralExpCS
			= new ParserRuleValue(40, "InvalidLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._046 /* 'invalid' */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _041 // InvariantConstraintCS
			= new ParserRuleValue(41, "InvariantConstraintCS",
				new @NonNull SerializationRule [] {
					sr2._160 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' } */,
					sr2._161 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _042 // LETTER_CHARACTER
			= new TerminalRuleValue(42, "LETTER_CHARACTER");
		private final @NonNull DataTypeRuleValue _043 // LOWER
			= new DataTypeRuleValue(43, "LOWER");
		private final @NonNull ParserRuleValue _044 // LambdaLiteralExpCS
			= new ParserRuleValue(44, "LambdaLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._048 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _045 // LetExpCS
			= new ParserRuleValue(45, "LetExpCS",
				new @NonNull SerializationRule [] {
					sr0._049 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _046 // LetVariableCS
			= new ParserRuleValue(46, "LetVariableCS",
				new @NonNull SerializationRule [] {
					sr0._051 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _047 // ML_COMMENT
			= new TerminalRuleValue(47, "ML_COMMENT");
		private final @NonNull TerminalRuleValue _048 // ML_SINGLE_QUOTED_STRING
			= new TerminalRuleValue(48, "ML_SINGLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _049 // MapLiteralExpCS
			= new ParserRuleValue(49, "MapLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._053 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _050 // MapLiteralPartCS
			= new ParserRuleValue(50, "MapLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._054 /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _051 // MapTypeCS
			= new ParserRuleValue(51, "MapTypeCS",
				new @NonNull SerializationRule [] {
					sr0._055 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _052 // Model
			= new ParserRuleValue(52, "Model",
				new @NonNull SerializationRule [] {
					sr0._057 /* ownedExpression=ExpCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _053 // ModelElementCS
			= new ParserRuleValue(53, "ModelElementCS",
				new @NonNull SerializationRule [] {
					sr2._156 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr2._155 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr2._177 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
					sr2._152 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr2._157 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr2._137 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr2._154 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr2._149 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr2._144 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr2._130 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr2._129 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr2._178 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */,
					sr2._136 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._153 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._131 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._132 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._142 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._146 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._121 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._122 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._126 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._191 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._187 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._185 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr3._199 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr3._198 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._124 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._123 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._125 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr2._168 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._174 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._172 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._163 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._173 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._164 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._188 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._189 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._190 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
				iv._42); /* AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */
		private final @NonNull ParserRuleValue _054 // ModelElementRefCS
			= new ParserRuleValue(54, "ModelElementRefCS",
				new @NonNull SerializationRule [] {
					sr2._162 /* { 'reference' ownedPathName=PathNameCS ';' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _055 // MultiplicityBoundsCS
			= new ParserRuleValue(55, "MultiplicityBoundsCS",
				new @NonNull SerializationRule [] {
					sr0._001 /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _056 // MultiplicityCS
			= new ParserRuleValue(56, "MultiplicityCS",
				new @NonNull SerializationRule [] {
					sr0._006 /* { '[' stringBounds={'*|+|?'} ']' } */,
					sr0._007 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					sr0._004 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
					sr0._005 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					sr0._002 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					sr0._003 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _057 // MultiplicityStringCS
			= new ParserRuleValue(57, "MultiplicityStringCS",
				new @NonNull SerializationRule [] {
					sr0._008 /* stringBounds={'*|+|?'} */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _058 // NUMBER_LITERAL
			= new DataTypeRuleValue(58, "NUMBER_LITERAL");
		private final @NonNull ParserRuleValue _059 // NameExpCS
			= new ParserRuleValue(59, "NameExpCS",
				new @NonNull SerializationRule [] {
					sr0._058 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _060 // NavigatingArgCS
			= new ParserRuleValue(60, "NavigatingArgCS",
				new @NonNull SerializationRule [] {
					sr1._064 /* ownedNameExpression=NavigatingArgExpCS */,
					sr0._061 /* { ':' ownedType=TypeExpCS } */,
					sr0._060 /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._062 /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._063 /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _061 // NavigatingArgExpCS
			= new ParserRuleValue(61, "NavigatingArgExpCS",
				new @NonNull SerializationRule [] {
					sr0._029 /* symbol={'false|true'} */,
					sr0._038 /* '*' */,
					sr0._030 /* 'invalid' */,
					sr0._035 /* 'null' */,
					sr0._027 /* 'self' */,
					sr0._026 /* symbol=NUMBER_LITERAL */,
					sr0._028 /* segments+=StringLiteral[+] */,
					sr0._036 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._041 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr1._078 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					sr0._031 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._034 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._033 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._032 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._042 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._037 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._050 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr0._039 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._040 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				iv._70); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _062 // NavigatingBarArgCS
			= new ParserRuleValue(62, "NavigatingBarArgCS",
				new @NonNull SerializationRule [] {
					sr1._065 /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _063 // NavigatingCommaArgCS
			= new ParserRuleValue(63, "NavigatingCommaArgCS",
				new @NonNull SerializationRule [] {
					sr1._067 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					sr1._068 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr1._066 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr1._069 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _064 // NavigatingSemiArgCS
			= new ParserRuleValue(64, "NavigatingSemiArgCS",
				new @NonNull SerializationRule [] {
					sr1._070 /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _065 // NavigationOperatorName
			= new DataTypeRuleValue(65, "NavigationOperatorName");
		private final @NonNull ParserRuleValue _066 // NestedExpCS
			= new ParserRuleValue(66, "NestedExpCS",
				new @NonNull SerializationRule [] {
					sr1._071 /* { '(' ownedExpression=ExpCS ')' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _067 // NextPathElementCS
			= new ParserRuleValue(67, "NextPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._009 /* referredElement=UnreservedName */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _068 // NullLiteralExpCS
			= new ParserRuleValue(68, "NullLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._073 /* 'null' */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _069 // NumberLiteralExpCS
			= new ParserRuleValue(69, "NumberLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._076 /* symbol=NUMBER_LITERAL */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _070 // OperationCS
			= new ParserRuleValue(70, "OperationCS",
				new @NonNull SerializationRule [] {
					sr2._169 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._165 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._166 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._170 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._167 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._171 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _071 // PackageCS
			= new ParserRuleValue(71, "PackageCS",
				new @NonNull SerializationRule [] {
					sr2._176 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
					sr2._175 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _072 // ParameterCS
			= new ParserRuleValue(72, "ParameterCS",
				new @NonNull SerializationRule [] {
					sr2._179 /* { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _073 // PathNameCS
			= new ParserRuleValue(73, "PathNameCS",
				new @NonNull SerializationRule [] {
					sr0._010 /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _074 // PatternExpCS
			= new ParserRuleValue(74, "PatternExpCS",
				new @NonNull SerializationRule [] {
					sr1._077 /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _075 // PostconditionConstraintCS
			= new ParserRuleValue(75, "PostconditionConstraintCS",
				new @NonNull SerializationRule [] {
					sr2._180 /* { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _076 // PreconditionConstraintCS
			= new ParserRuleValue(76, "PreconditionConstraintCS",
				new @NonNull SerializationRule [] {
					sr2._181 /* { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _077 // PrefixedLetExpCS
			= new ParserRuleValue(77, "PrefixedLetExpCS",
				new @NonNull SerializationRule [] {
					sr1._079 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					sr0._049 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				iv._32); /* LetExpCS|PrefixedLetExpCS */
		private final @NonNull ParserRuleValue _078 // PrefixedPrimaryExpCS
			= new ParserRuleValue(78, "PrefixedPrimaryExpCS",
				new @NonNull SerializationRule [] {
					sr0._016 /* symbol={'false|true'} */,
					sr0._045 /* 'invalid' */,
					sr1._074 /* 'null' */,
					sr1._075 /* symbol=NUMBER_LITERAL */,
					sr1._085 /* 'self' */,
					sr1._091 /* segments+=StringLiteral[+] */,
					sr1._105 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._116 /* '*' */,
					sr1._080 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr1._072 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._047 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._043 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._059 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr1._092 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._052 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				iv._67); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _079 // PrimaryExpCS
			= new ParserRuleValue(79, "PrimaryExpCS",
				new @NonNull SerializationRule [] {
					sr0._016 /* symbol={'false|true'} */,
					sr0._045 /* 'invalid' */,
					sr1._074 /* 'null' */,
					sr1._075 /* symbol=NUMBER_LITERAL */,
					sr1._085 /* 'self' */,
					sr1._091 /* segments+=StringLiteral[+] */,
					sr1._105 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._116 /* '*' */,
					sr1._071 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._048 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._044 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._058 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr1._092 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._053 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				iv._66); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _080 // PrimitiveLiteralExpCS
			= new ParserRuleValue(80, "PrimitiveLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._016 /* symbol={'false|true'} */,
					sr0._046 /* 'invalid' */,
					sr1._073 /* 'null' */,
					sr1._076 /* symbol=NUMBER_LITERAL */,
					sr1._091 /* segments+=StringLiteral[+] */,
					sr1._116 /* '*' */
				},
				iv._65); /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _081 // PrimitiveTypeCS
			= new ParserRuleValue(81, "PrimitiveTypeCS",
				new @NonNull SerializationRule [] {
					sr1._081 /* name=PrimitiveTypeIdentifier */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _082 // PrimitiveTypeIdentifier
			= new DataTypeRuleValue(82, "PrimitiveTypeIdentifier");
		private final @NonNull ParserRuleValue _083 // ReferenceCS
			= new ParserRuleValue(83, "ReferenceCS",
				new @NonNull SerializationRule [] {
					sr2._186 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._184 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._182 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr3._193 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._183 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr3._192 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _084 // RoundBracketedClauseCS
			= new ParserRuleValue(84, "RoundBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._083 /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _085 // SIGNED
			= new DataTypeRuleValue(85, "SIGNED");
		private final @NonNull TerminalRuleValue _086 // SIMPLE_ID
			= new TerminalRuleValue(86, "SIMPLE_ID");
		private final @NonNull TerminalRuleValue _087 // SINGLE_QUOTED_STRING
			= new TerminalRuleValue(87, "SINGLE_QUOTED_STRING");
		private final @NonNull TerminalRuleValue _088 // SL_COMMENT
			= new TerminalRuleValue(88, "SL_COMMENT");
		private final @NonNull ParserRuleValue _089 // SelfExpCS
			= new ParserRuleValue(89, "SelfExpCS",
				new @NonNull SerializationRule [] {
					sr1._084 /* 'self' */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _090 // ShadowPartCS
			= new ParserRuleValue(90, "ShadowPartCS",
				new @NonNull SerializationRule [] {
					sr1._087 /* ownedInitExpression=StringLiteralExpCS */,
					sr1._086 /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _091 // SimplePathNameCS
			= new ParserRuleValue(91, "SimplePathNameCS",
				new @NonNull SerializationRule [] {
					sr1._088 /* ownedPathElements+=FirstPathElementCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _092 // SpecificationCS
			= new ParserRuleValue(92, "SpecificationCS",
				new @NonNull SerializationRule [] {
					sr3._195 /* exprString=UNQUOTED_STRING */,
					sr3._194 /* ownedExpression=ExpCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _093 // SquareBracketedClauseCS
			= new ParserRuleValue(93, "SquareBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._089 /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _094 // StringLiteral
			= new DataTypeRuleValue(94, "StringLiteral");
		private final @NonNull ParserRuleValue _095 // StringLiteralExpCS
			= new ParserRuleValue(95, "StringLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._090 /* segments+=StringLiteral[+] */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _096 // StructuralFeatureCS
			= new ParserRuleValue(96, "StructuralFeatureCS",
				new @NonNull SerializationRule [] {
					sr1._121 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._122 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._126 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._186 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._184 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._182 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._124 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._123 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._125 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr3._193 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._183 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr3._192 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
				iv._40); /* AttributeCS|ReferenceCS|StructuralFeatureCS */
		private final @NonNull ParserRuleValue _097 // StructuredClassCS
			= new ParserRuleValue(97, "StructuredClassCS",
				new @NonNull SerializationRule [] {
					sr3._197 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr3._196 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _098 // SysMLCS
			= new ParserRuleValue(98, "SysMLCS",
				new @NonNull SerializationRule [] {
					sr3._203 /* { 'sysml' ownedDetails+=DetailCS ';' } */,
					sr3._202 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _099 // TemplateBindingCS
			= new ParserRuleValue(99, "TemplateBindingCS",
				new @NonNull SerializationRule [] {
					sr0._011 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _100 // TemplateParameterSubstitutionCS
			= new ParserRuleValue(100, "TemplateParameterSubstitutionCS",
				new @NonNull SerializationRule [] {
					sr0._012 /* ownedActualParameter=TypeRefCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _101 // TemplateSignatureCS
			= new ParserRuleValue(101, "TemplateSignatureCS",
				new @NonNull SerializationRule [] {
					sr3._205 /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */,
					sr3._204 /* { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _102 // TopLevelCS
			= new ParserRuleValue(102, "TopLevelCS",
				new @NonNull SerializationRule [] {
					sr3._206 /* { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _103 // TupleLiteralExpCS
			= new ParserRuleValue(103, "TupleLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._093 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _104 // TupleLiteralPartCS
			= new ParserRuleValue(104, "TupleLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr1._094 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _105 // TuplePartCS
			= new ParserRuleValue(105, "TuplePartCS",
				new @NonNull SerializationRule [] {
					sr1._095 /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _106 // TupleTypeCS
			= new ParserRuleValue(106, "TupleTypeCS",
				new @NonNull SerializationRule [] {
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _107 // TypeExpCS
			= new ParserRuleValue(107, "TypeExpCS",
				new @NonNull SerializationRule [] {
					sr1._102 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._100 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._098 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._103 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._101 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._099 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._55); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull ParserRuleValue _108 // TypeExpWithoutMultiplicityCS
			= new ParserRuleValue(108, "TypeExpWithoutMultiplicityCS",
				new @NonNull SerializationRule [] {
					sr1._082 /* name=PrimitiveTypeIdentifier */,
					sr0._022 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._056 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._111 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */,
					sr0._021 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
					sr1._096 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._54); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull DataTypeRuleValue _109 // TypeIdentifier
			= new DataTypeRuleValue(109, "TypeIdentifier");
		private final @NonNull ParserRuleValue _110 // TypeLiteralCS
			= new ParserRuleValue(110, "TypeLiteralCS",
				new @NonNull SerializationRule [] {
					sr1._081 /* name=PrimitiveTypeIdentifier */,
					sr0._023 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._055 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._51); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */
		private final @NonNull ParserRuleValue _111 // TypeLiteralExpCS
			= new ParserRuleValue(111, "TypeLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._104 /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _112 // TypeLiteralWithMultiplicityCS
			= new ParserRuleValue(112, "TypeLiteralWithMultiplicityCS",
				new @NonNull SerializationRule [] {
					sr1._106 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._107 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._109 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._108 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._53); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
		private final @NonNull ParserRuleValue _113 // TypeNameExpCS
			= new ParserRuleValue(113, "TypeNameExpCS",
				new @NonNull SerializationRule [] {
					sr1._110 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _114 // TypeParameterCS
			= new ParserRuleValue(114, "TypeParameterCS",
				new @NonNull SerializationRule [] {
					sr0._013 /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _115 // TypeRefCS
			= new ParserRuleValue(115, "TypeRefCS",
				new @NonNull SerializationRule [] {
					sr1._082 /* name=PrimitiveTypeIdentifier */,
					sr3._218 /* ownedPathName=PathNameCS */,
					sr3._219 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr3._215 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
					sr0._014 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */,
					sr0._022 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._056 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._096 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._72); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
		private final @NonNull ParserRuleValue _116 // TypedMultiplicityRefCS
			= new ParserRuleValue(116, "TypedMultiplicityRefCS",
				new @NonNull SerializationRule [] {
					sr3._212 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr3._208 /* { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */,
					sr3._213 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] } */,
					sr3._209 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] } */,
					sr3._207 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr3._210 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr3._211 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._61); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
		private final @NonNull ParserRuleValue _117 // TypedRefCS
			= new ParserRuleValue(117, "TypedRefCS",
				new @NonNull SerializationRule [] {
					sr1._081 /* name=PrimitiveTypeIdentifier */,
					sr3._218 /* ownedPathName=PathNameCS */,
					sr3._219 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr3._215 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
					sr0._023 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._055 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._60); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
		private final @NonNull ParserRuleValue _118 // TypedTypeRefCS
			= new ParserRuleValue(118, "TypedTypeRefCS",
				new @NonNull SerializationRule [] {
					sr3._217 /* ownedPathName=PathNameCS */,
					sr3._216 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr3._214 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _119 // UNQUOTED_STRING
			= new TerminalRuleValue(119, "UNQUOTED_STRING");
		private final @NonNull DataTypeRuleValue _120 // UPPER
			= new DataTypeRuleValue(120, "UPPER");
		private final @NonNull DataTypeRuleValue _121 // URI
			= new DataTypeRuleValue(121, "URI");
		private final @NonNull ParserRuleValue _122 // URIFirstPathElementCS
			= new ParserRuleValue(122, "URIFirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr1._112 /* referredElement=UnrestrictedName */,
					sr1._113 /* referredElement=URI */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _123 // URIPathNameCS
			= new ParserRuleValue(123, "URIPathNameCS",
				new @NonNull SerializationRule [] {
					sr1._114 /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _124 // UnaryOperatorName
			= new DataTypeRuleValue(124, "UnaryOperatorName");
		private final @NonNull ParserRuleValue _125 // UnlimitedNaturalLiteralExpCS
			= new ParserRuleValue(125, "UnlimitedNaturalLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._115 /* '*' */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _126 // UnreservedName
			= new DataTypeRuleValue(126, "UnreservedName");
		private final @NonNull DataTypeRuleValue _127 // UnrestrictedName
			= new DataTypeRuleValue(127, "UnrestrictedName");
		private final @NonNull TerminalRuleValue _128 // WS
			= new TerminalRuleValue(128, "WS");
		private final @NonNull ParserRuleValue _129 // WildcardTypeRefCS
			= new ParserRuleValue(129, "WildcardTypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._015 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				(IndexVector)null);
	}

	/**
	 * Configuration for each EClass that may be serialized.
	 */
	private class _EClassData
	{
		private final @NonNull EClassData _00 // AnnotationCS
			= new EClassData(BaseCSPackage.Literals.ANNOTATION_CS,
				new @NonNull SerializationRule [] {
					sr1._118 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._120 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
					sr1._119 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._117 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._118 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._120 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
					sr1._119 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._117 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
						iv._42) /* AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */,
					new EReferenceData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
						iv._18) /* ModelElementRefCS */,
					new EReferenceData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						iv._6) /* DetailCS */,
					new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
				}
			);
		private final @NonNull EClassData _01 // AttributeCS
			= new EClassData(BaseCSPackage.Literals.ATTRIBUTE_CS,
				new @NonNull SerializationRule [] {
					sr1._121 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._122 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._126 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._124 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._123 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._125 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._121 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._122 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._126 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._124 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._123 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._125 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._121 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._122 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._126 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._124 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._123 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._125 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReferenceData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						iv._36) /* SpecificationCS */,
					new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassData _02 // BooleanLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._016 /* symbol={'false|true'} */,
					sr0._029 /* symbol={'false|true'} */,
					sr0._029 /* symbol={'false|true'} */,
					sr0._016 /* symbol={'false|true'} */,
					sr0._016 /* symbol={'false|true'} */,
					sr0._016 /* symbol={'false|true'} */
				}, null
			);
		private final @NonNull EClassData _03 // CollectionLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._039 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._039 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						iv._3) /* CollectionLiteralPartCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						iv._4) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassData _04 // CollectionLiteralPartCS
			= new EClassData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
				new @NonNull SerializationRule [] {
					sr0._019 /* ownedExpression=PatternExpCS */,
					sr0._020 /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _05 // CollectionPatternCS
			= new EClassData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
				new @NonNull SerializationRule [] {
					sr0._021 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
					sr1._101 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
					sr0._021 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						iv._27) /* PatternExpCS */,
					new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						iv._4) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassData _06 // CollectionTypeCS
			= new EClassData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
				new @NonNull SerializationRule [] {
					sr0._022 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._023 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr1._100 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr0._022 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._023 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr1._107 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr3._207 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr0._023 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						iv._54) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassData _07 // ContextCS
			= new EClassData(EssentialOCLCSPackage.Literals.CONTEXT_CS,
				new @NonNull SerializationRule [] {
					sr0._057 /* ownedExpression=ExpCS */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _08 // CurlyBracketedClauseCS
			= new EClassData(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule [] {
					sr0._024 /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						iv._35) /* ShadowPartCS */
				}
			);
		private final @NonNull EClassData _09 // DataTypeCS
			= new EClassData(BaseCSPackage.Literals.DATA_TYPE_CS,
				new @NonNull SerializationRule [] {
					sr2._137 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr2._130 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr2._129 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr2._136 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._131 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._132 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._127 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr2._133 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr2._128 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr2._138 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._134 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._135 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._137 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr2._130 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr2._129 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr2._136 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._131 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._132 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						iv._13) /* InvariantConstraintCS */,
					new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._46) /* TemplateSignatureCS */,
					new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
				}
			);
		private final @NonNull EClassData _10 // DetailCS
			= new EClassData(BaseCSPackage.Literals.DETAIL_CS,
				new @NonNull SerializationRule [] {
					sr2._139 /* { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
				}, null
			);
		private final @NonNull EClassData _11 // DocumentationCS
			= new EClassData(BaseCSPackage.Literals.DOCUMENTATION_CS,
				new @NonNull SerializationRule [] {
					sr2._140 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr2._141 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						iv._6) /* DetailCS */
				}
			);
		private final @NonNull EClassData _12 // EnumerationCS
			= new EClassData(BaseCSPackage.Literals.ENUMERATION_CS,
				new @NonNull SerializationRule [] {
					sr2._152 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr2._149 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr2._144 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr2._153 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._142 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._146 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._147 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr2._151 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr2._148 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr2._143 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._145 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._150 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._152 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr2._149 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr2._144 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr2._153 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._142 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._146 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
						iv._8) /* EnumerationLiteralCS */,
					new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						iv._13) /* InvariantConstraintCS */,
					new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._46) /* TemplateSignatureCS */,
					new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
				}
			);
		private final @NonNull EClassData _13 // EnumerationLiteralCS
			= new EClassData(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS,
				new @NonNull SerializationRule [] {
					sr2._156 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr2._155 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr2._157 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr2._154 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr2._156 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr2._155 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr2._157 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr2._154 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
				}
			);
		private final @NonNull EClassData _14 // ExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._038 /* '*' */,
					sr0._030 /* 'invalid' */,
					sr0._035 /* 'null' */,
					sr0._027 /* 'self' */,
					sr0._038 /* '*' */,
					sr0._030 /* 'invalid' */,
					sr0._035 /* 'null' */,
					sr0._027 /* 'self' */
				}, null
			);
		private final @NonNull EClassData _15 // ExpSpecificationCS
			= new EClassData(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
				new @NonNull SerializationRule [] {
					sr3._195 /* exprString=UNQUOTED_STRING */,
					sr3._194 /* ownedExpression=ExpCS */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _16 // IfExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.IF_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._032 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._044 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._032 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._043 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._044 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						iv._7) /* ElseIfThenExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _17 // IfThenExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._025 /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _18 // ImplicitOppositeCS
			= new EClassData(BaseCSPackage.Literals.IMPLICIT_OPPOSITE_CS,
				new @NonNull SerializationRule [] {
					sr2._158 /* { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassData _19 // ImportCS
			= new EClassData(BaseCSPackage.Literals.IMPORT_CS,
				new @NonNull SerializationRule [] {
					sr2._159 /* { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
						iv._64) /* URIPathNameCS */
				}
			);
		private final @NonNull EClassData _20 // InfixExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._031 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._031 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
						iv._67) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _21 // InvalidLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._046 /* 'invalid' */,
					sr0._045 /* 'invalid' */,
					sr0._045 /* 'invalid' */,
					sr0._046 /* 'invalid' */
				}, null
			);
		private final @NonNull EClassData _22 // LambdaLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._033 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._048 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._033 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._047 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._048 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _23 // LetExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.LET_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._050 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr0._049 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr0._050 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr0._049 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						iv._14) /* LetVariableCS */
				}
			);
		private final @NonNull EClassData _24 // LetVariableCS
			= new EClassData(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
				new @NonNull SerializationRule [] {
					sr0._051 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._34) /* RoundBracketedClauseCS */
				}
			);
		private final @NonNull EClassData _25 // MapLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._040 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._053 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._040 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._052 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._053 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						iv._16) /* MapTypeCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						iv._15) /* MapLiteralPartCS */
				}
			);
		private final @NonNull EClassData _26 // MapLiteralPartCS
			= new EClassData(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
				new @NonNull SerializationRule [] {
					sr0._054 /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _27 // MapTypeCS
			= new EClassData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
				new @NonNull SerializationRule [] {
					sr0._056 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr0._055 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._098 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr0._056 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr0._055 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._109 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr3._210 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr0._055 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassData _28 // ModelElementRefCS
			= new EClassData(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS,
				new @NonNull SerializationRule [] {
					sr2._162 /* { 'reference' ownedPathName=PathNameCS ';' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
						iv._26) /* PathNameCS */
				}
			);
		private final @NonNull EClassData _29 // MultiplicityBoundsCS
			= new EClassData(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
				new @NonNull SerializationRule [] {
					sr0._001 /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */,
					sr0._005 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					sr0._002 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					sr0._003 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, null
			);
		private final @NonNull EClassData _30 // MultiplicityStringCS
			= new EClassData(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
				new @NonNull SerializationRule [] {
					sr0._006 /* { '[' stringBounds={'*|+|?'} ']' } */,
					sr0._007 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					sr0._004 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
					sr0._008 /* stringBounds={'*|+|?'} */
				}, null
			);
		private final @NonNull EClassData _31 // NameExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._042 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._058 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._042 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._059 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._058 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._26) /* PathNameCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._5) /* CurlyBracketedClauseCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._34) /* RoundBracketedClauseCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						iv._37) /* SquareBracketedClauseCS */
				}
			);
		private final @NonNull EClassData _32 // NavigatingArgCS
			= new EClassData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
				new @NonNull SerializationRule [] {
					sr1._064 /* ownedNameExpression=NavigatingArgExpCS */,
					sr0._061 /* { ':' ownedType=TypeExpCS } */,
					sr0._060 /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._062 /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._063 /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					sr1._065 /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */,
					sr1._067 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					sr1._068 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr1._066 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr1._069 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					sr1._070 /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						iv._2) /* CoIteratorVariableCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						iv._70) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _33 // NestedExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._034 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._034 /* { '(' ownedExpression=ExpCS ')' } */,
					sr1._071 /* { '(' ownedExpression=ExpCS ')' } */,
					sr1._072 /* { '(' ownedExpression=ExpCS ')' } */,
					sr1._071 /* { '(' ownedExpression=ExpCS ')' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _34 // NullLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr1._073 /* 'null' */,
					sr1._074 /* 'null' */,
					sr1._074 /* 'null' */,
					sr1._073 /* 'null' */
				}, null
			);
		private final @NonNull EClassData _35 // NumberLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._026 /* symbol=NUMBER_LITERAL */,
					sr0._026 /* symbol=NUMBER_LITERAL */,
					sr1._076 /* symbol=NUMBER_LITERAL */,
					sr1._075 /* symbol=NUMBER_LITERAL */,
					sr1._075 /* symbol=NUMBER_LITERAL */,
					sr1._076 /* symbol=NUMBER_LITERAL */
				}, null
			);
		private final @NonNull EClassData _36 // OCLinEcoreConstraintCS
			= new EClassData(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS,
				new @NonNull SerializationRule [] {
					sr2._160 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' } */,
					sr2._161 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */,
					sr2._180 /* { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */,
					sr2._181 /* { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						iv._36) /* SpecificationCS */,
					new EReferenceData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						iv._36) /* SpecificationCS */
				}
			);
		private final @NonNull EClassData _37 // OperationCS
			= new EClassData(BaseCSPackage.Literals.OPERATION_CS,
				new @NonNull SerializationRule [] {
					sr2._168 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._174 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._172 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._163 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._173 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._164 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._169 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._165 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._166 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._170 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._167 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._171 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						iv._30) /* PreconditionConstraintCS */,
					new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						iv._25) /* ParameterCS */,
					new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						iv._29) /* PostconditionConstraintCS */,
					new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
						iv._36) /* SpecificationCS */,
					new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						iv._60) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
					new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._46) /* TemplateSignatureCS */,
					new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassData _38 // PackageCS
			= new EClassData(BaseCSPackage.Literals.PACKAGE_CS,
				new @NonNull SerializationRule [] {
					sr2._177 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
					sr2._178 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */,
					sr2._176 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
					sr2._175 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						iv._24) /* PackageCS */,
					new EReferenceData(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
						iv._41) /* ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS */,
					new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
				}
			);
		private final @NonNull EClassData _39 // ParameterCS
			= new EClassData(BaseCSPackage.Literals.PARAMETER_CS,
				new @NonNull SerializationRule [] {
					sr2._179 /* { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassData _40 // PathElementCS
			= new EClassData(BaseCSPackage.Literals.PATH_ELEMENT_CS,
				new @NonNull SerializationRule [] {
					sr0._000 /* referredElement=UnrestrictedName */,
					sr0._009 /* referredElement=UnreservedName */,
					sr1._112 /* referredElement=UnrestrictedName */
				}, null
			);
		private final @NonNull EClassData _41 // PathElementWithURICS
			= new EClassData(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
				new @NonNull SerializationRule [] {
					sr1._113 /* referredElement=URI */
				}, null
			);
		private final @NonNull EClassData _42 // PathNameCS
			= new EClassData(BaseCSPackage.Literals.PATH_NAME_CS,
				new @NonNull SerializationRule [] {
					sr0._010 /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */,
					sr1._088 /* ownedPathElements+=FirstPathElementCS */,
					sr1._114 /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						iv._63) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
				}
			);
		private final @NonNull EClassData _43 // PatternExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
				new @NonNull SerializationRule [] {
					sr1._077 /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassData _44 // PrefixExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._041 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr1._078 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					sr0._041 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr1._078 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					sr1._079 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					sr1._080 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._68) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _45 // PrimitiveTypeRefCS
			= new EClassData(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
				new @NonNull SerializationRule [] {
					sr1._082 /* name=PrimitiveTypeIdentifier */,
					sr1._081 /* name=PrimitiveTypeIdentifier */,
					sr1._102 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._082 /* name=PrimitiveTypeIdentifier */,
					sr1._081 /* name=PrimitiveTypeIdentifier */,
					sr1._106 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr3._212 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._081 /* name=PrimitiveTypeIdentifier */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassData _46 // ReferenceCS
			= new EClassData(BaseCSPackage.Literals.REFERENCE_CS,
				new @NonNull SerializationRule [] {
					sr2._191 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._187 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._185 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._188 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._189 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._190 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._186 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._184 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._182 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr3._193 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._183 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr3._192 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._186 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._184 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._182 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr3._193 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._183 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr3._192 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReferenceData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						iv._36) /* SpecificationCS */,
					new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */,
					new EReferenceData(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
						iv._11) /* ImplicitOppositeCS */
				}
			);
		private final @NonNull EClassData _47 // RoundBracketedClauseCS
			= new EClassData(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule [] {
					sr1._083 /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						iv._21) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
				}
			);
		private final @NonNull EClassData _48 // SelfExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
				new @NonNull SerializationRule [] {
					sr1._085 /* 'self' */,
					sr1._085 /* 'self' */,
					sr1._084 /* 'self' */
				}, null
			);
		private final @NonNull EClassData _49 // ShadowPartCS
			= new EClassData(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
				new @NonNull SerializationRule [] {
					sr1._087 /* ownedInitExpression=StringLiteralExpCS */,
					sr1._086 /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _50 // SquareBracketedClauseCS
			= new EClassData(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule [] {
					sr1._089 /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _51 // StringLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._028 /* segments+=StringLiteral[+] */,
					sr0._028 /* segments+=StringLiteral[+] */,
					sr1._091 /* segments+=StringLiteral[+] */,
					sr1._091 /* segments+=StringLiteral[+] */,
					sr1._091 /* segments+=StringLiteral[+] */,
					sr1._090 /* segments+=StringLiteral[+] */
				}, null
			);
		private final @NonNull EClassData _52 // StructuredClassCS
			= new EClassData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS,
				new @NonNull SerializationRule [] {
					sr3._199 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr3._198 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr3._199 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr3._198 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr3._197 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr3._196 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						iv._13) /* InvariantConstraintCS */,
					new EReferenceData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
						iv._60) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
					new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._46) /* TemplateSignatureCS */,
					new EReferenceData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
						iv._40) /* AttributeCS|ReferenceCS|StructuralFeatureCS */,
					new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReferenceData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
						iv._23) /* OperationCS */
				}
			);
		private final @NonNull EClassData _53 // SysMLCS
			= new EClassData(OCLinEcoreCSPackage.Literals.SYS_MLCS,
				new @NonNull SerializationRule [] {
					sr3._201 /* { 'sysml' ownedDetails+=DetailCS ';' } */,
					sr3._200 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */,
					sr3._203 /* { 'sysml' ownedDetails+=DetailCS ';' } */,
					sr3._202 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						iv._6) /* DetailCS */
				}
			);
		private final @NonNull EClassData _54 // TemplateBindingCS
			= new EClassData(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
				new @NonNull SerializationRule [] {
					sr0._011 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						iv._45) /* TemplateParameterSubstitutionCS */,
					new EReferenceData(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassData _55 // TemplateParameterSubstitutionCS
			= new EClassData(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
				new @NonNull SerializationRule [] {
					sr0._012 /* ownedActualParameter=TypeRefCS */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						iv._72) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
				}
			);
		private final @NonNull EClassData _56 // TemplateSignatureCS
			= new EClassData(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
				new @NonNull SerializationRule [] {
					sr3._205 /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */,
					sr3._204 /* { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						iv._56) /* TypeParameterCS */
				}
			);
		private final @NonNull EClassData _57 // TopLevelCS
			= new EClassData(OCLinEcoreCSPackage.Literals.TOP_LEVEL_CS,
				new @NonNull SerializationRule [] {
					sr3._206 /* { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						iv._24) /* PackageCS */,
					new EReferenceData(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
						iv._12) /* ImportCS */
				}
			);
		private final @NonNull EClassData _58 // TupleLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._037 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._037 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._092 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._092 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._093 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						iv._47) /* TupleLiteralPartCS */
				}
			);
		private final @NonNull EClassData _59 // TupleLiteralPartCS
			= new EClassData(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
				new @NonNull SerializationRule [] {
					sr1._094 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _60 // TuplePartCS
			= new EClassData(BaseCSPackage.Literals.TUPLE_PART_CS,
				new @NonNull SerializationRule [] {
					sr1._095 /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassData _61 // TupleTypeCS
			= new EClassData(BaseCSPackage.Literals.TUPLE_TYPE_CS,
				new @NonNull SerializationRule [] {
					sr1._096 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._099 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._096 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._108 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr3._211 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						iv._48) /* TuplePartCS */,
					new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassData _62 // TypeLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._036 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._036 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._105 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._105 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._104 /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						iv._53) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
				}
			);
		private final @NonNull EClassData _63 // TypeNameExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
				new @NonNull SerializationRule [] {
					sr1._103 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._111 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */,
					sr1._110 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._26) /* PathNameCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._5) /* CurlyBracketedClauseCS */
				}
			);
		private final @NonNull EClassData _64 // TypeParameterCS
			= new EClassData(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
				new @NonNull SerializationRule [] {
					sr0._013 /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						iv._60) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassData _65 // TypedTypeRefCS
			= new EClassData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
				new @NonNull SerializationRule [] {
					sr3._218 /* ownedPathName=PathNameCS */,
					sr3._219 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr3._215 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
					sr3._208 /* { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */,
					sr3._213 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] } */,
					sr3._209 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] } */,
					sr3._218 /* ownedPathName=PathNameCS */,
					sr3._219 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr3._215 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
					sr3._217 /* ownedPathName=PathNameCS */,
					sr3._216 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr3._214 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						iv._26) /* PathNameCS */,
					new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						iv._44) /* TemplateBindingCS */,
					new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassData _66 // UnlimitedNaturalLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr1._116 /* '*' */,
					sr1._116 /* '*' */,
					sr1._116 /* '*' */,
					sr1._115 /* '*' */
				}, null
			);
		private final @NonNull EClassData _67 // VariableCS
			= new EClassData(EssentialOCLCSPackage.Literals.VARIABLE_CS,
				new @NonNull SerializationRule [] {
					sr0._017 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassData _68 // WildcardTypeRefCS
			= new EClassData(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
				new @NonNull SerializationRule [] {
					sr0._014 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */,
					sr0._015 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				new @NonNull EReferenceData [] {
					new EReferenceData(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						iv._60) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
				}
			);
	}

	/**
	 * The various serialization rules that serialize an EClass.
	 */
	private class _SerializationRules0
	{
		// Base::FirstPathElementCS : referredElement=UnrestrictedName
		private @NonNull SerializationRule _000 = new SerializationRule(31,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._049 /* assert (|referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._151 /* 1*referredElement=UnrestrictedName */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |referredElement| = 1 */
			});
		// Base::MultiplicityBoundsCS : { lowerBound=LOWER { '..' upperBound=UPPER }[?] }
		private @NonNull SerializationRule _001 = new SerializationRule(55,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._100 /* assign V0 = |upperBound| */,
				ms._001 /* assert (|lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._068 /* 1*lowerBound=LOWER */,
				st._169 /* V00*next-2-steps */,
				st._008 /* 1*'..' */,
				st._160 /* 1*upperBound=UPPER */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |lowerBound| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |upperBound| = C00[?] */
			});
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' }
		private @NonNull SerializationRule _002 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._100 /* assign V0 = |upperBound| */,
				ms._001 /* assert (|lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._021 /* 1*'[' */,
				st._068 /* 1*lowerBound=LOWER */,
				st._169 /* V00*next-2-steps */,
				st._008 /* 1*'..' */,
				st._160 /* 1*upperBound=UPPER */,
				st._062 /* 1*'|?' */,
				st._022 /* 1*']' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._5 /* «! » + «value» */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |lowerBound| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |upperBound| = C00[?] */
			});
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _003 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._116 /* assign V1 = |isNullFree.'|1'| */,
				ms._100 /* assign V0 = |upperBound| */,
				ms._001 /* assert (|lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._021 /* 1*'[' */,
				st._068 /* 1*lowerBound=LOWER */,
				st._169 /* V00*next-2-steps */,
				st._008 /* 1*'..' */,
				st._160 /* 1*upperBound=UPPER */,
				st._180 /* V01*'|1' */,
				st._022 /* 1*']' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._5 /* «! » + «value» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					ev._23)
			},
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |isNullFree.'|1'| =
					E02.0: |isNullFree.'|1'| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |lowerBound| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |upperBound| = C00[?] */
			});
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _004 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._073 /* assign V0 = |isNullFree.'|1'| */,
				ms._054 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._021 /* 1*'[' */,
				st._157 /* 1*stringBounds */,
				st._167 /* V00*'|1' */,
				st._022 /* 1*']' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._5 /* «! » + «value» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._04),
				new EAttributeData(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					ev._23)
			},
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |stringBounds.'*|+|?'| =
					E00.0: |stringBounds.'*|+|?'| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |isNullFree.'|1'| =
					E01.0: |isNullFree.'|1'| = C00[?] */
			});
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' }
		private @NonNull SerializationRule _005 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._100 /* assign V0 = |upperBound| */,
				ms._001 /* assert (|lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-6-steps */,
				st._021 /* 1*'[' */,
				st._068 /* 1*lowerBound=LOWER */,
				st._169 /* V00*next-2-steps */,
				st._008 /* 1*'..' */,
				st._160 /* 1*upperBound=UPPER */,
				st._022 /* 1*']' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._5 /* «! » + «value» */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |lowerBound| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |upperBound| = C00[?] */
			});
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} ']' }
		private @NonNull SerializationRule _006 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._054 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-3-steps */,
				st._021 /* 1*'[' */,
				st._157 /* 1*stringBounds */,
				st._022 /* 1*']' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._5 /* «! » + «value» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._04)
			},
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |stringBounds.'*|+|?'| =
					E00.0: |stringBounds.'*|+|?'| = 1 */
			});
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} '|?' ']' }
		private @NonNull SerializationRule _007 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._054 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._021 /* 1*'[' */,
				st._157 /* 1*stringBounds */,
				st._062 /* 1*'|?' */,
				st._022 /* 1*']' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._5 /* «! » + «value» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._04)
			},
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |stringBounds.'*|+|?'| =
					E00.0: |stringBounds.'*|+|?'| = 1 */
			});
		// Base::MultiplicityStringCS : stringBounds={'*|+|?'}
		private @NonNull SerializationRule _008 = new SerializationRule(57,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._054 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._157 /* 1*stringBounds */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._04)
			},
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |stringBounds.'*|+|?'| =
					E00.0: |stringBounds.'*|+|?'| = 1 */
			});
		// Base::NextPathElementCS : referredElement=UnreservedName
		private @NonNull SerializationRule _009 = new SerializationRule(67,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._049 /* assert (|referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._149 /* 1*referredElement=UnreservedName */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |referredElement| = 1 */
			});
		// Base::PathNameCS : { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] }
		private @NonNull SerializationRule _010 = new SerializationRule(73,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._247 /* check-rule basecs::PathNameCS.ownedPathElements : 31|67 */,
				ms._065 /* assign V0 = (|ownedPathElements| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._117 /* 1*ownedPathElements+=FirstPathElementCS */,
				st._169 /* V00*next-2-steps */,
				st._010 /* 1*'::' */,
				st._118 /* 1*ownedPathElements+=NextPathElementCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._22) /* FirstPathElementCS|NextPathElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathElements| = 1 + C00[*] */
			});
		// Base::TemplateBindingCS : { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _011 = new SerializationRule(99,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._256 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 100 */,
				ms._255 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 56 */,
				ms._125 /* assign V1 = |ownedMultiplicity| */,
				ms._066 /* assign V0 = (|ownedSubstitutions| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-5-steps */,
				st._130 /* 1*ownedSubstitutions+=TemplateParameterSubstitutionCS */,
				st._169 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._130 /* 1*ownedSubstitutions+=TemplateParameterSubstitutionCS */,
				st._185 /* V01*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					iv._45) /* TemplateParameterSubstitutionCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedSubstitutions| = 1 + C00[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedMultiplicity| = C01:MultiplicityCS[?] */
			});
		// Base::TemplateParameterSubstitutionCS : ownedActualParameter=TypeRefCS
		private @NonNull SerializationRule _012 = new SerializationRule(100,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._257 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 115 */,
				ms._007 /* assert (|ownedActualParameter| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._079 /* 1*ownedActualParameter=TypeRefCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					iv._57) /* TypeRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedActualParameter| = 1 */
			});
		// Base::TypeParameterCS : { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] }
		private @NonNull SerializationRule _013 = new SerializationRule(114,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._261 /* check-rule basecs::TypeParameterCS.ownedExtends : 117 */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._058 /* assign V0 = (|ownedExtends| > 0) */,
				ms._105 /* assign V1 = (|ownedExtends| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-5-steps */,
				st._035 /* 1*'extends' */,
				st._095 /* 1*ownedExtends+=TypedRefCS */,
				st._181 /* V01*next-2-steps */,
				st._002 /* 1*'&&' */,
				st._095 /* 1*ownedExtends+=TypedRefCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					iv._59) /* TypedRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedExtends| = C00[?] + C00[?] * C01[*] */
			});
		// Base::WildcardTypeRefCS : { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
		private @NonNull SerializationRule _014 = new SerializationRule(129,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._267 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : 117 */,
				ms._081 /* assign V0 = |ownedExtends| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._016 /* 1*'?' */,
				st._169 /* V00*next-2-steps */,
				st._035 /* 1*'extends' */,
				st._096 /* 1*ownedExtends=TypedRefCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					iv._59) /* TypedRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedExtends| = C00[?] */
			});
		// Base::WildcardTypeRefCS : { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
		private @NonNull SerializationRule _015 = new SerializationRule(129,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._267 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : 117 */,
				ms._081 /* assign V0 = |ownedExtends| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._016 /* 1*'?' */,
				st._169 /* V00*next-2-steps */,
				st._035 /* 1*'extends' */,
				st._096 /* 1*ownedExtends=TypedRefCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					iv._59) /* TypedRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedExtends| = C00[?] */
			});
		// EssentialOCL::BooleanLiteralExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _016 = new SerializationRule(5,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._055 /* assert (|symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._158 /* 1*symbol */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._14)
			},
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |symbol.'false|true'| =
					E00.0: |symbol.'false|true'| = 1 */
			});
		// EssentialOCL::CoIteratorVariableCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] }
		private @NonNull SerializationRule _017 = new SerializationRule(7,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._320 /* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
				ms._090 /* assign V0 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._139 /* 1*ownedType=TypeExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */
			});
		// EssentialOCL::CollectionLiteralExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _018 = new SerializationRule(8,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._272 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9 */,
				ms._273 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11 */,
				ms._037 /* assert (|ownedType| - 1) == 0 */,
				ms._062 /* assign V0 = (|ownedParts| > 0) */,
				ms._108 /* assign V1 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._136 /* 1*ownedType=CollectionTypeCS */,
				st._060 /* 1*'{' */,
				st._169 /* V00*next-4-steps */,
				st._111 /* 1*ownedParts+=CollectionLiteralPartCS */,
				st._181 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._111 /* 1*ownedParts+=CollectionLiteralPartCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					iv._3) /* CollectionLiteralPartCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					iv._4) /* CollectionTypeCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedParts| = C00[?] + C00[?] * C01[*] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedType| = 1 */
			});
		// EssentialOCL::CollectionLiteralPartCS : ownedExpression=PatternExpCS
		private @NonNull SerializationRule _019 = new SerializationRule(9,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._275 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 74 */,
				ms._015 /* assert (|ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._093 /* 1*ownedExpression=PatternExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					iv._27) /* PatternExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedExpression| = 1 */
			});
		// EssentialOCL::CollectionLiteralPartCS : { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] }
		private @NonNull SerializationRule _020 = new SerializationRule(9,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._276 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 30 */,
				ms._274 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 30 */,
				ms._084 /* assign V0 = |ownedLastExpression| */,
				ms._015 /* assert (|ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._089 /* 1*ownedExpression=ExpCS */,
				st._169 /* V00*next-2-steps */,
				st._008 /* 1*'..' */,
				st._105 /* 1*ownedLastExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedLastExpression| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedExpression| = 1 */
			});
		// EssentialOCL::CollectionPatternCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' }
		private @NonNull SerializationRule _021 = new SerializationRule(10,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._277 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74 */,
				ms._278 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 11 */,
				ms._098 /* assign V0 = |restVariableName| */,
				ms._110 /* assign V1 = (|ownedParts| - 1) */,
				ms._042 /* assert (|ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._135 /* 1*ownedType=CollectionTypeCS */,
				st._060 /* 1*'{' */,
				st._169 /* V00*next-6-steps */,
				st._113 /* 1*ownedParts+=PatternExpCS */,
				st._181 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._113 /* 1*ownedParts+=PatternExpCS */,
				st._006 /* 1*'++' */,
				st._156 /* 1*restVariableName=Identifier */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._27) /* PatternExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._4) /* CollectionTypeCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |restVariableName| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedParts| = C00[?] + C00[?] * C01[*] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedType| = 1 */
			});
		// EssentialOCL::CollectionTypeCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
		private @NonNull SerializationRule _022 = new SerializationRule(11,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
				ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
				ms._091 /* assign V0 = |ownedType| */,
				ms._006 /* assert (|name| - 1) == 0 */,
				ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-6-steps */,
				st._070 /* 1*name=CollectionTypeIdentifier */,
				st._169 /* V00*next-4-steps */,
				st._003 /* 1*'(' */,
				st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
				st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				ss._5 /* «! » + «value» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._50) /* TypeExpWithoutMultiplicityCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedCollectionMultiplicity| = C00[?] * C01:MultiplicityCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */
			});
		// EssentialOCL::CollectionTypeCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
		private @NonNull SerializationRule _023 = new SerializationRule(11,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
				ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
				ms._091 /* assign V0 = |ownedType| */,
				ms._006 /* assert (|name| - 1) == 0 */,
				ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-6-steps */,
				st._070 /* 1*name=CollectionTypeIdentifier */,
				st._169 /* V00*next-4-steps */,
				st._003 /* 1*'(' */,
				st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
				st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				ss._5 /* «! » + «value» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._50) /* TypeExpWithoutMultiplicityCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedCollectionMultiplicity| = C00[?] * C01:MultiplicityCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */
			});
		// EssentialOCL::CurlyBracketedClauseCS : { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _024 = new SerializationRule(13,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._282 /* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 90 */,
				ms._063 /* assign V0 = (|ownedParts| > 0) */,
				ms._107 /* assign V1 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._060 /* 1*'{' */,
				st._169 /* V00*next-4-steps */,
				st._114 /* 1*ownedParts+=ShadowPartCS */,
				st._181 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._114 /* 1*ownedParts+=ShadowPartCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					iv._35) /* ShadowPartCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedParts| = C00[?] + C00[?] * C01[*] */
			});
		// EssentialOCL::ElseIfThenExpCS : { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS }
		private @NonNull SerializationRule _025 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._289 /* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 30 */,
				ms._288 /* check-rule essentialoclcs::IfThenExpCS.ownedCondition : 30 */,
				ms._036 /* assert (|ownedThenExpression| - 1) == 0 */,
				ms._011 /* assert (|ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._032 /* 1*'elseif' */,
				st._083 /* 1*ownedCondition=ExpCS */,
				st._058 /* 1*'then' */,
				st._134 /* 1*ownedThenExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedThenExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedCondition| = 1 */
			});
		// EssentialOCL::ExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _026 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._056 /* assert (|symbol| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._159 /* 1*symbol=NUMBER_LITERAL */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |symbol| = 1 */
			});
		// EssentialOCL::ExpCS : 'self'
		private @NonNull SerializationRule _027 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._055 /* 1*'self' */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
			});
		// EssentialOCL::ExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _028 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._099 /* assign V0 = |segments| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* V00*segments+=StringLiteral */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |segments| = C00:StringLiteral[+] */
			});
		// EssentialOCL::ExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _029 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._055 /* assert (|symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._158 /* 1*symbol */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._14)
			},
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |symbol.'false|true'| =
					E00.0: |symbol.'false|true'| = 1 */
			});
		// EssentialOCL::ExpCS : 'invalid'
		private @NonNull SerializationRule _030 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._040 /* 1*'invalid' */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
			});
		// EssentialOCL::ExpCS : { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS }
		private @NonNull SerializationRule _031 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._290 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : 78 */,
				ms._306 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 30 */,
				ms._034 /* assert (|ownedRight| - 1) == 0 */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._025 /* assert (|ownedLeft| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-3-steps */,
				st._106 /* 1*ownedLeft=PrefixedPrimaryExpCS */,
				st._069 /* 1*name=BinaryOperatorName */,
				st._127 /* 1*ownedRight=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					iv._33) /* PrefixedPrimaryExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedLeft| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedRight| = 1 */
			});
		// EssentialOCL::ExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _032 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._287 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30 */,
				ms._285 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30 */,
				ms._284 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74 */,
				ms._286 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
				ms._013 /* assert (|ownedElseExpression| - 1) == 0 */,
				ms._082 /* assign V0 = |ownedIfThenExpressions| */,
				ms._035 /* assert (|ownedThenExpression| - 1) == 0 */,
				ms._010 /* assert (|ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._036 /* 1*'if' */,
				st._084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
				st._058 /* 1*'then' */,
				st._133 /* 1*ownedThenExpression=ExpCS */,
				st._170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
				st._031 /* 1*'else' */,
				st._087 /* 1*ownedElseExpression=ExpCS */,
				st._033 /* 1*'endif' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._28) /* ExpCS|PatternExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._7) /* ElseIfThenExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedThenExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedElseExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedCondition| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedIfThenExpressions| = C00:ElseIfThenExpCS[*] */
			});
		// EssentialOCL::ExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _033 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._291 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30 */,
				ms._014 /* assert (|ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._018 /* 1*'Lambda' */,
				st._060 /* 1*'{' */,
				st._094 /* 1*ownedExpressionCS=ExpCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedExpressionCS| = 1 */
			});
		// EssentialOCL::ExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _034 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._305 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 30 */,
				ms._016 /* assert (|ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-3-steps */,
				st._003 /* 1*'(' */,
				st._092 /* 1*ownedExpression=ExpCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._5 /* «! » + «value» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedExpression| = 1 */
			});
		// EssentialOCL::ExpCS : 'null'
		private @NonNull SerializationRule _035 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._046 /* 1*'null' */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
			});
		// EssentialOCL::ExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _036 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._315 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112 */,
				ms._039 /* assert (|ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._52) /* TypeLiteralWithMultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedType| = 1 */
			});
		// EssentialOCL::ExpCS : { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
		private @NonNull SerializationRule _037 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._314 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104 */,
				ms._060 /* assign V0 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._020 /* 1*'Tuple' */,
				st._060 /* 1*'{' */,
				st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
				st._169 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._47) /* TupleLiteralPartCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedParts| = 1 + C00[*] */
			});
		// EssentialOCL::ExpCS : '*'
		private @NonNull SerializationRule _038 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._005 /* 1*'*' */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
			});
		// EssentialOCL::ExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _039 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._272 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9 */,
				ms._273 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11 */,
				ms._037 /* assert (|ownedType| - 1) == 0 */,
				ms._062 /* assign V0 = (|ownedParts| > 0) */,
				ms._108 /* assign V1 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._136 /* 1*ownedType=CollectionTypeCS */,
				st._060 /* 1*'{' */,
				st._169 /* V00*next-4-steps */,
				st._111 /* 1*ownedParts+=CollectionLiteralPartCS */,
				st._181 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._111 /* 1*ownedParts+=CollectionLiteralPartCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					iv._3) /* CollectionLiteralPartCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					iv._4) /* CollectionTypeCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedParts| = C00[?] + C00[?] * C01[*] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedType| = 1 */
			});
		// EssentialOCL::ExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _040 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._296 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51 */,
				ms._295 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50 */,
				ms._038 /* assert (|ownedType| - 1) == 0 */,
				ms._061 /* assign V0 = (|ownedParts| > 0) */,
				ms._109 /* assign V1 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._137 /* 1*ownedType=MapTypeCS */,
				st._060 /* 1*'{' */,
				st._169 /* V00*next-4-steps */,
				st._112 /* 1*ownedParts+=MapLiteralPartCS */,
				st._181 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._112 /* 1*ownedParts+=MapLiteralPartCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					iv._16) /* MapTypeCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					iv._15) /* MapLiteralPartCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedType| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedParts| = C00[?] + C00[?] * C01[*] */
			});
		// EssentialOCL::ExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _041 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._308 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 78 */,
				ms._034 /* assert (|ownedRight| - 1) == 0 */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-2-steps */,
				st._073 /* 1*name=UnaryOperatorName */,
				st._129 /* 1*ownedRight=PrefixedPrimaryExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._33) /* PrefixedPrimaryExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedRight| = 1 */
			});
		// EssentialOCL::ExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _042 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._269 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73 */,
				ms._268 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._270 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84 */,
				ms._271 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93 */,
				ms._174 /* assign V3 = |isPre.'@'| */,
				ms._159 /* assign V2 = |ownedCurlyBracketedClause| */,
				ms._127 /* assign V1 = |ownedRoundBracketedClause| */,
				ms._088 /* assign V0 = |ownedSquareBracketedClauses| */,
				ms._031 /* assert (|ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._123 /* 1*ownedPathName=PathNameCS */,
				st._174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
				st._187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
				st._191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
				st._196 /* V03*next-2-steps */,
				st._017 /* 1*'@' */,
				st._051 /* 1*'pre' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				null,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._08)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._34) /* RoundBracketedClauseCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._37) /* SquareBracketedClauseCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |isPre.'@'| =
					E04.0: |isPre.'@'| = C03[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedCurlyBracketedClause| = C02:CurlyBracketedClauseCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedRoundBracketedClause| = C01:RoundBracketedClauseCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSquareBracketedClauses| = C00:SquareBracketedClauseCS[*] */
			});
		// EssentialOCL::IfExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _043 = new SerializationRule(36,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._287 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30 */,
				ms._285 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30 */,
				ms._284 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74 */,
				ms._286 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
				ms._013 /* assert (|ownedElseExpression| - 1) == 0 */,
				ms._082 /* assign V0 = |ownedIfThenExpressions| */,
				ms._035 /* assert (|ownedThenExpression| - 1) == 0 */,
				ms._010 /* assert (|ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._036 /* 1*'if' */,
				st._084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
				st._058 /* 1*'then' */,
				st._133 /* 1*ownedThenExpression=ExpCS */,
				st._170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
				st._031 /* 1*'else' */,
				st._087 /* 1*ownedElseExpression=ExpCS */,
				st._033 /* 1*'endif' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._28) /* ExpCS|PatternExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._7) /* ElseIfThenExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedThenExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedElseExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedCondition| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedIfThenExpressions| = C00:ElseIfThenExpCS[*] */
			});
		// EssentialOCL::IfExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _044 = new SerializationRule(36,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._287 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30 */,
				ms._285 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30 */,
				ms._284 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74 */,
				ms._286 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
				ms._013 /* assert (|ownedElseExpression| - 1) == 0 */,
				ms._082 /* assign V0 = |ownedIfThenExpressions| */,
				ms._035 /* assert (|ownedThenExpression| - 1) == 0 */,
				ms._010 /* assert (|ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._036 /* 1*'if' */,
				st._084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
				st._058 /* 1*'then' */,
				st._133 /* 1*ownedThenExpression=ExpCS */,
				st._170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
				st._031 /* 1*'else' */,
				st._087 /* 1*ownedElseExpression=ExpCS */,
				st._033 /* 1*'endif' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._28) /* ExpCS|PatternExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._7) /* ElseIfThenExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedThenExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedElseExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedCondition| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedIfThenExpressions| = C00:ElseIfThenExpCS[*] */
			});
		// EssentialOCL::InvalidLiteralExpCS : 'invalid'
		private @NonNull SerializationRule _045 = new SerializationRule(40,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._040 /* 1*'invalid' */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
			});
		// EssentialOCL::InvalidLiteralExpCS : 'invalid'
		private @NonNull SerializationRule _046 = new SerializationRule(40,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._040 /* 1*'invalid' */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
			});
		// EssentialOCL::LambdaLiteralExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _047 = new SerializationRule(44,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._291 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30 */,
				ms._014 /* assert (|ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._018 /* 1*'Lambda' */,
				st._060 /* 1*'{' */,
				st._094 /* 1*ownedExpressionCS=ExpCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedExpressionCS| = 1 */
			});
		// EssentialOCL::LambdaLiteralExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _048 = new SerializationRule(44,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._291 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30 */,
				ms._014 /* assert (|ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._018 /* 1*'Lambda' */,
				st._060 /* 1*'{' */,
				st._094 /* 1*ownedExpressionCS=ExpCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedExpressionCS| = 1 */
			});
		// EssentialOCL::LetExpCS : { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
		private @NonNull SerializationRule _049 = new SerializationRule(45,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._292 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : 30 */,
				ms._293 /* check-rule essentialoclcs::LetExpCS.ownedVariables : 46 */,
				ms._019 /* assert (|ownedInExpression| - 1) == 0 */,
				ms._068 /* assign V0 = (|ownedVariables| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._043 /* 1*'let' */,
				st._146 /* 1*ownedVariables+=LetVariableCS */,
				st._169 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._146 /* 1*ownedVariables+=LetVariableCS */,
				st._038 /* 1*'in' */,
				st._098 /* 1*ownedInExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					iv._14) /* LetVariableCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedInExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedVariables| = 1 + C00[*] */
			});
		// EssentialOCL::LetExpCS : { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
		private @NonNull SerializationRule _050 = new SerializationRule(45,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._292 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : 30 */,
				ms._293 /* check-rule essentialoclcs::LetExpCS.ownedVariables : 46 */,
				ms._019 /* assert (|ownedInExpression| - 1) == 0 */,
				ms._068 /* assign V0 = (|ownedVariables| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._043 /* 1*'let' */,
				st._146 /* 1*ownedVariables+=LetVariableCS */,
				st._169 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._146 /* 1*ownedVariables+=LetVariableCS */,
				st._038 /* 1*'in' */,
				st._098 /* 1*ownedInExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					iv._14) /* LetVariableCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedInExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedVariables| = 1 + C00[*] */
			});
		// EssentialOCL::LetVariableCS : { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _051 = new SerializationRule(46,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._320 /* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
				ms._319 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 30 */,
				ms._294 /* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 84 */,
				ms._022 /* assert (|ownedInitExpression| - 1) == 0 */,
				ms._130 /* assign V1 = |ownedType| */,
				ms._086 /* assign V0 = |ownedRoundBracketedClause| */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._074 /* 1*name=UnrestrictedName */,
				st._172 /* V00*ownedRoundBracketedClause=RoundBracketedClauseCS */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._139 /* 1*ownedType=TypeExpCS */,
				st._014 /* 1*'=' */,
				st._099 /* 1*ownedInitExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._34) /* RoundBracketedClauseCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedType| = C01[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedInitExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedRoundBracketedClause| = C00:RoundBracketedClauseCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */
			});
		// EssentialOCL::MapLiteralExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _052 = new SerializationRule(49,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._296 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51 */,
				ms._295 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50 */,
				ms._038 /* assert (|ownedType| - 1) == 0 */,
				ms._061 /* assign V0 = (|ownedParts| > 0) */,
				ms._109 /* assign V1 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._137 /* 1*ownedType=MapTypeCS */,
				st._060 /* 1*'{' */,
				st._169 /* V00*next-4-steps */,
				st._112 /* 1*ownedParts+=MapLiteralPartCS */,
				st._181 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._112 /* 1*ownedParts+=MapLiteralPartCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					iv._16) /* MapTypeCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					iv._15) /* MapLiteralPartCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedType| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedParts| = C00[?] + C00[?] * C01[*] */
			});
		// EssentialOCL::MapLiteralExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _053 = new SerializationRule(49,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._296 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51 */,
				ms._295 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50 */,
				ms._038 /* assert (|ownedType| - 1) == 0 */,
				ms._061 /* assign V0 = (|ownedParts| > 0) */,
				ms._109 /* assign V1 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._137 /* 1*ownedType=MapTypeCS */,
				st._060 /* 1*'{' */,
				st._169 /* V00*next-4-steps */,
				st._112 /* 1*ownedParts+=MapLiteralPartCS */,
				st._181 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._112 /* 1*ownedParts+=MapLiteralPartCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					iv._16) /* MapTypeCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					iv._15) /* MapLiteralPartCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedType| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedParts| = C00[?] + C00[?] * C01[*] */
			});
		// EssentialOCL::MapLiteralPartCS : { ownedKey=ExpCS '<-' ownedValue=ExpCS }
		private @NonNull SerializationRule _054 = new SerializationRule(50,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._297 /* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 30 */,
				ms._298 /* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 30 */,
				ms._043 /* assert (|ownedValue| - 1) == 0 */,
				ms._024 /* assert (|ownedKey| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-3-steps */,
				st._103 /* 1*ownedKey=ExpCS */,
				st._013 /* 1*'<-' */,
				st._144 /* 1*ownedValue=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedKey| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedValue| = 1 */
			});
		// EssentialOCL::MapTypeCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
		private @NonNull SerializationRule _055 = new SerializationRule(51,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
				ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
				ms._093 /* assign V0 = |ownedValueType| */,
				ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
				ms._002 /* assert (|name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._019 /* 1*'Map' */,
				st._169 /* V00*next-5-steps */,
				st._003 /* 1*'(' */,
				st._104 /* 1*ownedKeyType=TypeExpCS */,
				st._007 /* 1*',' */,
				st._145 /* 1*ownedValueType=TypeExpCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._49) /* TypeExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedValueType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedKeyType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name.'Map'| =
					E00.0: |name.'Map'| = 1 */
			});
		// EssentialOCL::MapTypeCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
		private @NonNull SerializationRule _056 = new SerializationRule(51,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
				ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
				ms._093 /* assign V0 = |ownedValueType| */,
				ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
				ms._002 /* assert (|name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._019 /* 1*'Map' */,
				st._169 /* V00*next-5-steps */,
				st._003 /* 1*'(' */,
				st._104 /* 1*ownedKeyType=TypeExpCS */,
				st._007 /* 1*',' */,
				st._145 /* 1*ownedValueType=TypeExpCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._49) /* TypeExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedValueType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedKeyType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name.'Map'| =
					E00.0: |name.'Map'| = 1 */
			});
		// EssentialOCL::Model : ownedExpression=ExpCS
		private @NonNull SerializationRule _057 = new SerializationRule(52,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._281 /* check-rule essentialoclcs::ContextCS.ownedExpression : 30 */,
				ms._017 /* assert (|ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._091 /* 1*ownedExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedExpression| = 1 */
			});
		// EssentialOCL::NameExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _058 = new SerializationRule(59,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._269 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73 */,
				ms._268 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._270 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84 */,
				ms._271 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93 */,
				ms._174 /* assign V3 = |isPre.'@'| */,
				ms._159 /* assign V2 = |ownedCurlyBracketedClause| */,
				ms._127 /* assign V1 = |ownedRoundBracketedClause| */,
				ms._088 /* assign V0 = |ownedSquareBracketedClauses| */,
				ms._031 /* assert (|ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._123 /* 1*ownedPathName=PathNameCS */,
				st._174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
				st._187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
				st._191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
				st._196 /* V03*next-2-steps */,
				st._017 /* 1*'@' */,
				st._051 /* 1*'pre' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				null,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._08)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._34) /* RoundBracketedClauseCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._37) /* SquareBracketedClauseCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |isPre.'@'| =
					E04.0: |isPre.'@'| = C03[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedCurlyBracketedClause| = C02:CurlyBracketedClauseCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedRoundBracketedClause| = C01:RoundBracketedClauseCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSquareBracketedClauses| = C00:SquareBracketedClauseCS[*] */
			});
		// EssentialOCL::NameExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _059 = new SerializationRule(59,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._269 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73 */,
				ms._268 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._270 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84 */,
				ms._271 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93 */,
				ms._174 /* assign V3 = |isPre.'@'| */,
				ms._159 /* assign V2 = |ownedCurlyBracketedClause| */,
				ms._127 /* assign V1 = |ownedRoundBracketedClause| */,
				ms._088 /* assign V0 = |ownedSquareBracketedClauses| */,
				ms._031 /* assert (|ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._123 /* 1*ownedPathName=PathNameCS */,
				st._174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
				st._187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
				st._191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
				st._196 /* V03*next-2-steps */,
				st._017 /* 1*'@' */,
				st._051 /* 1*'pre' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				null,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._08)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._34) /* RoundBracketedClauseCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._37) /* SquareBracketedClauseCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |isPre.'@'| =
					E04.0: |isPre.'@'| = C03[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedCurlyBracketedClause| = C02:CurlyBracketedClauseCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedRoundBracketedClause| = C01:RoundBracketedClauseCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSquareBracketedClauses| = C00:SquareBracketedClauseCS[*] */
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _060 = new SerializationRule(60,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._083 /* assign V0 = |ownedInitExpression| */,
				ms._009 /* assert (|ownedCoIterator| - 1) == 0 */,
				ms._026 /* assert (|ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-6-steps */,
				st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
				st._013 /* 1*'<-' */,
				st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
				st._169 /* V00*next-2-steps */,
				st._014 /* 1*'=' */,
				st._100 /* 1*ownedInitExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedCoIterator| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedNameExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedInitExpression| = C00[?] */
			});
		// EssentialOCL::NavigatingArgCS : { ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _061 = new SerializationRule(60,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._040 /* assert (|ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-2-steps */,
				st._009 /* 1*':' */,
				st._140 /* 1*ownedType=TypeExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedType| = 1 */
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _062 = new SerializationRule(60,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._122 /* assign V1 = |ownedInitExpression| */,
				ms._078 /* assign V0 = |ownedCoIterator| */,
				ms._040 /* assert (|ownedType| - 1) == 0 */,
				ms._026 /* assert (|ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-9-steps */,
				st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
				st._009 /* 1*':' */,
				st._140 /* 1*ownedType=TypeExpCS */,
				st._169 /* V00*next-2-steps */,
				st._013 /* 1*'<-' */,
				st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._100 /* 1*ownedInitExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedCoIterator| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedNameExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedInitExpression| = C01[?] */
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _063 = new SerializationRule(60,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._021 /* assert (|ownedInitExpression| - 1) == 0 */,
				ms._119 /* assign V1 = |ownedCoIterator| */,
				ms._092 /* assign V0 = |ownedType| */,
				ms._026 /* assert (|ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-9-steps */,
				st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._140 /* 1*ownedType=TypeExpCS */,
				st._181 /* V01*next-2-steps */,
				st._013 /* 1*'<-' */,
				st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
				st._038 /* 1*'in' */,
				st._100 /* 1*ownedInitExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedCoIterator| = C01[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedNameExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedInitExpression| = 1 */
			});
	}
	private class _SerializationRules1
	{
		// EssentialOCL::NavigatingArgCS : ownedNameExpression=NavigatingArgExpCS
		private @NonNull SerializationRule _064 = new SerializationRule(60,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._026 /* assert (|ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedNameExpression| = 1 */
			});
		// EssentialOCL::NavigatingBarArgCS : { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _065 = new SerializationRule(62,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._092 /* assign V0 = |ownedType| */,
				ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
				ms._046 /* assert (|prefix.'|'| - 1) == 0 */,
				ms._122 /* assign V1 = |ownedInitExpression| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._061 /* 1*'|' */,
				st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
				st._169 /* V00*next-5-steps */,
				st._009 /* 1*':' */,
				st._140 /* 1*ownedType=TypeExpCS */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._100 /* 1*ownedInitExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._22)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedNameExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |prefix.'|'| =
					E00.0: |prefix.'|'| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedInitExpression| = C00[?] * C01[?] */
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _066 = new SerializationRule(63,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._122 /* assign V1 = |ownedInitExpression| */,
				ms._078 /* assign V0 = |ownedCoIterator| */,
				ms._040 /* assert (|ownedType| - 1) == 0 */,
				ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
				ms._044 /* assert (|prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._007 /* 1*',' */,
				st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
				st._009 /* 1*':' */,
				st._140 /* 1*ownedType=TypeExpCS */,
				st._169 /* V00*next-2-steps */,
				st._013 /* 1*'<-' */,
				st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._100 /* 1*ownedInitExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._05)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedType| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedCoIterator| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedNameExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |prefix.','| =
					E00.0: |prefix.','| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedInitExpression| = C01[?] */
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS }
		private @NonNull SerializationRule _067 = new SerializationRule(63,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
				ms._044 /* assert (|prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-2-steps */,
				st._007 /* 1*',' */,
				st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._3 /* «! » + «value» + «? » */,
				null
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._05)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedNameExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |prefix.','| =
					E00.0: |prefix.','| = 1 */
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _068 = new SerializationRule(63,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._083 /* assign V0 = |ownedInitExpression| */,
				ms._009 /* assert (|ownedCoIterator| - 1) == 0 */,
				ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
				ms._044 /* assert (|prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._007 /* 1*',' */,
				st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
				st._013 /* 1*'<-' */,
				st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
				st._169 /* V00*next-2-steps */,
				st._014 /* 1*'=' */,
				st._100 /* 1*ownedInitExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._05)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedCoIterator| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedNameExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |prefix.','| =
					E00.0: |prefix.','| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedInitExpression| = C00[?] */
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _069 = new SerializationRule(63,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._021 /* assert (|ownedInitExpression| - 1) == 0 */,
				ms._119 /* assign V1 = |ownedCoIterator| */,
				ms._092 /* assign V0 = |ownedType| */,
				ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
				ms._044 /* assert (|prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._007 /* 1*',' */,
				st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._140 /* 1*ownedType=TypeExpCS */,
				st._181 /* V01*next-2-steps */,
				st._013 /* 1*'<-' */,
				st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
				st._038 /* 1*'in' */,
				st._100 /* 1*ownedInitExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._05)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedCoIterator| = C01[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedNameExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |prefix.','| =
					E00.0: |prefix.','| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedInitExpression| = 1 */
			});
		// EssentialOCL::NavigatingSemiArgCS : { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _070 = new SerializationRule(64,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._092 /* assign V0 = |ownedType| */,
				ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
				ms._045 /* assert (|prefix.';'| - 1) == 0 */,
				ms._122 /* assign V1 = |ownedInitExpression| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._011 /* 1*';' */,
				st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
				st._169 /* V00*next-5-steps */,
				st._009 /* 1*':' */,
				st._140 /* 1*ownedType=TypeExpCS */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._100 /* 1*ownedInitExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._07)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedNameExpression| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |prefix.';'| =
					E00.0: |prefix.';'| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedInitExpression| = C00[?] * C01[?] */
			});
		// EssentialOCL::NestedExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _071 = new SerializationRule(66,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._305 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 30 */,
				ms._016 /* assert (|ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-3-steps */,
				st._003 /* 1*'(' */,
				st._092 /* 1*ownedExpression=ExpCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._5 /* «! » + «value» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedExpression| = 1 */
			});
		// EssentialOCL::NestedExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _072 = new SerializationRule(66,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._305 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 30 */,
				ms._016 /* assert (|ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-3-steps */,
				st._003 /* 1*'(' */,
				st._092 /* 1*ownedExpression=ExpCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._5 /* «! » + «value» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedExpression| = 1 */
			});
		// EssentialOCL::NullLiteralExpCS : 'null'
		private @NonNull SerializationRule _073 = new SerializationRule(68,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._046 /* 1*'null' */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
			});
		// EssentialOCL::NullLiteralExpCS : 'null'
		private @NonNull SerializationRule _074 = new SerializationRule(68,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._046 /* 1*'null' */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
			});
		// EssentialOCL::NumberLiteralExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _075 = new SerializationRule(69,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._056 /* assert (|symbol| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._159 /* 1*symbol=NUMBER_LITERAL */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |symbol| = 1 */
			});
		// EssentialOCL::NumberLiteralExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _076 = new SerializationRule(69,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._056 /* assert (|symbol| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._159 /* 1*symbol=NUMBER_LITERAL */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |symbol| = 1 */
			});
		// EssentialOCL::PatternExpCS : { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS }
		private @NonNull SerializationRule _077 = new SerializationRule(74,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._309 /* check-rule essentialoclcs::PatternExpCS.ownedPatternType : 107 */,
				ms._033 /* assert (|ownedPatternType| - 1) == 0 */,
				ms._094 /* assign V0 = |patternVariableName| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-3-steps */,
				st._175 /* V00*patternVariableName=UnrestrictedName */,
				st._009 /* 1*':' */,
				st._126 /* 1*ownedPatternType=TypeExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					iv._49) /* TypeExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |patternVariableName| = C00:UnrestrictedName[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedPatternType| = 1 */
			});
		// EssentialOCL::PrefixedLetExpCS : { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		private @NonNull SerializationRule _078 = new SerializationRule(77,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._307 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 77 */,
				ms._034 /* assert (|ownedRight| - 1) == 0 */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-2-steps */,
				st._073 /* 1*name=UnaryOperatorName */,
				st._128 /* 1*ownedRight=PrefixedLetExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._31) /* PrefixedLetExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedRight| = 1 */
			});
		// EssentialOCL::PrefixedLetExpCS : { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		private @NonNull SerializationRule _079 = new SerializationRule(77,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._307 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 77 */,
				ms._034 /* assert (|ownedRight| - 1) == 0 */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-2-steps */,
				st._073 /* 1*name=UnaryOperatorName */,
				st._128 /* 1*ownedRight=PrefixedLetExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._31) /* PrefixedLetExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedRight| = 1 */
			});
		// EssentialOCL::PrefixedPrimaryExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _080 = new SerializationRule(78,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._308 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 78 */,
				ms._034 /* assert (|ownedRight| - 1) == 0 */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-2-steps */,
				st._073 /* 1*name=UnaryOperatorName */,
				st._129 /* 1*ownedRight=PrefixedPrimaryExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._33) /* PrefixedPrimaryExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedRight| = 1 */
			});
		// EssentialOCL::PrimitiveTypeCS : name=PrimitiveTypeIdentifier
		private @NonNull SerializationRule _081 = new SerializationRule(81,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._004 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._072 /* 1*name=PrimitiveTypeIdentifier */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */
			});
		// EssentialOCL::PrimitiveTypeCS : name=PrimitiveTypeIdentifier
		private @NonNull SerializationRule _082 = new SerializationRule(81,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._004 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._072 /* 1*name=PrimitiveTypeIdentifier */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */
			});
		// EssentialOCL::RoundBracketedClauseCS : { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' }
		private @NonNull SerializationRule _083 = new SerializationRule(84,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._310 /* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 60|62|63|64 */,
				ms._057 /* assign V0 = (|ownedArguments| > 0) */,
				ms._103 /* assign V1 = (|ownedArguments| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-5-steps */,
				st._003 /* 1*'(' */,
				st._169 /* V00*next-2-steps */,
				st._080 /* 1*ownedArguments+=NavigatingArgCS */,
				st._182 /* V01*ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._5 /* «! » + «value» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					iv._21) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedArguments| = C00[?] + C00[?] * C01:NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS[*] */
			});
		// EssentialOCL::SelfExpCS : 'self'
		private @NonNull SerializationRule _084 = new SerializationRule(89,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._055 /* 1*'self' */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
			});
		// EssentialOCL::SelfExpCS : 'self'
		private @NonNull SerializationRule _085 = new SerializationRule(89,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._055 /* 1*'self' */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
			});
		// EssentialOCL::ShadowPartCS : { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) }
		private @NonNull SerializationRule _086 = new SerializationRule(90,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._311 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 30|74 */,
				ms._020 /* assert (|ownedInitExpression| - 1) == 0 */,
				ms._050 /* assert (|referredProperty| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-3-steps */,
				st._155 /* 1*referredProperty=UnrestrictedName */,
				st._014 /* 1*'=' */,
				st._101 /* 1*ownedInitExpression=ExpCS|PatternExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._28) /* ExpCS|PatternExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |referredProperty| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedInitExpression| = 1 */
			});
		// EssentialOCL::ShadowPartCS : ownedInitExpression=StringLiteralExpCS
		private @NonNull SerializationRule _087 = new SerializationRule(90,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._312 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 95 */,
				ms._020 /* assert (|ownedInitExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._102 /* 1*ownedInitExpression=StringLiteralExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._38) /* StringLiteralExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedInitExpression| = 1 */
			});
		// EssentialOCL::SimplePathNameCS : ownedPathElements+=FirstPathElementCS
		private @NonNull SerializationRule _088 = new SerializationRule(91,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._246 /* check-rule basecs::PathNameCS.ownedPathElements : 31 */,
				ms._027 /* assert (|ownedPathElements| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*ownedPathElements+=FirstPathElementCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._10) /* FirstPathElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathElements| = 1 */
			});
		// EssentialOCL::SquareBracketedClauseCS : { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' }
		private @NonNull SerializationRule _089 = new SerializationRule(93,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._313 /* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 30 */,
				ms._067 /* assign V0 = (|ownedTerms| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-6-steps */,
				st._021 /* 1*'[' */,
				st._132 /* 1*ownedTerms+=ExpCS */,
				st._169 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._132 /* 1*ownedTerms+=ExpCS */,
				st._022 /* 1*']' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedTerms| = 1 + C00[*] */
			});
		// EssentialOCL::StringLiteralExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _090 = new SerializationRule(95,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._099 /* assign V0 = |segments| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* V00*segments+=StringLiteral */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |segments| = C00:StringLiteral[+] */
			});
		// EssentialOCL::StringLiteralExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _091 = new SerializationRule(95,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._099 /* assign V0 = |segments| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* V00*segments+=StringLiteral */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |segments| = C00:StringLiteral[+] */
			});
		// EssentialOCL::TupleLiteralExpCS : { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
		private @NonNull SerializationRule _092 = new SerializationRule(103,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._314 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104 */,
				ms._060 /* assign V0 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._020 /* 1*'Tuple' */,
				st._060 /* 1*'{' */,
				st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
				st._169 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._47) /* TupleLiteralPartCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedParts| = 1 + C00[*] */
			});
		// EssentialOCL::TupleLiteralExpCS : { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
		private @NonNull SerializationRule _093 = new SerializationRule(103,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._314 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104 */,
				ms._060 /* assign V0 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._020 /* 1*'Tuple' */,
				st._060 /* 1*'{' */,
				st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
				st._169 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._47) /* TupleLiteralPartCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedParts| = 1 + C00[*] */
			});
		// EssentialOCL::TupleLiteralPartCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _094 = new SerializationRule(104,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._320 /* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
				ms._319 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 30 */,
				ms._022 /* assert (|ownedInitExpression| - 1) == 0 */,
				ms._090 /* assign V0 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-6-steps */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._139 /* 1*ownedType=TypeExpCS */,
				st._014 /* 1*'=' */,
				st._099 /* 1*ownedInitExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedInitExpression| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */
			});
		// EssentialOCL::TuplePartCS : { name=UnrestrictedName ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _095 = new SerializationRule(105,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._262 /* check-rule basecs::TypedElementCS.ownedType : 107 */,
				ms._041 /* assert (|ownedType| - 1) == 0 */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-3-steps */,
				st._074 /* 1*name=UnrestrictedName */,
				st._009 /* 1*':' */,
				st._138 /* 1*ownedType=TypeExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = 1 */
			});
		// EssentialOCL::TupleTypeCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
		private @NonNull SerializationRule _096 = new SerializationRule(106,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
				ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
				ms._064 /* assign V0 = (|ownedParts| > 0) */,
				ms._111 /* assign V1 = (|ownedParts| > 0) */,
				ms._152 /* assign V2 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-9-steps */,
				st._020 /* 1*'Tuple' */,
				st._169 /* V00*next-7-steps */,
				st._003 /* 1*'(' */,
				st._181 /* V01*next-4-steps */,
				st._116 /* 1*ownedParts+=TuplePartCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._116 /* 1*ownedParts+=TuplePartCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._10)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._48) /* TuplePartCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name.'Tuple'| =
					E00.0: |name.'Tuple'| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedParts| = C00[?] * C01[?] + C00[?] * C01[?] * C02[*] */
			});
		// EssentialOCL::TupleTypeCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
		private @NonNull SerializationRule _097 = new SerializationRule(106,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
				ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
				ms._064 /* assign V0 = (|ownedParts| > 0) */,
				ms._111 /* assign V1 = (|ownedParts| > 0) */,
				ms._152 /* assign V2 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-9-steps */,
				st._020 /* 1*'Tuple' */,
				st._169 /* V00*next-7-steps */,
				st._003 /* 1*'(' */,
				st._181 /* V01*next-4-steps */,
				st._116 /* 1*ownedParts+=TuplePartCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._116 /* 1*ownedParts+=TuplePartCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._10)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._48) /* TuplePartCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name.'Tuple'| =
					E00.0: |name.'Tuple'| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedParts| = C00[?] * C01[?] + C00[?] * C01[?] * C02[*] */
			});
		// EssentialOCL::TypeExpCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _098 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
				ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._124 /* assign V1 = |ownedMultiplicity| */,
				ms._093 /* assign V0 = |ownedValueType| */,
				ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
				ms._002 /* assert (|name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._019 /* 1*'Map' */,
				st._169 /* V00*next-5-steps */,
				st._003 /* 1*'(' */,
				st._104 /* 1*ownedKeyType=TypeExpCS */,
				st._007 /* 1*',' */,
				st._145 /* 1*ownedValueType=TypeExpCS */,
				st._004 /* 1*')' */,
				st._186 /* V01*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedValueType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedKeyType| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedMultiplicity| = C01:MultiplicityCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name.'Map'| =
					E00.0: |name.'Map'| = 1 */
			});
		// EssentialOCL::TypeExpCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _099 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._177 /* assign V3 = |ownedMultiplicity| */,
				ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
				ms._064 /* assign V0 = (|ownedParts| > 0) */,
				ms._111 /* assign V1 = (|ownedParts| > 0) */,
				ms._152 /* assign V2 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._020 /* 1*'Tuple' */,
				st._169 /* V00*next-7-steps */,
				st._003 /* 1*'(' */,
				st._181 /* V01*next-4-steps */,
				st._116 /* 1*ownedParts+=TuplePartCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._116 /* 1*ownedParts+=TuplePartCS */,
				st._004 /* 1*')' */,
				st._198 /* V03*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._10)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._48) /* TuplePartCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name.'Tuple'| =
					E00.0: |name.'Tuple'| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedParts| = C00[?] * C01[?] + C00[?] * C01[?] * C02[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedMultiplicity| = C03:MultiplicityCS[?] */
			});
		// EssentialOCL::TypeExpCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _100 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
				ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._161 /* assign V2 = |ownedMultiplicity| */,
				ms._091 /* assign V0 = |ownedType| */,
				ms._006 /* assert (|name| - 1) == 0 */,
				ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._070 /* 1*name=CollectionTypeIdentifier */,
				st._169 /* V00*next-4-steps */,
				st._003 /* 1*'(' */,
				st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
				st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
				st._004 /* 1*')' */,
				st._192 /* V02*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				ss._5 /* «! » + «value» */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._50) /* TypeExpWithoutMultiplicityCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedCollectionMultiplicity| = C00[?] * C01:MultiplicityCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedMultiplicity| = C02:MultiplicityCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */
			});
		// EssentialOCL::TypeExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _101 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._277 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74 */,
				ms._278 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 11 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._161 /* assign V2 = |ownedMultiplicity| */,
				ms._098 /* assign V0 = |restVariableName| */,
				ms._110 /* assign V1 = (|ownedParts| - 1) */,
				ms._042 /* assert (|ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-11-steps */,
				st._135 /* 1*ownedType=CollectionTypeCS */,
				st._060 /* 1*'{' */,
				st._169 /* V00*next-6-steps */,
				st._113 /* 1*ownedParts+=PatternExpCS */,
				st._181 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._113 /* 1*ownedParts+=PatternExpCS */,
				st._006 /* 1*'++' */,
				st._156 /* 1*restVariableName=Identifier */,
				st._063 /* 1*'}' */,
				st._192 /* V02*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._27) /* PatternExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._4) /* CollectionTypeCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |restVariableName| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedParts| = C00[?] + C00[?] * C01[*] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedType| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedMultiplicity| = C02:MultiplicityCS[?] */
			});
		// EssentialOCL::TypeExpCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _102 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._085 /* assign V0 = |ownedMultiplicity| */,
				ms._004 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-2-steps */,
				st._072 /* 1*name=PrimitiveTypeIdentifier */,
				st._171 /* V00*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedMultiplicity| = C00:MultiplicityCS[?] */
			});
		// EssentialOCL::TypeExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _103 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._317 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73 */,
				ms._318 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._316 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._161 /* assign V2 = |ownedMultiplicity| */,
				ms._079 /* assign V0 = |ownedCurlyBracketedClause| */,
				ms._028 /* assert (|ownedPathName| - 1) == 0 */,
				ms._126 /* assign V1 = |ownedPatternGuard| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._120 /* 1*ownedPathName=PathNameCS */,
				st._169 /* V00*next-5-steps */,
				st._085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
				st._181 /* V01*next-3-steps */,
				st._060 /* 1*'{' */,
				st._125 /* 1*ownedPatternGuard=ExpCS */,
				st._063 /* 1*'}' */,
				st._192 /* V02*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._9) /* ExpCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedPatternGuard| = C00[?] * C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedMultiplicity| = C02:MultiplicityCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedCurlyBracketedClause| = C00[?] */
			});
		// EssentialOCL::TypeLiteralExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _104 = new SerializationRule(111,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._315 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112 */,
				ms._039 /* assert (|ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._52) /* TypeLiteralWithMultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedType| = 1 */
			});
		// EssentialOCL::TypeLiteralExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _105 = new SerializationRule(111,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._315 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112 */,
				ms._039 /* assert (|ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._52) /* TypeLiteralWithMultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedType| = 1 */
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _106 = new SerializationRule(112,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._085 /* assign V0 = |ownedMultiplicity| */,
				ms._004 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-2-steps */,
				st._072 /* 1*name=PrimitiveTypeIdentifier */,
				st._171 /* V00*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedMultiplicity| = C00:MultiplicityCS[?] */
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _107 = new SerializationRule(112,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
				ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._161 /* assign V2 = |ownedMultiplicity| */,
				ms._091 /* assign V0 = |ownedType| */,
				ms._006 /* assert (|name| - 1) == 0 */,
				ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._070 /* 1*name=CollectionTypeIdentifier */,
				st._169 /* V00*next-4-steps */,
				st._003 /* 1*'(' */,
				st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
				st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
				st._004 /* 1*')' */,
				st._192 /* V02*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				ss._5 /* «! » + «value» */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._50) /* TypeExpWithoutMultiplicityCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedCollectionMultiplicity| = C00[?] * C01:MultiplicityCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedMultiplicity| = C02:MultiplicityCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _108 = new SerializationRule(112,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._177 /* assign V3 = |ownedMultiplicity| */,
				ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
				ms._064 /* assign V0 = (|ownedParts| > 0) */,
				ms._111 /* assign V1 = (|ownedParts| > 0) */,
				ms._152 /* assign V2 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._020 /* 1*'Tuple' */,
				st._169 /* V00*next-7-steps */,
				st._003 /* 1*'(' */,
				st._181 /* V01*next-4-steps */,
				st._116 /* 1*ownedParts+=TuplePartCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._116 /* 1*ownedParts+=TuplePartCS */,
				st._004 /* 1*')' */,
				st._198 /* V03*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._10)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._48) /* TuplePartCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name.'Tuple'| =
					E00.0: |name.'Tuple'| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedParts| = C00[?] * C01[?] + C00[?] * C01[?] * C02[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedMultiplicity| = C03:MultiplicityCS[?] */
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _109 = new SerializationRule(112,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
				ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._124 /* assign V1 = |ownedMultiplicity| */,
				ms._093 /* assign V0 = |ownedValueType| */,
				ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
				ms._002 /* assert (|name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._019 /* 1*'Map' */,
				st._169 /* V00*next-5-steps */,
				st._003 /* 1*'(' */,
				st._104 /* 1*ownedKeyType=TypeExpCS */,
				st._007 /* 1*',' */,
				st._145 /* 1*ownedValueType=TypeExpCS */,
				st._004 /* 1*')' */,
				st._186 /* V01*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedValueType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedKeyType| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedMultiplicity| = C01:MultiplicityCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name.'Map'| =
					E00.0: |name.'Map'| = 1 */
			});
		// EssentialOCL::TypeNameExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
		private @NonNull SerializationRule _110 = new SerializationRule(113,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._317 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73 */,
				ms._318 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30 */,
				ms._316 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._079 /* assign V0 = |ownedCurlyBracketedClause| */,
				ms._028 /* assert (|ownedPathName| - 1) == 0 */,
				ms._126 /* assign V1 = |ownedPatternGuard| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._120 /* 1*ownedPathName=PathNameCS */,
				st._169 /* V00*next-5-steps */,
				st._085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
				st._181 /* V01*next-3-steps */,
				st._060 /* 1*'{' */,
				st._125 /* 1*ownedPatternGuard=ExpCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedPatternGuard| = C00[?] * C01[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedCurlyBracketedClause| = C00[?] */
			});
		// EssentialOCL::TypeNameExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
		private @NonNull SerializationRule _111 = new SerializationRule(113,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._317 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73 */,
				ms._318 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30 */,
				ms._316 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._079 /* assign V0 = |ownedCurlyBracketedClause| */,
				ms._028 /* assert (|ownedPathName| - 1) == 0 */,
				ms._126 /* assign V1 = |ownedPatternGuard| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._120 /* 1*ownedPathName=PathNameCS */,
				st._169 /* V00*next-5-steps */,
				st._085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
				st._181 /* V01*next-3-steps */,
				st._060 /* 1*'{' */,
				st._125 /* 1*ownedPatternGuard=ExpCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._9) /* ExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedPatternGuard| = C00[?] * C01[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedCurlyBracketedClause| = C00[?] */
			});
		// EssentialOCL::URIFirstPathElementCS : referredElement=UnrestrictedName
		private @NonNull SerializationRule _112 = new SerializationRule(122,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._049 /* assert (|referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._150 /* 1*referredElement=UnrestrictedName */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |referredElement| = 1 */
			});
		// EssentialOCL::URIFirstPathElementCS : referredElement=URI
		private @NonNull SerializationRule _113 = new SerializationRule(122,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._049 /* assert (|referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._148 /* 1*referredElement=URI */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |referredElement| = 1 */
			});
		// EssentialOCL::URIPathNameCS : { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] }
		private @NonNull SerializationRule _114 = new SerializationRule(123,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._248 /* check-rule basecs::PathNameCS.ownedPathElements : 67|122 */,
				ms._065 /* assign V0 = (|ownedPathElements| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._119 /* 1*ownedPathElements+=URIFirstPathElementCS */,
				st._169 /* V00*next-2-steps */,
				st._010 /* 1*'::' */,
				st._118 /* 1*ownedPathElements+=NextPathElementCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._62) /* NextPathElementCS|URIFirstPathElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathElements| = 1 + C00[*] */
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS : '*'
		private @NonNull SerializationRule _115 = new SerializationRule(125,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._005 /* 1*'*' */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS : '*'
		private @NonNull SerializationRule _116 = new SerializationRule(125,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._005 /* 1*'*' */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
			});
		// OCLinEcore::AnnotationCS : { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' }
		private @NonNull SerializationRule _117 = new SerializationRule(1,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._229 /* check-rule basecs::AnnotationCS.ownedContents : 53 */,
				ms._230 /* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._200 /* assign V5 = |ownedReferences| */,
				ms._188 /* assign V4 = |ownedContents| */,
				ms._176 /* assign V3 = |ownedAnnotations| */,
				ms._076 /* assign V0 = |name| */,
				ms._104 /* assign V1 = (|ownedDetails| > 0) */,
				ms._149 /* assign V2 = (|ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-14-steps */,
				st._023 /* 1*'annotation' */,
				st._168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
				st._181 /* V01*next-6-steps */,
				st._003 /* 1*'(' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._004 /* 1*')' */,
				st._060 /* 1*'{' */,
				st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
				st._205 /* V04*ownedContents+=ModelElementCS */,
				st._211 /* V05*ownedReferences+=ModelElementRefCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					iv._17) /* ModelElementCS */,
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					iv._18) /* ModelElementRefCS */,
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedContents| = C04:ModelElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = C00:UnrestrictedName|SINGLE_QUOTED_STRING[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedReferences| = C05:ModelElementRefCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedDetails| = C01[?] + C01[?] * C02[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedAnnotations| = C03:AnnotationElementCS[+] */
			});
		// OCLinEcore::AnnotationCS : { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
		private @NonNull SerializationRule _118 = new SerializationRule(1,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._076 /* assign V0 = |name| */,
				ms._104 /* assign V1 = (|ownedDetails| > 0) */,
				ms._149 /* assign V2 = (|ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._023 /* 1*'annotation' */,
				st._168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
				st._181 /* V01*next-6-steps */,
				st._003 /* 1*'(' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._004 /* 1*')' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = C00:UnrestrictedName|SINGLE_QUOTED_STRING[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedDetails| = C01[?] + C01[?] * C02[*] */
			});
		// OCLinEcore::AnnotationCS : { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' }
		private @NonNull SerializationRule _119 = new SerializationRule(1,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._229 /* check-rule basecs::AnnotationCS.ownedContents : 53 */,
				ms._230 /* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._200 /* assign V5 = |ownedReferences| */,
				ms._188 /* assign V4 = |ownedContents| */,
				ms._176 /* assign V3 = |ownedAnnotations| */,
				ms._076 /* assign V0 = |name| */,
				ms._104 /* assign V1 = (|ownedDetails| > 0) */,
				ms._149 /* assign V2 = (|ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-14-steps */,
				st._023 /* 1*'annotation' */,
				st._168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
				st._181 /* V01*next-6-steps */,
				st._003 /* 1*'(' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._004 /* 1*')' */,
				st._060 /* 1*'{' */,
				st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
				st._205 /* V04*ownedContents+=ModelElementCS */,
				st._211 /* V05*ownedReferences+=ModelElementRefCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					iv._17) /* ModelElementCS */,
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					iv._18) /* ModelElementRefCS */,
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedContents| = C04:ModelElementCS[+] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = C00:UnrestrictedName|SINGLE_QUOTED_STRING[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedReferences| = C05:ModelElementRefCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedDetails| = C01[?] + C01[?] * C02[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedAnnotations| = C03:AnnotationElementCS[*] */
			});
		// OCLinEcore::AnnotationCS : { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' }
		private @NonNull SerializationRule _120 = new SerializationRule(1,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._229 /* check-rule basecs::AnnotationCS.ownedContents : 53 */,
				ms._230 /* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._200 /* assign V5 = |ownedReferences| */,
				ms._188 /* assign V4 = |ownedContents| */,
				ms._176 /* assign V3 = |ownedAnnotations| */,
				ms._076 /* assign V0 = |name| */,
				ms._104 /* assign V1 = (|ownedDetails| > 0) */,
				ms._149 /* assign V2 = (|ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-14-steps */,
				st._023 /* 1*'annotation' */,
				st._168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
				st._181 /* V01*next-6-steps */,
				st._003 /* 1*'(' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._004 /* 1*')' */,
				st._060 /* 1*'{' */,
				st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
				st._205 /* V04*ownedContents+=ModelElementCS */,
				st._211 /* V05*ownedReferences+=ModelElementRefCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					iv._17) /* ModelElementCS */,
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					iv._18) /* ModelElementRefCS */,
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedContents| = C04:ModelElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = C00:UnrestrictedName|SINGLE_QUOTED_STRING[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedReferences| = C05:ModelElementRefCS[+] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedDetails| = C01[?] + C01[?] * C02[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedAnnotations| = C03:AnnotationElementCS[*] */
			});
		// OCLinEcore::AttributeCS : { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _121 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._113 /* assign V1 = |default| */,
				ms._089 /* assign V0 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._154 /* assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._181 /* assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-14-steps */,
				st._024 /* 1*'attribute' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-4-steps */,
				st._060 /* 1*'{' */,
				st._196 /* V03*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._01)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |default| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| =
					E03.0: |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| = C02[?] * C03[+] */
			});
		// OCLinEcore::AttributeCS : { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _122 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._156 /* assign V2 = |default| */,
				ms._129 /* assign V1 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._096 /* assign V0 = |qualifiers.'static'| */,
				ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
				ms._171 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._192 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-16-steps */,
				st._028 /* 1*'definition' */,
				st._166 /* V00*'static' */,
				st._024 /* 1*'attribute' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._189 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._202 /* V04*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._01,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |default| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedType| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'definition'| =
					E00.0: |qualifiers.'definition'| = 1
					E00.1: |qualifiers.'static'| = C00[?]
					E00.2: |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| = C03[?] * C04[+] */
			});
		// OCLinEcore::AttributeCS : { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
		private @NonNull SerializationRule _123 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._196 /* assign V5 = |ownedAnnotations| */,
				ms._156 /* assign V2 = |default| */,
				ms._129 /* assign V1 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._096 /* assign V0 = |qualifiers.'static'| */,
				ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
				ms._171 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._192 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
				ms._209 /* assign V6 = |ownedDefaultExpressions| */,
				ms._210 /* assign V7 = (|ownedDefaultExpressions| > 0) */,
				ms._218 /* assign V8 = 0 */,
				ms._224 /* assign V9 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-28-steps */,
				st._028 /* 1*'definition' */,
				st._166 /* V00*'static' */,
				st._024 /* 1*'attribute' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._189 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._202 /* V04*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
				st._213 /* V06*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._220 /* V08*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._01,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |default| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedAnnotations| = C05:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedDefaultExpressions| = C06[*] * C07:SpecificationCS[?] + C08[*] * C09:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedType| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'definition'| =
					E00.0: |qualifiers.'definition'| = 1
					E00.1: |qualifiers.'static'| = C00[?]
					E00.2: |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| = C03[?] * C04[+] */
			});
		// OCLinEcore::AttributeCS : { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
		private @NonNull SerializationRule _124 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._186 /* assign V4 = |ownedAnnotations| */,
				ms._113 /* assign V1 = |default| */,
				ms._089 /* assign V0 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._154 /* assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._181 /* assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
				ms._198 /* assign V5 = |ownedDefaultExpressions| */,
				ms._202 /* assign V6 = (|ownedDefaultExpressions| > 0) */,
				ms._214 /* assign V7 = 0 */,
				ms._218 /* assign V8 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-26-steps */,
				st._024 /* 1*'attribute' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-4-steps */,
				st._060 /* 1*'{' */,
				st._196 /* V03*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
				st._207 /* V05*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._216 /* V06*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._217 /* V07*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._222 /* V08*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._01)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |default| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedAnnotations| = C04:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedDefaultExpressions| = C05[*] * C06:SpecificationCS[?] + C07[*] * C08:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| =
					E03.0: |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| = C02[?] * C03[+] */
			});
		// OCLinEcore::AttributeCS : { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
		private @NonNull SerializationRule _125 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._196 /* assign V5 = |ownedAnnotations| */,
				ms._156 /* assign V2 = |default| */,
				ms._129 /* assign V1 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._095 /* assign V0 = |qualifiers.'definition'| */,
				ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
				ms._171 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._192 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
				ms._209 /* assign V6 = |ownedDefaultExpressions| */,
				ms._210 /* assign V7 = (|ownedDefaultExpressions| > 0) */,
				ms._218 /* assign V8 = 0 */,
				ms._224 /* assign V9 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-28-steps */,
				st._056 /* 1*'static' */,
				st._164 /* V00*'definition' */,
				st._024 /* 1*'attribute' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._189 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._202 /* V04*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
				st._213 /* V06*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._220 /* V08*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._01,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |default| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedAnnotations| = C05:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedDefaultExpressions| = C06[*] * C07:SpecificationCS[?] + C08[*] * C09:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedType| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'static'| =
					E00.0: |qualifiers.'static'| = 1
					E00.1: |qualifiers.'definition'| = C00[?]
					E00.2: |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| = C03[?] * C04[+] */
			});
		// OCLinEcore::AttributeCS : { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _126 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._156 /* assign V2 = |default| */,
				ms._129 /* assign V1 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._095 /* assign V0 = |qualifiers.'definition'| */,
				ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
				ms._171 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._192 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-16-steps */,
				st._056 /* 1*'static' */,
				st._164 /* V00*'definition' */,
				st._024 /* 1*'attribute' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._189 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._202 /* V04*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._01,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |default| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedType| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'static'| =
					E00.0: |qualifiers.'static'| = 1
					E00.1: |qualifiers.'definition'| = C00[?]
					E00.2: |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| = C03[?] * C04[+] */
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
		private @NonNull SerializationRule _127 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._157 /* assign V2 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-11-steps */,
				st._165 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-2-steps */,
				st._060 /* 1*'{' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isPrimitive.'primitive'| =
					E00.0: |isPrimitive.'primitive'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |instanceClassName| = C02[?] */
			});
	}
	private class _SerializationRules2
	{
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
		private @NonNull SerializationRule _128 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._157 /* assign V2 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
				ms._166 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
				ms._185 /* assign V4 = |isSerializable.'serializable'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-12-steps */,
				st._165 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-3-steps */,
				st._060 /* 1*'{' */,
				st._201 /* V04*'serializable' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					ev._20),
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |isSerializable.'serializable'| =
					E04.0: |isSerializable.'serializable'| = C03[?] * C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isPrimitive.'primitive'| =
					E00.0: |isPrimitive.'primitive'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |instanceClassName| = C02[?] */
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
		private @NonNull SerializationRule _129 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._157 /* assign V2 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
				ms._166 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
				ms._185 /* assign V4 = |isSerializable.'serializable'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-12-steps */,
				st._165 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-3-steps */,
				st._060 /* 1*'{' */,
				st._201 /* V04*'serializable' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					ev._20),
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |isSerializable.'serializable'| =
					E04.0: |isSerializable.'serializable'| = C03[?] * C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isPrimitive.'primitive'| =
					E00.0: |isPrimitive.'primitive'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |instanceClassName| = C02[?] */
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
		private @NonNull SerializationRule _130 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._157 /* assign V2 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-12-steps */,
				st._165 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-3-steps */,
				st._060 /* 1*'{' */,
				st._000 /* 1*'!serializable' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isPrimitive.'primitive'| =
					E00.0: |isPrimitive.'primitive'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |instanceClassName| = C02[?] */
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _131 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._197 /* assign V5 = |ownedConstraints| */,
				ms._186 /* assign V4 = |ownedAnnotations| */,
				ms._157 /* assign V2 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-15-steps */,
				st._165 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-3-steps */,
				st._060 /* 1*'{' */,
				st._000 /* 1*'!serializable' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
				st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isPrimitive.'primitive'| =
					E00.0: |isPrimitive.'primitive'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedConstraints| = C05:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |instanceClassName| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedAnnotations| = C04:AnnotationElementCS[*] */
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _132 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._208 /* assign V6 = |ownedConstraints| */,
				ms._196 /* assign V5 = |ownedAnnotations| */,
				ms._157 /* assign V2 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
				ms._166 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
				ms._185 /* assign V4 = |isSerializable.'serializable'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-15-steps */,
				st._165 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-3-steps */,
				st._060 /* 1*'{' */,
				st._201 /* V04*'serializable' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
				st._215 /* V06*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					ev._20),
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |isSerializable.'serializable'| =
					E04.0: |isSerializable.'serializable'| = C03[?] * C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isPrimitive.'primitive'| =
					E00.0: |isPrimitive.'primitive'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |ownedConstraints| = C06:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |instanceClassName| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedAnnotations| = C05:AnnotationElementCS[*] */
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
		private @NonNull SerializationRule _133 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._157 /* assign V2 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-12-steps */,
				st._165 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-3-steps */,
				st._060 /* 1*'{' */,
				st._000 /* 1*'!serializable' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isPrimitive.'primitive'| =
					E00.0: |isPrimitive.'primitive'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |instanceClassName| = C02[?] */
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _134 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._197 /* assign V5 = |ownedConstraints| */,
				ms._186 /* assign V4 = |ownedAnnotations| */,
				ms._157 /* assign V2 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-15-steps */,
				st._165 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-3-steps */,
				st._060 /* 1*'{' */,
				st._000 /* 1*'!serializable' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
				st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isPrimitive.'primitive'| =
					E00.0: |isPrimitive.'primitive'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedConstraints| = C05:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |instanceClassName| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedAnnotations| = C04:AnnotationElementCS[*] */
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _135 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._208 /* assign V6 = |ownedConstraints| */,
				ms._196 /* assign V5 = |ownedAnnotations| */,
				ms._157 /* assign V2 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
				ms._166 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
				ms._185 /* assign V4 = |isSerializable.'serializable'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-15-steps */,
				st._165 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-3-steps */,
				st._060 /* 1*'{' */,
				st._201 /* V04*'serializable' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
				st._215 /* V06*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					ev._20),
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |isSerializable.'serializable'| =
					E04.0: |isSerializable.'serializable'| = C03[?] * C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isPrimitive.'primitive'| =
					E00.0: |isPrimitive.'primitive'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |ownedConstraints| = C06:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |instanceClassName| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedAnnotations| = C05:AnnotationElementCS[*] */
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _136 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._197 /* assign V5 = |ownedConstraints| */,
				ms._186 /* assign V4 = |ownedAnnotations| */,
				ms._157 /* assign V2 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-14-steps */,
				st._165 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-2-steps */,
				st._060 /* 1*'{' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
				st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isPrimitive.'primitive'| =
					E00.0: |isPrimitive.'primitive'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedConstraints| = C05:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |instanceClassName| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedAnnotations| = C04:AnnotationElementCS[*] */
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
		private @NonNull SerializationRule _137 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._157 /* assign V2 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-11-steps */,
				st._165 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-2-steps */,
				st._060 /* 1*'{' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isPrimitive.'primitive'| =
					E00.0: |isPrimitive.'primitive'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |instanceClassName| = C02[?] */
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _138 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._197 /* assign V5 = |ownedConstraints| */,
				ms._186 /* assign V4 = |ownedAnnotations| */,
				ms._157 /* assign V2 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-14-steps */,
				st._165 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-2-steps */,
				st._060 /* 1*'{' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
				st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isPrimitive.'primitive'| =
					E00.0: |isPrimitive.'primitive'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedConstraints| = C05:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |instanceClassName| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedAnnotations| = C04:AnnotationElementCS[*] */
			});
		// OCLinEcore::DetailCS : { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] }
		private @NonNull SerializationRule _139 = new SerializationRule(16,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._101 /* assign V0 = |values| */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-3-steps */,
				st._075 /* 1*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
				st._014 /* 1*'=' */,
				st._178 /* V00*values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.DETAIL_CS__VALUES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |values| = C00:SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */
			});
		// OCLinEcore::DocumentationCS : { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
		private @NonNull SerializationRule _140 = new SerializationRule(17,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._102 /* assign V0 = |value| */,
				ms._104 /* assign V1 = (|ownedDetails| > 0) */,
				ms._149 /* assign V2 = (|ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._030 /* 1*'documentation' */,
				st._177 /* V00*value=SINGLE_QUOTED_STRING */,
				st._181 /* V01*next-6-steps */,
				st._003 /* 1*'(' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._004 /* 1*')' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedDetails| = C01[?] + C01[?] * C02[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |value| = C00:SINGLE_QUOTED_STRING[?] */
			});
		// OCLinEcore::DocumentationCS : { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
		private @NonNull SerializationRule _141 = new SerializationRule(17,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._102 /* assign V0 = |value| */,
				ms._104 /* assign V1 = (|ownedDetails| > 0) */,
				ms._149 /* assign V2 = (|ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._030 /* 1*'documentation' */,
				st._177 /* V00*value=SINGLE_QUOTED_STRING */,
				st._181 /* V01*next-6-steps */,
				st._003 /* 1*'(' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._004 /* 1*')' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedDetails| = C01[?] + C01[?] * C02[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |value| = C00:SINGLE_QUOTED_STRING[?] */
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _142 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._197 /* assign V5 = |ownedConstraints| */,
				ms._189 /* assign V4 = |ownedLiterals| */,
				ms._176 /* assign V3 = |ownedAnnotations| */,
				ms._114 /* assign V1 = |instanceClassName| */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-15-steps */,
				st._034 /* 1*'enum' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-3-steps */,
				st._060 /* 1*'{' */,
				st._000 /* 1*'!serializable' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
				st._206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
				st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedLiterals| = C04:EnumerationLiteralCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedConstraints| = C05:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |instanceClassName| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedAnnotations| = C03:AnnotationElementCS[*] */
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _143 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._197 /* assign V5 = |ownedConstraints| */,
				ms._189 /* assign V4 = |ownedLiterals| */,
				ms._176 /* assign V3 = |ownedAnnotations| */,
				ms._114 /* assign V1 = |instanceClassName| */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-14-steps */,
				st._034 /* 1*'enum' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-2-steps */,
				st._060 /* 1*'{' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
				st._206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
				st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedLiterals| = C04:EnumerationLiteralCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedConstraints| = C05:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |instanceClassName| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedAnnotations| = C03:AnnotationElementCS[*] */
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
		private @NonNull SerializationRule _144 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._114 /* assign V1 = |instanceClassName| */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._148 /* assign V2 = (|isSerializable.'serializable'| > 0) */,
				ms._175 /* assign V3 = |isSerializable.'serializable'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-11-steps */,
				st._034 /* 1*'enum' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-3-steps */,
				st._060 /* 1*'{' */,
				st._195 /* V03*'serializable' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					ev._20)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |isSerializable.'serializable'| =
					E03.0: |isSerializable.'serializable'| = C02[?] * C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |instanceClassName| = C01[?] */
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _145 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._197 /* assign V5 = |ownedConstraints| */,
				ms._189 /* assign V4 = |ownedLiterals| */,
				ms._176 /* assign V3 = |ownedAnnotations| */,
				ms._114 /* assign V1 = |instanceClassName| */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-15-steps */,
				st._034 /* 1*'enum' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-3-steps */,
				st._060 /* 1*'{' */,
				st._000 /* 1*'!serializable' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
				st._206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
				st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedLiterals| = C04:EnumerationLiteralCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedConstraints| = C05:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |instanceClassName| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedAnnotations| = C03:AnnotationElementCS[*] */
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _146 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._208 /* assign V6 = |ownedConstraints| */,
				ms._199 /* assign V5 = |ownedLiterals| */,
				ms._186 /* assign V4 = |ownedAnnotations| */,
				ms._114 /* assign V1 = |instanceClassName| */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._148 /* assign V2 = (|isSerializable.'serializable'| > 0) */,
				ms._175 /* assign V3 = |isSerializable.'serializable'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-15-steps */,
				st._034 /* 1*'enum' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-3-steps */,
				st._060 /* 1*'{' */,
				st._195 /* V03*'serializable' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
				st._210 /* V05*ownedLiterals+=EnumerationLiteralCS */,
				st._215 /* V06*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					ev._20)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |isSerializable.'serializable'| =
					E03.0: |isSerializable.'serializable'| = C02[?] * C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedLiterals| = C05:EnumerationLiteralCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |ownedConstraints| = C06:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |instanceClassName| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedAnnotations| = C04:AnnotationElementCS[*] */
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
		private @NonNull SerializationRule _147 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._114 /* assign V1 = |instanceClassName| */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._034 /* 1*'enum' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-2-steps */,
				st._060 /* 1*'{' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |instanceClassName| = C01[?] */
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
		private @NonNull SerializationRule _148 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._114 /* assign V1 = |instanceClassName| */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._148 /* assign V2 = (|isSerializable.'serializable'| > 0) */,
				ms._175 /* assign V3 = |isSerializable.'serializable'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-11-steps */,
				st._034 /* 1*'enum' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-3-steps */,
				st._060 /* 1*'{' */,
				st._195 /* V03*'serializable' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					ev._20)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |isSerializable.'serializable'| =
					E03.0: |isSerializable.'serializable'| = C02[?] * C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |instanceClassName| = C01[?] */
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
		private @NonNull SerializationRule _149 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._114 /* assign V1 = |instanceClassName| */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-11-steps */,
				st._034 /* 1*'enum' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-3-steps */,
				st._060 /* 1*'{' */,
				st._000 /* 1*'!serializable' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |instanceClassName| = C01[?] */
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _150 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._208 /* assign V6 = |ownedConstraints| */,
				ms._199 /* assign V5 = |ownedLiterals| */,
				ms._186 /* assign V4 = |ownedAnnotations| */,
				ms._114 /* assign V1 = |instanceClassName| */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._148 /* assign V2 = (|isSerializable.'serializable'| > 0) */,
				ms._175 /* assign V3 = |isSerializable.'serializable'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-15-steps */,
				st._034 /* 1*'enum' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-3-steps */,
				st._060 /* 1*'{' */,
				st._195 /* V03*'serializable' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
				st._210 /* V05*ownedLiterals+=EnumerationLiteralCS */,
				st._215 /* V06*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					ev._20)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |isSerializable.'serializable'| =
					E03.0: |isSerializable.'serializable'| = C02[?] * C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedLiterals| = C05:EnumerationLiteralCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |ownedConstraints| = C06:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |instanceClassName| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedAnnotations| = C04:AnnotationElementCS[*] */
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
		private @NonNull SerializationRule _151 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._114 /* assign V1 = |instanceClassName| */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-11-steps */,
				st._034 /* 1*'enum' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-3-steps */,
				st._060 /* 1*'{' */,
				st._000 /* 1*'!serializable' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |instanceClassName| = C01[?] */
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
		private @NonNull SerializationRule _152 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._114 /* assign V1 = |instanceClassName| */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._034 /* 1*'enum' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-2-steps */,
				st._060 /* 1*'{' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |instanceClassName| = C01[?] */
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _153 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._197 /* assign V5 = |ownedConstraints| */,
				ms._189 /* assign V4 = |ownedLiterals| */,
				ms._176 /* assign V3 = |ownedAnnotations| */,
				ms._114 /* assign V1 = |instanceClassName| */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-14-steps */,
				st._034 /* 1*'enum' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._189 /* V02*next-2-steps */,
				st._060 /* 1*'{' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
				st._206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
				st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedLiterals| = C04:EnumerationLiteralCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedConstraints| = C05:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |instanceClassName| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedAnnotations| = C03:AnnotationElementCS[*] */
			});
		// OCLinEcore::EnumerationLiteralCS : { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
		private @NonNull SerializationRule _154 = new SerializationRule(22,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._158 /* assign V2 = |ownedAnnotations| */,
				ms._133 /* assign V1 = |value| */,
				ms._075 /* assign V0 = |literal| */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-11-steps */,
				st._044 /* 1*'literal' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*literal=SINGLE_QUOTED_STRING */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._161 /* 1*value=SIGNED */,
				st._060 /* 1*'{' */,
				st._190 /* V02*ownedAnnotations+=AnnotationElementCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |value| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |literal| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedAnnotations| = C02:AnnotationElementCS[*] */
			});
		// OCLinEcore::EnumerationLiteralCS : { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
		private @NonNull SerializationRule _155 = new SerializationRule(22,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._133 /* assign V1 = |value| */,
				ms._075 /* assign V0 = |literal| */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-9-steps */,
				st._044 /* 1*'literal' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*literal=SINGLE_QUOTED_STRING */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._161 /* 1*value=SIGNED */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |value| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |literal| = C00[?] */
			});
		// OCLinEcore::EnumerationLiteralCS : { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
		private @NonNull SerializationRule _156 = new SerializationRule(22,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._133 /* assign V1 = |value| */,
				ms._075 /* assign V0 = |literal| */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._071 /* 1*name=EnumerationLiteralName */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*literal=SINGLE_QUOTED_STRING */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._161 /* 1*value=SIGNED */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |value| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |literal| = C00[?] */
			});
		// OCLinEcore::EnumerationLiteralCS : { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
		private @NonNull SerializationRule _157 = new SerializationRule(22,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._158 /* assign V2 = |ownedAnnotations| */,
				ms._133 /* assign V1 = |value| */,
				ms._075 /* assign V0 = |literal| */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._071 /* 1*name=EnumerationLiteralName */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*literal=SINGLE_QUOTED_STRING */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._161 /* 1*value=SIGNED */,
				st._060 /* 1*'{' */,
				st._190 /* V02*ownedAnnotations+=AnnotationElementCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |value| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |literal| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedAnnotations| = C02:AnnotationElementCS[*] */
			});
		// OCLinEcore::ImplicitOppositeCS : { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] }
		private @NonNull SerializationRule _158 = new SerializationRule(37,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._041 /* assert (|ownedType| - 1) == 0 */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._069 /* assign V0 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
				ms._131 /* assign V1 = |qualifiers.'!ordered|!unique|ordered|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-9-steps */,
				st._048 /* 1*'opposite' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._169 /* V00*next-4-steps */,
				st._060 /* 1*'{' */,
				st._181 /* V01*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._03)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |qualifiers.'!ordered|!unique|ordered|unique'| =
					E02.0: |qualifiers.'!ordered|!unique|ordered|unique'| = C00[?] * C01[+] */
			});
		// OCLinEcore::ImportCS : { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' }
		private @NonNull SerializationRule _159 = new SerializationRule(38,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._236 /* check-rule basecs::ImportCS.ownedPathName : 123 */,
				ms._115 /* assign V1 = |isAll.'::*'| */,
				ms._030 /* assert (|ownedPathName| - 1) == 0 */,
				ms._076 /* assign V0 = |name| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._037 /* 1*'import' */,
				st._169 /* V00*next-2-steps */,
				st._074 /* 1*name=UnrestrictedName */,
				st._009 /* 1*':' */,
				st._124 /* 1*ownedPathName=URIPathNameCS */,
				st._179 /* V01*'::*' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
					ev._06)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					iv._64) /* URIPathNameCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedPathName| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |isAll.'::*'| =
					E02.0: |isAll.'::*'| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = C00[?] */
			});
		// OCLinEcore::InvariantConstraintCS : { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' }
		private @NonNull SerializationRule _160 = new SerializationRule(41,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
				ms._117 /* assign V1 = |name| */,
				ms._051 /* assert (|stereotype.'invariant'| - 1) == 0 */,
				ms._072 /* assign V0 = |isCallable.'callable'| */,
				ms._160 /* assign V2 = |ownedMessageSpecification| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-9-steps */,
				st._163 /* V00*'callable' */,
				st._041 /* 1*'invariant' */,
				st._181 /* V01*next-5-steps */,
				st._074 /* 1*name=UnrestrictedName */,
				st._189 /* V02*next-3-steps */,
				st._003 /* 1*'(' */,
				st._107 /* 1*ownedMessageSpecification=SpecificationCS */,
				st._004 /* 1*')' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._5 /* «! » + «value» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._16),
				new EAttributeData(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					ev._12)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._36) /* SpecificationCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |stereotype.'invariant'| =
					E01.0: |stereotype.'invariant'| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |name| = C01[?] */,
				new EStructuralFeatureData(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isCallable.'callable'| =
					E00.0: |isCallable.'callable'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedMessageSpecification| = C01[?] * C02[?] */
			});
		// OCLinEcore::InvariantConstraintCS : { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
		private @NonNull SerializationRule _161 = new SerializationRule(41,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._234 /* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
				ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
				ms._179 /* assign V3 = |ownedSpecification| */,
				ms._117 /* assign V1 = |name| */,
				ms._051 /* assert (|stereotype.'invariant'| - 1) == 0 */,
				ms._072 /* assign V0 = |isCallable.'callable'| */,
				ms._160 /* assign V2 = |ownedMessageSpecification| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-11-steps */,
				st._163 /* V00*'callable' */,
				st._041 /* 1*'invariant' */,
				st._181 /* V01*next-5-steps */,
				st._074 /* 1*name=UnrestrictedName */,
				st._189 /* V02*next-3-steps */,
				st._003 /* 1*'(' */,
				st._107 /* 1*ownedMessageSpecification=SpecificationCS */,
				st._004 /* 1*')' */,
				st._009 /* 1*':' */,
				st._200 /* V03*ownedSpecification=SpecificationCS */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._5 /* «! » + «value» */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._16),
				new EAttributeData(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					ev._12)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._36) /* SpecificationCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |stereotype.'invariant'| =
					E01.0: |stereotype.'invariant'| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedSpecification| = C03:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |name| = C01[?] */,
				new EStructuralFeatureData(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isCallable.'callable'| =
					E00.0: |isCallable.'callable'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedMessageSpecification| = C01[?] * C02[?] */
			});
		// OCLinEcore::ModelElementRefCS : { 'reference' ownedPathName=PathNameCS ';' }
		private @NonNull SerializationRule _162 = new SerializationRule(54,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._238 /* check-rule basecs::ModelElementRefCS.ownedPathName : 73 */,
				ms._032 /* assert (|ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-3-steps */,
				st._054 /* 1*'reference' */,
				st._122 /* 1*ownedPathName=PathNameCS */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */
			});
		// OCLinEcore::OperationCS : { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
		private @NonNull SerializationRule _163 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
				ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._145 /* assign V12 = |ownedPostconditions| */,
				ms._227 /* assign V9 = |ownedPreconditions| */,
				ms._219 /* assign V8 = |ownedAnnotations| */,
				ms._180 /* assign V3 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._134 /* assign V10 = (|ownedBodyExpressions| > 0) */,
				ms._141 /* assign V11 = |ownedBodyExpressions| */,
				ms._204 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._216 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
				ms._182 /* assign V4 = (|ownedExceptions| > 0) */,
				ms._194 /* assign V5 = (|ownedExceptions| - 1) */,
				ms._106 /* assign V1 = (|ownedParameters| > 0) */,
				ms._150 /* assign V2 = (|ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-34-steps */,
				st._047 /* 1*'operation' */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._074 /* 1*name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._181 /* V01*next-4-steps */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._196 /* V03*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._202 /* V04*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._207 /* V05*next-2-steps */,
				st._007 /* 1*',' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._213 /* V06*next-4-steps */,
				st._060 /* 1*'{' */,
				st._217 /* V07*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._221 /* V08*ownedAnnotations+=AnnotationElementCS */,
				st._227 /* V09*ownedPreconditions+=PreconditionConstraintCS */,
				st._229 /* V10*next-4-steps */,
				st._025 /* 1*'body' */,
				st._009 /* 1*':' */,
				st._234 /* V11*ownedBodyExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._239 /* V12*ownedPostconditions+=PostconditionConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._30) /* PreconditionConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._29) /* PostconditionConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedPreconditions| = C09:PreconditionConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedParameters| = C01[?] + C01[?] * C02[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E09: |ownedPostconditions| = C12:PostconditionConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedBodyExpressions| = C10[*] * C11:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedExceptions| = C04[?] + C04[?] * C05[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |ownedAnnotations| = C08:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedType| = C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| =
					E05.0: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| = C06[?] * C07[+] */
			});
		// OCLinEcore::OperationCS : { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
		private @NonNull SerializationRule _164 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
				ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._147 /* assign V13 = |ownedPostconditions| */,
				ms._138 /* assign V10 = |ownedPreconditions| */,
				ms._225 /* assign V9 = |ownedAnnotations| */,
				ms._190 /* assign V4 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._095 /* assign V0 = |qualifiers.'definition'| */,
				ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
				ms._139 /* assign V11 = (|ownedBodyExpressions| > 0) */,
				ms._143 /* assign V12 = |ownedBodyExpressions| */,
				ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
				ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
				ms._151 /* assign V2 = (|ownedParameters| > 0) */,
				ms._168 /* assign V3 = (|ownedParameters| - 1) */,
				ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-36-steps */,
				st._056 /* 1*'static' */,
				st._164 /* V00*'definition' */,
				st._047 /* 1*'operation' */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._074 /* 1*name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._189 /* V02*next-4-steps */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._196 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._202 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._207 /* V05*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._213 /* V06*next-2-steps */,
				st._007 /* 1*',' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._217 /* V07*next-4-steps */,
				st._060 /* 1*'{' */,
				st._220 /* V08*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._225 /* V09*ownedAnnotations+=AnnotationElementCS */,
				st._232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
				st._233 /* V11*next-4-steps */,
				st._025 /* 1*'body' */,
				st._009 /* 1*':' */,
				st._237 /* V12*ownedBodyExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._02,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._30) /* PreconditionConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._29) /* PostconditionConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedPreconditions| = C10:PreconditionConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedParameters| = C02[?] + C02[?] * C03[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E09: |ownedPostconditions| = C13:PostconditionConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedBodyExpressions| = C11[*] * C12:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedExceptions| = C05[?] + C05[?] * C06[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |ownedAnnotations| = C09:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedType| = C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'static'| =
					E00.0: |qualifiers.'static'| = 1
					E00.1: |qualifiers.'definition'| = C00[?]
					E00.2: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| = C07[?] * C08[+] */
			});
		// OCLinEcore::OperationCS : { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _165 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._190 /* assign V4 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._096 /* assign V0 = |qualifiers.'static'| */,
				ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
				ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
				ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
				ms._151 /* assign V2 = (|ownedParameters| > 0) */,
				ms._168 /* assign V3 = (|ownedParameters| - 1) */,
				ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-27-steps */,
				st._028 /* 1*'definition' */,
				st._166 /* V00*'static' */,
				st._047 /* 1*'operation' */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._074 /* 1*name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._189 /* V02*next-4-steps */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._196 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._202 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._207 /* V05*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._213 /* V06*next-2-steps */,
				st._007 /* 1*',' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._217 /* V07*next-4-steps */,
				st._060 /* 1*'{' */,
				st._220 /* V08*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._02,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedParameters| = C02[?] + C02[?] * C03[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedExceptions| = C05[?] + C05[?] * C06[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedType| = C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'definition'| =
					E00.0: |qualifiers.'definition'| = 1
					E00.1: |qualifiers.'static'| = C00[?]
					E00.2: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| = C07[?] * C08[+] */
			});
		// OCLinEcore::OperationCS : { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _166 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._190 /* assign V4 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._095 /* assign V0 = |qualifiers.'definition'| */,
				ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
				ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
				ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
				ms._151 /* assign V2 = (|ownedParameters| > 0) */,
				ms._168 /* assign V3 = (|ownedParameters| - 1) */,
				ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-27-steps */,
				st._056 /* 1*'static' */,
				st._164 /* V00*'definition' */,
				st._047 /* 1*'operation' */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._074 /* 1*name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._189 /* V02*next-4-steps */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._196 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._202 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._207 /* V05*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._213 /* V06*next-2-steps */,
				st._007 /* 1*',' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._217 /* V07*next-4-steps */,
				st._060 /* 1*'{' */,
				st._220 /* V08*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._02,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedParameters| = C02[?] + C02[?] * C03[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedExceptions| = C05[?] + C05[?] * C06[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedType| = C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'static'| =
					E00.0: |qualifiers.'static'| = 1
					E00.1: |qualifiers.'definition'| = C00[?]
					E00.2: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| = C07[?] * C08[+] */
			});
		// OCLinEcore::OperationCS : { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
		private @NonNull SerializationRule _167 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
				ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._147 /* assign V13 = |ownedPostconditions| */,
				ms._138 /* assign V10 = |ownedPreconditions| */,
				ms._225 /* assign V9 = |ownedAnnotations| */,
				ms._190 /* assign V4 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._096 /* assign V0 = |qualifiers.'static'| */,
				ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
				ms._139 /* assign V11 = (|ownedBodyExpressions| > 0) */,
				ms._143 /* assign V12 = |ownedBodyExpressions| */,
				ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
				ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
				ms._151 /* assign V2 = (|ownedParameters| > 0) */,
				ms._168 /* assign V3 = (|ownedParameters| - 1) */,
				ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-36-steps */,
				st._028 /* 1*'definition' */,
				st._166 /* V00*'static' */,
				st._047 /* 1*'operation' */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._074 /* 1*name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._189 /* V02*next-4-steps */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._196 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._202 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._207 /* V05*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._213 /* V06*next-2-steps */,
				st._007 /* 1*',' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._217 /* V07*next-4-steps */,
				st._060 /* 1*'{' */,
				st._220 /* V08*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._225 /* V09*ownedAnnotations+=AnnotationElementCS */,
				st._232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
				st._233 /* V11*next-4-steps */,
				st._025 /* 1*'body' */,
				st._009 /* 1*':' */,
				st._237 /* V12*ownedBodyExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._02,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._30) /* PreconditionConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._29) /* PostconditionConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedPreconditions| = C10:PreconditionConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedParameters| = C02[?] + C02[?] * C03[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E09: |ownedPostconditions| = C13:PostconditionConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedBodyExpressions| = C11[*] * C12:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedExceptions| = C05[?] + C05[?] * C06[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |ownedAnnotations| = C09:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedType| = C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'definition'| =
					E00.0: |qualifiers.'definition'| = 1
					E00.1: |qualifiers.'static'| = C00[?]
					E00.2: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| = C07[?] * C08[+] */
			});
		// OCLinEcore::OperationCS : { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _168 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._180 /* assign V3 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._204 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._216 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
				ms._182 /* assign V4 = (|ownedExceptions| > 0) */,
				ms._194 /* assign V5 = (|ownedExceptions| - 1) */,
				ms._106 /* assign V1 = (|ownedParameters| > 0) */,
				ms._150 /* assign V2 = (|ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-25-steps */,
				st._047 /* 1*'operation' */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._074 /* 1*name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._181 /* V01*next-4-steps */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._196 /* V03*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._202 /* V04*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._207 /* V05*next-2-steps */,
				st._007 /* 1*',' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._213 /* V06*next-4-steps */,
				st._060 /* 1*'{' */,
				st._217 /* V07*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedParameters| = C01[?] + C01[?] * C02[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedExceptions| = C04[?] + C04[?] * C05[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedType| = C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| =
					E05.0: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| = C06[?] * C07[+] */
			});
		// OCLinEcore::OperationCS : { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _169 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._180 /* assign V3 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._204 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._216 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
				ms._182 /* assign V4 = (|ownedExceptions| > 0) */,
				ms._194 /* assign V5 = (|ownedExceptions| - 1) */,
				ms._106 /* assign V1 = (|ownedParameters| > 0) */,
				ms._150 /* assign V2 = (|ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-25-steps */,
				st._047 /* 1*'operation' */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._074 /* 1*name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._181 /* V01*next-4-steps */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._196 /* V03*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._202 /* V04*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._207 /* V05*next-2-steps */,
				st._007 /* 1*',' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._213 /* V06*next-4-steps */,
				st._060 /* 1*'{' */,
				st._217 /* V07*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedParameters| = C01[?] + C01[?] * C02[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedExceptions| = C04[?] + C04[?] * C05[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedType| = C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| =
					E05.0: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| = C06[?] * C07[+] */
			});
		// OCLinEcore::OperationCS : { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
		private @NonNull SerializationRule _170 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
				ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._145 /* assign V12 = |ownedPostconditions| */,
				ms._227 /* assign V9 = |ownedPreconditions| */,
				ms._219 /* assign V8 = |ownedAnnotations| */,
				ms._180 /* assign V3 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._087 /* assign V0 = |ownedSignature| */,
				ms._134 /* assign V10 = (|ownedBodyExpressions| > 0) */,
				ms._141 /* assign V11 = |ownedBodyExpressions| */,
				ms._204 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._216 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
				ms._182 /* assign V4 = (|ownedExceptions| > 0) */,
				ms._194 /* assign V5 = (|ownedExceptions| - 1) */,
				ms._106 /* assign V1 = (|ownedParameters| > 0) */,
				ms._150 /* assign V2 = (|ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-34-steps */,
				st._047 /* 1*'operation' */,
				st._173 /* V00*ownedSignature=TemplateSignatureCS */,
				st._074 /* 1*name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._181 /* V01*next-4-steps */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._196 /* V03*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._202 /* V04*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._207 /* V05*next-2-steps */,
				st._007 /* 1*',' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._213 /* V06*next-4-steps */,
				st._060 /* 1*'{' */,
				st._217 /* V07*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._221 /* V08*ownedAnnotations+=AnnotationElementCS */,
				st._227 /* V09*ownedPreconditions+=PreconditionConstraintCS */,
				st._229 /* V10*next-4-steps */,
				st._025 /* 1*'body' */,
				st._009 /* 1*':' */,
				st._234 /* V11*ownedBodyExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._239 /* V12*ownedPostconditions+=PostconditionConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._30) /* PreconditionConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._29) /* PostconditionConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedPreconditions| = C09:PreconditionConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedParameters| = C01[?] + C01[?] * C02[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E09: |ownedPostconditions| = C12:PostconditionConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedBodyExpressions| = C10[*] * C11:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedExceptions| = C04[?] + C04[?] * C05[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedSignature| = C00:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |ownedAnnotations| = C08:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedType| = C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| =
					E05.0: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| = C06[?] * C07[+] */
			});
		// OCLinEcore::OperationCS : { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
		private @NonNull SerializationRule _171 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
				ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._147 /* assign V13 = |ownedPostconditions| */,
				ms._138 /* assign V10 = |ownedPreconditions| */,
				ms._225 /* assign V9 = |ownedAnnotations| */,
				ms._190 /* assign V4 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._095 /* assign V0 = |qualifiers.'definition'| */,
				ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
				ms._139 /* assign V11 = (|ownedBodyExpressions| > 0) */,
				ms._143 /* assign V12 = |ownedBodyExpressions| */,
				ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
				ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
				ms._151 /* assign V2 = (|ownedParameters| > 0) */,
				ms._168 /* assign V3 = (|ownedParameters| - 1) */,
				ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-36-steps */,
				st._056 /* 1*'static' */,
				st._164 /* V00*'definition' */,
				st._047 /* 1*'operation' */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._074 /* 1*name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._189 /* V02*next-4-steps */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._196 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._202 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._207 /* V05*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._213 /* V06*next-2-steps */,
				st._007 /* 1*',' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._217 /* V07*next-4-steps */,
				st._060 /* 1*'{' */,
				st._220 /* V08*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._225 /* V09*ownedAnnotations+=AnnotationElementCS */,
				st._232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
				st._233 /* V11*next-4-steps */,
				st._025 /* 1*'body' */,
				st._009 /* 1*':' */,
				st._237 /* V12*ownedBodyExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._02,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._30) /* PreconditionConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._29) /* PostconditionConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedPreconditions| = C10:PreconditionConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedParameters| = C02[?] + C02[?] * C03[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E09: |ownedPostconditions| = C13:PostconditionConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedBodyExpressions| = C11[*] * C12:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedExceptions| = C05[?] + C05[?] * C06[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |ownedAnnotations| = C09:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedType| = C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'static'| =
					E00.0: |qualifiers.'static'| = 1
					E00.1: |qualifiers.'definition'| = C00[?]
					E00.2: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| = C07[?] * C08[+] */
			});
		// OCLinEcore::OperationCS : { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _172 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._190 /* assign V4 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._095 /* assign V0 = |qualifiers.'definition'| */,
				ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
				ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
				ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
				ms._151 /* assign V2 = (|ownedParameters| > 0) */,
				ms._168 /* assign V3 = (|ownedParameters| - 1) */,
				ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-27-steps */,
				st._056 /* 1*'static' */,
				st._164 /* V00*'definition' */,
				st._047 /* 1*'operation' */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._074 /* 1*name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._189 /* V02*next-4-steps */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._196 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._202 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._207 /* V05*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._213 /* V06*next-2-steps */,
				st._007 /* 1*',' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._217 /* V07*next-4-steps */,
				st._060 /* 1*'{' */,
				st._220 /* V08*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._02,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedParameters| = C02[?] + C02[?] * C03[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedExceptions| = C05[?] + C05[?] * C06[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedType| = C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'static'| =
					E00.0: |qualifiers.'static'| = 1
					E00.1: |qualifiers.'definition'| = C00[?]
					E00.2: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| = C07[?] * C08[+] */
			});
		// OCLinEcore::OperationCS : { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
		private @NonNull SerializationRule _173 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
				ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._147 /* assign V13 = |ownedPostconditions| */,
				ms._138 /* assign V10 = |ownedPreconditions| */,
				ms._225 /* assign V9 = |ownedAnnotations| */,
				ms._190 /* assign V4 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._096 /* assign V0 = |qualifiers.'static'| */,
				ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
				ms._139 /* assign V11 = (|ownedBodyExpressions| > 0) */,
				ms._143 /* assign V12 = |ownedBodyExpressions| */,
				ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
				ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
				ms._151 /* assign V2 = (|ownedParameters| > 0) */,
				ms._168 /* assign V3 = (|ownedParameters| - 1) */,
				ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-36-steps */,
				st._028 /* 1*'definition' */,
				st._166 /* V00*'static' */,
				st._047 /* 1*'operation' */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._074 /* 1*name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._189 /* V02*next-4-steps */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._196 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._202 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._207 /* V05*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._213 /* V06*next-2-steps */,
				st._007 /* 1*',' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._217 /* V07*next-4-steps */,
				st._060 /* 1*'{' */,
				st._220 /* V08*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._225 /* V09*ownedAnnotations+=AnnotationElementCS */,
				st._232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
				st._233 /* V11*next-4-steps */,
				st._025 /* 1*'body' */,
				st._009 /* 1*':' */,
				st._237 /* V12*ownedBodyExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._02,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._30) /* PreconditionConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._29) /* PostconditionConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedPreconditions| = C10:PreconditionConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedParameters| = C02[?] + C02[?] * C03[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E09: |ownedPostconditions| = C13:PostconditionConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedBodyExpressions| = C11[*] * C12:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedExceptions| = C05[?] + C05[?] * C06[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |ownedAnnotations| = C09:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedType| = C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'definition'| =
					E00.0: |qualifiers.'definition'| = 1
					E00.1: |qualifiers.'static'| = C00[?]
					E00.2: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| = C07[?] * C08[+] */
			});
		// OCLinEcore::OperationCS : { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _174 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._190 /* assign V4 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._096 /* assign V0 = |qualifiers.'static'| */,
				ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
				ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
				ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
				ms._151 /* assign V2 = (|ownedParameters| > 0) */,
				ms._168 /* assign V3 = (|ownedParameters| - 1) */,
				ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-27-steps */,
				st._028 /* 1*'definition' */,
				st._166 /* V00*'static' */,
				st._047 /* 1*'operation' */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._074 /* 1*name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._189 /* V02*next-4-steps */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._196 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._109 /* 1*ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._202 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._207 /* V05*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._213 /* V06*next-2-steps */,
				st._007 /* 1*',' */,
				st._088 /* 1*ownedExceptions+=TypedRefCS */,
				st._217 /* V07*next-4-steps */,
				st._060 /* 1*'{' */,
				st._220 /* V08*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._02,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReferenceData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedParameters| = C02[?] + C02[?] * C03[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedExceptions| = C05[?] + C05[?] * C06[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedType| = C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'definition'| =
					E00.0: |qualifiers.'definition'| = 1
					E00.1: |qualifiers.'static'| = C00[?]
					E00.2: |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| = C07[?] * C08[+] */
			});
		// OCLinEcore::PackageCS : { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' }
		private @NonNull SerializationRule _175 = new SerializationRule(71,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._245 /* check-rule basecs::PackageOwnerCS.ownedPackages : 71 */,
				ms._244 /* check-rule basecs::PackageCS.ownedClasses : 6 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._187 /* assign V4 = |ownedClasses| */,
				ms._178 /* assign V3 = |ownedPackages| */,
				ms._158 /* assign V2 = |ownedAnnotations| */,
				ms._118 /* assign V1 = |nsURI| */,
				ms._077 /* assign V0 = |nsPrefix| */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-13-steps */,
				st._049 /* 1*'package' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._077 /* 1*nsPrefix=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._078 /* 1*nsURI=URI */,
				st._060 /* 1*'{' */,
				st._190 /* V02*ownedAnnotations+=AnnotationElementCS */,
				st._199 /* V03*ownedPackages+=PackageCS */,
				st._204 /* V04*ownedClasses+=ClassCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					iv._24) /* PackageCS */,
				new EReferenceData(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					iv._1) /* ClassCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedPackages| = C03:PackageCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedClasses| = C04:ClassCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |nsPrefix| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.PACKAGE_CS__NS_URI,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |nsURI| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedAnnotations| = C02:AnnotationElementCS[*] */
			});
		// OCLinEcore::PackageCS : { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' }
		private @NonNull SerializationRule _176 = new SerializationRule(71,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._118 /* assign V1 = |nsURI| */,
				ms._077 /* assign V0 = |nsPrefix| */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-9-steps */,
				st._049 /* 1*'package' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._077 /* 1*nsPrefix=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._078 /* 1*nsURI=URI */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |nsPrefix| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.PACKAGE_CS__NS_URI,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |nsURI| = C01[?] */
			});
		// OCLinEcore::PackageCS : { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' }
		private @NonNull SerializationRule _177 = new SerializationRule(71,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._118 /* assign V1 = |nsURI| */,
				ms._077 /* assign V0 = |nsPrefix| */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-9-steps */,
				st._049 /* 1*'package' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._077 /* 1*nsPrefix=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._078 /* 1*nsURI=URI */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |nsPrefix| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.PACKAGE_CS__NS_URI,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |nsURI| = C01[?] */
			});
		// OCLinEcore::PackageCS : { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' }
		private @NonNull SerializationRule _178 = new SerializationRule(71,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._245 /* check-rule basecs::PackageOwnerCS.ownedPackages : 71 */,
				ms._244 /* check-rule basecs::PackageCS.ownedClasses : 6 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._187 /* assign V4 = |ownedClasses| */,
				ms._178 /* assign V3 = |ownedPackages| */,
				ms._158 /* assign V2 = |ownedAnnotations| */,
				ms._118 /* assign V1 = |nsURI| */,
				ms._077 /* assign V0 = |nsPrefix| */,
				ms._005 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-13-steps */,
				st._049 /* 1*'package' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._077 /* 1*nsPrefix=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._078 /* 1*nsURI=URI */,
				st._060 /* 1*'{' */,
				st._190 /* V02*ownedAnnotations+=AnnotationElementCS */,
				st._199 /* V03*ownedPackages+=PackageCS */,
				st._204 /* V04*ownedClasses+=ClassCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					iv._24) /* PackageCS */,
				new EReferenceData(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					iv._1) /* ClassCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |ownedPackages| = C03:PackageCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedClasses| = C04:ClassCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |nsPrefix| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.PACKAGE_CS__NS_URI,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |nsURI| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedAnnotations| = C02:AnnotationElementCS[*] */
			});
		// OCLinEcore::ParameterCS : { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] }
		private @NonNull SerializationRule _179 = new SerializationRule(72,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._089 /* assign V0 = |ownedType| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._167 /* assign V3 = (|ownedAnnotations| > 0) */,
				ms._186 /* assign V4 = |ownedAnnotations| */,
				ms._112 /* assign V1 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
				ms._165 /* assign V2 = |qualifiers.'!ordered|!unique|ordered|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-13-steps */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._181 /* V01*next-4-steps */,
				st._060 /* 1*'{' */,
				st._189 /* V02*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._196 /* V03*next-3-steps */,
				st._060 /* 1*'{' */,
				st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._03)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedAnnotations| = C03[?] * C04:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |qualifiers.'!ordered|!unique|ordered|unique'| =
					E02.0: |qualifiers.'!ordered|!unique|ordered|unique'| = C01[?] * C02[+] */
			});
		// OCLinEcore::PostconditionConstraintCS : { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
		private @NonNull SerializationRule _180 = new SerializationRule(75,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._234 /* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
				ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
				ms._163 /* assign V2 = |ownedSpecification| */,
				ms._076 /* assign V0 = |name| */,
				ms._052 /* assert (|stereotype.'postcondition'| - 1) == 0 */,
				ms._123 /* assign V1 = |ownedMessageSpecification| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._050 /* 1*'postcondition' */,
				st._169 /* V00*next-5-steps */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-3-steps */,
				st._003 /* 1*'(' */,
				st._107 /* 1*ownedMessageSpecification=SpecificationCS */,
				st._004 /* 1*')' */,
				st._009 /* 1*':' */,
				st._194 /* V02*ownedSpecification=SpecificationCS */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._5 /* «! » + «value» */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._17)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._36) /* SpecificationCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |stereotype.'postcondition'| =
					E00.0: |stereotype.'postcondition'| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedSpecification| = C02:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedMessageSpecification| = C00[?] * C01[?] */
			});
		// OCLinEcore::PreconditionConstraintCS : { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
		private @NonNull SerializationRule _181 = new SerializationRule(76,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._234 /* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
				ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
				ms._163 /* assign V2 = |ownedSpecification| */,
				ms._076 /* assign V0 = |name| */,
				ms._053 /* assert (|stereotype.'precondition'| - 1) == 0 */,
				ms._123 /* assign V1 = |ownedMessageSpecification| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._052 /* 1*'precondition' */,
				st._169 /* V00*next-5-steps */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-3-steps */,
				st._003 /* 1*'(' */,
				st._107 /* 1*ownedMessageSpecification=SpecificationCS */,
				st._004 /* 1*')' */,
				st._009 /* 1*':' */,
				st._194 /* V02*ownedSpecification=SpecificationCS */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._5 /* «! » + «value» */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._18)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._36) /* SpecificationCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |stereotype.'precondition'| =
					E00.0: |stereotype.'precondition'| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedSpecification| = C02:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedMessageSpecification| = C00[?] * C01[?] */
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _182 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._173 /* assign V3 = |default| */,
				ms._164 /* assign V2 = |ownedType| */,
				ms._132 /* assign V1 = |referredOpposite| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._095 /* assign V0 = |qualifiers.'definition'| */,
				ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
				ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-19-steps */,
				st._056 /* 1*'static' */,
				st._164 /* V00*'definition' */,
				st._053 /* 1*'property' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._001 /* 1*'#' */,
				st._154 /* 1*referredOpposite=UnrestrictedName */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._196 /* V03*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._202 /* V04*next-4-steps */,
				st._060 /* 1*'{' */,
				st._207 /* V05*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._00,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |referredOpposite| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |default| = C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedType| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'static'| =
					E00.0: |qualifiers.'static'| = 1
					E00.1: |qualifiers.'definition'| = C00[?]
					E00.2: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| = C04[?] * C05[+] */
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
		private @NonNull SerializationRule _183 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
				ms._146 /* assign V13 = |ownedImplicitOpposites| */,
				ms._207 /* assign V6 = |ownedAnnotations| */,
				ms._173 /* assign V3 = |default| */,
				ms._164 /* assign V2 = |ownedType| */,
				ms._132 /* assign V1 = |referredOpposite| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._096 /* assign V0 = |qualifiers.'static'| */,
				ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
				ms._213 /* assign V7 = (|referredKeys| > 0) */,
				ms._217 /* assign V8 = (|referredKeys| - 1) */,
				ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
				ms._226 /* assign V9 = |ownedDefaultExpressions| */,
				ms._135 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
				ms._140 /* assign V11 = 0 */,
				ms._142 /* assign V12 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-41-steps */,
				st._028 /* 1*'definition' */,
				st._166 /* V00*'static' */,
				st._053 /* 1*'property' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._001 /* 1*'#' */,
				st._154 /* 1*referredOpposite=UnrestrictedName */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._196 /* V03*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._202 /* V04*next-4-steps */,
				st._060 /* 1*'{' */,
				st._207 /* V05*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._214 /* V06*ownedAnnotations+=AnnotationElementCS */,
				st._217 /* V07*next-6-steps */,
				st._042 /* 1*'key' */,
				st._152 /* 1*referredKeys+=UnrestrictedName */,
				st._220 /* V08*next-2-steps */,
				st._007 /* 1*',' */,
				st._153 /* 1*referredKeys+=UnrestrictedName */,
				st._011 /* 1*';' */,
				st._224 /* V09*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._233 /* V11*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._240 /* V13*next-2-steps */,
				st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
				st._011 /* 1*';' */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._3 /* «! » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._00,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */,
				new EReferenceData(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					iv._11) /* ImplicitOppositeCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |referredOpposite| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |referredKeys| = C07[*] + C07[*] * C08[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |default| = C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedAnnotations| = C06:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedDefaultExpressions| = C09[*] * C10:SpecificationCS[?] + C11[*] * C12:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedType| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'definition'| =
					E00.0: |qualifiers.'definition'| = 1
					E00.1: |qualifiers.'static'| = C00[?]
					E00.2: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| = C04[?] * C05[+] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedImplicitOpposites| = C13[*] */
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _184 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._173 /* assign V3 = |default| */,
				ms._164 /* assign V2 = |ownedType| */,
				ms._132 /* assign V1 = |referredOpposite| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._096 /* assign V0 = |qualifiers.'static'| */,
				ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
				ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-19-steps */,
				st._028 /* 1*'definition' */,
				st._166 /* V00*'static' */,
				st._053 /* 1*'property' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._001 /* 1*'#' */,
				st._154 /* 1*referredOpposite=UnrestrictedName */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._196 /* V03*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._202 /* V04*next-4-steps */,
				st._060 /* 1*'{' */,
				st._207 /* V05*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._00,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |referredOpposite| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |default| = C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedType| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'definition'| =
					E00.0: |qualifiers.'definition'| = 1
					E00.1: |qualifiers.'static'| = C00[?]
					E00.2: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| = C04[?] * C05[+] */
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _185 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._173 /* assign V3 = |default| */,
				ms._164 /* assign V2 = |ownedType| */,
				ms._132 /* assign V1 = |referredOpposite| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._095 /* assign V0 = |qualifiers.'definition'| */,
				ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
				ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-19-steps */,
				st._056 /* 1*'static' */,
				st._164 /* V00*'definition' */,
				st._053 /* 1*'property' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._001 /* 1*'#' */,
				st._154 /* 1*referredOpposite=UnrestrictedName */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._196 /* V03*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._202 /* V04*next-4-steps */,
				st._060 /* 1*'{' */,
				st._207 /* V05*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._00,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |referredOpposite| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |default| = C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedType| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'static'| =
					E00.0: |qualifiers.'static'| = 1
					E00.1: |qualifiers.'definition'| = C00[?]
					E00.2: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| = C04[?] * C05[+] */
			});
		// OCLinEcore::ReferenceCS : { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _186 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._156 /* assign V2 = |default| */,
				ms._129 /* assign V1 = |ownedType| */,
				ms._097 /* assign V0 = |referredOpposite| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._170 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._191 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-17-steps */,
				st._053 /* 1*'property' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._001 /* 1*'#' */,
				st._154 /* 1*referredOpposite=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._189 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._202 /* V04*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._00)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |referredOpposite| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |default| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedType| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| =
					E04.0: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| = C03[?] * C04[+] */
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _187 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._173 /* assign V3 = |default| */,
				ms._164 /* assign V2 = |ownedType| */,
				ms._132 /* assign V1 = |referredOpposite| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._096 /* assign V0 = |qualifiers.'static'| */,
				ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
				ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-19-steps */,
				st._028 /* 1*'definition' */,
				st._166 /* V00*'static' */,
				st._053 /* 1*'property' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._001 /* 1*'#' */,
				st._154 /* 1*referredOpposite=UnrestrictedName */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._196 /* V03*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._202 /* V04*next-4-steps */,
				st._060 /* 1*'{' */,
				st._207 /* V05*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._00,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |referredOpposite| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |default| = C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedType| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'definition'| =
					E00.0: |qualifiers.'definition'| = 1
					E00.1: |qualifiers.'static'| = C00[?]
					E00.2: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| = C04[?] * C05[+] */
			});
		// OCLinEcore::ReferenceCS : { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
		private @NonNull SerializationRule _188 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
				ms._144 /* assign V12 = |ownedImplicitOpposites| */,
				ms._196 /* assign V5 = |ownedAnnotations| */,
				ms._156 /* assign V2 = |default| */,
				ms._129 /* assign V1 = |ownedType| */,
				ms._097 /* assign V0 = |referredOpposite| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._205 /* assign V6 = (|referredKeys| > 0) */,
				ms._212 /* assign V7 = (|referredKeys| - 1) */,
				ms._170 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._191 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
				ms._220 /* assign V8 = |ownedDefaultExpressions| */,
				ms._223 /* assign V9 = (|ownedDefaultExpressions| > 0) */,
				ms._136 /* assign V10 = 0 */,
				ms._140 /* assign V11 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-39-steps */,
				st._053 /* 1*'property' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._001 /* 1*'#' */,
				st._154 /* 1*referredOpposite=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._189 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._202 /* V04*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
				st._213 /* V06*next-6-steps */,
				st._042 /* 1*'key' */,
				st._152 /* 1*referredKeys+=UnrestrictedName */,
				st._217 /* V07*next-2-steps */,
				st._007 /* 1*',' */,
				st._153 /* 1*referredKeys+=UnrestrictedName */,
				st._011 /* 1*';' */,
				st._220 /* V08*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._229 /* V10*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._235 /* V11*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._236 /* V12*next-2-steps */,
				st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
				st._011 /* 1*';' */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._3 /* «! » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._00)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */,
				new EReferenceData(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					iv._11) /* ImplicitOppositeCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |referredOpposite| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |referredKeys| = C06[*] + C06[*] * C07[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |default| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedAnnotations| = C05:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedDefaultExpressions| = C08[*] * C09:SpecificationCS[?] + C10[*] * C11:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedType| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| =
					E04.0: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| = C03[?] * C04[+] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedImplicitOpposites| = C12[*] */
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
		private @NonNull SerializationRule _189 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
				ms._146 /* assign V13 = |ownedImplicitOpposites| */,
				ms._207 /* assign V6 = |ownedAnnotations| */,
				ms._173 /* assign V3 = |default| */,
				ms._164 /* assign V2 = |ownedType| */,
				ms._132 /* assign V1 = |referredOpposite| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._096 /* assign V0 = |qualifiers.'static'| */,
				ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
				ms._213 /* assign V7 = (|referredKeys| > 0) */,
				ms._217 /* assign V8 = (|referredKeys| - 1) */,
				ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
				ms._226 /* assign V9 = |ownedDefaultExpressions| */,
				ms._135 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
				ms._140 /* assign V11 = 0 */,
				ms._142 /* assign V12 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-41-steps */,
				st._028 /* 1*'definition' */,
				st._166 /* V00*'static' */,
				st._053 /* 1*'property' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._001 /* 1*'#' */,
				st._154 /* 1*referredOpposite=UnrestrictedName */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._196 /* V03*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._202 /* V04*next-4-steps */,
				st._060 /* 1*'{' */,
				st._207 /* V05*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._214 /* V06*ownedAnnotations+=AnnotationElementCS */,
				st._217 /* V07*next-6-steps */,
				st._042 /* 1*'key' */,
				st._152 /* 1*referredKeys+=UnrestrictedName */,
				st._220 /* V08*next-2-steps */,
				st._007 /* 1*',' */,
				st._153 /* 1*referredKeys+=UnrestrictedName */,
				st._011 /* 1*';' */,
				st._224 /* V09*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._233 /* V11*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._240 /* V13*next-2-steps */,
				st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
				st._011 /* 1*';' */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._3 /* «! » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._00,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */,
				new EReferenceData(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					iv._11) /* ImplicitOppositeCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |referredOpposite| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |referredKeys| = C07[*] + C07[*] * C08[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |default| = C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedAnnotations| = C06:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedDefaultExpressions| = C09[*] * C10:SpecificationCS[?] + C11[*] * C12:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedType| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'definition'| =
					E00.0: |qualifiers.'definition'| = 1
					E00.1: |qualifiers.'static'| = C00[?]
					E00.2: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| = C04[?] * C05[+] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedImplicitOpposites| = C13[*] */
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
		private @NonNull SerializationRule _190 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
				ms._146 /* assign V13 = |ownedImplicitOpposites| */,
				ms._207 /* assign V6 = |ownedAnnotations| */,
				ms._173 /* assign V3 = |default| */,
				ms._164 /* assign V2 = |ownedType| */,
				ms._132 /* assign V1 = |referredOpposite| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._095 /* assign V0 = |qualifiers.'definition'| */,
				ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
				ms._213 /* assign V7 = (|referredKeys| > 0) */,
				ms._217 /* assign V8 = (|referredKeys| - 1) */,
				ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
				ms._226 /* assign V9 = |ownedDefaultExpressions| */,
				ms._135 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
				ms._140 /* assign V11 = 0 */,
				ms._142 /* assign V12 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-41-steps */,
				st._056 /* 1*'static' */,
				st._164 /* V00*'definition' */,
				st._053 /* 1*'property' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._001 /* 1*'#' */,
				st._154 /* 1*referredOpposite=UnrestrictedName */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._196 /* V03*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._202 /* V04*next-4-steps */,
				st._060 /* 1*'{' */,
				st._207 /* V05*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._214 /* V06*ownedAnnotations+=AnnotationElementCS */,
				st._217 /* V07*next-6-steps */,
				st._042 /* 1*'key' */,
				st._152 /* 1*referredKeys+=UnrestrictedName */,
				st._220 /* V08*next-2-steps */,
				st._007 /* 1*',' */,
				st._153 /* 1*referredKeys+=UnrestrictedName */,
				st._011 /* 1*';' */,
				st._224 /* V09*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._233 /* V11*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._240 /* V13*next-2-steps */,
				st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
				st._011 /* 1*';' */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._3 /* «! » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._00,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */,
				new EReferenceData(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					iv._11) /* ImplicitOppositeCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |referredOpposite| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |referredKeys| = C07[*] + C07[*] * C08[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |default| = C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedAnnotations| = C06:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedDefaultExpressions| = C09[*] * C10:SpecificationCS[?] + C11[*] * C12:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedType| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'static'| =
					E00.0: |qualifiers.'static'| = 1
					E00.1: |qualifiers.'definition'| = C00[?]
					E00.2: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| = C04[?] * C05[+] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedImplicitOpposites| = C13[*] */
			});
		// OCLinEcore::ReferenceCS : { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _191 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._156 /* assign V2 = |default| */,
				ms._129 /* assign V1 = |ownedType| */,
				ms._097 /* assign V0 = |referredOpposite| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._170 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._191 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-17-steps */,
				st._053 /* 1*'property' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._001 /* 1*'#' */,
				st._154 /* 1*referredOpposite=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._189 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._202 /* V04*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._00)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |referredOpposite| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |default| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedType| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| =
					E04.0: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| = C03[?] * C04[+] */
			});
	}
	private class _SerializationRules3
	{
		// OCLinEcore::ReferenceCS : { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
		private @NonNull SerializationRule _192 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
				ms._146 /* assign V13 = |ownedImplicitOpposites| */,
				ms._207 /* assign V6 = |ownedAnnotations| */,
				ms._173 /* assign V3 = |default| */,
				ms._164 /* assign V2 = |ownedType| */,
				ms._132 /* assign V1 = |referredOpposite| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._095 /* assign V0 = |qualifiers.'definition'| */,
				ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
				ms._213 /* assign V7 = (|referredKeys| > 0) */,
				ms._217 /* assign V8 = (|referredKeys| - 1) */,
				ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
				ms._226 /* assign V9 = |ownedDefaultExpressions| */,
				ms._135 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
				ms._140 /* assign V11 = 0 */,
				ms._142 /* assign V12 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-41-steps */,
				st._056 /* 1*'static' */,
				st._164 /* V00*'definition' */,
				st._053 /* 1*'property' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._001 /* 1*'#' */,
				st._154 /* 1*referredOpposite=UnrestrictedName */,
				st._189 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._196 /* V03*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._202 /* V04*next-4-steps */,
				st._060 /* 1*'{' */,
				st._207 /* V05*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._214 /* V06*ownedAnnotations+=AnnotationElementCS */,
				st._217 /* V07*next-6-steps */,
				st._042 /* 1*'key' */,
				st._152 /* 1*referredKeys+=UnrestrictedName */,
				st._220 /* V08*next-2-steps */,
				st._007 /* 1*',' */,
				st._153 /* 1*referredKeys+=UnrestrictedName */,
				st._011 /* 1*';' */,
				st._224 /* V09*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._233 /* V11*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._240 /* V13*next-2-steps */,
				st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
				st._011 /* 1*';' */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._3 /* «! » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._00,ev._13)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */,
				new EReferenceData(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					iv._11) /* ImplicitOppositeCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |referredOpposite| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |referredKeys| = C07[*] + C07[*] * C08[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |default| = C03[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedAnnotations| = C06:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedDefaultExpressions| = C09[*] * C10:SpecificationCS[?] + C11[*] * C12:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedType| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |qualifiers.'static'| =
					E00.0: |qualifiers.'static'| = 1
					E00.1: |qualifiers.'definition'| = C00[?]
					E00.2: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| = C04[?] * C05[+] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedImplicitOpposites| = C13[*] */
			});
		// OCLinEcore::ReferenceCS : { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
		private @NonNull SerializationRule _193 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
				ms._144 /* assign V12 = |ownedImplicitOpposites| */,
				ms._196 /* assign V5 = |ownedAnnotations| */,
				ms._156 /* assign V2 = |default| */,
				ms._129 /* assign V1 = |ownedType| */,
				ms._097 /* assign V0 = |referredOpposite| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._205 /* assign V6 = (|referredKeys| > 0) */,
				ms._212 /* assign V7 = (|referredKeys| - 1) */,
				ms._170 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._191 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
				ms._220 /* assign V8 = |ownedDefaultExpressions| */,
				ms._223 /* assign V9 = (|ownedDefaultExpressions| > 0) */,
				ms._136 /* assign V10 = 0 */,
				ms._140 /* assign V11 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-39-steps */,
				st._053 /* 1*'property' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._169 /* V00*next-2-steps */,
				st._001 /* 1*'#' */,
				st._154 /* 1*referredOpposite=UnrestrictedName */,
				st._181 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
				st._189 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._064 /* 1*default=SINGLE_QUOTED_STRING */,
				st._196 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._202 /* V04*next-1-steps */,
				st._147 /* 1*qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
				st._213 /* V06*next-6-steps */,
				st._042 /* 1*'key' */,
				st._152 /* 1*referredKeys+=UnrestrictedName */,
				st._217 /* V07*next-2-steps */,
				st._007 /* 1*',' */,
				st._153 /* 1*referredKeys+=UnrestrictedName */,
				st._011 /* 1*';' */,
				st._220 /* V08*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._229 /* V10*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._235 /* V11*ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._236 /* V12*next-2-steps */,
				st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
				st._011 /* 1*';' */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._3 /* «! » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				null,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._00)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */,
				new EReferenceData(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					iv._11) /* ImplicitOppositeCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |referredOpposite| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |referredKeys| = C06[*] + C06[*] * C07[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |default| = C02[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |ownedAnnotations| = C05:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedDefaultExpressions| = C08[*] * C09:SpecificationCS[?] + C10[*] * C11:SpecificationCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedType| = C01[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| =
					E04.0: |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| = C03[?] * C04[+] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedImplicitOpposites| = C12[*] */
			});
		// OCLinEcore::SpecificationCS : ownedExpression=ExpCS
		private @NonNull SerializationRule _194 = new SerializationRule(92,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._283 /* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 30 */,
				ms._018 /* assert (|ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._090 /* 1*ownedExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					iv._9) /* ExpCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedExpression| = 1 */
			});
		// OCLinEcore::SpecificationCS : exprString=UNQUOTED_STRING
		private @NonNull SerializationRule _195 = new SerializationRule(92,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._000 /* assert (|exprString| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._065 /* 1*exprString=UNQUOTED_STRING */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |exprString| = 1 */
			});
		// OCLinEcore::StructuredClassCS : { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _196 = new SerializationRule(97,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._254 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._253 /* check-rule basecs::StructuredClassCS.ownedProperties : 96 */,
				ms._252 /* check-rule basecs::StructuredClassCS.ownedOperations : 70 */,
				ms._137 /* assign V10 = |ownedConstraints| */,
				ms._228 /* assign V9 = |ownedProperties| */,
				ms._221 /* assign V8 = |ownedOperations| */,
				ms._215 /* assign V7 = |ownedAnnotations| */,
				ms._184 /* assign V4 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._071 /* assign V0 = |isAbstract.'abstract'| */,
				ms._193 /* assign V5 = (|isInterface.'interface'| > 0) */,
				ms._206 /* assign V6 = |isInterface.'interface'| */,
				ms._153 /* assign V2 = (|ownedSuperTypes| > 0) */,
				ms._169 /* assign V3 = (|ownedSuperTypes| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-23-steps */,
				st._162 /* V00*'abstract' */,
				st._026 /* 1*'class' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-5-steps */,
				st._035 /* 1*'extends' */,
				st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
				st._196 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
				st._202 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._207 /* V05*next-3-steps */,
				st._060 /* 1*'{' */,
				st._212 /* V06*'interface' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._218 /* V07*ownedAnnotations+=AnnotationElementCS */,
				st._223 /* V08*ownedOperations+=OperationCS */,
				st._228 /* V09*ownedProperties+=StructuralFeatureCS */,
				st._230 /* V10*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					ev._15),
				new EAttributeData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					ev._11)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					iv._39) /* StructuralFeatureCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					iv._23) /* OperationCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |isInterface.'interface'| =
					E05.0: |isInterface.'interface'| = C05[?] * C06[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isAbstract.'abstract'| =
					E00.0: |isAbstract.'abstract'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E09: |ownedConstraints| = C10:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedSuperTypes| = C02[?] + C02[?] * C03[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |instanceClassName| = C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |ownedAnnotations| = C07:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedProperties| = C09:StructuralFeatureCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedOperations| = C08:OperationCS[*] */
			});
		// OCLinEcore::StructuredClassCS : { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' }
		private @NonNull SerializationRule _197 = new SerializationRule(97,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._254 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._184 /* assign V4 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._071 /* assign V0 = |isAbstract.'abstract'| */,
				ms._193 /* assign V5 = (|isInterface.'interface'| > 0) */,
				ms._206 /* assign V6 = |isInterface.'interface'| */,
				ms._153 /* assign V2 = (|ownedSuperTypes| > 0) */,
				ms._169 /* assign V3 = (|ownedSuperTypes| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-18-steps */,
				st._162 /* V00*'abstract' */,
				st._026 /* 1*'class' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-5-steps */,
				st._035 /* 1*'extends' */,
				st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
				st._196 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
				st._202 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._207 /* V05*next-3-steps */,
				st._060 /* 1*'{' */,
				st._212 /* V06*'interface' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					ev._15),
				new EAttributeData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					ev._11)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |isInterface.'interface'| =
					E05.0: |isInterface.'interface'| = C05[?] * C06[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isAbstract.'abstract'| =
					E00.0: |isAbstract.'abstract'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedSuperTypes| = C02[?] + C02[?] * C03[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |instanceClassName| = C04[?] */
			});
		// OCLinEcore::StructuredClassCS : { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _198 = new SerializationRule(97,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._254 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._253 /* check-rule basecs::StructuredClassCS.ownedProperties : 96 */,
				ms._252 /* check-rule basecs::StructuredClassCS.ownedOperations : 70 */,
				ms._137 /* assign V10 = |ownedConstraints| */,
				ms._228 /* assign V9 = |ownedProperties| */,
				ms._221 /* assign V8 = |ownedOperations| */,
				ms._215 /* assign V7 = |ownedAnnotations| */,
				ms._184 /* assign V4 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._071 /* assign V0 = |isAbstract.'abstract'| */,
				ms._193 /* assign V5 = (|isInterface.'interface'| > 0) */,
				ms._206 /* assign V6 = |isInterface.'interface'| */,
				ms._153 /* assign V2 = (|ownedSuperTypes| > 0) */,
				ms._169 /* assign V3 = (|ownedSuperTypes| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-23-steps */,
				st._162 /* V00*'abstract' */,
				st._026 /* 1*'class' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-5-steps */,
				st._035 /* 1*'extends' */,
				st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
				st._196 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
				st._202 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._207 /* V05*next-3-steps */,
				st._060 /* 1*'{' */,
				st._212 /* V06*'interface' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._218 /* V07*ownedAnnotations+=AnnotationElementCS */,
				st._223 /* V08*ownedOperations+=OperationCS */,
				st._228 /* V09*ownedProperties+=StructuralFeatureCS */,
				st._230 /* V10*ownedConstraints+=InvariantConstraintCS */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				null,
				null,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					ev._15),
				new EAttributeData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					ev._11)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReferenceData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					iv._39) /* StructuralFeatureCS */,
				new EReferenceData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					iv._23) /* OperationCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |isInterface.'interface'| =
					E05.0: |isInterface.'interface'| = C05[?] * C06[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isAbstract.'abstract'| =
					E00.0: |isAbstract.'abstract'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E09: |ownedConstraints| = C10:InvariantConstraintCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedSuperTypes| = C02[?] + C02[?] * C03[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |instanceClassName| = C04[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E06: |ownedAnnotations| = C07:AnnotationElementCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E08: |ownedProperties| = C09:StructuralFeatureCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E07: |ownedOperations| = C08:OperationCS[*] */
			});
		// OCLinEcore::StructuredClassCS : { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' }
		private @NonNull SerializationRule _199 = new SerializationRule(97,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._254 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._184 /* assign V4 = |instanceClassName| */,
				ms._128 /* assign V1 = |ownedSignature| */,
				ms._005 /* assert (|name| - 1) == 0 */,
				ms._071 /* assign V0 = |isAbstract.'abstract'| */,
				ms._193 /* assign V5 = (|isInterface.'interface'| > 0) */,
				ms._206 /* assign V6 = |isInterface.'interface'| */,
				ms._153 /* assign V2 = (|ownedSuperTypes| > 0) */,
				ms._169 /* assign V3 = (|ownedSuperTypes| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-18-steps */,
				st._162 /* V00*'abstract' */,
				st._026 /* 1*'class' */,
				st._074 /* 1*name=UnrestrictedName */,
				st._188 /* V01*ownedSignature=TemplateSignatureCS */,
				st._189 /* V02*next-5-steps */,
				st._035 /* 1*'extends' */,
				st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
				st._196 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
				st._202 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
				st._207 /* V05*next-3-steps */,
				st._060 /* 1*'{' */,
				st._212 /* V06*'interface' */,
				st._063 /* 1*'}' */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				ss._8 /* «? » + «value» + «? » */,
				ss._6 /* «-» + «? » + «value» + «?\n» */,
				ss._4 /* «! » + «value» + «?\n» */
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					ev._15),
				new EAttributeData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					ev._11)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					iv._59) /* TypedRefCS */,
				new EReferenceData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E05: |isInterface.'interface'| =
					E05.0: |isInterface.'interface'| = C05[?] * C06[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |isAbstract.'abstract'| =
					E00.0: |isAbstract.'abstract'| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedSuperTypes| = C02[?] + C02[?] * C03[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedSignature| = C01:TemplateSignatureCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E04: |instanceClassName| = C04[?] */
			});
		// OCLinEcore::SysMLCS : { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' }
		private @NonNull SerializationRule _200 = new SerializationRule(98,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._080 /* assign V0 = |ownedDetails| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-6-steps */,
				st._057 /* 1*'sysml' */,
				st._060 /* 1*'{' */,
				st._169 /* V00*next-2-steps */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._011 /* 1*';' */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedDetails| = C00[*] */
			});
		// OCLinEcore::SysMLCS : { 'sysml' ownedDetails+=DetailCS ';' }
		private @NonNull SerializationRule _201 = new SerializationRule(98,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._012 /* assert (|ownedDetails| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-3-steps */,
				st._057 /* 1*'sysml' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedDetails| = 1 */
			});
		// OCLinEcore::SysMLCS : { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' }
		private @NonNull SerializationRule _202 = new SerializationRule(98,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._080 /* assign V0 = |ownedDetails| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-6-steps */,
				st._057 /* 1*'sysml' */,
				st._060 /* 1*'{' */,
				st._169 /* V00*next-2-steps */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._011 /* 1*';' */,
				st._063 /* 1*'}' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._7 /* «? » + «value» + «+» + «?\n» */,
				null,
				null,
				ss._4 /* «! » + «value» + «?\n» */,
				ss._6 /* «-» + «? » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedDetails| = C00[*] */
			});
		// OCLinEcore::SysMLCS : { 'sysml' ownedDetails+=DetailCS ';' }
		private @NonNull SerializationRule _203 = new SerializationRule(98,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._012 /* assert (|ownedDetails| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-3-steps */,
				st._057 /* 1*'sysml' */,
				st._086 /* 1*ownedDetails+=DetailCS */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedDetails| = 1 */
			});
		// OCLinEcore::TemplateSignatureCS : { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' }
		private @NonNull SerializationRule _204 = new SerializationRule(101,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._258 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 114 */,
				ms._059 /* assign V0 = (|ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-6-steps */,
				st._012 /* 1*'<' */,
				st._110 /* 1*ownedParameters+=TypeParameterCS */,
				st._169 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._110 /* 1*ownedParameters+=TypeParameterCS */,
				st._015 /* 1*'>' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._56) /* TypeParameterCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedParameters| = 1 + C00[*] */
			});
		// OCLinEcore::TemplateSignatureCS : { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' }
		private @NonNull SerializationRule _205 = new SerializationRule(101,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._258 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 114 */,
				ms._059 /* assign V0 = (|ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-6-steps */,
				st._003 /* 1*'(' */,
				st._110 /* 1*ownedParameters+=TypeParameterCS */,
				st._169 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._110 /* 1*ownedParameters+=TypeParameterCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._56) /* TypeParameterCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedParameters| = 1 + C00[*] */
			});
		// OCLinEcore::TopLevelCS : { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] }
		private @NonNull SerializationRule _206 = new SerializationRule(102,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._245 /* check-rule basecs::PackageOwnerCS.ownedPackages : 71 */,
				ms._250 /* check-rule basecs::RootCS.ownedImports : 38 */,
				ms._162 /* assign V2 = |ownedPackages| */,
				ms._121 /* assign V1 = |ownedImports| */,
				ms._070 /* assign V0 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._169 /* V00*next-1-steps */,
				st._045 /* 1*'module' */,
				st._184 /* V01*ownedImports+=ImportCS */,
				st._193 /* V02*ownedPackages+=PackageCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					iv._24) /* PackageCS */,
				new EReferenceData(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					iv._12) /* ImportCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedPackages| = C02:PackageCS[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedImports| = C01:ImportCS[*] */
			});
		// OCLinEcore::TypedMultiplicityRefCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _207 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
				ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._161 /* assign V2 = |ownedMultiplicity| */,
				ms._091 /* assign V0 = |ownedType| */,
				ms._006 /* assert (|name| - 1) == 0 */,
				ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-7-steps */,
				st._070 /* 1*name=CollectionTypeIdentifier */,
				st._169 /* V00*next-4-steps */,
				st._003 /* 1*'(' */,
				st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
				st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
				st._004 /* 1*')' */,
				st._192 /* V02*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				ss._5 /* «! » + «value» */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._50) /* TypeExpWithoutMultiplicityCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedCollectionMultiplicity| = C00[?] * C01:MultiplicityCS[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedMultiplicity| = C02:MultiplicityCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */
			});
		// OCLinEcore::TypedMultiplicityRefCS : { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _208 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._085 /* assign V0 = |ownedMultiplicity| */,
				ms._029 /* assert (|ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-2-steps */,
				st._121 /* 1*ownedPathName=PathNameCS */,
				st._171 /* V00*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedMultiplicity| = C00:MultiplicityCS[?] */
			});
		// OCLinEcore::TypedMultiplicityRefCS : { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _209 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._085 /* assign V0 = |ownedMultiplicity| */,
				ms._008 /* assert (|ownedBinding| - 1) == 0 */,
				ms._029 /* assert (|ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-5-steps */,
				st._121 /* 1*ownedPathName=PathNameCS */,
				st._012 /* 1*'<' */,
				st._081 /* 1*ownedBinding=TemplateBindingCS */,
				st._015 /* 1*'>' */,
				st._171 /* V00*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._44) /* TemplateBindingCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedBinding| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedMultiplicity| = C00:MultiplicityCS[?] */
			});
		// OCLinEcore::TypedMultiplicityRefCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _210 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
				ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._124 /* assign V1 = |ownedMultiplicity| */,
				ms._093 /* assign V0 = |ownedValueType| */,
				ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
				ms._002 /* assert (|name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-8-steps */,
				st._019 /* 1*'Map' */,
				st._169 /* V00*next-5-steps */,
				st._003 /* 1*'(' */,
				st._104 /* 1*ownedKeyType=TypeExpCS */,
				st._007 /* 1*',' */,
				st._145 /* 1*ownedValueType=TypeExpCS */,
				st._004 /* 1*')' */,
				st._186 /* V01*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._49) /* TypeExpCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedValueType| = C00[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedKeyType| = C00[?] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E03: |ownedMultiplicity| = C01:MultiplicityCS[?] */,
				new EStructuralFeatureData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name.'Map'| =
					E00.0: |name.'Map'| = 1 */
			});
		// OCLinEcore::TypedMultiplicityRefCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _211 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._177 /* assign V3 = |ownedMultiplicity| */,
				ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
				ms._064 /* assign V0 = (|ownedParts| > 0) */,
				ms._111 /* assign V1 = (|ownedParts| > 0) */,
				ms._152 /* assign V2 = (|ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-10-steps */,
				st._020 /* 1*'Tuple' */,
				st._169 /* V00*next-7-steps */,
				st._003 /* 1*'(' */,
				st._181 /* V01*next-4-steps */,
				st._116 /* 1*ownedParts+=TuplePartCS */,
				st._189 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._116 /* 1*ownedParts+=TuplePartCS */,
				st._004 /* 1*')' */,
				st._198 /* V03*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				null,
				null,
				ss._3 /* «! » + «value» + «? » */,
				null,
				ss._5 /* «! » + «value» */,
				null
			},
			new @NonNull EAttributeData [] {
				new EAttributeData(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._10)
			},
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._48) /* TuplePartCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name.'Tuple'| =
					E00.0: |name.'Tuple'| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedParts| = C00[?] * C01[?] + C00[?] * C01[?] * C02[*] */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedMultiplicity| = C03:MultiplicityCS[?] */
			});
		// OCLinEcore::TypedMultiplicityRefCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _212 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._085 /* assign V0 = |ownedMultiplicity| */,
				ms._004 /* assert (|name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-2-steps */,
				st._072 /* 1*name=PrimitiveTypeIdentifier */,
				st._171 /* V00*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |name| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedMultiplicity| = C00:MultiplicityCS[?] */
			});
		// OCLinEcore::TypedMultiplicityRefCS : { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _213 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._085 /* assign V0 = |ownedMultiplicity| */,
				ms._008 /* assert (|ownedBinding| - 1) == 0 */,
				ms._029 /* assert (|ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-5-steps */,
				st._121 /* 1*ownedPathName=PathNameCS */,
				st._003 /* 1*'(' */,
				st._081 /* 1*ownedBinding=TemplateBindingCS */,
				st._004 /* 1*')' */,
				st._171 /* V00*ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._5 /* «! » + «value» */,
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._44) /* TemplateBindingCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedBinding| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E02: |ownedMultiplicity| = C00:MultiplicityCS[?] */
			});
		// OCLinEcore::TypedTypeRefCS : { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' }
		private @NonNull SerializationRule _214 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._008 /* assert (|ownedBinding| - 1) == 0 */,
				ms._029 /* assert (|ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._121 /* 1*ownedPathName=PathNameCS */,
				st._012 /* 1*'<' */,
				st._081 /* 1*ownedBinding=TemplateBindingCS */,
				st._015 /* 1*'>' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._44) /* TemplateBindingCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedBinding| = 1 */
			});
		// OCLinEcore::TypedTypeRefCS : { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' }
		private @NonNull SerializationRule _215 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._008 /* assert (|ownedBinding| - 1) == 0 */,
				ms._029 /* assert (|ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._121 /* 1*ownedPathName=PathNameCS */,
				st._012 /* 1*'<' */,
				st._081 /* 1*ownedBinding=TemplateBindingCS */,
				st._015 /* 1*'>' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._44) /* TemplateBindingCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedBinding| = 1 */
			});
		// OCLinEcore::TypedTypeRefCS : { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' }
		private @NonNull SerializationRule _216 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._008 /* assert (|ownedBinding| - 1) == 0 */,
				ms._029 /* assert (|ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._121 /* 1*ownedPathName=PathNameCS */,
				st._003 /* 1*'(' */,
				st._081 /* 1*ownedBinding=TemplateBindingCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._5 /* «! » + «value» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._44) /* TemplateBindingCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedBinding| = 1 */
			});
		// OCLinEcore::TypedTypeRefCS : ownedPathName=PathNameCS
		private @NonNull SerializationRule _217 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._029 /* assert (|ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._121 /* 1*ownedPathName=PathNameCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */
			});
		// OCLinEcore::TypedTypeRefCS : ownedPathName=PathNameCS
		private @NonNull SerializationRule _218 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._029 /* assert (|ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._121 /* 1*ownedPathName=PathNameCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */
			});
		// OCLinEcore::TypedTypeRefCS : { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' }
		private @NonNull SerializationRule _219 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._008 /* assert (|ownedBinding| - 1) == 0 */,
				ms._029 /* assert (|ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._076 /* 1*next-4-steps */,
				st._121 /* 1*ownedPathName=PathNameCS */,
				st._003 /* 1*'(' */,
				st._081 /* 1*ownedBinding=TemplateBindingCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._5 /* «! » + «value» */
			},
			null,
			new @NonNull EReferenceData [] {
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReferenceData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._44) /* TemplateBindingCS */
			},
			new @NonNull EStructuralFeatureData [] {
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E00: |ownedPathName| = 1 */,
				new EStructuralFeatureData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					"getIndexVectorId(eStructuralFeatureData.getCardinalityExpression(), true)") /* E01: |ownedBinding| = 1 */
			});
	}

	private _EnumValues ev;
	private _IndexVectors iv;
	private _MatchTerms mt;
	private _MatchSteps ms;
	private _SerializationTerms st;
	private _SerializationSegments ss;
	private _SerializationRules0 sr0;
	private _SerializationRules1 sr1;
	private _SerializationRules2 sr2;
	private _SerializationRules3 sr3;
	private _RuleValues rv;
	private _EClassData ec;

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
		sr0 = new _SerializationRules0();
		sr1 = new _SerializationRules1();
		sr2 = new _SerializationRules2();
		sr3 = new _SerializationRules3();
		rv = new _RuleValues();
		ec = new _EClassData();
		st.init();
	}

	//	Commented imports to ensure Xtend provides a true import allowing unqualified annotated usage
		//	import Inject;
		//	import NonNull;
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
		//	import CardinalitySolution;
		//	import EAttributeSizeCardinalitySolution;
		//	import EStructuralFeatureSizeCardinalitySolution;
		//	import GreaterThanCardinalitySolution;
		//	import IntegerCardinalitySolution;
		//	import SubtractCardinalitySolution;
		//	import VariableCardinalitySolution;
		//	import CardinalitySolutionStep;
		//	import RTGrammarAnalysis;
		//	import AbstractRuleValue;
		//	import DataTypeRuleValue;
		//	import EAttributeData;
		//	import EClassData;
		//	import EReferenceData;
		//	import EStructuralFeatureData;
		//	import IndexVector;
		//	import ParserRuleValue;
		//	import TerminalRuleValue;
		//	import BaseCSPackage;
		//	import EssentialOCLCSPackage;
		//	import OCLinEcoreCSPackage;
}
