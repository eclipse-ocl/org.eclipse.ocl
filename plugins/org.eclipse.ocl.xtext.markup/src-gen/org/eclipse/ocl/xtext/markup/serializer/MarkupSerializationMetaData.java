/*******************************************************************************
 * Copyright (c) 2011, 2024 Willink Transformations and others.
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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.serializer.AbstractSerializationMetaData;
import org.eclipse.ocl.xtext.base.serializer.EClassValue;
import org.eclipse.ocl.xtext.base.serializer.EClassValue.EReference_TargetGrammarRuleVector;
import org.eclipse.ocl.xtext.base.serializer.EnumerationValue;
import org.eclipse.ocl.xtext.base.serializer.EnumerationValue.EnumerationValueMultiple;
import org.eclipse.ocl.xtext.base.serializer.GrammarCardinality;
import org.eclipse.ocl.xtext.base.serializer.GrammarRuleValue;
import org.eclipse.ocl.xtext.base.serializer.GrammarRuleVector;
import org.eclipse.ocl.xtext.base.serializer.SerializationMatchStep;
import org.eclipse.ocl.xtext.base.serializer.SerializationMatchTerm;
import org.eclipse.ocl.xtext.base.serializer.SerializationMetaData;
import org.eclipse.ocl.xtext.base.serializer.SerializationRule;
import org.eclipse.ocl.xtext.base.serializer.SerializationRule.SerializationFeature;
import org.eclipse.ocl.xtext.base.serializer.SerializationSegment;
import org.eclipse.ocl.xtext.base.serializer.SerializationStep;
import org.eclipse.ocl.xtext.base.serializer.SubstringStep;
import org.eclipse.ocl.xtext.base.serializer.TerminalRuleValue;
import org.eclipse.ocl.xtext.markupcs.MarkupPackage;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.service.GrammarProvider;

/******* This file is 100% auto-generated - do not edit it *******/

/**
 * The MarkupSerializationMetaData singleton provides the metadata to support a
 * model to text serialization of a parsed Xtext semantic model or to re-format an Xtext text node model.
 */
public class MarkupSerializationMetaData extends AbstractSerializationMetaData
{
	/**
	 * The Provider supports injected creation of the MarkupSerializationMetaData singleton.
	 */
	public static class Provider implements SerializationMetaData.Provider
	{
		private static @Nullable MarkupSerializationMetaData INSTANCE = null;

		@Inject
		private GrammarProvider grammarProvider;

		@Override
		public synchronized @NonNull SerializationMetaData get() {
			// synchronized synchronizes the creation of this singleton.
			// It does not imply that the overall application is threadsafe.
			MarkupSerializationMetaData instance = INSTANCE;
			if (instance == null) {
				assert grammarProvider != null;
				Grammar grammar = grammarProvider.getGrammar(Provider.class);
				assert grammar != null;
				INSTANCE = instance = new MarkupSerializationMetaData(grammar);
			}
			return instance;
		}
	}

	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[13];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[2];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[27];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[3];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[15];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[19];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[14];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [5] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[33];
	private final @NonNull SubstringStep @NonNull [] substringSteps = new @NonNull SubstringStep[0];

	private MarkupSerializationMetaData(@NonNull Grammar grammar) {
		super(grammar);
		initGrammarRuleVectors();
		initEnumerationValues();
		initMatchTerms();
		initMatchSteps();
		initSerializationSegments();
		initSerializationSteps();
		initSerializationRules();
		initSubstringSteps();
		initGrammarRuleValues();
		initEClassValues();
	}

	@Override
	public @NonNull EClassValue @NonNull [] getEClassValues() {
		return eClassValues;
	}

	@Override
	public @NonNull EnumerationValue @NonNull [] getEnumerationValues() {
		return enumerationValues;
	}

	@Override
	protected int getFirstGlobalSerializationStepAssignmentIndex() {
		return 0;
	}

	@Override
	protected int getFirstGlobalSerializationStepLiteralIndex() {
		return 11;
	}

	@Override
	public @NonNull GrammarRuleValue @NonNull [] getGrammarRuleValues() {
		return grammarRuleValues;
	}

	@Override
	public @NonNull GrammarRuleVector @NonNull [] getGrammarRuleVectors() {
		return grammarRuleVectors;
	}

	@Override
	protected int getLastGlobalSerializationStepAssignmentIndex() {
		return 10;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 23;
	}

	@Override
	public @Nullable String @Nullable [] getMultipleLineCommentMidfixes() {
		return null;
	}

	@Override
	public @NonNull String @Nullable [] getMultipleLineCommentPrefixes() {
		return null;
	}

	@Override
	public @NonNull String @Nullable [] getMultipleLineCommentSuffixes() {
		return null;
	}

	@Override
	public @NonNull SerializationMatchStep @NonNull [] getSerializationMatchSteps() {
		return serializationMatchSteps;
	}

	@Override
	public @NonNull SerializationMatchTerm @NonNull [] getSerializationMatchTerms() {
		return serializationMatchTerms;
	}

	@Override
	public @NonNull SerializationRule @NonNull [] getSerializationRules() {
		return serializationRules;
	}

	@Override
	public @NonNull SerializationSegment @NonNull [] @NonNull [] getSerializationSegments() {
		return serializationSegments;
	}

	@Override
	public @NonNull SerializationStep @NonNull [] getSerializationSteps() {
		return serializationSteps;
	}

	@Override
	public @NonNull String @Nullable [] getSingleLineCommentPrefixes() {
		return null;
	}

	@Override
	public @NonNull SubstringStep @NonNull [] getSubstringSteps() {
		return substringSteps;
	}

	/**
	 * Initialize configuration for each EClassifier that may be serialized.
	 */
	private void initEClassValues() {
		eClassValues[0] = new EClassValue(MarkupPackage.Literals.BULLET_ELEMENT,
			createSerializationRules(
				0 /* BulletElement-0: 'bullet' (':' BulletElement::level=INT)[V0:?] '[' (CompoundElement::elements+=MarkupElement)[V1:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[1] = new EClassValue(MarkupPackage.Literals.FIGURE_ELEMENT,
			createSerializationRules(
				1 /* FigureElement-0: 'figure' ('#' FigureElement::def=ID)[V0:?] '[' FigureElement::src=STRING (',' FigureElement::alt=STRING (',' FigureElement::requiredWidth=INT (',' FigureElement::requiredHeight=INT)[V3:?])[V2:?])[V1:?] ']' */
			), null
		);
		eClassValues[2] = new EClassValue(MarkupPackage.Literals.FIGURE_REF_ELEMENT,
			createSerializationRules(
				2 /* FigureRefElement-0: 'figureRef' '[' FigureRefElement::ref=ID ']' */
			), null
		);
		eClassValues[3] = new EClassValue(MarkupPackage.Literals.FONT_ELEMENT,
			createSerializationRules(
				3 /* FontElement-0: FontElement::font='b|e' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[4] = new EClassValue(MarkupPackage.Literals.FOOTNOTE_ELEMENT,
			createSerializationRules(
				4 /* FootnoteElement-0: 'footnote' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[5] = new EClassValue(MarkupPackage.Literals.HEADING_ELEMENT,
			createSerializationRules(
				5 /* HeadingElement-0: 'heading' (':' HeadingElement::level=INT)[V0:?] '[' (CompoundElement::elements+=MarkupElement)[V1:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[6] = new EClassValue(MarkupPackage.Literals.MARKUP,
			createSerializationRules(
				6 /* Markup-0: (CompoundElement::elements+=MarkupElement)[V0:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[7] = new EClassValue(MarkupPackage.Literals.NEW_LINE_ELEMENT,
			createSerializationRules(
				7 /* NewLineElement-0: NewLineElement::text=NL */
			), null
		);
		eClassValues[8] = new EClassValue(MarkupPackage.Literals.NULL_ELEMENT,
			createSerializationRules(
				8 /* NullElement-0: '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[9] = new EClassValue(MarkupPackage.Literals.OCL_CODE_ELEMENT,
			createSerializationRules(
				9 /* OCLCodeElement-0: 'oclCode' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[10] = new EClassValue(MarkupPackage.Literals.OCL_EVAL_ELEMENT,
			createSerializationRules(
				10 /* OCLEvalElement-0: 'oclEval' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[11] = new EClassValue(MarkupPackage.Literals.OCL_TEXT_ELEMENT,
			createSerializationRules(
				11 /* OCLTextElement-0: 'oclText' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[12] = new EClassValue(MarkupPackage.Literals.TEXT_ELEMENT,
			createSerializationRules(
				12 /* TextElement-0: (TextElement::text+=ID|WORD|INT|WS)[V0:+] */,
				13 /* TextElement-1: TextElement::text+=MarkupKeyword */
			), null
		);
	}

	/**
	 * Initialize string combinations used by assigned String EAttributes.
	 */
	private void initEnumerationValues() {
		// 0: '#|,|:'
		enumerationValues[0] = new EnumerationValueMultiple(new @NonNull String[]{"#", ",", ":"});
		// 1: 'b|e'
		enumerationValues[1] = new EnumerationValueMultiple(new @NonNull String[]{"b", "e"});
	}

	/**
	 * Initialize the various serialization rules for each grammar rule.
	 */
	private void initGrammarRuleValues() {
		grammarRuleValues[0] = new TerminalRuleValue(0, "ANY_OTHER");
		grammarRuleValues[1] = createParserRuleValue(1, "BulletElement", -1,
			createSerializationRules(
				0	/* BulletElement-0: 'bullet' (':' BulletElement::level=INT)[V0:?] '[' (CompoundElement::elements+=MarkupElement)[V1:*] ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {BulletElement} : [value] | [value] */,
			(0 << 16) | 4	/* "bullet" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 4	/* level=INT : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 2	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* elements+=MarkupElement* : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[2] = new TerminalRuleValue(2, "ESCAPED");
		grammarRuleValues[3] = createParserRuleValue(3, "FigureElement", -1,
			createSerializationRules(
				1	/* FigureElement-0: 'figure' ('#' FigureElement::def=ID)[V0:?] '[' FigureElement::src=STRING (',' FigureElement::alt=STRING (',' FigureElement::requiredWidth=INT (',' FigureElement::requiredHeight=INT)[V3:?])[V2:?])[V1:?] ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 4	/* "figure" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* "#" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 4	/* def=ID : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 2	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 4	/* src=STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 4	/* alt=STRING : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 4	/* requiredWidth=INT : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 4	/* requiredHeight=INT : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[4] = createParserRuleValue(4, "FigureRefElement", -1,
			createSerializationRules(
				2	/* FigureRefElement-0: 'figureRef' '[' FigureRefElement::ref=ID ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 4	/* "figureRef" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 2	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 4	/* ref=ID : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[5] = createParserRuleValue(5, "FontElement", -1,
			createSerializationRules(
				3	/* FontElement-0: FontElement::font='b|e' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 4	/* font=("b"|"e") : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 2	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* elements+=MarkupElement* : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[6] = createParserRuleValue(6, "FootnoteElement", -1,
			createSerializationRules(
				4	/* FootnoteElement-0: 'footnote' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {FootnoteElement} : [value] | [value] */,
			(0 << 16) | 4	/* "footnote" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 2	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* elements+=MarkupElement* : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[7] = new TerminalRuleValue(7, "HORIZONTAL_WS");
		grammarRuleValues[8] = createParserRuleValue(8, "HeadingElement", -1,
			createSerializationRules(
				5	/* HeadingElement-0: 'heading' (':' HeadingElement::level=INT)[V0:?] '[' (CompoundElement::elements+=MarkupElement)[V1:*] ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {HeadingElement} : [value] | [value] */,
			(0 << 16) | 4	/* "heading" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 4	/* ":" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 4	/* level=INT : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 2	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* elements+=MarkupElement* : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[9] = new TerminalRuleValue(9, "ID");
		grammarRuleValues[10] = new TerminalRuleValue(10, "INT");
		grammarRuleValues[11] = new TerminalRuleValue(11, "LETTER");
		grammarRuleValues[12] = createParserRuleValue(12, "Markup", -1,
			createSerializationRules(
				6	/* Markup-0: (CompoundElement::elements+=MarkupElement)[V0:*] */
			),
			(0 << 16) | 0	/* elements+=MarkupElement* : [value] | [value] */
		);
		grammarRuleValues[13] = createParserRuleValue(13, "MarkupElement", 1 /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */,
			createSerializationRules(
				0	/* BulletElement-0: 'bullet' (':' BulletElement::level=INT)[V0:?] '[' (CompoundElement::elements+=MarkupElement)[V1:*] ']' */,
				1	/* FigureElement-0: 'figure' ('#' FigureElement::def=ID)[V0:?] '[' FigureElement::src=STRING (',' FigureElement::alt=STRING (',' FigureElement::requiredWidth=INT (',' FigureElement::requiredHeight=INT)[V3:?])[V2:?])[V1:?] ']' */,
				2	/* FigureRefElement-0: 'figureRef' '[' FigureRefElement::ref=ID ']' */,
				3	/* FontElement-0: FontElement::font='b|e' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */,
				4	/* FootnoteElement-0: 'footnote' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */,
				5	/* HeadingElement-0: 'heading' (':' HeadingElement::level=INT)[V0:?] '[' (CompoundElement::elements+=MarkupElement)[V1:*] ']' */,
				7	/* NewLineElement-0: NewLineElement::text=NL */,
				8	/* NullElement-0: '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */,
				9	/* OCLCodeElement-0: 'oclCode' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */,
				10	/* OCLEvalElement-0: 'oclEval' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */,
				11	/* OCLTextElement-0: 'oclText' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */,
				12	/* TextElement-0: (TextElement::text+=ID|WORD|INT|WS)[V0:+] */,
				13	/* TextElement-1: TextElement::text+=MarkupKeyword */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* FontElement : [value] | [value] */,
			(0 << 16) | 0	/* NewLineElement : [value] | [value] */,
			(0 << 16) | 0	/* BulletElement : [value] | [value] */,
			(0 << 16) | 0	/* FigureElement : [value] | [value] */,
			(0 << 16) | 0	/* FigureRefElement : [value] | [value] */,
			(0 << 16) | 0	/* FootnoteElement : [value] | [value] */,
			(0 << 16) | 0	/* HeadingElement : [value] | [value] */,
			(0 << 16) | 0	/* NullElement : [value] | [value] */,
			(0 << 16) | 0	/* OCLCodeElement : [value] | [value] */,
			(0 << 16) | 0	/* OCLEvalElement : [value] | [value] */,
			(0 << 16) | 0	/* OCLTextElement : [value] | [value] */,
			(0 << 16) | 0	/* TextElement : [value] | [value] */
		);
		grammarRuleValues[14] = createDataTypeRuleValue(14, "MarkupKeyword", 4 /* [soft-space, value, soft-space] */);
		grammarRuleValues[15] = new TerminalRuleValue(15, "NL");
		grammarRuleValues[16] = new TerminalRuleValue(16, "NUMBER");
		grammarRuleValues[17] = createParserRuleValue(17, "NewLineElement", -1,
			createSerializationRules(
				7	/* NewLineElement-0: NewLineElement::text=NL */
			),
			(0 << 16) | 4	/* text=NL : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[18] = createParserRuleValue(18, "NullElement", -1,
			createSerializationRules(
				8	/* NullElement-0: '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {NullElement} : [value] | [value] */,
			(0 << 16) | 2	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* elements+=MarkupElement* : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[19] = createParserRuleValue(19, "OCLCodeElement", -1,
			createSerializationRules(
				9	/* OCLCodeElement-0: 'oclCode' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {OCLCodeElement} : [value] | [value] */,
			(0 << 16) | 4	/* "oclCode" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 2	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* elements+=MarkupElement* : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[20] = createParserRuleValue(20, "OCLEvalElement", -1,
			createSerializationRules(
				10	/* OCLEvalElement-0: 'oclEval' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {OCLEvalElement} : [value] | [value] */,
			(0 << 16) | 4	/* "oclEval" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 2	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* elements+=MarkupElement* : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[21] = createParserRuleValue(21, "OCLTextElement", -1,
			createSerializationRules(
				11	/* OCLTextElement-0: 'oclText' '[' (CompoundElement::elements+=MarkupElement)[V0:*] ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {OCLTextElement} : [value] | [value] */,
			(0 << 16) | 4	/* "oclText" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 2	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* elements+=MarkupElement* : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[22] = new TerminalRuleValue(22, "STRING");
		grammarRuleValues[23] = createParserRuleValue(23, "TextElement", -1,
			createSerializationRules(
				12	/* TextElement-0: (TextElement::text+=ID|WORD|INT|WS)[V0:+] */,
				13	/* TextElement-1: TextElement::text+=MarkupKeyword */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 4	/* text+=(ID|WORD|INT|WS|":"|"#"|",")+ : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 4	/* text+=MarkupKeyword : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[24] = new TerminalRuleValue(24, "VERTICAL_WS");
		grammarRuleValues[25] = new TerminalRuleValue(25, "WORD");
		grammarRuleValues[26] = new TerminalRuleValue(26, "WS");
	}

	/**
	 * Initialize bit vectors of useful grammar rule combinations.
	 */
	private void initGrammarRuleVectors() {
		// 0: MarkupElement
		grammarRuleVectors[0] = new GrammarRuleVector(0x2000L);
		// 1: BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement
		grammarRuleVectors[1] = new GrammarRuleVector(0xbe217aL);
		// 2: ID|INT|WORD|WS
		grammarRuleVectors[2] = new GrammarRuleVector(0x6000600L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// 0: assert (|FigureElement::src| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(14);
		// 1: assert (|FigureRefElement::ref| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(15);
		// 2: assert (|FontElement::font.'b|e'| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(16);
		// 3: assert (|NewLineElement::text| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(17);
		// 4: assert (|TextElement::text| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(18);
		// 5: assign V0 = |BulletElement::level|
		serializationMatchSteps[5] = createMatchStep_Assign(0, 1);
		// 6: assign V0 = |CompoundElement::elements|
		serializationMatchSteps[6] = createMatchStep_Assign(0, 2);
		// 7: assign V0 = |FigureElement::def|
		serializationMatchSteps[7] = createMatchStep_Assign(0, 4);
		// 8: assign V0 = |HeadingElement::level|
		serializationMatchSteps[8] = createMatchStep_Assign(0, 10);
		// 9: assign V0 = |TextElement::text.'#|,|:'|
		serializationMatchSteps[9] = createMatchStep_Assign(0, 12);
		// 10: assign V1 = |CompoundElement::elements|
		serializationMatchSteps[10] = createMatchStep_Assign(1, 2);
		// 11: assign V1 = |FigureElement::alt|
		serializationMatchSteps[11] = createMatchStep_Assign(1, 3);
		// 12: assign V2 = |FigureElement::requiredWidth|
		serializationMatchSteps[12] = createMatchStep_Assign(2, 6);
		// 13: assign V3 = |FigureElement::requiredHeight|
		serializationMatchSteps[13] = createMatchStep_Assign(3, 5);
		// 14: check-rule markupcs::CompoundElement.elements : 1|3|4|5|6|8|13|17|18|19|20|21|23
		serializationMatchSteps[14] = createMatchStep_RuleCheck(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 1/*BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement*/);
	}

	/**
	 * Initialize expression terms used during the matching process.
	 */
	private void initMatchTerms() {
		// 0: 1
		serializationMatchTerms[0] = createSerializationMatchTermInteger(1);
		// 1: |BulletElement::level|
		serializationMatchTerms[1] = createSerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.BULLET_ELEMENT__LEVEL);
		// 2: |CompoundElement::elements|
		serializationMatchTerms[2] = createSerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS);
		// 3: |FigureElement::alt|
		serializationMatchTerms[3] = createSerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__ALT);
		// 4: |FigureElement::def|
		serializationMatchTerms[4] = createSerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__DEF);
		// 5: |FigureElement::requiredHeight|
		serializationMatchTerms[5] = createSerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_HEIGHT);
		// 6: |FigureElement::requiredWidth|
		serializationMatchTerms[6] = createSerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_WIDTH);
		// 7: |FigureElement::src|
		serializationMatchTerms[7] = createSerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__SRC);
		// 8: |FigureRefElement::ref|
		serializationMatchTerms[8] = createSerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF);
		// 9: |FontElement::font.'b|e'|
		serializationMatchTerms[9] = createSerializationMatchTermEAttributeSize(MarkupPackage.Literals.FONT_ELEMENT__FONT, 1 /* 'b|e' */);
		// 10: |HeadingElement::level|
		serializationMatchTerms[10] = createSerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.HEADING_ELEMENT__LEVEL);
		// 11: |NewLineElement::text|
		serializationMatchTerms[11] = createSerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT);
		// 12: |TextElement::text.'#|,|:'|
		serializationMatchTerms[12] = createSerializationMatchTermEAttributeSize(MarkupPackage.Literals.TEXT_ELEMENT__TEXT, 0 /* '#|,|:' */);
		// 13: |TextElement::text|
		serializationMatchTerms[13] = createSerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.TEXT_ELEMENT__TEXT);
		// 14: (|FigureElement::src| - 1)
		serializationMatchTerms[14] = createSerializationMatchTermSubtract(7, 0);
		// 15: (|FigureRefElement::ref| - 1)
		serializationMatchTerms[15] = createSerializationMatchTermSubtract(8, 0);
		// 16: (|FontElement::font.'b|e'| - 1)
		serializationMatchTerms[16] = createSerializationMatchTermSubtract(9, 0);
		// 17: (|NewLineElement::text| - 1)
		serializationMatchTerms[17] = createSerializationMatchTermSubtract(11, 0);
		// 18: (|TextElement::text| - 1)
		serializationMatchTerms[18] = createSerializationMatchTermSubtract(13, 0);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules() {
		// Markup::BulletElement-0(markupcs::BulletElement): { "bullet" { ":" level=INT }[?] "[" elements+=MarkupElement[*] "]" }
		serializationRules[0] = createSerializationRule("BulletElement-0", 1,
			createSerializationMatchSteps(
				14		/* check-rule markupcs::CompoundElement.elements : BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */,
				10		/* assign V1 = |CompoundElement::elements| */,
				5		/* assign V0 = |BulletElement::level| */
			),
			createSerializationSteps(
				16		/* 'bullet' || soft-space value soft-space */,
				26		/* V00*2-steps || value */,
				13		/* ':' || soft-space value soft-space */,
				4		/* BulletElement::level=INT || soft-space value soft-space */,
				14		/* '[' || no-space value no-space */,
				27		/* V01*1-steps || value */,
				2		/* CompoundElement::elements+=MarkupElement || value */,
				15		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(MarkupPackage.Literals.BULLET_ELEMENT__LEVEL, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 0/* MarkupElement */,
					(13/*MarkupElement*/ << 4) | 2 /*[*]*/
				)
			});
		// Markup::FigureElement-0(markupcs::FigureElement): { "figure" { "#" def=ID }[?] "[" src=STRING { "," alt=STRING { "," requiredWidth=INT { "," requiredHeight=INT }[?] }[?] }[?] "]" }
		serializationRules[1] = createSerializationRule("FigureElement-0", 3,
			createSerializationMatchSteps(
				11		/* assign V1 = |FigureElement::alt| */,
				0		/* assert (|FigureElement::src| - 1) == 0 */,
				7		/* assign V0 = |FigureElement::def| */,
				12		/* assign V2 = |FigureElement::requiredWidth| */,
				13		/* assign V3 = |FigureElement::requiredHeight| */
			),
			createSerializationSteps(
				17		/* 'figure' || soft-space value soft-space */,
				26		/* V00*2-steps || value */,
				11		/* '#' || soft-space value soft-space */,
				1		/* FigureElement::def=ID || soft-space value soft-space */,
				14		/* '[' || no-space value no-space */,
				9		/* FigureElement::src=STRING || soft-space value soft-space */,
				28		/* V01*8-steps || value */,
				12		/* ',' || no-space value soft-space */,
				0		/* FigureElement::alt=STRING || soft-space value soft-space */,
				29		/* V02*5-steps || value */,
				12		/* ',' || no-space value soft-space */,
				8		/* FigureElement::requiredWidth=INT || soft-space value soft-space */,
				30		/* V03*2-steps || value */,
				12		/* ',' || no-space value soft-space */,
				7		/* FigureElement::requiredHeight=INT || soft-space value soft-space */,
				15		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(MarkupPackage.Literals.FIGURE_ELEMENT__ALT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(MarkupPackage.Literals.FIGURE_ELEMENT__DEF, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_HEIGHT, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_WIDTH, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationSimpleAttribute(MarkupPackage.Literals.FIGURE_ELEMENT__SRC, true, GrammarCardinality.ONE)
			});
		// Markup::FigureRefElement-0(markupcs::FigureRefElement): { "figureRef" "[" ref=ID "]" }
		serializationRules[2] = createSerializationRule("FigureRefElement-0", 4,
			createSerializationMatchSteps(
				1		/* assert (|FigureRefElement::ref| - 1) == 0 */
			),
			createSerializationSteps(
				18		/* 'figureRef' || soft-space value soft-space */,
				14		/* '[' || no-space value no-space */,
				6		/* FigureRefElement::ref=ID || soft-space value soft-space */,
				15		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF, -1
				)
			});
		// Markup::FontElement-0(markupcs::FontElement): { font={'b|e'} "[" elements+=MarkupElement[*] "]" }
		serializationRules[3] = createSerializationRule("FontElement-0", 5,
			createSerializationMatchSteps(
				14		/* check-rule markupcs::CompoundElement.elements : BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */,
				6		/* assign V0 = |CompoundElement::elements| */,
				2		/* assert (|FontElement::font.'b|e'| - 1) == 0 */
			),
			createSerializationSteps(
				3		/* FontElement::font='b|e' || soft-space value soft-space */,
				14		/* '[' || no-space value no-space */,
				24		/* V00*1-steps || value */,
				2		/* CompoundElement::elements+=MarkupElement || value */,
				15		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(MarkupPackage.Literals.FONT_ELEMENT__FONT, false,
					(1/*'b|e'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 0/* MarkupElement */,
					(13/*MarkupElement*/ << 4) | 2 /*[*]*/
				)
			});
		// Markup::FootnoteElement-0(markupcs::FootnoteElement): { "footnote" "[" elements+=MarkupElement[*] "]" }
		serializationRules[4] = createSerializationRule("FootnoteElement-0", 6,
			createSerializationMatchSteps(
				14		/* check-rule markupcs::CompoundElement.elements : BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */,
				6		/* assign V0 = |CompoundElement::elements| */
			),
			createSerializationSteps(
				19		/* 'footnote' || soft-space value soft-space */,
				14		/* '[' || no-space value no-space */,
				24		/* V00*1-steps || value */,
				2		/* CompoundElement::elements+=MarkupElement || value */,
				15		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 0/* MarkupElement */,
					(13/*MarkupElement*/ << 4) | 2 /*[*]*/
				)
			});
		// Markup::HeadingElement-0(markupcs::HeadingElement): { "heading" { ":" level=INT }[?] "[" elements+=MarkupElement[*] "]" }
		serializationRules[5] = createSerializationRule("HeadingElement-0", 8,
			createSerializationMatchSteps(
				14		/* check-rule markupcs::CompoundElement.elements : BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */,
				10		/* assign V1 = |CompoundElement::elements| */,
				8		/* assign V0 = |HeadingElement::level| */
			),
			createSerializationSteps(
				20		/* 'heading' || soft-space value soft-space */,
				26		/* V00*2-steps || value */,
				13		/* ':' || soft-space value soft-space */,
				5		/* HeadingElement::level=INT || soft-space value soft-space */,
				14		/* '[' || no-space value no-space */,
				27		/* V01*1-steps || value */,
				2		/* CompoundElement::elements+=MarkupElement || value */,
				15		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(MarkupPackage.Literals.HEADING_ELEMENT__LEVEL, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 0/* MarkupElement */,
					(13/*MarkupElement*/ << 4) | 2 /*[*]*/
				)
			});
		// Markup::Markup-0(markupcs::Markup): elements+=MarkupElement[*]
		serializationRules[6] = createSerializationRule("Markup-0", 12,
			createSerializationMatchSteps(
				14		/* check-rule markupcs::CompoundElement.elements : BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */,
				6		/* assign V0 = |CompoundElement::elements| */
			),
			createSerializationSteps(
				24		/* V00*1-steps || value */,
				2		/* CompoundElement::elements+=MarkupElement || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 0/* MarkupElement */,
					(13/*MarkupElement*/ << 4) | 2 /*[*]*/
				)
			});
		// Markup::NewLineElement-0(markupcs::NewLineElement): text=NL
		serializationRules[7] = createSerializationRule("NewLineElement-0", 17,
			createSerializationMatchSteps(
				3		/* assert (|NewLineElement::text| - 1) == 0 */
			),
			createSerializationSteps(
				10		/* NewLineElement::text=NL || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT, true, GrammarCardinality.ONE)
			});
		// Markup::NullElement-0(markupcs::NullElement): { "[" elements+=MarkupElement[*] "]" }
		serializationRules[8] = createSerializationRule("NullElement-0", 18,
			createSerializationMatchSteps(
				14		/* check-rule markupcs::CompoundElement.elements : BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */,
				6		/* assign V0 = |CompoundElement::elements| */
			),
			createSerializationSteps(
				14		/* '[' || no-space value no-space */,
				24		/* V00*1-steps || value */,
				2		/* CompoundElement::elements+=MarkupElement || value */,
				15		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 0/* MarkupElement */,
					(13/*MarkupElement*/ << 4) | 2 /*[*]*/
				)
			});
		// Markup::OCLCodeElement-0(markupcs::OCLCodeElement): { "oclCode" "[" elements+=MarkupElement[*] "]" }
		serializationRules[9] = createSerializationRule("OCLCodeElement-0", 19,
			createSerializationMatchSteps(
				14		/* check-rule markupcs::CompoundElement.elements : BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */,
				6		/* assign V0 = |CompoundElement::elements| */
			),
			createSerializationSteps(
				21		/* 'oclCode' || soft-space value soft-space */,
				14		/* '[' || no-space value no-space */,
				24		/* V00*1-steps || value */,
				2		/* CompoundElement::elements+=MarkupElement || value */,
				15		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 0/* MarkupElement */,
					(13/*MarkupElement*/ << 4) | 2 /*[*]*/
				)
			});
		// Markup::OCLEvalElement-0(markupcs::OCLEvalElement): { "oclEval" "[" elements+=MarkupElement[*] "]" }
		serializationRules[10] = createSerializationRule("OCLEvalElement-0", 20,
			createSerializationMatchSteps(
				14		/* check-rule markupcs::CompoundElement.elements : BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */,
				6		/* assign V0 = |CompoundElement::elements| */
			),
			createSerializationSteps(
				22		/* 'oclEval' || soft-space value soft-space */,
				14		/* '[' || no-space value no-space */,
				24		/* V00*1-steps || value */,
				2		/* CompoundElement::elements+=MarkupElement || value */,
				15		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 0/* MarkupElement */,
					(13/*MarkupElement*/ << 4) | 2 /*[*]*/
				)
			});
		// Markup::OCLTextElement-0(markupcs::OCLTextElement): { "oclText" "[" elements+=MarkupElement[*] "]" }
		serializationRules[11] = createSerializationRule("OCLTextElement-0", 21,
			createSerializationMatchSteps(
				14		/* check-rule markupcs::CompoundElement.elements : BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */,
				6		/* assign V0 = |CompoundElement::elements| */
			),
			createSerializationSteps(
				23		/* 'oclText' || soft-space value soft-space */,
				14		/* '[' || no-space value no-space */,
				24		/* V00*1-steps || value */,
				2		/* CompoundElement::elements+=MarkupElement || value */,
				15		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 0/* MarkupElement */,
					(13/*MarkupElement*/ << 4) | 2 /*[*]*/
				)
			});
		// Markup::TextElement-0(markupcs::TextElement): text+=('#|,|:'|ID|WORD|INT|WS)[+]
		serializationRules[12] = createSerializationRule("TextElement-0", 23,
			createSerializationMatchSteps(
				9		/* assign V0 = |TextElement::text.'#|,|:'| */
			),
			createSerializationSteps(
				25		/* V00*1-steps || value */,
				32		/* TextElement::text+=ID|WORD|INT|WS || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(MarkupPackage.Literals.TEXT_ELEMENT__TEXT, false,
					(0/*'#|,|:'*/ << 4) | 3 /*[+]*/
				)
			});
		// Markup::TextElement-1(markupcs::TextElement): text+=MarkupKeyword
		serializationRules[13] = createSerializationRule("TextElement-1", 23,
			createSerializationMatchSteps(
				4		/* assert (|TextElement::text| - 1) == 0 */
			),
			createSerializationSteps(
				31		/* TextElement::text+=MarkupKeyword || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(MarkupPackage.Literals.TEXT_ELEMENT__TEXT, true, GrammarCardinality.ONE)
			});
	}

	/**
	 * Initialize the various string segment sequences that may be used to serialize a serialization term.
	 */
	private void initSerializationSegments() {
		serializationSegments[0] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.VALUE /* value */
		};
		serializationSegments[1] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */
		};
		serializationSegments[2] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.NO_SPACE /* no-space */
		};
		serializationSegments[3] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[4] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
	}

	/**
	 * Initialize the various serialization steps used to serialize a serialization rule.
	 */
	private void initSerializationSteps() {
		// 0: FigureElement::alt=STRING || soft-space value soft-space
		serializationSteps[0] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.FIGURE_ELEMENT__ALT, 22 /*STRING*/, 4);
		// 1: FigureElement::def=ID || soft-space value soft-space
		serializationSteps[1] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.FIGURE_ELEMENT__DEF, 9 /*ID*/, 4);
		// 2: CompoundElement::elements+=MarkupElement || value
		serializationSteps[2] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 13 /*MarkupElement*/, 0);
		// 3: FontElement::font='b|e' || soft-space value soft-space
		serializationSteps[3] = createSerializationStepAssignKeyword(MarkupPackage.Literals.FONT_ELEMENT__FONT, 1 /* 'b|e' */, 4);
		// 4: BulletElement::level=INT || soft-space value soft-space
		serializationSteps[4] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.BULLET_ELEMENT__LEVEL, 10 /*INT*/, 4);
		// 5: HeadingElement::level=INT || soft-space value soft-space
		serializationSteps[5] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.HEADING_ELEMENT__LEVEL, 10 /*INT*/, 4);
		// 6: FigureRefElement::ref=ID || soft-space value soft-space
		serializationSteps[6] = createSerializationStepCrossReference(MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF, getCrossReference(MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF, "ID"), 9, 4);
		// 7: FigureElement::requiredHeight=INT || soft-space value soft-space
		serializationSteps[7] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_HEIGHT, 10 /*INT*/, 4);
		// 8: FigureElement::requiredWidth=INT || soft-space value soft-space
		serializationSteps[8] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_WIDTH, 10 /*INT*/, 4);
		// 9: FigureElement::src=STRING || soft-space value soft-space
		serializationSteps[9] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.FIGURE_ELEMENT__SRC, 22 /*STRING*/, 4);
		// 10: NewLineElement::text=NL || soft-space value soft-space
		serializationSteps[10] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT, 15 /*NL*/, 4);
		// 11: '#' || soft-space value soft-space
		serializationSteps[11] = createSerializationStepKeyword("#", 4);
		// 12: ',' || no-space value soft-space
		serializationSteps[12] = createSerializationStepKeyword(",", 3);
		// 13: ':' || soft-space value soft-space
		serializationSteps[13] = createSerializationStepKeyword(":", 4);
		// 14: '[' || no-space value no-space
		serializationSteps[14] = createSerializationStepKeyword("[", 2);
		// 15: ']' || no-space value
		serializationSteps[15] = createSerializationStepKeyword("]", 1);
		// 16: 'bullet' || soft-space value soft-space
		serializationSteps[16] = createSerializationStepKeyword("bullet", 4);
		// 17: 'figure' || soft-space value soft-space
		serializationSteps[17] = createSerializationStepKeyword("figure", 4);
		// 18: 'figureRef' || soft-space value soft-space
		serializationSteps[18] = createSerializationStepKeyword("figureRef", 4);
		// 19: 'footnote' || soft-space value soft-space
		serializationSteps[19] = createSerializationStepKeyword("footnote", 4);
		// 20: 'heading' || soft-space value soft-space
		serializationSteps[20] = createSerializationStepKeyword("heading", 4);
		// 21: 'oclCode' || soft-space value soft-space
		serializationSteps[21] = createSerializationStepKeyword("oclCode", 4);
		// 22: 'oclEval' || soft-space value soft-space
		serializationSteps[22] = createSerializationStepKeyword("oclEval", 4);
		// 23: 'oclText' || soft-space value soft-space
		serializationSteps[23] = createSerializationStepKeyword("oclText", 4);
		// 24: V00*1-steps || value
		serializationSteps[24] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 1, 0);
		// 25: V00*1-steps || value
		serializationSteps[25] = createSerializationStepSequence((0/*V0*/ << 4) | 3/*[+]*/, 1, 0);
		// 26: V00*2-steps || value
		serializationSteps[26] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 2, 0);
		// 27: V01*1-steps || value
		serializationSteps[27] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 1, 0);
		// 28: V01*8-steps || value
		serializationSteps[28] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 8, 0);
		// 29: V02*5-steps || value
		serializationSteps[29] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 5, 0);
		// 30: V03*2-steps || value
		serializationSteps[30] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 2, 0);
		// 31: TextElement::text+=MarkupKeyword || soft-space value soft-space
		serializationSteps[31] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.TEXT_ELEMENT__TEXT, 14 /*MarkupKeyword*/, 4);
		// 32: TextElement::text+=ID|WORD|INT|WS || soft-space value soft-space
		serializationSteps[32] = createSerializationStepAssigns(MarkupPackage.Literals.TEXT_ELEMENT__TEXT, 0 /* '#|,|:' */, new int[] { 9/*ID*/,25/*WORD*/,10/*INT*/,26/*WS*/}, 4);
	}

	/**
	 * Initialize the various serialization steps used to serialize a serialization rule.
	 */
	private void initSubstringSteps() {
	}
}

//	Commented imports ensure the Xtend synthesis provides a true import allowing unqualified annotated usage
//	import Inject;
//	import NonNull;
//	import Nullable;
//	import EClassValue;
//	import EReference_TargetGrammarRuleVector;
//	import EnumerationValue;
//	import EnumerationValueMultiple;
//	import GrammarCardinality;
//	import GrammarRuleValue;
//	import GrammarRuleVector;
//	import SerializationMatchStep;
//	import SerializationMatchTerm;
//	import SerializationMetaData;
//	import SerializationRule;
//	import SerializationFeature;
//	import SerializationSegment;
//	import SerializationStep;
//	import SubstringStep;
//	import TerminalRuleValue;
//	import MarkupPackage;
//	import Grammar;
//	import GrammarProvider;
