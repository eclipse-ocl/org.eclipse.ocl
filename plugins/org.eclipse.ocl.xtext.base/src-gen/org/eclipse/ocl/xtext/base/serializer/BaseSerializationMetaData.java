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
package org.eclipse.ocl.xtext.base.serializer;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.AbstractSerializationMetaData;
import org.eclipse.ocl.examples.xtext.serializer.DataTypeRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue.EnumerationValueMultiple;
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
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;

public class BaseSerializationMetaData extends AbstractSerializationMetaData
{
	private boolean initialized = false;
	private final @NonNull EClassValue @NonNull [] eClassValues = new @NonNull EClassValue[10];
	private final @NonNull EnumerationValue @NonNull [] enumerationValues = new @NonNull EnumerationValue[2];
	private final @NonNull GrammarRuleValue @NonNull [] grammarRuleValues = new @NonNull GrammarRuleValue[35];
	private final @NonNull GrammarRuleVector @NonNull [] grammarRuleVectors = new @NonNull GrammarRuleVector[10];
	private final @NonNull SerializationMatchStep @NonNull [] serializationMatchSteps = new @NonNull SerializationMatchStep[26];
	private final @NonNull SerializationMatchTerm @NonNull [] serializationMatchTerms = new @NonNull SerializationMatchTerm[28];
	private final @NonNull SerializationRule @NonNull [] serializationRules = new @NonNull SerializationRule[17];
	private final @NonNull SerializationSegment @NonNull [] @NonNull [] serializationSegments = new @NonNull SerializationSegment @NonNull [6] @NonNull [];
	private final @NonNull SerializationStep @NonNull [] serializationSteps = new @NonNull SerializationStep[38];

	private final @Nullable String @Nullable [] multipleLineCommentMidfixes = new @Nullable String[] {" *"};
	private final @NonNull String @Nullable [] multipleLineCommentPrefixes = new @NonNull String[] {"/*"};
	private final @NonNull String @Nullable [] multipleLineCommentSuffixes = new @NonNull String[] {"*/"};
	private final @NonNull String @Nullable [] singleLineCommentPrefixes = new @NonNull String[] {"--"};

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
		return 12;
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
		return 11;
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
		eClassValues[0] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
			createSerializationRules(
				1 /* { lowerBound=LOWER { ".." upperBound=UPPER }[?] } */,
				2 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" } */,
				3 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" } */,
				4 /* { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree="|1"[?] "]" } */
			), null
		);
		eClassValues[1] = new EClassValue(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
			createSerializationRules(
				5 /* { "[" stringBounds={'*|+|?'} "]" } */,
				6 /* { "[" stringBounds={'*|+|?'} "|?" "]" } */,
				7 /* { "[" stringBounds={'*|+|?'} isNullFree="|1"[?] "]" } */,
				8 /* stringBounds={'*|+|?'} */
			), null
		);
		eClassValues[2] = new EClassValue(BaseCSPackage.Literals.PATH_ELEMENT_CS,
			createSerializationRules(
				0 /* referredElement=UnrestrictedName */,
				9 /* referredElement=UnreservedName */
			), null
		);
		eClassValues[3] = new EClassValue(BaseCSPackage.Literals.PATH_NAME_CS,
			createSerializationRules(
				10 /* { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
					1) /* FirstPathElementCS|NextPathElementCS */
			}
		);
		eClassValues[4] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
			createSerializationRules(
				11 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
					0) /* MultiplicityCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
					4) /* TemplateParameterSubstitutionCS */
			}
		);
		eClassValues[5] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
			createSerializationRules(
				12 /* ownedActualParameter=TypeRefCS */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
					9) /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */
			}
		);
		eClassValues[6] = new EClassValue(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
			createSerializationRules(
				13 /* { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
					5) /* TypeParameterCS */
			}
		);
		eClassValues[7] = new EClassValue(BaseCSPackage.Literals.TYPE_PARAMETER_CS,
			createSerializationRules(
				14 /* { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
					8) /* TypedRefCS|TypedTypeRefCS */
			}
		);
		eClassValues[8] = new EClassValue(BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
			createSerializationRules(
				15 /* { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
					3) /* TemplateBindingCS */,
				createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
					2) /* PathNameCS */
			}
		);
		eClassValues[9] = new EClassValue(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
			createSerializationRules(
				16 /* { "?" { "extends" ownedExtends=TypedRefCS }[?] } */
			),
			new @NonNull EReference_RuleIndexes [] {
				createEReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
					8) /* TypedRefCS|TypedTypeRefCS */
			}
		);
	}

	/**
	 * Initialize string combinations used by assigned String EAttributes.
	 */
	private void initEnumerationValues() {
		// '*|+|?'
		enumerationValues[0] = new EnumerationValueMultiple(new @NonNull String[]{"*", "+", "?"});
		// '|1'
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
				0	/* FirstPathElementCS: referredElement=UnrestrictedName */
			),
			(0 << 16) | 5	/* referredElement=UnrestrictedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[5] = new DataTypeRuleValue(5, "ID");
		grammarRuleValues[6] = new TerminalRuleValue(6, "INT");
		grammarRuleValues[7] = new DataTypeRuleValue(7, "Identifier");
		grammarRuleValues[8] = new TerminalRuleValue(8, "LETTER_CHARACTER");
		grammarRuleValues[9] = new DataTypeRuleValue(9, "LOWER");
		grammarRuleValues[10] = new TerminalRuleValue(10, "ML_COMMENT");
		grammarRuleValues[11] = new TerminalRuleValue(11, "ML_SINGLE_QUOTED_STRING");
		grammarRuleValues[12] = createParserRuleValue(12, "MultiplicityBoundsCS", -1,
			createSerializationRules(
				1	/* MultiplicityBoundsCS: { lowerBound=LOWER { ".." upperBound=UPPER }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 5	/* lowerBound=LOWER : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* ".." : [value] | [no-space, value, no-space] */,
			(0 << 16) | 5	/* upperBound=UPPER : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[13] = createParserRuleValue(13, "MultiplicityCS", -1,
			createSerializationRules(
				2	/* MultiplicityCS: { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" } */,
				3	/* MultiplicityCS: { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" } */,
				4	/* MultiplicityCS: { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree="|1"[?] "]" } */,
				5	/* MultiplicityCS: { "[" stringBounds={'*|+|?'} "]" } */,
				6	/* MultiplicityCS: { "[" stringBounds={'*|+|?'} "|?" "]" } */,
				7	/* MultiplicityCS: { "[" stringBounds={'*|+|?'} isNullFree="|1"[?] "]" } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 3	/* "[" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* MultiplicityBoundsCS : [value] | [value] */,
			(0 << 16) | 0	/* MultiplicityStringCS : [value] | [value] */,
			(0 << 16) | 0	/* Alternatives? : [value] | [value] */,
			(0 << 16) | 5	/* "|?" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 5	/* isNullFree?="|1" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 1	/* "]" : [value] | [no-space, value] */
		);
		grammarRuleValues[14] = createParserRuleValue(14, "MultiplicityStringCS", -1,
			createSerializationRules(
				8	/* MultiplicityStringCS: stringBounds={'*|+|?'} */
			),
			(0 << 16) | 5	/* stringBounds=("*"|"+"|"?") : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[15] = new DataTypeRuleValue(15, "NUMBER_LITERAL");
		grammarRuleValues[16] = createParserRuleValue(16, "NextPathElementCS", -1,
			createSerializationRules(
				9	/* NextPathElementCS: referredElement=UnreservedName */
			),
			(0 << 16) | 5	/* referredElement=UnreservedName : [value] | [soft-space, value, soft-space] */
		);
		grammarRuleValues[17] = createParserRuleValue(17, "PathNameCS", -1,
			createSerializationRules(
				10	/* PathNameCS: { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] } */
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
		grammarRuleValues[21] = new DataTypeRuleValue(21, "StringLiteral");
		grammarRuleValues[22] = createParserRuleValue(22, "TemplateBindingCS", -1,
			createSerializationRules(
				11	/* TemplateBindingCS: { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
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
				12	/* TemplateParameterSubstitutionCS: ownedActualParameter=TypeRefCS */
			),
			(0 << 16) | 2	/* ownedActualParameter=TypeRefCS : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */
		);
		grammarRuleValues[24] = createParserRuleValue(24, "TemplateSignatureCS", -1,
			createSerializationRules(
				13	/* TemplateSignatureCS: { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 4	/* "," : [value] | [no-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedParameters+=TypeParameterCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[25] = createParserRuleValue(25, "TypeParameterCS", -1,
			createSerializationRules(
				14	/* TypeParameterCS: { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] } */
			),
			(0 << 16) | 2	/* Group : [value] | [supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport, value] */,
			(0 << 16) | 5	/* name=UnrestrictedName : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 5	/* "extends" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* Group* : [value] | [value] */,
			(0 << 16) | 5	/* "&&" : [value] | [soft-space, value, soft-space] */,
			(0 << 16) | 0	/* ownedExtends+=TypedRefCS : [value] | [value] */
		);
		grammarRuleValues[26] = createParserRuleValue(26, "TypeRefCS", 9 /* TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS */,
			createSerializationRules(
				15	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */,
				16	/* WildcardTypeRefCS: { "?" { "extends" ownedExtends=TypedRefCS }[?] } */
			),
			(0 << 16) | 0	/* Alternatives : [value] | [value] */,
			(0 << 16) | 0	/* TypedRefCS : [value] | [value] */,
			(0 << 16) | 0	/* WildcardTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[27] = createParserRuleValue(27, "TypedRefCS", 8 /* TypedRefCS|TypedTypeRefCS */,
			createSerializationRules(
				15	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */
			),
			(0 << 16) | 0	/* TypedTypeRefCS : [value] | [value] */
		);
		grammarRuleValues[28] = createParserRuleValue(28, "TypedTypeRefCS", -1,
			createSerializationRules(
				15	/* TypedTypeRefCS: { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] } */
			),
			(0 << 16) | 0	/* Group : [value] | [value] */,
			(0 << 16) | 0	/* ownedPathName=PathNameCS : [value] | [value] */,
			(0 << 16) | 0	/* Group? : [value] | [value] */,
			(0 << 16) | 3	/* "(" : [value] | [no-space, value, no-space] */,
			(0 << 16) | 0	/* ownedBinding=TemplateBindingCS : [value] | [value] */,
			(0 << 16) | 1	/* ")" : [value] | [no-space, value] */
		);
		grammarRuleValues[29] = new DataTypeRuleValue(29, "UPPER");
		grammarRuleValues[30] = new DataTypeRuleValue(30, "URI");
		grammarRuleValues[31] = new DataTypeRuleValue(31, "UnreservedName");
		grammarRuleValues[32] = new DataTypeRuleValue(32, "UnrestrictedName");
		grammarRuleValues[33] = new TerminalRuleValue(33, "WS");
		grammarRuleValues[34] = createParserRuleValue(34, "WildcardTypeRefCS", -1,
			createSerializationRules(
				16	/* WildcardTypeRefCS: { "?" { "extends" ownedExtends=TypedRefCS }[?] } */
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
		// MultiplicityCS
		grammarRuleVectors[0] = new GrammarRuleVector(0x2000L);
		// FirstPathElementCS|NextPathElementCS
		grammarRuleVectors[1] = new GrammarRuleVector(0x10010L);
		// PathNameCS
		grammarRuleVectors[2] = new GrammarRuleVector(0x20000L);
		// TemplateBindingCS
		grammarRuleVectors[3] = new GrammarRuleVector(0x400000L);
		// TemplateParameterSubstitutionCS
		grammarRuleVectors[4] = new GrammarRuleVector(0x800000L);
		// TypeParameterCS
		grammarRuleVectors[5] = new GrammarRuleVector(0x2000000L);
		// TypeRefCS
		grammarRuleVectors[6] = new GrammarRuleVector(0x4000000L);
		// TypedRefCS
		grammarRuleVectors[7] = new GrammarRuleVector(0x8000000L);
		// TypedRefCS|TypedTypeRefCS
		grammarRuleVectors[8] = new GrammarRuleVector(0x18000000L);
		// TypeRefCS|TypedRefCS|TypedTypeRefCS|WildcardTypeRefCS
		grammarRuleVectors[9] = new GrammarRuleVector(0x41c000000L);
	}

	/**
	 * Initialize steps for the matching process.
	 */
	private void initMatchSteps() {
		// assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0
		serializationMatchSteps[0] = createMatchStep_Assert(17);
		// assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0
		serializationMatchSteps[1] = createMatchStep_Assert(18);
		// assert (|NamedElementCS::name| - 1) == 0
		serializationMatchSteps[2] = createMatchStep_Assert(19);
		// assert (|PathElementCS::referredElement| - 1) == 0
		serializationMatchSteps[3] = createMatchStep_Assert(20);
		// assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0
		serializationMatchSteps[4] = createMatchStep_Assert(23);
		// assert (|TypedTypeRefCS::ownedPathName| - 1) == 0
		serializationMatchSteps[5] = createMatchStep_Assert(27);
		// assign V0 = (|PathNameCS::ownedPathElements| - 1)
		serializationMatchSteps[6] = createMatchStep_Assign(0, 21);
		// assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchSteps[7] = createMatchStep_Assign(0, 22);
		// assign V0 = (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchSteps[8] = createMatchStep_Assign(0, 24);
		// assign V0 = (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchSteps[9] = createMatchStep_Assign(0, 26);
		// assign V0 = |MultiplicityBoundsCS::upperBound|
		serializationMatchSteps[10] = createMatchStep_Assign(0, 3);
		// assign V0 = |MultiplicityCS::isNullFree.'|1'|
		serializationMatchSteps[11] = createMatchStep_Assign(0, 4);
		// assign V0 = |TypedTypeRefCS::ownedBinding|
		serializationMatchSteps[12] = createMatchStep_Assign(0, 14);
		// assign V0 = |WildcardTypeRefCS::ownedExtends|
		serializationMatchSteps[13] = createMatchStep_Assign(0, 16);
		// assign V1 = (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchSteps[14] = createMatchStep_Assign(1, 25);
		// assign V1 = |MultiplicityCS::isNullFree.'|1'|
		serializationMatchSteps[15] = createMatchStep_Assign(1, 4);
		// assign V1 = |TemplateBindingCS::ownedMultiplicity|
		serializationMatchSteps[16] = createMatchStep_Assign(1, 9);
		// check-rule basecs::PathNameCS.ownedPathElements : 4|16
		serializationMatchSteps[17] = createMatchStep_RuleCheck(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 1/*FirstPathElementCS|NextPathElementCS*/);
		// check-rule basecs::TemplateBindingCS.ownedMultiplicity : 13
		serializationMatchSteps[18] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 0/*MultiplicityCS*/);
		// check-rule basecs::TemplateBindingCS.ownedSubstitutions : 23
		serializationMatchSteps[19] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 4/*TemplateParameterSubstitutionCS*/);
		// check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 26
		serializationMatchSteps[20] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 6/*TypeRefCS*/);
		// check-rule basecs::TemplateSignatureCS.ownedParameters : 25
		serializationMatchSteps[21] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 5/*TypeParameterCS*/);
		// check-rule basecs::TypeParameterCS.ownedExtends : 27
		serializationMatchSteps[22] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 7/*TypedRefCS*/);
		// check-rule basecs::TypedTypeRefCS.ownedBinding : 22
		serializationMatchSteps[23] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 3/*TemplateBindingCS*/);
		// check-rule basecs::TypedTypeRefCS.ownedPathName : 17
		serializationMatchSteps[24] = createMatchStep_RuleCheck(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 2/*PathNameCS*/);
		// check-rule basecs::WildcardTypeRefCS.ownedExtends : 27
		serializationMatchSteps[25] = createMatchStep_RuleCheck(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 7/*TypedRefCS*/);
	}

	/**
	 * Initialize expression terms used during the matching process.
	 */
	private void initMatchTerms() {
		// 0
		serializationMatchTerms[0] = new SerializationMatchTermInteger(0);
		// 1
		serializationMatchTerms[1] = new SerializationMatchTermInteger(1);
		// |MultiplicityBoundsCS::lowerBound|
		serializationMatchTerms[2] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		// |MultiplicityBoundsCS::upperBound|
		serializationMatchTerms[3] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
		// |MultiplicityCS::isNullFree.'|1'|
		serializationMatchTerms[4] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, 1 /* '|1' */);
		// |MultiplicityStringCS::stringBounds.'*|+|?'|
		serializationMatchTerms[5] = createSerializationMatchTermEAttributeSize(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */);
		// |NamedElementCS::name|
		serializationMatchTerms[6] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
		// |PathElementCS::referredElement|
		serializationMatchTerms[7] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
		// |PathNameCS::ownedPathElements|
		serializationMatchTerms[8] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		// |TemplateBindingCS::ownedMultiplicity|
		serializationMatchTerms[9] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
		// |TemplateBindingCS::ownedSubstitutions|
		serializationMatchTerms[10] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		// |TemplateParameterSubstitutionCS::ownedActualParameter|
		serializationMatchTerms[11] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		// |TemplateSignatureCS::ownedParameters|
		serializationMatchTerms[12] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		// |TypeParameterCS::ownedExtends|
		serializationMatchTerms[13] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
		// |TypedTypeRefCS::ownedBinding|
		serializationMatchTerms[14] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
		// |TypedTypeRefCS::ownedPathName|
		serializationMatchTerms[15] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
		// |WildcardTypeRefCS::ownedExtends|
		serializationMatchTerms[16] = new SerializationMatchTermEStructuralFeatureSize(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		// (|MultiplicityBoundsCS::lowerBound| - 1)
		serializationMatchTerms[17] = createSerializationMatchTermSubtract(2, 1);
		// (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1)
		serializationMatchTerms[18] = createSerializationMatchTermSubtract(5, 1);
		// (|NamedElementCS::name| - 1)
		serializationMatchTerms[19] = createSerializationMatchTermSubtract(6, 1);
		// (|PathElementCS::referredElement| - 1)
		serializationMatchTerms[20] = createSerializationMatchTermSubtract(7, 1);
		// (|PathNameCS::ownedPathElements| - 1)
		serializationMatchTerms[21] = createSerializationMatchTermSubtract(8, 1);
		// (|TemplateBindingCS::ownedSubstitutions| - 1)
		serializationMatchTerms[22] = createSerializationMatchTermSubtract(10, 1);
		// (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1)
		serializationMatchTerms[23] = createSerializationMatchTermSubtract(11, 1);
		// (|TemplateSignatureCS::ownedParameters| - 1)
		serializationMatchTerms[24] = createSerializationMatchTermSubtract(12, 1);
		// (|TypeParameterCS::ownedExtends| - 1)
		serializationMatchTerms[25] = createSerializationMatchTermSubtract(13, 1);
		// (|TypeParameterCS::ownedExtends| > 0)
		serializationMatchTerms[26] = createSerializationMatchTermGreaterThan(13, 0);
		// (|TypedTypeRefCS::ownedPathName| - 1)
		serializationMatchTerms[27] = createSerializationMatchTermSubtract(15, 1);
	}

	/**
	 * Initialize the various serialization rules that serialize an EClass.
	 */
	private void initSerializationRules() {
		// Base::FirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName
		serializationRules[0] =
			new SerializationRule("FirstPathElementCS", 4,
				createSerializationMatchSteps(
					3		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					37		/* PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space */
				),
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
			;
		// Base::MultiplicityBoundsCS(basecs::MultiplicityBoundsCS): { lowerBound=LOWER { ".." upperBound=UPPER }[?] }
		serializationRules[1] =
			new SerializationRule("MultiplicityBoundsCS", 12,
				createSerializationMatchSteps(
					10		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					0		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					0		/* MultiplicityBoundsCS::lowerBound=9 || soft-space value soft-space */,
					28		/* V00*4-steps || value */,
					24		/* 1*1-steps || value */,
					16		/* '..' || no-space value no-space */,
					24		/* 1*1-steps || value */,
					11		/* MultiplicityBoundsCS::upperBound=29 || soft-space value soft-space */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				null);
			;
		// Base::MultiplicityCS(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "]" }
		serializationRules[2] =
			new SerializationRule("MultiplicityCS", 13,
				createSerializationMatchSteps(
					10		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					0		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					19		/* '[' || no-space value no-space */,
					25		/* 1*7-steps || value */,
					24		/* 1*1-steps || value */,
					0		/* MultiplicityBoundsCS::lowerBound=9 || soft-space value soft-space */,
					28		/* V00*4-steps || value */,
					24		/* 1*1-steps || value */,
					16		/* '..' || no-space value no-space */,
					24		/* 1*1-steps || value */,
					11		/* MultiplicityBoundsCS::upperBound=29 || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					20		/* ']' || no-space value */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				null);
			;
		// Base::MultiplicityCS(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } "|?" "]" }
		serializationRules[3] =
			new SerializationRule("MultiplicityCS", 13,
				createSerializationMatchSteps(
					10		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					0		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					19		/* '[' || no-space value no-space */,
					25		/* 1*7-steps || value */,
					24		/* 1*1-steps || value */,
					0		/* MultiplicityBoundsCS::lowerBound=9 || soft-space value soft-space */,
					28		/* V00*4-steps || value */,
					24		/* 1*1-steps || value */,
					16		/* '..' || no-space value no-space */,
					24		/* 1*1-steps || value */,
					11		/* MultiplicityBoundsCS::upperBound=29 || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					23		/* '|?' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					20		/* ']' || no-space value */
				),
				null,
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				null);
			;
		// Base::MultiplicityCS(basecs::MultiplicityBoundsCS): { "[" { lowerBound=LOWER { ".." upperBound=UPPER }[?] } isNullFree="|1"[?] "]" }
		serializationRules[4] =
			new SerializationRule("MultiplicityCS", 13,
				createSerializationMatchSteps(
					15		/* assign V1 = |MultiplicityCS::isNullFree.'|1'| */,
					10		/* assign V0 = |MultiplicityBoundsCS::upperBound| */,
					0		/* assert (|MultiplicityBoundsCS::lowerBound| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					19		/* '[' || no-space value no-space */,
					25		/* 1*7-steps || value */,
					24		/* 1*1-steps || value */,
					0		/* MultiplicityBoundsCS::lowerBound=9 || soft-space value soft-space */,
					28		/* V00*4-steps || value */,
					24		/* 1*1-steps || value */,
					16		/* '..' || no-space value no-space */,
					24		/* 1*1-steps || value */,
					11		/* MultiplicityBoundsCS::upperBound=29 || soft-space value soft-space */,
					31		/* V01*1-steps || value */,
					22		/* '|1' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					20		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						1	/* '|1' */
					)
				},
				null,
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* '|1' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				},
				null);
			;
		// Base::MultiplicityCS(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "]" }
		serializationRules[5] =
			new SerializationRule("MultiplicityCS", 13,
				createSerializationMatchSteps(
					1		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					19		/* '[' || no-space value no-space */,
					24		/* 1*1-steps || value */,
					10		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					20		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						0	/* '*|+|?' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(0 /* '*|+|?' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// Base::MultiplicityCS(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} "|?" "]" }
		serializationRules[6] =
			new SerializationRule("MultiplicityCS", 13,
				createSerializationMatchSteps(
					1		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					19		/* '[' || no-space value no-space */,
					24		/* 1*1-steps || value */,
					10		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					23		/* '|?' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					20		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						0	/* '*|+|?' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(0 /* '*|+|?' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// Base::MultiplicityCS(basecs::MultiplicityStringCS): { "[" stringBounds={'*|+|?'} isNullFree="|1"[?] "]" }
		serializationRules[7] =
			new SerializationRule("MultiplicityCS", 13,
				createSerializationMatchSteps(
					11		/* assign V0 = |MultiplicityCS::isNullFree.'|1'| */,
					1		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					19		/* '[' || no-space value no-space */,
					24		/* 1*1-steps || value */,
					10		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */,
					26		/* V00*1-steps || value */,
					22		/* '|1' || soft-space value soft-space */,
					24		/* 1*1-steps || value */,
					20		/* ']' || no-space value */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						1	/* '|1' */
					),
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						0	/* '*|+|?' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(1 /* '|1' */, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(0 /* '*|+|?' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// Base::MultiplicityStringCS(basecs::MultiplicityStringCS): stringBounds={'*|+|?'}
		serializationRules[8] =
			new SerializationRule("MultiplicityStringCS", 14,
				createSerializationMatchSteps(
					1		/* assert (|MultiplicityStringCS::stringBounds.'*|+|?'| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					10		/* MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space */
				),
				new @NonNull EAttribute_EnumerationValues [] {
					createEAttribute_EnumerationValues(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						0	/* '*|+|?' */
					)
				},
				null,
				null,
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(0 /* '*|+|?' */, GrammarCardinality.ONE)
						}
					)
				},
				null);
			;
		// Base::NextPathElementCS(basecs::PathElementCS): referredElement=UnreservedName
		serializationRules[9] =
			new SerializationRule("NextPathElementCS", 16,
				createSerializationMatchSteps(
					3		/* assert (|PathElementCS::referredElement| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					36		/* PathElementCS::referredElement=UnreservedName || soft-space value soft-space */
				),
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
			;
		// Base::PathNameCS(basecs::PathNameCS): { ownedPathElements+=FirstPathElementCS { "::" ownedPathElements+=NextPathElementCS }[*] }
		serializationRules[10] =
			new SerializationRule("PathNameCS", 17,
				createSerializationMatchSteps(
					17		/* check-rule basecs::PathNameCS.ownedPathElements : 4|16 */,
					6		/* assign V0 = (|PathNameCS::ownedPathElements| - 1) */
				),
				createSerializationSteps(
					35		/* PathNameCS::ownedPathElements+=4 || value */,
					27		/* V00*3-steps || value */,
					24		/* 1*1-steps || value */,
					17		/* '::' || no-space value no-space */,
					34		/* PathNameCS::ownedPathElements+=16 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						1) /* FirstPathElementCS|NextPathElementCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(4, GrammarCardinality.ONE),
						new RuleIndex_GrammarCardinality(16, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Base::TemplateBindingCS(basecs::TemplateBindingCS): { ownedSubstitutions+=TemplateParameterSubstitutionCS { "," ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] }
		serializationRules[11] =
			new SerializationRule("TemplateBindingCS", 22,
				createSerializationMatchSteps(
					18		/* check-rule basecs::TemplateBindingCS.ownedMultiplicity : 13 */,
					19		/* check-rule basecs::TemplateBindingCS.ownedSubstitutions : 23 */,
					16		/* assign V1 = |TemplateBindingCS::ownedMultiplicity| */,
					7		/* assign V0 = (|TemplateBindingCS::ownedSubstitutions| - 1) */
				),
				createSerializationSteps(
					9		/* TemplateBindingCS::ownedSubstitutions+=23 || value */,
					27		/* V00*3-steps || value */,
					24		/* 1*1-steps || value */,
					15		/* ',' || no-space value soft-space */,
					9		/* TemplateBindingCS::ownedSubstitutions+=23 || value */,
					31		/* V01*1-steps || value */,
					6		/* TemplateBindingCS::ownedMultiplicity=13 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						0) /* MultiplicityCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						4) /* TemplateParameterSubstitutionCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(13, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(23, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// Base::TemplateParameterSubstitutionCS(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS
		serializationRules[12] =
			new SerializationRule("TemplateParameterSubstitutionCS", 23,
				createSerializationMatchSteps(
					20		/* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : 26 */,
					4		/* assert (|TemplateParameterSubstitutionCS::ownedActualParameter| - 1) == 0 */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					2		/* TemplateParameterSubstitutionCS::ownedActualParameter=26 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						6) /* TypeRefCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(26, GrammarCardinality.ONE)
						}
					)
				});
			;
		// Base::TemplateSignatureCS(basecs::TemplateSignatureCS): { "(" ownedParameters+=TypeParameterCS { "," ownedParameters+=TypeParameterCS }[*] ")" }
		serializationRules[13] =
			new SerializationRule("TemplateSignatureCS", 24,
				createSerializationMatchSteps(
					21		/* check-rule basecs::TemplateSignatureCS.ownedParameters : 25 */,
					8		/* assign V0 = (|TemplateSignatureCS::ownedParameters| - 1) */
				),
				createSerializationSteps(
					33		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					24		/* 1*1-steps || value */,
					13		/* '(' || no-space value no-space */,
					7		/* TemplateSignatureCS::ownedParameters+=25 || value */,
					27		/* V00*3-steps || value */,
					24		/* 1*1-steps || value */,
					15		/* ',' || no-space value soft-space */,
					7		/* TemplateSignatureCS::ownedParameters+=25 || value */,
					24		/* 1*1-steps || value */,
					14		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						5) /* TypeParameterCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(25, GrammarCardinality.ONE_OR_MORE)
						}
					)
				});
			;
		// Base::TypeParameterCS(basecs::TypeParameterCS): { name=UnrestrictedName { "extends" ownedExtends+=TypedRefCS { "&&" ownedExtends+=TypedRefCS }[*] }[?] }
		serializationRules[14] =
			new SerializationRule("TypeParameterCS", 25,
				createSerializationMatchSteps(
					22		/* check-rule basecs::TypeParameterCS.ownedExtends : 27 */,
					2		/* assert (|NamedElementCS::name| - 1) == 0 */,
					9		/* assign V0 = (|TypeParameterCS::ownedExtends| > 0) */,
					14		/* assign V1 = (|TypeParameterCS::ownedExtends| - 1) */
				),
				createSerializationSteps(
					33		/* wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value */,
					24		/* 1*1-steps || value */,
					1		/* NamedElementCS::name=32 || soft-space value soft-space */,
					30		/* V00*7-steps || value */,
					24		/* 1*1-steps || value */,
					21		/* 'extends' || soft-space value soft-space */,
					4		/* TypeParameterCS::ownedExtends+=27 || value */,
					32		/* V01*3-steps || value */,
					24		/* 1*1-steps || value */,
					12		/* '&&' || soft-space value soft-space */,
					4		/* TypeParameterCS::ownedExtends+=27 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						7) /* TypedRefCS */
				},
				new /*@NonNull*/ EAttribute [] {
					BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME
				},
				new @NonNull EAttribute_EnumerationValue_GrammarCardinality [] {
					new EAttribute_EnumerationValue_GrammarCardinality(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
						new @NonNull EnumerationValue_GrammarCardinality [] {
							createEnumerationValue_GrammarCardinality(-1, GrammarCardinality.ONE)
						}
					)
				},
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ZERO_OR_MORE)
						}
					)
				});
			;
		// Base::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS { "(" ownedBinding=TemplateBindingCS ")" }[?] }
		serializationRules[15] =
			new SerializationRule("TypedTypeRefCS", 28,
				createSerializationMatchSteps(
					23		/* check-rule basecs::TypedTypeRefCS.ownedBinding : 22 */,
					24		/* check-rule basecs::TypedTypeRefCS.ownedPathName : 17 */,
					12		/* assign V0 = |TypedTypeRefCS::ownedBinding| */,
					5		/* assert (|TypedTypeRefCS::ownedPathName| - 1) == 0 */
				),
				createSerializationSteps(
					8		/* TypedTypeRefCS::ownedPathName=17 || value */,
					29		/* V00*5-steps || value */,
					24		/* 1*1-steps || value */,
					13		/* '(' || no-space value no-space */,
					3		/* TypedTypeRefCS::ownedBinding=22 || value */,
					24		/* 1*1-steps || value */,
					14		/* ')' || no-space value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						3) /* TemplateBindingCS */,
					createEReference_RuleIndexes(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						2) /* PathNameCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(22, GrammarCardinality.ZERO_OR_ONE)
						}
					),
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(17, GrammarCardinality.ONE)
						}
					)
				});
			;
		// Base::WildcardTypeRefCS(basecs::WildcardTypeRefCS): { "?" { "extends" ownedExtends=TypedRefCS }[?] }
		serializationRules[16] =
			new SerializationRule("WildcardTypeRefCS", 34,
				createSerializationMatchSteps(
					25		/* check-rule basecs::WildcardTypeRefCS.ownedExtends : 27 */,
					13		/* assign V0 = |WildcardTypeRefCS::ownedExtends| */
				),
				createSerializationSteps(
					24		/* 1*1-steps || value */,
					18		/* '?' || soft-space value soft-space */,
					27		/* V00*3-steps || value */,
					24		/* 1*1-steps || value */,
					21		/* 'extends' || soft-space value soft-space */,
					5		/* WildcardTypeRefCS::ownedExtends=27 || value */
				),
				null,
				new @NonNull EReference_RuleIndexes [] {
					createEReference_RuleIndexes(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						7) /* TypedRefCS */
				},
				null,
				null,
				new @NonNull EReference_RuleIndex_GrammarCardinality [] {
					new EReference_RuleIndex_GrammarCardinality(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS,
						new @NonNull RuleIndex_GrammarCardinality [] {
						new RuleIndex_GrammarCardinality(27, GrammarCardinality.ZERO_OR_ONE)
						}
					)
				});
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
			new CustomSerializationSegment(BaseCommentSegmentSupport.class) /* supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport */,
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
		// MultiplicityBoundsCS::lowerBound=9 || soft-space value soft-space
		serializationSteps[0] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, 9 /*LOWER*/, 5);
		// NamedElementCS::name=32 || soft-space value soft-space
		serializationSteps[1] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, 32 /*UnrestrictedName*/, 5);
		// TemplateParameterSubstitutionCS::ownedActualParameter=26 || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[2] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, 26 /*TypeRefCS*/, 2);
		// TypedTypeRefCS::ownedBinding=22 || value
		serializationSteps[3] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, 22 /*TemplateBindingCS*/, 0);
		// TypeParameterCS::ownedExtends+=27 || value
		serializationSteps[4] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, 27 /*TypedRefCS*/, 0);
		// WildcardTypeRefCS::ownedExtends=27 || value
		serializationSteps[5] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, 27 /*TypedRefCS*/, 0);
		// TemplateBindingCS::ownedMultiplicity=13 || value
		serializationSteps[6] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, 13 /*MultiplicityCS*/, 0);
		// TemplateSignatureCS::ownedParameters+=25 || value
		serializationSteps[7] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, 25 /*TypeParameterCS*/, 0);
		// TypedTypeRefCS::ownedPathName=17 || value
		serializationSteps[8] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, 17 /*PathNameCS*/, 0);
		// TemplateBindingCS::ownedSubstitutions+=23 || value
		serializationSteps[9] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, 23 /*TemplateParameterSubstitutionCS*/, 0);
		// MultiplicityStringCS::stringBounds='*|+|?' || soft-space value soft-space
		serializationSteps[10] = createSerializationStepAssignKeyword(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, 0 /* '*|+|?' */, 5);
		// MultiplicityBoundsCS::upperBound=29 || soft-space value soft-space
		serializationSteps[11] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, 29 /*UPPER*/, 5);
		// '&&' || soft-space value soft-space
		serializationSteps[12] = createSerializationStepKeyword("&&", 5);
		// '(' || no-space value no-space
		serializationSteps[13] = createSerializationStepKeyword("(", 3);
		// ')' || no-space value
		serializationSteps[14] = createSerializationStepKeyword(")", 1);
		// ',' || no-space value soft-space
		serializationSteps[15] = createSerializationStepKeyword(",", 4);
		// '..' || no-space value no-space
		serializationSteps[16] = createSerializationStepKeyword("..", 3);
		// '::' || no-space value no-space
		serializationSteps[17] = createSerializationStepKeyword("::", 3);
		// '?' || soft-space value soft-space
		serializationSteps[18] = createSerializationStepKeyword("?", 5);
		// '[' || no-space value no-space
		serializationSteps[19] = createSerializationStepKeyword("[", 3);
		// ']' || no-space value
		serializationSteps[20] = createSerializationStepKeyword("]", 1);
		// 'extends' || soft-space value soft-space
		serializationSteps[21] = createSerializationStepKeyword("extends", 5);
		// '|1' || soft-space value soft-space
		serializationSteps[22] = createSerializationStepKeyword("|1", 5);
		// '|?' || soft-space value soft-space
		serializationSteps[23] = createSerializationStepKeyword("|?", 5);
		// 1*1-steps || value
		serializationSteps[24] = createSerializationStepSequence(-1, 1, 0);
		// 1*7-steps || value
		serializationSteps[25] = createSerializationStepSequence(-1, 7, 0);
		// V00*1-steps || value
		serializationSteps[26] = createSerializationStepSequence(0, 1, 0);
		// V00*3-steps || value
		serializationSteps[27] = createSerializationStepSequence(0, 3, 0);
		// V00*4-steps || value
		serializationSteps[28] = createSerializationStepSequence(0, 4, 0);
		// V00*5-steps || value
		serializationSteps[29] = createSerializationStepSequence(0, 5, 0);
		// V00*7-steps || value
		serializationSteps[30] = createSerializationStepSequence(0, 7, 0);
		// V01*1-steps || value
		serializationSteps[31] = createSerializationStepSequence(1, 1, 0);
		// V01*3-steps || value
		serializationSteps[32] = createSerializationStepSequence(1, 3, 0);
		// wrapper || supported by org.eclipse.ocl.xtext.base.serializer.BaseCommentSegmentSupport value
		serializationSteps[33] = createSerializationStepWrapper(2);
		// PathNameCS::ownedPathElements+=16 || value
		serializationSteps[34] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 16 /*NextPathElementCS*/, 0);
		// PathNameCS::ownedPathElements+=4 || value
		serializationSteps[35] = createSerializationStepAssignedRuleCall(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, 4 /*FirstPathElementCS*/, 0);
		// PathElementCS::referredElement=UnreservedName || soft-space value soft-space
		serializationSteps[36] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnreservedName"), 5);
		// PathElementCS::referredElement=UnrestrictedName || soft-space value soft-space
		serializationSteps[37] = createSerializationStepCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, getCrossReference(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "UnrestrictedName"), 5);
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
//	import BaseCommentSegmentSupport;
//	import BaseCSPackage;
