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
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValue_MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EAttribute_EnumerationValues;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndex_MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndexes;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EnumerationValue_MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.RuleIndex_MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepAssignKeyword;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepAssignedRuleCall;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepAssigns;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepCrossReference;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepLiteral;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepSequence;
import org.eclipse.ocl.xtext.base.cs2text.runtime.TerminalRuleValue;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;

public class EssentialOCLAnalysisProvider extends AbstractAnalysisProvider
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
				new GrammarRuleValue [] {
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
	private class _GrammarRuleVectors
	{
		private final @NonNull GrammarRuleVector _0 // CoIteratorVariableCS
			= new GrammarRuleVector(0x8L);
		private final @NonNull GrammarRuleVector _1 // CollectionLiteralPartCS
			= new GrammarRuleVector(0x20L);
		private final @NonNull GrammarRuleVector _2 // CollectionTypeCS
			= new GrammarRuleVector(0x80L);
		private final @NonNull GrammarRuleVector _3 // CurlyBracketedClauseCS
			= new GrammarRuleVector(0x200L);
		private final @NonNull GrammarRuleVector _4 // ElseIfThenExpCS
			= new GrammarRuleVector(0x2000L);
		private final @NonNull GrammarRuleVector _5 // ExpCS
			= new GrammarRuleVector(0x100000L);
		private final @NonNull GrammarRuleVector _6 // FirstPathElementCS
			= new GrammarRuleVector(0x200000L);
		private final @NonNull GrammarRuleVector _7 // LetVariableCS
			= new GrammarRuleVector(0x100000000L);
		private final @NonNull GrammarRuleVector _8 // MapLiteralPartCS
			= new GrammarRuleVector(0x1000000000L);
		private final @NonNull GrammarRuleVector _9 // MapTypeCS
			= new GrammarRuleVector(0x2000000000L);
		private final @NonNull GrammarRuleVector _10 // MultiplicityCS
			= new GrammarRuleVector(0x10000000000L);
		private final @NonNull GrammarRuleVector _11 // NavigatingArgExpCS
			= new GrammarRuleVector(0x200000000000L);
		private final @NonNull GrammarRuleVector _12 // NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
			= new GrammarRuleVector(0x1c00000000000L);
		private final @NonNull GrammarRuleVector _13 // NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
			= new GrammarRuleVector(0x1d00000000000L);
		private final @NonNull GrammarRuleVector _14 // FirstPathElementCS|NextPathElementCS
			= new GrammarRuleVector(0x8000000200000L);
		private final @NonNull GrammarRuleVector _15 // PathNameCS
			= new GrammarRuleVector(0x40000000000000L);
		private final @NonNull GrammarRuleVector _16 // PatternExpCS
			= new GrammarRuleVector(0x80000000000000L);
		private final @NonNull GrammarRuleVector _17 // ExpCS|PatternExpCS
			= new GrammarRuleVector(0x80000000100000L);
		private final @NonNull GrammarRuleVector _18 // PrefixedLetExpCS
			= new GrammarRuleVector(0x100000000000000L);
		private final @NonNull GrammarRuleVector _19 // LetExpCS|PrefixedLetExpCS
			= new GrammarRuleVector(0x100000080000000L);
		private final @NonNull GrammarRuleVector _20 // PrefixedPrimaryExpCS
			= new GrammarRuleVector(0x200000000000000L);
		private final @NonNull GrammarRuleVector _21 // RoundBracketedClauseCS
			= new GrammarRuleVector(0x4000000000000000L);
		private final @NonNull GrammarRuleVector _22 // ShadowPartCS
			= new GrammarRuleVector(0x0L,0x8L);
		private final @NonNull GrammarRuleVector _23 // SquareBracketedClauseCS
			= new GrammarRuleVector(0x0L,0x20L);
		private final @NonNull GrammarRuleVector _24 // StringLiteralExpCS
			= new GrammarRuleVector(0x0L,0x80L);
		private final @NonNull GrammarRuleVector _25 // TemplateBindingCS
			= new GrammarRuleVector(0x0L,0x100L);
		private final @NonNull GrammarRuleVector _26 // TemplateParameterSubstitutionCS
			= new GrammarRuleVector(0x0L,0x200L);
		private final @NonNull GrammarRuleVector _27 // TupleLiteralPartCS
			= new GrammarRuleVector(0x0L,0x1000L);
		private final @NonNull GrammarRuleVector _28 // TuplePartCS
			= new GrammarRuleVector(0x0L,0x2000L);
		private final @NonNull GrammarRuleVector _29 // TypeExpCS
			= new GrammarRuleVector(0x0L,0x8000L);
		private final @NonNull GrammarRuleVector _30 // TypeExpWithoutMultiplicityCS
			= new GrammarRuleVector(0x0L,0x10000L);
		private final @NonNull GrammarRuleVector _31 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
			= new GrammarRuleVector(0x1000002000000080L,0x24000L);
		private final @NonNull GrammarRuleVector _32 // TypeLiteralWithMultiplicityCS
			= new GrammarRuleVector(0x0L,0x80000L);
		private final @NonNull GrammarRuleVector _33 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS
			= new GrammarRuleVector(0x1000002000000080L,0xa4000L);
		private final @NonNull GrammarRuleVector _34 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new GrammarRuleVector(0x10000020000000c0L,0x134000L);
		private final @NonNull GrammarRuleVector _35 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new GrammarRuleVector(0x10000020000000c0L,0x13c000L);
		private final @NonNull GrammarRuleVector _36 // TypeParameterCS
			= new GrammarRuleVector(0x0L,0x200000L);
		private final @NonNull GrammarRuleVector _37 // TypeRefCS
			= new GrammarRuleVector(0x0L,0x400000L);
		private final @NonNull GrammarRuleVector _38 // TypedRefCS
			= new GrammarRuleVector(0x0L,0x800000L);
		private final @NonNull GrammarRuleVector _39 // TypedRefCS|TypedTypeRefCS
			= new GrammarRuleVector(0x0L,0x1800000L);
		private final @NonNull GrammarRuleVector _40 // NextPathElementCS|URIFirstPathElementCS
			= new GrammarRuleVector(0x8000000000000L,0x8000000L);
		private final @NonNull GrammarRuleVector _41 // FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
			= new GrammarRuleVector(0x8000000200000L,0x8000000L);
		private final @NonNull GrammarRuleVector _42 // BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0x830000008000004L,0x40000080L);
		private final @NonNull GrammarRuleVector _43 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0xc3408084a000014L,0x40040884L);
		private final @NonNull GrammarRuleVector _44 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0xe3408084a000014L,0x40040884L);
		private final @NonNull GrammarRuleVector _45 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0xf340808ca000014L,0x40040884L);
		private final @NonNull GrammarRuleVector _46 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0xf340808ca100014L,0x40040884L);
		private final @NonNull GrammarRuleVector _47 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0xf342808ca100014L,0x40040884L);
		private final @NonNull GrammarRuleVector _48 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0xfb40808ca100014L,0x40040884L);
		private final @NonNull GrammarRuleVector _49 // TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
			= new GrammarRuleVector(0x0L,0x401c00000L);
	}

	/**
	 * String combinations used by assigned String EAttributes
	 */
	private class _EnumValues
	{
		private final @NonNull EnumerationValue _0 // '*|+|?'
			= new EnumerationValueMultiple(new @NonNull String[]{"*", "+", "?"});
		private final @NonNull EnumerationValue _1 // ','
			= new EnumerationValueSingle(",");
		private final @NonNull EnumerationValue _2 // ';'
			= new EnumerationValueSingle(";");
		private final @NonNull EnumerationValue _3 // '@'
			= new EnumerationValueSingle("@");
		private final @NonNull EnumerationValue _4 // 'Map'
			= new EnumerationValueSingle("Map");
		private final @NonNull EnumerationValue _5 // 'Tuple'
			= new EnumerationValueSingle("Tuple");
		private final @NonNull EnumerationValue _6 // 'false|true'
			= new EnumerationValueMultiple(new @NonNull String[]{"false", "true"});
		private final @NonNull EnumerationValue _7 // '|'
			= new EnumerationValueSingle("|");
		private final @NonNull EnumerationValue _8 // '|1'
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
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, ev._3);
		private final @NonNull SerializationMatchTerm _004 // |AbstractNameExpCS::ownedCurlyBracketedClause|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull SerializationMatchTerm _005 // |AbstractNameExpCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _006 // |AbstractNameExpCS::ownedRoundBracketedClause|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull SerializationMatchTerm _007 // |AbstractNameExpCS::ownedSquareBracketedClauses|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		private final @NonNull SerializationMatchTerm _008 // |BooleanLiteralExpCS::symbol.'false|true'|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._6);
		private final @NonNull SerializationMatchTerm _009 // |CollectionLiteralExpCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _010 // |CollectionLiteralExpCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _011 // |CollectionLiteralPartCS::ownedExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull SerializationMatchTerm _012 // |CollectionLiteralPartCS::ownedLastExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		private final @NonNull SerializationMatchTerm _013 // |CollectionPatternCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _014 // |CollectionPatternCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _015 // |CollectionPatternCS::restVariableName|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		private final @NonNull SerializationMatchTerm _016 // |CollectionTypeCS::name|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		private final @NonNull SerializationMatchTerm _017 // |CollectionTypeCS::ownedCollectionMultiplicity|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		private final @NonNull SerializationMatchTerm _018 // |CollectionTypeCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _019 // |ContextCS::ownedExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		private final @NonNull SerializationMatchTerm _020 // |CurlyBracketedClauseCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _021 // |IfExpCS::ownedCondition|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		private final @NonNull SerializationMatchTerm _022 // |IfExpCS::ownedElseExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		private final @NonNull SerializationMatchTerm _023 // |IfExpCS::ownedIfThenExpressions|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		private final @NonNull SerializationMatchTerm _024 // |IfExpCS::ownedThenExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull SerializationMatchTerm _025 // |IfThenExpCS::ownedCondition|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		private final @NonNull SerializationMatchTerm _026 // |IfThenExpCS::ownedThenExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull SerializationMatchTerm _027 // |InfixExpCS::ownedLeft|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		private final @NonNull SerializationMatchTerm _028 // |LambdaLiteralExpCS::ownedExpressionCS|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		private final @NonNull SerializationMatchTerm _029 // |LetExpCS::ownedInExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		private final @NonNull SerializationMatchTerm _030 // |LetExpCS::ownedVariables|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		private final @NonNull SerializationMatchTerm _031 // |LetVariableCS::ownedRoundBracketedClause|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull SerializationMatchTerm _032 // |MapLiteralExpCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _033 // |MapLiteralExpCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _034 // |MapLiteralPartCS::ownedKey|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		private final @NonNull SerializationMatchTerm _035 // |MapLiteralPartCS::ownedValue|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		private final @NonNull SerializationMatchTerm _036 // |MapTypeCS::name.'Map'|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, ev._4);
		private final @NonNull SerializationMatchTerm _037 // |MapTypeCS::ownedKeyType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		private final @NonNull SerializationMatchTerm _038 // |MapTypeCS::ownedValueType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		private final @NonNull SerializationMatchTerm _039 // |MultiplicityBoundsCS::lowerBound|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull SerializationMatchTerm _040 // |MultiplicityBoundsCS::upperBound|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull SerializationMatchTerm _041 // |MultiplicityCS::isNullFree.'|1'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, ev._8);
		private final @NonNull SerializationMatchTerm _042 // |MultiplicityStringCS::stringBounds.'*|+|?'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._0);
		private final @NonNull SerializationMatchTerm _043 // |NamedElementCS::name|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull SerializationMatchTerm _044 // |NavigatingArgCS::ownedCoIterator|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		private final @NonNull SerializationMatchTerm _045 // |NavigatingArgCS::ownedInitExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull SerializationMatchTerm _046 // |NavigatingArgCS::ownedNameExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		private final @NonNull SerializationMatchTerm _047 // |NavigatingArgCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _048 // |NavigatingArgCS::prefix.','|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._1);
		private final @NonNull SerializationMatchTerm _049 // |NavigatingArgCS::prefix.';'|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._2);
		private final @NonNull SerializationMatchTerm _050 // |NavigatingArgCS::prefix.'|'|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._7);
		private final @NonNull SerializationMatchTerm _051 // |NestedExpCS::ownedExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		private final @NonNull SerializationMatchTerm _052 // |NumberLiteralExpCS::symbol|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull SerializationMatchTerm _053 // |OperatorExpCS::ownedRight|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull SerializationMatchTerm _054 // |PathElementCS::referredElement|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private final @NonNull SerializationMatchTerm _055 // |PathNameCS::ownedPathElements|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull SerializationMatchTerm _056 // |PatternExpCS::ownedPatternType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		private final @NonNull SerializationMatchTerm _057 // |PatternExpCS::patternVariableName|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		private final @NonNull SerializationMatchTerm _058 // |PrimitiveTypeRefCS::name|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		private final @NonNull SerializationMatchTerm _059 // |RoundBracketedClauseCS::ownedArguments|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		private final @NonNull SerializationMatchTerm _060 // |ShadowPartCS::ownedInitExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull SerializationMatchTerm _061 // |ShadowPartCS::referredProperty|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		private final @NonNull SerializationMatchTerm _062 // |SquareBracketedClauseCS::ownedTerms|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		private final @NonNull SerializationMatchTerm _063 // |StringLiteralExpCS::segments|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		private final @NonNull SerializationMatchTerm _064 // |TemplateBindingCS::ownedMultiplicity|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull SerializationMatchTerm _065 // |TemplateBindingCS::ownedSubstitutions|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull SerializationMatchTerm _066 // |TemplateParameterSubstitutionCS::ownedActualParameter|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull SerializationMatchTerm _067 // |TemplateSignatureCS::ownedParameters|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull SerializationMatchTerm _068 // |TupleLiteralExpCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _069 // |TupleTypeCS::name.'Tuple'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, ev._5);
		private final @NonNull SerializationMatchTerm _070 // |TupleTypeCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _071 // |TypeLiteralExpCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _072 // |TypeNameExpCS::ownedCurlyBracketedClause|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull SerializationMatchTerm _073 // |TypeNameExpCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _074 // |TypeNameExpCS::ownedPatternGuard|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		private final @NonNull SerializationMatchTerm _075 // |TypeParameterCS::ownedExtends|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull SerializationMatchTerm _076 // |TypedElementCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _077 // |TypedRefCS::ownedMultiplicity|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull SerializationMatchTerm _078 // |TypedTypeRefCS::ownedBinding|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull SerializationMatchTerm _079 // |TypedTypeRefCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _080 // |VariableCS::ownedInitExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull SerializationMatchTerm _081 // |VariableCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _082 // |WildcardTypeRefCS::ownedExtends|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull SerializationMatchTerm _083 // (|AbstractNameExpCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_005, _001);
		private final @NonNull SerializationMatchTerm _084 // (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
			= new SerializationMatchTermSubtract(_008, _001);
		private final @NonNull SerializationMatchTerm _085 // (|CollectionLiteralExpCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_009, _001);
		private final @NonNull SerializationMatchTerm _086 // (|CollectionLiteralExpCS::ownedParts| > 0)
			= new SerializationMatchTermGreaterThan(_009, _000);
		private final @NonNull SerializationMatchTerm _087 // (|CollectionLiteralExpCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_010, _001);
		private final @NonNull SerializationMatchTerm _088 // (|CollectionLiteralPartCS::ownedExpression| - 1)
			= new SerializationMatchTermSubtract(_011, _001);
		private final @NonNull SerializationMatchTerm _089 // (|CollectionPatternCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_013, _001);
		private final @NonNull SerializationMatchTerm _090 // (|CollectionPatternCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_014, _001);
		private final @NonNull SerializationMatchTerm _091 // (|CollectionTypeCS::name| - 1)
			= new SerializationMatchTermSubtract(_016, _001);
		private final @NonNull SerializationMatchTerm _092 // (|ContextCS::ownedExpression| - 1)
			= new SerializationMatchTermSubtract(_019, _001);
		private final @NonNull SerializationMatchTerm _093 // (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_020, _001);
		private final @NonNull SerializationMatchTerm _094 // (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new SerializationMatchTermGreaterThan(_020, _000);
		private final @NonNull SerializationMatchTerm _095 // (|IfExpCS::ownedCondition| - 1)
			= new SerializationMatchTermSubtract(_021, _001);
		private final @NonNull SerializationMatchTerm _096 // (|IfExpCS::ownedElseExpression| - 1)
			= new SerializationMatchTermSubtract(_022, _001);
		private final @NonNull SerializationMatchTerm _097 // (|IfExpCS::ownedThenExpression| - 1)
			= new SerializationMatchTermSubtract(_024, _001);
		private final @NonNull SerializationMatchTerm _098 // (|IfThenExpCS::ownedCondition| - 1)
			= new SerializationMatchTermSubtract(_025, _001);
		private final @NonNull SerializationMatchTerm _099 // (|IfThenExpCS::ownedThenExpression| - 1)
			= new SerializationMatchTermSubtract(_026, _001);
		private final @NonNull SerializationMatchTerm _100 // (|InfixExpCS::ownedLeft| - 1)
			= new SerializationMatchTermSubtract(_027, _001);
		private final @NonNull SerializationMatchTerm _101 // (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
			= new SerializationMatchTermSubtract(_028, _001);
		private final @NonNull SerializationMatchTerm _102 // (|LetExpCS::ownedInExpression| - 1)
			= new SerializationMatchTermSubtract(_029, _001);
		private final @NonNull SerializationMatchTerm _103 // (|LetExpCS::ownedVariables| - 1)
			= new SerializationMatchTermSubtract(_030, _001);
		private final @NonNull SerializationMatchTerm _104 // (|MapLiteralExpCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_032, _001);
		private final @NonNull SerializationMatchTerm _105 // (|MapLiteralExpCS::ownedParts| > 0)
			= new SerializationMatchTermGreaterThan(_032, _000);
		private final @NonNull SerializationMatchTerm _106 // (|MapLiteralExpCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_033, _001);
		private final @NonNull SerializationMatchTerm _107 // (|MapLiteralPartCS::ownedKey| - 1)
			= new SerializationMatchTermSubtract(_034, _001);
		private final @NonNull SerializationMatchTerm _108 // (|MapLiteralPartCS::ownedValue| - 1)
			= new SerializationMatchTermSubtract(_035, _001);
		private final @NonNull SerializationMatchTerm _109 // (|MapTypeCS::name.'Map'| - 1)
			= new SerializationMatchTermSubtract(_036, _001);
		private final @NonNull SerializationMatchTerm _110 // (|MapTypeCS::ownedKeyType| - V0)
			= new SerializationMatchTermSubtract(_037, _002);
		private final @NonNull SerializationMatchTerm _111 // (|MultiplicityBoundsCS::lowerBound| - 1)
			= new SerializationMatchTermSubtract(_039, _001);
		private final @NonNull SerializationMatchTerm _112 // (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
			= new SerializationMatchTermSubtract(_042, _001);
		private final @NonNull SerializationMatchTerm _113 // (|NamedElementCS::name| - 1)
			= new SerializationMatchTermSubtract(_043, _001);
		private final @NonNull SerializationMatchTerm _114 // (|NavigatingArgCS::ownedCoIterator| - 1)
			= new SerializationMatchTermSubtract(_044, _001);
		private final @NonNull SerializationMatchTerm _115 // (|NavigatingArgCS::ownedInitExpression| - 1)
			= new SerializationMatchTermSubtract(_045, _001);
		private final @NonNull SerializationMatchTerm _116 // (|NavigatingArgCS::ownedNameExpression| - 1)
			= new SerializationMatchTermSubtract(_046, _001);
		private final @NonNull SerializationMatchTerm _117 // (|NavigatingArgCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_047, _001);
		private final @NonNull SerializationMatchTerm _118 // (|NavigatingArgCS::prefix.','| - 1)
			= new SerializationMatchTermSubtract(_048, _001);
		private final @NonNull SerializationMatchTerm _119 // (|NavigatingArgCS::prefix.';'| - 1)
			= new SerializationMatchTermSubtract(_049, _001);
		private final @NonNull SerializationMatchTerm _120 // (|NavigatingArgCS::prefix.'|'| - 1)
			= new SerializationMatchTermSubtract(_050, _001);
		private final @NonNull SerializationMatchTerm _121 // (|NestedExpCS::ownedExpression| - 1)
			= new SerializationMatchTermSubtract(_051, _001);
		private final @NonNull SerializationMatchTerm _122 // (|NumberLiteralExpCS::symbol| - 1)
			= new SerializationMatchTermSubtract(_052, _001);
		private final @NonNull SerializationMatchTerm _123 // (|OperatorExpCS::ownedRight| - 1)
			= new SerializationMatchTermSubtract(_053, _001);
		private final @NonNull SerializationMatchTerm _124 // (|PathElementCS::referredElement| - 1)
			= new SerializationMatchTermSubtract(_054, _001);
		private final @NonNull SerializationMatchTerm _125 // (|PathNameCS::ownedPathElements| - 1)
			= new SerializationMatchTermSubtract(_055, _001);
		private final @NonNull SerializationMatchTerm _126 // (|PatternExpCS::ownedPatternType| - 1)
			= new SerializationMatchTermSubtract(_056, _001);
		private final @NonNull SerializationMatchTerm _127 // (|PrimitiveTypeRefCS::name| - 1)
			= new SerializationMatchTermSubtract(_058, _001);
		private final @NonNull SerializationMatchTerm _128 // (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new SerializationMatchTermSubtract(_059, _001);
		private final @NonNull SerializationMatchTerm _129 // (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new SerializationMatchTermGreaterThan(_059, _000);
		private final @NonNull SerializationMatchTerm _130 // (|ShadowPartCS::ownedInitExpression| - 1)
			= new SerializationMatchTermSubtract(_060, _001);
		private final @NonNull SerializationMatchTerm _131 // (|ShadowPartCS::referredProperty| - 1)
			= new SerializationMatchTermSubtract(_061, _001);
		private final @NonNull SerializationMatchTerm _132 // (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new SerializationMatchTermSubtract(_062, _001);
		private final @NonNull SerializationMatchTerm _133 // (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new SerializationMatchTermSubtract(_065, _001);
		private final @NonNull SerializationMatchTerm _134 // (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
			= new SerializationMatchTermSubtract(_066, _001);
		private final @NonNull SerializationMatchTerm _135 // (|TemplateSignatureCS::ownedParameters| - 1)
			= new SerializationMatchTermSubtract(_067, _001);
		private final @NonNull SerializationMatchTerm _136 // (|TupleLiteralExpCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_068, _001);
		private final @NonNull SerializationMatchTerm _137 // (|TupleTypeCS::name.'Tuple'| - 1)
			= new SerializationMatchTermSubtract(_069, _001);
		private final @NonNull SerializationMatchTerm _138 // (|TupleTypeCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_070, _001);
		private final @NonNull SerializationMatchTerm _139 // (|TupleTypeCS::ownedParts| > 0)
			= new SerializationMatchTermGreaterThan(_070, _000);
		private final @NonNull SerializationMatchTerm _140 // (|TypeLiteralExpCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_071, _001);
		private final @NonNull SerializationMatchTerm _141 // (|TypeNameExpCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_073, _001);
		private final @NonNull SerializationMatchTerm _142 // (|TypeParameterCS::ownedExtends| - 1)
			= new SerializationMatchTermSubtract(_075, _001);
		private final @NonNull SerializationMatchTerm _143 // (|TypeParameterCS::ownedExtends| > 0)
			= new SerializationMatchTermGreaterThan(_075, _000);
		private final @NonNull SerializationMatchTerm _144 // (|TypedElementCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_076, _001);
		private final @NonNull SerializationMatchTerm _145 // (|TypedTypeRefCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_079, _001);
		private final @NonNull SerializationMatchTerm _146 // (|VariableCS::ownedInitExpression| - 1)
			= new SerializationMatchTermSubtract(_080, _001);
	}

	/**
	 * Steps for the matching process.
	 */
	private class _MatchSteps
	{
		private final @NonNull SerializationMatchStep _000 // assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._083);
		private final @NonNull SerializationMatchStep _001 // assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
			= new MatchStep_Assert(mt._084);
		private final @NonNull SerializationMatchStep _002 // assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._087);
		private final @NonNull SerializationMatchStep _003 // assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
			= new MatchStep_Assert(mt._088);
		private final @NonNull SerializationMatchStep _004 // assert (|CollectionPatternCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._090);
		private final @NonNull SerializationMatchStep _005 // assert (|CollectionTypeCS::name| - 1) == 0
			= new MatchStep_Assert(mt._091);
		private final @NonNull SerializationMatchStep _006 // assert (|ContextCS::ownedExpression| - 1) == 0
			= new MatchStep_Assert(mt._092);
		private final @NonNull SerializationMatchStep _007 // assert (|IfExpCS::ownedCondition| - 1) == 0
			= new MatchStep_Assert(mt._095);
		private final @NonNull SerializationMatchStep _008 // assert (|IfExpCS::ownedElseExpression| - 1) == 0
			= new MatchStep_Assert(mt._096);
		private final @NonNull SerializationMatchStep _009 // assert (|IfExpCS::ownedThenExpression| - 1) == 0
			= new MatchStep_Assert(mt._097);
		private final @NonNull SerializationMatchStep _010 // assert (|IfThenExpCS::ownedCondition| - 1) == 0
			= new MatchStep_Assert(mt._098);
		private final @NonNull SerializationMatchStep _011 // assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
			= new MatchStep_Assert(mt._099);
		private final @NonNull SerializationMatchStep _012 // assert (|InfixExpCS::ownedLeft| - 1) == 0
			= new MatchStep_Assert(mt._100);
		private final @NonNull SerializationMatchStep _013 // assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
			= new MatchStep_Assert(mt._101);
		private final @NonNull SerializationMatchStep _014 // assert (|LetExpCS::ownedInExpression| - 1) == 0
			= new MatchStep_Assert(mt._102);
		private final @NonNull SerializationMatchStep _015 // assert (|MapLiteralExpCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._106);
		private final @NonNull SerializationMatchStep _016 // assert (|MapLiteralPartCS::ownedKey| - 1) == 0
			= new MatchStep_Assert(mt._107);
		private final @NonNull SerializationMatchStep _017 // assert (|MapLiteralPartCS::ownedValue| - 1) == 0
			= new MatchStep_Assert(mt._108);
		private final @NonNull SerializationMatchStep _018 // assert (|MapTypeCS::name.'Map'| - 1) == 0
			= new MatchStep_Assert(mt._109);
		private final @NonNull SerializationMatchStep _019 // assert (|MapTypeCS::ownedKeyType| - V0) == 0
			= new MatchStep_Assert(mt._110);
		private final @NonNull SerializationMatchStep _020 // assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
			= new MatchStep_Assert(mt._111);
		private final @NonNull SerializationMatchStep _021 // assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
			= new MatchStep_Assert(mt._112);
		private final @NonNull SerializationMatchStep _022 // assert (|NamedElementCS::name| - 1) == 0
			= new MatchStep_Assert(mt._113);
		private final @NonNull SerializationMatchStep _023 // assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
			= new MatchStep_Assert(mt._114);
		private final @NonNull SerializationMatchStep _024 // assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
			= new MatchStep_Assert(mt._115);
		private final @NonNull SerializationMatchStep _025 // assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
			= new MatchStep_Assert(mt._116);
		private final @NonNull SerializationMatchStep _026 // assert (|NavigatingArgCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._117);
		private final @NonNull SerializationMatchStep _027 // assert (|NavigatingArgCS::prefix.','| - 1) == 0
			= new MatchStep_Assert(mt._118);
		private final @NonNull SerializationMatchStep _028 // assert (|NavigatingArgCS::prefix.';'| - 1) == 0
			= new MatchStep_Assert(mt._119);
		private final @NonNull SerializationMatchStep _029 // assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
			= new MatchStep_Assert(mt._120);
		private final @NonNull SerializationMatchStep _030 // assert (|NestedExpCS::ownedExpression| - 1) == 0
			= new MatchStep_Assert(mt._121);
		private final @NonNull SerializationMatchStep _031 // assert (|NumberLiteralExpCS::symbol| - 1) == 0
			= new MatchStep_Assert(mt._122);
		private final @NonNull SerializationMatchStep _032 // assert (|OperatorExpCS::ownedRight| - 1) == 0
			= new MatchStep_Assert(mt._123);
		private final @NonNull SerializationMatchStep _033 // assert (|PathElementCS::referredElement| - 1) == 0
			= new MatchStep_Assert(mt._124);
		private final @NonNull SerializationMatchStep _034 // assert (|PathNameCS::ownedPathElements| - 1) == 0
			= new MatchStep_Assert(mt._125);
		private final @NonNull SerializationMatchStep _035 // assert (|PatternExpCS::ownedPatternType| - 1) == 0
			= new MatchStep_Assert(mt._126);
		private final @NonNull SerializationMatchStep _036 // assert (|PrimitiveTypeRefCS::name| - 1) == 0
			= new MatchStep_Assert(mt._127);
		private final @NonNull SerializationMatchStep _037 // assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
			= new MatchStep_Assert(mt._130);
		private final @NonNull SerializationMatchStep _038 // assert (|ShadowPartCS::referredProperty| - 1) == 0
			= new MatchStep_Assert(mt._131);
		private final @NonNull SerializationMatchStep _039 // assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
			= new MatchStep_Assert(mt._134);
		private final @NonNull SerializationMatchStep _040 // assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
			= new MatchStep_Assert(mt._137);
		private final @NonNull SerializationMatchStep _041 // assert (|TypeLiteralExpCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._140);
		private final @NonNull SerializationMatchStep _042 // assert (|TypeNameExpCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._141);
		private final @NonNull SerializationMatchStep _043 // assert (|TypedElementCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._144);
		private final @NonNull SerializationMatchStep _044 // assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._145);
		private final @NonNull SerializationMatchStep _045 // assert (|VariableCS::ownedInitExpression| - 1) == 0
			= new MatchStep_Assert(mt._146);
		private final @NonNull SerializationMatchStep _046 // assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
			= new MatchStep_Assign(0, mt._086);
		private final @NonNull SerializationMatchStep _047 // assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new MatchStep_Assign(0, mt._094);
		private final @NonNull SerializationMatchStep _048 // assign V0 = (|LetExpCS::ownedVariables| - 1)
			= new MatchStep_Assign(0, mt._103);
		private final @NonNull SerializationMatchStep _049 // assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
			= new MatchStep_Assign(0, mt._105);
		private final @NonNull SerializationMatchStep _050 // assign V0 = (|PathNameCS::ownedPathElements| - 1)
			= new MatchStep_Assign(0, mt._125);
		private final @NonNull SerializationMatchStep _051 // assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new MatchStep_Assign(0, mt._129);
		private final @NonNull SerializationMatchStep _052 // assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new MatchStep_Assign(0, mt._132);
		private final @NonNull SerializationMatchStep _053 // assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new MatchStep_Assign(0, mt._133);
		private final @NonNull SerializationMatchStep _054 // assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
			= new MatchStep_Assign(0, mt._135);
		private final @NonNull SerializationMatchStep _055 // assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
			= new MatchStep_Assign(0, mt._136);
		private final @NonNull SerializationMatchStep _056 // assign V0 = (|TupleTypeCS::ownedParts| > 0)
			= new MatchStep_Assign(0, mt._139);
		private final @NonNull SerializationMatchStep _057 // assign V0 = (|TypeParameterCS::ownedExtends| > 0)
			= new MatchStep_Assign(0, mt._143);
		private final @NonNull SerializationMatchStep _058 // assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
			= new MatchStep_Assign(0, mt._007);
		private final @NonNull SerializationMatchStep _059 // assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
			= new MatchStep_Assign(0, mt._012);
		private final @NonNull SerializationMatchStep _060 // assign V0 = |CollectionPatternCS::restVariableName|
			= new MatchStep_Assign(0, mt._015);
		private final @NonNull SerializationMatchStep _061 // assign V0 = |CollectionTypeCS::ownedType|
			= new MatchStep_Assign(0, mt._018);
		private final @NonNull SerializationMatchStep _062 // assign V0 = |IfExpCS::ownedIfThenExpressions|
			= new MatchStep_Assign(0, mt._023);
		private final @NonNull SerializationMatchStep _063 // assign V0 = |LetVariableCS::ownedRoundBracketedClause|
			= new MatchStep_Assign(0, mt._031);
		private final @NonNull SerializationMatchStep _064 // assign V0 = |MapTypeCS::ownedValueType|
			= new MatchStep_Assign(0, mt._038);
		private final @NonNull SerializationMatchStep _065 // assign V0 = |MultiplicityBoundsCS::upperBound|
			= new MatchStep_Assign(0, mt._040);
		private final @NonNull SerializationMatchStep _066 // assign V0 = |MultiplicityCS::isNullFree.'|1'|
			= new MatchStep_Assign(0, mt._041);
		private final @NonNull SerializationMatchStep _067 // assign V0 = |NavigatingArgCS::ownedCoIterator|
			= new MatchStep_Assign(0, mt._044);
		private final @NonNull SerializationMatchStep _068 // assign V0 = |NavigatingArgCS::ownedInitExpression|
			= new MatchStep_Assign(0, mt._045);
		private final @NonNull SerializationMatchStep _069 // assign V0 = |NavigatingArgCS::ownedType|
			= new MatchStep_Assign(0, mt._047);
		private final @NonNull SerializationMatchStep _070 // assign V0 = |PatternExpCS::patternVariableName|
			= new MatchStep_Assign(0, mt._057);
		private final @NonNull SerializationMatchStep _071 // assign V0 = |StringLiteralExpCS::segments|
			= new MatchStep_Assign(0, mt._063);
		private final @NonNull SerializationMatchStep _072 // assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
			= new MatchStep_Assign(0, mt._072);
		private final @NonNull SerializationMatchStep _073 // assign V0 = |TypedRefCS::ownedMultiplicity|
			= new MatchStep_Assign(0, mt._077);
		private final @NonNull SerializationMatchStep _074 // assign V0 = |TypedTypeRefCS::ownedBinding|
			= new MatchStep_Assign(0, mt._078);
		private final @NonNull SerializationMatchStep _075 // assign V0 = |VariableCS::ownedType|
			= new MatchStep_Assign(0, mt._081);
		private final @NonNull SerializationMatchStep _076 // assign V0 = |WildcardTypeRefCS::ownedExtends|
			= new MatchStep_Assign(0, mt._082);
		private final @NonNull SerializationMatchStep _077 // assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
			= new MatchStep_Assign(1, mt._085);
		private final @NonNull SerializationMatchStep _078 // assign V1 = (|CollectionPatternCS::ownedParts| - 1)
			= new MatchStep_Assign(1, mt._089);
		private final @NonNull SerializationMatchStep _079 // assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new MatchStep_Assign(1, mt._093);
		private final @NonNull SerializationMatchStep _080 // assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
			= new MatchStep_Assign(1, mt._104);
		private final @NonNull SerializationMatchStep _081 // assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new MatchStep_Assign(1, mt._128);
		private final @NonNull SerializationMatchStep _082 // assign V1 = (|TupleTypeCS::ownedParts| > 0)
			= new MatchStep_Assign(1, mt._139);
		private final @NonNull SerializationMatchStep _083 // assign V1 = (|TypeParameterCS::ownedExtends| - 1)
			= new MatchStep_Assign(1, mt._142);
		private final @NonNull SerializationMatchStep _084 // assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
			= new MatchStep_Assign(1, mt._006);
		private final @NonNull SerializationMatchStep _085 // assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
			= new MatchStep_Assign(1, mt._017);
		private final @NonNull SerializationMatchStep _086 // assign V1 = |MultiplicityCS::isNullFree.'|1'|
			= new MatchStep_Assign(1, mt._041);
		private final @NonNull SerializationMatchStep _087 // assign V1 = |NavigatingArgCS::ownedCoIterator|
			= new MatchStep_Assign(1, mt._044);
		private final @NonNull SerializationMatchStep _088 // assign V1 = |NavigatingArgCS::ownedInitExpression|
			= new MatchStep_Assign(1, mt._045);
		private final @NonNull SerializationMatchStep _089 // assign V1 = |TemplateBindingCS::ownedMultiplicity|
			= new MatchStep_Assign(1, mt._064);
		private final @NonNull SerializationMatchStep _090 // assign V1 = |TypeNameExpCS::ownedPatternGuard|
			= new MatchStep_Assign(1, mt._074);
		private final @NonNull SerializationMatchStep _091 // assign V1 = |TypedRefCS::ownedMultiplicity|
			= new MatchStep_Assign(1, mt._077);
		private final @NonNull SerializationMatchStep _092 // assign V1 = |VariableCS::ownedType|
			= new MatchStep_Assign(1, mt._081);
		private final @NonNull SerializationMatchStep _093 // assign V2 = (|TupleTypeCS::ownedParts| - 1)
			= new MatchStep_Assign(2, mt._138);
		private final @NonNull SerializationMatchStep _094 // assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
			= new MatchStep_Assign(2, mt._004);
		private final @NonNull SerializationMatchStep _095 // assign V2 = |TypedRefCS::ownedMultiplicity|
			= new MatchStep_Assign(2, mt._077);
		private final @NonNull SerializationMatchStep _096 // assign V3 = |AbstractNameExpCS::isPre.'@'|
			= new MatchStep_Assign(3, mt._003);
		private final @NonNull SerializationMatchStep _097 // assign V3 = |TypedRefCS::ownedMultiplicity|
			= new MatchStep_Assign(3, mt._077);
		private final @NonNull SerializationMatchStep _098 // check-rule basecs::PathNameCS.ownedPathElements : 21
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._6/*FirstPathElementCS*/);
		private final @NonNull SerializationMatchStep _099 // check-rule basecs::PathNameCS.ownedPathElements : 21|51
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._14/*FirstPathElementCS|NextPathElementCS*/);
		private final @NonNull SerializationMatchStep _100 // check-rule basecs::PathNameCS.ownedPathElements : 51|91
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._40/*NextPathElementCS|URIFirstPathElementCS*/);
		private final @NonNull SerializationMatchStep _101 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : 40
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, iv._10/*MultiplicityCS*/);
		private final @NonNull SerializationMatchStep _102 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : 73
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, iv._26/*TemplateParameterSubstitutionCS*/);
		private final @NonNull SerializationMatchStep _103 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 86
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, iv._37/*TypeRefCS*/);
		private final @NonNull SerializationMatchStep _104 // check-rule basecs::TemplateSignatureCS.ownedParameters : 85
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, iv._36/*TypeParameterCS*/);
		private final @NonNull SerializationMatchStep _105 // check-rule basecs::TupleTypeCS.ownedParts : 77
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, iv._28/*TuplePartCS*/);
		private final @NonNull SerializationMatchStep _106 // check-rule basecs::TypeParameterCS.ownedExtends : 87
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, iv._38/*TypedRefCS*/);
		private final @NonNull SerializationMatchStep _107 // check-rule basecs::TypedElementCS.ownedType : 79
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, iv._29/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _108 // check-rule basecs::TypedRefCS.ownedMultiplicity : 40
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, iv._10/*MultiplicityCS*/);
		private final @NonNull SerializationMatchStep _109 // check-rule basecs::TypedTypeRefCS.ownedBinding : 72
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, iv._25/*TemplateBindingCS*/);
		private final @NonNull SerializationMatchStep _110 // check-rule basecs::TypedTypeRefCS.ownedPathName : 54
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, iv._15/*PathNameCS*/);
		private final @NonNull SerializationMatchStep _111 // check-rule basecs::WildcardTypeRefCS.ownedExtends : 87
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, iv._38/*TypedRefCS*/);
		private final @NonNull SerializationMatchStep _112 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 9
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._3/*CurlyBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _113 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 54
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, iv._15/*PathNameCS*/);
		private final @NonNull SerializationMatchStep _114 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 62
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._21/*RoundBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _115 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 69
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, iv._23/*SquareBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _116 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 5
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, iv._1/*CollectionLiteralPartCS*/);
		private final @NonNull SerializationMatchStep _117 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 7
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, iv._2/*CollectionTypeCS*/);
		private final @NonNull SerializationMatchStep _118 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _119 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 55
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._16/*PatternExpCS*/);
		private final @NonNull SerializationMatchStep _120 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _121 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : 55
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, iv._16/*PatternExpCS*/);
		private final @NonNull SerializationMatchStep _122 // check-rule essentialoclcs::CollectionPatternCS.ownedType : 7
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, iv._2/*CollectionTypeCS*/);
		private final @NonNull SerializationMatchStep _123 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 40
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, iv._10/*MultiplicityCS*/);
		private final @NonNull SerializationMatchStep _124 // check-rule essentialoclcs::CollectionTypeCS.ownedType : 80
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, iv._30/*TypeExpWithoutMultiplicityCS*/);
		private final @NonNull SerializationMatchStep _125 // check-rule essentialoclcs::ContextCS.ownedExpression : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _126 // check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 67
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, iv._22/*ShadowPartCS*/);
		private final @NonNull SerializationMatchStep _127 // check-rule essentialoclcs::IfExpCS.ownedCondition : 20|55
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, iv._17/*ExpCS|PatternExpCS*/);
		private final @NonNull SerializationMatchStep _128 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _129 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 13
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, iv._4/*ElseIfThenExpCS*/);
		private final @NonNull SerializationMatchStep _130 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _131 // check-rule essentialoclcs::IfThenExpCS.ownedCondition : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _132 // check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _133 // check-rule essentialoclcs::InfixExpCS.ownedLeft : 57
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, iv._20/*PrefixedPrimaryExpCS*/);
		private final @NonNull SerializationMatchStep _134 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _135 // check-rule essentialoclcs::LetExpCS.ownedInExpression : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _136 // check-rule essentialoclcs::LetExpCS.ownedVariables : 32
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, iv._7/*LetVariableCS*/);
		private final @NonNull SerializationMatchStep _137 // check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 62
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._21/*RoundBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _138 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 36
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, iv._8/*MapLiteralPartCS*/);
		private final @NonNull SerializationMatchStep _139 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : 37
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, iv._9/*MapTypeCS*/);
		private final @NonNull SerializationMatchStep _140 // check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _141 // check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _142 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : 79
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, iv._29/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _143 // check-rule essentialoclcs::MapTypeCS.ownedValueType : 79
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, iv._29/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _144 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, iv._0/*CoIteratorVariableCS*/);
		private final @NonNull SerializationMatchStep _145 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _146 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, iv._11/*NavigatingArgExpCS*/);
		private final @NonNull SerializationMatchStep _147 // check-rule essentialoclcs::NavigatingArgCS.ownedType : 79
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, iv._29/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _148 // check-rule essentialoclcs::NestedExpCS.ownedExpression : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _149 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _150 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 56
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._18/*PrefixedLetExpCS*/);
		private final @NonNull SerializationMatchStep _151 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 57
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._20/*PrefixedPrimaryExpCS*/);
		private final @NonNull SerializationMatchStep _152 // check-rule essentialoclcs::PatternExpCS.ownedPatternType : 79
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, iv._29/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _153 // check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 44|46|47|48
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, iv._13/*NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS*/);
		private final @NonNull SerializationMatchStep _154 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 20|55
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._17/*ExpCS|PatternExpCS*/);
		private final @NonNull SerializationMatchStep _155 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 71
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._24/*StringLiteralExpCS*/);
		private final @NonNull SerializationMatchStep _156 // check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _157 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 76
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, iv._27/*TupleLiteralPartCS*/);
		private final @NonNull SerializationMatchStep _158 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 83
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, iv._32/*TypeLiteralWithMultiplicityCS*/);
		private final @NonNull SerializationMatchStep _159 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 9
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._3/*CurlyBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _160 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 54
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, iv._15/*PathNameCS*/);
		private final @NonNull SerializationMatchStep _161 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _162 // check-rule essentialoclcs::VariableCS.ownedInitExpression : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, iv._5/*ExpCS*/);
		private final @NonNull SerializationMatchStep _163 // check-rule essentialoclcs::VariableCS.ownedType : 79
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, iv._29/*TypeExpCS*/);
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
		private final @NonNull SerializationStepLiteral _010 // 1*'<-'
									= new SerializationStepLiteral(-1, "<-");
		private final @NonNull SerializationStepLiteral _011 // 1*'='
									= new SerializationStepLiteral(-1, "=");
		private final @NonNull SerializationStepLiteral _012 // 1*'?'
									= new SerializationStepLiteral(-1, "?");
		private final @NonNull SerializationStepLiteral _013 // 1*'@'
									= new SerializationStepLiteral(-1, "@");
		private final @NonNull SerializationStepLiteral _014 // 1*'Lambda'
									= new SerializationStepLiteral(-1, "Lambda");
		private final @NonNull SerializationStepLiteral _015 // 1*'Map'
									= new SerializationStepLiteral(-1, "Map");
		private final @NonNull SerializationStepLiteral _016 // 1*'Tuple'
									= new SerializationStepLiteral(-1, "Tuple");
		private final @NonNull SerializationStepLiteral _017 // 1*'['
									= new SerializationStepLiteral(-1, "[");
		private final @NonNull SerializationStepLiteral _018 // 1*']'
									= new SerializationStepLiteral(-1, "]");
		private final @NonNull SerializationStepLiteral _019 // 1*'else'
									= new SerializationStepLiteral(-1, "else");
		private final @NonNull SerializationStepLiteral _020 // 1*'elseif'
									= new SerializationStepLiteral(-1, "elseif");
		private final @NonNull SerializationStepLiteral _021 // 1*'endif'
									= new SerializationStepLiteral(-1, "endif");
		private final @NonNull SerializationStepLiteral _022 // 1*'extends'
									= new SerializationStepLiteral(-1, "extends");
		private final @NonNull SerializationStepLiteral _023 // 1*'if'
									= new SerializationStepLiteral(-1, "if");
		private final @NonNull SerializationStepLiteral _024 // 1*'in'
									= new SerializationStepLiteral(-1, "in");
		private final @NonNull SerializationStepLiteral _025 // 1*'invalid'
									= new SerializationStepLiteral(-1, "invalid");
		private final @NonNull SerializationStepLiteral _026 // 1*'let'
									= new SerializationStepLiteral(-1, "let");
		private final @NonNull SerializationStepLiteral _027 // 1*'null'
									= new SerializationStepLiteral(-1, "null");
		private final @NonNull SerializationStepLiteral _028 // 1*'pre'
									= new SerializationStepLiteral(-1, "pre");
		private final @NonNull SerializationStepLiteral _029 // 1*'self'
									= new SerializationStepLiteral(-1, "self");
		private final @NonNull SerializationStepLiteral _030 // 1*'then'
									= new SerializationStepLiteral(-1, "then");
		private final @NonNull SerializationStepLiteral _031 // 1*'{'
									= new SerializationStepLiteral(-1, "{");
		private final @NonNull SerializationStepLiteral _032 // 1*'|'
									= new SerializationStepLiteral(-1, "|");
		private final @NonNull SerializationStepLiteral _033 // 1*'|?'
									= new SerializationStepLiteral(-1, "|?");
		private final @NonNull SerializationStepLiteral _034 // 1*'}'
									= new SerializationStepLiteral(-1, "}");
		private final @NonNull SerializationStepAssignedRuleCall _035 // 1*AbstractNameExpCS::ownedPathName=54
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 54 /* PathNameCS */);
		private final @NonNull SerializationStepAssignKeyword _036 // 1*BooleanLiteralExpCS::symbol='false|true'
									= new SerializationStepAssignKeyword(-1, EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._6);
		private final @NonNull SerializationStepAssignedRuleCall _037 // 1*CollectionLiteralExpCS::ownedParts+=5
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 5 /* CollectionLiteralPartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _038 // 1*CollectionLiteralExpCS::ownedType=7
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 7 /* CollectionTypeCS */);
		private final @NonNull SerializationStepAssignedRuleCall _039 // 1*CollectionLiteralPartCS::ownedExpression=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _040 // 1*CollectionLiteralPartCS::ownedExpression=55
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 55 /* PatternExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _041 // 1*CollectionLiteralPartCS::ownedLastExpression=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _042 // 1*CollectionPatternCS::ownedParts+=55
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 55 /* PatternExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _043 // 1*CollectionPatternCS::ownedType=7
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 7 /* CollectionTypeCS */);
		private final @NonNull SerializationStepAssignedRuleCall _044 // 1*CollectionPatternCS::restVariableName=24
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 24 /* Identifier */);
		private final @NonNull SerializationStepAssignedRuleCall _045 // 1*CollectionTypeCS::name=8
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 8 /* CollectionTypeIdentifier */);
		private final @NonNull SerializationStepAssignedRuleCall _046 // 1*CollectionTypeCS::ownedType=80
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 80 /* TypeExpWithoutMultiplicityCS */);
		private final @NonNull SerializationStepAssignedRuleCall _047 // 1*ContextCS::ownedExpression=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _048 // 1*CurlyBracketedClauseCS::ownedParts+=67
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 67 /* ShadowPartCS */);
		private final @NonNull SerializationStepAssigns _049 // 1*IfExpCS::ownedCondition=20|55
									= new SerializationStepAssigns(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, null, new @NonNull Integer [] { 20/*ExpCS*/,55/*PatternExpCS*/});
		private final @NonNull SerializationStepAssignedRuleCall _050 // 1*IfExpCS::ownedElseExpression=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _051 // 1*IfExpCS::ownedThenExpression=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _052 // 1*IfThenExpCS::ownedCondition=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _053 // 1*IfThenExpCS::ownedThenExpression=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _054 // 1*InfixExpCS::ownedLeft=57
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 57 /* PrefixedPrimaryExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _055 // 1*LambdaLiteralExpCS::ownedExpressionCS=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _056 // 1*LetExpCS::ownedInExpression=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _057 // 1*LetExpCS::ownedVariables+=32
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 32 /* LetVariableCS */);
		private final @NonNull SerializationStepAssignedRuleCall _058 // 1*MapLiteralExpCS::ownedParts+=36
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 36 /* MapLiteralPartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _059 // 1*MapLiteralExpCS::ownedType=37
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 37 /* MapTypeCS */);
		private final @NonNull SerializationStepAssignedRuleCall _060 // 1*MapLiteralPartCS::ownedKey=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _061 // 1*MapLiteralPartCS::ownedValue=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _062 // 1*MapTypeCS::ownedKeyType=79
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 79 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _063 // 1*MapTypeCS::ownedValueType=79
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 79 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _064 // 1*MultiplicityBoundsCS::lowerBound=29
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 29 /* LOWER */);
		private final @NonNull SerializationStepAssignedRuleCall _065 // 1*MultiplicityBoundsCS::upperBound=89
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 89 /* UPPER */);
		private final @NonNull SerializationStepAssignKeyword _066 // 1*MultiplicityStringCS::stringBounds='*|+|?'
									= new SerializationStepAssignKeyword(-1, BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._0);
		private final @NonNull SerializationStepAssignedRuleCall _067 // 1*NamedElementCS::name=1
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 1 /* BinaryOperatorName */);
		private final @NonNull SerializationStepAssignedRuleCall _068 // 1*NamedElementCS::name=93
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 93 /* UnaryOperatorName */);
		private final @NonNull SerializationStepAssignedRuleCall _069 // 1*NamedElementCS::name=96
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 96 /* UnrestrictedName */);
		private final @NonNull SerializationStepAssignedRuleCall _070 // 1*NavigatingArgCS::ownedCoIterator=3
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 3 /* CoIteratorVariableCS */);
		private final @NonNull SerializationStepAssignedRuleCall _071 // 1*NavigatingArgCS::ownedInitExpression=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _072 // 1*NavigatingArgCS::ownedNameExpression=45
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 45 /* NavigatingArgExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _073 // 1*NavigatingArgCS::ownedType=79
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 79 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _074 // 1*NestedExpCS::ownedExpression=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _075 // 1*NumberLiteralExpCS::symbol=42
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 42 /* NUMBER_LITERAL */);
		private final @NonNull SerializationStepAssignedRuleCall _076 // 1*OperatorExpCS::ownedRight=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _077 // 1*OperatorExpCS::ownedRight=56
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 56 /* PrefixedLetExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _078 // 1*OperatorExpCS::ownedRight=57
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 57 /* PrefixedPrimaryExpCS */);
		private final @NonNull SerializationStepCrossReference _079 // 1*PathElementCS::referredElement=URI
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"));
		private final @NonNull SerializationStepCrossReference _080 // 1*PathElementCS::referredElement=UnreservedName
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"));
		private final @NonNull SerializationStepCrossReference _081 // 1*PathElementCS::referredElement=UnrestrictedName
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull SerializationStepCrossReference _082 // 1*PathElementCS::referredElement=UnrestrictedName
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull SerializationStepAssignedRuleCall _083 // 1*PathNameCS::ownedPathElements+=21
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 21 /* FirstPathElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _084 // 1*PathNameCS::ownedPathElements+=51
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 51 /* NextPathElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _085 // 1*PathNameCS::ownedPathElements+=91
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 91 /* URIFirstPathElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _086 // 1*PatternExpCS::ownedPatternType=79
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 79 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _087 // 1*PrimitiveTypeRefCS::name=61
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 61 /* PrimitiveTypeIdentifier */);
		private final @NonNull SerializationStepAssignedRuleCall _088 // 1*RoundBracketedClauseCS::ownedArguments+=44
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 44 /* NavigatingArgCS */);
		private final @NonNull SerializationStepAssigns _089 // 1*ShadowPartCS::ownedInitExpression=20|55
									= new SerializationStepAssigns(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, null, new @NonNull Integer [] { 20/*ExpCS*/,55/*PatternExpCS*/});
		private final @NonNull SerializationStepAssignedRuleCall _090 // 1*ShadowPartCS::ownedInitExpression=71
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 71 /* StringLiteralExpCS */);
		private final @NonNull SerializationStepCrossReference _091 // 1*ShadowPartCS::referredProperty=UnrestrictedName
									= new SerializationStepCrossReference(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"));
		private final @NonNull SerializationStepAssignedRuleCall _092 // 1*SquareBracketedClauseCS::ownedTerms+=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _093 // 1*TemplateBindingCS::ownedSubstitutions+=73
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 73 /* TemplateParameterSubstitutionCS */);
		private final @NonNull SerializationStepAssignedRuleCall _094 // 1*TemplateParameterSubstitutionCS::ownedActualParameter=86
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 86 /* TypeRefCS */);
		private final @NonNull SerializationStepAssignedRuleCall _095 // 1*TemplateSignatureCS::ownedParameters+=85
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 85 /* TypeParameterCS */);
		private final @NonNull SerializationStepAssignedRuleCall _096 // 1*TupleLiteralExpCS::ownedParts+=76
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 76 /* TupleLiteralPartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _097 // 1*TupleTypeCS::ownedParts+=77
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 77 /* TuplePartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _098 // 1*TypeLiteralExpCS::ownedType=83
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 83 /* TypeLiteralWithMultiplicityCS */);
		private final @NonNull SerializationStepAssignedRuleCall _099 // 1*TypeNameExpCS::ownedCurlyBracketedClause=9
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 9 /* CurlyBracketedClauseCS */);
		private final @NonNull SerializationStepAssignedRuleCall _100 // 1*TypeNameExpCS::ownedPathName=54
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 54 /* PathNameCS */);
		private final @NonNull SerializationStepAssignedRuleCall _101 // 1*TypeNameExpCS::ownedPatternGuard=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _102 // 1*TypeParameterCS::ownedExtends+=87
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 87 /* TypedRefCS */);
		private final @NonNull SerializationStepAssignedRuleCall _103 // 1*TypedElementCS::ownedType=79
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 79 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _104 // 1*TypedTypeRefCS::ownedBinding=72
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 72 /* TemplateBindingCS */);
		private final @NonNull SerializationStepAssignedRuleCall _105 // 1*TypedTypeRefCS::ownedPathName=54
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 54 /* PathNameCS */);
		private final @NonNull SerializationStepAssignedRuleCall _106 // 1*VariableCS::ownedInitExpression=20
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 20 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _107 // 1*VariableCS::ownedType=79
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 79 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _108 // 1*WildcardTypeRefCS::ownedExtends=87
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 87 /* TypedRefCS */);
		private final @NonNull SerializationStepSequence _109 // 1*steps-1..10
									= new SerializationStepSequence(-1, 1, 10);
		private final @NonNull SerializationStepSequence _110 // 1*steps-1..11
									= new SerializationStepSequence(-1, 1, 11);
		private final @NonNull SerializationStepSequence _111 // 1*steps-1..12
									= new SerializationStepSequence(-1, 1, 12);
		private final @NonNull SerializationStepSequence _112 // 1*steps-1..3
									= new SerializationStepSequence(-1, 1, 3);
		private final @NonNull SerializationStepSequence _113 // 1*steps-1..4
									= new SerializationStepSequence(-1, 1, 4);
		private final @NonNull SerializationStepSequence _114 // 1*steps-1..5
									= new SerializationStepSequence(-1, 1, 5);
		private final @NonNull SerializationStepSequence _115 // 1*steps-1..6
									= new SerializationStepSequence(-1, 1, 6);
		private final @NonNull SerializationStepSequence _116 // 1*steps-1..7
									= new SerializationStepSequence(-1, 1, 7);
		private final @NonNull SerializationStepSequence _117 // 1*steps-1..8
									= new SerializationStepSequence(-1, 1, 8);
		private final @NonNull SerializationStepSequence _118 // 1*steps-1..9
									= new SerializationStepSequence(-1, 1, 9);
		private final @NonNull SerializationStepLiteral _119 // V00*'|1'
									= new SerializationStepLiteral(0, "|1");
		private final @NonNull SerializationStepAssignedRuleCall _120 // V00*AbstractNameExpCS::ownedSquareBracketedClauses+=69
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 69 /* SquareBracketedClauseCS */);
		private final @NonNull SerializationStepAssignedRuleCall _121 // V00*IfExpCS::ownedIfThenExpressions+=13
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 13 /* ElseIfThenExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _122 // V00*LetVariableCS::ownedRoundBracketedClause=62
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 62 /* RoundBracketedClauseCS */);
		private final @NonNull SerializationStepAssignedRuleCall _123 // V00*PatternExpCS::patternVariableName=96
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 96 /* UnrestrictedName */);
		private final @NonNull SerializationStepAssignedRuleCall _124 // V00*StringLiteralExpCS::segments+=70
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 70 /* StringLiteral */);
		private final @NonNull SerializationStepAssignedRuleCall _125 // V00*TypedRefCS::ownedMultiplicity=40
									= new SerializationStepAssignedRuleCall(0, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 40 /* MultiplicityCS */);
		private final @NonNull SerializationStepSequence _126 // V00*steps-3..10
									= new SerializationStepSequence(0, 3, 10);
		private final @NonNull SerializationStepSequence _127 // V00*steps-3..5
									= new SerializationStepSequence(0, 3, 5);
		private final @NonNull SerializationStepSequence _128 // V00*steps-3..6
									= new SerializationStepSequence(0, 3, 6);
		private final @NonNull SerializationStepSequence _129 // V00*steps-3..7
									= new SerializationStepSequence(0, 3, 7);
		private final @NonNull SerializationStepSequence _130 // V00*steps-3..8
									= new SerializationStepSequence(0, 3, 8);
		private final @NonNull SerializationStepSequence _131 // V00*steps-4..10
									= new SerializationStepSequence(0, 4, 10);
		private final @NonNull SerializationStepSequence _132 // V00*steps-4..6
									= new SerializationStepSequence(0, 4, 6);
		private final @NonNull SerializationStepSequence _133 // V00*steps-4..8
									= new SerializationStepSequence(0, 4, 8);
		private final @NonNull SerializationStepSequence _134 // V00*steps-4..9
									= new SerializationStepSequence(0, 4, 9);
		private final @NonNull SerializationStepSequence _135 // V00*steps-5..7
									= new SerializationStepSequence(0, 5, 7);
		private final @NonNull SerializationStepSequence _136 // V00*steps-6..8
									= new SerializationStepSequence(0, 6, 8);
		private final @NonNull SerializationStepLiteral _137 // V01*'|1'
									= new SerializationStepLiteral(1, "|1");
		private final @NonNull SerializationStepAssignedRuleCall _138 // V01*AbstractNameExpCS::ownedRoundBracketedClause=62
									= new SerializationStepAssignedRuleCall(1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 62 /* RoundBracketedClauseCS */);
		private final @NonNull SerializationStepAssignedRuleCall _139 // V01*CollectionTypeCS::ownedCollectionMultiplicity=40
									= new SerializationStepAssignedRuleCall(1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 40 /* MultiplicityCS */);
		private final @NonNull SerializationStepAssigns _140 // V01*RoundBracketedClauseCS::ownedArguments+=47|48|46
									= new SerializationStepAssigns(1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, null, new @NonNull Integer [] { 47/*NavigatingCommaArgCS*/,48/*NavigatingSemiArgCS*/,46/*NavigatingBarArgCS*/});
		private final @NonNull SerializationStepAssignedRuleCall _141 // V01*TemplateBindingCS::ownedMultiplicity=40
									= new SerializationStepAssignedRuleCall(1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 40 /* MultiplicityCS */);
		private final @NonNull SerializationStepAssignedRuleCall _142 // V01*TypedRefCS::ownedMultiplicity=40
									= new SerializationStepAssignedRuleCall(1, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 40 /* MultiplicityCS */);
		private final @NonNull SerializationStepSequence _143 // V01*steps-4..6
									= new SerializationStepSequence(1, 4, 6);
		private final @NonNull SerializationStepSequence _144 // V01*steps-5..7
									= new SerializationStepSequence(1, 5, 7);
		private final @NonNull SerializationStepSequence _145 // V01*steps-5..8
									= new SerializationStepSequence(1, 5, 8);
		private final @NonNull SerializationStepSequence _146 // V01*steps-5..9
									= new SerializationStepSequence(1, 5, 9);
		private final @NonNull SerializationStepSequence _147 // V01*steps-6..8
									= new SerializationStepSequence(1, 6, 8);
		private final @NonNull SerializationStepSequence _148 // V01*steps-7..9
									= new SerializationStepSequence(1, 7, 9);
		private final @NonNull SerializationStepSequence _149 // V01*steps-8..10
									= new SerializationStepSequence(1, 8, 10);
		private final @NonNull SerializationStepSequence _150 // V01*steps-9..11
									= new SerializationStepSequence(1, 9, 11);
		private final @NonNull SerializationStepAssignedRuleCall _151 // V02*AbstractNameExpCS::ownedCurlyBracketedClause=9
									= new SerializationStepAssignedRuleCall(2, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 9 /* CurlyBracketedClauseCS */);
		private final @NonNull SerializationStepAssignedRuleCall _152 // V02*TypedRefCS::ownedMultiplicity=40
									= new SerializationStepAssignedRuleCall(2, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 40 /* MultiplicityCS */);
		private final @NonNull SerializationStepSequence _153 // V02*steps-7..9
									= new SerializationStepSequence(2, 7, 9);
		private final @NonNull SerializationStepAssignedRuleCall _154 // V03*TypedRefCS::ownedMultiplicity=40
									= new SerializationStepAssignedRuleCall(3, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 40 /* MultiplicityCS */);
		private final @NonNull SerializationStepSequence _155 // V03*steps-6..8
									= new SerializationStepSequence(3, 6, 8);
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
			ss._1 /* «! » «value» «! » */,
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
				null);
		private final @NonNull ParserRuleValue _03 // CoIteratorVariableCS
			= new ParserRuleValue(3, "CoIteratorVariableCS",
				new @NonNull SerializationRule [] {
					sr0._18 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _04 // CollectionLiteralExpCS
			= new ParserRuleValue(4, "CollectionLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._19 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _05 // CollectionLiteralPartCS
			= new ParserRuleValue(5, "CollectionLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._20 /* ownedExpression=PatternExpCS */,
					sr0._21 /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _06 // CollectionPatternCS
			= new ParserRuleValue(6, "CollectionPatternCS",
				new @NonNull SerializationRule [] {
					sr0._22 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _07 // CollectionTypeCS
			= new ParserRuleValue(7, "CollectionTypeCS",
				new @NonNull SerializationRule [] {
					sr0._23 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _08 // CollectionTypeIdentifier
			= new DataTypeRuleValue(8, "CollectionTypeIdentifier");
		private final @NonNull ParserRuleValue _09 // CurlyBracketedClauseCS
			= new ParserRuleValue(9, "CurlyBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr0._24 /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				null);
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
				null);
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
					sr0._26 /* symbol={'false|true'} */,
					sr0._27 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._28 /* '*' */,
					sr0._29 /* 'invalid' */,
					sr0._30 /* 'null' */,
					sr0._31 /* 'self' */,
					sr0._32 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._33 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._34 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._35 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._36 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._37 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._38 /* symbol=NUMBER_LITERAL */,
					sr0._39 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._40 /* segments+=StringLiteral[+] */,
					sr0._41 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._42 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._46 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._68 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._46); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _21 // FirstPathElementCS
			= new ParserRuleValue(21, "FirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._00 /* referredElement=UnrestrictedName */
				},
				null);
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
				null);
		private final @NonNull DataTypeRuleValue _26 // InfixOperatorName
			= new DataTypeRuleValue(26, "InfixOperatorName");
		private final @NonNull ParserRuleValue _27 // InvalidLiteralExpCS
			= new ParserRuleValue(27, "InvalidLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._44 /* 'invalid' */
				},
				null);
		private final @NonNull TerminalRuleValue _28 // LETTER_CHARACTER
			= new TerminalRuleValue(28, "LETTER_CHARACTER");
		private final @NonNull DataTypeRuleValue _29 // LOWER
			= new DataTypeRuleValue(29, "LOWER");
		private final @NonNull ParserRuleValue _30 // LambdaLiteralExpCS
			= new ParserRuleValue(30, "LambdaLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._45 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _31 // LetExpCS
			= new ParserRuleValue(31, "LetExpCS",
				new @NonNull SerializationRule [] {
					sr0._46 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _32 // LetVariableCS
			= new ParserRuleValue(32, "LetVariableCS",
				new @NonNull SerializationRule [] {
					sr0._47 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull TerminalRuleValue _33 // ML_COMMENT
			= new TerminalRuleValue(33, "ML_COMMENT");
		private final @NonNull TerminalRuleValue _34 // ML_SINGLE_QUOTED_STRING
			= new TerminalRuleValue(34, "ML_SINGLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _35 // MapLiteralExpCS
			= new ParserRuleValue(35, "MapLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._48 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _36 // MapLiteralPartCS
			= new ParserRuleValue(36, "MapLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._49 /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _37 // MapTypeCS
			= new ParserRuleValue(37, "MapTypeCS",
				new @NonNull SerializationRule [] {
					sr0._50 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _38 // Model
			= new ParserRuleValue(38, "Model",
				new @NonNull SerializationRule [] {
					sr0._51 /* ownedExpression=ExpCS */
				},
				null);
		private final @NonNull ParserRuleValue _39 // MultiplicityBoundsCS
			= new ParserRuleValue(39, "MultiplicityBoundsCS",
				new @NonNull SerializationRule [] {
					sr0._01 /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _40 // MultiplicityCS
			= new ParserRuleValue(40, "MultiplicityCS",
				new @NonNull SerializationRule [] {
					sr0._02 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					sr0._03 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					sr0._04 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */,
					sr0._05 /* { '[' stringBounds={'*|+|?'} ']' } */,
					sr0._06 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					sr0._07 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */
				},
				null);
		private final @NonNull ParserRuleValue _41 // MultiplicityStringCS
			= new ParserRuleValue(41, "MultiplicityStringCS",
				new @NonNull SerializationRule [] {
					sr0._08 /* stringBounds={'*|+|?'} */
				},
				null);
		private final @NonNull DataTypeRuleValue _42 // NUMBER_LITERAL
			= new DataTypeRuleValue(42, "NUMBER_LITERAL");
		private final @NonNull ParserRuleValue _43 // NameExpCS
			= new ParserRuleValue(43, "NameExpCS",
				new @NonNull SerializationRule [] {
					sr0._52 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _44 // NavigatingArgCS
			= new ParserRuleValue(44, "NavigatingArgCS",
				new @NonNull SerializationRule [] {
					sr0._53 /* ownedNameExpression=NavigatingArgExpCS */,
					sr0._54 /* { ':' ownedType=TypeExpCS } */,
					sr0._55 /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._56 /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._57 /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _45 // NavigatingArgExpCS
			= new ParserRuleValue(45, "NavigatingArgExpCS",
				new @NonNull SerializationRule [] {
					sr0._26 /* symbol={'false|true'} */,
					sr0._27 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._28 /* '*' */,
					sr0._29 /* 'invalid' */,
					sr0._30 /* 'null' */,
					sr0._31 /* 'self' */,
					sr0._32 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._33 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._34 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._35 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._36 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._37 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._38 /* symbol=NUMBER_LITERAL */,
					sr0._39 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._40 /* segments+=StringLiteral[+] */,
					sr0._41 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._42 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._46 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._68 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._47); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _46 // NavigatingBarArgCS
			= new ParserRuleValue(46, "NavigatingBarArgCS",
				new @NonNull SerializationRule [] {
					sr0._58 /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _47 // NavigatingCommaArgCS
			= new ParserRuleValue(47, "NavigatingCommaArgCS",
				new @NonNull SerializationRule [] {
					sr0._62 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					sr0._59 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._60 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._61 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _48 // NavigatingSemiArgCS
			= new ParserRuleValue(48, "NavigatingSemiArgCS",
				new @NonNull SerializationRule [] {
					sr0._63 /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _49 // NavigationOperatorName
			= new DataTypeRuleValue(49, "NavigationOperatorName");
		private final @NonNull ParserRuleValue _50 // NestedExpCS
			= new ParserRuleValue(50, "NestedExpCS",
				new @NonNull SerializationRule [] {
					sr1._64 /* { '(' ownedExpression=ExpCS ')' } */
				},
				null);
		private final @NonNull ParserRuleValue _51 // NextPathElementCS
			= new ParserRuleValue(51, "NextPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._09 /* referredElement=UnreservedName */
				},
				null);
		private final @NonNull ParserRuleValue _52 // NullLiteralExpCS
			= new ParserRuleValue(52, "NullLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._65 /* 'null' */
				},
				null);
		private final @NonNull ParserRuleValue _53 // NumberLiteralExpCS
			= new ParserRuleValue(53, "NumberLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._66 /* symbol=NUMBER_LITERAL */
				},
				null);
		private final @NonNull ParserRuleValue _54 // PathNameCS
			= new ParserRuleValue(54, "PathNameCS",
				new @NonNull SerializationRule [] {
					sr0._10 /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				null);
		private final @NonNull ParserRuleValue _55 // PatternExpCS
			= new ParserRuleValue(55, "PatternExpCS",
				new @NonNull SerializationRule [] {
					sr1._67 /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _56 // PrefixedLetExpCS
			= new ParserRuleValue(56, "PrefixedLetExpCS",
				new @NonNull SerializationRule [] {
					sr0._46 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._68 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._19); /* LetExpCS|PrefixedLetExpCS */
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
				iv._44); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
				iv._43); /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
				iv._42); /* BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _60 // PrimitiveTypeCS
			= new ParserRuleValue(60, "PrimitiveTypeCS",
				new @NonNull SerializationRule [] {
					sr1._70 /* name=PrimitiveTypeIdentifier */
				},
				null);
		private final @NonNull DataTypeRuleValue _61 // PrimitiveTypeIdentifier
			= new DataTypeRuleValue(61, "PrimitiveTypeIdentifier");
		private final @NonNull ParserRuleValue _62 // RoundBracketedClauseCS
			= new ParserRuleValue(62, "RoundBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._71 /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				null);
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
				null);
		private final @NonNull ParserRuleValue _67 // ShadowPartCS
			= new ParserRuleValue(67, "ShadowPartCS",
				new @NonNull SerializationRule [] {
					sr1._73 /* ownedInitExpression=StringLiteralExpCS */,
					sr1._74 /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				null);
		private final @NonNull ParserRuleValue _68 // SimplePathNameCS
			= new ParserRuleValue(68, "SimplePathNameCS",
				new @NonNull SerializationRule [] {
					sr1._75 /* ownedPathElements+=FirstPathElementCS */
				},
				null);
		private final @NonNull ParserRuleValue _69 // SquareBracketedClauseCS
			= new ParserRuleValue(69, "SquareBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._76 /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				null);
		private final @NonNull DataTypeRuleValue _70 // StringLiteral
			= new DataTypeRuleValue(70, "StringLiteral");
		private final @NonNull ParserRuleValue _71 // StringLiteralExpCS
			= new ParserRuleValue(71, "StringLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._77 /* segments+=StringLiteral[+] */
				},
				null);
		private final @NonNull ParserRuleValue _72 // TemplateBindingCS
			= new ParserRuleValue(72, "TemplateBindingCS",
				new @NonNull SerializationRule [] {
					sr0._11 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _73 // TemplateParameterSubstitutionCS
			= new ParserRuleValue(73, "TemplateParameterSubstitutionCS",
				new @NonNull SerializationRule [] {
					sr0._12 /* ownedActualParameter=TypeRefCS */
				},
				null);
		private final @NonNull ParserRuleValue _74 // TemplateSignatureCS
			= new ParserRuleValue(74, "TemplateSignatureCS",
				new @NonNull SerializationRule [] {
					sr0._13 /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */
				},
				null);
		private final @NonNull ParserRuleValue _75 // TupleLiteralExpCS
			= new ParserRuleValue(75, "TupleLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._78 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _76 // TupleLiteralPartCS
			= new ParserRuleValue(76, "TupleLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr1._79 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _77 // TuplePartCS
			= new ParserRuleValue(77, "TuplePartCS",
				new @NonNull SerializationRule [] {
					sr1._80 /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _78 // TupleTypeCS
			= new ParserRuleValue(78, "TupleTypeCS",
				new @NonNull SerializationRule [] {
					sr1._81 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _79 // TypeExpCS
			= new ParserRuleValue(79, "TypeExpCS",
				new @NonNull SerializationRule [] {
					sr1._82 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._83 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._84 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._85 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._86 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._87 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._35); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
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
				iv._34); /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
		private final @NonNull ParserRuleValue _81 // TypeLiteralCS
			= new ParserRuleValue(81, "TypeLiteralCS",
				new @NonNull SerializationRule [] {
					sr0._23 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._50 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._70 /* name=PrimitiveTypeIdentifier */,
					sr1._81 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				iv._31); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS */
		private final @NonNull ParserRuleValue _82 // TypeLiteralExpCS
			= new ParserRuleValue(82, "TypeLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._88 /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				null);
		private final @NonNull ParserRuleValue _83 // TypeLiteralWithMultiplicityCS
			= new ParserRuleValue(83, "TypeLiteralWithMultiplicityCS",
				new @NonNull SerializationRule [] {
					sr1._89 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._90 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._91 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._92 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				iv._33); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
		private final @NonNull ParserRuleValue _84 // TypeNameExpCS
			= new ParserRuleValue(84, "TypeNameExpCS",
				new @NonNull SerializationRule [] {
					sr1._93 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _85 // TypeParameterCS
			= new ParserRuleValue(85, "TypeParameterCS",
				new @NonNull SerializationRule [] {
					sr0._14 /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _86 // TypeRefCS
			= new ParserRuleValue(86, "TypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._15 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */,
					sr0._16 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				iv._49); /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
		private final @NonNull ParserRuleValue _87 // TypedRefCS
			= new ParserRuleValue(87, "TypedRefCS",
				new @NonNull SerializationRule [] {
					sr0._15 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				iv._39); /* TypedRefCS|TypedTypeRefCS */
		private final @NonNull ParserRuleValue _88 // TypedTypeRefCS
			= new ParserRuleValue(88, "TypedTypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._15 /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _89 // UPPER
			= new DataTypeRuleValue(89, "UPPER");
		private final @NonNull DataTypeRuleValue _90 // URI
			= new DataTypeRuleValue(90, "URI");
		private final @NonNull ParserRuleValue _91 // URIFirstPathElementCS
			= new ParserRuleValue(91, "URIFirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr1._94 /* referredElement=UnrestrictedName */,
					sr1._95 /* referredElement=URI */
				},
				null);
		private final @NonNull ParserRuleValue _92 // URIPathNameCS
			= new ParserRuleValue(92, "URIPathNameCS",
				new @NonNull SerializationRule [] {
					sr1._96 /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _93 // UnaryOperatorName
			= new DataTypeRuleValue(93, "UnaryOperatorName");
		private final @NonNull ParserRuleValue _94 // UnlimitedNaturalLiteralExpCS
			= new ParserRuleValue(94, "UnlimitedNaturalLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._97 /* '*' */
				},
				null);
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
					new SerializationRule_SegmentsList(sr0._17, sl._24) /* symbol={'false|true'} */,
					new SerializationRule_SegmentsList(sr0._26, sl._24) /* symbol={'false|true'} */
				}, null
			);
		private final @NonNull EClassValue _01 // CollectionLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._19, sl._28) /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					new SerializationRule_SegmentsList(sr0._27, sl._28) /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
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
						iv._48) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _03 // CollectionPatternCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._22, sl._06) /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
					new SerializationRule_SegmentsList(sr1._84, sl._07) /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._10) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
						iv._16) /* PatternExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
						iv._2) /* CollectionTypeCS */
				}
			);
		private final @NonNull EClassValue _04 // CollectionTypeCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._23, sl._18) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._85, sl._19) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._91, sl._19) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
						iv._10) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._10) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
						iv._34) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _05 // ContextCS
			= new EClassValue(EssentialOCLCSPackage.Literals.CONTEXT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._51, sl._24) /* ownedExpression=ExpCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _06 // CurlyBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._24, sl._14) /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
						iv._22) /* ShadowPartCS */
				}
			);
		private final @NonNull EClassValue _07 // ExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._28, sl._24) /* '*' */,
					new SerializationRule_SegmentsList(sr0._29, sl._24) /* 'invalid' */,
					new SerializationRule_SegmentsList(sr0._30, sl._24) /* 'null' */,
					new SerializationRule_SegmentsList(sr0._31, sl._24) /* 'self' */
				}, null
			);
		private final @NonNull EClassValue _08 // IfExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.IF_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._32, sl._45) /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					new SerializationRule_SegmentsList(sr0._43, sl._45) /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
						iv._48) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
						iv._4) /* ElseIfThenExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _09 // IfThenExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._25, sl._43) /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _10 // InfixExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._33, sl._29) /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
						iv._44) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
					new SerializationRule_SegmentsList(sr0._34, sl._48) /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					new SerializationRule_SegmentsList(sr0._45, sl._48) /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _13 // LetExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.LET_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._46, sl._40) /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
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
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._21) /* RoundBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _15 // MapLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._35, sl._28) /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
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
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _17 // MapTypeCS
			= new EClassValue(EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._50, sl._20) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._86, sl._21) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._92, sl._21) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
						iv._35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._10) /* MultiplicityCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
						iv._35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _18 // MultiplicityBoundsCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._01, sl._22) /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */,
					new SerializationRule_SegmentsList(sr0._02, sl._10) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._03, sl._11) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._04, sl._11) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, null
			);
		private final @NonNull EClassValue _19 // MultiplicityStringCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._05, sl._12) /* { '[' stringBounds={'*|+|?'} ']' } */,
					new SerializationRule_SegmentsList(sr0._06, sl._13) /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._07, sl._13) /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._08, sl._50) /* stringBounds={'*|+|?'} */
				}, null
			);
		private final @NonNull EClassValue _20 // NameExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._36, sl._25) /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					new SerializationRule_SegmentsList(sr0._52, sl._25) /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
						iv._3) /* CurlyBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
						iv._15) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
						iv._21) /* RoundBracketedClauseCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
						iv._23) /* SquareBracketedClauseCS */
				}
			);
		private final @NonNull EClassValue _21 // NavigatingArgCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._53, sl._24) /* ownedNameExpression=NavigatingArgExpCS */,
					new SerializationRule_SegmentsList(sr0._54, sl._39) /* { ':' ownedType=TypeExpCS } */,
					new SerializationRule_SegmentsList(sr0._56, sl._30) /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._55, sl._31) /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._57, sl._27) /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					new SerializationRule_SegmentsList(sr0._58, sl._41) /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */,
					new SerializationRule_SegmentsList(sr0._62, sl._34) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					new SerializationRule_SegmentsList(sr0._60, sl._36) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._59, sl._37) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._61, sl._35) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					new SerializationRule_SegmentsList(sr0._63, sl._38) /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
						iv._0) /* CoIteratorVariableCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
						iv._47) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
						iv._35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _22 // NestedExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._37, sl._33) /* { '(' ownedExpression=ExpCS ')' } */,
					new SerializationRule_SegmentsList(sr1._64, sl._33) /* { '(' ownedExpression=ExpCS ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
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
					new SerializationRule_SegmentsList(sr0._38, sl._24) /* symbol=NUMBER_LITERAL */,
					new SerializationRule_SegmentsList(sr1._66, sl._24) /* symbol=NUMBER_LITERAL */
				}, null
			);
		private final @NonNull EClassValue _25 // PathElementCS
			= new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._00, sl._50) /* referredElement=UnrestrictedName */,
					new SerializationRule_SegmentsList(sr0._09, sl._50) /* referredElement=UnreservedName */,
					new SerializationRule_SegmentsList(sr1._94, sl._50) /* referredElement=UnrestrictedName */
				}, null
			);
		private final @NonNull EClassValue _26 // PathElementWithURICS
			= new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._95, sl._50) /* referredElement=URI */
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
						iv._41) /* FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS */
				}
			);
		private final @NonNull EClassValue _28 // PatternExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._67, sl._49) /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
						iv._35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _29 // PrefixExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._39, sl._39) /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					new SerializationRule_SegmentsList(sr1._68, sl._39) /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
					new SerializationRule_SegmentsList(sr1._69, sl._39) /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
						iv._45) /* BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _30 // PrimitiveTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._70, sl._50) /* name=PrimitiveTypeIdentifier */,
					new SerializationRule_SegmentsList(sr1._82, sl._15) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._89, sl._15) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
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
						iv._13) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
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
						iv._48) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _34 // SquareBracketedClauseCS
			= new EClassValue(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._76, sl._09) /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _35 // StringLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._40, sl._24) /* segments+=StringLiteral[+] */,
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
						iv._26) /* TemplateParameterSubstitutionCS */
				}
			);
		private final @NonNull EClassValue _37 // TemplateParameterSubstitutionCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._12, sl._24) /* ownedActualParameter=TypeRefCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						iv._49) /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
				}
			);
		private final @NonNull EClassValue _38 // TemplateSignatureCS
			= new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._13, sl._32) /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						iv._36) /* TypeParameterCS */
				}
			);
		private final @NonNull EClassValue _39 // TupleLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._41, sl._47) /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._78, sl._47) /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
						iv._27) /* TupleLiteralPartCS */
				}
			);
		private final @NonNull EClassValue _40 // TupleLiteralPartCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._79, sl._46) /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
						iv._35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _41 // TuplePartCS
			= new EClassValue(BaseCSPackage.Literals.TUPLE_PART_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._80, sl._49) /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
						iv._35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _42 // TupleTypeCS
			= new EClassValue(BaseCSPackage.Literals.TUPLE_TYPE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._81, sl._16) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					new SerializationRule_SegmentsList(sr1._83, sl._17) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._90, sl._17) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._10) /* MultiplicityCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
						iv._28) /* TuplePartCS */
				}
			);
		private final @NonNull EClassValue _43 // TypeLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._42, sl._24) /* ownedType=TypeLiteralWithMultiplicityCS */,
					new SerializationRule_SegmentsList(sr1._88, sl._24) /* ownedType=TypeLiteralWithMultiplicityCS */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
						iv._33) /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS */
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
						iv._15) /* PathNameCS */,
					new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
						iv._46) /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
				}
			);
		private final @NonNull EClassValue _45 // TypeParameterCS
			= new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._14, sl._44) /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						iv._39) /* TypedRefCS|TypedTypeRefCS */
				}
			);
		private final @NonNull EClassValue _46 // TypedTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._15, sl._04) /* { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						iv._25) /* TemplateBindingCS */,
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						iv._15) /* PathNameCS */
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
						iv._35) /* CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS */
				}
			);
		private final @NonNull EClassValue _49 // WildcardTypeRefCS
			= new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._16, sl._23) /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						iv._39) /* TypedRefCS|TypedTypeRefCS */
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
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._033 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
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
		// Base::MultiplicityBoundsCS : { lowerBound=LOWER { '..' upperBound=UPPER }[?] }
		private @NonNull SerializationRule _01 = new SerializationRule(39,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._065 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._020 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || «null» */,
				st._064 /* 1*MultiplicityBoundsCS::lowerBound=29 || «? » «value» «? » */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._065 /* 1*MultiplicityBoundsCS::upperBound=89 || «? » «value» «? » */
			},
			sl._22,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' }
		private @NonNull SerializationRule _02 = new SerializationRule(40,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._065 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._020 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._116 /* 1*steps-1..7 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._064 /* 1*MultiplicityBoundsCS::lowerBound=29 || «? » «value» «? » */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._065 /* 1*MultiplicityBoundsCS::upperBound=89 || «? » «value» «? » */,
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
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' }
		private @NonNull SerializationRule _03 = new SerializationRule(40,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._065 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._020 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._064 /* 1*MultiplicityBoundsCS::lowerBound=29 || «? » «value» «? » */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._065 /* 1*MultiplicityBoundsCS::upperBound=89 || «? » «value» «? » */,
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
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _04 = new SerializationRule(40,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._086 /* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._065 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._020 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._064 /* 1*MultiplicityBoundsCS::lowerBound=29 || «? » «value» «? » */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._065 /* 1*MultiplicityBoundsCS::upperBound=89 || «? » «value» «? » */,
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
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._8, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} ']' }
		private @NonNull SerializationRule _05 = new SerializationRule(40,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._021 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._066 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */,
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
						new EnumerationValue_MultiplicativeCardinality(ev._0, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} '|?' ']' }
		private @NonNull SerializationRule _06 = new SerializationRule(40,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._021 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._066 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */,
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
						new EnumerationValue_MultiplicativeCardinality(ev._0, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _07 = new SerializationRule(40,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._066 /* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._021 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._066 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */,
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
						new EnumerationValue_MultiplicativeCardinality(ev._8, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._0, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityStringCS : stringBounds={'*|+|?'}
		private @NonNull SerializationRule _08 = new SerializationRule(41,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._021 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._066 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */
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
						new EnumerationValue_MultiplicativeCardinality(ev._0, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Base::NextPathElementCS : referredElement=UnreservedName
		private @NonNull SerializationRule _09 = new SerializationRule(51,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._033 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._099 /* check-rule basecs::PathNameCS.ownedPathElements : 21|51 */,
				ms._050 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || «null» */,
				st._083 /* 1*PathNameCS::ownedPathElements+=21 || «null» */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'::' || «! » «value» «! » */,
				st._084 /* 1*PathNameCS::ownedPathElements+=51 || «null» */
			},
			sl._03,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._14) /* FirstPathElementCS|NextPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(21, GrammarCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(51, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::TemplateBindingCS : { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _11 = new SerializationRule(72,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._101 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 40 */,
				ms._102 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 73 */,
				ms._089 /* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				ms._053 /* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._115 /* 1*steps-1..6 || «null» */,
				st._093 /* 1*TemplateBindingCS::ownedSubstitutions+=73 || «null» */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._093 /* 1*TemplateBindingCS::ownedSubstitutions+=73 || «null» */,
				st._141 /* V01*TemplateBindingCS::ownedMultiplicity=40 || «null» */
			},
			sl._05,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					iv._26) /* TemplateParameterSubstitutionCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(73, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// Base::TemplateParameterSubstitutionCS : ownedActualParameter=TypeRefCS
		private @NonNull SerializationRule _12 = new SerializationRule(73,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._103 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 86 */,
				ms._039 /* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._094 /* 1*TemplateParameterSubstitutionCS::ownedActualParameter=86 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					iv._37) /* TypeRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(86, GrammarCardinality.ONE)
					}
				)
			});
		// Base::TemplateSignatureCS : { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' }
		private @NonNull SerializationRule _13 = new SerializationRule(74,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._104 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 85 */,
				ms._054 /* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._116 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._095 /* 1*TemplateSignatureCS::ownedParameters+=85 || «null» */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._095 /* 1*TemplateSignatureCS::ownedParameters+=85 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._32,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					iv._36) /* TypeParameterCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(85, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// Base::TypeParameterCS : { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] }
		private @NonNull SerializationRule _14 = new SerializationRule(85,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._106 /* check-rule basecs::TypeParameterCS.ownedExtends : 87 */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._057 /* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				ms._083 /* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*NamedElementCS::name=96 || «? » «value» «? » */,
				st._130 /* V00*steps-3..8 || «null» */,
				st._022 /* 1*'extends' || «? » «value» «? » */,
				st._102 /* 1*TypeParameterCS::ownedExtends+=87 || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._000 /* 1*'&&' || «? » «value» «? » */,
				st._102 /* 1*TypeParameterCS::ownedExtends+=87 || «null» */
			},
			sl._44,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					iv._38) /* TypedRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(87, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::TypedTypeRefCS : { ownedPathName=PathNameCS { '(' ownedBinding=TemplateBindingCS ')' }[?] }
		private @NonNull SerializationRule _15 = new SerializationRule(88,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._109 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 72 */,
				ms._110 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 54 */,
				ms._074 /* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				ms._044 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._115 /* 1*steps-1..6 || «null» */,
				st._105 /* 1*TypedTypeRefCS::ownedPathName=54 || «null» */,
				st._128 /* V00*steps-3..6 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._104 /* 1*TypedTypeRefCS::ownedBinding=72 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._04,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._25) /* TemplateBindingCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._15) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(72, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(54, GrammarCardinality.ONE)
					}
				)
			});
		// Base::WildcardTypeRefCS : { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
		private @NonNull SerializationRule _16 = new SerializationRule(98,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._111 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : 87 */,
				ms._076 /* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || «null» */,
				st._012 /* 1*'?' || «? » «value» «? » */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._022 /* 1*'extends' || «? » «value» «? » */,
				st._108 /* 1*WildcardTypeRefCS::ownedExtends=87 || «null» */
			},
			sl._23,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					iv._38) /* TypedRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(87, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::BooleanLiteralExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _17 = new SerializationRule(2,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._001 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._036 /* 1*BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
						new EnumerationValue_MultiplicativeCardinality(ev._6, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::CoIteratorVariableCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] }
		private @NonNull SerializationRule _18 = new SerializationRule(3,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._163 /* check-rule essentialoclcs::VariableCS.ownedType : 79 */,
				ms._075 /* assign V0 = |VariableCS::ownedType| */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*NamedElementCS::name=96 || «? » «value» «? » */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._107 /* 1*VariableCS::ownedType=79 || «null» */
			},
			sl._43,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._29) /* TypeExpCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _19 = new SerializationRule(4,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._117 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 7 */,
				ms._116 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 5 */,
				ms._002 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._046 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._077 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._038 /* 1*CollectionLiteralExpCS::ownedType=7 || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._133 /* V00*steps-4..8 || «null» */,
				st._037 /* 1*CollectionLiteralExpCS::ownedParts+=5 || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._037 /* 1*CollectionLiteralExpCS::ownedParts+=5 || «null» */,
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
					new RuleIndex_MultiplicativeCardinality(7, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(5, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : ownedExpression=PatternExpCS
		private @NonNull SerializationRule _20 = new SerializationRule(5,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._119 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 55 */,
				ms._003 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._040 /* 1*CollectionLiteralPartCS::ownedExpression=55 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					iv._16) /* PatternExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] }
		private @NonNull SerializationRule _21 = new SerializationRule(5,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._118 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 20 */,
				ms._120 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 20 */,
				ms._059 /* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
				ms._003 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._039 /* 1*CollectionLiteralPartCS::ownedExpression=20 || «null» */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._006 /* 1*'..' || «! » «value» «! » */,
				st._041 /* 1*CollectionLiteralPartCS::ownedLastExpression=20 || «null» */
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
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CollectionPatternCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' }
		private @NonNull SerializationRule _22 = new SerializationRule(6,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._122 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 7 */,
				ms._121 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 55 */,
				ms._060 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._078 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._004 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._110 /* 1*steps-1..11 || «null» */,
				st._043 /* 1*CollectionPatternCS::ownedType=7 || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._131 /* V00*steps-4..10 || «null» */,
				st._042 /* 1*CollectionPatternCS::ownedParts+=55 || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._042 /* 1*CollectionPatternCS::ownedParts+=55 || «null» */,
				st._004 /* 1*'++' || «? » «value» «? » */,
				st._044 /* 1*CollectionPatternCS::restVariableName=24 || «? » «value» «? » */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._06,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._16) /* PatternExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(7, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::CollectionTypeCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
		private @NonNull SerializationRule _23 = new SerializationRule(7,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._123 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 40 */,
				ms._124 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 80 */,
				ms._061 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._085 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._116 /* 1*steps-1..7 || «null» */,
				st._045 /* 1*CollectionTypeCS::name=8 || «? » «value» «? » */,
				st._129 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._046 /* 1*CollectionTypeCS::ownedType=80 || «null» */,
				st._139 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=40 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._18,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._10) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._30) /* TypeExpWithoutMultiplicityCS */
			},
			new /*@NonNull*/ EAttribute [] {
				EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(80, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CurlyBracketedClauseCS : { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _24 = new SerializationRule(9,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._126 /* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 67 */,
				ms._047 /* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
				ms._079 /* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._129 /* V00*steps-3..7 || «null» */,
				st._048 /* 1*CurlyBracketedClauseCS::ownedParts+=67 || «null» */,
				st._144 /* V01*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._048 /* 1*CurlyBracketedClauseCS::ownedParts+=67 || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._14,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					iv._22) /* ShadowPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(67, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ElseIfThenExpCS : { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS }
		private @NonNull SerializationRule _25 = new SerializationRule(13,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._131 /* check-rule essentialoclcs::IfThenExpCS.ownedCondition : 20 */,
				ms._132 /* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 20 */,
				ms._011 /* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
				ms._010 /* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._020 /* 1*'elseif' || «? » «value» «? » */,
				st._052 /* 1*IfThenExpCS::ownedCondition=20 || «null» */,
				st._030 /* 1*'then' || «? » «value» «? » */,
				st._053 /* 1*IfThenExpCS::ownedThenExpression=20 || «null» */
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
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _26 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._001 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._036 /* 1*BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
						new EnumerationValue_MultiplicativeCardinality(ev._6, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::ExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _27 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._117 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 7 */,
				ms._116 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 5 */,
				ms._002 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._046 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._077 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._038 /* 1*CollectionLiteralExpCS::ownedType=7 || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._133 /* V00*steps-4..8 || «null» */,
				st._037 /* 1*CollectionLiteralExpCS::ownedParts+=5 || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._037 /* 1*CollectionLiteralExpCS::ownedParts+=5 || «null» */,
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
					new RuleIndex_MultiplicativeCardinality(7, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(5, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : '*'
		private @NonNull SerializationRule _28 = new SerializationRule(20,
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
		private @NonNull SerializationRule _29 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._025 /* 1*'invalid' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : 'null'
		private @NonNull SerializationRule _30 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._027 /* 1*'null' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : 'self'
		private @NonNull SerializationRule _31 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._029 /* 1*'self' || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _32 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._127 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 20|55 */,
				ms._129 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 13 */,
				ms._128 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 20 */,
				ms._130 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 20 */,
				ms._008 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._062 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._009 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._007 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._023 /* 1*'if' || «? » «value» «? » */,
				st._049 /* 1*IfExpCS::ownedCondition=20|55 || «null» */,
				st._030 /* 1*'then' || «? » «value» «? » */,
				st._051 /* 1*IfExpCS::ownedThenExpression=20 || «null» */,
				st._121 /* V00*IfExpCS::ownedIfThenExpressions+=13 || «null» */,
				st._019 /* 1*'else' || «? » «value» «? » */,
				st._050 /* 1*IfExpCS::ownedElseExpression=20 || «null» */,
				st._021 /* 1*'endif' || «? » «value» «? » */
			},
			sl._45,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._17) /* ExpCS|PatternExpCS */,
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
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(55, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS }
		private @NonNull SerializationRule _33 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._133 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : 57 */,
				ms._149 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 20 */,
				ms._032 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._012 /* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._054 /* 1*InfixExpCS::ownedLeft=57 || «null» */,
				st._067 /* 1*NamedElementCS::name=1 || «? » «value» «? » */,
				st._076 /* 1*OperatorExpCS::ownedRight=20 || «null» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					iv._20) /* PrefixedPrimaryExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._5) /* ExpCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(57, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _34 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._134 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 20 */,
				ms._013 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._014 /* 1*'Lambda' || «? » «value» «? » */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._055 /* 1*LambdaLiteralExpCS::ownedExpressionCS=20 || «null» */,
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
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _35 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._139 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 37 */,
				ms._138 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 36 */,
				ms._015 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._049 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._080 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._059 /* 1*MapLiteralExpCS::ownedType=37 || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._133 /* V00*steps-4..8 || «null» */,
				st._058 /* 1*MapLiteralExpCS::ownedParts+=36 || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._058 /* 1*MapLiteralExpCS::ownedParts+=36 || «null» */,
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
					new RuleIndex_MultiplicativeCardinality(37, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(36, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _36 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
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
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._035 /* 1*AbstractNameExpCS::ownedPathName=54 || «null» */,
				st._120 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=69 || «null» */,
				st._138 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=62 || «null» */,
				st._151 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=9 || «null» */,
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
					iv._23) /* SquareBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._15) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._21) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._3) /* CurlyBracketedClauseCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._3, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(69, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(54, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(9, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _37 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._148 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 20 */,
				ms._030 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._074 /* 1*NestedExpCS::ownedExpression=20 || «null» */,
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
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _38 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._031 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._075 /* 1*NumberLiteralExpCS::symbol=42 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::ExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _39 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._151 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 57 */,
				ms._032 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._068 /* 1*NamedElementCS::name=93 || «? » «value» «? » */,
				st._078 /* 1*OperatorExpCS::ownedRight=57 || «null» */
			},
			sl._39,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._20) /* PrefixedPrimaryExpCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(57, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _40 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._071 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._124 /* V00*StringLiteralExpCS::segments+=70 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE_OR_MORE)
					}
				)
			},
			null);
		// EssentialOCL::ExpCS : { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
		private @NonNull SerializationRule _41 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._157 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 76 */,
				ms._055 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._016 /* 1*'Tuple' || «? » «value» «? » */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._096 /* 1*TupleLiteralExpCS::ownedParts+=76 || «null» */,
				st._135 /* V00*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._096 /* 1*TupleLiteralExpCS::ownedParts+=76 || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._47,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._27) /* TupleLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(76, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _42 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._158 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 83 */,
				ms._041 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._098 /* 1*TypeLiteralExpCS::ownedType=83 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._32) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(83, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::IfExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _43 = new SerializationRule(25,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._127 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 20|55 */,
				ms._129 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 13 */,
				ms._128 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 20 */,
				ms._130 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 20 */,
				ms._008 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._062 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._009 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._007 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._023 /* 1*'if' || «? » «value» «? » */,
				st._049 /* 1*IfExpCS::ownedCondition=20|55 || «null» */,
				st._030 /* 1*'then' || «? » «value» «? » */,
				st._051 /* 1*IfExpCS::ownedThenExpression=20 || «null» */,
				st._121 /* V00*IfExpCS::ownedIfThenExpressions+=13 || «null» */,
				st._019 /* 1*'else' || «? » «value» «? » */,
				st._050 /* 1*IfExpCS::ownedElseExpression=20 || «null» */,
				st._021 /* 1*'endif' || «? » «value» «? » */
			},
			sl._45,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._17) /* ExpCS|PatternExpCS */,
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
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(55, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::InvalidLiteralExpCS : 'invalid'
		private @NonNull SerializationRule _44 = new SerializationRule(27,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._134 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 20 */,
				ms._013 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._014 /* 1*'Lambda' || «? » «value» «? » */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._055 /* 1*LambdaLiteralExpCS::ownedExpressionCS=20 || «null» */,
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
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::LetExpCS : { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
		private @NonNull SerializationRule _46 = new SerializationRule(31,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._136 /* check-rule essentialoclcs::LetExpCS.ownedVariables : 32 */,
				ms._135 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : 20 */,
				ms._014 /* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
				ms._048 /* assign V0 = (|LetExpCS::ownedVariables| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._026 /* 1*'let' || «? » «value» «? » */,
				st._057 /* 1*LetExpCS::ownedVariables+=32 || «null» */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._057 /* 1*LetExpCS::ownedVariables+=32 || «null» */,
				st._024 /* 1*'in' || «? » «value» «? » */,
				st._056 /* 1*LetExpCS::ownedInExpression=20 || «null» */
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
					new RuleIndex_MultiplicativeCardinality(32, GrammarCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::LetVariableCS : { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _47 = new SerializationRule(32,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._137 /* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 62 */,
				ms._162 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 20 */,
				ms._163 /* check-rule essentialoclcs::VariableCS.ownedType : 79 */,
				ms._045 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._092 /* assign V1 = |VariableCS::ownedType| */,
				ms._063 /* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*NamedElementCS::name=96 || «? » «value» «? » */,
				st._122 /* V00*LetVariableCS::ownedRoundBracketedClause=62 || «null» */,
				st._143 /* V01*steps-4..6 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._107 /* 1*VariableCS::ownedType=79 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._106 /* 1*VariableCS::ownedInitExpression=20 || «null» */
			},
			sl._42,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._21) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._29) /* TypeExpCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::MapLiteralExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _48 = new SerializationRule(35,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._139 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 37 */,
				ms._138 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 36 */,
				ms._015 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._049 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._080 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._059 /* 1*MapLiteralExpCS::ownedType=37 || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._133 /* V00*steps-4..8 || «null» */,
				st._058 /* 1*MapLiteralExpCS::ownedParts+=36 || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._058 /* 1*MapLiteralExpCS::ownedParts+=36 || «null» */,
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
					new RuleIndex_MultiplicativeCardinality(37, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(36, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::MapLiteralPartCS : { ownedKey=ExpCS '<-' ownedValue=ExpCS }
		private @NonNull SerializationRule _49 = new SerializationRule(36,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._141 /* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 20 */,
				ms._140 /* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 20 */,
				ms._017 /* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
				ms._016 /* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._060 /* 1*MapLiteralPartCS::ownedKey=20 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._061 /* 1*MapLiteralPartCS::ownedValue=20 || «null» */
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
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::MapTypeCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
		private @NonNull SerializationRule _50 = new SerializationRule(37,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._143 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 79 */,
				ms._142 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 79 */,
				ms._064 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._019 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._018 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._015 /* 1*'Map' || «? » «value» «? » */,
				st._130 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._062 /* 1*MapTypeCS::ownedKeyType=79 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._063 /* 1*MapTypeCS::ownedValueType=79 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._20,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._4)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._29) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._29) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._4, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::Model : ownedExpression=ExpCS
		private @NonNull SerializationRule _51 = new SerializationRule(38,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._125 /* check-rule essentialoclcs::ContextCS.ownedExpression : 20 */,
				ms._006 /* assert (|ContextCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._047 /* 1*ContextCS::ownedExpression=20 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NameExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _52 = new SerializationRule(43,
			new @NonNull SerializationMatchStep @NonNull [] {
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
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._035 /* 1*AbstractNameExpCS::ownedPathName=54 || «null» */,
				st._120 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=69 || «null» */,
				st._138 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=62 || «null» */,
				st._151 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=9 || «null» */,
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
					iv._23) /* SquareBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._15) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._21) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._3) /* CurlyBracketedClauseCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._3, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(69, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(54, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(62, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(9, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : ownedNameExpression=NavigatingArgExpCS
		private @NonNull SerializationRule _53 = new SerializationRule(44,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=45 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
					new RuleIndex_MultiplicativeCardinality(45, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _54 = new SerializationRule(44,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._147 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
				ms._026 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=79 || «null» */
			},
			sl._39,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._29) /* TypeExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _55 = new SerializationRule(44,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._144 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._147 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
				ms._088 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				ms._067 /* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				ms._026 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._109 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=45 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=79 || «null» */,
				st._135 /* V00*steps-5..7 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._070 /* 1*NavigatingArgCS::ownedCoIterator=3 || «null» */,
				st._149 /* V01*steps-8..10 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=20 || «null» */
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
					iv._29) /* TypeExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _56 = new SerializationRule(44,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._144 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._068 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._023 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._116 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=45 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._070 /* 1*NavigatingArgCS::ownedCoIterator=3 || «null» */,
				st._135 /* V00*steps-5..7 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=20 || «null» */
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
					new RuleIndex_MultiplicativeCardinality(45, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _57 = new SerializationRule(44,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._144 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._147 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
				ms._024 /* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				ms._087 /* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				ms._069 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._109 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=45 || «null» */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=79 || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._070 /* 1*NavigatingArgCS::ownedCoIterator=3 || «null» */,
				st._024 /* 1*'in' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=20 || «null» */
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
					iv._29) /* TypeExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingBarArgCS : { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _58 = new SerializationRule(46,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._147 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
				ms._069 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._029 /* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
				ms._088 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._032 /* 1*'|' || «? » «value» «? » */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=45 || «null» */,
				st._134 /* V00*steps-4..9 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=79 || «null» */,
				st._148 /* V01*steps-7..9 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=20 || «null» */
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
					iv._29) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._7, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _59 = new SerializationRule(47,
			new @NonNull SerializationMatchStep @NonNull [] {
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
			new @NonNull SerializationStep @NonNull [] {
				st._110 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=45 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=79 || «null» */,
				st._136 /* V00*steps-6..8 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._070 /* 1*NavigatingArgCS::ownedCoIterator=3 || «null» */,
				st._150 /* V01*steps-9..11 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=20 || «null» */
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
					iv._29) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._1, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _60 = new SerializationRule(47,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._144 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 3 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._068 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._023 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._027 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=45 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._070 /* 1*NavigatingArgCS::ownedCoIterator=3 || «null» */,
				st._136 /* V00*steps-6..8 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=20 || «null» */
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
						new EnumerationValue_MultiplicativeCardinality(ev._1, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _61 = new SerializationRule(47,
			new @NonNull SerializationMatchStep @NonNull [] {
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
			new @NonNull SerializationStep @NonNull [] {
				st._110 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=45 || «null» */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=79 || «null» */,
				st._148 /* V01*steps-7..9 || «null» */,
				st._010 /* 1*'<-' || «? » «value» «? » */,
				st._070 /* 1*NavigatingArgCS::ownedCoIterator=3 || «null» */,
				st._024 /* 1*'in' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=20 || «null» */
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
					iv._29) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._1, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(3, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS }
		private @NonNull SerializationRule _62 = new SerializationRule(47,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._027 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=45 || «null» */
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
						new EnumerationValue_MultiplicativeCardinality(ev._1, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingSemiArgCS : { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _63 = new SerializationRule(48,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._146 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 45 */,
				ms._145 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 20 */,
				ms._147 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 79 */,
				ms._069 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._025 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._028 /* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
				ms._088 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._009 /* 1*';' || «! » «value» «?\n» */,
				st._072 /* 1*NavigatingArgCS::ownedNameExpression=45 || «null» */,
				st._134 /* V00*steps-4..9 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._073 /* 1*NavigatingArgCS::ownedType=79 || «null» */,
				st._148 /* V01*steps-7..9 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._071 /* 1*NavigatingArgCS::ownedInitExpression=20 || «null» */
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
					iv._29) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._2, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(45, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
	}
	private class _SerializationRules1
	{
		// EssentialOCL::NestedExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _64 = new SerializationRule(50,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._148 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 20 */,
				ms._030 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._074 /* 1*NestedExpCS::ownedExpression=20 || «null» */,
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
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NullLiteralExpCS : 'null'
		private @NonNull SerializationRule _65 = new SerializationRule(52,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._031 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._075 /* 1*NumberLiteralExpCS::symbol=42 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
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
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::PatternExpCS : { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS }
		private @NonNull SerializationRule _67 = new SerializationRule(55,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._152 /* check-rule essentialoclcs::PatternExpCS.ownedPatternType : 79 */,
				ms._035 /* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
				ms._070 /* assign V0 = |PatternExpCS::patternVariableName| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._123 /* V00*PatternExpCS::patternVariableName=96 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._086 /* 1*PatternExpCS::ownedPatternType=79 || «null» */
			},
			sl._49,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					iv._29) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedLetExpCS : { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		private @NonNull SerializationRule _68 = new SerializationRule(56,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._150 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 56 */,
				ms._032 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._068 /* 1*NamedElementCS::name=93 || «? » «value» «? » */,
				st._077 /* 1*OperatorExpCS::ownedRight=56 || «null» */
			},
			sl._39,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._18) /* PrefixedLetExpCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(56, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedPrimaryExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _69 = new SerializationRule(57,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._151 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 57 */,
				ms._032 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._068 /* 1*NamedElementCS::name=93 || «? » «value» «? » */,
				st._078 /* 1*OperatorExpCS::ownedRight=57 || «null» */
			},
			sl._39,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					iv._20) /* PrefixedPrimaryExpCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(57, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrimitiveTypeCS : name=PrimitiveTypeIdentifier
		private @NonNull SerializationRule _70 = new SerializationRule(60,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._036 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._087 /* 1*PrimitiveTypeRefCS::name=61 || «? » «value» «? » */
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
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::RoundBracketedClauseCS : { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' }
		private @NonNull SerializationRule _71 = new SerializationRule(62,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._153 /* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 44|46|47|48 */,
				ms._051 /* assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0) */,
				ms._081 /* assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._115 /* 1*steps-1..6 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._088 /* 1*RoundBracketedClauseCS::ownedArguments+=44 || «null» */,
				st._140 /* V01*RoundBracketedClauseCS::ownedArguments+=47|48|46 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._08,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					iv._13) /* NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(44, GrammarCardinality.ZERO_OR_ONE),
					new RuleIndex_MultiplicativeCardinality(46, GrammarCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(47, GrammarCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(48, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::SelfExpCS : 'self'
		private @NonNull SerializationRule _72 = new SerializationRule(66,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._155 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 71 */,
				ms._037 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._090 /* 1*ShadowPartCS::ownedInitExpression=71 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._24) /* StringLiteralExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(71, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ShadowPartCS : { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) }
		private @NonNull SerializationRule _74 = new SerializationRule(67,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._154 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 20|55 */,
				ms._037 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
				ms._038 /* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._091 /* 1*ShadowPartCS::referredProperty=UnrestrictedName || «? » «value» «? » */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._089 /* 1*ShadowPartCS::ownedInitExpression=20|55 || «null» */
			},
			sl._49,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._17) /* ExpCS|PatternExpCS */
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
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE),
					new RuleIndex_MultiplicativeCardinality(55, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::SimplePathNameCS : ownedPathElements+=FirstPathElementCS
		private @NonNull SerializationRule _75 = new SerializationRule(68,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._098 /* check-rule basecs::PathNameCS.ownedPathElements : 21 */,
				ms._034 /* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._083 /* 1*PathNameCS::ownedPathElements+=21 || «null» */
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
					new RuleIndex_MultiplicativeCardinality(21, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::SquareBracketedClauseCS : { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' }
		private @NonNull SerializationRule _76 = new SerializationRule(69,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._156 /* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 20 */,
				ms._052 /* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._116 /* 1*steps-1..7 || «null» */,
				st._017 /* 1*'[' || «! » «value» «! » */,
				st._092 /* 1*SquareBracketedClauseCS::ownedTerms+=20 || «null» */,
				st._132 /* V00*steps-4..6 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._092 /* 1*SquareBracketedClauseCS::ownedTerms+=20 || «null» */,
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
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::StringLiteralExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _77 = new SerializationRule(71,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._071 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._124 /* V00*StringLiteralExpCS::segments+=70 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE_OR_MORE)
					}
				)
			},
			null);
		// EssentialOCL::TupleLiteralExpCS : { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' }
		private @NonNull SerializationRule _78 = new SerializationRule(75,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._157 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 76 */,
				ms._055 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._016 /* 1*'Tuple' || «? » «value» «? » */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._096 /* 1*TupleLiteralExpCS::ownedParts+=76 || «null» */,
				st._135 /* V00*steps-5..7 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._096 /* 1*TupleLiteralExpCS::ownedParts+=76 || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._47,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					iv._27) /* TupleLiteralPartCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(76, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::TupleLiteralPartCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _79 = new SerializationRule(76,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._162 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 20 */,
				ms._163 /* check-rule essentialoclcs::VariableCS.ownedType : 79 */,
				ms._045 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._075 /* assign V0 = |VariableCS::ownedType| */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._116 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*NamedElementCS::name=96 || «? » «value» «? » */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._107 /* 1*VariableCS::ownedType=79 || «null» */,
				st._011 /* 1*'=' || «? » «value» «? » */,
				st._106 /* 1*VariableCS::ownedInitExpression=20 || «null» */
			},
			sl._46,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._5) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._29) /* TypeExpCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TuplePartCS : { name=UnrestrictedName ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _80 = new SerializationRule(77,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._107 /* check-rule basecs::TypedElementCS.ownedType : 79 */,
				ms._043 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._022 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._113 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*NamedElementCS::name=96 || «? » «value» «? » */,
				st._007 /* 1*':' || «? » «value» «? » */,
				st._103 /* 1*TypedElementCS::ownedType=79 || «null» */
			},
			sl._49,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._29) /* TypeExpCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TupleTypeCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
		private @NonNull SerializationRule _81 = new SerializationRule(78,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._105 /* check-rule basecs::TupleTypeCS.ownedParts : 77 */,
				ms._040 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._056 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._082 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._093 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._109 /* 1*steps-1..10 || «null» */,
				st._016 /* 1*'Tuple' || «? » «value» «? » */,
				st._126 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._146 /* V01*steps-5..9 || «null» */,
				st._097 /* 1*TupleTypeCS::ownedParts+=77 || «null» */,
				st._153 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._097 /* 1*TupleTypeCS::ownedParts+=77 || «null» */,
				st._002 /* 1*')' || «! » «value» */
			},
			sl._16,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._5)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._28) /* TuplePartCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._5, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(77, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _82 = new SerializationRule(79,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._073 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._036 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || «null» */,
				st._087 /* 1*PrimitiveTypeRefCS::name=61 || «? » «value» «? » */,
				st._125 /* V00*TypedRefCS::ownedMultiplicity=40 || «null» */
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
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _83 = new SerializationRule(79,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._105 /* check-rule basecs::TupleTypeCS.ownedParts : 77 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._097 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._040 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._056 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._082 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._093 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._110 /* 1*steps-1..11 || «null» */,
				st._016 /* 1*'Tuple' || «? » «value» «? » */,
				st._126 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._146 /* V01*steps-5..9 || «null» */,
				st._097 /* 1*TupleTypeCS::ownedParts+=77 || «null» */,
				st._153 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._097 /* 1*TupleTypeCS::ownedParts+=77 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._154 /* V03*TypedRefCS::ownedMultiplicity=40 || «null» */
			},
			sl._17,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._5)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._28) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._5, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(77, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _84 = new SerializationRule(79,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._122 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 7 */,
				ms._121 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 55 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._095 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._060 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._078 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._004 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._111 /* 1*steps-1..12 || «null» */,
				st._043 /* 1*CollectionPatternCS::ownedType=7 || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._131 /* V00*steps-4..10 || «null» */,
				st._042 /* 1*CollectionPatternCS::ownedParts+=55 || «null» */,
				st._147 /* V01*steps-6..8 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._042 /* 1*CollectionPatternCS::ownedParts+=55 || «null» */,
				st._004 /* 1*'++' || «? » «value» «? » */,
				st._044 /* 1*CollectionPatternCS::restVariableName=24 || «? » «value» «? » */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._152 /* V02*TypedRefCS::ownedMultiplicity=40 || «null» */
			},
			sl._07,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._2) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._16) /* PatternExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(7, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(55, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _85 = new SerializationRule(79,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._123 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 40 */,
				ms._124 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 80 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._095 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._061 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._085 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._045 /* 1*CollectionTypeCS::name=8 || «? » «value» «? » */,
				st._129 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._046 /* 1*CollectionTypeCS::ownedType=80 || «null» */,
				st._139 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=40 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._152 /* V02*TypedRefCS::ownedMultiplicity=40 || «null» */
			},
			sl._19,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._10) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._30) /* TypeExpWithoutMultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
			},
			new /*@NonNull*/ EAttribute [] {
				EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(80, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _86 = new SerializationRule(79,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._143 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 79 */,
				ms._142 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 79 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._091 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._064 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._019 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._018 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || «null» */,
				st._015 /* 1*'Map' || «? » «value» «? » */,
				st._130 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._062 /* 1*MapTypeCS::ownedKeyType=79 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._063 /* 1*MapTypeCS::ownedValueType=79 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._142 /* V01*TypedRefCS::ownedMultiplicity=40 || «null» */
			},
			sl._21,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._4)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._29) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._29) /* TypeExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._4, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _87 = new SerializationRule(79,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._159 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 9 */,
				ms._160 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 54 */,
				ms._161 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 20 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._095 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._072 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._042 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._090 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || «null» */,
				st._100 /* 1*TypeNameExpCS::ownedPathName=54 || «null» */,
				st._130 /* V00*steps-3..8 || «null» */,
				st._099 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=9 || «null» */,
				st._145 /* V01*steps-5..8 || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._101 /* 1*TypeNameExpCS::ownedPatternGuard=20 || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._152 /* V02*TypedRefCS::ownedMultiplicity=40 || «null» */
			},
			sl._02,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._3) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._15) /* PathNameCS */,
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
					new RuleIndex_MultiplicativeCardinality(9, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(54, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _88 = new SerializationRule(82,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._158 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 83 */,
				ms._041 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._098 /* 1*TypeLiteralExpCS::ownedType=83 || supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport «value» */
			},
			sl._24,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._32) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(83, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _89 = new SerializationRule(83,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._073 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._036 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._112 /* 1*steps-1..3 || «null» */,
				st._087 /* 1*PrimitiveTypeRefCS::name=61 || «? » «value» «? » */,
				st._125 /* V00*TypedRefCS::ownedMultiplicity=40 || «null» */
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
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _90 = new SerializationRule(83,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._105 /* check-rule basecs::TupleTypeCS.ownedParts : 77 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._097 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._040 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._056 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._082 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._093 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._110 /* 1*steps-1..11 || «null» */,
				st._016 /* 1*'Tuple' || «? » «value» «? » */,
				st._126 /* V00*steps-3..10 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._146 /* V01*steps-5..9 || «null» */,
				st._097 /* 1*TupleTypeCS::ownedParts+=77 || «null» */,
				st._153 /* V02*steps-7..9 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._097 /* 1*TupleTypeCS::ownedParts+=77 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._154 /* V03*TypedRefCS::ownedMultiplicity=40 || «null» */
			},
			sl._17,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					ev._5)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					iv._28) /* TuplePartCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._5, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(77, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _91 = new SerializationRule(83,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._123 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 40 */,
				ms._124 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 80 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._095 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._061 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._005 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._085 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._045 /* 1*CollectionTypeCS::name=8 || «? » «value» «? » */,
				st._129 /* V00*steps-3..7 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._046 /* 1*CollectionTypeCS::ownedType=80 || «null» */,
				st._139 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=40 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._152 /* V02*TypedRefCS::ownedMultiplicity=40 || «null» */
			},
			sl._19,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._10) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._30) /* TypeExpWithoutMultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
			},
			new /*@NonNull*/ EAttribute [] {
				EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(80, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _92 = new SerializationRule(83,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._143 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 79 */,
				ms._142 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 79 */,
				ms._108 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 40 */,
				ms._091 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._064 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._019 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._018 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._118 /* 1*steps-1..9 || «null» */,
				st._015 /* 1*'Map' || «? » «value» «? » */,
				st._130 /* V00*steps-3..8 || «null» */,
				st._001 /* 1*'(' || «! » «value» «! » */,
				st._062 /* 1*MapTypeCS::ownedKeyType=79 || «null» */,
				st._005 /* 1*',' || «! » «value» «? » */,
				st._063 /* 1*MapTypeCS::ownedValueType=79 || «null» */,
				st._002 /* 1*')' || «! » «value» */,
				st._142 /* V01*TypedRefCS::ownedMultiplicity=40 || «null» */
			},
			sl._21,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._4)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._29) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._29) /* TypeExpCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._10) /* MultiplicityCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._4, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(79, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(40, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeNameExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
		private @NonNull SerializationRule _93 = new SerializationRule(84,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._159 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 9 */,
				ms._160 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 54 */,
				ms._161 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 20 */,
				ms._072 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._042 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._090 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._117 /* 1*steps-1..8 || «null» */,
				st._100 /* 1*TypeNameExpCS::ownedPathName=54 || «null» */,
				st._130 /* V00*steps-3..8 || «null» */,
				st._099 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=9 || «null» */,
				st._145 /* V01*steps-5..8 || «null» */,
				st._031 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._101 /* 1*TypeNameExpCS::ownedPatternGuard=20 || «null» */,
				st._034 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._01,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._3) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._15) /* PathNameCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._5) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(9, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(54, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_MultiplicativeCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(20, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::URIFirstPathElementCS : referredElement=UnrestrictedName
		private @NonNull SerializationRule _94 = new SerializationRule(91,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._033 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
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
		// EssentialOCL::URIFirstPathElementCS : referredElement=URI
		private @NonNull SerializationRule _95 = new SerializationRule(91,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._033 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
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
		// EssentialOCL::URIPathNameCS : { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] }
		private @NonNull SerializationRule _96 = new SerializationRule(92,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._100 /* check-rule basecs::PathNameCS.ownedPathElements : 51|91 */,
				ms._050 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*steps-1..5 || «null» */,
				st._085 /* 1*PathNameCS::ownedPathElements+=91 || «null» */,
				st._127 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'::' || «! » «value» «! » */,
				st._084 /* 1*PathNameCS::ownedPathElements+=51 || «null» */
			},
			sl._03,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._40) /* NextPathElementCS|URIFirstPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(51, GrammarCardinality.ZERO_OR_MORE),
					new RuleIndex_MultiplicativeCardinality(91, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS : '*'
		private @NonNull SerializationRule _97 = new SerializationRule(94,
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
//	import EAttribute_EnumerationValue_MultiplicativeCardinality;
//	import EAttribute_EnumerationValues;
//	import EReference_RuleIndex_MultiplicativeCardinality;
//	import EReference_RuleIndexes;
//	import EnumerationValue_MultiplicativeCardinality;
//	import RuleIndex_MultiplicativeCardinality;
//	import SerializationStep;
//	import SerializationStepAssignKeyword;
//	import SerializationStepAssignedRuleCall;
//	import SerializationStepAssigns;
//	import SerializationStepCrossReference;
//	import SerializationStepLiteral;
//	import SerializationStepSequence;
//	import TerminalRuleValue;
//	import BaseCSPackage;
//	import EssentialOCLCSPackage;
