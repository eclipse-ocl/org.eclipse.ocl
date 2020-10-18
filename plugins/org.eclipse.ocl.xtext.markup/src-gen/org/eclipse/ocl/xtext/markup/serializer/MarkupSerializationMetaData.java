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
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueMultiple;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEStructuralFeatureSize;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermInteger;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EAttribute_EnumerationValue_GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EAttribute_EnumerationValues;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EReference_RuleIndex_GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EReference_RuleIndexes;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EnumerationValue_GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.RuleIndex_GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.TerminalRuleValue;
import org.eclipse.ocl.xtext.markupcs.MarkupPackage;

public class MarkupSerializationMetaData extends AbstractSerializationMetaData
{
	private boolean initialized = false;
	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[13];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[2];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[27];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[3];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[15];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[19];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[14];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [5] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[33];


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

	/**
	 * Post constructor/injection initialization to avoid recursions.
	 */
	@Inject
	public void init() {
		if (!initialized) {
			initialized = true;
			initGrammarRuleVectors();
			initEnumerationValues();
			initMatchTerms();
			initMatchSteps();
			initSerializationSegments();
			initSerializationSteps();
			initSerializationRules();
			initGrammarRuleValues();
			initEClassValues();
		}
	}

	/**
	 * Initialize configuration for each EClass that may be serialized.
	 */
	private void initEClassValues() {
		eClassValues[0] = new EClassValue(MarkupPackage.Literals.BULLET_ELEMENT,
			createSerializationRules(
				0 /* { "bullet" { ":" level=INT }[?] "[" elements+=MarkupElement[*] "]" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[1] = new EClassValue(MarkupPackage.Literals.FIGURE_ELEMENT,
			createSerializationRules(
				1 /* { "figure" { "#" def=ID }[?] "[" src=STRING { "," alt=STRING { "," requiredWidth=INT { "," requiredHeight=INT }[?] }[?] }[?] "]" } */
			), null
		);
		eClassValues[2] = new EClassValue(MarkupPackage.Literals.FIGURE_REF_ELEMENT,
			createSerializationRules(
				2 /* { "figureRef" "[" ref=ID "]" } */
			), null
		);
		eClassValues[3] = new EClassValue(MarkupPackage.Literals.FONT_ELEMENT,
			createSerializationRules(
				3 /* { font={'b|e'} "[" elements+=MarkupElement[*] "]" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[4] = new EClassValue(MarkupPackage.Literals.FOOTNOTE_ELEMENT,
			createSerializationRules(
				4 /* { "footnote" "[" elements+=MarkupElement[*] "]" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[5] = new EClassValue(MarkupPackage.Literals.HEADING_ELEMENT,
			createSerializationRules(
				5 /* { "heading" { ":" level=INT }[?] "[" elements+=MarkupElement[*] "]" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[6] = new EClassValue(MarkupPackage.Literals.MARKUP,
			createSerializationRules(
				6 /* elements+=MarkupElement[*] */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[7] = new EClassValue(MarkupPackage.Literals.NEW_LINE_ELEMENT,
			createSerializationRules(
				7 /* text=NL */
			), null
		);
		eClassValues[8] = new EClassValue(MarkupPackage.Literals.NULL_ELEMENT,
			createSerializationRules(
				8 /* { "[" elements+=MarkupElement[*] "]" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[9] = new EClassValue(MarkupPackage.Literals.OCL_CODE_ELEMENT,
			createSerializationRules(
				9 /* { "oclCode" "[" elements+=MarkupElement[*] "]" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[10] = new EClassValue(MarkupPackage.Literals.OCL_EVAL_ELEMENT,
			createSerializationRules(
				10 /* { "oclEval" "[" elements+=MarkupElement[*] "]" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[11] = new EClassValue(MarkupPackage.Literals.OCL_TEXT_ELEMENT,
			createSerializationRules(
				11 /* { "oclText" "[" elements+=MarkupElement[*] "]" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
					1) /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */
			}
		);
		eClassValues[12] = new EClassValue(MarkupPackage.Literals.TEXT_ELEMENT,
			createSerializationRules(
				12 /* text+=('#|,|:'|ID|WORD|INT|WS)[+] */,
				13 /* text+=MarkupKeyword */
			), null
		);
	}

	/**
	 * Initialize string combinations used by assigned String EAttributes.
	 */
	private void initEnumerationValues() {
		// '#|,|:'
		enumerationValues[0] = new EnumerationValueMultiple(new @NonNull String[]{"#", ",", ":"});
		// 'b|e'
		enumerationValues[1] = new EnumerationValueMultiple(new @NonNull String[]{"b", "e"});
	}

	/**
	 * Initialize the various serialization rules for each grammar rule.
	 */
	private void initGrammarRuleValues() {
		grammarRuleValues[0] = new TerminalRuleValue(0, "ANY_OTHER");
		grammarRuleValues[1] = createParserRuleValue(1, "BulletElement", -1,
			createSerializationRules(
				0	/* BulletElement: { "bullet" { ":" level=INT }[?] "[" elements+=MarkupElement[*] "]" } */
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
				1	/* FigureElement: { "figure" { "#" def=ID }[?] "[" src=STRING { "," alt=STRING { "," requiredWidth=INT { "," requiredHeight=INT }[?] }[?] }[?] "]" } */
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
				2	/* FigureRefElement: { "figureRef" "[" ref=ID "]" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 4	/* "figureRef" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 2	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 4	/* ref=ID : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[5] = createParserRuleValue(5, "FontElement", -1,
			createSerializationRules(
				3	/* FontElement: { font={'b|e'} "[" elements+=MarkupElement[*] "]" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 4	/* font=("b"|"e") : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 2	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* elements+=MarkupElement* : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[6] = createParserRuleValue(6, "FootnoteElement", -1,
			createSerializationRules(
				4	/* FootnoteElement: { "footnote" "[" elements+=MarkupElement[*] "]" } */
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
				5	/* HeadingElement: { "heading" { ":" level=INT }[?] "[" elements+=MarkupElement[*] "]" } */
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
				6	/* Markup: elements+=MarkupElement[*] */
			),
			(0 << 16) | 0	/* elements+=MarkupElement* : [value] | [value] */
		);
		grammarRuleValues[13] = createParserRuleValue(13, "MarkupElement", 1 /* BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement */,
			createSerializationRules(
				0	/* BulletElement: { "bullet" { ":" level=INT }[?] "[" elements+=MarkupElement[*] "]" } */,
				1	/* FigureElement: { "figure" { "#" def=ID }[?] "[" src=STRING { "," alt=STRING { "," requiredWidth=INT { "," requiredHeight=INT }[?] }[?] }[?] "]" } */,
				2	/* FigureRefElement: { "figureRef" "[" ref=ID "]" } */,
				3	/* FontElement: { font={'b|e'} "[" elements+=MarkupElement[*] "]" } */,
				4	/* FootnoteElement: { "footnote" "[" elements+=MarkupElement[*] "]" } */,
				5	/* HeadingElement: { "heading" { ":" level=INT }[?] "[" elements+=MarkupElement[*] "]" } */,
				7	/* NewLineElement: text=NL */,
				8	/* NullElement: { "[" elements+=MarkupElement[*] "]" } */,
				9	/* OCLCodeElement: { "oclCode" "[" elements+=MarkupElement[*] "]" } */,
				10	/* OCLEvalElement: { "oclEval" "[" elements+=MarkupElement[*] "]" } */,
				11	/* OCLTextElement: { "oclText" "[" elements+=MarkupElement[*] "]" } */,
				12	/* TextElement: text+=('#|,|:'|ID|WORD|INT|WS)[+] */,
				13	/* TextElement: text+=MarkupKeyword */
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
		grammarRuleValues[14] = new DataTypeRuleValue(14, "MarkupKeyword");
		grammarRuleValues[15] = new TerminalRuleValue(15, "NL");
		grammarRuleValues[16] = new TerminalRuleValue(16, "NUMBER");
		grammarRuleValues[17] = createParserRuleValue(17, "NewLineElement", -1,
			createSerializationRules(
				7	/* NewLineElement: text=NL */
			),
			(0 << 16) | 4	/* text=NL : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[18] = createParserRuleValue(18, "NullElement", -1,
			createSerializationRules(
				8	/* NullElement: { "[" elements+=MarkupElement[*] "]" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {NullElement} : [value] | [value] */,
			(0 << 16) | 2	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* elements+=MarkupElement* : [value] | [value] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[19] = createParserRuleValue(19, "OCLCodeElement", -1,
			createSerializationRules(
				9	/* OCLCodeElement: { "oclCode" "[" elements+=MarkupElement[*] "]" } */
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
				10	/* OCLEvalElement: { "oclEval" "[" elements+=MarkupElement[*] "]" } */
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
				11	/* OCLTextElement: { "oclText" "[" elements+=MarkupElement[*] "]" } */
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
				12	/* TextElement: text+=('#|,|:'|ID|WORD|INT|WS)[+] */,
				13	/* TextElement: text+=MarkupKeyword */
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
		// MarkupElement
		grammarRuleVectors[0] = new GrammarRuleVector(0x2000L);
		// BulletElement|FigureElement|FigureRefElement|FontElement|FootnoteElement|HeadingElement|MarkupElement|NewLineElement|NullElement|OCLCodeElement|OCLEvalElement|OCLTextElement|TextElement
		grammarRuleVectors[1] = new GrammarRuleVector(0xbe217aL);
		// ID|INT|WORD|WS
		grammarRuleVectors[2] = new GrammarRuleVector(0x6000600L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// assert (|FigureElement::src| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(14);
		// assert (|FigureRefElement::ref| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(15);
		// assert (|FontElement::font.'b|e'| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(16);
		// assert (|NewLineElement::text| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(17);
		// assert (|TextElement::text| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(18);
		// assign V0 = |BulletElement::level|
		serializationMatchSteps[5] = createMatchStep_Assign(0, 1);
		// assign V0 = |CompoundElement::elements|
		serializationMatchSteps[6] = createMatchStep_Assign(0, 2);
		// assign V0 = |FigureElement::def|
		serializationMatchSteps[7] = createMatchStep_Assign(0, 4);
		// assign V0 = |HeadingElement::level|
		serializationMatchSteps[8] = createMatchStep_Assign(0, 10);
		// assign V0 = |TextElement::text.'#|,|:'|
		serializationMatchSteps[9] = createMatchStep_Assign(0, 12);
		// assign V1 = |CompoundElement::elements|
		serializationMatchSteps[10] = createMatchStep_Assign(1, 2);
		// assign V1 = |FigureElement::alt|
		serializationMatchSteps[11] = createMatchStep_Assign(1, 3);
		// assign V2 = |FigureElement::requiredWidth|
		serializationMatchSteps[12] = createMatchStep_Assign(2, 6);
		// assign V3 = |FigureElement::requiredHeight|
		serializationMatchSteps[13] = createMatchStep_Assign(3, 5);
		// check-rule markupcs::CompoundElement.elements : 13
		serializationMatchSteps[14] = createMatchStep_RuleCheck(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 0/*MarkupElement*/);
	}

	/**
	 * Initialize expression terms used during the matching process.
	 */
	private void initMatchTerms() {
		// 1
		serializationMatchTerms[0] = new SerializationMatchTermInteger(1);
		// |BulletElement::level|
		serializationMatchTerms[1] = new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.BULLET_ELEMENT__LEVEL);
		// |CompoundElement::elements|
		serializationMatchTerms[2] = new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS);
		// |FigureElement::alt|
		serializationMatchTerms[3] = new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__ALT);
		// |FigureElement::def|
		serializationMatchTerms[4] = new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__DEF);
		// |FigureElement::requiredHeight|
		serializationMatchTerms[5] = new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_HEIGHT);
		// |FigureElement::requiredWidth|
		serializationMatchTerms[6] = new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_WIDTH);
		// |FigureElement::src|
		serializationMatchTerms[7] = new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_ELEMENT__SRC);
		// |FigureRefElement::ref|
		serializationMatchTerms[8] = new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF);
		// |FontElement::font.'b|e'|
		serializationMatchTerms[9] = createSerializationMatchTermEAttributeSize(MarkupPackage.Literals.FONT_ELEMENT__FONT, 1 /* 'b|e' */);
		// |HeadingElement::level|
		serializationMatchTerms[10] = new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.HEADING_ELEMENT__LEVEL);
		// |NewLineElement::text|
		serializationMatchTerms[11] = new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT);
		// |TextElement::text.'#|,|:'|
		serializationMatchTerms[12] = createSerializationMatchTermEAttributeSize(MarkupPackage.Literals.TEXT_ELEMENT__TEXT, 0 /* '#|,|:' */);
		// |TextElement::text|
		serializationMatchTerms[13] = new SerializationMatchTermEStructuralFeatureSize(MarkupPackage.Literals.TEXT_ELEMENT__TEXT);
		// (|FigureElement::src| - 1)
		serializationMatchTerms[14] = createSerializationMatchTermSubtract(7, 0);
		// (|FigureRefElement::ref| - 1)
		serializationMatchTerms[15] = createSerializationMatchTermSubtract(8, 0);
		// (|FontElement::font.'b|e'| - 1)
		serializationMatchTerms[16] = createSerializationMatchTermSubtract(9, 0);
		// (|NewLineElement::text| - 1)
		serializationMatchTerms[17] = createSerializationMatchTermSubtract(11, 0);
		// (|TextElement::text| - 1)
		serializationMatchTerms[18] = createSerializationMatchTermSubtract(13, 0);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules() {
		// Markup::BulletElement(markupcs::BulletElement): { "bullet" { ":" level=INT }[?] "[" elements+=MarkupElement[*] "]" }
		serializationRules[0] =
			new SerializationRule("BulletElement", 1,
				createSerializationMatchSteps(
					14		/* check-rule markupcs::CompoundElement.elements : 13 */,
					10		/* assign V1 = |CompoundElement::elements| */,
					5		/* assign V0 = |BulletElement::level| */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					16		/* 'bullet' || soft-space value soft-space */,
					26		/* V00*4-steps || value */,
					24		/* 1*1-steps || value */,
					13		/* ':' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					4		/* BulletElement::level=10 || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					14		/* '[' || no-space value no-space */,
					27		/* V01*1-steps || value */,
					2		/* CompoundElement::elements+=13 || value */,
					24		/* 1*1-steps || value */,
					15		/* ']' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						0) /* MarkupElement */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.BULLET_ELEMENT__LEVEL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
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
			;
		// Markup::FigureElement(markupcs::FigureElement): { "figure" { "#" def=ID }[?] "[" src=STRING { "," alt=STRING { "," requiredWidth=INT { "," requiredHeight=INT }[?] }[?] }[?] "]" }
		serializationRules[1] =
			new SerializationRule("FigureElement", 3,
				createSerializationMatchSteps(
					11		/* assign V1 = |FigureElement::alt| */,
					0		/* assert (|FigureElement::src| - 1) == 0 */,
					7		/* assign V0 = |FigureElement::def| */,
					12		/* assign V2 = |FigureElement::requiredWidth| */,
					13		/* assign V3 = |FigureElement::requiredHeight| */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					17		/* 'figure' || soft-space value soft-space */,
					26		/* V00*4-steps || value */,
					24		/* 1*1-steps || value */,
					11		/* '#' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					1		/* FigureElement::def=9 || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					14		/* '[' || no-space value no-space */,
					24		/* 1*1-steps || value */,
					9		/* FigureElement::src=22 || soft-space value soft-space */,
					28		/* V01*14-steps || value */,
					24		/* 1*1-steps || value */,
					12		/* ',' || no-space value soft-space */,
					24		/* 1*1-steps || value */,
					0		/* FigureElement::alt=22 || soft-space value soft-space */,
					29		/* V02*9-steps || value */,
					24		/* 1*1-steps || value */,
					12		/* ',' || no-space value soft-space */,
					24		/* 1*1-steps || value */,
					8		/* FigureElement::requiredWidth=10 || soft-space value soft-space */,
					30		/* V03*4-steps || value */,
					24		/* 1*1-steps || value */,
					12		/* ',' || no-space value soft-space */,
					24		/* 1*1-steps || value */,
					7		/* FigureElement::requiredHeight=10 || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					15		/* ']' || no-space value */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					MarkupPackage.Literals.FIGURE_ELEMENT__SRC
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__ALT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__DEF,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_HEIGHT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_WIDTH,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.FIGURE_ELEMENT__SRC,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// Markup::FigureRefElement(markupcs::FigureRefElement): { "figureRef" "[" ref=ID "]" }
		serializationRules[2] =
			new SerializationRule("FigureRefElement", 4,
				createSerializationMatchSteps(
					1		/* assert (|FigureRefElement::ref| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					18		/* 'figureRef' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					14		/* '[' || no-space value no-space */,
					24		/* 1*1-steps || value */,
					6		/* FigureRefElement::ref=ID || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					15		/* ']' || no-space value */
				),
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
			;
		// Markup::FontElement(markupcs::FontElement): { font={'b|e'} "[" elements+=MarkupElement[*] "]" }
		serializationRules[3] =
			new SerializationRule("FontElement", 5,
				createSerializationMatchSteps(
					14		/* check-rule markupcs::CompoundElement.elements : 13 */,
					6		/* assign V0 = |CompoundElement::elements| */,
					2		/* assert (|FontElement::font.'b|e'| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					3		/* FontElement::font='b|e' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					14		/* '[' || no-space value no-space */,
					25		/* V00*1-steps || value */,
					2		/* CompoundElement::elements+=13 || value */,
					24		/* 1*1-steps || value */,
					15		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(MarkupPackage.Literals.FONT_ELEMENT__FONT,
						1	/* 'b|e' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						0) /* MarkupElement */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.FONT_ELEMENT__FONT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* 'b|e' */, GrammarCardinality.ONE)
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
			;
		// Markup::FootnoteElement(markupcs::FootnoteElement): { "footnote" "[" elements+=MarkupElement[*] "]" }
		serializationRules[4] =
			new SerializationRule("FootnoteElement", 6,
				createSerializationMatchSteps(
					14		/* check-rule markupcs::CompoundElement.elements : 13 */,
					6		/* assign V0 = |CompoundElement::elements| */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					19		/* 'footnote' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					14		/* '[' || no-space value no-space */,
					25		/* V00*1-steps || value */,
					2		/* CompoundElement::elements+=13 || value */,
					24		/* 1*1-steps || value */,
					15		/* ']' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						0) /* MarkupElement */
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
			;
		// Markup::HeadingElement(markupcs::HeadingElement): { "heading" { ":" level=INT }[?] "[" elements+=MarkupElement[*] "]" }
		serializationRules[5] =
			new SerializationRule("HeadingElement", 8,
				createSerializationMatchSteps(
					14		/* check-rule markupcs::CompoundElement.elements : 13 */,
					10		/* assign V1 = |CompoundElement::elements| */,
					8		/* assign V0 = |HeadingElement::level| */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					20		/* 'heading' || soft-space value soft-space */,
					26		/* V00*4-steps || value */,
					24		/* 1*1-steps || value */,
					13		/* ':' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					5		/* HeadingElement::level=10 || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					14		/* '[' || no-space value no-space */,
					27		/* V01*1-steps || value */,
					2		/* CompoundElement::elements+=13 || value */,
					24		/* 1*1-steps || value */,
					15		/* ']' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						0) /* MarkupElement */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.HEADING_ELEMENT__LEVEL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
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
			;
		// Markup::Markup(markupcs::Markup): elements+=MarkupElement[*]
		serializationRules[6] =
			new SerializationRule("Markup", 12,
				createSerializationMatchSteps(
					14		/* check-rule markupcs::CompoundElement.elements : 13 */,
					6		/* assign V0 = |CompoundElement::elements| */
				),
				createSerializationSteps(
					25		/* V00*1-steps || value */,
					2		/* CompoundElement::elements+=13 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						0) /* MarkupElement */
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
			;
		// Markup::NewLineElement(markupcs::NewLineElement): text=NL
		serializationRules[7] =
			new SerializationRule("NewLineElement", 17,
				createSerializationMatchSteps(
					3		/* assert (|NewLineElement::text| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					10		/* NewLineElement::text=15 || soft-space value soft-space */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// Markup::NullElement(markupcs::NullElement): { "[" elements+=MarkupElement[*] "]" }
		serializationRules[8] =
			new SerializationRule("NullElement", 18,
				createSerializationMatchSteps(
					14		/* check-rule markupcs::CompoundElement.elements : 13 */,
					6		/* assign V0 = |CompoundElement::elements| */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					14		/* '[' || no-space value no-space */,
					25		/* V00*1-steps || value */,
					2		/* CompoundElement::elements+=13 || value */,
					24		/* 1*1-steps || value */,
					15		/* ']' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						0) /* MarkupElement */
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
			;
		// Markup::OCLCodeElement(markupcs::OCLCodeElement): { "oclCode" "[" elements+=MarkupElement[*] "]" }
		serializationRules[9] =
			new SerializationRule("OCLCodeElement", 19,
				createSerializationMatchSteps(
					14		/* check-rule markupcs::CompoundElement.elements : 13 */,
					6		/* assign V0 = |CompoundElement::elements| */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					21		/* 'oclCode' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					14		/* '[' || no-space value no-space */,
					25		/* V00*1-steps || value */,
					2		/* CompoundElement::elements+=13 || value */,
					24		/* 1*1-steps || value */,
					15		/* ']' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						0) /* MarkupElement */
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
			;
		// Markup::OCLEvalElement(markupcs::OCLEvalElement): { "oclEval" "[" elements+=MarkupElement[*] "]" }
		serializationRules[10] =
			new SerializationRule("OCLEvalElement", 20,
				createSerializationMatchSteps(
					14		/* check-rule markupcs::CompoundElement.elements : 13 */,
					6		/* assign V0 = |CompoundElement::elements| */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					22		/* 'oclEval' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					14		/* '[' || no-space value no-space */,
					25		/* V00*1-steps || value */,
					2		/* CompoundElement::elements+=13 || value */,
					24		/* 1*1-steps || value */,
					15		/* ']' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						0) /* MarkupElement */
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
			;
		// Markup::OCLTextElement(markupcs::OCLTextElement): { "oclText" "[" elements+=MarkupElement[*] "]" }
		serializationRules[11] =
			new SerializationRule("OCLTextElement", 21,
				createSerializationMatchSteps(
					14		/* check-rule markupcs::CompoundElement.elements : 13 */,
					6		/* assign V0 = |CompoundElement::elements| */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					23		/* 'oclText' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					14		/* '[' || no-space value no-space */,
					25		/* V00*1-steps || value */,
					2		/* CompoundElement::elements+=13 || value */,
					24		/* 1*1-steps || value */,
					15		/* ']' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS,
						0) /* MarkupElement */
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
			;
		// Markup::TextElement(markupcs::TextElement): text+=('#|,|:'|ID|WORD|INT|WS)[+]
		serializationRules[12] =
			new SerializationRule("TextElement", 23,
				createSerializationMatchSteps(
					9		/* assign V0 = |TextElement::text.'#|,|:'| */
				),
				createSerializationSteps(
					25		/* V00*1-steps || value */,
					32		/* TextElement::text+=9|25|10|26 || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(MarkupPackage.Literals.TEXT_ELEMENT__TEXT,
						0	/* '#|,|:' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.TEXT_ELEMENT__TEXT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(0 /* '#|,|:' */, GrammarCardinality.ONE_OR_MORE)
						}
					)
				},
				null);
			;
		// Markup::TextElement(markupcs::TextElement): text+=MarkupKeyword
		serializationRules[13] =
			new SerializationRule("TextElement", 23,
				createSerializationMatchSteps(
					4		/* assert (|TextElement::text| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					31		/* TextElement::text+=14 || soft-space value soft-space */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					MarkupPackage.Literals.TEXT_ELEMENT__TEXT
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(MarkupPackage.Literals.TEXT_ELEMENT__TEXT,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
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
		// FigureElement::alt=22 || soft-space value soft-space
		serializationSteps[0] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.FIGURE_ELEMENT__ALT, 22 /*STRING*/, 4);
		// FigureElement::def=9 || soft-space value soft-space
		serializationSteps[1] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.FIGURE_ELEMENT__DEF, 9 /*ID*/, 4);
		// CompoundElement::elements+=13 || value
		serializationSteps[2] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, 13 /*MarkupElement*/, 0);
		// FontElement::font='b|e' || soft-space value soft-space
		serializationSteps[3] = createSerializationStepAssignKeyword(MarkupPackage.Literals.FONT_ELEMENT__FONT, 1 /* 'b|e' */, 4);
		// BulletElement::level=10 || soft-space value soft-space
		serializationSteps[4] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.BULLET_ELEMENT__LEVEL, 10 /*INT*/, 4);
		// HeadingElement::level=10 || soft-space value soft-space
		serializationSteps[5] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.HEADING_ELEMENT__LEVEL, 10 /*INT*/, 4);
		// FigureRefElement::ref=ID || soft-space value soft-space
		serializationSteps[6] = createSerializationStepCrossReference(MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF, getCrossReference(MarkupPackage.Literals.FIGURE_REF_ELEMENT__REF, "ID"), 4);
		// FigureElement::requiredHeight=10 || soft-space value soft-space
		serializationSteps[7] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_HEIGHT, 10 /*INT*/, 4);
		// FigureElement::requiredWidth=10 || soft-space value soft-space
		serializationSteps[8] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.FIGURE_ELEMENT__REQUIRED_WIDTH, 10 /*INT*/, 4);
		// FigureElement::src=22 || soft-space value soft-space
		serializationSteps[9] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.FIGURE_ELEMENT__SRC, 22 /*STRING*/, 4);
		// NewLineElement::text=15 || soft-space value soft-space
		serializationSteps[10] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.NEW_LINE_ELEMENT__TEXT, 15 /*NL*/, 4);
		// '#' || soft-space value soft-space
		serializationSteps[11] = createSerializationStepKeyword("#", 4);
		// ',' || no-space value soft-space
		serializationSteps[12] = createSerializationStepKeyword(",", 3);
		// ':' || soft-space value soft-space
		serializationSteps[13] = createSerializationStepKeyword(":", 4);
		// '[' || no-space value no-space
		serializationSteps[14] = createSerializationStepKeyword("[", 2);
		// ']' || no-space value
		serializationSteps[15] = createSerializationStepKeyword("]", 1);
		// 'bullet' || soft-space value soft-space
		serializationSteps[16] = createSerializationStepKeyword("bullet", 4);
		// 'figure' || soft-space value soft-space
		serializationSteps[17] = createSerializationStepKeyword("figure", 4);
		// 'figureRef' || soft-space value soft-space
		serializationSteps[18] = createSerializationStepKeyword("figureRef", 4);
		// 'footnote' || soft-space value soft-space
		serializationSteps[19] = createSerializationStepKeyword("footnote", 4);
		// 'heading' || soft-space value soft-space
		serializationSteps[20] = createSerializationStepKeyword("heading", 4);
		// 'oclCode' || soft-space value soft-space
		serializationSteps[21] = createSerializationStepKeyword("oclCode", 4);
		// 'oclEval' || soft-space value soft-space
		serializationSteps[22] = createSerializationStepKeyword("oclEval", 4);
		// 'oclText' || soft-space value soft-space
		serializationSteps[23] = createSerializationStepKeyword("oclText", 4);
		// 1*1-steps || value
		serializationSteps[24] = createSerializationStepSequence(-1, 1, 0);
		// V00*1-steps || value
		serializationSteps[25] = createSerializationStepSequence(0, 1, 0);
		// V00*4-steps || value
		serializationSteps[26] = createSerializationStepSequence(0, 4, 0);
		// V01*1-steps || value
		serializationSteps[27] = createSerializationStepSequence(1, 1, 0);
		// V01*14-steps || value
		serializationSteps[28] = createSerializationStepSequence(1, 14, 0);
		// V02*9-steps || value
		serializationSteps[29] = createSerializationStepSequence(2, 9, 0);
		// V03*4-steps || value
		serializationSteps[30] = createSerializationStepSequence(3, 4, 0);
		// TextElement::text+=14 || soft-space value soft-space
		serializationSteps[31] = createSerializationStepAssignedRuleCall(MarkupPackage.Literals.TEXT_ELEMENT__TEXT, 14 /*MarkupKeyword*/, 4);
		// TextElement::text+=9|25|10|26 || soft-space value soft-space
		serializationSteps[32] = createSerializationStepAssigns(MarkupPackage.Literals.TEXT_ELEMENT__TEXT, 0 /* '#|,|:' */, new @NonNull Integer [] { 9/*ID*/,25/*WORD*/,10/*INT*/,26/*WS*/}, 4);
	}
}

//	Commented imports ensure Xtend provides a true import allowing unqualified annotated usage
//	import Inject;
//	import EAttribute;
//	import NonNull;
//	import Nullable;
//	import DataTypeRuleValue;
//	import EClassValue;
//	import EnumerationValue;
//	import EnumerationValueMultiple;
//	import GrammarCardinality;
//	import GrammarRuleValue;
//	import GrammarRuleVector;
//	import SerializationMatchStep;
//	import SerializationMatchTerm;
//	import SerializationMatchTermEStructuralFeatureSize;
//	import SerializationMatchTermInteger;
//	import SerializationRule;
//	import EAttribute_EnumerationValue_GrammarCardinality;
//	import EAttribute_EnumerationValues;
//	import EReference_RuleIndex_GrammarCardinality;
//	import EReference_RuleIndexes;
//	import EnumerationValue_GrammarCardinality;
//	import RuleIndex_GrammarCardinality;
//	import SerializationSegment;
//	import SerializationStep;
//	import TerminalRuleValue;
//	import MarkupPackage;
