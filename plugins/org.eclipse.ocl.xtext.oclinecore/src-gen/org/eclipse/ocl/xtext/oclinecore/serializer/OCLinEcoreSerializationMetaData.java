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
import org.eclipse.ocl.examples.xtext.serializer.AbstractSerializationMetaData;
import org.eclipse.ocl.examples.xtext.serializer.DataTypeRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue.SerializationRule_SegmentsList;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueMultiple;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueSingle;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.ParserRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.SerializationGrammarAnalysis;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchStep.MatchStep_Assert;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchStep.MatchStep_Assign;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchStep.MatchStep_RuleCheck;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEAttributeSize;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEStructuralFeatureSize;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermGreaterThan;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermInteger;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermSubtract;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermVariable;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EAttribute_EnumerationValue_GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EAttribute_EnumerationValues;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EReference_RuleIndex_GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EReference_RuleIndexes;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EnumerationValue_GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.RuleIndex_GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment.CustomSerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepAssignKeyword;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepAssignedRuleCall;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepAssigns;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepCrossReference;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepLiteral;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepSequence;
import org.eclipse.ocl.examples.xtext.serializer.TerminalRuleValue;
import org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage;

public class OCLinEcoreSerializationMetaData extends AbstractSerializationMetaData
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
				new GrammarRuleValue [] {
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
	private class _GrammarRuleVectors
	{
		private final @NonNull GrammarRuleVector _0 // AnnotationElementCS
			= new GrammarRuleVector(0x4L);
		private final @NonNull GrammarRuleVector _1 // ClassCS
			= new GrammarRuleVector(0x40L);
		private final @NonNull GrammarRuleVector _2 // CoIteratorVariableCS
			= new GrammarRuleVector(0x80L);
		private final @NonNull GrammarRuleVector _3 // CollectionLiteralPartCS
			= new GrammarRuleVector(0x200L);
		private final @NonNull GrammarRuleVector _4 // CollectionTypeCS
			= new GrammarRuleVector(0x800L);
		private final @NonNull GrammarRuleVector _5 // CurlyBracketedClauseCS
			= new GrammarRuleVector(0x2000L);
		private final @NonNull GrammarRuleVector _6 // DetailCS
			= new GrammarRuleVector(0x10000L);
		private final @NonNull GrammarRuleVector _7 // ElseIfThenExpCS
			= new GrammarRuleVector(0x100000L);
		private final @NonNull GrammarRuleVector _8 // EnumerationLiteralCS
			= new GrammarRuleVector(0x400000L);
		private final @NonNull GrammarRuleVector _9 // ExpCS
			= new GrammarRuleVector(0x40000000L);
		private final @NonNull GrammarRuleVector _10 // FirstPathElementCS
			= new GrammarRuleVector(0x80000000L);
		private final @NonNull GrammarRuleVector _11 // ImplicitOppositeCS
			= new GrammarRuleVector(0x2000000000L);
		private final @NonNull GrammarRuleVector _12 // ImportCS
			= new GrammarRuleVector(0x4000000000L);
		private final @NonNull GrammarRuleVector _13 // InvariantConstraintCS
			= new GrammarRuleVector(0x20000000000L);
		private final @NonNull GrammarRuleVector _14 // LetVariableCS
			= new GrammarRuleVector(0x400000000000L);
		private final @NonNull GrammarRuleVector _15 // MapLiteralPartCS
			= new GrammarRuleVector(0x4000000000000L);
		private final @NonNull GrammarRuleVector _16 // MapTypeCS
			= new GrammarRuleVector(0x8000000000000L);
		private final @NonNull GrammarRuleVector _17 // ModelElementCS
			= new GrammarRuleVector(0x20000000000000L);
		private final @NonNull GrammarRuleVector _18 // ModelElementRefCS
			= new GrammarRuleVector(0x40000000000000L);
		private final @NonNull GrammarRuleVector _19 // MultiplicityCS
			= new GrammarRuleVector(0x100000000000000L);
		private final @NonNull GrammarRuleVector _20 // NavigatingArgExpCS
			= new GrammarRuleVector(0x2000000000000000L);
		private final @NonNull GrammarRuleVector _21 // NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
			= new GrammarRuleVector(0xc000000000000000L,0x1L);
		private final @NonNull GrammarRuleVector _22 // NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS
			= new GrammarRuleVector(0xd000000000000000L,0x1L);
		private final @NonNull GrammarRuleVector _23 // FirstPathElementCS|NextPathElementCS
			= new GrammarRuleVector(0x80000000L,0x8L);
		private final @NonNull GrammarRuleVector _24 // OperationCS
			= new GrammarRuleVector(0x0L,0x40L);
		private final @NonNull GrammarRuleVector _25 // PackageCS
			= new GrammarRuleVector(0x0L,0x80L);
		private final @NonNull GrammarRuleVector _26 // ParameterCS
			= new GrammarRuleVector(0x0L,0x100L);
		private final @NonNull GrammarRuleVector _27 // PathNameCS
			= new GrammarRuleVector(0x0L,0x200L);
		private final @NonNull GrammarRuleVector _28 // PatternExpCS
			= new GrammarRuleVector(0x0L,0x400L);
		private final @NonNull GrammarRuleVector _29 // ExpCS|PatternExpCS
			= new GrammarRuleVector(0x40000000L,0x400L);
		private final @NonNull GrammarRuleVector _30 // PostconditionConstraintCS
			= new GrammarRuleVector(0x0L,0x800L);
		private final @NonNull GrammarRuleVector _31 // PreconditionConstraintCS
			= new GrammarRuleVector(0x0L,0x1000L);
		private final @NonNull GrammarRuleVector _32 // PrefixedLetExpCS
			= new GrammarRuleVector(0x0L,0x2000L);
		private final @NonNull GrammarRuleVector _33 // LetExpCS|PrefixedLetExpCS
			= new GrammarRuleVector(0x200000000000L,0x2000L);
		private final @NonNull GrammarRuleVector _34 // PrefixedPrimaryExpCS
			= new GrammarRuleVector(0x0L,0x4000L);
		private final @NonNull GrammarRuleVector _35 // RoundBracketedClauseCS
			= new GrammarRuleVector(0x0L,0x100000L);
		private final @NonNull GrammarRuleVector _36 // ML_SINGLE_QUOTED_STRING|SINGLE_QUOTED_STRING
			= new GrammarRuleVector(0x1000000000000L,0x800000L);
		private final @NonNull GrammarRuleVector _37 // ShadowPartCS
			= new GrammarRuleVector(0x0L,0x4000000L);
		private final @NonNull GrammarRuleVector _38 // SpecificationCS
			= new GrammarRuleVector(0x0L,0x10000000L);
		private final @NonNull GrammarRuleVector _39 // SquareBracketedClauseCS
			= new GrammarRuleVector(0x0L,0x20000000L);
		private final @NonNull GrammarRuleVector _40 // StringLiteralExpCS
			= new GrammarRuleVector(0x0L,0x80000000L);
		private final @NonNull GrammarRuleVector _41 // StructuralFeatureCS
			= new GrammarRuleVector(0x0L,0x100000000L);
		private final @NonNull GrammarRuleVector _42 // AttributeCS|ReferenceCS|StructuralFeatureCS
			= new GrammarRuleVector(0x8L,0x100080000L);
		private final @NonNull GrammarRuleVector _43 // ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS
			= new GrammarRuleVector(0x208040L,0x200000000L);
		private final @NonNull GrammarRuleVector _44 // AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS
			= new GrammarRuleVector(0x20000000608048L,0x3000800c0L);
		private final @NonNull GrammarRuleVector _45 // AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS
			= new GrammarRuleVector(0x20006L,0x400000000L);
		private final @NonNull GrammarRuleVector _46 // TemplateBindingCS
			= new GrammarRuleVector(0x0L,0x800000000L);
		private final @NonNull GrammarRuleVector _47 // TemplateParameterSubstitutionCS
			= new GrammarRuleVector(0x0L,0x1000000000L);
		private final @NonNull GrammarRuleVector _48 // TemplateSignatureCS
			= new GrammarRuleVector(0x0L,0x2000000000L);
		private final @NonNull GrammarRuleVector _49 // TupleLiteralPartCS
			= new GrammarRuleVector(0x0L,0x10000000000L);
		private final @NonNull GrammarRuleVector _50 // TuplePartCS
			= new GrammarRuleVector(0x0L,0x20000000000L);
		private final @NonNull GrammarRuleVector _51 // TypeExpCS
			= new GrammarRuleVector(0x0L,0x80000000000L);
		private final @NonNull GrammarRuleVector _52 // TypeExpWithoutMultiplicityCS
			= new GrammarRuleVector(0x0L,0x100000000000L);
		private final @NonNull GrammarRuleVector _53 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS
			= new GrammarRuleVector(0x8000000000800L,0x440000020000L);
		private final @NonNull GrammarRuleVector _54 // TypeLiteralWithMultiplicityCS
			= new GrammarRuleVector(0x0L,0x1000000000000L);
		private final @NonNull GrammarRuleVector _55 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeLiteralWithMultiplicityCS
			= new GrammarRuleVector(0x8000000000800L,0x1440000020000L);
		private final @NonNull GrammarRuleVector _56 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new GrammarRuleVector(0x8000000000c00L,0x2540000020000L);
		private final @NonNull GrammarRuleVector _57 // CollectionPatternCS|CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeExpCS|TypeExpWithoutMultiplicityCS|TypeLiteralCS|TypeNameExpCS
			= new GrammarRuleVector(0x8000000000c00L,0x25c0000020000L);
		private final @NonNull GrammarRuleVector _58 // TypeParameterCS
			= new GrammarRuleVector(0x0L,0x4000000000000L);
		private final @NonNull GrammarRuleVector _59 // TypeRefCS
			= new GrammarRuleVector(0x0L,0x8000000000000L);
		private final @NonNull GrammarRuleVector _60 // TypedMultiplicityRefCS
			= new GrammarRuleVector(0x0L,0x10000000000000L);
		private final @NonNull GrammarRuleVector _61 // TypedRefCS
			= new GrammarRuleVector(0x0L,0x20000000000000L);
		private final @NonNull GrammarRuleVector _62 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS
			= new GrammarRuleVector(0x8000000000800L,0x60440000020000L);
		private final @NonNull GrammarRuleVector _63 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedMultiplicityRefCS|TypedRefCS|TypedTypeRefCS
			= new GrammarRuleVector(0x8000000000800L,0x70440000020000L);
		private final @NonNull GrammarRuleVector _64 // NextPathElementCS|URIFirstPathElementCS
			= new GrammarRuleVector(0x0L,0x400000000000008L);
		private final @NonNull GrammarRuleVector _65 // FirstPathElementCS|NextPathElementCS|URIFirstPathElementCS
			= new GrammarRuleVector(0x80000000L,0x400000000000008L);
		private final @NonNull GrammarRuleVector _66 // URIPathNameCS
			= new GrammarRuleVector(0x0L,0x800000000000000L);
		private final @NonNull GrammarRuleVector _67 // BooleanLiteralExpCS|InvalidLiteralExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimitiveLiteralExpCS|StringLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0x10000000020L,0x2000000080010030L);
		private final @NonNull GrammarRuleVector _68 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0x802111000000120L,0x2000808082018034L);
		private final @NonNull GrammarRuleVector _69 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0x802111000000120L,0x200080808201c034L);
		private final @NonNull GrammarRuleVector _70 // BooleanLiteralExpCS|CollectionLiteralExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0x802311000000120L,0x200080808201e034L);
		private final @NonNull GrammarRuleVector _71 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0x802311040000120L,0x200080808201e034L);
		private final @NonNull GrammarRuleVector _72 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0x2802311040000120L,0x200080808201e034L);
		private final @NonNull GrammarRuleVector _73 // BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PatternExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS
			= new GrammarRuleVector(0x802311040000120L,0x200080808201e434L);
		private final @NonNull GrammarRuleVector _74 // SINGLE_QUOTED_STRING|UnrestrictedName
			= new GrammarRuleVector(0x0L,0x8000000000800000L);
		private final @NonNull GrammarRuleVector _75 // CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
			= new GrammarRuleVector(0x8000000000800L,0x68440000020000L,0x2L);
	}

	/**
	 * String combinations used by assigned String EAttributes
	 */
	private class _EnumValues
	{
		private final @NonNull EnumerationValue _00 // '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'
			= new EnumerationValueMultiple(new @NonNull String[]{"!composes", "!derived", "!ordered", "!readonly", "!resolve", "!transient", "!unique", "!unsettable", "!volatile", "composes", "derived", "ordered", "readonly", "resolve", "transient", "unique", "unsettable", "volatile"});
		private final @NonNull EnumerationValue _01 // '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'
			= new EnumerationValueMultiple(new @NonNull String[]{"!derived", "!id", "!ordered", "!readonly", "!transient", "!unique", "!unsettable", "!volatile", "derived", "id", "ordered", "readonly", "transient", "unique", "unsettable", "volatile"});
		private final @NonNull EnumerationValue _02 // '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'
			= new EnumerationValueMultiple(new @NonNull String[]{"!derived", "!ordered", "!transient", "!unique", "derived", "ordered", "transient", "unique"});
		private final @NonNull EnumerationValue _03 // '!ordered|!unique|ordered|unique'
			= new EnumerationValueMultiple(new @NonNull String[]{"!ordered", "!unique", "ordered", "unique"});
		private final @NonNull EnumerationValue _04 // '*|+|?'
			= new EnumerationValueMultiple(new @NonNull String[]{"*", "+", "?"});
		private final @NonNull EnumerationValue _05 // ','
			= new EnumerationValueSingle(",");
		private final @NonNull EnumerationValue _06 // '::*'
			= new EnumerationValueSingle("::*");
		private final @NonNull EnumerationValue _07 // ';'
			= new EnumerationValueSingle(";");
		private final @NonNull EnumerationValue _08 // '@'
			= new EnumerationValueSingle("@");
		private final @NonNull EnumerationValue _09 // 'Map'
			= new EnumerationValueSingle("Map");
		private final @NonNull EnumerationValue _10 // 'Tuple'
			= new EnumerationValueSingle("Tuple");
		private final @NonNull EnumerationValue _11 // 'abstract'
			= new EnumerationValueSingle("abstract");
		private final @NonNull EnumerationValue _12 // 'callable'
			= new EnumerationValueSingle("callable");
		private final @NonNull EnumerationValue _13 // 'definition'
			= new EnumerationValueSingle("definition");
		private final @NonNull EnumerationValue _14 // 'false|true'
			= new EnumerationValueMultiple(new @NonNull String[]{"false", "true"});
		private final @NonNull EnumerationValue _15 // 'interface'
			= new EnumerationValueSingle("interface");
		private final @NonNull EnumerationValue _16 // 'invariant'
			= new EnumerationValueSingle("invariant");
		private final @NonNull EnumerationValue _17 // 'postcondition'
			= new EnumerationValueSingle("postcondition");
		private final @NonNull EnumerationValue _18 // 'precondition'
			= new EnumerationValueSingle("precondition");
		private final @NonNull EnumerationValue _19 // 'primitive'
			= new EnumerationValueSingle("primitive");
		private final @NonNull EnumerationValue _20 // 'serializable'
			= new EnumerationValueSingle("serializable");
		private final @NonNull EnumerationValue _21 // 'static'
			= new EnumerationValueSingle("static");
		private final @NonNull EnumerationValue _22 // '|'
			= new EnumerationValueSingle("|");
		private final @NonNull EnumerationValue _23 // '|1'
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
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, ev._08);
		private final @NonNull SerializationMatchTerm _004 // |AbstractNameExpCS::ownedCurlyBracketedClause|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull SerializationMatchTerm _005 // |AbstractNameExpCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _006 // |AbstractNameExpCS::ownedRoundBracketedClause|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull SerializationMatchTerm _007 // |AbstractNameExpCS::ownedSquareBracketedClauses|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
		private final @NonNull SerializationMatchTerm _008 // |AnnotationCS::ownedContents|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS);
		private final @NonNull SerializationMatchTerm _009 // |AnnotationCS::ownedReferences|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES);
		private final @NonNull SerializationMatchTerm _010 // |AnnotationElementCS::ownedDetails|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
		private final @NonNull SerializationMatchTerm _011 // |BooleanLiteralExpCS::symbol.'false|true'|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._14);
		private final @NonNull SerializationMatchTerm _012 // |ClassCS::instanceClassName|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME);
		private final @NonNull SerializationMatchTerm _013 // |ClassCS::ownedConstraints|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
		private final @NonNull SerializationMatchTerm _014 // |CollectionLiteralExpCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _015 // |CollectionLiteralExpCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _016 // |CollectionLiteralPartCS::ownedExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
		private final @NonNull SerializationMatchTerm _017 // |CollectionLiteralPartCS::ownedLastExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
		private final @NonNull SerializationMatchTerm _018 // |CollectionPatternCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _019 // |CollectionPatternCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _020 // |CollectionPatternCS::restVariableName|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
		private final @NonNull SerializationMatchTerm _021 // |CollectionTypeCS::name|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
		private final @NonNull SerializationMatchTerm _022 // |CollectionTypeCS::ownedCollectionMultiplicity|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
		private final @NonNull SerializationMatchTerm _023 // |CollectionTypeCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _024 // |ConstraintCS::ownedMessageSpecification|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		private final @NonNull SerializationMatchTerm _025 // |ConstraintCS::ownedSpecification|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
		private final @NonNull SerializationMatchTerm _026 // |ConstraintCS::stereotype.'invariant'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._16);
		private final @NonNull SerializationMatchTerm _027 // |ConstraintCS::stereotype.'postcondition'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._17);
		private final @NonNull SerializationMatchTerm _028 // |ConstraintCS::stereotype.'precondition'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._18);
		private final @NonNull SerializationMatchTerm _029 // |ContextCS::ownedExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
		private final @NonNull SerializationMatchTerm _030 // |CurlyBracketedClauseCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _031 // |DataTypeCS::isPrimitive.'primitive'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, ev._19);
		private final @NonNull SerializationMatchTerm _032 // |DataTypeCS::isSerializable.'serializable'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE, ev._20);
		private final @NonNull SerializationMatchTerm _033 // |DetailCS::values|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.DETAIL_CS__VALUES);
		private final @NonNull SerializationMatchTerm _034 // |DocumentationCS::value|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
		private final @NonNull SerializationMatchTerm _035 // |EnumerationCS::isSerializable.'serializable'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE, ev._20);
		private final @NonNull SerializationMatchTerm _036 // |EnumerationCS::ownedLiterals|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
		private final @NonNull SerializationMatchTerm _037 // |EnumerationLiteralCS::literal|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL);
		private final @NonNull SerializationMatchTerm _038 // |EnumerationLiteralCS::value|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE);
		private final @NonNull SerializationMatchTerm _039 // |ExpSpecificationCS::ownedExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
		private final @NonNull SerializationMatchTerm _040 // |IfExpCS::ownedCondition|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
		private final @NonNull SerializationMatchTerm _041 // |IfExpCS::ownedElseExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
		private final @NonNull SerializationMatchTerm _042 // |IfExpCS::ownedIfThenExpressions|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		private final @NonNull SerializationMatchTerm _043 // |IfExpCS::ownedThenExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull SerializationMatchTerm _044 // |IfThenExpCS::ownedCondition|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
		private final @NonNull SerializationMatchTerm _045 // |IfThenExpCS::ownedThenExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
		private final @NonNull SerializationMatchTerm _046 // |ImportCS::isAll.'::*'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, ev._06);
		private final @NonNull SerializationMatchTerm _047 // |ImportCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _048 // |InfixExpCS::ownedLeft|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
		private final @NonNull SerializationMatchTerm _049 // |LambdaLiteralExpCS::ownedExpressionCS|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
		private final @NonNull SerializationMatchTerm _050 // |LetExpCS::ownedInExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
		private final @NonNull SerializationMatchTerm _051 // |LetExpCS::ownedVariables|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
		private final @NonNull SerializationMatchTerm _052 // |LetVariableCS::ownedRoundBracketedClause|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
		private final @NonNull SerializationMatchTerm _053 // |MapLiteralExpCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _054 // |MapLiteralExpCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _055 // |MapLiteralPartCS::ownedKey|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
		private final @NonNull SerializationMatchTerm _056 // |MapLiteralPartCS::ownedValue|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
		private final @NonNull SerializationMatchTerm _057 // |MapTypeCS::name.'Map'|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, ev._09);
		private final @NonNull SerializationMatchTerm _058 // |MapTypeCS::ownedKeyType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
		private final @NonNull SerializationMatchTerm _059 // |MapTypeCS::ownedValueType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
		private final @NonNull SerializationMatchTerm _060 // |ModelElementCS::ownedAnnotations|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		private final @NonNull SerializationMatchTerm _061 // |ModelElementRefCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _062 // |MultiplicityBoundsCS::lowerBound|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		private final @NonNull SerializationMatchTerm _063 // |MultiplicityBoundsCS::upperBound|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		private final @NonNull SerializationMatchTerm _064 // |MultiplicityCS::isNullFree.'|1'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, ev._23);
		private final @NonNull SerializationMatchTerm _065 // |MultiplicityStringCS::stringBounds.'*|+|?'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._04);
		private final @NonNull SerializationMatchTerm _066 // |NamedElementCS::name|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		private final @NonNull SerializationMatchTerm _067 // |NavigatingArgCS::ownedCoIterator|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
		private final @NonNull SerializationMatchTerm _068 // |NavigatingArgCS::ownedInitExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull SerializationMatchTerm _069 // |NavigatingArgCS::ownedNameExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
		private final @NonNull SerializationMatchTerm _070 // |NavigatingArgCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _071 // |NavigatingArgCS::prefix.','|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._05);
		private final @NonNull SerializationMatchTerm _072 // |NavigatingArgCS::prefix.';'|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._07);
		private final @NonNull SerializationMatchTerm _073 // |NavigatingArgCS::prefix.'|'|
			= new SerializationMatchTermEAttributeSize(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._22);
		private final @NonNull SerializationMatchTerm _074 // |NestedExpCS::ownedExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
		private final @NonNull SerializationMatchTerm _075 // |NumberLiteralExpCS::symbol|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
		private final @NonNull SerializationMatchTerm _076 // |OCLinEcoreConstraintCS::isCallable.'callable'|
			= new SerializationMatchTermEAttributeSize(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE, ev._12);
		private final @NonNull SerializationMatchTerm _077 // |OperationCS::ownedBodyExpressions|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		private final @NonNull SerializationMatchTerm _078 // |OperationCS::ownedExceptions|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
		private final @NonNull SerializationMatchTerm _079 // |OperationCS::ownedParameters|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
		private final @NonNull SerializationMatchTerm _080 // |OperationCS::ownedPostconditions|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
		private final @NonNull SerializationMatchTerm _081 // |OperationCS::ownedPreconditions|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
		private final @NonNull SerializationMatchTerm _082 // |OperatorExpCS::ownedRight|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
		private final @NonNull SerializationMatchTerm _083 // |PackageCS::nsPrefix|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
		private final @NonNull SerializationMatchTerm _084 // |PackageCS::nsURI|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
		private final @NonNull SerializationMatchTerm _085 // |PackageCS::ownedClasses|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
		private final @NonNull SerializationMatchTerm _086 // |PackageOwnerCS::ownedPackages|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
		private final @NonNull SerializationMatchTerm _087 // |PathElementCS::referredElement|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		private final @NonNull SerializationMatchTerm _088 // |PathNameCS::ownedPathElements|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		private final @NonNull SerializationMatchTerm _089 // |PatternExpCS::ownedPatternType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
		private final @NonNull SerializationMatchTerm _090 // |PatternExpCS::patternVariableName|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
		private final @NonNull SerializationMatchTerm _091 // |PrimitiveTypeRefCS::name|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
		private final @NonNull SerializationMatchTerm _092 // |ReferenceCS::ownedImplicitOpposites|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES);
		private final @NonNull SerializationMatchTerm _093 // |ReferenceCS::referredKeys|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS);
		private final @NonNull SerializationMatchTerm _094 // |ReferenceCS::referredOpposite|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE);
		private final @NonNull SerializationMatchTerm _095 // |RootCS::ownedImports|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
		private final @NonNull SerializationMatchTerm _096 // |RoundBracketedClauseCS::ownedArguments|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
		private final @NonNull SerializationMatchTerm _097 // |ShadowPartCS::ownedInitExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull SerializationMatchTerm _098 // |ShadowPartCS::referredProperty|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		private final @NonNull SerializationMatchTerm _099 // |SpecificationCS::exprString|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
		private final @NonNull SerializationMatchTerm _100 // |SquareBracketedClauseCS::ownedTerms|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		private final @NonNull SerializationMatchTerm _101 // |StringLiteralExpCS::segments|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
		private final @NonNull SerializationMatchTerm _102 // |StructuralFeatureCS::default|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
		private final @NonNull SerializationMatchTerm _103 // |StructuralFeatureCS::ownedDefaultExpressions|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		private final @NonNull SerializationMatchTerm _104 // |StructuredClassCS::isAbstract.'abstract'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, ev._11);
		private final @NonNull SerializationMatchTerm _105 // |StructuredClassCS::isInterface.'interface'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE, ev._15);
		private final @NonNull SerializationMatchTerm _106 // |StructuredClassCS::ownedOperations|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
		private final @NonNull SerializationMatchTerm _107 // |StructuredClassCS::ownedProperties|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
		private final @NonNull SerializationMatchTerm _108 // |StructuredClassCS::ownedSuperTypes|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
		private final @NonNull SerializationMatchTerm _109 // |TemplateBindingCS::ownedMultiplicity|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		private final @NonNull SerializationMatchTerm _110 // |TemplateBindingCS::ownedSubstitutions|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		private final @NonNull SerializationMatchTerm _111 // |TemplateParameterSubstitutionCS::ownedActualParameter|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		private final @NonNull SerializationMatchTerm _112 // |TemplateSignatureCS::ownedParameters|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		private final @NonNull SerializationMatchTerm _113 // |TemplateableElementCS::ownedSignature|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
		private final @NonNull SerializationMatchTerm _114 // |TupleLiteralExpCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _115 // |TupleTypeCS::name.'Tuple'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, ev._10);
		private final @NonNull SerializationMatchTerm _116 // |TupleTypeCS::ownedParts|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
		private final @NonNull SerializationMatchTerm _117 // |TypeLiteralExpCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _118 // |TypeNameExpCS::ownedCurlyBracketedClause|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
		private final @NonNull SerializationMatchTerm _119 // |TypeNameExpCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _120 // |TypeNameExpCS::ownedPatternGuard|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
		private final @NonNull SerializationMatchTerm _121 // |TypeParameterCS::ownedExtends|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		private final @NonNull SerializationMatchTerm _122 // |TypedElementCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _123 // |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._00);
		private final @NonNull SerializationMatchTerm _124 // |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._01);
		private final @NonNull SerializationMatchTerm _125 // |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._02);
		private final @NonNull SerializationMatchTerm _126 // |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._03);
		private final @NonNull SerializationMatchTerm _127 // |TypedElementCS::qualifiers.'definition'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._13);
		private final @NonNull SerializationMatchTerm _128 // |TypedElementCS::qualifiers.'static'|
			= new SerializationMatchTermEAttributeSize(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._21);
		private final @NonNull SerializationMatchTerm _129 // |TypedRefCS::ownedMultiplicity|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		private final @NonNull SerializationMatchTerm _130 // |TypedTypeRefCS::ownedBinding|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		private final @NonNull SerializationMatchTerm _131 // |TypedTypeRefCS::ownedPathName|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		private final @NonNull SerializationMatchTerm _132 // |VariableCS::ownedInitExpression|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
		private final @NonNull SerializationMatchTerm _133 // |VariableCS::ownedType|
			= new SerializationMatchTermEStructuralFeatureSize(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
		private final @NonNull SerializationMatchTerm _134 // |WildcardTypeRefCS::ownedExtends|
			= new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		private final @NonNull SerializationMatchTerm _135 // (|AbstractNameExpCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_005, _001);
		private final @NonNull SerializationMatchTerm _136 // (|AnnotationElementCS::ownedDetails| - 1)
			= new SerializationMatchTermSubtract(_010, _001);
		private final @NonNull SerializationMatchTerm _137 // (|AnnotationElementCS::ownedDetails| > 0)
			= new SerializationMatchTermGreaterThan(_010, _000);
		private final @NonNull SerializationMatchTerm _138 // (|BooleanLiteralExpCS::symbol.'false|true'| - 1)
			= new SerializationMatchTermSubtract(_011, _001);
		private final @NonNull SerializationMatchTerm _139 // (|CollectionLiteralExpCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_014, _001);
		private final @NonNull SerializationMatchTerm _140 // (|CollectionLiteralExpCS::ownedParts| > 0)
			= new SerializationMatchTermGreaterThan(_014, _000);
		private final @NonNull SerializationMatchTerm _141 // (|CollectionLiteralExpCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_015, _001);
		private final @NonNull SerializationMatchTerm _142 // (|CollectionLiteralPartCS::ownedExpression| - 1)
			= new SerializationMatchTermSubtract(_016, _001);
		private final @NonNull SerializationMatchTerm _143 // (|CollectionPatternCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_018, _001);
		private final @NonNull SerializationMatchTerm _144 // (|CollectionPatternCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_019, _001);
		private final @NonNull SerializationMatchTerm _145 // (|CollectionTypeCS::name| - 1)
			= new SerializationMatchTermSubtract(_021, _001);
		private final @NonNull SerializationMatchTerm _146 // (|ConstraintCS::stereotype.'invariant'| - 1)
			= new SerializationMatchTermSubtract(_026, _001);
		private final @NonNull SerializationMatchTerm _147 // (|ConstraintCS::stereotype.'postcondition'| - 1)
			= new SerializationMatchTermSubtract(_027, _001);
		private final @NonNull SerializationMatchTerm _148 // (|ConstraintCS::stereotype.'precondition'| - 1)
			= new SerializationMatchTermSubtract(_028, _001);
		private final @NonNull SerializationMatchTerm _149 // (|ContextCS::ownedExpression| - 1)
			= new SerializationMatchTermSubtract(_029, _001);
		private final @NonNull SerializationMatchTerm _150 // (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_030, _001);
		private final @NonNull SerializationMatchTerm _151 // (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new SerializationMatchTermGreaterThan(_030, _000);
		private final @NonNull SerializationMatchTerm _152 // (|DataTypeCS::isSerializable.'serializable'| > 0)
			= new SerializationMatchTermGreaterThan(_032, _000);
		private final @NonNull SerializationMatchTerm _153 // (|EnumerationCS::isSerializable.'serializable'| > 0)
			= new SerializationMatchTermGreaterThan(_035, _000);
		private final @NonNull SerializationMatchTerm _154 // (|ExpSpecificationCS::ownedExpression| - 1)
			= new SerializationMatchTermSubtract(_039, _001);
		private final @NonNull SerializationMatchTerm _155 // (|IfExpCS::ownedCondition| - 1)
			= new SerializationMatchTermSubtract(_040, _001);
		private final @NonNull SerializationMatchTerm _156 // (|IfExpCS::ownedElseExpression| - 1)
			= new SerializationMatchTermSubtract(_041, _001);
		private final @NonNull SerializationMatchTerm _157 // (|IfExpCS::ownedThenExpression| - 1)
			= new SerializationMatchTermSubtract(_043, _001);
		private final @NonNull SerializationMatchTerm _158 // (|IfThenExpCS::ownedCondition| - 1)
			= new SerializationMatchTermSubtract(_044, _001);
		private final @NonNull SerializationMatchTerm _159 // (|IfThenExpCS::ownedThenExpression| - 1)
			= new SerializationMatchTermSubtract(_045, _001);
		private final @NonNull SerializationMatchTerm _160 // (|ImportCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_047, _001);
		private final @NonNull SerializationMatchTerm _161 // (|InfixExpCS::ownedLeft| - 1)
			= new SerializationMatchTermSubtract(_048, _001);
		private final @NonNull SerializationMatchTerm _162 // (|LambdaLiteralExpCS::ownedExpressionCS| - 1)
			= new SerializationMatchTermSubtract(_049, _001);
		private final @NonNull SerializationMatchTerm _163 // (|LetExpCS::ownedInExpression| - 1)
			= new SerializationMatchTermSubtract(_050, _001);
		private final @NonNull SerializationMatchTerm _164 // (|LetExpCS::ownedVariables| - 1)
			= new SerializationMatchTermSubtract(_051, _001);
		private final @NonNull SerializationMatchTerm _165 // (|MapLiteralExpCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_053, _001);
		private final @NonNull SerializationMatchTerm _166 // (|MapLiteralExpCS::ownedParts| > 0)
			= new SerializationMatchTermGreaterThan(_053, _000);
		private final @NonNull SerializationMatchTerm _167 // (|MapLiteralExpCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_054, _001);
		private final @NonNull SerializationMatchTerm _168 // (|MapLiteralPartCS::ownedKey| - 1)
			= new SerializationMatchTermSubtract(_055, _001);
		private final @NonNull SerializationMatchTerm _169 // (|MapLiteralPartCS::ownedValue| - 1)
			= new SerializationMatchTermSubtract(_056, _001);
		private final @NonNull SerializationMatchTerm _170 // (|MapTypeCS::name.'Map'| - 1)
			= new SerializationMatchTermSubtract(_057, _001);
		private final @NonNull SerializationMatchTerm _171 // (|MapTypeCS::ownedKeyType| - V0)
			= new SerializationMatchTermSubtract(_058, _002);
		private final @NonNull SerializationMatchTerm _172 // (|ModelElementCS::ownedAnnotations| > 0)
			= new SerializationMatchTermGreaterThan(_060, _000);
		private final @NonNull SerializationMatchTerm _173 // (|ModelElementRefCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_061, _001);
		private final @NonNull SerializationMatchTerm _174 // (|MultiplicityBoundsCS::lowerBound| - 1)
			= new SerializationMatchTermSubtract(_062, _001);
		private final @NonNull SerializationMatchTerm _175 // (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
			= new SerializationMatchTermSubtract(_065, _001);
		private final @NonNull SerializationMatchTerm _176 // (|NamedElementCS::name| - 1)
			= new SerializationMatchTermSubtract(_066, _001);
		private final @NonNull SerializationMatchTerm _177 // (|NavigatingArgCS::ownedCoIterator| - 1)
			= new SerializationMatchTermSubtract(_067, _001);
		private final @NonNull SerializationMatchTerm _178 // (|NavigatingArgCS::ownedInitExpression| - 1)
			= new SerializationMatchTermSubtract(_068, _001);
		private final @NonNull SerializationMatchTerm _179 // (|NavigatingArgCS::ownedNameExpression| - 1)
			= new SerializationMatchTermSubtract(_069, _001);
		private final @NonNull SerializationMatchTerm _180 // (|NavigatingArgCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_070, _001);
		private final @NonNull SerializationMatchTerm _181 // (|NavigatingArgCS::prefix.','| - 1)
			= new SerializationMatchTermSubtract(_071, _001);
		private final @NonNull SerializationMatchTerm _182 // (|NavigatingArgCS::prefix.';'| - 1)
			= new SerializationMatchTermSubtract(_072, _001);
		private final @NonNull SerializationMatchTerm _183 // (|NavigatingArgCS::prefix.'|'| - 1)
			= new SerializationMatchTermSubtract(_073, _001);
		private final @NonNull SerializationMatchTerm _184 // (|NestedExpCS::ownedExpression| - 1)
			= new SerializationMatchTermSubtract(_074, _001);
		private final @NonNull SerializationMatchTerm _185 // (|NumberLiteralExpCS::symbol| - 1)
			= new SerializationMatchTermSubtract(_075, _001);
		private final @NonNull SerializationMatchTerm _186 // (|OperationCS::ownedBodyExpressions| > 0)
			= new SerializationMatchTermGreaterThan(_077, _000);
		private final @NonNull SerializationMatchTerm _187 // (|OperationCS::ownedExceptions| - 1)
			= new SerializationMatchTermSubtract(_078, _001);
		private final @NonNull SerializationMatchTerm _188 // (|OperationCS::ownedExceptions| > 0)
			= new SerializationMatchTermGreaterThan(_078, _000);
		private final @NonNull SerializationMatchTerm _189 // (|OperationCS::ownedParameters| - 1)
			= new SerializationMatchTermSubtract(_079, _001);
		private final @NonNull SerializationMatchTerm _190 // (|OperationCS::ownedParameters| > 0)
			= new SerializationMatchTermGreaterThan(_079, _000);
		private final @NonNull SerializationMatchTerm _191 // (|OperatorExpCS::ownedRight| - 1)
			= new SerializationMatchTermSubtract(_082, _001);
		private final @NonNull SerializationMatchTerm _192 // (|PathElementCS::referredElement| - 1)
			= new SerializationMatchTermSubtract(_087, _001);
		private final @NonNull SerializationMatchTerm _193 // (|PathNameCS::ownedPathElements| - 1)
			= new SerializationMatchTermSubtract(_088, _001);
		private final @NonNull SerializationMatchTerm _194 // (|PatternExpCS::ownedPatternType| - 1)
			= new SerializationMatchTermSubtract(_089, _001);
		private final @NonNull SerializationMatchTerm _195 // (|PrimitiveTypeRefCS::name| - 1)
			= new SerializationMatchTermSubtract(_091, _001);
		private final @NonNull SerializationMatchTerm _196 // (|ReferenceCS::referredKeys| - 1)
			= new SerializationMatchTermSubtract(_093, _001);
		private final @NonNull SerializationMatchTerm _197 // (|ReferenceCS::referredKeys| > 0)
			= new SerializationMatchTermGreaterThan(_093, _000);
		private final @NonNull SerializationMatchTerm _198 // (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new SerializationMatchTermSubtract(_096, _001);
		private final @NonNull SerializationMatchTerm _199 // (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new SerializationMatchTermGreaterThan(_096, _000);
		private final @NonNull SerializationMatchTerm _200 // (|ShadowPartCS::ownedInitExpression| - 1)
			= new SerializationMatchTermSubtract(_097, _001);
		private final @NonNull SerializationMatchTerm _201 // (|ShadowPartCS::referredProperty| - 1)
			= new SerializationMatchTermSubtract(_098, _001);
		private final @NonNull SerializationMatchTerm _202 // (|SpecificationCS::exprString| - 1)
			= new SerializationMatchTermSubtract(_099, _001);
		private final @NonNull SerializationMatchTerm _203 // (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new SerializationMatchTermSubtract(_100, _001);
		private final @NonNull SerializationMatchTerm _204 // (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
			= new SerializationMatchTermGreaterThan(_103, _000);
		private final @NonNull SerializationMatchTerm _205 // (|StructuredClassCS::isInterface.'interface'| > 0)
			= new SerializationMatchTermGreaterThan(_105, _000);
		private final @NonNull SerializationMatchTerm _206 // (|StructuredClassCS::ownedSuperTypes| - 1)
			= new SerializationMatchTermSubtract(_108, _001);
		private final @NonNull SerializationMatchTerm _207 // (|StructuredClassCS::ownedSuperTypes| > 0)
			= new SerializationMatchTermGreaterThan(_108, _000);
		private final @NonNull SerializationMatchTerm _208 // (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new SerializationMatchTermSubtract(_110, _001);
		private final @NonNull SerializationMatchTerm _209 // (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
			= new SerializationMatchTermSubtract(_111, _001);
		private final @NonNull SerializationMatchTerm _210 // (|TemplateSignatureCS::ownedParameters| - 1)
			= new SerializationMatchTermSubtract(_112, _001);
		private final @NonNull SerializationMatchTerm _211 // (|TupleLiteralExpCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_114, _001);
		private final @NonNull SerializationMatchTerm _212 // (|TupleTypeCS::name.'Tuple'| - 1)
			= new SerializationMatchTermSubtract(_115, _001);
		private final @NonNull SerializationMatchTerm _213 // (|TupleTypeCS::ownedParts| - 1)
			= new SerializationMatchTermSubtract(_116, _001);
		private final @NonNull SerializationMatchTerm _214 // (|TupleTypeCS::ownedParts| > 0)
			= new SerializationMatchTermGreaterThan(_116, _000);
		private final @NonNull SerializationMatchTerm _215 // (|TypeLiteralExpCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_117, _001);
		private final @NonNull SerializationMatchTerm _216 // (|TypeNameExpCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_119, _001);
		private final @NonNull SerializationMatchTerm _217 // (|TypeParameterCS::ownedExtends| - 1)
			= new SerializationMatchTermSubtract(_121, _001);
		private final @NonNull SerializationMatchTerm _218 // (|TypeParameterCS::ownedExtends| > 0)
			= new SerializationMatchTermGreaterThan(_121, _000);
		private final @NonNull SerializationMatchTerm _219 // (|TypedElementCS::ownedType| - 1)
			= new SerializationMatchTermSubtract(_122, _001);
		private final @NonNull SerializationMatchTerm _220 // (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new SerializationMatchTermGreaterThan(_123, _000);
		private final @NonNull SerializationMatchTerm _221 // (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new SerializationMatchTermGreaterThan(_124, _000);
		private final @NonNull SerializationMatchTerm _222 // (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new SerializationMatchTermGreaterThan(_125, _000);
		private final @NonNull SerializationMatchTerm _223 // (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new SerializationMatchTermGreaterThan(_126, _000);
		private final @NonNull SerializationMatchTerm _224 // (|TypedElementCS::qualifiers.'definition'| - 1)
			= new SerializationMatchTermSubtract(_127, _001);
		private final @NonNull SerializationMatchTerm _225 // (|TypedElementCS::qualifiers.'static'| - 1)
			= new SerializationMatchTermSubtract(_128, _001);
		private final @NonNull SerializationMatchTerm _226 // (|TypedTypeRefCS::ownedBinding| - 1)
			= new SerializationMatchTermSubtract(_130, _001);
		private final @NonNull SerializationMatchTerm _227 // (|TypedTypeRefCS::ownedPathName| - 1)
			= new SerializationMatchTermSubtract(_131, _001);
		private final @NonNull SerializationMatchTerm _228 // (|VariableCS::ownedInitExpression| - 1)
			= new SerializationMatchTermSubtract(_132, _001);
	}

	/**
	 * Steps for the matching process.
	 */
	private class _MatchSteps
	{
		private final @NonNull SerializationMatchStep _000 // assert (|AbstractNameExpCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._135);
		private final @NonNull SerializationMatchStep _001 // assert (|AnnotationElementCS::ownedDetails| - 1) == 0
			= new MatchStep_Assert(mt._136);
		private final @NonNull SerializationMatchStep _002 // assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0
			= new MatchStep_Assert(mt._138);
		private final @NonNull SerializationMatchStep _003 // assert (|CollectionLiteralExpCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._141);
		private final @NonNull SerializationMatchStep _004 // assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0
			= new MatchStep_Assert(mt._142);
		private final @NonNull SerializationMatchStep _005 // assert (|CollectionPatternCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._144);
		private final @NonNull SerializationMatchStep _006 // assert (|CollectionTypeCS::name| - 1) == 0
			= new MatchStep_Assert(mt._145);
		private final @NonNull SerializationMatchStep _007 // assert (|ConstraintCS::stereotype.'invariant'| - 1) == 0
			= new MatchStep_Assert(mt._146);
		private final @NonNull SerializationMatchStep _008 // assert (|ConstraintCS::stereotype.'postcondition'| - 1) == 0
			= new MatchStep_Assert(mt._147);
		private final @NonNull SerializationMatchStep _009 // assert (|ConstraintCS::stereotype.'precondition'| - 1) == 0
			= new MatchStep_Assert(mt._148);
		private final @NonNull SerializationMatchStep _010 // assert (|ContextCS::ownedExpression| - 1) == 0
			= new MatchStep_Assert(mt._149);
		private final @NonNull SerializationMatchStep _011 // assert (|ExpSpecificationCS::ownedExpression| - 1) == 0
			= new MatchStep_Assert(mt._154);
		private final @NonNull SerializationMatchStep _012 // assert (|IfExpCS::ownedCondition| - 1) == 0
			= new MatchStep_Assert(mt._155);
		private final @NonNull SerializationMatchStep _013 // assert (|IfExpCS::ownedElseExpression| - 1) == 0
			= new MatchStep_Assert(mt._156);
		private final @NonNull SerializationMatchStep _014 // assert (|IfExpCS::ownedThenExpression| - 1) == 0
			= new MatchStep_Assert(mt._157);
		private final @NonNull SerializationMatchStep _015 // assert (|IfThenExpCS::ownedCondition| - 1) == 0
			= new MatchStep_Assert(mt._158);
		private final @NonNull SerializationMatchStep _016 // assert (|IfThenExpCS::ownedThenExpression| - 1) == 0
			= new MatchStep_Assert(mt._159);
		private final @NonNull SerializationMatchStep _017 // assert (|ImportCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._160);
		private final @NonNull SerializationMatchStep _018 // assert (|InfixExpCS::ownedLeft| - 1) == 0
			= new MatchStep_Assert(mt._161);
		private final @NonNull SerializationMatchStep _019 // assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0
			= new MatchStep_Assert(mt._162);
		private final @NonNull SerializationMatchStep _020 // assert (|LetExpCS::ownedInExpression| - 1) == 0
			= new MatchStep_Assert(mt._163);
		private final @NonNull SerializationMatchStep _021 // assert (|MapLiteralExpCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._167);
		private final @NonNull SerializationMatchStep _022 // assert (|MapLiteralPartCS::ownedKey| - 1) == 0
			= new MatchStep_Assert(mt._168);
		private final @NonNull SerializationMatchStep _023 // assert (|MapLiteralPartCS::ownedValue| - 1) == 0
			= new MatchStep_Assert(mt._169);
		private final @NonNull SerializationMatchStep _024 // assert (|MapTypeCS::name.'Map'| - 1) == 0
			= new MatchStep_Assert(mt._170);
		private final @NonNull SerializationMatchStep _025 // assert (|MapTypeCS::ownedKeyType| - V0) == 0
			= new MatchStep_Assert(mt._171);
		private final @NonNull SerializationMatchStep _026 // assert (|ModelElementRefCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._173);
		private final @NonNull SerializationMatchStep _027 // assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
			= new MatchStep_Assert(mt._174);
		private final @NonNull SerializationMatchStep _028 // assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
			= new MatchStep_Assert(mt._175);
		private final @NonNull SerializationMatchStep _029 // assert (|NamedElementCS::name| - 1) == 0
			= new MatchStep_Assert(mt._176);
		private final @NonNull SerializationMatchStep _030 // assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0
			= new MatchStep_Assert(mt._177);
		private final @NonNull SerializationMatchStep _031 // assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0
			= new MatchStep_Assert(mt._178);
		private final @NonNull SerializationMatchStep _032 // assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0
			= new MatchStep_Assert(mt._179);
		private final @NonNull SerializationMatchStep _033 // assert (|NavigatingArgCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._180);
		private final @NonNull SerializationMatchStep _034 // assert (|NavigatingArgCS::prefix.','| - 1) == 0
			= new MatchStep_Assert(mt._181);
		private final @NonNull SerializationMatchStep _035 // assert (|NavigatingArgCS::prefix.';'| - 1) == 0
			= new MatchStep_Assert(mt._182);
		private final @NonNull SerializationMatchStep _036 // assert (|NavigatingArgCS::prefix.'|'| - 1) == 0
			= new MatchStep_Assert(mt._183);
		private final @NonNull SerializationMatchStep _037 // assert (|NestedExpCS::ownedExpression| - 1) == 0
			= new MatchStep_Assert(mt._184);
		private final @NonNull SerializationMatchStep _038 // assert (|NumberLiteralExpCS::symbol| - 1) == 0
			= new MatchStep_Assert(mt._185);
		private final @NonNull SerializationMatchStep _039 // assert (|OperatorExpCS::ownedRight| - 1) == 0
			= new MatchStep_Assert(mt._191);
		private final @NonNull SerializationMatchStep _040 // assert (|PathElementCS::referredElement| - 1) == 0
			= new MatchStep_Assert(mt._192);
		private final @NonNull SerializationMatchStep _041 // assert (|PathNameCS::ownedPathElements| - 1) == 0
			= new MatchStep_Assert(mt._193);
		private final @NonNull SerializationMatchStep _042 // assert (|PatternExpCS::ownedPatternType| - 1) == 0
			= new MatchStep_Assert(mt._194);
		private final @NonNull SerializationMatchStep _043 // assert (|PrimitiveTypeRefCS::name| - 1) == 0
			= new MatchStep_Assert(mt._195);
		private final @NonNull SerializationMatchStep _044 // assert (|ShadowPartCS::ownedInitExpression| - 1) == 0
			= new MatchStep_Assert(mt._200);
		private final @NonNull SerializationMatchStep _045 // assert (|ShadowPartCS::referredProperty| - 1) == 0
			= new MatchStep_Assert(mt._201);
		private final @NonNull SerializationMatchStep _046 // assert (|SpecificationCS::exprString| - 1) == 0
			= new MatchStep_Assert(mt._202);
		private final @NonNull SerializationMatchStep _047 // assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
			= new MatchStep_Assert(mt._209);
		private final @NonNull SerializationMatchStep _048 // assert (|TupleTypeCS::name.'Tuple'| - 1) == 0
			= new MatchStep_Assert(mt._212);
		private final @NonNull SerializationMatchStep _049 // assert (|TypeLiteralExpCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._215);
		private final @NonNull SerializationMatchStep _050 // assert (|TypeNameExpCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._216);
		private final @NonNull SerializationMatchStep _051 // assert (|TypedElementCS::ownedType| - 1) == 0
			= new MatchStep_Assert(mt._219);
		private final @NonNull SerializationMatchStep _052 // assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0
			= new MatchStep_Assert(mt._224);
		private final @NonNull SerializationMatchStep _053 // assert (|TypedElementCS::qualifiers.'static'| - 1) == 0
			= new MatchStep_Assert(mt._225);
		private final @NonNull SerializationMatchStep _054 // assert (|TypedTypeRefCS::ownedBinding| - 1) == 0
			= new MatchStep_Assert(mt._226);
		private final @NonNull SerializationMatchStep _055 // assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
			= new MatchStep_Assert(mt._227);
		private final @NonNull SerializationMatchStep _056 // assert (|VariableCS::ownedInitExpression| - 1) == 0
			= new MatchStep_Assert(mt._228);
		private final @NonNull SerializationMatchStep _057 // assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0)
			= new MatchStep_Assign(0, mt._140);
		private final @NonNull SerializationMatchStep _058 // assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0)
			= new MatchStep_Assign(0, mt._151);
		private final @NonNull SerializationMatchStep _059 // assign V0 = (|LetExpCS::ownedVariables| - 1)
			= new MatchStep_Assign(0, mt._164);
		private final @NonNull SerializationMatchStep _060 // assign V0 = (|MapLiteralExpCS::ownedParts| > 0)
			= new MatchStep_Assign(0, mt._166);
		private final @NonNull SerializationMatchStep _061 // assign V0 = (|PathNameCS::ownedPathElements| - 1)
			= new MatchStep_Assign(0, mt._193);
		private final @NonNull SerializationMatchStep _062 // assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0)
			= new MatchStep_Assign(0, mt._199);
		private final @NonNull SerializationMatchStep _063 // assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1)
			= new MatchStep_Assign(0, mt._203);
		private final @NonNull SerializationMatchStep _064 // assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
			= new MatchStep_Assign(0, mt._208);
		private final @NonNull SerializationMatchStep _065 // assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
			= new MatchStep_Assign(0, mt._210);
		private final @NonNull SerializationMatchStep _066 // assign V0 = (|TupleLiteralExpCS::ownedParts| - 1)
			= new MatchStep_Assign(0, mt._211);
		private final @NonNull SerializationMatchStep _067 // assign V0 = (|TupleTypeCS::ownedParts| > 0)
			= new MatchStep_Assign(0, mt._214);
		private final @NonNull SerializationMatchStep _068 // assign V0 = (|TypeParameterCS::ownedExtends| > 0)
			= new MatchStep_Assign(0, mt._218);
		private final @NonNull SerializationMatchStep _069 // assign V0 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new MatchStep_Assign(0, mt._223);
		private final @NonNull SerializationMatchStep _070 // assign V0 = 0
			= new MatchStep_Assign(0, mt._000);
		private final @NonNull SerializationMatchStep _071 // assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses|
			= new MatchStep_Assign(0, mt._007);
		private final @NonNull SerializationMatchStep _072 // assign V0 = |AnnotationElementCS::ownedDetails|
			= new MatchStep_Assign(0, mt._010);
		private final @NonNull SerializationMatchStep _073 // assign V0 = |CollectionLiteralPartCS::ownedLastExpression|
			= new MatchStep_Assign(0, mt._017);
		private final @NonNull SerializationMatchStep _074 // assign V0 = |CollectionPatternCS::restVariableName|
			= new MatchStep_Assign(0, mt._020);
		private final @NonNull SerializationMatchStep _075 // assign V0 = |CollectionTypeCS::ownedType|
			= new MatchStep_Assign(0, mt._023);
		private final @NonNull SerializationMatchStep _076 // assign V0 = |DataTypeCS::isPrimitive.'primitive'|
			= new MatchStep_Assign(0, mt._031);
		private final @NonNull SerializationMatchStep _077 // assign V0 = |DetailCS::values|
			= new MatchStep_Assign(0, mt._033);
		private final @NonNull SerializationMatchStep _078 // assign V0 = |DocumentationCS::value|
			= new MatchStep_Assign(0, mt._034);
		private final @NonNull SerializationMatchStep _079 // assign V0 = |EnumerationLiteralCS::literal|
			= new MatchStep_Assign(0, mt._037);
		private final @NonNull SerializationMatchStep _080 // assign V0 = |IfExpCS::ownedIfThenExpressions|
			= new MatchStep_Assign(0, mt._042);
		private final @NonNull SerializationMatchStep _081 // assign V0 = |LetVariableCS::ownedRoundBracketedClause|
			= new MatchStep_Assign(0, mt._052);
		private final @NonNull SerializationMatchStep _082 // assign V0 = |MapTypeCS::ownedValueType|
			= new MatchStep_Assign(0, mt._059);
		private final @NonNull SerializationMatchStep _083 // assign V0 = |MultiplicityBoundsCS::upperBound|
			= new MatchStep_Assign(0, mt._063);
		private final @NonNull SerializationMatchStep _084 // assign V0 = |MultiplicityCS::isNullFree.'|1'|
			= new MatchStep_Assign(0, mt._064);
		private final @NonNull SerializationMatchStep _085 // assign V0 = |NamedElementCS::name|
			= new MatchStep_Assign(0, mt._066);
		private final @NonNull SerializationMatchStep _086 // assign V0 = |NavigatingArgCS::ownedCoIterator|
			= new MatchStep_Assign(0, mt._067);
		private final @NonNull SerializationMatchStep _087 // assign V0 = |NavigatingArgCS::ownedInitExpression|
			= new MatchStep_Assign(0, mt._068);
		private final @NonNull SerializationMatchStep _088 // assign V0 = |NavigatingArgCS::ownedType|
			= new MatchStep_Assign(0, mt._070);
		private final @NonNull SerializationMatchStep _089 // assign V0 = |OCLinEcoreConstraintCS::isCallable.'callable'|
			= new MatchStep_Assign(0, mt._076);
		private final @NonNull SerializationMatchStep _090 // assign V0 = |PackageCS::nsPrefix|
			= new MatchStep_Assign(0, mt._083);
		private final @NonNull SerializationMatchStep _091 // assign V0 = |PatternExpCS::patternVariableName|
			= new MatchStep_Assign(0, mt._090);
		private final @NonNull SerializationMatchStep _092 // assign V0 = |ReferenceCS::referredOpposite|
			= new MatchStep_Assign(0, mt._094);
		private final @NonNull SerializationMatchStep _093 // assign V0 = |StringLiteralExpCS::segments|
			= new MatchStep_Assign(0, mt._101);
		private final @NonNull SerializationMatchStep _094 // assign V0 = |StructuredClassCS::isAbstract.'abstract'|
			= new MatchStep_Assign(0, mt._104);
		private final @NonNull SerializationMatchStep _095 // assign V0 = |TemplateableElementCS::ownedSignature|
			= new MatchStep_Assign(0, mt._113);
		private final @NonNull SerializationMatchStep _096 // assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause|
			= new MatchStep_Assign(0, mt._118);
		private final @NonNull SerializationMatchStep _097 // assign V0 = |TypedElementCS::ownedType|
			= new MatchStep_Assign(0, mt._122);
		private final @NonNull SerializationMatchStep _098 // assign V0 = |TypedElementCS::qualifiers.'definition'|
			= new MatchStep_Assign(0, mt._127);
		private final @NonNull SerializationMatchStep _099 // assign V0 = |TypedElementCS::qualifiers.'static'|
			= new MatchStep_Assign(0, mt._128);
		private final @NonNull SerializationMatchStep _100 // assign V0 = |TypedRefCS::ownedMultiplicity|
			= new MatchStep_Assign(0, mt._129);
		private final @NonNull SerializationMatchStep _101 // assign V0 = |VariableCS::ownedType|
			= new MatchStep_Assign(0, mt._133);
		private final @NonNull SerializationMatchStep _102 // assign V0 = |WildcardTypeRefCS::ownedExtends|
			= new MatchStep_Assign(0, mt._134);
		private final @NonNull SerializationMatchStep _103 // assign V1 = (|AnnotationElementCS::ownedDetails| > 0)
			= new MatchStep_Assign(1, mt._137);
		private final @NonNull SerializationMatchStep _104 // assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1)
			= new MatchStep_Assign(1, mt._139);
		private final @NonNull SerializationMatchStep _105 // assign V1 = (|CollectionPatternCS::ownedParts| - 1)
			= new MatchStep_Assign(1, mt._143);
		private final @NonNull SerializationMatchStep _106 // assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1)
			= new MatchStep_Assign(1, mt._150);
		private final @NonNull SerializationMatchStep _107 // assign V1 = (|MapLiteralExpCS::ownedParts| - 1)
			= new MatchStep_Assign(1, mt._165);
		private final @NonNull SerializationMatchStep _108 // assign V1 = (|OperationCS::ownedParameters| > 0)
			= new MatchStep_Assign(1, mt._190);
		private final @NonNull SerializationMatchStep _109 // assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1)
			= new MatchStep_Assign(1, mt._198);
		private final @NonNull SerializationMatchStep _110 // assign V1 = (|TupleTypeCS::ownedParts| > 0)
			= new MatchStep_Assign(1, mt._214);
		private final @NonNull SerializationMatchStep _111 // assign V1 = (|TypeParameterCS::ownedExtends| - 1)
			= new MatchStep_Assign(1, mt._217);
		private final @NonNull SerializationMatchStep _112 // assign V1 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0)
			= new MatchStep_Assign(1, mt._223);
		private final @NonNull SerializationMatchStep _113 // assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause|
			= new MatchStep_Assign(1, mt._006);
		private final @NonNull SerializationMatchStep _114 // assign V1 = |ClassCS::instanceClassName|
			= new MatchStep_Assign(1, mt._012);
		private final @NonNull SerializationMatchStep _115 // assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity|
			= new MatchStep_Assign(1, mt._022);
		private final @NonNull SerializationMatchStep _116 // assign V1 = |ConstraintCS::ownedMessageSpecification|
			= new MatchStep_Assign(1, mt._024);
		private final @NonNull SerializationMatchStep _117 // assign V1 = |EnumerationLiteralCS::value|
			= new MatchStep_Assign(1, mt._038);
		private final @NonNull SerializationMatchStep _118 // assign V1 = |ImportCS::isAll.'::*'|
			= new MatchStep_Assign(1, mt._046);
		private final @NonNull SerializationMatchStep _119 // assign V1 = |MultiplicityCS::isNullFree.'|1'|
			= new MatchStep_Assign(1, mt._064);
		private final @NonNull SerializationMatchStep _120 // assign V1 = |NamedElementCS::name|
			= new MatchStep_Assign(1, mt._066);
		private final @NonNull SerializationMatchStep _121 // assign V1 = |NavigatingArgCS::ownedCoIterator|
			= new MatchStep_Assign(1, mt._067);
		private final @NonNull SerializationMatchStep _122 // assign V1 = |NavigatingArgCS::ownedInitExpression|
			= new MatchStep_Assign(1, mt._068);
		private final @NonNull SerializationMatchStep _123 // assign V1 = |PackageCS::nsURI|
			= new MatchStep_Assign(1, mt._084);
		private final @NonNull SerializationMatchStep _124 // assign V1 = |ReferenceCS::referredOpposite|
			= new MatchStep_Assign(1, mt._094);
		private final @NonNull SerializationMatchStep _125 // assign V1 = |RootCS::ownedImports|
			= new MatchStep_Assign(1, mt._095);
		private final @NonNull SerializationMatchStep _126 // assign V1 = |StructuralFeatureCS::default|
			= new MatchStep_Assign(1, mt._102);
		private final @NonNull SerializationMatchStep _127 // assign V1 = |TemplateBindingCS::ownedMultiplicity|
			= new MatchStep_Assign(1, mt._109);
		private final @NonNull SerializationMatchStep _128 // assign V1 = |TemplateableElementCS::ownedSignature|
			= new MatchStep_Assign(1, mt._113);
		private final @NonNull SerializationMatchStep _129 // assign V1 = |TypeNameExpCS::ownedPatternGuard|
			= new MatchStep_Assign(1, mt._120);
		private final @NonNull SerializationMatchStep _130 // assign V1 = |TypedElementCS::ownedType|
			= new MatchStep_Assign(1, mt._122);
		private final @NonNull SerializationMatchStep _131 // assign V1 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'|
			= new MatchStep_Assign(1, mt._126);
		private final @NonNull SerializationMatchStep _132 // assign V1 = |TypedRefCS::ownedMultiplicity|
			= new MatchStep_Assign(1, mt._129);
		private final @NonNull SerializationMatchStep _133 // assign V1 = |VariableCS::ownedType|
			= new MatchStep_Assign(1, mt._133);
		private final @NonNull SerializationMatchStep _134 // assign V10 = (|OperationCS::ownedBodyExpressions| > 0)
			= new MatchStep_Assign(10, mt._186);
		private final @NonNull SerializationMatchStep _135 // assign V10 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
			= new MatchStep_Assign(10, mt._204);
		private final @NonNull SerializationMatchStep _136 // assign V10 = 0
			= new MatchStep_Assign(10, mt._000);
		private final @NonNull SerializationMatchStep _137 // assign V10 = |ClassCS::ownedConstraints|
			= new MatchStep_Assign(10, mt._013);
		private final @NonNull SerializationMatchStep _138 // assign V10 = |OperationCS::ownedPreconditions|
			= new MatchStep_Assign(10, mt._081);
		private final @NonNull SerializationMatchStep _139 // assign V11 = (|OperationCS::ownedBodyExpressions| > 0)
			= new MatchStep_Assign(11, mt._186);
		private final @NonNull SerializationMatchStep _140 // assign V11 = 0
			= new MatchStep_Assign(11, mt._000);
		private final @NonNull SerializationMatchStep _141 // assign V11 = |OperationCS::ownedBodyExpressions|
			= new MatchStep_Assign(11, mt._077);
		private final @NonNull SerializationMatchStep _142 // assign V12 = 0
			= new MatchStep_Assign(12, mt._000);
		private final @NonNull SerializationMatchStep _143 // assign V12 = |OperationCS::ownedBodyExpressions|
			= new MatchStep_Assign(12, mt._077);
		private final @NonNull SerializationMatchStep _144 // assign V12 = |OperationCS::ownedPostconditions|
			= new MatchStep_Assign(12, mt._080);
		private final @NonNull SerializationMatchStep _145 // assign V12 = |ReferenceCS::ownedImplicitOpposites|
			= new MatchStep_Assign(12, mt._092);
		private final @NonNull SerializationMatchStep _146 // assign V13 = |OperationCS::ownedPostconditions|
			= new MatchStep_Assign(13, mt._080);
		private final @NonNull SerializationMatchStep _147 // assign V13 = |ReferenceCS::ownedImplicitOpposites|
			= new MatchStep_Assign(13, mt._092);
		private final @NonNull SerializationMatchStep _148 // assign V2 = (|AnnotationElementCS::ownedDetails| - 1)
			= new MatchStep_Assign(2, mt._136);
		private final @NonNull SerializationMatchStep _149 // assign V2 = (|EnumerationCS::isSerializable.'serializable'| > 0)
			= new MatchStep_Assign(2, mt._153);
		private final @NonNull SerializationMatchStep _150 // assign V2 = (|OperationCS::ownedParameters| - 1)
			= new MatchStep_Assign(2, mt._189);
		private final @NonNull SerializationMatchStep _151 // assign V2 = (|OperationCS::ownedParameters| > 0)
			= new MatchStep_Assign(2, mt._190);
		private final @NonNull SerializationMatchStep _152 // assign V2 = (|StructuredClassCS::ownedSuperTypes| > 0)
			= new MatchStep_Assign(2, mt._207);
		private final @NonNull SerializationMatchStep _153 // assign V2 = (|TupleTypeCS::ownedParts| - 1)
			= new MatchStep_Assign(2, mt._213);
		private final @NonNull SerializationMatchStep _154 // assign V2 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new MatchStep_Assign(2, mt._221);
		private final @NonNull SerializationMatchStep _155 // assign V2 = 0
			= new MatchStep_Assign(2, mt._000);
		private final @NonNull SerializationMatchStep _156 // assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause|
			= new MatchStep_Assign(2, mt._004);
		private final @NonNull SerializationMatchStep _157 // assign V2 = |ClassCS::instanceClassName|
			= new MatchStep_Assign(2, mt._012);
		private final @NonNull SerializationMatchStep _158 // assign V2 = |ConstraintCS::ownedMessageSpecification|
			= new MatchStep_Assign(2, mt._024);
		private final @NonNull SerializationMatchStep _159 // assign V2 = |ConstraintCS::ownedSpecification|
			= new MatchStep_Assign(2, mt._025);
		private final @NonNull SerializationMatchStep _160 // assign V2 = |ModelElementCS::ownedAnnotations|
			= new MatchStep_Assign(2, mt._060);
		private final @NonNull SerializationMatchStep _161 // assign V2 = |PackageOwnerCS::ownedPackages|
			= new MatchStep_Assign(2, mt._086);
		private final @NonNull SerializationMatchStep _162 // assign V2 = |StructuralFeatureCS::default|
			= new MatchStep_Assign(2, mt._102);
		private final @NonNull SerializationMatchStep _163 // assign V2 = |TypedElementCS::ownedType|
			= new MatchStep_Assign(2, mt._122);
		private final @NonNull SerializationMatchStep _164 // assign V2 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'|
			= new MatchStep_Assign(2, mt._126);
		private final @NonNull SerializationMatchStep _165 // assign V2 = |TypedRefCS::ownedMultiplicity|
			= new MatchStep_Assign(2, mt._129);
		private final @NonNull SerializationMatchStep _166 // assign V3 = (|DataTypeCS::isSerializable.'serializable'| > 0)
			= new MatchStep_Assign(3, mt._152);
		private final @NonNull SerializationMatchStep _167 // assign V3 = (|ModelElementCS::ownedAnnotations| > 0)
			= new MatchStep_Assign(3, mt._172);
		private final @NonNull SerializationMatchStep _168 // assign V3 = (|OperationCS::ownedParameters| - 1)
			= new MatchStep_Assign(3, mt._189);
		private final @NonNull SerializationMatchStep _169 // assign V3 = (|StructuredClassCS::ownedSuperTypes| - 1)
			= new MatchStep_Assign(3, mt._206);
		private final @NonNull SerializationMatchStep _170 // assign V3 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new MatchStep_Assign(3, mt._220);
		private final @NonNull SerializationMatchStep _171 // assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
			= new MatchStep_Assign(3, mt._221);
		private final @NonNull SerializationMatchStep _172 // assign V3 = 0
			= new MatchStep_Assign(3, mt._000);
		private final @NonNull SerializationMatchStep _173 // assign V3 = |AbstractNameExpCS::isPre.'@'|
			= new MatchStep_Assign(3, mt._003);
		private final @NonNull SerializationMatchStep _174 // assign V3 = |ConstraintCS::ownedSpecification|
			= new MatchStep_Assign(3, mt._025);
		private final @NonNull SerializationMatchStep _175 // assign V3 = |EnumerationCS::isSerializable.'serializable'|
			= new MatchStep_Assign(3, mt._035);
		private final @NonNull SerializationMatchStep _176 // assign V3 = |ModelElementCS::ownedAnnotations|
			= new MatchStep_Assign(3, mt._060);
		private final @NonNull SerializationMatchStep _177 // assign V3 = |PackageOwnerCS::ownedPackages|
			= new MatchStep_Assign(3, mt._086);
		private final @NonNull SerializationMatchStep _178 // assign V3 = |StructuralFeatureCS::default|
			= new MatchStep_Assign(3, mt._102);
		private final @NonNull SerializationMatchStep _179 // assign V3 = |TypedElementCS::ownedType|
			= new MatchStep_Assign(3, mt._122);
		private final @NonNull SerializationMatchStep _180 // assign V3 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new MatchStep_Assign(3, mt._124);
		private final @NonNull SerializationMatchStep _181 // assign V3 = |TypedRefCS::ownedMultiplicity|
			= new MatchStep_Assign(3, mt._129);
		private final @NonNull SerializationMatchStep _182 // assign V4 = (|OperationCS::ownedExceptions| > 0)
			= new MatchStep_Assign(4, mt._188);
		private final @NonNull SerializationMatchStep _183 // assign V4 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
			= new MatchStep_Assign(4, mt._220);
		private final @NonNull SerializationMatchStep _184 // assign V4 = |AnnotationCS::ownedContents|
			= new MatchStep_Assign(4, mt._008);
		private final @NonNull SerializationMatchStep _185 // assign V4 = |ClassCS::instanceClassName|
			= new MatchStep_Assign(4, mt._012);
		private final @NonNull SerializationMatchStep _186 // assign V4 = |DataTypeCS::isSerializable.'serializable'|
			= new MatchStep_Assign(4, mt._032);
		private final @NonNull SerializationMatchStep _187 // assign V4 = |EnumerationCS::ownedLiterals|
			= new MatchStep_Assign(4, mt._036);
		private final @NonNull SerializationMatchStep _188 // assign V4 = |ModelElementCS::ownedAnnotations|
			= new MatchStep_Assign(4, mt._060);
		private final @NonNull SerializationMatchStep _189 // assign V4 = |PackageCS::ownedClasses|
			= new MatchStep_Assign(4, mt._085);
		private final @NonNull SerializationMatchStep _190 // assign V4 = |TypedElementCS::ownedType|
			= new MatchStep_Assign(4, mt._122);
		private final @NonNull SerializationMatchStep _191 // assign V4 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new MatchStep_Assign(4, mt._123);
		private final @NonNull SerializationMatchStep _192 // assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
			= new MatchStep_Assign(4, mt._124);
		private final @NonNull SerializationMatchStep _193 // assign V5 = (|OperationCS::ownedExceptions| - 1)
			= new MatchStep_Assign(5, mt._187);
		private final @NonNull SerializationMatchStep _194 // assign V5 = (|OperationCS::ownedExceptions| > 0)
			= new MatchStep_Assign(5, mt._188);
		private final @NonNull SerializationMatchStep _195 // assign V5 = (|StructuredClassCS::isInterface.'interface'| > 0)
			= new MatchStep_Assign(5, mt._205);
		private final @NonNull SerializationMatchStep _196 // assign V5 = |AnnotationCS::ownedReferences|
			= new MatchStep_Assign(5, mt._009);
		private final @NonNull SerializationMatchStep _197 // assign V5 = |ClassCS::ownedConstraints|
			= new MatchStep_Assign(5, mt._013);
		private final @NonNull SerializationMatchStep _198 // assign V5 = |EnumerationCS::ownedLiterals|
			= new MatchStep_Assign(5, mt._036);
		private final @NonNull SerializationMatchStep _199 // assign V5 = |ModelElementCS::ownedAnnotations|
			= new MatchStep_Assign(5, mt._060);
		private final @NonNull SerializationMatchStep _200 // assign V5 = |StructuralFeatureCS::ownedDefaultExpressions|
			= new MatchStep_Assign(5, mt._103);
		private final @NonNull SerializationMatchStep _201 // assign V5 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
			= new MatchStep_Assign(5, mt._123);
		private final @NonNull SerializationMatchStep _202 // assign V6 = (|OperationCS::ownedExceptions| - 1)
			= new MatchStep_Assign(6, mt._187);
		private final @NonNull SerializationMatchStep _203 // assign V6 = (|ReferenceCS::referredKeys| > 0)
			= new MatchStep_Assign(6, mt._197);
		private final @NonNull SerializationMatchStep _204 // assign V6 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
			= new MatchStep_Assign(6, mt._204);
		private final @NonNull SerializationMatchStep _205 // assign V6 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new MatchStep_Assign(6, mt._222);
		private final @NonNull SerializationMatchStep _206 // assign V6 = |ClassCS::ownedConstraints|
			= new MatchStep_Assign(6, mt._013);
		private final @NonNull SerializationMatchStep _207 // assign V6 = |ModelElementCS::ownedAnnotations|
			= new MatchStep_Assign(6, mt._060);
		private final @NonNull SerializationMatchStep _208 // assign V6 = |StructuralFeatureCS::ownedDefaultExpressions|
			= new MatchStep_Assign(6, mt._103);
		private final @NonNull SerializationMatchStep _209 // assign V6 = |StructuredClassCS::isInterface.'interface'|
			= new MatchStep_Assign(6, mt._105);
		private final @NonNull SerializationMatchStep _210 // assign V7 = (|ReferenceCS::referredKeys| - 1)
			= new MatchStep_Assign(7, mt._196);
		private final @NonNull SerializationMatchStep _211 // assign V7 = (|ReferenceCS::referredKeys| > 0)
			= new MatchStep_Assign(7, mt._197);
		private final @NonNull SerializationMatchStep _212 // assign V7 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
			= new MatchStep_Assign(7, mt._204);
		private final @NonNull SerializationMatchStep _213 // assign V7 = (|TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
			= new MatchStep_Assign(7, mt._222);
		private final @NonNull SerializationMatchStep _214 // assign V7 = 0
			= new MatchStep_Assign(7, mt._000);
		private final @NonNull SerializationMatchStep _215 // assign V7 = |ModelElementCS::ownedAnnotations|
			= new MatchStep_Assign(7, mt._060);
		private final @NonNull SerializationMatchStep _216 // assign V7 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new MatchStep_Assign(7, mt._125);
		private final @NonNull SerializationMatchStep _217 // assign V8 = (|ReferenceCS::referredKeys| - 1)
			= new MatchStep_Assign(8, mt._196);
		private final @NonNull SerializationMatchStep _218 // assign V8 = 0
			= new MatchStep_Assign(8, mt._000);
		private final @NonNull SerializationMatchStep _219 // assign V8 = |ModelElementCS::ownedAnnotations|
			= new MatchStep_Assign(8, mt._060);
		private final @NonNull SerializationMatchStep _220 // assign V8 = |StructuralFeatureCS::ownedDefaultExpressions|
			= new MatchStep_Assign(8, mt._103);
		private final @NonNull SerializationMatchStep _221 // assign V8 = |StructuredClassCS::ownedOperations|
			= new MatchStep_Assign(8, mt._106);
		private final @NonNull SerializationMatchStep _222 // assign V8 = |TypedElementCS::qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
			= new MatchStep_Assign(8, mt._125);
		private final @NonNull SerializationMatchStep _223 // assign V9 = (|StructuralFeatureCS::ownedDefaultExpressions| > 0)
			= new MatchStep_Assign(9, mt._204);
		private final @NonNull SerializationMatchStep _224 // assign V9 = 0
			= new MatchStep_Assign(9, mt._000);
		private final @NonNull SerializationMatchStep _225 // assign V9 = |ModelElementCS::ownedAnnotations|
			= new MatchStep_Assign(9, mt._060);
		private final @NonNull SerializationMatchStep _226 // assign V9 = |OperationCS::ownedPreconditions|
			= new MatchStep_Assign(9, mt._081);
		private final @NonNull SerializationMatchStep _227 // assign V9 = |StructuralFeatureCS::ownedDefaultExpressions|
			= new MatchStep_Assign(9, mt._103);
		private final @NonNull SerializationMatchStep _228 // assign V9 = |StructuredClassCS::ownedProperties|
			= new MatchStep_Assign(9, mt._107);
		private final @NonNull SerializationMatchStep _229 // check-rule basecs::AnnotationCS.ownedContents : 53
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, iv._17/*ModelElementCS*/);
		private final @NonNull SerializationMatchStep _230 // check-rule basecs::AnnotationCS.ownedReferences : 54
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, iv._18/*ModelElementRefCS*/);
		private final @NonNull SerializationMatchStep _231 // check-rule basecs::AnnotationElementCS.ownedDetails : 16
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, iv._6/*DetailCS*/);
		private final @NonNull SerializationMatchStep _232 // check-rule basecs::ClassCS.ownedConstraints : 41
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, iv._13/*InvariantConstraintCS*/);
		private final @NonNull SerializationMatchStep _233 // check-rule basecs::ConstraintCS.ownedMessageSpecification : 92
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, iv._38/*SpecificationCS*/);
		private final @NonNull SerializationMatchStep _234 // check-rule basecs::ConstraintCS.ownedSpecification : 92
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, iv._38/*SpecificationCS*/);
		private final @NonNull SerializationMatchStep _235 // check-rule basecs::EnumerationCS.ownedLiterals : 22
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, iv._8/*EnumerationLiteralCS*/);
		private final @NonNull SerializationMatchStep _236 // check-rule basecs::ImportCS.ownedPathName : 123
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, iv._66/*URIPathNameCS*/);
		private final @NonNull SerializationMatchStep _237 // check-rule basecs::ModelElementCS.ownedAnnotations : 2
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, iv._0/*AnnotationElementCS*/);
		private final @NonNull SerializationMatchStep _238 // check-rule basecs::ModelElementRefCS.ownedPathName : 73
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, iv._27/*PathNameCS*/);
		private final @NonNull SerializationMatchStep _239 // check-rule basecs::OperationCS.ownedBodyExpressions : 92
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, iv._38/*SpecificationCS*/);
		private final @NonNull SerializationMatchStep _240 // check-rule basecs::OperationCS.ownedExceptions : 117
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, iv._61/*TypedRefCS*/);
		private final @NonNull SerializationMatchStep _241 // check-rule basecs::OperationCS.ownedParameters : 72
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, iv._26/*ParameterCS*/);
		private final @NonNull SerializationMatchStep _242 // check-rule basecs::OperationCS.ownedPostconditions : 75
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, iv._30/*PostconditionConstraintCS*/);
		private final @NonNull SerializationMatchStep _243 // check-rule basecs::OperationCS.ownedPreconditions : 76
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, iv._31/*PreconditionConstraintCS*/);
		private final @NonNull SerializationMatchStep _244 // check-rule basecs::PackageCS.ownedClasses : 6
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, iv._1/*ClassCS*/);
		private final @NonNull SerializationMatchStep _245 // check-rule basecs::PackageOwnerCS.ownedPackages : 71
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, iv._25/*PackageCS*/);
		private final @NonNull SerializationMatchStep _246 // check-rule basecs::PathNameCS.ownedPathElements : 31
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._10/*FirstPathElementCS*/);
		private final @NonNull SerializationMatchStep _247 // check-rule basecs::PathNameCS.ownedPathElements : 31|67
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._23/*FirstPathElementCS|NextPathElementCS*/);
		private final @NonNull SerializationMatchStep _248 // check-rule basecs::PathNameCS.ownedPathElements : 67|122
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, iv._64/*NextPathElementCS|URIFirstPathElementCS*/);
		private final @NonNull SerializationMatchStep _249 // check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, iv._11/*ImplicitOppositeCS*/);
		private final @NonNull SerializationMatchStep _250 // check-rule basecs::RootCS.ownedImports : 38
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, iv._12/*ImportCS*/);
		private final @NonNull SerializationMatchStep _251 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, iv._38/*SpecificationCS*/);
		private final @NonNull SerializationMatchStep _252 // check-rule basecs::StructuredClassCS.ownedOperations : 70
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, iv._24/*OperationCS*/);
		private final @NonNull SerializationMatchStep _253 // check-rule basecs::StructuredClassCS.ownedProperties : 96
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, iv._41/*StructuralFeatureCS*/);
		private final @NonNull SerializationMatchStep _254 // check-rule basecs::StructuredClassCS.ownedSuperTypes : 117
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, iv._61/*TypedRefCS*/);
		private final @NonNull SerializationMatchStep _255 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : 56
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, iv._19/*MultiplicityCS*/);
		private final @NonNull SerializationMatchStep _256 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : 100
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, iv._47/*TemplateParameterSubstitutionCS*/);
		private final @NonNull SerializationMatchStep _257 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 115
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, iv._59/*TypeRefCS*/);
		private final @NonNull SerializationMatchStep _258 // check-rule basecs::TemplateSignatureCS.ownedParameters : 114
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, iv._58/*TypeParameterCS*/);
		private final @NonNull SerializationMatchStep _259 // check-rule basecs::TemplateableElementCS.ownedSignature : 101
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, iv._48/*TemplateSignatureCS*/);
		private final @NonNull SerializationMatchStep _260 // check-rule basecs::TupleTypeCS.ownedParts : 105
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, iv._50/*TuplePartCS*/);
		private final @NonNull SerializationMatchStep _261 // check-rule basecs::TypeParameterCS.ownedExtends : 117
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, iv._61/*TypedRefCS*/);
		private final @NonNull SerializationMatchStep _262 // check-rule basecs::TypedElementCS.ownedType : 107
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, iv._51/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _263 // check-rule basecs::TypedElementCS.ownedType : 116
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, iv._60/*TypedMultiplicityRefCS*/);
		private final @NonNull SerializationMatchStep _264 // check-rule basecs::TypedRefCS.ownedMultiplicity : 56
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, iv._19/*MultiplicityCS*/);
		private final @NonNull SerializationMatchStep _265 // check-rule basecs::TypedTypeRefCS.ownedBinding : 99
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, iv._46/*TemplateBindingCS*/);
		private final @NonNull SerializationMatchStep _266 // check-rule basecs::TypedTypeRefCS.ownedPathName : 73
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, iv._27/*PathNameCS*/);
		private final @NonNull SerializationMatchStep _267 // check-rule basecs::WildcardTypeRefCS.ownedExtends : 117
			= new MatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, iv._61/*TypedRefCS*/);
		private final @NonNull SerializationMatchStep _268 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._5/*CurlyBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _269 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, iv._27/*PathNameCS*/);
		private final @NonNull SerializationMatchStep _270 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._35/*RoundBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _271 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, iv._39/*SquareBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _272 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, iv._3/*CollectionLiteralPartCS*/);
		private final @NonNull SerializationMatchStep _273 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, iv._4/*CollectionTypeCS*/);
		private final @NonNull SerializationMatchStep _274 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _275 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 74
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, iv._28/*PatternExpCS*/);
		private final @NonNull SerializationMatchStep _276 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _277 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, iv._28/*PatternExpCS*/);
		private final @NonNull SerializationMatchStep _278 // check-rule essentialoclcs::CollectionPatternCS.ownedType : 11
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, iv._4/*CollectionTypeCS*/);
		private final @NonNull SerializationMatchStep _279 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, iv._19/*MultiplicityCS*/);
		private final @NonNull SerializationMatchStep _280 // check-rule essentialoclcs::CollectionTypeCS.ownedType : 108
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, iv._52/*TypeExpWithoutMultiplicityCS*/);
		private final @NonNull SerializationMatchStep _281 // check-rule essentialoclcs::ContextCS.ownedExpression : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _282 // check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 90
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, iv._37/*ShadowPartCS*/);
		private final @NonNull SerializationMatchStep _283 // check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _284 // check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, iv._29/*ExpCS|PatternExpCS*/);
		private final @NonNull SerializationMatchStep _285 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _286 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, iv._7/*ElseIfThenExpCS*/);
		private final @NonNull SerializationMatchStep _287 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _288 // check-rule essentialoclcs::IfThenExpCS.ownedCondition : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _289 // check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _290 // check-rule essentialoclcs::InfixExpCS.ownedLeft : 78
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, iv._34/*PrefixedPrimaryExpCS*/);
		private final @NonNull SerializationMatchStep _291 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _292 // check-rule essentialoclcs::LetExpCS.ownedInExpression : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _293 // check-rule essentialoclcs::LetExpCS.ownedVariables : 46
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, iv._14/*LetVariableCS*/);
		private final @NonNull SerializationMatchStep _294 // check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 84
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, iv._35/*RoundBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _295 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, iv._15/*MapLiteralPartCS*/);
		private final @NonNull SerializationMatchStep _296 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, iv._16/*MapTypeCS*/);
		private final @NonNull SerializationMatchStep _297 // check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _298 // check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _299 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, iv._51/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _300 // check-rule essentialoclcs::MapTypeCS.ownedValueType : 107
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, iv._51/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _301 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, iv._2/*CoIteratorVariableCS*/);
		private final @NonNull SerializationMatchStep _302 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _303 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, iv._20/*NavigatingArgExpCS*/);
		private final @NonNull SerializationMatchStep _304 // check-rule essentialoclcs::NavigatingArgCS.ownedType : 107
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, iv._51/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _305 // check-rule essentialoclcs::NestedExpCS.ownedExpression : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _306 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _307 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 77
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._32/*PrefixedLetExpCS*/);
		private final @NonNull SerializationMatchStep _308 // check-rule essentialoclcs::OperatorExpCS.ownedRight : 78
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, iv._34/*PrefixedPrimaryExpCS*/);
		private final @NonNull SerializationMatchStep _309 // check-rule essentialoclcs::PatternExpCS.ownedPatternType : 107
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, iv._51/*TypeExpCS*/);
		private final @NonNull SerializationMatchStep _310 // check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 60|62|63|64
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, iv._22/*NavigatingArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingSemiArgCS*/);
		private final @NonNull SerializationMatchStep _311 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 30|74
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._29/*ExpCS|PatternExpCS*/);
		private final @NonNull SerializationMatchStep _312 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 95
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, iv._40/*StringLiteralExpCS*/);
		private final @NonNull SerializationMatchStep _313 // check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _314 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, iv._49/*TupleLiteralPartCS*/);
		private final @NonNull SerializationMatchStep _315 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, iv._54/*TypeLiteralWithMultiplicityCS*/);
		private final @NonNull SerializationMatchStep _316 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, iv._5/*CurlyBracketedClauseCS*/);
		private final @NonNull SerializationMatchStep _317 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, iv._27/*PathNameCS*/);
		private final @NonNull SerializationMatchStep _318 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _319 // check-rule essentialoclcs::VariableCS.ownedInitExpression : 30
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, iv._9/*ExpCS*/);
		private final @NonNull SerializationMatchStep _320 // check-rule essentialoclcs::VariableCS.ownedType : 107
			= new MatchStep_RuleCheck(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, iv._51/*TypeExpCS*/);
	}

	/**
	 * The various serialization term used to serialize a serialization rule.
	 */
	private class _SerializationTerms
	{
		private final @NonNull SerializationStepLiteral _000 // 1*'!serializable'
									= new SerializationStepLiteral(-1, "!serializable");
		private final @NonNull SerializationStepLiteral _001 // 1*'#'
									= new SerializationStepLiteral(-1, "#");
		private final @NonNull SerializationStepLiteral _002 // 1*'&&'
									= new SerializationStepLiteral(-1, "&&");
		private final @NonNull SerializationStepLiteral _003 // 1*'('
									= new SerializationStepLiteral(-1, "(");
		private final @NonNull SerializationStepLiteral _004 // 1*')'
									= new SerializationStepLiteral(-1, ")");
		private final @NonNull SerializationStepLiteral _005 // 1*'*'
									= new SerializationStepLiteral(-1, "*");
		private final @NonNull SerializationStepLiteral _006 // 1*'++'
									= new SerializationStepLiteral(-1, "++");
		private final @NonNull SerializationStepLiteral _007 // 1*','
									= new SerializationStepLiteral(-1, ",");
		private final @NonNull SerializationStepLiteral _008 // 1*'..'
									= new SerializationStepLiteral(-1, "..");
		private final @NonNull SerializationStepLiteral _009 // 1*':'
									= new SerializationStepLiteral(-1, ":");
		private final @NonNull SerializationStepLiteral _010 // 1*'::'
									= new SerializationStepLiteral(-1, "::");
		private final @NonNull SerializationStepLiteral _011 // 1*';'
									= new SerializationStepLiteral(-1, ";");
		private final @NonNull SerializationStepLiteral _012 // 1*'<'
									= new SerializationStepLiteral(-1, "<");
		private final @NonNull SerializationStepLiteral _013 // 1*'<-'
									= new SerializationStepLiteral(-1, "<-");
		private final @NonNull SerializationStepLiteral _014 // 1*'='
									= new SerializationStepLiteral(-1, "=");
		private final @NonNull SerializationStepLiteral _015 // 1*'>'
									= new SerializationStepLiteral(-1, ">");
		private final @NonNull SerializationStepLiteral _016 // 1*'?'
									= new SerializationStepLiteral(-1, "?");
		private final @NonNull SerializationStepLiteral _017 // 1*'@'
									= new SerializationStepLiteral(-1, "@");
		private final @NonNull SerializationStepLiteral _018 // 1*'Lambda'
									= new SerializationStepLiteral(-1, "Lambda");
		private final @NonNull SerializationStepLiteral _019 // 1*'Map'
									= new SerializationStepLiteral(-1, "Map");
		private final @NonNull SerializationStepLiteral _020 // 1*'Tuple'
									= new SerializationStepLiteral(-1, "Tuple");
		private final @NonNull SerializationStepLiteral _021 // 1*'['
									= new SerializationStepLiteral(-1, "[");
		private final @NonNull SerializationStepLiteral _022 // 1*']'
									= new SerializationStepLiteral(-1, "]");
		private final @NonNull SerializationStepLiteral _023 // 1*'annotation'
									= new SerializationStepLiteral(-1, "annotation");
		private final @NonNull SerializationStepLiteral _024 // 1*'attribute'
									= new SerializationStepLiteral(-1, "attribute");
		private final @NonNull SerializationStepLiteral _025 // 1*'body'
									= new SerializationStepLiteral(-1, "body");
		private final @NonNull SerializationStepLiteral _026 // 1*'class'
									= new SerializationStepLiteral(-1, "class");
		private final @NonNull SerializationStepLiteral _027 // 1*'datatype'
									= new SerializationStepLiteral(-1, "datatype");
		private final @NonNull SerializationStepLiteral _028 // 1*'definition'
									= new SerializationStepLiteral(-1, "definition");
		private final @NonNull SerializationStepLiteral _029 // 1*'derivation'
									= new SerializationStepLiteral(-1, "derivation");
		private final @NonNull SerializationStepLiteral _030 // 1*'documentation'
									= new SerializationStepLiteral(-1, "documentation");
		private final @NonNull SerializationStepLiteral _031 // 1*'else'
									= new SerializationStepLiteral(-1, "else");
		private final @NonNull SerializationStepLiteral _032 // 1*'elseif'
									= new SerializationStepLiteral(-1, "elseif");
		private final @NonNull SerializationStepLiteral _033 // 1*'endif'
									= new SerializationStepLiteral(-1, "endif");
		private final @NonNull SerializationStepLiteral _034 // 1*'enum'
									= new SerializationStepLiteral(-1, "enum");
		private final @NonNull SerializationStepLiteral _035 // 1*'extends'
									= new SerializationStepLiteral(-1, "extends");
		private final @NonNull SerializationStepLiteral _036 // 1*'if'
									= new SerializationStepLiteral(-1, "if");
		private final @NonNull SerializationStepLiteral _037 // 1*'import'
									= new SerializationStepLiteral(-1, "import");
		private final @NonNull SerializationStepLiteral _038 // 1*'in'
									= new SerializationStepLiteral(-1, "in");
		private final @NonNull SerializationStepLiteral _039 // 1*'initial'
									= new SerializationStepLiteral(-1, "initial");
		private final @NonNull SerializationStepLiteral _040 // 1*'invalid'
									= new SerializationStepLiteral(-1, "invalid");
		private final @NonNull SerializationStepLiteral _041 // 1*'invariant'
									= new SerializationStepLiteral(-1, "invariant");
		private final @NonNull SerializationStepLiteral _042 // 1*'key'
									= new SerializationStepLiteral(-1, "key");
		private final @NonNull SerializationStepLiteral _043 // 1*'let'
									= new SerializationStepLiteral(-1, "let");
		private final @NonNull SerializationStepLiteral _044 // 1*'literal'
									= new SerializationStepLiteral(-1, "literal");
		private final @NonNull SerializationStepLiteral _045 // 1*'module'
									= new SerializationStepLiteral(-1, "module");
		private final @NonNull SerializationStepLiteral _046 // 1*'null'
									= new SerializationStepLiteral(-1, "null");
		private final @NonNull SerializationStepLiteral _047 // 1*'operation'
									= new SerializationStepLiteral(-1, "operation");
		private final @NonNull SerializationStepLiteral _048 // 1*'opposite'
									= new SerializationStepLiteral(-1, "opposite");
		private final @NonNull SerializationStepLiteral _049 // 1*'package'
									= new SerializationStepLiteral(-1, "package");
		private final @NonNull SerializationStepLiteral _050 // 1*'postcondition'
									= new SerializationStepLiteral(-1, "postcondition");
		private final @NonNull SerializationStepLiteral _051 // 1*'pre'
									= new SerializationStepLiteral(-1, "pre");
		private final @NonNull SerializationStepLiteral _052 // 1*'precondition'
									= new SerializationStepLiteral(-1, "precondition");
		private final @NonNull SerializationStepLiteral _053 // 1*'property'
									= new SerializationStepLiteral(-1, "property");
		private final @NonNull SerializationStepLiteral _054 // 1*'reference'
									= new SerializationStepLiteral(-1, "reference");
		private final @NonNull SerializationStepLiteral _055 // 1*'self'
									= new SerializationStepLiteral(-1, "self");
		private final @NonNull SerializationStepLiteral _056 // 1*'static'
									= new SerializationStepLiteral(-1, "static");
		private final @NonNull SerializationStepLiteral _057 // 1*'sysml'
									= new SerializationStepLiteral(-1, "sysml");
		private final @NonNull SerializationStepLiteral _058 // 1*'then'
									= new SerializationStepLiteral(-1, "then");
		private final @NonNull SerializationStepLiteral _059 // 1*'throws'
									= new SerializationStepLiteral(-1, "throws");
		private final @NonNull SerializationStepLiteral _060 // 1*'{'
									= new SerializationStepLiteral(-1, "{");
		private final @NonNull SerializationStepLiteral _061 // 1*'|'
									= new SerializationStepLiteral(-1, "|");
		private final @NonNull SerializationStepLiteral _062 // 1*'|?'
									= new SerializationStepLiteral(-1, "|?");
		private final @NonNull SerializationStepLiteral _063 // 1*'}'
									= new SerializationStepLiteral(-1, "}");
		private final @NonNull SerializationStepAssignedRuleCall _064 // 1*AbstractNameExpCS::ownedPathName=73
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, 73 /* PathNameCS */);
		private final @NonNull SerializationStepAssignedRuleCall _065 // 1*AnnotationElementCS::ownedDetails+=16
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, 16 /* DetailCS */);
		private final @NonNull SerializationStepAssignKeyword _066 // 1*BooleanLiteralExpCS::symbol='false|true'
									= new SerializationStepAssignKeyword(-1, EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._14);
		private final @NonNull SerializationStepAssignedRuleCall _067 // 1*ClassCS::instanceClassName=87
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, 87 /* SINGLE_QUOTED_STRING */);
		private final @NonNull SerializationStepAssignedRuleCall _068 // 1*CollectionLiteralExpCS::ownedParts+=9
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, 9 /* CollectionLiteralPartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _069 // 1*CollectionLiteralExpCS::ownedType=11
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, 11 /* CollectionTypeCS */);
		private final @NonNull SerializationStepAssignedRuleCall _070 // 1*CollectionLiteralPartCS::ownedExpression=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _071 // 1*CollectionLiteralPartCS::ownedExpression=74
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, 74 /* PatternExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _072 // 1*CollectionLiteralPartCS::ownedLastExpression=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _073 // 1*CollectionPatternCS::ownedParts+=74
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, 74 /* PatternExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _074 // 1*CollectionPatternCS::ownedType=11
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, 11 /* CollectionTypeCS */);
		private final @NonNull SerializationStepAssignedRuleCall _075 // 1*CollectionPatternCS::restVariableName=35
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, 35 /* Identifier */);
		private final @NonNull SerializationStepAssignedRuleCall _076 // 1*CollectionTypeCS::name=12
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME, 12 /* CollectionTypeIdentifier */);
		private final @NonNull SerializationStepAssignedRuleCall _077 // 1*CollectionTypeCS::ownedType=108
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, 108 /* TypeExpWithoutMultiplicityCS */);
		private final @NonNull SerializationStepAssignedRuleCall _078 // 1*ConstraintCS::ownedMessageSpecification=92
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, 92 /* SpecificationCS */);
		private final @NonNull SerializationStepAssignedRuleCall _079 // 1*ContextCS::ownedExpression=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _080 // 1*CurlyBracketedClauseCS::ownedParts+=90
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, 90 /* ShadowPartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _081 // 1*EnumerationLiteralCS::literal=87
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL, 87 /* SINGLE_QUOTED_STRING */);
		private final @NonNull SerializationStepAssignedRuleCall _082 // 1*EnumerationLiteralCS::value=85
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE, 85 /* SIGNED */);
		private final @NonNull SerializationStepAssignedRuleCall _083 // 1*ExpSpecificationCS::ownedExpression=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssigns _084 // 1*IfExpCS::ownedCondition=30|74
									= new SerializationStepAssigns(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, null, new @NonNull Integer [] { 30/*ExpCS*/,74/*PatternExpCS*/});
		private final @NonNull SerializationStepAssignedRuleCall _085 // 1*IfExpCS::ownedElseExpression=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _086 // 1*IfExpCS::ownedThenExpression=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _087 // 1*IfThenExpCS::ownedCondition=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _088 // 1*IfThenExpCS::ownedThenExpression=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _089 // 1*ImportCS::ownedPathName=123
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, 123 /* URIPathNameCS */);
		private final @NonNull SerializationStepAssignedRuleCall _090 // 1*InfixExpCS::ownedLeft=78
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, 78 /* PrefixedPrimaryExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _091 // 1*LambdaLiteralExpCS::ownedExpressionCS=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _092 // 1*LetExpCS::ownedInExpression=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _093 // 1*LetExpCS::ownedVariables+=46
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, 46 /* LetVariableCS */);
		private final @NonNull SerializationStepAssignedRuleCall _094 // 1*MapLiteralExpCS::ownedParts+=50
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, 50 /* MapLiteralPartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _095 // 1*MapLiteralExpCS::ownedType=51
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, 51 /* MapTypeCS */);
		private final @NonNull SerializationStepAssignedRuleCall _096 // 1*MapLiteralPartCS::ownedKey=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _097 // 1*MapLiteralPartCS::ownedValue=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _098 // 1*MapTypeCS::ownedKeyType=107
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, 107 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _099 // 1*MapTypeCS::ownedValueType=107
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, 107 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _100 // 1*ModelElementRefCS::ownedPathName=73
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, 73 /* PathNameCS */);
		private final @NonNull SerializationStepAssignedRuleCall _101 // 1*MultiplicityBoundsCS::lowerBound=43
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 43 /* LOWER */);
		private final @NonNull SerializationStepAssignedRuleCall _102 // 1*MultiplicityBoundsCS::upperBound=120
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 120 /* UPPER */);
		private final @NonNull SerializationStepAssignKeyword _103 // 1*MultiplicityStringCS::stringBounds='*|+|?'
									= new SerializationStepAssignKeyword(-1, BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._04);
		private final @NonNull SerializationStepAssignedRuleCall _104 // 1*NamedElementCS::name=124
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 124 /* UnaryOperatorName */);
		private final @NonNull SerializationStepAssignedRuleCall _105 // 1*NamedElementCS::name=127
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 127 /* UnrestrictedName */);
		private final @NonNull SerializationStepAssigns _106 // 1*NamedElementCS::name=127|87
									= new SerializationStepAssigns(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, null, new @NonNull Integer [] { 127/*UnrestrictedName*/,87/*SINGLE_QUOTED_STRING*/});
		private final @NonNull SerializationStepAssignedRuleCall _107 // 1*NamedElementCS::name=23
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 23 /* EnumerationLiteralName */);
		private final @NonNull SerializationStepAssignedRuleCall _108 // 1*NamedElementCS::name=4
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 4 /* BinaryOperatorName */);
		private final @NonNull SerializationStepAssignedRuleCall _109 // 1*NavigatingArgCS::ownedCoIterator=7
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, 7 /* CoIteratorVariableCS */);
		private final @NonNull SerializationStepAssignedRuleCall _110 // 1*NavigatingArgCS::ownedInitExpression=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _111 // 1*NavigatingArgCS::ownedNameExpression=61
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, 61 /* NavigatingArgExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _112 // 1*NavigatingArgCS::ownedType=107
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, 107 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _113 // 1*NestedExpCS::ownedExpression=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _114 // 1*NumberLiteralExpCS::symbol=58
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL, 58 /* NUMBER_LITERAL */);
		private final @NonNull SerializationStepAssignedRuleCall _115 // 1*OperationCS::ownedExceptions+=117
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, 117 /* TypedRefCS */);
		private final @NonNull SerializationStepAssignedRuleCall _116 // 1*OperationCS::ownedParameters+=72
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, 72 /* ParameterCS */);
		private final @NonNull SerializationStepAssignedRuleCall _117 // 1*OperatorExpCS::ownedRight=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _118 // 1*OperatorExpCS::ownedRight=77
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 77 /* PrefixedLetExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _119 // 1*OperatorExpCS::ownedRight=78
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, 78 /* PrefixedPrimaryExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _120 // 1*PackageCS::nsPrefix=127
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, 127 /* UnrestrictedName */);
		private final @NonNull SerializationStepAssignedRuleCall _121 // 1*PackageCS::nsURI=121
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PACKAGE_CS__NS_URI, 121 /* URI */);
		private final @NonNull SerializationStepCrossReference _122 // 1*PathElementCS::referredElement=URI
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "URI"));
		private final @NonNull SerializationStepCrossReference _123 // 1*PathElementCS::referredElement=UnreservedName
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"));
		private final @NonNull SerializationStepCrossReference _124 // 1*PathElementCS::referredElement=UnrestrictedName
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"));
		private final @NonNull SerializationStepAssignedRuleCall _125 // 1*PathNameCS::ownedPathElements+=122
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 122 /* URIFirstPathElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _126 // 1*PathNameCS::ownedPathElements+=31
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 31 /* FirstPathElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _127 // 1*PathNameCS::ownedPathElements+=67
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 67 /* NextPathElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _128 // 1*PatternExpCS::ownedPatternType=107
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, 107 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _129 // 1*PrimitiveTypeRefCS::name=82
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, 82 /* PrimitiveTypeIdentifier */);
		private final @NonNull SerializationStepAssignedRuleCall _130 // 1*ReferenceCS::ownedImplicitOpposites+=37
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, 37 /* ImplicitOppositeCS */);
		private final @NonNull SerializationStepCrossReference _131 // 1*ReferenceCS::referredKeys+=UnrestrictedName
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, getCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, "UnrestrictedName"));
		private final @NonNull SerializationStepCrossReference _132 // 1*ReferenceCS::referredOpposite=UnrestrictedName
									= new SerializationStepCrossReference(-1, BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, getCrossReference(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, "UnrestrictedName"));
		private final @NonNull SerializationStepAssignedRuleCall _133 // 1*RoundBracketedClauseCS::ownedArguments+=60
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, 60 /* NavigatingArgCS */);
		private final @NonNull SerializationStepAssigns _134 // 1*ShadowPartCS::ownedInitExpression=30|74
									= new SerializationStepAssigns(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, null, new @NonNull Integer [] { 30/*ExpCS*/,74/*PatternExpCS*/});
		private final @NonNull SerializationStepAssignedRuleCall _135 // 1*ShadowPartCS::ownedInitExpression=95
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, 95 /* StringLiteralExpCS */);
		private final @NonNull SerializationStepCrossReference _136 // 1*ShadowPartCS::referredProperty=UnrestrictedName
									= new SerializationStepCrossReference(-1, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, getCrossReference(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "UnrestrictedName"));
		private final @NonNull SerializationStepAssignedRuleCall _137 // 1*SpecificationCS::exprString=119
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, 119 /* UNQUOTED_STRING */);
		private final @NonNull SerializationStepAssignedRuleCall _138 // 1*SquareBracketedClauseCS::ownedTerms+=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _139 // 1*StructuralFeatureCS::default=87
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, 87 /* SINGLE_QUOTED_STRING */);
		private final @NonNull SerializationStepAssignedRuleCall _140 // 1*StructuredClassCS::ownedSuperTypes+=117
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, 117 /* TypedRefCS */);
		private final @NonNull SerializationStepAssignedRuleCall _141 // 1*TemplateBindingCS::ownedSubstitutions+=100
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 100 /* TemplateParameterSubstitutionCS */);
		private final @NonNull SerializationStepAssignedRuleCall _142 // 1*TemplateParameterSubstitutionCS::ownedActualParameter=115
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 115 /* TypeRefCS */);
		private final @NonNull SerializationStepAssignedRuleCall _143 // 1*TemplateSignatureCS::ownedParameters+=114
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 114 /* TypeParameterCS */);
		private final @NonNull SerializationStepAssignedRuleCall _144 // 1*TupleLiteralExpCS::ownedParts+=104
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, 104 /* TupleLiteralPartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _145 // 1*TupleTypeCS::ownedParts+=105
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, 105 /* TuplePartCS */);
		private final @NonNull SerializationStepAssignedRuleCall _146 // 1*TypeLiteralExpCS::ownedType=112
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, 112 /* TypeLiteralWithMultiplicityCS */);
		private final @NonNull SerializationStepAssignedRuleCall _147 // 1*TypeNameExpCS::ownedCurlyBracketedClause=13
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 13 /* CurlyBracketedClauseCS */);
		private final @NonNull SerializationStepAssignedRuleCall _148 // 1*TypeNameExpCS::ownedPathName=73
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, 73 /* PathNameCS */);
		private final @NonNull SerializationStepAssignedRuleCall _149 // 1*TypeNameExpCS::ownedPatternGuard=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _150 // 1*TypeParameterCS::ownedExtends+=117
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 117 /* TypedRefCS */);
		private final @NonNull SerializationStepAssignedRuleCall _151 // 1*TypedElementCS::ownedType=107
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 107 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _152 // 1*TypedElementCS::ownedType=116
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, 116 /* TypedMultiplicityRefCS */);
		private final @NonNull SerializationStepAssignKeyword _153 // 1*TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'
									= new SerializationStepAssignKeyword(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._00);
		private final @NonNull SerializationStepAssignKeyword _154 // 1*TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'
									= new SerializationStepAssignKeyword(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._01);
		private final @NonNull SerializationStepAssignKeyword _155 // 1*TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'
									= new SerializationStepAssignKeyword(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._02);
		private final @NonNull SerializationStepAssignKeyword _156 // 1*TypedElementCS::qualifiers+='!ordered|!unique|ordered|unique'
									= new SerializationStepAssignKeyword(-1, BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._03);
		private final @NonNull SerializationStepAssignedRuleCall _157 // 1*TypedTypeRefCS::ownedBinding=99
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 99 /* TemplateBindingCS */);
		private final @NonNull SerializationStepAssignedRuleCall _158 // 1*TypedTypeRefCS::ownedPathName=73
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 73 /* PathNameCS */);
		private final @NonNull SerializationStepAssignedRuleCall _159 // 1*VariableCS::ownedInitExpression=30
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, 30 /* ExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _160 // 1*VariableCS::ownedType=107
									= new SerializationStepAssignedRuleCall(-1, EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, 107 /* TypeExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _161 // 1*WildcardTypeRefCS::ownedExtends=117
									= new SerializationStepAssignedRuleCall(-1, BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 117 /* TypedRefCS */);
		private final @NonNull SerializationStepSequence _162 // 1*steps-1..10
									= new SerializationStepSequence(-1, 1, 10);
		private final @NonNull SerializationStepSequence _163 // 1*steps-1..11
									= new SerializationStepSequence(-1, 1, 11);
		private final @NonNull SerializationStepSequence _164 // 1*steps-1..12
									= new SerializationStepSequence(-1, 1, 12);
		private final @NonNull SerializationStepSequence _165 // 1*steps-1..13
									= new SerializationStepSequence(-1, 1, 13);
		private final @NonNull SerializationStepSequence _166 // 1*steps-1..14
									= new SerializationStepSequence(-1, 1, 14);
		private final @NonNull SerializationStepSequence _167 // 1*steps-1..15
									= new SerializationStepSequence(-1, 1, 15);
		private final @NonNull SerializationStepSequence _168 // 1*steps-1..16
									= new SerializationStepSequence(-1, 1, 16);
		private final @NonNull SerializationStepSequence _169 // 1*steps-1..17
									= new SerializationStepSequence(-1, 1, 17);
		private final @NonNull SerializationStepSequence _170 // 1*steps-1..18
									= new SerializationStepSequence(-1, 1, 18);
		private final @NonNull SerializationStepSequence _171 // 1*steps-1..19
									= new SerializationStepSequence(-1, 1, 19);
		private final @NonNull SerializationStepSequence _172 // 1*steps-1..20
									= new SerializationStepSequence(-1, 1, 20);
		private final @NonNull SerializationStepSequence _173 // 1*steps-1..24
									= new SerializationStepSequence(-1, 1, 24);
		private final @NonNull SerializationStepSequence _174 // 1*steps-1..26
									= new SerializationStepSequence(-1, 1, 26);
		private final @NonNull SerializationStepSequence _175 // 1*steps-1..27
									= new SerializationStepSequence(-1, 1, 27);
		private final @NonNull SerializationStepSequence _176 // 1*steps-1..28
									= new SerializationStepSequence(-1, 1, 28);
		private final @NonNull SerializationStepSequence _177 // 1*steps-1..29
									= new SerializationStepSequence(-1, 1, 29);
		private final @NonNull SerializationStepSequence _178 // 1*steps-1..3
									= new SerializationStepSequence(-1, 1, 3);
		private final @NonNull SerializationStepSequence _179 // 1*steps-1..35
									= new SerializationStepSequence(-1, 1, 35);
		private final @NonNull SerializationStepSequence _180 // 1*steps-1..37
									= new SerializationStepSequence(-1, 1, 37);
		private final @NonNull SerializationStepSequence _181 // 1*steps-1..4
									= new SerializationStepSequence(-1, 1, 4);
		private final @NonNull SerializationStepSequence _182 // 1*steps-1..40
									= new SerializationStepSequence(-1, 1, 40);
		private final @NonNull SerializationStepSequence _183 // 1*steps-1..42
									= new SerializationStepSequence(-1, 1, 42);
		private final @NonNull SerializationStepSequence _184 // 1*steps-1..5
									= new SerializationStepSequence(-1, 1, 5);
		private final @NonNull SerializationStepSequence _185 // 1*steps-1..6
									= new SerializationStepSequence(-1, 1, 6);
		private final @NonNull SerializationStepSequence _186 // 1*steps-1..7
									= new SerializationStepSequence(-1, 1, 7);
		private final @NonNull SerializationStepSequence _187 // 1*steps-1..8
									= new SerializationStepSequence(-1, 1, 8);
		private final @NonNull SerializationStepSequence _188 // 1*steps-1..9
									= new SerializationStepSequence(-1, 1, 9);
		private final @NonNull SerializationStepLiteral _189 // V00*'abstract'
									= new SerializationStepLiteral(0, "abstract");
		private final @NonNull SerializationStepLiteral _190 // V00*'callable'
									= new SerializationStepLiteral(0, "callable");
		private final @NonNull SerializationStepLiteral _191 // V00*'definition'
									= new SerializationStepLiteral(0, "definition");
		private final @NonNull SerializationStepLiteral _192 // V00*'primitive'
									= new SerializationStepLiteral(0, "primitive");
		private final @NonNull SerializationStepLiteral _193 // V00*'static'
									= new SerializationStepLiteral(0, "static");
		private final @NonNull SerializationStepLiteral _194 // V00*'|1'
									= new SerializationStepLiteral(0, "|1");
		private final @NonNull SerializationStepAssignedRuleCall _195 // V00*AbstractNameExpCS::ownedSquareBracketedClauses+=93
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, 93 /* SquareBracketedClauseCS */);
		private final @NonNull SerializationStepAssigns _196 // V00*DetailCS::values+=87|48
									= new SerializationStepAssigns(0, BaseCSPackage.Literals.DETAIL_CS__VALUES, null, new @NonNull Integer [] { 87/*SINGLE_QUOTED_STRING*/,48/*ML_SINGLE_QUOTED_STRING*/});
		private final @NonNull SerializationStepAssignedRuleCall _197 // V00*DocumentationCS::value=87
									= new SerializationStepAssignedRuleCall(0, BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE, 87 /* SINGLE_QUOTED_STRING */);
		private final @NonNull SerializationStepAssignedRuleCall _198 // V00*IfExpCS::ownedIfThenExpressions+=20
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, 20 /* ElseIfThenExpCS */);
		private final @NonNull SerializationStepAssignedRuleCall _199 // V00*LetVariableCS::ownedRoundBracketedClause=84
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, 84 /* RoundBracketedClauseCS */);
		private final @NonNull SerializationStepAssigns _200 // V00*NamedElementCS::name=127|87
									= new SerializationStepAssigns(0, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, null, new @NonNull Integer [] { 127/*UnrestrictedName*/,87/*SINGLE_QUOTED_STRING*/});
		private final @NonNull SerializationStepAssignedRuleCall _201 // V00*PatternExpCS::patternVariableName=127
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME, 127 /* UnrestrictedName */);
		private final @NonNull SerializationStepAssignedRuleCall _202 // V00*StringLiteralExpCS::segments+=94
									= new SerializationStepAssignedRuleCall(0, EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS, 94 /* StringLiteral */);
		private final @NonNull SerializationStepAssignedRuleCall _203 // V00*TemplateableElementCS::ownedSignature=101
									= new SerializationStepAssignedRuleCall(0, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 101 /* TemplateSignatureCS */);
		private final @NonNull SerializationStepAssignedRuleCall _204 // V00*TypedRefCS::ownedMultiplicity=56
									= new SerializationStepAssignedRuleCall(0, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 56 /* MultiplicityCS */);
		private final @NonNull SerializationStepSequence _205 // V00*steps-2..3
									= new SerializationStepSequence(0, 2, 3);
		private final @NonNull SerializationStepSequence _206 // V00*steps-3..10
									= new SerializationStepSequence(0, 3, 10);
		private final @NonNull SerializationStepSequence _207 // V00*steps-3..5
									= new SerializationStepSequence(0, 3, 5);
		private final @NonNull SerializationStepSequence _208 // V00*steps-3..7
									= new SerializationStepSequence(0, 3, 7);
		private final @NonNull SerializationStepSequence _209 // V00*steps-3..8
									= new SerializationStepSequence(0, 3, 8);
		private final @NonNull SerializationStepSequence _210 // V00*steps-4..10
									= new SerializationStepSequence(0, 4, 10);
		private final @NonNull SerializationStepSequence _211 // V00*steps-4..6
									= new SerializationStepSequence(0, 4, 6);
		private final @NonNull SerializationStepSequence _212 // V00*steps-4..8
									= new SerializationStepSequence(0, 4, 8);
		private final @NonNull SerializationStepSequence _213 // V00*steps-4..9
									= new SerializationStepSequence(0, 4, 9);
		private final @NonNull SerializationStepSequence _214 // V00*steps-5..7
									= new SerializationStepSequence(0, 5, 7);
		private final @NonNull SerializationStepSequence _215 // V00*steps-6..10
									= new SerializationStepSequence(0, 6, 10);
		private final @NonNull SerializationStepSequence _216 // V00*steps-6..8
									= new SerializationStepSequence(0, 6, 8);
		private final @NonNull SerializationStepLiteral _217 // V01*'::*'
									= new SerializationStepLiteral(1, "::*");
		private final @NonNull SerializationStepLiteral _218 // V01*'|1'
									= new SerializationStepLiteral(1, "|1");
		private final @NonNull SerializationStepAssignedRuleCall _219 // V01*AbstractNameExpCS::ownedRoundBracketedClause=84
									= new SerializationStepAssignedRuleCall(1, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, 84 /* RoundBracketedClauseCS */);
		private final @NonNull SerializationStepAssignedRuleCall _220 // V01*CollectionTypeCS::ownedCollectionMultiplicity=56
									= new SerializationStepAssignedRuleCall(1, EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, 56 /* MultiplicityCS */);
		private final @NonNull SerializationStepAssignedRuleCall _221 // V01*RootCS::ownedImports+=38
									= new SerializationStepAssignedRuleCall(1, BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, 38 /* ImportCS */);
		private final @NonNull SerializationStepAssigns _222 // V01*RoundBracketedClauseCS::ownedArguments+=63|64|62
									= new SerializationStepAssigns(1, EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, null, new @NonNull Integer [] { 63/*NavigatingCommaArgCS*/,64/*NavigatingSemiArgCS*/,62/*NavigatingBarArgCS*/});
		private final @NonNull SerializationStepAssignedRuleCall _223 // V01*TemplateBindingCS::ownedMultiplicity=56
									= new SerializationStepAssignedRuleCall(1, BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 56 /* MultiplicityCS */);
		private final @NonNull SerializationStepAssignedRuleCall _224 // V01*TemplateableElementCS::ownedSignature=101
									= new SerializationStepAssignedRuleCall(1, BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, 101 /* TemplateSignatureCS */);
		private final @NonNull SerializationStepAssignedRuleCall _225 // V01*TypedRefCS::ownedMultiplicity=56
									= new SerializationStepAssignedRuleCall(1, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 56 /* MultiplicityCS */);
		private final @NonNull SerializationStepSequence _226 // V01*steps-4..10
									= new SerializationStepSequence(1, 4, 10);
		private final @NonNull SerializationStepSequence _227 // V01*steps-4..6
									= new SerializationStepSequence(1, 4, 6);
		private final @NonNull SerializationStepSequence _228 // V01*steps-4..9
									= new SerializationStepSequence(1, 4, 9);
		private final @NonNull SerializationStepSequence _229 // V01*steps-5..7
									= new SerializationStepSequence(1, 5, 7);
		private final @NonNull SerializationStepSequence _230 // V01*steps-5..8
									= new SerializationStepSequence(1, 5, 8);
		private final @NonNull SerializationStepSequence _231 // V01*steps-5..9
									= new SerializationStepSequence(1, 5, 9);
		private final @NonNull SerializationStepSequence _232 // V01*steps-6..10
									= new SerializationStepSequence(1, 6, 10);
		private final @NonNull SerializationStepSequence _233 // V01*steps-6..8
									= new SerializationStepSequence(1, 6, 8);
		private final @NonNull SerializationStepSequence _234 // V01*steps-7..9
									= new SerializationStepSequence(1, 7, 9);
		private final @NonNull SerializationStepSequence _235 // V01*steps-8..10
									= new SerializationStepSequence(1, 8, 10);
		private final @NonNull SerializationStepSequence _236 // V01*steps-8..9
									= new SerializationStepSequence(1, 8, 9);
		private final @NonNull SerializationStepSequence _237 // V01*steps-9..11
									= new SerializationStepSequence(1, 9, 11);
		private final @NonNull SerializationStepAssignedRuleCall _238 // V02*AbstractNameExpCS::ownedCurlyBracketedClause=13
									= new SerializationStepAssignedRuleCall(2, EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, 13 /* CurlyBracketedClauseCS */);
		private final @NonNull SerializationStepAssignedRuleCall _239 // V02*ConstraintCS::ownedSpecification=92
									= new SerializationStepAssignedRuleCall(2, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 92 /* SpecificationCS */);
		private final @NonNull SerializationStepAssignedRuleCall _240 // V02*ModelElementCS::ownedAnnotations+=2
									= new SerializationStepAssignedRuleCall(2, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _241 // V02*PackageOwnerCS::ownedPackages+=71
									= new SerializationStepAssignedRuleCall(2, BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 71 /* PackageCS */);
		private final @NonNull SerializationStepAssignedRuleCall _242 // V02*TypedRefCS::ownedMultiplicity=56
									= new SerializationStepAssignedRuleCall(2, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 56 /* MultiplicityCS */);
		private final @NonNull SerializationStepSequence _243 // V02*steps-10..12
									= new SerializationStepSequence(2, 10, 12);
		private final @NonNull SerializationStepSequence _244 // V02*steps-10..14
									= new SerializationStepSequence(2, 10, 14);
		private final @NonNull SerializationStepSequence _245 // V02*steps-6..11
									= new SerializationStepSequence(2, 6, 11);
		private final @NonNull SerializationStepSequence _246 // V02*steps-6..8
									= new SerializationStepSequence(2, 6, 8);
		private final @NonNull SerializationStepSequence _247 // V02*steps-6..9
									= new SerializationStepSequence(2, 6, 9);
		private final @NonNull SerializationStepSequence _248 // V02*steps-7..9
									= new SerializationStepSequence(2, 7, 9);
		private final @NonNull SerializationStepSequence _249 // V02*steps-8..10
									= new SerializationStepSequence(2, 8, 10);
		private final @NonNull SerializationStepSequence _250 // V02*steps-8..11
									= new SerializationStepSequence(2, 8, 11);
		private final @NonNull SerializationStepSequence _251 // V02*steps-8..12
									= new SerializationStepSequence(2, 8, 12);
		private final @NonNull SerializationStepSequence _252 // V02*steps-8..9
									= new SerializationStepSequence(2, 8, 9);
		private final @NonNull SerializationStepSequence _253 // V02*steps-9..11
									= new SerializationStepSequence(2, 9, 11);
		private final @NonNull SerializationStepLiteral _254 // V03*'serializable'
									= new SerializationStepLiteral(3, "serializable");
		private final @NonNull SerializationStepAssignedRuleCall _255 // V03*ConstraintCS::ownedSpecification=92
									= new SerializationStepAssignedRuleCall(3, BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, 92 /* SpecificationCS */);
		private final @NonNull SerializationStepAssignedRuleCall _256 // V03*ModelElementCS::ownedAnnotations+=2
									= new SerializationStepAssignedRuleCall(3, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _257 // V03*PackageOwnerCS::ownedPackages+=71
									= new SerializationStepAssignedRuleCall(3, BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, 71 /* PackageCS */);
		private final @NonNull SerializationStepAssignedRuleCall _258 // V03*TypedRefCS::ownedMultiplicity=56
									= new SerializationStepAssignedRuleCall(3, BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, 56 /* MultiplicityCS */);
		private final @NonNull SerializationStepSequence _259 // V03*steps-10..12
									= new SerializationStepSequence(3, 10, 12);
		private final @NonNull SerializationStepSequence _260 // V03*steps-11..14
									= new SerializationStepSequence(3, 11, 14);
		private final @NonNull SerializationStepSequence _261 // V03*steps-12..13
									= new SerializationStepSequence(3, 12, 13);
		private final @NonNull SerializationStepSequence _262 // V03*steps-12..14
									= new SerializationStepSequence(3, 12, 14);
		private final @NonNull SerializationStepSequence _263 // V03*steps-12..16
									= new SerializationStepSequence(3, 12, 16);
		private final @NonNull SerializationStepSequence _264 // V03*steps-13..17
									= new SerializationStepSequence(3, 13, 17);
		private final @NonNull SerializationStepSequence _265 // V03*steps-6..8
									= new SerializationStepSequence(3, 6, 8);
		private final @NonNull SerializationStepSequence _266 // V03*steps-9..11
									= new SerializationStepSequence(3, 9, 11);
		private final @NonNull SerializationStepSequence _267 // V03*steps-9..12
									= new SerializationStepSequence(3, 9, 12);
		private final @NonNull SerializationStepLiteral _268 // V04*'serializable'
									= new SerializationStepLiteral(4, "serializable");
		private final @NonNull SerializationStepAssignedRuleCall _269 // V04*AnnotationCS::ownedContents+=53
									= new SerializationStepAssignedRuleCall(4, BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, 53 /* ModelElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _270 // V04*EnumerationCS::ownedLiterals+=22
									= new SerializationStepAssignedRuleCall(4, BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, 22 /* EnumerationLiteralCS */);
		private final @NonNull SerializationStepAssignedRuleCall _271 // V04*ModelElementCS::ownedAnnotations+=2
									= new SerializationStepAssignedRuleCall(4, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _272 // V04*PackageCS::ownedClasses+=6
									= new SerializationStepAssignedRuleCall(4, BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, 6 /* ClassCS */);
		private final @NonNull SerializationStepSequence _273 // V04*steps-12..14
									= new SerializationStepSequence(4, 12, 14);
		private final @NonNull SerializationStepSequence _274 // V04*steps-14..15
									= new SerializationStepSequence(4, 14, 15);
		private final @NonNull SerializationStepSequence _275 // V04*steps-14..16
									= new SerializationStepSequence(4, 14, 16);
		private final @NonNull SerializationStepSequence _276 // V04*steps-15..16
									= new SerializationStepSequence(4, 15, 16);
		private final @NonNull SerializationStepSequence _277 // V04*steps-15..19
									= new SerializationStepSequence(4, 15, 19);
		private final @NonNull SerializationStepSequence _278 // V04*steps-15..20
									= new SerializationStepSequence(4, 15, 20);
		private final @NonNull SerializationStepAssignedRuleCall _279 // V05*AnnotationCS::ownedReferences+=54
									= new SerializationStepAssignedRuleCall(5, BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, 54 /* ModelElementRefCS */);
		private final @NonNull SerializationStepAssignedRuleCall _280 // V05*ClassCS::ownedConstraints+=41
									= new SerializationStepAssignedRuleCall(5, BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 41 /* InvariantConstraintCS */);
		private final @NonNull SerializationStepAssignedRuleCall _281 // V05*EnumerationCS::ownedLiterals+=22
									= new SerializationStepAssignedRuleCall(5, BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, 22 /* EnumerationLiteralCS */);
		private final @NonNull SerializationStepAssignedRuleCall _282 // V05*ModelElementCS::ownedAnnotations+=2
									= new SerializationStepAssignedRuleCall(5, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull SerializationStepSequence _283 // V05*steps-15..18
									= new SerializationStepSequence(5, 15, 18);
		private final @NonNull SerializationStepSequence _284 // V05*steps-17..18
									= new SerializationStepSequence(5, 17, 18);
		private final @NonNull SerializationStepSequence _285 // V05*steps-17..21
									= new SerializationStepSequence(5, 17, 21);
		private final @NonNull SerializationStepSequence _286 // V05*steps-17..22
									= new SerializationStepSequence(5, 17, 22);
		private final @NonNull SerializationStepSequence _287 // V05*steps-18..20
									= new SerializationStepSequence(5, 18, 20);
		private final @NonNull SerializationStepLiteral _288 // V06*'interface'
									= new SerializationStepLiteral(6, "interface");
		private final @NonNull SerializationStepAssignedRuleCall _289 // V06*ClassCS::ownedConstraints+=41
									= new SerializationStepAssignedRuleCall(6, BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 41 /* InvariantConstraintCS */);
		private final @NonNull SerializationStepAssignedRuleCall _290 // V06*ModelElementCS::ownedAnnotations+=2
									= new SerializationStepAssignedRuleCall(6, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _291 // V06*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new SerializationStepAssignedRuleCall(6, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull SerializationStepSequence _292 // V06*steps-19..23
									= new SerializationStepSequence(6, 19, 23);
		private final @NonNull SerializationStepSequence _293 // V06*steps-20..22
									= new SerializationStepSequence(6, 20, 22);
		private final @NonNull SerializationStepSequence _294 // V06*steps-20..26
									= new SerializationStepSequence(6, 20, 26);
		private final @NonNull SerializationStepSequence _295 // V06*steps-21..25
									= new SerializationStepSequence(6, 21, 25);
		private final @NonNull SerializationStepAssignedRuleCall _296 // V07*ModelElementCS::ownedAnnotations+=2
									= new SerializationStepAssignedRuleCall(7, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _297 // V07*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new SerializationStepAssignedRuleCall(7, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull SerializationStepSequence _298 // V07*steps-22..26
									= new SerializationStepSequence(7, 22, 26);
		private final @NonNull SerializationStepSequence _299 // V07*steps-22..28
									= new SerializationStepSequence(7, 22, 28);
		private final @NonNull SerializationStepSequence _300 // V07*steps-23..24
									= new SerializationStepSequence(7, 23, 24);
		private final @NonNull SerializationStepSequence _301 // V07*steps-23..25
									= new SerializationStepSequence(7, 23, 25);
		private final @NonNull SerializationStepSequence _302 // V07*steps-23..27
									= new SerializationStepSequence(7, 23, 27);
		private final @NonNull SerializationStepAssignedRuleCall _303 // V08*ModelElementCS::ownedAnnotations+=2
									= new SerializationStepAssignedRuleCall(8, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _304 // V08*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new SerializationStepAssignedRuleCall(8, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull SerializationStepAssignedRuleCall _305 // V08*StructuredClassCS::ownedOperations+=70
									= new SerializationStepAssignedRuleCall(8, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, 70 /* OperationCS */);
		private final @NonNull SerializationStepSequence _306 // V08*steps-24..28
									= new SerializationStepSequence(8, 24, 28);
		private final @NonNull SerializationStepSequence _307 // V08*steps-25..26
									= new SerializationStepSequence(8, 25, 26);
		private final @NonNull SerializationStepSequence _308 // V08*steps-25..27
									= new SerializationStepSequence(8, 25, 27);
		private final @NonNull SerializationStepSequence _309 // V08*steps-27..31
									= new SerializationStepSequence(8, 27, 31);
		private final @NonNull SerializationStepAssignedRuleCall _310 // V09*ModelElementCS::ownedAnnotations+=2
									= new SerializationStepAssignedRuleCall(9, BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, 2 /* AnnotationElementCS */);
		private final @NonNull SerializationStepAssignedRuleCall _311 // V09*OperationCS::ownedPreconditions+=76
									= new SerializationStepAssignedRuleCall(9, BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 76 /* PreconditionConstraintCS */);
		private final @NonNull SerializationStepAssignedRuleCall _312 // V09*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new SerializationStepAssignedRuleCall(9, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull SerializationStepAssignedRuleCall _313 // V09*StructuredClassCS::ownedProperties+=96
									= new SerializationStepAssignedRuleCall(9, BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, 96 /* StructuralFeatureCS */);
		private final @NonNull SerializationStepSequence _314 // V09*steps-29..33
									= new SerializationStepSequence(9, 29, 33);
		private final @NonNull SerializationStepAssignedRuleCall _315 // V10*ClassCS::ownedConstraints+=41
									= new SerializationStepAssignedRuleCall(10, BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, 41 /* InvariantConstraintCS */);
		private final @NonNull SerializationStepAssignedRuleCall _316 // V10*OperationCS::ownedPreconditions+=76
									= new SerializationStepAssignedRuleCall(10, BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, 76 /* PreconditionConstraintCS */);
		private final @NonNull SerializationStepAssignedRuleCall _317 // V10*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new SerializationStepAssignedRuleCall(10, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull SerializationStepSequence _318 // V10*steps-29..33
									= new SerializationStepSequence(10, 29, 33);
		private final @NonNull SerializationStepSequence _319 // V10*steps-32..36
									= new SerializationStepSequence(10, 32, 36);
		private final @NonNull SerializationStepAssignedRuleCall _320 // V11*OperationCS::ownedBodyExpressions+=92
									= new SerializationStepAssignedRuleCall(11, BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull SerializationStepAssignedRuleCall _321 // V11*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new SerializationStepAssignedRuleCall(11, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull SerializationStepSequence _322 // V11*steps-31..35
									= new SerializationStepSequence(11, 31, 35);
		private final @NonNull SerializationStepSequence _323 // V11*steps-34..38
									= new SerializationStepSequence(11, 34, 38);
		private final @NonNull SerializationStepAssignedRuleCall _324 // V12*OperationCS::ownedBodyExpressions+=92
									= new SerializationStepAssignedRuleCall(12, BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull SerializationStepAssignedRuleCall _325 // V12*OperationCS::ownedPostconditions+=75
									= new SerializationStepAssignedRuleCall(12, BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 75 /* PostconditionConstraintCS */);
		private final @NonNull SerializationStepAssignedRuleCall _326 // V12*StructuralFeatureCS::ownedDefaultExpressions+=92
									= new SerializationStepAssignedRuleCall(12, BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, 92 /* SpecificationCS */);
		private final @NonNull SerializationStepSequence _327 // V12*steps-37..39
									= new SerializationStepSequence(12, 37, 39);
		private final @NonNull SerializationStepAssignedRuleCall _328 // V13*OperationCS::ownedPostconditions+=75
									= new SerializationStepAssignedRuleCall(13, BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, 75 /* PostconditionConstraintCS */);
		private final @NonNull SerializationStepSequence _329 // V13*steps-39..41
									= new SerializationStepSequence(13, 39, 41);
	}

	/**
	 * The various string segment sequences that may be used to serialize a serialization term.
	 */
	private class _SerializationSegments
	{
		private final @NonNull SerializationSegment [] _0 = new @NonNull SerializationSegment @NonNull [] {
			new CustomSerializationSegment(BaseCommentSegmentSupport.class) /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport */,
			SerializationSegment.VALUE /* «value» */
		};
		private final @NonNull SerializationSegment [] _1 = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* «! » */,
			SerializationSegment.VALUE /* «value» */,
			SerializationSegment.NO_SPACE /* «! » */
		};
		private final @NonNull SerializationSegment [] _2 = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* «! » */,
			SerializationSegment.VALUE /* «value» */,
			SerializationSegment.SOFT_SPACE /* «? » */
		};
		private final @NonNull SerializationSegment [] _3 = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* «! » */,
			SerializationSegment.VALUE /* «value» */,
			SerializationSegment.SOFT_NEW_LINE /* «?\n» */
		};
		private final @NonNull SerializationSegment [] _4 = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* «! » */,
			SerializationSegment.VALUE /* «value» */
		};
		private final @NonNull SerializationSegment [] _5 = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.POP /* «-» */,
			SerializationSegment.SOFT_SPACE /* «? » */,
			SerializationSegment.VALUE /* «value» */,
			SerializationSegment.SOFT_NEW_LINE /* «?\n» */
		};
		private final @NonNull SerializationSegment [] _6 = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* «? » */,
			SerializationSegment.VALUE /* «value» */,
			SerializationSegment.PUSH /* «+» */,
			SerializationSegment.SOFT_NEW_LINE /* «?\n» */
		};
		private final @NonNull SerializationSegment [] _7 = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* «? » */,
			SerializationSegment.VALUE /* «value» */,
			SerializationSegment.SOFT_SPACE /* «? » */
		};
		private final @NonNull SerializationSegment [] _8 = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.HALF_NEW_LINE /* «½\n» */,
			SerializationSegment.VALUE /* «value» */,
			SerializationSegment.HALF_NEW_LINE /* «½\n» */
		};
	}

	/**
	 * The various lists of string segment sequences that may be used to serialize a serialization rule.
	 */
	private class _SerializationSegmentsLists
	{
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _00 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _01 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			null,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _02 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			null,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _03 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _04 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _05 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _06 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _07 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _08 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _09 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _10 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _11 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _12 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _13 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _14 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _15 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _16 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _17 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _18 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _19 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _20 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _21 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _22 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _23 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._4 /* «! » «value» */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _24 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _25 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _26 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _27 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _28 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _29 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _30 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			null,
			null,
			null,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _31 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			null,
			null,
			ss._1 /* «! » «value» «! » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _32 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _33 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _34 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			null,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _35 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _36 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _37 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _38 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _39 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._1 /* «! » «value» «! » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _40 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _41 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._2 /* «! » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _42 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _43 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _44 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _45 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._3 /* «! » «value» «?\n» */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _46 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _47 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _48 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _49 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _50 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _51 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _52 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _53 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _54 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _55 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _56 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _57 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _58 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _59 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _60 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._3 /* «! » «value» «?\n» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _61 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _62 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._2 /* «! » «value» «? » */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _63 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			null,
			ss._3 /* «! » «value» «?\n» */,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _64 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._6 /* «? » «value» «+» «?\n» */,
			null,
			ss._5 /* «-» «? » «value» «?\n» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _65 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _66 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _67 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _68 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _69 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _70 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _71 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _72 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _73 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _74 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _75 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _76 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _77 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _78 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _79 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _80 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _81 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _82 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _83 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _84 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _85 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _86 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _87 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _88 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _89 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _90 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _91 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _92 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _93 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _94 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			ss._0 /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _95 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
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
					sr1._097 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
					sr1._098 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._099 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _002 // AnnotationElementCS
			= new ParserRuleValue(2, "AnnotationElementCS",
				new @NonNull SerializationRule [] {
					sr1._096 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr1._097 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
					sr1._098 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._099 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */,
					sr1._113 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					sr2._150 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */,
					sr2._151 /* { 'sysml' ownedDetails+=DetailCS ';' } */
				},
				iv._45); /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
		private final @NonNull ParserRuleValue _003 // AttributeCS
			= new ParserRuleValue(3, "AttributeCS",
				new @NonNull SerializationRule [] {
					sr1._100 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._101 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._102 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._103 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._104 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._105 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				},
				null);
		private final @NonNull DataTypeRuleValue _004 // BinaryOperatorName
			= new DataTypeRuleValue(4, "BinaryOperatorName");
		private final @NonNull ParserRuleValue _005 // BooleanLiteralExpCS
			= new ParserRuleValue(5, "BooleanLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._015 /* symbol={'false|true'} */
				},
				null);
		private final @NonNull ParserRuleValue _006 // ClassCS
			= new ParserRuleValue(6, "ClassCS",
				new @NonNull SerializationRule [] {
					sr1._106 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._107 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._108 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._109 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._110 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._111 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._114 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._115 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._116 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._117 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._118 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._119 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr2._148 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr2._149 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				iv._43); /* ClassCS|DataTypeCS|EnumerationCS|StructuredClassCS */
		private final @NonNull ParserRuleValue _007 // CoIteratorVariableCS
			= new ParserRuleValue(7, "CoIteratorVariableCS",
				new @NonNull SerializationRule [] {
					sr0._016 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _008 // CollectionLiteralExpCS
			= new ParserRuleValue(8, "CollectionLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._017 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _009 // CollectionLiteralPartCS
			= new ParserRuleValue(9, "CollectionLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._018 /* ownedExpression=PatternExpCS */,
					sr0._019 /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _010 // CollectionPatternCS
			= new ParserRuleValue(10, "CollectionPatternCS",
				new @NonNull SerializationRule [] {
					sr0._020 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _011 // CollectionTypeCS
			= new ParserRuleValue(11, "CollectionTypeCS",
				new @NonNull SerializationRule [] {
					sr0._021 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _012 // CollectionTypeIdentifier
			= new DataTypeRuleValue(12, "CollectionTypeIdentifier");
		private final @NonNull ParserRuleValue _013 // CurlyBracketedClauseCS
			= new ParserRuleValue(13, "CurlyBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr0._022 /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				},
				null);
		private final @NonNull TerminalRuleValue _014 // DOUBLE_QUOTED_STRING
			= new TerminalRuleValue(14, "DOUBLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _015 // DataTypeCS
			= new ParserRuleValue(15, "DataTypeCS",
				new @NonNull SerializationRule [] {
					sr1._106 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._107 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._108 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._109 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._110 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._111 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _016 // DetailCS
			= new ParserRuleValue(16, "DetailCS",
				new @NonNull SerializationRule [] {
					sr1._112 /* { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
				},
				null);
		private final @NonNull ParserRuleValue _017 // DocumentationCS
			= new ParserRuleValue(17, "DocumentationCS",
				new @NonNull SerializationRule [] {
					sr1._113 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				},
				null);
		private final @NonNull TerminalRuleValue _018 // ESCAPED_CHARACTER
			= new TerminalRuleValue(18, "ESCAPED_CHARACTER");
		private final @NonNull TerminalRuleValue _019 // ESCAPED_ID
			= new TerminalRuleValue(19, "ESCAPED_ID");
		private final @NonNull ParserRuleValue _020 // ElseIfThenExpCS
			= new ParserRuleValue(20, "ElseIfThenExpCS",
				new @NonNull SerializationRule [] {
					sr0._023 /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _021 // EnumerationCS
			= new ParserRuleValue(21, "EnumerationCS",
				new @NonNull SerializationRule [] {
					sr1._114 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._115 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._116 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._117 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._118 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._119 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _022 // EnumerationLiteralCS
			= new ParserRuleValue(22, "EnumerationLiteralCS",
				new @NonNull SerializationRule [] {
					sr1._120 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._121 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr1._122 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._123 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				null);
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
					sr0._024 /* symbol={'false|true'} */,
					sr0._025 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._026 /* '*' */,
					sr0._027 /* 'invalid' */,
					sr0._028 /* 'null' */,
					sr0._029 /* 'self' */,
					sr0._030 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._031 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._032 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._033 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._034 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._035 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._036 /* symbol=NUMBER_LITERAL */,
					sr0._037 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._038 /* segments+=StringLiteral[+] */,
					sr0._039 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._040 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._044 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._066 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._71); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _031 // FirstPathElementCS
			= new ParserRuleValue(31, "FirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._000 /* referredElement=UnrestrictedName */
				},
				null);
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
				null);
		private final @NonNull ParserRuleValue _037 // ImplicitOppositeCS
			= new ParserRuleValue(37, "ImplicitOppositeCS",
				new @NonNull SerializationRule [] {
					sr1._124 /* { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _038 // ImportCS
			= new ParserRuleValue(38, "ImportCS",
				new @NonNull SerializationRule [] {
					sr1._125 /* { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' } */
				},
				null);
		private final @NonNull DataTypeRuleValue _039 // InfixOperatorName
			= new DataTypeRuleValue(39, "InfixOperatorName");
		private final @NonNull ParserRuleValue _040 // InvalidLiteralExpCS
			= new ParserRuleValue(40, "InvalidLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._042 /* 'invalid' */
				},
				null);
		private final @NonNull ParserRuleValue _041 // InvariantConstraintCS
			= new ParserRuleValue(41, "InvariantConstraintCS",
				new @NonNull SerializationRule [] {
					sr1._126 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */,
					sr1._127 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' } */
				},
				null);
		private final @NonNull TerminalRuleValue _042 // LETTER_CHARACTER
			= new TerminalRuleValue(42, "LETTER_CHARACTER");
		private final @NonNull DataTypeRuleValue _043 // LOWER
			= new DataTypeRuleValue(43, "LOWER");
		private final @NonNull ParserRuleValue _044 // LambdaLiteralExpCS
			= new ParserRuleValue(44, "LambdaLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._043 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _045 // LetExpCS
			= new ParserRuleValue(45, "LetExpCS",
				new @NonNull SerializationRule [] {
					sr0._044 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _046 // LetVariableCS
			= new ParserRuleValue(46, "LetVariableCS",
				new @NonNull SerializationRule [] {
					sr0._045 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull TerminalRuleValue _047 // ML_COMMENT
			= new TerminalRuleValue(47, "ML_COMMENT");
		private final @NonNull TerminalRuleValue _048 // ML_SINGLE_QUOTED_STRING
			= new TerminalRuleValue(48, "ML_SINGLE_QUOTED_STRING");
		private final @NonNull ParserRuleValue _049 // MapLiteralExpCS
			= new ParserRuleValue(49, "MapLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._046 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _050 // MapLiteralPartCS
			= new ParserRuleValue(50, "MapLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr0._047 /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _051 // MapTypeCS
			= new ParserRuleValue(51, "MapTypeCS",
				new @NonNull SerializationRule [] {
					sr0._048 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _052 // Model
			= new ParserRuleValue(52, "Model",
				new @NonNull SerializationRule [] {
					sr0._049 /* ownedExpression=ExpCS */
				},
				null);
		private final @NonNull ParserRuleValue _053 // ModelElementCS
			= new ParserRuleValue(53, "ModelElementCS",
				new @NonNull SerializationRule [] {
					sr1._100 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._101 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._102 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._103 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._104 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._105 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._106 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._107 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._108 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._109 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._110 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._111 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._114 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					sr1._115 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._116 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					sr1._117 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._118 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					sr1._119 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					sr1._120 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._121 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr1._122 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					sr1._123 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					sr2._129 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._130 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._131 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._132 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._133 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._134 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._135 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
					sr2._136 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */,
					sr2._140 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._141 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._142 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._143 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._144 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._145 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._148 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr2._149 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				iv._44); /* AttributeCS|ClassCS|DataTypeCS|EnumerationCS|EnumerationLiteralCS|ModelElementCS|OperationCS|PackageCS|ReferenceCS|StructuralFeatureCS|StructuredClassCS */
		private final @NonNull ParserRuleValue _054 // ModelElementRefCS
			= new ParserRuleValue(54, "ModelElementRefCS",
				new @NonNull SerializationRule [] {
					sr2._128 /* { 'reference' ownedPathName=PathNameCS ';' } */
				},
				null);
		private final @NonNull ParserRuleValue _055 // MultiplicityBoundsCS
			= new ParserRuleValue(55, "MultiplicityBoundsCS",
				new @NonNull SerializationRule [] {
					sr0._001 /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _056 // MultiplicityCS
			= new ParserRuleValue(56, "MultiplicityCS",
				new @NonNull SerializationRule [] {
					sr0._002 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					sr0._003 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					sr0._004 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */,
					sr0._005 /* { '[' stringBounds={'*|+|?'} ']' } */,
					sr0._006 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					sr0._007 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */
				},
				null);
		private final @NonNull ParserRuleValue _057 // MultiplicityStringCS
			= new ParserRuleValue(57, "MultiplicityStringCS",
				new @NonNull SerializationRule [] {
					sr0._008 /* stringBounds={'*|+|?'} */
				},
				null);
		private final @NonNull DataTypeRuleValue _058 // NUMBER_LITERAL
			= new DataTypeRuleValue(58, "NUMBER_LITERAL");
		private final @NonNull ParserRuleValue _059 // NameExpCS
			= new ParserRuleValue(59, "NameExpCS",
				new @NonNull SerializationRule [] {
					sr0._050 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _060 // NavigatingArgCS
			= new ParserRuleValue(60, "NavigatingArgCS",
				new @NonNull SerializationRule [] {
					sr0._051 /* ownedNameExpression=NavigatingArgExpCS */,
					sr0._052 /* { ':' ownedType=TypeExpCS } */,
					sr0._053 /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._054 /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._055 /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _061 // NavigatingArgExpCS
			= new ParserRuleValue(61, "NavigatingArgExpCS",
				new @NonNull SerializationRule [] {
					sr0._024 /* symbol={'false|true'} */,
					sr0._025 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					sr0._026 /* '*' */,
					sr0._027 /* 'invalid' */,
					sr0._028 /* 'null' */,
					sr0._029 /* 'self' */,
					sr0._030 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
					sr0._031 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
					sr0._032 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
					sr0._033 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
					sr0._034 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
					sr0._035 /* { '(' ownedExpression=ExpCS ')' } */,
					sr0._036 /* symbol=NUMBER_LITERAL */,
					sr0._037 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
					sr0._038 /* segments+=StringLiteral[+] */,
					sr0._039 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
					sr0._040 /* ownedType=TypeLiteralWithMultiplicityCS */,
					sr0._044 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
					sr1._066 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				},
				iv._72); /* BooleanLiteralExpCS|CollectionLiteralExpCS|ExpCS|IfExpCS|InvalidLiteralExpCS|LambdaLiteralExpCS|LetExpCS|MapLiteralExpCS|NameExpCS|NavigatingArgExpCS|NestedExpCS|NullLiteralExpCS|NumberLiteralExpCS|PrefixedLetExpCS|PrefixedPrimaryExpCS|PrimaryExpCS|PrimitiveLiteralExpCS|SelfExpCS|StringLiteralExpCS|TupleLiteralExpCS|TypeLiteralExpCS|UnlimitedNaturalLiteralExpCS */
		private final @NonNull ParserRuleValue _062 // NavigatingBarArgCS
			= new ParserRuleValue(62, "NavigatingBarArgCS",
				new @NonNull SerializationRule [] {
					sr0._056 /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _063 // NavigatingCommaArgCS
			= new ParserRuleValue(63, "NavigatingCommaArgCS",
				new @NonNull SerializationRule [] {
					sr0._060 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					sr0._057 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._058 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					sr0._059 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _064 // NavigatingSemiArgCS
			= new ParserRuleValue(64, "NavigatingSemiArgCS",
				new @NonNull SerializationRule [] {
					sr0._061 /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _065 // NavigationOperatorName
			= new DataTypeRuleValue(65, "NavigationOperatorName");
		private final @NonNull ParserRuleValue _066 // NestedExpCS
			= new ParserRuleValue(66, "NestedExpCS",
				new @NonNull SerializationRule [] {
					sr0._062 /* { '(' ownedExpression=ExpCS ')' } */
				},
				null);
		private final @NonNull ParserRuleValue _067 // NextPathElementCS
			= new ParserRuleValue(67, "NextPathElementCS",
				new @NonNull SerializationRule [] {
					sr0._009 /* referredElement=UnreservedName */
				},
				null);
		private final @NonNull ParserRuleValue _068 // NullLiteralExpCS
			= new ParserRuleValue(68, "NullLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr0._063 /* 'null' */
				},
				null);
		private final @NonNull ParserRuleValue _069 // NumberLiteralExpCS
			= new ParserRuleValue(69, "NumberLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._064 /* symbol=NUMBER_LITERAL */
				},
				null);
		private final @NonNull ParserRuleValue _070 // OperationCS
			= new ParserRuleValue(70, "OperationCS",
				new @NonNull SerializationRule [] {
					sr2._129 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._130 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._131 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._132 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
					sr2._133 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					sr2._134 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _071 // PackageCS
			= new ParserRuleValue(71, "PackageCS",
				new @NonNull SerializationRule [] {
					sr2._135 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
					sr2._136 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _072 // ParameterCS
			= new ParserRuleValue(72, "ParameterCS",
				new @NonNull SerializationRule [] {
					sr2._137 /* { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _073 // PathNameCS
			= new ParserRuleValue(73, "PathNameCS",
				new @NonNull SerializationRule [] {
					sr0._010 /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				null);
		private final @NonNull ParserRuleValue _074 // PatternExpCS
			= new ParserRuleValue(74, "PatternExpCS",
				new @NonNull SerializationRule [] {
					sr1._065 /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _075 // PostconditionConstraintCS
			= new ParserRuleValue(75, "PostconditionConstraintCS",
				new @NonNull SerializationRule [] {
					sr2._138 /* { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				},
				null);
		private final @NonNull ParserRuleValue _076 // PreconditionConstraintCS
			= new ParserRuleValue(76, "PreconditionConstraintCS",
				new @NonNull SerializationRule [] {
					sr2._139 /* { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				},
				null);
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
				null);
		private final @NonNull DataTypeRuleValue _082 // PrimitiveTypeIdentifier
			= new DataTypeRuleValue(82, "PrimitiveTypeIdentifier");
		private final @NonNull ParserRuleValue _083 // ReferenceCS
			= new ParserRuleValue(83, "ReferenceCS",
				new @NonNull SerializationRule [] {
					sr2._140 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._141 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._142 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._143 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._144 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._145 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _084 // RoundBracketedClauseCS
			= new ParserRuleValue(84, "RoundBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._069 /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				},
				null);
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
				null);
		private final @NonNull ParserRuleValue _090 // ShadowPartCS
			= new ParserRuleValue(90, "ShadowPartCS",
				new @NonNull SerializationRule [] {
					sr1._071 /* ownedInitExpression=StringLiteralExpCS */,
					sr1._072 /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				},
				null);
		private final @NonNull ParserRuleValue _091 // SimplePathNameCS
			= new ParserRuleValue(91, "SimplePathNameCS",
				new @NonNull SerializationRule [] {
					sr1._073 /* ownedPathElements+=FirstPathElementCS */
				},
				null);
		private final @NonNull ParserRuleValue _092 // SpecificationCS
			= new ParserRuleValue(92, "SpecificationCS",
				new @NonNull SerializationRule [] {
					sr2._146 /* exprString=UNQUOTED_STRING */,
					sr2._147 /* ownedExpression=ExpCS */
				},
				null);
		private final @NonNull ParserRuleValue _093 // SquareBracketedClauseCS
			= new ParserRuleValue(93, "SquareBracketedClauseCS",
				new @NonNull SerializationRule [] {
					sr1._074 /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				},
				null);
		private final @NonNull DataTypeRuleValue _094 // StringLiteral
			= new DataTypeRuleValue(94, "StringLiteral");
		private final @NonNull ParserRuleValue _095 // StringLiteralExpCS
			= new ParserRuleValue(95, "StringLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._075 /* segments+=StringLiteral[+] */
				},
				null);
		private final @NonNull ParserRuleValue _096 // StructuralFeatureCS
			= new ParserRuleValue(96, "StructuralFeatureCS",
				new @NonNull SerializationRule [] {
					sr1._100 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._101 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._102 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._103 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr1._104 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr1._105 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
					sr2._140 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._141 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._142 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._143 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					sr2._144 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					sr2._145 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
				iv._42); /* AttributeCS|ReferenceCS|StructuralFeatureCS */
		private final @NonNull ParserRuleValue _097 // StructuredClassCS
			= new ParserRuleValue(97, "StructuredClassCS",
				new @NonNull SerializationRule [] {
					sr2._148 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					sr2._149 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _098 // SysMLCS
			= new ParserRuleValue(98, "SysMLCS",
				new @NonNull SerializationRule [] {
					sr2._150 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */,
					sr2._151 /* { 'sysml' ownedDetails+=DetailCS ';' } */
				},
				null);
		private final @NonNull ParserRuleValue _099 // TemplateBindingCS
			= new ParserRuleValue(99, "TemplateBindingCS",
				new @NonNull SerializationRule [] {
					sr0._011 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _100 // TemplateParameterSubstitutionCS
			= new ParserRuleValue(100, "TemplateParameterSubstitutionCS",
				new @NonNull SerializationRule [] {
					sr0._012 /* ownedActualParameter=TypeRefCS */
				},
				null);
		private final @NonNull ParserRuleValue _101 // TemplateSignatureCS
			= new ParserRuleValue(101, "TemplateSignatureCS",
				new @NonNull SerializationRule [] {
					sr2._152 /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */,
					sr2._153 /* { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				},
				null);
		private final @NonNull ParserRuleValue _102 // TopLevelCS
			= new ParserRuleValue(102, "TopLevelCS",
				new @NonNull SerializationRule [] {
					sr2._154 /* { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] } */
				},
				null);
		private final @NonNull ParserRuleValue _103 // TupleLiteralExpCS
			= new ParserRuleValue(103, "TupleLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._076 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				},
				null);
		private final @NonNull ParserRuleValue _104 // TupleLiteralPartCS
			= new ParserRuleValue(104, "TupleLiteralPartCS",
				new @NonNull SerializationRule [] {
					sr1._077 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _105 // TuplePartCS
			= new ParserRuleValue(105, "TuplePartCS",
				new @NonNull SerializationRule [] {
					sr1._078 /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				},
				null);
		private final @NonNull ParserRuleValue _106 // TupleTypeCS
			= new ParserRuleValue(106, "TupleTypeCS",
				new @NonNull SerializationRule [] {
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _107 // TypeExpCS
			= new ParserRuleValue(107, "TypeExpCS",
				new @NonNull SerializationRule [] {
					sr1._080 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._081 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._082 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._083 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._084 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr1._085 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */
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
				null);
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
				null);
		private final @NonNull ParserRuleValue _114 // TypeParameterCS
			= new ParserRuleValue(114, "TypeParameterCS",
				new @NonNull SerializationRule [] {
					sr0._013 /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				},
				null);
		private final @NonNull ParserRuleValue _115 // TypeRefCS
			= new ParserRuleValue(115, "TypeRefCS",
				new @NonNull SerializationRule [] {
					sr0._014 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */,
					sr0._021 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
					sr0._048 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
					sr1._068 /* name=PrimitiveTypeIdentifier */,
					sr1._079 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
					sr2._162 /* ownedPathName=PathNameCS */,
					sr2._163 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr2._164 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				},
				iv._75); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
		private final @NonNull ParserRuleValue _116 // TypedMultiplicityRefCS
			= new ParserRuleValue(116, "TypedMultiplicityRefCS",
				new @NonNull SerializationRule [] {
					sr2._155 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._156 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._157 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._158 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._159 /* { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */,
					sr2._160 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
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
					sr2._162 /* ownedPathName=PathNameCS */,
					sr2._163 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr2._164 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				},
				iv._62); /* CollectionTypeCS|MapTypeCS|PrimitiveTypeCS|TupleTypeCS|TypeLiteralCS|TypedRefCS|TypedTypeRefCS */
		private final @NonNull ParserRuleValue _118 // TypedTypeRefCS
			= new ParserRuleValue(118, "TypedTypeRefCS",
				new @NonNull SerializationRule [] {
					sr2._162 /* ownedPathName=PathNameCS */,
					sr2._163 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					sr2._164 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				},
				null);
		private final @NonNull TerminalRuleValue _119 // UNQUOTED_STRING
			= new TerminalRuleValue(119, "UNQUOTED_STRING");
		private final @NonNull DataTypeRuleValue _120 // UPPER
			= new DataTypeRuleValue(120, "UPPER");
		private final @NonNull DataTypeRuleValue _121 // URI
			= new DataTypeRuleValue(121, "URI");
		private final @NonNull ParserRuleValue _122 // URIFirstPathElementCS
			= new ParserRuleValue(122, "URIFirstPathElementCS",
				new @NonNull SerializationRule [] {
					sr1._092 /* referredElement=UnrestrictedName */,
					sr1._093 /* referredElement=URI */
				},
				null);
		private final @NonNull ParserRuleValue _123 // URIPathNameCS
			= new ParserRuleValue(123, "URIPathNameCS",
				new @NonNull SerializationRule [] {
					sr1._094 /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				},
				null);
		private final @NonNull DataTypeRuleValue _124 // UnaryOperatorName
			= new DataTypeRuleValue(124, "UnaryOperatorName");
		private final @NonNull ParserRuleValue _125 // UnlimitedNaturalLiteralExpCS
			= new ParserRuleValue(125, "UnlimitedNaturalLiteralExpCS",
				new @NonNull SerializationRule [] {
					sr1._095 /* '*' */
				},
				null);
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
					new SerializationRule_SegmentsList(sr1._096, sl._70) /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._097, sl._71) /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
					new SerializationRule_SegmentsList(sr1._098, sl._71) /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._099, sl._71) /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
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
					new SerializationRule_SegmentsList(sr1._100, sl._74) /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._102, sl._93) /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._104, sl._93) /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._101, sl._75) /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
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
					new SerializationRule_SegmentsList(sr0._024, sl._29) /* symbol={'false|true'} */
				}, null
			);
		private final @NonNull EClassValue _03 // CollectionLiteralExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._017, sl._34) /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
					new SerializationRule_SegmentsList(sr0._025, sl._34) /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
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
					new SerializationRule_SegmentsList(sr1._082, sl._09) /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */
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
					new SerializationRule_SegmentsList(sr2._160, sl._23) /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
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
					new SerializationRule_SegmentsList(sr1._108, sl._85) /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._106, sl._87) /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._110, sl._87) /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._109, sl._86) /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._107, sl._88) /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._111, sl._88) /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
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
					new SerializationRule_SegmentsList(sr1._114, sl._68) /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._118, sl._68) /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._117, sl._67) /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._115, sl._69) /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._119, sl._69) /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
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
					new SerializationRule_SegmentsList(sr1._122, sl._60) /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._120, sl._78) /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._123, sl._61) /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
					new SerializationRule_SegmentsList(sr1._121, sl._80) /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
						iv._45) /* AnnotationCS|AnnotationElementCS|DocumentationCS|SysMLCS */
				}
			);
		private final @NonNull EClassValue _14 // ExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._026, sl._29) /* '*' */,
					new SerializationRule_SegmentsList(sr0._027, sl._29) /* 'invalid' */,
					new SerializationRule_SegmentsList(sr0._028, sl._29) /* 'null' */,
					new SerializationRule_SegmentsList(sr0._029, sl._29) /* 'self' */
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
					new SerializationRule_SegmentsList(sr0._030, sl._55) /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
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
					new SerializationRule_SegmentsList(sr0._031, sl._35) /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */
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
					new SerializationRule_SegmentsList(sr0._032, sl._64) /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
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
					new SerializationRule_SegmentsList(sr0._033, sl._34) /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
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
					new SerializationRule_SegmentsList(sr1._084, sl._25) /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
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
					new SerializationRule_SegmentsList(sr2._128, sl._27) /* { 'reference' ownedPathName=PathNameCS ';' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
						iv._27) /* PathNameCS */
				}
			);
		private final @NonNull EClassValue _29 // MultiplicityBoundsCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._001, sl._26) /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */,
					new SerializationRule_SegmentsList(sr0._002, sl._14) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._003, sl._15) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._004, sl._15) /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, null
			);
		private final @NonNull EClassValue _30 // MultiplicityStringCS
			= new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._005, sl._16) /* { '[' stringBounds={'*|+|?'} ']' } */,
					new SerializationRule_SegmentsList(sr0._006, sl._17) /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
					new SerializationRule_SegmentsList(sr0._007, sl._17) /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
					new SerializationRule_SegmentsList(sr0._008, sl._95) /* stringBounds={'*|+|?'} */
				}, null
			);
		private final @NonNull EClassValue _31 // NameExpCS
			= new EClassValue(EssentialOCLCSPackage.Literals.NAME_EXP_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._034, sl._30) /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
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
					new SerializationRule_SegmentsList(sr0._051, sl._29) /* ownedNameExpression=NavigatingArgExpCS */,
					new SerializationRule_SegmentsList(sr0._052, sl._46) /* { ':' ownedType=TypeExpCS } */,
					new SerializationRule_SegmentsList(sr0._054, sl._37) /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._053, sl._38) /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._055, sl._32) /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
					new SerializationRule_SegmentsList(sr0._056, sl._49) /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */,
					new SerializationRule_SegmentsList(sr0._060, sl._41) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
					new SerializationRule_SegmentsList(sr0._058, sl._43) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
					new SerializationRule_SegmentsList(sr0._057, sl._44) /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
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
					new SerializationRule_SegmentsList(sr0._035, sl._40) /* { '(' ownedExpression=ExpCS ')' } */,
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
					new SerializationRule_SegmentsList(sr0._036, sl._29) /* symbol=NUMBER_LITERAL */,
					new SerializationRule_SegmentsList(sr1._064, sl._29) /* symbol=NUMBER_LITERAL */
				}, null
			);
		private final @NonNull EClassValue _36 // OCLinEcoreConstraintCS
			= new EClassValue(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._127, sl._77) /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' } */,
					new SerializationRule_SegmentsList(sr1._126, sl._76) /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */,
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
					new SerializationRule_SegmentsList(sr2._129, sl._58) /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._131, sl._89) /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._133, sl._89) /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._130, sl._59) /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
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
					new SerializationRule_SegmentsList(sr1._092, sl._95) /* referredElement=UnrestrictedName */
				}, null
			);
		private final @NonNull EClassValue _41 // PathElementWithURICS
			= new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr1._093, sl._95) /* referredElement=URI */
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
					new SerializationRule_SegmentsList(sr0._037, sl._46) /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
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
					new SerializationRule_SegmentsList(sr1._080, sl._19) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._087, sl._19) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr2._155, sl._19) /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
						iv._19) /* MultiplicityCS */
				}
			);
		private final @NonNull EClassValue _46 // ReferenceCS
			= new EClassValue(BaseCSPackage.Literals.REFERENCE_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._140, sl._72) /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._142, sl._91) /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._144, sl._91) /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._141, sl._73) /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					new SerializationRule_SegmentsList(sr2._143, sl._92) /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
					new SerializationRule_SegmentsList(sr2._145, sl._92) /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
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
					new SerializationRule_SegmentsList(sr1._071, sl._29) /* ownedInitExpression=StringLiteralExpCS */,
					new SerializationRule_SegmentsList(sr1._072, sl._65) /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
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
					new SerializationRule_SegmentsList(sr0._038, sl._29) /* segments+=StringLiteral[+] */,
					new SerializationRule_SegmentsList(sr1._075, sl._29) /* segments+=StringLiteral[+] */
				}, null
			);
		private final @NonNull EClassValue _52 // StructuredClassCS
			= new EClassValue(BaseCSPackage.Literals.STRUCTURED_CLASS_CS,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr2._148, sl._83) /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
					new SerializationRule_SegmentsList(sr2._149, sl._84) /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
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
					new SerializationRule_SegmentsList(sr2._151, sl._51) /* { 'sysml' ownedDetails+=DetailCS ';' } */,
					new SerializationRule_SegmentsList(sr2._150, sl._63) /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */
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
					new SerializationRule_SegmentsList(sr0._039, sl._62) /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
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
					new SerializationRule_SegmentsList(sr1._081, sl._21) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr1._088, sl._21) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr2._156, sl._21) /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
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
					new SerializationRule_SegmentsList(sr0._040, sl._29) /* ownedType=TypeLiteralWithMultiplicityCS */,
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
					new SerializationRule_SegmentsList(sr1._085, sl._03) /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */,
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
					new SerializationRule_SegmentsList(sr2._162, sl._00) /* ownedPathName=PathNameCS */,
					new SerializationRule_SegmentsList(sr2._163, sl._06) /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
					new SerializationRule_SegmentsList(sr2._164, sl._10) /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
					new SerializationRule_SegmentsList(sr2._159, sl._01) /* { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr2._157, sl._07) /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] } */,
					new SerializationRule_SegmentsList(sr2._158, sl._11) /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] } */
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
					new SerializationRule_SegmentsList(sr0._014, sl._28) /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
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
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._040 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._124 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._95,
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
		private @NonNull SerializationRule _001 = new SerializationRule(55,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._083 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._027 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || «null» */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=43 || «? » «value» «? » */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'..' || «! » «value» «! » */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=120 || «? » «value» «? » */
			},
			sl._26,
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
		private @NonNull SerializationRule _002 = new SerializationRule(56,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._083 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._027 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._186 /* 1*steps-1..7 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=43 || «? » «value» «? » */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._008 /* 1*'..' || «! » «value» «! » */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=120 || «? » «value» «? » */,
				st._022 /* 1*']' || «! » «value» */
			},
			sl._14,
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
		private @NonNull SerializationRule _003 = new SerializationRule(56,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._083 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._027 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=43 || «? » «value» «? » */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._008 /* 1*'..' || «! » «value» «! » */,
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
		private @NonNull SerializationRule _004 = new SerializationRule(56,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._119 /* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._083 /* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				ms._027 /* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._101 /* 1*MultiplicityBoundsCS::lowerBound=43 || «? » «value» «? » */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._008 /* 1*'..' || «! » «value» «! » */,
				st._102 /* 1*MultiplicityBoundsCS::upperBound=120 || «? » «value» «? » */,
				st._218 /* V01*'|1' || «? » «value» «? » */,
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
						new EnumerationValue_GrammarCardinality(ev._23, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} ']' }
		private @NonNull SerializationRule _005 = new SerializationRule(56,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._028 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._181 /* 1*steps-1..4 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._103 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */,
				st._022 /* 1*']' || «! » «value» */
			},
			sl._16,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._04)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._04, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} '|?' ']' }
		private @NonNull SerializationRule _006 = new SerializationRule(56,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._028 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._103 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._04, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Base::MultiplicityCS : { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' }
		private @NonNull SerializationRule _007 = new SerializationRule(56,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._084 /* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
				ms._028 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._103 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */,
				st._194 /* V00*'|1' || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._04, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._23, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Base::MultiplicityStringCS : stringBounds={'*|+|?'}
		private @NonNull SerializationRule _008 = new SerializationRule(57,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._028 /* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._103 /* 1*MultiplicityStringCS::stringBounds='*|+|?' || «? » «value» «? » */
			},
			sl._95,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					ev._04)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._04, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Base::NextPathElementCS : referredElement=UnreservedName
		private @NonNull SerializationRule _009 = new SerializationRule(67,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._040 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._123 /* 1*PathElementCS::referredElement=UnreservedName || «? » «value» «? » */
			},
			sl._95,
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
		private @NonNull SerializationRule _010 = new SerializationRule(73,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._247 /* check-rule basecs::PathNameCS.ownedPathElements : 31|67 */,
				ms._061 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || «null» */,
				st._126 /* 1*PathNameCS::ownedPathElements+=31 || «null» */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._010 /* 1*'::' || «! » «value» «! » */,
				st._127 /* 1*PathNameCS::ownedPathElements+=67 || «null» */
			},
			sl._04,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._23) /* FirstPathElementCS|NextPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(31, GrammarCardinality.ONE),
					new RuleIndex_GrammarCardinality(67, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::TemplateBindingCS : { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _011 = new SerializationRule(99,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._256 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 100 */,
				ms._255 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 56 */,
				ms._127 /* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				ms._064 /* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._185 /* 1*steps-1..6 || «null» */,
				st._141 /* 1*TemplateBindingCS::ownedSubstitutions+=100 || «null» */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._141 /* 1*TemplateBindingCS::ownedSubstitutions+=100 || «null» */,
				st._223 /* V01*TemplateBindingCS::ownedMultiplicity=56 || «null» */
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(100, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// Base::TemplateParameterSubstitutionCS : ownedActualParameter=TypeRefCS
		private @NonNull SerializationRule _012 = new SerializationRule(100,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._257 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 115 */,
				ms._047 /* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._142 /* 1*TemplateParameterSubstitutionCS::ownedActualParameter=115 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					iv._59) /* TypeRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(115, GrammarCardinality.ONE)
					}
				)
			});
		// Base::TypeParameterCS : { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] }
		private @NonNull SerializationRule _013 = new SerializationRule(114,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._261 /* check-rule basecs::TypeParameterCS.ownedExtends : 117 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._068 /* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				ms._111 /* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._209 /* V00*steps-3..8 || «null» */,
				st._035 /* 1*'extends' || «? » «value» «? » */,
				st._150 /* 1*TypeParameterCS::ownedExtends+=117 || «null» */,
				st._233 /* V01*steps-6..8 || «null» */,
				st._002 /* 1*'&&' || «? » «value» «? » */,
				st._150 /* 1*TypeParameterCS::ownedExtends+=117 || «null» */
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
					new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Base::WildcardTypeRefCS : { '?' { 'extends' ownedExtends=TypedRefCS }[?] }
		private @NonNull SerializationRule _014 = new SerializationRule(129,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._267 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : 117 */,
				ms._102 /* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || «null» */,
				st._016 /* 1*'?' || «? » «value» «? » */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._035 /* 1*'extends' || «? » «value» «? » */,
				st._161 /* 1*WildcardTypeRefCS::ownedExtends=117 || «null» */
			},
			sl._28,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					iv._61) /* TypedRefCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::BooleanLiteralExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _015 = new SerializationRule(5,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._002 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._066 /* 1*BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._14)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._14, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::CoIteratorVariableCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] }
		private @NonNull SerializationRule _016 = new SerializationRule(7,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._320 /* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
				ms._101 /* assign V0 = |VariableCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._160 /* 1*VariableCS::ownedType=107 || «null» */
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
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _017 = new SerializationRule(8,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._272 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9 */,
				ms._273 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11 */,
				ms._003 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._057 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._104 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._188 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*CollectionLiteralExpCS::ownedType=11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._212 /* V00*steps-4..8 || «null» */,
				st._068 /* 1*CollectionLiteralExpCS::ownedParts+=9 || «null» */,
				st._233 /* V01*steps-6..8 || «null» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(9, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(11, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : ownedExpression=PatternExpCS
		private @NonNull SerializationRule _018 = new SerializationRule(9,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._275 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 74 */,
				ms._004 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._071 /* 1*CollectionLiteralPartCS::ownedExpression=74 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					iv._28) /* PatternExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(74, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionLiteralPartCS : { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] }
		private @NonNull SerializationRule _019 = new SerializationRule(9,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._276 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : 30 */,
				ms._274 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : 30 */,
				ms._073 /* assign V0 = |CollectionLiteralPartCS::ownedLastExpression| */,
				ms._004 /* assert (|CollectionLiteralPartCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._070 /* 1*CollectionLiteralPartCS::ownedExpression=30 || «null» */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._008 /* 1*'..' || «! » «value» «! » */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CollectionPatternCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' }
		private @NonNull SerializationRule _020 = new SerializationRule(10,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._278 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 11 */,
				ms._277 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74 */,
				ms._074 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._105 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._005 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._163 /* 1*steps-1..11 || «null» */,
				st._074 /* 1*CollectionPatternCS::ownedType=11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._210 /* V00*steps-4..10 || «null» */,
				st._073 /* 1*CollectionPatternCS::ownedParts+=74 || «null» */,
				st._233 /* V01*steps-6..8 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._073 /* 1*CollectionPatternCS::ownedParts+=74 || «null» */,
				st._006 /* 1*'++' || «? » «value» «? » */,
				st._075 /* 1*CollectionPatternCS::restVariableName=35 || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._08,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._4) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._28) /* PatternExpCS */
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
					new RuleIndex_GrammarCardinality(74, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(11, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::CollectionTypeCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] }
		private @NonNull SerializationRule _021 = new SerializationRule(11,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
				ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
				ms._075 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._006 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._115 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._186 /* 1*steps-1..7 || «null» */,
				st._076 /* 1*CollectionTypeCS::name=12 || «? » «value» «? » */,
				st._208 /* V00*steps-3..7 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._077 /* 1*CollectionTypeCS::ownedType=108 || «null» */,
				st._220 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=56 || «null» */,
				st._004 /* 1*')' || «! » «value» */
			},
			sl._22,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._52) /* TypeExpWithoutMultiplicityCS */
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
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(108, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::CurlyBracketedClauseCS : { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _022 = new SerializationRule(13,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._282 /* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : 90 */,
				ms._058 /* assign V0 = (|CurlyBracketedClauseCS::ownedParts| > 0) */,
				ms._106 /* assign V1 = (|CurlyBracketedClauseCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._208 /* V00*steps-3..7 || «null» */,
				st._080 /* 1*CurlyBracketedClauseCS::ownedParts+=90 || «null» */,
				st._229 /* V01*steps-5..7 || «null» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(90, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ElseIfThenExpCS : { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS }
		private @NonNull SerializationRule _023 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._288 /* check-rule essentialoclcs::IfThenExpCS.ownedCondition : 30 */,
				ms._289 /* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : 30 */,
				ms._016 /* assert (|IfThenExpCS::ownedThenExpression| - 1) == 0 */,
				ms._015 /* assert (|IfThenExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._032 /* 1*'elseif' || «? » «value» «? » */,
				st._087 /* 1*IfThenExpCS::ownedCondition=30 || «null» */,
				st._058 /* 1*'then' || «? » «value» «? » */,
				st._088 /* 1*IfThenExpCS::ownedThenExpression=30 || «null» */
			},
			sl._52,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : symbol={'false|true'}
		private @NonNull SerializationRule _024 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._002 /* assert (|BooleanLiteralExpCS::symbol.'false|true'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._066 /* 1*BooleanLiteralExpCS::symbol='false|true' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					ev._14)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._14, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// EssentialOCL::ExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _025 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._272 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : 9 */,
				ms._273 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : 11 */,
				ms._003 /* assert (|CollectionLiteralExpCS::ownedType| - 1) == 0 */,
				ms._057 /* assign V0 = (|CollectionLiteralExpCS::ownedParts| > 0) */,
				ms._104 /* assign V1 = (|CollectionLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._188 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._069 /* 1*CollectionLiteralExpCS::ownedType=11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._212 /* V00*steps-4..8 || «null» */,
				st._068 /* 1*CollectionLiteralExpCS::ownedParts+=9 || «null» */,
				st._233 /* V01*steps-6..8 || «null» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(9, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(11, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : '*'
		private @NonNull SerializationRule _026 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._005 /* 1*'*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : 'invalid'
		private @NonNull SerializationRule _027 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._040 /* 1*'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : 'null'
		private @NonNull SerializationRule _028 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._046 /* 1*'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : 'self'
		private @NonNull SerializationRule _029 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._055 /* 1*'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _030 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._285 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30 */,
				ms._284 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74 */,
				ms._287 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30 */,
				ms._286 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
				ms._013 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._080 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._014 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._012 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._188 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._036 /* 1*'if' || «? » «value» «? » */,
				st._084 /* 1*IfExpCS::ownedCondition=30|74 || «null» */,
				st._058 /* 1*'then' || «? » «value» «? » */,
				st._086 /* 1*IfExpCS::ownedThenExpression=30 || «null» */,
				st._198 /* V00*IfExpCS::ownedIfThenExpressions+=20 || «null» */,
				st._031 /* 1*'else' || «? » «value» «? » */,
				st._085 /* 1*IfExpCS::ownedElseExpression=30 || «null» */,
				st._033 /* 1*'endif' || «? » «value» «? » */
			},
			sl._55,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._29) /* ExpCS|PatternExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._7) /* ElseIfThenExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE),
					new RuleIndex_GrammarCardinality(74, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS }
		private @NonNull SerializationRule _031 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._290 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : 78 */,
				ms._306 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 30 */,
				ms._039 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._018 /* assert (|InfixExpCS::ownedLeft| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._181 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._090 /* 1*InfixExpCS::ownedLeft=78 || «null» */,
				st._108 /* 1*NamedElementCS::name=4 || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(78, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _032 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._291 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30 */,
				ms._019 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _033 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._296 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51 */,
				ms._295 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50 */,
				ms._021 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._060 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._107 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._188 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._095 /* 1*MapLiteralExpCS::ownedType=51 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._212 /* V00*steps-4..8 || «null» */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=50 || «null» */,
				st._233 /* V01*steps-6..8 || «null» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(51, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _034 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._271 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93 */,
				ms._268 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._270 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84 */,
				ms._269 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73 */,
				ms._173 /* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				ms._156 /* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				ms._113 /* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				ms._071 /* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				ms._000 /* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._064 /* 1*AbstractNameExpCS::ownedPathName=73 || «null» */,
				st._195 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=93 || «null» */,
				st._219 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=84 || «null» */,
				st._238 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=13 || «null» */,
				st._265 /* V03*steps-6..8 || «null» */,
				st._017 /* 1*'@' || «? » «value» «? » */,
				st._051 /* 1*'pre' || «? » «value» «? » */
			},
			sl._30,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._08)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._39) /* SquareBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._35) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._08, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(84, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(93, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _035 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._305 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 30 */,
				ms._037 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._181 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : symbol=NUMBER_LITERAL
		private @NonNull SerializationRule _036 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._038 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*NumberLiteralExpCS::symbol=58 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
		private @NonNull SerializationRule _037 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._308 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 78 */,
				ms._039 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._178 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
					new RuleIndex_GrammarCardinality(78, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _038 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._093 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._202 /* V00*StringLiteralExpCS::segments+=94 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
		private @NonNull SerializationRule _039 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._314 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104 */,
				ms._066 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._020 /* 1*'Tuple' || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._144 /* 1*TupleLiteralExpCS::ownedParts+=104 || «null» */,
				st._214 /* V00*steps-5..7 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._144 /* 1*TupleLiteralExpCS::ownedParts+=104 || «null» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(104, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::ExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _040 = new SerializationRule(30,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._315 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112 */,
				ms._049 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*TypeLiteralExpCS::ownedType=112 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._54) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(112, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::IfExpCS : { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' }
		private @NonNull SerializationRule _041 = new SerializationRule(36,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._285 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : 30 */,
				ms._284 /* check-rule essentialoclcs::IfExpCS.ownedCondition : 30|74 */,
				ms._287 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : 30 */,
				ms._286 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : 20 */,
				ms._013 /* assert (|IfExpCS::ownedElseExpression| - 1) == 0 */,
				ms._080 /* assign V0 = |IfExpCS::ownedIfThenExpressions| */,
				ms._014 /* assert (|IfExpCS::ownedThenExpression| - 1) == 0 */,
				ms._012 /* assert (|IfExpCS::ownedCondition| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._188 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._036 /* 1*'if' || «? » «value» «? » */,
				st._084 /* 1*IfExpCS::ownedCondition=30|74 || «null» */,
				st._058 /* 1*'then' || «? » «value» «? » */,
				st._086 /* 1*IfExpCS::ownedThenExpression=30 || «null» */,
				st._198 /* V00*IfExpCS::ownedIfThenExpressions+=20 || «null» */,
				st._031 /* 1*'else' || «? » «value» «? » */,
				st._085 /* 1*IfExpCS::ownedElseExpression=30 || «null» */,
				st._033 /* 1*'endif' || «? » «value» «? » */
			},
			sl._55,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					iv._29) /* ExpCS|PatternExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					iv._7) /* ElseIfThenExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE),
					new RuleIndex_GrammarCardinality(74, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(20, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::InvalidLiteralExpCS : 'invalid'
		private @NonNull SerializationRule _042 = new SerializationRule(40,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._040 /* 1*'invalid' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::LambdaLiteralExpCS : { 'Lambda' '{' ownedExpressionCS=ExpCS '}' }
		private @NonNull SerializationRule _043 = new SerializationRule(44,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._291 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : 30 */,
				ms._019 /* assert (|LambdaLiteralExpCS::ownedExpressionCS| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::LetExpCS : { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS }
		private @NonNull SerializationRule _044 = new SerializationRule(45,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._293 /* check-rule essentialoclcs::LetExpCS.ownedVariables : 46 */,
				ms._292 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : 30 */,
				ms._020 /* assert (|LetExpCS::ownedInExpression| - 1) == 0 */,
				ms._059 /* assign V0 = (|LetExpCS::ownedVariables| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._043 /* 1*'let' || «? » «value» «? » */,
				st._093 /* 1*LetExpCS::ownedVariables+=46 || «null» */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._093 /* 1*LetExpCS::ownedVariables+=46 || «null» */,
				st._038 /* 1*'in' || «? » «value» «? » */,
				st._092 /* 1*LetExpCS::ownedInExpression=30 || «null» */
			},
			sl._48,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					iv._14) /* LetVariableCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(46, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::LetVariableCS : { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _045 = new SerializationRule(46,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._320 /* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
				ms._294 /* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : 84 */,
				ms._319 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 30 */,
				ms._056 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._133 /* assign V1 = |VariableCS::ownedType| */,
				ms._081 /* assign V0 = |LetVariableCS::ownedRoundBracketedClause| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._199 /* V00*LetVariableCS::ownedRoundBracketedClause=84 || «null» */,
				st._227 /* V01*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._160 /* 1*VariableCS::ownedType=107 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._159 /* 1*VariableCS::ownedInitExpression=30 || «null» */
			},
			sl._50,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._35) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */
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
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(84, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::MapLiteralExpCS : { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' }
		private @NonNull SerializationRule _046 = new SerializationRule(49,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._296 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : 51 */,
				ms._295 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : 50 */,
				ms._021 /* assert (|MapLiteralExpCS::ownedType| - 1) == 0 */,
				ms._060 /* assign V0 = (|MapLiteralExpCS::ownedParts| > 0) */,
				ms._107 /* assign V1 = (|MapLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._188 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._095 /* 1*MapLiteralExpCS::ownedType=51 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._212 /* V00*steps-4..8 || «null» */,
				st._094 /* 1*MapLiteralExpCS::ownedParts+=50 || «null» */,
				st._233 /* V01*steps-6..8 || «null» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(50, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(51, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::MapLiteralPartCS : { ownedKey=ExpCS '<-' ownedValue=ExpCS }
		private @NonNull SerializationRule _047 = new SerializationRule(50,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._297 /* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : 30 */,
				ms._298 /* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : 30 */,
				ms._023 /* assert (|MapLiteralPartCS::ownedValue| - 1) == 0 */,
				ms._022 /* assert (|MapLiteralPartCS::ownedKey| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._181 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::MapTypeCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] }
		private @NonNull SerializationRule _048 = new SerializationRule(51,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
				ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
				ms._082 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._025 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._024 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || «null» */,
				st._019 /* 1*'Map' || «? » «value» «? » */,
				st._209 /* V00*steps-3..8 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._09, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::Model : ownedExpression=ExpCS
		private @NonNull SerializationRule _049 = new SerializationRule(52,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._281 /* check-rule essentialoclcs::ContextCS.ownedExpression : 30 */,
				ms._010 /* assert (|ContextCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._079 /* 1*ContextCS::ownedExpression=30 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NameExpCS : { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] }
		private @NonNull SerializationRule _050 = new SerializationRule(59,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._271 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : 93 */,
				ms._268 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._270 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : 84 */,
				ms._269 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : 73 */,
				ms._173 /* assign V3 = |AbstractNameExpCS::isPre.'@'| */,
				ms._156 /* assign V2 = |AbstractNameExpCS::ownedCurlyBracketedClause| */,
				ms._113 /* assign V1 = |AbstractNameExpCS::ownedRoundBracketedClause| */,
				ms._071 /* assign V0 = |AbstractNameExpCS::ownedSquareBracketedClauses| */,
				ms._000 /* assert (|AbstractNameExpCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._064 /* 1*AbstractNameExpCS::ownedPathName=73 || «null» */,
				st._195 /* V00*AbstractNameExpCS::ownedSquareBracketedClauses+=93 || «null» */,
				st._219 /* V01*AbstractNameExpCS::ownedRoundBracketedClause=84 || «null» */,
				st._238 /* V02*AbstractNameExpCS::ownedCurlyBracketedClause=13 || «null» */,
				st._265 /* V03*steps-6..8 || «null» */,
				st._017 /* 1*'@' || «? » «value» «? » */,
				st._051 /* 1*'pre' || «? » «value» «? » */
			},
			sl._30,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					ev._08)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					iv._39) /* SquareBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					iv._35) /* RoundBracketedClauseCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._08, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(84, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(93, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : ownedNameExpression=NavigatingArgExpCS
		private @NonNull SerializationRule _051 = new SerializationRule(60,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _052 = new SerializationRule(60,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._033 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._178 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _053 = new SerializationRule(60,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._122 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				ms._086 /* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				ms._033 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._162 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedType=107 || «null» */,
				st._214 /* V00*steps-5..7 || «null» */,
				st._013 /* 1*'<-' || «? » «value» «? » */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=7 || «null» */,
				st._235 /* V01*steps-8..10 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._38,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(7, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _054 = new SerializationRule(60,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._087 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._030 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._186 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._013 /* 1*'<-' || «? » «value» «? » */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=7 || «null» */,
				st._214 /* V00*steps-5..7 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._37,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(7, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingArgCS : { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _055 = new SerializationRule(60,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._031 /* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				ms._121 /* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				ms._088 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._162 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedType=107 || «null» */,
				st._233 /* V01*steps-6..8 || «null» */,
				st._013 /* 1*'<-' || «? » «value» «? » */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=7 || «null» */,
				st._038 /* 1*'in' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._32,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(7, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingBarArgCS : { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _056 = new SerializationRule(62,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._088 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._036 /* assert (|NavigatingArgCS::prefix.'|'| - 1) == 0 */,
				ms._122 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._188 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._061 /* 1*'|' || «? » «value» «? » */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._213 /* V00*steps-4..9 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedType=107 || «null» */,
				st._234 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._49,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._22)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._22, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _057 = new SerializationRule(63,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._122 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */,
				ms._086 /* assign V0 = |NavigatingArgCS::ownedCoIterator| */,
				ms._033 /* assert (|NavigatingArgCS::ownedType| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._034 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._163 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedType=107 || «null» */,
				st._216 /* V00*steps-6..8 || «null» */,
				st._013 /* 1*'<-' || «? » «value» «? » */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=7 || «null» */,
				st._237 /* V01*steps-9..11 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._44,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._05, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(7, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] }
		private @NonNull SerializationRule _058 = new SerializationRule(63,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._087 /* assign V0 = |NavigatingArgCS::ownedInitExpression| */,
				ms._030 /* assert (|NavigatingArgCS::ownedCoIterator| - 1) == 0 */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._034 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._013 /* 1*'<-' || «? » «value» «? » */,
				st._109 /* 1*NavigatingArgCS::ownedCoIterator=7 || «null» */,
				st._216 /* V00*steps-6..8 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._43,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._05)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._05, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(7, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _059 = new SerializationRule(63,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._301 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : 7 */,
				ms._031 /* assert (|NavigatingArgCS::ownedInitExpression| - 1) == 0 */,
				ms._121 /* assign V1 = |NavigatingArgCS::ownedCoIterator| */,
				ms._088 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._034 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._163 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedType=107 || «null» */,
				st._234 /* V01*steps-7..9 || «null» */,
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
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					iv._2) /* CoIteratorVariableCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._05, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(7, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NavigatingCommaArgCS : { prefix=',' ownedNameExpression=NavigatingArgExpCS }
		private @NonNull SerializationRule _060 = new SerializationRule(63,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._034 /* assert (|NavigatingArgCS::prefix.','| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._178 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._05, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NavigatingSemiArgCS : { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] }
		private @NonNull SerializationRule _061 = new SerializationRule(64,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._302 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : 30 */,
				ms._304 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : 107 */,
				ms._303 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : 61 */,
				ms._088 /* assign V0 = |NavigatingArgCS::ownedType| */,
				ms._032 /* assert (|NavigatingArgCS::ownedNameExpression| - 1) == 0 */,
				ms._035 /* assert (|NavigatingArgCS::prefix.';'| - 1) == 0 */,
				ms._122 /* assign V1 = |NavigatingArgCS::ownedInitExpression| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._188 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._111 /* 1*NavigatingArgCS::ownedNameExpression=61 || «null» */,
				st._213 /* V00*steps-4..9 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._112 /* 1*NavigatingArgCS::ownedType=107 || «null» */,
				st._234 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._110 /* 1*NavigatingArgCS::ownedInitExpression=30 || «null» */
			},
			sl._45,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					ev._07)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					iv._20) /* NavigatingArgExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._07, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(61, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::NestedExpCS : { '(' ownedExpression=ExpCS ')' }
		private @NonNull SerializationRule _062 = new SerializationRule(66,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._305 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : 30 */,
				ms._037 /* assert (|NestedExpCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._181 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::NullLiteralExpCS : 'null'
		private @NonNull SerializationRule _063 = new SerializationRule(68,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._046 /* 1*'null' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
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
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._038 /* assert (|NumberLiteralExpCS::symbol| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._114 /* 1*NumberLiteralExpCS::symbol=58 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
		private @NonNull SerializationRule _065 = new SerializationRule(74,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._309 /* check-rule essentialoclcs::PatternExpCS.ownedPatternType : 107 */,
				ms._042 /* assert (|PatternExpCS::ownedPatternType| - 1) == 0 */,
				ms._091 /* assign V0 = |PatternExpCS::patternVariableName| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._181 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._201 /* V00*PatternExpCS::patternVariableName=127 || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._128 /* 1*PatternExpCS::ownedPatternType=107 || «null» */
			},
			sl._65,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE,
					iv._51) /* TypeExpCS */
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
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedLetExpCS : { name=UnaryOperatorName ownedRight=PrefixedLetExpCS }
		private @NonNull SerializationRule _066 = new SerializationRule(77,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._307 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 77 */,
				ms._039 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._178 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
					new RuleIndex_GrammarCardinality(77, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrefixedPrimaryExpCS : { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS }
		private @NonNull SerializationRule _067 = new SerializationRule(78,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._308 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : 78 */,
				ms._039 /* assert (|OperatorExpCS::ownedRight| - 1) == 0 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._178 /* 1*steps-1..3 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
					new RuleIndex_GrammarCardinality(78, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::PrimitiveTypeCS : name=PrimitiveTypeIdentifier
		private @NonNull SerializationRule _068 = new SerializationRule(81,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._043 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._129 /* 1*PrimitiveTypeRefCS::name=82 || «? » «value» «? » */
			},
			sl._95,
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
		private @NonNull SerializationRule _069 = new SerializationRule(84,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._310 /* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : 60|62|63|64 */,
				ms._062 /* assign V0 = (|RoundBracketedClauseCS::ownedArguments| > 0) */,
				ms._109 /* assign V1 = (|RoundBracketedClauseCS::ownedArguments| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._185 /* 1*steps-1..6 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._133 /* 1*RoundBracketedClauseCS::ownedArguments+=60 || «null» */,
				st._222 /* V01*RoundBracketedClauseCS::ownedArguments+=63|64|62 || «null» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(60, GrammarCardinality.ZERO_OR_ONE),
					new RuleIndex_GrammarCardinality(62, GrammarCardinality.ZERO_OR_MORE),
					new RuleIndex_GrammarCardinality(63, GrammarCardinality.ZERO_OR_MORE),
					new RuleIndex_GrammarCardinality(64, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::SelfExpCS : 'self'
		private @NonNull SerializationRule _070 = new SerializationRule(89,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._055 /* 1*'self' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// EssentialOCL::ShadowPartCS : ownedInitExpression=StringLiteralExpCS
		private @NonNull SerializationRule _071 = new SerializationRule(90,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._312 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 95 */,
				ms._044 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._135 /* 1*ShadowPartCS::ownedInitExpression=95 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._40) /* StringLiteralExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(95, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::ShadowPartCS : { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) }
		private @NonNull SerializationRule _072 = new SerializationRule(90,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._311 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : 30|74 */,
				ms._044 /* assert (|ShadowPartCS::ownedInitExpression| - 1) == 0 */,
				ms._045 /* assert (|ShadowPartCS::referredProperty| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._181 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._136 /* 1*ShadowPartCS::referredProperty=UnrestrictedName || «? » «value» «? » */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._134 /* 1*ShadowPartCS::ownedInitExpression=30|74 || «null» */
			},
			sl._65,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					iv._29) /* ExpCS|PatternExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE),
					new RuleIndex_GrammarCardinality(74, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				)
			});
		// EssentialOCL::SimplePathNameCS : ownedPathElements+=FirstPathElementCS
		private @NonNull SerializationRule _073 = new SerializationRule(91,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._246 /* check-rule basecs::PathNameCS.ownedPathElements : 31 */,
				ms._041 /* assert (|PathNameCS::ownedPathElements| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._126 /* 1*PathNameCS::ownedPathElements+=31 || «null» */
			},
			sl._00,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._10) /* FirstPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(31, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::SquareBracketedClauseCS : { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' }
		private @NonNull SerializationRule _074 = new SerializationRule(93,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._313 /* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : 30 */,
				ms._063 /* assign V0 = (|SquareBracketedClauseCS::ownedTerms| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._186 /* 1*steps-1..7 || «null» */,
				st._021 /* 1*'[' || «! » «value» «! » */,
				st._138 /* 1*SquareBracketedClauseCS::ownedTerms+=30 || «null» */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._138 /* 1*SquareBracketedClauseCS::ownedTerms+=30 || «null» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::StringLiteralExpCS : segments+=StringLiteral[+]
		private @NonNull SerializationRule _075 = new SerializationRule(95,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._093 /* assign V0 = |StringLiteralExpCS::segments| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._202 /* V00*StringLiteralExpCS::segments+=94 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
		private @NonNull SerializationRule _076 = new SerializationRule(103,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._314 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : 104 */,
				ms._066 /* assign V0 = (|TupleLiteralExpCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._020 /* 1*'Tuple' || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._144 /* 1*TupleLiteralExpCS::ownedParts+=104 || «null» */,
				st._214 /* V00*steps-5..7 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._144 /* 1*TupleLiteralExpCS::ownedParts+=104 || «null» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(104, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// EssentialOCL::TupleLiteralPartCS : { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS }
		private @NonNull SerializationRule _077 = new SerializationRule(104,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._320 /* check-rule essentialoclcs::VariableCS.ownedType : 107 */,
				ms._319 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : 30 */,
				ms._056 /* assert (|VariableCS::ownedInitExpression| - 1) == 0 */,
				ms._101 /* assign V0 = |VariableCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._186 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._160 /* 1*VariableCS::ownedType=107 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._159 /* 1*VariableCS::ownedInitExpression=30 || «null» */
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TuplePartCS : { name=UnrestrictedName ':' ownedType=TypeExpCS }
		private @NonNull SerializationRule _078 = new SerializationRule(105,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._262 /* check-rule basecs::TypedElementCS.ownedType : 107 */,
				ms._051 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._181 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._151 /* 1*TypedElementCS::ownedType=107 || «null» */
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
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TupleTypeCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] }
		private @NonNull SerializationRule _079 = new SerializationRule(106,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
				ms._048 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._067 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._110 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._153 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._162 /* 1*steps-1..10 || «null» */,
				st._020 /* 1*'Tuple' || «? » «value» «? » */,
				st._206 /* V00*steps-3..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._231 /* V01*steps-5..9 || «null» */,
				st._145 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._248 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._145 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._10, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _080 = new SerializationRule(107,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._100 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._043 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._178 /* 1*steps-1..3 || «null» */,
				st._129 /* 1*PrimitiveTypeRefCS::name=82 || «? » «value» «? » */,
				st._204 /* V00*TypedRefCS::ownedMultiplicity=56 || «null» */
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
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _081 = new SerializationRule(107,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._181 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._048 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._067 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._110 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._153 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._163 /* 1*steps-1..11 || «null» */,
				st._020 /* 1*'Tuple' || «? » «value» «? » */,
				st._206 /* V00*steps-3..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._231 /* V01*steps-5..9 || «null» */,
				st._145 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._248 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._145 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._258 /* V03*TypedRefCS::ownedMultiplicity=56 || «null» */
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._10, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _082 = new SerializationRule(107,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._278 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : 11 */,
				ms._277 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : 74 */,
				ms._165 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._074 /* assign V0 = |CollectionPatternCS::restVariableName| */,
				ms._105 /* assign V1 = (|CollectionPatternCS::ownedParts| - 1) */,
				ms._005 /* assert (|CollectionPatternCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._164 /* 1*steps-1..12 || «null» */,
				st._074 /* 1*CollectionPatternCS::ownedType=11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._210 /* V00*steps-4..10 || «null» */,
				st._073 /* 1*CollectionPatternCS::ownedParts+=74 || «null» */,
				st._233 /* V01*steps-6..8 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._073 /* 1*CollectionPatternCS::ownedParts+=74 || «null» */,
				st._006 /* 1*'++' || «? » «value» «? » */,
				st._075 /* 1*CollectionPatternCS::restVariableName=35 || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._242 /* V02*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._09,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					iv._4) /* CollectionTypeCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					iv._28) /* PatternExpCS */
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
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(74, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(11, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _083 = new SerializationRule(107,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
				ms._165 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._075 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._006 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._115 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || «null» */,
				st._076 /* 1*CollectionTypeCS::name=12 || «? » «value» «? » */,
				st._208 /* V00*steps-3..7 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._077 /* 1*CollectionTypeCS::ownedType=108 || «null» */,
				st._220 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=56 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._242 /* V02*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._23,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._52) /* TypeExpWithoutMultiplicityCS */
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
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(108, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _084 = new SerializationRule(107,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
				ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
				ms._132 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._082 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._025 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._024 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._188 /* 1*steps-1..9 || «null» */,
				st._019 /* 1*'Map' || «? » «value» «? » */,
				st._209 /* V00*steps-3..8 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._098 /* 1*MapTypeCS::ownedKeyType=107 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._099 /* 1*MapTypeCS::ownedValueType=107 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._225 /* V01*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._25,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._51) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._09, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _085 = new SerializationRule(107,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._317 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._318 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30 */,
				ms._316 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._165 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._096 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._050 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._129 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._188 /* 1*steps-1..9 || «null» */,
				st._148 /* 1*TypeNameExpCS::ownedPathName=73 || «null» */,
				st._209 /* V00*steps-3..8 || «null» */,
				st._147 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=13 || «null» */,
				st._230 /* V01*steps-5..8 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._149 /* 1*TypeNameExpCS::ownedPatternGuard=30 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._242 /* V02*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._03,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					iv._9) /* ExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					iv._5) /* CurlyBracketedClauseCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralExpCS : ownedType=TypeLiteralWithMultiplicityCS
		private @NonNull SerializationRule _086 = new SerializationRule(111,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._315 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : 112 */,
				ms._049 /* assert (|TypeLiteralExpCS::ownedType| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._146 /* 1*TypeLiteralExpCS::ownedType=112 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					iv._54) /* TypeLiteralWithMultiplicityCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(112, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _087 = new SerializationRule(112,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._100 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._043 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._178 /* 1*steps-1..3 || «null» */,
				st._129 /* 1*PrimitiveTypeRefCS::name=82 || «? » «value» «? » */,
				st._204 /* V00*TypedRefCS::ownedMultiplicity=56 || «null» */
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
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _088 = new SerializationRule(112,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._181 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._048 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._067 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._110 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._153 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._163 /* 1*steps-1..11 || «null» */,
				st._020 /* 1*'Tuple' || «? » «value» «? » */,
				st._206 /* V00*steps-3..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._231 /* V01*steps-5..9 || «null» */,
				st._145 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._248 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._145 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._258 /* V03*TypedRefCS::ownedMultiplicity=56 || «null» */
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._10, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _089 = new SerializationRule(112,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
				ms._165 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._075 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._006 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._115 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || «null» */,
				st._076 /* 1*CollectionTypeCS::name=12 || «? » «value» «? » */,
				st._208 /* V00*steps-3..7 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._077 /* 1*CollectionTypeCS::ownedType=108 || «null» */,
				st._220 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=56 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._242 /* V02*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._23,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._52) /* TypeExpWithoutMultiplicityCS */
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
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(108, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeLiteralWithMultiplicityCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _090 = new SerializationRule(112,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
				ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
				ms._132 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._082 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._025 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._024 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._188 /* 1*steps-1..9 || «null» */,
				st._019 /* 1*'Map' || «? » «value» «? » */,
				st._209 /* V00*steps-3..8 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._098 /* 1*MapTypeCS::ownedKeyType=107 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._099 /* 1*MapTypeCS::ownedValueType=107 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._225 /* V01*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._25,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._51) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._09, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::TypeNameExpCS : { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] }
		private @NonNull SerializationRule _091 = new SerializationRule(113,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._317 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : 73 */,
				ms._318 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : 30 */,
				ms._316 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : 13 */,
				ms._096 /* assign V0 = |TypeNameExpCS::ownedCurlyBracketedClause| */,
				ms._050 /* assert (|TypeNameExpCS::ownedPathName| - 1) == 0 */,
				ms._129 /* assign V1 = |TypeNameExpCS::ownedPatternGuard| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || «null» */,
				st._148 /* 1*TypeNameExpCS::ownedPathName=73 || «null» */,
				st._209 /* V00*steps-3..8 || «null» */,
				st._147 /* 1*TypeNameExpCS::ownedCurlyBracketedClause=13 || «null» */,
				st._230 /* V01*steps-5..8 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._149 /* 1*TypeNameExpCS::ownedPatternGuard=30 || «null» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// EssentialOCL::URIFirstPathElementCS : referredElement=UnrestrictedName
		private @NonNull SerializationRule _092 = new SerializationRule(122,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._040 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._124 /* 1*PathElementCS::referredElement=UnrestrictedName || «? » «value» «? » */
			},
			sl._95,
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
		private @NonNull SerializationRule _093 = new SerializationRule(122,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._040 /* assert (|PathElementCS::referredElement| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._122 /* 1*PathElementCS::referredElement=URI || «? » «value» «? » */
			},
			sl._95,
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
		private @NonNull SerializationRule _094 = new SerializationRule(123,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._248 /* check-rule basecs::PathNameCS.ownedPathElements : 67|122 */,
				ms._061 /* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || «null» */,
				st._125 /* 1*PathNameCS::ownedPathElements+=122 || «null» */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._010 /* 1*'::' || «! » «value» «! » */,
				st._127 /* 1*PathNameCS::ownedPathElements+=67 || «null» */
			},
			sl._04,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					iv._64) /* NextPathElementCS|URIFirstPathElementCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(67, GrammarCardinality.ZERO_OR_MORE),
					new RuleIndex_GrammarCardinality(122, GrammarCardinality.ONE)
					}
				)
			});
		// EssentialOCL::UnlimitedNaturalLiteralExpCS : '*'
		private @NonNull SerializationRule _095 = new SerializationRule(125,
			new @NonNull SerializationMatchStep @NonNull [] {
			},
			new @NonNull SerializationStep @NonNull [] {
				st._005 /* 1*'*' || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			null,
			null,
			null,
			null);
		// OCLinEcore::AnnotationCS : { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
		private @NonNull SerializationRule _096 = new SerializationRule(1,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._085 /* assign V0 = |NamedElementCS::name| */,
				ms._103 /* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				ms._148 /* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._163 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._023 /* 1*'annotation' || «? » «value» «? » */,
				st._200 /* V00*NamedElementCS::name=127|87 || «? » «value» «? » */,
				st._226 /* V01*steps-4..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._248 /* V02*steps-7..9 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::AnnotationCS : { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' }
		private @NonNull SerializationRule _097 = new SerializationRule(1,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._230 /* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._229 /* check-rule basecs::AnnotationCS.ownedContents : 53 */,
				ms._196 /* assign V5 = |AnnotationCS::ownedReferences| */,
				ms._184 /* assign V4 = |AnnotationCS::ownedContents| */,
				ms._176 /* assign V3 = |ModelElementCS::ownedAnnotations| */,
				ms._085 /* assign V0 = |NamedElementCS::name| */,
				ms._103 /* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				ms._148 /* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._167 /* 1*steps-1..15 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._023 /* 1*'annotation' || «? » «value» «? » */,
				st._200 /* V00*NamedElementCS::name=127|87 || «? » «value» «? » */,
				st._226 /* V01*steps-4..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._248 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._256 /* V03*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._269 /* V04*AnnotationCS::ownedContents+=53 || «null» */,
				st._279 /* V05*AnnotationCS::ownedReferences+=54 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._71,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					iv._18) /* ModelElementRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					iv._17) /* ModelElementCS */
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
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(53, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(54, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// OCLinEcore::AnnotationCS : { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' }
		private @NonNull SerializationRule _098 = new SerializationRule(1,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._230 /* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._229 /* check-rule basecs::AnnotationCS.ownedContents : 53 */,
				ms._196 /* assign V5 = |AnnotationCS::ownedReferences| */,
				ms._184 /* assign V4 = |AnnotationCS::ownedContents| */,
				ms._176 /* assign V3 = |ModelElementCS::ownedAnnotations| */,
				ms._085 /* assign V0 = |NamedElementCS::name| */,
				ms._103 /* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				ms._148 /* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._167 /* 1*steps-1..15 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._023 /* 1*'annotation' || «? » «value» «? » */,
				st._200 /* V00*NamedElementCS::name=127|87 || «? » «value» «? » */,
				st._226 /* V01*steps-4..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._248 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._256 /* V03*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._269 /* V04*AnnotationCS::ownedContents+=53 || «null» */,
				st._279 /* V05*AnnotationCS::ownedReferences+=54 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._71,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					iv._18) /* ModelElementRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					iv._17) /* ModelElementCS */
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
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(53, GrammarCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(54, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::AnnotationCS : { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' }
		private @NonNull SerializationRule _099 = new SerializationRule(1,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._230 /* check-rule basecs::AnnotationCS.ownedReferences : 54 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._229 /* check-rule basecs::AnnotationCS.ownedContents : 53 */,
				ms._196 /* assign V5 = |AnnotationCS::ownedReferences| */,
				ms._184 /* assign V4 = |AnnotationCS::ownedContents| */,
				ms._176 /* assign V3 = |ModelElementCS::ownedAnnotations| */,
				ms._085 /* assign V0 = |NamedElementCS::name| */,
				ms._103 /* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				ms._148 /* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._167 /* 1*steps-1..15 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._023 /* 1*'annotation' || «? » «value» «? » */,
				st._200 /* V00*NamedElementCS::name=127|87 || «? » «value» «? » */,
				st._226 /* V01*steps-4..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._248 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._256 /* V03*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._269 /* V04*AnnotationCS::ownedContents+=53 || «null» */,
				st._279 /* V05*AnnotationCS::ownedReferences+=54 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._71,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					iv._18) /* ModelElementRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					iv._6) /* DetailCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					iv._17) /* ModelElementCS */
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
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ONE_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(53, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(54, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::AttributeCS : { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _100 = new SerializationRule(3,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._126 /* assign V1 = |StructuralFeatureCS::default| */,
				ms._097 /* assign V0 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._154 /* assign V2 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._180 /* assign V3 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._167 /* 1*steps-1..15 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._024 /* 1*'attribute' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._234 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._139 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._244 /* V02*steps-10..14 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._261 /* V03*steps-12..13 || «null» */,
				st._154 /* 1*TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._01, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::AttributeCS : { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
		private @NonNull SerializationRule _101 = new SerializationRule(3,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
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
			new @NonNull SerializationStep @NonNull [] {
				st._175 /* 1*steps-1..27 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._024 /* 1*'attribute' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._234 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._139 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._244 /* V02*steps-10..14 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._261 /* V03*steps-12..13 || «null» */,
				st._154 /* 1*TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._271 /* V04*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._285 /* V05*steps-17..21 || «null» */,
				st._039 /* 1*'initial' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._291 /* V06*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._298 /* V07*steps-22..26 || «null» */,
				st._029 /* 1*'derivation' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._304 /* V08*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._75,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._01)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._01, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::AttributeCS : { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _102 = new SerializationRule(3,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._162 /* assign V2 = |StructuralFeatureCS::default| */,
				ms._130 /* assign V1 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._099 /* assign V0 = |TypedElementCS::qualifiers.'static'| */,
				ms._052 /* assert (|TypedElementCS::qualifiers.'definition'| - 1) == 0 */,
				ms._171 /* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._192 /* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._169 /* 1*steps-1..17 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._028 /* 1*'definition' || «? » «value» «? » */,
				st._193 /* V00*'static' || «? » «value» «? » */,
				st._024 /* 1*'attribute' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._233 /* V01*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._253 /* V02*steps-9..11 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._139 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._263 /* V03*steps-12..16 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._274 /* V04*steps-14..15 || «null» */,
				st._154 /* 1*TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._93,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._01,ev._13,ev._21)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._01, GrammarCardinality.ZERO_OR_MORE),
						new EnumerationValue_GrammarCardinality(ev._13, GrammarCardinality.ONE),
						new EnumerationValue_GrammarCardinality(ev._21, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::AttributeCS : { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
		private @NonNull SerializationRule _103 = new SerializationRule(3,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
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
			new @NonNull SerializationStep @NonNull [] {
				st._177 /* 1*steps-1..29 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._028 /* 1*'definition' || «? » «value» «? » */,
				st._193 /* V00*'static' || «? » «value» «? » */,
				st._024 /* 1*'attribute' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._233 /* V01*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._253 /* V02*steps-9..11 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._139 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._263 /* V03*steps-12..16 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._274 /* V04*steps-14..15 || «null» */,
				st._154 /* 1*TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._282 /* V05*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._292 /* V06*steps-19..23 || «null» */,
				st._039 /* 1*'initial' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._297 /* V07*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._306 /* V08*steps-24..28 || «null» */,
				st._029 /* 1*'derivation' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._312 /* V09*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._94,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._01,ev._13,ev._21)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._01, GrammarCardinality.ZERO_OR_MORE),
						new EnumerationValue_GrammarCardinality(ev._13, GrammarCardinality.ONE),
						new EnumerationValue_GrammarCardinality(ev._21, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::AttributeCS : { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _104 = new SerializationRule(3,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._162 /* assign V2 = |StructuralFeatureCS::default| */,
				ms._130 /* assign V1 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._098 /* assign V0 = |TypedElementCS::qualifiers.'definition'| */,
				ms._053 /* assert (|TypedElementCS::qualifiers.'static'| - 1) == 0 */,
				ms._171 /* assign V3 = (|TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
				ms._192 /* assign V4 = |TypedElementCS::qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._169 /* 1*steps-1..17 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._056 /* 1*'static' || «? » «value» «? » */,
				st._191 /* V00*'definition' || «? » «value» «? » */,
				st._024 /* 1*'attribute' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._233 /* V01*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._253 /* V02*steps-9..11 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._139 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._263 /* V03*steps-12..16 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._274 /* V04*steps-14..15 || «null» */,
				st._154 /* 1*TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._93,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._01,ev._13,ev._21)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._01, GrammarCardinality.ZERO_OR_MORE),
						new EnumerationValue_GrammarCardinality(ev._13, GrammarCardinality.ZERO_OR_ONE),
						new EnumerationValue_GrammarCardinality(ev._21, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::AttributeCS : { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' }
		private @NonNull SerializationRule _105 = new SerializationRule(3,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
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
			new @NonNull SerializationStep @NonNull [] {
				st._177 /* 1*steps-1..29 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._056 /* 1*'static' || «? » «value» «? » */,
				st._191 /* V00*'definition' || «? » «value» «? » */,
				st._024 /* 1*'attribute' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._233 /* V01*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._253 /* V02*steps-9..11 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._139 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._263 /* V03*steps-12..16 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._274 /* V04*steps-14..15 || «null» */,
				st._154 /* 1*TypedElementCS::qualifiers+='!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._282 /* V05*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._292 /* V06*steps-19..23 || «null» */,
				st._039 /* 1*'initial' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._297 /* V07*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._306 /* V08*steps-24..28 || «null» */,
				st._029 /* 1*'derivation' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._312 /* V09*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._94,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._01,ev._13,ev._21)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._01, GrammarCardinality.ZERO_OR_MORE),
						new EnumerationValue_GrammarCardinality(ev._13, GrammarCardinality.ZERO_OR_ONE),
						new EnumerationValue_GrammarCardinality(ev._21, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
		private @NonNull SerializationRule _106 = new SerializationRule(15,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._157 /* assign V2 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._076 /* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._165 /* 1*steps-1..13 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._192 /* V00*'primitive' || «? » «value» «? » */,
				st._027 /* 1*'datatype' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._224 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._246 /* V02*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._267 /* V03*steps-9..12 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._19, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _107 = new SerializationRule(15,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._197 /* assign V5 = |ClassCS::ownedConstraints| */,
				ms._188 /* assign V4 = |ModelElementCS::ownedAnnotations| */,
				ms._157 /* assign V2 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._076 /* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._168 /* 1*steps-1..16 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._192 /* V00*'primitive' || «? » «value» «? » */,
				st._027 /* 1*'datatype' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._224 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._246 /* V02*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._267 /* V03*steps-9..12 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._000 /* 1*'!serializable' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._271 /* V04*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._280 /* V05*ClassCS::ownedConstraints+=41 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._88,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._19, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
		private @NonNull SerializationRule _108 = new SerializationRule(15,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._157 /* assign V2 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._076 /* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._164 /* 1*steps-1..12 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._192 /* V00*'primitive' || «? » «value» «? » */,
				st._027 /* 1*'datatype' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._224 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._246 /* V02*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._266 /* V03*steps-9..11 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._19, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _109 = new SerializationRule(15,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._197 /* assign V5 = |ClassCS::ownedConstraints| */,
				ms._188 /* assign V4 = |ModelElementCS::ownedAnnotations| */,
				ms._157 /* assign V2 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._076 /* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				ms._172 /* assign V3 = 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._167 /* 1*steps-1..15 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._192 /* V00*'primitive' || «? » «value» «? » */,
				st._027 /* 1*'datatype' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._224 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._246 /* V02*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._266 /* V03*steps-9..11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._271 /* V04*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._280 /* V05*ClassCS::ownedConstraints+=41 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._86,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					ev._19)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._19, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
		private @NonNull SerializationRule _110 = new SerializationRule(15,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._157 /* assign V2 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._076 /* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				ms._166 /* assign V3 = (|DataTypeCS::isSerializable.'serializable'| > 0) */,
				ms._186 /* assign V4 = |DataTypeCS::isSerializable.'serializable'| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._165 /* 1*steps-1..13 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._192 /* V00*'primitive' || «? » «value» «? » */,
				st._027 /* 1*'datatype' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._224 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._246 /* V02*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._267 /* V03*steps-9..12 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._268 /* V04*'serializable' || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._20, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._19, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::DataTypeCS : { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _111 = new SerializationRule(15,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._206 /* assign V6 = |ClassCS::ownedConstraints| */,
				ms._199 /* assign V5 = |ModelElementCS::ownedAnnotations| */,
				ms._157 /* assign V2 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._076 /* assign V0 = |DataTypeCS::isPrimitive.'primitive'| */,
				ms._166 /* assign V3 = (|DataTypeCS::isSerializable.'serializable'| > 0) */,
				ms._186 /* assign V4 = |DataTypeCS::isSerializable.'serializable'| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._168 /* 1*steps-1..16 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._192 /* V00*'primitive' || «? » «value» «? » */,
				st._027 /* 1*'datatype' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._224 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._246 /* V02*steps-6..8 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._267 /* V03*steps-9..12 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._268 /* V04*'serializable' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._282 /* V05*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._289 /* V06*ClassCS::ownedConstraints+=41 || «null» */,
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
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._20, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._19, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::DetailCS : { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] }
		private @NonNull SerializationRule _112 = new SerializationRule(16,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._077 /* assign V0 = |DetailCS::values| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._181 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._106 /* 1*NamedElementCS::name=127|87 || «? » «value» «? » */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._196 /* V00*DetailCS::values+=87|48 || «? » «value» «? » */
			},
			sl._81,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DETAIL_CS__VALUES,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// OCLinEcore::DocumentationCS : { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' }
		private @NonNull SerializationRule _113 = new SerializationRule(17,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._078 /* assign V0 = |DocumentationCS::value| */,
				ms._103 /* assign V1 = (|AnnotationElementCS::ownedDetails| > 0) */,
				ms._148 /* assign V2 = (|AnnotationElementCS::ownedDetails| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._163 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._030 /* 1*'documentation' || «? » «value» «? » */,
				st._197 /* V00*DocumentationCS::value=87 || «? » «value» «? » */,
				st._226 /* V01*steps-4..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._065 /* 1*AnnotationElementCS::ownedDetails+=16 || «null» */,
				st._248 /* V02*steps-7..9 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' }
		private @NonNull SerializationRule _114 = new SerializationRule(21,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._114 /* assign V1 = |ClassCS::instanceClassName| */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._164 /* 1*steps-1..12 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'enum' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._203 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._229 /* V01*steps-5..7 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._250 /* V02*steps-8..11 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _115 = new SerializationRule(21,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._197 /* assign V5 = |ClassCS::ownedConstraints| */,
				ms._187 /* assign V4 = |EnumerationCS::ownedLiterals| */,
				ms._176 /* assign V3 = |ModelElementCS::ownedAnnotations| */,
				ms._114 /* assign V1 = |ClassCS::instanceClassName| */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._168 /* 1*steps-1..16 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'enum' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._203 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._229 /* V01*steps-5..7 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._250 /* V02*steps-8..11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._000 /* 1*'!serializable' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._256 /* V03*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._270 /* V04*EnumerationCS::ownedLiterals+=22 || «null» */,
				st._280 /* V05*ClassCS::ownedConstraints+=41 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._69,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */
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
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(22, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' }
		private @NonNull SerializationRule _116 = new SerializationRule(21,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._114 /* assign V1 = |ClassCS::instanceClassName| */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._163 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'enum' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._203 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._229 /* V01*steps-5..7 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._249 /* V02*steps-8..10 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _117 = new SerializationRule(21,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._197 /* assign V5 = |ClassCS::ownedConstraints| */,
				ms._187 /* assign V4 = |EnumerationCS::ownedLiterals| */,
				ms._176 /* assign V3 = |ModelElementCS::ownedAnnotations| */,
				ms._114 /* assign V1 = |ClassCS::instanceClassName| */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._155 /* assign V2 = 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._167 /* 1*steps-1..15 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'enum' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._203 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._229 /* V01*steps-5..7 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._249 /* V02*steps-8..10 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._256 /* V03*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._270 /* V04*EnumerationCS::ownedLiterals+=22 || «null» */,
				st._280 /* V05*ClassCS::ownedConstraints+=41 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._67,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */
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
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(22, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' }
		private @NonNull SerializationRule _118 = new SerializationRule(21,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._114 /* assign V1 = |ClassCS::instanceClassName| */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._149 /* assign V2 = (|EnumerationCS::isSerializable.'serializable'| > 0) */,
				ms._175 /* assign V3 = |EnumerationCS::isSerializable.'serializable'| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._164 /* 1*steps-1..12 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'enum' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._203 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._229 /* V01*steps-5..7 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._250 /* V02*steps-8..11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._254 /* V03*'serializable' || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._20, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::EnumerationCS : { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _119 = new SerializationRule(21,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._235 /* check-rule basecs::EnumerationCS.ownedLiterals : 22 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._206 /* assign V6 = |ClassCS::ownedConstraints| */,
				ms._198 /* assign V5 = |EnumerationCS::ownedLiterals| */,
				ms._188 /* assign V4 = |ModelElementCS::ownedAnnotations| */,
				ms._114 /* assign V1 = |ClassCS::instanceClassName| */,
				ms._095 /* assign V0 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._149 /* assign V2 = (|EnumerationCS::isSerializable.'serializable'| > 0) */,
				ms._175 /* assign V3 = |EnumerationCS::isSerializable.'serializable'| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._168 /* 1*steps-1..16 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._034 /* 1*'enum' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._203 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._229 /* V01*steps-5..7 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._250 /* V02*steps-8..11 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._254 /* V03*'serializable' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._271 /* V04*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._281 /* V05*EnumerationCS::ownedLiterals+=22 || «null» */,
				st._289 /* V06*ClassCS::ownedConstraints+=41 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._69,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					ev._20)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					iv._8) /* EnumerationLiteralCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */
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
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._20, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(22, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::EnumerationLiteralCS : { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
		private @NonNull SerializationRule _120 = new SerializationRule(22,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._117 /* assign V1 = |EnumerationLiteralCS::value| */,
				ms._079 /* assign V0 = |EnumerationLiteralCS::literal| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._162 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._044 /* 1*'literal' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._081 /* 1*EnumerationLiteralCS::literal=87 || «? » «value» «? » */,
				st._234 /* V01*steps-7..9 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// OCLinEcore::EnumerationLiteralCS : { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
		private @NonNull SerializationRule _121 = new SerializationRule(22,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._160 /* assign V2 = |ModelElementCS::ownedAnnotations| */,
				ms._117 /* assign V1 = |EnumerationLiteralCS::value| */,
				ms._079 /* assign V0 = |EnumerationLiteralCS::literal| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._164 /* 1*steps-1..12 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._044 /* 1*'literal' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._081 /* 1*EnumerationLiteralCS::literal=87 || «? » «value» «? » */,
				st._234 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._082 /* 1*EnumerationLiteralCS::value=85 || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._240 /* V02*ModelElementCS::ownedAnnotations+=2 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::EnumerationLiteralCS : { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' }
		private @NonNull SerializationRule _122 = new SerializationRule(22,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._117 /* assign V1 = |EnumerationLiteralCS::value| */,
				ms._079 /* assign V0 = |EnumerationLiteralCS::literal| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._188 /* 1*steps-1..9 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._107 /* 1*NamedElementCS::name=23 || «? » «value» «? » */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._081 /* 1*EnumerationLiteralCS::literal=87 || «? » «value» «? » */,
				st._233 /* V01*steps-6..8 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// OCLinEcore::EnumerationLiteralCS : { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' }
		private @NonNull SerializationRule _123 = new SerializationRule(22,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._160 /* assign V2 = |ModelElementCS::ownedAnnotations| */,
				ms._117 /* assign V1 = |EnumerationLiteralCS::value| */,
				ms._079 /* assign V0 = |EnumerationLiteralCS::literal| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._163 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._107 /* 1*NamedElementCS::name=23 || «? » «value» «? » */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._081 /* 1*EnumerationLiteralCS::literal=87 || «? » «value» «? » */,
				st._233 /* V01*steps-6..8 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._082 /* 1*EnumerationLiteralCS::value=85 || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._240 /* V02*ModelElementCS::ownedAnnotations+=2 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::ImplicitOppositeCS : { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] }
		private @NonNull SerializationRule _124 = new SerializationRule(37,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._051 /* assert (|TypedElementCS::ownedType| - 1) == 0 */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._069 /* assign V0 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
				ms._131 /* assign V1 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._162 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._048 /* 1*'opposite' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._215 /* V00*steps-6..10 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._236 /* V01*steps-8..9 || «null» */,
				st._156 /* 1*TypedElementCS::qualifiers+='!ordered|!unique|ordered|unique' || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._03, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ONE)
					}
				)
			});
		// OCLinEcore::ImportCS : { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' }
		private @NonNull SerializationRule _125 = new SerializationRule(38,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._236 /* check-rule basecs::ImportCS.ownedPathName : 123 */,
				ms._118 /* assign V1 = |ImportCS::isAll.'::*'| */,
				ms._017 /* assert (|ImportCS::ownedPathName| - 1) == 0 */,
				ms._085 /* assign V0 = |NamedElementCS::name| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._037 /* 1*'import' || «null» */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._089 /* 1*ImportCS::ownedPathName=123 || «null» */,
				st._217 /* V01*'::*' || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.IMPORT_CS__IS_ALL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._06, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(123, GrammarCardinality.ONE)
					}
				)
			});
		// OCLinEcore::InvariantConstraintCS : { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
		private @NonNull SerializationRule _126 = new SerializationRule(41,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._234 /* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
				ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
				ms._174 /* assign V3 = |ConstraintCS::ownedSpecification| */,
				ms._120 /* assign V1 = |NamedElementCS::name| */,
				ms._007 /* assert (|ConstraintCS::stereotype.'invariant'| - 1) == 0 */,
				ms._089 /* assign V0 = |OCLinEcoreConstraintCS::isCallable.'callable'| */,
				ms._158 /* assign V2 = |ConstraintCS::ownedMessageSpecification| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._164 /* 1*steps-1..12 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._190 /* V00*'callable' || «? » «value» «? » */,
				st._041 /* 1*'invariant' || «? » «value» «? » */,
				st._228 /* V01*steps-4..9 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._247 /* V02*steps-6..9 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._078 /* 1*ConstraintCS::ownedMessageSpecification=92 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._009 /* 1*':' || «! » «value» «? » */,
				st._255 /* V03*ConstraintCS::ownedSpecification=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._76,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					ev._12),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._16)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._38) /* SpecificationCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._12, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._16, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::InvariantConstraintCS : { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' }
		private @NonNull SerializationRule _127 = new SerializationRule(41,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
				ms._120 /* assign V1 = |NamedElementCS::name| */,
				ms._007 /* assert (|ConstraintCS::stereotype.'invariant'| - 1) == 0 */,
				ms._089 /* assign V0 = |OCLinEcoreConstraintCS::isCallable.'callable'| */,
				ms._158 /* assign V2 = |ConstraintCS::ownedMessageSpecification| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._162 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._190 /* V00*'callable' || «? » «value» «? » */,
				st._041 /* 1*'invariant' || «? » «value» «? » */,
				st._228 /* V01*steps-4..9 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._247 /* V02*steps-6..9 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._078 /* 1*ConstraintCS::ownedMessageSpecification=92 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._77,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					ev._12),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					ev._16)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					iv._38) /* SpecificationCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._12, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._16, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
	}
	private class _SerializationRules2
	{
		// OCLinEcore::ModelElementRefCS : { 'reference' ownedPathName=PathNameCS ';' }
		private @NonNull SerializationRule _128 = new SerializationRule(54,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._238 /* check-rule basecs::ModelElementRefCS.ownedPathName : 73 */,
				ms._026 /* assert (|ModelElementRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._181 /* 1*steps-1..4 || «null» */,
				st._054 /* 1*'reference' || «? » «value» «? » */,
				st._100 /* 1*ModelElementRefCS::ownedPathName=73 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._27,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
					}
				)
			});
		// OCLinEcore::OperationCS : { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _129 = new SerializationRule(70,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
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
			new @NonNull SerializationStep @NonNull [] {
				st._174 /* 1*steps-1..26 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._047 /* 1*'operation' || «? » «value» «? » */,
				st._203 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._232 /* V01*steps-6..10 || «null» */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._249 /* V02*steps-8..10 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._262 /* V03*steps-12..14 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._278 /* V04*steps-15..20 || «null» */,
				st._059 /* 1*'throws' || «? » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._287 /* V05*steps-18..20 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._295 /* V06*steps-21..25 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._300 /* V07*steps-23..24 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._58,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._26) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._61) /* TypedRefCS */
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
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._02, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(72, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::OperationCS : { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
		private @NonNull SerializationRule _130 = new SerializationRule(70,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
				ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
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
			new @NonNull SerializationStep @NonNull [] {
				st._179 /* 1*steps-1..35 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._047 /* 1*'operation' || «? » «value» «? » */,
				st._203 /* V00*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._232 /* V01*steps-6..10 || «null» */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._249 /* V02*steps-8..10 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._262 /* V03*steps-12..14 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._278 /* V04*steps-15..20 || «null» */,
				st._059 /* 1*'throws' || «? » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._287 /* V05*steps-18..20 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._295 /* V06*steps-21..25 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._300 /* V07*steps-23..24 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._303 /* V08*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._311 /* V09*OperationCS::ownedPreconditions+=76 || «null» */,
				st._318 /* V10*steps-29..33 || «null» */,
				st._025 /* 1*'body' || «? » «value» «? » */,
				st._009 /* 1*':' || «! » «value» «? » */,
				st._320 /* V11*OperationCS::ownedBodyExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._325 /* V12*OperationCS::ownedPostconditions+=75 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._59,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._31) /* PreconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._26) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._30) /* PostconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._61) /* TypedRefCS */
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
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._02, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(72, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(75, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(76, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::OperationCS : { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _131 = new SerializationRule(70,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
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
			new @NonNull SerializationStep @NonNull [] {
				st._176 /* 1*steps-1..28 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._028 /* 1*'definition' || «? » «value» «? » */,
				st._193 /* V00*'static' || «? » «value» «? » */,
				st._047 /* 1*'operation' || «? » «value» «? » */,
				st._224 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._251 /* V02*steps-8..12 || «null» */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._259 /* V03*steps-10..12 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._275 /* V04*steps-14..16 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._286 /* V05*steps-17..22 || «null» */,
				st._059 /* 1*'throws' || «? » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._293 /* V06*steps-20..22 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._302 /* V07*steps-23..27 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._307 /* V08*steps-25..26 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._89,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02,ev._13,ev._21)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._26) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._61) /* TypedRefCS */
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
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._02, GrammarCardinality.ZERO_OR_MORE),
						new EnumerationValue_GrammarCardinality(ev._13, GrammarCardinality.ONE),
						new EnumerationValue_GrammarCardinality(ev._21, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(72, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::OperationCS : { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
		private @NonNull SerializationRule _132 = new SerializationRule(70,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
				ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
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
			new @NonNull SerializationStep @NonNull [] {
				st._180 /* 1*steps-1..37 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._028 /* 1*'definition' || «? » «value» «? » */,
				st._193 /* V00*'static' || «? » «value» «? » */,
				st._047 /* 1*'operation' || «? » «value» «? » */,
				st._224 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._251 /* V02*steps-8..12 || «null» */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._259 /* V03*steps-10..12 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._275 /* V04*steps-14..16 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._286 /* V05*steps-17..22 || «null» */,
				st._059 /* 1*'throws' || «? » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._293 /* V06*steps-20..22 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._302 /* V07*steps-23..27 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._307 /* V08*steps-25..26 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._310 /* V09*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._316 /* V10*OperationCS::ownedPreconditions+=76 || «null» */,
				st._322 /* V11*steps-31..35 || «null» */,
				st._025 /* 1*'body' || «? » «value» «? » */,
				st._009 /* 1*':' || «! » «value» «? » */,
				st._324 /* V12*OperationCS::ownedBodyExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._328 /* V13*OperationCS::ownedPostconditions+=75 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._90,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02,ev._13,ev._21)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._31) /* PreconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._26) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._30) /* PostconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._61) /* TypedRefCS */
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
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._02, GrammarCardinality.ZERO_OR_MORE),
						new EnumerationValue_GrammarCardinality(ev._13, GrammarCardinality.ONE),
						new EnumerationValue_GrammarCardinality(ev._21, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(72, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(75, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(76, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::OperationCS : { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _133 = new SerializationRule(70,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
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
			new @NonNull SerializationStep @NonNull [] {
				st._176 /* 1*steps-1..28 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._056 /* 1*'static' || «? » «value» «? » */,
				st._191 /* V00*'definition' || «? » «value» «? » */,
				st._047 /* 1*'operation' || «? » «value» «? » */,
				st._224 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._251 /* V02*steps-8..12 || «null» */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._259 /* V03*steps-10..12 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._275 /* V04*steps-14..16 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._286 /* V05*steps-17..22 || «null» */,
				st._059 /* 1*'throws' || «? » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._293 /* V06*steps-20..22 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._302 /* V07*steps-23..27 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._307 /* V08*steps-25..26 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._89,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02,ev._13,ev._21)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._26) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._61) /* TypedRefCS */
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
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._02, GrammarCardinality.ZERO_OR_MORE),
						new EnumerationValue_GrammarCardinality(ev._13, GrammarCardinality.ZERO_OR_ONE),
						new EnumerationValue_GrammarCardinality(ev._21, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(72, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::OperationCS : { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' }
		private @NonNull SerializationRule _134 = new SerializationRule(70,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._239 /* check-rule basecs::OperationCS.ownedBodyExpressions : 92 */,
				ms._243 /* check-rule basecs::OperationCS.ownedPreconditions : 76 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._241 /* check-rule basecs::OperationCS.ownedParameters : 72 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._242 /* check-rule basecs::OperationCS.ownedPostconditions : 75 */,
				ms._240 /* check-rule basecs::OperationCS.ownedExceptions : 117 */,
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
			new @NonNull SerializationStep @NonNull [] {
				st._180 /* 1*steps-1..37 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._056 /* 1*'static' || «? » «value» «? » */,
				st._191 /* V00*'definition' || «? » «value» «? » */,
				st._047 /* 1*'operation' || «? » «value» «? » */,
				st._224 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._251 /* V02*steps-8..12 || «null» */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._259 /* V03*steps-10..12 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._116 /* 1*OperationCS::ownedParameters+=72 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._275 /* V04*steps-14..16 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._286 /* V05*steps-17..22 || «null» */,
				st._059 /* 1*'throws' || «? » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._293 /* V06*steps-20..22 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._115 /* 1*OperationCS::ownedExceptions+=117 || «null» */,
				st._302 /* V07*steps-23..27 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._307 /* V08*steps-25..26 || «null» */,
				st._155 /* 1*TypedElementCS::qualifiers+='!derived|!ordered|!transient|!unique|derived|ordered|transient|unique' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._310 /* V09*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._316 /* V10*OperationCS::ownedPreconditions+=76 || «null» */,
				st._322 /* V11*steps-31..35 || «null» */,
				st._025 /* 1*'body' || «? » «value» «? » */,
				st._009 /* 1*':' || «! » «value» «? » */,
				st._324 /* V12*OperationCS::ownedBodyExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._328 /* V13*OperationCS::ownedPostconditions+=75 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._90,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._02,ev._13,ev._21)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					iv._31) /* PreconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					iv._26) /* ParameterCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					iv._30) /* PostconditionConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					iv._61) /* TypedRefCS */
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
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._02, GrammarCardinality.ZERO_OR_MORE),
						new EnumerationValue_GrammarCardinality(ev._13, GrammarCardinality.ZERO_OR_ONE),
						new EnumerationValue_GrammarCardinality(ev._21, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(72, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(75, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(76, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::PackageCS : { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' }
		private @NonNull SerializationRule _135 = new SerializationRule(71,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._123 /* assign V1 = |PackageCS::nsURI| */,
				ms._090 /* assign V0 = |PackageCS::nsPrefix| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._162 /* 1*steps-1..10 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._049 /* 1*'package' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._120 /* 1*PackageCS::nsPrefix=127 || «? » «value» «? » */,
				st._234 /* V01*steps-7..9 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_CS__NS_URI,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// OCLinEcore::PackageCS : { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' }
		private @NonNull SerializationRule _136 = new SerializationRule(71,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._245 /* check-rule basecs::PackageOwnerCS.ownedPackages : 71 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._244 /* check-rule basecs::PackageCS.ownedClasses : 6 */,
				ms._189 /* assign V4 = |PackageCS::ownedClasses| */,
				ms._177 /* assign V3 = |PackageOwnerCS::ownedPackages| */,
				ms._160 /* assign V2 = |ModelElementCS::ownedAnnotations| */,
				ms._123 /* assign V1 = |PackageCS::nsURI| */,
				ms._090 /* assign V0 = |PackageCS::nsPrefix| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._166 /* 1*steps-1..14 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._049 /* 1*'package' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._120 /* 1*PackageCS::nsPrefix=127 || «? » «value» «? » */,
				st._234 /* V01*steps-7..9 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._121 /* 1*PackageCS::nsURI=121 || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._240 /* V02*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._257 /* V03*PackageOwnerCS::ownedPackages+=71 || «null» */,
				st._272 /* V04*PackageCS::ownedClasses+=6 || «½\n» «value» «½\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._79,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					iv._25) /* PackageCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					iv._1) /* ClassCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_CS__NS_URI,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(6, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(71, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::ParameterCS : { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] }
		private @NonNull SerializationRule _137 = new SerializationRule(72,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._097 /* assign V0 = |TypedElementCS::ownedType| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._167 /* assign V3 = (|ModelElementCS::ownedAnnotations| > 0) */,
				ms._188 /* assign V4 = |ModelElementCS::ownedAnnotations| */,
				ms._112 /* assign V1 = (|TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
				ms._164 /* assign V2 = |TypedElementCS::qualifiers.'!ordered|!unique|ordered|unique'| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._166 /* 1*steps-1..14 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._207 /* V00*steps-3..5 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._232 /* V01*steps-6..10 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._252 /* V02*steps-8..9 || «null» */,
				st._156 /* 1*TypedElementCS::qualifiers+='!ordered|!unique|ordered|unique' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._260 /* V03*steps-11..14 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._271 /* V04*ModelElementCS::ownedAnnotations+=2 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._03, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::PostconditionConstraintCS : { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
		private @NonNull SerializationRule _138 = new SerializationRule(75,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._234 /* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
				ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
				ms._159 /* assign V2 = |ConstraintCS::ownedSpecification| */,
				ms._085 /* assign V0 = |NamedElementCS::name| */,
				ms._008 /* assert (|ConstraintCS::stereotype.'postcondition'| - 1) == 0 */,
				ms._116 /* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._163 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._050 /* 1*'postcondition' || «? » «value» «? » */,
				st._209 /* V00*steps-3..8 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._230 /* V01*steps-5..8 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._078 /* 1*ConstraintCS::ownedMessageSpecification=92 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._009 /* 1*':' || «! » «value» «? » */,
				st._239 /* V02*ConstraintCS::ownedSpecification=92 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._17, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::PreconditionConstraintCS : { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' }
		private @NonNull SerializationRule _139 = new SerializationRule(76,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._234 /* check-rule basecs::ConstraintCS.ownedSpecification : 92 */,
				ms._233 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : 92 */,
				ms._159 /* assign V2 = |ConstraintCS::ownedSpecification| */,
				ms._085 /* assign V0 = |NamedElementCS::name| */,
				ms._009 /* assert (|ConstraintCS::stereotype.'precondition'| - 1) == 0 */,
				ms._116 /* assign V1 = |ConstraintCS::ownedMessageSpecification| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._163 /* 1*steps-1..11 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._052 /* 1*'precondition' || «? » «value» «? » */,
				st._209 /* V00*steps-3..8 || «null» */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._230 /* V01*steps-5..8 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._078 /* 1*ConstraintCS::ownedMessageSpecification=92 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._009 /* 1*':' || «! » «value» «? » */,
				st._239 /* V02*ConstraintCS::ownedSpecification=92 || «null» */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._18, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::ReferenceCS : { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _140 = new SerializationRule(83,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
				ms._162 /* assign V2 = |StructuralFeatureCS::default| */,
				ms._130 /* assign V1 = |TypedElementCS::ownedType| */,
				ms._092 /* assign V0 = |ReferenceCS::referredOpposite| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._170 /* assign V3 = (|TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
				ms._191 /* assign V4 = |TypedElementCS::qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._170 /* 1*steps-1..18 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._053 /* 1*'property' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._001 /* 1*'#' || «! » «value» «! » */,
				st._132 /* 1*ReferenceCS::referredOpposite=UnrestrictedName || «? » «value» «? » */,
				st._234 /* V01*steps-7..9 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._243 /* V02*steps-10..12 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._139 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._264 /* V03*steps-13..17 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._276 /* V04*steps-15..16 || «null» */,
				st._153 /* 1*TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._00, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				)
			});
		// OCLinEcore::ReferenceCS : { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
		private @NonNull SerializationRule _141 = new SerializationRule(83,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
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
			new @NonNull SerializationStep @NonNull [] {
				st._182 /* 1*steps-1..40 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._053 /* 1*'property' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._001 /* 1*'#' || «! » «value» «! » */,
				st._132 /* 1*ReferenceCS::referredOpposite=UnrestrictedName || «? » «value» «? » */,
				st._234 /* V01*steps-7..9 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._243 /* V02*steps-10..12 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._139 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._264 /* V03*steps-13..17 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._276 /* V04*steps-15..16 || «null» */,
				st._153 /* 1*TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._282 /* V05*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._294 /* V06*steps-20..26 || «null» */,
				st._042 /* 1*'key' || «? » «value» «? » */,
				st._131 /* 1*ReferenceCS::referredKeys+=UnrestrictedName || «? » «value» «? » */,
				st._301 /* V07*steps-23..25 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._131 /* 1*ReferenceCS::referredKeys+=UnrestrictedName || «? » «value» «? » */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._309 /* V08*steps-27..31 || «null» */,
				st._039 /* 1*'initial' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._312 /* V09*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._319 /* V10*steps-32..36 || «null» */,
				st._029 /* 1*'derivation' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._321 /* V11*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._327 /* V12*steps-37..39 || «null» */,
				st._130 /* 1*ReferenceCS::ownedImplicitOpposites+=37 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._73,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._00)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					iv._11) /* ImplicitOppositeCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._00, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(37, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				)
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _142 = new SerializationRule(83,
			new @NonNull SerializationMatchStep @NonNull [] {
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
			new @NonNull SerializationStep @NonNull [] {
				st._172 /* 1*steps-1..20 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._028 /* 1*'definition' || «? » «value» «? » */,
				st._193 /* V00*'static' || «? » «value» «? » */,
				st._053 /* 1*'property' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._233 /* V01*steps-6..8 || «null» */,
				st._001 /* 1*'#' || «! » «value» «! » */,
				st._132 /* 1*ReferenceCS::referredOpposite=UnrestrictedName || «? » «value» «? » */,
				st._253 /* V02*steps-9..11 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._262 /* V03*steps-12..14 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._139 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._277 /* V04*steps-15..19 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._284 /* V05*steps-17..18 || «null» */,
				st._153 /* 1*TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._91,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._00,ev._13,ev._21)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._00, GrammarCardinality.ZERO_OR_MORE),
						new EnumerationValue_GrammarCardinality(ev._13, GrammarCardinality.ONE),
						new EnumerationValue_GrammarCardinality(ev._21, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				)
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
		private @NonNull SerializationRule _143 = new SerializationRule(83,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
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
			new @NonNull SerializationStep @NonNull [] {
				st._183 /* 1*steps-1..42 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._028 /* 1*'definition' || «? » «value» «? » */,
				st._193 /* V00*'static' || «? » «value» «? » */,
				st._053 /* 1*'property' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._233 /* V01*steps-6..8 || «null» */,
				st._001 /* 1*'#' || «! » «value» «! » */,
				st._132 /* 1*ReferenceCS::referredOpposite=UnrestrictedName || «? » «value» «? » */,
				st._253 /* V02*steps-9..11 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._262 /* V03*steps-12..14 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._139 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._277 /* V04*steps-15..19 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._284 /* V05*steps-17..18 || «null» */,
				st._153 /* 1*TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._290 /* V06*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._299 /* V07*steps-22..28 || «null» */,
				st._042 /* 1*'key' || «? » «value» «? » */,
				st._131 /* 1*ReferenceCS::referredKeys+=UnrestrictedName || «? » «value» «? » */,
				st._308 /* V08*steps-25..27 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._131 /* 1*ReferenceCS::referredKeys+=UnrestrictedName || «? » «value» «? » */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._314 /* V09*steps-29..33 || «null» */,
				st._039 /* 1*'initial' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._317 /* V10*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._323 /* V11*steps-34..38 || «null» */,
				st._029 /* 1*'derivation' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._326 /* V12*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._329 /* V13*steps-39..41 || «null» */,
				st._130 /* 1*ReferenceCS::ownedImplicitOpposites+=37 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._92,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._00,ev._13,ev._21)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					iv._11) /* ImplicitOppositeCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._00, GrammarCardinality.ZERO_OR_MORE),
						new EnumerationValue_GrammarCardinality(ev._13, GrammarCardinality.ONE),
						new EnumerationValue_GrammarCardinality(ev._21, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(37, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				)
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' }
		private @NonNull SerializationRule _144 = new SerializationRule(83,
			new @NonNull SerializationMatchStep @NonNull [] {
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
			new @NonNull SerializationStep @NonNull [] {
				st._172 /* 1*steps-1..20 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._056 /* 1*'static' || «? » «value» «? » */,
				st._191 /* V00*'definition' || «? » «value» «? » */,
				st._053 /* 1*'property' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._233 /* V01*steps-6..8 || «null» */,
				st._001 /* 1*'#' || «! » «value» «! » */,
				st._132 /* 1*ReferenceCS::referredOpposite=UnrestrictedName || «? » «value» «? » */,
				st._253 /* V02*steps-9..11 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._262 /* V03*steps-12..14 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._139 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._277 /* V04*steps-15..19 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._284 /* V05*steps-17..18 || «null» */,
				st._153 /* 1*TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._91,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._00,ev._13,ev._21)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._00, GrammarCardinality.ZERO_OR_MORE),
						new EnumerationValue_GrammarCardinality(ev._13, GrammarCardinality.ZERO_OR_ONE),
						new EnumerationValue_GrammarCardinality(ev._21, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				)
			});
		// OCLinEcore::ReferenceCS : { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' }
		private @NonNull SerializationRule _145 = new SerializationRule(83,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._251 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : 92 */,
				ms._249 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : 37 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._263 /* check-rule basecs::TypedElementCS.ownedType : 116 */,
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
			new @NonNull SerializationStep @NonNull [] {
				st._183 /* 1*steps-1..42 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._056 /* 1*'static' || «? » «value» «? » */,
				st._191 /* V00*'definition' || «? » «value» «? » */,
				st._053 /* 1*'property' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._233 /* V01*steps-6..8 || «null» */,
				st._001 /* 1*'#' || «! » «value» «! » */,
				st._132 /* 1*ReferenceCS::referredOpposite=UnrestrictedName || «? » «value» «? » */,
				st._253 /* V02*steps-9..11 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._152 /* 1*TypedElementCS::ownedType=116 || «null» */,
				st._262 /* V03*steps-12..14 || «null» */,
				st._014 /* 1*'=' || «? » «value» «? » */,
				st._139 /* 1*StructuralFeatureCS::default=87 || «? » «value» «? » */,
				st._277 /* V04*steps-15..19 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._284 /* V05*steps-17..18 || «null» */,
				st._153 /* 1*TypedElementCS::qualifiers+='!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._290 /* V06*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._299 /* V07*steps-22..28 || «null» */,
				st._042 /* 1*'key' || «? » «value» «? » */,
				st._131 /* 1*ReferenceCS::referredKeys+=UnrestrictedName || «? » «value» «? » */,
				st._308 /* V08*steps-25..27 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._131 /* 1*ReferenceCS::referredKeys+=UnrestrictedName || «? » «value» «? » */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._314 /* V09*steps-29..33 || «null» */,
				st._039 /* 1*'initial' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._317 /* V10*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._323 /* V11*steps-34..38 || «null» */,
				st._029 /* 1*'derivation' || «? » «value» «? » */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._326 /* V12*StructuralFeatureCS::ownedDefaultExpressions+=92 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._329 /* V13*steps-39..41 || «null» */,
				st._130 /* 1*ReferenceCS::ownedImplicitOpposites+=37 || «null» */,
				st._011 /* 1*';' || «! » «value» «?\n» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._92,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					ev._00,ev._13,ev._21)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					iv._38) /* SpecificationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					iv._11) /* ImplicitOppositeCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					iv._60) /* TypedMultiplicityRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._00, GrammarCardinality.ZERO_OR_MORE),
						new EnumerationValue_GrammarCardinality(ev._13, GrammarCardinality.ZERO_OR_ONE),
						new EnumerationValue_GrammarCardinality(ev._21, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(92, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(37, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(116, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				)
			});
		// OCLinEcore::SpecificationCS : exprString=UNQUOTED_STRING
		private @NonNull SerializationRule _146 = new SerializationRule(92,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._046 /* assert (|SpecificationCS::exprString| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._137 /* 1*SpecificationCS::exprString=119 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
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
		// OCLinEcore::SpecificationCS : ownedExpression=ExpCS
		private @NonNull SerializationRule _147 = new SerializationRule(92,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._283 /* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : 30 */,
				ms._011 /* assert (|ExpSpecificationCS::ownedExpression| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._083 /* 1*ExpSpecificationCS::ownedExpression=30 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */
			},
			sl._29,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					iv._9) /* ExpCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
					}
				)
			});
		// OCLinEcore::StructuredClassCS : { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' }
		private @NonNull SerializationRule _148 = new SerializationRule(97,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._254 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
				ms._185 /* assign V4 = |ClassCS::instanceClassName| */,
				ms._128 /* assign V1 = |TemplateableElementCS::ownedSignature| */,
				ms._029 /* assert (|NamedElementCS::name| - 1) == 0 */,
				ms._094 /* assign V0 = |StructuredClassCS::isAbstract.'abstract'| */,
				ms._195 /* assign V5 = (|StructuredClassCS::isInterface.'interface'| > 0) */,
				ms._209 /* assign V6 = |StructuredClassCS::isInterface.'interface'| */,
				ms._152 /* assign V2 = (|StructuredClassCS::ownedSuperTypes| > 0) */,
				ms._169 /* assign V3 = (|StructuredClassCS::ownedSuperTypes| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._171 /* 1*steps-1..19 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._189 /* V00*'abstract' || «? » «value» «? » */,
				st._026 /* 1*'class' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._224 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._245 /* V02*steps-6..11 || «null» */,
				st._035 /* 1*'extends' || «? » «value» «? » */,
				st._140 /* 1*StructuredClassCS::ownedSuperTypes+=117 || «null» */,
				st._266 /* V03*steps-9..11 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._140 /* 1*StructuredClassCS::ownedSuperTypes+=117 || «null» */,
				st._273 /* V04*steps-12..14 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._283 /* V05*steps-15..18 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._288 /* V06*'interface' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._011 /* 1*';' || «! » «value» «?\n» */
			},
			sl._83,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					ev._11),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					ev._15)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					iv._61) /* TypedRefCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._11, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._15, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::StructuredClassCS : { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' }
		private @NonNull SerializationRule _149 = new SerializationRule(97,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._259 /* check-rule basecs::TemplateableElementCS.ownedSignature : 101 */,
				ms._237 /* check-rule basecs::ModelElementCS.ownedAnnotations : 2 */,
				ms._252 /* check-rule basecs::StructuredClassCS.ownedOperations : 70 */,
				ms._254 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : 117 */,
				ms._232 /* check-rule basecs::ClassCS.ownedConstraints : 41 */,
				ms._253 /* check-rule basecs::StructuredClassCS.ownedProperties : 96 */,
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
			new @NonNull SerializationStep @NonNull [] {
				st._173 /* 1*steps-1..24 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._189 /* V00*'abstract' || «? » «value» «? » */,
				st._026 /* 1*'class' || «? » «value» «? » */,
				st._105 /* 1*NamedElementCS::name=127 || «? » «value» «? » */,
				st._224 /* V01*TemplateableElementCS::ownedSignature=101 || «null» */,
				st._245 /* V02*steps-6..11 || «null» */,
				st._035 /* 1*'extends' || «? » «value» «? » */,
				st._140 /* 1*StructuredClassCS::ownedSuperTypes+=117 || «null» */,
				st._266 /* V03*steps-9..11 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._140 /* 1*StructuredClassCS::ownedSuperTypes+=117 || «null» */,
				st._273 /* V04*steps-12..14 || «null» */,
				st._009 /* 1*':' || «? » «value» «? » */,
				st._067 /* 1*ClassCS::instanceClassName=87 || «? » «value» «? » */,
				st._283 /* V05*steps-15..18 || «null» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._288 /* V06*'interface' || «? » «value» «? » */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._296 /* V07*ModelElementCS::ownedAnnotations+=2 || «null» */,
				st._305 /* V08*StructuredClassCS::ownedOperations+=70 || «null» */,
				st._313 /* V09*StructuredClassCS::ownedProperties+=96 || «null» */,
				st._315 /* V10*ClassCS::ownedConstraints+=41 || «null» */,
				st._063 /* 1*'}' || «-» «? » «value» «?\n» */
			},
			sl._84,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					ev._11),
				new EAttribute_EnumerationValues(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					ev._15)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					iv._48) /* TemplateSignatureCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					iv._0) /* AnnotationElementCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					iv._24) /* OperationCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					iv._61) /* TypedRefCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					iv._13) /* InvariantConstraintCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					iv._41) /* StructuralFeatureCS */
			},
			new /*@NonNull*/ EAttribute [] {
				BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._11, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._15, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(2, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(41, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(70, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(96, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(101, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(117, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::SysMLCS : { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' }
		private @NonNull SerializationRule _150 = new SerializationRule(98,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._072 /* assign V0 = |AnnotationElementCS::ownedDetails| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._186 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._057 /* 1*'sysml' || «? » «value» «? » */,
				st._060 /* 1*'{' || «? » «value» «+» «?\n» */,
				st._211 /* V00*steps-4..6 || «null» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::SysMLCS : { 'sysml' ownedDetails+=DetailCS ';' }
		private @NonNull SerializationRule _151 = new SerializationRule(98,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._231 /* check-rule basecs::AnnotationElementCS.ownedDetails : 16 */,
				ms._001 /* assert (|AnnotationElementCS::ownedDetails| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._181 /* 1*steps-1..4 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(16, GrammarCardinality.ONE)
					}
				)
			});
		// OCLinEcore::TemplateSignatureCS : { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' }
		private @NonNull SerializationRule _152 = new SerializationRule(101,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._258 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 114 */,
				ms._065 /* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._186 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._143 /* 1*TemplateSignatureCS::ownedParameters+=114 || «null» */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._143 /* 1*TemplateSignatureCS::ownedParameters+=114 || «null» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(114, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// OCLinEcore::TemplateSignatureCS : { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' }
		private @NonNull SerializationRule _153 = new SerializationRule(101,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._258 /* check-rule basecs::TemplateSignatureCS.ownedParameters : 114 */,
				ms._065 /* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._186 /* 1*steps-1..7 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._012 /* 1*'<' || «? » «value» «? » */,
				st._143 /* 1*TemplateSignatureCS::ownedParameters+=114 || «null» */,
				st._211 /* V00*steps-4..6 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._143 /* 1*TemplateSignatureCS::ownedParameters+=114 || «null» */,
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(114, GrammarCardinality.ONE_OR_MORE)
					}
				)
			});
		// OCLinEcore::TopLevelCS : { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] }
		private @NonNull SerializationRule _154 = new SerializationRule(102,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._245 /* check-rule basecs::PackageOwnerCS.ownedPackages : 71 */,
				ms._250 /* check-rule basecs::RootCS.ownedImports : 38 */,
				ms._161 /* assign V2 = |PackageOwnerCS::ownedPackages| */,
				ms._125 /* assign V1 = |RootCS::ownedImports| */,
				ms._070 /* assign V0 = 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport «value» */,
				st._205 /* V00*steps-2..3 || «null» */,
				st._045 /* 1*'module' || «? » «value» «? » */,
				st._221 /* V01*RootCS::ownedImports+=38 || «null» */,
				st._241 /* V02*PackageOwnerCS::ownedPackages+=71 || «null» */
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(38, GrammarCardinality.ZERO_OR_MORE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(71, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _155 = new SerializationRule(116,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._100 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._043 /* assert (|PrimitiveTypeRefCS::name| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._178 /* 1*steps-1..3 || «null» */,
				st._129 /* 1*PrimitiveTypeRefCS::name=82 || «? » «value» «? » */,
				st._204 /* V00*TypedRefCS::ownedMultiplicity=56 || «null» */
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
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _156 = new SerializationRule(116,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._260 /* check-rule basecs::TupleTypeCS.ownedParts : 105 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._181 /* assign V3 = |TypedRefCS::ownedMultiplicity| */,
				ms._048 /* assert (|TupleTypeCS::name.'Tuple'| - 1) == 0 */,
				ms._067 /* assign V0 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._110 /* assign V1 = (|TupleTypeCS::ownedParts| > 0) */,
				ms._153 /* assign V2 = (|TupleTypeCS::ownedParts| - 1) */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._163 /* 1*steps-1..11 || «null» */,
				st._020 /* 1*'Tuple' || «? » «value» «? » */,
				st._206 /* V00*steps-3..10 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._231 /* V01*steps-5..9 || «null» */,
				st._145 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._248 /* V02*steps-7..9 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._145 /* 1*TupleTypeCS::ownedParts+=105 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._258 /* V03*TypedRefCS::ownedMultiplicity=56 || «null» */
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._10, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(105, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _157 = new SerializationRule(116,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._100 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._054 /* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
				ms._055 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._185 /* 1*steps-1..6 || «null» */,
				st._158 /* 1*TypedTypeRefCS::ownedPathName=73 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._157 /* 1*TypedTypeRefCS::ownedBinding=99 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._204 /* V00*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._07,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._46) /* TemplateBindingCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(99, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _158 = new SerializationRule(116,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._100 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._054 /* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
				ms._055 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._185 /* 1*steps-1..6 || «null» */,
				st._158 /* 1*TypedTypeRefCS::ownedPathName=73 || «null» */,
				st._012 /* 1*'<' || «? » «value» «? » */,
				st._157 /* 1*TypedTypeRefCS::ownedBinding=99 || «null» */,
				st._015 /* 1*'>' || «? » «value» «? » */,
				st._204 /* V00*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._11,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._46) /* TemplateBindingCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(99, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _159 = new SerializationRule(116,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._100 /* assign V0 = |TypedRefCS::ownedMultiplicity| */,
				ms._055 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._178 /* 1*steps-1..3 || «null» */,
				st._158 /* 1*TypedTypeRefCS::ownedPathName=73 || «null» */,
				st._204 /* V00*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._01,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _160 = new SerializationRule(116,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._279 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : 56 */,
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._280 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : 108 */,
				ms._165 /* assign V2 = |TypedRefCS::ownedMultiplicity| */,
				ms._075 /* assign V0 = |CollectionTypeCS::ownedType| */,
				ms._006 /* assert (|CollectionTypeCS::name| - 1) == 0 */,
				ms._115 /* assign V1 = |CollectionTypeCS::ownedCollectionMultiplicity| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._187 /* 1*steps-1..8 || «null» */,
				st._076 /* 1*CollectionTypeCS::name=12 || «? » «value» «? » */,
				st._208 /* V00*steps-3..7 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._077 /* 1*CollectionTypeCS::ownedType=108 || «null» */,
				st._220 /* V01*CollectionTypeCS::ownedCollectionMultiplicity=56 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._242 /* V02*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._23,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					iv._52) /* TypeExpWithoutMultiplicityCS */
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
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(108, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::TypedMultiplicityRefCS : { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] }
		private @NonNull SerializationRule _161 = new SerializationRule(116,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._264 /* check-rule basecs::TypedRefCS.ownedMultiplicity : 56 */,
				ms._300 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : 107 */,
				ms._299 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : 107 */,
				ms._132 /* assign V1 = |TypedRefCS::ownedMultiplicity| */,
				ms._082 /* assign V0 = |MapTypeCS::ownedValueType| */,
				ms._025 /* assert (|MapTypeCS::ownedKeyType| - V0) == 0 */,
				ms._024 /* assert (|MapTypeCS::name.'Map'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._188 /* 1*steps-1..9 || «null» */,
				st._019 /* 1*'Map' || «? » «value» «? » */,
				st._209 /* V00*steps-3..8 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._098 /* 1*MapTypeCS::ownedKeyType=107 || «null» */,
				st._007 /* 1*',' || «! » «value» «? » */,
				st._099 /* 1*MapTypeCS::ownedValueType=107 || «null» */,
				st._004 /* 1*')' || «! » «value» */,
				st._225 /* V01*TypedRefCS::ownedMultiplicity=56 || «null» */
			},
			sl._25,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					ev._09)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					iv._19) /* MultiplicityCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					iv._51) /* TypeExpCS */,
				new EReference_RuleIndexes(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					iv._51) /* TypeExpCS */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._09, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(56, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(107, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			});
		// OCLinEcore::TypedTypeRefCS : ownedPathName=PathNameCS
		private @NonNull SerializationRule _162 = new SerializationRule(118,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._055 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._158 /* 1*TypedTypeRefCS::ownedPathName=73 || «null» */
			},
			sl._00,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
					}
				)
			});
		// OCLinEcore::TypedTypeRefCS : { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' }
		private @NonNull SerializationRule _163 = new SerializationRule(118,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._054 /* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
				ms._055 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || «null» */,
				st._158 /* 1*TypedTypeRefCS::ownedPathName=73 || «null» */,
				st._003 /* 1*'(' || «! » «value» «! » */,
				st._157 /* 1*TypedTypeRefCS::ownedBinding=99 || «null» */,
				st._004 /* 1*')' || «! » «value» */
			},
			sl._06,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._46) /* TemplateBindingCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(99, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
					}
				)
			});
		// OCLinEcore::TypedTypeRefCS : { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' }
		private @NonNull SerializationRule _164 = new SerializationRule(118,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._265 /* check-rule basecs::TypedTypeRefCS.ownedBinding : 99 */,
				ms._266 /* check-rule basecs::TypedTypeRefCS.ownedPathName : 73 */,
				ms._054 /* assert (|TypedTypeRefCS::ownedBinding| - 1) == 0 */,
				ms._055 /* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._184 /* 1*steps-1..5 || «null» */,
				st._158 /* 1*TypedTypeRefCS::ownedPathName=73 || «null» */,
				st._012 /* 1*'<' || «? » «value» «? » */,
				st._157 /* 1*TypedTypeRefCS::ownedBinding=99 || «null» */,
				st._015 /* 1*'>' || «? » «value» «? » */
			},
			sl._10,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					iv._46) /* TemplateBindingCS */,
				new EReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					iv._27) /* PathNameCS */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(99, GrammarCardinality.ONE)
					}
				),
				new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(73, GrammarCardinality.ONE)
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
	private _SerializationRules1 sr1;
	private _SerializationRules2 sr2;
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
//	import SerializationSegment;
//	import CustomSerializationSegment;
//	import SerializationStep;
//	import SerializationStepAssignKeyword;
//	import SerializationStepAssignedRuleCall;
//	import SerializationStepAssigns;
//	import SerializationStepCrossReference;
//	import SerializationStepLiteral;
//	import SerializationStepSequence;
//	import TerminalRuleValue;
//	import BaseCommentSegmentSupport;
//	import BaseCSPackage;
//	import EssentialOCLCSPackage;
//	import OCLinEcoreCSPackage;
