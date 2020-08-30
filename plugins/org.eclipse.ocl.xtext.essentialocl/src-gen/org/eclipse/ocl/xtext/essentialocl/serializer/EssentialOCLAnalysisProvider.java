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
package org.eclipse.ocl.xtext.essentialocl.serializer;

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

public class EssentialOCLAnalysisProvider extends AbstractAnalysisProvider
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
					ec._01  /* essentialoclcs::CollectionLiteralExpCS */,
					ec._02  /* essentialoclcs::CollectionLiteralPartCS */,
					ec._03  /* essentialoclcs::CollectionPatternCS */,
					ec._04  /* essentialoclcs::CollectionTypeCS */,
					ec._05  /* essentialoclcs::ContextCS */,
					ec._06  /* essentialoclcs::CurlyBracketedClauseCS */,
					ec._07  /* essentialoclcs::ExpCS */,
					ec._08  /* essentialoclcs::IfExpCS */,
					ec._09  /* essentialoclcs::IfThenExpCS */,
					ec._10  /* essentialoclcs::InfixExpCS */,
					ec._11  /* essentialoclcs::InvalidLiteralExpCS */,
					ec._12  /* essentialoclcs::LambdaLiteralExpCS */,
					ec._13  /* essentialoclcs::LetExpCS */,
					ec._14  /* essentialoclcs::LetVariableCS */,
					ec._15  /* essentialoclcs::MapLiteralExpCS */,
					ec._16  /* essentialoclcs::MapLiteralPartCS */,
					ec._17  /* essentialoclcs::MapTypeCS */,
					ec._18  /* basecs::MultiplicityBoundsCS */,
					ec._19  /* basecs::MultiplicityStringCS */,
					ec._20  /* essentialoclcs::NameExpCS */,
					ec._21  /* essentialoclcs::NavigatingArgCS */,
					ec._22  /* essentialoclcs::NestedExpCS */,
					ec._23  /* essentialoclcs::NullLiteralExpCS */,
					ec._24  /* essentialoclcs::NumberLiteralExpCS */,
					ec._25  /* basecs::PathElementCS */,
					ec._26  /* basecs::PathElementWithURICS */,
					ec._27  /* basecs::PathNameCS */,
					ec._28  /* essentialoclcs::PatternExpCS */,
					ec._29  /* essentialoclcs::PrefixExpCS */,
					ec._30  /* basecs::PrimitiveTypeRefCS */,
					ec._31  /* essentialoclcs::RoundBracketedClauseCS */,
					ec._32  /* essentialoclcs::SelfExpCS */,
					ec._33  /* essentialoclcs::ShadowPartCS */,
					ec._34  /* essentialoclcs::SquareBracketedClauseCS */,
					ec._35  /* essentialoclcs::StringLiteralExpCS */,
					ec._36  /* basecs::TemplateBindingCS */,
					ec._37  /* basecs::TemplateParameterSubstitutionCS */,
					ec._38  /* basecs::TemplateSignatureCS */,
					ec._39  /* essentialoclcs::TupleLiteralExpCS */,
					ec._40  /* essentialoclcs::TupleLiteralPartCS */,
					ec._41  /* basecs::TuplePartCS */,
					ec._42  /* basecs::TupleTypeCS */,
					ec._43  /* essentialoclcs::TypeLiteralExpCS */,
					ec._44  /* essentialoclcs::TypeNameExpCS */,
					ec._45  /* basecs::TypeParameterCS */,
					ec._46  /* basecs::TypedTypeRefCS */,
					ec._47  /* essentialoclcs::UnlimitedNaturalLiteralExpCS */,
					ec._48  /* essentialoclcs::VariableCS */,
					ec._49  /* basecs::WildcardTypeRefCS */
				},
				/**
				 *	The indexable per-grammar rule meta data.
				 */
				new AbstractRuleValue [] {
					gr._00  /* 0 : ANY_OTHER */,
					gr._01  /* 1 : BinaryOperatorName */,
					gr._02  /* 2 : BooleanLiteralExpCS */,
					gr._03  /* 3 : CoIteratorVariableCS */,
					gr._04  /* 4 : CollectionLiteralExpCS */,
					gr._05  /* 5 : CollectionLiteralPartCS */,
					gr._06  /* 6 : CollectionPatternCS */,
					gr._07  /* 7 : CollectionTypeCS */,
					gr._08  /* 8 : CollectionTypeIdentifier */,
					gr._09  /* 9 : CurlyBracketedClauseCS */,
					gr._10  /* 10 : DOUBLE_QUOTED_STRING */,
					gr._11  /* 11 : ESCAPED_CHARACTER */,
					gr._12  /* 12 : ESCAPED_ID */,
					gr._13  /* 13 : ElseIfThenExpCS */,
					gr._14  /* 14 : EssentialOCLInfixOperatorName */,
					gr._15  /* 15 : EssentialOCLNavigationOperatorName */,
					gr._16  /* 16 : EssentialOCLReservedKeyword */,
					gr._17  /* 17 : EssentialOCLUnaryOperatorName */,
					gr._18  /* 18 : EssentialOCLUnreservedName */,
					gr._19  /* 19 : EssentialOCLUnrestrictedName */,
					gr._20  /* 20 : ExpCS */,
					gr._21  /* 21 : FirstPathElementCS */,
					gr._22  /* 22 : ID */,
					gr._23  /* 23 : INT */,
					gr._24  /* 24 : Identifier */,
					gr._25  /* 25 : IfExpCS */,
					gr._26  /* 26 : InfixOperatorName */,
					gr._27  /* 27 : InvalidLiteralExpCS */,
					gr._28  /* 28 : LETTER_CHARACTER */,
					gr._29  /* 29 : LOWER */,
					gr._30  /* 30 : LambdaLiteralExpCS */,
					gr._31  /* 31 : LetExpCS */,
					gr._32  /* 32 : LetVariableCS */,
					gr._33  /* 33 : ML_COMMENT */,
					gr._34  /* 34 : ML_SINGLE_QUOTED_STRING */,
					gr._35  /* 35 : MapLiteralExpCS */,
					gr._36  /* 36 : MapLiteralPartCS */,
					gr._37  /* 37 : MapTypeCS */,
					gr._38  /* 38 : Model */,
					gr._39  /* 39 : MultiplicityBoundsCS */,
					gr._40  /* 40 : MultiplicityCS */,
					gr._41  /* 41 : MultiplicityStringCS */,
					gr._42  /* 42 : NUMBER_LITERAL */,
					gr._43  /* 43 : NameExpCS */,
					gr._44  /* 44 : NavigatingArgCS */,
					gr._45  /* 45 : NavigatingArgExpCS */,
					gr._46  /* 46 : NavigatingBarArgCS */,
					gr._47  /* 47 : NavigatingCommaArgCS */,
					gr._48  /* 48 : NavigatingSemiArgCS */,
					gr._49  /* 49 : NavigationOperatorName */,
					gr._50  /* 50 : NestedExpCS */,
					gr._51  /* 51 : NextPathElementCS */,
					gr._52  /* 52 : NullLiteralExpCS */,
					gr._53  /* 53 : NumberLiteralExpCS */,
					gr._54  /* 54 : PathNameCS */,
					gr._55  /* 55 : PatternExpCS */,
					gr._56  /* 56 : PrefixedLetExpCS */,
					gr._57  /* 57 : PrefixedPrimaryExpCS */,
					gr._58  /* 58 : PrimaryExpCS */,
					gr._59  /* 59 : PrimitiveLiteralExpCS */,
					gr._60  /* 60 : PrimitiveTypeCS */,
					gr._61  /* 61 : PrimitiveTypeIdentifier */,
					gr._62  /* 62 : RoundBracketedClauseCS */,
					gr._63  /* 63 : SIMPLE_ID */,
					gr._64  /* 64 : SINGLE_QUOTED_STRING */,
					gr._65  /* 65 : SL_COMMENT */,
					gr._66  /* 66 : SelfExpCS */,
					gr._67  /* 67 : ShadowPartCS */,
					gr._68  /* 68 : SimplePathNameCS */,
					gr._69  /* 69 : SquareBracketedClauseCS */,
					gr._70  /* 70 : StringLiteral */,
					gr._71  /* 71 : StringLiteralExpCS */,
					gr._72  /* 72 : TemplateBindingCS */,
					gr._73  /* 73 : TemplateParameterSubstitutionCS */,
					gr._74  /* 74 : TemplateSignatureCS */,
					gr._75  /* 75 : TupleLiteralExpCS */,
					gr._76  /* 76 : TupleLiteralPartCS */,
					gr._77  /* 77 : TuplePartCS */,
					gr._78  /* 78 : TupleTypeCS */,
					gr._79  /* 79 : TypeExpCS */,
					gr._80  /* 80 : TypeExpWithoutMultiplicityCS */,
					gr._81  /* 81 : TypeLiteralCS */,
					gr._82  /* 82 : TypeLiteralExpCS */,
					gr._83  /* 83 : TypeLiteralWithMultiplicityCS */,
					gr._84  /* 84 : TypeNameExpCS */,
					gr._85  /* 85 : TypeParameterCS */,
					gr._86  /* 86 : TypeRefCS */,
					gr._87  /* 87 : TypedRefCS */,
					gr._88  /* 88 : TypedTypeRefCS */,
					gr._89  /* 89 : UPPER */,
					gr._90  /* 90 : URI */,
					gr._91  /* 91 : URIFirstPathElementCS */,
					gr._92  /* 92 : URIPathNameCS */,
					gr._93  /* 93 : UnaryOperatorName */,
					gr._94  /* 94 : UnlimitedNaturalLiteralExpCS */,
					gr._95  /* 95 : UnreservedName */,
					gr._96  /* 96 : UnrestrictedName */,
					gr._97  /* 97 : WS */,
					gr._98  /* 98 : WildcardTypeRefCS */
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
			= new IndexVector(0x8L);
		private final @NonNull IndexVector _1 // CollectionLiteralPartCS
			= new IndexVector(0x20L);
		private final @NonNull IndexVector _2 // CollectionTypeCS
			= new IndexVector(0x80L);
		private final @NonNull IndexVector _3 // CurlyBracketedClauseCS
			= new IndexVector(0x200L);
		private final @NonNull IndexVector _4 // ElseIfThenExpCS
			= new IndexVector(0x2000L);
		private final @NonNull IndexVector _5 // ExpCS
			= new IndexVector(0x100000L);
		private final @NonNull IndexVector _6 // FirstPathElementCS
			= new IndexVector(0x200000L);
		private final @NonNull IndexVector _7 // LetVariableCS
			= new IndexVector(0x100000000L);
		private final @NonNull IndexVector _8 // MapLiteralPartCS
			= new IndexVector(0x1000000000L);
		private final @NonNull IndexVector _9 // MapTypeCS
			= new IndexVector(0x2000000000L);
		private final @NonNull IndexVector _10 // MultiplicityCS
			= new IndexVector(0x10000000000L);
		private final @NonNull IndexVector _11 // NavigatingArgExpCS
			= new IndexVector(0x200000000000L);
		private final @NonNull IndexVector _12 // NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
			= new IndexVector(0x1d00000000000L);
		private final @NonNull IndexVector _13 // FirstPathElementCS|NextPathElementCS
			= new IndexVector(0x8000000200000L);
		private final @NonNull IndexVector _14 // PathNameCS
			= new IndexVector(0x40000000000000L);
		private final @NonNull IndexVector _15 // PatternExpCS
			= new IndexVector(0x80000000000000L);
		private final @NonNull IndexVector _16 // ExpCS|PatternExpCS
			= new IndexVector(0x80000000100000L);
		private final @NonNull IndexVector _17 // PrefixedLetExpCS
			= new IndexVector(0x100000000000000L);
		private final @NonNull IndexVector _18 // LetExpCS|PrefixedLetExpCS
			= new IndexVector(0x100000080000000L);
		private final @NonNull IndexVector _19 // PrefixedPrimaryExpCS
			= new IndexVector(0x200000000000000L);
		private final @NonNull IndexVector _20 // RoundBracketedClauseCS
			= new IndexVector(0x4000000000000000L);
		private final @NonNull IndexVector _21 // ShadowPartCS
			= new IndexVector(0x0L,0x8L);
		private final @NonNull IndexVector _22 // SquareBracketedClauseCS
			= new IndexVector(0x0L,0x20L);
		private final @NonNull IndexVector _23 // StringLiteralExpCS
			= new IndexVector(0x0L,0x80L);
		private final @NonNull IndexVector _24 // TemplateBindingCS
			= new IndexVector(0x0L,0x100L);
		private final @NonNull IndexVector _25 // TemplateParameterSubstitutionCS
			= new IndexVector(0x0L,0x200L);
		private final @NonNull IndexVector _26 // TupleLiteralPartCS
			= new IndexVector(0x0L,0x1000L);
		private final @NonNull IndexVector _27 // TuplePartCS
			= new IndexVector(0x0L,0x2000L);
		private final @NonNull IndexVector _28 // TypeExpCS
			= new IndexVector(0x0L,0x8000L);
		private final @NonNull IndexVector _29 // TypeExpWithoutMultiplicityCS
			= new IndexVector(0x0L,0x10000L);
		private final @NonNull IndexVector _30 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
			= new IndexVector(0x1000002000000080L,0x24000L);
		private final @NonNull IndexVector _31 // TypeLiteralWithMultiplicityCS
			= new IndexVector(0x0L,0x80000L);
		private final @NonNull IndexVector _32 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS
			= new IndexVector(0x1000002000000080L,0xa4000L);
		private final @NonNull IndexVector _33 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new IndexVector(0x10000020000000c0L,0x134000L);
		private final @NonNull IndexVector _34 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new IndexVector(0x10000020000000c0L,0x13c000L);
		private final @NonNull IndexVector _35 // TypeParameterCS
			= new IndexVector(0x0L,0x200000L);
		private final @NonNull IndexVector _36 // TypeRefCS
			= new IndexVector(0x0L,0x400000L);
		private final @NonNull IndexVector _37 // TypedRefCS
			= new IndexVector(0x0L,0x800000L);
		private final @NonNull IndexVector _38 // TypedRefCS|TypedTypeRefCS
			= new IndexVector(0x0L,0x1800000L);
		private final @NonNull IndexVector _39 // NextPathElementCS|URIFirstPathElementCS
			= new IndexVector(0x8000000000000L,0x8000000L);
		private final @NonNull IndexVector _40 // FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
			= new IndexVector(0x8000000200000L,0x8000000L);
		private final @NonNull IndexVector _41 // BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0x830000008000004L,0x40000080L);
		private final @NonNull IndexVector _42 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0xc3408084a000014L,0x40040884L);
		private final @NonNull IndexVector _43 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0xe3408084a000014L,0x40040884L);
		private final @NonNull IndexVector _44 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0xf340808ca000014L,0x40040884L);
		private final @NonNull IndexVector _45 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0xf340808ca100014L,0x40040884L);
		private final @NonNull IndexVector _46 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0xf342808ca100014L,0x40040884L);
		private final @NonNull IndexVector _47 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new IndexVector(0xfb40808ca100014L,0x40040884L);
		private final @NonNull IndexVector _48 // TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
			= new IndexVector(0x0L,0x401c00000L);
	}

	/**
	 * String combinations used by assigned String EAttributes
	 */
	private class _EnumValues
	{
		private final @NonNull EnumerationValue _0 // '*|+|?'
			= new MultipleEnumerationValue(new @NonNull String[]{"*", "+", "?"});
		private final @NonNull EnumerationValue _1 // ','
			= new SingleEnumerationValue(",");
		private final @NonNull EnumerationValue _2 // ';'
			= new SingleEnumerationValue(";");
		private final @NonNull EnumerationValue _3 // '@'
			= new SingleEnumerationValue("@");
		private final @NonNull EnumerationValue _4 // 'Map'
			= new SingleEnumerationValue("Map");
		private final @NonNull EnumerationValue _5 // 'Tuple'
			= new SingleEnumerationValue("Tuple");
		private final @NonNull EnumerationValue _6 // 'false|true'
			= new MultipleEnumerationValue(new @NonNull String[]{"false", "true"});
		private final @NonNull EnumerationValue _7 // '|'
			= new SingleEnumerationValue("|");
		private final @NonNull EnumerationValue _8 // '|1'
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
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, ev._3);
		private final @NonNull CardinalitySolution _004 // |AbstractNameExpCS::ownedCurlyBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _005 // |AbstractNameExpCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _006 // |AbstractNameExpCS::ownedRoundBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _007 // |AbstractNameExpCS::ownedSquareBracketedClauses|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		private final @NonNull CardinalitySolution _008 // |BooleanLiteralExpCS::symbol.'false|true'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._6);
		private final @NonNull CardinalitySolution _009 // |CollectionLiteralExpCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _010 // |CollectionLiteralExpCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _011 // |CollectionLiteralPartCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _012 // |CollectionLiteralPartCS::ownedLastExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		private final @NonNull CardinalitySolution _013 // |CollectionPatternCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _014 // |CollectionPatternCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _015 // |CollectionPatternCS::restVariableName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		private final @NonNull CardinalitySolution _016 // |CollectionTypeCS::name|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		private final @NonNull CardinalitySolution _017 // |CollectionTypeCS::ownedCollectionMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		private final @NonNull CardinalitySolution _018 // |CollectionTypeCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _019 // |ContextCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _020 // |CurlyBracketedClauseCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _021 // |IfExpCS::ownedCondition|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		private final @NonNull CardinalitySolution _022 // |IfExpCS::ownedElseExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		private final @NonNull CardinalitySolution _023 // |IfExpCS::ownedIfThenExpressions|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		private final @NonNull CardinalitySolution _024 // |IfExpCS::ownedThenExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull CardinalitySolution _025 // |IfThenExpCS::ownedCondition|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		private final @NonNull CardinalitySolution _026 // |IfThenExpCS::ownedThenExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull CardinalitySolution _027 // |InfixExpCS::ownedLeft|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		private final @NonNull CardinalitySolution _028 // |LambdaLiteralExpCS::ownedExpressionCS|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		private final @NonNull CardinalitySolution _029 // |LetExpCS::ownedInExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		private final @NonNull CardinalitySolution _030 // |LetExpCS::ownedVariables|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		private final @NonNull CardinalitySolution _031 // |LetVariableCS::ownedRoundBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _032 // |MapLiteralExpCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _033 // |MapLiteralExpCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _034 // |MapLiteralPartCS::ownedKey|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		private final @NonNull CardinalitySolution _035 // |MapLiteralPartCS::ownedValue|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		private final @NonNull CardinalitySolution _036 // |MapTypeCS::name.'Map'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, ev._4);
		private final @NonNull CardinalitySolution _037 // |MapTypeCS::ownedKeyType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		private final @NonNull CardinalitySolution _038 // |MapTypeCS::ownedValueType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		private final @NonNull CardinalitySolution _039 // |MultiplicityBoundsCS::lowerBound|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull CardinalitySolution _040 // |MultiplicityBoundsCS::upperBound|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull CardinalitySolution _041 // |MultiplicityCS::isNullFree.'|1'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, ev._8);
		private final @NonNull CardinalitySolution _042 // |MultiplicityStringCS::stringBounds.'*|+|?'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._0);
		private final @NonNull CardinalitySolution _043 // |NamedElementCS::name|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull CardinalitySolution _044 // |NavigatingArgCS::ownedCoIterator|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		private final @NonNull CardinalitySolution _045 // |NavigatingArgCS::ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _046 // |NavigatingArgCS::ownedNameExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		private final @NonNull CardinalitySolution _047 // |NavigatingArgCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _048 // |NavigatingArgCS::prefix.','|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._1);
		private final @NonNull CardinalitySolution _049 // |NavigatingArgCS::prefix.';'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._2);
		private final @NonNull CardinalitySolution _050 // |NavigatingArgCS::prefix.'|'|
			= new EAttributeSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._7);
		private final @NonNull CardinalitySolution _051 // |NestedExpCS::ownedExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		private final @NonNull CardinalitySolution _052 // |NumberLiteralExpCS::symbol|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull CardinalitySolution _053 // |OperatorExpCS::ownedRight|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull CardinalitySolution _054 // |PathElementCS::referredElement|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private final @NonNull CardinalitySolution _055 // |PathNameCS::ownedPathElements|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull CardinalitySolution _056 // |PatternExpCS::ownedPatternType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		private final @NonNull CardinalitySolution _057 // |PatternExpCS::patternVariableName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		private final @NonNull CardinalitySolution _058 // |PrimitiveTypeRefCS::name|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		private final @NonNull CardinalitySolution _059 // |RoundBracketedClauseCS::ownedArguments|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		private final @NonNull CardinalitySolution _060 // |ShadowPartCS::ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _061 // |ShadowPartCS::referredProperty|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		private final @NonNull CardinalitySolution _062 // |SquareBracketedClauseCS::ownedTerms|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		private final @NonNull CardinalitySolution _063 // |StringLiteralExpCS::segments|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		private final @NonNull CardinalitySolution _064 // |TemplateBindingCS::ownedMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull CardinalitySolution _065 // |TemplateBindingCS::ownedSubstitutions|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull CardinalitySolution _066 // |TemplateParameterSubstitutionCS::ownedActualParameter|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull CardinalitySolution _067 // |TemplateSignatureCS::ownedParameters|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull CardinalitySolution _068 // |TupleLiteralExpCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _069 // |TupleTypeCS::name.'Tuple'|
			= new EAttributeSizeCardinalitySolution(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, ev._5);
		private final @NonNull CardinalitySolution _070 // |TupleTypeCS::ownedParts|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		private final @NonNull CardinalitySolution _071 // |TypeLiteralExpCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _072 // |TypeNameExpCS::ownedCurlyBracketedClause|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull CardinalitySolution _073 // |TypeNameExpCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _074 // |TypeNameExpCS::ownedPatternGuard|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		private final @NonNull CardinalitySolution _075 // |TypeParameterCS::ownedExtends|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull CardinalitySolution _076 // |TypedElementCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _077 // |TypedRefCS::ownedMultiplicity|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull CardinalitySolution _078 // |TypedTypeRefCS::ownedBinding|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull CardinalitySolution _079 // |TypedTypeRefCS::ownedPathName|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull CardinalitySolution _080 // |VariableCS::ownedInitExpression|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull CardinalitySolution _081 // |VariableCS::ownedType|
			= new EStructuralFeatureSizeCardinalitySolution(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		private final @NonNull CardinalitySolution _082 // |WildcardTypeRefCS::ownedExtends|
			= new EStructuralFeatureSizeCardinalitySolution(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull CardinalitySolution _083 // (|AbstractNameExpCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_005, _001);
		private final @NonNull CardinalitySolution _084 // (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
			= new SubtractCardinalitySolution(_008, _001);
		private final @NonNull CardinalitySolution _085 // (|CollectionLiteralExpCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_009, _001);
		private final @NonNull CardinalitySolution _086 // (|CollectionLiteralExpCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_009, _000);
		private final @NonNull CardinalitySolution _087 // (|CollectionLiteralExpCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_010, _001);
		private final @NonNull CardinalitySolution _088 // (|CollectionLiteralPartCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_011, _001);
		private final @NonNull CardinalitySolution _089 // (|CollectionPatternCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_013, _001);
		private final @NonNull CardinalitySolution _090 // (|CollectionPatternCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_014, _001);
		private final @NonNull CardinalitySolution _091 // (|CollectionTypeCS::name| - 1)
			= new SubtractCardinalitySolution(_016, _001);
		private final @NonNull CardinalitySolution _092 // (|ContextCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_019, _001);
		private final @NonNull CardinalitySolution _093 // (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_020, _001);
		private final @NonNull CardinalitySolution _094 // (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_020, _000);
		private final @NonNull CardinalitySolution _095 // (|IfExpCS::ownedCondition| - 1)
			= new SubtractCardinalitySolution(_021, _001);
		private final @NonNull CardinalitySolution _096 // (|IfExpCS::ownedElseExpression| - 1)
			= new SubtractCardinalitySolution(_022, _001);
		private final @NonNull CardinalitySolution _097 // (|IfExpCS::ownedThenExpression| - 1)
			= new SubtractCardinalitySolution(_024, _001);
		private final @NonNull CardinalitySolution _098 // (|IfThenExpCS::ownedCondition| - 1)
			= new SubtractCardinalitySolution(_025, _001);
		private final @NonNull CardinalitySolution _099 // (|IfThenExpCS::ownedThenExpression| - 1)
			= new SubtractCardinalitySolution(_026, _001);
		private final @NonNull CardinalitySolution _100 // (|InfixExpCS::ownedLeft| - 1)
			= new SubtractCardinalitySolution(_027, _001);
		private final @NonNull CardinalitySolution _101 // (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
			= new SubtractCardinalitySolution(_028, _001);
		private final @NonNull CardinalitySolution _102 // (|LetExpCS::ownedInExpression| - 1)
			= new SubtractCardinalitySolution(_029, _001);
		private final @NonNull CardinalitySolution _103 // (|LetExpCS::ownedVariables| - 1)
			= new SubtractCardinalitySolution(_030, _001);
		private final @NonNull CardinalitySolution _104 // (|MapLiteralExpCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_032, _001);
		private final @NonNull CardinalitySolution _105 // (|MapLiteralExpCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_032, _000);
		private final @NonNull CardinalitySolution _106 // (|MapLiteralExpCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_033, _001);
		private final @NonNull CardinalitySolution _107 // (|MapLiteralPartCS::ownedKey| - 1)
			= new SubtractCardinalitySolution(_034, _001);
		private final @NonNull CardinalitySolution _108 // (|MapLiteralPartCS::ownedValue| - 1)
			= new SubtractCardinalitySolution(_035, _001);
		private final @NonNull CardinalitySolution _109 // (|MapTypeCS::name.'Map'| - 1)
			= new SubtractCardinalitySolution(_036, _001);
		private final @NonNull CardinalitySolution _110 // (|MapTypeCS::ownedKeyType| - V0)
			= new SubtractCardinalitySolution(_037, _002);
		private final @NonNull CardinalitySolution _111 // (|MultiplicityBoundsCS::lowerBound| - 1)
			= new SubtractCardinalitySolution(_039, _001);
		private final @NonNull CardinalitySolution _112 // (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
			= new SubtractCardinalitySolution(_042, _001);
		private final @NonNull CardinalitySolution _113 // (|NamedElementCS::name| - 1)
			= new SubtractCardinalitySolution(_043, _001);
		private final @NonNull CardinalitySolution _114 // (|NavigatingArgCS::ownedCoIterator| - 1)
			= new SubtractCardinalitySolution(_044, _001);
		private final @NonNull CardinalitySolution _115 // (|NavigatingArgCS::ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_045, _001);
		private final @NonNull CardinalitySolution _116 // (|NavigatingArgCS::ownedNameExpression| - 1)
			= new SubtractCardinalitySolution(_046, _001);
		private final @NonNull CardinalitySolution _117 // (|NavigatingArgCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_047, _001);
		private final @NonNull CardinalitySolution _118 // (|NavigatingArgCS::prefix.','| - 1)
			= new SubtractCardinalitySolution(_048, _001);
		private final @NonNull CardinalitySolution _119 // (|NavigatingArgCS::prefix.';'| - 1)
			= new SubtractCardinalitySolution(_049, _001);
		private final @NonNull CardinalitySolution _120 // (|NavigatingArgCS::prefix.'|'| - 1)
			= new SubtractCardinalitySolution(_050, _001);
		private final @NonNull CardinalitySolution _121 // (|NestedExpCS::ownedExpression| - 1)
			= new SubtractCardinalitySolution(_051, _001);
		private final @NonNull CardinalitySolution _122 // (|NumberLiteralExpCS::symbol| - 1)
			= new SubtractCardinalitySolution(_052, _001);
		private final @NonNull CardinalitySolution _123 // (|OperatorExpCS::ownedRight| - 1)
			= new SubtractCardinalitySolution(_053, _001);
		private final @NonNull CardinalitySolution _124 // (|PathElementCS::referredElement| - 1)
			= new SubtractCardinalitySolution(_054, _001);
		private final @NonNull CardinalitySolution _125 // (|PathNameCS::ownedPathElements| - 1)
			= new SubtractCardinalitySolution(_055, _001);
		private final @NonNull CardinalitySolution _126 // (|PatternExpCS::ownedPatternType| - 1)
			= new SubtractCardinalitySolution(_056, _001);
		private final @NonNull CardinalitySolution _127 // (|PrimitiveTypeRefCS::name| - 1)
			= new SubtractCardinalitySolution(_058, _001);
		private final @NonNull CardinalitySolution _128 // (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new SubtractCardinalitySolution(_059, _001);
		private final @NonNull CardinalitySolution _129 // (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new GreaterThanCardinalitySolution(_059, _000);
		private final @NonNull CardinalitySolution _130 // (|ShadowPartCS::ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_060, _001);
		private final @NonNull CardinalitySolution _131 // (|ShadowPartCS::referredProperty| - 1)
			= new SubtractCardinalitySolution(_061, _001);
		private final @NonNull CardinalitySolution _132 // (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new SubtractCardinalitySolution(_062, _001);
		private final @NonNull CardinalitySolution _133 // (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new SubtractCardinalitySolution(_065, _001);
		private final @NonNull CardinalitySolution _134 // (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
			= new SubtractCardinalitySolution(_066, _001);
		private final @NonNull CardinalitySolution _135 // (|TemplateSignatureCS::ownedParameters| - 1)
			= new SubtractCardinalitySolution(_067, _001);
		private final @NonNull CardinalitySolution _136 // (|TupleLiteralExpCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_068, _001);
		private final @NonNull CardinalitySolution _137 // (|TupleTypeCS::name.'Tuple'| - 1)
			= new SubtractCardinalitySolution(_069, _001);
		private final @NonNull CardinalitySolution _138 // (|TupleTypeCS::ownedParts| - 1)
			= new SubtractCardinalitySolution(_070, _001);
		private final @NonNull CardinalitySolution _139 // (|TupleTypeCS::ownedParts| > 0)
			= new GreaterThanCardinalitySolution(_070, _000);
		private final @NonNull CardinalitySolution _140 // (|TypeLiteralExpCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_071, _001);
		private final @NonNull CardinalitySolution _141 // (|TypeNameExpCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_073, _001);
		private final @NonNull CardinalitySolution _142 // (|TypeParameterCS::ownedExtends| - 1)
			= new SubtractCardinalitySolution(_075, _001);
		private final @NonNull CardinalitySolution _143 // (|TypeParameterCS::ownedExtends| > 0)
			= new GreaterThanCardinalitySolution(_075, _000);
		private final @NonNull CardinalitySolution _144 // (|TypedElementCS::ownedType| - 1)
			= new SubtractCardinalitySolution(_076, _001);
		private final @NonNull CardinalitySolution _145 // (|TypedTypeRefCS::ownedPathName| - 1)
			= new SubtractCardinalitySolution(_079, _001);
		private final @NonNull CardinalitySolution _146 // (|VariableCS::ownedInitExpression| - 1)
			= new SubtractCardinalitySolution(_080, _001);
	}

	/**
	 * Steps for the matching process.
	 */
	private class _MatchSteps
	{
		private final @NonNull CardinalitySolutionStep _000 // assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._083);
		private final @NonNull CardinalitySolutionStep _001 // assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._084);
		private final @NonNull CardinalitySolutionStep _002 // assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._087);
		private final @NonNull CardinalitySolutionStep _003 // assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._088);
		private final @NonNull CardinalitySolutionStep _004 // assert (|CollectionPatternCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._090);
		private final @NonNull CardinalitySolutionStep _005 // assert (|CollectionTypeCS::name| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._091);
		private final @NonNull CardinalitySolutionStep _006 // assert (|ContextCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._092);
		private final @NonNull CardinalitySolutionStep _007 // assert (|IfExpCS::ownedCondition| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._095);
		private final @NonNull CardinalitySolutionStep _008 // assert (|IfExpCS::ownedElseExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._096);
		private final @NonNull CardinalitySolutionStep _009 // assert (|IfExpCS::ownedThenExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._097);
		private final @NonNull CardinalitySolutionStep _010 // assert (|IfThenExpCS::ownedCondition| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._098);
		private final @NonNull CardinalitySolutionStep _011 // assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._099);
		private final @NonNull CardinalitySolutionStep _012 // assert (|InfixExpCS::ownedLeft| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._100);
		private final @NonNull CardinalitySolutionStep _013 // assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._101);
		private final @NonNull CardinalitySolutionStep _014 // assert (|LetExpCS::ownedInExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._102);
		private final @NonNull CardinalitySolutionStep _015 // assert (|MapLiteralExpCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._106);
		private final @NonNull CardinalitySolutionStep _016 // assert (|MapLiteralPartCS::ownedKey| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._107);
		private final @NonNull CardinalitySolutionStep _017 // assert (|MapLiteralPartCS::ownedValue| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._108);
		private final @NonNull CardinalitySolutionStep _018 // assert (|MapTypeCS::name.'Map'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._109);
		private final @NonNull CardinalitySolutionStep _019 // assert (|MapTypeCS::ownedKeyType| - V0) == 0
			= new CardinalitySolutionStep_Assert(mt._110);
		private final @NonNull CardinalitySolutionStep _020 // assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._111);
		private final @NonNull CardinalitySolutionStep _021 // assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._112);
		private final @NonNull CardinalitySolutionStep _022 // assert (|NamedElementCS::name| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._113);
		private final @NonNull CardinalitySolutionStep _023 // assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._114);
		private final @NonNull CardinalitySolutionStep _024 // assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._115);
		private final @NonNull CardinalitySolutionStep _025 // assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._116);
		private final @NonNull CardinalitySolutionStep _026 // assert (|NavigatingArgCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._117);
		private final @NonNull CardinalitySolutionStep _027 // assert (|NavigatingArgCS::prefix.','| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._118);
		private final @NonNull CardinalitySolutionStep _028 // assert (|NavigatingArgCS::prefix.';'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._119);
		private final @NonNull CardinalitySolutionStep _029 // assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._120);
		private final @NonNull CardinalitySolutionStep _030 // assert (|NestedExpCS::ownedExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._121);
		private final @NonNull CardinalitySolutionStep _031 // assert (|NumberLiteralExpCS::symbol| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._122);
		private final @NonNull CardinalitySolutionStep _032 // assert (|OperatorExpCS::ownedRight| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._123);
		private final @NonNull CardinalitySolutionStep _033 // assert (|PathElementCS::referredElement| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._124);
		private final @NonNull CardinalitySolutionStep _034 // assert (|PathNameCS::ownedPathElements| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._125);
		private final @NonNull CardinalitySolutionStep _035 // assert (|PatternExpCS::ownedPatternType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._126);
		private final @NonNull CardinalitySolutionStep _036 // assert (|PrimitiveTypeRefCS::name| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._127);
		private final @NonNull CardinalitySolutionStep _037 // assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._130);
		private final @NonNull CardinalitySolutionStep _038 // assert (|ShadowPartCS::referredProperty| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._131);
		private final @NonNull CardinalitySolutionStep _039 // assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._134);
		private final @NonNull CardinalitySolutionStep _040 // assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._137);
		private final @NonNull CardinalitySolutionStep _041 // assert (|TypeLiteralExpCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._140);
		private final @NonNull CardinalitySolutionStep _042 // assert (|TypeNameExpCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._141);
		private final @NonNull CardinalitySolutionStep _043 // assert (|TypedElementCS::ownedType| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._144);
		private final @NonNull CardinalitySolutionStep _044 // assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._145);
		private final @NonNull CardinalitySolutionStep _045 // assert (|VariableCS::ownedInitExpression| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._146);
		private final @NonNull CardinalitySolutionStep _046 // assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._086);
		private final @NonNull CardinalitySolutionStep _047 // assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._094);
		private final @NonNull CardinalitySolutionStep _048 // assign V0 = (|LetExpCS::ownedVariables| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._103);
		private final @NonNull CardinalitySolutionStep _049 // assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._105);
		private final @NonNull CardinalitySolutionStep _050 // assign V0 = (|PathNameCS::ownedPathElements| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._125);
		private final @NonNull CardinalitySolutionStep _051 // assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._129);
		private final @NonNull CardinalitySolutionStep _052 // assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._132);
		private final @NonNull CardinalitySolutionStep _053 // assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._133);
		private final @NonNull CardinalitySolutionStep _054 // assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._135);
		private final @NonNull CardinalitySolutionStep _055 // assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(0, mt._136);
		private final @NonNull CardinalitySolutionStep _056 // assign V0 = (|TupleTypeCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._139);
		private final @NonNull CardinalitySolutionStep _057 // assign V0 = (|TypeParameterCS::ownedExtends| > 0)
			= new CardinalitySolutionStep_Assign(0, mt._143);
		private final @NonNull CardinalitySolutionStep _058 // assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
			= new CardinalitySolutionStep_Assign(0, mt._007);
		private final @NonNull CardinalitySolutionStep _059 // assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
			= new CardinalitySolutionStep_Assign(0, mt._012);
		private final @NonNull CardinalitySolutionStep _060 // assign V0 = |CollectionPatternCS::restVariableName|
			= new CardinalitySolutionStep_Assign(0, mt._015);
		private final @NonNull CardinalitySolutionStep _061 // assign V0 = |CollectionTypeCS::ownedType|
			= new CardinalitySolutionStep_Assign(0, mt._018);
		private final @NonNull CardinalitySolutionStep _062 // assign V0 = |IfExpCS::ownedIfThenExpressions|
			= new CardinalitySolutionStep_Assign(0, mt._023);
		private final @NonNull CardinalitySolutionStep _063 // assign V0 = |LetVariableCS::ownedRoundBracketedClause|
			= new CardinalitySolutionStep_Assign(0, mt._031);
		private final @NonNull CardinalitySolutionStep _064 // assign V0 = |MapTypeCS::ownedValueType|
			= new CardinalitySolutionStep_Assign(0, mt._038);
		private final @NonNull CardinalitySolutionStep _065 // assign V0 = |MultiplicityBoundsCS::upperBound|
			= new CardinalitySolutionStep_Assign(0, mt._040);
		private final @NonNull CardinalitySolutionStep _066 // assign V0 = |MultiplicityCS::isNullFree.'|1'|
			= new CardinalitySolutionStep_Assign(0, mt._041);
		private final @NonNull CardinalitySolutionStep _067 // assign V0 = |NavigatingArgCS::ownedCoIterator|
			= new CardinalitySolutionStep_Assign(0, mt._044);
		private final @NonNull CardinalitySolutionStep _068 // assign V0 = |NavigatingArgCS::ownedInitExpression|
			= new CardinalitySolutionStep_Assign(0, mt._045);
		private final @NonNull CardinalitySolutionStep _069 // assign V0 = |NavigatingArgCS::ownedType|
			= new CardinalitySolutionStep_Assign(0, mt._047);
		private final @NonNull CardinalitySolutionStep _070 // assign V0 = |PatternExpCS::patternVariableName|
			= new CardinalitySolutionStep_Assign(0, mt._057);
		private final @NonNull CardinalitySolutionStep _071 // assign V0 = |StringLiteralExpCS::segments|
			= new CardinalitySolutionStep_Assign(0, mt._063);
		private final @NonNull CardinalitySolutionStep _072 // assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
			= new CardinalitySolutionStep_Assign(0, mt._072);
		private final @NonNull CardinalitySolutionStep _073 // assign V0 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(0, mt._077);
		private final @NonNull CardinalitySolutionStep _074 // assign V0 = |TypedTypeRefCS::ownedBinding|
			= new CardinalitySolutionStep_Assign(0, mt._078);
		private final @NonNull CardinalitySolutionStep _075 // assign V0 = |VariableCS::ownedType|
			= new CardinalitySolutionStep_Assign(0, mt._081);
		private final @NonNull CardinalitySolutionStep _076 // assign V0 = |WildcardTypeRefCS::ownedExtends|
			= new CardinalitySolutionStep_Assign(0, mt._082);
		private final @NonNull CardinalitySolutionStep _077 // assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._085);
		private final @NonNull CardinalitySolutionStep _078 // assign V1 = (|CollectionPatternCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._089);
		private final @NonNull CardinalitySolutionStep _079 // assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._093);
		private final @NonNull CardinalitySolutionStep _080 // assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._104);
		private final @NonNull CardinalitySolutionStep _081 // assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._128);
		private final @NonNull CardinalitySolutionStep _082 // assign V1 = (|TupleTypeCS::ownedParts| > 0)
			= new CardinalitySolutionStep_Assign(1, mt._139);
		private final @NonNull CardinalitySolutionStep _083 // assign V1 = (|TypeParameterCS::ownedExtends| - 1)
			= new CardinalitySolutionStep_Assign(1, mt._142);
		private final @NonNull CardinalitySolutionStep _084 // assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
			= new CardinalitySolutionStep_Assign(1, mt._006);
		private final @NonNull CardinalitySolutionStep _085 // assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
			= new CardinalitySolutionStep_Assign(1, mt._017);
		private final @NonNull CardinalitySolutionStep _086 // assign V1 = |MultiplicityCS::isNullFree.'|1'|
			= new CardinalitySolutionStep_Assign(1, mt._041);
		private final @NonNull CardinalitySolutionStep _087 // assign V1 = |NavigatingArgCS::ownedCoIterator|
			= new CardinalitySolutionStep_Assign(1, mt._044);
		private final @NonNull CardinalitySolutionStep _088 // assign V1 = |NavigatingArgCS::ownedInitExpression|
			= new CardinalitySolutionStep_Assign(1, mt._045);
		private final @NonNull CardinalitySolutionStep _089 // assign V1 = |TemplateBindingCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(1, mt._064);
		private final @NonNull CardinalitySolutionStep _090 // assign V1 = |TypeNameExpCS::ownedPatternGuard|
			= new CardinalitySolutionStep_Assign(1, mt._074);
		private final @NonNull CardinalitySolutionStep _091 // assign V1 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(1, mt._077);
		private final @NonNull CardinalitySolutionStep _092 // assign V1 = |VariableCS::ownedType|
			= new CardinalitySolutionStep_Assign(1, mt._081);
		private final @NonNull CardinalitySolutionStep _093 // assign V2 = (|TupleTypeCS::ownedParts| - 1)
			= new CardinalitySolutionStep_Assign(2, mt._138);
		private final @NonNull CardinalitySolutionStep _094 // assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
			= new CardinalitySolutionStep_Assign(2, mt._004);
		private final @NonNull CardinalitySolutionStep _095 // assign V2 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(2, mt._077);
		private final @NonNull CardinalitySolutionStep _096 // assign V3 = |AbstractNameExpCS::isPre.'@'|
			= new CardinalitySolutionStep_Assign(3, mt._003);
		private final @NonNull CardinalitySolutionStep _097 // assign V3 = |TypedRefCS::ownedMultiplicity|
			= new CardinalitySolutionStep_Assign(3, mt._077);
		private final @NonNull CardinalitySolutionStep _098 // check-rule basecs::PathNameCS.ownedPathElements : 21
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._6/*FirstPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _099 // check-rule basecs::PathNameCS.ownedPathElements : 21|51
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._13/*FirstPathElementCS|NextPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _100 // check-rule basecs::PathNameCS.ownedPathElements : 51|91
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._39/*NextPathElementCS|URIFirstPathElementCS*/);
		private final @NonNull CardinalitySolutionStep _101 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : 40
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, iv._10/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _102 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : 73
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, iv._25/*TemplateParameterSubstitutionCS*/);
		private final @NonNull CardinalitySolutionStep _103 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 86
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, iv._36/*TypeRefCS*/);
		private final @NonNull CardinalitySolutionStep _104 // check-rule basecs::TemplateSignatureCS.ownedParameters : 85
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, iv._35/*TypeParameterCS*/);
		private final @NonNull CardinalitySolutionStep _105 // check-rule basecs::TupleTypeCS.ownedParts : 77
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, iv._27/*TuplePartCS*/);
		private final @NonNull CardinalitySolutionStep _106 // check-rule basecs::TypeParameterCS.ownedExtends : 87
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, iv._37/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _107 // check-rule basecs::TypedElementCS.ownedType : 79
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, iv._28/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _108 // check-rule basecs::TypedRefCS.ownedMultiplicity : 40
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, iv._10/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _109 // check-rule basecs::TypedTypeRefCS.ownedBinding : 72
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, iv._24/*TemplateBindingCS*/);
		private final @NonNull CardinalitySolutionStep _110 // check-rule basecs::TypedTypeRefCS.ownedPathName : 54
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, iv._14/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _111 // check-rule basecs::WildcardTypeRefCS.ownedExtends : 87
			= new CardinalitySolutionStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, iv._37/*TypedRefCS*/);
		private final @NonNull CardinalitySolutionStep _112 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 9
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._3/*CurlyBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _113 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 54
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, iv._14/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _114 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 62
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._20/*RoundBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _115 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 69
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, iv._22/*SquareBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _116 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 5
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, iv._1/*CollectionLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _117 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 7
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, iv._2/*CollectionTypeCS*/);
		private final @NonNull CardinalitySolutionStep _118 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _119 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 55
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._15/*PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _120 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _121 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : 55
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, iv._15/*PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _122 // check-rule essentialoclcs::CollectionPatternCS.ownedType : 7
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, iv._2/*CollectionTypeCS*/);
		private final @NonNull CardinalitySolutionStep _123 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 40
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, iv._10/*MultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _124 // check-rule essentialoclcs::CollectionTypeCS.ownedType : 80
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, iv._29/*TypeExpWithoutMultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _125 // check-rule essentialoclcs::ContextCS.ownedExpression : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _126 // check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 67
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, iv._21/*ShadowPartCS*/);
		private final @NonNull CardinalitySolutionStep _127 // check-rule essentialoclcs::IfExpCS.ownedCondition : 20|55
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, iv._16/*ExpCS|PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _128 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _129 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 13
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, iv._4/*ElseIfThenExpCS*/);
		private final @NonNull CardinalitySolutionStep _130 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _131 // check-rule essentialoclcs::IfThenExpCS.ownedCondition : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _132 // check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _133 // check-rule essentialoclcs::InfixExpCS.ownedLeft : 57
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, iv._19/*PrefixedPrimaryExpCS*/);
		private final @NonNull CardinalitySolutionStep _134 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _135 // check-rule essentialoclcs::LetExpCS.ownedInExpression : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _136 // check-rule essentialoclcs::LetExpCS.ownedVariables : 32
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, iv._7/*LetVariableCS*/);
		private final @NonNull CardinalitySolutionStep _137 // check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 62
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._20/*RoundBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _138 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 36
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, iv._8/*MapLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _139 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : 37
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, iv._9/*MapTypeCS*/);
		private final @NonNull CardinalitySolutionStep _140 // check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _141 // check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _142 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : 79
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, iv._28/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _143 // check-rule essentialoclcs::MapTypeCS.ownedValueType : 79
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, iv._28/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _144 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, iv._0/*CoIteratorVariableCS*/);
		private final @NonNull CardinalitySolutionStep _145 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _146 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, iv._11/*NavigatingArgExpCS*/);
		private final @NonNull CardinalitySolutionStep _147 // check-rule essentialoclcs::NavigatingArgCS.ownedType : 79
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, iv._28/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _148 // check-rule essentialoclcs::NestedExpCS.ownedExpression : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _149 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _150 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 56
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._17/*PrefixedLetExpCS*/);
		private final @NonNull CardinalitySolutionStep _151 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 57
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._19/*PrefixedPrimaryExpCS*/);
		private final @NonNull CardinalitySolutionStep _152 // check-rule essentialoclcs::PatternExpCS.ownedPatternType : 79
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, iv._28/*TypeExpCS*/);
		private final @NonNull CardinalitySolutionStep _153 // check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 44|46|47|48
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, iv._12/*NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS*/);
		private final @NonNull CardinalitySolutionStep _154 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 20|55
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._16/*ExpCS|PatternExpCS*/);
		private final @NonNull CardinalitySolutionStep _155 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 71
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._23/*StringLiteralExpCS*/);
		private final @NonNull CardinalitySolutionStep _156 // check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _157 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 76
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, iv._26/*TupleLiteralPartCS*/);
		private final @NonNull CardinalitySolutionStep _158 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 83
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, iv._31/*TypeLiteralWithMultiplicityCS*/);
		private final @NonNull CardinalitySolutionStep _159 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 9
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._3/*CurlyBracketedClauseCS*/);
		private final @NonNull CardinalitySolutionStep _160 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 54
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, iv._14/*PathNameCS*/);
		private final @NonNull CardinalitySolutionStep _161 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _162 // check-rule essentialoclcs::VariableCS.ownedInitExpression : 20
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull CardinalitySolutionStep _163 // check-rule essentialoclcs::VariableCS.ownedType : 79
			= new CardinalitySolutionStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, iv._28/*TypeExpCS*/);
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
		private final @NonNull RTSerializationLiteralStep _012 // 1*'?'
									= new RTSerializationLiteralStep(-1, "?");
		private final @NonNull RTSerializationLiteralStep _013 // 1*'@'
									= new RTSerializationLiteralStep(-1, "@");
		private final @NonNull RTSerializationLiteralStep _014 // 1*'Lambda'
									= new RTSerializationLiteralStep(-1, "Lambda");
		private final @NonNull RTSerializationLiteralStep _015 // 1*'Map'
									= new RTSerializationLiteralStep(-1, "Map");
		private final @NonNull RTSerializationLiteralStep _016 // 1*'Tuple'
									= new RTSerializationLiteralStep(-1, "Tuple");
		private final @NonNull RTSerializationLiteralStep _017 // 1*'['
									= new RTSerializationLiteralStep(-1, "[");
		private final @NonNull RTSerializationLiteralStep _018 // 1*']'
									= new RTSerializationLiteralStep(-1, "]");
		private final @NonNull RTSerializationLiteralStep _019 // 1*'else'
									= new RTSerializationLiteralStep(-1, "else");
		private final @NonNull RTSerializationLiteralStep _020 // 1*'elseif'
									= new RTSerializationLiteralStep(-1, "elseif");
		private final @NonNull RTSerializationLiteralStep _021 // 1*'endif'
									= new RTSerializationLiteralStep(-1, "endif");
		private final @NonNull RTSerializationLiteralStep _022 // 1*'extends'
									= new RTSerializationLiteralStep(-1, "extends");
		private final @NonNull RTSerializationLiteralStep _023 // 1*'if'
									= new RTSerializationLiteralStep(-1, "if");
		private final @NonNull RTSerializationLiteralStep _024 // 1*'in'
									= new RTSerializationLiteralStep(-1, "in");
		private final @NonNull RTSerializationLiteralStep _025 // 1*'invalid'
									= new RTSerializationLiteralStep(-1, "invalid");
		private final @NonNull RTSerializationLiteralStep _026 // 1*'let'
									= new RTSerializationLiteralStep(-1, "let");
		private final @NonNull RTSerializationLiteralStep _027 // 1*'null'
									= new RTSerializationLiteralStep(-1, "null");
		private final @NonNull RTSerializationLiteralStep _028 // 1*'pre'
									= new RTSerializationLiteralStep(-1, "pre");
		private final @NonNull RTSerializationLiteralStep _029 // 1*'self'
									= new RTSerializationLiteralStep(-1, "self");
		private final @NonNull RTSerializationLiteralStep _030 // 1*'then'
									= new RTSerializationLiteralStep(-1, "then");
		private final @NonNull RTSerializationLiteralStep _031 // 1*'{'
									= new RTSerializationLiteralStep(-1, "{");
		private final @NonNull RTSerializationLiteralStep _032 // 1*'|'
									= new RTSerializationLiteralStep(-1, "|");
		private final @NonNull RTSerializationLiteralStep _033 // 1*'|?'
									= new RTSerializationLiteralStep(-1, "|?");
		private final @NonNull RTSerializationLiteralStep _034 // 1*'}'
									= new RTSerializationLiteralStep(-1, "}");
		private final @NonNull RTSerializationAssignedRuleCallStep _035 // 1*AbstractNameExpCS::ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignStep _036 // 1*BooleanLiteralExpCS::symbol
									= new RTSerializationAssignStep(-1, EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull RTSerializationAssignedRuleCallStep _037 // 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _038 // 1*CollectionLiteralExpCS::ownedType=CollectionTypeCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _039 // 1*CollectionLiteralPartCS::ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _040 // 1*CollectionLiteralPartCS::ownedExpression=PatternExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _041 // 1*CollectionLiteralPartCS::ownedLastExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _042 // 1*CollectionPatternCS::ownedParts+=PatternExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _043 // 1*CollectionPatternCS::ownedType=CollectionTypeCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _044 // 1*CollectionPatternCS::restVariableName=Identifier
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _045 // 1*CollectionTypeCS::name=CollectionTypeIdentifier
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _046 // 1*CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _047 // 1*ContextCS::ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _048 // 1*CurlyBracketedClauseCS::ownedParts+=ShadowPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallsStep _049 // 1*IfExpCS::ownedCondition=ExpCS|PatternExpCS
									= new RTSerializationAssignedRuleCallsStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationAssignedRuleCallStep _050 // 1*IfExpCS::ownedElseExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _051 // 1*IfExpCS::ownedThenExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _052 // 1*IfThenExpCS::ownedCondition=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		private final @NonNull RTSerializationAssignedRuleCallStep _053 // 1*IfThenExpCS::ownedThenExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _054 // 1*InfixExpCS::ownedLeft=PrefixedPrimaryExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		private final @NonNull RTSerializationAssignedRuleCallStep _055 // 1*LambdaLiteralExpCS::ownedExpressionCS=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		private final @NonNull RTSerializationAssignedRuleCallStep _056 // 1*LetExpCS::ownedInExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _057 // 1*LetExpCS::ownedVariables+=LetVariableCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		private final @NonNull RTSerializationAssignedRuleCallStep _058 // 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _059 // 1*MapLiteralExpCS::ownedType=MapTypeCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _060 // 1*MapLiteralPartCS::ownedKey=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		private final @NonNull RTSerializationAssignedRuleCallStep _061 // 1*MapLiteralPartCS::ownedValue=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		private final @NonNull RTSerializationAssignedRuleCallStep _062 // 1*MapTypeCS::ownedKeyType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _063 // 1*MapTypeCS::ownedValueType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _064 // 1*MultiplicityBoundsCS::lowerBound=LOWER
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull RTSerializationAssignedRuleCallStep _065 // 1*MultiplicityBoundsCS::upperBound=UPPER
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull RTSerializationAssignStep _066 // 1*MultiplicityStringCS::stringBounds
									= new RTSerializationAssignStep(-1, BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS);
		private final @NonNull RTSerializationAssignedRuleCallStep _067 // 1*NamedElementCS::name=BinaryOperatorName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _068 // 1*NamedElementCS::name=UnaryOperatorName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _069 // 1*NamedElementCS::name=UnrestrictedName
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _070 // 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		private final @NonNull RTSerializationAssignedRuleCallStep _071 // 1*NavigatingArgCS::ownedInitExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _072 // 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _073 // 1*NavigatingArgCS::ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _074 // 1*NestedExpCS::ownedExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _075 // 1*NumberLiteralExpCS::symbol=NUMBER_LITERAL
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull RTSerializationAssignedRuleCallStep _076 // 1*OperatorExpCS::ownedRight=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull RTSerializationAssignedRuleCallStep _077 // 1*OperatorExpCS::ownedRight=PrefixedLetExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull RTSerializationAssignedRuleCallStep _078 // 1*OperatorExpCS::ownedRight=PrefixedPrimaryExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull RTSerializationCrossReferenceStep _079 // 1*PathElementCS::referredElement=URI
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"));
		private final @NonNull RTSerializationCrossReferenceStep _080 // 1*PathElementCS::referredElement=UnreservedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"));
		private final @NonNull RTSerializationCrossReferenceStep _081 // 1*PathElementCS::referredElement=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull RTSerializationCrossReferenceStep _082 // 1*PathElementCS::referredElement=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _083 // 1*PathNameCS::ownedPathElements+=FirstPathElementCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _084 // 1*PathNameCS::ownedPathElements+=NextPathElementCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _085 // 1*PathNameCS::ownedPathElements+=URIFirstPathElementCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _086 // 1*PatternExpCS::ownedPatternType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _087 // 1*PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _088 // 1*RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		private final @NonNull RTSerializationAssignedRuleCallsStep _089 // 1*ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS
									= new RTSerializationAssignedRuleCallsStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
				 new @NonNull AbstractRuleValue [2]);
		private final @NonNull RTSerializationAssignedRuleCallStep _090 // 1*ShadowPartCS::ownedInitExpression=StringLiteralExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull RTSerializationCrossReferenceStep _091 // 1*ShadowPartCS::referredProperty=UnrestrictedName
									= new RTSerializationCrossReferenceStep(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"));
		private final @NonNull RTSerializationAssignedRuleCallStep _092 // 1*SquareBracketedClauseCS::ownedTerms+=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		private final @NonNull RTSerializationAssignedRuleCallStep _093 // 1*TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _094 // 1*TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull RTSerializationAssignedRuleCallStep _095 // 1*TemplateSignatureCS::ownedParameters+=TypeParameterCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull RTSerializationAssignedRuleCallStep _096 // 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _097 // 1*TupleTypeCS::ownedParts+=TuplePartCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _098 // 1*TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _099 // 1*TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _100 // 1*TypeNameExpCS::ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _101 // 1*TypeNameExpCS::ownedPatternGuard=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		private final @NonNull RTSerializationAssignedRuleCallStep _102 // 1*TypeParameterCS::ownedExtends+=TypedRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull RTSerializationAssignedRuleCallStep _103 // 1*TypedElementCS::ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _104 // 1*TypedTypeRefCS::ownedBinding=TemplateBindingCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull RTSerializationAssignedRuleCallStep _105 // 1*TypedTypeRefCS::ownedPathName=PathNameCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _106 // 1*VariableCS::ownedInitExpression=ExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull RTSerializationAssignedRuleCallStep _107 // 1*VariableCS::ownedType=TypeExpCS
									= new RTSerializationAssignedRuleCallStep(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		private final @NonNull RTSerializationAssignedRuleCallStep _108 // 1*WildcardTypeRefCS::ownedExtends=TypedRefCS
									= new RTSerializationAssignedRuleCallStep(-1, BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull RTSerializationSequenceStep _109 // 1*steps-1..10
									= new RTSerializationSequenceStep(-1, 1, 10);
		private final @NonNull RTSerializationSequenceStep _110 // 1*steps-1..11
									= new RTSerializationSequenceStep(-1, 1, 11);
		private final @NonNull RTSerializationSequenceStep _111 // 1*steps-1..12
									= new RTSerializationSequenceStep(-1, 1, 12);
		private final @NonNull RTSerializationSequenceStep _112 // 1*steps-1..3
									= new RTSerializationSequenceStep(-1, 1, 3);
		private final @NonNull RTSerializationSequenceStep _113 // 1*steps-1..4
									= new RTSerializationSequenceStep(-1, 1, 4);
		private final @NonNull RTSerializationSequenceStep _114 // 1*steps-1..5
									= new RTSerializationSequenceStep(-1, 1, 5);
		private final @NonNull RTSerializationSequenceStep _115 // 1*steps-1..6
									= new RTSerializationSequenceStep(-1, 1, 6);
		private final @NonNull RTSerializationSequenceStep _116 // 1*steps-1..7
									= new RTSerializationSequenceStep(-1, 1, 7);
		private final @NonNull RTSerializationSequenceStep _117 // 1*steps-1..8
									= new RTSerializationSequenceStep(-1, 1, 8);
		private final @NonNull RTSerializationSequenceStep _118 // 1*steps-1..9
									= new RTSerializationSequenceStep(-1, 1, 9);
		private final @NonNull RTSerializationLiteralStep _119 // V00*'|1'
									= new RTSerializationLiteralStep(0, "|1");
		private final @NonNull RTSerializationAssignedRuleCallStep _120 // V00*AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		private final @NonNull RTSerializationAssignedRuleCallStep _121 // V00*IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		private final @NonNull RTSerializationAssignedRuleCallStep _122 // V00*LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _123 // V00*PatternExpCS::patternVariableName=UnrestrictedName
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		private final @NonNull RTSerializationAssignedRuleCallStep _124 // V00*StringLiteralExpCS::segments+=StringLiteral
									= new RTSerializationAssignedRuleCallStep(0, EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		private final @NonNull RTSerializationAssignedRuleCallStep _125 // V00*TypedRefCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(0, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationSequenceStep _126 // V00*steps-3..10
									= new RTSerializationSequenceStep(0, 3, 10);
		private final @NonNull RTSerializationSequenceStep _127 // V00*steps-3..5
									= new RTSerializationSequenceStep(0, 3, 5);
		private final @NonNull RTSerializationSequenceStep _128 // V00*steps-3..6
									= new RTSerializationSequenceStep(0, 3, 6);
		private final @NonNull RTSerializationSequenceStep _129 // V00*steps-3..7
									= new RTSerializationSequenceStep(0, 3, 7);
		private final @NonNull RTSerializationSequenceStep _130 // V00*steps-3..8
									= new RTSerializationSequenceStep(0, 3, 8);
		private final @NonNull RTSerializationSequenceStep _131 // V00*steps-4..10
									= new RTSerializationSequenceStep(0, 4, 10);
		private final @NonNull RTSerializationSequenceStep _132 // V00*steps-4..6
									= new RTSerializationSequenceStep(0, 4, 6);
		private final @NonNull RTSerializationSequenceStep _133 // V00*steps-4..8
									= new RTSerializationSequenceStep(0, 4, 8);
		private final @NonNull RTSerializationSequenceStep _134 // V00*steps-4..9
									= new RTSerializationSequenceStep(0, 4, 9);
		private final @NonNull RTSerializationSequenceStep _135 // V00*steps-5..7
									= new RTSerializationSequenceStep(0, 5, 7);
		private final @NonNull RTSerializationSequenceStep _136 // V00*steps-6..8
									= new RTSerializationSequenceStep(0, 6, 8);
		private final @NonNull RTSerializationLiteralStep _137 // V01*'|1'
									= new RTSerializationLiteralStep(1, "|1");
		private final @NonNull RTSerializationAssignedRuleCallStep _138 // V01*AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _139 // V01*CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		private final @NonNull RTSerializationAssignedRuleCallsStep _140 // V01*RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS
									= new RTSerializationAssignedRuleCallsStep(1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
				 new @NonNull AbstractRuleValue [3]);
		private final @NonNull RTSerializationAssignedRuleCallStep _141 // V01*TemplateBindingCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationAssignedRuleCallStep _142 // V01*TypedRefCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(1, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationSequenceStep _143 // V01*steps-4..6
									= new RTSerializationSequenceStep(1, 4, 6);
		private final @NonNull RTSerializationSequenceStep _144 // V01*steps-5..7
									= new RTSerializationSequenceStep(1, 5, 7);
		private final @NonNull RTSerializationSequenceStep _145 // V01*steps-5..8
									= new RTSerializationSequenceStep(1, 5, 8);
		private final @NonNull RTSerializationSequenceStep _146 // V01*steps-5..9
									= new RTSerializationSequenceStep(1, 5, 9);
		private final @NonNull RTSerializationSequenceStep _147 // V01*steps-6..8
									= new RTSerializationSequenceStep(1, 6, 8);
		private final @NonNull RTSerializationSequenceStep _148 // V01*steps-7..9
									= new RTSerializationSequenceStep(1, 7, 9);
		private final @NonNull RTSerializationSequenceStep _149 // V01*steps-8..10
									= new RTSerializationSequenceStep(1, 8, 10);
		private final @NonNull RTSerializationSequenceStep _150 // V01*steps-9..11
									= new RTSerializationSequenceStep(1, 9, 11);
		private final @NonNull RTSerializationAssignedRuleCallStep _151 // V02*AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS
									= new RTSerializationAssignedRuleCallStep(2, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull RTSerializationAssignedRuleCallStep _152 // V02*TypedRefCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(2, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationSequenceStep _153 // V02*steps-7..9
									= new RTSerializationSequenceStep(2, 7, 9);
		private final @NonNull RTSerializationAssignedRuleCallStep _154 // V03*TypedRefCS::ownedMultiplicity=MultiplicityCS
									= new RTSerializationAssignedRuleCallStep(3, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull RTSerializationSequenceStep _155 // V03*steps-6..8
									= new RTSerializationSequenceStep(3, 6, 8);

		/**
		 * Post constructor initialization that avoids recursions.
		 */
		private final void init() {
			_035.init(gr._54);
			_037.init(gr._05);
			_038.init(gr._07);
			_039.init(gr._20);
			_040.init(gr._55);
			_041.init(gr._20);
			_042.init(gr._55);
			_043.init(gr._07);
			_044.init(gr._24);
			_045.init(gr._08);
			_046.init(gr._80);
			_047.init(gr._20);
			_048.init(gr._67);
			_049.init(new @NonNull AbstractRuleValue [] {gr._20/*ExpCS*/, gr._55/*PatternExpCS*/});
			_050.init(gr._20);
			_051.init(gr._20);
			_052.init(gr._20);
			_053.init(gr._20);
			_054.init(gr._57);
			_055.init(gr._20);
			_056.init(gr._20);
			_057.init(gr._32);
			_058.init(gr._36);
			_059.init(gr._37);
			_060.init(gr._20);
			_061.init(gr._20);
			_062.init(gr._79);
			_063.init(gr._79);
			_064.init(gr._29);
			_065.init(gr._89);
			_067.init(gr._01);
			_068.init(gr._93);
			_069.init(gr._96);
			_070.init(gr._03);
			_071.init(gr._20);
			_072.init(gr._45);
			_073.init(gr._79);
			_074.init(gr._20);
			_075.init(gr._42);
			_076.init(gr._20);
			_077.init(gr._56);
			_078.init(gr._57);
			_083.init(gr._21);
			_084.init(gr._51);
			_085.init(gr._91);
			_086.init(gr._79);
			_087.init(gr._61);
			_088.init(gr._44);
			_089.init(new @NonNull AbstractRuleValue [] {gr._20/*ExpCS*/, gr._55/*PatternExpCS*/});
			_090.init(gr._71);
			_092.init(gr._20);
			_093.init(gr._73);
			_094.init(gr._86);
			_095.init(gr._85);
			_096.init(gr._76);
			_097.init(gr._77);
			_098.init(gr._83);
			_099.init(gr._09);
			_100.init(gr._54);
			_101.init(gr._20);
			_102.init(gr._87);
			_103.init(gr._79);
			_104.init(gr._72);
			_105.init(gr._54);
			_106.init(gr._20);
			_107.init(gr._79);
			_108.init(gr._87);
			_120.init(gr._69);
			_121.init(gr._13);
			_122.init(gr._62);
			_123.init(gr._96);
			_124.init(gr._70);
			_125.init(gr._40);
			_138.init(gr._62);
			_139.init(gr._40);
			_140.init(new @NonNull AbstractRuleValue [] {gr._47/*NavigatingCommaArgCS*/, gr._48/*NavigatingSemiArgCS*/, gr._46/*NavigatingBarArgCS*/});
			_141.init(gr._40);
			_142.init(gr._40);
			_151.init(gr._09);
			_152.init(gr._40);
			_154.init(gr._40);
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
			null,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _26 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _27 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _28 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _29 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _30 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _31 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _32 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _33 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _34 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._2 /* «! » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _35 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _36 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _37 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _38 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _39 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _40 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _41 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull Segment @NonNull [] @Nullable [] _42 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
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
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _44 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
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
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _46 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _47 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _48 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _49 = new @NonNull Segment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _50 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull DataTypeRuleValue _01 // BinaryOperatorName
			= new DataTypeRuleValue(1, "BinaryOperatorName");
		private final @NonNull ParserRuleValue _02 // BooleanLiteralExpCS
			= new ParserRuleValue(2, "BooleanLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._17 /* symbol={'false|true'} */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _03 // CoIteratorVariableCS
			= new ParserRuleValue(3, "CoIteratorVariableCS",
				new @NonNull SerializationRule [] {
					sr0._18 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _04 // CollectionLiteralExpCS
			= new ParserRuleValue(4, "CollectionLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._19 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _05 // CollectionLiteralPartCS
			= new ParserRuleValue(5, "CollectionLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._20 /* ownedExpression=PatternExpCS */,
					sr0._21 /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _06 // CollectionPatternCS
			= new ParserRuleValue(6, "CollectionPatternCS",
				new @NonNull SerializationRule [] {
					sr0._22 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _07 // CollectionTypeCS
			= new ParserRuleValue(7, "CollectionTypeCS",
				new @NonNull SerializationRule [] {
					sr0._23 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _08 // CollectionTypeIdentifier
			= new DataTypeRuleValue(8, "CollectionTypeIdentifier");
		private final @NonNull ParserRuleValue _09 // CurlyBracketedClauseCS
			= new ParserRuleValue(9, "CurlyBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr0._24 /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _10 // DOUBLE_QUOTED_STRING
			= new TerminalRuleValue(10, "DOUBLE_QUOTED_STRING");
		private final @NonNull TerminalRuleValue _11 // ESCAPED_CHARACTER
			= new TerminalRuleValue(11, "ESCAPED_CHARACTER");
		private final @NonNull TerminalRuleValue _12 // ESCAPED_ID
			= new TerminalRuleValue(12, "ESCAPED_ID");
		private final @NonNull ParserRuleValue _13 // ElseIfThenExpCS
			= new ParserRuleValue(13, "ElseIfThenExpCS",
				new @NonNull SerializationRule [] {
					sr0._25 /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _14 // EssentialOCLInfixOperatorName
			= new DataTypeRuleValue(14, "EssentialOCLInfixOperatorName");
		private final @NonNull DataTypeRuleValue _15 // EssentialOCLNavigationOperatorName
			= new DataTypeRuleValue(15, "EssentialOCLNavigationOperatorName");
		private final @NonNull DataTypeRuleValue _16 // EssentialOCLReservedKeyword
			= new DataTypeRuleValue(16, "EssentialOCLReservedKeyword");
		private final @NonNull DataTypeRuleValue _17 // EssentialOCLUnaryOperatorName
			= new DataTypeRuleValue(17, "EssentialOCLUnaryOperatorName");
		private final @NonNull DataTypeRuleValue _18 // EssentialOCLUnreservedName
			= new DataTypeRuleValue(18, "EssentialOCLUnreservedName");
		private final @NonNull DataTypeRuleValue _19 // EssentialOCLUnrestrictedName
			= new DataTypeRuleValue(19, "EssentialOCLUnrestrictedName");
		private final @NonNull ParserRuleValue _20 // ExpCS
			= new ParserRuleValue(20, "ExpCS",
				new @NonNull SerializationRule [] {
					sr0._36 /* symbol={'false|true'} */,
					sr0._39 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._26 /* '*' */,
					sr0._32 /* 'invalid' */,
					sr0._28 /* 'null' */,
					sr0._40 /* 'self' */,
					sr0._33 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._41 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._35 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._27 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._31 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._34 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._29 /* symbol=NUMBER_LITERAL */,
					sr0._42 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._38 /* segments+=StringLiteral[+] */,
					sr0._30 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._37 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._46 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._68 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._45); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _21 // FirstPathElementCS
			= new ParserRuleValue(21, "FirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._00 /* referredElement=UnrestrictedName */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _22 // ID
			= new DataTypeRuleValue(22, "ID");
		private final @NonNull TerminalRuleValue _23 // INT
			= new TerminalRuleValue(23, "INT");
		private final @NonNull DataTypeRuleValue _24 // Identifier
			= new DataTypeRuleValue(24, "Identifier");
		private final @NonNull ParserRuleValue _25 // IfExpCS
			= new ParserRuleValue(25, "IfExpCS",
				new @NonNull SerializationRule [] {
					sr0._43 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _26 // InfixOperatorName
			= new DataTypeRuleValue(26, "InfixOperatorName");
		private final @NonNull ParserRuleValue _27 // InvalidLiteralExpCS
			= new ParserRuleValue(27, "InvalidLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._44 /* 'invalid' */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _28 // LETTER_CHARACTER
			= new TerminalRuleValue(28, "LETTER_CHARACTER");
		private final @NonNull DataTypeRuleValue _29 // LOWER
			= new DataTypeRuleValue(29, "LOWER");
		private final @NonNull ParserRuleValue _30 // LambdaLiteralExpCS
			= new ParserRuleValue(30, "LambdaLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._45 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _31 // LetExpCS
			= new ParserRuleValue(31, "LetExpCS",
				new @NonNull SerializationRule [] {
					sr0._46 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _32 // LetVariableCS
			= new ParserRuleValue(32, "LetVariableCS",
				new @NonNull SerializationRule [] {
					sr0._47 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _33 // ML_COMMENT
			= new TerminalRuleValue(33, "ML_COMMENT");
		private final @NonNull TerminalRuleValue _34 // ML_SINGLE_QUOTED_STRING
			= new TerminalRuleValue(34, "ML_SINGLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _35 // MapLiteralExpCS
			= new ParserRuleValue(35, "MapLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._48 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _36 // MapLiteralPartCS
			= new ParserRuleValue(36, "MapLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._49 /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _37 // MapTypeCS
			= new ParserRuleValue(37, "MapTypeCS",
				new @NonNull SerializationRule [] {
					sr0._50 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _38 // Model
			= new ParserRuleValue(38, "Model",
				new @NonNull SerializationRule [] {
					sr0._51 /* ownedExpression=ExpCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _39 // MultiplicityBoundsCS
			= new ParserRuleValue(39, "MultiplicityBoundsCS",
				new @NonNull SerializationRule [] {
					sr0._01 /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _40 // MultiplicityCS
			= new ParserRuleValue(40, "MultiplicityCS",
				new @NonNull SerializationRule [] {
					sr0._02 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					sr0._04 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					sr0._05 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */,
					sr0._06 /* { '[' stringBounds={'*|+|?'} ']' } */,
					sr0._07 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					sr0._03 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _41 // MultiplicityStringCS
			= new ParserRuleValue(41, "MultiplicityStringCS",
				new @NonNull SerializationRule [] {
					sr0._08 /* stringBounds={'*|+|?'} */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _42 // NUMBER_LITERAL
			= new DataTypeRuleValue(42, "NUMBER_LITERAL");
		private final @NonNull ParserRuleValue _43 // NameExpCS
			= new ParserRuleValue(43, "NameExpCS",
				new @NonNull SerializationRule [] {
					sr0._52 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _44 // NavigatingArgCS
			= new ParserRuleValue(44, "NavigatingArgCS",
				new @NonNull SerializationRule [] {
					sr0._56 /* ownedNameExpression=NavigatingArgExpCS */,
					sr0._53 /* { ':' ownedType=TypeExpCS } */,
					sr0._55 /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._54 /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._57 /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _45 // NavigatingArgExpCS
			= new ParserRuleValue(45, "NavigatingArgExpCS",
				new @NonNull SerializationRule [] {
					sr0._36 /* symbol={'false|true'} */,
					sr0._39 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._26 /* '*' */,
					sr0._32 /* 'invalid' */,
					sr0._28 /* 'null' */,
					sr0._40 /* 'self' */,
					sr0._33 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._41 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._35 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._27 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._31 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._34 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._29 /* symbol=NUMBER_LITERAL */,
					sr0._42 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._38 /* segments+=StringLiteral[+] */,
					sr0._30 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._37 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._46 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._68 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._46); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _46 // NavigatingBarArgCS
			= new ParserRuleValue(46, "NavigatingBarArgCS",
				new @NonNull SerializationRule [] {
					sr0._58 /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _47 // NavigatingCommaArgCS
			= new ParserRuleValue(47, "NavigatingCommaArgCS",
				new @NonNull SerializationRule [] {
					sr0._62 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					sr0._60 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._61 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._59 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _48 // NavigatingSemiArgCS
			= new ParserRuleValue(48, "NavigatingSemiArgCS",
				new @NonNull SerializationRule [] {
					sr0._63 /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _49 // NavigationOperatorName
			= new DataTypeRuleValue(49, "NavigationOperatorName");
		private final @NonNull ParserRuleValue _50 // NestedExpCS
			= new ParserRuleValue(50, "NestedExpCS",
				new @NonNull SerializationRule [] {
					sr1._64 /* { '(' ownedExpression=ExpCS ')' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _51 // NextPathElementCS
			= new ParserRuleValue(51, "NextPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._09 /* referredElement=UnreservedName */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _52 // NullLiteralExpCS
			= new ParserRuleValue(52, "NullLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._65 /* 'null' */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _53 // NumberLiteralExpCS
			= new ParserRuleValue(53, "NumberLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._66 /* symbol=NUMBER_LITERAL */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _54 // PathNameCS
			= new ParserRuleValue(54, "PathNameCS",
				new @NonNull SerializationRule [] {
					sr0._10 /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _55 // PatternExpCS
			= new ParserRuleValue(55, "PatternExpCS",
				new @NonNull SerializationRule [] {
					sr1._67 /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _56 // PrefixedLetExpCS
			= new ParserRuleValue(56, "PrefixedLetExpCS",
				new @NonNull SerializationRule [] {
					sr0._46 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._68 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._18); /* LetExpCS|PrefixedLetExpCS */
		private final @NonNull ParserRuleValue _57 // PrefixedPrimaryExpCS
			= new ParserRuleValue(57, "PrefixedPrimaryExpCS",
				new @NonNull SerializationRule [] {
					sr0._17 /* symbol={'false|true'} */,
					sr0._19 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._43 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._44 /* 'invalid' */,
					sr0._45 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._48 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._52 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr1._64 /* { '(' ownedExpression=ExpCS ')' } */,
					sr1._65 /* 'null' */,
					sr1._66 /* symbol=NUMBER_LITERAL */,
					sr1._69 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr1._72 /* 'self' */,
					sr1._77 /* segments+=StringLiteral[+] */,
					sr1._78 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._88 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._97 /* '*' */
				},
				iv._43); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _58 // PrimaryExpCS
			= new ParserRuleValue(58, "PrimaryExpCS",
				new @NonNull SerializationRule [] {
					sr0._17 /* symbol={'false|true'} */,
					sr0._19 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._43 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._44 /* 'invalid' */,
					sr0._45 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._48 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._52 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr1._64 /* { '(' ownedExpression=ExpCS ')' } */,
					sr1._65 /* 'null' */,
					sr1._66 /* symbol=NUMBER_LITERAL */,
					sr1._72 /* 'self' */,
					sr1._77 /* segments+=StringLiteral[+] */,
					sr1._78 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr1._88 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr1._97 /* '*' */
				},
				iv._42); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _59 // PrimitiveLiteralExpCS
			= new ParserRuleValue(59, "PrimitiveLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._17 /* symbol={'false|true'} */,
					sr0._44 /* 'invalid' */,
					sr1._65 /* 'null' */,
					sr1._66 /* symbol=NUMBER_LITERAL */,
					sr1._77 /* segments+=StringLiteral[+] */,
					sr1._97 /* '*' */
				},
				iv._41); /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _60 // PrimitiveTypeCS
			= new ParserRuleValue(60, "PrimitiveTypeCS",
				new @NonNull SerializationRule [] {
					sr1._70 /* name=PrimitiveTypeIdentifier */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _61 // PrimitiveTypeIdentifier
			= new DataTypeRuleValue(61, "PrimitiveTypeIdentifier");
		private final @NonNull ParserRuleValue _62 // RoundBracketedClauseCS
			= new ParserRuleValue(62, "RoundBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._71 /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				(IndexVector)null);
		private final @NonNull TerminalRuleValue _63 // SIMPLE_ID
			= new TerminalRuleValue(63, "SIMPLE_ID");
		private final @NonNull TerminalRuleValue _64 // SINGLE_QUOTED_STRING
			= new TerminalRuleValue(64, "SINGLE_QUOTED_STRING");
		private final @NonNull TerminalRuleValue _65 // SL_COMMENT
			= new TerminalRuleValue(65, "SL_COMMENT");
		private final @NonNull ParserRuleValue _66 // SelfExpCS
			= new ParserRuleValue(66, "SelfExpCS",
				new @NonNull SerializationRule [] {
					sr1._72 /* 'self' */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _67 // ShadowPartCS
			= new ParserRuleValue(67, "ShadowPartCS",
				new @NonNull SerializationRule [] {
					sr1._73 /* ownedInitExpression=StringLiteralExpCS */,
					sr1._74 /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _68 // SimplePathNameCS
			= new ParserRuleValue(68, "SimplePathNameCS",
				new @NonNull SerializationRule [] {
					sr1._75 /* ownedPathElements+=FirstPathElementCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _69 // SquareBracketedClauseCS
			= new ParserRuleValue(69, "SquareBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._76 /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _70 // StringLiteral
			= new DataTypeRuleValue(70, "StringLiteral");
		private final @NonNull ParserRuleValue _71 // StringLiteralExpCS
			= new ParserRuleValue(71, "StringLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._77 /* segments+=StringLiteral[+] */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _72 // TemplateBindingCS
			= new ParserRuleValue(72, "TemplateBindingCS",
				new @NonNull SerializationRule [] {
					sr0._11 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _73 // TemplateParameterSubstitutionCS
			= new ParserRuleValue(73, "TemplateParameterSubstitutionCS",
				new @NonNull SerializationRule [] {
					sr0._12 /* ownedActualParameter=TypeRefCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _74 // TemplateSignatureCS
			= new ParserRuleValue(74, "TemplateSignatureCS",
				new @NonNull SerializationRule [] {
					sr0._13 /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _75 // TupleLiteralExpCS
			= new ParserRuleValue(75, "TupleLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._78 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _76 // TupleLiteralPartCS
			= new ParserRuleValue(76, "TupleLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr1._79 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _77 // TuplePartCS
			= new ParserRuleValue(77, "TuplePartCS",
				new @NonNull SerializationRule [] {
					sr1._80 /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _78 // TupleTypeCS
			= new ParserRuleValue(78, "TupleTypeCS",
				new @NonNull SerializationRule [] {
					sr1._81 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _79 // TypeExpCS
			= new ParserRuleValue(79, "TypeExpCS",
				new @NonNull SerializationRule [] {
					sr1._84 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._86 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._85 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._83 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._82 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._87 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._34); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull ParserRuleValue _80 // TypeExpWithoutMultiplicityCS
			= new ParserRuleValue(80, "TypeExpWithoutMultiplicityCS",
				new @NonNull SerializationRule [] {
					sr0._22 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
					sr0._23 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._50 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._70 /* name=PrimitiveTypeIdentifier */,
					sr1._81 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr1._93 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				iv._33); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull ParserRuleValue _81 // TypeLiteralCS
			= new ParserRuleValue(81, "TypeLiteralCS",
				new @NonNull SerializationRule [] {
					sr0._23 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._50 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._70 /* name=PrimitiveTypeIdentifier */,
					sr1._81 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._30); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */
		private final @NonNull ParserRuleValue _82 // TypeLiteralExpCS
			= new ParserRuleValue(82, "TypeLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._88 /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _83 // TypeLiteralWithMultiplicityCS
			= new ParserRuleValue(83, "TypeLiteralWithMultiplicityCS",
				new @NonNull SerializationRule [] {
					sr1._91 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._92 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._90 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._89 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._32); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
		private final @NonNull ParserRuleValue _84 // TypeNameExpCS
			= new ParserRuleValue(84, "TypeNameExpCS",
				new @NonNull SerializationRule [] {
					sr1._93 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _85 // TypeParameterCS
			= new ParserRuleValue(85, "TypeParameterCS",
				new @NonNull SerializationRule [] {
					sr0._14 /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _86 // TypeRefCS
			= new ParserRuleValue(86, "TypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._15 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */,
					sr0._16 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				iv._48); /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
		private final @NonNull ParserRuleValue _87 // TypedRefCS
			= new ParserRuleValue(87, "TypedRefCS",
				new @NonNull SerializationRule [] {
					sr0._15 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				iv._38); /* TypedRefCS|TypedTypeRefCS */
		private final @NonNull ParserRuleValue _88 // TypedTypeRefCS
			= new ParserRuleValue(88, "TypedTypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._15 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _89 // UPPER
			= new DataTypeRuleValue(89, "UPPER");
		private final @NonNull DataTypeRuleValue _90 // URI
			= new DataTypeRuleValue(90, "URI");
		private final @NonNull ParserRuleValue _91 // URIFirstPathElementCS
			= new ParserRuleValue(91, "URIFirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr1._95 /* referredElement=UnrestrictedName */,
					sr1._94 /* referredElement=URI */
				},
				(IndexVector)null);
		private final @NonNull ParserRuleValue _92 // URIPathNameCS
			= new ParserRuleValue(92, "URIPathNameCS",
				new @NonNull SerializationRule [] {
					sr1._96 /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _93 // UnaryOperatorName
			= new DataTypeRuleValue(93, "UnaryOperatorName");
		private final @NonNull ParserRuleValue _94 // UnlimitedNaturalLiteralExpCS
			= new ParserRuleValue(94, "UnlimitedNaturalLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._97 /* '*' */
				},
				(IndexVector)null);
		private final @NonNull DataTypeRuleValue _95 // UnreservedName
			= new DataTypeRuleValue(95, "UnreservedName");
		private final @NonNull DataTypeRuleValue _96 // UnrestrictedName
			= new DataTypeRuleValue(96, "UnrestrictedName");
		private final @NonNull TerminalRuleValue _97 // WS
			= new TerminalRuleValue(97, "WS");
		private final @NonNull ParserRuleValue _98 // WildcardTypeRefCS
			= new ParserRuleValue(98, "WildcardTypeRefCS",
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
		private final @NonNull EClassValue _00 // BooleanLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._17, sl._24) /* symbol={'false|true'} */,
					new SerializationRule_SegmentsList(sr0._36, sl._24) /* symbol={'false|true'} */
				}, null
			);
		private final @NonNull EClassValue _01 // CollectionLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._19, sl._28) /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					new SerializationRule_SegmentsList(sr0._39, sl._28) /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
						iv._1) /* CollectionLiteralPartCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
						iv._2) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassValue _02 // CollectionLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._20, sl._24) /* ownedExpression=PatternExpCS */,
					new SerializationRule_SegmentsList(sr0._21, sl._26) /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
						iv._47) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _03 // CollectionPatternCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._22, sl._06) /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
					new SerializationRule_SegmentsList(sr1._85, sl._07) /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._10) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						iv._15) /* PatternExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						iv._2) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassValue _04 // CollectionTypeCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._23, sl._18) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._83, sl._19) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._90, sl._19) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						iv._10) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._10) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						iv._33) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _05 // ContextCS
			= new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._51, sl._24) /* ownedExpression=ExpCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _06 // CurlyBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._24, sl._14) /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						iv._21) /* ShadowPartCS */
				}
			);
		private final @NonNull EClassValue _07 // ExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._26, sl._24) /* '*' */,
					new SerializationRule_SegmentsList(sr0._32, sl._24) /* 'invalid' */,
					new SerializationRule_SegmentsList(sr0._28, sl._24) /* 'null' */,
					new SerializationRule_SegmentsList(sr0._40, sl._24) /* 'self' */
				}, null
			);
		private final @NonNull EClassValue _08 // IfExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._33, sl._45) /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					new SerializationRule_SegmentsList(sr0._43, sl._45) /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						iv._47) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						iv._4) /* ElseIfThenExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _09 // IfThenExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._25, sl._43) /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _10 // InfixExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._41, sl._29) /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
						iv._43) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _11 // InvalidLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._44, sl._24) /* 'invalid' */
				}, null
			);
		private final @NonNull EClassValue _12 // LambdaLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._35, sl._48) /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					new SerializationRule_SegmentsList(sr0._45, sl._48) /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _13 // LetExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._46, sl._40) /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
						iv._7) /* LetVariableCS */
				}
			);
		private final @NonNull EClassValue _14 // LetVariableCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._47, sl._42) /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._20) /* RoundBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._34) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _15 // MapLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._27, sl._28) /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					new SerializationRule_SegmentsList(sr0._48, sl._28) /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
						iv._8) /* MapLiteralPartCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
						iv._9) /* MapTypeCS */
				}
			);
		private final @NonNull EClassValue _16 // MapLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._49, sl._29) /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _17 // MapTypeCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._50, sl._20) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._82, sl._21) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._89, sl._21) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						iv._34) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._10) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						iv._34) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _18 // MultiplicityBoundsCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._01, sl._23) /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */,
					new SerializationRule_SegmentsList(sr0._02, sl._10) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._04, sl._11) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._05, sl._11) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, null
			);
		private final @NonNull EClassValue _19 // MultiplicityStringCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._06, sl._12) /* { '[' stringBounds={'*|+|?'} ']' } */,
					new SerializationRule_SegmentsList(sr0._07, sl._13) /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._03, sl._13) /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._08, sl._50) /* stringBounds={'*|+|?'} */
				}, null
			);
		private final @NonNull EClassValue _20 // NameExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._31, sl._25) /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					new SerializationRule_SegmentsList(sr0._52, sl._25) /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._3) /* CurlyBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._14) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._20) /* RoundBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						iv._22) /* SquareBracketedClauseCS */
				}
			);
		private final @NonNull EClassValue _21 // NavigatingArgCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._56, sl._24) /* ownedNameExpression=NavigatingArgExpCS */,
					new SerializationRule_SegmentsList(sr0._53, sl._39) /* { ':' ownedType=TypeExpCS } */,
					new SerializationRule_SegmentsList(sr0._54, sl._30) /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._55, sl._31) /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._57, sl._27) /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					new SerializationRule_SegmentsList(sr0._58, sl._41) /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */,
					new SerializationRule_SegmentsList(sr0._62, sl._34) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					new SerializationRule_SegmentsList(sr0._61, sl._36) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._60, sl._37) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._59, sl._35) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					new SerializationRule_SegmentsList(sr0._63, sl._38) /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						iv._0) /* CoIteratorVariableCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						iv._34) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _22 // NestedExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._34, sl._33) /* { '(' ownedExpression=ExpCS ')' } */,
					new SerializationRule_SegmentsList(sr1._64, sl._33) /* { '(' ownedExpression=ExpCS ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _23 // NullLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._65, sl._24) /* 'null' */
				}, null
			);
		private final @NonNull EClassValue _24 // NumberLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._29, sl._24) /* symbol=NUMBER_LITERAL */,
					new SerializationRule_SegmentsList(sr1._66, sl._24) /* symbol=NUMBER_LITERAL */
				}, null
			);
		private final @NonNull EClassValue _25 // PathElementCS
			= new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._00, sl._50) /* referredElement=UnrestrictedName */,
					new SerializationRule_SegmentsList(sr0._09, sl._50) /* referredElement=UnreservedName */,
					new SerializationRule_SegmentsList(sr1._95, sl._50) /* referredElement=UnrestrictedName */
				}, null
			);
		private final @NonNull EClassValue _26 // PathElementWithURICS
			= new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._94, sl._50) /* referredElement=URI */
				}, null
			);
		private final @NonNull EClassValue _27 // PathNameCS
			= new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._10, sl._03) /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */,
					new SerializationRule_SegmentsList(sr1._75, sl._00) /* ownedPathElements+=FirstPathElementCS */,
					new SerializationRule_SegmentsList(sr1._96, sl._03) /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						iv._40) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
				}
			);
		private final @NonNull EClassValue _28 // PatternExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._67, sl._49) /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
						iv._34) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _29 // PrefixExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._42, sl._39) /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					new SerializationRule_SegmentsList(sr1._68, sl._39) /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					new SerializationRule_SegmentsList(sr1._69, sl._39) /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._44) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _30 // PrimitiveTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._70, sl._50) /* name=PrimitiveTypeIdentifier */,
					new SerializationRule_SegmentsList(sr1._84, sl._15) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._91, sl._15) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._10) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassValue _31 // RoundBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._71, sl._08) /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
						iv._12) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
				}
			);
		private final @NonNull EClassValue _32 // SelfExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SELF_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._72, sl._24) /* 'self' */
				}, null
			);
		private final @NonNull EClassValue _33 // ShadowPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._73, sl._24) /* ownedInitExpression=StringLiteralExpCS */,
					new SerializationRule_SegmentsList(sr1._74, sl._49) /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
						iv._47) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _34 // SquareBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._76, sl._09) /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _35 // StringLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._38, sl._24) /* segments+=StringLiteral[+] */,
					new SerializationRule_SegmentsList(sr1._77, sl._24) /* segments+=StringLiteral[+] */
				}, null
			);
		private final @NonNull EClassValue _36 // TemplateBindingCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._11, sl._05) /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						iv._10) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						iv._25) /* TemplateParameterSubstitutionCS */
				}
			);
		private final @NonNull EClassValue _37 // TemplateParameterSubstitutionCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._12, sl._24) /* ownedActualParameter=TypeRefCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						iv._48) /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
				}
			);
		private final @NonNull EClassValue _38 // TemplateSignatureCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._13, sl._32) /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						iv._35) /* TypeParameterCS */
				}
			);
		private final @NonNull EClassValue _39 // TupleLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._30, sl._47) /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._78, sl._47) /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						iv._26) /* TupleLiteralPartCS */
				}
			);
		private final @NonNull EClassValue _40 // TupleLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._79, sl._46) /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._34) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _41 // TuplePartCS
			= new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._80, sl._49) /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._34) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _42 // TupleTypeCS
			= new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._81, sl._16) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._86, sl._17) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._92, sl._17) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._10) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						iv._27) /* TuplePartCS */
				}
			);
		private final @NonNull EClassValue _43 // TypeLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._37, sl._24) /* ownedType=TypeLiteralWithMultiplicityCS */,
					new SerializationRule_SegmentsList(sr1._88, sl._24) /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						iv._32) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
				}
			);
		private final @NonNull EClassValue _44 // TypeNameExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._87, sl._02) /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._93, sl._01) /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._3) /* CurlyBracketedClauseCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._10) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._14) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _45 // TypeParameterCS
			= new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._14, sl._44) /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						iv._38) /* TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _46 // TypedTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._15, sl._04) /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						iv._24) /* TemplateBindingCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						iv._14) /* PathNameCS */
				}
			);
		private final @NonNull EClassValue _47 // UnlimitedNaturalLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._97, sl._24) /* '*' */
				}, null
			);
		private final @NonNull EClassValue _48 // VariableCS
			= new EClassValue(EssentialOCLCSPackage.Literals.VARIABLE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._18, sl._43) /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._34) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _49 // WildcardTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._16, sl._22) /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						iv._38) /* TypedRefCS|TypedTypeRefCS */
				}
			);
	}

	/**
	 * The various serialization rules that serialize an EClass.
	 */
	private class _SerializationRules0
	{
		// Base::FirstPathElementCS : referredElement=UnrestrictedName
		private @NonNull SerializationRule _00 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._033 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._082 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._50,
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
		private @NonNull SerializationRule _01 = new SerializationRule(39,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._065 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._020 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || «null» */,
				st._064 /* 1*MultiplicityBoundsCS::lowerBound=LOWER || «? » «value» «? » */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._006 /* 1*'..' || «? » «value» «? » */,
				st._065 /* 1*MultiplicityBoundsCS::upperBound=UPPER || «? » «value» «? » */
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
		private @NonNull SerializationRule _02 = new SerializationRule(40,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._065 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._020 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._116 /* 1*steps-1..7 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._064 /* 1*MultiplicityBoundsCS::lowerBound=LOWER || «? » «value» «? » */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «? » «value» «? » */,
				st._065 /* 1*MultiplicityBoundsCS::upperBound=UPPER || «? » «value» «? » */,
				st._018 /* 1*']' || «! » «value» */
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
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _03 = new SerializationRule(40,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._066 /* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._021 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._066 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._119 /* V00*'|1' || «? » «value» «? » */,
				st._018 /* 1*']' || «! » «value» */
			},
			sl._13,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					ev._8),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._0)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._8, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._0, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' }
		private @NonNull SerializationRule _04 = new SerializationRule(40,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._065 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._020 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._064 /* 1*MultiplicityBoundsCS::lowerBound=LOWER || «? » «value» «? » */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «? » «value» «? » */,
				st._065 /* 1*MultiplicityBoundsCS::upperBound=UPPER || «? » «value» «? » */,
				st._033 /* 1*'|?' || «? » «value» «? » */,
				st._018 /* 1*']' || «! » «value» */
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
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _05 = new SerializationRule(40,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._086 /* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._065 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._020 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._064 /* 1*MultiplicityBoundsCS::lowerBound=LOWER || «? » «value» «? » */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «? » «value» «? » */,
				st._065 /* 1*MultiplicityBoundsCS::upperBound=UPPER || «? » «value» «? » */,
				st._137 /* V01*'|1' || «? » «value» «? » */,
				st._018 /* 1*']' || «! » «value» */
			},
			sl._11,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					ev._8)
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
						new EnumerationValue_MultiplicativeCardinality(ev._8, MultiplicativeCardinality.ZERO_OR_ONE)
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
		private @NonNull SerializationRule _06 = new SerializationRule(40,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._021 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._066 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._018 /* 1*']' || «! » «value» */
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
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} '|?' ']' }
		private @NonNull SerializationRule _07 = new SerializationRule(40,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._021 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._066 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */,
				st._033 /* 1*'|?' || «? » «value» «? » */,
				st._018 /* 1*']' || «! » «value» */
			},
			sl._13,
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
		// Base::MultiplicityStringCS : stringBounds={'*|+|?'}
		private @NonNull SerializationRule _08 = new SerializationRule(41,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._021 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._066 /* 1*MultiplicityStringCS::stringBounds || «? » «value» «? » */
			},
			sl._50,
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
		private @NonNull SerializationRule _09 = new SerializationRule(51,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._033 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._080 /* 1*PathElementCS::referredElement=UnreservedName || «? » «value» «? » */
			},
			sl._50,
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
		private @NonNull SerializationRule _10 = new SerializationRule(54,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._099 /* check-rule basecs::PathNameCS.ownedPathElements : 21|51 */,
				ms._050 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || «null» */,
				st._083 /* 1*PathNameCS::ownedPathElements+=FirstPathElementCS || «null» */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'::' || «! » «value» «! » */,
				st._084 /* 1*PathNameCS::ownedPathElements+=NextPathElementCS || «null» */
			},
			sl._03,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._13) /* FirstPathElementCS|NextPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(51, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(21, MultiplicativeCardinality.ONE)
					}
				)
			});
		// Base::TemplateBindingCS : { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _11 = new SerializationRule(72,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._101 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 40 */,
				ms._102 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 73 */,
				ms._089 /* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				ms._053 /* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._115 /* 1*steps-1..6 || «null» */,
				st._093 /* 1*TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || «null» */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._093 /* 1*TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || «null» */,
				st._141 /* V01*TemplateBindingCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._05,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					iv._25) /* TemplateParameterSubstitutionCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(73, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// Base::TemplateParameterSubstitutionCS : ownedActualParameter=TypeRefCS
		private @NonNull SerializationRule _12 = new SerializationRule(73,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._103 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 86 */,
				ms._039 /* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._094 /* 1*TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					iv._36) /* TypeRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(86, MultiplicativeCardinality.ONE)
					}
				)
			});
		// Base::TemplateSignatureCS : { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' }
		private @NonNull SerializationRule _13 = new SerializationRule(74,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._104 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 85 */,
				ms._054 /* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._116 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._095 /* 1*TemplateSignatureCS::ownedParameters+=TypeParameterCS || «null» */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._095 /* 1*TemplateSignatureCS::ownedParameters+=TypeParameterCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._32,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._35) /* TypeParameterCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(85, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// Base::TypeParameterCS : { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] }
		private @NonNull SerializationRule _14 = new SerializationRule(85,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._106 /* check-rule basecs::TypeParameterCS.ownedExtends : 87 */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._057 /* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				ms._083 /* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._130 /* V00*steps-3..8 || «null» */,
				st._022 /* 1*'extends' || «? » «value» «? » */,
				st._102 /* 1*TypeParameterCS::ownedExtends+=TypedRefCS || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._000 /* 1*'&&' || «? » «value» «? » */,
				st._102 /* 1*TypeParameterCS::ownedExtends+=TypedRefCS || «null» */
			},
			sl._44,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					iv._37) /* TypedRefCS */
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
					new RuleIndex_MultiplicativeCardinality(87, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::TypedTypeRefCS : { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] }
		private @NonNull SerializationRule _15 = new SerializationRule(88,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._109 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 72 */,
				ms._110 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 54 */,
				ms._074 /* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				ms._044 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._115 /* 1*steps-1..6 || «null» */,
				st._105 /* 1*TypedTypeRefCS::ownedPathName=PathNameCS || «null» */,
				st._128 /* V00*steps-3..6 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._104 /* 1*TypedTypeRefCS::ownedBinding=TemplateBindingCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._04,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._24) /* TemplateBindingCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._14) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(72, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(54, MultiplicativeCardinality.ONE)
					}
				)
			});
		// Base::WildcardTypeRefCS : { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
		private @NonNull SerializationRule _16 = new SerializationRule(98,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._111 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : 87 */,
				ms._076 /* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || «null» */,
				st._012 /* 1*'?' || «? » «value» «? » */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._022 /* 1*'extends' || «? » «value» «? » */,
				st._108 /* 1*WildcardTypeRefCS::ownedExtends=TypedRefCS || «null» */
			},
			sl._22,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					iv._37) /* TypedRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(87, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::BooleanLiteralExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _17 = new SerializationRule(2,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._001 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._036 /* 1*BooleanLiteralExpCS::symbol || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._6)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._6, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::CoIteratorVariableCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] }
		private @NonNull SerializationRule _18 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._163 /* check-rule essentialoclcs::VariableCS.ownedType : 79 */,
				ms._075 /* assign V0 = |VariableCS::ownedType| */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._107 /* 1*VariableCS::ownedType=TypeExpCS || «null» */
			},
			sl._43,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._28) /* TypeExpCS */
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
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _19 = new SerializationRule(4,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._117 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 7 */,
				ms._116 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 5 */,
				ms._002 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._046 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._077 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._038 /* 1*CollectionLiteralExpCS::ownedType=CollectionTypeCS || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._133 /* V00*steps-4..8 || «null» */,
				st._037 /* 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._037 /* 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._28,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					iv._1) /* CollectionLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(7, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(5, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : ownedExpression=PatternExpCS
		private @NonNull SerializationRule _20 = new SerializationRule(5,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._119 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 55 */,
				ms._003 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._040 /* 1*CollectionLiteralPartCS::ownedExpression=PatternExpCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					iv._15) /* PatternExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] }
		private @NonNull SerializationRule _21 = new SerializationRule(5,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._118 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 20 */,
				ms._120 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 20 */,
				ms._059 /* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
				ms._003 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._039 /* 1*CollectionLiteralPartCS::ownedExpression=ExpCS || «null» */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._006 /* 1*'..' || «? » «value» «? » */,
				st._041 /* 1*CollectionLiteralPartCS::ownedLastExpression=ExpCS || «null» */
			},
			sl._26,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CollectionPatternCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' }
		private @NonNull SerializationRule _22 = new SerializationRule(6,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._122 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 7 */,
				ms._121 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 55 */,
				ms._060 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._078 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._004 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._110 /* 1*steps-1..11 || «null» */,
				st._043 /* 1*CollectionPatternCS::ownedType=CollectionTypeCS || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._131 /* V00*steps-4..10 || «null» */,
				st._042 /* 1*CollectionPatternCS::ownedParts+=PatternExpCS || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._042 /* 1*CollectionPatternCS::ownedParts+=PatternExpCS || «null» */,
				st._004 /* 1*'++' || «? » «value» «? » */,
				st._044 /* 1*CollectionPatternCS::restVariableName=Identifier || «? » «value» «? » */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._06,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._15) /* PatternExpCS */
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
					new RuleIndex_MultiplicativeCardinality(7, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::CollectionTypeCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
		private @NonNull SerializationRule _23 = new SerializationRule(7,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._123 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 40 */,
				ms._124 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 80 */,
				ms._061 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._085 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._116 /* 1*steps-1..7 || «null» */,
				st._045 /* 1*CollectionTypeCS::name=CollectionTypeIdentifier || «? » «value» «? » */,
				st._129 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._046 /* 1*CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || «null» */,
				st._139 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._18,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._10) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._29) /* TypeExpWithoutMultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(80, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CurlyBracketedClauseCS : { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _24 = new SerializationRule(9,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._126 /* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 67 */,
				ms._047 /* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
				ms._079 /* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._129 /* V00*steps-3..7 || «null» */,
				st._048 /* 1*CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || «null» */,
				st._144 /* V01*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._048 /* 1*CurlyBracketedClauseCS::ownedParts+=ShadowPartCS || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._14,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					iv._21) /* ShadowPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ElseIfThenExpCS : { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS }
		private @NonNull SerializationRule _25 = new SerializationRule(13,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._131 /* check-rule essentialoclcs::IfThenExpCS.ownedCondition : 20 */,
				ms._132 /* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 20 */,
				ms._011 /* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
				ms._010 /* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._020 /* 1*'elseif' || «? » «value» «? » */,
				st._052 /* 1*IfThenExpCS::ownedCondition=ExpCS || «null» */,
				st._030 /* 1*'then' || «? » «value» «? » */,
				st._053 /* 1*IfThenExpCS::ownedThenExpression=ExpCS || «null» */
			},
			sl._43,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : '*'
		private @NonNull SerializationRule _26 = new SerializationRule(20,
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
		// EssentialOCL::ExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _27 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._139 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 37 */,
				ms._138 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 36 */,
				ms._015 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._049 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._080 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._059 /* 1*MapLiteralExpCS::ownedType=MapTypeCS || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._133 /* V00*steps-4..8 || «null» */,
				st._058 /* 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._058 /* 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._28,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					iv._9) /* MapTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					iv._8) /* MapLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(37, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(36, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : 'null'
		private @NonNull SerializationRule _28 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._027 /* 1*'null' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _29 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._031 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._075 /* 1*NumberLiteralExpCS::symbol=NUMBER_LITERAL || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
		// EssentialOCL::ExpCS : { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
		private @NonNull SerializationRule _30 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._157 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 76 */,
				ms._055 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._016 /* 1*'Tuple' || «? » «value» «? » */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._096 /* 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || «null» */,
				st._135 /* V00*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._096 /* 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._47,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._26) /* TupleLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(76, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _31 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._115 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 69 */,
				ms._113 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 54 */,
				ms._114 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 62 */,
				ms._112 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 9 */,
				ms._096 /* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				ms._094 /* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				ms._084 /* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				ms._058 /* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				ms._000 /* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._035 /* 1*AbstractNameExpCS::ownedPathName=PathNameCS || «null» */,
				st._120 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || «null» */,
				st._138 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || «null» */,
				st._151 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || «null» */,
				st._155 /* V03*steps-6..8 || «null» */,
				st._013 /* 1*'@' || «? » «value» «? » */,
				st._028 /* 1*'pre' || «? » «value» «? » */
			},
			sl._25,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._3)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._22) /* SquareBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._14) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._20) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._3) /* CurlyBracketedClauseCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._3, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(69, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(54, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(9, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : 'invalid'
		private @NonNull SerializationRule _32 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._025 /* 1*'invalid' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _33 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._127 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 20|55 */,
				ms._129 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 13 */,
				ms._128 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 20 */,
				ms._130 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 20 */,
				ms._008 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._062 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._009 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._007 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._023 /* 1*'if' || «? » «value» «? » */,
				st._049 /* 1*IfExpCS::ownedCondition=ExpCS|PatternExpCS || «null» */,
				st._030 /* 1*'then' || «? » «value» «? » */,
				st._051 /* 1*IfExpCS::ownedThenExpression=ExpCS || «null» */,
				st._121 /* V00*IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || «null» */,
				st._019 /* 1*'else' || «? » «value» «? » */,
				st._050 /* 1*IfExpCS::ownedElseExpression=ExpCS || «null» */,
				st._021 /* 1*'endif' || «? » «value» «? » */
			},
			sl._45,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._16) /* ExpCS|PatternExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._4) /* ElseIfThenExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _34 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._148 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 20 */,
				ms._030 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._074 /* 1*NestedExpCS::ownedExpression=ExpCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._33,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _35 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._134 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 20 */,
				ms._013 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._014 /* 1*'Lambda' || «? » «value» «? » */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._055 /* 1*LambdaLiteralExpCS::ownedExpressionCS=ExpCS || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._48,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _36 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._001 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._036 /* 1*BooleanLiteralExpCS::symbol || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._6)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._6, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::ExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _37 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._158 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 83 */,
				ms._041 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._098 /* 1*TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._31) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(83, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _38 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._071 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._124 /* V00*StringLiteralExpCS::segments+=StringLiteral || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
		// EssentialOCL::ExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _39 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._117 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 7 */,
				ms._116 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 5 */,
				ms._002 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._046 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._077 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._038 /* 1*CollectionLiteralExpCS::ownedType=CollectionTypeCS || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._133 /* V00*steps-4..8 || «null» */,
				st._037 /* 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._037 /* 1*CollectionLiteralExpCS::ownedParts+=CollectionLiteralPartCS || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._28,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					iv._1) /* CollectionLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(7, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(5, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : 'self'
		private @NonNull SerializationRule _40 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._029 /* 1*'self' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS }
		private @NonNull SerializationRule _41 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._133 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : 57 */,
				ms._149 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 20 */,
				ms._032 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._012 /* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._054 /* 1*InfixExpCS::ownedLeft=PrefixedPrimaryExpCS || «null» */,
				st._067 /* 1*NamedElementCS::name=BinaryOperatorName || «? » «value» «? » */,
				st._076 /* 1*OperatorExpCS::ownedRight=ExpCS || «null» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					iv._19) /* PrefixedPrimaryExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._5) /* ExpCS */
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
					new RuleIndex_MultiplicativeCardinality(57, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _42 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._151 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 57 */,
				ms._032 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._068 /* 1*NamedElementCS::name=UnaryOperatorName || «? » «value» «? » */,
				st._078 /* 1*OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || «null» */
			},
			sl._39,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._19) /* PrefixedPrimaryExpCS */
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
					new RuleIndex_MultiplicativeCardinality(57, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::IfExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _43 = new SerializationRule(25,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._127 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 20|55 */,
				ms._129 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 13 */,
				ms._128 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 20 */,
				ms._130 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 20 */,
				ms._008 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._062 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._009 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._007 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._023 /* 1*'if' || «? » «value» «? » */,
				st._049 /* 1*IfExpCS::ownedCondition=ExpCS|PatternExpCS || «null» */,
				st._030 /* 1*'then' || «? » «value» «? » */,
				st._051 /* 1*IfExpCS::ownedThenExpression=ExpCS || «null» */,
				st._121 /* V00*IfExpCS::ownedIfThenExpressions+=ElseIfThenExpCS || «null» */,
				st._019 /* 1*'else' || «? » «value» «? » */,
				st._050 /* 1*IfExpCS::ownedElseExpression=ExpCS || «null» */,
				st._021 /* 1*'endif' || «? » «value» «? » */
			},
			sl._45,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._16) /* ExpCS|PatternExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._4) /* ElseIfThenExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::InvalidLiteralExpCS : 'invalid'
		private @NonNull SerializationRule _44 = new SerializationRule(27,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._025 /* 1*'invalid' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::LambdaLiteralExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _45 = new SerializationRule(30,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._134 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 20 */,
				ms._013 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._014 /* 1*'Lambda' || «? » «value» «? » */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._055 /* 1*LambdaLiteralExpCS::ownedExpressionCS=ExpCS || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._48,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::LetExpCS : { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
		private @NonNull SerializationRule _46 = new SerializationRule(31,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._136 /* check-rule essentialoclcs::LetExpCS.ownedVariables : 32 */,
				ms._135 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : 20 */,
				ms._014 /* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
				ms._048 /* assign V0 = (|LetExpCS::ownedVariables| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._026 /* 1*'let' || «? » «value» «? » */,
				st._057 /* 1*LetExpCS::ownedVariables+=LetVariableCS || «null» */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._057 /* 1*LetExpCS::ownedVariables+=LetVariableCS || «null» */,
				st._024 /* 1*'in' || «? » «value» «? » */,
				st._056 /* 1*LetExpCS::ownedInExpression=ExpCS || «null» */
			},
			sl._40,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					iv._7) /* LetVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(32, MultiplicativeCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::LetVariableCS : { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _47 = new SerializationRule(32,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._137 /* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 62 */,
				ms._162 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 20 */,
				ms._163 /* check-rule essentialoclcs::VariableCS.ownedType : 79 */,
				ms._045 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._092 /* assign V1 = |VariableCS::ownedType| */,
				ms._063 /* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._122 /* V00*LetVariableCS::ownedRoundBracketedClause=RoundBracketedClauseCS || «null» */,
				st._143 /* V01*steps-4..6 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._107 /* 1*VariableCS::ownedType=TypeExpCS || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._106 /* 1*VariableCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._42,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._20) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._28) /* TypeExpCS */
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
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::MapLiteralExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _48 = new SerializationRule(35,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._139 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 37 */,
				ms._138 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 36 */,
				ms._015 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._049 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._080 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._059 /* 1*MapLiteralExpCS::ownedType=MapTypeCS || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._133 /* V00*steps-4..8 || «null» */,
				st._058 /* 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._058 /* 1*MapLiteralExpCS::ownedParts+=MapLiteralPartCS || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._28,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					iv._9) /* MapTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					iv._8) /* MapLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(37, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(36, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::MapLiteralPartCS : { ownedKey=ExpCS '<-' ownedValue=ExpCS }
		private @NonNull SerializationRule _49 = new SerializationRule(36,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._141 /* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 20 */,
				ms._140 /* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 20 */,
				ms._017 /* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
				ms._016 /* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._060 /* 1*MapLiteralPartCS::ownedKey=ExpCS || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._061 /* 1*MapLiteralPartCS::ownedValue=ExpCS || «null» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::MapTypeCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
		private @NonNull SerializationRule _50 = new SerializationRule(37,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._143 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 79 */,
				ms._142 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 79 */,
				ms._064 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._019 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._018 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._015 /* 1*'Map' || «? » «value» «? » */,
				st._130 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._062 /* 1*MapTypeCS::ownedKeyType=TypeExpCS || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._063 /* 1*MapTypeCS::ownedValueType=TypeExpCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._20,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._4)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._28) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._28) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._4, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::Model : ownedExpression=ExpCS
		private @NonNull SerializationRule _51 = new SerializationRule(38,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._125 /* check-rule essentialoclcs::ContextCS.ownedExpression : 20 */,
				ms._006 /* assert (|ContextCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._047 /* 1*ContextCS::ownedExpression=ExpCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NameExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _52 = new SerializationRule(43,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._115 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 69 */,
				ms._113 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 54 */,
				ms._114 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 62 */,
				ms._112 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 9 */,
				ms._096 /* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				ms._094 /* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				ms._084 /* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				ms._058 /* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				ms._000 /* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._035 /* 1*AbstractNameExpCS::ownedPathName=PathNameCS || «null» */,
				st._120 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=SquareBracketedClauseCS || «null» */,
				st._138 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=RoundBracketedClauseCS || «null» */,
				st._151 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || «null» */,
				st._155 /* V03*steps-6..8 || «null» */,
				st._013 /* 1*'@' || «? » «value» «? » */,
				st._028 /* 1*'pre' || «? » «value» «? » */
			},
			sl._25,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._3)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._22) /* SquareBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._14) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._20) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._3) /* CurlyBracketedClauseCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._3, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(69, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(54, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(9, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _53 = new SerializationRule(44,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._147 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
				ms._026 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */
			},
			sl._39,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._28) /* TypeExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _54 = new SerializationRule(44,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._144 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._068 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._023 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._116 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._070 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || «null» */,
				st._135 /* V00*steps-5..7 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._30,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._11) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _55 = new SerializationRule(44,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._144 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._147 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
				ms._088 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				ms._067 /* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				ms._026 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._109 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */,
				st._135 /* V00*steps-5..7 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._070 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || «null» */,
				st._149 /* V01*steps-8..10 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._31,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._11) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._28) /* TypeExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : ownedNameExpression=NavigatingArgExpCS
		private @NonNull SerializationRule _56 = new SerializationRule(44,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._11) /* NavigatingArgExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _57 = new SerializationRule(44,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._144 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._147 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
				ms._024 /* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				ms._087 /* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				ms._069 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._109 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._070 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || «null» */,
				st._024 /* 1*'in' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._27,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._11) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._28) /* TypeExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingBarArgCS : { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _58 = new SerializationRule(46,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._147 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
				ms._069 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._029 /* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
				ms._088 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._032 /* 1*'|' || «? » «value» «? » */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._134 /* V00*steps-4..9 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */,
				st._148 /* V01*steps-7..9 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._41,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._7)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._11) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._28) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._7, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _59 = new SerializationRule(47,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._144 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._147 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
				ms._024 /* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				ms._087 /* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				ms._069 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._027 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._110 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */,
				st._148 /* V01*steps-7..9 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._070 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || «null» */,
				st._024 /* 1*'in' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._35,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._1)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._11) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._28) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._1, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _60 = new SerializationRule(47,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._144 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._147 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
				ms._088 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				ms._067 /* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				ms._026 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._027 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._110 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */,
				st._136 /* V00*steps-6..8 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._070 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || «null» */,
				st._150 /* V01*steps-9..11 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._37,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._1)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._11) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._28) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._1, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _61 = new SerializationRule(47,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._144 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._068 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._023 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._027 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._070 /* 1*NavigatingArgCS::ownedCoIterator=CoIteratorVariableCS || «null» */,
				st._136 /* V00*steps-6..8 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._36,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._1)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._11) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._0) /* CoIteratorVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._5) /* ExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._1, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS }
		private @NonNull SerializationRule _62 = new SerializationRule(47,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._027 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */
			},
			sl._34,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._1)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._11) /* NavigatingArgExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._1, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingSemiArgCS : { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _63 = new SerializationRule(48,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._147 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
				ms._069 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._028 /* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
				ms._088 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._009 /* 1*';' || «! » «value» «?\n» */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=NavigatingArgExpCS || «null» */,
				st._134 /* V00*steps-4..9 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=TypeExpCS || «null» */,
				st._148 /* V01*steps-7..9 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._38,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._2)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._11) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._28) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._2, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
	}
	private class _SerializationRules1
	{
		// EssentialOCL::NestedExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _64 = new SerializationRule(50,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._148 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 20 */,
				ms._030 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._074 /* 1*NestedExpCS::ownedExpression=ExpCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._33,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NullLiteralExpCS : 'null'
		private @NonNull SerializationRule _65 = new SerializationRule(52,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._027 /* 1*'null' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::NumberLiteralExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _66 = new SerializationRule(53,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._031 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._075 /* 1*NumberLiteralExpCS::symbol=NUMBER_LITERAL || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
		private @NonNull SerializationRule _67 = new SerializationRule(55,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._152 /* check-rule essentialoclcs::PatternExpCS.ownedPatternType : 79 */,
				ms._035 /* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
				ms._070 /* assign V0 = |PatternExpCS::patternVariableName| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._123 /* V00*PatternExpCS::patternVariableName=UnrestrictedName || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._086 /* 1*PatternExpCS::ownedPatternType=TypeExpCS || «null» */
			},
			sl._49,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					iv._28) /* TypeExpCS */
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
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedLetExpCS : { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		private @NonNull SerializationRule _68 = new SerializationRule(56,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._150 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 56 */,
				ms._032 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._068 /* 1*NamedElementCS::name=UnaryOperatorName || «? » «value» «? » */,
				st._077 /* 1*OperatorExpCS::ownedRight=PrefixedLetExpCS || «null» */
			},
			sl._39,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._17) /* PrefixedLetExpCS */
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
					new RuleIndex_MultiplicativeCardinality(56, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedPrimaryExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _69 = new SerializationRule(57,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._151 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 57 */,
				ms._032 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._068 /* 1*NamedElementCS::name=UnaryOperatorName || «? » «value» «? » */,
				st._078 /* 1*OperatorExpCS::ownedRight=PrefixedPrimaryExpCS || «null» */
			},
			sl._39,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._19) /* PrefixedPrimaryExpCS */
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
					new RuleIndex_MultiplicativeCardinality(57, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrimitiveTypeCS : name=PrimitiveTypeIdentifier
		private @NonNull SerializationRule _70 = new SerializationRule(60,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._036 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._087 /* 1*PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || «? » «value» «? » */
			},
			sl._50,
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
		private @NonNull SerializationRule _71 = new SerializationRule(62,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._153 /* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 44|46|47|48 */,
				ms._051 /* assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0) */,
				ms._081 /* assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._115 /* 1*steps-1..6 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._088 /* 1*RoundBracketedClauseCS::ownedArguments+=NavigatingArgCS || «null» */,
				st._140 /* V01*RoundBracketedClauseCS::ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._08,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					iv._12) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(46, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(47, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(48, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(44, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::SelfExpCS : 'self'
		private @NonNull SerializationRule _72 = new SerializationRule(66,
			new @NonNull CardinalitySolutionStep @NonNull [] {
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._029 /* 1*'self' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ShadowPartCS : ownedInitExpression=StringLiteralExpCS
		private @NonNull SerializationRule _73 = new SerializationRule(67,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._155 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 71 */,
				ms._037 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._090 /* 1*ShadowPartCS::ownedInitExpression=StringLiteralExpCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._23) /* StringLiteralExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(71, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ShadowPartCS : { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) }
		private @NonNull SerializationRule _74 = new SerializationRule(67,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._154 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 20|55 */,
				ms._037 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
				ms._038 /* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._091 /* 1*ShadowPartCS::referredProperty=UnrestrictedName || «? » «value» «? » */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._089 /* 1*ShadowPartCS::ownedInitExpression=ExpCS|PatternExpCS || «null» */
			},
			sl._49,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._16) /* ExpCS|PatternExpCS */
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
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::SimplePathNameCS : ownedPathElements+=FirstPathElementCS
		private @NonNull SerializationRule _75 = new SerializationRule(68,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._098 /* check-rule basecs::PathNameCS.ownedPathElements : 21 */,
				ms._034 /* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._083 /* 1*PathNameCS::ownedPathElements+=FirstPathElementCS || «null» */
			},
			sl._00,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._6) /* FirstPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(21, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::SquareBracketedClauseCS : { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' }
		private @NonNull SerializationRule _76 = new SerializationRule(69,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._156 /* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 20 */,
				ms._052 /* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._116 /* 1*steps-1..7 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._092 /* 1*SquareBracketedClauseCS::ownedTerms+=ExpCS || «null» */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._092 /* 1*SquareBracketedClauseCS::ownedTerms+=ExpCS || «null» */,
				st._018 /* 1*']' || «! » «value» */
			},
			sl._09,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::StringLiteralExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _77 = new SerializationRule(71,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._071 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._124 /* V00*StringLiteralExpCS::segments+=StringLiteral || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
		private @NonNull SerializationRule _78 = new SerializationRule(75,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._157 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 76 */,
				ms._055 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._016 /* 1*'Tuple' || «? » «value» «? » */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._096 /* 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || «null» */,
				st._135 /* V00*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._096 /* 1*TupleLiteralExpCS::ownedParts+=TupleLiteralPartCS || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._47,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._26) /* TupleLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(76, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::TupleLiteralPartCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _79 = new SerializationRule(76,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._162 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 20 */,
				ms._163 /* check-rule essentialoclcs::VariableCS.ownedType : 79 */,
				ms._045 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._075 /* assign V0 = |VariableCS::ownedType| */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._116 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._107 /* 1*VariableCS::ownedType=TypeExpCS || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._106 /* 1*VariableCS::ownedInitExpression=ExpCS || «null» */
			},
			sl._46,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._28) /* TypeExpCS */
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
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TuplePartCS : { name=UnrestrictedName ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _80 = new SerializationRule(77,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._107 /* check-rule basecs::TypedElementCS.ownedType : 79 */,
				ms._043 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*NamedElementCS::name=UnrestrictedName || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._103 /* 1*TypedElementCS::ownedType=TypeExpCS || «null» */
			},
			sl._49,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._28) /* TypeExpCS */
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
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TupleTypeCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
		private @NonNull SerializationRule _81 = new SerializationRule(78,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._105 /* check-rule basecs::TupleTypeCS.ownedParts : 77 */,
				ms._040 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._056 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._082 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._093 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._109 /* 1*steps-1..10 || «null» */,
				st._016 /* 1*'Tuple' || «? » «value» «? » */,
				st._126 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._146 /* V01*steps-5..9 || «null» */,
				st._097 /* 1*TupleTypeCS::ownedParts+=TuplePartCS || «null» */,
				st._153 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._097 /* 1*TupleTypeCS::ownedParts+=TuplePartCS || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._16,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._5)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._27) /* TuplePartCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._5, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(77, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _82 = new SerializationRule(79,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._143 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 79 */,
				ms._142 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 79 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._091 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._064 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._019 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._018 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || «null» */,
				st._015 /* 1*'Map' || «? » «value» «? » */,
				st._130 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._062 /* 1*MapTypeCS::ownedKeyType=TypeExpCS || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._063 /* 1*MapTypeCS::ownedValueType=TypeExpCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._142 /* V01*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._21,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._4)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._28) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._28) /* TypeExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._4, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _83 = new SerializationRule(79,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._123 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 40 */,
				ms._124 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 80 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._095 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._061 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._085 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._045 /* 1*CollectionTypeCS::name=CollectionTypeIdentifier || «? » «value» «? » */,
				st._129 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._046 /* 1*CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || «null» */,
				st._139 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._152 /* V02*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._19,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._10) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._29) /* TypeExpWithoutMultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(80, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _84 = new SerializationRule(79,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._073 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._036 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || «null» */,
				st._087 /* 1*PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || «? » «value» «? » */,
				st._125 /* V00*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._15,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _85 = new SerializationRule(79,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._122 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 7 */,
				ms._121 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 55 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._095 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._060 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._078 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._004 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._111 /* 1*steps-1..12 || «null» */,
				st._043 /* 1*CollectionPatternCS::ownedType=CollectionTypeCS || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._131 /* V00*steps-4..10 || «null» */,
				st._042 /* 1*CollectionPatternCS::ownedParts+=PatternExpCS || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._042 /* 1*CollectionPatternCS::ownedParts+=PatternExpCS || «null» */,
				st._004 /* 1*'++' || «? » «value» «? » */,
				st._044 /* 1*CollectionPatternCS::restVariableName=Identifier || «? » «value» «? » */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._152 /* V02*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._07,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._15) /* PatternExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(7, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _86 = new SerializationRule(79,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._105 /* check-rule basecs::TupleTypeCS.ownedParts : 77 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._097 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._040 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._056 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._082 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._093 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._110 /* 1*steps-1..11 || «null» */,
				st._016 /* 1*'Tuple' || «? » «value» «? » */,
				st._126 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._146 /* V01*steps-5..9 || «null» */,
				st._097 /* 1*TupleTypeCS::ownedParts+=TuplePartCS || «null» */,
				st._153 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._097 /* 1*TupleTypeCS::ownedParts+=TuplePartCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._154 /* V03*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._17,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._5)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._27) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._5, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(77, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _87 = new SerializationRule(79,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._159 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 9 */,
				ms._160 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 54 */,
				ms._161 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 20 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._095 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._072 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._042 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._090 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || «null» */,
				st._100 /* 1*TypeNameExpCS::ownedPathName=PathNameCS || «null» */,
				st._130 /* V00*steps-3..8 || «null» */,
				st._099 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || «null» */,
				st._145 /* V01*steps-5..8 || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._101 /* 1*TypeNameExpCS::ownedPatternGuard=ExpCS || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._152 /* V02*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._02,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._3) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._14) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(9, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(54, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _88 = new SerializationRule(82,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._158 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 83 */,
				ms._041 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._098 /* 1*TypeLiteralExpCS::ownedType=TypeLiteralWithMultiplicityCS || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._31) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(83, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _89 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._143 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 79 */,
				ms._142 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 79 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._091 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._064 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._019 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._018 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || «null» */,
				st._015 /* 1*'Map' || «? » «value» «? » */,
				st._130 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._062 /* 1*MapTypeCS::ownedKeyType=TypeExpCS || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._063 /* 1*MapTypeCS::ownedValueType=TypeExpCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._142 /* V01*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._21,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._4)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._28) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._28) /* TypeExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._4, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _90 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._123 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 40 */,
				ms._124 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 80 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._095 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._061 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._085 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._045 /* 1*CollectionTypeCS::name=CollectionTypeIdentifier || «? » «value» «? » */,
				st._129 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._046 /* 1*CollectionTypeCS::ownedType=TypeExpWithoutMultiplicityCS || «null» */,
				st._139 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=MultiplicityCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._152 /* V02*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._19,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._10) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._29) /* TypeExpWithoutMultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(80, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _91 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._073 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._036 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || «null» */,
				st._087 /* 1*PrimitiveTypeRefCS::name=PrimitiveTypeIdentifier || «? » «value» «? » */,
				st._125 /* V00*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._15,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
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
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _92 = new SerializationRule(83,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._105 /* check-rule basecs::TupleTypeCS.ownedParts : 77 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._097 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._040 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._056 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._082 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._093 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._110 /* 1*steps-1..11 || «null» */,
				st._016 /* 1*'Tuple' || «? » «value» «? » */,
				st._126 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._146 /* V01*steps-5..9 || «null» */,
				st._097 /* 1*TupleTypeCS::ownedParts+=TuplePartCS || «null» */,
				st._153 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._097 /* 1*TupleTypeCS::ownedParts+=TuplePartCS || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._154 /* V03*TypedRefCS::ownedMultiplicity=MultiplicityCS || «null» */
			},
			sl._17,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._5)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._27) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._5, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(77, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeNameExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
		private @NonNull SerializationRule _93 = new SerializationRule(84,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._159 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 9 */,
				ms._160 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 54 */,
				ms._161 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 20 */,
				ms._072 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._042 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._090 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._100 /* 1*TypeNameExpCS::ownedPathName=PathNameCS || «null» */,
				st._130 /* V00*steps-3..8 || «null» */,
				st._099 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=CurlyBracketedClauseCS || «null» */,
				st._145 /* V01*steps-5..8 || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._101 /* 1*TypeNameExpCS::ownedPatternGuard=ExpCS || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._01,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._3) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._14) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(9, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(54, MultiplicativeCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::URIFirstPathElementCS : referredElement=URI
		private @NonNull SerializationRule _94 = new SerializationRule(91,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._033 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._079 /* 1*PathElementCS::referredElement=URI || «? » «value» «? » */
			},
			sl._50,
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
		private @NonNull SerializationRule _95 = new SerializationRule(91,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._033 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._081 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._50,
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
		private @NonNull SerializationRule _96 = new SerializationRule(92,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._100 /* check-rule basecs::PathNameCS.ownedPathElements : 51|91 */,
				ms._050 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || «null» */,
				st._085 /* 1*PathNameCS::ownedPathElements+=URIFirstPathElementCS || «null» */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'::' || «! » «value» «! » */,
				st._084 /* 1*PathNameCS::ownedPathElements+=NextPathElementCS || «null» */
			},
			sl._03,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._39) /* NextPathElementCS|URIFirstPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(51, MultiplicativeCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(91, MultiplicativeCardinality.ONE)
					}
				)
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS : '*'
		private @NonNull SerializationRule _97 = new SerializationRule(94,
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
//	import EssentialOCLCSPackage;
