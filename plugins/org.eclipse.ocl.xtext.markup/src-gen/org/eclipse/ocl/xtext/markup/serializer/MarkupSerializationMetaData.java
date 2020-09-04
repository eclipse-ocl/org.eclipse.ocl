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
import org.eclipse.ocl.examples.xtext.serializer.AbstractSerializationMetaData;
import org.eclipse.ocl.examples.xtext.serializer.DataTypeRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue.SerializationRule_SegmentsList;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueMultiple;
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
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermInteger;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermSubtract;
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
import org.eclipse.ocl.xtext.markupcs.MarkupPackage;

public class MarkupSerializationMetaData extends AbstractSerializationMetaData
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
				new GrammarRuleValue [] {
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
	private class _GrammarRuleVectors
	{
		private final @NonNull GrammarRuleVector _0 // MarkupElement
			= new GrammarRuleVector(0x2000L);
		private final @NonNull GrammarRuleVector _1 // BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement
			= new GrammarRuleVector(0xbe217aL);
		private final @NonNull GrammarRuleVector _2 // ID|INT|WORD|WS
			= new GrammarRuleVector(0x6000600L);
	}

	/**
	 * String combinations used by assigned String EAttributes
	 */
	private class _EnumValues
	{
		private final @NonNull EnumerationValue _0 // '#|,|:'
			= new EnumerationValueMultiple(new @NonNull String[]{"#", ",", ":"});
		private final @NonNull EnumerationValue _1 // 'b|e'
			= new EnumerationValueMultiple(new @NonNull String[]{"b", "e"});
	}

	/**
	 * Expression terms used during the matching process.
	 */
	private class _MatchTerms
	{
		private final @NonNull SerializationMatchTerm _00 // 1
			= new SerializationMatchTermInteger(1);
		private final @NonNull SerializationMatchTerm _01 // |BulletElement::level|
			= new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.BULLET_ELEMENT__LEVEL);
		private final @NonNull SerializationMatchTerm _02 // |CompoundElement::elements|
			= new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS);
		private final @NonNull SerializationMatchTerm _03 // |FigureElement::alt|
			= new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__ALT);
		private final @NonNull SerializationMatchTerm _04 // |FigureElement::def|
			= new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__DEF);
		private final @NonNull SerializationMatchTerm _05 // |FigureElement::requiredHeight|
			= new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_HEIGHT);
		private final @NonNull SerializationMatchTerm _06 // |FigureElement::requiredWidth|
			= new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_WIDTH);
		private final @NonNull SerializationMatchTerm _07 // |FigureElement::src|
			= new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__SRC);
		private final @NonNull SerializationMatchTerm _08 // |FigureRefElement::ref|
			= new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF);
		private final @NonNull SerializationMatchTerm _09 // |FontElement::font.'b|e'|
			= new SerializationMatchTermEAttributeSize(MarkupPackage.Literals.FONT_ELEMENT__FONT, ev._1);
		private final @NonNull SerializationMatchTerm _10 // |HeadingElement::level|
			= new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.HEADING_ELEMENT__LEVEL);
		private final @NonNull SerializationMatchTerm _11 // |NewLineElement::text|
			= new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT);
		private final @NonNull SerializationMatchTerm _12 // |TextElement::text.'#|,|:'|
			= new SerializationMatchTermEAttributeSize(MarkupPackage.Literals.TEXT_ELEMENT__TEXT, ev._0);
		private final @NonNull SerializationMatchTerm _13 // |TextElement::text|
			= new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.TEXT_ELEMENT__TEXT);
		private final @NonNull SerializationMatchTerm _14 // (|FigureElement::src| - 1)
			= new SerializationMatchTermSubtract(_07, _00);
		private final @NonNull SerializationMatchTerm _15 // (|FigureRefElement::ref| - 1)
			= new SerializationMatchTermSubtract(_08, _00);
		private final @NonNull SerializationMatchTerm _16 // (|FontElement::font.'b|e'| - 1)
			= new SerializationMatchTermSubtract(_09, _00);
		private final @NonNull SerializationMatchTerm _17 // (|NewLineElement::text| - 1)
			= new SerializationMatchTermSubtract(_11, _00);
		private final @NonNull SerializationMatchTerm _18 // (|TextElement::text| - 1)
			= new SerializationMatchTermSubtract(_13, _00);
	}

	/**
	 * Steps for the matching process.
	 */
	private class _MatchSteps
	{
		private final @NonNull SerializationMatchStep _00 // assert (|FigureElement::src| - 1) == 0
			= new MatchStep_Assert(mt._14);
		private final @NonNull SerializationMatchStep _01 // assert (|FigureRefElement::ref| - 1) == 0
			= new MatchStep_Assert(mt._15);
		private final @NonNull SerializationMatchStep _02 // assert (|FontElement::font.'b|e'| - 1) == 0
			= new MatchStep_Assert(mt._16);
		private final @NonNull SerializationMatchStep _03 // assert (|NewLineElement::text| - 1) == 0
			= new MatchStep_Assert(mt._17);
		private final @NonNull SerializationMatchStep _04 // assert (|TextElement::text| - 1) == 0
			= new MatchStep_Assert(mt._18);
		private final @NonNull SerializationMatchStep _05 // assign V0 = |BulletElement::level|
			= new MatchStep_Assign(0, mt._01);
		private final @NonNull SerializationMatchStep _06 // assign V0 = |CompoundElement::elements|
			= new MatchStep_Assign(0, mt._02);
		private final @NonNull SerializationMatchStep _07 // assign V0 = |FigureElement::def|
			= new MatchStep_Assign(0, mt._04);
		private final @NonNull SerializationMatchStep _08 // assign V0 = |HeadingElement::level|
			= new MatchStep_Assign(0, mt._10);
		private final @NonNull SerializationMatchStep _09 // assign V0 = |TextElement::text.'#|,|:'|
			= new MatchStep_Assign(0, mt._12);
		private final @NonNull SerializationMatchStep _10 // assign V1 = |CompoundElement::elements|
			= new MatchStep_Assign(1, mt._02);
		private final @NonNull SerializationMatchStep _11 // assign V1 = |FigureElement::alt|
			= new MatchStep_Assign(1, mt._03);
		private final @NonNull SerializationMatchStep _12 // assign V2 = |FigureElement::requiredWidth|
			= new MatchStep_Assign(2, mt._06);
		private final @NonNull SerializationMatchStep _13 // assign V3 = |FigureElement::requiredHeight|
			= new MatchStep_Assign(3, mt._05);
		private final @NonNull SerializationMatchStep _14 // check-rule markupcs::CompoundElement.elements : 13
			= new MatchStep_RuleCheck(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, iv._0/*MarkupElement*/);
	}

	/**
	 * The various serialization term used to serialize a serialization rule.
	 */
	private class _SerializationTerms
	{
		private final @NonNull SerializationStepLiteral _00 // 1*'#'
									= new SerializationStepLiteral(-1, "#");
		private final @NonNull SerializationStepLiteral _01 // 1*','
									= new SerializationStepLiteral(-1, ",");
		private final @NonNull SerializationStepLiteral _02 // 1*':'
									= new SerializationStepLiteral(-1, ":");
		private final @NonNull SerializationStepLiteral _03 // 1*'['
									= new SerializationStepLiteral(-1, "[");
		private final @NonNull SerializationStepLiteral _04 // 1*']'
									= new SerializationStepLiteral(-1, "]");
		private final @NonNull SerializationStepLiteral _05 // 1*'bullet'
									= new SerializationStepLiteral(-1, "bullet");
		private final @NonNull SerializationStepLiteral _06 // 1*'figure'
									= new SerializationStepLiteral(-1, "figure");
		private final @NonNull SerializationStepLiteral _07 // 1*'figureRef'
									= new SerializationStepLiteral(-1, "figureRef");
		private final @NonNull SerializationStepLiteral _08 // 1*'footnote'
									= new SerializationStepLiteral(-1, "footnote");
		private final @NonNull SerializationStepLiteral _09 // 1*'heading'
									= new SerializationStepLiteral(-1, "heading");
		private final @NonNull SerializationStepLiteral _10 // 1*'oclCode'
									= new SerializationStepLiteral(-1, "oclCode");
		private final @NonNull SerializationStepLiteral _11 // 1*'oclEval'
									= new SerializationStepLiteral(-1, "oclEval");
		private final @NonNull SerializationStepLiteral _12 // 1*'oclText'
									= new SerializationStepLiteral(-1, "oclText");
		private final @NonNull SerializationStepAssignedRuleCall _13 // 1*BulletElement::level=10
									= new SerializationStepAssignedRuleCall(-1, MarkupPackage.Literals.BULLET_ELEMENT__LEVEL, 10 /* INT */);
		private final @NonNull SerializationStepAssignedRuleCall _14 // 1*FigureElement::alt=22
									= new SerializationStepAssignedRuleCall(-1, MarkupPackage.Literals.FIGURE_ELEMENT__ALT, 22 /* STRING */);
		private final @NonNull SerializationStepAssignedRuleCall _15 // 1*FigureElement::def=9
									= new SerializationStepAssignedRuleCall(-1, MarkupPackage.Literals.FIGURE_ELEMENT__DEF, 9 /* ID */);
		private final @NonNull SerializationStepAssignedRuleCall _16 // 1*FigureElement::requiredHeight=10
									= new SerializationStepAssignedRuleCall(-1, MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_HEIGHT, 10 /* INT */);
		private final @NonNull SerializationStepAssignedRuleCall _17 // 1*FigureElement::requiredWidth=10
									= new SerializationStepAssignedRuleCall(-1, MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_WIDTH, 10 /* INT */);
		private final @NonNull SerializationStepAssignedRuleCall _18 // 1*FigureElement::src=22
									= new SerializationStepAssignedRuleCall(-1, MarkupPackage.Literals.FIGURE_ELEMENT__SRC, 22 /* STRING */);
		private final @NonNull SerializationStepCrossReference _19 // 1*FigureRefElement::ref=ID
									= new SerializationStepCrossReference(-1, MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF, getCrossReference(MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF, "ID"));
		private final @NonNull SerializationStepAssignKeyword _20 // 1*FontElement::font='b|e'
									= new SerializationStepAssignKeyword(-1, MarkupPackage.Literals.FONT_ELEMENT__FONT, ev._1);
		private final @NonNull SerializationStepAssignedRuleCall _21 // 1*HeadingElement::level=10
									= new SerializationStepAssignedRuleCall(-1, MarkupPackage.Literals.HEADING_ELEMENT__LEVEL, 10 /* INT */);
		private final @NonNull SerializationStepAssignedRuleCall _22 // 1*NewLineElement::text=15
									= new SerializationStepAssignedRuleCall(-1, MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT, 15 /* NL */);
		private final @NonNull SerializationStepAssignedRuleCall _23 // 1*TextElement::text+=14
									= new SerializationStepAssignedRuleCall(-1, MarkupPackage.Literals.TEXT_ELEMENT__TEXT, 14 /* MarkupKeyword */);
		private final @NonNull SerializationStepSequence _24 // 1*steps-1..17
									= new SerializationStepSequence(-1, 1, 17);
		private final @NonNull SerializationStepSequence _25 // 1*steps-1..4
									= new SerializationStepSequence(-1, 1, 4);
		private final @NonNull SerializationStepSequence _26 // 1*steps-1..5
									= new SerializationStepSequence(-1, 1, 5);
		private final @NonNull SerializationStepSequence _27 // 1*steps-1..8
									= new SerializationStepSequence(-1, 1, 8);
		private final @NonNull SerializationStepAssignedRuleCall _28 // V00*CompoundElement::elements+=13
									= new SerializationStepAssignedRuleCall(0, MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 13 /* MarkupElement */);
		private final @NonNull SerializationStepAssigns _29 // V00*TextElement::text+=9|25|10|26
									= new SerializationStepAssigns(0, MarkupPackage.Literals.TEXT_ELEMENT__TEXT, ev._0, new @NonNull Integer [] { 9/*ID*/,25/*WORD*/,10/*INT*/,26/*WS*/});
		private final @NonNull SerializationStepSequence _30 // V00*steps-3..5
									= new SerializationStepSequence(0, 3, 5);
		private final @NonNull SerializationStepAssignedRuleCall _31 // V01*CompoundElement::elements+=13
									= new SerializationStepAssignedRuleCall(1, MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 13 /* MarkupElement */);
		private final @NonNull SerializationStepSequence _32 // V01*steps-8..16
									= new SerializationStepSequence(1, 8, 16);
		private final @NonNull SerializationStepSequence _33 // V02*steps-11..16
									= new SerializationStepSequence(2, 11, 16);
		private final @NonNull SerializationStepSequence _34 // V03*steps-14..16
									= new SerializationStepSequence(3, 14, 16);
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _0 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _1 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _2 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			null,
			ss._7 /* «? » «value» «? » */,
			ss._7 /* «? » «value» «? » */,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _3 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
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
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _4 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			ss._1 /* «! » «value» «! » */,
			null,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _5 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
			null,
			ss._7 /* «? » «value» «? » */,
			ss._1 /* «! » «value» «! » */,
			ss._7 /* «? » «value» «? » */,
			ss._4 /* «! » «value» */
		};
		private final @NonNull SerializationSegment @NonNull [] @Nullable [] _6 = new @NonNull SerializationSegment @NonNull [] @Nullable [] {
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
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._10 /* assign V1 = |CompoundElement::elements| */,
				ms._05 /* assign V0 = |BulletElement::level| */
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.BULLET_ELEMENT__LEVEL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::FigureElement : { 'figure' { '#' def=ID }[?] '[' src=STRING { ',' alt=STRING { ',' requiredWidth=INT { ',' requiredHeight=INT }[?] }[?] }[?] ']' }
		private @NonNull SerializationRule _01 = new SerializationRule(3,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._11 /* assign V1 = |FigureElement::alt| */,
				ms._00 /* assert (|FigureElement::src| - 1) == 0 */,
				ms._07 /* assign V0 = |FigureElement::def| */,
				ms._12 /* assign V2 = |FigureElement::requiredWidth| */,
				ms._13 /* assign V3 = |FigureElement::requiredHeight| */
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__ALT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_WIDTH,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__DEF,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__SRC,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				),
				new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_HEIGHT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			null);
		// Markup::FigureRefElement : { 'figureRef' '[' ref=ID ']' }
		private @NonNull SerializationRule _02 = new SerializationRule(4,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._01 /* assert (|FigureRefElement::ref| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF,
					new @NonNull RuleIndex_GrammarCardinality [] {
					}
				)
			});
		// Markup::FontElement : { font={'b|e'} '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _03 = new SerializationRule(5,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */,
				ms._02 /* assert (|FontElement::font.'b|e'| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._26 /* 1*steps-1..5 || «null» */,
				st._20 /* 1*FontElement::font='b|e' || «? » «value» «? » */,
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.FONT_ELEMENT__FONT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._1, GrammarCardinality.ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::FootnoteElement : { 'footnote' '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _04 = new SerializationRule(6,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::HeadingElement : { 'heading' { ':' level=INT }[?] '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _05 = new SerializationRule(8,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._10 /* assign V1 = |CompoundElement::elements| */,
				ms._08 /* assign V0 = |HeadingElement::level| */
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.HEADING_ELEMENT__LEVEL,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ZERO_OR_ONE)
					}
				)
			},
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::Markup : elements+=MarkupElement[*]
		private @NonNull SerializationRule _06 = new SerializationRule(12,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::NewLineElement : text=NL
		private @NonNull SerializationRule _07 = new SerializationRule(17,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._03 /* assert (|NewLineElement::text| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._22 /* 1*NewLineElement::text=15 || «? » «value» «? » */
			},
			sl._6,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
			null);
		// Markup::NullElement : { '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _08 = new SerializationRule(18,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::OCLCodeElement : { 'oclCode' '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _09 = new SerializationRule(19,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::OCLEvalElement : { 'oclEval' '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _10 = new SerializationRule(20,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::OCLTextElement : { 'oclText' '[' elements+=MarkupElement[*] ']' }
		private @NonNull SerializationRule _11 = new SerializationRule(21,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._14 /* check-rule markupcs::CompoundElement.elements : 13 */,
				ms._06 /* assign V0 = |CompoundElement::elements| */
			},
			new @NonNull SerializationStep @NonNull [] {
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
			new @NonNull EReference_RuleIndex_GrammarCardinality [] {
				new EReference_RuleIndex_GrammarCardinality(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					new @NonNull RuleIndex_GrammarCardinality [] {
					new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
					}
				)
			});
		// Markup::TextElement : text+=('#|,|:'|ID|WORD|INT|WS)[+]
		private @NonNull SerializationRule _12 = new SerializationRule(23,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._09 /* assign V0 = |TextElement::text.'#|,|:'| */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._29 /* V00*TextElement::text+=9|25|10|26 || «? » «value» «? » */
			},
			sl._6,
			new @NonNull EAttribute_EnumerationValues [] {
				new EAttribute_EnumerationValues(MarkupPackage.Literals.TEXT_ELEMENT__TEXT,
					ev._0)
			},
			null,
			null,
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.TEXT_ELEMENT__TEXT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(ev._0, GrammarCardinality.ONE_OR_MORE)
					}
				)
			},
			null);
		// Markup::TextElement : text+=MarkupKeyword
		private @NonNull SerializationRule _13 = new SerializationRule(23,
			new @NonNull SerializationMatchStep @NonNull [] {
				ms._04 /* assert (|TextElement::text| - 1) == 0 */
			},
			new @NonNull SerializationStep @NonNull [] {
				st._23 /* 1*TextElement::text+=14 || «? » «value» «? » */
			},
			sl._6,
			null,
			null,
			new /*@NonNull*/ EAttribute [] {
				MarkupPackage.Literals.TEXT_ELEMENT__TEXT
			},
			new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
				new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.TEXT_ELEMENT__TEXT,
					new @NonNull EnumerationValue_GrammarCardinality [] {
						new EnumerationValue_GrammarCardinality(null, GrammarCardinality.ONE)
					}
				)
			},
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
//	import SerializationMatchTermInteger;
//	import SerializationMatchTermSubtract;
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
//	import MarkupPackage;
