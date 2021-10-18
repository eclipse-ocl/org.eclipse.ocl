/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.serializer.AbstractSerializationMetaData;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue.EReference_TargetGrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueSingle;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMetaData;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.SerializationFeature;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.TerminalRuleValue;
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

	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[32];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[3];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[41];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[12];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[49];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[59];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[35];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [9] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[90];
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
		return 36;
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
		return 35;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 74;
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
	 * Initialize configuration for each EClass that may be serialized.
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
		eClassValues[4] = new EClassValue(IdiomsPackage.Literals.EPACKAGE_IMPORT,
			createSerializationRules(
				4 /* EPackageImport-0: 'import' EPackageImport::ePackage=STRING ('as' EPackageImport::as=ID)[V0:?] */
			), null
		);
		eClassValues[5] = new EClassValue(IdiomsPackage.Literals.FINAL_LOCATOR,
			createSerializationRules(
				5 /* FinalLocator-0: 'final' */
			), null
		);
		eClassValues[6] = new EClassValue(IdiomsPackage.Literals.HALF_NEW_LINE_SEGMENT,
			createSerializationRules(
				6 /* HalfNewLineSegment-0: 'half-new-line' */
			), null
		);
		eClassValues[7] = new EClassValue(IdiomsPackage.Literals.IDIOM,
			createSerializationRules(
				8 /* Idiom-1: (Idiom::mixin?='mixin')[V0:?] 'idiom' Idiom::name=ID ('for' (Idiom::forEPackage=ID '::')[V2:?] Idiom::forEClass=ID)[V1:?] ('in' Idiom::inRuleRegex=STRING)[V3:?] Idiom::ownedSubIdioms+=SubIdiom */,
				7 /* Idiom-0: (Idiom::mixin?='mixin')[V0:?] 'idiom' Idiom::name=ID ('for' (Idiom::forEPackage=ID '::')[V2:?] Idiom::forEClass=ID)[V1:?] ('in' Idiom::inRuleRegex=STRING)[V3:?] '{' (Idiom::ownedSubIdioms+=SubIdiom)[V4:*] '}' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS,
					9) /* SubIdiom */
			}
		);
		eClassValues[8] = new EClassValue(IdiomsPackage.Literals.IDIOMS_IMPORT,
			createSerializationRules(
				9 /* IdiomsImport-0: 'with' IdiomsImport::idiomsModel=STRING ('as' IdiomsImport::as=ID)[V0:?] */
			), null
		);
		eClassValues[9] = new EClassValue(IdiomsPackage.Literals.IDIOMS_MODEL,
			createSerializationRules(
				10 /* IdiomsModel-0: 'model' IdiomsModel::names+=ID ('.' IdiomsModel::names+=ID)[V0:*] (IdiomsModel::ownedWiths+=IdiomsImport)[V1:*] (IdiomsModel::ownedImports+=EPackageImport)[V2:*] (IdiomsModel::ownedLocatorDeclarations+=LocatorDeclaration)[V3:*] (IdiomsModel::ownedSegmentDeclarations+=SegmentDeclaration)[V4:*] (IdiomsModel::ownedIdioms+=Idiom)[V5:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS,
					1) /* Idiom */,
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORTS,
					0) /* EPackageImport */,
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS,
					4) /* LocatorDeclaration */,
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS,
					8) /* SegmentDeclaration */,
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS,
					2) /* IdiomsImport */
			}
		);
		eClassValues[10] = new EClassValue(IdiomsPackage.Literals.KEYWORD_LOCATOR,
			createSerializationRules(
				11 /* KeywordLocator-0: KeywordLocator::string=STRING */
			), null
		);
		eClassValues[11] = new EClassValue(IdiomsPackage.Literals.LOCATOR_DECLARATION,
			createSerializationRules(
				12 /* LocatorDeclaration-0: 'locator' LocatorDeclaration::name=ID LocatorDeclaration::ownedLocator=Locator ';' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR,
					5) /* AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator */
			}
		);
		eClassValues[12] = new EClassValue(IdiomsPackage.Literals.NEW_LINE_SEGMENT,
			createSerializationRules(
				13 /* NewLineSegment-0: 'new-line' */
			), null
		);
		eClassValues[13] = new EClassValue(IdiomsPackage.Literals.NO_SPACE_SEGMENT,
			createSerializationRules(
				14 /* NoSpaceSegment-0: 'no-space' */
			), null
		);
		eClassValues[14] = new EClassValue(IdiomsPackage.Literals.POP_SEGMENT,
			createSerializationRules(
				15 /* PopSegment-0: 'pop' */
			), null
		);
		eClassValues[15] = new EClassValue(IdiomsPackage.Literals.POST_COMMENT_SEGMENT,
			createSerializationRules(
				16 /* PostCommentSegment-0: 'post-comment' */
			), null
		);
		eClassValues[16] = new EClassValue(IdiomsPackage.Literals.PRE_COMMENT_SEGMENT,
			createSerializationRules(
				17 /* PreCommentSegment-0: 'pre-comment' */
			), null
		);
		eClassValues[17] = new EClassValue(IdiomsPackage.Literals.PUSH_SEGMENT,
			createSerializationRules(
				18 /* PushSegment-0: 'push' */
			), null
		);
		eClassValues[18] = new EClassValue(IdiomsPackage.Literals.REFERRED_LOCATOR,
			createSerializationRules(
				19 /* ReferredLocator-0: (ReferredLocator::idiomsModel=ID '::')[V0:?] ReferredLocator::locatorDeclaration=ID */
			), null
		);
		eClassValues[19] = new EClassValue(IdiomsPackage.Literals.REFERRED_SEGMENT,
			createSerializationRules(
				20 /* ReferredSegment-0: (ReferredSegment::idiomsModel=ID '::')[V0:?] ReferredSegment::segmentDeclaration=ID */
			), null
		);
		eClassValues[20] = new EClassValue(IdiomsPackage.Literals.RETURNS_LOCATOR,
			createSerializationRules(
				21 /* ReturnsLocator-0: 'returns' (ReturnsLocator::ePackage=ID '::')[V0:?] ReturnsLocator::eClass=ID */
			), null
		);
		eClassValues[21] = new EClassValue(IdiomsPackage.Literals.SEGMENT_DECLARATION,
			createSerializationRules(
				22 /* SegmentDeclaration-0: 'segment' SegmentDeclaration::name=ID SegmentDeclaration::ownedSegment=Segment ';' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT,
					10) /* CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */
			}
		);
		eClassValues[22] = new EClassValue(IdiomsPackage.Literals.SOFT_NEW_LINE_SEGMENT,
			createSerializationRules(
				23 /* SoftNewLineSegment-0: 'soft-new-line' */
			), null
		);
		eClassValues[23] = new EClassValue(IdiomsPackage.Literals.SOFT_SPACE_SEGMENT,
			createSerializationRules(
				24 /* SoftSpaceSegment-0: 'soft-space' */
			), null
		);
		eClassValues[24] = new EClassValue(IdiomsPackage.Literals.STRING_SEGMENT,
			createSerializationRules(
				25 /* StringSegment-0: 'string' StringSegment::string=STRING StringSegment::printable?='printable' */
			), null
		);
		eClassValues[25] = new EClassValue(IdiomsPackage.Literals.SUB_IDIOM,
			createSerializationRules(
				28 /* SubIdiom-2: 'at' SubIdiom::ownedLocator=Locator ('do' (SubIdiom::ownedSegments+=Segment|ReferredSegment)[V1:*])[V0:?] ';' */,
				26 /* SubIdiom-0: 'at' 'each' SubIdiom::ownedLocator=Locator ('do' (SubIdiom::ownedSegments+=Segment|ReferredSegment)[V1:*])[V0:?] ';' */,
				27 /* SubIdiom-1: 'at' SubIdiom::all?='all' SubIdiom::ownedLocator=Locator ('do' (SubIdiom::ownedSegments+=Segment|ReferredSegment)[V1:*])[V0:?] ';' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR,
					5) /* AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator */,
				createEReference_TargetGrammarRuleVector(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS,
					11) /* CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */
			}
		);
		eClassValues[26] = new EClassValue(IdiomsPackage.Literals.VALUE_SEGMENT,
			createSerializationRules(
				29 /* ValueSegment-0: 'value' */
			), null
		);
		eClassValues[27] = new EClassValue(IdiomsPackage.Literals.WRAP_ANCHOR_SEGMENT,
			createSerializationRules(
				30 /* WrapAnchorSegment-0: 'wrap-anchor' */
			), null
		);
		eClassValues[28] = new EClassValue(IdiomsPackage.Literals.WRAP_BEGIN_ALL_SEGMENT,
			createSerializationRules(
				31 /* WrapBeginAllSegment-0: 'wrap-begin-all' */
			), null
		);
		eClassValues[29] = new EClassValue(IdiomsPackage.Literals.WRAP_BEGIN_SOME_SEGMENT,
			createSerializationRules(
				32 /* WrapBeginSomeSegment-0: 'wrap-begin-some' */
			), null
		);
		eClassValues[30] = new EClassValue(IdiomsPackage.Literals.WRAP_END_SEGMENT,
			createSerializationRules(
				33 /* WrapEndSegment-0: 'wrap-end' */
			), null
		);
		eClassValues[31] = new EClassValue(IdiomsPackage.Literals.WRAP_HERE_SEGMENT,
			createSerializationRules(
				34 /* WrapHereSegment-0: 'wrap-here' */
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
		grammarRuleValues[5] = createParserRuleValue(5, "EPackageImport", -1,
			createSerializationRules(
				4	/* EPackageImport-0: 'import' EPackageImport::ePackage=STRING ('as' EPackageImport::as=ID)[V0:?] */
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
		grammarRuleValues[7] = createParserRuleValue(7, "HalfNewLineSegment", -1,
			createSerializationRules(
				6	/* HalfNewLineSegment-0: 'half-new-line' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {HalfNewLineSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "half-new-line" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[8] = new TerminalRuleValue(8, "ID");
		grammarRuleValues[9] = new TerminalRuleValue(9, "INT");
		grammarRuleValues[10] = createParserRuleValue(10, "Idiom", -1,
			createSerializationRules(
				7	/* Idiom-0: (Idiom::mixin?='mixin')[V0:?] 'idiom' Idiom::name=ID ('for' (Idiom::forEPackage=ID '::')[V2:?] Idiom::forEClass=ID)[V1:?] ('in' Idiom::inRuleRegex=STRING)[V3:?] '{' (Idiom::ownedSubIdioms+=SubIdiom)[V4:*] '}' */,
				8	/* Idiom-1: (Idiom::mixin?='mixin')[V0:?] 'idiom' Idiom::name=ID ('for' (Idiom::forEPackage=ID '::')[V2:?] Idiom::forEClass=ID)[V1:?] ('in' Idiom::inRuleRegex=STRING)[V3:?] Idiom::ownedSubIdioms+=SubIdiom */
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
		grammarRuleValues[11] = createParserRuleValue(11, "IdiomsImport", -1,
			createSerializationRules(
				9	/* IdiomsImport-0: 'with' IdiomsImport::idiomsModel=STRING ('as' IdiomsImport::as=ID)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "with" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* idiomsModel=STRING : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* "as" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* as=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 5	/* ";"? : [value] | [pre-comment, no-space, value, soft-new-line, post-comment] */
		);
		grammarRuleValues[12] = createParserRuleValue(12, "IdiomsModel", -1,
			createSerializationRules(
				10	/* IdiomsModel-0: 'model' IdiomsModel::names+=ID ('.' IdiomsModel::names+=ID)[V0:*] (IdiomsModel::ownedWiths+=IdiomsImport)[V1:*] (IdiomsModel::ownedImports+=EPackageImport)[V2:*] (IdiomsModel::ownedLocatorDeclarations+=LocatorDeclaration)[V3:*] (IdiomsModel::ownedSegmentDeclarations+=SegmentDeclaration)[V4:*] (IdiomsModel::ownedIdioms+=Idiom)[V5:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "model" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* names+=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 4	/* "." : [value] | [pre-comment, no-space, value, no-space, post-comment] */,
			(0 << 16) | 6	/* names+=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 2	/* ownedWiths+=IdiomsImport* : [value] | [soft-new-line, value, soft-new-line] */,
			(0 << 16) | 2	/* ownedImports+=EPackageImport* : [value] | [soft-new-line, value, soft-new-line] */,
			(0 << 16) | 0	/* Alternatives* : [value] | [value] */,
			(3 << 16) | 0	/* ownedLocatorDeclarations+=LocatorDeclaration : [new-line, soft-new-line, value, soft-new-line] | [value] */,
			(3 << 16) | 0	/* ownedSegmentDeclarations+=SegmentDeclaration : [new-line, soft-new-line, value, soft-new-line] | [value] */,
			(0 << 16) | 3	/* ownedIdioms+=Idiom : [value] | [new-line, soft-new-line, value, soft-new-line] */
		);
		grammarRuleValues[13] = createParserRuleValue(13, "KeywordLocator", -1,
			createSerializationRules(
				11	/* KeywordLocator-0: KeywordLocator::string=STRING */
			),
			(0 << 16) | 6	/* string=STRING : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[14] = createParserRuleValue(14, "Locator", 5 /* AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator */,
			createSerializationRules(
				0	/* AnyAssignmentLocator-0: 'any-assignment' */,
				1	/* AnyElementLocator-0: 'any-element' */,
				2	/* AssignmentLocator-0: 'assignment' ((AssignmentLocator::ePackage=ID '::')[V1:?] AssignmentLocator::eClass=ID '::')[V0:?] AssignmentLocator::eStructuralFeature=ID */,
				5	/* FinalLocator-0: 'final' */,
				11	/* KeywordLocator-0: KeywordLocator::string=STRING */,
				19	/* ReferredLocator-0: (ReferredLocator::idiomsModel=ID '::')[V0:?] ReferredLocator::locatorDeclaration=ID */,
				21	/* ReturnsLocator-0: 'returns' (ReturnsLocator::ePackage=ID '::')[V0:?] ReturnsLocator::eClass=ID */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* AnyAssignmentLocator : [value] | [value] */,
			(0 << 16) | 0	/* AnyElementLocator : [value] | [value] */,
			(0 << 16) | 0	/* AssignmentLocator : [value] | [value] */,
			(0 << 16) | 0	/* FinalLocator : [value] | [value] */,
			(0 << 16) | 0	/* KeywordLocator : [value] | [value] */,
			(0 << 16) | 0	/* ReferredLocator : [value] | [value] */,
			(0 << 16) | 0	/* ReturnsLocator : [value] | [value] */
		);
		grammarRuleValues[15] = createParserRuleValue(15, "LocatorDeclaration", -1,
			createSerializationRules(
				12	/* LocatorDeclaration-0: 'locator' LocatorDeclaration::name=ID LocatorDeclaration::ownedLocator=Locator ';' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "locator" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* name=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* ownedLocator=Locator : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [pre-comment, no-space, value, soft-new-line, post-comment] */
		);
		grammarRuleValues[16] = new TerminalRuleValue(16, "ML_COMMENT");
		grammarRuleValues[17] = createParserRuleValue(17, "NewLineSegment", -1,
			createSerializationRules(
				13	/* NewLineSegment-0: 'new-line' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {NewLineSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "new-line" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[18] = createParserRuleValue(18, "NoSpaceSegment", -1,
			createSerializationRules(
				14	/* NoSpaceSegment-0: 'no-space' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {NoSpaceSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "no-space" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[19] = createParserRuleValue(19, "PopSegment", -1,
			createSerializationRules(
				15	/* PopSegment-0: 'pop' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PopSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "pop" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[20] = createParserRuleValue(20, "PostCommentSegment", -1,
			createSerializationRules(
				16	/* PostCommentSegment-0: 'post-comment' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PostCommentSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "post-comment" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[21] = createParserRuleValue(21, "PreCommentSegment", -1,
			createSerializationRules(
				17	/* PreCommentSegment-0: 'pre-comment' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PreCommentSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "pre-comment" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[22] = createParserRuleValue(22, "PushSegment", -1,
			createSerializationRules(
				18	/* PushSegment-0: 'push' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {PushSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "push" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[23] = createParserRuleValue(23, "ReferredLocator", -1,
			createSerializationRules(
				19	/* ReferredLocator-0: (ReferredLocator::idiomsModel=ID '::')[V0:?] ReferredLocator::locatorDeclaration=ID */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* idiomsModel=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 4	/* "::" : [value] | [pre-comment, no-space, value, no-space, post-comment] */,
			(0 << 16) | 6	/* locatorDeclaration=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[24] = createParserRuleValue(24, "ReferredSegment", -1,
			createSerializationRules(
				20	/* ReferredSegment-0: (ReferredSegment::idiomsModel=ID '::')[V0:?] ReferredSegment::segmentDeclaration=ID */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* idiomsModel=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 4	/* "::" : [value] | [pre-comment, no-space, value, no-space, post-comment] */,
			(0 << 16) | 6	/* segmentDeclaration=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[25] = createParserRuleValue(25, "ReturnsLocator", -1,
			createSerializationRules(
				21	/* ReturnsLocator-0: 'returns' (ReturnsLocator::ePackage=ID '::')[V0:?] ReturnsLocator::eClass=ID */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "returns" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 6	/* ePackage=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 4	/* "::" : [value] | [pre-comment, no-space, value, no-space, post-comment] */,
			(0 << 16) | 6	/* eClass=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[26] = new TerminalRuleValue(26, "SL_COMMENT");
		grammarRuleValues[27] = new TerminalRuleValue(27, "STRING");
		grammarRuleValues[28] = createParserRuleValue(28, "Segment", 10 /* CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */,
			createSerializationRules(
				3	/* CustomSegment-0: 'custom' CustomSegment::supportClassName=STRING */,
				6	/* HalfNewLineSegment-0: 'half-new-line' */,
				13	/* NewLineSegment-0: 'new-line' */,
				14	/* NoSpaceSegment-0: 'no-space' */,
				15	/* PopSegment-0: 'pop' */,
				16	/* PostCommentSegment-0: 'post-comment' */,
				17	/* PreCommentSegment-0: 'pre-comment' */,
				18	/* PushSegment-0: 'push' */,
				23	/* SoftNewLineSegment-0: 'soft-new-line' */,
				24	/* SoftSpaceSegment-0: 'soft-space' */,
				25	/* StringSegment-0: 'string' StringSegment::string=STRING StringSegment::printable?='printable' */,
				29	/* ValueSegment-0: 'value' */,
				30	/* WrapAnchorSegment-0: 'wrap-anchor' */,
				31	/* WrapBeginAllSegment-0: 'wrap-begin-all' */,
				32	/* WrapBeginSomeSegment-0: 'wrap-begin-some' */,
				33	/* WrapEndSegment-0: 'wrap-end' */,
				34	/* WrapHereSegment-0: 'wrap-here' */
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
		grammarRuleValues[29] = createParserRuleValue(29, "SegmentDeclaration", -1,
			createSerializationRules(
				22	/* SegmentDeclaration-0: 'segment' SegmentDeclaration::name=ID SegmentDeclaration::ownedSegment=Segment ';' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "segment" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* name=ID : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 0	/* ownedSegment=Segment : [value] | [value] */,
			(0 << 16) | 5	/* ";" : [value] | [pre-comment, no-space, value, soft-new-line, post-comment] */
		);
		grammarRuleValues[30] = createParserRuleValue(30, "SoftNewLineSegment", -1,
			createSerializationRules(
				23	/* SoftNewLineSegment-0: 'soft-new-line' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {SoftNewLineSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "soft-new-line" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[31] = createParserRuleValue(31, "SoftSpaceSegment", -1,
			createSerializationRules(
				24	/* SoftSpaceSegment-0: 'soft-space' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {SoftSpaceSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "soft-space" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[32] = createParserRuleValue(32, "StringSegment", -1,
			createSerializationRules(
				25	/* StringSegment-0: 'string' StringSegment::string=STRING StringSegment::printable?='printable' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 6	/* "string" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* string=STRING : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */,
			(0 << 16) | 6	/* printable?="printable" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[33] = createParserRuleValue(33, "SubIdiom", -1,
			createSerializationRules(
				26	/* SubIdiom-0: 'at' 'each' SubIdiom::ownedLocator=Locator ('do' (SubIdiom::ownedSegments+=Segment|ReferredSegment)[V1:*])[V0:?] ';' */,
				27	/* SubIdiom-1: 'at' SubIdiom::all?='all' SubIdiom::ownedLocator=Locator ('do' (SubIdiom::ownedSegments+=Segment|ReferredSegment)[V1:*])[V0:?] ';' */,
				28	/* SubIdiom-2: 'at' SubIdiom::ownedLocator=Locator ('do' (SubIdiom::ownedSegments+=Segment|ReferredSegment)[V1:*])[V0:?] ';' */
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
		grammarRuleValues[34] = createParserRuleValue(34, "ValueSegment", -1,
			createSerializationRules(
				29	/* ValueSegment-0: 'value' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {ValueSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "value" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[35] = new TerminalRuleValue(35, "WS");
		grammarRuleValues[36] = createParserRuleValue(36, "WrapAnchorSegment", -1,
			createSerializationRules(
				30	/* WrapAnchorSegment-0: 'wrap-anchor' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapAnchorSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-anchor" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[37] = createParserRuleValue(37, "WrapBeginAllSegment", -1,
			createSerializationRules(
				31	/* WrapBeginAllSegment-0: 'wrap-begin-all' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapBeginAllSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-begin-all" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[38] = createParserRuleValue(38, "WrapBeginSomeSegment", -1,
			createSerializationRules(
				32	/* WrapBeginSomeSegment-0: 'wrap-begin-some' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapBeginSomeSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-begin-some" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[39] = createParserRuleValue(39, "WrapEndSegment", -1,
			createSerializationRules(
				33	/* WrapEndSegment-0: 'wrap-end' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WrapEndSegment} : [value] | [value] */,
			(0 << 16) | 6	/* "wrap-end" : [value] | [pre-comment, soft-space, value, soft-space, post-comment] */
		);
		grammarRuleValues[40] = createParserRuleValue(40, "WrapHereSegment", -1,
			createSerializationRules(
				34	/* WrapHereSegment-0: 'wrap-here' */
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
		// 0: EPackageImport
		grammarRuleVectors[0] = new GrammarRuleVector(0x20L);
		// 1: Idiom
		grammarRuleVectors[1] = new GrammarRuleVector(0x400L);
		// 2: IdiomsImport
		grammarRuleVectors[2] = new GrammarRuleVector(0x800L);
		// 3: Locator
		grammarRuleVectors[3] = new GrammarRuleVector(0x4000L);
		// 4: LocatorDeclaration
		grammarRuleVectors[4] = new GrammarRuleVector(0x8000L);
		// 5: AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator
		grammarRuleVectors[5] = new GrammarRuleVector(0x280604eL);
		// 6: Segment
		grammarRuleVectors[6] = new GrammarRuleVector(0x10000000L);
		// 7: ReferredSegment|Segment
		grammarRuleVectors[7] = new GrammarRuleVector(0x11000000L);
		// 8: SegmentDeclaration
		grammarRuleVectors[8] = new GrammarRuleVector(0x20000000L);
		// 9: SubIdiom
		grammarRuleVectors[9] = new GrammarRuleVector(0x200000000L);
		// 10: CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment
		grammarRuleVectors[10] = new GrammarRuleVector(0x1f5d07e0090L);
		// 11: CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment
		grammarRuleVectors[11] = new GrammarRuleVector(0x1f5d17e0090L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// 0: assert (|AssignmentLocator::eStructuralFeature| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(39);
		// 1: assert (|CustomSegment::supportClassName| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(40);
		// 2: assert (|EPackageImport::ePackage| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(41);
		// 3: assert (|Idiom::name| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(42);
		// 4: assert (|Idiom::ownedSubIdioms| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(43);
		// 5: assert (|IdiomsImport::idiomsModel| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(44);
		// 6: assert (|KeywordLocator::string| - 1) == 0
		serializationMatchSteps[6] = createMatchStep_Assert(46);
		// 7: assert (|LocatorDeclaration::name| - 1) == 0
		serializationMatchSteps[7] = createMatchStep_Assert(47);
		// 8: assert (|LocatorDeclaration::ownedLocator| - 1) == 0
		serializationMatchSteps[8] = createMatchStep_Assert(48);
		// 9: assert (|ReferredLocator::locatorDeclaration| - 1) == 0
		serializationMatchSteps[9] = createMatchStep_Assert(49);
		// 10: assert (|ReferredSegment::segmentDeclaration| - 1) == 0
		serializationMatchSteps[10] = createMatchStep_Assert(50);
		// 11: assert (|ReturnsLocator::eClass| - 1) == 0
		serializationMatchSteps[11] = createMatchStep_Assert(51);
		// 12: assert (|SegmentDeclaration::name| - 1) == 0
		serializationMatchSteps[12] = createMatchStep_Assert(52);
		// 13: assert (|SegmentDeclaration::ownedSegment| - 1) == 0
		serializationMatchSteps[13] = createMatchStep_Assert(53);
		// 14: assert (|StringSegment::printable.'printable'| - 1) == 0
		serializationMatchSteps[14] = createMatchStep_Assert(54);
		// 15: assert (|StringSegment::string| - 1) == 0
		serializationMatchSteps[15] = createMatchStep_Assert(55);
		// 16: assert (|SubIdiom::all.'all'| - 1) == 0
		serializationMatchSteps[16] = createMatchStep_Assert(56);
		// 17: assert (|SubIdiom::ownedLocator| - 1) == 0
		serializationMatchSteps[17] = createMatchStep_Assert(57);
		// 18: assert |SubIdiom::all| == 0
		serializationMatchSteps[18] = createMatchStep_Assert(36);
		// 19: assign V0 = (|IdiomsModel::names| - 1)
		serializationMatchSteps[19] = createMatchStep_Assign(0, 45);
		// 20: assign V0 = (|SubIdiom::ownedSegments| > 0)
		serializationMatchSteps[20] = createMatchStep_Assign(0, 58);
		// 21: assign V0 = |AssignmentLocator::eClass|
		serializationMatchSteps[21] = createMatchStep_Assign(0, 2);
		// 22: assign V0 = |EPackageImport::as|
		serializationMatchSteps[22] = createMatchStep_Assign(0, 6);
		// 23: assign V0 = |Idiom::mixin.'mixin'|
		serializationMatchSteps[23] = createMatchStep_Assign(0, 11);
		// 24: assign V0 = |IdiomsImport::as|
		serializationMatchSteps[24] = createMatchStep_Assign(0, 14);
		// 25: assign V0 = |ReferredLocator::idiomsModel|
		serializationMatchSteps[25] = createMatchStep_Assign(0, 25);
		// 26: assign V0 = |ReferredSegment::idiomsModel|
		serializationMatchSteps[26] = createMatchStep_Assign(0, 27);
		// 27: assign V0 = |ReturnsLocator::ePackage|
		serializationMatchSteps[27] = createMatchStep_Assign(0, 30);
		// 28: assign V1 = |AssignmentLocator::ePackage|
		serializationMatchSteps[28] = createMatchStep_Assign(1, 3);
		// 29: assign V1 = |Idiom::forEClass|
		serializationMatchSteps[29] = createMatchStep_Assign(1, 8);
		// 30: assign V1 = |IdiomsModel::ownedWiths|
		serializationMatchSteps[30] = createMatchStep_Assign(1, 21);
		// 31: assign V1 = |SubIdiom::ownedSegments|
		serializationMatchSteps[31] = createMatchStep_Assign(1, 38);
		// 32: assign V2 = |Idiom::forEPackage|
		serializationMatchSteps[32] = createMatchStep_Assign(2, 9);
		// 33: assign V2 = |IdiomsModel::ownedImports|
		serializationMatchSteps[33] = createMatchStep_Assign(2, 18);
		// 34: assign V3 = |Idiom::inRuleRegex|
		serializationMatchSteps[34] = createMatchStep_Assign(3, 10);
		// 35: assign V3 = |IdiomsModel::ownedLocatorDeclarations|
		serializationMatchSteps[35] = createMatchStep_Assign(3, 19);
		// 36: assign V4 = |Idiom::ownedSubIdioms|
		serializationMatchSteps[36] = createMatchStep_Assign(4, 13);
		// 37: assign V4 = |IdiomsModel::ownedSegmentDeclarations|
		serializationMatchSteps[37] = createMatchStep_Assign(4, 20);
		// 38: assign V5 = |IdiomsModel::ownedIdioms|
		serializationMatchSteps[38] = createMatchStep_Assign(5, 17);
		// 39: check-rule idioms::Idiom.ownedSubIdioms : 33
		serializationMatchSteps[39] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS, 9/*SubIdiom*/);
		// 40: check-rule idioms::IdiomsModel.ownedIdioms : 10
		serializationMatchSteps[40] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS, 1/*Idiom*/);
		// 41: check-rule idioms::IdiomsModel.ownedImports : 5
		serializationMatchSteps[41] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORTS, 0/*EPackageImport*/);
		// 42: check-rule idioms::IdiomsModel.ownedLocatorDeclarations : 15
		serializationMatchSteps[42] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS, 4/*LocatorDeclaration*/);
		// 43: check-rule idioms::IdiomsModel.ownedSegmentDeclarations : 29
		serializationMatchSteps[43] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS, 8/*SegmentDeclaration*/);
		// 44: check-rule idioms::IdiomsModel.ownedWiths : 11
		serializationMatchSteps[44] = createMatchStep_RuleCheck(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS, 2/*IdiomsImport*/);
		// 45: check-rule idioms::LocatorDeclaration.ownedLocator : 1|2|3|6|13|14|23|25
		serializationMatchSteps[45] = createMatchStep_RuleCheck(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR, 5/*AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator*/);
		// 46: check-rule idioms::SegmentDeclaration.ownedSegment : 4|7|17|18|19|20|21|22|28|30|31|32|34|36|37|38|39|40
		serializationMatchSteps[46] = createMatchStep_RuleCheck(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT, 10/*CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment*/);
		// 47: check-rule idioms::SubIdiom.ownedLocator : 1|2|3|6|13|14|23|25
		serializationMatchSteps[47] = createMatchStep_RuleCheck(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR, 5/*AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator*/);
		// 48: check-rule idioms::SubIdiom.ownedSegments : 4|7|17|18|19|20|21|22|24|28|30|31|32|34|36|37|38|39|40
		serializationMatchSteps[48] = createMatchStep_RuleCheck(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS, 11/*CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment*/);
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
		// 6: |EPackageImport::as|
		serializationMatchTerms[6] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.EPACKAGE_IMPORT__AS);
		// 7: |EPackageImport::ePackage|
		serializationMatchTerms[7] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.EPACKAGE_IMPORT__EPACKAGE);
		// 8: |Idiom::forEClass|
		serializationMatchTerms[8] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__FOR_ECLASS);
		// 9: |Idiom::forEPackage|
		serializationMatchTerms[9] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE);
		// 10: |Idiom::inRuleRegex|
		serializationMatchTerms[10] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__IN_RULE_REGEX);
		// 11: |Idiom::mixin.'mixin'|
		serializationMatchTerms[11] = createSerializationMatchTermEAttributeSize(IdiomsPackage.Literals.IDIOM__MIXIN, 1 /* 'mixin' */);
		// 12: |Idiom::name|
		serializationMatchTerms[12] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__NAME);
		// 13: |Idiom::ownedSubIdioms|
		serializationMatchTerms[13] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS);
		// 14: |IdiomsImport::as|
		serializationMatchTerms[14] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_IMPORT__AS);
		// 15: |IdiomsImport::idiomsModel|
		serializationMatchTerms[15] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL);
		// 16: |IdiomsModel::names|
		serializationMatchTerms[16] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__NAMES);
		// 17: |IdiomsModel::ownedIdioms|
		serializationMatchTerms[17] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS);
		// 18: |IdiomsModel::ownedImports|
		serializationMatchTerms[18] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORTS);
		// 19: |IdiomsModel::ownedLocatorDeclarations|
		serializationMatchTerms[19] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS);
		// 20: |IdiomsModel::ownedSegmentDeclarations|
		serializationMatchTerms[20] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS);
		// 21: |IdiomsModel::ownedWiths|
		serializationMatchTerms[21] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS);
		// 22: |KeywordLocator::string|
		serializationMatchTerms[22] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.KEYWORD_LOCATOR__STRING);
		// 23: |LocatorDeclaration::name|
		serializationMatchTerms[23] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.LOCATOR_DECLARATION__NAME);
		// 24: |LocatorDeclaration::ownedLocator|
		serializationMatchTerms[24] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR);
		// 25: |ReferredLocator::idiomsModel|
		serializationMatchTerms[25] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.REFERRED_LOCATOR__IDIOMS_MODEL);
		// 26: |ReferredLocator::locatorDeclaration|
		serializationMatchTerms[26] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION);
		// 27: |ReferredSegment::idiomsModel|
		serializationMatchTerms[27] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.REFERRED_SEGMENT__IDIOMS_MODEL);
		// 28: |ReferredSegment::segmentDeclaration|
		serializationMatchTerms[28] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION);
		// 29: |ReturnsLocator::eClass|
		serializationMatchTerms[29] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS);
		// 30: |ReturnsLocator::ePackage|
		serializationMatchTerms[30] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.RETURNS_LOCATOR__EPACKAGE);
		// 31: |SegmentDeclaration::name|
		serializationMatchTerms[31] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SEGMENT_DECLARATION__NAME);
		// 32: |SegmentDeclaration::ownedSegment|
		serializationMatchTerms[32] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT);
		// 33: |StringSegment::printable.'printable'|
		serializationMatchTerms[33] = createSerializationMatchTermEAttributeSize(IdiomsPackage.Literals.STRING_SEGMENT__PRINTABLE, 2 /* 'printable' */);
		// 34: |StringSegment::string|
		serializationMatchTerms[34] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.STRING_SEGMENT__STRING);
		// 35: |SubIdiom::all.'all'|
		serializationMatchTerms[35] = createSerializationMatchTermEAttributeSize(IdiomsPackage.Literals.SUB_IDIOM__ALL, 0 /* 'all' */);
		// 36: |SubIdiom::all|
		serializationMatchTerms[36] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SUB_IDIOM__ALL);
		// 37: |SubIdiom::ownedLocator|
		serializationMatchTerms[37] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR);
		// 38: |SubIdiom::ownedSegments|
		serializationMatchTerms[38] = createSerializationMatchTermEStructuralFeatureSize(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS);
		// 39: (|AssignmentLocator::eStructuralFeature| - 1)
		serializationMatchTerms[39] = createSerializationMatchTermSubtract(4, 1);
		// 40: (|CustomSegment::supportClassName| - 1)
		serializationMatchTerms[40] = createSerializationMatchTermSubtract(5, 1);
		// 41: (|EPackageImport::ePackage| - 1)
		serializationMatchTerms[41] = createSerializationMatchTermSubtract(7, 1);
		// 42: (|Idiom::name| - 1)
		serializationMatchTerms[42] = createSerializationMatchTermSubtract(12, 1);
		// 43: (|Idiom::ownedSubIdioms| - 1)
		serializationMatchTerms[43] = createSerializationMatchTermSubtract(13, 1);
		// 44: (|IdiomsImport::idiomsModel| - 1)
		serializationMatchTerms[44] = createSerializationMatchTermSubtract(15, 1);
		// 45: (|IdiomsModel::names| - 1)
		serializationMatchTerms[45] = createSerializationMatchTermSubtract(16, 1);
		// 46: (|KeywordLocator::string| - 1)
		serializationMatchTerms[46] = createSerializationMatchTermSubtract(22, 1);
		// 47: (|LocatorDeclaration::name| - 1)
		serializationMatchTerms[47] = createSerializationMatchTermSubtract(23, 1);
		// 48: (|LocatorDeclaration::ownedLocator| - 1)
		serializationMatchTerms[48] = createSerializationMatchTermSubtract(24, 1);
		// 49: (|ReferredLocator::locatorDeclaration| - 1)
		serializationMatchTerms[49] = createSerializationMatchTermSubtract(26, 1);
		// 50: (|ReferredSegment::segmentDeclaration| - 1)
		serializationMatchTerms[50] = createSerializationMatchTermSubtract(28, 1);
		// 51: (|ReturnsLocator::eClass| - 1)
		serializationMatchTerms[51] = createSerializationMatchTermSubtract(29, 1);
		// 52: (|SegmentDeclaration::name| - 1)
		serializationMatchTerms[52] = createSerializationMatchTermSubtract(31, 1);
		// 53: (|SegmentDeclaration::ownedSegment| - 1)
		serializationMatchTerms[53] = createSerializationMatchTermSubtract(32, 1);
		// 54: (|StringSegment::printable.'printable'| - 1)
		serializationMatchTerms[54] = createSerializationMatchTermSubtract(33, 1);
		// 55: (|StringSegment::string| - 1)
		serializationMatchTerms[55] = createSerializationMatchTermSubtract(34, 1);
		// 56: (|SubIdiom::all.'all'| - 1)
		serializationMatchTerms[56] = createSerializationMatchTermSubtract(35, 1);
		// 57: (|SubIdiom::ownedLocator| - 1)
		serializationMatchTerms[57] = createSerializationMatchTermSubtract(37, 1);
		// 58: (|SubIdiom::ownedSegments| > 0)
		serializationMatchTerms[58] = createSerializationMatchTermGreaterThan(38, 0);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules() {
		// Idioms::AnyAssignmentLocator-0(idioms::AnyAssignmentLocator): "any-assignment"
		serializationRules[0] = createSerializationRule("AnyAssignmentLocator-0", 1,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				39		/* 'any-assignment' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::AnyElementLocator-0(idioms::AnyElementLocator): "any-element"
		serializationRules[1] = createSerializationRule("AnyElementLocator-0", 2,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				40		/* 'any-element' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::AssignmentLocator-0(idioms::AssignmentLocator): { "assignment" { { ePackage=ID "::" }[?] eClass=ID "::" }[?] eStructuralFeature=ID }
		serializationRules[2] = createSerializationRule("AssignmentLocator-0", 3,
			createSerializationMatchSteps(
				0		/* assert (|AssignmentLocator::eStructuralFeature| - 1) == 0 */,
				21		/* assign V0 = |AssignmentLocator::eClass| */,
				28		/* assign V1 = |AssignmentLocator::ePackage| */
			),
			createSerializationSteps(
				42		/* 'assignment' || pre-comment soft-space value soft-space post-comment */,
				79		/* V00*5-steps || value */,
				81		/* V01*2-steps || value */,
				5		/* AssignmentLocator::ePackage=ID || pre-comment soft-space value soft-space post-comment */,
				37		/* '::' || pre-comment no-space value no-space post-comment */,
				3		/* AssignmentLocator::eClass=ID || pre-comment soft-space value soft-space post-comment */,
				37		/* '::' || pre-comment no-space value no-space post-comment */,
				8		/* AssignmentLocator::eStructuralFeature=ID || pre-comment soft-space value soft-space post-comment */
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
				44		/* 'custom' || pre-comment soft-space value soft-space post-comment */,
				35		/* CustomSegment::supportClassName=STRING || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.CUSTOM_SEGMENT__SUPPORT_CLASS_NAME, true, GrammarCardinality.ONE)
			});
		// Idioms::EPackageImport-0(idioms::EPackageImport): { "import" ePackage=STRING { "as" as=ID }[?] }
		serializationRules[4] = createSerializationRule("EPackageImport-0", 5,
			createSerializationMatchSteps(
				22		/* assign V0 = |EPackageImport::as| */,
				2		/* assert (|EPackageImport::ePackage| - 1) == 0 */
			),
			createSerializationSteps(
				51		/* 'import' || pre-comment soft-space value soft-space post-comment */,
				6		/* EPackageImport::ePackage=STRING || pre-comment soft-space value soft-space post-comment */,
				76		/* V00*2-steps || value */,
				41		/* 'as' || pre-comment soft-space value soft-space post-comment */,
				1		/* EPackageImport::as=ID || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.EPACKAGE_IMPORT__AS, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(IdiomsPackage.Literals.EPACKAGE_IMPORT__EPACKAGE, -1
				)
			});
		// Idioms::FinalLocator-0(idioms::FinalLocator): "final"
		serializationRules[5] = createSerializationRule("FinalLocator-0", 6,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				47		/* 'final' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::HalfNewLineSegment-0(idioms::HalfNewLineSegment): "half-new-line"
		serializationRules[6] = createSerializationRule("HalfNewLineSegment-0", 7,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				49		/* 'half-new-line' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::Idiom-0(idioms::Idiom): { mixin?="mixin"[?] "idiom" name=ID { "for" { forEPackage=ID "::" }[?] forEClass=ID }[?] { "in" inRuleRegex=STRING }[?] { "{" ownedSubIdioms+=SubIdiom[*] "}" } }
		serializationRules[7] = createSerializationRule("Idiom-0", 10,
			createSerializationMatchSteps(
				39		/* check-rule idioms::Idiom.ownedSubIdioms : SubIdiom */,
				36		/* assign V4 = |Idiom::ownedSubIdioms| */,
				34		/* assign V3 = |Idiom::inRuleRegex| */,
				29		/* assign V1 = |Idiom::forEClass| */,
				32		/* assign V2 = |Idiom::forEPackage| */,
				3		/* assert (|Idiom::name| - 1) == 0 */,
				23		/* assign V0 = |Idiom::mixin.'mixin'| */
			),
			createSerializationSteps(
				75		/* V00*1-steps || value */,
				16		/* Idiom::mixin?='mixin' || pre-comment soft-space value soft-space post-comment */,
				50		/* 'idiom' || pre-comment soft-space value soft-space post-comment */,
				17		/* Idiom::name=ID || pre-comment soft-space value soft-space post-comment */,
				82		/* V01*5-steps || value */,
				48		/* 'for' || pre-comment soft-space value soft-space post-comment */,
				84		/* V02*2-steps || value */,
				10		/* Idiom::forEPackage=ID || pre-comment soft-space value soft-space post-comment */,
				37		/* '::' || pre-comment no-space value no-space post-comment */,
				9		/* Idiom::forEClass=ID || pre-comment soft-space value soft-space post-comment */,
				86		/* V03*2-steps || value */,
				52		/* 'in' || pre-comment soft-space value soft-space post-comment */,
				14		/* Idiom::inRuleRegex=STRING || pre-comment soft-space value soft-space post-comment */,
				73		/* '{' || pre-comment soft-space value push soft-new-line post-comment */,
				87		/* V04*1-steps || value */,
				29		/* Idiom::ownedSubIdioms+=SubIdiom || value soft-new-line */,
				74		/* '}' || pre-comment pop soft-space value soft-new-line post-comment */
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
				createSerializationReference(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS, 9/* SubIdiom */,
					(33/*SubIdiom*/ << 4) | 2 /*[*]*/
				)
			});
		// Idioms::Idiom-1(idioms::Idiom): { mixin?="mixin"[?] "idiom" name=ID { "for" { forEPackage=ID "::" }[?] forEClass=ID }[?] { "in" inRuleRegex=STRING }[?] ownedSubIdioms+=SubIdiom }
		serializationRules[8] = createSerializationRule("Idiom-1", 10,
			createSerializationMatchSteps(
				39		/* check-rule idioms::Idiom.ownedSubIdioms : SubIdiom */,
				4		/* assert (|Idiom::ownedSubIdioms| - 1) == 0 */,
				34		/* assign V3 = |Idiom::inRuleRegex| */,
				29		/* assign V1 = |Idiom::forEClass| */,
				32		/* assign V2 = |Idiom::forEPackage| */,
				3		/* assert (|Idiom::name| - 1) == 0 */,
				23		/* assign V0 = |Idiom::mixin.'mixin'| */
			),
			createSerializationSteps(
				75		/* V00*1-steps || value */,
				16		/* Idiom::mixin?='mixin' || pre-comment soft-space value soft-space post-comment */,
				50		/* 'idiom' || pre-comment soft-space value soft-space post-comment */,
				17		/* Idiom::name=ID || pre-comment soft-space value soft-space post-comment */,
				82		/* V01*5-steps || value */,
				48		/* 'for' || pre-comment soft-space value soft-space post-comment */,
				84		/* V02*2-steps || value */,
				10		/* Idiom::forEPackage=ID || pre-comment soft-space value soft-space post-comment */,
				37		/* '::' || pre-comment no-space value no-space post-comment */,
				9		/* Idiom::forEClass=ID || pre-comment soft-space value soft-space post-comment */,
				86		/* V03*2-steps || value */,
				52		/* 'in' || pre-comment soft-space value soft-space post-comment */,
				14		/* Idiom::inRuleRegex=STRING || pre-comment soft-space value soft-space post-comment */,
				29		/* Idiom::ownedSubIdioms+=SubIdiom || value soft-new-line */
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
				createSerializationReference(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS, 9/* SubIdiom */,
					(33/*SubIdiom*/ << 4) | 0 /*[1]*/
				)
			});
		// Idioms::IdiomsImport-0(idioms::IdiomsImport): { "with" idiomsModel=STRING { "as" as=ID }[?] }
		serializationRules[9] = createSerializationRule("IdiomsImport-0", 11,
			createSerializationMatchSteps(
				24		/* assign V0 = |IdiomsImport::as| */,
				5		/* assert (|IdiomsImport::idiomsModel| - 1) == 0 */
			),
			createSerializationSteps(
				67		/* 'with' || pre-comment soft-space value soft-space post-comment */,
				11		/* IdiomsImport::idiomsModel=STRING || pre-comment soft-space value soft-space post-comment */,
				76		/* V00*2-steps || value */,
				41		/* 'as' || pre-comment soft-space value soft-space post-comment */,
				2		/* IdiomsImport::as=ID || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.IDIOMS_IMPORT__AS, false, GrammarCardinality.ZERO_OR_ONE),
				createSerializationReference(IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL, -1
				)
			});
		// Idioms::IdiomsModel-0(idioms::IdiomsModel): { "model" names+=ID { "." names+=ID }[*] ownedWiths+=IdiomsImport[*] ownedImports+=EPackageImport[*] { ownedLocatorDeclarations+=LocatorDeclaration[*] ownedSegmentDeclarations+=SegmentDeclaration[*] ownedIdioms+=Idiom[*] } }
		serializationRules[10] = createSerializationRule("IdiomsModel-0", 12,
			createSerializationMatchSteps(
				40		/* check-rule idioms::IdiomsModel.ownedIdioms : Idiom */,
				41		/* check-rule idioms::IdiomsModel.ownedImports : EPackageImport */,
				42		/* check-rule idioms::IdiomsModel.ownedLocatorDeclarations : LocatorDeclaration */,
				43		/* check-rule idioms::IdiomsModel.ownedSegmentDeclarations : SegmentDeclaration */,
				44		/* check-rule idioms::IdiomsModel.ownedWiths : IdiomsImport */,
				38		/* assign V5 = |IdiomsModel::ownedIdioms| */,
				37		/* assign V4 = |IdiomsModel::ownedSegmentDeclarations| */,
				35		/* assign V3 = |IdiomsModel::ownedLocatorDeclarations| */,
				33		/* assign V2 = |IdiomsModel::ownedImports| */,
				30		/* assign V1 = |IdiomsModel::ownedWiths| */,
				19		/* assign V0 = (|IdiomsModel::names| - 1) */
			),
			createSerializationSteps(
				54		/* 'model' || pre-comment soft-space value soft-space post-comment */,
				20		/* IdiomsModel::names+=ID || pre-comment soft-space value soft-space post-comment */,
				77		/* V00*2-steps || value */,
				36		/* '.' || pre-comment no-space value no-space post-comment */,
				20		/* IdiomsModel::names+=ID || pre-comment soft-space value soft-space post-comment */,
				80		/* V01*1-steps || value */,
				30		/* IdiomsModel::ownedWiths+=IdiomsImport || soft-new-line value soft-new-line */,
				83		/* V02*1-steps || value */,
				22		/* IdiomsModel::ownedImports+=EPackageImport || soft-new-line value soft-new-line */,
				85		/* V03*1-steps || new-line soft-new-line value soft-new-line */,
				25		/* IdiomsModel::ownedLocatorDeclarations+=LocatorDeclaration || value */,
				88		/* V04*1-steps || new-line soft-new-line value soft-new-line */,
				27		/* IdiomsModel::ownedSegmentDeclarations+=SegmentDeclaration || value */,
				89		/* V05*1-steps || value */,
				21		/* IdiomsModel::ownedIdioms+=Idiom || new-line soft-new-line value soft-new-line */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.IDIOMS_MODEL__NAMES, false, GrammarCardinality.ONE_OR_MORE),
				createSerializationReference(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS, 1/* Idiom */,
					(10/*Idiom*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORTS, 0/* EPackageImport */,
					(5/*EPackageImport*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS, 4/* LocatorDeclaration */,
					(15/*LocatorDeclaration*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS, 8/* SegmentDeclaration */,
					(29/*SegmentDeclaration*/ << 4) | 2 /*[*]*/
				),
				createSerializationReference(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS, 2/* IdiomsImport */,
					(11/*IdiomsImport*/ << 4) | 2 /*[*]*/
				)
			});
		// Idioms::KeywordLocator-0(idioms::KeywordLocator): string=STRING
		serializationRules[11] = createSerializationRule("KeywordLocator-0", 13,
			createSerializationMatchSteps(
				6		/* assert (|KeywordLocator::string| - 1) == 0 */
			),
			createSerializationSteps(
				33		/* KeywordLocator::string=STRING || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.KEYWORD_LOCATOR__STRING, true, GrammarCardinality.ONE)
			});
		// Idioms::LocatorDeclaration-0(idioms::LocatorDeclaration): { "locator" name=ID ownedLocator=Locator ";" }
		serializationRules[12] = createSerializationRule("LocatorDeclaration-0", 15,
			createSerializationMatchSteps(
				45		/* check-rule idioms::LocatorDeclaration.ownedLocator : AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator */,
				8		/* assert (|LocatorDeclaration::ownedLocator| - 1) == 0 */,
				7		/* assert (|LocatorDeclaration::name| - 1) == 0 */
			),
			createSerializationSteps(
				53		/* 'locator' || pre-comment soft-space value soft-space post-comment */,
				18		/* LocatorDeclaration::name=ID || pre-comment soft-space value soft-space post-comment */,
				23		/* LocatorDeclaration::ownedLocator=Locator || value */,
				38		/* ';' || pre-comment no-space value soft-new-line post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.LOCATOR_DECLARATION__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR, 3/* Locator */,
					(14/*Locator*/ << 4) | 0 /*[1]*/
				)
			});
		// Idioms::NewLineSegment-0(idioms::NewLineSegment): "new-line"
		serializationRules[13] = createSerializationRule("NewLineSegment-0", 17,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				55		/* 'new-line' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::NoSpaceSegment-0(idioms::NoSpaceSegment): "no-space"
		serializationRules[14] = createSerializationRule("NoSpaceSegment-0", 18,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				56		/* 'no-space' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::PopSegment-0(idioms::PopSegment): "pop"
		serializationRules[15] = createSerializationRule("PopSegment-0", 19,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				57		/* 'pop' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::PostCommentSegment-0(idioms::PostCommentSegment): "post-comment"
		serializationRules[16] = createSerializationRule("PostCommentSegment-0", 20,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				58		/* 'post-comment' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::PreCommentSegment-0(idioms::PreCommentSegment): "pre-comment"
		serializationRules[17] = createSerializationRule("PreCommentSegment-0", 21,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				59		/* 'pre-comment' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::PushSegment-0(idioms::PushSegment): "push"
		serializationRules[18] = createSerializationRule("PushSegment-0", 22,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				60		/* 'push' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::ReferredLocator-0(idioms::ReferredLocator): { { idiomsModel=ID "::" }[?] locatorDeclaration=ID }
		serializationRules[19] = createSerializationRule("ReferredLocator-0", 23,
			createSerializationMatchSteps(
				9		/* assert (|ReferredLocator::locatorDeclaration| - 1) == 0 */,
				25		/* assign V0 = |ReferredLocator::idiomsModel| */
			),
			createSerializationSteps(
				76		/* V00*2-steps || value */,
				12		/* ReferredLocator::idiomsModel=ID || pre-comment soft-space value soft-space post-comment */,
				37		/* '::' || pre-comment no-space value no-space post-comment */,
				15		/* ReferredLocator::locatorDeclaration=ID || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(IdiomsPackage.Literals.REFERRED_LOCATOR__IDIOMS_MODEL, -1
				),
				createSerializationReference(IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION, -1
				)
			});
		// Idioms::ReferredSegment-0(idioms::ReferredSegment): { { idiomsModel=ID "::" }[?] segmentDeclaration=ID }
		serializationRules[20] = createSerializationRule("ReferredSegment-0", 24,
			createSerializationMatchSteps(
				10		/* assert (|ReferredSegment::segmentDeclaration| - 1) == 0 */,
				26		/* assign V0 = |ReferredSegment::idiomsModel| */
			),
			createSerializationSteps(
				76		/* V00*2-steps || value */,
				13		/* ReferredSegment::idiomsModel=ID || pre-comment soft-space value soft-space post-comment */,
				37		/* '::' || pre-comment no-space value no-space post-comment */,
				32		/* ReferredSegment::segmentDeclaration=ID || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(IdiomsPackage.Literals.REFERRED_SEGMENT__IDIOMS_MODEL, -1
				),
				createSerializationReference(IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION, -1
				)
			});
		// Idioms::ReturnsLocator-0(idioms::ReturnsLocator): { "returns" { ePackage=ID "::" }[?] eClass=ID }
		serializationRules[21] = createSerializationRule("ReturnsLocator-0", 25,
			createSerializationMatchSteps(
				11		/* assert (|ReturnsLocator::eClass| - 1) == 0 */,
				27		/* assign V0 = |ReturnsLocator::ePackage| */
			),
			createSerializationSteps(
				61		/* 'returns' || pre-comment soft-space value soft-space post-comment */,
				76		/* V00*2-steps || value */,
				7		/* ReturnsLocator::ePackage=ID || pre-comment soft-space value soft-space post-comment */,
				37		/* '::' || pre-comment no-space value no-space post-comment */,
				4		/* ReturnsLocator::eClass=ID || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS, -1
				),
				createSerializationReference(IdiomsPackage.Literals.RETURNS_LOCATOR__EPACKAGE, -1
				)
			});
		// Idioms::SegmentDeclaration-0(idioms::SegmentDeclaration): { "segment" name=ID ownedSegment=Segment ";" }
		serializationRules[22] = createSerializationRule("SegmentDeclaration-0", 29,
			createSerializationMatchSteps(
				46		/* check-rule idioms::SegmentDeclaration.ownedSegment : CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */,
				13		/* assert (|SegmentDeclaration::ownedSegment| - 1) == 0 */,
				12		/* assert (|SegmentDeclaration::name| - 1) == 0 */
			),
			createSerializationSteps(
				62		/* 'segment' || pre-comment soft-space value soft-space post-comment */,
				19		/* SegmentDeclaration::name=ID || pre-comment soft-space value soft-space post-comment */,
				26		/* SegmentDeclaration::ownedSegment=Segment || value */,
				38		/* ';' || pre-comment no-space value soft-new-line post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(IdiomsPackage.Literals.SEGMENT_DECLARATION__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT, 6/* Segment */,
					(28/*Segment*/ << 4) | 0 /*[1]*/
				)
			});
		// Idioms::SoftNewLineSegment-0(idioms::SoftNewLineSegment): "soft-new-line"
		serializationRules[23] = createSerializationRule("SoftNewLineSegment-0", 30,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				63		/* 'soft-new-line' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::SoftSpaceSegment-0(idioms::SoftSpaceSegment): "soft-space"
		serializationRules[24] = createSerializationRule("SoftSpaceSegment-0", 31,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				64		/* 'soft-space' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::StringSegment-0(idioms::StringSegment): { "string" string=STRING printable?="printable" }
		serializationRules[25] = createSerializationRule("StringSegment-0", 32,
			createSerializationMatchSteps(
				14		/* assert (|StringSegment::printable.'printable'| - 1) == 0 */,
				15		/* assert (|StringSegment::string| - 1) == 0 */
			),
			createSerializationSteps(
				65		/* 'string' || pre-comment soft-space value soft-space post-comment */,
				34		/* StringSegment::string=STRING || pre-comment soft-space value soft-space post-comment */,
				31		/* StringSegment::printable?='printable' || pre-comment soft-space value soft-space post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(IdiomsPackage.Literals.STRING_SEGMENT__PRINTABLE, false,
					(2/*'printable'*/ << 4) | 0 /*[1]*/
				),
				createSerializationSimpleAttribute(IdiomsPackage.Literals.STRING_SEGMENT__STRING, true, GrammarCardinality.ONE)
			});
		// Idioms::SubIdiom-0(idioms::SubIdiom): { "at" "each" ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" }
		serializationRules[26] = createSerializationRule("SubIdiom-0", 33,
			createSerializationMatchSteps(
				18		/* assert |SubIdiom::all| == 0 */,
				47		/* check-rule idioms::SubIdiom.ownedLocator : AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator */,
				48		/* check-rule idioms::SubIdiom.ownedSegments : CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */,
				17		/* assert (|SubIdiom::ownedLocator| - 1) == 0 */,
				20		/* assign V0 = (|SubIdiom::ownedSegments| > 0) */,
				31		/* assign V1 = |SubIdiom::ownedSegments| */
			),
			createSerializationSteps(
				43		/* 'at' || pre-comment soft-space value soft-space post-comment */,
				46		/* 'each' || pre-comment soft-space value soft-space post-comment */,
				24		/* SubIdiom::ownedLocator=Locator || value */,
				78		/* V00*3-steps || value */,
				45		/* 'do' || pre-comment soft-space value soft-space post-comment */,
				80		/* V01*1-steps || value */,
				28		/* SubIdiom::ownedSegments+=Segment|ReferredSegment || value */,
				38		/* ';' || pre-comment no-space value soft-new-line post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR, 3/* Locator */,
					(14/*Locator*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS, 7/* ReferredSegment,Segment */,
					(24/*ReferredSegment*/ << 4) | 2 /*[*]*/,
					(28/*Segment*/ << 4) | 2 /*[*]*/
				)
			});
		// Idioms::SubIdiom-1(idioms::SubIdiom): { "at" all?="all" ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" }
		serializationRules[27] = createSerializationRule("SubIdiom-1", 33,
			createSerializationMatchSteps(
				47		/* check-rule idioms::SubIdiom.ownedLocator : AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator */,
				48		/* check-rule idioms::SubIdiom.ownedSegments : CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */,
				17		/* assert (|SubIdiom::ownedLocator| - 1) == 0 */,
				16		/* assert (|SubIdiom::all.'all'| - 1) == 0 */,
				20		/* assign V0 = (|SubIdiom::ownedSegments| > 0) */,
				31		/* assign V1 = |SubIdiom::ownedSegments| */
			),
			createSerializationSteps(
				43		/* 'at' || pre-comment soft-space value soft-space post-comment */,
				0		/* SubIdiom::all?='all' || pre-comment soft-space value soft-space post-comment */,
				24		/* SubIdiom::ownedLocator=Locator || value */,
				78		/* V00*3-steps || value */,
				45		/* 'do' || pre-comment soft-space value soft-space post-comment */,
				80		/* V01*1-steps || value */,
				28		/* SubIdiom::ownedSegments+=Segment|ReferredSegment || value */,
				38		/* ';' || pre-comment no-space value soft-new-line post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(IdiomsPackage.Literals.SUB_IDIOM__ALL, false,
					(0/*'all'*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR, 3/* Locator */,
					(14/*Locator*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS, 7/* ReferredSegment,Segment */,
					(24/*ReferredSegment*/ << 4) | 2 /*[*]*/,
					(28/*Segment*/ << 4) | 2 /*[*]*/
				)
			});
		// Idioms::SubIdiom-2(idioms::SubIdiom): { "at" ownedLocator=Locator { "do" ownedSegments+=(Segment|ReferredSegment)[*] }[?] ";" }
		serializationRules[28] = createSerializationRule("SubIdiom-2", 33,
			createSerializationMatchSteps(
				18		/* assert |SubIdiom::all| == 0 */,
				47		/* check-rule idioms::SubIdiom.ownedLocator : AnyAssignmentLocator|AnyElementLocator|AssignmentLocator|FinalLocator|KeywordLocator|Locator|ReferredLocator|ReturnsLocator */,
				48		/* check-rule idioms::SubIdiom.ownedSegments : CustomSegment|HalfNewLineSegment|NewLineSegment|NoSpaceSegment|PopSegment|PostCommentSegment|PreCommentSegment|PushSegment|ReferredSegment|Segment|SoftNewLineSegment|SoftSpaceSegment|StringSegment|ValueSegment|WrapAnchorSegment|WrapBeginAllSegment|WrapBeginSomeSegment|WrapEndSegment|WrapHereSegment */,
				17		/* assert (|SubIdiom::ownedLocator| - 1) == 0 */,
				20		/* assign V0 = (|SubIdiom::ownedSegments| > 0) */,
				31		/* assign V1 = |SubIdiom::ownedSegments| */
			),
			createSerializationSteps(
				43		/* 'at' || pre-comment soft-space value soft-space post-comment */,
				24		/* SubIdiom::ownedLocator=Locator || value */,
				78		/* V00*3-steps || value */,
				45		/* 'do' || pre-comment soft-space value soft-space post-comment */,
				80		/* V01*1-steps || value */,
				28		/* SubIdiom::ownedSegments+=Segment|ReferredSegment || value */,
				38		/* ';' || pre-comment no-space value soft-new-line post-comment */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR, 3/* Locator */,
					(14/*Locator*/ << 4) | 0 /*[1]*/
				),
				createSerializationReference(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS, 7/* ReferredSegment,Segment */,
					(24/*ReferredSegment*/ << 4) | 2 /*[*]*/,
					(28/*Segment*/ << 4) | 2 /*[*]*/
				)
			});
		// Idioms::ValueSegment-0(idioms::ValueSegment): "value"
		serializationRules[29] = createSerializationRule("ValueSegment-0", 34,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				66		/* 'value' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::WrapAnchorSegment-0(idioms::WrapAnchorSegment): "wrap-anchor"
		serializationRules[30] = createSerializationRule("WrapAnchorSegment-0", 36,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				68		/* 'wrap-anchor' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::WrapBeginAllSegment-0(idioms::WrapBeginAllSegment): "wrap-begin-all"
		serializationRules[31] = createSerializationRule("WrapBeginAllSegment-0", 37,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				69		/* 'wrap-begin-all' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::WrapBeginSomeSegment-0(idioms::WrapBeginSomeSegment): "wrap-begin-some"
		serializationRules[32] = createSerializationRule("WrapBeginSomeSegment-0", 38,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				70		/* 'wrap-begin-some' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::WrapEndSegment-0(idioms::WrapEndSegment): "wrap-end"
		serializationRules[33] = createSerializationRule("WrapEndSegment-0", 39,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				71		/* 'wrap-end' || pre-comment soft-space value soft-space post-comment */
			),
			null);
		// Idioms::WrapHereSegment-0(idioms::WrapHereSegment): "wrap-here"
		serializationRules[34] = createSerializationRule("WrapHereSegment-0", 40,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				72		/* 'wrap-here' || pre-comment soft-space value soft-space post-comment */
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
		// 1: EPackageImport::as=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[1] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.EPACKAGE_IMPORT__AS, 8 /*ID*/, 6);
		// 2: IdiomsImport::as=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[2] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_IMPORT__AS, 8 /*ID*/, 6);
		// 3: AssignmentLocator::eClass=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[3] = createSerializationStepCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ECLASS, getCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ECLASS, "ID"), 8, 6);
		// 4: ReturnsLocator::eClass=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[4] = createSerializationStepCrossReference(IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS, getCrossReference(IdiomsPackage.Literals.RETURNS_LOCATOR__ECLASS, "ID"), 8, 6);
		// 5: AssignmentLocator::ePackage=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[5] = createSerializationStepCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__EPACKAGE, getCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__EPACKAGE, "ID"), 8, 6);
		// 6: EPackageImport::ePackage=STRING || pre-comment soft-space value soft-space post-comment
		serializationSteps[6] = createSerializationStepCrossReference(IdiomsPackage.Literals.EPACKAGE_IMPORT__EPACKAGE, getCrossReference(IdiomsPackage.Literals.EPACKAGE_IMPORT__EPACKAGE, "STRING"), 27, 6);
		// 7: ReturnsLocator::ePackage=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[7] = createSerializationStepCrossReference(IdiomsPackage.Literals.RETURNS_LOCATOR__EPACKAGE, getCrossReference(IdiomsPackage.Literals.RETURNS_LOCATOR__EPACKAGE, "ID"), 8, 6);
		// 8: AssignmentLocator::eStructuralFeature=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[8] = createSerializationStepCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE, getCrossReference(IdiomsPackage.Literals.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE, "ID"), 8, 6);
		// 9: Idiom::forEClass=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[9] = createSerializationStepCrossReference(IdiomsPackage.Literals.IDIOM__FOR_ECLASS, getCrossReference(IdiomsPackage.Literals.IDIOM__FOR_ECLASS, "ID"), 8, 6);
		// 10: Idiom::forEPackage=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[10] = createSerializationStepCrossReference(IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE, getCrossReference(IdiomsPackage.Literals.IDIOM__FOR_EPACKAGE, "ID"), 8, 6);
		// 11: IdiomsImport::idiomsModel=STRING || pre-comment soft-space value soft-space post-comment
		serializationSteps[11] = createSerializationStepCrossReference(IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL, getCrossReference(IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL, "STRING"), 27, 6);
		// 12: ReferredLocator::idiomsModel=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[12] = createSerializationStepCrossReference(IdiomsPackage.Literals.REFERRED_LOCATOR__IDIOMS_MODEL, getCrossReference(IdiomsPackage.Literals.REFERRED_LOCATOR__IDIOMS_MODEL, "ID"), 8, 6);
		// 13: ReferredSegment::idiomsModel=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[13] = createSerializationStepCrossReference(IdiomsPackage.Literals.REFERRED_SEGMENT__IDIOMS_MODEL, getCrossReference(IdiomsPackage.Literals.REFERRED_SEGMENT__IDIOMS_MODEL, "ID"), 8, 6);
		// 14: Idiom::inRuleRegex=STRING || pre-comment soft-space value soft-space post-comment
		serializationSteps[14] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOM__IN_RULE_REGEX, 27 /*STRING*/, 6);
		// 15: ReferredLocator::locatorDeclaration=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[15] = createSerializationStepCrossReference(IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION, getCrossReference(IdiomsPackage.Literals.REFERRED_LOCATOR__LOCATOR_DECLARATION, "ID"), 8, 6);
		// 16: Idiom::mixin?='mixin' || pre-comment soft-space value soft-space post-comment
		serializationSteps[16] = createSerializationStepAssignKeyword(IdiomsPackage.Literals.IDIOM__MIXIN, 1 /* 'mixin' */, 6);
		// 17: Idiom::name=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[17] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOM__NAME, 8 /*ID*/, 6);
		// 18: LocatorDeclaration::name=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[18] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.LOCATOR_DECLARATION__NAME, 8 /*ID*/, 6);
		// 19: SegmentDeclaration::name=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[19] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.SEGMENT_DECLARATION__NAME, 8 /*ID*/, 6);
		// 20: IdiomsModel::names+=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[20] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__NAMES, 8 /*ID*/, 6);
		// 21: IdiomsModel::ownedIdioms+=Idiom || new-line soft-new-line value soft-new-line
		serializationSteps[21] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IDIOMS, 10 /*Idiom*/, 3);
		// 22: IdiomsModel::ownedImports+=EPackageImport || soft-new-line value soft-new-line
		serializationSteps[22] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_IMPORTS, 5 /*EPackageImport*/, 2);
		// 23: LocatorDeclaration::ownedLocator=Locator || value
		serializationSteps[23] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.LOCATOR_DECLARATION__OWNED_LOCATOR, 14 /*Locator*/, 0);
		// 24: SubIdiom::ownedLocator=Locator || value
		serializationSteps[24] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.SUB_IDIOM__OWNED_LOCATOR, 14 /*Locator*/, 0);
		// 25: IdiomsModel::ownedLocatorDeclarations+=LocatorDeclaration || value
		serializationSteps[25] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS, 15 /*LocatorDeclaration*/, 0);
		// 26: SegmentDeclaration::ownedSegment=Segment || value
		serializationSteps[26] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.SEGMENT_DECLARATION__OWNED_SEGMENT, 28 /*Segment*/, 0);
		// 27: IdiomsModel::ownedSegmentDeclarations+=SegmentDeclaration || value
		serializationSteps[27] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS, 29 /*SegmentDeclaration*/, 0);
		// 28: SubIdiom::ownedSegments+=Segment|ReferredSegment || value
		serializationSteps[28] = createSerializationStepAssigns(IdiomsPackage.Literals.SUB_IDIOM__OWNED_SEGMENTS, -1, new int[] { 28/*Segment*/,24/*ReferredSegment*/}, 0);
		// 29: Idiom::ownedSubIdioms+=SubIdiom || value soft-new-line
		serializationSteps[29] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOM__OWNED_SUB_IDIOMS, 33 /*SubIdiom*/, 1);
		// 30: IdiomsModel::ownedWiths+=IdiomsImport || soft-new-line value soft-new-line
		serializationSteps[30] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.IDIOMS_MODEL__OWNED_WITHS, 11 /*IdiomsImport*/, 2);
		// 31: StringSegment::printable?='printable' || pre-comment soft-space value soft-space post-comment
		serializationSteps[31] = createSerializationStepAssignKeyword(IdiomsPackage.Literals.STRING_SEGMENT__PRINTABLE, 2 /* 'printable' */, 6);
		// 32: ReferredSegment::segmentDeclaration=ID || pre-comment soft-space value soft-space post-comment
		serializationSteps[32] = createSerializationStepCrossReference(IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION, getCrossReference(IdiomsPackage.Literals.REFERRED_SEGMENT__SEGMENT_DECLARATION, "ID"), 8, 6);
		// 33: KeywordLocator::string=STRING || pre-comment soft-space value soft-space post-comment
		serializationSteps[33] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.KEYWORD_LOCATOR__STRING, 27 /*STRING*/, 6);
		// 34: StringSegment::string=STRING || pre-comment soft-space value soft-space post-comment
		serializationSteps[34] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.STRING_SEGMENT__STRING, 27 /*STRING*/, 6);
		// 35: CustomSegment::supportClassName=STRING || pre-comment soft-space value soft-space post-comment
		serializationSteps[35] = createSerializationStepAssignedRuleCall(IdiomsPackage.Literals.CUSTOM_SEGMENT__SUPPORT_CLASS_NAME, 27 /*STRING*/, 6);
		// 36: '.' || pre-comment no-space value no-space post-comment
		serializationSteps[36] = createSerializationStepKeyword(".", 4);
		// 37: '::' || pre-comment no-space value no-space post-comment
		serializationSteps[37] = createSerializationStepKeyword("::", 4);
		// 38: ';' || pre-comment no-space value soft-new-line post-comment
		serializationSteps[38] = createSerializationStepKeyword(";", 5);
		// 39: 'any-assignment' || pre-comment soft-space value soft-space post-comment
		serializationSteps[39] = createSerializationStepKeyword("any-assignment", 6);
		// 40: 'any-element' || pre-comment soft-space value soft-space post-comment
		serializationSteps[40] = createSerializationStepKeyword("any-element", 6);
		// 41: 'as' || pre-comment soft-space value soft-space post-comment
		serializationSteps[41] = createSerializationStepKeyword("as", 6);
		// 42: 'assignment' || pre-comment soft-space value soft-space post-comment
		serializationSteps[42] = createSerializationStepKeyword("assignment", 6);
		// 43: 'at' || pre-comment soft-space value soft-space post-comment
		serializationSteps[43] = createSerializationStepKeyword("at", 6);
		// 44: 'custom' || pre-comment soft-space value soft-space post-comment
		serializationSteps[44] = createSerializationStepKeyword("custom", 6);
		// 45: 'do' || pre-comment soft-space value soft-space post-comment
		serializationSteps[45] = createSerializationStepKeyword("do", 6);
		// 46: 'each' || pre-comment soft-space value soft-space post-comment
		serializationSteps[46] = createSerializationStepKeyword("each", 6);
		// 47: 'final' || pre-comment soft-space value soft-space post-comment
		serializationSteps[47] = createSerializationStepKeyword("final", 6);
		// 48: 'for' || pre-comment soft-space value soft-space post-comment
		serializationSteps[48] = createSerializationStepKeyword("for", 6);
		// 49: 'half-new-line' || pre-comment soft-space value soft-space post-comment
		serializationSteps[49] = createSerializationStepKeyword("half-new-line", 6);
		// 50: 'idiom' || pre-comment soft-space value soft-space post-comment
		serializationSteps[50] = createSerializationStepKeyword("idiom", 6);
		// 51: 'import' || pre-comment soft-space value soft-space post-comment
		serializationSteps[51] = createSerializationStepKeyword("import", 6);
		// 52: 'in' || pre-comment soft-space value soft-space post-comment
		serializationSteps[52] = createSerializationStepKeyword("in", 6);
		// 53: 'locator' || pre-comment soft-space value soft-space post-comment
		serializationSteps[53] = createSerializationStepKeyword("locator", 6);
		// 54: 'model' || pre-comment soft-space value soft-space post-comment
		serializationSteps[54] = createSerializationStepKeyword("model", 6);
		// 55: 'new-line' || pre-comment soft-space value soft-space post-comment
		serializationSteps[55] = createSerializationStepKeyword("new-line", 6);
		// 56: 'no-space' || pre-comment soft-space value soft-space post-comment
		serializationSteps[56] = createSerializationStepKeyword("no-space", 6);
		// 57: 'pop' || pre-comment soft-space value soft-space post-comment
		serializationSteps[57] = createSerializationStepKeyword("pop", 6);
		// 58: 'post-comment' || pre-comment soft-space value soft-space post-comment
		serializationSteps[58] = createSerializationStepKeyword("post-comment", 6);
		// 59: 'pre-comment' || pre-comment soft-space value soft-space post-comment
		serializationSteps[59] = createSerializationStepKeyword("pre-comment", 6);
		// 60: 'push' || pre-comment soft-space value soft-space post-comment
		serializationSteps[60] = createSerializationStepKeyword("push", 6);
		// 61: 'returns' || pre-comment soft-space value soft-space post-comment
		serializationSteps[61] = createSerializationStepKeyword("returns", 6);
		// 62: 'segment' || pre-comment soft-space value soft-space post-comment
		serializationSteps[62] = createSerializationStepKeyword("segment", 6);
		// 63: 'soft-new-line' || pre-comment soft-space value soft-space post-comment
		serializationSteps[63] = createSerializationStepKeyword("soft-new-line", 6);
		// 64: 'soft-space' || pre-comment soft-space value soft-space post-comment
		serializationSteps[64] = createSerializationStepKeyword("soft-space", 6);
		// 65: 'string' || pre-comment soft-space value soft-space post-comment
		serializationSteps[65] = createSerializationStepKeyword("string", 6);
		// 66: 'value' || pre-comment soft-space value soft-space post-comment
		serializationSteps[66] = createSerializationStepKeyword("value", 6);
		// 67: 'with' || pre-comment soft-space value soft-space post-comment
		serializationSteps[67] = createSerializationStepKeyword("with", 6);
		// 68: 'wrap-anchor' || pre-comment soft-space value soft-space post-comment
		serializationSteps[68] = createSerializationStepKeyword("wrap-anchor", 6);
		// 69: 'wrap-begin-all' || pre-comment soft-space value soft-space post-comment
		serializationSteps[69] = createSerializationStepKeyword("wrap-begin-all", 6);
		// 70: 'wrap-begin-some' || pre-comment soft-space value soft-space post-comment
		serializationSteps[70] = createSerializationStepKeyword("wrap-begin-some", 6);
		// 71: 'wrap-end' || pre-comment soft-space value soft-space post-comment
		serializationSteps[71] = createSerializationStepKeyword("wrap-end", 6);
		// 72: 'wrap-here' || pre-comment soft-space value soft-space post-comment
		serializationSteps[72] = createSerializationStepKeyword("wrap-here", 6);
		// 73: '{' || pre-comment soft-space value push soft-new-line post-comment
		serializationSteps[73] = createSerializationStepKeyword("{", 8);
		// 74: '}' || pre-comment pop soft-space value soft-new-line post-comment
		serializationSteps[74] = createSerializationStepKeyword("}", 7);
		// 75: V00*1-steps || value
		serializationSteps[75] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 1, 0);
		// 76: V00*2-steps || value
		serializationSteps[76] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 2, 0);
		// 77: V00*2-steps || value
		serializationSteps[77] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 2, 0);
		// 78: V00*3-steps || value
		serializationSteps[78] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 3, 0);
		// 79: V00*5-steps || value
		serializationSteps[79] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 5, 0);
		// 80: V01*1-steps || value
		serializationSteps[80] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 1, 0);
		// 81: V01*2-steps || value
		serializationSteps[81] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 2, 0);
		// 82: V01*5-steps || value
		serializationSteps[82] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 5, 0);
		// 83: V02*1-steps || value
		serializationSteps[83] = createSerializationStepSequence((2/*V2*/ << 4) | 2/*[*]*/, 1, 0);
		// 84: V02*2-steps || value
		serializationSteps[84] = createSerializationStepSequence((2/*V2*/ << 4) | 1/*[?]*/, 2, 0);
		// 85: V03*1-steps || new-line soft-new-line value soft-new-line
		serializationSteps[85] = createSerializationStepSequence((3/*V3*/ << 4) | 2/*[*]*/, 1, 3);
		// 86: V03*2-steps || value
		serializationSteps[86] = createSerializationStepSequence((3/*V3*/ << 4) | 1/*[?]*/, 2, 0);
		// 87: V04*1-steps || value
		serializationSteps[87] = createSerializationStepSequence((4/*V4*/ << 4) | 2/*[*]*/, 1, 0);
		// 88: V04*1-steps || new-line soft-new-line value soft-new-line
		serializationSteps[88] = createSerializationStepSequence((4/*V4*/ << 4) | 2/*[*]*/, 1, 3);
		// 89: V05*1-steps || value
		serializationSteps[89] = createSerializationStepSequence((5/*V5*/ << 4) | 2/*[*]*/, 1, 0);
	}
}

//	Commented imports ensure Xtend provides a true import allowing unqualified annotated usage
//	import Inject;
//	import NonNull;
//	import Nullable;
//	import IdiomsPackage;
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
//	import TerminalRuleValue;
//	import Grammar;
//	import GrammarProvider;
