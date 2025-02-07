/*******************************************************************************
 * Copyright (c) 2011, 2025 Willink Transformations and others.
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
package org.eclipse.ocl.xtext.idioms.serializer;

import com.google.inject.Inject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.serializer.AbstractSerializationMetaData;
import org.eclipse.ocl.xtext.base.serializer.EClassValue;
import org.eclipse.ocl.xtext.base.serializer.EClassValue.EReference_TargetGrammarRuleVector;
import org.eclipse.ocl.xtext.base.serializer.EnumerationValue;
import org.eclipse.ocl.xtext.base.serializer.EnumerationValue.EnumerationValueSingle;
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
import org.eclipse.ocl.xtext.idioms.IdiomsPackage;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.service.GrammarProvider;

/******* This file is 100% auto-generated - do not edit it *******/

/**
 * The IdiomsSerializationMetaData singleton provides the metadata to support a
 * model to text serialization of a parsed Xtext semantic model or to re-format an Xtext text node model.
 */
public class IdiomsSerializationMetaData extends AbstractSerializationMetaData
{
	/**
	 * The Provider supports injected creation of the IdiomsSerializationMetaData singleton.
	 */
	public static class Provider implements SerializationMetaData.Provider
	{
		private static @Nullable IdiomsSerializationMetaData INSTANCE = null;

		@Inject
		private GrammarProvider grammarProvider;

		@Override
		public synchronized @NonNull SerializationMetaData get() {
			// synchronized synchronizes the creation of this singleton.
			// It does not imply that the overall application is threadsafe.
			IdiomsSerializationMetaData instance = INSTANCE;
			if (instance == null) {
				assert grammarProvider != null;
				Grammar grammar = grammarProvider.getGrammar(Provider.class);
				assert grammar != null;
				INSTANCE = instance = new IdiomsSerializationMetaData(grammar);
			}
			return instance;
		}
	}

	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[35];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[3];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[44];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[13];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[55];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[65];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[38];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [9] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[99];
	private final @NonNull SubstringStep @NonNull [] substringSteps = new @NonNull SubstringStep[0];
	private final @Nullable String @Nullable [] multipleLineCommentMidfixes = new @Nullable String[] {" *"};
	private final @NonNull String @Nullable [] multipleLineCommentPrefixes = new @NonNull String[] {"/*"};
	private final @NonNull String @Nullable [] multipleLineCommentSuffixes = new @NonNull String[] {"*/"};
	private final @NonNull String @Nullable [] singleLineCommentPrefixes = new @NonNull String[] {"//"};

	private IdiomsSerializationMetaData(@NonNull Grammar grammar) {
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
		return 41;
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
		return 40;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 82;
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

	@Override
	public @NonNull SubstringStep @NonNull [] getSubstringSteps() {
		return substringSteps;
	}

	/**
	 * Initialize configuration for each EClassifier that may be serialized.
	 */
	private void initEClassValues() {
		eClassValues[0] = new EClassValue(IdiomsPackage.Literals.ANY_ASSIGNMENT_LOCATOR,
			createSerializationRules(
				0 /* AnyAssignmentLocator-0: 'any-assignment' */
			), null
		);
		eClassValues[1] = new EClassValue(IdiomsPackage.Literals.ANY_ELEMENT_LOCATOR,
			createSerializationRules(
				1 /* AnyElementLocator-0: 'any-element' */
			), null
		);
		eClassValues[2] = new EClassValue(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR,
			createSerializationRules(
				2 /* AssignmentLocator-0: 'assignment' ((AssignmentLocator::ePackage=ID '::')[V1:?] AssignmentLocator::eClass=ID '::')[V0:?] AssignmentLocator::eStructuralFeature=ID */
			), null
		);
		eClassValues[3] = new EClassValue(IdiomsPackage.Literals.CUSTOM_SEGMENT,
			createSerializationRules(
				3 /* CustomSegment-0: 'custom' CustomSegment::supportClassName=STRING */
			), null
		);
		eClassValues[4] = new EClassValue(IdiomsPackage.Literals.EPACKAGE_DECLARATION,
			createSerializationRules(
				4 /* EPackageDeclaration-0: 'import' EPackageDeclaration::ePackage=STRING ('as' EPackageDeclaration::as=ID)[V0:?] */
			), null
		);
		eClassValues[5] = new EClassValue(IdiomsPackage.Literals.FINAL_LOCATOR,
			createSerializationRules(
				5 /* FinalLocator-0: 'final' */
			), null
		);
		eClassValues[6] = new EClassValue(IdiomsPackage.Literals.GRAMMAR_DECLARATION,
			createSerializationRules(
				6 /* GrammarDeclaration-0: 'grammar' GrammarDeclaration::grammar=STRING ('as' GrammarDeclaration::as=ID)[V0:?] */
			), null
		);
		eClassValues[7] = new EClassValue(IdiomsPackage.Literals.HALF_NEW_LINE_SEGMENT,
			createSerializationRules(
				7 /* HalfNewLineSegment-0: 'half-new-line' */
			), null
		);
		eClassValues[8] = new EClassValue(IdiomsPackage.Literals.IDIOM,
			createSerializationRules(
				9 /* Idiom-1: (Idiom::mixin?='mixin')[V0:?] 'idiom' Idiom::name=ID ('for' (Idiom::forEPackage=ID '::')[V2:?] Idiom::forEClass=ID)[V1:?] ('in' Idiom::inRuleRegex=STRING)[V3:?] Idiom::ownedSubIdioms+=SubIdiom */,
				8 /* Idiom-0: (Idiom::mixin?='mixin')[V0:?] 'idiom' Idiom::name=ID ('for' (Idiom::forEPackage=ID '::')[V2:?] Idiom::forEClass=ID)[V1:?] ('in' Idiom::inRuleRegex=STRING)[V3:?] '{' (Idiom::ownedSubIdioms+=SubIdiom)[V4:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS,
					10) /* SubIdiom */
			}
		);
		eClassValues[9] = new EClassValue(IdiomsPackage.Literals.IDIOMS_IMPORT,
			createSerializationRules(
				10 /* IdiomsImport-0: 'with' IdiomsImport::idiomsModel=STRING ('as' IdiomsImport::as=ID)[V0:?] */
			), null
		);
		eClassValues[10] = new EClassValue(IdiomsPackage.Literals.IDIOMS_MODEL,
			createSerializationRules(
				11 /* IdiomsModel-0: 'model' IdiomsModel::names+=ID ('.' IdiomsModel::names+=ID)[V0:*] (IdiomsModel::ownedWiths+=IdiomsImport)[V1:*] (IdiomsModel::ownedImportDeclarations+=EPackageDeclaration)[V2:*] (IdiomsModel::ownedGrammarDeclarations+=GrammarDeclaration)[V3:*] (IdiomsModel::ownedLocatorDeclarations+=LocatorDeclaration)[V4:*] (IdiomsModel::ownedSegmentDeclarations+=SegmentDeclaration)[V5:*] (IdiomsModel::ownedIdioms+=Idiom)[V6:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_GRAMMAR_DECLARATIONS,
					1) /* GrammarDeclaration */,
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS,
					2) /* Idiom */,
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORT_DECLARATIONS,
					0) /* EPackageDeclaration */,
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS,
					5) /* LocatorDeclaration */,
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS,
					9) /* SegmentDeclaration */,
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS,
					3) /* IdiomsImport */
			}
		);
		eClassValues[11] = new EClassValue(IdiomsPackage.Literals.KEYWORD_LOCATOR,
			createSerializationRules(
				12 /* KeywordLocator-0: KeywordLocator::string=STRING */
			), null
		);
		eClassValues[12] = new EClassValue(IdiomsPackage.Literals.LOCATOR_DECLARATION,
			createSerializationRules(
				13 /* LocatorDeclaration-0: 'locator' LocatorDeclaration::name=ID LocatorDeclaration::ownedLocator=Locator ';' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR,
					6) /* AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator|RuleLocator */
			}
		);
		eClassValues[13] = new EClassValue(IdiomsPackage.Literals.NEW_LINE_SEGMENT,
			createSerializationRules(
				14 /* NewLineSegment-0: 'new-line' */
			), null
		);
		eClassValues[14] = new EClassValue(IdiomsPackage.Literals.NO_NEW_LINE_SEGMENT,
			createSerializationRules(
				15 /* NoNewLineSegment-0: 'no-new-line' */
			), null
		);
		eClassValues[15] = new EClassValue(IdiomsPackage.Literals.NO_SPACE_SEGMENT,
			createSerializationRules(
				16 /* NoSpaceSegment-0: 'no-space' */
			), null
		);
		eClassValues[16] = new EClassValue(IdiomsPackage.Literals.POP_SEGMENT,
			createSerializationRules(
				17 /* PopSegment-0: 'pop' */
			), null
		);
		eClassValues[17] = new EClassValue(IdiomsPackage.Literals.POST_COMMENT_SEGMENT,
			createSerializationRules(
				18 /* PostCommentSegment-0: 'post-comment' */
			), null
		);
		eClassValues[18] = new EClassValue(IdiomsPackage.Literals.PRE_COMMENT_SEGMENT,
			createSerializationRules(
				19 /* PreCommentSegment-0: 'pre-comment' */
			), null
		);
		eClassValues[19] = new EClassValue(IdiomsPackage.Literals.PUSH_SEGMENT,
			createSerializationRules(
				20 /* PushSegment-0: 'push' */
			), null
		);
		eClassValues[20] = new EClassValue(IdiomsPackage.Literals.REFERRED_LOCATOR,
			createSerializationRules(
				21 /* ReferredLocator-0: (ReferredLocator::idiomsModel=ID '::')[V0:?] ReferredLocator::locatorDeclaration=ID */
			), null
		);
		eClassValues[21] = new EClassValue(IdiomsPackage.Literals.REFERRED_SEGMENT,
			createSerializationRules(
				22 /* ReferredSegment-0: (ReferredSegment::idiomsModel=ID '::')[V0:?] ReferredSegment::segmentDeclaration=ID */
			), null
		);
		eClassValues[22] = new EClassValue(IdiomsPackage.Literals.RETURNS_LOCATOR,
			createSerializationRules(
				23 /* ReturnsLocator-0: 'returns' (ReturnsLocator::ePackage=ID '::')[V0:?] ReturnsLocator::eClass=ID */
			), null
		);
		eClassValues[23] = new EClassValue(IdiomsPackage.Literals.RULE_LOCATOR,
			createSerializationRules(
				24 /* RuleLocator-0: 'rule' (RuleLocator::referredGrammar=ID '::')[V0:?] RuleLocator::referredRule=ID */
			), null
		);
		eClassValues[24] = new EClassValue(IdiomsPackage.Literals.SEGMENT_DECLARATION,
			createSerializationRules(
				25 /* SegmentDeclaration-0: 'segment' SegmentDeclaration::name=ID SegmentDeclaration::ownedSegment=Segment ';' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT,
					11) /* CustomSegment|HalfNewLineSegment|NewLineSegment|NoNewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */
			}
		);
		eClassValues[25] = new EClassValue(IdiomsPackage.Literals.SOFT_NEW_LINE_SEGMENT,
			createSerializationRules(
				26 /* SoftNewLineSegment-0: 'soft-new-line' */
			), null
		);
		eClassValues[26] = new EClassValue(IdiomsPackage.Literals.SOFT_SPACE_SEGMENT,
			createSerializationRules(
				27 /* SoftSpaceSegment-0: 'soft-space' */
			), null
		);
		eClassValues[27] = new EClassValue(IdiomsPackage.Literals.STRING_SEGMENT,
			createSerializationRules(
				28 /* StringSegment-0: 'string' StringSegment::string=STRING (StringSegment::printable?='printable')[V0:?] */
			), null
		);
		eClassValues[28] = new EClassValue(IdiomsPackage.Literals.SUB_IDIOM,
			createSerializationRules(
				31 /* SubIdiom-2: 'at' SubIdiom::ownedLocator=Locator ('do' (SubIdiom::ownedSegments+=Segment|ReferredSegment)[V1:*])[V0:?] ';' */,
				29 /* SubIdiom-0: 'at' 'each' SubIdiom::ownedLocator=Locator ('do' (SubIdiom::ownedSegments+=Segment|ReferredSegment)[V1:*])[V0:?] ';' */,
				30 /* SubIdiom-1: 'at' SubIdiom::all?='all' SubIdiom::ownedLocator=Locator ('do' (SubIdiom::ownedSegments+=Segment|ReferredSegment)[V1:*])[V0:?] ';' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR,
					6) /* AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator|RuleLocator */,
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS,
					12) /* CustomSegment|HalfNewLineSegment|NewLineSegment|NoNewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */
			}
		);
		eClassValues[29] = new EClassValue(IdiomsPackage.Literals.VALUE_SEGMENT,
			createSerializationRules(
				32 /* ValueSegment-0: 'value' */
			), null
		);
		eClassValues[30] = new EClassValue(IdiomsPackage.Literals.WRAP_ANCHOR_SEGMENT,
			createSerializationRules(
				33 /* WrapAnchorSegment-0: 'wrap-anchor' */
			), null
		);
		eClassValues[31] = new EClassValue(IdiomsPackage.Literals.WRAP_BEGIN_ALL_SEGMENT,
			createSerializationRules(
				34 /* WrapBeginAllSegment-0: 'wrap-begin-all' */
			), null
		);
		eClassValues[32] = new EClassValue(IdiomsPackage.Literals.WRAP_BEGIN_SOME_SEGMENT,
			createSerializationRules(
				35 /* WrapBeginSomeSegment-0: 'wrap-begin-some' */
			), null
		);
		eClassValues[33] = new EClassValue(IdiomsPackage.Literals.WRAP_END_SEGMENT,
			createSerializationRules(
				36 /* WrapEndSegment-0: 'wrap-end' */
			), null
		);
		eClassValues[34] = new EClassValue(IdiomsPackage.Literals.WRAP_HERE_SEGMENT,
			createSerializationRules(
				37 /* WrapHereSegment-0: 'wrap-here' */
			), null
		);
	}

	/**
	 * Initialize string combinations used by assigned String EAttributes.
	 */
	private void initEnumerationValues() {
		// 0: 'all'
		enumerationValues[0] = new EnumerationValueSingle("all");
		// 1: 'mixin'
		enumerationValues[1] = new EnumerationValueSingle("mixin");
		// 2: 'printable'
		enumerationValues[2] = new EnumerationValueSingle("printable");
	}

	/**
	 * Initialize the various serialization rules for each grammar rule.
	 */
	private void initGrammarRuleValues() {
		grammarRuleValues[0] = new TerminalRuleValue(0, "ANY_OTHER");
		grammarRuleValues[1] = createParserRuleValue(1, "AnyAssignmentLocator", -1,
			createSerializationRules(
				0	/* AnyAssignmentLocator-0: 'any-assignment' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {AnyAssignmentLocator} : [value] | [value] */,
			(0 << 16) | 6	/* "any-assignment" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[2] = createParserRuleValue(2, "AnyElementLocator", -1,
			createSerializationRules(
				1	/* AnyElementLocator-0: 'any-element' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {AnyElementLocator} : [value] | [value] */,
			(0 << 16) | 6	/* "any-element" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[3] = createParserRuleValue(3, "AssignmentLocator", -1,
			createSerializationRules(
				2	/* AssignmentLocator-0: 'assignment' ((AssignmentLocator::ePackage=ID '::')[V1:?] AssignmentLocator::eClass=ID '::')[V0:?] AssignmentLocator::eStructuralFeature=ID */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "assignment" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ePackage=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 4	/* "::" : [value] | [pre-comment, no-space, value, no-space, post-comment] */,
			(0 << 16) | 6	/* eClass=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 4	/* "::" : [value] | [pre-comment, no-space, value, no-space, post-comment] */,
			(0 << 16) | 6	/* eStructuralFeature=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[4] = createParserRuleValue(4, "CustomSegment", -1,
			createSerializationRules(
				3	/* CustomSegment-0: 'custom' CustomSegment::supportClassName=STRING */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "custom" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* supportClassName=STRING : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[5] = createParserRuleValue(5, "EPackageDeclaration", -1,
			createSerializationRules(
				4	/* EPackageDeclaration-0: 'import' EPackageDeclaration::ePackage=STRING ('as' EPackageDeclaration::as=ID)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "import" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* ePackage=STRING : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "as" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* as=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 5	/* ";"? : [value] | [pre-comment, no-space, value, soft-new-line, post-comment] */
		);
		grammarRuleValues[6] = createParserRuleValue(6, "FinalLocator", -1,
			createSerializationRules(
				5	/* FinalLocator-0: 'final' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {FinalLocator} : [value] | [value] */,
			(0 << 16) | 6	/* "final" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[7] = createParserRuleValue(7, "GrammarDeclaration", -1,
			createSerializationRules(
				6	/* GrammarDeclaration-0: 'grammar' GrammarDeclaration::grammar=STRING ('as' GrammarDeclaration::as=ID)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "grammar" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* grammar=STRING : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "as" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* as=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 5	/* ";"? : [value] | [pre-comment, no-space, value, soft-new-line, post-comment] */
		);
		grammarRuleValues[8] = createParserRuleValue(8, "HalfNewLineSegment", -1,
			createSerializationRules(
				7	/* HalfNewLineSegment-0: 'half-new-line' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {HalfNewLineSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "half-new-line" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[9] = new TerminalRuleValue(9, "ID");
		grammarRuleValues[10] = new TerminalRuleValue(10, "INT");
		grammarRuleValues[11] = createParserRuleValue(11, "Idiom", -1,
			createSerializationRules(
				8	/* Idiom-0: (Idiom::mixin?='mixin')[V0:?] 'idiom' Idiom::name=ID ('for' (Idiom::forEPackage=ID '::')[V2:?] Idiom::forEClass=ID)[V1:?] ('in' Idiom::inRuleRegex=STRING)[V3:?] '{' (Idiom::ownedSubIdioms+=SubIdiom)[V4:*] '}' */,
				9	/* Idiom-1: (Idiom::mixin?='mixin')[V0:?] 'idiom' Idiom::name=ID ('for' (Idiom::forEPackage=ID '::')[V2:?] Idiom::forEClass=ID)[V1:?] ('in' Idiom::inRuleRegex=STRING)[V3:?] Idiom::ownedSubIdioms+=SubIdiom */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* mixin?="mixin"? : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* "idiom" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* name=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "for" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* forEPackage=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 4	/* "::" : [value] | [pre-comment, no-space, value, no-space, post-comment] */,
			(0 << 16) | 6	/* forEClass=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "in" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* inRuleRegex=STRING : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 1	/* ownedSubIdioms+=SubIdiom : [value] | [value, soft-new-line] */,
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 8	/* "{" : [value] | [pre-comment, soft-space, value, push, soft-new-line, post-comment] */,
			(0 << 16) | 1	/* ownedSubIdioms+=SubIdiom* : [value] | [value, soft-new-line] */,
			(0 << 16) | 7	/* "}" : [value] | [pre-comment, pop, soft-space, value, soft-new-line, post-comment] */
		);
		grammarRuleValues[12] = createParserRuleValue(12, "IdiomsImport", -1,
			createSerializationRules(
				10	/* IdiomsImport-0: 'with' IdiomsImport::idiomsModel=STRING ('as' IdiomsImport::as=ID)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "with" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* idiomsModel=STRING : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "as" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* as=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 5	/* ";"? : [value] | [pre-comment, no-space, value, soft-new-line, post-comment] */
		);
		grammarRuleValues[13] = createParserRuleValue(13, "IdiomsModel", -1,
			createSerializationRules(
				11	/* IdiomsModel-0: 'model' IdiomsModel::names+=ID ('.' IdiomsModel::names+=ID)[V0:*] (IdiomsModel::ownedWiths+=IdiomsImport)[V1:*] (IdiomsModel::ownedImportDeclarations+=EPackageDeclaration)[V2:*] (IdiomsModel::ownedGrammarDeclarations+=GrammarDeclaration)[V3:*] (IdiomsModel::ownedLocatorDeclarations+=LocatorDeclaration)[V4:*] (IdiomsModel::ownedSegmentDeclarations+=SegmentDeclaration)[V5:*] (IdiomsModel::ownedIdioms+=Idiom)[V6:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "model" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* names+=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 4	/* "." : [value] | [pre-comment, no-space, value, no-space, post-comment] */,
			(0 << 16) | 6	/* names+=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(0 << 16) | 2	/* ownedWiths+=IdiomsImport : [value] | [soft-new-line, value, soft-new-line] */,
			(0 << 16) | 2	/* ownedImportDeclarations+=EPackageDeclaration : [value] | [soft-new-line, value, soft-new-line] */,
			(0 << 16) | 0	/* ownedGrammarDeclarations+=GrammarDeclaration : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(3 << 16) | 0	/* ownedLocatorDeclarations+=LocatorDeclaration : [new-line, soft-new-line, value, soft-new-line] | [value] */,
			(3 << 16) | 0	/* ownedSegmentDeclarations+=SegmentDeclaration : [new-line, soft-new-line, value, soft-new-line] | [value] */,
			(0 << 16) | 3	/* ownedIdioms+=Idiom : [value] | [new-line, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[14] = createParserRuleValue(14, "KeywordLocator", -1,
			createSerializationRules(
				12	/* KeywordLocator-0: KeywordLocator::string=STRING */
			),
			(0 << 16) | 6	/* string=STRING : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[15] = createParserRuleValue(15, "Locator", 6 /* AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator|RuleLocator */,
			createSerializationRules(
				0	/* AnyAssignmentLocator-0: 'any-assignment' */,
				1	/* AnyElementLocator-0: 'any-element' */,
				2	/* AssignmentLocator-0: 'assignment' ((AssignmentLocator::ePackage=ID '::')[V1:?] AssignmentLocator::eClass=ID '::')[V0:?] AssignmentLocator::eStructuralFeature=ID */,
				5	/* FinalLocator-0: 'final' */,
				12	/* KeywordLocator-0: KeywordLocator::string=STRING */,
				21	/* ReferredLocator-0: (ReferredLocator::idiomsModel=ID '::')[V0:?] ReferredLocator::locatorDeclaration=ID */,
				23	/* ReturnsLocator-0: 'returns' (ReturnsLocator::ePackage=ID '::')[V0:?] ReturnsLocator::eClass=ID */,
				24	/* RuleLocator-0: 'rule' (RuleLocator::referredGrammar=ID '::')[V0:?] RuleLocator::referredRule=ID */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* AnyAssignmentLocator : [value] | [value] */,
			(0 << 16) | 0	/* AnyElementLocator : [value] | [value] */,
			(0 << 16) | 0	/* AssignmentLocator : [value] | [value] */,
			(0 << 16) | 0	/* FinalLocator : [value] | [value] */,
			(0 << 16) | 0	/* KeywordLocator : [value] | [value] */,
			(0 << 16) | 0	/* ReferredLocator : [value] | [value] */,
			(0 << 16) | 0	/* ReturnsLocator : [value] | [value] */,
			(0 << 16) | 0	/* RuleLocator : [value] | [value] */
		);
		grammarRuleValues[16] = createParserRuleValue(16, "LocatorDeclaration", -1,
			createSerializationRules(
				13	/* LocatorDeclaration-0: 'locator' LocatorDeclaration::name=ID LocatorDeclaration::ownedLocator=Locator ';' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "locator" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* name=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* ownedLocator=Locator : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [pre-comment, no-space, value, soft-new-line, post-comment] */
		);
		grammarRuleValues[17] = new TerminalRuleValue(17, "ML_COMMENT");
		grammarRuleValues[18] = createParserRuleValue(18, "NewLineSegment", -1,
			createSerializationRules(
				14	/* NewLineSegment-0: 'new-line' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {NewLineSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "new-line" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[19] = createParserRuleValue(19, "NoNewLineSegment", -1,
			createSerializationRules(
				15	/* NoNewLineSegment-0: 'no-new-line' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {NoNewLineSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "no-new-line" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[20] = createParserRuleValue(20, "NoSpaceSegment", -1,
			createSerializationRules(
				16	/* NoSpaceSegment-0: 'no-space' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {NoSpaceSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "no-space" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[21] = createParserRuleValue(21, "PopSegment", -1,
			createSerializationRules(
				17	/* PopSegment-0: 'pop' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PopSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "pop" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[22] = createParserRuleValue(22, "PostCommentSegment", -1,
			createSerializationRules(
				18	/* PostCommentSegment-0: 'post-comment' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PostCommentSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "post-comment" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[23] = createParserRuleValue(23, "PreCommentSegment", -1,
			createSerializationRules(
				19	/* PreCommentSegment-0: 'pre-comment' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PreCommentSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "pre-comment" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[24] = createParserRuleValue(24, "PushSegment", -1,
			createSerializationRules(
				20	/* PushSegment-0: 'push' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PushSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "push" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[25] = createParserRuleValue(25, "ReferredLocator", -1,
			createSerializationRules(
				21	/* ReferredLocator-0: (ReferredLocator::idiomsModel=ID '::')[V0:?] ReferredLocator::locatorDeclaration=ID */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* idiomsModel=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 4	/* "::" : [value] | [pre-comment, no-space, value, no-space, post-comment] */,
			(0 << 16) | 6	/* locatorDeclaration=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[26] = createParserRuleValue(26, "ReferredSegment", -1,
			createSerializationRules(
				22	/* ReferredSegment-0: (ReferredSegment::idiomsModel=ID '::')[V0:?] ReferredSegment::segmentDeclaration=ID */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* idiomsModel=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 4	/* "::" : [value] | [pre-comment, no-space, value, no-space, post-comment] */,
			(0 << 16) | 6	/* segmentDeclaration=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[27] = createParserRuleValue(27, "ReturnsLocator", -1,
			createSerializationRules(
				23	/* ReturnsLocator-0: 'returns' (ReturnsLocator::ePackage=ID '::')[V0:?] ReturnsLocator::eClass=ID */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "returns" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ePackage=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 4	/* "::" : [value] | [pre-comment, no-space, value, no-space, post-comment] */,
			(0 << 16) | 6	/* eClass=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[28] = createParserRuleValue(28, "RuleLocator", -1,
			createSerializationRules(
				24	/* RuleLocator-0: 'rule' (RuleLocator::referredGrammar=ID '::')[V0:?] RuleLocator::referredRule=ID */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "rule" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* referredGrammar=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 4	/* "::" : [value] | [pre-comment, no-space, value, no-space, post-comment] */,
			(0 << 16) | 6	/* referredRule=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[29] = new TerminalRuleValue(29, "SL_COMMENT");
		grammarRuleValues[30] = new TerminalRuleValue(30, "STRING");
		grammarRuleValues[31] = createParserRuleValue(31, "Segment", 11 /* CustomSegment|HalfNewLineSegment|NewLineSegment|NoNewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */,
			createSerializationRules(
				3	/* CustomSegment-0: 'custom' CustomSegment::supportClassName=STRING */,
				7	/* HalfNewLineSegment-0: 'half-new-line' */,
				14	/* NewLineSegment-0: 'new-line' */,
				15	/* NoNewLineSegment-0: 'no-new-line' */,
				16	/* NoSpaceSegment-0: 'no-space' */,
				17	/* PopSegment-0: 'pop' */,
				18	/* PostCommentSegment-0: 'post-comment' */,
				19	/* PreCommentSegment-0: 'pre-comment' */,
				20	/* PushSegment-0: 'push' */,
				26	/* SoftNewLineSegment-0: 'soft-new-line' */,
				27	/* SoftSpaceSegment-0: 'soft-space' */,
				28	/* StringSegment-0: 'string' StringSegment::string=STRING (StringSegment::printable?='printable')[V0:?] */,
				32	/* ValueSegment-0: 'value' */,
				33	/* WrapAnchorSegment-0: 'wrap-anchor' */,
				34	/* WrapBeginAllSegment-0: 'wrap-begin-all' */,
				35	/* WrapBeginSomeSegment-0: 'wrap-begin-some' */,
				36	/* WrapEndSegment-0: 'wrap-end' */,
				37	/* WrapHereSegment-0: 'wrap-here' */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* CustomSegment : [value] | [value] */,
			(0 << 16) | 0	/* HalfNewLineSegment : [value] | [value] */,
			(0 << 16) | 0	/* NewLineSegment : [value] | [value] */,
			(0 << 16) | 0	/* NoNewLineSegment : [value] | [value] */,
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
		grammarRuleValues[32] = createParserRuleValue(32, "SegmentDeclaration", -1,
			createSerializationRules(
				25	/* SegmentDeclaration-0: 'segment' SegmentDeclaration::name=ID SegmentDeclaration::ownedSegment=Segment ';' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "segment" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* name=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* ownedSegment=Segment : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [pre-comment, no-space, value, soft-new-line, post-comment] */
		);
		grammarRuleValues[33] = createParserRuleValue(33, "SoftNewLineSegment", -1,
			createSerializationRules(
				26	/* SoftNewLineSegment-0: 'soft-new-line' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {SoftNewLineSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "soft-new-line" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[34] = createParserRuleValue(34, "SoftSpaceSegment", -1,
			createSerializationRules(
				27	/* SoftSpaceSegment-0: 'soft-space' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {SoftSpaceSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "soft-space" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[35] = createParserRuleValue(35, "StringSegment", -1,
			createSerializationRules(
				28	/* StringSegment-0: 'string' StringSegment::string=STRING (StringSegment::printable?='printable')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "string" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* string=STRING : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* printable?="printable"? : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[36] = createParserRuleValue(36, "SubIdiom", -1,
			createSerializationRules(
				29	/* SubIdiom-0: 'at' 'each' SubIdiom::ownedLocator=Locator ('do' (SubIdiom::ownedSegments+=Segment|ReferredSegment)[V1:*])[V0:?] ';' */,
				30	/* SubIdiom-1: 'at' SubIdiom::all?='all' SubIdiom::ownedLocator=Locator ('do' (SubIdiom::ownedSegments+=Segment|ReferredSegment)[V1:*])[V0:?] ';' */,
				31	/* SubIdiom-2: 'at' SubIdiom::ownedLocator=Locator ('do' (SubIdiom::ownedSegments+=Segment|ReferredSegment)[V1:*])[V0:?] ';' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "at" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 6	/* all?="all" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* "each" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* ownedLocator=Locator : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "do" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* ownedSegments+=(Segment|ReferredSegment)* : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [pre-comment, no-space, value, soft-new-line, post-comment] */
		);
		grammarRuleValues[37] = createParserRuleValue(37, "ValueSegment", -1,
			createSerializationRules(
				32	/* ValueSegment-0: 'value' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {ValueSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "value" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[38] = new TerminalRuleValue(38, "WS");
		grammarRuleValues[39] = createParserRuleValue(39, "WrapAnchorSegment", -1,
			createSerializationRules(
				33	/* WrapAnchorSegment-0: 'wrap-anchor' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapAnchorSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-anchor" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[40] = createParserRuleValue(40, "WrapBeginAllSegment", -1,
			createSerializationRules(
				34	/* WrapBeginAllSegment-0: 'wrap-begin-all' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapBeginAllSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-begin-all" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[41] = createParserRuleValue(41, "WrapBeginSomeSegment", -1,
			createSerializationRules(
				35	/* WrapBeginSomeSegment-0: 'wrap-begin-some' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapBeginSomeSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-begin-some" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[42] = createParserRuleValue(42, "WrapEndSegment", -1,
			createSerializationRules(
				36	/* WrapEndSegment-0: 'wrap-end' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapEndSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-end" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[43] = createParserRuleValue(43, "WrapHereSegment", -1,
			createSerializationRules(
				37	/* WrapHereSegment-0: 'wrap-here' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapHereSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-here" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
	}

	/**
	 * Initialize bit vectors of useful grammar rule combinations.
	 */
	private void initGrammarRuleVectors() {
		// 0: EPackageDeclaration
		grammarRuleVectors[0] = new GrammarRuleVector(0x20L);
		// 1: GrammarDeclaration
		grammarRuleVectors[1] = new GrammarRuleVector(0x80L);
		// 2: Idiom
		grammarRuleVectors[2] = new GrammarRuleVector(0x800L);
		// 3: IdiomsImport
		grammarRuleVectors[3] = new GrammarRuleVector(0x1000L);
		// 4: Locator
		grammarRuleVectors[4] = new GrammarRuleVector(0x8000L);
		// 5: LocatorDeclaration
		grammarRuleVectors[5] = new GrammarRuleVector(0x10000L);
		// 6: AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator|RuleLocator
		grammarRuleVectors[6] = new GrammarRuleVector(0x1a00c04eL);
		// 7: Segment
		grammarRuleVectors[7] = new GrammarRuleVector(0x80000000L);
		// 8: ReferredSegment|Segment
		grammarRuleVectors[8] = new GrammarRuleVector(0x84000000L);
		// 9: SegmentDeclaration
		grammarRuleVectors[9] = new GrammarRuleVector(0x100000000L);
		// 10: SubIdiom
		grammarRuleVectors[10] = new GrammarRuleVector(0x1000000000L);
		// 11: CustomSegment|HalfNewLineSegment|NewLineSegment|NoNewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment
		grammarRuleVectors[11] = new GrammarRuleVector(0xfae81fc0110L);
		// 12: CustomSegment|HalfNewLineSegment|NewLineSegment|NoNewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment
		grammarRuleVectors[12] = new GrammarRuleVector(0xfae85fc0110L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// 0: assert (|AssignmentLocator::eStructuralFeature| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(44);
		// 1: assert (|CustomSegment::supportClassName| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(45);
		// 2: assert (|EPackageDeclaration::ePackage| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(46);
		// 3: assert (|GrammarDeclaration::grammar| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(47);
		// 4: assert (|Idiom::name| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(48);
		// 5: assert (|Idiom::ownedSubIdioms| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(49);
		// 6: assert (|IdiomsImport::idiomsModel| - 1) == 0
		serializationMatchSteps[6] = createMatchStep_Assert(50);
		// 7: assert (|KeywordLocator::string| - 1) == 0
		serializationMatchSteps[7] = createMatchStep_Assert(52);
		// 8: assert (|LocatorDeclaration::name| - 1) == 0
		serializationMatchSteps[8] = createMatchStep_Assert(53);
		// 9: assert (|LocatorDeclaration::ownedLocator| - 1) == 0
		serializationMatchSteps[9] = createMatchStep_Assert(54);
		// 10: assert (|ReferredLocator::locatorDeclaration| - 1) == 0
		serializationMatchSteps[10] = createMatchStep_Assert(55);
		// 11: assert (|ReferredSegment::segmentDeclaration| - 1) == 0
		serializationMatchSteps[11] = createMatchStep_Assert(56);
		// 12: assert (|ReturnsLocator::eClass| - 1) == 0
		serializationMatchSteps[12] = createMatchStep_Assert(57);
		// 13: assert (|RuleLocator::referredRule| - 1) == 0
		serializationMatchSteps[13] = createMatchStep_Assert(58);
		// 14: assert (|SegmentDeclaration::name| - 1) == 0
		serializationMatchSteps[14] = createMatchStep_Assert(59);
		// 15: assert (|SegmentDeclaration::ownedSegment| - 1) == 0
		serializationMatchSteps[15] = createMatchStep_Assert(60);
		// 16: assert (|StringSegment::string| - 1) == 0
		serializationMatchSteps[16] = createMatchStep_Assert(61);
		// 17: assert (|SubIdiom::all.'all'| - 1) == 0
		serializationMatchSteps[17] = createMatchStep_Assert(62);
		// 18: assert (|SubIdiom::ownedLocator| - 1) == 0
		serializationMatchSteps[18] = createMatchStep_Assert(63);
		// 19: assert |SubIdiom::all| == 0
		serializationMatchSteps[19] = createMatchStep_Assert(41);
		// 20: assign V0 = (|IdiomsModel::names| - 1)
		serializationMatchSteps[20] = createMatchStep_Assign(0, 51);
		// 21: assign V0 = (|SubIdiom::ownedSegments| > 0)
		serializationMatchSteps[21] = createMatchStep_Assign(0, 64);
		// 22: assign V0 = |AssignmentLocator::eClass|
		serializationMatchSteps[22] = createMatchStep_Assign(0, 2);
		// 23: assign V0 = |EPackageDeclaration::as|
		serializationMatchSteps[23] = createMatchStep_Assign(0, 6);
		// 24: assign V0 = |GrammarDeclaration::as|
		serializationMatchSteps[24] = createMatchStep_Assign(0, 8);
		// 25: assign V0 = |Idiom::mixin.'mixin'|
		serializationMatchSteps[25] = createMatchStep_Assign(0, 13);
		// 26: assign V0 = |IdiomsImport::as|
		serializationMatchSteps[26] = createMatchStep_Assign(0, 16);
		// 27: assign V0 = |ReferredLocator::idiomsModel|
		serializationMatchSteps[27] = createMatchStep_Assign(0, 28);
		// 28: assign V0 = |ReferredSegment::idiomsModel|
		serializationMatchSteps[28] = createMatchStep_Assign(0, 30);
		// 29: assign V0 = |ReturnsLocator::ePackage|
		serializationMatchSteps[29] = createMatchStep_Assign(0, 33);
		// 30: assign V0 = |RuleLocator::referredGrammar|
		serializationMatchSteps[30] = createMatchStep_Assign(0, 34);
		// 31: assign V0 = |StringSegment::printable.'printable'|
		serializationMatchSteps[31] = createMatchStep_Assign(0, 38);
		// 32: assign V1 = |AssignmentLocator::ePackage|
		serializationMatchSteps[32] = createMatchStep_Assign(1, 3);
		// 33: assign V1 = |Idiom::forEClass|
		serializationMatchSteps[33] = createMatchStep_Assign(1, 10);
		// 34: assign V1 = |IdiomsModel::ownedWiths|
		serializationMatchSteps[34] = createMatchStep_Assign(1, 24);
		// 35: assign V1 = |SubIdiom::ownedSegments|
		serializationMatchSteps[35] = createMatchStep_Assign(1, 43);
		// 36: assign V2 = |Idiom::forEPackage|
		serializationMatchSteps[36] = createMatchStep_Assign(2, 11);
		// 37: assign V2 = |IdiomsModel::ownedImportDeclarations|
		serializationMatchSteps[37] = createMatchStep_Assign(2, 21);
		// 38: assign V3 = |Idiom::inRuleRegex|
		serializationMatchSteps[38] = createMatchStep_Assign(3, 12);
		// 39: assign V3 = |IdiomsModel::ownedGrammarDeclarations|
		serializationMatchSteps[39] = createMatchStep_Assign(3, 19);
		// 40: assign V4 = |Idiom::ownedSubIdioms|
		serializationMatchSteps[40] = createMatchStep_Assign(4, 15);
		// 41: assign V4 = |IdiomsModel::ownedLocatorDeclarations|
		serializationMatchSteps[41] = createMatchStep_Assign(4, 22);
		// 42: assign V5 = |IdiomsModel::ownedSegmentDeclarations|
		serializationMatchSteps[42] = createMatchStep_Assign(5, 23);
		// 43: assign V6 = |IdiomsModel::ownedIdioms|
		serializationMatchSteps[43] = createMatchStep_Assign(6, 20);
		// 44: check-rule idioms::Idiom.ownedSubIdioms : 36
		serializationMatchSteps[44] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS, 10/*SubIdiom*/);
		// 45: check-rule idioms::IdiomsModel.ownedGrammarDeclarations : 7
		serializationMatchSteps[45] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_GRAMMAR_DECLARATIONS, 1/*GrammarDeclaration*/);
		// 46: check-rule idioms::IdiomsModel.ownedIdioms : 11
		serializationMatchSteps[46] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS, 2/*Idiom*/);
		// 47: check-rule idioms::IdiomsModel.ownedImportDeclarations : 5
		serializationMatchSteps[47] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORT_DECLARATIONS, 0/*EPackageDeclaration*/);
		// 48: check-rule idioms::IdiomsModel.ownedLocatorDeclarations : 16
		serializationMatchSteps[48] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS, 5/*LocatorDeclaration*/);
		// 49: check-rule idioms::IdiomsModel.ownedSegmentDeclarations : 32
		serializationMatchSteps[49] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS, 9/*SegmentDeclaration*/);
		// 50: check-rule idioms::IdiomsModel.ownedWiths : 12
		serializationMatchSteps[50] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS, 3/*IdiomsImport*/);
		// 51: check-rule idioms::LocatorDeclaration.ownedLocator : 1|2|3|6|14|15|25|27|28
		serializationMatchSteps[51] = createMatchStep_RuleCheck(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR, 6/*AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator|RuleLocator*/);
		// 52: check-rule idioms::SegmentDeclaration.ownedSegment : 4|8|18|19|20|21|22|23|24|31|33|34|35|37|39|40|41|42|43
		serializationMatchSteps[52] = createMatchStep_RuleCheck(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT, 11/*CustomSegment|HalfNewLineSegment|NewLineSegment|NoNewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment*/);
		// 53: check-rule idioms::SubIdiom.ownedLocator : 1|2|3|6|14|15|25|27|28
		serializationMatchSteps[53] = createMatchStep_RuleCheck(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR, 6/*AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator|RuleLocator*/);
		// 54: check-rule idioms::SubIdiom.ownedSegments : 4|8|18|19|20|21|22|23|24|26|31|33|34|35|37|39|40|41|42|43
		serializationMatchSteps[54] = createMatchStep_RuleCheck(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS, 12/*CustomSegment|HalfNewLineSegment|NewLineSegment|NoNewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment*/);
	}

	/**
	 * Initialize expression terms used during the matching process.
	 */
	private void initMatchTerms() {
		// 0: 0
		serializationMatchTerms[0] = createSerializationMatchTermInteger(0);
		// 1: 1
		serializationMatchTerms[1] = createSerializationMatchTermInteger(1);
		// 2: |AssignmentLocator::eClass|
		serializationMatchTerms[2] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ECLASS);
		// 3: |AssignmentLocator::ePackage|
		serializationMatchTerms[3] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__EPACKAGE);
		// 4: |AssignmentLocator::eStructuralFeature|
		serializationMatchTerms[4] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE);
		// 5: |CustomSegment::supportClassName|
		serializationMatchTerms[5] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.CUSTOM_SEGMENT__SUPPORT_CLASS_NAME);
		// 6: |EPackageDeclaration::as|
		serializationMatchTerms[6] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.EPACKAGE_DECLARATION__AS);
		// 7: |EPackageDeclaration::ePackage|
		serializationMatchTerms[7] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.EPACKAGE_DECLARATION__EPACKAGE);
		// 8: |GrammarDeclaration::as|
		serializationMatchTerms[8] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.GRAMMAR_DECLARATION__AS);
		// 9: |GrammarDeclaration::grammar|
		serializationMatchTerms[9] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.GRAMMAR_DECLARATION__GRAMMAR);
		// 10: |Idiom::forEClass|
		serializationMatchTerms[10] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__FOR_ECLASS);
		// 11: |Idiom::forEPackage|
		serializationMatchTerms[11] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE);
		// 12: |Idiom::inRuleRegex|
		serializationMatchTerms[12] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__IN_RULE_REGEX);
		// 13: |Idiom::mixin.'mixin'|
		serializationMatchTerms[13] = createSerializationMatchTermEAttributeSize(IdiomsPackage.Literals.IDIOM__MIXIN, 1 /* 'mixin' */);
		// 14: |Idiom::name|
		serializationMatchTerms[14] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__NAME);
		// 15: |Idiom::ownedSubIdioms|
		serializationMatchTerms[15] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS);
		// 16: |IdiomsImport::as|
		serializationMatchTerms[16] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_IMPORT__AS);
		// 17: |IdiomsImport::idiomsModel|
		serializationMatchTerms[17] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL);
		// 18: |IdiomsModel::names|
		serializationMatchTerms[18] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__NAMES);
		// 19: |IdiomsModel::ownedGrammarDeclarations|
		serializationMatchTerms[19] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_GRAMMAR_DECLARATIONS);
		// 20: |IdiomsModel::ownedIdioms|
		serializationMatchTerms[20] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS);
		// 21: |IdiomsModel::ownedImportDeclarations|
		serializationMatchTerms[21] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORT_DECLARATIONS);
		// 22: |IdiomsModel::ownedLocatorDeclarations|
		serializationMatchTerms[22] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS);
		// 23: |IdiomsModel::ownedSegmentDeclarations|
		serializationMatchTerms[23] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS);
		// 24: |IdiomsModel::ownedWiths|
		serializationMatchTerms[24] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS);
		// 25: |KeywordLocator::string|
		serializationMatchTerms[25] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.KEYWORD_LOCATOR__STRING);
		// 26: |LocatorDeclaration::name|
		serializationMatchTerms[26] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.LOCATOR_DECLARATION__NAME);
		// 27: |LocatorDeclaration::ownedLocator|
		serializationMatchTerms[27] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR);
		// 28: |ReferredLocator::idiomsModel|
		serializationMatchTerms[28] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.REFERRED_LOCATOR__IDIOMS_MODEL);
		// 29: |ReferredLocator::locatorDeclaration|
		serializationMatchTerms[29] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION);
		// 30: |ReferredSegment::idiomsModel|
		serializationMatchTerms[30] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.REFERRED_SEGMENT__IDIOMS_MODEL);
		// 31: |ReferredSegment::segmentDeclaration|
		serializationMatchTerms[31] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION);
		// 32: |ReturnsLocator::eClass|
		serializationMatchTerms[32] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS);
		// 33: |ReturnsLocator::ePackage|
		serializationMatchTerms[33] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.RETURNS_LOCATOR__EPACKAGE);
		// 34: |RuleLocator::referredGrammar|
		serializationMatchTerms[34] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.RULE_LOCATOR__REFERRED_GRAMMAR);
		// 35: |RuleLocator::referredRule|
		serializationMatchTerms[35] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.RULE_LOCATOR__REFERRED_RULE);
		// 36: |SegmentDeclaration::name|
		serializationMatchTerms[36] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SEGMENT_DECLARATION__NAME);
		// 37: |SegmentDeclaration::ownedSegment|
		serializationMatchTerms[37] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT);
		// 38: |StringSegment::printable.'printable'|
		serializationMatchTerms[38] = createSerializationMatchTermEAttributeSize(IdiomsPackage.Literals.STRING_SEGMENT__PRINTABLE, 2 /* 'printable' */);
		// 39: |StringSegment::string|
		serializationMatchTerms[39] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.STRING_SEGMENT__STRING);
		// 40: |SubIdiom::all.'all'|
		serializationMatchTerms[40] = createSerializationMatchTermEAttributeSize(IdiomsPackage.Literals.SUB_IDIOM__ALL, 0 /* 'all' */);
		// 41: |SubIdiom::all|
		serializationMatchTerms[41] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SUB_IDIOM__ALL);
		// 42: |SubIdiom::ownedLocator|
		serializationMatchTerms[42] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR);
		// 43: |SubIdiom::ownedSegments|
		serializationMatchTerms[43] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS);
		// 44: (|AssignmentLocator::eStructuralFeature| - 1)
		serializationMatchTerms[44] = createSerializationMatchTermSubtract(4, 1);
		// 45: (|CustomSegment::supportClassName| - 1)
		serializationMatchTerms[45] = createSerializationMatchTermSubtract(5, 1);
		// 46: (|EPackageDeclaration::ePackage| - 1)
		serializationMatchTerms[46] = createSerializationMatchTermSubtract(7, 1);
		// 47: (|GrammarDeclaration::grammar| - 1)
		serializationMatchTerms[47] = createSerializationMatchTermSubtract(9, 1);
		// 48: (|Idiom::name| - 1)
		serializationMatchTerms[48] = createSerializationMatchTermSubtract(14, 1);
		// 49: (|Idiom::ownedSubIdioms| - 1)
		serializationMatchTerms[49] = createSerializationMatchTermSubtract(15, 1);
		// 50: (|IdiomsImport::idiomsModel| - 1)
		serializationMatchTerms[50] = createSerializationMatchTermSubtract(17, 1);
		// 51: (|IdiomsModel::names| - 1)
		serializationMatchTerms[51] = createSerializationMatchTermSubtract(18, 1);
		// 52: (|KeywordLocator::string| - 1)
		serializationMatchTerms[52] = createSerializationMatchTermSubtract(25, 1);
		// 53: (|LocatorDeclaration::name| - 1)
		serializationMatchTerms[53] = createSerializationMatchTermSubtract(26, 1);
		// 54: (|LocatorDeclaration::ownedLocator| - 1)
		serializationMatchTerms[54] = createSerializationMatchTermSubtract(27, 1);
		// 55: (|ReferredLocator::locatorDeclaration| - 1)
		serializationMatchTerms[55] = createSerializationMatchTermSubtract(29, 1);
		// 56: (|ReferredSegment::segmentDeclaration| - 1)
		serializationMatchTerms[56] = createSerializationMatchTermSubtract(31, 1);
		// 57: (|ReturnsLocator::eClass| - 1)
		serializationMatchTerms[57] = createSerializationMatchTermSubtract(32, 1);
		// 58: (|RuleLocator::referredRule| - 1)
		serializationMatchTerms[58] = createSerializationMatchTermSubtract(35, 1);
		// 59: (|SegmentDeclaration::name| - 1)
		serializationMatchTerms[59] = createSerializationMatchTermSubtract(36, 1);
		// 60: (|SegmentDeclaration::ownedSegment| - 1)
		serializationMatchTerms[60] = createSerializationMatchTermSubtract(37, 1);
		// 61: (|StringSegment::string| - 1)
		serializationMatchTerms[61] = createSerializationMatchTermSubtract(39, 1);
		// 62: (|SubIdiom::all.'all'| - 1)
		serializationMatchTerms[62] = createSerializationMatchTermSubtract(40, 1);
		// 63: (|SubIdiom::ownedLocator| - 1)
		serializationMatchTerms[63] = createSerializationMatchTermSubtract(42, 1);
		// 64: (|SubIdiom::ownedSegments| > 0)
		serializationMatchTerms[64] = createSerializationMatchTermGreaterThan(43, 0);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules() {
		// Idioms::AnyAssignmentLocator-0(idioms::AnyAssignmentLocator): "any-assignment"
		serializationRules[0] = createSerializationRule("AnyAssignmentLocator-0", 1,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				44		/* 'any-assignment' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::AnyElementLocator-0(idioms::AnyElementLocator): "any-element"
		serializationRules[1] = createSerializationRule("AnyElementLocator-0", 2,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				45		/* 'any-element' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::AssignmentLocator-0(idioms::AssignmentLocator): { "assignment" { { ePackage=ID "::" }[?] eClass=ID "::" }[?] eStructuralFeature=ID }
		serializationRules[2] = createSerializationRule("AssignmentLocator-0", 3,
			createSerializationMatchSteps(
				0		/* assert (|AssignmentLocator::eStructuralFeature| - 1) == 0 */,
				22		/* assign V0 = |AssignmentLocator::eClass| */,
				32		/* assign V1 = |AssignmentLocator::ePackage| */
			),
			createSerializationSteps(
				47		/* 'assignment' || pre-comment soft-space value soft-space post-comment */,
				87		/* V00*5-steps || value */,
				89		/* V01*2-steps || value */,
				6		/* AssignmentLocator::ePackage=ID || pre-comment soft-space value soft-space post-comment */,
				42		/* '::' || pre-comment no-space value no-space post-comment */,
				4		/* AssignmentLocator::eClass=ID || pre-comment soft-space value soft-space post-comment */,
				42		/* '::' || pre-comment no-space value no-space post-comment */,
				9		/* AssignmentLocator::eStructuralFeature=ID || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ECLASS, -1
				),
				createSerializationReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__EPACKAGE, -1
				),
				createSerializationReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE, -1
				)
			});
		// Idioms::CustomSegment-0(idioms::CustomSegment): { "custom" supportClassName=STRING }
		serializationRules[3] = createSerializationRule("CustomSegment-0", 4,
			createSerializationMatchSteps(
				1		/* assert (|CustomSegment::supportClassName| - 1) == 0 */
			),
			createSerializationSteps(
				49		/* 'custom' || pre-comment soft-space value soft-space post-comment */,
				40		/* CustomSegment::supportClassName=STRING || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.CUSTOM_SEGMENT__SUPPORT_CLASS_NAME, true, GrammarCardinality.ONE)
			});
		// Idioms::EPackageDeclaration-0(idioms::EPackageDeclaration): { "import" ePackage=STRING { "as" as=ID }[?] }
		serializationRules[4] = createSerializationRule("EPackageDeclaration-0", 5,
			createSerializationMatchSteps(
				23		/* assign V0 = |EPackageDeclaration::as| */,
				2		/* assert (|EPackageDeclaration::ePackage| - 1) == 0 */
			),
			createSerializationSteps(
				57		/* 'import' || pre-comment soft-space value soft-space post-comment */,
				7		/* EPackageDeclaration::ePackage=STRING || pre-comment soft-space value soft-space post-comment */,
				84		/* V00*2-steps || value */,
				46		/* 'as' || pre-comment soft-space value soft-space post-comment */,
				1		/* EPackageDeclaration::as=ID || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.EPACKAGE_DECLARATION__AS, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(IdiomsPackage.Literals.EPACKAGE_DECLARATION__EPACKAGE, -1
				)
			});
		// Idioms::FinalLocator-0(idioms::FinalLocator): "final"
		serializationRules[5] = createSerializationRule("FinalLocator-0", 6,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				52		/* 'final' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::GrammarDeclaration-0(idioms::GrammarDeclaration): { "grammar" grammar=STRING { "as" as=ID }[?] }
		serializationRules[6] = createSerializationRule("GrammarDeclaration-0", 7,
			createSerializationMatchSteps(
				24		/* assign V0 = |GrammarDeclaration::as| */,
				3		/* assert (|GrammarDeclaration::grammar| - 1) == 0 */
			),
			createSerializationSteps(
				54		/* 'grammar' || pre-comment soft-space value soft-space post-comment */,
				12		/* GrammarDeclaration::grammar=STRING || pre-comment soft-space value soft-space post-comment */,
				84		/* V00*2-steps || value */,
				46		/* 'as' || pre-comment soft-space value soft-space post-comment */,
				2		/* GrammarDeclaration::as=ID || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.GRAMMAR_DECLARATION__AS, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(IdiomsPackage.Literals.GRAMMAR_DECLARATION__GRAMMAR, -1
				)
			});
		// Idioms::HalfNewLineSegment-0(idioms::HalfNewLineSegment): "half-new-line"
		serializationRules[7] = createSerializationRule("HalfNewLineSegment-0", 8,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				55		/* 'half-new-line' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::Idiom-0(idioms::Idiom): { mixin?="mixin"[?] "idiom" name=ID { "for" { forEPackage=ID "::" }[?] forEClass=ID }[?] { "in" inRuleRegex=STRING }[?] { "{" ownedSubIdioms+=SubIdiom[*] "}" } }
		serializationRules[8] = createSerializationRule("Idiom-0", 11,
			createSerializationMatchSteps(
				44		/* check-rule idioms::Idiom.ownedSubIdioms : SubIdiom */,
				40		/* assign V4 = |Idiom::ownedSubIdioms| */,
				38		/* assign V3 = |Idiom::inRuleRegex| */,
				33		/* assign V1 = |Idiom::forEClass| */,
				36		/* assign V2 = |Idiom::forEPackage| */,
				4		/* assert (|Idiom::name| - 1) == 0 */,
				25		/* assign V0 = |Idiom::mixin.'mixin'| */
			),
			createSerializationSteps(
				83		/* V00*1-steps || value */,
				18		/* Idiom::mixin?='mixin' || pre-comment soft-space value soft-space post-comment */,
				56		/* 'idiom' || pre-comment soft-space value soft-space post-comment */,
				19		/* Idiom::name=ID || pre-comment soft-space value soft-space post-comment */,
				90		/* V01*5-steps || value */,
				53		/* 'for' || pre-comment soft-space value soft-space post-comment */,
				92		/* V02*2-steps || value */,
				11		/* Idiom::forEPackage=ID || pre-comment soft-space value soft-space post-comment */,
				42		/* '::' || pre-comment no-space value no-space post-comment */,
				10		/* Idiom::forEClass=ID || pre-comment soft-space value soft-space post-comment */,
				94		/* V03*2-steps || value */,
				58		/* 'in' || pre-comment soft-space value soft-space post-comment */,
				16		/* Idiom::inRuleRegex=STRING || pre-comment soft-space value soft-space post-comment */,
				81		/* '{' || pre-comment soft-space value push soft-new-line post-comment */,
				95		/* V04*1-steps || value */,
				32		/* Idiom::ownedSubIdioms+=SubIdiom || value soft-new-line */,
				82		/* '}' || pre-comment pop soft-space value soft-new-line post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.IDIOM__IN_RULE_REGEX, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(IdiomsPackage.Literals.IDIOM__MIXIN, false,
					(1/*'mixin'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(IdiomsPackage.Literals.IDIOM__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(IdiomsPackage.Literals.IDIOM__FOR_ECLASS, -1
				),
				createSerializationReference(IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE, -1
				),
				createSerializationReference(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS, 10/* SubIdiom */,
					(36/*SubIdiom*/ << 4) | 2 /*[*]*/
				)
			});
		// Idioms::Idiom-1(idioms::Idiom): { mixin?="mixin"[?] "idiom" name=ID { "for" { forEPackage=ID "::" }[?] forEClass=ID }[?] { "in" inRuleRegex=STRING }[?] ownedSubIdioms+=SubIdiom }
		serializationRules[9] = createSerializationRule("Idiom-1", 11,
			createSerializationMatchSteps(
				44		/* check-rule idioms::Idiom.ownedSubIdioms : SubIdiom */,
				5		/* assert (|Idiom::ownedSubIdioms| - 1) == 0 */,
				38		/* assign V3 = |Idiom::inRuleRegex| */,
				33		/* assign V1 = |Idiom::forEClass| */,
				36		/* assign V2 = |Idiom::forEPackage| */,
				4		/* assert (|Idiom::name| - 1) == 0 */,
				25		/* assign V0 = |Idiom::mixin.'mixin'| */
			),
			createSerializationSteps(
				83		/* V00*1-steps || value */,
				18		/* Idiom::mixin?='mixin' || pre-comment soft-space value soft-space post-comment */,
				56		/* 'idiom' || pre-comment soft-space value soft-space post-comment */,
				19		/* Idiom::name=ID || pre-comment soft-space value soft-space post-comment */,
				90		/* V01*5-steps || value */,
				53		/* 'for' || pre-comment soft-space value soft-space post-comment */,
				92		/* V02*2-steps || value */,
				11		/* Idiom::forEPackage=ID || pre-comment soft-space value soft-space post-comment */,
				42		/* '::' || pre-comment no-space value no-space post-comment */,
				10		/* Idiom::forEClass=ID || pre-comment soft-space value soft-space post-comment */,
				94		/* V03*2-steps || value */,
				58		/* 'in' || pre-comment soft-space value soft-space post-comment */,
				16		/* Idiom::inRuleRegex=STRING || pre-comment soft-space value soft-space post-comment */,
				32		/* Idiom::ownedSubIdioms+=SubIdiom || value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.IDIOM__IN_RULE_REGEX, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationEnumeratedAttribute(IdiomsPackage.Literals.IDIOM__MIXIN, false,
					(1/*'mixin'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(IdiomsPackage.Literals.IDIOM__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(IdiomsPackage.Literals.IDIOM__FOR_ECLASS, -1
				),
				createSerializationReference(IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE, -1
				),
				createSerializationReference(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS, 10/* SubIdiom */,
					(36/*SubIdiom*/ << 4) | 0 /*[1]*/
				)
			});
		// Idioms::IdiomsImport-0(idioms::IdiomsImport): { "with" idiomsModel=STRING { "as" as=ID }[?] }
		serializationRules[10] = createSerializationRule("IdiomsImport-0", 12,
			createSerializationMatchSteps(
				26		/* assign V0 = |IdiomsImport::as| */,
				6		/* assert (|IdiomsImport::idiomsModel| - 1) == 0 */
			),
			createSerializationSteps(
				75		/* 'with' || pre-comment soft-space value soft-space post-comment */,
				13		/* IdiomsImport::idiomsModel=STRING || pre-comment soft-space value soft-space post-comment */,
				84		/* V00*2-steps || value */,
				46		/* 'as' || pre-comment soft-space value soft-space post-comment */,
				3		/* IdiomsImport::as=ID || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.IDIOMS_IMPORT__AS, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL, -1
				)
			});
		// Idioms::IdiomsModel-0(idioms::IdiomsModel): { "model" names+=ID { "." names+=ID }[*] { ownedWiths+=IdiomsImport[*] ownedImportDeclarations+=EPackageDeclaration[*] ownedGrammarDeclarations+=GrammarDeclaration[*] } { ownedLocatorDeclarations+=LocatorDeclaration[*] ownedSegmentDeclarations+=SegmentDeclaration[*] ownedIdioms+=Idiom[*] } }
		serializationRules[11] = createSerializationRule("IdiomsModel-0", 13,
			createSerializationMatchSteps(
				45		/* check-rule idioms::IdiomsModel.ownedGrammarDeclarations : GrammarDeclaration */,
				46		/* check-rule idioms::IdiomsModel.ownedIdioms : Idiom */,
				47		/* check-rule idioms::IdiomsModel.ownedImportDeclarations : EPackageDeclaration */,
				48		/* check-rule idioms::IdiomsModel.ownedLocatorDeclarations : LocatorDeclaration */,
				49		/* check-rule idioms::IdiomsModel.ownedSegmentDeclarations : SegmentDeclaration */,
				50		/* check-rule idioms::IdiomsModel.ownedWiths : IdiomsImport */,
				43		/* assign V6 = |IdiomsModel::ownedIdioms| */,
				42		/* assign V5 = |IdiomsModel::ownedSegmentDeclarations| */,
				41		/* assign V4 = |IdiomsModel::ownedLocatorDeclarations| */,
				39		/* assign V3 = |IdiomsModel::ownedGrammarDeclarations| */,
				37		/* assign V2 = |IdiomsModel::ownedImportDeclarations| */,
				34		/* assign V1 = |IdiomsModel::ownedWiths| */,
				20		/* assign V0 = (|IdiomsModel::names| - 1) */
			),
			createSerializationSteps(
				60		/* 'model' || pre-comment soft-space value soft-space post-comment */,
				22		/* IdiomsModel::names+=ID || pre-comment soft-space value soft-space post-comment */,
				85		/* V00*2-steps || value */,
				41		/* '.' || pre-comment no-space value no-space post-comment */,
				22		/* IdiomsModel::names+=ID || pre-comment soft-space value soft-space post-comment */,
				88		/* V01*1-steps || value */,
				33		/* IdiomsModel::ownedWiths+=IdiomsImport || soft-new-line value soft-new-line */,
				91		/* V02*1-steps || value */,
				25		/* IdiomsModel::ownedImportDeclarations+=EPackageDeclaration || soft-new-line value soft-new-line */,
				93		/* V03*1-steps || value */,
				23		/* IdiomsModel::ownedGrammarDeclarations+=GrammarDeclaration || value */,
				96		/* V04*1-steps || new-line soft-new-line value soft-new-line */,
				28		/* IdiomsModel::ownedLocatorDeclarations+=LocatorDeclaration || value */,
				97		/* V05*1-steps || new-line soft-new-line value soft-new-line */,
				30		/* IdiomsModel::ownedSegmentDeclarations+=SegmentDeclaration || value */,
				98		/* V06*1-steps || value */,
				24		/* IdiomsModel::ownedIdioms+=Idiom || new-line soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.IDIOMS_MODEL__NAMES, false, GrammarCardinality.ONE_OR_MORE),
				createSerializationReference(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_GRAMMAR_DECLARATIONS, 1/* GrammarDeclaration */,
					(7/*GrammarDeclaration*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS, 2/* Idiom */,
					(11/*Idiom*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORT_DECLARATIONS, 0/* EPackageDeclaration */,
					(5/*EPackageDeclaration*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS, 5/* LocatorDeclaration */,
					(16/*LocatorDeclaration*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS, 9/* SegmentDeclaration */,
					(32/*SegmentDeclaration*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS, 3/* IdiomsImport */,
					(12/*IdiomsImport*/ << 4) | 2 /*[*]*/
				)
			});
		// Idioms::KeywordLocator-0(idioms::KeywordLocator): string=STRING
		serializationRules[12] = createSerializationRule("KeywordLocator-0", 14,
			createSerializationMatchSteps(
				7		/* assert (|KeywordLocator::string| - 1) == 0 */
			),
			createSerializationSteps(
				38		/* KeywordLocator::string=STRING || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.KEYWORD_LOCATOR__STRING, true, GrammarCardinality.ONE)
			});
		// Idioms::LocatorDeclaration-0(idioms::LocatorDeclaration): { "locator" name=ID ownedLocator=Locator ";" }
		serializationRules[13] = createSerializationRule("LocatorDeclaration-0", 16,
			createSerializationMatchSteps(
				51		/* check-rule idioms::LocatorDeclaration.ownedLocator : AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator|RuleLocator */,
				9		/* assert (|LocatorDeclaration::ownedLocator| - 1) == 0 */,
				8		/* assert (|LocatorDeclaration::name| - 1) == 0 */
			),
			createSerializationSteps(
				59		/* 'locator' || pre-comment soft-space value soft-space post-comment */,
				20		/* LocatorDeclaration::name=ID || pre-comment soft-space value soft-space post-comment */,
				26		/* LocatorDeclaration::ownedLocator=Locator || value */,
				43		/* ';' || pre-comment no-space value soft-new-line post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.LOCATOR_DECLARATION__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR, 4/* Locator */,
					(15/*Locator*/ << 4) | 0 /*[1]*/
				)
			});
		// Idioms::NewLineSegment-0(idioms::NewLineSegment): "new-line"
		serializationRules[14] = createSerializationRule("NewLineSegment-0", 18,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				61		/* 'new-line' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::NoNewLineSegment-0(idioms::NoNewLineSegment): "no-new-line"
		serializationRules[15] = createSerializationRule("NoNewLineSegment-0", 19,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				62		/* 'no-new-line' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::NoSpaceSegment-0(idioms::NoSpaceSegment): "no-space"
		serializationRules[16] = createSerializationRule("NoSpaceSegment-0", 20,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				63		/* 'no-space' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::PopSegment-0(idioms::PopSegment): "pop"
		serializationRules[17] = createSerializationRule("PopSegment-0", 21,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				64		/* 'pop' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::PostCommentSegment-0(idioms::PostCommentSegment): "post-comment"
		serializationRules[18] = createSerializationRule("PostCommentSegment-0", 22,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				65		/* 'post-comment' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::PreCommentSegment-0(idioms::PreCommentSegment): "pre-comment"
		serializationRules[19] = createSerializationRule("PreCommentSegment-0", 23,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				66		/* 'pre-comment' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::PushSegment-0(idioms::PushSegment): "push"
		serializationRules[20] = createSerializationRule("PushSegment-0", 24,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				67		/* 'push' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::ReferredLocator-0(idioms::ReferredLocator): { { idiomsModel=ID "::" }[?] locatorDeclaration=ID }
		serializationRules[21] = createSerializationRule("ReferredLocator-0", 25,
			createSerializationMatchSteps(
				10		/* assert (|ReferredLocator::locatorDeclaration| - 1) == 0 */,
				27		/* assign V0 = |ReferredLocator::idiomsModel| */
			),
			createSerializationSteps(
				84		/* V00*2-steps || value */,
				14		/* ReferredLocator::idiomsModel=ID || pre-comment soft-space value soft-space post-comment */,
				42		/* '::' || pre-comment no-space value no-space post-comment */,
				17		/* ReferredLocator::locatorDeclaration=ID || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(IdiomsPackage.Literals.REFERRED_LOCATOR__IDIOMS_MODEL, -1
				),
				createSerializationReference(IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION, -1
				)
			});
		// Idioms::ReferredSegment-0(idioms::ReferredSegment): { { idiomsModel=ID "::" }[?] segmentDeclaration=ID }
		serializationRules[22] = createSerializationRule("ReferredSegment-0", 26,
			createSerializationMatchSteps(
				11		/* assert (|ReferredSegment::segmentDeclaration| - 1) == 0 */,
				28		/* assign V0 = |ReferredSegment::idiomsModel| */
			),
			createSerializationSteps(
				84		/* V00*2-steps || value */,
				15		/* ReferredSegment::idiomsModel=ID || pre-comment soft-space value soft-space post-comment */,
				42		/* '::' || pre-comment no-space value no-space post-comment */,
				37		/* ReferredSegment::segmentDeclaration=ID || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(IdiomsPackage.Literals.REFERRED_SEGMENT__IDIOMS_MODEL, -1
				),
				createSerializationReference(IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION, -1
				)
			});
		// Idioms::ReturnsLocator-0(idioms::ReturnsLocator): { "returns" { ePackage=ID "::" }[?] eClass=ID }
		serializationRules[23] = createSerializationRule("ReturnsLocator-0", 27,
			createSerializationMatchSteps(
				12		/* assert (|ReturnsLocator::eClass| - 1) == 0 */,
				29		/* assign V0 = |ReturnsLocator::ePackage| */
			),
			createSerializationSteps(
				68		/* 'returns' || pre-comment soft-space value soft-space post-comment */,
				84		/* V00*2-steps || value */,
				8		/* ReturnsLocator::ePackage=ID || pre-comment soft-space value soft-space post-comment */,
				42		/* '::' || pre-comment no-space value no-space post-comment */,
				5		/* ReturnsLocator::eClass=ID || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS, -1
				),
				createSerializationReference(IdiomsPackage.Literals.RETURNS_LOCATOR__EPACKAGE, -1
				)
			});
		// Idioms::RuleLocator-0(idioms::RuleLocator): { "rule" { referredGrammar=ID "::" }[?] referredRule=ID }
		serializationRules[24] = createSerializationRule("RuleLocator-0", 28,
			createSerializationMatchSteps(
				13		/* assert (|RuleLocator::referredRule| - 1) == 0 */,
				30		/* assign V0 = |RuleLocator::referredGrammar| */
			),
			createSerializationSteps(
				69		/* 'rule' || pre-comment soft-space value soft-space post-comment */,
				84		/* V00*2-steps || value */,
				35		/* RuleLocator::referredGrammar=ID || pre-comment soft-space value soft-space post-comment */,
				42		/* '::' || pre-comment no-space value no-space post-comment */,
				36		/* RuleLocator::referredRule=ID || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(IdiomsPackage.Literals.RULE_LOCATOR__REFERRED_GRAMMAR, -1
				),
				createSerializationReference(IdiomsPackage.Literals.RULE_LOCATOR__REFERRED_RULE, -1
				)
			});
		// Idioms::SegmentDeclaration-0(idioms::SegmentDeclaration): { "segment" name=ID ownedSegment=Segment ";" }
		serializationRules[25] = createSerializationRule("SegmentDeclaration-0", 32,
			createSerializationMatchSteps(
				52		/* check-rule idioms::SegmentDeclaration.ownedSegment : CustomSegment|HalfNewLineSegment|NewLineSegment|NoNewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */,
				15		/* assert (|SegmentDeclaration::ownedSegment| - 1) == 0 */,
				14		/* assert (|SegmentDeclaration::name| - 1) == 0 */
			),
			createSerializationSteps(
				70		/* 'segment' || pre-comment soft-space value soft-space post-comment */,
				21		/* SegmentDeclaration::name=ID || pre-comment soft-space value soft-space post-comment */,
				29		/* SegmentDeclaration::ownedSegment=Segment || value */,
				43		/* ';' || pre-comment no-space value soft-new-line post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.SEGMENT_DECLARATION__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT, 7/* Segment */,
					(31/*Segment*/ << 4) | 0 /*[1]*/
				)
			});
		// Idioms::SoftNewLineSegment-0(idioms::SoftNewLineSegment): "soft-new-line"
		serializationRules[26] = createSerializationRule("SoftNewLineSegment-0", 33,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				71		/* 'soft-new-line' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::SoftSpaceSegment-0(idioms::SoftSpaceSegment): "soft-space"
		serializationRules[27] = createSerializationRule("SoftSpaceSegment-0", 34,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				72		/* 'soft-space' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::StringSegment-0(idioms::StringSegment): { "string" string=STRING printable?="printable"[?] }
		serializationRules[28] = createSerializationRule("StringSegment-0", 35,
			createSerializationMatchSteps(
				31		/* assign V0 = |StringSegment::printable.'printable'| */,
				16		/* assert (|StringSegment::string| - 1) == 0 */
			),
			createSerializationSteps(
				73		/* 'string' || pre-comment soft-space value soft-space post-comment */,
				39		/* StringSegment::string=STRING || pre-comment soft-space value soft-space post-comment */,
				83		/* V00*1-steps || value */,
				34		/* StringSegment::printable?='printable' || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(IdiomsPackage.Literals.STRING_SEGMENT__PRINTABLE, false,
					(2/*'printable'*/ << 4) | 1 /*[?]*/
				),
				createSerializationSimpleAttribute(IdiomsPackage.Literals.STRING_SEGMENT__STRING, true, GrammarCardinality.ONE)
			});
		// Idioms::SubIdiom-0(idioms::SubIdiom): { "at" "each" ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" }
		serializationRules[29] = createSerializationRule("SubIdiom-0", 36,
			createSerializationMatchSteps(
				19		/* assert |SubIdiom::all| == 0 */,
				53		/* check-rule idioms::SubIdiom.ownedLocator : AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator|RuleLocator */,
				54		/* check-rule idioms::SubIdiom.ownedSegments : CustomSegment|HalfNewLineSegment|NewLineSegment|NoNewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */,
				18		/* assert (|SubIdiom::ownedLocator| - 1) == 0 */,
				21		/* assign V0 = (|SubIdiom::ownedSegments| > 0) */,
				35		/* assign V1 = |SubIdiom::ownedSegments| */
			),
			createSerializationSteps(
				48		/* 'at' || pre-comment soft-space value soft-space post-comment */,
				51		/* 'each' || pre-comment soft-space value soft-space post-comment */,
				27		/* SubIdiom::ownedLocator=Locator || value */,
				86		/* V00*3-steps || value */,
				50		/* 'do' || pre-comment soft-space value soft-space post-comment */,
				88		/* V01*1-steps || value */,
				31		/* SubIdiom::ownedSegments+=Segment|ReferredSegment || value */,
				43		/* ';' || pre-comment no-space value soft-new-line post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR, 4/* Locator */,
					(15/*Locator*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS, 8/* ReferredSegment,Segment */,
					(26/*ReferredSegment*/ << 4) | 2 /*[*]*/,
					(31/*Segment*/ << 4) | 2 /*[*]*/
				)
			});
		// Idioms::SubIdiom-1(idioms::SubIdiom): { "at" all?="all" ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" }
		serializationRules[30] = createSerializationRule("SubIdiom-1", 36,
			createSerializationMatchSteps(
				53		/* check-rule idioms::SubIdiom.ownedLocator : AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator|RuleLocator */,
				54		/* check-rule idioms::SubIdiom.ownedSegments : CustomSegment|HalfNewLineSegment|NewLineSegment|NoNewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */,
				18		/* assert (|SubIdiom::ownedLocator| - 1) == 0 */,
				17		/* assert (|SubIdiom::all.'all'| - 1) == 0 */,
				21		/* assign V0 = (|SubIdiom::ownedSegments| > 0) */,
				35		/* assign V1 = |SubIdiom::ownedSegments| */
			),
			createSerializationSteps(
				48		/* 'at' || pre-comment soft-space value soft-space post-comment */,
				0		/* SubIdiom::all?='all' || pre-comment soft-space value soft-space post-comment */,
				27		/* SubIdiom::ownedLocator=Locator || value */,
				86		/* V00*3-steps || value */,
				50		/* 'do' || pre-comment soft-space value soft-space post-comment */,
				88		/* V01*1-steps || value */,
				31		/* SubIdiom::ownedSegments+=Segment|ReferredSegment || value */,
				43		/* ';' || pre-comment no-space value soft-new-line post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(IdiomsPackage.Literals.SUB_IDIOM__ALL, false,
					(0/*'all'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR, 4/* Locator */,
					(15/*Locator*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS, 8/* ReferredSegment,Segment */,
					(26/*ReferredSegment*/ << 4) | 2 /*[*]*/,
					(31/*Segment*/ << 4) | 2 /*[*]*/
				)
			});
		// Idioms::SubIdiom-2(idioms::SubIdiom): { "at" ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" }
		serializationRules[31] = createSerializationRule("SubIdiom-2", 36,
			createSerializationMatchSteps(
				19		/* assert |SubIdiom::all| == 0 */,
				53		/* check-rule idioms::SubIdiom.ownedLocator : AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator|RuleLocator */,
				54		/* check-rule idioms::SubIdiom.ownedSegments : CustomSegment|HalfNewLineSegment|NewLineSegment|NoNewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */,
				18		/* assert (|SubIdiom::ownedLocator| - 1) == 0 */,
				21		/* assign V0 = (|SubIdiom::ownedSegments| > 0) */,
				35		/* assign V1 = |SubIdiom::ownedSegments| */
			),
			createSerializationSteps(
				48		/* 'at' || pre-comment soft-space value soft-space post-comment */,
				27		/* SubIdiom::ownedLocator=Locator || value */,
				86		/* V00*3-steps || value */,
				50		/* 'do' || pre-comment soft-space value soft-space post-comment */,
				88		/* V01*1-steps || value */,
				31		/* SubIdiom::ownedSegments+=Segment|ReferredSegment || value */,
				43		/* ';' || pre-comment no-space value soft-new-line post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR, 4/* Locator */,
					(15/*Locator*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS, 8/* ReferredSegment,Segment */,
					(26/*ReferredSegment*/ << 4) | 2 /*[*]*/,
					(31/*Segment*/ << 4) | 2 /*[*]*/
				)
			});
		// Idioms::ValueSegment-0(idioms::ValueSegment): "value"
		serializationRules[32] = createSerializationRule("ValueSegment-0", 37,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				74		/* 'value' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::WrapAnchorSegment-0(idioms::WrapAnchorSegment): "wrap-anchor"
		serializationRules[33] = createSerializationRule("WrapAnchorSegment-0", 39,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				76		/* 'wrap-anchor' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::WrapBeginAllSegment-0(idioms::WrapBeginAllSegment): "wrap-begin-all"
		serializationRules[34] = createSerializationRule("WrapBeginAllSegment-0", 40,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				77		/* 'wrap-begin-all' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::WrapBeginSomeSegment-0(idioms::WrapBeginSomeSegment): "wrap-begin-some"
		serializationRules[35] = createSerializationRule("WrapBeginSomeSegment-0", 41,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				78		/* 'wrap-begin-some' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::WrapEndSegment-0(idioms::WrapEndSegment): "wrap-end"
		serializationRules[36] = createSerializationRule("WrapEndSegment-0", 42,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				79		/* 'wrap-end' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::WrapHereSegment-0(idioms::WrapHereSegment): "wrap-here"
		serializationRules[37] = createSerializationRule("WrapHereSegment-0", 43,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				80		/* 'wrap-here' || pre-comment soft-space value soft-space post-comment */
			),
			null);
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
			SerializationSegment.PRE_COMMENT /* pre-comment */,
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.POST_COMMENT /* post-comment */
		};
		serializationSegments[5] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.PRE_COMMENT /* pre-comment */,
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.POST_COMMENT /* post-comment */
		};
		serializationSegments[6] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.PRE_COMMENT /* pre-comment */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.POST_COMMENT /* post-comment */
		};
		serializationSegments[7] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.PRE_COMMENT /* pre-comment */,
			SerializationSegment.POP /* pop */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.POST_COMMENT /* post-comment */
		};
		serializationSegments[8] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.PRE_COMMENT /* pre-comment */,
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.PUSH /* push */,
			SerializationSegment.SOFT_NEW_LINE /* soft-new-line */,
			SerializationSegment.POST_COMMENT /* post-comment */
		};
	}

	/**
	 * Initialize the various serialization steps used to serialize a serialization rule.
	 */
	private void initSerializationSteps() {
		// 0: SubIdiom::all?='all' || pre-comment soft-space value soft-space post-comment
		serializationSteps[0] = createSerializationStepAssignKeyword(IdiomsPackage.Literals.SUB_IDIOM__ALL, 0 /* 'all' */, 6);
		// 1: EPackageDeclaration::as=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[1] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.EPACKAGE_DECLARATION__AS, 9 /*ID*/, 6);
		// 2: GrammarDeclaration::as=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[2] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.GRAMMAR_DECLARATION__AS, 9 /*ID*/, 6);
		// 3: IdiomsImport::as=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[3] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_IMPORT__AS, 9 /*ID*/, 6);
		// 4: AssignmentLocator::eClass=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[4] = createSerializationStepCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ECLASS, getCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ECLASS, "ID"), 9, 6);
		// 5: ReturnsLocator::eClass=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[5] = createSerializationStepCrossReference(IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS, getCrossReference(IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS, "ID"), 9, 6);
		// 6: AssignmentLocator::ePackage=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[6] = createSerializationStepCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__EPACKAGE, getCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__EPACKAGE, "ID"), 9, 6);
		// 7: EPackageDeclaration::ePackage=STRING || pre-comment soft-space value soft-space post-comment
		serializationSteps[7] = createSerializationStepCrossReference(IdiomsPackage.Literals.EPACKAGE_DECLARATION__EPACKAGE, getCrossReference(IdiomsPackage.Literals.EPACKAGE_DECLARATION__EPACKAGE, "STRING"), 30, 6);
		// 8: ReturnsLocator::ePackage=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[8] = createSerializationStepCrossReference(IdiomsPackage.Literals.RETURNS_LOCATOR__EPACKAGE, getCrossReference(IdiomsPackage.Literals.RETURNS_LOCATOR__EPACKAGE, "ID"), 9, 6);
		// 9: AssignmentLocator::eStructuralFeature=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[9] = createSerializationStepCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE, getCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE, "ID"), 9, 6);
		// 10: Idiom::forEClass=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[10] = createSerializationStepCrossReference(IdiomsPackage.Literals.IDIOM__FOR_ECLASS, getCrossReference(IdiomsPackage.Literals.IDIOM__FOR_ECLASS, "ID"), 9, 6);
		// 11: Idiom::forEPackage=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[11] = createSerializationStepCrossReference(IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE, getCrossReference(IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE, "ID"), 9, 6);
		// 12: GrammarDeclaration::grammar=STRING || pre-comment soft-space value soft-space post-comment
		serializationSteps[12] = createSerializationStepCrossReference(IdiomsPackage.Literals.GRAMMAR_DECLARATION__GRAMMAR, getCrossReference(IdiomsPackage.Literals.GRAMMAR_DECLARATION__GRAMMAR, "STRING"), 30, 6);
		// 13: IdiomsImport::idiomsModel=STRING || pre-comment soft-space value soft-space post-comment
		serializationSteps[13] = createSerializationStepCrossReference(IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL, getCrossReference(IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL, "STRING"), 30, 6);
		// 14: ReferredLocator::idiomsModel=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[14] = createSerializationStepCrossReference(IdiomsPackage.Literals.REFERRED_LOCATOR__IDIOMS_MODEL, getCrossReference(IdiomsPackage.Literals.REFERRED_LOCATOR__IDIOMS_MODEL, "ID"), 9, 6);
		// 15: ReferredSegment::idiomsModel=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[15] = createSerializationStepCrossReference(IdiomsPackage.Literals.REFERRED_SEGMENT__IDIOMS_MODEL, getCrossReference(IdiomsPackage.Literals.REFERRED_SEGMENT__IDIOMS_MODEL, "ID"), 9, 6);
		// 16: Idiom::inRuleRegex=STRING || pre-comment soft-space value soft-space post-comment
		serializationSteps[16] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOM__IN_RULE_REGEX, 30 /*STRING*/, 6);
		// 17: ReferredLocator::locatorDeclaration=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[17] = createSerializationStepCrossReference(IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION, getCrossReference(IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION, "ID"), 9, 6);
		// 18: Idiom::mixin?='mixin' || pre-comment soft-space value soft-space post-comment
		serializationSteps[18] = createSerializationStepAssignKeyword(IdiomsPackage.Literals.IDIOM__MIXIN, 1 /* 'mixin' */, 6);
		// 19: Idiom::name=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[19] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOM__NAME, 9 /*ID*/, 6);
		// 20: LocatorDeclaration::name=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[20] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.LOCATOR_DECLARATION__NAME, 9 /*ID*/, 6);
		// 21: SegmentDeclaration::name=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[21] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.SEGMENT_DECLARATION__NAME, 9 /*ID*/, 6);
		// 22: IdiomsModel::names+=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[22] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__NAMES, 9 /*ID*/, 6);
		// 23: IdiomsModel::ownedGrammarDeclarations+=GrammarDeclaration || value
		serializationSteps[23] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_GRAMMAR_DECLARATIONS, 7 /*GrammarDeclaration*/, 0);
		// 24: IdiomsModel::ownedIdioms+=Idiom || new-line soft-new-line value soft-new-line
		serializationSteps[24] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS, 11 /*Idiom*/, 3);
		// 25: IdiomsModel::ownedImportDeclarations+=EPackageDeclaration || soft-new-line value soft-new-line
		serializationSteps[25] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORT_DECLARATIONS, 5 /*EPackageDeclaration*/, 2);
		// 26: LocatorDeclaration::ownedLocator=Locator || value
		serializationSteps[26] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR, 15 /*Locator*/, 0);
		// 27: SubIdiom::ownedLocator=Locator || value
		serializationSteps[27] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR, 15 /*Locator*/, 0);
		// 28: IdiomsModel::ownedLocatorDeclarations+=LocatorDeclaration || value
		serializationSteps[28] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS, 16 /*LocatorDeclaration*/, 0);
		// 29: SegmentDeclaration::ownedSegment=Segment || value
		serializationSteps[29] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT, 31 /*Segment*/, 0);
		// 30: IdiomsModel::ownedSegmentDeclarations+=SegmentDeclaration || value
		serializationSteps[30] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS, 32 /*SegmentDeclaration*/, 0);
		// 31: SubIdiom::ownedSegments+=Segment|ReferredSegment || value
		serializationSteps[31] = createSerializationStepAssigns(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS, -1, new int[] { 31/*Segment*/,26/*ReferredSegment*/}, 0);
		// 32: Idiom::ownedSubIdioms+=SubIdiom || value soft-new-line
		serializationSteps[32] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS, 36 /*SubIdiom*/, 1);
		// 33: IdiomsModel::ownedWiths+=IdiomsImport || soft-new-line value soft-new-line
		serializationSteps[33] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS, 12 /*IdiomsImport*/, 2);
		// 34: StringSegment::printable?='printable' || pre-comment soft-space value soft-space post-comment
		serializationSteps[34] = createSerializationStepAssignKeyword(IdiomsPackage.Literals.STRING_SEGMENT__PRINTABLE, 2 /* 'printable' */, 6);
		// 35: RuleLocator::referredGrammar=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[35] = createSerializationStepCrossReference(IdiomsPackage.Literals.RULE_LOCATOR__REFERRED_GRAMMAR, getCrossReference(IdiomsPackage.Literals.RULE_LOCATOR__REFERRED_GRAMMAR, "ID"), 9, 6);
		// 36: RuleLocator::referredRule=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[36] = createSerializationStepCrossReference(IdiomsPackage.Literals.RULE_LOCATOR__REFERRED_RULE, getCrossReference(IdiomsPackage.Literals.RULE_LOCATOR__REFERRED_RULE, "ID"), 9, 6);
		// 37: ReferredSegment::segmentDeclaration=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[37] = createSerializationStepCrossReference(IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION, getCrossReference(IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION, "ID"), 9, 6);
		// 38: KeywordLocator::string=STRING || pre-comment soft-space value soft-space post-comment
		serializationSteps[38] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.KEYWORD_LOCATOR__STRING, 30 /*STRING*/, 6);
		// 39: StringSegment::string=STRING || pre-comment soft-space value soft-space post-comment
		serializationSteps[39] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.STRING_SEGMENT__STRING, 30 /*STRING*/, 6);
		// 40: CustomSegment::supportClassName=STRING || pre-comment soft-space value soft-space post-comment
		serializationSteps[40] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.CUSTOM_SEGMENT__SUPPORT_CLASS_NAME, 30 /*STRING*/, 6);
		// 41: '.' || pre-comment no-space value no-space post-comment
		serializationSteps[41] = createSerializationStepKeyword(".", 4);
		// 42: '::' || pre-comment no-space value no-space post-comment
		serializationSteps[42] = createSerializationStepKeyword("::", 4);
		// 43: ';' || pre-comment no-space value soft-new-line post-comment
		serializationSteps[43] = createSerializationStepKeyword(";", 5);
		// 44: 'any-assignment' || pre-comment soft-space value soft-space post-comment
		serializationSteps[44] = createSerializationStepKeyword("any-assignment", 6);
		// 45: 'any-element' || pre-comment soft-space value soft-space post-comment
		serializationSteps[45] = createSerializationStepKeyword("any-element", 6);
		// 46: 'as' || pre-comment soft-space value soft-space post-comment
		serializationSteps[46] = createSerializationStepKeyword("as", 6);
		// 47: 'assignment' || pre-comment soft-space value soft-space post-comment
		serializationSteps[47] = createSerializationStepKeyword("assignment", 6);
		// 48: 'at' || pre-comment soft-space value soft-space post-comment
		serializationSteps[48] = createSerializationStepKeyword("at", 6);
		// 49: 'custom' || pre-comment soft-space value soft-space post-comment
		serializationSteps[49] = createSerializationStepKeyword("custom", 6);
		// 50: 'do' || pre-comment soft-space value soft-space post-comment
		serializationSteps[50] = createSerializationStepKeyword("do", 6);
		// 51: 'each' || pre-comment soft-space value soft-space post-comment
		serializationSteps[51] = createSerializationStepKeyword("each", 6);
		// 52: 'final' || pre-comment soft-space value soft-space post-comment
		serializationSteps[52] = createSerializationStepKeyword("final", 6);
		// 53: 'for' || pre-comment soft-space value soft-space post-comment
		serializationSteps[53] = createSerializationStepKeyword("for", 6);
		// 54: 'grammar' || pre-comment soft-space value soft-space post-comment
		serializationSteps[54] = createSerializationStepKeyword("grammar", 6);
		// 55: 'half-new-line' || pre-comment soft-space value soft-space post-comment
		serializationSteps[55] = createSerializationStepKeyword("half-new-line", 6);
		// 56: 'idiom' || pre-comment soft-space value soft-space post-comment
		serializationSteps[56] = createSerializationStepKeyword("idiom", 6);
		// 57: 'import' || pre-comment soft-space value soft-space post-comment
		serializationSteps[57] = createSerializationStepKeyword("import", 6);
		// 58: 'in' || pre-comment soft-space value soft-space post-comment
		serializationSteps[58] = createSerializationStepKeyword("in", 6);
		// 59: 'locator' || pre-comment soft-space value soft-space post-comment
		serializationSteps[59] = createSerializationStepKeyword("locator", 6);
		// 60: 'model' || pre-comment soft-space value soft-space post-comment
		serializationSteps[60] = createSerializationStepKeyword("model", 6);
		// 61: 'new-line' || pre-comment soft-space value soft-space post-comment
		serializationSteps[61] = createSerializationStepKeyword("new-line", 6);
		// 62: 'no-new-line' || pre-comment soft-space value soft-space post-comment
		serializationSteps[62] = createSerializationStepKeyword("no-new-line", 6);
		// 63: 'no-space' || pre-comment soft-space value soft-space post-comment
		serializationSteps[63] = createSerializationStepKeyword("no-space", 6);
		// 64: 'pop' || pre-comment soft-space value soft-space post-comment
		serializationSteps[64] = createSerializationStepKeyword("pop", 6);
		// 65: 'post-comment' || pre-comment soft-space value soft-space post-comment
		serializationSteps[65] = createSerializationStepKeyword("post-comment", 6);
		// 66: 'pre-comment' || pre-comment soft-space value soft-space post-comment
		serializationSteps[66] = createSerializationStepKeyword("pre-comment", 6);
		// 67: 'push' || pre-comment soft-space value soft-space post-comment
		serializationSteps[67] = createSerializationStepKeyword("push", 6);
		// 68: 'returns' || pre-comment soft-space value soft-space post-comment
		serializationSteps[68] = createSerializationStepKeyword("returns", 6);
		// 69: 'rule' || pre-comment soft-space value soft-space post-comment
		serializationSteps[69] = createSerializationStepKeyword("rule", 6);
		// 70: 'segment' || pre-comment soft-space value soft-space post-comment
		serializationSteps[70] = createSerializationStepKeyword("segment", 6);
		// 71: 'soft-new-line' || pre-comment soft-space value soft-space post-comment
		serializationSteps[71] = createSerializationStepKeyword("soft-new-line", 6);
		// 72: 'soft-space' || pre-comment soft-space value soft-space post-comment
		serializationSteps[72] = createSerializationStepKeyword("soft-space", 6);
		// 73: 'string' || pre-comment soft-space value soft-space post-comment
		serializationSteps[73] = createSerializationStepKeyword("string", 6);
		// 74: 'value' || pre-comment soft-space value soft-space post-comment
		serializationSteps[74] = createSerializationStepKeyword("value", 6);
		// 75: 'with' || pre-comment soft-space value soft-space post-comment
		serializationSteps[75] = createSerializationStepKeyword("with", 6);
		// 76: 'wrap-anchor' || pre-comment soft-space value soft-space post-comment
		serializationSteps[76] = createSerializationStepKeyword("wrap-anchor", 6);
		// 77: 'wrap-begin-all' || pre-comment soft-space value soft-space post-comment
		serializationSteps[77] = createSerializationStepKeyword("wrap-begin-all", 6);
		// 78: 'wrap-begin-some' || pre-comment soft-space value soft-space post-comment
		serializationSteps[78] = createSerializationStepKeyword("wrap-begin-some", 6);
		// 79: 'wrap-end' || pre-comment soft-space value soft-space post-comment
		serializationSteps[79] = createSerializationStepKeyword("wrap-end", 6);
		// 80: 'wrap-here' || pre-comment soft-space value soft-space post-comment
		serializationSteps[80] = createSerializationStepKeyword("wrap-here", 6);
		// 81: '{' || pre-comment soft-space value push soft-new-line post-comment
		serializationSteps[81] = createSerializationStepKeyword("{", 8);
		// 82: '}' || pre-comment pop soft-space value soft-new-line post-comment
		serializationSteps[82] = createSerializationStepKeyword("}", 7);
		// 83: V00*1-steps || value
		serializationSteps[83] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 1, 0);
		// 84: V00*2-steps || value
		serializationSteps[84] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 2, 0);
		// 85: V00*2-steps || value
		serializationSteps[85] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 2, 0);
		// 86: V00*3-steps || value
		serializationSteps[86] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 3, 0);
		// 87: V00*5-steps || value
		serializationSteps[87] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 5, 0);
		// 88: V01*1-steps || value
		serializationSteps[88] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 1, 0);
		// 89: V01*2-steps || value
		serializationSteps[89] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 2, 0);
		// 90: V01*5-steps || value
		serializationSteps[90] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 5, 0);
		// 91: V02*1-steps || value
		serializationSteps[91] = createSerializationStepSequence((2/*V2*/ << 4) | 2/*[*]*/, 1, 0);
		// 92: V02*2-steps || value
		serializationSteps[92] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 2, 0);
		// 93: V03*1-steps || value
		serializationSteps[93] = createSerializationStepSequence((3/*V3*/ << 4) | 2/*[*]*/, 1, 0);
		// 94: V03*2-steps || value
		serializationSteps[94] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 2, 0);
		// 95: V04*1-steps || value
		serializationSteps[95] = createSerializationStepSequence((4/*V4*/ << 4) | 2/*[*]*/, 1, 0);
		// 96: V04*1-steps || new-line soft-new-line value soft-new-line
		serializationSteps[96] = createSerializationStepSequence((4/*V4*/ << 4) | 2/*[*]*/, 1, 3);
		// 97: V05*1-steps || new-line soft-new-line value soft-new-line
		serializationSteps[97] = createSerializationStepSequence((5/*V5*/ << 4) | 2/*[*]*/, 1, 3);
		// 98: V06*1-steps || value
		serializationSteps[98] = createSerializationStepSequence((6/*V6*/ << 4) | 2/*[*]*/, 1, 0);
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
//	import EnumerationValueSingle;
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
//	import IdiomsPackage;
//	import Grammar;
//	import GrammarProvider;
