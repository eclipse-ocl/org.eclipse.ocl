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
				new EClassValue [] {
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
					gr._000  /* 0 : ANY_OTHER */,
					gr._001  /* 1 : AnnotationCS */,
					gr._002  /* 2 : AnnotationElementCS */,
					gr._003  /* 3 : AttributeCS */,
					gr._004  /* 4 : BinaryOperatorName */,
					gr._005  /* 5 : BooleanLiteralExpCS */,
					gr._006  /* 6 : ClassCS */,
					gr._007  /* 7 : CoIteratorVariableCS */,
					gr._008  /* 8 : CollectionLiteralExpCS */,
					gr._009  /* 9 : CollectionLiteralPartCS */,
					gr._010  /* 10 : CollectionPatternCS */,
					gr._011  /* 11 : CollectionTypeCS */,
					gr._012  /* 12 : CollectionTypeIdentifier */,
					gr._013  /* 13 : CurlyBracketedClauseCS */,
					gr._014  /* 14 : DOUBLE_QUOTED_STRING */,
					gr._015  /* 15 : DataTypeCS */,
					gr._016  /* 16 : DetailCS */,
					gr._017  /* 17 : DocumentationCS */,
					gr._018  /* 18 : ESCAPED_CHARACTER */,
					gr._019  /* 19 : ESCAPED_ID */,
					gr._020  /* 20 : ElseIfThenExpCS */,
					gr._021  /* 21 : EnumerationCS */,
					gr._022  /* 22 : EnumerationLiteralCS */,
					gr._023  /* 23 : EnumerationLiteralName */,
					gr._024  /* 24 : EssentialOCLInfixOperatorName */,
					gr._025  /* 25 : EssentialOCLNavigationOperatorName */,
					gr._026  /* 26 : EssentialOCLReservedKeyword */,
					gr._027  /* 27 : EssentialOCLUnaryOperatorName */,
					gr._028  /* 28 : EssentialOCLUnreservedName */,
					gr._029  /* 29 : EssentialOCLUnrestrictedName */,
					gr._030  /* 30 : ExpCS */,
					gr._031  /* 31 : FirstPathElementCS */,
					gr._032  /* 32 : ID */,
					gr._033  /* 33 : INT */,
					gr._034  /* 34 : INTEGER */,
					gr._035  /* 35 : Identifier */,
					gr._036  /* 36 : IfExpCS */,
					gr._037  /* 37 : ImplicitOppositeCS */,
					gr._038  /* 38 : ImportCS */,
					gr._039  /* 39 : InfixOperatorName */,
					gr._040  /* 40 : InvalidLiteralExpCS */,
					gr._041  /* 41 : InvariantConstraintCS */,
					gr._042  /* 42 : LETTER_CHARACTER */,
					gr._043  /* 43 : LOWER */,
					gr._044  /* 44 : LambdaLiteralExpCS */,
					gr._045  /* 45 : LetExpCS */,
					gr._046  /* 46 : LetVariableCS */,
					gr._047  /* 47 : ML_COMMENT */,
					gr._048  /* 48 : ML_SINGLE_QUOTED_STRING */,
					gr._049  /* 49 : MapLiteralExpCS */,
					gr._050  /* 50 : MapLiteralPartCS */,
					gr._051  /* 51 : MapTypeCS */,
					gr._052  /* 52 : Model */,
					gr._053  /* 53 : ModelElementCS */,
					gr._054  /* 54 : ModelElementRefCS */,
					gr._055  /* 55 : MultiplicityBoundsCS */,
					gr._056  /* 56 : MultiplicityCS */,
					gr._057  /* 57 : MultiplicityStringCS */,
					gr._058  /* 58 : NUMBER_LITERAL */,
					gr._059  /* 59 : NameExpCS */,
					gr._060  /* 60 : NavigatingArgCS */,
					gr._061  /* 61 : NavigatingArgExpCS */,
					gr._062  /* 62 : NavigatingBarArgCS */,
					gr._063  /* 63 : NavigatingCommaArgCS */,
					gr._064  /* 64 : NavigatingSemiArgCS */,
					gr._065  /* 65 : NavigationOperatorName */,
					gr._066  /* 66 : NestedExpCS */,
					gr._067  /* 67 : NextPathElementCS */,
					gr._068  /* 68 : NullLiteralExpCS */,
					gr._069  /* 69 : NumberLiteralExpCS */,
					gr._070  /* 70 : OperationCS */,
					gr._071  /* 71 : PackageCS */,
					gr._072  /* 72 : ParameterCS */,
					gr._073  /* 73 : PathNameCS */,
					gr._074  /* 74 : PatternExpCS */,
					gr._075  /* 75 : PostconditionConstraintCS */,
					gr._076  /* 76 : PreconditionConstraintCS */,
					gr._077  /* 77 : PrefixedLetExpCS */,
					gr._078  /* 78 : PrefixedPrimaryExpCS */,
					gr._079  /* 79 : PrimaryExpCS */,
					gr._080  /* 80 : PrimitiveLiteralExpCS */,
					gr._081  /* 81 : PrimitiveTypeCS */,
					gr._082  /* 82 : PrimitiveTypeIdentifier */,
					gr._083  /* 83 : ReferenceCS */,
					gr._084  /* 84 : RoundBracketedClauseCS */,
					gr._085  /* 85 : SIGNED */,
					gr._086  /* 86 : SIMPLE_ID */,
					gr._087  /* 87 : SINGLE_QUOTED_STRING */,
					gr._088  /* 88 : SL_COMMENT */,
					gr._089  /* 89 : SelfExpCS */,
					gr._090  /* 90 : ShadowPartCS */,
					gr._091  /* 91 : SimplePathNameCS */,
					gr._092  /* 92 : SpecificationCS */,
					gr._093  /* 93 : SquareBracketedClauseCS */,
					gr._094  /* 94 : StringLiteral */,
					gr._095  /* 95 : StringLiteralExpCS */,
					gr._096  /* 96 : StructuralFeatureCS */,
					gr._097  /* 97 : StructuredClassCS */,
					gr._098  /* 98 : SysMLCS */,
					gr._099  /* 99 : TemplateBindingCS */,
					gr._100  /* 100 : TemplateParameterSubstitutionCS */,
					gr._101  /* 101 : TemplateSignatureCS */,
					gr._102  /* 102 : TopLevelCS */,
					gr._103  /* 103 : TupleLiteralExpCS */,
					gr._104  /* 104 : TupleLiteralPartCS */,
					gr._105  /* 105 : TuplePartCS */,
					gr._106  /* 106 : TupleTypeCS */,
					gr._107  /* 107 : TypeExpCS */,
					gr._108  /* 108 : TypeExpWithoutMultiplicityCS */,
					gr._109  /* 109 : TypeIdentifier */,
					gr._110  /* 110 : TypeLiteralCS */,
					gr._111  /* 111 : TypeLiteralExpCS */,
					gr._112  /* 112 : TypeLiteralWithMultiplicityCS */,
					gr._113  /* 113 : TypeNameExpCS */,
					gr._114  /* 114 : TypeParameterCS */,
					gr._115  /* 115 : TypeRefCS */,
					gr._116  /* 116 : TypedMultiplicityRefCS */,
					gr._117  /* 117 : TypedRefCS */,
					gr._118  /* 118 : TypedTypeRefCS */,
					gr._119  /* 119 : UNQUOTED_STRING */,
					gr._120  /* 120 : UPPER */,
					gr._121  /* 121 : URI */,
					gr._122  /* 122 : URIFirstPathElementCS */,
					gr._123  /* 123 : URIPathNameCS */,
					gr._124  /* 124 : UnaryOperatorName */,
					gr._125  /* 125 : UnlimitedNaturalLiteralExpCS */,
					gr._126  /* 126 : UnreservedName */,
					gr._127  /* 127 : UnrestrictedName */,
					gr._128  /* 128 : WS */,
					gr._129  /* 129 : WildcardTypeRefCS */
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
		private final @NonNull IndexVector _21 // NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
			= new IndexVector(0xc000000000000000L,0x1L);
		private final @NonNull IndexVector _22 // NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
			= new IndexVector(0xd000000000000000L,0x1L);
		private final @NonNull IndexVector _23 // FirstPathElementCS|NextPathElementCS
			= new IndexVector(0x80000000L,0x8L);
		private final @NonNull IndexVector _24 // OperationCS
			= new IndexVector(0x0L,0x40L);
		private final @NonNull IndexVector _25 // PackageCS
			= new IndexVector(0x0L,0x80L);
		private final @NonNull IndexVector _26 // ParameterCS
			= new IndexVector(0x0L,0x100L);
		private final @NonNull IndexVector _27 // PathNameCS
			= new IndexVector(0x0L,0x200L);
		private final @NonNull IndexVector _28 // PatternExpCS
			= new IndexVector(0x0L,0x400L);
		private final @NonNull IndexVector _29 // ExpCS|PatternExpCS
			= new IndexVector(0x40000000L,0x400L);
		private final @NonNull IndexVector _30 // PostconditionConstraintCS
			= new IndexVector(0x0L,0x800L);
		private final @NonNull IndexVector _31 // PreconditionConstraintCS
			= new IndexVector(0x0L,0x1000L);
		private final @NonNull IndexVector _32 // PrefixedLetExpCS
			= new IndexVector(0x0L,0x2000L);
		private final @NonNull IndexVector _33 // LetExpCS|PrefixedLetExpCS
			= new IndexVector(0x200000000000L,0x2000L);
		private final @NonNull IndexVector _34 // PrefixedPrimaryExpCS
			= new IndexVector(0x0L,0x4000L);
		private final @NonNull IndexVector _35 // RoundBracketedClauseCS
			= new IndexVector(0x0L,0x100000L);
		private final @NonNull IndexVector _36 // ML_SINGLE_QUOTED_STRING|SINGLE_QUOTED_STRING
			= new IndexVector(0x1000000000000L,0x800000L);
		private final @NonNull IndexVector _37 // ShadowPartCS
			= new IndexVector(0x0L,0x4000000L);
		private final @NonNull IndexVector _38 // SpecificationCS
			= new IndexVector(0x0L,0x10000000L);
		private final @NonNull IndexVector _39 // SquareBracketedClauseCS
			= new IndexVector(0x0L,0x20000000L);
		private final @NonNull IndexVector _40 // StringLiteralExpCS
			= new IndexVector(0x0L,0x80000000L);
		private final @NonNull IndexVector _41 // StructuralFeatureCS
			= new IndexVector(0x0L,0x100000000L);
		private final @NonNull IndexVector _42 // AttributeCS|ReferenceCS|StructuralFeatureCS
			= new IndexVector(0x8L,0x100080000L);
		private final @NonNull IndexVector _43 // ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS
			= new IndexVector(0x208040L,0x200000000L);
		private final @NonNull IndexVector _44 // AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS
			= new IndexVector(0x20000000608048L,0x3000800c0L);
		private final @NonNull IndexVector _45 // AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS
			= new IndexVector(0x20006L,0x400000000L);
		private final @NonNull IndexVector _46 // TemplateBindingCS
			= new IndexVector(0x0L,0x800000000L);
		private final @NonNull IndexVector _47 // TemplateParameterSubstitutionCS
			= new IndexVector(0x0L,0x1000000000L);
		private final @NonNull IndexVector _48 // TemplateSignatureCS
			= new IndexVector(0x0L,0x2000000000L);
		private final @NonNull IndexVector _49 // TupleLiteralPartCS
			= new IndexVector(0x0L,0x10000000000L);
		private final @NonNull IndexVector _50 // TuplePartCS
			= new IndexVector(0x0L,0x20000000000L);
		private final @NonNull IndexVector _51 // TypeExpCS
			= new IndexVector(0x0L,0x80000000000L);
		private final @NonNull IndexVector _52 // TypeExpWithoutMultiplicityCS
			= new IndexVector(0x0L,0x100000000000L);
		private final @NonNull IndexVector _53 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
			= new IndexVector(0x8000000000800L,0x440000020000L);
		private final @NonNull IndexVector _54 // TypeLiteralWithMultiplicityCS
			= new IndexVector(0x0L,0x1000000000000L);
		private final @NonNull IndexVector _55 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS
			= new IndexVector(0x8000000000800L,0x1440000020000L);
		private final @NonNull IndexVector _56 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new IndexVector(0x8000000000c00L,0x2540000020000L);
		private final @NonNull IndexVector _57 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new IndexVector(0x8000000000c00L,0x25c0000020000L);
		private final @NonNull IndexVector _58 // TypeParameterCS
			= new IndexVector(0x0L,0x4000000000000L);
		private final @NonNull IndexVector _59 // TypeRefCS
			= new IndexVector(0x0L,0x8000000000000L);
		private final @NonNull IndexVector _60 // TypedMultiplicityRefCS
			= new IndexVector(0x0L,0x10000000000000L);
		private final @NonNull IndexVector _61 // TypedRefCS
			= new IndexVector(0x0L,0x20000000000000L);
		private final @NonNull IndexVector _62 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS
			= new IndexVector(0x8000000000800L,0x60440000020000L);
		private final @NonNull IndexVector _63 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS
			= new IndexVector(0x8000000000800L,0x70440000020000L);
		private final @NonNull IndexVector _64 // NextPathElementCS|URIFirstPathElementCS
			= new IndexVector(0x0L,0x400000000000008L);
		private final @NonNull IndexVector _65 // FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
			= new IndexVector(0x80000000L,0x400000000000008L);
		private final @NonNull IndexVector _66 // URIPathNameCS
			= new IndexVector(0x0L,0x800000000000000L);
		private final @NonNull IndexVector _67 // BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x10000000020L,0x2000000080010030L);
		private final @NonNull IndexVector _68 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x802111000000120L,0x2000808082018034L);
		private final @NonNull IndexVector _69 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x802111000000120L,0x200080808201c034L);
		private final @NonNull IndexVector _70 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x802311000000120L,0x200080808201e034L);
		private final @NonNull IndexVector _71 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x802311040000120L,0x200080808201e034L);
		private final @NonNull IndexVector _72 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x2802311040000120L,0x200080808201e034L);
		private final @NonNull IndexVector _73 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x802311040000120L,0x200080808201e434L);
		private final @NonNull IndexVector _74 // SINGLE_QUOTED_STRING|UnrestrictedName
			= new IndexVector(0x0L,0x8000000000800000L);
		private final @NonNull IndexVector _75 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
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
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, iv._38/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _234 // check-rule basecs::ConstraintCS.ownedSpecification : 92
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, iv._38/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _235 // check-rule basecs::EnumerationCS.ownedLiterals : 22
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, iv._8/*EnumerationLiteralCS*/);
		private final @NonNull CardinalitySolutionStep _236 // check-rule basecs::ImportCS.ownedPathName : 123
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, iv._66/*URIPathNameCS*/);
		private final @NonNull CardinalitySolutionStep _237 // check-rule basecs::ModelElementCS.ownedAnnotations : 2
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, iv._0/*AnnotationElementCS*/);
		private final @NonNull CardinalitySolutionStep _238 // check-rule basecs::ModelElementRefCS.ownedPathName : 73
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, iv._27/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _239 // check-rule basecs::OperationCS.ownedBodyExpressions : 92
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, iv._38/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _240 // check-rule basecs::OperationCS.ownedExceptions : 117
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, iv._61/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _241 // check-rule basecs::OperationCS.ownedParameters : 72
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, iv._26/*ParameterCS*/);
		private final @NonNull CardinalitySolutionStep _242 // check-rule basecs::OperationCS.ownedPostconditions : 75
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, iv._30/*PostconditionConstraintCS*/);
		private final @NonNull CardinalitySolutionStep _243 // check-rule basecs::OperationCS.ownedPreconditions : 76
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, iv._31/*PreconditionConstraintCS*/);
		private final @NonNull CardinalitySolutionStep _244 // check-rule basecs::PackageCS.ownedClasses : 6
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, iv._1/*ClassCS*/);
		private final @NonNull CardinalitySolutionStep _245 // check-rule basecs::PackageOwnerCS.ownedPackages : 71
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, iv._25/*PackageCS*/);
		private final @NonNull CardinalitySolutionStep _246 // check-rule basecs::PathNameCS.ownedPathElements : 31
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._10/*FirstPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _247 // check-rule basecs::PathNameCS.ownedPathElements : 31|67
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._23/*FirstPathElementCS|NextPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _248 // check-rule basecs::PathNameCS.ownedPathElements : 67|122
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._64/*NextPathElementCS|URIFirstPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _249 // check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, iv._11/*ImplicitOppositeCS*/);
		private final @NonNull CardinalitySolutionStep _250 // check-rule basecs::RootCS.ownedImports : 38
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, iv._12/*ImportCS*/);
		private final @NonNull CardinalitySolutionStep _251 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, iv._38/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _252 // check-rule basecs::StructuredClassCS.ownedOperations : 70
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, iv._24/*OperationCS*/);
		private final @NonNull CardinalitySolutionStep _253 // check-rule basecs::StructuredClassCS.ownedProperties : 96
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, iv._41/*StructuralFeatureCS*/);
		private final @NonNull CardinalitySolutionStep _254 // check-rule basecs::StructuredClassCS.ownedSuperTypes : 117
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, iv._61/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _255 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : 56
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, iv._19/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _256 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : 100
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, iv._47/*TemplateParameterSubstitutionCS*/);
		private final @NonNull CardinalitySolutionStep _257 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 115
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, iv._59/*TypeRefCS*/);
		private final @NonNull CardinalitySolutionStep _258 // check-rule basecs::TemplateSignatureCS.ownedParameters : 114
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, iv._58/*TypeParameterCS*/);
		private final @NonNull CardinalitySolutionStep _259 // check-rule basecs::TemplateableElementCS.ownedSignature : 101
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, iv._48/*TemplateSignatureCS*/);
		private final @NonNull CardinalitySolutionStep _260 // check-rule basecs::TupleTypeCS.ownedParts : 105
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, iv._50/*TuplePartCS*/);
		private final @NonNull CardinalitySolutionStep _261 // check-rule basecs::TypeParameterCS.ownedExtends : 117
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, iv._61/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _262 // check-rule basecs::TypedElementCS.ownedType : 107
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, iv._51/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _263 // check-rule basecs::TypedElementCS.ownedType : 116
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, iv._60/*TypedMultiplicityRefCS*/);
		private final @NonNull CardinalitySolutionStep _264 // check-rule basecs::TypedRefCS.ownedMultiplicity : 56
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, iv._19/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _265 // check-rule basecs::TypedTypeRefCS.ownedBinding : 99
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, iv._46/*TemplateBindingCS*/);
		private final @NonNull CardinalitySolutionStep _266 // check-rule basecs::TypedTypeRefCS.ownedPathName : 73
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, iv._27/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _267 // check-rule basecs::WildcardTypeRefCS.ownedExtends : 117
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, iv._61/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _268 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._5/*CurlyBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _269 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, iv._27/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _270 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._35/*RoundBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _271 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, iv._39/*SquareBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _272 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, iv._3/*CollectionLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _273 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, iv._4/*CollectionTypeCS*/);
		private final @NonNull CardinalitySolutionStep _274 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _275 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 74
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._28/*PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _276 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _277 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, iv._28/*PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _278 // check-rule essentialoclcs::CollectionPatternCS.ownedType : 11
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, iv._4/*CollectionTypeCS*/);
		private final @NonNull CardinalitySolutionStep _279 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, iv._19/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _280 // check-rule essentialoclcs::CollectionTypeCS.ownedType : 108
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, iv._52/*TypeExpWithoutMultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _281 // check-rule essentialoclcs::ContextCS.ownedExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _282 // check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 90
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, iv._37/*ShadowPartCS*/);
		private final @NonNull CardinalitySolutionStep _283 // check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _284 // check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, iv._29/*ExpCS|PatternExpCS*/);
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
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, iv._34/*PrefixedPrimaryExpCS*/);
		private final @NonNull CardinalitySolutionStep _291 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _292 // check-rule essentialoclcs::LetExpCS.ownedInExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _293 // check-rule essentialoclcs::LetExpCS.ownedVariables : 46
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, iv._14/*LetVariableCS*/);
		private final @NonNull CardinalitySolutionStep _294 // check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 84
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._35/*RoundBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _295 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, iv._15/*MapLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _296 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, iv._16/*MapTypeCS*/);
		private final @NonNull CardinalitySolutionStep _297 // check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _298 // check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _299 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, iv._51/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _300 // check-rule essentialoclcs::MapTypeCS.ownedValueType : 107
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, iv._51/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _301 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, iv._2/*CoIteratorVariableCS*/);
		private final @NonNull CardinalitySolutionStep _302 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _303 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, iv._20/*NavigatingArgExpCS*/);
		private final @NonNull CardinalitySolutionStep _304 // check-rule essentialoclcs::NavigatingArgCS.ownedType : 107
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, iv._51/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _305 // check-rule essentialoclcs::NestedExpCS.ownedExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _306 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _307 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 77
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._32/*PrefixedLetExpCS*/);
		private final @NonNull CardinalitySolutionStep _308 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 78
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._34/*PrefixedPrimaryExpCS*/);
		private final @NonNull CardinalitySolutionStep _309 // check-rule essentialoclcs::PatternExpCS.ownedPatternType : 107
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, iv._51/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _310 // check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 60|62|63|64
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, iv._22/*NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS*/);
		private final @NonNull CardinalitySolutionStep _311 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 30|74
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._29/*ExpCS|PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _312 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 95
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._40/*StringLiteralExpCS*/);
		private final @NonNull CardinalitySolutionStep _313 // check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _314 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, iv._49/*TupleLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _315 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, iv._54/*TypeLiteralWithMultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _316 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._5/*CurlyBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _317 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, iv._27/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _318 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _319 // check-rule essentialoclcs::VariableCS.ownedInitExpression : 30
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _320 // check-rule essentialoclcs::VariableCS.ownedType : 107
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, iv._51/*TypeExpCS*/);
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
		private final @NonNull RTSerializationAssignedRuleCallStep _064 // 1*AbstractNameExpCS::ownedPathName=73
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 73 /* PathNameCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _065 // 1*AnnotationElementCS::ownedDetails+=16
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 16 /* DetailCS */);
		private final @NonNull RTSerializationAssignStep _066 // 1*BooleanLiteralExpCS::symbol
									= new RTSerializationAssignStep(-1, EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull RTSerializationAssignedRuleCallStep _067 // 1*ClassCS::instanceClassName=87
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, 87 /* SINGLE_QUOTED_STRING */);
		private final @NonNull RTSerializationAssignedRuleCallStep _068 // 1*CollectionLiteralExpCS::ownedParts+=9
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 9 /* CollectionLiteralPartCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _069 // 1*CollectionLiteralExpCS::ownedType=11
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 11 /* CollectionTypeCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _070 // 1*CollectionLiteralPartCS::ownedExpression=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _071 // 1*CollectionLiteralPartCS::ownedExpression=74
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 74 /* PatternExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _072 // 1*CollectionLiteralPartCS::ownedLastExpression=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _073 // 1*CollectionPatternCS::ownedParts+=74
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 74 /* PatternExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _074 // 1*CollectionPatternCS::ownedType=11
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 11 /* CollectionTypeCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _075 // 1*CollectionPatternCS::restVariableName=35
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 35 /* Identifier */);
		private final @NonNull RTSerializationAssignedRuleCallStep _076 // 1*CollectionTypeCS::name=12
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 12 /* CollectionTypeIdentifier */);
		private final @NonNull RTSerializationAssignedRuleCallStep _077 // 1*CollectionTypeCS::ownedType=108
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 108 /* TypeExpWithoutMultiplicityCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _078 // 1*ConstraintCS::ownedMessageSpecification=92
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 92 /* SpecificationCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _079 // 1*ContextCS::ownedExpression=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _080 // 1*CurlyBracketedClauseCS::ownedParts+=90
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 90 /* ShadowPartCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _081 // 1*EnumerationLiteralCS::literal=87
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL, 87 /* SINGLE_QUOTED_STRING */);
		private final @NonNull RTSerializationAssignedRuleCallStep _082 // 1*EnumerationLiteralCS::value=85
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE, 85 /* SIGNED */);
		private final @NonNull RTSerializationAssignedRuleCallStep _083 // 1*ExpSpecificationCS::ownedExpression=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallsStep _084 // 1*IfExpCS::ownedCondition=30|74
									= new RTSerializationAssignedRuleCallsStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, iv._29 /* ExpCS, PatternExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _085 // 1*IfExpCS::ownedElseExpression=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _086 // 1*IfExpCS::ownedThenExpression=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _087 // 1*IfThenExpCS::ownedCondition=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _088 // 1*IfThenExpCS::ownedThenExpression=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _089 // 1*ImportCS::ownedPathName=123
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 123 /* URIPathNameCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _090 // 1*InfixExpCS::ownedLeft=78
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 78 /* PrefixedPrimaryExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _091 // 1*LambdaLiteralExpCS::ownedExpressionCS=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _092 // 1*LetExpCS::ownedInExpression=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _093 // 1*LetExpCS::ownedVariables+=46
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 46 /* LetVariableCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _094 // 1*MapLiteralExpCS::ownedParts+=50
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 50 /* MapLiteralPartCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _095 // 1*MapLiteralExpCS::ownedType=51
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 51 /* MapTypeCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _096 // 1*MapLiteralPartCS::ownedKey=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _097 // 1*MapLiteralPartCS::ownedValue=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _098 // 1*MapTypeCS::ownedKeyType=107
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 107 /* TypeExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _099 // 1*MapTypeCS::ownedValueType=107
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 107 /* TypeExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _100 // 1*ModelElementRefCS::ownedPathName=73
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, 73 /* PathNameCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _101 // 1*MultiplicityBoundsCS::lowerBound=43
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 43 /* LOWER */);
		private final @NonNull RTSerializationAssignedRuleCallStep _102 // 1*MultiplicityBoundsCS::upperBound=120
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 120 /* UPPER */);
		private final @NonNull RTSerializationAssignStep _103 // 1*MultiplicityStringCS::stringBounds
									= new RTSerializationAssignStep(-1, BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS);
		private final @NonNull RTSerializationAssignedRuleCallStep _104 // 1*NamedElementCS::name=124
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 124 /* UnaryOperatorName */);
		private final @NonNull RTSerializationAssignedRuleCallStep _105 // 1*NamedElementCS::name=127
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 127 /* UnrestrictedName */);
		private final @NonNull RTSerializationAssignedRuleCallStep _106 // 1*NamedElementCS::name=23
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 23 /* EnumerationLiteralName */);
		private final @NonNull RTSerializationAssignedRuleCallStep _107 // 1*NamedElementCS::name=4
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 4 /* BinaryOperatorName */);
		private final @NonNull RTSerializationAssignedRuleCallsStep _108 // 1*NamedElementCS::name=87|127
									= new RTSerializationAssignedRuleCallsStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, iv._74 /* SINGLE_QUOTED_STRING, UnrestrictedName */);
		private final @NonNull RTSerializationAssignedRuleCallStep _109 // 1*NavigatingArgCS::ownedCoIterator=7
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 7 /* CoIteratorVariableCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _110 // 1*NavigatingArgCS::ownedInitExpression=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _111 // 1*NavigatingArgCS::ownedNameExpression=61
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 61 /* NavigatingArgExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _112 // 1*NavigatingArgCS::ownedType=107
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 107 /* TypeExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _113 // 1*NestedExpCS::ownedExpression=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _114 // 1*NumberLiteralExpCS::symbol=58
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 58 /* NUMBER_LITERAL */);
		private final @NonNull RTSerializationAssignedRuleCallStep _115 // 1*OperationCS::ownedExceptions+=117
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, 117 /* TypedRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _116 // 1*OperationCS::ownedParameters+=72
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 72 /* ParameterCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _117 // 1*OperatorExpCS::ownedRight=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _118 // 1*OperatorExpCS::ownedRight=77
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 77 /* PrefixedLetExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _119 // 1*OperatorExpCS::ownedRight=78
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 78 /* PrefixedPrimaryExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _120 // 1*PackageCS::nsPrefix=127
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, 127 /* UnrestrictedName */);
		private final @NonNull RTSerializationAssignedRuleCallStep _121 // 1*PackageCS::nsURI=121
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PACKAGE_CS__NS_URI, 121 /* URI */);
		private final @NonNull RTSerializationCrossReferenceStep _122 // 1*PathElementCS::referredElement=URI
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"));
		private final @NonNull RTSerializationCrossReferenceStep _123 // 1*PathElementCS::referredElement=UnreservedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"));
		private final @NonNull RTSerializationCrossReferenceStep _124 // 1*PathElementCS::referredElement=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull RTSerializationCrossReferenceStep _125 // 1*PathElementCS::referredElement=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _126 // 1*PathNameCS::ownedPathElements+=122
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 122 /* URIFirstPathElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _127 // 1*PathNameCS::ownedPathElements+=31
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 31 /* FirstPathElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _128 // 1*PathNameCS::ownedPathElements+=67
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 67 /* NextPathElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _129 // 1*PatternExpCS::ownedPatternType=107
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 107 /* TypeExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _130 // 1*PrimitiveTypeRefCS::name=82
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 82 /* PrimitiveTypeIdentifier */);
		private final @NonNull RTSerializationAssignedRuleCallStep _131 // 1*ReferenceCS::ownedImplicitOpposites+=37
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, 37 /* ImplicitOppositeCS */);
		private final @NonNull RTSerializationCrossReferenceStep _132 // 1*ReferenceCS::referredKeys+=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, getCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, "UnrestrictedName"));
		private final @NonNull RTSerializationCrossReferenceStep _133 // 1*ReferenceCS::referredKeys+=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, getCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, "UnrestrictedName"));
		private final @NonNull RTSerializationCrossReferenceStep _134 // 1*ReferenceCS::referredOpposite=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, getCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, "UnrestrictedName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _135 // 1*RoundBracketedClauseCS::ownedArguments+=60
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 60 /* NavigatingArgCS */);
		private final @NonNull RTSerializationAssignedRuleCallsStep _136 // 1*ShadowPartCS::ownedInitExpression=30|74
									= new RTSerializationAssignedRuleCallsStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._29 /* ExpCS, PatternExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _137 // 1*ShadowPartCS::ownedInitExpression=95
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 95 /* StringLiteralExpCS */);
		private final @NonNull RTSerializationCrossReferenceStep _138 // 1*ShadowPartCS::referredProperty=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _139 // 1*SpecificationCS::exprString=119
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, 119 /* UNQUOTED_STRING */);
		private final @NonNull RTSerializationAssignedRuleCallStep _140 // 1*SquareBracketedClauseCS::ownedTerms+=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _141 // 1*StructuralFeatureCS::default=87
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, 87 /* SINGLE_QUOTED_STRING */);
		private final @NonNull RTSerializationAssignedRuleCallStep _142 // 1*StructuredClassCS::ownedSuperTypes+=117
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 117 /* TypedRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _143 // 1*TemplateBindingCS::ownedSubstitutions+=100
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 100 /* TemplateParameterSubstitutionCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _144 // 1*TemplateParameterSubstitutionCS::ownedActualParameter=115
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 115 /* TypeRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _145 // 1*TemplateSignatureCS::ownedParameters+=114
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 114 /* TypeParameterCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _146 // 1*TupleLiteralExpCS::ownedParts+=104
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 104 /* TupleLiteralPartCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _147 // 1*TupleTypeCS::ownedParts+=105
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 105 /* TuplePartCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _148 // 1*TypeLiteralExpCS::ownedType=112
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 112 /* TypeLiteralWithMultiplicityCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _149 // 1*TypeNameExpCS::ownedCurlyBracketedClause=13
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 13 /* CurlyBracketedClauseCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _150 // 1*TypeNameExpCS::ownedPathName=73
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 73 /* PathNameCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _151 // 1*TypeNameExpCS::ownedPatternGuard=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _152 // 1*TypeParameterCS::ownedExtends+=117
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 117 /* TypedRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _153 // 1*TypedElementCS::ownedType=107
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 107 /* TypeExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _154 // 1*TypedElementCS::ownedType=116
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 116 /* TypedMultiplicityRefCS */);
		private final @NonNull RTSerializationAssignStep _155 // 1*TypedElementCS::qualifiers
									= new RTSerializationAssignStep(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS);
		private final @NonNull RTSerializationAssignedRuleCallStep _156 // 1*TypedTypeRefCS::ownedBinding=99
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 99 /* TemplateBindingCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _157 // 1*TypedTypeRefCS::ownedPathName=73
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 73 /* PathNameCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _158 // 1*VariableCS::ownedInitExpression=30
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _159 // 1*VariableCS::ownedType=107
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 107 /* TypeExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _160 // 1*WildcardTypeRefCS::ownedExtends=117
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 117 /* TypedRefCS */);
		private final @NonNull RTSerializationSequenceStep _161 // 1*steps-1..10
									= new RTSerializationSequenceStep(-1, 1, 10);
		private final @NonNull RTSerializationSequenceStep _162 // 1*steps-1..11
									= new RTSerializationSequenceStep(-1, 1, 11);
		private final @NonNull RTSerializationSequenceStep _163 // 1*steps-1..12
									= new RTSerializationSequenceStep(-1, 1, 12);
		private final @NonNull RTSerializationSequenceStep _164 // 1*steps-1..13
									= new RTSerializationSequenceStep(-1, 1, 13);
		private final @NonNull RTSerializationSequenceStep _165 // 1*steps-1..14
									= new RTSerializationSequenceStep(-1, 1, 14);
		private final @NonNull RTSerializationSequenceStep _166 // 1*steps-1..15
									= new RTSerializationSequenceStep(-1, 1, 15);
		private final @NonNull RTSerializationSequenceStep _167 // 1*steps-1..16
									= new RTSerializationSequenceStep(-1, 1, 16);
		private final @NonNull RTSerializationSequenceStep _168 // 1*steps-1..17
									= new RTSerializationSequenceStep(-1, 1, 17);
		private final @NonNull RTSerializationSequenceStep _169 // 1*steps-1..18
									= new RTSerializationSequenceStep(-1, 1, 18);
		private final @NonNull RTSerializationSequenceStep _170 // 1*steps-1..19
									= new RTSerializationSequenceStep(-1, 1, 19);
		private final @NonNull RTSerializationSequenceStep _171 // 1*steps-1..20
									= new RTSerializationSequenceStep(-1, 1, 20);
		private final @NonNull RTSerializationSequenceStep _172 // 1*steps-1..24
									= new RTSerializationSequenceStep(-1, 1, 24);
		private final @NonNull RTSerializationSequenceStep _173 // 1*steps-1..26
									= new RTSerializationSequenceStep(-1, 1, 26);
		private final @NonNull RTSerializationSequenceStep _174 // 1*steps-1..27
									= new RTSerializationSequenceStep(-1, 1, 27);
		private final @NonNull RTSerializationSequenceStep _175 // 1*steps-1..28
									= new RTSerializationSequenceStep(-1, 1, 28);
		private final @NonNull RTSerializationSequenceStep _176 // 1*steps-1..29
									= new RTSerializationSequenceStep(-1, 1, 29);
		private final @NonNull RTSerializationSequenceStep _177 // 1*steps-1..3
									= new RTSerializationSequenceStep(-1, 1, 3);
		private final @NonNull RTSerializationSequenceStep _178 // 1*steps-1..35
									= new RTSerializationSequenceStep(-1, 1, 35);
		private final @NonNull RTSerializationSequenceStep _179 // 1*steps-1..37
									= new RTSerializationSequenceStep(-1, 1, 37);
		private final @NonNull RTSerializationSequenceStep _180 // 1*steps-1..4
									= new RTSerializationSequenceStep(-1, 1, 4);
		private final @NonNull RTSerializationSequenceStep _181 // 1*steps-1..40
									= new RTSerializationSequenceStep(-1, 1, 40);
		private final @NonNull RTSerializationSequenceStep _182 // 1*steps-1..42
									= new RTSerializationSequenceStep(-1, 1, 42);
		private final @NonNull RTSerializationSequenceStep _183 // 1*steps-1..5
									= new RTSerializationSequenceStep(-1, 1, 5);
		private final @NonNull RTSerializationSequenceStep _184 // 1*steps-1..6
									= new RTSerializationSequenceStep(-1, 1, 6);
		private final @NonNull RTSerializationSequenceStep _185 // 1*steps-1..7
									= new RTSerializationSequenceStep(-1, 1, 7);
		private final @NonNull RTSerializationSequenceStep _186 // 1*steps-1..8
									= new RTSerializationSequenceStep(-1, 1, 8);
		private final @NonNull RTSerializationSequenceStep _187 // 1*steps-1..9
									= new RTSerializationSequenceStep(-1, 1, 9);
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
		private final @NonNull RTSerializationAssignedRuleCallStep _194 // V00*AbstractNameExpCS::ownedSquareBracketedClauses+=93
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 93 /* SquareBracketedClauseCS */);
		private final @NonNull RTSerializationAssignedRuleCallsStep _195 // V00*DetailCS::values+=48|87
									= new RTSerializationAssignedRuleCallsStep(0, BaseCSPackage.Literals.DETAIL_CS__VALUES, iv._36 /* ML_SINGLE_QUOTED_STRING, SINGLE_QUOTED_STRING */);
		private final @NonNull RTSerializationAssignedRuleCallStep _196 // V00*DocumentationCS::value=87
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE, 87 /* SINGLE_QUOTED_STRING */);
		private final @NonNull RTSerializationAssignedRuleCallStep _197 // V00*IfExpCS::ownedIfThenExpressions+=20
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 20 /* ElseIfThenExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _198 // V00*LetVariableCS::ownedRoundBracketedClause=84
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 84 /* RoundBracketedClauseCS */);
		private final @NonNull RTSerializationAssignedRuleCallsStep _199 // V00*NamedElementCS::name=87|127
									= new RTSerializationAssignedRuleCallsStep(0, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, iv._74 /* SINGLE_QUOTED_STRING, UnrestrictedName */);
		private final @NonNull RTSerializationAssignedRuleCallStep _200 // V00*PatternExpCS::patternVariableName=127
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 127 /* UnrestrictedName */);
		private final @NonNull RTSerializationAssignedRuleCallStep _201 // V00*StringLiteralExpCS::segments+=94
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 94 /* StringLiteral */);
		private final @NonNull RTSerializationAssignedRuleCallStep _202 // V00*TemplateableElementCS::ownedSignature=101
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 101 /* TemplateSignatureCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _203 // V00*TypedRefCS::ownedMultiplicity=56
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 56 /* MultiplicityCS */);
		private final @NonNull RTSerializationSequenceStep _204 // V00*steps-2..3
									= new RTSerializationSequenceStep(0, 2, 3);
		private final @NonNull RTSerializationSequenceStep _205 // V00*steps-3..10
									= new RTSerializationSequenceStep(0, 3, 10);
		private final @NonNull RTSerializationSequenceStep _206 // V00*steps-3..5
									= new RTSerializationSequenceStep(0, 3, 5);
		private final @NonNull RTSerializationSequenceStep _207 // V00*steps-3..7
									= new RTSerializationSequenceStep(0, 3, 7);
		private final @NonNull RTSerializationSequenceStep _208 // V00*steps-3..8
									= new RTSerializationSequenceStep(0, 3, 8);
		private final @NonNull RTSerializationSequenceStep _209 // V00*steps-4..10
									= new RTSerializationSequenceStep(0, 4, 10);
		private final @NonNull RTSerializationSequenceStep _210 // V00*steps-4..6
									= new RTSerializationSequenceStep(0, 4, 6);
		private final @NonNull RTSerializationSequenceStep _211 // V00*steps-4..8
									= new RTSerializationSequenceStep(0, 4, 8);
		private final @NonNull RTSerializationSequenceStep _212 // V00*steps-4..9
									= new RTSerializationSequenceStep(0, 4, 9);
		private final @NonNull RTSerializationSequenceStep _213 // V00*steps-5..7
									= new RTSerializationSequenceStep(0, 5, 7);
		private final @NonNull RTSerializationSequenceStep _214 // V00*steps-6..10
									= new RTSerializationSequenceStep(0, 6, 10);
		private final @NonNull RTSerializationSequenceStep _215 // V00*steps-6..8
									= new RTSerializationSequenceStep(0, 6, 8);
		private final @NonNull RTSerializationLiteralStep _216 // V01*'::*'
									= new RTSerializationLiteralStep(1, "::*");
		private final @NonNull RTSerializationLiteralStep _217 // V01*'|1'
									= new RTSerializationLiteralStep(1, "|1");
		private final @NonNull RTSerializationAssignedRuleCallStep _218 // V01*AbstractNameExpCS::ownedRoundBracketedClause=84
									= new RTSerializationAssignedRuleCallStep(1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 84 /* RoundBracketedClauseCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _219 // V01*CollectionTypeCS::ownedCollectionMultiplicity=56
									= new RTSerializationAssignedRuleCallStep(1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 56 /* MultiplicityCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _220 // V01*RootCS::ownedImports+=38
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 38 /* ImportCS */);
		private final @NonNull RTSerializationAssignedRuleCallsStep _221 // V01*RoundBracketedClauseCS::ownedArguments+=62|63|64
									= new RTSerializationAssignedRuleCallsStep(1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, iv._21 /* NavigatingBarArgCS, NavigatingCommaArgCS, NavigatingSemiArgCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _222 // V01*TemplateBindingCS::ownedMultiplicity=56
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 56 /* MultiplicityCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _223 // V01*TemplateableElementCS::ownedSignature=101
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 101 /* TemplateSignatureCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _224 // V01*TypedRefCS::ownedMultiplicity=56
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 56 /* MultiplicityCS */);
		private final @NonNull RTSerializationSequenceStep _225 // V01*steps-4..10
									= new RTSerializationSequenceStep(1, 4, 10);
		private final @NonNull RTSerializationSequenceStep _226 // V01*steps-4..6
									= new RTSerializationSequenceStep(1, 4, 6);
		private final @NonNull RTSerializationSequenceStep _227 // V01*steps-4..9
									= new RTSerializationSequenceStep(1, 4, 9);
		private final @NonNull RTSerializationSequenceStep _228 // V01*steps-5..7
									= new RTSerializationSequenceStep(1, 5, 7);
		private final @NonNull RTSerializationSequenceStep _229 // V01*steps-5..8
									= new RTSerializationSequenceStep(1, 5, 8);
		private final @NonNull RTSerializationSequenceStep _230 // V01*steps-5..9
									= new RTSerializationSequenceStep(1, 5, 9);
		private final @NonNull RTSerializationSequenceStep _231 // V01*steps-6..10
									= new RTSerializationSequenceStep(1, 6, 10);
		private final @NonNull RTSerializationSequenceStep _232 // V01*steps-6..8
									= new RTSerializationSequenceStep(1, 6, 8);
		private final @NonNull RTSerializationSequenceStep _233 // V01*steps-7..9
									= new RTSerializationSequenceStep(1, 7, 9);
		private final @NonNull RTSerializationSequenceStep _234 // V01*steps-8..10
									= new RTSerializationSequenceStep(1, 8, 10);
		private final @NonNull RTSerializationSequenceStep _235 // V01*steps-8..9
									= new RTSerializationSequenceStep(1, 8, 9);
		private final @NonNull RTSerializationSequenceStep _236 // V01*steps-9..11
									= new RTSerializationSequenceStep(1, 9, 11);
		private final @NonNull RTSerializationAssignedRuleCallStep _237 // V02*AbstractNameExpCS::ownedCurlyBracketedClause=13
									= new RTSerializationAssignedRuleCallStep(2, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 13 /* CurlyBracketedClauseCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _238 // V02*ConstraintCS::ownedSpecification=92
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 92 /* SpecificationCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _239 // V02*ModelElementCS::ownedAnnotations+=2
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _240 // V02*PackageOwnerCS::ownedPackages+=71
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 71 /* PackageCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _241 // V02*TypedRefCS::ownedMultiplicity=56
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 56 /* MultiplicityCS */);
		private final @NonNull RTSerializationSequenceStep _242 // V02*steps-10..12
									= new RTSerializationSequenceStep(2, 10, 12);
		private final @NonNull RTSerializationSequenceStep _243 // V02*steps-10..14
									= new RTSerializationSequenceStep(2, 10, 14);
		private final @NonNull RTSerializationSequenceStep _244 // V02*steps-6..11
									= new RTSerializationSequenceStep(2, 6, 11);
		private final @NonNull RTSerializationSequenceStep _245 // V02*steps-6..8
									= new RTSerializationSequenceStep(2, 6, 8);
		private final @NonNull RTSerializationSequenceStep _246 // V02*steps-6..9
									= new RTSerializationSequenceStep(2, 6, 9);
		private final @NonNull RTSerializationSequenceStep _247 // V02*steps-7..9
									= new RTSerializationSequenceStep(2, 7, 9);
		private final @NonNull RTSerializationSequenceStep _248 // V02*steps-8..10
									= new RTSerializationSequenceStep(2, 8, 10);
		private final @NonNull RTSerializationSequenceStep _249 // V02*steps-8..11
									= new RTSerializationSequenceStep(2, 8, 11);
		private final @NonNull RTSerializationSequenceStep _250 // V02*steps-8..12
									= new RTSerializationSequenceStep(2, 8, 12);
		private final @NonNull RTSerializationSequenceStep _251 // V02*steps-8..9
									= new RTSerializationSequenceStep(2, 8, 9);
		private final @NonNull RTSerializationSequenceStep _252 // V02*steps-9..11
									= new RTSerializationSequenceStep(2, 9, 11);
		private final @NonNull RTSerializationLiteralStep _253 // V03*'serializable'
									= new RTSerializationLiteralStep(3, "serializable");
		private final @NonNull RTSerializationAssignedRuleCallStep _254 // V03*ConstraintCS::ownedSpecification=92
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 92 /* SpecificationCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _255 // V03*ModelElementCS::ownedAnnotations+=2
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _256 // V03*PackageOwnerCS::ownedPackages+=71
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 71 /* PackageCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _257 // V03*TypedRefCS::ownedMultiplicity=56
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 56 /* MultiplicityCS */);
		private final @NonNull RTSerializationSequenceStep _258 // V03*steps-10..12
									= new RTSerializationSequenceStep(3, 10, 12);
		private final @NonNull RTSerializationSequenceStep _259 // V03*steps-11..14
									= new RTSerializationSequenceStep(3, 11, 14);
		private final @NonNull RTSerializationSequenceStep _260 // V03*steps-12..13
									= new RTSerializationSequenceStep(3, 12, 13);
		private final @NonNull RTSerializationSequenceStep _261 // V03*steps-12..14
									= new RTSerializationSequenceStep(3, 12, 14);
		private final @NonNull RTSerializationSequenceStep _262 // V03*steps-12..16
									= new RTSerializationSequenceStep(3, 12, 16);
		private final @NonNull RTSerializationSequenceStep _263 // V03*steps-13..17
									= new RTSerializationSequenceStep(3, 13, 17);
		private final @NonNull RTSerializationSequenceStep _264 // V03*steps-6..8
									= new RTSerializationSequenceStep(3, 6, 8);
		private final @NonNull RTSerializationSequenceStep _265 // V03*steps-9..11
									= new RTSerializationSequenceStep(3, 9, 11);
		private final @NonNull RTSerializationSequenceStep _266 // V03*steps-9..12
									= new RTSerializationSequenceStep(3, 9, 12);
		private final @NonNull RTSerializationLiteralStep _267 // V04*'serializable'
									= new RTSerializationLiteralStep(4, "serializable");
		private final @NonNull RTSerializationAssignedRuleCallStep _268 // V04*AnnotationCS::ownedContents+=53
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, 53 /* ModelElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _269 // V04*EnumerationCS::ownedLiterals+=22
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, 22 /* EnumerationLiteralCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _270 // V04*ModelElementCS::ownedAnnotations+=2
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _271 // V04*PackageCS::ownedClasses+=6
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 6 /* ClassCS */);
		private final @NonNull RTSerializationSequenceStep _272 // V04*steps-12..14
									= new RTSerializationSequenceStep(4, 12, 14);
		private final @NonNull RTSerializationSequenceStep _273 // V04*steps-14..15
									= new RTSerializationSequenceStep(4, 14, 15);
		private final @NonNull RTSerializationSequenceStep _274 // V04*steps-14..16
									= new RTSerializationSequenceStep(4, 14, 16);
		private final @NonNull RTSerializationSequenceStep _275 // V04*steps-15..16
									= new RTSerializationSequenceStep(4, 15, 16);
		private final @NonNull RTSerializationSequenceStep _276 // V04*steps-15..19
									= new RTSerializationSequenceStep(4, 15, 19);
		private final @NonNull RTSerializationSequenceStep _277 // V04*steps-15..20
									= new RTSerializationSequenceStep(4, 15, 20);
		private final @NonNull RTSerializationAssignedRuleCallStep _278 // V05*AnnotationCS::ownedReferences+=54
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, 54 /* ModelElementRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _279 // V05*ClassCS::ownedConstraints+=41
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 41 /* InvariantConstraintCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _280 // V05*EnumerationCS::ownedLiterals+=22
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, 22 /* EnumerationLiteralCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _281 // V05*ModelElementCS::ownedAnnotations+=2
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull RTSerializationSequenceStep _282 // V05*steps-15..18
									= new RTSerializationSequenceStep(5, 15, 18);
		private final @NonNull RTSerializationSequenceStep _283 // V05*steps-17..18
									= new RTSerializationSequenceStep(5, 17, 18);
		private final @NonNull RTSerializationSequenceStep _284 // V05*steps-17..21
									= new RTSerializationSequenceStep(5, 17, 21);
		private final @NonNull RTSerializationSequenceStep _285 // V05*steps-17..22
									= new RTSerializationSequenceStep(5, 17, 22);
		private final @NonNull RTSerializationSequenceStep _286 // V05*steps-18..20
									= new RTSerializationSequenceStep(5, 18, 20);
		private final @NonNull RTSerializationLiteralStep _287 // V06*'interface'
									= new RTSerializationLiteralStep(6, "interface");
		private final @NonNull RTSerializationAssignedRuleCallStep _288 // V06*ClassCS::ownedConstraints+=41
									= new RTSerializationAssignedRuleCallStep(6, BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 41 /* InvariantConstraintCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _289 // V06*ModelElementCS::ownedAnnotations+=2
									= new RTSerializationAssignedRuleCallStep(6, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _290 // V06*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new RTSerializationAssignedRuleCallStep(6, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull RTSerializationSequenceStep _291 // V06*steps-19..23
									= new RTSerializationSequenceStep(6, 19, 23);
		private final @NonNull RTSerializationSequenceStep _292 // V06*steps-20..22
									= new RTSerializationSequenceStep(6, 20, 22);
		private final @NonNull RTSerializationSequenceStep _293 // V06*steps-20..26
									= new RTSerializationSequenceStep(6, 20, 26);
		private final @NonNull RTSerializationSequenceStep _294 // V06*steps-21..25
									= new RTSerializationSequenceStep(6, 21, 25);
		private final @NonNull RTSerializationAssignedRuleCallStep _295 // V07*ModelElementCS::ownedAnnotations+=2
									= new RTSerializationAssignedRuleCallStep(7, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _296 // V07*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new RTSerializationAssignedRuleCallStep(7, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull RTSerializationSequenceStep _297 // V07*steps-22..26
									= new RTSerializationSequenceStep(7, 22, 26);
		private final @NonNull RTSerializationSequenceStep _298 // V07*steps-22..28
									= new RTSerializationSequenceStep(7, 22, 28);
		private final @NonNull RTSerializationSequenceStep _299 // V07*steps-23..24
									= new RTSerializationSequenceStep(7, 23, 24);
		private final @NonNull RTSerializationSequenceStep _300 // V07*steps-23..25
									= new RTSerializationSequenceStep(7, 23, 25);
		private final @NonNull RTSerializationSequenceStep _301 // V07*steps-23..27
									= new RTSerializationSequenceStep(7, 23, 27);
		private final @NonNull RTSerializationAssignedRuleCallStep _302 // V08*ModelElementCS::ownedAnnotations+=2
									= new RTSerializationAssignedRuleCallStep(8, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _303 // V08*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new RTSerializationAssignedRuleCallStep(8, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _304 // V08*StructuredClassCS::ownedOperations+=70
									= new RTSerializationAssignedRuleCallStep(8, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 70 /* OperationCS */);
		private final @NonNull RTSerializationSequenceStep _305 // V08*steps-24..28
									= new RTSerializationSequenceStep(8, 24, 28);
		private final @NonNull RTSerializationSequenceStep _306 // V08*steps-25..26
									= new RTSerializationSequenceStep(8, 25, 26);
		private final @NonNull RTSerializationSequenceStep _307 // V08*steps-25..27
									= new RTSerializationSequenceStep(8, 25, 27);
		private final @NonNull RTSerializationSequenceStep _308 // V08*steps-27..31
									= new RTSerializationSequenceStep(8, 27, 31);
		private final @NonNull RTSerializationAssignedRuleCallStep _309 // V09*ModelElementCS::ownedAnnotations+=2
									= new RTSerializationAssignedRuleCallStep(9, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _310 // V09*OperationCS::ownedPreconditions+=76
									= new RTSerializationAssignedRuleCallStep(9, BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 76 /* PreconditionConstraintCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _311 // V09*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new RTSerializationAssignedRuleCallStep(9, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _312 // V09*StructuredClassCS::ownedProperties+=96
									= new RTSerializationAssignedRuleCallStep(9, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 96 /* StructuralFeatureCS */);
		private final @NonNull RTSerializationSequenceStep _313 // V09*steps-29..33
									= new RTSerializationSequenceStep(9, 29, 33);
		private final @NonNull RTSerializationAssignedRuleCallStep _314 // V10*ClassCS::ownedConstraints+=41
									= new RTSerializationAssignedRuleCallStep(10, BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 41 /* InvariantConstraintCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _315 // V10*OperationCS::ownedPreconditions+=76
									= new RTSerializationAssignedRuleCallStep(10, BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 76 /* PreconditionConstraintCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _316 // V10*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new RTSerializationAssignedRuleCallStep(10, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull RTSerializationSequenceStep _317 // V10*steps-29..33
									= new RTSerializationSequenceStep(10, 29, 33);
		private final @NonNull RTSerializationSequenceStep _318 // V10*steps-32..36
									= new RTSerializationSequenceStep(10, 32, 36);
		private final @NonNull RTSerializationAssignedRuleCallStep _319 // V11*OperationCS::ownedBodyExpressions+=92
									= new RTSerializationAssignedRuleCallStep(11, BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _320 // V11*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new RTSerializationAssignedRuleCallStep(11, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull RTSerializationSequenceStep _321 // V11*steps-31..35
									= new RTSerializationSequenceStep(11, 31, 35);
		private final @NonNull RTSerializationSequenceStep _322 // V11*steps-34..38
									= new RTSerializationSequenceStep(11, 34, 38);
		private final @NonNull RTSerializationAssignedRuleCallStep _323 // V12*OperationCS::ownedBodyExpressions+=92
									= new RTSerializationAssignedRuleCallStep(12, BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _324 // V12*OperationCS::ownedPostconditions+=75
									= new RTSerializationAssignedRuleCallStep(12, BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 75 /* PostconditionConstraintCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _325 // V12*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new RTSerializationAssignedRuleCallStep(12, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull RTSerializationSequenceStep _326 // V12*steps-37..39
									= new RTSerializationSequenceStep(12, 37, 39);
		private final @NonNull RTSerializationAssignedRuleCallStep _327 // V13*OperationCS::ownedPostconditions+=75
									= new RTSerializationAssignedRuleCallStep(13, BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 75 /* PostconditionConstraintCS */);
		private final @NonNull RTSerializationSequenceStep _328 // V13*steps-39..41
									= new RTSerializationSequenceStep(13, 39, 41);
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
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _02 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			null,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _03 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _04 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null
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
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _07 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _08 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _09 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _10 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _11 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _12 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _13 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _14 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _15 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _16 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _17 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _18 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _19 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _20 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _21 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _22 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _23 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._4 /* «! » «value» */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _24 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _25 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _26 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _27 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _28 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _29 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _30 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			null,
			null,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _31 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _32 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _33 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _34 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _35 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _36 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _37 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _38 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _39 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _40 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _41 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._2 /* «! » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _42 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _43 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _44 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _45 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _46 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _47 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _48 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
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
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _50 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _51 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _52 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _53 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _54 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _55 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _56 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _57 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _58 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
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
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _59 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
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
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _60 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _61 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _62 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _63 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._3 /* «! » «value» «?\n» */,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _64 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _65 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _66 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _67 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _68 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _69 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _70 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _71 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _72 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _73 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._2 /* «! » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._3 /* «! » «value» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */,
			null,
			null,
			ss._3 /* «! » «value» «?\n» */,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _74 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _75 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _76 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _77 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _78 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _79 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._8 /* «½\n» «value» «½\n» */,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _80 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _81 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _82 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _83 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _84 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _85 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _86 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _87 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _88 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _89 = new @NonNull Segment @NonNull [] @Nullable [] {
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
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _90 = new @NonNull Segment @NonNull [] @Nullable [] {
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
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _91 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _92 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._2 /* «! » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._3 /* «! » «value» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */,
			null,
			null,
			ss._3 /* «! » «value» «?\n» */,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _93 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _94 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._5 /* «-» «? » «value» «?\n» */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _95 = new @NonNull Segment @NonNull [] @Nullable [] {
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
					sr1._096 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._098 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
					sr1._099 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._097 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._113 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr2._151 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */,
					sr2._150 /* { 'sysml' ownedDetails+=DetailCS ';' } */
				},
				iv._45); /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
		private final @NonNull ParserRuleValue _003 // AttributeCS
			= new ParserRuleValue(3, "AttributeCS",
				new @NonNull SerializationRule [] {
					sr1._101 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._102 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._100 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._103 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._104 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
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
					sr1._107 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._106 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._111 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._110 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._108 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._109 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._117 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._114 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._116 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._118 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._119 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._115 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._149 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr2._148 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				iv._43); /* ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS */
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
					sr1._107 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._106 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._111 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._110 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._108 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
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
					sr1._117 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._114 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._116 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._118 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._119 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._115 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _022 // EnumerationLiteralCS
			= new ParserRuleValue(22, "EnumerationLiteralCS",
				new @NonNull SerializationRule [] {
					sr1._123 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._122 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr1._121 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._120 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
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
					sr0._028 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._037 /* '*' */,
					sr0._035 /* 'invalid' */,
					sr0._034 /* 'null' */,
					sr0._040 /* 'self' */,
					sr0._031 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._027 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._033 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._030 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._024 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._036 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._032 /* symbol=NUMBER_LITERAL */,
					sr0._038 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._026 /* segments+=StringLiteral[+] */,
					sr0._025 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._029 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._044 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._066 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._71); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
					sr1._127 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */,
					sr1._126 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' } */
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
					sr1._101 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._102 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._100 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._103 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._104 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._105 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._107 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._106 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._111 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._110 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._108 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._109 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._117 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._114 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._116 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._118 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._119 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._115 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._123 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._122 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr1._121 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._120 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr2._130 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._131 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._133 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._132 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._129 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._134 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._135 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
					sr2._136 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */,
					sr2._144 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._143 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._142 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._140 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._145 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._141 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._149 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr2._148 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				iv._44); /* AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */
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
					sr0._004 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					sr0._006 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					sr0._002 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */,
					sr0._005 /* { '[' stringBounds={'*|+|?'} ']' } */,
					sr0._007 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					sr0._003 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */
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
					sr0._055 /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._053 /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._054 /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _061 // NavigatingArgExpCS
			= new ParserRuleValue(61, "NavigatingArgExpCS",
				new @NonNull SerializationRule [] {
					sr0._039 /* symbol={'false|true'} */,
					sr0._028 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._037 /* '*' */,
					sr0._035 /* 'invalid' */,
					sr0._034 /* 'null' */,
					sr0._040 /* 'self' */,
					sr0._031 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._027 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._033 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._030 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._024 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._036 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._032 /* symbol=NUMBER_LITERAL */,
					sr0._038 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._026 /* segments+=StringLiteral[+] */,
					sr0._025 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._029 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._044 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._066 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._72); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
					sr0._060 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._057 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
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
					sr2._131 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._133 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._132 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._129 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
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
					sr0._044 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._066 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._33); /* LetExpCS|PrefixedLetExpCS */
		private final @NonNull ParserRuleValue _078 // PrefixedPrimaryExpCS
			= new ParserRuleValue(78, "PrefixedPrimaryExpCS",
				new @NonNull SerializationRule [] {
					sr0._015 /* symbol={'false|true'} */,
					sr0._017 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._041 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._042 /* 'invalid' */,
					sr0._043 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._046 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._050 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._062 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._063 /* 'null' */,
					sr1._064 /* symbol=NUMBER_LITERAL */,
					sr1._067 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr1._070 /* 'self' */,
					sr1._075 /* segments+=StringLiteral[+] */,
					sr1._076 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._086 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._095 /* '*' */
				},
				iv._69); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _079 // PrimaryExpCS
			= new ParserRuleValue(79, "PrimaryExpCS",
				new @NonNull SerializationRule [] {
					sr0._015 /* symbol={'false|true'} */,
					sr0._017 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._041 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._042 /* 'invalid' */,
					sr0._043 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._046 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._050 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._062 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._063 /* 'null' */,
					sr1._064 /* symbol=NUMBER_LITERAL */,
					sr1._070 /* 'self' */,
					sr1._075 /* segments+=StringLiteral[+] */,
					sr1._076 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._086 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._095 /* '*' */
				},
				iv._68); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
				iv._67); /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
					sr2._143 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._142 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._140 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._145 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
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
					sr1._102 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._100 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._103 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._104 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._105 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr2._144 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._143 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._142 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._140 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._145 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._141 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
				iv._42); /* AttributeCS|ReferenceCS|StructuralFeatureCS */
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
					sr2._151 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */,
					sr2._150 /* { 'sysml' ownedDetails+=DetailCS ';' } */
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
					sr1._085 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._084 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._083 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._081 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._080 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._57); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull ParserRuleValue _108 // TypeExpWithoutMultiplicityCS
			= new ParserRuleValue(108, "TypeExpWithoutMultiplicityCS",
				new @NonNull SerializationRule [] {
					sr0._020 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
					sr0._021 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._048 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._068 /* name=PrimitiveTypeIdentifier */,
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._091 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				iv._56); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull DataTypeRuleValue _109 // TypeIdentifier
			= new DataTypeRuleValue(109, "TypeIdentifier");
		private final @NonNull ParserRuleValue _110 // TypeLiteralCS
			= new ParserRuleValue(110, "TypeLiteralCS",
				new @NonNull SerializationRule [] {
					sr0._021 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._048 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._068 /* name=PrimitiveTypeIdentifier */,
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._53); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */
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
					sr1._088 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._089 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._090 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._55); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
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
					sr0._014 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */,
					sr0._021 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._048 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._068 /* name=PrimitiveTypeIdentifier */,
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr2._163 /* ownedPathName=PathNameCS */,
					sr2._164 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr2._162 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				},
				iv._75); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
		private final @NonNull ParserRuleValue _116 // TypedMultiplicityRefCS
			= new ParserRuleValue(116, "TypedMultiplicityRefCS",
				new @NonNull SerializationRule [] {
					sr2._156 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._157 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._155 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._160 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._159 /* { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._158 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._161 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._63); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
		private final @NonNull ParserRuleValue _117 // TypedRefCS
			= new ParserRuleValue(117, "TypedRefCS",
				new @NonNull SerializationRule [] {
					sr0._021 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._048 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._068 /* name=PrimitiveTypeIdentifier */,
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr2._163 /* ownedPathName=PathNameCS */,
					sr2._164 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr2._162 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				},
				iv._62); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
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
	private class _EClassValues
	{
		private final @NonNull EClassValue _00 // AnnotationCS
			= new EClassValue(BaseCSPackage.Literals.ANNOTATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._096, sl._70) /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._098, sl._71) /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
					new SerializationRule_SegmentsList(sr1._099, sl._71) /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._097, sl._71) /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
						iv._44) /* AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						iv._6) /* DetailCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
						iv._18) /* ModelElementRefCS */
				}
			);
		private final @NonNull EClassValue _01 // AttributeCS
			= new EClassValue(BaseCSPackage.Literals.ATTRIBUTE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._101, sl._74) /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._100, sl._93) /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._104, sl._93) /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._102, sl._75) /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._103, sl._94) /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._105, sl._94) /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						iv._38) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._63) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _02 // BooleanLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._015, sl._29) /* symbol={'false|true'} */,
					new SerializationRule_SegmentsList(sr0._039, sl._29) /* symbol={'false|true'} */
				}, null
			);
		private final @NonNull EClassValue _03 // CollectionLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._017, sl._34) /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					new SerializationRule_SegmentsList(sr0._028, sl._34) /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						iv._3) /* CollectionLiteralPartCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						iv._4) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassValue _04 // CollectionLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._018, sl._29) /* ownedExpression=PatternExpCS */,
					new SerializationRule_SegmentsList(sr0._019, sl._31) /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						iv._73) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _05 // CollectionPatternCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._020, sl._08) /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
					new SerializationRule_SegmentsList(sr1._084, sl._09) /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						iv._28) /* PatternExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						iv._4) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassValue _06 // CollectionTypeCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._021, sl._22) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._083, sl._23) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._089, sl._23) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr2._158, sl._23) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						iv._56) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _07 // ContextCS
			= new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._049, sl._29) /* ownedExpression=ExpCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _08 // CurlyBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._022, sl._18) /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						iv._37) /* ShadowPartCS */
				}
			);
		private final @NonNull EClassValue _09 // DataTypeCS
			= new EClassValue(BaseCSPackage.Literals.DATA_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._111, sl._85) /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._107, sl._87) /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._108, sl._87) /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._110, sl._86) /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._106, sl._88) /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._109, sl._88) /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						iv._13) /* InvariantConstraintCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._48) /* TemplateSignatureCS */
				}
			);
		private final @NonNull EClassValue _10 // DetailCS
			= new EClassValue(BaseCSPackage.Literals.DETAIL_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._112, sl._81) /* { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
				}, null
			);
		private final @NonNull EClassValue _11 // DocumentationCS
			= new EClassValue(BaseCSPackage.Literals.DOCUMENTATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._113, sl._70) /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						iv._6) /* DetailCS */
				}
			);
		private final @NonNull EClassValue _12 // EnumerationCS
			= new EClassValue(BaseCSPackage.Literals.ENUMERATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._116, sl._66) /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._117, sl._68) /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._119, sl._68) /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._118, sl._67) /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._114, sl._69) /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._115, sl._69) /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						iv._13) /* InvariantConstraintCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
						iv._8) /* EnumerationLiteralCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._48) /* TemplateSignatureCS */
				}
			);
		private final @NonNull EClassValue _13 // EnumerationLiteralCS
			= new EClassValue(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._121, sl._60) /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._123, sl._78) /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._120, sl._61) /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._122, sl._80) /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
				}
			);
		private final @NonNull EClassValue _14 // ExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._037, sl._29) /* '*' */,
					new SerializationRule_SegmentsList(sr0._035, sl._29) /* 'invalid' */,
					new SerializationRule_SegmentsList(sr0._034, sl._29) /* 'null' */,
					new SerializationRule_SegmentsList(sr0._040, sl._29) /* 'self' */
				}, null
			);
		private final @NonNull EClassValue _15 // ExpSpecificationCS
			= new EClassValue(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._146, sl._29) /* exprString=UNQUOTED_STRING */,
					new SerializationRule_SegmentsList(sr2._147, sl._29) /* ownedExpression=ExpCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _16 // IfExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._031, sl._55) /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					new SerializationRule_SegmentsList(sr0._041, sl._55) /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						iv._73) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						iv._7) /* ElseIfThenExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _17 // IfThenExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._023, sl._52) /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _18 // ImplicitOppositeCS
			= new EClassValue(BaseCSPackage.Literals.IMPLICIT_OPPOSITE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._124, sl._82) /* { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._63) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _19 // ImportCS
			= new EClassValue(BaseCSPackage.Literals.IMPORT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._125, sl._33) /* { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
						iv._66) /* URIPathNameCS */
				}
			);
		private final @NonNull EClassValue _20 // InfixExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._027, sl._35) /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
						iv._69) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _21 // InvalidLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._042, sl._29) /* 'invalid' */
				}, null
			);
		private final @NonNull EClassValue _22 // LambdaLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._033, sl._64) /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					new SerializationRule_SegmentsList(sr0._043, sl._64) /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _23 // LetExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._044, sl._48) /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						iv._14) /* LetVariableCS */
				}
			);
		private final @NonNull EClassValue _24 // LetVariableCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._045, sl._50) /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._35) /* RoundBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _25 // MapLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._030, sl._34) /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					new SerializationRule_SegmentsList(sr0._046, sl._34) /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						iv._15) /* MapLiteralPartCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						iv._16) /* MapTypeCS */
				}
			);
		private final @NonNull EClassValue _26 // MapLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._047, sl._35) /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _27 // MapTypeCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._048, sl._24) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._081, sl._25) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._090, sl._25) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr2._161, sl._25) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						iv._57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						iv._57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _28 // ModelElementRefCS
			= new EClassValue(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._128, sl._26) /* { 'reference' ownedPathName=PathNameCS ';' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
						iv._27) /* PathNameCS */
				}
			);
		private final @NonNull EClassValue _29 // MultiplicityBoundsCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._001, sl._28) /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */,
					new SerializationRule_SegmentsList(sr0._004, sl._14) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._006, sl._15) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._002, sl._15) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, null
			);
		private final @NonNull EClassValue _30 // MultiplicityStringCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._005, sl._16) /* { '[' stringBounds={'*|+|?'} ']' } */,
					new SerializationRule_SegmentsList(sr0._007, sl._17) /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._003, sl._17) /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._008, sl._95) /* stringBounds={'*|+|?'} */
				}, null
			);
		private final @NonNull EClassValue _31 // NameExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._024, sl._30) /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					new SerializationRule_SegmentsList(sr0._050, sl._30) /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._5) /* CurlyBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._27) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._35) /* RoundBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						iv._39) /* SquareBracketedClauseCS */
				}
			);
		private final @NonNull EClassValue _32 // NavigatingArgCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._052, sl._29) /* ownedNameExpression=NavigatingArgExpCS */,
					new SerializationRule_SegmentsList(sr0._051, sl._46) /* { ':' ownedType=TypeExpCS } */,
					new SerializationRule_SegmentsList(sr0._053, sl._37) /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._055, sl._38) /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._054, sl._32) /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					new SerializationRule_SegmentsList(sr0._056, sl._49) /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */,
					new SerializationRule_SegmentsList(sr0._058, sl._41) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					new SerializationRule_SegmentsList(sr0._057, sl._43) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._060, sl._44) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._059, sl._42) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					new SerializationRule_SegmentsList(sr0._061, sl._45) /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						iv._2) /* CoIteratorVariableCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						iv._72) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						iv._57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _33 // NestedExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._036, sl._40) /* { '(' ownedExpression=ExpCS ')' } */,
					new SerializationRule_SegmentsList(sr0._062, sl._40) /* { '(' ownedExpression=ExpCS ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _34 // NullLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._063, sl._29) /* 'null' */
				}, null
			);
		private final @NonNull EClassValue _35 // NumberLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._032, sl._29) /* symbol=NUMBER_LITERAL */,
					new SerializationRule_SegmentsList(sr1._064, sl._29) /* symbol=NUMBER_LITERAL */
				}, null
			);
		private final @NonNull EClassValue _36 // OCLinEcoreConstraintCS
			= new EClassValue(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._126, sl._77) /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._127, sl._76) /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._138, sl._56) /* { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._139, sl._56) /* { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						iv._38) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						iv._38) /* SpecificationCS */
				}
			);
		private final @NonNull EClassValue _37 // OperationCS
			= new EClassValue(BaseCSPackage.Literals.OPERATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._130, sl._58) /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._133, sl._89) /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._129, sl._89) /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._131, sl._59) /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr2._132, sl._90) /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr2._134, sl._90) /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
						iv._38) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
						iv._62) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						iv._26) /* ParameterCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						iv._30) /* PostconditionConstraintCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						iv._31) /* PreconditionConstraintCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._48) /* TemplateSignatureCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._63) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _38 // PackageCS
			= new EClassValue(BaseCSPackage.Literals.PACKAGE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._135, sl._78) /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._136, sl._79) /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
						iv._43) /* ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						iv._25) /* PackageCS */
				}
			);
		private final @NonNull EClassValue _39 // ParameterCS
			= new EClassValue(BaseCSPackage.Literals.PARAMETER_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._137, sl._53) /* { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._63) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _40 // PathElementCS
			= new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._000, sl._95) /* referredElement=UnrestrictedName */,
					new SerializationRule_SegmentsList(sr0._009, sl._95) /* referredElement=UnreservedName */,
					new SerializationRule_SegmentsList(sr1._093, sl._95) /* referredElement=UnrestrictedName */
				}, null
			);
		private final @NonNull EClassValue _41 // PathElementWithURICS
			= new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._092, sl._95) /* referredElement=URI */
				}, null
			);
		private final @NonNull EClassValue _42 // PathNameCS
			= new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._010, sl._04) /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */,
					new SerializationRule_SegmentsList(sr1._073, sl._00) /* ownedPathElements+=FirstPathElementCS */,
					new SerializationRule_SegmentsList(sr1._094, sl._04) /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						iv._65) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
				}
			);
		private final @NonNull EClassValue _43 // PatternExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._065, sl._65) /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
						iv._57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _44 // PrefixExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._038, sl._46) /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					new SerializationRule_SegmentsList(sr1._066, sl._46) /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					new SerializationRule_SegmentsList(sr1._067, sl._46) /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._70) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _45 // PrimitiveTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._068, sl._95) /* name=PrimitiveTypeIdentifier */,
					new SerializationRule_SegmentsList(sr1._082, sl._19) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._087, sl._19) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr2._156, sl._19) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassValue _46 // ReferenceCS
			= new EClassValue(BaseCSPackage.Literals.REFERENCE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._144, sl._72) /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._142, sl._91) /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._145, sl._91) /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._143, sl._73) /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					new SerializationRule_SegmentsList(sr2._140, sl._92) /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					new SerializationRule_SegmentsList(sr2._141, sl._92) /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
						iv._38) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
						iv._11) /* ImplicitOppositeCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._63) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _47 // RoundBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._069, sl._12) /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						iv._22) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
				}
			);
		private final @NonNull EClassValue _48 // SelfExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._070, sl._29) /* 'self' */
				}, null
			);
		private final @NonNull EClassValue _49 // ShadowPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._072, sl._29) /* ownedInitExpression=StringLiteralExpCS */,
					new SerializationRule_SegmentsList(sr1._071, sl._65) /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						iv._73) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _50 // SquareBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._074, sl._13) /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _51 // StringLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._026, sl._29) /* segments+=StringLiteral[+] */,
					new SerializationRule_SegmentsList(sr1._075, sl._29) /* segments+=StringLiteral[+] */
				}, null
			);
		private final @NonNull EClassValue _52 // StructuredClassCS
			= new EClassValue(BaseCSPackage.Literals.STRUCTURED_CLASS_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._149, sl._83) /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._148, sl._84) /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						iv._13) /* InvariantConstraintCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
						iv._24) /* OperationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
						iv._42) /* AttributeCS|ReferenceCS|StructuralFeatureCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._48) /* TemplateSignatureCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
						iv._62) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _53 // SysMLCS
			= new EClassValue(OCLinEcoreCSPackage.Literals.SYS_MLCS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._150, sl._51) /* { 'sysml' ownedDetails+=DetailCS ';' } */,
					new SerializationRule_SegmentsList(sr2._151, sl._63) /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						iv._6) /* DetailCS */
				}
			);
		private final @NonNull EClassValue _54 // TemplateBindingCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._011, sl._05) /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						iv._47) /* TemplateParameterSubstitutionCS */
				}
			);
		private final @NonNull EClassValue _55 // TemplateParameterSubstitutionCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._012, sl._29) /* ownedActualParameter=TypeRefCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						iv._75) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
				}
			);
		private final @NonNull EClassValue _56 // TemplateSignatureCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._152, sl._39) /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */,
					new SerializationRule_SegmentsList(sr2._153, sl._47) /* { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						iv._58) /* TypeParameterCS */
				}
			);
		private final @NonNull EClassValue _57 // TopLevelCS
			= new EClassValue(OCLinEcoreCSPackage.Literals.TOP_LEVEL_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._154, sl._36) /* { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
						iv._12) /* ImportCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						iv._25) /* PackageCS */
				}
			);
		private final @NonNull EClassValue _58 // TupleLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._025, sl._62) /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._076, sl._62) /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						iv._49) /* TupleLiteralPartCS */
				}
			);
		private final @NonNull EClassValue _59 // TupleLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._077, sl._57) /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _60 // TuplePartCS
			= new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._078, sl._65) /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _61 // TupleTypeCS
			= new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._079, sl._20) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._085, sl._21) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._088, sl._21) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr2._157, sl._21) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						iv._50) /* TuplePartCS */
				}
			);
		private final @NonNull EClassValue _62 // TypeLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._029, sl._29) /* ownedType=TypeLiteralWithMultiplicityCS */,
					new SerializationRule_SegmentsList(sr1._086, sl._29) /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						iv._55) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
				}
			);
		private final @NonNull EClassValue _63 // TypeNameExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._080, sl._03) /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._091, sl._02) /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._5) /* CurlyBracketedClauseCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._27) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						iv._71) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _64 // TypeParameterCS
			= new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._013, sl._54) /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						iv._62) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _65 // TypedTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._163, sl._00) /* ownedPathName=PathNameCS */,
					new SerializationRule_SegmentsList(sr2._164, sl._06) /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					new SerializationRule_SegmentsList(sr2._162, sl._10) /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
					new SerializationRule_SegmentsList(sr2._159, sl._01) /* { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr2._155, sl._07) /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr2._160, sl._11) /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						iv._46) /* TemplateBindingCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						iv._27) /* PathNameCS */
				}
			);
		private final @NonNull EClassValue _66 // UnlimitedNaturalLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._095, sl._29) /* '*' */
				}, null
			);
		private final @NonNull EClassValue _67 // VariableCS
			= new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._016, sl._52) /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._57) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _68 // WildcardTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._014, sl._27) /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						iv._62) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
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
				st._124 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._95,
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
				st._183 /* 1*steps-1..5 || «null» */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=43 || «? » «value» «? » */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'..' || «? » «value» «? » */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=120 || «? » «value» «? » */
			},
			sl._28,
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
				st._186 /* 1*steps-1..8 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=43 || «? » «value» «? » */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._008 /* 1*'..' || «? » «value» «? » */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=120 || «? » «value» «? » */,
				st._217 /* V01*'|1' || «? » «value» «? » */,
				st._022 /* 1*']' || «! » «value» */
			},
			sl._15,
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
				st._183 /* 1*steps-1..5 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._103 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._193 /* V00*'|1' || «? » «value» «? » */,
				st._022 /* 1*']' || «! » «value» */
			},
			sl._17,
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
				st._185 /* 1*steps-1..7 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=43 || «? » «value» «? » */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._008 /* 1*'..' || «? » «value» «? » */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=120 || «? » «value» «? » */,
				st._022 /* 1*']' || «! » «value» */
			},
			sl._14,
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
				st._180 /* 1*steps-1..4 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._103 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._022 /* 1*']' || «! » «value» */
			},
			sl._16,
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
				st._186 /* 1*steps-1..8 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=43 || «? » «value» «? » */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._008 /* 1*'..' || «? » «value» «? » */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=120 || «? » «value» «? » */,
				st._062 /* 1*'|?' || «? » «value» «? » */,
				st._022 /* 1*']' || «! » «value» */
			},
			sl._15,
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
				st._183 /* 1*steps-1..5 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._103 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._062 /* 1*'|?' || «? » «value» «? » */,
				st._022 /* 1*']' || «! » «value» */
			},
			sl._17,
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
				st._103 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */
			},
			sl._95,
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
				st._123 /* 1*PathElementCS::referredElement=UnreservedName || «? » «value» «? » */
			},
			sl._95,
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
				st._183 /* 1*steps-1..5 || «null» */,
				st._127 /* 1*PathNameCS::ownedPathElements+=31 || «null» */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._010 /* 1*'::' || «! » «value» «! » */,
				st._128 /* 1*PathNameCS::ownedPathElements+=67 || «null» */
			},
			sl._04,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._23) /* FirstPathElementCS|NextPathElementCS */
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
				st._184 /* 1*steps-1..6 || «null» */,
				st._143 /* 1*TemplateBindingCS::ownedSubstitutions+=100 || «null» */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._143 /* 1*TemplateBindingCS::ownedSubstitutions+=100 || «null» */,
				st._222 /* V01*TemplateBindingCS::ownedMultiplicity=56 || «null» */
			},
			sl._05,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					iv._47) /* TemplateParameterSubstitutionCS */,
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
				st._144 /* 1*TemplateParameterSubstitutionCS::ownedActualParameter=115 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					iv._59) /* TypeRefCS */
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
				st._186 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._208 /* V00*steps-3..8 || «null» */,
				st._035 /* 1*'extends' || «? » «value» «? » */,
				st._152 /* 1*TypeParameterCS::ownedExtends+=117 || «null» */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._002 /* 1*'&&' || «? » «value» «? » */,
				st._152 /* 1*TypeParameterCS::ownedExtends+=117 || «null» */
			},
			sl._54,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					iv._61) /* TypedRefCS */
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
				st._183 /* 1*steps-1..5 || «null» */,
				st._016 /* 1*'?' || «? » «value» «? » */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._035 /* 1*'extends' || «? » «value» «? » */,
				st._160 /* 1*WildcardTypeRefCS::ownedExtends=117 || «null» */
			},
			sl._27,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					iv._61) /* TypedRefCS */
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
				st._066 /* 1*BooleanLiteralExpCS::symbol || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._183 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._159 /* 1*VariableCS::ownedType=107 || «null» */
			},
			sl._52,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */
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
				st._187 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*CollectionLiteralExpCS::ownedType=11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._211 /* V00*steps-4..8 || «null» */,
				st._068 /* 1*CollectionLiteralExpCS::ownedParts+=9 || «null» */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._068 /* 1*CollectionLiteralExpCS::ownedParts+=9 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._34,
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
				st._071 /* 1*CollectionLiteralPartCS::ownedExpression=74 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					iv._28) /* PatternExpCS */
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
				st._183 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._070 /* 1*CollectionLiteralPartCS::ownedExpression=30 || «null» */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'..' || «? » «value» «? » */,
				st._072 /* 1*CollectionLiteralPartCS::ownedLastExpression=30 || «null» */
			},
			sl._31,
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
				st._162 /* 1*steps-1..11 || «null» */,
				st._074 /* 1*CollectionPatternCS::ownedType=11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._209 /* V00*steps-4..10 || «null» */,
				st._073 /* 1*CollectionPatternCS::ownedParts+=74 || «null» */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._073 /* 1*CollectionPatternCS::ownedParts+=74 || «null» */,
				st._006 /* 1*'++' || «? » «value» «? » */,
				st._075 /* 1*CollectionPatternCS::restVariableName=35 || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._08,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._28) /* PatternExpCS */,
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
				st._185 /* 1*steps-1..7 || «null» */,
				st._076 /* 1*CollectionTypeCS::name=12 || «? » «value» «? » */,
				st._207 /* V00*steps-3..7 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._077 /* 1*CollectionTypeCS::ownedType=108 || «null» */,
				st._219 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=56 || «null» */,
				st._004 /* 1*')' || «! » «value» */
			},
			sl._22,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._52) /* TypeExpWithoutMultiplicityCS */,
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
				st._186 /* 1*steps-1..8 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._207 /* V00*steps-3..7 || «null» */,
				st._080 /* 1*CurlyBracketedClauseCS::ownedParts+=90 || «null» */,
				st._228 /* V01*steps-5..7 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._080 /* 1*CurlyBracketedClauseCS::ownedParts+=90 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._18,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					iv._37) /* ShadowPartCS */
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
				st._183 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._032 /* 1*'elseif' || «? » «value» «? » */,
				st._087 /* 1*IfThenExpCS::ownedCondition=30 || «null» */,
				st._058 /* 1*'then' || «? » «value» «? » */,
				st._088 /* 1*IfThenExpCS::ownedThenExpression=30 || «null» */
			},
			sl._52,
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
				st._186 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._064 /* 1*AbstractNameExpCS::ownedPathName=73 || «null» */,
				st._194 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=93 || «null» */,
				st._218 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=84 || «null» */,
				st._237 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=13 || «null» */,
				st._264 /* V03*steps-6..8 || «null» */,
				st._017 /* 1*'@' || «? » «value» «? » */,
				st._051 /* 1*'pre' || «? » «value» «? » */
			},
			sl._30,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._08)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._35) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._39) /* SquareBracketedClauseCS */
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
				st._186 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._020 /* 1*'Tuple' || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._146 /* 1*TupleLiteralExpCS::ownedParts+=104 || «null» */,
				st._213 /* V00*steps-5..7 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._146 /* 1*TupleLiteralExpCS::ownedParts+=104 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._62,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._49) /* TupleLiteralPartCS */
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
				st._201 /* V00*StringLiteralExpCS::segments+=94 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._180 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._090 /* 1*InfixExpCS::ownedLeft=78 || «null» */,
				st._107 /* 1*NamedElementCS::name=4 || «? » «value» «? » */,
				st._117 /* 1*OperatorExpCS::ownedRight=30 || «null» */
			},
			sl._35,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					iv._34) /* PrefixedPrimaryExpCS */,
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
				st._187 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*CollectionLiteralExpCS::ownedType=11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._211 /* V00*steps-4..8 || «null» */,
				st._068 /* 1*CollectionLiteralExpCS::ownedParts+=9 || «null» */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._068 /* 1*CollectionLiteralExpCS::ownedParts+=9 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._34,
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
				st._148 /* 1*TypeLiteralExpCS::ownedType=112 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._54) /* TypeLiteralWithMultiplicityCS */
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
				st._187 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._095 /* 1*MapLiteralExpCS::ownedType=51 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._211 /* V00*steps-4..8 || «null» */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=50 || «null» */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=50 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._34,
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
				st._187 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._036 /* 1*'if' || «? » «value» «? » */,
				st._084 /* 1*IfExpCS::ownedCondition=30|74 || «null» */,
				st._058 /* 1*'then' || «? » «value» «? » */,
				st._086 /* 1*IfExpCS::ownedThenExpression=30 || «null» */,
				st._197 /* V00*IfExpCS::ownedIfThenExpressions+=20 || «null» */,
				st._031 /* 1*'else' || «? » «value» «? » */,
				st._085 /* 1*IfExpCS::ownedElseExpression=30 || «null» */,
				st._033 /* 1*'endif' || «? » «value» «? » */
			},
			sl._55,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._29) /* ExpCS|PatternExpCS */,
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
				st._114 /* 1*NumberLiteralExpCS::symbol=58 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._183 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._018 /* 1*'Lambda' || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._091 /* 1*LambdaLiteralExpCS::ownedExpressionCS=30 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._64,
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
				st._046 /* 1*'null' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._040 /* 1*'invalid' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._180 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._113 /* 1*NestedExpCS::ownedExpression=30 || «null» */,
				st._004 /* 1*')' || «! » «value» */
			},
			sl._40,
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
				st._005 /* 1*'*' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._177 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._104 /* 1*NamedElementCS::name=124 || «? » «value» «? » */,
				st._119 /* 1*OperatorExpCS::ownedRight=78 || «null» */
			},
			sl._46,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._34) /* PrefixedPrimaryExpCS */
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
				st._066 /* 1*BooleanLiteralExpCS::symbol || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._055 /* 1*'self' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._187 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._036 /* 1*'if' || «? » «value» «? » */,
				st._084 /* 1*IfExpCS::ownedCondition=30|74 || «null» */,
				st._058 /* 1*'then' || «? » «value» «? » */,
				st._086 /* 1*IfExpCS::ownedThenExpression=30 || «null» */,
				st._197 /* V00*IfExpCS::ownedIfThenExpressions+=20 || «null» */,
				st._031 /* 1*'else' || «? » «value» «? » */,
				st._085 /* 1*IfExpCS::ownedElseExpression=30 || «null» */,
				st._033 /* 1*'endif' || «? » «value» «? » */
			},
			sl._55,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._29) /* ExpCS|PatternExpCS */,
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
				st._040 /* 1*'invalid' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._183 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._018 /* 1*'Lambda' || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._091 /* 1*LambdaLiteralExpCS::ownedExpressionCS=30 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._64,
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
				st._186 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._043 /* 1*'let' || «? » «value» «? » */,
				st._093 /* 1*LetExpCS::ownedVariables+=46 || «null» */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._093 /* 1*LetExpCS::ownedVariables+=46 || «null» */,
				st._038 /* 1*'in' || «? » «value» «? » */,
				st._092 /* 1*LetExpCS::ownedInExpression=30 || «null» */
			},
			sl._48,
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
				st._186 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._198 /* V00*LetVariableCS::ownedRoundBracketedClause=84 || «null» */,
				st._226 /* V01*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._159 /* 1*VariableCS::ownedType=107 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._158 /* 1*VariableCS::ownedInitExpression=30 || «null» */
			},
			sl._50,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._35) /* RoundBracketedClauseCS */
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
				st._187 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._095 /* 1*MapLiteralExpCS::ownedType=51 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._211 /* V00*steps-4..8 || «null» */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=50 || «null» */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=50 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._34,
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
				st._180 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._096 /* 1*MapLiteralPartCS::ownedKey=30 || «null» */,
				st._013 /* 1*'<-' || «? » «value» «? » */,
				st._097 /* 1*MapLiteralPartCS::ownedValue=30 || «null» */
			},
			sl._35,
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
				st._186 /* 1*steps-1..8 || «null» */,
				st._019 /* 1*'Map' || «? » «value» «? » */,
				st._208 /* V00*steps-3..8 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._098 /* 1*MapTypeCS::ownedKeyType=107 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._099 /* 1*MapTypeCS::ownedValueType=107 || «null» */,
				st._004 /* 1*')' || «! » «value» */
			},
			sl._24,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._51) /* TypeExpCS */
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
				st._079 /* 1*ContextCS::ownedExpression=30 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._186 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._064 /* 1*AbstractNameExpCS::ownedPathName=73 || «null» */,
				st._194 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=93 || «null» */,
				st._218 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=84 || «null» */,
				st._237 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=13 || «null» */,
				st._264 /* V03*steps-6..8 || «null» */,
				st._017 /* 1*'@' || «? » «value» «? » */,
				st._051 /* 1*'pre' || «? » «value» «? » */
			},
			sl._30,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._08)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._35) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._39) /* SquareBracketedClauseCS */
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
				st._177 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedType=107 || «null» */
			},
			sl._46,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */
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
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._185 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._013 /* 1*'<-' || «? » «value» «? » */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=7 || «null» */,
				st._213 /* V00*steps-5..7 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._37,
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
				st._161 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedType=107 || «null» */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._013 /* 1*'<-' || «? » «value» «? » */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=7 || «null» */,
				st._038 /* 1*'in' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._32,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
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
				st._161 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedType=107 || «null» */,
				st._213 /* V00*steps-5..7 || «null» */,
				st._013 /* 1*'<-' || «? » «value» «? » */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=7 || «null» */,
				st._234 /* V01*steps-8..10 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._38,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
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
				st._187 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._061 /* 1*'|' || «? » «value» «? » */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._212 /* V00*steps-4..9 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedType=107 || «null» */,
				st._233 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._49,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._22)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
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
				st._186 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._013 /* 1*'<-' || «? » «value» «? » */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=7 || «null» */,
				st._215 /* V00*steps-6..8 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._43,
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
				st._177 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */
			},
			sl._41,
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
				st._162 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedType=107 || «null» */,
				st._233 /* V01*steps-7..9 || «null» */,
				st._013 /* 1*'<-' || «? » «value» «? » */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=7 || «null» */,
				st._038 /* 1*'in' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._42,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
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
				st._162 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedType=107 || «null» */,
				st._215 /* V00*steps-6..8 || «null» */,
				st._013 /* 1*'<-' || «? » «value» «? » */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=7 || «null» */,
				st._236 /* V01*steps-9..11 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._44,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
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
				st._187 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._212 /* V00*steps-4..9 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedType=107 || «null» */,
				st._233 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._45,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._07)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
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
				st._180 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._113 /* 1*NestedExpCS::ownedExpression=30 || «null» */,
				st._004 /* 1*')' || «! » «value» */
			},
			sl._40,
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
				st._046 /* 1*'null' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._114 /* 1*NumberLiteralExpCS::symbol=58 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._180 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._200 /* V00*PatternExpCS::patternVariableName=127 || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._129 /* 1*PatternExpCS::ownedPatternType=107 || «null» */
			},
			sl._65,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					iv._51) /* TypeExpCS */
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
				st._177 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._104 /* 1*NamedElementCS::name=124 || «? » «value» «? » */,
				st._118 /* 1*OperatorExpCS::ownedRight=77 || «null» */
			},
			sl._46,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._32) /* PrefixedLetExpCS */
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
				st._177 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._104 /* 1*NamedElementCS::name=124 || «? » «value» «? » */,
				st._119 /* 1*OperatorExpCS::ownedRight=78 || «null» */
			},
			sl._46,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._34) /* PrefixedPrimaryExpCS */
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
				st._130 /* 1*PrimitiveTypeRefCS::name=82 || «? » «value» «? » */
			},
			sl._95,
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
				st._184 /* 1*steps-1..6 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._135 /* 1*RoundBracketedClauseCS::ownedArguments+=60 || «null» */,
				st._221 /* V01*RoundBracketedClauseCS::ownedArguments+=62|63|64 || «null» */,
				st._004 /* 1*')' || «! » «value» */
			},
			sl._12,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					iv._22) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(60, MultiplicativeCardinality.ZERO_OR_ONE),
					new RuleIndex_MultiplicativeCardinality(63, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(64, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::SelfExpCS : 'self'
		private @NonNull SerializationRule _070 = new SerializationRule(89,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._055 /* 1*'self' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._180 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._138 /* 1*ShadowPartCS::referredProperty=UnrestrictedName || «? » «value» «? » */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._136 /* 1*ShadowPartCS::ownedInitExpression=30|74 || «null» */
			},
			sl._65,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._29) /* ExpCS|PatternExpCS */
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
				st._137 /* 1*ShadowPartCS::ownedInitExpression=95 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._40) /* StringLiteralExpCS */
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
				st._127 /* 1*PathNameCS::ownedPathElements+=31 || «null» */
			},
			sl._00,
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
				st._185 /* 1*steps-1..7 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._140 /* 1*SquareBracketedClauseCS::ownedTerms+=30 || «null» */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._140 /* 1*SquareBracketedClauseCS::ownedTerms+=30 || «null» */,
				st._022 /* 1*']' || «! » «value» */
			},
			sl._13,
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
				st._201 /* V00*StringLiteralExpCS::segments+=94 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._186 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._020 /* 1*'Tuple' || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._146 /* 1*TupleLiteralExpCS::ownedParts+=104 || «null» */,
				st._213 /* V00*steps-5..7 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._146 /* 1*TupleLiteralExpCS::ownedParts+=104 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._62,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._49) /* TupleLiteralPartCS */
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
				st._185 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._159 /* 1*VariableCS::ownedType=107 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._158 /* 1*VariableCS::ownedInitExpression=30 || «null» */
			},
			sl._57,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
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
				st._180 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=107 || «null» */
			},
			sl._65,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */
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
				st._161 /* 1*steps-1..10 || «null» */,
				st._020 /* 1*'Tuple' || «? » «value» «? » */,
				st._205 /* V00*steps-3..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._230 /* V01*steps-5..9 || «null» */,
				st._147 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._247 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._147 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._004 /* 1*')' || «! » «value» */
			},
			sl._20,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._10)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._50) /* TuplePartCS */
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
				st._187 /* 1*steps-1..9 || «null» */,
				st._150 /* 1*TypeNameExpCS::ownedPathName=73 || «null» */,
				st._208 /* V00*steps-3..8 || «null» */,
				st._149 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=13 || «null» */,
				st._229 /* V01*steps-5..8 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._151 /* 1*TypeNameExpCS::ownedPatternGuard=30 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._241 /* V02*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._03,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */,
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
				st._187 /* 1*steps-1..9 || «null» */,
				st._019 /* 1*'Map' || «? » «value» «? » */,
				st._208 /* V00*steps-3..8 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._098 /* 1*MapTypeCS::ownedKeyType=107 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._099 /* 1*MapTypeCS::ownedValueType=107 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._224 /* V01*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._25,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._51) /* TypeExpCS */,
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
				st._177 /* 1*steps-1..3 || «null» */,
				st._130 /* 1*PrimitiveTypeRefCS::name=82 || «? » «value» «? » */,
				st._203 /* V00*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._19,
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
				st._186 /* 1*steps-1..8 || «null» */,
				st._076 /* 1*CollectionTypeCS::name=12 || «? » «value» «? » */,
				st._207 /* V00*steps-3..7 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._077 /* 1*CollectionTypeCS::ownedType=108 || «null» */,
				st._219 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=56 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._241 /* V02*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._23,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._52) /* TypeExpWithoutMultiplicityCS */,
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
				st._163 /* 1*steps-1..12 || «null» */,
				st._074 /* 1*CollectionPatternCS::ownedType=11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._209 /* V00*steps-4..10 || «null» */,
				st._073 /* 1*CollectionPatternCS::ownedParts+=74 || «null» */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._073 /* 1*CollectionPatternCS::ownedParts+=74 || «null» */,
				st._006 /* 1*'++' || «? » «value» «? » */,
				st._075 /* 1*CollectionPatternCS::restVariableName=35 || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._241 /* V02*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._09,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._28) /* PatternExpCS */,
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
				st._162 /* 1*steps-1..11 || «null» */,
				st._020 /* 1*'Tuple' || «? » «value» «? » */,
				st._205 /* V00*steps-3..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._230 /* V01*steps-5..9 || «null» */,
				st._147 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._247 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._147 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._257 /* V03*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._21,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._10)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._50) /* TuplePartCS */,
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
				st._148 /* 1*TypeLiteralExpCS::ownedType=112 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._54) /* TypeLiteralWithMultiplicityCS */
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
				st._177 /* 1*steps-1..3 || «null» */,
				st._130 /* 1*PrimitiveTypeRefCS::name=82 || «? » «value» «? » */,
				st._203 /* V00*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._19,
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
				st._162 /* 1*steps-1..11 || «null» */,
				st._020 /* 1*'Tuple' || «? » «value» «? » */,
				st._205 /* V00*steps-3..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._230 /* V01*steps-5..9 || «null» */,
				st._147 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._247 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._147 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._257 /* V03*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._21,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._10)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._50) /* TuplePartCS */,
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
				st._186 /* 1*steps-1..8 || «null» */,
				st._076 /* 1*CollectionTypeCS::name=12 || «? » «value» «? » */,
				st._207 /* V00*steps-3..7 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._077 /* 1*CollectionTypeCS::ownedType=108 || «null» */,
				st._219 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=56 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._241 /* V02*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._23,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._52) /* TypeExpWithoutMultiplicityCS */,
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
				st._187 /* 1*steps-1..9 || «null» */,
				st._019 /* 1*'Map' || «? » «value» «? » */,
				st._208 /* V00*steps-3..8 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._098 /* 1*MapTypeCS::ownedKeyType=107 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._099 /* 1*MapTypeCS::ownedValueType=107 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._224 /* V01*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._25,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._51) /* TypeExpCS */,
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
				st._186 /* 1*steps-1..8 || «null» */,
				st._150 /* 1*TypeNameExpCS::ownedPathName=73 || «null» */,
				st._208 /* V00*steps-3..8 || «null» */,
				st._149 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=13 || «null» */,
				st._229 /* V01*steps-5..8 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._151 /* 1*TypeNameExpCS::ownedPatternGuard=30 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._02,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */,
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
				st._122 /* 1*PathElementCS::referredElement=URI || «? » «value» «? » */
			},
			sl._95,
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
				st._125 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._95,
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
				st._183 /* 1*steps-1..5 || «null» */,
				st._126 /* 1*PathNameCS::ownedPathElements+=122 || «null» */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._010 /* 1*'::' || «! » «value» «! » */,
				st._128 /* 1*PathNameCS::ownedPathElements+=67 || «null» */
			},
			sl._04,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._64) /* NextPathElementCS|URIFirstPathElementCS */
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
				st._005 /* 1*'*' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._162 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._023 /* 1*'annotation' || «? » «value» «? » */,
				st._199 /* V00*NamedElementCS::name=87|127 || «? » «value» «? » */,
				st._225 /* V01*steps-4..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._247 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._70,
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
				st._166 /* 1*steps-1..15 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._023 /* 1*'annotation' || «? » «value» «? » */,
				st._199 /* V00*NamedElementCS::name=87|127 || «? » «value» «? » */,
				st._225 /* V01*steps-4..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._247 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._255 /* V03*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._268 /* V04*AnnotationCS::ownedContents+=53 || «null» */,
				st._278 /* V05*AnnotationCS::ownedReferences+=54 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._71,
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
				st._166 /* 1*steps-1..15 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._023 /* 1*'annotation' || «? » «value» «? » */,
				st._199 /* V00*NamedElementCS::name=87|127 || «? » «value» «? » */,
				st._225 /* V01*steps-4..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._247 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._255 /* V03*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._268 /* V04*AnnotationCS::ownedContents+=53 || «null» */,
				st._278 /* V05*AnnotationCS::ownedReferences+=54 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._71,
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
				st._166 /* 1*steps-1..15 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._023 /* 1*'annotation' || «? » «value» «? » */,
				st._199 /* V00*NamedElementCS::name=87|127 || «? » «value» «? » */,
				st._225 /* V01*steps-4..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._247 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._255 /* V03*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._268 /* V04*AnnotationCS::ownedContents+=53 || «null» */,
				st._278 /* V05*AnnotationCS::ownedReferences+=54 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._71,
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
				st._168 /* 1*steps-1..17 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._028 /* 1*'definition' || «? » «value» «? » */,
				st._192 /* V00*'static' || «? » «value» «? » */,
				st._024 /* 1*'attribute' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._252 /* V02*steps-9..11 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._141 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._262 /* V03*steps-12..16 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._273 /* V04*steps-14..15 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._93,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._01,ev._13)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ZERO_OR_MORE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ONE)
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
				st._166 /* 1*steps-1..15 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._024 /* 1*'attribute' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._233 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._141 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._243 /* V02*steps-10..14 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._260 /* V03*steps-12..13 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._74,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
				st._174 /* 1*steps-1..27 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._024 /* 1*'attribute' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._233 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._141 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._243 /* V02*steps-10..14 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._260 /* V03*steps-12..13 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._270 /* V04*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._284 /* V05*steps-17..21 || «null» */,
				st._039 /* 1*'initial' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._290 /* V06*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._297 /* V07*steps-22..26 || «null» */,
				st._029 /* 1*'derivation' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._303 /* V08*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._75,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
				st._176 /* 1*steps-1..29 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._028 /* 1*'definition' || «? » «value» «? » */,
				st._192 /* V00*'static' || «? » «value» «? » */,
				st._024 /* 1*'attribute' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._252 /* V02*steps-9..11 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._141 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._262 /* V03*steps-12..16 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._273 /* V04*steps-14..15 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._281 /* V05*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._291 /* V06*steps-19..23 || «null» */,
				st._039 /* 1*'initial' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._296 /* V07*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._305 /* V08*steps-24..28 || «null» */,
				st._029 /* 1*'derivation' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._311 /* V09*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._94,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._01,ev._13)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ZERO_OR_MORE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ONE)
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
				st._168 /* 1*steps-1..17 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._056 /* 1*'static' || «? » «value» «? » */,
				st._190 /* V00*'definition' || «? » «value» «? » */,
				st._024 /* 1*'attribute' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._252 /* V02*steps-9..11 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._141 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._262 /* V03*steps-12..16 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._273 /* V04*steps-14..15 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._93,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._01,ev._13)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ZERO_OR_MORE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ZERO_OR_ONE)
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
				st._176 /* 1*steps-1..29 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._056 /* 1*'static' || «? » «value» «? » */,
				st._190 /* V00*'definition' || «? » «value» «? » */,
				st._024 /* 1*'attribute' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._252 /* V02*steps-9..11 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._141 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._262 /* V03*steps-12..16 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._273 /* V04*steps-14..15 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._281 /* V05*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._291 /* V06*steps-19..23 || «null» */,
				st._039 /* 1*'initial' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._296 /* V07*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._305 /* V08*steps-24..28 || «null» */,
				st._029 /* 1*'derivation' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._311 /* V09*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._94,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._01,ev._13)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
						new EnumerationValue_MultiplicativeCardinality(ev._01, MultiplicativeCardinality.ZERO_OR_MORE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ZERO_OR_ONE)
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
				st._167 /* 1*steps-1..16 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._191 /* V00*'primitive' || «? » «value» «? » */,
				st._027 /* 1*'datatype' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._245 /* V02*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._266 /* V03*steps-9..12 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._000 /* 1*'!serializable' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._270 /* V04*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._279 /* V05*ClassCS::ownedConstraints+=41 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._88,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
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
				st._164 /* 1*steps-1..13 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._191 /* V00*'primitive' || «? » «value» «? » */,
				st._027 /* 1*'datatype' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._245 /* V02*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._266 /* V03*steps-9..12 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._000 /* 1*'!serializable' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._87,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */
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
				st._164 /* 1*steps-1..13 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._191 /* V00*'primitive' || «? » «value» «? » */,
				st._027 /* 1*'datatype' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._245 /* V02*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._266 /* V03*steps-9..12 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._267 /* V04*'serializable' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._87,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					ev._20),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */
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
				st._167 /* 1*steps-1..16 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._191 /* V00*'primitive' || «? » «value» «? » */,
				st._027 /* 1*'datatype' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._245 /* V02*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._266 /* V03*steps-9..12 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._267 /* V04*'serializable' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._281 /* V05*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._288 /* V06*ClassCS::ownedConstraints+=41 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._88,
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
					iv._48) /* TemplateSignatureCS */,
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
				st._166 /* 1*steps-1..15 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._191 /* V00*'primitive' || «? » «value» «? » */,
				st._027 /* 1*'datatype' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._245 /* V02*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._265 /* V03*steps-9..11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._270 /* V04*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._279 /* V05*ClassCS::ownedConstraints+=41 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._86,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
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
				st._163 /* 1*steps-1..12 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._191 /* V00*'primitive' || «? » «value» «? » */,
				st._027 /* 1*'datatype' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._245 /* V02*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._265 /* V03*steps-9..11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._85,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */
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
				st._180 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._108 /* 1*NamedElementCS::name=87|127 || «? » «value» «? » */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._195 /* V00*DetailCS::values+=48|87 || «? » «value» «? » */
			},
			sl._81,
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
				st._162 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._030 /* 1*'documentation' || «? » «value» «? » */,
				st._196 /* V00*DocumentationCS::value=87 || «? » «value» «? » */,
				st._225 /* V01*steps-4..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._247 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._70,
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
				st._167 /* 1*steps-1..16 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'enum' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._228 /* V01*steps-5..7 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._249 /* V02*steps-8..11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._000 /* 1*'!serializable' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._255 /* V03*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._269 /* V04*EnumerationCS::ownedLiterals+=22 || «null» */,
				st._279 /* V05*ClassCS::ownedConstraints+=41 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._69,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
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
				st._167 /* 1*steps-1..16 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'enum' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._228 /* V01*steps-5..7 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._249 /* V02*steps-8..11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._253 /* V03*'serializable' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._270 /* V04*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._280 /* V05*EnumerationCS::ownedLiterals+=22 || «null» */,
				st._288 /* V06*ClassCS::ownedConstraints+=41 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._69,
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
					iv._48) /* TemplateSignatureCS */,
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
				st._162 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'enum' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._228 /* V01*steps-5..7 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._248 /* V02*steps-8..10 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._66,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */
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
				st._163 /* 1*steps-1..12 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'enum' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._228 /* V01*steps-5..7 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._249 /* V02*steps-8..11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._000 /* 1*'!serializable' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._68,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */
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
				st._166 /* 1*steps-1..15 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'enum' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._228 /* V01*steps-5..7 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._248 /* V02*steps-8..10 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._255 /* V03*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._269 /* V04*EnumerationCS::ownedLiterals+=22 || «null» */,
				st._279 /* V05*ClassCS::ownedConstraints+=41 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._67,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
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
				st._163 /* 1*steps-1..12 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'enum' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._228 /* V01*steps-5..7 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._249 /* V02*steps-8..11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._253 /* V03*'serializable' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._68,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					ev._20)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */
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
				st._162 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._106 /* 1*NamedElementCS::name=23 || «? » «value» «? » */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._081 /* 1*EnumerationLiteralCS::literal=87 || «? » «value» «? » */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._082 /* 1*EnumerationLiteralCS::value=85 || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._239 /* V02*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._61,
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
				st._187 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._106 /* 1*NamedElementCS::name=23 || «? » «value» «? » */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._081 /* 1*EnumerationLiteralCS::literal=87 || «? » «value» «? » */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._082 /* 1*EnumerationLiteralCS::value=85 || «? » «value» «? » */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._60,
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
				st._163 /* 1*steps-1..12 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._044 /* 1*'literal' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._081 /* 1*EnumerationLiteralCS::literal=87 || «? » «value» «? » */,
				st._233 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._082 /* 1*EnumerationLiteralCS::value=85 || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._239 /* V02*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._80,
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
				st._161 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._044 /* 1*'literal' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._081 /* 1*EnumerationLiteralCS::literal=87 || «? » «value» «? » */,
				st._233 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._082 /* 1*EnumerationLiteralCS::value=85 || «? » «value» «? » */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._78,
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
				st._161 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._048 /* 1*'opposite' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._214 /* V00*steps-6..10 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._235 /* V01*steps-8..9 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._82,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._03)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
				st._186 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._037 /* 1*'import' || «null» */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._089 /* 1*ImportCS::ownedPathName=123 || «null» */,
				st._216 /* V01*'::*' || «? » «value» «? » */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._33,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
					ev._06)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					iv._66) /* URIPathNameCS */
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
				st._161 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._189 /* V00*'callable' || «? » «value» «? » */,
				st._041 /* 1*'invariant' || «? » «value» «? » */,
				st._227 /* V01*steps-4..9 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._246 /* V02*steps-6..9 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._078 /* 1*ConstraintCS::ownedMessageSpecification=92 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._77,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._16),
				new EAttribute_EnumerationValues(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					ev._12)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._38) /* SpecificationCS */
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
				st._163 /* 1*steps-1..12 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._189 /* V00*'callable' || «? » «value» «? » */,
				st._041 /* 1*'invariant' || «? » «value» «? » */,
				st._227 /* V01*steps-4..9 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._246 /* V02*steps-6..9 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._078 /* 1*ConstraintCS::ownedMessageSpecification=92 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._009 /* 1*':' || «! » «value» «? » */,
				st._254 /* V03*ConstraintCS::ownedSpecification=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._76,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._16),
				new EAttribute_EnumerationValues(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					ev._12)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._38) /* SpecificationCS */
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
				st._180 /* 1*steps-1..4 || «null» */,
				st._054 /* 1*'reference' || «? » «value» «? » */,
				st._100 /* 1*ModelElementRefCS::ownedPathName=73 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._26,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */
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
				st._175 /* 1*steps-1..28 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._056 /* 1*'static' || «? » «value» «? » */,
				st._190 /* V00*'definition' || «? » «value» «? » */,
				st._047 /* 1*'operation' || «? » «value» «? » */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._250 /* V02*steps-8..12 || «null» */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._258 /* V03*steps-10..12 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._274 /* V04*steps-14..16 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._285 /* V05*steps-17..22 || «null» */,
				st._059 /* 1*'throws' || «? » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._292 /* V06*steps-20..22 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._301 /* V07*steps-23..27 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._306 /* V08*steps-25..26 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._89,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02,ev._21,ev._13)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._26) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._61) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
						new EnumerationValue_MultiplicativeCardinality(ev._02, MultiplicativeCardinality.ZERO_OR_MORE),
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ZERO_OR_ONE)
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
				st._173 /* 1*steps-1..26 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._047 /* 1*'operation' || «? » «value» «? » */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._231 /* V01*steps-6..10 || «null» */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._248 /* V02*steps-8..10 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._261 /* V03*steps-12..14 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._277 /* V04*steps-15..20 || «null» */,
				st._059 /* 1*'throws' || «? » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._286 /* V05*steps-18..20 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._294 /* V06*steps-21..25 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._299 /* V07*steps-23..24 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._58,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._26) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._61) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
				st._178 /* 1*steps-1..35 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._047 /* 1*'operation' || «? » «value» «? » */,
				st._202 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._231 /* V01*steps-6..10 || «null» */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._248 /* V02*steps-8..10 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._261 /* V03*steps-12..14 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._277 /* V04*steps-15..20 || «null» */,
				st._059 /* 1*'throws' || «? » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._286 /* V05*steps-18..20 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._294 /* V06*steps-21..25 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._299 /* V07*steps-23..24 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._302 /* V08*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._310 /* V09*OperationCS::ownedPreconditions+=76 || «null» */,
				st._317 /* V10*steps-29..33 || «null» */,
				st._025 /* 1*'body' || «? » «value» «? » */,
				st._009 /* 1*':' || «! » «value» «? » */,
				st._319 /* V11*OperationCS::ownedBodyExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._324 /* V12*OperationCS::ownedPostconditions+=75 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._59,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._31) /* PreconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._26) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._30) /* PostconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._61) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
				st._179 /* 1*steps-1..37 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._028 /* 1*'definition' || «? » «value» «? » */,
				st._192 /* V00*'static' || «? » «value» «? » */,
				st._047 /* 1*'operation' || «? » «value» «? » */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._250 /* V02*steps-8..12 || «null» */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._258 /* V03*steps-10..12 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._274 /* V04*steps-14..16 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._285 /* V05*steps-17..22 || «null» */,
				st._059 /* 1*'throws' || «? » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._292 /* V06*steps-20..22 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._301 /* V07*steps-23..27 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._306 /* V08*steps-25..26 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._309 /* V09*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._315 /* V10*OperationCS::ownedPreconditions+=76 || «null» */,
				st._321 /* V11*steps-31..35 || «null» */,
				st._025 /* 1*'body' || «? » «value» «? » */,
				st._009 /* 1*':' || «! » «value» «? » */,
				st._323 /* V12*OperationCS::ownedBodyExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._327 /* V13*OperationCS::ownedPostconditions+=75 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._90,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02,ev._21,ev._13)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._31) /* PreconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._26) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._30) /* PostconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._61) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
						new EnumerationValue_MultiplicativeCardinality(ev._02, MultiplicativeCardinality.ZERO_OR_MORE),
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ONE)
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
				st._175 /* 1*steps-1..28 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._028 /* 1*'definition' || «? » «value» «? » */,
				st._192 /* V00*'static' || «? » «value» «? » */,
				st._047 /* 1*'operation' || «? » «value» «? » */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._250 /* V02*steps-8..12 || «null» */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._258 /* V03*steps-10..12 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._274 /* V04*steps-14..16 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._285 /* V05*steps-17..22 || «null» */,
				st._059 /* 1*'throws' || «? » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._292 /* V06*steps-20..22 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._301 /* V07*steps-23..27 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._306 /* V08*steps-25..26 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._89,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02,ev._21,ev._13)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._26) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._61) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
						new EnumerationValue_MultiplicativeCardinality(ev._02, MultiplicativeCardinality.ZERO_OR_MORE),
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ZERO_OR_ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ONE)
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
				st._179 /* 1*steps-1..37 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._056 /* 1*'static' || «? » «value» «? » */,
				st._190 /* V00*'definition' || «? » «value» «? » */,
				st._047 /* 1*'operation' || «? » «value» «? » */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._250 /* V02*steps-8..12 || «null» */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._258 /* V03*steps-10..12 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._274 /* V04*steps-14..16 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._285 /* V05*steps-17..22 || «null» */,
				st._059 /* 1*'throws' || «? » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._292 /* V06*steps-20..22 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._301 /* V07*steps-23..27 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._306 /* V08*steps-25..26 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._309 /* V09*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._315 /* V10*OperationCS::ownedPreconditions+=76 || «null» */,
				st._321 /* V11*steps-31..35 || «null» */,
				st._025 /* 1*'body' || «? » «value» «? » */,
				st._009 /* 1*':' || «! » «value» «? » */,
				st._323 /* V12*OperationCS::ownedBodyExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._327 /* V13*OperationCS::ownedPostconditions+=75 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._90,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02,ev._21,ev._13)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._31) /* PreconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._26) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._30) /* PostconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._61) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
						new EnumerationValue_MultiplicativeCardinality(ev._02, MultiplicativeCardinality.ZERO_OR_MORE),
						new EnumerationValue_MultiplicativeCardinality(ev._21, MultiplicativeCardinality.ONE),
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ZERO_OR_ONE)
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
				st._161 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._049 /* 1*'package' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._120 /* 1*PackageCS::nsPrefix=127 || «? » «value» «? » */,
				st._233 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._121 /* 1*PackageCS::nsURI=121 || «? » «value» «? » */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._78,
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
				st._165 /* 1*steps-1..14 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._049 /* 1*'package' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._120 /* 1*PackageCS::nsPrefix=127 || «? » «value» «? » */,
				st._233 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._121 /* 1*PackageCS::nsURI=121 || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._239 /* V02*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._256 /* V03*PackageOwnerCS::ownedPackages+=71 || «null» */,
				st._271 /* V04*PackageCS::ownedClasses+=6 || «½\n» «value» «½\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._79,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					iv._25) /* PackageCS */,
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
				st._165 /* 1*steps-1..14 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._206 /* V00*steps-3..5 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._231 /* V01*steps-6..10 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._251 /* V02*steps-8..9 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._259 /* V03*steps-11..14 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._270 /* V04*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._53,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._03)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
				st._162 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._050 /* 1*'postcondition' || «? » «value» «? » */,
				st._208 /* V00*steps-3..8 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._229 /* V01*steps-5..8 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._078 /* 1*ConstraintCS::ownedMessageSpecification=92 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._009 /* 1*':' || «! » «value» «? » */,
				st._238 /* V02*ConstraintCS::ownedSpecification=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._56,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._17)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._38) /* SpecificationCS */
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
				st._162 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._052 /* 1*'precondition' || «? » «value» «? » */,
				st._208 /* V00*steps-3..8 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._229 /* V01*steps-5..8 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._078 /* 1*ConstraintCS::ownedMessageSpecification=92 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._009 /* 1*':' || «! » «value» «? » */,
				st._238 /* V02*ConstraintCS::ownedSpecification=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._56,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._18)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._38) /* SpecificationCS */
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
				st._182 /* 1*steps-1..42 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._028 /* 1*'definition' || «? » «value» «? » */,
				st._192 /* V00*'static' || «? » «value» «? » */,
				st._053 /* 1*'property' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._001 /* 1*'#' || «! » «value» «! » */,
				st._134 /* 1*ReferenceCS::referredOpposite=UnrestrictedName || «? » «value» «? » */,
				st._252 /* V02*steps-9..11 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._261 /* V03*steps-12..14 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._141 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._276 /* V04*steps-15..19 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._283 /* V05*steps-17..18 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._289 /* V06*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._298 /* V07*steps-22..28 || «null» */,
				st._042 /* 1*'key' || «? » «value» «? » */,
				st._133 /* 1*ReferenceCS::referredKeys+=UnrestrictedName || «? » «value» «? » */,
				st._307 /* V08*steps-25..27 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._132 /* 1*ReferenceCS::referredKeys+=UnrestrictedName || «? » «value» «? » */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._313 /* V09*steps-29..33 || «null» */,
				st._039 /* 1*'initial' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._316 /* V10*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._322 /* V11*steps-34..38 || «null» */,
				st._029 /* 1*'derivation' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._325 /* V12*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._328 /* V13*steps-39..41 || «null» */,
				st._131 /* 1*ReferenceCS::ownedImplicitOpposites+=37 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._92,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._00)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */,
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
				st._182 /* 1*steps-1..42 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._056 /* 1*'static' || «? » «value» «? » */,
				st._190 /* V00*'definition' || «? » «value» «? » */,
				st._053 /* 1*'property' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._001 /* 1*'#' || «! » «value» «! » */,
				st._134 /* 1*ReferenceCS::referredOpposite=UnrestrictedName || «? » «value» «? » */,
				st._252 /* V02*steps-9..11 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._261 /* V03*steps-12..14 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._141 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._276 /* V04*steps-15..19 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._283 /* V05*steps-17..18 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._289 /* V06*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._298 /* V07*steps-22..28 || «null» */,
				st._042 /* 1*'key' || «? » «value» «? » */,
				st._133 /* 1*ReferenceCS::referredKeys+=UnrestrictedName || «? » «value» «? » */,
				st._307 /* V08*steps-25..27 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._132 /* 1*ReferenceCS::referredKeys+=UnrestrictedName || «? » «value» «? » */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._313 /* V09*steps-29..33 || «null» */,
				st._039 /* 1*'initial' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._316 /* V10*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._322 /* V11*steps-34..38 || «null» */,
				st._029 /* 1*'derivation' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._325 /* V12*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._328 /* V13*steps-39..41 || «null» */,
				st._131 /* 1*ReferenceCS::ownedImplicitOpposites+=37 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._92,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._00)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */,
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
				st._171 /* 1*steps-1..20 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._028 /* 1*'definition' || «? » «value» «? » */,
				st._192 /* V00*'static' || «? » «value» «? » */,
				st._053 /* 1*'property' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._001 /* 1*'#' || «! » «value» «! » */,
				st._134 /* 1*ReferenceCS::referredOpposite=UnrestrictedName || «? » «value» «? » */,
				st._252 /* V02*steps-9..11 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._261 /* V03*steps-12..14 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._141 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._276 /* V04*steps-15..19 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._283 /* V05*steps-17..18 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._91,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._00)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
				st._181 /* 1*steps-1..40 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._053 /* 1*'property' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._001 /* 1*'#' || «! » «value» «! » */,
				st._134 /* 1*ReferenceCS::referredOpposite=UnrestrictedName || «? » «value» «? » */,
				st._233 /* V01*steps-7..9 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._242 /* V02*steps-10..12 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._141 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._263 /* V03*steps-13..17 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._275 /* V04*steps-15..16 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._281 /* V05*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._293 /* V06*steps-20..26 || «null» */,
				st._042 /* 1*'key' || «? » «value» «? » */,
				st._133 /* 1*ReferenceCS::referredKeys+=UnrestrictedName || «? » «value» «? » */,
				st._300 /* V07*steps-23..25 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._132 /* 1*ReferenceCS::referredKeys+=UnrestrictedName || «? » «value» «? » */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._308 /* V08*steps-27..31 || «null» */,
				st._039 /* 1*'initial' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._311 /* V09*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._318 /* V10*steps-32..36 || «null» */,
				st._029 /* 1*'derivation' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._320 /* V11*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._326 /* V12*steps-37..39 || «null» */,
				st._131 /* 1*ReferenceCS::ownedImplicitOpposites+=37 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._73,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._00)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */,
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
				st._169 /* 1*steps-1..18 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._053 /* 1*'property' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._001 /* 1*'#' || «! » «value» «! » */,
				st._134 /* 1*ReferenceCS::referredOpposite=UnrestrictedName || «? » «value» «? » */,
				st._233 /* V01*steps-7..9 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._242 /* V02*steps-10..12 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._141 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._263 /* V03*steps-13..17 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._275 /* V04*steps-15..16 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._72,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._00)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
				st._171 /* 1*steps-1..20 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._056 /* 1*'static' || «? » «value» «? » */,
				st._190 /* V00*'definition' || «? » «value» «? » */,
				st._053 /* 1*'property' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._232 /* V01*steps-6..8 || «null» */,
				st._001 /* 1*'#' || «! » «value» «! » */,
				st._134 /* 1*ReferenceCS::referredOpposite=UnrestrictedName || «? » «value» «? » */,
				st._252 /* V02*steps-9..11 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._154 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._261 /* V03*steps-12..14 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._141 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._276 /* V04*steps-15..19 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._283 /* V05*steps-17..18 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._91,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._21,ev._13,ev._00)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
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
				st._139 /* 1*SpecificationCS::exprString=119 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._083 /* 1*ExpSpecificationCS::ownedExpression=30 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
				st._172 /* 1*steps-1..24 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._188 /* V00*'abstract' || «? » «value» «? » */,
				st._026 /* 1*'class' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._244 /* V02*steps-6..11 || «null» */,
				st._035 /* 1*'extends' || «? » «value» «? » */,
				st._142 /* 1*StructuredClassCS::ownedSuperTypes+=117 || «null» */,
				st._265 /* V03*steps-9..11 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._142 /* 1*StructuredClassCS::ownedSuperTypes+=117 || «null» */,
				st._272 /* V04*steps-12..14 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._282 /* V05*steps-15..18 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._287 /* V06*'interface' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._295 /* V07*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._304 /* V08*StructuredClassCS::ownedOperations+=70 || «null» */,
				st._312 /* V09*StructuredClassCS::ownedProperties+=96 || «null» */,
				st._314 /* V10*ClassCS::ownedConstraints+=41 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._84,
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
					iv._61) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					iv._41) /* StructuralFeatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					iv._24) /* OperationCS */
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
				st._170 /* 1*steps-1..19 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._188 /* V00*'abstract' || «? » «value» «? » */,
				st._026 /* 1*'class' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._223 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._244 /* V02*steps-6..11 || «null» */,
				st._035 /* 1*'extends' || «? » «value» «? » */,
				st._142 /* 1*StructuredClassCS::ownedSuperTypes+=117 || «null» */,
				st._265 /* V03*steps-9..11 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._142 /* 1*StructuredClassCS::ownedSuperTypes+=117 || «null» */,
				st._272 /* V04*steps-12..14 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._282 /* V05*steps-15..18 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._287 /* V06*'interface' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._83,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					ev._15),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					ev._11)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					iv._61) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */
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
				st._180 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._057 /* 1*'sysml' || «? » «value» «? » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._51,
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
				st._185 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._057 /* 1*'sysml' || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._63,
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
				st._185 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._145 /* 1*TemplateSignatureCS::ownedParameters+=114 || «null» */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._145 /* 1*TemplateSignatureCS::ownedParameters+=114 || «null» */,
				st._004 /* 1*')' || «! » «value» */
			},
			sl._39,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._58) /* TypeParameterCS */
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
				st._185 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._012 /* 1*'<' || «? » «value» «? » */,
				st._145 /* 1*TemplateSignatureCS::ownedParameters+=114 || «null» */,
				st._210 /* V00*steps-4..6 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._145 /* 1*TemplateSignatureCS::ownedParameters+=114 || «null» */,
				st._015 /* 1*'>' || «? » «value» «? » */
			},
			sl._47,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._58) /* TypeParameterCS */
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
				st._183 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._204 /* V00*steps-2..3 || «null» */,
				st._045 /* 1*'module' || «? » «value» «? » */,
				st._220 /* V01*RootCS::ownedImports+=38 || «null» */,
				st._240 /* V02*PackageOwnerCS::ownedPackages+=71 || «null» */
			},
			sl._36,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					iv._25) /* PackageCS */,
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
				st._184 /* 1*steps-1..6 || «null» */,
				st._157 /* 1*TypedTypeRefCS::ownedPathName=73 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._156 /* 1*TypedTypeRefCS::ownedBinding=99 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._203 /* V00*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._07,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._46) /* TemplateBindingCS */,
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
				st._177 /* 1*steps-1..3 || «null» */,
				st._130 /* 1*PrimitiveTypeRefCS::name=82 || «? » «value» «? » */,
				st._203 /* V00*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._19,
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
				st._162 /* 1*steps-1..11 || «null» */,
				st._020 /* 1*'Tuple' || «? » «value» «? » */,
				st._205 /* V00*steps-3..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._230 /* V01*steps-5..9 || «null» */,
				st._147 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._247 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._147 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._257 /* V03*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._21,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._10)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._50) /* TuplePartCS */,
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
				st._186 /* 1*steps-1..8 || «null» */,
				st._076 /* 1*CollectionTypeCS::name=12 || «? » «value» «? » */,
				st._207 /* V00*steps-3..7 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._077 /* 1*CollectionTypeCS::ownedType=108 || «null» */,
				st._219 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=56 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._241 /* V02*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._23,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._52) /* TypeExpWithoutMultiplicityCS */,
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
				st._177 /* 1*steps-1..3 || «null» */,
				st._157 /* 1*TypedTypeRefCS::ownedPathName=73 || «null» */,
				st._203 /* V00*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._01,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */,
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
				st._184 /* 1*steps-1..6 || «null» */,
				st._157 /* 1*TypedTypeRefCS::ownedPathName=73 || «null» */,
				st._012 /* 1*'<' || «? » «value» «? » */,
				st._156 /* 1*TypedTypeRefCS::ownedBinding=99 || «null» */,
				st._015 /* 1*'>' || «? » «value» «? » */,
				st._203 /* V00*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._11,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._46) /* TemplateBindingCS */,
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
				st._187 /* 1*steps-1..9 || «null» */,
				st._019 /* 1*'Map' || «? » «value» «? » */,
				st._208 /* V00*steps-3..8 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._098 /* 1*MapTypeCS::ownedKeyType=107 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._099 /* 1*MapTypeCS::ownedValueType=107 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._224 /* V01*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._25,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._51) /* TypeExpCS */,
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
				st._183 /* 1*steps-1..5 || «null» */,
				st._157 /* 1*TypedTypeRefCS::ownedPathName=73 || «null» */,
				st._012 /* 1*'<' || «? » «value» «? » */,
				st._156 /* 1*TypedTypeRefCS::ownedBinding=99 || «null» */,
				st._015 /* 1*'>' || «? » «value» «? » */
			},
			sl._10,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._46) /* TemplateBindingCS */
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
				st._157 /* 1*TypedTypeRefCS::ownedPathName=73 || «null» */
			},
			sl._00,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */
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
				st._183 /* 1*steps-1..5 || «null» */,
				st._157 /* 1*TypedTypeRefCS::ownedPathName=73 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._156 /* 1*TypedTypeRefCS::ownedBinding=99 || «null» */,
				st._004 /* 1*')' || «! » «value» */
			},
			sl._06,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._46) /* TemplateBindingCS */
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

	private _EClassValues ec;
	private _EnumValues ev;
	private _IndexVectors iv;
	private _MatchSteps ms;
	private _MatchTerms mt;
	private _SerializationSegmentsLists sl;
	private _SerializationSegments ss;
	private _SerializationRules0 sr0;
	private _SerializationRules1 sr1;
	private _SerializationRules2 sr2;
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
		sr2 = new _SerializationRules2();
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
//	import EssentialOCLCSPackage;
//	import OCLinEcoreCSPackage;
