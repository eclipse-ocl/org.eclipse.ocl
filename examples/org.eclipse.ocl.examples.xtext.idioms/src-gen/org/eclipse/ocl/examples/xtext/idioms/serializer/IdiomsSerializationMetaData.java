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
package org.eclipse.ocl.examples.xtext.idioms.serializer;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.serializer.AbstractSerializationMetaData;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueSingle;
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
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment.CustomSerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.TerminalRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport;
import org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport;

public class IdiomsSerializationMetaData extends AbstractSerializationMetaData
{
	private boolean initialized = false;
	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[33];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[3];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[43];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[14];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[52];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[59];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[36];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [9] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[91];

	private final @Nullable String @Nullable [] multipleLineCommentMidfixes = new @Nullable String[] {" *"};
	private final @NonNull String @Nullable [] multipleLineCommentPrefixes = new @NonNull String[] {"/*"};
	private final @NonNull String @Nullable [] multipleLineCommentSuffixes = new @NonNull String[] {"*/"};
	private final @NonNull String @Nullable [] singleLineCommentPrefixes = new @NonNull String[] {"//"};

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
		return 34;
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
		return 33;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 75;
	}

	@Override
	public @Nullable String @Nullable [] getMultipleLineCommentMidfixes() {
		return multipleLineCommentMidfixes;
	}

	@Override
	public @NonNull String @Nullable [] getMultipleLineCommentPrefixes() {
		return multipleLineCommentPrefixes;
	}

	@Override
	public @NonNull String @Nullable [] getMultipleLineCommentSuffixes() {
		return multipleLineCommentSuffixes;
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
		return singleLineCommentPrefixes;
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
		eClassValues[0] = new EClassValue(IdiomsPackage.Literals.ANY_ASSIGNMENT_LOCATOR,
			createSerializationRules(
				0 /* "any-assignment" */
			), null
		);
		eClassValues[1] = new EClassValue(IdiomsPackage.Literals.ANY_ELEMENT_LOCATOR,
			createSerializationRules(
				1 /* "any-element" */
			), null
		);
		eClassValues[2] = new EClassValue(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR,
			createSerializationRules(
				2 /* { "assignment" { { ePackage=ID "::" }[?] eClass=ID "::" }[?] eStructuralFeature=ID } */
			), null
		);
		eClassValues[3] = new EClassValue(IdiomsPackage.Literals.COMPOUND_LOCATOR,
			createSerializationRules(
				3 /* { "{" ownedLocators+=ElementLocator { "|" ownedLocators+=ElementLocator }[*] "}" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(IdiomsPackage.Literals.COMPOUND_LOCATOR__OWNED_LOCATORS,
					6) /* AssignmentLocator|CompoundLocator|ElementLocator|KeywordLocator|ReferredLocator */
			}
		);
		eClassValues[4] = new EClassValue(IdiomsPackage.Literals.CUSTOM_SEGMENT,
			createSerializationRules(
				4 /* { "custom" supportClassName=STRING } */
			), null
		);
		eClassValues[5] = new EClassValue(IdiomsPackage.Literals.EPACKAGE_IMPORT,
			createSerializationRules(
				5 /* { "import" ePackage=STRING { "as" as=ID }[?] } */
			), null
		);
		eClassValues[6] = new EClassValue(IdiomsPackage.Literals.FINAL_LOCATOR,
			createSerializationRules(
				6 /* "final" */
			), null
		);
		eClassValues[7] = new EClassValue(IdiomsPackage.Literals.HALF_NEW_LINE_SEGMENT,
			createSerializationRules(
				7 /* "half-new-line" */
			), null
		);
		eClassValues[8] = new EClassValue(IdiomsPackage.Literals.IDIOM,
			createSerializationRules(
				8 /* { mixin="mixin"[?] "idiom" name=ID { "for" { forEPackage=ID "::" }[?] forEClass=ID }[?] { "in" inRuleRegex=STRING }[?] ownedSubIdioms+=SubIdiom } */,
				9 /* { mixin="mixin"[?] "idiom" name=ID { "for" { forEPackage=ID "::" }[?] forEClass=ID }[?] { "in" inRuleRegex=STRING }[?] { "{" ownedSubIdioms+=SubIdiom[*] "}" } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS,
					11) /* SubIdiom */
			}
		);
		eClassValues[9] = new EClassValue(IdiomsPackage.Literals.IDIOMS_IMPORT,
			createSerializationRules(
				10 /* { "with" idiomsModel=STRING { "as" as=ID }[?] } */
			), null
		);
		eClassValues[10] = new EClassValue(IdiomsPackage.Literals.IDIOMS_MODEL,
			createSerializationRules(
				11 /* { "model" name=ID ownedWiths+=IdiomsImport[*] ownedImports+=EPackageImport[*] { ownedLocatorDeclarations+=LocatorDeclaration[*] ownedSegmentDeclarations+=SegmentDeclaration[*] ownedIdioms+=Idiom[*] } } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS,
					2) /* Idiom */,
				createEReference_RuleIndexes(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORTS,
					0) /* EPackageImport */,
				createEReference_RuleIndexes(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS,
					5) /* LocatorDeclaration */,
				createEReference_RuleIndexes(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS,
					10) /* SegmentDeclaration */,
				createEReference_RuleIndexes(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS,
					3) /* IdiomsImport */
			}
		);
		eClassValues[11] = new EClassValue(IdiomsPackage.Literals.KEYWORD_LOCATOR,
			createSerializationRules(
				12 /* string=STRING */
			), null
		);
		eClassValues[12] = new EClassValue(IdiomsPackage.Literals.LOCATOR_DECLARATION,
			createSerializationRules(
				13 /* { "locator" name=ID ownedLocator=Locator ";" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR,
					7) /* AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|CompoundLocator|ElementLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator */
			}
		);
		eClassValues[13] = new EClassValue(IdiomsPackage.Literals.NEW_LINE_SEGMENT,
			createSerializationRules(
				14 /* "new-line" */
			), null
		);
		eClassValues[14] = new EClassValue(IdiomsPackage.Literals.NO_SPACE_SEGMENT,
			createSerializationRules(
				15 /* "no-space" */
			), null
		);
		eClassValues[15] = new EClassValue(IdiomsPackage.Literals.POP_SEGMENT,
			createSerializationRules(
				16 /* "pop" */
			), null
		);
		eClassValues[16] = new EClassValue(IdiomsPackage.Literals.POST_COMMENT_SEGMENT,
			createSerializationRules(
				17 /* "post-comment" */
			), null
		);
		eClassValues[17] = new EClassValue(IdiomsPackage.Literals.PRE_COMMENT_SEGMENT,
			createSerializationRules(
				18 /* "pre-comment" */
			), null
		);
		eClassValues[18] = new EClassValue(IdiomsPackage.Literals.PUSH_SEGMENT,
			createSerializationRules(
				19 /* "push" */
			), null
		);
		eClassValues[19] = new EClassValue(IdiomsPackage.Literals.REFERRED_LOCATOR,
			createSerializationRules(
				20 /* { { idiomsModel=ID "::" }[?] locatorDeclaration=ID } */
			), null
		);
		eClassValues[20] = new EClassValue(IdiomsPackage.Literals.REFERRED_SEGMENT,
			createSerializationRules(
				21 /* { { idiomsModel=ID "::" }[?] segmentDeclaration=ID } */
			), null
		);
		eClassValues[21] = new EClassValue(IdiomsPackage.Literals.RETURNS_LOCATOR,
			createSerializationRules(
				22 /* { "returns" { ePackage=ID "::" }[?] eClass=ID } */
			), null
		);
		eClassValues[22] = new EClassValue(IdiomsPackage.Literals.SEGMENT_DECLARATION,
			createSerializationRules(
				23 /* { "segment" name=ID ownedSegment=Segment ";" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT,
					12) /* CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */
			}
		);
		eClassValues[23] = new EClassValue(IdiomsPackage.Literals.SOFT_NEW_LINE_SEGMENT,
			createSerializationRules(
				24 /* "soft-new-line" */
			), null
		);
		eClassValues[24] = new EClassValue(IdiomsPackage.Literals.SOFT_SPACE_SEGMENT,
			createSerializationRules(
				25 /* "soft-space" */
			), null
		);
		eClassValues[25] = new EClassValue(IdiomsPackage.Literals.STRING_SEGMENT,
			createSerializationRules(
				26 /* { "string" string=STRING printable="printable" } */
			), null
		);
		eClassValues[26] = new EClassValue(IdiomsPackage.Literals.SUB_IDIOM,
			createSerializationRules(
				29 /* { "at" ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" } */,
				27 /* { "at" "each" ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" } */,
				28 /* { "at" all="all"[?] ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR,
					7) /* AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|CompoundLocator|ElementLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator */,
				createEReference_RuleIndexes(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS,
					13) /* CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */
			}
		);
		eClassValues[27] = new EClassValue(IdiomsPackage.Literals.VALUE_SEGMENT,
			createSerializationRules(
				30 /* "value" */
			), null
		);
		eClassValues[28] = new EClassValue(IdiomsPackage.Literals.WRAP_ANCHOR_SEGMENT,
			createSerializationRules(
				31 /* "wrap-anchor" */
			), null
		);
		eClassValues[29] = new EClassValue(IdiomsPackage.Literals.WRAP_BEGIN_ALL_SEGMENT,
			createSerializationRules(
				32 /* "wrap-begin-all" */
			), null
		);
		eClassValues[30] = new EClassValue(IdiomsPackage.Literals.WRAP_BEGIN_SOME_SEGMENT,
			createSerializationRules(
				33 /* "wrap-begin-some" */
			), null
		);
		eClassValues[31] = new EClassValue(IdiomsPackage.Literals.WRAP_END_SEGMENT,
			createSerializationRules(
				34 /* "wrap-end" */
			), null
		);
		eClassValues[32] = new EClassValue(IdiomsPackage.Literals.WRAP_HERE_SEGMENT,
			createSerializationRules(
				35 /* "wrap-here" */
			), null
		);
	}

	/**
	 * Initialize string combinations used by assigned String EAttributes.
	 */
	private void initEnumerationValues() {
		// 'all'
		enumerationValues[0] = new EnumerationValueSingle("all");
		// 'mixin'
		enumerationValues[1] = new EnumerationValueSingle("mixin");
		// 'printable'
		enumerationValues[2] = new EnumerationValueSingle("printable");
	}

	/**
	 * Initialize the various serialization rules for each grammar rule.
	 */
	private void initGrammarRuleValues() {
		grammarRuleValues[0] = new TerminalRuleValue(0, "ANY_OTHER");
		grammarRuleValues[1] = createParserRuleValue(1, "AnyAssignmentLocator", -1,
			createSerializationRules(
				0	/* AnyAssignmentLocator: "any-assignment" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {AnyAssignmentLocator} : [value] | [value] */,
			(0 << 16) | 6	/* "any-assignment" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[2] = createParserRuleValue(2, "AnyElementLocator", -1,
			createSerializationRules(
				1	/* AnyElementLocator: "any-element" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {AnyElementLocator} : [value] | [value] */,
			(0 << 16) | 6	/* "any-element" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[3] = createParserRuleValue(3, "AssignmentLocator", -1,
			createSerializationRules(
				2	/* AssignmentLocator: { "assignment" { { ePackage=ID "::" }[?] eClass=ID "::" }[?] eStructuralFeature=ID } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "assignment" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ePackage=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 4	/* "::" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, no-space, value, no-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* eClass=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 4	/* "::" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, no-space, value, no-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* eStructuralFeature=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[4] = createParserRuleValue(4, "CompoundLocator", -1,
			createSerializationRules(
				3	/* CompoundLocator: { "{" ownedLocators+=ElementLocator { "|" ownedLocators+=ElementLocator }[*] "}" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 8	/* "{" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, push, soft-new-line, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* ownedLocators+=ElementLocator : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 6	/* "|" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* ownedLocators+=ElementLocator : [value] | [value] */,
			(0 << 16) | 7	/* "}" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, pop, soft-space, value, soft-new-line, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[5] = createParserRuleValue(5, "CustomSegment", -1,
			createSerializationRules(
				4	/* CustomSegment: { "custom" supportClassName=STRING } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "custom" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* supportClassName=STRING : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[6] = createParserRuleValue(6, "EPackageImport", -1,
			createSerializationRules(
				5	/* EPackageImport: { "import" ePackage=STRING { "as" as=ID }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "import" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* ePackage=STRING : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "as" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* as=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 5	/* ";"? : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, no-space, value, soft-new-line, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[7] = createParserRuleValue(7, "ElementLocator", 6 /* AssignmentLocator|CompoundLocator|ElementLocator|KeywordLocator|ReferredLocator */,
			createSerializationRules(
				2	/* AssignmentLocator: { "assignment" { { ePackage=ID "::" }[?] eClass=ID "::" }[?] eStructuralFeature=ID } */,
				3	/* CompoundLocator: { "{" ownedLocators+=ElementLocator { "|" ownedLocators+=ElementLocator }[*] "}" } */,
				12	/* KeywordLocator: string=STRING */,
				20	/* ReferredLocator: { { idiomsModel=ID "::" }[?] locatorDeclaration=ID } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* AssignmentLocator : [value] | [value] */,
			(0 << 16) | 0	/* CompoundLocator : [value] | [value] */,
			(0 << 16) | 0	/* KeywordLocator : [value] | [value] */,
			(0 << 16) | 0	/* ReferredLocator : [value] | [value] */
		);
		grammarRuleValues[8] = createParserRuleValue(8, "FinalLocator", -1,
			createSerializationRules(
				6	/* FinalLocator: "final" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {FinalLocator} : [value] | [value] */,
			(0 << 16) | 6	/* "final" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[9] = createParserRuleValue(9, "HalfNewLineSegment", -1,
			createSerializationRules(
				7	/* HalfNewLineSegment: "half-new-line" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {HalfNewLineSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "half-new-line" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[10] = new TerminalRuleValue(10, "ID");
		grammarRuleValues[11] = new TerminalRuleValue(11, "INT");
		grammarRuleValues[12] = createParserRuleValue(12, "Idiom", -1,
			createSerializationRules(
				8	/* Idiom: { mixin="mixin"[?] "idiom" name=ID { "for" { forEPackage=ID "::" }[?] forEClass=ID }[?] { "in" inRuleRegex=STRING }[?] ownedSubIdioms+=SubIdiom } */,
				9	/* Idiom: { mixin="mixin"[?] "idiom" name=ID { "for" { forEPackage=ID "::" }[?] forEClass=ID }[?] { "in" inRuleRegex=STRING }[?] { "{" ownedSubIdioms+=SubIdiom[*] "}" } } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* mixin?="mixin"? : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* "idiom" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* name=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "for" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* forEPackage=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 4	/* "::" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, no-space, value, no-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* forEClass=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "in" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* inRuleRegex=STRING : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 1	/* ownedSubIdioms+=SubIdiom : [value] | [value, soft-new-line] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 8	/* "{" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, push, soft-new-line, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 1	/* ownedSubIdioms+=SubIdiom* : [value] | [value, soft-new-line] */,
			(0 << 16) | 7	/* "}" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, pop, soft-space, value, soft-new-line, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[13] = createParserRuleValue(13, "IdiomsImport", -1,
			createSerializationRules(
				10	/* IdiomsImport: { "with" idiomsModel=STRING { "as" as=ID }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "with" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* idiomsModel=STRING : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "as" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* as=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 5	/* ";"? : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, no-space, value, soft-new-line, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[14] = createParserRuleValue(14, "IdiomsModel", -1,
			createSerializationRules(
				11	/* IdiomsModel: { "model" name=ID ownedWiths+=IdiomsImport[*] ownedImports+=EPackageImport[*] { ownedLocatorDeclarations+=LocatorDeclaration[*] ownedSegmentDeclarations+=SegmentDeclaration[*] ownedIdioms+=Idiom[*] } } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "model" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* name=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 2	/* ownedWiths+=IdiomsImport* : [value] | [soft-new-line, value, soft-new-line] */,
			(0 << 16) | 2	/* ownedImports+=EPackageImport* : [value] | [soft-new-line, value, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(3 << 16) | 0	/* ownedLocatorDeclarations+=LocatorDeclaration : [new-line, soft-new-line, value, soft-new-line] | [value] */,
			(3 << 16) | 0	/* ownedSegmentDeclarations+=SegmentDeclaration : [new-line, soft-new-line, value, soft-new-line] | [value] */,
			(0 << 16) | 3	/* ownedIdioms+=Idiom : [value] | [new-line, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[15] = createParserRuleValue(15, "KeywordLocator", -1,
			createSerializationRules(
				12	/* KeywordLocator: string=STRING */
			),
			(0 << 16) | 6	/* string=STRING : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[16] = createParserRuleValue(16, "Locator", 7 /* AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|CompoundLocator|ElementLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator */,
			createSerializationRules(
				0	/* AnyAssignmentLocator: "any-assignment" */,
				1	/* AnyElementLocator: "any-element" */,
				2	/* AssignmentLocator: { "assignment" { { ePackage=ID "::" }[?] eClass=ID "::" }[?] eStructuralFeature=ID } */,
				3	/* CompoundLocator: { "{" ownedLocators+=ElementLocator { "|" ownedLocators+=ElementLocator }[*] "}" } */,
				6	/* FinalLocator: "final" */,
				12	/* KeywordLocator: string=STRING */,
				20	/* ReferredLocator: { { idiomsModel=ID "::" }[?] locatorDeclaration=ID } */,
				22	/* ReturnsLocator: { "returns" { ePackage=ID "::" }[?] eClass=ID } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* AnyAssignmentLocator : [value] | [value] */,
			(0 << 16) | 0	/* AnyElementLocator : [value] | [value] */,
			(0 << 16) | 0	/* ElementLocator : [value] | [value] */,
			(0 << 16) | 0	/* FinalLocator : [value] | [value] */,
			(0 << 16) | 0	/* ReturnsLocator : [value] | [value] */
		);
		grammarRuleValues[17] = createParserRuleValue(17, "LocatorDeclaration", -1,
			createSerializationRules(
				13	/* LocatorDeclaration: { "locator" name=ID ownedLocator=Locator ";" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "locator" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* name=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* ownedLocator=Locator : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, no-space, value, soft-new-line, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[18] = new TerminalRuleValue(18, "ML_COMMENT");
		grammarRuleValues[19] = createParserRuleValue(19, "NewLineSegment", -1,
			createSerializationRules(
				14	/* NewLineSegment: "new-line" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {NewLineSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "new-line" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[20] = createParserRuleValue(20, "NoSpaceSegment", -1,
			createSerializationRules(
				15	/* NoSpaceSegment: "no-space" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {NoSpaceSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "no-space" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[21] = createParserRuleValue(21, "PopSegment", -1,
			createSerializationRules(
				16	/* PopSegment: "pop" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PopSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "pop" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[22] = createParserRuleValue(22, "PostCommentSegment", -1,
			createSerializationRules(
				17	/* PostCommentSegment: "post-comment" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PostCommentSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "post-comment" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[23] = createParserRuleValue(23, "PreCommentSegment", -1,
			createSerializationRules(
				18	/* PreCommentSegment: "pre-comment" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PreCommentSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "pre-comment" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[24] = createParserRuleValue(24, "PushSegment", -1,
			createSerializationRules(
				19	/* PushSegment: "push" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PushSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "push" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[25] = createParserRuleValue(25, "ReferredLocator", -1,
			createSerializationRules(
				20	/* ReferredLocator: { { idiomsModel=ID "::" }[?] locatorDeclaration=ID } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* idiomsModel=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 4	/* "::" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, no-space, value, no-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* locatorDeclaration=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[26] = createParserRuleValue(26, "ReferredSegment", -1,
			createSerializationRules(
				21	/* ReferredSegment: { { idiomsModel=ID "::" }[?] segmentDeclaration=ID } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* idiomsModel=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 4	/* "::" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, no-space, value, no-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* segmentDeclaration=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[27] = createParserRuleValue(27, "ReturnsLocator", -1,
			createSerializationRules(
				22	/* ReturnsLocator: { "returns" { ePackage=ID "::" }[?] eClass=ID } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "returns" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ePackage=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 4	/* "::" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, no-space, value, no-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* eClass=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[28] = new TerminalRuleValue(28, "SL_COMMENT");
		grammarRuleValues[29] = new TerminalRuleValue(29, "STRING");
		grammarRuleValues[30] = createParserRuleValue(30, "Segment", 12 /* CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */,
			createSerializationRules(
				4	/* CustomSegment: { "custom" supportClassName=STRING } */,
				7	/* HalfNewLineSegment: "half-new-line" */,
				14	/* NewLineSegment: "new-line" */,
				15	/* NoSpaceSegment: "no-space" */,
				16	/* PopSegment: "pop" */,
				17	/* PostCommentSegment: "post-comment" */,
				18	/* PreCommentSegment: "pre-comment" */,
				19	/* PushSegment: "push" */,
				24	/* SoftNewLineSegment: "soft-new-line" */,
				25	/* SoftSpaceSegment: "soft-space" */,
				26	/* StringSegment: { "string" string=STRING printable="printable" } */,
				30	/* ValueSegment: "value" */,
				31	/* WrapAnchorSegment: "wrap-anchor" */,
				32	/* WrapBeginAllSegment: "wrap-begin-all" */,
				33	/* WrapBeginSomeSegment: "wrap-begin-some" */,
				34	/* WrapEndSegment: "wrap-end" */,
				35	/* WrapHereSegment: "wrap-here" */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* CustomSegment : [value] | [value] */,
			(0 << 16) | 0	/* HalfNewLineSegment : [value] | [value] */,
			(0 << 16) | 0	/* NewLineSegment : [value] | [value] */,
			(0 << 16) | 0	/* NoSpaceSegment : [value] | [value] */,
			(0 << 16) | 0	/* PopSegment : [value] | [value] */,
			(0 << 16) | 0	/* PostCommentSegment : [value] | [value] */,
			(0 << 16) | 0	/* PreCommentSegment : [value] | [value] */,
			(0 << 16) | 0	/* PushSegment : [value] | [value] */,
			(0 << 16) | 0	/* SoftNewLineSegment : [value] | [value] */,
			(0 << 16) | 0	/* SoftSpaceSegment : [value] | [value] */,
			(0 << 16) | 0	/* StringSegment : [value] | [value] */,
			(0 << 16) | 0	/* ValueSegment : [value] | [value] */,
			(0 << 16) | 0	/* WrapAnchorSegment : [value] | [value] */,
			(0 << 16) | 0	/* WrapBeginAllSegment : [value] | [value] */,
			(0 << 16) | 0	/* WrapBeginSomeSegment : [value] | [value] */,
			(0 << 16) | 0	/* WrapEndSegment : [value] | [value] */,
			(0 << 16) | 0	/* WrapHereSegment : [value] | [value] */
		);
		grammarRuleValues[31] = createParserRuleValue(31, "SegmentDeclaration", -1,
			createSerializationRules(
				23	/* SegmentDeclaration: { "segment" name=ID ownedSegment=Segment ";" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "segment" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* name=ID : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* ownedSegment=Segment : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, no-space, value, soft-new-line, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[32] = createParserRuleValue(32, "SoftNewLineSegment", -1,
			createSerializationRules(
				24	/* SoftNewLineSegment: "soft-new-line" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {SoftNewLineSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "soft-new-line" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[33] = createParserRuleValue(33, "SoftSpaceSegment", -1,
			createSerializationRules(
				25	/* SoftSpaceSegment: "soft-space" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {SoftSpaceSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "soft-space" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[34] = createParserRuleValue(34, "StringSegment", -1,
			createSerializationRules(
				26	/* StringSegment: { "string" string=STRING printable="printable" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "string" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* string=STRING : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* printable?="printable" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[35] = createParserRuleValue(35, "SubIdiom", -1,
			createSerializationRules(
				27	/* SubIdiom: { "at" "each" ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" } */,
				28	/* SubIdiom: { "at" all="all"[?] ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" } */,
				29	/* SubIdiom: { "at" ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "at" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 6	/* all?="all" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 6	/* "each" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* ownedLocator=Locator : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "do" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */,
			(0 << 16) | 0	/* ownedSegments+=(Segment|ReferredSegment)* : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, no-space, value, soft-new-line, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[36] = createParserRuleValue(36, "ValueSegment", -1,
			createSerializationRules(
				30	/* ValueSegment: "value" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {ValueSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "value" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[37] = new TerminalRuleValue(37, "WS");
		grammarRuleValues[38] = createParserRuleValue(38, "WrapAnchorSegment", -1,
			createSerializationRules(
				31	/* WrapAnchorSegment: "wrap-anchor" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapAnchorSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-anchor" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[39] = createParserRuleValue(39, "WrapBeginAllSegment", -1,
			createSerializationRules(
				32	/* WrapBeginAllSegment: "wrap-begin-all" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapBeginAllSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-begin-all" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[40] = createParserRuleValue(40, "WrapBeginSomeSegment", -1,
			createSerializationRules(
				33	/* WrapBeginSomeSegment: "wrap-begin-some" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapBeginSomeSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-begin-some" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[41] = createParserRuleValue(41, "WrapEndSegment", -1,
			createSerializationRules(
				34	/* WrapEndSegment: "wrap-end" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapEndSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-end" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
		grammarRuleValues[42] = createParserRuleValue(42, "WrapHereSegment", -1,
			createSerializationRules(
				35	/* WrapHereSegment: "wrap-here" */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapHereSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-here" : [value] | [supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport, soft-space, value, soft-space, supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport] */
		);
	}

	/**
	 * Initialize bit vectors of useful grammar rule combinations.
	 */
	private void initGrammarRuleVectors() {
		// EPackageImport
		grammarRuleVectors[0] = new GrammarRuleVector(0x40L);
		// ElementLocator
		grammarRuleVectors[1] = new GrammarRuleVector(0x80L);
		// Idiom
		grammarRuleVectors[2] = new GrammarRuleVector(0x1000L);
		// IdiomsImport
		grammarRuleVectors[3] = new GrammarRuleVector(0x2000L);
		// Locator
		grammarRuleVectors[4] = new GrammarRuleVector(0x10000L);
		// LocatorDeclaration
		grammarRuleVectors[5] = new GrammarRuleVector(0x20000L);
		// AssignmentLocator|CompoundLocator|ElementLocator|KeywordLocator|ReferredLocator
		grammarRuleVectors[6] = new GrammarRuleVector(0x2008098L);
		// AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|CompoundLocator|ElementLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator
		grammarRuleVectors[7] = new GrammarRuleVector(0xa01819eL);
		// Segment
		grammarRuleVectors[8] = new GrammarRuleVector(0x40000000L);
		// ReferredSegment|Segment
		grammarRuleVectors[9] = new GrammarRuleVector(0x44000000L);
		// SegmentDeclaration
		grammarRuleVectors[10] = new GrammarRuleVector(0x80000000L);
		// SubIdiom
		grammarRuleVectors[11] = new GrammarRuleVector(0x800000000L);
		// CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment
		grammarRuleVectors[12] = new GrammarRuleVector(0x7d741f80220L);
		// CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment
		grammarRuleVectors[13] = new GrammarRuleVector(0x7d745f80220L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// assert (|AssignmentLocator::eStructuralFeature| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(39);
		// assert (|CustomSegment::supportClassName| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(41);
		// assert (|EPackageImport::ePackage| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(42);
		// assert (|Idiom::name| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(43);
		// assert (|Idiom::ownedSubIdioms| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(44);
		// assert (|IdiomsImport::idiomsModel| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(45);
		// assert (|IdiomsModel::name| - 1) == 0
		serializationMatchSteps[6] = createMatchStep_Assert(46);
		// assert (|KeywordLocator::string| - 1) == 0
		serializationMatchSteps[7] = createMatchStep_Assert(47);
		// assert (|LocatorDeclaration::name| - 1) == 0
		serializationMatchSteps[8] = createMatchStep_Assert(48);
		// assert (|LocatorDeclaration::ownedLocator| - 1) == 0
		serializationMatchSteps[9] = createMatchStep_Assert(49);
		// assert (|ReferredLocator::locatorDeclaration| - 1) == 0
		serializationMatchSteps[10] = createMatchStep_Assert(50);
		// assert (|ReferredSegment::segmentDeclaration| - 1) == 0
		serializationMatchSteps[11] = createMatchStep_Assert(51);
		// assert (|ReturnsLocator::eClass| - 1) == 0
		serializationMatchSteps[12] = createMatchStep_Assert(52);
		// assert (|SegmentDeclaration::name| - 1) == 0
		serializationMatchSteps[13] = createMatchStep_Assert(53);
		// assert (|SegmentDeclaration::ownedSegment| - 1) == 0
		serializationMatchSteps[14] = createMatchStep_Assert(54);
		// assert (|StringSegment::printable.'printable'| - 1) == 0
		serializationMatchSteps[15] = createMatchStep_Assert(55);
		// assert (|StringSegment::string| - 1) == 0
		serializationMatchSteps[16] = createMatchStep_Assert(56);
		// assert (|SubIdiom::ownedLocator| - 1) == 0
		serializationMatchSteps[17] = createMatchStep_Assert(57);
		// assign V0 = (|CompoundLocator::ownedLocators| - 1)
		serializationMatchSteps[18] = createMatchStep_Assign(0, 40);
		// assign V0 = (|SubIdiom::ownedSegments| > 0)
		serializationMatchSteps[19] = createMatchStep_Assign(0, 58);
		// assign V0 = |AssignmentLocator::eClass|
		serializationMatchSteps[20] = createMatchStep_Assign(0, 2);
		// assign V0 = |EPackageImport::as|
		serializationMatchSteps[21] = createMatchStep_Assign(0, 7);
		// assign V0 = |Idiom::mixin.'mixin'|
		serializationMatchSteps[22] = createMatchStep_Assign(0, 12);
		// assign V0 = |IdiomsImport::as|
		serializationMatchSteps[23] = createMatchStep_Assign(0, 15);
		// assign V0 = |IdiomsModel::ownedWiths|
		serializationMatchSteps[24] = createMatchStep_Assign(0, 22);
		// assign V0 = |ReferredLocator::idiomsModel|
		serializationMatchSteps[25] = createMatchStep_Assign(0, 26);
		// assign V0 = |ReferredSegment::idiomsModel|
		serializationMatchSteps[26] = createMatchStep_Assign(0, 28);
		// assign V0 = |ReturnsLocator::ePackage|
		serializationMatchSteps[27] = createMatchStep_Assign(0, 31);
		// assign V0 = |SubIdiom::all.'all'|
		serializationMatchSteps[28] = createMatchStep_Assign(0, 36);
		// assign V1 = (|SubIdiom::ownedSegments| > 0)
		serializationMatchSteps[29] = createMatchStep_Assign(1, 58);
		// assign V1 = |AssignmentLocator::ePackage|
		serializationMatchSteps[30] = createMatchStep_Assign(1, 3);
		// assign V1 = |Idiom::forEClass|
		serializationMatchSteps[31] = createMatchStep_Assign(1, 9);
		// assign V1 = |IdiomsModel::ownedImports|
		serializationMatchSteps[32] = createMatchStep_Assign(1, 19);
		// assign V1 = |SubIdiom::ownedSegments|
		serializationMatchSteps[33] = createMatchStep_Assign(1, 38);
		// assign V2 = |Idiom::forEPackage|
		serializationMatchSteps[34] = createMatchStep_Assign(2, 10);
		// assign V2 = |IdiomsModel::ownedLocatorDeclarations|
		serializationMatchSteps[35] = createMatchStep_Assign(2, 20);
		// assign V2 = |SubIdiom::ownedSegments|
		serializationMatchSteps[36] = createMatchStep_Assign(2, 38);
		// assign V3 = |Idiom::inRuleRegex|
		serializationMatchSteps[37] = createMatchStep_Assign(3, 11);
		// assign V3 = |IdiomsModel::ownedSegmentDeclarations|
		serializationMatchSteps[38] = createMatchStep_Assign(3, 21);
		// assign V4 = |Idiom::ownedSubIdioms|
		serializationMatchSteps[39] = createMatchStep_Assign(4, 14);
		// assign V4 = |IdiomsModel::ownedIdioms|
		serializationMatchSteps[40] = createMatchStep_Assign(4, 18);
		// check-rule idioms::CompoundLocator.ownedLocators : 7
		serializationMatchSteps[41] = createMatchStep_RuleCheck(IdiomsPackage.Literals.COMPOUND_LOCATOR__OWNED_LOCATORS, 1/*ElementLocator*/);
		// check-rule idioms::Idiom.ownedSubIdioms : 35
		serializationMatchSteps[42] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS, 11/*SubIdiom*/);
		// check-rule idioms::IdiomsModel.ownedIdioms : 12
		serializationMatchSteps[43] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS, 2/*Idiom*/);
		// check-rule idioms::IdiomsModel.ownedImports : 6
		serializationMatchSteps[44] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORTS, 0/*EPackageImport*/);
		// check-rule idioms::IdiomsModel.ownedLocatorDeclarations : 17
		serializationMatchSteps[45] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS, 5/*LocatorDeclaration*/);
		// check-rule idioms::IdiomsModel.ownedSegmentDeclarations : 31
		serializationMatchSteps[46] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS, 10/*SegmentDeclaration*/);
		// check-rule idioms::IdiomsModel.ownedWiths : 13
		serializationMatchSteps[47] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS, 3/*IdiomsImport*/);
		// check-rule idioms::LocatorDeclaration.ownedLocator : 16
		serializationMatchSteps[48] = createMatchStep_RuleCheck(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR, 4/*Locator*/);
		// check-rule idioms::SegmentDeclaration.ownedSegment : 30
		serializationMatchSteps[49] = createMatchStep_RuleCheck(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT, 8/*Segment*/);
		// check-rule idioms::SubIdiom.ownedLocator : 16
		serializationMatchSteps[50] = createMatchStep_RuleCheck(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR, 4/*Locator*/);
		// check-rule idioms::SubIdiom.ownedSegments : 26|30
		serializationMatchSteps[51] = createMatchStep_RuleCheck(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS, 9/*ReferredSegment|Segment*/);
	}

	/**
	 * Initialize expression terms used during the matching process.
	 */
	private void initMatchTerms() {
		// 0
		serializationMatchTerms[0] = new SerializationMatchTermInteger(0);
		// 1
		serializationMatchTerms[1] = new SerializationMatchTermInteger(1);
		// |AssignmentLocator::eClass|
		serializationMatchTerms[2] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ECLASS);
		// |AssignmentLocator::ePackage|
		serializationMatchTerms[3] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__EPACKAGE);
		// |AssignmentLocator::eStructuralFeature|
		serializationMatchTerms[4] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE);
		// |CompoundLocator::ownedLocators|
		serializationMatchTerms[5] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.COMPOUND_LOCATOR__OWNED_LOCATORS);
		// |CustomSegment::supportClassName|
		serializationMatchTerms[6] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.CUSTOM_SEGMENT__SUPPORT_CLASS_NAME);
		// |EPackageImport::as|
		serializationMatchTerms[7] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.EPACKAGE_IMPORT__AS);
		// |EPackageImport::ePackage|
		serializationMatchTerms[8] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.EPACKAGE_IMPORT__EPACKAGE);
		// |Idiom::forEClass|
		serializationMatchTerms[9] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__FOR_ECLASS);
		// |Idiom::forEPackage|
		serializationMatchTerms[10] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE);
		// |Idiom::inRuleRegex|
		serializationMatchTerms[11] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__IN_RULE_REGEX);
		// |Idiom::mixin.'mixin'|
		serializationMatchTerms[12] = createSerializationMatchTermEAttributeSize(IdiomsPackage.Literals.IDIOM__MIXIN, 1 /* 'mixin' */);
		// |Idiom::name|
		serializationMatchTerms[13] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__NAME);
		// |Idiom::ownedSubIdioms|
		serializationMatchTerms[14] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS);
		// |IdiomsImport::as|
		serializationMatchTerms[15] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_IMPORT__AS);
		// |IdiomsImport::idiomsModel|
		serializationMatchTerms[16] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL);
		// |IdiomsModel::name|
		serializationMatchTerms[17] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__NAME);
		// |IdiomsModel::ownedIdioms|
		serializationMatchTerms[18] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS);
		// |IdiomsModel::ownedImports|
		serializationMatchTerms[19] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORTS);
		// |IdiomsModel::ownedLocatorDeclarations|
		serializationMatchTerms[20] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS);
		// |IdiomsModel::ownedSegmentDeclarations|
		serializationMatchTerms[21] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS);
		// |IdiomsModel::ownedWiths|
		serializationMatchTerms[22] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS);
		// |KeywordLocator::string|
		serializationMatchTerms[23] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.KEYWORD_LOCATOR__STRING);
		// |LocatorDeclaration::name|
		serializationMatchTerms[24] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.LOCATOR_DECLARATION__NAME);
		// |LocatorDeclaration::ownedLocator|
		serializationMatchTerms[25] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR);
		// |ReferredLocator::idiomsModel|
		serializationMatchTerms[26] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.REFERRED_LOCATOR__IDIOMS_MODEL);
		// |ReferredLocator::locatorDeclaration|
		serializationMatchTerms[27] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION);
		// |ReferredSegment::idiomsModel|
		serializationMatchTerms[28] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.REFERRED_SEGMENT__IDIOMS_MODEL);
		// |ReferredSegment::segmentDeclaration|
		serializationMatchTerms[29] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION);
		// |ReturnsLocator::eClass|
		serializationMatchTerms[30] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS);
		// |ReturnsLocator::ePackage|
		serializationMatchTerms[31] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.RETURNS_LOCATOR__EPACKAGE);
		// |SegmentDeclaration::name|
		serializationMatchTerms[32] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SEGMENT_DECLARATION__NAME);
		// |SegmentDeclaration::ownedSegment|
		serializationMatchTerms[33] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT);
		// |StringSegment::printable.'printable'|
		serializationMatchTerms[34] = createSerializationMatchTermEAttributeSize(IdiomsPackage.Literals.STRING_SEGMENT__PRINTABLE, 2 /* 'printable' */);
		// |StringSegment::string|
		serializationMatchTerms[35] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.STRING_SEGMENT__STRING);
		// |SubIdiom::all.'all'|
		serializationMatchTerms[36] = createSerializationMatchTermEAttributeSize(IdiomsPackage.Literals.SUB_IDIOM__ALL, 0 /* 'all' */);
		// |SubIdiom::ownedLocator|
		serializationMatchTerms[37] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR);
		// |SubIdiom::ownedSegments|
		serializationMatchTerms[38] = new SerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS);
		// (|AssignmentLocator::eStructuralFeature| - 1)
		serializationMatchTerms[39] = createSerializationMatchTermSubtract(4, 1);
		// (|CompoundLocator::ownedLocators| - 1)
		serializationMatchTerms[40] = createSerializationMatchTermSubtract(5, 1);
		// (|CustomSegment::supportClassName| - 1)
		serializationMatchTerms[41] = createSerializationMatchTermSubtract(6, 1);
		// (|EPackageImport::ePackage| - 1)
		serializationMatchTerms[42] = createSerializationMatchTermSubtract(8, 1);
		// (|Idiom::name| - 1)
		serializationMatchTerms[43] = createSerializationMatchTermSubtract(13, 1);
		// (|Idiom::ownedSubIdioms| - 1)
		serializationMatchTerms[44] = createSerializationMatchTermSubtract(14, 1);
		// (|IdiomsImport::idiomsModel| - 1)
		serializationMatchTerms[45] = createSerializationMatchTermSubtract(16, 1);
		// (|IdiomsModel::name| - 1)
		serializationMatchTerms[46] = createSerializationMatchTermSubtract(17, 1);
		// (|KeywordLocator::string| - 1)
		serializationMatchTerms[47] = createSerializationMatchTermSubtract(23, 1);
		// (|LocatorDeclaration::name| - 1)
		serializationMatchTerms[48] = createSerializationMatchTermSubtract(24, 1);
		// (|LocatorDeclaration::ownedLocator| - 1)
		serializationMatchTerms[49] = createSerializationMatchTermSubtract(25, 1);
		// (|ReferredLocator::locatorDeclaration| - 1)
		serializationMatchTerms[50] = createSerializationMatchTermSubtract(27, 1);
		// (|ReferredSegment::segmentDeclaration| - 1)
		serializationMatchTerms[51] = createSerializationMatchTermSubtract(29, 1);
		// (|ReturnsLocator::eClass| - 1)
		serializationMatchTerms[52] = createSerializationMatchTermSubtract(30, 1);
		// (|SegmentDeclaration::name| - 1)
		serializationMatchTerms[53] = createSerializationMatchTermSubtract(32, 1);
		// (|SegmentDeclaration::ownedSegment| - 1)
		serializationMatchTerms[54] = createSerializationMatchTermSubtract(33, 1);
		// (|StringSegment::printable.'printable'| - 1)
		serializationMatchTerms[55] = createSerializationMatchTermSubtract(34, 1);
		// (|StringSegment::string| - 1)
		serializationMatchTerms[56] = createSerializationMatchTermSubtract(35, 1);
		// (|SubIdiom::ownedLocator| - 1)
		serializationMatchTerms[57] = createSerializationMatchTermSubtract(37, 1);
		// (|SubIdiom::ownedSegments| > 0)
		serializationMatchTerms[58] = createSerializationMatchTermGreaterThan(38, 0);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules() {
		// Idioms::AnyAssignmentLocator(idioms::AnyAssignmentLocator): "any-assignment"
		serializationRules[0] =
			new SerializationRule("AnyAssignmentLocator", 1,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					37		/* 'any-assignment' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::AnyElementLocator(idioms::AnyElementLocator): "any-element"
		serializationRules[1] =
			new SerializationRule("AnyElementLocator", 2,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					38		/* 'any-element' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::AssignmentLocator(idioms::AssignmentLocator): { "assignment" { { ePackage=ID "::" }[?] eClass=ID "::" }[?] eStructuralFeature=ID }
		serializationRules[2] =
			new SerializationRule("AssignmentLocator", 3,
				createSerializationMatchSteps(
					0		/* assert (|AssignmentLocator::eStructuralFeature| - 1) == 0 */,
					20		/* assign V0 = |AssignmentLocator::eClass| */,
					30		/* assign V1 = |AssignmentLocator::ePackage| */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					40		/* 'assignment' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					81		/* V00*9-steps || value */,
					83		/* V01*4-steps || value */,
					76		/* 1*1-steps || value */,
					4		/* AssignmentLocator::ePackage=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					34		/* '::' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value no-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					2		/* AssignmentLocator::eClass=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					34		/* '::' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value no-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					7		/* AssignmentLocator::eStructuralFeature=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ECLASS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__EPACKAGE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// Idioms::CompoundLocator(idioms::CompoundLocator): { "{" ownedLocators+=ElementLocator { "|" ownedLocators+=ElementLocator }[*] "}" }
		serializationRules[3] =
			new SerializationRule("CompoundLocator", 4,
				createSerializationMatchSteps(
					41		/* check-rule idioms::CompoundLocator.ownedLocators : 7 */,
					18		/* assign V0 = (|CompoundLocator::ownedLocators| - 1) */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					73		/* '{' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value push soft-new-line supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					24		/* CompoundLocator::ownedLocators+=7 || value */,
					79		/* V00*3-steps || value */,
					76		/* 1*1-steps || value */,
					74		/* '|' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					24		/* CompoundLocator::ownedLocators+=7 || value */,
					76		/* 1*1-steps || value */,
					75		/* '}' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport pop soft-space value soft-new-line supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(IdiomsPackage.Literals.COMPOUND_LOCATOR__OWNED_LOCATORS,
						1) /* ElementLocator */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.COMPOUND_LOCATOR__OWNED_LOCATORS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(7, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// Idioms::CustomSegment(idioms::CustomSegment): { "custom" supportClassName=STRING }
		serializationRules[4] =
			new SerializationRule("CustomSegment", 5,
				createSerializationMatchSteps(
					1		/* assert (|CustomSegment::supportClassName| - 1) == 0 */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					42		/* 'custom' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					33		/* CustomSegment::supportClassName=29 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					IdiomsPackage.Literals.CUSTOM_SEGMENT__SUPPORT_CLASS_NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.CUSTOM_SEGMENT__SUPPORT_CLASS_NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// Idioms::EPackageImport(idioms::EPackageImport): { "import" ePackage=STRING { "as" as=ID }[?] }
		serializationRules[5] =
			new SerializationRule("EPackageImport", 6,
				createSerializationMatchSteps(
					21		/* assign V0 = |EPackageImport::as| */,
					2		/* assert (|EPackageImport::ePackage| - 1) == 0 */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					49		/* 'import' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					5		/* EPackageImport::ePackage=STRING || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					80		/* V00*4-steps || value */,
					76		/* 1*1-steps || value */,
					39		/* 'as' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					0		/* EPackageImport::as=10 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.EPACKAGE_IMPORT__AS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.EPACKAGE_IMPORT__EPACKAGE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// Idioms::FinalLocator(idioms::FinalLocator): "final"
		serializationRules[6] =
			new SerializationRule("FinalLocator", 8,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					45		/* 'final' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::HalfNewLineSegment(idioms::HalfNewLineSegment): "half-new-line"
		serializationRules[7] =
			new SerializationRule("HalfNewLineSegment", 9,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					47		/* 'half-new-line' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::Idiom(idioms::Idiom): { mixin="mixin"[?] "idiom" name=ID { "for" { forEPackage=ID "::" }[?] forEClass=ID }[?] { "in" inRuleRegex=STRING }[?] ownedSubIdioms+=SubIdiom }
		serializationRules[8] =
			new SerializationRule("Idiom", 12,
				createSerializationMatchSteps(
					42		/* check-rule idioms::Idiom.ownedSubIdioms : 35 */,
					4		/* assert (|Idiom::ownedSubIdioms| - 1) == 0 */,
					37		/* assign V3 = |Idiom::inRuleRegex| */,
					31		/* assign V1 = |Idiom::forEClass| */,
					34		/* assign V2 = |Idiom::forEPackage| */,
					3		/* assert (|Idiom::name| - 1) == 0 */,
					22		/* assign V0 = |Idiom::mixin.'mixin'| */
				),
				createSerializationSteps(
					78		/* V00*1-steps || value */,
					52		/* 'mixin' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					48		/* 'idiom' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					15		/* Idiom::name=10 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					84		/* V01*9-steps || value */,
					76		/* 1*1-steps || value */,
					46		/* 'for' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					87		/* V02*4-steps || value */,
					76		/* 1*1-steps || value */,
					9		/* Idiom::forEPackage=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					34		/* '::' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value no-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					8		/* Idiom::forEClass=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					89		/* V03*4-steps || value */,
					76		/* 1*1-steps || value */,
					50		/* 'in' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					13		/* Idiom::inRuleRegex=29 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					28		/* Idiom::ownedSubIdioms+=35 || value soft-new-line */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(IdiomsPackage.Literals.IDIOM__MIXIN,
						1	/* 'mixin' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS,
						11) /* SubIdiom */
				},
				new /*@NonNull*/ EAttribute [] {
					IdiomsPackage.Literals.IDIOM__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.IDIOM__IN_RULE_REGEX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.IDIOM__MIXIN,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* 'mixin' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.IDIOM__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.IDIOM__FOR_ECLASS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(35, GrammarCardinality.ONE)
						}
					)
				});
			;
		// Idioms::Idiom(idioms::Idiom): { mixin="mixin"[?] "idiom" name=ID { "for" { forEPackage=ID "::" }[?] forEClass=ID }[?] { "in" inRuleRegex=STRING }[?] { "{" ownedSubIdioms+=SubIdiom[*] "}" } }
		serializationRules[9] =
			new SerializationRule("Idiom", 12,
				createSerializationMatchSteps(
					42		/* check-rule idioms::Idiom.ownedSubIdioms : 35 */,
					39		/* assign V4 = |Idiom::ownedSubIdioms| */,
					37		/* assign V3 = |Idiom::inRuleRegex| */,
					31		/* assign V1 = |Idiom::forEClass| */,
					34		/* assign V2 = |Idiom::forEPackage| */,
					3		/* assert (|Idiom::name| - 1) == 0 */,
					22		/* assign V0 = |Idiom::mixin.'mixin'| */
				),
				createSerializationSteps(
					78		/* V00*1-steps || value */,
					52		/* 'mixin' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					48		/* 'idiom' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					15		/* Idiom::name=10 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					84		/* V01*9-steps || value */,
					76		/* 1*1-steps || value */,
					46		/* 'for' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					87		/* V02*4-steps || value */,
					76		/* 1*1-steps || value */,
					9		/* Idiom::forEPackage=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					34		/* '::' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value no-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					8		/* Idiom::forEClass=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					89		/* V03*4-steps || value */,
					76		/* 1*1-steps || value */,
					50		/* 'in' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					13		/* Idiom::inRuleRegex=29 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					77		/* 1*6-steps || value */,
					76		/* 1*1-steps || value */,
					73		/* '{' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value push soft-new-line supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					90		/* V04*1-steps || value */,
					28		/* Idiom::ownedSubIdioms+=35 || value soft-new-line */,
					76		/* 1*1-steps || value */,
					75		/* '}' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport pop soft-space value soft-new-line supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(IdiomsPackage.Literals.IDIOM__MIXIN,
						1	/* 'mixin' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS,
						11) /* SubIdiom */
				},
				new /*@NonNull*/ EAttribute [] {
					IdiomsPackage.Literals.IDIOM__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.IDIOM__IN_RULE_REGEX,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.IDIOM__MIXIN,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* 'mixin' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.IDIOM__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.IDIOM__FOR_ECLASS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(35, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Idioms::IdiomsImport(idioms::IdiomsImport): { "with" idiomsModel=STRING { "as" as=ID }[?] }
		serializationRules[10] =
			new SerializationRule("IdiomsImport", 13,
				createSerializationMatchSteps(
					23		/* assign V0 = |IdiomsImport::as| */,
					5		/* assert (|IdiomsImport::idiomsModel| - 1) == 0 */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					67		/* 'with' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					10		/* IdiomsImport::idiomsModel=STRING || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					80		/* V00*4-steps || value */,
					76		/* 1*1-steps || value */,
					39		/* 'as' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					1		/* IdiomsImport::as=10 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.IDIOMS_IMPORT__AS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// Idioms::IdiomsModel(idioms::IdiomsModel): { "model" name=ID ownedWiths+=IdiomsImport[*] ownedImports+=EPackageImport[*] { ownedLocatorDeclarations+=LocatorDeclaration[*] ownedSegmentDeclarations+=SegmentDeclaration[*] ownedIdioms+=Idiom[*] } }
		serializationRules[11] =
			new SerializationRule("IdiomsModel", 14,
				createSerializationMatchSteps(
					43		/* check-rule idioms::IdiomsModel.ownedIdioms : 12 */,
					44		/* check-rule idioms::IdiomsModel.ownedImports : 6 */,
					45		/* check-rule idioms::IdiomsModel.ownedLocatorDeclarations : 17 */,
					46		/* check-rule idioms::IdiomsModel.ownedSegmentDeclarations : 31 */,
					47		/* check-rule idioms::IdiomsModel.ownedWiths : 13 */,
					40		/* assign V4 = |IdiomsModel::ownedIdioms| */,
					38		/* assign V3 = |IdiomsModel::ownedSegmentDeclarations| */,
					35		/* assign V2 = |IdiomsModel::ownedLocatorDeclarations| */,
					32		/* assign V1 = |IdiomsModel::ownedImports| */,
					24		/* assign V0 = |IdiomsModel::ownedWiths| */,
					6		/* assert (|IdiomsModel::name| - 1) == 0 */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					53		/* 'model' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					16		/* IdiomsModel::name=10 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					78		/* V00*1-steps || value */,
					29		/* IdiomsModel::ownedWiths+=13 || soft-new-line value soft-new-line */,
					82		/* V01*1-steps || value */,
					20		/* IdiomsModel::ownedImports+=6 || soft-new-line value soft-new-line */,
					77		/* 1*6-steps || value */,
					86		/* V02*1-steps || new-line soft-new-line value soft-new-line */,
					23		/* IdiomsModel::ownedLocatorDeclarations+=17 || value */,
					88		/* V03*1-steps || new-line soft-new-line value soft-new-line */,
					26		/* IdiomsModel::ownedSegmentDeclarations+=31 || value */,
					90		/* V04*1-steps || value */,
					19		/* IdiomsModel::ownedIdioms+=12 || new-line soft-new-line value soft-new-line */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS,
						2) /* Idiom */,
					createEReference_RuleIndexes(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORTS,
						0) /* EPackageImport */,
					createEReference_RuleIndexes(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS,
						5) /* LocatorDeclaration */,
					createEReference_RuleIndexes(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS,
						10) /* SegmentDeclaration */,
					createEReference_RuleIndexes(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS,
						3) /* IdiomsImport */
				},
				new /*@NonNull*/ EAttribute [] {
					IdiomsPackage.Literals.IDIOMS_MODEL__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.IDIOMS_MODEL__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(12, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(6, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(17, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(31, GrammarCardinality.ZERO_OR_MORE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Idioms::KeywordLocator(idioms::KeywordLocator): string=STRING
		serializationRules[12] =
			new SerializationRule("KeywordLocator", 15,
				createSerializationMatchSteps(
					7		/* assert (|KeywordLocator::string| - 1) == 0 */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					31		/* KeywordLocator::string=29 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					IdiomsPackage.Literals.KEYWORD_LOCATOR__STRING
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.KEYWORD_LOCATOR__STRING,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// Idioms::LocatorDeclaration(idioms::LocatorDeclaration): { "locator" name=ID ownedLocator=Locator ";" }
		serializationRules[13] =
			new SerializationRule("LocatorDeclaration", 17,
				createSerializationMatchSteps(
					48		/* check-rule idioms::LocatorDeclaration.ownedLocator : 16 */,
					9		/* assert (|LocatorDeclaration::ownedLocator| - 1) == 0 */,
					8		/* assert (|LocatorDeclaration::name| - 1) == 0 */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					51		/* 'locator' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					17		/* LocatorDeclaration::name=10 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					21		/* LocatorDeclaration::ownedLocator=16 || value */,
					76		/* 1*1-steps || value */,
					35		/* ';' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value soft-new-line supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR,
						4) /* Locator */
				},
				new /*@NonNull*/ EAttribute [] {
					IdiomsPackage.Literals.LOCATOR_DECLARATION__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.LOCATOR_DECLARATION__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ONE)
						}
					)
				});
			;
		// Idioms::NewLineSegment(idioms::NewLineSegment): "new-line"
		serializationRules[14] =
			new SerializationRule("NewLineSegment", 19,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					54		/* 'new-line' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::NoSpaceSegment(idioms::NoSpaceSegment): "no-space"
		serializationRules[15] =
			new SerializationRule("NoSpaceSegment", 20,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					55		/* 'no-space' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::PopSegment(idioms::PopSegment): "pop"
		serializationRules[16] =
			new SerializationRule("PopSegment", 21,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					56		/* 'pop' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::PostCommentSegment(idioms::PostCommentSegment): "post-comment"
		serializationRules[17] =
			new SerializationRule("PostCommentSegment", 22,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					57		/* 'post-comment' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::PreCommentSegment(idioms::PreCommentSegment): "pre-comment"
		serializationRules[18] =
			new SerializationRule("PreCommentSegment", 23,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					58		/* 'pre-comment' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::PushSegment(idioms::PushSegment): "push"
		serializationRules[19] =
			new SerializationRule("PushSegment", 24,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					60		/* 'push' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::ReferredLocator(idioms::ReferredLocator): { { idiomsModel=ID "::" }[?] locatorDeclaration=ID }
		serializationRules[20] =
			new SerializationRule("ReferredLocator", 25,
				createSerializationMatchSteps(
					10		/* assert (|ReferredLocator::locatorDeclaration| - 1) == 0 */,
					25		/* assign V0 = |ReferredLocator::idiomsModel| */
				),
				createSerializationSteps(
					80		/* V00*4-steps || value */,
					76		/* 1*1-steps || value */,
					11		/* ReferredLocator::idiomsModel=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					34		/* '::' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value no-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					14		/* ReferredLocator::locatorDeclaration=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.REFERRED_LOCATOR__IDIOMS_MODEL,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// Idioms::ReferredSegment(idioms::ReferredSegment): { { idiomsModel=ID "::" }[?] segmentDeclaration=ID }
		serializationRules[21] =
			new SerializationRule("ReferredSegment", 26,
				createSerializationMatchSteps(
					11		/* assert (|ReferredSegment::segmentDeclaration| - 1) == 0 */,
					26		/* assign V0 = |ReferredSegment::idiomsModel| */
				),
				createSerializationSteps(
					80		/* V00*4-steps || value */,
					76		/* 1*1-steps || value */,
					12		/* ReferredSegment::idiomsModel=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					34		/* '::' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value no-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					30		/* ReferredSegment::segmentDeclaration=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.REFERRED_SEGMENT__IDIOMS_MODEL,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// Idioms::ReturnsLocator(idioms::ReturnsLocator): { "returns" { ePackage=ID "::" }[?] eClass=ID }
		serializationRules[22] =
			new SerializationRule("ReturnsLocator", 27,
				createSerializationMatchSteps(
					12		/* assert (|ReturnsLocator::eClass| - 1) == 0 */,
					27		/* assign V0 = |ReturnsLocator::ePackage| */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					61		/* 'returns' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					80		/* V00*4-steps || value */,
					76		/* 1*1-steps || value */,
					6		/* ReturnsLocator::ePackage=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					34		/* '::' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value no-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					3		/* ReturnsLocator::eClass=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.RETURNS_LOCATOR__EPACKAGE,
						new @NonNull RuleIndex_GrammarCardinality [] {
						}
					)
				});
			;
		// Idioms::SegmentDeclaration(idioms::SegmentDeclaration): { "segment" name=ID ownedSegment=Segment ";" }
		serializationRules[23] =
			new SerializationRule("SegmentDeclaration", 31,
				createSerializationMatchSteps(
					49		/* check-rule idioms::SegmentDeclaration.ownedSegment : 30 */,
					14		/* assert (|SegmentDeclaration::ownedSegment| - 1) == 0 */,
					13		/* assert (|SegmentDeclaration::name| - 1) == 0 */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					62		/* 'segment' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					18		/* SegmentDeclaration::name=10 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					25		/* SegmentDeclaration::ownedSegment=30 || value */,
					76		/* 1*1-steps || value */,
					35		/* ';' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value soft-new-line supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT,
						8) /* Segment */
				},
				new /*@NonNull*/ EAttribute [] {
					IdiomsPackage.Literals.SEGMENT_DECLARATION__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.SEGMENT_DECLARATION__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ONE)
						}
					)
				});
			;
		// Idioms::SoftNewLineSegment(idioms::SoftNewLineSegment): "soft-new-line"
		serializationRules[24] =
			new SerializationRule("SoftNewLineSegment", 32,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					63		/* 'soft-new-line' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::SoftSpaceSegment(idioms::SoftSpaceSegment): "soft-space"
		serializationRules[25] =
			new SerializationRule("SoftSpaceSegment", 33,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					64		/* 'soft-space' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::StringSegment(idioms::StringSegment): { "string" string=STRING printable="printable" }
		serializationRules[26] =
			new SerializationRule("StringSegment", 34,
				createSerializationMatchSteps(
					15		/* assert (|StringSegment::printable.'printable'| - 1) == 0 */,
					16		/* assert (|StringSegment::string| - 1) == 0 */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					65		/* 'string' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					32		/* StringSegment::string=29 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					59		/* 'printable' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(IdiomsPackage.Literals.STRING_SEGMENT__PRINTABLE,
						2	/* 'printable' */
					)
				},
				null,
				new /*@NonNull*/ EAttribute [] {
					IdiomsPackage.Literals.STRING_SEGMENT__STRING
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.STRING_SEGMENT__PRINTABLE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(2 /* 'printable' */, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.STRING_SEGMENT__STRING,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// Idioms::SubIdiom(idioms::SubIdiom): { "at" "each" ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" }
		serializationRules[27] =
			new SerializationRule("SubIdiom", 35,
				createSerializationMatchSteps(
					50		/* check-rule idioms::SubIdiom.ownedLocator : 16 */,
					51		/* check-rule idioms::SubIdiom.ownedSegments : 26|30 */,
					17		/* assert (|SubIdiom::ownedLocator| - 1) == 0 */,
					19		/* assign V0 = (|SubIdiom::ownedSegments| > 0) */,
					33		/* assign V1 = |SubIdiom::ownedSegments| */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					41		/* 'at' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					76		/* 1*1-steps || value */,
					44		/* 'each' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					22		/* SubIdiom::ownedLocator=16 || value */,
					80		/* V00*4-steps || value */,
					76		/* 1*1-steps || value */,
					43		/* 'do' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					82		/* V01*1-steps || value */,
					27		/* SubIdiom::ownedSegments+=30|26 || value */,
					76		/* 1*1-steps || value */,
					35		/* ';' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value soft-new-line supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR,
						4) /* Locator */,
					createEReference_RuleIndexes(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS,
						9) /* ReferredSegment|Segment */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(26, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Idioms::SubIdiom(idioms::SubIdiom): { "at" all="all"[?] ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" }
		serializationRules[28] =
			new SerializationRule("SubIdiom", 35,
				createSerializationMatchSteps(
					50		/* check-rule idioms::SubIdiom.ownedLocator : 16 */,
					51		/* check-rule idioms::SubIdiom.ownedSegments : 26|30 */,
					17		/* assert (|SubIdiom::ownedLocator| - 1) == 0 */,
					28		/* assign V0 = |SubIdiom::all.'all'| */,
					29		/* assign V1 = (|SubIdiom::ownedSegments| > 0) */,
					36		/* assign V2 = |SubIdiom::ownedSegments| */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					41		/* 'at' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					78		/* V00*1-steps || value */,
					36		/* 'all' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					22		/* SubIdiom::ownedLocator=16 || value */,
					83		/* V01*4-steps || value */,
					76		/* 1*1-steps || value */,
					43		/* 'do' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					85		/* V02*1-steps || value */,
					27		/* SubIdiom::ownedSegments+=30|26 || value */,
					76		/* 1*1-steps || value */,
					35		/* ';' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value soft-new-line supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(IdiomsPackage.Literals.SUB_IDIOM__ALL,
						0	/* 'all' */
					)
				},
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR,
						4) /* Locator */,
					createEReference_RuleIndexes(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS,
						9) /* ReferredSegment|Segment */
				},
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(IdiomsPackage.Literals.SUB_IDIOM__ALL,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(0 /* 'all' */, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(26, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Idioms::SubIdiom(idioms::SubIdiom): { "at" ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" }
		serializationRules[29] =
			new SerializationRule("SubIdiom", 35,
				createSerializationMatchSteps(
					50		/* check-rule idioms::SubIdiom.ownedLocator : 16 */,
					51		/* check-rule idioms::SubIdiom.ownedSegments : 26|30 */,
					17		/* assert (|SubIdiom::ownedLocator| - 1) == 0 */,
					19		/* assign V0 = (|SubIdiom::ownedSegments| > 0) */,
					33		/* assign V1 = |SubIdiom::ownedSegments| */
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					41		/* 'at' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					22		/* SubIdiom::ownedLocator=16 || value */,
					80		/* V00*4-steps || value */,
					76		/* 1*1-steps || value */,
					43		/* 'do' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */,
					82		/* V01*1-steps || value */,
					27		/* SubIdiom::ownedSegments+=30|26 || value */,
					76		/* 1*1-steps || value */,
					35		/* ';' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value soft-new-line supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR,
						4) /* Locator */,
					createEReference_RuleIndexes(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS,
						9) /* ReferredSegment|Segment */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(26, GrammarCardinality.ZERO_OR_MORE),
						new RuleIndex_GrammarCardinality(30, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Idioms::ValueSegment(idioms::ValueSegment): "value"
		serializationRules[30] =
			new SerializationRule("ValueSegment", 36,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					66		/* 'value' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::WrapAnchorSegment(idioms::WrapAnchorSegment): "wrap-anchor"
		serializationRules[31] =
			new SerializationRule("WrapAnchorSegment", 38,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					68		/* 'wrap-anchor' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::WrapBeginAllSegment(idioms::WrapBeginAllSegment): "wrap-begin-all"
		serializationRules[32] =
			new SerializationRule("WrapBeginAllSegment", 39,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					69		/* 'wrap-begin-all' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::WrapBeginSomeSegment(idioms::WrapBeginSomeSegment): "wrap-begin-some"
		serializationRules[33] =
			new SerializationRule("WrapBeginSomeSegment", 40,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					70		/* 'wrap-begin-some' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::WrapEndSegment(idioms::WrapEndSegment): "wrap-end"
		serializationRules[34] =
			new SerializationRule("WrapEndSegment", 41,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					71		/* 'wrap-end' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
				null);
			;
		// Idioms::WrapHereSegment(idioms::WrapHereSegment): "wrap-here"
		serializationRules[35] =
			new SerializationRule("WrapHereSegment", 42,
				createSerializationMatchSteps(
				),
				createSerializationSteps(
					76		/* 1*1-steps || value */,
					72		/* 'wrap-here' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
				),
				null,
				null,
				null,
				null,
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
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[2] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[3] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NEW_LINE /* new-line */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */
		};
		serializationSegments[4] = new @NonNull SerializationSegment @NonNull [] {
			new CustomSerializationSegment(XtextPreCommentSegmentSupport.class) /* supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport */,
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.NO_SPACE /* no-space */,
			new CustomSerializationSegment(XtextPostCommentSegmentSupport.class) /* supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
		};
		serializationSegments[5] = new @NonNull SerializationSegment @NonNull [] {
			new CustomSerializationSegment(XtextPreCommentSegmentSupport.class) /* supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport */,
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			new CustomSerializationSegment(XtextPostCommentSegmentSupport.class) /* supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
		};
		serializationSegments[6] = new @NonNull SerializationSegment @NonNull [] {
			new CustomSerializationSegment(XtextPreCommentSegmentSupport.class) /* supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			new CustomSerializationSegment(XtextPostCommentSegmentSupport.class) /* supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
		};
		serializationSegments[7] = new @NonNull SerializationSegment @NonNull [] {
			new CustomSerializationSegment(XtextPreCommentSegmentSupport.class) /* supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			new CustomSerializationSegment(XtextPostCommentSegmentSupport.class) /* supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
		};
		serializationSegments[8] = new @NonNull SerializationSegment @NonNull [] {
			new CustomSerializationSegment(XtextPreCommentSegmentSupport.class) /* supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			new CustomSerializationSegment(XtextPostCommentSegmentSupport.class) /* supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport */
		};
	}

	/**
	 * Initialize the various serialization steps used to serialize a serialization rule.
	 */
	private void initSerializationSteps() {
		// EPackageImport::as=10 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[0] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.EPACKAGE_IMPORT__AS, 10 /*ID*/, 6);
		// IdiomsImport::as=10 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[1] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_IMPORT__AS, 10 /*ID*/, 6);
		// AssignmentLocator::eClass=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[2] = createSerializationStepCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ECLASS, getCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ECLASS, "ID"), 6);
		// ReturnsLocator::eClass=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[3] = createSerializationStepCrossReference(IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS, getCrossReference(IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS, "ID"), 6);
		// AssignmentLocator::ePackage=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[4] = createSerializationStepCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__EPACKAGE, getCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__EPACKAGE, "ID"), 6);
		// EPackageImport::ePackage=STRING || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[5] = createSerializationStepCrossReference(IdiomsPackage.Literals.EPACKAGE_IMPORT__EPACKAGE, getCrossReference(IdiomsPackage.Literals.EPACKAGE_IMPORT__EPACKAGE, "STRING"), 6);
		// ReturnsLocator::ePackage=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[6] = createSerializationStepCrossReference(IdiomsPackage.Literals.RETURNS_LOCATOR__EPACKAGE, getCrossReference(IdiomsPackage.Literals.RETURNS_LOCATOR__EPACKAGE, "ID"), 6);
		// AssignmentLocator::eStructuralFeature=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[7] = createSerializationStepCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE, getCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE, "ID"), 6);
		// Idiom::forEClass=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[8] = createSerializationStepCrossReference(IdiomsPackage.Literals.IDIOM__FOR_ECLASS, getCrossReference(IdiomsPackage.Literals.IDIOM__FOR_ECLASS, "ID"), 6);
		// Idiom::forEPackage=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[9] = createSerializationStepCrossReference(IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE, getCrossReference(IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE, "ID"), 6);
		// IdiomsImport::idiomsModel=STRING || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[10] = createSerializationStepCrossReference(IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL, getCrossReference(IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL, "STRING"), 6);
		// ReferredLocator::idiomsModel=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[11] = createSerializationStepCrossReference(IdiomsPackage.Literals.REFERRED_LOCATOR__IDIOMS_MODEL, getCrossReference(IdiomsPackage.Literals.REFERRED_LOCATOR__IDIOMS_MODEL, "ID"), 6);
		// ReferredSegment::idiomsModel=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[12] = createSerializationStepCrossReference(IdiomsPackage.Literals.REFERRED_SEGMENT__IDIOMS_MODEL, getCrossReference(IdiomsPackage.Literals.REFERRED_SEGMENT__IDIOMS_MODEL, "ID"), 6);
		// Idiom::inRuleRegex=29 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[13] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOM__IN_RULE_REGEX, 29 /*STRING*/, 6);
		// ReferredLocator::locatorDeclaration=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[14] = createSerializationStepCrossReference(IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION, getCrossReference(IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION, "ID"), 6);
		// Idiom::name=10 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[15] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOM__NAME, 10 /*ID*/, 6);
		// IdiomsModel::name=10 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[16] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__NAME, 10 /*ID*/, 6);
		// LocatorDeclaration::name=10 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[17] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.LOCATOR_DECLARATION__NAME, 10 /*ID*/, 6);
		// SegmentDeclaration::name=10 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[18] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.SEGMENT_DECLARATION__NAME, 10 /*ID*/, 6);
		// IdiomsModel::ownedIdioms+=12 || new-line soft-new-line value soft-new-line
		serializationSteps[19] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS, 12 /*Idiom*/, 3);
		// IdiomsModel::ownedImports+=6 || soft-new-line value soft-new-line
		serializationSteps[20] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORTS, 6 /*EPackageImport*/, 2);
		// LocatorDeclaration::ownedLocator=16 || value
		serializationSteps[21] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR, 16 /*Locator*/, 0);
		// SubIdiom::ownedLocator=16 || value
		serializationSteps[22] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR, 16 /*Locator*/, 0);
		// IdiomsModel::ownedLocatorDeclarations+=17 || value
		serializationSteps[23] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS, 17 /*LocatorDeclaration*/, 0);
		// CompoundLocator::ownedLocators+=7 || value
		serializationSteps[24] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.COMPOUND_LOCATOR__OWNED_LOCATORS, 7 /*ElementLocator*/, 0);
		// SegmentDeclaration::ownedSegment=30 || value
		serializationSteps[25] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT, 30 /*Segment*/, 0);
		// IdiomsModel::ownedSegmentDeclarations+=31 || value
		serializationSteps[26] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS, 31 /*SegmentDeclaration*/, 0);
		// SubIdiom::ownedSegments+=30|26 || value
		serializationSteps[27] = createSerializationStepAssigns(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS, -1, new @NonNull Integer [] { 30/*Segment*/,26/*ReferredSegment*/}, 0);
		// Idiom::ownedSubIdioms+=35 || value soft-new-line
		serializationSteps[28] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS, 35 /*SubIdiom*/, 1);
		// IdiomsModel::ownedWiths+=13 || soft-new-line value soft-new-line
		serializationSteps[29] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS, 13 /*IdiomsImport*/, 2);
		// ReferredSegment::segmentDeclaration=ID || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[30] = createSerializationStepCrossReference(IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION, getCrossReference(IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION, "ID"), 6);
		// KeywordLocator::string=29 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[31] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.KEYWORD_LOCATOR__STRING, 29 /*STRING*/, 6);
		// StringSegment::string=29 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[32] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.STRING_SEGMENT__STRING, 29 /*STRING*/, 6);
		// CustomSegment::supportClassName=29 || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[33] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.CUSTOM_SEGMENT__SUPPORT_CLASS_NAME, 29 /*STRING*/, 6);
		// '::' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value no-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[34] = createSerializationStepKeyword("::", 4);
		// ';' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport no-space value soft-new-line supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[35] = createSerializationStepKeyword(";", 5);
		// 'all' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[36] = createSerializationStepKeyword("all", 6);
		// 'any-assignment' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[37] = createSerializationStepKeyword("any-assignment", 6);
		// 'any-element' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[38] = createSerializationStepKeyword("any-element", 6);
		// 'as' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[39] = createSerializationStepKeyword("as", 6);
		// 'assignment' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[40] = createSerializationStepKeyword("assignment", 6);
		// 'at' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[41] = createSerializationStepKeyword("at", 6);
		// 'custom' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[42] = createSerializationStepKeyword("custom", 6);
		// 'do' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[43] = createSerializationStepKeyword("do", 6);
		// 'each' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[44] = createSerializationStepKeyword("each", 6);
		// 'final' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[45] = createSerializationStepKeyword("final", 6);
		// 'for' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[46] = createSerializationStepKeyword("for", 6);
		// 'half-new-line' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[47] = createSerializationStepKeyword("half-new-line", 6);
		// 'idiom' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[48] = createSerializationStepKeyword("idiom", 6);
		// 'import' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[49] = createSerializationStepKeyword("import", 6);
		// 'in' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[50] = createSerializationStepKeyword("in", 6);
		// 'locator' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[51] = createSerializationStepKeyword("locator", 6);
		// 'mixin' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[52] = createSerializationStepKeyword("mixin", 6);
		// 'model' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[53] = createSerializationStepKeyword("model", 6);
		// 'new-line' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[54] = createSerializationStepKeyword("new-line", 6);
		// 'no-space' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[55] = createSerializationStepKeyword("no-space", 6);
		// 'pop' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[56] = createSerializationStepKeyword("pop", 6);
		// 'post-comment' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[57] = createSerializationStepKeyword("post-comment", 6);
		// 'pre-comment' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[58] = createSerializationStepKeyword("pre-comment", 6);
		// 'printable' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[59] = createSerializationStepKeyword("printable", 6);
		// 'push' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[60] = createSerializationStepKeyword("push", 6);
		// 'returns' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[61] = createSerializationStepKeyword("returns", 6);
		// 'segment' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[62] = createSerializationStepKeyword("segment", 6);
		// 'soft-new-line' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[63] = createSerializationStepKeyword("soft-new-line", 6);
		// 'soft-space' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[64] = createSerializationStepKeyword("soft-space", 6);
		// 'string' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[65] = createSerializationStepKeyword("string", 6);
		// 'value' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[66] = createSerializationStepKeyword("value", 6);
		// 'with' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[67] = createSerializationStepKeyword("with", 6);
		// 'wrap-anchor' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[68] = createSerializationStepKeyword("wrap-anchor", 6);
		// 'wrap-begin-all' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[69] = createSerializationStepKeyword("wrap-begin-all", 6);
		// 'wrap-begin-some' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[70] = createSerializationStepKeyword("wrap-begin-some", 6);
		// 'wrap-end' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[71] = createSerializationStepKeyword("wrap-end", 6);
		// 'wrap-here' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[72] = createSerializationStepKeyword("wrap-here", 6);
		// '{' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value push soft-new-line supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[73] = createSerializationStepKeyword("{", 8);
		// '|' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport soft-space value soft-space supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[74] = createSerializationStepKeyword("|", 6);
		// '}' || supported by org.eclipse.ocl.examples.xtext.serializer.XtextPreCommentSegmentSupport pop soft-space value soft-new-line supported by org.eclipse.ocl.examples.xtext.serializer.XtextPostCommentSegmentSupport
		serializationSteps[75] = createSerializationStepKeyword("}", 7);
		// 1*1-steps || value
		serializationSteps[76] = createSerializationStepSequence(-1, 1, 0);
		// 1*6-steps || value
		serializationSteps[77] = createSerializationStepSequence(-1, 6, 0);
		// V00*1-steps || value
		serializationSteps[78] = createSerializationStepSequence(0, 1, 0);
		// V00*3-steps || value
		serializationSteps[79] = createSerializationStepSequence(0, 3, 0);
		// V00*4-steps || value
		serializationSteps[80] = createSerializationStepSequence(0, 4, 0);
		// V00*9-steps || value
		serializationSteps[81] = createSerializationStepSequence(0, 9, 0);
		// V01*1-steps || value
		serializationSteps[82] = createSerializationStepSequence(1, 1, 0);
		// V01*4-steps || value
		serializationSteps[83] = createSerializationStepSequence(1, 4, 0);
		// V01*9-steps || value
		serializationSteps[84] = createSerializationStepSequence(1, 9, 0);
		// V02*1-steps || value
		serializationSteps[85] = createSerializationStepSequence(2, 1, 0);
		// V02*1-steps || new-line soft-new-line value soft-new-line
		serializationSteps[86] = createSerializationStepSequence(2, 1, 3);
		// V02*4-steps || value
		serializationSteps[87] = createSerializationStepSequence(2, 4, 0);
		// V03*1-steps || new-line soft-new-line value soft-new-line
		serializationSteps[88] = createSerializationStepSequence(3, 1, 3);
		// V03*4-steps || value
		serializationSteps[89] = createSerializationStepSequence(3, 4, 0);
		// V04*1-steps || value
		serializationSteps[90] = createSerializationStepSequence(4, 1, 0);
	}
}

//	Commented imports ensure Xtend provides a true import allowing unqualified annotated usage
//	import Inject;
//	import EAttribute;
//	import NonNull;
//	import Nullable;
//	import IdiomsPackage;
//	import EClassValue;
//	import EnumerationValue;
//	import EnumerationValueSingle;
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
//	import CustomSerializationSegment;
//	import SerializationStep;
//	import TerminalRuleValue;
//	import XtextPostCommentSegmentSupport;
//	import XtextPreCommentSegmentSupport;
