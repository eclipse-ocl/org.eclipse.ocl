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
package org.eclipse.ocl.xtext.base.serializer;

import com.google.inject.Inject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.serializer.EClassValue.EReference_TargetGrammarRuleVector;
import org.eclipse.ocl.xtext.base.serializer.EnumerationValue.EnumerationValueMultiple;
import org.eclipse.ocl.xtext.base.serializer.EnumerationValue.EnumerationValueSingle;
import org.eclipse.ocl.xtext.base.serializer.SerializationRule.SerializationFeature;
import org.eclipse.ocl.xtext.base.serializer.SerializationSegment.CustomSerializationSegment;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.service.GrammarProvider;

/******* This file is 100% auto-generated - do not edit it *******/

/**
 * The BaseSerializationMetaData singleton provides the metadata to support a
 * model to text serialization of a parsed Xtext semantic model or to re-format an Xtext text node model.
 */
public class BaseSerializationMetaData extends AbstractSerializationMetaData
{
	/**
	 * The Provider supports injected creation of the BaseSerializationMetaData singleton.
	 */
	public static class Provider implements SerializationMetaData.Provider
	{
		private static @Nullable BaseSerializationMetaData INSTANCE = null;

		@Inject
		private GrammarProvider grammarProvider;

		@Override
		public synchronized @NonNull SerializationMetaData get() {
			// synchronized synchronizes the creation of this singleton.
			// It does not imply that the overall application is threadsafe.
			BaseSerializationMetaData instance = INSTANCE;
			if (instance == null) {
				assert grammarProvider != null;
				Grammar grammar = grammarProvider.getGrammar(Provider.class);
				assert grammar != null;
				INSTANCE = instance = new BaseSerializationMetaData(grammar);
			}
			return instance;
		}
	}

	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[10];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[2];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[36];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[11];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[30];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[34];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[18];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [6] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[35];
	private final @NonNull SubstringStep @NonNull [] substringSteps = new @NonNull SubstringStep[0];
	private final @Nullable String @Nullable [] multipleLineCommentMidfixes = new @Nullable String[] {" *"};
	private final @NonNull String @Nullable [] multipleLineCommentPrefixes = new @NonNull String[] {"/*"};
	private final @NonNull String @Nullable [] multipleLineCommentSuffixes = new @NonNull String[] {"*/"};
	private final @NonNull String @Nullable [] singleLineCommentPrefixes = new @NonNull String[] {"--"};

	private BaseSerializationMetaData(@NonNull Grammar grammar) {
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
		return 13;
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
		return 12;
	}

	@Override
	protected int getLastGlobalSerializationStepLiteralIndex() {
		return 23;
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
		eClassValues[0] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
			createSerializationRules(
				1 /* MultiplicityBoundsCS-0: MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] */,
				4 /* MultiplicityCS-2: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] ']' */,
				2 /* MultiplicityCS-0: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] '|?' ']' */,
				3 /* MultiplicityCS-1: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] MultiplicityCS::isNullFree?='|1' ']' */
			), null
		);
		eClassValues[1] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
			createSerializationRules(
				7 /* MultiplicityCS-5: '[' MultiplicityStringCS::stringBounds='*|+|?' ']' */,
				5 /* MultiplicityCS-3: '[' MultiplicityStringCS::stringBounds='*|+|?' '|?' ']' */,
				6 /* MultiplicityCS-4: '[' MultiplicityStringCS::stringBounds='*|+|?' MultiplicityCS::isNullFree?='|1' ']' */,
				8 /* MultiplicityStringCS-0: MultiplicityStringCS::stringBounds='*|+|?' */
			), null
		);
		eClassValues[2] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
			createSerializationRules(
				0 /* FirstPathElementCS-0: PathElementCS::referredElement=UnrestrictedName */,
				9 /* NextPathElementCS-0: PathElementCS::referredElement=UnreservedName */
			), null
		);
		eClassValues[3] = new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
			createSerializationRules(
				10 /* PathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */,
				16 /* UnreservedPathNameCS-0: PathNameCS::ownedPathElements+=NextPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					2) /* FirstPathElementCS|NextPathElementCS */
			}
		);
		eClassValues[4] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
			createSerializationRules(
				11 /* TemplateBindingCS-0: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS (',' TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS)[V0:*] (TemplateBindingCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					0) /* MultiplicityCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					5) /* TemplateParameterSubstitutionCS */
			}
		);
		eClassValues[5] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
			createSerializationRules(
				12 /* TemplateParameterSubstitutionCS-0: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					10) /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
			}
		);
		eClassValues[6] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
			createSerializationRules(
				13 /* TemplateSignatureCS-0: '(' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] ')' */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					6) /* TypeParameterCS */
			}
		);
		eClassValues[7] = new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
			createSerializationRules(
				14 /* TypeParameterCS-0: NamedElementCS::name=UnrestrictedName ('extends' TypeParameterCS::ownedExtends+=TypedRefCS ('&&' TypeParameterCS::ownedExtends+=TypedRefCS)[V1:*])[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					9) /* TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[8] = new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
			createSerializationRules(
				15 /* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					4) /* TemplateBindingCS */,
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					3) /* PathNameCS */
			}
		);
		eClassValues[9] = new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
			createSerializationRules(
				17 /* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			new @NonNull EReference_TargetGrammarRuleVector [] {
				createEReference_TargetGrammarRuleVector(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					9) /* TypedRefCS|TypedTypeRefCS */
			}
		);
	}

	/**
	 * Initialize string combinations used by assigned String EAttributes.
	 */
	private void initEnumerationValues() {
		// 0: '*|+|?'
		enumerationValues[0] = new EnumerationValueMultiple(new @NonNull String[]{"*", "+", "?"});
		// 1: '|1'
		enumerationValues[1] = new EnumerationValueSingle("|1");
	}

	/**
	 * Initialize the various serialization rules for each grammar rule.
	 */
	private void initGrammarRuleValues() {
		grammarRuleValues[0] = new TerminalRuleValue(0, "ANY_OTHER");
		grammarRuleValues[1] = new TerminalRuleValue(1, "DOUBLE_QUOTED_STRING");
		grammarRuleValues[2] = new TerminalRuleValue(2, "ESCAPED_CHARACTER");
		grammarRuleValues[3] = new TerminalRuleValue(3, "ESCAPED_ID");
		grammarRuleValues[4] = createParserRuleValue(4, "FirstPathElementCS", -1,
			createSerializationRules(
				0	/* FirstPathElementCS-0: PathElementCS::referredElement=UnrestrictedName */
			),
			(0 << 16) | 5	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[5] = createDataTypeRuleValue(5, "ID", 5 /* [soft-space, value, soft-space] */);
		grammarRuleValues[6] = new TerminalRuleValue(6, "INT");
		grammarRuleValues[7] = createDataTypeRuleValue(7, "Identifier", 5 /* [soft-space, value, soft-space] */);
		grammarRuleValues[8] = new TerminalRuleValue(8, "LETTER_CHARACTER");
		grammarRuleValues[9] = createDataTypeRuleValue(9, "LOWER", 5 /* [soft-space, value, soft-space] */);
		grammarRuleValues[10] = new TerminalRuleValue(10, "ML_COMMENT");
		grammarRuleValues[11] = new TerminalRuleValue(11, "ML_SINGLE_QUOTED_STRING");
		grammarRuleValues[12] = createParserRuleValue(12, "MultiplicityBoundsCS", -1,
			createSerializationRules(
				1	/* MultiplicityBoundsCS-0: MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 5	/* lowerBound=LOWER : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 5	/* upperBound=UPPER : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[13] = createParserRuleValue(13, "MultiplicityCS", -1,
			createSerializationRules(
				2	/* MultiplicityCS-0: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] '|?' ']' */,
				3	/* MultiplicityCS-1: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] MultiplicityCS::isNullFree?='|1' ']' */,
				4	/* MultiplicityCS-2: '[' MultiplicityBoundsCS::lowerBound=LOWER ('..' MultiplicityBoundsCS::upperBound=UPPER)[V0:?] ']' */,
				5	/* MultiplicityCS-3: '[' MultiplicityStringCS::stringBounds='*|+|?' '|?' ']' */,
				6	/* MultiplicityCS-4: '[' MultiplicityStringCS::stringBounds='*|+|?' MultiplicityCS::isNullFree?='|1' ']' */,
				7	/* MultiplicityCS-5: '[' MultiplicityStringCS::stringBounds='*|+|?' ']' */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 3	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* MultiplicityBoundsCS : [value] | [value] */,
			(0 << 16) | 0	/* MultiplicityStringCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 3	/* "|?" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 3	/* isNullFree?="|1" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[14] = createParserRuleValue(14, "MultiplicityStringCS", -1,
			createSerializationRules(
				8	/* MultiplicityStringCS-0: MultiplicityStringCS::stringBounds='*|+|?' */
			),
			(0 << 16) | 5	/* stringBounds=("*"|"+"|"?") : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[15] = createDataTypeRuleValue(15, "NUMBER_LITERAL", 5 /* [soft-space, value, soft-space] */);
		grammarRuleValues[16] = createParserRuleValue(16, "NextPathElementCS", -1,
			createSerializationRules(
				9	/* NextPathElementCS-0: PathElementCS::referredElement=UnreservedName */
			),
			(0 << 16) | 5	/* referredElement=UnreservedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[17] = createParserRuleValue(17, "PathNameCS", -1,
			createSerializationRules(
				10	/* PathNameCS-0: PathNameCS::ownedPathElements+=FirstPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=FirstPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 3	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[18] = new TerminalRuleValue(18, "SIMPLE_ID");
		grammarRuleValues[19] = new TerminalRuleValue(19, "SINGLE_QUOTED_STRING");
		grammarRuleValues[20] = new TerminalRuleValue(20, "SL_COMMENT");
		grammarRuleValues[21] = createDataTypeRuleValue(21, "StringLiteral", 5 /* [soft-space, value, soft-space] */);
		grammarRuleValues[22] = createParserRuleValue(22, "TemplateBindingCS", -1,
			createSerializationRules(
				11	/* TemplateBindingCS-0: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS (',' TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS)[V0:*] (TemplateBindingCS::ownedMultiplicity=MultiplicityCS)[V1:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedSubstitutions+=TemplateParameterSubstitutionCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 4	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedSubstitutions+=TemplateParameterSubstitutionCS : [value] | [value] */,
			(0 << 16) | 0	/* ownedMultiplicity=MultiplicityCS? : [value] | [value] */
		);
		grammarRuleValues[23] = createParserRuleValue(23, "TemplateParameterSubstitutionCS", -1,
			createSerializationRules(
				12	/* TemplateParameterSubstitutionCS-0: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS */
			),
			(0 << 16) | 2	/* ownedActualParameter=TypeRefCS : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[24] = createParserRuleValue(24, "TemplateSignatureCS", -1,
			createSerializationRules(
				13	/* TemplateSignatureCS-0: '(' TemplateSignatureCS::ownedParameters+=TypeParameterCS (',' TemplateSignatureCS::ownedParameters+=TypeParameterCS)[V0:*] ')' */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 4	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[25] = createParserRuleValue(25, "TypeParameterCS", -1,
			createSerializationRules(
				14	/* TypeParameterCS-0: NamedElementCS::name=UnrestrictedName ('extends' TypeParameterCS::ownedExtends+=TypedRefCS ('&&' TypeParameterCS::ownedExtends+=TypedRefCS)[V1:*])[V0:?] */
			),
			(0 << 16) | 2	/* Group : [value] | [org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 5	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 5	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "&&" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */
		);
		grammarRuleValues[26] = createParserRuleValue(26, "TypeRefCS", 10 /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
			createSerializationRules(
				15	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */,
				17	/* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* WildcardTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[27] = createParserRuleValue(27, "TypedRefCS", 9 /* TypedRefCS|TypedTypeRefCS */,
			createSerializationRules(
				15	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
			),
			(0 << 16) | 0	/* TypedTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[28] = createParserRuleValue(28, "TypedTypeRefCS", -1,
			createSerializationRules(
				15	/* TypedTypeRefCS-0: TypedTypeRefCS::ownedPathName=PathNameCS ('(' TypedTypeRefCS::ownedBinding=TemplateBindingCS ')')[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedBinding=TemplateBindingCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[29] = createDataTypeRuleValue(29, "UPPER", 5 /* [soft-space, value, soft-space] */);
		grammarRuleValues[30] = createDataTypeRuleValue(30, "URI", 5 /* [soft-space, value, soft-space] */);
		grammarRuleValues[31] = createDataTypeRuleValue(31, "UnreservedName", 5 /* [soft-space, value, soft-space] */);
		grammarRuleValues[32] = createParserRuleValue(32, "UnreservedPathNameCS", -1,
			createSerializationRules(
				16	/* UnreservedPathNameCS-0: PathNameCS::ownedPathElements+=NextPathElementCS ('::' PathNameCS::ownedPathElements+=NextPathElementCS)[V0:*] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 3	/* "::" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedPathElements+=NextPathElementCS : [value] | [value] */
		);
		grammarRuleValues[33] = createDataTypeRuleValue(33, "UnrestrictedName", 5 /* [soft-space, value, soft-space] */);
		grammarRuleValues[34] = new TerminalRuleValue(34, "WS");
		grammarRuleValues[35] = createParserRuleValue(35, "WildcardTypeRefCS", -1,
			createSerializationRules(
				17	/* WildcardTypeRefCS-0: '?' ('extends' WildcardTypeRefCS::ownedExtends=TypedRefCS)[V0:?] */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* {WildcardTypeRefCS} : [value] | [value] */,
			(0 << 16) | 5	/* "?" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 5	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends=TypedRefCS : [value] | [value] */
		);
	}

	/**
	 * Initialize bit vectors of useful grammar rule combinations.
	 */
	private void initGrammarRuleVectors() {
		// 0: MultiplicityCS
		grammarRuleVectors[0] = new GrammarRuleVector(0x2000L);
		// 1: NextPathElementCS
		grammarRuleVectors[1] = new GrammarRuleVector(0x10000L);
		// 2: FirstPathElementCS|NextPathElementCS
		grammarRuleVectors[2] = new GrammarRuleVector(0x10010L);
		// 3: PathNameCS
		grammarRuleVectors[3] = new GrammarRuleVector(0x20000L);
		// 4: TemplateBindingCS
		grammarRuleVectors[4] = new GrammarRuleVector(0x400000L);
		// 5: TemplateParameterSubstitutionCS
		grammarRuleVectors[5] = new GrammarRuleVector(0x800000L);
		// 6: TypeParameterCS
		grammarRuleVectors[6] = new GrammarRuleVector(0x2000000L);
		// 7: TypeRefCS
		grammarRuleVectors[7] = new GrammarRuleVector(0x4000000L);
		// 8: TypedRefCS
		grammarRuleVectors[8] = new GrammarRuleVector(0x8000000L);
		// 9: TypedRefCS|TypedTypeRefCS
		grammarRuleVectors[9] = new GrammarRuleVector(0x18000000L);
		// 10: TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
		grammarRuleVectors[10] = new GrammarRuleVector(0x81c000000L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// 0: assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(22);
		// 1: assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(23);
		// 2: assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(24);
		// 3: assert (|NamedElementCS::name| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(25);
		// 4: assert (|PathElementCS::referredElement| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(26);
		// 5: assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(29);
		// 6: assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[6] = createMatchStep_Assert(33);
		// 7: assert |ModelElementCS::ownedAnnotations| == 0
		serializationMatchSteps[7] = createMatchStep_Assert(2);
		// 8: assert |MultiplicityCS::isNullFree| == 0
		serializationMatchSteps[8] = createMatchStep_Assert(6);
		// 9: assert |TypedRefCS::ownedMultiplicity| == 0
		serializationMatchSteps[9] = createMatchStep_Assert(16);
		// 10: assert |TypedTypeRefCS::isTypeof| == 0
		serializationMatchSteps[10] = createMatchStep_Assert(17);
		// 11: assert |WildcardTypeRefCS::ownedSuper| == 0
		serializationMatchSteps[11] = createMatchStep_Assert(21);
		// 12: assign V0 = (|PathNameCS::ownedPathElements| - 1)
		serializationMatchSteps[12] = createMatchStep_Assign(0, 27);
		// 13: assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchSteps[13] = createMatchStep_Assign(0, 28);
		// 14: assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchSteps[14] = createMatchStep_Assign(0, 30);
		// 15: assign V0 = (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchSteps[15] = createMatchStep_Assign(0, 32);
		// 16: assign V0 = |MultiplicityBoundsCS::upperBound|
		serializationMatchSteps[16] = createMatchStep_Assign(0, 4);
		// 17: assign V0 = |TypedTypeRefCS::ownedBinding|
		serializationMatchSteps[17] = createMatchStep_Assign(0, 18);
		// 18: assign V0 = |WildcardTypeRefCS::ownedExtends|
		serializationMatchSteps[18] = createMatchStep_Assign(0, 20);
		// 19: assign V1 = (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchSteps[19] = createMatchStep_Assign(1, 31);
		// 20: assign V1 = |TemplateBindingCS::ownedMultiplicity|
		serializationMatchSteps[20] = createMatchStep_Assign(1, 11);
		// 21: check-rule basecs::PathNameCS.ownedPathElements : 16
		serializationMatchSteps[21] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 1/*NextPathElementCS*/);
		// 22: check-rule basecs::TemplateBindingCS.ownedMultiplicity : 13
		serializationMatchSteps[22] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 0/*MultiplicityCS*/);
		// 23: check-rule basecs::TemplateBindingCS.ownedSubstitutions : 23
		serializationMatchSteps[23] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 5/*TemplateParameterSubstitutionCS*/);
		// 24: check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 26|27|28|35
		serializationMatchSteps[24] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 10/*TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS*/);
		// 25: check-rule basecs::TemplateSignatureCS.ownedParameters : 25
		serializationMatchSteps[25] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 6/*TypeParameterCS*/);
		// 26: check-rule basecs::TypeParameterCS.ownedExtends : 27|28
		serializationMatchSteps[26] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 9/*TypedRefCS|TypedTypeRefCS*/);
		// 27: check-rule basecs::TypedTypeRefCS.ownedBinding : 22
		serializationMatchSteps[27] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 4/*TemplateBindingCS*/);
		// 28: check-rule basecs::TypedTypeRefCS.ownedPathName : 17
		serializationMatchSteps[28] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 3/*PathNameCS*/);
		// 29: check-rule basecs::WildcardTypeRefCS.ownedExtends : 27|28
		serializationMatchSteps[29] = createMatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 9/*TypedRefCS|TypedTypeRefCS*/);
	}

	/**
	 * Initialize expression terms used during the matching process.
	 */
	private void initMatchTerms() {
		// 0: 0
		serializationMatchTerms[0] = createSerializationMatchTermInteger(0);
		// 1: 1
		serializationMatchTerms[1] = createSerializationMatchTermInteger(1);
		// 2: |ModelElementCS::ownedAnnotations|
		serializationMatchTerms[2] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		// 3: |MultiplicityBoundsCS::lowerBound|
		serializationMatchTerms[3] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		// 4: |MultiplicityBoundsCS::upperBound|
		serializationMatchTerms[4] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		// 5: |MultiplicityCS::isNullFree.'|1'|
		serializationMatchTerms[5] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 1 /* '|1' */);
		// 6: |MultiplicityCS::isNullFree|
		serializationMatchTerms[6] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE);
		// 7: |MultiplicityStringCS::stringBounds.'*|+|?'|
		serializationMatchTerms[7] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */);
		// 8: |NamedElementCS::name|
		serializationMatchTerms[8] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		// 9: |PathElementCS::referredElement|
		serializationMatchTerms[9] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		// 10: |PathNameCS::ownedPathElements|
		serializationMatchTerms[10] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		// 11: |TemplateBindingCS::ownedMultiplicity|
		serializationMatchTerms[11] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		// 12: |TemplateBindingCS::ownedSubstitutions|
		serializationMatchTerms[12] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		// 13: |TemplateParameterSubstitutionCS::ownedActualParameter|
		serializationMatchTerms[13] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		// 14: |TemplateSignatureCS::ownedParameters|
		serializationMatchTerms[14] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		// 15: |TypeParameterCS::ownedExtends|
		serializationMatchTerms[15] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		// 16: |TypedRefCS::ownedMultiplicity|
		serializationMatchTerms[16] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
		// 17: |TypedTypeRefCS::isTypeof|
		serializationMatchTerms[17] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF);
		// 18: |TypedTypeRefCS::ownedBinding|
		serializationMatchTerms[18] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		// 19: |TypedTypeRefCS::ownedPathName|
		serializationMatchTerms[19] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		// 20: |WildcardTypeRefCS::ownedExtends|
		serializationMatchTerms[20] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		// 21: |WildcardTypeRefCS::ownedSuper|
		serializationMatchTerms[21] = createSerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_SUPER);
		// 22: (|MultiplicityBoundsCS::lowerBound| - 1)
		serializationMatchTerms[22] = createSerializationMatchTermSubtract(3, 1);
		// 23: (|MultiplicityCS::isNullFree.'|1'| - 1)
		serializationMatchTerms[23] = createSerializationMatchTermSubtract(5, 1);
		// 24: (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
		serializationMatchTerms[24] = createSerializationMatchTermSubtract(7, 1);
		// 25: (|NamedElementCS::name| - 1)
		serializationMatchTerms[25] = createSerializationMatchTermSubtract(8, 1);
		// 26: (|PathElementCS::referredElement| - 1)
		serializationMatchTerms[26] = createSerializationMatchTermSubtract(9, 1);
		// 27: (|PathNameCS::ownedPathElements| - 1)
		serializationMatchTerms[27] = createSerializationMatchTermSubtract(10, 1);
		// 28: (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchTerms[28] = createSerializationMatchTermSubtract(12, 1);
		// 29: (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
		serializationMatchTerms[29] = createSerializationMatchTermSubtract(13, 1);
		// 30: (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchTerms[30] = createSerializationMatchTermSubtract(14, 1);
		// 31: (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchTerms[31] = createSerializationMatchTermSubtract(15, 1);
		// 32: (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchTerms[32] = createSerializationMatchTermGreaterThan(15, 0);
		// 33: (|TypedTypeRefCS::ownedPathName| - 1)
		serializationMatchTerms[33] = createSerializationMatchTermSubtract(19, 1);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules() {
		// Base::FirstPathElementCS-0(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[0] = createSerializationRule("FirstPathElementCS-0", 4,
			createSerializationMatchSteps(
				4		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				34		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::MultiplicityBoundsCS-0(basecs::MultiplicityBoundsCS): { lowerBound=LOWER { ".." upperBound=UPPER }[?] }
		serializationRules[1] = createSerializationRule("MultiplicityBoundsCS-0", 12,
			createSerializationMatchSteps(
				8		/* assert |MultiplicityCS::isNullFree| == 0 */,
				16		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				0		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				1		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				24		/* V00*2-steps || value */,
				17		/* '..' || no-space value no-space */,
				12		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-0(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" }
		serializationRules[2] = createSerializationRule("MultiplicityCS-0", 13,
			createSerializationMatchSteps(
				8		/* assert |MultiplicityCS::isNullFree| == 0 */,
				16		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				0		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				20		/* '[' || no-space value no-space */,
				1		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				24		/* V00*2-steps || value */,
				17		/* '..' || no-space value no-space */,
				12		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				23		/* '|?' || no-space value no-space */,
				21		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-1(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree?="|1" "]" }
		serializationRules[3] = createSerializationRule("MultiplicityCS-1", 13,
			createSerializationMatchSteps(
				1		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				16		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				0		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				20		/* '[' || no-space value no-space */,
				1		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				24		/* V00*2-steps || value */,
				17		/* '..' || no-space value no-space */,
				12		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				0		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				21		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, false,
					(1/*'|1'*/ << 4) | 0 /*[1]*/
				),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-2(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" }
		serializationRules[4] = createSerializationRule("MultiplicityCS-2", 13,
			createSerializationMatchSteps(
				8		/* assert |MultiplicityCS::isNullFree| == 0 */,
				16		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
				0		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
			),
			createSerializationSteps(
				20		/* '[' || no-space value no-space */,
				1		/* MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space */,
				24		/* V00*2-steps || value */,
				17		/* '..' || no-space value no-space */,
				12		/* MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space */,
				21		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, true, GrammarCardinality.ONE),
				createSerializationSimpleAttribute(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, false, GrammarCardinality.ZERO_OR_ONE)
			});
		// Base::MultiplicityCS-3(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "|?" "]" }
		serializationRules[5] = createSerializationRule("MultiplicityCS-3", 13,
			createSerializationMatchSteps(
				8		/* assert |MultiplicityCS::isNullFree| == 0 */,
				2		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				20		/* '[' || no-space value no-space */,
				11		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				23		/* '|?' || no-space value no-space */,
				21		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityCS-4(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} isNullFree?="|1" "]" }
		serializationRules[6] = createSerializationRule("MultiplicityCS-4", 13,
			createSerializationMatchSteps(
				1		/* assert (|MultiplicityCS::isNullFree.'|1'| - 1) == 0 */,
				2		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				20		/* '[' || no-space value no-space */,
				11		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				0		/* MultiplicityCS::isNullFree?='|1' || no-space value no-space */,
				21		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, false,
					(1/*'|1'*/ << 4) | 0 /*[1]*/
				),
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityCS-5(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "]" }
		serializationRules[7] = createSerializationRule("MultiplicityCS-5", 13,
			createSerializationMatchSteps(
				8		/* assert |MultiplicityCS::isNullFree| == 0 */,
				2		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				20		/* '[' || no-space value no-space */,
				11		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
				21		/* ']' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::MultiplicityStringCS-0(basecs::MultiplicityStringCS): stringBounds={'*|+|?'}
		serializationRules[8] = createSerializationRule("MultiplicityStringCS-0", 14,
			createSerializationMatchSteps(
				8		/* assert |MultiplicityCS::isNullFree| == 0 */,
				2		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
			),
			createSerializationSteps(
				11		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationEnumeratedAttribute(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, false,
					(0/*'*|+|?'*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::NextPathElementCS-0(basecs::PathElementCS): referredElement=UnreservedName
		serializationRules[9] = createSerializationRule("NextPathElementCS-0", 16,
			createSerializationMatchSteps(
				4		/* assert (|PathElementCS::referredElement| - 1) == 0 */
			),
			createSerializationSteps(
				33		/* PathElementCS::referredElement=UnreservedName || soft-space value soft-space */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, -1
				)
			});
		// Base::PathNameCS-0(basecs::PathNameCS): { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[10] = createSerializationRule("PathNameCS-0", 17,
			null,	// run-time resolution using SerializationSteps
			createSerializationSteps(
				32		/* PathNameCS::ownedPathElements+=FirstPathElementCS || value */,
				25		/* V00*2-steps || value */,
				18		/* '::' || no-space value no-space */,
				31		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 2/* FirstPathElementCS,NextPathElementCS */,
					(4/*FirstPathElementCS*/ << 4) | 0 /*[1]*/,
					(16/*NextPathElementCS*/ << 4) | 2 /*[*]*/
				)
			});
		// Base::TemplateBindingCS-0(basecs::TemplateBindingCS): { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[11] = createSerializationRule("TemplateBindingCS-0", 22,
			createSerializationMatchSteps(
				22		/* check-rule basecs::TemplateBindingCS.ownedMultiplicity : MultiplicityCS */,
				23		/* check-rule basecs::TemplateBindingCS.ownedSubstitutions : TemplateParameterSubstitutionCS */,
				20		/* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
				13		/* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
			),
			createSerializationSteps(
				10		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				25		/* V00*2-steps || value */,
				16		/* ',' || no-space value soft-space */,
				10		/* TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value */,
				28		/* V01*1-steps || value */,
				7		/* TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 0/* MultiplicityCS */,
					(13/*MultiplicityCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 5/* TemplateParameterSubstitutionCS */,
					(23/*TemplateParameterSubstitutionCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::TemplateParameterSubstitutionCS-0(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS
		serializationRules[12] = createSerializationRule("TemplateParameterSubstitutionCS-0", 23,
			createSerializationMatchSteps(
				7		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				24		/* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
				5		/* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
			),
			createSerializationSteps(
				3		/* TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 7/* TypeRefCS */,
					(26/*TypeRefCS*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::TemplateSignatureCS-0(basecs::TemplateSignatureCS): { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" }
		serializationRules[13] = createSerializationRule("TemplateSignatureCS-0", 24,
			createSerializationMatchSteps(
				7		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				25		/* check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS */,
				14		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
			),
			createSerializationSteps(
				30		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				14		/* '(' || no-space value no-space */,
				8		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				25		/* V00*2-steps || value */,
				16		/* ',' || no-space value soft-space */,
				8		/* TemplateSignatureCS::ownedParameters+=TypeParameterCS || value */,
				15		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 6/* TypeParameterCS */,
					(25/*TypeParameterCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::TypeParameterCS-0(basecs::TypeParameterCS): { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] }
		serializationRules[14] = createSerializationRule("TypeParameterCS-0", 25,
			createSerializationMatchSteps(
				7		/* assert |ModelElementCS::ownedAnnotations| == 0 */,
				26		/* check-rule basecs::TypeParameterCS.ownedExtends : TypedRefCS|TypedTypeRefCS */,
				3		/* assert (|NamedElementCS::name| - 1) == 0 */,
				15		/* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
				19		/* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
			),
			createSerializationSteps(
				30		/* wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
				2		/* NamedElementCS::name=UnrestrictedName || soft-space value soft-space */,
				27		/* V00*5-steps || value */,
				22		/* 'extends' || soft-space value soft-space */,
				5		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */,
				29		/* V01*2-steps || value */,
				13		/* '&&' || soft-space value soft-space */,
				5		/* TypeParameterCS::ownedExtends+=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationSimpleAttribute(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, true, GrammarCardinality.ONE),
				createSerializationReference(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 8/* TypedRefCS */,
					(27/*TypedRefCS*/ << 4) | 2 /*[*]*/
				)
			});
		// Base::TypedTypeRefCS-0(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] }
		serializationRules[15] = createSerializationRule("TypedTypeRefCS-0", 28,
			createSerializationMatchSteps(
				10		/* assert |TypedTypeRefCS::isTypeof| == 0 */,
				9		/* assert |TypedRefCS::ownedMultiplicity| == 0 */,
				27		/* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
				28		/* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
				17		/* assign V0 = |TypedTypeRefCS::ownedBinding| */,
				6		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
			),
			createSerializationSteps(
				9		/* TypedTypeRefCS::ownedPathName=PathNameCS || value */,
				26		/* V00*3-steps || value */,
				14		/* '(' || no-space value no-space */,
				4		/* TypedTypeRefCS::ownedBinding=TemplateBindingCS || value */,
				15		/* ')' || no-space value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 4/* TemplateBindingCS */,
					(22/*TemplateBindingCS*/ << 4) | 1 /*[?]*/
				),
				createSerializationReference(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 3/* PathNameCS */,
					(17/*PathNameCS*/ << 4) | 0 /*[1]*/
				)
			});
		// Base::UnreservedPathNameCS-0(basecs::PathNameCS): { ownedPathElements+=NextPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[16] = createSerializationRule("UnreservedPathNameCS-0", 32,
			createSerializationMatchSteps(
				21		/* check-rule basecs::PathNameCS.ownedPathElements : NextPathElementCS */,
				12		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
			),
			createSerializationSteps(
				31		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */,
				25		/* V00*2-steps || value */,
				18		/* '::' || no-space value no-space */,
				31		/* PathNameCS::ownedPathElements+=NextPathElementCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 1/* NextPathElementCS */,
					(16/*NextPathElementCS*/ << 4) | 3 /*[+]*/
				)
			});
		// Base::WildcardTypeRefCS-0(basecs::WildcardTypeRefCS): { "?" { "extends" ownedExtends=TypedRefCS }[?] }
		serializationRules[17] = createSerializationRule("WildcardTypeRefCS-0", 35,
			createSerializationMatchSteps(
				11		/* assert |WildcardTypeRefCS::ownedSuper| == 0 */,
				29		/* check-rule basecs::WildcardTypeRefCS.ownedExtends : TypedRefCS|TypedTypeRefCS */,
				18		/* assign V0 = |WildcardTypeRefCS::ownedExtends| */
			),
			createSerializationSteps(
				19		/* '?' || soft-space value soft-space */,
				24		/* V00*2-steps || value */,
				22		/* 'extends' || soft-space value soft-space */,
				6		/* WildcardTypeRefCS::ownedExtends=TypedRefCS || value */
			),
			new @NonNull SerializationFeature [] {
				createSerializationReference(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 8/* TypedRefCS */,
					(27/*TypedRefCS*/ << 4) | 1 /*[?]*/
				)
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
			new CustomSerializationSegment(BaseCommentSegmentSupport.class) /* org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport */,
			SerializationSegment.VALUE /* value */
		};
		serializationSegments[3] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.NO_SPACE /* no-space */
		};
		serializationSegments[4] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.NO_SPACE /* no-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
		serializationSegments[5] = new @NonNull SerializationSegment @NonNull [] {
			SerializationSegment.SOFT_SPACE /* soft-space */,
			SerializationSegment.VALUE /* value */,
			SerializationSegment.SOFT_SPACE /* soft-space */
		};
	}

	/**
	 * Initialize the various serialization steps used to serialize a serialization rule.
	 */
	private void initSerializationSteps() {
		// 0: MultiplicityCS::isNullFree?='|1' || no-space value no-space
		serializationSteps[0] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 1 /* '|1' */, 3);
		// 1: MultiplicityBoundsCS::lowerBound=LOWER || soft-space value soft-space
		serializationSteps[1] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 9 /*LOWER*/, 5);
		// 2: NamedElementCS::name=UnrestrictedName || soft-space value soft-space
		serializationSteps[2] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 33 /*UnrestrictedName*/, 5);
		// 3: TemplateParameterSubstitutionCS::ownedActualParameter=TypeRefCS || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[3] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 26 /*TypeRefCS*/, 2);
		// 4: TypedTypeRefCS::ownedBinding=TemplateBindingCS || value
		serializationSteps[4] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 22 /*TemplateBindingCS*/, 0);
		// 5: TypeParameterCS::ownedExtends+=TypedRefCS || value
		serializationSteps[5] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 27 /*TypedRefCS*/, 0);
		// 6: WildcardTypeRefCS::ownedExtends=TypedRefCS || value
		serializationSteps[6] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 27 /*TypedRefCS*/, 0);
		// 7: TemplateBindingCS::ownedMultiplicity=MultiplicityCS || value
		serializationSteps[7] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 13 /*MultiplicityCS*/, 0);
		// 8: TemplateSignatureCS::ownedParameters+=TypeParameterCS || value
		serializationSteps[8] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 25 /*TypeParameterCS*/, 0);
		// 9: TypedTypeRefCS::ownedPathName=PathNameCS || value
		serializationSteps[9] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 17 /*PathNameCS*/, 0);
		// 10: TemplateBindingCS::ownedSubstitutions+=TemplateParameterSubstitutionCS || value
		serializationSteps[10] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 23 /*TemplateParameterSubstitutionCS*/, 0);
		// 11: MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space
		serializationSteps[11] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */, 5);
		// 12: MultiplicityBoundsCS::upperBound=UPPER || soft-space value soft-space
		serializationSteps[12] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 29 /*UPPER*/, 5);
		// 13: '&&' || soft-space value soft-space
		serializationSteps[13] = createSerializationStepKeyword("&&", 5);
		// 14: '(' || no-space value no-space
		serializationSteps[14] = createSerializationStepKeyword("(", 3);
		// 15: ')' || no-space value
		serializationSteps[15] = createSerializationStepKeyword(")", 1);
		// 16: ',' || no-space value soft-space
		serializationSteps[16] = createSerializationStepKeyword(",", 4);
		// 17: '..' || no-space value no-space
		serializationSteps[17] = createSerializationStepKeyword("..", 3);
		// 18: '::' || no-space value no-space
		serializationSteps[18] = createSerializationStepKeyword("::", 3);
		// 19: '?' || soft-space value soft-space
		serializationSteps[19] = createSerializationStepKeyword("?", 5);
		// 20: '[' || no-space value no-space
		serializationSteps[20] = createSerializationStepKeyword("[", 3);
		// 21: ']' || no-space value
		serializationSteps[21] = createSerializationStepKeyword("]", 1);
		// 22: 'extends' || soft-space value soft-space
		serializationSteps[22] = createSerializationStepKeyword("extends", 5);
		// 23: '|?' || no-space value no-space
		serializationSteps[23] = createSerializationStepKeyword("|?", 3);
		// 24: V00*2-steps || value
		serializationSteps[24] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 2, 0);
		// 25: V00*2-steps || value
		serializationSteps[25] = createSerializationStepSequence((0/*V0*/ << 4) | 2/*[*]*/, 2, 0);
		// 26: V00*3-steps || value
		serializationSteps[26] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 3, 0);
		// 27: V00*5-steps || value
		serializationSteps[27] = createSerializationStepSequence((0/*V0*/ << 4) | 1/*[?]*/, 5, 0);
		// 28: V01*1-steps || value
		serializationSteps[28] = createSerializationStepSequence((1/*V1*/ << 4) | 1/*[?]*/, 1, 0);
		// 29: V01*2-steps || value
		serializationSteps[29] = createSerializationStepSequence((1/*V1*/ << 4) | 2/*[*]*/, 2, 0);
		// 30: wrapper || org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[30] = createSerializationStepWrapper(2);
		// 31: PathNameCS::ownedPathElements+=NextPathElementCS || value
		serializationSteps[31] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 16 /*NextPathElementCS*/, 0);
		// 32: PathNameCS::ownedPathElements+=FirstPathElementCS || value
		serializationSteps[32] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 4 /*FirstPathElementCS*/, 0);
		// 33: PathElementCS::referredElement=UnreservedName || soft-space value soft-space
		serializationSteps[33] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"), 31, 5);
		// 34: PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space
		serializationSteps[34] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"), 33, 5);
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
//	import BaseCommentSegmentSupport;
//	import EClassValue;
//	import EReference_TargetGrammarRuleVector;
//	import EnumerationValue;
//	import EnumerationValueMultiple;
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
//	import CustomSerializationSegment;
//	import SerializationStep;
//	import SubstringStep;
//	import TerminalRuleValue;
//	import BaseCSPackage;
//	import Grammar;
//	import GrammarProvider;
