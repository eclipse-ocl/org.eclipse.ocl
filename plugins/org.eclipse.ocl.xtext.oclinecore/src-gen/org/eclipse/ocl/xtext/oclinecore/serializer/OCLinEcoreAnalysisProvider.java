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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jdt.annotation.NonNull;
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
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData;
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
		private final @NonNull CardinalitySolution _003 // |AbstractNameExpCS::isPre.'@'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, ev._08);
		private final @NonNull CardinalitySolution _004 // |AbstractNameExpCS::ownedCurlyBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _005 // |AbstractNameExpCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _006 // |AbstractNameExpCS::ownedRoundBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _007 // |AbstractNameExpCS::ownedSquareBracketedClauses|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		private final @NonNull CardinalitySolution _008 // |AnnotationCS::ownedContents|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS);
		private final @NonNull CardinalitySolution _009 // |AnnotationCS::ownedReferences|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES);
		private final @NonNull CardinalitySolution _010 // |AnnotationElementCS::ownedDetails|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
		private final @NonNull CardinalitySolution _011 // |BooleanLiteralExpCS::symbol.'false|true'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._14);
		private final @NonNull CardinalitySolution _012 // |ClassCS::instanceClassName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME);
		private final @NonNull CardinalitySolution _013 // |ClassCS::ownedConstraints|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		private final @NonNull CardinalitySolution _014 // |CollectionLiteralExpCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _015 // |CollectionLiteralExpCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _016 // |CollectionLiteralPartCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _017 // |CollectionLiteralPartCS::ownedLastExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		private final @NonNull CardinalitySolution _018 // |CollectionPatternCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _019 // |CollectionPatternCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _020 // |CollectionPatternCS::restVariableName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		private final @NonNull CardinalitySolution _021 // |CollectionTypeCS::name|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		private final @NonNull CardinalitySolution _022 // |CollectionTypeCS::ownedCollectionMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		private final @NonNull CardinalitySolution _023 // |CollectionTypeCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _024 // |ConstraintCS::ownedMessageSpecification|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		private final @NonNull CardinalitySolution _025 // |ConstraintCS::ownedSpecification|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		private final @NonNull CardinalitySolution _026 // |ConstraintCS::stereotype.'invariant'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._16);
		private final @NonNull CardinalitySolution _027 // |ConstraintCS::stereotype.'postcondition'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._17);
		private final @NonNull CardinalitySolution _028 // |ConstraintCS::stereotype.'precondition'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._18);
		private final @NonNull CardinalitySolution _029 // |ContextCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _030 // |CurlyBracketedClauseCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _031 // |DataTypeCS::isPrimitive.'primitive'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, ev._19);
		private final @NonNull CardinalitySolution _032 // |DataTypeCS::isSerializable.'serializable'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE, ev._20);
		private final @NonNull CardinalitySolution _033 // |DetailCS::values|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.DETAIL_CS__VALUES);
		private final @NonNull CardinalitySolution _034 // |DocumentationCS::value|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
		private final @NonNull CardinalitySolution _035 // |EnumerationCS::isSerializable.'serializable'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE, ev._20);
		private final @NonNull CardinalitySolution _036 // |EnumerationCS::ownedLiterals|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
		private final @NonNull CardinalitySolution _037 // |EnumerationLiteralCS::literal|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL);
		private final @NonNull CardinalitySolution _038 // |EnumerationLiteralCS::value|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE);
		private final @NonNull CardinalitySolution _039 // |ExpSpecificationCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _040 // |IfExpCS::ownedCondition|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		private final @NonNull CardinalitySolution _041 // |IfExpCS::ownedElseExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		private final @NonNull CardinalitySolution _042 // |IfExpCS::ownedIfThenExpressions|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		private final @NonNull CardinalitySolution _043 // |IfExpCS::ownedThenExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull CardinalitySolution _044 // |IfThenExpCS::ownedCondition|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		private final @NonNull CardinalitySolution _045 // |IfThenExpCS::ownedThenExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull CardinalitySolution _046 // |ImportCS::isAll.'::*'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, ev._06);
		private final @NonNull CardinalitySolution _047 // |ImportCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _048 // |InfixExpCS::ownedLeft|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		private final @NonNull CardinalitySolution _049 // |LambdaLiteralExpCS::ownedExpressionCS|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		private final @NonNull CardinalitySolution _050 // |LetExpCS::ownedInExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		private final @NonNull CardinalitySolution _051 // |LetExpCS::ownedVariables|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		private final @NonNull CardinalitySolution _052 // |LetVariableCS::ownedRoundBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _053 // |MapLiteralExpCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _054 // |MapLiteralExpCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _055 // |MapLiteralPartCS::ownedKey|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		private final @NonNull CardinalitySolution _056 // |MapLiteralPartCS::ownedValue|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		private final @NonNull CardinalitySolution _057 // |MapTypeCS::name.'Map'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, ev._09);
		private final @NonNull CardinalitySolution _058 // |MapTypeCS::ownedKeyType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		private final @NonNull CardinalitySolution _059 // |MapTypeCS::ownedValueType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		private final @NonNull CardinalitySolution _060 // |ModelElementCS::ownedAnnotations|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull CardinalitySolution _061 // |ModelElementRefCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _062 // |MultiplicityBoundsCS::lowerBound|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull CardinalitySolution _063 // |MultiplicityBoundsCS::upperBound|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull CardinalitySolution _064 // |MultiplicityCS::isNullFree.'|1'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, ev._23);
		private final @NonNull CardinalitySolution _065 // |MultiplicityStringCS::stringBounds.'*|+|?'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._04);
		private final @NonNull CardinalitySolution _066 // |NamedElementCS::name|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull CardinalitySolution _067 // |NavigatingArgCS::ownedCoIterator|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		private final @NonNull CardinalitySolution _068 // |NavigatingArgCS::ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _069 // |NavigatingArgCS::ownedNameExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		private final @NonNull CardinalitySolution _070 // |NavigatingArgCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _071 // |NavigatingArgCS::prefix.','|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._05);
		private final @NonNull CardinalitySolution _072 // |NavigatingArgCS::prefix.';'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._07);
		private final @NonNull CardinalitySolution _073 // |NavigatingArgCS::prefix.'|'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._22);
		private final @NonNull CardinalitySolution _074 // |NestedExpCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _075 // |NumberLiteralExpCS::symbol|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull CardinalitySolution _076 // |OCLinEcoreConstraintCS::isCallable.'callable'|
			= new EAttributeSizeCardinalitySolution(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE, ev._12);
		private final @NonNull CardinalitySolution _077 // |OperationCS::ownedBodyExpressions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		private final @NonNull CardinalitySolution _078 // |OperationCS::ownedExceptions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
		private final @NonNull CardinalitySolution _079 // |OperationCS::ownedParameters|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
		private final @NonNull CardinalitySolution _080 // |OperationCS::ownedPostconditions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
		private final @NonNull CardinalitySolution _081 // |OperationCS::ownedPreconditions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		private final @NonNull CardinalitySolution _082 // |OperatorExpCS::ownedRight|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull CardinalitySolution _083 // |PackageCS::nsPrefix|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
		private final @NonNull CardinalitySolution _084 // |PackageCS::nsURI|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
		private final @NonNull CardinalitySolution _085 // |PackageCS::ownedClasses|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
		private final @NonNull CardinalitySolution _086 // |PackageOwnerCS::ownedPackages|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		private final @NonNull CardinalitySolution _087 // |PathElementCS::referredElement|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private final @NonNull CardinalitySolution _088 // |PathNameCS::ownedPathElements|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull CardinalitySolution _089 // |PatternExpCS::ownedPatternType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		private final @NonNull CardinalitySolution _090 // |PatternExpCS::patternVariableName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		private final @NonNull CardinalitySolution _091 // |PrimitiveTypeRefCS::name|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		private final @NonNull CardinalitySolution _092 // |ReferenceCS::ownedImplicitOpposites|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES);
		private final @NonNull CardinalitySolution _093 // |ReferenceCS::referredKeys|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS);
		private final @NonNull CardinalitySolution _094 // |ReferenceCS::referredOpposite|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE);
		private final @NonNull CardinalitySolution _095 // |RootCS::ownedImports|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		private final @NonNull CardinalitySolution _096 // |RoundBracketedClauseCS::ownedArguments|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		private final @NonNull CardinalitySolution _097 // |ShadowPartCS::ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _098 // |ShadowPartCS::referredProperty|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		private final @NonNull CardinalitySolution _099 // |SpecificationCS::exprString|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		private final @NonNull CardinalitySolution _100 // |SquareBracketedClauseCS::ownedTerms|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		private final @NonNull CardinalitySolution _101 // |StringLiteralExpCS::segments|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		private final @NonNull CardinalitySolution _102 // |StructuralFeatureCS::default|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
		private final @NonNull CardinalitySolution _103 // |StructuralFeatureCS::ownedDefaultExpressions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull CardinalitySolution _104 // |StructuredClassCS::isAbstract.'abstract'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, ev._11);
		private final @NonNull CardinalitySolution _105 // |StructuredClassCS::isInterface.'interface'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE, ev._15);
		private final @NonNull CardinalitySolution _106 // |StructuredClassCS::ownedOperations|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
		private final @NonNull CardinalitySolution _107 // |StructuredClassCS::ownedProperties|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
		private final @NonNull CardinalitySolution _108 // |StructuredClassCS::ownedSuperTypes|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
		private final @NonNull CardinalitySolution _109 // |TemplateBindingCS::ownedMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull CardinalitySolution _110 // |TemplateBindingCS::ownedSubstitutions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull CardinalitySolution _111 // |TemplateParameterSubstitutionCS::ownedActualParameter|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull CardinalitySolution _112 // |TemplateSignatureCS::ownedParameters|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull CardinalitySolution _113 // |TemplateableElementCS::ownedSignature|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		private final @NonNull CardinalitySolution _114 // |TupleLiteralExpCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _115 // |TupleTypeCS::name.'Tuple'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, ev._10);
		private final @NonNull CardinalitySolution _116 // |TupleTypeCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _117 // |TypeLiteralExpCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _118 // |TypeNameExpCS::ownedCurlyBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _119 // |TypeNameExpCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _120 // |TypeNameExpCS::ownedPatternGuard|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		private final @NonNull CardinalitySolution _121 // |TypeParameterCS::ownedExtends|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull CardinalitySolution _122 // |TypedElementCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _123 // |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._00);
		private final @NonNull CardinalitySolution _124 // |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._01);
		private final @NonNull CardinalitySolution _125 // |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._02);
		private final @NonNull CardinalitySolution _126 // |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._03);
		private final @NonNull CardinalitySolution _127 // |TypedElementCS::qualifiers.'definition'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._13);
		private final @NonNull CardinalitySolution _128 // |TypedElementCS::qualifiers.'static'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._21);
		private final @NonNull CardinalitySolution _129 // |TypedRefCS::ownedMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull CardinalitySolution _130 // |TypedTypeRefCS::ownedBinding|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull CardinalitySolution _131 // |TypedTypeRefCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _132 // |VariableCS::ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _133 // |VariableCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _134 // |WildcardTypeRefCS::ownedExtends|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull CardinalitySolution _135 // (|AbstractNameExpCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_005, _001);
		private final @NonNull CardinalitySolution _136 // (|AnnotationElementCS::ownedDetails| - 1)
			= new SubtractCardinalitySolution(_010, _001);
		private final @NonNull CardinalitySolution _137 // (|AnnotationElementCS::ownedDetails| > 0)
			= new GreaterThanCardinalitySolution(_010, _000);
		private final @NonNull CardinalitySolution _138 // (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
			= new SubtractCardinalitySolution(_011, _001);
		private final @NonNull CardinalitySolution _139 // (|CollectionLiteralExpCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_014, _001);
		private final @NonNull CardinalitySolution _140 // (|CollectionLiteralExpCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_014, _000);
		private final @NonNull CardinalitySolution _141 // (|CollectionLiteralExpCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_015, _001);
		private final @NonNull CardinalitySolution _142 // (|CollectionLiteralPartCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_016, _001);
		private final @NonNull CardinalitySolution _143 // (|CollectionPatternCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_018, _001);
		private final @NonNull CardinalitySolution _144 // (|CollectionPatternCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_019, _001);
		private final @NonNull CardinalitySolution _145 // (|CollectionTypeCS::name| - 1)
			= new SubtractCardinalitySolution(_021, _001);
		private final @NonNull CardinalitySolution _146 // (|ConstraintCS::stereotype.'invariant'| - 1)
			= new SubtractCardinalitySolution(_026, _001);
		private final @NonNull CardinalitySolution _147 // (|ConstraintCS::stereotype.'postcondition'| - 1)
			= new SubtractCardinalitySolution(_027, _001);
		private final @NonNull CardinalitySolution _148 // (|ConstraintCS::stereotype.'precondition'| - 1)
			= new SubtractCardinalitySolution(_028, _001);
		private final @NonNull CardinalitySolution _149 // (|ContextCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_029, _001);
		private final @NonNull CardinalitySolution _150 // (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_030, _001);
		private final @NonNull CardinalitySolution _151 // (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_030, _000);
		private final @NonNull CardinalitySolution _152 // (|DataTypeCS::isSerializable.'serializable'| > 0)
			= new GreaterThanCardinalitySolution(_032, _000);
		private final @NonNull CardinalitySolution _153 // (|EnumerationCS::isSerializable.'serializable'| > 0)
			= new GreaterThanCardinalitySolution(_035, _000);
		private final @NonNull CardinalitySolution _154 // (|ExpSpecificationCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_039, _001);
		private final @NonNull CardinalitySolution _155 // (|IfExpCS::ownedCondition| - 1)
			= new SubtractCardinalitySolution(_040, _001);
		private final @NonNull CardinalitySolution _156 // (|IfExpCS::ownedElseExpression| - 1)
			= new SubtractCardinalitySolution(_041, _001);
		private final @NonNull CardinalitySolution _157 // (|IfExpCS::ownedThenExpression| - 1)
			= new SubtractCardinalitySolution(_043, _001);
		private final @NonNull CardinalitySolution _158 // (|IfThenExpCS::ownedCondition| - 1)
			= new SubtractCardinalitySolution(_044, _001);
		private final @NonNull CardinalitySolution _159 // (|IfThenExpCS::ownedThenExpression| - 1)
			= new SubtractCardinalitySolution(_045, _001);
		private final @NonNull CardinalitySolution _160 // (|ImportCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_047, _001);
		private final @NonNull CardinalitySolution _161 // (|InfixExpCS::ownedLeft| - 1)
			= new SubtractCardinalitySolution(_048, _001);
		private final @NonNull CardinalitySolution _162 // (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
			= new SubtractCardinalitySolution(_049, _001);
		private final @NonNull CardinalitySolution _163 // (|LetExpCS::ownedInExpression| - 1)
			= new SubtractCardinalitySolution(_050, _001);
		private final @NonNull CardinalitySolution _164 // (|LetExpCS::ownedVariables| - 1)
			= new SubtractCardinalitySolution(_051, _001);
		private final @NonNull CardinalitySolution _165 // (|MapLiteralExpCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_053, _001);
		private final @NonNull CardinalitySolution _166 // (|MapLiteralExpCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_053, _000);
		private final @NonNull CardinalitySolution _167 // (|MapLiteralExpCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_054, _001);
		private final @NonNull CardinalitySolution _168 // (|MapLiteralPartCS::ownedKey| - 1)
			= new SubtractCardinalitySolution(_055, _001);
		private final @NonNull CardinalitySolution _169 // (|MapLiteralPartCS::ownedValue| - 1)
			= new SubtractCardinalitySolution(_056, _001);
		private final @NonNull CardinalitySolution _170 // (|MapTypeCS::name.'Map'| - 1)
			= new SubtractCardinalitySolution(_057, _001);
		private final @NonNull CardinalitySolution _171 // (|MapTypeCS::ownedKeyType| - V0)
			= new SubtractCardinalitySolution(_058, _002);
		private final @NonNull CardinalitySolution _172 // (|ModelElementCS::ownedAnnotations| > 0)
			= new GreaterThanCardinalitySolution(_060, _000);
		private final @NonNull CardinalitySolution _173 // (|ModelElementRefCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_061, _001);
		private final @NonNull CardinalitySolution _174 // (|MultiplicityBoundsCS::lowerBound| - 1)
			= new SubtractCardinalitySolution(_062, _001);
		private final @NonNull CardinalitySolution _175 // (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
			= new SubtractCardinalitySolution(_065, _001);
		private final @NonNull CardinalitySolution _176 // (|NamedElementCS::name| - 1)
			= new SubtractCardinalitySolution(_066, _001);
		private final @NonNull CardinalitySolution _177 // (|NavigatingArgCS::ownedCoIterator| - 1)
			= new SubtractCardinalitySolution(_067, _001);
		private final @NonNull CardinalitySolution _178 // (|NavigatingArgCS::ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_068, _001);
		private final @NonNull CardinalitySolution _179 // (|NavigatingArgCS::ownedNameExpression| - 1)
			= new SubtractCardinalitySolution(_069, _001);
		private final @NonNull CardinalitySolution _180 // (|NavigatingArgCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_070, _001);
		private final @NonNull CardinalitySolution _181 // (|NavigatingArgCS::prefix.','| - 1)
			= new SubtractCardinalitySolution(_071, _001);
		private final @NonNull CardinalitySolution _182 // (|NavigatingArgCS::prefix.';'| - 1)
			= new SubtractCardinalitySolution(_072, _001);
		private final @NonNull CardinalitySolution _183 // (|NavigatingArgCS::prefix.'|'| - 1)
			= new SubtractCardinalitySolution(_073, _001);
		private final @NonNull CardinalitySolution _184 // (|NestedExpCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_074, _001);
		private final @NonNull CardinalitySolution _185 // (|NumberLiteralExpCS::symbol| - 1)
			= new SubtractCardinalitySolution(_075, _001);
		private final @NonNull CardinalitySolution _186 // (|OperationCS::ownedBodyExpressions| > 0)
			= new GreaterThanCardinalitySolution(_077, _000);
		private final @NonNull CardinalitySolution _187 // (|OperationCS::ownedExceptions| - 1)
			= new SubtractCardinalitySolution(_078, _001);
		private final @NonNull CardinalitySolution _188 // (|OperationCS::ownedExceptions| > 0)
			= new GreaterThanCardinalitySolution(_078, _000);
		private final @NonNull CardinalitySolution _189 // (|OperationCS::ownedParameters| - 1)
			= new SubtractCardinalitySolution(_079, _001);
		private final @NonNull CardinalitySolution _190 // (|OperationCS::ownedParameters| > 0)
			= new GreaterThanCardinalitySolution(_079, _000);
		private final @NonNull CardinalitySolution _191 // (|OperatorExpCS::ownedRight| - 1)
			= new SubtractCardinalitySolution(_082, _001);
		private final @NonNull CardinalitySolution _192 // (|PathElementCS::referredElement| - 1)
			= new SubtractCardinalitySolution(_087, _001);
		private final @NonNull CardinalitySolution _193 // (|PathNameCS::ownedPathElements| - 1)
			= new SubtractCardinalitySolution(_088, _001);
		private final @NonNull CardinalitySolution _194 // (|PatternExpCS::ownedPatternType| - 1)
			= new SubtractCardinalitySolution(_089, _001);
		private final @NonNull CardinalitySolution _195 // (|PrimitiveTypeRefCS::name| - 1)
			= new SubtractCardinalitySolution(_091, _001);
		private final @NonNull CardinalitySolution _196 // (|ReferenceCS::referredKeys| - 1)
			= new SubtractCardinalitySolution(_093, _001);
		private final @NonNull CardinalitySolution _197 // (|ReferenceCS::referredKeys| > 0)
			= new GreaterThanCardinalitySolution(_093, _000);
		private final @NonNull CardinalitySolution _198 // (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new SubtractCardinalitySolution(_096, _001);
		private final @NonNull CardinalitySolution _199 // (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new GreaterThanCardinalitySolution(_096, _000);
		private final @NonNull CardinalitySolution _200 // (|ShadowPartCS::ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_097, _001);
		private final @NonNull CardinalitySolution _201 // (|ShadowPartCS::referredProperty| - 1)
			= new SubtractCardinalitySolution(_098, _001);
		private final @NonNull CardinalitySolution _202 // (|SpecificationCS::exprString| - 1)
			= new SubtractCardinalitySolution(_099, _001);
		private final @NonNull CardinalitySolution _203 // (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new SubtractCardinalitySolution(_100, _001);
		private final @NonNull CardinalitySolution _204 // (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
			= new GreaterThanCardinalitySolution(_103, _000);
		private final @NonNull CardinalitySolution _205 // (|StructuredClassCS::isInterface.'interface'| > 0)
			= new GreaterThanCardinalitySolution(_105, _000);
		private final @NonNull CardinalitySolution _206 // (|StructuredClassCS::ownedSuperTypes| - 1)
			= new SubtractCardinalitySolution(_108, _001);
		private final @NonNull CardinalitySolution _207 // (|StructuredClassCS::ownedSuperTypes| > 0)
			= new GreaterThanCardinalitySolution(_108, _000);
		private final @NonNull CardinalitySolution _208 // (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new SubtractCardinalitySolution(_110, _001);
		private final @NonNull CardinalitySolution _209 // (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
			= new SubtractCardinalitySolution(_111, _001);
		private final @NonNull CardinalitySolution _210 // (|TemplateSignatureCS::ownedParameters| - 1)
			= new SubtractCardinalitySolution(_112, _001);
		private final @NonNull CardinalitySolution _211 // (|TupleLiteralExpCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_114, _001);
		private final @NonNull CardinalitySolution _212 // (|TupleTypeCS::name.'Tuple'| - 1)
			= new SubtractCardinalitySolution(_115, _001);
		private final @NonNull CardinalitySolution _213 // (|TupleTypeCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_116, _001);
		private final @NonNull CardinalitySolution _214 // (|TupleTypeCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_116, _000);
		private final @NonNull CardinalitySolution _215 // (|TypeLiteralExpCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_117, _001);
		private final @NonNull CardinalitySolution _216 // (|TypeNameExpCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_119, _001);
		private final @NonNull CardinalitySolution _217 // (|TypeParameterCS::ownedExtends| - 1)
			= new SubtractCardinalitySolution(_121, _001);
		private final @NonNull CardinalitySolution _218 // (|TypeParameterCS::ownedExtends| > 0)
			= new GreaterThanCardinalitySolution(_121, _000);
		private final @NonNull CardinalitySolution _219 // (|TypedElementCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_122, _001);
		private final @NonNull CardinalitySolution _220 // (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new GreaterThanCardinalitySolution(_123, _000);
		private final @NonNull CardinalitySolution _221 // (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new GreaterThanCardinalitySolution(_124, _000);
		private final @NonNull CardinalitySolution _222 // (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new GreaterThanCardinalitySolution(_125, _000);
		private final @NonNull CardinalitySolution _223 // (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new GreaterThanCardinalitySolution(_126, _000);
		private final @NonNull CardinalitySolution _224 // (|TypedElementCS::qualifiers.'definition'| - 1)
			= new SubtractCardinalitySolution(_127, _001);
		private final @NonNull CardinalitySolution _225 // (|TypedElementCS::qualifiers.'static'| - 1)
			= new SubtractCardinalitySolution(_128, _001);
		private final @NonNull CardinalitySolution _226 // (|TypedTypeRefCS::ownedBinding| - 1)
			= new SubtractCardinalitySolution(_130, _001);
		private final @NonNull CardinalitySolution _227 // (|TypedTypeRefCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_131, _001);
		private final @NonNull CardinalitySolution _228 // (|VariableCS::ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_132, _001);
	}

	/**
	 * Steps for the matching process.
	 */
	private class _MatchSteps
	{
		private final @NonNull CardinalitySolutionStep _000 // assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._135);
		private final @NonNull CardinalitySolutionStep _001 // assert (|AnnotationElementCS::ownedDetails| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._136);
		private final @NonNull CardinalitySolutionStep _002 // assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._138);
		private final @NonNull CardinalitySolutionStep _003 // assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._141);
		private final @NonNull CardinalitySolutionStep _004 // assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._142);
		private final @NonNull CardinalitySolutionStep _005 // assert (|CollectionPatternCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._144);
		private final @NonNull CardinalitySolutionStep _006 // assert (|CollectionTypeCS::name| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._145);
		private final @NonNull CardinalitySolutionStep _007 // assert (|ConstraintCS::stereotype.'invariant'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._146);
		private final @NonNull CardinalitySolutionStep _008 // assert (|ConstraintCS::stereotype.'postcondition'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._147);
		private final @NonNull CardinalitySolutionStep _009 // assert (|ConstraintCS::stereotype.'precondition'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._148);
		private final @NonNull CardinalitySolutionStep _010 // assert (|ContextCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._149);
		private final @NonNull CardinalitySolutionStep _011 // assert (|ExpSpecificationCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._154);
		private final @NonNull CardinalitySolutionStep _012 // assert (|IfExpCS::ownedCondition| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._155);
		private final @NonNull CardinalitySolutionStep _013 // assert (|IfExpCS::ownedElseExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._156);
		private final @NonNull CardinalitySolutionStep _014 // assert (|IfExpCS::ownedThenExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._157);
		private final @NonNull CardinalitySolutionStep _015 // assert (|IfThenExpCS::ownedCondition| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._158);
		private final @NonNull CardinalitySolutionStep _016 // assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._159);
		private final @NonNull CardinalitySolutionStep _017 // assert (|ImportCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._160);
		private final @NonNull CardinalitySolutionStep _018 // assert (|InfixExpCS::ownedLeft| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._161);
		private final @NonNull CardinalitySolutionStep _019 // assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._162);
		private final @NonNull CardinalitySolutionStep _020 // assert (|LetExpCS::ownedInExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._163);
		private final @NonNull CardinalitySolutionStep _021 // assert (|MapLiteralExpCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._167);
		private final @NonNull CardinalitySolutionStep _022 // assert (|MapLiteralPartCS::ownedKey| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._168);
		private final @NonNull CardinalitySolutionStep _023 // assert (|MapLiteralPartCS::ownedValue| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._169);
		private final @NonNull CardinalitySolutionStep _024 // assert (|MapTypeCS::name.'Map'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._170);
		private final @NonNull CardinalitySolutionStep _025 // assert (|MapTypeCS::ownedKeyType| - V0) == 0
			= new CardinalitySolutionStep_Assert(mt._171);
		private final @NonNull CardinalitySolutionStep _026 // assert (|ModelElementRefCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._173);
		private final @NonNull CardinalitySolutionStep _027 // assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._174);
		private final @NonNull CardinalitySolutionStep _028 // assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._175);
		private final @NonNull CardinalitySolutionStep _029 // assert (|NamedElementCS::name| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._176);
		private final @NonNull CardinalitySolutionStep _030 // assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._177);
		private final @NonNull CardinalitySolutionStep _031 // assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._178);
		private final @NonNull CardinalitySolutionStep _032 // assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._179);
		private final @NonNull CardinalitySolutionStep _033 // assert (|NavigatingArgCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._180);
		private final @NonNull CardinalitySolutionStep _034 // assert (|NavigatingArgCS::prefix.','| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._181);
		private final @NonNull CardinalitySolutionStep _035 // assert (|NavigatingArgCS::prefix.';'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._182);
		private final @NonNull CardinalitySolutionStep _036 // assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._183);
		private final @NonNull CardinalitySolutionStep _037 // assert (|NestedExpCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._184);
		private final @NonNull CardinalitySolutionStep _038 // assert (|NumberLiteralExpCS::symbol| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._185);
		private final @NonNull CardinalitySolutionStep _039 // assert (|OperatorExpCS::ownedRight| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._191);
		private final @NonNull CardinalitySolutionStep _040 // assert (|PathElementCS::referredElement| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._192);
		private final @NonNull CardinalitySolutionStep _041 // assert (|PathNameCS::ownedPathElements| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._193);
		private final @NonNull CardinalitySolutionStep _042 // assert (|PatternExpCS::ownedPatternType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._194);
		private final @NonNull CardinalitySolutionStep _043 // assert (|PrimitiveTypeRefCS::name| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._195);
		private final @NonNull CardinalitySolutionStep _044 // assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._200);
		private final @NonNull CardinalitySolutionStep _045 // assert (|ShadowPartCS::referredProperty| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._201);
		private final @NonNull CardinalitySolutionStep _046 // assert (|SpecificationCS::exprString| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._202);
		private final @NonNull CardinalitySolutionStep _047 // assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._209);
		private final @NonNull CardinalitySolutionStep _048 // assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._212);
		private final @NonNull CardinalitySolutionStep _049 // assert (|TypeLiteralExpCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._215);
		private final @NonNull CardinalitySolutionStep _050 // assert (|TypeNameExpCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._216);
		private final @NonNull CardinalitySolutionStep _051 // assert (|TypedElementCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._219);
		private final @NonNull CardinalitySolutionStep _052 // assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._224);
		private final @NonNull CardinalitySolutionStep _053 // assert (|TypedElementCS::qualifiers.'static'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._225);
		private final @NonNull CardinalitySolutionStep _054 // assert (|TypedTypeRefCS::ownedBinding| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._226);
		private final @NonNull CardinalitySolutionStep _055 // assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._227);
		private final @NonNull CardinalitySolutionStep _056 // assert (|VariableCS::ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._228);
		private final @NonNull CardinalitySolutionStep _057 // assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._140);
		private final @NonNull CardinalitySolutionStep _058 // assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._151);
		private final @NonNull CardinalitySolutionStep _059 // assign V0 = (|LetExpCS::ownedVariables| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._164);
		private final @NonNull CardinalitySolutionStep _060 // assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._166);
		private final @NonNull CardinalitySolutionStep _061 // assign V0 = (|PathNameCS::ownedPathElements| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._193);
		private final @NonNull CardinalitySolutionStep _062 // assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._199);
		private final @NonNull CardinalitySolutionStep _063 // assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._203);
		private final @NonNull CardinalitySolutionStep _064 // assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._208);
		private final @NonNull CardinalitySolutionStep _065 // assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._210);
		private final @NonNull CardinalitySolutionStep _066 // assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._211);
		private final @NonNull CardinalitySolutionStep _067 // assign V0 = (|TupleTypeCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._214);
		private final @NonNull CardinalitySolutionStep _068 // assign V0 = (|TypeParameterCS::ownedExtends| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._218);
		private final @NonNull CardinalitySolutionStep _069 // assign V0 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._223);
		private final @NonNull CardinalitySolutionStep _070 // assign V0 = 0
			= new CardinalitySolutionStep_Assign(0, mt._000);
		private final @NonNull CardinalitySolutionStep _071 // assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
			= new CardinalitySolutionStep_Assign(0, mt._007);
		private final @NonNull CardinalitySolutionStep _072 // assign V0 = |AnnotationElementCS::ownedDetails|
			= new CardinalitySolutionStep_Assign(0, mt._010);
		private final @NonNull CardinalitySolutionStep _073 // assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
			= new CardinalitySolutionStep_Assign(0, mt._017);
		private final @NonNull CardinalitySolutionStep _074 // assign V0 = |CollectionPatternCS::restVariableName|
			= new CardinalitySolutionStep_Assign(0, mt._020);
		private final @NonNull CardinalitySolutionStep _075 // assign V0 = |CollectionTypeCS::ownedType|
			= new CardinalitySolutionStep_Assign(0, mt._023);
		private final @NonNull CardinalitySolutionStep _076 // assign V0 = |DataTypeCS::isPrimitive.'primitive'|
			= new CardinalitySolutionStep_Assign(0, mt._031);
		private final @NonNull CardinalitySolutionStep _077 // assign V0 = |DetailCS::values|
			= new CardinalitySolutionStep_Assign(0, mt._033);
		private final @NonNull CardinalitySolutionStep _078 // assign V0 = |DocumentationCS::value|
			= new CardinalitySolutionStep_Assign(0, mt._034);
		private final @NonNull CardinalitySolutionStep _079 // assign V0 = |EnumerationLiteralCS::literal|
			= new CardinalitySolutionStep_Assign(0, mt._037);
		private final @NonNull CardinalitySolutionStep _080 // assign V0 = |IfExpCS::ownedIfThenExpressions|
			= new CardinalitySolutionStep_Assign(0, mt._042);
		private final @NonNull CardinalitySolutionStep _081 // assign V0 = |LetVariableCS::ownedRoundBracketedClause|
			= new CardinalitySolutionStep_Assign(0, mt._052);
		private final @NonNull CardinalitySolutionStep _082 // assign V0 = |MapTypeCS::ownedValueType|
			= new CardinalitySolutionStep_Assign(0, mt._059);
		private final @NonNull CardinalitySolutionStep _083 // assign V0 = |MultiplicityBoundsCS::upperBound|
			= new CardinalitySolutionStep_Assign(0, mt._063);
		private final @NonNull CardinalitySolutionStep _084 // assign V0 = |MultiplicityCS::isNullFree.'|1'|
			= new CardinalitySolutionStep_Assign(0, mt._064);
		private final @NonNull CardinalitySolutionStep _085 // assign V0 = |NamedElementCS::name|
			= new CardinalitySolutionStep_Assign(0, mt._066);
		private final @NonNull CardinalitySolutionStep _086 // assign V0 = |NavigatingArgCS::ownedCoIterator|
			= new CardinalitySolutionStep_Assign(0, mt._067);
		private final @NonNull CardinalitySolutionStep _087 // assign V0 = |NavigatingArgCS::ownedInitExpression|
			= new CardinalitySolutionStep_Assign(0, mt._068);
		private final @NonNull CardinalitySolutionStep _088 // assign V0 = |NavigatingArgCS::ownedType|
			= new CardinalitySolutionStep_Assign(0, mt._070);
		private final @NonNull CardinalitySolutionStep _089 // assign V0 = |OCLinEcoreConstraintCS::isCallable.'callable'|
			= new CardinalitySolutionStep_Assign(0, mt._076);
		private final @NonNull CardinalitySolutionStep _090 // assign V0 = |PackageCS::nsPrefix|
			= new CardinalitySolutionStep_Assign(0, mt._083);
		private final @NonNull CardinalitySolutionStep _091 // assign V0 = |PatternExpCS::patternVariableName|
			= new CardinalitySolutionStep_Assign(0, mt._090);
		private final @NonNull CardinalitySolutionStep _092 // assign V0 = |ReferenceCS::referredOpposite|
			= new CardinalitySolutionStep_Assign(0, mt._094);
		private final @NonNull CardinalitySolutionStep _093 // assign V0 = |StringLiteralExpCS::segments|
			= new CardinalitySolutionStep_Assign(0, mt._101);
		private final @NonNull CardinalitySolutionStep _094 // assign V0 = |StructuredClassCS::isAbstract.'abstract'|
			= new CardinalitySolutionStep_Assign(0, mt._104);
		private final @NonNull CardinalitySolutionStep _095 // assign V0 = |TemplateableElementCS::ownedSignature|
			= new CardinalitySolutionStep_Assign(0, mt._113);
		private final @NonNull CardinalitySolutionStep _096 // assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
			= new CardinalitySolutionStep_Assign(0, mt._118);
		private final @NonNull CardinalitySolutionStep _097 // assign V0 = |TypedElementCS::ownedType|
			= new CardinalitySolutionStep_Assign(0, mt._122);
		private final @NonNull CardinalitySolutionStep _098 // assign V0 = |TypedElementCS::qualifiers.'definition'|
			= new CardinalitySolutionStep_Assign(0, mt._127);
		private final @NonNull CardinalitySolutionStep _099 // assign V0 = |TypedElementCS::qualifiers.'static'|
			= new CardinalitySolutionStep_Assign(0, mt._128);
		private final @NonNull CardinalitySolutionStep _100 // assign V0 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(0, mt._129);
		private final @NonNull CardinalitySolutionStep _101 // assign V0 = |VariableCS::ownedType|
			= new CardinalitySolutionStep_Assign(0, mt._133);
		private final @NonNull CardinalitySolutionStep _102 // assign V0 = |WildcardTypeRefCS::ownedExtends|
			= new CardinalitySolutionStep_Assign(0, mt._134);
		private final @NonNull CardinalitySolutionStep _103 // assign V1 = (|AnnotationElementCS::ownedDetails| > 0)
			= new CardinalitySolutionStep_Assign(1, mt._137);
		private final @NonNull CardinalitySolutionStep _104 // assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._139);
		private final @NonNull CardinalitySolutionStep _105 // assign V1 = (|CollectionPatternCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._143);
		private final @NonNull CardinalitySolutionStep _106 // assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._150);
		private final @NonNull CardinalitySolutionStep _107 // assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._165);
		private final @NonNull CardinalitySolutionStep _108 // assign V1 = (|OperationCS::ownedParameters| > 0)
			= new CardinalitySolutionStep_Assign(1, mt._190);
		private final @NonNull CardinalitySolutionStep _109 // assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._198);
		private final @NonNull CardinalitySolutionStep _110 // assign V1 = (|TupleTypeCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(1, mt._214);
		private final @NonNull CardinalitySolutionStep _111 // assign V1 = (|TypeParameterCS::ownedExtends| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._217);
		private final @NonNull CardinalitySolutionStep _112 // assign V1 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new CardinalitySolutionStep_Assign(1, mt._223);
		private final @NonNull CardinalitySolutionStep _113 // assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
			= new CardinalitySolutionStep_Assign(1, mt._006);
		private final @NonNull CardinalitySolutionStep _114 // assign V1 = |ClassCS::instanceClassName|
			= new CardinalitySolutionStep_Assign(1, mt._012);
		private final @NonNull CardinalitySolutionStep _115 // assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
			= new CardinalitySolutionStep_Assign(1, mt._022);
		private final @NonNull CardinalitySolutionStep _116 // assign V1 = |ConstraintCS::ownedMessageSpecification|
			= new CardinalitySolutionStep_Assign(1, mt._024);
		private final @NonNull CardinalitySolutionStep _117 // assign V1 = |EnumerationLiteralCS::value|
			= new CardinalitySolutionStep_Assign(1, mt._038);
		private final @NonNull CardinalitySolutionStep _118 // assign V1 = |ImportCS::isAll.'::*'|
			= new CardinalitySolutionStep_Assign(1, mt._046);
		private final @NonNull CardinalitySolutionStep _119 // assign V1 = |MultiplicityCS::isNullFree.'|1'|
			= new CardinalitySolutionStep_Assign(1, mt._064);
		private final @NonNull CardinalitySolutionStep _120 // assign V1 = |NamedElementCS::name|
			= new CardinalitySolutionStep_Assign(1, mt._066);
		private final @NonNull CardinalitySolutionStep _121 // assign V1 = |NavigatingArgCS::ownedCoIterator|
			= new CardinalitySolutionStep_Assign(1, mt._067);
		private final @NonNull CardinalitySolutionStep _122 // assign V1 = |NavigatingArgCS::ownedInitExpression|
			= new CardinalitySolutionStep_Assign(1, mt._068);
		private final @NonNull CardinalitySolutionStep _123 // assign V1 = |PackageCS::nsURI|
			= new CardinalitySolutionStep_Assign(1, mt._084);
		private final @NonNull CardinalitySolutionStep _124 // assign V1 = |ReferenceCS::referredOpposite|
			= new CardinalitySolutionStep_Assign(1, mt._094);
		private final @NonNull CardinalitySolutionStep _125 // assign V1 = |RootCS::ownedImports|
			= new CardinalitySolutionStep_Assign(1, mt._095);
		private final @NonNull CardinalitySolutionStep _126 // assign V1 = |StructuralFeatureCS::default|
			= new CardinalitySolutionStep_Assign(1, mt._102);
		private final @NonNull CardinalitySolutionStep _127 // assign V1 = |TemplateBindingCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(1, mt._109);
		private final @NonNull CardinalitySolutionStep _128 // assign V1 = |TemplateableElementCS::ownedSignature|
			= new CardinalitySolutionStep_Assign(1, mt._113);
		private final @NonNull CardinalitySolutionStep _129 // assign V1 = |TypeNameExpCS::ownedPatternGuard|
			= new CardinalitySolutionStep_Assign(1, mt._120);
		private final @NonNull CardinalitySolutionStep _130 // assign V1 = |TypedElementCS::ownedType|
			= new CardinalitySolutionStep_Assign(1, mt._122);
		private final @NonNull CardinalitySolutionStep _131 // assign V1 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'|
			= new CardinalitySolutionStep_Assign(1, mt._126);
		private final @NonNull CardinalitySolutionStep _132 // assign V1 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(1, mt._129);
		private final @NonNull CardinalitySolutionStep _133 // assign V1 = |VariableCS::ownedType|
			= new CardinalitySolutionStep_Assign(1, mt._133);
		private final @NonNull CardinalitySolutionStep _134 // assign V10 = (|OperationCS::ownedBodyExpressions| > 0)
			= new CardinalitySolutionStep_Assign(10, mt._186);
		private final @NonNull CardinalitySolutionStep _135 // assign V10 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
			= new CardinalitySolutionStep_Assign(10, mt._204);
		private final @NonNull CardinalitySolutionStep _136 // assign V10 = 0
			= new CardinalitySolutionStep_Assign(10, mt._000);
		private final @NonNull CardinalitySolutionStep _137 // assign V10 = |ClassCS::ownedConstraints|
			= new CardinalitySolutionStep_Assign(10, mt._013);
		private final @NonNull CardinalitySolutionStep _138 // assign V10 = |OperationCS::ownedPreconditions|
			= new CardinalitySolutionStep_Assign(10, mt._081);
		private final @NonNull CardinalitySolutionStep _139 // assign V11 = (|OperationCS::ownedBodyExpressions| > 0)
			= new CardinalitySolutionStep_Assign(11, mt._186);
		private final @NonNull CardinalitySolutionStep _140 // assign V11 = 0
			= new CardinalitySolutionStep_Assign(11, mt._000);
		private final @NonNull CardinalitySolutionStep _141 // assign V11 = |OperationCS::ownedBodyExpressions|
			= new CardinalitySolutionStep_Assign(11, mt._077);
		private final @NonNull CardinalitySolutionStep _142 // assign V12 = 0
			= new CardinalitySolutionStep_Assign(12, mt._000);
		private final @NonNull CardinalitySolutionStep _143 // assign V12 = |OperationCS::ownedBodyExpressions|
			= new CardinalitySolutionStep_Assign(12, mt._077);
		private final @NonNull CardinalitySolutionStep _144 // assign V12 = |OperationCS::ownedPostconditions|
			= new CardinalitySolutionStep_Assign(12, mt._080);
		private final @NonNull CardinalitySolutionStep _145 // assign V12 = |ReferenceCS::ownedImplicitOpposites|
			= new CardinalitySolutionStep_Assign(12, mt._092);
		private final @NonNull CardinalitySolutionStep _146 // assign V13 = |OperationCS::ownedPostconditions|
			= new CardinalitySolutionStep_Assign(13, mt._080);
		private final @NonNull CardinalitySolutionStep _147 // assign V13 = |ReferenceCS::ownedImplicitOpposites|
			= new CardinalitySolutionStep_Assign(13, mt._092);
		private final @NonNull CardinalitySolutionStep _148 // assign V2 = (|AnnotationElementCS::ownedDetails| - 1)
			= new CardinalitySolutionStep_Assign(2, mt._136);
		private final @NonNull CardinalitySolutionStep _149 // assign V2 = (|EnumerationCS::isSerializable.'serializable'| > 0)
			= new CardinalitySolutionStep_Assign(2, mt._153);
		private final @NonNull CardinalitySolutionStep _150 // assign V2 = (|OperationCS::ownedParameters| - 1)
			= new CardinalitySolutionStep_Assign(2, mt._189);
		private final @NonNull CardinalitySolutionStep _151 // assign V2 = (|OperationCS::ownedParameters| > 0)
			= new CardinalitySolutionStep_Assign(2, mt._190);
		private final @NonNull CardinalitySolutionStep _152 // assign V2 = (|StructuredClassCS::ownedSuperTypes| > 0)
			= new CardinalitySolutionStep_Assign(2, mt._207);
		private final @NonNull CardinalitySolutionStep _153 // assign V2 = (|TupleTypeCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(2, mt._213);
		private final @NonNull CardinalitySolutionStep _154 // assign V2 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new CardinalitySolutionStep_Assign(2, mt._221);
		private final @NonNull CardinalitySolutionStep _155 // assign V2 = 0
			= new CardinalitySolutionStep_Assign(2, mt._000);
		private final @NonNull CardinalitySolutionStep _156 // assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
			= new CardinalitySolutionStep_Assign(2, mt._004);
		private final @NonNull CardinalitySolutionStep _157 // assign V2 = |ClassCS::instanceClassName|
			= new CardinalitySolutionStep_Assign(2, mt._012);
		private final @NonNull CardinalitySolutionStep _158 // assign V2 = |ConstraintCS::ownedMessageSpecification|
			= new CardinalitySolutionStep_Assign(2, mt._024);
		private final @NonNull CardinalitySolutionStep _159 // assign V2 = |ConstraintCS::ownedSpecification|
			= new CardinalitySolutionStep_Assign(2, mt._025);
		private final @NonNull CardinalitySolutionStep _160 // assign V2 = |ModelElementCS::ownedAnnotations|
			= new CardinalitySolutionStep_Assign(2, mt._060);
		private final @NonNull CardinalitySolutionStep _161 // assign V2 = |PackageOwnerCS::ownedPackages|
			= new CardinalitySolutionStep_Assign(2, mt._086);
		private final @NonNull CardinalitySolutionStep _162 // assign V2 = |StructuralFeatureCS::default|
			= new CardinalitySolutionStep_Assign(2, mt._102);
		private final @NonNull CardinalitySolutionStep _163 // assign V2 = |TypedElementCS::ownedType|
			= new CardinalitySolutionStep_Assign(2, mt._122);
		private final @NonNull CardinalitySolutionStep _164 // assign V2 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'|
			= new CardinalitySolutionStep_Assign(2, mt._126);
		private final @NonNull CardinalitySolutionStep _165 // assign V2 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(2, mt._129);
		private final @NonNull CardinalitySolutionStep _166 // assign V3 = (|DataTypeCS::isSerializable.'serializable'| > 0)
			= new CardinalitySolutionStep_Assign(3, mt._152);
		private final @NonNull CardinalitySolutionStep _167 // assign V3 = (|ModelElementCS::ownedAnnotations| > 0)
			= new CardinalitySolutionStep_Assign(3, mt._172);
		private final @NonNull CardinalitySolutionStep _168 // assign V3 = (|OperationCS::ownedParameters| - 1)
			= new CardinalitySolutionStep_Assign(3, mt._189);
		private final @NonNull CardinalitySolutionStep _169 // assign V3 = (|StructuredClassCS::ownedSuperTypes| - 1)
			= new CardinalitySolutionStep_Assign(3, mt._206);
		private final @NonNull CardinalitySolutionStep _170 // assign V3 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new CardinalitySolutionStep_Assign(3, mt._220);
		private final @NonNull CardinalitySolutionStep _171 // assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new CardinalitySolutionStep_Assign(3, mt._221);
		private final @NonNull CardinalitySolutionStep _172 // assign V3 = 0
			= new CardinalitySolutionStep_Assign(3, mt._000);
		private final @NonNull CardinalitySolutionStep _173 // assign V3 = |AbstractNameExpCS::isPre.'@'|
			= new CardinalitySolutionStep_Assign(3, mt._003);
		private final @NonNull CardinalitySolutionStep _174 // assign V3 = |ConstraintCS::ownedSpecification|
			= new CardinalitySolutionStep_Assign(3, mt._025);
		private final @NonNull CardinalitySolutionStep _175 // assign V3 = |EnumerationCS::isSerializable.'serializable'|
			= new CardinalitySolutionStep_Assign(3, mt._035);
		private final @NonNull CardinalitySolutionStep _176 // assign V3 = |ModelElementCS::ownedAnnotations|
			= new CardinalitySolutionStep_Assign(3, mt._060);
		private final @NonNull CardinalitySolutionStep _177 // assign V3 = |PackageOwnerCS::ownedPackages|
			= new CardinalitySolutionStep_Assign(3, mt._086);
		private final @NonNull CardinalitySolutionStep _178 // assign V3 = |StructuralFeatureCS::default|
			= new CardinalitySolutionStep_Assign(3, mt._102);
		private final @NonNull CardinalitySolutionStep _179 // assign V3 = |TypedElementCS::ownedType|
			= new CardinalitySolutionStep_Assign(3, mt._122);
		private final @NonNull CardinalitySolutionStep _180 // assign V3 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new CardinalitySolutionStep_Assign(3, mt._124);
		private final @NonNull CardinalitySolutionStep _181 // assign V3 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(3, mt._129);
		private final @NonNull CardinalitySolutionStep _182 // assign V4 = (|OperationCS::ownedExceptions| > 0)
			= new CardinalitySolutionStep_Assign(4, mt._188);
		private final @NonNull CardinalitySolutionStep _183 // assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new CardinalitySolutionStep_Assign(4, mt._220);
		private final @NonNull CardinalitySolutionStep _184 // assign V4 = |AnnotationCS::ownedContents|
			= new CardinalitySolutionStep_Assign(4, mt._008);
		private final @NonNull CardinalitySolutionStep _185 // assign V4 = |ClassCS::instanceClassName|
			= new CardinalitySolutionStep_Assign(4, mt._012);
		private final @NonNull CardinalitySolutionStep _186 // assign V4 = |DataTypeCS::isSerializable.'serializable'|
			= new CardinalitySolutionStep_Assign(4, mt._032);
		private final @NonNull CardinalitySolutionStep _187 // assign V4 = |EnumerationCS::ownedLiterals|
			= new CardinalitySolutionStep_Assign(4, mt._036);
		private final @NonNull CardinalitySolutionStep _188 // assign V4 = |ModelElementCS::ownedAnnotations|
			= new CardinalitySolutionStep_Assign(4, mt._060);
		private final @NonNull CardinalitySolutionStep _189 // assign V4 = |PackageCS::ownedClasses|
			= new CardinalitySolutionStep_Assign(4, mt._085);
		private final @NonNull CardinalitySolutionStep _190 // assign V4 = |TypedElementCS::ownedType|
			= new CardinalitySolutionStep_Assign(4, mt._122);
		private final @NonNull CardinalitySolutionStep _191 // assign V4 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new CardinalitySolutionStep_Assign(4, mt._123);
		private final @NonNull CardinalitySolutionStep _192 // assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new CardinalitySolutionStep_Assign(4, mt._124);
		private final @NonNull CardinalitySolutionStep _193 // assign V5 = (|OperationCS::ownedExceptions| - 1)
			= new CardinalitySolutionStep_Assign(5, mt._187);
		private final @NonNull CardinalitySolutionStep _194 // assign V5 = (|OperationCS::ownedExceptions| > 0)
			= new CardinalitySolutionStep_Assign(5, mt._188);
		private final @NonNull CardinalitySolutionStep _195 // assign V5 = (|StructuredClassCS::isInterface.'interface'| > 0)
			= new CardinalitySolutionStep_Assign(5, mt._205);
		private final @NonNull CardinalitySolutionStep _196 // assign V5 = |AnnotationCS::ownedReferences|
			= new CardinalitySolutionStep_Assign(5, mt._009);
		private final @NonNull CardinalitySolutionStep _197 // assign V5 = |ClassCS::ownedConstraints|
			= new CardinalitySolutionStep_Assign(5, mt._013);
		private final @NonNull CardinalitySolutionStep _198 // assign V5 = |EnumerationCS::ownedLiterals|
			= new CardinalitySolutionStep_Assign(5, mt._036);
		private final @NonNull CardinalitySolutionStep _199 // assign V5 = |ModelElementCS::ownedAnnotations|
			= new CardinalitySolutionStep_Assign(5, mt._060);
		private final @NonNull CardinalitySolutionStep _200 // assign V5 = |StructuralFeatureCS::ownedDefaultExpressions|
			= new CardinalitySolutionStep_Assign(5, mt._103);
		private final @NonNull CardinalitySolutionStep _201 // assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new CardinalitySolutionStep_Assign(5, mt._123);
		private final @NonNull CardinalitySolutionStep _202 // assign V6 = (|OperationCS::ownedExceptions| - 1)
			= new CardinalitySolutionStep_Assign(6, mt._187);
		private final @NonNull CardinalitySolutionStep _203 // assign V6 = (|ReferenceCS::referredKeys| > 0)
			= new CardinalitySolutionStep_Assign(6, mt._197);
		private final @NonNull CardinalitySolutionStep _204 // assign V6 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
			= new CardinalitySolutionStep_Assign(6, mt._204);
		private final @NonNull CardinalitySolutionStep _205 // assign V6 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new CardinalitySolutionStep_Assign(6, mt._222);
		private final @NonNull CardinalitySolutionStep _206 // assign V6 = |ClassCS::ownedConstraints|
			= new CardinalitySolutionStep_Assign(6, mt._013);
		private final @NonNull CardinalitySolutionStep _207 // assign V6 = |ModelElementCS::ownedAnnotations|
			= new CardinalitySolutionStep_Assign(6, mt._060);
		private final @NonNull CardinalitySolutionStep _208 // assign V6 = |StructuralFeatureCS::ownedDefaultExpressions|
			= new CardinalitySolutionStep_Assign(6, mt._103);
		private final @NonNull CardinalitySolutionStep _209 // assign V6 = |StructuredClassCS::isInterface.'interface'|
			= new CardinalitySolutionStep_Assign(6, mt._105);
		private final @NonNull CardinalitySolutionStep _210 // assign V7 = (|ReferenceCS::referredKeys| - 1)
			= new CardinalitySolutionStep_Assign(7, mt._196);
		private final @NonNull CardinalitySolutionStep _211 // assign V7 = (|ReferenceCS::referredKeys| > 0)
			= new CardinalitySolutionStep_Assign(7, mt._197);
		private final @NonNull CardinalitySolutionStep _212 // assign V7 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
			= new CardinalitySolutionStep_Assign(7, mt._204);
		private final @NonNull CardinalitySolutionStep _213 // assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new CardinalitySolutionStep_Assign(7, mt._222);
		private final @NonNull CardinalitySolutionStep _214 // assign V7 = 0
			= new CardinalitySolutionStep_Assign(7, mt._000);
		private final @NonNull CardinalitySolutionStep _215 // assign V7 = |ModelElementCS::ownedAnnotations|
			= new CardinalitySolutionStep_Assign(7, mt._060);
		private final @NonNull CardinalitySolutionStep _216 // assign V7 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new CardinalitySolutionStep_Assign(7, mt._125);
		private final @NonNull CardinalitySolutionStep _217 // assign V8 = (|ReferenceCS::referredKeys| - 1)
			= new CardinalitySolutionStep_Assign(8, mt._196);
		private final @NonNull CardinalitySolutionStep _218 // assign V8 = 0
			= new CardinalitySolutionStep_Assign(8, mt._000);
		private final @NonNull CardinalitySolutionStep _219 // assign V8 = |ModelElementCS::ownedAnnotations|
			= new CardinalitySolutionStep_Assign(8, mt._060);
		private final @NonNull CardinalitySolutionStep _220 // assign V8 = |StructuralFeatureCS::ownedDefaultExpressions|
			= new CardinalitySolutionStep_Assign(8, mt._103);
		private final @NonNull CardinalitySolutionStep _221 // assign V8 = |StructuredClassCS::ownedOperations|
			= new CardinalitySolutionStep_Assign(8, mt._106);
		private final @NonNull CardinalitySolutionStep _222 // assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new CardinalitySolutionStep_Assign(8, mt._125);
		private final @NonNull CardinalitySolutionStep _223 // assign V9 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
			= new CardinalitySolutionStep_Assign(9, mt._204);
		private final @NonNull CardinalitySolutionStep _224 // assign V9 = 0
			= new CardinalitySolutionStep_Assign(9, mt._000);
		private final @NonNull CardinalitySolutionStep _225 // assign V9 = |ModelElementCS::ownedAnnotations|
			= new CardinalitySolutionStep_Assign(9, mt._060);
		private final @NonNull CardinalitySolutionStep _226 // assign V9 = |OperationCS::ownedPreconditions|
			= new CardinalitySolutionStep_Assign(9, mt._081);
		private final @NonNull CardinalitySolutionStep _227 // assign V9 = |StructuralFeatureCS::ownedDefaultExpressions|
			= new CardinalitySolutionStep_Assign(9, mt._103);
		private final @NonNull CardinalitySolutionStep _228 // assign V9 = |StructuredClassCS::ownedProperties|
			= new CardinalitySolutionStep_Assign(9, mt._107);
		private final @NonNull CardinalitySolutionStep _229 // check-rule basecs::AnnotationCS.ownedContents : 53
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, iv._17/*ModelElementCS*/);
		private final @NonNull CardinalitySolutionStep _230 // check-rule basecs::AnnotationCS.ownedReferences : 54
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, iv._18/*ModelElementRefCS*/);
		private final @NonNull CardinalitySolutionStep _231 // check-rule basecs::AnnotationElementCS.ownedDetails : 16
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, iv._6/*DetailCS*/);
		private final @NonNull CardinalitySolutionStep _232 // check-rule basecs::ClassCS.ownedConstraints : 41
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, iv._13/*InvariantConstraintCS*/);
		private final @NonNull CardinalitySolutionStep _233 // check-rule basecs::ConstraintCS.ownedMessageSpecification : 92
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, iv._36/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _234 // check-rule basecs::ConstraintCS.ownedSpecification : 92
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, iv._36/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _235 // check-rule basecs::EnumerationCS.ownedLiterals : 22
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, iv._8/*EnumerationLiteralCS*/);
		private final @NonNull CardinalitySolutionStep _236 // check-rule basecs::ImportCS.ownedPathName : 123
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, iv._64/*URIPathNameCS*/);
		private final @NonNull CardinalitySolutionStep _237 // check-rule basecs::ModelElementCS.ownedAnnotations : 2
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, iv._0/*AnnotationElementCS*/);
		private final @NonNull CardinalitySolutionStep _238 // check-rule basecs::ModelElementRefCS.ownedPathName : 73
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, iv._26/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _239 // check-rule basecs::OperationCS.ownedBodyExpressions : 92
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, iv._36/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _240 // check-rule basecs::OperationCS.ownedExceptions : 117
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, iv._59/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _241 // check-rule basecs::OperationCS.ownedParameters : 72
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, iv._25/*ParameterCS*/);
		private final @NonNull CardinalitySolutionStep _242 // check-rule basecs::OperationCS.ownedPostconditions : 75
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, iv._29/*PostconditionConstraintCS*/);
		private final @NonNull CardinalitySolutionStep _243 // check-rule basecs::OperationCS.ownedPreconditions : 76
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, iv._30/*PreconditionConstraintCS*/);
		private final @NonNull CardinalitySolutionStep _244 // check-rule basecs::PackageCS.ownedClasses : 6
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, iv._1/*ClassCS*/);
		private final @NonNull CardinalitySolutionStep _245 // check-rule basecs::PackageOwnerCS.ownedPackages : 71
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, iv._24/*PackageCS*/);
		private final @NonNull CardinalitySolutionStep _246 // check-rule basecs::PathNameCS.ownedPathElements : 31
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._10/*FirstPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _247 // check-rule basecs::PathNameCS.ownedPathElements : 31|67
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._22/*FirstPathElementCS|NextPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _248 // check-rule basecs::PathNameCS.ownedPathElements : 67|122
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._62/*NextPathElementCS|URIFirstPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _249 // check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, iv._11/*ImplicitOppositeCS*/);
		private final @NonNull CardinalitySolutionStep _250 // check-rule basecs::RootCS.ownedImports : 38
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, iv._12/*ImportCS*/);
		private final @NonNull CardinalitySolutionStep _251 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, iv._36/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _252 // check-rule basecs::StructuredClassCS.ownedOperations : 70
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, iv._23/*OperationCS*/);
		private final @NonNull CardinalitySolutionStep _253 // check-rule basecs::StructuredClassCS.ownedProperties : 96
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, iv._39/*StructuralFeatureCS*/);
		private final @NonNull CardinalitySolutionStep _254 // check-rule basecs::StructuredClassCS.ownedSuperTypes : 117
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, iv._59/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _255 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : 56
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, iv._19/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _256 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : 100
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, iv._45/*TemplateParameterSubstitutionCS*/);
		private final @NonNull CardinalitySolutionStep _257 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 115
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, iv._57/*TypeRefCS*/);
		private final @NonNull CardinalitySolutionStep _258 // check-rule basecs::TemplateSignatureCS.ownedParameters : 114
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, iv._56/*TypeParameterCS*/);
		private final @NonNull CardinalitySolutionStep _259 // check-rule basecs::TemplateableElementCS.ownedSignature : 101
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, iv._46/*TemplateSignatureCS*/);
		private final @NonNull CardinalitySolutionStep _260 // check-rule basecs::TupleTypeCS.ownedParts : 105
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, iv._48/*TuplePartCS*/);
		private final @NonNull CardinalitySolutionStep _261 // check-rule basecs::TypeParameterCS.ownedExtends : 117
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, iv._59/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _262 // check-rule basecs::TypedElementCS.ownedType : 107
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, iv._49/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _263 // check-rule basecs::TypedElementCS.ownedType : 116
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, iv._58/*TypedMultiplicityRefCS*/);
		private final @NonNull CardinalitySolutionStep _264 // check-rule basecs::TypedRefCS.ownedMultiplicity : 56
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, iv._19/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _265 // check-rule basecs::TypedTypeRefCS.ownedBinding : 99
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, iv._44/*TemplateBindingCS*/);
		private final @NonNull CardinalitySolutionStep _266 // check-rule basecs::TypedTypeRefCS.ownedPathName : 73
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, iv._26/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _267 // check-rule basecs::WildcardTypeRefCS.ownedExtends : 117
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, iv._59/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _268 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._5/*CurlyBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _269 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, iv._26/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _270 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._34/*RoundBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _271 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, iv._37/*SquareBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _272 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, iv._3/*CollectionLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _273 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, iv._4/*CollectionTypeCS*/);
		private final @NonNull CardinalitySolutionStep _274 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _275 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 74
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._27/*PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _276 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _277 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, iv._27/*PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _278 // check-rule essentialoclcs::CollectionPatternCS.ownedType : 11
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, iv._4/*CollectionTypeCS*/);
		private final @NonNull CardinalitySolutionStep _279 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, iv._19/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _280 // check-rule essentialoclcs::CollectionTypeCS.ownedType : 108
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, iv._50/*TypeExpWithoutMultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _281 // check-rule essentialoclcs::ContextCS.ownedExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _282 // check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 90
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, iv._35/*ShadowPartCS*/);
		private final @NonNull CardinalitySolutionStep _283 // check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _284 // check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, iv._28/*ExpCS|PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _285 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _286 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, iv._7/*ElseIfThenExpCS*/);
		private final @NonNull CardinalitySolutionStep _287 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _288 // check-rule essentialoclcs::IfThenExpCS.ownedCondition : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _289 // check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _290 // check-rule essentialoclcs::InfixExpCS.ownedLeft : 78
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, iv._33/*PrefixedPrimaryExpCS*/);
		private final @NonNull CardinalitySolutionStep _291 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _292 // check-rule essentialoclcs::LetExpCS.ownedInExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _293 // check-rule essentialoclcs::LetExpCS.ownedVariables : 46
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, iv._14/*LetVariableCS*/);
		private final @NonNull CardinalitySolutionStep _294 // check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 84
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._34/*RoundBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _295 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, iv._15/*MapLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _296 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, iv._16/*MapTypeCS*/);
		private final @NonNull CardinalitySolutionStep _297 // check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _298 // check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _299 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, iv._49/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _300 // check-rule essentialoclcs::MapTypeCS.ownedValueType : 107
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, iv._49/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _301 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, iv._2/*CoIteratorVariableCS*/);
		private final @NonNull CardinalitySolutionStep _302 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _303 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, iv._20/*NavigatingArgExpCS*/);
		private final @NonNull CardinalitySolutionStep _304 // check-rule essentialoclcs::NavigatingArgCS.ownedType : 107
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, iv._49/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _305 // check-rule essentialoclcs::NestedExpCS.ownedExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _306 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _307 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 77
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._31/*PrefixedLetExpCS*/);
		private final @NonNull CardinalitySolutionStep _308 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 78
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._33/*PrefixedPrimaryExpCS*/);
		private final @NonNull CardinalitySolutionStep _309 // check-rule essentialoclcs::PatternExpCS.ownedPatternType : 107
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, iv._49/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _310 // check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 60|62|63|64
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, iv._21/*NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS*/);
		private final @NonNull CardinalitySolutionStep _311 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 30|74
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._28/*ExpCS|PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _312 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 95
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._38/*StringLiteralExpCS*/);
		private final @NonNull CardinalitySolutionStep _313 // check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _314 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, iv._47/*TupleLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _315 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, iv._52/*TypeLiteralWithMultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _316 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._5/*CurlyBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _317 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, iv._26/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _318 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _319 // check-rule essentialoclcs::VariableCS.ownedInitExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _320 // check-rule essentialoclcs::VariableCS.ownedType : 107
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, iv._49/*TypeExpCS*/);
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
		private final @NonNull RTSerializationAssignedRuleCallStep _064 // 1*AbstractNameExpCS::ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _065 // 1*AnnotationElementCS::ownedDetails+=DetailCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
		private final @NonNull RTSerializationAssignStep _066 // 1*BooleanLiteralExpCS::symbol
									= new RTSerializationAssignStep(-1, EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull RTSerializationAssignedRuleCallStep _067 // 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _068 // 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _069 // 1*CollectionLiteralExpCS::ownedType=CollectionTypeCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _070 // 1*CollectionLiteralPartCS::ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _071 // 1*CollectionLiteralPartCS::ownedExpression=PatternExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _072 // 1*CollectionLiteralPartCS::ownedLastExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _073 // 1*CollectionPatternCS::ownedParts+=PatternExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _074 // 1*CollectionPatternCS::ownedType=CollectionTypeCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _075 // 1*CollectionPatternCS::restVariableName=Identifier
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _076 // 1*CollectionTypeCS::name=CollectionTypeIdentifier
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _077 // 1*CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _078 // 1*ConstraintCS::ownedMessageSpecification=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		private final @NonNull RTSerializationAssignedRuleCallStep _079 // 1*ContextCS::ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _080 // 1*CurlyBracketedClauseCS::ownedParts+=ShadowPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _081 // 1*EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL);
		private final @NonNull RTSerializationAssignedRuleCallStep _082 // 1*EnumerationLiteralCS::value=SIGNED
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE);
		private final @NonNull RTSerializationAssignedRuleCallStep _083 // 1*ExpSpecificationCS::ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallsStep _084 // 1*IfExpCS::ownedCondition=ExpCS|PatternExpCS
									= new RTSerializationAssignedRuleCallsStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationAssignedRuleCallStep _085 // 1*IfExpCS::ownedElseExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _086 // 1*IfExpCS::ownedThenExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _087 // 1*IfThenExpCS::ownedCondition=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		private final @NonNull RTSerializationAssignedRuleCallStep _088 // 1*IfThenExpCS::ownedThenExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _089 // 1*ImportCS::ownedPathName=URIPathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _090 // 1*InfixExpCS::ownedLeft=PrefixedPrimaryExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		private final @NonNull RTSerializationAssignedRuleCallStep _091 // 1*LambdaLiteralExpCS::ownedExpressionCS=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		private final @NonNull RTSerializationAssignedRuleCallStep _092 // 1*LetExpCS::ownedInExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _093 // 1*LetExpCS::ownedVariables+=LetVariableCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		private final @NonNull RTSerializationAssignedRuleCallStep _094 // 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _095 // 1*MapLiteralExpCS::ownedType=MapTypeCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _096 // 1*MapLiteralPartCS::ownedKey=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		private final @NonNull RTSerializationAssignedRuleCallStep _097 // 1*MapLiteralPartCS::ownedValue=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		private final @NonNull RTSerializationAssignedRuleCallStep _098 // 1*MapTypeCS::ownedKeyType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _099 // 1*MapTypeCS::ownedValueType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _100 // 1*ModelElementRefCS::ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _101 // 1*MultiplicityBoundsCS::lowerBound=LOWER
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull RTSerializationAssignedRuleCallStep _102 // 1*MultiplicityBoundsCS::upperBound=UPPER
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull RTSerializationAssignStep _103 // 1*MultiplicityStringCS::stringBounds
									= new RTSerializationAssignStep(-1, BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS);
		private final @NonNull RTSerializationAssignedRuleCallStep _104 // 1*NamedElementCS::name=BinaryOperatorName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _105 // 1*NamedElementCS::name=EnumerationLiteralName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _106 // 1*NamedElementCS::name=UnaryOperatorName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _107 // 1*NamedElementCS::name=UnrestrictedName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallsStep _108 // 1*NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallsStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationAssignedRuleCallStep _109 // 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		private final @NonNull RTSerializationAssignedRuleCallStep _110 // 1*NavigatingArgCS::ownedInitExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _111 // 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _112 // 1*NavigatingArgCS::ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _113 // 1*NestedExpCS::ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _114 // 1*NumberLiteralExpCS::symbol=NUMBER_LITERAL
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull RTSerializationAssignedRuleCallStep _115 // 1*OperationCS::ownedExceptions+=TypedRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _116 // 1*OperationCS::ownedParameters+=ParameterCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
		private final @NonNull RTSerializationAssignedRuleCallStep _117 // 1*OperatorExpCS::ownedRight=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull RTSerializationAssignedRuleCallStep _118 // 1*OperatorExpCS::ownedRight=PrefixedLetExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull RTSerializationAssignedRuleCallStep _119 // 1*OperatorExpCS::ownedRight=PrefixedPrimaryExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull RTSerializationAssignedRuleCallStep _120 // 1*PackageCS::nsPrefix=UnrestrictedName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
		private final @NonNull RTSerializationAssignedRuleCallStep _121 // 1*PackageCS::nsURI=URI
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
		private final @NonNull RTSerializationCrossReferenceStep _122 // 1*PathElementCS::referredElement=URI
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"));
		private final @NonNull RTSerializationCrossReferenceStep _123 // 1*PathElementCS::referredElement=UnreservedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"));
		private final @NonNull RTSerializationCrossReferenceStep _124 // 1*PathElementCS::referredElement=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull RTSerializationCrossReferenceStep _125 // 1*PathElementCS::referredElement=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _126 // 1*PathNameCS::ownedPathElements+=FirstPathElementCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _127 // 1*PathNameCS::ownedPathElements+=NextPathElementCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _128 // 1*PathNameCS::ownedPathElements+=URIFirstPathElementCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _129 // 1*PatternExpCS::ownedPatternType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _130 // 1*PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _131 // 1*ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES);
		private final @NonNull RTSerializationCrossReferenceStep _132 // 1*ReferenceCS::referredKeys+=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, getCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, "UnrestrictedName"));
		private final @NonNull RTSerializationCrossReferenceStep _133 // 1*ReferenceCS::referredKeys+=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, getCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, "UnrestrictedName"));
		private final @NonNull RTSerializationCrossReferenceStep _134 // 1*ReferenceCS::referredOpposite=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, getCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, "UnrestrictedName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _135 // 1*RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		private final @NonNull RTSerializationAssignedRuleCallsStep _136 // 1*ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS
									= new RTSerializationAssignedRuleCallsStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationAssignedRuleCallStep _137 // 1*ShadowPartCS::ownedInitExpression=StringLiteralExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull RTSerializationCrossReferenceStep _138 // 1*ShadowPartCS::referredProperty=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _139 // 1*SpecificationCS::exprString=UNQUOTED_STRING
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		private final @NonNull RTSerializationAssignedRuleCallStep _140 // 1*SquareBracketedClauseCS::ownedTerms+=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		private final @NonNull RTSerializationAssignedRuleCallStep _141 // 1*StructuralFeatureCS::default=SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
		private final @NonNull RTSerializationAssignedRuleCallStep _142 // 1*StructuredClassCS::ownedSuperTypes+=TypedRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
		private final @NonNull RTSerializationAssignedRuleCallStep _143 // 1*TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _144 // 1*TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull RTSerializationAssignedRuleCallStep _145 // 1*TemplateSignatureCS::ownedParameters+=TypeParameterCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull RTSerializationAssignedRuleCallStep _146 // 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _147 // 1*TupleTypeCS::ownedParts+=TuplePartCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _148 // 1*TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _149 // 1*TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _150 // 1*TypeNameExpCS::ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _151 // 1*TypeNameExpCS::ownedPatternGuard=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		private final @NonNull RTSerializationAssignedRuleCallStep _152 // 1*TypeParameterCS::ownedExtends+=TypedRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull RTSerializationAssignedRuleCallStep _153 // 1*TypedElementCS::ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _154 // 1*TypedElementCS::ownedType=TypedMultiplicityRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignStep _155 // 1*TypedElementCS::qualifiers
									= new RTSerializationAssignStep(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS);
		private final @NonNull RTSerializationAssignedRuleCallStep _156 // 1*TypedTypeRefCS::ownedBinding=TemplateBindingCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull RTSerializationAssignedRuleCallStep _157 // 1*TypedTypeRefCS::ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _158 // 1*VariableCS::ownedInitExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _159 // 1*VariableCS::ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _160 // 1*WildcardTypeRefCS::ownedExtends=TypedRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull RTSerializationSequenceStep _161 // 1*next-10-steps
									= new RTSerializationSequenceStep(-1, 1, 11);
		private final @NonNull RTSerializationSequenceStep _162 // 1*next-11-steps
									= new RTSerializationSequenceStep(-1, 1, 12);
		private final @NonNull RTSerializationSequenceStep _163 // 1*next-12-steps
									= new RTSerializationSequenceStep(-1, 1, 13);
		private final @NonNull RTSerializationSequenceStep _164 // 1*next-13-steps
									= new RTSerializationSequenceStep(-1, 1, 14);
		private final @NonNull RTSerializationSequenceStep _165 // 1*next-14-steps
									= new RTSerializationSequenceStep(-1, 1, 15);
		private final @NonNull RTSerializationSequenceStep _166 // 1*next-15-steps
									= new RTSerializationSequenceStep(-1, 1, 16);
		private final @NonNull RTSerializationSequenceStep _167 // 1*next-16-steps
									= new RTSerializationSequenceStep(-1, 1, 17);
		private final @NonNull RTSerializationSequenceStep _168 // 1*next-17-steps
									= new RTSerializationSequenceStep(-1, 1, 18);
		private final @NonNull RTSerializationSequenceStep _169 // 1*next-18-steps
									= new RTSerializationSequenceStep(-1, 1, 19);
		private final @NonNull RTSerializationSequenceStep _170 // 1*next-19-steps
									= new RTSerializationSequenceStep(-1, 1, 20);
		private final @NonNull RTSerializationSequenceStep _171 // 1*next-2-steps
									= new RTSerializationSequenceStep(-1, 1, 3);
		private final @NonNull RTSerializationSequenceStep _172 // 1*next-23-steps
									= new RTSerializationSequenceStep(-1, 1, 24);
		private final @NonNull RTSerializationSequenceStep _173 // 1*next-25-steps
									= new RTSerializationSequenceStep(-1, 1, 26);
		private final @NonNull RTSerializationSequenceStep _174 // 1*next-26-steps
									= new RTSerializationSequenceStep(-1, 1, 27);
		private final @NonNull RTSerializationSequenceStep _175 // 1*next-27-steps
									= new RTSerializationSequenceStep(-1, 1, 28);
		private final @NonNull RTSerializationSequenceStep _176 // 1*next-28-steps
									= new RTSerializationSequenceStep(-1, 1, 29);
		private final @NonNull RTSerializationSequenceStep _177 // 1*next-3-steps
									= new RTSerializationSequenceStep(-1, 1, 4);
		private final @NonNull RTSerializationSequenceStep _178 // 1*next-34-steps
									= new RTSerializationSequenceStep(-1, 1, 35);
		private final @NonNull RTSerializationSequenceStep _179 // 1*next-36-steps
									= new RTSerializationSequenceStep(-1, 1, 37);
		private final @NonNull RTSerializationSequenceStep _180 // 1*next-39-steps
									= new RTSerializationSequenceStep(-1, 1, 40);
		private final @NonNull RTSerializationSequenceStep _181 // 1*next-4-steps
									= new RTSerializationSequenceStep(-1, 1, 5);
		private final @NonNull RTSerializationSequenceStep _182 // 1*next-41-steps
									= new RTSerializationSequenceStep(-1, 1, 42);
		private final @NonNull RTSerializationSequenceStep _183 // 1*next-5-steps
									= new RTSerializationSequenceStep(-1, 1, 6);
		private final @NonNull RTSerializationSequenceStep _184 // 1*next-6-steps
									= new RTSerializationSequenceStep(-1, 1, 7);
		private final @NonNull RTSerializationSequenceStep _185 // 1*next-7-steps
									= new RTSerializationSequenceStep(-1, 1, 8);
		private final @NonNull RTSerializationSequenceStep _186 // 1*next-8-steps
									= new RTSerializationSequenceStep(-1, 1, 9);
		private final @NonNull RTSerializationSequenceStep _187 // 1*next-9-steps
									= new RTSerializationSequenceStep(-1, 1, 10);
		private final @NonNull RTSerializationLiteralStep _188 // V00*'abstract'
									= new RTSerializationLiteralStep(0, "abstract");
		private final @NonNull RTSerializationLiteralStep _189 // V00*'callable'
									= new RTSerializationLiteralStep(0, "callable");
		private final @NonNull RTSerializationLiteralStep _190 // V00*'definition'
									= new RTSerializationLiteralStep(0, "definition");
		private final @NonNull RTSerializationLiteralStep _191 // V00*'primitive'
									= new RTSerializationLiteralStep(0, "primitive");
		private final @NonNull RTSerializationLiteralStep _192 // V00*'static'
									= new RTSerializationLiteralStep(0, "static");
		private final @NonNull RTSerializationLiteralStep _193 // V00*'|1'
									= new RTSerializationLiteralStep(0, "|1");
		private final @NonNull RTSerializationAssignedRuleCallStep _194 // V00*AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		private final @NonNull RTSerializationAssignedRuleCallsStep _195 // V00*DetailCS::values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallsStep(0, BaseCSPackage.Literals.DETAIL_CS__VALUES,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationAssignedRuleCallStep _196 // V00*DocumentationCS::value=SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
		private final @NonNull RTSerializationAssignedRuleCallStep _197 // V00*IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _198 // V00*LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallsStep _199 // V00*NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING
									= new RTSerializationAssignedRuleCallsStep(0, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationAssignedRuleCallStep _200 // V00*PatternExpCS::patternVariableName=UnrestrictedName
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _201 // V00*StringLiteralExpCS::segments+=StringLiteral
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _202 // V00*TemplateableElementCS::ownedSignature=TemplateSignatureCS
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		private final @NonNull RTSerializationAssignedRuleCallStep _203 // V00*TypedRefCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationSequenceStep _204 // V00*next-1-steps
									= new RTSerializationSequenceStep(0, 2, 3);
		private final @NonNull RTSerializationSequenceStep _205 // V00*next-2-steps
									= new RTSerializationSequenceStep(0, 3, 5);
		private final @NonNull RTSerializationSequenceStep _206 // V00*next-2-steps
									= new RTSerializationSequenceStep(0, 4, 6);
		private final @NonNull RTSerializationSequenceStep _207 // V00*next-2-steps
									= new RTSerializationSequenceStep(0, 5, 7);
		private final @NonNull RTSerializationSequenceStep _208 // V00*next-2-steps
									= new RTSerializationSequenceStep(0, 6, 8);
		private final @NonNull RTSerializationSequenceStep _209 // V00*next-4-steps
									= new RTSerializationSequenceStep(0, 3, 7);
		private final @NonNull RTSerializationSequenceStep _210 // V00*next-4-steps
									= new RTSerializationSequenceStep(0, 4, 8);
		private final @NonNull RTSerializationSequenceStep _211 // V00*next-4-steps
									= new RTSerializationSequenceStep(0, 6, 10);
		private final @NonNull RTSerializationSequenceStep _212 // V00*next-5-steps
									= new RTSerializationSequenceStep(0, 3, 8);
		private final @NonNull RTSerializationSequenceStep _213 // V00*next-5-steps
									= new RTSerializationSequenceStep(0, 4, 9);
		private final @NonNull RTSerializationSequenceStep _214 // V00*next-6-steps
									= new RTSerializationSequenceStep(0, 4, 10);
		private final @NonNull RTSerializationSequenceStep _215 // V00*next-7-steps
									= new RTSerializationSequenceStep(0, 3, 10);
		private final @NonNull RTSerializationLiteralStep _216 // V01*'::*'
									= new RTSerializationLiteralStep(1, "::*");
		private final @NonNull RTSerializationLiteralStep _217 // V01*'|1'
									= new RTSerializationLiteralStep(1, "|1");
		private final @NonNull RTSerializationAssignedRuleCallStep _218 // V01*AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _219 // V01*CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		private final @NonNull RTSerializationAssignedRuleCallStep _220 // V01*RootCS::ownedImports+=ImportCS
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		private final @NonNull RTSerializationAssignedRuleCallsStep _221 // V01*RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS
									= new RTSerializationAssignedRuleCallsStep(1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
				 new @NonNull AbstractRuleValue [3]);
		private final @NonNull RTSerializationAssignedRuleCallStep _222 // V01*TemplateBindingCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationAssignedRuleCallStep _223 // V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		private final @NonNull RTSerializationAssignedRuleCallStep _224 // V01*TypedRefCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationSequenceStep _225 // V01*next-1-steps
									= new RTSerializationSequenceStep(1, 8, 9);
		private final @NonNull RTSerializationSequenceStep _226 // V01*next-2-steps
									= new RTSerializationSequenceStep(1, 4, 6);
		private final @NonNull RTSerializationSequenceStep _227 // V01*next-2-steps
									= new RTSerializationSequenceStep(1, 5, 7);
		private final @NonNull RTSerializationSequenceStep _228 // V01*next-2-steps
									= new RTSerializationSequenceStep(1, 6, 8);
		private final @NonNull RTSerializationSequenceStep _229 // V01*next-2-steps
									= new RTSerializationSequenceStep(1, 7, 9);
		private final @NonNull RTSerializationSequenceStep _230 // V01*next-2-steps
									= new RTSerializationSequenceStep(1, 8, 10);
		private final @NonNull RTSerializationSequenceStep _231 // V01*next-2-steps
									= new RTSerializationSequenceStep(1, 9, 11);
		private final @NonNull RTSerializationSequenceStep _232 // V01*next-3-steps
									= new RTSerializationSequenceStep(1, 5, 8);
		private final @NonNull RTSerializationSequenceStep _233 // V01*next-4-steps
									= new RTSerializationSequenceStep(1, 5, 9);
		private final @NonNull RTSerializationSequenceStep _234 // V01*next-4-steps
									= new RTSerializationSequenceStep(1, 6, 10);
		private final @NonNull RTSerializationSequenceStep _235 // V01*next-5-steps
									= new RTSerializationSequenceStep(1, 4, 9);
		private final @NonNull RTSerializationSequenceStep _236 // V01*next-6-steps
									= new RTSerializationSequenceStep(1, 4, 10);
		private final @NonNull RTSerializationAssignedRuleCallStep _237 // V02*AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(2, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _238 // V02*ConstraintCS::ownedSpecification=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		private final @NonNull RTSerializationAssignedRuleCallStep _239 // V02*ModelElementCS::ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _240 // V02*PackageOwnerCS::ownedPackages+=PackageCS
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		private final @NonNull RTSerializationAssignedRuleCallStep _241 // V02*TypedRefCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationSequenceStep _242 // V02*next-1-steps
									= new RTSerializationSequenceStep(2, 8, 9);
		private final @NonNull RTSerializationSequenceStep _243 // V02*next-2-steps
									= new RTSerializationSequenceStep(2, 6, 8);
		private final @NonNull RTSerializationSequenceStep _244 // V02*next-2-steps
									= new RTSerializationSequenceStep(2, 7, 9);
		private final @NonNull RTSerializationSequenceStep _245 // V02*next-2-steps
									= new RTSerializationSequenceStep(2, 8, 10);
		private final @NonNull RTSerializationSequenceStep _246 // V02*next-2-steps
									= new RTSerializationSequenceStep(2, 9, 11);
		private final @NonNull RTSerializationSequenceStep _247 // V02*next-2-steps
									= new RTSerializationSequenceStep(2, 10, 12);
		private final @NonNull RTSerializationSequenceStep _248 // V02*next-3-steps
									= new RTSerializationSequenceStep(2, 6, 9);
		private final @NonNull RTSerializationSequenceStep _249 // V02*next-3-steps
									= new RTSerializationSequenceStep(2, 8, 11);
		private final @NonNull RTSerializationSequenceStep _250 // V02*next-4-steps
									= new RTSerializationSequenceStep(2, 10, 14);
		private final @NonNull RTSerializationSequenceStep _251 // V02*next-4-steps
									= new RTSerializationSequenceStep(2, 8, 12);
		private final @NonNull RTSerializationSequenceStep _252 // V02*next-5-steps
									= new RTSerializationSequenceStep(2, 6, 11);
		private final @NonNull RTSerializationLiteralStep _253 // V03*'serializable'
									= new RTSerializationLiteralStep(3, "serializable");
		private final @NonNull RTSerializationAssignedRuleCallStep _254 // V03*ConstraintCS::ownedSpecification=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		private final @NonNull RTSerializationAssignedRuleCallStep _255 // V03*ModelElementCS::ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _256 // V03*PackageOwnerCS::ownedPackages+=PackageCS
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		private final @NonNull RTSerializationAssignedRuleCallStep _257 // V03*TypedRefCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationSequenceStep _258 // V03*next-1-steps
									= new RTSerializationSequenceStep(3, 12, 13);
		private final @NonNull RTSerializationSequenceStep _259 // V03*next-2-steps
									= new RTSerializationSequenceStep(3, 10, 12);
		private final @NonNull RTSerializationSequenceStep _260 // V03*next-2-steps
									= new RTSerializationSequenceStep(3, 12, 14);
		private final @NonNull RTSerializationSequenceStep _261 // V03*next-2-steps
									= new RTSerializationSequenceStep(3, 6, 8);
		private final @NonNull RTSerializationSequenceStep _262 // V03*next-2-steps
									= new RTSerializationSequenceStep(3, 9, 11);
		private final @NonNull RTSerializationSequenceStep _263 // V03*next-3-steps
									= new RTSerializationSequenceStep(3, 11, 14);
		private final @NonNull RTSerializationSequenceStep _264 // V03*next-3-steps
									= new RTSerializationSequenceStep(3, 9, 12);
		private final @NonNull RTSerializationSequenceStep _265 // V03*next-4-steps
									= new RTSerializationSequenceStep(3, 12, 16);
		private final @NonNull RTSerializationSequenceStep _266 // V03*next-4-steps
									= new RTSerializationSequenceStep(3, 13, 17);
		private final @NonNull RTSerializationLiteralStep _267 // V04*'serializable'
									= new RTSerializationLiteralStep(4, "serializable");
		private final @NonNull RTSerializationAssignedRuleCallStep _268 // V04*AnnotationCS::ownedContents+=ModelElementCS
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _269 // V04*EnumerationCS::ownedLiterals+=EnumerationLiteralCS
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
		private final @NonNull RTSerializationAssignedRuleCallStep _270 // V04*ModelElementCS::ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _271 // V04*PackageCS::ownedClasses+=ClassCS
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
		private final @NonNull RTSerializationSequenceStep _272 // V04*next-1-steps
									= new RTSerializationSequenceStep(4, 14, 15);
		private final @NonNull RTSerializationSequenceStep _273 // V04*next-1-steps
									= new RTSerializationSequenceStep(4, 15, 16);
		private final @NonNull RTSerializationSequenceStep _274 // V04*next-2-steps
									= new RTSerializationSequenceStep(4, 12, 14);
		private final @NonNull RTSerializationSequenceStep _275 // V04*next-2-steps
									= new RTSerializationSequenceStep(4, 14, 16);
		private final @NonNull RTSerializationSequenceStep _276 // V04*next-4-steps
									= new RTSerializationSequenceStep(4, 15, 19);
		private final @NonNull RTSerializationSequenceStep _277 // V04*next-5-steps
									= new RTSerializationSequenceStep(4, 15, 20);
		private final @NonNull RTSerializationAssignedRuleCallStep _278 // V05*AnnotationCS::ownedReferences+=ModelElementRefCS
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES);
		private final @NonNull RTSerializationAssignedRuleCallStep _279 // V05*ClassCS::ownedConstraints+=InvariantConstraintCS
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _280 // V05*EnumerationCS::ownedLiterals+=EnumerationLiteralCS
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
		private final @NonNull RTSerializationAssignedRuleCallStep _281 // V05*ModelElementCS::ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationSequenceStep _282 // V05*next-1-steps
									= new RTSerializationSequenceStep(5, 17, 18);
		private final @NonNull RTSerializationSequenceStep _283 // V05*next-2-steps
									= new RTSerializationSequenceStep(5, 18, 20);
		private final @NonNull RTSerializationSequenceStep _284 // V05*next-3-steps
									= new RTSerializationSequenceStep(5, 15, 18);
		private final @NonNull RTSerializationSequenceStep _285 // V05*next-4-steps
									= new RTSerializationSequenceStep(5, 17, 21);
		private final @NonNull RTSerializationSequenceStep _286 // V05*next-5-steps
									= new RTSerializationSequenceStep(5, 17, 22);
		private final @NonNull RTSerializationLiteralStep _287 // V06*'interface'
									= new RTSerializationLiteralStep(6, "interface");
		private final @NonNull RTSerializationAssignedRuleCallStep _288 // V06*ClassCS::ownedConstraints+=InvariantConstraintCS
									= new RTSerializationAssignedRuleCallStep(6, BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _289 // V06*ModelElementCS::ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(6, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _290 // V06*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(6, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationSequenceStep _291 // V06*next-2-steps
									= new RTSerializationSequenceStep(6, 20, 22);
		private final @NonNull RTSerializationSequenceStep _292 // V06*next-4-steps
									= new RTSerializationSequenceStep(6, 19, 23);
		private final @NonNull RTSerializationSequenceStep _293 // V06*next-4-steps
									= new RTSerializationSequenceStep(6, 21, 25);
		private final @NonNull RTSerializationSequenceStep _294 // V06*next-6-steps
									= new RTSerializationSequenceStep(6, 20, 26);
		private final @NonNull RTSerializationAssignedRuleCallStep _295 // V07*ModelElementCS::ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(7, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _296 // V07*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(7, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationSequenceStep _297 // V07*next-1-steps
									= new RTSerializationSequenceStep(7, 23, 24);
		private final @NonNull RTSerializationSequenceStep _298 // V07*next-2-steps
									= new RTSerializationSequenceStep(7, 23, 25);
		private final @NonNull RTSerializationSequenceStep _299 // V07*next-4-steps
									= new RTSerializationSequenceStep(7, 22, 26);
		private final @NonNull RTSerializationSequenceStep _300 // V07*next-4-steps
									= new RTSerializationSequenceStep(7, 23, 27);
		private final @NonNull RTSerializationSequenceStep _301 // V07*next-6-steps
									= new RTSerializationSequenceStep(7, 22, 28);
		private final @NonNull RTSerializationAssignedRuleCallStep _302 // V08*ModelElementCS::ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(8, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _303 // V08*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(8, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _304 // V08*StructuredClassCS::ownedOperations+=OperationCS
									= new RTSerializationAssignedRuleCallStep(8, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
		private final @NonNull RTSerializationSequenceStep _305 // V08*next-1-steps
									= new RTSerializationSequenceStep(8, 25, 26);
		private final @NonNull RTSerializationSequenceStep _306 // V08*next-2-steps
									= new RTSerializationSequenceStep(8, 25, 27);
		private final @NonNull RTSerializationSequenceStep _307 // V08*next-4-steps
									= new RTSerializationSequenceStep(8, 24, 28);
		private final @NonNull RTSerializationSequenceStep _308 // V08*next-4-steps
									= new RTSerializationSequenceStep(8, 27, 31);
		private final @NonNull RTSerializationAssignedRuleCallStep _309 // V09*ModelElementCS::ownedAnnotations+=AnnotationElementCS
									= new RTSerializationAssignedRuleCallStep(9, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _310 // V09*OperationCS::ownedPreconditions+=PreconditionConstraintCS
									= new RTSerializationAssignedRuleCallStep(9, BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _311 // V09*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(9, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _312 // V09*StructuredClassCS::ownedProperties+=StructuralFeatureCS
									= new RTSerializationAssignedRuleCallStep(9, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
		private final @NonNull RTSerializationSequenceStep _313 // V09*next-4-steps
									= new RTSerializationSequenceStep(9, 29, 33);
		private final @NonNull RTSerializationAssignedRuleCallStep _314 // V10*ClassCS::ownedConstraints+=InvariantConstraintCS
									= new RTSerializationAssignedRuleCallStep(10, BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _315 // V10*OperationCS::ownedPreconditions+=PreconditionConstraintCS
									= new RTSerializationAssignedRuleCallStep(10, BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _316 // V10*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(10, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationSequenceStep _317 // V10*next-4-steps
									= new RTSerializationSequenceStep(10, 32, 36);
		private final @NonNull RTSerializationSequenceStep _318 // V10*next-4-steps
									= new RTSerializationSequenceStep(10, 29, 33);
		private final @NonNull RTSerializationAssignedRuleCallStep _319 // V11*OperationCS::ownedBodyExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(11, BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _320 // V11*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(11, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationSequenceStep _321 // V11*next-4-steps
									= new RTSerializationSequenceStep(11, 31, 35);
		private final @NonNull RTSerializationSequenceStep _322 // V11*next-4-steps
									= new RTSerializationSequenceStep(11, 34, 38);
		private final @NonNull RTSerializationAssignedRuleCallStep _323 // V12*OperationCS::ownedBodyExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(12, BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _324 // V12*OperationCS::ownedPostconditions+=PostconditionConstraintCS
									= new RTSerializationAssignedRuleCallStep(12, BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _325 // V12*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS
									= new RTSerializationAssignedRuleCallStep(12, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull RTSerializationSequenceStep _326 // V12*next-2-steps
									= new RTSerializationSequenceStep(12, 37, 39);
		private final @NonNull RTSerializationAssignedRuleCallStep _327 // V13*OperationCS::ownedPostconditions+=PostconditionConstraintCS
									= new RTSerializationAssignedRuleCallStep(13, BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
		private final @NonNull RTSerializationSequenceStep _328 // V13*next-2-steps
									= new RTSerializationSequenceStep(13, 39, 41);

		/**
		 * Post constructor initialization that avoids recursions.
		 */
		private final void init() {
			_064.init(rv._073);
			_065.init(rv._016);
			_067.init(rv._087);
			_068.init(rv._009);
			_069.init(rv._011);
			_070.init(rv._030);
			_071.init(rv._074);
			_072.init(rv._030);
			_073.init(rv._074);
			_074.init(rv._011);
			_075.init(rv._035);
			_076.init(rv._012);
			_077.init(rv._108);
			_078.init(rv._092);
			_079.init(rv._030);
			_080.init(rv._090);
			_081.init(rv._087);
			_082.init(rv._085);
			_083.init(rv._030);
			_084.init(new @NonNull AbstractRuleValue [] {rv._030/*ExpCS*/, rv._074/*PatternExpCS*/});
			_085.init(rv._030);
			_086.init(rv._030);
			_087.init(rv._030);
			_088.init(rv._030);
			_089.init(rv._123);
			_090.init(rv._078);
			_091.init(rv._030);
			_092.init(rv._030);
			_093.init(rv._046);
			_094.init(rv._050);
			_095.init(rv._051);
			_096.init(rv._030);
			_097.init(rv._030);
			_098.init(rv._107);
			_099.init(rv._107);
			_100.init(rv._073);
			_101.init(rv._043);
			_102.init(rv._120);
			_104.init(rv._004);
			_105.init(rv._023);
			_106.init(rv._124);
			_107.init(rv._127);
			_108.init(new @NonNull AbstractRuleValue [] {rv._127/*UnrestrictedName*/, rv._087/*SINGLE_QUOTED_STRING*/});
			_109.init(rv._007);
			_110.init(rv._030);
			_111.init(rv._061);
			_112.init(rv._107);
			_113.init(rv._030);
			_114.init(rv._058);
			_115.init(rv._117);
			_116.init(rv._072);
			_117.init(rv._030);
			_118.init(rv._077);
			_119.init(rv._078);
			_120.init(rv._127);
			_121.init(rv._121);
			_126.init(rv._031);
			_127.init(rv._067);
			_128.init(rv._122);
			_129.init(rv._107);
			_130.init(rv._082);
			_131.init(rv._037);
			_135.init(rv._060);
			_136.init(new @NonNull AbstractRuleValue [] {rv._030/*ExpCS*/, rv._074/*PatternExpCS*/});
			_137.init(rv._095);
			_139.init(rv._119);
			_140.init(rv._030);
			_141.init(rv._087);
			_142.init(rv._117);
			_143.init(rv._100);
			_144.init(rv._115);
			_145.init(rv._114);
			_146.init(rv._104);
			_147.init(rv._105);
			_148.init(rv._112);
			_149.init(rv._013);
			_150.init(rv._073);
			_151.init(rv._030);
			_152.init(rv._117);
			_153.init(rv._107);
			_154.init(rv._116);
			_156.init(rv._099);
			_157.init(rv._073);
			_158.init(rv._030);
			_159.init(rv._107);
			_160.init(rv._117);
			_194.init(rv._093);
			_195.init(new @NonNull AbstractRuleValue [] {rv._087/*SINGLE_QUOTED_STRING*/, rv._048/*ML_SINGLE_QUOTED_STRING*/});
			_196.init(rv._087);
			_197.init(rv._020);
			_198.init(rv._084);
			_199.init(new @NonNull AbstractRuleValue [] {rv._127/*UnrestrictedName*/, rv._087/*SINGLE_QUOTED_STRING*/});
			_200.init(rv._127);
			_201.init(rv._094);
			_202.init(rv._101);
			_203.init(rv._056);
			_218.init(rv._084);
			_219.init(rv._056);
			_220.init(rv._038);
			_221.init(new @NonNull AbstractRuleValue [] {rv._063/*NavigatingCommaArgCS*/, rv._064/*NavigatingSemiArgCS*/, rv._062/*NavigatingBarArgCS*/});
			_222.init(rv._056);
			_223.init(rv._101);
			_224.init(rv._056);
			_237.init(rv._013);
			_238.init(rv._092);
			_239.init(rv._002);
			_240.init(rv._071);
			_241.init(rv._056);
			_254.init(rv._092);
			_255.init(rv._002);
			_256.init(rv._071);
			_257.init(rv._056);
			_268.init(rv._053);
			_269.init(rv._022);
			_270.init(rv._002);
			_271.init(rv._006);
			_278.init(rv._054);
			_279.init(rv._041);
			_280.init(rv._022);
			_281.init(rv._002);
			_288.init(rv._041);
			_289.init(rv._002);
			_290.init(rv._092);
			_295.init(rv._002);
			_296.init(rv._092);
			_302.init(rv._002);
			_303.init(rv._092);
			_304.init(rv._070);
			_309.init(rv._002);
			_310.init(rv._076);
			_311.init(rv._092);
			_312.init(rv._096);
			_314.init(rv._041);
			_315.init(rv._076);
			_316.init(rv._092);
			_319.init(rv._092);
			_320.init(rv._092);
			_323.init(rv._092);
			_324.init(rv._075);
			_325.init(rv._092);
			_327.init(rv._075);
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
		private final @NonNull Segment [] _9 = new @NonNull Segment @NonNull [] {
			IdiomsUtils.HALF_NEW_LINE /* «½\n» */,
			IdiomsUtils.VALUE /* «value» */,
			IdiomsUtils.HALF_NEW_LINE /* «½\n» */
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
					sr1._096 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._098 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
					sr1._099 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._097 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _002 // AnnotationElementCS
			= new ParserRuleValue(2, "AnnotationElementCS",
				new @NonNull SerializationRule [] {
					sr2._150 /* { 'sysml' ownedDetails+=DetailCS ';' } */,
					sr2._151 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */,
					sr1._096 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._113 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._098 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
					sr1._099 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._097 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				},
				iv._43); /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
		private final @NonNull ParserRuleValue _003 // AttributeCS
			= new ParserRuleValue(3, "AttributeCS",
				new @NonNull SerializationRule [] {
					sr1._101 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._100 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._104 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._102 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._103 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._105 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _004 // BinaryOperatorName
			= new DataTypeRuleValue(4, "BinaryOperatorName");
		private final @NonNull ParserRuleValue _005 // BooleanLiteralExpCS
			= new ParserRuleValue(5, "BooleanLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._015 /* symbol={'false|true'} */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _006 // ClassCS
			= new ParserRuleValue(6, "ClassCS",
				new @NonNull SerializationRule [] {
					sr1._116 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._111 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._117 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._119 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._107 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._108 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._110 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._118 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._106 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._109 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._114 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._115 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._149 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr2._148 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				iv._41); /* ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS */
		private final @NonNull ParserRuleValue _007 // CoIteratorVariableCS
			= new ParserRuleValue(7, "CoIteratorVariableCS",
				new @NonNull SerializationRule [] {
					sr0._016 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _008 // CollectionLiteralExpCS
			= new ParserRuleValue(8, "CollectionLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._017 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _009 // CollectionLiteralPartCS
			= new ParserRuleValue(9, "CollectionLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._018 /* ownedExpression=PatternExpCS */,
					sr0._019 /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _010 // CollectionPatternCS
			= new ParserRuleValue(10, "CollectionPatternCS",
				new @NonNull SerializationRule [] {
					sr0._020 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _011 // CollectionTypeCS
			= new ParserRuleValue(11, "CollectionTypeCS",
				new @NonNull SerializationRule [] {
					sr0._021 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _012 // CollectionTypeIdentifier
			= new DataTypeRuleValue(12, "CollectionTypeIdentifier");
		private final @NonNull ParserRuleValue _013 // CurlyBracketedClauseCS
			= new ParserRuleValue(13, "CurlyBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr0._022 /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _014 // DOUBLE_QUOTED_STRING
			= new TerminalRuleValue(14, "DOUBLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _015 // DataTypeCS
			= new ParserRuleValue(15, "DataTypeCS",
				new @NonNull SerializationRule [] {
					sr1._111 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._107 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._108 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._110 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._106 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._109 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _016 // DetailCS
			= new ParserRuleValue(16, "DetailCS",
				new @NonNull SerializationRule [] {
					sr1._112 /* { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _017 // DocumentationCS
			= new ParserRuleValue(17, "DocumentationCS",
				new @NonNull SerializationRule [] {
					sr1._113 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _018 // ESCAPED_CHARACTER
			= new TerminalRuleValue(18, "ESCAPED_CHARACTER");
		private final @NonNull TerminalRuleValue _019 // ESCAPED_ID
			= new TerminalRuleValue(19, "ESCAPED_ID");
		private final @NonNull ParserRuleValue _020 // ElseIfThenExpCS
			= new ParserRuleValue(20, "ElseIfThenExpCS",
				new @NonNull SerializationRule [] {
					sr0._023 /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _021 // EnumerationCS
			= new ParserRuleValue(21, "EnumerationCS",
				new @NonNull SerializationRule [] {
					sr1._116 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._117 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._119 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._118 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._114 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._115 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _022 // EnumerationLiteralCS
			= new ParserRuleValue(22, "EnumerationLiteralCS",
				new @NonNull SerializationRule [] {
					sr1._121 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._123 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._120 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr1._122 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
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
					sr0._039 /* symbol={'false|true'} */,
					sr0._037 /* '*' */,
					sr0._035 /* 'invalid' */,
					sr0._034 /* 'null' */,
					sr0._040 /* 'self' */,
					sr0._032 /* symbol=NUMBER_LITERAL */,
					sr0._026 /* segments+=StringLiteral[+] */,
					sr0._029 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._038 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr1._066 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					sr0._027 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._036 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._033 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._031 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._024 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._025 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._044 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr0._028 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._030 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
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
					sr0._041 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _037 // ImplicitOppositeCS
			= new ParserRuleValue(37, "ImplicitOppositeCS",
				new @NonNull SerializationRule [] {
					sr1._124 /* { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _038 // ImportCS
			= new ParserRuleValue(38, "ImportCS",
				new @NonNull SerializationRule [] {
					sr1._125 /* { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _039 // InfixOperatorName
			= new DataTypeRuleValue(39, "InfixOperatorName");
		private final @NonNull ParserRuleValue _040 // InvalidLiteralExpCS
			= new ParserRuleValue(40, "InvalidLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._042 /* 'invalid' */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _041 // InvariantConstraintCS
			= new ParserRuleValue(41, "InvariantConstraintCS",
				new @NonNull SerializationRule [] {
					sr1._126 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' } */,
					sr1._127 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _042 // LETTER_CHARACTER
			= new TerminalRuleValue(42, "LETTER_CHARACTER");
		private final @NonNull DataTypeRuleValue _043 // LOWER
			= new DataTypeRuleValue(43, "LOWER");
		private final @NonNull ParserRuleValue _044 // LambdaLiteralExpCS
			= new ParserRuleValue(44, "LambdaLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._043 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _045 // LetExpCS
			= new ParserRuleValue(45, "LetExpCS",
				new @NonNull SerializationRule [] {
					sr0._044 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _046 // LetVariableCS
			= new ParserRuleValue(46, "LetVariableCS",
				new @NonNull SerializationRule [] {
					sr0._045 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _047 // ML_COMMENT
			= new TerminalRuleValue(47, "ML_COMMENT");
		private final @NonNull TerminalRuleValue _048 // ML_SINGLE_QUOTED_STRING
			= new TerminalRuleValue(48, "ML_SINGLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _049 // MapLiteralExpCS
			= new ParserRuleValue(49, "MapLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._046 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _050 // MapLiteralPartCS
			= new ParserRuleValue(50, "MapLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._047 /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _051 // MapTypeCS
			= new ParserRuleValue(51, "MapTypeCS",
				new @NonNull SerializationRule [] {
					sr0._048 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _052 // Model
			= new ParserRuleValue(52, "Model",
				new @NonNull SerializationRule [] {
					sr0._049 /* ownedExpression=ExpCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _053 // ModelElementCS
			= new ParserRuleValue(53, "ModelElementCS",
				new @NonNull SerializationRule [] {
					sr1._121 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._123 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr2._135 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
					sr1._116 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._120 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr1._111 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._122 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr1._117 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._119 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._107 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._108 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr2._136 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */,
					sr1._110 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._118 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._106 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._109 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._114 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._115 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._101 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._100 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._104 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._144 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._142 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._145 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._149 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr2._148 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._102 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._103 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._105 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr2._130 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._133 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._129 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._131 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._132 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._134 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._143 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._140 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._141 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
				iv._42); /* AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */
		private final @NonNull ParserRuleValue _054 // ModelElementRefCS
			= new ParserRuleValue(54, "ModelElementRefCS",
				new @NonNull SerializationRule [] {
					sr2._128 /* { 'reference' ownedPathName=PathNameCS ';' } */
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
					sr0._005 /* { '[' stringBounds={'*|+|?'} ']' } */,
					sr0._007 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					sr0._003 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
					sr0._004 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					sr0._006 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					sr0._002 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
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
					sr0._050 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _060 // NavigatingArgCS
			= new ParserRuleValue(60, "NavigatingArgCS",
				new @NonNull SerializationRule [] {
					sr0._052 /* ownedNameExpression=NavigatingArgExpCS */,
					sr0._051 /* { ':' ownedType=TypeExpCS } */,
					sr0._053 /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._055 /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._054 /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _061 // NavigatingArgExpCS
			= new ParserRuleValue(61, "NavigatingArgExpCS",
				new @NonNull SerializationRule [] {
					sr0._039 /* symbol={'false|true'} */,
					sr0._037 /* '*' */,
					sr0._035 /* 'invalid' */,
					sr0._034 /* 'null' */,
					sr0._040 /* 'self' */,
					sr0._032 /* symbol=NUMBER_LITERAL */,
					sr0._026 /* segments+=StringLiteral[+] */,
					sr0._029 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._038 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr1._066 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					sr0._027 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._036 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._033 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._031 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._024 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._025 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._044 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr0._028 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._030 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				iv._70); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _062 // NavigatingBarArgCS
			= new ParserRuleValue(62, "NavigatingBarArgCS",
				new @NonNull SerializationRule [] {
					sr0._056 /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _063 // NavigatingCommaArgCS
			= new ParserRuleValue(63, "NavigatingCommaArgCS",
				new @NonNull SerializationRule [] {
					sr0._058 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					sr0._057 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._060 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._059 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _064 // NavigatingSemiArgCS
			= new ParserRuleValue(64, "NavigatingSemiArgCS",
				new @NonNull SerializationRule [] {
					sr0._061 /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _065 // NavigationOperatorName
			= new DataTypeRuleValue(65, "NavigationOperatorName");
		private final @NonNull ParserRuleValue _066 // NestedExpCS
			= new ParserRuleValue(66, "NestedExpCS",
				new @NonNull SerializationRule [] {
					sr0._062 /* { '(' ownedExpression=ExpCS ')' } */
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
					sr0._063 /* 'null' */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _069 // NumberLiteralExpCS
			= new ParserRuleValue(69, "NumberLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._064 /* symbol=NUMBER_LITERAL */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _070 // OperationCS
			= new ParserRuleValue(70, "OperationCS",
				new @NonNull SerializationRule [] {
					sr2._130 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._133 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._129 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._131 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._132 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._134 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _071 // PackageCS
			= new ParserRuleValue(71, "PackageCS",
				new @NonNull SerializationRule [] {
					sr2._135 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
					sr2._136 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _072 // ParameterCS
			= new ParserRuleValue(72, "ParameterCS",
				new @NonNull SerializationRule [] {
					sr2._137 /* { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] } */
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
					sr1._065 /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _075 // PostconditionConstraintCS
			= new ParserRuleValue(75, "PostconditionConstraintCS",
				new @NonNull SerializationRule [] {
					sr2._138 /* { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _076 // PreconditionConstraintCS
			= new ParserRuleValue(76, "PreconditionConstraintCS",
				new @NonNull SerializationRule [] {
					sr2._139 /* { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _077 // PrefixedLetExpCS
			= new ParserRuleValue(77, "PrefixedLetExpCS",
				new @NonNull SerializationRule [] {
					sr1._066 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					sr0._044 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				iv._32); /* LetExpCS|PrefixedLetExpCS */
		private final @NonNull ParserRuleValue _078 // PrefixedPrimaryExpCS
			= new ParserRuleValue(78, "PrefixedPrimaryExpCS",
				new @NonNull SerializationRule [] {
					sr0._015 /* symbol={'false|true'} */,
					sr0._042 /* 'invalid' */,
					sr0._063 /* 'null' */,
					sr1._064 /* symbol=NUMBER_LITERAL */,
					sr1._070 /* 'self' */,
					sr1._075 /* segments+=StringLiteral[+] */,
					sr1._086 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._095 /* '*' */,
					sr1._067 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._062 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._043 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._041 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._050 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr1._076 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._017 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._046 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				iv._67); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _079 // PrimaryExpCS
			= new ParserRuleValue(79, "PrimaryExpCS",
				new @NonNull SerializationRule [] {
					sr0._015 /* symbol={'false|true'} */,
					sr0._042 /* 'invalid' */,
					sr0._063 /* 'null' */,
					sr1._064 /* symbol=NUMBER_LITERAL */,
					sr1._070 /* 'self' */,
					sr1._075 /* segments+=StringLiteral[+] */,
					sr1._086 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._095 /* '*' */,
					sr0._062 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._043 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._041 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._050 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr1._076 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._017 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._046 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				iv._66); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _080 // PrimitiveLiteralExpCS
			= new ParserRuleValue(80, "PrimitiveLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._015 /* symbol={'false|true'} */,
					sr0._042 /* 'invalid' */,
					sr0._063 /* 'null' */,
					sr1._064 /* symbol=NUMBER_LITERAL */,
					sr1._075 /* segments+=StringLiteral[+] */,
					sr1._095 /* '*' */
				},
				iv._65); /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _081 // PrimitiveTypeCS
			= new ParserRuleValue(81, "PrimitiveTypeCS",
				new @NonNull SerializationRule [] {
					sr1._068 /* name=PrimitiveTypeIdentifier */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _082 // PrimitiveTypeIdentifier
			= new DataTypeRuleValue(82, "PrimitiveTypeIdentifier");
		private final @NonNull ParserRuleValue _083 // ReferenceCS
			= new ParserRuleValue(83, "ReferenceCS",
				new @NonNull SerializationRule [] {
					sr2._144 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._142 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._145 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._143 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._140 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._141 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _084 // RoundBracketedClauseCS
			= new ParserRuleValue(84, "RoundBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._069 /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
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
					sr1._070 /* 'self' */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _090 // ShadowPartCS
			= new ParserRuleValue(90, "ShadowPartCS",
				new @NonNull SerializationRule [] {
					sr1._072 /* ownedInitExpression=StringLiteralExpCS */,
					sr1._071 /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _091 // SimplePathNameCS
			= new ParserRuleValue(91, "SimplePathNameCS",
				new @NonNull SerializationRule [] {
					sr1._073 /* ownedPathElements+=FirstPathElementCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _092 // SpecificationCS
			= new ParserRuleValue(92, "SpecificationCS",
				new @NonNull SerializationRule [] {
					sr2._146 /* exprString=UNQUOTED_STRING */,
					sr2._147 /* ownedExpression=ExpCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _093 // SquareBracketedClauseCS
			= new ParserRuleValue(93, "SquareBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._074 /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _094 // StringLiteral
			= new DataTypeRuleValue(94, "StringLiteral");
		private final @NonNull ParserRuleValue _095 // StringLiteralExpCS
			= new ParserRuleValue(95, "StringLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._075 /* segments+=StringLiteral[+] */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _096 // StructuralFeatureCS
			= new ParserRuleValue(96, "StructuralFeatureCS",
				new @NonNull SerializationRule [] {
					sr1._101 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._100 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._104 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._144 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._142 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._145 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._102 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._103 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._105 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr2._143 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._140 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._141 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
				iv._40); /* AttributeCS|ReferenceCS|StructuralFeatureCS */
		private final @NonNull ParserRuleValue _097 // StructuredClassCS
			= new ParserRuleValue(97, "StructuredClassCS",
				new @NonNull SerializationRule [] {
					sr2._149 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr2._148 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _098 // SysMLCS
			= new ParserRuleValue(98, "SysMLCS",
				new @NonNull SerializationRule [] {
					sr2._150 /* { 'sysml' ownedDetails+=DetailCS ';' } */,
					sr2._151 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */
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
					sr2._152 /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */,
					sr2._153 /* { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _102 // TopLevelCS
			= new ParserRuleValue(102, "TopLevelCS",
				new @NonNull SerializationRule [] {
					sr2._154 /* { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _103 // TupleLiteralExpCS
			= new ParserRuleValue(103, "TupleLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._076 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _104 // TupleLiteralPartCS
			= new ParserRuleValue(104, "TupleLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr1._077 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _105 // TuplePartCS
			= new ParserRuleValue(105, "TuplePartCS",
				new @NonNull SerializationRule [] {
					sr1._078 /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _106 // TupleTypeCS
			= new ParserRuleValue(106, "TupleTypeCS",
				new @NonNull SerializationRule [] {
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _107 // TypeExpCS
			= new ParserRuleValue(107, "TypeExpCS",
				new @NonNull SerializationRule [] {
					sr1._082 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._083 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._081 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._080 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._084 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._085 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._55); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull ParserRuleValue _108 // TypeExpWithoutMultiplicityCS
			= new ParserRuleValue(108, "TypeExpWithoutMultiplicityCS",
				new @NonNull SerializationRule [] {
					sr1._068 /* name=PrimitiveTypeIdentifier */,
					sr0._021 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._048 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._091 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */,
					sr0._020 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._54); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull DataTypeRuleValue _109 // TypeIdentifier
			= new DataTypeRuleValue(109, "TypeIdentifier");
		private final @NonNull ParserRuleValue _110 // TypeLiteralCS
			= new ParserRuleValue(110, "TypeLiteralCS",
				new @NonNull SerializationRule [] {
					sr1._068 /* name=PrimitiveTypeIdentifier */,
					sr0._021 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._048 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._51); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */
		private final @NonNull ParserRuleValue _111 // TypeLiteralExpCS
			= new ParserRuleValue(111, "TypeLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._086 /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _112 // TypeLiteralWithMultiplicityCS
			= new ParserRuleValue(112, "TypeLiteralWithMultiplicityCS",
				new @NonNull SerializationRule [] {
					sr1._087 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._089 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._090 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._088 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._53); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
		private final @NonNull ParserRuleValue _113 // TypeNameExpCS
			= new ParserRuleValue(113, "TypeNameExpCS",
				new @NonNull SerializationRule [] {
					sr1._091 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
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
					sr1._068 /* name=PrimitiveTypeIdentifier */,
					sr2._163 /* ownedPathName=PathNameCS */,
					sr2._164 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr2._162 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
					sr0._014 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */,
					sr0._021 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._048 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._72); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
		private final @NonNull ParserRuleValue _116 // TypedMultiplicityRefCS
			= new ParserRuleValue(116, "TypedMultiplicityRefCS",
				new @NonNull SerializationRule [] {
					sr2._156 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._159 /* { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._155 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._160 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._158 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._161 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._157 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._61); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
		private final @NonNull ParserRuleValue _117 // TypedRefCS
			= new ParserRuleValue(117, "TypedRefCS",
				new @NonNull SerializationRule [] {
					sr1._068 /* name=PrimitiveTypeIdentifier */,
					sr2._163 /* ownedPathName=PathNameCS */,
					sr2._164 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr2._162 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
					sr0._021 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._048 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._60); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
		private final @NonNull ParserRuleValue _118 // TypedTypeRefCS
			= new ParserRuleValue(118, "TypedTypeRefCS",
				new @NonNull SerializationRule [] {
					sr2._163 /* ownedPathName=PathNameCS */,
					sr2._164 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr2._162 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
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
					sr1._093 /* referredElement=UnrestrictedName */,
					sr1._092 /* referredElement=URI */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _123 // URIPathNameCS
			= new ParserRuleValue(123, "URIPathNameCS",
				new @NonNull SerializationRule [] {
					sr1._094 /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _124 // UnaryOperatorName
			= new DataTypeRuleValue(124, "UnaryOperatorName");
		private final @NonNull ParserRuleValue _125 // UnlimitedNaturalLiteralExpCS
			= new ParserRuleValue(125, "UnlimitedNaturalLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._095 /* '*' */
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
					sr0._014 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
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
					sr1._096 /* 1875959310 { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._098 /* -1547419260 { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
					sr1._099 /* 665619455 { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._097 /* -1355059670 { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._096 /* 1875959310 { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._098 /* -1547419260 { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
					sr1._099 /* 665619455 { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._097 /* -1355059670 { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
						iv._42) /* AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
						iv._18) /* ModelElementRefCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						iv._6) /* DetailCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
				}
			);
		private final @NonNull EClassData _01 // AttributeCS
			= new EClassData(BaseCSPackage.Literals.ATTRIBUTE_CS,
				new @NonNull SerializationRule [] {
					sr1._101 /* 1476734384 { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._100 /* -1782941058 { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._104 /* -1288428798 { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._102 /* -1243372148 { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._103 /* 634990094 { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._105 /* 1850901535 { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._101 /* 1476734384 { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._100 /* -1782941058 { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._104 /* -1288428798 { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._102 /* -1243372148 { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._103 /* 634990094 { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._105 /* 1850901535 { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._101 /* 1476734384 { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._100 /* -1782941058 { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._104 /* -1288428798 { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._102 /* -1243372148 { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._103 /* 634990094 { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._105 /* 1850901535 { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						iv._36) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassData _02 // BooleanLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._015 /* 126418804 symbol={'false|true'} */,
					sr0._039 /* -82999717 symbol={'false|true'} */,
					sr0._039 /* -82999717 symbol={'false|true'} */,
					sr0._015 /* 126418804 symbol={'false|true'} */,
					sr0._015 /* 126418804 symbol={'false|true'} */,
					sr0._015 /* 126418804 symbol={'false|true'} */
				}, null
			);
		private final @NonNull EClassData _03 // CollectionLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._017 /* -63714815 { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._028 /* 836380199 { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._028 /* 836380199 { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._017 /* -63714815 { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._017 /* -63714815 { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						iv._3) /* CollectionLiteralPartCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						iv._4) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassData _04 // CollectionLiteralPartCS
			= new EClassData(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
				new @NonNull SerializationRule [] {
					sr0._018 /* 663395878 ownedExpression=PatternExpCS */,
					sr0._019 /* -912575271 { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _05 // CollectionPatternCS
			= new EClassData(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
				new @NonNull SerializationRule [] {
					sr0._020 /* 539246515 { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
					sr1._084 /* 1166766251 { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
					sr0._020 /* 539246515 { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						iv._27) /* PatternExpCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						iv._4) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassData _06 // CollectionTypeCS
			= new EClassData(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
				new @NonNull SerializationRule [] {
					sr0._021 /* 1673256475 { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._021 /* 1673256475 { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr1._083 /* -1417159480 { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr0._021 /* 1673256475 { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._021 /* 1673256475 { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr1._089 /* -744094698 { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._158 /* 659238273 { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr0._021 /* 1673256475 { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						iv._54) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassData _07 // ContextCS
			= new EClassData(EssentialOCLCSPackage.Literals.CONTEXT_CS,
				new @NonNull SerializationRule [] {
					sr0._049 /* 731381858 ownedExpression=ExpCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _08 // CurlyBracketedClauseCS
			= new EClassData(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule [] {
					sr0._022 /* -977317712 { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						iv._35) /* ShadowPartCS */
				}
			);
		private final @NonNull EClassData _09 // DataTypeCS
			= new EClassData(BaseCSPackage.Literals.DATA_TYPE_CS,
				new @NonNull SerializationRule [] {
					sr1._111 /* 863787158 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._107 /* 145715890 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._108 /* -1535327225 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._110 /* -1422062067 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._106 /* 1927607441 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._109 /* -1803879056 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._111 /* 863787158 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._107 /* 145715890 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._108 /* -1535327225 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._110 /* -1422062067 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._106 /* 1927607441 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._109 /* -1803879056 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._111 /* 863787158 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._107 /* 145715890 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._108 /* -1535327225 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._110 /* -1422062067 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._106 /* 1927607441 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._109 /* -1803879056 { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						iv._13) /* InvariantConstraintCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._46) /* TemplateSignatureCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
				}
			);
		private final @NonNull EClassData _10 // DetailCS
			= new EClassData(BaseCSPackage.Literals.DETAIL_CS,
				new @NonNull SerializationRule [] {
					sr1._112 /* 63768522 { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
				}, null
			);
		private final @NonNull EClassData _11 // DocumentationCS
			= new EClassData(BaseCSPackage.Literals.DOCUMENTATION_CS,
				new @NonNull SerializationRule [] {
					sr1._113 /* -817664638 { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._113 /* -817664638 { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						iv._6) /* DetailCS */
				}
			);
		private final @NonNull EClassData _12 // EnumerationCS
			= new EClassData(BaseCSPackage.Literals.ENUMERATION_CS,
				new @NonNull SerializationRule [] {
					sr1._116 /* -1463482196 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._117 /* 130048487 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._119 /* 1099626160 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._118 /* 77229055 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._114 /* -1893344634 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._115 /* 1098220175 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._116 /* -1463482196 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._117 /* 130048487 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._119 /* 1099626160 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._118 /* 77229055 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._114 /* -1893344634 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._115 /* 1098220175 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._116 /* -1463482196 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._117 /* 130048487 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._119 /* 1099626160 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._118 /* 77229055 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._114 /* -1893344634 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._115 /* 1098220175 { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
						iv._8) /* EnumerationLiteralCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						iv._13) /* InvariantConstraintCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._46) /* TemplateSignatureCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
				}
			);
		private final @NonNull EClassData _13 // EnumerationLiteralCS
			= new EClassData(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS,
				new @NonNull SerializationRule [] {
					sr1._121 /* 1659333344 { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._123 /* -289232312 { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._120 /* 228595735 { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr1._122 /* 1104004116 { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr1._121 /* 1659333344 { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._123 /* -289232312 { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._120 /* 228595735 { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr1._122 /* 1104004116 { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
				}
			);
		private final @NonNull EClassData _14 // ExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._037 /* 1312779817 '*' */,
					sr0._035 /* -284224349 'invalid' */,
					sr0._034 /* -2038027518 'null' */,
					sr0._040 /* -261481978 'self' */,
					sr0._037 /* 1312779817 '*' */,
					sr0._035 /* -284224349 'invalid' */,
					sr0._034 /* -2038027518 'null' */,
					sr0._040 /* -261481978 'self' */
				}, null
			);
		private final @NonNull EClassData _15 // ExpSpecificationCS
			= new EClassData(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
				new @NonNull SerializationRule [] {
					sr2._146 /* 1348521422 exprString=UNQUOTED_STRING */,
					sr2._147 /* -624628449 ownedExpression=ExpCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _16 // IfExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.IF_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._031 /* 817701228 { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._041 /* 1561349670 { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._031 /* 817701228 { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._041 /* 1561349670 { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._041 /* 1561349670 { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						iv._7) /* ElseIfThenExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _17 // IfThenExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._023 /* 647588337 { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _18 // ImplicitOppositeCS
			= new EClassData(BaseCSPackage.Literals.IMPLICIT_OPPOSITE_CS,
				new @NonNull SerializationRule [] {
					sr1._124 /* -1777295690 { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassData _19 // ImportCS
			= new EClassData(BaseCSPackage.Literals.IMPORT_CS,
				new @NonNull SerializationRule [] {
					sr1._125 /* 1212262205 { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
						iv._64) /* URIPathNameCS */
				}
			);
		private final @NonNull EClassData _20 // InfixExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._027 /* 2038848049 { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._027 /* 2038848049 { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
						iv._67) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _21 // InvalidLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._042 /* -46020175 'invalid' */,
					sr0._042 /* -46020175 'invalid' */,
					sr0._042 /* -46020175 'invalid' */,
					sr0._042 /* -46020175 'invalid' */
				}, null
			);
		private final @NonNull EClassData _22 // LambdaLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._033 /* -44663401 { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._043 /* -1737594575 { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._033 /* -44663401 { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._043 /* -1737594575 { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._043 /* -1737594575 { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _23 // LetExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.LET_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._044 /* -277783037 { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr0._044 /* -277783037 { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr0._044 /* -277783037 { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr0._044 /* -277783037 { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						iv._14) /* LetVariableCS */
				}
			);
		private final @NonNull EClassData _24 // LetVariableCS
			= new EClassData(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
				new @NonNull SerializationRule [] {
					sr0._045 /* 745491382 { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._34) /* RoundBracketedClauseCS */
				}
			);
		private final @NonNull EClassData _25 // MapLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._030 /* 2126702591 { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._046 /* 42961757 { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._030 /* 2126702591 { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._046 /* 42961757 { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._046 /* 42961757 { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						iv._16) /* MapTypeCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						iv._15) /* MapLiteralPartCS */
				}
			);
		private final @NonNull EClassData _26 // MapLiteralPartCS
			= new EClassData(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
				new @NonNull SerializationRule [] {
					sr0._047 /* -885823298 { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _27 // MapTypeCS
			= new EClassData(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
				new @NonNull SerializationRule [] {
					sr0._048 /* 403079830 { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr0._048 /* 403079830 { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._081 /* -1479525220 { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr0._048 /* 403079830 { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr0._048 /* 403079830 { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._090 /* 858194047 { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._161 /* 1435137216 { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr0._048 /* 403079830 { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassData _28 // ModelElementRefCS
			= new EClassData(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS,
				new @NonNull SerializationRule [] {
					sr2._128 /* -201560844 { 'reference' ownedPathName=PathNameCS ';' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
						iv._26) /* PathNameCS */
				}
			);
		private final @NonNull EClassData _29 // MultiplicityBoundsCS
			= new EClassData(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
				new @NonNull SerializationRule [] {
					sr0._001 /* -1963260355 { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */,
					sr0._004 /* -1468957458 { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					sr0._006 /* -468148177 { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					sr0._002 /* 1290067122 { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, null
			);
		private final @NonNull EClassData _30 // MultiplicityStringCS
			= new EClassData(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
				new @NonNull SerializationRule [] {
					sr0._005 /* -2095100977 { '[' stringBounds={'*|+|?'} ']' } */,
					sr0._007 /* 967035879 { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					sr0._003 /* -147651631 { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
					sr0._008 /* 2117622423 stringBounds={'*|+|?'} */
				}, null
			);
		private final @NonNull EClassData _31 // NameExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._024 /* 142297633 { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._050 /* 1874065675 { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._024 /* 142297633 { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._050 /* 1874065675 { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._050 /* 1874065675 { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._26) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._5) /* CurlyBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._34) /* RoundBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						iv._37) /* SquareBracketedClauseCS */
				}
			);
		private final @NonNull EClassData _32 // NavigatingArgCS
			= new EClassData(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
				new @NonNull SerializationRule [] {
					sr0._052 /* 792180005 ownedNameExpression=NavigatingArgExpCS */,
					sr0._051 /* 2001927055 { ':' ownedType=TypeExpCS } */,
					sr0._053 /* -876125479 { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._055 /* -1846466997 { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._054 /* 474079684 { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					sr0._056 /* -515155520 { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */,
					sr0._058 /* 2144606011 { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					sr0._057 /* 476728591 { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._060 /* -901533432 { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._059 /* -2138627980 { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					sr0._061 /* -340784784 { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						iv._2) /* CoIteratorVariableCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						iv._70) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _33 // NestedExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._036 /* -99563695 { '(' ownedExpression=ExpCS ')' } */,
					sr0._036 /* -99563695 { '(' ownedExpression=ExpCS ')' } */,
					sr0._062 /* 50101635 { '(' ownedExpression=ExpCS ')' } */,
					sr0._062 /* 50101635 { '(' ownedExpression=ExpCS ')' } */,
					sr0._062 /* 50101635 { '(' ownedExpression=ExpCS ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _34 // NullLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._063 /* 1813019361 'null' */,
					sr0._063 /* 1813019361 'null' */,
					sr0._063 /* 1813019361 'null' */,
					sr0._063 /* 1813019361 'null' */
				}, null
			);
		private final @NonNull EClassData _35 // NumberLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._032 /* -1525300814 symbol=NUMBER_LITERAL */,
					sr0._032 /* -1525300814 symbol=NUMBER_LITERAL */,
					sr1._064 /* 2110962809 symbol=NUMBER_LITERAL */,
					sr1._064 /* 2110962809 symbol=NUMBER_LITERAL */,
					sr1._064 /* 2110962809 symbol=NUMBER_LITERAL */,
					sr1._064 /* 2110962809 symbol=NUMBER_LITERAL */
				}, null
			);
		private final @NonNull EClassData _36 // OCLinEcoreConstraintCS
			= new EClassData(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS,
				new @NonNull SerializationRule [] {
					sr1._126 /* -1299011769 { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' } */,
					sr1._127 /* -742546036 { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */,
					sr2._138 /* 1279198002 { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */,
					sr2._139 /* -1107154491 { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						iv._36) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						iv._36) /* SpecificationCS */
				}
			);
		private final @NonNull EClassData _37 // OperationCS
			= new EClassData(BaseCSPackage.Literals.OPERATION_CS,
				new @NonNull SerializationRule [] {
					sr2._130 /* 6521048 { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._133 /* -934365425 { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._129 /* 1298145114 { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._131 /* -1276557736 { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._132 /* 1619370068 { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._134 /* -84197786 { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._130 /* 6521048 { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._133 /* -934365425 { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._129 /* 1298145114 { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._131 /* -1276557736 { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._132 /* 1619370068 { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._134 /* -84197786 { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						iv._30) /* PreconditionConstraintCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						iv._25) /* ParameterCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						iv._29) /* PostconditionConstraintCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
						iv._36) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						iv._60) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._46) /* TemplateSignatureCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassData _38 // PackageCS
			= new EClassData(BaseCSPackage.Literals.PACKAGE_CS,
				new @NonNull SerializationRule [] {
					sr2._135 /* -466056533 { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
					sr2._136 /* -1472673508 { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */,
					sr2._135 /* -466056533 { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
					sr2._136 /* -1472673508 { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						iv._24) /* PackageCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
						iv._41) /* ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
				}
			);
		private final @NonNull EClassData _39 // ParameterCS
			= new EClassData(BaseCSPackage.Literals.PARAMETER_CS,
				new @NonNull SerializationRule [] {
					sr2._137 /* 1466714894 { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassData _40 // PathElementCS
			= new EClassData(BaseCSPackage.Literals.PATH_ELEMENT_CS,
				new @NonNull SerializationRule [] {
					sr0._000 /* -980253870 referredElement=UnrestrictedName */,
					sr0._009 /* -332589389 referredElement=UnreservedName */,
					sr1._093 /* -1550660453 referredElement=UnrestrictedName */
				}, null
			);
		private final @NonNull EClassData _41 // PathElementWithURICS
			= new EClassData(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
				new @NonNull SerializationRule [] {
					sr1._092 /* -288801386 referredElement=URI */
				}, null
			);
		private final @NonNull EClassData _42 // PathNameCS
			= new EClassData(BaseCSPackage.Literals.PATH_NAME_CS,
				new @NonNull SerializationRule [] {
					sr0._010 /* -65672703 { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */,
					sr1._073 /* -784880341 ownedPathElements+=FirstPathElementCS */,
					sr1._094 /* -1812055452 { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						iv._63) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
				}
			);
		private final @NonNull EClassData _43 // PatternExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
				new @NonNull SerializationRule [] {
					sr1._065 /* 709804144 { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassData _44 // PrefixExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._038 /* 1319257846 { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr1._066 /* 33542119 { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					sr0._038 /* 1319257846 { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr1._066 /* 33542119 { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					sr1._066 /* 33542119 { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					sr1._067 /* 1657789762 { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._68) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _45 // PrimitiveTypeRefCS
			= new EClassData(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
				new @NonNull SerializationRule [] {
					sr1._068 /* 864352851 name=PrimitiveTypeIdentifier */,
					sr1._068 /* 864352851 name=PrimitiveTypeIdentifier */,
					sr1._082 /* 1462877970 { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._068 /* 864352851 name=PrimitiveTypeIdentifier */,
					sr1._068 /* 864352851 name=PrimitiveTypeIdentifier */,
					sr1._087 /* -1737527105 { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._156 /* 343064501 { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._068 /* 864352851 name=PrimitiveTypeIdentifier */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassData _46 // ReferenceCS
			= new EClassData(BaseCSPackage.Literals.REFERENCE_CS,
				new @NonNull SerializationRule [] {
					sr2._144 /* 1760410727 { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._142 /* -1714809403 { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._145 /* -1950972974 { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._143 /* 1677582584 { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._140 /* 200627942 { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._141 /* -1361474221 { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._144 /* 1760410727 { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._142 /* -1714809403 { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._145 /* -1950972974 { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._143 /* 1677582584 { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._140 /* 200627942 { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._141 /* -1361474221 { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._144 /* 1760410727 { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._142 /* -1714809403 { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._145 /* -1950972974 { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._143 /* 1677582584 { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._140 /* 200627942 { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._141 /* -1361474221 { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						iv._36) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._61) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
						iv._11) /* ImplicitOppositeCS */
				}
			);
		private final @NonNull EClassData _47 // RoundBracketedClauseCS
			= new EClassData(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule [] {
					sr1._069 /* 1544381179 { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						iv._21) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
				}
			);
		private final @NonNull EClassData _48 // SelfExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
				new @NonNull SerializationRule [] {
					sr1._070 /* 166326016 'self' */,
					sr1._070 /* 166326016 'self' */,
					sr1._070 /* 166326016 'self' */
				}, null
			);
		private final @NonNull EClassData _49 // ShadowPartCS
			= new EClassData(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
				new @NonNull SerializationRule [] {
					sr1._072 /* -380109728 ownedInitExpression=StringLiteralExpCS */,
					sr1._071 /* 1676185538 { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _50 // SquareBracketedClauseCS
			= new EClassData(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule [] {
					sr1._074 /* -1610593093 { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _51 // StringLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._026 /* 1863415793 segments+=StringLiteral[+] */,
					sr0._026 /* 1863415793 segments+=StringLiteral[+] */,
					sr1._075 /* -640515248 segments+=StringLiteral[+] */,
					sr1._075 /* -640515248 segments+=StringLiteral[+] */,
					sr1._075 /* -640515248 segments+=StringLiteral[+] */,
					sr1._075 /* -640515248 segments+=StringLiteral[+] */
				}, null
			);
		private final @NonNull EClassData _52 // StructuredClassCS
			= new EClassData(BaseCSPackage.Literals.STRUCTURED_CLASS_CS,
				new @NonNull SerializationRule [] {
					sr2._149 /* 1172601396 { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr2._148 /* 826917569 { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._149 /* 1172601396 { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr2._148 /* 826917569 { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._149 /* 1172601396 { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr2._148 /* 826917569 { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						iv._13) /* InvariantConstraintCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
						iv._60) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._46) /* TemplateSignatureCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
						iv._40) /* AttributeCS|ReferenceCS|StructuralFeatureCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._43) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
						iv._23) /* OperationCS */
				}
			);
		private final @NonNull EClassData _53 // SysMLCS
			= new EClassData(OCLinEcoreCSPackage.Literals.SYS_MLCS,
				new @NonNull SerializationRule [] {
					sr2._150 /* -2012141884 { 'sysml' ownedDetails+=DetailCS ';' } */,
					sr2._151 /* -275278063 { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */,
					sr2._150 /* -2012141884 { 'sysml' ownedDetails+=DetailCS ';' } */,
					sr2._151 /* -275278063 { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						iv._6) /* DetailCS */
				}
			);
		private final @NonNull EClassData _54 // TemplateBindingCS
			= new EClassData(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
				new @NonNull SerializationRule [] {
					sr0._011 /* 796808738 { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						iv._45) /* TemplateParameterSubstitutionCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassData _55 // TemplateParameterSubstitutionCS
			= new EClassData(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
				new @NonNull SerializationRule [] {
					sr0._012 /* 1625968706 ownedActualParameter=TypeRefCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						iv._72) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
				}
			);
		private final @NonNull EClassData _56 // TemplateSignatureCS
			= new EClassData(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
				new @NonNull SerializationRule [] {
					sr2._152 /* 1104385433 { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */,
					sr2._153 /* -1385709174 { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						iv._56) /* TypeParameterCS */
				}
			);
		private final @NonNull EClassData _57 // TopLevelCS
			= new EClassData(OCLinEcoreCSPackage.Literals.TOP_LEVEL_CS,
				new @NonNull SerializationRule [] {
					sr2._154 /* -470303527 { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						iv._24) /* PackageCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
						iv._12) /* ImportCS */
				}
			);
		private final @NonNull EClassData _58 // TupleLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._025 /* -889971061 { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._025 /* -889971061 { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._076 /* -508314783 { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._076 /* -508314783 { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._076 /* -508314783 { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						iv._47) /* TupleLiteralPartCS */
				}
			);
		private final @NonNull EClassData _59 // TupleLiteralPartCS
			= new EClassData(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
				new @NonNull SerializationRule [] {
					sr1._077 /* 1389961047 { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassData _60 // TuplePartCS
			= new EClassData(BaseCSPackage.Literals.TUPLE_PART_CS,
				new @NonNull SerializationRule [] {
					sr1._078 /* -1836965325 { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassData _61 // TupleTypeCS
			= new EClassData(BaseCSPackage.Literals.TUPLE_TYPE_CS,
				new @NonNull SerializationRule [] {
					sr1._079 /* -706344470 { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._079 /* -706344470 { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._085 /* -183141247 { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._079 /* -706344470 { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._079 /* -706344470 { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._088 /* 27474662 { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._157 /* -2084000380 { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._079 /* -706344470 { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						iv._48) /* TuplePartCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassData _62 // TypeLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr0._029 /* -2100486945 ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._029 /* -2100486945 ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._086 /* 2113014961 ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._086 /* 2113014961 ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._086 /* 2113014961 ownedType=TypeLiteralWithMultiplicityCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						iv._53) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
				}
			);
		private final @NonNull EClassData _63 // TypeNameExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
				new @NonNull SerializationRule [] {
					sr1._080 /* 829123167 { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._091 /* -53400149 { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */,
					sr1._091 /* -53400149 { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._26) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._5) /* CurlyBracketedClauseCS */
				}
			);
		private final @NonNull EClassData _64 // TypeParameterCS
			= new EClassData(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
				new @NonNull SerializationRule [] {
					sr0._013 /* -302465592 { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						iv._60) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassData _65 // TypedTypeRefCS
			= new EClassData(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
				new @NonNull SerializationRule [] {
					sr2._163 /* -1518315428 ownedPathName=PathNameCS */,
					sr2._164 /* 101447907 { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr2._162 /* -2074416897 { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
					sr2._159 /* 52611878 { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._155 /* -570759867 { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._160 /* 502168572 { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._163 /* -1518315428 ownedPathName=PathNameCS */,
					sr2._164 /* 101447907 { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr2._162 /* -2074416897 { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
					sr2._163 /* -1518315428 ownedPathName=PathNameCS */,
					sr2._164 /* 101447907 { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr2._162 /* -2074416897 { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						iv._26) /* PathNameCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						iv._44) /* TemplateBindingCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassData _66 // UnlimitedNaturalLiteralExpCS
			= new EClassData(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
				new @NonNull SerializationRule [] {
					sr1._095 /* 511060279 '*' */,
					sr1._095 /* 511060279 '*' */,
					sr1._095 /* 511060279 '*' */,
					sr1._095 /* 511060279 '*' */
				}, null
			);
		private final @NonNull EClassData _67 // VariableCS
			= new EClassData(EssentialOCLCSPackage.Literals.VARIABLE_CS,
				new @NonNull SerializationRule [] {
					sr0._016 /* 1619828400 { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._55) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassData _68 // WildcardTypeRefCS
			= new EClassData(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
				new @NonNull SerializationRule [] {
					sr0._014 /* 1554449207 { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */,
					sr0._014 /* 1554449207 { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
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
				ms._040 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._124 /* 1*PathElementCS::referredElement=UnrestrictedName */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
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
		private @NonNull SerializationRule _001 = new SerializationRule(55,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._083 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._027 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=LOWER */,
				st._205 /* V00*next-2-steps */,
				st._008 /* 1*'..' */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=UPPER */
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
		private @NonNull SerializationRule _002 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._119 /* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._083 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._027 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._021 /* 1*'[' */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=LOWER */,
				st._206 /* V00*next-2-steps */,
				st._008 /* 1*'..' */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=UPPER */,
				st._217 /* V01*'|1' */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					ev._23)
			},
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._23, MultiplicativeCardinality.ZERO_OR_ONE)
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
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _003 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._084 /* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._028 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._021 /* 1*'[' */,
				st._103 /* 1*MultiplicityStringCS::stringBounds */,
				st._193 /* V00*'|1' */,
				st._022 /* 1*']' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._5 /* «! » + «value» */
			},
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._04),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					ev._23)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._04, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._23, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' }
		private @NonNull SerializationRule _004 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._083 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._027 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._184 /* 1*next-6-steps */,
				st._021 /* 1*'[' */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=LOWER */,
				st._206 /* V00*next-2-steps */,
				st._008 /* 1*'..' */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=UPPER */,
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
		private @NonNull SerializationRule _005 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._028 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*next-3-steps */,
				st._021 /* 1*'[' */,
				st._103 /* 1*MultiplicityStringCS::stringBounds */,
				st._022 /* 1*']' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._5 /* «! » + «value» */
			},
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._04)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._04, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' }
		private @NonNull SerializationRule _006 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._083 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._027 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._021 /* 1*'[' */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=LOWER */,
				st._206 /* V00*next-2-steps */,
				st._008 /* 1*'..' */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=UPPER */,
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
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} '|?' ']' }
		private @NonNull SerializationRule _007 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._028 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._021 /* 1*'[' */,
				st._103 /* 1*MultiplicityStringCS::stringBounds */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._04)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._04, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityStringCS : stringBounds={'*|+|?'}
		private @NonNull SerializationRule _008 = new SerializationRule(57,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._028 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._103 /* 1*MultiplicityStringCS::stringBounds */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._04)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._04, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Base::NextPathElementCS : referredElement=UnreservedName
		private @NonNull SerializationRule _009 = new SerializationRule(67,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._040 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._123 /* 1*PathElementCS::referredElement=UnreservedName */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
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
		private @NonNull SerializationRule _010 = new SerializationRule(73,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._247 /* check-rule basecs::PathNameCS.ownedPathElements : 31|67 */,
				ms._061 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._126 /* 1*PathNameCS::ownedPathElements+=FirstPathElementCS */,
				st._205 /* V00*next-2-steps */,
				st._010 /* 1*'::' */,
				st._127 /* 1*PathNameCS::ownedPathElements+=NextPathElementCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._22) /* FirstPathElementCS|NextPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(31, MultiplicativeCardinality.ONE)
					}
				)
			});
		// Base::TemplateBindingCS : { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _011 = new SerializationRule(99,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._256 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 100 */,
				ms._255 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 56 */,
				ms._127 /* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				ms._064 /* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._183 /* 1*next-5-steps */,
				st._143 /* 1*TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS */,
				st._205 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._143 /* 1*TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS */,
				st._222 /* V01*TemplateBindingCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					iv._45) /* TemplateParameterSubstitutionCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(100, MultiplicativeCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// Base::TemplateParameterSubstitutionCS : ownedActualParameter=TypeRefCS
		private @NonNull SerializationRule _012 = new SerializationRule(100,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._257 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 115 */,
				ms._047 /* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._144 /* 1*TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					iv._57) /* TypeRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(115, MultiplicativeCardinality.ONE)
					}
				)
			});
		// Base::TypeParameterCS : { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] }
		private @NonNull SerializationRule _013 = new SerializationRule(114,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._261 /* check-rule basecs::TypeParameterCS.ownedExtends : 117 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._068 /* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				ms._111 /* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._212 /* V00*next-5-steps */,
				st._035 /* 1*'extends' */,
				st._152 /* 1*TypeParameterCS::ownedExtends+=TypedRefCS */,
				st._228 /* V01*next-2-steps */,
				st._002 /* 1*'&&' */,
				st._152 /* 1*TypeParameterCS::ownedExtends+=TypedRefCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					iv._59) /* TypedRefCS */
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
					new RuleIndex_MultiplicativeCardinality(117, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::WildcardTypeRefCS : { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
		private @NonNull SerializationRule _014 = new SerializationRule(129,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._267 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : 117 */,
				ms._102 /* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._016 /* 1*'?' */,
				st._205 /* V00*next-2-steps */,
				st._035 /* 1*'extends' */,
				st._160 /* 1*WildcardTypeRefCS::ownedExtends=TypedRefCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					iv._59) /* TypedRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(117, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::BooleanLiteralExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _015 = new SerializationRule(5,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._002 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._066 /* 1*BooleanLiteralExpCS::symbol */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._14)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._14, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::CoIteratorVariableCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] }
		private @NonNull SerializationRule _016 = new SerializationRule(7,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._320 /* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
				ms._101 /* assign V0 = |VariableCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._205 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._159 /* 1*VariableCS::ownedType=TypeExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */
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
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _017 = new SerializationRule(8,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._272 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9 */,
				ms._273 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11 */,
				ms._003 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._057 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._104 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._186 /* 1*next-8-steps */,
				st._069 /* 1*CollectionLiteralExpCS::ownedType=CollectionTypeCS */,
				st._060 /* 1*'{' */,
				st._210 /* V00*next-4-steps */,
				st._068 /* 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS */,
				st._228 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._068 /* 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					iv._3) /* CollectionLiteralPartCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					iv._4) /* CollectionTypeCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(9, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(11, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : ownedExpression=PatternExpCS
		private @NonNull SerializationRule _018 = new SerializationRule(9,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._275 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 74 */,
				ms._004 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._071 /* 1*CollectionLiteralPartCS::ownedExpression=PatternExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					iv._27) /* PatternExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(74, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] }
		private @NonNull SerializationRule _019 = new SerializationRule(9,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._276 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 30 */,
				ms._274 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 30 */,
				ms._073 /* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
				ms._004 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._070 /* 1*CollectionLiteralPartCS::ownedExpression=ExpCS */,
				st._205 /* V00*next-2-steps */,
				st._008 /* 1*'..' */,
				st._072 /* 1*CollectionLiteralPartCS::ownedLastExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionPatternCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' }
		private @NonNull SerializationRule _020 = new SerializationRule(10,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._277 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74 */,
				ms._278 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 11 */,
				ms._074 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._105 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._005 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._161 /* 1*next-10-steps */,
				st._074 /* 1*CollectionPatternCS::ownedType=CollectionTypeCS */,
				st._060 /* 1*'{' */,
				st._214 /* V00*next-6-steps */,
				st._073 /* 1*CollectionPatternCS::ownedParts+=PatternExpCS */,
				st._228 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._073 /* 1*CollectionPatternCS::ownedParts+=PatternExpCS */,
				st._006 /* 1*'++' */,
				st._075 /* 1*CollectionPatternCS::restVariableName=Identifier */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._27) /* PatternExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._4) /* CollectionTypeCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(74, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(11, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionTypeCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
		private @NonNull SerializationRule _021 = new SerializationRule(11,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
				ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
				ms._075 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._006 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._115 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._184 /* 1*next-6-steps */,
				st._076 /* 1*CollectionTypeCS::name=CollectionTypeIdentifier */,
				st._209 /* V00*next-4-steps */,
				st._003 /* 1*'(' */,
				st._077 /* 1*CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS */,
				st._219 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._50) /* TypeExpWithoutMultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(108, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CurlyBracketedClauseCS : { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _022 = new SerializationRule(13,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._282 /* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 90 */,
				ms._058 /* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
				ms._106 /* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._060 /* 1*'{' */,
				st._209 /* V00*next-4-steps */,
				st._080 /* 1*CurlyBracketedClauseCS::ownedParts+=ShadowPartCS */,
				st._227 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._080 /* 1*CurlyBracketedClauseCS::ownedParts+=ShadowPartCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					iv._35) /* ShadowPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(90, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ElseIfThenExpCS : { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS }
		private @NonNull SerializationRule _023 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._289 /* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 30 */,
				ms._288 /* check-rule essentialoclcs::IfThenExpCS.ownedCondition : 30 */,
				ms._016 /* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
				ms._015 /* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._032 /* 1*'elseif' */,
				st._087 /* 1*IfThenExpCS::ownedCondition=ExpCS */,
				st._058 /* 1*'then' */,
				st._088 /* 1*IfThenExpCS::ownedThenExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _024 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._269 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73 */,
				ms._268 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._270 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84 */,
				ms._271 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93 */,
				ms._173 /* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				ms._156 /* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				ms._113 /* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				ms._071 /* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				ms._000 /* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._064 /* 1*AbstractNameExpCS::ownedPathName=PathNameCS */,
				st._194 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
				st._218 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS */,
				st._237 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
				st._261 /* V03*next-2-steps */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._08)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._34) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._37) /* SquareBracketedClauseCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._08, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(73, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(84, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(93, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
		private @NonNull SerializationRule _025 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._314 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104 */,
				ms._066 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._020 /* 1*'Tuple' */,
				st._060 /* 1*'{' */,
				st._146 /* 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS */,
				st._207 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._146 /* 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._47) /* TupleLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(104, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _026 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._093 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._201 /* V00*StringLiteralExpCS::segments+=StringLiteral */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
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
		// EssentialOCL::ExpCS : { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS }
		private @NonNull SerializationRule _027 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._290 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : 78 */,
				ms._306 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 30 */,
				ms._039 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._018 /* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*next-3-steps */,
				st._090 /* 1*InfixExpCS::ownedLeft=PrefixedPrimaryExpCS */,
				st._104 /* 1*NamedElementCS::name=BinaryOperatorName */,
				st._117 /* 1*OperatorExpCS::ownedRight=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					iv._33) /* PrefixedPrimaryExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._9) /* ExpCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(78, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _028 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._272 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9 */,
				ms._273 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11 */,
				ms._003 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._057 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._104 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._186 /* 1*next-8-steps */,
				st._069 /* 1*CollectionLiteralExpCS::ownedType=CollectionTypeCS */,
				st._060 /* 1*'{' */,
				st._210 /* V00*next-4-steps */,
				st._068 /* 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS */,
				st._228 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._068 /* 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					iv._3) /* CollectionLiteralPartCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					iv._4) /* CollectionTypeCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(9, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(11, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _029 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._315 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112 */,
				ms._049 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._148 /* 1*TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._52) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(112, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _030 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._296 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51 */,
				ms._295 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50 */,
				ms._021 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._060 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._107 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._186 /* 1*next-8-steps */,
				st._095 /* 1*MapLiteralExpCS::ownedType=MapTypeCS */,
				st._060 /* 1*'{' */,
				st._210 /* V00*next-4-steps */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS */,
				st._228 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					iv._16) /* MapTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					iv._15) /* MapLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(51, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _031 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._287 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30 */,
				ms._285 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30 */,
				ms._284 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74 */,
				ms._286 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
				ms._013 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._080 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._014 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._012 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._186 /* 1*next-8-steps */,
				st._036 /* 1*'if' */,
				st._084 /* 1*IfExpCS::ownedCondition=ExpCS|PatternExpCS */,
				st._058 /* 1*'then' */,
				st._086 /* 1*IfExpCS::ownedThenExpression=ExpCS */,
				st._197 /* V00*IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS */,
				st._031 /* 1*'else' */,
				st._085 /* 1*IfExpCS::ownedElseExpression=ExpCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._28) /* ExpCS|PatternExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._7) /* ElseIfThenExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(74, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _032 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._038 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*NumberLiteralExpCS::symbol=NUMBER_LITERAL */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
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
		// EssentialOCL::ExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _033 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._291 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30 */,
				ms._019 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._018 /* 1*'Lambda' */,
				st._060 /* 1*'{' */,
				st._091 /* 1*LambdaLiteralExpCS::ownedExpressionCS=ExpCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : 'null'
		private @NonNull SerializationRule _034 = new SerializationRule(30,
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
			null,
			null,
			null);
		// EssentialOCL::ExpCS : 'invalid'
		private @NonNull SerializationRule _035 = new SerializationRule(30,
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
			null,
			null,
			null);
		// EssentialOCL::ExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _036 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._305 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 30 */,
				ms._037 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*next-3-steps */,
				st._003 /* 1*'(' */,
				st._113 /* 1*NestedExpCS::ownedExpression=ExpCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._5 /* «! » + «value» */
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : '*'
		private @NonNull SerializationRule _037 = new SerializationRule(30,
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
			null,
			null,
			null);
		// EssentialOCL::ExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _038 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._308 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 78 */,
				ms._039 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._171 /* 1*next-2-steps */,
				st._106 /* 1*NamedElementCS::name=UnaryOperatorName */,
				st._119 /* 1*OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._33) /* PrefixedPrimaryExpCS */
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
					new RuleIndex_MultiplicativeCardinality(78, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _039 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._002 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._066 /* 1*BooleanLiteralExpCS::symbol */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._14)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._14, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::ExpCS : 'self'
		private @NonNull SerializationRule _040 = new SerializationRule(30,
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
			null,
			null,
			null);
		// EssentialOCL::IfExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _041 = new SerializationRule(36,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._287 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30 */,
				ms._285 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30 */,
				ms._284 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74 */,
				ms._286 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
				ms._013 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._080 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._014 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._012 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._186 /* 1*next-8-steps */,
				st._036 /* 1*'if' */,
				st._084 /* 1*IfExpCS::ownedCondition=ExpCS|PatternExpCS */,
				st._058 /* 1*'then' */,
				st._086 /* 1*IfExpCS::ownedThenExpression=ExpCS */,
				st._197 /* V00*IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS */,
				st._031 /* 1*'else' */,
				st._085 /* 1*IfExpCS::ownedElseExpression=ExpCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._28) /* ExpCS|PatternExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._7) /* ElseIfThenExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(74, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::InvalidLiteralExpCS : 'invalid'
		private @NonNull SerializationRule _042 = new SerializationRule(40,
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
			null,
			null,
			null);
		// EssentialOCL::LambdaLiteralExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _043 = new SerializationRule(44,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._291 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30 */,
				ms._019 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._018 /* 1*'Lambda' */,
				st._060 /* 1*'{' */,
				st._091 /* 1*LambdaLiteralExpCS::ownedExpressionCS=ExpCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::LetExpCS : { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
		private @NonNull SerializationRule _044 = new SerializationRule(45,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._292 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : 30 */,
				ms._293 /* check-rule essentialoclcs::LetExpCS.ownedVariables : 46 */,
				ms._020 /* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
				ms._059 /* assign V0 = (|LetExpCS::ownedVariables| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._043 /* 1*'let' */,
				st._093 /* 1*LetExpCS::ownedVariables+=LetVariableCS */,
				st._206 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._093 /* 1*LetExpCS::ownedVariables+=LetVariableCS */,
				st._038 /* 1*'in' */,
				st._092 /* 1*LetExpCS::ownedInExpression=ExpCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					iv._14) /* LetVariableCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(46, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::LetVariableCS : { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _045 = new SerializationRule(46,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._320 /* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
				ms._319 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 30 */,
				ms._294 /* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 84 */,
				ms._056 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._133 /* assign V1 = |VariableCS::ownedType| */,
				ms._081 /* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._198 /* V00*LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS */,
				st._226 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._159 /* 1*VariableCS::ownedType=TypeExpCS */,
				st._014 /* 1*'=' */,
				st._158 /* 1*VariableCS::ownedInitExpression=ExpCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._34) /* RoundBracketedClauseCS */
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
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(84, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::MapLiteralExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _046 = new SerializationRule(49,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._296 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51 */,
				ms._295 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50 */,
				ms._021 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._060 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._107 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._186 /* 1*next-8-steps */,
				st._095 /* 1*MapLiteralExpCS::ownedType=MapTypeCS */,
				st._060 /* 1*'{' */,
				st._210 /* V00*next-4-steps */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS */,
				st._228 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					iv._16) /* MapTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					iv._15) /* MapLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(51, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::MapLiteralPartCS : { ownedKey=ExpCS '<-' ownedValue=ExpCS }
		private @NonNull SerializationRule _047 = new SerializationRule(50,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._297 /* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 30 */,
				ms._298 /* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 30 */,
				ms._023 /* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
				ms._022 /* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*next-3-steps */,
				st._096 /* 1*MapLiteralPartCS::ownedKey=ExpCS */,
				st._013 /* 1*'<-' */,
				st._097 /* 1*MapLiteralPartCS::ownedValue=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::MapTypeCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
		private @NonNull SerializationRule _048 = new SerializationRule(51,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
				ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
				ms._082 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._025 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._024 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._019 /* 1*'Map' */,
				st._212 /* V00*next-5-steps */,
				st._003 /* 1*'(' */,
				st._098 /* 1*MapTypeCS::ownedKeyType=TypeExpCS */,
				st._007 /* 1*',' */,
				st._099 /* 1*MapTypeCS::ownedValueType=TypeExpCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._49) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._09, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::Model : ownedExpression=ExpCS
		private @NonNull SerializationRule _049 = new SerializationRule(52,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._281 /* check-rule essentialoclcs::ContextCS.ownedExpression : 30 */,
				ms._010 /* assert (|ContextCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._079 /* 1*ContextCS::ownedExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NameExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _050 = new SerializationRule(59,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._269 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73 */,
				ms._268 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._270 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84 */,
				ms._271 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93 */,
				ms._173 /* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				ms._156 /* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				ms._113 /* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				ms._071 /* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				ms._000 /* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._064 /* 1*AbstractNameExpCS::ownedPathName=PathNameCS */,
				st._194 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
				st._218 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS */,
				st._237 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
				st._261 /* V03*next-2-steps */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._08)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._34) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._37) /* SquareBracketedClauseCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._08, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(73, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(84, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(93, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _051 = new SerializationRule(60,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._033 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._171 /* 1*next-2-steps */,
				st._009 /* 1*':' */,
				st._112 /* 1*NavigatingArgCS::ownedType=TypeExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : ownedNameExpression=NavigatingArgExpCS
		private @NonNull SerializationRule _052 = new SerializationRule(60,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(61, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _053 = new SerializationRule(60,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._087 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._030 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._184 /* 1*next-6-steps */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				st._013 /* 1*'<-' */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS */,
				st._207 /* V00*next-2-steps */,
				st._014 /* 1*'=' */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(7, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(61, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _054 = new SerializationRule(60,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._031 /* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				ms._121 /* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				ms._088 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._187 /* 1*next-9-steps */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				st._205 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._112 /* 1*NavigatingArgCS::ownedType=TypeExpCS */,
				st._228 /* V01*next-2-steps */,
				st._013 /* 1*'<-' */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS */,
				st._038 /* 1*'in' */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(7, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(61, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _055 = new SerializationRule(60,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._122 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				ms._086 /* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				ms._033 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._187 /* 1*next-9-steps */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				st._009 /* 1*':' */,
				st._112 /* 1*NavigatingArgCS::ownedType=TypeExpCS */,
				st._207 /* V00*next-2-steps */,
				st._013 /* 1*'<-' */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS */,
				st._230 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(7, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(61, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingBarArgCS : { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _056 = new SerializationRule(62,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._088 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._036 /* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
				ms._122 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._186 /* 1*next-8-steps */,
				st._061 /* 1*'|' */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				st._213 /* V00*next-5-steps */,
				st._009 /* 1*':' */,
				st._112 /* 1*NavigatingArgCS::ownedType=TypeExpCS */,
				st._229 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS */
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._22)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._22, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(61, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _057 = new SerializationRule(63,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._087 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._030 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._034 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._007 /* 1*',' */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				st._013 /* 1*'<-' */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS */,
				st._208 /* V00*next-2-steps */,
				st._014 /* 1*'=' */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS */
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._05, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(7, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(61, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS }
		private @NonNull SerializationRule _058 = new SerializationRule(63,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._034 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._171 /* 1*next-2-steps */,
				st._007 /* 1*',' */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._3 /* «! » + «value» + «? » */,
				null
			},
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._05, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(61, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _059 = new SerializationRule(63,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._031 /* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				ms._121 /* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				ms._088 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._034 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._161 /* 1*next-10-steps */,
				st._007 /* 1*',' */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				st._206 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._112 /* 1*NavigatingArgCS::ownedType=TypeExpCS */,
				st._229 /* V01*next-2-steps */,
				st._013 /* 1*'<-' */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS */,
				st._038 /* 1*'in' */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS */
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._05, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(7, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(61, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _060 = new SerializationRule(63,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._122 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				ms._086 /* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				ms._033 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._034 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._161 /* 1*next-10-steps */,
				st._007 /* 1*',' */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				st._009 /* 1*':' */,
				st._112 /* 1*NavigatingArgCS::ownedType=TypeExpCS */,
				st._208 /* V00*next-2-steps */,
				st._013 /* 1*'<-' */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS */,
				st._231 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS */
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._05, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(7, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(61, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingSemiArgCS : { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _061 = new SerializationRule(64,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._088 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._035 /* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
				ms._122 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._186 /* 1*next-8-steps */,
				st._011 /* 1*';' */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS */,
				st._213 /* V00*next-5-steps */,
				st._009 /* 1*':' */,
				st._112 /* 1*NavigatingArgCS::ownedType=TypeExpCS */,
				st._229 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS */
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._07)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._07, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(61, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NestedExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _062 = new SerializationRule(66,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._305 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 30 */,
				ms._037 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*next-3-steps */,
				st._003 /* 1*'(' */,
				st._113 /* 1*NestedExpCS::ownedExpression=ExpCS */,
				st._004 /* 1*')' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._2 /* «! » + «value» + «! » */,
				null,
				ss._5 /* «! » + «value» */
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NullLiteralExpCS : 'null'
		private @NonNull SerializationRule _063 = new SerializationRule(68,
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
			null,
			null,
			null);
	}
	private class _SerializationRules1
	{
		// EssentialOCL::NumberLiteralExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _064 = new SerializationRule(69,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._038 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*NumberLiteralExpCS::symbol=NUMBER_LITERAL */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
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
		private @NonNull SerializationRule _065 = new SerializationRule(74,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._309 /* check-rule essentialoclcs::PatternExpCS.ownedPatternType : 107 */,
				ms._042 /* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
				ms._091 /* assign V0 = |PatternExpCS::patternVariableName| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*next-3-steps */,
				st._200 /* V00*PatternExpCS::patternVariableName=UnrestrictedName */,
				st._009 /* 1*':' */,
				st._129 /* 1*PatternExpCS::ownedPatternType=TypeExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					iv._49) /* TypeExpCS */
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
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedLetExpCS : { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		private @NonNull SerializationRule _066 = new SerializationRule(77,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._307 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 77 */,
				ms._039 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._171 /* 1*next-2-steps */,
				st._106 /* 1*NamedElementCS::name=UnaryOperatorName */,
				st._118 /* 1*OperatorExpCS::ownedRight=PrefixedLetExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._31) /* PrefixedLetExpCS */
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
					new RuleIndex_MultiplicativeCardinality(77, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedPrimaryExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _067 = new SerializationRule(78,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._308 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 78 */,
				ms._039 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._171 /* 1*next-2-steps */,
				st._106 /* 1*NamedElementCS::name=UnaryOperatorName */,
				st._119 /* 1*OperatorExpCS::ownedRight=PrefixedPrimaryExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._33) /* PrefixedPrimaryExpCS */
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
					new RuleIndex_MultiplicativeCardinality(78, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrimitiveTypeCS : name=PrimitiveTypeIdentifier
		private @NonNull SerializationRule _068 = new SerializationRule(81,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._043 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._130 /* 1*PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
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
		private @NonNull SerializationRule _069 = new SerializationRule(84,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._310 /* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 60|62|63|64 */,
				ms._062 /* assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0) */,
				ms._109 /* assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._183 /* 1*next-5-steps */,
				st._003 /* 1*'(' */,
				st._205 /* V00*next-2-steps */,
				st._135 /* 1*RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS */,
				st._221 /* V01*RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					iv._21) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(60, MultiplicativeCardinality.ZERO_OR_ONE),
					new RuleIndex_MultiplicativeCardinality(63, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(64, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::SelfExpCS : 'self'
		private @NonNull SerializationRule _070 = new SerializationRule(89,
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
			null,
			null,
			null);
		// EssentialOCL::ShadowPartCS : { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) }
		private @NonNull SerializationRule _071 = new SerializationRule(90,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._311 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 30|74 */,
				ms._044 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
				ms._045 /* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*next-3-steps */,
				st._138 /* 1*ShadowPartCS::referredProperty=UnrestrictedName */,
				st._014 /* 1*'=' */,
				st._136 /* 1*ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._28) /* ExpCS|PatternExpCS */
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
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(74, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ShadowPartCS : ownedInitExpression=StringLiteralExpCS
		private @NonNull SerializationRule _072 = new SerializationRule(90,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._312 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 95 */,
				ms._044 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._137 /* 1*ShadowPartCS::ownedInitExpression=StringLiteralExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._38) /* StringLiteralExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(95, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::SimplePathNameCS : ownedPathElements+=FirstPathElementCS
		private @NonNull SerializationRule _073 = new SerializationRule(91,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._246 /* check-rule basecs::PathNameCS.ownedPathElements : 31 */,
				ms._041 /* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._126 /* 1*PathNameCS::ownedPathElements+=FirstPathElementCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._10) /* FirstPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(31, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::SquareBracketedClauseCS : { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' }
		private @NonNull SerializationRule _074 = new SerializationRule(93,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._313 /* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 30 */,
				ms._063 /* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._184 /* 1*next-6-steps */,
				st._021 /* 1*'[' */,
				st._140 /* 1*SquareBracketedClauseCS::ownedTerms+=ExpCS */,
				st._206 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._140 /* 1*SquareBracketedClauseCS::ownedTerms+=ExpCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::StringLiteralExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _075 = new SerializationRule(95,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._093 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._201 /* V00*StringLiteralExpCS::segments+=StringLiteral */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
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
		private @NonNull SerializationRule _076 = new SerializationRule(103,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._314 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104 */,
				ms._066 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._020 /* 1*'Tuple' */,
				st._060 /* 1*'{' */,
				st._146 /* 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS */,
				st._207 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._146 /* 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._47) /* TupleLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(104, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::TupleLiteralPartCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _077 = new SerializationRule(104,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._320 /* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
				ms._319 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 30 */,
				ms._056 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._101 /* assign V0 = |VariableCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._184 /* 1*next-6-steps */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._205 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._159 /* 1*VariableCS::ownedType=TypeExpCS */,
				st._014 /* 1*'=' */,
				st._158 /* 1*VariableCS::ownedInitExpression=ExpCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
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
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TuplePartCS : { name=UnrestrictedName ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _078 = new SerializationRule(105,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._262 /* check-rule basecs::TypedElementCS.ownedType : 107 */,
				ms._051 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*next-3-steps */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._009 /* 1*':' */,
				st._153 /* 1*TypedElementCS::ownedType=TypeExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._49) /* TypeExpCS */
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
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TupleTypeCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
		private @NonNull SerializationRule _079 = new SerializationRule(106,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
				ms._048 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._067 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._110 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._153 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._187 /* 1*next-9-steps */,
				st._020 /* 1*'Tuple' */,
				st._215 /* V00*next-7-steps */,
				st._003 /* 1*'(' */,
				st._233 /* V01*next-4-steps */,
				st._147 /* 1*TupleTypeCS::ownedParts+=TuplePartCS */,
				st._244 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._147 /* 1*TupleTypeCS::ownedParts+=TuplePartCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._10)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._48) /* TuplePartCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._10, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(105, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _080 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._317 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73 */,
				ms._318 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._316 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._165 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._096 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._050 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._129 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._186 /* 1*next-8-steps */,
				st._150 /* 1*TypeNameExpCS::ownedPathName=PathNameCS */,
				st._212 /* V00*next-5-steps */,
				st._149 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
				st._232 /* V01*next-3-steps */,
				st._060 /* 1*'{' */,
				st._151 /* 1*TypeNameExpCS::ownedPatternGuard=ExpCS */,
				st._063 /* 1*'}' */,
				st._241 /* V02*TypedRefCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(73, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _081 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
				ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._132 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._082 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._025 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._024 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._186 /* 1*next-8-steps */,
				st._019 /* 1*'Map' */,
				st._212 /* V00*next-5-steps */,
				st._003 /* 1*'(' */,
				st._098 /* 1*MapTypeCS::ownedKeyType=TypeExpCS */,
				st._007 /* 1*',' */,
				st._099 /* 1*MapTypeCS::ownedValueType=TypeExpCS */,
				st._004 /* 1*')' */,
				st._224 /* V01*TypedRefCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._09, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _082 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._100 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._043 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._171 /* 1*next-2-steps */,
				st._130 /* 1*PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				st._203 /* V00*TypedRefCS::ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _083 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
				ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._165 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._075 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._006 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._115 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._076 /* 1*CollectionTypeCS::name=CollectionTypeIdentifier */,
				st._209 /* V00*next-4-steps */,
				st._003 /* 1*'(' */,
				st._077 /* 1*CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS */,
				st._219 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS */,
				st._004 /* 1*')' */,
				st._241 /* V02*TypedRefCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._50) /* TypeExpWithoutMultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(108, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _084 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._277 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74 */,
				ms._278 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 11 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._165 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._074 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._105 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._005 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._162 /* 1*next-11-steps */,
				st._074 /* 1*CollectionPatternCS::ownedType=CollectionTypeCS */,
				st._060 /* 1*'{' */,
				st._214 /* V00*next-6-steps */,
				st._073 /* 1*CollectionPatternCS::ownedParts+=PatternExpCS */,
				st._228 /* V01*next-2-steps */,
				st._007 /* 1*',' */,
				st._073 /* 1*CollectionPatternCS::ownedParts+=PatternExpCS */,
				st._006 /* 1*'++' */,
				st._075 /* 1*CollectionPatternCS::restVariableName=Identifier */,
				st._063 /* 1*'}' */,
				st._241 /* V02*TypedRefCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._27) /* PatternExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._4) /* CollectionTypeCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(74, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(11, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _085 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._181 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._048 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._067 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._110 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._153 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._161 /* 1*next-10-steps */,
				st._020 /* 1*'Tuple' */,
				st._215 /* V00*next-7-steps */,
				st._003 /* 1*'(' */,
				st._233 /* V01*next-4-steps */,
				st._147 /* 1*TupleTypeCS::ownedParts+=TuplePartCS */,
				st._244 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._147 /* 1*TupleTypeCS::ownedParts+=TuplePartCS */,
				st._004 /* 1*')' */,
				st._257 /* V03*TypedRefCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._10)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._48) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._10, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(105, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _086 = new SerializationRule(111,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._315 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112 */,
				ms._049 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._148 /* 1*TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._52) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(112, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _087 = new SerializationRule(112,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._100 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._043 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._171 /* 1*next-2-steps */,
				st._130 /* 1*PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				st._203 /* V00*TypedRefCS::ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _088 = new SerializationRule(112,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._181 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._048 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._067 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._110 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._153 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._161 /* 1*next-10-steps */,
				st._020 /* 1*'Tuple' */,
				st._215 /* V00*next-7-steps */,
				st._003 /* 1*'(' */,
				st._233 /* V01*next-4-steps */,
				st._147 /* 1*TupleTypeCS::ownedParts+=TuplePartCS */,
				st._244 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._147 /* 1*TupleTypeCS::ownedParts+=TuplePartCS */,
				st._004 /* 1*')' */,
				st._257 /* V03*TypedRefCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._10)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._48) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._10, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(105, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _089 = new SerializationRule(112,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
				ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._165 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._075 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._006 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._115 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._076 /* 1*CollectionTypeCS::name=CollectionTypeIdentifier */,
				st._209 /* V00*next-4-steps */,
				st._003 /* 1*'(' */,
				st._077 /* 1*CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS */,
				st._219 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS */,
				st._004 /* 1*')' */,
				st._241 /* V02*TypedRefCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._50) /* TypeExpWithoutMultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(108, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _090 = new SerializationRule(112,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
				ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._132 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._082 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._025 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._024 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._186 /* 1*next-8-steps */,
				st._019 /* 1*'Map' */,
				st._212 /* V00*next-5-steps */,
				st._003 /* 1*'(' */,
				st._098 /* 1*MapTypeCS::ownedKeyType=TypeExpCS */,
				st._007 /* 1*',' */,
				st._099 /* 1*MapTypeCS::ownedValueType=TypeExpCS */,
				st._004 /* 1*')' */,
				st._224 /* V01*TypedRefCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._09, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeNameExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
		private @NonNull SerializationRule _091 = new SerializationRule(113,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._317 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73 */,
				ms._318 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30 */,
				ms._316 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._096 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._050 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._129 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._150 /* 1*TypeNameExpCS::ownedPathName=PathNameCS */,
				st._212 /* V00*next-5-steps */,
				st._149 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
				st._232 /* V01*next-3-steps */,
				st._060 /* 1*'{' */,
				st._151 /* 1*TypeNameExpCS::ownedPatternGuard=ExpCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(73, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::URIFirstPathElementCS : referredElement=URI
		private @NonNull SerializationRule _092 = new SerializationRule(122,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._040 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._122 /* 1*PathElementCS::referredElement=URI */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
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
		// EssentialOCL::URIFirstPathElementCS : referredElement=UnrestrictedName
		private @NonNull SerializationRule _093 = new SerializationRule(122,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._040 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._125 /* 1*PathElementCS::referredElement=UnrestrictedName */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
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
		private @NonNull SerializationRule _094 = new SerializationRule(123,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._248 /* check-rule basecs::PathNameCS.ownedPathElements : 67|122 */,
				ms._061 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._128 /* 1*PathNameCS::ownedPathElements+=URIFirstPathElementCS */,
				st._205 /* V00*next-2-steps */,
				st._010 /* 1*'::' */,
				st._127 /* 1*PathNameCS::ownedPathElements+=NextPathElementCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null,
				ss._2 /* «! » + «value» + «! » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._62) /* NextPathElementCS|URIFirstPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(122, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS : '*'
		private @NonNull SerializationRule _095 = new SerializationRule(125,
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
			null,
			null,
			null);
		// OCLinEcore::AnnotationCS : { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
		private @NonNull SerializationRule _096 = new SerializationRule(1,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._085 /* assign V0 = |NamedElementCS::name| */,
				ms._103 /* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				ms._148 /* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._161 /* 1*next-10-steps */,
				st._023 /* 1*'annotation' */,
				st._199 /* V00*NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING */,
				st._236 /* V01*next-6-steps */,
				st._003 /* 1*'(' */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=DetailCS */,
				st._244 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=DetailCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(16, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::AnnotationCS : { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' }
		private @NonNull SerializationRule _097 = new SerializationRule(1,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._229 /* check-rule basecs::AnnotationCS.ownedContents : 53 */,
				ms._230 /* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._196 /* assign V5 = |AnnotationCS::ownedReferences| */,
				ms._184 /* assign V4 = |AnnotationCS::ownedContents| */,
				ms._176 /* assign V3 = |ModelElementCS::ownedAnnotations| */,
				ms._085 /* assign V0 = |NamedElementCS::name| */,
				ms._103 /* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				ms._148 /* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._165 /* 1*next-14-steps */,
				st._023 /* 1*'annotation' */,
				st._199 /* V00*NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING */,
				st._236 /* V01*next-6-steps */,
				st._003 /* 1*'(' */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=DetailCS */,
				st._244 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=DetailCS */,
				st._004 /* 1*')' */,
				st._060 /* 1*'{' */,
				st._255 /* V03*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._268 /* V04*AnnotationCS::ownedContents+=ModelElementCS */,
				st._278 /* V05*AnnotationCS::ownedReferences+=ModelElementRefCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					iv._17) /* ModelElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					iv._18) /* ModelElementRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(53, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(54, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(16, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// OCLinEcore::AnnotationCS : { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' }
		private @NonNull SerializationRule _098 = new SerializationRule(1,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._229 /* check-rule basecs::AnnotationCS.ownedContents : 53 */,
				ms._230 /* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._196 /* assign V5 = |AnnotationCS::ownedReferences| */,
				ms._184 /* assign V4 = |AnnotationCS::ownedContents| */,
				ms._176 /* assign V3 = |ModelElementCS::ownedAnnotations| */,
				ms._085 /* assign V0 = |NamedElementCS::name| */,
				ms._103 /* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				ms._148 /* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._165 /* 1*next-14-steps */,
				st._023 /* 1*'annotation' */,
				st._199 /* V00*NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING */,
				st._236 /* V01*next-6-steps */,
				st._003 /* 1*'(' */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=DetailCS */,
				st._244 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=DetailCS */,
				st._004 /* 1*')' */,
				st._060 /* 1*'{' */,
				st._255 /* V03*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._268 /* V04*AnnotationCS::ownedContents+=ModelElementCS */,
				st._278 /* V05*AnnotationCS::ownedReferences+=ModelElementRefCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					iv._17) /* ModelElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					iv._18) /* ModelElementRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(53, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(54, MultiplicativeCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(16, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::AnnotationCS : { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' }
		private @NonNull SerializationRule _099 = new SerializationRule(1,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._229 /* check-rule basecs::AnnotationCS.ownedContents : 53 */,
				ms._230 /* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._196 /* assign V5 = |AnnotationCS::ownedReferences| */,
				ms._184 /* assign V4 = |AnnotationCS::ownedContents| */,
				ms._176 /* assign V3 = |ModelElementCS::ownedAnnotations| */,
				ms._085 /* assign V0 = |NamedElementCS::name| */,
				ms._103 /* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				ms._148 /* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._165 /* 1*next-14-steps */,
				st._023 /* 1*'annotation' */,
				st._199 /* V00*NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING */,
				st._236 /* V01*next-6-steps */,
				st._003 /* 1*'(' */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=DetailCS */,
				st._244 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=DetailCS */,
				st._004 /* 1*')' */,
				st._060 /* 1*'{' */,
				st._255 /* V03*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._268 /* V04*AnnotationCS::ownedContents+=ModelElementCS */,
				st._278 /* V05*AnnotationCS::ownedReferences+=ModelElementRefCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					iv._17) /* ModelElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					iv._18) /* ModelElementRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(53, MultiplicativeCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(54, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(16, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::AttributeCS : { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _100 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._162 /* assign V2 = |StructuralFeatureCS::default| */,
				ms._130 /* assign V1 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._099 /* assign V0 = |TypedElementCS::qualifiers.'static'| */,
				ms._052 /* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
				ms._171 /* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._192 /* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._167 /* 1*next-16-steps */,
				st._028 /* 1*'definition' */,
				st._192 /* V00*'static' */,
				st._024 /* 1*'attribute' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._228 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._246 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._141 /* 1*StructuralFeatureCS::default=SINGLE_QUOTED_STRING */,
				st._265 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._272 /* V04*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::AttributeCS : { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _101 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._126 /* assign V1 = |StructuralFeatureCS::default| */,
				ms._097 /* assign V0 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._154 /* assign V2 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._180 /* assign V3 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._165 /* 1*next-14-steps */,
				st._024 /* 1*'attribute' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._206 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._229 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._141 /* 1*StructuralFeatureCS::default=SINGLE_QUOTED_STRING */,
				st._250 /* V02*next-4-steps */,
				st._060 /* 1*'{' */,
				st._258 /* V03*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::AttributeCS : { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
		private @NonNull SerializationRule _102 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._188 /* assign V4 = |ModelElementCS::ownedAnnotations| */,
				ms._126 /* assign V1 = |StructuralFeatureCS::default| */,
				ms._097 /* assign V0 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._154 /* assign V2 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._180 /* assign V3 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
				ms._200 /* assign V5 = |StructuralFeatureCS::ownedDefaultExpressions| */,
				ms._204 /* assign V6 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
				ms._214 /* assign V7 = 0 */,
				ms._218 /* assign V8 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._174 /* 1*next-26-steps */,
				st._024 /* 1*'attribute' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._206 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._229 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._141 /* 1*StructuralFeatureCS::default=SINGLE_QUOTED_STRING */,
				st._250 /* V02*next-4-steps */,
				st._060 /* 1*'{' */,
				st._258 /* V03*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._270 /* V04*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._285 /* V05*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._290 /* V06*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._299 /* V07*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._303 /* V08*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::AttributeCS : { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
		private @NonNull SerializationRule _103 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._199 /* assign V5 = |ModelElementCS::ownedAnnotations| */,
				ms._162 /* assign V2 = |StructuralFeatureCS::default| */,
				ms._130 /* assign V1 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._099 /* assign V0 = |TypedElementCS::qualifiers.'static'| */,
				ms._052 /* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
				ms._171 /* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._192 /* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
				ms._208 /* assign V6 = |StructuralFeatureCS::ownedDefaultExpressions| */,
				ms._212 /* assign V7 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
				ms._218 /* assign V8 = 0 */,
				ms._224 /* assign V9 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*next-28-steps */,
				st._028 /* 1*'definition' */,
				st._192 /* V00*'static' */,
				st._024 /* 1*'attribute' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._228 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._246 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._141 /* 1*StructuralFeatureCS::default=SINGLE_QUOTED_STRING */,
				st._265 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._272 /* V04*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._281 /* V05*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._292 /* V06*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._296 /* V07*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._307 /* V08*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._311 /* V09*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::AttributeCS : { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _104 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._162 /* assign V2 = |StructuralFeatureCS::default| */,
				ms._130 /* assign V1 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._098 /* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
				ms._053 /* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
				ms._171 /* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._192 /* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._167 /* 1*next-16-steps */,
				st._056 /* 1*'static' */,
				st._190 /* V00*'definition' */,
				st._024 /* 1*'attribute' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._228 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._246 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._141 /* 1*StructuralFeatureCS::default=SINGLE_QUOTED_STRING */,
				st._265 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._272 /* V04*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::AttributeCS : { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
		private @NonNull SerializationRule _105 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._199 /* assign V5 = |ModelElementCS::ownedAnnotations| */,
				ms._162 /* assign V2 = |StructuralFeatureCS::default| */,
				ms._130 /* assign V1 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._098 /* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
				ms._053 /* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
				ms._171 /* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._192 /* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
				ms._208 /* assign V6 = |StructuralFeatureCS::ownedDefaultExpressions| */,
				ms._212 /* assign V7 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
				ms._218 /* assign V8 = 0 */,
				ms._224 /* assign V9 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*next-28-steps */,
				st._056 /* 1*'static' */,
				st._190 /* V00*'definition' */,
				st._024 /* 1*'attribute' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._228 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._246 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._141 /* 1*StructuralFeatureCS::default=SINGLE_QUOTED_STRING */,
				st._265 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._272 /* V04*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._281 /* V05*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._292 /* V06*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._296 /* V07*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._307 /* V08*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._311 /* V09*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _106 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._197 /* assign V5 = |ClassCS::ownedConstraints| */,
				ms._188 /* assign V4 = |ModelElementCS::ownedAnnotations| */,
				ms._157 /* assign V2 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._076 /* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._166 /* 1*next-15-steps */,
				st._191 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._243 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._264 /* V03*next-3-steps */,
				st._060 /* 1*'{' */,
				st._000 /* 1*'!serializable' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._270 /* V04*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._279 /* V05*ClassCS::ownedConstraints+=InvariantConstraintCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._19, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(41, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
		private @NonNull SerializationRule _107 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._157 /* assign V2 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._076 /* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._163 /* 1*next-12-steps */,
				st._191 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._243 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._264 /* V03*next-3-steps */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._19, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
		private @NonNull SerializationRule _108 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._157 /* assign V2 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._076 /* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				ms._166 /* assign V3 = (|DataTypeCS::isSerializable.'serializable'| > 0) */,
				ms._186 /* assign V4 = |DataTypeCS::isSerializable.'serializable'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._163 /* 1*next-12-steps */,
				st._191 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._243 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._264 /* V03*next-3-steps */,
				st._060 /* 1*'{' */,
				st._267 /* V04*'serializable' */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					ev._20),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._20, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._19, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _109 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._206 /* assign V6 = |ClassCS::ownedConstraints| */,
				ms._199 /* assign V5 = |ModelElementCS::ownedAnnotations| */,
				ms._157 /* assign V2 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._076 /* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				ms._166 /* assign V3 = (|DataTypeCS::isSerializable.'serializable'| > 0) */,
				ms._186 /* assign V4 = |DataTypeCS::isSerializable.'serializable'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._166 /* 1*next-15-steps */,
				st._191 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._243 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._264 /* V03*next-3-steps */,
				st._060 /* 1*'{' */,
				st._267 /* V04*'serializable' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._281 /* V05*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._288 /* V06*ClassCS::ownedConstraints+=InvariantConstraintCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					ev._20),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._20, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._19, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(41, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _110 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._197 /* assign V5 = |ClassCS::ownedConstraints| */,
				ms._188 /* assign V4 = |ModelElementCS::ownedAnnotations| */,
				ms._157 /* assign V2 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._076 /* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._165 /* 1*next-14-steps */,
				st._191 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._243 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._262 /* V03*next-2-steps */,
				st._060 /* 1*'{' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._270 /* V04*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._279 /* V05*ClassCS::ownedConstraints+=InvariantConstraintCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._19, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(41, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
		private @NonNull SerializationRule _111 = new SerializationRule(15,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._157 /* assign V2 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._076 /* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._162 /* 1*next-11-steps */,
				st._191 /* V00*'primitive' */,
				st._027 /* 1*'datatype' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._243 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._262 /* V03*next-2-steps */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._19, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::DetailCS : { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] }
		private @NonNull SerializationRule _112 = new SerializationRule(16,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._077 /* assign V0 = |DetailCS::values| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*next-3-steps */,
				st._108 /* 1*NamedElementCS::name=UnrestrictedName|SINGLE_QUOTED_STRING */,
				st._014 /* 1*'=' */,
				st._195 /* V00*DetailCS::values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */,
				ss._8 /* «? » + «value» + «? » */
			},
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.DETAIL_CS__VALUES,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// OCLinEcore::DocumentationCS : { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
		private @NonNull SerializationRule _113 = new SerializationRule(17,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._078 /* assign V0 = |DocumentationCS::value| */,
				ms._103 /* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				ms._148 /* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._161 /* 1*next-10-steps */,
				st._030 /* 1*'documentation' */,
				st._196 /* V00*DocumentationCS::value=SINGLE_QUOTED_STRING */,
				st._236 /* V01*next-6-steps */,
				st._003 /* 1*'(' */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=DetailCS */,
				st._244 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=DetailCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(16, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _114 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._197 /* assign V5 = |ClassCS::ownedConstraints| */,
				ms._187 /* assign V4 = |EnumerationCS::ownedLiterals| */,
				ms._176 /* assign V3 = |ModelElementCS::ownedAnnotations| */,
				ms._114 /* assign V1 = |ClassCS::instanceClassName| */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._166 /* 1*next-15-steps */,
				st._034 /* 1*'enum' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._227 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._249 /* V02*next-3-steps */,
				st._060 /* 1*'{' */,
				st._000 /* 1*'!serializable' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._255 /* V03*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._269 /* V04*EnumerationCS::ownedLiterals+=EnumerationLiteralCS */,
				st._279 /* V05*ClassCS::ownedConstraints+=InvariantConstraintCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(22, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(41, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _115 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._206 /* assign V6 = |ClassCS::ownedConstraints| */,
				ms._198 /* assign V5 = |EnumerationCS::ownedLiterals| */,
				ms._188 /* assign V4 = |ModelElementCS::ownedAnnotations| */,
				ms._114 /* assign V1 = |ClassCS::instanceClassName| */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._149 /* assign V2 = (|EnumerationCS::isSerializable.'serializable'| > 0) */,
				ms._175 /* assign V3 = |EnumerationCS::isSerializable.'serializable'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._166 /* 1*next-15-steps */,
				st._034 /* 1*'enum' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._227 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._249 /* V02*next-3-steps */,
				st._060 /* 1*'{' */,
				st._253 /* V03*'serializable' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._270 /* V04*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._280 /* V05*EnumerationCS::ownedLiterals+=EnumerationLiteralCS */,
				st._288 /* V06*ClassCS::ownedConstraints+=InvariantConstraintCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					ev._20)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._20, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(22, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(41, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
		private @NonNull SerializationRule _116 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._114 /* assign V1 = |ClassCS::instanceClassName| */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._161 /* 1*next-10-steps */,
				st._034 /* 1*'enum' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._227 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._245 /* V02*next-2-steps */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
		private @NonNull SerializationRule _117 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._114 /* assign V1 = |ClassCS::instanceClassName| */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._162 /* 1*next-11-steps */,
				st._034 /* 1*'enum' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._227 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._249 /* V02*next-3-steps */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _118 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._197 /* assign V5 = |ClassCS::ownedConstraints| */,
				ms._187 /* assign V4 = |EnumerationCS::ownedLiterals| */,
				ms._176 /* assign V3 = |ModelElementCS::ownedAnnotations| */,
				ms._114 /* assign V1 = |ClassCS::instanceClassName| */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._165 /* 1*next-14-steps */,
				st._034 /* 1*'enum' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._227 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._245 /* V02*next-2-steps */,
				st._060 /* 1*'{' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._255 /* V03*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._269 /* V04*EnumerationCS::ownedLiterals+=EnumerationLiteralCS */,
				st._279 /* V05*ClassCS::ownedConstraints+=InvariantConstraintCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(22, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(41, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
		private @NonNull SerializationRule _119 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._114 /* assign V1 = |ClassCS::instanceClassName| */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._149 /* assign V2 = (|EnumerationCS::isSerializable.'serializable'| > 0) */,
				ms._175 /* assign V3 = |EnumerationCS::isSerializable.'serializable'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._162 /* 1*next-11-steps */,
				st._034 /* 1*'enum' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._227 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._249 /* V02*next-3-steps */,
				st._060 /* 1*'{' */,
				st._253 /* V03*'serializable' */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					ev._20)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._20, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::EnumerationLiteralCS : { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
		private @NonNull SerializationRule _120 = new SerializationRule(22,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._160 /* assign V2 = |ModelElementCS::ownedAnnotations| */,
				ms._117 /* assign V1 = |EnumerationLiteralCS::value| */,
				ms._079 /* assign V0 = |EnumerationLiteralCS::literal| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._161 /* 1*next-10-steps */,
				st._105 /* 1*NamedElementCS::name=EnumerationLiteralName */,
				st._205 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._081 /* 1*EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING */,
				st._228 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._082 /* 1*EnumerationLiteralCS::value=SIGNED */,
				st._060 /* 1*'{' */,
				st._239 /* V02*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::EnumerationLiteralCS : { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
		private @NonNull SerializationRule _121 = new SerializationRule(22,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._117 /* assign V1 = |EnumerationLiteralCS::value| */,
				ms._079 /* assign V0 = |EnumerationLiteralCS::literal| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._186 /* 1*next-8-steps */,
				st._105 /* 1*NamedElementCS::name=EnumerationLiteralName */,
				st._205 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._081 /* 1*EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING */,
				st._228 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._082 /* 1*EnumerationLiteralCS::value=SIGNED */,
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
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// OCLinEcore::EnumerationLiteralCS : { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
		private @NonNull SerializationRule _122 = new SerializationRule(22,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._160 /* assign V2 = |ModelElementCS::ownedAnnotations| */,
				ms._117 /* assign V1 = |EnumerationLiteralCS::value| */,
				ms._079 /* assign V0 = |EnumerationLiteralCS::literal| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._162 /* 1*next-11-steps */,
				st._044 /* 1*'literal' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._206 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._081 /* 1*EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING */,
				st._229 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._082 /* 1*EnumerationLiteralCS::value=SIGNED */,
				st._060 /* 1*'{' */,
				st._239 /* V02*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::EnumerationLiteralCS : { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
		private @NonNull SerializationRule _123 = new SerializationRule(22,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._117 /* assign V1 = |EnumerationLiteralCS::value| */,
				ms._079 /* assign V0 = |EnumerationLiteralCS::literal| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._187 /* 1*next-9-steps */,
				st._044 /* 1*'literal' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._206 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._081 /* 1*EnumerationLiteralCS::literal=SINGLE_QUOTED_STRING */,
				st._229 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._082 /* 1*EnumerationLiteralCS::value=SIGNED */,
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
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// OCLinEcore::ImplicitOppositeCS : { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] }
		private @NonNull SerializationRule _124 = new SerializationRule(37,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._051 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._069 /* assign V0 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
				ms._131 /* assign V1 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._187 /* 1*next-9-steps */,
				st._048 /* 1*'opposite' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._211 /* V00*next-4-steps */,
				st._060 /* 1*'{' */,
				st._225 /* V01*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._03)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._03, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLinEcore::ImportCS : { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' }
		private @NonNull SerializationRule _125 = new SerializationRule(38,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._236 /* check-rule basecs::ImportCS.ownedPathName : 123 */,
				ms._118 /* assign V1 = |ImportCS::isAll.'::*'| */,
				ms._017 /* assert (|ImportCS::ownedPathName| - 1) == 0 */,
				ms._085 /* assign V0 = |NamedElementCS::name| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._037 /* 1*'import' */,
				st._205 /* V00*next-2-steps */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._009 /* 1*':' */,
				st._089 /* 1*ImportCS::ownedPathName=URIPathNameCS */,
				st._216 /* V01*'::*' */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
					ev._06)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					iv._64) /* URIPathNameCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._06, MultiplicativeCardinality.ZERO_OR_ONE)
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
					new RuleIndex_MultiplicativeCardinality(123, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLinEcore::InvariantConstraintCS : { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' }
		private @NonNull SerializationRule _126 = new SerializationRule(41,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
				ms._120 /* assign V1 = |NamedElementCS::name| */,
				ms._007 /* assert (|ConstraintCS::stereotype.'invariant'| - 1) == 0 */,
				ms._089 /* assign V0 = |OCLinEcoreConstraintCS::isCallable.'callable'| */,
				ms._158 /* assign V2 = |ConstraintCS::ownedMessageSpecification| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._187 /* 1*next-9-steps */,
				st._189 /* V00*'callable' */,
				st._041 /* 1*'invariant' */,
				st._235 /* V01*next-5-steps */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._248 /* V02*next-3-steps */,
				st._003 /* 1*'(' */,
				st._078 /* 1*ConstraintCS::ownedMessageSpecification=SpecificationCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._16),
				new EAttribute_EnumerationValues(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					ev._12)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._36) /* SpecificationCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._16, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._12, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::InvariantConstraintCS : { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
		private @NonNull SerializationRule _127 = new SerializationRule(41,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._234 /* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
				ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
				ms._174 /* assign V3 = |ConstraintCS::ownedSpecification| */,
				ms._120 /* assign V1 = |NamedElementCS::name| */,
				ms._007 /* assert (|ConstraintCS::stereotype.'invariant'| - 1) == 0 */,
				ms._089 /* assign V0 = |OCLinEcoreConstraintCS::isCallable.'callable'| */,
				ms._158 /* assign V2 = |ConstraintCS::ownedMessageSpecification| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._162 /* 1*next-11-steps */,
				st._189 /* V00*'callable' */,
				st._041 /* 1*'invariant' */,
				st._235 /* V01*next-5-steps */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._248 /* V02*next-3-steps */,
				st._003 /* 1*'(' */,
				st._078 /* 1*ConstraintCS::ownedMessageSpecification=SpecificationCS */,
				st._004 /* 1*')' */,
				st._009 /* 1*':' */,
				st._254 /* V03*ConstraintCS::ownedSpecification=SpecificationCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._16),
				new EAttribute_EnumerationValues(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					ev._12)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._36) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._36) /* SpecificationCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._16, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._12, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
	}
	private class _SerializationRules2
	{
		// OCLinEcore::ModelElementRefCS : { 'reference' ownedPathName=PathNameCS ';' }
		private @NonNull SerializationRule _128 = new SerializationRule(54,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._238 /* check-rule basecs::ModelElementRefCS.ownedPathName : 73 */,
				ms._026 /* assert (|ModelElementRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*next-3-steps */,
				st._054 /* 1*'reference' */,
				st._100 /* 1*ModelElementRefCS::ownedPathName=PathNameCS */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(73, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLinEcore::OperationCS : { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _129 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._190 /* assign V4 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._098 /* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
				ms._053 /* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
				ms._194 /* assign V5 = (|OperationCS::ownedExceptions| > 0) */,
				ms._202 /* assign V6 = (|OperationCS::ownedExceptions| - 1) */,
				ms._151 /* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				ms._168 /* assign V3 = (|OperationCS::ownedParameters| - 1) */,
				ms._213 /* assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._222 /* assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._175 /* 1*next-27-steps */,
				st._056 /* 1*'static' */,
				st._190 /* V00*'definition' */,
				st._047 /* 1*'operation' */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._251 /* V02*next-4-steps */,
				st._116 /* 1*OperationCS::ownedParameters+=ParameterCS */,
				st._259 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._116 /* 1*OperationCS::ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._275 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._286 /* V05*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._115 /* 1*OperationCS::ownedExceptions+=TypedRefCS */,
				st._291 /* V06*next-2-steps */,
				st._007 /* 1*',' */,
				st._115 /* 1*OperationCS::ownedExceptions+=TypedRefCS */,
				st._300 /* V07*next-4-steps */,
				st._060 /* 1*'{' */,
				st._305 /* V08*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._02)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._02, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(72, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(117, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::OperationCS : { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _130 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._179 /* assign V3 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._205 /* assign V6 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._216 /* assign V7 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
				ms._182 /* assign V4 = (|OperationCS::ownedExceptions| > 0) */,
				ms._193 /* assign V5 = (|OperationCS::ownedExceptions| - 1) */,
				ms._108 /* assign V1 = (|OperationCS::ownedParameters| > 0) */,
				ms._150 /* assign V2 = (|OperationCS::ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*next-25-steps */,
				st._047 /* 1*'operation' */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._234 /* V01*next-4-steps */,
				st._116 /* 1*OperationCS::ownedParameters+=ParameterCS */,
				st._245 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._116 /* 1*OperationCS::ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._260 /* V03*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._277 /* V04*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._115 /* 1*OperationCS::ownedExceptions+=TypedRefCS */,
				st._283 /* V05*next-2-steps */,
				st._007 /* 1*',' */,
				st._115 /* 1*OperationCS::ownedExceptions+=TypedRefCS */,
				st._293 /* V06*next-4-steps */,
				st._060 /* 1*'{' */,
				st._297 /* V07*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._02, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(72, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(117, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::OperationCS : { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
		private @NonNull SerializationRule _131 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
				ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._144 /* assign V12 = |OperationCS::ownedPostconditions| */,
				ms._226 /* assign V9 = |OperationCS::ownedPreconditions| */,
				ms._219 /* assign V8 = |ModelElementCS::ownedAnnotations| */,
				ms._179 /* assign V3 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._134 /* assign V10 = (|OperationCS::ownedBodyExpressions| > 0) */,
				ms._141 /* assign V11 = |OperationCS::ownedBodyExpressions| */,
				ms._205 /* assign V6 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._216 /* assign V7 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
				ms._182 /* assign V4 = (|OperationCS::ownedExceptions| > 0) */,
				ms._193 /* assign V5 = (|OperationCS::ownedExceptions| - 1) */,
				ms._108 /* assign V1 = (|OperationCS::ownedParameters| > 0) */,
				ms._150 /* assign V2 = (|OperationCS::ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._178 /* 1*next-34-steps */,
				st._047 /* 1*'operation' */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._234 /* V01*next-4-steps */,
				st._116 /* 1*OperationCS::ownedParameters+=ParameterCS */,
				st._245 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._116 /* 1*OperationCS::ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._260 /* V03*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._277 /* V04*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._115 /* 1*OperationCS::ownedExceptions+=TypedRefCS */,
				st._283 /* V05*next-2-steps */,
				st._007 /* 1*',' */,
				st._115 /* 1*OperationCS::ownedExceptions+=TypedRefCS */,
				st._293 /* V06*next-4-steps */,
				st._060 /* 1*'{' */,
				st._297 /* V07*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._302 /* V08*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._310 /* V09*OperationCS::ownedPreconditions+=PreconditionConstraintCS */,
				st._318 /* V10*next-4-steps */,
				st._025 /* 1*'body' */,
				st._009 /* 1*':' */,
				st._319 /* V11*OperationCS::ownedBodyExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._324 /* V12*OperationCS::ownedPostconditions+=PostconditionConstraintCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._30) /* PreconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._29) /* PostconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._02, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(76, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(72, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(75, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(117, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::OperationCS : { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
		private @NonNull SerializationRule _132 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
				ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._146 /* assign V13 = |OperationCS::ownedPostconditions| */,
				ms._138 /* assign V10 = |OperationCS::ownedPreconditions| */,
				ms._225 /* assign V9 = |ModelElementCS::ownedAnnotations| */,
				ms._190 /* assign V4 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._099 /* assign V0 = |TypedElementCS::qualifiers.'static'| */,
				ms._052 /* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
				ms._139 /* assign V11 = (|OperationCS::ownedBodyExpressions| > 0) */,
				ms._143 /* assign V12 = |OperationCS::ownedBodyExpressions| */,
				ms._194 /* assign V5 = (|OperationCS::ownedExceptions| > 0) */,
				ms._202 /* assign V6 = (|OperationCS::ownedExceptions| - 1) */,
				ms._151 /* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				ms._168 /* assign V3 = (|OperationCS::ownedParameters| - 1) */,
				ms._213 /* assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._222 /* assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._179 /* 1*next-36-steps */,
				st._028 /* 1*'definition' */,
				st._192 /* V00*'static' */,
				st._047 /* 1*'operation' */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._251 /* V02*next-4-steps */,
				st._116 /* 1*OperationCS::ownedParameters+=ParameterCS */,
				st._259 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._116 /* 1*OperationCS::ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._275 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._286 /* V05*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._115 /* 1*OperationCS::ownedExceptions+=TypedRefCS */,
				st._291 /* V06*next-2-steps */,
				st._007 /* 1*',' */,
				st._115 /* 1*OperationCS::ownedExceptions+=TypedRefCS */,
				st._300 /* V07*next-4-steps */,
				st._060 /* 1*'{' */,
				st._305 /* V08*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._309 /* V09*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._315 /* V10*OperationCS::ownedPreconditions+=PreconditionConstraintCS */,
				st._321 /* V11*next-4-steps */,
				st._025 /* 1*'body' */,
				st._009 /* 1*':' */,
				st._323 /* V12*OperationCS::ownedBodyExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._327 /* V13*OperationCS::ownedPostconditions+=PostconditionConstraintCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._02)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._30) /* PreconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._29) /* PostconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._02, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(76, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(72, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(75, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(117, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::OperationCS : { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _133 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._190 /* assign V4 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._099 /* assign V0 = |TypedElementCS::qualifiers.'static'| */,
				ms._052 /* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
				ms._194 /* assign V5 = (|OperationCS::ownedExceptions| > 0) */,
				ms._202 /* assign V6 = (|OperationCS::ownedExceptions| - 1) */,
				ms._151 /* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				ms._168 /* assign V3 = (|OperationCS::ownedParameters| - 1) */,
				ms._213 /* assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._222 /* assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._175 /* 1*next-27-steps */,
				st._028 /* 1*'definition' */,
				st._192 /* V00*'static' */,
				st._047 /* 1*'operation' */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._251 /* V02*next-4-steps */,
				st._116 /* 1*OperationCS::ownedParameters+=ParameterCS */,
				st._259 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._116 /* 1*OperationCS::ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._275 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._286 /* V05*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._115 /* 1*OperationCS::ownedExceptions+=TypedRefCS */,
				st._291 /* V06*next-2-steps */,
				st._007 /* 1*',' */,
				st._115 /* 1*OperationCS::ownedExceptions+=TypedRefCS */,
				st._300 /* V07*next-4-steps */,
				st._060 /* 1*'{' */,
				st._305 /* V08*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._02)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._02, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(72, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(117, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::OperationCS : { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
		private @NonNull SerializationRule _134 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
				ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._146 /* assign V13 = |OperationCS::ownedPostconditions| */,
				ms._138 /* assign V10 = |OperationCS::ownedPreconditions| */,
				ms._225 /* assign V9 = |ModelElementCS::ownedAnnotations| */,
				ms._190 /* assign V4 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._098 /* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
				ms._053 /* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
				ms._139 /* assign V11 = (|OperationCS::ownedBodyExpressions| > 0) */,
				ms._143 /* assign V12 = |OperationCS::ownedBodyExpressions| */,
				ms._194 /* assign V5 = (|OperationCS::ownedExceptions| > 0) */,
				ms._202 /* assign V6 = (|OperationCS::ownedExceptions| - 1) */,
				ms._151 /* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				ms._168 /* assign V3 = (|OperationCS::ownedParameters| - 1) */,
				ms._213 /* assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
				ms._222 /* assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._179 /* 1*next-36-steps */,
				st._056 /* 1*'static' */,
				st._190 /* V00*'definition' */,
				st._047 /* 1*'operation' */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._003 /* 1*'(' */,
				st._251 /* V02*next-4-steps */,
				st._116 /* 1*OperationCS::ownedParameters+=ParameterCS */,
				st._259 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._116 /* 1*OperationCS::ownedParameters+=ParameterCS */,
				st._004 /* 1*')' */,
				st._275 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._286 /* V05*next-5-steps */,
				st._059 /* 1*'throws' */,
				st._115 /* 1*OperationCS::ownedExceptions+=TypedRefCS */,
				st._291 /* V06*next-2-steps */,
				st._007 /* 1*',' */,
				st._115 /* 1*OperationCS::ownedExceptions+=TypedRefCS */,
				st._300 /* V07*next-4-steps */,
				st._060 /* 1*'{' */,
				st._305 /* V08*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._309 /* V09*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._315 /* V10*OperationCS::ownedPreconditions+=PreconditionConstraintCS */,
				st._321 /* V11*next-4-steps */,
				st._025 /* 1*'body' */,
				st._009 /* 1*':' */,
				st._323 /* V12*OperationCS::ownedBodyExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._327 /* V13*OperationCS::ownedPostconditions+=PostconditionConstraintCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._02)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._30) /* PreconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._25) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._29) /* PostconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._59) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._02, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(76, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(72, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(75, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(117, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::PackageCS : { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' }
		private @NonNull SerializationRule _135 = new SerializationRule(71,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._123 /* assign V1 = |PackageCS::nsURI| */,
				ms._090 /* assign V0 = |PackageCS::nsPrefix| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._187 /* 1*next-9-steps */,
				st._049 /* 1*'package' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._206 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._120 /* 1*PackageCS::nsPrefix=UnrestrictedName */,
				st._229 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._121 /* 1*PackageCS::nsURI=URI */,
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
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.PACKAGE_CS__NS_URI,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// OCLinEcore::PackageCS : { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' }
		private @NonNull SerializationRule _136 = new SerializationRule(71,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._245 /* check-rule basecs::PackageOwnerCS.ownedPackages : 71 */,
				ms._244 /* check-rule basecs::PackageCS.ownedClasses : 6 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._189 /* assign V4 = |PackageCS::ownedClasses| */,
				ms._177 /* assign V3 = |PackageOwnerCS::ownedPackages| */,
				ms._160 /* assign V2 = |ModelElementCS::ownedAnnotations| */,
				ms._123 /* assign V1 = |PackageCS::nsURI| */,
				ms._090 /* assign V0 = |PackageCS::nsPrefix| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._164 /* 1*next-13-steps */,
				st._049 /* 1*'package' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._206 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._120 /* 1*PackageCS::nsPrefix=UnrestrictedName */,
				st._229 /* V01*next-2-steps */,
				st._014 /* 1*'=' */,
				st._121 /* 1*PackageCS::nsURI=URI */,
				st._060 /* 1*'{' */,
				st._239 /* V02*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._256 /* V03*PackageOwnerCS::ownedPackages+=PackageCS */,
				st._271 /* V04*PackageCS::ownedClasses+=ClassCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					iv._24) /* PackageCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					iv._1) /* ClassCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.PACKAGE_CS__NS_URI,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(71, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(6, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::ParameterCS : { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] }
		private @NonNull SerializationRule _137 = new SerializationRule(72,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._097 /* assign V0 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._167 /* assign V3 = (|ModelElementCS::ownedAnnotations| > 0) */,
				ms._188 /* assign V4 = |ModelElementCS::ownedAnnotations| */,
				ms._112 /* assign V1 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
				ms._164 /* assign V2 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._164 /* 1*next-13-steps */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._205 /* V00*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._234 /* V01*next-4-steps */,
				st._060 /* 1*'{' */,
				st._242 /* V02*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
				st._063 /* 1*'}' */,
				st._263 /* V03*next-3-steps */,
				st._060 /* 1*'{' */,
				st._270 /* V04*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._03)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._03, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::PostconditionConstraintCS : { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
		private @NonNull SerializationRule _138 = new SerializationRule(75,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._234 /* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
				ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
				ms._159 /* assign V2 = |ConstraintCS::ownedSpecification| */,
				ms._085 /* assign V0 = |NamedElementCS::name| */,
				ms._008 /* assert (|ConstraintCS::stereotype.'postcondition'| - 1) == 0 */,
				ms._116 /* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._161 /* 1*next-10-steps */,
				st._050 /* 1*'postcondition' */,
				st._212 /* V00*next-5-steps */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._232 /* V01*next-3-steps */,
				st._003 /* 1*'(' */,
				st._078 /* 1*ConstraintCS::ownedMessageSpecification=SpecificationCS */,
				st._004 /* 1*')' */,
				st._009 /* 1*':' */,
				st._238 /* V02*ConstraintCS::ownedSpecification=SpecificationCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._17)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._36) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._36) /* SpecificationCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._17, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::PreconditionConstraintCS : { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
		private @NonNull SerializationRule _139 = new SerializationRule(76,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._234 /* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
				ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
				ms._159 /* assign V2 = |ConstraintCS::ownedSpecification| */,
				ms._085 /* assign V0 = |NamedElementCS::name| */,
				ms._009 /* assert (|ConstraintCS::stereotype.'precondition'| - 1) == 0 */,
				ms._116 /* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._161 /* 1*next-10-steps */,
				st._052 /* 1*'precondition' */,
				st._212 /* V00*next-5-steps */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._232 /* V01*next-3-steps */,
				st._003 /* 1*'(' */,
				st._078 /* 1*ConstraintCS::ownedMessageSpecification=SpecificationCS */,
				st._004 /* 1*')' */,
				st._009 /* 1*':' */,
				st._238 /* V02*ConstraintCS::ownedSpecification=SpecificationCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._18)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._36) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._36) /* SpecificationCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._18, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
		private @NonNull SerializationRule _140 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
				ms._147 /* assign V13 = |ReferenceCS::ownedImplicitOpposites| */,
				ms._207 /* assign V6 = |ModelElementCS::ownedAnnotations| */,
				ms._178 /* assign V3 = |StructuralFeatureCS::default| */,
				ms._163 /* assign V2 = |TypedElementCS::ownedType| */,
				ms._124 /* assign V1 = |ReferenceCS::referredOpposite| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._099 /* assign V0 = |TypedElementCS::qualifiers.'static'| */,
				ms._052 /* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
				ms._211 /* assign V7 = (|ReferenceCS::referredKeys| > 0) */,
				ms._217 /* assign V8 = (|ReferenceCS::referredKeys| - 1) */,
				ms._183 /* assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._201 /* assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
				ms._227 /* assign V9 = |StructuralFeatureCS::ownedDefaultExpressions| */,
				ms._135 /* assign V10 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
				ms._140 /* assign V11 = 0 */,
				ms._142 /* assign V12 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._182 /* 1*next-41-steps */,
				st._028 /* 1*'definition' */,
				st._192 /* V00*'static' */,
				st._053 /* 1*'property' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._228 /* V01*next-2-steps */,
				st._001 /* 1*'#' */,
				st._134 /* 1*ReferenceCS::referredOpposite=UnrestrictedName */,
				st._246 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._260 /* V03*next-2-steps */,
				st._014 /* 1*'=' */,
				st._141 /* 1*StructuralFeatureCS::default=SINGLE_QUOTED_STRING */,
				st._276 /* V04*next-4-steps */,
				st._060 /* 1*'{' */,
				st._282 /* V05*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._289 /* V06*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._301 /* V07*next-6-steps */,
				st._042 /* 1*'key' */,
				st._133 /* 1*ReferenceCS::referredKeys+=UnrestrictedName */,
				st._306 /* V08*next-2-steps */,
				st._007 /* 1*',' */,
				st._132 /* 1*ReferenceCS::referredKeys+=UnrestrictedName */,
				st._011 /* 1*';' */,
				st._313 /* V09*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._316 /* V10*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._322 /* V11*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._325 /* V12*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._328 /* V13*next-2-steps */,
				st._131 /* 1*ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._00)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					iv._11) /* ImplicitOppositeCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._00, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(37, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
		private @NonNull SerializationRule _141 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
				ms._147 /* assign V13 = |ReferenceCS::ownedImplicitOpposites| */,
				ms._207 /* assign V6 = |ModelElementCS::ownedAnnotations| */,
				ms._178 /* assign V3 = |StructuralFeatureCS::default| */,
				ms._163 /* assign V2 = |TypedElementCS::ownedType| */,
				ms._124 /* assign V1 = |ReferenceCS::referredOpposite| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._098 /* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
				ms._053 /* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
				ms._211 /* assign V7 = (|ReferenceCS::referredKeys| > 0) */,
				ms._217 /* assign V8 = (|ReferenceCS::referredKeys| - 1) */,
				ms._183 /* assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._201 /* assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
				ms._227 /* assign V9 = |StructuralFeatureCS::ownedDefaultExpressions| */,
				ms._135 /* assign V10 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
				ms._140 /* assign V11 = 0 */,
				ms._142 /* assign V12 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._182 /* 1*next-41-steps */,
				st._056 /* 1*'static' */,
				st._190 /* V00*'definition' */,
				st._053 /* 1*'property' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._228 /* V01*next-2-steps */,
				st._001 /* 1*'#' */,
				st._134 /* 1*ReferenceCS::referredOpposite=UnrestrictedName */,
				st._246 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._260 /* V03*next-2-steps */,
				st._014 /* 1*'=' */,
				st._141 /* 1*StructuralFeatureCS::default=SINGLE_QUOTED_STRING */,
				st._276 /* V04*next-4-steps */,
				st._060 /* 1*'{' */,
				st._282 /* V05*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._289 /* V06*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._301 /* V07*next-6-steps */,
				st._042 /* 1*'key' */,
				st._133 /* 1*ReferenceCS::referredKeys+=UnrestrictedName */,
				st._306 /* V08*next-2-steps */,
				st._007 /* 1*',' */,
				st._132 /* 1*ReferenceCS::referredKeys+=UnrestrictedName */,
				st._011 /* 1*';' */,
				st._313 /* V09*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._316 /* V10*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._322 /* V11*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._325 /* V12*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._328 /* V13*next-2-steps */,
				st._131 /* 1*ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._00)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					iv._11) /* ImplicitOppositeCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._00, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(37, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _142 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._178 /* assign V3 = |StructuralFeatureCS::default| */,
				ms._163 /* assign V2 = |TypedElementCS::ownedType| */,
				ms._124 /* assign V1 = |ReferenceCS::referredOpposite| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._099 /* assign V0 = |TypedElementCS::qualifiers.'static'| */,
				ms._052 /* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
				ms._183 /* assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._201 /* assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._170 /* 1*next-19-steps */,
				st._028 /* 1*'definition' */,
				st._192 /* V00*'static' */,
				st._053 /* 1*'property' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._228 /* V01*next-2-steps */,
				st._001 /* 1*'#' */,
				st._134 /* 1*ReferenceCS::referredOpposite=UnrestrictedName */,
				st._246 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._260 /* V03*next-2-steps */,
				st._014 /* 1*'=' */,
				st._141 /* 1*StructuralFeatureCS::default=SINGLE_QUOTED_STRING */,
				st._276 /* V04*next-4-steps */,
				st._060 /* 1*'{' */,
				st._282 /* V05*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._00)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._00, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::ReferenceCS : { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
		private @NonNull SerializationRule _143 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
				ms._145 /* assign V12 = |ReferenceCS::ownedImplicitOpposites| */,
				ms._199 /* assign V5 = |ModelElementCS::ownedAnnotations| */,
				ms._162 /* assign V2 = |StructuralFeatureCS::default| */,
				ms._130 /* assign V1 = |TypedElementCS::ownedType| */,
				ms._092 /* assign V0 = |ReferenceCS::referredOpposite| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._203 /* assign V6 = (|ReferenceCS::referredKeys| > 0) */,
				ms._210 /* assign V7 = (|ReferenceCS::referredKeys| - 1) */,
				ms._170 /* assign V3 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._191 /* assign V4 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
				ms._220 /* assign V8 = |StructuralFeatureCS::ownedDefaultExpressions| */,
				ms._223 /* assign V9 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0) */,
				ms._136 /* assign V10 = 0 */,
				ms._140 /* assign V11 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._180 /* 1*next-39-steps */,
				st._053 /* 1*'property' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._206 /* V00*next-2-steps */,
				st._001 /* 1*'#' */,
				st._134 /* 1*ReferenceCS::referredOpposite=UnrestrictedName */,
				st._229 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._247 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._141 /* 1*StructuralFeatureCS::default=SINGLE_QUOTED_STRING */,
				st._266 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._273 /* V04*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._281 /* V05*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._294 /* V06*next-6-steps */,
				st._042 /* 1*'key' */,
				st._133 /* 1*ReferenceCS::referredKeys+=UnrestrictedName */,
				st._298 /* V07*next-2-steps */,
				st._007 /* 1*',' */,
				st._132 /* 1*ReferenceCS::referredKeys+=UnrestrictedName */,
				st._011 /* 1*';' */,
				st._308 /* V08*next-4-steps */,
				st._039 /* 1*'initial' */,
				st._009 /* 1*':' */,
				st._311 /* V09*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._317 /* V10*next-4-steps */,
				st._029 /* 1*'derivation' */,
				st._009 /* 1*':' */,
				st._320 /* V11*StructuralFeatureCS::ownedDefaultExpressions+=SpecificationCS */,
				st._011 /* 1*';' */,
				st._326 /* V12*next-2-steps */,
				st._131 /* 1*ReferenceCS::ownedImplicitOpposites+=ImplicitOppositeCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._00)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._36) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					iv._11) /* ImplicitOppositeCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._00, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(37, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::ReferenceCS : { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _144 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._162 /* assign V2 = |StructuralFeatureCS::default| */,
				ms._130 /* assign V1 = |TypedElementCS::ownedType| */,
				ms._092 /* assign V0 = |ReferenceCS::referredOpposite| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._170 /* assign V3 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._191 /* assign V4 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._168 /* 1*next-17-steps */,
				st._053 /* 1*'property' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._206 /* V00*next-2-steps */,
				st._001 /* 1*'#' */,
				st._134 /* 1*ReferenceCS::referredOpposite=UnrestrictedName */,
				st._229 /* V01*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._247 /* V02*next-2-steps */,
				st._014 /* 1*'=' */,
				st._141 /* 1*StructuralFeatureCS::default=SINGLE_QUOTED_STRING */,
				st._266 /* V03*next-4-steps */,
				st._060 /* 1*'{' */,
				st._273 /* V04*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._00)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._00, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _145 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._178 /* assign V3 = |StructuralFeatureCS::default| */,
				ms._163 /* assign V2 = |TypedElementCS::ownedType| */,
				ms._124 /* assign V1 = |ReferenceCS::referredOpposite| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._098 /* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
				ms._053 /* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
				ms._183 /* assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._201 /* assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._170 /* 1*next-19-steps */,
				st._056 /* 1*'static' */,
				st._190 /* V00*'definition' */,
				st._053 /* 1*'property' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._228 /* V01*next-2-steps */,
				st._001 /* 1*'#' */,
				st._134 /* 1*ReferenceCS::referredOpposite=UnrestrictedName */,
				st._246 /* V02*next-2-steps */,
				st._009 /* 1*':' */,
				st._154 /* 1*TypedElementCS::ownedType=TypedMultiplicityRefCS */,
				st._260 /* V03*next-2-steps */,
				st._014 /* 1*'=' */,
				st._141 /* 1*StructuralFeatureCS::default=SINGLE_QUOTED_STRING */,
				st._276 /* V04*next-4-steps */,
				st._060 /* 1*'{' */,
				st._282 /* V05*next-1-steps */,
				st._155 /* 1*TypedElementCS::qualifiers */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._00)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._58) /* TypedMultiplicityRefCS */
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._00, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::SpecificationCS : exprString=UNQUOTED_STRING
		private @NonNull SerializationRule _146 = new SerializationRule(92,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._046 /* assert (|SpecificationCS::exprString| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._139 /* 1*SpecificationCS::exprString=UNQUOTED_STRING */
			},
			new @NonNull Segment @NonNull [] [] {
				ss._8 /* «? » + «value» + «? » */
			},
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
		// OCLinEcore::SpecificationCS : ownedExpression=ExpCS
		private @NonNull SerializationRule _147 = new SerializationRule(92,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._283 /* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 30 */,
				ms._011 /* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._083 /* 1*ExpSpecificationCS::ownedExpression=ExpCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(30, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLinEcore::StructuredClassCS : { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _148 = new SerializationRule(97,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._254 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._253 /* check-rule basecs::StructuredClassCS.ownedProperties : 96 */,
				ms._252 /* check-rule basecs::StructuredClassCS.ownedOperations : 70 */,
				ms._137 /* assign V10 = |ClassCS::ownedConstraints| */,
				ms._228 /* assign V9 = |StructuredClassCS::ownedProperties| */,
				ms._221 /* assign V8 = |StructuredClassCS::ownedOperations| */,
				ms._215 /* assign V7 = |ModelElementCS::ownedAnnotations| */,
				ms._185 /* assign V4 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._094 /* assign V0 = |StructuredClassCS::isAbstract.'abstract'| */,
				ms._195 /* assign V5 = (|StructuredClassCS::isInterface.'interface'| > 0) */,
				ms._209 /* assign V6 = |StructuredClassCS::isInterface.'interface'| */,
				ms._152 /* assign V2 = (|StructuredClassCS::ownedSuperTypes| > 0) */,
				ms._169 /* assign V3 = (|StructuredClassCS::ownedSuperTypes| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*next-23-steps */,
				st._188 /* V00*'abstract' */,
				st._026 /* 1*'class' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._252 /* V02*next-5-steps */,
				st._035 /* 1*'extends' */,
				st._142 /* 1*StructuredClassCS::ownedSuperTypes+=TypedRefCS */,
				st._262 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._142 /* 1*StructuredClassCS::ownedSuperTypes+=TypedRefCS */,
				st._274 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._284 /* V05*next-3-steps */,
				st._060 /* 1*'{' */,
				st._287 /* V06*'interface' */,
				st._063 /* 1*'}' */,
				st._060 /* 1*'{' */,
				st._295 /* V07*ModelElementCS::ownedAnnotations+=AnnotationElementCS */,
				st._304 /* V08*StructuredClassCS::ownedOperations+=OperationCS */,
				st._312 /* V09*StructuredClassCS::ownedProperties+=StructuralFeatureCS */,
				st._314 /* V10*ClassCS::ownedConstraints+=InvariantConstraintCS */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					ev._15),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					ev._11)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					iv._59) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					iv._39) /* StructuralFeatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					iv._23) /* OperationCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._15, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._11, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(41, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(117, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(2, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(96, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(70, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::StructuredClassCS : { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' }
		private @NonNull SerializationRule _149 = new SerializationRule(97,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._254 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._185 /* assign V4 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._094 /* assign V0 = |StructuredClassCS::isAbstract.'abstract'| */,
				ms._195 /* assign V5 = (|StructuredClassCS::isInterface.'interface'| > 0) */,
				ms._209 /* assign V6 = |StructuredClassCS::isInterface.'interface'| */,
				ms._152 /* assign V2 = (|StructuredClassCS::ownedSuperTypes| > 0) */,
				ms._169 /* assign V3 = (|StructuredClassCS::ownedSuperTypes| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._169 /* 1*next-18-steps */,
				st._188 /* V00*'abstract' */,
				st._026 /* 1*'class' */,
				st._107 /* 1*NamedElementCS::name=UnrestrictedName */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=TemplateSignatureCS */,
				st._252 /* V02*next-5-steps */,
				st._035 /* 1*'extends' */,
				st._142 /* 1*StructuredClassCS::ownedSuperTypes+=TypedRefCS */,
				st._262 /* V03*next-2-steps */,
				st._007 /* 1*',' */,
				st._142 /* 1*StructuredClassCS::ownedSuperTypes+=TypedRefCS */,
				st._274 /* V04*next-2-steps */,
				st._009 /* 1*':' */,
				st._067 /* 1*ClassCS::instanceClassName=SINGLE_QUOTED_STRING */,
				st._284 /* V05*next-3-steps */,
				st._060 /* 1*'{' */,
				st._287 /* V06*'interface' */,
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					ev._15),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					ev._11)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					iv._59) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._46) /* TemplateSignatureCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._15, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._11, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(117, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(101, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::SysMLCS : { 'sysml' ownedDetails+=DetailCS ';' }
		private @NonNull SerializationRule _150 = new SerializationRule(98,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._001 /* assert (|AnnotationElementCS::ownedDetails| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*next-3-steps */,
				st._057 /* 1*'sysml' */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=DetailCS */,
				st._011 /* 1*';' */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				ss._4 /* «! » + «value» + «?\n» */
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(16, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLinEcore::SysMLCS : { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' }
		private @NonNull SerializationRule _151 = new SerializationRule(98,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._072 /* assign V0 = |AnnotationElementCS::ownedDetails| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._184 /* 1*next-6-steps */,
				st._057 /* 1*'sysml' */,
				st._060 /* 1*'{' */,
				st._206 /* V00*next-2-steps */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=DetailCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(16, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::TemplateSignatureCS : { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' }
		private @NonNull SerializationRule _152 = new SerializationRule(101,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._258 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 114 */,
				ms._065 /* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._184 /* 1*next-6-steps */,
				st._003 /* 1*'(' */,
				st._145 /* 1*TemplateSignatureCS::ownedParameters+=TypeParameterCS */,
				st._206 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._145 /* 1*TemplateSignatureCS::ownedParameters+=TypeParameterCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._56) /* TypeParameterCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(114, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// OCLinEcore::TemplateSignatureCS : { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' }
		private @NonNull SerializationRule _153 = new SerializationRule(101,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._258 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 114 */,
				ms._065 /* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._184 /* 1*next-6-steps */,
				st._012 /* 1*'<' */,
				st._145 /* 1*TemplateSignatureCS::ownedParameters+=TypeParameterCS */,
				st._206 /* V00*next-2-steps */,
				st._007 /* 1*',' */,
				st._145 /* 1*TemplateSignatureCS::ownedParameters+=TypeParameterCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._56) /* TypeParameterCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(114, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// OCLinEcore::TopLevelCS : { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] }
		private @NonNull SerializationRule _154 = new SerializationRule(102,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._245 /* check-rule basecs::PackageOwnerCS.ownedPackages : 71 */,
				ms._250 /* check-rule basecs::RootCS.ownedImports : 38 */,
				ms._161 /* assign V2 = |PackageOwnerCS::ownedPackages| */,
				ms._125 /* assign V1 = |RootCS::ownedImports| */,
				ms._070 /* assign V0 = 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._204 /* V00*next-1-steps */,
				st._045 /* 1*'module' */,
				st._220 /* V01*RootCS::ownedImports+=ImportCS */,
				st._240 /* V02*PackageOwnerCS::ownedPackages+=PackageCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				ss._8 /* «? » + «value» + «? » */,
				null,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					iv._24) /* PackageCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					iv._12) /* ImportCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(71, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(38, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _155 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._100 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._054 /* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
				ms._055 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._183 /* 1*next-5-steps */,
				st._157 /* 1*TypedTypeRefCS::ownedPathName=PathNameCS */,
				st._003 /* 1*'(' */,
				st._156 /* 1*TypedTypeRefCS::ownedBinding=TemplateBindingCS */,
				st._004 /* 1*')' */,
				st._203 /* V00*TypedRefCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._44) /* TemplateBindingCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(73, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(99, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _156 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._100 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._043 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._171 /* 1*next-2-steps */,
				st._130 /* 1*PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier */,
				st._203 /* V00*TypedRefCS::ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				ss._8 /* «? » + «value» + «? » */,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _157 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._181 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._048 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._067 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._110 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._153 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._161 /* 1*next-10-steps */,
				st._020 /* 1*'Tuple' */,
				st._215 /* V00*next-7-steps */,
				st._003 /* 1*'(' */,
				st._233 /* V01*next-4-steps */,
				st._147 /* 1*TupleTypeCS::ownedParts+=TuplePartCS */,
				st._244 /* V02*next-2-steps */,
				st._007 /* 1*',' */,
				st._147 /* 1*TupleTypeCS::ownedParts+=TuplePartCS */,
				st._004 /* 1*')' */,
				st._257 /* V03*TypedRefCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._10)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._48) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._10, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(105, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _158 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
				ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._165 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._075 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._006 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._115 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._185 /* 1*next-7-steps */,
				st._076 /* 1*CollectionTypeCS::name=CollectionTypeIdentifier */,
				st._209 /* V00*next-4-steps */,
				st._003 /* 1*'(' */,
				st._077 /* 1*CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS */,
				st._219 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS */,
				st._004 /* 1*')' */,
				st._241 /* V02*TypedRefCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._50) /* TypeExpWithoutMultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(108, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _159 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._100 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._055 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._171 /* 1*next-2-steps */,
				st._157 /* 1*TypedTypeRefCS::ownedPathName=PathNameCS */,
				st._203 /* V00*TypedRefCS::ownedMultiplicity=MultiplicityCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null,
				null,
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(73, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _160 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._100 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._054 /* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
				ms._055 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._183 /* 1*next-5-steps */,
				st._157 /* 1*TypedTypeRefCS::ownedPathName=PathNameCS */,
				st._012 /* 1*'<' */,
				st._156 /* 1*TypedTypeRefCS::ownedBinding=TemplateBindingCS */,
				st._015 /* 1*'>' */,
				st._203 /* V00*TypedRefCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._44) /* TemplateBindingCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(73, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(99, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _161 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
				ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._132 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._082 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._025 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._024 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._186 /* 1*next-8-steps */,
				st._019 /* 1*'Map' */,
				st._212 /* V00*next-5-steps */,
				st._003 /* 1*'(' */,
				st._098 /* 1*MapTypeCS::ownedKeyType=TypeExpCS */,
				st._007 /* 1*',' */,
				st._099 /* 1*MapTypeCS::ownedValueType=TypeExpCS */,
				st._004 /* 1*')' */,
				st._224 /* V01*TypedRefCS::ownedMultiplicity=MultiplicityCS */
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
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._49) /* TypeExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._09, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::TypedTypeRefCS : { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' }
		private @NonNull SerializationRule _162 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._054 /* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
				ms._055 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._157 /* 1*TypedTypeRefCS::ownedPathName=PathNameCS */,
				st._012 /* 1*'<' */,
				st._156 /* 1*TypedTypeRefCS::ownedBinding=TemplateBindingCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._44) /* TemplateBindingCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(73, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(99, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLinEcore::TypedTypeRefCS : ownedPathName=PathNameCS
		private @NonNull SerializationRule _163 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._055 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._157 /* 1*TypedTypeRefCS::ownedPathName=PathNameCS */
			},
			new @NonNull Segment @NonNull [] [] {
				null
			},
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(73, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLinEcore::TypedTypeRefCS : { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' }
		private @NonNull SerializationRule _164 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._054 /* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
				ms._055 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._181 /* 1*next-4-steps */,
				st._157 /* 1*TypedTypeRefCS::ownedPathName=PathNameCS */,
				st._003 /* 1*'(' */,
				st._156 /* 1*TypedTypeRefCS::ownedBinding=TemplateBindingCS */,
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
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._26) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._44) /* TemplateBindingCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(73, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(99, MultiplicativeCardinality.ONE)
					}
				)
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
		rv = new _RuleValues();
		ec = new _EClassData();
		st.init();
	}

}
//	Commented imports ensure Xtend provides a true import allowing unqualified annotated usage
//	import Inject;
//	import EAttribute;
//	import NonNull;
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
//	import EClassData;
//	import IndexVector;
//	import ParserRuleValue;
//	import TerminalRuleValue;
//	import BaseCSPackage;
//	import EssentialOCLCSPackage;
//	import OCLinEcoreCSPackage;
