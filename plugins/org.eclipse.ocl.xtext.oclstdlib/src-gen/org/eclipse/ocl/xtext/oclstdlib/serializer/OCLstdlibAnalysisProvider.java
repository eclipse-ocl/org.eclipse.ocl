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
package org.eclipse.ocl.xtext.oclstdlib.serializer;

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
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignsStep;
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
import org.eclipse.ocl.xtext.oclstdlibcs.OCLstdlibCSPackage;

public class OCLstdlibAnalysisProvider extends AbstractAnalysisProvider
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
					ec._01  /* essentialoclcs::BooleanLiteralExpCS */,
					ec._02  /* essentialoclcs::CollectionLiteralExpCS */,
					ec._03  /* essentialoclcs::CollectionLiteralPartCS */,
					ec._04  /* essentialoclcs::CollectionPatternCS */,
					ec._05  /* essentialoclcs::CollectionTypeCS */,
					ec._06  /* essentialoclcs::ContextCS */,
					ec._07  /* essentialoclcs::CurlyBracketedClauseCS */,
					ec._08  /* basecs::DetailCS */,
					ec._09  /* basecs::DocumentationCS */,
					ec._10  /* essentialoclcs::ExpCS */,
					ec._11  /* essentialoclcs::ExpSpecificationCS */,
					ec._12  /* essentialoclcs::IfExpCS */,
					ec._13  /* essentialoclcs::IfThenExpCS */,
					ec._14  /* basecs::ImportCS */,
					ec._15  /* essentialoclcs::InfixExpCS */,
					ec._16  /* essentialoclcs::InvalidLiteralExpCS */,
					ec._17  /* essentialoclcs::LambdaLiteralExpCS */,
					ec._18  /* basecs::LambdaTypeCS */,
					ec._19  /* essentialoclcs::LetExpCS */,
					ec._20  /* essentialoclcs::LetVariableCS */,
					ec._21  /* oclstdlibcs::LibClassCS */,
					ec._22  /* oclstdlibcs::LibCoercionCS */,
					ec._23  /* oclstdlibcs::LibConstraintCS */,
					ec._24  /* oclstdlibcs::LibIterationCS */,
					ec._25  /* oclstdlibcs::LibOperationCS */,
					ec._26  /* oclstdlibcs::LibOppositeCS */,
					ec._27  /* oclstdlibcs::LibPackageCS */,
					ec._28  /* oclstdlibcs::LibPropertyCS */,
					ec._29  /* oclstdlibcs::LibRootPackageCS */,
					ec._30  /* essentialoclcs::MapLiteralExpCS */,
					ec._31  /* essentialoclcs::MapLiteralPartCS */,
					ec._32  /* essentialoclcs::MapTypeCS */,
					ec._33  /* basecs::MultiplicityBoundsCS */,
					ec._34  /* basecs::MultiplicityStringCS */,
					ec._35  /* essentialoclcs::NameExpCS */,
					ec._36  /* essentialoclcs::NavigatingArgCS */,
					ec._37  /* essentialoclcs::NestedExpCS */,
					ec._38  /* essentialoclcs::NullLiteralExpCS */,
					ec._39  /* essentialoclcs::NumberLiteralExpCS */,
					ec._40  /* basecs::PackageCS */,
					ec._41  /* basecs::ParameterCS */,
					ec._42  /* basecs::PathElementCS */,
					ec._43  /* basecs::PathElementWithURICS */,
					ec._44  /* basecs::PathNameCS */,
					ec._45  /* essentialoclcs::PatternExpCS */,
					ec._46  /* oclstdlibcs::PrecedenceCS */,
					ec._47  /* essentialoclcs::PrefixExpCS */,
					ec._48  /* basecs::PrimitiveTypeRefCS */,
					ec._49  /* essentialoclcs::RoundBracketedClauseCS */,
					ec._50  /* essentialoclcs::SelfExpCS */,
					ec._51  /* essentialoclcs::ShadowPartCS */,
					ec._52  /* essentialoclcs::SquareBracketedClauseCS */,
					ec._53  /* essentialoclcs::StringLiteralExpCS */,
					ec._54  /* basecs::TemplateBindingCS */,
					ec._55  /* basecs::TemplateParameterSubstitutionCS */,
					ec._56  /* basecs::TemplateSignatureCS */,
					ec._57  /* essentialoclcs::TupleLiteralExpCS */,
					ec._58  /* essentialoclcs::TupleLiteralPartCS */,
					ec._59  /* basecs::TuplePartCS */,
					ec._60  /* basecs::TupleTypeCS */,
					ec._61  /* essentialoclcs::TypeLiteralExpCS */,
					ec._62  /* essentialoclcs::TypeNameExpCS */,
					ec._63  /* basecs::TypeParameterCS */,
					ec._64  /* basecs::TypedTypeRefCS */,
					ec._65  /* essentialoclcs::UnlimitedNaturalLiteralExpCS */,
					ec._66  /* essentialoclcs::VariableCS */,
					ec._67  /* basecs::WildcardTypeRefCS */
				},
				/**
				 *	The indexable per-grammar rule meta data.
				 */
				new AbstractRuleValue [] {
					gr._000  /* 0 : ANY_OTHER */,
					gr._001  /* 1 : AccumulatorCS */,
					gr._002  /* 2 : AnnotationCS */,
					gr._003  /* 3 : AnnotationElementCS */,
					gr._004  /* 4 : AnyName */,
					gr._005  /* 5 : BinaryOperatorName */,
					gr._006  /* 6 : BooleanLiteralExpCS */,
					gr._007  /* 7 : ClassCS */,
					gr._008  /* 8 : CoIteratorVariableCS */,
					gr._009  /* 9 : CollectionLiteralExpCS */,
					gr._010  /* 10 : CollectionLiteralPartCS */,
					gr._011  /* 11 : CollectionPatternCS */,
					gr._012  /* 12 : CollectionTypeCS */,
					gr._013  /* 13 : CollectionTypeIdentifier */,
					gr._014  /* 14 : CurlyBracketedClauseCS */,
					gr._015  /* 15 : DOUBLE_QUOTED_STRING */,
					gr._016  /* 16 : DetailCS */,
					gr._017  /* 17 : DocumentationCS */,
					gr._018  /* 18 : ESCAPED_CHARACTER */,
					gr._019  /* 19 : ESCAPED_ID */,
					gr._020  /* 20 : ElseIfThenExpCS */,
					gr._021  /* 21 : EssentialOCLInfixOperatorName */,
					gr._022  /* 22 : EssentialOCLNavigationOperatorName */,
					gr._023  /* 23 : EssentialOCLReservedKeyword */,
					gr._024  /* 24 : EssentialOCLUnaryOperatorName */,
					gr._025  /* 25 : EssentialOCLUnreservedName */,
					gr._026  /* 26 : EssentialOCLUnrestrictedName */,
					gr._027  /* 27 : ExpCS */,
					gr._028  /* 28 : FirstPathElementCS */,
					gr._029  /* 29 : ID */,
					gr._030  /* 30 : INT */,
					gr._031  /* 31 : Identifier */,
					gr._032  /* 32 : IfExpCS */,
					gr._033  /* 33 : ImportCS */,
					gr._034  /* 34 : InfixOperatorName */,
					gr._035  /* 35 : InvCS */,
					gr._036  /* 36 : InvalidLiteralExpCS */,
					gr._037  /* 37 : IteratorCS */,
					gr._038  /* 38 : LETTER_CHARACTER */,
					gr._039  /* 39 : LOWER */,
					gr._040  /* 40 : LambdaContextTypeRefCS */,
					gr._041  /* 41 : LambdaLiteralExpCS */,
					gr._042  /* 42 : LambdaTypeCS */,
					gr._043  /* 43 : LetExpCS */,
					gr._044  /* 44 : LetVariableCS */,
					gr._045  /* 45 : LibClassCS */,
					gr._046  /* 46 : LibCoercionCS */,
					gr._047  /* 47 : LibIterationCS */,
					gr._048  /* 48 : LibOperationCS */,
					gr._049  /* 49 : LibOppositeCS */,
					gr._050  /* 50 : LibPackageCS */,
					gr._051  /* 51 : LibPathElementCS */,
					gr._052  /* 52 : LibPathNameCS */,
					gr._053  /* 53 : LibPropertyCS */,
					gr._054  /* 54 : Library */,
					gr._055  /* 55 : ML_COMMENT */,
					gr._056  /* 56 : ML_SINGLE_QUOTED_STRING */,
					gr._057  /* 57 : MapLiteralExpCS */,
					gr._058  /* 58 : MapLiteralPartCS */,
					gr._059  /* 59 : MapTypeCS */,
					gr._060  /* 60 : Model */,
					gr._061  /* 61 : MultiplicityBoundsCS */,
					gr._062  /* 62 : MultiplicityCS */,
					gr._063  /* 63 : MultiplicityStringCS */,
					gr._064  /* 64 : NUMBER_LITERAL */,
					gr._065  /* 65 : Name */,
					gr._066  /* 66 : NameExpCS */,
					gr._067  /* 67 : NavigatingArgCS */,
					gr._068  /* 68 : NavigatingArgExpCS */,
					gr._069  /* 69 : NavigatingBarArgCS */,
					gr._070  /* 70 : NavigatingCommaArgCS */,
					gr._071  /* 71 : NavigatingSemiArgCS */,
					gr._072  /* 72 : NavigationOperatorName */,
					gr._073  /* 73 : NestedExpCS */,
					gr._074  /* 74 : NextPathElementCS */,
					gr._075  /* 75 : NullLiteralExpCS */,
					gr._076  /* 76 : NumberLiteralExpCS */,
					gr._077  /* 77 : OperationCS */,
					gr._078  /* 78 : PackageCS */,
					gr._079  /* 79 : ParameterCS */,
					gr._080  /* 80 : PathNameCS */,
					gr._081  /* 81 : PatternExpCS */,
					gr._082  /* 82 : PostCS */,
					gr._083  /* 83 : PreCS */,
					gr._084  /* 84 : PrecedenceCS */,
					gr._085  /* 85 : PrefixedLetExpCS */,
					gr._086  /* 86 : PrefixedPrimaryExpCS */,
					gr._087  /* 87 : PrimaryExpCS */,
					gr._088  /* 88 : PrimitiveLiteralExpCS */,
					gr._089  /* 89 : PrimitiveTypeCS */,
					gr._090  /* 90 : PrimitiveTypeIdentifier */,
					gr._091  /* 91 : RestrictedKeywords */,
					gr._092  /* 92 : RoundBracketedClauseCS */,
					gr._093  /* 93 : SIMPLE_ID */,
					gr._094  /* 94 : SINGLE_QUOTED_STRING */,
					gr._095  /* 95 : SL_COMMENT */,
					gr._096  /* 96 : SelfExpCS */,
					gr._097  /* 97 : ShadowPartCS */,
					gr._098  /* 98 : SimplePathNameCS */,
					gr._099  /* 99 : SpecificationCS */,
					gr._100  /* 100 : SquareBracketedClauseCS */,
					gr._101  /* 101 : StringLiteral */,
					gr._102  /* 102 : StringLiteralExpCS */,
					gr._103  /* 103 : TemplateBindingCS */,
					gr._104  /* 104 : TemplateParameterSubstitutionCS */,
					gr._105  /* 105 : TemplateSignatureCS */,
					gr._106  /* 106 : TupleLiteralExpCS */,
					gr._107  /* 107 : TupleLiteralPartCS */,
					gr._108  /* 108 : TuplePartCS */,
					gr._109  /* 109 : TupleTypeCS */,
					gr._110  /* 110 : TypeExpCS */,
					gr._111  /* 111 : TypeExpWithoutMultiplicityCS */,
					gr._112  /* 112 : TypeLiteralCS */,
					gr._113  /* 113 : TypeLiteralExpCS */,
					gr._114  /* 114 : TypeLiteralWithMultiplicityCS */,
					gr._115  /* 115 : TypeNameExpCS */,
					gr._116  /* 116 : TypeParameterCS */,
					gr._117  /* 117 : TypeRefCS */,
					gr._118  /* 118 : TypedMultiplicityRefCS */,
					gr._119  /* 119 : TypedRefCS */,
					gr._120  /* 120 : TypedTypeRefCS */,
					gr._121  /* 121 : UPPER */,
					gr._122  /* 122 : URI */,
					gr._123  /* 123 : URIFirstPathElementCS */,
					gr._124  /* 124 : URIPathNameCS */,
					gr._125  /* 125 : UnaryOperatorName */,
					gr._126  /* 126 : UnlimitedNaturalLiteralExpCS */,
					gr._127  /* 127 : UnreservedName */,
					gr._128  /* 128 : UnrestrictedName */,
					gr._129  /* 129 : WS */,
					gr._130  /* 130 : WildcardTypeRefCS */
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
		private final @NonNull IndexVector _0 // AccumulatorCS
			= new IndexVector(0x2L);
		private final @NonNull IndexVector _1 // AnnotationElementCS
			= new IndexVector(0x8L);
		private final @NonNull IndexVector _2 // ClassCS
			= new IndexVector(0x80L);
		private final @NonNull IndexVector _3 // CoIteratorVariableCS
			= new IndexVector(0x100L);
		private final @NonNull IndexVector _4 // CollectionLiteralPartCS
			= new IndexVector(0x400L);
		private final @NonNull IndexVector _5 // CollectionTypeCS
			= new IndexVector(0x1000L);
		private final @NonNull IndexVector _6 // CurlyBracketedClauseCS
			= new IndexVector(0x4000L);
		private final @NonNull IndexVector _7 // DetailCS
			= new IndexVector(0x10000L);
		private final @NonNull IndexVector _8 // AnnotationCS|AnnotationElementCS|DocumentationCS
			= new IndexVector(0x2000cL);
		private final @NonNull IndexVector _9 // ElseIfThenExpCS
			= new IndexVector(0x100000L);
		private final @NonNull IndexVector _10 // ExpCS
			= new IndexVector(0x8000000L);
		private final @NonNull IndexVector _11 // FirstPathElementCS
			= new IndexVector(0x10000000L);
		private final @NonNull IndexVector _12 // ImportCS
			= new IndexVector(0x200000000L);
		private final @NonNull IndexVector _13 // InvCS
			= new IndexVector(0x800000000L);
		private final @NonNull IndexVector _14 // IteratorCS
			= new IndexVector(0x2000000000L);
		private final @NonNull IndexVector _15 // LambdaContextTypeRefCS
			= new IndexVector(0x10000000000L);
		private final @NonNull IndexVector _16 // LetVariableCS
			= new IndexVector(0x100000000000L);
		private final @NonNull IndexVector _17 // ClassCS|LibClassCS
			= new IndexVector(0x200000000080L);
		private final @NonNull IndexVector _18 // LibOppositeCS
			= new IndexVector(0x2000000000000L);
		private final @NonNull IndexVector _19 // LibPackageCS
			= new IndexVector(0x4000000000000L);
		private final @NonNull IndexVector _20 // LibPathElementCS
			= new IndexVector(0x8000000000000L);
		private final @NonNull IndexVector _21 // LibPathNameCS
			= new IndexVector(0x10000000000000L);
		private final @NonNull IndexVector _22 // LibPropertyCS
			= new IndexVector(0x20000000000000L);
		private final @NonNull IndexVector _23 // MapLiteralPartCS
			= new IndexVector(0x400000000000000L);
		private final @NonNull IndexVector _24 // MapTypeCS
			= new IndexVector(0x800000000000000L);
		private final @NonNull IndexVector _25 // MultiplicityCS
			= new IndexVector(0x4000000000000000L);
		private final @NonNull IndexVector _26 // NavigatingArgExpCS
			= new IndexVector(0x0L,0x10L);
		private final @NonNull IndexVector _27 // NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
			= new IndexVector(0x0L,0xe0L);
		private final @NonNull IndexVector _28 // NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
			= new IndexVector(0x0L,0xe8L);
		private final @NonNull IndexVector _29 // FirstPathElementCS|NextPathElementCS
			= new IndexVector(0x10000000L,0x400L);
		private final @NonNull IndexVector _30 // OperationCS
			= new IndexVector(0x0L,0x2000L);
		private final @NonNull IndexVector _31 // LibCoercionCS|LibIterationCS|LibOperationCS|OperationCS
			= new IndexVector(0x1c00000000000L,0x2000L);
		private final @NonNull IndexVector _32 // PackageCS
			= new IndexVector(0x0L,0x4000L);
		private final @NonNull IndexVector _33 // ParameterCS
			= new IndexVector(0x0L,0x8000L);
		private final @NonNull IndexVector _34 // PathNameCS
			= new IndexVector(0x0L,0x10000L);
		private final @NonNull IndexVector _35 // PatternExpCS
			= new IndexVector(0x0L,0x20000L);
		private final @NonNull IndexVector _36 // ExpCS|PatternExpCS
			= new IndexVector(0x8000000L,0x20000L);
		private final @NonNull IndexVector _37 // PostCS
			= new IndexVector(0x0L,0x40000L);
		private final @NonNull IndexVector _38 // PreCS
			= new IndexVector(0x0L,0x80000L);
		private final @NonNull IndexVector _39 // PrecedenceCS
			= new IndexVector(0x0L,0x100000L);
		private final @NonNull IndexVector _40 // PrefixedLetExpCS
			= new IndexVector(0x0L,0x200000L);
		private final @NonNull IndexVector _41 // LetExpCS|PrefixedLetExpCS
			= new IndexVector(0x80000000000L,0x200000L);
		private final @NonNull IndexVector _42 // PrefixedPrimaryExpCS
			= new IndexVector(0x0L,0x400000L);
		private final @NonNull IndexVector _43 // RoundBracketedClauseCS
			= new IndexVector(0x0L,0x10000000L);
		private final @NonNull IndexVector _44 // Identifier|SINGLE_QUOTED_STRING
			= new IndexVector(0x80000000L,0x40000000L);
		private final @NonNull IndexVector _45 // ML_SINGLE_QUOTED_STRING|SINGLE_QUOTED_STRING
			= new IndexVector(0x100000000000000L,0x40000000L);
		private final @NonNull IndexVector _46 // Name|SINGLE_QUOTED_STRING
			= new IndexVector(0x0L,0x40000002L);
		private final @NonNull IndexVector _47 // ShadowPartCS
			= new IndexVector(0x0L,0x200000000L);
		private final @NonNull IndexVector _48 // SpecificationCS
			= new IndexVector(0x0L,0x800000000L);
		private final @NonNull IndexVector _49 // SquareBracketedClauseCS
			= new IndexVector(0x0L,0x1000000000L);
		private final @NonNull IndexVector _50 // StringLiteralExpCS
			= new IndexVector(0x0L,0x4000000000L);
		private final @NonNull IndexVector _51 // TemplateBindingCS
			= new IndexVector(0x0L,0x8000000000L);
		private final @NonNull IndexVector _52 // TemplateParameterSubstitutionCS
			= new IndexVector(0x0L,0x10000000000L);
		private final @NonNull IndexVector _53 // TemplateSignatureCS
			= new IndexVector(0x0L,0x20000000000L);
		private final @NonNull IndexVector _54 // TupleLiteralPartCS
			= new IndexVector(0x0L,0x80000000000L);
		private final @NonNull IndexVector _55 // TuplePartCS
			= new IndexVector(0x0L,0x100000000000L);
		private final @NonNull IndexVector _56 // TypeExpCS
			= new IndexVector(0x0L,0x400000000000L);
		private final @NonNull IndexVector _57 // TypeExpWithoutMultiplicityCS
			= new IndexVector(0x0L,0x800000000000L);
		private final @NonNull IndexVector _58 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
			= new IndexVector(0x800000000001000L,0x1200002000000L);
		private final @NonNull IndexVector _59 // TypeLiteralWithMultiplicityCS
			= new IndexVector(0x0L,0x4000000000000L);
		private final @NonNull IndexVector _60 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS
			= new IndexVector(0x800000000001000L,0x5200002000000L);
		private final @NonNull IndexVector _61 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new IndexVector(0x800000000001800L,0x9a00002000000L);
		private final @NonNull IndexVector _62 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new IndexVector(0x800000000001800L,0x9e00002000000L);
		private final @NonNull IndexVector _63 // TypeParameterCS
			= new IndexVector(0x0L,0x10000000000000L);
		private final @NonNull IndexVector _64 // TypeRefCS
			= new IndexVector(0x0L,0x20000000000000L);
		private final @NonNull IndexVector _65 // TypedMultiplicityRefCS
			= new IndexVector(0x0L,0x40000000000000L);
		private final @NonNull IndexVector _66 // TypedRefCS
			= new IndexVector(0x0L,0x80000000000000L);
		private final @NonNull IndexVector _67 // LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS
			= new IndexVector(0x800040000000000L,0x140200000000000L);
		private final @NonNull IndexVector _68 // LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS
			= new IndexVector(0x800040000000000L,0x180200000000000L);
		private final @NonNull IndexVector _69 // NextPathElementCS|URIFirstPathElementCS
			= new IndexVector(0x0L,0x800000000000400L);
		private final @NonNull IndexVector _70 // FirstPathElementCS|LibPathElementCS|NextPathElementCS|URIFirstPathElementCS
			= new IndexVector(0x8000010000000L,0x800000000000400L);
		private final @NonNull IndexVector _71 // URIPathNameCS
			= new IndexVector(0x0L,0x1000000000000000L);
		private final @NonNull IndexVector _72 // BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x1000000040L,0x4000004001001800L);
		private final @NonNull IndexVector _73 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x200021100000240L,0x4002044101801a04L);
		private final @NonNull IndexVector _74 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x200021100000240L,0x4002044101c01a04L);
		private final @NonNull IndexVector _75 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x2000a1100000240L,0x4002044101e01a04L);
		private final @NonNull IndexVector _76 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x2000a1108000240L,0x4002044101e01a04L);
		private final @NonNull IndexVector _77 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x2000a1108000240L,0x4002044101e01a14L);
		private final @NonNull IndexVector _78 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x2000a1108000240L,0x4002044101e21a04L);
		private final @NonNull IndexVector _79 // LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
			= new IndexVector(0x800040000000000L,0x1a0200000000000L,0x4L);
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
		private final @NonNull EnumerationValue _05 // 'Lambda'
			= new SingleEnumerationValue("Lambda");
		private final @NonNull EnumerationValue _06 // 'Map'
			= new SingleEnumerationValue("Map");
		private final @NonNull EnumerationValue _07 // 'Tuple'
			= new SingleEnumerationValue("Tuple");
		private final @NonNull EnumerationValue _08 // 'abstract'
			= new SingleEnumerationValue("abstract");
		private final @NonNull EnumerationValue _09 // 'false|true'
			= new MultipleEnumerationValue(new @NonNull String[]{"false", "true"});
		private final @NonNull EnumerationValue _10 // 'inv'
			= new SingleEnumerationValue("inv");
		private final @NonNull EnumerationValue _11 // 'invalidating'
			= new SingleEnumerationValue("invalidating");
		private final @NonNull EnumerationValue _12 // 'post'
			= new SingleEnumerationValue("post");
		private final @NonNull EnumerationValue _13 // 'pre'
			= new SingleEnumerationValue("pre");
		private final @NonNull EnumerationValue _14 // 'right'
			= new SingleEnumerationValue("right");
		private final @NonNull EnumerationValue _15 // 'static'
			= new SingleEnumerationValue("static");
		private final @NonNull EnumerationValue _16 // 'typeof'
			= new SingleEnumerationValue("typeof");
		private final @NonNull EnumerationValue _17 // 'validating'
			= new SingleEnumerationValue("validating");
		private final @NonNull EnumerationValue _18 // '|'
			= new SingleEnumerationValue("|");
		private final @NonNull EnumerationValue _19 // '|1'
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
		private final @NonNull CardinalitySolution _008 // |AnnotationElementCS::ownedDetails|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
		private final @NonNull CardinalitySolution _009 // |BooleanLiteralExpCS::symbol.'false|true'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._09);
		private final @NonNull CardinalitySolution _010 // |ClassCS::ownedConstraints|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		private final @NonNull CardinalitySolution _011 // |CollectionLiteralExpCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _012 // |CollectionLiteralExpCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _013 // |CollectionLiteralPartCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _014 // |CollectionLiteralPartCS::ownedLastExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		private final @NonNull CardinalitySolution _015 // |CollectionPatternCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _016 // |CollectionPatternCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _017 // |CollectionPatternCS::restVariableName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		private final @NonNull CardinalitySolution _018 // |CollectionTypeCS::name|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		private final @NonNull CardinalitySolution _019 // |CollectionTypeCS::ownedCollectionMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		private final @NonNull CardinalitySolution _020 // |CollectionTypeCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _021 // |ConstraintCS::ownedMessageSpecification|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		private final @NonNull CardinalitySolution _022 // |ConstraintCS::ownedSpecification|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		private final @NonNull CardinalitySolution _023 // |ConstraintCS::stereotype.'inv'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._10);
		private final @NonNull CardinalitySolution _024 // |ConstraintCS::stereotype.'post'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._12);
		private final @NonNull CardinalitySolution _025 // |ConstraintCS::stereotype.'pre'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._13);
		private final @NonNull CardinalitySolution _026 // |ContextCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _027 // |CurlyBracketedClauseCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _028 // |DetailCS::values|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.DETAIL_CS__VALUES);
		private final @NonNull CardinalitySolution _029 // |DocumentationCS::value|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
		private final @NonNull CardinalitySolution _030 // |ExpSpecificationCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _031 // |IfExpCS::ownedCondition|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		private final @NonNull CardinalitySolution _032 // |IfExpCS::ownedElseExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		private final @NonNull CardinalitySolution _033 // |IfExpCS::ownedIfThenExpressions|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		private final @NonNull CardinalitySolution _034 // |IfExpCS::ownedThenExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull CardinalitySolution _035 // |IfThenExpCS::ownedCondition|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		private final @NonNull CardinalitySolution _036 // |IfThenExpCS::ownedThenExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull CardinalitySolution _037 // |ImportCS::isAll.'::*'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, ev._02);
		private final @NonNull CardinalitySolution _038 // |ImportCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _039 // |InfixExpCS::ownedLeft|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		private final @NonNull CardinalitySolution _040 // |JavaImplementationCS::implementation|
			= new EStructuralFeatureSizeCardinalitySolution(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION);
		private final @NonNull CardinalitySolution _041 // |LambdaLiteralExpCS::ownedExpressionCS|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		private final @NonNull CardinalitySolution _042 // |LambdaTypeCS::name.'Lambda'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME, ev._05);
		private final @NonNull CardinalitySolution _043 // |LambdaTypeCS::ownedContextType|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE);
		private final @NonNull CardinalitySolution _044 // |LambdaTypeCS::ownedParameterTypes|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES);
		private final @NonNull CardinalitySolution _045 // |LambdaTypeCS::ownedResultType|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE);
		private final @NonNull CardinalitySolution _046 // |LetExpCS::ownedInExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		private final @NonNull CardinalitySolution _047 // |LetExpCS::ownedVariables|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		private final @NonNull CardinalitySolution _048 // |LetVariableCS::ownedRoundBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _049 // |LibClassCS::metaclassName|
			= new EStructuralFeatureSizeCardinalitySolution(OCLstdlibCSPackage.Literals.LIB_CLASS_CS__METACLASS_NAME);
		private final @NonNull CardinalitySolution _050 // |LibIterationCS::isInvalidating.'invalidating'|
			= new EAttributeSizeCardinalitySolution(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING, ev._11);
		private final @NonNull CardinalitySolution _051 // |LibIterationCS::isValidating.'validating'|
			= new EAttributeSizeCardinalitySolution(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING, ev._17);
		private final @NonNull CardinalitySolution _052 // |LibIterationCS::ownedAccumulators|
			= new EStructuralFeatureSizeCardinalitySolution(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS);
		private final @NonNull CardinalitySolution _053 // |LibIterationCS::ownedIterators|
			= new EStructuralFeatureSizeCardinalitySolution(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS);
		private final @NonNull CardinalitySolution _054 // |LibOperationCS::isInvalidating.'invalidating'|
			= new EAttributeSizeCardinalitySolution(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING, ev._11);
		private final @NonNull CardinalitySolution _055 // |LibOperationCS::isStatic.'static'|
			= new EAttributeSizeCardinalitySolution(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC, ev._15);
		private final @NonNull CardinalitySolution _056 // |LibOperationCS::isValidating.'validating'|
			= new EAttributeSizeCardinalitySolution(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING, ev._17);
		private final @NonNull CardinalitySolution _057 // |LibOperationCS::precedence|
			= new EStructuralFeatureSizeCardinalitySolution(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE);
		private final @NonNull CardinalitySolution _058 // |LibPackageCS::ownedPrecedences|
			= new EStructuralFeatureSizeCardinalitySolution(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES);
		private final @NonNull CardinalitySolution _059 // |LibPropertyCS::isStatic.'static'|
			= new EAttributeSizeCardinalitySolution(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC, ev._15);
		private final @NonNull CardinalitySolution _060 // |LibPropertyCS::ownedOpposite|
			= new EStructuralFeatureSizeCardinalitySolution(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE);
		private final @NonNull CardinalitySolution _061 // |MapLiteralExpCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _062 // |MapLiteralExpCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _063 // |MapLiteralPartCS::ownedKey|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		private final @NonNull CardinalitySolution _064 // |MapLiteralPartCS::ownedValue|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		private final @NonNull CardinalitySolution _065 // |MapTypeCS::name.'Map'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, ev._06);
		private final @NonNull CardinalitySolution _066 // |MapTypeCS::ownedKeyType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		private final @NonNull CardinalitySolution _067 // |MapTypeCS::ownedValueType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		private final @NonNull CardinalitySolution _068 // |ModelElementCS::ownedAnnotations|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull CardinalitySolution _069 // |MultiplicityBoundsCS::lowerBound|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull CardinalitySolution _070 // |MultiplicityBoundsCS::upperBound|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull CardinalitySolution _071 // |MultiplicityCS::isNullFree.'|1'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, ev._19);
		private final @NonNull CardinalitySolution _072 // |MultiplicityStringCS::stringBounds.'*|+|?'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._00);
		private final @NonNull CardinalitySolution _073 // |NamedElementCS::name|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull CardinalitySolution _074 // |NavigatingArgCS::ownedCoIterator|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		private final @NonNull CardinalitySolution _075 // |NavigatingArgCS::ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _076 // |NavigatingArgCS::ownedNameExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		private final @NonNull CardinalitySolution _077 // |NavigatingArgCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _078 // |NavigatingArgCS::prefix.','|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._01);
		private final @NonNull CardinalitySolution _079 // |NavigatingArgCS::prefix.';'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._03);
		private final @NonNull CardinalitySolution _080 // |NavigatingArgCS::prefix.'|'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._18);
		private final @NonNull CardinalitySolution _081 // |NestedExpCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _082 // |NumberLiteralExpCS::symbol|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull CardinalitySolution _083 // |OperationCS::ownedBodyExpressions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		private final @NonNull CardinalitySolution _084 // |OperationCS::ownedParameters|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
		private final @NonNull CardinalitySolution _085 // |OperationCS::ownedPostconditions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
		private final @NonNull CardinalitySolution _086 // |OperationCS::ownedPreconditions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		private final @NonNull CardinalitySolution _087 // |OperatorExpCS::ownedRight|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull CardinalitySolution _088 // |PackageCS::nsPrefix|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
		private final @NonNull CardinalitySolution _089 // |PackageCS::nsURI|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
		private final @NonNull CardinalitySolution _090 // |PackageCS::ownedClasses|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
		private final @NonNull CardinalitySolution _091 // |PackageOwnerCS::ownedPackages|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		private final @NonNull CardinalitySolution _092 // |PathElementCS::referredElement|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private final @NonNull CardinalitySolution _093 // |PathNameCS::ownedPathElements|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull CardinalitySolution _094 // |PatternExpCS::ownedPatternType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		private final @NonNull CardinalitySolution _095 // |PatternExpCS::patternVariableName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		private final @NonNull CardinalitySolution _096 // |PrecedenceCS::isRightAssociative.'right'|
			= new EAttributeSizeCardinalitySolution(OCLstdlibCSPackage.Literals.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE, ev._14);
		private final @NonNull CardinalitySolution _097 // |PrimitiveTypeRefCS::name|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		private final @NonNull CardinalitySolution _098 // |RootCS::ownedImports|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		private final @NonNull CardinalitySolution _099 // |RoundBracketedClauseCS::ownedArguments|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		private final @NonNull CardinalitySolution _100 // |ShadowPartCS::ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _101 // |ShadowPartCS::referredProperty|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		private final @NonNull CardinalitySolution _102 // |SquareBracketedClauseCS::ownedTerms|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		private final @NonNull CardinalitySolution _103 // |StringLiteralExpCS::segments|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		private final @NonNull CardinalitySolution _104 // |StructuredClassCS::isAbstract.'abstract'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, ev._08);
		private final @NonNull CardinalitySolution _105 // |StructuredClassCS::ownedOperations|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
		private final @NonNull CardinalitySolution _106 // |StructuredClassCS::ownedProperties|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
		private final @NonNull CardinalitySolution _107 // |StructuredClassCS::ownedSuperTypes|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
		private final @NonNull CardinalitySolution _108 // |TemplateBindingCS::ownedMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull CardinalitySolution _109 // |TemplateBindingCS::ownedSubstitutions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull CardinalitySolution _110 // |TemplateParameterSubstitutionCS::ownedActualParameter|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull CardinalitySolution _111 // |TemplateSignatureCS::ownedParameters|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull CardinalitySolution _112 // |TemplateableElementCS::ownedSignature|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		private final @NonNull CardinalitySolution _113 // |TupleLiteralExpCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _114 // |TupleTypeCS::name.'Tuple'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, ev._07);
		private final @NonNull CardinalitySolution _115 // |TupleTypeCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _116 // |TypeLiteralExpCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _117 // |TypeNameExpCS::ownedCurlyBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _118 // |TypeNameExpCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _119 // |TypeNameExpCS::ownedPatternGuard|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		private final @NonNull CardinalitySolution _120 // |TypeParameterCS::ownedExtends|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull CardinalitySolution _121 // |TypedElementCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _122 // |TypedRefCS::ownedMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull CardinalitySolution _123 // |TypedTypeRefCS::isTypeof.'typeof'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF, ev._16);
		private final @NonNull CardinalitySolution _124 // |TypedTypeRefCS::ownedBinding|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull CardinalitySolution _125 // |TypedTypeRefCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _126 // |VariableCS::ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _127 // |VariableCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _128 // |WildcardTypeRefCS::ownedExtends|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull CardinalitySolution _129 // (|AbstractNameExpCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_005, _001);
		private final @NonNull CardinalitySolution _130 // (|AnnotationElementCS::ownedDetails| - 1)
			= new SubtractCardinalitySolution(_008, _001);
		private final @NonNull CardinalitySolution _131 // (|AnnotationElementCS::ownedDetails| > 0)
			= new GreaterThanCardinalitySolution(_008, _000);
		private final @NonNull CardinalitySolution _132 // (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
			= new SubtractCardinalitySolution(_009, _001);
		private final @NonNull CardinalitySolution _133 // (|CollectionLiteralExpCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_011, _001);
		private final @NonNull CardinalitySolution _134 // (|CollectionLiteralExpCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_011, _000);
		private final @NonNull CardinalitySolution _135 // (|CollectionLiteralExpCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_012, _001);
		private final @NonNull CardinalitySolution _136 // (|CollectionLiteralPartCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_013, _001);
		private final @NonNull CardinalitySolution _137 // (|CollectionPatternCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_015, _001);
		private final @NonNull CardinalitySolution _138 // (|CollectionPatternCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_016, _001);
		private final @NonNull CardinalitySolution _139 // (|CollectionTypeCS::name| - 1)
			= new SubtractCardinalitySolution(_018, _001);
		private final @NonNull CardinalitySolution _140 // (|ConstraintCS::ownedSpecification| - 1)
			= new SubtractCardinalitySolution(_022, _001);
		private final @NonNull CardinalitySolution _141 // (|ConstraintCS::stereotype.'inv'| - 1)
			= new SubtractCardinalitySolution(_023, _001);
		private final @NonNull CardinalitySolution _142 // (|ConstraintCS::stereotype.'post'| - 1)
			= new SubtractCardinalitySolution(_024, _001);
		private final @NonNull CardinalitySolution _143 // (|ConstraintCS::stereotype.'pre'| - 1)
			= new SubtractCardinalitySolution(_025, _001);
		private final @NonNull CardinalitySolution _144 // (|ContextCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_026, _001);
		private final @NonNull CardinalitySolution _145 // (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_027, _001);
		private final @NonNull CardinalitySolution _146 // (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_027, _000);
		private final @NonNull CardinalitySolution _147 // (|ExpSpecificationCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_030, _001);
		private final @NonNull CardinalitySolution _148 // (|IfExpCS::ownedCondition| - 1)
			= new SubtractCardinalitySolution(_031, _001);
		private final @NonNull CardinalitySolution _149 // (|IfExpCS::ownedElseExpression| - 1)
			= new SubtractCardinalitySolution(_032, _001);
		private final @NonNull CardinalitySolution _150 // (|IfExpCS::ownedThenExpression| - 1)
			= new SubtractCardinalitySolution(_034, _001);
		private final @NonNull CardinalitySolution _151 // (|IfThenExpCS::ownedCondition| - 1)
			= new SubtractCardinalitySolution(_035, _001);
		private final @NonNull CardinalitySolution _152 // (|IfThenExpCS::ownedThenExpression| - 1)
			= new SubtractCardinalitySolution(_036, _001);
		private final @NonNull CardinalitySolution _153 // (|ImportCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_038, _001);
		private final @NonNull CardinalitySolution _154 // (|InfixExpCS::ownedLeft| - 1)
			= new SubtractCardinalitySolution(_039, _001);
		private final @NonNull CardinalitySolution _155 // (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
			= new SubtractCardinalitySolution(_041, _001);
		private final @NonNull CardinalitySolution _156 // (|LambdaTypeCS::name.'Lambda'| - 1)
			= new SubtractCardinalitySolution(_042, _001);
		private final @NonNull CardinalitySolution _157 // (|LambdaTypeCS::ownedContextType| - 1)
			= new SubtractCardinalitySolution(_043, _001);
		private final @NonNull CardinalitySolution _158 // (|LambdaTypeCS::ownedParameterTypes| - 1)
			= new SubtractCardinalitySolution(_044, _001);
		private final @NonNull CardinalitySolution _159 // (|LambdaTypeCS::ownedParameterTypes| > 0)
			= new GreaterThanCardinalitySolution(_044, _000);
		private final @NonNull CardinalitySolution _160 // (|LambdaTypeCS::ownedResultType| - 1)
			= new SubtractCardinalitySolution(_045, _001);
		private final @NonNull CardinalitySolution _161 // (|LetExpCS::ownedInExpression| - 1)
			= new SubtractCardinalitySolution(_046, _001);
		private final @NonNull CardinalitySolution _162 // (|LetExpCS::ownedVariables| - 1)
			= new SubtractCardinalitySolution(_047, _001);
		private final @NonNull CardinalitySolution _163 // (|LibIterationCS::ownedAccumulators| - 1)
			= new SubtractCardinalitySolution(_052, _001);
		private final @NonNull CardinalitySolution _164 // (|LibIterationCS::ownedAccumulators| > 0)
			= new GreaterThanCardinalitySolution(_052, _000);
		private final @NonNull CardinalitySolution _165 // (|LibIterationCS::ownedIterators| - 1)
			= new SubtractCardinalitySolution(_053, _001);
		private final @NonNull CardinalitySolution _166 // (|LibPackageCS::ownedPrecedences| > 0)
			= new GreaterThanCardinalitySolution(_058, _000);
		private final @NonNull CardinalitySolution _167 // (|MapLiteralExpCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_061, _001);
		private final @NonNull CardinalitySolution _168 // (|MapLiteralExpCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_061, _000);
		private final @NonNull CardinalitySolution _169 // (|MapLiteralExpCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_062, _001);
		private final @NonNull CardinalitySolution _170 // (|MapLiteralPartCS::ownedKey| - 1)
			= new SubtractCardinalitySolution(_063, _001);
		private final @NonNull CardinalitySolution _171 // (|MapLiteralPartCS::ownedValue| - 1)
			= new SubtractCardinalitySolution(_064, _001);
		private final @NonNull CardinalitySolution _172 // (|MapTypeCS::name.'Map'| - 1)
			= new SubtractCardinalitySolution(_065, _001);
		private final @NonNull CardinalitySolution _173 // (|MapTypeCS::ownedKeyType| - V0)
			= new SubtractCardinalitySolution(_066, _002);
		private final @NonNull CardinalitySolution _174 // (|ModelElementCS::ownedAnnotations| - 1)
			= new SubtractCardinalitySolution(_068, _001);
		private final @NonNull CardinalitySolution _175 // (|MultiplicityBoundsCS::lowerBound| - 1)
			= new SubtractCardinalitySolution(_069, _001);
		private final @NonNull CardinalitySolution _176 // (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
			= new SubtractCardinalitySolution(_072, _001);
		private final @NonNull CardinalitySolution _177 // (|NamedElementCS::name| - 1)
			= new SubtractCardinalitySolution(_073, _001);
		private final @NonNull CardinalitySolution _178 // (|NavigatingArgCS::ownedCoIterator| - 1)
			= new SubtractCardinalitySolution(_074, _001);
		private final @NonNull CardinalitySolution _179 // (|NavigatingArgCS::ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_075, _001);
		private final @NonNull CardinalitySolution _180 // (|NavigatingArgCS::ownedNameExpression| - 1)
			= new SubtractCardinalitySolution(_076, _001);
		private final @NonNull CardinalitySolution _181 // (|NavigatingArgCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_077, _001);
		private final @NonNull CardinalitySolution _182 // (|NavigatingArgCS::prefix.','| - 1)
			= new SubtractCardinalitySolution(_078, _001);
		private final @NonNull CardinalitySolution _183 // (|NavigatingArgCS::prefix.';'| - 1)
			= new SubtractCardinalitySolution(_079, _001);
		private final @NonNull CardinalitySolution _184 // (|NavigatingArgCS::prefix.'|'| - 1)
			= new SubtractCardinalitySolution(_080, _001);
		private final @NonNull CardinalitySolution _185 // (|NestedExpCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_081, _001);
		private final @NonNull CardinalitySolution _186 // (|NumberLiteralExpCS::symbol| - 1)
			= new SubtractCardinalitySolution(_082, _001);
		private final @NonNull CardinalitySolution _187 // (|OperationCS::ownedParameters| - 1)
			= new SubtractCardinalitySolution(_084, _001);
		private final @NonNull CardinalitySolution _188 // (|OperationCS::ownedParameters| > 0)
			= new GreaterThanCardinalitySolution(_084, _000);
		private final @NonNull CardinalitySolution _189 // (|OperatorExpCS::ownedRight| - 1)
			= new SubtractCardinalitySolution(_087, _001);
		private final @NonNull CardinalitySolution _190 // (|PackageCS::nsPrefix| - V0)
			= new SubtractCardinalitySolution(_088, _002);
		private final @NonNull CardinalitySolution _191 // (|PathElementCS::referredElement| - 1)
			= new SubtractCardinalitySolution(_092, _001);
		private final @NonNull CardinalitySolution _192 // (|PathNameCS::ownedPathElements| - 1)
			= new SubtractCardinalitySolution(_093, _001);
		private final @NonNull CardinalitySolution _193 // (|PatternExpCS::ownedPatternType| - 1)
			= new SubtractCardinalitySolution(_094, _001);
		private final @NonNull CardinalitySolution _194 // (|PrecedenceCS::isRightAssociative.'right'| - 1)
			= new SubtractCardinalitySolution(_096, _001);
		private final @NonNull CardinalitySolution _195 // (|PrimitiveTypeRefCS::name| - 1)
			= new SubtractCardinalitySolution(_097, _001);
		private final @NonNull CardinalitySolution _196 // (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new SubtractCardinalitySolution(_099, _001);
		private final @NonNull CardinalitySolution _197 // (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new GreaterThanCardinalitySolution(_099, _000);
		private final @NonNull CardinalitySolution _198 // (|ShadowPartCS::ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_100, _001);
		private final @NonNull CardinalitySolution _199 // (|ShadowPartCS::referredProperty| - 1)
			= new SubtractCardinalitySolution(_101, _001);
		private final @NonNull CardinalitySolution _200 // (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new SubtractCardinalitySolution(_102, _001);
		private final @NonNull CardinalitySolution _201 // (|StructuredClassCS::ownedSuperTypes| - 1)
			= new SubtractCardinalitySolution(_107, _001);
		private final @NonNull CardinalitySolution _202 // (|StructuredClassCS::ownedSuperTypes| > 0)
			= new GreaterThanCardinalitySolution(_107, _000);
		private final @NonNull CardinalitySolution _203 // (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new SubtractCardinalitySolution(_109, _001);
		private final @NonNull CardinalitySolution _204 // (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
			= new SubtractCardinalitySolution(_110, _001);
		private final @NonNull CardinalitySolution _205 // (|TemplateSignatureCS::ownedParameters| - 1)
			= new SubtractCardinalitySolution(_111, _001);
		private final @NonNull CardinalitySolution _206 // (|TupleLiteralExpCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_113, _001);
		private final @NonNull CardinalitySolution _207 // (|TupleTypeCS::name.'Tuple'| - 1)
			= new SubtractCardinalitySolution(_114, _001);
		private final @NonNull CardinalitySolution _208 // (|TupleTypeCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_115, _001);
		private final @NonNull CardinalitySolution _209 // (|TupleTypeCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_115, _000);
		private final @NonNull CardinalitySolution _210 // (|TypeLiteralExpCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_116, _001);
		private final @NonNull CardinalitySolution _211 // (|TypeNameExpCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_118, _001);
		private final @NonNull CardinalitySolution _212 // (|TypeParameterCS::ownedExtends| - 1)
			= new SubtractCardinalitySolution(_120, _001);
		private final @NonNull CardinalitySolution _213 // (|TypeParameterCS::ownedExtends| > 0)
			= new GreaterThanCardinalitySolution(_120, _000);
		private final @NonNull CardinalitySolution _214 // (|TypedElementCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_121, _001);
		private final @NonNull CardinalitySolution _215 // (|TypedTypeRefCS::isTypeof.'typeof'| - 1)
			= new SubtractCardinalitySolution(_123, _001);
		private final @NonNull CardinalitySolution _216 // (|TypedTypeRefCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_125, _001);
		private final @NonNull CardinalitySolution _217 // (|VariableCS::ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_126, _001);
	}

	/**
	 * Steps for the matching process.
	 */
	private class _MatchSteps
	{
		private final @NonNull CardinalitySolutionStep _000 // assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._129);
		private final @NonNull CardinalitySolutionStep _001 // assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._132);
		private final @NonNull CardinalitySolutionStep _002 // assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._135);
		private final @NonNull CardinalitySolutionStep _003 // assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._136);
		private final @NonNull CardinalitySolutionStep _004 // assert (|CollectionPatternCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._138);
		private final @NonNull CardinalitySolutionStep _005 // assert (|CollectionTypeCS::name| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._139);
		private final @NonNull CardinalitySolutionStep _006 // assert (|ConstraintCS::ownedSpecification| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._140);
		private final @NonNull CardinalitySolutionStep _007 // assert (|ConstraintCS::stereotype.'inv'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._141);
		private final @NonNull CardinalitySolutionStep _008 // assert (|ConstraintCS::stereotype.'post'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._142);
		private final @NonNull CardinalitySolutionStep _009 // assert (|ConstraintCS::stereotype.'pre'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._143);
		private final @NonNull CardinalitySolutionStep _010 // assert (|ContextCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._144);
		private final @NonNull CardinalitySolutionStep _011 // assert (|ExpSpecificationCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._147);
		private final @NonNull CardinalitySolutionStep _012 // assert (|IfExpCS::ownedCondition| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._148);
		private final @NonNull CardinalitySolutionStep _013 // assert (|IfExpCS::ownedElseExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._149);
		private final @NonNull CardinalitySolutionStep _014 // assert (|IfExpCS::ownedThenExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._150);
		private final @NonNull CardinalitySolutionStep _015 // assert (|IfThenExpCS::ownedCondition| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._151);
		private final @NonNull CardinalitySolutionStep _016 // assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._152);
		private final @NonNull CardinalitySolutionStep _017 // assert (|ImportCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._153);
		private final @NonNull CardinalitySolutionStep _018 // assert (|InfixExpCS::ownedLeft| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._154);
		private final @NonNull CardinalitySolutionStep _019 // assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._155);
		private final @NonNull CardinalitySolutionStep _020 // assert (|LambdaTypeCS::name.'Lambda'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._156);
		private final @NonNull CardinalitySolutionStep _021 // assert (|LambdaTypeCS::ownedContextType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._157);
		private final @NonNull CardinalitySolutionStep _022 // assert (|LambdaTypeCS::ownedResultType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._160);
		private final @NonNull CardinalitySolutionStep _023 // assert (|LetExpCS::ownedInExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._161);
		private final @NonNull CardinalitySolutionStep _024 // assert (|MapLiteralExpCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._169);
		private final @NonNull CardinalitySolutionStep _025 // assert (|MapLiteralPartCS::ownedKey| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._170);
		private final @NonNull CardinalitySolutionStep _026 // assert (|MapLiteralPartCS::ownedValue| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._171);
		private final @NonNull CardinalitySolutionStep _027 // assert (|MapTypeCS::name.'Map'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._172);
		private final @NonNull CardinalitySolutionStep _028 // assert (|MapTypeCS::ownedKeyType| - V0) == 0
			= new CardinalitySolutionStep_Assert(mt._173);
		private final @NonNull CardinalitySolutionStep _029 // assert (|ModelElementCS::ownedAnnotations| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._174);
		private final @NonNull CardinalitySolutionStep _030 // assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._175);
		private final @NonNull CardinalitySolutionStep _031 // assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._176);
		private final @NonNull CardinalitySolutionStep _032 // assert (|NamedElementCS::name| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._177);
		private final @NonNull CardinalitySolutionStep _033 // assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._178);
		private final @NonNull CardinalitySolutionStep _034 // assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._179);
		private final @NonNull CardinalitySolutionStep _035 // assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._180);
		private final @NonNull CardinalitySolutionStep _036 // assert (|NavigatingArgCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._181);
		private final @NonNull CardinalitySolutionStep _037 // assert (|NavigatingArgCS::prefix.','| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._182);
		private final @NonNull CardinalitySolutionStep _038 // assert (|NavigatingArgCS::prefix.';'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._183);
		private final @NonNull CardinalitySolutionStep _039 // assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._184);
		private final @NonNull CardinalitySolutionStep _040 // assert (|NestedExpCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._185);
		private final @NonNull CardinalitySolutionStep _041 // assert (|NumberLiteralExpCS::symbol| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._186);
		private final @NonNull CardinalitySolutionStep _042 // assert (|OperatorExpCS::ownedRight| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._189);
		private final @NonNull CardinalitySolutionStep _043 // assert (|PackageCS::nsPrefix| - V0) == 0
			= new CardinalitySolutionStep_Assert(mt._190);
		private final @NonNull CardinalitySolutionStep _044 // assert (|PathElementCS::referredElement| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._191);
		private final @NonNull CardinalitySolutionStep _045 // assert (|PathNameCS::ownedPathElements| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._192);
		private final @NonNull CardinalitySolutionStep _046 // assert (|PatternExpCS::ownedPatternType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._193);
		private final @NonNull CardinalitySolutionStep _047 // assert (|PrecedenceCS::isRightAssociative.'right'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._194);
		private final @NonNull CardinalitySolutionStep _048 // assert (|PrimitiveTypeRefCS::name| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._195);
		private final @NonNull CardinalitySolutionStep _049 // assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._198);
		private final @NonNull CardinalitySolutionStep _050 // assert (|ShadowPartCS::referredProperty| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._199);
		private final @NonNull CardinalitySolutionStep _051 // assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._204);
		private final @NonNull CardinalitySolutionStep _052 // assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._207);
		private final @NonNull CardinalitySolutionStep _053 // assert (|TypeLiteralExpCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._210);
		private final @NonNull CardinalitySolutionStep _054 // assert (|TypeNameExpCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._211);
		private final @NonNull CardinalitySolutionStep _055 // assert (|TypedElementCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._214);
		private final @NonNull CardinalitySolutionStep _056 // assert (|TypedTypeRefCS::isTypeof.'typeof'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._215);
		private final @NonNull CardinalitySolutionStep _057 // assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._216);
		private final @NonNull CardinalitySolutionStep _058 // assert (|VariableCS::ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._217);
		private final @NonNull CardinalitySolutionStep _059 // assign V0 = (|AnnotationElementCS::ownedDetails| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._131);
		private final @NonNull CardinalitySolutionStep _060 // assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._134);
		private final @NonNull CardinalitySolutionStep _061 // assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._146);
		private final @NonNull CardinalitySolutionStep _062 // assign V0 = (|LetExpCS::ownedVariables| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._162);
		private final @NonNull CardinalitySolutionStep _063 // assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._168);
		private final @NonNull CardinalitySolutionStep _064 // assign V0 = (|PathNameCS::ownedPathElements| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._192);
		private final @NonNull CardinalitySolutionStep _065 // assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._197);
		private final @NonNull CardinalitySolutionStep _066 // assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._200);
		private final @NonNull CardinalitySolutionStep _067 // assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._203);
		private final @NonNull CardinalitySolutionStep _068 // assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._205);
		private final @NonNull CardinalitySolutionStep _069 // assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._206);
		private final @NonNull CardinalitySolutionStep _070 // assign V0 = (|TupleTypeCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._209);
		private final @NonNull CardinalitySolutionStep _071 // assign V0 = (|TypeParameterCS::ownedExtends| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._213);
		private final @NonNull CardinalitySolutionStep _072 // assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
			= new CardinalitySolutionStep_Assign(0, mt._007);
		private final @NonNull CardinalitySolutionStep _073 // assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
			= new CardinalitySolutionStep_Assign(0, mt._014);
		private final @NonNull CardinalitySolutionStep _074 // assign V0 = |CollectionPatternCS::restVariableName|
			= new CardinalitySolutionStep_Assign(0, mt._017);
		private final @NonNull CardinalitySolutionStep _075 // assign V0 = |CollectionTypeCS::ownedType|
			= new CardinalitySolutionStep_Assign(0, mt._020);
		private final @NonNull CardinalitySolutionStep _076 // assign V0 = |DetailCS::values|
			= new CardinalitySolutionStep_Assign(0, mt._028);
		private final @NonNull CardinalitySolutionStep _077 // assign V0 = |DocumentationCS::value|
			= new CardinalitySolutionStep_Assign(0, mt._029);
		private final @NonNull CardinalitySolutionStep _078 // assign V0 = |IfExpCS::ownedIfThenExpressions|
			= new CardinalitySolutionStep_Assign(0, mt._033);
		private final @NonNull CardinalitySolutionStep _079 // assign V0 = |JavaImplementationCS::implementation|
			= new CardinalitySolutionStep_Assign(0, mt._040);
		private final @NonNull CardinalitySolutionStep _080 // assign V0 = |LetVariableCS::ownedRoundBracketedClause|
			= new CardinalitySolutionStep_Assign(0, mt._048);
		private final @NonNull CardinalitySolutionStep _081 // assign V0 = |LibOperationCS::isStatic.'static'|
			= new CardinalitySolutionStep_Assign(0, mt._055);
		private final @NonNull CardinalitySolutionStep _082 // assign V0 = |LibPropertyCS::isStatic.'static'|
			= new CardinalitySolutionStep_Assign(0, mt._059);
		private final @NonNull CardinalitySolutionStep _083 // assign V0 = |MapTypeCS::ownedValueType|
			= new CardinalitySolutionStep_Assign(0, mt._067);
		private final @NonNull CardinalitySolutionStep _084 // assign V0 = |MultiplicityBoundsCS::upperBound|
			= new CardinalitySolutionStep_Assign(0, mt._070);
		private final @NonNull CardinalitySolutionStep _085 // assign V0 = |MultiplicityCS::isNullFree.'|1'|
			= new CardinalitySolutionStep_Assign(0, mt._071);
		private final @NonNull CardinalitySolutionStep _086 // assign V0 = |NamedElementCS::name|
			= new CardinalitySolutionStep_Assign(0, mt._073);
		private final @NonNull CardinalitySolutionStep _087 // assign V0 = |NavigatingArgCS::ownedCoIterator|
			= new CardinalitySolutionStep_Assign(0, mt._074);
		private final @NonNull CardinalitySolutionStep _088 // assign V0 = |NavigatingArgCS::ownedInitExpression|
			= new CardinalitySolutionStep_Assign(0, mt._075);
		private final @NonNull CardinalitySolutionStep _089 // assign V0 = |NavigatingArgCS::ownedType|
			= new CardinalitySolutionStep_Assign(0, mt._077);
		private final @NonNull CardinalitySolutionStep _090 // assign V0 = |PackageCS::nsURI|
			= new CardinalitySolutionStep_Assign(0, mt._089);
		private final @NonNull CardinalitySolutionStep _091 // assign V0 = |PatternExpCS::patternVariableName|
			= new CardinalitySolutionStep_Assign(0, mt._095);
		private final @NonNull CardinalitySolutionStep _092 // assign V0 = |RootCS::ownedImports|
			= new CardinalitySolutionStep_Assign(0, mt._098);
		private final @NonNull CardinalitySolutionStep _093 // assign V0 = |StringLiteralExpCS::segments|
			= new CardinalitySolutionStep_Assign(0, mt._103);
		private final @NonNull CardinalitySolutionStep _094 // assign V0 = |StructuredClassCS::isAbstract.'abstract'|
			= new CardinalitySolutionStep_Assign(0, mt._104);
		private final @NonNull CardinalitySolutionStep _095 // assign V0 = |TemplateableElementCS::ownedSignature|
			= new CardinalitySolutionStep_Assign(0, mt._112);
		private final @NonNull CardinalitySolutionStep _096 // assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
			= new CardinalitySolutionStep_Assign(0, mt._117);
		private final @NonNull CardinalitySolutionStep _097 // assign V0 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(0, mt._122);
		private final @NonNull CardinalitySolutionStep _098 // assign V0 = |TypedTypeRefCS::ownedBinding|
			= new CardinalitySolutionStep_Assign(0, mt._124);
		private final @NonNull CardinalitySolutionStep _099 // assign V0 = |VariableCS::ownedType|
			= new CardinalitySolutionStep_Assign(0, mt._127);
		private final @NonNull CardinalitySolutionStep _100 // assign V0 = |WildcardTypeRefCS::ownedExtends|
			= new CardinalitySolutionStep_Assign(0, mt._128);
		private final @NonNull CardinalitySolutionStep _101 // assign V1 = (|AnnotationElementCS::ownedDetails| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._130);
		private final @NonNull CardinalitySolutionStep _102 // assign V1 = (|AnnotationElementCS::ownedDetails| > 0)
			= new CardinalitySolutionStep_Assign(1, mt._131);
		private final @NonNull CardinalitySolutionStep _103 // assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._133);
		private final @NonNull CardinalitySolutionStep _104 // assign V1 = (|CollectionPatternCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._137);
		private final @NonNull CardinalitySolutionStep _105 // assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._145);
		private final @NonNull CardinalitySolutionStep _106 // assign V1 = (|LambdaTypeCS::ownedParameterTypes| > 0)
			= new CardinalitySolutionStep_Assign(1, mt._159);
		private final @NonNull CardinalitySolutionStep _107 // assign V1 = (|LibIterationCS::ownedIterators| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._165);
		private final @NonNull CardinalitySolutionStep _108 // assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._167);
		private final @NonNull CardinalitySolutionStep _109 // assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._196);
		private final @NonNull CardinalitySolutionStep _110 // assign V1 = (|TupleTypeCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(1, mt._209);
		private final @NonNull CardinalitySolutionStep _111 // assign V1 = (|TypeParameterCS::ownedExtends| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._212);
		private final @NonNull CardinalitySolutionStep _112 // assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
			= new CardinalitySolutionStep_Assign(1, mt._006);
		private final @NonNull CardinalitySolutionStep _113 // assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
			= new CardinalitySolutionStep_Assign(1, mt._019);
		private final @NonNull CardinalitySolutionStep _114 // assign V1 = |ConstraintCS::ownedMessageSpecification|
			= new CardinalitySolutionStep_Assign(1, mt._021);
		private final @NonNull CardinalitySolutionStep _115 // assign V1 = |ImportCS::isAll.'::*'|
			= new CardinalitySolutionStep_Assign(1, mt._037);
		private final @NonNull CardinalitySolutionStep _116 // assign V1 = |LibPropertyCS::ownedOpposite|
			= new CardinalitySolutionStep_Assign(1, mt._060);
		private final @NonNull CardinalitySolutionStep _117 // assign V1 = |ModelElementCS::ownedAnnotations|
			= new CardinalitySolutionStep_Assign(1, mt._068);
		private final @NonNull CardinalitySolutionStep _118 // assign V1 = |MultiplicityCS::isNullFree.'|1'|
			= new CardinalitySolutionStep_Assign(1, mt._071);
		private final @NonNull CardinalitySolutionStep _119 // assign V1 = |NavigatingArgCS::ownedCoIterator|
			= new CardinalitySolutionStep_Assign(1, mt._074);
		private final @NonNull CardinalitySolutionStep _120 // assign V1 = |NavigatingArgCS::ownedInitExpression|
			= new CardinalitySolutionStep_Assign(1, mt._075);
		private final @NonNull CardinalitySolutionStep _121 // assign V1 = |PackageOwnerCS::ownedPackages|
			= new CardinalitySolutionStep_Assign(1, mt._091);
		private final @NonNull CardinalitySolutionStep _122 // assign V1 = |TemplateBindingCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(1, mt._108);
		private final @NonNull CardinalitySolutionStep _123 // assign V1 = |TemplateableElementCS::ownedSignature|
			= new CardinalitySolutionStep_Assign(1, mt._112);
		private final @NonNull CardinalitySolutionStep _124 // assign V1 = |TypeNameExpCS::ownedPatternGuard|
			= new CardinalitySolutionStep_Assign(1, mt._119);
		private final @NonNull CardinalitySolutionStep _125 // assign V1 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(1, mt._122);
		private final @NonNull CardinalitySolutionStep _126 // assign V1 = |VariableCS::ownedType|
			= new CardinalitySolutionStep_Assign(1, mt._127);
		private final @NonNull CardinalitySolutionStep _127 // assign V10 = |OperationCS::ownedPostconditions|
			= new CardinalitySolutionStep_Assign(10, mt._085);
		private final @NonNull CardinalitySolutionStep _128 // assign V10 = |OperationCS::ownedPreconditions|
			= new CardinalitySolutionStep_Assign(10, mt._086);
		private final @NonNull CardinalitySolutionStep _129 // assign V11 = |OperationCS::ownedPostconditions|
			= new CardinalitySolutionStep_Assign(11, mt._085);
		private final @NonNull CardinalitySolutionStep _130 // assign V11 = |OperationCS::ownedPreconditions|
			= new CardinalitySolutionStep_Assign(11, mt._086);
		private final @NonNull CardinalitySolutionStep _131 // assign V2 = (|AnnotationElementCS::ownedDetails| - 1)
			= new CardinalitySolutionStep_Assign(2, mt._130);
		private final @NonNull CardinalitySolutionStep _132 // assign V2 = (|LambdaTypeCS::ownedParameterTypes| - 1)
			= new CardinalitySolutionStep_Assign(2, mt._158);
		private final @NonNull CardinalitySolutionStep _133 // assign V2 = (|LibIterationCS::ownedAccumulators| > 0)
			= new CardinalitySolutionStep_Assign(2, mt._164);
		private final @NonNull CardinalitySolutionStep _134 // assign V2 = (|LibPackageCS::ownedPrecedences| > 0)
			= new CardinalitySolutionStep_Assign(2, mt._166);
		private final @NonNull CardinalitySolutionStep _135 // assign V2 = (|OperationCS::ownedParameters| > 0)
			= new CardinalitySolutionStep_Assign(2, mt._188);
		private final @NonNull CardinalitySolutionStep _136 // assign V2 = (|TupleTypeCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(2, mt._208);
		private final @NonNull CardinalitySolutionStep _137 // assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
			= new CardinalitySolutionStep_Assign(2, mt._004);
		private final @NonNull CardinalitySolutionStep _138 // assign V2 = |JavaImplementationCS::implementation|
			= new CardinalitySolutionStep_Assign(2, mt._040);
		private final @NonNull CardinalitySolutionStep _139 // assign V2 = |LibClassCS::metaclassName|
			= new CardinalitySolutionStep_Assign(2, mt._049);
		private final @NonNull CardinalitySolutionStep _140 // assign V2 = |OperationCS::ownedPreconditions|
			= new CardinalitySolutionStep_Assign(2, mt._086);
		private final @NonNull CardinalitySolutionStep _141 // assign V2 = |PackageCS::ownedClasses|
			= new CardinalitySolutionStep_Assign(2, mt._090);
		private final @NonNull CardinalitySolutionStep _142 // assign V2 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(2, mt._122);
		private final @NonNull CardinalitySolutionStep _143 // assign V3 = (|LibIterationCS::ownedAccumulators| - 1)
			= new CardinalitySolutionStep_Assign(3, mt._163);
		private final @NonNull CardinalitySolutionStep _144 // assign V3 = (|OperationCS::ownedParameters| - 1)
			= new CardinalitySolutionStep_Assign(3, mt._187);
		private final @NonNull CardinalitySolutionStep _145 // assign V3 = (|StructuredClassCS::ownedSuperTypes| > 0)
			= new CardinalitySolutionStep_Assign(3, mt._202);
		private final @NonNull CardinalitySolutionStep _146 // assign V3 = |AbstractNameExpCS::isPre.'@'|
			= new CardinalitySolutionStep_Assign(3, mt._003);
		private final @NonNull CardinalitySolutionStep _147 // assign V3 = |LibPackageCS::ownedPrecedences|
			= new CardinalitySolutionStep_Assign(3, mt._058);
		private final @NonNull CardinalitySolutionStep _148 // assign V3 = |ModelElementCS::ownedAnnotations|
			= new CardinalitySolutionStep_Assign(3, mt._068);
		private final @NonNull CardinalitySolutionStep _149 // assign V3 = |OperationCS::ownedPostconditions|
			= new CardinalitySolutionStep_Assign(3, mt._085);
		private final @NonNull CardinalitySolutionStep _150 // assign V3 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(3, mt._122);
		private final @NonNull CardinalitySolutionStep _151 // assign V4 = (|OperationCS::ownedParameters| > 0)
			= new CardinalitySolutionStep_Assign(4, mt._188);
		private final @NonNull CardinalitySolutionStep _152 // assign V4 = (|StructuredClassCS::ownedSuperTypes| - 1)
			= new CardinalitySolutionStep_Assign(4, mt._201);
		private final @NonNull CardinalitySolutionStep _153 // assign V4 = |LibOperationCS::isValidating.'validating'|
			= new CardinalitySolutionStep_Assign(4, mt._056);
		private final @NonNull CardinalitySolutionStep _154 // assign V4 = |PackageCS::ownedClasses|
			= new CardinalitySolutionStep_Assign(4, mt._090);
		private final @NonNull CardinalitySolutionStep _155 // assign V5 = (|OperationCS::ownedParameters| - 1)
			= new CardinalitySolutionStep_Assign(5, mt._187);
		private final @NonNull CardinalitySolutionStep _156 // assign V5 = |LibOperationCS::isInvalidating.'invalidating'|
			= new CardinalitySolutionStep_Assign(5, mt._054);
		private final @NonNull CardinalitySolutionStep _157 // assign V5 = |ModelElementCS::ownedAnnotations|
			= new CardinalitySolutionStep_Assign(5, mt._068);
		private final @NonNull CardinalitySolutionStep _158 // assign V5 = |StructuredClassCS::ownedOperations|
			= new CardinalitySolutionStep_Assign(5, mt._105);
		private final @NonNull CardinalitySolutionStep _159 // assign V6 = |LibIterationCS::isInvalidating.'invalidating'|
			= new CardinalitySolutionStep_Assign(6, mt._050);
		private final @NonNull CardinalitySolutionStep _160 // assign V6 = |LibOperationCS::precedence|
			= new CardinalitySolutionStep_Assign(6, mt._057);
		private final @NonNull CardinalitySolutionStep _161 // assign V6 = |StructuredClassCS::ownedProperties|
			= new CardinalitySolutionStep_Assign(6, mt._106);
		private final @NonNull CardinalitySolutionStep _162 // assign V7 = |ClassCS::ownedConstraints|
			= new CardinalitySolutionStep_Assign(7, mt._010);
		private final @NonNull CardinalitySolutionStep _163 // assign V7 = |JavaImplementationCS::implementation|
			= new CardinalitySolutionStep_Assign(7, mt._040);
		private final @NonNull CardinalitySolutionStep _164 // assign V7 = |LibIterationCS::isValidating.'validating'|
			= new CardinalitySolutionStep_Assign(7, mt._051);
		private final @NonNull CardinalitySolutionStep _165 // assign V8 = |JavaImplementationCS::implementation|
			= new CardinalitySolutionStep_Assign(8, mt._040);
		private final @NonNull CardinalitySolutionStep _166 // assign V8 = |ModelElementCS::ownedAnnotations|
			= new CardinalitySolutionStep_Assign(8, mt._068);
		private final @NonNull CardinalitySolutionStep _167 // assign V9 = |ModelElementCS::ownedAnnotations|
			= new CardinalitySolutionStep_Assign(9, mt._068);
		private final @NonNull CardinalitySolutionStep _168 // assign V9 = |OperationCS::ownedBodyExpressions|
			= new CardinalitySolutionStep_Assign(9, mt._083);
		private final @NonNull CardinalitySolutionStep _169 // check-rule basecs::AnnotationElementCS.ownedDetails : 16
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, iv._7/*DetailCS*/);
		private final @NonNull CardinalitySolutionStep _170 // check-rule basecs::ClassCS.ownedConstraints : 35
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, iv._13/*InvCS*/);
		private final @NonNull CardinalitySolutionStep _171 // check-rule basecs::ConstraintCS.ownedMessageSpecification : 99
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, iv._48/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _172 // check-rule basecs::ConstraintCS.ownedSpecification : 99
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, iv._48/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _173 // check-rule basecs::ImportCS.ownedPathName : 124
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, iv._71/*URIPathNameCS*/);
		private final @NonNull CardinalitySolutionStep _174 // check-rule basecs::LambdaTypeCS.ownedContextType : 40
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, iv._15/*LambdaContextTypeRefCS*/);
		private final @NonNull CardinalitySolutionStep _175 // check-rule basecs::LambdaTypeCS.ownedParameterTypes : 118
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES, iv._65/*TypedMultiplicityRefCS*/);
		private final @NonNull CardinalitySolutionStep _176 // check-rule basecs::LambdaTypeCS.ownedResultType : 119
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, iv._66/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _177 // check-rule basecs::ModelElementCS.ownedAnnotations : 3
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, iv._1/*AnnotationElementCS*/);
		private final @NonNull CardinalitySolutionStep _178 // check-rule basecs::OperationCS.ownedBodyExpressions : 99
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, iv._48/*SpecificationCS*/);
		private final @NonNull CardinalitySolutionStep _179 // check-rule basecs::OperationCS.ownedParameters : 79
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, iv._33/*ParameterCS*/);
		private final @NonNull CardinalitySolutionStep _180 // check-rule basecs::OperationCS.ownedPostconditions : 82
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, iv._37/*PostCS*/);
		private final @NonNull CardinalitySolutionStep _181 // check-rule basecs::OperationCS.ownedPostconditions : 83
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, iv._38/*PreCS*/);
		private final @NonNull CardinalitySolutionStep _182 // check-rule basecs::OperationCS.ownedPreconditions : 82
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, iv._37/*PostCS*/);
		private final @NonNull CardinalitySolutionStep _183 // check-rule basecs::OperationCS.ownedPreconditions : 83
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, iv._38/*PreCS*/);
		private final @NonNull CardinalitySolutionStep _184 // check-rule basecs::PackageCS.ownedClasses : 7
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, iv._2/*ClassCS*/);
		private final @NonNull CardinalitySolutionStep _185 // check-rule basecs::PackageOwnerCS.ownedPackages : 50
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, iv._19/*LibPackageCS*/);
		private final @NonNull CardinalitySolutionStep _186 // check-rule basecs::PackageOwnerCS.ownedPackages : 78
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, iv._32/*PackageCS*/);
		private final @NonNull CardinalitySolutionStep _187 // check-rule basecs::PathNameCS.ownedPathElements : 28
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._11/*FirstPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _188 // check-rule basecs::PathNameCS.ownedPathElements : 28|74
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._29/*FirstPathElementCS|NextPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _189 // check-rule basecs::PathNameCS.ownedPathElements : 51
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._20/*LibPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _190 // check-rule basecs::PathNameCS.ownedPathElements : 74|123
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._69/*NextPathElementCS|URIFirstPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _191 // check-rule basecs::RootCS.ownedImports : 33
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, iv._12/*ImportCS*/);
		private final @NonNull CardinalitySolutionStep _192 // check-rule basecs::StructuredClassCS.ownedOperations : 77
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, iv._30/*OperationCS*/);
		private final @NonNull CardinalitySolutionStep _193 // check-rule basecs::StructuredClassCS.ownedProperties : 53
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, iv._22/*LibPropertyCS*/);
		private final @NonNull CardinalitySolutionStep _194 // check-rule basecs::StructuredClassCS.ownedSuperTypes : 119
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, iv._66/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _195 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : 62
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, iv._25/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _196 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : 104
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, iv._52/*TemplateParameterSubstitutionCS*/);
		private final @NonNull CardinalitySolutionStep _197 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 117
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, iv._64/*TypeRefCS*/);
		private final @NonNull CardinalitySolutionStep _198 // check-rule basecs::TemplateSignatureCS.ownedParameters : 116
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, iv._63/*TypeParameterCS*/);
		private final @NonNull CardinalitySolutionStep _199 // check-rule basecs::TemplateableElementCS.ownedSignature : 105
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, iv._53/*TemplateSignatureCS*/);
		private final @NonNull CardinalitySolutionStep _200 // check-rule basecs::TupleTypeCS.ownedParts : 108
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, iv._55/*TuplePartCS*/);
		private final @NonNull CardinalitySolutionStep _201 // check-rule basecs::TypeParameterCS.ownedExtends : 119
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, iv._66/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _202 // check-rule basecs::TypedElementCS.ownedType : 118
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, iv._65/*TypedMultiplicityRefCS*/);
		private final @NonNull CardinalitySolutionStep _203 // check-rule basecs::TypedRefCS.ownedMultiplicity : 62
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, iv._25/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _204 // check-rule basecs::TypedTypeRefCS.ownedBinding : 103
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, iv._51/*TemplateBindingCS*/);
		private final @NonNull CardinalitySolutionStep _205 // check-rule basecs::TypedTypeRefCS.ownedPathName : 52
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, iv._21/*LibPathNameCS*/);
		private final @NonNull CardinalitySolutionStep _206 // check-rule basecs::WildcardTypeRefCS.ownedExtends : 119
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, iv._66/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _207 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._6/*CurlyBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _208 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 80
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, iv._34/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _209 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 92
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._43/*RoundBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _210 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 100
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, iv._49/*SquareBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _211 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 10
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, iv._4/*CollectionLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _212 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 12
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, iv._5/*CollectionTypeCS*/);
		private final @NonNull CardinalitySolutionStep _213 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _214 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 81
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._35/*PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _215 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _216 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : 81
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, iv._35/*PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _217 // check-rule essentialoclcs::CollectionPatternCS.ownedType : 12
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, iv._5/*CollectionTypeCS*/);
		private final @NonNull CardinalitySolutionStep _218 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 62
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, iv._25/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _219 // check-rule essentialoclcs::CollectionTypeCS.ownedType : 111
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, iv._57/*TypeExpWithoutMultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _220 // check-rule essentialoclcs::ContextCS.ownedExpression : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _221 // check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 97
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, iv._47/*ShadowPartCS*/);
		private final @NonNull CardinalitySolutionStep _222 // check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _223 // check-rule essentialoclcs::IfExpCS.ownedCondition : 27|81
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, iv._36/*ExpCS|PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _224 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _225 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, iv._9/*ElseIfThenExpCS*/);
		private final @NonNull CardinalitySolutionStep _226 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _227 // check-rule essentialoclcs::IfThenExpCS.ownedCondition : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _228 // check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _229 // check-rule essentialoclcs::InfixExpCS.ownedLeft : 86
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, iv._42/*PrefixedPrimaryExpCS*/);
		private final @NonNull CardinalitySolutionStep _230 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _231 // check-rule essentialoclcs::LetExpCS.ownedInExpression : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _232 // check-rule essentialoclcs::LetExpCS.ownedVariables : 44
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, iv._16/*LetVariableCS*/);
		private final @NonNull CardinalitySolutionStep _233 // check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 92
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._43/*RoundBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _234 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 58
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, iv._23/*MapLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _235 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : 59
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, iv._24/*MapTypeCS*/);
		private final @NonNull CardinalitySolutionStep _236 // check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _237 // check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _238 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : 110
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, iv._56/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _239 // check-rule essentialoclcs::MapTypeCS.ownedValueType : 110
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, iv._56/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _240 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, iv._3/*CoIteratorVariableCS*/);
		private final @NonNull CardinalitySolutionStep _241 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _242 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, iv._26/*NavigatingArgExpCS*/);
		private final @NonNull CardinalitySolutionStep _243 // check-rule essentialoclcs::NavigatingArgCS.ownedType : 110
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, iv._56/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _244 // check-rule essentialoclcs::NestedExpCS.ownedExpression : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _245 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _246 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 85
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._40/*PrefixedLetExpCS*/);
		private final @NonNull CardinalitySolutionStep _247 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 86
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._42/*PrefixedPrimaryExpCS*/);
		private final @NonNull CardinalitySolutionStep _248 // check-rule essentialoclcs::PatternExpCS.ownedPatternType : 110
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, iv._56/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _249 // check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 67|69|70|71
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, iv._28/*NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS*/);
		private final @NonNull CardinalitySolutionStep _250 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 102
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._50/*StringLiteralExpCS*/);
		private final @NonNull CardinalitySolutionStep _251 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 27|81
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._36/*ExpCS|PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _252 // check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _253 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 107
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, iv._54/*TupleLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _254 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 114
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, iv._59/*TypeLiteralWithMultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _255 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._6/*CurlyBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _256 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 80
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, iv._34/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _257 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _258 // check-rule essentialoclcs::VariableCS.ownedInitExpression : 27
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, iv._10/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _259 // check-rule essentialoclcs::VariableCS.ownedType : 110
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, iv._56/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _260 // check-rule oclstdlibcs::LibIterationCS.ownedAccumulators : 1
			= new CardinalitySolutionStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS, iv._0/*AccumulatorCS*/);
		private final @NonNull CardinalitySolutionStep _261 // check-rule oclstdlibcs::LibIterationCS.ownedIterators : 37
			= new CardinalitySolutionStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS, iv._14/*IteratorCS*/);
		private final @NonNull CardinalitySolutionStep _262 // check-rule oclstdlibcs::LibPackageCS.ownedPrecedences : 84
			= new CardinalitySolutionStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES, iv._39/*PrecedenceCS*/);
		private final @NonNull CardinalitySolutionStep _263 // check-rule oclstdlibcs::LibPropertyCS.ownedOpposite : 49
			= new CardinalitySolutionStep_RuleCheck(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE, iv._18/*LibOppositeCS*/);
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
		private final @NonNull RTSerializationLiteralStep _010 // 1*'<-'
									= new RTSerializationLiteralStep(-1, "<-");
		private final @NonNull RTSerializationLiteralStep _011 // 1*'='
									= new RTSerializationLiteralStep(-1, "=");
		private final @NonNull RTSerializationLiteralStep _012 // 1*'=>'
									= new RTSerializationLiteralStep(-1, "=>");
		private final @NonNull RTSerializationLiteralStep _013 // 1*'?'
									= new RTSerializationLiteralStep(-1, "?");
		private final @NonNull RTSerializationLiteralStep _014 // 1*'@'
									= new RTSerializationLiteralStep(-1, "@");
		private final @NonNull RTSerializationLiteralStep _015 // 1*'Lambda'
									= new RTSerializationLiteralStep(-1, "Lambda");
		private final @NonNull RTSerializationLiteralStep _016 // 1*'Map'
									= new RTSerializationLiteralStep(-1, "Map");
		private final @NonNull RTSerializationLiteralStep _017 // 1*'Tuple'
									= new RTSerializationLiteralStep(-1, "Tuple");
		private final @NonNull RTSerializationLiteralStep _018 // 1*'['
									= new RTSerializationLiteralStep(-1, "[");
		private final @NonNull RTSerializationLiteralStep _019 // 1*']'
									= new RTSerializationLiteralStep(-1, "]");
		private final @NonNull RTSerializationLiteralStep _020 // 1*'annotation'
									= new RTSerializationLiteralStep(-1, "annotation");
		private final @NonNull RTSerializationLiteralStep _021 // 1*'body'
									= new RTSerializationLiteralStep(-1, "body");
		private final @NonNull RTSerializationLiteralStep _022 // 1*'coercion'
									= new RTSerializationLiteralStep(-1, "coercion");
		private final @NonNull RTSerializationLiteralStep _023 // 1*'conformsTo'
									= new RTSerializationLiteralStep(-1, "conformsTo");
		private final @NonNull RTSerializationLiteralStep _024 // 1*'documentation'
									= new RTSerializationLiteralStep(-1, "documentation");
		private final @NonNull RTSerializationLiteralStep _025 // 1*'else'
									= new RTSerializationLiteralStep(-1, "else");
		private final @NonNull RTSerializationLiteralStep _026 // 1*'elseif'
									= new RTSerializationLiteralStep(-1, "elseif");
		private final @NonNull RTSerializationLiteralStep _027 // 1*'endif'
									= new RTSerializationLiteralStep(-1, "endif");
		private final @NonNull RTSerializationLiteralStep _028 // 1*'extends'
									= new RTSerializationLiteralStep(-1, "extends");
		private final @NonNull RTSerializationLiteralStep _029 // 1*'if'
									= new RTSerializationLiteralStep(-1, "if");
		private final @NonNull RTSerializationLiteralStep _030 // 1*'import'
									= new RTSerializationLiteralStep(-1, "import");
		private final @NonNull RTSerializationLiteralStep _031 // 1*'in'
									= new RTSerializationLiteralStep(-1, "in");
		private final @NonNull RTSerializationLiteralStep _032 // 1*'inv'
									= new RTSerializationLiteralStep(-1, "inv");
		private final @NonNull RTSerializationLiteralStep _033 // 1*'invalid'
									= new RTSerializationLiteralStep(-1, "invalid");
		private final @NonNull RTSerializationLiteralStep _034 // 1*'iteration'
									= new RTSerializationLiteralStep(-1, "iteration");
		private final @NonNull RTSerializationLiteralStep _035 // 1*'left'
									= new RTSerializationLiteralStep(-1, "left");
		private final @NonNull RTSerializationLiteralStep _036 // 1*'let'
									= new RTSerializationLiteralStep(-1, "let");
		private final @NonNull RTSerializationLiteralStep _037 // 1*'library'
									= new RTSerializationLiteralStep(-1, "library");
		private final @NonNull RTSerializationLiteralStep _038 // 1*'null'
									= new RTSerializationLiteralStep(-1, "null");
		private final @NonNull RTSerializationLiteralStep _039 // 1*'operation'
									= new RTSerializationLiteralStep(-1, "operation");
		private final @NonNull RTSerializationLiteralStep _040 // 1*'opposite'
									= new RTSerializationLiteralStep(-1, "opposite");
		private final @NonNull RTSerializationLiteralStep _041 // 1*'package'
									= new RTSerializationLiteralStep(-1, "package");
		private final @NonNull RTSerializationLiteralStep _042 // 1*'post'
									= new RTSerializationLiteralStep(-1, "post");
		private final @NonNull RTSerializationLiteralStep _043 // 1*'pre'
									= new RTSerializationLiteralStep(-1, "pre");
		private final @NonNull RTSerializationLiteralStep _044 // 1*'precedence'
									= new RTSerializationLiteralStep(-1, "precedence");
		private final @NonNull RTSerializationLiteralStep _045 // 1*'property'
									= new RTSerializationLiteralStep(-1, "property");
		private final @NonNull RTSerializationLiteralStep _046 // 1*'right'
									= new RTSerializationLiteralStep(-1, "right");
		private final @NonNull RTSerializationLiteralStep _047 // 1*'self'
									= new RTSerializationLiteralStep(-1, "self");
		private final @NonNull RTSerializationLiteralStep _048 // 1*'then'
									= new RTSerializationLiteralStep(-1, "then");
		private final @NonNull RTSerializationLiteralStep _049 // 1*'type'
									= new RTSerializationLiteralStep(-1, "type");
		private final @NonNull RTSerializationLiteralStep _050 // 1*'typeof'
									= new RTSerializationLiteralStep(-1, "typeof");
		private final @NonNull RTSerializationLiteralStep _051 // 1*'{'
									= new RTSerializationLiteralStep(-1, "{");
		private final @NonNull RTSerializationLiteralStep _052 // 1*'|'
									= new RTSerializationLiteralStep(-1, "|");
		private final @NonNull RTSerializationLiteralStep _053 // 1*'|?'
									= new RTSerializationLiteralStep(-1, "|?");
		private final @NonNull RTSerializationLiteralStep _054 // 1*'}'
									= new RTSerializationLiteralStep(-1, "}");
		private final @NonNull RTSerializationAssignedRuleCallStep _055 // 1*AbstractNameExpCS::ownedPathName=80
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 80 /* PathNameCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _056 // 1*AnnotationElementCS::ownedDetails+=16
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 16 /* DetailCS */);
		private final @NonNull RTSerializationAssignStep _057 // 1*BooleanLiteralExpCS::symbol
									= new RTSerializationAssignStep(-1, EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._09);
		private final @NonNull RTSerializationAssignedRuleCallStep _058 // 1*CollectionLiteralExpCS::ownedParts+=10
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 10 /* CollectionLiteralPartCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _059 // 1*CollectionLiteralExpCS::ownedType=12
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 12 /* CollectionTypeCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _060 // 1*CollectionLiteralPartCS::ownedExpression=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _061 // 1*CollectionLiteralPartCS::ownedExpression=81
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 81 /* PatternExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _062 // 1*CollectionLiteralPartCS::ownedLastExpression=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _063 // 1*CollectionPatternCS::ownedParts+=81
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 81 /* PatternExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _064 // 1*CollectionPatternCS::ownedType=12
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 12 /* CollectionTypeCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _065 // 1*CollectionPatternCS::restVariableName=31
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 31 /* Identifier */);
		private final @NonNull RTSerializationAssignedRuleCallStep _066 // 1*CollectionTypeCS::name=13
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 13 /* CollectionTypeIdentifier */);
		private final @NonNull RTSerializationAssignedRuleCallStep _067 // 1*CollectionTypeCS::ownedType=111
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 111 /* TypeExpWithoutMultiplicityCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _068 // 1*ConstraintCS::ownedMessageSpecification=99
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 99 /* SpecificationCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _069 // 1*ConstraintCS::ownedSpecification=99
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 99 /* SpecificationCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _070 // 1*ContextCS::ownedExpression=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _071 // 1*CurlyBracketedClauseCS::ownedParts+=97
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 97 /* ShadowPartCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _072 // 1*ExpSpecificationCS::ownedExpression=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignsStep _073 // 1*IfExpCS::ownedCondition=27|81
									= new RTSerializationAssignsStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, null, new @NonNull Integer [] { 27/*ExpCS*/,81/*PatternExpCS*/});
		private final @NonNull RTSerializationAssignedRuleCallStep _074 // 1*IfExpCS::ownedElseExpression=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _075 // 1*IfExpCS::ownedThenExpression=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _076 // 1*IfThenExpCS::ownedCondition=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _077 // 1*IfThenExpCS::ownedThenExpression=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _078 // 1*ImportCS::ownedPathName=124
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 124 /* URIPathNameCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _079 // 1*InfixExpCS::ownedLeft=86
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 86 /* PrefixedPrimaryExpCS */);
		private final @NonNull RTSerializationCrossReferenceStep _080 // 1*JavaImplementationCS::implementation=SINGLE_QUOTED_STRING
									= new RTSerializationCrossReferenceStep(-1, OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, getCrossReference(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, "SINGLE_QUOTED_STRING"));
		private final @NonNull RTSerializationCrossReferenceStep _081 // 1*JavaImplementationCS::implementation=SINGLE_QUOTED_STRING
									= new RTSerializationCrossReferenceStep(-1, OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, getCrossReference(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, "SINGLE_QUOTED_STRING"));
		private final @NonNull RTSerializationCrossReferenceStep _082 // 1*JavaImplementationCS::implementation=SINGLE_QUOTED_STRING
									= new RTSerializationCrossReferenceStep(-1, OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, getCrossReference(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, "SINGLE_QUOTED_STRING"));
		private final @NonNull RTSerializationCrossReferenceStep _083 // 1*JavaImplementationCS::implementation=SINGLE_QUOTED_STRING
									= new RTSerializationCrossReferenceStep(-1, OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, getCrossReference(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, "SINGLE_QUOTED_STRING"));
		private final @NonNull RTSerializationAssignedRuleCallStep _084 // 1*LambdaLiteralExpCS::ownedExpressionCS=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _085 // 1*LambdaTypeCS::ownedContextType=40
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, 40 /* LambdaContextTypeRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _086 // 1*LambdaTypeCS::ownedParameterTypes+=118
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES, 118 /* TypedMultiplicityRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _087 // 1*LambdaTypeCS::ownedResultType=119
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, 119 /* TypedRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _088 // 1*LetExpCS::ownedInExpression=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _089 // 1*LetExpCS::ownedVariables+=44
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 44 /* LetVariableCS */);
		private final @NonNull RTSerializationCrossReferenceStep _090 // 1*LibClassCS::metaclassName=AnyName
									= new RTSerializationCrossReferenceStep(-1, OCLstdlibCSPackage.Literals.LIB_CLASS_CS__METACLASS_NAME, getCrossReference(OCLstdlibCSPackage.Literals.LIB_CLASS_CS__METACLASS_NAME, "AnyName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _091 // 1*LibIterationCS::ownedAccumulators+=1
									= new RTSerializationAssignedRuleCallStep(-1, OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS, 1 /* AccumulatorCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _092 // 1*LibIterationCS::ownedIterators+=37
									= new RTSerializationAssignedRuleCallStep(-1, OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS, 37 /* IteratorCS */);
		private final @NonNull RTSerializationCrossReferenceStep _093 // 1*LibOperationCS::precedence=Name
									= new RTSerializationCrossReferenceStep(-1, OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE, getCrossReference(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE, "Name"));
		private final @NonNull RTSerializationAssignedRuleCallStep _094 // 1*MapLiteralExpCS::ownedParts+=58
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 58 /* MapLiteralPartCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _095 // 1*MapLiteralExpCS::ownedType=59
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 59 /* MapTypeCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _096 // 1*MapLiteralPartCS::ownedKey=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _097 // 1*MapLiteralPartCS::ownedValue=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _098 // 1*MapTypeCS::ownedKeyType=110
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 110 /* TypeExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _099 // 1*MapTypeCS::ownedValueType=110
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 110 /* TypeExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _100 // 1*ModelElementCS::ownedAnnotations+=3
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 3 /* AnnotationElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _101 // 1*MultiplicityBoundsCS::lowerBound=39
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 39 /* LOWER */);
		private final @NonNull RTSerializationAssignedRuleCallStep _102 // 1*MultiplicityBoundsCS::upperBound=121
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 121 /* UPPER */);
		private final @NonNull RTSerializationAssignStep _103 // 1*MultiplicityStringCS::stringBounds
									= new RTSerializationAssignStep(-1, BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._00);
		private final @NonNull RTSerializationAssignedRuleCallStep _104 // 1*NamedElementCS::name=125
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 125 /* UnaryOperatorName */);
		private final @NonNull RTSerializationAssignedRuleCallStep _105 // 1*NamedElementCS::name=128
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 128 /* UnrestrictedName */);
		private final @NonNull RTSerializationAssignedRuleCallStep _106 // 1*NamedElementCS::name=31
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 31 /* Identifier */);
		private final @NonNull RTSerializationAssignsStep _107 // 1*NamedElementCS::name=31|94
									= new RTSerializationAssignsStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, null, new @NonNull Integer [] { 31/*Identifier*/,94/*SINGLE_QUOTED_STRING*/});
		private final @NonNull RTSerializationAssignedRuleCallStep _108 // 1*NamedElementCS::name=4
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 4 /* AnyName */);
		private final @NonNull RTSerializationAssignedRuleCallStep _109 // 1*NamedElementCS::name=5
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 5 /* BinaryOperatorName */);
		private final @NonNull RTSerializationAssignedRuleCallStep _110 // 1*NamedElementCS::name=65
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 65 /* Name */);
		private final @NonNull RTSerializationAssignsStep _111 // 1*NamedElementCS::name=65|94
									= new RTSerializationAssignsStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, null, new @NonNull Integer [] { 65/*Name*/,94/*SINGLE_QUOTED_STRING*/});
		private final @NonNull RTSerializationAssignedRuleCallStep _112 // 1*NavigatingArgCS::ownedCoIterator=8
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 8 /* CoIteratorVariableCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _113 // 1*NavigatingArgCS::ownedInitExpression=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _114 // 1*NavigatingArgCS::ownedNameExpression=68
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 68 /* NavigatingArgExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _115 // 1*NavigatingArgCS::ownedType=110
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 110 /* TypeExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _116 // 1*NestedExpCS::ownedExpression=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _117 // 1*NumberLiteralExpCS::symbol=64
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 64 /* NUMBER_LITERAL */);
		private final @NonNull RTSerializationAssignedRuleCallStep _118 // 1*OperationCS::ownedBodyExpressions+=99
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 99 /* SpecificationCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _119 // 1*OperationCS::ownedParameters+=79
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 79 /* ParameterCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _120 // 1*OperatorExpCS::ownedRight=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _121 // 1*OperatorExpCS::ownedRight=85
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 85 /* PrefixedLetExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _122 // 1*OperatorExpCS::ownedRight=86
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 86 /* PrefixedPrimaryExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _123 // 1*PackageCS::nsPrefix=31
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, 31 /* Identifier */);
		private final @NonNull RTSerializationAssignedRuleCallStep _124 // 1*PackageCS::nsURI=122
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PACKAGE_CS__NS_URI, 122 /* URI */);
		private final @NonNull RTSerializationCrossReferenceStep _125 // 1*PathElementCS::referredElement=Name
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "Name"));
		private final @NonNull RTSerializationCrossReferenceStep _126 // 1*PathElementCS::referredElement=URI
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"));
		private final @NonNull RTSerializationCrossReferenceStep _127 // 1*PathElementCS::referredElement=UnreservedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"));
		private final @NonNull RTSerializationCrossReferenceStep _128 // 1*PathElementCS::referredElement=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull RTSerializationCrossReferenceStep _129 // 1*PathElementCS::referredElement=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _130 // 1*PathNameCS::ownedPathElements+=123
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 123 /* URIFirstPathElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _131 // 1*PathNameCS::ownedPathElements+=28
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 28 /* FirstPathElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _132 // 1*PathNameCS::ownedPathElements+=51
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 51 /* LibPathElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _133 // 1*PathNameCS::ownedPathElements+=74
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 74 /* NextPathElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _134 // 1*PatternExpCS::ownedPatternType=110
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 110 /* TypeExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _135 // 1*PrimitiveTypeRefCS::name=90
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 90 /* PrimitiveTypeIdentifier */);
		private final @NonNull RTSerializationAssignedRuleCallStep _136 // 1*RootCS::ownedImports+=33
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 33 /* ImportCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _137 // 1*RoundBracketedClauseCS::ownedArguments+=67
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 67 /* NavigatingArgCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _138 // 1*ShadowPartCS::ownedInitExpression=102
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 102 /* StringLiteralExpCS */);
		private final @NonNull RTSerializationAssignsStep _139 // 1*ShadowPartCS::ownedInitExpression=27|81
									= new RTSerializationAssignsStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, null, new @NonNull Integer [] { 27/*ExpCS*/,81/*PatternExpCS*/});
		private final @NonNull RTSerializationCrossReferenceStep _140 // 1*ShadowPartCS::referredProperty=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _141 // 1*SquareBracketedClauseCS::ownedTerms+=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _142 // 1*StructuredClassCS::ownedSuperTypes+=119
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 119 /* TypedRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _143 // 1*TemplateBindingCS::ownedSubstitutions+=104
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 104 /* TemplateParameterSubstitutionCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _144 // 1*TemplateParameterSubstitutionCS::ownedActualParameter=117
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 117 /* TypeRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _145 // 1*TemplateSignatureCS::ownedParameters+=116
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 116 /* TypeParameterCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _146 // 1*TupleLiteralExpCS::ownedParts+=107
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 107 /* TupleLiteralPartCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _147 // 1*TupleTypeCS::ownedParts+=108
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 108 /* TuplePartCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _148 // 1*TypeLiteralExpCS::ownedType=114
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 114 /* TypeLiteralWithMultiplicityCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _149 // 1*TypeNameExpCS::ownedCurlyBracketedClause=14
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /* CurlyBracketedClauseCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _150 // 1*TypeNameExpCS::ownedPathName=80
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 80 /* PathNameCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _151 // 1*TypeNameExpCS::ownedPatternGuard=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _152 // 1*TypeParameterCS::ownedExtends+=119
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 119 /* TypedRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _153 // 1*TypedElementCS::ownedType=118
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 118 /* TypedMultiplicityRefCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _154 // 1*TypedTypeRefCS::ownedBinding=103
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 103 /* TemplateBindingCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _155 // 1*TypedTypeRefCS::ownedPathName=52
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 52 /* LibPathNameCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _156 // 1*VariableCS::ownedInitExpression=27
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 27 /* ExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _157 // 1*VariableCS::ownedType=110
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 110 /* TypeExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _158 // 1*WildcardTypeRefCS::ownedExtends=119
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 119 /* TypedRefCS */);
		private final @NonNull RTSerializationSequenceStep _159 // 1*steps-1..10
									= new RTSerializationSequenceStep(-1, 1, 10);
		private final @NonNull RTSerializationSequenceStep _160 // 1*steps-1..11
									= new RTSerializationSequenceStep(-1, 1, 11);
		private final @NonNull RTSerializationSequenceStep _161 // 1*steps-1..12
									= new RTSerializationSequenceStep(-1, 1, 12);
		private final @NonNull RTSerializationSequenceStep _162 // 1*steps-1..13
									= new RTSerializationSequenceStep(-1, 1, 13);
		private final @NonNull RTSerializationSequenceStep _163 // 1*steps-1..14
									= new RTSerializationSequenceStep(-1, 1, 14);
		private final @NonNull RTSerializationSequenceStep _164 // 1*steps-1..15
									= new RTSerializationSequenceStep(-1, 1, 15);
		private final @NonNull RTSerializationSequenceStep _165 // 1*steps-1..17
									= new RTSerializationSequenceStep(-1, 1, 17);
		private final @NonNull RTSerializationSequenceStep _166 // 1*steps-1..20
									= new RTSerializationSequenceStep(-1, 1, 20);
		private final @NonNull RTSerializationSequenceStep _167 // 1*steps-1..24
									= new RTSerializationSequenceStep(-1, 1, 24);
		private final @NonNull RTSerializationSequenceStep _168 // 1*steps-1..3
									= new RTSerializationSequenceStep(-1, 1, 3);
		private final @NonNull RTSerializationSequenceStep _169 // 1*steps-1..30
									= new RTSerializationSequenceStep(-1, 1, 30);
		private final @NonNull RTSerializationSequenceStep _170 // 1*steps-1..33
									= new RTSerializationSequenceStep(-1, 1, 33);
		private final @NonNull RTSerializationSequenceStep _171 // 1*steps-1..34
									= new RTSerializationSequenceStep(-1, 1, 34);
		private final @NonNull RTSerializationSequenceStep _172 // 1*steps-1..4
									= new RTSerializationSequenceStep(-1, 1, 4);
		private final @NonNull RTSerializationSequenceStep _173 // 1*steps-1..5
									= new RTSerializationSequenceStep(-1, 1, 5);
		private final @NonNull RTSerializationSequenceStep _174 // 1*steps-1..6
									= new RTSerializationSequenceStep(-1, 1, 6);
		private final @NonNull RTSerializationSequenceStep _175 // 1*steps-1..7
									= new RTSerializationSequenceStep(-1, 1, 7);
		private final @NonNull RTSerializationSequenceStep _176 // 1*steps-1..8
									= new RTSerializationSequenceStep(-1, 1, 8);
		private final @NonNull RTSerializationSequenceStep _177 // 1*steps-1..9
									= new RTSerializationSequenceStep(-1, 1, 9);
		private final @NonNull RTSerializationLiteralStep _178 // V00*'abstract'
									= new RTSerializationLiteralStep(0, "abstract");
		private final @NonNull RTSerializationLiteralStep _179 // V00*'static'
									= new RTSerializationLiteralStep(0, "static");
		private final @NonNull RTSerializationLiteralStep _180 // V00*'|1'
									= new RTSerializationLiteralStep(0, "|1");
		private final @NonNull RTSerializationAssignedRuleCallStep _181 // V00*AbstractNameExpCS::ownedSquareBracketedClauses+=100
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 100 /* SquareBracketedClauseCS */);
		private final @NonNull RTSerializationAssignsStep _182 // V00*DetailCS::values+=94|56
									= new RTSerializationAssignsStep(0, BaseCSPackage.Literals.DETAIL_CS__VALUES, null, new @NonNull Integer [] { 94/*SINGLE_QUOTED_STRING*/,56/*ML_SINGLE_QUOTED_STRING*/});
		private final @NonNull RTSerializationAssignedRuleCallStep _183 // V00*DocumentationCS::value=94
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE, 94 /* SINGLE_QUOTED_STRING */);
		private final @NonNull RTSerializationAssignedRuleCallStep _184 // V00*IfExpCS::ownedIfThenExpressions+=20
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 20 /* ElseIfThenExpCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _185 // V00*LetVariableCS::ownedRoundBracketedClause=92
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 92 /* RoundBracketedClauseCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _186 // V00*PatternExpCS::patternVariableName=128
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 128 /* UnrestrictedName */);
		private final @NonNull RTSerializationAssignedRuleCallStep _187 // V00*StringLiteralExpCS::segments+=101
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 101 /* StringLiteral */);
		private final @NonNull RTSerializationAssignedRuleCallStep _188 // V00*TemplateableElementCS::ownedSignature=105
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 105 /* TemplateSignatureCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _189 // V00*TypedRefCS::ownedMultiplicity=62
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 62 /* MultiplicityCS */);
		private final @NonNull RTSerializationSequenceStep _190 // V00*steps-2..4
									= new RTSerializationSequenceStep(0, 2, 4);
		private final @NonNull RTSerializationSequenceStep _191 // V00*steps-3..10
									= new RTSerializationSequenceStep(0, 3, 10);
		private final @NonNull RTSerializationSequenceStep _192 // V00*steps-3..5
									= new RTSerializationSequenceStep(0, 3, 5);
		private final @NonNull RTSerializationSequenceStep _193 // V00*steps-3..6
									= new RTSerializationSequenceStep(0, 3, 6);
		private final @NonNull RTSerializationSequenceStep _194 // V00*steps-3..7
									= new RTSerializationSequenceStep(0, 3, 7);
		private final @NonNull RTSerializationSequenceStep _195 // V00*steps-3..8
									= new RTSerializationSequenceStep(0, 3, 8);
		private final @NonNull RTSerializationSequenceStep _196 // V00*steps-4..10
									= new RTSerializationSequenceStep(0, 4, 10);
		private final @NonNull RTSerializationSequenceStep _197 // V00*steps-4..6
									= new RTSerializationSequenceStep(0, 4, 6);
		private final @NonNull RTSerializationSequenceStep _198 // V00*steps-4..8
									= new RTSerializationSequenceStep(0, 4, 8);
		private final @NonNull RTSerializationSequenceStep _199 // V00*steps-4..9
									= new RTSerializationSequenceStep(0, 4, 9);
		private final @NonNull RTSerializationSequenceStep _200 // V00*steps-5..7
									= new RTSerializationSequenceStep(0, 5, 7);
		private final @NonNull RTSerializationSequenceStep _201 // V00*steps-6..8
									= new RTSerializationSequenceStep(0, 6, 8);
		private final @NonNull RTSerializationSequenceStep _202 // V00*steps-8..10
									= new RTSerializationSequenceStep(0, 8, 10);
		private final @NonNull RTSerializationLiteralStep _203 // V01*'::*'
									= new RTSerializationLiteralStep(1, "::*");
		private final @NonNull RTSerializationLiteralStep _204 // V01*'|1'
									= new RTSerializationLiteralStep(1, "|1");
		private final @NonNull RTSerializationAssignedRuleCallStep _205 // V01*AbstractNameExpCS::ownedRoundBracketedClause=92
									= new RTSerializationAssignedRuleCallStep(1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 92 /* RoundBracketedClauseCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _206 // V01*CollectionTypeCS::ownedCollectionMultiplicity=62
									= new RTSerializationAssignedRuleCallStep(1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 62 /* MultiplicityCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _207 // V01*LibPropertyCS::ownedOpposite=49
									= new RTSerializationAssignedRuleCallStep(1, OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE, 49 /* LibOppositeCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _208 // V01*ModelElementCS::ownedAnnotations+=3
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 3 /* AnnotationElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _209 // V01*PackageOwnerCS::ownedPackages+=50
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 50 /* LibPackageCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _210 // V01*PackageOwnerCS::ownedPackages+=78
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 78 /* PackageCS */);
		private final @NonNull RTSerializationAssignsStep _211 // V01*RoundBracketedClauseCS::ownedArguments+=70|71|69
									= new RTSerializationAssignsStep(1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, null, new @NonNull Integer [] { 70/*NavigatingCommaArgCS*/,71/*NavigatingSemiArgCS*/,69/*NavigatingBarArgCS*/});
		private final @NonNull RTSerializationAssignedRuleCallStep _212 // V01*TemplateBindingCS::ownedMultiplicity=62
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 62 /* MultiplicityCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _213 // V01*TemplateableElementCS::ownedSignature=105
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 105 /* TemplateSignatureCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _214 // V01*TypedRefCS::ownedMultiplicity=62
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 62 /* MultiplicityCS */);
		private final @NonNull RTSerializationSequenceStep _215 // V01*steps-4..10
									= new RTSerializationSequenceStep(1, 4, 10);
		private final @NonNull RTSerializationSequenceStep _216 // V01*steps-4..6
									= new RTSerializationSequenceStep(1, 4, 6);
		private final @NonNull RTSerializationSequenceStep _217 // V01*steps-5..7
									= new RTSerializationSequenceStep(1, 5, 7);
		private final @NonNull RTSerializationSequenceStep _218 // V01*steps-5..8
									= new RTSerializationSequenceStep(1, 5, 8);
		private final @NonNull RTSerializationSequenceStep _219 // V01*steps-5..9
									= new RTSerializationSequenceStep(1, 5, 9);
		private final @NonNull RTSerializationSequenceStep _220 // V01*steps-6..10
									= new RTSerializationSequenceStep(1, 6, 10);
		private final @NonNull RTSerializationSequenceStep _221 // V01*steps-6..8
									= new RTSerializationSequenceStep(1, 6, 8);
		private final @NonNull RTSerializationSequenceStep _222 // V01*steps-7..9
									= new RTSerializationSequenceStep(1, 7, 9);
		private final @NonNull RTSerializationSequenceStep _223 // V01*steps-8..10
									= new RTSerializationSequenceStep(1, 8, 10);
		private final @NonNull RTSerializationSequenceStep _224 // V01*steps-9..11
									= new RTSerializationSequenceStep(1, 9, 11);
		private final @NonNull RTSerializationAssignedRuleCallStep _225 // V02*AbstractNameExpCS::ownedCurlyBracketedClause=14
									= new RTSerializationAssignedRuleCallStep(2, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 14 /* CurlyBracketedClauseCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _226 // V02*OperationCS::ownedPreconditions+=82
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 82 /* PostCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _227 // V02*PackageCS::ownedClasses+=7
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 7 /* ClassCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _228 // V02*TypedRefCS::ownedMultiplicity=62
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 62 /* MultiplicityCS */);
		private final @NonNull RTSerializationSequenceStep _229 // V02*steps-10..15
									= new RTSerializationSequenceStep(2, 10, 15);
		private final @NonNull RTSerializationSequenceStep _230 // V02*steps-11..14
									= new RTSerializationSequenceStep(2, 11, 14);
		private final @NonNull RTSerializationSequenceStep _231 // V02*steps-6..8
									= new RTSerializationSequenceStep(2, 6, 8);
		private final @NonNull RTSerializationSequenceStep _232 // V02*steps-7..11
									= new RTSerializationSequenceStep(2, 7, 11);
		private final @NonNull RTSerializationSequenceStep _233 // V02*steps-7..9
									= new RTSerializationSequenceStep(2, 7, 9);
		private final @NonNull RTSerializationSequenceStep _234 // V02*steps-8..10
									= new RTSerializationSequenceStep(2, 8, 10);
		private final @NonNull RTSerializationAssignedRuleCallStep _235 // V03*LibPackageCS::ownedPrecedences+=84
									= new RTSerializationAssignedRuleCallStep(3, OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES, 84 /* PrecedenceCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _236 // V03*ModelElementCS::ownedAnnotations+=3
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 3 /* AnnotationElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _237 // V03*OperationCS::ownedPostconditions+=83
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 83 /* PreCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _238 // V03*TypedRefCS::ownedMultiplicity=62
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 62 /* MultiplicityCS */);
		private final @NonNull RTSerializationSequenceStep _239 // V03*steps-13..15
									= new RTSerializationSequenceStep(3, 13, 15);
		private final @NonNull RTSerializationSequenceStep _240 // V03*steps-6..8
									= new RTSerializationSequenceStep(3, 6, 8);
		private final @NonNull RTSerializationSequenceStep _241 // V03*steps-9..11
									= new RTSerializationSequenceStep(3, 9, 11);
		private final @NonNull RTSerializationSequenceStep _242 // V03*steps-9..14
									= new RTSerializationSequenceStep(3, 9, 14);
		private final @NonNull RTSerializationLiteralStep _243 // V04*'validating'
									= new RTSerializationLiteralStep(4, "validating");
		private final @NonNull RTSerializationAssignedRuleCallStep _244 // V04*PackageCS::ownedClasses+=7
									= new RTSerializationAssignedRuleCallStep(4, BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 7 /* ClassCS */);
		private final @NonNull RTSerializationSequenceStep _245 // V04*steps-12..14
									= new RTSerializationSequenceStep(4, 12, 14);
		private final @NonNull RTSerializationSequenceStep _246 // V04*steps-16..21
									= new RTSerializationSequenceStep(4, 16, 21);
		private final @NonNull RTSerializationLiteralStep _247 // V05*'invalidating'
									= new RTSerializationLiteralStep(5, "invalidating");
		private final @NonNull RTSerializationAssignedRuleCallStep _248 // V05*ModelElementCS::ownedAnnotations+=3
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 3 /* AnnotationElementCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _249 // V05*StructuredClassCS::ownedOperations+=77
									= new RTSerializationAssignedRuleCallStep(5, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 77 /* OperationCS */);
		private final @NonNull RTSerializationSequenceStep _250 // V05*steps-19..21
									= new RTSerializationSequenceStep(5, 19, 21);
		private final @NonNull RTSerializationLiteralStep _251 // V06*'invalidating'
									= new RTSerializationLiteralStep(6, "invalidating");
		private final @NonNull RTSerializationAssignedRuleCallStep _252 // V06*StructuredClassCS::ownedProperties+=53
									= new RTSerializationAssignedRuleCallStep(6, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 53 /* LibPropertyCS */);
		private final @NonNull RTSerializationSequenceStep _253 // V06*steps-17..20
									= new RTSerializationSequenceStep(6, 17, 20);
		private final @NonNull RTSerializationLiteralStep _254 // V07*'validating'
									= new RTSerializationLiteralStep(7, "validating");
		private final @NonNull RTSerializationAssignedRuleCallStep _255 // V07*ClassCS::ownedConstraints+=35
									= new RTSerializationAssignedRuleCallStep(7, BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 35 /* InvCS */);
		private final @NonNull RTSerializationSequenceStep _256 // V07*steps-21..23
									= new RTSerializationSequenceStep(7, 21, 23);
		private final @NonNull RTSerializationAssignedRuleCallStep _257 // V08*ModelElementCS::ownedAnnotations+=3
									= new RTSerializationAssignedRuleCallStep(8, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 3 /* AnnotationElementCS */);
		private final @NonNull RTSerializationSequenceStep _258 // V08*steps-27..29
									= new RTSerializationSequenceStep(8, 27, 29);
		private final @NonNull RTSerializationAssignedRuleCallStep _259 // V09*ModelElementCS::ownedAnnotations+=3
									= new RTSerializationAssignedRuleCallStep(9, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 3 /* AnnotationElementCS */);
		private final @NonNull RTSerializationSequenceStep _260 // V09*steps-26..30
									= new RTSerializationSequenceStep(9, 26, 30);
		private final @NonNull RTSerializationAssignedRuleCallStep _261 // V10*OperationCS::ownedPostconditions+=82
									= new RTSerializationAssignedRuleCallStep(10, BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 82 /* PostCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _262 // V10*OperationCS::ownedPreconditions+=82
									= new RTSerializationAssignedRuleCallStep(10, BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 82 /* PostCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _263 // V11*OperationCS::ownedPostconditions+=83
									= new RTSerializationAssignedRuleCallStep(11, BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 83 /* PreCS */);
		private final @NonNull RTSerializationAssignedRuleCallStep _264 // V11*OperationCS::ownedPreconditions+=83
									= new RTSerializationAssignedRuleCallStep(11, BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 83 /* PreCS */);
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
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _06 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null
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
			ss._5 /* «-» «? » «value» «?\n» */
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
			ss._5 /* «-» «? » «value» «?\n» */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _09 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _10 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _11 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _12 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _13 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _14 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _15 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _16 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _17 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
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
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _18 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
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
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _19 = new @NonNull Segment @NonNull [] @Nullable [] {
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
			ss._4 /* «! » «value» */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _21 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _22 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._4 /* «! » «value» */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _23 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _24 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _25 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _26 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _27 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _28 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */,
			null
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
			ss._1 /* «! » «value» «! » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _32 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			null,
			ss._3 /* «! » «value» «?\n» */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _33 = new @NonNull Segment @NonNull [] @Nullable [] {
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
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _37 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _38 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _39 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _40 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._2 /* «! » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _41 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _42 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
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
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _44 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _45 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null
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
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _48 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _49 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
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
			null,
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
			null,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _52 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _53 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _54 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _55 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _56 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _57 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _58 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null,
			ss._3 /* «! » «value» «?\n» */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _59 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null,
			ss._3 /* «! » «value» «?\n» */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */,
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
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _60 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _61 = new @NonNull Segment @NonNull [] @Nullable [] {
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
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _62 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */,
			ss._8 /* «½\n» «value» «½\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _63 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._8 /* «½\n» «value» «½\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _64 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._1 /* «! » «value» «! » */,
			ss._4 /* «! » «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _65 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._1 /* «! » «value» «! » */,
			ss._4 /* «! » «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _66 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _67 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _68 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _69 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
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
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _70 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
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
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */,
			null,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _71 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _72 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _73 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull ParserRuleValue _001 // AccumulatorCS
			= new ParserRuleValue(1, "AccumulatorCS",
				new @NonNull SerializationRule [] {
					sr1._096 /* { name=Identifier ':' ownedType=TypedMultiplicityRefCS } */
				},
				null);
		private final @NonNull ParserRuleValue _002 // AnnotationCS
			= new ParserRuleValue(2, "AnnotationCS",
				new @NonNull SerializationRule [] {
					sr1._097 /* { 'annotation' name=(Identifier|SINGLE_QUOTED_STRING) { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._098 /* { 'annotation' name=(Identifier|SINGLE_QUOTED_STRING) { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _003 // AnnotationElementCS
			= new ParserRuleValue(3, "AnnotationElementCS",
				new @NonNull SerializationRule [] {
					sr1._097 /* { 'annotation' name=(Identifier|SINGLE_QUOTED_STRING) { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._098 /* { 'annotation' name=(Identifier|SINGLE_QUOTED_STRING) { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS '}' } */,
					sr1._100 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				},
				iv._8); /* AnnotationCS|AnnotationElementCS|DocumentationCS */
		private final @NonNull DataTypeRuleValue _004 // AnyName
			= new DataTypeRuleValue(4, "AnyName");
		private final @NonNull DataTypeRuleValue _005 // BinaryOperatorName
			= new DataTypeRuleValue(5, "BinaryOperatorName");
		private final @NonNull ParserRuleValue _006 // BooleanLiteralExpCS
			= new ParserRuleValue(6, "BooleanLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._016 /* symbol={'false|true'} */
				},
				null);
		private final @NonNull ParserRuleValue _007 // ClassCS
			= new ParserRuleValue(7, "ClassCS",
				new @NonNull SerializationRule [] {
					sr1._106 /* { isAbstract='abstract'[?] 'type' name=AnyName ownedSignature=TemplateSignatureCS[?] { ':' metaclassName=AnyName }[?] { 'conformsTo' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] '{' ownedOperations+=OperationCS[*] ownedProperties+=LibPropertyCS[*] ownedConstraints+=InvCS[*] ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				iv._17); /* ClassCS|LibClassCS */
		private final @NonNull ParserRuleValue _008 // CoIteratorVariableCS
			= new ParserRuleValue(8, "CoIteratorVariableCS",
				new @NonNull SerializationRule [] {
					sr0._017 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _009 // CollectionLiteralExpCS
			= new ParserRuleValue(9, "CollectionLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _010 // CollectionLiteralPartCS
			= new ParserRuleValue(10, "CollectionLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._019 /* ownedExpression=PatternExpCS */,
					sr0._020 /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _011 // CollectionPatternCS
			= new ParserRuleValue(11, "CollectionPatternCS",
				new @NonNull SerializationRule [] {
					sr0._021 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _012 // CollectionTypeCS
			= new ParserRuleValue(12, "CollectionTypeCS",
				new @NonNull SerializationRule [] {
					sr0._022 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _013 // CollectionTypeIdentifier
			= new DataTypeRuleValue(13, "CollectionTypeIdentifier");
		private final @NonNull ParserRuleValue _014 // CurlyBracketedClauseCS
			= new ParserRuleValue(14, "CurlyBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr0._023 /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				null);
		private final @NonNull TerminalRuleValue _015 // DOUBLE_QUOTED_STRING
			= new TerminalRuleValue(15, "DOUBLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _016 // DetailCS
			= new ParserRuleValue(16, "DetailCS",
				new @NonNull SerializationRule [] {
					sr1._099 /* { name=(Name|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
				},
				null);
		private final @NonNull ParserRuleValue _017 // DocumentationCS
			= new ParserRuleValue(17, "DocumentationCS",
				new @NonNull SerializationRule [] {
					sr1._100 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				},
				null);
		private final @NonNull TerminalRuleValue _018 // ESCAPED_CHARACTER
			= new TerminalRuleValue(18, "ESCAPED_CHARACTER");
		private final @NonNull TerminalRuleValue _019 // ESCAPED_ID
			= new TerminalRuleValue(19, "ESCAPED_ID");
		private final @NonNull ParserRuleValue _020 // ElseIfThenExpCS
			= new ParserRuleValue(20, "ElseIfThenExpCS",
				new @NonNull SerializationRule [] {
					sr0._024 /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				null);
		private final @NonNull DataTypeRuleValue _021 // EssentialOCLInfixOperatorName
			= new DataTypeRuleValue(21, "EssentialOCLInfixOperatorName");
		private final @NonNull DataTypeRuleValue _022 // EssentialOCLNavigationOperatorName
			= new DataTypeRuleValue(22, "EssentialOCLNavigationOperatorName");
		private final @NonNull DataTypeRuleValue _023 // EssentialOCLReservedKeyword
			= new DataTypeRuleValue(23, "EssentialOCLReservedKeyword");
		private final @NonNull DataTypeRuleValue _024 // EssentialOCLUnaryOperatorName
			= new DataTypeRuleValue(24, "EssentialOCLUnaryOperatorName");
		private final @NonNull DataTypeRuleValue _025 // EssentialOCLUnreservedName
			= new DataTypeRuleValue(25, "EssentialOCLUnreservedName");
		private final @NonNull DataTypeRuleValue _026 // EssentialOCLUnrestrictedName
			= new DataTypeRuleValue(26, "EssentialOCLUnrestrictedName");
		private final @NonNull ParserRuleValue _027 // ExpCS
			= new ParserRuleValue(27, "ExpCS",
				new @NonNull SerializationRule [] {
					sr0._025 /* symbol={'false|true'} */,
					sr0._026 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._027 /* '*' */,
					sr0._028 /* 'invalid' */,
					sr0._029 /* 'null' */,
					sr0._030 /* 'self' */,
					sr0._031 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._032 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._033 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._034 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._035 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._036 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._037 /* symbol=NUMBER_LITERAL */,
					sr0._038 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._039 /* segments+=StringLiteral[+] */,
					sr0._040 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._041 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._045 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._067 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._76); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _028 // FirstPathElementCS
			= new ParserRuleValue(28, "FirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._000 /* referredElement=UnrestrictedName */
				},
				null);
		private final @NonNull DataTypeRuleValue _029 // ID
			= new DataTypeRuleValue(29, "ID");
		private final @NonNull TerminalRuleValue _030 // INT
			= new TerminalRuleValue(30, "INT");
		private final @NonNull DataTypeRuleValue _031 // Identifier
			= new DataTypeRuleValue(31, "Identifier");
		private final @NonNull ParserRuleValue _032 // IfExpCS
			= new ParserRuleValue(32, "IfExpCS",
				new @NonNull SerializationRule [] {
					sr0._042 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				},
				null);
		private final @NonNull ParserRuleValue _033 // ImportCS
			= new ParserRuleValue(33, "ImportCS",
				new @NonNull SerializationRule [] {
					sr1._101 /* { 'import' { name=Identifier ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _034 // InfixOperatorName
			= new DataTypeRuleValue(34, "InfixOperatorName");
		private final @NonNull ParserRuleValue _035 // InvCS
			= new ParserRuleValue(35, "InvCS",
				new @NonNull SerializationRule [] {
					sr1._102 /* { stereotype='inv' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS ';' } */
				},
				null);
		private final @NonNull ParserRuleValue _036 // InvalidLiteralExpCS
			= new ParserRuleValue(36, "InvalidLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._043 /* 'invalid' */
				},
				null);
		private final @NonNull ParserRuleValue _037 // IteratorCS
			= new ParserRuleValue(37, "IteratorCS",
				new @NonNull SerializationRule [] {
					sr1._103 /* { name=Identifier ':' ownedType=TypedMultiplicityRefCS } */
				},
				null);
		private final @NonNull TerminalRuleValue _038 // LETTER_CHARACTER
			= new TerminalRuleValue(38, "LETTER_CHARACTER");
		private final @NonNull DataTypeRuleValue _039 // LOWER
			= new DataTypeRuleValue(39, "LOWER");
		private final @NonNull ParserRuleValue _040 // LambdaContextTypeRefCS
			= new ParserRuleValue(40, "LambdaContextTypeRefCS",
				new @NonNull SerializationRule [] {
					sr1._104 /* ownedPathName=LibPathNameCS */
				},
				null);
		private final @NonNull ParserRuleValue _041 // LambdaLiteralExpCS
			= new ParserRuleValue(41, "LambdaLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._044 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _042 // LambdaTypeCS
			= new ParserRuleValue(42, "LambdaTypeCS",
				new @NonNull SerializationRule [] {
					sr1._105 /* { name='Lambda' ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS '(' { ownedParameterTypes+=TypedMultiplicityRefCS { ',' ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ')' ':' ownedResultType=TypedRefCS } */
				},
				null);
		private final @NonNull ParserRuleValue _043 // LetExpCS
			= new ParserRuleValue(43, "LetExpCS",
				new @NonNull SerializationRule [] {
					sr0._045 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _044 // LetVariableCS
			= new ParserRuleValue(44, "LetVariableCS",
				new @NonNull SerializationRule [] {
					sr0._046 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _045 // LibClassCS
			= new ParserRuleValue(45, "LibClassCS",
				new @NonNull SerializationRule [] {
					sr1._106 /* { isAbstract='abstract'[?] 'type' name=AnyName ownedSignature=TemplateSignatureCS[?] { ':' metaclassName=AnyName }[?] { 'conformsTo' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] '{' ownedOperations+=OperationCS[*] ownedProperties+=LibPropertyCS[*] ownedConstraints+=InvCS[*] ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _046 // LibCoercionCS
			= new ParserRuleValue(46, "LibCoercionCS",
				new @NonNull SerializationRule [] {
					sr1._107 /* { 'coercion' name=Name '(' ')' ':' ownedType=TypedMultiplicityRefCS { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' } */,
					sr1._108 /* { 'coercion' name=Name '(' ')' ':' ownedType=TypedMultiplicityRefCS { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _047 // LibIterationCS
			= new ParserRuleValue(47, "LibIterationCS",
				new @NonNull SerializationRule [] {
					sr1._109 /* { 'iteration' name=Name ownedSignature=TemplateSignatureCS[?] '(' ownedIterators+=IteratorCS { ',' ownedIterators+=IteratorCS }[*] { ';' ownedAccumulators+=AccumulatorCS { ',' ownedAccumulators+=AccumulatorCS }[*] }[?] { '|' ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isInvalidating='invalidating'[?] isValidating='validating'[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' } */,
					sr1._110 /* { 'iteration' name=Name ownedSignature=TemplateSignatureCS[?] '(' ownedIterators+=IteratorCS { ',' ownedIterators+=IteratorCS }[*] { ';' ownedAccumulators+=AccumulatorCS { ',' ownedAccumulators+=AccumulatorCS }[*] }[?] { '|' ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isInvalidating='invalidating'[?] isValidating='validating'[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _048 // LibOperationCS
			= new ParserRuleValue(48, "LibOperationCS",
				new @NonNull SerializationRule [] {
					sr1._111 /* { isStatic='static'[?] 'operation' name=Name ownedSignature=TemplateSignatureCS[?] '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isValidating='validating'[?] isInvalidating='invalidating'[?] { 'precedence' '=' precedence=Name }[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' } */,
					sr1._112 /* { isStatic='static'[?] 'operation' name=Name ownedSignature=TemplateSignatureCS[?] '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isValidating='validating'[?] isInvalidating='invalidating'[?] { 'precedence' '=' precedence=Name }[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS ';' }[*] ownedPostconditions+=PostCS[*] ownedPreconditions+=PreCS[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _049 // LibOppositeCS
			= new ParserRuleValue(49, "LibOppositeCS",
				new @NonNull SerializationRule [] {
					sr1._113 /* { 'opposite' name=Name ':' ownedType=TypedMultiplicityRefCS } */
				},
				null);
		private final @NonNull ParserRuleValue _050 // LibPackageCS
			= new ParserRuleValue(50, "LibPackageCS",
				new @NonNull SerializationRule [] {
					sr1._114 /* { 'library' name=Name { ':' nsPrefix=Identifier '=' nsURI=URI }[?] '{' ownedPackages+=PackageCS[*] { 'precedence' ownedPrecedences+=PrecedenceCS[+] ';' }[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _051 // LibPathElementCS
			= new ParserRuleValue(51, "LibPathElementCS",
				new @NonNull SerializationRule [] {
					sr1._115 /* referredElement=Name */
				},
				null);
		private final @NonNull ParserRuleValue _052 // LibPathNameCS
			= new ParserRuleValue(52, "LibPathNameCS",
				new @NonNull SerializationRule [] {
					sr1._116 /* { ownedPathElements+=LibPathElementCS { '::' ownedPathElements+=LibPathElementCS }[*] } */
				},
				null);
		private final @NonNull ParserRuleValue _053 // LibPropertyCS
			= new ParserRuleValue(53, "LibPropertyCS",
				new @NonNull SerializationRule [] {
					sr1._117 /* { isStatic='static'[?] 'property' name=Name ':' ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' } */,
					sr1._118 /* { isStatic='static'[?] 'property' name=Name ':' ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _054 // Library
			= new ParserRuleValue(54, "Library",
				new @NonNull SerializationRule [] {
					sr1._119 /* { { ownedImports+=ImportCS ';' }[*] ownedPackages+=LibPackageCS[*] } */
				},
				null);
		private final @NonNull TerminalRuleValue _055 // ML_COMMENT
			= new TerminalRuleValue(55, "ML_COMMENT");
		private final @NonNull TerminalRuleValue _056 // ML_SINGLE_QUOTED_STRING
			= new TerminalRuleValue(56, "ML_SINGLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _057 // MapLiteralExpCS
			= new ParserRuleValue(57, "MapLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._047 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _058 // MapLiteralPartCS
			= new ParserRuleValue(58, "MapLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._048 /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _059 // MapTypeCS
			= new ParserRuleValue(59, "MapTypeCS",
				new @NonNull SerializationRule [] {
					sr0._049 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _060 // Model
			= new ParserRuleValue(60, "Model",
				new @NonNull SerializationRule [] {
					sr0._050 /* ownedExpression=ExpCS */
				},
				null);
		private final @NonNull ParserRuleValue _061 // MultiplicityBoundsCS
			= new ParserRuleValue(61, "MultiplicityBoundsCS",
				new @NonNull SerializationRule [] {
					sr0._001 /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _062 // MultiplicityCS
			= new ParserRuleValue(62, "MultiplicityCS",
				new @NonNull SerializationRule [] {
					sr0._002 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					sr0._003 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					sr0._004 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */,
					sr0._005 /* { '[' stringBounds={'*|+|?'} ']' } */,
					sr0._006 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					sr0._007 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */
				},
				null);
		private final @NonNull ParserRuleValue _063 // MultiplicityStringCS
			= new ParserRuleValue(63, "MultiplicityStringCS",
				new @NonNull SerializationRule [] {
					sr0._008 /* stringBounds={'*|+|?'} */
				},
				null);
		private final @NonNull DataTypeRuleValue _064 // NUMBER_LITERAL
			= new DataTypeRuleValue(64, "NUMBER_LITERAL");
		private final @NonNull DataTypeRuleValue _065 // Name
			= new DataTypeRuleValue(65, "Name");
		private final @NonNull ParserRuleValue _066 // NameExpCS
			= new ParserRuleValue(66, "NameExpCS",
				new @NonNull SerializationRule [] {
					sr0._051 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _067 // NavigatingArgCS
			= new ParserRuleValue(67, "NavigatingArgCS",
				new @NonNull SerializationRule [] {
					sr0._052 /* ownedNameExpression=NavigatingArgExpCS */,
					sr0._053 /* { ':' ownedType=TypeExpCS } */,
					sr0._054 /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._055 /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._056 /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _068 // NavigatingArgExpCS
			= new ParserRuleValue(68, "NavigatingArgExpCS",
				new @NonNull SerializationRule [] {
					sr0._025 /* symbol={'false|true'} */,
					sr0._026 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._027 /* '*' */,
					sr0._028 /* 'invalid' */,
					sr0._029 /* 'null' */,
					sr0._030 /* 'self' */,
					sr0._031 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._032 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._033 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._034 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._035 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._036 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._037 /* symbol=NUMBER_LITERAL */,
					sr0._038 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._039 /* segments+=StringLiteral[+] */,
					sr0._040 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._041 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._045 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._067 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._77); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _069 // NavigatingBarArgCS
			= new ParserRuleValue(69, "NavigatingBarArgCS",
				new @NonNull SerializationRule [] {
					sr0._057 /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _070 // NavigatingCommaArgCS
			= new ParserRuleValue(70, "NavigatingCommaArgCS",
				new @NonNull SerializationRule [] {
					sr0._061 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					sr0._058 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._059 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._060 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _071 // NavigatingSemiArgCS
			= new ParserRuleValue(71, "NavigatingSemiArgCS",
				new @NonNull SerializationRule [] {
					sr0._062 /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _072 // NavigationOperatorName
			= new DataTypeRuleValue(72, "NavigationOperatorName");
		private final @NonNull ParserRuleValue _073 // NestedExpCS
			= new ParserRuleValue(73, "NestedExpCS",
				new @NonNull SerializationRule [] {
					sr0._063 /* { '(' ownedExpression=ExpCS ')' } */
				},
				null);
		private final @NonNull ParserRuleValue _074 // NextPathElementCS
			= new ParserRuleValue(74, "NextPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._009 /* referredElement=UnreservedName */
				},
				null);
		private final @NonNull ParserRuleValue _075 // NullLiteralExpCS
			= new ParserRuleValue(75, "NullLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._064 /* 'null' */
				},
				null);
		private final @NonNull ParserRuleValue _076 // NumberLiteralExpCS
			= new ParserRuleValue(76, "NumberLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._065 /* symbol=NUMBER_LITERAL */
				},
				null);
		private final @NonNull ParserRuleValue _077 // OperationCS
			= new ParserRuleValue(77, "OperationCS",
				new @NonNull SerializationRule [] {
					sr1._107 /* { 'coercion' name=Name '(' ')' ':' ownedType=TypedMultiplicityRefCS { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' } */,
					sr1._108 /* { 'coercion' name=Name '(' ')' ':' ownedType=TypedMultiplicityRefCS { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] '}' } */,
					sr1._109 /* { 'iteration' name=Name ownedSignature=TemplateSignatureCS[?] '(' ownedIterators+=IteratorCS { ',' ownedIterators+=IteratorCS }[*] { ';' ownedAccumulators+=AccumulatorCS { ',' ownedAccumulators+=AccumulatorCS }[*] }[?] { '|' ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isInvalidating='invalidating'[?] isValidating='validating'[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' } */,
					sr1._110 /* { 'iteration' name=Name ownedSignature=TemplateSignatureCS[?] '(' ownedIterators+=IteratorCS { ',' ownedIterators+=IteratorCS }[*] { ';' ownedAccumulators+=AccumulatorCS { ',' ownedAccumulators+=AccumulatorCS }[*] }[?] { '|' ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isInvalidating='invalidating'[?] isValidating='validating'[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] '}' } */,
					sr1._111 /* { isStatic='static'[?] 'operation' name=Name ownedSignature=TemplateSignatureCS[?] '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isValidating='validating'[?] isInvalidating='invalidating'[?] { 'precedence' '=' precedence=Name }[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' } */,
					sr1._112 /* { isStatic='static'[?] 'operation' name=Name ownedSignature=TemplateSignatureCS[?] '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isValidating='validating'[?] isInvalidating='invalidating'[?] { 'precedence' '=' precedence=Name }[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS ';' }[*] ownedPostconditions+=PostCS[*] ownedPreconditions+=PreCS[*] '}' } */
				},
				iv._31); /* LibCoercionCS|LibIterationCS|LibOperationCS|OperationCS */
		private final @NonNull ParserRuleValue _078 // PackageCS
			= new ParserRuleValue(78, "PackageCS",
				new @NonNull SerializationRule [] {
					sr1._120 /* { 'package' name=Name { ':' nsPrefix=Identifier '=' nsURI=URI }[?] '{' ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _079 // ParameterCS
			= new ParserRuleValue(79, "ParameterCS",
				new @NonNull SerializationRule [] {
					sr1._121 /* { name=Identifier ':' ownedType=TypedMultiplicityRefCS } */
				},
				null);
		private final @NonNull ParserRuleValue _080 // PathNameCS
			= new ParserRuleValue(80, "PathNameCS",
				new @NonNull SerializationRule [] {
					sr0._010 /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				null);
		private final @NonNull ParserRuleValue _081 // PatternExpCS
			= new ParserRuleValue(81, "PatternExpCS",
				new @NonNull SerializationRule [] {
					sr1._066 /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _082 // PostCS
			= new ParserRuleValue(82, "PostCS",
				new @NonNull SerializationRule [] {
					sr1._122 /* { stereotype='post' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS ';' } */
				},
				null);
		private final @NonNull ParserRuleValue _083 // PreCS
			= new ParserRuleValue(83, "PreCS",
				new @NonNull SerializationRule [] {
					sr1._123 /* { stereotype='pre' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS ';' } */
				},
				null);
		private final @NonNull ParserRuleValue _084 // PrecedenceCS
			= new ParserRuleValue(84, "PrecedenceCS",
				new @NonNull SerializationRule [] {
					sr1._124 /* { 'left' ':' name=Name } */,
					sr1._125 /* { isRightAssociative='right' ':' name=Name } */
				},
				null);
		private final @NonNull ParserRuleValue _085 // PrefixedLetExpCS
			= new ParserRuleValue(85, "PrefixedLetExpCS",
				new @NonNull SerializationRule [] {
					sr0._045 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._067 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._41); /* LetExpCS|PrefixedLetExpCS */
		private final @NonNull ParserRuleValue _086 // PrefixedPrimaryExpCS
			= new ParserRuleValue(86, "PrefixedPrimaryExpCS",
				new @NonNull SerializationRule [] {
					sr0._016 /* symbol={'false|true'} */,
					sr0._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._042 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._043 /* 'invalid' */,
					sr0._044 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._047 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._051 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._063 /* { '(' ownedExpression=ExpCS ')' } */,
					sr1._064 /* 'null' */,
					sr1._065 /* symbol=NUMBER_LITERAL */,
					sr1._068 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr1._071 /* 'self' */,
					sr1._076 /* segments+=StringLiteral[+] */,
					sr1._077 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._086 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._095 /* '*' */
				},
				iv._74); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _087 // PrimaryExpCS
			= new ParserRuleValue(87, "PrimaryExpCS",
				new @NonNull SerializationRule [] {
					sr0._016 /* symbol={'false|true'} */,
					sr0._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._042 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._043 /* 'invalid' */,
					sr0._044 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._047 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._051 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._063 /* { '(' ownedExpression=ExpCS ')' } */,
					sr1._064 /* 'null' */,
					sr1._065 /* symbol=NUMBER_LITERAL */,
					sr1._071 /* 'self' */,
					sr1._076 /* segments+=StringLiteral[+] */,
					sr1._077 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._086 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._095 /* '*' */
				},
				iv._73); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _088 // PrimitiveLiteralExpCS
			= new ParserRuleValue(88, "PrimitiveLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._016 /* symbol={'false|true'} */,
					sr0._043 /* 'invalid' */,
					sr1._064 /* 'null' */,
					sr1._065 /* symbol=NUMBER_LITERAL */,
					sr1._076 /* segments+=StringLiteral[+] */,
					sr1._095 /* '*' */
				},
				iv._72); /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _089 // PrimitiveTypeCS
			= new ParserRuleValue(89, "PrimitiveTypeCS",
				new @NonNull SerializationRule [] {
					sr1._069 /* name=PrimitiveTypeIdentifier */
				},
				null);
		private final @NonNull DataTypeRuleValue _090 // PrimitiveTypeIdentifier
			= new DataTypeRuleValue(90, "PrimitiveTypeIdentifier");
		private final @NonNull DataTypeRuleValue _091 // RestrictedKeywords
			= new DataTypeRuleValue(91, "RestrictedKeywords");
		private final @NonNull ParserRuleValue _092 // RoundBracketedClauseCS
			= new ParserRuleValue(92, "RoundBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._070 /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				null);
		private final @NonNull TerminalRuleValue _093 // SIMPLE_ID
			= new TerminalRuleValue(93, "SIMPLE_ID");
		private final @NonNull TerminalRuleValue _094 // SINGLE_QUOTED_STRING
			= new TerminalRuleValue(94, "SINGLE_QUOTED_STRING");
		private final @NonNull TerminalRuleValue _095 // SL_COMMENT
			= new TerminalRuleValue(95, "SL_COMMENT");
		private final @NonNull ParserRuleValue _096 // SelfExpCS
			= new ParserRuleValue(96, "SelfExpCS",
				new @NonNull SerializationRule [] {
					sr1._071 /* 'self' */
				},
				null);
		private final @NonNull ParserRuleValue _097 // ShadowPartCS
			= new ParserRuleValue(97, "ShadowPartCS",
				new @NonNull SerializationRule [] {
					sr1._072 /* ownedInitExpression=StringLiteralExpCS */,
					sr1._073 /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				null);
		private final @NonNull ParserRuleValue _098 // SimplePathNameCS
			= new ParserRuleValue(98, "SimplePathNameCS",
				new @NonNull SerializationRule [] {
					sr1._074 /* ownedPathElements+=FirstPathElementCS */
				},
				null);
		private final @NonNull ParserRuleValue _099 // SpecificationCS
			= new ParserRuleValue(99, "SpecificationCS",
				new @NonNull SerializationRule [] {
					sr1._126 /* ownedExpression=ExpCS */
				},
				null);
		private final @NonNull ParserRuleValue _100 // SquareBracketedClauseCS
			= new ParserRuleValue(100, "SquareBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._075 /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				null);
		private final @NonNull DataTypeRuleValue _101 // StringLiteral
			= new DataTypeRuleValue(101, "StringLiteral");
		private final @NonNull ParserRuleValue _102 // StringLiteralExpCS
			= new ParserRuleValue(102, "StringLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._076 /* segments+=StringLiteral[+] */
				},
				null);
		private final @NonNull ParserRuleValue _103 // TemplateBindingCS
			= new ParserRuleValue(103, "TemplateBindingCS",
				new @NonNull SerializationRule [] {
					sr0._011 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _104 // TemplateParameterSubstitutionCS
			= new ParserRuleValue(104, "TemplateParameterSubstitutionCS",
				new @NonNull SerializationRule [] {
					sr0._012 /* ownedActualParameter=TypeRefCS */
				},
				null);
		private final @NonNull ParserRuleValue _105 // TemplateSignatureCS
			= new ParserRuleValue(105, "TemplateSignatureCS",
				new @NonNull SerializationRule [] {
					sr0._013 /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */
				},
				null);
		private final @NonNull ParserRuleValue _106 // TupleLiteralExpCS
			= new ParserRuleValue(106, "TupleLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._077 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _107 // TupleLiteralPartCS
			= new ParserRuleValue(107, "TupleLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr1._078 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _108 // TuplePartCS
			= new ParserRuleValue(108, "TuplePartCS",
				new @NonNull SerializationRule [] {
					sr1._127 /* { name=Identifier ':' ownedType=TypedMultiplicityRefCS } */
				},
				null);
		private final @NonNull ParserRuleValue _109 // TupleTypeCS
			= new ParserRuleValue(109, "TupleTypeCS",
				new @NonNull SerializationRule [] {
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _110 // TypeExpCS
			= new ParserRuleValue(110, "TypeExpCS",
				new @NonNull SerializationRule [] {
					sr1._080 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._081 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._082 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._083 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._084 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._085 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._62); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull ParserRuleValue _111 // TypeExpWithoutMultiplicityCS
			= new ParserRuleValue(111, "TypeExpWithoutMultiplicityCS",
				new @NonNull SerializationRule [] {
					sr0._021 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
					sr0._022 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._049 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._069 /* name=PrimitiveTypeIdentifier */,
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._091 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				iv._61); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull ParserRuleValue _112 // TypeLiteralCS
			= new ParserRuleValue(112, "TypeLiteralCS",
				new @NonNull SerializationRule [] {
					sr0._022 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._049 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._069 /* name=PrimitiveTypeIdentifier */,
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._58); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */
		private final @NonNull ParserRuleValue _113 // TypeLiteralExpCS
			= new ParserRuleValue(113, "TypeLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._086 /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				null);
		private final @NonNull ParserRuleValue _114 // TypeLiteralWithMultiplicityCS
			= new ParserRuleValue(114, "TypeLiteralWithMultiplicityCS",
				new @NonNull SerializationRule [] {
					sr1._087 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._088 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._089 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._090 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._60); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
		private final @NonNull ParserRuleValue _115 // TypeNameExpCS
			= new ParserRuleValue(115, "TypeNameExpCS",
				new @NonNull SerializationRule [] {
					sr1._091 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _116 // TypeParameterCS
			= new ParserRuleValue(116, "TypeParameterCS",
				new @NonNull SerializationRule [] {
					sr0._014 /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _117 // TypeRefCS
			= new ParserRuleValue(117, "TypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._015 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */,
					sr0._049 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._105 /* { name='Lambda' ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS '(' { ownedParameterTypes+=TypedMultiplicityRefCS { ',' ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ')' ':' ownedResultType=TypedRefCS } */,
					sr2._133 /* { isTypeof='typeof' '(' ownedPathName=LibPathNameCS ')' } */,
					sr2._134 /* { ownedPathName=LibPathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				iv._79); /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
		private final @NonNull ParserRuleValue _118 // TypedMultiplicityRefCS
			= new ParserRuleValue(118, "TypedMultiplicityRefCS",
				new @NonNull SerializationRule [] {
					sr2._128 /* { name='Lambda' ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS '(' { ownedParameterTypes+=TypedMultiplicityRefCS { ',' ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ')' ':' ownedResultType=TypedRefCS ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._129 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._130 /* { isTypeof='typeof' '(' ownedPathName=LibPathNameCS ')' ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._131 /* { ownedPathName=LibPathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._132 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._67); /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
		private final @NonNull ParserRuleValue _119 // TypedRefCS
			= new ParserRuleValue(119, "TypedRefCS",
				new @NonNull SerializationRule [] {
					sr0._049 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._105 /* { name='Lambda' ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS '(' { ownedParameterTypes+=TypedMultiplicityRefCS { ',' ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ')' ':' ownedResultType=TypedRefCS } */,
					sr2._133 /* { isTypeof='typeof' '(' ownedPathName=LibPathNameCS ')' } */,
					sr2._134 /* { ownedPathName=LibPathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				iv._68); /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */
		private final @NonNull ParserRuleValue _120 // TypedTypeRefCS
			= new ParserRuleValue(120, "TypedTypeRefCS",
				new @NonNull SerializationRule [] {
					sr2._133 /* { isTypeof='typeof' '(' ownedPathName=LibPathNameCS ')' } */,
					sr2._134 /* { ownedPathName=LibPathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _121 // UPPER
			= new DataTypeRuleValue(121, "UPPER");
		private final @NonNull DataTypeRuleValue _122 // URI
			= new DataTypeRuleValue(122, "URI");
		private final @NonNull ParserRuleValue _123 // URIFirstPathElementCS
			= new ParserRuleValue(123, "URIFirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr1._092 /* referredElement=UnrestrictedName */,
					sr1._093 /* referredElement=URI */
				},
				null);
		private final @NonNull ParserRuleValue _124 // URIPathNameCS
			= new ParserRuleValue(124, "URIPathNameCS",
				new @NonNull SerializationRule [] {
					sr1._094 /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _125 // UnaryOperatorName
			= new DataTypeRuleValue(125, "UnaryOperatorName");
		private final @NonNull ParserRuleValue _126 // UnlimitedNaturalLiteralExpCS
			= new ParserRuleValue(126, "UnlimitedNaturalLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._095 /* '*' */
				},
				null);
		private final @NonNull DataTypeRuleValue _127 // UnreservedName
			= new DataTypeRuleValue(127, "UnreservedName");
		private final @NonNull DataTypeRuleValue _128 // UnrestrictedName
			= new DataTypeRuleValue(128, "UnrestrictedName");
		private final @NonNull TerminalRuleValue _129 // WS
			= new TerminalRuleValue(129, "WS");
		private final @NonNull ParserRuleValue _130 // WildcardTypeRefCS
			= new ParserRuleValue(130, "WildcardTypeRefCS",
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
		private final @NonNull EClassValue _00 // AnnotationCS
			= new EClassValue(BaseCSPackage.Literals.ANNOTATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._097, sl._60) /* { 'annotation' name=(Identifier|SINGLE_QUOTED_STRING) { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._098, sl._61) /* { 'annotation' name=(Identifier|SINGLE_QUOTED_STRING) { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						iv._7) /* DetailCS */
				}
			);
		private final @NonNull EClassValue _01 // BooleanLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._016, sl._29) /* symbol={'false|true'} */,
					new SerializationRule_SegmentsList(sr0._025, sl._29) /* symbol={'false|true'} */
				}, null
			);
		private final @NonNull EClassValue _02 // CollectionLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._018, sl._34) /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					new SerializationRule_SegmentsList(sr0._026, sl._34) /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						iv._4) /* CollectionLiteralPartCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						iv._5) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassValue _03 // CollectionLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._019, sl._29) /* ownedExpression=PatternExpCS */,
					new SerializationRule_SegmentsList(sr0._020, sl._31) /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						iv._78) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _04 // CollectionPatternCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._021, sl._07) /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
					new SerializationRule_SegmentsList(sr1._082, sl._08) /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._25) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						iv._35) /* PatternExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						iv._5) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassValue _05 // CollectionTypeCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._022, sl._21) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._083, sl._22) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._089, sl._22) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						iv._25) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._25) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						iv._61) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _06 // ContextCS
			= new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._050, sl._29) /* ownedExpression=ExpCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _07 // CurlyBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._023, sl._15) /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						iv._47) /* ShadowPartCS */
				}
			);
		private final @NonNull EClassValue _08 // DetailCS
			= new EClassValue(BaseCSPackage.Literals.DETAIL_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._099, sl._66) /* { name=(Name|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
				}, null
			);
		private final @NonNull EClassValue _09 // DocumentationCS
			= new EClassValue(BaseCSPackage.Literals.DOCUMENTATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._100, sl._60) /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
						iv._7) /* DetailCS */
				}
			);
		private final @NonNull EClassValue _10 // ExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._027, sl._29) /* '*' */,
					new SerializationRule_SegmentsList(sr0._028, sl._29) /* 'invalid' */,
					new SerializationRule_SegmentsList(sr0._029, sl._29) /* 'null' */,
					new SerializationRule_SegmentsList(sr0._030, sl._29) /* 'self' */
				}, null
			);
		private final @NonNull EClassValue _11 // ExpSpecificationCS
			= new EClassValue(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._126, sl._29) /* ownedExpression=ExpCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _12 // IfExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._031, sl._51) /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					new SerializationRule_SegmentsList(sr0._042, sl._51) /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						iv._78) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						iv._9) /* ElseIfThenExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _13 // IfThenExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._024, sl._49) /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _14 // ImportCS
			= new EClassValue(BaseCSPackage.Literals.IMPORT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._101, sl._54) /* { 'import' { name=Identifier ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
						iv._71) /* URIPathNameCS */
				}
			);
		private final @NonNull EClassValue _15 // InfixExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._032, sl._35) /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
						iv._74) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _16 // InvalidLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._043, sl._29) /* 'invalid' */
				}, null
			);
		private final @NonNull EClassValue _17 // LambdaLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._033, sl._56) /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					new SerializationRule_SegmentsList(sr0._044, sl._56) /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _18 // LambdaTypeCS
			= new EClassValue(BaseCSPackage.Literals.LAMBDA_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._105, sl._17) /* { name='Lambda' ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS '(' { ownedParameterTypes+=TypedMultiplicityRefCS { ',' ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ')' ':' ownedResultType=TypedRefCS } */,
					new SerializationRule_SegmentsList(sr2._128, sl._18) /* { name='Lambda' ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS '(' { ownedParameterTypes+=TypedMultiplicityRefCS { ',' ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ')' ':' ownedResultType=TypedRefCS ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE,
						iv._15) /* LambdaContextTypeRefCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._25) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES,
						iv._67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE,
						iv._68) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._53) /* TemplateSignatureCS */
				}
			);
		private final @NonNull EClassValue _19 // LetExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._045, sl._46) /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						iv._16) /* LetVariableCS */
				}
			);
		private final @NonNull EClassValue _20 // LetVariableCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._046, sl._48) /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._43) /* RoundBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _21 // LibClassCS
			= new EClassValue(OCLstdlibCSPackage.Literals.LIB_CLASS_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._106, sl._68) /* { isAbstract='abstract'[?] 'type' name=AnyName ownedSignature=TemplateSignatureCS[?] { ':' metaclassName=AnyName }[?] { 'conformsTo' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] '{' ownedOperations+=OperationCS[*] ownedProperties+=LibPropertyCS[*] ownedConstraints+=InvCS[*] ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
						iv._13) /* InvCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
						iv._31) /* LibCoercionCS|LibIterationCS|LibOperationCS|OperationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
						iv._22) /* LibPropertyCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._53) /* TemplateSignatureCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
						iv._68) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _22 // LibCoercionCS
			= new EClassValue(OCLstdlibCSPackage.Literals.LIB_COERCION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._107, sl._64) /* { 'coercion' name=Name '(' ')' ':' ownedType=TypedMultiplicityRefCS { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._108, sl._65) /* { 'coercion' name=Name '(' ')' ':' ownedType=TypedMultiplicityRefCS { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						iv._38) /* PreCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						iv._37) /* PostCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _23 // LibConstraintCS
			= new EClassValue(OCLstdlibCSPackage.Literals.LIB_CONSTRAINT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._102, sl._52) /* { stereotype='inv' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS ';' } */,
					new SerializationRule_SegmentsList(sr1._122, sl._52) /* { stereotype='post' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS ';' } */,
					new SerializationRule_SegmentsList(sr1._123, sl._52) /* { stereotype='pre' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS ';' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
						iv._48) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
						iv._48) /* SpecificationCS */
				}
			);
		private final @NonNull EClassValue _24 // LibIterationCS
			= new EClassValue(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._109, sl._58) /* { 'iteration' name=Name ownedSignature=TemplateSignatureCS[?] '(' ownedIterators+=IteratorCS { ',' ownedIterators+=IteratorCS }[*] { ';' ownedAccumulators+=AccumulatorCS { ',' ownedAccumulators+=AccumulatorCS }[*] }[?] { '|' ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isInvalidating='invalidating'[?] isValidating='validating'[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._110, sl._59) /* { 'iteration' name=Name ownedSignature=TemplateSignatureCS[?] '(' ownedIterators+=IteratorCS { ',' ownedIterators+=IteratorCS }[*] { ';' ownedAccumulators+=AccumulatorCS { ',' ownedAccumulators+=AccumulatorCS }[*] }[?] { '|' ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isInvalidating='invalidating'[?] isValidating='validating'[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS,
						iv._0) /* AccumulatorCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
					new EReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS,
						iv._14) /* IteratorCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						iv._33) /* ParameterCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						iv._38) /* PreCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						iv._37) /* PostCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._53) /* TemplateSignatureCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _25 // LibOperationCS
			= new EClassValue(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._111, sl._69) /* { isStatic='static'[?] 'operation' name=Name ownedSignature=TemplateSignatureCS[?] '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isValidating='validating'[?] isInvalidating='invalidating'[?] { 'precedence' '=' precedence=Name }[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._112, sl._70) /* { isStatic='static'[?] 'operation' name=Name ownedSignature=TemplateSignatureCS[?] '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isValidating='validating'[?] isInvalidating='invalidating'[?] { 'precedence' '=' precedence=Name }[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS ';' }[*] ownedPostconditions+=PostCS[*] ownedPreconditions+=PreCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
						iv._48) /* SpecificationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
						iv._33) /* ParameterCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
						iv._37) /* PostCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
						iv._38) /* PreCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
						iv._53) /* TemplateSignatureCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _26 // LibOppositeCS
			= new EClassValue(OCLstdlibCSPackage.Literals.LIB_OPPOSITE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._113, sl._67) /* { 'opposite' name=Name ':' ownedType=TypedMultiplicityRefCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _27 // LibPackageCS
			= new EClassValue(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._114, sl._62) /* { 'library' name=Name { ':' nsPrefix=Identifier '=' nsURI=URI }[?] '{' ownedPackages+=PackageCS[*] { 'precedence' ownedPrecedences+=PrecedenceCS[+] ';' }[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
						iv._17) /* ClassCS|LibClassCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						iv._32) /* PackageCS */,
					new EReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES,
						iv._39) /* PrecedenceCS */
				}
			);
		private final @NonNull EClassValue _28 // LibPropertyCS
			= new EClassValue(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._117, sl._71) /* { isStatic='static'[?] 'property' name=Name ':' ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._118, sl._72) /* { isStatic='static'[?] 'property' name=Name ':' ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
					new EReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE,
						iv._18) /* LibOppositeCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _29 // LibRootPackageCS
			= new EClassValue(OCLstdlibCSPackage.Literals.LIB_ROOT_PACKAGE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._119, sl._32) /* { { ownedImports+=ImportCS ';' }[*] ownedPackages+=LibPackageCS[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
						iv._12) /* ImportCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						iv._19) /* LibPackageCS */
				}
			);
		private final @NonNull EClassValue _30 // MapLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._034, sl._34) /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					new SerializationRule_SegmentsList(sr0._047, sl._34) /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						iv._23) /* MapLiteralPartCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						iv._24) /* MapTypeCS */
				}
			);
		private final @NonNull EClassValue _31 // MapLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._048, sl._35) /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _32 // MapTypeCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._049, sl._23) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._084, sl._24) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._090, sl._24) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr2._132, sl._24) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						iv._62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._25) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						iv._62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _33 // MultiplicityBoundsCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._001, sl._25) /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */,
					new SerializationRule_SegmentsList(sr0._002, sl._11) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._003, sl._12) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._004, sl._12) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, null
			);
		private final @NonNull EClassValue _34 // MultiplicityStringCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._005, sl._13) /* { '[' stringBounds={'*|+|?'} ']' } */,
					new SerializationRule_SegmentsList(sr0._006, sl._14) /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._007, sl._14) /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._008, sl._73) /* stringBounds={'*|+|?'} */
				}, null
			);
		private final @NonNull EClassValue _35 // NameExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._035, sl._30) /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					new SerializationRule_SegmentsList(sr0._051, sl._30) /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._6) /* CurlyBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._34) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._43) /* RoundBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						iv._49) /* SquareBracketedClauseCS */
				}
			);
		private final @NonNull EClassValue _36 // NavigatingArgCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._052, sl._29) /* ownedNameExpression=NavigatingArgExpCS */,
					new SerializationRule_SegmentsList(sr0._053, sl._45) /* { ':' ownedType=TypeExpCS } */,
					new SerializationRule_SegmentsList(sr0._055, sl._36) /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._054, sl._37) /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._056, sl._33) /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					new SerializationRule_SegmentsList(sr0._057, sl._47) /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */,
					new SerializationRule_SegmentsList(sr0._061, sl._40) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					new SerializationRule_SegmentsList(sr0._059, sl._42) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._058, sl._43) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._060, sl._41) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					new SerializationRule_SegmentsList(sr0._062, sl._44) /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						iv._3) /* CoIteratorVariableCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						iv._77) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						iv._62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _37 // NestedExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._036, sl._39) /* { '(' ownedExpression=ExpCS ')' } */,
					new SerializationRule_SegmentsList(sr0._063, sl._39) /* { '(' ownedExpression=ExpCS ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _38 // NullLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._064, sl._29) /* 'null' */
				}, null
			);
		private final @NonNull EClassValue _39 // NumberLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._037, sl._29) /* symbol=NUMBER_LITERAL */,
					new SerializationRule_SegmentsList(sr1._065, sl._29) /* symbol=NUMBER_LITERAL */
				}, null
			);
		private final @NonNull EClassValue _40 // PackageCS
			= new EClassValue(BaseCSPackage.Literals.PACKAGE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._120, sl._63) /* { 'package' name=Name { ':' nsPrefix=Identifier '=' nsURI=URI }[?] '{' ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._8) /* AnnotationCS|AnnotationElementCS|DocumentationCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
						iv._17) /* ClassCS|LibClassCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
						iv._32) /* PackageCS */
				}
			);
		private final @NonNull EClassValue _41 // ParameterCS
			= new EClassValue(BaseCSPackage.Literals.PARAMETER_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._096, sl._57) /* { name=Identifier ':' ownedType=TypedMultiplicityRefCS } */,
					new SerializationRule_SegmentsList(sr1._103, sl._57) /* { name=Identifier ':' ownedType=TypedMultiplicityRefCS } */,
					new SerializationRule_SegmentsList(sr1._121, sl._57) /* { name=Identifier ':' ownedType=TypedMultiplicityRefCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _42 // PathElementCS
			= new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._000, sl._73) /* referredElement=UnrestrictedName */,
					new SerializationRule_SegmentsList(sr0._009, sl._73) /* referredElement=UnreservedName */,
					new SerializationRule_SegmentsList(sr1._092, sl._73) /* referredElement=UnrestrictedName */,
					new SerializationRule_SegmentsList(sr1._115, sl._73) /* referredElement=Name */
				}, null
			);
		private final @NonNull EClassValue _43 // PathElementWithURICS
			= new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._093, sl._73) /* referredElement=URI */
				}, null
			);
		private final @NonNull EClassValue _44 // PathNameCS
			= new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._010, sl._03) /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */,
					new SerializationRule_SegmentsList(sr1._074, sl._00) /* ownedPathElements+=FirstPathElementCS */,
					new SerializationRule_SegmentsList(sr1._094, sl._03) /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */,
					new SerializationRule_SegmentsList(sr1._116, sl._03) /* { ownedPathElements+=LibPathElementCS { '::' ownedPathElements+=LibPathElementCS }[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						iv._70) /* FirstPathElementCS|LibPathElementCS|NextPathElementCS|URIFirstPathElementCS */
				}
			);
		private final @NonNull EClassValue _45 // PatternExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._066, sl._57) /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
						iv._62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _46 // PrecedenceCS
			= new EClassValue(OCLstdlibCSPackage.Literals.PRECEDENCE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._124, sl._66) /* { 'left' ':' name=Name } */,
					new SerializationRule_SegmentsList(sr1._125, sl._66) /* { isRightAssociative='right' ':' name=Name } */
				}, null
			);
		private final @NonNull EClassValue _47 // PrefixExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._038, sl._45) /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					new SerializationRule_SegmentsList(sr1._067, sl._45) /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					new SerializationRule_SegmentsList(sr1._068, sl._45) /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._75) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _48 // PrimitiveTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._069, sl._73) /* name=PrimitiveTypeIdentifier */,
					new SerializationRule_SegmentsList(sr1._080, sl._16) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._087, sl._16) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._25) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassValue _49 // RoundBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._070, sl._09) /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						iv._28) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
				}
			);
		private final @NonNull EClassValue _50 // SelfExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._071, sl._29) /* 'self' */
				}, null
			);
		private final @NonNull EClassValue _51 // ShadowPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._072, sl._29) /* ownedInitExpression=StringLiteralExpCS */,
					new SerializationRule_SegmentsList(sr1._073, sl._57) /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						iv._78) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _52 // SquareBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._075, sl._10) /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _53 // StringLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._039, sl._29) /* segments+=StringLiteral[+] */,
					new SerializationRule_SegmentsList(sr1._076, sl._29) /* segments+=StringLiteral[+] */
				}, null
			);
		private final @NonNull EClassValue _54 // TemplateBindingCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._011, sl._06) /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						iv._25) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						iv._52) /* TemplateParameterSubstitutionCS */
				}
			);
		private final @NonNull EClassValue _55 // TemplateParameterSubstitutionCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._012, sl._29) /* ownedActualParameter=TypeRefCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						iv._79) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
				}
			);
		private final @NonNull EClassValue _56 // TemplateSignatureCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._013, sl._38) /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						iv._63) /* TypeParameterCS */
				}
			);
		private final @NonNull EClassValue _57 // TupleLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._040, sl._55) /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._077, sl._55) /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						iv._54) /* TupleLiteralPartCS */
				}
			);
		private final @NonNull EClassValue _58 // TupleLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._078, sl._53) /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _59 // TuplePartCS
			= new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._127, sl._57) /* { name=Identifier ':' ownedType=TypedMultiplicityRefCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._67) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedMultiplicityRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _60 // TupleTypeCS
			= new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._079, sl._19) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._081, sl._20) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._088, sl._20) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr2._129, sl._20) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._25) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						iv._55) /* TuplePartCS */
				}
			);
		private final @NonNull EClassValue _61 // TypeLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._041, sl._29) /* ownedType=TypeLiteralWithMultiplicityCS */,
					new SerializationRule_SegmentsList(sr1._086, sl._29) /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						iv._60) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
				}
			);
		private final @NonNull EClassValue _62 // TypeNameExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._085, sl._02) /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._091, sl._01) /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._6) /* CurlyBracketedClauseCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._25) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._34) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						iv._76) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _63 // TypeParameterCS
			= new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._014, sl._50) /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						iv._68) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _64 // TypedTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._133, sl._27) /* { isTypeof='typeof' '(' ownedPathName=LibPathNameCS ')' } */,
					new SerializationRule_SegmentsList(sr2._134, sl._04) /* { ownedPathName=LibPathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._104, sl._00) /* ownedPathName=LibPathNameCS */,
					new SerializationRule_SegmentsList(sr2._130, sl._28) /* { isTypeof='typeof' '(' ownedPathName=LibPathNameCS ')' ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr2._131, sl._05) /* { ownedPathName=LibPathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						iv._51) /* TemplateBindingCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._25) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						iv._21) /* LibPathNameCS */
				}
			);
		private final @NonNull EClassValue _65 // UnlimitedNaturalLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._095, sl._29) /* '*' */
				}, null
			);
		private final @NonNull EClassValue _66 // VariableCS
			= new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._017, sl._49) /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._62) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _67 // WildcardTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._015, sl._26) /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						iv._68) /* LambdaTypeCS|MapTypeCS|TupleTypeCS|TypedRefCS|TypedTypeRefCS */
				}
			);
	}

	/**
	 * The various serialization rules that serialize an EClass.
	 */
	private class _SerializationRules0
	{
		// Base::FirstPathElementCS : referredElement=UnrestrictedName
		private @NonNull SerializationRule _000 = new SerializationRule(28,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._044 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._128 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._73,
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
		private @NonNull SerializationRule _001 = new SerializationRule(61,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._084 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._030 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || «null» */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=39 || «? » «value» «? » */,
				st._192 /* V00*steps-3..5 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=121 || «? » «value» «? » */
			},
			sl._25,
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
		private @NonNull SerializationRule _002 = new SerializationRule(62,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._084 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._030 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._175 /* 1*steps-1..7 || «null» */,
				st._018 /* 1*'[' || «! » «value» «! » */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=39 || «? » «value» «? » */,
				st._197 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=121 || «? » «value» «? » */,
				st._019 /* 1*']' || «! » «value» */
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
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' }
		private @NonNull SerializationRule _003 = new SerializationRule(62,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._084 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._030 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || «null» */,
				st._018 /* 1*'[' || «! » «value» «! » */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=39 || «? » «value» «? » */,
				st._197 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=121 || «? » «value» «? » */,
				st._053 /* 1*'|?' || «? » «value» «? » */,
				st._019 /* 1*']' || «! » «value» */
			},
			sl._12,
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
		private @NonNull SerializationRule _004 = new SerializationRule(62,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._118 /* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._084 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._030 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || «null» */,
				st._018 /* 1*'[' || «! » «value» «! » */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=39 || «? » «value» «? » */,
				st._197 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=121 || «? » «value» «? » */,
				st._204 /* V01*'|1' || «? » «value» «? » */,
				st._019 /* 1*']' || «! » «value» */
			},
			sl._12,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					ev._19)
			},
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._19, MultiplicativeCardinality.ZERO_OR_ONE)
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
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} ']' }
		private @NonNull SerializationRule _005 = new SerializationRule(62,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._031 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || «null» */,
				st._018 /* 1*'[' || «! » «value» «! » */,
				st._103 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._019 /* 1*']' || «! » «value» */
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
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} '|?' ']' }
		private @NonNull SerializationRule _006 = new SerializationRule(62,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._031 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || «null» */,
				st._018 /* 1*'[' || «! » «value» «! » */,
				st._103 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._053 /* 1*'|?' || «? » «value» «? » */,
				st._019 /* 1*']' || «! » «value» */
			},
			sl._14,
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
		private @NonNull SerializationRule _007 = new SerializationRule(62,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._085 /* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._031 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || «null» */,
				st._018 /* 1*'[' || «! » «value» «! » */,
				st._103 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._180 /* V00*'|1' || «? » «value» «? » */,
				st._019 /* 1*']' || «! » «value» */
			},
			sl._14,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._00),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					ev._19)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._19, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._00, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityStringCS : stringBounds={'*|+|?'}
		private @NonNull SerializationRule _008 = new SerializationRule(63,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._031 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._103 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */
			},
			sl._73,
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
		private @NonNull SerializationRule _009 = new SerializationRule(74,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._044 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._127 /* 1*PathElementCS::referredElement=UnreservedName || «? » «value» «? » */
			},
			sl._73,
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
		private @NonNull SerializationRule _010 = new SerializationRule(80,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._188 /* check-rule basecs::PathNameCS.ownedPathElements : 28|74 */,
				ms._064 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || «null» */,
				st._131 /* 1*PathNameCS::ownedPathElements+=28 || «null» */,
				st._192 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'::' || «! » «value» «! » */,
				st._133 /* 1*PathNameCS::ownedPathElements+=74 || «null» */
			},
			sl._03,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._29) /* FirstPathElementCS|NextPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(74, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(28, MultiplicativeCardinality.ONE)
					}
				)
			});
		// Base::TemplateBindingCS : { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _011 = new SerializationRule(103,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._196 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 104 */,
				ms._195 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 62 */,
				ms._122 /* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				ms._067 /* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._174 /* 1*steps-1..6 || «null» */,
				st._143 /* 1*TemplateBindingCS::ownedSubstitutions+=104 || «null» */,
				st._192 /* V00*steps-3..5 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._143 /* 1*TemplateBindingCS::ownedSubstitutions+=104 || «null» */,
				st._212 /* V01*TemplateBindingCS::ownedMultiplicity=62 || «null» */
			},
			sl._06,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					iv._52) /* TemplateParameterSubstitutionCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(104, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// Base::TemplateParameterSubstitutionCS : ownedActualParameter=TypeRefCS
		private @NonNull SerializationRule _012 = new SerializationRule(104,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._197 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 117 */,
				ms._051 /* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._144 /* 1*TemplateParameterSubstitutionCS::ownedActualParameter=117 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					iv._64) /* TypeRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(117, MultiplicativeCardinality.ONE)
					}
				)
			});
		// Base::TemplateSignatureCS : { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' }
		private @NonNull SerializationRule _013 = new SerializationRule(105,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._198 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 116 */,
				ms._068 /* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._175 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._145 /* 1*TemplateSignatureCS::ownedParameters+=116 || «null» */,
				st._197 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._145 /* 1*TemplateSignatureCS::ownedParameters+=116 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._38,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._63) /* TypeParameterCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(116, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// Base::TypeParameterCS : { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] }
		private @NonNull SerializationRule _014 = new SerializationRule(116,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._201 /* check-rule basecs::TypeParameterCS.ownedExtends : 119 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._071 /* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				ms._111 /* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=128 || «? » «value» «? » */,
				st._195 /* V00*steps-3..8 || «null» */,
				st._028 /* 1*'extends' || «? » «value» «? » */,
				st._152 /* 1*TypeParameterCS::ownedExtends+=119 || «null» */,
				st._221 /* V01*steps-6..8 || «null» */,
				st._000 /* 1*'&&' || «? » «value» «? » */,
				st._152 /* 1*TypeParameterCS::ownedExtends+=119 || «null» */
			},
			sl._50,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					iv._66) /* TypedRefCS */
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
					new RuleIndex_MultiplicativeCardinality(119, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::WildcardTypeRefCS : { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
		private @NonNull SerializationRule _015 = new SerializationRule(130,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._206 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : 119 */,
				ms._100 /* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || «null» */,
				st._013 /* 1*'?' || «? » «value» «? » */,
				st._192 /* V00*steps-3..5 || «null» */,
				st._028 /* 1*'extends' || «? » «value» «? » */,
				st._158 /* 1*WildcardTypeRefCS::ownedExtends=119 || «null» */
			},
			sl._26,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					iv._66) /* TypedRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(119, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::BooleanLiteralExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _016 = new SerializationRule(6,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._001 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._057 /* 1*BooleanLiteralExpCS::symbol || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._09)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._09, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::CoIteratorVariableCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] }
		private @NonNull SerializationRule _017 = new SerializationRule(8,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule essentialoclcs::VariableCS.ownedType : 110 */,
				ms._099 /* assign V0 = |VariableCS::ownedType| */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=128 || «? » «value» «? » */,
				st._192 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._157 /* 1*VariableCS::ownedType=110 || «null» */
			},
			sl._49,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._56) /* TypeExpCS */
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
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _018 = new SerializationRule(9,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._211 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 10 */,
				ms._212 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 12 */,
				ms._002 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._060 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._103 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._059 /* 1*CollectionLiteralExpCS::ownedType=12 || «null» */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._198 /* V00*steps-4..8 || «null» */,
				st._058 /* 1*CollectionLiteralExpCS::ownedParts+=10 || «null» */,
				st._221 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._058 /* 1*CollectionLiteralExpCS::ownedParts+=10 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._34,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					iv._4) /* CollectionLiteralPartCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					iv._5) /* CollectionTypeCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(10, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(12, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : ownedExpression=PatternExpCS
		private @NonNull SerializationRule _019 = new SerializationRule(10,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._214 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 81 */,
				ms._003 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._061 /* 1*CollectionLiteralPartCS::ownedExpression=81 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					iv._35) /* PatternExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(81, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] }
		private @NonNull SerializationRule _020 = new SerializationRule(10,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._213 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 27 */,
				ms._215 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 27 */,
				ms._073 /* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
				ms._003 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._060 /* 1*CollectionLiteralPartCS::ownedExpression=27 || «null» */,
				st._192 /* V00*steps-3..5 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._062 /* 1*CollectionLiteralPartCS::ownedLastExpression=27 || «null» */
			},
			sl._31,
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
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CollectionPatternCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' }
		private @NonNull SerializationRule _021 = new SerializationRule(11,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._217 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 12 */,
				ms._216 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 81 */,
				ms._074 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._104 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._004 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._160 /* 1*steps-1..11 || «null» */,
				st._064 /* 1*CollectionPatternCS::ownedType=12 || «null» */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._196 /* V00*steps-4..10 || «null» */,
				st._063 /* 1*CollectionPatternCS::ownedParts+=81 || «null» */,
				st._221 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._063 /* 1*CollectionPatternCS::ownedParts+=81 || «null» */,
				st._004 /* 1*'++' || «? » «value» «? » */,
				st._065 /* 1*CollectionPatternCS::restVariableName=31 || «? » «value» «? » */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._07,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._5) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._35) /* PatternExpCS */
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
					new RuleIndex_MultiplicativeCardinality(81, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(12, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionTypeCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
		private @NonNull SerializationRule _022 = new SerializationRule(12,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._218 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 62 */,
				ms._219 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 111 */,
				ms._075 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._113 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._175 /* 1*steps-1..7 || «null» */,
				st._066 /* 1*CollectionTypeCS::name=13 || «? » «value» «? » */,
				st._194 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._067 /* 1*CollectionTypeCS::ownedType=111 || «null» */,
				st._206 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=62 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._21,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._25) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._57) /* TypeExpWithoutMultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(111, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CurlyBracketedClauseCS : { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _023 = new SerializationRule(14,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._221 /* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 97 */,
				ms._061 /* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
				ms._105 /* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || «null» */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._194 /* V00*steps-3..7 || «null» */,
				st._071 /* 1*CurlyBracketedClauseCS::ownedParts+=97 || «null» */,
				st._217 /* V01*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._071 /* 1*CurlyBracketedClauseCS::ownedParts+=97 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._15,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					iv._47) /* ShadowPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(97, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ElseIfThenExpCS : { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS }
		private @NonNull SerializationRule _024 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._228 /* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 27 */,
				ms._227 /* check-rule essentialoclcs::IfThenExpCS.ownedCondition : 27 */,
				ms._016 /* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
				ms._015 /* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._026 /* 1*'elseif' || «? » «value» «? » */,
				st._076 /* 1*IfThenExpCS::ownedCondition=27 || «null» */,
				st._048 /* 1*'then' || «? » «value» «? » */,
				st._077 /* 1*IfThenExpCS::ownedThenExpression=27 || «null» */
			},
			sl._49,
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _025 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._001 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._057 /* 1*BooleanLiteralExpCS::symbol || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._09)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._09, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::ExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _026 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._211 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 10 */,
				ms._212 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 12 */,
				ms._002 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._060 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._103 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._059 /* 1*CollectionLiteralExpCS::ownedType=12 || «null» */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._198 /* V00*steps-4..8 || «null» */,
				st._058 /* 1*CollectionLiteralExpCS::ownedParts+=10 || «null» */,
				st._221 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._058 /* 1*CollectionLiteralExpCS::ownedParts+=10 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._34,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					iv._4) /* CollectionLiteralPartCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					iv._5) /* CollectionTypeCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(10, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(12, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : '*'
		private @NonNull SerializationRule _027 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._003 /* 1*'*' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : 'invalid'
		private @NonNull SerializationRule _028 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._033 /* 1*'invalid' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : 'null'
		private @NonNull SerializationRule _029 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._038 /* 1*'null' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : 'self'
		private @NonNull SerializationRule _030 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._047 /* 1*'self' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _031 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._225 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
				ms._224 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 27 */,
				ms._223 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 27|81 */,
				ms._226 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 27 */,
				ms._013 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._078 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._014 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._012 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._029 /* 1*'if' || «? » «value» «? » */,
				st._073 /* 1*IfExpCS::ownedCondition=27|81 || «null» */,
				st._048 /* 1*'then' || «? » «value» «? » */,
				st._075 /* 1*IfExpCS::ownedThenExpression=27 || «null» */,
				st._184 /* V00*IfExpCS::ownedIfThenExpressions+=20 || «null» */,
				st._025 /* 1*'else' || «? » «value» «? » */,
				st._074 /* 1*IfExpCS::ownedElseExpression=27 || «null» */,
				st._027 /* 1*'endif' || «? » «value» «? » */
			},
			sl._51,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._9) /* ElseIfThenExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._36) /* ExpCS|PatternExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(81, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS }
		private @NonNull SerializationRule _032 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._245 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 27 */,
				ms._229 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : 86 */,
				ms._042 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._018 /* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._079 /* 1*InfixExpCS::ownedLeft=86 || «null» */,
				st._109 /* 1*NamedElementCS::name=5 || «? » «value» «? » */,
				st._120 /* 1*OperatorExpCS::ownedRight=27 || «null» */
			},
			sl._35,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					iv._42) /* PrefixedPrimaryExpCS */
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
					new RuleIndex_MultiplicativeCardinality(86, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _033 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._230 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 27 */,
				ms._019 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._015 /* 1*'Lambda' || «? » «value» «? » */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._084 /* 1*LambdaLiteralExpCS::ownedExpressionCS=27 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._56,
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
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _034 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._234 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 58 */,
				ms._235 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 59 */,
				ms._024 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._063 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._108 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._095 /* 1*MapLiteralExpCS::ownedType=59 || «null» */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._198 /* V00*steps-4..8 || «null» */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=58 || «null» */,
				st._221 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=58 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._34,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					iv._23) /* MapLiteralPartCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					iv._24) /* MapTypeCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(58, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(59, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _035 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._209 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 92 */,
				ms._210 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 100 */,
				ms._208 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 80 */,
				ms._207 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14 */,
				ms._146 /* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				ms._137 /* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				ms._112 /* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				ms._072 /* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				ms._000 /* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._055 /* 1*AbstractNameExpCS::ownedPathName=80 || «null» */,
				st._181 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=100 || «null» */,
				st._205 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=92 || «null» */,
				st._225 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=14 || «null» */,
				st._240 /* V03*steps-6..8 || «null» */,
				st._014 /* 1*'@' || «? » «value» «? » */,
				st._043 /* 1*'pre' || «? » «value» «? » */
			},
			sl._30,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._04)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._43) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._49) /* SquareBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._34) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._6) /* CurlyBracketedClauseCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(80, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(100, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _036 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._244 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 27 */,
				ms._040 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._116 /* 1*NestedExpCS::ownedExpression=27 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._39,
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
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _037 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._041 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*NumberLiteralExpCS::symbol=64 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
		// EssentialOCL::ExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _038 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._247 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 86 */,
				ms._042 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._168 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._104 /* 1*NamedElementCS::name=125 || «? » «value» «? » */,
				st._122 /* 1*OperatorExpCS::ownedRight=86 || «null» */
			},
			sl._45,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._42) /* PrefixedPrimaryExpCS */
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
					new RuleIndex_MultiplicativeCardinality(86, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _039 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._093 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._187 /* V00*StringLiteralExpCS::segments+=101 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
		// EssentialOCL::ExpCS : { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
		private @NonNull SerializationRule _040 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._253 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 107 */,
				ms._069 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._017 /* 1*'Tuple' || «? » «value» «? » */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._146 /* 1*TupleLiteralExpCS::ownedParts+=107 || «null» */,
				st._200 /* V00*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._146 /* 1*TupleLiteralExpCS::ownedParts+=107 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._55,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._54) /* TupleLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _041 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._254 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 114 */,
				ms._053 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._148 /* 1*TypeLiteralExpCS::ownedType=114 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._59) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(114, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::IfExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _042 = new SerializationRule(32,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._225 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
				ms._224 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 27 */,
				ms._223 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 27|81 */,
				ms._226 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 27 */,
				ms._013 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._078 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._014 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._012 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._029 /* 1*'if' || «? » «value» «? » */,
				st._073 /* 1*IfExpCS::ownedCondition=27|81 || «null» */,
				st._048 /* 1*'then' || «? » «value» «? » */,
				st._075 /* 1*IfExpCS::ownedThenExpression=27 || «null» */,
				st._184 /* V00*IfExpCS::ownedIfThenExpressions+=20 || «null» */,
				st._025 /* 1*'else' || «? » «value» «? » */,
				st._074 /* 1*IfExpCS::ownedElseExpression=27 || «null» */,
				st._027 /* 1*'endif' || «? » «value» «? » */
			},
			sl._51,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._9) /* ElseIfThenExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._36) /* ExpCS|PatternExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(81, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::InvalidLiteralExpCS : 'invalid'
		private @NonNull SerializationRule _043 = new SerializationRule(36,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._033 /* 1*'invalid' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::LambdaLiteralExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _044 = new SerializationRule(41,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._230 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 27 */,
				ms._019 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._015 /* 1*'Lambda' || «? » «value» «? » */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._084 /* 1*LambdaLiteralExpCS::ownedExpressionCS=27 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._56,
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
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::LetExpCS : { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
		private @NonNull SerializationRule _045 = new SerializationRule(43,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._232 /* check-rule essentialoclcs::LetExpCS.ownedVariables : 44 */,
				ms._231 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : 27 */,
				ms._023 /* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
				ms._062 /* assign V0 = (|LetExpCS::ownedVariables| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._036 /* 1*'let' || «? » «value» «? » */,
				st._089 /* 1*LetExpCS::ownedVariables+=44 || «null» */,
				st._197 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._089 /* 1*LetExpCS::ownedVariables+=44 || «null» */,
				st._031 /* 1*'in' || «? » «value» «? » */,
				st._088 /* 1*LetExpCS::ownedInExpression=27 || «null» */
			},
			sl._46,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					iv._16) /* LetVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(44, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::LetVariableCS : { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _046 = new SerializationRule(44,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._233 /* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 92 */,
				ms._259 /* check-rule essentialoclcs::VariableCS.ownedType : 110 */,
				ms._258 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 27 */,
				ms._058 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._126 /* assign V1 = |VariableCS::ownedType| */,
				ms._080 /* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=128 || «? » «value» «? » */,
				st._185 /* V00*LetVariableCS::ownedRoundBracketedClause=92 || «null» */,
				st._216 /* V01*steps-4..6 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._157 /* 1*VariableCS::ownedType=110 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._156 /* 1*VariableCS::ownedInitExpression=27 || «null» */
			},
			sl._48,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._43) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */
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
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::MapLiteralExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _047 = new SerializationRule(57,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._234 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 58 */,
				ms._235 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 59 */,
				ms._024 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._063 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._108 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._095 /* 1*MapLiteralExpCS::ownedType=59 || «null» */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._198 /* V00*steps-4..8 || «null» */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=58 || «null» */,
				st._221 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=58 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._34,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					iv._23) /* MapLiteralPartCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					iv._24) /* MapTypeCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(58, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(59, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::MapLiteralPartCS : { ownedKey=ExpCS '<-' ownedValue=ExpCS }
		private @NonNull SerializationRule _048 = new SerializationRule(58,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._236 /* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 27 */,
				ms._237 /* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 27 */,
				ms._026 /* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
				ms._025 /* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._096 /* 1*MapLiteralPartCS::ownedKey=27 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._097 /* 1*MapLiteralPartCS::ownedValue=27 || «null» */
			},
			sl._35,
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
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::MapTypeCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
		private @NonNull SerializationRule _049 = new SerializationRule(59,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._239 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 110 */,
				ms._238 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 110 */,
				ms._083 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._028 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._027 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || «null» */,
				st._016 /* 1*'Map' || «? » «value» «? » */,
				st._195 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._098 /* 1*MapTypeCS::ownedKeyType=110 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._099 /* 1*MapTypeCS::ownedValueType=110 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._23,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._06)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._56) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._06, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::Model : ownedExpression=ExpCS
		private @NonNull SerializationRule _050 = new SerializationRule(60,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._220 /* check-rule essentialoclcs::ContextCS.ownedExpression : 27 */,
				ms._010 /* assert (|ContextCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._070 /* 1*ContextCS::ownedExpression=27 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NameExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _051 = new SerializationRule(66,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._209 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 92 */,
				ms._210 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 100 */,
				ms._208 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 80 */,
				ms._207 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 14 */,
				ms._146 /* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				ms._137 /* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				ms._112 /* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				ms._072 /* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				ms._000 /* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._055 /* 1*AbstractNameExpCS::ownedPathName=80 || «null» */,
				st._181 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=100 || «null» */,
				st._205 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=92 || «null» */,
				st._225 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=14 || «null» */,
				st._240 /* V03*steps-6..8 || «null» */,
				st._014 /* 1*'@' || «? » «value» «? » */,
				st._043 /* 1*'pre' || «? » «value» «? » */
			},
			sl._30,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._04)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._43) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._49) /* SquareBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._34) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._6) /* CurlyBracketedClauseCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(80, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(92, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(100, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : ownedNameExpression=NavigatingArgExpCS
		private @NonNull SerializationRule _052 = new SerializationRule(67,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._242 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
				ms._035 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*NavigatingArgCS::ownedNameExpression=68 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._26) /* NavigatingArgExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _053 = new SerializationRule(67,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._243 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
				ms._036 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._168 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._115 /* 1*NavigatingArgCS::ownedType=110 || «null» */
			},
			sl._45,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._56) /* TypeExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _054 = new SerializationRule(67,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._240 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8 */,
				ms._243 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
				ms._242 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
				ms._241 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
				ms._120 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				ms._087 /* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				ms._036 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				ms._035 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._159 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._114 /* 1*NavigatingArgCS::ownedNameExpression=68 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._115 /* 1*NavigatingArgCS::ownedType=110 || «null» */,
				st._200 /* V00*steps-5..7 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedCoIterator=8 || «null» */,
				st._223 /* V01*steps-8..10 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._113 /* 1*NavigatingArgCS::ownedInitExpression=27 || «null» */
			},
			sl._37,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._3) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._26) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(8, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _055 = new SerializationRule(67,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._240 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8 */,
				ms._242 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
				ms._241 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
				ms._088 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._033 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._035 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._175 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._114 /* 1*NavigatingArgCS::ownedNameExpression=68 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedCoIterator=8 || «null» */,
				st._200 /* V00*steps-5..7 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._113 /* 1*NavigatingArgCS::ownedInitExpression=27 || «null» */
			},
			sl._36,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._3) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._26) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(8, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _056 = new SerializationRule(67,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._240 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8 */,
				ms._243 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
				ms._242 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
				ms._241 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
				ms._034 /* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				ms._119 /* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				ms._089 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._035 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._159 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._114 /* 1*NavigatingArgCS::ownedNameExpression=68 || «null» */,
				st._192 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._115 /* 1*NavigatingArgCS::ownedType=110 || «null» */,
				st._221 /* V01*steps-6..8 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedCoIterator=8 || «null» */,
				st._031 /* 1*'in' || «? » «value» «? » */,
				st._113 /* 1*NavigatingArgCS::ownedInitExpression=27 || «null» */
			},
			sl._33,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._3) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._26) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(8, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingBarArgCS : { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _057 = new SerializationRule(69,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._243 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
				ms._242 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
				ms._241 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
				ms._089 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._035 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._039 /* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
				ms._120 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._052 /* 1*'|' || «? » «value» «? » */,
				st._114 /* 1*NavigatingArgCS::ownedNameExpression=68 || «null» */,
				st._199 /* V00*steps-4..9 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._115 /* 1*NavigatingArgCS::ownedType=110 || «null» */,
				st._222 /* V01*steps-7..9 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._113 /* 1*NavigatingArgCS::ownedInitExpression=27 || «null» */
			},
			sl._47,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._18)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._26) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._18, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _058 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._240 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8 */,
				ms._243 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
				ms._242 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
				ms._241 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
				ms._120 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				ms._087 /* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				ms._036 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				ms._035 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._037 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._160 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._114 /* 1*NavigatingArgCS::ownedNameExpression=68 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._115 /* 1*NavigatingArgCS::ownedType=110 || «null» */,
				st._201 /* V00*steps-6..8 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedCoIterator=8 || «null» */,
				st._224 /* V01*steps-9..11 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._113 /* 1*NavigatingArgCS::ownedInitExpression=27 || «null» */
			},
			sl._43,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._3) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._26) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(8, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _059 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._240 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8 */,
				ms._242 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
				ms._241 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
				ms._088 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._033 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._035 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._037 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._114 /* 1*NavigatingArgCS::ownedNameExpression=68 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedCoIterator=8 || «null» */,
				st._201 /* V00*steps-6..8 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._113 /* 1*NavigatingArgCS::ownedInitExpression=27 || «null» */
			},
			sl._42,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._3) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._26) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(8, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _060 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._240 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 8 */,
				ms._243 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
				ms._242 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
				ms._241 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
				ms._034 /* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				ms._119 /* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				ms._089 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._035 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._037 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._160 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._114 /* 1*NavigatingArgCS::ownedNameExpression=68 || «null» */,
				st._197 /* V00*steps-4..6 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._115 /* 1*NavigatingArgCS::ownedType=110 || «null» */,
				st._222 /* V01*steps-7..9 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedCoIterator=8 || «null» */,
				st._031 /* 1*'in' || «? » «value» «? » */,
				st._113 /* 1*NavigatingArgCS::ownedInitExpression=27 || «null» */
			},
			sl._41,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._3) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._26) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(8, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS }
		private @NonNull SerializationRule _061 = new SerializationRule(70,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._242 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
				ms._035 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._037 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._168 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._114 /* 1*NavigatingArgCS::ownedNameExpression=68 || «null» */
			},
			sl._40,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._26) /* NavigatingArgExpCS */
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
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingSemiArgCS : { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _062 = new SerializationRule(71,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._243 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 110 */,
				ms._242 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 68 */,
				ms._241 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 27 */,
				ms._089 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._035 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._038 /* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
				ms._120 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._009 /* 1*';' || «! » «value» «?\n» */,
				st._114 /* 1*NavigatingArgCS::ownedNameExpression=68 || «null» */,
				st._199 /* V00*steps-4..9 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._115 /* 1*NavigatingArgCS::ownedType=110 || «null» */,
				st._222 /* V01*steps-7..9 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._113 /* 1*NavigatingArgCS::ownedInitExpression=27 || «null» */
			},
			sl._44,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._03)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._26) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */
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
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(68, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NestedExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _063 = new SerializationRule(73,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._244 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 27 */,
				ms._040 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._116 /* 1*NestedExpCS::ownedExpression=27 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._39,
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
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				)
			});
	}
	private class _SerializationRules1
	{
		// EssentialOCL::NullLiteralExpCS : 'null'
		private @NonNull SerializationRule _064 = new SerializationRule(75,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._038 /* 1*'null' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::NumberLiteralExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _065 = new SerializationRule(76,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._041 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*NumberLiteralExpCS::symbol=64 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
		private @NonNull SerializationRule _066 = new SerializationRule(81,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._248 /* check-rule essentialoclcs::PatternExpCS.ownedPatternType : 110 */,
				ms._046 /* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
				ms._091 /* assign V0 = |PatternExpCS::patternVariableName| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._186 /* V00*PatternExpCS::patternVariableName=128 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._134 /* 1*PatternExpCS::ownedPatternType=110 || «null» */
			},
			sl._57,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					iv._56) /* TypeExpCS */
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
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedLetExpCS : { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		private @NonNull SerializationRule _067 = new SerializationRule(85,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._246 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 85 */,
				ms._042 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._168 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._104 /* 1*NamedElementCS::name=125 || «? » «value» «? » */,
				st._121 /* 1*OperatorExpCS::ownedRight=85 || «null» */
			},
			sl._45,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._40) /* PrefixedLetExpCS */
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
					new RuleIndex_MultiplicativeCardinality(85, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedPrimaryExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _068 = new SerializationRule(86,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._247 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 86 */,
				ms._042 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._168 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._104 /* 1*NamedElementCS::name=125 || «? » «value» «? » */,
				st._122 /* 1*OperatorExpCS::ownedRight=86 || «null» */
			},
			sl._45,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._42) /* PrefixedPrimaryExpCS */
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
					new RuleIndex_MultiplicativeCardinality(86, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrimitiveTypeCS : name=PrimitiveTypeIdentifier
		private @NonNull SerializationRule _069 = new SerializationRule(89,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._048 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._135 /* 1*PrimitiveTypeRefCS::name=90 || «? » «value» «? » */
			},
			sl._73,
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
		private @NonNull SerializationRule _070 = new SerializationRule(92,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._249 /* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 67|69|70|71 */,
				ms._065 /* assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0) */,
				ms._109 /* assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._174 /* 1*steps-1..6 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._192 /* V00*steps-3..5 || «null» */,
				st._137 /* 1*RoundBracketedClauseCS::ownedArguments+=67 || «null» */,
				st._211 /* V01*RoundBracketedClauseCS::ownedArguments+=70|71|69 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._09,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					iv._28) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ZERO_OR_ONE),
					new RuleIndex_MultiplicativeCardinality(69, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(70, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(71, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::SelfExpCS : 'self'
		private @NonNull SerializationRule _071 = new SerializationRule(96,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._047 /* 1*'self' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ShadowPartCS : ownedInitExpression=StringLiteralExpCS
		private @NonNull SerializationRule _072 = new SerializationRule(97,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._250 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 102 */,
				ms._049 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._138 /* 1*ShadowPartCS::ownedInitExpression=102 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._50) /* StringLiteralExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(102, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ShadowPartCS : { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) }
		private @NonNull SerializationRule _073 = new SerializationRule(97,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._251 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 27|81 */,
				ms._049 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
				ms._050 /* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._140 /* 1*ShadowPartCS::referredProperty=UnrestrictedName || «? » «value» «? » */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._139 /* 1*ShadowPartCS::ownedInitExpression=27|81 || «null» */
			},
			sl._57,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._36) /* ExpCS|PatternExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(81, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				)
			});
		// EssentialOCL::SimplePathNameCS : ownedPathElements+=FirstPathElementCS
		private @NonNull SerializationRule _074 = new SerializationRule(98,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._187 /* check-rule basecs::PathNameCS.ownedPathElements : 28 */,
				ms._045 /* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._131 /* 1*PathNameCS::ownedPathElements+=28 || «null» */
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
					new RuleIndex_MultiplicativeCardinality(28, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::SquareBracketedClauseCS : { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' }
		private @NonNull SerializationRule _075 = new SerializationRule(100,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._252 /* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 27 */,
				ms._066 /* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._175 /* 1*steps-1..7 || «null» */,
				st._018 /* 1*'[' || «! » «value» «! » */,
				st._141 /* 1*SquareBracketedClauseCS::ownedTerms+=27 || «null» */,
				st._197 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._141 /* 1*SquareBracketedClauseCS::ownedTerms+=27 || «null» */,
				st._019 /* 1*']' || «! » «value» */
			},
			sl._10,
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
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::StringLiteralExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _076 = new SerializationRule(102,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._093 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._187 /* V00*StringLiteralExpCS::segments+=101 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
		private @NonNull SerializationRule _077 = new SerializationRule(106,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._253 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 107 */,
				ms._069 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._017 /* 1*'Tuple' || «? » «value» «? » */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._146 /* 1*TupleLiteralExpCS::ownedParts+=107 || «null» */,
				st._200 /* V00*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._146 /* 1*TupleLiteralExpCS::ownedParts+=107 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._55,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._54) /* TupleLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(107, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::TupleLiteralPartCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _078 = new SerializationRule(107,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._259 /* check-rule essentialoclcs::VariableCS.ownedType : 110 */,
				ms._258 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 27 */,
				ms._058 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._099 /* assign V0 = |VariableCS::ownedType| */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._175 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=128 || «? » «value» «? » */,
				st._192 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._157 /* 1*VariableCS::ownedType=110 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._156 /* 1*VariableCS::ownedInitExpression=27 || «null» */
			},
			sl._53,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._10) /* ExpCS */
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
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TupleTypeCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
		private @NonNull SerializationRule _079 = new SerializationRule(109,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._200 /* check-rule basecs::TupleTypeCS.ownedParts : 108 */,
				ms._052 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._070 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._110 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._136 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._159 /* 1*steps-1..10 || «null» */,
				st._017 /* 1*'Tuple' || «? » «value» «? » */,
				st._191 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._219 /* V01*steps-5..9 || «null» */,
				st._147 /* 1*TupleTypeCS::ownedParts+=108 || «null» */,
				st._233 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._147 /* 1*TupleTypeCS::ownedParts+=108 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._19,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._07)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._55) /* TuplePartCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._07, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(108, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _080 = new SerializationRule(110,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._097 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._048 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._168 /* 1*steps-1..3 || «null» */,
				st._135 /* 1*PrimitiveTypeRefCS::name=90 || «? » «value» «? » */,
				st._189 /* V00*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._16,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _081 = new SerializationRule(110,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._200 /* check-rule basecs::TupleTypeCS.ownedParts : 108 */,
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._150 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._052 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._070 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._110 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._136 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._160 /* 1*steps-1..11 || «null» */,
				st._017 /* 1*'Tuple' || «? » «value» «? » */,
				st._191 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._219 /* V01*steps-5..9 || «null» */,
				st._147 /* 1*TupleTypeCS::ownedParts+=108 || «null» */,
				st._233 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._147 /* 1*TupleTypeCS::ownedParts+=108 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._238 /* V03*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._20,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._07)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._55) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._07, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(108, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _082 = new SerializationRule(110,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._217 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 12 */,
				ms._216 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 81 */,
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._142 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._074 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._104 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._004 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._161 /* 1*steps-1..12 || «null» */,
				st._064 /* 1*CollectionPatternCS::ownedType=12 || «null» */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._196 /* V00*steps-4..10 || «null» */,
				st._063 /* 1*CollectionPatternCS::ownedParts+=81 || «null» */,
				st._221 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._063 /* 1*CollectionPatternCS::ownedParts+=81 || «null» */,
				st._004 /* 1*'++' || «? » «value» «? » */,
				st._065 /* 1*CollectionPatternCS::restVariableName=31 || «? » «value» «? » */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._228 /* V02*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._08,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._5) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._35) /* PatternExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(81, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(12, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _083 = new SerializationRule(110,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._218 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 62 */,
				ms._219 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 111 */,
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._142 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._075 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._113 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || «null» */,
				st._066 /* 1*CollectionTypeCS::name=13 || «? » «value» «? » */,
				st._194 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._067 /* 1*CollectionTypeCS::ownedType=111 || «null» */,
				st._206 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=62 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._228 /* V02*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._22,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._25) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._57) /* TypeExpWithoutMultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(111, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _084 = new SerializationRule(110,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._239 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 110 */,
				ms._238 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 110 */,
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._125 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._083 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._028 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._027 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*steps-1..9 || «null» */,
				st._016 /* 1*'Map' || «? » «value» «? » */,
				st._195 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._098 /* 1*MapTypeCS::ownedKeyType=110 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._099 /* 1*MapTypeCS::ownedValueType=110 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._214 /* V01*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._24,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._06)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._06, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _085 = new SerializationRule(110,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._256 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 80 */,
				ms._257 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 27 */,
				ms._255 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14 */,
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._142 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._096 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._054 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._124 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*steps-1..9 || «null» */,
				st._150 /* 1*TypeNameExpCS::ownedPathName=80 || «null» */,
				st._195 /* V00*steps-3..8 || «null» */,
				st._149 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=14 || «null» */,
				st._218 /* V01*steps-5..8 || «null» */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._151 /* 1*TypeNameExpCS::ownedPatternGuard=27 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._228 /* V02*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._02,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._34) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._6) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(14, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(80, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _086 = new SerializationRule(113,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._254 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 114 */,
				ms._053 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._148 /* 1*TypeLiteralExpCS::ownedType=114 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._59) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(114, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _087 = new SerializationRule(114,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._097 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._048 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._168 /* 1*steps-1..3 || «null» */,
				st._135 /* 1*PrimitiveTypeRefCS::name=90 || «? » «value» «? » */,
				st._189 /* V00*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._16,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _088 = new SerializationRule(114,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._200 /* check-rule basecs::TupleTypeCS.ownedParts : 108 */,
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._150 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._052 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._070 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._110 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._136 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._160 /* 1*steps-1..11 || «null» */,
				st._017 /* 1*'Tuple' || «? » «value» «? » */,
				st._191 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._219 /* V01*steps-5..9 || «null» */,
				st._147 /* 1*TupleTypeCS::ownedParts+=108 || «null» */,
				st._233 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._147 /* 1*TupleTypeCS::ownedParts+=108 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._238 /* V03*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._20,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._07)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._55) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._07, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(108, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _089 = new SerializationRule(114,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._218 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 62 */,
				ms._219 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 111 */,
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._142 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._075 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._113 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || «null» */,
				st._066 /* 1*CollectionTypeCS::name=13 || «? » «value» «? » */,
				st._194 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._067 /* 1*CollectionTypeCS::ownedType=111 || «null» */,
				st._206 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=62 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._228 /* V02*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._22,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._25) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._57) /* TypeExpWithoutMultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(111, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _090 = new SerializationRule(114,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._239 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 110 */,
				ms._238 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 110 */,
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._125 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._083 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._028 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._027 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*steps-1..9 || «null» */,
				st._016 /* 1*'Map' || «? » «value» «? » */,
				st._195 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._098 /* 1*MapTypeCS::ownedKeyType=110 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._099 /* 1*MapTypeCS::ownedValueType=110 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._214 /* V01*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._24,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._06)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._06, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeNameExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
		private @NonNull SerializationRule _091 = new SerializationRule(115,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._256 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 80 */,
				ms._257 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 27 */,
				ms._255 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 14 */,
				ms._096 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._054 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._124 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._176 /* 1*steps-1..8 || «null» */,
				st._150 /* 1*TypeNameExpCS::ownedPathName=80 || «null» */,
				st._195 /* V00*steps-3..8 || «null» */,
				st._149 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=14 || «null» */,
				st._218 /* V01*steps-5..8 || «null» */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._151 /* 1*TypeNameExpCS::ownedPatternGuard=27 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._01,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._34) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._10) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._6) /* CurlyBracketedClauseCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(14, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(80, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::URIFirstPathElementCS : referredElement=UnrestrictedName
		private @NonNull SerializationRule _092 = new SerializationRule(123,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._044 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._129 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._73,
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
		private @NonNull SerializationRule _093 = new SerializationRule(123,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._044 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._126 /* 1*PathElementCS::referredElement=URI || «? » «value» «? » */
			},
			sl._73,
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
		private @NonNull SerializationRule _094 = new SerializationRule(124,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._190 /* check-rule basecs::PathNameCS.ownedPathElements : 74|123 */,
				ms._064 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || «null» */,
				st._130 /* 1*PathNameCS::ownedPathElements+=123 || «null» */,
				st._192 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'::' || «! » «value» «! » */,
				st._133 /* 1*PathNameCS::ownedPathElements+=74 || «null» */
			},
			sl._03,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._69) /* NextPathElementCS|URIFirstPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(74, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(123, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS : '*'
		private @NonNull SerializationRule _095 = new SerializationRule(126,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._003 /* 1*'*' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// OCLstdlib::AccumulatorCS : { name=Identifier ':' ownedType=TypedMultiplicityRefCS }
		private @NonNull SerializationRule _096 = new SerializationRule(1,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._202 /* check-rule basecs::TypedElementCS.ownedType : 118 */,
				ms._055 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._106 /* 1*NamedElementCS::name=31 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=118 || «null» */
			},
			sl._57,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._65) /* TypedMultiplicityRefCS */
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
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::AnnotationCS : { 'annotation' name=(Identifier|SINGLE_QUOTED_STRING) { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
		private @NonNull SerializationRule _097 = new SerializationRule(2,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._169 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._059 /* assign V0 = (|AnnotationElementCS::ownedDetails| > 0) */,
				ms._101 /* assign V1 = (|AnnotationElementCS::ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._160 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._020 /* 1*'annotation' || «? » «value» «? » */,
				st._107 /* 1*NamedElementCS::name=31|94 || «? » «value» «? » */,
				st._196 /* V00*steps-4..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._056 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._222 /* V01*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._056 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._009 /* 1*';' || «! » «value» «?\n» */
			},
			sl._60,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._7) /* DetailCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(16, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLstdlib::AnnotationCS : { 'annotation' name=(Identifier|SINGLE_QUOTED_STRING) { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS '}' }
		private @NonNull SerializationRule _098 = new SerializationRule(2,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._169 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._177 /* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
				ms._029 /* assert (|ModelElementCS::ownedAnnotations| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._059 /* assign V0 = (|AnnotationElementCS::ownedDetails| > 0) */,
				ms._101 /* assign V1 = (|AnnotationElementCS::ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._162 /* 1*steps-1..13 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._020 /* 1*'annotation' || «? » «value» «? » */,
				st._107 /* 1*NamedElementCS::name=31|94 || «? » «value» «? » */,
				st._196 /* V00*steps-4..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._056 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._222 /* V01*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._056 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._100 /* 1*ModelElementCS::ownedAnnotations+=3 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._61,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._7) /* DetailCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._1) /* AnnotationElementCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(16, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLstdlib::DetailCS : { name=(Name|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] }
		private @NonNull SerializationRule _099 = new SerializationRule(16,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._076 /* assign V0 = |DetailCS::values| */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._111 /* 1*NamedElementCS::name=65|94 || «? » «value» «? » */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._182 /* V00*DetailCS::values+=94|56 || «? » «value» «? » */
			},
			sl._66,
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
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.DETAIL_CS__VALUES,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			},
			null);
		// OCLstdlib::DocumentationCS : { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
		private @NonNull SerializationRule _100 = new SerializationRule(17,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._169 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._077 /* assign V0 = |DocumentationCS::value| */,
				ms._102 /* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				ms._131 /* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._160 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._024 /* 1*'documentation' || «? » «value» «? » */,
				st._183 /* V00*DocumentationCS::value=94 || «? » «value» «? » */,
				st._215 /* V01*steps-4..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._056 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._233 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._056 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._009 /* 1*';' || «! » «value» «?\n» */
			},
			sl._60,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._7) /* DetailCS */
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
		// OCLstdlib::ImportCS : { 'import' { name=Identifier ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] }
		private @NonNull SerializationRule _101 = new SerializationRule(33,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._173 /* check-rule basecs::ImportCS.ownedPathName : 124 */,
				ms._115 /* assign V1 = |ImportCS::isAll.'::*'| */,
				ms._017 /* assert (|ImportCS::ownedPathName| - 1) == 0 */,
				ms._086 /* assign V0 = |NamedElementCS::name| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._175 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._030 /* 1*'import' || «? » «value» «? » */,
				st._192 /* V00*steps-3..5 || «null» */,
				st._106 /* 1*NamedElementCS::name=31 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._078 /* 1*ImportCS::ownedPathName=124 || «null» */,
				st._203 /* V01*'::*' || «? » «value» «? » */
			},
			sl._54,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
					ev._02)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					iv._71) /* URIPathNameCS */
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
					new RuleIndex_MultiplicativeCardinality(124, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::InvCS : { stereotype='inv' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS ';' }
		private @NonNull SerializationRule _102 = new SerializationRule(35,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._171 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 99 */,
				ms._172 /* check-rule basecs::ConstraintCS.ownedSpecification : 99 */,
				ms._006 /* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				ms._086 /* assign V0 = |NamedElementCS::name| */,
				ms._007 /* assert (|ConstraintCS::stereotype.'inv'| - 1) == 0 */,
				ms._114 /* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._160 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._032 /* 1*'inv' || «? » «value» «? » */,
				st._195 /* V00*steps-3..8 || «null» */,
				st._105 /* 1*NamedElementCS::name=128 || «? » «value» «? » */,
				st._218 /* V01*steps-5..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._068 /* 1*ConstraintCS::ownedMessageSpecification=99 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._069 /* 1*ConstraintCS::ownedSpecification=99 || «null» */,
				st._009 /* 1*';' || «! » «value» «?\n» */
			},
			sl._52,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._10)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._48) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._48) /* SpecificationCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._10, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(99, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(99, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::IteratorCS : { name=Identifier ':' ownedType=TypedMultiplicityRefCS }
		private @NonNull SerializationRule _103 = new SerializationRule(37,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._202 /* check-rule basecs::TypedElementCS.ownedType : 118 */,
				ms._055 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._106 /* 1*NamedElementCS::name=31 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=118 || «null» */
			},
			sl._57,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._65) /* TypedMultiplicityRefCS */
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
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::LambdaContextTypeRefCS : ownedPathName=LibPathNameCS
		private @NonNull SerializationRule _104 = new SerializationRule(40,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._205 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 52 */,
				ms._057 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._155 /* 1*TypedTypeRefCS::ownedPathName=52 || «null» */
			},
			sl._00,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._21) /* LibPathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(52, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::LambdaTypeCS : { name='Lambda' ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS '(' { ownedParameterTypes+=TypedMultiplicityRefCS { ',' ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ')' ':' ownedResultType=TypedRefCS }
		private @NonNull SerializationRule _105 = new SerializationRule(42,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._174 /* check-rule basecs::LambdaTypeCS.ownedContextType : 40 */,
				ms._199 /* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
				ms._176 /* check-rule basecs::LambdaTypeCS.ownedResultType : 119 */,
				ms._175 /* check-rule basecs::LambdaTypeCS.ownedParameterTypes : 118 */,
				ms._022 /* assert (|LambdaTypeCS::ownedResultType| - 1) == 0 */,
				ms._021 /* assert (|LambdaTypeCS::ownedContextType| - 1) == 0 */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._020 /* assert (|LambdaTypeCS::name.'Lambda'| - 1) == 0 */,
				ms._106 /* assign V1 = (|LambdaTypeCS::ownedParameterTypes| > 0) */,
				ms._132 /* assign V2 = (|LambdaTypeCS::ownedParameterTypes| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._162 /* 1*steps-1..13 || «null» */,
				st._015 /* 1*'Lambda' || «? » «value» «? » */,
				st._188 /* V00*TemplateableElementCS::ownedSignature=105 || «null» */,
				st._085 /* 1*LambdaTypeCS::ownedContextType=40 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._220 /* V01*steps-6..10 || «null» */,
				st._086 /* 1*LambdaTypeCS::ownedParameterTypes+=118 || «null» */,
				st._234 /* V02*steps-8..10 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._086 /* 1*LambdaTypeCS::ownedParameterTypes+=118 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._087 /* 1*LambdaTypeCS::ownedResultType=119 || «null» */
			},
			sl._17,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE,
					iv._15) /* LambdaContextTypeRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._53) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE,
					iv._66) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES,
					iv._65) /* TypedMultiplicityRefCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._05, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(119, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(105, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLstdlib::LibClassCS : { isAbstract='abstract'[?] 'type' name=AnyName ownedSignature=TemplateSignatureCS[?] { ':' metaclassName=AnyName }[?] { 'conformsTo' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] '{' ownedOperations+=OperationCS[*] ownedProperties+=LibPropertyCS[*] ownedConstraints+=InvCS[*] ownedAnnotations+=AnnotationElementCS[*] '}' }
		private @NonNull SerializationRule _106 = new SerializationRule(45,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._194 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : 119 */,
				ms._192 /* check-rule basecs::StructuredClassCS.ownedOperations : 77 */,
				ms._199 /* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
				ms._170 /* check-rule basecs::ClassCS.ownedConstraints : 35 */,
				ms._193 /* check-rule basecs::StructuredClassCS.ownedProperties : 53 */,
				ms._177 /* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
				ms._166 /* assign V8 = |ModelElementCS::ownedAnnotations| */,
				ms._162 /* assign V7 = |ClassCS::ownedConstraints| */,
				ms._161 /* assign V6 = |StructuredClassCS::ownedProperties| */,
				ms._158 /* assign V5 = |StructuredClassCS::ownedOperations| */,
				ms._139 /* assign V2 = |LibClassCS::metaclassName| */,
				ms._123 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._094 /* assign V0 = |StructuredClassCS::isAbstract.'abstract'| */,
				ms._145 /* assign V3 = (|StructuredClassCS::ownedSuperTypes| > 0) */,
				ms._152 /* assign V4 = (|StructuredClassCS::ownedSuperTypes| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._166 /* 1*steps-1..20 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._178 /* V00*'abstract' || «? » «value» «? » */,
				st._049 /* 1*'type' || «? » «value» «? » */,
				st._108 /* 1*NamedElementCS::name=4 || «? » «value» «? » */,
				st._213 /* V01*TemplateableElementCS::ownedSignature=105 || «null» */,
				st._231 /* V02*steps-6..8 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._090 /* 1*LibClassCS::metaclassName=AnyName || «? » «value» «? » */,
				st._242 /* V03*steps-9..14 || «null» */,
				st._023 /* 1*'conformsTo' || «? » «value» «? » */,
				st._142 /* 1*StructuredClassCS::ownedSuperTypes+=119 || «null» */,
				st._245 /* V04*steps-12..14 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._142 /* 1*StructuredClassCS::ownedSuperTypes+=119 || «null» */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._249 /* V05*StructuredClassCS::ownedOperations+=77 || «null» */,
				st._252 /* V06*StructuredClassCS::ownedProperties+=53 || «null» */,
				st._255 /* V07*ClassCS::ownedConstraints+=35 || «null» */,
				st._257 /* V08*ModelElementCS::ownedAnnotations+=3 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._68,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					ev._08)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					iv._66) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					iv._30) /* OperationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._53) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					iv._22) /* LibPropertyCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._1) /* AnnotationElementCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._08, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_CLASS_CS__METACLASS_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(35, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(77, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(53, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(105, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(119, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLstdlib::LibCoercionCS : { 'coercion' name=Name '(' ')' ':' ownedType=TypedMultiplicityRefCS { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' }
		private @NonNull SerializationRule _107 = new SerializationRule(46,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._202 /* check-rule basecs::TypedElementCS.ownedType : 118 */,
				ms._079 /* assign V0 = |JavaImplementationCS::implementation| */,
				ms._055 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._160 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._022 /* 1*'coercion' || «? » «value» «? » */,
				st._110 /* 1*NamedElementCS::name=65 || «? » «value» «? » */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=118 || «null» */,
				st._202 /* V00*steps-8..10 || «null» */,
				st._012 /* 1*'=>' || «? » «value» «? » */,
				st._081 /* 1*JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || «? » «value» «? » */,
				st._009 /* 1*';' || «! » «value» «?\n» */
			},
			sl._64,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._65) /* TypedMultiplicityRefCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::LibCoercionCS : { 'coercion' name=Name '(' ')' ':' ownedType=TypedMultiplicityRefCS { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] '}' }
		private @NonNull SerializationRule _108 = new SerializationRule(46,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._202 /* check-rule basecs::TypedElementCS.ownedType : 118 */,
				ms._182 /* check-rule basecs::OperationCS.ownedPreconditions : 82 */,
				ms._181 /* check-rule basecs::OperationCS.ownedPostconditions : 83 */,
				ms._177 /* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
				ms._149 /* assign V3 = |OperationCS::ownedPostconditions| */,
				ms._140 /* assign V2 = |OperationCS::ownedPreconditions| */,
				ms._117 /* assign V1 = |ModelElementCS::ownedAnnotations| */,
				ms._079 /* assign V0 = |JavaImplementationCS::implementation| */,
				ms._055 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._164 /* 1*steps-1..15 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._022 /* 1*'coercion' || «? » «value» «? » */,
				st._110 /* 1*NamedElementCS::name=65 || «? » «value» «? » */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=118 || «null» */,
				st._202 /* V00*steps-8..10 || «null» */,
				st._012 /* 1*'=>' || «? » «value» «? » */,
				st._081 /* 1*JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || «? » «value» «? » */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._208 /* V01*ModelElementCS::ownedAnnotations+=3 || «null» */,
				st._226 /* V02*OperationCS::ownedPreconditions+=82 || «null» */,
				st._237 /* V03*OperationCS::ownedPostconditions+=83 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._65,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._65) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._37) /* PostCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._38) /* PreCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._1) /* AnnotationElementCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(83, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(82, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::LibIterationCS : { 'iteration' name=Name ownedSignature=TemplateSignatureCS[?] '(' ownedIterators+=IteratorCS { ',' ownedIterators+=IteratorCS }[*] { ';' ownedAccumulators+=AccumulatorCS { ',' ownedAccumulators+=AccumulatorCS }[*] }[?] { '|' ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isInvalidating='invalidating'[?] isValidating='validating'[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' }
		private @NonNull SerializationRule _109 = new SerializationRule(47,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._179 /* check-rule basecs::OperationCS.ownedParameters : 79 */,
				ms._202 /* check-rule basecs::TypedElementCS.ownedType : 118 */,
				ms._260 /* check-rule oclstdlibcs::LibIterationCS.ownedAccumulators : 1 */,
				ms._199 /* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
				ms._261 /* check-rule oclstdlibcs::LibIterationCS.ownedIterators : 37 */,
				ms._165 /* assign V8 = |JavaImplementationCS::implementation| */,
				ms._164 /* assign V7 = |LibIterationCS::isValidating.'validating'| */,
				ms._159 /* assign V6 = |LibIterationCS::isInvalidating.'invalidating'| */,
				ms._055 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._107 /* assign V1 = (|LibIterationCS::ownedIterators| - 1) */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._151 /* assign V4 = (|OperationCS::ownedParameters| > 0) */,
				ms._155 /* assign V5 = (|OperationCS::ownedParameters| - 1) */,
				ms._133 /* assign V2 = (|LibIterationCS::ownedAccumulators| > 0) */,
				ms._143 /* assign V3 = (|LibIterationCS::ownedAccumulators| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._169 /* 1*steps-1..30 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'iteration' || «? » «value» «? » */,
				st._110 /* 1*NamedElementCS::name=65 || «? » «value» «? » */,
				st._188 /* V00*TemplateableElementCS::ownedSignature=105 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._092 /* 1*LibIterationCS::ownedIterators+=37 || «null» */,
				st._222 /* V01*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._092 /* 1*LibIterationCS::ownedIterators+=37 || «null» */,
				st._229 /* V02*steps-10..15 || «null» */,
				st._009 /* 1*';' || «! » «value» «?\n» */,
				st._091 /* 1*LibIterationCS::ownedAccumulators+=1 || «null» */,
				st._239 /* V03*steps-13..15 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._091 /* 1*LibIterationCS::ownedAccumulators+=1 || «null» */,
				st._246 /* V04*steps-16..21 || «null» */,
				st._052 /* 1*'|' || «? » «value» «? » */,
				st._119 /* 1*OperationCS::ownedParameters+=79 || «null» */,
				st._250 /* V05*steps-19..21 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._119 /* 1*OperationCS::ownedParameters+=79 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=118 || «null» */,
				st._251 /* V06*'invalidating' || «? » «value» «? » */,
				st._254 /* V07*'validating' || «? » «value» «? » */,
				st._258 /* V08*steps-27..29 || «null» */,
				st._012 /* 1*'=>' || «? » «value» «? » */,
				st._080 /* 1*JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || «? » «value» «? » */,
				st._009 /* 1*';' || «! » «value» «?\n» */
			},
			sl._58,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING,
					ev._11),
				new EAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING,
					ev._17)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._33) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._65) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS,
					iv._0) /* AccumulatorCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._53) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS,
					iv._14) /* IteratorCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._11, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._17, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(1, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(37, MultiplicativeCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(105, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::LibIterationCS : { 'iteration' name=Name ownedSignature=TemplateSignatureCS[?] '(' ownedIterators+=IteratorCS { ',' ownedIterators+=IteratorCS }[*] { ';' ownedAccumulators+=AccumulatorCS { ',' ownedAccumulators+=AccumulatorCS }[*] }[?] { '|' ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isInvalidating='invalidating'[?] isValidating='validating'[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PostCS[*] ownedPostconditions+=PreCS[*] '}' }
		private @NonNull SerializationRule _110 = new SerializationRule(47,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._179 /* check-rule basecs::OperationCS.ownedParameters : 79 */,
				ms._202 /* check-rule basecs::TypedElementCS.ownedType : 118 */,
				ms._182 /* check-rule basecs::OperationCS.ownedPreconditions : 82 */,
				ms._181 /* check-rule basecs::OperationCS.ownedPostconditions : 83 */,
				ms._260 /* check-rule oclstdlibcs::LibIterationCS.ownedAccumulators : 1 */,
				ms._199 /* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
				ms._177 /* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
				ms._261 /* check-rule oclstdlibcs::LibIterationCS.ownedIterators : 37 */,
				ms._129 /* assign V11 = |OperationCS::ownedPostconditions| */,
				ms._128 /* assign V10 = |OperationCS::ownedPreconditions| */,
				ms._167 /* assign V9 = |ModelElementCS::ownedAnnotations| */,
				ms._165 /* assign V8 = |JavaImplementationCS::implementation| */,
				ms._164 /* assign V7 = |LibIterationCS::isValidating.'validating'| */,
				ms._159 /* assign V6 = |LibIterationCS::isInvalidating.'invalidating'| */,
				ms._055 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._107 /* assign V1 = (|LibIterationCS::ownedIterators| - 1) */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._151 /* assign V4 = (|OperationCS::ownedParameters| > 0) */,
				ms._155 /* assign V5 = (|OperationCS::ownedParameters| - 1) */,
				ms._133 /* assign V2 = (|LibIterationCS::ownedAccumulators| > 0) */,
				ms._143 /* assign V3 = (|LibIterationCS::ownedAccumulators| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._171 /* 1*steps-1..34 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'iteration' || «? » «value» «? » */,
				st._110 /* 1*NamedElementCS::name=65 || «? » «value» «? » */,
				st._188 /* V00*TemplateableElementCS::ownedSignature=105 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._092 /* 1*LibIterationCS::ownedIterators+=37 || «null» */,
				st._222 /* V01*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._092 /* 1*LibIterationCS::ownedIterators+=37 || «null» */,
				st._229 /* V02*steps-10..15 || «null» */,
				st._009 /* 1*';' || «! » «value» «?\n» */,
				st._091 /* 1*LibIterationCS::ownedAccumulators+=1 || «null» */,
				st._239 /* V03*steps-13..15 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._091 /* 1*LibIterationCS::ownedAccumulators+=1 || «null» */,
				st._246 /* V04*steps-16..21 || «null» */,
				st._052 /* 1*'|' || «? » «value» «? » */,
				st._119 /* 1*OperationCS::ownedParameters+=79 || «null» */,
				st._250 /* V05*steps-19..21 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._119 /* 1*OperationCS::ownedParameters+=79 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=118 || «null» */,
				st._251 /* V06*'invalidating' || «? » «value» «? » */,
				st._254 /* V07*'validating' || «? » «value» «? » */,
				st._258 /* V08*steps-27..29 || «null» */,
				st._012 /* 1*'=>' || «? » «value» «? » */,
				st._080 /* 1*JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || «? » «value» «? » */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._259 /* V09*ModelElementCS::ownedAnnotations+=3 || «null» */,
				st._262 /* V10*OperationCS::ownedPreconditions+=82 || «null» */,
				st._263 /* V11*OperationCS::ownedPostconditions+=83 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._59,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING,
					ev._11),
				new EAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING,
					ev._17)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._33) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._65) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._37) /* PostCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._38) /* PreCS */,
				new EReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS,
					iv._0) /* AccumulatorCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._53) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._1) /* AnnotationElementCS */,
				new EReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS,
					iv._14) /* IteratorCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_INVALIDATING,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._11, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__IS_VALIDATING,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._17, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ACCUMULATORS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(1, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_ITERATION_CS__OWNED_ITERATORS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(37, MultiplicativeCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(83, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(82, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(105, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::LibOperationCS : { isStatic='static'[?] 'operation' name=Name ownedSignature=TemplateSignatureCS[?] '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isValidating='validating'[?] isInvalidating='invalidating'[?] { 'precedence' '=' precedence=Name }[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' }
		private @NonNull SerializationRule _111 = new SerializationRule(48,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._179 /* check-rule basecs::OperationCS.ownedParameters : 79 */,
				ms._202 /* check-rule basecs::TypedElementCS.ownedType : 118 */,
				ms._199 /* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
				ms._163 /* assign V7 = |JavaImplementationCS::implementation| */,
				ms._160 /* assign V6 = |LibOperationCS::precedence| */,
				ms._156 /* assign V5 = |LibOperationCS::isInvalidating.'invalidating'| */,
				ms._153 /* assign V4 = |LibOperationCS::isValidating.'validating'| */,
				ms._055 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._123 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._081 /* assign V0 = |LibOperationCS::isStatic.'static'| */,
				ms._135 /* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				ms._144 /* assign V3 = (|OperationCS::ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._167 /* 1*steps-1..24 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._179 /* V00*'static' || «? » «value» «? » */,
				st._039 /* 1*'operation' || «? » «value» «? » */,
				st._110 /* 1*NamedElementCS::name=65 || «? » «value» «? » */,
				st._213 /* V01*TemplateableElementCS::ownedSignature=105 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._232 /* V02*steps-7..11 || «null» */,
				st._119 /* 1*OperationCS::ownedParameters+=79 || «null» */,
				st._241 /* V03*steps-9..11 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._119 /* 1*OperationCS::ownedParameters+=79 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=118 || «null» */,
				st._243 /* V04*'validating' || «? » «value» «? » */,
				st._247 /* V05*'invalidating' || «? » «value» «? » */,
				st._253 /* V06*steps-17..20 || «null» */,
				st._044 /* 1*'precedence' || «? » «value» «? » */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._093 /* 1*LibOperationCS::precedence=Name || «? » «value» «? » */,
				st._256 /* V07*steps-21..23 || «null» */,
				st._012 /* 1*'=>' || «? » «value» «? » */,
				st._083 /* 1*JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || «? » «value» «? » */,
				st._009 /* 1*';' || «! » «value» «?\n» */
			},
			sl._69,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC,
					ev._15),
				new EAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING,
					ev._11),
				new EAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING,
					ev._17)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._33) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._65) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._53) /* TemplateSignatureCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._11, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._15, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._17, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(105, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				)
			});
		// OCLstdlib::LibOperationCS : { isStatic='static'[?] 'operation' name=Name ownedSignature=TemplateSignatureCS[?] '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' ':' ownedType=TypedMultiplicityRefCS isValidating='validating'[?] isInvalidating='invalidating'[?] { 'precedence' '=' precedence=Name }[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS ';' }[*] ownedPostconditions+=PostCS[*] ownedPreconditions+=PreCS[*] '}' }
		private @NonNull SerializationRule _112 = new SerializationRule(48,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._179 /* check-rule basecs::OperationCS.ownedParameters : 79 */,
				ms._202 /* check-rule basecs::TypedElementCS.ownedType : 118 */,
				ms._180 /* check-rule basecs::OperationCS.ownedPostconditions : 82 */,
				ms._183 /* check-rule basecs::OperationCS.ownedPreconditions : 83 */,
				ms._199 /* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
				ms._177 /* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
				ms._178 /* check-rule basecs::OperationCS.ownedBodyExpressions : 99 */,
				ms._130 /* assign V11 = |OperationCS::ownedPreconditions| */,
				ms._127 /* assign V10 = |OperationCS::ownedPostconditions| */,
				ms._168 /* assign V9 = |OperationCS::ownedBodyExpressions| */,
				ms._166 /* assign V8 = |ModelElementCS::ownedAnnotations| */,
				ms._163 /* assign V7 = |JavaImplementationCS::implementation| */,
				ms._160 /* assign V6 = |LibOperationCS::precedence| */,
				ms._156 /* assign V5 = |LibOperationCS::isInvalidating.'invalidating'| */,
				ms._153 /* assign V4 = |LibOperationCS::isValidating.'validating'| */,
				ms._055 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._123 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._081 /* assign V0 = |LibOperationCS::isStatic.'static'| */,
				ms._135 /* assign V2 = (|OperationCS::ownedParameters| > 0) */,
				ms._144 /* assign V3 = (|OperationCS::ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._170 /* 1*steps-1..33 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._179 /* V00*'static' || «? » «value» «? » */,
				st._039 /* 1*'operation' || «? » «value» «? » */,
				st._110 /* 1*NamedElementCS::name=65 || «? » «value» «? » */,
				st._213 /* V01*TemplateableElementCS::ownedSignature=105 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._232 /* V02*steps-7..11 || «null» */,
				st._119 /* 1*OperationCS::ownedParameters+=79 || «null» */,
				st._241 /* V03*steps-9..11 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._119 /* 1*OperationCS::ownedParameters+=79 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=118 || «null» */,
				st._243 /* V04*'validating' || «? » «value» «? » */,
				st._247 /* V05*'invalidating' || «? » «value» «? » */,
				st._253 /* V06*steps-17..20 || «null» */,
				st._044 /* 1*'precedence' || «? » «value» «? » */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._093 /* 1*LibOperationCS::precedence=Name || «? » «value» «? » */,
				st._256 /* V07*steps-21..23 || «null» */,
				st._012 /* 1*'=>' || «? » «value» «? » */,
				st._083 /* 1*JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || «? » «value» «? » */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._257 /* V08*ModelElementCS::ownedAnnotations+=3 || «null» */,
				st._260 /* V09*steps-26..30 || «null» */,
				st._021 /* 1*'body' || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._118 /* 1*OperationCS::ownedBodyExpressions+=99 || «null» */,
				st._009 /* 1*';' || «! » «value» «?\n» */,
				st._261 /* V10*OperationCS::ownedPostconditions+=82 || «null» */,
				st._264 /* V11*OperationCS::ownedPreconditions+=83 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._70,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC,
					ev._15),
				new EAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING,
					ev._11),
				new EAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING,
					ev._17)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._33) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._65) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._37) /* PostCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._38) /* PreCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._53) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._1) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._48) /* SpecificationCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_INVALIDATING,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._11, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_STATIC,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._15, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__IS_VALIDATING,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._17, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(99, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(82, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(83, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(105, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				)
			});
		// OCLstdlib::LibOppositeCS : { 'opposite' name=Name ':' ownedType=TypedMultiplicityRefCS }
		private @NonNull SerializationRule _113 = new SerializationRule(49,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._202 /* check-rule basecs::TypedElementCS.ownedType : 118 */,
				ms._055 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._040 /* 1*'opposite' || «? » «value» «? » */,
				st._110 /* 1*NamedElementCS::name=65 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=118 || «null» */
			},
			sl._67,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._65) /* TypedMultiplicityRefCS */
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
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::LibPackageCS : { 'library' name=Name { ':' nsPrefix=Identifier '=' nsURI=URI }[?] '{' ownedPackages+=PackageCS[*] { 'precedence' ownedPrecedences+=PrecedenceCS[+] ';' }[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] '}' }
		private @NonNull SerializationRule _114 = new SerializationRule(50,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._186 /* check-rule basecs::PackageOwnerCS.ownedPackages : 78 */,
				ms._262 /* check-rule oclstdlibcs::LibPackageCS.ownedPrecedences : 84 */,
				ms._184 /* check-rule basecs::PackageCS.ownedClasses : 7 */,
				ms._177 /* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
				ms._157 /* assign V5 = |ModelElementCS::ownedAnnotations| */,
				ms._154 /* assign V4 = |PackageCS::ownedClasses| */,
				ms._121 /* assign V1 = |PackageOwnerCS::ownedPackages| */,
				ms._090 /* assign V0 = |PackageCS::nsURI| */,
				ms._043 /* assert (|PackageCS::nsPrefix| - V0) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._134 /* assign V2 = (|LibPackageCS::ownedPrecedences| > 0) */,
				ms._147 /* assign V3 = |LibPackageCS::ownedPrecedences| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._165 /* 1*steps-1..17 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._037 /* 1*'library' || «? » «value» «? » */,
				st._110 /* 1*NamedElementCS::name=65 || «? » «value» «? » */,
				st._198 /* V00*steps-4..8 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._123 /* 1*PackageCS::nsPrefix=31 || «? » «value» «? » */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._124 /* 1*PackageCS::nsURI=122 || «? » «value» «? » */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._210 /* V01*PackageOwnerCS::ownedPackages+=78 || «null» */,
				st._230 /* V02*steps-11..14 || «null» */,
				st._044 /* 1*'precedence' || «? » «value» «? » */,
				st._235 /* V03*LibPackageCS::ownedPrecedences+=84 || «null» */,
				st._009 /* 1*';' || «! » «value» «?\n» */,
				st._244 /* V04*PackageCS::ownedClasses+=7 || «½\n» «value» «½\n» */,
				st._248 /* V05*ModelElementCS::ownedAnnotations+=3 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._62,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					iv._32) /* PackageCS */,
				new EReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES,
					iv._39) /* PrecedenceCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					iv._2) /* ClassCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._1) /* AnnotationElementCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(7, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(78, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_PACKAGE_CS__OWNED_PRECEDENCES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(84, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLstdlib::LibPathElementCS : referredElement=Name
		private @NonNull SerializationRule _115 = new SerializationRule(51,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._044 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._125 /* 1*PathElementCS::referredElement=Name || «? » «value» «? » */
			},
			sl._73,
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
		// OCLstdlib::LibPathNameCS : { ownedPathElements+=LibPathElementCS { '::' ownedPathElements+=LibPathElementCS }[*] }
		private @NonNull SerializationRule _116 = new SerializationRule(52,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._189 /* check-rule basecs::PathNameCS.ownedPathElements : 51 */,
				ms._064 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || «null» */,
				st._132 /* 1*PathNameCS::ownedPathElements+=51 || «null» */,
				st._192 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'::' || «! » «value» «! » */,
				st._132 /* 1*PathNameCS::ownedPathElements+=51 || «null» */
			},
			sl._03,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._20) /* LibPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(51, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// OCLstdlib::LibPropertyCS : { isStatic='static'[?] 'property' name=Name ':' ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] ';' }
		private @NonNull SerializationRule _117 = new SerializationRule(53,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule oclstdlibcs::LibPropertyCS.ownedOpposite : 49 */,
				ms._202 /* check-rule basecs::TypedElementCS.ownedType : 118 */,
				ms._138 /* assign V2 = |JavaImplementationCS::implementation| */,
				ms._116 /* assign V1 = |LibPropertyCS::ownedOpposite| */,
				ms._055 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._082 /* assign V0 = |LibPropertyCS::isStatic.'static'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._160 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._179 /* V00*'static' || «? » «value» «? » */,
				st._045 /* 1*'property' || «? » «value» «? » */,
				st._110 /* 1*NamedElementCS::name=65 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=118 || «null» */,
				st._207 /* V01*LibPropertyCS::ownedOpposite=49 || «null» */,
				st._234 /* V02*steps-8..10 || «null» */,
				st._012 /* 1*'=>' || «? » «value» «? » */,
				st._082 /* 1*JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || «? » «value» «? » */,
				st._009 /* 1*';' || «! » «value» «?\n» */
			},
			sl._71,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC,
					ev._15)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE,
					iv._18) /* LibOppositeCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._65) /* TypedMultiplicityRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._15, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(49, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::LibPropertyCS : { isStatic='static'[?] 'property' name=Name ':' ownedType=TypedMultiplicityRefCS ownedOpposite=LibOppositeCS[?] { '=>' implementation=SINGLE_QUOTED_STRING }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
		private @NonNull SerializationRule _118 = new SerializationRule(53,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._263 /* check-rule oclstdlibcs::LibPropertyCS.ownedOpposite : 49 */,
				ms._202 /* check-rule basecs::TypedElementCS.ownedType : 118 */,
				ms._177 /* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
				ms._148 /* assign V3 = |ModelElementCS::ownedAnnotations| */,
				ms._138 /* assign V2 = |JavaImplementationCS::implementation| */,
				ms._116 /* assign V1 = |LibPropertyCS::ownedOpposite| */,
				ms._055 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._082 /* assign V0 = |LibPropertyCS::isStatic.'static'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._162 /* 1*steps-1..13 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._179 /* V00*'static' || «? » «value» «? » */,
				st._045 /* 1*'property' || «? » «value» «? » */,
				st._110 /* 1*NamedElementCS::name=65 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=118 || «null» */,
				st._207 /* V01*LibPropertyCS::ownedOpposite=49 || «null» */,
				st._234 /* V02*steps-8..10 || «null» */,
				st._012 /* 1*'=>' || «? » «value» «? » */,
				st._082 /* 1*JavaImplementationCS::implementation=SINGLE_QUOTED_STRING || «? » «value» «? » */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._236 /* V03*ModelElementCS::ownedAnnotations+=3 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._72,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC,
					ev._15)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE,
					iv._18) /* LibOppositeCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._65) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._1) /* AnnotationElementCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__IS_STATIC,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._15, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.LIB_PROPERTY_CS__OWNED_OPPOSITE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(49, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::Library : { { ownedImports+=ImportCS ';' }[*] ownedPackages+=LibPackageCS[*] }
		private @NonNull SerializationRule _119 = new SerializationRule(54,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._185 /* check-rule basecs::PackageOwnerCS.ownedPackages : 50 */,
				ms._191 /* check-rule basecs::RootCS.ownedImports : 33 */,
				ms._121 /* assign V1 = |PackageOwnerCS::ownedPackages| */,
				ms._092 /* assign V0 = |RootCS::ownedImports| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._190 /* V00*steps-2..4 || «null» */,
				st._136 /* 1*RootCS::ownedImports+=33 || «null» */,
				st._009 /* 1*';' || «! » «value» «?\n» */,
				st._209 /* V01*PackageOwnerCS::ownedPackages+=50 || «null» */
			},
			sl._32,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					iv._19) /* LibPackageCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					iv._12) /* ImportCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(33, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(50, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLstdlib::PackageCS : { 'package' name=Name { ':' nsPrefix=Identifier '=' nsURI=URI }[?] '{' ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] ownedAnnotations+=AnnotationElementCS[*] '}' }
		private @NonNull SerializationRule _120 = new SerializationRule(78,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._186 /* check-rule basecs::PackageOwnerCS.ownedPackages : 78 */,
				ms._184 /* check-rule basecs::PackageCS.ownedClasses : 7 */,
				ms._177 /* check-rule basecs::ModelElementCS.ownedAnnotations : 3 */,
				ms._148 /* assign V3 = |ModelElementCS::ownedAnnotations| */,
				ms._141 /* assign V2 = |PackageCS::ownedClasses| */,
				ms._121 /* assign V1 = |PackageOwnerCS::ownedPackages| */,
				ms._090 /* assign V0 = |PackageCS::nsURI| */,
				ms._043 /* assert (|PackageCS::nsPrefix| - V0) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._162 /* 1*steps-1..13 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._041 /* 1*'package' || «? » «value» «? » */,
				st._110 /* 1*NamedElementCS::name=65 || «? » «value» «? » */,
				st._198 /* V00*steps-4..8 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._123 /* 1*PackageCS::nsPrefix=31 || «? » «value» «? » */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._124 /* 1*PackageCS::nsURI=122 || «? » «value» «? » */,
				st._051 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._210 /* V01*PackageOwnerCS::ownedPackages+=78 || «null» */,
				st._227 /* V02*PackageCS::ownedClasses+=7 || «½\n» «value» «½\n» */,
				st._236 /* V03*ModelElementCS::ownedAnnotations+=3 || «null» */,
				st._054 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._63,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					iv._32) /* PackageCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					iv._2) /* ClassCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._1) /* AnnotationElementCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(7, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(78, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLstdlib::ParameterCS : { name=Identifier ':' ownedType=TypedMultiplicityRefCS }
		private @NonNull SerializationRule _121 = new SerializationRule(79,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._202 /* check-rule basecs::TypedElementCS.ownedType : 118 */,
				ms._055 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._106 /* 1*NamedElementCS::name=31 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=118 || «null» */
			},
			sl._57,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._65) /* TypedMultiplicityRefCS */
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
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::PostCS : { stereotype='post' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS ';' }
		private @NonNull SerializationRule _122 = new SerializationRule(82,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._171 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 99 */,
				ms._172 /* check-rule basecs::ConstraintCS.ownedSpecification : 99 */,
				ms._006 /* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				ms._086 /* assign V0 = |NamedElementCS::name| */,
				ms._008 /* assert (|ConstraintCS::stereotype.'post'| - 1) == 0 */,
				ms._114 /* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._160 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._042 /* 1*'post' || «? » «value» «? » */,
				st._195 /* V00*steps-3..8 || «null» */,
				st._105 /* 1*NamedElementCS::name=128 || «? » «value» «? » */,
				st._218 /* V01*steps-5..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._068 /* 1*ConstraintCS::ownedMessageSpecification=99 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._069 /* 1*ConstraintCS::ownedSpecification=99 || «null» */,
				st._009 /* 1*';' || «! » «value» «?\n» */
			},
			sl._52,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._12)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._48) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._48) /* SpecificationCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._12, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(99, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(99, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::PreCS : { stereotype='pre' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS ';' }
		private @NonNull SerializationRule _123 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._171 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 99 */,
				ms._172 /* check-rule basecs::ConstraintCS.ownedSpecification : 99 */,
				ms._006 /* assert (|ConstraintCS::ownedSpecification| - 1) == 0 */,
				ms._086 /* assign V0 = |NamedElementCS::name| */,
				ms._009 /* assert (|ConstraintCS::stereotype.'pre'| - 1) == 0 */,
				ms._114 /* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._160 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._043 /* 1*'pre' || «? » «value» «? » */,
				st._195 /* V00*steps-3..8 || «null» */,
				st._105 /* 1*NamedElementCS::name=128 || «? » «value» «? » */,
				st._218 /* V01*steps-5..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._068 /* 1*ConstraintCS::ownedMessageSpecification=99 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._069 /* 1*ConstraintCS::ownedSpecification=99 || «null» */,
				st._009 /* 1*';' || «! » «value» «?\n» */
			},
			sl._52,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._13)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._48) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._48) /* SpecificationCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._13, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(99, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(99, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::PrecedenceCS : { 'left' ':' name=Name }
		private @NonNull SerializationRule _124 = new SerializationRule(84,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._035 /* 1*'left' || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._110 /* 1*NamedElementCS::name=65 || «? » «value» «? » */
			},
			sl._66,
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
				)
			},
			null);
		// OCLstdlib::PrecedenceCS : { isRightAssociative='right' ':' name=Name }
		private @NonNull SerializationRule _125 = new SerializationRule(84,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._047 /* assert (|PrecedenceCS::isRightAssociative.'right'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._046 /* 1*'right' || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._110 /* 1*NamedElementCS::name=65 || «? » «value» «? » */
			},
			sl._66,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(OCLstdlibCSPackage.Literals.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE,
					ev._14)
			},
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(OCLstdlibCSPackage.Literals.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._14, MultiplicativeCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// OCLstdlib::SpecificationCS : ownedExpression=ExpCS
		private @NonNull SerializationRule _126 = new SerializationRule(99,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._222 /* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 27 */,
				ms._011 /* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._072 /* 1*ExpSpecificationCS::ownedExpression=27 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
					new RuleIndex_MultiplicativeCardinality(27, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::TuplePartCS : { name=Identifier ':' ownedType=TypedMultiplicityRefCS }
		private @NonNull SerializationRule _127 = new SerializationRule(108,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._202 /* check-rule basecs::TypedElementCS.ownedType : 118 */,
				ms._055 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._032 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._172 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._106 /* 1*NamedElementCS::name=31 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._153 /* 1*TypedElementCS::ownedType=118 || «null» */
			},
			sl._57,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._65) /* TypedMultiplicityRefCS */
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
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ONE)
					}
				)
			});
	}
	private class _SerializationRules2
	{
		// OCLstdlib::TypedMultiplicityRefCS : { name='Lambda' ownedSignature=TemplateSignatureCS[?] ownedContextType=LambdaContextTypeRefCS '(' { ownedParameterTypes+=TypedMultiplicityRefCS { ',' ownedParameterTypes+=TypedMultiplicityRefCS }[*] }[?] ')' ':' ownedResultType=TypedRefCS ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _128 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._174 /* check-rule basecs::LambdaTypeCS.ownedContextType : 40 */,
				ms._199 /* check-rule basecs::TemplateableElementCS.ownedSignature : 105 */,
				ms._176 /* check-rule basecs::LambdaTypeCS.ownedResultType : 119 */,
				ms._175 /* check-rule basecs::LambdaTypeCS.ownedParameterTypes : 118 */,
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._150 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._022 /* assert (|LambdaTypeCS::ownedResultType| - 1) == 0 */,
				ms._021 /* assert (|LambdaTypeCS::ownedContextType| - 1) == 0 */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._020 /* assert (|LambdaTypeCS::name.'Lambda'| - 1) == 0 */,
				ms._106 /* assign V1 = (|LambdaTypeCS::ownedParameterTypes| > 0) */,
				ms._132 /* assign V2 = (|LambdaTypeCS::ownedParameterTypes| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._163 /* 1*steps-1..14 || «null» */,
				st._015 /* 1*'Lambda' || «? » «value» «? » */,
				st._188 /* V00*TemplateableElementCS::ownedSignature=105 || «null» */,
				st._085 /* 1*LambdaTypeCS::ownedContextType=40 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._220 /* V01*steps-6..10 || «null» */,
				st._086 /* 1*LambdaTypeCS::ownedParameterTypes+=118 || «null» */,
				st._234 /* V02*steps-8..10 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._086 /* 1*LambdaTypeCS::ownedParameterTypes+=118 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._087 /* 1*LambdaTypeCS::ownedResultType=119 || «null» */,
				st._238 /* V03*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._18,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE,
					iv._15) /* LambdaContextTypeRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._53) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE,
					iv._66) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES,
					iv._65) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._05, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(118, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(119, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(105, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLstdlib::TypedMultiplicityRefCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _129 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._200 /* check-rule basecs::TupleTypeCS.ownedParts : 108 */,
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._150 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._052 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._070 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._110 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._136 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._160 /* 1*steps-1..11 || «null» */,
				st._017 /* 1*'Tuple' || «? » «value» «? » */,
				st._191 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._219 /* V01*steps-5..9 || «null» */,
				st._147 /* 1*TupleTypeCS::ownedParts+=108 || «null» */,
				st._233 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._147 /* 1*TupleTypeCS::ownedParts+=108 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._238 /* V03*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._20,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._07)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._55) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._07, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(108, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLstdlib::TypedMultiplicityRefCS : { isTypeof='typeof' '(' ownedPathName=LibPathNameCS ')' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _130 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._205 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 52 */,
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._097 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._057 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */,
				ms._056 /* assert (|TypedTypeRefCS::isTypeof.'typeof'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._174 /* 1*steps-1..6 || «null» */,
				st._050 /* 1*'typeof' || «? » «value» «? » */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._155 /* 1*TypedTypeRefCS::ownedPathName=52 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._189 /* V00*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._28,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF,
					ev._16)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._21) /* LibPathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._16, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(52, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::TypedMultiplicityRefCS : { ownedPathName=LibPathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _131 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._205 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 52 */,
				ms._204 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 103 */,
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._125 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._098 /* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				ms._057 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._175 /* 1*steps-1..7 || «null» */,
				st._155 /* 1*TypedTypeRefCS::ownedPathName=52 || «null» */,
				st._193 /* V00*steps-3..6 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._154 /* 1*TypedTypeRefCS::ownedBinding=103 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._214 /* V01*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._05,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._21) /* LibPathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._51) /* TemplateBindingCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(103, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(52, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::TypedMultiplicityRefCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _132 = new SerializationRule(118,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._239 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 110 */,
				ms._238 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 110 */,
				ms._203 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 62 */,
				ms._125 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._083 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._028 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._027 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._177 /* 1*steps-1..9 || «null» */,
				st._016 /* 1*'Map' || «? » «value» «? » */,
				st._195 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._098 /* 1*MapTypeCS::ownedKeyType=110 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._099 /* 1*MapTypeCS::ownedValueType=110 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._214 /* V01*TypedRefCS::ownedMultiplicity=62 || «null» */
			},
			sl._24,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._06)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._56) /* TypeExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._25) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._06, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(110, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLstdlib::TypedTypeRefCS : { isTypeof='typeof' '(' ownedPathName=LibPathNameCS ')' }
		private @NonNull SerializationRule _133 = new SerializationRule(120,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._205 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 52 */,
				ms._057 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */,
				ms._056 /* assert (|TypedTypeRefCS::isTypeof.'typeof'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._173 /* 1*steps-1..5 || «null» */,
				st._050 /* 1*'typeof' || «? » «value» «? » */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._155 /* 1*TypedTypeRefCS::ownedPathName=52 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._27,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF,
					ev._16)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._21) /* LibPathNameCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._16, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(52, MultiplicativeCardinality.ONE)
					}
				)
			});
		// OCLstdlib::TypedTypeRefCS : { ownedPathName=LibPathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] }
		private @NonNull SerializationRule _134 = new SerializationRule(120,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._205 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 52 */,
				ms._204 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 103 */,
				ms._098 /* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				ms._057 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._174 /* 1*steps-1..6 || «null» */,
				st._155 /* 1*TypedTypeRefCS::ownedPathName=52 || «null» */,
				st._193 /* V00*steps-3..6 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._154 /* 1*TypedTypeRefCS::ownedBinding=103 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._04,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._21) /* LibPathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._51) /* TemplateBindingCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(103, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(52, MultiplicativeCardinality.ONE)
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
//	import RTSerializationAssignsStep;
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
//	import OCLstdlibCSPackage;
