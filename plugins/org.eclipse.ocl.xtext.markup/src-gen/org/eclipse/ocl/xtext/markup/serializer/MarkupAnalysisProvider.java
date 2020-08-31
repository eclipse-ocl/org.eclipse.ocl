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
package org.eclipse.ocl.xtext.markup.serializer;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.AbstractAnalysisProvider;
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue;
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
import org.eclipse.ocl.xtext.base.cs2text.solutions.IntegerCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution;
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
import org.eclipse.ocl.xtext.markupcs.MarkupPackage;

public class MarkupAnalysisProvider extends AbstractAnalysisProvider
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
					ec._00  /* markupcs::BulletElement */,
					ec._01  /* markupcs::FigureElement */,
					ec._02  /* markupcs::FigureRefElement */,
					ec._03  /* markupcs::FontElement */,
					ec._04  /* markupcs::FootnoteElement */,
					ec._05  /* markupcs::HeadingElement */,
					ec._06  /* markupcs::Markup */,
					ec._07  /* markupcs::NewLineElement */,
					ec._08  /* markupcs::NullElement */,
					ec._09  /* markupcs::OCLCodeElement */,
					ec._10  /* markupcs::OCLEvalElement */,
					ec._11  /* markupcs::OCLTextElement */,
					ec._12  /* markupcs::TextElement */
				},
				/**
				 *	The indexable per-grammar rule meta data.
				 */
				new AbstractRuleValue [] {
					gr._00  /* 0 : ANY_OTHER */,
					gr._01  /* 1 : BulletElement */,
					gr._02  /* 2 : ESCAPED */,
					gr._03  /* 3 : FigureElement */,
					gr._04  /* 4 : FigureRefElement */,
					gr._05  /* 5 : FontElement */,
					gr._06  /* 6 : FootnoteElement */,
					gr._07  /* 7 : HORIZONTAL_WS */,
					gr._08  /* 8 : HeadingElement */,
					gr._09  /* 9 : ID */,
					gr._10  /* 10 : INT */,
					gr._11  /* 11 : LETTER */,
					gr._12  /* 12 : Markup */,
					gr._13  /* 13 : MarkupElement */,
					gr._14  /* 14 : MarkupKeyword */,
					gr._15  /* 15 : NL */,
					gr._16  /* 16 : NUMBER */,
					gr._17  /* 17 : NewLineElement */,
					gr._18  /* 18 : NullElement */,
					gr._19  /* 19 : OCLCodeElement */,
					gr._20  /* 20 : OCLEvalElement */,
					gr._21  /* 21 : OCLTextElement */,
					gr._22  /* 22 : STRING */,
					gr._23  /* 23 : TextElement */,
					gr._24  /* 24 : VERTICAL_WS */,
					gr._25  /* 25 : WORD */,
					gr._26  /* 26 : WS */
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
		private final @NonNull IndexVector _0 // MarkupElement
			= new IndexVector(0x2000L);
		private final @NonNull IndexVector _1 // BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement
			= new IndexVector(0xbe217aL);
		private final @NonNull IndexVector _2 // ID|INT|WORD|WS
			= new IndexVector(0x6000600L);
	}

	/**
	 * String combinations used by assigned String EAttributes
	 */
	private class _EnumValues
	{
		private final @NonNull EnumerationValue _0 // '#|,|:'
			= new MultipleEnumerationValue(new @NonNull String[]{"#", ",", ":"});
		private final @NonNull EnumerationValue _1 // 'b|e'
			= new MultipleEnumerationValue(new @NonNull String[]{"b", "e"});
	}

	/**
	 * Expression terms used during the matching process.
	 */
	private class _MatchTerms
	{
		private final @NonNull CardinalitySolution _00 // 1
			= new IntegerCardinalitySolution(1);
		private final @NonNull CardinalitySolution _01 // |BulletElement::level|
			= new EStructuralFeatureSizeCardinalitySolution(MarkupPackage.Literals.BULLET_ELEMENT__LEVEL);
		private final @NonNull CardinalitySolution _02 // |CompoundElement::elements|
			= new EStructuralFeatureSizeCardinalitySolution(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS);
		private final @NonNull CardinalitySolution _03 // |FigureElement::alt|
			= new EStructuralFeatureSizeCardinalitySolution(MarkupPackage.Literals.FIGURE_ELEMENT__ALT);
		private final @NonNull CardinalitySolution _04 // |FigureElement::def|
			= new EStructuralFeatureSizeCardinalitySolution(MarkupPackage.Literals.FIGURE_ELEMENT__DEF);
		private final @NonNull CardinalitySolution _05 // |FigureElement::requiredHeight|
			= new EStructuralFeatureSizeCardinalitySolution(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_HEIGHT);
		private final @NonNull CardinalitySolution _06 // |FigureElement::requiredWidth|
			= new EStructuralFeatureSizeCardinalitySolution(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_WIDTH);
		private final @NonNull CardinalitySolution _07 // |FigureElement::src|
			= new EStructuralFeatureSizeCardinalitySolution(MarkupPackage.Literals.FIGURE_ELEMENT__SRC);
		private final @NonNull CardinalitySolution _08 // |FigureRefElement::ref|
			= new EStructuralFeatureSizeCardinalitySolution(MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF);
		private final @NonNull CardinalitySolution _09 // |FontElement::font.'b|e'|
			= new EAttributeSizeCardinalitySolution(MarkupPackage.Literals.FONT_ELEMENT__FONT, ev._1);
		private final @NonNull CardinalitySolution _10 // |HeadingElement::level|
			= new EStructuralFeatureSizeCardinalitySolution(MarkupPackage.Literals.HEADING_ELEMENT__LEVEL);
		private final @NonNull CardinalitySolution _11 // |NewLineElement::text|
			= new EStructuralFeatureSizeCardinalitySolution(MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT);
		private final @NonNull CardinalitySolution _12 // |TextElement::text.'#|,|:'|
			= new EAttributeSizeCardinalitySolution(MarkupPackage.Literals.TEXT_ELEMENT__TEXT, ev._0);
		private final @NonNull CardinalitySolution _13 // |TextElement::text|
			= new EStructuralFeatureSizeCardinalitySolution(MarkupPackage.Literals.TEXT_ELEMENT__TEXT);
		private final @NonNull CardinalitySolution _14 // (|FigureElement::src| - 1)
			= new SubtractCardinalitySolution(_07, _00);
		private final @NonNull CardinalitySolution _15 // (|FigureRefElement::ref| - 1)
			= new SubtractCardinalitySolution(_08, _00);
		private final @NonNull CardinalitySolution _16 // (|FontElement::font.'b|e'| - 1)
			= new SubtractCardinalitySolution(_09, _00);
		private final @NonNull CardinalitySolution _17 // (|NewLineElement::text| - 1)
			= new SubtractCardinalitySolution(_11, _00);
		private final @NonNull CardinalitySolution _18 // (|TextElement::text| - 1)
			= new SubtractCardinalitySolution(_13, _00);
	}

	/**
	 * Steps for the matching process.
	 */
	private class _MatchSteps
	{
		private final @NonNull CardinalitySolutionStep _00 // assert (|FigureElement::src| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._14);
		private final @NonNull CardinalitySolutionStep _01 // assert (|FigureRefElement::ref| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._15);
		private final @NonNull CardinalitySolutionStep _02 // assert (|FontElement::font.'b|e'| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._16);
		private final @NonNull CardinalitySolutionStep _03 // assert (|NewLineElement::text| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._17);
		private final @NonNull CardinalitySolutionStep _04 // assert (|TextElement::text| - 1) == 0
			= new CardinalitySolutionStep_Assert(mt._18);
		private final @NonNull CardinalitySolutionStep _05 // assign V0 = |BulletElement::level|
			= new CardinalitySolutionStep_Assign(0, mt._01);
		private final @NonNull CardinalitySolutionStep _06 // assign V0 = |CompoundElement::elements|
			= new CardinalitySolutionStep_Assign(0, mt._02);
		private final @NonNull CardinalitySolutionStep _07 // assign V0 = |FigureElement::def|
			= new CardinalitySolutionStep_Assign(0, mt._04);
		private final @NonNull CardinalitySolutionStep _08 // assign V0 = |HeadingElement::level|
			= new CardinalitySolutionStep_Assign(0, mt._10);
		private final @NonNull CardinalitySolutionStep _09 // assign V0 = |TextElement::text.'#|,|:'|
			= new CardinalitySolutionStep_Assign(0, mt._12);
		private final @NonNull CardinalitySolutionStep _10 // assign V1 = |CompoundElement::elements|
			= new CardinalitySolutionStep_Assign(1, mt._02);
		private final @NonNull CardinalitySolutionStep _11 // assign V1 = |FigureElement::alt|
			= new CardinalitySolutionStep_Assign(1, mt._03);
		private final @NonNull CardinalitySolutionStep _12 // assign V2 = |FigureElement::requiredWidth|
			= new CardinalitySolutionStep_Assign(2, mt._06);
		private final @NonNull CardinalitySolutionStep _13 // assign V3 = |FigureElement::requiredHeight|
			= new CardinalitySolutionStep_Assign(3, mt._05);
		private final @NonNull CardinalitySolutionStep _14 // check-rule markupcs::CompoundElement.elements : 13
			= new CardinalitySolutionStep_RuleCheck(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, iv._0/*MarkupElement*/);
	}

	/**
	 * The various serialization term used to serialize a serialization rule.
	 */
	private class _SerializationTerms
	{
		private final @NonNull RTSerializationLiteralStep _00 // 1*'#'
									= new RTSerializationLiteralStep(-1, "#");
		private final @NonNull RTSerializationLiteralStep _01 // 1*','
									= new RTSerializationLiteralStep(-1, ",");
		private final @NonNull RTSerializationLiteralStep _02 // 1*':'
									= new RTSerializationLiteralStep(-1, ":");
		private final @NonNull RTSerializationLiteralStep _03 // 1*'['
									= new RTSerializationLiteralStep(-1, "[");
		private final @NonNull RTSerializationLiteralStep _04 // 1*']'
									= new RTSerializationLiteralStep(-1, "]");
		private final @NonNull RTSerializationLiteralStep _05 // 1*'bullet'
									= new RTSerializationLiteralStep(-1, "bullet");
		private final @NonNull RTSerializationLiteralStep _06 // 1*'figure'
									= new RTSerializationLiteralStep(-1, "figure");
		private final @NonNull RTSerializationLiteralStep _07 // 1*'figureRef'
									= new RTSerializationLiteralStep(-1, "figureRef");
		private final @NonNull RTSerializationLiteralStep _08 // 1*'footnote'
									= new RTSerializationLiteralStep(-1, "footnote");
		private final @NonNull RTSerializationLiteralStep _09 // 1*'heading'
									= new RTSerializationLiteralStep(-1, "heading");
		private final @NonNull RTSerializationLiteralStep _10 // 1*'oclCode'
									= new RTSerializationLiteralStep(-1, "oclCode");
		private final @NonNull RTSerializationLiteralStep _11 // 1*'oclEval'
									= new RTSerializationLiteralStep(-1, "oclEval");
		private final @NonNull RTSerializationLiteralStep _12 // 1*'oclText'
									= new RTSerializationLiteralStep(-1, "oclText");
		private final @NonNull RTSerializationAssignedRuleCallStep _13 // 1*BulletElement::level=10
									= new RTSerializationAssignedRuleCallStep(-1, MarkupPackage.Literals.BULLET_ELEMENT__LEVEL, 10 /* INT */);
		private final @NonNull RTSerializationAssignedRuleCallStep _14 // 1*FigureElement::alt=22
									= new RTSerializationAssignedRuleCallStep(-1, MarkupPackage.Literals.FIGURE_ELEMENT__ALT, 22 /* STRING */);
		private final @NonNull RTSerializationAssignedRuleCallStep _15 // 1*FigureElement::def=9
									= new RTSerializationAssignedRuleCallStep(-1, MarkupPackage.Literals.FIGURE_ELEMENT__DEF, 9 /* ID */);
		private final @NonNull RTSerializationAssignedRuleCallStep _16 // 1*FigureElement::requiredHeight=10
									= new RTSerializationAssignedRuleCallStep(-1, MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_HEIGHT, 10 /* INT */);
		private final @NonNull RTSerializationAssignedRuleCallStep _17 // 1*FigureElement::requiredWidth=10
									= new RTSerializationAssignedRuleCallStep(-1, MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_WIDTH, 10 /* INT */);
		private final @NonNull RTSerializationAssignedRuleCallStep _18 // 1*FigureElement::src=22
									= new RTSerializationAssignedRuleCallStep(-1, MarkupPackage.Literals.FIGURE_ELEMENT__SRC, 22 /* STRING */);
		private final @NonNull RTSerializationCrossReferenceStep _19 // 1*FigureRefElement::ref=ID
									= new RTSerializationCrossReferenceStep(-1, MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF, getCrossReference(MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF, "ID"));
		private final @NonNull RTSerializationAssignStep _20 // 1*FontElement::font
									= new RTSerializationAssignStep(-1, MarkupPackage.Literals.FONT_ELEMENT__FONT, ev._1);
		private final @NonNull RTSerializationAssignedRuleCallStep _21 // 1*HeadingElement::level=10
									= new RTSerializationAssignedRuleCallStep(-1, MarkupPackage.Literals.HEADING_ELEMENT__LEVEL, 10 /* INT */);
		private final @NonNull RTSerializationAssignedRuleCallStep _22 // 1*NewLineElement::text=15
									= new RTSerializationAssignedRuleCallStep(-1, MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT, 15 /* NL */);
		private final @NonNull RTSerializationAssignedRuleCallStep _23 // 1*TextElement::text+=14
									= new RTSerializationAssignedRuleCallStep(-1, MarkupPackage.Literals.TEXT_ELEMENT__TEXT, 14 /* MarkupKeyword */);
		private final @NonNull RTSerializationSequenceStep _24 // 1*steps-1..17
									= new RTSerializationSequenceStep(-1, 1, 17);
		private final @NonNull RTSerializationSequenceStep _25 // 1*steps-1..4
									= new RTSerializationSequenceStep(-1, 1, 4);
		private final @NonNull RTSerializationSequenceStep _26 // 1*steps-1..5
									= new RTSerializationSequenceStep(-1, 1, 5);
		private final @NonNull RTSerializationSequenceStep _27 // 1*steps-1..8
									= new RTSerializationSequenceStep(-1, 1, 8);
		private final @NonNull RTSerializationAssignedRuleCallStep _28 // V00*CompoundElement::elements+=13
									= new RTSerializationAssignedRuleCallStep(0, MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 13 /* MarkupElement */);
		private final @NonNull RTSerializationAssignsStep _29 // V00*TextElement::text+=9|25|10|26
									= new RTSerializationAssignsStep(0, MarkupPackage.Literals.TEXT_ELEMENT__TEXT, ev._0, new @NonNull Integer [] { 9/*ID*/,25/*WORD*/,10/*INT*/,26/*WS*/});
		private final @NonNull RTSerializationSequenceStep _30 // V00*steps-3..5
									= new RTSerializationSequenceStep(0, 3, 5);
		private final @NonNull RTSerializationAssignedRuleCallStep _31 // V01*CompoundElement::elements+=13
									= new RTSerializationAssignedRuleCallStep(1, MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 13 /* MarkupElement */);
		private final @NonNull RTSerializationSequenceStep _32 // V01*steps-8..16
									= new RTSerializationSequenceStep(1, 8, 16);
		private final @NonNull RTSerializationSequenceStep _33 // V02*steps-11..16
									= new RTSerializationSequenceStep(2, 11, 16);
		private final @NonNull RTSerializationSequenceStep _34 // V03*steps-14..16
									= new RTSerializationSequenceStep(3, 14, 16);
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
		private final @NonNull Segment @NonNull [] @Nullable [] _0 = new @NonNull Segment @NonNull [] @Nullable [] {
			null
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _1 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _2 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _3 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._2 /* «! » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._2 /* «! » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._2 /* «! » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _4 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _5 = new @NonNull Segment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull Segment @NonNull [] @Nullable [] _6 = new @NonNull Segment @NonNull [] @Nullable [] {
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
		private final @NonNull ParserRuleValue _01 // BulletElement
			= new ParserRuleValue(1, "BulletElement",
				new @NonNull SerializationRule [] {
					sr0._00 /* { 'bullet' { ':' level=INT }[?] '[' elements+=MarkupElement[*] ']' } */
				},
				null);
		private final @NonNull TerminalRuleValue _02 // ESCAPED
			= new TerminalRuleValue(2, "ESCAPED");
		private final @NonNull ParserRuleValue _03 // FigureElement
			= new ParserRuleValue(3, "FigureElement",
				new @NonNull SerializationRule [] {
					sr0._01 /* { 'figure' { '#' def=ID }[?] '[' src=STRING { ',' alt=STRING { ',' requiredWidth=INT { ',' requiredHeight=INT }[?] }[?] }[?] ']' } */
				},
				null);
		private final @NonNull ParserRuleValue _04 // FigureRefElement
			= new ParserRuleValue(4, "FigureRefElement",
				new @NonNull SerializationRule [] {
					sr0._02 /* { 'figureRef' '[' ref=ID ']' } */
				},
				null);
		private final @NonNull ParserRuleValue _05 // FontElement
			= new ParserRuleValue(5, "FontElement",
				new @NonNull SerializationRule [] {
					sr0._03 /* { font={'b|e'} '[' elements+=MarkupElement[*] ']' } */
				},
				null);
		private final @NonNull ParserRuleValue _06 // FootnoteElement
			= new ParserRuleValue(6, "FootnoteElement",
				new @NonNull SerializationRule [] {
					sr0._04 /* { 'footnote' '[' elements+=MarkupElement[*] ']' } */
				},
				null);
		private final @NonNull TerminalRuleValue _07 // HORIZONTAL_WS
			= new TerminalRuleValue(7, "HORIZONTAL_WS");
		private final @NonNull ParserRuleValue _08 // HeadingElement
			= new ParserRuleValue(8, "HeadingElement",
				new @NonNull SerializationRule [] {
					sr0._05 /* { 'heading' { ':' level=INT }[?] '[' elements+=MarkupElement[*] ']' } */
				},
				null);
		private final @NonNull TerminalRuleValue _09 // ID
			= new TerminalRuleValue(9, "ID");
		private final @NonNull TerminalRuleValue _10 // INT
			= new TerminalRuleValue(10, "INT");
		private final @NonNull TerminalRuleValue _11 // LETTER
			= new TerminalRuleValue(11, "LETTER");
		private final @NonNull ParserRuleValue _12 // Markup
			= new ParserRuleValue(12, "Markup",
				new @NonNull SerializationRule [] {
					sr0._06 /* elements+=MarkupElement[*] */
				},
				null);
		private final @NonNull ParserRuleValue _13 // MarkupElement
			= new ParserRuleValue(13, "MarkupElement",
				new @NonNull SerializationRule [] {
					sr0._00 /* { 'bullet' { ':' level=INT }[?] '[' elements+=MarkupElement[*] ']' } */,
					sr0._01 /* { 'figure' { '#' def=ID }[?] '[' src=STRING { ',' alt=STRING { ',' requiredWidth=INT { ',' requiredHeight=INT }[?] }[?] }[?] ']' } */,
					sr0._02 /* { 'figureRef' '[' ref=ID ']' } */,
					sr0._03 /* { font={'b|e'} '[' elements+=MarkupElement[*] ']' } */,
					sr0._04 /* { 'footnote' '[' elements+=MarkupElement[*] ']' } */,
					sr0._05 /* { 'heading' { ':' level=INT }[?] '[' elements+=MarkupElement[*] ']' } */,
					sr0._07 /* text=NL */,
					sr0._08 /* { '[' elements+=MarkupElement[*] ']' } */,
					sr0._09 /* { 'oclCode' '[' elements+=MarkupElement[*] ']' } */,
					sr0._10 /* { 'oclEval' '[' elements+=MarkupElement[*] ']' } */,
					sr0._11 /* { 'oclText' '[' elements+=MarkupElement[*] ']' } */,
					sr0._12 /* text+=('#|,|:'|ID|WORD|INT|WS)[+] */,
					sr0._13 /* text+=MarkupKeyword */
				},
				iv._1); /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
		private final @NonNull DataTypeRuleValue _14 // MarkupKeyword
			= new DataTypeRuleValue(14, "MarkupKeyword");
		private final @NonNull TerminalRuleValue _15 // NL
			= new TerminalRuleValue(15, "NL");
		private final @NonNull TerminalRuleValue _16 // NUMBER
			= new TerminalRuleValue(16, "NUMBER");
		private final @NonNull ParserRuleValue _17 // NewLineElement
			= new ParserRuleValue(17, "NewLineElement",
				new @NonNull SerializationRule [] {
					sr0._07 /* text=NL */
				},
				null);
		private final @NonNull ParserRuleValue _18 // NullElement
			= new ParserRuleValue(18, "NullElement",
				new @NonNull SerializationRule [] {
					sr0._08 /* { '[' elements+=MarkupElement[*] ']' } */
				},
				null);
		private final @NonNull ParserRuleValue _19 // OCLCodeElement
			= new ParserRuleValue(19, "OCLCodeElement",
				new @NonNull SerializationRule [] {
					sr0._09 /* { 'oclCode' '[' elements+=MarkupElement[*] ']' } */
				},
				null);
		private final @NonNull ParserRuleValue _20 // OCLEvalElement
			= new ParserRuleValue(20, "OCLEvalElement",
				new @NonNull SerializationRule [] {
					sr0._10 /* { 'oclEval' '[' elements+=MarkupElement[*] ']' } */
				},
				null);
		private final @NonNull ParserRuleValue _21 // OCLTextElement
			= new ParserRuleValue(21, "OCLTextElement",
				new @NonNull SerializationRule [] {
					sr0._11 /* { 'oclText' '[' elements+=MarkupElement[*] ']' } */
				},
				null);
		private final @NonNull TerminalRuleValue _22 // STRING
			= new TerminalRuleValue(22, "STRING");
		private final @NonNull ParserRuleValue _23 // TextElement
			= new ParserRuleValue(23, "TextElement",
				new @NonNull SerializationRule [] {
					sr0._12 /* text+=('#|,|:'|ID|WORD|INT|WS)[+] */,
					sr0._13 /* text+=MarkupKeyword */
				},
				null);
		private final @NonNull TerminalRuleValue _24 // VERTICAL_WS
			= new TerminalRuleValue(24, "VERTICAL_WS");
		private final @NonNull TerminalRuleValue _25 // WORD
			= new TerminalRuleValue(25, "WORD");
		private final @NonNull TerminalRuleValue _26 // WS
			= new TerminalRuleValue(26, "WS");
	}

	/**
	 * Configuration for each EClass that may be serialized.
	 */
	private class _EClassValues
	{
		private final @NonNull EClassValue _00 // BulletElement
			= new EClassValue(MarkupPackage.Literals.BULLET_ELEMENT,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._00, sl._2) /* { 'bullet' { ':' level=INT }[?] '[' elements+=MarkupElement[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						iv._1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
				}
			);
		private final @NonNull EClassValue _01 // FigureElement
			= new EClassValue(MarkupPackage.Literals.FIGURE_ELEMENT,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._01, sl._3) /* { 'figure' { '#' def=ID }[?] '[' src=STRING { ',' alt=STRING { ',' requiredWidth=INT { ',' requiredHeight=INT }[?] }[?] }[?] ']' } */
				}, null
			);
		private final @NonNull EClassValue _02 // FigureRefElement
			= new EClassValue(MarkupPackage.Literals.FIGURE_REF_ELEMENT,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._02, sl._5) /* { 'figureRef' '[' ref=ID ']' } */
				}, null
			);
		private final @NonNull EClassValue _03 // FontElement
			= new EClassValue(MarkupPackage.Literals.FONT_ELEMENT,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._03, sl._4) /* { font={'b|e'} '[' elements+=MarkupElement[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						iv._1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
				}
			);
		private final @NonNull EClassValue _04 // FootnoteElement
			= new EClassValue(MarkupPackage.Literals.FOOTNOTE_ELEMENT,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._04, sl._4) /* { 'footnote' '[' elements+=MarkupElement[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						iv._1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
				}
			);
		private final @NonNull EClassValue _05 // HeadingElement
			= new EClassValue(MarkupPackage.Literals.HEADING_ELEMENT,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._05, sl._2) /* { 'heading' { ':' level=INT }[?] '[' elements+=MarkupElement[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						iv._1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
				}
			);
		private final @NonNull EClassValue _06 // Markup
			= new EClassValue(MarkupPackage.Literals.MARKUP,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._06, sl._0) /* elements+=MarkupElement[*] */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						iv._1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
				}
			);
		private final @NonNull EClassValue _07 // NewLineElement
			= new EClassValue(MarkupPackage.Literals.NEW_LINE_ELEMENT,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._07, sl._6) /* text=NL */
				}, null
			);
		private final @NonNull EClassValue _08 // NullElement
			= new EClassValue(MarkupPackage.Literals.NULL_ELEMENT,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._08, sl._1) /* { '[' elements+=MarkupElement[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						iv._1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
				}
			);
		private final @NonNull EClassValue _09 // OCLCodeElement
			= new EClassValue(MarkupPackage.Literals.OCL_CODE_ELEMENT,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._09, sl._4) /* { 'oclCode' '[' elements+=MarkupElement[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						iv._1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
				}
			);
		private final @NonNull EClassValue _10 // OCLEvalElement
			= new EClassValue(MarkupPackage.Literals.OCL_EVAL_ELEMENT,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._10, sl._4) /* { 'oclEval' '[' elements+=MarkupElement[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						iv._1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
				}
			);
		private final @NonNull EClassValue _11 // OCLTextElement
			= new EClassValue(MarkupPackage.Literals.OCL_TEXT_ELEMENT,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._11, sl._4) /* { 'oclText' '[' elements+=MarkupElement[*] ']' } */
				},
				new @NonNull EReference_RuleIndexes [] {
					new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						iv._1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
				}
			);
		private final @NonNull EClassValue _12 // TextElement
			= new EClassValue(MarkupPackage.Literals.TEXT_ELEMENT,
				new @NonNull SerializationRule_SegmentsList [] {
					new SerializationRule_SegmentsList(sr0._12, sl._6) /* text+=('#|,|:'|ID|WORD|INT|WS)[+] */,
					new SerializationRule_SegmentsList(sr0._13, sl._6) /* text+=MarkupKeyword */
				}, null
			);
	}

	/**
	 * The various serialization rules that serialize an EClass.
	 */
	private class _SerializationRules0
	{
		// Markup::BulletElement : { 'bullet' { ':' level=INT }[?] '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _00 = new SerializationRule(1,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._10 /* assign V1 = |CompoundElement::elements| */,
				ms._05 /* assign V0 = |BulletElement::level| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._27 /* 1*steps-1..8 || «null» */,
				st._05 /* 1*'bullet' || «? » «value» «? » */,
				st._30 /* V00*steps-3..5 || «null» */,
				st._02 /* 1*':' || «? » «value» «? » */,
				st._13 /* 1*BulletElement::level=10 || «? » «value» «? » */,
				st._03 /* 1*'[' || «! » «value» «! » */,
				st._31 /* V01*CompoundElement::elements+=13 || «null» */,
				st._04 /* 1*']' || «! » «value» */
			},
			sl._2,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					iv._0) /* MarkupElement */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(MarkupPackage.Literals.BULLET_ELEMENT__LEVEL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::FigureElement : { 'figure' { '#' def=ID }[?] '[' src=STRING { ',' alt=STRING { ',' requiredWidth=INT { ',' requiredHeight=INT }[?] }[?] }[?] ']' }
		private @NonNull SerializationRule _01 = new SerializationRule(3,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._11 /* assign V1 = |FigureElement::alt| */,
				ms._00 /* assert (|FigureElement::src| - 1) == 0 */,
				ms._07 /* assign V0 = |FigureElement::def| */,
				ms._12 /* assign V2 = |FigureElement::requiredWidth| */,
				ms._13 /* assign V3 = |FigureElement::requiredHeight| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._24 /* 1*steps-1..17 || «null» */,
				st._06 /* 1*'figure' || «? » «value» «? » */,
				st._30 /* V00*steps-3..5 || «null» */,
				st._00 /* 1*'#' || «? » «value» «? » */,
				st._15 /* 1*FigureElement::def=9 || «? » «value» «? » */,
				st._03 /* 1*'[' || «! » «value» «! » */,
				st._18 /* 1*FigureElement::src=22 || «? » «value» «? » */,
				st._32 /* V01*steps-8..16 || «null» */,
				st._01 /* 1*',' || «! » «value» «? » */,
				st._14 /* 1*FigureElement::alt=22 || «? » «value» «? » */,
				st._33 /* V02*steps-11..16 || «null» */,
				st._01 /* 1*',' || «! » «value» «? » */,
				st._17 /* 1*FigureElement::requiredWidth=10 || «? » «value» «? » */,
				st._34 /* V03*steps-14..16 || «null» */,
				st._01 /* 1*',' || «! » «value» «? » */,
				st._16 /* 1*FigureElement::requiredHeight=10 || «? » «value» «? » */,
				st._04 /* 1*']' || «! » «value» */
			},
			sl._3,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				MarkupPackage.Literals.FIGURE_ELEMENT__SRC
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__ALT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__DEF,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_HEIGHT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_WIDTH,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_MultiplicativeCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__SRC,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Markup::FigureRefElement : { 'figureRef' '[' ref=ID ']' }
		private @NonNull SerializationRule _02 = new SerializationRule(4,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._01 /* assert (|FigureRefElement::ref| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._26 /* 1*steps-1..5 || «null» */,
				st._07 /* 1*'figureRef' || «? » «value» «? » */,
				st._03 /* 1*'[' || «! » «value» «! » */,
				st._19 /* 1*FigureRefElement::ref=ID || «? » «value» «? » */,
				st._04 /* 1*']' || «! » «value» */
			},
			sl._5,
			null,
			null,
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					}
				)
			});
		// Markup::FontElement : { font={'b|e'} '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _03 = new SerializationRule(5,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */,
				ms._02 /* assert (|FontElement::font.'b|e'| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._26 /* 1*steps-1..5 || «null» */,
				st._20 /* 1*FontElement::font || «? » «value» «? » */,
				st._03 /* 1*'[' || «! » «value» «! » */,
				st._28 /* V00*CompoundElement::elements+=13 || «null» */,
				st._04 /* 1*']' || «! » «value» */
			},
			sl._4,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(MarkupPackage.Literals.FONT_ELEMENT__FONT,
					ev._1)
			},
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					iv._0) /* MarkupElement */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(MarkupPackage.Literals.FONT_ELEMENT__FONT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._1, MultiplicativeCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::FootnoteElement : { 'footnote' '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _04 = new SerializationRule(6,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._26 /* 1*steps-1..5 || «null» */,
				st._08 /* 1*'footnote' || «? » «value» «? » */,
				st._03 /* 1*'[' || «! » «value» «! » */,
				st._28 /* V00*CompoundElement::elements+=13 || «null» */,
				st._04 /* 1*']' || «! » «value» */
			},
			sl._4,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					iv._0) /* MarkupElement */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::HeadingElement : { 'heading' { ':' level=INT }[?] '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _05 = new SerializationRule(8,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._10 /* assign V1 = |CompoundElement::elements| */,
				ms._08 /* assign V0 = |HeadingElement::level| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._27 /* 1*steps-1..8 || «null» */,
				st._09 /* 1*'heading' || «? » «value» «? » */,
				st._30 /* V00*steps-3..5 || «null» */,
				st._02 /* 1*':' || «? » «value» «? » */,
				st._21 /* 1*HeadingElement::level=10 || «? » «value» «? » */,
				st._03 /* 1*'[' || «! » «value» «! » */,
				st._31 /* V01*CompoundElement::elements+=13 || «null» */,
				st._04 /* 1*']' || «! » «value» */
			},
			sl._2,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					iv._0) /* MarkupElement */
			},
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(MarkupPackage.Literals.HEADING_ELEMENT__LEVEL,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::Markup : elements+=MarkupElement[*]
		private @NonNull SerializationRule _06 = new SerializationRule(12,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._28 /* V00*CompoundElement::elements+=13 || «null» */
			},
			sl._0,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					iv._0) /* MarkupElement */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::NewLineElement : text=NL
		private @NonNull SerializationRule _07 = new SerializationRule(17,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._03 /* assert (|NewLineElement::text| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._22 /* 1*NewLineElement::text=15 || «? » «value» «? » */
			},
			sl._6,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
			null);
		// Markup::NullElement : { '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _08 = new SerializationRule(18,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._25 /* 1*steps-1..4 || «null» */,
				st._03 /* 1*'[' || «! » «value» «! » */,
				st._28 /* V00*CompoundElement::elements+=13 || «null» */,
				st._04 /* 1*']' || «! » «value» */
			},
			sl._1,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					iv._0) /* MarkupElement */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::OCLCodeElement : { 'oclCode' '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _09 = new SerializationRule(19,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._26 /* 1*steps-1..5 || «null» */,
				st._10 /* 1*'oclCode' || «? » «value» «? » */,
				st._03 /* 1*'[' || «! » «value» «! » */,
				st._28 /* V00*CompoundElement::elements+=13 || «null» */,
				st._04 /* 1*']' || «! » «value» */
			},
			sl._4,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					iv._0) /* MarkupElement */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::OCLEvalElement : { 'oclEval' '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _10 = new SerializationRule(20,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._26 /* 1*steps-1..5 || «null» */,
				st._11 /* 1*'oclEval' || «? » «value» «? » */,
				st._03 /* 1*'[' || «! » «value» «! » */,
				st._28 /* V00*CompoundElement::elements+=13 || «null» */,
				st._04 /* 1*']' || «! » «value» */
			},
			sl._4,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					iv._0) /* MarkupElement */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::OCLTextElement : { 'oclText' '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _11 = new SerializationRule(21,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._26 /* 1*steps-1..5 || «null» */,
				st._12 /* 1*'oclText' || «? » «value» «? » */,
				st._03 /* 1*'[' || «! » «value» «! » */,
				st._28 /* V00*CompoundElement::elements+=13 || «null» */,
				st._04 /* 1*']' || «! » «value» */
			},
			sl._4,
			null,
			new @NonNull EReference_RuleIndexes [] {
				new EReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					iv._0) /* MarkupElement */
			},
			null,
			null,
			new @NonNull EReference_RuleIndex_MultiplicativeCardinality [] {
				new EReference_RuleIndex_MultiplicativeCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_MultiplicativeCardinality [] {
					new RuleIndex_MultiplicativeCardinality(13, MultiplicativeCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::TextElement : text+=('#|,|:'|ID|WORD|INT|WS)[+]
		private @NonNull SerializationRule _12 = new SerializationRule(23,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._09 /* assign V0 = |TextElement::text.'#|,|:'| */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._29 /* V00*TextElement::text+=9|25|10|26 || «? » «value» «? » */
			},
			sl._6,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(MarkupPackage.Literals.TEXT_ELEMENT__TEXT,
					ev._0)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(MarkupPackage.Literals.TEXT_ELEMENT__TEXT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(ev._0, MultiplicativeCardinality.ONE_OR_MORE)
					}
				)
			},
			null);
		// Markup::TextElement : text+=MarkupKeyword
		private @NonNull SerializationRule _13 = new SerializationRule(23,
			new @NonNull CardinalitySolutionStep @NonNull [] {
				ms._04 /* assert (|TextElement::text| - 1) == 0 */
			},
			new @NonNull RTSerializationStep @NonNull [] {
				st._23 /* 1*TextElement::text+=14 || «? » «value» «? » */
			},
			sl._6,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				MarkupPackage.Literals.TEXT_ELEMENT__TEXT
			},
			new @NonNull EAttribute_EnumerationValue_MultiplicativeCardinality [] {
				new EAttribute_EnumerationValue_MultiplicativeCardinality(MarkupPackage.Literals.TEXT_ELEMENT__TEXT,
					new @NonNull EnumerationValue_MultiplicativeCardinality [] {
						new EnumerationValue_MultiplicativeCardinality(null, MultiplicativeCardinality.ONE)
					}
				)
			},
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
//	import IntegerCardinalitySolution;
//	import SubtractCardinalitySolution;
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
//	import MarkupPackage;
